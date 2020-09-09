package com.my.admin.functionalInter;

import java.util.ArrayList;
import java.util.List;

public class FATest {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(111);
        list.add(13);
        list.add(122);
        FACI faci = new FACI(list);
        faci.hh((i) -> {        //lambda表达式
            System.out.println(i);
        });
    }
}
