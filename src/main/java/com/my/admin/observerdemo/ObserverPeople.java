package com.my.admin.observerdemo;

import java.util.Objects;
import java.util.Observable;
import java.util.Observer;

public class ObserverPeople implements Observer {

    private String name;

    public ObserverPeople() {
    }

    public ObserverPeople(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ObserverPeople that = (ObserverPeople) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public void update(Observable o, Object arg) {
        System.out.println(this.name + "观察到了！！！");
    }
}
