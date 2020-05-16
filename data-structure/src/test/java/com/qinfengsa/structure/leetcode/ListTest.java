package com.qinfengsa.structure.leetcode;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import static com.qinfengsa.structure.util.LogUtils.logResult;

/**
 * @author: qinfengsa
 * @date: 2019/5/15 08:25
 */
@Slf4j
public class ListTest {

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
        log.debug(sb.toString());
        return sb.toString();

    }


    /**
     * 两数相加
     * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
     * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
     * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
     * 示例：
     * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
     * 输出：7 -> 0 -> 8
     * 原因：342 + 465 = 807
     */
    @Test
    public void addTwoNumbersTest() {
        ListNode node1 = new ListNode(9);
        ListNode node2 = new ListNode(8);
        ListNode node3 = new ListNode(6);
        ListNode node7 = new ListNode(3);

        ListNode node4 = new ListNode(1);
        ListNode node5 = new ListNode(6);
        ListNode node6 = new ListNode(4);
        node1.next = node2;
        //node2.next = node3;
        //node3.next = node7;

        //node4.next = node5;
        // node5.next = node6;

        ListNode node = addTwoNumbers(node1,node4);
        log.debug(logNode(node));
    }

    /**
     * 两数相加
     * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
     * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
     * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
     * 示例：
     * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
     * 输出：7 -> 0 -> 8
     * 原因：342 + 465 = 807
     * @param l1
     * @param l2
     * @return
     */
    private ListNode addTwoNumbers(ListNode l1, ListNode l2) {


        ListNode head = new ListNode(-1);
        ListNode node = head;
        int lastNum = 0;
        while (Objects.nonNull(l1) || Objects.nonNull(l2) || lastNum > 0) {
            int a = 0;
            int b = 0;
            if (Objects.nonNull(l1)) {
                a = l1.val;
                l1 = l1.next;
            }
            if (Objects.nonNull(l2)) {
                b = l2.val;
                l2 = l2.next;
            }

            int num = a + b + lastNum;
            if (num >= 10 ) {
                node.next = new ListNode(num - 10);
                lastNum = 1;
            } else {
                node.next = new ListNode(num);
                lastNum = 0;
            }

            node = node.next;
        }
        return head.next;
    }

    /**
     * 合并两个有效链表
     */
    @Test
    public void mergeTwoListsTest() {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(5);

        ListNode node4 = new ListNode(1);
        ListNode node5 = new ListNode(3);
        ListNode node6 = new ListNode(4);
        node1.next = node2;
        node2.next = node3;

        node4.next = node5;
        node5.next = node6;

        ListNode b = mergeTwoLists2(node1,node4);
        log.info("b:{}",b);
    }

    /**
     * 合并两个有效链表
     * @param l1
     * @param l2
     * @return
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (Objects.isNull(l1) && Objects.isNull(l2)) {
            return null;
        }
        if (Objects.isNull(l1)) {
            return l2;
        }
        if (Objects.isNull(l2)) {
            return l1;
        }
        ListNode head = null;
        if (l1.val <= l2.val) {
            head = l1;
            l1 = l1.next;
        } else {
            head = l2;
            l2 = l2.next;
        }
        ListNode node = head;
        while (Objects.nonNull(l1) || Objects.nonNull(l2)) {
            if (Objects.isNull(l1)) {
                node.next = l2;
                l2 = l2.next;
                node = node.next;
                continue;
            }
            if (Objects.isNull(l2)) {
                node.next = l1;
                l1 = l1.next;
                node = node.next;
                continue;
            }
            if (l1.val <= l2.val) {
                node.next = l1;
                l1 = l1.next;
            } else {
                node.next = l2;
                l2 = l2.next;
            }

            node = node.next;
        }


        return head;
    }

    public ListNode mergeTwoLists2(ListNode l1, ListNode l2) {
        if (Objects.isNull(l1) && Objects.isNull(l2)) {
            return null;
        } else if (Objects.isNull(l1)) {
            return l2;
        } else if (Objects.isNull(l2)) {
            return l1;
        } else {
            if (l1.val < l2.val) {
                l1.next = mergeTwoLists2(l1.next, l2);
                return l1;
            } else {
                l2.next = mergeTwoLists2(l1, l2.next);
                return l2;
            }
        }

    }

    /**
     * 回文链表判断
     */
    @Test
    public void isPalindromeTest()   {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node7 = new ListNode(4);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(2);
        ListNode node6 = new ListNode(1);
        node1.next = node2;
        node2.next = node3;
        node3.next = node7;
        node7.next = node4;
        node4.next = node5;
        node5.next = node6;

        boolean b = isPalindrome(node1);
        log.debug("b:{}",b);
    }



    /**
     * 回文链表判断
     * 请判断一个链表是否为回文链表。
     *
     * 示例 1:
     *
     * 输入: 1->2
     * 输出: false
     * 示例 2:
     *
     * 输入: 1->2->2->1
     * 输出: true
     * 进阶：
     * 你能否用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题？
     * @param head
     * @return
     */
    private boolean isPalindrome(ListNode head) {
        if (Objects.isNull(head)) {
            return false;
        }
        ListNode fast = head;
        ListNode slow = head;
        while (Objects.nonNull(fast.next) && Objects.nonNull(fast.next.next)) {
            fast = fast.next.next;
            slow = slow.next;
        }
        // slow去掉链表的中间，然后把slow后面的结点进行反转

        ListNode node = slow.next;
        ListNode pre = null;
        ListNode next = null;
        while (Objects.nonNull(node)) {
            next = node.next;
            node.next = pre;
            pre = node;
            node = next;
        }
        slow.next = pre;
        fast = head;
        slow = slow.next;
        while (Objects.nonNull(slow)) {

            if (fast.val != slow.val) {
                return false;
            }
            fast = fast.next;
            slow = slow.next;
        }

        return true;
    }


    @Test
    public void deleteNode() {
        ListNode head = new ListNode(4);
        ListNode node2 = new ListNode(5);
        ListNode node3 = new ListNode(1);
        ListNode node4 = new ListNode(9);
        head.next = node2;
        node2.next = node3;
        node3.next = node4;
        log.debug(logNode(head));
        deleteNode(node2);
        log.debug(logNode(head));
    }

    /**
     *  删除链表中的节点
     * 请编写一个函数，使其可以删除某个链表中给定的（非末尾）节点，你将只被给定要求被删除的节点。
     *
     * 现有一个链表 -- head = [4,5,1,9]，它可以表示为:
     *
     *
     *
     *
     *
     * 示例 1:
     *
     * 输入: head = [4,5,1,9], node = 5
     * 输出: [4,1,9]
     * 解释: 给定你链表中值为 5 的第二个节点，那么在调用了你的函数之后，该链表应变为 4 -> 1 -> 9.
     * 示例 2:
     *
     * 输入: head = [4,5,1,9], node = 1
     * 输出: [4,5,9]
     * 解释: 给定你链表中值为 1 的第三个节点，那么在调用了你的函数之后，该链表应变为 4 -> 5 -> 9.
     *
     *
     * 说明:
     *
     * 链表至少包含两个节点。
     * 链表中所有节点的值都是唯一的。
     * 给定的节点为非末尾节点并且一定是链表中的一个有效节点。
     * 不要从你的函数中返回任何结果。
     * @param node
     */
    public void deleteNode(ListNode node) {
        // 非尾结点可以把 node的值替换为next的数据
        ListNode next = node.next;

        node.val = next.val;

        node.next = next.next;


    }


    @Test
    public void mergeKLists() {
        ListNode[] lists = new ListNode[3];
        ListNode node11 = new ListNode(1);
        ListNode node12 = new ListNode(4);
        ListNode node13 = new ListNode(5);
        node11.next = node12;
        node12.next = node13;

        ListNode node21 = new ListNode(1);
        ListNode node22 = new ListNode(3);
        ListNode node23 = new ListNode(4);
        node21.next = node22;
        node22.next = node23;

        ListNode node31 = new ListNode(2);
        ListNode node32 = new ListNode(6);
        node31.next = node32;
        lists[0] = node11;
        lists[1] = node21;
        lists[2] = node31;
        ListNode result = mergeKLists2(lists);
         logNode(result) ;
    }

    /**
     * 合并K个元素的有序链表
     * 合并 k 个排序链表，返回合并后的排序链表。请分析和描述算法的复杂度。
     *
     * 示例:
     *
     * 输入:
     * [
     *   1->4->5,
     *   1->3->4,
     *   2->6
     * ]
     * 输出: 1->1->2->3->4->4->5->6
     * @param lists
     * @return
     */
    public ListNode mergeKLists(ListNode[] lists) {

        ListNode node = getMin(lists);
        ListNode head = node;
        while (node != null){
            // 找到最小的结点
            ListNode nextNode = getMin(lists);
            node.next = nextNode;
            node = nextNode;
        }
        return head;
    }

    private ListNode getMin(ListNode[] lists) {

        int minIndex = -1;
        for (int i = 0; i < lists.length; i++) {
            if (lists[i] != null) {
                if (minIndex == -1) {
                    minIndex = i;
                } else if (lists[i].val < lists[minIndex].val) {
                    minIndex = i;
                }
            }
        }
        if (minIndex >= 0) {
            ListNode min = lists[minIndex];
            lists[minIndex] = min.next;
            min.next = null;

            return min;
        }

        return null;
    }

    @Test
    public void sortList() {
        ListNode head = new ListNode(-1);
        ListNode node2 = new ListNode(5);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(0);
        head.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        ListNode result = quickSort(head);
        log.debug(logNode(result));
    }

    /**
     *  排序链表
     * 在 O(n log n) 时间复杂度和常数级空间复杂度下，对链表进行排序。
     *
     * 示例 1:
     *
     * 输入: 4->2->1->3
     * 输出: 1->2->3->4
     * 示例 2:
     *
     * 输入: -1->5->3->4->0
     * 输出: -1->0->3->4->5
     * @param head
     * @return
     */
    public ListNode sortList(ListNode head) {
        // 要求O(n log n) 的时间复杂度，使用归并排序 或者 快速排序
        if (head == null || head.next == null)
            return head;
        ListNode fast = head.next, slow = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode tmp = slow.next;
        slow.next = null;
        ListNode left = sortList(head);
        ListNode right = sortList(tmp);
        ListNode h = new ListNode(0);
        ListNode res = h;
        while (left != null && right != null) {
            if (left.val < right.val) {
                h.next = left;
                left = left.next;
            } else {
                h.next = right;
                right = right.next;
            }
            h = h.next;
        }
        h.next = left != null ? left : right;
        return res.next;

    }


    /**
     * 快速排序
     * @param head
     * @return
     */
   /* private ListNode quickSort(ListNode head) {
        // 思路 ： 选择一个 结点 是 结点 左边结点< 当前节点，右边节点> 当前节点
        ListNode currentNode = head;
        ListNode node = head;
        while (node.next != null) {

            node = node.next;
        }
        return null;
    }*/

    private ListNode quickSort(ListNode head) {
        if (head == null) {
            return head;
        }
        // 一个结点
        if (head.next == null) {
            return head;
        }
        // 两个节点
        if (head.next != null && head.next.next == null) {
            ListNode nextNode = head.next;
            if (head.val <= nextNode.val) {
                return head;
            } else {
                nextNode.next = head;
                head.next = null;
                return nextNode;
            }
        }
        ListNode node = head.next;
        ListNode lowNode = null,aNode = null;
        ListNode highNode = null,bNode = null;
        while (node != null) {

            if (node.val <= head.val) {
                if (lowNode == null) {
                    lowNode = node;
                    aNode = lowNode;
                } else {
                    lowNode.next = node;
                    lowNode = lowNode.next;
                }
            } else {
                if (highNode == null) {
                    highNode = node;
                    bNode = highNode;
                } else {
                    highNode.next = node;
                    highNode = highNode.next;
                }
            }
            node = node.next;
        }
        if (lowNode != null) {
            lowNode.next = null;
            aNode = quickSort(aNode);
        }
        if (highNode != null) {
            highNode.next = null;
            bNode = quickSort(bNode);
        }
        if (aNode != null) {
            node = aNode;
            while (aNode.next != null) {
                aNode = aNode.next;
            }
            aNode.next = head;
        } else {
            node = head;
        }

        head.next = bNode;
        return  node;
    }


    @Test
    public void reverseKGroup() {
        int k = 2;
        ListNode head = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
        ListNode node6 = new ListNode(6);
        head.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        //node5.next = node6;

        ListNode result = reverseKGroup(head,k);
        logResult(logNode(result)) ;
    }

    /**
     * 25. K 个一组翻转链表
     * 给你一个链表，每 k 个节点一组进行翻转，请你返回翻转后的链表。
     *
     * k 是一个正整数，它的值小于或等于链表的长度。
     *
     * 如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。
     *
     * 示例 :
     *
     * 给定这个链表：1->2->3->4->5
     *
     * 当 k = 2 时，应当返回: 2->1->4->3->5
     *
     * 当 k = 3 时，应当返回: 3->2->1->4->5
     *
     * 说明 :
     *
     * 你的算法只能使用常数的额外空间。
     * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
     * @param head
     * @param k
     * @return
     */
    public ListNode reverseKGroup(ListNode head, int k) {

        int index = 0;

        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode pre = dummy;  // pre 反转部分的前一个节点

        ListNode node = head;
        while (node != null) {
            ListNode next = node.next;
            if (++index % k == 0) {
                // 断掉
                node.next = null;
                ListNode startNode = pre.next;
                pre.next = reverseNode(startNode);
                startNode.next = next;
                pre = startNode;
            }
            node = next;
        }
        return dummy.next ;
    }


    public ListNode reverseKGroupDeque(ListNode head, int k) {

        Deque<ListNode> deque = new LinkedList<>();
        ListNode node = head;
        ListNode startNode = null, endNode = null;
        while (node != null) {
            ListNode next = node.next;
            deque.addLast(node);

            if (deque.size() == k) {
                // 出栈
                while (!deque.isEmpty()) {
                    ListNode dequeNode = deque.removeLast();
                    if (startNode == null) {
                        startNode = dequeNode;
                    }
                    if (endNode == null) {
                        endNode = dequeNode;
                    } else {
                        endNode.next = dequeNode;
                        endNode = dequeNode;
                    }
                }
                endNode.next = null;
            }
            node = next;

        }
        if (deque.size() > 0 && deque.size() < k) {
            // 出队
            while (!deque.isEmpty()) {
                ListNode dequeNode = deque.removeFirst();
                if (startNode == null) {
                    startNode = dequeNode;
                }
                if (endNode == null) {
                    endNode = dequeNode;
                } else {
                    endNode.next = dequeNode;
                    endNode = dequeNode;
                }
            }
            endNode.next = null;
        }


        return startNode;
    }

    /**
     * 反转链表
     * @param head
     */
    private ListNode reverseNode(ListNode head) {

        ListNode pre = null;
        ListNode next = null;
        ListNode node = head;
        while (Objects.nonNull(node)) {
            next = node.next;
            node.next = pre;
            pre = node;
            node = next;
        }

        return pre;

    }

    @Test
    public void deleteDuplicates() {
        ListNode head = new ListNode(1);
        ListNode node2 = new ListNode(1);
        ListNode node3 = new ListNode(1);
        ListNode node4 = new ListNode(1);
        ListNode node5 = new ListNode(1);
        ListNode node6 = new ListNode(1);
        head.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;

        ListNode result = deleteDuplicates(head);
        logResult(logNode(result)) ;
    }

    /**
     * 83. 删除排序链表中的重复元素
     * 给定一个排序链表，删除所有重复的元素，使得每个元素只出现一次。
     *
     * 示例 1:
     *
     * 输入: 1->1->2
     * 输出: 1->2
     * 示例 2:
     *
     * 输入: 1->1->2->3->3
     * 输出: 1->2->3
     * @param head
     * @return
     */
    public ListNode deleteDuplicates(ListNode head) {

        // 有序列表
        ListNode node = head;
        while (node != null && node.next != null) {
            ListNode next = node.next;
            if (node.val == next.val) {
                // 移除next
                node.next = next.next;
            } else {
                node = node.next;
            }
        }


        return head;
    }


    @Test
    public void reverseBetween() {
        ListNode head = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
        head.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        ListNode result = reverseBetween(head,1,5);
        logResult(logNode(result)) ;
    }

    /**
     * 92. 反转链表 II
     * 反转从位置 m 到 n 的链表。请使用一趟扫描完成反转。
     *
     * 说明:
     * 1 ≤ m ≤ n ≤ 链表长度。
     *
     * 示例:
     *
     * 输入: 1->2->3->4->5->NULL, m = 2, n = 4
     * 输出: 1->4->3->2->5->NULL
     * @param head
     * @param m
     * @param n
     * @return
     */
    public ListNode reverseBetween(ListNode head, int m, int n) {
        ListNode node = head;
        // 思路 1->2->3->4->5 => 1->3->2->4->5 => 1->4->3->2->5
        ListNode start = null;
        int index = 1;
        while (index < m) {
            start = node;
            node = node.next;
            index++;
        }
        ListNode pre = node;
        while (index < n && node.next != null) {
            ListNode next = node.next;
            node.next = next.next;
            next.next = pre;
            pre = next;
            index++;
        }
        if (start != null) {
            start.next = pre;
        }
        if (m == 1) {
            return pre;
        }
        return head;
    }


    @Test
    public void getDecimalValue( ) {
        ListNode head = new ListNode(1);
        ListNode node2 = new ListNode(0);
        ListNode node3 = new ListNode(1);
        //ListNode node4 = new ListNode(4);
        //ListNode node5 = new ListNode(5);
        head.next = node2;
        node2.next = node3;
        //node3.next = node4;
        //node4.next = node5;
        logResult(getDecimalValue(head));
    }


    /**
     * 1290. 二进制链表转整数
     * 给你一个单链表的引用结点 head。链表中每个结点的值不是 0 就是 1。已知此链表是一个整数数字的二进制表示形式。
     *
     * 请你返回该链表所表示数字的 十进制值 。
     *
     *
     *
     * 示例 1：
     *
     *
     *
     * 输入：head = [1,0,1]
     * 输出：5
     * 解释：二进制数 (101) 转化为十进制数 (5)
     * 示例 2：
     *
     * 输入：head = [0]
     * 输出：0
     * 示例 3：
     *
     * 输入：head = [1]
     * 输出：1
     * 示例 4：
     *
     * 输入：head = [1,0,0,1,0,0,1,1,1,0,0,0,0,0,0]
     * 输出：18880
     * 示例 5：
     *
     * 输入：head = [0,0]
     * 输出：0
     *
     *
     * 提示：
     *
     * 链表不为空。
     * 链表的结点总数不超过 30。
     * 每个结点的值不是 0 就是 1。
     * @param head
     * @return
     */
    public int getDecimalValue(ListNode head) {
        ListNode node = head;
        int result = 0;
        while (node != null) {
            int val = node.val;
            log.debug("val:{}",val);
            result = result*2 + val;
            log.debug("k:{}",result);
            node = node.next;
        }
        return result;
    }


    /**
     * 445. 两数相加 II
     * 给你两个 非空 链表来代表两个非负整数。数字最高位位于链表开始位置。它们的每个节点只存储一位数字。将这两数相加会返回一个新的链表。
     *
     * 你可以假设除了数字 0 之外，这两个数字都不会以零开头。
     *
     *
     *
     * 进阶：
     *
     * 如果输入链表不能修改该如何处理？换句话说，你不能对列表中的节点进行翻转。
     *
     *
     *
     * 示例：
     *
     * 输入：(7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4)
     * 输出：7 -> 8 -> 0 -> 7
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        // 1 反转链表
       /* l1 = reverseList(l1);

        l2 = reverseList(l2);
        ListNode next = null;
        int lastVal = 0;
        while (l1 != null || l2 != null || lastVal > 0) {
            int val = lastVal;
            if (l1 != null) {
                val += l1.val;
                l1 = l1.next;
            }
            if (l2 != null) {
                val += l2.val;
                l2 = l2.next;
            }
            if (val >= 10) {
                val -= 10;
                lastVal = 1;
            } else {
                lastVal = 0;
            }
            ListNode node = new ListNode(val);
            node.next = next;
            next = node;


        }



        */

        // 2 使用栈
        Deque<ListNode> deque1 = new LinkedList<>();
        Deque<ListNode> deque2 = new LinkedList<>();
        while (l1 != null) {
            deque1.addLast(l1);
            l1 = l1.next;
        }
        while (l2 != null) {
            deque2.addLast(l2);
            l2 = l2.next;
        }
        ListNode next = null;
        int lastVal = 0;
        while (!deque1.isEmpty() || !deque2.isEmpty() || lastVal > 0) {
            int val = lastVal;
            if (!deque1.isEmpty()) {
                val += deque1.removeLast().val;
            }
            if (!deque2.isEmpty()) {
                val += deque2.removeLast().val;
            }
            if (val >= 10) {
                val -= 10;
                lastVal = 1;
            } else {
                lastVal = 0;
            }
            ListNode node = new ListNode(val);
            node.next = next;
            next = node;

        }
        return next;

    }


    @Test
    public void reverseList() {
        ListNode head = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);

        head.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        head = reverseList(head);
        logNode(head) ;
    }



    private ListNode reverseList(ListNode head) {
        ListNode node = head;
        ListNode pre = head;
        while (node.next != null) {
            ListNode next = node.next;
            node.next = next.next;
            next.next = pre;

            pre = next;

        }

        return pre;
    }


    @Test
    public void removeDuplicateNodes() {
        ListNode head = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(3);
        ListNode node5 = new ListNode(2);
        ListNode node6 = new ListNode(1);
        head.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;
        head = removeDuplicateNodes(head);
        logNode(head) ;
    }

    /**
     * 面试题 02.01. 移除重复节点
     * 编写代码，移除未排序链表中的重复节点。保留最开始出现的节点。
     *
     * 示例1:
     *
     *  输入：[1, 2, 3, 3, 2, 1]
     *  输出：[1, 2, 3]
     * 示例2:
     *
     *  输入：[1, 1, 1, 1, 2]
     *  输出：[1, 2]
     * 提示：
     *
     * 链表长度在[0, 20000]范围内。
     * 链表元素在[0, 20000]范围内。
     * 进阶：
     *
     * 如果不得使用临时缓冲区，该怎么解决？
     * @param head
     * @return
     */
    public ListNode removeDuplicateNodes(ListNode head) {
        Set<Integer> set = new HashSet<>();

        if (head == null) {
            return head;
        }
        ListNode node = head;

        ListNode pre = null;
        while (node != null) {
            int val = node.val;
            ListNode next = node.next;
            if (set.contains(val)) {
                pre.next = next;
            } else {
                set.add(val);
                pre = node;
            }

            node = next;
        }

        return head;
    }


    @Test
    public void kthToLast() {
        ListNode head = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
        ListNode node6 = new ListNode(6);
        head.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;
        int k = 2;
        logResult(kthToLast(head,k)); ;
    }

    /**
     * 面试题 02.02. 返回倒数第 k 个节点
     * 实现一种算法，找出单向链表中倒数第 k 个节点。返回该节点的值。
     *
     * 注意：本题相对原题稍作改动
     *
     * 示例：
     *
     * 输入： 1->2->3->4->5 和 k = 2
     * 输出： 4
     * 说明：
     *
     * 给定的 k 保证是有效的。
     * @param head
     * @param k
     * @return
     */
    public int kthToLast(ListNode head, int k) {
        ListNode fast = head;
        while (k-- > 0) {
            fast = fast.next;
        }



        ListNode slow = head;
        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }

        return slow.val;
    }

    @Test
    public void deleteNode2() {
        ListNode head = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
        ListNode node6 = new ListNode(6);
        head.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;
        deleteNode2(head);
        logNode(head);
    }

    /**
     * 面试题 02.03. 删除中间节点
     * 实现一种算法，删除单向链表中间的某个节点（除了第一个和最后一个节点，不一定是中间节点），假定你只能访问该节点。
     *
     *
     *
     * 示例：
     *
     * 输入：单向链表a->b->c->d->e->f中的节点c
     * 结果：不返回任何数据，但该链表变为a->b->d->e->f
     * @param node
     */
    public void deleteNode2(ListNode node) {
        ListNode fast = node,slow = node;
        ListNode pre = null;
        while (fast != null && fast.next != null) {
            pre = slow;
            fast = fast.next.next;
            slow = slow.next;
        }
        log.debug("pre:{};slow:{}",pre.val,slow.val);
    }


    /**
     * 面试题18. 删除链表的节点
     * 给定单向链表的头指针和一个要删除的节点的值，定义一个函数删除该节点。
     *
     * 返回删除后的链表的头节点。
     *
     * 注意：此题对比原题有改动
     *
     * 示例 1:
     *
     * 输入: head = [4,5,1,9], val = 5
     * 输出: [4,1,9]
     * 解释: 给定你链表中值为 5 的第二个节点，那么在调用了你的函数之后，该链表应变为 4 -> 1 -> 9.
     * 示例 2:
     *
     * 输入: head = [4,5,1,9], val = 1
     * 输出: [4,5,9]
     * 解释: 给定你链表中值为 1 的第三个节点，那么在调用了你的函数之后，该链表应变为 4 -> 5 -> 9.
     *
     *
     * 说明：
     *
     * 题目保证链表中节点的值互不相同
     * 若使用 C 或 C++ 语言，你不需要 free 或 delete 被删除的节点
     * @param head
     * @param val
     * @return
     */
    public ListNode deleteNode3(ListNode head, int val) {
        ListNode node = head;
        ListNode pre = new ListNode(0);
        ListNode root = pre;
        pre.next = node;
        while (node != null) {
            if (node.val == val) {
                pre.next = node.next;
                node = null;
                break;
            }
            pre = node;
            node = node.next;
        }
        return root.next;
    }


    /**
     * 面试题52. 两个链表的第一个公共节点
     * 输入两个链表，找出它们的第一个公共节点。
     *
     * 如下面的两个链表：
     *
     *
     *
     * 在节点 c1 开始相交。
     *
     *
     *
     * 示例 1：
     *
     *
     *
     * 输入：intersectVal = 8, listA = [4,1,8,4,5], listB = [5,0,1,8,4,5], skipA = 2, skipB = 3
     * 输出：Reference of the node with value = 8
     * 输入解释：相交节点的值为 8 （注意，如果两个列表相交则不能为 0）。从各自的表头开始算起，链表 A 为 [4,1,8,4,5]，链表 B 为 [5,0,1,8,4,5]。在 A 中，相交节点前有 2 个节点；在 B 中，相交节点前有 3 个节点。
     *
     *
     * 示例 2：
     *
     *
     *
     * 输入：intersectVal = 2, listA = [0,9,1,2,4], listB = [3,2,4], skipA = 3, skipB = 1
     * 输出：Reference of the node with value = 2
     * 输入解释：相交节点的值为 2 （注意，如果两个列表相交则不能为 0）。从各自的表头开始算起，链表 A 为 [0,9,1,2,4]，链表 B 为 [3,2,4]。在 A 中，相交节点前有 3 个节点；在 B 中，相交节点前有 1 个节点。
     *
     *
     * 示例 3：
     *
     *
     *
     * 输入：intersectVal = 0, listA = [2,6,4], listB = [1,5], skipA = 3, skipB = 2
     * 输出：null
     * 输入解释：从各自的表头开始算起，链表 A 为 [2,6,4]，链表 B 为 [1,5]。由于这两个链表不相交，所以 intersectVal 必须为 0，而 skipA 和 skipB 可以是任意值。
     * 解释：这两个链表不相交，因此返回 null。
     *
     *
     * 注意：
     *
     * 如果两个链表没有交点，返回 null.
     * 在返回结果后，两个链表仍须保持原有的结构。
     * 可假定整个链表结构中没有循环。
     * 程序尽量满足 O(n) 时间复杂度，且仅用 O(1) 内存。
     * 本题与主站 160 题相同：https://leetcode-cn.com/problems/intersection-of-two-linked-lists/
     * @param headA
     * @param headB
     * @return
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        int len1 = 0, len2 = 0;
        ListNode node1 = headA;
        ListNode node2 = headB;
        while (node1 != null) {
            len1++;
            node1 = node1.next;
        }
        while (node2 != null) {
            node2 = node2.next;
            len2++;
        }
        node1 = headA;
        node2 = headB;
        if (len1 > len2) {
            int num = len1 - len2;
            while (num-- > 0) {
                node1 = node1.next;
            }
        }
        if (len2 > len1) {
            int num = len2 - len1;
            while (num-- > 0) {
                node2 = node2.next;
            }
        }
        while (Objects.nonNull(node1) && Objects.nonNull(node2) && node1 != node2 ) {
            node1 = node1.next;
            node2 = node2.next;
        }
        return node1;

    }


    /**
     * 23. 合并K个排序链表
     * 合并 k 个排序链表，返回合并后的排序链表。请分析和描述算法的复杂度。
     *
     * 示例:
     *
     * 输入:
     * [
     *   1->4->5,
     *   1->3->4,
     *   2->6
     * ]
     * 输出: 1->1->2->3->4->4->5->6
     * @param lists
     * @return
     */
    public ListNode mergeKLists2(ListNode[] lists) {

        int len = lists.length;

        return mergeKLists(lists,0,len-1);
    }

    public ListNode mergeKLists(ListNode[] lists,int start,int end) {
        if (start == end) {
            return lists[start];
        }
        int mid = (start + end) >> 1;
        ListNode left = mergeKLists(lists,start,mid);
        ListNode right = mergeKLists(lists,mid + 1,end);
        return mergeTwoLists(left,right);
    }




    private int getMinNode(ListNode[] lists) {
        int minIndex = -1,min = -1;
        for (int i = 0; i < lists.length; i++) {
            ListNode node = lists[i];
            if (Objects.nonNull(node)) {
                if (min == -1) {
                    minIndex = i;
                    min = node.val;
                } else if (node.val < min) {
                    minIndex = i;
                    min = node.val;
                }
            }

        }

        return minIndex;
    }


    /**
     * 面试题 02.06. 回文链表
     * 编写一个函数，检查输入的链表是否是回文的。
     *
     *
     *
     * 示例 1：
     *
     * 输入： 1->2
     * 输出： false
     * 示例 2：
     *
     * 输入： 1->2->2->1
     * 输出： true
     *
     *
     * 进阶：
     * 你能否用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题？
     * @param head
     * @return
     */
    public boolean isPalindrome2(ListNode head) {

        Deque<ListNode> stack = new LinkedList<>();
        int len = 0;
        ListNode node = head;
        while (node != null) {
            len++;
            node = node.next;
        }
        int count = len >> 1;

        node = head;

        while (count-- > 0) {
            stack.push(node);
            node = node.next;
        }
        if ((len & 1) == 1) {
            node = node.next;
        }

        while (!stack.isEmpty()) {
            ListNode stackNode = stack.poll();
            if (stackNode.val != node.val) {
                return false;
            }
            node = node.next;
        }


        return true;

    }


    /**
     * 82. 删除排序链表中的重复元素 II
     * 给定一个排序链表，删除所有含有重复数字的节点，只保留原始链表中 没有重复出现 的数字。
     *
     * 示例 1:
     *
     * 输入: 1->2->3->3->4->4->5
     * 输出: 1->2->5
     * 示例 2:
     *
     * 输入: 1->1->1->2->3
     * 输出: 2->3
     * @param head
     * @return
     */
    public ListNode deleteDuplicates2(ListNode head) {
        if (Objects.isNull(head)) {
            return null;
        }
        ListNode root = new ListNode(0);

        root.next = head;
        ListNode pre = root;
        ListNode node = head;

        while (Objects.nonNull(node.next)) {
            ListNode next = node.next;
            int val = node.val;
            boolean flag = false;
            while (Objects.nonNull(next) && next.val == val) {
                next = next.next;
                flag = true;
            }

            if (flag) {
                pre.next = next;
            } else {
                pre = node;
            }
            node = next;
        }

        return root.next;
    }


    /**
     * 143. 重排链表
     * 给定一个单链表 L：L0→L1→…→Ln-1→Ln ，
     * 将其重新排列后变为： L0→Ln→L1→Ln-1→L2→Ln-2→…
     *
     * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
     *
     * 示例 1:
     *
     * 给定链表 1->2->3->4, 重新排列为 1->4->2->3.
     * 示例 2:
     *
     * 给定链表 1->2->3->4->5, 重新排列为 1->5->2->4->3.
     * @param head
     */
    public void reorderList(ListNode head) {
        List<ListNode> list = new ArrayList<>();
        ListNode node = head;
        while (Objects.nonNull(node)) {
            list.add(node);
            node = node.next;
        }
        int left = 0,right = list.size() - 1;
        while (left < right) {
            list.get(left).next = list.get(right);
            if (left < (list.size() >> 1)) {
                list.get(right).next = list.get(left + 1);
            }
            left++;
            right--;
        }
        list.get(left).next = null;

    }


    /**
     * 147. 对链表进行插入排序
     * 对链表进行插入排序。
     *
     *
     * 插入排序的动画演示如上。从第一个元素开始，该链表可以被认为已经部分排序（用黑色表示）。
     * 每次迭代时，从输入数据中移除一个元素（用红色表示），并原地将其插入到已排好序的链表中。
     *
     *
     *
     * 插入排序算法：
     *
     * 插入排序是迭代的，每次只移动一个元素，直到所有元素可以形成一个有序的输出列表。
     * 每次迭代中，插入排序只从输入数据中移除一个待排序的元素，找到它在序列中适当的位置，并将其插入。
     * 重复直到所有输入数据插入完为止。
     *
     *
     * 示例 1：
     *
     * 输入: 4->2->1->3
     * 输出: 1->2->3->4
     * 示例 2：
     *
     * 输入: -1->5->3->4->0
     * 输出: -1->0->3->4->5
     * @param head
     * @return
     */
    public ListNode insertionSortList(ListNode head) {

        //ListNode node = head;

        ListNode root = new ListNode(0);
        ListNode pre = root;
        for (ListNode node = head;Objects.nonNull(node);) {
            ListNode current = node;
            node = node.next;
            if (current.val < pre.val) {
                pre = root;
            }
            while (Objects.nonNull(pre.next) && current.val > pre.next.val) {
                pre = pre.next;
            }
            current.next = pre.next;
            pre.next = current;

        }

        return root.next;
    }


    /**
     * 86. 分隔链表
     * 给定一个链表和一个特定值 x，对链表进行分隔，使得所有小于 x 的节点都在大于或等于 x 的节点之前。
     *
     * 你应当保留两个分区中每个节点的初始相对位置。
     *
     * 示例:
     *
     * 输入: head = 1->4->3->2->5->2, x = 3
     * 输出: 1->2->2->4->3->5
     * @param head
     * @param x
     * @return
     */
    public ListNode partition(ListNode head, int x) {
        ListNode listHead1 = new ListNode(-1);
        ListNode list1 = listHead1;
        ListNode listHead2 = new ListNode(-1);
        ListNode list2 = listHead2;
        ListNode node = head;
        while (Objects.nonNull(node)) {

            if (node.val < x) {
                list1.next = node;
                list1 = list1.next;
            } else {
                list2.next = node;
                list2 = list2.next;
            }
            node = node.next;
        }
        list2.next = null;

        list1.next = listHead2.next;
        return listHead1.next;
    }


    @Test
    public void reverseKGroup2() {
        int k = 2;
        ListNode head = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
        ListNode node6 = new ListNode(6);
        head.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        //node5.next = node6;

        ListNode result = reverseKGroup2(head,k);
        logResult(logNode(result)) ;
    }
    /**
     * 25. K 个一组翻转链表
     * 给你一个链表，每 k 个节点一组进行翻转，请你返回翻转后的链表。
     *
     * k 是一个正整数，它的值小于或等于链表的长度。
     *
     * 如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。
     *
     *
     *
     * 示例：
     *
     * 给你这个链表：1->2->3->4->5
     *
     * 当 k = 2 时，应当返回: 2->1->4->3->5
     *
     * 当 k = 3 时，应当返回: 3->2->1->4->5
     *
     *
     *
     * 说明：
     *
     * 你的算法只能使用常数的额外空间。
     * 你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。
     * @param head
     * @param k
     * @return
     */
    public ListNode reverseKGroup2(ListNode head, int k) {
        ListNode root = new ListNode(0);
        root.next = head;

        List<ListNode> list = new ArrayList<>();
        ListNode node = head;
        ListNode pre = root;
        while (Objects.nonNull(node)) {
            list.add(node);

            ListNode next = node.next;
            if (list.size() == k) {
                for (int i = list.size() - 1; i >= 0; i--) {
                    pre.next = list.remove(i);
                    pre = pre.next;
                }
                pre.next = next;
            }

            node = next;
        }



        return root.next;
    }
}
