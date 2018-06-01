package com.my.admin.javabasic;


/**
 * 条件运算符
 */
public class ConditionalOperator {
    public static void main(String[] args) {
        int x = 5;
        byte a = 5;
        //当2个比较值不是同一数据类型时，如果有一方是byte, short, char.规则是：如果结果在byte，short，char的范围内，返回类型则取范围小的（byte，short，char）
        byte b = (x > 2) ? a : 125;
//        byte c = (x > 2) ? a : 200;    //
        //当2个比较值不是同一数据类型时，如果没有byte, short, char.规则是：返回类型取范围大的
        int d = (x > 2) ? a : 200;
    }
}
