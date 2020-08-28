package com.qinfengsa.structure.leetcode;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * 1261. 在受污染的二叉树中查找元素
 *
 * <p>给出一个满足下述规则的二叉树：
 *
 * <p>root.val == 0 如果 treeNode.val == x 且 treeNode.left != null，那么 treeNode.left.val == 2 * x + 1
 * 如果 treeNode.val == x 且 treeNode.right != null，那么 treeNode.right.val == 2 * x + 2
 * 现在这个二叉树受到「污染」，所有的 treeNode.val 都变成了 -1。
 *
 * <p>请你先还原二叉树，然后实现 FindElements 类：
 *
 * <p>FindElements(TreeNode* root) 用受污染的二叉树初始化对象，你需要先把它还原。 bool find(int target) 判断目标值 target
 * 是否存在于还原后的二叉树中并返回结果。
 *
 * <p>示例 1：
 *
 * <p>输入： ["FindElements","find","find"] [[[-1,null,-1]],[1],[2]] 输出： [null,false,true] 解释：
 * FindElements findElements = new FindElements([-1,null,-1]); findElements.find(1); // return False
 * findElements.find(2); // return True 示例 2：
 *
 * <p>输入： ["FindElements","find","find","find"] [[[-1,-1,-1,-1,-1]],[1],[3],[5]] 输出：
 * [null,true,true,false] 解释： FindElements findElements = new FindElements([-1,-1,-1,-1,-1]);
 * findElements.find(1); // return True findElements.find(3); // return True findElements.find(5);
 * // return False 示例 3：
 *
 * <p>输入： ["FindElements","find","find","find","find"] [[[-1,null,-1,-1,null,-1]],[2],[3],[4],[5]]
 * 输出： [null,true,false,false,true] 解释： FindElements findElements = new
 * FindElements([-1,null,-1,-1,null,-1]); findElements.find(2); // return True findElements.find(3);
 * // return False findElements.find(4); // return False findElements.find(5); // return True
 *
 * <p>提示：
 *
 * <p>TreeNode.val == -1 二叉树的高度不超过 20 节点的总数在 [1, 10^4] 之间 调用 find() 的总次数在 [1, 10^4] 之间 0 <= target
 * <= 10^6
 *
 * @author qinfengsa
 * @date 2020/08/28 10:00
 */
public class FindElements {
    TreeNode root;

    Set<Integer> set;

    public FindElements(TreeNode root) {
        this.root = root;
        this.set = new HashSet<>();
        if (Objects.isNull(root)) {
            return;
        }
        root.val = 0;
        set.add(0);
        dfs(root);
    }

    private void dfs(TreeNode parent) {
        if (Objects.isNull(parent)) {
            return;
        }
        int val = parent.val;
        if (Objects.nonNull(parent.left)) {
            int leftVal = 2 * val + 1;
            parent.left.val = leftVal;
            set.add(leftVal);
            dfs(parent.left);
        }
        if (Objects.nonNull(parent.right)) {
            int rightVal = 2 * val + 2;
            parent.left.val = rightVal;
            set.add(rightVal);
            dfs(parent.right);
        }
    }

    public boolean find(int target) {

        return set.contains(target);
    }
}
