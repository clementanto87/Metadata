package com.school.management.repository;

import com.school.management.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Integer> {
    List<Student> findByCourseList_Id(Integer id);

    List<Student> findByCourseCountIsLessThanEqual(Integer courseCount);
}
