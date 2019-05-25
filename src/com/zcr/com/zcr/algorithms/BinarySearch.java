package com.zcr.com.zcr.algorithms;

import java.util.Arrays;

/**
 * @author zcr
 * @date 2019/5/7-23:05
 */
public class BinarySearch {
    //二分法查找
    /*二分法检索(binary search)又称折半检索，二分法检索的基本思想是设数组中的元素从小到大有序地存放在数组(array)中，首先将给定值key与数组中间位置上元素的关键码(key)比较，如果相等，则检索成功;
    否则，若key小，则在数组前半部分中继续进行二分法检索；
    若key大，则在数组后半部分中继续进行二分法检索。
    这样，经过一次比较就缩小一半的检索区间，如此进行下去，直到检索成功或检索失败。*/

    public static void main(String args[]) {
        int[] values3 = { 3, 1, 6, 2, 9, 0, 7, 4, 5, 8 };
        BubbleSort.bubbleSort3(values3);
        System.out.println(Arrays.toString(values3));//二分法查找之前，一定要对数组元素排
        int searchWord = 2;// 所要查找的数
        Arrays.sort(values3);
        System.out.println(BinarySearch.binarySearch(values3,searchWord));
    }
    public static int binarySearch(int[] array,int value){
        int low = 0;
        int high = array.length - 1;
        while(low <= high){
            int mid = (low + high) / 2;
            if (value == array[mid]) {
                return mid;//返回查询到的索引位置
            }
            if (value > array[mid]){
                low = mid + 1;
            }
            if (value < array[mid]) {
                high = mid - 1;

            }
        }
        return -1;//上面循环完毕，说明未找到，返回-1
    }
}
