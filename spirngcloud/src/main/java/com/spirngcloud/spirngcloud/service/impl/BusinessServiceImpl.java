package com.spirngcloud.spirngcloud.service.impl;

import com.spirngcloud.spirngcloud.security.AopCheck;
import com.spirngcloud.spirngcloud.service.BusinessService;
import org.springframework.stereotype.Service;

@Service
public class BusinessServiceImpl implements BusinessService {

    @AopCheck
    @Override
    public void delete(int id) {
        System.out.println("删除啦啦啦");
    }
}
