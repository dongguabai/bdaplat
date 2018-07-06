package com.zj.bda.web.grace;

import com.google.common.collect.Lists;
import com.zj.bda.web.grace.converter.DateConverter;
import com.zj.bda.web.grace.interceptor.HttpInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Dongguabai
 * @date 2018-07-04 21:39
 */
@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new DateConverter());
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new HttpInterceptor()).addPathPatterns("/**").excludePathPatterns(Lists.newArrayList("/assets/**","/**/*.html","/druid/**"));
        //registry.addInterceptor(new HttpInterceptor()).addPathPatterns("/**").excludePathPatterns(Lists.newArrayList("/css/**", "/img/**", "/js/**","/**/*.html"));
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry){
        registry.addViewController("/").setViewName("forward:/index.html");
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
    }

}