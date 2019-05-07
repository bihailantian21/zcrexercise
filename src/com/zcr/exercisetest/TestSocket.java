package com.zcr.exercisetest;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.net.*;

/**
 * @author zcr
 * @date 2019/5/7-10:04
 */
public class TestSocket {
    public static void  main(String args[]){
        try {
            System.out.println(InetAddress.getLocalHost());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        /*InetAddress i1 = new InetAddress();
        System.out.println(i1.getHostAddress());
        System.out.println(i1.getHostName());
*/
       /* //UDP

        DatagramSocket ds1 = new DatagramSocket();
        byte[] b1 = {1,2,3,4};
        DatagramPacket dp1 = new DatagramPacket(b1,3,i1,8090);
        System.out.println(dp1.getLength());
        System.out.println(dp1.getAddress());
        System.out.println(dp1.getPort());
        ds1.send(dp1);

        DatagramSocket ds2 = new DatagramSocket(8090);
        byte[] b2;
        DatagramPacket dp2 = new DatagramPacket(b2,3);
        ds2.receive(dp2);*/


      /*  //TCP
        Socket s1 = new Socket();
        s1.connect();*/


    }
}
