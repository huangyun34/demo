package com.my.admin.example.modifiers.m1;

public class MAP {

    public void m(){
        MA ma = new MA();//public修饰的顶层类，可以在同包的类中调用
        ma.new MA1();//public修饰的成员类，可以在同包的类中调用
        ma.new MA3();//protected修饰的成员类，可以在同包的类中调用
        ma.new MA4();//缺省值修饰的成员类，可以在同包的类中调用
//        ma.new MA2();//FIXME private修饰的成员类，不可以在同包的类中调用
    }
}
