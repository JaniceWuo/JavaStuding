package com.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class test09 {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        Class c1 = Class.forName("com.reflection.User");
        //构造一个对象
//        User user = (User) c1.newInstance();  //本质是调用了类的无参构造器  如果删掉无参构造器就会报错
//        System.out.println(user);

        //通过构造器创建对象   可以不要无参构造器
//        try {
//            Constructor constructor = c1.getDeclaredConstructor(String.class,int.class,int.class);
//            User user1 = (User)constructor.newInstance("janice",1,20);
//            System.out.println(user1);
//        } catch (NoSuchMethodException e) {
//            e.printStackTrace();
//        } catch (InvocationTargetException e) {
//            e.printStackTrace();
//        }

        //通过反射调用普通方法   需要类中有无参构造器
        User user2 = (User) c1.newInstance();
        Method setName = c1.getDeclaredMethod("setName", String.class);
        setName.invoke(user2,"小红");
        System.out.println(user2.getName());

        //通过反射操作属性
        User user3 = (User) c1.newInstance();
        try {
            Field name = c1.getDeclaredField("name");
            name.setAccessible(true);  //不能直接操作私有属性  要把安全检测关掉
            name.set(user3,"小蓝");
            System.out.println(user3.getName());
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

    }
}
