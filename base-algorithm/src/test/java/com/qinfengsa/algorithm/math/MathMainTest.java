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

    @Test
    public void kthSmallestPath() {
        int[] destination = {2, 3};
        int k = 3;
        logResult(main.kthSmallestPath(destination, k));
    }

    @Test
    public void getMinDistSum() {
        int[][] positions = {{0, 1}, {1, 0}, {1, 2}, {2, 1}};
        logResult(main.getMinDistSum(positions));
    }

    @Test
    public void countDifferentSubsequenceGCDs() {
        int[] nums = {5, 15, 40, 5, 6};
        logResult(main.countDifferentSubsequenceGCDs(nums));
    }

    @Test
    public void sumOfFlooredPairs() {
        int[] nums = {7, 7, 7, 7, 7, 7, 7};
        logResult(main.sumOfFlooredPairs(nums));
    }

    @Test
    public void minRecSize() {
        int[][] lines = {{2, 3}, {3, 0}, {4, 1}};
        logResult(main.minRecSize(lines));
    }

    @Test
    public void leastMinutes() {
        int n = 2;
        logResult(main.leastMinutes(n));
    }
}
