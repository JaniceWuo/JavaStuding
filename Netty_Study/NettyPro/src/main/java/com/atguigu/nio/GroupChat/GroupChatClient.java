package com.atguigu.nio.GroupChat;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Scanner;

public class GroupChatClient {
    private final String HOST = "127.0.0.1";
    private final int PORT = 6667;
    private Selector selector;
    private SocketChannel socketChannel;
    private String username;

    public GroupChatClient() throws IOException {
        selector = Selector.open();
        //连接服务器
        socketChannel.open(new InetSocketAddress( "127.0.0.1",PORT ) );
        socketChannel.configureBlocking( false );
        socketChannel.register( selector, SelectionKey.OP_READ );
        username = socketChannel.getLocalAddress().toString().substring( 1 );
        System.out.println(username + "is ok");
    }

    //向服务器发送消息
    public void sendInfo(String info){
        info = username + "说：" + info;
        try {
            socketChannel.write( ByteBuffer.wrap( info.getBytes() ) );
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    //读取从服务器端回复的消息
    public void readInfo(){
        try {
            int readChannels = selector.select(2000);
            if(readChannels > 0){
                Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                while (iterator.hasNext()){
                    SelectionKey key = iterator.next();
                    if (key.isReadable()){
                        //得到相关通道
                        SocketChannel sc = (SocketChannel)key.channel();

                        ByteBuffer buffer = ByteBuffer.allocate( 1024 );
                        sc.read( buffer );
                        String msg = new String( buffer.array() );
                        System.out.println(msg.trim());
                    }
                }
            }else{
                System.out.println("没有可用的通道");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        GroupChatClient chatClient = new GroupChatClient();
        //启动一个线程
        new Thread(  ){
            public void run(){
                while (true){
                    chatClient.readInfo();
                    try{
                        Thread.currentThread().sleep( 3000 );
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
            }
        }.start();

        Scanner scanner = new Scanner( System.in );
        while (scanner.hasNextLine()){
            String content = scanner.nextLine();
            chatClient.sendInfo( content );
        }
    }
}
