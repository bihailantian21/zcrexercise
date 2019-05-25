package com.zcr.exercisetest;
//包
/*java.lang java.awt java.net java.io java.util
1.通常是类的第一句非注释性语句。
2.包名：域名倒着写即可，再加上模块名，便于内部管理类。
1.写项目时都要加包，不要使用默认包。
2.com.gao和com.gao.car，这两个包没有包含关系，是两个完全独立的包。只是逻辑上看起来后者是前者的一部分。
1.Java会默认导入java.lang包下所有的类，因此这些类我们可以直接使用。
2.如果导入两个同名的类，只能用包名+类名来显示调用相关类：java.util.Date date = new java.util.Date();
*/
//静态导入(static import)是在JDK1.5新增加的功能，其作用是用于导入指定类的静态属性，这样我们可以直接使用静态属性。
/*import static java.lang.Math.*;
import static java.lang.Math.PI;//导入Math类的PI属性*/

import com.zcr.exercisefundation.Car;
import com.zcr.exercisefundation.ElectricCar;
import com.zcr.exercisefundation.Engine;
import com.zcr.exercisefundation.Student;

/**
 * @author zcr
 * @date 2019/5/6-21:28
 */
public class TestClass {
    public static void  main(String args[]){
        //对象传递的时候传递的是地址的拷贝，但是也是有可能改变以前对象的值的
        Student stu1 = new Student(201401,"高一一");//堆中产生stu1（201401,"高一一"），地址是123，即stu1=123
        System.out.println("我的名字是："+stu1.getName());
        stu1.testParameterTransfer01(stu1);//把stu1的地址传递给了u，即u=123这个地址对应的堆里面发生了变化（201401,"高二二"），u是局部变量，执行完之后就没有了
        System.out.println("我的名字是："+stu1.getName());
        /*java是值传递，是拷贝值的内容。
        但是呢，传对象的时候，因为它们经常会指向同一个对象，多个变量的地址都是一样的，所以通过一个变量改变了这个对象的值，
        那么原来呢这个地方的对象的值也会被改变。*/
        stu1.testParameterTransfer02(stu1);//把stu1的地址传递给了u，即u=123。然后又新建了一个对象，新对象地址是124，把新对象的地址给了u，即u=124。
        //（201401,"高三三"）。但只是改变了124地址上的对象，而没有改变123地址上的对象。所以，此时打印stu1的姓名是没有变化的。
        System.out.println("我的名字是："+stu1.getName());
        //参数传值机制
        /*
        Java中，方法中所有参数都是**“值传递”**，也就是“传递的是值的副本”。 也就是说，
        我们得到的是“原参数的复印件，而不是原件”。因此，复印件改变不会影响原件。
        你没有把原件传给了它，而是把复印件传递给了它，所以对复印件的改变不会影响原件。
        我的文件的地址是123，复印件的地址一样的123，所以对此地址上的东西的改变都有影响。
        · 基本数据类型参数的传值
        传递的是值的副本。 副本改变不会影响原件。
        · 引用类型参数的传值
        传递的是值的副本。但是引用类型指的是“对象的地址”。因此，副本和原参数都指向了同一个“地址”，
        改变“副本指向地址对象的值，也意味着原参数指向对象的值也发生了改变”。*/


        Car car1 = new Car();
        System.out.println(car1);
        Car car2 = new Car("Audi","A8",2018,0);
        System.out.println(car2);

        Engine e1 = new Engine();
        e1.setBrand("迈斯特");

        car2.setEngine(e1);
        car2.zuzhuang();


        ElectricCar ecar1 = new ElectricCar(23.23);
        System.out.println(ecar1);
        System.out.println(ecar1.getOdometer_reading());//通过super()传过来的

        ElectricCar ecar2 = new ElectricCar("Tesla","model X",2019,10,230.89);
        System.out.println(ecar2);
        ecar2.Dri();

        Car car3 = new ElectricCar(2333);
        System.out.println(((ElectricCar) car3).getBattery_size());
        car3.propertity();
        if (car3 instanceof Car) {//instanceof是二元运算符，左边是对象，右边是类；当对象是右面类或子类所创建对象时，返回true；否则，返回false。
            System.out.println("我是汽车");

        }
        if (car3 instanceof ElectricCar) {
            System.out.println("我是电动汽车");

        }

        System.out.println(Math.PI);

        try {
            int a = 10/0;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            System.out.println(e.toString());
        } finally {
            System.out.println(Math.PI);
        }


        //==和equals方法
        /*“==”代表比较双方是否相同。
        如果是基本类型则表示值相等，如果是引用类型则表示地址相等即是同一个对象。

        Object类中定义有：public boolean equals(Object obj)方法，提供定义“对象内容相等”的逻辑。
        比如，我们在公安系统中认为id相同的人就是同一个人、学籍系统中认为学号相同的人就是同一个人。
        Object 的 equals 方法默认就是比较两个对象的hashcode，是同一个对象的引用时返回 true 否则返回 false。
        但是，我们可以根据我们自己的要求重写equals方法。*/
        Car car11 = new Car();
        Car car22 = new Car();
        System.out.println(car11==car22);//false地址
        System.out.println(car11.equals(car22));//true内容
        Student p1 = new Student(123,"www");
        Student p2 = new Student(123,"www");
        System.out.println(p1 == p2);//false地址
        System.out.println(p1.equals(p2));//true内容

        /*String equals源码
        public boolean equals(Object anObject) {
            if (this == anObject) {
                return true;
            }
            if (anObject instanceof String) {
                String anotherString = (String)anObject;
                int n = value.length;
                if (n == anotherString.value.length) {
                    char v1[] = value;
                    char v2[] = anotherString.value;
                    int i = 0;
                    while (n-- != 0) {
                        if (v1[i] != v2[i])
                            return false;
                        i++;
                    }
                    return true;
                }
            }
            return false;
        }*/

    }
}
