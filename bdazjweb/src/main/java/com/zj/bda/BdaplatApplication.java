package com.zj.bda;

import com.zj.bda.web.listener.InitApplicationContextLoaderListner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.zj.bda.web", "com.zj.bda.service","com.zj.bda.common"})
public class BdaplatApplication {

    public static void main(String[] args) {

        SpringApplication bdaSpringApplication = new SpringApplication(BdaplatApplication.class);
        bdaSpringApplication.addListeners(new InitApplicationContextLoaderListner());
        bdaSpringApplication.run(args);

       // SpringApplication.run(BdaplatApplication.class, args);
    }
}
