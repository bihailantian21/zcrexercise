package com.zcr.exercisetest.thread;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class TestThread3 {
    public static void main(String[] args)  throws InterruptedException {
        //线程同步
        //并发：synchronized：同一个对象多个线程同时操作。（同时操作同一个账户；同时购买同一车次的票）
        //比如说：多个代理同时去访问：有负数的情况、有相同的情况。分析：负数：临界值没有控制 相同的值：拷贝10的时候已经都拿到自己的工作台

        //线程不安全：取钱
        Account account =new Account(100,"结婚礼金");
        Drawing u1 = new Drawing(account,80,"可悲的你");
        Drawing w1 = new Drawing(account,90,"happy的她");
        u1.start();
        w1.start();

        //线程不安全：操作容器
        List<String> list = new ArrayList<String>();
        for(int i=0;i<10;i++) {
            new Thread(()->{
                list.add(Thread.currentThread().getName());
            }) .start();
        }
        System.out.println(list.size());

        //同步的两个条件：队列与锁
        //保证线程安全：排队，根据算法决定谁先用谁后用。怎么知道谁在用，卡，锁。
        //每个对象都有一个排它锁
        /*线程同步：现实生活中，我们会遇到"同一个资源多个人都想使用"的问题。比如：派发礼品，多个人都想获得。天然的解决方法就是：
        大家排队，前一个人领取完后，后一个人再来领取。
        处理多线程问题时，多个线程访问同一个对象，并且某些线程还想修改这个对象。这时候，我们就需要用到线程同步。线程同步其实就是一种等待机制，
        多个需要同时访问此对象的线程进入这个对象的等待池形成队列，等待前面的线程使用完毕后，下一个线程再使用。

        由于同一进程的多个线程共享同一块存储空间，在带来方便的同时，也带来了访问冲突的问题。为了保证数据在方法中被访问时的正确性，
        在访问时加入了锁机制（synchronized），当一个线程获得对象的排他锁，独占资源，其他线程必须等待，使用后释放锁即可，存在以下问题：
        1.一个线程持有锁会导致其他所有需要此锁的线程挂起。
        2.在多线程竞争下，加锁、释放锁会导致比较多的上下文切换和调度延时，引起性能问题。
        3.如果一个优先级高的线程等待一个优先级低的线程释放锁会导致优先级倒置，引起性能问题。

        由于我们可以通过private关键字来保证数据对象只能被方法访问，所以我们只需针对方法提出一套机制，这套机制就是
        synchronized关键字，它包括两种用法：synchronized方法和synchronized块。
        同步方法：
        public synchonized void method(int args){}
        synchronized方法控制对"成员变量|类变量"对象的访问：每个对象对应一把锁，每个synchronized方法都必须获得调用该方法的对象的锁方能执行，
        否则所属线程阻塞。方法一旦执行，就独占该锁，直到从该方法返回时才将锁释放，此后被阻塞的线程方法方能获得该锁，重新进入可执行状态。
        缺陷：若将一个大的方法声明为synchonized将会大大影响效率。
        一个一个蹦出来的，有队列
        synchronized操作了什么，锁了对象的资源、而不是锁方法。
        */
        //一份资源
        SafeWeb12306 web =new SafeWeb12306();
        //多个代理
        new Thread(web,"问问").start();
        new Thread(web,"潇洒").start();
        new Thread(web,"叶先生").start();

        //账户
        Account account2 =new Account(100,"结婚礼金");
        SafeDrawing you = new SafeDrawing(account2,80,"可悲的你2");
        SafeDrawing wife = new SafeDrawing(account2,90,"happy的她2");
        you.start();
        wife.start();
        /*synchronized锁的是账户而不是提款机
        以上代码失败，执行结果还是有负数
        */

        /*synchronized块，同步块：synchronized(obj){},obj称之为同步监视器。
        obj可以是任何对象，但是推荐使用共享资源作为同步监视器。
        同步方法中无需执行同步监视器，因为同步方法的同步监视器是this即该对象本身，或class即类的模子。
        同步监视器的执行过程：
        第一个线程访问，锁定同步监视器，执行其中代码
        第二个线程访问，发现同步监视器被锁定，无法访问
        第一个线程访问完毕，解锁同步监视器
        第二个线程访问，发现同步监视器未锁，锁定并访问。

        方法里面的块：局部块
        构造块：对象的信息
        静态块：类的信息
        同步块：

        account对象看有没有锁，有的话就阻塞。什么时候释放：所有操作都执行完

        */
        Account account3 =new Account(1000,"结婚礼金");
        SynDrawing you3 = new SynDrawing(account3,80,"可悲的你3");
        SynDrawing wife3 = new SynDrawing(account3,90,"happy的她3");
        you3.start();
        wife3.start();

        //线程安全：操作容器
        List<String> list2 = new ArrayList<String>();
        for(int i=0;i<10;i++) {
            new Thread(()->{
                //同步块
                synchronized(list2) {//添加的时候保证list是拿到锁的
                    list2.add(Thread.currentThread().getName());
                }
            }) .start();
        }
        Thread.sleep(10000);
        System.out.println(list.size());

        //同步性能分析
        //一份资源
        SynWeb12306 web2 =new SynWeb12306();
        //多个代理
        new Thread(web2,"巍峨").start();
        new Thread(web2,"撒阿斯顿").start();
        new Thread(web2,"阿斯顿").start();

        //快乐影院
        Cinema c = new Cinema(2,"happy sxt");
        new Thread(new Customer(c,2),"老高").start();
        new Thread(new Customer(c,1),"老裴").start();

        //还要加上选位置功能，加上容器
        //可用位置
        List<Integer> available =new ArrayList<Integer>();
        available.add(1);
        available.add(2);
        available.add(3);
        available.add(6);
        available.add(7);
        //顾客需要的位置
        List<Integer> seats1 =new ArrayList<Integer>();
        seats1.add(1);
        seats1.add(2);
        List<Integer> seats2 =new ArrayList<Integer>();
        seats2.add(4);
        seats2.add(5);
        seats2.add(6);
        SxtCinema c2 = new SxtCinema(available,"happy sxt");
        new Thread(new HappyCustomer(c2,seats1),"老张").start();
        new Thread(new HappyCustomer(c2,seats2),"老王").start();

        //快乐火车票
        //以上都是使用同步块，以下使用同步方法
       /* 要使用同步方法只能写在12306里面，写完之后怎么和乘客打交道
        乘客我们让他继承Thread，直接作为代理，子类中加线程变量，把父类的构造器延续下来，同时加入了自己的线程变量
        用的时候我们要知道是哪个
        */
        Web123062 c3 = new Web123062(4,"happy sxt");
        new  Passenger(c3,"老哇",2).start();
        new  Passenger(c3,"老啊",1).start();

        //并发容器
        //锁定了list
        //list有对应的并发容器，自己进行锁定，就不需要我们来
        CopyOnWriteArrayList<String> list3 = new CopyOnWriteArrayList<String>();
        for(int i=0;i<10;i++) {
            new Thread(()->{
                list3.add(Thread.currentThread().getName());
            }) .start();
        }
        Thread.sleep(10000);
        System.out.println(list3.size());

        //死锁_产生与解决
        /*死锁：多个线程各自占有一些共享资源，并且互相等待其他线程占有的资源才能进行，而导致两个或多个线程都在等待对方释放资源，
        都停止执行的情形。某一个同步代码块同时拥有"两个以上对象的锁"时，就可能会发生死锁问题。

        死锁: 过多的同步可能造成相互不释放资源
        从而相互等待，一般发生于同步中持有多个对象的锁
        避免: 不要在同一个代码块中，同时持有多个对象的锁

        注释的那种情况：造成了死锁
        解决方式：后一种 往外挪一下，不要锁套锁
        */
        Markup g1 = new Markup(1,"张柏芝");
        Markup g2 = new Markup(0,"王菲");
        g1.start();
        g2.start();

        //总结
        /*
        什么是线程同步
        ▪ 同步问题的提出
        现实生活中，我们会遇到“同一个资源，多个人都想使用”的问题。 比如：教室里，只有一台电脑，多个人都想使用。
        天然的解决办法就是，在电脑旁边，大家排队。前一人使用完后，后一人再使用。
        ▪ 线程同步的概念
        处理多线程问题时，多个线程访问同一个对象，并且某些线程还想修改这个对象。
        这时候，我们就需要用到“线程同步”。 线程同步其实就是一种等待机制， 这个对象的等待池形成队列，等待前面的线程使用完毕后，下一个线程再使用。

        多线程操作同一个对象(未使用线程同步)没有线程同步机制，两个线程同时操作同一个账户对象，竟然从只有100元的账户，
        轻松取出80*2=160元，账户余额竟然成为了-60。这么大的问题，显然银行不会答应的。

        实现线程同步

        由于同一进程的多个线程共享同一块存储空间，在带来方便的同时，也带来了访问冲突的问题。
        Java语言提供了专门机制以解决这种冲突，有效避免了同一个数据对象被多个线程同时访问造成的这种问题。
        由于我们可以通过 private 关键字来保证数据对象只能被方法访问，所以我们只需针对方法提出一套机制，这套机制就是synchronized关键字，
        它包括两种用法：synchronized 方法和 synchronized 块。

        ▪ synchronized 方法
        通过在方法声明中加入 synchronized关键字来声明，语法如下：
        public synchronized void accessVal(int newVal);
        synchronized 方法控制对“对象的类成员变量”的访问：每个对象对应一把锁，每个 synchronized 方法都必须获得调用该方法的对象的锁方能执行，
        否则所属线程阻塞，方法一旦执行，就独占该锁，直到从该方法返回时才将锁释放，此后被阻塞的线程方能获得该锁，重新进入可执行状态。

        ▪ synchronized块
        synchronized 方法的缺陷：若将一个大的方法声明为synchronized 将会大大影响效率。
        Java 为我们提供了更好的解决办法，那就是 synchronized 块。 块可以让我们精确地控制到具体的“成员变量”，缩小同步的范围，提高效率。
        synchronized 块：通过 synchronized关键字来声明synchronized 块，语法如下：
        synchronized(syncObject)
        　 {
        　　 //允许访问控制的代码
        　 }
        “synchronized (account)” 意味着线程需要获得account对象的“锁”才有资格运行同步块中的代码。
        Account对象的“锁”也称为“互斥锁”，在同一时刻只能被一个线程使用。
        A线程拥有锁，则可以调用“同步块”中的代码;B线程没有锁，则进入account对象的“锁池队列”等待，直到A线程使用完毕释放了account对象的锁，
        B线程得到锁才可以开始调用“同步块”中的代码。

        死锁及解决方案
        死锁的概念
        “死锁”指的是：
        多个线程各自占有一些共享资源，并且互相等待其他线程占有的资源才能进行，而导致两个或者多个线程都在等待对方释放资源，都停止执行的情形。
        因此， 某一个同步块需要同时拥有“两个以上对象的锁”时，就可能会发生“死锁”的问题。
        下面案例中，“化妆线程”需要同时拥有“镜子对象”、“口红对象”才能运行同步块。
        那么，实际运行时，“小丫的化妆线程”拥有了“镜子对象”，“大丫的化妆线程”拥有了“口红对象”，都在互相等待对方释放资源，才能化妆。
        这样，两个线程就形成了互相等待，无法继续运行的“死锁状态”。(两线程都在等对方的资源，都处于停滞状态)
        死锁的解决方法
        死锁是由于“同步块需要同时持有多个对象锁造成”的，要解决这个问题，思路很简单，就是：
        同一个代码块，不要同时持有两个对象锁。(两线程都可以得到需要的资源，程序正常运行结束)
         */

        //线程并发协作(生产者/消费者模式)
        /*生产者/消费者模式
        线程与线程之间如何通信 ？
        设计模式是指：类与类之间的组织方式
        并发的模式：生产者消费者模型
        阿里：应用层 服务层 数据层
                服务层与应用层之间进行解耦
        服务层：用户中心 商户中心 交易中心
        应用层：用户界面
                消息队列*/

        /*线程通信：
        应用场景：生产者和消费者问题
        假设仓库中只能存放一件产品，生产者将生产出来的产品放入仓库，消费者将仓库中的产品取走消费。
        如果仓库中没有产品，则生产者将产品放入仓库，否则停止生产并等待，直到仓库中的产品被消费者取走为止。
        如果仓库中放有产品，则消费者可以将产品取走消费，否则停止消费并等待，直到仓库中再次放入产品为止。
        分析：这是一个线程同步问题，生产者和消费者共享同一个资源，并且生产者和消费者之间相互依赖互为条件。
        对于生产者，没有生产产品之前，要通知消费者等待。而生产了产品之后，又要马上通知消费者消费。
        对于消费者，在消费之后，要通知生产者已经消费结束，需要继续生产新的产品以供消费。
        在生产者消费者问题中，仅有synchronized是不够的：
        synchronized可以阻止并发更新同一个共享资源，实现了同步。
        synchronized不能用来实现不同线程之间的消息传递（通信）

        解决方法1：并发协作模型"生产者/消费者模式"--》管程法
                生产者：负责生产数据的模块（这里模块可能是：方法、对象、线程、进程）
                消费者：负责处理数据的模块（这里模块可能是：方法、对象、线程、进程）
                缓冲区：消费者不能直接使用生产者的数据，他们都之间有个缓冲区
        生产者将生产好的数据放入缓冲区，消费者从缓冲区中拿走要处理的数据。

        解决方法2：并发协作模型"生产者/消费者模式"--》信号灯法

        Java提供了三个方法解决线程之间的通信问题：
        wait()表示线程一直等待，直到其他线程通知，与sleep不同，会释放锁
        wait(long timeout)指定等待的毫秒数
        notify()唤醒一个处于等待状态的线程
        notifyAll()唤醒同一个对象上所有调用wait（）方法的线程，优先级别高的线程优先调度
        都只能在同步方法或者同步代码块中使用，否则会抛出异常
                */

        //管程法
       /* 生产者：多线程
        缓冲区：容器 并发
        存：不够
        取：空
        消费者：多线程
        比如：我们要操作馒头*/
       //协作模型:生产者消费者实现方式一:管程法（借助缓冲区）根据容器进行交流的
        SynContainer container = new SynContainer();
        new Productor(container).start();
        new Consumer(container).start();

        //协作模型:生产者消费者实现方式二:信号灯（借助标志位）
        Tv tv  =new Tv();
        new Player(tv).start();
        new Watcher(tv).start();

        //总结：生产者/消费者模式
        /*多线程环境下，我们经常需要多个线程的并发和协作。这个时候，就需要了解一个重要的多线程并发协作模型“生产者/消费者模式”。

        Ø 什么是生产者?
        生产者指的是负责生产数据的模块(这里模块可能是：方法、对象、线程、进程)。
        Ø 什么是消费者?
        消费者指的是负责处理数据的模块(这里模块可能是：方法、对象、线程、进程)。
        Ø 什么是缓冲区?
        消费者不能直接使用生产者的数据，它们之间有个“缓冲区”。生产者将生产好的数据放入“缓冲区”，消费者从“缓冲区”拿要处理的数据。

        缓冲区是实现并发的核心，缓冲区的设置有3个好处：
        Ø 实现线程的并发协作
        有了缓冲区以后，生产者线程只需要往缓冲区里面放置数据，而不需要管消费者消费的情况;
        同样，消费者只需要从缓冲区拿数据处理即可，也不需要管生产者生产的情况。 这样，就从逻辑上实现了“生产者线程”和“消费者线程”的分离。
        Ø 解耦了生产者和消费者
        生产者不需要和消费者直接打交道。
        Ø 解决忙闲不均，提高效率
        生产者生产数据慢时，缓冲区仍有数据，不影响消费者消费;消费者处理数据慢时，生产者仍然可以继续往缓冲区里面放置数据 。

        线程并发协作总结：
        线程并发协作(也叫线程通信)，通常用于生产者/消费者模式，情景如下：
        1.生产者和消费者共享同一个资源，并且生产者和消费者之间相互依赖，互为条件。
        2.对于生产者，没有生产产品之前，消费者要进入等待状态。而生产了产品之后，又需要马上通知消费者消费。
        3.对于消费者，在消费之后，要通知生产者已经消费结束，需要继续生产新产品以供消费。
        4.在生产者消费者问题中，仅有synchronized是不够的。
        synchronized可阻止并发更新同一个共享资源，实现了同步;
        synchronized不能用来实现不同线程之间的消息传递(通信)。
        5.那线程是通过哪些方法来进行消息传递(通信)的呢?见如下总结：wait()notify()notifyAll()
        6.以上方法均是java.lang.Object类的方法;
        都只能在同步方法或者同步代码块中使用，否则会抛出异常。
        老鸟建议
        在实际开发中，尤其是“架构设计”中，会大量使用这个模式。 对于初学者了解即可，如果晋升到中高级开发人员，这就是必须掌握的内容。

        */

        //任务定时调度
       /* 通过Timer和Timetask，我们可以实现定时启动某个线程。
        java.util.Timer
        在这种实现方式中，Timer类作用是类似闹钟的功能，也就是定时或者每隔一定时间触发一次线程。
        其实，Timer类本身实现的就是一个线程，只是这个线程是用来实现调用其它线程的。
        构造方法
        Timer() Timer(boolean isDaemon) Timer(String name) Timer(String name,boolean isDaemon)
        方法
        cancel() purge() schedule(TimerTask task,long delay) schedule(TimerTask task,long delay,long period)
        schedule(TimerTask task,Date time) schedule(TimerTask task,Date firsttime,long period)

        java.util.TimerTask
        TimerTask类是一个抽象类，该类实现了Runnable接口，所以该类具备多线程的能力。
        在这种实现方式中，通过继承TimerTask使该类获得多线程的能力，
        将需要多线程执行的代码书写在run方法内部，然后通过Timer类启动线程的执行。
        构造方法
        TimeTask()
        方法
        cancel() run() scheduledExecutionTime()

        */
       //java.util.Timer的使用
        Timer t1 = new Timer();//定义计时器；
        MyTask task1 = new MyTask();//定义任务；
        t1.schedule(task1,3000);  //3秒后执行；
        //t1.schedule(task1,5000,1000);//5秒以后每隔1秒执行一次！
        //GregorianCalendar calendar1 = new GregorianCalendar(2010,0,5,14,36,57);
        //t1.schedule(task1,calendar1.getTime()); //指定时间定时执行；
       /* 运行以上程序时，可以感觉到在输出之前有明显的延迟(大概就是3秒!)。还有几个方法，我注释掉了，大家自己试试吧!
                在实际使用时，一个Timer可以启动任意多个TimerTask实现的线程，但是多个线程之间会存在阻塞。
                所以如果多个线程之间需要完全独立的话，最好还是一个Timer启动一个TimerTask实现。
        老鸟建议
        实际开发中，我们可以使用开源框架quanz，更加方便的实现任务定时调度。实际上，quanz底层原理就是我们这里介绍的内容。
        */

       //高级主题
        //定时调度
        Timer timer = new Timer();//就是一个闹钟
        //执行安排
        //timer.schedule(new MyTask(), 1000);  //执行任务一次
        //timer.schedule(new MyTask(), 1000,200); //执行多次  每隔200ms执行1次
        Calendar cal = new GregorianCalendar(2019,5,31,17,04,59);
        timer.schedule(new MyTask(), cal.getTime(),200); //指定时间

        //quartz
        /*任务定时调度
                Schdule调度器，控制所有的调度
                Trigger 触发条件，采用OSL模式
                JobDetail 需要处理的job
                Job 执行逻辑
        DSL Domain-specific language领域特定语言，针对一个特定的领域，具有受限表达性的一种计算机程序语言，
        即领域专用语言，声明式编程：
        1Method Chaining方法链 Fluent Style流畅风格 builder模式构建器
        2Nested Functions 嵌套函数
        3Lambda Expressions/Closures
        4Functional Sequence

        调度器
        除法器
        任务
        集成到了spring框架中

        使用quartz
        需要去官网下载，下载好后需要导包。
        里面有很多案例。
        */



    }




}

