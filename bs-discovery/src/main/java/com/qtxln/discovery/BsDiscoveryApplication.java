package com.qtxln.discovery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class BsDiscoveryApplication {

    public static void main(String[] args) {
        SpringApplication.run(BsDiscoveryApplication.class, args);
    }
}
