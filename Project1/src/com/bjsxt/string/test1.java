package com.bjsxt.string;

public class test1 {
    public static void main(String[] args) {
        String str1 = new String("abc");
        String str2 = "abc";
        String str3 = "ABC";
        System.out.println(str1==str2);
        System.out.println(str1.equals(str2));
        System.out.println(str1.charAt(0));
        System.out.println(str1.charAt(str1.length()-1));
        System.out.println(str2.equalsIgnoreCase(str3));

        String str4 = "abcdcd".replace('d','D');
        System.out.println(str4); //abcDcD

        String str5 = "abcdcd".replace("cd","hello");
        System.out.println(str5);

        System.out.println("ss,i love you".startsWith("ss"));  //判断字符串是否以ss开头
        System.out.println("ss,i love you".endsWith("you"));
        System.out.println("ss,i love you".endsWith("love"));

        String str6 = " a b c d   ";
        System.out.println(str6.trim().length());  //trim只会去掉前后的空格
        System.out.println(str6);
    }

}
