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
}
