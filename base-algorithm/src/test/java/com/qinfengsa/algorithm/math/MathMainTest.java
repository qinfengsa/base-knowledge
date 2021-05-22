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
}
