package com.qinfengsa.structure.array;

import static com.qinfengsa.structure.util.LogUtils.logResult;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

/**
 * 数组
 *
 * @author qinfengsa
 * @date 2021/5/10 12:55
 */
@Slf4j
public class ArrayMainTest {

    static ArrayMain main = new ArrayMain();

    @Test
    public void processTasks() {
        int[][] tasks = {{1, 3, 2}, {2, 5, 3}, {5, 6, 2}};
        logResult(main.processTasks(tasks));
    }

    @Test
    public void shortestPath() {
        int[][] grid = {{0, 1, 1}, {1, 1, 1}, {1, 0, 0}};
        int k = 1;
        logResult(main.shortestPath(grid, k));
    }

    @Test
    public void rotateTheBox() {
        char[][] box = {
            {'#', '#', '*', '.', '*', '.'},
            {'#', '#', '#', '*', '.', '.'},
            {'#', '#', '#', '.', '#', '.'}
        };
        logResult(main.rotateTheBox(box));
    }

    @Test
    public void subsetXORSum() {
        int[] nums = {3, 4, 5, 6, 7, 8};
        logResult(main.subsetXORSum(nums));
    }

    @Test
    public void minFlips() {
        int[][] mat = {{0}};
        logResult(main.minFlips(mat));
    }

    @Test
    public void minSpeedOnTime() {
        int[] dist = {1, 3, 2};
        double hour = 1.9;
        logResult(main.minSpeedOnTime(dist, hour));
    }

    @Test
    public void minCost() {
        int[][] grid = {{1, 1, 1, 1}, {2, 2, 2, 2}, {1, 1, 1, 1}, {2, 2, 2, 2}};
        logResult(main.minCost(grid));
    }

    @Test
    public void maxSatisfaction() {
        int[] satisfaction = {-2, 5, -1, 0, 3, -3};
        logResult(main.maxSatisfaction(satisfaction));
    }

    @Test
    public void getBiggestThree() {
        int[][] grid = {
            {3, 4, 5, 1, 3},
            {3, 3, 4, 2, 3},
            {20, 30, 200, 40, 10},
            {1, 5, 5, 4, 1},
            {4, 3, 2, 2, 5}
        };
        int[] result = main.getBiggestThree(grid);
        log.debug("result:{}", result);
    }

    @Test
    public void assignTasks() {
        int[] servers = {3, 3, 2}, tasks = {1, 2, 3, 2, 1, 2};
        // int[] servers = {10, 63, 95, 16, 85, 57, 83, 95, 6, 29, 71}, tasks = {70, 31, 83, 15, 32,
        // 67, 98, 65, 56, 48, 38, 90, 5};
        log.debug("result:{}", main.assignTasks(servers, tasks));
    }

    @Test
    public void kthSmallest() {
        int[][] mat = {{1, 1, 10}, {2, 2, 9}};
        int k = 7;
        logResult(main.kthSmallest(mat, k));
    }

    @Test
    public void busiestServers() {
        int k = 3;
        int[] arrival = {1, 2, 3, 4, 5}, load = {5, 2, 3, 3, 3};
        logResult(main.busiestServers(k, arrival, load));
    }

    @Test
    public void findRotation() {
        int[][] mat = {{0, 0}, {1, 1}}, target = {{0, 1}, {1, 0}};
        logResult(main.findRotation(mat, target));
    }

    @Test
    public void countSubgraphsForEachDiameter() {
        int n = 3;
        int[][] edges = {{1, 2}, {2, 3}};
        log.debug("result:{}", main.countSubgraphsForEachDiameter(n, edges));
    }

    @Test
    public void areConnected() {
        int n = 5, threshold = 1;
        int[][] queries = {{4, 5}, {4, 5}, {3, 2}, {2, 3}, {3, 4}};
        logResult(main.areConnected(n, threshold, queries));
    }

    @Test
    public void matrixRankTransform() {
        int[][] matrix = {
            {-2, -35, -32, -5, -30, 33, -12},
            {7, 2, -43, 4, -49, 14, 17},
            {4, 23, -6, -15, -24, -17, 6},
            {-47, 20, 39, -26, 9, -44, 39},
            {-50, -47, 44, 43, -22, 33, -36},
            {-13, 34, 49, 24, 23, -2, -35},
            {-40, 43, -22, -19, -4, 23, -18}
        };
        logResult(main.matrixRankTransform(matrix));
    }

    @Test
    public void createSortedArray() {
        int[] instructions = {1, 3, 3, 3, 2, 4, 2, 1, 2};
        logResult(main.createSortedArray(instructions));
    }

    @Test
    public void isCovered() {
        int[][] ranges = {
            {25, 42}, {7, 14}, {2, 32}, {25, 28}, {39, 49}, {1, 50}, {29, 45}, {18, 47}
        };
        int left = 15, right = 38;
        logResult(main.isCovered(ranges, left, right));
    }

    @Test
    public void largestMagicSquare() {
        int[][] grid = {{7, 1, 4, 5, 6}, {2, 5, 1, 6, 4}, {1, 5, 4, 3, 2}, {1, 2, 7, 3, 4}};
        logResult(main.largestMagicSquare(grid));
    }

    @Test
    public void mergeTriplets() {
        int[][] triplets = {{3, 4, 5}, {4, 5, 6}};
        int[] target = {3, 2, 5};
        logResult(main.mergeTriplets(triplets, target));
    }

    @Test
    public void minInterval() {
        int[][] intervals = {{2, 3}, {2, 5}, {1, 8}, {20, 25}};
        int[] queries = {2, 19, 5, 22};
        int[] result = main.minInterval(intervals, queries);
        log.debug("result:{}", result);
    }

    @Test
    public void countSubIslands() {
        int[][]
                grid1 =
                        {
                            {1, 0, 1, 0, 1},
                            {1, 1, 1, 1, 1},
                            {0, 0, 0, 0, 0},
                            {1, 1, 1, 1, 1},
                            {1, 0, 1, 0, 1}
                        },
                grid2 =
                        {
                            {0, 0, 0, 0, 0},
                            {1, 1, 1, 1, 1},
                            {0, 1, 0, 1, 0},
                            {0, 1, 0, 1, 0},
                            {1, 0, 0, 0, 1}
                        };
        logResult(main.countSubIslands(grid1, grid2));
    }

    @Test
    public void minWastedSpace() {
        int[] packages = {3, 5, 8, 10, 11, 12};
        int[][] boxes = {{12}, {11, 9}, {10, 5, 14}};
        logResult(main.minWastedSpace(packages, boxes));
    }

    @Test
    public void largestArea() {
        String[] grid = {"110", "231", "221"};
        logResult(main.largestArea(grid));
    }

    @Test
    public void minDifference() {
        int[] nums = {4, 5, 2, 2, 7, 10};
        int[][] queries = {{2, 3}, {0, 2}, {0, 5}, {3, 5}};
        int[] result = main.minDifference(nums, queries);
        log.debug("result:{}", result);
    }

    @Test
    public void findPeakGrid() {
        int[][] mat = {{10, 20, 15}, {21, 30, 14}, {7, 16, 32}};
        int[] result = main.findPeakGrid(mat);
        log.debug("result:{}", result);
    }

    @Test
    public void canBeIncreasing() {
        int[] nums = {1, 2, 3};
        logResult(main.canBeIncreasing(nums));
    }

    @Test
    public void maxAlternatingSum() {
        int[] nums = {6, 2, 1, 2, 4, 5};
        logResult(main.maxAlternatingSum(nums));
    }
}
