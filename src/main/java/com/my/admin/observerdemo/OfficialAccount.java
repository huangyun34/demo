package com.my.admin.observerdemo;

import java.util.Observable;

/**
 * 被观察对象
 */
public class OfficialAccount extends Observable {

    public void push() {
        //设置有改变
        setChanged();
        //通知观察者
        notifyObservers();
    }
}
