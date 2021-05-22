package com.qinfengsa.structure.segment;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import lombok.extern.slf4j.Slf4j;

/**
 * 线段树
 *
 * @author qinfengsa
 * @date 2021/05/22 08:08
 */
@Slf4j
public class SegmentTreeMain {

    static final int MOD = 1000000007;

    /**
     * 850. 矩形面积 II
     *
     * <p>我们给出了一个（轴对齐的）矩形列表 rectangles 。 对于 rectangle[i] = [x1, y1, x2, y2]，其中（x1，y1）是矩形 i
     * 左下角的坐标，（x2，y2）是该矩形右上角的坐标。
     *
     * <p>找出平面中所有矩形叠加覆盖后的总面积。 由于答案可能太大，请返回它对 10 ^ 9 + 7 取模的结果。
     *
     * <p>示例 1：
     *
     * <p>输入：[[0,0,2,2],[1,0,2,3],[1,0,3,1]] 输出：6 解释：如图所示。 示例 2：
     *
     * <p>输入：[[0,0,1000000000,1000000000]] 输出：49 解释：答案是 10^18 对 (10^9 + 7) 取模的结果， 即 (10^9)^2 →
     * (-7)^2 = 49 。 提示：
     *
     * <p>1 <= rectangles.length <= 200 rectanges[i].length = 4 0 <= rectangles[i][j] <= 10^9
     * 矩形叠加覆盖后的总面积不会超越 2^63 - 1 ，这意味着可以用一个 64 位有符号整数来保存面积结果。
     *
     * @param rectangles
     * @return
     */
    public int rectangleArea(int[][] rectangles) {
        int len = rectangles.length;
        int OPEN = 1, CLOSE = -1;
        // 扫描线
        int[][] lines = new int[len << 1][];
        // 坐标 x 集合
        Set<Integer> xSet = new HashSet<>();
        int idx = 0;
        for (int[] rectangle : rectangles) {
            lines[idx++] = new int[] {rectangle[1], OPEN, rectangle[0], rectangle[2]};
            lines[idx++] = new int[] {rectangle[3], CLOSE, rectangle[0], rectangle[2]};
            xSet.add(rectangle[0]);
            xSet.add(rectangle[2]);
        }
        // 排序
        Arrays.sort(lines, Comparator.comparingInt(a -> a[0]));
        // x 坐标
        int[] xArr = new int[xSet.size()];
        idx = 0;
        for (int x : xSet) {
            xArr[idx++] = x;
        }
        Arrays.sort(xArr);
        Map<Integer, Integer> indexMap = new HashMap<>();
        for (int i = 0; i < xArr.length; i++) {
            indexMap.put(xArr[i], i);
        }
        RectangleSegTree segTree = new RectangleSegTree();
        RectangleSegNode root = segTree.build(0, xArr.length - 1, xArr);
        long result = 0L, curLen = 0L;
        int curY = 0;
        for (int[] line : lines) {
            int y = line[0], lineType = line[1], x1 = line[2], x2 = line[3];
            result += curLen * (y - curY);
            segTree.update(root, indexMap.get(x1), indexMap.get(x2), lineType);
            curLen = root.len;
            curY = y;
        }

        result %= MOD;
        return (int) result;
    }

    // 线段树
    static class RectangleSegTree {

        public RectangleSegNode build(int start, int end, int[] xArr) {
            RectangleSegNode node = new RectangleSegNode(start, end, xArr);

            RectangleSegNode left, right;
            if (start + 1 == end) {
                left = new RectangleSegNode(start, start, xArr);
                right = new RectangleSegNode(end, end, xArr);
            } else {
                int mid = node.getMid();
                left = build(start, mid, xArr);
                right = build(mid, end, xArr);
            }

            node.left = left;
            node.right = right;
            return node;
        }

        public void updateRoot(RectangleSegNode node) {
            if (node.count > 0) {
                node.len = node.xArr[node.end] - node.xArr[node.start];
                return;
            }
            if (node.start == node.end) {
                node.len = 0;
            } else {

                node.len = node.left.len + node.right.len;
            }
        }

        public void update(RectangleSegNode node, int x1, int x2, int lineType) {

            // 区间外
            if (x2 <= node.start || x1 >= node.end) {
                return;
            }
            // 区间内 完全覆盖
            if (x1 <= node.start && x2 >= node.end) {
                node.count += lineType;
                updateRoot(node);
                return;
            }
            int mid = node.getMid();
            if (x2 <= mid) {
                // 左节点
                update(node.left, x1, x2, lineType);
            } else if (x1 > mid) {
                update(node.right, x1, x2, lineType);
            } else {
                update(node.left, x1, x2, lineType);
                update(node.right, x1, x2, lineType);
            }
            updateRoot(node);
        }
    }

    static class RectangleSegNode {
        int start, end;
        RectangleSegNode left, right;
        int count;
        long len;
        int[] xArr;

        public RectangleSegNode(int start, int end, int[] xArr) {
            this.start = start;
            this.end = end;
            this.xArr = xArr;
            this.count = 0;
            this.len = 0L;
        }

        int getMid() {
            return (start + end) >> 1;
        }
    }
}
