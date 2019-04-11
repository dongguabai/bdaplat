package com.zj.bda.web.controller;

import com.zj.bda.common.util.SpringUtil;
import com.zj.bda.service.TestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @author dongguabai
 * @date 2019-04-07 01:07
 */
@Slf4j
@Controller
@Lazy(false)
public class EmailController {


    @RequestMapping(value = "/email/asyncCall", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> asyncCall() throws InterruptedException {

        Map<String, Object> resMap = new HashMap<String, Object>();

        EmailController emailController = (EmailController) SpringUtil.getBean(EmailController.class);
        System.out.println("22222222");
        emailController.testAsyncTask();
        System.out.println("333333333");
      // EmailController proxy = (EmailController) AopContext.currentProxy();
        //System.out.println(emailController == proxy);
        //proxy.testAsyncTask();
        System.out.println("end!");
        resMap.put("code", "success");

        return resMap;
    }


    @Async
    public void testAsyncTask() throws InterruptedException {
        Thread.sleep(10000);
        System.out.println("异步任务执行完成！");
    }

    @Autowired
    private TestService testService;

    @RequestMapping("/fl")
    @ResponseBody
    public Object fl(){
        testService.unTrancationnal();

        return "OK";

    }

}
