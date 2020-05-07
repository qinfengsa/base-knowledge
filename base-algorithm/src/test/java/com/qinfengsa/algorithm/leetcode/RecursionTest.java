package com.qinfengsa.algorithm.leetcode;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 递归
 * @author: qinfengsa
 * @date: 2019/6/2 08:23
 */
@Slf4j
public class RecursionTest {

    /**
     * 打印链表
     * @param head
     * @return
     */
    private String logNode(ListNode head){
        StringBuilder sb = new StringBuilder();

        sb.append("ListNode{");

        int index = 0;
        while (Objects.nonNull(head)) {
            if (index > 0) {
                sb.append(",");
            }
            sb.append(head.val);
            head = head.next;
            index++;

        }
        sb.append("}");
        sb.append("size=");
        sb.append(index);
        return sb.toString();

    }


    @Test
    public void swapPairs( ) {
        ListNode head = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        head.next = node2;
        node2.next = node3;
        node3.next = node4;
        ListNode root = swapPairs(head);
        log.debug(logNode(root));
    }

    /**
     * 两两交换链表中的节点
     * 给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。
     *
     * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
     *
     *
     *
     * 示例:
     *
     * 给定 1->2->3->4, 你应该返回 2->1->4->3.
     * @param head
     * @return
     */
    public ListNode swapPairs(ListNode head) {
        if (Objects.isNull(head)) {
            return null;
        }
        if (Objects.isNull(head.next)) {
            return head;
        }

        ListNode node = head;
        ListNode root = head.next;

        ListNode nextHead = root.next;

        root.next = node;
        node.next = swapPairs(nextHead);
        return root;

    }

    @Test
    public void fib() {
        int result = fib2(6);
        log.debug("result:{}",result);
    }


    /**
     * 面试题10- I. 斐波那契数列
     * 写一个函数，输入 n ，求斐波那契（Fibonacci）数列的第 n 项。斐波那契数列的定义如下：
     *
     * F(0) = 0,   F(1) = 1
     * F(N) = F(N - 1) + F(N - 2), 其中 N > 1.
     * 斐波那契数列由 0 和 1 开始，之后的斐波那契数就是由之前的两数相加而得出。
     *
     * 答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。
     *
     *
     *
     * 示例 1：
     *
     * 输入：n = 2
     * 输出：1
     * 示例 2：
     *
     * 输入：n = 5
     * 输出：5
     *
     *
     * 提示：
     *
     * 0 <= n <= 100
     * @param n
     * @return
     */
    private int fib2(int n) {
        int mod = 1000000007;
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        int a = 0,b = 1,tmp = 1;

        for (int i = 2; i <= n ; i++) {
            tmp = (a + b) % mod;
            a = b ;
            b = tmp;
        }
        return tmp;
    }


    /**
     *  斐波那契数
     * 斐波那契数，通常用 F(n) 表示，形成的序列称为斐波那契数列。该数列由 0 和 1 开始，后面的每一项数字都是前面两项数字的和。也就是：
     *
     * F(0) = 0,   F(1) = 1
     * F(N) = F(N - 1) + F(N - 2), 其中 N > 1.
     * 给定 N，计算 F(N)。
     *
     *
     *
     * 示例 1：
     *
     * 输入：2
     * 输出：1
     * 解释：F(2) = F(1) + F(0) = 1 + 0 = 1.
     * 示例 2：
     *
     * 输入：3
     * 输出：2
     * 解释：F(3) = F(2) + F(1) = 1 + 1 = 2.
     * 示例 3：
     *
     * 输入：4
     * 输出：3
     * 解释：F(4) = F(3) + F(2) = 2 + 1 = 3.
     *
     *
     * 提示：
     *
     * 0 ≤ N ≤ 30
     * @param N
     * @return
     */
    public int fib(int N) {
        Map<Integer,Integer> map = new HashMap<>();
        map.put(0,0);
        map.put(1,1);
        return fibA(map,N);
    }


    private int fibA(Map<Integer,Integer> map,int N) {

        Integer result = map.get(N);
        if (Objects.nonNull(result)) {
            return result;
        }
        result = fibA(map,N - 2) + fibA(map,N - 1);
        // 用map记录计算好的值
        map.put(N,result);
        return result;


    }


    @Test
    public void kthGrammar() {
        int result = kthGrammar(4, 5);
        log.info("result : {}",result);
    }

    /**
     * 第K个语法符号
     * 在第一行我们写上一个 0。接下来的每一行，将前一行中的0替换为01，1替换为10。
     *
     * 给定行数 N 和序数 K，返回第 N 行中第 K个字符。（K从1开始）
     *
     *
     * 例子:
     *
     * 输入: N = 1, K = 1
     * 输出: 0
     *
     * 输入: N = 2, K = 1
     * 输出: 0
     *
     * 输入: N = 2, K = 2
     * 输出: 1
     *
     * 输入: N = 4, K = 5
     * 输出: 1
     *
     * 解释:
     * 第一行: 0
     * 第二行: 01
     * 第三行: 0110
     * 第四行: 01101001
     *
     * 注意：
     *
     * N 的范围 [1, 30].
     * K 的范围 [1, 2^(N-1)].
     * @param N
     * @param K
     * @return
     */
    public int kthGrammar(int N, int K) {

        if (N == 1) {
            return 0;
        }

        // 判断K是奇数函数偶数
        boolean isEvenNum = true;
        if ((K & 1) == 1) { // 奇数
            isEvenNum = false;
        }

        // 根据 K + 1/2 找到上一行的位置，
        int knum = (K + 1)/2;

        int num = kthGrammar(N - 1,knum);

        if (num == 0) {
            if (isEvenNum) {
                return 1;
            } else {
                return 0;
            }

        } else {
            if (isEvenNum) {
                return 0;
            } else {
                return 1;
            }
        }

        /*if(N==1 && K==1)
            return 0;
        int a = kthGrammar(N-1,(K+1)/2);  //求父结点的值；(K+1)/2为父结点的序号
        int b = -(a-1);   //若a=0则b=1,若a=1则b=0
        if(K%2==1)
            return a;   //K为奇数则其值与父结点相同
        else
            return b; */
    }



    @Test
    public void reverseString() {
        char[] s = {'h','e','l','l','o'};
        reverseString(s);
        log.info("result : {}",s);
    }

    /**
     * 反转字符串
     * 编写一个函数，其作用是将输入的字符串反转过来。输入字符串以字符数组 char[] 的形式给出。
     *
     * 不要给另外的数组分配额外的空间，你必须原地修改输入数组、使用 O(1) 的额外空间解决这一问题。
     *
     * 你可以假设数组中的所有字符都是 ASCII 码表中的可打印字符。
     *
     *
     *
     * 示例 1：
     *
     * 输入：["h","e","l","l","o"]
     * 输出：["o","l","l","e","h"]
     * 示例 2：
     *
     * 输入：["H","a","n","n","a","h"]
     * 输出：["h","a","n","n","a","H"]
     * @param s
     */
    public void reverseString(char[] s) {
        int len = s.length;
        reverseString(s,0,len-1);

    }

    private void reverseString(char[] s,int start, int end) {
        if (start >= end) {
            return;
        }
        char a = s[start];
        s[start] = s[end];
        s[end] = a;
        reverseString(s,start+1,end-1);

    }

}
