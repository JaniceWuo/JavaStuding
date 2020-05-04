package com.bjsxt.testInterface;

public class SuperMan implements Volant,Honest {
    @Override
    public void fly() {
        System.out.println("横着飞");
    }

    @Override
    public void stop() {
        System.out.println("竖着停");
    }

    @Override
    public void helpOther() {
        System.out.println("哪里call我飞哪里");
    }

    public static void main(String[] args) {
        SuperMan man1 = new SuperMan();
        man1.fly();
        man1.stop();
        man1.helpOther();

        /**
         * 强制转换法
         */
        Volant m1 = new SuperMan();
        m1.fly();
        m1.stop();
        Honest h1 = (Honest) m1;
        h1.helpOther();
    }
}
