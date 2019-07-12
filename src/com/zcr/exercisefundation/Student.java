package com.zcr.exercisefundation;

import com.zcr.exercisetest.TestAnnotation;

import java.util.Objects;

/**
 * @author zcr
 * @date 2019/5/6-20:35
 */
@TestAnnotation.ZTable("tb_student")
public class Student implements Comparable<Student>{
    //对于一个类来说，一般有三种常见的成员：属性field、方法method、构造器constructor。这三种成员都可以定义零个或多个。
    //属性
    /*属性用于定义该类或该类对象包含的数据或者说静态特征。属性作用范围是整个类体。
    在定义成员变量时可以对其初始化，如果不对其初始化，Java使用默认的值对其初始化。
    [修饰符] 属性类型 属性名 = [默认值] ;*/

    @TestAnnotation.ZField(columnName = "id",type = "int",length = 10)
    private int id;
    @TestAnnotation.ZField(columnName = "sname",type = "varchar",length = 10)
    private String name;
    @TestAnnotation.ZField(columnName = "age",type = "int",length = 3)
    private int age;

    //静态成员变量（类变量）
    /*在类中，用static声明的成员变量为静态成员变量，也称为类变量。 类变量的生命周期和类相同，在整个应用程序执行期间都有效。它有如下特点：
        1.为该类的公用变量，属于类，被该类的所有实例共享，在类被载入时被显式初始化。
        2.对于该类的所有对象来说，static成员变量只有一份。被该类的所有对象共享!!
        3.一般用“类名.类属性/方法”来调用。(也可以通过对象引用或类名(不需要实例化)访问静态成员。)
        4.在static方法中不可直接访问非static的成员。*/
    static String school = "清华大学";

    //静态方法
    public static void printSchool() {
        //login;静态方法中调用非静态成员，编译就会报错.方法区中的静态方法可以去调用堆中的普通方法吗？不能，因为我们类中的静态方法找不到堆中的对象的方法
        System.out.println("我的学校是：" + school);
    }

    //静态初始化块
    /*构造方法用于对象的初始化!静态初始化块，用于类的初始化操作!
    在静态初始化块中不能直接访问非static成员。
    注意事项：
    静态初始化块执行顺序(学完继承再看这里)：
            1.上溯到Object类，先执行Object的静态初始化块，再向下执行子类的静态初始化块，直到我们的类的静态初始化块为止。
            2.构造方法执行顺序和上面顺序一样!!*/
    static {
        //这个静态块在类初始的时候进行执行，做类的初始化
        //这个静态块中不能调用非静态的变量和方法，因为这只是画图纸，还没有对象
        System.out.println("执行类的初始化工作");
        school = "北京大学";
        printSchool();
    }

    //构造方法
    /*用于对象的初始化。构造器是一个创建对象时被自动调用的特殊方法，目的是对象的初始化。
    构造器的名称应与类的名称一致。Java通过new关键字来调用构造器，从而返回该类的实例，是一种特殊的方法。
    声明格式：　
    [修饰符] 类名(形参列表){
        //n条语句
    }
    要点：
        1.通过new关键字调用!!
        2.构造器虽然有返回值，但是不能定义返回值类型(返回值的类型肯定是本类)，不能在构造器里使用return返回某个值。
        3.如果我们没有定义构造器，则编译器会自动定义一个无参的构造函数。如果已定义则编译器不会自动添加!
        4.构造器的方法名必须和类名一致!*/
    public Student() {
        super();//所有的构造方法的第一句总是super()，你删了编译器会帮你自动调用super()
        // TODO Auto-generated constructor stub
    }

    //构造方法的重载 （构造方法经常需要重载，因为我们经常需要对对象采用不同的创建方式。）
    /*在方法内部，你直接写个id的时候，用就近原则，即什么都不写直接写id时，指的就是局部变量id。而不是我的成员变量的id。
    如果方法构造中形参名与属性名相同时，需要使用this关键字区分属性与形参。
    this表示创建好的对象，这里表示对象的成员变量，即属性
    this.id 表示属性id;id表示形参id*/
    public Student(int id,String name) {
        this();// 调用无参的构造方法，并且必须位于第一行！
        this.id = id;
        this.name = name;
    }

