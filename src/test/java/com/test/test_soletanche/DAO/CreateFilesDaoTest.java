package com.test.test_soletanche.DAO;

import com.test.test_soletanche.model.FilesDetail;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CreateFilesDaoTest {

    File resourcesDirectory = new File("src/test/resources");
    @Autowired
    FileDao fileDao;

    @Test
    public void changePath() {
        List<FilesDetail> fileList;
        Path newPath;
        Path result;

        newPath = fileDao.changePath("src/test/TestDirectory/");
        result = Paths.get("src/test/TestDirectory/");
        fileList = fileDao.getFolderList();
        assertEquals(result, newPath);
        assertEquals("test", fileList.get(0).getFileName());

        newPath = fileDao.changePath("src/test/TestDirectory/test/");
        result = Paths.get("src/test/TestDirectory/test/");
        fileList = fileDao.getFolderList();
        assertEquals(result, newPath);
        assertEquals("image1.jpeg", fileList.get(0).getFileName());

        newPath = fileDao.changePath("src/test/sdfsa");
        fileList = fileDao.getFolderList();
        assertNull(newPath);
        assertEquals(1, fileList.size());


    }

    @Test
    public void getCurrentPath() {
        Path currenPath;
        Path result;

        fileDao.changePath("src/test/TestDirectory/");
        currenPath = fileDao.getCurrentPath();
        result = Paths.get("src/test/TestDirectory/");
        assertEquals(result, currenPath);

        fileDao.changePath("src/test/TestDirectory/test");
        currenPath = fileDao.getCurrentPath();
        result = Paths.get("src/test/TestDirectory/test");
        assertEquals(result, currenPath);

        fileDao.changePath("src/test/sdfsa");
        currenPath = fileDao.getCurrentPath();
        assertEquals(result, currenPath);

    }
}