package com.zcr.exercisetest.thread;

import java.util.concurrent.atomic.AtomicInteger;

public class TestThread4 {
    //变量1
    private static int a = 0;
    //变量2
    private static boolean flag = false;

    private volatile static int num = 0;

    //库存
    private static AtomicInteger stock = new AtomicInteger(5);




    public static void main(String[] args) throws InterruptedException {
        //happenbefore
    /*你写的代码可能根本没有按照你期望的顺序执行，因为编译器和CPU会尝试重排指令使得代码更快的运行
        第一步从内存中获取指令fetch将指令进行解码翻译
        第二步从寄存器中拿出对应的值、工作内存，需要拷贝
        第三步计算
        第四步同步到主存
        看到下一个指令与上一条无关，那么就提前执行了指令重排对我们的指令是有影响的
    执行代码的顺序可能与编写代码不一致，即虚拟机优化代码顺序，则为指令重排
    happen-before:即，编译器或运行时环境为了优化程序性能而采取的对指令进行重新排序执行的一种手段
    在虚拟机层面，为了尽可能减少内存操作速度远慢于CPU运行速度所带来的CPU空置的影响，虚拟机会按照自己的一些规则将程序编写顺序打乱--
    即写在后面的代码在时间顺序上可能会先执行，而写在前面的代码会后执行--以即可能充分的利用CPU
    那上面的例子来说，假如不是a=1的操作，而是a=new[1024*1024](分配1M空间)，那么它会运行的很慢，此时CPU是等待其执行结束呢？还是先执行下面那句flag=true呢
    显然先执行flag=true可以提前使用CPU，加快整体效率，当然这样的前提是不会产生什么错误。
    虽然这里有两种情况：后面的代码先于前面的代码开始执行；前面的代码先开始执行，但当效率较慢的时候，后面的代码开始执行并先于前面的代码执行结束。
    不管谁先开始，总之后面的代码在一些情况下存在先结束的可能。
    在硬件层面，CPU会接收到的一些指令按照其规则重新排序，同样是基于CPU速度比缓存速度块的原因，和上面的一点的目的类似。只是虚拟机可以在更大层面更多指令范围内重新排序。

    数据依赖：如果两个操作访问同一个变量，且这两个操作中有一个为写操作，此时这两个操作之间就存在数据依赖。数据依赖分为以下三种类型：
        1：写后读
        2：写后写
        3：读后写
    以上三种情况，只要重新排序两个操作的执行顺序，程序的执行结果将会被改变，所以，编译器和处理器在重新排序时，会遵守数据依赖性，
    编译器和处理器不会改变数据依赖关系的两个操作的执行顺序。
        */
        //指令重排: 代码执行顺序与预期不一致


        /*for (int i = 0; i < 10; i++) {
            a = 0;
            flag = false;

            //线程1 更改数据
            Thread t1 = new Thread(() -> {
                a = 1;
                flag = true;
            });
            //线程2 读取数据
            Thread t2 = new Thread(() -> {
                if (flag) {
                    a *= 1;
                }
                //指令重排
                if (a == 0) {
                    System.out.println("happen before a->" + a);
                }
            });

            t1.start();
            t2.start();
            System.out.println(111);

                //合并线程
                t1.join();
                t2.join();
        }*/


        //volitale
    /*volitale保证线程之间的变量的可见行，简单滴说就是当线程A对变量X进行了修改后，在线程A后面执行的其他线程能看到变量X的变动
    更详细的说是要符合以下两个规则：
    线程对变量进行修改之后，要立刻会写到主内存
    线程对变量读取的时候，要从主内存中读，而不是缓存
    Java线程-----工作内存-----save和load操作----主内存
    各个线程的工作内存之间彼此独立、互不可见，在线程启动的时候，虚拟机为每个内存分配一块工作内存，不仅
    包含了线程内部定义的局部变量，也包含了线程所需要使用的共享变量的副本（非线程内构造的对象）
    即为了提高执行效率。
    volitale是不错的机制，但是volitale不能保证原子性
    */
        //volatile用于保证数据的同步，也就是可见性
        new Thread(() -> {
            while (num == 0) { //此处不要编写代码

            }
        }).start();

        Thread.sleep(1000);
        num = 1;
        //不加volatile ，不会停死循环。加了以后就会1秒后停止了循环。
        //保证数据的同步，可见性。


        //scl单例模式
        //装饰模式、静态代理、单例模式
        /**
         * DCL单例模式: 懒汉式套路基础上加入并发控制，保证在多线程环境下，对外存在一个对象
         * 1、构造器私有化 -->避免外部new构造器
         * 2、提供私有的静态属性 -->存储对象的地址
         * 3、提供公共的静态方法 --> 获取属性
         */
        Thread t = new Thread(()->{
            System.out.println(DoubleCheckedLocking.getInstance1(100));
        }) ;
        t.start();
        System.out.println(DoubleCheckedLocking.getInstance1(1000));






        //ThreadLocal
        /*在多线程环境下，每个线程都有自己饿数据。一个线程使用自己的局部变量比使用全局变量好，因为局部变量只有线程自己能看见，不会影响其他线程。
        ThreadLocal能够放一个线程级别的变量，其本身能够被多个线程共享使用，并且又能够达到线程安全的目的。
        说白了，ThreadLocal就是想在多线程环境下去保证成员变量的安全，常用的方法，就是get/set/initiaValue方法
                JDK建议ThreadLocal定义为private static
        ThreadLocal最常用的地方就是为每个线程绑定一个数据库连接，HTTP请求，用户身份信息等，这样一个线程的所有调用到的方法都可以非常方便的访问这些资源
                Hibernate的Session工具类HibernateUtil
                通过不同的线程对象设置Bean属性，保证各个线程Bean对象的独立性

                * ThreadLocal:每个线程自身的存储本地、局部区域
 *  get/set/initialValue
            */





        //可重入锁
        /*锁作为并发共享数据保证一致性的工具，大多数内置锁都是可以重入的，也就是说，如果每个线程试图获取一个已经由它自己持有的锁时，那么这个请求会立刻成功，
        并且会将这个锁的计数值加1，而当线程退出同步代码块时，计数器会递减，当计数值等于0时，锁释放。
        如果没有可重入锁的支持，在第二次企图获得锁时会进入死锁状态。可重入锁随处可见。
        */
        //可重入锁: 锁可以延续使用
        //new LockTest01().test();

        //不可重入锁: 锁不可以延续使用
        /*TestThread4 test = new TestThread4();
        test.a();
        test.doSomething();*/

        //可重入锁: 锁可以延续使用 + 计数器

        //CAS原子操作
        /*锁分为两种：
        悲观锁：synchronized是独占锁即悲观锁，会导致其他所有需要锁的线程挂起，等待持有锁的线程释放锁
        乐观锁：每次不加锁而是假设没有冲突而去完成某项操作，如果因为冲突失败就重试，直到成功为止
        比较并交换：
        乐观锁的实现：
        有三个值：一个当前内存值V、旧的预期值A、将更新的值B。先获取到内存当中当前的内存值V，
        再将内存值V和原值A进行比较，要是相等就修改为要修改的值B并返回true，否则什么都不做，() -> 并返回false
                CAS是一组原子操作，不会被外部打断；
        属于硬件级别的操作（利用CPU的CAS指令，同时借助JNI来完成的非阻塞算法），效率比加锁操作高。

        ABA问题：如果变量V初次读取的时候是A，并且在准备赋值的时候检查到它仍然是A，那能说明它的值没有被其他线程修改过了么？
        如果在这段期间曾经被改成B，然后又改回A，那么CAS操作就会误认为它从来没有被修改过。
        */
        for(int i=0;i<5;i++) {
            new Thread(()->{
                //模拟网络延时
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Integer left = stock.decrementAndGet();
                if(left<1) {
                    System.out.println("抢完了...");
                    return ;
                }
                System.out.println(Thread.currentThread().getName()+"抢了一件商品-->还剩"+left);
                //System.out.println("-->还剩"+left);
            }) .start();
        }



    }



}


class DoubleCheckedLocking {
    //2、提供私有的静态属性
    //没有volatile其他线程可能访问一个没有初始化的对象,保证同步更新。
    private static volatile DoubleCheckedLocking instance;

    //1、构造器私有化
    private DoubleCheckedLocking() {
    }

    //3、提供公共的静态方法 --> 获取属性
    public static DoubleCheckedLocking getInstance() {
        //再次检测
        if (null != instance) { //避免不必要的同步 ，已经存在对象
            return instance;
        }
        synchronized (DoubleCheckedLocking.class) {//避免创建两个对象，所以这里同步，锁定这个class
            if (null == instance) {
                instance = new DoubleCheckedLocking();
                //1、开辟空间 //2、初始化对象信息 //3、返回对象的地址给引用
            }
        }
        return instance;
    }

    public static DoubleCheckedLocking getInstance1(long time) {
        if (null == instance) {
            try {
                Thread.sleep(time);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            instance = new DoubleCheckedLocking();
            //1、开辟空间 //2、初始化对象信息 //3、返回对象的地址给引用
        }
        return instance;
    }
}

