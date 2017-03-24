package spittr.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan("spittr.web")
public class WebConfig extends WebMvcConfigurerAdapter {

	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		resolver.setViewClass(org.springframework.web.servlet.view.JstlView.class);
		resolver.setExposeContextBeansAsAttributes(true);
		return resolver;
	}

	@Bean
	public MessageSource messageSource() {
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasename("messages");
		return messageSource;
	}

	// reloadable ( prefixes: "file://" "classpath:" or root of web app : "" empty)
	// @Bean
	// public MessageSource reloadableMessageSource(){
	// 		ReloadableResourceBundleMessageSource messageSource = new
	// 		ReloadableResourceBundleMessageSource();
	// 		messageSource.setBasename("classpath:messages");
	// 		messageSource.setCacheSeconds(15);
	// 		return messageSource;
	// }

	
//	tiles config
//	@Bean
//	public TilesConfigurer tilesConfigurer() {
//		TilesConfigurer tilesConfigurer = new TilesConfigurer();
//		tilesConfigurer.setDefinitions(new String[] { "/WEB-INF/layout/tiles.xml" });
//		tilesConfigurer.setCheckRefresh(true);
//		return tilesConfigurer;
//	}
//	
////	tiles viewResolver
//	@Bean
//	public ViewResolver tilesViewResolver(){
//		return new TilesViewResolver();
//	}

	/** ask DispatcherServlet to forward
	 requests for static resources to the servlet container’s default servlet and not to try to
	 handle them itself.
	 */
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		super.addResourceHandlers(registry);
	}
	
	

}
