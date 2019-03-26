package com.spirngcloud.spirngcloud.security;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class SecurityAop {

    @Autowired
    private AuthUser authUser;

    @Pointcut("@annotation(AopCheck)")
    public void woca(){}

    @Before("woca()")
    public  void before(){
        authUser.checkUser();
    }
}
