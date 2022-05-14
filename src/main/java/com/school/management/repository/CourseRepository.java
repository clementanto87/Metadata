package com.school.management.repository;

import com.school.management.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Integer> {
    List<Course> findByEnrollmentCountIsLessThanEqual(Integer enrollmentCount);
}
