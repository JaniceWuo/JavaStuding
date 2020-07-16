package com.atguigu.bio;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BIOServer {
    public static void main(String[] args) throws IOException {
        ExecutorService threadPool = Executors.newCachedThreadPool();
        ServerSocket serverSocket = new ServerSocket( 6666 );

        System.out.println("服务器启动了");
        while(true){
            //监听 等待客户端连接
            final Socket socket = serverSocket.accept();
            System.out.println("连接到一个客户端");
            threadPool.execute( new Runnable() {
                public void run() {
                    //可以和客户端通讯
                    handler( socket );
                }
            } );
        }

    }

    public static void handler(Socket socket){
        byte[] bytes = new byte[1024];
        try {
            System.out.println("线程信息id = " + Thread.currentThread().getId() + "名字" + Thread.currentThread().getName());
            InputStream inputStream = socket.getInputStream();
            //循环读取客户端发送的数据
            while (true){
                System.out.println("线程信息id = " + Thread.currentThread().getId() + "名字" + Thread.currentThread().getName());
                int read = inputStream.read( bytes );
                if(read != -1){
                    System.out.println(new String(bytes,0,read));  //输出客户端发送的数据
                }else{
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            System.out.println("关闭和client的连接");
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
