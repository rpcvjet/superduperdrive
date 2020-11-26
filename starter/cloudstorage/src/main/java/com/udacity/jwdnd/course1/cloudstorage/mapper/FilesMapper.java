package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Files;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface FilesMapper {

    @Select("SELECT * FROM FILES WHERE userid = #{userid}")
    List<Files> getAllFilesByUserId(Integer userId);

    @Select("SELECT * FROM FILES WHERE fileId = #{fileId}")
    Files getFileId(Integer fileId);

    @Select("SELECT * FROM FILES WHERE filename = #{filename} AND userid = #{userId}")
    Files getFile(String filename, Integer userId);

    @Insert("INSERT INTO FILES (filename, contenttype, filesize, userid, filedata)" +
            "VALUES(#{filename}, #{contenttype}, #{filesize}, #{userid}, #{filedata})")
    @Options(useGeneratedKeys = true, keyProperty = "fileId")
    Integer insert(Files file);

    @Update("UPDATE FILES set filename = #{filename}, contenttype = #{contenttype}, filesize = #{filesize}, " +
            "userId = #{userId}, filedata = #{filedata} where fileId = #{fileId}")
    Integer update(Files file);

    @Delete("DELETE FROM FILES WHERE fileId = #{fileId} AND userid = #{userid}")
    int delete(Files file);
}
