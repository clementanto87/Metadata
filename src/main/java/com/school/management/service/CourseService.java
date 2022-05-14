package com.school.management.service;

import com.school.management.dto.CourseDto;
import com.school.management.entity.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CourseService {
    CourseDto saveCourse(CourseDto courseDto);

    Page<Course> getCourses(Pageable pageable);

    CourseDto updateCourse(CourseDto courseDto);

    void deleteCourseById(Integer id);

    CourseDto patchCourseName(Integer id, String name);

    List<CourseDto> findCoursesByStudent(Integer studentId);

    List<CourseDto> emptyCourses();
}
