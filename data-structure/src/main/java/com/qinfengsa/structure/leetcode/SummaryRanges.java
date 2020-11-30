package com.qinfengsa.structure.leetcode;

import com.qinfengsa.structure.util.LogUtils;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

/**
 * 352. 将数据流变为多个不相交区间
 *
 * <p>给定一个非负整数的数据流输入 a1，a2，…，an，…，将到目前为止看到的数字总结为不相交的区间列表。
 *
 * <p>例如，假设数据流中的整数为 1，3，7，2，6，…，每次的总结为：
 *
 * <p>[1, 1] [1, 1], [3, 3] [1, 1], [3, 3], [7, 7] [1, 3], [7, 7] [1, 3], [6, 7]
 *
 * <p>进阶： 如果有很多合并，并且与数据流的大小相比，不相交区间的数量很小，该怎么办?
 *
 * @author qinfengsa
 * @date 2020/11/30 7:24
 */
public class SummaryRanges {

    private TreeMap<Integer, Node> treeMap;

    private Node head;

    /** Initialize your data structure here. */
    public SummaryRanges() {
        treeMap = new TreeMap<>();
        head = new Node(-2, -2);
        treeMap.put(-2, head);
    }

    public void addNum(int val) {
        if (treeMap.size() == 1) {
            Node node = new Node(val, val);
            treeMap.put(val, node);
            head.next = node;
            return;
        }
        // 左边 肯定存在
        Map.Entry<Integer, Node> left = treeMap.floorEntry(val);
        // 右边
        Map.Entry<Integer, Node> right = treeMap.higherEntry(val);
        // 放到最右边
        Node leftNode = left.getValue();
        if (val <= leftNode.end) {
            return;
        }
        if (leftNode.end + 1 == val) {
            leftNode.end = val;
        } else {
            Node node = new Node(val, val);
            treeMap.put(val, node);
            leftNode.next = node;

            leftNode = node;
        }
        // 判断右边节点能不能合并
        if (Objects.nonNull(right)) {
            Node rightNode = right.getValue();
            if (leftNode.end + 1 == rightNode.start) {
                leftNode.end = rightNode.end;
                treeMap.remove(right.getKey());
                leftNode.next = rightNode.next;
            } else {
                leftNode.next = rightNode;
            }
        }
    }

    public int[][] getIntervals() {
        int len = treeMap.keySet().size() - 1;
        int[][] result = new int[len][2];
        Node node = head.next;
        for (int i = 0; i < len; i++) {
            result[i][0] = node.start;
            result[i][1] = node.end;
            node = node.next;
        }
        return result;
    }

    class Node {
        int start, end;
        Node next;

        public Node(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    public static void main(String[] args) {
        /*SummaryRanges ranges = new SummaryRanges();
        ranges.addNum(1);
        LogUtils.logResult(ranges.getIntervals());
        ranges.addNum(3);
        LogUtils.logResult(ranges.getIntervals());
        ranges.addNum(7);
        LogUtils.logResult(ranges.getIntervals());
        ranges.addNum(2);
        LogUtils.logResult(ranges.getIntervals());
        ranges.addNum(6);
        LogUtils.logResult(ranges.getIntervals());*/
        SummaryRanges ranges = new SummaryRanges();
        ranges.addNum(6);
        LogUtils.logResult(ranges.getIntervals());
        ranges.addNum(6);
        LogUtils.logResult(ranges.getIntervals());
        ranges.addNum(0);
        LogUtils.logResult(ranges.getIntervals());
        ranges.addNum(4);
        LogUtils.logResult(ranges.getIntervals());
        ranges.addNum(8);
        LogUtils.logResult(ranges.getIntervals());

        ranges.addNum(7);
        LogUtils.logResult(ranges.getIntervals());
        ranges.addNum(6);
        LogUtils.logResult(ranges.getIntervals());
        ranges.addNum(4);
        LogUtils.logResult(ranges.getIntervals());
        ranges.addNum(7);
        LogUtils.logResult(ranges.getIntervals());
        ranges.addNum(5);
        LogUtils.logResult(ranges.getIntervals());
    }
}
