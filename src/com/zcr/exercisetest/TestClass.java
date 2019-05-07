package com.zcr.exercisetest;

import com.zcr.exercisefundation.Car;
import com.zcr.exercisefundation.ElectricCar;
import com.zcr.exercisefundation.Engine;

/**
 * @author zcr
 * @date 2019/5/6-21:28
 */
public class TestClass {
    public static void  main(String args[]){

        //第一阶段2
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
        if (car3 instanceof Car) {
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


        Car car11 = new Car();
        Car car22 = new Car();
        System.out.println(car11==car22);
        System.out.println(car11.equals(car22));

    }
}
