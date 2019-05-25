package com.zcr.exercisefundation;

/**
 * @author zcr
 * @date 2019/5/6-11:29
 */
public class ElectricCar extends Car implements AutoDri {
    //Java中只有单继承
    //子类继承父类，可以得到父类的全部属性和方法 (除了父类的构造方法)，但不见得可以直接访问(比如，父类私有的属性和方法)。
    private double battery_size;

    //继承树追溯
    /*
    ·属性/方法查找顺序：(比如：查找变量h)
        1.查找当前类中有没有属性h
        2.依次上溯每个父类，查看每个父类中是否有h，直到Object
        3.如果没找到，则出现编译错误。
        4.上面步骤，只要找到h变量，则这个过程终止。
    ·构造方法调用顺序：
        构造方法第一句总是：super(…)来调用父类对应的构造方法。所以，流程就是：先向上追溯到Object，然后再依次向下执行类的初始化块和构造方法，直到当前子类为止。
    注：静态初始化块调用顺序，与构造方法调用顺序一样，不再重复。*/


    //super关键字
    /*super是直接父类对象的引用。可以通过super来访问父类中被子类覆盖的方法或属性。
    使用super调用普通方法，语句没有位置限制，可以在子类中随便调用。
    若是构造方法的第一行代码没有显式的调用super(…)或者this(…);那么Java默认都会调用super(),
    含义是调用父类的无参数构造方法。这里的super()可以省略。*/
    public ElectricCar(double battery_size) {
        //这里有super();自己不写的话程序会给你自动加上无参的super()。即使没有super()，它还是调用到了父类的构造方法，在子类的构造方法前面，会自动给你加一个super();语句。
        this.battery_size = battery_size;
    }

    public ElectricCar(String make, String model, int year, double odometer_reading, double battery_size) {
        super(make, model, year, odometer_reading);//调用父类的构造方法
        this.battery_size = battery_size;
    }

    public double getBattery_size() {
        return battery_size;
    }

    public void setBattery_size(double battery_size) {
        this.battery_size = battery_size;
    }

    public void Dri(){
        System.out.println("我在自动驾驶");

    }

    //方法的重写override
    /*方法的重写需要符合下面的三个要点：
    1.“==”： 方法名、形参列表相同。
    2.“≤”：返回值类型和声明异常类型，子类小于等于父类。
    3.“≥”： 访问权限，子类大于等于父类。*/
    public void propertity(){
        super.propertity();//可以通过super来访问父类中被子类覆盖的方法或属性。调用父类对象的普通方法
        this.setYear(2019);
        System.out.println("我有四个轮胎，我还有一个翅膀"+this.getYear());
    }
}
