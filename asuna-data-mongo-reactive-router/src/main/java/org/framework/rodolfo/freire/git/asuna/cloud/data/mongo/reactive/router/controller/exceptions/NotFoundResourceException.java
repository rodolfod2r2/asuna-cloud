package org.framework.rodolfo.freire.git.asuna.cloud.data.mongo.reactive.router.controller.exceptions;

public class NotFoundResourceException extends Exception {

    public NotFoundResourceException(String resourceName) {
        super("Resource " + resourceName + " not found.");
    }
}