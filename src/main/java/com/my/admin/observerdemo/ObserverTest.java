package com.my.admin.observerdemo;

public class ObserverTest {

    public static void main(String[] args) {
//        OfficialAccount officialAccount = new OfficialAccount();
//        ObserverPeople o1 = new ObserverPeople("1");
//        ObserverPeople o2 = new ObserverPeople("2");
//        officialAccount.addObserver(o1);
//        officialAccount.addObserver(o2);
//
//        officialAccount.push();

        ObservedAccount observedAccount = new ObservedAccount();
        OwnObserverPeople ownObserverPeople = new OwnObserverPeople("哈哈");
        OwnObserverPeople ownObserverPeople1 = new OwnObserverPeople("嘿嘿");
        observedAccount.add(ownObserverPeople);
        observedAccount.add(ownObserverPeople1);
        observedAccount.push();
    }
}
