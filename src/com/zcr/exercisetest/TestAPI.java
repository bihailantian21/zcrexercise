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
        //String
        /*1.String类又称作不可变字符序列。
        2.String位于java.lang包中，Java程序默认导入java.lang包下的所有类。
        3.Java字符串就是Unicode字符序列，例如字符串“Java”就是4个Unicode字符’J’、’a’、’v’、’a’组成的。
        4.Java没有内置的字符串类型，而是在标准Java类库中提供了一个预定义的类String，
        每个用双引号括起来的字符串都是String类的一个实例。*/

        String s0 = "";//空字符串
        String s00 = "Hello World";
        //当”+“运算符两侧的操作数中只要有一个是字符串(String)类型，系统会自动将另一个操作数转换为字符串然后再进行连接。
        System.out.println(s0+s00);
        int age = 18;
        System.out.println("age is" + age);

        //String类和常量池
        /*在Java的内存分析中，我们会经常听到关于“常量池”的描述，实际上常量池也分了以下三种：
        1.全局字符串常量池(String Pool)
        全局字符串常量池中存放的内容是在类加载完成后存到String Pool中的，在每个VM中只有一份，存放的是字符串常量的引用值(在堆中生成字符串对象实例)。
        2.class文件常量池(Class Constant Pool)
        class常量池是在编译的时候每个class都有的，在编译阶段，存放的是常量(文本字符串、final常量等)和符号引用。
        3.运行时常量池(Runtime Constant Pool)
        运行时常量池是在类加载完成之后，将每个class常量池中的符号引用值转存到运行时常量池中，也就是说，每个class都有一个运行时常量池，
        类在解析之后，将符号引用替换成直接引用，与全局常量池中的引用值保持一致。*/
        String str1 = "abc";//自动放到常量池里，放的是引用地址
        String str2 = new String("def");
        String str3 = "abc";
        String str4 = str2.intern();
        String str5 = "def";
        System.out.println(str1 == str3);// true 地址相同 所以通常比较字符串时，使用equals比较内容
        System.out.println(str2 == str4);// false 地址不同
        System.out.println(str4 == str5);// true 地址相同
        /**
         *首先经过编译之后，在该类的class常量池中存放一些符号引用，然后类加载之后，将class常量池中存放的符号引用转存到运行时常量池中，
         *然后经过验证，准备阶段之后，在堆中生成驻留字符串的实例对象(也就是上例中str1所指向的“abc”实例对象)，
         *然后将这个对象的引用存到全局String Pool中，也就是String Pool中，
         *最后在解析阶段，要把运行时常量池中的符号引用替换成直接引用，那么就直接查询String Pool，
         *保证String Pool里的引用值与运行时常量池中的引用值一致，大概整个过程就是这样了。
         *
         *1现在就很容易解释整个程序的内存分配过程了，首先，在堆中会有一个“abc”实例，全局String Pool中存放着“abc”的一个引用值，
         *2然后在运行第二句的时候会生成两个实例，一个是“def”的实例对象，并且String Pool中存储一个“def”的引用值，还有一个是new出来的一个“def”的实例对象，与上面那个是不同的实例，
         *3当在解析str3的时候查找String Pool，里面有“abc”的全局驻留字符串引用，所以str3的引用地址与之前的那个已存在的相同，
         *4str4是在运行的时候调用intern()函数，返回String Pool中“def”的引用值，如果没有就将str2的引用值添加进去，
         *在这里，String Pool中已经有了“def”的引用值了，所以返回上面在new str2的时候添加到String Pool中的 “def”引用值，
         *5最后str5在解析的时候就也是指向存在于String Pool中的“def”的引用值，那么这样一分析之后，结果就容易理解了。
         */

        //通过字节数组来创建字符串
        byte[] by1 = {1,3,1,4,2,12,126};
        String s1 = new String(by1,2,2);
        System.out.println(s1);
        //通过字符数组来创建字符串
        char[] cs2 = { 'I', 'L', 'o', 'v', 'e', 'C', 'o', 'd', 'e' };
        String s2 = new String(cs2);
        System.out.println(s2);

        //String类的常用方法
        String s3 = "I LOVE THE WORLD";
        System.out.println(s3);
        System.out.println(s3.length());//返回字符串的长度
        System.out.println(s3.charAt(0));//返回字符串中第index个字符
        System.out.println(s3.indexOf('I'));//返回从头开始查找第一个子字符串在字符串中的索引位置，如果未找到子字符串，则返回-1
        System.out.println(s3.indexOf("THE"));
        System.out.println(s3.lastIndexOf("THE"));//返回从末尾开始查找第一个子字符串在字符串中的索引位置，如果未找到子字符串，则返回-1
        System.out.println(s3.indexOf('L',4));
        System.out.println(s3.indexOf("L",4));
        System.out.println(s3.substring(3));//返回一个新字符串，该串包含从原始字符串第一个参数为下标值到串尾
        System.out.println(s3.substring(3,6));//返回一个新字符串，该串包含从原始字符串到第二个参数为下标值-1的所有字符
        System.out.println(s3.replace("LOVE","LIKE"));//返回一个新串，它是通过用新的字符串替换此字符串中出现的所有的旧字符串而生的
        System.out.println(s3.replace(' ','&'));
        System.out.println(s3.toLowerCase());//返回一个新字符串，该串将原始字符串中的所有大写字母改成小写字母
        System.out.println(s3.toUpperCase());//返回一个新字符串，该串将原始字符串中的所有小写字母改成大写字母
        System.out.println(s3.trim());//返回一个新字符串，该串删除了原始字符串头部和尾部的空格

        String s4 = "ILoveCode";
        String s5 = "ILOVECODE";
        System.out.println(s4.equals(s2));//如果两个字符串相等返回true，否则返回false
        System.out.println(s4.equalsIgnoreCase(s5));//如果两个字符串相等（忽略大小写）返回true，否则返回false
        System.out.println(s4.startsWith("IL"));//如果字符串以这个开头，返回true
        System.out.println(s4.endsWith("DE"));//如果字符串以这个结尾，返回true
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
