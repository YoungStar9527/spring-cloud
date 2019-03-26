package com.springone.springone.system.health;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

/**
 * 检测RocketMQ的测试类
 */
@Component
public class RocketMQHealthIndicator implements HealthIndicator {

    @Override
    public Health health() {
        int errorCode = check();
        if(errorCode != 0){
            return Health.down().withDetail("Erro Code",errorCode).build();
        }
        return Health.up().build();
    }

    /**
     * 检测操作(伪代码)
     * @return
     */
    private int check(){
        return 0;
    }
}
