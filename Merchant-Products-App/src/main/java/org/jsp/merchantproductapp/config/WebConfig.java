package org.jsp.merchantproductapp.config;

import org.jsp.merchantproductapp.intercepter.ProductIntercepter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
	
	@Autowired
	private ProductIntercepter productIntercepter;
	
	@Override
	  public void addInterceptors(InterceptorRegistry registry) {
	        registry.addInterceptor(productIntercepter);
	    }	
}
