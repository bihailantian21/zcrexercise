package com.zcr.softwaretest;

import java.util.Scanner;

/**
 * @author zcr
 * @date 2019/5/9-20:36
 */
public class Triangle2 {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        int b = sc.nextInt();
        int c = sc.nextInt();
        if (a < 1 || a > 300){
            System.out.println("a超出范围");
        }
        if (b < 1 || b > 300){
            System.out.println("b超出范围");
        }
        if (c < 1 || c > 300){
            System.out.println("c超出范围");
        }

        System.out.println(a);
        System.out.println(b);
        System.out.println(c);

        Boolean isTriangle ;
        if ((a + b > c) && (a + c > b) && (b + c > a)){
            isTriangle = true;
        }else{
            isTriangle = false;
        }

        if (isTriangle){
            if (a == b && b == c){
                System.out.println("等边三角形");
            }else if (a != b && a != c && b != c){
                System.out.println("一般三角形");
            }else{
                System.out.println("等腰三角形");
            }
        }else {
            System.out.println("非三角形");
        }


    }
}
