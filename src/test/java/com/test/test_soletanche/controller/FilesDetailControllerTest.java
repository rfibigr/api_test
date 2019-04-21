package com.test.test_soletanche.controller;

import com.test.test_soletanche.DAO.FileDao;
import com.test.test_soletanche.model.FilesDetail;
import com.test.test_soletanche.service.FilesService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class FilesDetailControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private FileDao fileDao;

    @Autowired
    private FilesService filesService;

    @Test
    public void getAllFiles() throws Exception{

        fileDao.changePath("src/test/TestDirectory");

        mvc.perform(MockMvcRequestBuilders
        .get("/file")
        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isNotEmpty());

    }

    @Test
    public void getFileById() throws Exception{

        fileDao.changePath("src/test/TestDirectory");
        FilesDetail firstFile = filesService.getAllFiles().get(0);
        int id = firstFile.getId();


        mvc.perform(MockMvcRequestBuilders
                .get("/file/" + id)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(id));

        mvc.perform(MockMvcRequestBuilders
                .get("/file/58779")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNoContent());
}

    @Test
    public void getAllDir() throws Exception {

        fileDao.changePath("src/test/TestDirectory");

        mvc.perform(MockMvcRequestBuilders
                .get("/file/isdir")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }


    @Test
    public void downloadFile() throws Exception{
        fileDao.changePath("src/test/TestDirectory");

        mvc.perform(MockMvcRequestBuilders
                .get("/download/0")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNoContent());

        mvc.perform(MockMvcRequestBuilders
                .get("/download/123412")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    public void getCurrentPath() throws Exception {
        fileDao.changePath("src/test/TestDirectory/");

        mvc.perform(MockMvcRequestBuilders
                .get("/path")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void uploadFile() throws Exception {
        fileDao.changePath("src/test/");

        MockMultipartFile mockFile = new MockMultipartFile("file", "test.jpeg",
                "img/jpeg", "Random string to simulate a picture".getBytes());
        mvc.perform(MockMvcRequestBuilders.fileUpload("/upload")
                .file(mockFile))
                .andExpect(status().isCreated());

        MockMultipartFile mockFile2 = new MockMultipartFile("file", "test1.jpeg",
            "img/jpeg", (byte[]) null);
        mvc.perform(MockMvcRequestBuilders.fileUpload("/upload")
                .file(mockFile2))
            .andExpect(status().isNoContent());

        mvc.perform(MockMvcRequestBuilders.fileUpload("/upload"))
                .andExpect(status().is4xxClientError());

}

    @Test
    public void movePath() throws Exception {
        fileDao.changePath("src/test/TestDirectory");

        mvc.perform(MockMvcRequestBuilders.post("/move")
                .accept(MediaType.TEXT_PLAIN).content("src/test/")
                .contentType(MediaType.TEXT_PLAIN))
                .andExpect(status().isCreated());

        mvc.perform(MockMvcRequestBuilders.post("/move")
                .accept(MediaType.TEXT_PLAIN).content(("src/test/123"))
                .contentType(MediaType.TEXT_PLAIN))
                .andExpect(status().isNoContent());

        mvc.perform(MockMvcRequestBuilders.post("/move")
                .accept(MediaType.TEXT_PLAIN).content("src/test/text.txt")
                .contentType(MediaType.TEXT_PLAIN))
                .andExpect(status().isNoContent());

        mvc.perform(MockMvcRequestBuilders.post("/move")
                .contentType(MediaType.ALL_VALUE))
                .andExpect(status().is4xxClientError());
    }
}

