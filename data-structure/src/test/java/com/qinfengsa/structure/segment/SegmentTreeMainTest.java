package com.qinfengsa.structure.segment;

import static com.qinfengsa.structure.util.LogUtils.logResult;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

/**
 * 线段树
 *
 * @author qinfengsa
 * @date 2021/05/22 09:07
 */
@Slf4j
public class SegmentTreeMainTest {

    static SegmentTreeMain main = new SegmentTreeMain();

    @Test
    public void rectangleArea() {
        int[][] rectangles = {{0, 0, 2, 2}, {1, 0, 2, 3}, {1, 0, 3, 1}};
        logResult(main.rectangleArea(rectangles));
    }
}
