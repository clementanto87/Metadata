package com.school.management.service;

import com.school.management.dto.StudentDto;

public interface EnrollService {
    StudentDto enrollStudent(Integer studentId, Integer courseId) throws Exception;
}
