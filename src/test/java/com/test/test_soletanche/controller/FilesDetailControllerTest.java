package com.test.test_soletanche.controller;

import com.test.test_soletanche.DAO.FileDao;
import com.test.test_soletanche.service.FilesService;
import org.apache.catalina.webresources.FileResource;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.net.HttpURLConnection;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FilesDetailControllerTest {

    private MockMvc mockMvc;

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Autowired
    private FileDao fileDao;

    @Autowired
    private FilesService filesService;


    @Test
    public void getAllFiles() {

    }

    @Test
    public void getFileById() {
    }

    @Test
    public void getAllDir() {
    }

    @Test
    public void downloadFile() {
    }

    @Test
    public void getCurrentPath() {
    }

    @Test
    public void uploadFile() {
    }

    @Test
    public void movePath() {
    }
}