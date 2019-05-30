package com.zcr.exercisetest;

import java.io.*;
import java.net.URL;
import java.nio.CharBuffer;
import java.util.Date;
import java.util.logging.Filter;


/**
 * 1.FileInputStream FileOutputStream
 *abc你想成为怎样的人呢？。
 * 编码：字符串到字节数组getBytes() 解码：字节数组到字符串new String()
 * 1.通过字节或字节数组的方式读取文件\通过字符串或字节数组的方式写数据到文件中\复制
 * 2.FileReader/FileWriter 通过字符/字符数组的方式读取文件\通过字符数组或字符串的方式写出或追加数据到文件中\复制
 * 3.BufferedInputStream/BufferedOutputStream 读\写\复制
 * 4.BufferedReader/BufferedWriter 逐行读取\逐行写入\复制
 * 5.ByteArrayInputStream/ByteArrayOutputStream
 * 6.DataInputStream/DataOutputStream
 * 7.ObjectInputStream/ObjectOutputStream
 * 8.PrintStream
 * 9.InputStreamReader/OutputStreamWriter 将字节流转化成字符流
 * 10.抽象组件、具体组件、抽象装饰类、具体装饰类
 *
 *         InputStream          这是我们的抽象组件
 *         -FileInputStream
 *         -StringBufferInputStream
 *         -ByteArrayInputStream    这三类是可以被装饰者包起来的具体组件。
 *         -FilterInputStream       这是一个抽象装饰者
 *         --PushbackInputStream
 *         --BufferedInputStream
 *         --DataInputStream
 *         --LineNumberInputStream    这四个是我们的具体装饰者
 *
 * InputStream                     OutputStream
 *             -FileInputStream*                -FileOutputStream*
 *             -FilterInputStream              -FilterOutputStream
 *                 --BufferedInputStream*           --BufferedOutputStream*
 *                 --DataInputStream*               --DataOutputStream*
 *                 --LineNumberInputSream           --PrintStream*
 *                 --PushbackInputStream
 *             -PipedInputStream               -PipedOutputStream
 *             -SequenceInputStream            -ByteArrayOutputStream*
 *             -ByteArrayInputStream*          -ObjectOutputStream*
 *             -StringBufferInputStream
 *             -ObjectInputStream*
 *
 * Reader                          Writer
 *             -InputStreamReader*              -OutputStreamWriter*
 *                 --FileReader*                    --FileWriter*
 *             -BufferedReader*                 -BufferedWriter*
 *                 --LineNumberReader
 *             -FilterReader                   -FilterWriter
 *                 --PushbackReader
 *             -CharArrayReader                -CharArrayWriter
 *             -PipedReader                    -PipedWriter
 *             -StringReader                   -StringWriter
 *
 *
 *
 *
 */

public class TestIO {
    //输入输出(Input/Output)
    /*程序运行需要数据，数据的获取往往需要跟外部系统进行通信，外部系统可能是文件、数据库、其他程序、网络、IO设备等等。
    外部系统比较复杂多变，那么我们有必要通过某种手段进行抽象、屏蔽外部的差异，从而实现更加便捷的编程。
    输入(Input)指的是：可以让程序从外部系统获得数据(核心含义是“读”，读取外部数据)。常见的应用：
    Ø 读取硬盘上的文件内容到程序。例如：播放器打开一个视频文件、word打开一个doc文件。
    Ø 读取网络上某个位置内容到程序。例如：浏览器中输入网址后，打开该网址对应的网页内容;下载网络上某个网址的文件。
    Ø 读取数据库系统的数据到程序。
    Ø 读取某些硬件系统数据到程序。例如：车载电脑读取雷达扫描信息到程序;温控系统等。
    输出(Output)指的是：程序输出数据给外部系统从而可以操作外部系统(核心含义是“写”，将数据写出到外部系统)。常见的应用有：
    Ø 将数据写到硬盘中。例如：我们编辑完一个word文档后，将内容写到硬盘上进行保存。
    Ø 将数据写到数据库系统中。例如：我们注册一个网站会员，实际就是后台程序向数据库中写入一条记录。
    Ø 将数据写到某些硬件系统中。例如：导弹系统导航程序将新的路径输出到飞控子系统，飞控子系统根据数据修正飞行路径。
    java.io包为我们提供了相关的API，实现了对所有外部系统的输入输出操作，这就是我们这章所要学习的技术。
    */

    //数据源
    /*数据源data source，提供数据的原始媒介。
    常见的数据源有：数据库、文件、其他程序、内存、网络连接、IO设备。
    数据源分为：源设备、目标设备。
            1.源设备：为程序提供数据，一般对应输入流。
            2.目标设备：程序数据的目的地，一般对应输出流。*/

    //流
    /*流是一个抽象、动态的概念，是一连串连续动态的数据集合。
    对于输入流而言，数据源就像水箱，流(stream)就像水管中流动着的水流，程序就是我们最终的用户。
    我们通过流(A Stream)将数据源(Source)中的数据(information)输送到程序(Program)中。
    对于输出流而言，目标数据源就是目的地(dest)，我们通过流(A Stream)将程序(Program)中的数据(information)输送到目的数据源(dest)中。

    菜鸟雷区：输入/输出流的划分是相对程序而言的，并不是相对数据源。
    一切以程序为中心。
    输入流：数据源到程序（InputStream、Reader）读进来
    输出流：程序到目的地（OutputStream、Writer）写出去

    核心类：
    File
    InputStream OutputStream
    Reader Writer
    Closeable Flushable Serializable
    */

