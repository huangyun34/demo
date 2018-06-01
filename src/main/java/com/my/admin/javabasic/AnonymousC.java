package com.my.admin.javabasic;

public class AnonymousC {

    private AI dd(){
        return new AI() {
            @Override
            public String dd() {
                return null;
            }
        };
    }

    private AbstractSub ddd(){
        return new AbstractSub();
    }

    private AbstractT1 dddd(){
        return new AbstractT1() {
            String bd = "";
            @Override
            public void c() {
//                super.c();
            }
        };
    }
}
