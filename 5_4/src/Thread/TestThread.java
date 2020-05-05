package Thread;

public class TestThread {
    public static void main(String[] args) {
        Hero gareen = new Hero();
        gareen.name = "盖伦";
        gareen.hp = 600*10;
        gareen.damage = 1;

        Hero teemo = new Hero();
        teemo.name = "提莫";
        teemo.hp = 300*10;
        teemo.damage = 1;

        Hero bh = new Hero();
        bh.name = "赏金猎人";
        bh.hp = 500*10;
        bh.damage = 1;

        Hero leesin = new Hero();
        leesin.name = "盲僧";
        leesin.hp = 500*10;
        leesin.damage = 1;

//        while(!teemo.isDead()){
//            gareen.attackHero(teemo);
//        }
//        while (!leesin.isDead()){
//            bh.attackHero(leesin);
//        }
          //法1 继承线程类
//        KillThread killThread1 = new KillThread(gareen,teemo);
//        killThread1.start();
//        KillThread killThread2 = new KillThread(bh,leesin);
//        killThread2.start();

        //法2： 实现Runnable接口
//        Battle battle1 = new Battle(gareen,teemo);
//        new Thread(battle1).start();
//        Battle battle2 = new Battle(bh,leesin);
//        new Thread(battle2).start();

        //法3
//        Thread t1 = new Thread(){
//            public void run(){
//                while (!teemo.isDead()) gareen.attackHero(teemo);
//            }
//        };
//        t1.start();
//        //将t1加入主线程：
//        try {
//            t1.join();
//        }catch (InterruptedException e){
//            e.printStackTrace();
//        }
//        //这样做就会等到t1执行完才会执行下面的
//        Thread t2 = new Thread(){
//          public void run(){
//              while (!leesin.isDead()) bh.attackHero(leesin);
//          }
//        };
//        t2.start();


        //观察优先级
//        t1.setPriority(Thread.MAX_PRIORITY);
//        t2.setPriority(Thread.MIN_PRIORITY);
//        t1.start();
//        t2.start();

        //临时暂停
//        Thread t2 = new Thread(){
//            public void run(){
//                while (!leesin.isDead()){
//                    Thread.yield();
//                    bh.attackHero(leesin);
//                }
//            }
//        };
//        t1.setPriority(5);
//        t2.setPriority(5);
//        t1.start();
//        t2.start();

        HeroEnergy hero = new HeroEnergy(gareen);
        hero.start();

    }
}
