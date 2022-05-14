package com.school.management.controller;

import com.school.management.dto.StudentDto;
import com.school.management.service.EnrollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EnrollController {

    @Autowired
    private EnrollService enrollService;

    @PutMapping("/enroll/{studentId}/{courseId}")
    public StudentDto enrollStudent(@PathVariable Integer studentId,
                                    @PathVariable Integer courseId) throws Exception {
        return enrollService.enrollStudent(studentId, courseId);
    }
}
