package com.monkeydp.daios.dms.sdk.exception.handler

import com.monkeydp.tools.config.debugMode
import com.monkeydp.tools.config.devModel
import com.monkeydp.tools.logger.getLogger
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Pointcut
import org.aspectj.lang.reflect.MethodSignature
import java.lang.reflect.Method
import kotlin.reflect.full.isSubclassOf

/**
 * @author iPotato
 * @date 2019/12/5
 */
@Aspect
class ExceptionHandler {
    
    companion object {
        val log = getLogger()
    }
    
    @Pointcut("@annotation(com.monkeydp.daios.dms.sdk.exception.handler.IgnoreException)")
    fun ignoreException() {
    }
    
    @Around("ignoreException()")
    fun around(joinPoint: ProceedingJoinPoint): Any? {
        val signature: MethodSignature = joinPoint.getSignature() as MethodSignature
        val method: Method = signature.getMethod()
        val annot = method.getAnnotation(IgnoreException::class.java)
        return try {
            joinPoint.proceed(joinPoint.args);
        } catch (e: Throwable) {
            if (!e.javaClass.kotlin.isSubclassOf(annot.exKClass) ||
                devModel != annot.inDevMode
            ) throw e
            log.debugMode(e)
            null
        }
    }
}