package com.qinfengsa.algorithm.linesweep;

import static com.qinfengsa.algorithm.util.LogUtils.logResult;

import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;
/**
 * 扫描线算法
 *
 * @author qinfengsa
 * @date 2020/12/05 10:38
 */
public class LineSweepTest {
    // 扫描线算法流程：
    //
    // 1 想象一下有一条平行于 y yy 轴的直线，正在从左边缓缓向右平移……
    // 2 再想像一下 y yy 轴上有一棵线段树，它记录的是 y yy 轴上每个点的覆盖次数
    // 3 每当遇到某个矩形的某一条边时，就计算面积——用这次碰边的 x xx 坐标减去上一次碰边时的 x xx 坐标，再用这个差值乘以当前 y yy 轴上有多少个点被覆盖
    // 4 当这条直线遇到某个矩形的左边时，将这个矩形的左边所对应的y轴区间的覆盖次数 + 1 +1+1，当遇到某个矩形的右边时，就相应的 − 1 -1−1
    // 5 让这条线继续向右移动……

    /**
     * 391. 完美矩形
     *
     * <p>我们有 N 个与坐标轴对齐的矩形, 其中 N > 0, 判断它们是否能精确地覆盖一个矩形区域。
     *
     * <p>每个矩形用左下角的点和右上角的点的坐标来表示。例如， 一个单位正方形可以表示为 [1,1,2,2]。 ( 左下角的点的坐标为 (1, 1) 以及右上角的点的坐标为 (2, 2)
     * )。
     *
     * <p>示例 1:
     *
     * <p>rectangles = [ [1,1,3,3], [3,1,4,2], [3,2,4,4], [1,3,2,4], [2,3,3,4] ]
     *
     * <p>返回 true。5个矩形一起可以精确地覆盖一个矩形区域。
     *
     * <p>示例 2:
     *
     * <p>rectangles = [ [1,1,2,3], [1,3,2,4], [3,1,4,2], [3,2,4,4] ]
     *
     * <p>返回 false。两个矩形之间有间隔，无法覆盖成一个矩形。
     *
     * <p>示例 3:
     *
     * <p>rectangles = [ [1,1,3,3], [3,1,4,2], [1,3,2,4], [3,2,4,4] ]
     *
     * <p>返回 false。图形顶端留有间隔，无法覆盖成一个矩形。
     *
     * <p>示例 4:
     *
     * <p>rectangles = [ [1,1,3,3], [3,1,4,2], [1,3,2,4], [2,2,4,4] ]
     *
     * <p>返回 false。因为中间有相交区域，虽然形成了矩形，但不是精确覆盖。
     *
     * @param rectangles
     * @return
     */
    public boolean isRectangleCover(int[][] rectangles) {
        // 1,总面积等于小块的面积和
        // 2,除大矩形4个顶点外其它点都出现偶数(2或4)次
        int left = Integer.MAX_VALUE, right = 0;
        int bottom = Integer.MAX_VALUE, top = 0;
        int area = 0;

        Map<String, Integer> countMap = new HashMap<>();
        for (int[] rectangle : rectangles) {
            left = Math.min(left, rectangle[0]);
            bottom = Math.min(bottom, rectangle[1]);
            right = Math.max(right, rectangle[2]);
            top = Math.max(top, rectangle[3]);
            area += (rectangle[3] - rectangle[1]) * (rectangle[2] - rectangle[0]);

            String key0 = rectangle[0] + "-" + rectangle[1];
            String key1 = rectangle[0] + "-" + rectangle[3];
            String key2 = rectangle[2] + "-" + rectangle[1];
            String key3 = rectangle[2] + "-" + rectangle[3];
            countMap.put(key0, countMap.getOrDefault(key0, 0) + 1);
            countMap.put(key1, countMap.getOrDefault(key1, 0) + 1);
            countMap.put(key2, countMap.getOrDefault(key2, 0) + 1);
            countMap.put(key3, countMap.getOrDefault(key3, 0) + 1);
        }
        if (area != (right - left) * (top - bottom)) {
            return false;
        }
        countMap.put(left + "-" + bottom, countMap.getOrDefault(left + "-" + bottom, 0) + 1);
        countMap.put(left + "-" + top, countMap.getOrDefault(left + "-" + top, 0) + 1);
        countMap.put(right + "-" + bottom, countMap.getOrDefault(right + "-" + bottom, 0) + 1);
        countMap.put(right + "-" + top, countMap.getOrDefault(right + "-" + top, 0) + 1);
        for (int count : countMap.values()) {
            if ((count & 1) == 1) {
                return false;
            }
        }
        return true;
    }

    @Test
    public void isRectangleCover() {
        int[][] rectangles = {
            {0, 0, 1, 1},
            {1, 1, 2, 2},
            {0, 1, 2, 2}
        };
        logResult(isRectangleCover(rectangles));
    }
}
