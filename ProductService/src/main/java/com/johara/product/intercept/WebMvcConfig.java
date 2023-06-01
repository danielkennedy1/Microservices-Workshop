package com.johara.product.intercept;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    private final CookieInterceptor cookieInterceptor;

    @Autowired
    public WebMvcConfig(CookieInterceptor cookieInterceptor){
        this.cookieInterceptor = cookieInterceptor;
    }
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        System.out.println("Adding cookie interceptor");
        registry.addInterceptor(cookieInterceptor).addPathPatterns("/**");
    }
}