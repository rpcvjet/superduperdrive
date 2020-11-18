package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.NotesMapper;
import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Notes;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class NotesService {
    private final NotesMapper notesMapper;
    private final UserMapper userMapper;
    private final AuthenticationService authenticationService;
    private final UserService userService;

    public NotesService(NotesMapper notesMapper, UserMapper userMapper, AuthenticationService authenticationService, UserService userService) {
        this.notesMapper = notesMapper;
        this.userMapper = userMapper;
        this.authenticationService = authenticationService;
        this.userService = userService;
    }

    @PostConstruct
    public void postConstruct() {
        System.out.println("CREATING NOTES SERVICE");
    }

    public List<Notes> getAllNotes(Integer userid){
        System.out.println("GETTING USER ID" + userid);
        return notesMapper.getAllNotesByUserId(userService.getCurrentUser().getUserid());
    }

    public Integer addNote(Notes note, Authentication authentication){
        System.out.println("NOTE ID " + note.getNoteid());
        System.out.println("NOTE Title" + note.getNotetitle());
        System.out.println("NOTE DESC" + note.getNotedescription());
        if (note.getNoteid() == null) {
            System.out.println("POSTINGGGGGGGGGG");
            notesMapper.addNote(new Notes(null,note.getNotetitle(), note.getNotedescription(), userMapper.getUser(authentication.getName()).getUserid()));
        }
        else {
            System.out.println("UPDATING NOTE");
            note.setNotedescription(note.getNotedescription());
            note.setNotetitle(note.getNotetitle());
            notesMapper.updateNote(note);
        }
        return notesMapper.addNote(note);
    }

    public void updateNoteById(Notes note) {
        notesMapper.updateNote(note);
    }

    public void deleteNote(Integer noteid) {
        notesMapper.deleteByNoteId(noteid);
    }

}
