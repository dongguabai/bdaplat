package com.zj.bda.proxy;

/**
 * 接口实现---真实角色（目标对象）
 * @author Dongguabai
 * @date 2018/8/26 21:30
 */
public class TargetImpl implements ITarget{

    //业务方法
    @Override
    public void show() {
        System.out.println("真实角色执行show方法");
    }
}
