import java.util.Comparator;
import java.util.TreeSet;

/*
用内部类重写比较方法
 */
public class TestTreeSet {
    public static void main(String[] args) {
        TreeSet<Integer> ts = new TreeSet<>(
                new Comparator<Integer>() {
                    @Override
                    public int compare(Integer o1, Integer o2) {
                        return o2-o1;
                    }
                }
        );
        for(int i =0;i<10;i++){
            ts.add(i);
        }
        for(Integer num:ts){
            System.out.println(num);
        }
    }

}
