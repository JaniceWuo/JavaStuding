package com.Net;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

//TCP通信的服务器端
public class TCPServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket( 8888 );
        Socket socket = serverSocket.accept();  //获取到请求的客户顿对象Socket
        InputStream inputStream = socket.getInputStream();
        byte[] bytes = new byte[1024];
        int len = inputStream.read( bytes );
        System.out.println(new String( bytes,0,len ) );
        OutputStream outputStream = socket.getOutputStream();
        outputStream.write( "收到谢谢".getBytes() );
        socket.close();
        serverSocket.close();
    }
}
