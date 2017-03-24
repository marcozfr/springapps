package org.example.rest.app;

import org.example.rest.config.HelloWorldConfiguration;
import org.example.rest.healthcheck.TemplateHealthCheck;
import org.example.rest.resource.HelloWorldResource;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class HelloWorldApplication extends Application<HelloWorldConfiguration> {

	public static void main(String[] args) throws Exception {
		new HelloWorldApplication().run(args);
	}
	
	@Override
	public String getName() {
		return "hello-world";
	}
	
	@Override
	public void initialize(Bootstrap<HelloWorldConfiguration> bootstrap) {
	
	}
	
	@Override
	public void run(HelloWorldConfiguration arg0, Environment arg1) throws Exception {
		final HelloWorldResource resource = new HelloWorldResource(arg0.getTemplate(), arg0.getDefaultName());
		arg1.jersey().register(resource);
		
		final TemplateHealthCheck healthCheck = new TemplateHealthCheck(arg0.getTemplate());
		arg1.healthChecks().register("template", healthCheck);
	}

}