    public Student(int id, String name, int age) {
        this(id,name);//调用带参的构造方法，并且必须位于第一行！那么这样以后，下面两行就不需要写了。
        /*this.id = id;
        this.name = name;*/
        this.age = age;//不写this，无法区分局部变量id和成员变量id
    }

    /*this用于普通的方法和构造器，它用来指代当前对象
    ·对象创建的过程和this的本质
    构造方法是创建Java对象的重要途径，通过new关键字调用构造器时，构造器也确实返回该类的对象，但这个对象并不是完全由构造器负责创建。创建一个对象分为如下四步：
        1.分配对象空间，并将对象成员变量初始化为0或空
        2.执行属性值的显式初始化
        3.执行构造方法（构造的时候对象已经建好了，这里构造只是进行更进一步的初始化工作，所以构造去哦器中也可以用this指代当前对象）
        4.返回对象的地址给相关的变量
    this的本质就是“创建好的对象的地址”! 由于在构造方法调用前，对象已经创建。因此，在构造方法中也可以使用this代表“当前对象” 。

    this最常的用法：
    1.在程序中产生二义性之处，应使用this来指明当前对象;
    普通方法中，this总是指向调用该方法的对象。
        void eat() {
            this.sing(); // 调用本类中的sing();这里的this可写可不写
            System.out.println(“你妈妈喊你回家吃饭！”);
        }
    构造方法中，this总是指向正要初始化的对象。
        TestThis(int a, int b) {
            // TestThis(); //这样是无法调用构造方法的！
            this(); // 调用无参的构造方法，并且必须位于第一行！
            a = a;// 这里都是指的局部变量而不是成员变量
            // 这样就区分了成员变量和局部变量. 这种情况占了this使用情况大多数！
            this.a = a;
            this.b = b;
        }
    2.让类中的一个方法，访问该类的另一个方法或属性。
    3.使用this关键字调用重载的构造方法，避免相同的初始化代码。但只能在构造方法中用，并且必须位于构造方法的第一句。
        TestThis(int a, int b, int c) {
            this(a, b); // 调用带参的构造方法，并且必须位于第一行！因为我们之前已经写过了，所以我们直接去调用，就是通过this调用
            this.c = c;
        }
    3.this不能用于static方法中。（因为this是指代当前对象，而static是在方法区里面，是类的信息）*/
    public void login() {
        printSchool();//普通方法中调用静态方法
        System.out.println(school);//普通方法中调用静态变量
        System.out.println(this.name + "，要登陆！");//不写this效果一样
    }

    void sing() {
        System.out.println("我在唱歌！");
    }

    void eat() {
        this.sing();//// 调用本类中的sing();
        sing();//这里的this可以不写
        System.out.println("你妈妈喊你回家吃饭！");
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return id == student.id &&
                age == student.age &&
                Objects.equals(name, student.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, age);
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }


    @Override
    public int compareTo(Student s) {
        //return -1; //-1表示放在红黑树的左边,即逆序输出
        //return 1;  //1表示放在红黑树的右边，即顺序输出
        //return 0;  //表示元素相同，仅存放第一个元素

        //主要条件 姓名的长度,如果姓名长度小的就放在左子树，否则放在右子树
        int num=this.name.length()-s.name.length();//姓名短的在前，长的在后

        //姓名的长度相同，不代表内容相同,如果按字典顺序此 String 对象位于参数字符串之前，则比较结果为一个负整数。
        //如果按字典顺序此 String 对象位于参数字符串之后，则比较结果为一个正整数。
        //如果这两个字符串相等，则结果为 0
        int num1=num==0?this.name.compareTo(s.name):num;//姓名字母小的在前，字母大的在后

        //姓名的长度和内容相同，不代表年龄相同，所以还要判断年龄
        int num2=num1==0?this.age-s.age:num1;//年龄小的在前，大的在后
        return num2;
    }

    //方法
    /*方法用于定义该类或该类实例的行为特征和功能实现。方法是类和对象行为特征的抽象。
    方法定义格式：
    [修饰符] 方法返回值类型 方法名(形参列表) {
     n条语句
    }*/
    void study(){
        System.out.println("我正在学习！");
    }

    public   void   testParameterTransfer01(Student  u){
        u.name="高二二";
    }

    public   void   testParameterTransfer02(Student  u){
        u  =  new  Student(200,"高三三");
    }

