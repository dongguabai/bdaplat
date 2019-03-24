package com.zj.bda.web.grace;

import com.google.common.collect.Lists;
import com.zj.bda.web.grace.converter.DateConverter;
import com.zj.bda.web.grace.interceptor.HttpInterceptor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import wm.dgb.security.support.safe.xss.XssFilter;

import javax.annotation.Resource;

/**
 * @author Dongguabai
 * @date 2018-07-04 21:39
 */
@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {

    @Resource(name = "mvcAsyncExecutor")
    private AsyncTaskExecutor mvcAsyncExecutor;

    /**
     * 在项目开发过程中，经常会涉及页面跳转问题，而且这个页面跳转没有任何业务逻辑过程，只是单纯的路由过程 ( 点击一个按钮跳转到一个页面 ) 。
     * @param registry
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("forward:/index2.html");
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new DateConverter());
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //Spring 5.0之后静态文件会走自定义拦截器
        registry.addInterceptor(new HttpInterceptor()).addPathPatterns("/**").excludePathPatterns(Lists.newArrayList("/assets/**", "/**/*.html", "/druid/**"));
        //registry.addInterceptor(new HttpInterceptor()).addPathPatterns("/**").excludePathPatterns(Lists.newArrayList("/css/**", "/img/**", "/js/**","/**/*.html"));
    }

    @Override
    public void configureAsyncSupport(AsyncSupportConfigurer configurer) {
        configurer.setTaskExecutor(mvcAsyncExecutor);
    }

    /**
     * xss过滤拦截器
     */
    @Bean
    public FilterRegistrationBean xssFilterRegistrationBean() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new XssFilter());
        filterRegistrationBean.setOrder(1);
        filterRegistrationBean.setEnabled(true);
        filterRegistrationBean.addUrlPatterns("/*");
        return filterRegistrationBean;
    }

}
