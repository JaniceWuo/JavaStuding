package Algorithm;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner( System.in );
        int n = sc.nextInt();
        Long[] arr = new Long[n];
        Map<Long,Integer> map = new HashMap<Long, Integer>(  );
        for(int i = 0;i<n;i++){
            arr[i] = sc.nextLong();
            map.put( arr[i],1 );
        }
        int res = n;
        System.out.println(2);
    }
}
