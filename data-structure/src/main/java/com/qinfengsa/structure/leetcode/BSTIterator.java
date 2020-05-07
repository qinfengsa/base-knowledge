package com.qinfengsa.structure.leetcode;

import com.qinfengsa.structure.leetcode.TreeNode;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

/**
 * 二叉搜索树迭代器
 * 实现一个二叉搜索树迭代器。你将使用二叉搜索树的根节点初始化迭代器。
 *
 * 调用 next() 将返回二叉搜索树中的下一个最小的数。
 *
 *
 *
 * 示例：
 *
 * BSTIterator iterator = new BSTIterator(root);
 * iterator.next();    // 返回 3
 * iterator.next();    // 返回 7
 * iterator.hasNext(); // 返回 true
 * iterator.next();    // 返回 9
 * iterator.hasNext(); // 返回 true
 * iterator.next();    // 返回 15
 * iterator.hasNext(); // 返回 true
 * iterator.next();    // 返回 20
 * iterator.hasNext(); // 返回 false
 *
 *
 * 提示：
 *
 * next() 和 hasNext() 操作的时间复杂度是 O(1)，并使用 O(h) 内存，其中 h 是树的高度。
 * 你可以假设 next() 调用总是有效的，也就是说，当调用 next() 时，BST 中至少存在一个下一个最小的数。
 * @author: qinfengsa
 * @date: 2019/5/15 13:10
 */
public class BSTIterator {

    Queue<Integer> element = new LinkedList<>();

    /**
     * 构造方法
     * @param root
     */
    public BSTIterator(TreeNode root) {

        // 中序遍历，筛入队列
        inorderTraversal(root);

    }

    private void inorderTraversal(TreeNode root) {

        if (Objects.isNull(root)) {
            return;
        }
        inorderTraversal(root.left);
        element.offer(root.val);
        inorderTraversal(root.right);

    }


    public int next() {
        if (element.isEmpty()) {
            return -1;
        }
        return element.poll();
    }


    public boolean hasNext() {
        if (element.isEmpty()) {
            return false;
        }
        if (Objects.nonNull(element.peek())) {
            return true;
        }
        return false;
    }
}
