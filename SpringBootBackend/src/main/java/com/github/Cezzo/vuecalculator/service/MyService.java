package com.github.Cezzo.vuecalculator.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.Cezzo.vuecalculator.model.MyEntity;
import com.github.Cezzo.vuecalculator.repository.MyEntityRepository;

@Service
public class MyService {

    @Autowired
    private MyEntityRepository myEntityRepository;

    public MyEntity saveMyEntity(MyEntity myEntity) {
        return myEntityRepository.save(myEntity);
    }

    // Other methods to interact with the database
}
