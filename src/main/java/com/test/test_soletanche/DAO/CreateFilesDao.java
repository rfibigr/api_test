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

/**
 * Class that use to check file in a Directory, Upload file, and with the possibility to change the Directory
 *
 * Construct :          Create a List of Object FileDetail for each file and folder in the current Directory
 *                      Initialize with path = "/".
 *
 * Method UploadFile :  Take an MulitpartFile as argument and upload this file in the current Directory
 *                      Return null is file is empty.
 *
 * Method ChangePath :  Take a String with the new path in arguments and create a new list of FileDetail for each file
 *                      and Directory in the new path.
 *
 * Getter for folder list and currentPath
 */

@Repository("localDao")
public class CreateFilesDao implements FileDao {

    private static List<FilesDetail> folderList = new ArrayList<>();
    private static String curentPath;


    //Constructor
    public CreateFilesDao() {
        curentPath =  "/";
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
        if (!Files.exists(path)){
            return null;
        }
        curentPath = newPath;
        //TODO Don't clear the list but create an array of list with path as key
        folderList.clear();
        File folder = new File(newPath);
        File[] listOfFiles = folder.listFiles();
        assert listOfFiles != null;
        for (File file : listOfFiles) {
            folderList.add(new FilesDetail(file));
        }
        return path;
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

