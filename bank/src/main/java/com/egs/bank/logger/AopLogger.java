package com.egs.bank.logger;

import com.egs.bank.security.JwtToken;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AopLogger {

    @Autowired
    MyLogger logger;

    @Autowired
    JwtToken jwtToken;

    @Before("execution(* com.egs.bank..service..*(..)) ")
    public void logService(JoinPoint joinPoint) {
        String args = argsToString(joinPoint.getArgs());
        String callingMethod = joinPoint.getSignature().getName() + " called :params=" + args;
        logger.eventLogger.info(callingMethod);
    }

    @Before("execution(* com.egs.bank..controller..*(..)) ")
    public void logController(JoinPoint joinPoint) {
        String args = argsToString(joinPoint.getArgs());
        String callingMethod = joinPoint.getSignature().getName() + " called :params=" + args;
        logger.eventLogger.info(callingMethod);
    }

    @AfterThrowing(value = "execution(* com.egs.bank..controller..*(..)) ", throwing = "e")
    public void logControllerError(JoinPoint joinPoint, Throwable e) {
        logger.logStackTrace(e);
        logger.eventLogger.error(joinPoint.getSignature().getName() + " error:" + e.getMessage());
    }

    @AfterThrowing(value = "execution(* com.egs.bank..service..*(..)) ", throwing = "e")
    public void logServiceError(JoinPoint joinPoint, Throwable e) {
        logger.logStackTrace(e);
        logger.eventLogger.error(joinPoint.getSignature().getName() + " error:" + e.getMessage());
    }

    @AfterReturning(value = "execution(* com.egs.bank..service..*(..)) ", returning = "returnValue")
    public void returnService(JoinPoint joinPoint, Object returnValue) {
        if (returnValue != null) {
            logger.eventLogger.info(joinPoint.getSignature().getName() + " returned:" + returnValue);
        } else {
            logger.eventLogger.info(joinPoint.getSignature().getName() + "Returning null -");
        }
    }

    @AfterReturning(value = "execution(* com.egs.bank..controller..*(..)) ", returning = "returnValue")
    public void returnController(JoinPoint joinPoint, Object returnValue) {
        if (returnValue != null) {
            logger.eventLogger.info(joinPoint.getSignature().getName() + " returned:" + returnValue);
        } else {
            logger.eventLogger.info(joinPoint.getSignature().getName() + "Returning null -");
        }
    }

    private String argsToString(Object[] arguments) {
        String args = "";
        for (Object arg : arguments) {
            if (arg != null)
                args += arg.toString() + ",";
            else
                args += "NULL,";
        }
        if (!"".equals(args))
            args = args.substring(0, args.length() - 1);
        return args;
    }
}
