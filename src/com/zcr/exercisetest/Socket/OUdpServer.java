package com.zcr.exercisetest.Socket;


import java.io.ByteArrayInputStream;
        import java.io.ObjectInputStream;
        import java.net.DatagramPacket;
        import java.net.DatagramSocket;

public class OUdpServer {
    public static void main(String[] args) throws Exception {
        //创建数据报套接字：指定接收信息的端口
        DatagramSocket ds = new DatagramSocket(8999);
        byte[] b = new byte[1024];
        //创建数据报包，指定要接收的数据的缓存位置和长度
        DatagramPacket dp = new DatagramPacket(b, b.length);
        //接收客户端发送的数据报
        ds.receive(dp); // 阻塞式方法
        //dp.getData():获取客户端发送的数据，返回值是一个字节数组
        ByteArrayInputStream bis = new ByteArrayInputStream(dp.getData());
        ObjectInputStream ois = new ObjectInputStream(bis);
        System.out.println(ois.readObject());
        //关闭资源
        ois.close();
        bis.close();
        ds.close();
    }
}

