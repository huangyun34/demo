package com.my.admin.some.assertA;

import java.util.*;

public abstract class ABA {

    public static String s2 = "123";
    public static void sout(String s){
        System.out.println(s);
//        Arrays.asList(1).stream().forEach(i -> {
//
//        });
    }
    public static void abc(String s){
//        System.out.println("12321");
//        return;
    }

    public abstract void ss();

    public static void main(String[] args) {
        int[] a1 = {1,2,3,4};
        int[] a2 = {1,2,3,4};
        System.out.println(Objects.deepEquals(a1, a2));
//        System.out.println(Objects.equals(null, ""));
    }
}
