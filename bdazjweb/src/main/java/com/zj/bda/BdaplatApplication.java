package com.zj.bda;

import com.zj.bda.common.init.listener.InitApplicationContextLoaderListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import tk.mybatis.spring.annotation.MapperScan;
import wm.dgb.security.grace.scan.DgbSecurityScan;

/**
 * @author Dongguabai
 * @date 2018-06-29 19:03
 */
@SpringBootApplication(scanBasePackages = {"com.zj.bda","wm.dgb.schedule"})
@DgbSecurityScan
//@ComponentScan(basePackages = {"com.zj.bda","wm.dgb"})   //上面可以替代
@PropertySource({"classpath:config/dgb-security.properties"})
@MapperScan(basePackages = {"com.zj.bda.persistence.mapper","com.zj.bda.common.concurrent.lock.support"})
//@ServletComponentScan  扫描Servlet，不需要使用
@EnableAsync  //class @Component   method @Async
@EnableScheduling
@EnableCaching  // @Cacheable(value = "localCache",unless="#result == null",key="参看"Spring缓存注解@Cacheable、@CacheEvict、@CachePut使用 - fashflying - 博客园"")    @CacheEvict(value = "localCache",key = "#user.userName",beforeInvocation = false)
//@EnableAspectJAutoProxy(exposeProxy = true) // 开启cglib代理，并且AopContext可以获取代理对象
public class BdaplatApplication{

    public static void main(String[] args) {

        SpringApplication bdaSpringApplication = new SpringApplication(BdaplatApplication.class);
        bdaSpringApplication.addListeners(new InitApplicationContextLoaderListener());
        bdaSpringApplication.run(args);

       // SpringApplication.run(BdaplatApplication.class, args);
    }
}
//war用

/*public class BdaplatApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {

        SpringApplication bdaSpringApplication = new SpringApplication(BdaplatApplication.class);
        bdaSpringApplication.addListeners(new InitApplicationContextLoaderListener());
        bdaSpringApplication.run(args);

        // SpringApplication.run(BdaplatApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        // bulider  main类
        return builder.sources(BdaplatApplication.class);
    }
}*/
