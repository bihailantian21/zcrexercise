package com.zcr.exercisetest;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.LineIterator;
import org.apache.commons.io.filefilter.DirectoryFileFilter;
import org.apache.commons.io.filefilter.EmptyFileFilter;
import org.apache.commons.io.filefilter.FileFilterUtils;
import org.apache.commons.io.filefilter.SuffixFileFilter;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Vector;

public class TestDecorate {
    //装饰模式
    /*节点流：与源点直接进行交互
    处理流：
    设计模式是解决复杂代码的一种策略
    单例、工厂、装饰

    装饰器模式是GOF23种设计模式中较为常用的一种模式。它可以实现对原有类的包装和装饰，使新的类具有更强的功能。
    我这里有智能手机iphone, 我们可以通过加装投影组件，实现原有手机功能的扩展。这就是一种“装饰器模式”。
    我们在未来给普通人加装“外骨骼”装饰，让普通人具有力扛千斤的能力，也是一种“装饰器模式”。

    }*/
    public static void main(String[] args) throws Exception {
        //实现放大镜对声音的放大功能
        Person2 p=new Person2();
        p.say();
        //装饰
        Amplifier am=new Amplifier(p);
        am.say();

       /* 模拟咖啡
        1.抽象组件：需要装饰的抽象对象（接口或抽象父类）
        2.具体组件：需要装饰的对象
        3.抽象装饰类：包含了对抽象组建的引用以及装饰着共有的方法
        4.具体装饰类：被装饰的对象
        */
        Drink coffee=new Coffee();
        Drink suger=new Suger(coffee);//装饰
        System.out.println(suger.info()+"–>"+suger.cost());//原味咖啡加入了蔗糖–>20.0
        Drink milk=new Milk(coffee);//装饰
        System.out.println(milk.info()+"–>"+milk.cost());//原味咖啡加入了牛奶–>40.0
        milk=new Milk(suger);//装饰
        System.out.println(milk.info()+"-->"+milk.cost());//原味咖啡加入了蔗糖加入了牛奶-->80.0
        //在不改变原来代码的基础上直接到里面丢就可以达到新的功能

        //给手机加上投影仪这种装饰模式
        Iphone phone = new Iphone("iphone30");
        phone.show();
        System.out.println("===============装饰后");
        TouyingPhone typhone = new TouyingPhone(phone);
        typhone.show();

        //IO流体系中的装饰器模式
        /*IO流体系中大量使用了装饰器模式，让流具有更强的功能、更强的灵活性。比如：
        FileInputStream fis = new FileInputStream(src);
        BufferedInputStream bis = new BufferedInputStream(fis);
        显然BufferedInputStream装饰了原有的FileInputStream，让普通的FileInputStream也具备了缓存功能，提高了效率。
        大家举一反三，可以翻看本章代码，看看还有哪些地方使用了装饰器模式。

        InputStream          这是我们的抽象组件
        -FileInputStream
        -StringBufferInputStream
        -ByteArrayInputStream    这三类是可以被装饰者包起来的具体组件。
        -FilterInputStream       这是一个抽象装饰者
        --PushbackInputStream
        --BufferedInputStream
        --DataInputStream
        --LineNumberInputStream    这四个是我们的具体装饰者
        */

        //IO文件分割RandomAccessFile随机流  既可以支持读，又可以支持写   通过模式进行切换
        //随机读取和写入流 RandomAccessFile
        //分多少块
        File src = new File("src/com/sxt/io/Copy.java");
        //总长度
        long len = src.length();
        //每块大小
        int blockSize =1024;
        //块数: 多少块
        int size =(int) Math.ceil(len*1.0/blockSize);
        System.out.println(size);//135

        //起始位置和实际大小
        int beginPos = 0;
        int actualSize = (int)(blockSize>len?len:blockSize);
        for(int i=0;i<size;i++) {
            beginPos = i*blockSize;
            if(i==size-1) { //最后一块
                actualSize = (int)len;
            }else {
                actualSize = blockSize;
                len -=actualSize; //剩余量
            }
            System.out.println(i+"-->"+beginPos +"-->"+actualSize);
            split(i,beginPos,actualSize);
        }

        //文件分割面向对象终极版以及文件的合并
        SplitFile sf = new SplitFile("src/com/sxt/io/SplitFile.java","dest") ;
        sf.split();
        sf.merge("aaa.java");

        //Apache IOUtils和FileUtils的使用
        /*JDK中提供的文件操作相关的类，但是功能都非常基础，进行复杂操作时需要做大量编程工作。实际开发中，往往需要你自己动手编写相关的代码，
        尤其在遍历目录文件时，经常用到递归，非常繁琐。 Apache-commons工具包中提供了IOUtils/FileUtils，
        可以让我们非常方便的对文件和目录进行操作。 本文就是让大家对IOUtils/FileUtils类有一个全面的认识，便于大家以后开发与文件和目录相关的功能。

        Apache IOUtils和FileUtils类库为我们提供了更加简单、功能更加强大的文件操作和IO流操作功能。非常值得大家学习和使用。

        Apache软件基金会(也就是Apache Software Foundation，简称为ASF)，是专门为支持开源软件项目而办的一个非盈利性组织。
        在它所支持的Apache项目与子项目中，所发行的软件产品都遵循Apache许可证(Apache License)。 官方网址为：www.apache.org 。

        很多著名的Java开源项目都来源于这个组织。比如：commons、kafka、lucene、maven、shiro、struts等技术，
        以及大数据技术中的：hadoop(大数据第一技术)、hbase、spark、storm、mahout等。
       */

        //FileUtils的妙用
        /*·jar包下载和介绍
        首先，我们要下载FileUtils相关的Apache-commons-io jar包以及api文档。FileUtils类库的下载页面在：
        http://commons.apache.org/proper/commons-io/download_io.cgi
        点击进入下载页面：
        API文档的页面：http://commons.apache.org/proper/commons-io/javadocs/api-2.5/index.html
        我们本次示例，下载了最新的2.5版本，作为测试和示范。
        ·eclpise项目如何导入外部的jar包操作
        1.在eclipse项目下新建lib文件夹
        2.解压下载后的版本，找到commons-io-2.5.jar包，并拷贝到lib文件夹下
        3. 设置jar包进入项目的classpath中。
        项目名上右击，依次选择【Build Path】–>【Configure Build Path…】，在打开的窗口中，先选中【Libraries】页，
        再从右边的按钮中点击 【add JARs…】; 在打开的窗口中，我们依次展开本项目的项目和lib文件夹，然后选中我们刚才复制到项目中的jar包，
        然后点击【Apply】使刚才的操作生效，最后点击【OK】关闭窗口。
        项目结构如下：

        新手雷区:很多初学者会忘记配置项目的classpath，从而项目找不到相关的jar包。大家可以在此处多配置几次，直到足够熟练!
        */
       /* · FieUtils类中常用方法的介绍
        打开FileUtils的api文档，我们抽出一些工作中比较常用的方法，进行总结和讲解。总结如下：
        cleanDirectory：清空目录，但不删除目录。
        contentEquals：比较两个文件的内容是否相同。
        copyDirectory：将一个目录内容拷贝到另一个目录。可以通过FileFilter过滤需要拷贝的 文件。
        copyFile：将一个文件拷贝到一个新的地址。
        copyFileToDirectory：将一个文件拷贝到某个目录下。
        copyInputStreamToFile：将一个输入流中的内容拷贝到某个文件。
        deleteDirectory：删除目录。
        deleteQuietly：删除文件。
        listFiles：列出指定目录下的所有文件。
        openInputSteam：打开指定文件的输入流。
        readFileToString：将文件内容作为字符串返回。
        readLines：将文件内容按行返回到一个字符串数组中。
        size：返回文件或目录的大小。
        write：将字符串内容直接写到文件中。
        writeByteArrayToFile:将字节数组内容写到文件中。
        writeLines：将容器中的元素的toString方法返回的内容依次写入文件中。
        writeStringToFile：将字符串内容写到文件中。
      */
        //文件大小
        long lenn =FileUtils.sizeOf(new File("src/com/zcr/exercisetest/SxtArrayList.java"));
        System.out.println(lenn);
        //目录大小
        lenn = FileUtils.sizeOf(new File("src/com/zcr/exercisetest"));
        System.out.println(lenn);
        //列出子孙级
        Collection<File> files =FileUtils.listFiles(new File("src/com/zcr/exercisetest"),
                EmptyFileFilter.NOT_EMPTY, null);//过滤空的文件
        for (File file : files) {
            System.out.println(file.getAbsolutePath());
        }
        System.out.println("---------------------");

        files =FileUtils.listFiles(new File("src/com/zcr/exercisetest"),
                EmptyFileFilter.NOT_EMPTY, DirectoryFileFilter.INSTANCE);//操作子孙级
        for (File file : files) {
            System.out.println(file.getAbsolutePath());
        }
        System.out.println("---------------------");

        files =FileUtils.listFiles(new File("src/com/zcr/exercisetest"),
                new SuffixFileFilter("java"), DirectoryFileFilter.INSTANCE);//只看后缀是.java的
        for (File file : files) {
            System.out.println(file.getAbsolutePath());
        }
        System.out.println("---------------------");
        files =FileUtils.listFiles(new File("src/com/zcr/exercisetest"),
                FileFilterUtils.or(new SuffixFileFilter("java"),
                        new SuffixFileFilter("class"),EmptyFileFilter.EMPTY), DirectoryFileFilter.INSTANCE);
        for (File file : files) {
            System.out.println(file.getAbsolutePath());
        }

        System.out.println("---------------------");
        files =FileUtils.listFiles(new File("src/com/zcr/exercisetest"),
                FileFilterUtils.and(new SuffixFileFilter("java"),
                        EmptyFileFilter.NOT_EMPTY), DirectoryFileFilter.INSTANCE);
        for (File file : files) {
            System.out.println(file.getAbsolutePath());
        }

        //读取内容
        //读取文件
        String msg =FileUtils.readFileToString(new File("fileutil.txt"),"UTF-8");
        System.out.println(msg);//读取内容
        byte[] datas = FileUtils.readFileToByteArray(new File("fileutil.txt"));//读到字节数组中
        System.out.println(datas.length);
        //逐行读取
        List<String> msgs= FileUtils.readLines(new File("fileutil.txt"),"UTF-8");//把每一行丢到容器里面
        for (String string : msgs) {
            System.out.println(string);
        }
        LineIterator it =FileUtils.lineIterator(new File("fileutil.txt"),"UTF-8");//用迭代器逐行读取
        while(it.hasNext()) {
            System.out.println(it.nextLine());
        }

        //写出内容
        //写出文件
        FileUtils.write(new File("happy.sxt"), "学习是一件伟大的事业\r\n","UTF-8");
        FileUtils.writeStringToFile(new File("happy.sxt"), "学习是一件辛苦的事业\r\n","UTF-8",true);//是否追加文件
        FileUtils.writeByteArrayToFile(new File("happy.sxt"), "学习是一件幸福的事业\r\n".getBytes("UTF-8"),true);//操作字节数组
        //写出列表
        List<String> datass =new ArrayList<String>();
        datass.add("马云");
        datass.add("马化腾");
        datass.add("弼马温");
        FileUtils.writeLines(new File("happy.sxt"), datass,"。。。。。",true);//第三个参数是间隔符

        //拷贝
        //复制文件
        FileUtils.copyFile(new File("pc.png"),new File("p-copy.png"));
        //复制文件到目录
        FileUtils.copyFileToDirectory(new File("pc.png"),new File("lib"));
        //复制目录到目录
        FileUtils.copyDirectoryToDirectory(new File("lib"),new File("lib2"));
        //复制目录
        FileUtils.copyDirectory(new File("lib"),new File("lib2"));
        //拷贝URL内容
        String url = "https://pic2.zhimg.com/v2-7d01cab20858648cbf62333a7988e6d0_qhd.jpg";
        FileUtils.copyURLToFile(new URL(url), new File("marvel.jpg"));
        String datas2 = IOUtils.toString(new URL("http://www.163.com"), "gbk");
        System.out.println(datas2);

        //读取文件内容，并输出到控制台上(只需一行代码!)
        String content = FileUtils.readFileToString(new File("a.txt"), "gbk");
        System.out.println(content);

        //目录拷贝，并使用FileFilter过滤目录和以html结尾的文件
        FileUtils.copyDirectory(new File("aaa"), new File("bbb"), new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                // 使用FileFilter过滤目录和以html结尾的文件
                if (pathname.isDirectory() || pathname.getName().endsWith("html")) {
                    return true;
                } else {
                    return false;
                }
            }
        });

        //IOUtils的妙用
        /*打开IOUtils的api文档，我们发现它的方法大部分都是重载的。所以，我们理解它的方法并不是难事。因此，对于方法的用法总结如下：
        1.buffer方法：将传入的流进行包装，变成缓冲流。并可以通过参数指定缓冲大小。
        2.closeQueitly方法：关闭流。
        3.contentEquals方法：比较两个流中的内容是否一致。
        4.copy方法：将输入流中的内容拷贝到输出流中，并可以指定字符编码。
        5.copyLarge方法：将输入流中的内容拷贝到输出流中，适合大于2G内容的拷贝。
        6 lineIterator方法：返回可以迭代每一行内容的迭代器。
        7.read方法：将输入流中的部分内容读入到字节数组中。
        8. readFully方法：将输入流中的所有内容读入到字节数组中。
        9.readLine方法：读入输入流内容中的一行。
        10.toBufferedInputStream，toBufferedReader：将输入转为带缓存的输入流。
        11.toByteArray，toCharArray：将输入流的内容转为字节数组、字符数组。
        12.toString：将输入流或数组中的内容转化为字符串。
        13.write方法：向流里面写入内容。
        14. writeLine方法：向流里面写入一行内容。
        我们没有必要对每个方法做测试，只是演示一下读入a.txt文件内容到程序中，并转成String对象，打印出来。
        */
        String content2 = IOUtils.toString(new FileInputStream("a.txt"),"gbk");
        System.out.println(content2);











    }
    /**
     * 指定第i块的起始位置 和实际长度
     * @param i
     * @param beginPos
     * @param actualSize
     * @throws IOException
     */
    public static void split(int i,int beginPos,int actualSize ) throws IOException {
        RandomAccessFile raf =new RandomAccessFile(new File("src/com/sxt/io/Copy.java"),"r");
        //随机读取
        raf.seek(beginPos);
        //读取
        //3、操作 (分段读取)
        byte[] flush = new byte[1024]; //缓冲容器
        int len = -1; //接收长度
        while((len=raf.read(flush))!=-1) {
            if(actualSize>len) { //获取本次读取的所有内容
                System.out.println(new String(flush,0,len));
                actualSize -=len;
            }else {
                System.out.println(new String(flush,0,actualSize));
                break;
            }
        }

        raf.close();
    }
    //分开思想: 起始、实际大小
    public static void test2() throws IOException {
        RandomAccessFile raf =new RandomAccessFile(new File("src/com/sxt/io/Copy.java"),"r");
        //起始位置
        int beginPos =2+1026;
        //实际大小
        int actualSize = 1026;
        //随机读取
        raf.seek(beginPos);
        //读取
        //3、操作 (分段读取)
        byte[] flush = new byte[1024]; //缓冲容器
        int len = -1; //接收长度
        while((len=raf.read(flush))!=-1) {
            if(actualSize>len) { //获取本次读取的所有内容
                System.out.println(new String(flush,0,len));
                actualSize -=len;
            }else {
                System.out.println(new String(flush,0,actualSize));
                break;
            }
        }

        raf.close();
    }


    //指定起始位置，读取剩余所有内容
    public static void test1() throws IOException {
        RandomAccessFile raf =new RandomAccessFile(new File("src/com/sxt/io/Copy.java"),"r");
        //随机读取
        raf.seek(2);
        //读取
        //3、操作 (分段读取)
        byte[] flush = new byte[1024]; //缓冲容器
        int len = -1; //接收长度
        while((len=raf.read(flush))!=-1) {
            System.out.println(new String(flush,0,len));
        }

        raf.close();
    }





}
interface Say{
    void say();
}
class Person2 implements Say{
    private int voice=10;
    public void say(){
        System.out.println("人的声音为："+this.getVoice());
    }
    public int getVoice(){
        return voice;
    }
    public void setVoice(int voice){
        this.voice=voice;
    }
}
//装饰类：放大器
class Amplifier implements Say{
    private Person2 p;
    Amplifier(Person2 p){
        this.p=p;
    }
    public void say(){
        System.out.println("人的声音为："+p.getVoice()*100);
        System.out.println("噪音！");
    }
}




