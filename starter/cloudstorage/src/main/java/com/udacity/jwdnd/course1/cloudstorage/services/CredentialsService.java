package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.CredentialsMapper;
import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Credentials;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class CredentialsService {

    private final CredentialsMapper credentialsMapper;
    private final UserMapper userMapper;
    private final AuthenticationService authenticationService;
    private final UserService userService;
    private final EncryptionService encryptionService;

    public CredentialsService(CredentialsMapper credentialsMapper, UserMapper userMapper, AuthenticationService authenticationService, UserService userService, EncryptionService encryptionService) {
        this.credentialsMapper = credentialsMapper;
        this.userMapper = userMapper;
        this.authenticationService = authenticationService;
        this.userService = userService;
        this.encryptionService = encryptionService;
    }

    @PostConstruct
    public void postConstruct() {
        System.out.println("CREATING CREDENTIALS SERVICE");
    }

    public List<Credentials> getUserCredentials(Integer userid){
        System.out.println("GETTING CREDENTIALS");
        return credentialsMapper.getUserCredentials(userService.getCurrentUser().getUserid());
    }

    public Integer addCredential(Credentials credentials, Authentication authentication) {
        //INSERT HERE
        if(credentials.getCredentialid() == null){
            System.out.println("POSTING CREDENTIALS!!");
            String key = encryptionService.getSecureKey().toString();
            System.out.println("KEY CREATE " +  key);
            credentialsMapper.addCredential(new Credentials(null, credentials.getUrl(), credentials.getUsername(), key, encryptionService.encryptValue(credentials.getPassword(), key), userMapper.getUser(authentication.getName()).getUserid()));
        }
        //UPDATE HERE
        else {
            System.out.println("UPDATING CREDENTIALS");
            credentials.setPassword(encryptionService.encryptValue(credentials.getPassword(),credentials.getKey()));
            credentialsMapper.updateCredential(credentials);
        }
        return credentialsMapper.addCredential(credentials);
    }
    public void deleteCredential(Integer credentialid) {
        credentialsMapper.deleteCredential(credentialid);
    }
}
