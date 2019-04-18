package com.test.test_soletanche.service;

import com.test.test_soletanche.DAO.FileDao;
import com.test.test_soletanche.model.FilesDetail;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.*;

public class FilesServiceTest {

    FilesService filesService;

    @Test
    public void getAllFiles() {
        List<FilesDetail> fileList = filesService.getAllFiles();
        assertEquals(5, fileList.size());
        assertEquals("Empty", fileList.get(0).getFileName());
        assertEquals("image", fileList.get(2).getFileName());
    }

    @Test
    public void getFileById() {
        FilesDetail filesDetail0 = filesService.getFileById(0);
        assertEquals("Empty", filesDetail0.getFileName());
        FilesDetail filesDetail2 = filesService.getFileById(0);
        assertEquals("image", filesDetail2.getFileName());
    }

    @Test
    public void getFileByType() {
    }
}