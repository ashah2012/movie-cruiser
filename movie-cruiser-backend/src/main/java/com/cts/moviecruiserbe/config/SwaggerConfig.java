/**
 * 
 */
package com.cts.moviecruiserbe.config;

import static springfox.documentation.builders.PathSelectors.regex;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author ubuntu
 *
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket productApi() {
		return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.basePackage("com.cts.moviecruiserbe")).paths(regex("/api/movie.*")).build();
		
	}
	/*
	protected void addResourceHandlers(final ResourceHandlerRegistry resourceHandlerRegistry) {
		resourceHandlerRegistry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
		resourceHandlerRegistry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars");
	}*/
}