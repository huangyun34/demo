package com.my.admin.annotation.inheritedannotation;

@CTable
public class Super extends SuperMan {
    private int superX;

    public int superY;

    public Super() {
    }

    private int superX(){
        return 0;
    }

    public int superY(){
        return 0;
    }
}
