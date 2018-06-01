package com.my.admin.annotation.inheritedannotation;

import java.util.Arrays;

@BTable
public class Sub extends Super {
    private int subX;

    public int subY;

    private Sub() {
    }

    public Sub(int i) {
    }

    private int subX(){
        return 0;
    }

    public int subY(){
        return 0;
    }

    public static void main(String[] args) {
        Class<Sub> clazz = Sub.class;
        System.out.println("===========Field===========");
        System.out.println(Arrays.toString(clazz.getFields()));
        System.out.println(Arrays.toString(clazz.getDeclaredFields()));
        System.out.println("===========Method===========");
        System.out.println(Arrays.toString(clazz.getMethods()));
        System.out.println(Arrays.toString(clazz.getDeclaredMethods()));
        System.out.println("===========Constructor===========");
        System.out.println(Arrays.toString(clazz.getConstructors()));
        System.out.println(Arrays.toString(clazz.getDeclaredConstructors()));
        System.out.println("===========AnnotatedElement===========");
        //注解DBTable2是否存在于元素上
        System.out.println(clazz.isAnnotationPresent(BTable.class));
        //如果存在该元素的指定类型的注释DBTable2，则返回这些注释，否则返回 null。
        System.out.println(clazz.getAnnotation(BTable.class));
        //继承
        System.out.println(Arrays.toString(clazz.getAnnotations()));
        System.out.println(Arrays.toString(clazz.getDeclaredAnnotations()));  ////自身
    }
}
