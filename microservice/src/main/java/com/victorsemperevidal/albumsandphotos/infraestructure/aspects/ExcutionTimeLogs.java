package com.victorsemperevidal.albumsandphotos.infraestructure.aspects;

import java.text.MessageFormat;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ExcutionTimeLogs {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Around("execution(* com.victorsemperevidal.albumsandphotos.application.*.*(..))")
    public Object logApplicationLayer(ProceedingJoinPoint joinPoint) throws Throwable {
        return logExecutionTime(joinPoint);
    }

    @Around("execution(* com.victorsemperevidal.albumsandphotos.infraestructure.services.external_data.*.*(..))")
    public Object logInfraestructureLayerExternalDataService(ProceedingJoinPoint joinPoint) throws Throwable {
        return logExecutionTime(joinPoint);
    }

    @Around("execution(* com.victorsemperevidal.albumsandphotos.infraestructure.services.populate_service.*.*(..))")
    public Object logInfraestructureLayerPopulateService(ProceedingJoinPoint joinPoint) throws Throwable {
        return logExecutionTime(joinPoint);
    }

    @Around("execution(* com.victorsemperevidal.albumsandphotos.infraestructure.services.process_albums_service.*.*(..))")
    public Object logInfraestructureLayerProcessAlbumsService(ProceedingJoinPoint joinPoint) throws Throwable {
        return logExecutionTime(joinPoint);
    }

    private Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        String className = joinPoint.getTarget().getClass().getSimpleName();
        String methodName = joinPoint.getSignature().getName();

        long start = System.nanoTime();
        Object result = joinPoint.proceed();
        long end = System.nanoTime();

        String logMessage = MessageFormat.format("Tiempo de ejecución de {0}.{1}: {2} ns", className, methodName,
                (end - start));
        log.debug(logMessage);

        return result;
    }
}