        public static void main(String[] args) {
            //第一个简单的IO流程序及深入理解
            /*当程序需要读取数据源的数据时，就会通过IO流对象开启一个通向数据源的流，
            通过这个IO流对象的相关方法可以顺序读取数据源中的数据。
            使用流读取文件内容(不规范的写法，仅用于测试)*/
            try {
                //创建输入流
                FileInputStream fis = new FileInputStream("a.txt"); // 文件内容是：abc
                //一个字节一个字节的读取数据
                int s1 = fis.read(); // 打印输入字符a对应的ascii码值97
                int s2 = fis.read(); // 打印输入字符b对应的ascii码值98
                int s3 = fis.read(); // 打印输入字符c 对应的ascii码值99
                int s4 = fis.read(); // 由于文件内容已经读取完毕，返回-1
                System.out.println(s1);
                System.out.println(s2);
                System.out.println(s3);
                System.out.println(s4);
                // 流对象使用完，必须关闭！不然，总占用系统资源，最终会造成系统崩溃！
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            /*1.在上面示例中我们读取的文件内容是已知的，因此可以使用固定次数的“int s= fis.read();”语句读取内容，
            但是在实际开发中通常我们根本不知道文件的内容，因此我们在读取的时候需要配合while循环使用。
            2.为了保证出现异常后流的正常关闭，通常要将流的关闭语句要放到finally语句块中，并且要判断流是不是null。
            */
            //使用流读取文件内容(经典代码）如下代码是一段非常典型的IO流代码，其他流对象的使用也基本是同样的模式!
            FileInputStream fis2 = null;
            try {
                fis2 = new FileInputStream("a.txt"); // 内容是：abc
                StringBuilder sb = new StringBuilder();
                int temp = 0;
                //当temp等于-1时，表示已经到了文件结尾，停止读取
                while ((temp = fis2.read()) != -1) {
                    sb.append((char) temp);
                }
                System.out.println(sb);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    //这种写法，保证了即使遇到异常情况，也会关闭流对象。
                    if (fis2 != null) {
                        fis2.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }


            //Java中流的概念细分
            /*按流的方向分类：
            1.输入流：数据流向是数据源到程序(以InputStream、Reader结尾的流)。
            source------------>(reads)program
            2.输出流：数据流向是程序到目的地(以OutPutStream、Writer结尾的流)。
            program(writes)----------->dest
            按处理的数据单元分类：
            1.字节流：以字节为单位获取数据，命名上以Stream结尾的流一般是字节流，如FileInputStream、FileOutputStream。
            InputStream                     OutputStream
            -FileInputStream*                -FileOutputStream*
            -FilterInputStream              -FilterOutputStream
                --BufferedInputStream*           --BufferedOutputStream*
                --DataInputStream*               --DataOutputStream*
                --LineNumberInputSream           --PrintStream*
                --PushbackInputStream
            -PipedInputStream               -PipedOutputStream
            -SequenceInputStream            -ByteArrayOutputStream*
            -ByteArrayInputStream*          -ObjectOutputStream*
            -StringBufferInputStream
            -ObjectInputStream*
            2.字符流：以字符为单位获取数据，命名上以Reader/Writer结尾的流一般是字符流，如FileReader、FileWriter。
            Reader                          Writer
            -InputStreamReader*              -OutputStreamWriter*
                --FileReader*                    --FileWriter*
            -BufferedReader*                 -BufferedWriter*
                --LineNumberReader
            -FilterReader                   -FilterWriter
                --PushbackReader
            -CharArrayReader                -CharArrayWriter
            -PipedReader                    -PipedWriter
            -StringReader                   -StringWriter
            因为文件编码的不同，从而有了对字符进行高效操作的字符流对象。
            原理：底层还是基于字节流操作自动搜寻了指定的码表。字符集：相当于一个大字典。
            Byte Streams(InputStream/OutputStream)----------->Byte(8bit)
            Character Streams(Reader/Writer)---------------->char(16bit)
            按处理对象不同分类：
            1.节点流：可以直接从数据源或目的地读写数据，如FileInputStream、FileReader、DataInputStream等。
            有File/Byte开头的是节点流
            有Buffered开头的是处理流
            2.处理流：不直接连接到数据源或目的地，是”处理流的流”。通过对其他流的处理提高程序的性能，如BufferedInputStream、BufferedReader等。
            处理流也叫包装流。
            装饰设计模式（用的舒服，提高性能）
            节点流处于IO操作的第一线，所有操作必须通过它们进行;
            处理流可以对节点流进行包装，提高性能或提高程序的灵活性。


            简单做个总结：
            1.InputStream/OutputStream
            字节流的抽象类。
            2.Reader/Writer
            字符流的抽象类。

            3.FileInputStream/FileOutputStream
            节点流：以字节为单位直接操作“文件”。
            4.ByteArrayInputStream/ByteArrayOutputStream
            节点流：以字节为单位直接操作“字节数组对象”。
            5.ObjectInputStream/ObjectOutputStream
            处理流：以字节为单位直接操作“对象”。
            6.DataInputStream/DataOutputStream
            处理流：以字节为单位直接操作“基本数据类型与字符串类型”。
            9. BufferedInputStream/BufferedOutputStream
            处理流：将InputStream/OutputStream对象进行包装，增加缓存功能，提高 读写效率。
            11.PrintStream
            处理流：将OutputStream进行包装，可以方便地输出字符，更加灵活。

            8.BufferedReader/BufferedWriter
            处理流：将Reader/Writer对象进行包装，增加缓存功能，提高读写效率
            10.InputStreamReader/OutputStreamWriter
            处理流：将字节流对象转化成字符流对象。
            7.FileReader/FileWriter
            节点流：以字符为单位直接操作“文本文件”(注意：只能读写文本文件)。

            老鸟建议：上面的解释，一句话就点中了流的核心作用。大家在后面学习的时候，用心体会。

            */


            //四大IO抽象类
            /*InputStream/OutputStream和Reader/writer类是所有IO流类的抽象父类，我们有必要简单了解一下这个四个抽象类的作用。
            然后，通过它们具体的子类熟悉相关的用法。把握父类！
            InputStream字节输入流
            int read() int read(byte[] b) int read(byte[] b,int off,int len) close()
            int available() mark(int readlimit) boolean markSupported() byte[] readAllBytes() reset() long skip()
            此抽象类是表示字节输入流的所有类的父类。InputSteam是一个抽象类，它不可以实例化。 数据的读取需要由它的子类来实现。
            根据节点的不同，它派生了不同的节点流子类 。
            继承自InputSteam的流都是用于向程序中输入数据，且数据的单位为字节(8 bit)。
            常用方法：
            int read()：读取一个字节的数据，并将字节的值作为int类型返回(0-255之间的一个值)。如果未读出字节则返回-1(返回值为-1表示读取结束)。
            void close()：关闭输入流对象，释放相关系统资源。


            OutputStream字节输出流
            write(int b) write(byte[] b) write(byte[] b,int off,int len) flush() close()
            此抽象类是表示字节输出流的所有类的父类。输出流接收输出字节并将这些字节发送到某个目的地。
            常用方法：
            void write(int n)：向目的地中写入一个字节。
            void close()：关闭输出流对象，释放相关系统资源。

            Reader字符输入流
            int read() int read(char[] cbuf) int read(char[] cbuf,int off,int len) close()
            mark(int readAheadLimit) boolean markSupported() int read(CharBuffer target) reset() long skip(long n)
            Reader用于读取的字符流抽象类，数据单位为字符。
            int read(): 读取一个字符的数据，并将字符的值作为int类型返回(0-65535之间的一个值，即Unicode值)。如果未读出字符则返回-1(返回值为-1表示读取结束)。
            void close() ： 关闭流对象，释放相关系统资源。

            Writer字符输出流
            write(String str) write(String str,int off,int len)  write(int c) flush() close()
            write(char[] cbuf) write(char[] cbuf,int off,int len)
            append(char c) append(CharSequence csq) append(CharSequence csq,int start,int end)
            Writer用于写入的字符流抽象类，数据单位为字符。
            void write(int n)： 向输出流中写入一个字符。
            void close() ： 关闭输出流对象，释放相关系统资源。*/

            //IO标准步骤
            //1.创建源
            //2.选择流
            //3.操作
            //4.释放
            //1.创建源
            File src = new File("a.txt");
            //2.选择流
            InputStream is = null;
            try {
                is = new FileInputStream(src);
                //3.读取
                int temp;
                while ((temp = is.read()) != -1) {
                    System.out.println((char) temp);
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                //文件的末尾返回-1
                //4.释放
                try {
                    if (null != is) {
                        is.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            //常见的流
            /*文件字节流
            FileInputStream通过字节的方式读取文件，适合读取所有类型的文件(图像、视频、文本文件等)。Java也提供了FileReader专门读取文本文件。
            FileOutputStream 通过字节的方式写数据到文件中，适合所有类型的文件。Java也提供了FileWriter专门写入文本文件。
             */
            //将文件内容读取到程序中
            //1.一个一个字节的读，通过循环一个一个字节的读。read()
            //2.一段一段的读,通过缓冲区 read(byte[] b)
            //1.创建源
            File src01 = new File("b.txt");
            //2.选择流
            InputStream is01 = null;
            try {
                is01 = new FileInputStream(src01);
                //3.操作（读取，分段读取）
                byte[] car = new byte[1024];//缓冲容器 [1024]1k1k的读[3]3个字节3个字节的读
                int len = -1;//接收长度
                while ((len = is01.read(car)) != -1) {
                    //字节数组如何还原为字符串.字节数组--》字符串（解码）
                    String str = new String(car, 0, len);
                    System.out.println(str);
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                //文件的末尾返回-1
                //4.释放
                try {
                    if (null != is01) {
                        is.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            //将字符串/字节数组的内容写入到文件中
            /*构造方法：
            FileOutputStream(File files)创建文件输出流以写入由指定的File对象表示的文件
            FileOutputStream(FileDescriptor fdObj)创建文件输出流以写入指定的文件描述符，表示与文件系统中实际文件的现有连接
            FileOutputStream(File files,boolean append)创建文件输出流以追加的形式写入由指定的File对象表示的文件
            FileOutputStream(String name)创建文件输出流以指定的名称写入文件
            FileOutputStream(String name,boolean append)创建文件输出流以指定的名称写入文件
            */
            //1.创建源
            File desc01 = new File("c.txt");//inputstream中你要读取字节必须存在，而这里输出可以不存在系统自动创建
            //2.选择流
            OutputStream os01 = null;
            try {
                os01 = new FileOutputStream(desc01, true);//默认是true表明以追加的形式写入文件，若为false则覆盖
                //3.操作（写出）
                String msg = "IO is so easy";
                byte[] datas = msg.getBytes();//字符串--》字节数组（编码）
                os01.write(datas, 0, datas.length);//该方法不再一个字节一个字节地写入，void write(byte[ ] b, int off, int length)，这个方法也是写入一个字节数组，但是我们程序员可以指定从字节数组的哪个位置开始写入，写入的长度是多少。
                os01.flush();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                //4.释放
                try {
                    if (null != os01) {
                        os01.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            //文件的复制
            //程序作为一个中转，读一个内容，写一个内容，即可实现复制。
            //1.创建源
            File src02 = new File("c.txt");//源头
            File desc02 = new File("d.txt");//目的地  //inputstream中你要读取字节必须存在，而这里输出可以不存在系统自动创建
            //2.选择流
            InputStream is02 = null;
            OutputStream os02 = null;
            try {
                is02 = new FileInputStream(src02);
                os02 = new FileOutputStream(desc02, true);//默认是true表明以追加的形式写入文件，若为false则覆盖
                //3.操作（读取，分段读取）
                byte[] flush = new byte[1024];//缓冲容器 [1024]1k1k的读.为了提高效率，设置缓存数组！（读取的字节数据会暂存放到该字节数组中）
                int len = -1;
                while ((len = is02.read(flush)) != -1) {
                    //3.操作（写出）
                    //读的存在了字节中，那么就直接将这个字节进行写出
                    os02.write(flush, 0, len);
                    /*将缓存数组中的数据写入文件中，注意：写入的是读取的真实长度；
                     *如果使用fos.write(buffer)方法，那么写入的长度将会是1024，即缓存
                     *数组的长度*/
                }

                os02.flush();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                //4.释放，分别关闭，先打开的后关闭
                try {
                    if (null != is) {
                        is02.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    if (null != os02) {
                        os02.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
            /*总结：
            在使用文件字节流时，我们需要注意以下两点：
            1.为了减少对硬盘的读写次数，提高效率，通常设置缓存数组。
            相应地，读取时使用的方法为：read(byte[] b);
            写入时的方法为：write(byte[ ] b, int off, int length)。
            2.程序中如果遇到多个流，每个流都要单独关闭，防止其中一个流出现异常后导致其他流无法关闭的情况。
           */

            //文件字符流
           /* 前面介绍的文件字节流可以处理所有的文件，但是字节流不能很好的处理Unicode字符，经常会出现“乱码”现象。
           所以，我们处理文本文件，一般可以使用文件字符流，它以字符为单位进行操作。
            FileReader:通过字符的方式读取文件，仅适合字符文件
            FileWriter:通过字符的方式写出或追加数据到文件中，仅适合字符文件。特殊之处：append
            之前处理的是字节数组
            这里可以用来处理字符数组，即字符串
            */
            //字符输入流
            //1.创建源
            File src03 = new File("a.txt");
            //2.选择流
            Reader reader03 = null;
            try {
                reader03 = new FileReader(src);
                //3.操作（读取，分段读取）
                char[] flush = new char[1024];//缓冲容器 [1024]1k1k的读
                int len = -1;//接收长度
                while ((len = reader03.read(flush)) != -1) {
                    //字符数组--》字符串
                    String str = new String(flush, 0, len);
                    System.out.println(str);
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                //文件的末尾返回-1
                //4.释放
                try {
                    if (null != reader03) {
                        reader03.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            //字符输出流
            //1.创建源
            File desc03 = new File("dest.txt");//inputstream中你要读取字节必须存在，而这里输出可以不存在系统自动创建
            //2.选择流
            Writer writer03 = null;
            try {
                writer03 = new FileWriter(desc03);//默认是true表明以追加的形式写入文件，若为false则覆盖
                //3.操作（写出）
			/*写法一
			String msg="很简单";
			char[] datas=msg.toCharArray();//字符串--》字符数组
			writer.write(datas,0,datas.length);
			*/
			/*写法二
			String msg="很简单";
			writer.write(msg);
			writer.write(“的”);
			*/
                //写法三
                writer03.append("很简单").append("的");
                writer03.flush();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                //4.释放
                try {
                    if (null != writer03) {
                        writer03.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            //使用FileReader与FileWriter实现文本文件的复制
            // 写法和使用Stream基本一样。只不过，读取时是读取的字符。
            FileReader fr04 = null;
            FileWriter fw04 = null;
            int len = 0;
            try {
                fr04 = new FileReader("dest.txt");
                fw04 = new FileWriter("destdest.txt");
                //为了提高效率，创建缓冲用的字符数组
                char[] buffer = new char[1024];
                //边读边写
                while ((len = fr04.read(buffer)) != -1) {
                    fw04.write(buffer, 0, len);
                }

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (fw04 != null) {
                        fw04.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    if (fr04 != null) {
                        fr04.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }


            //缓冲字节流
           /* 注意：看完装饰模式之后再返回来看缓冲字节流和缓冲字符流
            装饰模式的具体：
            对字节流的缓冲，底层一定要有一个节点流，否则就成了无本之木、无源之水
            提高了读写的性能，IO
            释放的时候，只需要释放外部的节点流，内部会自动释放

            Java缓冲流本身并不具有IO流的读取与写入功能，只是在别的流(节点流或其他处理流)上加上缓冲功能提高效率，
            就像是把别的流包装起来一样，因此缓冲流是一种处理流(包装流)。
            当对文件或者其他数据源进行频繁的读写操作时，效率比较低，这时如果使用缓冲流就能够更高效的读写信息。
            因为缓冲流是先将数据缓存起来，然后当缓存区存满后或者手动刷新时再一次性的读取到程序或写入目的地。
            因此，缓冲流还是很重要的，我们在IO操作时记得加上缓冲流来提升性能。
            BufferedInputStream和BufferedOutputStream这两个流是缓冲字节流，通过内部缓存数组来提高操作流的效率。
            下面我们通过两种方式(普通文件字节流与缓冲文件字节流)实现一个视频文件的复制，来体会一下缓冲流的好处。

            构造方法：
            BufferedInputStream(InputStream is)
            BufferedInputStream(InputStream is,int size)
            BufferedOutputStream(OutputStream os)
            BufferedOutputStream(OutputStream os,int size)
            */

            //文件字节输入流 加入缓冲流
            //1.创建源
            File src05 = new File("a.txt");
            //2.选择流
            InputStream is05 = null;
            BufferedInputStream bis05 = null;
            try {
                is05 = new FileInputStream(src05);
                bis05 = new BufferedInputStream(is05);//is=new BufferedInputStream(new FileInputStream(src));
                //3.操作（读取，分段读取）
                byte[] car05 = new byte[6];//缓冲容器 [1024]1k1k的读。中文是1个字3个字节
                int len05 = -1;//接收长度
                while ((len05 = bis05.read(car05)) != -1) {
                    //字节数组如何还原为字符串.字节数组--》字符串（解码）
                    String str05 = new String(car05, 0, len05);
                    System.out.println(str05);
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                //文件的末尾返回-1
                //4.释放
                try {
                    if (null != is05) {
                        is05.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    if (null != bis05) {
                        bis05.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            //文件字节输出流 加入缓冲流
            //1.创建源
            File desc05 = new File("buffer.txt");//inputstream中你要读取字节必须存在，而这里输出可以不存在系统自动创建
            //2.选择流
            OutputStream os05 = null;
            try {
                os05 = new BufferedOutputStream(new FileOutputStream(desc05));//默认是true表明以追加的形式写入文件，若为false则覆盖
                //3.操作（写出）
                String msg = "IO is so easy";
                byte[] datas = msg.getBytes();//字符串--》字节数组（编码）
                os05.write(datas, 0, datas.length);
                os05.flush();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                //4.释放
                try {
                    if (null != os05) {
                        os05.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            // 使用缓冲字节流实现复制
            long time1 = System.currentTimeMillis();
            FileInputStream fis06 = null;
            BufferedInputStream bis06 = null;
            FileOutputStream fos06 = null;
            BufferedOutputStream bos06 = null;
            int temp = 0;
            try {
                fis06 = new FileInputStream("copysrc.png");
                fos06 = new FileOutputStream("copydesc01.png");
                //使用缓冲字节流包装文件字节流，增加缓冲功能，提高效率
                //缓存区的大小（缓存数组的长度）默认是8192，也可以自己指定大小
                bis06 = new BufferedInputStream(fis06);
                bos06 = new BufferedOutputStream(fos06);
                while ((temp = bis06.read()) != -1) {
                    bos06.write(temp);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                //注意：增加处理流后，注意流的关闭顺序！“后开的先关闭！”
                try {
                    if (bos06 != null) {
                        bos06.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    if (bis06 != null) {
                        bis06.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    if (fos06 != null) {
                        fos06.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    if (fis06 != null) {
                        fis06.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            long time2 = System.currentTimeMillis();
            System.out.println("缓冲字节流花费的时间为：" + (time2 - time1));

            // 使用普通字节流实现复制
            //1.创建源
            long time3 = System.currentTimeMillis();
            File src07 = new File("copysrc.png");//源头
            File desc07 = new File("copydesc02.png");//目的地  //inputstream中你要读取字节必须存在，而这里输出可以不存在系统自动创建
            //2.选择流
            InputStream is07 = null;
            OutputStream os07 = null;
            try {
                is07 = new FileInputStream(src07);
                os07 = new FileOutputStream(desc07, true);//默认是true表明以追加的形式写入文件，若为false则覆盖
                //3.操作（读取，分段读取）
                byte[] flush = new byte[1024];//缓冲容器 [1024]1k1k的读.为了提高效率，设置缓存数组！（读取的字节数据会暂存放到该字节数组中）
                int len07 = -1;
                while ((len07 = is07.read(flush)) != -1) {
                    //3.操作（写出）
                    //读的存在了字节中，那么就直接将这个字节进行写出
                    os07.write(flush, 0, len07);
                    /*将缓存数组中的数据写入文件中，注意：写入的是读取的真实长度；
                     *如果使用fos.write(buffer)方法，那么写入的长度将会是1024，即缓存
                     *数组的长度*/
                }

                os07.flush();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                //4.释放，分别关闭，先打开的后关闭
                try {
                    if (null != is07) {
                        is07.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    if (null != os07) {
                        os07.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
            long time4 = System.currentTimeMillis();
            System.out.println("普通字节流花费的时间为：" + (time4 - time3));
           /* 注意
            1.在关闭流时，应该先关闭最外层的包装流，即“后开的先关闭”。
            2.缓存区的大小默认是8192字节，也可以使用其它的构造方法自己指定大小。
           */

            //缓冲字符流
            /*BufferedReader/BufferedWriter增加了缓存机制，大大提高了读写文本文件的效率，
            同时，提供了更方便的按行读取的方法：readLine(); 换行newLine()。处理文本时，我们一般可以使用缓冲字符流。
           */
            //字符输入流 加入缓冲流
            //1.创建源
            File src08 = new File("a.txt");
            //2.选择流
            Reader reader08 = null;
            try {
                reader08 = new BufferedReader(new FileReader(src08));
                //3.操作（读取，分段读取）
                /*char[] flush=new char[1024];//缓冲容器 [1024]1k1k的读
                int len08=-1;//接收长度
                while((len08=reader08.read(flush))!=-1){
                    //字符数组--》字符串
                    String str08=new String(flush,0,len08);
                    System.out.println(str08);
                }*/
                String line08 = null;
                while ((line08 = ((BufferedReader) reader08).readLine()) != null) {
                    System.out.println(line08);
                }

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                //文件的末尾返回-1
                //4.释放
                try {
                    if (null != reader08) {
                        reader08.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            //字符输出流 加入缓冲流
            //1.创建源
            File desc09 = new File("buffer.txt");//inputstream中你要读取字节必须存在，而这里输出可以不存在系统自动创建
            //2.选择流
            Writer writer09 = null;
            try {
                writer09 = new BufferedWriter(new FileWriter(desc09));//默认是true表明以追加的形式写入文件，若为false则覆盖
                //3.操作（写出）
			/*写法一
			String msg="很简单";
			char[] datas=msg.toCharArray();//字符串--》字符数组
			writer.write(datas,0,datas.length);
			*/
			/*写法二
			String msg="很简单";
			writer.write(msg);
			writer.write(“的”);
			*/
               /* //写法三
                writer09.append("很简单").append("的字符缓冲输出流");
                writer09.flush();*/
                ((BufferedWriter) writer09).newLine();
                writer09.append("好好休息");//换行符就不需要再加\r\n了，直接用这个
                writer09.flush();

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                //4.释放
                try {
                    if (null != writer09) {
                        writer09.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
            /*
            BufferedReader新增的功能是逐行读取readLine()
            BufferedWriter新增的功能是逐行写入newLine()
            */

            //使用BufferedReader与BufferedWriter实现文本文件的复制
            // 注：处理文本文件时，实际开发中可以用如下写法，简单高效！！
            FileReader fr10 = null;
            FileWriter fw10 = null;
            BufferedReader br10 = null;
            BufferedWriter bw10 = null;
            String tempString = "";
            try {
                fr10 = new FileReader("buffer03.txt");
                fw10 = new FileWriter("buffer04.txt");
                //使用缓冲字符流进行包装
                br10 = new BufferedReader(fr10);
                bw10 = new BufferedWriter(fw10);
                //BufferedReader提供了更方便的readLine()方法，直接按行读取文本
                //br.readLine()方法的返回值是一个字符串对象，即文本中的一行内容
                while ((tempString = br10.readLine()) != null) {
                    //将读取的一行字符串写入文件中
                    bw10.write(tempString);
                    //下次写入之前先换行，否则会在上一行后边继续追加，而不是另起一行
                    bw10.newLine();
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (bw10 != null) {
                        bw10.close();
                    }
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                try {
                    if (br10 != null) {
                        br10.close();
                    }
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                try {
                    if (fw10 != null) {
                        fw10.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    if (fr10 != null) {
                        fr10.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            /*注意
            1.readLine()方法是BufferedReader特有的方法，可以对文本文件进行更加方便的读取操作。
            2.写入一行后要记得使用newLine()方法换行。
            */

            //字节数组流
            /*之前我们处理的源头都是文件，这个文件有一个特点存储在硬盘上，java虚拟机无权直接访问硬盘，必须借助操作系统。
            所以我们借完操作系统后要通知操作系统释放。
            现在我们把源头换为内存，不再是文件了，这个内存可以是你电脑上的内存、网络上的内存、远程服务器的内存。
            java虚拟机可以直接访问内存，这里是由我们的垃圾回收机制gc释放的。

            ByteArrayInputStream和ByteArrayOutputStream经常用在需要流和数组之间转化的情况!
            说白了，FileInputStream是把文件当做数据源。ByteArrayInputStream则是把内存中的”某个字节数组对象”当做数据源。

            字节数组：任何东西都可以转化为字节数组，包括数字、字符、对象都可以
            文件可以无限制的往进加内容，而内存呢速度快量小有限制。

            而且不需要关闭。

            程序 <----ByteArrayInputStream------ByteArray  读取
            程序 ----->ByteArrayOutputStream------->ByteArray  写入

            ByteArrayInputStream
            构造方法：ByteArrayInputStream(byte[] buf)  ByteArrayInputStream(byte[] buf,int offset,int length)
            之前的构造丢的是一个文件，现在是一个字节数组
            available() close() mark(int readahestlimit) marksupported()
            read() read(byte[] b,int off,int len) reset() skip(long n)

            ByteArrayOutputStream
            构造方法：ByteArrayOutputStream() ByteArrayOutputStream(int size)
            输出流的构造方法不需要目的地
            close() reset() size() toByteArray()创建一个新分配的字节数组 toString() toString(int hibyte) toString(String charsetName)
            write(byte[] b,int off,int len) write(int b) writeTo(OutputStream out)
            写完以后要去数组中拿一下

            */

            //字节数组输入流
            //1.创建源:字节数组 不要太大
            byte[] src001 = "talk is cheap show me the code".getBytes();
            //2.选择流
            InputStream is001 = null;
            try {
                is001 = new ByteArrayInputStream(src001);
                //3.操作（读取，分段读取）
                byte[] flush = new byte[1024];//缓冲容器 [1024]1k1k的读
                int len001 = -1;//接收长度
                while ((len001 = is001.read(flush)) != -1) {
                    //字符数组--》字符串
                    String str = new String(flush, 0, len001);
                    System.out.println(str);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }//释放资源可以不用处理

            ByteArrayInputStream bais001 = null;
            StringBuilder sb001 = new StringBuilder();
            //将字符串转变成字节数组
            byte[] b = "abcdefg".getBytes();
            int temp001 = 0;
            //用于保存读取的字节数
            int num = 0;
            try {
                //该构造方法的参数是一个字节数组，这个字节数组就是数据源
                bais001 = new ByteArrayInputStream(b);
                while ((temp = bais001.read()) != -1) {
                    sb001.append((char) temp);
                    num++;
                }
                System.out.println(sb001);
                System.out.println("读取的字节数：" + num);
            } finally {
                try {
                    if (bais001 != null) {
                        bais001.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }


            //字节数组输出流
            //1.创建源:源头内部来维护
            byte[] desc002 = null;
            //2.选择流（新增方法）（我们这里需要用它的toByteArray()新增方法，所以不要发生多态。）不关联源
            //新增方法就是父类没有子类有，如果要使用新增方法不能发生多态
            //父亲有的子类没有   都有同名的方法（多态）    子类有的父类没有
            ByteArrayOutputStream baos002 = null;
            try {
                baos002 = new ByteArrayOutputStream();
                //3.操作（写出）
                String msg = "show me the code";
                byte[] datas = msg.getBytes();//字符串--》字节数组
                baos002.write(datas, 0, datas.length);
                baos002.flush();
                //获取数据toByteArray()
                desc002 = baos002.toByteArray();
                System.out.println(desc002.length + "-->" + new String(desc002, 0, baos002.size()));//16-->show me the code
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            //对接流
            /*将一张图片读到字节数组中，然后将它还原。
            pic（文件） 写入 文件输入流 字节数组
            文件输出流 读取
            借用程序来中转*/
            //图片转成字节数组
            byte[] datas = fileToByteArray("copysrc.png");
            System.out.println(datas.length);
            //字节数组转成图片
            byteArrayToFile(datas, "pc.png");


            //数据流
            /*另外一种装饰模式
            数据流将“基本数据类型与字符串类型”作为数据源，从而允许程序以与机器无关的方式从底层输入输出流中操作Java基本数据类型与字符串类型。
            DataInputStream和DataOutputStream提供了可以存取与机器无关的所有Java基础类型数据(如：int、double、String等)的方法。
            DataInputStream和DataOutputStream是处理流，可以对其他节点流或处理流进行包装，增加一些更灵活、更高效的功能。

          程序 写入 DataOutputStream(OutputStream/FileOutputStream) file
              读取 DataInputStream(InputStream/FileInputStream)

              DataOutputStream
              read(byte[] b) read(byte[] b,int off,int len) readBoolean() readChar() readDouble()
              readFloat() readInt()


              DataInputStream


          */
            //数据流
            //1.写出后读取
            //2.读取的顺序与写出保持一致
            DataOutputStream dos = null;
            DataInputStream dis = null;
            FileOutputStream fos = null;
            FileInputStream fis = null;
            try {
                fos = new FileOutputStream("data.txt");
                fis = new FileInputStream("data.txt");
                //使用数据流对缓冲流进行包装，新增缓冲功能
                dos = new DataOutputStream(new BufferedOutputStream(fos));
                dis = new DataInputStream(new BufferedInputStream(fis));
                //将如下数据写入到文件中
                dos.writeChar('a');
                dos.writeInt(10);
                dos.writeDouble(Math.random());
                dos.writeBoolean(true);
                dos.writeUTF("北京");
                //手动刷新缓冲区：将流中数据写入到文件中
                dos.flush();
                //直接读取数据：读取的顺序要与写入的顺序一致，否则不能正确读取数据。
                System.out.println("char: " + dis.readChar());
                System.out.println("int: " + dis.readInt());
                System.out.println("double: " + dis.readDouble());
                System.out.println("boolean: " + dis.readBoolean());
                System.out.println("String: " + dis.readUTF());
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (dos != null) {
                        dos.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    if (dis != null) {
                        dis.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    if (fos != null) {
                        fos.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    if (fis != null) {
                        fis.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            //菜鸟雷区:使用数据流时，读取的顺序一定要与写入的顺序一致，否则不能正确读取数据。


            /*//对象流
            内存：字节数组
            不是所有的对象都可以序列化
            我们前边学到的数据流只能实现对基本数据类型和字符串类型的读写，并不能读取对象(字符串除外)，如果要对某个对象进行读写操作，
            我们需要学习一对新的处理流：ObjectInputStream/ObjectOutputStream。

            ObjectInputStream/ObjectOutputStream是以“对象”为数据源，但是必须将传输的对象进行序列化与反序列化操作。

            序列化与反序列化的具体内容，请见<10.3 Java对象的序列化和反序列化>。

            1.写出后读取
            2.读取的顺序与写出保持一致
            3.不是所有的对象都可以序列化

            注意
            1.对象流不仅可以读写对象，还可以读写基本数据类型。
            2.使用对象流读写对象时，该对象必须序列化与反序列化。
            3.系统提供的类(如Date等)已经实现了序列化接口，自定义类必须手动实现序列化接口。

            */


            /**使用对象输出流将数据写入文件,写出：序列化*/
            // 创建Object输出流，并包装缓冲流，增加缓冲功能
            OutputStream os = null;
            BufferedOutputStream bos = null;
            ObjectOutputStream oos = null;
            try {
                os = new FileOutputStream(new File("bjsxt.txt"));
                bos = new BufferedOutputStream(os);
                oos = new ObjectOutputStream(bos);
                // 使用Object输出流
                //对象流也可以对基本数据类型进行读写操作
                oos.writeInt(12);
                oos.writeDouble(3.14);
                oos.writeChar('A');
                oos.writeBoolean(true);
                oos.writeUTF("北京");
                //对象流能够对对象数据类型进行读写操作
                //Date是系统提供的类，已经实现了序列化接口
                //如果是自定义类，则需要自己实现序列化接口
                oos.writeObject(new Date());
                Employeee emp = new Employeee(12, "ad");
                oos.writeObject(emp);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                //关闭输出流
                if (oos != null) {
                    try {
                        oos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (bos != null) {
                    try {
                        bos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (os != null) {
                    try {
                        os.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            /**使用对象输入流将数据读入程序，读取，反序列化*/
            // 创建Object输入流
            InputStream iss = null;
            BufferedInputStream bis = null;
            ObjectInputStream ois = null;
            try {
                iss = new FileInputStream(new File("bjsxt.txt"));
                bis = new BufferedInputStream(iss);
                ois = new ObjectInputStream(bis);
                // 使用Object输入流按照写入顺序读取
                System.out.println(ois.readInt());
                System.out.println(ois.readDouble());
                System.out.println(ois.readChar());
                System.out.println(ois.readBoolean());
                System.out.println(ois.readUTF());
                System.out.println(ois.readObject().toString());
                System.out.println(ois.readObject().toString());
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                // 关闭Object输入流
                if (ois != null) {
                    try {
                        ois.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (bis != null) {
                    try {
                        bis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (is != null) {
                    try {
                        is.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            //打印流
            //程序------写入 PrintStream(OutputStream)----->file   也是装饰流
            /*PrintStream ps=System.out;
            ps.println("打印流");
            ps.println(true);
            try {
                ps=new PrintStream(new BufferedOutputStream(new FileOutputStream("print.txt")));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            ps.println("打印流");
            ps.println(true);
            ps.flush();//也提供了自动刷新
            //重定向输出端
            System.setOut(ps);
            System.out.println("change");
            //重定向回控制台
            System.setOut(new PrintStream(new BufferedOutputStream(new FileOutputStream(FileDescriptor.out))));
            System.out.println("i back");*/

            //转换流
            //除了字符缓冲流和字节缓冲流，另外的一个装饰模式的流。
           /*
           InputStreamReader/OutputStreamWriter用来实现将字节流转化成字符流。比如，如下场景：
            System.in是字节流对象，代表键盘的输入，如果我们想按行接收用户的输入时，就必须用到缓冲字符流BufferedReader特有的方法readLine()，
            但是经过观察会发现在创建BufferedReader的构造方法的参数必须是一个Reader对象，这时候我们的转换流InputStreamReader就派上用场了。

            而System.out也是字节流对象，代表输出到显示器，按行读取用户的输入后，并且要将读取的一行字符串直接显示到控制台，
            就需要用到字符流的write(String str)方法，所以我们要使用OutputStreamWriter将字节流转化为字符流。

            解码：字节转成字符
            字符集要统一，所以我们可以指定字符集。

            处理纯文本内容

            1.以字符流的形式操作字节流（纯文本的）
            2.指定字符集
           */
            //操作System.in和System.out
            //InputStreamReader isr=new InputStreamReader(System.in);
            //OutputStreamWriter osw=new OutputStreamWriter(System.out);
            //传过来的都是字符串，凡是用到字符串的地方我们可以都加一个缓冲流
            /*try(BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter writer=new BufferedWriter(new OutputStreamWriter(System.out)); ){
            //循环获取键盘的输入(exit退出),输出此内容
                String msg = "";
                while(!msg.equals("exit")){
                    msg=reader.readLine();//循环读取
                    writer.write(msg);//循环写出
                    writer.newLine();
                    writer.flush();//强制刷新一下，要不然根本进入不了下一次循环
                }
            }catch(IOException e){
                System.out.println("操作异常");
            }*/
            //使用InputStreamReader接收用户的输入，并输出到控制台
            // 创建字符输入和输出流:使用转换流将字节流转换成字符流
            /*BufferedReader br = null;
            BufferedWriter bw = null;
            try {
                br = new BufferedReader(new InputStreamReader(System.in));
                bw = new BufferedWriter(new OutputStreamWriter(System.out));
                // 使用字符输入和输出流
                String str = br.readLine();
                // 一直读取，直到用户输入了exit为止
                while (!"exit".equals(str)){
                    // 写到控制台
                    bw.write(str);
                    bw.newLine();// 写一行后换行
                    bw.flush();// 手动刷新
                    // 再读一行
                    str = br.readLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                // 关闭字符输入和输出流
                if (br != null) {
                    try {
                        br.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (bw != null) {
                    try {
                        bw.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }*/


            //操作网络流，下载百度的源代码
            //InputStreamReader isr=new InputStreamReader(System.in);
            //OutputStreamWriter osw=new OutputStreamWriter(System.out);
            //传过来的都是字符串，凡是用到字符串的地方我们可以都加一个缓冲流
            try (InputStream is0003 = new URL("http://www.baidu.com").openStream()) {
                int temp033;
                while ((temp033 = is0003.read()) != -1) {
                    System.out.print((char) temp033);
                }
            } catch (IOException e) {
                System.out.println("操作异常");
            }

            //以上是乱码的
            //下面是完整代码
            //操作网络流，下载百度的源代码
            try (BufferedReader reader = new BufferedReader(//缓冲一下
                    new InputStreamReader(//转化成字符流
                            new URL("http://www.baidu.com").openStream(), "UTF-8"))) {
                String msg;
                while ((msg = reader.readLine()) != null) {
                    System.out.print(msg);
                }
            } catch (IOException e) {
                System.out.println("操作异常");
            }
            System.out.println();

        /*//OutputStreamWriter可以指定输出内容的字符集
        //操作网络流，下载百度的源代码
        try(BufferedReader reader=new BufferedReader(//缓冲一下
            new InputStreamReader(//转化成字符流
                    new URL("http://www.baidu.com").openStream(),"UTF-8"));
            try(BufferedWriter writer=new BufferedWriter(//缓冲一下
                    new OutputStreamWriter(
                        new FileOutputStream("baidu.html"),"UTF-8")){//指定输出的字符集
            String msg;
            while(msg=reader.readLine()!=null){
                //System.out.print(msg);
                writer.write(msg);
                writer.newLine();
            }
            }catch(IOException e){
                System.out.println("操作异常");
            }*/


            //序列化
            /*1、序列化和反序列化是什么
            当两个进程远程通信时，彼此可以发送各种类型的数据。 无论是何种类型的数据，都会以二进制序列的形式在网络上传送。比如，
            我们可以通过http协议发送字符串信息;我们也可以在网络上直接发送Java对象。
            发送方需要把这个Java对象转换为字节序列，才能在网络上传送;
            接收方则需要把字节序列再恢复为Java对象才能正常读取。
            把Java对象转换为字节序列的过程称为对象的序列化。把字节序列恢复为Java对象的过程称为对象的反序列化。
            对象序列化的作用有如下两种：
            1. 持久化： 把对象的字节序列永久地保存到硬盘上，通常存放在一个文件中，
            比如：休眠的实现。以后服务器session管理，hibernate将对象持久化实现。
            2. 网络通信：在网络上传送对象的字节序列。比如：服务器之间的数据通信、对象传递。

            2、序列化涉及的类和接口
            ObjectOutputStream代表对象输出流，它的writeObject(Object obj)方法可对参数指定的obj对象进行序列化，把得到的字节序列写到一个目标输出流中。
            ObjectInputStream代表对象输入流，它的readObject()方法从一个源输入流中读取字节序列，再把它们反序列化为一个对象，并将其返回。
            只有实现了Serializable接口的类的对象才能被序列化。 Serializable接口是一个空接口，只起到标记作用。

            3、序列化/反序列化的步骤和实例
            将Person类的实例进行序列化和反序列化
            */
            FileOutputStream fosx = null;
            ObjectOutputStream oosx = null;
            ObjectInputStream oisx = null;
            FileInputStream fisx = null;
            try {
                // 通过ObjectOutputStream将Person对象的数据写入到文件中，即序列化。
                Personn person = new Personn(18, true, "高");
                // 序列化
                fosx = new FileOutputStream("x.txt");
                oosx = new ObjectOutputStream(fosx);
                oosx.writeObject(person);
                oosx.flush();

                // 反序列化
                fisx = new FileInputStream("x.txt");
                // 通过ObjectInputStream将文件中二进制数据反序列化成Person对象：
                oisx = new ObjectInputStream(fisx);
                Personn p = (Personn) oisx.readObject();
                System.out.println(p);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (oosx != null) {
                    try {
                        oosx.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (fosx != null) {
                    try {
                        fosx.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (oisx != null) {
                    try {
                        oisx.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (fisx != null) {
                    try {
                        fisx.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            /*注意
            1.static属性不参与序列化。
            2.对象中的某些属性如果不想被序列化，不能使用static，而是使用transient修饰。
            3.为了防止读和写的序列化ID不一致，一般指定一个固定的序列化ID。
            */


        }

        //图片读取到字节数组中，字节数组还原为文件
        /*1.图片读取到字节数组
        1）图片到程序FileInputStream
        2）程序到 字节数组ByteArrayOutPutStream
        */
        public static byte[] fileToByteArray(String filePath){
            //字节数组的目的地可以不用管
            //1.创建源和目的地
            File src=new File(filePath);//源头
            byte[] desc=null;//目的地
            //2.选择流
            InputStream is=null;
            ByteArrayOutputStream baos=null;
            try{
                is=new FileInputStream(src);
                baos=new ByteArrayOutputStream ();
                //3.操作（读取，分段读取）
                byte[] flush=new byte[1024*10];//缓冲容器 [1024]1k1k的读
                int len=-1;//接收长度
                while((len=is.read(flush))!=-1){
                    //程序到 字节数组ByteArrayOutPutStream
                    //数据我们已经有了，即字节数组我们已经有了
                    baos.write(flush,0,len);//写出到字节数组中

                }
                baos.flush();
                return baos.toByteArray();
            }catch(FileNotFoundException e){
                e.printStackTrace();
            }catch(IOException e){
                e.printStackTrace();
            }finally{
                //文件的末尾返回-1
                //4.释放
                try{
                    if(null!=is){
                        is.close();
                    }
                }catch(IOException e){
                    e.printStackTrace();
                }

            }
            return null;
        }


        /*2.字节数组写出到图片
        1）字节数组到程序ByteArrayInputStream
        2）程序到文件       FileOutputStream
        */
        public static void byteArrayToFile(byte[] src,String filePath){
            //1.创建源:字节数组 不要太大
            //byte[] src=src;多此一举
            File desc=new File(filePath);
            //2.选择流
            InputStream is=null;
            OutputStream os=null;
            try{
                is=new ByteArrayInputStream(src);
                os=new FileOutputStream(desc);
                //3.操作（读取，分段读取）
                byte[]flush=new byte[1024];//缓冲容器 [1024]1k1k的读
                int len=-1;//接收长度
                while((len=is.read(flush))!=-1){
                    os.write(flush,0,len);
                }
                os.flush();
            }catch(IOException e){
                e.printStackTrace();
            }finally{
                //4.释放
                try{
                    if(null!=os){
                    os.close();
                    }
                }catch(IOException e){
                    e.printStackTrace();
                }

            }
        }



}

class Employeee implements java.io.Serializable{
    private int age;
    private transient String name;//该数据不需要序列化

    public Employeee(int age, String name) {
        this.age = age;
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Employeee{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}


//Person类实现Serializable接口后，Person对象才能被序列化
class Personn implements Serializable {
    // 添加序列化ID，它决定着是否能够成功反序列化！
    private static final long serialVersionUID = 1L;
    int age;
    boolean isMan;
    String name;

    public Personn(int age, boolean isMan, String name) {
        super();
        this.age = age;
        this.isMan = isMan;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person [age=" + age + ", isMan=" + isMan + ", name=" + name + "]";
    }

}

