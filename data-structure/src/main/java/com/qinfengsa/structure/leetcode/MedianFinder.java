package com.qinfengsa.structure.leetcode;


import lombok.extern.slf4j.Slf4j;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 数据流的中位数
 * 中位数是有序列表中间的数。如果列表长度是偶数，中位数则是中间两个数的平均值。
 *
 * 例如，
 *
 * [2,3,4] 的中位数是 3
 *
 * [2,3] 的中位数是 (2 + 3) / 2 = 2.5
 *
 * 设计一个支持以下两种操作的数据结构：
 *
 * void addNum(int num) - 从数据流中添加一个整数到数据结构中。
 * double findMedian() - 返回目前所有元素的中位数。
 * 示例：
 *
 * addNum(1)
 * addNum(2)
 * findMedian() -> 1.5
 * addNum(3)
 * findMedian() -> 2
 * 进阶:
 *
 * 如果数据流中所有整数都在 0 到 100 范围内，你将如何优化你的算法？
 * 如果数据流中 99% 的整数都在 0 到 100 范围内，你将如何优化你的算法？
 * @author: qinfengsa
 * @date: 2020/2/16 08:43
 */
@Slf4j
public class MedianFinder {

    private int size;

    // 思路： 构建两个堆，一个最大堆（存放前n/2个数）一个最小堆（存放后n/2个数）
    /**
     * 最小堆 存放后n/2个数
     */
    private Queue<Integer> minQueue;

    /**
     * 最大堆 存放前n/2个数
     */
    private Queue<Integer> maxQueue;

    /** initialize your data structure here. */
    public MedianFinder() {
        minQueue = new PriorityQueue<>();
        maxQueue = new PriorityQueue<>((a,b)->b-a);
    }

    public void addNum(int num) {
        size++;
        Integer leftMid = maxQueue.peek();
        Integer rightMid = minQueue.peek();
        if (leftMid != null && num < leftMid) {
            maxQueue.add(num);
        } else if (rightMid != null && num > rightMid) {
            minQueue.add(num);
        } else if (maxQueue.size() <= minQueue.size()) {
            maxQueue.add(num);
        } else {
            minQueue.add(num);
        }
        if (maxQueue.size() - minQueue.size() > 1) {
            minQueue.add(maxQueue.poll());
        }
        if (minQueue.size() - maxQueue.size() > 1) {
            maxQueue.add(minQueue.poll());
        }
    }

    public double findMedian() {
        if (size == 0) {
            return 0.0;
        }
        Integer leftMid = maxQueue.peek();
        Integer rightMid = minQueue.peek();
        double result = 0.0;
        if (maxQueue.size() > minQueue.size() ){
            result = leftMid.doubleValue();
        } else if (maxQueue.size() < minQueue.size()){
            result = rightMid.doubleValue();
        } else {
            result = (leftMid.doubleValue() + rightMid.doubleValue())/2.0;
        }
        return result;
    }

    public static void main(String[] args) {
        MedianFinder finder = new MedianFinder();
        finder.addNum(1);
        finder.addNum(2);
        double a = finder.findMedian();
        log.debug("a;{} -> 1.5",a);
        finder.addNum(3);
        double b = finder.findMedian();
        log.debug("b;{} -> 2",b);
    }
}
