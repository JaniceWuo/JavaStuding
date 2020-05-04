import java.util.*;

public class TestHash {
    public static void main(String[] args) {
        HashMap<Integer,String> dic = new HashMap<>();
//        dic.put("a","物理英雄");
//        dic.put("b","魔法英雄");
//        System.out.println(dic.get("a"));  //单引号和双引号都有区别
//        System.out.println((int)(Math.random()*9999));

        List<Integer> data = new ArrayList<>();
        for(int i = 0;i<300;i++){
            String name = "hero-" + (int)(Math.random()*10);
//            data.add((int)(Math.random()*9999));
            dic.put(i,name);
        }
//        System.out.println(dic);
        forMethod(dic);

    }

    public static void forMethod(HashMap<Integer,String> data){
        long startTime = System.currentTimeMillis();
        for(int i = 0;i<data.size();i++){
            if(data.get(i).equals("hero-5")) System.out.println(i);
        }
        long endTime = System.currentTimeMillis();
        System.out.println(endTime-startTime);
    }
}
