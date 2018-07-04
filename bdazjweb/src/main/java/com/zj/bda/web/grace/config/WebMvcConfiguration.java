package com.zj.bda.web.grace.config;

import com.google.common.collect.Lists;
import com.zj.bda.web.grace.converter.DateConverter;
import com.zj.bda.web.grace.interceptor.HttpInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Created by Dongguabai on 2018-06-25 13:10
 */
@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new DateConverter());
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new HttpInterceptor()).addPathPatterns("/**").excludePathPatterns(Lists.newArrayList("/assets/**","/**/*.html"));
        //registry.addInterceptor(new HttpInterceptor()).addPathPatterns("/**").excludePathPatterns(Lists.newArrayList("/css/**", "/img/**", "/js/**","/**/*.html"));
    }
}
