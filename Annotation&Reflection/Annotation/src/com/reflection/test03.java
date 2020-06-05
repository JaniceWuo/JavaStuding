package com.reflection;


import java.lang.annotation.ElementType;

//所有类型的Class
public class test03 {
    public static void main(String[] args) {
        Class c1 = Object.class;
        Class c2 = Override.class;
        Class c3 = ElementType.class; //枚举类型
        Class c4 = Comparable.class;  //接口类型

        System.out.println(c1);
        System.out.println(c2);
        System.out.println(c3);
        System.out.println(c4);

        int[] a = new int[10];
        int[] b = new int[100];
        System.out.println(a.getClass());
    }
}
