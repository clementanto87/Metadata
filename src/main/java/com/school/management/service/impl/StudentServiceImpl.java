package com.school.management.service.impl;

import com.school.management.dto.StudentDto;
import com.school.management.entity.Student;
import com.school.management.repository.StudentRepository;
import com.school.management.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public StudentDto saveStudent(StudentDto studentDto) {
        return new StudentDto(studentRepository.save(new Student((studentDto))));
    }

    @Override
    public Page<Student> getStudents(Pageable pageable) {
        return studentRepository.findAll(pageable);
    }

    @Override
    public StudentDto updateStudent(StudentDto studentDto) {
        return new StudentDto(studentRepository.save(new Student(studentDto)));
    }

    @Override
    public void deleteStudentById(Integer id) {
        studentRepository.deleteById(id);
    }

    @Override
    public StudentDto patchStudentName(Integer id, String name) {
        Optional<Student> byId = studentRepository.findById(id);
        if (byId.isPresent()) {
            Student student = byId.get();
            student.setName(name);
            return new StudentDto(studentRepository.save(student));
        }
        return new StudentDto();
    }

    @Override
    public List<StudentDto> findStudentsByCourse(Integer courseId) {
        return studentRepository.findByCourseList_Id(courseId)
                .stream()
                .map(StudentDto::new)
                .collect(Collectors.toList());
    }

    @Override
    public List<StudentDto> emptyStudents() {
        return studentRepository.findByCourseCountIsLessThanEqual(0)
                .stream()
                .map(StudentDto::new)
                .collect(Collectors.toList());
    }
}
