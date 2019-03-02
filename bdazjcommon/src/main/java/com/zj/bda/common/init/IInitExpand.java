package com.zj.bda.common.init;

/**
 * 实现此接口，即可在Springboot容器初始化时执行init方法，后续如果有顺序控制要求，可以增加order方法，将int与Class封装成一个pojo
 *
 * 实现类必须注册为Spring的组件
 * @author Dongguabai
 * @date 2018-07-01 12:48
 */
public interface IInitExpand {

    /**
     * 实现此方法，在项目启动时初始化
     */
    void init();

//  public int order();

}
