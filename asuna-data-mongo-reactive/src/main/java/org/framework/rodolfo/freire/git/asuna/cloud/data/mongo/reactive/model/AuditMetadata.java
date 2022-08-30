package org.framework.rodolfo.freire.git.asuna.cloud.data.mongo.reactive.model;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

public abstract class AuditMetadata {

    @CreatedDate
    private LocalDateTime createdDate;

    @LastModifiedDate
    private LocalDateTime lastModifiedDate;

    @CreatedBy
    private String createdByUser;

    @LastModifiedBy
    private String modifiedByUser;

}
