package com.swapvista.config;

import java.util.Arrays;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
public class WebConfig {

	//Define constants for max age and filter order
    private static final Long MAX_AGE = 3600L;
    private static final int CORS_FILTER_ORDER = -102;

    @Bean
    public FilterRegistrationBean<CorsFilter> corsFilter() {
    	
    	//Creates a configuration source for CORS
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        
        //Create a CORS configuration
        CorsConfiguration config = new CorsConfiguration();
      
        //Allow credentials to be included in CORS requests
        config.setAllowCredentials(true);
       
        //Specifying allowed origin
        config.addAllowedOrigin("http://localhost:3000");
       
        //Specifying allowed headers
        config.setAllowedHeaders(Arrays.asList(
                HttpHeaders.AUTHORIZATION,
                HttpHeaders.CONTENT_TYPE,
                HttpHeaders.ACCEPT));
       
        //Specify allowed HTTP methods
        config.setAllowedMethods(Arrays.asList(
                HttpMethod.GET.name(),
                HttpMethod.POST.name(),
                HttpMethod.PUT.name(),
                HttpMethod.DELETE.name()));
       
        //Setting maximum age for CORS requests
        config.setMaxAge(MAX_AGE);
       
        //Applying the CORS configuration for all paths
        source.registerCorsConfiguration("/**", config);
        
        //Creating a filter registration bean for the CorsFilter
        FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<>(new CorsFilter(source));

        //Set the order of the filter to ensure it runs before other filters
        //Negative value ensures it runs very early, before Spring Security filters
        bean.setOrder(CORS_FILTER_ORDER);  // value -102
        return bean;
    }
}
