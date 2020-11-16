package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.NotesMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Notes;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class NotesService {
    private final NotesMapper notesMapper;

    public NotesService(NotesMapper notesMapper) {
        this.notesMapper = notesMapper;
    }

    @PostConstruct
    public void postConstruct() {
        System.out.println("CREATING NOTES SERVICE");
    }

    public List<Notes> getAllNotes(){
        return notesMapper.getAllNotes();
    }

    public Integer addNote(Notes note){
        System.out.println("THIS IS THE NOTE SENDING "+ note.toString());
        return notesMapper.addNote(note);
    }

    public void updateNoteById(Notes note) {
        notesMapper.updateNote(note);
    }

    public void deleteNote(Integer noteid) {
        notesMapper.deleteByNoteId(noteid);
    }

}
