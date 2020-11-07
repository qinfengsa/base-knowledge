package com.qinfengsa.structure.leetcode;

import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;
import lombok.extern.slf4j.Slf4j;

/**
 * 919. 完全二叉树插入器
 *
 * <p>完全二叉树是每一层（除最后一层外）都是完全填充（即，节点数达到最大）的，并且所有的节点都尽可能地集中在左侧。
 *
 * <p>设计一个用完全二叉树初始化的数据结构 CBTInserter，它支持以下几种操作：
 *
 * <p>CBTInserter(TreeNode root) 使用头节点为 root 的给定树初始化该数据结构； CBTInserter.insert(int v)
 * 向树中插入一个新节点，节点类型为 TreeNode，值为 v 。使树保持完全二叉树的状态，并返回插入的新节点的父节点的值； CBTInserter.get_root() 将返回树的头节点。
 *
 * <p>示例 1：
 *
 * <p>输入：inputs = ["CBTInserter","insert","get_root"], inputs = [[[1]],[2],[]] 输出：[null,1,[1,2]] 示例
 * 2：
 *
 * <p>输入：inputs = ["CBTInserter","insert","insert","get_root"], inputs =
 * [[[1,2,3,4,5,6]],[7],[8],[]] 输出：[null,3,4,[1,2,3,4,5,6,7,8]]
 *
 * <p>提示：
 *
 * <p>最初给定的树是完全二叉树，且包含 1 到 1000 个节点。 每个测试用例最多调用 CBTInserter.insert 操作 10000 次。 给定节点或插入节点的每个值都在 0 到
 * 5000 之间。
 *
 * @author qinfengsa
 * @date 2020/11/07 10:42
 */
@Slf4j
public class CBTInserter {
    private TreeNode root;

    private Queue<TreeNode> queue = new LinkedList<>();

    private TreeNode lastParent;

    public CBTInserter(TreeNode root) {
        this.root = root;
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            // 当前节点 左节点为 null 或 右节点为null
            if (Objects.isNull(node.left)) {
                lastParent = node;
                break;
            } else {
                queue.offer(node.left);
            }
            if (Objects.isNull(node.right)) {
                lastParent = node;
                break;
            } else {
                queue.offer(node.right);
            }
        }
    }

    public int insert(int v) {
        int val = lastParent.val;
        if (Objects.isNull(lastParent.left)) {
            lastParent.left = new TreeNode(v);
            queue.offer(lastParent.left);
        } else if (Objects.isNull(lastParent.right)) {
            lastParent.right = new TreeNode(v);
            queue.offer(lastParent.right);
            lastParent = queue.poll();
        }
        return val;
    }

    public TreeNode get_root() {
        return this.root;
    }

    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        node1.left = node2;
        CBTInserter obj = new CBTInserter(node1);
        int val1 = obj.insert(3);
        log.debug("val1 : {}", val1);
        int val2 = obj.insert(4);
        log.debug("val2 : {}", val2);
        int val3 = obj.insert(5);
        log.debug("val3 : {}", val3);
        int val4 = obj.insert(6);
        log.debug("val4 : {}", val4);
        obj.get_root();
    }
}
