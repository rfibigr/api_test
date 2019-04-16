package com.test.test_soletanche.service;

import com.test.test_soletanche.model.FilesDetail;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class FilesService {

    /*
    Get all the detail for each file in the root directory
        TODO
    Cree un constructor pour la class qui instancie tous les elements
    Try to optimise
    Change array for list
     */
    private static final String DIRECTORY = "/Users/rfibigr/";
    private FilesDetail[] allTheFiles;

    public FilesService() {
        File folder = new File(DIRECTORY);
        File[] listOfFiles = folder.listFiles();
        this.allTheFiles = new FilesDetail[listOfFiles.length];
        for(int i = 0; i < listOfFiles.length; i++){
            this.allTheFiles[i] = new FilesDetail(i, listOfFiles[i]);
        }
    }

    public FilesDetail[] getAllTheFiles() {
        return allTheFiles;
    }

    public FilesDetail getFileById(int id){
        return allTheFiles[id];
    }

    //getFilesByName

    //getFilesByExtention

    //getFileByCreationDate


}
