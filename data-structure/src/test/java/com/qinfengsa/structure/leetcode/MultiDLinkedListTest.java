package com.qinfengsa.structure.leetcode;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Objects;

/**
 * 扁平化多级双向链表
 * 您将获得一个双向链表，除了下一个和前一个指针之外，它还有一个子指针，可能指向单独的双向链表。这些子列表可能有一个或多个自己的子项，依此类推，生成多级数据结构，如下面的示例所示。
 *
 * 扁平化列表，使所有结点出现在单级双链表中。您将获得列表第一级的头部。
 * 示例:
 *
 * 输入:
 *  1---2---3---4---5---6--NULL
 *          |
 *          7---8---9---10--NULL
 *              |
 *              11--12--NULL
 *
 * 输出:
 * 1-2-3-7-8-11-12-9-10-4-5-6-NULL
 * @author: qinfengsa
 * @date: 2019/5/7 21:22
 */
@Slf4j
public class MultiDLinkedListTest {


    @Test
    public void testFlatten() {
        Node node1 = new Node(1,null,null,null);
        Node node2 = new Node(2,null,null,null);
        Node node3 = new Node(3,null,null,null);
        Node node4 = new Node(4,null,null,null);
        Node node5 = new Node(5,null,null,null);
        Node node6 = new Node(6,null,null,null);
        Node node7 = new Node(7,null,null,null);
        Node node8 = new Node(8,null,null,null);
        Node node9 = new Node(9,null,null,null);
        Node node10 = new Node(10,null,null,null);
        Node node11 = new Node(11,null,null,null);
        Node node12 = new Node(12,null,null,null);
        node1.next = node2;

        node2.prev = node1;node2.next = node3;

        node3.prev = node2;node3.next = node4; node3.child = node7;

        node4.prev = node3;node4.next = node5;

        node5.prev = node4;node5.next = node6;

        node6.prev = node5;

        node7.next = node8;

        node8.prev = node7;node8.next = node9; node8.child = node11;

        node9.prev = node8;node9.next = node10;

        node10.prev = node9;

        //node11.next = node12;

        //node12.prev = node11;

        Node node = flatten(node1);
        logNode(node);
    }




    public Node flatten(Node head) {
        Node node = head;

        if (Objects.nonNull(node)) {
            while (Objects.nonNull(node)) {
                Node nextNode = node.next;
                if (Objects.nonNull(node.child)) {

                    Node cNode = node.child;
                    node.next = cNode;
                    cNode.prev = node;
                    node.child = null;
                    Node newNode = getLast(cNode);
                    if (Objects.nonNull(newNode)) {
                        newNode.next = nextNode;
                    }
                    if (Objects.nonNull(nextNode) ){
                        nextNode.prev = newNode;
                    }


                }
                node = node.next;
            }

        }
        return head;
    }

    private Node getLast(Node head) {
        Node node = head;

        while (Objects.nonNull(node.next)) {
            node = node.next;
        }
        return node;
    }

    private Node getChild(Node head) {
        Node node = head;

        while (Objects.nonNull(node.next)) {

            Node nextNode = node.next;
            if (Objects.nonNull(node.child)) {

                Node cNode = node.child;
                node.next = cNode;
                cNode.prev = node;
                node.child = null;
                Node newNode = getChild(cNode);
                newNode.next = nextNode;
                nextNode.prev = newNode;

            }
            node = nextNode;


        }


        return node;
    }


    public void logNode(Node head) {
        Node node = head;
        StringBuilder sb = new StringBuilder();
        while (Objects.nonNull(node)) {
            sb.append(node.val);
            sb.append(",");
            node = node.next;
        }
        log.info("node: {}",sb);

    }


    /**
     * 扁平化多级双向链表结点
     * @author qinfengsa
     * @date 2019/5/7 21:23
     */
    class Node {
        public int val;
        public Node prev;
        public Node next;
        public Node child;

        public Node() {}

        public Node(int _val,Node _prev,Node _next,Node _child) {
            val = _val;
            prev = _prev;
            next = _next;
            child = _child;
        }
    }
}
