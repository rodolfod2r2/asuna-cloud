package org.framework.rodolfo.freire.git.asuna.cloud.data.mongo.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Message")
@Getter
@Setter
public class Message extends AuditMetadata {

    @Id
    @Indexed
    private String id;
    private String message;

}
