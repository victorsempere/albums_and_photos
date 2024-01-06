package com.victorsemperevidal.albumsandphotos.infraestructure.aspects;

import java.text.MessageFormat;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * En este servicio nos encargamos de que cualquier excepción lanzada se quede
 * registrada en los logs del sistema
 * 
 */
@Aspect
@Component
public class ExceptionsLogs {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @AfterThrowing(pointcut = "execution(* com.victorsemperevidal.albumsandphotos..*(..))", throwing = "ex")
    public void exceptionThrown(JoinPoint joinPoint, Exception ex) {
        String className = joinPoint.getTarget().getClass().getSimpleName();
        String methodName = joinPoint.getSignature().getName();

        String errorMessage = MessageFormat.format("Excepción capturada desde {0}.{1}", className, methodName);
        log.error(errorMessage, ex);
    }
}
