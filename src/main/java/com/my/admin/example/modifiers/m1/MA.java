package com.my.admin.example.modifiers.m1;

public class MA {//public修饰顶层类

    public void m(){
        MA2 ma2 = new MA2();//在顶层类中初始化MA2
        MA3 ma3 = new MA3();//在顶层类中初始化MA3
        MA4 ma4 = new MA4();//在顶层类中初始化MA4
    }

    public class MA1 {//public修饰的成员类

    }

    private class MA2 {//private修饰的成员类

    }

    protected class MA3 {//protected修饰的成员类

    }

    public class MA5 extends MA3 {//protected修饰的成员类

    }

    class MA4 {//缺省值修饰的成员类

    }
}
