package com.atguigu.nio;

import java.nio.IntBuffer;

public class BasicBuffer {
    public static void main(String[] args) {
        //创建一个Buffer  大小为5
        IntBuffer intBuffer = IntBuffer.allocate( 5 );
        for (int i = 0; i < intBuffer.capacity(); i++){
            intBuffer.put( i * 2 );
        }
        //读取数据
        //必须要进行读取切换
        intBuffer.flip();
        while (intBuffer.hasRemaining()){
            System.out.println(intBuffer.get());
        }
    }
}
