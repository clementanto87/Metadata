package com.school.management.service.impl;

import com.school.management.dto.StudentDto;
import com.school.management.entity.Course;
import com.school.management.entity.Student;
import com.school.management.exception.CourseUnavailable;
import com.school.management.exception.EnrollmentNotPossible;
import com.school.management.repository.CourseRepository;
import com.school.management.repository.StudentRepository;
import com.school.management.service.EnrollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;

@Service
public class EnrollServiceImpl implements EnrollService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Override
    @Transactional
    public StudentDto enrollStudent(Integer studentId, Integer courseId) throws Exception {
        pickACourse(courseId);
        addCourseToStudent(studentId, courseId);
        return new StudentDto(studentRepository.findById(studentId).get());
    }

    private void pickACourse(Integer courseId) throws CourseUnavailable {
        Optional<Course> byId = courseRepository.findById(courseId);
        if (byId.isPresent()) {
            Course course = byId.get();
            if (course.getEnrollmentCount() < 50) {
                course.setEnrollmentCount(course.getEnrollmentCount() + 1);
                courseRepository.save(course);
            } else {
                throw new CourseUnavailable("Course not available for enrollment");
            }
        }
    }

    private void addCourseToStudent(Integer studentId, Integer courseId) throws EnrollmentNotPossible {
        Optional<Student> studentById = studentRepository.findById(studentId);
        Optional<Course> courseById = courseRepository.findById(courseId);
        if (studentById.isPresent()) {
            Student student = studentById.get();
            if (student.getCourseCount() >= 5) {
                throw new EnrollmentNotPossible("Cant pick more than 5 courses");
            } else {
                Set<Course> courseList = student.getCourseList();
                if (courseList.stream().anyMatch(course -> course.getId() == courseId)) {
                    throw new EnrollmentNotPossible("Course already enrolled");
                }
                courseList.add(courseById.get());
                student.setCourseList(courseList);
                student.setCourseCount(student.getCourseCount() + 1);
                studentRepository.save(student);
            }
        }
    }
}
