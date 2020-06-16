package com.qinfengsa.structure.leetcode;

import java.util.Objects;
import java.util.PriorityQueue;
import lombok.extern.slf4j.Slf4j;

/**
 * 面试题41. 数据流中的中位数
 *
 * <p>如何得到一个数据流中的中位数？如果从数据流中读出奇数个数值，那么中位数就是所有数值排序之后位于中间的数值。如果从数据流中读出偶数个数值，那么中位数就是所有数值排序之后中间两个数的平均值。
 *
 * <p>例如，
 *
 * <p>[2,3,4] 的中位数是 3
 *
 * <p>[2,3] 的中位数是 (2 + 3) / 2 = 2.5
 *
 * <p>设计一个支持以下两种操作的数据结构：
 *
 * <p>void addNum(int num) - 从数据流中添加一个整数到数据结构中。 double findMedian() - 返回目前所有元素的中位数。 示例 1：
 *
 * <p>输入： ["MedianFinder","addNum","addNum","findMedian","addNum","findMedian"]
 * [[],[1],[2],[],[3],[]] 输出：[null,null,null,1.50000,null,2.00000] 示例 2：
 *
 * <p>输入： ["MedianFinder","addNum","findMedian","addNum","findMedian"] [[],[2],[],[3],[]]
 * 输出：[null,null,2.00000,null,2.50000]
 *
 * <p>限制：
 *
 * <p>最多会对 addNum、findMedia进行 50000 次调用。 注意：本题与主站 295
 * 题相同：https://leetcode-cn.com/problems/find-median-from-data-stream/
 *
 * @author qinfengsa
 * @date 2020/06/16 06:37
 */
@Slf4j
public class MedianFinder2 {

    // 最大堆
    private PriorityQueue<Integer> leftQueue;

    // 最小堆
    private PriorityQueue<Integer> rightQueue;

    /** initialize your data structure here. */
    public MedianFinder2() {
        leftQueue = new PriorityQueue<>((a, b) -> b - a);

        rightQueue = new PriorityQueue<>();
    }

    public void addNum(int num) {
        Integer leftMid = leftQueue.peek();
        Integer rightMid = rightQueue.peek();
        if (Objects.nonNull(leftMid) && num < leftMid) {
            leftQueue.offer(num);
        } else if (Objects.nonNull(rightMid) && num > rightMid) {
            rightQueue.offer(num);
        } else if (leftQueue.size() <= rightQueue.size()) {
            leftQueue.offer(num);
        } else {
            rightQueue.offer(num);
        }
        if (leftQueue.size() - rightQueue.size() > 1) {
            rightQueue.offer(leftQueue.poll());
        }
        if (rightQueue.size() - leftQueue.size() > 1) {
            leftQueue.offer(rightQueue.poll());
        }
    }

    public double findMedian() {
        if (leftQueue.isEmpty()) {
            return 0.0;
        }
        if (leftQueue.size() != rightQueue.size()) {

            return leftQueue.size() > rightQueue.size() ? leftQueue.peek() : rightQueue.peek();
        }
        int val = leftQueue.peek() + rightQueue.peek();

        return (double) val / 2.0;
    }

    public static void main(String[] args) {
        MedianFinder2 finder = new MedianFinder2();
        finder.addNum(1);
        finder.addNum(2);
        double a = finder.findMedian();
        log.debug("a;{} -> 1.5", a);
        finder.addNum(3);
        double b = finder.findMedian();
        log.debug("b;{} -> 2", b);
    }
}
