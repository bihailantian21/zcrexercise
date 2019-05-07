package com.zcr.exercisetest;

import com.zcr.exercisefundation.Car;
import com.zcr.exercisefundation.ElectricCar;
import com.zcr.exercisefundation.Student;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author zcr
 * @date 2019/5/6-11:11
 */
public class TestFundation {

    public static void  main(String args[]){

        //第一阶段1
        byte b;
        short s;
        int i;
        long l = 100000000L;
        float f = 3.455f;
        double d;
        char c = '中';
        boolean bb;

        //常量
        final double PI = 3.14;

        int month = 56;
        switch (month){
            case 1:
                System.out.println(1);
                break;
            case 2:
                System.out.println(2);
                break;
            default:
                System.out.println(13);
                break;
        }


        Scanner sc = new Scanner(System.in);
        int x = sc.nextInt();
        System.out.println(x);
        String name = sc.nextLine();
        System.out.println(name);

        Random r = new Random();
        int number = r.nextInt();
        System.out.println(number);

        int[] arr = new int[5];
        arr[0]=1;
        arr[2]=3;
        System.out.println(arr[4]);


    }

}
