package com.zcr.exercisetest.thread;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.*;

/**
 * @author zcr
 * @date 2019/5/7-9:55
 */
public class TestThread {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        /*Thread t1 = new Thread();
        t1.start();
        t1.run();
        t1.setName("线程1");
        t1.getName();
        Thread.currentThread();
        Thread.sleep(200);

        Runnable r1 = new Runnable() {
            @Override
            public void run() {
                System.out.println("线程2");
            }
        };
        Thread t2 = new Thread(r1);
        t2.start();

        ExecutorService s1 = Executors.newFixedThreadPool(2);
        s1.submit(r1);*/

        //多线程
        /*多线程是Java语言的重要特性，大量应用于网络编程、服务器端程序的开发，最常见的UI界面底层原理、操作系统底层原理都大量使用了多线程。
        我们可以流畅的点击软件或者游戏中的各种按钮，其实，底层就是多线程的应用。UI界面的主线程绘制界面，如果有一个耗时的操作发生则启动新的线程，
        完全不影响主线程的工作。当这个线程工作完毕后，再更新到主界面上。
        我们可以上百人、上千人、上万人同时访问某个网站，其实，也是基于网站服务器的多线程原理。如果没有多线程，服务器处理速度会极大降低。

        “程序(Program)”是一个静态的概念，一般对应于操作系统中的一个可执行文件，比如：我们要启动酷狗听音乐，则对应酷狗的可执行程序。
        当我们双击酷狗，则加载程序到内存中，开始执行该程序，于是产生了“进程”。

        执行中的程序叫做进程(Process)，是一个动态的概念。现代的操作系统都可以同时启动多个进程。
        比如：我们在用酷狗听音乐，也可以使用eclipse写代码，也可以同时用浏览器查看网页。进程具有如下特点：
        1.进程是程序的一次动态执行过程， 占用特定的地址空间。
        2.每个进程由3部分组成：cpu、data、code。每个进程都是独立的，保有自己的cpu时间，代码和数据，即便用同一份程序产生好几个进程，
        它们之间还是拥有自己的这3样东西，这样的缺点是：浪费内存，cpu的负担较重。
        3.多任务(Multitasking)操作系统将CPU时间动态地划分给每个进程，操作系统同时执行多个进程，每个进程独立运行。
        以进程的观点来看，它会以为自己独占CPU的使用权。
        4.进程的查看
        Windows系统: Ctrl+Alt+Del，启动任务管理器即可查看所有进程。
        Unix系统: ps or top。

        一个进程可以产生多个线程。同多个进程可以共享操作系统的某些资源一样，同一进程的多个线程也可以共享此进程的某些资源(比如：代码、数据)，
        所以线程又被称为轻量级进程(lightweight process)。
        1.一个进程内部的一个执行单元，它是程序中的一个单一的顺序控制流程。
        2.一个进程可拥有多个并行的(concurrent)线程。
        3.一个进程中的多个线程共享相同的内存单元/内存地址空间，可以访问相同的变量和对象，而且它们从同一堆中分配对象并进行通信、数据交换和同步操作。
        4.由于线程间的通信是在同一地址空间上进行的，所以不需要额外的通信机制，这就使得通信更简便而且信息传递的速度也更快。
        5.线程的启动、中断、消亡，消耗的资源非常少。

        线程和进程的区别
        1.每个进程都有独立的代码和数据空间(进程上下文)，进程间的切换会有较大的开销。
        2.线程可以看成是轻量级的进程，属于同一进程的线程共享代码和数据空间，每个线程有独立的运行栈和程序计数器(PC)，线程切换的开销小。
        3.线程和进程最根本的区别在于：进程是资源分配的单位，线程是调度和执行的单位。
        4.多进程: 在操作系统中能同时运行多个任务(程序)。
        5.多线程: 在同一应用程序中有多个顺序流同时执行。
        6.线程是进程的一部分，所以线程有的时候被称为轻量级进程。
        7.一个没有线程的进程是可以被看作单线程的，如果一个进程内拥有多个线程，进程的执行过程不是一条线(线程)的，而是多条线(线程)共同完成的。
        8.系统在运行的时候会为每个进程分配不同的内存区域，但是不会为线程分配内存(线程所使用的资源是它所属的进程的资源)，
        线程组只能共享资源。那就是说，除了CPU之外(线程在运行的时候要占用CPU资源)，计算机内部的软硬件资源的分配与线程无关，线程只能共享它所属进程的资源。

        进程与程序的区别
        程序是一组指令的集合，它是静态的实体，没有执行的含义。而进程是一个动态的实体，有自己的生命周期。
        一般说来，一个进程肯定与一个程序相对应，并且只有一个，但是一个程序可以有多个进程，或者一个进程都没有。
        除此之外，进程还有并发性和交往性。简单地说，进程是程序的一部分，程序运行的时候会产生进程。

        Java中如何实现多线程
        在Java中使用多线程非常简单，我们先学习如何创建和使用线程，然后再结合案例深入剖析线程的特性。
        创建线程的三种方式：继承Thread类、实现Runnable接口、实现Callable接口
        1.public class Thread extends Object implements Runnable
        继承Thread类，重写run()方法。
        class PrimeThread extends Thread{
            long minPrime;
            PrimeThread(long minPrime){
                this.minPrime = minPrime;
            }
            public void run(){

            }
        }
        创建一个线程并启动它
        PrimeThread p = new PrimeThread(143);//仅仅是创建了线程对象
        p.start();//开启线程，当方法执行时，调用run()代码将被执行。run()方法是线程体的入口点，线程执行的代码
        注意：执行线程必须调用start(),加入到调度器中；不一定立即执行，系统安排调度分配执行；
        直接调用run()方法不是开启多线程，是普通的方法调用
        2.实现Runnale接口，实现run()方法。
        class PrimeRun implements Runnable{
            long minPrime;
            PrimeRun(long minPrime){
                this.minPrime = minPrime;
            }
            public void run(){

            }
        }
        在创建Thread时作为参数传递
        只有Thread类才具有和CPU直接打交道的能力，才能启动start()
        PrimeThread p = new PrimeThread(143);
        new Thread(p).start();

        Thread类的构造方法
        Thread() Thread(Runnable target) Thread(Runnable target,String name) Thread(String name)
        Thread(ThreadGroup group,Runnable target) Thread(ThreadGroup group,Runnable target,String name)
        方法：start()导致此线程开始执行，Java虚拟机调用此线程的run()方法
        run()如果这个线程是使用单独的Runnable运行对象构造的，那么这个Runnable对象的run()方法被调用；否则此方法不执行任何操作并返回
        */

