package spittr.aop;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.atomic.AtomicInteger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class ResourceMetric {
	
	private AtomicInteger requestCount =  new AtomicInteger();
	
	@Before("execution(* spittr.data.*.*(..))")
	public void logBeforeMethod(JoinPoint joinpoint){
		System.out.println(joinpoint.getSignature().getName());
		System.out.println("Starting at : " + Instant.now());
	}
	
	@After("within(spittr.data.*)")
	public void logAfterSuccessMethod(JoinPoint joinpoint){
		System.out.println(joinpoint.getSignature().getName());
		System.out.println("Finishing at : " + Instant.now());
	}
	
	@Pointcut("execution(* spittr.data.SpittleRepository.save(..))")
	public void logOnSave(){
		
	}
	
	@After("logOnSave()")
	public void logSucessToDb(JoinPoint joinpoint){
//		System.out.println("logging to db : ");
//		System.out.println(joinpoint.getKind());
//		System.out.println(joinpoint.getSourceLocation().toString());
	}
	
	@Around("logOnSave()")
	public void logTimeSpent(ProceedingJoinPoint pJoinPoint){
		try {
			Instant now = Instant.now();
			pJoinPoint.proceed();
			Instant then = Instant.now();
			
			System.out.println("Time spent on save (ms): " + Duration.between(now, then).toMillis());
			requestCount.getAndIncrement();
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@AfterThrowing("execution(* spittr.data.*.*(..))")
	public void logAfterErrorMethod(){
		
		System.out.println("Finishing with error at : " + Instant.now());
	}
	
	public int getRequestCount(){
		return requestCount.get();
	}
	
}
