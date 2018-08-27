package com.zj.bda.proxy;

import org.junit.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author Dongguabai
 * @date 2018/8/26 21:29
 */
public class ProxyTest {

    //静态代理的使用
    @Test
    public void test01(){
        //创建目标对象
        ITarget target = new TargetImpl();
        //创建代理
        ITarget targetProxy = new TargetProxy(target);
        //通过代理调用目标
        targetProxy.show();

        System.out.println(target);
        System.out.println(targetProxy);
    }

    //动态代理的使用
    @Test
    public void test02(){
        //创建目标对象
        ITarget target = new TargetImpl();
        //使用Proxy的newInstance方法来创建代理对象
        ITarget targetProxy = (ITarget) Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                return method.invoke(target, args);
            }
        });
        System.out.println(target);
        System.out.println(targetProxy);
        //通过代理调用目标
        targetProxy.show();
    }
    //动态代理的使用
    @Test
    public void test03(){
        //创建目标对象
        ITarget target = new TargetImpl();
        //使用Proxy的newInstance方法来创建代理对象
       JDKDynamicProxybuilder<ITarget> proxybuilder = new JDKDynamicProxybuilder<>(target);
        ITarget targetProxy = proxybuilder.buildProxy();
       /* //通过代理调用目标
        targetProxy.show();*/
        System.out.println(target);
        System.out.println(targetProxy);
    }


    @Test
    public void test1(){
        //1.创建目标
        ITarget target = new TargetImpl();

        //2.通过ProxyBuilder工具创建一个代理对象
        ProxyBuilder pb=new ProxyBuilder(target);

        ITarget proxy = (ITarget) pb.createProxy();
    }

    @Test
    public void testMapper(){
    }

}