    //面向对象的内存分析
    /*Java虚拟机的内存可以分为三个区域：栈stack、堆heap、方法区method area。
        栈的特点如下：comp1,stu1
            1.栈描述的是方法执行的内存模型。每个方法被调用都会创建一个栈帧(存储局部变量、操作数、方法出口等)
            2.JVM为每个线程创建一个栈，用于存放该线程执行方法的信息(实际参数、局部变量等)
            3.栈属于线程私有，不能实现线程间的共享!
            4.栈的存储特性是“先进后出，后进先出”
            5.栈是由系统自动分配，速度快!栈是一个连续的内存空间!
        堆的特点如下：SxtStu(id:0 sname:-> age:0 comp:->)Computer(brand:->)
            1.堆用于存储创建好的对象和数组(数组也是对象)
            2.JVM只有一个堆，被所有线程共享
            3.堆是一个不连续的内存空间，分配灵活，速度慢!
        方法区(又叫静态区)特点如下：SxtStu类代码 Computer类代码 "张三" "联想"
            1.JVM只有一个方法区，被所有线程共享!
            2.方法区实际也是堆，只是用于存储类、常量相关的信息!
            3.用来存放程序中永远是不变或唯一的内容。(类信息【Class对象】、静态变量、字符串常量等)
        java命令启动程序，把代码加载到空间里。
            1首先方法区有了我们类的信息，SxtStu 类相关信息：1代码2静态变量3静态方法4字符串常量：“我正在学习！使用我们的电脑，” “我在玩游戏！王者农药！” “张三” “联想”。
            2然后找main函数，调用main方法时在栈中开辟一个main方法栈帧。首先定义了一个stu1局部变量，stu1=null。
            3然后new SxtStu();调new的时候就需要建一个对象，调SxtStu()的时候就去调用了类的构造方法SxtStu()，又开辟了一个新的栈帧，开辟完以后，就开始执行这个方法去创建一个这个类的实例对。
            4因为内存的方法区中已经有这个类的信息，所以我们在堆里建出一个对象了，这个对象有属性有方法,
            id 0
            sname null
            age 0
            comp null
            study()
            play()
            5建好的每个对象都有一个地址，这个内存块都有一个地址，在堆里面有很多个这样的内存块，地址一般是一个数字表示。例：在这里插入图片描述这串数字就是地址。
            SxtStu stu1 = new SxtStu();这样就把这个值赋值给了stu1对象
            stu1 = 15db9742，一个简单的赋值就把它们俩给关联起来了。
            所以，当我写个stu1时，它就明白我指的是这个15db9742的地方。
            6stu1.sname = “张三”;找到15db9742，
            id 0
            sname “张三“ 去方法区中找到字符串，把字符串的地址告诉属性sname
            age 0
            comp null
            study()
            play()
            7Computer comp1
            新建局部变量comp1 ，也在main方法中建一个局部变量comp1=null。
            8= new Computer();
            调用Computer()构造器，开辟一个新的栈帧，然后就会在堆中创建新的对象
            brand null
            9堆中的对象一样有个地址15db9753，把地址给了comp1，把它们俩关联起来了。
            10comp1.brand = “联想”;把字符串常量对象给了brand属性
            brand 联想
            11stu1.comp = comp1;
            comp 15db9753
            12stu1.study();*/

    //垃圾回收原理和算法
    /*·内存管理
            Java的内存管理很大程度指的就是对象的管理，其中包括对象空间的分配和释放。
            对象空间的分配：使用new关键字创建对象即可
            对象空间的释放：将对象赋值null即可。垃圾回收器将负责回收所有”不可达”对象的内存空间。
    ·垃圾回收过程
            任何一种垃圾回收算法一般要做两件基本事情：
            1.发现无用的对象，怎么发现他是个关键问题
            2.回收无用对象占用的内存空间。
            垃圾回收机制保证可以将“无用的对象”进行回收。无用的对象指的就是没有任何变量引用该对象。Java的垃圾回收器通过相关算法发现无用对象，并进行清除和整理。
    ·垃圾回收相关算法
            1.引用计数法
            堆中每个对象都有一个引用计数。
            被引用一次，计数加1.
            被引用变量值变为null，则计数减1，直到计数为0，则表示变成无用对象。
            优点是算法简单，缺点是“循环引用的无用对象”无法别识别。
            2.引用可达法(根搜索算法)
            程序把所有的引用关系看作一张图，从一个节点GC ROOT开始，寻找对应的引用节点，找到这个节点以后，
            继续寻找这个节点的引用节点，当所有的引用节点寻找完毕之后，剩余的节点则被认为是没有被引用到的节点，即无用的节点。
    */

