package com.zcr.com.zcr.algorithms;

/**
 * @author zcr
 * @date 2019/5/7-23:05
 */
public class BinarySearch {
    public static int binarySearch(int[] array,int value){
        int low = 0;
        int high = array.length - 1;
        while(low <= high){
            int mid = (low + high) / 2;
            if (value == array[mid]) {
                return mid;
            }
            if (value > array[mid]){
                low = mid + 1;
            }
            if (value < array[mid]) {
                high = mid - 1;

            }
        }
        return -1;
    }
}
