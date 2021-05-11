package com.qinfengsa.structure.graph;

import static com.qinfengsa.structure.util.LogUtils.logResult;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

/**
 * 图
 *
 * @author qinfengsa
 * @date 2021/5/10 13:33
 */
@Slf4j
public class GraphMainTest {

    static GraphMain main = new GraphMain();

    @Test
    public void electricCarPlan() {
        int[][] paths = {{0, 4, 2}, {4, 3, 5}, {3, 0, 5}, {0, 1, 5}, {3, 2, 4}, {1, 2, 8}};
        int cnt = 8, start = 0, end = 2;
        int[] charge = {4, 1, 1, 3, 2};
        logResult(main.electricCarPlan(paths, cnt, start, end, charge));
    }
}
