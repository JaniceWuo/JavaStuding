package com.Net.FileUpload;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class TCPClient {
    public static void main(String[] args) throws IOException {
        //创建一个本地字节输入流
        FileInputStream fis = new FileInputStream( "E:\\test.png" );
        //创建一个客户端Socket对象，构造方法中绑定服务器的ip地址和端口号
        Socket socket = new Socket( "127.0.0.1",8888 );
        //获取网络字节输出流对象
        OutputStream os = socket.getOutputStream();
        int len = 0;
        byte[] bytes = new byte[1024];
        while ((len = fis.read(bytes))!=-1){
            os.write( bytes, 0, len );
        }
        socket.shutdownOutput();
        InputStream is = socket.getInputStream();
        while ((len = is.read( bytes ))!=-1){
            System.out.println(new String(bytes, 0, len) );
        }
        fis.close();
        socket.close();
    }
}
