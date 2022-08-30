package org.framework.rodolfo.freire.git.asuna.cloud.aop.aspect.log.audit;

import ch.qos.logback.classic.spi.ILoggingEvent;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class AspectEvents {

    private String level;
    private String loggerName;
    private String threadName;
    private String stackTrace;
    private Map<String, String> mdc;
    private Object content;

    public AspectEvents(ILoggingEvent event) {
        this.level = event.getLevel().toString();
        this.loggerName = event.getLoggerName();
        this.threadName = event.getThreadName();
        this.content = event.getFormattedMessage();

        if (event.hasCallerData()) {
            StackTraceElement st = event.getCallerData()[0];
            this.stackTrace = String.format("%s.%s:%d", st.getClassName(), st.getMethodName(), st.getLineNumber());
        }

        Map<String, String> mdcPropertyMap = event.getMDCPropertyMap();
        if (mdcPropertyMap != null && !mdcPropertyMap.isEmpty()) {
            this.mdc = mdcPropertyMap;
        }
    }
}
