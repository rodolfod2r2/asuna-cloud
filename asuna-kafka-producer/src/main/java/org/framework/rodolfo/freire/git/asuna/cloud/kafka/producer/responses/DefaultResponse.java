package org.framework.rodolfo.freire.git.asuna.cloud.kafka.producer.responses;

import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.Nullable;
import org.springframework.web.reactive.HandlerMapping;
import org.springframework.web.reactive.function.server.ServerRequest;

import java.sql.Timestamp;

@Getter
@Setter
public class DefaultResponse {

    private String timestamp = new Timestamp(System.currentTimeMillis()).toString();
    private int status;
    private String error = "";
    private String message;
    private String path;

    public DefaultResponse(int status, String message, @Nullable ServerRequest request) {
        this.status = status;
        this.message = message;
        this.path = String.valueOf(request.attribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE));
    }

}
