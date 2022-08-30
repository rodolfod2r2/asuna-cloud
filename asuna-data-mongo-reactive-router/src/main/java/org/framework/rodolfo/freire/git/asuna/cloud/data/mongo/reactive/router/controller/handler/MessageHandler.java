package org.framework.rodolfo.freire.git.asuna.cloud.data.mongo.reactive.router.controller.handler;

import org.apache.commons.lang3.ObjectUtils;
import org.framework.rodolfo.freire.git.asuna.cloud.data.mongo.reactive.router.controller.exceptions.ForbiddenResourceOverrideException;
import org.framework.rodolfo.freire.git.asuna.cloud.data.mongo.reactive.router.controller.exceptions.InvalidRequestBodyException;
import org.framework.rodolfo.freire.git.asuna.cloud.data.mongo.reactive.router.controller.exceptions.NotFoundResourceException;
import org.framework.rodolfo.freire.git.asuna.cloud.data.mongo.reactive.router.controller.response.Responses;
import org.framework.rodolfo.freire.git.asuna.cloud.data.mongo.reactive.router.model.Message;
import org.framework.rodolfo.freire.git.asuna.cloud.data.mongo.reactive.router.service.MessageService;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import javax.validation.constraints.NotNull;

import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Component
public class MessageHandler {

    private final MessageService service;

    public MessageHandler(MessageService service) {
        this.service = service;
    }

    public Mono<ServerResponse> getAllMessage(ServerRequest request) {
        return request
                .bodyToMono(Message.class)
                .flatMap(message -> ok().body(service.findAll(), Message.class))
                .onErrorResume(NotFoundResourceException.class, Responses::notFound)
                .onErrorResume(ForbiddenResourceOverrideException.class, Responses::forbidden)
                .onErrorResume(InvalidRequestBodyException.class, Responses::badRequest)
                .log("GET_ALL");
    }

    public Mono<ServerResponse> getMessageById(ServerRequest request) {
        return request
                .bodyToMono(Message.class)
                .flatMap(message -> ok().body(service.findById(request.pathVariable("id")), Message.class))
                .onErrorResume(NotFoundResourceException.class, Responses::notFound)
                .onErrorResume(ForbiddenResourceOverrideException.class, Responses::forbidden)
                .onErrorResume(InvalidRequestBodyException.class, Responses::badRequest)
                .log("GET_BY_ID");
    }

    public Mono<ServerResponse> saveMessage(@NotNull ServerRequest request) {
        return request
                .bodyToMono(Message.class)
                .flatMap(service::save)
                .flatMap((success) -> ObjectUtils.isNotEmpty(success) ? Responses.noContent() : Responses.internalServerError())
                .onErrorResume(NotFoundResourceException.class, Responses::notFound)
                .onErrorResume(ForbiddenResourceOverrideException.class, Responses::forbidden)
                .onErrorResume(InvalidRequestBodyException.class, Responses::badRequest)
                .log("SAVE");
    }

    public Mono<ServerResponse> updateMessage(ServerRequest request) {
        return request
                .bodyToMono(Message.class)
                .flatMap(service::update)
                .flatMap((success) -> ObjectUtils.isNotEmpty(success) ? Responses.noContent() : Responses.internalServerError())
                .onErrorResume(NotFoundResourceException.class, Responses::notFound)
                .onErrorResume(ForbiddenResourceOverrideException.class, Responses::forbidden)
                .onErrorResume(InvalidRequestBodyException.class, Responses::badRequest)
                .log("UPDATE");
    }

    public Mono<ServerResponse> deleteMessage(ServerRequest request) {
        return request
                .bodyToMono(Message.class)
                .flatMap(message -> {
                    service.delete(request.pathVariable("id"));
                    return Mono.just(message);
                })
                .flatMap((success) -> ObjectUtils.isNotEmpty(success) ? Responses.noContent() : Responses.internalServerError())
                .onErrorResume(NotFoundResourceException.class, Responses::notFound)
                .onErrorResume(ForbiddenResourceOverrideException.class, Responses::forbidden)
                .onErrorResume(InvalidRequestBodyException.class, Responses::badRequest)
                .log("DELETE");
    }

}
