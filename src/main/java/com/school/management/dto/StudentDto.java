package com.school.management.dto;

import com.school.management.entity.Course;
import com.school.management.entity.Student;
import lombok.Data;

import java.util.Set;

@Data
public class StudentDto {

    private Integer id;

    private String name;

    private Integer age;

    private String department;

    private Integer courseCount;

    private Set<Course> courseList;

    public StudentDto() { }

    public StudentDto(Student student) {
        this.id = student.getId();
        this.name = student.getName();
        this.age = student.getAge();
        this.department = student.getDepartment();
        this.courseCount = student.getCourseCount();
        this.courseList = student.getCourseList();
    }
}
