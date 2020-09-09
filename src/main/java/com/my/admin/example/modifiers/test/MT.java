package com.my.admin.example.modifiers.test;

import com.my.admin.example.modifiers.m1.MA;

public class MT {

    public void m(){
        MA ma = new MA();//public修饰的顶层类，可以在不同包的类中调用
        ma.new MA1();//public修饰的成员类，可以在不同包的类中调用
//        ma.new MA3();//FIXME protected修饰的成员类，不可以在不同包的类中调用
//        ma.new MA4();//FIXME 缺省值修饰的成员类，不可以在不同包的类中调用
//        ma.new MA2();//FIXME private修饰的成员类，不可以在不同包的类中调用
//        MB mb = new MB();//FIXME 缺省值修饰的顶层类，不可以在不同包的类中调用
    }
}