class Account{
    int money; //金额
    String name; //名称
    public Account(int money, String name) {
        this.money = money;
        this.name = name;
    }
}

//模拟取款
class Drawing extends Thread{
    Account account ; //取钱的账户
    int drawingMoney ;//取的钱数
    int packetTotal ; //口袋的总数

    public Drawing(Account account, int drawingMoney,String name) {
        super(name);
        this.account = account;
        this.drawingMoney = drawingMoney;
    }

    @Override
    public void run() {
        if(account.money - drawingMoney<0) {
            return;
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        account.money -= drawingMoney;
        packetTotal += drawingMoney;
        System.out.println(this.getName()+"-->账户余额为:"+account.money);
        System.out.println(this.getName()+"-->口袋的钱为:"+packetTotal);
    }

}

class SafeWeb12306 implements Runnable{
    //票数
    private int ticketNums =10;
    private boolean flag = true;
    @Override
    public void run() {
        while(flag) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            test();
        }
    }
    //线程安全  同步
    public synchronized void test() {//B线程进来了，此时
        if(ticketNums<=0) {
            flag = false;
            return ;
        }
        //模拟延时
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+"-->"+ticketNums--);
    }
}



//模拟取款
class SafeDrawing extends Thread{
    Account account ; //取钱的账户
    int drawingMoney ;//取的钱数
    int packetTotal ; //口袋的总数

