package com.zcr.exercisetest.httpserverproject;

import java.lang.reflect.InvocationTargetException;

/**
 * 反射: 把java类中的各种结构(方法、属性、构造器、类名)映射成一个个的Java对象。
 * 1、获取Class对象
 *三种方式: Class.forName("完整路径")
 * 2、可以动态创建对象
 * clz.getConstructor().newInstance()
 */
public class TestReflect {

    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
        //反射
        /*反射Reflection：把Java类中的各种结构（方法、属性、构造器、类名）映射成一个个的Java对象。
        利用反射技术可以对一个类进行解剖，反射是框架设计的灵魂。

        在运行期间，一个类只有一个class对象产生
        1.源头：获取class对象 Class clz = Class.forName("com.shsxt.Student")
        2.创建对象：com.shsxt.Student stu = (com.shsxt.Student)clz.newInstance();
        */
        //三种方式
        //1、对象.getClass()
        Iphone iphone =new Iphone();
        Class clz = iphone.getClass();
        //2、类.class()
        clz = Iphone.class;
        //3、Class.forName("包名.类名")
        clz = Class.forName("com.zcr.exercisetest.httpserverproject.Iphone");

        //创建对象
		/*Iphone iphone2 =(Iphone)clz.newInstance();//不推荐这种方式
		System.out.println(iphone2);*/
        Iphone iphone2 =(Iphone)clz.getConstructor().newInstance();//推荐用构造器去
        System.out.println(iphone2);
    }

}

class Iphone{
    public Iphone() {

    }
}


