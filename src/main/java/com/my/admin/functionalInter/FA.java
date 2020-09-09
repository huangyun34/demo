package com.my.admin.functionalInter;

//函数式接口
@FunctionalInterface
public interface FA {

    //抽象方法，有且仅有一个
    void say(int i);

    default void say1() {
        System.out.println("default");
    }

    static void say5(){
        System.out.println("static");
    }
}
