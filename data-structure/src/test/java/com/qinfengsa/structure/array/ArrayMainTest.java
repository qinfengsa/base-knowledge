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
}
