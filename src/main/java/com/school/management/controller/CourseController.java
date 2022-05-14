package com.school.management.controller;

import com.school.management.config.ApplicationConstant;
import com.school.management.dto.CourseDto;
import com.school.management.dto.StudentDto;
import com.school.management.entity.Course;
import com.school.management.entity.Student;
import com.school.management.service.CourseService;
import com.school.management.service.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApplicationConstant.COURSES)
public class CourseController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private StudentService studentService;

    @PostMapping
    @Operation(summary = "Save a course")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Course Saved",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Course.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid Input",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Resource Not Found",
                    content = @Content) })
    public CourseDto saveCourse(@RequestBody CourseDto courseDto) {
        return courseService.saveCourse(courseDto);
    }

    @GetMapping
    @Operation(summary = "Get all courses")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Courses List",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Course[].class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid Input",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Resource Not Found",
                    content = @Content) })
    public Page<CourseDto> getCourses(@RequestParam(required = false, defaultValue = "0") Integer page,
                                      @RequestParam(required = false, defaultValue = "10") Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        return courseService.getCourses(pageable).map(CourseDto::new);
    }

    @PutMapping
    @Operation(summary = "Update course")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Courses Update",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Course[].class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid Input",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Resource Not Found",
                    content = @Content) })
    public CourseDto updateCourse(@RequestBody CourseDto courseDto) {
        return courseService.updateCourse(courseDto);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete course")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Course Delete",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = void.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid Input",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Resource Not Found",
                    content = @Content) })
    public void deleteCourseById(@PathVariable Integer id) {
        courseService.deleteCourseById(id);
    }


    @PatchMapping("/{id}/{name}")
    @Operation(summary = "Update Course name by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Course Update",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Course.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid Input",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Resource Not Found",
                    content = @Content) })
    public CourseDto patchCourseName(@PathVariable Integer id, @PathVariable String name) {
        return courseService.patchCourseName(id, name);
    }

    @GetMapping("/students/{studentId}")
    @Operation(summary = "Get Course list by student id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Course List by student id",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Course[].class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid Input",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Resource Not Found",
                    content = @Content) })
    public List<CourseDto> findCoursesByStudent(@PathVariable Integer studentId) {
        return courseService.findCoursesByStudent(studentId);
    }

    @GetMapping("/empty")
    @Operation(summary = "Get Course list with no enrollment")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Course List",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Course[].class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid Input",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Resource Not Found",
                    content = @Content) })
    public List<CourseDto> emptyCourses() {
        return courseService.emptyCourses();
    }

}
