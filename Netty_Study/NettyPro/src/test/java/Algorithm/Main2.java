package Algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner( System.in );
        int T = sc.nextInt();
        List<Integer> res = new ArrayList<>(  );
        while(T-- > 0){
            int m = sc.nextInt();
            int n = sc.nextInt();
            if(m == 1 && n == 1) res.add(1);
            else{
                if(m%2==0 || n % 2 == 0){
                    res.add(2);
                }
                else{
                    if(m==1 && n>1){
                        int temp = 3;
                        while (n % temp!=0){
                            temp++;
                        }
                        res.add(temp);
                    }
                    else if(m!=1 && n==1){
                        int temp = 3;
                        while (m%temp !=0){
                            temp++;
                        }
                        res.add(temp);
                    }
                    else{
                        int temp = 3;
                        while(m%temp!=0){
                            temp++;
                        }
                        int temp2 = 3;
                        while(n%temp2!=0){
                            temp2++;
                        }
                        int ss = temp<temp2?temp:temp2;
                        res.add(ss);
                        break;
                    }
                }
            }
        }
        for(Integer num:res){
            System.out.println(num);
        }

    }
}
