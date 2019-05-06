package com.zcr.exercisetest;

import com.zcr.exercisefundation.Car;
import com.zcr.exercisefundation.ElectricCar;

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

        ElectricCar ecar1 = new ElectricCar(23.23);
        System.out.println(ecar1);
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
    }
}
