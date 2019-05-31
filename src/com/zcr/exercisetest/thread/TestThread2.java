package com.zcr.exercisetest.thread;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TestThread2 {
    public static void main(String[] args) throws InterruptedException{
        //线程状态
        /*新生状态--start()-->就绪状态--获得执行权/失去执行权--运行状态--run()方法结束-->死亡状态
                                    synchronized       wait()    sleep()/join()/IO流阻塞
                            锁可用  <                     |                 >
                            同步阻塞   <--notify()--  等待阻塞             其他阻塞
                        sleep()休眠时间到，join()联合线程执行完毕，IO方法阻塞结束
        1.新生状态。Thread t = new Thread()线程对象一旦创建，就进入到了新生状态
        new 线程有了自己的工作空间，一个工作空间针对一个线程
        2.就绪状态。当调用start()方法时，线程立即进入就绪状态，但是不意味着立即调度执行
        start就绪状态具备了运行条件，还没有分配到CPU
        有四种方法可以进入就绪状态1start()2解除3yield()中断一下4jvm切换）
        3.运行状态。进入运行状态，线程才真正执行线程体的代码块
        系统选定才能获得CPU 只有从就绪到这里
        4.阻塞状态。当调用sleep()\wait()或同步锁定时，线程进入了阻塞状态，所谓阻塞就是代码不往下执行，在等待着，
        同理不保证调用以上方法就立即阻塞。阻塞事件解除武装后，重新进入就绪状态，等待CPU调度执行才进入运行状态。
        有四种方法可以进入阻塞状态1sleep()2wait()3join()插队4read()write()）
        5.死亡状态。控制线程进入死亡状态，也就是想法设法使得线程体的代码执行完毕或中断执行即可。一旦进入死亡状态，不再调用start()方法再次启动线程。
        1正常终止2线程被强制终止stop() destory()

        线程方法：
        sleep()使线程停止运行一段时间，将处于阻塞状态。如果调用了sleep()方法后，没有其他等待执行的线程，这个时候当前线程不会马上恢复执行。
        join()阻塞指定线程等到另一个线程完成以后再继续执行。
        yield()让当前正在执行线程暂停，不是阻塞线程，而是将线程转入就绪状态；调用以后，如果没有其他等待执行的线程，此时当前线程马上就会恢复执行
        setDaemon()可以将指定的线程设置成后台线程，守护线程；创建用户线程的线程结束时，后台线程也将随之消亡；只能在线程启动之前把它设为后台线程
        setPriority(int newPriority) getPriority()线程的优先级代表的是概率 范围从1到10，默认为5
        stop()不推荐使用

        一个线程对象在它的生命周期内，需要经历5个状态。
▪ 新生状态(New)
用new关键字建立一个线程对象后，该线程对象就处于新生状态。处于新生状态的线程有自己的内存空间，通过调用start方法进入就绪状态。
▪ 就绪状态(Runnable)
处于就绪状态的线程已经具备了运行条件，但是还没有被分配到CPU，处于“线程就绪队列”，等待系统为其分配CPU。
就绪状态并不是执行状态，当系统选定一个等待执行的Thread对象后，它就会进入执行状态。一旦获得CPU，线程就进入运行状态并自动调用自己的run方法。
有4中原因会导致线程进入就绪状态：
1.新建线程：调用start()方法，进入就绪状态;
2.阻塞线程：阻塞解除，进入就绪状态;
3. 运行线程：调用yield()方法，直接进入就绪状态;
4. 运行线程：JVM将CPU资源从本线程切换到其他线程。
▪ 运行状态(Running)
在运行状态的线程执行自己run方法中的代码，直到调用其他方法而终止或等待某资源而阻塞或完成任务而死亡。
如果在给定的时间片内没有执行结束，就会被系统给换下来回到就绪状态。也可能由于某些“导致阻塞的事件”而进入阻塞状态。
▪ 阻塞状态(Blocked)
阻塞指的是暂停一个线程的执行以等待某个条件发生(如某资源就绪)。有4种原因会导致阻塞：
1.执行sleep(int millsecond)方法，使当前线程休眠，进入阻塞状态。当指定的时间到了后，线程进入就绪状态。
2.执行wait()方法，使当前线程进入阻塞状态。当使用nofity()**方法唤醒这个线程后，它进入就绪状态。
3.线程运行时，某个操作进入阻塞状态，比如执行IO流操作(**read()/write()**方法本身就是阻塞的方法)。只有当引起该操作阻塞的原因消失后，线程进入就绪状态。
4. join()线程联合: 当某个线程等待另一个线程执行结束后，才能继续执行时，使用join()方法。
▪ 死亡状态(Terminated)
死亡状态是线程生命周期中的最后一个阶段。线程死亡的原因有两个。一个是正常运行的线程完成了它run()方法内的全部工作;
另一个是线程被强制终止，如通过执行stop()或destroy()**方法来终止一个线程(注：stop()/destroy()方法已经被JDK废弃，不推荐使用)。
当一个线程进入死亡状态以后，就不能再回到其它状态了。
         */

        //终止线程的典型方式
        //终止线程我们一般不使用JDK提供的stop()/destroy()方法(它们本身也被JDK废弃了)。通常的做法是提供一个boolean型的终止变量，当这个变量置为false，则终止线程的运行。
        TestThreadCiycle ttc = new TestThreadCiycle("线程A:");
        Thread t1 = new Thread(ttc);// 新生状态
        t1.start();// 就绪状态
        for (int i = 0; i < 10; i++) {
            System.out.println("主线程" + i);
        }
        ttc.terminate();
        System.out.println("ttc stop!");

        //暂停线程执行sleep/礼让yield
        /*sleep(时间)指定当前线程阻塞的毫秒数；存在异常InterruptedException;
        时间达到后线程进入就绪状态；可以模拟网络延时、倒计时等
        每个对象都有一个锁，不会释放锁

        这个休息的特点是抱着锁睡觉，站在马路中间谁都过不去
        sleep()方法与对象没关系，谁执行这个线程体，谁就去执行sleep()操作
        1.抢票，模拟网络延时 2.模拟兔子休息 3.实现倒计时 4.
        */
        //暂停线程的方法-sleep()
        //运行时可以感受到每条结果输出之前的延迟，是Thread.sleep(2000)语句在起作用
        StateThread thread1 = new StateThread();
        thread1.start();
        StateThread thread2 = new StateThread();
        thread2.start();

        Date endTime = new Date(System.currentTimeMillis() + 1000*100);
        long end = endTime.getTime();
        System.out.println(endTime);
        System.out.println(end);
        while (true){
            System.out.println(new SimpleDateFormat("mm:ss").format(endTime));
            Thread.sleep(1000);
            endTime = new Date(endTime.getTime()-1000);
            if (end - 1000 > endTime.getTime()){
                break;
            }

        }

        //暂停线程的方法-yield()
        //可以引起线程切换，但运行时没有明显延迟
        //礼让线程，让当前正在执行的线程暂停；不是阻塞线程，而是将线程从运行状态转入就绪状态；让CPU调度器重新调度
        //可能礼让成功，也可能又重回来调用自己了。即有时候礼让成功，有时候礼让不成功。
        StateThread2 thread3 = new StateThread2();
        thread3.start();
        StateThread2 thread4 = new StateThread2();
        thread4.start();

        MyYield my = new MyYield();
        new Thread(my,"a").start();
        new Thread(my,"b").start();

        //用lamda
        new Thread(
                ()->{
                    for (int i = 0; i < 10; i++) {
                        System.out.println("lamda");
                    }
                }
        ).start();
        for (int i = 0; i < 10; i++) {
            if (i%3==0){
                Thread.yield();
            }
            System.out.println("main---");
        }


        //总结
        /*暂停线程执行常用的方法有sleep()和yield()方法，这两个方法的区别是：
        1.sleep()方法：可以让正在运行的线程进入阻塞状态，直到休眠时间满了，进入就绪状态。
        2.yield()方法：可以让正在运行的线程直接进入就绪状态，让出CPU的使用权。
        */

        //线程的联合join()
        //合并线程，待此线程执行完成后，再执行其他线程，其他线程阻塞
        //插队线程，合并线程。一个车插到别的车前面，别的车就得等插队车走后才能走，并且别的车进入阻塞状态。
        /*join()写在谁的run方法体中就阻塞谁，谁去调用join()谁就去插队。

        线程A在运行期间，可以调用线程B的join()方法，让线程B和线程A联合。这样，线程A就必须等待线程B执行完毕后，才能继续执行。
        如下面示例中，“爸爸线程”要抽烟，于是联合了“儿子线程”去买烟，必须等待“儿子线程”买烟完毕，“爸爸线程”才能继续抽烟。
        */
        System.out.println("爸爸和儿子买烟故事");
        Thread father = new Thread(new FatherThread());
        father.start();

        //lamda表达式
        Thread t11 = new Thread(
                ()->{
                    for (int i = 0; i < 10; i++) {
                        System.out.println("lambda---"+i);
                    }
                }
        );
        t11.start();
        for (int j = 0; j < 10; j++) {
            if (j == 3){
                t11.join();
            }
            System.out.println("main---"+j);
        }

        //深度观察状态
        /*进入就绪状态1.start()2.阻塞解除3.yield()4JVM
        进入运行状态是CPU控制的
        阻塞1.sleep()2.wait()
        死亡terminate
        */
        Thread tt = new Thread(
                ()->{
                    for (int i = 0; i < 5; i++) {
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println("----");
                }
        );
        //观察状态
        Thread.State state = tt.getState();
        System.out.println(state);//NEW
        tt.start();
        state = tt.getState();
        System.out.println(state);//RUNNABLE
        while (state != Thread.State.TERMINATED){
            Thread.sleep(200);
            state = tt.getState();
            System.out.println(state);//TIMED WAITING
        }
        state = tt.getState();
        System.out.println(state);//TERMINATED

        //线程优先级priority
        /*Java提供一个线程调度器来监控程序中启动后进入就绪状态的所有线程。线程调度器按照线程的优先级决定应该调度哪个线程来执行。
        线程优先级用数字来表示，范围从1到10。Thread.MIN_PRIORITY=1;Thread.MAX_PRIORITY = 10;Thread.NORM_PRIORITY=5;
        使用以下方法获得或设置线程对象的优先级：
        int getPriority()
        void setpriority(int newPriority)
        isAlive()
        setName()
        getName()
        currentThread()
        优先级的设定建议在start()调用前
        意：优先级低只是意味着获得调度的概率低，并不是绝对先调用优先级高后调用优先级低的线程。
        */
        //线程常用方法
        Runnable r = new MyThread();
        Thread t = new Thread(r, "Name test");//定义线程对象，并传入参数；
        t.start();//启动线程；
        System.out.println("name is: " + t.getName());//输出线程名称；
        Thread.currentThread().sleep(5000);//线程暂停5分钟；
        System.out.println(t.isAlive());//判断线程还在运行吗？
        System.out.println("over!");

        //线程的优先级
       /* 1.处于就绪状态的线程，会进入“就绪队列”等待JVM来挑选。
        2.线程的优先级用数字表示，范围从1到10，一个线程的缺省优先级是5。
        3.使用下列方法获得或设置线程对象的优先级。
        int getPriority();
        void setPriority(int newPriority);
        注意：优先级低只是意味着获得调度的概率低。并不是绝对先调用优先级高的线程后调用优先级低的线程。
        */
        Thread t111 = new Thread(new MyThread(), "t1");
        Thread t2 = new Thread(new MyThread(), "t2");
        t111.setPriority(1);
        t2.setPriority(10);
        t111.start();
        t2.start();

        //守护进程damon
        /*线程分为用户线程和守护线程；虚拟机必须确保用户线程执行完毕；
        虚拟机不用等待守护线程执行完毕；如后台记录操作日志，监控内存使用等等
        */
        God god = new God();
        You1 you = new You1();
        Thread ttd = new Thread(god);
        ttd.setDaemon(true);//将用户线程调整为守护线程
        ttd.start();
        new Thread(you).start();





    }
}

class TestThreadCiycle implements Runnable {
    String name;
    boolean live = true;// 标记变量，表示线程是否可中止；

    public TestThreadCiycle(String name) {
        super();
        this.name = name;
    }

    public void run() {
        int i = 0;
        //当live的值是true时，继续线程体；false则结束循环，继而终止线程体；
        while (live) {
            System.out.println(name + (i++));
        }
    }

    public void terminate() {
        live = false;
    }
}

class StateThread extends Thread {
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println(this.getName() + ":" + i);
            try {
                Thread.sleep(2000);//调用线程的sleep()方法；
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class StateThread2 extends Thread {
    public void run() {
        for (int i = 0; i < 6; i++) {
            System.out.println(this.getName() + ":" + i);
            Thread.yield();//调用线程的yield()方法；
        }
    }
}

class MyYield implements Runnable{
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+"--->start");
        Thread.yield();
        System.out.println(Thread.currentThread().getName()+"--->end");
    }
}

class FatherThread implements Runnable {
    public void run() {
        System.out.println("爸爸想抽烟，发现烟抽完了");
        System.out.println("爸爸让儿子去买包红塔山");
        Thread son = new Thread(new SonThread());
        son.start();
        System.out.println("爸爸等儿子买烟回来");
        try {
            son.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println("爸爸出门去找儿子跑哪去了");
            // 结束JVM。如果是0则表示正常结束；如果是非0则表示非正常结束
            System.exit(1);
        }
        System.out.println("爸爸高兴的接过烟开始抽，并把零钱给了儿子");
    }
}

class SonThread implements Runnable {
    public void run() {
        System.out.println("儿子出门去买烟");
        System.out.println("儿子买烟需要10分钟");
        try {
            for (int i = 1; i <= 10; i++) {
                System.out.println("第" + i + "分钟");
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("儿子买烟回来了");
    }
}

class MyThread implements Runnable {
    //线程体；
    public void run() {
        for (int i = 0; i < 10; i++)
            System.out.println(i);
    }
}

class You1 extends Thread{
    @Override
    public void run() {
        for (int i = 0; i < 30; i++) {
            System.out.println("happy life...");
        }
        System.out.println("ooooo...");
    }
}
class God extends Thread{
    @Override
    public void run() {
        System.out.println("结束了");
    }
}
