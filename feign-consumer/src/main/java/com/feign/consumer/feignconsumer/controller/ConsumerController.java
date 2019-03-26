package com.feign.consumer.feignconsumer.controller;

import com.feign.consumer.feignconsumer.entity.UserEntity;
import com.feign.consumer.feignconsumer.service.HelloServiceOld;
import com.feign.consumer.feignconsumer.service.RefactorelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ConsumerController {
    /**
     * feign默认使用ribbon和hystrix(待验证)
     */
    @Autowired
    private HelloServiceOld helloServiceOld;

    @Autowired
    private RefactorelloService refactorelloService;


    @RequestMapping("/feignConsumer")
    @ResponseBody
    public String helloConsumer(){
        return helloServiceOld.hello();
    }

    @RequestMapping("/feignConsumer2")
    @ResponseBody
    public String helloConsumer2(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(helloServiceOld.hello()).append("\n")
                .append(helloServiceOld.hello("帅哥")).append("\n")
                .append(helloServiceOld.hello("帅哥")).append("\n")
                .append(helloServiceOld.hello(new UserEntity(1l,"帅哥",18,1).toString()));
        return stringBuilder.toString();
    }

    @RequestMapping("/feignConsumer3")
    @ResponseBody
    public String helloConsumer3() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(refactorelloService.hello()).append("\n")
                .append(refactorelloService.hello("帅哥")).append("\n")
                .append(refactorelloService.hello("帅哥")).append("\n")
                .append(refactorelloService.hello(new UserEntity(1l,"帅哥",18,1).toString()));
        return stringBuilder.toString();
    }
}