//抽象组件
interface Drink{
    double cost();//费用
    String info();//说明
}
//具体组件
class Coffee implements Drink{
    private String name="原味咖啡";
    public double cost(){
        return 10;
    }
    public String info(){
        return name;
    }
}
//抽象装饰类
abstract class Decorate implements Drink{
    //对抽象组件的引用
    private Drink drink;
    public Decorate(Drink drink){
        this.drink=drink;
    }
    public double cost(){
        return this.drink.cost();
    }
    public String info(){
        return this.drink.info();
    }
}
//具体装饰类
class Milk extends Decorate{
    public Milk(Drink drink){
        super(drink);
    }
    public double cost(){
        return super.cost()*4;
    }
    public String info(){
        return super.info()+"加入了牛奶";
    }
}
//具体装饰类
class Suger extends Decorate{
    public Suger(Drink drink){
        super(drink);
    }
    public double cost(){
        return super.cost()*2;
    }
    public String info(){
        return super.info()+"加入了蔗糖";
    }
}




class Iphone {
    private String name;
    public Iphone(String name) {
        this.name = name;
    }
    public void show() {
        System.out.println("我是" + name + ",可以在屏幕上显示");
    }
}

class TouyingPhone {
    public Iphone phone;
    public TouyingPhone(Iphone p) {
        this.phone = p;
    }
    // 功能更强的方法
    public void show() {
        phone.show();
        System.out.println("还可以投影，在墙壁上显示");
    }
}





