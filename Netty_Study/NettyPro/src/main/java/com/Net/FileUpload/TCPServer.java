package com.Net.FileUpload;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

//�ļ��ϴ��ķ�������
//��ȡ�ͻ����ϴ����ļ�  ���浽��������Ӳ��
public class TCPServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket( 8888 );
        Socket socket = serverSocket.accept();
        InputStream is = socket.getInputStream();
        File file = new File( "d:\\upload" );
        if(!file.exists()){
            file.mkdir();
        }
        FileOutputStream fos = new FileOutputStream( file + "\\1.png" );
        int len = 0;
        byte[] bytes = new byte[1024];
        while ((len = is.read( bytes ))!=-1){
            fos.write( bytes, 0, len);
        }
        socket.getOutputStream().write( "�ϴ��ɹ�".getBytes() );
        fos.close();
        socket.close();
        serverSocket.close();
    }
}
