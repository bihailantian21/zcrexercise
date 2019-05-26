package com.zcr.exercisetest;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * @author zcr
 * @date 2019/5/6-21:29
 */
public class TestAPI {
    public static void  main(String args[]) throws ParseException {
        //String
        /*1.String类又称作不可变字符序列。
        2.String位于java.lang包中，Java程序默认导入java.lang包下的所有类。
        3.Java字符串就是Unicode字符序列，例如字符串“Java”就是4个Unicode字符’J’、’a’、’v’、’a’组成的。
        4.Java没有内置的字符串类型，而是在标准Java类库中提供了一个预定义的类String，
        每个用双引号括起来的字符串都是String类的一个实例。*/

        //String类
        /*String类对象代表不可变的Unicode字符序列，因此我们可以将String对象称为“不可变对象”。
        那什么叫做“不可变对象”呢?指的是对象内部的成员变量的值无法再改变。我们打开String类的源码，如下所示：
        public final class String implements java.io.Serializable,Comparable<String>,CharSequence{
            private final char value[];//The value is used for character storage
            private final int offset;//The offset is the first index of the storage that is used
            private final int count;//The count is the number of characters in the String
        我们发现字符串内容全部存储到value[]数组中，而变量value是final类型的，也就是常量(即只能被赋值一次)。 这就是“不可变对象”的典型定义方式。
        String类型是不能再变的，所以我们要改变字符串时就需要新创建一个字符串变量。
        我们发现在前面学习String的某些方法，比如：substring()是对字符串的截取操作，但本质是读取原字符串内容生成了新的字符串。测试代码如下：*/
        String s1 = new String("abcdef");
        String s2 = s1.substring(2, 4);
        System.out.println(Integer.toHexString(s1.hashCode()));// 打印：ab199863
        System.out.println(Integer.toHexString(s2.hashCode()));// 打印：c61, 显然s1和s2不是同一个对象

        //字符串常量拼接时的优化
        /*在遇到字符串常量之间的拼接时，编译器会做出优化，即在编译期间就会完成字符串的拼接。
        因此，在使用==进行String对象之间的比较时，我们需要特别注意，如下所示*/
        //编译器做了优化,直接在编译的时候将字符串进行拼接
        String str1 = "hello" + " java";//相当于str1 = "hello java";
        String str2 = "hello java";
        System.out.println(str1 == str2);//true
        String str3 = "hello";
        String str4 = " java";
        //编译的时候不知道变量中存储的是什么,所以没办法在编译的时候优化
        String str5 = str3 + str4;
        System.out.println(str2 == str5);//false
        /*String类常用的方法有(可翻到前面查看，已讲过，此处不赘述)：
        1.String类的下述方法能创建并返回一个新的String对象: concat()、 replace()、substring()、 toLowerCase()、 toUpperCase()、trim()。
        2.提供查找功能的有关方法: endsWith()、 startsWith()、 indexOf()、lastIndexOf()。
        3.提供比较功能的方法: equals()、equalsIgnoreCase()、compareTo()。
        4.其它方法: charAt() 、length()。*/

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
        String str11 = "abc";//自动放到常量池里，放的是引用地址
        String str22 = new String("def");
        String str33 = "abc";
        String str44 = str2.intern();
        String str55 = "def";
        System.out.println(str11 == str33);// true 地址相同 所以通常比较字符串时，使用equals比较内容
        System.out.println(str22 == str44);// false 地址不同
        System.out.println(str44 == str55);// true 地址相同
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
        String ss1 = new String(by1,2,2);
        System.out.println(ss1);
        //通过字符数组来创建字符串
        char[] cs2 = { 'I', 'L', 'o', 'v', 'e', 'C', 'o', 'd', 'e' };
        String ss2 = new String(cs2);
        System.out.println(ss2);

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



        //StringBuffer和StringBuilder(可变字符序列)
        /*StringBuffer和StringBuilder非常类似，均代表可变的字符序列。
        这两个类都是抽象类AbstractStringBuilder的子类，方法几乎一模一样。
        我们打开AbstractStringBuilder的源码，如下所示：
            abstract class AbstractStringBuilder implements Appendable, CharSequence {
                //The value is used for character storage.
                char value[];
                //以下代码省略
            }
        显然，内部也是一个字符数组，但这个字符数组没有用final修饰，随时可以修改。因此，StringBuilder和StringBuffer称之为“可变字符序列”。那两者有什么区别呢?
        1.StringBuffer JDK1.0版本提供的类，线程安全，做线程同步检查， 效率较低。
        2.StringBuilder JDK1.5版本提供的类，线程不安全，不做线程同步检查，因此效率较高。 建议采用该类。
        */
        StringBuilder sb = new StringBuilder("abcdefg");//StringBuilder线程不安全，效率高（一般使用它）；StringBuffer线程安全，效率低
        System.out.println(Integer.toHexString(sb.hashCode()));
        System.out.println(sb);
        sb.setCharAt(2,'M');
        System.out.println(Integer.toHexString(sb.hashCode()));
        System.out.println(sb);
        /*· 常用方法列表：
        1.重载的public StringBuilder append(…)方法
        可以为该StringBuilder 对象添加字符序列，仍然返回自身对象。
        2.方法 public StringBuilder delete(int start,int end)
        可以删除从start开始到end-1为止的一段字符序列，仍然返回自身对象。
        3.方法 public StringBuilder deleteCharAt(int index)
        移除此序列指定位置上的 char，仍然返回自身对象。
        4.重载的public StringBuilder insert(…)方法
        可以为该StringBuilder 对象在指定位置插入字符序列，仍然返回自身对象。
        5.方法 public StringBuilder reverse()
        用于将字符序列逆序，仍然返回自身对象。
        6.方法 public String toString() 返回此序列中数据的字符串表示形式。
        7.和 String 类含义类似的方法：
        public int indexOf(String str)
        public int indexOf(String str,int fromIndex)
        public String substring(int start)
        public String substring(int start,int end)
        public int length()
        char charAt(int index)
        */
        //StringBuffer/StringBuilder基本用法
        /**StringBuilder*/
        StringBuilder sb11 = new StringBuilder();
        for (int i = 0; i < 7; i++) {
            sb.append((char) ('a' + i));//追加单个字符  (char) ('a' + i)代表了先是Ascii数字转化为字符
        }
        System.out.println(sb11.toString());//转换成String输出
        sb.append(", I can sing my abc!");//追加字符串
        System.out.println(sb.toString());
        /**StringBuffer*/
        StringBuffer sb22 = new StringBuffer("中华人民共和国");
        sb22.insert(0, "爱").insert(0, "我");//插入字符串。链式调用。核心就是：该方法调用了return this，把自己又返回来
        System.out.println(sb22);
        sb22.delete(0, 2);//删除子字符串
        System.out.println(sb22);
        sb22.deleteCharAt(0).deleteCharAt(0);//删除某个字符
        System.out.println(sb22.charAt(0));//获取某个字符
        System.out.println(sb22.reverse());//字符串逆序

        //不可变和可变字符序列使用陷阱
        /*String使用的陷阱：
        String一经初始化后，就不会再改变其内容了。对String字符串的操作实际上是对其副本(原始拷贝)的操作，原来的字符串一点都没有改变。比如：
        String s =“a”; 创建了一个字符串
        s = s+“b”;
        实际上原来的"a"字符串对象已经丢弃了，现在又产生了另一个字符串s+“b”(也就是"ab")。 如果多次执行这些改变串内容的操作，
        会导致大量副本字符串对象存留在内存中，降低效率。如果这样的操作放到循环中，会极大影响程序的时间和空间性能，甚至会造成服务器的崩溃。

        相反，StringBuilder和StringBuffer类是对原字符串本身操作的，可以对字符串进行修改而不产生副本拷贝或者产生少量的副本。因此可以在循环中使用。
        */
        //String和StringBuilder在频繁字符串修改时效率测试
        /**使用String进行字符串的拼接*/
        String str88 = "";
        //本质上使用StringBuilder拼接, 但是每次循环都会生成一个StringBuilder对象
        long num1 = Runtime.getRuntime().freeMemory();//获取系统剩余内存空间
        long time1 = System.currentTimeMillis();//获取系统的当前时间
        for (int i = 0; i < 5000; i++) {
            str88 = str88 + i;//相当于产生了10000个对象
        }
        long num2 = Runtime.getRuntime().freeMemory();
        long time2 = System.currentTimeMillis();
        System.out.println("String占用内存 : " + (num1 - num2));
        System.out.println("String占用时间 : " + (time2 - time1));
        /**使用StringBuilder进行字符串的拼接*/
        StringBuilder sb18 = new StringBuilder("");
        long num3 = Runtime.getRuntime().freeMemory();
        long time3 = System.currentTimeMillis();
        for (int i = 0; i < 5000; i++) {
            sb18.append(i);//不需要频繁产生对象
        }
        long num4 = Runtime.getRuntime().freeMemory();
        long time4 = System.currentTimeMillis();
        System.out.println("StringBuilder占用内存 : " + (num3 - num4));
        System.out.println("StringBuilder占用时间 : " + (time4 - time3));
        /*以后遇到这种循环累加字符串时，要用StringBuilder。
        要点：
        1.String：不可变字符序列。
        2.StringBuffer：可变字符序列，并且线程安全，但是效率低。
        3.StringBuilder：可变字符序列，线程不安全，但是效率高(一般用它)。*/

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
        System.out.println(Arrays.toString(a1));//[2, 4, 14, 54, 523, 12]
        Arrays.sort(a1);
        System.out.println(Arrays.toString(a1));//[2, 4, 12, 14, 54, 523]

        //时间处理相关类
        /*时间是一个一维的东东。所以，我们需要一把刻度尺来表达和度量时间。
        在计算机世界，我们把1970 年 1 月 1 日 00:00:00定为基准时间，每个度量单位是毫秒(1秒的千分之一)
        我们用long类型的变量来表示时间，从基准时间往前几亿年，往后几亿年都能表示。如果想获得现在时刻的“时刻数值”，可以使用：
        long now = System.currentTimeMillis();
        这个“时刻数值”是所有时间类的核心值，年月日都是根据这个“数值”计算出来的。我们工作学习涉及的时间相关类有如下：
        java.util.Date java.util.Calender(java.util.GergorianCalendar) java.text.DataFormat(java.text.SimpleDataFormat)
        java.sql.Date java.sql.Time java.sql.Timestamp*/
        long now  = System.currentTimeMillis();
        System.out.println("现在的时间是"+now);

        //Date时间类(java.util.Date)
        /*在标准Java类库中包含一个Date类。它的对象表示一个特定的瞬间，精确到毫秒。
        1.Date() 分配一个Date对象，并初始化此对象为系统当前的日期和时间，可以精确到毫秒)。
        2.Date(long date) 分配 Date 对象并初始化此对象，以表示自从标准基准时间(称为“历元(epoch)”，即 1970 年 1 月 1 日 00:00:00 GMT)以来的指定毫秒数。
        3.boolean after(Date when) 测试此日期是否在指定日期之后。
        4.boolean before(Date when) 测试此日期是否在指定日期之前。
        5.boolean equals(Object obj) 比较两个日期的相等性。
        6.long getTime() 返回自 1970 年 1 月 1 日 00:00:00 GMT 以来此 Date 对象表示的毫秒数。
        7.String toString() 把此 Date 对象转换为以下形式的 String：
        dow mon dd hh:mm:ss zzz yyyy 其中： dow 是一周中的某一天 (Sun、 Mon、Tue、Wed、 Thu、 Fri、 Sat)。*/
        Date d1 = new Date();
        Date d2 = new Date(2400);
        System.out.println(d1.toString());
        System.out.println(d1.getTime());
        System.out.println(d2.toString());
        System.out.println(d1.after(d2));
        System.out.println(d1.before(d2));
        System.out.println(d1.equals(d2));
        System.out.println(new Date(1000L * 60 * 60 * 24 * 365 * 39L).toString());
        //查看API文档大家可以看到其实Date类中的很多方法都已经过时了。JDK1.1之前的Date包含了：日期操作、字符串转化成时间对象等操作。
        //JDK1.1之后，日期操作一般使用Calendar类，而字符串的转化使用DateFormat类。

        //DateFormat类和SimpleDateFormat类
        /*·DateFormat类的作用
        把时间对象转化成指定格式的字符串。反之，把指定格式的字符串转化成时间对象。
        DateFormat是一个抽象类，一般使用它的的子类SimpleDateFormat类来实现。
        DateFormat类的源码
        public abstract class DataFormat extends Format{
            protected Calendar calendar;
            //把时间转成字符串
            public final StringBuffer format(Object obj,StringBuffer toAppendTo,Fieldposition fieldposition){
                if(obj instanceof Date)
                    return format((Data)obj,toAppendTo,fieldPosition);
                else if(obj instanceof Number)
                    return format(new Data((Number)obj).longValue(),toAppendTo,fieldPosition);
                else
                    throw new IllegalArgumentException("Cannot format given Object as a Date");
            }
            //把字符串转成时间
            public Data parse(String source) throws ParseException{
                ParsePosition pos = new ParsePosition(0);
                Date result = parse(source,pos);
                if(pos.index == 0)
                    throw new parseException("Unparseable date:\""+source+"\"",pos.errorIndex);
                return result;
            }
        }
        G公元 y年 M月 w年中周 W月中周 D年中天 d月中天 F月中星期 E星期中天 aam/pm标记 H天中小时0-23
        k天中小时1-24 Kam/pm中的小时0-11 ham/pm中的小时1-12 m小时中分钟 s分钟中秒 S毫秒 zZ时区
        */
        //把时间对象按照"格式字符串指定的格式"转换成相应的字符串
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String strdf = df.format(new Date(4000000));
        System.out.println("把时间对象转换为字符串"+strdf);
        //把字符串按照"格式字符串指定的格式"转换成相应的时间对象
        DateFormat df2 = new SimpleDateFormat("yyyy年MM月dd日 hh时mm分ss秒");
        Date date = null;
        try {
            date = df2.parse("1983年05月10日 10时45分59秒");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println("把字符串转换为时间对象"+date);
        // new出SimpleDateFormat对象
        SimpleDateFormat sd1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        SimpleDateFormat sd2 = new SimpleDateFormat("yyyy-MM-dd");
        // 将时间对象转换成字符串
        String daytime = sd1.format(new Date());
        System.out.println(daytime);//2017-05-17 05:43:00
        System.out.println(sd2.format(new Date()));//2017-05-17
        System.out.println(new SimpleDateFormat("hh:mm:ss").format(new Date()));//05:43:00
        // 将符合指定格式的字符串转成成时间对象.字符串格式需要和指定格式一致。
        String time = "2007-10-7";
        Date date1 = sd2.parse(time);
        System.out.println("date1: " + date1);//date1:Sun Oct 07 00:00:00 CST 2007
        time = "2007-10-7 20:15:30";
        date1 = sd1.parse(time);
        System.out.println("date2: " + date1);
        //时间格式字符的使用
        SimpleDateFormat sdf1 = new SimpleDateFormat("D");
        String daytim = sdf1.format(new Date());
        System.out.println("获得当前时间是今年的第几天"+daytim);

        //Calendar日历类
        /*Calendar 类是一个抽象类，为我们提供了关于日期计算的相关功能，比如：年、月、日、时、分、秒的展示和计算。
        GregorianCalendar 是 Calendar 的一个具体子类，提供了世界上大多数国家/地区使用的标准日历系统。
        菜鸟雷区：
        注意月份的表示，一月是0，二月是1，以此类推，12月是11。 因为大多数人习惯于使用单词而不是使用数字来表示月份，
        这样程序也许更易读，父类Calendar使用常量来表示月份：JANUARY、FEBRUARY等等。
        */
        //GregorianCalendar类和Calendar类的使用
        Calendar c = Calendar.getInstance();
        System.out.println(c);
        // 得到相关日期元素get
        GregorianCalendar calendar = new GregorianCalendar(2999, 10, 9, 22, 10, 50);
        int year = calendar.get(Calendar.YEAR); // 打印：1999 年份
        int month = calendar.get(Calendar.MONTH); // 打印：10    月份
        int day = calendar.get(Calendar.DAY_OF_MONTH); // 打印：9  几号
        int day2 = calendar.get(Calendar.DATE); // 打印：9  几号
        // 日：Calendar.DATE和Calendar.DAY_OF_MONTH同义
        int datej = calendar.get(Calendar.DAY_OF_WEEK); // 打印：3  星期几
        // 星期几 这里是：1-7.周日是1，周一是2，。。。周六是7
        System.out.println(year);
        System.out.println(month);
        System.out.println(day);
        System.out.println(day2);
        System.out.println(datej);

        // 设置日期
        GregorianCalendar calendar2 = new GregorianCalendar();
        calendar2.set(Calendar.YEAR, 2999);
        calendar2.set(Calendar.MONTH, Calendar.FEBRUARY); // 或者用数字：月份数0-11
        calendar2.set(Calendar.DATE, 3);
        calendar2.set(Calendar.HOUR_OF_DAY, 10);
        calendar2.set(Calendar.MINUTE, 20);
        calendar2.set(Calendar.SECOND, 23);
        printCalendar(calendar2);

        // 日期计算set
        GregorianCalendar calendar3 = new GregorianCalendar(2999, 10, 9, 22, 10, 50);
        calendar3.add(Calendar.MONTH, -7); // 月份减7
        calendar3.add(Calendar.DATE, 7); // 增加7天
        printCalendar(calendar3);

        // 日历对象和时间对象转化
        Date d = calendar3.getTime();   //日历类转化为时间对象getTime
        System.out.println("日历类转化为时间对象"+d);
        GregorianCalendar calendar4 = new GregorianCalendar();
        calendar4.setTime(new Date());//时间对象转化为日期类setTime
        System.out.println("时间对象转化为日期类"+calendar4);
        long g = System.currentTimeMillis();
        System.out.println(g);

        //编写程序，利用GregorianCalendar类，打印当前月份的日历
        System.out.println("请输入日期（格式为：2010-3-3）：");
        Scanner scanner = new Scanner(System.in);
        String dateString = scanner.nextLine(); // 2010-3-1  拿到用户输入的字符串
        // 将输入的字符串转化成日期类
        System.out.println("您刚刚输入的日期是：" + dateString);
        String[] str = dateString.split("-");
        int year2 = Integer.parseInt(str[0]);
        int month2 = new Integer(str[1]);
        int day22 = new Integer(str[2]);
        Calendar c2 = new GregorianCalendar(year2, month2 - 1, day22); // Month:0-11
        // 大家自己补充另一种方式：将字符串通过SImpleDateFormat转化成Date对象，
        //再将Date对象转化成日期类
        // SimpleDateFormat sdfDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        // Date date = sdfDateFormat.parse(dateString);
        // Calendar c = new GregorianCalendar();
        // c.setTime(date);
        // int day = c.get(Calendar.DATE);
        c2.set(Calendar.DATE, 1);//当前日期置为1
        int dow = c2.get(Calendar.DAY_OF_WEEK); // week:1-7 日一二三四五六不见得每月1号都是周日
        System.out.println("日\t一\t二\t三\t四\t五\t六");
        for (int i = 0; i < dow - 1; i++) {
            System.out.print("\t");
        }
        int maxDate = c2.getActualMaximum(Calendar.DATE);//不见得每个月都是31天
        for (int i = 1; i <= maxDate; i++) {
            StringBuilder sBuilder = new StringBuilder();
            if (c2.get(Calendar.DATE) == day22) {                        //当前日加*
                sBuilder.append(c2.get(Calendar.DATE) + "*\t");
            } else {
                sBuilder.append(c2.get(Calendar.DATE) + "\t");
            }
            System.out.print(sBuilder);
            if (c2.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {//每逢周六换行
                System.out.print("\n");
            }
            c2.add(Calendar.DATE, 1);//循环后日加1
        }
        System.out.println();

        //Math类
        /*java.lang.Math提供了一系列静态方法用于科学计算;其方法的参数和返回值类型一般为double型。
        如果需要更加强大的数学运算能力，计算高等数学中的相关内容，可以使用apache commons下面的Math类库。
        Math类的常用方法：
        1.abs 绝对值
        2. acos,asin,atan,cos,sin,tan 三角函数
        3.sqrt 平方根
        4. pow(double a, double b) a的b次幂
        5. max(double a, double b) 取大值
        6. min(double a, double b) 取小值
        7. ceil(double a) 大于a的最小整数
        8. floor(double a) 小于a的最大整数
        9. random() 返回 0.0 到 1.0 的随机数
        10. long round(double a) double型的数据a转换为long型(四舍五入)
        11. toDegrees(double angrad) 弧度->角度
        12. toRadians(double angdeg) 角度->弧度*/
        //取整相关操作
        System.out.println(Math.ceil(3.2));//4.0
        System.out.println(Math.floor(3.2));//3.0
        System.out.println(Math.round(3.2));//3
        System.out.println(Math.round(3.8));//4
        //绝对值、开方、a的b次幂等操作
        System.out.println(Math.abs(-45));
        System.out.println(Math.sqrt(64));
        System.out.println(Math.pow(5, 2));
        System.out.println(Math.pow(2, 5));
        //Math类中常用的常量
        System.out.println(Math.PI);
        System.out.println(Math.E);
        //随机数
        System.out.println(Math.random());// [0,1)

        System.out.println(Math.abs(-10));
        System.out.println(Math.ceil(13.32));
        System.out.println(Math.floor(13.432));
        System.out.println(Math.max(12.34,45.45));
        System.out.println(Math.min(12,23));
        System.out.println(Math.round(34.23));
        System.out.println(Math.pow(2,3));

        //Random类的常用方法
        /* Math类中虽然为我们提供了产生随机数的方法Math.random()，但是通常我们需要的随机数范围并不是[0, 1)之间的double类型的数据，
        这就需要对其进行一些复杂的运算。如果使用Math.random()计算过于复杂的话，我们可以使用例外一种方式得到随机数，
        即Random类，这个类是专门用来生成随机数的，并且Math.random()底层调用的就是Random的nextDouble()方法。*/
        Random rand = new Random();
        //随机生成[0,1)之间的double类型的数据
        System.out.println(rand.nextDouble());
        //随机生成int类型允许范围之内的整型数据
        System.out.println(rand.nextInt());
        //随机生成[0,1)之间的float类型的数据
        System.out.println(rand.nextFloat());
        //随机生成false或者true
        System.out.println(rand.nextBoolean());
        //随机生成[0,10)之间的int类型的数据
        System.out.print(rand.nextInt(10));
        //随机生成[20,30)之间的int类型的数据
        System.out.print(20 + rand.nextInt(10));
        //随机生成[20,30)之间的int类型的数据（此种方法计算较为复杂）
        System.out.print(20 + (int) (rand.nextDouble() * 10));


    }
    static void printCalendar(Calendar calendar) {
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;//0-11所以要加1
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int date = calendar.get(Calendar.DAY_OF_WEEK) - 1; // 星期几,  1是周日，2是周一，3是周二，1-7要分别减一
        String week = "" + ((date == 0) ? "日" : date);//0改为周日
        int hour = calendar.get(Calendar.HOUR);
        int minute = calendar.get(Calendar.MINUTE);
        int second = calendar.get(Calendar.SECOND);
        System.out.printf("%d年%d月%d日,星期%s %d:%d:%d\n", year, month, day,
                week, hour, minute, second);
    }



}
