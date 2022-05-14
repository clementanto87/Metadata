package com.school.management.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.school.management.dto.StudentDto;
import com.school.management.entity.Student;
import com.school.management.service.StudentService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestPropertySource(locations="classpath:test.properties")
@DisplayName("Student Controller Unit Test Case")
@AutoConfigureMockMvc
public class StudentControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    StudentService studentService;

    @Test
    public void getStudentsTest() throws Exception {
        List<Student> arrayList = new ArrayList<>();
        Page<Student> pagedResponse = new PageImpl<>(arrayList);
        Mockito.when(studentService.getStudents(Pageable.ofSize(10))).thenReturn(pagedResponse);
        mockMvc.perform( MockMvcRequestBuilders
                        .get("/students")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void saveStudentTest() throws Exception {
        StudentDto studentDto = new StudentDto();
        studentDto.setAge(21);
        Mockito.when(studentService.saveStudent(studentDto)).thenReturn(studentDto);
        mockMvc.perform(MockMvcRequestBuilders.post("/students")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(studentDto)))
                .andExpect(status().isOk());
    }

    @Test
    public void findStudentsByCourseTest() throws Exception {
        List<StudentDto> studentDtos = new ArrayList<>();
        Mockito.when(studentService.findStudentsByCourse(1)).thenReturn(studentDtos);
        mockMvc.perform( MockMvcRequestBuilders
                        .get("/students/courses/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void emptyStudentsTest() throws Exception {
        List<StudentDto> studentDtos = new ArrayList<>();
        Mockito.when(studentService.emptyStudents()).thenReturn(studentDtos);
        mockMvc.perform( MockMvcRequestBuilders
                        .get("/students/empty")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
