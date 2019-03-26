package com.springcloud.ribbon.consumer.ribbonconsumer.configuration;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.client.loadbalancer.LoadBalancerAutoConfiguration;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RibbonConfiguration extends LoadBalancerAutoConfiguration {

}
