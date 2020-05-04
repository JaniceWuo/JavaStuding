package com.bjsxt.interClass;

public class Outer1 {
    private int age = 24;
    private void show(){
        System.out.println("要你好看");
    }

    //内部类
    public class Inner1{
        private int age = 18;
        public void showInner(){
            System.out.println("Inner.show");
            System.out.println(age); //调用的是自己的age
            System.out.println(Outer1.this.age);  //24 可以通过外部类.this.属性 调用外部类
            show();  //内部类可以直接调用外部类的方法
        }
    }

    public static void main(String[] args) {
        Outer1.Inner1 inner1 = new Outer1().new Inner1();
        inner1.showInner();

    }
}
