package org.framework.rodolfo.freire.git.asuna.cloud.data.mongo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class AsunaDataMongoApplication {

    public static void main(String[] args) {
        SpringApplication.run(AsunaDataMongoApplication.class, args);
    }

}
