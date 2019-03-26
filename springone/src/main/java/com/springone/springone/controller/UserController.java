package com.springone.springone.controller;

import com.alibaba.fastjson.JSON;
import com.spring.service.api.springserviceapi.entity.UserEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/user")
public class UserController {

    @RequestMapping("/info")
    @ResponseBody
    public String getUser(String name) {
        UserEntity user = new UserEntity();
        user.setName(name);
        user.setId(1L);
        user.setSex(0);
        user.setAge(18);
        return JSON.toJSONString(user);
    }

    @RequestMapping(value = "/user",method = RequestMethod.POST)
    @ResponseBody
    public String getUser(UserEntity user) {
        return JSON.toJSONString(user);
    }
}