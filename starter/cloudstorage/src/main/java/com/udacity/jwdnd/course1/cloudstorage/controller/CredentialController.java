package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Credentials;
import com.udacity.jwdnd.course1.cloudstorage.model.CredentialsForm;
import com.udacity.jwdnd.course1.cloudstorage.model.NoteForm;
import com.udacity.jwdnd.course1.cloudstorage.services.*;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller()
@RequestMapping("/credentials")
public class CredentialController {

    private CredentialsService credentialsService;
    private UserService userService;
    private AuthenticationService authenticationService;
    private CredentialsService credentialService;
    private NotesService notesService;
    private EncryptionService encryptionService;
    private FilesService filesService;

    public CredentialController(CredentialsService credentialsService, UserService userService, AuthenticationService authenticationService, CredentialsService credentialService, NotesService notesService,EncryptionService encryptionService,FilesService filesService) {
        this.credentialsService = credentialsService;
        this.userService = userService;
        this.authenticationService = authenticationService;
        this.credentialService = credentialService;
        this.notesService = notesService;
        this.encryptionService = encryptionService;
        this.filesService = filesService;
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
        model.addAttribute("files", filesService.getAllUserFiles(userService.getCurrentUser().getUserid()));
        return "home";
    }

    @PostMapping("/insert")
    public String postCredential(Authentication authentication, @ModelAttribute("Credentials") Credentials credentials, Model model) {
        System.out.println("***** ADDING CREDENTIALS*****2");
        credentialsService.addCredential(credentials, authentication);
        model.addAttribute("Notes", this.notesService.getAllNotes(userService.getUser(authentication.getName()).getUserid()));
        model.addAttribute("Credentials", this.credentialService.getUserCredentials(userService.getUser(authentication.getName()).getUserid()));
        model.addAttribute("files", filesService.getAllUserFiles(userService.getCurrentUser().getUserid()));
        return "home";
    }

    @GetMapping("/delete/{credentialid}")
    public String deleteCredential(@PathVariable Integer credentialid, Model model, Authentication authentication) {
        credentialsService.deleteCredential(credentialid);
        model.addAttribute("encryptionService", encryptionService);
        model.addAttribute("Credentials", this.credentialService.getUserCredentials(userService.getUser(authentication.getName()).getUserid()));
        model.addAttribute("Notes", this.notesService.getAllNotes(userService.getUser(authentication.getName()).getUserid()));
        model.addAttribute("files", filesService.getAllUserFiles(userService.getCurrentUser().getUserid()));
        return "home";
    }


}