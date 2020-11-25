package com.qinfengsa.algorithm.leetcode;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

/**
 * LeetCode题目
 *
 * @author: qinfengsa
 * @date: 2019/3/30 00:30
 */
@Slf4j
public class LeetCodeTest {

    @Test
    public void happyNumTest() {
        int n = 2;
        LeetCode code = new LeetCode();
        boolean a = code.isHappy(n);
        System.out.println(a);
    }

    @Test
    public void getSumTest() {
        LeetCode code = new LeetCode();
        int a = code.getSum(-2, 4);
        System.out.println(a);
    }

    @Test
    public void fizzBuzzTest() {
        int n = 15;
        LeetCode code = new LeetCode();
        System.out.println(code.fizzBuzz(15));
    }

    @Test
    public void canCompleteCircuitTest() {
        int[] gas = {1, 2, 3, 4, 5};
        int[] cost = {3, 4, 5, 1, 2};
        LeetCode code = new LeetCode();
        int index = code.canCompleteCircuit(gas, cost);
        System.out.println(index);
    }
}
