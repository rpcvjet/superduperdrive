package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.NoteForm;
import com.udacity.jwdnd.course1.cloudstorage.model.Notes;
import com.udacity.jwdnd.course1.cloudstorage.services.NotesService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/note")
public class NotesController {

    private NotesService notesService;
    private UserService userService;

    public NotesController(NotesService notesService, UserService userService) {
        this.notesService = notesService;
        this.userService = userService;
    }

    @PostMapping("/insert")
    public String postMessage(Authentication authentication, NoteForm noteForm, Model model) {
        Notes note = new Notes();
        model.addAttribute("noteForm", new NoteForm());

        if (noteForm.getNoteid() == null) {
            System.out.println("POSTINGGGGGGGGGG");
            System.out.println(note.toString());
            note.setNotetitle(noteForm.getNotetitle());
            note.setNotedescription(noteForm.getNotedescription());
            notesService.addNote(note);
            noteForm.setNotetitle("");
            noteForm.setNotedescription("");
            model.addAttribute("Notes", this.notesService.getAllNotes());
            return "home";
        }
        else {
            System.out.println("UPDATING NOTE");
            note.setNoteid(noteForm.getNoteid());
            note.setNotetitle(noteForm.getNotetitle());
            note.setNotedescription(noteForm.getNotedescription());
            notesService.updateNoteById(note);
            model.addAttribute("Notes", this.notesService.getAllNotes());
            return "home";
        }

    }

    @GetMapping("/delete/{noteId}")
    public String deleteNote(@PathVariable Integer noteId, Model model, Authentication authentication) {
        notesService.deleteNote(noteId);
        model.addAttribute("Notes", this.notesService.getAllNotes());
        model.addAttribute("noteForm", new NoteForm());
        return "home";
    }
}
