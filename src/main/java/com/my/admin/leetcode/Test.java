package com.my.admin.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Test {

    /**
     * 两数之和
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum(int[] nums, int target) {
        int[] nums_sorted=new int[nums.length];
        //拷贝一份
        System.arraycopy(nums,0,nums_sorted,0,nums.length);
        //对nums进行排序
        Arrays.sort(nums_sorted);
        int max = nums_sorted.length - 1;
        int min = 0;
        int temp0 = 0;
        int temp1 = 0;
        while (max != min) {
            if (nums_sorted[max] + nums_sorted[min] > target) {
                max --;
            }else if (nums_sorted[max] + nums_sorted[min] < target){
                min ++;
            }else {
                temp0 = nums_sorted[min];
                temp1 = nums_sorted[max];
                break;
            }
        }
        boolean minSetSuccess = false;
        boolean maxSetSuccess = false;
        for (int i = 0; i < nums.length; i++) {
            if (!minSetSuccess && temp0 == nums[i]) {
                min = i;
                minSetSuccess = true;
            }else if (!maxSetSuccess && temp1 == nums[i]) {
                max = i;
                maxSetSuccess = true;
            }else if (maxSetSuccess && minSetSuccess){
                break;
            }
        }
        return new int[]{min, max};
    }

    public static void main(String[] args) {
    }

}
