package org.framework.rodolfo.freire.git.asuna.cloud.discovery.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class AsunaDiscoveryServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(AsunaDiscoveryServerApplication.class, args);
    }

}
