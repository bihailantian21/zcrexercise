package com.zcr.com.zcr.algorithms;

import java.util.Arrays;

/**
 * @author zcr
 * @date 2019/5/7-22:08
 */
public class TestAlgorithms {
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

        int searchWord = 2;
        Arrays.sort(values3);
        System.out.println(BinarySearch.binarySearch(values3,searchWord));

    }
}
