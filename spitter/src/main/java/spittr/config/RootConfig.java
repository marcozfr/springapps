package spittr.config;

import java.util.regex.Pattern;

import javax.annotation.PostConstruct;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.type.filter.RegexPatternTypeFilter;

import spittr.aop.ResourceMetric;
import spittr.config.RootConfig.WebPackage;

@Configuration
//@Import(DataConfig.class)
@ComponentScan(basePackages = { "spittr" }, excludeFilters = {
		@Filter(type = FilterType.CUSTOM, value = WebPackage.class) })
@PropertySource(value = "classpath:app.properties")
@EnableAspectJAutoProxy
public class RootConfig {
	
	@PostConstruct
	public void post(){
		System.out.println("Started spring root config ...");
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
