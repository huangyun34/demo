package com.my.admin.observerdemo;

import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

public class Observed {

    private List<OwnObserverPeople> list = new ArrayList<>();

    protected void add(OwnObserverPeople ownObserverPeople) {
        list.add(ownObserverPeople);
    }

    /**
     * 通知观察者
     */
    protected void notifyObserver() {
        if (!CollectionUtils.isEmpty(list)) {
            for (OwnObserverPeople ownObserverPeople : list) {
                ownObserverPeople.see();
            }
        }
    }
}
