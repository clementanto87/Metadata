package com.school.management.dto;


import com.school.management.entity.TestEntity;
import lombok.Data;

@Data
public class TestDto {

    private Integer id;

    private String name;

    public TestDto(TestEntity entity) {
        this.id = entity.getId();
        this.name = entity.getName();
    }
}
