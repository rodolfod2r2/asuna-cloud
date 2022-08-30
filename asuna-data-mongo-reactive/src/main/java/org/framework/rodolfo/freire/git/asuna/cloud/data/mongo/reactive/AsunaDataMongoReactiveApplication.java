package org.framework.rodolfo.freire.git.asuna.cloud.data.mongo.reactive;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class AsunaDataMongoReactiveApplication {

    public static void main(String[] args) {
        SpringApplication.run(AsunaDataMongoReactiveApplication.class, args);
    }

}
