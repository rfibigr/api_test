package com.test.test_soletanche.service;

import com.test.test_soletanche.DAO.FileDao;
import com.test.test_soletanche.model.FilesDetail;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.nio.file.Files;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


@RunWith(SpringRunner.class)
@SpringBootTest
public class FilesServiceTest {


    @Autowired
    private FileDao fileDao;

    @Autowired
    private FilesService filesService;

    //Add before to set path

    @Test
    public void getAllFiles() {
        fileDao.changePath("src/test/TestDirectory");

        List<FilesDetail> fileList = filesService.getAllFiles();
        assertEquals(3, fileList.size());
        assertEquals("test", fileList.get(0).getFileName());
        assertEquals("image.jpeg", fileList.get(2).getFileName());
        assertEquals("text.txt", fileList.get(1).getFileName());
    }

    @Test
    public void getSortedFiles() {
        fileDao.changePath("src/test/TestDirectory");

        List<FilesDetail> fileList = filesService.getSortedFiles();
        assertEquals(3, fileList.size());
        assertEquals("image.jpeg", fileList.get(0).getFileName());
        assertEquals("test", fileList.get(1).getFileName());
        assertEquals("text.txt", fileList.get(2).getFileName());
    }

    @Test
    public void getFileByType() {
        List<FilesDetail> dirList;

        fileDao.changePath("src/test/TestDirectory");
        dirList = filesService.getFileByType();
        assertEquals(1, dirList.size());
        assertEquals("test", dirList.get(0).getFileName());

        fileDao.changePath("src/test/TestDirectory/test");
        dirList = filesService.getFileByType();
        assertTrue(dirList.isEmpty());
    }

    @Test
    public void getFileById() {
        fileDao.changePath("src/test/TestDirectory");

        List<FilesDetail> fileList = filesService.getAllFiles();
        int id = fileList.get(0).getId();

        FilesDetail filesDetail2 = filesService.getFileById(id);
        assertEquals("test", filesDetail2.getFileName());
        FilesDetail filesDetail1 = filesService.getFileById(id + 1);
        assertEquals("text.txt", filesDetail1.getFileName());
        FilesDetail filesDetail3 = filesService.getFileById(id + 2);
        assertEquals("image.jpeg", filesDetail3.getFileName());
    }
}

