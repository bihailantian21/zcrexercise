package com.zcr.exercisetest;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.io.IOException;
import java.net.*;

/**
 * @author zcr
 * @date 2019/5/7-10:04
 */
public class TestSocket {
    public static void  main(String args[]){

//        InetAddress i1 = new InetAddress();Error:(18, 26) java: InetAddress()在//java.net.InetAddress中不是公共的; 无法从外部程序包中对其进行访问


        /*try {
            InetAddress i1 = InetAddress.getLocalHost();
            System.out.println(i1);
            System.out.println(i1.getHostAddress());
            System.out.println(i1.getHostName());

            //根据域名查找主机的IP地址
            InetAddress b1 = InetAddress.getByName("www.baidu.com");
            System.out.println(b1);
            InetAddress[] b1s = InetAddress.getAllByName("www.taobao.com");
            for (InetAddress address:b1s) {
                System.out.println(address);
            }
            //根据IP地址反向查找主机名
            InetAddress y1 = InetAddress.getByName("183.232.231.172");
            System.out.println(y1.getHostName());////如果没有主机名，会返回IP地址
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
*/

        try {
            DatagramSocket ds1 = new DatagramSocket();
            byte[] b1 = {1,2,3,4};//String text = "test";byte[] buf = text.getBytes();
            InetAddress i1 = InetAddress.getLocalHost();
            DatagramPacket dp1 = new DatagramPacket(b1,b1.length,i1,9953);
            System.out.println(dp1.getLength());
            System.out.println(dp1.getAddress());
            System.out.println(dp1.getPort());
            ds1.send(dp1);
            ds1.close();

            new Thread () {
                @Override
                        public void run(){
                    try {
                        System.out.println(1);
                        DatagramSocket ds2 = new DatagramSocket(9953);
                        System.out.println(2);

                        while (true) {
                            System.out.println(333);
                            byte[] b2 = new byte[2048];
                            DatagramPacket dp2 = new DatagramPacket(b2,b2.length);
                            ds2.receive(dp2);
                            System.out.println(3);


                            byte[] data = dp2.getData();
                            String msg = new String(data,0,dp2.getLength());
                            System.out.println(4);
                            System.out.println(msg);
                            System.out.println(5);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }.start();
        } catch (IOException e) {
            e.printStackTrace();
        }


       /* //TCP
        //客户端
        InetAddress i1 = InetAddress.getLocalHost();
        Socket s1 = new Socket(i1,9999);
        s1.connect();
        System.out.println(s1.getInputStream());
        System.out.println(s1.getOutputStream());
        System.out.println(s1.getLocalAddress());
        System.out.println(s1.getPort());
        //服务端
        ServerSocket ss1 = new ServerSocket(9999);
        ss1.accept();*/


    }
}
