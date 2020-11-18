package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Credentials;
import com.udacity.jwdnd.course1.cloudstorage.model.CredentialsForm;
import com.udacity.jwdnd.course1.cloudstorage.model.NoteForm;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialsService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/credentials")
public class CredentialController {

    private CredentialsService credentialsService;
    private UserService userService;

    public CredentialController(CredentialsService credentialsService, UserService userService) {
        this.credentialsService = credentialsService;
        this.userService = userService;
    }

    @PostMapping("/insert")
    public String postCredential(Authentication authentication, CredentialsForm credentialsForm, Model model){
        User user = userService.getUser(authentication.getName());
        Credentials credentials = new Credentials();


        if(credentialsForm.getCredentialid() == null){
            model.addAttribute("credentialsForm", new CredentialsForm());
            model.addAttribute("noteForm", new NoteForm());
            System.out.println("POSTING CREDENTIALS");
            credentials.setUserid(user.getUserid());
            credentials.setUrl(credentialsForm.getUrl());
            credentials.setUsername(credentialsForm.getUsername());
            credentials.setPassword(credentialsForm.getPassword());

            credentialsService.addCredential(credentials);
            model.addAttribute("Credentials",credentialsService.getUserCredentials(user));
            return "home";
        }
        else {
            System.out.println("UPDATING CREDENTIALS");
            credentials.setPassword(credentials.getPassword());
            credentials.setUrl(credentials.getUrl());
            credentials.setUsername(credentials.getUsername());
            return "home";
        }

    }

}
