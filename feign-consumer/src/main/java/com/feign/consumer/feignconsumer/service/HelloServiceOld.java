package com.feign.consumer.feignconsumer.service;

import com.feign.consumer.feignconsumer.entity.UserEntity;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient("springone-service")
public interface HelloServiceOld {

    @RequestMapping("/hello/hello")
    String hello();

    @RequestMapping(value = "/hello/hello1",method = RequestMethod.GET)
    String hello(@RequestParam("name")String name);

    @RequestMapping(value = "/hello/hello2",method = RequestMethod.GET)
    UserEntity hello(@RequestHeader("name")String name,@RequestHeader("age")Integer age);

    @RequestMapping(value = "/hello/hello3",method = RequestMethod.POST)
    String hello(@RequestBody UserEntity userEntity);
}
