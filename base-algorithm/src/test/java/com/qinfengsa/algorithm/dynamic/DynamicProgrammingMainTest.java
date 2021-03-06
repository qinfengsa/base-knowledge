package com.qinfengsa.algorithm.dynamic;

import static com.qinfengsa.algorithm.util.LogUtils.logResult;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

/**
 * 动态规划
 *
 * @author qinfengsa
 * @date 2021/5/10 9:31
 */
@Slf4j
public class DynamicProgrammingMainTest {

    DynamicProgrammingMain main = new DynamicProgrammingMain();

    @Test
    public void escapeMaze() {
        List<List<String>> maze = new ArrayList<>();
        maze.add(Arrays.asList(".#.", "#.."));
        maze.add(Arrays.asList("...", ".#."));
        maze.add(Arrays.asList(".##", ".#."));
        maze.add(Arrays.asList("..#", ".#."));

        logResult(main.escapeMaze(maze));
    }

    @Test
    public void palindromePartition() {
        String s = "aabbc";
        int k = 3;
        logResult(main.palindromePartition(s, k));
    }

    @Test
    public void minFallingPathSum() {
        int[][] arr = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        logResult(main.minFallingPathSum(arr));
    }

    @Test
    public void pathsWithMaxScore() {
        List<String> board = Arrays.asList("E23", "2X2", "12S");
        int[] result = main.pathsWithMaxScore(board);
        log.debug("result:{}", result);
    }

    @Test
    public void minInsertions() {
        String s = "nn";
        logResult(main.minInsertions(s));
    }

    @Test
    public void minDifficulty() {
        int[] jobDifficulty = {6, 5, 4, 3, 2, 1};
        int d = 2;
        logResult(main.minDifficulty(jobDifficulty, d));
    }

    @Test
    public void maxStudents() {
        char[][] seats = {
            {'#', '.', '#', '#', '.', '#'},
            {'.', '#', '#', '#', '#', '.'},
            {'#', '.', '#', '#', '.', '#'}
        };
        logResult(main.maxStudents(seats));
    }

    @Test
    public void largestMultipleOfThree() {
        int[] digits = {8, 6, 7, 1, 0};
        logResult(main.largestMultipleOfThree(digits));
    }

    @Test
    public void numberOfArrays() {
        String s = "1234567890";
        int k = 90;
        logResult(main.numberOfArrays(s, k));
    }

    @Test
    public void numOfArrays() {
        // int n = 50, m = 100, k = 25;
        int n = 2, m = 3, k = 1;
        logResult(main.numOfArrays(n, m, k));
    }

    @Test
    public void constrainedSubsetSum() {
        int[] nums = {10, -2, -10, -5, 20};
        int k = 2;
        logResult(main.constrainedSubsetSum(nums, k));
    }

    @Test
    public void numberWays() {
        List<List<Integer>> hats = new ArrayList<>();
        // [[3,4],[4,5],[5]]
        hats.add(Arrays.asList(3, 4));
        hats.add(Arrays.asList(4, 5));
        hats.add(Arrays.asList(5));
        logResult(main.numberWays(hats));
    }

    @Test
    public void cherryPickup() {
        int[][] grid = {
            {0, 8, 7, 10, 9, 10, 0, 9, 6},
            {8, 7, 10, 8, 7, 4, 9, 6, 10},
            {8, 1, 1, 5, 1, 5, 5, 1, 2},
            {9, 4, 10, 8, 8, 1, 9, 5, 0},
            {4, 3, 6, 10, 9, 2, 4, 8, 10},
            {7, 3, 2, 8, 3, 3, 5, 9, 8},
            {1, 2, 6, 5, 6, 2, 0, 10, 0}
        };
        logResult(main.cherryPickup(grid));
    }

    @Test
    public void minDistance() {
        int[] houses = {7, 4, 6, 1};
        int k = 1;
        logResult(main.minDistance(houses, k));
    }

    @Test
    public void maximumRequests() {
        int n = 5;
        int[][] requests = {{0, 1}, {1, 0}, {0, 1}, {1, 2}, {2, 0}, {3, 4}};
        logResult(main.maximumRequests(n, requests));
    }

    @Test
    public void numWays() {
        String[] words = {"abba", "baab"};
        String target = "bab";
        logResult(main.numWays(words, target));
    }

    @Test
    public void canDistribute() {
        int[] nums = {1, 1, 1, 1, 1}, quantity = {2, 3};
        logResult(main.canDistribute(nums, quantity));
    }

    @Test
    public void maxHeight() {
        int[][] cuboids = {{50, 45, 20}, {95, 37, 53}, {45, 23, 12}};
        logResult(main.maxHeight(cuboids));
    }

    @Test
    public void getMaxGridHappiness() {
        int m = 2, n = 2, introvertsCount = 4, extrovertsCount = 0;
        logResult(main.getMaxGridHappiness(m, n, introvertsCount, extrovertsCount));
    }

    @Test
    public void boxDelivering() {
        int[][] boxes = {{2, 4}, {2, 5}, {3, 1}, {3, 2}, {3, 7}, {3, 1}, {4, 4}, {1, 3}, {5, 2}};
        int portsCount = 5, maxBoxes = 5, maxWeight = 7;
        logResult(main.boxDelivering(boxes, portsCount, maxBoxes, maxWeight));
    }

    @Test
    public void findGoodStrings() {
        int n = 3;
        String s1 = "szc", s2 = "zyi", evil = "p";
        logResult(main.findGoodStrings(n, s1, s2, evil));
    }

    @Test
    public void maxGroupNumber() {
        int[] tiles = {2, 2, 2, 3, 4, 1, 3};
        logResult(main.maxGroupNumber(tiles));
    }

    @Test
    public void guardCastle() {
        String[] grid = {"S.C.P#P.", ".....#.S"};
        logResult(main.guardCastle(grid));
    }

    @Test
    public void earliestAndLatest() {
        int n = 5, firstPlayer = 1, secondPlayer = 5;
        int[] result = main.earliestAndLatest(n, firstPlayer, secondPlayer);
        log.debug("result:{}", result);
    }

    @Test
    public void twoEggDrop() {
        int n = 100;
        logResult(main.twoEggDrop(n));
    }

    @Test
    public void superEggDrop() {
        int k = 2, n = 6;
        logResult(main.superEggDrop(k, n));
    }
}
