package org.feather.aop;

import lombok.extern.slf4j.Slf4j;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * @projectName: SpringAI-MCP-RAG
 * @package: org.feather.aop
 * @className: LogAspect
 * @author: feather
 * @description:
 * @since: 2026-01-19 9:42 PM
 * @version: 1.0
 */
@Slf4j
@Component
@Aspect
public class LogAspect {

    /**
     * @description:aop 环绕切面
     * @author: feather
     * @date: 2026-01-19 9:45 PM
     * @param:  * @param joinPoint
     * @return: {@link Object}
     **/
    @Around("execution(* org.feather.service.impl..*.*(..))")
    public Object  recordTimesLog(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object proceed = joinPoint.proceed();
        String point = joinPoint.getTarget().getClass().getName()
                + "."
                +joinPoint.getSignature().getName();
        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;
        if (duration>3000){
            log.error("{} 耗时偏长 {}毫秒", point, duration);
        }else if (duration>2000){
            log.warn("{} 耗时中等 {}毫秒 ", point, duration);
        }else {
            log.info("{} 耗时 {}毫秒", point, duration);
        }
        return proceed;
    }
}
