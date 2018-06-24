package com.zj.bda;

import com.zj.bda.common.listener.InitApplicationContextLoaderListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.zj.bda"})
@MapperScan(basePackages = "com.zj.bda.persistence.mapper")
//@ServletComponentScan
@EnableAsync  //class @Component   method @Async
@EnableScheduling
//@EnableAspectJAutoProxy(exposeProxy = true)
public class BdaplatApplication {

    public static void main(String[] args) {

        SpringApplication bdaSpringApplication = new SpringApplication(BdaplatApplication.class);
        bdaSpringApplication.addListeners(new InitApplicationContextLoaderListener());
        bdaSpringApplication.run(args);

       // SpringApplication.run(BdaplatApplication.class, args);
    }
}
