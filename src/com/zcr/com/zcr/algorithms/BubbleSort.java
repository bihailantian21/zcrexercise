package com.zcr.com.zcr.algorithms;

/**
 * @author zcr
 * @date 2019/5/7-22:07
 */
public class BubbleSort {
    public static void bubbleSort1(int[] values) {//这是一趟的情况，找到最大的交换到最后面
        int temp;
        for (int i = 0; i < values.length - 1; i++) {//values.length - 1因为这一个数要和剩下的9个数进行比较
            if (values[i] > values[i + 1]) {
                temp = values[i];
                values[i] = values[i + 1];
                values[i + 1] = temp;
            }

        }
    }

    public static void bubbleSort2(int[] values2) {
        int temp;
        for (int i = 0; i < values2.length - 1; i++) {//values.length - 1进行9躺
            for (int j = 0; j < values2.length - i - 1; j++) {//values.length-(i+1)第一个数和剩下的9个数，第二个数和剩下的八个数
                if (values2[j] > values2[j + 1]) {
                    temp = values2[j];
                    values2[j] = values2[j + 1];
                    values2[j + 1] = temp;
                }

            }

        }
    }

    public static void bubbleSort3(int[] values3){
        int temp;
        for (int i = 0; i < values3.length-1; i++) {
            boolean flag = true;
            for (int j = 0; j < values3.length - i - 1; j++) {
                if (values3[j] > values3[j + 1]) {
                    temp = values3[j];
                    values3[j] = values3[j + 1];
                    values3[j + 1] = temp;
                    flag = false;
                }
            }
            if (flag) {
                break;
            }

        }
    }
}