        //创建线程的方法一：
        //创建子类对象
        StartThread st =new StartThread();
        //调用子类对象的start()方法来启动
        st.start(); //开启一个线程，交给CPU去调，不保证立即运行 cpu调用。所以这里可能是先去打代码再去听歌再打代码，交替执行
        //st.run(); //普通方法调用，这时候呢，就必须听完歌才能敲完代码。run()方法自己会去调用start()方法
        for(int i=0;i<20;i++) {
            System.out.println("一边coding");
        }

        //图片下载的例子：
        TDownloader td1 =new TDownloader("http://pic15.nipic.com/20110628/1369025_192645024000_2.jpg","phone.jpg");
        TDownloader td2 =new TDownloader("http://p1.pstatp.com/large/403c00037462ae2eee13","spl.jpg");
        TDownloader td3 =new TDownloader("http://5b0988e595225.cdn.sohucs.com/images/20170830/d8b57e0dce0d4fa29bd5ef014be663d5.jpeg","success.jpg");
        //启动三个线程
        td1.start();
        td2.start();
        td3.start();
        System.out.println("图片下载完毕！");

        //创建线程的方法二：
        /*//创建实现类对象
		StartRun sr =new StartRun();
		//创建代理类对象
		Thread t =new Thread(sr);
		//启动
		t.start(); //不保证立即运行 cpu调用
        */
        new Thread(new StartRun()).start();//合三为一简化操作。匿名使用，一个对象只用一次
        //st.run(); //普通方法调用
        for(int i=0;i<20;i++) {
            System.out.println("一边语音通话");
        }

        //图片下载的例子：
        IDownloader td11 =new IDownloader("http://pic15.nipic.com/20110628/1369025_192645024000_2.jpg","phone2.jpg");
        IDownloader td22 =new IDownloader("http://p1.pstatp.com/large/403c00037462ae2eee13","spl2.jpg");
        IDownloader td33 =new IDownloader("http://5b0988e595225.cdn.sohucs.com/images/20170830/d8b57e0dce0d4fa29bd5ef014be663d5.jpeg","success2.jpg");
        //启动三个线程
        new Thread(td11).start();
        new Thread(td22).start();
        new Thread(td33).start();

