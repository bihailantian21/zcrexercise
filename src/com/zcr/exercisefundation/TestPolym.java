package com.zcr.exercisefundation;

//final关键字
/*final关键字的作用：
        1.修饰变量: 被他修饰的变量不可改变。一旦赋了初值，就不能被重新赋值。常量！
final int MAX_SPEED = 120;
        2.修饰方法：该方法不可被子类重写。但是可以被重载!
final void study(){}
        3. 修饰类: 修饰的类不能被继承。比如：Math、String等。
final class A {}*/
final class Animall{//我是final类，不能被子类继承
    public final void shoutt(){
        System.out.println("我是final方法，不能被子类重写！");
    }
}
class Animal {
    public void shout() {
        System.out.println("叫了一声！");
    }
}
class Dog extends Animal {
    public void shout() {
        System.out.println("旺旺旺！");
    }
    public void seeDoor() {
        System.out.println("看门中....");
    }
}
class Cat extends Animal {
    public void shout() {
        System.out.println("喵喵喵喵！");
    }
}
public class TestPolym {
    public static void main(String[] args) {
        //多态和类型转换测试
        /*多态指的是同一个方法调用，由于对象不同可能会有不同的行为。
        多态的要点：
        1.多态是方法的多态，不是属性的多态(多态与属性无关)。
        2.多态的存在要有3个必要条件：继承，方法重写，父类引用指向子类对象。
        3.父类引用指向子类对象后，用该父类引用调用子类重写的方法，此时多态就出现了。

        给大家展示了多态最为多见的一种用法，即父类引用做方法的形参，实参可以是任意的子类对象，
        可以通过不同的子类对象实现不同的行为方式。
        由此，我们可以看出多态的主要优势是提高了代码的可扩展性，符合开闭原则。
        但是多态也有弊端，就是无法调用子类特有的功能，比如，我不能使用父类的引用变量调用Dog类特有的seeDoor()方法。
        那如果我们就想使用子类特有的功能行不行呢?行!这就是我们下一章节所讲的内容：对象的转型。
        */
        Animal a1 = new Cat(); // 向上可以自动转型。父类引用指向子类对象，我们称这个过程为向上转型，属于自动类型转换。
        //传的具体是哪一个类就调用哪一个类的方法。大大提高了程序的可扩展性。
        animalCry(a1);//
        Animal a2 = new Dog();
        animalCry(a2);//a2为编译类型，Dog对象才是运行时类型。

        /*向上转型后的父类引用变量只能调用它编译类型的方法，不能调用它运行时类型的方法。
        因为编译器只认得d是Animal类型的，只认Animal的方法。
        如果d还想使用狗类的方法seeDoor()，必须强制转回来。
        这时，我们就需要进行类型的强制转换，我们称之为向下转型!*/
        //编写程序时，如果想调用运行时类型的方法，只能进行强制类型转换。
        // 否则通不过编译器的检查。
        Dog dog = (Dog)a2;//向下需要强制类型转换
        dog.seeDoor();

        //对象的转型
        Object obj = new String("西安交通大学"); // 向上可以自动转型
        // obj.charAt(0) 无法调用。编译器认为obj是Object类型而不是String类型
        /* 编写程序时，如果想调用运行时类型的方法，只能进行强制类型转换。
         * 不然通不过编译器的检查。 */
        String str = (String) obj; // 向下转型
        System.out.println(str.charAt(0)); // 位于0索引位置的字符
        System.out.println(obj == str); // true.他们俩运行时是同一个对象

        /*
        真实的子类类型是String，但是此处向下转型为StringBuffer
        在向下转型过程中，必须将引用变量转成真实的子类类型(运行时类型)否则会出现类型转换异常ClassCastException。
        StringBuffer str1 = (StringBuffer) obj;
        System.out.println(str.charAt(0));
        为了避免出现这种异常，我们可以使用之前所学的instanceof运算符进行判断
        */
        if(obj instanceof String){
            String str3 = (String)obj;
            System.out.println(str3.charAt(0));
        }else if(obj instanceof StringBuffer){
            StringBuffer str4 = (StringBuffer) obj;
            System.out.println(str4.charAt(0));
        }

    }

    // 有了多态，只需要让增加的这个类继承Animal类就可以了。
    static void animalCry(Animal a) {
        a.shout();
    }

    /* 如果没有多态，我们这里需要写很多重载的方法。
     * 每增加一种动物，就需要重载一种动物的喊叫方法。非常麻烦。
     * 所以有了多态，我们只用让父类引用指向子类对象，即可。
    static void animalCry(Dog d) {
        d.shout();
    }
    static void animalCry(Cat c) {
        c.shout();
    }*/
}
