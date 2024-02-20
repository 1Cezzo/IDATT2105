package com.github.Cezzo.vuecalculator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.github.Cezzo.vuecalculator.model.MyEntity;
import com.github.Cezzo.vuecalculator.service.MyService;

@RestController
public class MyController {

    private final MyService myService;

    @Autowired
    public MyController(MyService myService) {
        this.myService = myService;
    }

    @PostMapping("/entities")
    public ResponseEntity<MyEntity> createEntity(@RequestBody MyEntity myEntity) {
        MyEntity savedEntity = myService.saveMyEntity(myEntity);
        return new ResponseEntity<>(savedEntity, HttpStatus.CREATED);
    }
}
