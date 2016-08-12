package spittr.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.formLogin().loginPage("/login").permitAll()
		.and().httpBasic().realmName("spitterRealm")
		.and().rememberMe().tokenValiditySeconds(2419200).key("spitterKey")
		.and().logout().permitAll().logoutSuccessUrl("/").logoutUrl("/logout")
		.and().authorizeRequests()
		.antMatchers("/","/home","/spitter/register").permitAll()
//		.antMatchers(HttpMethod.POST,"/spittles").
		.anyRequest().hasAuthority("ROLE_USER")
		;
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("user").password("password").roles("USER").and()
		.withUser("admin").password("password").roles("USER","ADMIN");
		
//		auth.jdbcAuthentication().dataSource(dataSource);
		
		
	}
}
