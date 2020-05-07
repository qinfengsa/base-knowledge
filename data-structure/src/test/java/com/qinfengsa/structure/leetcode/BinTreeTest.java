package com.qinfengsa.structure.leetcode;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

/**
 * 填充每个节点的下一个右侧节点指针
 * @author: qinfengsa
 * @date: 2019/5/14 09:41
 */
@Slf4j
public class BinTreeTest {


    /**
     * 给定一个完美二叉树，其所有叶子节点都在同一层，每个父节点都有两个子节点。二叉树定义如下：
     *
     * struct Node {
     *   int val;
     *   Node *left;
     *   Node *right;
     *   Node *next;
     * }
     * 填充它的每个 next 指针，让这个指针指向其下一个右侧节点。如果找不到下一个右侧节点，则将 next 指针设置为 NULL。
     *
     * 初始状态下，所有 next 指针都被设置为 NULL。
     *
     *
     *
     * 示例：
     *
     *
     *
     * 输入：{"$id":"1","left":{"$id":"2","left":{"$id":"3","left":null,"next":null,"right":null,"val":4},"next":null,"right":{"$id":"4","left":null,"next":null,"right":null,"val":5},"val":2},"next":null,"right":{"$id":"5","left":{"$id":"6","left":null,"next":null,"right":null,"val":6},"next":null,"right":{"$id":"7","left":null,"next":null,"right":null,"val":7},"val":3},"val":1}
     *
     * 输出：{"$id":"1","left":{"$id":"2","left":{"$id":"3","left":null,"next":{"$id":"4","left":null,"next":{"$id":"5","left":null,"next":{"$id":"6","left":null,"next":null,"right":null,"val":7},"right":null,"val":6},"right":null,"val":5},"right":null,"val":4},"next":{"$id":"7","left":{"$ref":"5"},"next":null,"right":{"$ref":"6"},"val":3},"right":{"$ref":"4"},"val":2},"next":null,"right":{"$ref":"7"},"val":1}
     *
     * 解释：给定二叉树如图 A 所示，你的函数应该填充它的每个 next 指针，以指向其下一个右侧节点，如图 B 所示。
     *
     *
     * 提示：
     *
     * 你只能使用常量级额外空间。
     * 使用递归解题也符合要求，本题中递归程序占用的栈空间不算做额外的空间复杂度。
     * @param root
     * @return
     */
    public Node connect(Node root) {

        if (Objects.isNull(root)) {
            return null;
        }

        Node last = root;
        Node layerLast = null;
        // 使用队列完成二叉树的按层遍历
        Queue<Node> queue = new LinkedList<>();
        // 根结点入队
        queue.offer(root);
        while (!queue.isEmpty()) {
            // 取出队首结点 node
            Node node = queue.poll();

            node.next = queue.peek();

            // 左右子结点分别入队
            if (Objects.nonNull(node.left)) {
                queue.offer(node.left);
                layerLast = node.left;
            }
            if (Objects.nonNull(node.right)) {
                queue.offer(node.right);
                layerLast = node.right;
            }
            // layerLast 记录每行最后的结点
            // last 记录每行最后的结点
            if (last == node) {
                last = layerLast;
                node.next = null;
            }
        }

        return root;
    }

    /**
     * 递归实现
     * @param root
     * @return
     */
    public Node connect2(Node root) {
        if (Objects.isNull(root)) {
            return null;
        }
        Node node = root;
        Node leftNode = root.left;
        Node rightNode = root.right;

        if (Objects.nonNull(rightNode)) {
            rightNode.next = getNext(root,rightNode);
        }
        if (Objects.nonNull(leftNode)) {
            leftNode.next = getNext(root,leftNode);

        }
        // 先右后左
        connect2(rightNode);
        connect2(leftNode);


        return root;
    }

    private Node getNext(Node parent,Node node) {
        Node next = null;
        if (node == parent.left) {
            next = parent.right;
        }

        while (Objects.isNull(next) && Objects.nonNull(parent.next)) {
            parent = parent.next;
            next = Objects.nonNull(parent.left) ? parent.left :parent.right;
        }
        log.debug("next:{} ",Objects.nonNull(next) ? next.val : -1);
        return next;
    }

    @Test
    public void connectTest() {
        Node node4 = new Node(4,null,null,null);
        Node node5 = new Node(5,null,null,null);
        Node node6 = new Node(6,null,null,null);
        Node node7 = new Node(7,null,null,null);

        Node node2 = new Node(2,node4,node5,null);
        Node node3 = new Node(3,node6,node7,null);
        Node root = new Node(1,node2,node3,null);

        Node newRoot = connect2(root);

        log.debug("result:{} ",newRoot );
    }

    @Test
    public void connectTest2() {

        Node node5 = new Node(5,null,null,null);

        Node node7 = new Node(7,null,null,null);
        Node node8 = new Node(8,null,null,null);

        Node node4 = new Node(4,node7,null,null);
        Node node6 = new Node(6,null,node8,null);
        Node node2 = new Node(2,node4,node5,null);
        Node node3 = new Node(3,null,node6,null);
        Node root = new Node(1,node2,node3,null);

        Node newRoot = connect2(root);

        log.debug("result:{} ",newRoot );
    }


    @Test
    public void connectTest3() {

        Node node13 = new Node(8,null,null,null);
        Node node12 = new Node(8,null,null,null);
        Node node11 = new Node(1,node12,node13,null);
        Node node10 = new Node(9,null,null,null);
        Node node9 = new Node(3,node10,node11,null);



        Node node8 = new Node(7,null,null,null);
        Node node7 = new Node(0,node8,null,null);

        Node node6 = new Node(1,null,null,null);
        Node node5 = new Node(7,node6,node7,null);

        Node node4 = new Node(2,null,null,null);
        Node node3 = new Node(0,node4,null,null);
        Node node2 = new Node(1,node3,node5,null);

        Node root = new Node(2,node2,node9,null);

        Node newRoot = connect2(root);

        log.debug("result:{} ",newRoot );
    }


}


class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {}

    public Node(int _val,Node _left,Node _right,Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
}