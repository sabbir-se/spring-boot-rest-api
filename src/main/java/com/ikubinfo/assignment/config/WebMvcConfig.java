package com.ikubinfo.assignment.config;

import com.ikubinfo.assignment.filter.GlobalInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Created by sabbir on 9/29/21.
 */

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Bean
    public GlobalInterceptor globalInterceptor() {
        return new GlobalInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(globalInterceptor());
    }
}
