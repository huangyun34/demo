package com.my.admin.blog;

public class Child extends Parent {

    public String name = "大头儿子";

    /**
     * 当子类和父类有属性重名时，需要super才能调用到父类的属性，
     * 直接调用会调用到子类的属性
     * 如果不重名，可直接调用且调用的是父类的属性
     */
    private void testParam(){
        System.out.println("爸爸的名字叫" + super.name);
        System.out.println("孩子的名字是" + name);
        System.out.println("爸爸是否有有钱：" + hasMoney);
    }

    /**
     * 方法和上面的属性结果一样
     */
    public void testMethod(){
        sleeping();
        super.sleeping();
        super.working();
    }

    public void sleeping(){
        System.out.println("儿子已经睡了");
    }

    public static void main(String[] args) {
        Child child = new Child();
        child.testParam();
        child.testMethod();
    }
}
