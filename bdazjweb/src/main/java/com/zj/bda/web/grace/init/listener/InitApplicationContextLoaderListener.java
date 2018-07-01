package com.zj.bda.web.grace.init.listener;

import com.zj.bda.web.grace.init.executor.InitExpandExecutor;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

/**
 * Created by Dongguabai on 2018-06-14.
 */
public class InitApplicationContextLoaderListener implements ApplicationListener<ContextRefreshedEvent> {

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        InitExpandExecutor.execute();
    }

}
