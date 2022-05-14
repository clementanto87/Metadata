package com.school.management.controller;

import com.school.management.dto.StudentDto;
import com.school.management.service.EnrollService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestPropertySource(locations="classpath:test.properties")
@DisplayName("Enroll Controller Unit Test Case")
@AutoConfigureMockMvc
public class EnrollControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    EnrollService enrollService;

    @Test
    public void enrollStudentTest() throws Exception {
        StudentDto studentDto = new StudentDto();
        Mockito.when(enrollService.enrollStudent(1, 1)).thenReturn(studentDto);

        mockMvc.perform( MockMvcRequestBuilders
                        .put("/enroll/1/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
