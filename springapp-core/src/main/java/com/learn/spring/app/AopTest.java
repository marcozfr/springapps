package com.learn.spring.app;

import org.springframework.aop.framework.ProxyFactory;

import com.learn.spring.beans.LocalCongressChief;
import com.learn.spring.beans.config.MethodLogging;

public class AopTest {

	public static void main(String[] args) {
		LocalCongressChief congressChief = new LocalCongressChief();
		congressChief.setMessage("My Message");
		congressChief.doPitch();
		
		ProxyFactory proxy = new ProxyFactory();
		proxy.addAdvice(new MethodLogging());
		proxy.setTarget(congressChief);
		
		//proxied
		((LocalCongressChief)proxy.getProxy()).doPitch();
		
		//bean no proxy
		congressChief.doPitch();
	}
}