        //对比
        /*方法一：子类继承Thread具备了多线程能力；启动线程：子类对象.start()；不建议使用：避免OOP单继承局限
        方法二：实现接口Runnable具有多线程能力；启动线程：传入目标对象+Thread对象.start()；推荐使用：多实现，灵活方便，方便同一份对象的代理
        Runnale
        对同一个资源可以有多个代理(共享资源,并发(要保证线程安全，后期会讲))
        多线程抢票 龟兔赛跑
        */
        //多线程抢票
        //一份资源
        Web12306 web =new Web12306();
        System.out.println(Thread.currentThread().getName());//这里是main
        //多个代理，如何区分多个代理呢，加名字
        new Thread(web,"王磊").start();
        new Thread(web,"温雅").start();
        new Thread(web,"张撒").start();

        //模拟龟兔赛跑例子
        Racer racer = new Racer();//方便共享资源，一条赛道大家都去竞争
        new Thread(racer,"tortoise").start();
        new Thread(racer,"rabbit").start();

        //了解创建线程的方式三: 了解Callale.实现Callable接口，重写call方法
       /* 并发领域的JUC编程
        run()方法不能抛出异常和返回返回返回值
                这里能

        需要借助服务、线程池*/
        CDownloader cd1 =new CDownloader("http://pic15.nipic.com/20110628/1369025_192645024000_2.jpg","phone3.jpg");
        CDownloader cd2 =new CDownloader("http://p1.pstatp.com/large/403c00037462ae2eee13","spl3.jpg");
        CDownloader cd3 =new CDownloader("http://5b0988e595225.cdn.sohucs.com/images/20170830/d8b57e0dce0d4fa29bd5ef014be663d5.jpeg","success3.jpg");
        //创建执行服务:
        ExecutorService  ser=Executors.newFixedThreadPool(3);
        //提交执行:
        Future<Boolean> result1 =ser.submit(cd1) ;
        Future<Boolean> result2 =ser.submit(cd2) ;
        Future<Boolean> result3 =ser.submit(cd3) ;
        //获取结果:
        boolean r1 =result1.get();
        boolean r2 =result1.get();
        boolean r3 =result1.get();
        System.out.println(r3);
        //关闭服务:
        ser.shutdownNow();

        //模拟龟兔赛跑
        CRacer racer2 = new CRacer();
        //创建执行服务:
        ExecutorService  ser2=Executors.newFixedThreadPool(2);
        //提交执行:
        Future<Integer> result12 =ser2.submit(racer2) ;
        Future<Integer> result22 =ser2.submit(racer2) ;
        //获取结果:
        Integer r12 =result12.get();
        Integer r22 =result22.get();
        System.out.println(r12+"-->"+r22);
        //关闭服务:
        ser.shutdownNow();

        //通过继承Thread类实现多线程
        /*继承Thread类实现多线程的步骤：
        1.在Java中负责实现线程功能的类是java.lang.Thread 类。
        2.可以通过创建 Thread的实例来创建新的线程。
        3.每个线程都是通过某个特定的Thread对象所对应的方法run( )来完成其操作的，方法run( )称为线程体。
        4.通过调用Thread类的start()方法来启动一个线程。
        此种方式的缺点：如果我们的类已经继承了一个类(如小程序必须继承自 Applet 类)，则无法再继承 Thread 类。
        */
        TestThread01 thread1 = new TestThread01();//创建线程对象
        thread1.start();//启动线程
        TestThread01 thread2 = new TestThread01();
        thread2.start();

        //通过Runnable接口实现多线程
       /* 在开发中，我们应用更多的是通过Runnable接口实现多线程。这种方式克服了11.2.1节中实现线程类的缺点，
        即在实现Runnable接口的同时还可以继承某个类。所以实现Runnable接口的方式要通用一些。
        */
        //创建线程对象，把实现了Runnable接口的对象作为参数传入；
        Thread thread3 = new Thread(new TestThread02());
        thread3.start();//启动线程；
        Thread thread4 = new Thread(new TestThread02());
        thread4.start();

        //静态代理设计模式
        //我们使用实现runnable接口重写run方法启动线程时，必须借用Thread对象，这个对象就是代理对象。代理模式以后用来记日志用的
        //你：真是角色 婚庆公司：代理角色，帮你搞婚庆 结婚礼仪：实现相同的接口
        /**
         * 静态代理
         * 公共接口:
         * 1、真实角色
         * 2、代理角色
         * 真实角色和代理对象 都先实现了结婚接口
         *
         */
        new WeddingCompany(new You()).happyMarry();
        //new Thread(线程对象).start();