    public SafeDrawing(Account account, int drawingMoney,String name) {
        super(name);
        this.account = account;
        this.drawingMoney = drawingMoney;
    }

    @Override
    public void run() {
        test();
    }
    //目标不对，锁定失败  这里不是锁this 应该锁定 account
    public synchronized void test() {
        if(account.money -drawingMoney<0) {
            return;
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        account.money -=drawingMoney;
        packetTotal +=drawingMoney;
        System.out.println(this.getName()+"-->账户余额为:"+account.money);
        System.out.println(this.getName()+"-->口袋的钱为:"+packetTotal);
    }
}



//模拟取款 线程安全
class SynDrawing extends Thread{
    Account account ; //取钱的账户
    int drawingMoney ;//取的钱数
    int packetTotal ; //口袋的总数

    public SynDrawing(Account account, int drawingMoney,String name) {
        super(name);
        this.account = account;
        this.drawingMoney = drawingMoney;
    }

    @Override
    public void run() {
        test() ;
    }
    //目标锁定account
    public  void test() {
        //提高性能
        if(account.money<=0) {//都没有房间了，还要问有没有房间的钥匙么，就不需要了
            return ;
        }
        //同步块
        synchronized(account) {
            if(account.money -drawingMoney<0) {
                return;
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            account.money -=drawingMoney;
            packetTotal +=drawingMoney;
            System.out.println(this.getName()+"-->账户余额为:"+account.money);
            System.out.println(this.getName()+"-->口袋的钱为:"+packetTotal);
        }
    }
}


class SynWeb12306 implements Runnable{
    //票数
    private int ticketNums =10;
    private boolean flag = true;
    @Override
    public void run() {
        while(flag) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            test5();
        }
    }
    //线程安全:尽可能锁定合理的范围(不是指代码 指数据的完整性)
    //double checking
    public  void test5() {
        if(ticketNums<=0) {//考虑的是没有票的情况
            flag = false;
            return ;
        }
        synchronized(this) {
            if(ticketNums<=0) {//考虑最后的1张票
                flag = false;
                return ;
            }
            //模拟延时
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"-->"+ticketNums--);
        }
    }
    //线程不安全  范围太小锁不住
    public  void test4() {
        synchronized(this) {
            if(ticketNums<=0) {
                flag = false;
                return ;
            }
        }
        //模拟延时
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+"-->"+ticketNums--);

    }
    //线程不安全  ticketNums对象在变
    public  void test3() {
        synchronized((Integer)ticketNums) {//没锁对
            if(ticketNums<=0) {
                flag = false;
                return ;
            }
            //模拟延时
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"-->"+ticketNums--);
        }
    }
    //线程安全 范围太大 -->效率低下
    public  void test2() {
        synchronized(this) {//ticketNums、flag两
            if(ticketNums<=0) {
                flag = false;
                return ;
            }
            //模拟延时
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"-->"+ticketNums--);
        }
    }


    //线程安全  同步
    public synchronized void test1() {
        if(ticketNums<=0) {
            flag = false;
            return ;
        }
        //模拟延时
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+"-->"+ticketNums--);
    }
}


