package com.zcr.exercisetest;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestException {
    public static void  main(String args[]){
        //异常
        /*在没有异常机制的情况下，我们需要考虑各种异常情况
        1.逻辑代码和错误处理代码放一起!
        2.程序员本身需要考虑的例外情况较复杂，对程序员本身要求较高!
        异常机制本质：就是当程序出现错误，程序安全退出的机制。
        异常指程序运行过程中出现的非正常现象，例如用户输入错误、除数为零、需要处理的文件不存在、数组下标越界等。
        在Java的异常处理机制中，引进了很多用来描述和处理异常的类，称为异常类。异常类定义中包含了该类异常的信息和对异常进行处理的方法。
        所谓异常处理，就是指程序在出现问题时依然可以正确的执行完。

        Java是采用面向对象的方式来处理异常的。处理过程：
        1.抛出异常：在执行一个方法时，如果发生异常，则这个方法生成代表该异常的一个对象，停止当前执行路径，并把异常对象提交给JRE。
        2. 捕获异常：JRE得到该异常后，寻找相应的代码来处理该异常。JRE在方法的调用栈中查找，从生成异常的方法开始回溯，直到找到相应的异常处理代码为止。

        异常分类
        JDK 中定义了很多异常类，这些类对应了各种各样可能出现的异常事件，所有异常对象都是派生于Throwable类的一个实例。
        如果内置的异常类不能够满足需要，还可以创建自己的异常类。
        Java对异常进行了分类，不同类型的异常分别用不同的Java类表示，所有异常的根类为java.lang.Throwable，
        Throwable下面又派生了两个子类：Error和Exception。
        Error-Unchecked Exception
        Exception-Checked Exception和RuntimeException-UncheckedException

        Error
        Error是程序无法处理的错误，表示运行应用程序中较严重问题。大多数错误与代码编写者执行的操作无关，而表示代码运行时 JVM(Java 虚拟机)出现的问题。
        例如，Java虚拟机运行错误(Virtual MachineError)，当 JVM 不再有继续执行操作所需的内存资源时，将出现 OutOfMemoryError。
        这些异常发生时，Java虚拟机(JVM)一般会选择线程终止。
        Error表明系统JVM已经处于不可恢复的崩溃状态中。我们不需要管它。

        Exception
        Exception是程序本身能够处理的异常，如：空指针异常(NullPointerException)、数组下标越界异常(ArrayIndexOutOfBoundsException)、
        类型转换异常(ClassCastException)、算术异常(ArithmeticException)等。
        Exception类是所有异常类的父类，其子类对应了各种各样可能出现的异常事件。 通常Java的异常可分为：
        1.RuntimeException 运行时异常
        2.CheckedException 已检查异常

        RuntimeException运行时异常
        派生于RuntimeException的异常，如被 0 除、数组下标越界、空指针等，其产生比较频繁，处理麻烦，如果显式的声明或捕获将会对程序可读性和运行效率影响很大。
        因此由系统自动检测并将它们交给缺省的异常处理程序(用户可不必对其处理)。
        这类异常通常是由编程错误导致的，所以在编写程序时，并不要求必须使用异常处理机制来处理这类异常,经常需要通过增加“逻辑处理来避免这些异常”。
        注意事项
        1.在方法抛出异常之后，运行时系统将转为寻找合适的异常处理器(exception handler)。潜在的异常处理器是异常发生时依次存留在调用栈中的方法的集合。
        当异常处理器所能处理的异常类型与方法抛出的异常类型相符时，即为合适的异常处理器。
        2.运行时系统从发生异常的方法开始，依次回查调用栈中的方法，直至找到含有合适异常处理器的方法并执行。
        当运行时系统遍历调用栈而未找到合适的异常处理器，则运行时系统终止。同时，意味着Java程序的终止。
        */
        int b=0;
        if(b!=0){//ArithmeticException异常：试图除以0
            System.out.println(1/b);
        }

        String str=null;//当程序访问一个空对象的成员变量或方法，或者访问一个空数组的成员时会发生空指针异常(NullPointerException)
        if(str!=null){//NullPointerException异常：解决空指针异常，通常是增加非空判断
            System.out.println(str.charAt(0));
        }

        Animal a = new Dog();
        if (a instanceof Cat) {//在引用数据类型转换时，有可能发生类型转换异常(ClassCastException)。
            Cat c = (Cat) a;
        }

        int[] arr = new int[5];
        int a1 = 5;
        if (a1 < arr.length) { //当程序访问一个数组的某个元素时，如果这个元素的索引超出了0~数组长度-1这个范围，
            System.out.println(arr[a1]);
        }// 则会出现数组下标越界异常(ArrayIndexOutOfBoundsException)。

        String str1 = "1234abcf";
        Pattern p = Pattern.compile("^\\d+$");
        Matcher m = p.matcher(str1);
        if (m.matches()) { // 如果str匹配代表数字的正则表达式,才会转换
            System.out.println(Integer.parseInt(str));
            //NumberFormatException异常
            //在使用包装类将字符串转换成基本数据类型时，如果字符串的格式不正确，则会出现数字格式异常(NumberFormatException)。
            //数字格式化异常的解决，可以引入正则表达式判断是否为数字
        }

        //CheckedException已检查异常
        /*所有不是RuntimeException的异常，统称为Checked Exception，又被称为“已检查异常”，如IOException、SQLException等以及用户自定义的
        Exception异常。 这类异常**在编译时就必须做出处理，否则无法通过编译。
        异常的处理方式有两种：使用“try/catch”捕获异常、使用“throws”声明异常。
        Add throws declaration 和 Surround with try/catch
        */

        //异常的处理方式之一：捕获异常
        /*捕获异常是通过3个关键词来实现的：try-catch-finally。用try来执行一段程序，如果出现异常，系统抛出一个异常，
        可以通过它的类型来捕捉(catch)并处理它，最后一步是通过finally语句为异常处理提供一个统一的出口，
        finally所指定的代码都要被执行(catch语句可有多条;finally语句最多只能有一条，根据自己的需要可有可无)。

        上面过程详细解析：
        1.try：
        try语句指定了一段代码，该段代码就是异常捕获并处理的范围。在执行过程中，当任意一条语句产生异常时，就会跳过该条语句中后面的代码。
        代码中可能会产生并抛出一种或几种类型的异常对象，它后面的catch语句要分别对这些异常做相应的处理。
        一个try语句必须带有至少一个catch语句块或一个finally语句块 。
        注意事项：当异常处理的代码执行结束以后，不会回到try语句去执行尚未执行的代码。
        2.catch：
        n-每个try语句块可以伴随一个或多个catch语句，用于处理可能产生的不同类型的异常对象。
        n-常用方法，这些方法均继承自Throwable类 。
        u-toString ()方法，显示异常的类名和产生异常的原因
        u-getMessage()方法，只显示产生异常的原因，但不显示类名。
        u-printStackTrace()方法，用来跟踪异常事件发生时堆栈的内容。
        n-catch捕获异常时的捕获顺序：
        u-如果异常类之间有继承关系，在顺序安排上需注意。越是顶层的类，越放在下面，再不然就直接把多余的catch省略掉。 也就是先捕获子类异常再捕获父类异常。
        3.finally：
        n-有些语句，不管是否发生了异常，都必须要执行，那么就可以把这样的语句放到finally语句块中。
        n-通常在finally中关闭程序块已打开的资源，比如：关闭文件流、释放数据库连接等。

        try-catch-finally语句块的执行过程：
        try-catch-finally程序块的执行流程以及执行结果比较复杂。
        基本执行过程如下：
        程序首先执行可能发生异常的try语句块。如果try语句没有出现异常则执行完后跳至finally语句块执行;
        如果try语句出现异常，则中断执行并根据发生的异常类型跳至相应的catch语句块执行处理。
        catch语句块可以有多个，分别捕获不同类型的异常。catch语句块执行完后程序会继续执行finally语句块。
        finally语句是可选的，如果有的话，则不管是否发生异常，finally语句都会被执行。

        注意事项
        1.即使try和catch块中存在return语句，finally语句也会执行。是在执行完finally语句后再通过return退出。
        2.finally语句块只有一种情况是不会执行的，那就是在执行finally之前遇到了System.exit(0)结束程序运行。
        */
        /*FileReader reader = null;
        try {
            reader = new FileReader("d.txt");
            char c = (char) reader.read();
            char c2 = (char) reader.read();
            System.out.println("" + c + c2);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }*/

        //异常的处理方式之二：声明异常(throws子句)
        /*当CheckedException产生时，不一定立刻处理它，可以再把异常throws出去。
        在方法中使用try-catch-finally是由这个方法来处理异常。但是在一些情况下，当前方法并不需要处理发生的异常，而是向上传递给调用它的方法处理。
        如果一个方法中可能产生某种异常，但是并不能确定如何处理这种异常，则应根据异常规范在方法的首部声明该方法可能抛出的异常。
        如果一个方法抛出多个已检查异常，就必须在方法的首部列出所有的异常，之间以逗号隔开。
        注意事项
        1.方法重写中声明异常原则：子类重写父类方法时，如果父类方法有声明异常，那么子类声明的异常范围不能超过父类声明的范围。
        */
        /*try {
            readFile("joke.txt");
        } catch (FileNotFoundException e) {
            System.out.println("所需文件不存在！");
        } catch (IOException e) {
            System.out.println("文件读写错误！");
        }*/

        //自定义异常
        /*1.在程序中，可能会遇到JDK提供的任何标准异常类都无法充分描述清楚我们想要表达的问题，这种情况下可以创建自己的异常类，即自定义异常类。
        2.自定义异常类只需从Exception类或者它的子类派生一个子类即可。
        3.自定义异常类如果继承Exception类，则为受检查异常，必须对其进行处理;
        如果不想处理，可以让自定义异常类继承运行时异常RuntimeException类。
        4.习惯上，自定义异常类应该包含2个构造器：一个是默认的构造器，另一个是带有详细信息的构造器。*/
        Person p1 = new Person();
        try {
            p1.setName("Lincoln");
            p1.setAge(-1);
        } catch (IllegalAgeException e) {
            e.printStackTrace();
            System.exit(-1);
        }
        System.out.println(p1);

        //使用异常机制的建议
        /*1.要避免使用异常处理代替错误处理，这样会降低程序的清晰性，并且效率低下。
        2.处理异常不可以代替简单测试—只在异常情况下使用异常机制。
        3.不要进行小粒度的异常处理—应该将整个任务包装在一个try语句块中。
        4.异常往往在高层处理(先了解!后面做项目会说!) 。*/

        //如何利用百度解决异常问题
        /*正常学习和开发中，我们经常会遇到各种异常。大家在遇到异常时，需要遵循下面四步来解决：
        1.细心查看异常信息，确定异常种类和相关Java代码行号;
        2.拷贝异常信息到百度，查看相关帖子，寻找解决思路;
        3.前两步无法搞定，再问同学或同事;
        4.前三步无法搞定，请示领导。*/



    }
    public static void readFile(String fileName) throws FileNotFoundException, IOException {
        FileReader in = new FileReader(fileName);
        int tem = 0;
        try {
            tem = in.read();
            while (tem != -1) {
                System.out.print((char) tem);
                tem = in.read();
            }
        } finally {
            in.close();
        }
    }
}
class Animal{

}
class Dog extends Animal{

}
class Cat extends Animal{

}

//自定义异常类
/**IllegalAgeException：非法年龄异常，继承Exception类*/
class IllegalAgeException extends Exception {
    //默认构造器
    public IllegalAgeException() {

    }
    //带有详细信息的构造器，信息存储在message中
    public IllegalAgeException(String message) {
        super(message);
    }
}

class Person {
    private String name;
    private int age;

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) throws IllegalAgeException {
        if (age < 0) {
            throw new IllegalAgeException("人的年龄不应该为负数");
        }
        this.age = age;
    }

    public String toString() {
        return "name is " + name + " and age is " + age;
    }
}




