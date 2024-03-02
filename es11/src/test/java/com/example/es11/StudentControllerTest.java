package com.example.es11;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles(value = "test")
public class StudentControllerTest {
    @Autowired
    private StudentController studentController;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void contextLoads() {
        assertThat(studentController).isNotNull();
    }
    private Student createStudent() throws Exception {
        Student student = new Student();
        student.setId(1L);
        student.setName("Pritvi");
        student.setSurname("Udhin");
        student.setIsWorking(false);

        String studentJson = objectMapper.writeValueAsString(student);

        MvcResult result = this.mockMvc.perform(post("/api/createStudent")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(studentJson))
                .andExpect(status().isOk())
                .andReturn();
        String studentJson1 = result.getResponse().getContentAsString();
        return objectMapper.readValue(studentJson1, Student.class);
    }

    private Student getStudentById(Long id) throws Exception {
        MvcResult result = this.mockMvc.perform(get("/getStudent/" + id))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        try {
            String studentJson = result.getResponse().getContentAsString();
            return objectMapper.readValue(studentJson, Student.class);
        } catch (Exception e) {
            return null;
        }
    }

    @Test
    void createStudentTest() throws Exception {
        Student student = createStudent();
        assertThat(student.getId()).isNotNull();
    }

    @Test
    void getStudentByIdTest() throws Exception {
        Student student = createStudent();
        assertThat(student.getId()).isNotNull();
        Student studentResponse = getStudentById(student.getId());
        assertThat(studentResponse.getId()).isEqualTo(student.getId());
    }

    @Test
    void deleteStudentTest() throws Exception {
        Student student = createStudent();
        assertThat(student.getId()).isNotNull();
        this.mockMvc.perform((delete("/deleteStudent/" + student.getId())))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        Student studentFromResponseGet = getStudentById(student.getId());
        assertThat(studentFromResponseGet).isNull();
    }

    @Test
    void updateWorkinkTest() throws Exception {
        Student student= createStudent();
        assertThat(student.getId()).isNotNull();
        MvcResult result = this.mockMvc.perform(patch("/changeIsWorking/" + student.getId() + "?workink=true"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        Student studentFromResponsePatch = objectMapper.readValue(result.getResponse().getContentAsString(), Student.class);
        assertThat(studentFromResponsePatch.getId()).isEqualTo(student.getId());
        assertThat(studentFromResponsePatch.getIsWorking()).isEqualTo(true);


    }
}
