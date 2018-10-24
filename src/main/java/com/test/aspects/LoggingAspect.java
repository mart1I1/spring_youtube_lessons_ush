package com.test.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Aspect
public class LoggingAspect {

    private Map<String, Integer> logEventCount = new HashMap<>();

    public Map<String, Integer> getLogEventCount() {
        return logEventCount;
    }

    @Pointcut("execution(* *.logEvent(..))")
    private void logEventsMethods() {}

    @AfterReturning("logEventsMethods()")
    public void countAfter(JoinPoint joinPoint) {
        String className = joinPoint.getTarget().getClass().getSimpleName();
        if (!logEventCount.containsKey(className)) {
            logEventCount.put(className, 0);
        }
        logEventCount.put(className, logEventCount.get(className) + 1);
    }
}
