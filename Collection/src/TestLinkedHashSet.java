import java.util.LinkedHashSet;
import java.util.LinkedList;

public class TestLinkedHashSet {
    public static void main(String[] args) {
        System.out.println(Math.PI);
        String str = String.valueOf(Math.PI);
//        System.out.println(str.charAt(0));
        LinkedHashSet<Character> set = new LinkedHashSet<>();
        for(int i = 0 ;i<17;i++){
//            int num = str.charAt(i);
            if(str.charAt(i)=='.') continue;
            set.add(str.charAt(i));
        }
        System.out.println(set);
    }
}
