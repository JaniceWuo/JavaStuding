package com.bjsxt.array;

public class test01 {
    public static void main(String[] args) {
        int[] arr1 = new int[5];
        System.out.println(arr1[0]);
        for(int i = 0;i < arr1.length; i++){
            arr1[i] = i;
            System.out.println(arr1[i]);
        }
        //二维数组
        int[][] arr2 = new int[5][5];
        int maxNum = 0;
        for(int i = 0; i < arr2.length; i++){
            for(int j = 0; j < arr2[0].length;j++){
                arr2[i][j] = (int)(Math.random()*100);
                System.out.println(arr2[i][j]);
                if (maxNum<arr2[i][j]) maxNum = arr2[i][j];
            }
        }
        System.out.println("maxNum is :" + maxNum);

    }
}
