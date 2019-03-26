package com.springcloud.ribbon.consumer.ribbonconsumer.controller;

import com.alibaba.fastjson.JSON;
import com.springcloud.ribbon.consumer.ribbonconsumer.entity.UserEntity;
import com.springcloud.ribbon.consumer.ribbonconsumer.service.impl.HelloServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import rx.Observable;
import rx.Subscriber;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;


@Controller
public class ConsumerController {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    HelloServiceImpl helloService;

    @RequestMapping(value = "/ribbon-consumer",method = RequestMethod.GET)
    @ResponseBody
    public String helloConsumer() throws ExecutionException, InterruptedException{
        /*UserEntity body = restTemplate.getForEntity("http://springone-service/user/info?name={1}",
                UserEntity.class,"帅哥").getBody();
        System.out.println(body);*/
        Future<String> a =helloService.findUserAsync(1L);
        while (true){
            if(a.isDone()){
                break;
            }
        }
        //在controller使用异步调用时，需要设置熔断器超时时间，否则会抛出TimeoutException: null异常
        return a.get();
    }

    @RequestMapping(value = "/ribbon-user",method = RequestMethod.GET)
    @ResponseBody
    public String helloUser(){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
        map.add("name", "first.last@example.com");
        map.add("id","123");
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);
        ResponseEntity response = restTemplate.postForEntity( "http://springone-service/user/user", request , UserEntity.class );
        return JSON.toJSONString(response.getBody());
    }

    @RequestMapping(value = "/observeUser",method = RequestMethod.GET)
    @ResponseBody
    public String observeUser(){
        //??观察 订阅者模式
        Observable<UserEntity> observable = helloService.findUserOber("帅哥");
        observable.subscribe(new Subscriber<UserEntity>() {
            public void onCompleted() {
                System.out.println("completed");
            }
            public void onError(Throwable throwable) {
                System.out.println("error-----------"+throwable);
            }
            public void onNext(UserEntity v) {
                System.out.println("next------------" + v.toString());
            }
        });
        return "";
    }

    @RequestMapping(value = "/hystrixException",method = RequestMethod.GET)
    @ResponseBody
    public String hystrixException(){
        return helloService.helloService("1");
    }
}
