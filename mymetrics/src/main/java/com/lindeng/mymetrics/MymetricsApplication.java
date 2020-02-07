package com.lindeng.mymetrics;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAdminServer
public class MymetricsApplication {

    public static void main(String[] args) {
        SpringApplication.run(MymetricsApplication.class, args);
    }

}