        //推导lamda简化线程
        /*lamda 希腊字母表中排序第十一位的字母，英语名称为lamda。避免匿名内部类定义过多。其实质属于函数式编程思想。
        (params)->expression (params)->statements (params)->{statements}
        如：new Thread({}->System.out.println("多线程学习")).start();
        用的线程比较少，只关注于线程体。Lambda表达式 简化线程(用一次)的使用。
        */
        //lambda表达式测试一
        //1.new Thread(new Test()).start();	静态内部类的使用

        //2局部内部类，把类丢到方法内部来
        class Test2 implements Runnable{
            public void run() {
                for(int i=0;i<5;i++) {
                    System.out.println("lamdada");
                }
            }
        }
        new Thread(new Test2()).start();//局部内部类的使用

        //3匿名内部类 必须借助接口或者父类
        new Thread(new Runnable() {
            public void run() {
                for(int i=0;i<5;i++) {
                    System.out.println("lamdada");
                }
            }
        }).start();

        //4jdk8 简化  lambda表达式  只需要关注线程体，删掉了接口名删掉了方法名，只需要关注你传什么参数，实现什么方法
        new Thread(()-> {
            for(int i=0;i<5;i++) {
                System.out.println("lamdada");
            }
        }
        ).start();

        //lambda推导，没有参数，没有返回值
        ILike like = new Like();
        like.lambda();//外部类的使用

        like = new Like2();
        like.lambda();//内部类的使用

        //3方法内部类
        class Like3 implements ILike{
            public void lambda() {
                System.out.println("i like lambda3 ");
            }

        }
        like = new Like3();
        like.lambda();//方法内部类的使用

        //4匿名内部类
        like = new ILike() {
            public void lambda() {
                System.out.println("i like lambda4 ");
            }
        };
        like.lambda();//匿名内部类的使用

        //5lambda
        like = ()-> {
            System.out.println("i like lambda5 ");
        };
        like.lambda();


		/*
		 *lambda推导必须存在类型，以上是like
		()-> {
			System.out.println("i like lambda5 ");
		}.lambda();//这样不对
		*/

		//lambda推导 +参数
        ILove love =(int a) -> {//只要把方法 拷贝过来，不需要方法名
            System.out.println("i like lambda -->"+a);
        };
        love.lambda(100);

        //简化
        love =(a) -> {//类型也可以拿掉
            System.out.println("i like lambda -->"+a);
        };
        love.lambda(50);

        love =a -> {//括号也省略
            System.out.println("i like lambda -->"+a);
        };
        love.lambda(5);

        love =a ->System.out.println("i like lambda -->"+a);//只有一行代码，花括号也省略
        love.lambda(0);

        //lambda推导 +参数+返回值
        IInterest interest = (int a,int c)-> {
            System.out.println("i like lambda -->"+(a+c));
            return a+c;
        };
        interest.lambda(100,200);

        interest = (a,c)-> {//去掉类型，多个参数括号不能省略
            System.out.println("i like lambda -->"+(a+c));
            return a+c;
        };
        interest.lambda(200,200);

        interest = (a,c)-> {//假设只有一行代码
            return a+c;
        };
        interest = (a,c)-> a+c;//那么可以省掉

        interest = (a,c)-> 100;//相当于返回值是100

        System.out.println(interest.lambda(10, 20));

        //lambda推导
        new Thread(()->{
            for(int i=0;i<10;i++) {
                System.out.println("一边学习lambda");
            }
        }) .start();
        new Thread(()->	System.out.println("一边学习奔溃")) .start();










    }
    //2静态内部类
    static class Like2 implements ILike {
        public void lambda() {
            System.out.println("i like lambda2 ");
        }

    }
}
class StartThread extends Thread {
    /**
     * 线程入口点
     */
    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println("一边听歌");
        }
    }
}

class TDownloader extends Thread {
    private String url; //远程路径
    private String name;  //存储名字

    public TDownloader(String url, String name) {//构造器
        this.url = url;
        this.name = name;
    }

    @Override
    public void run() {//线程体中
        WebDownloader wd = new WebDownloader();
        wd.download(url, name);
        System.out.println(name);
    }
}

class WebDownloader {
    /**
     * 下载
     * @param url
     * @param name
     */
    public void download(String url,String name) {
        try {
            FileUtils.copyURLToFile(new URL(url), new File(name));
        } catch (MalformedURLException e) {
            e.printStackTrace();
            System.out.println("不合法的url");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("下载失败");
        }
    }
}

class StartRun implements Runnable {
    /**
     * 线程入口点
     */
    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println("一边玩电脑");
        }
    }
}

class IDownloader implements Runnable {
    private String url; //远程路径
    private String name;  //存储名字

