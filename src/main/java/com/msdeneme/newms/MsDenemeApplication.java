package com.msdeneme.newms;

import com.msdeneme.newms.security.XSSFilter;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.Date;
import java.util.TimeZone;

@SpringBootApplication
public class MsDenemeApplication {

	private static final Logger logger = LoggerFactory.getLogger(MsDenemeApplication.class);

	public static void main(String[] args) {
		logger.info("Ms Deneme  Application up and running");
		//System.getProperties().put("http.proxyHost", "host");
		//System.getProperties().put("http.proxyPort", "port");

		logger.info("Proxy host: " + System.getProperties().getProperty("http.proxyHost"));
		logger.info("Proxy port:" + System.getProperties().getProperty("http.proxyPort"));
		SpringApplication.run(MsDenemeApplication.class, args);
	}

	@PostConstruct
	public void init(){
		TimeZone.setDefault(TimeZone.getTimeZone("Europe/Istanbul"));   // It will set UTC timezone
		logger.info("Spring boot application running in UTC timezone :" + new Date() + "  - " + TimeZone.getTimeZone("Europe/Istanbul"));   // It will print UTC timezone
	}

	@Bean
	FilterRegistrationBean myFilterRegistration() {
		FilterRegistrationBean frb = new FilterRegistrationBean();
		frb.setFilter(new XSSFilter());
		frb.setUrlPatterns(Arrays.asList("/*"));
		logger.info("XSS Filter Bean added");
		return frb;
	}

	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOrigins(Arrays.asList("*"));
		configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
		configuration.setAllowedHeaders(Arrays.asList("authorization", "content-type", "x-auth-token"));
		configuration.setExposedHeaders(Arrays.asList("x-auth-token"));
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		logger.info("CORS Configuration Source Bean added");
		return source;
	}


	@Bean
	public DataSource dataSource() {
		DataSource source = new DataSource();
		PoolProperties poolProperties = new PoolProperties();

		String dbConnectionUrl = "jdbc:mysql://localhost:3306/market";
		String dbUsername = "market_app" ;
		String dbPassword = "market_app!123";
		String dbDriverClassName = "com.mysql.jdbc.Driver";

		poolProperties.setUrl(dbConnectionUrl);
		poolProperties.setUsername(dbUsername);
		poolProperties.setPassword(dbPassword);
		poolProperties.setDriverClassName(dbDriverClassName);
		/*
		poolProperties.setValidationQuery(dbValidationQuery);
		poolProperties.setTestOnBorrow(Boolean.parseBoolean(dbTestOnBorrow));
		poolProperties.setMaxActive(Integer.parseInt(dbMaxActive));
		poolProperties.setMaxIdle(Integer.parseInt(dbMaxIdle));
		poolProperties.setMinIdle(Integer.parseInt(dbMinIdle));
		poolProperties.setMaxWait(Integer.parseInt(dbMaxWait));
		poolProperties.setInitialSize(Integer.parseInt(dbInitialSize));
		poolProperties.setLogAbandoned(Boolean.parseBoolean(dbLogAbandoned));
		poolProperties.setRemoveAbandoned(Boolean.parseBoolean(dbRemoveAbandoned));
		poolProperties.setRemoveAbandonedTimeout(Integer.parseInt(dbRemoveAbandonedTimeout));
		poolProperties.setValidationInterval(Integer.parseInt(dbValidationInterval));
	*/

		source.setPoolProperties(poolProperties);
		return source;
	}


}
