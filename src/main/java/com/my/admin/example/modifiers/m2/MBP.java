package com.my.admin.example.modifiers.m2;

public class MBP {

    public void m(){
        new MB();//缺失值修饰的顶层类，可以在同包的类中调用
    }
}
