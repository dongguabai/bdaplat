package com.zj.bda.dgbschedule.init;


import com.zj.bda.common.init.InitExpand;
import org.springframework.stereotype.Component;

/**
 * @author Dongguabai
 * @date 2018-07-01 13:33
 */
@Component
public class DgbScheduleInit implements InitExpand {
    @Override
    public void init() {
        System.out.println("初始化调度模块--------------");
    }

    public DgbScheduleInit() {
    }
}
