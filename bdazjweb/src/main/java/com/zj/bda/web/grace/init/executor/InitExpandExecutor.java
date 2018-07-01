package com.zj.bda.web.grace.init.executor;

import com.zj.bda.common.unspecific.util.ReflectUtil;
import com.zj.bda.web.grace.init.support.InitExpandClassSupport;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Set;

/**
 * 执行初始化那个接口的方法
 * 
 * @author Dongguabai
 *
 */
@Slf4j
public class InitExpandExecutor {
	
	public static void execute() {
		Set<Class<?>> classes = InitExpandClassSupport.getInitExpandImplements();
		try {
			for (Class<?> clazz : classes) {
				Method method = ReflectUtil.getMethod(clazz, "init");
				method.setAccessible(false);
				method.invoke(clazz.newInstance());
			}
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| InstantiationException e) {
			log.error("init invoke method is error",e);
			throw new RuntimeException(e);
		}
	}

}
