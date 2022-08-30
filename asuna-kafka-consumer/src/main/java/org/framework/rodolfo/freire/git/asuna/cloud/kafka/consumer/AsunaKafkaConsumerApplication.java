package org.framework.rodolfo.freire.git.asuna.cloud.kafka.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class AsunaKafkaConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(AsunaKafkaConsumerApplication.class, args);
    }

}
