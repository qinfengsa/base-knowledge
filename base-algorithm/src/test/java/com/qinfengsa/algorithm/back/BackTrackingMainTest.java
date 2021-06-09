package com.qinfengsa.algorithm.back;

import static com.qinfengsa.algorithm.util.LogUtils.logResult;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

/**
 * 回溯算法
 *
 * @author qinfengsa
 * @date 2021/05/18 21:45
 */
@Slf4j
public class BackTrackingMainTest {

    static BackTrackingMain main = new BackTrackingMain();

    @Test
    public void getProbability() {
        int[] balls = {6, 6, 6, 6, 6, 6};
        logResult(main.getProbability(balls));
    }

    @Test
    public void minimumIncompatibility() {
        int[] nums = {6, 3, 8, 1, 3, 1, 2, 2};
        int k = 4;
        logResult(main.minimumIncompatibility(nums, k));
    }
}
