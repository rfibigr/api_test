package com.test.test_soletanche.controller;

import com.test.test_soletanche.DAO.FileDao;
import com.test.test_soletanche.model.FilesDetail;
import com.test.test_soletanche.service.FilesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;



@RestController
public class FilesDetailController {


    @Autowired
    FilesService filesService;

    @Autowired
    FileDao fileDao;

    @GetMapping(value = "/file")
    public List<FilesDetail> getAllFiles() {
        return (filesService.getAllFiles());
    }

    @GetMapping(value = "/file/{id}")
    public FilesDetail getFileById(@PathVariable("id") int id) {
        FilesDetail fileid = filesService.getFileById(id);
        if (fileid != null){
            return (fileid);
        }
        else
            //TODO Gerer les erreurs
            return (null);
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<Resource> downloadFile(@PathVariable int id) throws IOException {
        FilesDetail fileToDownload = filesService.getFileById(id);

        // Variable Declaration
        Path path = fileToDownload.getPath();
        HttpHeaders headers = new HttpHeaders();
        String name = fileToDownload.getFileName();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + name + "\"");
        long length = fileToDownload.getSize();

        //Create resource
        ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path));

        //Return resource
        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(fileToDownload.getSize())
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(resource);
    }

    @PostMapping("/upload")
    public ResponseEntity<Void> uploadFile(@RequestParam("file")MultipartFile file){

        FilesDetail fileUpload = fileDao.uploadFile(file);
        if ( null ){
           return ResponseEntity.noContent().build();
        }
        return ResponseEntity.created( uri ).build();
    }

    @PostMapping("/move")
    public String movePath(@RequestBody String newPath){
        return(fileDao.changePath(newPath));
    }


}

