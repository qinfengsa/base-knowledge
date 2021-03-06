package com.qinfengsa.algorithm.greedy;

import static com.qinfengsa.algorithm.util.LogUtils.logResult;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

/**
 * 贪心算法 test
 *
 * @author qinfengsa
 * @date 2021/5/21 10:02
 */
@Slf4j
public class GreedyMainTest {

    static GreedyMain main = new GreedyMain();

    @Test
    public void minTaps() {
        int n = 5;
        int[] ranges = {3, 4, 1, 1, 0, 0};
        logResult(main.minTaps(n, ranges));
    }

    @Test
    public void isPossible() {
        int[] target = {9, 3, 5};
        logResult(main.isPossible(target));
    }

    @Test
    public void maxPerformance() {
        int n = 3, k = 2;
        int[] speed = {2, 8, 2}, efficiency = {2, 7, 1};
        logResult(main.maxPerformance(n, speed, efficiency, k));
    }

    @Test
    public void maxBuilding() {
        int n = 6;
        int[][] restrictions = {{5, 0}, {6, 0}};
        logResult(main.maxBuilding(n, restrictions));
    }

    @Test
    public void halfQuestions() {
        int[] questions = {1, 5, 1, 3, 4, 5, 2, 5, 3, 3, 8, 6};
        logResult(main.halfQuestions(questions));
    }
}
