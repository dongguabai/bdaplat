package com.zj.bda.common.init.listener;

import com.zj.bda.common.init.helper.InitExpandHelper;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
/**
 * @author Dongguabai
 * @date 2018-07-01 13:30
 * 监听 ContextRefreshedEvent 事件
 */
public class InitApplicationContextLoaderListener implements ApplicationListener<ContextRefreshedEvent> {

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
       InitExpandHelper.execute();
    }

}
