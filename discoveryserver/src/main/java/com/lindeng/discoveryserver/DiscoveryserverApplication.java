package com.lindeng.discoveryserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication // 等价于 @Configuration、@EnableAutoConfiguration、@ComponentScan
@EnableEurekaServer // 启用 eureka server 相关默认配置
public class DiscoveryserverApplication {

    public static void main(String[] args) {
        SpringApplication.run(DiscoveryserverApplication.class, args);
    }

}
