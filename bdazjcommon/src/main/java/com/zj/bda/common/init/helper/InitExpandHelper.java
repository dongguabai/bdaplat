package com.zj.bda.common.init.helper;

import com.zj.bda.common.init.InitExpand;
import com.zj.bda.common.unspecific.util.SpringUtil;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 初始化接口
 *
 * @author Dongguabai
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class InitExpandHelper {
   /*
    @Autowired
    private Map<String,InitExpand> initExpandMap;*/

    public static void execute() {
        SpringUtil.getBeansOfType(InitExpand.class).values().forEach(initExpand -> {
            initExpand.init();
        });
    }

}
