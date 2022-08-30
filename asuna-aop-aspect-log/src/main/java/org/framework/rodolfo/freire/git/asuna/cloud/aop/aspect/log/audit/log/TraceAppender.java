//package org.framework.rodolfo.freire.git.asuna.cloud.aop.aspect.log.audit.log;
//
//import ch.qos.logback.classic.spi.ILoggingEvent;
//import ch.qos.logback.core.AppenderBase;
//import lombok.NoArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//
//@Slf4j
//@NoArgsConstructor
//public class TraceAppender extends AppenderBase<ILoggingEvent> {
//
//    @Override
//    public void start() {
//        log.info("Iniciando Trace Appender");
//        super.start();
//    }
//
//    @Override
//    public void stop() {
//        log.info("Fechando Trace Appender");
//        super.stop();
//    }
//
//    @Override
//    protected void append(ILoggingEvent iLoggingEvent) {
//        if (!"main".equalsIgnoreCase(iLoggingEvent.getThreadName()) && TraceContextHolder.getInstance().getCurrentTrace() != null) {
//            TraceContextHolder.getInstance().getCurrentTrace().loggingTrace(iLoggingEvent);
//        }
//    }
//
//}
