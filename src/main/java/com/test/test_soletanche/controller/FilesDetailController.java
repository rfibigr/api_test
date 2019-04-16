package com.test.test_soletanche.controller;

import com.test.test_soletanche.model.FilesDetail;
import com.test.test_soletanche.service.FilesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.util.List;

@RestController
public class FilesDetailController {

    @Autowired
    FilesService filesService;


    @GetMapping(value = "/file")
    public FilesDetail[] getAllFilesDetailList() {
        return (filesService.getAllFilesDetail());
    }

    @GetMapping(value = "/file/{id}")
    public FilesDetail displayFiles(@PathVariable int id){
        File folder = new File("/Users/rfibigr/Documents/test_soletanche");
        File[] listOfFiles = folder.listFiles();
        FilesDetail files = new FilesDetail(id, listOfFiles[id]);

        return files;

    }

}
