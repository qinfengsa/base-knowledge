package com.qinfengsa.structure.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Queue;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

/**
 * N叉树
 *
 * @author: qinfengsa
 * @date: 2019/5/15 13:41
 */
@Slf4j
public class NAryTree {

    /**
     * 给定一个 N 叉树，返回其节点值的前序遍历。
     *
     * <p>例如，给定一个 3叉树 :
     *
     * <p>返回其前序遍历: [1,3,5,6,2,4]。
     *
     * <p>说明: 递归法很简单，你可以使用迭代法完成此题吗?
     *
     * @param root
     * @return
     */
    public List<Integer> preorder(NaNode root) {
        List<Integer> list = new ArrayList<>();
        preOrderTree(root, list);
        return list;
    }

    /**
     * N叉数前序遍历
     *
     * @param root
     * @param list
     */
    private void preOrderTree(NaNode root, List<Integer> list) {
        if (Objects.isNull(root)) {
            return;
        }

        list.add(root.val);
        if (Objects.nonNull(root.children) && root.children.size() > 0) {
            for (NaNode child : root.children) {
                preOrderTree(child, list);
            }
        }
    }

    /**
     * 给定一个 N 叉树，返回其节点值的后序遍历。
     *
     * @param root
     * @return
     */
    public List<Integer> postorder(NaNode root) {
        List<Integer> list = new ArrayList<>();
        postOrderTree(root, list);
        return list;
    }

    /**
     * N叉数后序遍历
     *
     * @param root
     * @param list
     */
    private void postOrderTree(NaNode root, List<Integer> list) {
        if (Objects.isNull(root)) {
            return;
        }
        if (Objects.nonNull(root.children) && root.children.size() > 0) {
            for (NaNode child : root.children) {
                postOrderTree(child, list);
            }
        }
        list.add(root.val);
    }

    /**
     * 给定一个 N 叉树，返回其节点值的层序遍历。 (即从左到右，逐层遍历)。
     *
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder(NaNode root) {
        List<List<Integer>> list = new ArrayList<>();
        if (Objects.isNull(root)) {
            return list;
        }
        Queue<NaNode> queue = new LinkedList<>();

        queue.offer(root);
        List<Integer> layerList = new ArrayList<>();
        NaNode last = null;
        NaNode layerLast = root;
        while (!queue.isEmpty()) {
            NaNode node = queue.poll();
            layerList.add(node.val);
            if (Objects.nonNull(node.children) && node.children.size() > 0) {
                for (NaNode child : node.children) {
                    // 子结点入队
                    queue.offer(child);
                    last = child;
                }
            }
            if (node == layerLast) {
                layerLast = last;
                list.add(layerList);
                layerList = new ArrayList<>();
            }
        }

        return list;
    }

    /**
     * 给定一个 N 叉树，找到其最大深度。
     *
     * <p>最大深度是指从根节点到最远叶子节点的最长路径上的节点总数。
     *
     * @param root
     * @return
     */
    public int maxDepth(NaNode root) {
        if (Objects.isNull(root)) {
            return 0;
        }
        List<NaNode> childs = root.children;
        int maxHeight = 0;

        if (Objects.isNull(childs) || childs.size() == 0) {
            return 1;
        }
        for (NaNode node : childs) {
            int height = maxDepth(node);
            if (height > maxHeight) {
                maxHeight = height;
            }
        }
        return maxHeight + 1;
    }

    @Test
    public void maxDepthTest() {
        NaNode root = new NaNode(1, null);

        NaNode node2 = new NaNode(3, null);
        NaNode node3 = new NaNode(2, null);
        NaNode node4 = new NaNode(4, null);

        List<NaNode> list1 = new ArrayList<>();
        list1.add(node2);
        list1.add(node3);
        list1.add(node4);
        root.children = list1;

        NaNode node5 = new NaNode(5, null);
        NaNode node6 = new NaNode(6, null);
        List<NaNode> list2 = new ArrayList<>();
        list2.add(node5);
        list2.add(node6);
        node2.children = list2;

        NAryTree tree = new NAryTree();

        int result = tree.maxDepth(root);

        log.debug("result:{}", result);
    }

    @Test
    public void levelOrderTest() {
        NaNode root = new NaNode(1, null);

        NaNode node2 = new NaNode(3, null);
        NaNode node3 = new NaNode(2, null);
        NaNode node4 = new NaNode(4, null);

        List<NaNode> list1 = new ArrayList<>();
        list1.add(node2);
        list1.add(node3);
        list1.add(node4);
        root.children = list1;

        NaNode node5 = new NaNode(5, null);
        NaNode node6 = new NaNode(6, null);
        List<NaNode> list2 = new ArrayList<>();
        list2.add(node5);
        list2.add(node6);
        node2.children = list2;

        NAryTree tree = new NAryTree();

        List<List<Integer>> result = tree.levelOrder(root);

        log.debug("result:{}", result);
    }
}

class NaNode {
    public int val;
    public List<NaNode> children;

    public NaNode() {}

    public NaNode(int _val, List<NaNode> _children) {
        val = _val;
        children = _children;
    }
}
;
