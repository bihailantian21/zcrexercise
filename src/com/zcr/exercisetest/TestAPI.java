package com.zcr.exercisetest;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

/**
 * @author zcr
 * @date 2019/5/6-21:29
 */
public class TestAPI {
    public static void  main(String args[]){
        //第一阶段3
        byte[] by1 = {1,3,1,4,2,12,126};
        String s1 = new String(by1,2,2);
        System.out.println(s1);

        char[] cs2 = { 'I', 'L', 'o', 'v', 'e', 'C', 'o', 'd', 'e' };
        String s2 = new String(cs2);
        System.out.println(s2);

        String s3 = "I LOVE THE WORLD";
        System.out.println(s3);
        System.out.println(s3.length());
        System.out.println(s3.charAt(0));
        System.out.println(s3.indexOf('I'));
        System.out.println(s3.indexOf("THE"));
        System.out.println(s3.indexOf('L',4));
        System.out.println(s3.indexOf("L",4));
        System.out.println(s3.substring(3));
        System.out.println(s3.substring(3,6));

        String s4 = "ILoveCode";
        String s5 = "ILOVECODE";
        System.out.println(s4.equals(s2));
        System.out.println(s4.equalsIgnoreCase(s5));
        System.out.println(s4.startsWith("IL"));
        System.out.println(s4.endsWith("DE"));
        System.out.println(s4.contains("Co"));
        System.out.println(s4.isEmpty());
//        System.out.println(s4.matches(""));


        byte[] by2 = s4.getBytes();//字符串转化为字节数组
        for (byte b : by2
        ) {
            System.out.println(b);

        }

        char[] cs3 = s4.toCharArray();//字符串转化为字符数组
        for (char c : cs3
        ) {
            System.out.println(c);

        }

        String s6 = String.valueOf(cs2);//字符数组转化为字符串
        System.out.println(s6);

        String s7 = String.valueOf(1233);//整数转化为字符串
        System.out.println(s7);

        int[] a = {1,2,3};//数组显示
        System.out.println(Arrays.toString(a));

        System.out.println(s5.toLowerCase());
        System.out.println(s4.toUpperCase());
        System.out.println(s4.concat(s5));

        System.out.println(s3.trim());
        String[] s8 = s3.split(" ");
        System.out.println(s8[0]);

        System.out.println(s4.replace('I','M'));
        System.out.println(s4.replace("Love","Like"));
//        System.out.println(s4.replace("[a-z]","5"));

        StringBuffer sb1 = new StringBuffer();
        StringBuffer sb2 = new StringBuffer("ILoveYou");
        StringBuffer sb3 = new StringBuffer(5);
        System.out.println(sb2.capacity());
        sb2.append("forever");
        System.out.println(sb2);
        sb2.insert(0,"ZCR");
        System.out.println(sb2);
        sb2.deleteCharAt(0);
        System.out.println(sb2);
        sb2.delete(0,2);
        System.out.println(sb2);
        sb2.replace(1,4,"Like");
        System.out.println(sb2);
        sb2.reverse();
        System.out.println(sb2);
        String sb4 = sb2.substring(5);
        System.out.println(sb4);
        String sb5 = sb2.substring(5,6);
        System.out.println(sb5);

        //数组
        int[] a1 = {2,4,14,54,523,12};
        System.out.println(Arrays.toString(a1));
        Arrays.sort(a1);
        System.out.println(Arrays.toString(a1));


        Date d1 = new Date();
        Date d2 = new Date(2400);
        System.out.println(d1.toString());
        System.out.println(d1.getTime());

        System.out.println(d2.toString());

        SimpleDateFormat sd1 = new SimpleDateFormat("yy-MM-dd ss-mm");
        System.out.println(sd1.format(d1));

        //System.out.println(sd1.parse("2019-12-01 982-23-27"));

        Calendar c = Calendar.getInstance();
        System.out.println(c);

        System.out.println(Math.abs(-10));
        System.out.println(Math.ceil(13.32));
        System.out.println(Math.floor(13.432));
        System.out.println(Math.max(12.34,45.45));
        System.out.println(Math.min(12,23));
        System.out.println(Math.round(34.23));
        System.out.println(Math.pow(2,3));



    }
}
