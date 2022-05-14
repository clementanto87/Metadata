package com.school.management.service;



import com.school.management.entity.TestEntity;

import java.util.List;

public interface TestService {
    List<TestEntity> getAll();

    TestEntity save();
}
