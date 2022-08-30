package org.framework.rodolfo.freire.git.asuna.cloud.aop.aspect.log.audit;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.AppenderBase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Configuration
@Slf4j
public class AspectAppender extends AppenderBase<ILoggingEvent> {

    @PostConstruct
    @Override
    public void start() {
        log.info("Iniciando Trace Appender");
        super.start();
    }

    @PreDestroy
    @Override
    public void stop() {
        log.info("Fechando Trace Appender");
        super.stop();
    }

    @Override
    protected void append(ILoggingEvent event) {
        if (event.getLoggerName().equals("ASUNA-TRACE")) {
            new AspectTrace(event);
        }
    }

}
