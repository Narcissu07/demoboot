package com.unicorn.demoboot.api.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.unicorn.common.framework.helper.AspectHelper;

@Aspect
@Component
public class HttpAspect {
	private static final Logger logger = LoggerFactory.getLogger(HttpAspect.class);
	
	@Around("execution(* com.unicorn.demoboot.api.controller..*.*(..))")
	public Object apiAspect(ProceedingJoinPoint jp) throws java.lang.Throwable {
		return AspectHelper.apiAspect(jp, logger);
	}
	
	@Around("execution(* com.unicorn.common.framework.impl..*.*(..))")
	public Object clientAspect(ProceedingJoinPoint jp) throws java.lang.Throwable {
		return AspectHelper.clientAspect(jp, logger);
	}
}
