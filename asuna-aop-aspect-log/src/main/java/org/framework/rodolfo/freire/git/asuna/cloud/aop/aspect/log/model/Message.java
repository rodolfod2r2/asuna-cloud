package org.framework.rodolfo.freire.git.asuna.cloud.aop.aspect.log.model;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ToString
public class Message extends AuditMetadata {

    private UUID uuid;
    private String message;

}
