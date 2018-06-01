package com.my.admin.javabasic;

public interface BI {

    String dd();

    default String cc(){
        return null;
    }
}
