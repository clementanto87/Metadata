package com.school.management.controller;

import com.school.management.config.ApplicationConstant;
import com.school.management.dto.CourseDto;
import com.school.management.dto.StudentDto;
import com.school.management.entity.Student;
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
@RequestMapping(ApplicationConstant.STUDENTS)
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping
    @Operation(summary = "Save a Student")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Student Saved",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Student.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid Input",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Resource Not Found",
                    content = @Content) })
    public StudentDto saveStudent(@RequestBody StudentDto studentDto) {
        return studentService.saveStudent(studentDto);
    }

    @GetMapping
    @Operation(summary = "Get all Student")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Student List",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Student[].class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid Input",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Resource Not Found",
                    content = @Content) })
    public Page<StudentDto> getStudents(@RequestParam(required = false, defaultValue = "0") Integer page,
                                        @RequestParam(required = false, defaultValue = "10") Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        return studentService.getStudents(pageable).map(StudentDto::new);
    }

    @PutMapping
    @Operation(summary = "Update Student")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Student Update",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Student.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid Input",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Resource Not Found",
                    content = @Content) })
    public StudentDto updateStudent(@RequestBody StudentDto studentDto) {
        return studentService.updateStudent(studentDto);
    }


    @DeleteMapping("/{id}")
    @Operation(summary = "Delete Student")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Student Deleted",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = void.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid Input",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Resource Not Found",
                    content = @Content) })
    public void deleteStudentById(@PathVariable Integer id) {
        studentService.deleteStudentById(id);
    }

    @PatchMapping("/{id}/{name}")
    @Operation(summary = "Update Student name by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Student Update",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Student.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid Input",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Resource Not Found",
                    content = @Content) })
    public StudentDto patchStudentName(@PathVariable Integer id, @PathVariable String name) {
        return studentService.patchStudentName(id, name);
    }

    @GetMapping("/courses/{courseId}")
    @Operation(summary = "Get Student list by course id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Students by course id",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Student[].class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid Input",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Resource Not Found",
                    content = @Content) })
    public List<StudentDto> findStudentsByCourse(@PathVariable Integer courseId) {
        return studentService.findStudentsByCourse(courseId);
    }

    @GetMapping("/empty")
    @Operation(summary = "Get Students with no course")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Student List",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Student[].class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid Input",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Resource Not Found",
                    content = @Content) })
    public List<StudentDto> emptyStudents() {
        return studentService.emptyStudents();
    }

}
