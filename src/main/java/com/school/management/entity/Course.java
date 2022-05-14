package com.school.management.entity;

import com.school.management.dto.CourseDto;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "course")
@Data
public class Course {

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "course_name")
    private String courseName;

    @Column(name = "enrollment_count")
    private Integer enrollmentCount;

    public Course() {}

    public Course(CourseDto courseDto) {
        this.id = courseDto.getId();
        this.courseName = courseDto.getCourseName();
        this.enrollmentCount = courseDto.getEnrollmentCount();
    }

}
