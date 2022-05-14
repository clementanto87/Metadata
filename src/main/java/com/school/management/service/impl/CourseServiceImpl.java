package com.school.management.service.impl;

import com.school.management.dto.CourseDto;
import com.school.management.entity.Course;
import com.school.management.repository.CourseRepository;
import com.school.management.repository.StudentRepository;
import com.school.management.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public CourseDto saveCourse(CourseDto courseDto) {
        Course course = new Course(courseDto);
        return new CourseDto(courseRepository.save(course));
    }

    @Override
    public Page<Course> getCourses(Pageable pageable) {
        return courseRepository.findAll(pageable);
    }

    @Override
    public CourseDto updateCourse(CourseDto courseDto) {
        return new CourseDto(courseRepository.save(new Course(courseDto)));
    }

    @Override
    public void deleteCourseById(Integer id) {
        courseRepository.deleteById(id);
    }

    @Override
    public CourseDto patchCourseName(Integer id, String name) {
        Optional<Course> byId = courseRepository.findById(id);
        if (byId.isPresent()) {
            Course course = byId.get();
            course.setCourseName(name);;
            return new CourseDto(courseRepository.save(course));
        }
        return new CourseDto();
    }

    @Override
    public List<CourseDto> findCoursesByStudent(Integer studentId) {
        return studentRepository.findById(studentId)
                .get()
                .getCourseList()
                .stream()
                .map(CourseDto::new)
                .collect(Collectors.toList());
    }

    @Override
    public List<CourseDto> emptyCourses() {
        return courseRepository.findByEnrollmentCountIsLessThanEqual(0)
                .stream()
                .map(CourseDto::new)
                .collect(Collectors.toList());
    }
}
