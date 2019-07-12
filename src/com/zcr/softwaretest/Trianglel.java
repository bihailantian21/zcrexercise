package com.zcr.softwaretest;

import java.util.Scanner;

/**
 * @author zcr
 * @date 2019/5/9-20:19
 */
public class Trianglel {
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        int b = sc.nextInt();
        int c = sc.nextInt();
        System.out.println(a);
        System.out.println(b);
        System.out.println(c);
        int match = 0;
        if (a == b) {
            match = match + 1;
        }
        if (a == c){
            match = match + 2;
        }
        if (b == c){
            match = match + 3;
        }
        if (match == 0){
            if (a + b <= c) {
                System.out.println("非三角形");
            }else if (a + c <= b) {
                System.out.println("非三角形");
            }else if (b + c <= a){
                System.out.println("非三角形");
            }else{
                System.out.println("一般三角形");
            }
        }else if (match == 1){
            if (a + b <= c){
                System.out.println("非三角形");
            }else{
                System.out.println("等腰三角形");
            }
        }else if (match == 2){
            if (a + c <= b){
                System.out.println("非三角形");
            }else{
                System.out.println("等腰三角形");
            }
        }else if (match == 3){
            if (b + c <= a){
                System.out.println("非三角形");
            }else{
                System.out.println("等腰三角形");
            }
        }else{
            System.out.println("等边三角形");
        }
    }

}
