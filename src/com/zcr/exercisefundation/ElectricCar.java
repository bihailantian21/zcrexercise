package com.zcr.exercisefundation;

/**
 * @author zcr
 * @date 2019/5/6-11:29
 */
public class ElectricCar extends Car implements AutoDri {
    private double battery_size;

    public ElectricCar(double battery_size) {
        //这里有super();自己不写的话程序会给你自动加上无参的super()
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

    @Override
    public void Dri(){
        System.out.println("我在自动驾驶");

    }

    public void propertity(){
        super.propertity();////调用父类对象的普通方法
        System.out.println("我有四个轮胎，我还有一个翅膀");
    }
}
