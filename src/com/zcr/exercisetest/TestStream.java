package com.zcr.exercisetest;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;

/**
 * @author zcr
 * @date 2019/5/6-21:45
 */
public class TestStream {
    public static void main(String args[]){
        String s1 = "E:\\Architect\\1\\me\\add1.txt";
        String s2 = "E:\\Architect\\1\\me\\add2.txt";
        String s3 = "E:\\Architect\\1\\me\\add3.txt";
        String s4 = "E:\\Architect\\1\\me\\add4.txt";
        String s5 = "E:\\Architect\\1\\me\\add5.txt";

        //新建目录
        File path1 = new File("E:\\Architect\\1\\me");
        try {
            if(!path1.exists()){
                path1.mkdir();
            }
        } catch (Exception e) {
            System.out.println("新建目录出错");
            e.printStackTrace();
        }


        //新建文件
        File f1 = new File(s1);
        try {
            if(!f1.exists()){
                f1.createNewFile();
            }
        } catch (Exception e) {
            System.out.println("新建文件出错");
            e.printStackTrace();
        }

        File f2 = new File(s2);
        try {
            if(!f2.exists()){
                f2.createNewFile();
            }
        } catch (Exception e) {
            System.out.println("新建文件出错");
            e.printStackTrace();
        }

        File f3 = new File(s3);
        try {
            if(!f3.exists()){
                f3.createNewFile();
            }
        } catch (Exception e) {
            System.out.println("新建文件出错");
            e.printStackTrace();
        }

        File f4 = new File(s4);
        try {
            if(!f4.exists()){
                f4.createNewFile();
            }
        } catch (Exception e) {
            System.out.println("新建文件出错");
            e.printStackTrace();
        }

        File f5 = new File(s5);
        try {
            if(!f5.exists()){
                f5.createNewFile();
            }
        } catch (Exception e) {
            System.out.println("新建文件出错");
            e.printStackTrace();
        }

        //删除文件
        File ff2 = new File("E:\\Architect\\1\\me\\del.txt");
        try {
                ff2.delete();
        } catch (Exception e) {
            System.out.println("删除文件出错");
            e.printStackTrace();
        }

        //删除空文件夹
        File ff3 = new File("E:\\Architect\\1\\me\\delll");
        try {
            ff3.delete();
        } catch (Exception e) {
            System.out.println("删除空文件夹出错");
            e.printStackTrace();
        }



        /*//删除文件夹下的所有文件夹
        File ff4 = new File("E:\\Architect\\1\\me\\del");
        File[] ff4s = f4.listFiles();
        try {
            for (int i = 0; i < ff4s.length; i++) {
                if (ff4s[i].isDirectory()){
                    ff4s[i].delete();
                }

            }
        } catch (Exception e) {
            System.out.println("删除文件夹出错");
            e.printStackTrace();
        }*/

        /*FileOutputStream fs = new FileOutputStream(f1);
        BufferedOutputStream bs = new BufferedOutputStream(fs);
        bs.write(12);
        bs.flush();
        bs.close();*/

        //写入文件
        try {
            FileWriter fw = new FileWriter(f1);//加上true是续写
            BufferedWriter bw = new BufferedWriter(fw);//可以写入int,字符串,字符数组
            bw.write(12);
            bw.newLine();
            bw.write("I Love Python");
            bw.newLine();
            bw.write("ILOVETHEWORLD",3,7);
            bw.newLine();
            char[] cc1 = { 'I', 'L', 'o', 'v', 'e', 'C', 'o', 'd', 'e' };
            bw.write(cc1);
            bw.newLine();
            bw.write(cc1,3,5);
            bw.newLine();
            bw.write("我爱中国");
            bw.flush();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //读取文件
        try {
            FileReader fr = new FileReader(f1);//可以读取单个字符，将字符读入数组
            BufferedReader br = new BufferedReader(fr);//可以读取单个字符，将字符读入数组的一部分，读取一个文本行
            /*//一个一个字节的读
            int ch = 0;
            while((ch=br.read())!=-1) {
                System.out.println((char)ch);
            }*/

            /*//利用数组来读取
            char[] cc2 = new char[1024];
            br.read(cc2,0,(int)f1.length());
            System.out.println(cc2);*/

           /* //利用数组来读取2
            char[] cc3 = new char[1024];
            int len = 0;
            while((len=br.read(cc3))!=-1){
                System.out.println(new String(cc3,0,len));
            }*/

            //一行一行的读
            while (br.ready()) {
                System.out.println(br.readLine());
            }
            br.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            //拷贝文本文件
            FileReader fr1 = new FileReader(f1);
            BufferedReader br1 = new BufferedReader(fr1);
            FileWriter fw1 = new FileWriter(f5);
            BufferedWriter bw1 = new BufferedWriter(fw1);
            String line = null;
            while ((line=br1.readLine())!=null){
                bw1.write(line);
                bw1.newLine();
                bw1.flush();
            }
            br1.close();
            bw1.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


        //读取文件属性
        System.out.println(f1.getName());
        System.out.println(f1.length());
        System.out.println(f1.isFile());
        System.out.println(f1.isDirectory());
        System.out.println(f1.canRead());
        System.out.println(f1.canWrite());
        System.out.println(new Date(f1.lastModified()));


        try {
            //字节流
            byte[] b1 = {123,127,1,2,3,4};
            OutputStream fos2 = new FileOutputStream(f2);//可以写入一个字节，可以写入字节数组。可以续写，加上true
            for (int i = 0; i < b1.length; i++) {
                fos2.write(b1[i]);
            }
            fos2.write(b1);
            fos2.write("字节数组".getBytes());
            fos2.write(b1,2,2);
            fos2.close();



            InputStream fis1 = new FileInputStream(f2);
           /* int size = fis1.available();
            for (int i = 0; i < size ; i++) {
                System.out.println((char)fis1.read());
            }*/

           /*//一个字节一个字节的读
            int ch = 0;
            while ((ch=fis1.read())!=-1){
                System.out.println((char) ch);
            }
            */
            //利用字节数组读
            byte[] bb1= new byte[1024];
            int len = 0;
            while((len=fis1.read(bb1))!=-1){
                System.out.println(new String(bb1,0,len));
            }



            fis1.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            //字节流，拷贝字节文件，mp3或者电影

            InputStream fis2 = new FileInputStream(f1);
            BufferedInputStream bis1 = new BufferedInputStream(fis2);
            OutputStream fos2 = new FileOutputStream(f3);
            BufferedOutputStream bos1 = new BufferedOutputStream(fos2);
            int ch1 = 0;
            while ((ch1=bis1.read())!=-1){
                bos1.write(ch1);
            }
            bos1.close();
            bis1.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            FileOutputStream fos3 = new FileOutputStream(f4);
            //转换流，字符转换为字节（涉及键盘录入的时候用）
            OutputStreamWriter osw = new OutputStreamWriter(fos3,"UTF-8");
            osw.write("在");
            osw.append("\r\n");
            osw.append('1');
            osw.close();
            fos3.close();

            FileInputStream fis3 = new FileInputStream(f4);
            //转换流，字节转换为字符
            InputStreamReader isr = new InputStreamReader(fis3, "UTF-8");//可以读取单个字符，将字符读入数组
            StringBuffer sb = new StringBuffer();
            while(isr.ready()){
                sb.append((char) isr.read());
            }
            System.out.println(sb.toString());
            isr.close();
            fis3.close();
        } catch (IOException e) {
            e.printStackTrace();
        }



    }






}
