package com.zcr.exercisetest.reflection;

import com.zcr.exercisefundation.Student;
import com.zcr.exercisetest.thread.ThreadLocalTest01;

import java.lang.reflect.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zcr
 * @date 2019/7/12-18:13
 */
public class TestReflection {
    /**
     *• 反射机制
     * – 指的是可以于运行时加载、探知、使用编译期间完全未知的类。
     * – 程序在运行状态中，可以动态加载一个只有名称的类，
     * 对于任意一个已加载的类，都能够知道这个类的所有属性和方法；
     * 对于任意一个对象，都能够调用它的任意一个方法和属性；
     * Class c = Class. forName (“com.bjsxt.test.User”);
     *
     * – 加载完类之后，在堆内存中，就产生了一个 Class 类型的对象（一个类只有一个 Class对象），
     * 这个对象就包含了完整的类的结构信息。 我们可以通过这个对象看到类的结构。
     * 这个对象就像一面镜子，透过 这个镜子看到类的结构，所以，我们形象的称之为：反射。
     *
     */

    /**
     * Class类介绍
     * • java.lang.Class类十分特殊，用来表示java中类型 (class/interface/enum/annotation/primitive type/void)本 身。
     * – Class类的对象包含了某个被加载类的结构。一个被加载的类对应一个 Class对象。
     * – 当一个class被加载，或当加载器（class loader）的defineClass()被 JVM调用，JVM 便自动产生一个Class 对象。
     * • Class类是Reflection的根源。 – 针对任何您想动态加载、运行的类，唯有先获得相应的Class 对象
     *
     * Class类的对象如何获取？
     * 方法一：运用getClass()
     * 方法二：运用Class.forName()
     * 方法三：运用.class语法
     *
     */

    /**
     * 反射机制的常见作用
     * • 动态加载类、动态获取类的信息（属性、方法、构造器）
     * • 动态构造对象
     * • 动态调用类和对象的任意方法、构造器
     * • 动态调用和处理属性
     * • 获取泛型信息
     * • 处理注解
     *
     * 应用反射的API，获取类的信息（类的名字、属性、方法、构造器）
     *
     */

