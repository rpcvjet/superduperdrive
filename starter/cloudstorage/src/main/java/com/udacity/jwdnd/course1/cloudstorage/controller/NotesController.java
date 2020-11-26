package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.CredentialsForm;
import com.udacity.jwdnd.course1.cloudstorage.model.NoteForm;
import com.udacity.jwdnd.course1.cloudstorage.model.Notes;
import com.udacity.jwdnd.course1.cloudstorage.services.*;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/note")
public class NotesController {

    private NotesService notesService;
    private UserService userService;
    private AuthenticationService authenticationService;
    private CredentialsService credentialService;
    private EncryptionService encryptionService;

    public NotesController(NotesService notesService, UserService userService, AuthenticationService authenticationService, CredentialsService credentialService, EncryptionService encryptionService) {
        this.notesService = notesService;
        this.userService = userService;
        this.authenticationService = authenticationService;
        this.credentialService = credentialService;
        this.encryptionService = encryptionService;
    }

    @ModelAttribute("noteForm")
    public NoteForm getNotes() {
        return new NoteForm();
    }

    @ModelAttribute("credentialsForm")
    public CredentialsForm getCredentials(){
        return new CredentialsForm();
    }

    @PostMapping("/insert")
    public String postMessage(Authentication authentication, @ModelAttribute("Notes") Notes notes, Model model) {
        System.out.println("POSTING A NOTE");
        notesService.addNote(notes, authentication);
        model.addAttribute("encryptionService", encryptionService);
        model.addAttribute("Notes", this.notesService.getAllNotes(userService.getUser(authentication.getName()).getUserid()));
        model.addAttribute("Credentials", this.credentialService.getUserCredentials(userService.getUser(authentication.getName()).getUserid()));
        return "home";
    }

    @GetMapping("/delete/{noteId}")
    public String deleteNote(@PathVariable Integer noteId, Model model, Authentication authentication) {
        notesService.deleteNote(noteId);
        model.addAttribute("encryptionService", encryptionService);
        model.addAttribute("Notes", this.notesService.getAllNotes(userService.getUser(authentication.getName()).getUserid()));
        model.addAttribute("Credentials", this.credentialService.getUserCredentials(userService.getUser(authentication.getName()).getUserid()));

        return "home";
    }
}
