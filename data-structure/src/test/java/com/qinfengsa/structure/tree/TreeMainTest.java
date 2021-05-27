package com.qinfengsa.structure.tree;

import static com.qinfengsa.structure.util.LogUtils.logResult;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

/**
 * 树
 *
 * @author qinfengsa
 * @date 2021/5/26 8:35
 */
@Slf4j
public class TreeMainTest {
    static TreeMain main = new TreeMain();

    @Test
    public void frogPosition() {
        int n = 9, t = 1, target = 8;
        int[][] edges = {{2, 1}, {3, 1}, {4, 2}, {5, 3}, {6, 5}, {7, 4}, {8, 7}, {9, 7}};
        logResult(main.frogPosition(n, edges, t, target));
    }
}
