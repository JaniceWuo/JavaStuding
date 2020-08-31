package com.atguigu.nio.GroupChat;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.PriorityQueue;

public class GroupChatServer {
    private Selector selector;
    private ServerSocketChannel listenChannel;
    private static final int PORT = 6667;

    //构造器 初始化工作
    public GroupChatServer(){
        try {
            //得到选择器
            selector = Selector.open();
            listenChannel = ServerSocketChannel.open();
            //绑定端口
            listenChannel.socket().bind( new InetSocketAddress( PORT ) );
            //设置为非阻塞
            listenChannel.configureBlocking( false );
            listenChannel.register( selector, SelectionKey.OP_ACCEPT );
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void listen(){
        while (true){
            try {
                int count = selector.select(2000);
                if(count > 0){
                    Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                    while (iterator.hasNext()){
                        SelectionKey key = iterator.next();
                        //监听到accept
                        if(key.isAcceptable()){
                            SocketChannel sc = listenChannel.accept();
                            sc.register( selector,SelectionKey.OP_READ );
                            System.out.println(sc.getRemoteAddress() + "上线了");
                        }
                        if (key.isReadable()){
                            readData( key );
                        }
                        iterator.remove();
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }finally {

            }
        }
    }

    //读取客户端消息
    private void readData(SelectionKey key){
        SocketChannel channel = null;
        try {
            channel = (SocketChannel) key.channel();
            ByteBuffer buffer = ByteBuffer.allocate( 1024 );
            int count = channel.read(buffer);
            if(count > 0){
                //把缓存区的数据转成字符串
                String msg = new String( buffer.array() );
                System.out.println("from 客户端" + msg);
                //向其他客户端转发消息
                sendInfoToOtherClients( msg,channel );
            }
        }catch (Exception e){
            try {
                System.out.println(channel.getRemoteAddress() + "离线了");
                //取消注册
                key.cancel();
                channel.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }

        }
    }

    //转发消息给其他客户（通道）
    private void sendInfoToOtherClients(String msg,SocketChannel self) throws IOException {
        System.out.println("服务器转发消息中");
        for(SelectionKey key: selector.keys() ){
            Channel targetChannel = key.channel();
            if(targetChannel instanceof SocketChannel && targetChannel!=self){
                SocketChannel dest = (SocketChannel) targetChannel;
                ByteBuffer buffer = ByteBuffer.wrap( msg.getBytes() );
                dest.write( buffer );
            }
        }
    }

    public static void main(String[] args) {
        GroupChatServer groupChatServer = new GroupChatServer();
        groupChatServer.listen();
    }
}
