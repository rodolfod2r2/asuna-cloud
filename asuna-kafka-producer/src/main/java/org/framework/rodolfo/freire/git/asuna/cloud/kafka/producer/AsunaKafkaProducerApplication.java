package org.framework.rodolfo.freire.git.asuna.cloud.kafka.producer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class AsunaKafkaProducerApplication {

    public static void main(String[] args) {
        SpringApplication.run(AsunaKafkaProducerApplication.class, args);
    }

}
