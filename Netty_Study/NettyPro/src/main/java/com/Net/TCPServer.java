package com.Net;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

//TCPͨ�ŵķ�������
public class TCPServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket( 8888 );
        Socket socket = serverSocket.accept();  //��ȡ������Ŀͻ��ٶ���Socket
        InputStream inputStream = socket.getInputStream();
        byte[] bytes = new byte[1024];
        int len = inputStream.read( bytes );
        System.out.println(new String( bytes,0,len ) );
        OutputStream outputStream = socket.getOutputStream();
        outputStream.write( "�յ�лл".getBytes() );
        socket.close();
        serverSocket.close();
    }
}
