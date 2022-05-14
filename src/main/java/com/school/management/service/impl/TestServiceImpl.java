package com.school.management.service.impl;


import com.school.management.entity.TestEntity;
import com.school.management.repository.TestRepository;
import com.school.management.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestServiceImpl implements TestService {

    @Autowired
    private TestRepository testRepository;

    @Override
    public List<TestEntity> getAll() {
        return testRepository.findAll();
    }

    @Override
    public TestEntity save() {
        TestEntity testEntity = new TestEntity();
        return testRepository.save(testEntity);
    }
}
