package org.framework.rodolfo.freire.git.asuna.cloud.asuna.admin.server;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
//@EnableEurekaClient
@EnableAdminServer
public class AsunaAdminServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(AsunaAdminServerApplication.class, args);
    }

}
