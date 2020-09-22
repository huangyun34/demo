package com.my.admin.leetcode;

public class MathByListNode {
    /**
     * 不支持符号
     * @param l1
     * @param l2
     * @return
     */
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode root = new ListNode(0);
        ListNode rTemp = root;
        //进位数字
        int carryNum = 0;
        int sum;
        while (null != l1 || null != l2 || carryNum > 0) {
            sum = ((null == l1) ? 0 : l1.val) + ((null == l2) ? 0 : l2.val) + carryNum;
            rTemp.next = new ListNode(sum % 10);
            carryNum = sum / 10;
            rTemp = rTemp.next;
            l1 = null == l1 ? null : l1.next;
            l2 = null == l2 ? null : l2.next;
        }
        return root.next;
    }

    /**
     * 两个数字相减不支持符号
     * @param l1
     * @param l2
     * @return
     */
    public static ListNode reduceTwoNumbers(ListNode l1, ListNode l2) {
        ListNode root = new ListNode(0);
        ListNode rTemp = root;
        //借位数字
        int carryNum = 0;
        int sum;
        while (null != l1 || null != l2 || carryNum > 0) {
            sum = ((null == l1) ? 0 : l1.val) - ((null == l2) ? 0 : l2.val) - carryNum;
            if (sum >= 0) {
                carryNum = 0;
                if (null != l1.next || sum > 0) {
                    rTemp.next = new ListNode(sum);
                }
            } else {
                carryNum = 1;
                if (null != l1.next) {
                    rTemp.next = new ListNode(sum + 10);
                }
            }
            rTemp = rTemp.next;
            l1 = null == l1 ? null : l1.next;
            l2 = null == l2 ? null : l2.next;
        }
        return root.next;
    }

    public static void main(String[] args) {
        String a = "100";
        String b = "1";
        ListNode listNode1 = new ListNode(0);
        ListNode temp1 = listNode1;
        for (int i = a.length() - 1; i >= 0; i --){
            temp1.next = new ListNode(Integer.valueOf(a.substring(i, i + 1)));
            temp1 = temp1.next;
        }

        ListNode listNode2 = new ListNode(0);
        ListNode temp2 = listNode2;
        for (int i = b.length() - 1; i >= 0; i --){
            temp2.next = new ListNode(Integer.valueOf(b.substring(i, i + 1)));
            temp2 = temp2.next;
        }
//        ListNode listNode = addTwoNumbers(listNode1, listNode2);
        ListNode listNode = reduceTwoNumbers(listNode1, listNode2);
        ListNode temp3 = listNode;
        StringBuilder re = new StringBuilder();
        while (null != temp3) {
            re.append(temp3.val);
            temp3 = temp3.next;
        }
        System.out.println(re.reverse().substring(0, re.length() - 1));
    }
}
