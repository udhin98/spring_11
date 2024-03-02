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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles(value = "test")
public class StudentServiceTest {
	@Autowired
	private StudentService studentService;
	@Autowired StudentRepository studentRepository;
	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private ObjectMapper objectMapper;
	@Test
	void studentIsWorking() throws Exception{
		Student studentEntity = new Student();
		studentEntity.setId(1L);
		studentEntity.setName("Pippo");
		studentEntity.setSurname("Rossi");
		studentEntity.setIsWorking(true);

		Student studentFromDB = studentRepository.save(studentEntity);
		assertThat(studentFromDB).isNotNull();
		assertThat(studentFromDB.getId()).isNotNull();

		Student studentFromService = studentService.changeIsWorking(studentFromDB.getId(), false);
		assertThat(studentFromService).isNotNull();
		assertThat(studentFromService.getId()).isNotNull();

	}
}