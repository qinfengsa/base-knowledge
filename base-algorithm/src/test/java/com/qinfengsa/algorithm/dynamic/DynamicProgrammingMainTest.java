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
}
