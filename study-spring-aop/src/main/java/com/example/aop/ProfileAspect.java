package com.example.aop;

import java.util.concurrent.TimeUnit;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class ProfileAspect {
//	@Before
//	@After
//	@AfterReturning
//	@AfterThrowing
	@Around("@annotation(profiler)")
	public Object methodProfiled(ProceedingJoinPoint pjp, Profiler profiler) throws Throwable {
		long start = System.nanoTime();
		Object result = pjp.proceed();
		long stop = System.nanoTime();
		String methodName = pjp.getSignature().getName();
		TimeUnit tu = profiler.value();
		System.err.println(
				String.format("%s runs %d %s", methodName, tu.convert(stop - start, TimeUnit.NANOSECONDS), tu.name()));
		return result;
	}

	@Around("@target(profiler)")
	public Object classProfiled(ProceedingJoinPoint pjp, Profiler profiler) throws Throwable {
		return methodProfiled(pjp, profiler);
	}

}
