package org.framework.rodolfo.freire.git.asuna.cloud.data.mongo.reactive.router.controller.exceptions;

public class InvalidRequestBodyException extends BadRequestException {

    public InvalidRequestBodyException(Class<?> expectedClass) {
        super("The provided request body is invalid. Expected a " + expectedClass.getSimpleName() + " element.");
    }

}