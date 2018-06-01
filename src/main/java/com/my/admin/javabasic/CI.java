package com.my.admin.javabasic;


public interface CI extends BI, AI {

    String dd();

    default String cc(){
        return null;
    }
}
