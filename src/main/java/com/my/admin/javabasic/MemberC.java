package com.my.admin.javabasic;

public class MemberC {

    private static int i = 1;
    private int i1 = 1;

    private static int i(){
        return 1;
    }

    private int i1(){
        return 2;
    }

    class m {
        private String s = "";

        private String s(){
            i++;
            i();
            i1++;
            i1();
            return null;
        }
    }

    class m2 {
        private String i = "";
        private String i1 = "";

        private String i(){
            i = "2";
            MemberC.this.i1++;
            i();
            MemberC.i();
            return null;
        }

        private String i1(){
            return null;
        }
    }

    static class m1 {
        private String s = "";

        private String s(){
            i++;
            i();
            return null;
        }
        private static String ss = "";

        private static String ss(){
            return null;
        }
    }
}
