package com.atguigu.nio;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class NIOFileChannel01 {
    public static void main(String[] args) throws IOException {
        String str = "hello";
        FileOutputStream fileOutputStream = new FileOutputStream( "d:\\1.txt" );
        FileChannel fileChannel = fileOutputStream.getChannel();

        //创建一个缓冲区 ByteBuffer
        ByteBuffer byteBuffer = ByteBuffer.allocate( 1024 );
        byteBuffer.put( str.getBytes() );
        byteBuffer.flip();
        fileChannel.write( byteBuffer );
        fileOutputStream.close();
    }
}
