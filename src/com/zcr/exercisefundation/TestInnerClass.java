package com.zcr.exercisefundation;

public class TestInnerClass {
    public static void main(String[] args) {
        //成员内部类-非静态内部类
        //先创建外部类实例，然后使用该外部类实例创建内部类实例
        Outer1.Inner1 inner1 = new Outer1().new Inner1();
        inner1.show();
        Outer1 outer1 = new Outer1();
        Outer1.Inner1 inn1 = outer1.new Inner1();
        inn1.show();

        //成员内部类-静态内部类
        //通过 new 外部类名.内部类名() 来创建内部类对象
        //Outer.Inner inner = new Outer().new Inner();这是前面要依赖外部类的情况下创建内部类对象
        Outer2.Inner2 inner2 =new Outer2.Inner2();

        //局部内部类
        new Outter3().show();


    }

}

/**外部类Outer*/
class Outer {
    private int age = 10;
    public void show(){
        System.out.println(age);//10
    }
    /**内部类Inner
     *我们把一个类放在另一个类的内部定义，称为内部类(innerclasses)。
     *内部类可以使用public、default、protected 、private以及static修饰。
     *而外部顶级类(我们以前接触的类)只能使用public和default修饰。
     *注意
     *内部类只是一个编译时概念，一旦我们编译成功，就会成为完全不同的两个类。
     *对于一个名为Outer的外部类和其内部定义的名为Inner的内部类。
     * 编译完成后会出现Outer.class和Outer$Inner.class两个类的字节码文件。
     * 所以内部类是相对独立的一种存在，其成员变量/方法名可以和外部类的相同。
     * */
    /*内部类的作用：
    1.内部类提供了更好的封装。只能让外部类直接访问，不允许同一个包中的其他类直接访问。
    2.内部类可以直接访问外部类的私有属性，内部类被当成其外部类的成员。 但外部类不能访问内部类的内部属性。
    3.接口只是解决了多重继承的部分问题，而内部类使得多重继承的解决方案变得更加完整。

    内部类的使用场合：
    1.由于内部类提供了更好的封装特性，并且可以很方便的访问外部类的属性。所以，在只为外部类提供服务的情况下可以优先考虑使用内部类。
    2. 使用内部类间接实现多继承：每个内部类都能独立地继承一个类或者实现某些接口，
    所以无论外部类是否已经继承了某个类或者实现了某些接口，对于内部类没有任何影响。

    成员内部类(非静态内部类、静态内部类)、匿名内部类、局部内部
    */
    public class Inner {
        //内部类中可以声明与外部类同名的属性与方法
        private int age = 20;
        public void show(){
            System.out.println(age);//20
        }
    }
}

//成员内部类-非静态内部类
/*成员内部类(可以使用private、default、protected、public任意进行修饰。 类文件：外部类$内部类.class)
非静态内部类(外部类里使用非静态内部类和平时使用其他类没什么不同)
i. 非静态内部类必须寄存在一个外部类对象里。因此，如果有一个非静态内部类对象那么一定存在对应的外部类对象。非静态内部类对象单独属于外部类的某个对象。
ii. 非静态内部类可以直接访问外部类的成员，但是外部类不能直接访问非静态内部类成员。
iii. 非静态内部类不能有静态方法、静态属性和静态初始化块。
iv. 外部类的静态方法、静态代码块不能访问非静态内部类，包括不能使用非静态内部类定义变量、创建实例。
v. 成员变量访问要点：
    1.内部类里方法的局部变量：变量名。
    2.内部类属性：this.变量名。
    3.外部类属性：外部类名.this.变量名。
vi.内部类的访问：
    1.外部类中定义内部类：new Inner()
    2. 外部类以外的地方使用非静态内部类： Outer.Inner varname = new Outer().new Inner()。
    */
class Outer1 {
    private int age = 10;
    class Inner1 {//非静态内部类
        int age = 20;
        public void show() {
            int age = 30;
            System.out.println("内部类方法里的局部变量age:" + age);// 30
            System.out.println("内部类的成员变量age:" + this.age);// 20
            System.out.println("外部类的成员变量age:" + Outer1.this.age);// 10 非静态内部类可以直接访问外部类的成员
        }
    }
}

//成员内部类-静态内部类
/*i. 定义方式：
static  class   ClassName {
//类体
}
ii. 使用要点：
   1.当一个静态内部类对象存在，并不一定存在对应的外部类对象。
        因此，静态内部类的实例方法不能直接访问外部类的实例方法。
   2.静态内部类看做外部类的一个静态成员。 因此，外部类的方法中可以通过：
        “静态内部类.名字”的方式访问静态内部类的静态成员，
        通过 new 静态内部类()访问静态内部类的实例。*/
class Outer2{
    //相当于外部类的一个静态成员
    static class Inner2{
    }
}


//匿名内部类
/*适合那种只需要使用一次的类。比如：键盘监听操作等等。
语法：
new  父类构造器(实参类表) \实现接口 () {
     //匿名内部类类体！
}
注意
1.匿名内部类没有访问修饰符。
2.匿名内部类没有构造方法。因为它连名字都没有那又何来构造方法呢。
*/




//局部内部类
/*定义在方法内部的，作用域只限于本方法，称为局部内部类。
局部内部类的的使用主要是用来解决比较复杂的问题，想创建一个类来辅助我们的解决方案，
到那时又不希望这个类是公共可用的，所以就产生了局部内部类。
局部内部类和成员内部类一样被编译，只是它的作用域发生了改变，它只能在该方法中被使用，出了该方法就会失效。
局部内部类在实际开发中应用很少。*/
class Outter3 {
    public void show() {
        //作用域仅限于该方法
        class Inner3 {
            public void fun() {
                System.out.println("helloworld");
            }
        }
        new Inner3().fun();
    }
}