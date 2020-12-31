package com.qinfengsa.structure.leetcode;

import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

/**
 * 715. Range 模块
 *
 * <p>Range 模块是跟踪数字范围的模块。你的任务是以一种有效的方式设计和实现以下接口。
 *
 * <p>addRange(int left, int right) 添加半开区间 [left, right)，跟踪该区间中的每个实数。添加与当前跟踪的数字部分重叠的区间时，应当添加在区间
 * [left, right) 中尚未跟踪的任何数字到该区间中。 queryRange(int left, int right) 只有在当前正在跟踪区间 [left, right)
 * 中的每一个实数时，才返回 true。 removeRange(int left, int right) 停止跟踪区间 [left, right) 中当前正在跟踪的每个实数。
 *
 * <p>示例：
 *
 * <p>addRange(10, 20): null removeRange(14, 16): null queryRange(10, 14): true （区间 [10, 14)
 * 中的每个数都正在被跟踪） queryRange(13, 15): false （未跟踪区间 [13, 15) 中像 14, 14.03, 14.17 这样的数字） queryRange(16,
 * 17): true （尽管执行了删除操作，区间 [16, 17) 中的数字 16 仍然会被跟踪）
 *
 * <p>提示：
 *
 * <p>半开区间 [left, right) 表示所有满足 left <= x < right 的实数。 对 addRange, queryRange, removeRange 的所有调用中 0
 * < left < right < 10^9。 在单个测试用例中，对 addRange 的调用总数不超过 1000 次。 在单个测试用例中，对 queryRange 的调用总数不超过 5000
 * 次。 在单个测试用例中，对 removeRange 的调用总数不超过 1000 次。
 *
 * @author qinfengsa
 * @date 2020/12/30 20:01
 */
public class RangeModule {

    // map.floorEntry() : 包含且向下取值
    // map.lowerEntry() : 不包含向下取值
    // map.ceilingEntry() : 包含且向上取值
    // map.higherEntry() ： 不包含向上取值
    // map.subMap(left, right) : 左右默认包含 区间取值
    // map.subMap(left, false, right, false) : 左右可选，区间取值
    TreeMap<Integer, Integer> range;

    public RangeModule() {
        range = new TreeMap<>();
    }

    public void addRange(int left, int right) {
        Map.Entry<Integer, Integer> leftRange = range.floorEntry(left);
        Map.Entry<Integer, Integer> rightRange = range.floorEntry(right);
        if (Objects.nonNull(rightRange) && rightRange.getValue() >= right) {
            right = rightRange.getValue();
        }
        if (Objects.nonNull(leftRange) && leftRange.getValue() >= left) {
            left = leftRange.getKey();
        }
        range.subMap(left, right).clear();
        range.put(left, right);
    }

    public boolean queryRange(int left, int right) {
        Map.Entry<Integer, Integer> leftRange = range.floorEntry(left);
        return Objects.nonNull(leftRange) && leftRange.getValue() >= right;
    }

    public void removeRange(int left, int right) {
        Map.Entry<Integer, Integer> leftRange = range.floorEntry(left);
        Map.Entry<Integer, Integer> rightRange = range.floorEntry(right);
        int oldLeft = left, oldRight = right;
        if (Objects.nonNull(leftRange) && leftRange.getValue() > left) {
            left = leftRange.getKey();
        }
        if (Objects.nonNull(rightRange)
                && rightRange.getKey() < right
                && rightRange.getValue() > right) {
            right = rightRange.getValue();
        }
        range.subMap(left, right).clear();
        if (left < oldLeft) {
            range.put(left, oldLeft);
        }
        if (oldRight < right) {
            range.put(oldRight, right);
        }
    }
}
