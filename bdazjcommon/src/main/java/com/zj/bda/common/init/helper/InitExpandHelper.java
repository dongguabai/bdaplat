package com.zj.bda.common.init.helper;

import com.zj.bda.common.init.IInitExpand;
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
   /*    本类需为Spring组件，且无法使用静态
    @Autowired
    private Map<String,IInitExpand> initExpandMap;*/

    public static void execute() {
        SpringUtil.getBeansOfType(IInitExpand.class).values().forEach(IInitExpand -> {
            IInitExpand.init();
        });
    }

}
