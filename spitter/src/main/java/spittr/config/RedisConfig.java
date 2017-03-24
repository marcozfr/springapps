package spittr.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@EnableRedisHttpSession
@Profile("redis")
public class RedisConfig {
	
	@Value("${session.redis.hostname}")
	private String hostname = "localhost";
	
	@Value("${session.redis.port}")
	private int port = 6379;
	
	@Bean
	JedisConnectionFactory getConnectionFactory(){
		System.out.println("Configuring Redis connection on " + hostname + ":"+port);
		
		JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();
		jedisConnectionFactory.setHostName(hostname);
		jedisConnectionFactory.setPort(port);
		return jedisConnectionFactory;
	}
	
//	@Bean
//	public HttpSessionStrategy httpSessionStrategy() {
//		return new HeaderHttpSessionStrategy(); 
//	}
	
	
//	public static class RedisInitializer 
//	extends AbstractHttpSessionApplicationInitializer {
//
//	}
}
