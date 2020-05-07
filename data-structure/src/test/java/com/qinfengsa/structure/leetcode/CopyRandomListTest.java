package com.qinfengsa.structure.leetcode;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 复制带随机指针的链表
 * 给定一个链表，每个节点包含一个额外增加的随机指针，该指针可以指向链表中的任何节点或空节点。
 *
 * 要求返回这个链表的深拷贝。
 * 示例：
 * 输入：
 * {"$id":"1","next":{"$id":"2","next":null,"random":{"$ref":"2"},"val":2},"random":{"$ref":"2"},"val":1}
 *
 * 解释：
 * 节点 1 的值是 1，它的下一个指针和随机指针都指向节点 2 。
 * 节点 2 的值是 2，它的下一个指针指向 null，随机指针指向它自己。
 *
 * @author: qinfengsa
 * @date: 2019/5/7 23:21
 */
@Slf4j
public class CopyRandomListTest {


    @Test
    public void testCopy() {

    }




    public Node copyRandomList(Node head) {
        if (Objects.isNull(head)) {
            return null;
        }

        Map<Node,Node> map = new HashMap<>();


        Node newHead = new Node(0,null,null);
        Node newNode = newHead;
        Node node = head;

        while (Objects.nonNull(node)) {
            newNode.next = new Node(node.val,null,null);;

            newNode = newNode.next;
            node = node.next;
            map.put(node,newNode);
        }
        Node node2 = head;

        newNode = newHead.next;
        while (Objects.nonNull(node2)) {
            Node random = node2.random;
            if (Objects.nonNull(random)) {
                newNode.random = map.get(random);
            }
            node2 = node2.next;
            newNode = newNode.next;
        }


        return newHead.next;
    }

    private Node getNodeByVal(Node head,int val) {
        Node node = head;
        while (Objects.nonNull(node)) {
            if (node.val == val) {
                return node;
            }
            node = node.next;
        }
        return null;
    }


    class Node {
        public int val;
        public Node next;
        public Node random;

        public Node() {}

        public Node(int _val,Node _next,Node _random) {
            val = _val;
            next = _next;
            random = _random;
        }
    }
}
