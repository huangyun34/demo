package com.my.admin.leetcode;


import org.apache.commons.lang3.ArrayUtils;

import java.util.Arrays;

public class ArraysUtils {
    /**
     * 给定两个大小为 m 和 n 的有序数组 nums1 和 nums2 。
     *
     * 请找出这两个有序数组的中位数。要求算法的时间复杂度为 O(log (m+n)) 。
     *
     * 示例 1:
     *
     * nums1 = [1, 3]
     * nums2 = [2]
     *
     * 中位数是 2.0
     * 示例 2:
     *
     * nums1 = [1, 2]
     * nums2 = [3, 4]
     *
     * 中位数是 (2 + 3)/2 = 2.5
     * @param nums1
     * @param nums2
     * @return
     */
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int[] newNums = addAll(nums1, nums2);
//        int[] newNums = ArrayUtils.addAll(nums1, nums2);
        Arrays.sort(newNums);
        if (newNums.length%2 == 1) {
            return newNums[newNums.length/2];
        }else {
            return (double) (newNums[newNums.length/2 - 1] + newNums[newNums.length/2])/2;
        }
    }

    private static int[] addAll(int[] array1, int... array2) {
        if (array1 == null) {
            return clone(array2);
        } else if (array2 == null) {
            return clone(array1);
        } else {
            int[] joinedArray = new int[array1.length + array2.length];
            System.arraycopy(array1, 0, joinedArray, 0, array1.length);
            System.arraycopy(array2, 0, joinedArray, array1.length, array2.length);
            return joinedArray;
        }
    }

    private static int[] clone(int[] array) {
        return array == null ? null : (int[])array.clone();
    }
}
