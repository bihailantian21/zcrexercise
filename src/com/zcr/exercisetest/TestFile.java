package com.zcr.exercisetest;

import java.io.File;
import java.io.IOException;
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

}
