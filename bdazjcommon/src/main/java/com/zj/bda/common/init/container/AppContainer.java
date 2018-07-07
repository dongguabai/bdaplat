package com.zj.bda.common.init.container;

import com.zj.bda.common.init.InitExpand;
import com.zj.bda.common.unspecific.util.ClassScanUtil;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Dongguabai
 * @date 2018-07-02 23:18
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class AppContainer {
    public static Set<Class<?>> all_classes_container = new HashSet<>();
    static Set<Class<?>> init_expand_class_container = new HashSet<>();

    static {
        all_classes_container = ClassScanUtil.getClasses("com.zj.bda");
        initModuleClasses();
    }

    private static void initModuleClasses(){
        all_classes_container.forEach(clazz->{
            if (InitExpand.class.isAssignableFrom(clazz)){
                init_expand_class_container.add(clazz);
            }
        });
        init_expand_class_container.remove(InitExpand.class);
    }
}
