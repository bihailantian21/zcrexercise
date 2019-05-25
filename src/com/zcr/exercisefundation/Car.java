package com.zcr.exercisefundation;

import java.util.Objects;

/**
 * 继承、封装
 * @author zcr
 * @date 2019/5/6-11:25
 */
public class Car{//Object类是所有Java类的根基类，也就意味着所有的Java对象都拥有Object类的属性和方法。如果在类的声明中未使用extends关键字指明其父类，则默认继承Object类。
    private String make;
    private String model;
    private int year;
    private double odometer_reading;
    private Engine engine;////类和类之间是可以互相引用的、嵌套的



    public Car() {
    }

    public Car(String make, String model, int year, double odometer_reading) {
        this();
        this.make = make;
        this.model = model;
        this.year = year;
        this.odometer_reading = odometer_reading;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public int getYear() {
        return year;
    }

    public double getOdometer_reading() {
        return odometer_reading;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public void setModel(String model) {
        this.model = model;
    }

    //封装
    /*编程中封装的具体优点：
            1.提高代码的安全性。
            2.提高代码的复用性。
            3.“高内聚”：封装细节，便于修改内部代码，提高可维护性。
            4.“低耦合”：简化外部调用，便于调用者使用，便于扩展和协作。*/
    //封装的实现--使用访问控制符：private、default、protected、public，它们说明了面向对象的封装性，所以我们要利用它们尽可能的让访问权限降到最低，从而提高安全性。
    /*
    1.private 表示私有，只有自己类能访问 只有同一个类中能访问
    2.default表示没有修饰符修饰，只有同一个包的类能访问   只有同一个包中的类才能访问
    3.protected表示可以被同一个包的类以及其他包中的子类访问  同一类、同一包、不同包中的子类（不同包中的非子类不能访问）
    4.public表示可以被该项目的所有包中的所有类访问  同一类中、同一个包中、不同包中的子类、不同包中
    */
    //封装的使用细节
    /*类的属性的处理:
    1.一般使用private访问权限。
    2. 提供相应的get/set方法来访问相关属性，这些方法通常是public修饰的，以提供对属性的赋值与读取操作(注意：boolean变量的get方法是is开头!)。
    3.一些只用于本类的辅助性方法可以用private修饰，希望其他类调用的方法用public修饰。
    */
    public void setYear(int year) {
        this.year = year;
    }

    public void setOdometer_reading(double odometer_reading) {
        this.odometer_reading = odometer_reading;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return year == car.year &&
                Double.compare(car.odometer_reading, odometer_reading) == 0 &&
                Objects.equals(make, car.make) &&
                Objects.equals(model, car.model) &&
                Objects.equals(engine, car.engine);
    }

    @Override
    public int hashCode() {
        return Objects.hash(make, model, year, odometer_reading, engine);
    }

    //重写toString()方法
    /*Object类中定义有public String toString()方法，其返回值是 String 类型。Object类中toString方法的源码为：
    public String toString() {
        return getClass().getName() + "@" + Integer.toHexString(hashCode());
    }
    根据如上源码得知，默认会返回“类名+@+16进制的hashcode”。在打印输出或者用字符串连接对象时，会自动调用该对象的toString()方法。*/
    @Override
    public String toString() {
        return "Car{" +
                "make='" + make + '\'' +
                ", model='" + model + '\'' +
                ", year=" + year +
                ", odometer_reading=" + odometer_reading +
                '}';
    }

    public void propertity(){
        year = 2018;
        System.out.println("我有四个轮胎"+this.getYear());
    }

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public void zuzhuang(){
        System.out.println("我的引擎品牌是"+engine.getBrand());
    }

    private void fuleisiyou(){
        System.out.println("fuleisiyou");
    }
}

//每一个源文件必须有且只有一个public class，并且类名和文件名保持一致！
// 一个Java文件可以同时定义多个class
class Tyre{

}
class Enginee{

}
class Seat{

}
