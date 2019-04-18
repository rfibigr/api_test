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

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;


/**
 * Class Controller
 *
 * GET /file : List all the files and Directory in the current Path
 * GET /file/{id} : get file information by id
 * GET /file/isdir : List all the Directory int the current Path
 * GET /download/{id} : Download file by id
 * GET /path : Get the path of the current directory
 * POST /upload : upload a file int the current directory
 * POST /move : change the current directory
 */

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
            return (null);
    }

    @GetMapping(value = "/file/isdir")
    public List<FilesDetail> getAllDir(){
        List<FilesDetail> fileDir = filesService.getFileByType();
        if (fileDir != null){
            return (fileDir);
        }
        else
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

    @GetMapping("/path")
    public Path getCurrentPath(){
        return(fileDao.getCurrentPath());
    }

    @PostMapping("/upload")
    public ResponseEntity<Void> uploadFile(@RequestParam("file")MultipartFile file){

        Path fileUpload = fileDao.uploadFile(file);
        if (fileUpload == null){
           return ResponseEntity.noContent().build();
        }
        return ResponseEntity.created(fileUpload.toUri()).build();
    }

    @PostMapping("/move")
    public ResponseEntity<Void> movePath(@RequestBody String newPath){
        Path newCurrentPath= fileDao.changePath(newPath);
        if (newCurrentPath == null){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.created(newCurrentPath.toUri()).build();
    }
}

