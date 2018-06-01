package com.my.admin.leetcode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class StringUtils {

    /**
     * 给定一个字符串，找出不含有重复字符的最长子串的长度。
     *
     * 示例：
     *
     * 给定 "abcabcbb" ，没有重复字符的最长子串是 "abc" ，那么长度就是3。
     *
     * 给定 "bbbbb" ，最长的子串就是 "b" ，长度是1。
     *
     * 给定 "pwwkew" ，最长子串是 "wke" ，长度是3。请注意答案必须是一个子串，"pwke" 是 子序列  而不是子串。
     * @param s
     * @return
     */
    public static int lengthOfLongestSubstring(String s) {
        StringBuffer stringBuffer = new StringBuffer();
        int length = 0;
        for (int i = 0; i < s.length(); i++){
            String s1 = s.substring(i, i+1);
            if (stringBuffer.indexOf(s1) == -1) {
                stringBuffer.append(s1);
            }else {
                length = stringBuffer.length() > length ? stringBuffer.length() : length;
                stringBuffer.append(s1);
                stringBuffer.delete(0, stringBuffer.indexOf(s1) + 1);
            }
            length = stringBuffer.length() > length ? stringBuffer.length() : length;
        }
        return length;
    }

    /**
     * //TODO 原理还未懂
     * 给定一个字符串，找出不含有重复字符的最长子串的长度。
     *
     * 示例：
     *
     * 给定 "abcabcbb" ，没有重复字符的最长子串是 "abc" ，那么长度就是3。
     *
     * 给定 "bbbbb" ，最长的子串就是 "b" ，长度是1。
     *
     * 给定 "pwwkew" ，最长子串是 "wke" ，长度是3。请注意答案必须是一个子串，"pwke" 是 子序列  而不是子串。
     * @param s
     * @return
     */
    public static int lengthOfLongestSubstringOptimized(String s) {
        int[] list = new int[256];
        int previous = -1, right = 0, max_len = 0;
        for(int i=0;i<list.length;i++){
            list[i]=-1;
        }
        while(right<s.length()){
            char c = s.charAt(right);
            if(list[(int)c] > previous)
                previous = list[(int)c];
            max_len = Math.max(max_len, right - previous);
            list[(int)c] = right++;
        }
        return max_len;
    }

    /**
     * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为1000。
     *
     * 示例 1：
     *
     * 输入: "babad"
     * 输出: "bab"
     * 注意: "aba"也是一个有效答案。
     * 示例 2：
     *
     * 输入: "cbbd"
     * 输出: "bb"
     * @param s
     * @return
     */
    public static String longestPalindrome(String s) {
        String result = "";
        String temp = s.intern();
        for (int i = 0; i < temp.length(); i++) {
            String s1 = temp.substring(i, i + 1);
            if (temp.lastIndexOf(s1) != i) {
                result = result.length() >= temp.substring(temp.lastIndexOf(s1), temp.lastIndexOf(s1) + 1).length() ?
                        result : temp.substring(i, temp.lastIndexOf(s1) + 1);
            }
            if (result.length() > s.length() - i) {
                return result;
            }
            temp = s.intern();
        }
        return "".equals(result) ? s : result;
    }

    public static void main(String[] args) {
        System.out.println(longestPalindrome("abdbacd"));
    }
}
