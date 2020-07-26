package com.qinfengsa.structure.leetcode;

/**
 * @author: qinfengsa
 * @date: 2019/5/13 19:00
 */
public class TreeNode {

    int val;

    TreeNode left;

    TreeNode right;

    TreeNode(int x) {
        val = x;
    }

    @Override
    public String toString() {
        return "TreeNode{" + "val=" + val + '}';
    }
}
