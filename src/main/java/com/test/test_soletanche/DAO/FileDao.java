package com.test.test_soletanche.DAO;

import com.test.test_soletanche.model.FilesDetail;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileDao {

    //upload file


   List<FilesDetail> getFolderList();

   FilesDetail uploadFile(MultipartFile file);

   String changePath(String newPath);

    //Delete file to list



}
