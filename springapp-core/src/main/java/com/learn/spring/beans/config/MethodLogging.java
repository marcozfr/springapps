package com.learn.spring.beans.config;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class MethodLogging implements MethodInterceptor{

	public Object invoke(MethodInvocation arg0) throws Throwable {
		System.out.println("In method " + arg0.getMethod().getName());
		Object retrieval = arg0.proceed();
		System.out.println("Out method " + arg0.getMethod().getName());
		return retrieval;
	}

}