    public static void main(String[] args) {
        String path = "com.zcr.exercisefundation.Student";

        try {
            //反射机制
            Class clazz = Class.forName(path);
            System.out.println(clazz);
            System.out.println(clazz.hashCode());

            Class clazz2 = Class.forName(path);
            System.out.println(clazz2);//一个类只有这么一个class对象



            //Class类介绍
            Class strClazz = String.class;
            Class strClazz2 = path.getClass();
            System.out.println(strClazz == strClazz2);

            Class intClazz = int.class;
            int[] arr01 = new int[10];
            int[] arr02 = new int[30];
            System.out.println(arr01.getClass().hashCode());
            System.out.println(arr02.getClass().hashCode());


            //反射机制的常见作用
            //获取类的名字（全路径包名+类名、只是类名）
            System.out.println(clazz.getName());//获得包名+类名
            System.out.println(clazz.getSimpleName());//获得类名
            //获取属性信息
            //返回公共属性
            Field[] fields = clazz.getFields();//只能获得public的属性
            System.out.println(fields.length);
            //获取所有的属性
            Field[] fields1 = clazz.getDeclaredFields();//获得所有的属性
            System.out.println(fields1.length);
            for (Field temp : fields1) {
                System.out.println("属性："+temp);
            }
            //获取指定的属性
            //获取方法信息
            Method[] methods = clazz.getDeclaredMethods();
            Method m01 = clazz.getDeclaredMethod("getName",null);
            Method m02 = clazz.getDeclaredMethod("setName", String.class);
            //为什么有参数的方法要传入参数类型？为了区分重载的方法，重载的方法不能光凭名字进行区分
            for (Method m : methods) {
                System.out.println("方法："+m);
            }
            //获取构造器信息
            Constructor[] constructors = clazz.getDeclaredConstructors();
            Constructor c = clazz.getDeclaredConstructor(null);
            //分别获取无参构造器和有参构造器
            //传递不同的参数类型，获取不同的构造器
            System.out.println("获得构造器："+c);
            for (Constructor constructor : constructors) {
                System.out.println("构造器" + constructor);
            }

            //构造对象
            //其实是调用了无参构造方法
            Class<Student> studentClass = (Class<Student>) Class.forName(path);
            //通过反射API调用构造方法，构造对象
            Student student = studentClass.newInstance();//其实是调用了无参构造方法
            //如果我们把user的无参构造器给删掉，就发现初始化异常，
            // 所以javabean必须要有无参构造方法。很多开源框架中就要大量的使用反射来构造对象。
            System.out.println(student);
            //调用有参的构造器
            Constructor<Student> studentConstructor = clazz.getDeclaredConstructor(int.class,String.class,int.class);
            Student student1 = studentConstructor.newInstance(1001,"小红",18);
            System.out.println(student1.getName());

            //调用普通方法
            Method method = clazz.getDeclaredMethod("setName", String.class);
            method.invoke(student,"小账");//实现了动态调用
            System.out.println(student.getName());

            //操作属性
            Field field = clazz.getDeclaredField("name");
            field.setAccessible(true);//这个属性不需要做安全检查了，可以直接访问
            field.set(student,"小张张");
            System.out.println(student.getName());
            System.out.println(field.get(student));
            //不能访问私有的属性
            //通过反射是有办法操作私有属性的，setAccessible这个属性不用做安全检查了，可以直接访问
            //上面是通过调用对象的方法获取到的值，下面是通过反射来调用到对象的属性
            //通过反射直接写属性的值、通过反射直接读属性的值
            /**
             *
             *
             * 反射操作泛型generic
             * • Java采用泛型擦除的机制来引入泛型。Java中的泛型仅仅是给编译器javac使用的，确保数据的安全性和免去强制类型转换的麻烦。但是，一旦编译完成，所有的和泛型有关的类型全部擦除。
             *
             * • 为了通过反射操作这些类型以迎合实际开发的需要，Java就新增了ParameterizedType， GenericArrayType，TypeVariable 和WildcardType几种类型来代表不能被归一到Class 类中的类型但是又和原始类型齐名的类型。
             *
             * • ParameterizedType: 表示一种参数化的类型，比如Collection
             * • GenericArrayType: 表示一种元素类型是参数化类型或者类型变量的数组类型
             * • TypeVariable: 是各种类型变量的公共父接口
             * • WildcardType: 代表一种通配符类型表达式，比如?, ? extends Number, ? super Integer【 wildcard是一个单词：就是“通配符”】
             */
            //获得指定方法参数泛型信息
            Method m = TestReflection.class.getMethod("test01",Map.class,List.class);
            Type[] types = m.getGenericParameterTypes();
            for (Type paramType : types) {
                System.out.println("#" + paramType);
                if (paramType instanceof ParameterizedType) {
                    Type[] genericTypes = ((ParameterizedType)paramType).getActualTypeArguments();
                    for (Type genericType : genericTypes) {
                        System.out.println("泛型类型"  + genericType);
                    }
                }
            }
            //返回执行方法返回值泛型信息
            Method m2 = TestReflection.class.getMethod("test02",null);
            Type returnType = m2.getGenericReturnType();
            if (returnType instanceof ParameterizedType) {
                Type[] genericTypes = ((ParameterizedType)returnType).getActualTypeArguments();
                for (Type genericType : genericTypes) {
                    System.out.println("返回值，泛型类型" + genericType);
                }
            }


            /**
             * 反射机制性能问题
             * • setAccessible
             * – 启用和禁用访问安全检查的开关
             * 值为 true 则指示反射的对象在使用时应该取消 Java 语言访问检查。
             * 值为 false 则指示反射的对象应该实施 Java 语言访问检查。并不是为true 就能访问为false就不能访问。
             * – 禁止安全检查，可以提高反射的运行速度。
             * • 可以考虑使用：cglib/javaassist字节码操作
             *
             * 比较反射的执行效率
             * 普通方法的调用
             *
             */
            test1();
            test2();
            test3();



        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void test01(Map<String,Student> map, List<Student> list) {
        System.out.println("test01");
    }

    public Map<Integer,Student> test02)() {
        System.out.println("test02");
        return null;
    }

    public static void test1() {
        Student student = new Student();
        long start = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            student.getName()
        }
        long end = System.currentTimeMillis();
        System.out.println("普通方法调用，耗时："+(end - start)+"ms");
    }

    public static void test2() throws Exception{
        Student student = new Student();
        Class clazz = student.getClass();
        Method m = clazz.getDeclaredMethod("getName",null);
        long start = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            m.invoke(student,null)
        }
        long end = System.currentTimeMillis();
        System.out.println("反射动态方法调用，耗时："+(end - start)+"ms");

    }

    public static void test3() throws Exception{
        Student student = new Student();
        Class clazz = student.getClass();
        Method m = clazz.getDeclaredMethod("getName",null);
        m.setAccessible(true);
        long start = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            m.invoke(student,null)
        }
        long end = System.currentTimeMillis();
        System.out.println("反射动态方法调用，跳过安全检查，耗时："+(end - start)+"ms");

    }
}
