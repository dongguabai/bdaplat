package com.zj.bda.common.init.listener;

import com.zj.bda.common.init.executor.InitExpandExecutor;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
/**
 * @author Dongguabai
 * @date 2018-07-01 13:30
 */
public class InitApplicationContextLoaderListener implements ApplicationListener<ContextRefreshedEvent> {

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        InitExpandExecutor.execute();
    }

}
