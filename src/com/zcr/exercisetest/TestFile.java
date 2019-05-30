package com.zcr.exercisetest;

import sun.nio.cs.US_ASCII;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.Date;

public class TestFile {
    public static void main(String args[]) throws Exception {
        //File类
        /*java.io.File类：代表文件和目录。 在开发中，读取文件、生成文件、删除文件、修改文件的属性时经常会用到本类。
        File类的常见构造方法：public File(String pathname)
        以pathname为路径创建File对象，如果pathname是相对路径，则默认的当前路径在系统属性user.dir中存储*/
        System.out.println(System.getProperty("user.dir"));
        File f = new File("a.txt"); //相对路径：默认放到user.dir目录下面
        f.createNewFile();//创建文件
        File f2 = new File("/Users/zhangcongrong/IdeaProjects/zcrexercise/b.txt");//绝对路径
        f2.createNewFile();

        //通过File对象可以访问文件的属性
        /*exists isDirectory isFile lastModified length getName getPath*/
        File f3 = new File("b.txt");
        System.out.println("File是否存在："+f3.exists());
        System.out.println("File是否是目录："+f3.isDirectory());
        System.out.println("File是否是文件："+f3.isFile());
        System.out.println("File最后修改时间："+new Date(f3.lastModified()));
        System.out.println("File的大小："+f3.length());
        System.out.println("File的文件名："+f3.getName());
        System.out.println("File的目录路径："+f3.getPath());

        //File类创建文件或目录的方法列表
        /*通过File对象创建空文件或目录(在该对象所指的文件或目录不存在的情况下)
        createNewFile delete mkdir mkdirs*/
        File f4 = new File("c.txt");
        f4.createNewFile(); // 会在d盘下面生成c.txt文件
        f4.delete(); // 将该文件或目录从硬盘上删除
        File f5 = new File("/newfile/files/mkd");
        boolean flag = f5.mkdir(); //目录结构中有一个不存在，则不会创建整个目录树
        System.out.println(flag);//创建失败
        File f6 = new File("/Users/zhangcongrong/IdeaProjects/zcrexercise/newfile/files/mkds");
        boolean flag2 = f6.mkdirs();//目录结构中有一个不存在也没关系；创建整个目录树
        System.out.println(flag2);//创建成功

        //File类的综合应用
        //指定一个文件
        File f7 = new File("sxt/b.txt");
        //判断该文件是否存在
        boolean flag3 = f7.exists();
        //如果存在就删除，如果不存在就创建
        if(flag3){
            //删除
            boolean flagd = f7.delete();
            if(flagd){
                System.out.println("删除成功");
            }else{
                System.out.println("删除失败");
            }
        }else{
            //创建
            boolean flagn = true;
            try {
                //如果目录不存在，先创建目录
                File dir = f7.getParentFile();
                dir.mkdirs();
                //创建文件
                flagn = f7.createNewFile();
                System.out.println("创建成功");
            } catch (IOException e) {
                System.out.println("创建失败");
                e.printStackTrace();
            }
        }
        //文件重命名(同学可以自己测试一下)
        //f7.renameTo(new File("readme.txt"));

        //递归遍历目录结构和树状展现
        //本节结合前面给大家讲的递归算法，展示目录结构。大家可以先建立一个目录，下面增加几个子文件夹或者文件，用于测试。
        File f8 = new File("movie");
        printFile(f8, 0);



        //File类_API使用规则和学习
       /* File类的继承体系
        public class File extends Object implements Serializable,Comparable<File>
        不代表文件本身，而代表路径名。
        常量:
        pathSeparator、与系统相关的路径分隔符字符，为方便起见，表示为字符串
        pathSeparatorChar、与系统相关的路径分隔符
        separator、与系统相关的默认名称名称-分隔符字符，以方便的方式表示为字符串
        separatorChar与系统相关的默认名称分隔符
        构造方法：
        File(File parent,String child)
        File(String pathname)
        File(String parent,String child)
        File(URI uri)
        方法：
        canExecute()
        canRead()
        canWrite
        compareTo(FIle pathname)
        createNewFile()
        createTempFile()

        mkdir() mkdirs()
        list()
        listFiles()
        listRoots()
        */
        //构建File对象
        String path01 ="/Users/zhangcongrong/IdeaProjects/zcrexercise/goujian/g.txt";

        //1、构建File对象
        File src01 = new File(path01);
        System.out.println(src01.length());

        //2、构建File对象
        src01 = new File("D/Users/zhangcongrong/IdeaProjects/zcrexercise/goujian","o.txt");
        src01 = new File("/Users/zhangcongrong/IdeaProjects/zcrexercise","/goujian/u.txt");
        System.out.println(src01.length());

        //3、构建File对象
        src01 = new File(new File("/Users/zhangcongrong/IdeaProjects/zcrexercise/goujian"),"j.txt");
        System.out.println(src01.length());

        /**
         * 构建File对象
         * 相对路径与绝对路径
         * 1)、存在盘符: 绝对路径
         * 2)、不存在盘符:相对路径  ,当前目录 user.dir
         */
        String path02 ="/Users/zhangcongrong/IdeaProjects/zcrexercise/goujian/i.txt";

        //绝对路径
        File src02 = new File(path02);
        System.out.println(src02.getAbsolutePath());

        //相对路径
        System.out.println(System.getProperty("user.dir"));
        src02 = new File("a.txt");
        System.out.println(src02.getAbsolutePath());


        //构建一个不存在的文件
        src02 = new File("aaa/n.txt");
        System.out.println(src02.getAbsolutePath());


        /**
         * 名称或路径
         * getName()   : 名称
         * getPath()    : 相对、绝对
         getAbsolutePath() :绝对
         getParent(): 上路径 返回null
         */
        File src03 = new File("IO_study01/1212.txt");

        //基本信息
        System.out.println("名称:"+src03.getName());
        System.out.println("路径:"+src03.getPath());
        System.out.println("绝对路径:"+src03.getAbsolutePath());
        System.out.println("父路径:"+src03.getParent());
        System.out.println("父对象:"+src03.getParentFile().getName());

        /**
         * 文件状态
         * 1、不存在: exists
         * 2、存在
         *      文件: isFile
         *      文件夹: isDirectory
         */
        File src04 = new File("IO_study01/IO.png");
        System.out.println(src04.getAbsolutePath());
        System.out.println("是否存在:"+src04.exists());
        System.out.println("是否文件:"+src04.isFile());
        System.out.println("是否文件夹:"+src04.isDirectory());

        src04 = new File("IO.png");
        System.out.println("----------");
        System.out.println("是否存在:"+src04.exists());
        System.out.println("是否文件:"+src04.isFile());
        System.out.println("是否文件夹:"+src04.isDirectory());

        src04 = new File("IO_study01");
        System.out.println("----------");
        System.out.println("是否存在:"+src04.exists());
        System.out.println("是否文件:"+src04.isFile());
        System.out.println("是否文件夹:"+src04.isDirectory());

        //文件状态
        src04 = new File("xxx");
        if(null == src04 || !src04.exists()) {
            System.out.println("文件不存在");
        }else {
            if(src04.isFile()) {
                System.out.println("文件操作");
            }else {
                System.out.println("文件夹操作");
            }
        }

        //length()文件的字节数
        System.out.println(src04.length());
        //createNewFile()  : 不存在才创建，存在创建成功
        // delete():删除已经存在的文件
        File src05 = new File("io.txt");
        boolean flag05 =src05.createNewFile();
        System.out.println(flag05);
        flag05 = src05.delete();
        System.out.println(flag05);

        System.out.println("----------");
        //不是文件夹
        src05 = new File("IO_study02");
        flag05 =src05.createNewFile();
        System.out.println(flag05);

        flag05 = src05.delete();
        System.out.println(flag05);


        //补充:  con com3... 操作系统的设备名，不能正确创建
        src05 = new File("IO_study01/con");
        //src05.createNewFile();Exception in thread "main" java.io.IOException: No such file or directory













       //File_文件夹创建_遍历(只是下一级，不是子孙级，子孙级需要递归)
        /**
         *创建目录
         *1、mkdir():确保上级目录存在，不存在创建失败
         *2、mkdirs():上级目录可以不存在，不存在一同来创建
         *列出下一级
         *1.list():列出下级名称
         *2.listFiles():列出下级File对象
         *列出所有盘符listRoots()
         */
        File dir=new File("sxt");
        boolean fla=dir.mkdirs();
        System.out.println(fla);
        dir=new File("movie");
        fla=dir.mkdirs();
        System.out.println(fla);
        //下级名称  list
        String[] subNames=dir.list();
        for(String s:subNames){
            System.out.println(s);
        }
        //下级对象  listFiles()
        File[] subFiles=dir.listFiles();
        for(File s:subFiles){
            System.out.println(s.getAbsolutePath());
        }
        //所有盘符
        File[] roots=dir.listRoots();
        for(File r:roots){
            System.out.println(r.getAbsolutePath());
        }

        /*递归列出所有的子孙文件
        递归：方法自己调用自己
        递归头：何时结束递归
        递归体：重复调用
                递归占用内存*/
        printTen(1);
        //File_文件夹统计 使用递归打印子孙级目录和文件的名称
        File src=new File("movie");
        printName(src,0);


        //如何统计文件夹大小？
        count(src);
        System.out.println(len);

        //统计文件夹大小使用面向对象思想
        DirCount dir2=new DirCount("movie");
        System.out.println(dir2.getLen()+"--->"+dir2.getFileSize()+"-->"+dir2.getDirSize());

        //File_字符集乱码
        /*文件编码
        字符集：Java字符使用16位的双字节存储，但是在实际文件存储的数据有各种字符集，需要正确操作，否则就有乱码的发生。
        US_ASCII ISO-8859-1 UTF-8(变长的unicode字符[1~3个字节]，国际通用) UTF-16BE UTF-16LE UTF-16
        字节（101）<-----编码（encode）-----字符（中）
                  -----解码（decode）------>*/
        //编码：字符串--》字节getBytes()
        String msg="性命生命使命a";
        //编码：字节数组
        byte[] datas=msg.getBytes();//默认使用工程的字符集UTF-8
        System.out.println(datas.length);//19，中文3个字节，英文一个字节
        //编码：其他字符集
        datas=msg.getBytes("UTF-16LE");
                System.out.println(datas.length);//14，每一个都是两个字节
        datas=msg.getBytes("GBK");
                System.out.println(datas.length);//13，		中文2个字节英文1个字节


        //解码：字节--》字符串new String()四个参数String​(byte[] bytes, int offset, int length, String charsetName)
        msg=new String(datas,0,datas.length,"UTF-8");
        System.out.println(msg);//性命生命使命a
        //乱码1.字节数不够2.字符集不统一
        msg=new String(datas,0,datas.length-2,"UTF-8");
        System.out.println(msg);//乱码
        msg=new String(datas,0,datas.length,"GBK");
        System.out.println(msg);//乱码

        //File类的全部源代码
        //PathDemo01
        String path ="\\Users\\zhangcongrong\\IdeaProjects\\zcrexercise\\movie\\china";
        System.out.println(File.separatorChar);
        //建议
        //1、/
        path = "/Users/zhangcongrong/IdeaProjects/zcrexercise/movie/china";
        System.out.println(path);
        //2、常量拼接
        path ="D:"+File.separator+"java300"+File.separator+"IO_study01"+File.separator+"IO.png";
        path = File.separator+"Users"+File.separator+"zhangcongrong"+File.separator+"IdeaProjects"+File.separator+
                "zcrexercise"+File.separator+"movie"+File.separator+"china";
        System.out.println(path);

    }
    /**
     * 打印文件信息
     * @param file 文件名称
     * @param level 层次数(实际就是：第几次递归调用)
     */
    static void printFile(File file, int level) {//level是用来显示是第几层调用的
        //输出层次数
        for (int i = 0; i < level; i++) {
            System.out.print("-");
        }
        //输出文件名
        System.out.println(file.getName());

        //如果file是目录，则获取子文件列表，并对每个子文件进行相同的操作
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (File temp : files) {
                //递归调用该方法：注意等+1
                printFile(temp, level + 1);
            }
        }
    }


    //打印1~10的数字
    public static void printTen(int n){
        if(n>10){//递归头:结束递归
            return;
        }
        System.out.println(n);
        printTen(n+1);//递归体：方法自己调用自己
    }

    public static void printName(File src,int deep){
        //控制前面层次
        for(int i=0;i<deep;i++){
            System.out.print("-");
        }
        System.out.println(src.getName());
        if(null==src||!src.exists()){//递归头
            return;
        }else if(src.isDirectory()){
            for(File s:src.listFiles()){
                printName(s,deep+1);//递归体
            }
        }
    }

    //如何统计文件夹大小？
    private static long len=0;
    public static void count(File src) {  //递归头
        if (null != src && src.exists()) {
            if (src.isFile()) {//大小     递归体
                len += src.length();
            } else {//子孙级
                for (File s : src.listFiles()) {
                    count(s);
                }
            }
        }
    }

}

//统计文件夹大小使用面向对象思想
class DirCount {
    private long len;//大小
    private String path;//文件夹路径
    private File src;//源
    private int FileSize;//文件的个数
    private int DirSize;//文件夹的个数

    public DirCount(String path) {
        this.path = path;
        this.src = new File(path);
        count(this.src);
    }

    private void count(File src) {
        if (null != src && src.exists()) {
            if (src.isFile()) {//大小     递归体
                len += src.length();
                this.FileSize++;
            } else {//子孙级
                this.DirSize++;
                for (File s : src.listFiles()) {
                    count(s);
                }
            }
        }
    }

    public long getLen() {
        return len;
    }

    public void setLen(long len) {
        this.len = len;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public File getSrc() {
        return src;
    }

    public void setSrc(File src) {
        this.src = src;
    }

    public int getFileSize() {
        return FileSize;
    }

    public void setFileSize(int fileSize) {
        FileSize = fileSize;
    }

    public int getDirSize() {
        return DirSize;
    }

    public void setDirSize(int dirSize) {
        DirSize = dirSize;
    }
}
