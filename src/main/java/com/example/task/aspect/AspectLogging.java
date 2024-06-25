package com.example.task.aspect;

import com.example.task.entity.User;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
@Log4j2
public class AspectLogging {

//    @Pointcut("within(@org.springframework.stereotype.Repository *)" +
//            " || within(@org.springframework.stereotype.Service *)" +
//            " || within(@org.springframework.web.bind.annotation.RestController *)")
//    public void springBeanPointcut(){
//    }

    @Pointcut("within(com.example.task.service.impl.*) " )
    public void applicationPackagePointcut() {
    }
    @Pointcut("execution(* com.example.task.service.impl.UserServiceImpl.loadUserByUsername(..))")
    public void excludeLoadUserByUsername() {
        // Pointcut for UserServiceImpl.loadUserByUsername method
    }


    @Around("applicationPackagePointcut() && !excludeLoadUserByUsername()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        User principal =(User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = principal.getUsername();
        Signature signature = joinPoint.getSignature();
        if(log.isInfoEnabled()) {
            log.info("Username: {} - ENTER:{}.{}()",
                    username,
                    signature.getDeclaringTypeName(),
                    signature.getName()
            );
        }
        try {
            Object result = joinPoint.proceed();
            if(log.isInfoEnabled()) {
                log.info("Username: {} - EXIT:{}.{}()",
                        username,
                        signature.getDeclaringTypeName(),
                        signature.getName()
                );
            }
            return result;
        } catch (IllegalArgumentException e) {
            log.info("Username: {} - Illegal argument: {} in {}.{}()",
                    username,
                    Arrays.toString(joinPoint.getArgs()),
                    signature.getDeclaringTypeName(),
                    signature.getName()
            );
            throw e;
        }
    }
}
