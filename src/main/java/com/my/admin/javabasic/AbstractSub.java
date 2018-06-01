package com.my.admin.javabasic;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class AbstractSub extends AbstractT1 {
    private double d = 2d;

    public static void main(String[] args) throws FileNotFoundException {
        AbstractSub abstractSub = new AbstractSub();
        Properties ds = System.getProperties();
        ds.list(new PrintStream(new FileOutputStream("/Users/huangyun/Desktop/data/java基础/123.txt")));
    }
}
