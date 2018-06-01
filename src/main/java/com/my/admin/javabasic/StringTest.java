package com.my.admin.javabasic;

import java.util.Date;

public class StringTest {

    public static void main(String[] args) {

//        String s1 = "aa";
//        String s2 = "a" + "a";
//        String s3 = "a";
//        String s4 = "a";
//        String s5 = s3 + s4;
//        String s6 = new String("a");
//        String s7 = new String("aa");
//        String s8 = new String("aa");
//        System.out.println(s1 == s2);
//        System.out.println(s3 == s4);
//        System.out.println(s5 == s1);
//        System.out.println(s6 == s3);
//        System.out.println(s7 == s1);
//        System.out.println(s8 == s7);
//        String s1 = new String("abc") + new String("1");//主动创建一个String对象，未在编译期字符串池保存字符串
//        String s2 = "abc";//在编译期字符串池中保存了字符串，并给了它一个引用
//        String s11 = s1.intern();//s1调用intern方法，发现字符串池中以后abc，那么s2的引用
//        String s2 = "abc";
//        System.out.println(s11==s1);//所以这个返回true，因为s11是直接拿的s2的引用
//        System.out.println(s1.intern()==s1);//所以这个返回true，因为s11是直接拿的s2的引用
//        System.out.println(s1==s2);//为什么这个是false呢，因为在s1是人为主动new的，那么它的对象是不在编译期字符串池中的，那么它的引用一定和s2的引用不同，因为

//        String s1 = new String("1") + new String("2");
//        String s = "12";
//        String s2 = s1.intern();
//        String s3 = s1.intern();
//        System.out.println(s1 == s2);
//        System.out.println(s1 == s2);
//        System.out.println(s1 == s3);
//        System.out.println(s2 == s3);

//        String s1 = new String("123");
//        String s2 = new String("123");
//        String s3 = s1.intern();
//        s1 = null;
//        String s4 = s2.intern();
//        System.out.println(s3 == s4);
//        int i = 0;
        String s = "123";
//        while (true) {
//            String b = new String(String.valueOf(i));
//            s.intern();
//            String b = "123";
//            i++;
//        }
//        long ds = new Date().getTime();
//        for (int i = 0; i < 10000000; i++) {
//            String b = s.intern();
////            if (b=="123");
////            if ("123"=="123");
//        }
//        System.out.println(new Date().getTime() - ds);
//        long ds1 = new Date().getTime();
//        for (int i = 0; i < 10000000; i++) {
//            String b = s;
////            if (b.equals("123"));
////            if ("123".equals("123"));
//        }
//        System.out.println(new Date().getTime() - ds1);
//        System.out.println(s);

        String s3 = new String("abc") + new String("01");
        //第一句话，在堆非常量池中建立了3个对象，一个abc，一个01，一个"abc01"
        String s4 = new String("abc") + new String("01");
        //第二句话，在堆非常量池中建立了3个对象，一个abc，一个01，一个"abc01"

        System.out.println(s3==s4);//false 因为s3,s4指向的对象不同
        System.out.println(s4.intern()==s4);//true 因为还没有"abc01"，所以s4.intern()把s4复制一份给了常量池，然后s4指向常量池的引用
        System.out.println(s3.intern()==s4);//true 因为有了"abc01"，s3.intern()直接从常量池中拿出对象，那它的引用是常量池对象的引用，所以他就和s4的引用一样啦
        System.out.println(s3.intern()==s3);
    }
}
