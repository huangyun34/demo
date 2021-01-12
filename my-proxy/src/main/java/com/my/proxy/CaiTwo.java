package com.my.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class CaiTwo implements InvocationHandler {

    private Rabbit rabbitH;

    private Rabbit rabbitF;

    public CaiTwo(Rabbit rabbitH, Rabbit rabbitF) {
        this.rabbitH = rabbitH;
        this.rabbitF = rabbitF;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        before();

        //被代理的对象中的方法
        method.invoke(rabbitH, null);
        method.invoke(rabbitF, null);

        after();
        return null;
    }

    private void before() {
        System.out.println("==========好大儿要吃草，蔡二准备提摩西草===========");
    }

    private void after() {
        System.out.println("==========花大儿吃完啦，蔡二搞快收拾残局，打扫为啥===========");
    }
}
