package org.kingback.suger.demo.server;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;

/**
 * @author wangyang09 wangyang09@suger.com>
 * Created on 2020-09-07
 */
public class JerryMouse {

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket();
        SocketAddress address = new InetSocketAddress(7799);
        serverSocket.bind(address);
        while (true) {
            //第一次阻塞，只有当有链接创建时才会继续
            Socket inSocket = serverSocket.accept();
            System.out.println("===get receive port:"+inSocket.getInetAddress()+":"+inSocket.getPort()+"===");
            Thread reciveThread = new Thread(() -> {
                while (true) {
                    InputStream inputStream = null;
                    try {

                        //第二次阻塞，内核数据准备好后才会返回
                        inputStream = inSocket.getInputStream();

                        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
//                                .lines().parallel().collect(Collectors.joining("\n"));
                        System.out.println("remote port:" + inSocket.getPort() + "Get Input String : --->" + reader.readLine());
                        OutputStream outputStream=inSocket.getOutputStream();

                        Thread.sleep(3000);

                        String resp="Ok,OK,I Know,I Know \n";
                        outputStream.write(resp.getBytes("UTF-8"));

                    } catch (IOException | InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            reciveThread.start();
        }

    }

}