    public IDownloader(String url, String name) {
        this.url = url;
        this.name = name;
    }

    @Override
    public void run() {
        WebDownloader wd = new WebDownloader();
        wd.download(url, name);
        System.out.println(name);
    }
}

class Web12306 implements Runnable {
    //票数
    private int ticketNums = 10;

    @Override
    public void run() {
        while (true) {
            if (ticketNums < 0) {
                break;
            }
            try {
                Thread.sleep(100);//模拟网络延时，睡200ms再去执行，可能会出现负数，并发的问题
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "-->" + ticketNums--);//这里知道谁运行的我，获得线程代理的名字
        }
    }
}

class Racer implements Runnable {
    private String winner;//胜利者

    @Override
    public void run() {
        for (int steps = 1; steps <= 20; steps++) {    //假设有100步
            //模拟休息
            if (Thread.currentThread().getName().equals("rabbit") && steps % 3 == 0) {//如果你是兔子，就让你延时，没走10步睡一下
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(Thread.currentThread().getName() + "-->" + steps);
            //比赛是否结束，每走一步看看比赛是否结束
            boolean flag = gameOver(steps);
            if (flag) {
                break;
            }
        }
    }

    private boolean gameOver(int steps) {//谁先达到100步谁就胜利
        if (winner != null) { //存在胜利者
            return true;
        } else {
            if (steps == 20) {
                winner = Thread.currentThread().getName();//
                System.out.println("winner==>" + winner);
                return true;
            }
        }
        return false;
    }
}

class CDownloader implements Callable<Boolean> {
    private String url; //远程路径
    private String name;  //存储名字

    public CDownloader(String url, String name) {
        this.url = url;
        this.name = name;
    }

    @Override
    public Boolean call() throws Exception {
        WebDownloader wd = new WebDownloader();
        wd.download(url, name);
        System.out.println(name);
        return true;
    }
}

class CRacer implements Callable<Integer> {
    private String winner;//胜利者

    @Override
    public Integer call() throws Exception {
        for (int steps = 1; steps <= 20; steps++) {
            //模拟休息
            if (Thread.currentThread().getName().equals("pool-1-thread-1") && steps % 3 == 0) {
                Thread.sleep(100);
            }
            System.out.println(Thread.currentThread().getName() + "-->" + steps);
            //比赛是否结束
            boolean flag = gameOver(steps);
            if (flag) {
                return steps;
            }
        }
        return null;
    }

    private boolean gameOver(int steps) {
        if (winner != null) { //存在胜利者
            return true;
        } else {
            if (steps == 20) {
                winner = Thread.currentThread().getName();
                System.out.println("winner==>" + winner);
                return true;
            }
        }
        return false;
    }
}

class TestThread01 extends Thread {//自定义类继承Thread类

    //run()方法里是线程体
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println(this.getName() + ":" + i);//getName()方法是返回线程名称
        }
    }
}


class TestThread02 implements Runnable {//自定义类实现Runnable接口；

    //run()方法里是线程体；
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName() + ":" + i);
        }
    }
}


interface Marry{
    void happyMarry();
}
//真实角色
class You implements Marry{

    @Override
    public void happyMarry() {
        System.out.println("you and 嫦娥终于奔月了....");
    }

}
//代理角色
class WeddingCompany implements Marry{//代理不结婚
    //真实角色
    private Marry target;
    public WeddingCompany(Marry target) {
        this.target = target;
    }
    @Override
    public void happyMarry() {
        ready();
        this.target.happyMarry();
        after();
    }

    private void ready() {
        System.out.println("布置猪窝。。。。");
    }
    private void after() {
        System.out.println("闹玉兔。。。。");
    }
}

class LambdaThread {
    //1静态内部类，随着外部类的加载而加载
    static class Test implements Runnable {
        public void run() {
            for (int i = 0; i < 5; i++) {
                System.out.println("lamdada");
            }
        }
    }
}


interface ILike{
    void lambda();
}
//1外部类
class Like implements ILike{

    @Override
    public void lambda() {
        System.out.println("i like lambda ");
    }

}

interface ILove{
    void lambda(int a);
}
//外部类
class Love implements ILove{

    @Override
    public void lambda(int a) {
        System.out.println("i like lambda -->"+a);
    }

}

interface IInterest{
    int lambda(int a,int b);
}
//外部类
class Interest implements IInterest{

    @Override
    public int lambda(int a,int c) {
        System.out.println("i like lambda -->"+(a+c));
        return a+c;
    }

}


