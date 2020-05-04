import java.util.ArrayList;

public class TestGeneric {
    public static void iterate(ArrayList<? extends Hero>list){
        for(Hero h:list) System.out.println(h.name);

    }
    public static void main(String[] args) {
        ArrayList<Hero> hs = new ArrayList<>();
        iterate(hs);
    }
}
