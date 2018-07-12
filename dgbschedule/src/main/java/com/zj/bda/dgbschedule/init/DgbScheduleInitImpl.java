package com.zj.bda.dgbschedule.init;

import com.zj.bda.common.init.IInitExpand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author Dongguabai
 * @date 2018-07-12 23:27
 */
@Slf4j
@Component
public class DgbScheduleInitImpl implements IInitExpand{
    @Override
    public void init() {
        log.info("调度模块初始化--------");
    }
}
