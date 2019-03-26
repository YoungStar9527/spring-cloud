package com.spring.service.api.springserviceapi.service;

import com.spring.service.api.springserviceapi.entity.UserEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/refactor")
public interface HelloService {

    @RequestMapping("/hello/hello4")
    String hello();

    @RequestMapping(value = "/hello/hello5",method = RequestMethod.GET)
    String hello(@RequestParam("name") String name);

    @RequestMapping(value = "/hello/hello6",method = RequestMethod.GET)
    UserEntity hello(@RequestHeader("name") String name, @RequestHeader("age") Integer age);

    @RequestMapping(value = "/hello/hello7",method = RequestMethod.POST)
    String hello(@RequestBody UserEntity userEntity);
}
