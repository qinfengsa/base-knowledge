package com.qinfengsa.structure.leetcode;

import static com.qinfengsa.structure.util.LogUtils.logResult;

import java.util.Objects;

/**
 * 304. 二维区域和检索 - 矩阵不可变 给定一个二维矩阵，计算其子矩形范围内元素的总和，该子矩阵的左上角为 (row1, col1) ，右下角为 (row2, col2)。
 *
 * <p>Range Sum Query 2D 上图子矩阵左上角 (row1, col1) = (2, 1) ，右下角(row2, col2) = (4, 3)，该子矩形内元素的总和为 8。
 *
 * <p>示例:
 *
 * <p>给定 matrix = [ [3, 0, 1, 4, 2], [5, 6, 3, 2, 1], [1, 2, 0, 1, 5], [4, 1, 0, 1, 7], [1, 0, 3, 0,
 * 5] ]
 *
 * <p>sumRegion(2, 1, 4, 3) -> 8 sumRegion(1, 1, 2, 2) -> 11 sumRegion(1, 2, 2, 4) -> 12 说明:
 *
 * <p>你可以假设矩阵不可变。 会多次调用 sumRegion 方法。 你可以假设 row1 ≤ row2 且 col1 ≤ col2。
 *
 * @author qinfengsa
 * @create 2020/05/11 18:35
 */
public class NumMatrix {

    private int[][] nums;

    public NumMatrix(int[][] matrix) {
        if (matrix.length == 0) {
            return;
        }
        nums = new int[matrix.length + 1][matrix[0].length + 1];
        /*nums[0][0] = matrix[0][0];
        for (int i = 1; i < matrix.length; i++) {
            nums[i][0] = nums[i - 1][0] + matrix[i][0];
        }
        for (int i = 1; i < matrix[0].length; i++) {
            nums[0][i] = nums[0][i - 1] + matrix[0][i];
        }*/
        for (int i = 1; i <= matrix.length; i++) {

            for (int j = 1; j <= matrix[0].length; j++) {
                nums[i][j] =
                        nums[i - 1][j] + nums[i][j - 1] - nums[i - 1][j - 1] + matrix[i - 1][j - 1];
            }
        }
        logResult(nums);
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        if (Objects.isNull(nums)) {
            return 0;
        }

        return nums[row2 + 1][col2 + 1]
                + nums[row1][col1]
                - nums[row2 + 1][col1]
                - nums[row1][col2 + 1];
    }

    public static void main(String[] args) {
        int[][] matrix = {
            {3, 0, 1, 4, 2}, {5, 6, 3, 2, 1}, {1, 2, 0, 1, 5}, {4, 1, 0, 1, 7}, {1, 0, 3, 0, 5}
        };
        NumMatrix numMatrix = new NumMatrix(matrix);

        logResult(numMatrix.sumRegion(2, 1, 4, 3));
        logResult(numMatrix.sumRegion(1, 1, 2, 2));
        logResult(numMatrix.sumRegion(1, 2, 2, 4));
    }
}
