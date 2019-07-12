package com.zcr.exercisetest;

import java.lang.annotation.*;
import java.lang.reflect.Field;

/**
 * @author zcr
 * @date 2019/7/12-11:22
 */
public class TestAnnotation {

    /**
     * • Annotation的作用：
     * – 不是程序本身，可以对程序作出解释。(这一点，跟注释没什么区别)
     * – 可以被其他程序(比如：编译器等)读取。(注解信息处理流程，是注解和注释的重大区别 。如果没有注解信息处理流程，则注解毫无意义)
     * 比如说：可以对一个程序写一写hibernate的注解，写完以后可以被hibernate读到
     *
     * • Annotation的格式：
     * – 注解是以“@注释名”在代码中存在的，还可以添加一些参数值，例如： @SuppressWarnings(value=“unchecked”)。
     *
     * • Annotation在哪里使用?
     * – 可以附加在package, class, method, field等上面，相当于给它们添加了额外的辅助信 息，我们可以通过反射机制编程实现对这些元数据的访问。
     * 包、类、方法、属性
     */

    //内置注解
    //@Override
    //定义在java.lang.Override中，此注释只适用于修辞方法，表示一个方法声明打算重写超类中的另一个方法声明。
    //@Deprecated
    //定义在java.lang.Deprecated中，此注释可用于修辞方法、属性、类 ，表示不鼓励程序员使用这样的元素，通常是因为它很危险或存在更 好的选择。
    //@SuppressWarnings
    //定义在java.lang.SuppressWarnings中，用来抑制编译时的警告信息 。 可以修饰类、方法、属性…
    //与前两个注释有所不同，你需要添加一个参数才能正确使用，这些参数值都是已经定义好了的，我们选择性的使用就好了，参数如下：
    //deprecation unchecked fallthrough path serial finally all
    //@SuppressWarnings(“unchecked”)
    //@SuppressWarnings(value={“unchecked”, “deprecation”})






    //自定义注解
    /**
     * • 使用@interface自定义注解时，自动继承了java.lang.annotation.Annotation接口
     *
     * • 要点：
     * – @interface用来声明一个注解
     *
     * • 格式为：
     * – public @interface 注解名 {定义体}
     * – 其中的每一个方法实际上是声明了一个配置参数。
     * – 方法的名称就是参数的名称
     * – 返回值类型就是参数的类型（返回值类型只能是基本类型、Class、String、enum）
     * – 可以通过default来声明参数的默认值。
     * – 如果只有一个参数成员，一般参数名为value
     *
     * • 注意：
     * 注解元素必须要有值。我们定义注解元素时，经常使用空字符串、0作为默认值。
     * 也经常使用负数(比如：-1)表示不存在的含义
     */
    @Target(value = {ElementType.METHOD,ElementType.TYPE,ElementType.FIELD})
    //这个注解可以修饰方法、类、属性
    @Retention(RetentionPolicy.RUNTIME)
    public @interface ZDYAnnotation {
        String studentName() default "";
        int age() default 0;
        int id() default -1;
        String[] schools() default {"清华","北大"};
    }

    @ZDYAnnotation(age = 19,studentName = "自动化",id = 1001,schools = {"上交","复旦"})
    public void test() {

    }

    //如果注解中只有一个参数，一般把它定义为value
    @Target(value = {ElementType.METHOD,ElementType.TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    public @interface ZDYAnnotation1 {
        String value();
    }

    @ZDYAnnotation1(value = "aaa")
    public void test1() {

    }








    //元注解
    /**
     * • 元注解的作用就是负责注解其他注解。 Java定义了4个标准的 meta-annotation类型，它们被用来提供对其它 annotation 类型作说明。
     * • 这些类型和它们所支持的类在java.lang.annotation包中可以 找到
     * – @Target
     * – @Retention
     * – @Documented
     * – @Inherited
     *
     * – @Target
     * • 作用： – 用于描述注解的使用范围（即:被描述的注解可以用在什么地方）
     * PACKAGE 包
     * TYPE 类、接口、枚举、注解
     * CONSTRUCTOR 构造器
     * FIELD 域
     * METHOD 方法
     * LOCAL_VARIABLE 局部变量
     * PARAMETER 参数
     *– @Target(value=ElementType.TYPE)
     *
     * – @Retention
     * • 作用： – 表示需要在什么级别保存该注释信息，用于描述注解的生命周期
     * SOURCE 在源文件中有效
     * CLASS 在class文件有效
     * RUNTIME 在运行时有效（可以被反射机制读取）
     *注解仅仅是定义是没有意义的，还要再写注解的类的解析，这样才有意义。
     * 注解信息处理流程。
     */






    //注解作业
    //这个注解用来修饰类
    //只有一个参数，把它和哪个表来对应
    @Target(value = {ElementType.TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    public @interface ZTable {
        String value();
    }

    //列名、类型、长度
    //定义一个新的注解，用来说明属性的一些特性
    @Target(value = {ElementType.FIELD})
    @Retention(RetentionPolicy.RUNTIME)
    public @interface ZField {
        String columnName();
        String type();
        int length();
    }

    //第一步：定义注解本身
    //第二步：在类中使用注解
    //第三步：写注解解析程序，把这些注解读出来进行吧相关的处理
    public static void main(String[] args) {

        try {
            //使用反射机制读取注解信息
            //反射是在运行期间有效的，所以这里要设为runtime
            Class aClass = Class.forName("com.zcr.exercisefundation.Student");
            //获取类的所有有效注解
            Annotation[] annotations = aClass.getAnnotations();
            for (Annotation a : annotations) {
                System.out.println(a);
            }

            //获得类的指定注解
            ZTable zTable = (ZTable) aClass.getAnnotations(ZTable.class);
            System.out.println(zTable.value());

            //获得类的属性的注解
            Field field = aClass.getDeclaredField("name");
            ZField zField = field.getAnnotation(ZField.class);
            System.out.println(zField.columnName()+"---"+zField.type()+"---"+zField.length());

            //根据获得的表名、字段的信息，拼出DDL语句，然后使用JDBC执行这个SQL，在数据库中生成相关的表
        } catch (Exception e) {
            e.printStackTrace();
        }


    }




}
