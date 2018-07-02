package com.zj.bda.common.init.container;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Set;

/**
 * @author Dongguabai
 * @date 2018-07-01 13:30
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class InitExpandClassContainer {
    public static Set<Class<?>> getInitExpandImplements() {
       return AppContainer.init_expand_class_container;
    }

}
