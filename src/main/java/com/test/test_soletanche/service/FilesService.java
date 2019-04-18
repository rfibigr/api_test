package com.test.test_soletanche.service;

import com.test.test_soletanche.DAO.FileDao;
import com.test.test_soletanche.model.FilesDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;


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

    public FilesDetail getFileByType(){
        return filesDetailsList.stream()
                .filter(filesDetail -> filesDetail.getDirectory().equals(Boolean.TRUE))
                .findAny().orElse(null);
    }


}
