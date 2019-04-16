package com.test.test_soletanche.controller;

import com.test.test_soletanche.model.FilesDetail;
import com.test.test_soletanche.service.FilesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@RestController
public class FilesDetailController {


    @Autowired
    FilesService filesService;

    @GetMapping(value = "/file")
    public FilesDetail[] getAllTheFiles() {
        return (filesService.getAllTheFiles());
    }

    @GetMapping(value = "/file/{id}")
    public FilesDetail getFilesbyid(@PathVariable int id){
        return (filesService.getFileById(id));
    }

    //@GetMapping(value = "/file/{name:.+")

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
    public FilesDetail uploadFile(@RequestParam(value="file", required = true)MultipartFile file){
        return filesService.uploadFile(file);
    }

}
