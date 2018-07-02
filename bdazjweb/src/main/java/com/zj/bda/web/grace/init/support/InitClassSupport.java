package com.zj.bda.web.grace.init.support;

import com.zj.bda.common.init.InitExpand;
import com.zj.bda.common.unspecific.util.ClassScanUtil;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Dongguabai
 * @date 2018-07-01 14:49
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class InitClassSupport {

    public static Set<Class<?>> all_classs = new HashSet<>();
    public static Set<Class<?>> init_expand_class = new HashSet<>();

    private static final Class<InitExpand> INIT_EXPAND_CLASS = InitExpand.class;

    static {
        all_classs = ClassScanUtil.getClasses("com.zj.bda");
        initModuleClasses();
    }

    private static void initModuleClasses(){
        all_classs.forEach(clazz->{
            if (INIT_EXPAND_CLASS.isAssignableFrom(clazz)){
                init_expand_class.add(clazz);
            }
        });
        init_expand_class.remove(INIT_EXPAND_CLASS);
    }

}
