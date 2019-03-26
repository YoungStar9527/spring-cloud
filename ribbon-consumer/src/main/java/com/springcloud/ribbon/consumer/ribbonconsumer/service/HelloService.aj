package com.springcloud.ribbon.consumer.ribbonconsumer.service;

public interface HelloService {

    String helloService();

    String helloFallback();
}
