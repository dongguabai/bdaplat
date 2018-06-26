package com.zj.bda.web.config;

import com.zj.bda.web.interceptor.HttpInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Created by Dongguabai on 2018-06-25 13:10
 */
@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
       registry.addInterceptor(new HttpInterceptor()).addPathPatterns("/**");
    }
}