    //通用的分代垃圾回收机制
    /*分代垃圾回收机制，是基于这样一个事实：不同的对象的生命周期是不一样的。因此，不同生命周期的对象可以采取不同的回收算法，以便提高回收效率。我们将对象分为三种状态：年轻代、年老代、持久代。JVM将堆内存划分为 Eden、Survivor 和 Tenured/Old 空间。

            1.年轻代
            所有新生成的对象首先都是放在Eden区。 年轻代的目标就是尽可能快速的收集掉那些生命周期短的对象，对应的是Minor GC，每次 Minor GC 会清理年轻代的内存，算法采用效率较高的复制算法，频繁的操作，但是会浪费内存空间。当“年轻代”区域存放满对象后，就将对象存放到年老代区域。
            2. 年老代
            在年轻代中经历了N(默认15)次垃圾回收后仍然存活的对象，就会被放到年老代中。因此，可以认为年老代中存放的都是一些生命周期较长的对象。年老代对象越来越多，我们就需要启动Major GC和Full GC(全量回收)，来一次大扫除，全面清理年轻代区域和年老代区域。
            3. 持久代、用于存放静态文件，如Java类、方法等。持久代对垃圾回收没有显著影响。
            ·Minor GC:
            用于清理年轻代区域。Eden区满了就会触发一次Minor GC。清理无用对象，将有用对象复制到“Survivor1”、“Survivor2”区中(这两个区，大小空间也相同，同一时刻Survivor1和Survivor2只有一个在用，一个为空)
            ·Major GC：
            用于清理老年代区域。
            ·Full GC：
            用于清理年轻代、年老代区域。 成本较高，会对系统性能产生影响。
    垃圾回收过程：
            1、新创建的对象，绝大多数都会存储在Eden中，
            2、当Eden满了（达到一定比例）不能创建新对象，则触发垃圾回收（GC），将无用对象清理掉，
            然后剩余对象复制到某个Survivor中，如S1，同时清空Eden区
            3、当Eden区再次满了，会将S1中的不能清空的对象存到另外一个Survivor中，如S2，
            同时将Eden区中的不能清空的对象，也复制到S1中，保证Eden和S1，均被清空。
            4、重复多次(默认15次)Survivor中没有被清理的对象，则会复制到老年代Old(Tenured)区中，
            5、当Old区满了，则会触发一个一次完整地垃圾回收（FullGC），之前新生代的垃圾回收称为（minorGC）*/

    //JVM调优和Full GC
    /*在对JVM调优的过程中，很大一部分工作就是对于Full GC的调节。有如下原因可能导致Full GC：
            1.年老代(Tenured)被写满
            2.持久代(Perm)被写满
            3.System.gc()被显式调用（程序建议GC启动，不是调用GC）
            4.上一次GC之后Heap的各域分配策略动态变化        */

    //开发中容易造成内存泄露的操作
    /*· 创建大量无用对象
            比如，我们在需要大量拼接字符串时，使用了String而不是StringBuilder。
            String str = "";
            for (int i = 0; i < 10000; i++) {
                str += i;     //相当于产生了10000个String对象
            }
    · 静态集合类的使用
        像HashMap、Vector、List等的使用最容易出现内存泄露，这些静态变量的生命周期和应用程序一致，所有的对象Object也不能被释放。
    · 各种连接对象(IO流对象、数据库连接对象、网络连接对象)未关闭
        IO流对象、数据库连接对象、网络连接对象等连接对象属于物理连接，和硬盘或者网络连接，不使用的时候一定要关闭。
    · 监听器的使用
        释放对象时，没有删除相应的监听器。
    要点：
        1.程序员无权调用垃圾回收器。
        2.程序员可以调用System.gc()，该方法只是通知JVM，并不是运行垃圾回收器。尽量少用，会申请启动Full GC，成本高，影响系统性能。
        3. finalize方法，是Java提供给程序员用来释放对象或资源的方法，但是尽量少用。*/
}
