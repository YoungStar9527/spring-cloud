package com.springone.springone.controller;

import com.spring.service.api.springserviceapi.entity.UserEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Random;

@Controller
@RequestMapping("/hello")
public class HelloController {

    private final Logger logger = LoggerFactory.getLogger(HelloController.class);

    @Autowired
    private DiscoveryClient client;

    @RequestMapping("/hello")
    @ResponseBody
    public String hello(HttpServletRequest request) {
        int time = new Random().nextInt(3000);
        System.out.println(time);
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ServiceInstance instance = client.getLocalServiceInstance();
        logger.info("/hello,host:" + instance.getHost()
                + ",service_id:" + instance.getServiceId());
        return "Hello World";
    }

    @RequestMapping("/hello1")
    @ResponseBody
    public String hello1(@RequestParam String name) {
        return "Hello" + name;
    }

    @RequestMapping("/hello2")
    @ResponseBody
    public UserEntity hello2(@RequestHeader String name, @RequestHeader Integer age) {
        return new UserEntity(1L, name, age, 1);
    }

    @RequestMapping("/hello3")
    @ResponseBody
    public String hello3(@RequestBody UserEntity user) {
        return user.getName() + ":" + user.getAge();
    }
}
