package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Notes;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface NotesMapper {
    @Select("SELECT * FROM NOTES")
    List<Notes> getAllNotes();

    @Select("SELECT * FROM NOTES WHERE userid = #{userid}")
    List<Notes> getAllNotesByUserId(Integer userid);

    @Insert("INSERT INTO NOTES(notetitle, notedescription, noteid) VALUES( #{notetitle}, #{notedescription}, #{noteid})")
    @Options(useGeneratedKeys = true, keyProperty = "noteid")
    Integer addNote(Notes note);

    @Update("UPDATE NOTES SET notetitle = #{notetitle}, notedescription = #{notedescription} WHERE noteid = #{noteid}")
    void updateNote(Notes note);

    @Delete("DELETE FROM Notes WHERE noteid = #{noteid}")
    void deleteByNoteId(Integer noteid);
}
