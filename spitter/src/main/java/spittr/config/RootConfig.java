package spittr.config;

import java.util.Arrays;
import java.util.regex.Pattern;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.type.filter.RegexPatternTypeFilter;

import spittr.aop.ResourceMetric;
import spittr.config.RootConfig.WebPackage;

@Configuration
@ComponentScan(basePackages = { "spittr" }, excludeFilters = {
		@Filter(type = FilterType.CUSTOM, value = WebPackage.class) })
@PropertySource(value = "classpath:app.properties")
@EnableAspectJAutoProxy
public class RootConfig {
	
	@Autowired
	Environment environment;
	
	@PostConstruct
	public void post(){
		System.out.println("Started spring root config ...");
		System.out.println("Active profiles: " + Arrays.asList(environment.getActiveProfiles()));
	}
	
	@Bean
	public ResourceMetric getResourceMetric(){
		return new ResourceMetric();
	}
	
	public static class WebPackage extends RegexPatternTypeFilter {
		public WebPackage() {
			super(Pattern.compile("spittr\\.web"));
		}
	}
}
