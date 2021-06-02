package com.qinfengsa.algorithm.math;

import static com.qinfengsa.algorithm.util.LogUtils.logResult;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

/**
 * 数学 相关 test
 *
 * @author qinfengsa
 * @date 2021/5/21 10:28
 */
@Slf4j
public class MathMainTest {

    static MathMain main = new MathMain();

    @Test
    public void maxValueAfterReverse() {
        int[] nums = {2, 3, 1, 5, 4};
        logResult(main.maxValueAfterReverse(nums));
    }

    @Test
    public void numPoints() {
        int[][] points = {{4, -4}, {-2, 0}, {0, 2}, {-3, 1}, {2, 3}, {2, 4}, {1, 1}};
        int r = 3;
        logResult(main.numPoints(points, r));
    }
}