//顾客
class Customer implements Runnable{
    Cinema cinema;//去哪里看电影
    int seats;	//几个位置
    public Customer(Cinema cinema, int seats) {
        this.cinema = cinema;
        this.seats = seats;
    }

    @Override
    public void run() {
        synchronized(cinema) {//锁影院
            boolean flag = cinema.bookTickets(seats);
            if(flag) {
                System.out.println("出票成功"+Thread.currentThread().getName()+"-<位置为:"+seats);
            }else {
                System.out.println("出票失败"+Thread.currentThread().getName()+"-<位置不够");
            }
        }
    }

}

//影院
class Cinema{
    int available; //可用的位置
    String name; //名称
    public Cinema(int available, String name) {
        this.available = available;
        this.name = name;
    }

    //购票
    public boolean bookTickets(int seats) {
        System.out.println("可用位置为:"+available);
        if(seats>available) {
            return false;
        }
        available -=seats;
        return true;
    }
}

//顾客
class HappyCustomer implements Runnable{
    SxtCinema cinema;
    List<Integer> seats;
    public HappyCustomer(SxtCinema cinema, List<Integer> seats) {
        this.cinema = cinema;
        this.seats = seats;
    }

    @Override
    public void run() {
        synchronized(cinema) {
            boolean flag = cinema.bookTickets(seats);
            if(flag) {
                System.out.println("出票成功"+Thread.currentThread().getName()+"-<位置为:"+seats);
            }else {
                System.out.println("出票失败"+Thread.currentThread().getName()+"-<位置不够");
            }
        }
    }

}

