package com.my.admin.observerdemo;

public class ObservedAccount extends Observed {

    public ObservedAccount() {
    }

    public void push() {
        notifyObserver();
    }
}
