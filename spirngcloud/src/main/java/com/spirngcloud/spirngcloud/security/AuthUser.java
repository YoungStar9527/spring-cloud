package com.spirngcloud.spirngcloud.security;

import org.springframework.stereotype.Component;

@Component
public class AuthUser {

    public void checkUser(){
        if(!Permission.getUser().equals("admin")){
            throw new RuntimeException("Not allow operation");
        }
    }
}
