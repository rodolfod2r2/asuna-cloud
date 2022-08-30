package org.framework.rodolfo.freire.git.asuna.cloud.aop.aspect.log.audit;

import org.springframework.boot.logging.LogLevel;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.time.temporal.ChronoUnit;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({TYPE, FIELD, METHOD, PARAMETER})
@Retention(RUNTIME)
public @interface SplunkLog {

    String nameTrace() default "";

    String tagTrace() default "";

    String loggerTrace() default "";

    String loggerName() default "";

    String loggerLayer() default "";

    LogLevel value() default LogLevel.INFO;

    ChronoUnit unit() default ChronoUnit.MILLIS;

    boolean showClassName() default false;

    boolean showMethodName() default false;

    boolean showArgs() default false;

    boolean showParams() default false;

    boolean showResult() default false;

    boolean showExecutionTime() default true;

}
