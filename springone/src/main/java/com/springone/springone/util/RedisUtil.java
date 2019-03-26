package com.springone.springone.util;

import redis.clients.jedis.Jedis;

import java.util.Set;

public class RedisUtil {
    public static void getRedisClient(){
        Jedis jedis = new Jedis("192.168.31.128",6379);
        jedis.connect();
        Set<String> keys = jedis.keys("*");
        for (String key:keys) {
            System.out.println(key);
        }
    }

}