//影院
class SxtCinema{
    List<Integer> available; //可用的位置
    String name; //名称
    public SxtCinema(List<Integer> available, String name) {
        this.available = available;
        this.name = name;
    }

    //购票
    public boolean bookTickets(List<Integer> seats) {//传具体的位置
        System.out.println("欢迎光临"+this.name+"，当前可用位置为:"+available);
        List<Integer> copy = new ArrayList<Integer>();
        copy.addAll(available);

        //相减
        copy.removeAll(seats);
        //判断大小
        if(available.size()-copy.size() !=seats.size()) {
            return false;
        }
        //成功
        available = copy;
        return true;
    }
}

//顾客
class Passenger extends  Thread{//Passenger 是代理
    int seats;
    public Passenger(Runnable target,String name,int seats) {
        super(target,name);
        this.seats = seats;
    }

}
//火车票网
class Web123062 implements Runnable{
    int available; //可用的位置
    String name; //名称
    public Web123062(int available, String name) {
        this.available = available;
        this.name = name;
    }

    public void run() {
        Passenger p = (Passenger)Thread.currentThread();//当前线程是顾客
        boolean flag = this.bookTickets(p.seats);
        if(flag) {
            System.out.println("出票成功"+Thread.currentThread().getName()+"-<位置为:"+p.seats);
        }else {
            System.out.println("出票失败"+Thread.currentThread().getName()+"-<位置不够");
        }
    }
    //购票
    public synchronized boolean bookTickets(int seats) {
        System.out.println("可用位置为:"+available);
        if(seats>available) {
            return false;
        }
        available -=seats;
        return true;
    }
}

