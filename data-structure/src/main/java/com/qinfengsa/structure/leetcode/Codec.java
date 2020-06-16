package com.qinfengsa.structure.leetcode;

import static com.qinfengsa.structure.util.LogUtils.logResult;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Objects;
/**
 * 面试题37. 序列化二叉树
 *
 * <p>请实现两个函数，分别用来序列化和反序列化二叉树。
 *
 * <p>示例:
 *
 * <p>你可以将以下二叉树：
 *
 * <p>1 / \ 2 3 / \ 4 5
 *
 * <p>序列化为 "[1,2,3,null,null,4,5]" 注意：本题与主站 297
 * 题相同：https://leetcode-cn.com/problems/serialize-and-deserialize-binary-tree/
 *
 * @author qinfengsa
 * @date 2020/06/16 05:32
 */
public class Codec {

    /**
     * 序列化
     *
     * @param root
     * @return
     */
    public String serialize(TreeNode root) {
        // 使用前序遍历序列化
        StringBuilder sb = new StringBuilder();
        serializeCode(root, sb);
        return sb.toString();
    }

    private void serializeCode(TreeNode root) {
        Deque<TreeNode> stack = new LinkedList<>();
        TreeNode node = root;
        StringBuilder sb = new StringBuilder();
        while (Objects.nonNull(node) || !stack.isEmpty()) {
            if (sb.length() > 0) {
                sb.append(",");
            }

            if (Objects.isNull(node)) {
                sb.append("#");
                if (!stack.isEmpty()) {
                    node = stack.pop();
                    continue;
                }
            } else {
                sb.append(node.val);
            }
            if (Objects.isNull(node)) {
                continue;
            }

            stack.push(node.right);
            node = node.left;
        }

        logResult(sb.toString());
    }

    private void serializeCode(TreeNode root, StringBuilder sb) {
        if (sb.length() > 0) {
            sb.append(",");
        }
        if (Objects.isNull(root)) {
            sb.append("#");
            return;
        }

        sb.append(root.val);
        serializeCode(root.left, sb);
        serializeCode(root.right, sb);
    }

    /**
     * 反序列化
     *
     * @param data
     * @return
     */
    public TreeNode deserialize(String data) {

        String[] nodes = data.split(",");
        TreeNode treeNode = deserializeCode(nodes);
        return treeNode;
    }

    private int index = 0;

    private TreeNode deserializeCode(String[] nodes) {
        if (index == nodes.length) {
            return null;
        }
        String val = nodes[index++];
        if (Objects.equals(val, "#")) {
            return null;
        }
        TreeNode treeNode = new TreeNode(Integer.parseInt(val));
        treeNode.left = deserializeCode(nodes);
        treeNode.right = deserializeCode(nodes);
        return treeNode;
    }

    public static void main(String[] args) {
        deserializeTest();
    }

    private static void serializeTest() {
        // 5,4,11,7,#,#,2,#,#,#,8,13,#,#,4,#,1,#,#
        TreeNode root = new TreeNode(5);
        TreeNode node2 = new TreeNode(4);
        TreeNode node3 = new TreeNode(8);
        TreeNode node4 = new TreeNode(11);
        TreeNode node5 = new TreeNode(13);
        TreeNode node6 = new TreeNode(4);
        TreeNode node7 = new TreeNode(7);
        TreeNode node8 = new TreeNode(2);
        TreeNode node9 = new TreeNode(1);
        root.left = node2;
        root.right = node3;
        node2.left = node4;

        node3.left = node5;
        node3.right = node6;

        node4.left = node7;
        node4.right = node8;

        node6.right = node9;
        Codec tree = new Codec();
        String data = tree.serialize(root);
        logResult(data);
    }

    private static void deserializeTest() {
        String data = "5,4,11,7,#,#,2,#,#,#,8,13,#,#,4,#,1,#,#";
        Codec tree = new Codec();
        TreeNode treeNode = tree.deserialize(data);
    }
}
