package com.qinfengsa.structure.leetcode;

import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;
import lombok.extern.slf4j.Slf4j;

/**
 * 面试题 10.10. 数字流的秩
 *
 * <p>假设你正在读取一串整数。每隔一段时间，你希望能找出数字 x 的秩(小于或等于 x 的值的个数)。请实现数据结构和算法来支持这些操作，也就是说：
 *
 * <p>实现 track(int x) 方法，每读入一个数字都会调用该方法；
 *
 * <p>实现 getRankOfNumber(int x) 方法，返回小于或等于 x 的值的个数。
 *
 * <p>注意：本题相对原题稍作改动
 *
 * <p>示例:
 *
 * <p>输入: ["StreamRank", "getRankOfNumber", "track", "getRankOfNumber"] [[], [1], [0], [0]] 输出:
 * [null,0,null,1] 提示：
 *
 * <p>x <= 50000 track 和 getRankOfNumber 方法的调用次数均不超过 2000 次
 *
 * @author qinfengsa
 * @date 2020/06/13 10:15
 */
@Slf4j
public class StreamRank {

    private TreeMap<Integer, Integer> treeMap;

    public StreamRank() {
        treeMap = new TreeMap<>();
    }

    public void track(int x) {
        treeMap.compute(x, (k, v) -> Objects.isNull(v) ? 1 : v + 1);
    }

    public int getRankOfNumber(int x) {
        if (treeMap.isEmpty() || treeMap.firstKey() > x) {
            return 0;
        }
        int sum = 0;
        // 小于等于x的个数之和
        for (Map.Entry<Integer, Integer> entry : treeMap.headMap(x, true).entrySet()) {
            sum += entry.getValue();
        }
        return sum;
    }
}
