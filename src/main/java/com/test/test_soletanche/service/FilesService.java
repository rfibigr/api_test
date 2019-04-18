package com.test.test_soletanche.service;

import com.test.test_soletanche.DAO.FileDao;
import com.test.test_soletanche.model.FilesDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


/**
 * Logic operation
 *
 * Method getAllFiles : return a List of Object FileDetail for each file and folder in the current Directory
 *
 * Method getFileByid : take an id as argument and return a FileDetail of the specific file.
 *
 * Method getFileBytype : return a list of all the Directory
 */

@Service
public class FilesService {

    private final FileDao fileDao;
    private final List<FilesDetail> filesDetailsList;

    @Autowired
    public FilesService(@Qualifier("localDao") FileDao fileDao){
        this.fileDao = fileDao;
        this.filesDetailsList = fileDao.getFolderList();
    }

    public List<FilesDetail> getAllFiles(){
        return filesDetailsList;
    }

    public FilesDetail getFileById(int id){
        return filesDetailsList.stream()
                .filter(filesDetail -> filesDetail.getId() == id)
                .findFirst().orElse(null);
    }

    public List<FilesDetail> getFileByType(){
        return filesDetailsList.stream()
                .filter(filesDetail -> filesDetail.getDirectory().equals(Boolean.TRUE))
                .collect(Collectors.toList());
    }


}
