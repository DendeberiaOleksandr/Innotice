package org.innotice.discovery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class InnoticeDiscoveryServer {

    public static void main(String[] args) {
        SpringApplication.run(InnoticeDiscoveryServer.class, args);
    }

}
