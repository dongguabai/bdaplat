package com.zj.bda.common.init.condition;

/**
 * 实现此接口，即可在Springboot容器初始化时执行init方法，后续如果有顺序控制要求，可以增加order方法，将int与Class封装成一个pojo
 *如果要使用Spring组件，则使用SpringUtil获取Spring组件
 * @author Dongguabai
 * @date 2018-07-01 12:48
 */
public interface InitExpand {

    /**
     * 实现此方法，在项目启动时初始化
     */
    void init();

//  public int order();

}