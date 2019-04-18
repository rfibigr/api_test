package com.test.test_soletanche.DAO;

import com.test.test_soletanche.model.FilesDetail;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Repository("localDao")
public class CreateFilesDao implements FileDao {

    private static List<FilesDetail> folderList = new ArrayList<>();
    private static String curentPath;

    //Constructor
    public CreateFilesDao() {
        curentPath =  "/Users/rfibigr/Documents/";
        File folder = (new File(curentPath));
        File[] listOfFiles = folder.listFiles();
        if (listOfFiles != null) {
            for (File file : listOfFiles) {
                folderList.add(new FilesDetail(file));
            }
        }
    }


    @Override
    public Path uploadFile(MultipartFile file) {
        //creation de l'element
        Path uploaded = null;

        if (file.isEmpty()){
            return null;
        }
        try {
            byte[] bytes = file.getBytes();
            Path path = Paths.get(curentPath + file.getOriginalFilename());
            System.out.println(path);
            uploaded = Files.write(path, bytes);
            //Convert MultipartFile to File
        } catch (IOException e) {
            e.printStackTrace();
        }
        return uploaded;
    }


    @Override
    public Path changePath(String newPath) {
        Path path = Paths.get(newPath);
        curentPath = newPath;
        //TODO Don't clear the list but see to create an array of list with path as key
        folderList.clear();
        File folder = new File(newPath);
        File[] listOfFiles = folder.listFiles();
        if (listOfFiles != null) {
            for (File file : listOfFiles) {
                folderList.add(new FilesDetail(file));
            }
            return path;
        }
        return null;

    }

    @Override
    public List<FilesDetail> getFolderList() {
        return folderList;
    }

    @Override
    public Path getCurrentPath() {
        return Paths.get(curentPath);
    }
}

