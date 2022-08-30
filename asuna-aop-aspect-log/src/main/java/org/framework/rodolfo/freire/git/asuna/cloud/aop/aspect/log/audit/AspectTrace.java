package org.framework.rodolfo.freire.git.asuna.cloud.aop.aspect.log.audit;

import ch.qos.logback.classic.spi.ILoggingEvent;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Getter
@Setter
@NoArgsConstructor
public class AspectTrace {

    private String app;
    private String version;
    private String profile;
    private String uuid;
    private String nameTrace;
    private String tagTrace;
    private String loggerTrace;
    private String loggerName;
    private String loggerLayer;
    private String level;
    private String className;
    private String methodName;
    private Object[] methodArgs;
    private String[] methodParams;
    private String[] methodResult;
    private String methodExecutionTime;
    private Instant start;
    private Instant end;
    private String duration;
    private List<AspectEvents> aspectEvents = new ArrayList<>();
    private AspectTraceStack stackTrace;

    public AspectTrace(ILoggingEvent event) {
        aspectEvents.add(new AspectEvents(event));
    }

    public void setStackTrace(Throwable throwable) {
        try {
            stackTrace = new AspectTraceStack(throwable.getClass().getName(), throwable.getMessage(), ExceptionUtils.getStackTrace(throwable));
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
    }

}
