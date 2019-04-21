package com.apiFileManager.DAO;

import com.apiFileManager.model.FilesDetail;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.List;

public interface FileDao {

   List<FilesDetail> getFolderList();

   Path uploadFile(MultipartFile file);

   Path changePath(String newPath);

   Path getCurrentPath();

   //Delete file to list

}
