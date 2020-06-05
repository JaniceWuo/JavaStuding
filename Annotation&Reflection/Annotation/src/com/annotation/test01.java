package com.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public class test01 {
    @myAnnotation("haha")
    public void test(){

    }
}

@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@interface myAnnotation{
    //注解的参数：参数类型+名（）
    String name() default "";
    int age() default 0;
    int id() default  -1; //如果默认值为-1 代表不存在

    String[] schools() default {"清华","北大"};
    String value();
}

class Solution {
    public int[] productExceptSelf(int[] nums) {
        int len = nums.length;;
        int[] arrLeft = new int[len];
        int[] arrRight = new int[len];
        arrLeft[0] = 1;
        arrRight[len-1] = 1;
        for(int i = 1;i<len;i++){
            arrLeft[i] = arrLeft[i-1] * nums[i-1];
        }
        for(int i = len-2;i>=0;i--){
            arrRight[i] = arrRight[i+1] * nums[i+1];
        }
        int[] resul = new int[len];
        for(int i = 0;i<len;i++) resul[i] = arrLeft[i]*arrRight[i];
        return resul;
    }
}