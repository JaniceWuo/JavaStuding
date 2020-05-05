package TestLambda;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TestLambda {
    public static void main(String[] args) {
        Random r = new Random();
        List<Hero> heros = new ArrayList<Hero>();
        for(int i = 0 ; i<10;i++){
            heros.add(new Hero("hero"+i,r.nextInt(100),r.nextInt(10)));
        }
//        System.out.println("初始化集合："+heros);
//        HeroChecker heroChecker = new HeroChecker() {
//            @Override
//            public boolean test(Hero h) {
//                return (h.hp>50&&h.damage<8);
//            }
//        };
//        filter(heros,heroChecker);
        //lambda：
        filter(heros,h->h.hp>50&&h.damage<8);
        System.out.println("在lambda中使用静态方法");
        filter(heros,h->testHero(h));
        System.out.println("直接引用静态方法");
        filter(heros,TestLambda::testHero);
    }

    public static boolean testHero(Hero h){
        return h.hp>50 && h.damage<8;

    }
    private static void filter(List<Hero> heros,HeroChecker checker){
        for(Hero hero:heros){
            if(checker.test(hero)) System.out.println(hero);
        }
    }

}
