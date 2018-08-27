package com.zj.bda.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 代理对象构造类
 * @author Dongguabai
 * @date 2018/8/26 22:08
 */
public class JDKDynamicProxybuilder<T> implements InvocationHandler{

    private T target;

    public JDKDynamicProxybuilder(T target) {
        this.target = target;
    }

    //创建代理对象
    public T buildProxy(){
        return (T) Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(),this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return method.invoke(target,args);
    }
}
