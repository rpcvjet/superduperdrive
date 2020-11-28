package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.CredentialsForm;
import com.udacity.jwdnd.course1.cloudstorage.model.NoteForm;
import com.udacity.jwdnd.course1.cloudstorage.services.*;
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
    private final FilesService filesService;

    public HomeController(NotesService notesService, CredentialsService credentialsService, UserService userService, EncryptionService encryptionService, FilesService filesService) {
        this.notesService = notesService;
        this.credentialsService = credentialsService;
        this.userService = userService;
        this.encryptionService = encryptionService;
        this.filesService = filesService;
    }

    @GetMapping
    public String getHomePage( Model model){
        model.addAttribute("noteForm", new NoteForm());
        model.addAttribute("credentialsForm", new CredentialsForm());
        model.addAttribute("encryptionService", encryptionService);
        model.addAttribute("Notes", notesService.getAllNotes(userService.getCurrentUser().getUserid()));
        model.addAttribute("Credentials", credentialsService.getUserCredentials(userService.getCurrentUser().getUserid()));
        model.addAttribute("files", filesService.getAllUserFiles(userService.getCurrentUser().getUserid()));
        return "home";
    }


}
