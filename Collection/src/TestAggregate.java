import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TestAggregate {
    public static void main(String[] args) {
        Random r = new Random();
//        System.out.println(r.nextInt(10));
        List<Hero> heros = new ArrayList<>();
        for(int i = 0;i<10;i++){
            heros.add(new Hero("hero"+i,r.nextInt(1000),r.nextInt(100)));
        }
        System.out.println(heros);
        //聚合方式
        String name = heros.stream().sorted((h1,h2)->h1.hp>h2.hp?-1:1).skip(2).map(h->h.name).findFirst().get();
        System.out.println(name);
    }

}
