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
    Try to optimise
    Change array for list
     */
    public FilesDetail[] getAllFilesDetail(){
        File folder = new File("/");
        File[] listOfFiles = folder.listFiles();
        FilesDetail[] filesDetailList = new FilesDetail[listOfFiles.length];
        for(int i = 0; i < listOfFiles.length; i++){
            filesDetailList[i] = new FilesDetail(i, listOfFiles[i]);
        }
        return filesDetailList;
    }
}