class SplitFile {
    //源头
    private File src;
    //目的地(文件夹)
    private String destDir;
    //所有分割后的文件存储路径
    private List<String> destPaths;
    //每块大小
    private int blockSize;
    //块数: 多少块
    private int size;

    public SplitFile(String srcPath, String destDir) {
        this(srcPath, destDir, 1024);
    }

    public SplitFile(String srcPath, String destDir, int blockSize) {
        this.src = new File(srcPath);
        this.destDir = destDir;
        this.blockSize = blockSize;
        this.destPaths = new ArrayList<String>();

        //初始化
        init();
    }

    //初始化
    private void init() {
        //总长度
        long len = this.src.length();
        //块数: 多少块
        this.size = (int) Math.ceil(len * 1.0 / blockSize);
        //路径
        for (int i = 0; i < size; i++) {
            this.destPaths.add(this.destDir + "/" + i + "-" + this.src.getName());
        }
    }

    /**
     * 分割
     * 1、计算每一块的起始位置及大小
     * 2、分割
     *
     * @throws IOException
     */
    public void split() throws IOException {
        //总长度
        long len = src.length();
        //起始位置和实际大小
        int beginPos = 0;
        int actualSize = (int) (blockSize > len ? len : blockSize);
        for (int i = 0; i < size; i++) {
            beginPos = i * blockSize;
            if (i == size - 1) { //最后一块
                actualSize = (int) len;
            } else {
                actualSize = blockSize;
                len -= actualSize; //剩余量
            }
            splitDetail(i, beginPos, actualSize);
        }
    }

