package org.framework.rodolfo.freire.git.asuna.cloud.aop.aspect.log.audit;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.CodeSignature;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.logging.LogLevel;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.StringJoiner;

@Aspect
@Component
public class LogAopAspect {

    static String inLog(String loggerLayer, String methodName, boolean showArgs, String[] params, Object[] args) {
        StringJoiner message = new StringJoiner(" ").add(loggerLayer).add("Started").add(methodName).add("method");
        if (showArgs && Objects.nonNull(params) && Objects.nonNull(args) && params.length == args.length) {
            Map<String, Object> values = new HashMap<>(params.length);
            for (int i = 0; i < params.length; i++) {
                values.put(params[i], args[i]);
            }
            message.add("with args:").add(values.toString());
        }
        return message.toString();
    }

    static String outerLog(String loggerLayer, String methodName, String duration, Object result, boolean showResult, boolean showExecutionTime) {
        StringJoiner message = new StringJoiner(" ").add(loggerLayer).add("Finished").add(methodName).add("method");
        if (showExecutionTime) {
            message.add("in").add(duration);
        }
        if (showResult) {
            message.add("with return:").add(result.toString());
        }
        return message.toString();
    }

    static void write(Logger loggerTrace, LogLevel level, String tagTrace, String message) {
        switch (level) {
            case DEBUG:
                loggerTrace.debug(tagTrace.concat(" - {}"), message);
                break;
            case TRACE:
                loggerTrace.trace(tagTrace.concat(" - {}"), message);
                break;
            case WARN:
                loggerTrace.warn(tagTrace.concat(" - {}"), message);
                break;
            case ERROR:
            case FATAL:
                loggerTrace.error(tagTrace.concat(" - {}"), message);
                break;
            default:
                loggerTrace.info(tagTrace.concat(" - {}"), message);
                break;
        }
    }

    @Around("@annotation(SplunkLog)")
    public Object log(ProceedingJoinPoint point) throws Throwable {

        CodeSignature codeSignature = (CodeSignature) point.getSignature();
        MethodSignature methodSignature = (MethodSignature) point.getSignature();
        Method method = methodSignature.getMethod();

//        AspectTrace aspectTrace = new AspectTrace();

        SplunkLog annotation = method.getAnnotation(SplunkLog.class);

        Logger loggerTrace = LoggerFactory.getLogger(annotation.loggerTrace());
        Logger loggerName = LoggerFactory.getLogger(annotation.loggerName());

//        aspectTrace.setApp("");
//        aspectTrace.setVersion("");
//        aspectTrace.setProfile("");
//        aspectTrace.setUuid("");
//        aspectTrace.setNameTrace("");
//        aspectTrace.setTagTrace(annotation.tagTrace());
//        aspectTrace.setLoggerTrace("");
//        aspectTrace.setLoggerName("");
//        aspectTrace.setLoggerLayer(annotation.loggerLayer());
//        aspectTrace.setLevel(annotation.value().name());
//        aspectTrace.setClassName("");
//        aspectTrace.setMethodName(method.getName());
//        aspectTrace.setMethodArgs(point.getArgs());
//        aspectTrace.setMethodParams(codeSignature.getParameterNames());
//        aspectTrace.setMethodResult(codeSignature.getParameterNames());
//        aspectTrace.setMethodExecutionTime("");

        String tagTrace = annotation.tagTrace();
        String loggerLayer = annotation.loggerLayer();
        LogLevel level = annotation.value();
        ChronoUnit unit = annotation.unit();
        boolean showClassName = annotation.showClassName();
        boolean showMethodName = annotation.showMethodName();
        boolean showArgs = annotation.showArgs();
        boolean showParams = annotation.showParams();
        boolean showResult = annotation.showResult();
        boolean showExecutionTime = annotation.showExecutionTime();

        String methodName = method.getName();
        Object[] methodArgs = point.getArgs();
        String[] methodParams = codeSignature.getParameterNames();

        write(loggerTrace, level, tagTrace, inLog(loggerLayer, methodName, showArgs, methodParams, methodArgs));

        Instant start = Instant.now();

        Object response;
        try {
            response = point.proceed();
//            return response;
        } catch (IllegalArgumentException e) {
//            log.error("Illegal argument: {} in {}.{}()", Arrays.toString(joinPoint.getArgs()),
//                    joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());
            throw e;
        }
       // Object response = point.proceed();
        Instant end = Instant.now();

        String duration = String.format("%s %s", unit.between(start, end), unit.name().toLowerCase());

        write(loggerTrace, level, tagTrace, outerLog(loggerLayer, methodName, duration, response, showResult, showExecutionTime));

        return response;
    }

    @AfterThrowing(pointcut = "@annotation(SplunkLog)", throwing = "error")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable error) {

        CodeSignature codeSignature = (CodeSignature) joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();

        SplunkLog annotation = method.getAnnotation(SplunkLog.class);

        Logger loggerTrace = LoggerFactory.getLogger(annotation.loggerTrace());

        AspectTrace aspectTrace = new AspectTrace();
        aspectTrace.setStackTrace(error);

        loggerTrace.error("[ASUNA-TRACE] - Target Method resulted into exception, message {}", aspectTrace.getStackTrace().getMessage());

    }

}

