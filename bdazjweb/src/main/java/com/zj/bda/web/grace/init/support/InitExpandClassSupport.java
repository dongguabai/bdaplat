package com.zj.bda.web.grace.init.support;

import java.util.Set;

/**
 * @author Dongguabai
 * @date 2018-07-01 13:30
 */
public class InitExpandClassSupport {
    public static Set<Class<?>> getInitExpandImplements() {
       return InitClassSupport.init_expand_class;
    }

    private InitExpandClassSupport() {
    }
}
