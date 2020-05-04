package com.bjsxt.array;
import java.util.Arrays;

public class array {
    public static void main(String[] args) {
        int a[] = new int[]{3,2,1,5};
        int[] b = Arrays.copyOfRange(a,0,3);  //下标3是取不到的
        for(int i = 0; i < b.length;i++)
            System.out.print(b[i]+" ");
        String s1 = a.toString();
        System.out.println(s1);

        String s2 = Arrays.toString(a);
        System.out.println(s2);
        Arrays.sort(a);
        System.out.println(Arrays.toString(a));//说明sort是对数组本身进行排序了
        System.out.println("数字2出现的位置："+Arrays.binarySearch(a,2));

        //填充数组
        int[] c = new int[10];
        Arrays.fill(c,5);
        System.out.println(Arrays.toString(c));

    }
}
