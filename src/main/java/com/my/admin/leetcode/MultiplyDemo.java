package com.my.admin.leetcode;

public class MultiplyDemo {

    /**
     *
     * //TODO 自实现
     * 给定两个以字符串形式表示的非负整数 num1 和 num2，返回 num1 和 num2 的乘积，它们的乘积也表示为字符串形式。
     * 输入: num1 = "2", num2 = "3"
     * 输出: "6"
     * 输入: num1 = "123", num2 = "456"
     * 输出: "56088"
     *
     * 说明：
     * num1 和 num2 的长度小于110。
     * num1 和 num2 只包含数字 0-9。
     * num1 和 num2 均不以零开头，除非是数字 0 本身。
     * 不能使用任何标准库的大数类型（比如 BigInteger）或直接将输入转换为整数来处理。
     * @param num1
     * @param num2
     * @return
     */
    public static String multiply(String num1, String num2) {
        if ("0".equals(num1) || "0".equals(num2)) {
            return "0";
        }

        //转换成单链表（逆序）
        ListNode1 root1 = new ListNode1(0);
        ListNode1 temp1 = root1;
        for (int i = num1.length() - 1; i >= 0; i--) {
            temp1.next = new ListNode1(Integer.valueOf(num1.substring(i, i + 1)));
            temp1 = temp1.next;
        }
        ListNode1 root2 = new ListNode1(0);
        ListNode1 temp2 = root2;
        for (int i = num2.length() - 1; i >= 0; i--) {
            temp2.next = new ListNode1(Integer.valueOf(num2.substring(i, i + 1)));
            temp2 = temp2.next;
        }
        //目标结果
        //被乘数
        ListNode1 l1 = root1.next;
        //乘数
        ListNode1 l2 = root2.next;
        //单位各户相乘
        ListNode1 result = new ListNode1(0);
        //进位数字
        int carryNum = 0;
        //记位器
        int i = 0;
        while (l2 != null) {
            ListNode1 root = new ListNode1(0);
            ListNode1 rTemp = root;
            for (int j = i; j > 0 ; j--) {
                rTemp.next = new ListNode1(0);
                rTemp = rTemp.next;
            }
            while (l1 != null || carryNum > 0) {
                int mul = ((null == l2) ? 0 : l2.val) * ((null == l1) ? 0 : l1.val) + carryNum;
                rTemp.next = new ListNode1(mul % 10);
                carryNum = mul / 10;
                rTemp = rTemp.next;
                if ((null != l1)){
                    l1 = l1.next;
                }
            }
            result = MultiplyDemo.ListNode1.addTwoNumbers(root, result);
            l1 = root1.next;
            l2 = l2.next;
            i++;
        }
        //剔除首节点
        result = result.next;
        //result 拼接成字符串
        StringBuffer stringBuffer = new StringBuffer();
        while (null != result){
            stringBuffer.append(result.val);
            result = result.next;
        }
        return stringBuffer.reverse().toString();
    }

    static class ListNode1 {
        int val;
        ListNode1 next;
        ListNode1(int x) {
            val = x;
        }
        public static ListNode1 addTwoNumbers(ListNode1 l1, ListNode1 l2) {
            ListNode1 root = new ListNode1(0);
            ListNode1 rTemp = root;
            //进位数字
            int carryNum = 0;
            int sum;
            while (null != l1 || null != l2 || carryNum > 0) {
                sum = ((null == l1) ? 0 : l1.val) + ((null == l2) ? 0 : l2.val) + carryNum;
                rTemp.next = new ListNode1(sum % 10);
                carryNum = sum / 10;
                rTemp = rTemp.next;
                l1 = null == l1 ? null : l1.next;
                l2 = null == l2 ? null : l2.next;
            }
            return root.next;
        }
    }

    /**
     * 高精度算法，优化方案
     * @param num1
     * @param num2
     * @return
     */
    public String multiplyOptimized(String num1, String num2) {
        char[] nums1 = num1.toCharArray();
        char[] nums2 = num2.toCharArray();
        int n = nums1.length,
                m=nums2.length,
                i,j,k;
        //一般来说两个数相乘，其最大位数也不会大于啷个位数之和
        int[] result=new int[n+m];
        //转换成数字
        for( i=0;i<n;i++)
            nums1[i]-='0';
        for( i=0;i<m;i++)
            nums2[i]-='0';
        //用nums2的每一位去乘nums1 累加到result
        for( i=0;i<m;i++){
            int carry=0;
            for( j=0;j<n;j++){
                result[i+j]=result[i+j]+nums2[m-1-i]*nums1[n-1-j]+carry;
                carry=result[i+j]/10;
                result[i+j]%=10;
            }
            k=i+j;
            //处理进位
            while(carry!=0){
                result[k]+=carry;
                carry=result[k]/10;
                result[k]%=10;
                k++;
            }
        }
        StringBuilder tmp=new StringBuilder(n+m);
        i=m+n-1;
        while(i>0 && result[i]==0)
            i--;
        while(i>=0)
            tmp.append(result[i--]);
        return tmp.toString();
    }
}
