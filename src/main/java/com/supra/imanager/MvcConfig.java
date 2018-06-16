package com.supra.imanager;

import java.io.IOException;
import java.util.Properties;

import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.exception.VelocityException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ui.velocity.VelocityEngineFactoryBean;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter {

	  @Override
	  public void addInterceptors(InterceptorRegistry registry) {
	    registry.addInterceptor(getRequestInterceptor());
	  }

	  @Bean
	  public RequestInterceptor getRequestInterceptor() {
		return new RequestInterceptor();
	  }
	  
	  
	  
	  @Bean
	  public VelocityEngine velocityEngine() throws VelocityException, IOException{
	  	VelocityEngineFactoryBean factory = new VelocityEngineFactoryBean();
	  	Properties props = new Properties();
	  	props.put("resource.loader", "class");
	  	props.put("class.resource.loader.class", 
	  			  "org.apache.velocity.runtime.resource.loader." + 
	  			  "ClasspathResourceLoader");
	  	factory.setVelocityProperties(props);
	  	
	  	return factory.createVelocityEngine();
	  }
	  
	  @Override
	    public void addResourceHandlers(ResourceHandlerRegistry registry) {
	        registry
	          .addResourceHandler("/resources/**")
	          .addResourceLocations("/resources/"); 
	    }

}
