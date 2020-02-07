package com.lindeng.cloudclient1.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@RestController
@RefreshScope
public class UserController {

    @Value("${locale.data.name}")
    private String name;
    @Value("${locale.data.age}")
    private int age;
    @Value("${locale.data.gender}")
    private String gender;
    @Value("${server.port}")
    private String port;

    @Autowired
    private DiscoveryClient discoveryClient;

    private static final Random RANDOM = new Random();

    @GetMapping("/users/{id}")
    @HystrixCommand(commandProperties = {
            // 熔断器在整个统计时间内是否开启的阀值
            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),
            //当出错率超过50%后熔断器启动
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "50"),
            // 熔断器工作时间，超过这个时间，先放一个请求进去，成功的话就关闭熔断，失败就再等一段时间
            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value="100")
    },
    fallbackMethod = "fallBack")
    public JsonNode getUser(@PathVariable String id) throws Exception {
        ObjectNode objectNode = JsonNodeFactory.instance.objectNode();
        objectNode.put("name", name);
        objectNode.put("age", age);
        objectNode.put("gender", gender);
        if(RANDOM.nextInt(10)%2==0){
            throw new Exception("error.");
        }
        //获取用户微服务实例
//        discoveryClient.getServices().forEach(s -> System.out.println("service: "+s));
//        Optional<String> serviceName = discoveryClient.getServices().stream().filter(s -> s.startsWith("client") || s.startsWith("client".toUpperCase())).findFirst();
//        if(serviceName.isPresent()) {
//            ServiceInstance serviceInstance = discoveryClient.getInstances(serviceName.get()).get(0);
//            //控制台输出微服务被调用时间、端口号等信息
//            String registerInfo = "time: " + new Timestamp(System.currentTimeMillis()) + ", serverId: " + serviceInstance.getServiceId() + ", host:" +
//                    " " + serviceInstance.getHost()
//                    + ", port: " + serviceInstance.getPort();
//            objectNode.put("registerInfo", registerInfo);
//        } else {
//            objectNode.put("port", port);
//        }
        objectNode.put("port", port);
        TimeUnit.MILLISECONDS.sleep(RANDOM.nextInt(200));
        return objectNode;
    }

    /**
     * 熔断策略的回调方法
     * @return
     */
    public JsonNode fallBack(String id) {
         ObjectNode objectNode = JsonNodeFactory.instance.objectNode();
         objectNode.put("HystrixCommand", "service is invalid.");
         return objectNode;
    }
}