//口红
class Lipstick{

}
//镜子
class Mirror{

}
//化妆
class Markup extends Thread{
    static Lipstick lipstick = new Lipstick();//来一个口红一面镜子
    static Mirror mirror = new Mirror();
    //选择
    int choice;
    //名字
    String girl;
    public Markup(int choice,String girl) {
        this.choice = choice;
        this.girl = girl;
    }

    @Override
    public void run() {
        //化妆
        markup();
    }
    //相互持有对方的对象锁-->可能造成死锁
    private void markup() {
        if(choice==0) {
            synchronized(lipstick) { //获得口红的锁
                System.out.println(this.girl+"涂口红");
                //1秒后想拥有镜子的锁
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
				/*synchronized(mirror) {
					System.out.println(this.girl+"照镜子");
				}*/
            }
            synchronized(mirror) {
                System.out.println(this.girl+"照镜子");
            }
        }else {
            synchronized(mirror) { //获得镜子的锁
                System.out.println(this.girl+"照镜子");
                //2秒后想拥有口红的锁
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
					/*synchronized(lipstick) {
						System.out.println(this.girl+"涂口红");
					}*/

            }
            synchronized(lipstick) {
                System.out.println(this.girl+"涂口红");
            }
        }
    }
}


//生产者
class Productor extends Thread{
    SynContainer container  ;
    public Productor(SynContainer container) {
        this.container = container;
    }

