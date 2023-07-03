package com.zw.cloud.tools.question;

public class Parent {
    public static String parentStr = "parent static string";
    private String param;
    static {
        System.out.println("parent static fields");
        System.out.println(parentStr);
    }

    public Parent(String param) {
        this.param = param;
        System.out.println("parent instance param initialization");
    }

    public Parent() {
        System.out.println("parent instance initialization");
    }
}

class Sub extends Parent {
    public static String subStr = "sub static string";

    private String param;

    static {
        System.out.println("sub static fields");
        System.out.println(subStr);
    }

    public Sub(String param) {
        this.param = param;
        System.out.println("sub instance param initialization");
    }

    public Sub() {
        System.out.println("sub instance initialization");
    }

    public static void main(String[] args) {
        System.out.println("sub main");
        //new Sub();
        /**
         * parent static fields
         * parent static string
         * sub static fields
         * sub static string
         * sub main
         * parent instance initialization
         * sub instance initialization
         */
        new Sub("A");
        /**
         * parent static fields
         * parent static string
         * sub static fields
         * sub static string
         * sub main
         * parent instance initialization
         * sub instance param initialization
         */
    }
}
