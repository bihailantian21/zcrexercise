package com.zcr.softwaretest;

import java.util.Scanner;

/**
 * @author zcr
 * @date 2019/5/9-20:46
 */
public class NextDate {
    public static boolean isLeapYear(int year){
        if (((year % 4 == 0)&&(year % 100 != 0)) || (year % 400 == 0)){
            return true;
        }else {
            return false;
        }
    }
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        int year = sc.nextInt();
        int month = sc.nextInt();
        int day = sc.nextInt();
        if (year < 1812 || year > 2019){
            System.out.println("年的值不在1812~2019内");
            System.out.println("请输入新的年值");
            year = sc.nextInt();
        }
        if (month < 1 || month > 12){
            System.out.println("月的值不在1~12内");
            System.out.println("请输入新的月值");
            month = sc.nextInt();
        }
        if (day < 1 || day > 31){
            System.out.println("日的值不在1~31内");
            System.out.println("请输入新的日值");
            day = sc.nextInt();

        }
        System.out.println(year);
        System.out.println(month);
        System.out.println(day);

        int tomorrowyear = year;
        int tomorrowmonth = month;
        int tomorrowday = day;

        switch (month){
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
                if (day < 31){
                    tomorrowday = day + 1;
                }else{
                    tomorrowday = 1;
                    tomorrowmonth = month + 1;
                }
                break;
            case 4:
            case 6:
            case 9:
            case 11:
                if (day < 30){
                    tomorrowday = day + 1;
                }else if (day == 30){
                    tomorrowday = 1;
                    tomorrowmonth = month + 1;
                }else {
                    System.out.println("当前月份没有31号");
                }
                break;
            case 12:
                if (day < 31){
                    tomorrowday = day + 1;
                }else{
                    tomorrowday = 1;
                    tomorrowmonth = 1;
                    if (year > 2019){
                        System.out.println("超过2019年了");
                    }else {
                        tomorrowyear = year + 1;
                    }
                }
                break;
            case 2:
                if (day < 28){
                    tomorrowday = day + 1;
                }else if (day == 28){
                    if (isLeapYear(year)){
                        tomorrowday = 29;
                    }else {
                        tomorrowday = 1;
                        tomorrowmonth = 3;
                    }
                }else if(day == 29){
                    if (isLeapYear(year)){
                        tomorrowday = 1;
                        tomorrowmonth = 3;
                    }else{
                        System.out.println("当前年份2月不能有29天");
                    }
                }else {
                    System.out.println("2月不能有30/31天");
                }
                break;
        }
        System.out.println(tomorrowyear);
        System.out.println(tomorrowmonth);
        System.out.println(tomorrowday);
    }
}
