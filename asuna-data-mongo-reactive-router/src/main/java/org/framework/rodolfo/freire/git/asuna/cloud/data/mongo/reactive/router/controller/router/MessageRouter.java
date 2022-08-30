package org.framework.rodolfo.freire.git.asuna.cloud.data.mongo.reactive.router.controller.router;

import org.framework.rodolfo.freire.git.asuna.cloud.data.mongo.reactive.router.controller.handler.MessageHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;

@Configuration
public class MessageRouter {


    @Bean
    public RouterFunction<ServerResponse> router(MessageHandler handler) {
        return RouterFunctions
                .route(GET("/message").and(accept(MediaType.APPLICATION_JSON)), handler::getAllMessage)
                .andRoute(GET("/message/{id}").and(accept(MediaType.APPLICATION_JSON)), handler::getMessageById)
                .andRoute(POST("/message").and(accept(MediaType.APPLICATION_JSON)), handler::saveMessage)
                .andRoute(PUT("/message").and(accept(MediaType.APPLICATION_JSON)), handler::updateMessage)
                .andRoute(DELETE("/message/{id}").and(accept(MediaType.APPLICATION_JSON)), handler::deleteMessage);


    }
}
