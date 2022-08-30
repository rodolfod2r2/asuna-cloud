//package org.framework.rodolfo.freire.git.asuna.cloud.aop.aspect.log.audit.log;
//
//import lombok.NoArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.framework.rodolfo.freire.git.asuna.cloud.aop.aspect.log.audit.AspectTrace;
//import org.springframework.context.annotation.Bean;
//
//import java.util.Objects;
//import java.util.UUID;
//import java.util.concurrent.ConcurrentHashMap;
//
//@NoArgsConstructor
//@Slf4j
//public class TraceContextHolder {
//
//    private static final ThreadLocal<String> CONTEXT_HOLDER = new ThreadLocal<>();
//    private static final ConcurrentHashMap<String, Trace> TRACE_MAP = new ConcurrentHashMap<>();
//    private static TraceContextHolder instanceHolder;
//
//    @Bean
//    public Trace initializeContextHolder(){
//        Trace trace = new Trace();
//        String uuid = UUID.randomUUID().toString();
//        CONTEXT_HOLDER.set(uuid);
//        trace.setUuid(uuid);
//        TRACE_MAP.put(uuid, trace);
//        return getCurrentTrace();
//    }
//
//    public static TraceContextHolder getInstance() {
//        if (instanceHolder == null) {
//            return new TraceContextHolder();
//        }
//        return instanceHolder;
//    }
//
//    public Trace getCurrentTrace() {
//        if (CONTEXT_HOLDER.get() != null) {
//            return TRACE_MAP.get(CONTEXT_HOLDER.get());
//        } else {
//            return null;
//        }
//    }
//
//    public void clearCurrentTrace() {
//        if (CONTEXT_HOLDER.get() != null) {
//            TRACE_MAP.remove(CONTEXT_HOLDER.get());
//        }
//    }
//
//    public void unset() {
//        CONTEXT_HOLDER.remove();
//    }
//
//    public void loggingStackTrace(Throwable throwable) {
//        if (throwable != null) {
//            new AspectTrace().setStackTrace(throwable);
//        }
//    }
//
//
//}
