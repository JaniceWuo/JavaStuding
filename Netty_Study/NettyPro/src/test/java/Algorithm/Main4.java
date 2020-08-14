package Algorithm;

import java.util.Scanner;

public class Main4 {
    public static void main(String[] args) {
        Scanner sc = new Scanner( System.in );
        int n = sc.nextInt();
        String str = sc.next();
        char[] strings = str.toCharArray();
        int i = 0;
        int j = n-1;
        int res = 0;
        while(i<j){
            if(strings[i] != strings[j]){
                res++;
            }
            i++;
            j--;
        }
        System.out.println(res);
    }
}
