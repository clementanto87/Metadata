package com.school.management.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "test")
@Data
public class TestEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;
}
