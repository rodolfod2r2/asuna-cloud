package org.framework.rodolfo.freire.git.asuna.cloud.config.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableConfigServer
@EnableEurekaClient
@SpringBootApplication
public class AsunaConfigServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(AsunaConfigServerApplication.class, args);
    }

}