    public void run() {
        //生产
        for(int i=0;i<10;i++) {
            System.out.println("生产-->"+i+"个馒头");
            container.push(new Steamedbun(i) );
        }
    }
}
//消费者
class Consumer extends Thread{
    SynContainer container  ;
    public Consumer(SynContainer container) {
        this.container = container;
    }
    public void run() {
        //消费
        for(int i=0;i<10;i++) {
            System.out.println("消费-->"+container.pop().id+"个馒头");
        }
    }
}
//缓冲区
class SynContainer{
    Steamedbun[] buns = new Steamedbun[10]; //存储容器
    int count = 0; //计数器
    //存储 生产
    public synchronized void push(Steamedbun bun) {
        //何时能生产  容器存在空间
        //不能生产 只有等待
        if(count == buns.length) {//缓冲区满了
            try {
                //wait后，线程会将持有的锁释放，进入阻塞状态；
                //这样其它需要锁的线程就可以获得锁；
                this.wait(); //线程阻塞  消费者通知生产解除
                //这里的含义是执行此方法的线程暂停，进入阻塞状态，
                //等消费者消费了馒头后再生产。
            } catch (InterruptedException e) {
            }
        }
        // 唤醒在当前对象等待池中等待的第一个线程。
        //notifyAll叫醒所有在当前对象等待池中等待的所有线程。
        // 如果不唤醒的话。以后这两个线程都会进入等待线程，没有人唤醒。

        //存在空间 可以生产
        buns[count] = bun;
        count++;
        this.notifyAll();//存在数据了，可以通知消费了
    }
    //获取 消费
    public synchronized Steamedbun pop() {
        //何时消费 容器中是否存在数据
        //没有数据 只有等待
        if(count == 0) {//缓冲区为空
            try {
                //如果馒头筐是空的，就暂停此消费线程（因为没什么可消费的嘛）。
                //等生产线程生产完再来消费；
                this.wait(); //线程阻塞  生产者通知消费解除
            } catch (InterruptedException e) {
            }
        }
        //存在数据可以消费
        count --;//从最后一个拿
        Steamedbun bun = buns[count] ;

        this.notifyAll(); //存在空间了，可以唤醒对方生产了。所有的阻塞都被唤醒
        return bun;
    }
}
//馒头
class Steamedbun{
    int id;
    public Steamedbun(int id) {
        this.id = id;
    }

}

