package com.zcr.exercisetest.Socket;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.io.*;
import java.net.*;

/**
 * @author zcr
 * @date 2019/5/7-10:04
 */
public class TestSocket {
    public static void  main(String args[]) throws UnknownHostException,MalformedURLException,IOException {
        //InetAddress i1 = new InetAddress();Error:(18, 26) java: InetAddress()在//java.net.InetAddress中不是公共的; 无法从外部程序包中对其进行访问

        try {
            //使用getLocalHost方法创建InetAddress对象  本机
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

        /*
        //UDP协议
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
*/


       /* //TCP协议
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

       //计算机网络
        /*
        计算机网络实现了不同计算机之间的通信，这必须依靠编写网络程序来实现。下面，我们将教大家如何编写网络程序。
        在学习编程之前，我们首先要了解关于网络通信的一些概念。
        什么是计算机网络?
        计算机网络是指将地理位置不同的具有独立功能的多台计算机及其外部设备，通过通信线路连接起来，
        在网络操作系统，网络管理软件及网络通信协议的管理和协调下，实现资源共享和信息传递的计算机系统。
        从其中我们可以提取到以下内容：
        1.计算机网络的作用：资源共享、信息传递、负载均衡与分布处理
        2.计算机网络的组成：
        a) 计算机硬件：计算机(大中小型服务器，台式机、笔记本等)、外部设备(路由器、交换机等)、通信线路(双绞线、光纤等)。
        b) 计算机软件：网络操作系统(Windows 2000 Server/Advance Server、Unix、Linux等)、网络管理软件(WorkWin、SugarNMS等)、网络通信协议(如TCP/IP协议栈等)。
        3. 计算机网络的多台计算机是具有独立功能的，而不是脱离网络就无法存在的。

        什么是网络通信协议?
        通过计算机网络可以实现不同计算机之间的连接与通信，但是计算机网络中实现通信必须有一些约定即通信协议，
        对速率、传输代码、代码结构、传输控制步骤、出错控制等制定标准。就像两个人想要顺利沟通就必须使用同一种语言一样，
        如果一个人只懂英语而另外一个人只懂中文，这样就会造成没有共同语言而无法沟通。
        国际标准化组织(ISO，即International Organization for Standardization)定义了网络通信协议的基本框架，
        被称为OSI(Open System Interconnect，即开放系统互联)模型。要制定通讯规则，内容会很多，比如要考虑A电脑如何找到B电脑，
        A电脑在发送信息给B电脑时是否需要B电脑进行反馈，A电脑传送给B电脑的数据格式又是怎样的?内容太多太杂，所以OSI模型将这些通讯标准进行层次划分，
        每一层次解决一个类别的问题，这样就使得标准的制定没那么复杂。OSI模型制定的七层标准模型，
        分别是：应用层，表示层，会话层，传输层，网络层，数据链路层，物理层。
        虽然国际标准化组织制定了这样一个网络通信协议的模型，但是实际上互联网通讯使用最多的网络通信协议是TCP/IP网络通信协议。
        TCP/IP 是一个协议族，也是按照层次划分，共四层：应用层，传输层，网络层，网络接口层(物理+数据链路层)。
        那么TCP/IP协议和OSI模型有什么区别呢?OSI网络通信协议模型，是一个参考模型，而TCP/IP协议是事实上的标准。
        TCP/IP协议参考了OSI 模型，但是并没有严格按照OSI规定的七层标准去划分，而只划分了四层，这样会更简单点，
        当划分太多层次时，你很难区分某个协议是属于哪个层次的。
        TCP/IP中有两个重要的协议，传输层的TCP协议和互连网络层的IP协议，因此就拿这两个协议做代表，来命名整个协议族了，
        再说TCP/IP协议时，是指整个协议族。

        网络协议的分层
        由于网络结点之间联系很复杂，在制定协议时，把复杂成份分解成一些简单的成份，再将它们复合起来。最常用的复合方式是层次方式，
        即同层间可以通信、上一层可以调用下一层，而与再下一层不发生关系。
        把用户应用程序作为最高层，把物理通信线路作为最低层，将其间的协议处理分为若干层，规定每层处理的任务，也规定每层的接口标准。

        数据封装与解封：
        由于用户传输的数据一般都比较大，有的可以达到MB字节，一次性发送出去十分困难，于是就需要把数据分成许多片段，再按照一定的次序发送出去。这个过程就需要对数据进行封装。
        数据封装(Data Encapsulation)是指将协议数据单元(PDU)封装在一组协议头和协议尾中的过程。在OSI七层参考模型中，每层主要负责与其它机器上的对等层进行通信。
        该过程是在协议数据单元(PDU)中实现的，其中每层的PDU一般由本层的协议头、协议尾和数据封装构成。
        1.数据发送处理过程
        (1)应用层将数据交给传输层，传输层添加上TCP的控制信息(称为TCP头部)，这个数据单元称为段(Segment)，加入控制信息的过程称为封装。然后，将段交给网络层。
        (2)网络层接收到段，再添加上IP头部，这个数据单元称为包(Packet)。然后，将包交给数据链路层。
        (3) 数据链路层接收到包，再添加上MAC头部和尾部，这个数据单元称为帧(Frame)。然后，将帧交给物理层。
        (4)物理层将接收到的数据转化为比特流（Bits）010110101001000010，然后在网线中传送。
        2.数据接收处理过程
        (1)物理层接收到比特流，经过处理后将数据交给数据链路层。
        (2)数据链路层将接收到的数据转化为数据帧，再除去MAC头部和尾部，这个除去控制信息的过程称为解封，然后将包交给网络层。
        (3)网络层接收到包，再除去IP头部，然后将段交给传输层。
        (4)传输层接收到段，再除去TCP头部，然后将数据交给应用层。
        从以上传输过程中，可以总结出以下规则：
        (1)发送方数据处理的方式是从高层到底层，逐层进行数据封装。
        (2)接收方数据处理的方式是从底层到高层，逐层进行数据解封装。
        接收方的每一层只把对该层有意义的数据拿走，或者说每一层只能处理发送方同等层的数据，然后把其余的部分传递给上一层，这就是对等层通信的概念。

        IP地址：
        定位：IP定位电脑 Port定位软件 URL定位资源
        用来标识网络中的一个通信实体的地址。通信实体可以是计算机、路由器等。
        比如互联网的每个服务器都要有自己的IP地址，而每个局域网的计算机要通信也要配置IP地址。路由器是连接两个或多个网络的网络设备。
        目前主流使用的IP地址是IPV4，但是随着网络规模的不断扩大，IPV4面临着枯竭的危险，所以推出了IPV6。
        IPV4：32位（4个字节）地址，并以8位为一个单位，分成四部分，以点分十进制表示，如192.168.0.1。
        因为8位二进制的计数范围是00000000—11111111，对应十进制的0-255，所以-4.278.4.1是错误的IPV4地址。
        IPV6：128位(16个字节)写成8个16位的无符号整数，每个整数用四个十六进制位表示，
        每个数之间用冒号(：)分开，如：3ffe:3201:1401:1280:c8ff:fe4d:db39:1984
        */
        /**
         IP:定位一个节点:计算机、路由、通讯设备等

         InetAddress: 多个静态方法
         1、getLocalHost:本机
         2、getByName:根据域名DNS | IP地址 -->IP

         两个成员方法
         1、getHostAddress: 返回地址
         2、getHostName:返回计算机名

         注意事项
         1.127.0.0.1 本机地址
         2.192.168.0.0–192.168.255.255为私有地址，属于非注册地址，专门为组织机构内部使用。
         */
        //使用getLocalHost方法创建InetAddress对象  本机
        InetAddress addr = InetAddress.getLocalHost();
        System.out.println(addr.getHostAddress());  //返回：192.168.1.110
        System.out.println(addr.getHostName());  //输出计算机名

        //根据域名得到InetAddress对象  DNS域名解析（将IP地址和有意义的字符串之间转换）
        addr = InetAddress.getByName("www.baidu.com");
        System.out.println(addr.getHostAddress());  //返回 shsxt服务器的ip地址123.56.138.186
        System.out.println(addr.getHostName());  //输出：www.shsxt.com

        //根据ip得到InetAddress对象
        addr = InetAddress.getByName("123.56.138.176");
        System.out.println(addr.getHostAddress());  //返回 shsxt的ip:123.56.138.176
        System.out.println(addr.getHostName());  //输出ip而不是域名。如果这个IP地址不存在或DNS服务器不允许进行IP地址和域名的映射，

        //端口
        /*IP地址用来标识一台计算机，但是一台计算机上可能提供多种网络应用程序，如何来区分这些不同的程序呢?这就要用到端口。0~65535
        端口是虚拟的概念，并不是说在主机上真的有若干个端口。通过端口，可以在一个主机上运行多个网络应用程序。
        端口的表示是一个16位的二进制整数，对应十进制的0-65535。
        Oracle、MySQL、Tomcat、QQ、msn、迅雷、电驴、360等网络程序都有自己的端口。
        公认端口0～1023比如80端口分为www，21端口分给FTP
        注册端口1024～49151分配给用户进程或应用程序
        动态/私有端口49152～65535
        总结
        1.IP地址好比每个人的地址(门牌号)，端口好比是房间号。必须同时指定IP地址和端口号才能够正确的发送数据。
        2. IP地址好比为电话号码，而端口号就好比为分机号。
        public class InetSocketAddress extends SocketAddress
        该类实现IP套接字地址（IP地址+端口号），它也可以是一对（主机名+端口号）
        方法：createUnresolved(String host,int port) equals(Object o) getAddress() getHostName() getHostString()
            getPort() hashCode() isUnresolved() toString()
         */
        /**
         * 端口
         * 1、区分软件
         * 2、2个字节 0-65535  UDP TCP
         * 3、同一个协议端口不能冲突
         * 4、定义端口越大越好
         *
         * InetSocketAddress
         * 1、构造器
         *   new InetSocketAddress(地址|域名,端口);
         * 2、方法
         *  getAddress​()  返回地址
         *  getPort() 返回端口
         *  getHostName()  返回
         */
        //包含端口
        InetSocketAddress socketAddress = new InetSocketAddress("127.0.0.1",8080);
        InetSocketAddress socketAddress2 = new InetSocketAddress("localhost",9000);
        System.out.println(socketAddress.getHostName());
        System.out.println(socketAddress.getAddress());
        System.out.println(socketAddress2.getAddress());
        System.out.println(socketAddress2.getPort());

        //URL
        /*在www上，每一信息资源都有统一且唯一的地址，该地址就叫URL(Uniform Resource Locator)，它是www的统一资源定位符。
        URL由4部分组成：协议 、存放资源的主机域名、资源文件名和端口号。
        如果未指定该端口号，则使用协议默认的端口。例如http 协议的默认端口为 80。 在浏览器中访问网页时，地址栏显示的地址就是URL。
        在java.net包中提供了URL类，该类封装了大量复杂的涉及从远程站点获取信息的细节。
        辨析：
        URI:Universal Resource Identifier 统一资源标志符，用来标识抽象或物理资源的一个紧凑字符串
        URL:Universal Resource Locator 统一资源定位符，一种定位资源的主要访问机制的字符串，一个标准的URL必须包括（protocal host port path parameter anchor）
        URN:Universal Resource Name 统一资源名称，通过特定命名空间中的唯一名称或ID来标识资源
        */
        /**
         * URL: 统一资源定位器,互联网三大基石之一(html http),区分资源
         * 1、协议
         * 2、域名、计算机
         * 3、端口: 默认80
         * 4、请求资源
         *  http://www.baidu.com:80/index.html?uname=shsxt&age=18#a
         */
        URL url = new URL("http://www.baidu.com:80/index.html?uname=shsxt&age=18#a");
        //获取四个值
        System.out.println("协议:"+url.getProtocol());
        System.out.println("域名|ip:"+url.getHost());
        System.out.println("端口:"+url.getPort());
        System.out.println("请求资源1:"+url.getFile());
        System.out.println("请求资源2:"+url.getPath());
        //参数
        System.out.println("参数:"+url.getQuery());
        //锚点
        System.out.println("锚点:"+url.getRef());

        //爬虫原理
        //获取URL
        URL url2 =new URL("https://www.jd.com");
        //URL url =new URL("https://www.dianping.com");
        //下载资源
        InputStream is = url.openStream();
        BufferedReader br =new BufferedReader(new InputStreamReader(is,"UTF-8"));
        String msg =null;
        while(null!=(msg=br.readLine())) {
            System.out.println(msg);
        }
        br.close();
        //分析
        //处理。。。。

        //网络爬虫的原理 +模拟浏览器
        //获取URL
        URL url3 =new URL("https://www.dianping.com");
        //下载资源
        HttpURLConnection conn =(HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.81 Safari/537.36");
        BufferedReader br3 =new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF-8"));
        String msg3 =null;
        while(null!=(msg3=br3.readLine())) {
            System.out.println(msg3);
        }
        br.close();
        //分析
        //处理。。。。

        //总结
        /*InetAddress 封装计算机的IP地址和DNS（没有端口信息），常用方法：getLocalHost()getByname()getAllbyName()getAddress()getHostName()
        InetSocketAddress包含IP和端口信息，常用于Socket通信。此类实现了IP套接字地址（IP地址+端口号），不依赖任何协议。getHostName()getAddress()
        URL 代表一个统一资源定位符，它是指向互联网资源的指针。资源可以是简单的文件或目录，也可以是对更为复杂的对象的引用，
        例如对数据库或搜索引擎的查询。getDefaultPort()getFile() getHost()getPath()getPort()getProtocol()geyQuery()getRef()
          */

        //Socket
        /*我们开发的网络应用程序位于应用层，TCP和UDP属于传输层协议，在应用层如何使用传输层的服务呢?在应用层和传输层之间，则是使用套接Socket来进行分离。
        相当于码头。
        套接字就像是传输层为应用层开的一个小口，应用程序通过这个小口向远程发送数据，或者接收远程发来的数据;而这个小口以内，
        也就是数据进入这个口之后，或者数据从这个口出来之前，是不知道也不需要知道的，也不会关心它如何传输，这属于网络其它层次工作。
            应用程序进程
            输出流 输入流
                套接字
            去往传输层 来自传输层
        Socket实际是传输层供给应用层的编程接口。Socket就是应用层与传输层之间的桥梁。使用Socket编程可以开发客户机和服务器应用程序，
        可以在本地网络上进行通信，也可通过Internet在全球范围内通信。
        Socket
        基于TCP协议的Socket编程 通信双方需要建立连接 连接建立时双方存在主次之分 114查号台
        基于UDP协议的Socket编程 通信双方不需要建立连接 通信双方完全平等 QQ聊天
        */

        //TCP UDP
        //TCP协议和UDP协议的联系和区别
        /*TCP协议和UDP协议是传输层的两种协议。Socket是传输层供给应用层的编程接口，所以Socket编程就分为TCP编程和UDP编程两类。
        在网络通讯中，TCP方式就类似于拨打电话，使用该种方式进行网络通讯时，需要建立专门的虚拟连接，然后进行可靠的数据传输，
        如果数据发送失败，则客户端会自动重发该数据。
        TCP(transfer contronl protocol)一种面向连接的、可靠的、基于字节流的传输层通信协议。
        特点：1.面向连接、2.点到点通信、3.高可靠性、4.占用系统资源多、5.效率低

        而UDP方式就类似于发送短信，使用这种方式进行网络通讯时，不需要建立专门的虚拟连接，传输也不是很可靠，如果发送失败则客户端无法获得。
        UDP(User DatagramProtocol)一种无连接的传输层协议、提供面向事务的简单不可靠信息传送服务
        特点：1.非面向连接的，3.传输不可靠，可能丢失、2.发送不管对方是否准备好，接收方收到也不确认、可以广播发送、4.非常简单的协议，5.开销小

        这两种传输方式都在实际的网络编程中使用，重要的数据一般使用TCP方式进行数据传输，而大量的非核心数据则可以通过UDP方式进行传递，
        在一些程序中甚至结合使用这两种方式进行数据传递。
        由于TCP需要建立专用的虚拟连接以及确认传输是否正确，所以使用TCP方式的速度稍微慢一些，而且传输时产生的数据量要比UDP稍微大一些。
        总结
        1.TCP是面向连接的，传输数据安全，稳定，效率相对较低。
        2.UDP是面向无连接的，传输数据不安全，效率较高。
        */
        //TCP协议
        /*TCP(Transfer Control Protocol)是面向连接的，所谓面向连接，就是当计算机双方通信时必需经过先建立连接，然后传送数据，最后拆除连接三个过程。
        TCP在建立连接时又分三步走：
        第一步，是请求端(客户端)发送一个包含SYN即同步(Synchronize)标志的TCP报文，SYN同步报文会指明客户端使用的端口以及TCP连接的初始序号。
        第二步，服务器在收到客户端的SYN报文后，将返回一个SYN+ACK的报文，表示客户端的请求被接受，同时TCP序号被加一，ACK即确认(Acknowledgement)。
        第三步，客户端也返回一个确认报文ACK给服务器端，同样TCP序列号被加一，到此一个TCP连接完成。
        然后才开始通信的第二步：数据处理。
        这就是所说的TCP的三次握手(Three-way Handshake)。

        基本步骤
        TCP编程
        需求：完成网络登录功能，用户输入用户名和密码，服务器给出登陆成功或失败的提示
        分析：使用基于TCP协议的Socket网络编程实现、TCP协议基于请求-响应模式、在网络通讯中，第一次主动发起通讯的程序被称作客户端程序、
        第一次通讯中等待连接的程序被称作服务器端程序、利用IO流实现数据的传输
                客户端      服务端
        输出流                     输出流
        输入流                     输入流
                    Socket
        public class ServerSocket extends Object implements Closeable
        这个类实现了服务器套接字，服务器套接字等待通过网络进入的请求，它根据该请求执行一些操作，然后可能将结果返回给请求者。
        构造方法：ServerSocket() ServerSocket(int port) ServerSocket(int port,int backlog) ServerSocket(int port,int backlog,InetAddress bind)
        方法：accept() bind(SocketAddress endpoint)bind(SocketAddress endpoint,int backlog)close() getChannel
        public class Socket extends Object implements Closeable
        构造方法：Socket() Socket(String host,int port)Socket(String host,int port,InetAddress localAddr,int localPort)

        详细步骤（通信原理）
        服务器创建ServerSocket，在指定端口监听并处理请求；客户端创建Socket，向服务端发送请求
        服务器端：
        创建ServerSocket(int port)对象；在Socket上监听客户端的连接请求；阻塞、等待连接的建立；
        接收并处理请求信息；将处理结果返回给客户端；关闭流和Socket对象
        客户端：
        创建Socket(String host,int port)对象；向服务器发送连接请求；向服务器发送服务请求
        接收服务结果；关闭流和Socket对象
        */
        //服务器读，客户端写
        //Server.java
        /**
         * 熟悉流程
         * 创建服务器
         * 1、指定端口 使用ServerSocket创建服务器
         * 2、阻塞式等待连接 accept
         * 3、操作: 输入输出流操作
         * 4、释放资源
         */
        //Client.java
        /**
         * 熟悉流程
         * 创建客户端
         * 1、建立连接: 使用Socket创建客户端 +服务的地址和端口
         * 2、操作: 输入输出流操作
         * 3、释放资源
         */


        /*网络登陆功能分解
        单向：客户端向服务器端发送字符串，服务器获取字符串并输出
        双向：服务器端给出客户端反馈，客户端得到反馈并输出
        文件：客户端向服务器端上传文件，服务器端获取文件并反馈结果
        多线程：服务器接收多个客户端的请求，并给出反馈，每个客户请求开启一个线程
         */
        //单向的。服务器读并分析，客户端先从控制台读再写
        //LoginServer.java
        /**
         * 模拟登录 单向
         * 创建服务器
         * 1、指定端口 使用ServerSocket创建服务器
         * 2、阻塞式等待连接 accept
         * 3、操作: 输入输出流操作
         * 4、释放资源
         */
        //LoginClient.java
        /**
         * 模拟登录 单向
         * 创建客户端
         * 1、建立连接: 使用Socket创建客户端 +服务的地址和端口
         * 2、操作: 输入输出流操作
         * 3、释放资源
         */

        //双向的。服务器读、分析、写，客户端先从控制台读再写，再读
        //LoginTwoWayServer.java
        /**
         * 模拟登录 双向
         * 创建服务器
         * 1、指定端口 使用ServerSocket创建服务器
         * 2、阻塞式等待连接 accept
         * 3、操作: 输入输出流操作
         * 4、释放资源
         */
        //LoginTwoWayClient.java
        /**
         * 模拟登录 双向
         * 创建客户端
         * 1、建立连接: 使用Socket创建客户端 +服务的地址和端口
         * 2、操作: 输入输出流操作
         * 3、释放资源
         */
        //1.只能输入一次
        //2.先输入才能获取

        //文件。服务器读然后再写入图片，客户端读出图片再写
        //FileServer.java
        /**
         * 存储文件
         * 创建服务器
         * 1、指定端口 使用ServerSocket创建服务器
         * 2、阻塞式等待连接 accept
         * 3、操作: 输入输出流操作
         * 4、释放资源
         */
        //FileClient.java
        /**
         * 上传文件
         * 创建客户端
         * 1、建立连接: 使用Socket创建客户端 +服务的地址和端口
         * 2、操作: 输入输出流操作
         * 3、释放资源
         */

        //多用户登陆。服务器这边启动多个线程连接客户端
        //LoginMultiServer.java
        /**
         * 模拟登录 多个客户端请求
         * 创建服务器
         * 1、指定端口 使用ServerSocket创建服务器
         * 2、阻塞式等待连接 accept
         * 3、操作: 输入输出流操作
         * 4、释放资源
         */
        //LoginMultiClient.java
        /**
         * 模拟登录  多个客户端请求
         * 创建客户端
         * 1、建立连接: 使用Socket创建客户端 +服务的地址和端口
         * 2、操作: 输入输出流操作
         * 3、释放资源
         */

        //手写聊天室_基础简易版
        /*服务器端：一个线程专门发送信息，一个线程专门接收信息
        客户端：一个线程专门发送信息，一个线程专门接收信息

        群聊：聊天室就是一个转发器
        私聊：报文，遍历找到它
        容器上面，遍历所有容器还是找到一个

        先请求后响应已经不行了，不能你说完一句我才能说。所以需要多线程。
        */
        //服务器先读后写，客户端先写后读
        //ChatClient.java
        /**
         * 在线聊天室: 客户端
         * 目标: 实现一个客户可以正常收发消息
         */
        //ChatServer.java
        /**
         * 在线聊天室: 服务器
         * 目标: 实现一个客户可以正常收发消息
         */
        //我发出去又给我返回来

        //要发多条while(isRunning)
        //MultiChatServer.java
        /**
         * 在线聊天室: 服务器
         * 目标: 实现一个客户可以正常收发多条消息
         */
        //MultiChatClient.java
        /**
         * 在线聊天室: 客户端
         * 目标: 实现一个客户可以正常收发多条消息
         */

        //只能一个人，现在想让多个客户正常的收发消息.服务器这么一直循环创建接收客户端的流，而且要有while(isRunning)
        //MultiClientChatServer.java
        /**
         * 在线聊天室: 服务器
         * 目标: 实现多个客户可以正常收发多条消息
         * 问题: 其他客户必须等待之前的客户退出，才能继续    排队
         */
        //MultiClientChatClient.java
        /**
         * 在线聊天室: 客户端
         * 目标: 实现多个客户可以正常收发多条消息
         * 问题: 其他客户必须等待之前的客户退出，才能继续 排队
         */

        //有问题，一个客户占上以后，等他退出后，下一个客户才能用
        //加上多线程.多线程：使得客户和客户各自是一个线程。再服务器这边加上new Thread为每个接收用户的流创建一个线程
        //TMultiChatServer.java
        /**
         * 在线聊天室: 服务器
         * 目标: 使用多线程实现多个客户可以正常收发多条消息
         * 问题:
         * 1、代码不好维护
         * 2、客户端读写没有分开 必须先写后读
         */
        //TMultiChatClient.java
        /**
         * 在线聊天室: 客户端
         * 目标: 使用多线程实现多个客户可以正常收发多条消息
         */

        //手写聊天室_OOP封装版
        /*解决
        1、代码不好维护
        2、客户端读写没有分开 必须先写后读
        封装起来
        读写分开
        */
        //TMultiChatServerOop.java
        /**
         * 在线聊天室: 服务器
         * 目标: 封装使用多线程实现多个客户可以正常收发多条消息
         * 问题:
         * 1、代码不好维护
         * 2、客户端读写没有分开 必须先写后读
         */
        //TMultiChatClientOop.java
        /**
         * 在线聊天室: 客户端
         * 目标: 封装使用多线程实现多个客户可以正常收发多条消息
         */
        //Send.java
        /**
         * 使用多线程封装:发送端
         * 1、发送消息
         * 2、从控制台获取消息
         * 3、释放资源
         * 4、重写run
         */
        //Receive.java
        /**
         * 使用多线程封装:接收端
         *  1、接收消息
         * 2、释放资源
         * 3、重写run
         */
        //SxtUtils.java
        /**
         * 工具类
         */
        /*自己和自己聊天，各自之间不受影响
                封装的魅力
        任何一个客户端与服务器之间自言自语
        */

        //手写聊天室_群聊过渡版
        //客户端发送到服务器一个消息，那么服务器就发送给所有的容器客户端.sendOthers(String msg,boolean isSys)
        //QServer.java
        /**
         * 在线聊天室: 服务器
         * 目标: 加入容器实现群聊
         */
        //QClient
        /**
         * 在线聊天室: 客户端
         * 目标: 加入容器实现群聊
         */
        //QSend.java
        /**
         * 使用多线程封装:发送端
         * 1、发送消息
         * 2、从控制台获取消息
         * 3、释放资源
         * 4、重写run
         */
        //QReceive.java
        /**
         * 使用多线程封装:接收端
         *  1、接收消息
         * 2、释放资源
         * 3、重写run
         */
        //SxtUtils.java
        /**
         * 工具类
         */

        //手写聊天室_私聊终极版sendOthers(String msg,boolean isSys) {
        //				boolean isPrivate = msg.startsWith("@");
        //SServer.java
        /**
         * 在线聊天室: 服务器
         * 目标: 私聊
         */
        //SClient.java
        /**
         * 在线聊天室: 客户端
         * 目标: 私聊
         */
        //SReceive.java
        /**
         * 使用多线程封装:接收端
         *  1、接收消息
         * 2、释放资源
         * 3、重写run
         */
        //SSend.java
        /**
         * 使用多线程封装:发送端
         * 1、发送消息
         * 2、从控制台获取消息
         * 3、释放资源
         * 4、重写run
         */
        //SxtUtil.java
        /**
         * 工具类
         *
         */
        //UDP协议
        /*基于TCP协议可以建立稳定连接的点对点的通信。这种通信方式实时、快速、安全性高，但是很占用系统的资源。
        在网络传输方式上，还有另一种基于UDP协议的通信方式，称为数据报通信方式。在这种方式中，
        每个数据发送单元被统一封装成数据报包的方式，发送方将数据报包发送到网络中，数据报包在网络中去寻找它的目的地。
        UDP基本步骤:
        UDP编程
        需求：完成在线咨询功能，学生和咨询师在线一对一交流
        分析：
        使用基于UDP协议的Socket网络编程实现；不需要利用IO流实现数据的传输；每个数据发送单元被统一封装成数据包
        的方式，发送方将数据包发送到网络中，数据包在网络中去寻找他的目的地
        DatagramDocket:用于发送或接收数据包的套接字 DatagramPacket:数据包
         */
        //是不需要用到流的，只需要在客户端进行发送，服务器端进行接收就可以
        //UdpClient.java
        /**
         * 基本流程: 发送端
         * 1、使用DatagramSocket  指定端口 创建发送端
         * 2、准备数据 一定转成字节数组
         * 3、 封装成DatagramPacket 包裹，需要指定目的地
         * 4、发送包裹send​(DatagramPacket p) *
         * 5、释放资源
         */
        //UdpServer.java
        /**
         * 基本流程: 接收端
         * Address already in use: Cannot bind  同一个协议下端口不允许冲突
         * 1、使用DatagramSocket  指定端口 创建接收端
         * 2、准备容器 封装成DatagramPacket 包裹
         * 3、阻塞式接收包裹receive​(DatagramPacket p)
         * 4、分析数据
         *    byte[]  getData​()
         *                getLength​()
         * 5、释放资源
         */

        //UDP上传文件。客户端到流中写入数据，再把数据封装后发送；服务器端先接收数据，再把数据从流中读取
        //UdpTypeClient.java
        /**
         * 基本类型: 发送端
         * 1、使用DatagramSocket  指定端口 创建发送端
         * 2、将基本类型  转成字节数组
         * 3、 封装成DatagramPacket 包裹，需要指定目的地
         * 4、发送包裹send​(DatagramPacket p) *
         * 5、释放资源
         */
        //UdpTypeServer.java
        /**
         * 基本类型: 接收端
         * Address already in use: Cannot bind  同一个协议下端口不允许冲突
         * 1、使用DatagramSocket  指定端口 创建接收端
         * 2、准备容器 封装成DatagramPacket 包裹
         * 3、阻塞式接收包裹receive​(DatagramPacket p)
         * 4、分析数据    将字节数组还原为对应的类型
         *    byte[]  getData​()
         *                getLength​()
         * 5、释放资源
         */

        //javabean 封装数据
        //Employee.java
        //UdpObjClient.java
        /**
         *  引用类型: 发送端
         * 1、使用DatagramSocket  指定端口 创建发送端
         * 2、将基本类型  转成字节数组
         * 3、 封装成DatagramPacket 包裹，需要指定目的地
         * 4、发送包裹send​(DatagramPacket p) *
         * 5、释放资源
         */
        //UdpObjServer.java
        /**
         * 引用类型: 接收端
         * Address already in use: Cannot bind  同一个协议下端口不允许冲突
         * 1、使用DatagramSocket  指定端口 创建接收端
         * 2、准备容器 封装成DatagramPacket 包裹
         * 3、阻塞式接收包裹receive​(DatagramPacket p)
         * 4、分析数据    将字节数组还原为对应的类型
         *    byte[]  getData​()
         *                getLength​()
         * 5、释放资源
         */

        //
        //IOUtils.java
        /**
         *1、 图片读取到字节数组
         *2、 字节数组写出到文件
         *
         */
        //UdpFileClient.java
        /**
         *  文件上传: 发送端
         * 1、使用DatagramSocket  指定端口 创建发送端
         * 2、将基本类型  转成字节数组
         * 3、 封装成DatagramPacket 包裹，需要指定目的地
         * 4、发送包裹send​(DatagramPacket p) *
         * 5、释放资源
         */
        //UdpFileServer.java
        /**
         * 文件存储: 接收端
         * Address already in use: Cannot bind  同一个协议下端口不允许冲突
         * 1、使用DatagramSocket  指定端口 创建接收端
         * 2、准备容器 封装成DatagramPacket 包裹
         * 3、阻塞式接收包裹receive​(DatagramPacket p)
         * 4、分析数据    将字节数组还原为对应的类型
         *    byte[]  getData​()
         *                getLength​()
         * 5、释放资源
         */

        //UDP案例在线咨询可以多次交流。发送端加上while(true);接收端也加上while(true)
        //UdpTalkClient.java
        /**
         * 多次交流: 发送端
         * 1、使用DatagramSocket  指定端口 创建发送端
         * 2、准备数据 一定转成字节数组
         * 3、 封装成DatagramPacket 包裹，需要指定目的地
         * 4、发送包裹send​(DatagramPacket p) *
         * 5、释放资源
         */
        //UdpTalkServer.java
        /**
         * 多次交流: 接收端
         * Address already in use: Cannot bind  同一个协议下端口不允许冲突
         * 1、使用DatagramSocket  指定端口 创建接收端
         * 2、准备容器 封装成DatagramPacket 包裹
         * 3、阻塞式接收包裹receive​(DatagramPacket p)
         * 4、分析数据
         *    byte[]  getData​()
         *                getLength​()
         * 5、释放资源
         */

        //使用面向对象封装。加入多线程。客户端有发送的多线程和接收的多线程；服务器端有发送的多线程和接收的多线程
        //TalkSend.java
        /**
         * 发送端: 使用面向对象封装
         */
        //TalkReceive.java
        /**
         * 接收端: 使用面向对象封装
         */
        //TalkStudent.java
        /**
         * 加入多线程，实现双向交流 模拟在线咨询
         */
        //TalkTeacher.java
        /**
         * 加入多线程，实现双向交流 模拟在线咨询
         */


        //Java网络编程
        /*Java为了可移植性，不允许直接调用操作系统，而是由java.net包来提供网络功能。
        Java虚拟机负责提供与操作系统的实际连接。下面我们来介绍几个java.net包中的常用的类。
        */
        //InetAddress
        /*作用：封装计算机的IP地址和DNS(没有端口信息)。
        注：DNS是Domain Name System，域名系统。
        特点：这个类没有构造方法。如果要得到对象，只能通过静态方法：getLocalHost()、getByName()、 getAllByName()、
        getAddress()、getHostName()。
        */
        //使用getLocalHost方法创建InetAddress对象
        InetAddress addre = InetAddress.getLocalHost();
        //返回IP地址：192.168.1.110
        System.out.println(addre.getHostAddress());
        //输出计算机名：gaoqi
        System.out.println(addre.getHostName());

        //根据域名得到InetAddress对象
        InetAddress addre2 = InetAddress.getByName("www.sxt.cn");
        // 返回 sxt服务器的IP:59.110.14.7
        System.out.println(addre2.getHostAddress());
        // 输出：www.sxt.cn
        System.out.println(addre2.getHostName());

        //根据IP得到InetAddress对象
        InetAddress addre3 = InetAddress.getByName("59.110.14.7");
        // 返回sxt服务器的IP：59.110.14.7
        System.out.println(addre3.getHostAddress());
        /*
         * 输出ip而不是域名。如果这个IP地址不存在或DNS服务器不允许进行IP地址
         * 和域名的映射，getHostName方法就直接返回这个IP地址。
         */
        System.out.println(addre3.getHostName());

        //InetSocketAddress
        //作用：包含IP和端口信息，常用于Socket通信。此类实现 IP 套接字地址(IP 地址 + 端口号)，不依赖任何协议。
        //InetSocketAddress的使用
        InetSocketAddress socketAddress4 = new InetSocketAddress("127.0.0.1", 8080);
        InetSocketAddress socketAddress5 = new InetSocketAddress("localhost", 9000);
        System.out.println(socketAddress4.getHostName());
        System.out.println(socketAddress5.getAddress());

        //URL类
        /*IP地址唯一标识了Internet上的计算机，而URL则标识了这些计算机上的资源。
        类 URL 代表一个统一资源定位符，它是指向互联网“资源”的指针。资源可以是简单的文件或目录，也可以是对更为复杂的对象的引用，
        例如对数据库或搜索引擎的查询。
        为了方便程序员编程，JDK中提供了URL类，该类的全名是java.net.URL，有了这样一个类，就可以使用它的各种方法来对URL对象进行分割、合并等处理。
        */
        //URL类的使用
        URL u = new URL("http://www.google.cn:80/webhp#aa?canhu=33");
        System.out.println("获取与此url关联的协议的默认端口：" + u.getDefaultPort());
        System.out.println("getFile:" + u.getFile()); // 端口号后面的内容
        System.out.println("主机名：" + u.getHost()); // www.google.cn
        System.out.println("路径：" + u.getPath()); // 端口号后，参数前的内容
        // 如果www.google.cn:80则返回80.否则返回-1
        System.out.println("端口：" + u.getPort());
        System.out.println("协议：" + u.getProtocol());
        System.out.println("参数部分：" + u.getQuery());
        System.out.println("锚点：" + u.getRef());
        URL u1 = new URL("http://www.abc.com/aa/");
        URL u2 = new URL(u, "2.html"); // 相对路径构建url对象
        System.out.println(u2.toString()); // http://www.abc.com/aa/2.html

        //最简单的网络爬虫
        basicSpider();
        //基于TCP协议的Socket编程和通信
        /*在网络通讯中，第一次主动发起通讯的程序被称作客户端(Client)程序，简称客户端，而在第一次通讯中等待连接的程序被称作服务器端(Server)程序，
        简称服务器。一旦通讯建立，则客户端和服务器端完全一样，没有本质的区别。
        “请求-响应”模式：
        1.Socket类：发送TCP消息。
        2.ServerSocket类：创建服务器。

        套接字是一种进程间的数据交换机制。这些进程既可以在同一机器上，也可以在通过网络连接的不同机器上。
        换句话说，套接字起到通信端点的作用。单个套接字是一个端点，而一对套接字则构成一个双向通信信道，
        使非关联进程可以在本地或通过网络进行数据交换。一旦建立套接字连接，数据即可在相同或不同的系统中双向或单向发送，
        直到其中一个端点关闭连接。套接字与主机地址和端口地址相关联。主机地址就是客户端或服务器程序所在的主机的IP地址。
        端口地址是指客户端或服务器程序使用的主机的通信端口。

        在客户端和服务器中，分别创建独立的Socket，并通过Socket的属性，将两个Socket进行连接，
        这样，客户端和服务器通过套接字所建立的连接使用输入输出流进行通信。

        TCP/IP套接字是最可靠的双向流协议，使用TCP/IP可以发送任意数量的数据。

        实际上，套接字只是计算机上已编号的端口。如果发送方和接收方计算机确定好端口，他们就可以通信了。

        如图12-6所示为客户端与服务器端的通信关系图：
        客户端     服务器端
        输出流         输出流
        输入流         输入流

        TCP/IP通信连接的简单过程：
        位于A计算机上的TCP/IP软件向B计算机发送包含端口号的消息，B计算机的TCP/IP软件接收该消息，并进行检查，
        查看是否有它知道的程序正在该端口上接收消息。如果有，他就将该消息交给这个程序。
        要使程序有效地运行，就必须有一个客户端和一个服务器。

        通过Socket的编程顺序：
        1.创建服务器ServerSocket，在创建时，定义ServerSocket的监听端口(在这个端口接收客户端发来的消息)。
        2.ServerSocket调用accept()方法，使之处于阻塞状态。
        3.创建客户端Socket，并设置服务器的IP及端口。
        4.客户端发出连接请求，建立连接。
        5.分别取得服务器和客户端Socket的InputStream和OutputStream。
        6.利用Socket和ServerSocket进行数据传输。
        7.关闭流及Socket。
        */

        //TCP：单向通信Socket之服务器端BasicSocketServer.java
        //TCP：单向通信Socket之客户端BasicSocketClient.java

        //TCP：双向通信Socket之服务器端SxServer.java
        //TCP：双向通信Socket之客户端SxClient.java
        /*菜鸟雷区
        运行时，要先启动服务器端，再启动客户端，才能得到正常的运行效果。
        但是，上面这个程序，必须按照安排好的顺序，服务器和客户端一问一答!不够灵活!!可以使用多线程实现更加灵活的双向通讯!!
                服务器端：一个线程专门发送消息，一个线程专门接收消息。
        客户端：一个线程专门发送消息，一个线程专门接收消息。
        */

        //TCP：聊天室之服务器端ChatServer2.java
        //TCP：聊天室之客户端ChatClient2.java


        //UDP通讯的实现
        /*▪ DatagramSocket：用于发送或接收数据报包
        当服务器要向客户端发送数据时，需要在服务器端产生一个DatagramSocket对象，在客户端产生一个DatagramSocket对象。
        服务器端的DatagramSocket将DatagramPacket发送到网络上，然后被客户端的DatagramSocket接收。
        DatagramSocket有两种常用的构造函数。一种是无需任何参数的，常用于客户端;另一种需要指定端口，常用于服务器端。如下所示：
        DatagramSocket() ：构造数据报套接字并将其绑定到本地主机上任何可用的端口。
        DatagramSocket(int port) ：创建数据报套接字并将其绑定到本地主机上的指定端口。
        常用方法：
        Ø send(DatagramPacket p) ：从此套接字发送数据报包。
        Ø receive(DatagramPacket p) ：从此套接字接收数据报包。
        Ø close() ：关闭此数据报套接字。

        ▪ DatagramPacket：数据容器(封包)的作用
        此类表示数据报包。 数据报包用来实现封包的功能。
        常用方法：
        Ø DatagramPacket(byte[] buf, int length) ：构造数据报包，用来接收长度为 length 的数据包。
        Ø DatagramPacket(byte[] buf, int length, InetAddress address, int port) ：构造数据报包，
        用来将长度为 length 的包发送到指定主机上的指定端口号。
        Ø getAddress() ：获取发送或接收方计算机的IP地址，此数据报将要发往该机器或者是从该机器接收到的。
        Ø getData() ：获取发送或接收的数据。
        Ø setData(byte[] buf) ：设置发送的数据。

        UDP通信编程基本步骤：
        1.创建客户端的DatagramSocket，创建时，定义客户端的监听端口。
        2.创建服务器端的DatagramSocket，创建时，定义服务器端的监听端口。
        3.在服务器端定义DatagramPacket对象，封装待发送的数据包。
        4.客户端将数据报包发送出去。
        5.服务器端接收数据报包。*/
        //UDP：单向通信之客户端
        //UDP：单向通信之服务器端

        //UDP：基本数据类型的传递之客户端BasicUdpClient.java
        //UDP：基本数据类型的传递之服务器端BasicUdpServer.java

        //通过字节数组流ByteArrayInputStream、ByteArrayOutputStream与数据流DataInputStream、DataOutputStream联合使用可以传递基本数据类型。
        //UDP：基本数据类型的传递之客户端JUdpClient
        //UDP：基本数据类型的传递之服务器端JUdpServer

        //通过字节数组流ByteArrayInputStream、ByteArrayOutputStream与数据流ObjectInputStream、ObjectOutputStream联合使用可以传递对象。
        //UDP：对象的传递之Person类OPerson.java
        //UDP：对象的传递之客户端OUdpClient.java
        //UDP：对象的传递之服务器端OUdpServer.java


        /*总结

        1.端口是虚拟的概念，并不是说在主机上真的有若干个端口。
        2.在www上，每一信息资源都有统一且唯一的地址，该地址就叫URL(Uniform Resource Locator)，它是www的统一资源定位符。
        3.TCP与UDP的区别
        1）TCP是面向连接的，传输数据安全，稳定，效率相对较低。
        2）UDP是面向无连接的，传输数据不安全，效率较高。
        4.Socket通信是一种基于TCP协议，建立稳定连接的点对点的通信。
        5.网络编程是由java.net包来提供网络功能。
        1）InetAddress：封装计算机的IP地址和DNS(没有端口信息!)。
        2）InetSocketAddress：包含IP和端口，常用于Socket通信。
        3）URL：以使用它的各种方法来对URL对象进行分割、合并等处理。
        6.基于TCP协议的Socket编程和通信
        1）“请求-响应”模式：
            –Socket类：发送TCP消息。
            –ServerSocket类：创建服务器。
        7.UDP通讯的实现
        1）DatagramSocket：用于发送或接收数据报包。
        2）常用方法：send()、receive()、 close()。
        8.DatagramPacket：数据容器(封包)的作用
        1）常用方法：构造方法、getAddrress(获取发送或接收方计算机的IP地址)、getData(获取发送或接收的数据)、setData(设置发送的数据)。

        如何定位（IP\端口、URL）
        TCP\UDP
                Socket编程
        */











    }
    //网络爬虫
    static void basicSpider() {
        URL url = null;
        InputStream is = null;
        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();
        String temp = "";
        try {
            url = new URL("http://www.baidu.com");
            is = url.openStream();
            br = new BufferedReader(new InputStreamReader(is));
            /*
             * 这样就可以将网络内容下载到本地机器。
             * 然后进行数据分析，建立索引。这也是搜索引擎的第一步。
             */
            while ((temp = br.readLine()) != null) {
                sb.append(temp);
            }
            System.out.println(sb);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }





}

