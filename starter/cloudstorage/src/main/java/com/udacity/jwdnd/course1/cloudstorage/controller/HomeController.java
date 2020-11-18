package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.NoteForm;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialsService;
import com.udacity.jwdnd.course1.cloudstorage.services.EncryptionService;
import com.udacity.jwdnd.course1.cloudstorage.services.NotesService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
@RequestMapping("/home")
public class HomeController {

    private final NotesService notesService;
    private final CredentialsService credentialsService;
    private final UserService userService;
    private final EncryptionService encryptionService;

    public HomeController(NotesService notesService, CredentialsService credentialsService, UserService userService, EncryptionService encryptionService) {
        this.notesService = notesService;
        this.credentialsService = credentialsService;
        this.userService = userService;
        this.encryptionService = encryptionService;
    }

    @GetMapping
    public String getHomePage( Model model){
        System.out.println("HOMECONTROLLERRRRRRRR");
        model.addAttribute("noteForm", new NoteForm());
        model.addAttribute("encryptionService", encryptionService);
        model.addAttribute("Notes", notesService.getAllNotes(userService.getCurrentUser().getUserid()));
        System.out.println("current User " + notesService.getAllNotes(userService.getCurrentUser().getUserid()) );
        model.addAttribute("credentialsForm", credentialsService.getAllCredentials());
        return "home";
    }


}
