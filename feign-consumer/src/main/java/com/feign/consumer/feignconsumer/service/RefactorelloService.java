package com.feign.consumer.feignconsumer.service;

import com.spring.service.api.springserviceapi.service.HelloService;
import org.springframework.cloud.netflix.feign.FeignClient;

@FeignClient("springone-service")
public interface RefactorelloService extends HelloService {

}
