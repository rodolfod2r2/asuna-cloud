package org.framework.rodolfo.freire.git.asuna.cloud.api.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class AsunaApiGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(AsunaApiGatewayApplication.class, args);
    }

}
