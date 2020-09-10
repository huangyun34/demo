package com.my.admin.observerdemo;

import java.util.Objects;

public class OwnObserverPeople implements Observer {

    private String name;

    public OwnObserverPeople() {
    }

    public OwnObserverPeople(String name) {
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
        OwnObserverPeople that = (OwnObserverPeople) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name);
    }

    @Override
    public void see() {
        System.out.println(this.name + "观察到了！！！");
    }
}
