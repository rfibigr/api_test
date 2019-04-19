package com.test.test_soletanche.controller;

import com.test.test_soletanche.DAO.FileDao;
import com.test.test_soletanche.service.FilesService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;



@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class FilesDetailControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext wac;

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

