package com.zj.bda;

import com.zj.bda.common.init.listener.InitApplicationContextLoaderListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author Dongguabai
 * @date 2018-06-29 19:03
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.zj.bda"})
@MapperScan(basePackages = "com.zj.bda.persistence.mapper")
//@ServletComponentScan
@EnableAsync  //class @Component   method @Async
@EnableScheduling
//@EnableAspectJAutoProxy(exposeProxy = true)  开启cglib代理，并且AopContext可以获取代理对象
public class BdaplatApplication {

    public static void main(String[] args) {

        SpringApplication bdaSpringApplication = new SpringApplication(BdaplatApplication.class);
        bdaSpringApplication.addListeners(new InitApplicationContextLoaderListener());
        bdaSpringApplication.run(args);

       // SpringApplication.run(BdaplatApplication.class, args);
    }
}
//war用
/*
public class BdaplatApplication extends SpringBootServletInitializer {

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
