package com.school.management.controller;

import com.school.management.entity.TestEntity;
import com.school.management.service.TestService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
//@RequestMapping
public class TestController {

    @Autowired
    private TestService testService;

    @GetMapping
    @Operation(summary = "Save a employee with initial status")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Employee Saved",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = TestEntity.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid Input",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Resource Not Found",
                    content = @Content) })
    public List<TestEntity> getAll() {
        return testService.getAll();
    }


    @GetMapping("/save")
    public TestEntity save() {
        return testService.save();
    }

}
