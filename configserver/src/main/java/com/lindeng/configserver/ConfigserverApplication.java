package com.lindeng.configserver;

import com.netflix.loadbalancer.BestAvailableRule;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RoundRobinRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@EnableConfigServer
//@EnableDiscoveryClient
@SpringBootApplication
public class ConfigserverApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConfigserverApplication.class, args);
    }

    @Bean
    //负载均衡注解
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    /**自定义配置ribbon负载均衡算法
     * @return
     */
    @Bean
    public IRule myRule() {
        //return new RoundRobinRule();//轮询
        //return new RetryRule();//重试
        return new RoundRobinRule();
    }
}
