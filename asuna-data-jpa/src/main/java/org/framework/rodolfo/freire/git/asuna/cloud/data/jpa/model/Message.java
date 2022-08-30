package org.framework.rodolfo.freire.git.asuna.cloud.data.jpa.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;


@Entity(name = "Message")
@Getter
@Setter
public class Message extends AuditMetadata {

    @Id
    private Long id;
    private String message;

}
