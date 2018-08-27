package com.zj.bda.proxy;

/**
 * 代理角色
 * @author Dongguabai
 * @date 2018/8/26 21:32
 */
//代理角色实现抽象角色或者持有抽象角色的引用，这里实现抽象角色
public class TargetProxy implements ITarget{

    private ITarget target;

    public TargetProxy(ITarget target) {
        this.target = target;
    }

    @Override
    public void show() {
        System.out.println("业务方法执行前------");
        target.show();
        System.out.println("业务方法执行后------");
    }
}
