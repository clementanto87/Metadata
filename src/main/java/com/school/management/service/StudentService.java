package com.school.management.service;

import com.school.management.dto.StudentDto;
import com.school.management.entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface StudentService {
    StudentDto saveStudent(StudentDto studentDto);

    Page<Student> getStudents(Pageable pageable);

    StudentDto updateStudent(StudentDto studentDto);

    void deleteStudentById(Integer id);

    StudentDto patchStudentName(Integer id, String name);

    List<StudentDto> findStudentsByCourse(Integer courseId);

    List<StudentDto> emptyStudents();
}
