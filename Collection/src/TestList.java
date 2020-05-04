import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TestList {
    public static void main(String[] args) {
        List<Integer> arr = new ArrayList<>();
        for(int i = 0; i < 10;i++){
            arr.add(i);
        }
        System.out.println(arr);
        int count = 0;
        for(int i = 0;i<1000000;i++){
            Collections.shuffle(arr);
            if(arr.get(0)==3 &&arr.get(1)==1 && arr.get(2)==4) count++;
        }

        System.out.println(count/1000000.0);
    }
}
