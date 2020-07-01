package com.qinfengsa.structure.leetcode;

import java.util.Random;

/**
 * 497. 非重叠矩形中的随机点
 *
 * <p>给定一个非重叠轴对齐矩形的列表 rects，写一个函数 pick 随机均匀地选取矩形覆盖的空间中的整数点。
 *
 * <p>提示：
 *
 * <p>整数点是具有整数坐标的点。 矩形周边上的点包含在矩形覆盖的空间中。 第 i 个矩形 rects [i] = [x1，y1，x2，y2]，其中 [x1，y1]
 * 是左下角的整数坐标，[x2，y2] 是右上角的整数坐标。 每个矩形的长度和宽度不超过 2000。 1 <= rects.length <= 100 pick 以整数坐标数组 [p_x, p_y]
 * 的形式返回一个点。 pick 最多被调用10000次。
 *
 * <p>示例 1：
 *
 * <p>输入: ["Solution","pick","pick","pick"] [[[[1,1,5,5]]],[],[],[]] 输出: [null,[4,1],[4,1],[3,3]] 示例
 * 2：
 *
 * <p>输入: ["Solution","pick","pick","pick","pick","pick"]
 * [[[[-2,-2,-1,-1],[1,0,3,0]]],[],[],[],[],[]] 输出: [null,[-1,-2],[2,0],[-2,-1],[3,0],[-2,-2]]
 *
 * <p>输入语法的说明：
 *
 * <p>输入是两个列表：调用的子例程及其参数。Solution 的构造函数有一个参数，即矩形数组 rects。pick 没有参数。参数总是用列表包装的，即使没有也是如此。
 *
 * @author qinfengsa
 * @date 2020/07/01 13:20
 */
public class RandomPoint {

    // 每个矩形拥有点的个数累加（面积）
    int[] points;

    // 每个矩形的长度
    int[] lens;

    // 开始节点
    int[][] startPoints;

    int total;

    Random random = new Random();

    public RandomPoint(int[][] rects) {
        int count = 0;
        points = new int[rects.length];
        lens = new int[rects.length];
        startPoints = new int[rects.length][2];
        for (int i = 0; i < rects.length; i++) {
            int[] rect = rects[i];
            count += (rect[2] - rect[0] + 1) * (rect[3] - rect[1] + 1);
            points[i] = count;
            lens[i] = rect[2] - rect[0] + 1;
            startPoints[i][0] = rect[0];
            startPoints[i][1] = rect[1];
        }
        total = count;
    }

    public int[] pick() {
        int target = random.nextInt(total);
        int i = getIndex(target);
        int[] start = startPoints[i];
        int len = lens[i];
        int idx = i > 0 ? target - points[i - 1] : target;

        int x = start[0] + idx % len;
        int y = start[1] + idx / len;

        return new int[] {x, y};
    }

    private int getIndex(int target) {
        int left = 0, right = points.length - 1;
        while (left < right) {
            int mid = (left + right) >> 1;
            if (target >= points[mid]) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }
}