    /**
     * 指定第i块的起始位置 和实际长度
     *
     * @param i
     * @param beginPos
     * @param actualSize
     * @throws IOException
     */
    private void splitDetail(int i, int beginPos, int actualSize) throws IOException {
        RandomAccessFile raf = new RandomAccessFile(this.src, "r");
        RandomAccessFile raf2 = new RandomAccessFile(this.destPaths.get(i), "rw");
        //随机读取
        raf.seek(beginPos);
        //读取
        //3、操作 (分段读取)
        byte[] flush = new byte[1024]; //缓冲容器
        int len = -1; //接收长度
        while ((len = raf.read(flush)) != -1) {
            if (actualSize > len) { //获取本次读取的所有内容
                raf2.write(flush, 0, len);
                actualSize -= len;
            } else {
                raf2.write(flush, 0, actualSize);
                break;
            }
        }
        raf2.close();
        raf.close();
    }

    /**
     * 文件的合并
     *
     * @throws IOException
     */
    public void merge(String destPath) throws IOException {
        //输出流
        OutputStream os = new BufferedOutputStream(new FileOutputStream(destPath, true));
        Vector<InputStream> vi = new Vector<InputStream>();
        SequenceInputStream sis = null;
        //输入流
        for (int i = 0; i < destPaths.size(); i++) {
            vi.add(new BufferedInputStream(new FileInputStream(destPaths.get(i))));
        }
        sis = new SequenceInputStream(vi.elements());
        //拷贝
        //3、操作 (分段读取)
        byte[] flush = new byte[1024]; //缓冲容器
        int len = -1; //接收长度
        while ((len = sis.read(flush)) != -1) {
            os.write(flush, 0, len); //分段写出
        }
        os.flush();
        sis.close();
        os.close();
    }
}

