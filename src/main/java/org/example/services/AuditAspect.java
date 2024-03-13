package org.example.services;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.example.models.AuditLog;
import org.example.repositories.AuditLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
@Slf4j
public class AuditAspect {

    private final AuditLogRepository auditLogRepository;

    @Autowired
    public AuditAspect(AuditLogRepository auditLogRepository) {
        this.auditLogRepository = auditLogRepository;
    }

    public String getCurrentUsername() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getName();
    }

    @AfterReturning(pointcut = "execution(* org.example.controllers.api_controllers.*.*(..))")
    public void logAudit(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getTarget().getClass().getName();
        Object[] args = joinPoint.getArgs();

        String username = getCurrentUsername();

        AuditLog auditLog = new AuditLog();
        auditLog.setUsername(username);
        auditLog.setMethodName(methodName);
        auditLog.setClassName(className);
        auditLog.setArguments(Arrays.toString(args));

        auditLogRepository.save(auditLog);
    }
    @AfterReturning(pointcut = "execution(* org.example.controllers.auth_controllers.*.*(..))")
    public void logAuditAuth(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getTarget().getClass().getName();
        Object[] args = joinPoint.getArgs();

        String username = getCurrentUsername();//anonymus

        AuditLog auditLog = new AuditLog();
        auditLog.setUsername(username);
        auditLog.setMethodName(methodName);
        auditLog.setClassName(className);
        auditLog.setArguments(Arrays.toString(args));

        auditLogRepository.save(auditLog);
    }
}
