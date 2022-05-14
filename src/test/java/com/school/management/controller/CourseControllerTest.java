package com.school.management.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.school.management.dto.CourseDto;
import com.school.management.entity.Course;
import com.school.management.repository.CourseRepository;
import com.school.management.service.CourseService;
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
@DisplayName("Course Controller Unit Test Case")
@AutoConfigureMockMvc
public class CourseControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    CourseService courseService;

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    public void getCoursesTest() throws Exception {
        List<Course> courseList = new ArrayList<>();
        Page<Course> pagedResponse = new PageImpl<>(courseList);
        Mockito.when(courseService.getCourses(Pageable.ofSize(10))).thenReturn(pagedResponse);
        mockMvc.perform( MockMvcRequestBuilders
                        .get("/courses")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void saveCourseTest() throws Exception {
        Course course = new Course();
        course.setCourseName("test name");
        Mockito.when(courseService.saveCourse(new CourseDto(course))).thenReturn(new CourseDto(course));
        mockMvc.perform(MockMvcRequestBuilders.post("/courses")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(new CourseDto(course))))
                .andExpect(status().isOk());
    }

    @Test
    public void findCoursesByStudentTest() throws Exception {
        List<CourseDto> courseDtos = new ArrayList<>();
        Mockito.when(courseService.findCoursesByStudent(1)).thenReturn(courseDtos);
        mockMvc.perform( MockMvcRequestBuilders
                        .get("/courses/students/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
