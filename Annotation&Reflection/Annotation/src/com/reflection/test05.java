package com.reflection;

//测试类什么时候会初始化
public class test05 {
    static {
        System.out.println("Main类被加载");
    }

    public static void main(String[] args) throws ClassNotFoundException {
        //主动引用
//        Son son = new Son();

        //反射也会产生主动引用
//        Class.forName("com.reflection.Son");

        //不会产生类的引用的方法
//        System.out.println(Son.b);
        Son[] arr = new Son[5];
//        System.out.println(Son.M);
    }
}

class Father{
    static int b = 2;
    static {
        System.out.println("父类被加载");
    }
}

class Son extends Father{
    static {
        System.out.println("子类被加载");
        m = 300;
    }
    static int m = 100;
    static final int M = 1;
}