package spittr.aop;

import java.time.Instant;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class ResourceMetric {
	
	@Before(value = "execution(** spittr.web.SpittleController.spittles(..))")
	public void logBeforeMethod(){
		System.out.println("Starting at : " + Instant.now());
	}
	
	@AfterReturning(value = "execution(** spittr.web.SpittleController.spittles(..))")
	public void logAfterSuccessMethod(){
		System.out.println("Finishing at : " + Instant.now());
	}
	
	@AfterThrowing(value = "execution(** spittr.web.SpittleController.spittles(..))")
	public void logAfterErrorMethod(){
		System.out.println("Finishing with error at : " + Instant.now());
	}
	
}
