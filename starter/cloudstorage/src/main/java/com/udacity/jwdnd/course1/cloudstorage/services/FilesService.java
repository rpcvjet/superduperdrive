package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.FilesMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Files;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FilesService {

    private final FilesMapper fileMapper;

    public FilesService(FilesMapper fileMapper) {
        this.fileMapper = fileMapper;
    }

    public List<Files> checkForDuplicates(Integer userId, String fileName) {
        return fileMapper.selectByNameAndUserId(userId, fileName);
    }

    public int uploadFile(Files files){
        return fileMapper.insertFile(files);
    }

    public int deleteFile(Integer fileId){
        return fileMapper.deleteFile(fileId);
    }

    public Files downloadFile(Integer fileId){
        return fileMapper.selectById(fileId);
    }

    public List<Files> getAllUserFiles(Integer userId){
        return fileMapper.selectAllFilesByUID(userId);
    }

    public List<Files> getAllFiles(){ return fileMapper.selectAllFiles();
    }
}