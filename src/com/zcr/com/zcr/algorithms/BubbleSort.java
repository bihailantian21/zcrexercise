package com.zcr.com.zcr.algorithms;

import java.util.Arrays;

/**
 * @author zcr
 * @date 2019/5/7-22:07
 */
public class BubbleSort {
    public static void main(String args[]) {
        int[] values = { 3, 1, 6, 2, 9, 0, 7, 4, 5, 8 };
        BubbleSort.bubbleSort1(values);
        System.out.println(Arrays.toString(values));

        int[] values2 = { 3, 1, 6, 2, 9, 0, 7, 4, 5, 8 };
        BubbleSort.bubbleSort2(values2);
        System.out.println(Arrays.toString(values2));

        int[] values3 = { 3, 1, 6, 2, 9, 0, 7, 4, 5, 8 };
        BubbleSort.bubbleSort3(values3);
        System.out.println(Arrays.toString(values3));
    }

    /*算法重复地走访过要排序的数列，一次比较两个元素，如果他们的顺序错误就把他们交换过来，这样越大的元素会经由交换慢慢“浮”到数列的顶端。

    冒泡排序算法的运作如下：
        1.比较相邻的元素。如果第一个比第二个大，就交换他们两个。
        2.对每一对相邻元素作同样的工作，从开始第一对到结尾的最后一对。在这一点，最后的元素应该会是最大的数。
        3.针对所有的元素重复以上的步骤，除了最后一个。
        4.持续每次对越来越少的元素重复上面的步骤，直到没有任何一对数字需要比较。*/
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
        for (int i = 0; i < values2.length - 1; i++) {//values.length - 1进行9躺 让他循环9次。0，1，2，3，4，5，6，7，8，9
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
        for (int i = 0; i < values3.length-1; i++) {// 外层循环：n个元素排序，则至多需要n-1趟循环
            boolean flag = true;// 定义一个布尔类型的变量，标记数组是否已达到有序状态
            for (int j = 0; j < values3.length - i - 1; j++) {//内层循环：每一趟循环都从数列的前两个元素开始进行比较，比较到无序数组的最后
                if (values3[j] > values3[j + 1]) {//如果前一个元素大于后一个元素，则交换两元素的值；
                    temp = values3[j];
                    values3[j] = values3[j + 1];
                    values3[j + 1] = temp;
                    flag = false;//本趟发生了交换，表明该数组在本趟处于无序状态，需要继续比较；
                }
            }
            if (flag) {//根据标记量的值判断数组是否有序，如果有序，则退出；无序，则继续循环。
                break;
            }

        }
    }
}



