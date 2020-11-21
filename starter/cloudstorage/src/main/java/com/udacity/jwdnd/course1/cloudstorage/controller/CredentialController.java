package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Credentials;
import com.udacity.jwdnd.course1.cloudstorage.model.CredentialsForm;
import com.udacity.jwdnd.course1.cloudstorage.model.NoteForm;
import com.udacity.jwdnd.course1.cloudstorage.services.*;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller()
@RequestMapping("/credentials")
public class CredentialController {

    private CredentialsService credentialsService;
    private UserService userService;
    private AuthenticationService authenticationService;
    private CredentialsService credentialService;
    private NotesService notesService;
    private EncryptionService encryptionService;

    public CredentialController(CredentialsService credentialsService, UserService userService, AuthenticationService authenticationService, CredentialsService credentialService, NotesService notesService,EncryptionService encryptionService) {
        this.credentialsService = credentialsService;
        this.userService = userService;
        this.authenticationService = authenticationService;
        this.credentialService = credentialService;
        this.notesService = notesService;
        this.encryptionService = encryptionService;
    }

    @ModelAttribute("credentialsForm")
    public CredentialsForm getCredentials() {
        return new CredentialsForm();
    }

    @ModelAttribute("noteForm")
    public NoteForm getNotes() {
        return new NoteForm();
    }

    @ModelAttribute("encryptionService")
    public EncryptionService startEncryptionService() {
        return new EncryptionService();
    }

    @GetMapping
    public String getData(Authentication authentication, Model model){
        model.addAttribute("encryptionService", encryptionService);
        model.addAttribute("credentials", this.credentialService.getUserCredentials(userService.getUser(authentication.getName()).getUserid()));
        model.addAttribute("notes", this.notesService.getAllNotes(userService.getUser(authentication.getName()).getUserid()));

        return "home";
    }

    @PostMapping("/insert")
    public String postCredential(Authentication authentication, @ModelAttribute("Credentials") Credentials credentials, Model model) {
        credentialsService.addCredential(credentials, authentication);
        model.addAttribute("Notes", this.notesService.getAllNotes(userService.getUser(authentication.getName()).getUserid()));
        model.addAttribute("Credentials", this.credentialService.getUserCredentials(userService.getUser(authentication.getName()).getUserid()));
        return "home";
    }


}