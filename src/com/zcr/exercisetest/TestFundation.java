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
        Scanner sc = new Scanner(System.in);
        int x = sc.nextInt();
        System.out.println(x);

        Random r = new Random();
        int number = r.nextInt();
        System.out.println(number);

        int[] arr = new int[5];
        arr[0]=1;
        arr[2]=3;
        System.out.println(arr[4]);


    }

}
