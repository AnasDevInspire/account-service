package com.inspire.core.account.controller;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ControllerAspect {

	private final Logger logger = LoggerFactory.getLogger(AccountController.class);

	@Before("execution(* com.inspire.core.account.controller.AccountController.*(..))")
	public void logBeforeMethod(JoinPoint joinPoint) {
//		logger.info(joinPoint.getSignature().getName(),joinPoint.getArgs());
		Object[] args = joinPoint.getArgs();
		String methodName = joinPoint.getSignature().getName();
		logger.info(">> {}() - {}", methodName, Arrays.toString(args));
	}

	@After("execution(* com.inspire.core.account.controller.AccountController.*(..))")
	public void logAfterMethod(JoinPoint joinPoint) {
		logger.info("/" + joinPoint.getSignature().getName(), joinPoint.getArgs());
//		logger.info("/" + joinPoint.getSignature().getName());
	}
}
