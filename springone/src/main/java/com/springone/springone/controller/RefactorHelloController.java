package com.springone.springone.controller;

import com.spring.service.api.springserviceapi.entity.UserEntity;
import com.spring.service.api.springserviceapi.service.HelloService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RefactorHelloController implements HelloService {

    @Override
    public String hello() {
        return "Hello";
    }

    @Override
    public String hello(String name) {
        return "Hello"+name;
    }

    @Override
    public UserEntity hello(String name, Integer age) {
        return new UserEntity(1L, name, age, 1);
    }

    @Override
    public String hello(UserEntity userEntity) {
        return userEntity.getName() + ":" + userEntity.getAge();
    }
}
