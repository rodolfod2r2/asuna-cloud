package org.framework.rodolfo.freire.git.asuna.cloud.aop.aspect.log.audit;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.time.Instant;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class AspectTraceStack implements Serializable {

    private static final long serialVersionUID = -5020371554890225783L;

    @JsonInclude(NON_NULL)
    public String clazz;

    @JsonInclude(NON_NULL)
    public String message;

    @JsonInclude(NON_NULL)
    public String stack;

}
