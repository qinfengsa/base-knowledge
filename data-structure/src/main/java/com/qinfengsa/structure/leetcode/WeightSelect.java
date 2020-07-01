package com.qinfengsa.structure.leetcode;

import java.util.Random;
import lombok.extern.slf4j.Slf4j;

/**
 * 528. 按权重随机选择
 *
 * <p>给定一个正整数数组 w ，其中 w[i] 代表位置 i 的权重，请写一个函数 pickIndex ，它可以随机地获取位置 i，选取位置 i 的概率与 w[i] 成正比。
 *
 * <p>例如，给定一个值 [1，9] 的输入列表，当我们从中选择一个数字时，很有可能 10 次中有 9 次应该选择数字 9 作为答案。
 *
 * <p>示例 1：
 *
 * <p>输入： ["Solution","pickIndex"] [[[1]],[]] 输出：[null,0] 示例 2：
 *
 * <p>输入： ["Solution","pickIndex","pickIndex","pickIndex","pickIndex","pickIndex"]
 * [[[1,3]],[],[],[],[],[]] 输出：[null,0,1,1,1,0]
 *
 * <p>输入语法说明：
 *
 * <p>输入是两个列表：调用成员函数名和调用的参数。Solution 的构造函数有一个参数，即数组 w。pickIndex 没有参数。输入参数是一个列表，即使参数为空，也会输入一个 [] 空列表。
 *
 * <p>提示：
 *
 * <p>1 <= w.length <= 10000 1 <= w[i] <= 10^5 pickIndex 将被调用不超过 10000 次
 *
 * @author qinfengsa
 * @date 2020/07/01 11:47
 */
@Slf4j
public class WeightSelect {

    int[] nums;

    Random random;

    int weightSum;

    public WeightSelect(int[] w) {
        nums = new int[w.length];
        int sum = 0;
        for (int i = 0; i < w.length; i++) {
            sum += w[i];
            nums[i] = sum;
        }
        random = new Random();
        weightSum = sum;
    }

    public int pickIndex() {
        int target = random.nextInt(weightSum);
        log.debug("target:{}", target);
        int index = getIndex(target);
        log.debug("index:{}", index);
        return index;
    }

    private int getIndex(int target) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int mid = (left + right) >> 1;
            if (target >= nums[mid]) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }

    public static void main(String[] args) {
        int[] w = {1, 3, 4};
        WeightSelect weightSelect = new WeightSelect(w);
        log.debug("1:{}", weightSelect.pickIndex());
        log.debug("2:{}", weightSelect.pickIndex());
        log.debug("3:{}", weightSelect.pickIndex());
        log.debug("4:{}", weightSelect.pickIndex());
        log.debug("5:{}", weightSelect.pickIndex());
        log.debug("6:{}", weightSelect.pickIndex());
        log.debug("7:{}", weightSelect.pickIndex());
        log.debug("8:{}", weightSelect.pickIndex());
        log.debug("9:{}", weightSelect.pickIndex());
        log.debug("10:{}", weightSelect.pickIndex());
    }
}
