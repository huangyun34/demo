package com.my.admin.some.assertA;

import com.my.admin.javabasic.A;
import com.my.admin.javabasic.B;
import com.my.admin.javabasic.C;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.function.Supplier;

public class AssertTest {
    public static void main(String[] args) {
        /**
         * 断言对象是否为null，为null 则报错
         */
//        Assert.notNull(null, "报错啦");
//        Supplier<String> re = new Supplier<String>() {
//            @Override
//            public String get() {
//                return "你能不要再错了吗？";
//            }
//        };
//        Supplier<String> re = () -> "你能不要再错了吗？";
//        Assert.notNull(null, re);

        /**
         * 断言是否包括某字符串，包含 则报错
         */
//        Assert.doesNotContain("1234567890","123", "包含了123");
//        Assert.doesNotContain("1234567890","235",
//                () -> "包含了235");
        /**
         * 断言是否字符串为空，字符串为null或"" 则报错
         */
//        Assert.hasLength("123", "是空字符串");
//        Assert.hasLength(" ",
//                () -> "非空字符串");
        /**
         * 断言是否字符串为空，字符串为null、""或"    " 则报错
         */
//        Assert.hasText("   ", "是空字符串或空白字符串");
//        Assert.hasText(" ",
//                () -> "是空字符串或空白字符串");

        /**
         * 断言是否可以前者是不是后者的超类，不是 则报错
         */
        Assert.isAssignable(A.class, C.class);
//        Assert.isAssignable(A.class, B.class,
//                () -> "前者不是后者的超类");
//        Assert.isAssignable(A.class, B.class, "前者不是后者的超类");
        /**
         * 断言第二个参数是否就是该类的实例，不是 则报错
         */
//        Assert.isInstanceOf(String.class, "");
//        Assert.isInstanceOf(String.class, "", "不是对应实例");
//        Assert.isInstanceOf(String.class, "",
//                () -> "不是对应实例");
        /**
         * 断言对象是否为null，不是 则报错
         */
//        Assert.isNull(null, "对象不为 null");
//        Assert.isNull(null, () -> "对象不为 null");
        /**
         * 断言对象是否为true，不是 则报错
         */
//        Assert.isTrue(true, "不为true");
//        Assert.isTrue(false, () -> "不为true");

        /**
         * 断言数组是否有null元素，有 则报错
         */
//        Assert.noNullElements(new String[]{"1"}, "数组中有null值");
//        Assert.noNullElements(new String[]{null, ""}, () -> "数组中有null值");

        /**
         * 断言数组、集合、map是否为null或size or length 为0，为null或size or length 为0 则报错
         */
//        HashMap<Object, Object> map = new HashMap<>();
//        map.put(1, 1);
//        ArrayList<Object> list = new ArrayList<>();
//        list.add("123");
//        HashSet<Object> set = new HashSet<>();
//        set.add("23");
//        String[] array = new String[]{"1"};
//        Assert.notEmpty(map, "map为空");
//        Assert.notEmpty(map, () -> "map为空");
//        Assert.notEmpty(list, "list为空");
//        Assert.notEmpty(list, () -> "list为空");
//        Assert.notEmpty(set, "set为空");
//        Assert.notEmpty(set, () -> "set为空");
//        Assert.notEmpty(array, "数组为空");
//        Assert.notEmpty(array, () -> "数组为空");
        /**
         * 断言表达式是否为true，false 则报错
         */
//        Assert.state(1 > 0, "错误");
//        Assert.state(1 > 0, () -> "错误");

    }
}
