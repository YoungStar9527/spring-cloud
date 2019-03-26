package com.springone.springone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class SpringoneApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringoneApplication.class, args);
    }

}
