package com.springcloud.ribbon.consumer.ribbonconsumer.service.impl;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.ObservableExecutionMode;
import com.netflix.hystrix.contrib.javanica.command.AsyncResult;
import com.springcloud.ribbon.consumer.ribbonconsumer.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;
import rx.Observable;

import java.util.concurrent.Future;

@Service
public class HelloServiceImpl{

    @Autowired
    private RestTemplate restTemplate;


    @HystrixCommand(fallbackMethod = "helloFallback")
    public String helloService(String id) {
        Long startTime = System.currentTimeMillis();
        String result = restTemplate.getForEntity("http://springone-service/hello/hello",
                String.class, "帅哥").getBody();
        Long endTime = System.currentTimeMillis();
        System.out.println("stop time:"+(startTime-endTime));
        throw new RuntimeException("myException");
        //return result;
    }

    @HystrixCommand(ignoreExceptions = Exception.class)
    public Future<String> findUserAsync(@PathVariable("id") Long id){
        return new AsyncResult<String>(){
            @Override
            public String invoke() {
                return restTemplate.getForEntity("http://springone-service/hello/hello",
                        String.class, "帅哥").getBody();
            }
        };
    }

    @HystrixCommand(observableExecutionMode = ObservableExecutionMode.EAGER)
    public Observable<UserEntity> findUserOber(final String name){
        return Observable.create(observer->{
            try {
                if(!observer.isUnsubscribed()){
                    UserEntity userEntity = restTemplate.getForEntity("http://springone-service/user/info",UserEntity.class,name).getBody();
                    observer.onNext(userEntity);
                    observer.onCompleted();
                }
            }catch(Exception e){
                observer.onError(e);
            }
        });
    }

    public String helloFallback(String id,Throwable e) {
        //?? assert 使用
        assert "myException".equals(e.getMessage());
        return "error";
    }

}
