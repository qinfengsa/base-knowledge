package com.qinfengsa.structure.leetcode;

import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

/**
 * 旋转链表 给定一个链表，旋转链表，将链表每个节点向右移动 k 个位置，其中 k 是非负数。
 *
 * <p>示例 1:
 *
 * <p>输入: 1->2->3->4->5->NULL, k = 2 输出: 4->5->1->2->3->NULL 解释: 向右旋转 1 步: 5->1->2->3->4->NULL 向右旋转
 * 2 步: 4->5->1->2->3->NULL 示例 2:
 *
 * <p>输入: 0->1->2->NULL, k = 4 输出: 2->0->1->NULL 解释: 向右旋转 1 步: 2->0->1->NULL 向右旋转 2 步: 1->2->0->NULL
 * 向右旋转 3 步: 0->1->2->NULL 向右旋转 4 步: 2->0->1->NULL
 *
 * @author: qinfengsa
 * @date: 2019/5/7 23:40
 */
@Slf4j
public class RotateRightTest {

    @Test
    public void rotateRightTest() {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        /*ListNode node0 = new ListNode(0);
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        node1.next = node2;
        node0.next = node1;*/

        ListNode a = rotateRight(node1, 5);
        logNode(a);
    }

    public ListNode rotateRight(ListNode head, int k) {
        ListNode node = head;
        if (Objects.isNull(node)) {
            return null;
        }
        if (Objects.isNull(node.next)) {
            return node;
        }
        if (k == 0) {
            return head;
        }
        // 把最后一位移动到头结点，然后循环k次
        int size = getSize(node);
        if (k == size) {
            return head;
        }
        k = k % size;

        int num = size - k;

        node = head;
        ListNode preNode = null;
        while (num > 0) {
            preNode = node;
            node = node.next;
            num--;
        }
        if (Objects.nonNull(preNode)) {
            preNode.next = null;
        }
        ListNode newhead = node;
        while (Objects.nonNull(node.next)) {
            node = node.next;
        }
        node.next = head;

        return newhead;
    }

    private int getSize(ListNode head) {
        ListNode node = head;
        int size = 0;
        while (Objects.nonNull(node)) {

            node = node.next;
            size++;
        }
        return size;
    }

    public void logNode(ListNode head) {
        ListNode node = head;
        StringBuilder sb = new StringBuilder();
        while (Objects.nonNull(node)) {
            sb.append(node.val);
            sb.append(",");
            node = node.next;
        }
        log.info("node: {}", sb);
    }

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
