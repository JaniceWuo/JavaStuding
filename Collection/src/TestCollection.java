import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TestCollection {
    public static void main(String[] args) {
//        ArrayList heros = new ArrayList();
//        heros.add(new Hero("hero0"));
//        heros.add(new Hero("hero1"));
//        System.out.println(heros);
//        ArrayList anotherHeros = new ArrayList();
//        anotherHeros.add(new Hero("hero2"));
//        anotherHeros.add(new Hero("hero3"));
//        heros.addAll(anotherHeros);
//        System.out.println(heros);
//        for(int i = 0; i < heros.size();i++){
////            System.out.println(heros.get(i));
//            if(heros.get(i).toString().equals("hero1")){
//                System.out.println("找到"+i);
//                heros.remove(i);
//            }
//        }
//        System.out.println(heros);


        //删除元素
        List<Hero> heros = new ArrayList<>();
        for(int i = 0;i<100;i++){
            heros.add(new Hero("hero"+i));
        }
//        System.out.println(heros);
        Iterator<Hero> it = heros.iterator();
        int j = 0;
        while (it.hasNext()){
            Hero hero = it.next();
            if(j%8==0){
                it.remove();
            }
            j+=1;
        }
        System.out.println(heros);
    }

}
