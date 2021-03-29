package com.my.proxy;

import java.lang.reflect.Proxy;

public class RabbitTest {

    public static void main(String[] args) {
        Rabbit rabbit = (Rabbit) Proxy.newProxyInstance(RabbitTest.class.getClassLoader(), new Class[]{Rabbit.class}, new CaiTwo(new HuaSan(), new FeiSi()));
        rabbit.eat();
        rabbit.hh();

    }
}
