package com.qinfengsa.structure.leetcode;

import java.util.Arrays;
import java.util.TreeMap;
import lombok.extern.slf4j.Slf4j;

/**
 * 1825. 求出 MK 平均值
 *
 * <p>给你两个整数 m 和 k ，以及数据流形式的若干整数。你需要实现一个数据结构，计算这个数据流的 MK 平均值 。
 *
 * <p>MK 平均值 按照如下步骤计算：
 *
 * <p>如果数据流中的整数少于 m 个，MK 平均值 为 -1 ，否则将数据流中最后 m 个元素拷贝到一个独立的容器中。 从这个容器中删除最小的 k 个数和最大的 k 个数。
 * 计算剩余元素的平均值，并 向下取整到最近的整数 。 请你实现 MKAverage 类：
 *
 * <p>MKAverage(int m, int k) 用一个空的数据流和两个整数 m 和 k 初始化 MKAverage 对象。 void addElement(int num)
 * 往数据流中插入一个新的元素 num 。 int calculateMKAverage() 对当前的数据流计算并返回 MK 平均数 ，结果需 向下取整到最近的整数 。
 *
 * <p>示例 1：
 *
 * <p>输入： ["MKAverage", "addElement", "addElement", "calculateMKAverage", "addElement",
 * "calculateMKAverage", "addElement", "addElement", "addElement", "calculateMKAverage"] [[3, 1],
 * [3], [1], [], [10], [], [5], [5], [5], []] 输出： [null, null, null, -1, null, 3, null, null, null,
 * 5]
 *
 * <p>解释： MKAverage obj = new MKAverage(3, 1); obj.addElement(3); // 当前元素为 [3] obj.addElement(1); //
 * 当前元素为 [3,1] obj.calculateMKAverage(); // 返回 -1 ，因为 m = 3 ，但数据流中只有 2 个元素 obj.addElement(10); //
 * 当前元素为 [3,1,10] obj.calculateMKAverage(); // 最后 3 个元素为 [3,1,10] // 删除最小以及最大的 1 个元素后，容器为 [3] // [3]
 * 的平均值等于 3/1 = 3 ，故返回 3 obj.addElement(5); // 当前元素为 [3,1,10,5] obj.addElement(5); // 当前元素为
 * [3,1,10,5,5] obj.addElement(5); // 当前元素为 [3,1,10,5,5,5] obj.calculateMKAverage(); // 最后 3 个元素为
 * [5,5,5] // 删除最小以及最大的 1 个元素后，容器为 [5] // [5] 的平均值等于 5/1 = 5 ，故返回 5
 *
 * <p>提示：
 *
 * <p>3 <= m <= 105 1 <= k*2 < m 1 <= num <= 105 addElement 与 calculateMKAverage 总操作次数不超过 105 次。
 *
 * @author qinfengsa
 * @date 2021/6/10 9:48
 */
@Slf4j
public class MKAverage {

    private final int m, k;

    private final int[] nums;

    private int idx = 0, sum = 0;
    private TreeMap<Integer, Integer> leftMap = new TreeMap<>(),
            midMap = new TreeMap<>(),
            rightMap = new TreeMap<>();

    /**
     * 用一个空的数据流和两个整数 m 和 k 初始化 MKAverage 对象。
     *
     * @param m
     * @param k
     */
    public MKAverage(int m, int k) {
        this.m = m;
        this.k = k;
        this.nums = new int[m];
    }

    /**
     * 往数据流中插入一个新的元素 num
     *
     * @param num
     */
    public void addElement(int num) {
        int index = idx % m;
        int oldVal = nums[index];
        nums[index] = num;
        idx++;
        if (idx == m) {
            int[] tmp = Arrays.copyOf(nums, m);
            Arrays.sort(tmp);
            for (int i = 0; i < m; i++) {
                int val = tmp[i];
                if (i < k) {
                    addKey(leftMap, val);
                } else if (i >= m - k) {
                    addKey(rightMap, val);
                } else {
                    addKey(midMap, val);
                    sum += val;
                }
            }
        } else if (idx > m) {
            if (midMap.containsKey(oldVal)) {
                removeKey(midMap, oldVal);
                sum -= oldVal;
            } else if (leftMap.containsKey(oldVal)) {
                removeKey(leftMap, oldVal);
                // 从中间 移动一个到 leftMap
                int midFirst = midMap.firstKey();
                removeKey(midMap, midFirst);
                sum -= midFirst;
                addKey(leftMap, midFirst);
            } else {
                removeKey(rightMap, oldVal);
                // 从中间 移动一个到 rightMap
                int midLast = midMap.lastKey();
                removeKey(midMap, midLast);
                sum -= midLast;
                addKey(rightMap, midLast);
            }
            if (num <= rightMap.firstKey() && num >= leftMap.lastKey()) {
                addKey(midMap, num);
                sum += num;
            } else if (num < leftMap.lastKey()) {
                // 从左边 移动一个到 midMap
                addKey(leftMap, num);
                int leftLast = leftMap.lastKey();
                removeKey(leftMap, leftLast);
                addKey(midMap, leftLast);
                sum += leftLast;
            } else {
                // 从右边 移动一个到 midMap
                addKey(rightMap, num);
                int rightFirst = rightMap.firstKey();
                removeKey(rightMap, rightFirst);
                addKey(midMap, rightFirst);
                sum += rightFirst;
            }
        }
    }

    private void addKey(TreeMap<Integer, Integer> treeMap, int key) {
        int count = treeMap.getOrDefault(key, 0);
        treeMap.put(key, count + 1);
    }

    private void removeKey(TreeMap<Integer, Integer> treeMap, int key) {
        int count = treeMap.getOrDefault(key, 0);
        if (count == 1) {
            treeMap.remove(key);
        } else {
            treeMap.put(key, count - 1);
        }
    }

    /**
     * 对当前的数据流计算并返回 MK 平均数 ，结果需 向下取整到最近的整数
     *
     * @return
     */
    public int calculateMKAverage() {
        if (idx < m) {
            return -1;
        }
        int midCount = m - (k << 1);
        if (midCount == 1) {
            return sum;
        }

        return sum / midCount;
    }

    public static void main(String[] args) {
        MKAverage average = new MKAverage(6, 1);
        average.addElement(3);
        average.addElement(1);
        average.addElement(12);
        average.addElement(5);
        average.addElement(3);
        average.addElement(4);
        log.debug("num1:{}", average.calculateMKAverage());
    }
}
