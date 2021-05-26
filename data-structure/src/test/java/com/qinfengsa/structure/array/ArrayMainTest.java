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
}
