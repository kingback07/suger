package org.kingback.suger.demo.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Set;

/**
 * @author wangyang09
 * Created on 2021-04-06
 */
public class NioSocketTest {
    public static void main(String[] args) throws IOException, InterruptedException {
        ServerSocketChannel socketChannel = ServerSocketChannel.open();
        socketChannel.bind(new InetSocketAddress(9990));
        socketChannel.configureBlocking(false);

        Selector selector = Selector.open();
        socketChannel.register(selector, SelectionKey.OP_ACCEPT);

        while (true) {

            int select = selector.select();

            Set<SelectionKey> selectionKeys = selector.selectedKeys();

            for (SelectionKey selectionKey : selectionKeys) {
                if (selectionKey.isAcceptable()) {
                    SocketChannel accept = socketChannel.accept();
                    System.out.println("接收到远程链接:" + accept.socket().getRemoteSocketAddress().toString());
                    accept.configureBlocking(false);
                    accept.register(selector, SelectionKey.OP_READ);
                }
                if (selectionKey.isReadable()) {
                    SocketChannel channel = (SocketChannel) selectionKey.channel();
                    ByteBuffer bb = ByteBuffer.allocate(1024);
                    channel.read(bb);
                    System.out.println("获取到远程链接[" + channel.getRemoteAddress().toString() + "] 消息:" + new String(bb.array()));
//                    String reply = "HSLLS";
//                    bb.flip();
//                    bb.put(reply.getBytes());
//                    channel.write(bb);
                }
            }
            selectionKeys.clear();
        }
    }
}
