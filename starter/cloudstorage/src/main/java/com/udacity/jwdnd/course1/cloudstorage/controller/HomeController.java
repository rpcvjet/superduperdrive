package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.NoteForm;
import com.udacity.jwdnd.course1.cloudstorage.services.NotesService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class HomeController {

    private NotesService notesService;

    public HomeController(NotesService notesService) {
        this.notesService = notesService;
    }

    @GetMapping()
    public String getHomePage(NoteForm noteForm ,Model model){
        System.out.println("HOMECONTROLLERRRRRRRR");
        return "home";
    }


}
