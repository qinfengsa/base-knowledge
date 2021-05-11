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
        /*maze.add(Arrays.asList(".#.", "#.."));
        maze.add(Arrays.asList("...", ".#."));
        maze.add(Arrays.asList(".##", ".#."));
        maze.add(Arrays.asList("..#", ".#."));*/
        /* maze.add(Arrays.asList("...", "...", "..."));
        maze.add(Arrays.asList(".##", "###", "##."));

        maze.add(Arrays.asList(".##", "###", "##."));
        maze.add(Arrays.asList(".##", "###", "##."));
        maze.add(Arrays.asList(".##", "###", "##."));
        maze.add(Arrays.asList(".##", "###", "##."));
        maze.add(Arrays.asList(".##", "###", "##."));*/

        /*maze.add(Arrays.asList("....###.", "###.#.##", ".##..##."));
        maze.add(Arrays.asList(".#####..", "##.####.", "##.####."));
        maze.add(Arrays.asList("....####", "###..###", "##..##.."));
        maze.add(Arrays.asList(".####...", "######.#", "###.##.."));
        maze.add(Arrays.asList("..###.##", "########", "#######."));
        maze.add(Arrays.asList("...##.##", "###.####", ".#.#.#.."));
        maze.add(Arrays.asList(".######.", "#.#.....", "#.#.#.#."));
        maze.add(Arrays.asList(".###.##.", "##.#####", "###.##.."));
        maze.add(Arrays.asList("..#.####", "#####.##", "##.###.."));
        maze.add(Arrays.asList(".#.###.#", ".#######", "#####.#."));
        maze.add(Arrays.asList(".######.", "####....", ".##..##."));
        maze.add(Arrays.asList(".###.#..", "###.#.#.", "#####.#."));
        maze.add(Arrays.asList(".###.###", "###.####", "....###."));
        maze.add(Arrays.asList(".###.##.", "########", "#####.#."));
        maze.add(Arrays.asList(".###.###", "##.####.", ".###...."));
        maze.add(Arrays.asList(".#.#.##.", ".##.####", "#####.#."));
        maze.add(Arrays.asList("..#.####", "#.##....", "####...."));
        maze.add(Arrays.asList("..#.##.#", "#.##..#.", "###.###."));
        maze.add(Arrays.asList("..##.#.#", ".##.#..#", ".####..."));
        maze.add(Arrays.asList(".##..##.", "########", "#####.#."));
        maze.add(Arrays.asList(".####.##", "#.#...##", "#.##..#."));
        maze.add(Arrays.asList("..#.####", "######.#", "###.###."));
        maze.add(Arrays.asList(".#..#..#", "###..##.", "#..#...."));*/

        maze.add(Arrays.asList(".##..####", ".#######."));
        maze.add(Arrays.asList("..######.", "########."));
        maze.add(Arrays.asList(".#####.##", ".#######."));
        maze.add(Arrays.asList(".#..###.#", "########."));
        maze.add(Arrays.asList(".########", "########."));
        maze.add(Arrays.asList(".######.#", "####.###."));
        maze.add(Arrays.asList(".#####.##", "#####.#.."));
        maze.add(Arrays.asList(".##.####.", "##.#####."));
        maze.add(Arrays.asList(".########", "#####.##."));
        maze.add(Arrays.asList(".#.######", "#.##.###."));
        maze.add(Arrays.asList(".########", "###.#.#.."));
        maze.add(Arrays.asList(".########", "########."));
        maze.add(Arrays.asList(".####.##.", "##.##...."));
        maze.add(Arrays.asList(".#######.", "###.#.##."));
        maze.add(Arrays.asList(".####.###", "###.####."));
        maze.add(Arrays.asList(".######.#", "##.####.."));
        maze.add(Arrays.asList(".##.#####", "##.###.#."));
        maze.add(Arrays.asList(".####.###", "##.#####."));
        maze.add(Arrays.asList(".##.##..#", ".#.#####."));
        maze.add(Arrays.asList(".###.####", "##.#..##."));
        maze.add(Arrays.asList(".####.#.#", "##.#####."));
        maze.add(Arrays.asList(".####.###", "####.###."));
        maze.add(Arrays.asList(".########", "#######.."));
        maze.add(Arrays.asList(".#####.##", "#.######."));
        maze.add(Arrays.asList(".########", "###..#.#."));
        maze.add(Arrays.asList(".####.#.#", "###..##.."));
        maze.add(Arrays.asList(".######.#", "########."));
        maze.add(Arrays.asList(".########", "##.#####."));
        maze.add(Arrays.asList(".########", "..######."));
        maze.add(Arrays.asList(".#####..#", "#######.."));
        maze.add(Arrays.asList(".#.######", ".#######."));
        maze.add(Arrays.asList(".###.#.#.", ".##..#.#."));
        maze.add(Arrays.asList(".#.##.###", "####.##.."));
        logResult(main.escapeMaze(maze));
    }
}
