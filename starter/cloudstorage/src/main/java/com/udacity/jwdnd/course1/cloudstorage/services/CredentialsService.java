package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.CredentialsMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Credentials;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class CredentialsService {

    private final CredentialsMapper credentialsMapper;
    private final UserService userService;

    public CredentialsService(CredentialsMapper credentialsMapper, UserService userService) {
        this.credentialsMapper = credentialsMapper;
        this.userService = userService;
    }

    @PostConstruct
    public void postConstruct() {
        System.out.println("CREATING CREDENTIALS SERVICE");
    }

    public List<Credentials> getUserCredentials(User user){
        return credentialsMapper.getUserCredentials(user.getUserid());
    }

    public Integer addCredential(Credentials credentials) {
        return credentialsMapper.addCredential(credentials);
    }

    public List<Credentials> getAllCredentials() {
        return credentialsMapper.getAllCredentials();
    }
}
