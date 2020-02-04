package com.zj.bda.web.grace.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 *  多个Interceptor，然后SpringMVC会根据声明的前后顺序一个接一个的执行，而且所有的Interceptor中的preHandle方法都会在
 *      * Controller方法调用之前调用。SpringMVC的这种Interceptor链式结构也是可以进行中断的，这种中断方式是令preHandle的返
 *      * 回值为false，当preHandle的返回值为false的时候整个请求就结束了。

postHandle
 这个方法只会在当前这个Interceptor的preHandle方法返回值为true的时候才会执行。postHandle是进行处理器拦截用的，它的执行时间是在处理器进行处理之
      * 后，也就是在Controller的方法调用之后执行，但是它会在DispatcherServlet进行视图的渲染之前执行，也就是说在这个方法中你可以对ModelAndView进行操
      * 作。这个方法的链式结构跟正常访问的方向是相反的，也就是说先声明的Interceptor拦截器该方法反而会后调用，这跟Struts2里面的拦截器的执行过程有点像，
 *
 * @author Dongguabai
 * @date 2018-07-01 13:30
 */
@Slf4j
public class HttpInterceptor implements HandlerInterceptor{

    /**
     * 在请求处理之前进行调用（Controller方法调用之前）
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //如果是SpringMVC请求
        if(handler instanceof HandlerMethod){
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            log.info("当前拦截的方法为：{}",handlerMethod.getMethod().getName());
            log.info("当前拦截的方法参数长度为：{}",handlerMethod.getMethod().getParameters().length);
            log.info("当前拦截的方法为：{}",handlerMethod.getBean().getClass().getName());
            System.out.println("开始拦截---------");
            String uri = request.getRequestURI();
            System.out.println("拦截的uri："+uri);
        }
        return true;

    }

    /**
     * 请求处理之后进行调用，但是在视图被渲染之前（Controller方法调用之后），不一定会触发
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
        System.out.println("请求完成后---------");
    }

    /**
     * 在整个请求结束之后被调用，也就是在DispatcherServlet 渲染了对应的视图之后执行
     * （主要是用于进行资源清理工作），肯定会触发
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
        ex.printStackTrace();
        System.out.println("afterCompletion。。。。。。。。。");
        log.info("Exception：{}",ex);
    }

}
