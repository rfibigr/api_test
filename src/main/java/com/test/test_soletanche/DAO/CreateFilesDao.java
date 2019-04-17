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
import java.util.Scanner;

@Repository("localDao")
public class CreateFilesDao implements FileDao {

    private static List<FilesDetail> folderList = new ArrayList<>();
    private static String curentPath;

    //Constructor
    public CreateFilesDao() {
        File folder = (new File("/"));
        File[] listOfFiles = folder.listFiles();
        int i = 0;
        for (File file : listOfFiles){
            folderList.add(new FilesDetail(i, file));
            i++;
        }
    }

    //Getter
    @Override
    public List<FilesDetail> getFolderList() {
        return folderList;
    }

    @Override
    public FilesDetail uploadFile(MultipartFile file) {
        //creation de l'element
        if (file.isEmpty()){
            return null;
        }
        try {
            byte[] bytes = file.getBytes();
            Path path = Paths.get(curentPath + file.getOriginalFilename());
            Files.write(path, bytes);
            //maj du constructor CreateFileDao
        } catch (IOException e) {
            e.printStackTrace();
        }
        // TODO Instancier le nouveau fichier et le retourner

        return "Upload succes";
    }

    @Override
    public String changePath(String newPath) {
        curentPath = newPath;
        folderList.clear();
        File folder = new File(newPath);
        File[] listOfFiles = folder.listFiles();
        int i = 0;
        for (File file : listOfFiles){
            folderList.add(new FilesDetail(i, file));
            i++;
        }
        //TODO gestion des retours
        return null;

    }

}

