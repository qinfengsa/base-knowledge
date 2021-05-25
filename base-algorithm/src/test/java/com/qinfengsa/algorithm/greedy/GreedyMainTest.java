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
}
