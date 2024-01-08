package com.victorsemperevidal.albumsandphotos.infraestructure.aspects;

import java.text.MessageFormat;
import java.util.Collection;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ExcutionTimeLogs {
    @Value("${execution-time-logs.enable-minimum-time-to-print-traces:false}")
    private boolean enableMinimumTimeToPrintTraces;

    @Value("${execution-time-logs.minimum-time-in-nanos-to-print-trace:1000000000}")
    private int minimumTimeInNanosToPrintTrace;

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Around("execution(* com.victorsemperevidal.albumsandphotos.infraestructure.controllers.AlbumsAndPhotosRestController.*(..))")
    public Object logInfraestructureLayerAlbumsAndPhotosRestController(ProceedingJoinPoint joinPoint) throws Throwable {
        return logExecutionTime(joinPoint);
    }

    @Around("execution(* com.victorsemperevidal.albumsandphotos.application..*(..))")
    public Object logApplicationLayer(ProceedingJoinPoint joinPoint) throws Throwable {
        return logExecutionTime(joinPoint);
    }

    @Around("execution(* com.victorsemperevidal.albumsandphotos.infraestructure.services.external_data..*(..))")
    public Object logInfraestructureLayerExternalDataService(ProceedingJoinPoint joinPoint) throws Throwable {
        return logExecutionTime(joinPoint);
    }

    @Around("execution(* com.victorsemperevidal.albumsandphotos.infraestructure.services.populate_service..*(..))")
    public Object logInfraestructureLayerPopulateService(ProceedingJoinPoint joinPoint) throws Throwable {
        return logExecutionTime(joinPoint);
    }

    @Around("execution(* com.victorsemperevidal.albumsandphotos.infraestructure.services.process_albums_service..*(..))")
    public Object logInfraestructureLayerProcessAlbumsService(ProceedingJoinPoint joinPoint) throws Throwable {
        return logExecutionTime(joinPoint);
    }

    @Around("execution(* com.victorsemperevidal.albumsandphotos.infraestructure.repositories..*(..))")
    public Object logInfraestructureLayerRepositories(ProceedingJoinPoint joinPoint) throws Throwable {
        return logExecutionTime(joinPoint);
    }

    @Around("execution(* com.victorsemperevidal.albumsandphotos.infraestructure.factories.daos..*(..))")
    public Object logInfraestructureLayerDaosFactories(ProceedingJoinPoint joinPoint) throws Throwable {
        return logExecutionTime(joinPoint);
    }

    @Around("execution(* com.victorsemperevidal.albumsandphotos.infraestructure.factories.domain_objects..*(..))")
    public Object logInfraestructureLayerDomainObjectsFactories(ProceedingJoinPoint joinPoint) throws Throwable {
        return logExecutionTime(joinPoint);
    }

    private Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        String className = joinPoint.getTarget().getClass().getSimpleName();
        String methodName = joinPoint.getSignature().getName();
        String declaringTypeName = joinPoint.getSignature().getDeclaringTypeName();

        long start = System.nanoTime();
        Object result = joinPoint.proceed();
        long end = System.nanoTime();

        long executionTimeInNanos = end - start;
        if (!enableMinimumTimeToPrintTraces || executionTimeInNanos > minimumTimeInNanosToPrintTrace) {
            String argsString = formatArgsIntoAString(joinPoint.getArgs());
            String logMessage = MessageFormat.format("Tiempo de ejecución {0}ns: {1}.{2} args: {3} tipo: {4}",
                    executionTimeInNanos, className, methodName, argsString, declaringTypeName);
            log.debug(logMessage);
        }

        return result;
    }

    private String formatArgsIntoAString(Object[] args) {
        if (args == null) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < args.length; i++) {
            if (sb.length() > 1) {
                sb.append(", ");
            }
            sb.append(getArgValue(args[i]));
        }
        sb.append("]");

        return sb.toString();
    }

    private String getArgValue(Object param) {
        if (param instanceof Iterable) {
            return getStringDescribingTypeOfIterableAndNumberOfElement((Iterable<?>) param);
        } else {
            return String.valueOf(param);
        }
    }

    private String getStringDescribingTypeOfIterableAndNumberOfElement(Iterable<?> iterable) {
        int size = -1;
        if (iterable instanceof Collection) {
            size = ((Collection<?>) iterable).size();
        }
        return MessageFormat.format("{0}.{1} ({2} elemenos)", iterable.getClass().getPackageName(),
                iterable.getClass().getSimpleName(), size);
    }
}