//生产者 演员
class Player extends Thread{
    Tv tv;
    public Player(Tv tv) {
        this.tv = tv;
    }

    public void run() {
        for(int i=0;i<10;i++) {
            if(i%2==0) {
                this.tv.play("奇葩说");
            }else {
                this.tv.play("太污了，喝瓶立白洗洗嘴");
            }
        }
    }
}
//消费者 观众
class Watcher extends Thread{
    Tv tv;
    public Watcher(Tv tv) {
        this.tv = tv;
    }

    public void run() {
        for(int i=0;i<10;i++) {
            tv.watch();
        }
    }
}
//同一个资源 电视
class Tv{
    String voice;
    //信号灯
    //T 表示演员表演 观众等待
    //F 表示观众观看 演员等待
    boolean flag = true;

    //表演
    public  synchronized void play(String voice) {
        //演员等待
        if(!flag) {//F 表示观众观看 演员等待
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //表演
        System.out.println("表演了:"+voice);
        this.voice = voice;
        //唤醒
        this.notifyAll();
        //切换标志
        this.flag =!this.flag;
    }
    //观看
    public synchronized  void watch() {
        //观众等待
        if(flag) {//T 表示演员表演 观众等待
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //观看
        System.out.println("听到了:"+voice);
        //唤醒
        this.notifyAll();
        //切换标志
        this.flag =!this.flag;
    }
}

class MyTask extends TimerTask {//自定义线程类继承TimerTask类；
    public void run() {
        for(int i=0;i<10;i++){
            System.out.println("任务1:"+i);
        }
    }
}

//任务类
class  MyTask2 extends TimerTask{

    @Override
    public void run() {
        for(int i=0;i<10;i++) {
            System.out.println("放空大脑休息一会");
        }
        System.out.println("------end-------");
    }

}