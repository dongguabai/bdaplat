package com.zj.bda.common.init.executor;

import com.zj.bda.common.init.InitExpand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 执行初始化那个接口的方法
 * 
 * @author Dongguabai
 *
 */
@Slf4j
@Component
public class InitExpandExecutor {

	@Autowired
	private Map<String,InitExpand> initExpandMap;
	
	public void execute() {
		initExpandMap.values().forEach(initExpandImpl -> initExpandImpl.init());
	}

}
