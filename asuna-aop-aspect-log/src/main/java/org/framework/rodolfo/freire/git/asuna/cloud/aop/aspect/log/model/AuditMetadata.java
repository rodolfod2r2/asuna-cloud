package org.framework.rodolfo.freire.git.asuna.cloud.aop.aspect.log.model;


import java.time.LocalDateTime;

public abstract class AuditMetadata {

    private LocalDateTime createdDate;

    private LocalDateTime lastModifiedDate;

    private String createdByUser;

    private String modifiedByUser;

}
