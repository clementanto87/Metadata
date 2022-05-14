package com.school.management.entity;

import com.school.management.dto.StudentDto;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "student")
@Data
public class Student {

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private Integer age;

    @Column(name = "department")
    private String department;

    @Column(name = "course_count")
    private Integer courseCount;

    @ManyToMany
    @JoinTable(name = "student_course", joinColumns =
    @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id"))
    private Set<Course> courseList;

    public Student() { }

    public Student(StudentDto studentDto) {
        this.id = studentDto.getId();
        this.name = studentDto.getName();
        this.age = studentDto.getAge();
        this.department = studentDto.getDepartment();
        this.courseCount = studentDto.getCourseCount();
        this.courseList = studentDto.getCourseList();
    }
}
