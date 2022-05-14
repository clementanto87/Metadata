package com.school.management.dto;

import com.school.management.entity.Course;
import lombok.Data;

@Data
public class CourseDto {

    private Integer id;

    private String courseName;

    private Integer enrollmentCount;

    public CourseDto() { }

    public CourseDto(Course course) {
        this.id = course.getId();
        this.courseName = course.getCourseName();
        this.enrollmentCount = course.getEnrollmentCount();
    }
}
