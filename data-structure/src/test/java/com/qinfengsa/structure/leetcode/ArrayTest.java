package com.qinfengsa.structure.leetcode;

import static com.qinfengsa.structure.util.LogUtils.logResult;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.SortedSet;
import java.util.Stack;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

/**
 * @author qinfengsa
 * @date 2019/5/8 13:38
 */
@Slf4j
public class ArrayTest {

    /**
     * 给定一个由整数组成的非空数组所表示的非负整数，在该数的基础上加一。
     *
     * <p>最高位数字存放在数组的首位， 数组中每个元素只存储一个数字。
     *
     * <p>你可以假设除了整数 0 之外，这个整数不会以零开头。
     *
     * <p>示例 1:
     *
     * <p>输入: [1,2,3] 输出: [1,2,4] 解释: 输入数组表示数字 123。 示例 2:
     *
     * <p>输入: [4,3,2,1] 输出: [4,3,2,2] 解释: 输入数组表示数字 4321。
     */
    @Test
    public void testAdd() {
        int[] digits = {9, 9, 9};
        int[] result = plusOne(digits);
        log.info("result:{}", result);
    }

    /**
     * 给定一个由整数组成的非空数组所表示的非负整数，在该数的基础上加一。
     *
     * <p>最高位数字存放在数组的首位， 数组中每个元素只存储一个数字。
     *
     * <p>你可以假设除了整数 0 之外，这个整数不会以零开头。
     *
     * <p>示例 1:
     *
     * <p>输入: [1,2,3] 输出: [1,2,4] 解释: 输入数组表示数字 123。 示例 2:
     *
     * <p>输入: [4,3,2,1] 输出: [4,3,2,2] 解释: 输入数组表示数字 4321。
     *
     * @param digits
     * @return
     */
    public int[] plusOne(int[] digits) {

        if (Objects.isNull(digits)) {
            return null;
        }
        if (digits.length == 0) {
            return digits;
        }

        int last = 1;
        for (int i = digits.length - 1; i >= 0; i--) {
            int num = digits[i] + last;
            if (num >= 10) {
                num = 0;
                last = 1;
            } else {
                last = 0;
            }
            digits[i] = num;
        }
        if (last > 0) {
            int nums[] = new int[digits.length + 1];
            nums[0] = last;
            for (int i = 0; i < digits.length; i++) {
                nums[i + 1] = digits[i];
            }
            return nums;
        }

        return digits;
    }

    /**
     * 在一个给定的数组nums中，总是存在一个最大元素 。
     *
     * <p>查找数组中的最大元素是否至少是数组中每个其他数字的两倍。
     *
     * <p>如果是，则返回最大元素的索引，否则返回-1。
     *
     * <p>示例 1:
     *
     * <p>输入: nums = [3, 6, 1, 0] 输出: 1 解释: 6是最大的整数, 对于数组中的其他整数, 6大于数组中其他元素的两倍。6的索引是1, 所以我们返回1.
     *
     * <p>示例 2:
     *
     * <p>输入: nums = [1, 2, 3, 4] 输出: -1 解释: 4没有超过3的两倍大, 所以我们返回 -1.
     */
    @Test
    public void testGetMax() {
        int[] nums = {1, 0};

        int result = dominantIndex(nums);
        log.info("result:{}", result);
    }

    /**
     * 在一个给定的数组nums中，总是存在一个最大元素 。
     *
     * <p>查找数组中的最大元素是否至少是数组中每个其他数字的两倍。
     *
     * <p>如果是，则返回最大元素的索引，否则返回-1。
     *
     * <p>示例 1:
     *
     * <p>输入: nums = [3, 6, 1, 0] 输出: 1 解释: 6是最大的整数, 对于数组中的其他整数, 6大于数组中其他元素的两倍。6的索引是1, 所以我们返回1.
     *
     * <p>示例 2:
     *
     * <p>输入: nums = [1, 2, 3, 4] 输出: -1 解释: 4没有超过3的两倍大, 所以我们返回 -1.
     *
     * @param nums
     * @return
     */
    public int dominantIndex(int[] nums) {
        if (Objects.isNull(nums)) {
            return -1;
        }
        if (nums.length == 0) {
            return -1;
        }
        if (nums.length == 1) {
            return 0;
        }
        int max = -1;
        int maxIndex = -1;
        int secondMax = -1;
        for (int i = 0; i < nums.length; i++) {
            if (max < 0) {
                max = nums[i];
                maxIndex = i;
            } else if (nums[i] > max) {

                secondMax = max;
                max = nums[i];
                maxIndex = i;
            } else if (nums[i] > secondMax) {
                secondMax = nums[i];
            }
        }
        log.info("1:{},2:{}", max, secondMax);
        if (max >= (2 * secondMax)) {
            return maxIndex;
        }

        return -1;
    }

    /**
     * 对角线遍历 给定一个含有 M x N 个元素的矩阵（M 行，N 列），请以对角线遍历的顺序返回这个矩阵中的所有元素，对角线遍历如下图所示。 示例: 输入: [ [ 1, 2, 3 ],
     * [ 4, 5, 6 ], [ 7, 8, 9 ] ]
     *
     * <p>输出: [1,2,4,7,5,3,6,8,9]
     */
    @Test
    public void testDiagonalOrder() {
        // int[][] matrix = {{1, 2, 3},{4, 5, 6},{7, 8, 9},{10,11,12},{13,14,15}};

        // int[][] matrix = {{1, 2, 3,4},{ 5, 6,7, 8},{  9,10,11,12} };
        int[][] matrix = {};
        int[] result = findDiagonalOrder(matrix);
        log.info("result:{}", result);
    }

    /**
     * 对角线遍历 给定一个含有 M x N 个元素的矩阵（M 行，N 列），请以对角线遍历的顺序返回这个矩阵中的所有元素，对角线遍历如下图所示。 示例:
     *
     * <p>输入: [ [ 1, 2, 3 ], [ 4, 5, 6 ], [ 7, 8, 9 ] ]
     *
     * <p>输出: [1,2,4,7,5,3,6,8,9]
     *
     * @param matrix
     * @return
     */
    public int[] findDiagonalOrder(int[][] matrix) {
        if (Objects.isNull(matrix)) {
            return null;
        }
        if (matrix.length == 0) {
            int[] a = new int[0];
            return a;
        }
        if (matrix.length == 1) {
            return matrix[0];
        }
        int width = matrix.length;
        int len = matrix[0].length;
        int size = len * width;
        int[] result = new int[size];
        int x = 0, y = 0;
        int index = 0;
        while (x < len && y < width) {
            result[index] = matrix[y][x];
            if (x == len - 1 && y == width - 1) {
                break;
            }
            // 如果x+y是偶数 右上移动
            if ((x + y) % 2 == 0) {
                // 最后一列要特殊处理 下移
                if (x == len - 1) {
                    y++;
                } else {
                    x++;
                    y--;
                }
                // 如果x+y是奇数 左下移动
            } else {
                // 最后一行要特殊处理 右移
                if (y == width - 1) {
                    x++;
                } else {
                    y++;
                    x--;
                }
            }
            if (x < 0) {
                x = 0;
            }
            if (x >= len) {
                x = len - 1;
            }
            if (y < 0) {
                y = 0;
            }
            if (y >= width) {
                y = width - 1;
            }
            index++;
        }
        return result;
    }

    @Test
    public void findDiagonalOrder2() {
        int[][] matrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}, {10, 11, 12}, {13, 14, 15}};
        List<List<Integer>> nums = new ArrayList<>();
        List<Integer> list1 = new ArrayList<>();
        list1.add(1);
        list1.add(2);
        list1.add(3);
        List<Integer> list2 = new ArrayList<>();
        list2.add(4);
        list2.add(5);
        list2.add(6);
        List<Integer> list3 = new ArrayList<>();
        list3.add(7);
        list3.add(8);
        list3.add(9);
        nums.add(list1);
        nums.add(list2);
        nums.add(list3);
        // int[][] matrix = {{1, 2, 3,4},{ 5, 6,7, 8},{  9,10,11,12} };
        // int[][] matrix = {};
        int[] result = findDiagonalOrder(nums);
        log.info("result:{}", result);
    }

    /**
     * 1424. 对角线遍历 II 给你一个列表 nums ，里面每一个元素都是一个整数列表。请你依照下面各图的规则，按顺序返回 nums 中对角线上的整数。
     *
     * <p>示例 1：
     *
     * <p>输入：nums = [[1,2,3],[4,5,6],[7,8,9]] 输出：[1,4,2,7,5,3,8,6,9] 示例 2：
     *
     * <p>输入：nums = [[1,2,3,4,5],[6,7],[8],[9,10,11],[12,13,14,15,16]]
     * 输出：[1,6,2,8,7,3,9,4,12,10,5,13,11,14,15,16] 示例 3：
     *
     * <p>输入：nums = [[1,2,3],[4],[5,6,7],[8],[9,10,11]] 输出：[1,4,2,5,3,8,6,9,7,10,11] 示例 4：
     *
     * <p>输入：nums = [[1,2,3,4,5,6]] 输出：[1,2,3,4,5,6]
     *
     * <p>提示：
     *
     * <p>1 <= nums.length <= 10^5 1 <= nums[i].length <= 10^5 1 <= nums[i][j] <= 10^9 nums 中最多有
     * 10^5 个数字。
     *
     * @param nums
     * @return
     */
    public int[] findDiagonalOrder(List<List<Integer>> nums) {
        int len = 0;
        int size = nums.size();
        Map<Integer, List<Integer>> map = new TreeMap<>();
        for (int i = 0; i < size; i++) {
            len += nums.get(i).size();
            for (int j = 0; j < nums.get(i).size(); j++) {
                List<Integer> list = map.computeIfAbsent(i + j, k -> new ArrayList<>());
                list.add(nums.get(i).get(j));
            }
        }
        int[] result = new int[len];
        int index = 0;

        for (Integer key : map.keySet()) {
            List<Integer> list = map.get(key);
            for (int i = list.size() - 1; i >= 0; i--) {
                result[index++] = list.get(i);
            }
        }
        return result;

        /*List<Integer> list = new ArrayList<>();

        int size = nums.size();
        int maxRow = 0;
        for (int i = 0; i < size; i++) {
            int row = i,col = 0;
            while (row >= 0) {
                if (col < nums.get(row).size()) {
                    list.add(nums.get(row).get(col));
                }

                row--;
                col++;
            }
            maxRow = Math.max(maxRow,nums.get(i).size());
        }
        for (int i = 1; i < maxRow; i++) {
            int row = size - 1, col = i;
            while (row >= 0) {
                if (col < nums.get(row).size()) {
                    list.add(nums.get(row).get(col));
                }
                row--;
                col++;
            }
        }



        int[] result = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            result[i] = list.get(i);
        }
        return result;*/
    }

    /**
     * 螺旋矩阵 给定一个包含 m x n 个元素的矩阵（m 行, n 列），请按照顺时针螺旋顺序，返回矩阵中的所有元素。
     *
     * <p>示例 1:
     *
     * <p>输入: [ [ 1, 2, 3 ], [ 4, 5, 6 ], [ 7, 8, 9 ] ] 输出: [1,2,3,6,9,8,7,4,5] 示例 2:
     *
     * <p>输入: [ [1, 2, 3, 4], [5, 6, 7, 8], [9,10,11,12] ] 输出: [1,2,3,4,8,12,11,10,9,5,6,7]
     */
    @Test
    public void testSpiralOrder() {
        int[][] matrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}, {10, 11, 12}};

        // int[][] matrix = {{1, 2, 3,4},{ 5, 6,7, 8},{  9,10,11,12},{  13,14,15,16} };
        // int[][] matrix = {};
        List<Integer> result = spiralOrder(matrix);
        log.info("result:{}", result);
    }

    /**
     * 螺旋矩阵 给定一个包含 m x n 个元素的矩阵（m 行, n 列），请按照顺时针螺旋顺序，返回矩阵中的所有元素。
     *
     * <p>示例 1:
     *
     * <p>输入: [ [ 1, 2, 3 ], [ 4, 5, 6 ], [ 7, 8, 9 ] ] 输出: [1,2,3,6,9,8,7,4,5] 示例 2: 输入: [ [1, 2,
     * 3, 4], [5, 6, 7, 8], [9,10,11,12] ] 输出: [1,2,3,4,8,12,11,10,9,5,6,7]
     *
     * @param matrix
     * @return
     */
    public List<Integer> spiralOrder(int[][] matrix) {
        if (Objects.isNull(matrix)) {
            return null;
        }
        if (matrix.length == 0) {
            return new ArrayList<>();
        }

        int width = matrix.length;
        int len = matrix[0].length;
        int size = len * width;

        List<Integer> list = new ArrayList<>(size);
        // 移动方向，1 从左至右 2 从上至下 3 从右至左 4 从下至上
        int moveType = 1;
        int xMin = 0;
        int xMax = len - 1;
        int yMin = 0;
        int yMax = width - 1;
        int x = 0, y = 0;
        while (x >= xMin && x <= xMax && y >= yMin && y <= yMax) {
            list.add(matrix[y][x]);
            if (xMin == xMax && yMax == yMin) {
                break;
            }

            if (moveType == 1) {

                x++;
                // 从左至右 移动到最右边时 yMin = y 方向改变
                if (x > xMax) {
                    x = xMax;
                    y = y + 1;
                    yMin = y;

                    moveType = 2;
                }
            } else if (moveType == 2) {
                y++;
                // 从上至下 移动到最下边时 xMax = x 方向改变
                if (y > yMax) {
                    y = yMax;
                    x = x - 1;
                    xMax = x;
                    moveType = 3;
                }
            } else if (moveType == 3) {
                x--;
                // 从右至左 移动到最左边时 yMax = y - 1 方向改变
                if (x < xMin) {
                    x = xMin;
                    y = y - 1;
                    yMax = y;
                    moveType = 4;
                }
            } else if (moveType == 4) {
                y--;
                // 从下至上 移动到最上边时 xMin = x+1  方向改变
                if (y < yMin) {
                    y = yMin;
                    x = x + 1;
                    xMin = x;
                    moveType = 1;
                }
            }
        }

        return list;
    }

    /**
     * 杨辉三角 给定一个非负整数 numRows，生成杨辉三角的前 numRows 行。 在杨辉三角中，每个数是它左上方和右上方的数的和。
     *
     * <p>示例:
     *
     * <p>输入: 5 输出: [ [1], [1,1], [1,2,1], [1,3,3,1], [1,4,6,4,1] ]
     */
    @Test
    public void testYangHui() {
        int numRows = 7;
        List<List<Integer>> result = generate(numRows);
        int index = 0;
        for (List<Integer> list : result) {
            index++;
            log.info("{}:{}", index, list);
        }
    }

    /**
     * 杨辉三角 给定一个非负整数 numRows，生成杨辉三角的前 numRows 行。 在杨辉三角中，每个数是它左上方和右上方的数的和。
     *
     * <p>示例:
     *
     * <p>输入: 5 输出: [ [1], [1,1], [1,2,1], [1,3,3,1], [1,4,6,4,1] ]
     *
     * @param numRows
     * @return
     */
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> result = new ArrayList<>(numRows);
        int[][] array = new int[numRows][numRows];
        // 循环打印杨辉三角,numRows 行
        for (int i = 0; i < numRows; i++) {
            // 注意:j<=i, 因为第1行有1列，第2行有2列，第3行有3列。。。
            for (int j = 0; j <= i; j++) {
                // 第一列和最后一列
                if (j == 0 || i == j) {
                    array[i][j] = 1;
                } else {
                    // 中间列的值 = 上一行和它所在列-1的值 + 上一行和它所在列的值
                    array[i][j] = array[i - 1][j - 1] + array[i - 1][j];
                }
            }
        }
        for (int i = 0; i < numRows; i++) {
            List<Integer> list = new ArrayList<>();
            for (int j = 0; j <= i; j++) {
                list.add(array[i][j]);
            }

            result.add(list);
        }

        return result;
    }

    /**
     * 旋转数组 给定一个数组，将数组中的元素向右移动 k 个位置，其中 k 是非负数。
     *
     * <p>示例 1:
     *
     * <p>输入: [1,2,3,4,5,6,7] 和 k = 3 输出: [5,6,7,1,2,3,4] 解释: 向右旋转 1 步: [7,1,2,3,4,5,6] 向右旋转 2 步:
     * [6,7,1,2,3,4,5] 向右旋转 3 步: [5,6,7,1,2,3,4] 示例 2:
     *
     * <p>输入: [-1,-100,3,99] 和 k = 2 输出: [3,99,-1,-100] 解释: 向右旋转 1 步: [99,-1,-100,3] 向右旋转 2 步:
     * [3,99,-1,-100] 说明:
     *
     * <p>尽可能想出更多的解决方案，至少有三种不同的方法可以解决这个问题。 要求使用空间复杂度为 O(1) 的原地算法。
     */
    @Test
    public void testRotate() {
        int[] nums = {1, 2, 3, 4, 5, 6, 7};
        rotate(nums, 7);
        log.debug("result:{} ", nums);
    }

    /**
     * 旋转数组 给定一个数组，将数组中的元素向右移动 k 个位置，其中 k 是非负数。
     *
     * <p>示例 1:
     *
     * <p>输入: [1,2,3,4,5,6,7] 和 k = 3 输出: [5,6,7,1,2,3,4] 解释: 向右旋转 1 步: [7,1,2,3,4,5,6] 向右旋转 2 步:
     * [6,7,1,2,3,4,5] 向右旋转 3 步: [5,6,7,1,2,3,4] 示例 2:
     *
     * <p>输入: [-1,-100,3,99] 和 k = 2 输出: [3,99,-1,-100] 解释: 向右旋转 1 步: [99,-1,-100,3] 向右旋转 2 步:
     * [3,99,-1,-100] 说明:
     *
     * <p>尽可能想出更多的解决方案，至少有三种不同的方法可以解决这个问题。 要求使用空间复杂度为 O(1) 的原地算法。
     *
     * @param nums
     * @param k
     */
    public void rotate(int[] nums, int k) {
        if (Objects.isNull(nums)) {
            return;
        }
        int size = nums.length;
        k = k % size;
        if (k == 0) {
            return;
        }

        int start = 0;
        int curNum = nums[start];
        int preNum = 0;
        int index = 0;
        for (int i = 0; i < size; i++) {
            preNum = curNum;
            index = (index + k) % size;
            curNum = nums[index];
            nums[index] = preNum;

            // 当出现重复，size整除时，start自增
            if (index == start) {
                index = ++start;
                curNum = nums[index];
            }
        }
    }

    /**
     * 杨辉三角 II 给定一个非负索引 k，其中 k ≤ 33，返回杨辉三角的第 k 行。 在杨辉三角中，每个数是它左上方和右上方的数的和。
     *
     * <p>示例:
     *
     * <p>输入: 3 输出: [1,3,3,1] 进阶：
     *
     * <p>你可以优化你的算法到 O(k) 空间复杂度吗？
     */
    @Test
    public void testYangHui2() {
        int rowIndex = 5;
        List<Integer> result = getRow(rowIndex);

        log.info("result:{}", result);
    }

    /**
     * 杨辉三角 给定一个非负整数 numRows，生成杨辉三角的前 numRows 行。 在杨辉三角中，每个数是它左上方和右上方的数的和。
     *
     * <p>示例:
     *
     * <p>输入: 5 输出: [ [1], [1,1], [1,2,1], [1,3,3,1], [1,4,6,4,1] ] 杨辉三角 II 给定一个非负索引 k，其中 k ≤
     * 33，返回杨辉三角的第 k 行。 在杨辉三角中，每个数是它左上方和右上方的数的和。
     *
     * <p>示例:
     *
     * <p>输入: 3 输出: [1,3,3,1] 进阶：
     *
     * <p>你可以优化你的算法到 O(k) 空间复杂度吗？
     *
     * @param rowIndex
     * @return
     */
    public List<Integer> getRow(int rowIndex) {
        rowIndex++;
        List<Integer> result = new ArrayList<>(rowIndex);
        int[] array = new int[rowIndex];
        // 循环打印杨辉三角,numRows 行
        for (int i = 0; i < rowIndex; i++) {
            // 注意:j<=i, 因为第1行有1列，第2行有2列，第3行有3列。。。
            int lastNum = 0;
            for (int j = 0; j <= i; j++) {
                // 第一列和最后一列
                int tmpNum = 0;
                if (j + 1 <= i) {
                    // 中间列的值 = 上一行和它所在列-1的值 + 上一行和它所在列的值
                    tmpNum = array[j] + array[j + 1];
                }
                if (j == 0 || i == j) {
                    array[j] = 1;
                } else {
                    if (lastNum > 0) {
                        array[j] = lastNum;
                    }
                }
                lastNum = tmpNum;
            }
        }
        for (int i = 0; i < rowIndex; i++) {
            result.add(array[i]);
        }

        return result;
    }

    /**
     * 删除排序数组中的重复项 给定一个排序数组，你需要在原地删除重复出现的元素，使得每个元素只出现一次，返回移除后数组的新长度。
     *
     * <p>不要使用额外的数组空间，你必须在原地修改输入数组并在使用 O(1) 额外空间的条件下完成。
     *
     * <p>示例 1:
     *
     * <p>给定数组 nums = [1,1,2],
     *
     * <p>函数应该返回新的长度 2, 并且原数组 nums 的前两个元素被修改为 1, 2。
     *
     * <p>你不需要考虑数组中超出新长度后面的元素。 示例 2:
     *
     * <p>给定 nums = [0,0,1,1,1,2,2,3,3,4],
     *
     * <p>函数应该返回新的长度 5, 并且原数组 nums 的前五个元素被修改为 0, 1, 2, 3, 4。
     *
     * <p>你不需要考虑数组中超出新长度后面的元素。
     */
    @Test
    public void testRemoveDuplicates() {
        int[] nums = {1, 1, 2};
        int result = removeDuplicates(nums);

        log.info("result:{},nums:{}", result, nums);
    }

    /**
     * 删除排序数组中的重复项 给定一个排序数组，你需要在原地删除重复出现的元素，使得每个元素只出现一次，返回移除后数组的新长度。
     *
     * <p>不要使用额外的数组空间，你必须在原地修改输入数组并在使用 O(1) 额外空间的条件下完成。
     *
     * <p>示例 1:
     *
     * <p>给定数组 nums = [1,1,2],
     *
     * <p>函数应该返回新的长度 2, 并且原数组 nums 的前两个元素被修改为 1, 2。
     *
     * <p>你不需要考虑数组中超出新长度后面的元素。 示例 2:
     *
     * <p>给定 nums = [0,0,1,1,1,2,2,3,3,4],
     *
     * <p>函数应该返回新的长度 5, 并且原数组 nums 的前五个元素被修改为 0, 1, 2, 3, 4。
     *
     * <p>你不需要考虑数组中超出新长度后面的元素。
     *
     * @param nums
     * @return
     */
    public int removeDuplicates(int[] nums) {
        /*int size = nums.length;
        if (size == 0) {
            return 0;
        }

        Integer lastNum = null;
        int k = 0;
        for (int i =0; i < size; i++) {

            if (!Objects.equals(lastNum,nums[i]) ) {
                nums[k] = nums[i];
                k++;
            }
            lastNum = nums[i];

        }


        return k;*/

        int size = nums.length;
        int index = 1;
        for (int i = 1; i < size; i++) {
            if (nums[i] > nums[i - 1]) {
                nums[index] = nums[i];
                index++;
            }
        }
        return index;
    }

    /**
     * 移动零 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
     *
     * <p>示例:
     *
     * <p>输入: [0,1,0,3,12] 输出: [1,3,12,0,0] 说明:
     *
     * <p>必须在原数组上操作，不能拷贝额外的数组。 尽量减少操作次数。
     */
    @Test
    public void testMoveZeroes() {
        int[] nums = {0, 1, 0, 3, 12};
        moveZeroes(nums);

        log.info("nums:{}", nums);
    }

    /**
     * 移动零 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
     *
     * <p>示例:
     *
     * <p>输入: [0,1,0,3,12] 输出: [1,3,12,0,0] 说明:
     *
     * <p>必须在原数组上操作，不能拷贝额外的数组。 尽量减少操作次数。
     *
     * @param nums
     */
    public void moveZeroes(int[] nums) {
        int size = nums.length;
        if (size == 0) {
            return;
        }
        int k = 0;
        for (int i = 0; i < size; i++) {

            if (nums[i] != 0) {
                nums[k] = nums[i];
                k++;
            }
        }

        for (int i = k; i < size; i++) {
            nums[i] = 0;
        }
    }

    /**
     * 岛屿的个数 给定一个由 '1'（陆地）和 '0'（水）组成的的二维网格，计算岛屿的数量。一个岛被水包围，并且它是通过水平方向或垂直方向上相邻的陆地连接而成的。
     * 你可以假设网格的四个边均被水包围。
     *
     * <p>示例 1:
     *
     * <p>输入: 11110 11010 11000 00000
     *
     * <p>输出: 1 示例 2:
     *
     * <p>输入: 11000 11000 00100 00011
     *
     * <p>输出: 3
     */
    @Test
    public void testNumIslands() {
        // char[][] grid =
        // {{'1','1','1','1','0'},{'1','1','0','1','0'},{'1','1','0','0','0'},{'0','0','0','0','0'}};
        char[][] grid = {
            {'1', '1', '0', '0', '0'},
            {'1', '1', '0', '0', '0'},
            {'0', '0', '1', '0', '0'},
            {'0', '0', '0', '1', '1'}
        };
        int result = numIslands(grid);
        log.info("result:{}", result);
    }

    /**
     * 岛屿的个数 给定一个由 '1'（陆地）和 '0'（水）组成的的二维网格，计算岛屿的数量。一个岛被水包围，并且它是通过水平方向或垂直方向上相邻的陆地连接而成的。
     * 你可以假设网格的四个边均被水包围。
     *
     * <p>示例 1:
     *
     * <p>输入: 11110 11010 11000 00000
     *
     * <p>输出: 1 示例 2:
     *
     * <p>输入: 11000 11000 00100 00011
     *
     * <p>输出: 3
     *
     * @param grid
     * @return
     */
    public int numIslands(char[][] grid) {

        if (grid.length == 0) {
            return 0;
        }

        if (grid[0].length == 0) {
            return 0;
        }
        int m = grid.length;
        int n = grid[0].length;
        int result = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1') {
                    result++;
                    updateAround(grid, i, j);
                }
            }
        }
        return result;
    }

    /**
     * 更新坐标row,col周围区域
     *
     * @param grid
     * @param row
     * @param col
     */
    private void updateAround(char[][] grid, int row, int col) {

        int m = grid.length;
        int n = grid[0].length;
        if (row < 0 || row >= m) {
            return;
        }
        if (col < 0 || col >= n) {
            return;
        }

        if (grid[row][col] != '1') {
            return;
        }
        // 更新坐标
        grid[row][col] = '2';
        // 更新相邻坐标
        updateAround(grid, row - 1, col);
        updateAround(grid, row + 1, col);
        updateAround(grid, row, col - 1);
        updateAround(grid, row, col + 1);
    }

    @Test
    public void sortColors() {
        int[] nums = {2, 0, 2, 1, 1, 0};
        sortColors(nums);
        log.debug("result:{}", nums);
    }

    /**
     * 颜色分类 给定一个包含红色、白色和蓝色，一共 n 个元素的数组，原地对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。
     *
     * <p>此题中，我们使用整数 0、 1 和 2 分别表示红色、白色和蓝色。
     *
     * <p>注意: 不能使用代码库中的排序函数来解决这道题。
     *
     * <p>示例:
     *
     * <p>输入: [2,0,2,1,1,0] 输出: [0,0,1,1,2,2] 进阶：
     *
     * <p>一个直观的解决方案是使用计数排序的两趟扫描算法。 首先，迭代计算出0、1 和 2 元素的个数，然后按照0、1、2的排序，重写当前数组。 你能想出一个仅使用常数空间的一趟扫描算法吗？
     *
     * @param nums
     */
    public void sortColors(int[] nums) {
        int[] counts = new int[3];
        // 计数排序
        for (int num : nums) {
            counts[num]++;
        }
        // 索引
        int index = 0;
        for (int i = 0; i < counts.length; i++) {
            int count = counts[i];
            while (count > 0) {
                nums[index++] = i;
                count--;
            }
        }
    }

    @Test
    public void findKthLargest() {
        int[] nums = {3, 2, 3, 1, 2, 4, 5, 5, 6};
        int k = 4;
        int result = findKthLargest(nums, k);
        log.debug("nums:{}", nums);
        log.debug("result:{}", result);
    }

    /**
     * 数组中的第K个最大元素 在未排序的数组中找到第 k 个最大的元素。请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
     *
     * <p>示例 1:
     *
     * <p>输入: [3,2,1,5,6,4] 和 k = 2 输出: 5 示例 2:
     *
     * <p>输入: [3,2,3,1,2,4,5,5,6] 和 k = 4 输出: 4 说明:
     *
     * <p>你可以假设 k 总是有效的，且 1 ≤ k ≤ 数组的长度。
     *
     * @param nums
     * @param k
     * @return
     */
    public int findKthLargest(int[] nums, int k) {

        // 利用快速排序的思想，从数组 S 中随机找出一个元素 X，把数组分为两部分 Sa 和 Sb。Sa 中的元素大于等于 X，
        // Sb 中元素小于 X。这时有两种情况：

        // Sa 中元素的个数小于 k，则 Sb 中的第 k-|Sa| 个元素即为第k大数；
        // Sa 中元素的个数大于等于 k，则返回 Sa 中的第 k 大数。时间复杂度近似为 O(n)
        int len = nums.length;

        // Arrays.sort(nums);
        quickSort(nums, len - k, 0, len - 1);

        return nums[len - k];
    }

    /**
     * 快速排序
     *
     * @param nums
     * @param k
     */
    private void quickSort(int[] nums, int k, int start, int end) {
        // 快速排序的基本思想，选取一个主元，使得数组左边小于主元 右边大于主元，
        // 然后分为左右两个数组，递归，分而治之

        // 寻找数组中的第K个最大元素，利用快速排序的思想
        // 1 选取一个主元，使得数组左边小于主元（k-n个元素） 右边大于主元（k个元素），判断主元的位置
        if (start >= end) {
            return;
        }

        // 主元，一般取第一个
        int tmp = nums[start];
        int index = 0;
        int i = start, j = end;
        while (i < j) {
            // j 递减，找到第一个 < tmp 的元素
            while (i < j && nums[j] >= tmp) {
                j--;
            }
            nums[i] = nums[j];
            // i 递增，找到第一个 > tmp 的元素
            while (i < j && nums[i] <= tmp) {
                i++;
            }
            nums[j] = nums[i];
        }
        // i 和 j 相遇，tmp 的位置 就是i ，左边小于 tmp 右边大于tmp
        nums[i] = tmp;

        index = i;

        if (k < index) {
            //  k < index 搜索左数组
            quickSort(nums, k, start, index - 1);
        } else if (k > index) {
            //  k > index 搜索右数组
            quickSort(nums, k, index + 1, end);
        }
        // k == index 结束

    }

    @Test
    public void isPalindrome() {
        String s = "race a car";
        boolean result = isPalindrome(s);
        log.debug("result:{}", result);
    }
    /**
     * 验证回文串 给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。
     *
     * <p>说明：本题中，我们将空字符串定义为有效的回文串。
     *
     * <p>示例 1:
     *
     * <p>输入: "A man, a plan, a canal: Panama" 输出: true 示例 2:
     *
     * <p>输入: "race a car" 输出: false
     *
     * @param s
     * @return
     */
    public boolean isPalindrome(String s) {
        int size = s.length();
        int i = 0;
        int j = size - 1;

        while (i < j) {
            Character start = getLower(s.charAt(i));
            Character end = getLower(s.charAt(j));
            if (Objects.isNull(start) || Objects.isNull(end)) {
                if (Objects.isNull(start)) {
                    i++;
                }
                if (Objects.isNull(end)) {
                    j--;
                }
                continue;
            }

            if (start != end) {
                return false;
            } else {
                i++;
                j--;
            }
        }

        return true;
    }

    private Character getLower(char a) {
        if (a >= 'A' && a <= 'Z') {
            a += 32;
            return a;
        }
        if (a >= 'a' && a <= 'z') {
            return a;
        }

        if (a >= '0' && a <= '9') {
            return a;
        }

        return null;
    }

    @Test
    public void reverseVowels() {
        String s = "leetcode";
        String result = reverseVowels(s);
        log.debug("result:{}", result);
    }

    /**
     * 反转字符串中的元音字母 编写一个函数，以字符串作为输入，反转该字符串中的元音字母。
     *
     * <p>示例 1:
     *
     * <p>输入: "hello" 输出: "holle" 示例 2:
     *
     * <p>输入: "leetcode" 输出: "leotcede" 说明: 元音字母不包含字母"y"。
     *
     * @param s
     * @return
     */
    public String reverseVowels(String s) {
        int size = s.length();
        int i = 0;
        int j = size - 1;

        char[] chars = s.toCharArray();

        while (i < j) {
            char start = chars[i];
            char end = chars[j];
            boolean b1 = isVowel(start);
            boolean b2 = isVowel(end);
            if (!b1 || !b2) {
                if (!b1) {
                    i++;
                }
                if (!b2) {
                    j--;
                }
                continue;
            }
            chars[j] = start;
            chars[i] = end;
            i++;
            j--;
        }
        return String.valueOf(chars);
    }

    private boolean isVowel(char a) {

        switch (a) {
            case 'A':
            case 'a':
            case 'E':
            case 'e':
            case 'I':
            case 'i':
            case 'O':
            case 'o':
            case 'U':
            case 'u':
                return true;
            default:
                return false;
        }
    }

    @Test
    public void maxArea() {
        int[] height = {1, 8, 6, 2, 5, 4, 8, 3, 7};
        int result = maxArea(height);
        log.debug("result:{}", result);
    }

    /**
     * 盛最多水的容器 给定 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和
     * (i, 0)。找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
     *
     * <p>说明：你不能倾斜容器，且 n 的值至少为 2。
     *
     * <p>图中垂直线代表输入数组 [1,8,6,2,5,4,8,3,7]。在此情况下，容器能够容纳水（表示为蓝色部分）的最大值为 49。
     *
     * <p>示例:
     *
     * <p>输入: [1,8,6,2,5,4,8,3,7] 输出: 49
     *
     * @param height
     * @return
     */
    public int maxArea(int[] height) {

        int size = height.length;

        int i = 0, j = size - 1;

        int maxArea = 0;
        int area = 0;
        int h = 0;
        int l = 0;
        while (i < j) {
            l = j - i;
            // 哪个短哪个移动一位
            if (height[i] < height[j]) {
                h = height[i];
                i++;
            } else {
                h = height[j];
                j--;
            }
            area = l * h;
            if (area > maxArea) {
                maxArea = area;
            }
        }

        return maxArea;
    }

    @Test
    public void rotate() {
        int[][] matrix = {{5, 1, 9, 11}, {2, 4, 8, 10}, {13, 3, 6, 7}, {15, 14, 12, 16}};
        log.debug("a:{}", matrix[0][2]);
        rotate(matrix);
        for (int[] a : matrix) {
            log.debug("result:{}", a);
        }
    }

    /**
     * 旋转图像 给定一个 n × n 的二维矩阵表示一个图像。
     *
     * <p>将图像顺时针旋转 90 度。
     *
     * <p>说明：
     *
     * <p>你必须在原地旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要使用另一个矩阵来旋转图像。
     *
     * <p>示例 1:
     *
     * <p>给定 matrix = [ [1,2,3], [4,5,6], [7,8,9] ],
     *
     * <p>原地旋转输入矩阵，使其变为: [ [7,4,1], [8,5,2], [9,6,3] ] 示例 2:
     *
     * <p>给定 matrix = [ [ 5, 1, 9,11], [ 2, 4, 8,10], [13, 3, 6, 7], [15,14,12,16] ],
     *
     * <p>原地旋转输入矩阵，使其变为: [ [15,13, 2, 5], [14, 3, 4, 1], [12, 6, 8, 9], [16, 7,10,11] ]
     *
     * @param matrix
     */
    public void rotate(int[][] matrix) {

        int len = matrix.length;
        int rowNum = len / 2;

        for (int i = 0; i <= rowNum; i++) {

            for (int j = i; j < len - i - 1; j++) {
                // 旋转4次
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[len - j - 1][i];
                matrix[len - j - 1][i] = matrix[len - i - 1][len - j - 1];
                matrix[len - i - 1][len - j - 1] = matrix[j][len - i - 1];
                matrix[j][len - i - 1] = tmp;
            }
        }
    }

    @Test
    public void setZeroes() {
        int[][] matrix = {{1, 0}};
        setZeroes(matrix);

        for (int[] a : matrix) {
            log.debug("result:{}", a);
        }
    }

    /**
     * 73. 矩阵置零 给定一个 m x n 的矩阵，如果一个元素为 0，则将其所在行和列的所有元素都设为 0。请使用原地算法。
     *
     * <p>示例 1:
     *
     * <p>输入: [ [1,1,1], [1,0,1], [1,1,1] ] 输出: [ [1,0,1], [0,0,0], [1,0,1] ] 示例 2:
     *
     * <p>输入: [ [0,1,2,0], [3,4,5,2], [1,3,1,5] ] 输出: [ [0,0,0,0], [0,4,5,0], [0,3,1,0] ] 进阶:
     *
     * <p>一个直接的解决方案是使用 O(mn) 的额外空间，但这并不是一个好的解决方案。 一个简单的改进方案是使用 O(m + n) 的额外空间，但这仍然不是最好的解决方案。
     * 你能想出一个常数空间的解决方案吗？
     *
     * @param matrix
     */
    public void setZeroes(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        // 遍历第一行 和 第一列
        boolean colZero = false;
        boolean rowZero = false;
        for (int j = 1; j < cols; j++) {
            if (matrix[0][j] == 0) {
                // 第0行全为0
                rowZero = true;
                break;
            }
        }

        for (int i = 1; i < rows; i++) {
            if (matrix[i][0] == 0) {
                // 第0列全为0
                colZero = true;
                break;
            }
        }
        if (matrix[0][0] == 0) {
            rowZero = true;
            colZero = true;
        }
        // 先把0 移动到第一列和第一行
        for (int i = 1; i < rows; i++) {
            for (int j = 1; j < cols; j++) {

                if (matrix[i][j] == 0) {
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }

        // 遍历第一行
        for (int j = 1; j < cols; j++) {
            if (matrix[0][j] == 0) {
                for (int i = 1; i < rows; i++) {
                    matrix[i][j] = 0;
                }
            }
        }

        // 遍历第一列
        for (int i = 1; i < rows; i++) {
            if (matrix[i][0] == 0) {
                for (int j = 1; j < cols; j++) {
                    matrix[i][j] = 0;
                }
            }
        }

        if (rowZero) {
            // 第一行置为0
            for (int j = 0; j < cols; j++) {
                matrix[0][j] = 0;
            }
        }
        if (colZero) {
            // 第一列置为0
            for (int i = 0; i < rows; i++) {
                matrix[i][0] = 0;
            }
        }
    }

    @Test
    public void missingNumber() {
        int[] nums = {9, 6, 4, 2, 3, 5, 7, 0, 1};
        int result = missingNumber(nums);
        log.debug("result:{}", result);
    }

    /**
     * 缺失数字 给定一个包含 0, 1, 2, ..., n 中 n 个数的序列，找出 0 .. n 中没有出现在序列中的那个数。
     *
     * <p>示例 1:
     *
     * <p>输入: [3,0,1] 输出: 2 示例 2:
     *
     * <p>输入: [9,6,4,2,3,5,7,0,1] 输出: 8 说明: 你的算法应具有线性时间复杂度。你能否仅使用额外常数空间来实现?
     *
     * @param nums
     * @return
     */
    public int missingNumber(int[] nums) {
        int len = nums.length;
        // 由于数组中只缺少了一个数字, 从0开始
        boolean[] arrNums = new boolean[len + 1];

        for (int num : nums) {
            arrNums[num] = true;
        }

        for (int i = 0; i < arrNums.length; i++) {

            if (!arrNums[i]) {
                return i;
            }
        }

        return -1;
    }

    @Test
    public void searchMatrix() {
        int[][] nums = {
            {1, 4, 7, 11, 15},
            {2, 5, 8, 12, 19},
            {3, 6, 9, 16, 22},
            {10, 13, 14, 17, 24},
            {18, 21, 23, 26, 30}
        };
        int target = 18;
        boolean result = searchMatrix(nums, target);
        log.debug("result:{}", result);
    }

    /**
     * 搜索二维矩阵 II 编写一个高效的算法来搜索 m x n 矩阵 matrix 中的一个目标值 target。该矩阵具有以下特性：
     *
     * <p>每行的元素从左到右升序排列。 每列的元素从上到下升序排列。 示例:
     *
     * <p>现有矩阵 matrix 如下：
     *
     * <p>[ [1, 4, 7, 11, 15], [2, 5, 8, 12, 19], [3, 6, 9, 16, 22], [10, 13, 14, 17, 24], [18, 21,
     * 23, 26, 30] ] 给定 target = 5，返回 true。
     *
     * <p>给定 target = 20，返回 false。
     *
     * @param matrix
     * @param target
     * @return
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        // 从右上角开始查，左边小,下边大
        int rows = matrix.length;
        if (rows == 0) {
            return false;
        }
        int cols = matrix[0].length;
        if (cols == 0) {
            return false;
        }
        int i = 0, j = cols - 1;

        while (i < rows && i >= 0 && j < cols && j >= 0) {

            int num = matrix[i][j];
            if (num == target) {
                return true;
            } else if (num > target) {
                j--;
            } else {
                i++;
            }
        }

        return false;
    }

    @Test
    public void longestPalindrome() {

        String s = "aa";
        String result = longestPalindrome(s);
        log.debug("result:{}", result);
    }

    /**
     * 最长回文子串 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
     *
     * <p>示例 1：
     *
     * <p>输入: "babad" 输出: "bab" 注意: "aba" 也是一个有效答案。 示例 2：
     *
     * <p>输入: "cbbd" 输出: "bb"
     *
     * @param s
     * @return
     */
    public String longestPalindrome(String s) {
        char[] chars = s.toCharArray();

        int len = chars.length;
        if (len <= 1) {
            return s;
        }

        String result = String.valueOf(chars[0]);
        for (int i = 0; i < len; i++) {
            // 选取left 和 right
            int left = i, right = i;

            // 当右边元素 == 当前元素 right++
            while (right < len - 1 && chars[i] == chars[right + 1]) {
                right++;
            }
            i = right;
            // 当右边元素 == 左边元素 left--; right++
            while (left > 0 && right < len - 1 && chars[left - 1] == chars[right + 1]) {
                left--;
                right++;
            }
            int charLen = right - left + 1;
            if (charLen > result.length()) {
                result = s.substring(left, right + 1);
            }
        }

        return result;
    }

    @Test
    public void increasingTriplet() {

        int[] nums = {7, 2, 3, 4, 5};

        boolean result = increasingTriplet(nums);
        log.debug("result :{}", result);
    }

    /**
     * 递增的三元子序列 给定一个未排序的数组，判断这个数组中是否存在长度为 3 的递增子序列。
     *
     * <p>数学表达式如下:
     *
     * <p>如果存在这样的 i, j, k, 且满足 0 ≤ i < j < k ≤ n-1， 使得 arr[i] < arr[j] < arr[k] ，返回 true ; 否则返回
     * false 。 说明: 要求算法的时间复杂度为 O(n)，空间复杂度为 O(1) 。
     *
     * <p>示例 1:
     *
     * <p>输入: [1,2,3,4,5] 输出: true 示例 2:
     *
     * <p>输入: [5,4,3,2,1] 输出: false
     *
     * @param nums
     * @return
     */
    public boolean increasingTriplet(int[] nums) {

        int len = nums.length;

        if (len < 3) {
            return false;
        }

        // 记录两个值 num1 和 num2 作为有序子序列的前两个数

        int num1 = nums[0], num2 = Integer.MAX_VALUE;
        int i = 1;
        while (i < len) {
            if (nums[i] < num1) {
                num1 = nums[i];
            } else if (nums[i] > num1 && nums[i] <= num2) {
                num2 = nums[i];
            } else if (nums[i] > num2) {
                return true;
            }
            i++;
        }

        return false;
    }

    @Test
    public void numSubmatrixSumTarget() {}

    /**
     * 给出矩阵 matrix 和目标值 target，返回元素总和等于目标值的非空子矩阵的数量。
     *
     * <p>子矩阵 x1, y1, x2, y2 是满足 x1 <= x <= x2 且 y1 <= y <= y2 的所有单元 matrix[x][y] 的集合。
     *
     * <p>如果 (x1, y1, x2, y2) 和 (x1', y1', x2', y2') 两个子矩阵中部分坐标不同（如：x1 != x1'），那么这两个子矩阵也不同。
     *
     * <p>示例 1：
     *
     * <p>输入：matrix = [[0,1,0],[1,1,1],[0,1,0]], target = 0 输出：4 解释：四个只含 0 的 1x1 子矩阵。 示例 2：
     *
     * <p>输入：matrix = [[1,-1],[-1,1]], target = 0 输出：5 解释：两个 1x2 子矩阵，加上两个 2x1 子矩阵，再加上一个 2x2 子矩阵。
     *
     * @param matrix
     * @param target
     * @return
     */
    public int numSubmatrixSumTarget(int[][] matrix, int target) {
        // 先对每行数据进行处理，求出每行开始到当前位置的和。sum[i][j] = matrix[i][0]+matrix[i][0]+...+matrix[i][j]。
        int row = matrix.length;
        int col = matrix[0].length;
        for (int i = 0; i < row; i++) {
            for (int j = 1; j < col; j++) {
                matrix[i][j] += matrix[i][j - 1];
            }
        }
        // 这样一行中j，k两个之间的区间和可以表示为sum[i][j]-sum[i][k-1]，很快能求出。
        int result = 0;

        return result;
    }

    @Test
    public void merge() {
        int[][] intervals = new int[][] {{1, 3}, {2, 6}, {8, 10}, {15, 18}};

        int[][] result = merge(intervals);
        log.debug("result:{}", result);
    }

    /**
     * 合并区间 给出一个区间的集合，请合并所有重叠的区间。
     *
     * <p>示例 1:
     *
     * <p>输入: [[1,3],[2,6],[8,10],[15,18]] 输出: [[1,6],[8,10],[15,18]] 解释: 区间 [1,3] 和 [2,6] 重叠,
     * 将它们合并为 [1,6]. 示例 2:
     *
     * <p>输入: [[1,4],[4,5]] 输出: [[1,5]] 解释: 区间 [1,4] 和 [4,5] 可被视为重叠区间。
     *
     * @param intervals
     * @return
     */
    public int[][] merge(int[][] intervals) {

        Arrays.sort(
                intervals,
                new Comparator<int[]>() {
                    @Override
                    public int compare(int[] a, int[] b) {
                        return a[0] - b[0];
                    }
                });
        int i = 0;
        int len = intervals.length;
        List<int[]> list = new ArrayList<>();
        while (i < len) {
            int left = intervals[i][0];
            int right = intervals[i][1];
            while (i < len - 1 && right >= intervals[i + 1][0]) {
                right = Math.max(right, intervals[i + 1][1]);
                i++;
            }
            list.add(new int[] {left, right});
            i++;
        }
        return list.toArray(new int[list.size()][2]);
    }

    /**
     * Product of Array Except Self 给定长度为 n 的整数数组 nums，其中 n > 1，返回输出数组 output ，其中 output[i] 等于 nums
     * 中除 nums[i] 之外其余各元素的乘积。
     *
     * <p>示例:
     *
     * <p>输入: [1,2,3,4] 输出: [24,12,8,6] 说明: 请不要使用除法，且在 O(n) 时间复杂度内完成此题。
     *
     * <p>进阶： 你可以在常数空间复杂度内完成这个题目吗？（ 出于对空间复杂度分析的目的，输出数组不被视为额外空间。）
     *
     * @param nums
     * @return
     */
    public int[] productExceptSelf(int[] nums) {
        int len = nums.length;
        int[] output = new int[len];
        // 左边元素的乘积
        int k = 1;
        for (int i = 0; i < len; i++) {
            output[i] = k;
            k = k * nums[i]; // 此时数组存储的是除去当前元素左边的元素乘积
        }

        // 右边元素的乘积
        k = 1;
        for (int i = len - 1; i >= 0; i--) {
            output[i] = k;
            k = k * nums[i]; // 此时数组存储的是除去当前元素左边的元素乘积
        }

        return output;
    }

    @Test
    public void gameOfLife() {
        int[][] board = {{0, 1, 0}, {0, 0, 1}, {1, 1, 1}, {0, 0, 0}};
        gameOfLife(board);
        for (int[] a : board) {
            log.debug("result:{}", a);
        }
    }

    /**
     * 生命游戏 根据百度百科，生命游戏，简称为生命，是英国数学家约翰·何顿·康威在1970年发明的细胞自动机。
     *
     * <p>给定一个包含 m × n 个格子的面板，每一个格子都可以看成是一个细胞。每个细胞具有一个初始状态 live（1）即为活细胞， 或
     * dead（0）即为死细胞。每个细胞与其八个相邻位置（水平，垂直，对角线）的细胞都遵循以下四条生存定律：
     *
     * <p>如果活细胞周围八个位置的活细胞数少于两个，则该位置活细胞死亡； 如果活细胞周围八个位置有两个或三个活细胞，则该位置活细胞仍然存活；
     * 如果活细胞周围八个位置有超过三个活细胞，则该位置活细胞死亡； 如果死细胞周围正好有三个活细胞，则该位置死细胞复活；
     * 根据当前状态，写一个函数来计算面板上细胞的下一个（一次更新后的）状态。下一个状态是通过将上述规则同时应用于当前状态下的每个细胞所形成的， 其中细胞的出生和死亡是同时发生的。
     *
     * <p>示例:
     *
     * <p>输入: [ [0,1,0], [0,0,1], [1,1,1], [0,0,0] ] 输出: [ [0,0,0], [1,0,1], [0,1,1], [0,1,0] ] 进阶:
     *
     * <p>你可以使用原地算法解决本题吗？请注意，面板上所有格子需要同时被更新：你不能先更新某些格子，然后使用它们的更新后的值再更新其他格子。
     * 本题中，我们使用二维数组来表示面板。原则上，面板是无限的，但当活细胞侵占了面板边界时会造成问题。你将如何解决这些问题？
     *
     * @param board
     */
    public void gameOfLife(int[][] board) {
        int rowLen = board.length;
        if (rowLen == 0) {
            return;
        }
        int colLen = board[0].length;
        if (colLen == 0) {
            return;
        }
        // 使用中间状态实现原地元素
        // 活细胞死亡 1 -> -1 -> 0
        // 死细胞复活 0 -> 2 -> 1
        for (int i = 0; i < rowLen; i++) {
            for (int j = 0; j < colLen; j++) {

                updateCell(board, i, j);
            }
        }
        for (int i = 0; i < rowLen; i++) {
            for (int j = 0; j < colLen; j++) {
                if (board[i][j] == -1) {
                    board[i][j] = 0;
                }
                if (board[i][j] == 2) {
                    board[i][j] = 1;
                }
            }
        }
    }

    private void updateCell(int[][] board, int row, int col) {
        // 活细胞死亡 1 -> -1 -> 0
        // 死细胞复活 0 -> 2 -> 1
        int rowLen = board.length;
        int colLen = board[0].length;
        int stat = board[row][col];

        int deadQty = 0;
        int liveQty = 0;
        for (int i = row - 1; i <= row + 1; i++) {
            for (int j = col - 1; j <= col + 1; j++) {
                if (i == row && j == col) {
                    continue;
                }
                if (i < 0 || i >= rowLen) {
                    continue;
                }
                if (j < 0 || j >= colLen) {
                    continue;
                }
                int cellStat = board[i][j];
                if (cellStat == 1 || cellStat == -1) {
                    liveQty++;
                }
                if (cellStat == 0 || cellStat == 2) {
                    deadQty++;
                }
            }
        }
        boolean bLive = false;

        // 如果活细胞周围八个位置的活细胞数少于两个，则该位置活细胞死亡；
        // 如果活细胞周围八个位置有两个或三个活细胞，则该位置活细胞仍然存活；
        // 如果活细胞周围八个位置有超过三个活细胞，则该位置活细胞死亡；
        if (stat == 1) { // 活细胞
            if (liveQty < 2) {
                // 活细胞死亡 1 -> -1 -> 0
                board[row][col] = -1;
            } else if (liveQty > 3) {
                board[row][col] = -1;
            }
        }
        // 如果死细胞周围正好有三个活细胞，则该位置死细胞复活；
        if (stat == 0) {
            if (liveQty == 3) {
                // 死细胞复活 0 -> 2 -> 1
                board[row][col] = 2;
            }
        }
    }

    @Test
    public void firstMissingPositive() {
        int[] nums = {3, 4, -1, 1};
        int result = firstMissingPositive(nums);
        log.debug("result:{}", result);
    }

    /**
     * 第一个缺失的正数 给定一个未排序的整数数组，找出其中没有出现的最小的正整数。
     *
     * <p>示例 1:
     *
     * <p>输入: [1,2,0] 输出: 3 示例 2:
     *
     * <p>输入: [3,4,-1,1] 输出: 2 示例 3:
     *
     * <p>输入: [7,8,9,11,12] 输出: 1 说明:
     *
     * <p>你的算法的时间复杂度应为O(n)，并且只能使用常数级别的空间
     *
     * @param nums
     * @return
     */
    private int firstMissingPositive(int[] nums) {
        // 思路,首先判断 <= 0 的个数为k个，则答案在 1~ （n - k + 1）之间
        int n = nums.length;
        int k = 0;
        for (int i = 0; i < n; i++) {
            if (nums[i] <= 0) {
                k++;
            }
        }
        int len = n - k + 1;
        int[] missNums = new int[len];
        for (int i = 0; i < n; i++) {
            int index = nums[i];
            if (index > 0 && index <= len) {
                missNums[index - 1] = index;
            }
        }
        for (int i = 0; i < missNums.length; i++) {
            if (missNums[i] != (i + 1)) {
                return i + 1;
            }
        }

        return len;
    }

    @Test
    public void maxSlidingWindow() {
        // [7,2,4]
        // 2
        // int[] nums = {1,3,-1,-3,5,3,6,7};
        // int k = 3;
        int[] nums = {-6, -10, -7, -1, -9, 9, -8, -4, 10, -5, 2, 9, 0, -7, 7, 4, -2, -10, 8, 7};
        int k = 7;
        int[] result = maxSlidingDynamic(nums, k);
        log.debug("result:{}", result);
    }

    /**
     * 滑动窗口最大值
     *
     * <p>给定一个数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。
     *
     * <p>返回滑动窗口中的最大值。
     *
     * <p>示例:
     *
     * <p>输入: nums = [1,3,-1,-3,5,3,6,7], 和 k = 3 输出: [3,3,5,5,6,7] 解释:
     *
     * <p>滑动窗口的位置 最大值 --------------- ----- [1 3 -1] -3 5 3 6 7 3 1 [3 -1 -3] 5 3 6 7 3 1 3 [-1 -3
     * 5] 3 6 7 5 1 3 -1 [-3 5 3] 6 7 5 1 3 -1 -3 [5 3 6] 7 6 1 3 -1 -3 5 [3 6 7] 7
     *
     * <p>提示：
     *
     * <p>你可以假设 k 总是有效的，在输入数组不为空的情况下，1 ≤ k ≤ 输入数组的大小。
     *
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSlidingWindow(int[] nums, int k) {

        int len = nums.length;
        if (len * k == 0) {
            return new int[0];
        }
        if (k == 1) {
            return nums;
        }
        int maxLen = len - k + 1;
        int[] result = new int[maxLen];

        // 双端队列
        Deque<Integer> deque = new LinkedList<>();
        for (int i = 0; i < k; i++) {
            while (!deque.isEmpty() && deque.peekLast() < nums[i]) {
                deque.removeLast();
            }
            deque.addLast(nums[i]);
        }
        result[0] = deque.peekFirst();

        for (int i = k; i < nums.length; i++) {
            if (!deque.isEmpty() && deque.peekFirst() == nums[i - k]) {
                deque.removeFirst();
            }
            while (!deque.isEmpty() && deque.peekLast() < nums[i]) {
                deque.removeLast();
            }
            deque.addLast(nums[i]);
            result[i - k + 1] = deque.peekFirst();
        }

        /*int [] left = new int[len];
        left[0] = nums[0];
        int [] right = new int[len];
        right[len - 1] = nums[len - 1];

        int count = len / k;
        for (int i = 0; i < count; i++) {

            for (int j = 1; j < k; j++) {
                int index = k * i + j;
                if (index < len) {

                }

            }
        }*/

        /*int max = Integer.MIN_VALUE;
        for (int i = 0; i < k; i++) {
            if (nums[i] > max) {
                max = nums[i];
            }
        }
        // 思路：先求出前 k 个 元素的 最大值 放入 result[0],所在位置
        // result[1] = result[0]和nums[k] 中较大的数
        // result[2] = result[1]和nums[k+1] 中较大的数
        result[0] = max;
        for (int i = k; i < len; i++) {
            int index = i - k + 1;
            if (result[index - 1] >= nums[i]) {
                result[index] = result[index - 1];
            } else {
                result[index] = nums[i];
            }


        }*/

        return result;
    }

    // 使用双向队列
    public int[] maxSlidingQueue(int[] nums, int k) {
        int len = nums.length;
        if (len * k == 0) {
            return new int[0];
        }
        if (k == 1) {
            return nums;
        }
        int maxLen = len - k + 1;
        int[] result = new int[maxLen];
        Deque<Integer> deque = new LinkedList<>();
        int maxIndex = 0;
        for (int i = 0; i < k; i++) {
            int num = nums[i];
            while (!deque.isEmpty() && deque.peekLast().compareTo(num) < 0) {
                deque.pollLast();
            }
            deque.addLast(num);
            if (num > nums[maxIndex]) {
                maxIndex = i;
            }
        }

        for (int i = k; i < len; i++) {
            int num = nums[i];
            while (!deque.isEmpty() && deque.peekLast().compareTo(num) < 0) {
                deque.pollLast();
            }
            deque.addLast(num);
        }

        return result;
    }

    // 动态规划
    public int[] maxSlidingDynamic(int[] nums, int k) {
        int len = nums.length;
        if (len * k == 0) {
            return new int[0];
        }
        if (k == 1) {
            return nums;
        }
        int maxLen = len - k + 1;
        int[] result = new int[maxLen];
        int[] left = new int[len];
        int[] right = new int[len];
        left[0] = nums[0];
        right[len - 1] = nums[len - 1];
        /*for (int i = 1; i < len; i++) {
            left[i] = Math.max(nums[i], nums[i - 1]);
        }
        for (int i = len - 2; i >= 0; i--) {
            right[i] = Math.max(nums[i], nums[i + 1]);
        }*/

        for (int i = 1; i < len; i++) {
            // from left to right
            if (i % k == 0) left[i] = nums[i]; // block_start
            else left[i] = Math.max(left[i - 1], nums[i]);

            // from right to left
            int j = len - i - 1;
            if ((j + 1) % k == 0) right[j] = nums[j]; // block_end
            else right[j] = Math.max(right[j + 1], nums[j]);
        }

        for (int i = 0; i < maxLen; i++) {
            result[i] = Math.max(left[i + k - 1], right[i]);
        }
        return result;
    }

    @Test
    public void testCalculate() {
        // log.debug("a:{}",3/2);

        String s = " 3/2 ";
        int result = calculate(s);
        log.debug("result:{}", result);
    }

    /**
     * 基本计算器 II 实现一个基本的计算器来计算一个简单的字符串表达式的值。
     *
     * <p>字符串表达式仅包含非负整数，+， - ，*，/ 四种运算符和空格 。 整数除法仅保留整数部分。
     *
     * <p>示例 1:
     *
     * <p>输入: "3+2*2" 输出: 7 示例 2:
     *
     * <p>输入: " 3/2 " 输出: 1 示例 3:
     *
     * <p>输入: " 3+5 / 2 " 输出: 5 说明：
     *
     * <p>你可以假设所给定的表达式都是有效的。 请不要使用内置的库函数 eval。
     *
     * @param s
     * @return
     */
    public int calculate(String s) {

        // 思路： 使用两个栈，分别存放运算的数值 和运算符
        Deque<Integer> numDeque = new LinkedList<>();
        Deque<Character> calculateDeque = new LinkedList<>();

        int result = 0;
        int num = 0;
        // 是否计算
        boolean isCal = false;
        for (char c : s.toCharArray()) {
            if (c == ' ') {
                continue;
            }
            if (isNumber(c)) {
                num = num * 10 + (c - '0');
            } else {
                if (isCal) {
                    // 先算 乘除
                    num = calculateNum(numDeque.removeLast(), num, calculateDeque.removeLast());
                }
                numDeque.addLast(num);
                calculateDeque.addLast(c);
                if (isMulOp(c)) {
                    isCal = true;
                } else if (isAndOp(c)) {
                    isCal = false;
                }
                num = 0;
            }
        }

        if (isCal) {
            // 先算 乘除
            num = calculateNum(numDeque.removeLast(), num, calculateDeque.removeLast());
        }
        numDeque.addLast(num);
        // 算加减法, 使用队列的特性

        result = numDeque.removeFirst();
        while (!numDeque.isEmpty()) {
            result = calculateNum(result, numDeque.removeFirst(), calculateDeque.removeFirst());
        }

        return result;
    }

    public boolean isAndOp(char c) {
        return c == '+' || c == '-';
    }

    public boolean isMulOp(char c) {
        return c == '*' || c == '/';
    }

    private boolean isNumber(char c) {
        switch (c) {
            case '0':
            case '1':
            case '2':
            case '3':
            case '4':
            case '5':
            case '6':
            case '7':
            case '8':
            case '9':
                return true;
        }
        return false;
    }

    private int calculateNum(int a, int b, char c) {
        int result = 0;
        switch (c) {
            case '+':
                result = a + b;
                break;
            case '-':
                result = a - b;
                break;
            case '*':
                result = a * b;
                break;
            case '/':
                result = a / b;
                break;
        }
        return result;
    }

    @Test
    public void testCalculate2() {
        // log.debug("a:{}",3/2);

        String s = "(1-(3-4))";
        int result = calculate2(s);
        log.debug("result:{}", result);
    }

    /**
     * 实现一个基本的计算器来计算一个简单的字符串表达式的值。
     *
     * <p>字符串表达式可以包含左括号 ( ，右括号 )，加号 + ，减号 -，非负整数和空格  。
     *
     * <p>示例 1:
     *
     * <p>输入: "1 + 1" 输出: 2 示例 2:
     *
     * <p>输入: " 2-1 + 2 " 输出: 3 示例 3:
     *
     * <p>输入: "(1+(4+5+2)-3)+(6+8)" 输出: 23 说明：
     *
     * <p>你可以假设所给定的表达式都是有效的。 请不要使用内置的库函数 eval。
     *
     * @param s
     * @return
     */
    public int calculate2(String s) {

        int result = 0;
        // 思路： 使用两个栈，分别存放运算的数值 和运算符
        Deque<Integer> numDeque = new LinkedList<>();
        Deque<Character> calculateDeque = new LinkedList<>();

        int num = 0;
        Character lastChar = null;
        // 是否计算
        boolean isCal = false;
        for (char c : s.toCharArray()) {
            if (c == ' ') {
                continue;
            }
            if (isNumber(c)) {
                lastChar = null;
                num = num * 10 + (c - '0');
            } else if (c == '(') {
                calculateDeque.addLast(c);
            } else if (c == ')') {
                Deque<Integer> deque1 = new LinkedList<>();
                Deque<Character> deque2 = new LinkedList<>();

                while (true) {
                    Character cs = calculateDeque.removeLast();
                    if (cs == '(') {
                        break;
                    }
                    deque2.addFirst(cs);
                    deque1.addFirst(numDeque.removeLast());
                }
                if (Objects.equals(lastChar, ')')) {
                    deque1.addFirst(numDeque.removeLast());
                } else {
                    deque1.addLast(num);
                }

                num = deque1.removeFirst();
                while (!deque1.isEmpty()) {
                    num = calculateNum(num, deque1.removeFirst(), deque2.removeFirst());
                }
                numDeque.addLast(num);
                num = 0;
                lastChar = ')';
            } else {

                if (lastChar == null) {
                    if (isCal) {
                        // 先算 乘除
                        num = calculateNum(numDeque.removeLast(), num, calculateDeque.removeLast());
                    }
                    numDeque.addLast(num);
                }
                calculateDeque.addLast(c);
                if (isMulOp(c)) {
                    isCal = true;
                } else if (isAndOp(c)) {
                    isCal = false;
                }
                num = 0;
            }
        }

        if (isCal) {
            // 先算 乘除
            num = calculateNum(numDeque.removeLast(), num, calculateDeque.removeLast());
        }
        if (lastChar == null) {
            numDeque.addLast(num);
        }

        // 算加减法, 使用队列的特性

        result = numDeque.removeFirst();
        while (!numDeque.isEmpty()) {
            result = calculateNum(result, numDeque.removeFirst(), calculateDeque.removeFirst());
        }

        return result;
    }

    @Test
    public void floodFill() {
        int[][] image = {{1, 1, 1}, {1, 1, 0}, {1, 0, 1}};
        int sr = 1, sc = 1;
        int newColor = 2;

        int[][] result = floodFill(image, sr, sc, newColor);
        for (int[] a : result) {
            log.debug("result:{}", a);
        }
    }

    /**
     * 图像渲染 有一幅以二维整数数组表示的图画，每一个整数表示该图画的像素值大小，数值在 0 到 65535 之间。
     *
     * <p>给你一个坐标 (sr, sc) 表示图像渲染开始的像素值（行 ，列）和一个新的颜色值 newColor，让你重新上色这幅图像。
     *
     * <p>为了完成上色工作，从初始坐标开始，记录初始坐标的上下左右四个方向上像素值与初始坐标相同的相连像素点，接着再记录这四个方向上符合条件的像素点与他们对应四个方向上像素值与初始坐标相同的相连像素点，……，重复该过程。将所有有记录的像素点的颜色值改为新的颜色值。
     *
     * <p>最后返回经过上色渲染后的图像。
     *
     * <p>示例 1:
     *
     * <p>输入: image = [[1,1,1],[1,1,0],[1,0,1]] sr = 1, sc = 1, newColor = 2 输出:
     * [[2,2,2],[2,2,0],[2,0,1]] 解析: 在图像的正中间，(坐标(sr,sc)=(1,1)), 在路径上所有符合条件的像素点的颜色都被更改成2。
     * 注意，右下角的像素没有更改为2， 因为它不是在上下左右四个方向上与初始点相连的像素点。 注意:
     *
     * <p>image 和 image[0] 的长度在范围 [1, 50] 内。 给出的初始点将满足 0 <= sr < image.length 和 0 <= sc <
     * image[0].length。 image[i][j] 和 newColor 表示的颜色值在范围 [0, 65535]内。
     *
     * @param image
     * @param sr
     * @param sc
     * @param newColor
     * @return
     */
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        // 思路： 递归

        int oldColor = image[sr][sc];
        if (oldColor != newColor) {
            floodFill(image, sr, sc, oldColor, newColor);
        }

        return image;
    }

    public void floodFill(int[][] image, int i, int j, int oldColor, int newColor) {
        if (i < 0 || i >= image.length) {
            return;
        }
        if (j < 0 || j >= image[0].length) {
            return;
        }
        if (image[i][j] == oldColor) {
            image[i][j] = newColor;
            floodFill(image, i - 1, j, oldColor, newColor);
            floodFill(image, i + 1, j, oldColor, newColor);
            floodFill(image, i, j - 1, oldColor, newColor);
            floodFill(image, i, j + 1, oldColor, newColor);
        }
    }

    @Test
    public void updateMatrix() {
        int[][] matrix = {
            {1, 0, 1, 1, 0, 0, 1, 0, 0, 1},
            {0, 1, 1, 0, 1, 0, 1, 0, 1, 1},
            {0, 0, 1, 0, 1, 0, 0, 1, 0, 0},
            {1, 0, 1, 0, 1, 1, 1, 1, 1, 1},
            {0, 1, 0, 1, 1, 0, 0, 0, 0, 1},
            {0, 0, 1, 0, 1, 1, 1, 0, 1, 0},
            {0, 1, 0, 1, 0, 1, 0, 0, 1, 1},
            {1, 0, 0, 0, 1, 1, 1, 1, 0, 1},
            {1, 1, 1, 1, 1, 1, 1, 0, 1, 0},
            {1, 1, 1, 1, 0, 1, 0, 0, 1, 1}
        };
        // {{0,0,0},{0,1,0},{1,1,1}};

        int[][] result = updateMatrix(matrix);
        for (int[] a : result) {
            log.debug("result:{}", a);
        }
    }
    /**
     * 01 矩阵 给定一个由 0 和 1 组成的矩阵，找出每个元素到最近的 0 的距离。
     *
     * <p>两个相邻元素间的距离为 1 。
     *
     * <p>示例 1: 输入:
     *
     * <p>0 0 0 0 1 0 0 0 0 输出:
     *
     * <p>0 0 0 0 1 0 0 0 0 示例 2: 输入:
     *
     * <p>0 0 0 0 1 0 1 1 1 输出:
     *
     * <p>0 0 0 0 1 0 1 2 1 注意:
     *
     * <p>给定矩阵的元素个数不超过 10000。 给定矩阵中至少有一个元素是 0。 矩阵中的元素只在四个方向上相邻: 上、下、左、右。
     *
     * @param matrix
     * @return
     */
    public int[][] updateMatrix(int[][] matrix) {
        // 思路 先把0元素的距离赋值为0，然后从0项四周扩散
        int row = matrix.length;
        int col = matrix[0].length;
        int[][] result = new int[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (matrix[i][j] != 0) {
                    // 防溢出
                    result[i][j] = Integer.MAX_VALUE >> 1;
                }
            }
        }
        // 从左上向右下 遍历 每次与 上边元素 和 左边元素 比较
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (i > 0) {
                    // 与 上边元素 比较
                    result[i][j] = Math.min(result[i][j], result[i - 1][j] + 1);
                }
                if (j > 0) {
                    // 与 左边元素 比较
                    result[i][j] = Math.min(result[i][j], result[i][j - 1] + 1);
                }
            }
        }
        // 从右下向左上 遍历 每次与 下边元素 和 右边元素 比较
        for (int i = row - 1; i >= 0; i--) {
            for (int j = col - 1; j >= 0; j--) {
                if (i < row - 1) {
                    // 与 下边元素 比较
                    result[i][j] = Math.min(result[i][j], result[i + 1][j] + 1);
                }
                if (j < col - 1) {
                    // 与 右边元素 比较
                    result[i][j] = Math.min(result[i][j], result[i][j + 1] + 1);
                }
            }
        }
        /*for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (matrix[i][j] != 0) {
                    result[i][j] = Integer.MAX_VALUE;
                }
            }
        }
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (result[i][j] == 0) {
                    updateMatrix(result,i - 1,j,1);
                    updateMatrix(result,i + 1,j,1);
                    updateMatrix(result,i,j - 1,1);
                    updateMatrix(result,i,j + 1,1);
                }
            }
        }*/
        return result;
    }

    private void updateMatrix(int[][] result, int i, int j, int value) {
        if (i < 0 || i >= result.length) {
            return;
        }
        if (j < 0 || j >= result[0].length) {
            return;
        }
        if (value < result[i][j]) {
            result[i][j] = value;
            updateMatrix(result, i - 1, j, value + 1);
            updateMatrix(result, i + 1, j, value + 1);
            updateMatrix(result, i, j - 1, value + 1);
            updateMatrix(result, i, j + 1, value + 1);
        }
    }

    @Test
    public void canVisitAllRooms() {
        List<List<Integer>> rooms = new ArrayList<>();
        rooms.add(Arrays.asList(6, 7, 8));
        rooms.add(Arrays.asList(5, 4, 9));
        rooms.add(new ArrayList<>());
        rooms.add(Arrays.asList(8));
        rooms.add(Arrays.asList(4));
        rooms.add(new ArrayList<>());
        rooms.add(Arrays.asList(1, 9, 2, 3));
        rooms.add(Arrays.asList(7));
        rooms.add(Arrays.asList(6, 5));
        rooms.add(Arrays.asList(2, 3, 1));
        /*rooms.add(Arrays.asList(1,3));
        rooms.add(Arrays.asList(3,0,1));
        rooms.add(Arrays.asList(2));
        rooms.add(Arrays.asList(0));*/
        // rooms.add(new ArrayList<>());
        boolean result = canVisitAllRooms(rooms);
        log.debug("result:{}", result);
    }

    /**
     * 钥匙和房间 有 N 个房间，开始时你位于 0 号房间。每个房间有不同的号码：0，1，2，...，N-1，并且房间里可能有一些钥匙能使你进入下一个房间。
     *
     * <p>在形式上，对于每个房间 i 都有一个钥匙列表 rooms[i]，每个钥匙 rooms[i][j] 由 [0,1，...，N-1] 中的一个整数表示，其中 N =
     * rooms.length。 钥匙 rooms[i][j] = v 可以打开编号为 v 的房间。
     *
     * <p>最初，除 0 号房间外的其余所有房间都被锁住。
     *
     * <p>你可以自由地在房间之间来回走动。
     *
     * <p>如果能进入每个房间返回 true，否则返回 false。
     *
     * <p>示例 1：
     *
     * <p>输入: [[1],[2],[3],[]] 输出: true 解释: 我们从 0 号房间开始，拿到钥匙 1。 之后我们去 1 号房间，拿到钥匙 2。 然后我们去 2 号房间，拿到钥匙
     * 3。 最后我们去了 3 号房间。 由于我们能够进入每个房间，我们返回 true。 示例 2：
     *
     * <p>输入：[[1,3],[3,0,1],[2],[0]] 输出：false 解释：我们不能进入 2 号房间。 提示：
     *
     * <p>1 <= rooms.length <= 1000 0 <= rooms[i].length <= 1000 所有房间中的钥匙数量总计不超过 3000。
     *
     * @param rooms
     * @return
     */
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        int len = rooms.size();
        boolean[] visit = new boolean[len];
        visit[0] = true;
        Queue<Integer> queue = new LinkedList<>();
        List<Integer> list = rooms.get(0);

        for (int index : list) {
            queue.offer(index);
        }
        while (!queue.isEmpty()) {
            int key = queue.poll();

            if (key < len) {
                visit[key] = true;
                for (int index : rooms.get(key)) {
                    if (!visit[index]) {
                        queue.offer(index);
                    }
                }
            }
        }
        for (boolean b : visit) {
            if (!b) {
                return false;
            }
        }
        return true;
    }

    @Test
    public void maxProduct() {
        int[] nums = {-2, 0, -1};
        int result = maxProduct(nums);
        log.debug("result:{}", result);
    }

    /**
     * 乘积最大子序列 给定一个整数数组 nums ，找出一个序列中乘积最大的连续子序列（该序列至少包含一个数）。
     *
     * <p>示例 1:
     *
     * <p>输入: [2,3,-2,4] 输出: 6 解释: 子数组 [2,3] 有最大乘积 6。 示例 2:
     *
     * <p>输入: [-2,0,-1] 输出: 0 解释: 结果不能为 2, 因为 [-2,-1] 不是子数组。
     *
     * @param nums
     * @return
     */
    public int maxProduct(int[] nums) {

        // 思路，由于都是整数，建立最大和最小数组
        int len = nums.length;
        if (len == 0) {
            return 0;
        }
        if (len == 1) {
            return nums[0];
        }
        int[] maxNums = new int[len];
        int[] minNums = new int[len];
        maxNums[0] = nums[0];
        minNums[0] = nums[0];
        for (int i = 1; i < len; i++) {
            int num = nums[i];
            maxNums[i] = Math.max(maxNums[i - 1] * num, num);
            minNums[i] = Math.min(minNums[i - 1] * num, num);
            if (num < 0) {
                // 如果最小值是个负数
                if (minNums[i - 1] < 0) {
                    maxNums[i] = Math.max(minNums[i - 1] * num, maxNums[i]);
                }
                // 如果最大值是个整数
                if (maxNums[i - 1] > 0) {
                    minNums[i] = Math.min(maxNums[i - 1] * num, minNums[i]);
                }
            }
        }
        int result = maxNums[0];
        for (int num : maxNums) {
            if (num > result) {
                result = num;
            }
        }

        return result;
    }

    @Test
    public void generateMatrix() {
        int n = 4;
        int[][] result = generateMatrix(n);
        for (int[] a : result) {
            log.debug("result:{}", a);
        }
    }

    /**
     * 螺旋矩阵 II 给定一个正整数 n，生成一个包含 1 到 n2 所有元素，且元素按顺时针顺序螺旋排列的正方形矩阵。
     *
     * <p>示例:
     *
     * <p>输入: 3 输出: [ [ 1, 2, 3 ], [ 8, 9, 4 ], [ 7, 6, 5 ] ]
     *
     * @param n
     * @return
     */
    public int[][] generateMatrix(int n) {

        int[][] matrix = new int[n][n];
        if (n == 1) {
            matrix[0][0] = 1;
            return matrix;
        }
        // 移动方向，1 从左至右 2 从上至下 3 从右至左 4 从下至上
        int moveType = 1;
        int xMin = 0;
        int xMax = n - 1;
        int yMin = 0;
        int yMax = n - 1;
        int x = 0, y = 0;
        int num = 1;
        while (x >= xMin && x <= xMax && y >= yMin && y <= yMax) {

            matrix[y][x] = num++;
            if (xMin == xMax && yMax == yMin) {
                break;
            }

            if (moveType == 1) {

                x++;
                // 从左至右 移动到最右边时 yMin = y 方向改变
                if (x > xMax) {
                    x = xMax;
                    yMin = ++y;

                    moveType = 2;
                }
            } else if (moveType == 2) {
                y++;
                // 从上至下 移动到最下边时 xMax = x 方向改变
                if (y > yMax) {
                    y = yMax;
                    xMax = --x;
                    moveType = 3;
                }
            } else if (moveType == 3) {
                x--;
                // 从右至左 移动到最左边时 yMax = y - 1 方向改变
                if (x < xMin) {
                    x = xMin;
                    yMax = --y;
                    moveType = 4;
                }
            } else {
                y--;
                // 从下至上 移动到最上边时 xMin = x+1  方向改变
                if (y < yMin) {
                    y = yMin;
                    xMin = ++x;
                    moveType = 1;
                }
            }
        }
        return matrix;
    }

    @Test
    public void threeSumClosest() {
        int[] nums = {-1, 1, 1, -4};
        int target = 1;
        int result = threeSumClosest(nums, target);
        log.debug("result : {}", result);
    }

    /**
     * 最接近的三数之和 给定一个包括 n 个整数的数组 nums 和 一个目标值 target。找出 nums 中的三个整数，使得它们的和与 target
     * 最接近。返回这三个数的和。假定每组输入只存在唯一答案。
     *
     * <p>例如，给定数组 nums = [-1，2，1，-4], 和 target = 1.
     *
     * <p>与 target 最接近的三个数的和为 2. (-1 + 2 + 1 = 2).
     *
     * @param nums
     * @param target
     * @return
     */
    public int threeSumClosest(int[] nums, int target) {

        int result = 0;
        if (nums.length < 3) {
            return 0;
        }
        if (nums.length == 3) {
            return nums[0] + nums[1] + nums[2];
        }
        if (target >= 0) {
            result = Integer.MAX_VALUE;
        } else {
            result = Integer.MAX_VALUE + target;
        }
        // 思路：先排序，然后选择一个主元，然后使用双指针遍历剩下的元素
        Arrays.sort(nums);

        for (int i = 0; i < nums.length - 2; i++) {
            // 选择 i 位置 做主元
            int num = nums[i];
            int tar = target - num;
            int left = i + 1, right = nums.length - 1;

            while (left < right) {
                int sum = nums[left] + nums[right];
                if (sum == tar) {
                    return target;
                }
                // sub 三数之和 与 target的差值
                int sub = 0;
                if (sum < tar) {
                    // 差值 较小
                    sub = tar - sum;
                    left++;
                } else {
                    sub = sum - tar;
                    right--;
                }
                // 如果 result 与 target 的差值较大，修改result
                if (Math.abs(result - target) > sub) {
                    result = sum + num;
                }
            }
        }
        return result;
    }

    @Test
    public void maxAreaOfIsland() {
        int[][] grid = {
            {0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
            {0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 0, 0},
            {0, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0}
        };
        int result = maxAreaOfIsland(grid);
        log.debug("result:{}", result);
    }

    /**
     * 岛屿的最大面积 给定一个包含了一些 0 和 1的非空二维数组 grid , 一个 岛屿 是由四个方向 (水平或垂直) 的 1 (代表土地)
     * 构成的组合。你可以假设二维矩阵的四个边缘都被水包围着。
     *
     * <p>找到给定的二维数组中最大的岛屿面积。(如果没有岛屿，则返回面积为0。)
     *
     * <p>示例 1:
     *
     * <p>[[0,0,1,0,0,0,0,1,0,0,0,0,0], [0,0,0,0,0,0,0,1,1,1,0,0,0], [0,1,1,0,1,0,0,0,0,0,0,0,0],
     * [0,1,0,0,1,1,0,0,1,0,1,0,0], [0,1,0,0,1,1,0,0,1,1,1,0,0], [0,0,0,0,0,0,0,0,0,0,1,0,0],
     * [0,0,0,0,0,0,0,1,1,1,0,0,0], [0,0,0,0,0,0,0,1,1,0,0,0,0]] 对于上面这个给定矩阵应返回
     * 6。注意答案不应该是11，因为岛屿只能包含水平或垂直的四个方向的‘1’。
     *
     * <p>示例 2:
     *
     * <p>[[0,0,0,0,0,0,0,0]] 对于上面这个给定的矩阵, 返回 0。
     *
     * <p>注意: 给定的矩阵grid 的长度和宽度都不超过 50。
     *
     * @param grid
     * @return
     */
    public int maxAreaOfIsland(int[][] grid) {

        if (grid.length == 0) {
            return 0;
        }

        if (grid[0].length == 0) {
            return 0;
        }
        int m = grid.length;
        int n = grid[0].length;
        int maxArea = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {

                    int area = updateAround(grid, i, j);
                    if (area > maxArea) {
                        maxArea = area;
                    }
                }
            }
        }
        return maxArea;
    }

    private int updateAround(int[][] grid, int row, int col) {

        int area = 0;
        int rowLen = grid.length;
        int colLen = grid[0].length;
        if (row < 0 || row >= rowLen) {
            return 0;
        }
        if (col < 0 || col >= colLen) {
            return 0;
        }

        if (grid[row][col] != 1) {
            return 0;
        }
        // 更新坐标
        grid[row][col] = 2;
        area++;
        // 更新相邻坐标
        area += updateAround(grid, row - 1, col);
        area += updateAround(grid, row + 1, col);
        area += updateAround(grid, row, col - 1);
        area += updateAround(grid, row, col + 1);

        return area;
    }

    @Test
    public void findLengthOfLCIS() {
        int[] nums = {2, 2, 2, 2, 2};
        int result = findLengthOfLCIS(nums);
        log.debug("result:{}", result);
    }

    /**
     * 最长连续递增序列 给定一个未经排序的整数数组，找到最长且连续的的递增序列。
     *
     * <p>示例 1:
     *
     * <p>输入: [1,3,5,4,7] 输出: 3 解释: 最长连续递增序列是 [1,3,5], 长度为3。 尽管 [1,3,5,7] 也是升序的子序列,
     * 但它不是连续的，因为5和7在原数组里被4隔开。 示例 2:
     *
     * <p>输入: [2,2,2,2,2] 输出: 1 解释: 最长连续递增序列是 [2], 长度为1。
     *
     * @param nums
     * @return
     */
    public int findLengthOfLCIS(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }

        int maxLen = 1;
        int len = 1;
        for (int i = 1; i < nums.length; i++) {

            if (nums[i] > nums[i - 1]) {
                len++;
            } else {
                len = 1;
            }
            if (len > maxLen) {
                maxLen = len;
            }
        }

        return maxLen;
    }

    @Test
    public void longestConsecutive() {
        int[] nums = {100, 4, 200, 1, 3, 2};
        int result = longestConsecutive(nums);
        log.debug("result:{}", result);
    }

    /**
     * 最长连续序列 给定一个未排序的整数数组，找出最长连续序列的长度。
     *
     * <p>要求算法的时间复杂度为 O(n)。
     *
     * <p>示例:
     *
     * <p>输入: [100, 4, 200, 1, 3, 2] 输出: 4 解释: 最长连续序列是 [1, 2, 3, 4]。它的长度为 4。
     *
     * @param nums
     * @return
     */
    public int longestConsecutive(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }

        int maxLen = 1;

        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        for (int num : nums) {
            if (!set.contains(num - 1)) {
                int len = 1;
                while (set.contains(++num)) {
                    len++;
                }
                if (len > maxLen) {
                    maxLen = len;
                }
            }
        }

        return maxLen;
    }

    @Test
    public void getPermutation() {
        int n = 2, k = 2;
        String result = getPermutation(n, k);
        log.debug("result:{}", result);
    }
    /**
     * 第k个排列 给出集合 [1,2,3,…,n]，其所有元素共有 n! 种排列。
     *
     * <p>按大小顺序列出所有排列情况，并一一标记，当 n = 3 时, 所有排列如下：
     *
     * <p>"123" "132" "213" "231" "312" "321" 给定 n 和 k，返回第 k 个排列。
     *
     * <p>说明：
     *
     * <p>给定 n 的范围是 [1, 9]。 给定 k 的范围是[1, n!]。 示例 1:
     *
     * <p>输入: n = 3, k = 3 输出: "213" 示例 2:
     *
     * <p>输入: n = 4, k = 9 输出: "2314"
     *
     * @param n
     * @param k
     * @return
     */
    public String getPermutation(int n, int k) {
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            list.add(i);
        }

        StringBuilder result = new StringBuilder();
        // 集合 [1,2,3,…,n] 有 n! 种排列

        // 判断 第一位
        for (int i = 1; i < n; i++) {
            int index = 0;
            if (k > 1) {
                int len = getLen(n - i);
                index = (k - 1) / len;
                k -= len * index;
            }
            result.append(list.remove(index));
        }
        result.append(list.get(0));
        return result.toString();
    }

    @Test
    public void testGetLen() {
        int result = getLen(3);
        log.debug("result:{}", result);
    }

    private int getLen(int n) {
        int result = 1;
        while (n > 1) {
            result *= n--;
        }

        return result;
    }

    @Test
    public void trap() {
        int[] height = {5, 2, 1, 2, 1, 5};
        int result = trap(height);
        log.debug("result:{}", result);
    }

    /**
     * 接雨水
     *
     * <p>给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。 上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1]
     * 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。 感谢 Marcos 贡献此图。
     *
     * <p>示例:
     *
     * <p>输入: [0,1,0,2,1,0,1,3,2,1,2,1] 输出: 6
     *
     * @param height
     * @return
     */
    public int trap(int[] height) {
        int len = height.length;
        if (len <= 2) {
            return 0;
        }
        // 波峰数组
        int[] leftHeight = new int[len];
        leftHeight[0] = height[0];
        for (int i = 1; i < len; i++) {
            leftHeight[i] = Math.max(height[i], leftHeight[i - 1]);
        }
        int[] rightHeight = new int[len];
        rightHeight[len - 1] = height[len - 1];
        for (int i = len - 2; i > 0; i--) {
            rightHeight[i] = Math.max(height[i], rightHeight[i + 1]);
        }

        int result = 0;
        for (int i = 1; i < len - 1; i++) {
            result += Math.min(leftHeight[i], rightHeight[i]) - height[i];
        }

        return result;
    }

    @Test
    public void findCircleNum() {
        int[][] M = {{1, 1, 0}, {1, 1, 0}, {0, 0, 1}};
        int result = findCircleNum(M);
        log.debug("result:{}", result);
    }
    /**
     * 朋友圈 班上有 N 名学生。其中有些人是朋友，有些则不是。他们的友谊具有是传递性。如果已知 A 是 B 的朋友，B 是 C 的朋友，那么我们可以认为 A 也是 C
     * 的朋友。所谓的朋友圈，是指所有朋友的集合。
     *
     * <p>给定一个 N * N 的矩阵 M，表示班级中学生之间的朋友关系。如果M[i][j] = 1，表示已知第 i 个和 j
     * 个学生互为朋友关系，否则为不知道。你必须输出所有学生中的已知的朋友圈总数。
     *
     * <p>示例 1:
     *
     * <p>输入: [[1,1,0], [1,1,0], [0,0,1]] 输出: 2 说明：已知学生0和学生1互为朋友，他们在一个朋友圈。 第2个学生自己在一个朋友圈。所以返回2。 示例
     * 2:
     *
     * <p>输入: [[1,1,0], [1,1,1], [0,1,1]] 输出: 1
     * 说明：已知学生0和学生1互为朋友，学生1和学生2互为朋友，所以学生0和学生2也是朋友，所以他们三个在一个朋友圈，返回1。 注意：
     *
     * <p>N 在[1,200]的范围内。 对于所有学生，有M[i][i] = 1。 如果有M[i][j] = 1，则有M[j][i] = 1。
     *
     * @param M
     * @return
     */
    public int findCircleNum(int[][] M) {
        int result = 0;
        int len = M.length;
        boolean[] visited = new boolean[len];
        // 深度优先遍历
        for (int i = 0; i < len; i++) {
            if (!visited[i]) {
                dfs(M, visited, i);
                result++;
            }
        }

        return result;
    }

    public void dfs(int[][] M, boolean[] visited, int i) {
        for (int j = 0; j < M.length; j++) {
            if (M[i][j] == 1 && !visited[j]) {
                visited[j] = true;
                dfs(M, visited, j);
            }
        }
    }

    @Test
    public void validUtf8() {
        int[] data = {115, 100, 102, 231, 154, 132, 13, 10};
        boolean result = validUtf8(data);
        log.debug("result:{}", result);
    }

    /**
     * UTF-8 编码验证 UTF-8 中的一个字符可能的长度为 1 到 4 字节，遵循以下的规则：
     *
     * <p>对于 1 字节的字符，字节的第一位设为0，后面7位为这个符号的unicode码。 对于 n 字节的字符 (n > 1)，第一个字节的前 n 位都设为1，第 n+1 位设为0，
     * 后面字节的前两位一律设为10。剩下的没有提及的二进制位，全部为这个符号的unicode码。 这是 UTF-8 编码的工作方式：
     *
     * <p>Char. number range | UTF-8 octet sequence (hexadecimal) | (binary)
     * --------------------+--------------------------------------------- 0000 0000-0000 007F |
     * 0xxxxxxx 0000 0080-0000 07FF | 110xxxxx 10xxxxxx 0000 0800-0000 FFFF | 1110xxxx 10xxxxxx
     * 10xxxxxx 0001 0000-0010 FFFF | 11110xxx 10xxxxxx 10xxxxxx 10xxxxxx 给定一个表示数据的整数数组，返回它是否为有效的
     * utf-8 编码。
     *
     * <p>注意: 输入是整数数组。只有每个整数的最低 8 个有效位用来存储数据。这意味着每个整数只表示 1 字节的数据。
     *
     * <p>示例 1:
     *
     * <p>data = [197, 130, 1], 表示 8 位的序列: 11000101 10000010 00000001.
     *
     * <p>返回 true 。 这是有效的 utf-8 编码，为一个2字节字符，跟着一个1字节字符。 示例 2:
     *
     * <p>data = [235, 140, 4], 表示 8 位的序列: 11101011 10001100 00000100.
     *
     * <p>返回 false 。 前 3 位都是 1 ，第 4 位为 0 表示它是一个3字节字符。 下一个字节是开头为 10 的延续字节，这是正确的。 但第二个延续字节不以 10
     * 开头，所以是不符合规则的。
     *
     * @param data
     * @return
     */
    public boolean validUtf8(int[] data) {
        int len = data.length;
        if (len == 0) {
            return false;
        }
        for (int i = 0; i < len; i++) {
            if (data[i] >= (1 << 8) || data[i] < 0) {
                return false;
            }
        }

        int mask1 = 1 << 7;
        int mask2 = 1 << 6;
        int start = 0;
        // 判断 有几个字节
        while (start < len) {
            // 字节数
            int byteCount = getByteCount(data[start]);
            if (byteCount == 0) {
                return false;
            }
            // 字节数 超过 data 长度，不符合规则
            if (start + byteCount > len) {
                return false;
            }
            // 判断 剩余 byteCount - 1个字节 是否符合规则
            for (int i = 1; i < byteCount; i++) {
                // 是否 10 开头, 右移6位
                /*int num = data[start + i]>>6;
                if (num != 2) {
                    return false;
                }*/
                if ((data[start + i] & mask1) == 0 || (mask2 & data[start + i]) != 0) {
                    log.debug("start:{}", start);
                    return false;
                }
            }
            start += byteCount;
        }

        /*if (len > 4) {
            return false;
        }


        if (len == 1) {
            // 是否 0 开头, 右移7位
            int num = data[0]>>7;
            if (num != 0) {
                return false;
            }
        } else {
            // 是否110，1110，11110 开头，右移 7 - len 位
            int num = data[0]>>(7-len);
            // 110，1110，11110 的规律 1 左移 len + 1 位 - 2
            int k = (1<<(len+1)) - 2;
            if (num != k) {
                return false;
            }

        }

        for (int i = 1; i < len; i++) {
            // 是否 10 开头, 右移6位
            int num = data[i]>>6;

            if (num != 2) {
                return false;
            }
        }*/

        return true;
    }

    @Test
    public void testGetByteCount() {
        log.debug("a:{}", getByteCount(115));
    }

    private int getByteCount(int data) {

        int mask = 1 << 7;
        int count = 0;
        // 判断是否 2 ~ 4 个字节
        // log.debug("a:{},b:{}",mask,(mask & data) );
        while ((mask & data) != 0) {
            count++;
            if (count > 4) {
                return 0;
            }
            mask = mask >> 1;
        }
        if (count == 1) {
            return 0;
        }
        // log.debug("count:{}",count);
        // 判断是否 1 个字节
        return count == 0 ? 1 : count;
    }

    @Test
    public void sumSubarrayMins() {
        int[] A = {3, 1, 2, 4};
        int result = sumSubarrayMins(A);
        log.debug("result:{}", result);
    }

    /**
     * 子数组的最小值之和 给定一个整数数组 A，找到 min(B) 的总和，其中 B 的范围为 A 的每个（连续）子数组。
     *
     * <p>由于答案可能很大，因此返回答案模 10^9 + 7。
     *
     * <p>示例：
     *
     * <p>输入：[3,1,2,4] 输出：17 解释： 子数组为 [3]，[1]，[2]，[4]，[3,1]，[1,2]，[2,4]，[3,1,2]，[1,2,4]，[3,1,2,4]。
     * 最小值为 3，1，2，4，1，1，2，1，1，1，和为 17。
     *
     * <p>提示：
     *
     * <p>1 <= A <= 30000 1 <= A[i] <= 30000
     *
     * @param A
     * @return
     */
    public int sumSubarrayMins(int[] A) {
        int MOD = 1_000_000_007;
        /*int result = 0;
        int len = A.length;
        // 思路，计算每个数字在 子数组最小值中出现的次数


        for (int i = 0 ; i < len; i++ ) {
            result += A[i];
        }


        return result;*/

        Stack<RepInteger> stack = new Stack();
        int ans = 0, dot = 0;
        for (int j = 0; j < A.length; ++j) {
            // Add all answers for subarrays [i, j], i <= j
            int count = 1;
            while (!stack.isEmpty() && stack.peek().val >= A[j]) {
                RepInteger node = stack.pop();
                count += node.count;
                dot -= node.val * node.count;
            }
            stack.push(new RepInteger(A[j], count));
            dot += A[j] * count;
            ans += dot;
            ans %= MOD;
        }

        return ans;
    }

    class RepInteger {
        int val, count;

        RepInteger(int v, int c) {
            val = v;
            count = c;
        }
    }

    @Test
    public void getSkyline() {
        int[][] buildings = {
            {0, 2, 3}, {2, 5, 4}
        }; // { {2,9,10}, {3,7,15}, {5,12,12}, {15,20,10}, {19,24,8} };
        List<List<Integer>> result = getSkyline(buildings);
        for (List<Integer> list : result) {
            log.debug("result:{}", list);
        }
    }

    class Point {
        // 高度
        int height;
        // 左节点 or 右节点
        boolean leftOrRight;

        Point(int height, boolean leftOrRight) {
            this.height = height;
            this.leftOrRight = leftOrRight;
        }
    }

    /**
     * 天际线问题 城市的天际线是从远处观看该城市中所有建筑物形成的轮廓的外部轮廓。现在，假设您获得了城市风光照片（图A）上显示的所有建筑物的位置和高度，
     * 请编写一个程序以输出由这些建筑物形成的天际线（图B）。
     *
     * <p>Buildings Skyline Contour
     *
     * <p>每个建筑物的几何信息用三元组 [Li，Ri，Hi] 表示，其中 Li 和 Ri 分别是第 i 座建筑物左右边缘的 x 坐标，Hi 是其高度。 可以保证 0 ≤ Li, Ri ≤
     * INT_MAX, 0 < Hi ≤ INT_MAX 和 Ri - Li > 0。您可以假设所有建筑物都是在绝对平坦且高度为 0 的表面上的完美矩形。
     *
     * <p>例如，图A中所有建筑物的尺寸记录为：[ [2 9 10], [3 7 15], [5 12 12], [15 20 10], [19 24 8] ] 。
     *
     * <p>输出是以 [ [x1,y1], [x2, y2], [x3, y3], ... ] 格式的“关键点”（图B中的红点）的列表，它们唯一地定义了天际线。
     * 关键点是水平线段的左端点。请注意，最右侧建筑物的最后一个关键点仅用于标记天际线的终点，并始终为零高度。 此外，任何两个相邻建筑物之间的地面都应被视为天际线轮廓的一部分。
     *
     * <p>例如，图B中的天际线应该表示为：[ [2 10], [3 15], [7 12], [12 0], [15 10], [20 8], [24, 0] ]。
     *
     * <p>说明:
     *
     * <p>任何输入列表中的建筑物数量保证在 [0, 10000] 范围内。 输入列表已经按左 x 坐标 Li 进行升序排列。 输出列表必须按 x 位排序。
     * 输出天际线中不得有连续的相同高度的水平线。例如 [...[2 3], [4 5], [7 5], [11 5], [12 7]...] 是不正确的答案； 三条高度为 5
     * 的线应该在最终输出中合并为一个：[...[2 3], [4 5], [12 7], ...]
     *
     * @param buildings
     * @return
     */
    public List<List<Integer>> getSkyline(int[][] buildings) {

        /**
         * vector<pair<int,int>> h; multiset<int> m; vector<vector<int>> res;
         *
         * <p>//1、将每一个建筑分成“两个部分”，例如:[2,9,10]可以转换成[2，-10][9,10]，我们用负值来表示左边界 for(const auto&
         * b:buildings) { h.push_back({b[0], -b[2]}); h.push_back({b[1], b[2]}); }
         *
         * <p>//2、根据x值对分段进行排序 sort(h.begin(),h.end()); int prev = 0, cur = 0; m.insert(0);
         *
         * <p>3、遍历 for (auto i:h) { if (i.second < 0) m.insert(-i.second); //左端点，高度入堆 else
         * m.erase(m.find(i.second)); //右端点，高度出堆 cur = *m.rbegin(); //当前最大高度高度 if (cur != prev) {
         * //当前最大高度不等于最大高度perv表示这是一个转折点 res.push_back({i.first, cur}); //添加坐标 prev = cur; //更新最大高度 }
         * } return res;
         */
        List<List<Integer>> result = new ArrayList<>();

        int len = buildings.length;
        if (len == 0) {
            return result;
        }
        // 思路, 把 每栋建筑的 左上点坐标 和 右上点 坐标 分边输入，然后 排序(以 x 坐标 做 key)
        //
        /*Map<Integer,Point> map = new TreeMap<>();
        for (int[] building : buildings) {
            Point point1 = map.get(building[0]);
            if (point1 == null || building[2] > point1.height) {
                // 用 负数表示左节点
                map.put(building[0],new Point(building[2], Boolean.TRUE));
            }
            Point point2 = map.get(building[1]);
            if (point2 == null || building[2] > point2.height) {
                // 右边节点
                map.put(building[1],new Point(building[2], Boolean.FALSE));
            }
        }
        int lastHeight = 0;
        // 优先队列，构造最大堆
        Queue<Integer> heightQueue = new PriorityQueue<Integer>((Integer a,Integer b) ->  b - a);
        // 按 x 坐标遍历
        for (Integer x : map.keySet()) {
            Point point = map.get(x);

            if (point.leftOrRight) {
                // 左端点,高度 入队
                heightQueue.offer(point.height);
            } else {
                heightQueue.remove(point.height);
            }
            // 当前最大高度
            Integer currentHeight = heightQueue.peek();
            // 当前最大高度不等于 lastHeight 表示这是一个转折点
            if (currentHeight == null) {
                currentHeight = 0;
            }

            if (currentHeight != lastHeight) {
                List<Integer> pList = new ArrayList<>();
                pList.add(x);
                pList.add(currentHeight);
                result.add(pList);
                lastHeight = currentHeight;
            }
        }*/
        Map<Integer, List<Integer>> map = new TreeMap<>();
        for (int[] building : buildings) {
            // 用 负数表示左节点
            if (!map.containsKey(building[0])) {
                map.put(building[0], new ArrayList<>());
            }
            map.get(building[0]).add(-building[2]);
            // 右边节点
            if (!map.containsKey(building[1])) {
                map.put(building[1], new ArrayList<>());
            }
            map.get(building[1]).add(building[2]);
        }
        int maxHeight = 0, lastHeight = 0;
        // 优先队列，构造最大堆
        Queue<Integer> heightQueue = new PriorityQueue<Integer>((Integer a, Integer b) -> b - a);
        // 按 x 坐标遍历
        for (Integer x : map.keySet()) {
            List<Integer> heights = map.get(x);
            Collections.sort(heights);
            for (int height : heights) {
                if (height < 0) {
                    // 左端点,高度 入队
                    heightQueue.offer(-height);
                } else {
                    heightQueue.remove(height);
                }
                // 当前最大高度
                Integer currentHeight = heightQueue.peek();
                // 当前最大高度不等于 lastHeight 表示这是一个转折点
                if (currentHeight == null) {
                    currentHeight = 0;
                }
                if (currentHeight != lastHeight) {
                    List<Integer> point = new ArrayList<>();
                    point.add(x);
                    point.add(currentHeight);
                    result.add(point);
                    lastHeight = currentHeight;
                }
            }
        }

        /*if (len == 1) {

        }
        int right = 0;
        for (int i = 0; i < len; i++) {
            int[] currBuilding = buildings[i];
            if (currBuilding[1] > right) {
                right = currBuilding[2];
            }

        }
        List<Integer> lastPoint = new ArrayList<>();
        lastPoint.add(right);
        lastPoint.add(0);
        result.add(lastPoint);*/

        return result;
    }

    @Test
    public void splitArray() {
        int[] nums = {7, 2, 5, 10, 8};
        int m = 2;
        int result = splitArray(nums, m);
        log.debug("result:{}", result);
    }

    /**
     * 分割数组的最大值 给定一个非负整数数组和一个整数 m，你需要将这个数组分成 m 个非空的连续子数组。设计一个算法使得这 m 个子数组各自和的最大值最小。
     *
     * <p>注意: 数组长度 n 满足以下条件:
     *
     * <p>1 ≤ n ≤ 1000 1 ≤ m ≤ min(50, n) 示例:
     *
     * <p>输入: nums = [7,2,5,10,8] m = 2
     *
     * <p>输出: 18
     *
     * <p>解释: 一共有四种方法将nums分割为2个子数组。 其中最好的方式是将其分为[7,2,5] 和 [10,8]， 因为此时这两个子数组各自的和的最大值为18，在所有情况中最小。
     *
     * @param nums
     * @param m
     * @return
     */
    public int splitArray(int[] nums, int m) {

        int max = 0, sum = 0;
        for (int num : nums) {
            if (num > max) {
                max = num;
            }
            sum += num;
        }
        // 思路 子数组的最大值是有范围的，即在区间 [max(nums),sum(nums)] 之中。
        // 令 l = max(nums)，h = sum(nums)  mid=(l+h)/2
        // 计算数组和最大值不大于mid对应的子数组个数 cnt(这个是关键！)
        // 如果 cnt>m，说明划分的子数组多了，即我们找到的 mid 偏小，故 l=mid+1l=mid+1；
        // 否则，说明划分的子数组少了，即 mid 偏大(或者正好就是目标值)，故 h=midh=mid。
        if (m == 1) {
            return max;
        }
        //
        int l = max, h = sum;

        while (l < h) {
            int mid = l + (h - l) / 2;
            int tmp = 0;
            int count = 1;
            // 假设 当前数组 的 子数组的最大值 是 mid
            // 遍历 nums 数组，计算超过mid的子数组的个数，个数 < m 说明 mid偏大，
            // 当数组之和 超过 mid count++
            for (int num : nums) {
                tmp += num;
                if (tmp > mid) {
                    tmp = num;
                    count++;
                }
            }
            if (count > m) {
                l = mid + 1;
            } else {
                h = mid;
            }
        }

        return l;
    }

    @Test
    public void kthSmallest() {
        int[][] matrix = {{1, 5, 9}, {10, 11, 13}, {12, 13, 15}};
        int k = 8;
        int result = kthSmallest2(matrix, k);
        log.debug("result:{}", result);
    }
    /**
     * 有序矩阵中第K小的元素 给定一个 n x n 矩阵，其中每行和每列元素均按升序排序，找到矩阵中第k小的元素。 请注意，它是排序后的第k小元素，而不是第k个元素。
     *
     * <p>示例:
     *
     * <p>matrix = [ [ 1, 5, 9], [10, 11, 13], [12, 13, 15] ], k = 8,
     *
     * <p>返回 13。 说明: 你可以假设 k 的值永远是有效的, 1 ≤ k ≤ n2 。
     *
     * @param matrix
     * @param k
     * @return
     */
    public int kthSmallest(int[][] matrix, int k) {

        int result = 0;
        // 最小值low在左上角,最大值high在右下角，那么要找的元素区间在[low,high]。
        // mid=(low+high)/2,如果小于等于mid的数量小于k，则可以pass掉小于等于mid的值，即要找的元素一定大于mid，则low=mid+1。
        // 这样每次折半，时间复杂度是log(high-low)，由于32位的int值，其最大log为32(因为除以2相当于右移1位)
        // 矩阵里查找小于某个值的数量，时间复杂度最小可以是O(N+N), 即O(N)
        // 所以总体时间复杂度是log(high-low)*O(N)，32算常数，即O(N)。
        int rows = matrix.length;
        if (rows == 0) {
            return 0;
        }
        int cols = matrix[0].length;
        int low = matrix[0][0], high = matrix[rows - 1][cols - 1];
        /*while (low < high) {
            int mid = low + (high-low) >> 1;
            int index =
        }*/

        // return result;
        PriorityQueue<Integer> queue = new PriorityQueue<>((a, b) -> b - a);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {

                queue.offer(matrix[i][j]);
                if (queue.size() > k) {
                    queue.poll();
                }
            }
        }

        return queue.peek();

        /*int result = 0;
        // 合并有序数组
        int len = matrix.length;
        if (len == 0) {
            return 0;
        }
        // index 数组
        int[] indexs = new int[len];
        int count = 0;

        int minRow = 0;
        while (count < k) {
            int min = Integer.MAX_VALUE;
            for (int i = 0; i < len; i++) {
                int currentCol = indexs[i];
                if (currentCol >= len) {
                    continue;
                }
                int num = matrix[i][currentCol];
                log.debug("num:{}",num);
                if (num < min) {
                    min = num;
                    minRow = i;
                }
            }
            indexs[minRow]++;
            count++;
            result = min;
            log.debug("min:{}",min);
            log.debug("indexs:{}",indexs);
        }
        log.debug("indexs:{}",indexs);*/

        // return result;
    }

    public int kthSmallest2(int[][] matrix, int k) {

        // 最小值low在左上角,最大值high在右下角，那么要找的元素区间在[low,high]。
        // mid=(low+high)/2,如果小于等于mid的数量小于k，则可以pass掉小于等于mid的值，即要找的元素一定大于mid，则low=mid+1。
        // 这样每次折半，时间复杂度是log(high-low)，由于32位的int值，其最大log为32(因为除以2相当于右移1位)
        // 矩阵里查找小于某个值的数量，时间复杂度最小可以是O(N+N), 即O(N)
        // 所以总体时间复杂度是log(high-low)*O(N)，32算常数，即O(N)。

        int rows = matrix.length;
        if (rows == 0) {
            return 0;
        }
        int cols = matrix[0].length;
        int low = matrix[0][0], high = matrix[rows - 1][cols - 1];
        while (low < high) {
            int mid = low + (high - low) / 2;
            int countLess = countLess(matrix, mid, rows);
            if (countLess < k) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }

    private int countLess(int[][] matrix, int target, int n) {
        int j = 0, i = n - 1;
        int count = 0;
        while (j < n && i > -1) {
            while (i > -1 && matrix[i][j] > target) {
                i--;
            }
            count += (i + 1);
            j++;
        }
        return count;
    }

    @Test
    public void largestRectangleArea() {
        int[] heights = {2, 1, 2, 1, 5, 1};
        int result = largestRectangleArea(heights);
        log.debug("result:{}", result);
    }

    /**
     * 柱状图中最大的矩形 给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
     *
     * <p>求在该柱状图中，能够勾勒出来的矩形的最大面积。
     *
     * <p>以上是柱状图的示例，其中每个柱子的宽度为 1，给定的高度为 [2,1,5,6,2,3]。
     *
     * <p>图中阴影部分为所能勾勒出的最大矩形面积，其面积为 10 个单位。
     *
     * <p>示例:
     *
     * <p>输入: [2,1,5,6,2,3] 输出: 10
     *
     * @param heights
     * @return
     */
    public int largestRectangleArea(int[] heights) {
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);
        int maxarea = 0;
        for (int i = 0; i < heights.length; ++i) {
            while (stack.peek() != -1 && heights[stack.peek()] >= heights[i])
                maxarea = Math.max(maxarea, heights[stack.pop()] * (i - stack.peek() - 1));
            stack.push(i);
        }
        log.debug("stack:{}", stack);
        log.debug("maxarea:{}", maxarea);
        while (stack.peek() != -1)
            maxarea = Math.max(maxarea, heights[stack.pop()] * (heights.length - stack.peek() - 1));
        return maxarea;
        /*int len = heights.length;

        // index 栈
        Deque<Integer> indexDeque = new LinkedList<>();
        int maxArea = 0;
        for (int i = 0; i < len; i++) {
            int height = heights[i];
            // height比栈顶高度 小
            int minHeight = 0, minIndex = 0;
            while (!indexDeque.isEmpty() && height <= heights[indexDeque.peekLast()]) {
                */
        /*minHeight = heights[indexDeque.peekLast()];
        minIndex = indexDeque.pollLast();
        int area = minHeight * (i - minIndex);
        if (area > maxArea) {
            maxArea = area;
        }*/
        /*
                maxArea = Math.max(maxArea, heights[indexDeque.peekLast()] * (i - indexDeque.pollLast() - 1));
            }

            indexDeque.addLast(i);

        }
        int minHeight = 0,minIndex = 0;
        log.debug("b:{}",indexDeque);
        while (!indexDeque.isEmpty() ) {
            minHeight = heights[indexDeque.peekLast()];
            minIndex = indexDeque.pollLast();
            int area = minHeight * (len - minIndex);
            if (area > maxArea) {
                maxArea = area;
            }
        }



        return maxArea;*/
    }

    @Test
    public void reconstructQueue() {
        int[][] people = {{7, 0}, {4, 4}, {7, 1}, {5, 0}, {6, 1}, {5, 2}};
        int[][] result = reconstructQueue(people);
        for (int[] a : result) {
            log.debug("a:{}", a);
        }
    }
    /**
     * Queue Reconstruction by Height 假设有打乱顺序的一群人站成一个队列。 每个人由一个整数对(h,
     * k)表示，其中h是这个人的身高，k是排在这个人前面且身高大于或等于h的人数。 编写一个算法来重建这个队列。
     *
     * <p>注意： 总人数少于1100人。
     *
     * <p>示例
     *
     * <p>输入: [[7,0], [4,4], [7,1], [5,0], [6,1], [5,2]]
     *
     * <p>输出: [[5,0], [7,0], [5,2], [6,1], [4,4], [7,1]]
     *
     * @param people
     * @return
     */
    public int[][] reconstructQueue(int[][] people) {
        int len = people.length;
        Arrays.sort(people, (a, b) -> a[0] == b[0] ? a[1] - b[1] : b[0] - a[0]);
        // 按 身高 高 -> 低 排序 k 表示
        List<int[]> list = new LinkedList<>();
        for (int[] person : people) {

            list.add(person[1], person);
        }
        int[][] result = new int[len][2];
        list.toArray(result);

        return result;
    }

    @Test
    public void minDeletionSize() {
        String[] a = {"cba", "daf", "ghi"};
        logResult(minDeletionSize(a));
    }

    /**
     * 删列造序 给定由 N 个小写字母字符串组成的数组 A，其中每个字符串长度相等。
     *
     * <p>删除 操作的定义是：选出一组要删掉的列，删去 A 中对应列中的所有字符，形式上，第 n 列为 [A[0][n], A[1][n], ..., A[A.length-1][n]]）。
     *
     * <p>比如，有 A = ["abcdef", "uvwxyz"]，
     *
     * <p>要删掉的列为 {0, 2, 3}，删除后 A 为["bef", "vyz"]， A 的列分别为["b","v"], ["e","y"], ["f","z"]。
     *
     * <p>你需要选出一组要删掉的列 D，对 A 执行删除操作，使 A 中剩余的每一列都是 非降序 排列的，然后请你返回 D.length 的最小可能值。
     *
     * <p>示例 1：
     *
     * <p>输入：["cba", "daf", "ghi"] 输出：1 解释： 当选择 D = {1}，删除后 A 的列为：["c","d","g"] 和
     * ["a","f","i"]，均为非降序排列。 若选择 D = {}，那么 A 的列 ["b","a","h"] 就不是非降序排列了。 示例 2：
     *
     * <p>输入：["a", "b"] 输出：0 解释：D = {} 示例 3：
     *
     * <p>输入：["zyx", "wvu", "tsr"] 输出：3 解释：D = {0, 1, 2}
     *
     * <p>提示：
     *
     * <p>1 <= A.length <= 100 1 <= A[i].length <= 1000
     *
     * @param A
     * @return
     */
    public int minDeletionSize(String[] A) {
        int rows = A.length;

        int result = 0;
        int len = A[0].length();
        for (int i = 0; i < len; i++) {
            for (int j = 1; j < rows; j++) {
                if (A[j - 1].charAt(i) > A[j].charAt(i)) {
                    result++;
                    break;
                }
            }
        }
        return result;
    }

    @Test
    public void minDeletionSize2() {
        String[] a = {"xga", "xfb", "yfa"};
        logResult(minDeletionSize2(a));
    }

    /**
     * 给定由 N 个小写字母字符串组成的数组 A，其中每个字符串长度相等。
     *
     * <p>选取一个删除索引序列，对于 A 中的每个字符串，删除对应每个索引处的字符。
     *
     * <p>比如，有 A = ["abcdef", "uvwxyz"]，删除索引序列 {0, 2, 3}，删除后 A 为["bef", "vyz"]。
     *
     * <p>假设，我们选择了一组删除索引 D，那么在执行删除操作之后，最终得到的数组的元素是按 字典序（A[0] <= A[1] <= A[2] ... <= A[A.length -
     * 1]）排列的，然后请你返回 D.length 的最小可能值。
     *
     * <p>
     *
     * <p>示例 1：
     *
     * <p>输入：["ca","bb","ac"] 输出：1 解释： 删除第一列后，A = ["a", "b", "c"]。 现在 A 中元素是按字典排列的 (即，A[0] <= A[1]
     * <= A[2])。 我们至少需要进行 1 次删除，因为最初 A 不是按字典序排列的，所以答案是 1。
     *
     * <p>示例 2：
     *
     * <p>输入：["xc","yb","za"] 输出：0 解释： A 的列已经是按字典序排列了，所以我们不需要删除任何东西。 注意 A 的行不需要按字典序排列。 也就是说，A[0][0]
     * <= A[0][1] <= ... 不一定成立。 示例 3：
     *
     * <p>输入：["zyx","wvu","tsr"] 输出：3 解释： 我们必须删掉每一列。
     *
     * <p>提示：
     *
     * <p>1 <= A.length <= 100 1 <= A[i].length <= 100
     *
     * @param A
     * @return
     */
    public int minDeletionSize2(String[] A) {
        int rows = A.length;

        int result = 0;
        int len = A[0].length();
        boolean[] lastCol = new boolean[rows - 1];
        search:
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < rows - 1; j++) {
                if (!lastCol[j] && A[j].charAt(i) > A[j + 1].charAt(i)) {
                    result++;
                    continue search;
                }
            }
            for (int j = 0; j < rows - 1; j++) {
                if (A[j].charAt(i) < A[j + 1].charAt(i)) {
                    lastCol[j] = true;
                }
            }
        }
        return result;
    }

    @Test
    public void minDeletionSize3() {
        String[] a = {"ghi", "def", "abc"};
        logResult(minDeletionSize3(a));
    }

    /**
     * 删列造序 III 给定由 N 个小写字母字符串组成的数组 A，其中每个字符串长度相等。
     *
     * <p>选取一个删除索引序列，对于 A 中的每个字符串，删除对应每个索引处的字符。
     *
     * <p>比如，有 A = ["babca","bbazb"]，删除索引序列 {0, 1, 4}，删除后 A 为["bc","az"]。
     *
     * <p>假设，我们选择了一组删除索引 D，那么在执行删除操作之后，最终得到的数组的行中的每个元素都是按字典序排列的。
     *
     * <p>清楚起见，A[0] 是按字典序排列的（即，A[0][0] <= A[0][1] <= ... <= A[0][A[0].length - 1]），A[1]
     * 是按字典序排列的（即，A[1][0] <= A[1][1] <= ... <= A[1][A[1].length - 1]），依此类推。
     *
     * <p>请你返回 D.length 的最小可能值。
     *
     * <p>示例 1：
     *
     * <p>输入：["babca","bbazb"] 输出：3 解释： 删除 0、1 和 4 这三列后，最终得到的数组是 A = ["bc", "az"]。
     * 这两行是分别按字典序排列的（即，A[0][0] <= A[0][1] 且 A[1][0] <= A[1][1]）。 注意，A[0] > A[1] —— 数组 A 不一定是按字典序排列的。
     * 示例 2：
     *
     * <p>输入：["edcba"] 输出：4 解释：如果删除的列少于 4 列，则剩下的行都不会按字典序排列。 示例 3：
     *
     * <p>输入：["ghi","def","abc"] 输出：0 解释：所有行都已按字典序排列。
     *
     * <p>提示：
     *
     * <p>1 <= A.length <= 100 1 <= A[i].length <= 100
     *
     * @param A
     * @return
     */
    public int minDeletionSize3(String[] A) {
        int rows = A.length;
        int result = 0;
        int len = A[0].length();
        // 思路, 查找每个字符串共用的上升字串
        int[] lenArray = new int[len];
        lenArray[0] = 1;
        int max = 1;
        for (int i = 1; i < len; i++) {
            int maxval = 0;
            for (int j = 0; j < i; j++) {

                boolean flag = true;
                for (int k = 0; k < rows; k++) {
                    // 存在递减 flag = false;
                    if (A[k].charAt(i) < A[k].charAt(j)) {
                        flag = false;
                        break;
                    }
                }
                if (flag) {
                    maxval = Math.max(maxval, lenArray[j]);
                }
            }
            lenArray[i] = maxval + 1;
            max = Math.max(max, lenArray[i]);
        }
        log.debug("lenArray:{}", lenArray);
        result = len - max;
        return result;
    }

    //
    public int lengthOfLIS(int[] nums) {

        int len = nums.length;
        if (len <= 1) {
            return len;
        }

        int[] lenArray = new int[len];
        int max = 1;
        lenArray[0] = 1;
        for (int i = 1; i < len; i++) {
            int maxval = 0;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    maxval = Math.max(maxval, lenArray[j]);
                }
            }

            lenArray[i] = maxval + 1;
            max = Math.max(max, lenArray[i]);
        }

        return max;
    }

    @Test
    public void searchInsert() {
        int[] nums = {1, 3, 5, 6};
        int target = 6;
        logResult(searchInsert(nums, target));
    }

    /**
     * 35. 搜索插入位置 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
     *
     * <p>你可以假设数组中无重复元素。
     *
     * <p>示例 1:
     *
     * <p>输入: [1,3,5,6], 5 输出: 2 示例 2:
     *
     * <p>输入: [1,3,5,6], 2 输出: 1 示例 3:
     *
     * <p>输入: [1,3,5,6], 7 输出: 4 示例 4:
     *
     * <p>输入: [1,3,5,6], 0 输出: 0
     *
     * @param nums
     * @param target
     * @return
     */
    public int searchInsert(int[] nums, int target) {
        int len = nums.length;
        int left = 0, right = len - 1;
        if (target > nums[right]) {
            return len;
        }
        if (target <= nums[0]) {
            return 0;
        }
        while (left < right) {
            int mid = (left + right) >> 1;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }

    @Test
    public void nextPermutation() {
        int[] nums = {5, 1, 1};
        nextPermutation(nums);
        log.debug("nums:{}", nums);
    }
    /**
     * 31. 下一个排列 实现获取下一个排列的函数，算法需要将给定数字序列重新排列成字典序中下一个更大的排列。
     *
     * <p>如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。
     *
     * <p>必须原地修改，只允许使用额外常数空间。
     *
     * <p>以下是一些例子，输入位于左侧列，其相应输出位于右侧列。 1,2,3 → 1,3,2 3,2,1 → 1,2,3 1,1,5 → 1,5,1
     *
     * @param nums
     */
    public void nextPermutation(int[] nums) {
        int len = nums.length;
        if (len <= 1) {
            return;
        }
        if (len == 2) {
            swap(nums, 0, 1);
            return;
        }
        // 如果最后两位是升序, 直接交换
        if (nums[len - 2] < nums[len - 1]) {
            swap(nums, len - 2, len - 1);
            return;
        }
        // 如果最后两位是降序
        // 从 最后一位,找到第一个降序的排列
        int index = len - 2;
        for (int i = index; i >= 0; i--) {
            if (nums[i] >= nums[i + 1]) {
                index = i;
            } else {
                break;
            }
        }
        // index 是从后往前 第一个降序的排列 的 开始 序号
        // 从 index 开始 反转序列
        int left = index, right = len - 1;
        while (left < right) {
            swap(nums, left++, right--);
        }
        if (index == 0) {
            return;
        }
        // 如果index 不等于0 交换 num[index - 1] 和第一个 大于 num[index - 1] 的 数字
        int num = nums[index - 1];
        int firstIndex = index;
        for (int i = index; i < len; i++) {
            if (nums[i] > num) {
                firstIndex = i;
                break;
            }
        }
        swap(nums, index - 1, firstIndex);
    }

    private void swap(int[] nums, int i, int j) {
        int val = nums[i];
        nums[i] = nums[j];
        nums[j] = val;
    }

    @Test
    public void reconstructMatrix() {
        int upper = 2, lower = 3;
        int[] colsum = {1, 1, 1};
        logResult(reconstructMatrix(upper, lower, colsum));
    }
    /**
     * 给你一个 2 行 n 列的二进制数组：
     *
     * <p>矩阵是一个二进制矩阵，这意味着矩阵中的每个元素不是 0 就是 1。 第 0 行的元素之和为 upper。 第 1 行的元素之和为 lower。 第 i 列（从 0
     * 开始编号）的元素之和为 colsum[i]，colsum 是一个长度为 n 的整数数组。 你需要利用 upper，lower 和 colsum
     * 来重构这个矩阵，并以二维整数数组的形式返回它。
     *
     * <p>如果有多个不同的答案，那么任意一个都可以通过本题。
     *
     * <p>如果不存在符合要求的答案，就请返回一个空的二维数组。
     *
     * <p>示例 1：
     *
     * <p>输入：upper = 2, lower = 1, colsum = [1,1,1] 输出：[[1,1,0],[0,0,1]] 解释：[[1,0,1],[0,1,0]] 和
     * [[0,1,1],[1,0,0]] 也是正确答案。 示例 2：
     *
     * <p>输入：upper = 2, lower = 3, colsum = [2,2,1,1] 输出：[] 示例 3： 9 2 [0,1,2,0,0,0,0,0,2,1,2,1,2]
     * 输入：upper = 5, lower = 5, colsum = [2,1,2,0,1,0,1,2,0,1]
     * 输出：[[1,1,1,0,1,0,0,1,0,0],[1,0,1,0,0,0,1,1,0,1]]
     *
     * @param upper
     * @param lower
     * @param colsum
     * @return
     */
    public List<List<Integer>> reconstructMatrix(int upper, int lower, int[] colsum) {
        List<List<Integer>> result = new ArrayList<>();
        int sum = 0;
        for (int num : colsum) {
            if (num > 2) {
                return result;
            }
            sum += num;
        }
        if (sum != (upper + lower)) {
            return result;
        }
        int len = colsum.length;
        int[] array1 = new int[len];
        int[] array2 = new int[len];
        int num1 = 0, num2 = 0;
        for (int i = 0; i < len; i++) {
            if (colsum[i] == 2) {
                array1[i] = 1;
                array2[i] = 1;
                num1++;
                num2++;
            }
        }
        for (int i = 0; i < len; i++) {
            if (colsum[i] == 1) {
                if (num1 < upper) {
                    array1[i] = 1;
                    num1++;
                } else {
                    array2[i] = 1;
                    num2++;
                }
            }
        }
        if (num1 != upper || num2 != lower) {
            return result;
        }

        List<Integer> list1 = new ArrayList<>();
        for (int num : array1) {
            list1.add(num);
        }
        List<Integer> list2 = new ArrayList<>();
        for (int num : array2) {
            list2.add(num);
        }
        result.add(list1);
        result.add(list2);
        return result;
    }

    @Test
    public void insert() {
        int[][] intervals = {{1, 2}, {3, 5}, {6, 7}, {8, 10}, {12, 16}};
        int[] newInterval = {16, 18};
        int[][] result = insert(intervals, newInterval);
        for (int[] a : result) {
            log.debug("a:{}", a);
        }
    }

    /**
     * 57. 插入区间 给出一个无重叠的 ，按照区间起始端点排序的区间列表。
     *
     * <p>在列表中插入一个新的区间，你需要确保列表中的区间仍然有序且不重叠（如果有必要的话，可以合并区间）。
     *
     * <p>示例 1:
     *
     * <p>输入: intervals = [[1,3],[6,9]], newInterval = [2,5] 输出: [[1,5],[6,9]] 示例 2:
     *
     * <p>输入: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8] 输出:
     * [[1,2],[3,10],[12,16]] 解释: 这是因为新的区间 [4,8] 与 [3,5],[6,7],[8,10] 重叠。
     *
     * @param intervals
     * @param newInterval
     * @return
     */
    public int[][] insert(int[][] intervals, int[] newInterval) {
        int len = intervals.length;
        int low = newInterval[0], high = newInterval[1];
        List<int[]> list = new ArrayList<>();
        int[] a = new int[2];
        a[0] = low;
        a[1] = high;
        for (int i = 0; i < len; i++) {
            int[] nums = intervals[i];
            if (nums[1] < low) {
                list.add(nums);
                continue;
            }
            if (nums[0] > high) {
                if (a != null) {
                    list.add(a);
                }
                list.add(nums);
                a = null;
                continue;
            }
            if (nums[0] <= low && low <= nums[1]) {
                a[0] = nums[0];
            }
            if (nums[0] <= high && high <= nums[1]) {
                a[1] = nums[1];
            }
        }
        if (a != null) {
            list.add(a);
        }
        int[][] result = new int[list.size()][2];
        for (int i = 0; i < list.size(); i++) {
            result[i] = list.get(i);
        }
        return result;
    }

    @Test
    public void mergeTest() {
        int[] A = {1, 2, 3, 0, 0, 0};
        int[] B = {2, 5, 6};
        int m = 3, n = 3;
        merge(A, m, B, n);
        logResult(A);
    }
    /**
     * 面试题 10.01. 合并排序的数组 给定两个排序后的数组 A 和 B，其中 A 的末端有足够的缓冲空间容纳 B。 编写一个方法，将 B 合并入 A 并排序。
     *
     * <p>初始化 A 和 B 的元素数量分别为 m 和 n。
     *
     * <p>示例:
     *
     * <p>输入: A = [1,2,3,0,0,0], m = 3 B = [2,5,6], n = 3
     *
     * <p>输出: [1,2,2,3,5,6]
     *
     * @param A
     * @param m
     * @param B
     * @param n
     */
    public void merge(int[] A, int m, int[] B, int n) {
        // 归并排序 从后往前
        int i = m - 1, j = n - 1;
        int index = m + n - 1;
        while (i >= 0 && j >= 0) {
            if (A[i] >= B[j]) {
                A[index--] = A[i--];
            } else {
                A[index--] = B[j--];
            }
        }
        while (j >= 0) {
            A[index--] = B[j--];
        }
        while (i >= 0) {
            A[index--] = A[i--];
        }
        /*int[] array = new int[m];
        for (int i = 0; i < m; i++) {
            array[i] = A[i];
        }
        int i = 0,j = 0;
        int index = 0;
        while (i < m || j < n) {
            if (i == m) {
                A[index++] = B[j++];
                continue;
            }
            if (j == n) {
                A[index++] = array[i++];
                continue;
            }
            if (array[i] <= B[j]) {
                A[index++] = array[i++];
            } else {
                A[index++] = B[j++];
            }

        }*/

    }

    @Test
    public void orangesRotting() {
        int[][] grid = {{2, 1, 1}, {0, 1, 1}, {1, 0, 1}};
        logResult(orangesRotting(grid));
    }
    /**
     * 994. 腐烂的橘子 在给定的网格中，每个单元格可以有以下三个值之一：
     *
     * <p>值 0 代表空单元格； 值 1 代表新鲜橘子； 值 2 代表腐烂的橘子。 每分钟，任何与腐烂的橘子（在 4 个正方向上）相邻的新鲜橘子都会腐烂。
     *
     * <p>返回直到单元格中没有新鲜橘子为止所必须经过的最小分钟数。如果不可能，返回 -1。
     *
     * <p>示例 1：
     *
     * <p>输入：[[2,1,1],[1,1,0],[0,1,1]] 输出：4 示例 2：
     *
     * <p>输入：[[2,1,1],[0,1,1],[1,0,1]] 输出：-1 解释：左下角的橘子（第 2 行， 第 0 列）永远不会腐烂，因为腐烂只会发生在 4 个正向上。 示例 3：
     *
     * <p>输入：[[0,2]] 输出：0 解释：因为 0 分钟时已经没有新鲜橘子了，所以答案就是 0 。
     *
     * <p>提示：
     *
     * <p>1 <= grid.length <= 10 1 <= grid[0].length <= 10 grid[i][j] 仅为 0、1 或 2
     *
     * @param grid
     * @return
     */
    public int orangesRotting(int[][] grid) {
        // 思路 广度优先遍历
        int second = -1;
        int rows = grid.length;
        int cols = grid[0].length;
        // 使用队列 进行广度优先遍历
        Queue<int[]> queue = new LinkedList<>();
        int badCount = 0, goodCount = 0;
        // 遍历整个数组
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 1) {
                    goodCount++;
                }
                if (grid[i][j] == 2) {
                    int[] position = new int[2];
                    position[0] = i;
                    position[1] = j;
                    queue.offer(position);
                }
            }
        }

        if (goodCount == 0) {
            return 0;
        }
        // 防止重复遍历
        boolean[][] visited = new boolean[rows][cols];

        int[] dirRow = {-1, 1, 0, 0};
        int[] dirCol = {0, 0, -1, 1};
        // 当队列不为空
        while (!queue.isEmpty()) {

            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] position = queue.poll();
                int row = position[0], col = position[1];
                visited[row][col] = true;
                // 上下左右四个方向
                for (int k = 0; k < 4; k++) {
                    int kRow = row + dirRow[k];
                    int kCol = col + dirCol[k];
                    if (inArea(kRow, kCol, rows, cols)
                            && grid[kRow][kCol] == 1
                            && !visited[kRow][kCol]) {
                        int[] array = new int[2];
                        array[0] = kRow;
                        array[1] = kCol;
                        queue.offer(array);
                        grid[kRow][kCol] = 2;
                        goodCount--;
                    }
                }
            }
            second++;
        }
        if (goodCount > 0) {
            return -1;
        }

        return second;
    }

    private boolean inArea(int row, int col, int rows, int cols) {
        return row >= 0 && row < rows && col >= 0 && col < cols;
    }

    @Test
    public void thirdMax() {
        int[] nums = {2, 2, 3, Integer.MIN_VALUE};
        logResult(thirdMax(nums));
    }

    /**
     * 414. 第三大的数 给定一个非空数组，返回此数组中第三大的数。如果不存在，则返回数组中最大的数。要求算法时间复杂度必须是O(n)。
     *
     * <p>示例 1:
     *
     * <p>输入: [3, 2, 1]
     *
     * <p>输出: 1
     *
     * <p>解释: 第三大的数是 1. 示例 2:
     *
     * <p>输入: [1, 2]
     *
     * <p>输出: 2
     *
     * <p>解释: 第三大的数不存在, 所以返回最大的数 2 . 示例 3:
     *
     * <p>输入: [2, 2, 3, 1]
     *
     * <p>输出: 1
     *
     * <p>解释: 注意，要求返回第三大的数，是指第三大且唯一出现的数。 存在两个值为2的数，它们都排第二。
     *
     * @param nums
     * @return
     */
    public int thirdMax(int[] nums) {
        // 设置3个遍历 记录前三大数字
        Integer big = null, mid = null, small = null;

        for (int num : nums) {
            if (big == null || num > big) {
                small = mid;
                mid = big;
                big = num;
            } else if ((mid == null || num > mid) && !Objects.equals(num, big)) {
                small = mid;
                mid = num;
            } else if ((small == null || num > small)
                    && !Objects.equals(num, big)
                    && !Objects.equals(num, mid)) {
                small = num;
            }
        }

        return small == null ? big : small;
    }

    @Test
    public void findDisappearedNumbers() {
        int[] nums = {4, 3, 2, 7, 8, 2, 3, 1};
        logResult(findDisappearedNumbers(nums));
    }

    /**
     * 448. 找到所有数组中消失的数字 给定一个范围在 1 ≤ a[i] ≤ n ( n = 数组大小 ) 的 整型数组，数组中的元素一些出现了两次，另一些只出现一次。
     *
     * <p>找到所有在 [1, n] 范围之间没有出现在数组中的数字。
     *
     * <p>您能在不使用额外空间且时间复杂度为O(n)的情况下完成这个任务吗? 你可以假定返回的数组不算在额外空间内。
     *
     * <p>示例:
     *
     * <p>输入: [4,3,2,7,8,2,3,1]
     *
     * <p>输出: [5,6]
     *
     * @param nums
     * @return
     */
    public List<Integer> findDisappearedNumbers(int[] nums) {
        int len = nums.length;
        List<Integer> result = new ArrayList<>();
        int[] array = new int[len];
        for (int num : nums) {
            array[num - 1]++;
        }
        for (int i = 0; i < len; i++) {
            if (array[i] == 0) {
                result.add(i + 1);
            }
        }

        return result;
    }

    @Test
    public void findContinuousSequence() {
        int target = 15;
        logResult(findContinuousSequence(target));
    }
    /**
     * 面试题57 - II. 和为s的连续正数序列 输入一个正整数 target ，输出所有和为 target 的连续正整数序列（至少含有两个数）。
     *
     * <p>序列内的数字由小到大排列，不同序列按照首个数字从小到大排列。
     *
     * <p>示例 1：
     *
     * <p>输入：target = 9 输出：[[2,3,4],[4,5]] 示例 2：
     *
     * <p>输入：target = 15 输出：[[1,2,3,4,5],[4,5,6],[7,8]]
     *
     * <p>限制：
     *
     * <p>1 <= target <= 10^5
     *
     * @param target
     * @return
     */
    public int[][] findContinuousSequence(int target) {
        if (target < 3) {
            return new int[0][];
        }
        List<List<Integer>> result = new ArrayList<>();
        // 滑动窗口
        Queue<Integer> queue = new LinkedList<>();
        int num = 0;
        for (int i = 1; i < target; i++) {
            queue.offer(i);
            num += i;
            while (num > target) {
                num -= queue.poll();
            }
            if (num == target) {
                result.add(new ArrayList<>(queue));
            }
        }
        logResult(result);
        if (result.size() == 0) {
            return null;
        }
        int[][] nums = new int[result.size()][];
        for (int i = 0; i < result.size(); i++) {
            List<Integer> list = result.get(i);
            int[] arrays = new int[list.size()];
            for (int j = 0; j < list.size(); j++) {
                arrays[j] = list.get(j);
            }
            nums[i] = arrays;
        }

        return nums;
    }

    @Test
    public void islandPerimeter() {
        int[][] grid = {{0, 1, 0, 0}, {1, 1, 1, 0}, {0, 1, 0, 0}, {1, 1, 0, 0}};
        logResult(islandPerimeter(grid));
    }
    /**
     * 463. 岛屿的周长 给定一个包含 0 和 1 的二维网格地图，其中 1 表示陆地 0 表示水域。
     *
     * <p>网格中的格子水平和垂直方向相连（对角线方向不相连）。整个网格被水完全包围，但其中恰好有一个岛屿（或者说，一个或多个表示陆地的格子相连组成的岛屿）。
     *
     * <p>岛屿中没有“湖”（“湖” 指水域在岛屿内部且不和岛屿周围的水相连）。格子是边长为 1 的正方形。网格为长方形，且宽度和高度均不超过 100 。计算这个岛屿的周长。
     *
     * <p>示例 :
     *
     * <p>输入: [[0,1,0,0], [1,1,1,0], [0,1,0,0], [1,1,0,0]]
     *
     * <p>输出: 16
     *
     * <p>解释: 它的周长是下面图片中的 16 个黄色的边：
     *
     * @param grid
     * @return
     */
    public int islandPerimeter(int[][] grid) {
        int result = 0;
        // inArea
        int rows = grid.length;
        int cols = grid[0].length;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 1) {
                    result += getSideLength(i, j, grid);
                }
            }
        }

        return result;
    }

    private int getSideLength(int row, int col, int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        int[] dirRow = {-1, 1, 0, 0};
        int[] dirCol = {0, 0, -1, 1};
        int result = 4;
        for (int k = 0; k < 4; k++) {
            int kRow = row + dirRow[k];
            int kCol = col + dirCol[k];
            if (inArea(kRow, kCol, rows, cols) && grid[kRow][kCol] == 1) {
                result--;
            }
        }
        return result;
    }

    @Test
    public void canPlaceFlowers() {
        int[] flowerbed = {1, 0, 0, 0, 0, 1};
        int n = 2;
        logResult(canPlaceFlowers(flowerbed, n));
    }

    /**
     * 605. 种花问题 假设你有一个很长的花坛，一部分地块种植了花，另一部分却没有。可是，花卉不能种植在相邻的地块上，它们会争夺水源，两者都会死去。
     *
     * <p>给定一个花坛（表示为一个数组包含0和1，其中0表示没种植花，1表示种植了花），和一个数 n 。能否在不打破种植规则的情况下种入 n 朵花？能则返回True，不能则返回False。
     *
     * <p>示例 1:
     *
     * <p>输入: flowerbed = [1,0,0,0,1], n = 1 输出: True 示例 2:
     *
     * <p>输入: flowerbed = [1,0,0,0,1], n = 2 输出: False 注意:
     *
     * <p>数组内已种好的花不会违反种植规则。 输入的数组长度范围为 [1, 20000]。 n 是非负整数，且不会超过输入数组的大小。
     *
     * @param flowerbed
     * @param n
     * @return
     */
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        int len = flowerbed.length;

        for (int i = 0; i < len; i++) {
            if (flowerbed[i] == 1) {
                i++;
                continue;
            }
            if ((i == 0 || flowerbed[i - 1] == 0) && (i == len - 1 || flowerbed[i + 1] == 0)) {
                flowerbed[i] = 1;
                n--;
            }
            if (n <= 0) {
                break;
            }
        }

        return n <= 0;
    }

    @Test
    public void isOneBitCharacter() {
        int[] bits = {1, 1, 1, 0};
        logResult(isOneBitCharacter(bits));
    }
    /**
     * 717. 1比特与2比特字符 有两种特殊字符。第一种字符可以用一比特0来表示。第二种字符可以用两比特(10 或 11)来表示。
     *
     * <p>现给一个由若干比特组成的字符串。问最后一个字符是否必定为一个一比特字符。给定的字符串总是由0结束。
     *
     * <p>示例 1:
     *
     * <p>输入: bits = [1, 0, 0] 输出: True 解释: 唯一的编码方式是一个两比特字符和一个一比特字符。所以最后一个字符是一比特字符。 示例 2:
     *
     * <p>输入: bits = [1, 1, 1, 0] 输出: False 解释: 唯一的编码方式是两比特字符和两比特字符。所以最后一个字符不是一比特字符。 注意:
     *
     * <p>1 <= len(bits) <= 1000. bits[i] 总是0 或 1.
     *
     * @param bits
     * @return
     */
    public boolean isOneBitCharacter(int[] bits) {
        int len = bits.length;
        if (len == 0) {
            return false;
        }
        if (bits[len - 1] != 0) {
            return false;
        }

        return isOneBitCharacter(bits, 0);
    }

    private boolean isOneBitCharacter(int[] bits, int start) {
        int len = bits.length;
        if (start == len - 1) {
            return bits[start] == 0;
        }
        if (start >= len) {
            return false;
        }
        int num = bits[start];
        boolean result = false;
        if (num == 0) {
            result = isOneBitCharacter(bits, start + 1);
        }
        if (result) {
            return true;
        }
        if (num == 1) {
            result = isOneBitCharacter(bits, start + 2);
        }

        return result;
    }

    @Test
    public void duplicateZeros() {
        int[] arr = {8, 4, 5, 0, 0, 0, 0, 7};
        duplicateZeros(arr);
        log.debug("arr:{}", arr);
    }
    /**
     * 1089. 复写零 给你一个长度固定的整数数组 arr，请你将该数组中出现的每个零都复写一遍，并将其余的元素向右平移。
     *
     * <p>注意：请不要在超过该数组长度的位置写入元素。
     *
     * <p>要求：请对输入的数组 就地 进行上述修改，不要从函数返回任何东西。
     *
     * <p>示例 1：
     *
     * <p>输入：[1,0,2,3,0,4,5,0] 输出：null 解释：调用函数后，输入的数组将被修改为：[1,0,0,2,3,0,0,4] 示例 2：
     *
     * <p>输入：[1,2,3] 输出：null 解释：调用函数后，输入的数组将被修改为：[1,2,3]
     *
     * <p>提示：
     *
     * <p>1 <= arr.length <= 10000 0 <= arr[i] <= 9
     *
     * @param arr
     */
    public void duplicateZeros(int[] arr) {
        int len = arr.length;
        int count = 0;
        int i = 0;
        while (i + count < len) {
            if (i + 1 + count >= len) {
                break;
            }
            if (arr[i++] == 0) {
                // 计数 0 的个数, 需要移动多少位
                count++;
            }
        }
        logResult(count);
        int j = len - 1;

        while (count > 0 && j > 0) {

            arr[j] = arr[j - count];
            if (arr[j] == 0) {
                arr[--j] = 0;
                count--;
            }
            j--;
            log.debug("arr:{}", arr);
        }
        /*for (int i = 1; i < len; i++) {
            if (arr[i - 1] == 0) {
                if (len - i - 1 > 0) {
                    System.arraycopy(arr,i , arr,i+1,len - i - 1);
                }
                arr[i ] = 0;
                i++;

            }

        }*/

    }

    @Test
    public void minTimeToVisitAllPoints() {
        int[][] points = {{1, 1}, {3, 4}, {-1, 0}};
        logResult(minTimeToVisitAllPoints(points));
    }

    /**
     * 1266. 访问所有点的最小时间 平面上有 n 个点，点的位置用整数坐标表示 points[i] = [xi, yi]。请你计算访问所有这些点需要的最小时间（以秒为单位）。
     *
     * <p>你可以按照下面的规则在平面上移动：
     *
     * <p>每一秒沿水平或者竖直方向移动一个单位长度，或者跨过对角线（可以看作在一秒内向水平和竖直方向各移动一个单位长度）。 必须按照数组中出现的顺序来访问这些点。
     *
     * <p>示例 1：
     *
     * <p>输入：points = [[1,1],[3,4],[-1,0]] 输出：7 解释：一条最佳的访问路径是： [1,1] -> [2,2] -> [3,3] -> [3,4] ->
     * [2,3] -> [1,2] -> [0,1] -> [-1,0] 从 [1,1] 到 [3,4] 需要 3 秒 从 [3,4] 到 [-1,0] 需要 4 秒 一共需要 7 秒 示例
     * 2：
     *
     * <p>输入：points = [[3,2],[-2,2]] 输出：5
     *
     * <p>提示：
     *
     * <p>points.length == n 1 <= n <= 100 points[i].length == 2 -1000 <= points[i][0], points[i][1]
     * <= 1000
     *
     * @param points
     * @return
     */
    public int minTimeToVisitAllPoints(int[][] points) {
        int result = 0;
        int len = points.length;

        for (int i = 1; i < len; i++) {
            int[] point1 = points[i - 1];
            int[] point2 = points[i];

            result += getTime(point1, point2);
        }

        return result;
    }

    private int getTime(int[] point1, int[] point2) {
        int x1 = point1[0], y1 = point1[1];
        int x2 = point2[0], y2 = point2[1];

        int x = Math.abs(x1 - x2);

        int y = Math.abs(y1 - y2);

        return Math.max(x, y);
    }

    @Test
    public void shiftGrid() {
        int[][] grid = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        int k = 9;
        logResult(shiftGrid(grid, k));
    }
    /**
     * 1260. 二维网格迁移 给你一个 m 行 n 列的二维网格 grid 和一个整数 k。你需要将 grid 迁移 k 次。
     *
     * <p>每次「迁移」操作将会引发下述活动：
     *
     * <p>位于 grid[i][j] 的元素将会移动到 grid[i][j + 1]。 位于 grid[i][n - 1] 的元素将会移动到 grid[i + 1][0]。 位于
     * grid[m - 1][n - 1] 的元素将会移动到 grid[0][0]。 请你返回 k 次迁移操作后最终得到的 二维网格。
     *
     * <p>示例 1：
     *
     * <p>输入：grid = [[1,2,3],[4,5,6],[7,8,9]], k = 1 输出：[[9,1,2],[3,4,5],[6,7,8]] 示例 2：
     *
     * <p>输入：grid = [[3,8,1,9],[19,7,2,5],[4,6,11,10],[12,0,21,13]], k = 4
     * 输出：[[12,0,21,13],[3,8,1,9],[19,7,2,5],[4,6,11,10]] 示例 3：
     *
     * <p>输入：grid = [[1,2,3],[4,5,6],[7,8,9]], k = 9 输出：[[1,2,3],[4,5,6],[7,8,9]]
     *
     * <p>提示：
     *
     * <p>1 <= grid.length <= 50 1 <= grid[i].length <= 50 -1000 <= grid[i][j] <= 1000 0 <= k <= 100
     *
     * @param grid
     * @param k
     * @return
     */
    public List<List<Integer>> shiftGrid(int[][] grid, int k) {

        List<List<Integer>> result = new ArrayList<>();
        int rows = grid.length;
        if (rows == 0) {
            return result;
        }
        int cols = grid[0].length;
        if (cols == 0) {
            return result;
        }

        int len = rows * cols;
        k = k % len;
        if (k > 0) {
            k = len - k;
        }

        int row = k / cols, col = k % cols;

        int index = 0;
        List<Integer> list = new ArrayList<>();
        while (index++ < len) {
            int num = grid[row][col];
            list.add(num);
            if (++col == cols) {
                col = 0;
                row++;
            }
            if (row == rows) {
                row = 0;
            }

            if (index % cols == 0) {
                result.add(list);
                list = new ArrayList<>();
            }
        }

        return result;
    }

    @Test
    public void oddCells() {
        int n = 2, m = 3;
        int[][] indices = {{0, 1}, {1, 1}};
        logResult(oddCells(n, m, indices));
    }

    /**
     * 1252. 奇数值单元格的数目 给你一个 n 行 m 列的矩阵，最开始的时候，每个单元格中的值都是 0。
     *
     * <p>另有一个索引数组 indices，indices[i] = [ri, ci] 中的 ri 和 ci 分别表示指定的行和列（从 0 开始编号）。
     *
     * <p>你需要将每对 [ri, ci] 指定的行和列上的所有单元格的值加 1。
     *
     * <p>请你在执行完所有 indices 指定的增量操作后，返回矩阵中 「奇数值单元格」 的数目。
     *
     * <p>示例 1：
     *
     * <p>输入：n = 2, m = 3, indices = [[0,1],[1,1]] 输出：6 解释：最开始的矩阵是 [[0,0,0],[0,0,0]]。 第一次增量操作后得到
     * [[1,2,1],[0,1,0]]。 最后的矩阵是 [[1,3,1],[1,3,1]]，里面有 6 个奇数。 示例 2：
     *
     * <p>输入：n = 2, m = 2, indices = [[1,1],[0,0]] 输出：0 解释：最后的矩阵是 [[2,2],[2,2]]，里面没有奇数。
     *
     * <p>提示：
     *
     * <p>1 <= n <= 50 1 <= m <= 50 1 <= indices.length <= 100 0 <= indices[i][0] < n 0 <=
     * indices[i][1] < m
     *
     * @param n
     * @param m
     * @param indices
     * @return
     */
    public int oddCells(int n, int m, int[][] indices) {
        int result = 0;
        // 判断当前行是奇数还是偶数
        int[] rows = new int[n];
        int[] cols = new int[m];
        for (int[] indice : indices) {
            rows[indice[0]]++;
            cols[indice[1]]++;
        }

        int oddRows = 0, oddCols = 0;
        for (int i = 0; i < n; i++) {
            if ((rows[i] & 1) > 0) {
                oddRows++;
            }
        }
        for (int i = 0; i < m; i++) {
            if ((cols[i] & 1) > 0) {
                oddCols++;
            }
        }
        result = oddRows * (m - oddCols) + oddCols * (n - oddRows);
        return result;
    }

    @Test
    public void canThreePartsEqualSum() {
        int[] A = {1, -1, 1, -1};
        logResult(canThreePartsEqualSum(A));
    }
    /**
     * 1013. 将数组分成和相等的三个部分 给你一个整数数组 A，只有可以将其划分为三个和相等的非空部分时才返回 true，否则返回 false。
     *
     * <p>形式上，如果可以找出索引 i+1 < j 且满足 (A[0] + A[1] + ... + A[i] == A[i+1] + A[i+2] + ... + A[j-1] ==
     * A[j] + A[j-1] + ... + A[A.length - 1]) 就可以将数组三等分。
     *
     * <p>示例 1：
     *
     * <p>输出：[0,2,1,-6,6,-7,9,1,2,0,1] 输出：true 解释：0 + 2 + 1 = -6 + 6 - 7 + 9 + 1 = 2 + 0 + 1 示例 2：
     *
     * <p>输入：[0,2,1,-6,6,7,9,-1,2,0,1] 输出：false 示例 3：
     *
     * <p>输入：[3,3,6,5,-2,2,5,1,-9,4] 输出：true 解释：3 + 3 = 6 = 5 - 2 + 2 + 5 + 1 - 9 + 4
     *
     * @param A
     * @return
     */
    public boolean canThreePartsEqualSum(int[] A) {
        int sum = 0;
        for (int a : A) {
            sum += a;
        }
        log.debug("sum:{}", sum);
        if (sum % 3 != 0) {
            return false;
        }
        int threePart = sum / 3;
        int part = 0;
        int flag = 0;
        for (int a : A) {
            part += a;
            if (part == threePart) {
                flag++;
                part = 0;
            }
        }
        log.debug("part:{}", part);
        if (part != 0) {
            return false;
        }

        return flag >= 3;
    }

    @Test
    public void relativeSortArray() {
        int[] arr1 = {2, 3, 1, 3, 2, 4, 6, 7, 9, 2, 19};
        int[] arr2 = {2, 1, 4, 3, 9, 6};
        int[] result = relativeSortArray(arr1, arr2);
        log.debug("result:{}", result);
    }

    /**
     * 1122. 数组的相对排序 给你两个数组，arr1 和 arr2，
     *
     * <p>arr2 中的元素各不相同 arr2 中的每个元素都出现在 arr1 中 对 arr1 中的元素进行排序，使 arr1 中项的相对顺序和 arr2 中的相对顺序相同。未在 arr2
     * 中出现过的元素需要按照升序放在 arr1 的末尾。
     *
     * <p>示例：
     *
     * <p>输入：arr1 = [2,3,1,3,2,4,6,7,9,2,19], arr2 = [2,1,4,3,9,6] 输出：[2,2,2,1,4,3,3,9,6,7,19]
     *
     * <p>提示：
     *
     * <p>arr1.length, arr2.length <= 1000 0 <= arr1[i], arr2[i] <= 1000 arr2 中的元素 arr2[i] 各不相同 arr2
     * 中的每个元素 arr2[i] 都出现在 arr1 中
     *
     * @param arr1
     * @param arr2
     * @return
     */
    public int[] relativeSortArray(int[] arr1, int[] arr2) {
        int len1 = arr1.length, len2 = arr2.length;
        if (len1 == 0) {
            return arr1;
        }
        if (len2 == 0) {
            Arrays.sort(arr1);
            return arr1;
        }
        int[] result = new int[len1];
        // 采用计数排序,但是会浪费一点空间
        int min = arr1[0], max = arr1[0];
        for (int i = 1; i < len1; i++) {
            if (arr1[i] < min) {
                min = arr1[i];
            }
            if (arr1[i] > max) {
                max = arr1[i];
            }
        }
        int len = max - min + 1;
        int[] counts = new int[len];
        for (int num : arr1) {
            counts[num - min]++;
        }
        int index = 0;
        for (int num : arr2) {
            int countIndex = num - min;
            while (counts[countIndex] > 0) {
                result[index++] = num;
                counts[countIndex]--;
            }
        }
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < counts[i]; j++) {
                result[index++] = i + min;
            }
        }
        return result;
    }

    @Test
    public void numEquivDominoPairs() {
        int[][] dominoes = {{1, 2}, {2, 1}, {3, 4}, {5, 6}, {1, 2}};
        logResult(numEquivDominoPairs(dominoes));
    }

    /**
     * 1128. 等价多米诺骨牌对的数量 给你一个由一些多米诺骨牌组成的列表 dominoes。
     *
     * <p>如果其中某一张多米诺骨牌可以通过旋转 0 度或 180 度得到另一张多米诺骨牌，我们就认为这两张牌是等价的。
     *
     * <p>形式上，dominoes[i] = [a, b] 和 dominoes[j] = [c, d] 等价的前提是 a==c 且 b==d，或是 a==d 且 b==c。
     *
     * <p>在 0 <= i < j < dominoes.length 的前提下，找出满足 dominoes[i] 和 dominoes[j] 等价的骨牌对 (i, j) 的数量。
     *
     * <p>示例：
     *
     * <p>输入：dominoes = [[1,2],[2,1],[3,4],[5,6]] 输出：1
     *
     * <p>提示：
     *
     * <p>1 <= dominoes.length <= 40000 1 <= dominoes[i][j] <= 9
     *
     * @param dominoes
     * @return
     */
    public int numEquivDominoPairs(int[][] dominoes) {
        int len = dominoes.length;
        if (len < 2) {
            return 0;
        }
        int result = 0;
        // 每张多米诺牌只有两位 [a, b] a,b 最大都是9
        int[] hash = new int[100];
        for (int i = 0; i < len; i++) {
            int[] domin = dominoes[i];
            Arrays.sort(domin);
            int index = domin[0] * 10 + domin[1];
            result += hash[index]++;
        }

        /*boolean[] visited = new boolean[len];
        for (int i = 0; i < len; i++) {
            if (visited[i]) {
                continue;
            }
            int count = 0;
            for (int j = i + 1; j < len; j++) {
                if (equalsDomino(dominoes[i],dominoes[j])) {
                    count++;
                    visited[j] = true;
                }
            }
            if (count > 1) {
                result += getCount(count);
            }
            result += count;
        }*/

        return result;
    }

    private int getCount(int num) {
        int result = (num * (num - 1)) >> 1;
        return result;
    }

    private boolean equalsDomino(int[] a, int[] b) {
        if (a.length != b.length) {
            return false;
        }
        int len = a.length;
        if (a[0] != b[0] && a[0] != b[len - 1]) {
            return false;
        }
        boolean flag = false;
        if (a[0] == b[len - 1]) {
            flag = true;
        }
        for (int i = 0; i < len; i++) {
            int index = i;
            if (flag) {
                index = len - i - 1;
            }
            if (a[i] != b[index]) {
                return false;
            }
        }
        return true;
    }

    @Test
    public void numSmallerByFrequency() {
        String[] queries = {"bbb", "cc"};
        String[] words = {"a", "aa", "aaa", "aaaa"};
        int[] result = numSmallerByFrequency(queries, words);
        log.debug("result:{}", result);
    }

    /**
     * 1170. 比较字符串最小字母出现频次 我们来定义一个函数 f(s)，其中传入参数 s 是一个非空字符串；该函数的功能是统计 s 中（按字典序比较）最小字母的出现频次。
     *
     * <p>例如，若 s = "dcce"，那么 f(s) = 2，因为最小的字母是 "c"，它出现了 2 次。
     *
     * <p>现在，给你两个字符串数组待查表 queries 和词汇表 words，请你返回一个整数数组 answer 作为答案， 其中每个 answer[i] 是满足
     * f(queries[i]) < f(W) 的词的数目，W 是词汇表 words 中的词。
     *
     * <p>示例 1：
     *
     * <p>输入：queries = ["cbd"], words = ["zaaaz"] 输出：[1] 解释：查询 f("cbd") = 1，而 f("zaaaz") = 3 所以
     * f("cbd") < f("zaaaz")。 示例 2：
     *
     * <p>输入：queries = ["bbb","cc"], words = ["a","aa","aaa","aaaa"] 输出：[1,2] 解释：第一个查询 f("bbb") <
     * f("aaaa")，第二个查询 f("aaa") 和 f("aaaa") 都 > f("cc")。
     *
     * <p>提示：
     *
     * <p>1 <= queries.length <= 2000 1 <= words.length <= 2000 1 <= queries[i].length,
     * words[i].length <= 10 queries[i][j], words[i][j] 都是小写英文字母
     *
     * @param queries
     * @param words
     * @return
     */
    public int[] numSmallerByFrequency(String[] queries, String[] words) {
        int len = queries.length;
        int wLen = words.length;
        int[] result = new int[len];

        int[] queriesNum = new int[len];
        for (int i = 0; i < len; i++) {
            String s = queries[i];
            int[] letters = new int[26];
            for (char c : s.toCharArray()) {
                letters[c - 'a']++;
            }
            for (int num : letters) {
                if (num > 0) {
                    queriesNum[i] = num;
                    break;
                }
            }
        }
        int[] wordsNum = new int[wLen];
        for (int i = 0; i < wLen; i++) {
            String s = words[i];
            int[] letters = new int[26];
            for (char c : s.toCharArray()) {
                letters[c - 'a']++;
            }
            for (int num : letters) {
                if (num > 0) {
                    wordsNum[i] = num;
                    break;
                }
            }
        }
        log.debug("queriesNum:{}", queriesNum);
        log.debug("wordsNum:{}", wordsNum);
        Arrays.sort(wordsNum);
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < wLen; j++) {
                if (wordsNum[j] > queriesNum[i]) {

                    result[i] = wLen - j;
                    break;
                }
            }
        }

        return result;
    }

    @Test
    public void maxAreaOfIsland2() {
        int[][] grid = {
            {0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
            {0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 0, 0},
            {0, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0}
        };

        logResult(maxAreaOfIsland2(grid));
    }

    /**
     * 695. 岛屿的最大面积 给定一个包含了一些 0 和 1的非空二维数组 grid , 一个 岛屿 是由四个方向 (水平或垂直) 的 1 (代表土地) 构成的组合。
     * 你可以假设二维矩阵的四个边缘都被水包围着。
     *
     * <p>找到给定的二维数组中最大的岛屿面积。(如果没有岛屿，则返回面积为0。)
     *
     * <p>示例 1:
     *
     * <p>[[0,0,1,0,0,0,0,1,0,0,0,0,0], [0,0,0,0,0,0,0,1,1,1,0,0,0], [0,1,1,0,1,0,0,0,0,0,0,0,0],
     * [0,1,0,0,1,1,0,0,1,0,1,0,0], [0,1,0,0,1,1,0,0,1,1,1,0,0], [0,0,0,0,0,0,0,0,0,0,1,0,0],
     * [0,0,0,0,0,0,0,1,1,1,0,0,0], [0,0,0,0,0,0,0,1,1,0,0,0,0]] 对于上面这个给定矩阵应返回
     * 6。注意答案不应该是11，因为岛屿只能包含水平或垂直的四个方向的‘1’。
     *
     * <p>示例 2:
     *
     * <p>[[0,0,0,0,0,0,0,0]] 对于上面这个给定的矩阵, 返回 0。
     *
     * <p>注意: 给定的矩阵grid 的长度和宽度都不超过 50。
     *
     * @param grid
     * @return
     */
    public int maxAreaOfIsland2(int[][] grid) {
        int rows = grid.length;
        if (rows == 0) {
            return 0;
        }
        int cols = grid[0].length;
        if (cols == 0) {
            return 0;
        }
        int result = 0;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 1) {
                    int area = getArea(i, j, grid);
                    if (area > result) {
                        result = area;
                    }
                }
            }
        }

        return result;
    }

    public int getArea(int row, int col, int[][] grid) {
        if (grid[row][col] != 1) {
            return 0;
        }
        grid[row][col] = 2;
        int[] dirRow = {-1, 1, 0, 0};
        int[] dirCol = {0, 0, -1, 1};
        int area = 1;
        // 四个方向
        for (int k = 0; k < 4; k++) {
            int kRow = row + dirRow[k];
            int kCol = col + dirCol[k];
            if (inArea(kRow, kCol, grid.length, grid[0].length)) {
                area += getArea(kRow, kCol, grid);
            }
        }

        return area;
    }

    @Test
    public void distanceBetweenBusStops() {
        int[] distance = {1, 2, 3, 4};
        int start = 0, destination = 3;
        logResult(distanceBetweenBusStops(distance, start, destination));
    }

    /**
     * 1184. 公交站间的距离 环形公交路线上有 n 个站，按次序从 0 到 n - 1 进行编号。我们已知每一对相邻公交站之间的距离，distance[i] 表示编号为 i 的车站和编号为
     * (i + 1) % n 的车站之间的距离。
     *
     * <p>环线上的公交车都可以按顺时针和逆时针的方向行驶。
     *
     * <p>返回乘客从出发点 start 到目的地 destination 之间的最短距离。
     *
     * <p>示例 1：
     *
     * <p>输入：distance = [1,2,3,4], start = 0, destination = 1 输出：1 解释：公交站 0 和 1 之间的距离是 1 或 9，最小值是 1。
     *
     * <p>示例 2：
     *
     * <p>输入：distance = [1,2,3,4], start = 0, destination = 2 输出：3 解释：公交站 0 和 2 之间的距离是 3 或 7，最小值是 3。
     *
     * <p>示例 3：
     *
     * <p>输入：distance = [1,2,3,4], start = 0, destination = 3 输出：4 解释：公交站 0 和 3 之间的距离是 6 或 4，最小值是 4。
     *
     * <p>提示：
     *
     * <p>1 <= n <= 10^4 distance.length == n 0 <= start, destination < n 0 <= distance[i] <= 10^4
     *
     * @param distance
     * @param start
     * @param destination
     * @return
     */
    public int distanceBetweenBusStops(int[] distance, int start, int destination) {
        int result = 0;
        int sum = 0;
        if (start > destination) {
            int tmp = start;
            start = destination;
            destination = tmp;
        }

        for (int i = 0; i < distance.length; i++) {
            sum += distance[i];
            if (i >= start && i < destination) {
                result += distance[i];
            }
        }
        return Math.min(result, sum - result);
    }

    @Test
    public void maxNumberOfBalloons() {
        String s = "nlaebolko";
        logResult(maxNumberOfBalloons(s));
    }

    /**
     * 1189. “气球” 的最大数量 给你一个字符串 text，你需要使用 text 中的字母来拼凑尽可能多的单词 "balloon"（气球）。
     *
     * <p>字符串 text 中的每个字母最多只能被使用一次。请你返回最多可以拼凑出多少个单词 "balloon"。
     *
     * <p>示例 1：
     *
     * <p>输入：text = "nlaebolko" 输出：1 示例 2：
     *
     * <p>输入：text = "loonbalxballpoon" 输出：2 示例 3：
     *
     * <p>输入：text = "leetcode" 输出：0
     *
     * <p>提示：
     *
     * <p>1 <= text.length <= 10^4 text 全部由小写英文字母组成
     *
     * @param text
     * @return
     */
    public int maxNumberOfBalloons(String text) {
        int[] letters = new int[26];
        for (char c : text.toCharArray()) {
            letters[c - 'a']++;
        }
        letters['l' - 'a'] >>= 1;
        letters['o' - 'a'] >>= 1;
        String balloon = "balon";
        int result = Integer.MAX_VALUE;
        for (char c : balloon.toCharArray()) {
            if (letters[c - 'a'] < result) {
                result = letters[c - 'a'];
            }
        }

        return result;
    }

    /**
     * 1200. 最小绝对差 给你个整数数组 arr，其中每个元素都 不相同。
     *
     * <p>请你找到所有具有最小绝对差的元素对，并且按升序的顺序返回。
     *
     * <p>示例 1：
     *
     * <p>输入：arr = [4,2,1,3] 输出：[[1,2],[2,3],[3,4]] 示例 2：
     *
     * <p>输入：arr = [1,3,6,10,15] 输出：[[1,3]] 示例 3：
     *
     * <p>输入：arr = [3,8,-10,23,19,-4,-14,27] 输出：[[-14,-10],[19,23],[23,27]]
     *
     * <p>提示：
     *
     * <p>2 <= arr.length <= 10^5 -10^6 <= arr[i] <= 10^6
     *
     * @param arr
     * @return
     */
    public List<List<Integer>> minimumAbsDifference(int[] arr) {
        List<List<Integer>> result = new ArrayList<>();
        int len = arr.length;
        if (len < 2) {
            return result;
        }
        Arrays.sort(arr);
        int min = arr[1] - arr[0];
        for (int i = 2; i < len; i++) {
            if (arr[i] - arr[i - 1] < min) {
                min = arr[i] - arr[i - 1];
            }
        }
        for (int i = 1; i < len; i++) {
            if (arr[i] - arr[i - 1] == min) {
                List<Integer> list = new ArrayList<>();
                list.add(arr[i - 1]);
                list.add(arr[i]);
                result.add(list);
            }
        }

        return result;
    }

    /**
     * 1207. 独一无二的出现次数 给你一个整数数组 arr，请你帮忙统计数组中每个数的出现次数。
     *
     * <p>如果每个数的出现次数都是独一无二的，就返回 true；否则返回 false。
     *
     * <p>示例 1：
     *
     * <p>输入：arr = [1,2,2,1,1,3] 输出：true 解释：在该数组中，1 出现了 3 次，2 出现了 2 次，3 只出现了 1 次。没有两个数的出现次数相同。 示例 2：
     *
     * <p>输入：arr = [1,2] 输出：false 示例 3：
     *
     * <p>输入：arr = [-3,0,1,-3,1,1,1,-3,10,0] 输出：true
     *
     * @param arr
     * @return
     */
    public boolean uniqueOccurrences(int[] arr) {
        if (arr.length <= 1) {
            return true;
        }
        // 计数排序
        int min = arr[0], max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < min) {
                min = arr[i];
            }
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        int countLen = max - min + 1;

        int[] counts = new int[countLen];
        for (int num : arr) {
            counts[num - min]++;
        }
        Set<Integer> set = new HashSet<>();
        for (int count : counts) {
            if (count > 0) {
                if (set.contains(count)) {
                    return false;
                }
                set.add(count);
            }
        }
        return true;
    }

    @Test
    public void getLeastNumbers() {
        int[] arr = {0, 0, 0, 2, 0, 5};
        int k = 0;
        int[] result = getLeastNumbers(arr, k);
        log.debug("result:{}", result);
    }

    /**
     * 面试题40. 最小的k个数 输入整数数组 arr ，找出其中最小的 k 个数。例如，输入4、5、1、6、2、7、3、8这8个数字，则最小的4个数字是1、2、3、4。
     *
     * <p>示例 1：
     *
     * <p>输入：arr = [3,2,1], k = 2 输出：[1,2] 或者 [2,1] 示例 2：
     *
     * <p>输入：arr = [0,1,2,1], k = 1 输出：[0]
     *
     * <p>限制：
     *
     * <p>0 <= k <= arr.length <= 10000 0 <= arr[i] <= 10000
     *
     * @param arr
     * @param k
     * @return
     */
    public int[] getLeastNumbers(int[] arr, int k) {
        if (k == 0) {
            return new int[0];
        }

        // 构建一个最大堆
        Queue<Integer> queue = new PriorityQueue<>(k, (a, b) -> b - a);

        for (int num : arr) {

            if (queue.size() < k) {
                queue.offer(num);
                continue;
            }
            if (queue.peek() > num) {
                queue.poll();
                queue.offer(num);
            }
        }

        int[] nums = new int[k];
        int index = 0;
        Iterator<Integer> iterator = queue.iterator();
        while (iterator.hasNext()) {
            nums[index++] = iterator.next();
        }
        return nums;
    }

    @Test
    public void minIncrementForUnique() {
        int[] nums = {3, 2, 1, 2, 1, 7};
        logResult(minIncrementForUnique(nums));
    }

    /**
     * 945. 使数组唯一的最小增量 给定整数数组 A，每次 move 操作将会选择任意 A[i]，并将其递增 1。
     *
     * <p>返回使 A 中的每个值都是唯一的最少操作次数。
     *
     * <p>示例 1:
     *
     * <p>输入：[1,2,2] 输出：1 解释：经过一次 move 操作，数组将变为 [1, 2, 3]。 示例 2:
     *
     * <p>输入：[3,2,1,2,1,7] 输出：6 解释：经过 6 次 move 操作，数组将变为 [3, 4, 1, 2, 5, 7]。 可以看出 5 次或 5 次以下的 move
     * 操作是不能让数组的每个值唯一的。 提示：
     *
     * <p>0 <= A.length <= 40000 0 <= A[i] < 40000
     *
     * @param A
     * @return
     */
    public int minIncrementForUnique(int[] A) {
        int result = 0;
        int len = A.length;
        if (len == 0) {
            return 0;
        }
        /*int min = A[0];
        int max = A[0];
        int sum = 0;
        for (int i = 1; i < len; i++) {
            if (A[i] < min) {
                min = A[i];
            }
            if (A[i] > max) {
                max = A[i];
            }
        }*/
        int[] counts = new int[4000];
        for (int num : A) {
            counts[num]++;
        }
        for (int i = 0; i < counts.length; i++) {
            while (counts[i] > 1) {
                int j = 1;
                while (i + j < counts.length) {
                    result++;
                    if (i + j < counts.length && counts[i + j] == 0) {
                        counts[i + j] = 1;
                        break;
                    }
                    j++;
                }
                counts[i]--;
            }
        }

        return result;
    }

    /**
     * 5348. 两个数组间的距离值 给你两个整数数组 arr1 ， arr2 和一个整数 d ，请你返回两个数组之间的 距离值 。
     *
     * <p>「距离值」 定义为符合此描述的元素数目：对于元素 arr1[i] ，不存在任何元素 arr2[j] 满足 |arr1[i]-arr2[j]| <= d 。
     *
     * <p>示例 1：
     *
     * <p>输入：arr1 = [4,5,8], arr2 = [10,9,1,8], d = 2 输出：2 解释： 对于 arr1[0]=4 我们有： |4-10|=6 > d=2
     * |4-9|=5 > d=2 |4-1|=3 > d=2 |4-8|=4 > d=2 对于 arr1[1]=5 我们有： |5-10|=5 > d=2 |5-9|=4 > d=2
     * |5-1|=4 > d=2 |5-8|=3 > d=2 对于 arr1[2]=8 我们有： |8-10|=2 <= d=2 |8-9|=1 <= d=2 |8-1|=7 > d=2
     * |8-8|=0 <= d=2 示例 2：
     *
     * <p>输入：arr1 = [1,4,2,3], arr2 = [-4,-3,6,10,20,30], d = 3 输出：2 示例 3：
     *
     * <p>输入：arr1 = [2,1,100,3], arr2 = [-5,-2,10,-3,7], d = 6 输出：1
     *
     * <p>提示：
     *
     * <p>1 <= arr1.length, arr2.length <= 500 -10^3 <= arr1[i], arr2[j] <= 10^3 0 <= d <= 100
     *
     * @param arr1
     * @param arr2
     * @param d
     * @return
     */
    public int findTheDistanceValue(int[] arr1, int[] arr2, int d) {
        // d的意思是arr1的一个元素和arr2的所有元素做差的绝对值，全部大于d，则说明arr1中的这个元素满足条件，
        int result = 0;
        for (int num1 : arr1) {
            boolean flag = true;
            for (int num2 : arr2) {
                if (Math.abs(num1 - num2) <= d) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                result++;
            }
        }

        return result;
    }

    /**
     * 5364. 按既定顺序创建目标数组 给你两个整数数组 nums 和 index。你需要按照以下规则创建目标数组：
     *
     * <p>目标数组 target 最初为空。 按从左到右的顺序依次读取 nums[i] 和 index[i]，在 target 数组中的下标 index[i] 处插入值 nums[i] 。
     * 重复上一步，直到在 nums 和 index 中都没有要读取的元素。 请你返回目标数组。
     *
     * <p>题目保证数字插入位置总是存在。
     *
     * <p>示例 1：
     *
     * <p>输入：nums = [0,1,2,3,4], index = [0,1,2,2,1] 输出：[0,4,1,3,2] 解释： nums index target 0 0 [0] 1
     * 1 [0,1] 2 2 [0,1,2] 3 2 [0,1,3,2] 4 1 [0,4,1,3,2] 示例 2：
     *
     * <p>输入：nums = [1,2,3,4,0], index = [0,1,2,3,0] 输出：[0,1,2,3,4] 解释： nums index target 1 0 [1] 2
     * 1 [1,2] 3 2 [1,2,3] 4 3 [1,2,3,4] 0 0 [0,1,2,3,4] 示例 3：
     *
     * <p>输入：nums = [1], index = [0] 输出：[1]
     *
     * <p>提示：
     *
     * <p>1 <= nums.length, index.length <= 100 nums.length == index.length 0 <= nums[i] <= 100 0 <=
     * index[i] <= i
     *
     * @param nums
     * @param index
     * @return
     */
    public int[] createTargetArray(int[] nums, int[] index) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            list.add(index[i], nums[i]);
        }
        int[] result = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            result[i] = list.get(i);
        }
        return result;
    }

    /**
     * 给你一个 m * n 的矩阵，矩阵中的数字 各不相同 。请你按 任意 顺序返回矩阵中的所有幸运数。
     *
     * <p>幸运数是指矩阵中满足同时下列两个条件的元素：
     *
     * <p>在同一行的所有元素中最小 在同一列的所有元素中最大
     *
     * <p>示例 1：
     *
     * <p>输入：matrix = [[3,7,8],[9,11,13],[15,16,17]] 输出：[15] 解释：15 是唯一的幸运数，因为它是其所在行中的最小值，也是所在列中的最大值。
     * 示例 2：
     *
     * <p>输入：matrix = [[1,10,4,2],[9,3,8,7],[15,16,17,12]] 输出：[12] 解释：12
     * 是唯一的幸运数，因为它是其所在行中的最小值，也是所在列中的最大值。 示例 3：
     *
     * <p>输入：matrix = [[7,8],[1,2]] 输出：[7]
     *
     * <p>提示：
     *
     * <p>m == mat.length n == mat[i].length 1 <= n, m <= 50 1 <= matrix[i][j] <= 10^5 矩阵中的所有元素都是不同的
     *
     * @param matrix
     * @return
     */
    public List<Integer> luckyNumbers(int[][] matrix) {
        List<Integer> list = new ArrayList<>();
        int rows = matrix.length;
        if (rows == 0) {
            return list;
        }
        int cols = matrix[0].length;
        if (cols == 0) {
            return list;
        }
        //  在同一行的所有元素中最小 在同一列的所有元素中最大
        for (int i = 0; i < rows; i++) {
            int min = matrix[i][0];
            int minIndex = 0;
            for (int j = 1; j < cols; j++) {
                if (matrix[i][j] < min) {
                    min = matrix[i][j];
                    minIndex = j;
                }
            }
            boolean flag = true;
            for (int k = 0; k < rows; k++) {
                if (matrix[k][minIndex] > min) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                list.add(min);
            }
        }

        return list;
    }

    @Test
    public void countNegatives() {
        int[][] grid = {{5, 1, 0}, {-5, -5, -5}};
        logResult(countNegatives(grid));
    }
    /**
     * 1351. 统计有序矩阵中的负数 给你一个 m * n 的矩阵 grid，矩阵中的元素无论是按行还是按列，都以非递增顺序排列。
     *
     * <p>请你统计并返回 grid 中 负数 的数目。
     *
     * <p>示例 1：
     *
     * <p>输入：grid = [[4,3,2,-1],[3,2,1,-1],[1,1,-1,-2],[-1,-1,-2,-3]] 输出：8 解释：矩阵中共有 8 个负数。 示例 2：
     *
     * <p>输入：grid = [[3,2],[1,0]] 输出：0 示例 3：
     *
     * <p>输入：grid = [[1,-1],[-1,-1]] 输出：3 示例 4：
     *
     * <p>输入：grid = [[-1]] 输出：1
     *
     * <p>提示：
     *
     * <p>m == grid.length n == grid[i].length 1 <= m, n <= 100 -100 <= grid[i][j] <= 100
     *
     * @param grid
     * @return
     */
    public int countNegatives(int[][] grid) {
        int count = 0;
        int m = grid.length;
        int n = grid[0].length;
        if (grid[0][0] < 0) {
            return m * n;
        }
        if (grid[m - 1][n - 1] >= 0) {
            return 0;
        }

        for (int i = 0; i < m; i++) {
            // 二分 查第一个

            if (grid[i][0] < 0) {
                count += n;
                continue;
            }
            if (grid[i][n - 1] >= 0) {
                continue;
            }
            int low = 0, high = n - 1;
            while (low < high) {
                int mid = (low + high) >> 1;
                if (grid[i][mid] < 0) {
                    high = mid;
                } else {
                    low = mid + 1;
                }
            }
            log.debug("index:{}", high);
            if (high > 0) {
                count += n - high;
            }
        }

        return count;
    }

    @Test
    public void arrayRankTransform() {
        int[] arr = {37, 12, 28, 9, 100, 56, 80, 5, 12};
        int[] result = arrayRankTransform(arr);
        log.debug("result :{}", result);
    }

    /**
     * 1331. 数组序号转换 给你一个整数数组 arr ，请你将数组中的每个元素替换为它们排序后的序号。
     *
     * <p>序号代表了一个元素有多大。序号编号的规则如下：
     *
     * <p>序号从 1 开始编号。 一个元素越大，那么序号越大。如果两个元素相等，那么它们的序号相同。 每个数字的序号都应该尽可能地小。
     *
     * <p>示例 1：
     *
     * <p>输入：arr = [40,10,20,30] 输出：[4,1,2,3] 解释：40 是最大的元素。 10 是最小的元素。 20 是第二小的数字。 30 是第三小的数字。 示例 2：
     *
     * <p>输入：arr = [100,100,100] 输出：[1,1,1] 解释：所有元素有相同的序号。 示例 3：
     *
     * <p>输入：arr = [37,12,28,9,100,56,80,5,12] 输出：[5,3,4,2,8,6,7,1,3]
     *
     * <p>提示：
     *
     * <p>0 <= arr.length <= 105 -109 <= arr[i] <= 109
     *
     * @param arr
     * @return
     */
    public int[] arrayRankTransform(int[] arr) {
        int[] result = new int[arr.length];
        if (arr.length == 0) {
            return result;
        }
        /*System.arraycopy(arr,0,result,0,arr.length);
        Arrays.sort(arr);
        Map<Integer,Integer> map = new HashMap<>();
        int tmp = 1;
        map.put(arr[0],1);
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] != arr[i-1]) {
                map.put(arr[i],i+tmp);
            } else{
                tmp--;
            }
        }
        for (int i = 0; i < result.length; i++) {
            int num = result[i];
            result[i] = map.get(num);
        }*/
        // 用桶排序
        int max = arr[0], min = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
            if (arr[i] < min) {
                min = arr[i];
            }
        }
        int[] bucket = new int[max - min + 1];
        for (int num : arr) {
            bucket[num - min]++;
        }
        int index = 1;
        for (int i = 0; i < bucket.length; i++) {
            if (bucket[i] != 0) {
                bucket[i] = index++;
            }
        }
        for (int i = 0; i < result.length; i++) {
            result[i] = bucket[arr[i] - min];
        }
        return result;
    }

    @Test
    public void smallerNumbersThanCurrent() {
        int[] nums = {8, 1, 2, 2, 3};
        int[] result = smallerNumbersThanCurrent(nums);
        log.debug("result:{}", result);
    }
    /**
     * 1365. 有多少小于当前数字的数字 给你一个数组 nums，对于其中每个元素 nums[i]，请你统计数组中比它小的所有数字的数目。
     *
     * <p>换而言之，对于每个 nums[i] 你必须计算出有效的 j 的数量，其中 j 满足 j != i 且 nums[j] < nums[i] 。
     *
     * <p>以数组形式返回答案。
     *
     * <p>示例 1：
     *
     * <p>输入：nums = [8,1,2,2,3] 输出：[4,0,1,1,3] 解释： 对于 nums[0]=8 存在四个比它小的数字：（1，2，2 和 3）。 对于 nums[1]=1
     * 不存在比它小的数字。 对于 nums[2]=2 存在一个比它小的数字：（1）。 对于 nums[3]=2 存在一个比它小的数字：（1）。 对于 nums[4]=3
     * 存在三个比它小的数字：（1，2 和 2）。 示例 2：
     *
     * <p>输入：nums = [6,5,4,8] 输出：[2,1,0,3] 示例 3：
     *
     * <p>输入：nums = [7,7,7,7] 输出：[0,0,0,0]
     *
     * <p>提示：
     *
     * <p>2 <= nums.length <= 500 0 <= nums[i] <= 100
     *
     * @param nums
     * @return
     */
    public int[] smallerNumbersThanCurrent(int[] nums) {
        int len = nums.length;
        int[] result = new int[len];

        int min = nums[0], max = nums[0];
        for (int i = 1; i < len; i++) {
            if (nums[i] > max) {
                max = nums[i];
            }
            if (nums[i] < min) {
                min = nums[i];
            }
        }
        int[] bucket = new int[max - min + 1];
        for (int num : nums) {
            bucket[num - min]++;
        }
        int count = 0;
        for (int i = 0; i < bucket.length; i++) {
            if (bucket[i] != 0) {
                int tmp = bucket[i];
                bucket[i] = count;
                count += tmp;
            }
        }
        for (int i = 0; i < result.length; i++) {
            result[i] = bucket[nums[i] - min];
        }

        return result;
    }

    /**
     * 1346. 检查整数及其两倍数是否存在 给你一个整数数组 arr，请你检查是否存在两个整数 N 和 M，满足 N 是 M 的两倍（即，N = 2 * M）。
     *
     * <p>更正式地，检查是否存在两个下标 i 和 j 满足：
     *
     * <p>i != j 0 <= i, j < arr.length arr[i] == 2 * arr[j]
     *
     * <p>示例 1：
     *
     * <p>输入：arr = [10,2,5,3] 输出：true 解释：N = 10 是 M = 5 的两倍，即 10 = 2 * 5 。 示例 2：
     *
     * <p>输入：arr = [7,1,14,11] 输出：true 解释：N = 14 是 M = 7 的两倍，即 14 = 2 * 7 。 示例 3：
     *
     * <p>输入：arr = [3,1,7,11] 输出：false 解释：在该情况下不存在 N 和 M 满足 N = 2 * M 。
     *
     * <p>提示：
     *
     * <p>2 <= arr.length <= 500 -10^3 <= arr[i] <= 10^3
     *
     * @param arr
     * @return
     */
    public boolean checkIfExist(int[] arr) {
        Set<Integer> set = new HashSet<>();
        for (int num : arr) {
            // 偶数,存在其一半
            if ((num & 1) == 0 && (set.contains(num >> 1)) || set.contains(num << 1)) {
                return true;
            }
            set.add(num);
        }
        return false;
    }

    @Test
    public void replaceElements() {
        int[] arr = {17, 18, 5, 4, 6, 1};
        int[] result = replaceElements(arr);
        log.debug("result:{}", result);
    }
    /**
     * 1299. 将每个元素替换为右侧最大元素 给你一个数组 arr ，请你将每个元素用它右边最大的元素替换，如果是最后一个元素，用 -1 替换。
     *
     * <p>完成所有替换操作后，请你返回这个数组。
     *
     * <p>示例：
     *
     * <p>输入：arr = [17,18,5,4,6,1] 输出：[18,6,6,6,1,-1]
     *
     * <p>提示：
     *
     * <p>1 <= arr.length <= 10^4 1 <= arr[i] <= 10^5
     *
     * @param arr
     * @return
     */
    public int[] replaceElements(int[] arr) {
        int max = -1;
        for (int i = arr.length - 1; i >= 0; i--) {
            int tmp = arr[i];
            arr[i] = max;
            if (tmp > max) {
                max = tmp;
            }
        }
        return arr;
    }

    /**
     * 999. 车的可用捕获量 在一个 8 x 8 的棋盘上，有一个白色车（rook）。也可能有空方块，白色的象（bishop）和黑色的卒（pawn）。它们分别以字符 “R”，“.”，“B”
     * 和 “p” 给出。大写字符表示白棋，小写字符表示黑棋。
     *
     * <p>车按国际象棋中的规则移动：它选择四个基本方向中的一个（北，东，西和南），然后朝那个方向移动，直到它选择停止、到达棋盘的边缘或移动到同一方格来捕获该方格上颜色相反的卒。另外，车不能与其他友方（白色）象进入同一个方格。
     *
     * <p>返回车能够在一次移动中捕获到的卒的数量。
     *
     * <p>示例 1：
     *
     * <p>输入：[[".",".",".",".",".",".",".","."],[".",".",".","p",".",".",".","."],[".",".",".","R",".",".",".","p"],[".",".",".",".",".",".",".","."],[".",".",".",".",".",".",".","."],[".",".",".","p",".",".",".","."],[".",".",".",".",".",".",".","."],[".",".",".",".",".",".",".","."]]
     * 输出：3 解释： 在本例中，车能够捕获所有的卒。 示例 2：
     *
     * <p>输入：[[".",".",".",".",".",".",".","."],[".","p","p","p","p","p",".","."],[".","p","p","B","p","p",".","."],[".","p","B","R","B","p",".","."],[".","p","p","B","p","p",".","."],[".","p","p","p","p","p",".","."],[".",".",".",".",".",".",".","."],[".",".",".",".",".",".",".","."]]
     * 输出：0 解释： 象阻止了车捕获任何卒。 示例 3：
     *
     * <p>输入：[[".",".",".",".",".",".",".","."],[".",".",".","p",".",".",".","."],[".",".",".","p",".",".",".","."],["p","p",".","R",".","p","B","."],[".",".",".",".",".",".",".","."],[".",".",".","B",".",".",".","."],[".",".",".","p",".",".",".","."],[".",".",".",".",".",".",".","."]]
     * 输出：3 解释： 车可以捕获位置 b5，d6 和 f5 的卒。
     *
     * <p>提示：
     *
     * <p>board.length == board[i].length == 8 board[i][j] 可以是 'R'，'.'，'B' 或 'p' 只有一个格子上存在
     * board[i][j] == 'R'
     *
     * @param board
     * @return
     */
    public int numRookCaptures(char[][] board) {
        int[] dirRow = {-1, 1, 0, 0};
        int[] dirCol = {0, 0, -1, 1};
        int rows = board.length;
        if (rows == 0) {
            return 0;
        }
        int cols = board[0].length;
        if (cols == 0) {
            return 0;
        }
        int row = 0, col = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (board[i][j] == 'R') {
                    row = i;
                    col = j;
                }
            }
        }
        int result = 0;
        for (int i = 0; i < 4; i++) {

            int cRow = row + dirRow[i];
            int cCol = col + dirCol[i];
            while (inArea(cRow, cCol, rows, cols)) {
                if (board[cRow][cCol] == 'B') {
                    break;
                }
                if (board[cRow][cCol] == 'p') {
                    result++;
                    break;
                }
                cRow += dirRow[i];
                cCol += dirCol[i];
            }
        }

        return result;
    }

    /**
     * 1356. 根据数字二进制下 1 的数目排序 给你一个整数数组 arr 。请你将数组中的元素按照其二进制表示中数字 1 的数目升序排序。
     *
     * <p>如果存在多个数字二进制中 1 的数目相同，则必须将它们按照数值大小升序排列。
     *
     * <p>请你返回排序后的数组。
     *
     * <p>示例 1：
     *
     * <p>输入：arr = [0,1,2,3,4,5,6,7,8] 输出：[0,1,2,4,8,3,5,6,7] 解释：[0] 是唯一一个有 0 个 1 的数。 [1,2,4,8] 都有 1
     * 个 1 。 [3,5,6] 有 2 个 1 。 [7] 有 3 个 1 。 按照 1 的个数排序得到的结果数组为 [0,1,2,4,8,3,5,6,7] 示例 2：
     *
     * <p>输入：arr = [1024,512,256,128,64,32,16,8,4,2,1] 输出：[1,2,4,8,16,32,64,128,256,512,1024]
     * 解释：数组中所有整数二进制下都只有 1 个 1 ，所以你需要按照数值大小将它们排序。 示例 3：
     *
     * <p>输入：arr = [10000,10000] 输出：[10000,10000] 示例 4：
     *
     * <p>输入：arr = [2,3,5,7,11,13,17,19] 输出：[2,3,5,17,7,11,13,19] 示例 5：
     *
     * <p>输入：arr = [10,100,1000,10000] 输出：[10,100,10000,1000]
     *
     * <p>提示：
     *
     * <p>1 <= arr.length <= 500 0 <= arr[i] <= 10^4
     *
     * @param arr
     * @return
     */
    public int[] sortByBits(int[] arr) {
        Integer[] sortArr = new Integer[arr.length];
        for (int i = 0; i < arr.length; i++) {
            sortArr[i] = arr[i];
        }

        Arrays.sort(
                sortArr,
                (a, b) ->
                        Integer.bitCount(a) != Integer.bitCount(b)
                                ? Integer.bitCount(a) - Integer.bitCount(b)
                                : a - b);
        for (int i = 0; i < arr.length; i++) {
            arr[i] = sortArr[i];
        }
        return arr;
    }

    /**
     * 1337. 方阵中战斗力最弱的 K 行 给你一个大小为 m * n 的方阵 mat，方阵由若干军人和平民组成，分别用 0 和 1 表示。
     *
     * <p>请你返回方阵中战斗力最弱的 k 行的索引，按从最弱到最强排序。
     *
     * <p>如果第 i 行的军人数量少于第 j 行，或者两行军人数量相同但 i 小于 j，那么我们认为第 i 行的战斗力比第 j 行弱。
     *
     * <p>军人 总是 排在一行中的靠前位置，也就是说 1 总是出现在 0 之前。
     *
     * <p>示例 1：
     *
     * <p>输入：mat = [[1,1,0,0,0], [1,1,1,1,0], [1,0,0,0,0], [1,1,0,0,0], [1,1,1,1,1]], k = 3
     * 输出：[2,0,3] 解释： 每行中的军人数目： 行 0 -> 2 行 1 -> 4 行 2 -> 1 行 3 -> 2 行 4 -> 5 从最弱到最强对这些行排序后得到
     * [2,0,3,1,4] 示例 2：
     *
     * <p>输入：mat = [[1,0,0,0], [1,1,1,1], [1,0,0,0], [1,0,0,0]], k = 2 输出：[0,2] 解释： 每行中的军人数目： 行 0 ->
     * 1 行 1 -> 4 行 2 -> 1 行 3 -> 1 从最弱到最强对这些行排序后得到 [0,2,3,1]
     *
     * <p>提示：
     *
     * <p>m == mat.length n == mat[i].length 2 <= n, m <= 100 1 <= k <= m matrix[i][j] 不是 0 就是 1
     *
     * @param mat
     * @param k
     * @return
     */
    public int[] kWeakestRows(int[][] mat, int k) {
        int[] result = new int[k];
        int rows = mat.length;
        int cols = mat[0].length;
        for (int i = 0; i < rows; i++) {
            int count = 0;
            for (int j = 0; j < cols; j++) {
                if (mat[i][j] == 0) {
                    break;
                }
                count++;
            }
            mat[i][0] = count;
            mat[i][1] = i;
        }
        Arrays.sort(mat, (a, b) -> a[0] - b[0]);
        for (int i = 0; i < k; i++) {
            result[i] = mat[i][1];
        }

        return result;
    }

    /**
     * 1313. 解压缩编码列表 给你一个以行程长度编码压缩的整数列表 nums 。
     *
     * <p>考虑每对相邻的两个元素 freq, val] = [nums[2*i], nums[2*i+1]] （其中 i >= 0 ），每一对都表示解压后子列表中有 freq 个值为 val
     * 的元素，你需要从左到右连接所有子列表以生成解压后的列表。
     *
     * <p>请你返回解压后的列表。
     *
     * <p>示例：
     *
     * <p>输入：nums = [1,2,3,4] 输出：[2,4,4,4] 解释：第一对 [1,2] 代表着 2 的出现频次为 1，所以生成数组 [2]。 第二对 [3,4] 代表着 4
     * 的出现频次为 3，所以生成数组 [4,4,4]。 最后将它们串联到一起 [2] + [4,4,4] = [2,4,4,4]。 示例 2：
     *
     * <p>输入：nums = [1,1,2,3] 输出：[1,3,3]
     *
     * <p>提示：
     *
     * <p>2 <= nums.length <= 100 nums.length % 2 == 0 1 <= nums[i] <= 100
     *
     * @param nums
     * @return
     */
    public int[] decompressRLElist(int[] nums) {

        int len = 0;
        for (int i = 0; i < nums.length; i += 2) {
            len += nums[i];
        }
        int[] result = new int[len];
        int index = 0;
        for (int i = 0; i < nums.length; i += 2) {
            int count = nums[i];
            int num = nums[i + 1];
            while (count-- > 0) {
                result[index++] = num;
            }
        }

        return result;
    }

    @Test
    public void tictactoe() {
        int[][] moves = {{1, 2}, {2, 1}, {1, 0}, {0, 0}, {0, 1}, {2, 0}, {1, 1}};
        logResult(tictactoe(moves));
    }

    /**
     * A 和 B 在一个 3 x 3 的网格上玩井字棋。
     *
     * <p>井字棋游戏的规则如下：
     *
     * <p>玩家轮流将棋子放在空方格 (" ") 上。 第一个玩家 A 总是用 "X" 作为棋子，而第二个玩家 B 总是用 "O" 作为棋子。 "X" 和 "O"
     * 只能放在空方格中，而不能放在已经被占用的方格上。 只要有 3 个相同的（非空）棋子排成一条直线（行、列、对角线）时，游戏结束。 如果所有方块都放满棋子（不为空），游戏也会结束。
     * 游戏结束后，棋子无法再进行任何移动。 给你一个数组 moves，其中每个元素是大小为 2 的另一个数组（元素分别对应网格的行和列），它按照 A 和 B 的行动顺序（先 A 后
     * B）记录了两人各自的棋子位置。
     *
     * <p>如果游戏存在获胜者（A 或 B），就返回该游戏的获胜者；如果游戏以平局结束，则返回 "Draw"；如果仍会有行动（游戏未结束），则返回 "Pending"。
     *
     * <p>你可以假设 moves 都 有效（遵循井字棋规则），网格最初是空的，A 将先行动。
     *
     * <p>示例 1：
     *
     * <p>输入：moves = [[0,0],[2,0],[1,1],[2,1],[2,2]] 输出："A" 解释："A" 获胜，他总是先走。 "X " "X " "X " "X " "X
     * " " " -> " " -> " X " -> " X " -> " X " " " "O " "O " "OO " "OOX" 示例 2：
     *
     * <p>输入：moves = [[0,0],[1,1],[0,1],[0,2],[1,0],[2,0]] 输出："B" 解释："B" 获胜。 "X " "X " "XX " "XXO"
     * "XXO" "XXO" " " -> " O " -> " O " -> " O " -> "XO " -> "XO " " " " " " " " " " " "O " 示例 3：
     *
     * <p>输入：moves = [[0,0],[1,1],[2,0],[1,0],[1,2],[2,1],[0,1],[0,2],[2,2]] 输出："Draw"
     * 输出：由于没有办法再行动，游戏以平局结束。 "XXO" "OOX" "XOX" 示例 4：
     *
     * <p>输入：moves = [[0,0],[1,1]] 输出："Pending" 解释：游戏还没有结束。 "X " " O " " "
     *
     * <p>提示：
     *
     * <p>1 <= moves.length <= 9 moves[i].length == 2 0 <= moves[i][j] <= 2 moves 里没有重复的元素。 moves
     * 遵循井字棋的规则。
     *
     * @param moves
     * @return
     */
    public String tictactoe(int[][] moves) {
        int len = moves.length;
        if (len < 5) {
            return "Pending";
        }
        // 思路,把棋盘的9个位置映射到 9位的二进制数上
        // 胜利的情况
        // 000000111 7
        // 000111000 56
        // 111000000 448
        // 001001001 73
        // 010010010 146
        // 100100100 292
        // 100010001 273
        // 001010100 84
        int[] winList = {7, 56, 448, 73, 146, 292, 273, 84};
        int aNum = 0, bNum = 0;
        String result = "";
        for (int i = 0; i < len; i++) {
            int[] move = moves[i];
            int x = move[0], y = move[1];
            int num = x * 3 + y;
            if ((i & 1) == 0) {
                // A
                aNum += 1 << num;
            } else {
                // B
                bNum += 1 << num;
            }
        }

        for (int win : winList) {
            // if the moving result contains the winning case in record, then win
            if ((aNum & win) == win) {
                return "A";
            }
            if ((bNum & win) == win) {
                return "B";
            }
        }
        if (len < 9) {
            result = "Pending";
        } else {
            result = "Draw";
        }

        return result;
    }

    @Test
    public void maxDistance() {
        int[][] grid = {{1, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 1}};
        logResult(maxDistance(grid));
    }
    /**
     * 1162. 地图分析 你现在手里有一份大小为 N x N 的『地图』（网格） grid，上面的每个『区域』（单元格）都用 0 和 1 标记好了。 其中 0 代表海洋，1
     * 代表陆地，你知道距离陆地区域最远的海洋区域是是哪一个吗？请返回该海洋区域到离它最近的陆地区域的距离。
     *
     * <p>我们这里说的距离是『曼哈顿距离』（ Manhattan Distance）：(x0, y0) 和 (x1, y1) 这两个区域之间的距离是 |x0 - x1| + |y0 -
     * y1| 。
     *
     * <p>如果我们的地图上只有陆地或者海洋，请返回 -1。
     *
     * <p>示例 1：
     *
     * <p>输入：[[1,0,1],[0,0,0],[1,0,1]] 输出：2 解释： 海洋区域 (1, 1) 和所有陆地区域之间的距离都达到最大，最大距离为 2。 示例 2：
     *
     * <p>输入：[[1,0,0],[0,0,0],[0,0,0]] 输出：4 解释： 海洋区域 (2, 2) 和所有陆地区域之间的距离都达到最大，最大距离为 4。
     *
     * <p>提示：
     *
     * <p>1 <= grid.length == grid[0].length <= 100 grid[i][j] 不是 0 就是 1
     *
     * @param grid
     * @return
     */
    public int maxDistance(int[][] grid) {
        int max = 0;
        int rows = grid.length;
        int cols = grid[0].length;
        // 从左上开始
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 1) {
                    continue;
                }
                if (grid[i][j] == 0) {
                    grid[i][j] = rows + cols;
                }
                if (i > 0) {
                    grid[i][j] = Math.min(grid[i][j], grid[i - 1][j] + 1);
                }
                if (j > 0) {
                    grid[i][j] = Math.min(grid[i][j], grid[i][j - 1] + 1);
                }
            }
        }
        // 从右下开始
        for (int i = rows - 1; i >= 0; i--) {
            for (int j = cols - 1; j >= 0; j--) {
                if (grid[i][j] == 1) {
                    continue;
                }
                if (i < rows - 1) {
                    grid[i][j] = Math.min(grid[i][j], grid[i + 1][j] + 1);
                }
                if (j < cols - 1) {
                    grid[i][j] = Math.min(grid[i][j], grid[i][j + 1] + 1);
                }
                if (grid[i][j] > max) {
                    max = grid[i][j];
                }
            }
        }
        logResult(grid);
        return max - 1;
        /* // 方向向量
        int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

        // 由于题目中给出了 grid 的范围，因此不用做输入检查
        int N = grid.length;

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (grid[i][j] == 1) {
                    queue.add(getIndex(i, j, N));
                }
            }
        }

        int size = queue.size();
        if (size == 0 || size == N * N) {
            return -1;
        }

        int step = 0;
        while (!queue.isEmpty()) {

            int currentQueueSize = queue.size();
            for (int i = 0; i < currentQueueSize; i++) {
                Integer head = queue.poll();

                int currentX = head / N;
                int currentY = head % N;

                for (int[] direction : directions) {
                    int newX = currentX + direction[0];
                    int newY = currentY + direction[1];

                    // 只关心有效范围内的海洋（0）
                    if (inArea(newX, newY, N,N) && grid[newX][newY] == 0) {
                        // 赋值成为一个不等于 0 的整数均可，因为后续逻辑只关心海洋（0）
                        grid[newX][newY] = 1;
                        queue.add(getIndex(newX, newY, N));
                    }
                }
            }

            step++;
        }
        // 由于最后一步，没有可以扩散的的区域，但是 step 加了 1，故在退出循环的时候应该减 1
        return step - 1;*/
    }

    private int getIndex(int x, int y, int cols) {
        return x * cols + y;
    }

    private void maxDistance1(int[][] grid, int row, int col, int last, boolean flag) {
        int rows = grid.length;
        int cols = grid[0].length;
        if (!inArea(row, col, rows, cols)) {
            return;
        }
        int num = grid[row][col];
        if (num != 1 && flag) {
            grid[row][col] = last + 1;
            num = last + 1;
        }
        if (num == 1) {
            flag = true;
        }

        maxDistance1(grid, row + 1, col, num, flag);
        maxDistance1(grid, row, col + 1, num, flag);
    }

    private void maxDistance2(int[][] grid, int row, int col, int last, boolean flag) {
        int rows = grid.length;
        int cols = grid[0].length;
        if (!inArea(row, col, rows, cols)) {
            return;
        }
        int num = grid[row][col];
        if (num != 1 && flag) {
            grid[row][col] = last + 1;
            num = last + 1;
        }
        if (num == 1) {
            flag = true;
        }

        maxDistance2(grid, row - 1, col, num, false);
        maxDistance2(grid, row, col - 1, num, false);
    }

    @Test
    public void findLucky() {
        int[] arr = {1, 2, 2, 3, 3, 3};
        logResult(findLucky(arr));
    }

    /**
     * 5368. 找出数组中的幸运数 在整数数组中，如果一个整数的出现频次和它的数值大小相等，我们就称这个整数为「幸运数」。
     *
     * <p>给你一个整数数组 arr，请你从中找出并返回一个幸运数。
     *
     * <p>如果数组中存在多个幸运数，只需返回 最大 的那个。 如果数组中不含幸运数，则返回 -1 。
     *
     * <p>示例 1：
     *
     * <p>输入：arr = [2,2,3,4] 输出：2 解释：数组中唯一的幸运数是 2 ，因为数值 2 的出现频次也是 2 。 示例 2：
     *
     * <p>输入：arr = [1,2,2,3,3,3] 输出：3 解释：1、2 以及 3 都是幸运数，只需要返回其中最大的 3 。 示例 3：
     *
     * <p>输入：arr = [2,2,2,3,3] 输出：-1 解释：数组中不存在幸运数。 示例 4：
     *
     * <p>输入：arr = [5] 输出：-1 示例 5：
     *
     * <p>输入：arr = [7,7,7,7,7,7,7] 输出：7
     *
     * <p>提示：
     *
     * <p>1 <= arr.length <= 500 1 <= arr[i] <= 500
     *
     * @param arr
     * @return
     */
    public int findLucky(int[] arr) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : arr) {
            int count = map.getOrDefault(num, 0);
            map.put(num, count + 1);
        }
        final int[] result = {-1};
        map.forEach(
                (k, v) -> {
                    if (Objects.equals(k, v) && k > result[0]) {
                        result[0] = k;
                    }
                });
        return result[0];
    }

    /**
     * 492. 构造矩形 作为一位web开发者， 懂得怎样去规划一个页面的尺寸是很重要的。 现给定一个具体的矩形页面面积，你的任务是设计一个长度为 L 和宽度为 W
     * 且满足以下要求的矩形的页面。要求：
     *
     * <p>1. 你设计的矩形页面必须等于给定的目标面积。
     *
     * <p>2. 宽度 W 不应大于长度 L，换言之，要求 L >= W 。
     *
     * <p>3. 长度 L 和宽度 W 之间的差距应当尽可能小。 你需要按顺序输出你设计的页面的长度 L 和宽度 W。
     *
     * <p>示例：
     *
     * <p>输入: 4 输出: [2, 2] 解释: 目标面积是 4， 所有可能的构造方案有 [1,4], [2,2], [4,1]。 但是根据要求2，[1,4] 不符合要求;
     * 根据要求3，[2,2] 比 [4,1] 更能符合要求. 所以输出长度 L 为 2， 宽度 W 为 2。 说明:
     *
     * <p>给定的面积不大于 10,000,000 且为正整数。 你设计的页面的长度和宽度必须都是正整数。
     *
     * @param area
     * @return
     */
    public int[] constructRectangle(int area) {
        int num = (int) Math.sqrt(area);
        while (num >= 1) {
            if (area % num == 0) {
                break;
            }
            num--;
        }
        int w = num;
        int l = area / w;
        int[] result = new int[2];
        result[0] = l;
        result[1] = w;
        return result;
    }

    @Test
    public void nextGreaterElement() {
        int[] nums1 = {2, 4};
        int[] nums2 = {1, 2, 3, 4};
        int[] result = nextGreaterElement(nums1, nums2);
        log.debug("result:{}", result);
    }

    /**
     * 496. 下一个更大元素 I 给定两个没有重复元素的数组 nums1 和 nums2 ，其中nums1 是 nums2 的子集。找到 nums1 中每个元素在 nums2
     * 中的下一个比其大的值。
     *
     * <p>nums1 中数字 x 的下一个更大元素是指 x 在 nums2 中对应位置的右边的第一个比 x 大的元素。如果不存在，对应位置输出-1。
     *
     * <p>示例 1:
     *
     * <p>输入: nums1 = [4,1,2], nums2 = [1,3,4,2]. 输出: [-1,3,-1] 解释:
     * 对于num1中的数字4，你无法在第二个数组中找到下一个更大的数字，因此输出 -1。 对于num1中的数字1，第二个数组中数字1右边的下一个较大数字是 3。
     * 对于num1中的数字2，第二个数组中没有下一个更大的数字，因此输出 -1。 示例 2:
     *
     * <p>输入: nums1 = [2,4], nums2 = [1,2,3,4]. 输出: [3,-1] 解释: 对于num1中的数字2，第二个数组中的下一个较大数字是3。
     * 对于num1中的数字4，第二个数组中没有下一个更大的数字，因此输出 -1。 注意:
     *
     * <p>nums1和nums2中所有元素是唯一的。 nums1和nums2 的数组大小都不超过1000。
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int len = nums1.length;
        int[] result = new int[len];
        Map<Integer, Integer> map = new HashMap<>();
        Deque<Integer> stack = new LinkedList<>();
        for (int num : nums2) {
            while (!stack.isEmpty() && num > stack.peekLast()) {
                map.put(stack.pollLast(), num);
            }
            stack.addLast(num);
        }
        while (!stack.isEmpty()) {
            map.put(stack.pollLast(), -1);
        }
        for (int i = 0; i < len; i++) {
            result[i] = map.get(nums1[i]);
        }
        return result;
    }

    /**
     * 506. 相对名次 给出 N 名运动员的成绩，找出他们的相对名次并授予前三名对应的奖牌。 前三名运动员将会被分别授予 “金牌”，“银牌” 和“ 铜牌”（"Gold Medal",
     * "Silver Medal", "Bronze Medal"）。
     *
     * <p>(注：分数越高的选手，排名越靠前。)
     *
     * <p>示例 1:
     *
     * <p>输入: [5, 4, 3, 2, 1] 输出: ["Gold Medal", "Silver Medal", "Bronze Medal", "4", "5"] 解释:
     * 前三名运动员的成绩为前三高的，因此将会分别被授予 “金牌”，“银牌”和“铜牌” ("Gold Medal", "Silver Medal" and "Bronze Medal").
     * 余下的两名运动员，我们只需要通过他们的成绩计算将其相对名次即可。 提示:
     *
     * <p>N 是一个正整数并且不会超过 10000。 所有运动员的成绩都不相同。
     *
     * @param nums
     * @return
     */
    public String[] findRelativeRanks(int[] nums) {
        int len = nums.length;
        String[] result = new String[len];
        /*int max = nums[0],min = nums[0];
        for (int i = 1; i < len; i++) {
            if (nums[i] > max) {
                max = nums[i];
            }
            if (nums[i] < min) {
                min = nums[i];
            }
        }

        int[] counts = new int[max - min + 1];
        for (int num : nums) {
            counts[num - min]++;
        }*/
        int[] newNums = new int[len];
        System.arraycopy(nums, 0, newNums, 0, len);

        Arrays.sort(newNums);
        Map<Integer, String> map = new HashMap<>();
        int index = 0;
        for (int i = len - 1; i >= 0; i--) {
            index++;
            String str;
            switch (index) {
                case 1:
                    str = "Gold Medal";
                    break;
                case 2:
                    str = "Silver Medal";
                    break;
                case 3:
                    str = "Bronze Medal";
                    break;
                default:
                    str = String.valueOf(index);
            }
            map.put(newNums[i], str);
        }

        for (int i = 0; i < len; i++) {
            int num = nums[i];
            result[i] = map.get(num);
        }
        return result;
    }

    @Test
    public void findPairs() {
        int[] nums = {1, 3, 1, 5, 4};
        int k = 0;
        logResult(findPairs(nums, k));
    }

    /**
     * 532. 数组中的K-diff数对 给定一个整数数组和一个整数 k, 你需要在数组里找到不同的 k-diff 数对。这里将 k-diff 数对定义为一个整数对 (i, j), 其中 i
     * 和 j 都是数组中的数字，且两数之差的绝对值是 k.
     *
     * <p>示例 1:
     *
     * <p>输入: [3, 1, 4, 1, 5], k = 2 输出: 2 解释: 数组中有两个 2-diff 数对, (1, 3) 和 (3, 5)。
     * 尽管数组中有两个1，但我们只应返回不同的数对的数量。 示例 2:
     *
     * <p>输入:[1, 2, 3, 4, 5], k = 1 输出: 4 解释: 数组中有四个 1-diff 数对, (1, 2), (2, 3), (3, 4) 和 (4, 5)。 示例
     * 3:
     *
     * <p>输入: [1, 3, 1, 5, 4], k = 0 输出: 1 解释: 数组中只有一个 0-diff 数对，(1, 1)。 注意:
     *
     * <p>数对 (i, j) 和数对 (j, i) 被算作同一数对。 数组的长度不超过10,000。 所有输入的整数的范围在 [-1e7, 1e7]。
     *
     * @param nums
     * @param k
     * @return
     */
    public int findPairs(int[] nums, int k) {
        if (k < 0) {
            return 0;
        }
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            int count = map.getOrDefault(num, 0);
            map.put(num, count + 1);
        }
        int result = 0;
        if (k == 0) {
            for (Integer count : map.values()) {

                if (count > 1) {
                    result += 1;
                }
            }
        } else {
            for (Integer key : map.keySet()) {
                int count2 = map.getOrDefault(key + k, 0);
                if (count2 > 0) {
                    result += 1;
                }
            }
        }
        return result;
    }

    @Test
    public void sortArray() {
        int[] nums = {-4, 0, 7, 4, 9, -5, -1, 0, -7, -1};
        sortArray(nums);
        log.debug("nums:{}", nums);
        Arrays.sort(nums);
        log.debug("nums:{}", nums);
    }

    /**
     * 912. 排序数组 给定一个整数数组 nums，将该数组升序排列。
     *
     * <p>示例 1：
     *
     * <p>输入：[5,2,3,1] 输出：[1,2,3,5] 示例 2：
     *
     * <p>输入：[5,1,1,2,0,0] 输出：[0,0,1,1,2,5]
     *
     * <p>提示：
     *
     * <p>1 <= A.length <= 10000 -50000 <= A[i] <= 50000
     *
     * @param nums
     * @return
     */
    public int[] sortArray(int[] nums) {
        shellSort(nums);
        return nums;
    }

    /**
     * 希尔排序
     *
     * @param nums
     */
    private void shellSort(int[] nums) {
        int len = nums.length;
        // 希尔排序，通过优化插入排序，让相距较远的元素优先做比较
        int shellNum = len / 2;
        while (shellNum >= 1) {
            for (int i = shellNum; i < len; i++) {
                int tmp = nums[i];
                int index = i;
                while (index >= shellNum && nums[index - shellNum] > tmp) {
                    nums[index] = nums[index - shellNum];
                    index -= shellNum;
                }
                nums[index] = tmp;
            }
            shellNum = shellNum / 2;
        }
    }

    /**
     * 堆排序
     *
     * @param nums
     */
    private void heapSort(int[] nums) {
        int len = nums.length;
        if (len == 1) {
            return;
        }
        for (int i = len; i > 1; i--) {
            if (i == 2) {
                if (nums[0] > nums[1]) {
                    swap(nums, 0, 1);
                }
                break;
            } else {
                createMaxHeap(nums, i);
                swap(nums, 0, i - 1);
            }
        }
    }

    /**
     * 构建最大堆
     *
     * @param nums
     * @param len
     */
    private void createMaxHeap(int[] nums, int len) {
        int heapHeight = (int) Math.floor(Math.log(len) / Math.log(2));
        // 0 -> 0
        // 1 -> 1, 2 2 -
        // 2 的heapHeight次幂 - 1
        int heapLen = (1 << heapHeight) - 1;
        // 构建最大堆
        for (int i = heapLen; i >= 0; i--) {
            int left = 2 * i + 1;
            int right = 2 * i + 2;
            if (left < len && nums[left] > nums[i]) {
                swap(nums, i, left);
            }
            if (right < len && nums[right] > nums[i]) {
                swap(nums, i, right);
            }
        }
    }

    /**
     * 566. 重塑矩阵 在MATLAB中，有一个非常有用的函数 reshape，它可以将一个矩阵重塑为另一个大小不同的新矩阵，但保留其原始数据。
     *
     * <p>给出一个由二维数组表示的矩阵，以及两个正整数r和c，分别表示想要的重构的矩阵的行数和列数。
     *
     * <p>重构后的矩阵需要将原始矩阵的所有元素以相同的行遍历顺序填充。
     *
     * <p>如果具有给定参数的reshape操作是可行且合理的，则输出新的重塑矩阵；否则，输出原始矩阵。
     *
     * <p>示例 1:
     *
     * <p>输入: nums = [[1,2], [3,4]] r = 1, c = 4 输出: [[1,2,3,4]] 解释: 行遍历nums的结果是 [1,2,3,4]。新的矩阵是 1 *
     * 4 矩阵, 用之前的元素值一行一行填充新矩阵。 示例 2:
     *
     * <p>输入: nums = [[1,2], [3,4]] r = 2, c = 4 输出: [[1,2], [3,4]] 解释: 没有办法将 2 * 2 矩阵转化为 2 * 4 矩阵。
     * 所以输出原矩阵。 注意：
     *
     * <p>给定矩阵的宽和高范围在 [1, 100]。 给定的 r 和 c 都是正数。
     *
     * @param nums
     * @param r
     * @param c
     * @return
     */
    public int[][] matrixReshape(int[][] nums, int r, int c) {
        int rows = nums.length;
        int cols = nums[0].length;
        if (rows * cols != r * c) {
            return nums;
        }
        int[][] result = new int[r][c];
        int row = 0, col = 0;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result[row][col++] = nums[i][j];
                if (col == c) {
                    col = 0;
                    row++;
                }
            }
        }

        return result;
    }

    @Test
    public void findUnsortedSubarray() {
        int[] nums = {2, 6, 4, 8, 10, 9, 15};
        logResult(findUnsortedSubarray(nums));
    }

    /**
     * 581. 最短无序连续子数组 给定一个整数数组，你需要寻找一个连续的子数组，如果对这个子数组进行升序排序，那么整个数组都会变为升序排序。
     *
     * <p>你找到的子数组应是最短的，请输出它的长度。
     *
     * <p>示例 1:
     *
     * <p>输入: [2, 6, 4, 8, 10, 9, 15] 输出: 5 解释: 你只需要对 [6, 4, 8, 10, 9] 进行升序排序，那么整个表都会变为升序排序。 说明 :
     *
     * <p>输入的数组长度范围在 [1, 10,000]。 输入的数组可能包含重复元素 ，所以升序的意思是<=。
     *
     * @param nums
     * @return
     */
    public int findUnsortedSubarray(int[] nums) {
        int count = 0;
        Deque<Integer> stack = new LinkedList<>();
        int len = nums.length;
        int min = len - 1, max = 0;
        for (int i = 0; i < len; i++) {
            int num = nums[i];
            while (!stack.isEmpty() && num < nums[stack.peekLast()]) {
                min = Math.min(min, stack.removeLast());
            }
            stack.addLast(i);
        }
        stack.clear();
        for (int i = len - 1; i >= 0; i--) {
            int num = nums[i];
            while (!stack.isEmpty() && num > nums[stack.peekLast()]) {
                max = Math.max(max, stack.removeLast());
            }
            stack.addLast(i);
        }

        return max > min ? max - min + 1 : 0;
    }

    @Test
    public void findMaxAverage() {
        int[] nums = {1, 12, -5, -6, 50, 3};
        int k = 4;
        logResult(findMaxAverage(nums, k));
    }

    /**
     * 643. 子数组最大平均数 I 给定 n 个整数，找出平均数最大且长度为 k 的连续子数组，并输出该最大平均数。
     *
     * <p>示例 1:
     *
     * <p>输入: [1,12,-5,-6,50,3], k = 4 输出: 12.75 解释: 最大平均数 (12-5-6+50)/4 = 51/4 = 12.75
     *
     * <p>注意:
     *
     * <p>1 <= k <= n <= 30,000。 所给数据范围 [-10,000，10,000]。
     *
     * @param nums
     * @param k
     * @return
     */
    public double findMaxAverage(int[] nums, int k) {
        int len = nums.length;
        double sum = 0;

        for (int i = 0; i < k; i++) {
            sum += nums[i];
        }
        double max = sum;
        for (int i = k; i < len; i++) {
            sum += nums[i] - nums[i - k];
            if (sum > max) {
                max = sum;
            }
        }

        return max / (double) k;
    }

    @Test
    public void findErrorNums() {
        int[] nums = {1, 2, 2, 4};
        int[] result = findErrorNums(nums);
        log.debug("result:{}", result);
    }

    /**
     * 645. 错误的集合 集合 S 包含从1到 n 的整数。不幸的是，因为数据错误，导致集合里面某一个元素复制了成了集合里面的另外一个元素的值， 导致集合丢失了一个整数并且有一个元素重复。
     *
     * <p>给定一个数组 nums 代表了集合 S 发生错误后的结果。你的任务是首先寻找到重复出现的整数，再找到丢失的整数，将它们以数组的形式返回。
     *
     * <p>示例 1:
     *
     * <p>输入: nums = [1,2,2,4] 输出: [2,3] 注意:
     *
     * <p>给定数组的长度范围是 [2, 10000]。 给定的数组是无序的。
     *
     * @param nums
     * @return
     */
    public int[] findErrorNums(int[] nums) {
        int len = nums.length;
        int[] counts = new int[len];

        for (int num : nums) {
            counts[num - 1]++;
        }
        int[] result = new int[2];
        for (int i = 0; i < len; i++) {
            if (counts[i] == 0) {
                result[1] = i + 1;
            }
            if (counts[i] > 1) {
                result[0] = i + 1;
            }
        }

        return result;
    }

    @Test
    public void imageSmoother() {
        int[][] nums = {{2, 3, 4}, {5, 6, 7}, {8, 9, 10}, {11, 12, 13}, {14, 15, 16}};
        logResult(imageSmoother(nums));
    }

    /**
     * 661. 图片平滑器 包含整数的二维矩阵 M 表示一个图片的灰度。你需要设计一个平滑器来让每一个单元的灰度成为平均灰度 (向下舍入) ，
     * 平均灰度的计算是周围的8个单元和它本身的值求平均，如果周围的单元格不足八个，则尽可能多的利用它们。
     *
     * <p>示例 1:
     *
     * <p>输入: [[1,1,1], [1,0,1], [1,1,1]] 输出: [[0, 0, 0], [0, 0, 0], [0, 0, 0]] 解释: 对于点 (0,0),
     * (0,2), (2,0), (2,2): 平均(3/4) = 平均(0.75) = 0 对于点 (0,1), (1,0), (1,2), (2,1): 平均(5/6) =
     * 平均(0.83333333) = 0 对于点 (1,1): 平均(8/9) = 平均(0.88888889) = 0 注意:
     *
     * <p>给定矩阵中的整数范围为 [0, 255]。 矩阵的长和宽的范围均为 [1, 150]。
     *
     * @param M
     * @return
     */
    public int[][] imageSmoother(int[][] M) {
        int rows = M.length;
        int cols = M[0].length;
        int[][] result = new int[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int count = 1, sum = M[i][j];
                boolean left = false, right = false, up = false, down = false;
                if (i - 1 >= 0) {
                    count++;
                    up = true;
                    sum += M[i - 1][j];
                }
                if (j - 1 >= 0) {
                    count++;
                    left = true;
                    sum += M[i][j - 1];
                }
                if (i + 1 < rows) {
                    count++;
                    down = true;
                    sum += M[i + 1][j];
                }
                if (j + 1 < cols) {
                    count++;
                    right = true;
                    sum += M[i][j + 1];
                }

                if (left && up) {
                    count++;
                    sum += M[i - 1][j - 1];
                }
                if (left && down) {
                    count++;
                    sum += M[i + 1][j - 1];
                }
                if (right && up) {
                    count++;
                    sum += M[i - 1][j + 1];
                }
                if (right && down) {
                    count++;
                    sum += M[i + 1][j + 1];
                }
                log.debug("i:{},j:{},sum:{},count:{}", i, j, sum, count);

                result[i][j] = sum / count;
            }
        }

        return result;
    }

    @Test
    public void checkPossibility() {
        int[] nums = {1, 2, 6, 7, 3, 5};
        logResult(checkPossibility(nums));
    }

    /**
     * 665. 非递减数列 给你一个长度为 n 的整数数组，请你判断在 最多 改变 1 个元素的情况下，该数组能否变成一个非递减数列。
     *
     * <p>我们是这样定义一个非递减数列的： 对于数组中所有的 i (1 <= i < n)，总满足 array[i] <= array[i + 1]。
     *
     * <p>示例 1:
     *
     * <p>输入: nums = [4,2,3] 输出: true 解释: 你可以通过把第一个4变成1来使得它成为一个非递减数列。 示例 2:
     *
     * <p>输入: nums = [4,2,1] 输出: false 解释: 你不能在只改变一个元素的情况下将其变为非递减数列。
     *
     * <p>说明：
     *
     * <p>1 <= n <= 10 ^ 4 - 10 ^ 5 <= nums[i] <= 10 ^ 5
     *
     * @param nums
     * @return
     */
    public boolean checkPossibility(int[] nums) {
        int len = nums.length;
        if (len <= 2) {
            return true;
        }

        int count = 0;
        if (nums[0] > nums[1]) {
            count++;
        }
        int index = 1;
        for (int i = 2; i < len; i++) {

            if (nums[i] >= nums[index]) {
                index = i;
            } else if (nums[i] >= nums[index - 1]) {
                index = i;
            } else {
                count++;
            }
        }
        log.debug("count:{} ", count);

        return count <= 1;
    }

    @Test
    public void findShortestSubArray() {
        int[] nums = {1, 2, 2, 3, 1, 4, 2};
        logResult(findShortestSubArray(nums));
    }

    /**
     * 697. 数组的度 给定一个非空且只包含非负数的整数数组 nums, 数组的度的定义是指数组里任一元素出现频数的最大值。
     *
     * <p>你的任务是找到与 nums 拥有相同大小的度的最短连续子数组，返回其长度。
     *
     * <p>示例 1:
     *
     * <p>输入: [1, 2, 2, 3, 1] 输出: 2 解释: 输入数组的度是2，因为元素1和2的出现频数最大，均为2. 连续子数组里面拥有相同度的有如下所示: [1, 2, 2,
     * 3, 1], [1, 2, 2, 3], [2, 2, 3, 1], [1, 2, 2], [2, 2, 3], [2, 2] 最短连续子数组[2, 2]的长度为2，所以返回2. 示例
     * 2:
     *
     * <p>输入: [1,2,2,3,1,4,2] 输出: 6 注意:
     *
     * <p>nums.length 在1到50,000区间范围内。 nums[i] 是一个在0到49,999范围内的整数。
     *
     * @param nums
     * @return
     */
    public int findShortestSubArray(int[] nums) {
        int result = 0;
        if (nums.length == 1) {
            return 1;
        }
        Map<Integer, List<Integer>> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            List<Integer> list = map.getOrDefault(num, new ArrayList<>());
            list.add(i);
            map.put(num, list);
        }
        List<Integer> tmp = new ArrayList<>();
        int tmpLen = 0;
        for (List<Integer> list : map.values()) {
            if (list.size() > tmp.size()) {
                tmp = list;
                if (tmp.size() == 1) {
                    tmpLen = 1;
                } else {
                    int size = tmp.size();
                    tmpLen = tmp.get(size - 1) - tmp.get(0) + 1;
                }
            } else if (list.size() == tmp.size()) {
                int size = list.size();
                if (size >= 2) {
                    int listLen = list.get(size - 1) - list.get(0) + 1;
                    if (listLen < tmpLen) {
                        tmp = list;
                        tmpLen = listLen;
                    }
                }
            }
        }

        return tmpLen;
    }

    @Test
    public void minSubsequence() {
        int[] nums = {4, 4, 7, 6, 7};
        logResult(minSubsequence(nums));
    }
    /**
     * 5376. 非递增顺序的最小子序列 给你一个数组 nums，请你从中抽取一个子序列，满足该子序列的元素之和 严格 大于未包含在该子序列中的各元素之和。
     *
     * <p>如果存在多个解决方案，只需返回 长度最小 的子序列。如果仍然有多个解决方案，则返回 元素之和最大 的子序列。
     *
     * <p>与子数组不同的地方在于，「数组的子序列」不强调元素在原数组中的连续性，也就是说，它可以通过从数组中分离一些（也可能不分离）元素得到。
     *
     * <p>注意，题目数据保证满足所有约束条件的解决方案是 唯一 的。同时，返回的答案应当按 非递增顺序 排列。
     *
     * <p>示例 1：
     *
     * <p>输入：nums = [4,3,10,9,8] 输出：[10,9] 解释：子序列 [10,9] 和 [10,8] 是最小的、满足元素之和大于其他各元素之和的子序列。但是 [10,9]
     * 的元素之和最大。 示例 2：
     *
     * <p>输入：nums = [4,4,7,6,7] 输出：[7,7,6] 解释：子序列 [7,7] 的和为 14 ，不严格大于剩下的其他元素之和（14 = 4 + 4 +
     * 6）。因此，[7,6,7] 是满足题意的最小子序列。 注意，元素按非递增顺序返回。 示例 3：
     *
     * <p>输入：nums = [6] 输出：[6]
     *
     * <p>提示：
     *
     * <p>1 <= nums.length <= 500 1 <= nums[i] <= 100
     *
     * @param nums
     * @return
     */
    public List<Integer> minSubsequence(int[] nums) {
        List<Integer> list = new ArrayList<>();
        Arrays.sort(nums);
        int len = nums.length;
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        int minSum = 0;
        for (int i = len - 1; i >= 0; i--) {
            int num = nums[i];
            minSum += num;
            list.add(num);
            if (minSum > sum - minSum) {
                break;
            }
        }

        return list;
    }

    /**
     * 1051. 高度检查器 学校在拍年度纪念照时，一般要求学生按照 非递减 的高度顺序排列。
     *
     * <p>请你返回能让所有学生以 非递减 高度排列的最小必要移动人数。
     *
     * <p>注意，当一组学生被选中时，他们之间可以以任何可能的方式重新排序，而未被选中的学生应该保持不动。
     *
     * <p>示例：
     *
     * <p>输入：heights = [1,1,4,2,1,3] 输出：3 解释： 当前数组：[1,1,4,2,1,3] 目标数组：[1,1,1,2,3,4] 在下标 2 处（从 0
     * 开始计数）出现 4 vs 1 ，所以我们必须移动这名学生。 在下标 4 处（从 0 开始计数）出现 1 vs 3 ，所以我们必须移动这名学生。 在下标 5 处（从 0 开始计数）出现 3
     * vs 4 ，所以我们必须移动这名学生。 示例 2：
     *
     * <p>输入：heights = [5,1,2,3,4] 输出：5 示例 3：
     *
     * <p>输入：heights = [1,2,3,4,5] 输出：0
     *
     * <p>提示：
     *
     * <p>1 <= heights.length <= 100 1 <= heights[i] <= 100
     *
     * @param heights
     * @return
     */
    public int heightChecker(int[] heights) {
        int[] bucket = new int[101];

        for (int height : heights) {
            bucket[height]++;
        }

        int count = 0;
        for (int i = 1, j = 0; i < bucket.length; i++) {
            while (bucket[i]-- > 0) {
                if (heights[j++] != i) count++;
            }
        }
        return count;
    }

    @Test
    public void rotate2() {
        int[][] matrix = {{5, 1, 9, 11}, {2, 4, 8, 10}, {13, 3, 6, 7}, {15, 14, 12, 16}};
        rotate2(matrix);
        logResult(matrix);
    }

    /**
     * 面试题 01.07. 旋转矩阵
     *
     * <p>给你一幅由 N × N 矩阵表示的图像，其中每个像素的大小为 4 字节。请你设计一种算法，将图像旋转 90 度。
     *
     * <p>不占用额外内存空间能否做到？
     *
     * <p>示例 1:
     *
     * <p>给定 matrix = [ [1,2,3], [4,5,6], [7,8,9] ],
     *
     * <p>原地旋转输入矩阵，使其变为: [ [7,4,1], [8,5,2], [9,6,3] ] 示例 2:
     *
     * <p>给定 matrix = [ [ 5, 1, 9,11], [ 2, 4, 8,10], [13, 3, 6, 7], [15,14,12,16] ],
     *
     * <p>原地旋转输入矩阵，使其变为: [ [15,13, 2, 5], [14, 3, 4, 1], [12, 6, 8, 9], [16, 7,10,11] ]
     *
     * @param matrix
     */
    public void rotate2(int[][] matrix) {
        int len = matrix.length;

        for (int i = 0; i < (len >> 1); i++) {
            int rowLen = len - 1;
            for (int j = i; j < rowLen - i; j++) {
                // 旋转4次
                int tmp = matrix[i][j];
                // 左上
                matrix[i][j] = matrix[rowLen - j][i];
                // 左下
                matrix[rowLen - j][i] = matrix[rowLen - i][rowLen - j];
                // 右下
                matrix[rowLen - i][rowLen - j] = matrix[j][rowLen - i];
                // 右上
                matrix[j][rowLen - i] = tmp;
            }
        }
    }

    @Test
    public void largeGroupPositions() {
        String s = "abcdddeeeeaabbbcd";
        List<List<Integer>> result = largeGroupPositions(s);
        logResult(result);
    }

    /**
     * 830. 较大分组的位置 在一个由小写字母构成的字符串 S 中，包含由一些连续的相同字符所构成的分组。
     *
     * <p>例如，在字符串 S = "abbxxxxzyy" 中，就含有 "a", "bb", "xxxx", "z" 和 "yy" 这样的一些分组。
     *
     * <p>我们称所有包含大于或等于三个连续字符的分组为较大分组。找到每一个较大分组的起始和终止位置。
     *
     * <p>最终结果按照字典顺序输出。
     *
     * <p>示例 1:
     *
     * <p>输入: "abbxxxxzzy" 输出: [[3,6]] 解释: "xxxx" 是一个起始于 3 且终止于 6 的较大分组。 示例 2:
     *
     * <p>输入: "abc" 输出: [] 解释: "a","b" 和 "c" 均不是符合要求的较大分组。 示例 3:
     *
     * <p>输入: "abcdddeeeeaabbbcd" 输出: [[3,5],[6,9],[12,14]] 说明: 1 <= S.length <= 1000
     *
     * @param S
     * @return
     */
    public List<List<Integer>> largeGroupPositions(String S) {
        List<List<Integer>> result = new ArrayList<>();
        // 我们称所有包含大于或等于三个连续字符的分组为较大分组
        int len = S.length();
        char[] chars = S.toCharArray();
        int startIndex = 0;
        for (int i = 1; i <= len; i++) {
            if (i == len || chars[i - 1] != chars[i]) {
                if (i - startIndex >= 3) {
                    List<Integer> list = new ArrayList<>();
                    list.add(startIndex);
                    list.add(i - 1);
                    result.add(list);
                }
                startIndex = i;
            } else {

            }
        }

        return result;
    }

    @Test
    public void flipAndInvertImage() {
        int[][] A = {{1, 1, 0, 0}, {1, 0, 0, 1}, {0, 1, 1, 1}, {1, 0, 1, 0}};
        logResult(flipAndInvertImage(A));
    }
    /**
     * 832. 翻转图像 给定一个二进制矩阵 A，我们想先水平翻转图像，然后反转图像并返回结果。
     *
     * <p>水平翻转图片就是将图片的每一行都进行翻转，即逆序。例如，水平翻转 [1, 1, 0] 的结果是 [0, 1, 1]。
     *
     * <p>反转图片的意思是图片中的 0 全部被 1 替换， 1 全部被 0 替换。例如，反转 [0, 1, 1] 的结果是 [1, 0, 0]。
     *
     * <p>示例 1:
     *
     * <p>输入: [[1,1,0],[1,0,1],[0,0,0]] 输出: [[1,0,0],[0,1,0],[1,1,1]] 解释: 首先翻转每一行:
     * [[0,1,1],[1,0,1],[0,0,0]]； 然后反转图片: [[1,0,0],[0,1,0],[1,1,1]] 示例 2:
     *
     * <p>输入: [[1,1,0,0],[1,0,0,1],[0,1,1,1],[1,0,1,0]] 输出:
     * [[1,1,0,0],[0,1,1,0],[0,0,0,1],[1,0,1,0]] 解释: 首先翻转每一行:
     * [[0,0,1,1],[1,0,0,1],[1,1,1,0],[0,1,0,1]]； 然后反转图片: [[1,1,0,0],[0,1,1,0],[0,0,0,1],[1,0,1,0]]
     * 说明:
     *
     * <p>1 <= A.length = A[0].length <= 20 0 <= A[i][j] <= 1
     *
     * @param A
     * @return
     */
    public int[][] flipAndInvertImage(int[][] A) {
        int rows = A.length;
        int cols = A[0].length;

        int[][] result = new int[rows][cols];

        for (int i = 0; i < rows; i++) {

            for (int j = 0; j < cols; j++) {
                result[i][j] = 1 - A[i][cols - j - 1];
            }
        }
        return result;
    }

    @Test
    public void numMagicSquaresInside() {
        // int[][] grid = {{4,3,8,4},{9,5,1,9},{2,7,6,2}};
        // int[][] grid = {{2,7,6},{1,5,9},{4,3,8}};
        int[][] grid = {
            {3, 2, 9, 2, 7}, {6, 1, 8, 4, 2}, {7, 5, 3, 2, 7}, {2, 9, 4, 9, 6}, {4, 3, 8, 2, 5}
        };
        logResult(numMagicSquaresInside(grid));
    }

    /**
     * 840. 矩阵中的幻方 3 x 3 的幻方是一个填充有从 1 到 9 的不同数字的 3 x 3 矩阵，其中每行，每列以及两条对角线上的各数之和都相等。
     *
     * <p>给定一个由整数组成的 grid，其中有多少个 3 × 3 的 “幻方” 子矩阵？（每个子矩阵都是连续的）。
     *
     * <p>示例：
     *
     * <p>输入: [[4,3,8,4], [9,5,1,9], [2,7,6,2]] 输出: 1 解释: 下面的子矩阵是一个 3 x 3 的幻方： 438 951 276
     *
     * <p>而这一个不是： 384 519 762
     *
     * <p>总的来说，在本示例所给定的矩阵中只有一个 3 x 3 的幻方子矩阵。 提示:
     *
     * <p>1 <= grid.length <= 10 1 <= grid[0].length <= 10 0 <= grid[i][j] <= 15
     *
     * @param grid
     * @return
     */
    public int numMagicSquaresInside(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;

        int[] magicNums = new int[9];
        int result = 0;
        for (int i = 0; i <= rows - 3; i++) {

            for (int j = 0; j <= cols - 3; j++) {
                if (grid[i + 1][j + 1] != 5) {
                    continue;
                }
                boolean flag = true;

                Arrays.fill(magicNums, 1);

                // 行  列
                notMagic:
                for (int k = 0; k < 3; k++) {
                    int num1 = 0, num2 = 0;
                    for (int l = 0; l < 3; l++) {
                        int num = grid[i + k][j + l];
                        if (num < 1 || num > 9) {
                            flag = false;
                            continue notMagic;
                        }
                        if (--magicNums[num - 1] < 0) {
                            flag = false;
                            continue notMagic;
                        }

                        num1 += num;
                        num2 += grid[i + l][j + k];
                    }
                    if (num1 != 15 || num2 != 15) {
                        flag = false;
                        break;
                    }
                }

                if (!flag) {
                    continue;
                }

                // 对角线
                int num = grid[i][j] + grid[i + 1][j + 1] + grid[i + 2][j + 2];

                if (num != 15) {
                    continue;
                }

                num = grid[i][j + 2] + grid[i + 1][j + 1] + grid[i + 2][j];

                if (num != 15) {
                    continue;
                }
                if (flag) {
                    result++;
                }
            }
        }
        return result;
    }

    @Test
    public void lastStoneWeight() {
        int[] stones = {1, 2, 3, 4, 5, 6, 7};
        logResult(lastStoneWeight(stones));
    }

    /**
     * 1046. 最后一块石头的重量 有一堆石头，每块石头的重量都是正整数。
     *
     * <p>每一回合，从中选出两块最重的石头，然后将它们一起粉碎。假设石头的重量分别为 x 和 y，且 x <= y。那么粉碎的可能结果如下：
     *
     * <p>如果 x == y，那么两块石头都会被完全粉碎； 如果 x != y，那么重量为 x 的石头将会完全粉碎，而重量为 y 的石头新重量为 y-x。
     * 最后，最多只会剩下一块石头。返回此石头的重量。如果没有石头剩下，就返回 0。
     *
     * <p>提示：
     *
     * <p>1 <= stones.length <= 30 1 <= stones[i] <= 1000 通过次数12,420提交次数20,870
     *
     * @param stones
     * @return
     */
    public int lastStoneWeight(int[] stones) {
        int len = stones.length;
        // 构建最大堆
        PriorityQueue<Integer> heap = new PriorityQueue<>(len, (a, b) -> b - a);
        for (int weight : stones) {
            heap.add(weight);
        }
        while (heap.size() > 1) {
            log.debug("a:{}", heap);
            int y = heap.poll();
            int x = heap.poll();
            if (y - x != 0) {
                heap.add(y - x);
            }
        }
        if (heap.size() == 0) {
            return 0;
        }
        return heap.peek();
    }

    @Test
    public void movingCount() {
        int m = 2, n = 3, k = 1;
        logResult(movingCount(m, n, k));
    }

    /**
     * 面试题13. 机器人的运动范围 地上有一个m行n列的方格，从坐标 [0,0] 到坐标 [m-1,n-1] 。一个机器人从坐标 [0, 0] 的格子开始移动，
     * 它每次可以向左、右、上、下移动一格（不能移动到方格外），也不能进入行坐标和列坐标的数位之和大于k的格子。例如，当k为18时， 机器人能够进入方格 [35, 37]
     * ，因为3+5+3+7=18。但它不能进入方格 [35, 38]，因为3+5+3+8=19。请问该机器人能够到达多少个格子？
     *
     * <p>示例 1：
     *
     * <p>输入：m = 2, n = 3, k = 1 输出：3 示例 1：
     *
     * <p>输入：m = 3, n = 1, k = 0 输出：1 提示：
     *
     * <p>1 <= n,m <= 100 0 <= k <= 20
     *
     * @param m
     * @param n
     * @param k
     * @return
     */
    public int movingCount(int m, int n, int k) {
        M = m;
        N = n;
        Set<String> set = new HashSet<>();

        movingCount(0, 0, k, set);

        return set.size();
    }

    static int M, N;

    static int[] DIR_ROW = {1, 0, -1, 0};
    static int[] DIR_COL = {0, 1, 0, -1};

    private void movingCount(int rowIndex, int colIndex, int k, Set<String> set) {
        if (getNum(rowIndex) + getNum(colIndex) <= k) {
            set.add(rowIndex + "," + colIndex);
        } else {
            return;
        }
        for (int i = 0; i < 4; i++) {
            int row = rowIndex + DIR_ROW[i];
            int col = colIndex + DIR_COL[i];
            if (!inArea(row, col, M, N)) {
                continue;
            }
            String key = row + "," + col;
            if (set.contains(key)) {
                continue;
            }
            movingCount(row, col, k, set);
        }
    }

    private int getNum(int num) {
        int result = 0;
        while (num > 0) {
            result += num % 10;
            num /= 10;
        }
        return result;
    }

    @Test
    public void prefixesDivBy5() {
        // int[] nums = { 1,0,1,1,1,1,0,0,0,0,1,0,0,0,0,0,1,0,0,1,1,1,1,1,0,0,0,0,1,1,
        // 1,0,0,0,0,0,1,0,0,0,1,0,0,1,1,1,1,1,1,0,1,1,0,1,0,0,0,0,0,0,1,0,1,1,1,0,0,1,0};
        int[] nums = {0, 1, 1, 1, 1, 1};
        logResult(prefixesDivBy5(nums));
    }

    /**
     * 1018. 可被 5 整除的二进制前缀 给定由若干 0 和 1 组成的数组 A。我们定义 N_i：从 A[0] 到 A[i] 的第 i
     * 个子数组被解释为一个二进制数（从最高有效位到最低有效位）。
     *
     * <p>返回布尔值列表 answer，只有当 N_i 可以被 5 整除时，答案 answer[i] 为 true，否则为 false。
     *
     * <p>示例 1：
     *
     * <p>输入：[0,1,1] 输出：[true,false,false] 解释： 输入数字为 0, 01, 011；也就是十进制中的 0, 1, 3 。只有第一个数可以被 5 整除，因此
     * answer[0] 为真。 示例 2：
     *
     * <p>输入：[1,1,1] 输出：[false,false,false] 示例 3：
     *
     * <p>输入：[0,1,1,1,1,1] 输出：[true,false,false,false,true,false] 示例 4：
     *
     * <p>输入：[1,1,1,0,1] 输出：[false,false,false,false,false]
     *
     * <p>提示：
     *
     * <p>1 <= A.length <= 30000 A[i] 为 0 或 1
     *
     * @param A
     * @return
     */
    public List<Boolean> prefixesDivBy5(int[] A) {
        List<Boolean> list = new ArrayList<>();
        int num = 0;
        for (int a : A) {
            num += 2 * num + a;
            if (num >= 10) {
                num = num % 10;
            }
            if (num == 0 || num == 5) {
                list.add(true);
                continue;
            }
            list.add(false);
        }

        return list;
    }

    /**
     * 985. 查询后的偶数和 给出一个整数数组 A 和一个查询数组 queries。
     *
     * <p>对于第 i 次查询，有 val = queries[i][0], index = queries[i][1]， 我们会把 val 加到 A[index] 上。然后，第 i
     * 次查询的答案是 A 中偶数值的和。
     *
     * <p>（此处给定的 index = queries[i][1] 是从 0 开始的索引，每次查询都会永久修改数组 A。）
     *
     * <p>返回所有查询的答案。你的答案应当以数组 answer 给出，answer[i] 为第 i 次查询的答案。
     *
     * <p>示例：
     *
     * <p>输入：A = [1,2,3,4], queries = [[1,0],[-3,1],[-4,0],[2,3]] 输出：[8,6,2,4] 解释： 开始时，数组为
     * [1,2,3,4]。 将 1 加到 A[0] 上之后，数组为 [2,2,3,4]，偶数值之和为 2 + 2 + 4 = 8。 将 -3 加到 A[1] 上之后，数组为
     * [2,-1,3,4]，偶数值之和为 2 + 4 = 6。 将 -4 加到 A[0] 上之后，数组为 [-2,-1,3,4]，偶数值之和为 -2 + 4 = 2。 将 2 加到 A[3]
     * 上之后，数组为 [-2,-1,3,6]，偶数值之和为 -2 + 6 = 4。
     *
     * <p>提示：
     *
     * <p>1 <= A.length <= 10000 -10000 <= A[i] <= 10000 1 <= queries.length <= 10000 -10000 <=
     * queries[i][0] <= 10000 0 <= queries[i][1] < A.length
     *
     * @param A
     * @param queries
     * @return
     */
    public int[] sumEvenAfterQueries(int[] A, int[][] queries) {
        int len = A.length;
        int[] result = new int[len];
        int evenNum = 0;
        for (int a : A) {
            if ((a & 1) == 0) {
                evenNum += a;
            }
        }

        for (int i = 0; i < len; i++) {
            int val = queries[i][0];
            int index = queries[i][1];

            // 如果原来是偶数
            if ((A[index] & 1) == 0) {
                evenNum -= A[index];
            }
            A[index] += val;
            if ((A[index] & 1) == 0) {
                evenNum += A[index];
            }
            /*int sum = 0;

            for (int num : A) {
                if ((num & 1) == 0) {
                    sum += num;
                }
            }*/
            result[i] = evenNum;
        }

        return result;
    }

    @Test
    public void addToArrayForm() {
        int[] A = {1};
        int K = 99;
        logResult(addToArrayForm(A, K));
    }
    /**
     * 989. 数组形式的整数加法 对于非负整数 X 而言，X 的数组形式是每位数字按从左到右的顺序形成的数组。例如，如果 X = 1231，那么其数组形式为 [1,2,3,1]。
     *
     * <p>给定非负整数 X 的数组形式 A，返回整数 X+K 的数组形式。
     *
     * <p>示例 1：
     *
     * <p>输入：A = [1,2,0,0], K = 34 输出：[1,2,3,4] 解释：1200 + 34 = 1234 示例 2：
     *
     * <p>输入：A = [2,7,4], K = 181 输出：[4,5,5] 解释：274 + 181 = 455 示例 3：
     *
     * <p>输入：A = [2,1,5], K = 806 输出：[1,0,2,1] 解释：215 + 806 = 1021 示例 4：
     *
     * <p>输入：A = [9,9,9,9,9,9,9,9,9,9], K = 1 输出：[1,0,0,0,0,0,0,0,0,0,0] 解释：9999999999 + 1 =
     * 10000000000
     *
     * <p>提示：
     *
     * <p>1 <= A.length <= 10000 0 <= A[i] <= 9 0 <= K <= 10000 如果 A.length > 1，那么 A[0] != 0
     *
     * @param A
     * @param K
     * @return
     */
    public List<Integer> addToArrayForm(int[] A, int K) {
        List<Integer> list = new ArrayList<>();
        int len = A.length;
        int i = len - 1;
        while (i >= 0 || K > 0) {
            if (i >= 0) {
                K += A[i];
            }
            list.add(K % 10);
            K /= 10;
            i--;
        }

        Collections.reverse(list);

        return list;
    }

    /**
     * 1010. 总持续时间可被 60 整除的歌曲 在歌曲列表中，第 i 首歌曲的持续时间为 time[i] 秒。
     *
     * <p>返回其总持续时间（以秒为单位）可被 60 整除的歌曲对的数量。形式上，我们希望索引的数字 i < j 且有 (time[i] + time[j]) % 60 == 0。
     *
     * <p>示例 1：
     *
     * <p>输入：[30,20,150,100,40] 输出：3 解释：这三对的总持续时间可被 60 整数： (time[0] = 30, time[2] = 150): 总持续时间 180
     * (time[1] = 20, time[3] = 100): 总持续时间 120 (time[1] = 20, time[4] = 40): 总持续时间 60 示例 2：
     *
     * <p>输入：[60,60,60] 输出：3 解释：所有三对的总持续时间都是 120，可以被 60 整数。
     *
     * <p>提示：
     *
     * <p>1 <= time.length <= 60000 1 <= time[i] <= 5001010. 总持续时间可被 60 整除的歌曲 在歌曲列表中，第 i 首歌曲的持续时间为
     * time[i] 秒。
     *
     * <p>返回其总持续时间（以秒为单位）可被 60 整除的歌曲对的数量。形式上，我们希望索引的数字 i < j 且有 (time[i] + time[j]) % 60 == 0。
     *
     * <p>示例 1：
     *
     * <p>输入：[30,20,150,100,40] 输出：3 解释：这三对的总持续时间可被 60 整数： (time[0] = 30, time[2] = 150): 总持续时间 180
     * (time[1] = 20, time[3] = 100): 总持续时间 120 (time[1] = 20, time[4] = 40): 总持续时间 60 示例 2：
     *
     * <p>输入：[60,60,60] 输出：3 解释：所有三对的总持续时间都是 120，可以被 60 整数。
     *
     * <p>提示：
     *
     * <p>1 <= time.length <= 60000 1 <= time[i] <= 500
     *
     * @param time
     * @return
     */
    public int numPairsDivisibleBy60(int[] time) {
        int len = time.length;
        int result = 0;
        // 使用余数
        int[] nums = new int[60];
        for (int i = 0; i < len; i++) {
            int index = time[i] % 60;
            nums[index]++;
        }

        int left = 1, right = 59;
        while (left < right) {
            result += nums[left++] * nums[right--];
        }
        if (nums[0] > 1) {
            result += nums[0] * (nums[0] - 1) / 2;
        }
        if (nums[30] > 1) {
            result += nums[30] * (nums[30] - 1) / 2;
        }

        return result;
    }

    /**
     * 867. 转置矩阵 给定一个矩阵 A， 返回 A 的转置矩阵。
     *
     * <p>矩阵的转置是指将矩阵的主对角线翻转，交换矩阵的行索引与列索引。
     *
     * <p>示例 1：
     *
     * <p>输入：[[1,2,3],[4,5,6],[7,8,9]] 输出：[[1,4,7],[2,5,8],[3,6,9]] 示例 2：
     *
     * <p>输入：[[1,2,3],[4,5,6]] 输出：[[1,4],[2,5],[3,6]]
     *
     * <p>提示：
     *
     * <p>1 <= A.length <= 1000 1 <= A[0].length <= 1000
     *
     * @param A
     * @return
     */
    public int[][] transpose(int[][] A) {
        int rows = A.length;
        int cols = A[0].length;
        int[][] result = new int[cols][rows];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result[j][i] = A[i][j];
            }
        }
        return result;
    }

    /**
     * 977. 有序数组的平方 给定一个按非递减顺序排序的整数数组 A，返回每个数字的平方组成的新数组，要求也按非递减顺序排序。
     *
     * <p>示例 1：
     *
     * <p>输入：[-4,-1,0,3,10] 输出：[0,1,9,16,100] 示例 2：
     *
     * <p>输入：[-7,-3,2,3,11] 输出：[4,9,9,49,121]
     *
     * <p>提示：
     *
     * <p>1 <= A.length <= 10000 -10000 <= A[i] <= 10000 A 已按非递减顺序排序。
     *
     * @param A
     * @return
     */
    public int[] sortedSquares(int[] A) {
        int len = A.length;
        int left = 0, right = len - 1;
        int index = len - 1;
        int[] result = new int[len];
        while (left < right) {
            int leftNum = A[left] * A[left];
            int rightNum = A[right] * A[right];
            if (leftNum <= rightNum) {
                result[index--] = rightNum;
                right--;

            } else {
                result[index--] = leftNum;
                left++;
            }
        }
        result[index] = A[left] * A[left];
        return result;
    }

    /**
     * 905. 按奇偶排序数组 给定一个非负整数数组 A，返回一个数组，在该数组中， A 的所有偶数元素之后跟着所有奇数元素。
     *
     * <p>你可以返回满足此条件的任何数组作为答案。
     *
     * <p>示例：
     *
     * <p>输入：[3,1,2,4] 输出：[2,4,3,1] 输出 [4,2,3,1]，[2,4,1,3] 和 [4,2,1,3] 也会被接受。
     *
     * <p>提示：
     *
     * <p>1 <= A.length <= 5000 0 <= A[i] <= 5000
     *
     * @param A
     * @return
     */
    public int[] sortArrayByParity(int[] A) {
        int len = A.length;
        int left = 0, right = len - 1;
        while (left < right) {
            // 找到左边的奇数
            if ((A[left] & 1) == 0) {
                left++;
                continue;
            }
            // 找到右边边的奇数
            if ((A[right] & 1) == 1) {
                right--;
                continue;
            }

            int tmp = A[left];
            A[left++] = A[right];
            A[right--] = tmp;
        }

        return A;
    }

    /**
     * 922. 按奇偶排序数组 II 给定一个非负整数数组 A， A 中一半整数是奇数，一半整数是偶数。
     *
     * <p>对数组进行排序，以便当 A[i] 为奇数时，i 也是奇数；当 A[i] 为偶数时， i 也是偶数。
     *
     * <p>你可以返回任何满足上述条件的数组作为答案。
     *
     * <p>示例：
     *
     * <p>输入：[4,2,5,7] 输出：[4,5,2,7] 解释：[4,7,2,5]，[2,5,4,7]，[2,7,4,5] 也会被接受。
     *
     * <p>提示：
     *
     * <p>2 <= A.length <= 20000 A.length % 2 == 0 0 <= A[i] <= 1000
     *
     * @param A
     * @return
     */
    public int[] sortArrayByParityII(int[] A) {

        int len = A.length;
        int index = 0;
        int[] result = new int[len];
        for (int a : A) {
            if ((a & 1) == 0 && index < len) {
                result[index] = a;
                index += 2;
            }
        }
        index = 1;
        for (int a : A) {
            if ((a & 1) == 1 && index < len) {
                result[index] = a;
                index += 2;
            }
        }
        return result;
    }

    /**
     * * 896. 单调数列 如果数组是单调递增或单调递减的，那么它是单调的。
     *
     * <p>如果对于所有 i <= j，A[i] <= A[j]，那么数组 A 是单调递增的。 如果对于所有 i <= j，A[i]> = A[j]，那么数组 A 是单调递减的。
     *
     * <p>当给定的数组 A 是单调数组时返回 true，否则返回 false。
     *
     * <p>示例 1：
     *
     * <p>输入：[1,2,2,3] 输出：true 示例 2：
     *
     * <p>输入：[6,5,4,4] 输出：true 示例 3：
     *
     * <p>输入：[1,3,2] 输出：false 示例 4：
     *
     * <p>输入：[1,2,4,5] 输出：true 示例 5：
     *
     * <p>输入：[1,1,1] 输出：true
     *
     * <p>提示：
     *
     * <p>1 <= A.length <= 50000 -100000 <= A[i] <= 100000
     *
     * @param A
     * @return
     */
    public boolean isMonotonic(int[] A) {
        boolean isAdd = true;
        boolean isSub = true;
        if (A.length <= 2) {
            return true;
        }
        for (int i = 1; i < A.length; i++) {
            if (A[i - 1] < A[i]) {
                isSub = false;
                if (!isAdd) {
                    return false;
                }
            }
            if (A[i - 1] > A[i]) {
                isAdd = false;
                if (!isSub) {
                    return false;
                }
            }
        }
        return isAdd || isSub;
    }

    @Test
    public void isLongPressedName() {
        String name = "pyplrz", typed = "ppyypllr";
        logResult(isLongPressedName(name, typed));
    }

    /**
     * 925. 长按键入 你的朋友正在使用键盘输入他的名字 name。偶尔，在键入字符 c 时，按键可能会被长按，而字符可能被输入 1 次或多次。
     *
     * <p>你将会检查键盘输入的字符 typed。如果它对应的可能是你的朋友的名字（其中一些字符可能被长按），那么就返回 True。
     *
     * <p>示例 1：
     *
     * <p>输入：name = "alex", typed = "aaleex" 输出：true 解释：'alex' 中的 'a' 和 'e' 被长按。 示例 2：
     *
     * <p>输入：name = "saeed", typed = "ssaaedd" 输出：false 解释：'e' 一定需要被键入两次，但在 typed 的输出中不是这样。 示例 3：
     *
     * <p>输入：name = "leelee", typed = "lleeelee" 输出：true 示例 4：
     *
     * <p>输入：name = "laiden", typed = "laiden" 输出：true 解释：长按名字中的字符并不是必要的。
     *
     * <p>提示：
     *
     * <p>name.length <= 1000 typed.length <= 1000 name 和 typed 的字符都是小写字母。
     *
     * @param name
     * @param typed
     * @return
     */
    public boolean isLongPressedName(String name, String typed) {
        int len1 = name.length();
        int len2 = typed.length();
        int index = 0;
        for (int i = 0; i < len2; i++) {
            char c = typed.charAt(i);
            if (index < len1 && c == name.charAt(index)) {
                index++;
            } else if (index - 1 >= 0 && c == name.charAt(index - 1)) {
            } else {
                return false;
            }
        }
        if (index < len1) {
            return false;
        }
        return true;
    }

    /**
     * 941. 有效的山脉数组 给定一个整数数组 A，如果它是有效的山脉数组就返回 true，否则返回 false。
     *
     * <p>让我们回顾一下，如果 A 满足下述条件，那么它是一个山脉数组：
     *
     * <p>A.length >= 3 在 0 < i < A.length - 1 条件下，存在 i 使得： A[0] < A[1] < ... A[i-1] < A[i] A[i] >
     * A[i+1] > ... > A[A.length - 1]
     *
     * <p>示例 1：
     *
     * <p>输入：[2,1] 输出：false 示例 2：
     *
     * <p>输入：[3,5,5] 输出：false 示例 3：
     *
     * <p>输入：[0,3,2,1] 输出：true
     *
     * <p>提示：
     *
     * <p>0 <= A.length <= 10000 0 <= A[i] <= 10000
     *
     * @param A
     * @return
     */
    public boolean validMountainArray(int[] A) {
        int len = A.length;
        int left = 0, right = len - 1;
        if (len < 3) {
            return false;
        }

        for (int i = 1; i < len; i++) {
            if (A[i] > A[i - 1]) {
                left = i;
            } else {
                break;
            }
        }
        if (left == 0) {
            return false;
        }
        for (int i = len - 2; i >= 0; i--) {
            if (A[i] > A[i + 1]) {
                right = i;
            } else {
                break;
            }
        }
        if (right == len - 1) {
            return false;
        }

        return left == right;
    }

    /**
     * 888. 公平的糖果交换 爱丽丝和鲍勃有不同大小的糖果棒：A[i] 是爱丽丝拥有的第 i 块糖的大小，B[j] 是鲍勃拥有的第 j 块糖的大小。
     *
     * <p>因为他们是朋友，所以他们想交换一个糖果棒，这样交换后，他们都有相同的糖果总量。（一个人拥有的糖果总量是他们拥有的糖果棒大小的总和。）
     *
     * <p>返回一个整数数组 ans，其中 ans[0] 是爱丽丝必须交换的糖果棒的大小，ans[1] 是 Bob 必须交换的糖果棒的大小。
     *
     * <p>如果有多个答案，你可以返回其中任何一个。保证答案存在。
     *
     * <p>示例 1：
     *
     * <p>输入：A = [1,1], B = [2,2] 输出：[1,2] 示例 2：
     *
     * <p>输入：A = [1,2], B = [2,3] 输出：[1,2] 示例 3：
     *
     * <p>输入：A = [2], B = [1,3] 输出：[2,3] 示例 4：
     *
     * <p>输入：A = [1,2,5], B = [2,4] 输出：[5,4]
     *
     * <p>提示：
     *
     * <p>1 <= A.length <= 10000 1 <= B.length <= 10000 1 <= A[i] <= 100000 1 <= B[i] <= 100000
     * 保证爱丽丝与鲍勃的糖果总量不同。 答案肯定存在。
     *
     * @param A
     * @param B
     * @return
     */
    public int[] fairCandySwap(int[] A, int[] B) {
        int[] result = new int[2];
        int sumA = 0, sumB = 0;
        Set<Integer> set = new HashSet<>();
        for (int a : A) {
            sumA += a;
            set.add(a);
        }
        for (int b : B) {
            sumB += b;
        }
        // sumA - a + b = sumB - b + a
        // => 2 (a - b) = sumA - sumB;
        // => a - b = (sumA - sumB) / 2;
        // => a  = (sumA - sumB) / 2 + b;
        int sum = (sumA - sumB) / 2;
        for (int b : B) {
            if (set.contains(b + sum)) {
                result[0] = b + sum;
                result[1] = b;
                break;
            }
        }

        return result;
    }

    @Test
    public void allCellsDistOrder() {
        int R = 1, C = 2, r0 = 0, c0 = 0;
        logResult(allCellsDistOrder(R, C, r0, c0));
    }

    /**
     * 1030. 距离顺序排列矩阵单元格 给出 R 行 C 列的矩阵，其中的单元格的整数坐标为 (r, c)，满足 0 <= r < R 且 0 <= c < C。
     *
     * <p>另外，我们在该矩阵中给出了一个坐标为 (r0, c0) 的单元格。
     *
     * <p>返回矩阵中的所有单元格的坐标，并按到 (r0, c0) 的距离从最小到最大的顺序排，其中，两单元格(r1, c1) 和 (r2, c2) 之间的距离是曼哈顿距离，|r1 - r2|
     * + |c1 - c2|。（你可以按任何满足此条件的顺序返回答案。）
     *
     * <p>示例 1：
     *
     * <p>输入：R = 1, C = 2, r0 = 0, c0 = 0 输出：[[0,0],[0,1]] 解释：从 (r0, c0) 到其他单元格的距离为：[0,1] 示例 2：
     *
     * <p>输入：R = 2, C = 2, r0 = 0, c0 = 1 输出：[[0,1],[0,0],[1,1],[1,0]] 解释：从 (r0, c0)
     * 到其他单元格的距离为：[0,1,1,2] [[0,1],[1,1],[0,0],[1,0]] 也会被视作正确答案。 示例 3：
     *
     * <p>输入：R = 2, C = 3, r0 = 1, c0 = 2 输出：[[1,2],[0,2],[1,1],[0,1],[1,0],[0,0]] 解释：从 (r0, c0)
     * 到其他单元格的距离为：[0,1,1,2,2,3] 其他满足题目要求的答案也会被视为正确，例如 [[1,2],[1,1],[0,2],[1,0],[0,1],[0,0]]。
     *
     * <p>提示：
     *
     * <p>1 <= R <= 100 1 <= C <= 100 0 <= r0 < R 0 <= c0 < C
     *
     * @param R
     * @param C
     * @param r0
     * @param c0
     * @return
     */
    public int[][] allCellsDistOrder(int R, int C, int r0, int c0) {

        Set<String> visited = new HashSet<>();

        List<int[]> list = new ArrayList<>();
        // 用广度优先遍历 使用队列
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[] {r0, c0});
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] cell = queue.poll();
                int row = cell[0], col = cell[1];
                if (visited.contains(row + "-" + col)) {
                    continue;
                }
                visited.add(row + "-" + col);
                list.add(cell);
                for (int j = 0; j < 4; j++) {
                    int nextRow = row + DIR_ROW[j];
                    int nextCol = col + DIR_COL[j];

                    if (inArea(nextRow, nextCol, R, C)) {
                        queue.offer(new int[] {nextRow, nextCol});
                    }
                }
            }
        }

        int len = list.size();
        int[][] result = new int[len][2];
        for (int i = 0; i < len; i++) {
            result[i] = list.get(i);
        }

        return result;
    }

    public int[][] merge2(int[][] intervals) {
        List<int[]> list = new ArrayList<>();

        Arrays.sort(intervals, (a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);

        for (int[] interval : intervals) {
            if (list.isEmpty()) {
                list.add(interval);
            } else {
                int size = list.size();
                int[] last = list.get(size - 1);
                if (last[1] < interval[0]) {
                    list.add(interval);
                } else if (interval[1] > last[1]) {
                    last[1] = interval[1];
                }
            }
        }
        int[][] result = new int[list.size()][2];
        for (int i = 0; i < list.size(); i++) {
            result[i] = list.get(i);
        }

        return result;
    }

    /**
     * 面试题 01.01. 判定字符是否唯一 实现一个算法，确定一个字符串 s 的所有字符是否全都不同。
     *
     * <p>示例 1：
     *
     * <p>输入: s = "leetcode" 输出: false 示例 2：
     *
     * <p>输入: s = "abc" 输出: true 限制：
     *
     * <p>0 <= len(s) <= 100 如果你不使用额外的数据结构，会很加分。
     *
     * @param astr
     * @return
     */
    public boolean isUnique(String astr) {
        int[] letters = new int[128];

        for (char c : astr.toCharArray()) {

            if (letters[c] > 0) {
                return false;
            }
            letters[c]++;
        }
        // 不适用额外的数据结构
        // 位运算 本质是一个 boolean 数组
        int num = 0;
        for (char c : astr.toCharArray()) {
            int n = 1 << (c - 'a');
            if ((num & n) > 0) {
                return false;
            }
            num += n;
        }

        return true;
    }

    @Test
    public void numWays() {
        int n = 5, k = 3;
        int[][] relation = {{0, 2}, {2, 1}, {3, 4}, {2, 3}, {1, 4}, {2, 0}, {0, 4}};
        logResult(numWays(n, relation, k));
    }
    /**
     * LCP 07. 传递信息 小朋友 A 在和 ta 的小伙伴们玩传信息游戏，游戏规则如下：
     *
     * <p>有 n 名玩家，所有玩家编号分别为 0 ～ n-1，其中小朋友 A 的编号为 0 每个玩家都有固定的若干个可传信息的其他玩家（也可能没有）。传信息的关系是单向的（比如 A 可以向
     * B 传信息，但 B 不能向 A 传信息）。 每轮信息必须需要传递给另一个人，且信息可重复经过同一个人 给定总玩家数 n，以及按 [玩家编号,对应可传递玩家编号] 关系组成的二维数组
     * relation。 返回信息从小 A (编号 0 ) 经过 k 轮传递到编号为 n-1 的小伙伴处的方案数；若不能到达，返回 0。
     *
     * <p>示例 1：
     *
     * <p>输入：n = 5, relation = [[0,2],[2,1],[3,4],[2,3],[1,4],[2,0],[0,4]], k = 3
     *
     * <p>输出：3
     *
     * <p>解释：信息从小 A 编号 0 处开始，经 3 轮传递，到达编号 4。共有 3 种方案，分别是 0->2->0->4， 0->2->1->4， 0->2->3->4。
     *
     * <p>示例 2：
     *
     * <p>输入：n = 3, relation = [[0,2],[2,1]], k = 2
     *
     * <p>输出：0
     *
     * <p>解释：信息不能从小 A 处经过 2 轮传递到编号 2
     *
     * <p>限制：
     *
     * <p>2 <= n <= 10 1 <= k <= 5 1 <= relation.length <= 90, 且 relation[i].length == 2 0 <=
     * relation[i][0],relation[i][1] < n 且 relation[i][0] != relation[i][1]
     *
     * @param n
     * @param relation
     * @param k
     * @return
     */
    public int numWays(int n, int[][] relation, int k) {
        // 深度优先遍历
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int[] r : relation) {
            List<Integer> list = map.getOrDefault(r[0], new ArrayList<>());
            list.add(r[1]);
            map.put(r[0], list);
        }
        numWays(map, 0, n - 1, k);
        return numWay;
    }

    static int numWay = 0;

    private void numWays(Map<Integer, List<Integer>> map, int start, int end, int count) {
        if (count == 0 && start == end) {
            numWay++;
            return;
        }
        if (count == 0) {
            return;
        }
        List<Integer> list = map.get(start);
        if (list == null) {
            return;
        }
        for (int num : list) {
            numWays(map, num, end, count - 1);
        }
    }

    /**
     * 面试题 17.10. 主要元素 如果数组中多一半的数都是同一个，则称之为主要元素。给定一个整数数组，找到它的主要元素。若没有，返回-1。
     *
     * <p>示例 1：
     *
     * <p>输入：[1,2,5,9,5,9,5,5,5] 输出：5
     *
     * <p>示例 2：
     *
     * <p>输入：[3,2] 输出：-1
     *
     * <p>示例 3：
     *
     * <p>输入：[2,2,1,1,1,2,2] 输出：2
     *
     * <p>说明： 你有办法在时间复杂度为 O(N)，空间复杂度为 O(1) 内完成吗？
     *
     * @param nums
     * @return
     */
    public int majorityElement(int[] nums) {
        int len = nums.length;

        int helf = (len >> 1) + 1;

        /*int max = nums[0],min = nums[0];
        for (int i = 1; i < len; i++) {
            if (nums[i] > max) {
                max = nums[i];
            }
            if (nums[i] < min) {
                min = nums[i];
            }
        }
        int[] counts = new int[max - min + 1];
        for (int num : nums) {
            int index = num - min;
            counts[index]++;
            if (counts[index] >= helf) {
                return num;
            }
        } */
        /*Map<Integer,Integer> counts = new HashMap<>();
        for (int num : nums) {
            int count = counts.getOrDefault(num,0);
            counts.put(num,++count);
            if (count >= helf) {
                return num;
            }
        }*/

        // 摩尔投票算法
        // 该算法在其局部变量中维护一个序列元素和一个计数器，计数器最初为零。
        // 然后，它一次一个地处理序列的元素。处理元素x时，如果计数器为零，
        // 则算法将x存储为其维护的序列元素，并将计数器设置为1。否则，它将x与存储的元素进行比较，并使计数器递增（如果相等）或递减计数器（不相等）。
        //
        // 这里有两个先觉条件要明确：
        //
        // 出现超过一半以上(n/2)的元素有且只有一个；
        // 这个元素一定存在

        int num = nums[0];
        int count = 1;
        for (int i = 1; i < len; i++) {
            if (count == 0) {
                num = nums[i];
                count = 1;
                continue;
            }
            if (num == nums[i]) {
                count++;
            } else {
                count--;
            }
        }
        if (count <= 0) {
            return -1;
        }
        count = 0;
        for (int a : nums) {
            if (a == num) {
                count++;
            }
        }
        if (count >= helf) {
            return num;
        }

        return -1;
    }

    @Test
    public void minStartValue() {
        int[] nums = {1, -2, -3};
        logResult(minStartValue(nums));
    }

    /**
     * 5372. 逐步求和得到正数的最小值 给你一个整数数组 nums 。你可以选定任意的 正数 startValue 作为初始值。
     *
     * <p>你需要从左到右遍历 nums 数组，并将 startValue 依次累加上 nums 数组中的值。
     *
     * <p>请你在确保累加和始终大于等于 1 的前提下，选出一个最小的 正数 作为 startValue 。
     *
     * <p>示例 1：
     *
     * <p>输入：nums = [-3,2,-3,4,2] 输出：5 解释：如果你选择 startValue = 4，在第三次累加时，和小于 1 。 累加求和 startValue = 4 |
     * startValue = 5 | nums (4 -3 ) = 1 | (5 -3 ) = 2 | -3 (1 +2 ) = 3 | (2 +2 ) = 4 | 2 (3 -3 ) =
     * 0 | (4 -3 ) = 1 | -3 (0 +4 ) = 4 | (1 +4 ) = 5 | 4 (4 +2 ) = 6 | (5 +2 ) = 7 | 2 示例 2：
     *
     * <p>输入：nums = [1,2] 输出：1 解释：最小的 startValue 需要是正数。 示例 3：
     *
     * <p>输入：nums = [1,-2,-3] 输出：5
     *
     * <p>提示：
     *
     * <p>1 <= nums.length <= 100 -100 <= nums[i] <= 100
     *
     * @param nums
     * @return
     */
    public int minStartValue(int[] nums) {
        int startValue = 1;

        int sum = startValue;

        for (int num : nums) {
            sum += num;
            if (sum < 1) {
                int val = 1 - sum;
                startValue += val;
                sum += val;
                log.debug("start:{}", startValue);
            }
        }

        return startValue;
    }

    @Test
    public void testSpiralOrder2() {
        int[][] matrix = {{1}, {4}, {7}};

        // int[][] matrix = {{1, 2, 3,4},{ 5, 6,7, 8},{  9,10,11,12},{  13,14,15,16} };
        // int[][] matrix = {};
        int[] result = spiralOrder2(matrix);
        log.info("result:{}", result);
    }

    /**
     * 面试题29. 顺时针打印矩阵 输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字。
     *
     * <p>示例 1：
     *
     * <p>输入：matrix = [[1,2,3],[4,5,6],[7,8,9]] 输出：[1,2,3,6,9,8,7,4,5] 示例 2：
     *
     * <p>输入：matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]] 输出：[1,2,3,4,8,12,11,10,9,5,6,7]
     *
     * <p>限制：
     *
     * <p>0 <= matrix.length <= 100 0 <= matrix[i].length <= 100
     *
     * @param matrix
     * @return
     */
    public int[] spiralOrder2(int[][] matrix) {
        int rows = matrix.length;
        if (rows == 0) {
            return new int[0];
        }
        int cols = matrix[0].length;
        if (cols == 0) {
            return new int[0];
        }
        int len = rows * cols;
        int index = 0;
        int i = 0, j = 0;
        int[] result = new int[len];

        int startRow = 0, startCol = 0, endRow = rows - 1, endCol = cols - 1;

        int flagIndex = 0;
        if (cols == 1) {
            flagIndex = 1;
        }
        startRow = 1;
        while (index < len) {
            result[index++] = matrix[i][j];
            switch (flagIndex) {
                case 0:
                    {
                        j++;
                        // 从左到右
                        if (j == endCol) {
                            flagIndex = 1;
                            endCol--;
                        }
                    }
                    ;
                    break;
                case 1:
                    {
                        // 从上到下
                        i++;
                        if (i == endRow) {
                            flagIndex = 2;
                            endRow--;
                        }
                    }
                    ;
                    break;
                case 2:
                    {
                        // 从右到左
                        j--;
                        if (j == startCol) {
                            flagIndex = 3;
                            startCol++;
                        }
                    }
                    ;
                    break;
                case 3:
                    {
                        // 从下到上
                        i--;
                        if (i == startRow) {
                            flagIndex = 0;
                            startRow++;
                        }
                    }
                    ;
                    break;
            }
        }

        return result;
    }

    @Test
    public void numberOfSubarrays() {
        int[] nums = {1, 1, 2, 1, 1};
        int k = 3;
        logResult(numberOfSubarrays(nums, k));
    }

    /**
     * 1248. 统计「优美子数组」 给你一个整数数组 nums 和一个整数 k。
     *
     * <p>如果某个 连续 子数组中恰好有 k 个奇数数字，我们就认为这个子数组是「优美子数组」。
     *
     * <p>请返回这个数组中「优美子数组」的数目。
     *
     * <p>示例 1：
     *
     * <p>输入：nums = [1,1,2,1,1], k = 3 输出：2 解释：包含 3 个奇数的子数组是 [1,1,2,1] 和 [1,2,1,1] 。 示例 2：
     *
     * <p>输入：nums = [2,4,6], k = 1 输出：0 解释：数列中不包含任何奇数，所以不存在优美子数组。 示例 3：
     *
     * <p>输入：nums = [2,2,2,1,2,2,1,2,2,2], k = 2 输出：16
     *
     * <p>提示：
     *
     * <p>1 <= nums.length <= 50000 1 <= nums[i] <= 10^5 1 <= k <= nums.length
     *
     * @param nums
     * @param k
     * @return
     */
    public int numberOfSubarrays(int[] nums, int k) {
        List<Integer> list = new ArrayList<>();
        int len = nums.length;
        list.add(-1);
        for (int i = 0; i < len; i++) {
            if ((nums[i] & 1) == 1) {
                list.add(i);
            }
        }
        list.add(len);
        int result = 0;
        log.debug("list:{}", list);
        for (int i = 1; i + k < list.size(); i++) {
            int num1 = list.get(i) - list.get(i - 1);
            int num2 = list.get(i + k) - list.get(i + k - 1);
            log.debug("num1:{},num2:{}", num1, num2);
            result += num1 * num2;
        }

        return result;
        // int count = 0;
        /*int right = 0;
        int count = 0;
        while (count < k && right <len) {
            // 奇数
            if ((nums[right++] & 1) == 1) {
                count++;
            }
        }
        if (count < k) {
            return 0;
        }
        int left = 0;
        right--;
        int result = 1;
        while (left < right && right < len) {
            int count1 = 1;
            while ((nums[left++] & 1) == 0 && left < right) {
                count1++;
            }


            int count2 = 0;
            if ((nums[right] & 1) == 1) {
                right++;
                count2++;
                while ((nums[right++] & 1) == 0 && right < len) {
                    count2++;
                }
            }




            result += count1 * count2;
        }
        return result;*/
    }

    @Test
    public void test1() {
        logResult(System.getProperty("user.home"));
    }

    /**
     * 面试题51. 数组中的逆序对 在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。输入一个数组，求出这个数组中的逆序对的总数。 示例 1:
     *
     * <p>输入: [7,5,6,4] 输出: 5
     *
     * <p>限制：
     *
     * <p>0 <= 数组长度 <= 50000
     *
     * @param nums
     * @return
     */
    public int reversePairs(int[] nums) {
        int len = nums.length;
        int result = 0;
        /*for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                if (nums[i] > nums[j]) {
                    result++;
                }
            }
        }*/
        return result;
    }

    @Test
    public void constructArr() {
        int[] a = {1, 2, 3, 4, 5};
        int[] result = constructArr(a);
        log.debug("result:{}", result);
    }
    /**
     * 面试题66. 构建乘积数组 给定一个数组 A[0,1,…,n-1]，请构建一个数组 B[0,1,…,n-1]，其中 B 中的元素
     * B[i]=A[0]×A[1]×…×A[i-1]×A[i+1]×…×A[n-1]。不能使用除法。
     *
     * <p>示例:
     *
     * <p>输入: [1,2,3,4,5] 输出: [120,60,40,30,24]
     *
     * <p>提示：
     *
     * <p>所有元素乘积之和不会溢出 32 位整数 a.length <= 100000
     *
     * @param a
     * @return
     */
    public int[] constructArr(int[] a) {
        int len = a.length;
        int[] result = new int[a.length];
        Arrays.fill(result, 1);

        int sum = 1;
        for (int i = 1; i < len; i++) {
            sum *= a[i - 1];
            result[i] *= sum;
        }
        log.debug("result:{}", result);
        sum = 1;
        for (int i = len - 2; i >= 0; i--) {
            sum *= a[i + 1];
            result[i] *= sum;
        }
        log.debug("result:{}", result);

        return result;
    }

    @Test
    public void divingBoard() {
        int shorter = 1, longer = 2, k = 3;
        int[] result = divingBoard(shorter, longer, k);
        log.debug("result:{}", result);
    }
    /**
     * 面试题 16.11. 跳水板 你正在使用一堆木板建造跳水板。有两种类型的木板，其中长度较短的木板长度为shorter，长度较长的木板长度为longer。
     * 你必须正好使用k块木板。编写一个方法，生成跳水板所有可能的长度。
     *
     * <p>返回的长度需要从小到大排列。
     *
     * <p>示例：
     *
     * <p>输入： shorter = 1 longer = 2 k = 3 输出： {3,4,5,6} 提示：
     *
     * <p>0 < shorter <= longer 0 <= k <= 100000
     *
     * @param shorter
     * @param longer
     * @param k
     * @return
     */
    public int[] divingBoard(int shorter, int longer, int k) {

        /*boardShorter = shorter;
        boardLonger = longer;
        divingBoard(0,k);
        int[] result = new int[divingBoardSet.size()];
        int index = 0;
        for (int num : divingBoardSet) {
            result[index++] = num;
        }
        Arrays.sort(result);
        return result;*/
        if (k == 0) return new int[0];
        if (shorter == longer) {
            return new int[] {k * shorter};
        }

        int[] result = new int[k + 1];
        for (int i = 0; i < k + 1; i++) {
            result[i] = (k - i) * shorter + i * longer;
        }
        return result;
    }

    @Test
    public void masterMind() {
        String solution = "RGBY", guess = "GGRR";
        int[] result = masterMind(solution, guess);
        log.debug("result:{}", result);
    }

    /**
     * 面试题 16.15. 珠玑妙算 珠玑妙算游戏（the game of master mind）的玩法如下。
     *
     * <p>计算机有4个槽，每个槽放一个球，颜色可能是红色（R）、黄色（Y）、绿色（G）或蓝色（B）。 例如，计算机可能有RGGB
     * 4种（槽1为红色，槽2、3为绿色，槽4为蓝色）。作为用户，你试图猜出颜色组合。打个比方，你可能会猜YRGB。
     * 要是猜对某个槽的颜色，则算一次“猜中”；要是只猜对颜色但槽位猜错了，则算一次“伪猜中”。注意，“猜中”不能算入“伪猜中”。
     *
     * <p>给定一种颜色组合solution和一个猜测guess，编写一个方法，返回猜中和伪猜中的次数answer，其中answer[0]为猜中的次数，answer[1]为伪猜中的次数。
     *
     * <p>示例：
     *
     * <p>输入： solution="RGBY",guess="GGRR" 输出： [1,1] 解释： 猜中1次，伪猜中1次。 提示：
     *
     * <p>len(solution) = len(guess) = 4 solution和guess仅包含"R","G","B","Y"这4种字符
     *
     * @param solution
     * @param guess
     * @return
     */
    public int[] masterMind(String solution, String guess) {
        int[] result = new int[2];
        int same = 0, sameColor = 0;
        int[] minds = new int[4];
        if (Objects.equals(solution, guess)) {
            return new int[] {solution.length(), 0};
        }

        for (int i = 0; i < solution.length(); i++) {
            char c = solution.charAt(i);
            if (c == guess.charAt(i)) {
                same++;
            } else {
                minds[getColorIndex(c)]++;
            }
        }

        for (int i = 0; i < guess.length(); i++) {
            char c = guess.charAt(i);
            if (c != solution.charAt(i)) {
                int index = getColorIndex(c);
                minds[index]--;
                if (minds[index] >= 0) {
                    sameColor++;
                }
            }
        }

        result[0] = same;
        result[1] = sameColor;
        return result;
    }

    private int getColorIndex(char c) {
        int index = -1;
        switch (c) {
            case 'R':
                index = 0;
                break;
            case 'G':
                index = 1;
                break;
            case 'B':
                index = 2;
                break;
            case 'Y':
                index = 3;
                break;
        }
        return index;
    }

    @Test
    public void floodFill2() {
        int[][] image = {{1, 1, 1}, {1, 1, 0}, {1, 0, 1}};
        int sr = 1, sc = 1;
        int newColor = 2;

        int[][] result = floodFill2(image, sr, sc, newColor);
        for (int[] a : result) {
            log.debug("result:{}", a);
        }
    }

    /**
     * 面试题 08.10. 颜色填充
     * 颜色填充。编写函数，实现许多图片编辑软件都支持的“颜色填充”功能。给定一个屏幕（以二维数组表示，元素为颜色值）、一个点和一个新的颜色值，将新颜色值填入这个点的周围区域，直到原来的颜色值全都改变。
     *
     * <p>示例1:
     *
     * <p>输入： image = [[1,1,1],[1,1,0],[1,0,1]] sr = 1, sc = 1, newColor = 2
     * 输出：[[2,2,2],[2,2,0],[2,0,1]] 解释: 在图像的正中间，(坐标(sr,sc)=(1,1)), 在路径上所有符合条件的像素点的颜色都被更改成2。
     * 注意，右下角的像素没有更改为2， 因为它不是在上下左右四个方向上与初始点相连的像素点。 说明：
     *
     * <p>image 和 image[0] 的长度在范围 [1, 50] 内。 给出的初始点将满足 0 <= sr < image.length 和 0 <= sc <
     * image[0].length。 image[i][j] 和 newColor 表示的颜色值在范围 [0, 65535]内。
     *
     * @param image
     * @param sr
     * @param sc
     * @param newColor
     * @return
     */
    public int[][] floodFill2(int[][] image, int sr, int sc, int newColor) {
        floodFill2(image, sr, sc, image[sr][sc], newColor);
        return image;
    }

    private void floodFill2(int[][] image, int sr, int sc, int oldColor, int newColor) {
        if (oldColor == newColor) {
            return;
        }
        int rows = image.length;
        int cols = image[0].length;
        if (!inArea(sr, sc, rows, cols)) {
            return;
        }
        if (image[sr][sc] != oldColor) {
            return;
        }
        image[sr][sc] = newColor;
        for (int i = 0; i < 4; i++) {
            int row = sr + DIR_ROW[i];
            int col = sc + DIR_COL[i];
            floodFill2(image, row, col, oldColor, newColor);
        }
    }

    @Test
    public void existTest() {
        char[][] board = {{'A', 'B', 'C', 'E'}, {'S', 'F', 'C', 'S'}, {'A', 'D', 'E', 'E'}};
        // char[][] board = {{'A','A'} };
        String word = "ABCCED";
        logResult(exist(board, word));
    }
    /**
     * 面试题12. 矩阵中的路径 请设计一个函数，用来判断在一个矩阵中是否存在一条包含某字符串所有字符的路径。路径可以从矩阵中的任意一格开始，
     * 每一步可以在矩阵中向左、右、上、下移动一格。如果一条路径经过了矩阵的某一格，那么该路径不能再次进入该格子。
     * 例如，在下面的3×4的矩阵中包含一条字符串“bfce”的路径（路径中的字母用加粗标出）。
     *
     * <p>[["a","b","c","e"], ["s","f","c","s"], ["a","d","e","e"]]
     *
     * <p>但矩阵中不包含字符串“abfb”的路径，因为字符串的第一个字符b占据了矩阵中的第一行第二个格子之后，路径不能再次进入这个格子。
     *
     * <p>示例 1：
     *
     * <p>输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCCED"
     * 输出：true 示例 2：
     *
     * <p>输入：board = [["a","b"],["c","d"]], word = "abcd" 输出：false 提示：
     *
     * <p>1 <= board.length <= 200 1 <= board[i].length <= 200
     *
     * @param board
     * @param word
     * @return
     */
    public boolean exist(char[][] board, String word) {
        int rows = board.length;
        int cols = board[0].length;
        boolean[][] visited = new boolean[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (existWord(board, i, j, word, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean existWord(char[][] board, int row, int col, String word, int index) {
        int rows = board.length;
        int cols = board[0].length;
        if (board[row][col] != word.charAt(index)) {
            log.debug("false1:{},{}", row, col);
            return false;
        }
        if (index == word.length() - 1 && board[row][col] == word.charAt(index)) {
            return true;
        }
        char tmp = board[row][col];
        board[row][col] = '/';
        for (int i = 0; i < 4; i++) {
            int rowIndex = row + DIR_ROW[i];
            int colIndex = col + DIR_COL[i];
            if (inArea(rowIndex, colIndex, rows, cols)
                    && existWord(board, rowIndex, colIndex, word, index + 1)) {
                return true;
            }
        }
        board[row][col] = tmp;
        return false;
    }

    @Test
    public void countTriplets() {
        int[] arr = {1, 1, 1, 1, 1};

        logResult(countTriplets(arr));
    }

    /**
     * 5405. 形成两个异或相等数组的三元组数目 给你一个整数数组 arr 。
     *
     * <p>现需要从数组中取三个下标 i、j 和 k ，其中 (0 <= i < j <= k < arr.length) 。
     *
     * <p>a 和 b 定义如下：
     *
     * <p>a = arr[i] ^ arr[i + 1] ^ ... ^ arr[j - 1] b = arr[j] ^ arr[j + 1] ^ ... ^ arr[k] 注意：^ 表示
     * 按位异或 操作。
     *
     * <p>请返回能够令 a == b 成立的三元组 (i, j , k) 的数目。
     *
     * <p>示例 1：
     *
     * <p>输入：arr = [2,3,1,6,7] 输出：4 解释：满足题意的三元组分别是 (0,1,2), (0,2,2), (2,3,4) 以及 (2,4,4) 示例 2：
     *
     * <p>输入：arr = [1,1,1,1,1] 输出：10 示例 3：
     *
     * <p>输入：arr = [2,3] 输出：0 示例 4：
     *
     * <p>输入：arr = [1,3,5,7,9] 输出：3 示例 5：
     *
     * <p>输入：arr = [7,11,12,9,5,2,7,17,22] 输出：8
     *
     * <p>提示：
     *
     * <p>1 <= arr.length <= 300 1 <= arr[i] <= 10^8
     *
     * @param arr
     * @return
     */
    public int countTriplets(int[] arr) {
        int count = 0;

        for (int i = 0; i < arr.length - 1; i++) {
            int sum = arr[i];
            //  j <= k
            for (int j = i + 1; j < arr.length; j++) {
                sum ^= arr[j];

                // log.debug("start {},end {}, sum ,{}  a:{}",i,j,sum,a);
                count += countTriplets(arr, i, j, sum);
            }
        }
        return count;
    }

    private int countTriplets(int[] arr, int start, int end, int sum) {
        int a = 0;
        int result = 0;
        for (int i = start; i < end; i++) {
            int num = arr[start];
            a = a ^ num;
            sum = sum ^ num;
            if (a == sum) {
                result++;
            }
        }
        return result;
    }

    @Test
    public void minTime() {
        int n = 7;
        int[][] edges = {{0, 1}, {0, 2}, {1, 4}, {1, 5}, {2, 3}, {2, 6}};
        List<Boolean> hasApple = Arrays.asList(false, false, false, false, false, false, false);
        logResult(minTime(n, edges, hasApple));
    }

    /**
     * 5406. 收集树上所有苹果的最少时间 给你一棵有 n 个节点的无向树，节点编号为 0 到 n-1 ，它们中有一些节点有苹果。通过树上的一条边，需要花费 1 秒钟。你从 节点 0 出发，
     * 请你返回最少需要多少秒，可以收集到所有苹果，并回到节点 0 。
     *
     * <p>无向树的边由 edges 给出，其中 edges[i] = [fromi, toi] ，表示有一条边连接 from 和 toi 。除此以外，还有一个布尔数组 hasApple ，
     * 其中 hasApple[i] = true 代表节点 i 有一个苹果，否则，节点 i 没有苹果。
     *
     * <p>示例 1：
     *
     * <p>输入：n = 7, edges = [[0,1],[0,2],[1,4],[1,5],[2,3],[2,6]], hasApple =
     * [false,false,true,false,true,true,false] 输出：8
     * 解释：上图展示了给定的树，其中红色节点表示有苹果。一个能收集到所有苹果的最优方案由绿色箭头表示。 示例 2：
     *
     * <p>输入：n = 7, edges = [[0,1],[0,2],[1,4],[1,5],[2,3],[2,6]], hasApple =
     * [false,false,true,false,false,true,false] 输出：6
     * 解释：上图展示了给定的树，其中红色节点表示有苹果。一个能收集到所有苹果的最优方案由绿色箭头表示。 示例 3：
     *
     * <p>输入：n = 7, edges = [[0,1],[0,2],[1,4],[1,5],[2,3],[2,6]], hasApple =
     * [false,false,false,false,false,false,false] 输出：0
     *
     * <p>提示：
     *
     * <p>1 <= n <= 10^5 edges.length == n-1 edges[i].length == 2 0 <= fromi, toi <= n-1 fromi < toi
     * hasApple.length == n
     *
     * @param n
     * @param edges
     * @param hasApple
     * @return
     */
    public int minTime(int n, int[][] edges, List<Boolean> hasApple) {
        int result = 0;

        Map<Integer, List<Integer>> map = new HashMap<>();

        for (int[] edge : edges) {
            List<Integer> list = map.getOrDefault(edge[0], new ArrayList<>());
            list.add(edge[1]);
            map.putIfAbsent(edge[0], list);
        }

        minTime(0, 0, map, hasApple);
        log.debug("a :{}", appleTime);
        return appleTime;
    }

    static int appleTime = 0;

    private boolean minTime(
            int root, int time, Map<Integer, List<Integer>> map, List<Boolean> hasApple) {

        boolean flag = false;
        if (hasApple.get(root)) {
            flag = true;
            log.debug("root :{},time :{}", root, time);
        }
        List<Integer> list = map.get(root);
        if (Objects.isNull(list)) {
            if (flag) {
                appleTime += time;
            }
            return flag;
        }
        for (int child : list) {
            flag |= minTime(child, 1, map, hasApple);
        }
        if (flag) {
            appleTime += time;
        }
        return flag;
    }

    /**
     * 228. 汇总区间 给定一个无重复元素的有序整数数组，返回数组区间范围的汇总。
     *
     * <p>示例 1:
     *
     * <p>输入: [0,1,2,4,5,7] 输出: ["0->2","4->5","7"] 解释: 0,1,2 可组成一个连续的区间; 4,5 可组成一个连续的区间。 示例 2:
     *
     * <p>输入: [0,2,3,4,6,8,9] 输出: ["0","2->4","6","8->9"] 解释: 2,3,4 可组成一个连续的区间; 8,9 可组成一个连续的区间。
     *
     * @param nums
     * @return
     */
    public List<String> summaryRanges(int[] nums) {
        List<String> result = new ArrayList<>();
        int len = nums.length;
        if (len == 0) {
            return result;
        }
        int start = nums[0];

        for (int i = 1; i <= len; i++) {
            if (i < len && nums[i] - nums[i - 1] == 1) {
                continue;
            }
            if (nums[i - 1] == start) {
                result.add(String.valueOf(start));
            } else {
                result.add(start + "->" + nums[i - 1]);
            }
            if (i < len) {
                start = nums[i];
            }
        }

        return result;
    }

    /**
     * 229. 求众数 II 给定一个大小为 n 的数组，找出其中所有出现超过 ⌊ n/3 ⌋ 次的元素。
     *
     * <p>说明: 要求算法的时间复杂度为 O(n)，空间复杂度为 O(1)。
     *
     * <p>示例 1:
     *
     * <p>输入: [3,2,3] 输出: [3] 示例 2:
     *
     * <p>输入: [1,1,1,3,3,2,2,2] 输出: [1,2]
     *
     * @param nums
     * @return
     */
    public List<Integer> majorityElement2(int[] nums) {
        List<Integer> list = new ArrayList<>();
        // 摩尔投票算法
        // 该算法在其局部变量中维护一个序列元素和一个计数器，计数器最初为零。
        // 然后，它一次一个地处理序列的元素。处理元素x时，如果计数器为零，
        // 则算法将x存储为其维护的序列元素，并将计数器设置为1。否则，它将x与存储的元素进行比较，并使计数器递增（如果相等）或递减计数器（不相等）。
        //
        // 这里有两个先觉条件要明确：
        //
        // 出现超过一半以上(n/2)的元素有且只有一个；
        // 这个元素一定存在
        int num1 = nums[0], count1 = 1;
        int num2 = nums[0], count2 = 1;
        for (int i = 1; i < nums.length; i++) {

            if (num1 == nums[i]) {
                count1++;
                continue;
            }
            if (num2 == nums[i]) {
                count2++;
                continue;
            }
            if (count1 == 0) {
                num1 = nums[i];
                count1 = 1;
                continue;
            }

            if (count2 == 0) {
                num2 = nums[i];
                count2 = 1;
                continue;
            }
            count1--;
            count2--;
        }
        /*Map<Integer,Integer> map = new HashMap<>();
        for (int num : nums) {
            int count = map.getOrDefault(num,0);
            map.put(num, count + 1);
        }
        int len = nums.length/ 3;



        map.forEach((k,v) -> {
            if (v > len) {
                list.add(k);
            }
        });*/

        return list;
    }

    /**
     * 442. 数组中重复的数据 给定一个整数数组 a，其中1 ≤ a[i] ≤ n （n为数组长度）, 其中有些元素出现两次而其他元素出现一次。
     *
     * <p>找到所有出现两次的元素。
     *
     * <p>你可以不用到任何额外空间并在O(n)时间复杂度内解决这个问题吗？
     *
     * <p>示例：
     *
     * <p>输入: [4,3,2,7,8,2,3,1]
     *
     * <p>输出: [2,3]
     *
     * @param nums
     * @return
     */
    public List<Integer> findDuplicates(int[] nums) {
        // 1 ≤ a[i] ≤ n
        // Arrays.sort(nums);
        /* int[] arrays = new int[nums.length];
        List<Integer> list = new ArrayList<>();
        for (int num : nums) {
            if ( arrays[num - 1] == 1) {
                list.add(num);
            }
            arrays[num]++;
        }*/

        List<Integer> list = new ArrayList<>();
        for (int num : nums) {
            if (num < 0) {
                num += nums.length + 1;
            }
            if (nums[num - 1] >= 0) {
                nums[num - 1] -= nums.length + 1;
            } else {
                list.add(num);
            }
        }
        return list;
    }

    /**
     * 373. 查找和最小的K对数字 给定两个以升序排列的整形数组 nums1 和 nums2, 以及一个整数 k。
     *
     * <p>定义一对值 (u,v)，其中第一个元素来自 nums1，第二个元素来自 nums2。
     *
     * <p>找到和最小的 k 对数字 (u1,v1), (u2,v2) ... (uk,vk)。
     *
     * <p>示例 1:
     *
     * <p>输入: nums1 = [1,7,11], nums2 = [2,4,6], k = 3 输出: [1,2],[1,4],[1,6] 解释: 返回序列中的前 3 对数：
     * [1,2],[1,4],[1,6],[7,2],[7,4],[11,2],[7,6],[11,4],[11,6] 示例 2:
     *
     * <p>输入: nums1 = [1,1,2], nums2 = [1,2,3], k = 2 输出: [1,1],[1,1] 解释: 返回序列中的前 2 对数：
     * [1,1],[1,1],[1,2],[2,1],[1,2],[2,2],[1,3],[1,3],[2,3] 示例 3:
     *
     * <p>输入: nums1 = [1,2], nums2 = [3], k = 3 输出: [1,3],[2,3] 解释: 也可能序列中所有的数对都被返回:[1,3],[2,3]
     *
     * @param nums1
     * @param nums2
     * @param k
     * @return
     */
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        // 构建最大堆
        PriorityQueue<int[]> heap = new PriorityQueue<>((a, b) -> b[0] + b[1] - a[0] - a[1]);

        for (int i = 0; i < nums1.length; i++) {
            for (int j = 0; j < nums2.length; j++) {
                int[] indexs = new int[2];
                indexs[0] = nums1[i];
                indexs[1] = nums2[j];
                int sum = nums1[i] + nums2[j];
                if (heap.size() < k) {
                    heap.offer(indexs);
                } else {
                    int[] head = heap.peek();
                    if (head[0] + head[1] > sum) {
                        heap.poll();
                        heap.offer(indexs);
                    }
                }
            }
        }
        List<List<Integer>> result = new ArrayList<>();
        for (Iterator<int[]> iterator = heap.iterator(); iterator.hasNext(); ) {
            int[] nums = iterator.next();
            List<Integer> list = new ArrayList<>();
            list.add(nums[0]);
            list.add(nums[1]);
            result.add(list);
        }
        return result;
    }

    /**
     * 5412. 在既定时间做作业的学生人数 给你两个整数数组 startTime（开始时间）和 endTime（结束时间），并指定一个整数 queryTime 作为查询时间。
     *
     * <p>已知，第 i 名学生在 startTime[i] 时开始写作业并于 endTime[i] 时完成作业。
     *
     * <p>请返回在查询时间 queryTime 时正在做作业的学生人数。形式上，返回能够使 queryTime 处于区间 [startTime[i],
     * endTime[i]]（含）的学生人数。
     *
     * <p>示例 1：
     *
     * <p>输入：startTime = [1,2,3], endTime = [3,2,7], queryTime = 4 输出：1 解释：一共有 3 名学生。 第一名学生在时间 1
     * 开始写作业，并于时间 3 完成作业，在时间 4 没有处于做作业的状态。 第二名学生在时间 2 开始写作业，并于时间 2 完成作业，在时间 4 没有处于做作业的状态。 第二名学生在时间 3
     * 开始写作业，预计于时间 7 完成作业，这是是唯一一名在时间 4 时正在做作业的学生。 示例 2：
     *
     * <p>输入：startTime = [4], endTime = [4], queryTime = 4 输出：1 解释：在查询时间只有一名学生在做作业。 示例 3：
     *
     * <p>输入：startTime = [4], endTime = [4], queryTime = 5 输出：0 示例 4：
     *
     * <p>输入：startTime = [1,1,1,1], endTime = [1,3,2,4], queryTime = 7 输出：0 示例 5：
     *
     * <p>输入：startTime = [9,8,7,6,5,4,3,2,1], endTime = [10,10,10,10,10,10,10,10,10], queryTime = 5
     * 输出：5
     *
     * <p>提示：
     *
     * <p>startTime.length == endTime.length 1 <= startTime.length <= 100 1 <= startTime[i] <=
     * endTime[i] <= 1000 1 <= queryTime <= 1000
     *
     * @param startTime
     * @param endTime
     * @param queryTime
     * @return
     */
    public int busyStudent(int[] startTime, int[] endTime, int queryTime) {
        int count = 0;
        for (int i = 0; i < startTime.length; i++) {
            if (startTime[i] <= queryTime && queryTime <= endTime[i]) {
                count++;
            }
        }
        return count;
    }

    @Test
    public void peopleIndexes() {
        List<List<String>> favoriteCompanies = new ArrayList<>();
        List<String> list1 = new ArrayList<>();
        list1.add("leetcode");
        list1.add("google");
        list1.add("facebook");
        List<String> list2 = new ArrayList<>();
        list2.add("google");
        list2.add("facebook");
        List<String> list3 = new ArrayList<>();
        list3.add("google");
        list3.add("facebook");
        List<String> list4 = new ArrayList<>();
        list4.add("google");
        list4.add("amazon");
        favoriteCompanies.add(list1);
        favoriteCompanies.add(list2);
        favoriteCompanies.add(list3);
        favoriteCompanies.add(list4);
        List<Integer> list = peopleIndexes(favoriteCompanies);
        logResult(list);
    }

    /**
     * 5414. 收藏清单 给你一个数组 favoriteCompanies ，其中 favoriteCompanies[i] 是第 i 名用户收藏的公司清单（下标从 0 开始）。
     *
     * <p>请找出不是其他任何人收藏的公司清单的子集的收藏清单，并返回该清单下标。下标需要按升序排列。
     *
     * <p>示例 1：
     *
     * <p>输入：favoriteCompanies =
     * [["leetcode","google","facebook"],["google","microsoft"],["google","facebook"],["google"],["amazon"]]
     * 输出：[0,1,4] 解释： favoriteCompanies[2]=["google","facebook"] 是
     * favoriteCompanies[0]=["leetcode","google","facebook"] 的子集。 favoriteCompanies[3]=["google"] 是
     * favoriteCompanies[0]=["leetcode","google","facebook"] 和
     * favoriteCompanies[1]=["google","microsoft"] 的子集。 其余的收藏清单均不是其他任何人收藏的公司清单的子集，因此，答案为 [0,1,4] 。
     * 示例 2：
     *
     * <p>输入：favoriteCompanies =
     * [["leetcode","google","facebook"],["leetcode","amazon"],["facebook","google"]] 输出：[0,1]
     * 解释：favoriteCompanies[2]=["facebook","google"] 是
     * favoriteCompanies[0]=["leetcode","google","facebook"] 的子集，因此，答案为 [0,1] 。 示例 3：
     *
     * <p>输入：favoriteCompanies = [["leetcode"],["google"],["facebook"],["amazon"]] 输出：[0,1,2,3]
     *
     * <p>提示：
     *
     * <p>1 <= favoriteCompanies.length <= 100 1 <= favoriteCompanies[i].length <= 500 1 <=
     * favoriteCompanies[i][j].length <= 20 favoriteCompanies[i] 中的所有字符串 各不相同 。 用户收藏的公司清单也 各不相同
     * ，也就是说，即便我们按字母顺序排序每个清单， favoriteCompanies[i] != favoriteCompanies[j] 仍然成立。 所有字符串仅包含小写英文字母。
     *
     * @param favoriteCompanies
     * @return
     */
    public List<Integer> peopleIndexes(List<List<String>> favoriteCompanies) {
        List<Integer> result = new ArrayList<>();
        int len = favoriteCompanies.size();
        for (int i = 0; i < len; i++) {
            Set<String> set1 = new HashSet<>(favoriteCompanies.get(i));
            boolean flag = true;
            for (int j = 0; j < len; j++) {
                if (i == j) {
                    continue;
                }
                Set<String> set2 = new HashSet<>(favoriteCompanies.get(j));
                if (set2.containsAll(set1)) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                result.add(i);
            }
        }
        return result;

        /*List<Integer> result = new ArrayList<>();
        Map<String,List<Integer>> favoriteMap = new HashMap<>();
        for (int i = 0; i < favoriteCompanies.size(); i++) {
            for (String key : favoriteCompanies.get(i)) {
                List<Integer> list = favoriteMap.computeIfAbsent(key,k -> new ArrayList<>());
                list.add(i);
            }
        }
        for (int i = 0; i < favoriteCompanies.size(); i++) {
            List<String> list = favoriteCompanies.get(i);
            List<Integer> indexs = favoriteMap.get(list.get(0));
            for (int j = 1; j < list.size(); j++) {
                indexs = calIndex(indexs,favoriteMap.get(list.get(j)));
                if (indexs.isEmpty()) {
                    break;
                }
            }
            if (indexs.isEmpty()) {
                result.add(i);
            }
        }

        return result;*/
    }

    /**
     * 求两个列表的相同元素
     *
     * @param index1
     * @param index2
     */
    private List<Integer> calIndex(List<Integer> index1, List<Integer> index2) {
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < index1.size(); i++) {
            for (int j = 0; j < index2.size(); j++) {
                if (Objects.equals(index1.get(i), index2.get(j))) {
                    result.add(index1.get(i));
                }
            }
        }
        return result;
    }

    @Test
    public void pacificAtlantic() {
        int[][] matrix = {
            {1, 2, 2, 3, 5}, {3, 2, 3, 4, 4}, {2, 3, 4, 3, 1}, {6, 7, 1, 4, 5}, {5, 1, 1, 2, 4}
        };
        List<List<Integer>> result = pacificAtlantic(matrix);
        logResult(result);
    }

    /**
     * 417. 太平洋大西洋水流问题 给定一个 m x n 的非负整数矩阵来表示一片大陆上各个单元格的高度。“太平洋”处于大陆的左边界和上边界，而“大西洋”处于大陆的右边界和下边界。
     *
     * <p>规定水流只能按照上、下、左、右四个方向流动，且只能从高到低或者在同等高度上流动。
     *
     * <p>请找出那些水流既可以流动到“太平洋”，又能流动到“大西洋”的陆地单元的坐标。
     *
     * <p>提示：
     *
     * <p>输出坐标的顺序不重要 m 和 n 都小于150
     *
     * <p>示例：
     *
     * <p>给定下面的 5x5 矩阵:
     *
     * <p>太平洋 ~ ~ ~ ~ ~ ~ 1 2 2 3 (5) * ~ 3 2 3 (4) (4) * ~ 2 4 (5) 3 1 * ~ (6) (7) 1 4 5 * ~ (5) 1
     * 1 2 4 * * * * * * 大西洋
     *
     * <p>返回:
     *
     * <p>[[0, 4], [1, 3], [1, 4], [2, 2], [3, 0], [3, 1], [4, 0]] (上图中带括号的单元).
     *
     * @param matrix
     * @return
     */
    public List<List<Integer>> pacificAtlantic(int[][] matrix) {
        List<List<Integer>> result = new ArrayList<>();
        int rows = matrix.length;
        if (rows == 0) {
            return result;
        }
        int cols = matrix[0].length;
        if (cols == 0) {
            return result;
        }
        boolean[][] canReach1 = new boolean[rows][cols];

        //  上方 第1行
        for (int i = 0; i < cols; i++) {
            // 查找比num小的点
            checkNums(matrix, canReach1, 0, i);
        }
        //  左方 第1列
        for (int i = 0; i < rows; i++) {
            // 查找比num小的点
            checkNums(matrix, canReach1, i, 0);
        }
        boolean[][] canReach2 = new boolean[rows][cols];
        //  下方 最后1行
        for (int i = 0; i < cols - 1; i++) {
            // 查找比num小的点
            checkNums(matrix, canReach2, rows - 1, i);
        }
        //  右方 最后1列
        for (int i = 0; i < rows; i++) {
            // 查找比num小的点
            checkNums(matrix, canReach2, i, cols - 1);
        }

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (canReach1[i][j] && canReach2[i][j]) {
                    result.add(Arrays.asList(i, j));
                }
            }
        }
        return result;
    }

    private void checkNums(int[][] matrix, boolean[][] canReach, int row, int col) {
        int num = matrix[row][col];
        if (canReach[row][col]) {
            return;
        }
        canReach[row][col] = true;
        for (int i = 0; i < 4; i++) {
            int r = row + DIR_ROW[i];
            int c = col + DIR_COL[i];
            if (inArea(r, c, matrix.length, matrix[0].length) && matrix[r][c] >= num) {
                checkNums(matrix, canReach, r, c);
            }
        }
    }

    /**
     * 5408. 通过翻转子数组使两个数组相等 给你两个长度相同的整数数组 target 和 arr 。
     *
     * <p>每一步中，你可以选择 arr 的任意 非空子数组 并将它翻转。你可以执行此过程任意次。
     *
     * <p>如果你能让 arr 变得与 target 相同，返回 True；否则，返回 False 。
     *
     * <p>示例 1：
     *
     * <p>输入：target = [1,2,3,4], arr = [2,4,1,3] 输出：true 解释：你可以按照如下步骤使 arr 变成 target： 1- 翻转子数组
     * [2,4,1] ，arr 变成 [1,4,2,3] 2- 翻转子数组 [4,2] ，arr 变成 [1,2,4,3] 3- 翻转子数组 [4,3] ，arr 变成 [1,2,3,4]
     * 上述方法并不是唯一的，还存在多种将 arr 变成 target 的方法。 示例 2：
     *
     * <p>输入：target = [7], arr = [7] 输出：true 解释：arr 不需要做任何翻转已经与 target 相等。 示例 3：
     *
     * <p>输入：target = [1,12], arr = [12,1] 输出：true 示例 4：
     *
     * <p>输入：target = [3,7,9], arr = [3,7,11] 输出：false 解释：arr 没有数字 9 ，所以无论如何也无法变成 target 。 示例 5：
     *
     * <p>输入：target = [1,1,1,1,1], arr = [1,1,1,1,1] 输出：true
     *
     * <p>提示：
     *
     * <p>target.length == arr.length 1 <= target.length <= 1000 1 <= target[i] <= 1000 1 <= arr[i]
     * <= 1000
     *
     * @param target
     * @param arr
     * @return
     */
    public boolean canBeEqual(int[] target, int[] arr) {
        // 判断两个集合元素是否相同即可
        int[] nums = new int[1001];
        for (int num : target) {
            nums[num]++;
        }
        for (int num : arr) {
            nums[num]--;
        }
        for (int num : nums) {
            if (num != 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * 5424. 数组中两元素的最大乘积 给你一个整数数组 nums，请你选择数组的两个不同下标 i 和 j，使 (nums[i]-1)*(nums[j]-1) 取得最大值。
     *
     * <p>请你计算并返回该式的最大值。
     *
     * <p>示例 1：
     *
     * <p>输入：nums = [3,4,5,2] 输出：12 解释：如果选择下标 i=1 和 j=2（下标从 0 开始），则可以获得最大值，(nums[1]-1)*(nums[2]-1) =
     * (4-1)*(5-1) = 3*4 = 12 。 示例 2：
     *
     * <p>输入：nums = [1,5,4,5] 输出：16 解释：选择下标 i=1 和 j=3（下标从 0 开始），则可以获得最大值 (5-1)*(5-1) = 16 。 示例 3：
     *
     * <p>输入：nums = [3,7] 输出：12
     *
     * <p>提示：
     *
     * <p>2 <= nums.length <= 500 1 <= nums[i] <= 10^3
     *
     * @param nums
     * @return
     */
    public int maxProduct2(int[] nums) {
        if (nums.length == 2) {
            return (nums[0] - 1) * (nums[1] - 1);
        }
        Arrays.sort(nums);
        return (nums[nums.length - 1] - 1) * (nums[nums.length - 2] - 1);
    }

    public void maxArea2() {
        int h = 1000000000, w = 1000000000;

        // logResult(maxArea(h,w,horizontalCuts,verticalCuts));
    }

    /**
     * 5425. 切割后面积最大的蛋糕 矩形蛋糕的高度为 h 且宽度为 w，给你两个整数数组 horizontalCuts 和 verticalCuts，其中
     * horizontalCuts[i] 是从矩形蛋糕顶部到第 i 个水平切口的距离，类似地， verticalCuts[j] 是从矩形蛋糕的左侧到第 j 个竖直切口的距离。
     *
     * <p>请你按数组 horizontalCuts 和 verticalCuts 中提供的水平和竖直位置切割后，请你找出 面积最大 的那份蛋糕，并返回其 面积 。
     * 由于答案可能是一个很大的数字，因此需要将结果对 10^9 + 7 取余后返回。
     *
     * <p>示例 1：
     *
     * <p>输入：h = 5, w = 4, horizontalCuts = [1,2,4], verticalCuts = [1,3] 输出：4
     * 解释：上图所示的矩阵蛋糕中，红色线表示水平和竖直方向上的切口。切割蛋糕后，绿色的那份蛋糕面积最大。 示例 2：
     *
     * <p>输入：h = 5, w = 4, horizontalCuts = [3,1], verticalCuts = [1] 输出：6
     * 解释：上图所示的矩阵蛋糕中，红色线表示水平和竖直方向上的切口。切割蛋糕后，绿色和黄色的两份蛋糕面积最大。 示例 3：
     *
     * <p>输入：h = 5, w = 4, horizontalCuts = [3], verticalCuts = [3] 输出：9
     *
     * <p>提示：
     *
     * <p>2 <= h, w <= 10^9 1 <= horizontalCuts.length < min(h, 10^5) 1 <= verticalCuts.length <
     * min(w, 10^5) 1 <= horizontalCuts[i] < h 1 <= verticalCuts[i] < w 题目数据保证 horizontalCuts
     * 中的所有元素各不相同 题目数据保证 verticalCuts 中的所有元素各不相同
     *
     * @param h
     * @param w
     * @param horizontalCuts
     * @param verticalCuts
     * @return
     */
    public int maxArea(int h, int w, int[] horizontalCuts, int[] verticalCuts) {
        int mod = 1000000007;
        long maxHeight = 0, maxWidth = 0;
        Arrays.sort(horizontalCuts);
        Arrays.sort(verticalCuts);
        for (int i = 0; i < horizontalCuts.length; i++) {
            long height;
            if (i == 0) {
                height = horizontalCuts[i];
            } else {
                height = horizontalCuts[i] - horizontalCuts[i - 1];
            }
            maxHeight = Math.max(maxHeight, height);
            if (i == horizontalCuts.length - 1) {
                height = h - horizontalCuts[i];
                maxHeight = Math.max(maxHeight, height);
            }
        }
        for (int i = 0; i < verticalCuts.length; i++) {
            long width;
            if (i == 0) {
                width = verticalCuts[i];
            } else {
                width = verticalCuts[i] - verticalCuts[i - 1];
            }
            maxWidth = Math.max(maxWidth, width);
            if (i == verticalCuts.length - 1) {
                width = w - verticalCuts[i];
                maxWidth = Math.max(maxWidth, width);
            }
        }

        return (int) ((maxHeight % mod) * (maxWidth % mod)) % mod;
    }

    /**
     * 面试题 01.08. 零矩阵 编写一种算法，若M × N矩阵中某个元素为0，则将其所在的行与列清零。
     *
     * <p>示例 1：
     *
     * <p>输入： [ [1,1,1], [1,0,1], [1,1,1] ] 输出： [ [1,0,1], [0,0,0], [1,0,1] ] 示例 2：
     *
     * <p>输入： [ [0,1,2,0], [3,4,5,2], [1,3,1,5] ] 输出： [ [0,0,0,0], [0,4,5,0], [0,3,1,0] ]
     *
     * @param matrix
     */
    public void setZeroes2(int[][] matrix) {
        int rows = matrix.length;
        if (rows == 0) {
            return;
        }
        int cols = matrix[0].length;
        if (cols == 0) {
            return;
        }
        boolean[] rowZero = new boolean[rows];
        boolean[] colZero = new boolean[cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (matrix[i][j] == 0) {
                    rowZero[i] = true;
                    colZero[j] = true;
                }
            }
        }

        for (int i = 0; i < rows; i++) {
            if (!rowZero[i]) {
                continue;
            }
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = 0;
            }
        }
        for (int j = 0; j < cols; j++) {
            if (!colZero[j]) {
                continue;
            }
            for (int i = 0; i < rows; i++) {
                matrix[i][j] = 0;
            }
        }
    }

    @Test
    public void findLongestSubarray() {
        String[] array = {
            "A", "1", "B", "C", "D", "2", "3", "4", "E", "5", "F", "G", "6", "7", "H", "I", "J",
            "K", "L", "M"
        };
        String[] result = findLongestSubarray(array);
        log.debug("result :{}", Arrays.asList(result));
    }

    /**
     * 面试题 17.05. 字母与数字
     *
     * <p>给定一个放有字符和数字的数组，找到最长的子数组，且包含的字符和数字的个数相同。
     *
     * <p>返回该子数组，若存在多个最长子数组，返回左端点最小的。若不存在这样的数组，返回一个空数组。
     *
     * <p>示例 1:
     *
     * <p>输入: ["A","1","B","C","D","2","3","4","E","5","F","G","6","7","H","I","J","K","L","M"]
     *
     * <p>输出: ["A","1","B","C","D","2","3","4","E","5","F","G","6","7"] 示例 2:
     *
     * <p>输入: ["A","A"]
     *
     * <p>输出: [] 提示：
     *
     * <p>array.length <= 100000
     *
     * @param array
     * @return
     */
    public String[] findLongestSubarray(String[] array) {
        int letterCount = 0, numCount = 0;
        int[] counts = new int[array.length];
        int count = 0;
        for (int i = 0; i < array.length; i++) {
            boolean isNum = true;
            for (char c : array[i].toCharArray()) {
                if (c < '0' || c > '9') {
                    isNum = false;
                    break;
                }
            }
            count += isNum ? -1 : 1;
            counts[i] = count;
        }
        log.debug("counts;{}", counts);
        Map<Integer, Integer> map = new HashMap<>();
        int start = 0, end = 0;
        map.put(0, -1);
        for (int i = 0; i < counts.length; i++) {
            if (map.containsKey(counts[i])) {
                int first = map.get(counts[i]);
                if (i - first > end - start + 1) {
                    start = first + 1;
                    end = i + 1;
                }

            } else {
                map.put(counts[i], i);
            }
        }
        log.debug("start :{},end :{}", start, end);

        return Arrays.copyOfRange(array, start, end);
    }

    /**
     * 56. 合并区间 给出一个区间的集合，请合并所有重叠的区间。
     *
     * <p>示例 1:
     *
     * <p>输入: [[1,3],[2,6],[8,10],[15,18]] 输出: [[1,6],[8,10],[15,18]] 解释: 区间 [1,3] 和 [2,6] 重叠,
     * 将它们合并为 [1,6]. 示例 2:
     *
     * <p>输入: [[1,4],[4,5]] 输出: [[1,5]] 解释: 区间 [1,4] 和 [4,5] 可被视为重叠区间。
     *
     * @param intervals
     * @return
     */
    public int[][] merge3(int[][] intervals) {
        if (intervals.length < 2) {
            return intervals;
        }
        List<int[]> list = new ArrayList<>();

        Arrays.sort(intervals, (a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);

        int[] interval = intervals[0];
        for (int i = 1; i < intervals.length; i++) {
            int[] current = intervals[i];
            if (current[0] > interval[1]) {
                list.add(interval);
                interval = current;
            } else if (current[1] > interval[1]) {
                interval[1] = current[1];
            }
        }
        list.add(interval);

        return list.toArray(new int[list.size()][2]);
    }

    @Test
    public void testMerge3() {
        int[][] intervals = new int[][] {{1, 3}, {2, 6}, {8, 10}, {15, 18}};

        int[][] result = merge(intervals);
        logResult(result);
    }

    @Test
    public void testYangHui21() {
        int numRows = 7;
        List<List<Integer>> result = generate2(numRows);
        int index = 0;
        for (List<Integer> list : result) {
            index++;
            log.info("{}:{}", index, list);
        }
    }

    /**
     * 118. 杨辉三角 给定一个非负整数 numRows，生成杨辉三角的前 numRows 行。
     *
     * <p>在杨辉三角中，每个数是它左上方和右上方的数的和。
     *
     * <p>示例:
     *
     * <p>输入: 5
     *
     * <p>输出: [ [1], [1,1], [1,2,1], [1,3,3,1], [1,4,6,4,1] ]
     *
     * @param numRows
     * @return
     */
    public List<List<Integer>> generate2(int numRows) {
        List<List<Integer>> result = new ArrayList<>();
        if (numRows == 0) {
            return result;
        }
        int[] nums = new int[numRows];
        nums[0] = 1;
        for (int i = 0; i < numRows; i++) {
            nums[i] = 1;
            for (int j = i - 1; j > 0; j--) {
                nums[j] += nums[j - 1];
            }
            List<Integer> list = new ArrayList<>();
            for (int j = 0; j <= i; j++) {
                list.add(nums[j]);
            }
            result.add(list);
        }

        return result;
    }

    @Test
    public void testYangHui22() {
        int rowIndex = 5;
        List<Integer> result = getRow2(rowIndex);
        logResult(result);
    }

    /**
     * 119. 杨辉三角 II
     *
     * <p>给定一个非负索引 k，其中 k ≤ 33，返回杨辉三角的第 k 行。
     *
     * <p>在杨辉三角中，每个数是它左上方和右上方的数的和。
     *
     * <p>示例:
     *
     * <p>输入: 3 输出: [1,3,3,1] 进阶：
     *
     * <p>你可以优化你的算法到 O(k) 空间复杂度吗？
     *
     * @param rowIndex
     * @return
     */
    public List<Integer> getRow2(int rowIndex) {
        int[] nums = new int[rowIndex + 1];
        nums[0] = 1;
        for (int i = 0; i <= rowIndex; i++) {
            nums[i] = 1;
            for (int j = i - 1; j > 0; j--) {
                nums[j] += nums[j - 1];
            }
        }
        List<Integer> list = new ArrayList<>();
        for (int num : nums) {
            list.add(num);
        }
        return list;
    }

    @Test
    public void smallestDifference() {
        int[] a = {-2147483648, 1}, b = {2147483647, 0};
        logResult(smallestDifference(a, b));
    }

    /**
     * 面试题 16.06. 最小差
     *
     * <p>给定两个整数数组a和b，计算具有最小差绝对值的一对数值（每个数组中取一个值），并返回该对数值的差
     *
     * <p>示例：
     *
     * <p>输入：{1, 3, 15, 11, 2}, {23, 127, 235, 19, 8} 输出： 3，即数值对(11, 8) 提示：1 <= a.length, b.length
     * <= 100000 -2147483648 <= a[i], b[i] <= 2147483647 正确结果在区间[-2147483648, 2147483647]内
     *
     * @param a
     * @param b
     * @return
     */
    public int smallestDifference(int[] a, int[] b) {
        long min = Integer.MAX_VALUE;
        Arrays.sort(a);
        Arrays.sort(b);
        int left = 0, right = 0;

        while (left < a.length && right < b.length) {
            if (a[left] >= b[right]) {
                min = Math.min(min, (long) a[left] - (long) b[right]);
                right++;
            } else {
                min = Math.min(min, (long) b[right] - (long) a[left]);
                left++;
            }
        }

        return (int) min;
    }

    @Test
    public void rotate3() {
        int[][] matrix = {{5, 1, 9, 11}, {2, 4, 8, 10}, {13, 3, 6, 7}, {15, 14, 12, 16}};
        rotate3(matrix);
        logResult(matrix);
    }

    /**
     * 旋转矩阵
     *
     * <p>给你一幅由 N × N 矩阵表示的图像，其中每个像素的大小为 4 字节。请你设计一种算法，将图像旋转 90 度。
     *
     * <p>不占用额外内存空间能否做到？
     *
     * <p>示例 1:
     *
     * <p>给定 matrix = [ [1,2,3], [4,5,6], [7,8,9] ],
     *
     * <p>原地旋转输入矩阵，使其变为: [ [7,4,1], [8,5,2], [9,6,3] ] 示例 2:
     *
     * <p>给定 matrix = [ [ 5, 1, 9,11], [ 2, 4, 8,10], [13, 3, 6, 7], [15,14,12,16] ],
     *
     * <p>原地旋转输入矩阵，使其变为: [ [15,13, 2, 5], [14, 3, 4, 1], [12, 6, 8, 9], [16, 7,10,11] ]
     *
     * @param matrix
     */
    public void rotate3(int[][] matrix) {
        int n = matrix.length;
        if (n <= 1) {
            return;
        }
        for (int i = 0; i <= (n >> 1); i++) {
            for (int j = i; j < n - i - 1; j++) {
                //
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[n - 1 - j][i];
                matrix[n - 1 - j][i] = matrix[n - 1 - i][n - 1 - j];
                matrix[n - 1 - i][n - 1 - j] = matrix[j][n - 1 - i];
                matrix[j][n - 1 - i] = tmp;
            }
        }
    }

    @Test
    public void findDiagonalOrder3() {
        // int[][] matrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}, {10, 11, 12}, {13, 14, 15}};

        int[][] matrix = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}};
        // int[][] matrix = {};
        int[] result = findDiagonalOrder3(matrix);
        log.info("result:{}", result);
    }
    /**
     * 对角线遍历
     *
     * <p>给定一个含有 M x N 个元素的矩阵（M 行，N 列），请以对角线遍历的顺序返回这个矩阵中的所有元素，对角线遍历如下图所示。
     *
     * <p>示例:
     *
     * <p>输入: [ [ 1, 2, 3 ], [ 4, 5, 6 ], [ 7, 8, 9 ] ]
     *
     * <p>输出: [1,2,4,7,5,3,6,8,9]
     *
     * <p>解释:
     *
     * <p>说明:
     *
     * <p>给定矩阵中的元素总数不会超过 100000 。
     *
     * @param matrix
     * @return
     */
    public int[] findDiagonalOrder3(int[][] matrix) {
        int rows = matrix.length;
        if (rows == 0) {
            return new int[0];
        }

        int cols = matrix[0].length;
        if (cols == 0) {
            return new int[0];
        }
        int[] result = new int[rows * cols];
        int row = 0, col = 0;
        int index = 0;
        for (int i = 0; i < rows + cols - 1; i++) {
            if ((i & 1) == 0) {
                // 偶数  左下 -> 右上
                while (row >= 0 && col < cols) {
                    result[index++] = matrix[row--][col++];
                }
                if (col == cols) {
                    col--;
                    row += 2;
                } else if (row == -1) {
                    row = 0;
                }

            } else {
                // 奇数, 右上 -> 左下
                while (row < rows && col >= 0) {
                    result[index++] = matrix[row++][col--];
                }
                if (row == rows) {
                    row--;
                    col += 2;
                } else if (col == -1) {
                    col = 0;
                }
            }
        }

        /*Deque<Integer> rowStack = new LinkedList<>();
        Deque<Integer> colStack = new LinkedList<>();
        rowStack.push(0);
        colStack.push(0);
        while (!rowStack.isEmpty()) {
            int size = rowStack.size();

            for (int i = 0; i < size; i++) {
                int row = rowStack.pop();
                int col = colStack.pop();
            }
        }*/

        return result;
    }

    @Test
    public void smallestK() {
        int[] arr = {1, 3, 5, 7, 2, 4, 6, 8};
        int k = 4;

        log.debug("result:{}", smallestK(arr, k));
    }

    /**
     * 面试题 17.14. 最小K个数
     *
     * <p>设计一个算法，找出数组中最小的k个数。以任意顺序返回这k个数均可。
     *
     * <p>示例：
     *
     * <p>输入： arr = [1,3,5,7,2,4,6,8], k = 4 输出： [1,2,3,4] 提示：
     *
     * <p>0 <= len(arr) <= 100000 0 <= k <= min(100000, len(arr))
     *
     * @param arr
     * @param k
     * @return
     */
    public int[] smallestK(int[] arr, int k) {
        if (arr.length == 0 || k == 0) {
            return new int[0];
        }
        // 快速排序思路

        smallestK(arr, k - 1, 0, arr.length - 1);
        log.debug("arr:{}", arr);
        return Arrays.copyOf(arr, k);
    }

    private void smallestK(int[] arr, int k, int start, int end) {
        int left = start, right = end;
        int num = arr[left];
        // 选择主元 num, 从后往前找到第一个比num小的元素
        // 从前往后找到第一个比num大的元素
        while (left < right) {

            while (left < right && arr[right] >= num) {
                right--;
            }
            arr[left] = arr[right];
            while (left < right && arr[left] < num) {
                left++;
            }
            arr[right] = arr[left];
        }

        arr[left] = num;
        if (k < left) {
            smallestK(arr, k, start, left - 1);
        }
        if (k > left) {
            smallestK(arr, k, left + 1, end);
        }
    }

    @Test
    public void findSwapValues() {
        int[] array1 = {4, 1, 2, 1, 1, 2};
        int[] array2 = {3, 6, 3, 3};

        logResult(findSwapValues(array1, array2));
    }

    /**
     * 面试题 16.21. 交换和
     *
     * <p>给定两个整数数组，请交换一对数值（每个数组中取一个数值），使得两个数组所有元素的和相等。
     *
     * <p>返回一个数组，第一个元素是第一个数组中要交换的元素，第二个元素是第二个数组中要交换的元素。若有多个答案，返回任意一个均可。 若无满足条件的数值，返回空数组。
     *
     * <p>示例:
     *
     * <p>输入: array1 = [4, 1, 2, 1, 1, 2], array2 = [3, 6, 3, 3] 输出: [1, 3] 示例:
     *
     * <p>输入: array1 = [1, 2, 3], array2 = [4, 5, 6] 输出: [] 提示：
     *
     * <p>1 <= array1.length, array2.length <= 100000
     *
     * @param array1
     * @param array2
     * @return
     */
    public int[] findSwapValues(int[] array1, int[] array2) {
        int sum1 = 0, sum2 = 0;
        Set<Integer> set = new HashSet<>();
        for (int num : array1) {
            sum1 += num;
        }
        for (int num : array2) {
            sum2 += num;
            set.add(num);
        }
        int sum = sum1 + sum2;
        if ((sum & 1) == 1) {
            return new int[0];
        }
        int helf = sum >> 1;

        int diff = helf - sum1;
        boolean flag = false;
        int[] result = new int[2];
        for (int num : array1) {

            if (set.contains(num + diff)) {
                flag = true;
                result[0] = num;
                result[1] = num + diff;
                break;
            }
        }

        return flag ? result : new int[0];
    }

    /**
     * 面试题 16.24. 数对和
     *
     * <p>设计一个算法，找出数组中两数之和为指定值的所有整数对。一个数只能属于一个数对。
     *
     * <p>示例 1:
     *
     * <p>输入: nums = [5,6,5], target = 11 输出: [[5,6]] 示例 2:
     *
     * <p>输入: nums = [5,6,5,6], target = 11 输出: [[5,6],[5,6]] 提示：
     *
     * <p>nums.length <= 100000
     *
     * @param nums
     * @param target
     * @return
     */
    public List<List<Integer>> pairSums(int[] nums, int target) {
        /*// HashMap 存储元素次数,遍历
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            int count = map.getOrDefault(num, 0);
            map.put(num, count + 1);
        }
        List<List<Integer>> result = new ArrayList<>();

        for (int k : map.keySet()) {
            // 相等的情况
            if (k == target - k) {
                int count = map.get(k) >> 1;
                List<Integer> list = Arrays.asList(k, k);
                for (int i = 0; i < count; i++) {
                    if (i == 0) {
                        result.add(list);
                    } else {
                        result.add(new ArrayList<>(list));
                    }
                }
                map.put(k, 0);
                continue;
            }
            int count1 = map.get(k), count2 = map.getOrDefault(target - k, 0);
            if (count2 > 0) {
                int min = Math.min(count1, count2);
                List<Integer> list = Arrays.asList(k, target - k);
                for (int i = 0; i < min; i++) {
                    if (i == 0) {
                        result.add(list);
                    } else {
                        result.add(new ArrayList<>(list));
                    }
                }
                map.put(k, count1 - min);
                map.put(target - k, count2 - min);
            }
        }

        return result;*/

        List<List<Integer>> result = new ArrayList<>();
        // 排序双指针
        Arrays.sort(nums);
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int num1 = nums[left], num2 = nums[right];
            int sum = num1 + num2;
            if (sum == target) {
                result.add(Arrays.asList(num1, num2));
                left++;
                right--;
            } else if (sum > target) {
                right--;
            } else {
                left++;
            }
        }

        return result;
    }

    @Test
    public void wiggleSort() {
        int[] nums = {5, 3, 1, 2, 3};
        wiggleSort(nums);
        log.debug("nums:{}", nums);
    }

    /**
     * 面试题 10.11. 峰与谷
     *
     * <p>在一个整数数组中，“峰”是大于或等于相邻整数的元素，相应地，“谷”是小于或等于相邻整数的元素。
     *
     * <p>例如，在数组{5, 8, 6, 2, 3, 4, 6}中，{8, 6}是峰， {5, 2}是谷。现在给定一个整数数组，将该数组按峰与谷的交替顺序排序。
     *
     * <p>示例:
     *
     * <p>输入: [5, 3, 1, 2, 3] 输出: [5, 1, 3, 2, 3] 提示：
     *
     * <p>nums.length <= 10000
     *
     * @param nums
     */
    public void wiggleSort(int[] nums) {
        if (nums.length < 2) {
            return;
        }
        int[] newNums = Arrays.copyOf(nums, nums.length);
        Arrays.sort(newNums);

        int i = 0, right = nums.length - 1, left = right >> 1;
        nums[i++] = newNums[left--];
        while (true) {
            if (i < nums.length) {
                nums[i++] = newNums[right--];
            } else break;
            if (i < nums.length) {
                nums[i++] = newNums[left--];
            } else break;
        }
        logResult(nums);
    }

    /**
     * 5428. 重新排列数组
     *
     * <p>给你一个数组 nums ，数组中有 2n 个元素，按 [x1,x2,...,xn,y1,y2,...,yn] 的格式排列。
     *
     * <p>请你将数组按 [x1,y1,x2,y2,...,xn,yn] 格式重新排列，返回重排后的数组。
     *
     * <p>示例 1：
     *
     * <p>输入：nums = [2,5,1,3,4,7], n = 3 输出：[2,3,5,4,1,7] 解释：由于 x1=2, x2=5, x3=1, y1=3, y2=4, y3=7
     * ，所以答案为 [2,3,5,4,1,7] 示例 2：
     *
     * <p>输入：nums = [1,2,3,4,4,3,2,1], n = 4 输出：[1,4,2,3,3,2,4,1] 示例 3：
     *
     * <p>输入：nums = [1,1,2,2], n = 2 输出：[1,2,1,2]
     *
     * <p>提示：
     *
     * <p>1 <= n <= 500 nums.length == 2n 1 <= nums[i] <= 10^3
     *
     * @param nums
     * @param n
     * @return
     */
    public int[] shuffle(int[] nums, int n) {
        int[] result = new int[nums.length];
        for (int i = 0; i < n; i++) {
            result[i << 1] = nums[i];
            result[(i << 1) + 1] = nums[n + i];
        }

        return result;
    }

    @Test
    public void shuffle() {
        int[] nums = {1, 2, 3, 4, 4, 3, 2, 1};
        int n = 4;
        int[] result = shuffle(nums, n);
        log.debug("result:{}", result);
    }

    @Test
    public void getStrongest() {
        int[] arr = {1, 1, 3, 5, 5};
        int k = 2;
        int[] result = getStrongest(arr, k);
        log.debug("result :{}", result);
    }

    /**
     * 5429. 数组中的 k 个最强值
     *
     * <p>给你一个整数数组 arr 和一个整数 k 。
     *
     * <p>设 m 为数组的中位数，只要满足下述两个前提之一，就可以判定 arr[i] 的值比 arr[j] 的值更强：
     *
     * <p>|arr[i] - m| > |arr[j] - m| |arr[i] - m| == |arr[j] - m|，且 arr[i] > arr[j] 请返回由数组中最强的 k
     * 个值组成的列表。答案可以以 任意顺序 返回。
     *
     * <p>中位数 是一个有序整数列表中处于中间位置的值。形式上，如果列表的长度为 n ，那么中位数就是该有序列表（下标从 0 开始）中位于 ((n - 1) / 2) 的元素。
     *
     * <p>例如 arr = [6, -3, 7, 2, 11]，n = 5：数组排序后得到 arr = [-3, 2, 6, 7, 11] ，数组的中间位置为 m = ((5 - 1) /
     * 2) = 2 ，中位数 arr[m] 的值为 6 。 例如 arr = [-7, 22, 17, 3]，n = 4：数组排序后得到 arr = [-7, 3, 17, 22]
     * ，数组的中间位置为 m = ((4 - 1) / 2) = 1 ，中位数 arr[m] 的值为 3 。
     *
     * <p>示例 1：
     *
     * <p>输入：arr = [1,2,3,4,5], k = 2 输出：[5,1] 解释：中位数为 3，按从强到弱顺序排序后，数组变为 [5,1,4,2,3]。最强的两个元素是 [5,
     * 1]。[1, 5] 也是正确答案。 注意，尽管 |5 - 3| == |1 - 3| ，但是 5 比 1 更强，因为 5 > 1 。 示例 2：
     *
     * <p>输入：arr = [1,1,3,5,5], k = 2 输出：[5,5] 解释：中位数为 3, 按从强到弱顺序排序后，数组变为 [5,5,1,1,3]。最强的两个元素是 [5,
     * 5]。 示例 3：
     *
     * <p>输入：arr = [6,7,11,7,6,8], k = 5 输出：[11,8,6,6,7] 解释：中位数为 7, 按从强到弱顺序排序后，数组变为 [11,8,6,6,7,7]。
     * [11,8,6,6,7] 的任何排列都是正确答案。 示例 4：
     *
     * <p>输入：arr = [6,-3,7,2,11], k = 3 输出：[-3,11,2] 示例 5：
     *
     * <p>输入：arr = [-7,22,17,3], k = 2 输出：[22,17]
     *
     * <p>提示：
     *
     * <p>1 <= arr.length <= 10^5 -10^5 <= arr[i] <= 10^5 1 <= k <= arr.length
     *
     * @param arr
     * @param k
     * @return
     */
    public int[] getStrongest(int[] arr, int k) {
        Arrays.sort(arr);
        int half = (arr.length - 1) >> 1;
        int m = arr[half];
        int left = 0, right = arr.length - 1;
        int index = 0;
        int[] result = new int[k];
        while (index < k) {
            int num;
            if (arr[right] - m == m - arr[left] || arr[right] - m > m - arr[left]) {
                num = arr[right--];
            } else {
                num = arr[left++];
            }

            result[index++] = num;
        }
        return result;
        /* Comparator<Integer> comparator =
                (a, b) -> {
                    if (Math.abs(a - m) == Math.abs(b - m)) {
                        return a - b;
                    }
                    return Math.abs(a - m) - Math.abs(b - m);
                };

        PriorityQueue<Integer> queue = new PriorityQueue<>(k, comparator);

        for (int num : arr) {
            if (queue.size() == k) {
                logResult(queue);
                log.debug("peek:{}", queue.peek());
                if (comparator.compare(num, queue.peek()) > 0) {
                    queue.poll();
                } else {
                    continue;
                }
            }
            queue.offer(num);
        }
        logResult(queue);
        int index = 0;
        int[] result = new int[k];
        for (Iterator<Integer> iterator = queue.iterator(); iterator.hasNext(); ) {
            result[index++] = iterator.next();
        }
        return result;*/
    }

    @Test
    public void trulyMostPopular() {
        String[] names = {};
        String[] synonyms = {};
        logResult(trulyMostPopular(names, synonyms));
    }

    /**
     * 面试题 17.07. 婴儿名字
     *
     * <p>每年，政府都会公布一万个最常见的婴儿名字和它们出现的频率，也就是同名婴儿的数量。有些名字有多种拼法，例如，John 和 Jon
     * 本质上是相同的名字，但被当成了两个名字公布出来。给定两个列表，一个是名字及对应的频率，另一个是本质相同的名字对。设计一个算法打印出每个真实名字的实际频率。注意，如果 John 和 Jon
     * 是相同的，并且 Jon 和 Johnny 相同，则 John 与 Johnny 也相同，即它们有传递和对称性。
     *
     * <p>在结果列表中，选择字典序最小的名字作为真实名字。
     *
     * <p>示例：
     *
     * <p>输入：names = ["John(15)","Jon(12)","Chris(13)","Kris(4)","Christopher(19)"], synonyms =
     * ["(Jon,John)","(John,Johnny)","(Chris,Kris)","(Chris,Christopher)"]
     * 输出：["John(27)","Chris(36)"] 提示：
     *
     * <p>names.length <= 100000
     *
     * @param names
     * @param synonyms
     * @return
     */
    public String[] trulyMostPopular(String[] names, String[] synonyms) {
        Map<String, String> map = new HashMap<>();
        for (String synonym : synonyms) {
            int index = synonym.indexOf(',');
            union(
                    map,
                    synonym.substring(1, index),
                    synonym.substring(index + 1, synonym.length() - 1));
        }
        logResult(map);
        Map<String, Integer> countMap = new HashMap<>();
        for (String name : names) {

            int index = name.indexOf('(');
            String minName = getMinName(map, name.substring(0, index));
            int count = countMap.getOrDefault(minName, 0);
            int curCount = Integer.parseInt(name.substring(index + 1, name.length() - 1));
            countMap.put(minName, count + curCount);
        }
        logResult(countMap);
        String[] result = new String[countMap.size()];
        StringBuilder sb = new StringBuilder();
        int index = 0;
        for (String key : countMap.keySet()) {
            sb.setLength(0);
            sb.append(key).append('(').append(countMap.get(key)).append(')');
            result[index++] = sb.toString();
        }

        return result;
    }

    private void union(Map<String, String> map, String name1, String name2) {

        String root1 = getMinName(map, name1);
        String root2 = getMinName(map, name2);
        if (Objects.equals(root1, root2)) {
            return;
        }
        if (root1.compareTo(root2) < 0) {
            map.put(root2, root1);
        } else {
            map.put(root1, root2);
        }
    }

    private String getMinName(Map<String, String> map, String name) {

        if (map.containsKey(name)) {
            String rootName = map.get(name);
            if (Objects.equals(rootName, name)) {
                return rootName;
            }
            return getMinName(map, rootName);
        }
        map.put(name, name);
        return name;
    }

    @Test
    public void pondSizes() {
        int[][] land = {{0, 2, 1, 0}, {0, 1, 0, 1}, {1, 1, 0, 1}, {0, 1, 0, 1}};
        int[] result = pondSizes(land);
        log.debug("result:{}", result);
    }

    /**
     * 面试题 16.19. 水域大小
     *
     * <p>你有一个用于表示一片土地的整数矩阵land，该矩阵中每个点的值代表对应地点的海拔高度。若值为0则表示水域。由垂直、水平或对角连接的水域为池塘。
     *
     * <p>池塘的大小是指相连接的水域的个数。编写一个方法来计算矩阵中所有池塘的大小，返回值需要从小到大排序。
     *
     * <p>示例：
     *
     * <p>输入： [ [0,2,1,0], [0,1,0,1], [1,1,0,1], [0,1,0,1] ] 输出： [1,2,4] 提示：
     *
     * <p>0 < len(land) <= 1000 0 < len(land[i]) <= 1000
     *
     * @param land
     * @return
     */
    public int[] pondSizes(int[][] land) {
        List<Integer> list = new ArrayList<>();
        int rows = land.length;
        if (rows == 0) {
            return new int[0];
        }
        int cols = land[0].length;
        if (cols == 0) {
            return new int[0];
        }
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (land[i][j] != 0) {
                    continue;
                }
                list.add(getPondSizes(land, i, j));
                logResult(list);
            }
        }
        int[] result = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            result[i] = list.get(i);
        }
        Arrays.sort(result);
        return result;
    }

    private int getPondSizes(int[][] land, int row, int col) {
        int result = 0;
        if (!inArea(row, col, land.length, land[0].length)) {
            return 0;
        }
        if (land[row][col] != 0) {
            return 0;
        }
        result++;
        land[row][col] = -1;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i == 0 && j == 0) {
                    continue;
                }
                result += getPondSizes(land, row + i, col + j);
            }
        }
        return result;
    }

    @Test
    public void drawLine() {
        int length = 1, w = 32, x1 = 22, x2 = 31, y = 0;
        int[] result = drawLine(length, w, x1, x2, y);
        log.debug("result:{}", result);
    }

    /**
     * 面试题 05.08. 绘制直线
     *
     * <p>绘制直线。有个单色屏幕存储在一个一维数组中，使得32个连续像素可以存放在一个 int 里。屏幕宽度为w，且w可被32整除（即一个 int
     * 不会分布在两行上），屏幕高度可由数组长度及屏幕宽度推算得出。请实现一个函数，绘制从点(x1, y)到点(x2, y)的水平线。
     *
     * <p>给出数组的长度 length，宽度 w（以比特为单位）、直线开始位置 x1（比特为单位）、直线结束位置 x2（比特为单位）、直线所在行数 y。返回绘制过后的数组。
     *
     * <p>示例1:
     *
     * <p>输入：length = 1, w = 32, x1 = 30, x2 = 31, y = 0 输出：[3]
     * 说明：在第0行的第30位到第31为画一条直线，屏幕表示为[0b000000000000000000000000000000011] 示例2:
     *
     * <p>输入：length = 3, w = 96, x1 = 0, x2 = 95, y = 0 输出：[-1, -1, -1]
     *
     * @param length
     * @param w
     * @param x1
     * @param x2
     * @param y
     * @return
     */
    public int[] drawLine(int length, int w, int x1, int x2, int y) {
        int[] result = new int[length];

        int low = (y * w + x1) / 32;
        int high = (y * w + x2) / 32;
        // 中间的全是 -1;
        for (int i = low; i <= high; i++) {
            result[i] = -1;
        }
        log.debug("low :{} high:{}", low, high);
        int startCount = x1 % 32;
        int endCount = x2 % 32;
        log.debug("startCount :{} endCount:{}", startCount, endCount);
        result[low] &= (-1 >>> startCount);
        result[high] &= (-1 << (31 - endCount));
        /*for (int i = 0; i < length; i++) {
            if (x1 > 31 + 32 * i) {
                continue;
            }
            int start = Math.max(32 * i, x1);
            int end = Math.min(31 + 32 * i, x2);

            start -= 32 * i;
            end -= 32 * i;
            int num1 = -1;
            for (int j = end; j >= start; j--) {}

            for (int j = 0; j < start; j++) {
                num1 = num1 >> 1;
            }
            int num2 = -1;
            for (int j = end; j < 31; j++) {
                num2 <<= 1;
            }
            log.debug("num1:{}  num2 : {}", num1, num2);
            result[i] = num1 & num2;
        }*/

        return result;
    }

    @Test
    public void subSort() {
        int[] array = {1, 2, 4, 7, 10, 11, 7, 12, 6, 7, 16, 18, 19};
        int[] result = subSort(array);

        log.debug("result:{}", result);
    }

    /**
     * 面试题 16.16. 部分排序
     *
     * <p>给定一个整数数组，编写一个函数，找出索引m和n，只要将索引区间[m,n]的元素排好序，整个数组就是有序的。
     *
     * <p>注意：n-m尽量最小，也就是说，找出符合条件的最短序列。函数返回值为[m,n]，若不存在这样的m和n（例如整个数组是有序的），请返回[-1,-1]。
     *
     * <p>示例：
     *
     * <p>输入： [1,2,4,7,10,11,7,12,6,7,16,18,19] 输出： [3,9] 提示：
     *
     * <p>0 <= len(array) <= 1000000
     *
     * @param array
     * @return
     */
    public int[] subSort(int[] array) {
        int[] result = new int[2];
        if (array.length == 0) {
            return new int[] {-1, -1};
        }
        int left = -1, right = -1;
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        int len = array.length;
        for (int i = 0; i < array.length; i++) {
            if (array[i] < max) {
                right = i;
            } else {
                max = Math.max(max, array[i]);
            }

            if (array[len - 1 - i] > min) {
                left = len - 1 - i;
            } else {
                min = Math.min(min, array[len - 1 - i]);
            }
        }

        /*int left = 0, right = array.length - 1;
        while (left < right) {
            if (array[left] >= array[right]) {
                break;
            }
            if (array[left] > array[left + 1] && array[right - 1] > array[right]) {
                break;
            }
            if (array[left + 1] >= array[left] && array[left] < array[right]) {
                left++;
            }
            if (array[right - 1] <= array[right] && array[left] < array[right]) {
                right--;
            }
        }
        if (left >= right) {
            return new int[] {-1, -1};
        }*/
        result[0] = left;
        result[1] = right;
        log.debug("leftVal:{},rightVal:{}", array[left], array[right]);
        log.debug("left:{},right:{}", left, right);

        return result;
    }

    @Test
    public void threeSum() {
        int[] nums = {-2, 0, 0, 2, 2};
        List<List<Integer>> result = threeSum(nums);
        logResult(result);
    }

    /**
     * 15. 三数之和
     *
     * <p>给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有满足条件且不重复的三元组。
     *
     * <p>注意：答案中不可以包含重复的三元组。
     *
     * <p>示例：
     *
     * <p>给定数组 nums = [-1, 0, 1, 2, -1, -4]，
     *
     * <p>满足要求的三元组集合为： [ [-1, 0, 1], [-1, -1, 2] ]
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i++) {
            // 选择一个元素作为主元
            int num = nums[i];
            if (nums[i] > 0) {
                break;
            }
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            int left = i + 1, right = nums.length - 1;
            while (left < right) {
                if (left > i + 1 && nums[left] == nums[left - 1]) {
                    left++;
                    continue;
                }
                int sum = nums[left] + nums[right];
                if (sum == -num) {
                    List<Integer> list = new ArrayList<>();
                    list.add(num);
                    list.add(nums[left]);
                    list.add(nums[right]);
                    result.add(list);
                    left++;
                    right--;
                } else if (sum > -num) {
                    right--;
                } else {
                    left++;
                }
            }
        }
        return result;
    }

    /**
     * 面试题 16.04. 井字游戏
     *
     * <p>设计一个算法，判断玩家是否赢了井字游戏。输入是一个 N x N 的数组棋盘，由字符" "，"X"和"O"组成，其中字符" "代表一个空位。
     *
     * <p>以下是井字游戏的规则：
     *
     * <p>玩家轮流将字符放入空位（" "）中。 第一个玩家总是放字符"O"，且第二个玩家总是放字符"X"。 "X"和"O"只允许放置在空位中，不允许对已放有字符的位置进行填充。
     * 当有N个相同（且非空）的字符填充任何行、列或对角线时，游戏结束，对应该字符的玩家获胜。 当所有位置非空时，也算为游戏结束。 如果游戏结束，玩家不允许再放置字符。
     * 如果游戏存在获胜者，就返回该游戏的获胜者使用的字符（"X"或"O"）；如果游戏以平局结束，则返回 "Draw"；如果仍会有行动（游戏未结束），则返回 "Pending"。
     *
     * <p>示例 1：
     *
     * <p>输入： board = ["O X"," XO","X O"] 输出： "X" 示例 2：
     *
     * <p>输入： board = ["OOX","XXO","OXO"] 输出： "Draw" 解释： 没有玩家获胜且不存在空位 示例 3：
     *
     * <p>输入： board = ["OOX","XXO","OX "] 输出： "Pending" 解释： 没有玩家获胜且仍存在空位 提示：
     *
     * <p>1 <= board.length == board[i].length <= 100 输入一定遵循井字棋规则
     *
     * @param board
     * @return
     */
    public String tictactoe2(String[] board) {

        int n = board.length;

        char[][] chars = new char[n][n];
        for (int i = 0; i < board.length; i++) {
            chars[i] = board[i].toCharArray();
        }
        // 检查所有行,所有列 和对角线
        int blankCount = 0;
        // 检查所有行
        for (int i = 0; i < n; i++) {
            char c = chars[i][0];
            if (c == ' ') {
                blankCount++;
                continue;
            }
            boolean flag = true;

            for (int j = 1; j < n; j++) {
                if (chars[i][j] != c) {
                    flag = false;
                }
                if (chars[i][j] == ' ') {
                    blankCount++;
                }
            }

            if (flag) {
                return String.valueOf(c);
            }
        }
        // 所有列
        for (int j = 0; j < n; j++) {
            char c = chars[0][j];
            if (c == ' ') {
                blankCount++;
                continue;
            }
            boolean flag = true;

            for (int i = 1; i < n; i++) {
                if (chars[i][j] != c) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                return String.valueOf(c);
            }
        }
        // 对角线
        char c = chars[0][0];
        if (c != ' ') {
            boolean flag = true;

            for (int i = 1; i < n; i++) {
                if (chars[i][i] != c) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                return String.valueOf(c);
            }
        }
        c = chars[0][n - 1];
        if (c != ' ') {
            boolean flag = true;
            for (int i = 1; i < n; i++) {
                if (chars[i][n - 1 - i] != c) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                return String.valueOf(c);
            }
        }

        if (blankCount > 0) {
            return "Pending";
        }
        return "Draw";
        /*int step = 0;
        int[] winList = {7, 56, 448, 73, 146, 292, 273, 84};
        int x = 0, o = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length(); j++) {
                int k = i * 3 + j;
                if (board[i].charAt(j) == 'X') {
                    x += 1 << k;
                    step++;
                }
                if (board[i].charAt(j) == 'O') {
                    o += 1 << k;
                    step++;
                }
            }
        }
        if (step < 5) {
            return "Pending";
        }
        for (int win : winList) {
            if ((x & win) == win ) {
                return "X";
            }
            if ((o & win) == win ) {
                return "O";
            }
        }
        if (step < ) {

        }*/
    }

    /**
     * 面试题 17.09. 第 k 个数
     *
     * <p>有些数的素因子只有 3，5，7，请设计一个算法找出第 k 个数。注意，不是必须有这些素因子，而是必须不包含其他的素因子。例如，前几个数按顺序应该是 1，3，5，7，9，15，21。
     *
     * <p>示例 1:
     *
     * <p>输入: k = 5
     *
     * <p>输出: 9
     *
     * @param k
     * @return
     */
    public int getKthMagicNumber(int k) {
        int idx1 = 0, idx2 = 0, idx3 = 0;
        int[] nums = new int[k];
        nums[0] = 1;
        for (int i = 1; i < k; i++) {
            nums[i] = Math.min(nums[idx1] * 3, Math.min(nums[idx2] * 5, nums[idx3] * 7));
            if (nums[i] == nums[idx1] * 3) {
                idx1++;
            }
            if (nums[i] == nums[idx2] * 5) {
                idx2++;
            }
            if (nums[i] == nums[idx3] * 7) {
                idx3++;
            }
        }

        return nums[k - 1];
    }

    @Test
    public void finalPrices() {
        int[] prices = {10, 1, 1, 1};
        int[] result = finalPrices(prices);
        log.debug("result:{}", result);
    }

    /**
     * 5420. 商品折扣后的最终价格
     *
     * <p>给你一个数组 prices ，其中 prices[i] 是商店里第 i 件商品的价格。
     *
     * <p>商店里正在进行促销活动，如果你要买第 i 件商品，那么你可以得到与 prices[j] 相等的折扣，其中 j 是满足 j > i 且 prices[j] <= prices[i]
     * 的 最小下标 ，如果没有满足条件的 j ，你将没有任何折扣。
     *
     * <p>请你返回一个数组，数组中第 i 个元素是折扣后你购买商品 i 最终需要支付的价格。
     *
     * <p>示例 1：
     *
     * <p>输入：prices = [8,4,6,2,3] 输出：[4,2,4,2,3] 解释： 商品 0 的价格为 price[0]=8 ，你将得到 prices[1]=4
     * 的折扣，所以最终价格为 8 - 4 = 4 。 商品 1 的价格为 price[1]=4 ，你将得到 prices[3]=2 的折扣，所以最终价格为 4 - 2 = 2 。 商品 2
     * 的价格为 price[2]=6 ，你将得到 prices[3]=2 的折扣，所以最终价格为 6 - 2 = 4 。 商品 3 和 4 都没有折扣。 示例 2：
     *
     * <p>输入：prices = [1,2,3,4,5] 输出：[1,2,3,4,5] 解释：在这个例子中，所有商品都没有折扣。 示例 3：
     *
     * <p>输入：prices = [10,1,1,6] 输出：[9,0,1,6]
     *
     * <p>提示：
     *
     * <p>1 <= prices.length <= 500 1 <= prices[i] <= 10^3
     *
     * @param prices
     * @return
     */
    public int[] finalPrices(int[] prices) {
        int len = prices.length;
        int[] result = new int[len];
        Deque<Integer> stack = new LinkedList<>();
        stack.push(0);

        for (int i = 1; i < len; i++) {
            int price = prices[i];
            while (!stack.isEmpty() && prices[stack.peek()] >= price) {
                result[stack.peek()] = prices[stack.peek()] - price;
                stack.pop();
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            int index = stack.pop();
            result[index] = prices[index];
        }

        return result;
    }

    @Test
    public void minSumOfLengths() {
        int[] arr = {31, 1, 1, 18, 15, 3, 15, 14};
        int target = 33;
        logResult(minSumOfLengths(arr, target));
    }

    /**
     * 5423. 找两个和为目标值且不重叠的子数组
     *
     * <p>给你一个整数数组 arr 和一个整数值 target 。
     *
     * <p>请你在 arr 中找 两个互不重叠的子数组 且它们的和都等于 target 。可能会有多种方案，请你返回满足要求的两个子数组长度和的 最小值 。
     *
     * <p>请返回满足要求的最小长度和，如果无法找到这样的两个子数组，请返回 -1 。
     *
     * <p>示例 1：
     *
     * <p>输入：arr = [3,2,2,4,3], target = 3 输出：2 解释：只有两个子数组和为 3 （[3] 和 [3]）。它们的长度和为 2 。 示例 2：
     *
     * <p>输入：arr = [7,3,4,7], target = 7 输出：2 解释：尽管我们有 3 个互不重叠的子数组和为 7 （[7], [3,4] 和
     * [7]），但我们会选择第一个和第三个子数组，因为它们的长度和 2 是最小值。 示例 3：
     *
     * <p>输入：arr = [4,3,2,6,2,3,4], target = 6 输出：-1 解释：我们只有一个和为 6 的子数组。 示例 4：
     *
     * <p>输入：arr = [5,5,4,4,5], target = 3 输出：-1 解释：我们无法找到和为 3 的子数组。 示例 5：
     *
     * <p>输入：arr = [3,1,1,1,5,1,2,1], target = 3 输出：3 解释：注意子数组 [1,2] 和 [2,1] 不能成为一个方案因为它们重叠了。
     *
     * <p>提示：
     *
     * <p>1 <= arr.length <= 10^5 1 <= arr[i] <= 1000 1 <= target <= 10^8
     *
     * @param arr
     * @param target
     * @return
     */
    public int minSumOfLengths(int[] arr, int target) {
        List<int[]> list = new ArrayList<>();
        // 先用滑动窗口求和为target的序列
        int left = 0, right = 0;
        int sum = 0;
        while (left <= right && right <= arr.length) {

            if (sum == target) {
                int[] child = {left, right - left};
                list.add(child);
                sum -= arr[left];
                left++;
            } else if (sum > target) {
                sum -= arr[left];
                left++;
            } else {
                if (right < arr.length) {
                    sum += arr[right];
                    right++;
                } else {
                    break;
                }
            }
        }

        if (list.size() < 2) {
            return -1;
        }
        Collections.sort(list, (a, b) -> a[1] - b[1] == 0 ? a[0] - b[0] : a[1] - b[1]);
        for (int i = 0; i < list.size(); i++) {
            log.debug("{} :{}", i, list.get(i));
        }
        // 判断有没有重合

        int[] pair1 = list.get(0);
        int result = pair1[1];
        boolean flag = false;
        for (int i = 1; i < list.size(); i++) {
            int[] pair = list.get(i);
            // 重合
            if (pair1[0] < pair[0] && pair1[0] + pair1[1] > pair[0]) {
                continue;
            }
            if (pair1[0] > pair[0] && pair[0] + pair[1] > pair1[0]) {
                continue;
            }
            flag = true;
            result += pair[1];
            break;
        }

        return flag ? result : -1;
    }

    @Test
    public void findBestValue() {
        int[] arr = {2, 3, 5};
        int target = 11;
        logResult(findBestValue(arr, target));
    }

    /**
     * 1300. 转变数组后最接近目标值的数组和
     *
     * <p>给你一个整数数组 arr 和一个目标值 target ，请你返回一个整数 value ，使得将数组中所有大于 value 的值变成 value 后，数组的和最接近 target
     * （最接近表示两者之差的绝对值最小）。
     *
     * <p>如果有多种使得和最接近 target 的方案，请你返回这些整数中的最小值。
     *
     * <p>请注意，答案不一定是 arr 中的数字。
     *
     * <p>示例 1：
     *
     * <p>输入：arr = [4,9,3], target = 10 输出：3 解释：当选择 value 为 3 时，数组会变成 [3, 3, 3]，和为 9 ，这是最接近 target
     * 的方案。 示例 2：
     *
     * <p>输入：arr = [2,3,5], target = 10 输出：5 示例 3：
     *
     * <p>输入：arr = [60864,25176,27249,21296,20204], target = 56803 输出：11361
     *
     * <p>提示：
     *
     * <p>1 <= arr.length <= 10^4 1 <= arr[i], target <= 10^5
     *
     * @param arr
     * @param target
     * @return
     */
    public int findBestValue(int[] arr, int target) {
        // 排序
        Arrays.sort(arr);
        // 确定区间 left ~ right
        int pre = 0;
        int len = arr.length;
        int index = -1;
        for (int i = 0; i < len; i++) {
            int num = arr[i];
            int sum = pre + num * (len - i);
            if (sum == target) {
                return num;
            }
            if (sum > target) {
                index = i;
                break;
            }
            pre += num;
        }
        log.debug("arr:{}", arr);
        // index == -1 说明总和 < target
        if (index == -1) {
            return arr[len - 1];
        }

        int sum = target - pre;
        log.debug("index:{} sum :{} ,pre :{}", index, sum, pre);
        int indexLen = len - index;
        // 结果在 index - 1 和 index 之间 直接计算
        int result = sum / indexLen;
        log.debug("result:{} ", result);
        if ((result + 1) * indexLen - sum < sum - result * indexLen) {
            return result + 1;
        }

        return result;
    }

    /**
     * 5436. 一维数组的动态和
     *
     * <p>给你一个数组 nums 。数组「动态和」的计算公式为：runningSum[i] = sum(nums[0]…nums[i]) 。
     *
     * <p>请返回 nums 的动态和。
     *
     * <p>示例 1：
     *
     * <p>输入：nums = [1,2,3,4] 输出：[1,3,6,10] 解释：动态和计算过程为 [1, 1+2, 1+2+3, 1+2+3+4] 。 示例 2：
     *
     * <p>输入：nums = [1,1,1,1,1] 输出：[1,2,3,4,5] 解释：动态和计算过程为 [1, 1+1, 1+1+1, 1+1+1+1, 1+1+1+1+1] 。 示例
     * 3：
     *
     * <p>输入：nums = [3,1,2,10,1] 输出：[3,4,6,16,17]
     *
     * <p>提示：
     *
     * <p>1 <= nums.length <= 1000 -10^6 <= nums[i] <= 10^6
     *
     * @param nums
     * @return
     */
    public int[] runningSum(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            nums[i] += nums[i - 1];
        }
        return nums;
    }

    @Test
    public void findLeastNumOfUniqueInts() {
        int[] arr = {5, 5, 4};
        int k = 1;
        logResult(findLeastNumOfUniqueInts(arr, k));
    }

    /**
     * 5437. 不同整数的最少数目
     *
     * <p>给你一个整数数组 arr 和一个整数 k 。现需要从数组中恰好移除 k 个元素，请找出移除后数组中不同整数的最少数目。
     *
     * <p>示例 1：
     *
     * <p>输入：arr = [5,5,4], k = 1 输出：1 解释：移除 1 个 4 ，数组中只剩下 5 一种整数。 示例 2：
     *
     * <p>输入：arr = [4,3,1,1,3,3,2], k = 3 输出：2 解释：先移除 4、2 ，然后再移除两个 1 中的任意 1 个或者三个 3 中的任意 1 个，最后剩下 1
     * 和 3 两种整数。
     *
     * <p>提示：
     *
     * <p>1 <= arr.length <= 10^5 1 <= arr[i] <= 10^9 0 <= k <= arr.length
     *
     * @param arr
     * @param k
     * @return
     */
    public int findLeastNumOfUniqueInts(int[] arr, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : arr) {
            map.compute(num, (key, v) -> Objects.isNull(v) ? 1 : v + 1);
        }
        List<Integer> list = new ArrayList<>(map.keySet());
        Collections.sort(list, Comparator.comparingInt(map::get));
        int i = 0;
        for (; i < list.size(); i++) {
            int count = map.get(list.get(i));
            if (k >= count) {
                k -= count;
            } else {
                break;
            }
        }

        return list.size() - i;
    }

    @Test
    public void minDays() {
        int[] bloomDay = {
            1542, 5142, 4695, 4385, 2629, 2492, 933, 1068, 151, 3960, 3790, 1196, 3842, 5147, 5526,
            5528, 2259, 1708, 2714, 5462, 3016, 3262, 1175, 4348, 4826, 80, 789, 5285, 3855, 3455,
            3480, 4277, 648, 1748, 625, 4256, 3931, 4938, 4553, 2129, 4480, 824, 3048, 2383, 3036,
            2192, 2156, 7, 438, 5258, 2430, 2459, 3769, 1694, 1687, 5130, 70, 3219, 4140, 2341,
            1159, 3952, 4934, 4335, 2786, 3124, 5344, 803, 4586, 1000, 2821, 4769, 629, 4673, 3920,
            3437, 4533, 2984, 3576, 5364, 1255, 1876, 2309, 5619, 2402, 1978, 4127, 1668, 147, 4139,
            292, 1499, 1786, 2435, 1988, 146, 500, 3377, 3789, 1301, 1193, 1686, 3501, 3895, 1321,
            1587, 4263, 593, 1580, 3652, 1638, 4905, 1927, 567, 2797, 2082, 1349, 4158, 679, 4944,
            4638, 4770, 3458, 2117, 2620, 938, 4121, 2374, 1478, 5269, 5548, 5125, 5237, 1693, 2188,
            690, 4640, 827, 2721, 2329, 430, 4423, 5510, 2312, 2493, 4884, 223, 1904, 4660, 5124,
            2851, 5227, 4160, 694, 5660, 5571, 834, 1704, 4550, 988, 573, 3373, 5419, 311, 4280,
            399, 5319, 4723, 5467, 1155, 4267, 303, 4233, 770, 3087, 3306, 1042, 4192, 3736, 893,
            5087, 1903, 3650, 393, 5304, 2767, 3562, 5501, 4789, 1863, 1653, 2528, 5521, 3802, 3925,
            2718, 5402, 2642, 304, 4171, 4356, 5486, 1426, 4526, 4541, 4310, 2160, 4542, 2850, 2396,
            1612, 4780, 3921, 5219, 2585, 4027, 1861, 3799, 101, 1434, 996, 203, 1216, 1654, 4382,
            3791, 3417, 1912, 5337, 814, 352, 3892, 3851, 3432, 2400
        };
        int m = 49, k = 4;
        logResult(minDays(bloomDay, m, k));
    }

    /**
     * 5438. 制作 m 束花所需的最少天数
     *
     * <p>给你一个整数数组 bloomDay，以及两个整数 m 和 k 。
     *
     * <p>现需要制作 m 束花。制作花束时，需要使用花园中 相邻的 k 朵花 。
     *
     * <p>花园中有 n 朵花，第 i 朵花会在 bloomDay[i] 时盛开，恰好 可以用于 一束 花中。
     *
     * <p>请你返回从花园中摘 m 束花需要等待的最少的天数。如果不能摘到 m 束花则返回 -1 。
     *
     * <p>示例 1：
     *
     * <p>输入：bloomDay = [1,10,3,10,2], m = 3, k = 1 输出：3 解释：让我们一起观察这三天的花开过程，x 表示花开，而 _ 表示花还未开。
     * 现在需要制作 3 束花，每束只需要 1 朵。 1 天后：[x, _, _, _, _] // 只能制作 1 束花 2 天后：[x, _, _, _, x] // 只能制作 2 束花 3
     * 天后：[x, _, x, _, x] // 可以制作 3 束花，答案为 3 示例 2：
     *
     * <p>输入：bloomDay = [1,10,3,10,2], m = 3, k = 2 输出：-1 解释：要制作 3 束花，每束需要 2 朵花，也就是一共需要 6 朵花。而花园中只有
     * 5 朵花，无法满足制作要求，返回 -1 。 示例 3：
     *
     * <p>输入：bloomDay = [7,7,7,7,12,7,7], m = 2, k = 3 输出：12 解释：要制作 2 束花，每束需要 3 朵。 花园在 7 天后和 12
     * 天后的情况如下： 7 天后：[x, x, x, x, _, x, x] 可以用前 3 朵盛开的花制作第一束花。但不能使用后 3 朵盛开的花，因为它们不相邻。 12 天后：[x, x,
     * x, x, x, x, x] 显然，我们可以用不同的方式制作两束花。 示例 4：
     *
     * <p>输入：bloomDay = [1000000000,1000000000], m = 1, k = 1 输出：1000000000 解释：需要等 1000000000
     * 天才能采到花来制作花束 示例 5：
     *
     * <p>输入：bloomDay = [1,10,2,9,3,8,4,7,5,6], m = 4, k = 2 输出：9
     *
     * <p>提示：
     *
     * <p>bloomDay.length == n 1 <= n <= 10^5 1 <= bloomDay[i] <= 10^9 1 <= m <= 10^6 1 <= k <= n
     *
     * @param bloomDay
     * @param m
     * @param k
     * @return
     */
    public int minDays(int[] bloomDay, int m, int k) {
        int len = bloomDay.length;
        int total = m * k;
        if (len < total) {
            return -1;
        }
        if (k == 1) {
            Arrays.sort(bloomDay);
            return bloomDay[m - 1];
        }
        int max = 0;
        for (int day : bloomDay) {
            max = Math.max(max, day);
        }
        if (len == total) {
            return max;
        }
        int left = 0, right = max;
        while (left < right) {
            int mid = (left + right) >> 1;
            if (getCount(bloomDay, k, mid) >= m) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;

        // 滑动窗口

        /*int maxLen = len - k + 1;
        int[] maxDays = new int[maxLen];

        // 双端队列
        Deque<Integer> deque = new LinkedList<>();
        for (int i = 0; i < k; i++) {
            while (!deque.isEmpty() && deque.peekLast() < bloomDay[i]) {
                deque.removeLast();
            }
            deque.addLast(bloomDay[i]);
        }
        maxDays[0] = deque.peekFirst();

        for (int i = k; i < bloomDay.length; i++) {
            if (!deque.isEmpty() && deque.peekFirst() == bloomDay[i - k]) {
                deque.removeFirst();
            }
            while (!deque.isEmpty() && deque.peekLast() < bloomDay[i]) {
                deque.removeLast();
            }
            deque.addLast(bloomDay[i]);
            maxDays[i - k + 1] = deque.peekFirst();
        }
        // 动态规划

        return 0;*/
    }

    /**
     * 是否能 制作 m 束花
     *
     * @param bloomDay
     * @param k
     * @param day
     * @return
     */
    private int getCount(int[] bloomDay, int k, int day) {
        int count = 0, result = 0;
        for (int i = 0; i < bloomDay.length; i++) {
            if (bloomDay[i] <= day) {
                count++;
            } else {
                count = 0;
            }
            if (count == k) {
                result++;
                count = 0;
            }
        }
        return result;
    }

    @Test
    public void shortestSeq() {
        int[] big = {7, 5, 9, 0, 2, 1, 3, 5, 7, 9, 1, 1, 5, 8, 8, 9, 7}, small = {1, 5, 9};
        int[] result = shortestSeq(big, small);
        log.debug("result:{}", result);
    }

    /**
     * 面试题 17.18. 最短超串
     *
     * <p>假设你有两个数组，一个长一个短，短的元素均不相同。找到长数组中包含短数组所有的元素的最短子数组，其出现顺序无关紧要。
     *
     * <p>返回最短子数组的左端点和右端点，如有多个满足条件的子数组，返回左端点最小的一个。若不存在，返回空数组。
     *
     * <p>示例 1:
     *
     * <p>输入: big = [7,5,9,0,2,1,3,5,7,9,1,1,5,8,8,9,7] small = [1,5,9] 输出: [7,10] 示例 2:
     *
     * <p>输入: big = [1,2,3] small = [4] 输出: [] 提示：
     *
     * <p>big.length <= 100000 1 <= small.length <= 100000
     *
     * @param big
     * @param small
     * @return
     */
    public int[] shortestSeq(int[] big, int[] small) {
        int[] result = new int[2];
        Map<Integer, Integer> indexMap = new HashMap<>();
        Set<Integer> set = new HashSet<>();
        for (int num : small) {
            set.add(num);
        }

        int minLen = Integer.MAX_VALUE;
        // 滑动窗口
        for (int i = 0; i < big.length; i++) {
            if (set.contains(big[i])) {
                indexMap.put(big[i], i);
            } else {
                continue;
            }
            if (set.size() != indexMap.size()) {
                continue;
            }
            int min = Integer.MAX_VALUE;
            for (int val : indexMap.values()) {
                min = Math.min(min, val);
            }
            int len = i - min + 1;
            if (len < minLen) {
                minLen = len;
                result[0] = min;
                result[1] = i;
            }
        }

        return minLen == Integer.MAX_VALUE ? new int[0] : result;
    }

    @Test
    public void printKMoves() {
        int k = 2;
        List<String> result = printKMoves(k);
        for (String s : result) {
            log.debug(s);
        }
    }

    /**
     * 面试题 16.22. 兰顿蚂蚁
     *
     * <p>一只蚂蚁坐在由白色和黑色方格构成的无限网格上。开始时，网格全白，蚂蚁面向右侧。每行走一步，蚂蚁执行以下操作。
     *
     * <p>(1) 如果在白色方格上，则翻转方格的颜色，向右(顺时针)转 90 度，并向前移动一个单位。 (2) 如果在黑色方格上，则翻转方格的颜色，向左(逆时针方向)转 90
     * 度，并向前移动一个单位。
     *
     * <p>编写程序来模拟蚂蚁执行的前 K 个动作，并返回最终的网格。
     *
     * <p>网格由数组表示，每个元素是一个字符串，代表网格中的一行，黑色方格由 'X' 表示，白色方格由 '_' 表示，蚂蚁所在的位置由 'L', 'U', 'R', 'D'
     * 表示，分别表示蚂蚁 左、上、右、下 的朝向。只需要返回能够包含蚂蚁走过的所有方格的最小矩形。
     *
     * <p>示例 1:
     *
     * <p>输入: 0 输出: ["R"] 示例 2:
     *
     * <p>输入: 2 输出: [ "_X", "LX" ] 示例 3:
     *
     * <p>输入: 5 输出: [ "_U", "X_", "XX" ] 说明：
     *
     * <p>K <= 100000
     *
     * @param K
     * @return
     */
    public List<String> printKMoves(int K) {
        // 方向
        int[] dirRow = {0, 1, 0, -1};
        int[] dirCol = {1, 0, -1, 0};
        char[] directions = {'R', 'D', 'L', 'U'};
        int minRow = 0, maxRow = 0, minCol = 0, maxCol = 0;

        int dirIdx = 0;
        int row = 0, col = 0;
        Set<Position> blackSet = new HashSet<>();
        for (int i = 0; i < K; i++) {

            Position t = new Position(row, col);
            // 放入集合, 成功， 说明脚下目前是白色，右旋转90
            if (blackSet.add(t)) {
                // 顺时针 转 90 度
                dirIdx = (dirIdx + 1) % 4;
            }
            // 没成功说明已经是黑色了，删除，左旋
            else {
                // 逆时针方向 转 90 度
                dirIdx = (dirIdx + 3) % 4;
                blackSet.remove(t);
            }

            row += dirRow[dirIdx];
            col += dirCol[dirIdx];
            minRow = Math.min(minRow, row);
            maxRow = Math.max(maxRow, row);
            minCol = Math.min(minCol, col);
            maxCol = Math.max(maxCol, col);
        }
        logResult(blackSet);
        log.debug("rows:{} cols:{}", maxRow - minRow + 1, maxCol - minCol + 1);
        // 返回的数组
        char[][] grid = new char[maxRow - minRow + 1][maxCol - minCol + 1];

        for (char[] chars : grid) {
            Arrays.fill(chars, '_');
        }

        // 替换黑块
        for (Position pos : blackSet) {
            grid[pos.row - minRow][pos.col - minCol] = 'X';
        }
        logResult(grid);
        // 替换蚂蚁
        grid[row - minRow][col - minCol] = directions[dirIdx];
        List<String> result = new ArrayList<>();
        for (char[] chars : grid) {
            result.add(new String(chars));
        }
        return result;
    }

    private class Position {
        int row, col;

        // 构造方法
        public Position(int row, int col) {
            this.row = row;
            this.col = col;
        }

        // 重写equals
        @Override
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof Position)) {
                return false;
            }
            Position o = (Position) obj;
            return this.row == o.row && this.col == o.col;
        }

        // 重写哈希算法，使两个Postion对象可以比较坐标
        @Override
        public int hashCode() {
            return 31 * row + col;
        }
    }

    @Test
    public void maxScoreSightseeingPair() {
        int[] A = {8, 1, 5, 2, 6};
        logResult(maxScoreSightseeingPair(A));
    }

    /**
     * 1014. 最佳观光组合
     *
     * <p>给定正整数数组 A，A[i] 表示第 i 个观光景点的评分，并且两个景点 i 和 j 之间的距离为 j - i。
     *
     * <p>一对景点（i < j）组成的观光组合的得分为（A[i] + A[j] + i - j）：景点的评分之和减去它们两者之间的距离。
     *
     * <p>返回一对观光景点能取得的最高分。
     *
     * <p>示例：
     *
     * <p>输入：[8,1,5,2,6] 输出：11 解释：i = 0, j = 2, A[i] + A[j] + i - j = 8 + 5 + 0 - 2 = 11
     *
     * <p>提示：
     *
     * <p>2 <= A.length <= 50000 1 <= A[i] <= 1000
     *
     * @param A
     * @return
     */
    public int maxScoreSightseeingPair(int[] A) {
        int max = 0;
        int len = A.length;
        int[] arr1 = new int[len];
        int[] arr2 = new int[len];

        for (int i = 0; i < len; i++) {
            arr1[i] = A[i] + i;
            arr2[i] = A[i] - i;
        }
        // 思路 数组1, 当前位置i 最大的 A[i] + i
        for (int i = 1; i < len; i++) {
            arr1[i] = Math.max(arr1[i], arr1[i - 1]);
        }
        // 思路 数组1, 当前位置i 最大的 A[i] + i + A[j]  - j
        for (int i = 1; i < len; i++) {
            arr2[i] = Math.max(arr1[i - 1] + arr2[i], arr2[i - 1]);
        }
        max = arr2[len - 1];
        return max;
    }

    @Test
    public void trap2() {
        int[] height = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        logResult(trap2(height));
    }

    /**
     * 面试题 17.21. 直方图的水量
     *
     * <p>给定一个直方图(也称柱状图)，假设有人从上面源源不断地倒水，最后直方图能存多少水量?直方图的宽度为 1。
     *
     * <p>上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的直方图，在这种情况下，可以接 6 个单位的水（蓝色部分表示水）。 感谢 Marcos 贡献此图。
     *
     * <p>示例:
     *
     * <p>输入: [0,1,0,2,1,0,1,3,2,1,2,1] 输出: 6
     *
     * @param height
     * @return
     */
    public int trap2(int[] height) {
        int len = height.length;
        if (len <= 2) {
            return 0;
        }
        // 波峰数组
        int[] leftHeight = new int[len];
        leftHeight[0] = height[0];
        for (int i = 1; i < len; i++) {
            leftHeight[i] = Math.max(height[i], leftHeight[i - 1]);
        }
        int[] rightHeight = new int[len];
        rightHeight[len - 1] = height[len - 1];
        for (int i = len - 2; i > 0; i--) {
            rightHeight[i] = Math.max(height[i], rightHeight[i + 1]);
        }

        int result = 0;
        for (int i = 1; i < len - 1; i++) {
            result += Math.min(leftHeight[i], rightHeight[i]) - height[i];
        }

        return result;
    }

    @Test
    public void countBattleships() {
        char[][] board = {{'X', '.', '.', 'X'}, {'.', '.', '.', 'X'}, {'.', '.', '.', 'X'}};
        logResult(countBattleships(board));
    }

    /**
     * 419. 甲板上的战舰
     *
     * <p>给定一个二维的甲板， 请计算其中有多少艘战舰。 战舰用 'X'表示，空位用 '.'表示。 你需要遵守以下规则：
     *
     * <p>给你一个有效的甲板，仅由战舰或者空位组成。 战舰只能水平或者垂直放置。换句话说,战舰只能由 1xN (1 行, N 列)组成，或者 Nx1 (N 行, 1
     * 列)组成，其中N可以是任意大小。 两艘战舰之间至少有一个水平或垂直的空位分隔 - 即没有相邻的战舰。 示例 :
     *
     * <p>X..X ...X ...X 在上面的甲板中有2艘战舰。
     *
     * <p>无效样例 :
     *
     * <p>...X XXXX ...X 你不会收到这样的无效甲板 - 因为战舰之间至少会有一个空位将它们分开。
     *
     * <p>进阶:
     *
     * <p>你可以用一次扫描算法，只使用O(1)额外空间，并且不修改甲板的值来解决这个问题吗？
     *
     * @param board
     * @return
     */
    public int countBattleships(char[][] board) {
        int m = board.length, n = board[0].length;
        int count = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                char c = board[i][j];
                if (c != 'X') {
                    continue;
                }
                // 判断 左边和 上边 是否为空的
                boolean flag = true;
                if (i > 0 && board[i - 1][j] == 'X') {
                    flag = false;
                }
                if (j > 0 && board[i][j - 1] == 'X') {
                    flag = false;
                }

                if (flag) {
                    count++;
                }
            }
        }
        return count;
    }

    @Test
    public void getFolderNames() {
        String[] names = {"gta", "gta(1)", "gta", "avalon"};
        String[] result = getFolderNames(names);
        logResult(result);
    }

    /**
     * 5441. 保证文件名唯一
     *
     * <p>给你一个长度为 n 的字符串数组 names 。你将会在文件系统中创建 n 个文件夹：在第 i 分钟，新建名为 names[i] 的文件夹。
     *
     * <p>由于两个文件 不能 共享相同的文件名，因此如果新建文件夹使用的文件名已经被占用，系统会以 (k) 的形式为新文件夹的文件名添加后缀，其中 k 是能保证文件名唯一的 最小正整数 。
     *
     * <p>返回长度为 n 的字符串数组，其中 ans[i] 是创建第 i 个文件夹时系统分配给该文件夹的实际名称。
     *
     * <p>示例 1：
     *
     * <p>输入：names = ["pes","fifa","gta","pes(2019)"] 输出：["pes","fifa","gta","pes(2019)"]
     * 解释：文件系统将会这样创建文件名： "pes" --> 之前未分配，仍为 "pes" "fifa" --> 之前未分配，仍为 "fifa" "gta" --> 之前未分配，仍为
     * "gta" "pes(2019)" --> 之前未分配，仍为 "pes(2019)" 示例 2：
     *
     * <p>输入：names = ["gta","gta(1)","gta","avalon"] 输出：["gta","gta(1)","gta(2)","avalon"]
     * 解释：文件系统将会这样创建文件名： "gta" --> 之前未分配，仍为 "gta" "gta(1)" --> 之前未分配，仍为 "gta(1)" "gta" -->
     * 文件名被占用，系统为该名称添加后缀 (k)，由于 "gta(1)" 也被占用，所以 k = 2 。实际创建的文件名为 "gta(2)" 。 "avalon" --> 之前未分配，仍为
     * "avalon" 示例 3：
     *
     * <p>输入：names = ["onepiece","onepiece(1)","onepiece(2)","onepiece(3)","onepiece"]
     * 输出：["onepiece","onepiece(1)","onepiece(2)","onepiece(3)","onepiece(4)"] 解释：当创建最后一个文件夹时，最小的正有效
     * k 为 4 ，文件名变为 "onepiece(4)"。 示例 4：
     *
     * <p>输入：names = ["wano","wano","wano","wano"] 输出：["wano","wano(1)","wano(2)","wano(3)"]
     * 解释：每次创建文件夹 "wano" 时，只需增加后缀中 k 的值即可。 示例 5：
     *
     * <p>输入：names = ["kaido","kaido(1)","kaido","kaido(1)"]
     * 输出：["kaido","kaido(1)","kaido(2)","kaido(1)(1)"] 解释：注意，如果含后缀文件名被占用，那么系统也会按规则在名称后添加新的后缀 (k) 。
     *
     * <p>提示：
     *
     * <p>1 <= names.length <= 5 * 10^4 1 <= names[i].length <= 20 names[i] 由小写英文字母、数字和/或圆括号组成。
     *
     * @param names
     * @return
     */
    public String[] getFolderNames(String[] names) {
        String[] result = new String[names.length];
        Map<String, Integer> map = new HashMap<>();
        Set<String> nameSet = new HashSet<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < names.length; i++) {
            String name = names[i];
            int index = map.getOrDefault(name, 0);
            sb.setLength(0);
            sb.append(name);
            if (index > 0) {
                sb.append('(').append(index).append(')');
            }
            while (nameSet.contains(sb.toString())) {
                index++;
                sb.setLength(0);
                sb.append(name).append('(').append(index).append(')');
            }
            nameSet.add(sb.toString());
            result[i] = sb.toString();
            map.put(name, index + 1);
        }

        return result;

        /*String[] result = new String[names.length];
        Map<String, Set<Integer>> map = new HashMap<>();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < names.length; i++) {
            String name = names[i];
            int index = 0;
            boolean flag = false;
            if (names[i].contains("(")) {
                int start = names[i].indexOf('(');
                int end = names[i].indexOf(')');
                name = names[i].substring(0, start);
                index = Integer.parseInt(names[i].substring(start + 1, end));
                flag = true;
            }
            Set<Integer> indexSet = map.computeIfAbsent(name, k -> new HashSet<>());
            while (indexSet.contains(index)) {
                index++;
            }
            indexSet.add(index);
            sb.setLength(0);
            sb.append(name);
            if (index > 0) {
                sb.append('(').append(index).append(')');
            }
            result[i] = sb.toString();
        }

        return result;*/
    }

    @Test
    public void avoidFlood() {
        int[] rains = {2, 3, 0, 0, 3, 1, 0, 1, 0, 2, 2};
        int[] result = avoidFlood(rains);
        log.debug("result:{}", result);
    }

    /**
     * 5442. 避免洪水泛滥
     *
     * <p>你的国家有无数个湖泊，所有湖泊一开始都是空的。当第 n 个湖泊下雨的时候，如果第 n 个湖泊是空的，那么它就会装满水，否则这个湖泊会发生洪水。你的目标是避免任意一个湖泊发生洪水。
     *
     * <p>给你一个整数数组 rains ，其中：
     *
     * <p>rains[i] > 0 表示第 i 天时，第 rains[i] 个湖泊会下雨。 rains[i] == 0 表示第 i 天没有湖泊会下雨，你可以选择 一个 湖泊并 抽干
     * 这个湖泊的水。 请返回一个数组 ans ，满足：
     *
     * <p>ans.length == rains.length 如果 rains[i] > 0 ，那么ans[i] == -1 。 如果 rains[i] == 0 ，ans[i] 是你第
     * i 天选择抽干的湖泊。 如果有多种可行解，请返回它们中的 任意一个 。如果没办法阻止洪水，请返回一个 空的数组 。
     *
     * <p>请注意，如果你选择抽干一个装满水的湖泊，它会变成一个空的湖泊。但如果你选择抽干一个空的湖泊，那么将无事发生（详情请看示例 4）。
     *
     * <p>示例 1：
     *
     * <p>输入：rains = [1,2,3,4] 输出：[-1,-1,-1,-1] 解释：第一天后，装满水的湖泊包括 [1] 第二天后，装满水的湖泊包括 [1,2]
     * 第三天后，装满水的湖泊包括 [1,2,3] 第四天后，装满水的湖泊包括 [1,2,3,4] 没有哪一天你可以抽干任何湖泊的水，也没有湖泊会发生洪水。 示例 2：
     *
     * <p>输入：rains = [1,2,0,0,2,1] 输出：[-1,-1,2,1,-1,-1] 解释：第一天后，装满水的湖泊包括 [1] 第二天后，装满水的湖泊包括 [1,2]
     * 第三天后，我们抽干湖泊 2 。所以剩下装满水的湖泊包括 [1] 第四天后，我们抽干湖泊 1 。所以暂时没有装满水的湖泊了。 第五天后，装满水的湖泊包括 [2]。
     * 第六天后，装满水的湖泊包括 [1,2]。 可以看出，这个方案下不会有洪水发生。同时， [-1,-1,1,2,-1,-1] 也是另一个可行的没有洪水的方案。 示例 3：
     *
     * <p>输入：rains = [1,2,0,1,2] 输出：[] 解释：第二天后，装满水的湖泊包括 [1,2]。我们可以在第三天抽干一个湖泊的水。 但第三天后，湖泊 1 和 2
     * 都会再次下雨，所以不管我们第三天抽干哪个湖泊的水，另一个湖泊都会发生洪水。 示例 4：
     *
     * <p>输入：rains = [69,0,0,0,69] 输出：[-1,69,1,1,-1] 解释：任何形如 [-1,69,x,y,-1], [-1,x,69,y,-1] 或者
     * [-1,x,y,69,-1] 都是可行的解，其中 1 <= x,y <= 10^9 示例 5：
     *
     * <p>输入：rains = [10,20,20] 输出：[] 解释：由于湖泊 20 会连续下 2 天的雨，所以没有没有办法阻止洪水。
     *
     * <p>提示：
     *
     * <p>1 <= rains.length <= 10^5 0 <= rains[i] <= 10^9
     *
     * @param rains
     * @return
     */
    public int[] avoidFlood(int[] rains) {
        int[] result = new int[rains.length];
        Map<Integer, Integer> lakes = new HashMap<>();
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < rains.length; i++) {
            int lake = rains[i];
            if (lake == 0) {
                list.add(i);
                result[i] = 1;
                continue;
            }
            if (lakes.containsKey(lake)) {
                if (list.isEmpty()) {
                    return new int[0];
                }
                int rainIndex = lakes.get(lake);
                int index = -1;
                for (int j = 0; j < list.size(); j++) {
                    if (list.get(j) > rainIndex) {
                        index = list.get(j);
                        list.remove(j);
                        break;
                    }
                }
                if (index == -1) {
                    return new int[0];
                }
                result[index] = lake;
                lakes.remove(lake);
            }
            lakes.put(lake, i);
            result[i] = -1;
        }
        return result;
    }

    /**
     * 457. 环形数组循环
     *
     * <p>给定一个含有正整数和负整数的环形数组 nums。 如果某个索引中的数 k 为正数，则向前移动 k 个索引。相反，如果是负数 (-k)，则向后移动 k
     * 个索引。因为数组是环形的，所以可以假设最后一个元素的下一个元素是第一个元素，而第一个元素的前一个元素是最后一个元素。
     *
     * <p>确定 nums 中是否存在循环（或周期）。循环必须在相同的索引处开始和结束并且循环长度 >
     * 1。此外，一个循环中的所有运动都必须沿着同一方向进行。换句话说，一个循环中不能同时包括向前的运动和向后的运动。
     *
     * <p>示例 1：
     *
     * <p>输入：[2,-1,1,2,2] 输出：true 解释：存在循环，按索引 0 -> 2 -> 3 -> 0 。循环长度为 3 。 示例 2：
     *
     * <p>输入：[-1,2] 输出：false 解释：按索引 1 -> 1 -> 1 ... 的运动无法构成循环，因为循环的长度为 1 。根据定义，循环的长度必须大于 1 。 示例 3:
     *
     * <p>输入：[-2,1,-1,-2,-2] 输出：false 解释：按索引 1 -> 2 -> 1 -> ... 的运动无法构成循环，因为按索引 1 -> 2
     * 的运动是向前的运动，而按索引 2 -> 1 的运动是向后的运动。一个循环中的所有运动都必须沿着同一方向进行。
     *
     * <p>提示：
     *
     * <p>-1000 ≤ nums[i] ≤ 1000 nums[i] ≠ 0 0 ≤ nums.length ≤ 5000
     *
     * <p>进阶：
     *
     * <p>你能写出时间时间复杂度为 O(n) 和额外空间复杂度为 O(1) 的算法吗？
     *
     * @param nums
     * @return
     */
    public boolean circularArrayLoop(int[] nums) {
        if (nums.length < 2) {
            return false;
        }
        for (int i = 0; i < nums.length; i++) {
            // 将无法成环的下标置为0
            if (nums[i] == 0) {
                continue;
            }
            int lastSlow, lastFast;
            int slow = i, fast = i;
            while (true) {
                lastSlow = slow;
                slow = (slow + nums[slow] + 5000 * nums.length) % nums.length;
                // nums[lastSlow] * nums[slow] < 0 前后方向不一致
                // lastSlow == slow 只有一个元素成环
                if (nums[lastSlow] * nums[slow] < 0 || nums[slow] == 0 || lastSlow == slow) {
                    setZero(nums, i);
                    break;
                }
                lastFast = fast;
                fast = (fast + nums[fast] + 5000 * nums.length) % nums.length;
                if (nums[lastFast] * nums[fast] < 0 || nums[fast] == 0 || lastFast == fast) {
                    setZero(nums, i);
                    break;
                }
                lastFast = fast;
                fast = (fast + nums[fast] + 5000 * nums.length) % nums.length;
                if (nums[lastFast] * nums[fast] < 0 || nums[fast] == 0 || lastFast == fast) {
                    setZero(nums, i);
                    break;
                }

                // 快慢指针相遇
                if (fast == slow) {
                    return true;
                }
            }
        }
        return false;
    }

    private void setZero(int[] nums, int i) {
        int j;
        while (true) {
            j = (i + nums[i] + 5000 * nums.length) % nums.length;
            if (nums[j] == 0 || nums[i] * nums[j] < 0) {
                nums[i] = 0;
                break;
            }
            nums[i] = 0;
            i = j;
        }
    }

    @Test
    public void makesquare() {
        int[] nums = {1, 1, 2, 2, 2};
        logResult(makesquare(nums));
    }

    /**
     * 473. 火柴拼正方形
     *
     * <p>还记得童话《卖火柴的小女孩》吗？现在，你知道小女孩有多少根火柴，请找出一种能使用所有火柴拼成一个正方形的方法。不能折断火柴，可以把火柴连接起来，并且每根火柴都要用到。
     *
     * <p>输入为小女孩拥有火柴的数目，每根火柴用其长度表示。输出即为是否能用所有的火柴拼成正方形。
     *
     * <p>示例 1:
     *
     * <p>输入: [1,1,2,2,2] 输出: true
     *
     * <p>解释: 能拼成一个边长为2的正方形，每边两根火柴。 示例 2:
     *
     * <p>输入: [3,3,3,3,4] 输出: false
     *
     * <p>解释: 不能用所有火柴拼成一个正方形。 注意:
     *
     * <p>给定的火柴长度和在 0 到 10^9之间。 火柴数组的长度不超过15。
     *
     * @param nums
     * @return
     */
    public boolean makesquare(int[] nums) {
        if (nums.length < 4) {
            return false;
        }
        int sum = 0;
        int max = 0;
        for (int num : nums) {
            sum += num;
            max = Math.max(max, num);
        }
        if (sum % 4 != 0) {
            return false;
        }
        int sideLen = sum >> 2;
        if (max > sideLen) {
            return false;
        }
        Arrays.sort(nums);
        // 从大到小
        boolean[] visited = new boolean[nums.length];
        boolean result = true;
        for (int i = 0; i < 4; i++) {
            result = result & makeSquare(nums, nums.length - 1, sideLen, visited);
        }
        return result;
    }

    private boolean makeSquare(int[] nums, int start, int sideLen, boolean[] visited) {
        if (sideLen < 0) {
            return false;
        }
        if (sideLen == 0) {
            return true;
        }
        for (int i = start; i >= 0; i--) {
            if (visited[i]) {
                continue;
            }
            visited[i] = true;
            if (makeSquare(nums, i - 1, sideLen - nums[i], visited)) {
                return true;
            }
            visited[i] = false;
        }
        return false;
    }

    boolean makeSquareResult = false;

    /*private void backtrack(int[] nums, int index, int sideLen, int[] sides) {
        if (makeSquareResult) {
            return;
        }
        if (index == -1) {
            for (int side : sides) {
                if (side != sideLen) {
                    return;
                }
            }
            makeSquareResult = true;
            return;
        }
        for (int i = 0; i < sides.length; i++) {
            int side = sides[i];
            sides[i] += nums[index];
            if (sides[i] <= sideLen) {
                backtrack(nums, index - 1, sideLen, sides);
            }
            sides[i] = side;
        }
    }*/

    @Test
    public void threeSumClosest2() {
        int[] nums = {1, 1, 1, 1};
        int target = 0;
        int result = threeSumClosest2(nums, target);
        log.debug("result : {}", result);
    }

    /**
     * 16. 最接近的三数之和
     *
     * <p>给定一个包括 n 个整数的数组 nums 和 一个目标值 target。找出 nums 中的三个整数，使得它们的和与 target
     * 最接近。返回这三个数的和。假定每组输入只存在唯一答案。
     *
     * <p>示例：
     *
     * <p>输入：nums = [-1,2,1,-4], target = 1 输出：2 解释：与 target 最接近的和是 2 (-1 + 2 + 1 = 2) 。
     *
     * <p>提示：
     *
     * <p>3 <= nums.length <= 10^3 -10^3 <= nums[i] <= 10^3 -10^4 <= target <= 10^4
     *
     * @param nums
     * @param target
     * @return
     */
    public int threeSumClosest2(int[] nums, int target) {
        Arrays.sort(nums);
        int min = Integer.MAX_VALUE;
        int result = target;
        for (int i = 0; i < nums.length - 2; i++) {
            int b = target - nums[i];
            int left = i + 1, right = nums.length - 1;
            while (left < right) {
                int sum = nums[left] + nums[right];
                if (sum == b) {
                    min = 0;
                    break;
                }
                if (Math.abs(sum - b) < min) {
                    min = Math.abs(sum - b);
                    result = sum + nums[i];
                }
                if (sum < b) {
                    left++;
                } else {
                    right--;
                }
            }
            if (min == 0) {
                result = target;
                break;
            }
        }
        return result;
    }

    @Test
    public void findPoisonedDuration() {
        int[] timeSeries = {1, 2};
        int duration = 2;
        logResult(findPoisonedDuration(timeSeries, duration));
    }

    /**
     * 495. 提莫攻击
     *
     * <p>在《英雄联盟》的世界中，有一个叫 “提莫”
     * 的英雄，他的攻击可以让敌方英雄艾希（编者注：寒冰射手）进入中毒状态。现在，给出提莫对艾希的攻击时间序列和提莫攻击的中毒持续时间，你需要输出艾希的中毒状态总时长。
     *
     * <p>你可以认为提莫在给定的时间点进行攻击，并立即使艾希处于中毒状态。
     *
     * <p>示例1:
     *
     * <p>输入: [1,4], 2 输出: 4 原因: 第 1 秒初，提莫开始对艾希进行攻击并使其立即中毒。中毒状态会维持 2 秒钟，直到第 2 秒末结束。 第 4
     * 秒初，提莫再次攻击艾希，使得艾希获得另外 2 秒中毒时间。 所以最终输出 4 秒。 示例2:
     *
     * <p>输入: [1,2], 2 输出: 3 原因: 第 1 秒初，提莫开始对艾希进行攻击并使其立即中毒。中毒状态会维持 2 秒钟，直到第 2 秒末结束。 但是第 2
     * 秒初，提莫再次攻击了已经处于中毒状态的艾希。 由于中毒状态不可叠加，提莫在第 2 秒初的这次攻击会在第 3 秒末结束。 所以最终输出 3 。
     *
     * <p>提示：
     *
     * <p>你可以假定时间序列数组的总长度不超过 10000。 你可以假定提莫攻击时间序列中的数字和提莫攻击的中毒持续时间都是非负整数，并且不超过 10,000,000。
     *
     * @param timeSeries
     * @param duration
     * @return
     */
    public int findPoisonedDuration(int[] timeSeries, int duration) {
        int result = 0;
        for (int i = 1; i < timeSeries.length; i++) {
            result += Math.min(duration, timeSeries[i] - timeSeries[i - 1]);
        }
        if (timeSeries.length > 0) {
            result += duration;
        }
        return result;
    }

    @Test
    public void firstMissingPositive2() {
        int[] nums = {0, 1, 2};
        int result = firstMissingPositive2(nums);
        log.debug("result:{}", result);
    }

    /**
     * 41. 缺失的第一个正数
     *
     * <p>给你一个未排序的整数数组，请你找出其中没有出现的最小的正整数。
     *
     * <p>示例 1:
     *
     * <p>输入: [1,2,0] 输出: 3 示例 2:
     *
     * <p>输入: [3,4,-1,1] 输出: 2 示例 3:
     *
     * <p>输入: [7,8,9,11,12] 输出: 1
     *
     * <p>提示：
     *
     * <p>你的算法的时间复杂度应为O(n)，并且只能使用常数级别的额外空间。
     *
     * @param nums
     * @return
     */
    public int firstMissingPositive2(int[] nums) {
        int len = nums.length;
        for (int i = 0; i < nums.length; i++) {
            if (i + 1 == nums[i]) {
                continue;
            }
            modifyNums(nums, nums[i]);
        }

        for (int i = 0; i < len; i++) {
            if (nums[i] != i + 1) {
                return i + 1;
            }
        }
        log.debug("result:{}", nums);
        return len;
    }

    private void modifyNums(int[] nums, int index) {
        if (index - 1 < 0 || index - 1 >= nums.length) {
            return;
        }
        if (nums[index - 1] == index) {
            return;
        }
        int tmp = nums[index - 1];
        nums[index - 1] = index;
        log.debug("nums:{}", nums);
        modifyNums(nums, tmp);
    }

    @Test
    public void singleNonDuplicate() {
        int[] nums = {3, 3, 7, 7, 10, 11, 11};
        logResult(singleNonDuplicate(nums));
    }

    /**
     * 540. 有序数组中的单一元素
     *
     * <p>给定一个只包含整数的有序数组，每个元素都会出现两次，唯有一个数只会出现一次，找出这个数。
     *
     * <p>示例 1:
     *
     * <p>输入: [1,1,2,3,3,4,4,8,8] 输出: 2 示例 2:
     *
     * <p>输入: [3,3,7,7,10,11,11] 输出: 10 注意: 您的方案应该在 O(log n)时间复杂度和 O(1)空间复杂度中运行。
     *
     * @param nums
     * @return
     */
    public int singleNonDuplicate(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        if (nums[0] != nums[1]) {
            return nums[0];
        }
        if (nums[nums.length - 1] != nums[nums.length - 2]) {
            return nums[nums.length - 1];
        }
        int left = 0, right = nums.length - 1;

        while (left < right) {
            int mid = (left + right) >> 1;
            if (mid > 0 && nums[mid] != nums[mid - 1] && nums[mid] != nums[mid + 1]) {
                return nums[mid];
            }
            if ((mid & 1) == 0) {
                if (nums[mid] == nums[mid - 1]) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            } else {
                if (nums[mid] == nums[mid - 1]) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }
        }

        return nums[left];
    }

    /**
     * 5432. 去掉最低工资和最高工资后的工资平均值
     *
     * <p>给你一个整数数组 salary ，数组里每个数都是 唯一 的，其中 salary[i] 是第 i 个员工的工资。
     *
     * <p>请你返回去掉最低工资和最高工资以后，剩下员工工资的平均值。
     *
     * <p>示例 1：
     *
     * <p>输入：salary = [4000,3000,1000,2000] 输出：2500.00000 解释：最低工资和最高工资分别是 1000 和 4000 。
     * 去掉最低工资和最高工资以后的平均工资是 (2000+3000)/2= 2500 示例 2：
     *
     * <p>输入：salary = [1000,2000,3000] 输出：2000.00000 解释：最低工资和最高工资分别是 1000 和 3000 。
     * 去掉最低工资和最高工资以后的平均工资是 (2000)/1= 2000 示例 3：
     *
     * <p>输入：salary = [6000,5000,4000,3000,2000,1000] 输出：3500.00000 示例 4：
     *
     * <p>输入：salary = [8000,9000,2000,3000,6000,1000] 输出：4750.00000
     *
     * <p>提示：
     *
     * <p>3 <= salary.length <= 100 10^3 <= salary[i] <= 10^6 salary[i] 是唯一的。 与真实值误差在 10^-5
     * 以内的结果都将视为正确答案。
     *
     * @param salary
     * @return
     */
    public double average(int[] salary) {
        int len = salary.length;
        int max = 0, min = Integer.MAX_VALUE;
        int sum = 0;
        for (int num : salary) {
            sum += num;
            max = Math.max(max, num);
            min = Math.min(min, num);
        }
        sum -= min;
        sum -= max;
        return (double) sum / (double) (len - 2);
    }

    @Test
    public void longestSubarray() {
        int[] nums = {1, 1, 1, 0};
        logResult(longestSubarray(nums));
    }

    /**
     * 5434. 删掉一个元素以后全为 1 的最长子数组
     *
     * <p>给你一个二进制数组 nums ，你需要从中删掉一个元素。
     *
     * <p>请你在删掉元素的结果数组中，返回最长的且只包含 1 的非空子数组的长度。
     *
     * <p>如果不存在这样的子数组，请返回 0 。
     *
     * <p>提示 1：
     *
     * <p>输入：nums = [1,1,0,1] 输出：3 解释：删掉位置 2 的数后，[1,1,1] 包含 3 个 1 。 示例 2：
     *
     * <p>输入：nums = [0,1,1,1,0,1,1,0,1] 输出：5 解释：删掉位置 4 的数字后，[0,1,1,1,1,1,0,1] 的最长全 1 子数组为
     * [1,1,1,1,1] 。 示例 3：
     *
     * <p>输入：nums = [1,1,1] 输出：2 解释：你必须要删除一个元素。 示例 4：
     *
     * <p>输入：nums = [1,1,0,0,1,1,1,0,1] 输出：4 示例 5：
     *
     * <p>输入：nums = [0,0,0] 输出：0
     *
     * <p>提示：
     *
     * <p>1 <= nums.length <= 10^5 nums[i] 要么是 0 要么是 1 。
     *
     * @param nums
     * @return
     */
    public int longestSubarray(int[] nums) {
        // 删掉一个元素 dp
        int[] deldp = new int[nums.length];
        // 不删掉一个元素 dp
        int[] undeldp = new int[nums.length];
        if (nums[0] == 1) {
            deldp[0] = 1;
            undeldp[0] = 1;
        }
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == 0) {
                continue;
            }
            if (nums[i - 1] == 0) {
                // 删掉 i - 1
                if (i - 2 >= 0) {
                    deldp[i] = undeldp[i - 2] + 1;
                } else {
                    deldp[i] = 1;
                }
                // 不删掉
                undeldp[i] = 1;
            } else {
                // 删掉
                deldp[i] = deldp[i - 1] + 1;
                // 不删掉
                undeldp[i] = undeldp[i - 1] + 1;
            }
        }
        log.debug("del:{}", deldp);
        log.debug("undeldp:{}", undeldp);
        int max = 0;
        for (int i = 0; i < nums.length; i++) {
            max = Math.max(max, deldp[i]);
            max = Math.max(max, undeldp[i]);
        }
        if (max == nums.length) {
            // 必须要删除一个元素, 全为1时 - 1
            return max - 1;
        }

        return max;
    }

    /**
     * 209. 长度最小的子数组
     *
     * <p>给定一个含有 n 个正整数的数组和一个正整数 s ，找出该数组中满足其和 ≥ s 的长度最小的连续子数组，并返回其长度。如果不存在符合条件的连续子数组，返回 0。
     *
     * <p>示例:
     *
     * <p>输入: s = 7, nums = [2,3,1,2,4,3] 输出: 2 解释: 子数组 [4,3] 是该条件下的长度最小的连续子数组。 进阶:
     *
     * <p>如果你已经完成了O(n) 时间复杂度的解法, 请尝试 O(n log n) 时间复杂度的解法。
     *
     * @param s
     * @param nums
     * @return
     */
    public int minSubArrayLen(int s, int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int result = nums.length + 1;
        int left = -1, right = 0;
        int sum = nums[right];
        while (right < nums.length) {
            if (sum < s && right + 1 < nums.length) {
                sum += nums[++right];
            } else {
                sum -= nums[left++];
            }
            if (sum >= s) {
                result = Math.min(result, right - left + 1);
            }
        }

        return result > nums.length ? 0 : result;
    }

    /**
     * 5449. 检查数组对是否可以被 k 整除
     *
     * <p>给你一个整数数组 arr 和一个整数 k ，其中数组长度是偶数，值为 n 。
     *
     * <p>现在需要把数组恰好分成 n / 2 对，以使每对数字的和都能够被 k 整除。
     *
     * <p>如果存在这样的分法，请返回 True ；否则，返回 False 。
     *
     * <p>示例 1：
     *
     * <p>输入：arr = [1,2,3,4,5,10,6,7,8,9], k = 5 输出：true 解释：划分后的数字对为 (1,9),(2,8),(3,7),(4,6) 以及
     * (5,10) 。 示例 2：
     *
     * <p>输入：arr = [1,2,3,4,5,6], k = 7 输出：true 解释：划分后的数字对为 (1,6),(2,5) 以及 (3,4) 。 示例 3：
     *
     * <p>输入：arr = [1,2,3,4,5,6], k = 10 输出：false 解释：无法在将数组中的数字分为三对的同时满足每对数字和能够被 10 整除的条件。 示例 4：
     *
     * <p>输入：arr = [-10,10], k = 2 输出：true 示例 5：
     *
     * <p>输入：arr = [-1,1,-2,2,-3,3,-4,4], k = 3 输出：true
     *
     * <p>提示：
     *
     * <p>arr.length == n 1 <= n <= 10^5 n 为偶数 -10^9 <= arr[i] <= 10^9 1 <= k <= 10^5
     *
     * @param arr
     * @param k
     * @return
     */
    public boolean canArrange(int[] arr, int k) {
        int len = arr.length >> 1;
        // 同余问题
        int[] counts = new int[k];
        for (int i = 0; i < arr.length; i++) {
            int num = arr[i];
            arr[i] = num % k;
            if (arr[i] < 0) {
                arr[i] += k;
            }
            counts[arr[i]]++;
        }

        if ((counts[0] & 1) == 1) {
            return false;
        }
        // k 是偶数 k = 8  余数4 必须是偶数
        if ((k & 1) == 0 && (counts[k >> 1] & 1) == 1) {
            return false;
        }
        int left = 1, right = k - 1;
        while (left < right) {
            if (counts[left++] != counts[right--]) {
                return false;
            }
        }

        return true;
    }

    @Test
    public void findKthLargest2() {
        int[] nums = {3, 2, 1, 5, 6, 4};
        int k = 2;
        int result = findKthLargest2(nums, k);
        log.debug("nums:{}", nums);
        log.debug("result:{}", result);
    }

    /**
     * 215. 数组中的第K个最大元素
     *
     * <p>在未排序的数组中找到第 k 个最大的元素。请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
     *
     * <p>示例 1:
     *
     * <p>输入: [3,2,1,5,6,4] 和 k = 2 输出: 5 示例 2:
     *
     * <p>输入: [3,2,3,1,2,4,5,5,6] 和 k = 4 输出: 4 说明:
     *
     * <p>你可以假设 k 总是有效的，且 1 ≤ k ≤ 数组的长度。
     *
     * @param nums
     * @param k
     * @return
     */
    public int findKthLargest2(int[] nums, int k) {
        // 利用快速排序的思路
        findKthLargest2(nums, 0, nums.length - 1, nums.length - k);
        return nums[nums.length - k];
    }

    private void findKthLargest2(int[] nums, int start, int end, int k) {
        if (start >= end) {
            return;
        }
        // 快速排序：选择一个主元 a一般取第一个, 把比a小的元素放左边,比a大的元素放右边
        int tmp = nums[start];
        int left = start, right = end;
        while (left < right) {
            // 从后往前找到第一个小于 tmp的 元素
            while (left < right && nums[right] >= tmp) {
                right--;
            }
            nums[left] = nums[right];
            // 从前往后找到第一个大于tmp的 元素
            while (left < right && nums[left] <= tmp) {
                left++;
            }
            nums[right] = nums[left];
        }
        nums[left] = tmp;
        if (k == left) {
            return;
        }
        if (k < left) {
            findKthLargest2(nums, start, left - 1, k);
        } else {
            findKthLargest2(nums, left + 1, end, k);
        }
    }

    /**
     * 525. 连续数组
     *
     * <p>给定一个二进制数组, 找到含有相同数量的 0 和 1 的最长连续子数组（的长度）。
     *
     * <p>示例 1:
     *
     * <p>输入: [0,1] 输出: 2 说明: [0, 1] 是具有相同数量0和1的最长连续子数组。 示例 2:
     *
     * <p>输入: [0,1,0] 输出: 2 说明: [0, 1] (或 [1, 0]) 是具有相同数量0和1的最长连续子数组。
     *
     * <p>注意: 给定的二进制数组的长度不会超过50000。
     *
     * @param nums
     * @return
     */
    public int findMaxLength(int[] nums) {
        // 用map记录索引
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int max = 0, count = 0;
        for (int i = 0; i < nums.length; i++) {
            count += nums[i] == 1 ? 1 : -1;
            if (map.containsKey(count)) {
                max = Math.max(max, i - map.get(count));
            } else {
                map.put(count, i);
            }
        }
        return max;
    }

    /**
     * 539. 最小时间差
     *
     * <p>给定一个 24 小时制（小时:分钟）的时间列表，找出列表中任意两个时间的最小时间差并以分钟数表示。
     *
     * <p>示例 1：
     *
     * <p>输入: ["23:59","00:00"] 输出: 1
     *
     * <p>备注:
     *
     * <p>列表中时间数在 2~20000 之间。 每个时间取值在 00:00~23:59 之间。
     *
     * @param timePoints
     * @return
     */
    public int findMinDifference(List<String> timePoints) {
        int[] minuteArr = new int[timePoints.size()];
        for (int i = 0; i < timePoints.size(); i++) {
            int h = Integer.parseInt(timePoints.get(i).substring(0, 2));
            int m = Integer.parseInt(timePoints.get(i).substring(3));
            minuteArr[i] = h * 60 + m;
        }
        Arrays.sort(minuteArr);
        int min = 24 * 60 - (minuteArr[minuteArr.length - 1] - minuteArr[0]);
        for (int i = 1; i < minuteArr.length; i++) {
            min = Math.min(min, minuteArr[i] - minuteArr[i - 1]);
        }
        return min;
    }

    @Test
    public void updateBoard() {
        char[][] board = {
            {'E', 'E', 'E', 'E', 'E'},
            {'E', 'E', 'M', 'E', 'E'},
            {'E', 'E', 'E', 'E', 'E'},
            {'E', 'E', 'E', 'E', 'E'}
        };
        int[] click = {3, 0};
        logResult(updateBoard(board, click));
    }
    /**
     * 529. 扫雷游戏
     *
     * <p>让我们一起来玩扫雷游戏！
     *
     * <p>给定一个代表游戏板的二维字符矩阵。 'M' 代表一个未挖出的地雷，'E' 代表一个未挖出的空方块，'B'
     * 代表没有相邻（上，下，左，右，和所有4个对角线）地雷的已挖出的空白方块，数字（'1' 到 '8'）表示有多少地雷与这块已挖出的方块相邻，'X' 则表示一个已挖出的地雷。
     *
     * <p>现在给出在所有未挖出的方块中（'M'或者'E'）的下一个点击位置（行和列索引），根据以下规则，返回相应位置被点击后对应的面板：
     *
     * <p>如果一个地雷（'M'）被挖出，游戏就结束了- 把它改为 'X'。 如果一个没有相邻地雷的空方块（'E'）被挖出，修改它为（'B'），并且所有和其相邻的方块都应该被递归地揭露。
     * 如果一个至少与一个地雷相邻的空方块（'E'）被挖出，修改它为数字（'1'到'8'），表示相邻地雷的数量。 如果在此次点击中，若无更多方块可被揭露，则返回面板。
     *
     * <p>示例 1：
     *
     * <p>输入:
     *
     * <p>[['E', 'E', 'E', 'E', 'E'], ['E', 'E', 'M', 'E', 'E'], ['E', 'E', 'E', 'E', 'E'], ['E',
     * 'E', 'E', 'E', 'E']]
     *
     * <p>Click : [3,0]
     *
     * <p>输出:
     *
     * <p>[['B', '1', 'E', '1', 'B'], ['B', '1', 'M', '1', 'B'], ['B', '1', '1', '1', 'B'], ['B',
     * 'B', 'B', 'B', 'B']]
     *
     * <p>解释:
     *
     * <p>示例 2：
     *
     * <p>输入:
     *
     * <p>[['B', '1', 'E', '1', 'B'], ['B', '1', 'M', '1', 'B'], ['B', '1', '1', '1', 'B'], ['B',
     * 'B', 'B', 'B', 'B']]
     *
     * <p>Click : [1,2]
     *
     * <p>输出:
     *
     * <p>[['B', '1', 'E', '1', 'B'], ['B', '1', 'X', '1', 'B'], ['B', '1', '1', '1', 'B'], ['B',
     * 'B', 'B', 'B', 'B']]
     *
     * <p>解释:
     *
     * <p>注意：
     *
     * <p>输入矩阵的宽和高的范围为 [1,50]。 点击的位置只能是未被挖出的方块 ('M' 或者 'E')，这也意味着面板至少包含一个可点击的方块。
     * 输入面板不会是游戏结束的状态（即有地雷已被挖出）。 简单起见，未提及的规则在这个问题中可被忽略。例如，当游戏结束时你不需要挖出所有地雷，考虑所有你可能赢得游戏或标记方块的情况。
     *
     * @param board
     * @param click
     * @return
     */
    public char[][] updateBoard(char[][] board, int[] click) {
        int row = click[0], col = click[1];
        if (board[row][col] == 'M') {

            board[row][col] = 'X';
            return board;
        }
        updateBoard(board, row, col);
        return board;
    }

    private void updateBoard(char[][] board, int row, int col) {

        if (!inArea(row, col, board.length, board[0].length)) {
            return;
        }
        if (board[row][col] == 'E') {
            int count = getLandMineCount(board, row, col);
            if (count == 0) {
                board[row][col] = 'B';
                // 递归
                for (int i = 0; i < 8; i++) {
                    int curRow = row + AROUND_DIR_ROW[i];
                    int curCol = col + AROUND_DIR_COL[i];
                    updateBoard(board, curRow, curCol);
                }
            } else {
                board[row][col] = (char) ('0' + count);
            }
        } else if (board[row][col] == 'M') {

            board[row][col] = 'X';
        }
    }

    private int getLandMineCount(char[][] board, int row, int col) {
        int count = 0;
        // 遍历周围8个
        for (int i = 0; i < 8; i++) {
            int curRow = row + AROUND_DIR_ROW[i];
            int curCol = col + AROUND_DIR_COL[i];
            if (!inArea(curRow, curCol, board.length, board[0].length)) {
                continue;
            }
            if (board[curRow][curCol] == 'M') {
                count++;
            }
        }
        return count;
    }

    static int[] AROUND_DIR_ROW = {-1, -1, -1, 0, 0, 1, 1, 1};
    static int[] AROUND_DIR_COL = {-1, 0, 1, -1, 1, -1, 0, 1};

    @Test
    public void arrayNesting() {
        int[] nums = {5, 4, 0, 3, 1, 6, 2};

        logResult(arrayNesting(nums));
    }
    /**
     * 565. 数组嵌套
     *
     * <p>索引从0开始长度为N的数组A，包含0到N - 1的所有整数。找到最大的集合S并返回其大小，其中 S[i] = {A[i], A[A[i]], A[A[A[i]]], ...
     * }且遵守以下的规则。
     *
     * <p>假设选择索引为i的元素A[i]为S的第一个元素，S的下一个元素应该是A[A[i]]，之后是A[A[A[i]]]... 以此类推，不断添加直到S出现重复的元素。
     *
     * <p>示例 1:
     *
     * <p>输入: A = [5,4,0,3,1,6,2] 输出: 4 解释: A[0] = 5, A[1] = 4, A[2] = 0, A[3] = 3, A[4] = 1, A[5] =
     * 6, A[6] = 2.
     *
     * <p>其中一种最长的 S[K]: S[0] = {A[0], A[5], A[6], A[2]} = {5, 6, 2, 0}
     *
     * <p>提示：
     *
     * <p>N是[1, 20,000]之间的整数。 A中不含有重复的元素。 A中的元素大小在[0, N-1]之间。
     *
     * @param nums
     * @return
     */
    public int arrayNesting(int[] nums) {

        boolean[] visited = new boolean[nums.length];
        int max = 0;
        for (int i = 0; i < nums.length; i++) {
            if (visited[i]) {
                continue;
            }
            int start = nums[i], count = 0;
            do {
                start = nums[start];
                visited[start] = true;
                count++;
            } while (start != nums[i]);
            max = Math.max(max, count);
        }
        return max;
    }

    private int getArrayNesting(int[] nums, int index, boolean[] visited) {
        Set<Integer> set = new HashSet<>();
        if (visited[index]) {
            return 0;
        }
        while (!set.contains(nums[index])) {
            visited[index] = true;
            set.add(nums[index]);
            index = nums[index];
        }
        return set.size();
    }

    /**
     * 5452. 判断能否形成等差数列
     *
     * <p>给你一个数字数组 arr 。
     *
     * <p>如果一个数列中，任意相邻两项的差总等于同一个常数，那么这个数列就称为 等差数列 。
     *
     * <p>如果可以重新排列数组形成等差数列，请返回 true ；否则，返回 false 。
     *
     * <p>示例 1：
     *
     * <p>输入：arr = [3,5,1] 输出：true 解释：对数组重新排序得到 [1,3,5] 或者 [5,3,1] ，任意相邻两项的差分别为 2 或 -2 ，可以形成等差数列。 示例
     * 2：
     *
     * <p>输入：arr = [1,2,4] 输出：false 解释：无法通过重新排序得到等差数列。
     *
     * <p>提示：
     *
     * <p>2 <= arr.length <= 1000 -10^6 <= arr[i] <= 10^6
     *
     * @param arr
     * @return
     */
    public boolean canMakeArithmeticProgression(int[] arr) {
        if (arr.length == 2) {
            return true;
        }
        Arrays.sort(arr);
        int val = arr[1] - arr[0];
        for (int i = 2; i < arr.length; i++) {
            if (arr[i] - arr[i - 1] != val) {
                return false;
            }
        }
        return true;
    }

    @Test
    public void getLastMoment() {
        int[] left = {5};
        int[] right = {4};
        int n = 9;
        logResult(getLastMoment(n, left, right));
    }

    /**
     * 5453. 所有蚂蚁掉下来前的最后一刻
     *
     * <p>有一块木板，长度为 n 个 单位 。一些蚂蚁在木板上移动，每只蚂蚁都以 每秒一个单位 的速度移动。其中，一部分蚂蚁向 左 移动，其他蚂蚁向 右 移动。
     *
     * <p>当两只向 不同 方向移动的蚂蚁在某个点相遇时，它们会同时改变移动方向并继续移动。假设更改方向不会花费任何额外时间。
     *
     * <p>而当蚂蚁在某一时刻 t 到达木板的一端时，它立即从木板上掉下来。
     *
     * <p>给你一个整数 n 和两个整数数组 left 以及 right 。两个数组分别标识向左或者向右移动的蚂蚁在 t = 0 时的位置。请你返回最后一只蚂蚁从木板上掉下来的时刻。
     *
     * <p>示例 1：
     *
     * <p>输入：n = 4, left = [4,3], right = [0,1] 输出：4 解释：如上图所示： -下标 0 处的蚂蚁命名为 A 并向右移动。 -下标 1 处的蚂蚁命名为
     * B 并向右移动。 -下标 3 处的蚂蚁命名为 C 并向左移动。 -下标 4 处的蚂蚁命名为 D 并向左移动。 请注意，蚂蚁在木板上的最后时刻是 t = 4
     * 秒，之后蚂蚁立即从木板上掉下来。（也就是说在 t = 4.0000000001 时，木板上没有蚂蚁）。 示例 2：
     *
     * <p>输入：n = 7, left = [], right = [0,1,2,3,4,5,6,7] 输出：7 解释：所有蚂蚁都向右移动，下标为 0 的蚂蚁需要 7 秒才能从木板上掉落。
     * 示例 3：
     *
     * <p>输入：n = 7, left = [0,1,2,3,4,5,6,7], right = [] 输出：7 解释：所有蚂蚁都向左移动，下标为 7 的蚂蚁需要 7 秒才能从木板上掉落。
     * 示例 4：
     *
     * <p>输入：n = 9, left = [5], right = [4] 输出：5 解释：t = 1 秒时，两只蚂蚁将回到初始位置，但移动方向与之前相反。 示例 5：
     *
     * <p>输入：n = 6, left = [6], right = [0] 输出：6
     *
     * <p>提示：
     *
     * <p>1 <= n <= 10^4 0 <= left.length <= n + 1 0 <= left[i] <= n 0 <= right.length <= n + 1 0 <=
     * right[i] <= n 1 <= left.length + right.length <= n + 1 left 和 right 中的所有值都是唯一的，并且每个值
     * 只能出现在二者之一 中。
     *
     * @param n
     * @param left
     * @param right
     * @return
     */
    public int getLastMoment(int n, int[] left, int[] right) {
        int max = 0;
        for (int num : left) {
            max = Math.max(max, num);
        }
        for (int num : right) {
            max = Math.max(max, n - num);
        }

        return max;
    }

    /**
     * 611. 有效三角形的个数
     *
     * <p>给定一个包含非负整数的数组，你的任务是统计其中可以组成三角形三条边的三元组个数。
     *
     * <p>示例 1:
     *
     * <p>输入: [2,2,3,4] 输出: 3 解释: 有效的组合是: 2,3,4 (使用第一个 2) 2,3,4 (使用第二个 2) 2,2,3 注意:
     *
     * <p>数组长度不超过1000。 数组里整数的范围为 [0, 1000]。
     *
     * @param nums
     * @return
     */
    public int triangleNumber(int[] nums) {
        int count = 0;
        Arrays.sort(nums);
        int len = nums.length;
        for (int i = len - 1; i > 1; i--) {

            int left = 0, right = i - 1;
            while (left < right) {
                if (nums[left] + nums[right] > nums[i]) {
                    count += right - left;
                    right--;
                } else {
                    left++;
                }
            }
        }
        /*for (int i = 0; i < nums.length - 2; i++) {
                    // 0 在 最前面,只需要判断nums[i] 即可
                    if (nums[i] == 0) {
                        continue;
                    }
                    //
                    for (int j = i + 1; j < nums.length - 1; j++) {
                        int k = i + 2;
                        // 两边之和大于第三边
                        while (k < nums.length && nums[k] < nums[i] + nums[j]) {
                            k++;
                        }
                        count += k - j - 1;
                    }
                }
        */
        return count;
    }

    /**
     * 593. 有效的正方形
     *
     * <p>给定二维空间中四点的坐标，返回四点是否可以构造一个正方形。
     *
     * <p>一个点的坐标（x，y）由一个有两个整数的整数数组表示。
     *
     * <p>示例:
     *
     * <p>输入: p1 = [0,0], p2 = [1,1], p3 = [1,0], p4 = [0,1] 输出: True
     *
     * <p>注意:
     *
     * <p>所有输入整数都在 [-10000，10000] 范围内。 一个有效的正方形有四个等长的正长和四个等角（90度角）。 输入点没有顺序。
     *
     * @param p1
     * @param p2
     * @param p3
     * @param p4
     * @return
     */
    public boolean validSquare(int[] p1, int[] p2, int[] p3, int[] p4) {
        int[] side = new int[6];

        side[0] = getSideSquare(p1, p2);
        side[1] = getSideSquare(p1, p3);
        side[2] = getSideSquare(p1, p4);
        side[3] = getSideSquare(p2, p3);
        side[4] = getSideSquare(p2, p4);
        side[5] = getSideSquare(p3, p4);
        Arrays.sort(side);

        return checkSide(side);
    }

    private boolean checkSide(int[] side) {

        int sideLen = side[0], diagonal = side[4];
        if (sideLen == 0) {
            return false;
        }
        // 前4条相等
        if (side[1] != sideLen || side[2] != sideLen || side[3] != sideLen) {
            return false;
        }
        // 后2条相等
        if (side[5] != diagonal) {
            return false;
        }
        // 对角线的平方是 边长平方的两倍
        if (sideLen * 2 != diagonal) {
            return false;
        }
        return true;
    }

    /**
     * 长度的平方
     *
     * @param p1
     * @param p2
     * @return
     */
    private int getSideSquare(int[] p1, int[] p2) {
        return (p1[0] - p2[0]) * (p1[0] - p2[0]) + (p1[1] - p2[1]) * (p1[1] - p2[1]);
    }

    @Test
    public void countSmaller() {
        int[] nums = {
            26, 78, 27, 100, 33, 67, 90, 23, 66, 5, 38, 7, 35, 23, 52, 22, 83, 51, 98, 69, 81, 32,
            78, 28, 94, 13, 2, 97, 3, 76, 99, 51, 9, 21, 84, 66, 65, 36, 100, 41
        };
        List<Integer> result = countSmaller(nums);
        logResult(result);
    }

    /**
     * 315. 计算右侧小于当前元素的个数
     *
     * <p>给定一个整数数组 nums，按要求返回一个新数组 counts。数组 counts 有该性质： counts[i] 的值是 nums[i] 右侧小于 nums[i] 的元素的数量。
     *
     * <p>示例:
     *
     * <p>输入: [5,2,6,1] 输出: [2,1,1,0] 解释: 5 的右侧有 2 个更小的元素 (2 和 1). 2 的右侧仅有 1 个更小的元素 (1). 6 的右侧有 1
     * 个更小的元素 (1). 1 的右侧有 0 个更小的元素.
     *
     * @param nums
     * @return
     */
    public List<Integer> countSmaller(int[] nums) {
        // 构建索引数组
        int len = nums.length;

        LinkedList<Integer> list = new LinkedList<>();
        if (len == 0) {
            return list;
        }
        // 从右往左,进行插入排序
        list.addFirst(0);
        for (int i = len - 2; i >= 0; i--) {
            int num = nums[i];
            if (num > nums[len - 1]) {
                list.addFirst(len - i - 1);
                System.arraycopy(nums, i + 1, nums, i, len - i - 1);
                nums[len - 1] = num;
                log.debug("1nums:{}", nums);
                continue;
            }
            if (num < nums[i + 1]) {
                list.addFirst(0);
                log.debug("2nums:{}", nums);
                continue;
            }
            int index = getIndex(nums, i + 1, len - 1, num);
            System.arraycopy(nums, i + 1, nums, i, index - 1 - i);
            nums[index - 1] = num;
            list.addFirst(index - 1 - i);
            log.debug("index:{}", index);
            log.debug("3nums:{}", nums);
        }
        return list;
    }

    private int getIndex(int[] nums, int start, int end, int target) {
        // 二分查找
        int left = start, right = end;
        while (left < right) {
            int mid = (left + right) >> 1;
            if (target > nums[mid]) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }

    @Test
    public void constructArray() {
        int n = 7, k = 5;
        int[] result = constructArray(n, k);
        log.debug("nums:{}", result);
    }

    /**
     * 667. 优美的排列 II
     *
     * <p>给定两个整数 n 和 k，你需要实现一个数组，这个数组包含从 1 到 n 的 n 个不同整数，同时满足以下条件：
     *
     * <p>① 如果这个数组是 [a1, a2, a3, ... , an] ，那么数组 [|a1 - a2|, |a2 - a3|, |a3 - a4|, ... , |an-1 -
     * an|] 中应该有且仅有 k 个不同整数；.
     *
     * <p>② 如果存在多种答案，你只需实现并返回其中任意一种.
     *
     * <p>示例 1:
     *
     * <p>输入: n = 3, k = 1 输出: [1, 2, 3] 解释: [1, 2, 3] 包含 3 个范围在 1-3 的不同整数， 并且 [1, 1] 中有且仅有 1 个不同整数
     * : 1
     *
     * <p>示例 2:
     *
     * <p>输入: n = 3, k = 2 输出: [1, 3, 2] 解释: [1, 3, 2] 包含 3 个范围在 1-3 的不同整数， 并且 [2, 1] 中有且仅有 2 个不同整数:
     * 1 和 2
     *
     * <p>提示:
     *
     * <p>n 和 k 满足条件 1 <= k < n <= 104.
     *
     * @param n
     * @param k
     * @return
     */
    public int[] constructArray(int n, int k) {
        int[] nums = new int[n];
        int index = 0;
        int left = 1, right = n;
        boolean flag = true;
        for (int i = 0; i < k - 1; i++) {
            if (flag) {
                nums[i] = left++;
            } else {
                nums[i] = right--;
            }
            flag = !flag;
        }
        k--;
        for (int i = left; i <= right; i++) {
            nums[k++] = flag ? i : right + left - i;
        }
        return nums;
    }

    /**
     * 659. 分割数组为连续子序列
     *
     * <p>给你一个按升序排序的整数数组 num（可能包含重复数字），请你将它们分割成一个或多个子序列，其中每个子序列都由连续整数组成且长度至少为 3 。
     *
     * <p>如果可以完成上述分割，则返回 true ；否则，返回 false 。
     *
     * <p>示例 1：
     *
     * <p>输入: [1,2,3,3,4,5] 输出: True 解释: 你可以分割出这样两个连续子序列 : 1, 2, 3 3, 4, 5
     *
     * <p>示例 2：
     *
     * <p>输入: [1,2,3,3,4,4,5,5] 输出: True 解释: 你可以分割出这样两个连续子序列 : 1, 2, 3, 4, 5 3, 4, 5
     *
     * <p>示例 3：
     *
     * <p>输入: [1,2,3,4,4,5] 输出: False
     *
     * <p>提示：
     *
     * <p>输入的数组长度范围为 [1, 10000]
     *
     * @param nums
     * @return
     */
    public boolean isPossible(int[] nums) {
        // nc[i]：存储原数组中数字i出现的次数 tail[i]：存储以数字i结尾的且符合题意的连续子序列个数
        // 1. 先去寻找一个长度为3的连续子序列i, i+1, i+2，找到后就将nc[i], nc[i+1],
        // nc[i+2]中对应数字消耗1个（即-1），并将tail[i+2]加1，即以i+2结尾的子序列个数+1。
        // 2. 如果后续发现有能够接在这个连续子序列的数字i+3，
        // 则延长以i+2为结尾的连续子序列到i+3，此时消耗nc[i+3]一个，由于子序列已延长，因此tail[i+2]减1，tail[i+3]加1
        // 在不满足上面的情况下
        // 3. 如果nc[i]为0，说明这个数字已经消耗完，可以不管了
        // 4. 如果nc[i]不为0，说明这个数字多出来了，且无法组成连续子序列，所以可以直接返回false了
        Counter counts = new Counter();
        Counter tail = new Counter();

        for (int num : nums) {
            counts.add(num, 1);
        }
        for (int num : nums) {
            if (counts.get(num) == 0) {
                continue;
            } else if (tail.get(num - 1) > 0) {

                tail.add(num - 1, -1);
                tail.add(num, 1);
            } else if (counts.get(num + 1) > 0 && counts.get(num + 2) > 0) {

                counts.add(num + 1, -1);
                counts.add(num + 2, -1);
                tail.add(num + 2, 1);
            } else {
                return false;
            }
            counts.add(num, -1);
        }

        return true;
    }

    class Counter extends HashMap<Integer, Integer> {
        public int get(int k) {
            return super.getOrDefault(k, 0);
        }

        public void add(int k, int v) {
            put(k, get(k) + v);
        }
    }

    @Test
    public void rangeSum() {
        int[] nums = {1, 2, 3, 4};
        int n = 4, left = 1, right = 5;
        logResult(rangeSum(nums, n, left, right));
    }

    /**
     * 5445. 子数组和排序后的区间和
     *
     * <p>给你一个数组 nums ，它包含 n 个正整数。你需要计算所有非空连续子数组的和，并将它们按升序排序，得到一个新的包含 n * (n + 1) / 2 个数字的数组。
     *
     * <p>请你返回在新数组中下标为 left 到 right （下标从 1 开始）的所有数字和（包括左右端点）。由于答案可能很大，请你将它对 10^9 + 7 取模后返回。
     *
     * <p>示例 1：
     *
     * <p>输入：nums = [1,2,3,4], n = 4, left = 1, right = 5 输出：13 解释：所有的子数组和为 1, 3, 6, 10, 2, 5, 9, 3,
     * 7, 4 。将它们升序排序后，我们得到新的数组 [1, 2, 3, 3, 4, 5, 6, 7, 9, 10] 。下标从 le = 1 到 ri = 5 的和为 1 + 2 + 3 +
     * 3 + 4 = 13 。 示例 2：
     *
     * <p>输入：nums = [1,2,3,4], n = 4, left = 3, right = 4 输出：6 解释：给定数组与示例 1 一样，所以新数组为 [1, 2, 3, 3,
     * 4, 5, 6, 7, 9, 10] 。下标从 le = 3 到 ri = 4 的和为 3 + 3 = 6 。 示例 3：
     *
     * <p>输入：nums = [1,2,3,4], n = 4, left = 1, right = 10 输出：50
     *
     * <p>提示：
     *
     * <p>1 <= nums.length <= 10^3 nums.length == n 1 <= nums[i] <= 100 1 <= left <= right <= n * (n
     * + 1) / 2
     *
     * @param nums
     * @param n
     * @param left
     * @param right
     * @return
     */
    public int rangeSum(int[] nums, int n, int left, int right) {
        int len = nums.length;
        // 归并排序
        int MOD = 1_000_000_007;
        PriorityQueue<int[]> pq = new PriorityQueue<>(n, (a, b) -> Integer.compare(a[0], b[0]));
        for (int i = 0; i < nums.length; i++) {
            pq.offer(new int[] {nums[i], i});
        }
        int result = 0;
        int count = 0;
        while (++count <= right && !pq.isEmpty()) {
            int[] cur = pq.poll();
            if (count >= left) {
                result += cur[0] % MOD;
            }
            if (cur[1] < n - 1) {
                pq.offer(new int[] {cur[0] + nums[cur[1] + 1], cur[1] + 1});
            }
        }
        return result;
    }

    @Test
    public void numIdenticalPairs() {
        int[] nums = {1, 1, 1, 1};
        logResult(numIdenticalPairs(nums));
    }

    /**
     * 5460. 好数对的数目
     *
     * <p>给你一个整数数组 nums 。
     *
     * <p>如果一组数字 (i,j) 满足 nums[i] == nums[j] 且 i < j ，就可以认为这是一组 好数对 。
     *
     * <p>返回好数对的数目。
     *
     * <p>示例 1：
     *
     * <p>输入：nums = [1,2,3,1,1,3] 输出：4 解释：有 4 组好数对，分别是 (0,3), (0,4), (3,4), (2,5) ，下标从 0 开始 示例 2：
     *
     * <p>输入：nums = [1,1,1,1] 输出：6 解释：数组中的每组数字都是好数对 示例 3：
     *
     * <p>输入：nums = [1,2,3] 输出：0
     *
     * <p>提示：
     *
     * <p>1 <= nums.length <= 100 1 <= nums[i] <= 100
     *
     * @param nums
     * @return
     */
    public int numIdenticalPairs(int[] nums) {
        Map<Integer, Integer> countMap = new HashMap<>();
        int result = 0;
        for (int num : nums) {
            int count = countMap.getOrDefault(num, 0);
            if (count > 0) {
                result += count;
            }
            countMap.put(num, count + 1);
        }
        return result;
    }

    /**
     * 350. 两个数组的交集 II
     *
     * <p>给定两个数组，编写一个函数来计算它们的交集。
     *
     * <p>示例 1：
     *
     * <p>输入：nums1 = [1,2,2,1], nums2 = [2,2] 输出：[2,2] 示例 2:
     *
     * <p>输入：nums1 = [4,9,5], nums2 = [9,4,9,8,4] 输出：[4,9]
     *
     * <p>说明：
     *
     * <p>输出结果中每个元素出现的次数，应与元素在两个数组中出现次数的最小值一致。 我们可以不考虑输出结果的顺序。 进阶：
     *
     * <p>如果给定的数组已经排好序呢？你将如何优化你的算法？ 如果 nums1 的大小比 nums2 小很多，哪种方法更优？ 如果 nums2
     * 的元素存储在磁盘上，磁盘内存是有限的，并且你不能一次加载所有的元素到内存中，你该怎么办？
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public int[] intersect(int[] nums1, int[] nums2) {

        Map<Integer, Integer> countMap = new HashMap<>();
        if (nums1.length > nums2.length) {
            int[] tmp = nums1;
            nums1 = nums2;
            nums2 = tmp;
        }
        for (int num : nums1) {
            int count = countMap.getOrDefault(num, 0);
            countMap.put(num, count + 1);
        }
        List<Integer> list = new ArrayList<>();
        for (int num : nums2) {
            int count = countMap.getOrDefault(num, 0);
            if (count > 0) {
                list.add(num);
                countMap.put(num, count - 1);
            }
        }
        int[] nums = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            nums[i] = list.get(i);
        }
        return nums;
    }

    /**
     * 713. 乘积小于K的子数组
     *
     * <p>给定一个正整数数组 nums。
     *
     * <p>找出该数组内乘积小于 k 的连续的子数组的个数。
     *
     * <p>示例 1:
     *
     * <p>输入: nums = [10,5,2,6], k = 100 输出: 8 解释: 8个乘积小于100的子数组分别为: [10], [5], [2], [6], [10,5],
     * [5,2], [2,6], [5,2,6]。 需要注意的是 [10,5,2] 并不是乘积小于100的子数组。 说明:
     *
     * <p>0 < nums.length <= 50000 0 < nums[i] < 1000 0 <= k < 10^6
     *
     * @param nums
     * @param k
     * @return
     */
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        if (k <= 1) {
            return 0;
        }
        int count = 0;
        int prod = 1;
        int left = 0;
        for (int right = 0; right < nums.length; right++) {
            prod *= nums[right];
            while (left < nums.length && prod >= k) {
                prod /= nums[left++];
            }
            count += right - left + 1;
        }

        return count;
    }

    /**
     * 1509. 三次操作后最大值与最小值的最小差
     *
     * <p>给你一个数组 nums ，每次操作你可以选择 nums 中的任意一个元素并将它改成任意值。
     *
     * <p>请你返回三次操作后， nums 中最大值与最小值的差的最小值。
     *
     * <p>示例 1：
     *
     * <p>输入：nums = [5,3,2,4] 输出：0 解释：将数组 [5,3,2,4] 变成 [2,2,2,2]. 最大值与最小值的差为 2-2 = 0 。 示例 2：
     *
     * <p>输入：nums = [1,5,0,10,14] 输出：1 解释：将数组 [1,5,0,10,14] 变成 [1,1,0,1,1] 。 最大值与最小值的差为 1-0 = 1 。 示例
     * 3：
     *
     * <p>输入：nums = [6,6,0,1,1,4,6] 输出：2 示例 4：
     *
     * <p>输入：nums = [1,5,6,14,15] 输出：1
     *
     * <p>提示：
     *
     * <p>1 <= nums.length <= 10^5 -10^9 <= nums[i] <= 10^9 通过次数1,817提交次数3,490
     *
     * @param nums
     * @return
     */
    public int minDifference(int[] nums) {
        if (nums.length < 5) {
            return 0;
        }
        int result = Integer.MAX_VALUE;
        Arrays.sort(nums);
        for (int i = 0; i <= 3; i++) {
            result = Math.min(result, nums[nums.length - 4 + i] - nums[i]);
        }

        return result;
    }

    /**
     * 1438. 绝对差不超过限制的最长连续子数组
     *
     * <p>给你一个整数数组 nums ，和一个表示限制的整数 limit，请你返回最长连续子数组的长度，该子数组中的任意两个元素之间的绝对差必须小于或者等于 limit 。
     *
     * <p>如果不存在满足条件的子数组，则返回 0 。
     *
     * <p>示例 1：
     *
     * <p>输入：nums = [8,2,4,7], limit = 4 输出：2 解释：所有子数组如下： [8] 最大绝对差 |8-8| = 0 <= 4. [8,2] 最大绝对差
     * |8-2| = 6 > 4. [8,2,4] 最大绝对差 |8-2| = 6 > 4. [8,2,4,7] 最大绝对差 |8-2| = 6 > 4. [2] 最大绝对差 |2-2| =
     * 0 <= 4. [2,4] 最大绝对差 |2-4| = 2 <= 4. [2,4,7] 最大绝对差 |2-7| = 5 > 4. [4] 最大绝对差 |4-4| = 0 <= 4.
     * [4,7] 最大绝对差 |4-7| = 3 <= 4. [7] 最大绝对差 |7-7| = 0 <= 4. 因此，满足题意的最长子数组的长度为 2 。 示例 2：
     *
     * <p>输入：nums = [10,1,2,4,7,2], limit = 5 输出：4 解释：满足题意的最长子数组是 [2,4,7,2]，其最大绝对差 |2-7| = 5 <= 5 。
     * 示例 3：
     *
     * <p>输入：nums = [4,2,2,2,4,4,2,2], limit = 0 输出：3
     *
     * <p>提示：
     *
     * <p>1 <= nums.length <= 10^5 1 <= nums[i] <= 10^9 0 <= limit <= 10^9
     *
     * @param nums
     * @param limit
     * @return
     */
    public int longestSubarray(int[] nums, int limit) {
        int max = 0;
        int left = 0;
        Deque<Integer> maxQueue = new LinkedList<>();
        Deque<Integer> minQueue = new LinkedList<>();
        for (int right = 0; right < nums.length; right++) {
            int num = nums[right];
            // 右沿元素进入窗口、维护最大值单调队列
            while (!maxQueue.isEmpty() && nums[maxQueue.peekLast()] < num) {
                maxQueue.pollLast();
            }
            maxQueue.add(right);
            // 右沿元素进入窗口、维护最小值单调队列
            while (!minQueue.isEmpty() && nums[minQueue.peekLast()] > num) {
                minQueue.pollLast();
            }
            minQueue.add(right);

            // 如果当前窗口的最大值最小值的差大于 limit，则不断缩小窗口（左沿++），直至最大值变小或者最小值变大从而满足 limit 限制
            while (!maxQueue.isEmpty()
                    && !minQueue.isEmpty()
                    && nums[maxQueue.peek()] - nums[minQueue.peek()] > limit) {
                if (maxQueue.peek() <= left) maxQueue.poll();
                if (minQueue.peek() <= left) minQueue.poll();
                left++;
            }
            max = Math.max(max, right - left + 1);
        }

        return max;
    }

    /**
     * 1437. 是否所有 1 都至少相隔 k 个元素
     *
     * <p>给你一个由若干 0 和 1 组成的数组 nums 以及整数 k。如果所有 1 都至少相隔 k 个元素，则返回 True ；否则，返回 False 。
     *
     * <p>示例 1：
     *
     * <p>输入：nums = [1,0,0,0,1,0,0,1], k = 2 输出：true 解释：每个 1 都至少相隔 2 个元素。 示例 2：
     *
     * <p>输入：nums = [1,0,0,1,0,1], k = 2 输出：false 解释：第二个 1 和第三个 1 之间只隔了 1 个元素。 示例 3：
     *
     * <p>输入：nums = [1,1,1,1,1], k = 0 输出：true 示例 4：
     *
     * <p>输入：nums = [0,1,0,1], k = 1 输出：true
     *
     * <p>提示：
     *
     * <p>1 <= nums.length <= 10^5 0 <= k <= nums.length nums[i] 的值为 0 或 1
     *
     * @param nums
     * @param k
     * @return
     */
    public boolean kLengthApart(int[] nums, int k) {
        int count = 0;
        int lastIndex = -1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 1) {
                if (lastIndex >= 0 && i - lastIndex - 1 < k) {
                    return false;
                }
                lastIndex = i;
            }
        }

        return true;
    }

    @Test
    public void maxScore() {
        int[] cardPoints = {1, 79, 80, 1, 1, 1, 200, 1};
        int k = 3;
        logResult(maxScore(cardPoints, k));
    }
    /**
     * 1423. 可获得的最大点数
     *
     * <p>几张卡牌 排成一行，每张卡牌都有一个对应的点数。点数由整数数组 cardPoints 给出。
     *
     * <p>每次行动，你可以从行的开头或者末尾拿一张卡牌，最终你必须正好拿 k 张卡牌。
     *
     * <p>你的点数就是你拿到手中的所有卡牌的点数之和。
     *
     * <p>给你一个整数数组 cardPoints 和整数 k，请你返回可以获得的最大点数。
     *
     * <p>示例 1：
     *
     * <p>输入：cardPoints = [1,2,3,4,5,6,1], k = 3 输出：12 解释：第一次行动，不管拿哪张牌，你的点数总是 1
     * 。但是，先拿最右边的卡牌将会最大化你的可获得点数。最优策略是拿右边的三张牌，最终点数为 1 + 6 + 5 = 12 。 示例 2：
     *
     * <p>输入：cardPoints = [2,2,2], k = 2 输出：4 解释：无论你拿起哪两张卡牌，可获得的点数总是 4 。 示例 3：
     *
     * <p>输入：cardPoints = [9,7,7,9,7,7,9], k = 7 输出：55 解释：你必须拿起所有卡牌，可以获得的点数为所有卡牌的点数之和。 示例 4：
     *
     * <p>输入：cardPoints = [1,1000,1], k = 1 输出：1 解释：你无法拿到中间那张卡牌，所以可以获得的最大点数为 1 。 示例 5：
     *
     * <p>输入：cardPoints = [1,79,80,1,1,1,200,1], k = 3 输出：202
     *
     * <p>提示：
     *
     * <p>1 <= cardPoints.length <= 10^5 1 <= cardPoints[i] <= 10^4 1 <= k <= cardPoints.length
     *
     * @param cardPoints
     * @param k
     * @return
     */
    public int maxScore(int[] cardPoints, int k) {
        // 换个思路, 求 len - k个连续元素的最小值即可
        int len = cardPoints.length;
        int sum = 0;
        for (int i = 0; i < len; i++) {
            sum += cardPoints[i];
        }
        if (len == k) {
            return sum;
        }

        int num = 0;
        for (int i = 0; i < len - k; i++) {
            num += cardPoints[i];
        }
        int min = num;
        for (int i = len - k; i < len; i++) {
            num += cardPoints[i];
            num -= cardPoints[i - len + k];
            min = Math.min(num, min);
        }
        return sum - min;
    }

    /**
     * 1409. 查询带键的排列
     *
     * <p>给你一个待查数组 queries ，数组中的元素为 1 到 m 之间的正整数。 请你根据以下规则处理所有待查项 queries[i]（从 i=0 到
     * i=queries.length-1）：
     *
     * <p>一开始，排列 P=[1,2,3,...,m]。 对于当前的 i ，请你找出待查项 queries[i] 在排列 P 中的位置（下标从 0 开始），然后将其从原位置移动到排列 P
     * 的起始位置（即下标为 0 处）。注意， queries[i] 在 P 中的位置就是 queries[i] 的查询结果。 请你以数组形式返回待查数组 queries 的查询结果。
     *
     * <p>示例 1：
     *
     * <p>输入：queries = [3,1,2,1], m = 5 输出：[2,1,2,1] 解释：待查数组 queries 处理如下： 对于 i=0: queries[i]=3,
     * P=[1,2,3,4,5], 3 在 P 中的位置是 2，接着我们把 3 移动到 P 的起始位置，得到 P=[3,1,2,4,5] 。 对于 i=1: queries[i]=1,
     * P=[3,1,2,4,5], 1 在 P 中的位置是 1，接着我们把 1 移动到 P 的起始位置，得到 P=[1,3,2,4,5] 。 对于 i=2: queries[i]=2,
     * P=[1,3,2,4,5], 2 在 P 中的位置是 2，接着我们把 2 移动到 P 的起始位置，得到 P=[2,1,3,4,5] 。 对于 i=3: queries[i]=1,
     * P=[2,1,3,4,5], 1 在 P 中的位置是 1，接着我们把 1 移动到 P 的起始位置，得到 P=[1,2,3,4,5] 。 因此，返回的结果数组为 [2,1,2,1] 。
     * 示例 2：
     *
     * <p>输入：queries = [4,1,2,2], m = 4 输出：[3,1,2,0] 示例 3：
     *
     * <p>输入：queries = [7,5,5,8,3], m = 8 输出：[6,5,0,7,5]
     *
     * <p>提示：
     *
     * <p>1 <= m <= 10^3 1 <= queries.length <= m 1 <= queries[i] <= m
     *
     * @param queries
     * @param m
     * @return
     */
    public int[] processQueries(int[] queries, int m) {
        int[] indexs = new int[m + 1];
        // 用来记录 数字 i 的 索引
        for (int i = 1; i <= m; i++) {
            indexs[i] = i;
        }
        for (int i = 0; i < queries.length; i++) {
            int num = queries[i];
            queries[i] = indexs[num] - 1;
            for (int j = 1; j <= m; j++) {
                if (indexs[j] < indexs[num]) {
                    indexs[j]++;
                }
            }
            indexs[num] = 1;
        }
        return queries;
    }

    /**
     * LCP 08. 剧情触发时间
     *
     * <p>在战略游戏中，玩家往往需要发展自己的势力来触发各种新的剧情。一个势力的主要属性有三种，分别是文明等级（C），资源储备（R）以及人口数量（H）。在游戏开始时（第 0
     * 天），三种属性的值均为 0。
     *
     * <p>随着游戏进程的进行，每一天玩家的三种属性都会对应增加，我们用一个二维数组 increase 来表示每天的增加情况。这个二维数组的每个元素是一个长度为 3 的一维数组，例如
     * [[1,2,1],[3,4,2]] 表示第一天三种属性分别增加 1,2,1 而第二天分别增加 3,4,2。
     *
     * <p>所有剧情的触发条件也用一个二维数组 requirements 表示。这个二维数组的每个元素是一个长度为 3 的一维数组，对于某个剧情的触发条件 c[i], r[i],
     * h[i]，如果当前 C >= c[i] 且 R >= r[i] 且 H >= h[i] ，则剧情会被触发。
     *
     * <p>根据所给信息，请计算每个剧情的触发时间，并以一个数组返回。如果某个剧情不会被触发，则该剧情对应的触发时间为 -1 。
     *
     * <p>示例 1：
     *
     * <p>输入： increase = [[2,8,4],[2,5,0],[10,9,8]] requirements =
     * [[2,11,3],[15,10,7],[9,17,12],[8,1,14]]
     *
     * <p>输出: [2,-1,3,-1]
     *
     * <p>解释：
     *
     * <p>初始时，C = 0，R = 0，H = 0
     *
     * <p>第 1 天，C = 2，R = 8，H = 4
     *
     * <p>第 2 天，C = 4，R = 13，H = 4，此时触发剧情 0
     *
     * <p>第 3 天，C = 14，R = 22，H = 12，此时触发剧情 2
     *
     * <p>剧情 1 和 3 无法触发。
     *
     * <p>示例 2：
     *
     * <p>输入： increase = [[0,4,5],[4,8,8],[8,6,1],[10,10,0]] requirements =
     * [[12,11,16],[20,2,6],[9,2,6],[10,18,3],[8,14,9]]
     *
     * <p>输出: [-1,4,3,3,3]
     *
     * <p>示例 3：
     *
     * <p>输入： increase = [[1,1,1]] requirements = [[0,0,0]]
     *
     * <p>输出: [0]
     *
     * <p>限制：
     *
     * <p>1 <= increase.length <= 10000 1 <= requirements.length <= 100000 0 <= increase[i] <= 10 0
     * <= requirements[i] <= 100000
     *
     * @param increase
     * @param requirements
     * @return
     */
    public int[] getTriggerTime(int[][] increase, int[][] requirements) {
        int[] result = new int[requirements.length];
        // 累加属性值,形成一个玩家每天属性值的数组
        for (int i = 1; i < increase.length; i++) {
            increase[i][0] += increase[i - 1][0];
            increase[i][1] += increase[i - 1][1];
            increase[i][2] += increase[i - 1][2];
        }
        // 二分查找 遍历触发条件，得到每个触发条件对应的天数
        for (int i = 0; i < requirements.length; i++) {
            if (requirements[i][0] == 0 && requirements[i][1] == 0 && requirements[i][2] == 0) {
                result[i] = 0;
                continue;
            }
            // 二分查找, 查看第几天符合要求
            int left = 0, right = increase.length - 1;
            while (left <= right) {
                int mid = (left + right) >> 1;
                if (increase[mid][0] < requirements[i][0]
                        || increase[mid][1] < requirements[i][1]
                        || increase[mid][2] < requirements[i][2]) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }
            if (left < increase.length
                    && increase[left][0] >= requirements[i][0]
                    && increase[left][1] >= requirements[i][1]
                    && increase[left][2] >= requirements[i][2]) {
                result[i] = left + 1;
            } else {
                result[i] = -1;
            }
        }

        return result;
    }

    @Test
    public void minTime2() {
        int[] time = {1, 2, 3, 3, 3};
        int m = 2;
        logResult(minTime(time, m));
    }

    /**
     * LCP 12. 小张刷题计划
     *
     * <p>为了提高自己的代码能力，小张制定了 LeetCode 刷题计划，他选中了 LeetCode 题库中的 n 道题，编号从 0 到 n-1，并计划在 m
     * 天内按照题目编号顺序刷完所有的题目（注意，小张不能用多天完成同一题）。
     *
     * <p>在小张刷题计划中，小张需要用 time[i] 的时间完成编号 i
     * 的题目。此外，小张还可以使用场外求助功能，通过询问他的好朋友小杨题目的解法，可以省去该题的做题时间。为了防止“小张刷题计划”变成“小杨刷题计划”，小张每天最多使用一次求助。
     *
     * <p>我们定义 m 天中做题时间最多的一天耗时为 T（小杨完成的题目不计入做题总时间）。请你帮小张求出最小的 T是多少。
     *
     * <p>示例 1：
     *
     * <p>输入：time = [1,2,3,3], m = 2
     *
     * <p>输出：3
     *
     * <p>解释：第一天小张完成前三题，其中第三题找小杨帮忙；第二天完成第四题，并且找小杨帮忙。这样做题时间最多的一天花费了 3 的时间，并且这个值是最小的。
     *
     * <p>示例 2：
     *
     * <p>输入：time = [999,999,999], m = 4
     *
     * <p>输出：0
     *
     * <p>解释：在前三天中，小张每天求助小杨一次，这样他可以在三天内完成所有的题目并不花任何时间。
     *
     * <p>限制：
     *
     * <p>1 <= time.length <= 10^5 1 <= time[i] <= 10000 1 <= m <= 1000
     *
     * @param time
     * @param m
     * @return
     */
    public int minTime(int[] time, int m) {
        // 二分法
        int left = 0, right = 0;
        for (int t : time) {
            right += t;
        }
        while (left <= right) {
            int mid = (left + right) >> 1;
            if (canSplitTime(time, m, mid)) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    /**
     * 判断 limit 时间下能否完成刷题
     *
     * @param time
     * @param limit
     * @return
     */
    private boolean canSplitTime(int[] time, int m, int limit) {
        int cnt = 0;
        int sum = 0;
        int maxT = 0;
        for (int t : time) {
            sum += t;
            maxT = Math.max(maxT, t);
            if (sum - maxT > limit) {
                if (++cnt == m) return false;
                sum = t;
                maxT = t;
            }
        }
        return true;
    }

    /**
     * 769. 最多能完成排序的块
     *
     * <p>数组arr是[0, 1, ..., arr.length -
     * 1]的一种排列，我们将这个数组分割成几个“块”，并将这些块分别进行排序。之后再连接起来，使得连接的结果和按升序排序后的原数组相同。
     *
     * <p>我们最多能将数组分成多少块？
     *
     * <p>示例 1:
     *
     * <p>输入: arr = [4,3,2,1,0] 输出: 1 解释: 将数组分成2块或者更多块，都无法得到所需的结果。 例如，分成 [4, 3], [2, 1, 0] 的结果是 [3,
     * 4, 0, 1, 2]，这不是有序的数组。 示例 2:
     *
     * <p>输入: arr = [1,0,2,3,4] 输出: 4 解释: 我们可以把它分成两块，例如 [1, 0], [2, 3, 4]。 然而，分成 [1, 0], [2], [3],
     * [4] 可以得到最多的块数。 注意:
     *
     * <p>arr 的长度在 [1, 10] 之间。 arr[i]是 [0, 1, ..., arr.length - 1]的一种排列。
     *
     * @param arr
     * @return
     */
    public int maxChunksToSorted(int[] arr) {
        int count = 0, max = 0;
        for (int i = 0; i < arr.length; ++i) {
            max = Math.max(max, arr[i]);
            if (max == i) {
                count++;
            }
        }
        return count;
    }

    /**
     * 775. 全局倒置与局部倒置
     *
     * <p>数组 A 是 [0, 1, ..., N - 1] 的一种排列，N 是数组 A 的长度。全局倒置指的是 i,j 满足 0 <= i < j < N 并且 A[i] > A[j]
     * ，局部倒置指的是 i 满足 0 <= i < N 并且 A[i] > A[i+1] 。
     *
     * <p>当数组 A 中全局倒置的数量等于局部倒置的数量时，返回 true 。
     *
     * <p>示例 1:
     *
     * <p>输入: A = [1,0,2] 输出: true 解释: 有 1 个全局倒置，和 1 个局部倒置。 示例 2:
     *
     * <p>输入: A = [1,2,0] 输出: false 解释: 有 2 个全局倒置，和 1 个局部倒置。 注意:
     *
     * <p>A 是 [0, 1, ..., A.length - 1] 的一种排列 A 的长度在 [1, 5000]之间 这个问题的时间限制已经减少了。
     *
     * @param A
     * @return
     */
    public boolean isIdealPermutation(int[] A) {
        int len = A.length;
        int min = Integer.MAX_VALUE;
        for (int i = len - 1; i >= 2; i--) {
            min = Math.min(min, A[i]);
            if (A[i - 2] > min) {
                return false;
            }
        }
        return false;
    }

    /**
     * 5475. 统计好三元组
     *
     * <p>给你一个整数数组 arr ，以及 a、b 、c 三个整数。请你统计其中好三元组的数量。
     *
     * <p>如果三元组 (arr[i], arr[j], arr[k]) 满足下列全部条件，则认为它是一个 好三元组 。
     *
     * <p>0 <= i < j < k < arr.length |arr[i] - arr[j]| <= a |arr[j] - arr[k]| <= b |arr[i] -
     * arr[k]| <= c 其中 |x| 表示 x 的绝对值。
     *
     * <p>返回 好三元组的数量 。
     *
     * <p>示例 1：
     *
     * <p>输入：arr = [3,0,1,1,9,7], a = 7, b = 2, c = 3 输出：4 解释：一共有 4 个好三元组：[(3,0,1), (3,0,1),
     * (3,1,1), (0,1,1)] 。 示例 2：
     *
     * <p>输入：arr = [1,1,2,2,3], a = 0, b = 0, c = 1 输出：0 解释：不存在满足所有条件的三元组。
     *
     * <p>提示：
     *
     * <p>3 <= arr.length <= 100 0 <= arr[i] <= 1000 0 <= a, b, c <= 1000
     *
     * @param arr
     * @param a
     * @param b
     * @param c
     * @return
     */
    public int countGoodTriplets(int[] arr, int a, int b, int c) {
        int count = 0;
        int len = arr.length;
        for (int i = 0; i < len - 2; i++) {
            for (int j = i + 1; j < len - 1; j++) {
                for (int k = j + 1; k < len; k++) {
                    if (Math.abs(arr[i] - arr[j]) <= a
                            && Math.abs(arr[j] - arr[k]) <= b
                            && Math.abs(arr[i] - arr[k]) <= c) {
                        count++;
                    }
                }
            }
        }

        return count;
    }

    @Test
    public void getWinner() {
        int[] arr = {1, 25, 35, 42, 68, 70};
        int k = 1;
        logResult(getWinner(arr, k));
    }

    /**
     * 5476. 找出数组游戏的赢家
     *
     * <p>给你一个由 不同 整数组成的整数数组 arr 和一个整数 k 。
     *
     * <p>每回合游戏都在数组的前两个元素（即 arr[0] 和 arr[1] ）之间进行。比较 arr[0] 与 arr[1] 的大小，较大的整数将会取得这一回合的胜利并保留在位置 0
     * ，较小的整数移至数组的末尾。当一个整数赢得 k 个连续回合时，游戏结束，该整数就是比赛的 赢家 。
     *
     * <p>返回赢得比赛的整数。
     *
     * <p>题目数据 保证 游戏存在赢家。
     *
     * <p>示例 1：
     *
     * <p>输入：arr = [2,1,3,5,4,6,7], k = 2 输出：5 解释：一起看一下本场游戏每回合的情况：
     *
     * <p>因此将进行 4 回合比赛，其中 5 是赢家，因为它连胜 2 回合。 示例 2：
     *
     * <p>输入：arr = [3,2,1], k = 10 输出：3 解释：3 将会在前 10 个回合中连续获胜。 示例 3：
     *
     * <p>输入：arr = [1,9,8,2,3,7,6,4,5], k = 7 输出：9 示例 4：
     *
     * <p>输入：arr = [1,11,22,33,44,55,66,77,88,99], k = 1000000000 输出：99
     *
     * <p>提示：
     *
     * <p>2 <= arr.length <= 10^5 1 <= arr[i] <= 10^6 arr 所含的整数 各不相同 。 1 <= k <= 10^9
     *
     * @param arr
     * @param k
     * @return
     */
    public int getWinner(int[] arr, int k) {
        int max = 0;
        Queue<Integer> queue = new LinkedList<>();
        for (int num : arr) {
            queue.offer(num);
            max = Math.max(num, max);
        }
        if (k >= arr.length) {
            return max;
        }
        int win = queue.poll();
        int count = 0;
        while (true) {
            int num = queue.poll();
            if (num > win) {
                count = 1;

                int tmp = win;
                win = num;
                num = tmp;

            } else {
                count++;
            }
            if (count == k) {
                break;
            }
            queue.offer(num);
        }

        return win;
    }

    @Test
    public void minSwaps() {
        int[][] grid = {{0, 0, 0}, {1, 1, 0}, {1, 0, 0}};

        logResult(minSwaps(grid));
    }
    /**
     * 5477. 排布二进制网格的最少交换次数
     *
     * <p>给你一个 n x n 的二进制网格 grid，每一次操作中，你可以选择网格的 相邻两行 进行交换。
     *
     * <p>一个符合要求的网格需要满足主对角线以上的格子全部都是 0 。
     *
     * <p>请你返回使网格满足要求的最少操作次数，如果无法使网格符合要求，请你返回 -1 。
     *
     * <p>主对角线指的是从 (1, 1) 到 (n, n) 的这些格子。
     *
     * <p>示例 1：
     *
     * <p>输入：grid = [[0,0,1],[1,1,0],[1,0,0]] 输出：3 示例 2：
     *
     * <p>输入：grid = [[0,1,1,0],[0,1,1,0],[0,1,1,0],[0,1,1,0]] 输出：-1 解释：所有行都是一样的，交换相邻行无法使网格符合要求。 示例
     * 3：
     *
     * <p>输入：grid = [[1,0,0],[1,1,0],[1,1,1]] 输出：0
     *
     * <p>提示：
     *
     * <p>n == grid.length n == grid[i].length 1 <= n <= 200 grid[i][j] 要么是 0 要么是 1 。
     *
     * @param grid
     * @return
     */
    public int minSwaps(int[][] grid) {
        int len = grid.length;
        int[] rowZeroCount = new int[len];
        for (int i = 0; i < len; i++) {
            int count = 0;
            for (int j = len - 1; j > 0; j--) {
                if (grid[i][j] == 1) {
                    break;
                }
                count++;
            }
            rowZeroCount[i] = count;
        }
        int count = 0;
        log.debug("count:{}", rowZeroCount);

        for (int i = 0; i < len - 1; i++) {
            if (rowZeroCount[i] >= len - i - 1) {
                continue; // 满足条件，该行直接跳过
            } else { // 不满足条件
                int j = i; // 用新参数遍历找满足条件的后缀0
                for (; j < len; j++) {
                    if (rowZeroCount[j] >= len - i - 1) break;
                }
                if (j == len) {
                    return -1; // 找不到，直接返回-1
                }
                for (; j > i; j--) { // 找到了最先满足条件的后缀0个数

                    // 每一行交换上去
                    int tmp = rowZeroCount[j];
                    rowZeroCount[j] = rowZeroCount[j - 1];
                    rowZeroCount[j - 1] = tmp;
                    count++; // 记录交换次数
                }
            }
        }
        log.debug("count:{}", rowZeroCount);

        return count;
    }

    /**
     * 1072. 按列翻转得到最大值等行数
     *
     * <p>给定由若干 0 和 1 组成的矩阵 matrix，从中选出任意数量的列并翻转其上的 每个 单元格。翻转后，单元格的值从 0 变成 1，或者从 1 变为 0 。
     *
     * <p>返回经过一些翻转后，行上所有值都相等的最大行数。
     *
     * <p>示例 1：
     *
     * <p>输入：[[0,1],[1,1]] 输出：1 解释：不进行翻转，有 1 行所有值都相等。 示例 2：
     *
     * <p>输入：[[0,1],[1,0]] 输出：2 解释：翻转第一列的值之后，这两行都由相等的值组成。 示例 3：
     *
     * <p>输入：[[0,0,0],[0,0,1],[1,1,0]] 输出：2 解释：翻转前两列的值之后，后两行由相等的值组成。
     *
     * <p>提示：
     *
     * <p>1 <= matrix.length <= 300 1 <= matrix[i].length <= 300 所有 matrix[i].length 都相等
     * matrix[i][j] 为 0 或 1
     *
     * @param matrix
     * @return
     */
    public int maxEqualRowsAfterFlips(int[][] matrix) {
        int max = 0;
        Map<String, Integer> map = new HashMap<>();
        // 按照数字出现的连续次数定义特征，比如 11100110 -> 3221，这样的好处是，00011001 同样也是 3221
        for (int[] nums : matrix) {
            int count = 1;
            StringBuilder sb = new StringBuilder();
            for (int i = 1; i < nums.length; i++) {
                if (nums[i] == nums[i - 1]) {
                    count++;
                } else {
                    sb.append(count);
                    count = 1;
                }
            }
            sb.append(count);
            int n = map.getOrDefault(sb.toString(), 0);
            map.put(sb.toString(), n + 1);
            max = Math.max(max, n + 1);
        }

        return max;
    }

    @Test
    public void addNegabinary() {
        int[] arr1 = {1, 1, 1, 1, 1}, arr2 = {1, 0, 1};
        int[] nums = addNegabinary(arr1, arr2);
        log.debug("nums:{}", nums);
    }

    /**
     * 1073. 负二进制数相加
     *
     * <p>给出基数为 -2 的两个数 arr1 和 arr2，返回两数相加的结果。
     *
     * <p>数字以 数组形式 给出：数组由若干 0 和 1 组成，按最高有效位到最低有效位的顺序排列。例如，arr = [1,1,0,1] 表示数字 (-2)^3 + (-2)^2 +
     * (-2)^0 = -3。数组形式 的数字也同样不含前导零：以 arr 为例，这意味着要么 arr == [0]，要么 arr[0] == 1。
     *
     * <p>返回相同表示形式的 arr1 和 arr2 相加的结果。两数的表示形式为：不含前导零、由若干 0 和 1 组成的数组。
     *
     * <p>示例：
     *
     * <p>输入：arr1 = [1,1,1,1,1], arr2 = [1,0,1] 输出：[1,0,0,0,0] 解释：arr1 表示 11，arr2 表示 5，输出表示 16 。
     *
     * <p>提示：
     *
     * <p>1 <= arr1.length <= 1000 1 <= arr2.length <= 1000 arr1 和 arr2 都不含前导零 arr1[i] 为 0 或 1
     * arr2[i] 为 0 或 1 通过次数1,418提交次数4,517
     *
     * @param arr1
     * @param arr2
     * @return
     */
    public int[] addNegabinary(int[] arr1, int[] arr2) {
        if (arr1[0] == 0) return arr2;
        if (arr2[0] == 0) return arr1;
        int len1 = arr1.length, len2 = arr2.length;
        int len = Math.max(len1, len2) + 2;
        int[] nums = new int[len];
        // 奇数位 -1
        // 当前项累加超过1时，进位项为-1。如偶数位的两个1结合后进位为1，但由于前一项时奇数项为负值，所以进位-1去减小奇数项的当前值；奇数为类比。
        // 当前项累加低于0时，进位项为1。这种情况是当前两个数为0且之前的进位是-1，因此要做的事情是当前位-1 + 2 = 1， 然后再下一时刻的进制位置为1.
        int index = 0;

        int m = len1 - 1, n = len2 - 1;
        int carry = 0;
        while (m >= 0 || n >= 0) {
            int p = m >= 0 ? arr1[m--] : 0;
            int q = n >= 0 ? arr2[n--] : 0;
            int sum = p + q + carry;
            switch (sum) {
                case -1:
                    nums[len - 1 - index] = 1;
                    carry = 1;
                    break;
                case 0:
                    nums[len - 1 - index] = 0;
                    carry = 0;
                    break;
                case 1:
                    nums[len - 1 - index] = 1;
                    carry = 0;
                    break;
                case 2:
                    nums[len - 1 - index] = 0;
                    carry = -1;
                    break;
                case 3:
                    nums[len - 1 - index] = 1;
                    carry = -1;
                    break;
            }
            index++;
        }
        // 最后还差时
        if (carry == -1) {
            nums[len - 1 - index++] = 1;
            nums[len - 1 - index] = 1;
        }
        index = 0;
        while (index < len && nums[index] == 0) {
            index++;
        }
        if (index == len) {
            return new int[] {0};
        }
        int[] result = new int[len - index];
        if (len - index >= 0) System.arraycopy(nums, index, result, 0, len - index);

        return result;
    }

    @Test
    public void shortestPathBinaryMatrix() {
        int[][] grid = {{0, 0, 0}, {1, 1, 0}, {1, 1, 0}};
        logResult(shortestPathBinaryMatrix(grid));
    }

    /**
     * 1091. 二进制矩阵中的最短路径
     *
     * <p>在一个 N × N 的方形网格中，每个单元格有两种状态：空（0）或者阻塞（1）。
     *
     * <p>一条从左上角到右下角、长度为 k 的畅通路径，由满足下述条件的单元格 C_1, C_2, ..., C_k 组成：
     *
     * <p>相邻单元格 C_i 和 C_{i+1} 在八个方向之一上连通（此时，C_i 和 C_{i+1} 不同且共享边或角） C_1 位于 (0, 0)（即，值为 grid[0][0]）
     * C_k 位于 (N-1, N-1)（即，值为 grid[N-1][N-1]） 如果 C_i 位于 (r, c)，则 grid[r][c] 为空（即，grid[r][c] == 0）
     * 返回这条从左上角到右下角的最短畅通路径的长度。如果不存在这样的路径，返回 -1 。
     *
     * <p>示例 1：
     *
     * <p>输入：[[0,1],[1,0]]
     *
     * <p>输出：2
     *
     * <p>示例 2：
     *
     * <p>输入：[[0,0,0],[1,1,0],[1,1,0]]
     *
     * <p>输出：4
     *
     * <p>提示：
     *
     * <p>1 <= grid.length == grid[0].length <= 100 grid[i][j] 为 0 或 1
     *
     * @param grid
     * @return
     */
    public int shortestPathBinaryMatrix(int[][] grid) {
        int n = grid.length;
        int[] dirRow = {-1, 1, 0, 0, -1, -1, 1, 1};
        int[] dirCol = {0, 0, -1, 1, -1, 1, -1, 1};
        if (grid[0][0] == 1 || grid[n - 1][n - 1] == 1) {
            return -1;
        }
        if (n == 1 && grid[0][0] == 0) {
            return 1;
        }
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[] {0, 0});
        int path = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            path++;
            for (int i = 0; i < size; i++) {
                int[] nums = queue.poll();
                int row = nums[0], col = nums[1];
                for (int j = 0; j < 8; j++) {
                    int rowIndex = row + dirRow[j];
                    int colIndex = col + dirCol[j];
                    if (inArea(rowIndex, colIndex, n, n) && grid[rowIndex][colIndex] == 0) {
                        if (rowIndex == n - 1 && colIndex == n - 1) {
                            return path;
                        }
                        grid[rowIndex][colIndex] = path;
                        queue.offer(new int[] {rowIndex, colIndex});
                    }
                }
            }
        }
        return -1;
    }

    /**
     * 1093. 大样本统计
     *
     * <p>我们对 0 到 255 之间的整数进行采样，并将结果存储在数组 count 中：count[k] 就是整数 k 的采样个数。
     *
     * <p>我们以 浮点数 数组的形式，分别返回样本的最小值、最大值、平均值、中位数和众数。其中，众数是保证唯一的。
     *
     * <p>我们先来回顾一下中位数的知识：
     *
     * <p>如果样本中的元素有序，并且元素数量为奇数时，中位数为最中间的那个元素； 如果样本中的元素有序，并且元素数量为偶数时，中位数为中间的两个元素的平均值。
     *
     * <p>示例 1：
     *
     * <p>输入：count =
     * [0,1,3,4,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0]
     * 输出：[1.00000,3.00000,2.37500,2.50000,3.00000] 示例 2：
     *
     * <p>输入：count =
     * [0,4,3,2,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0]
     * 输出：[1.00000,4.00000,2.18182,2.00000,1.00000]
     *
     * <p>提示：
     *
     * <p>count.length == 256 1 <= sum(count) <= 10^9 计数表示的众数是唯一的 答案与真实值误差在 10^-5 以内就会被视为正确答案
     *
     * @param count
     * @return
     */
    public double[] sampleStats(int[] count) {
        int min = -1, max = 0, node = 0;
        int len = count.length;
        int totalCount = 0;
        long sum = 0;
        int maxCount = 0;
        for (int i = 0; i < len; i++) {
            if (count[i] > 0) {
                sum += i * count[i];
                totalCount += count[i];
                if (min < 0.0) {
                    min = i;
                }
                max = i;
                if (count[i] > maxCount) {
                    maxCount = count[i];
                    node = i;
                }
            }
        }
        double avg = (double) sum / (double) totalCount;

        int helf = totalCount >> 1;
        int curCount = 0;
        int a = -1, b = -1;
        for (int i = 0; i < len; i++) {
            int cnt = count[i];
            curCount += cnt;
            if (a == -1 && curCount >= helf) {
                a = i;
            }
            if (b == -1 && curCount >= helf + 1) {
                b = i;
            }
        }
        double mid;
        if ((totalCount & 1) == 1) {
            mid = b;
        } else {
            mid = (a + b) / 2.0;
        }
        return new double[] {min, max, avg, mid, node};
    }

    @Test
    public void largestValsFromLabels() {
        int[] values = {9, 8, 8, 7, 6}, labels = {0, 0, 0, 1, 1};
        int num_wanted = 3, use_limit = 2;
        logResult(largestValsFromLabels(values, labels, num_wanted, use_limit));
    }

    /**
     * 1090. 受标签影响的最大值
     *
     * <p>我们有一个项的集合，其中第 i 项的值为 values[i]，标签为 labels[i]。
     *
     * <p>我们从这些项中选出一个子集 S，这样一来：
     *
     * <p>|S| <= num_wanted 对于任意的标签 L，子集 S 中标签为 L 的项的数目总满足 <= use_limit。 返回子集 S 的最大可能的 和。
     *
     * <p>示例 1：
     *
     * <p>输入：values = [5,4,3,2,1], labels = [1,1,2,2,3], num_wanted = 3, use_limit = 1 输出：9
     * 解释：选出的子集是第一项，第三项和第五项。 示例 2：
     *
     * <p>输入：values = [5,4,3,2,1], labels = [1,3,3,3,2], num_wanted = 3, use_limit = 2 输出：12
     * 解释：选出的子集是第一项，第二项和第三项。 示例 3：
     *
     * <p>输入：values = [9,8,8,7,6], labels = [0,0,0,1,1], num_wanted = 3, use_limit = 1 输出：16
     * 解释：选出的子集是第一项和第四项。 示例 4：
     *
     * <p>输入：values = [9,8,8,7,6], labels = [0,0,0,1,1], num_wanted = 3, use_limit = 2 输出：24
     * 解释：选出的子集是第一项，第二项和第四项。
     *
     * <p>提示：
     *
     * <p>1 <= values.length == labels.length <= 20000 0 <= values[i], labels[i] <= 20000 1 <=
     * num_wanted, use_limit <= values.length
     *
     * @param values
     * @param labels
     * @param num_wanted
     * @param use_limit
     * @return
     */
    public int largestValsFromLabels(int[] values, int[] labels, int num_wanted, int use_limit) {
        // 第一点要求 |S| <= num_wanted 是说选择的总数不能超过 num_wanted
        // 第二点要求是说同一个标签下的项，选择的数量不能超过 use_limit

        // 使用二位数组记录 values 和lebels
        int len = values.length;
        int[][] pairs = new int[len][2];
        for (int i = 0; i < len; i++) {
            pairs[i][0] = values[i];
            pairs[i][1] = labels[i];
        }
        Arrays.sort(pairs, (a, b) -> b[0] - a[0]);
        int num = 0;
        logResult(pairs);
        Map<Integer, Integer> counts = new HashMap<>();
        int result = 0;
        // 贪心
        for (int i = 0; i < len; i++) {
            if (num == num_wanted) {
                break;
            }
            int label = pairs[i][1];
            int count = counts.getOrDefault(label, 0);
            if (count == use_limit) {
                continue;
            }
            result += pairs[i][0];
            counts.put(label, count + 1);
            num++;
        }

        return result;
    }

    /**
     * 1094. 拼车
     *
     * <p>假设你是一位顺风车司机，车上最初有 capacity 个空座位可以用来载客。由于道路的限制，车 只能 向一个方向行驶（也就是说，不允许掉头或改变方向，你可以将其想象为一个向量）。
     *
     * <p>这儿有一份乘客行程计划表 trips[][]，其中 trips[i] = [num_passengers, start_location, end_location] 包含了第 i
     * 组乘客的行程信息：
     *
     * <p>必须接送的乘客数量； 乘客的上车地点； 以及乘客的下车地点。 这些给出的地点位置是从你的 初始 出发位置向前行驶到这些地点所需的距离（它们一定在你的行驶方向上）。
     *
     * <p>请你根据给出的行程计划表和车子的座位数，来判断你的车是否可以顺利完成接送所有乘客的任务（当且仅当你可以在所有给定的行程中接送所有乘客时，返回 true，否则请返回 false）。
     *
     * <p>示例 1：
     *
     * <p>输入：trips = [[2,1,5],[3,3,7]], capacity = 4 输出：false 示例 2：
     *
     * <p>输入：trips = [[2,1,5],[3,3,7]], capacity = 5 输出：true 示例 3：
     *
     * <p>输入：trips = [[2,1,5],[3,5,7]], capacity = 3 输出：true 示例 4：
     *
     * <p>输入：trips = [[3,2,7],[3,7,9],[8,3,9]], capacity = 11 输出：true
     *
     * <p>提示：
     *
     * <p>你可以假设乘客会自觉遵守 “先下后上” 的良好素质 trips.length <= 1000 trips[i].length == 3 1 <= trips[i][0] <=
     * 100 0 <= trips[i][1] < trips[i][2] <= 1000 1 <= capacity <= 100000
     *
     * @param trips
     * @param capacity
     * @return
     */
    public boolean carPooling(int[][] trips, int capacity) {
        int[] allCap = new int[1001];
        // Arrays.sort(trips, (a, b) -> a[1] == b[1] ? a[2] - b[2] : a[1] - b[1]);
        for (int[] trip : trips) {
            allCap[trip[1]] += trip[0];
            allCap[trip[2]] -= trip[0];
        }
        int num = 0;
        for (int cap : allCap) {
            num += cap;
            // 超过最大容量
            if (num > capacity) {
                return false;
            }
        }
        return true;
    }

    @Test
    public void corpFlightBookings() {
        int[][] bookings = {{1, 2, 10}, {2, 3, 20}, {2, 5, 25}};
        int n = 5;
        logResult(corpFlightBookings(bookings, n));
    }
    /**
     * 1109. 航班预订统计
     *
     * <p>这里有 n 个航班，它们分别从 1 到 n 进行编号。
     *
     * <p>我们这儿有一份航班预订表，表中第 i 条预订记录 bookings[i] = [i, j, k] 意味着我们在从 i 到 j 的每个航班上预订了 k 个座位。
     *
     * <p>请你返回一个长度为 n 的数组 answer，按航班编号顺序返回每个航班上预订的座位数。
     *
     * <p>示例：
     *
     * <p>输入：bookings = [[1,2,10],[2,3,20],[2,5,25]], n = 5 输出：[10,55,45,25,25]
     *
     * <p>提示：
     *
     * <p>1 <= bookings.length <= 20000 1 <= bookings[i][0] <= bookings[i][1] <= n <= 20000 1 <=
     * bookings[i][2] <= 10000
     *
     * @param bookings
     * @param n
     * @return
     */
    public int[] corpFlightBookings(int[][] bookings, int n) {
        int[] result = new int[n];
        for (int[] booking : bookings) {
            result[booking[0] - 1] += booking[2];
            if (booking[1] < n) {
                result[booking[1]] -= booking[2];
            }
        }
        log.debug("result:{}", result);
        for (int i = 1; i < n; i++) {
            result[i] += result[i - 1];
        }

        return result;
    }

    @Test
    public void findKthPositive() {
        int[] arr = {2, 3, 4, 7, 11};
        int k = 5;
        logResult(findKthPositive(arr, k));
    }

    /**
     * 5468. 第 k 个缺失的正整数
     *
     * <p>给你一个 严格升序排列 的正整数数组 arr 和一个整数 k 。
     *
     * <p>请你找到这个数组里第 k 个缺失的正整数。
     *
     * <p>示例 1：
     *
     * <p>输入：arr = [2,3,4,7,11], k = 5 输出：9 解释：缺失的正整数包括 [1,5,6,8,9,10,12,13,...] 。第 5 个缺失的正整数为 9 。
     * 示例 2：
     *
     * <p>输入：arr = [1,2,3,4], k = 2 输出：6 解释：缺失的正整数包括 [5,6,7,...] 。第 2 个缺失的正整数为 6 。
     *
     * <p>提示：
     *
     * <p>1 <= arr.length <= 1000 1 <= arr[i] <= 1000 1 <= k <= 1000 对于所有 1 <= i < j <= arr.length 的
     * i 和 j 满足 arr[i] < arr[j]
     *
     * @param arr
     * @param k
     * @return
     */
    public int findKthPositive(int[] arr, int k) {
        boolean[] nums = new boolean[1001];
        for (int num : arr) {
            nums[num] = true;
        }
        for (int i = 1; i < nums.length; i++) {
            if (!nums[i]) {
                k--;
            }
            if (k == 0) {
                return i;
            }
        }

        return 1000 + k;
    }

    @Test
    public void maxNonOverlapping() {
        int[] nums = {0, 0, 0};
        int target = 0;
        logResult(maxNonOverlapping(nums, target));
    }

    /**
     * 5471. 和为目标值的最大数目不重叠非空子数组数目
     *
     * <p>给你一个数组 nums 和一个整数 target 。
     *
     * <p>请你返回 非空不重叠 子数组的最大数目，且每个子数组中数字和都为 target 。
     *
     * <p>示例 1：
     *
     * <p>输入：nums = [1,1,1,1,1], target = 2 输出：2 解释：总共有 2 个不重叠子数组（加粗数字表示） [1,1,1,1,1] ，它们的和为目标值 2 。
     * 示例 2：
     *
     * <p>输入：nums = [-1,3,5,1,4,2,-9], target = 6 输出：2 解释：总共有 3 个子数组和为 6 。 ([5,1], [4,2],
     * [3,5,1,4,2,-9]) 但只有前 2 个是不重叠的。 示例 3：
     *
     * <p>输入：nums = [-2,6,6,3,5,4,1,2,8], target = 10 输出：3 示例 4：
     *
     * <p>输入：nums = [0,0,0], target = 0 输出：3
     *
     * <p>提示：
     *
     * <p>1 <= nums.length <= 10^5 -10^4 <= nums[i] <= 10^4 0 <= target <= 10^6
     *
     * @param nums
     * @param target
     * @return
     */
    public int maxNonOverlapping(int[] nums, int target) {
        int len = nums.length;
        int[] sums = nums.clone();
        Map<Integer, Integer> indexMap = new HashMap<>();
        indexMap.put(0, -1);
        List<int[]> list = new ArrayList<>();
        for (int i = 0; i < len; i++) {
            if (i > 0) {
                sums[i] += sums[i - 1];
            }

            int num = sums[i] - target;
            if (indexMap.containsKey(num)) {
                int index = indexMap.get(num);
                list.add(new int[] {index + 1, i});
            }
            indexMap.put(sums[i], i);
        }
        for (int[] arr : list) {
            log.debug("arr:{}", arr);
        }
        int lastIndex = 0;
        int result = 0;
        for (int i = 1; i < list.size(); i++) {
            int[] last = list.get(lastIndex);
            int[] current = list.get(i);
            if (current[0] > last[1]) {
                lastIndex = i;
                continue;
            }
            result++;
        }

        return list.size() - result;
    }

    /**
     * 1124. 表现良好的最长时间段
     *
     * <p>给你一份工作时间表 hours，上面记录着某一位员工每天的工作小时数。
     *
     * <p>我们认为当员工一天中的工作小时数大于 8 小时的时候，那么这一天就是「劳累的一天」。
     *
     * <p>所谓「表现良好的时间段」，意味在这段时间内，「劳累的天数」是严格 大于「不劳累的天数」。
     *
     * <p>请你返回「表现良好时间段」的最大长度。
     *
     * <p>示例 1：
     *
     * <p>输入：hours = [9,9,6,0,6,6,9] 输出：3 解释：最长的表现良好时间段是 [9,9,6]。
     *
     * <p>提示：
     *
     * <p>1 <= hours.length <= 10000 0 <= hours[i] <= 16
     *
     * @param hours
     * @return
     */
    public int longestWPI(int[] hours) {
        int len = hours.length;
        int max = 0;
        for (int i = 0; i < len; i++) {
            if (hours[i] > 8) {
                hours[i] = 1;
            } else {
                hours[i] = -1;
            }
        }
        // 前缀和
        int[] presum = new int[len + 1];
        for (int i = 1; i <= len; i++) {
            presum[i] = presum[i - 1] + hours[i - 1];
        }
        Deque<Integer> stack = new LinkedList<>();
        // 顺序生成单调栈，栈中元素从第一个元素开始严格单调递减，最后一个元素肯定是数组中的最小元素所在位置
        for (int i = 0; i <= len; i++) {
            if (stack.isEmpty() || presum[stack.peek()] > presum[i]) {
                stack.push(i);
            }
        }
        // 倒序扫描数组，求最大长度坡
        int idx = len;
        while (idx > max) {
            while (!stack.isEmpty() && presum[stack.peek()] < presum[idx]) {
                max = Math.max(max, idx - stack.pop());
            }
            idx--;
        }
        return max;
    }

    /**
     * 1129. 颜色交替的最短路径
     *
     * <p>在一个有向图中，节点分别标记为 0, 1, ..., n-1。这个图中的每条边不是红色就是蓝色，且存在自环或平行边。
     *
     * <p>red_edges 中的每一个 [i, j] 对表示从节点 i 到节点 j 的红色有向边。类似地，blue_edges 中的每一个 [i, j] 对表示从节点 i 到节点 j
     * 的蓝色有向边。
     *
     * <p>返回长度为 n 的数组 answer，其中 answer[X] 是从节点 0 到节点 X 的红色边和蓝色边交替出现的最短路径的长度。如果不存在这样的路径，那么 answer[x]
     * = -1。
     *
     * <p>示例 1：
     *
     * <p>输入：n = 3, red_edges = [[0,1],[1,2]], blue_edges = [] 输出：[0,1,-1] 示例 2：
     *
     * <p>输入：n = 3, red_edges = [[0,1]], blue_edges = [[2,1]] 输出：[0,1,-1] 示例 3：
     *
     * <p>输入：n = 3, red_edges = [[1,0]], blue_edges = [[2,1]] 输出：[0,-1,-1] 示例 4：
     *
     * <p>输入：n = 3, red_edges = [[0,1]], blue_edges = [[1,2]] 输出：[0,1,2] 示例 5：
     *
     * <p>输入：n = 3, red_edges = [[0,1],[0,2]], blue_edges = [[1,0]] 输出：[0,1,1]
     *
     * <p>提示：
     *
     * <p>1 <= n <= 100 red_edges.length <= 400 blue_edges.length <= 400 red_edges[i].length ==
     * blue_edges[i].length == 2 0 <= red_edges[i][j], blue_edges[i][j] < n
     *
     * @param n
     * @param red_edges
     * @param blue_edges
     * @return
     */
    public int[] shortestAlternatingPaths(int n, int[][] red_edges, int[][] blue_edges) {
        int[] result = new int[n];
        // 红 0 蓝 1
        int[][] red = new int[n][2];
        int[][] blue = new int[n][2];
        for (int i = 1; i < n; i++) {
            red[i][0] = i;
            red[i][1] = 0x0fffffff; // 初始化红边权值
        }
        red[0][0] = 0;
        red[0][1] = 0;
        for (int i = 1; i < n; i++) {
            blue[i][0] = i;
            blue[i][1] = 0x0fffffff;
        }
        blue[0][0] = 0;
        blue[0][1] = 0;
        dfsAlternatingPaths(red, blue, 0, 0, red_edges, blue_edges);
        dfsAlternatingPaths(red, blue, 1, 0, red_edges, blue_edges);
        for (int i = 0; i < n; i++) {
            result[i] = Math.min(red[i][1], blue[i][1]);
            if (result[i] == 0x0fffffff) { // 没有改变说明不存在
                result[i] = -1;
            }
        }
        return result;
    }

    public void dfsAlternatingPaths(
            int[][] red, int[][] blue, int color, int node, int[][] red_edges, int[][] blue_edges) {
        if (color == 0) {
            for (int[] blue_to : blue_edges) { // 以node为from to 为终 的边
                if (node == blue_to[0]
                        && red[node][1] + 1 < blue[blue_to[1]][1]) // 0到from点加1是否小于0到to的距离
                {
                    blue[blue_to[1]][1] = red[node][1] + 1;
                    dfsAlternatingPaths(red, blue, 1 - color, blue_to[1], red_edges, blue_edges);
                }
            }
        } else {
            for (int[] red_to : red_edges) { // 以node为from to 为终 的边
                if (node == red_to[0]
                        && blue[node][1] + 1 < red[red_to[1]][1]) // 0到from点加1是否小于0到to的距离
                {
                    red[red_to[1]][1] = blue[node][1] + 1;
                    dfsAlternatingPaths(red, blue, 1 - color, red_to[1], red_edges, blue_edges);
                }
            }
        }
    }

    /**
     * 1131. 绝对值表达式的最大值
     *
     * <p>给你两个长度相等的整数数组，返回下面表达式的最大值：
     *
     * <p>|arr1[i] - arr1[j]| + |arr2[i] - arr2[j]| + |i - j|
     *
     * <p>其中下标 i，j 满足 0 <= i, j < arr1.length。
     *
     * <p>示例 1：
     *
     * <p>输入：arr1 = [1,2,3,4], arr2 = [-1,4,5,6] 输出：13 示例 2：
     *
     * <p>输入：arr1 = [1,-2,-5,0,10], arr2 = [0,-2,-1,-7,-4] 输出：20
     *
     * <p>提示：
     *
     * <p>2 <= arr1.length == arr2.length <= 40000 -10^6 <= arr1[i], arr2[i] <= 10^6
     *
     * @param arr1
     * @param arr2
     * @return
     */
    public int maxAbsValExpr(int[] arr1, int[] arr2) {
        int len = arr1.length;
        // |arr1[i] - arr1[j]| + |arr2[i] - arr2[j]| + |i - j|
        //
        // =  (arr1[i] + arr2[i] + i) - (arr1[j] + arr2[j] + j)
        // =  (arr1[i] + arr2[i] - i) - (arr1[j] + arr2[j] - j)
        // =  (arr1[i] - arr2[i] + i) - (arr1[j] - arr2[j] + j)
        // =  (arr1[i] - arr2[i] - i) - (arr1[j] - arr2[j] - j)
        // = -(arr1[i] + arr2[i] + i) + (arr1[j] + arr2[j] + j)
        // = -(arr1[i] + arr2[i] - i) + (arr1[j] + arr2[j] - j)
        // = -(arr1[i] - arr2[i] + i) + (arr1[j] - arr2[j] + j)
        // = -(arr1[i] - arr2[i] - i) + (arr1[j] - arr2[j] - j)
        // 因为存在四组两两等价的展开，所以可以优化为四个表达式：
        // A = arr1[i] + arr2[i] + i
        // B = arr1[i] + arr2[i] - i
        // C = arr1[i] - arr2[i] + i
        // D = arr1[i] - arr2[i] - i
        //
        // max( |arr1[i] - arr1[j]| + |arr2[i] - arr2[j]| + |i - j|)
        // = max(max(A) - min(A),
        //      max(B) - min(B),
        //      max(C) - min(C),
        //      max(D) - min(D))
        int[] max = new int[4];
        Arrays.fill(max, Integer.MIN_VALUE);
        int[] min = new int[4];
        Arrays.fill(min, Integer.MAX_VALUE);
        for (int i = 0; i < len; i++) {
            int a = arr1[i] + arr2[i] + i,
                    b = arr1[i] + arr2[i] - i,
                    c = arr1[i] - arr2[i] + i,
                    d = arr1[i] - arr2[i] - i;
            max[0] = Math.max(max[0], a);
            min[0] = Math.min(min[0], a);

            max[1] = Math.max(max[1], b);
            min[1] = Math.min(min[1], b);

            max[2] = Math.max(max[2], c);
            min[2] = Math.min(min[2], c);

            max[3] = Math.max(max[3], d);
            min[3] = Math.min(min[3], d);
        }
        int result = 0;
        for (int i = 0; i < 4; i++) {
            result = Math.max(result, max[i] - min[i]);
        }

        return result;
    }

    @Test
    public void movesToMakeZigzag() {
        int[] nums = {9, 6, 1, 6, 2};
        logResult(movesToMakeZigzag(nums));
    }

    /**
     * 1144. 递减元素使数组呈锯齿状
     *
     * <p>给你一个整数数组 nums，每次 操作 会从中选择一个元素并 将该元素的值减少 1。
     *
     * <p>如果符合下列情况之一，则数组 A 就是 锯齿数组：
     *
     * <p>每个偶数索引对应的元素都大于相邻的元素，即 A[0] > A[1] < A[2] > A[3] < A[4] > ... 或者，每个奇数索引对应的元素都大于相邻的元素，即 A[0]
     * < A[1] > A[2] < A[3] > A[4] < ... 返回将数组 nums 转换为锯齿数组所需的最小操作次数。
     *
     * <p>示例 1：
     *
     * <p>输入：nums = [1,2,3] 输出：2 解释：我们可以把 2 递减到 0，或把 3 递减到 1。 示例 2：
     *
     * <p>输入：nums = [9,6,1,6,2] 输出：4
     *
     * <p>提示：
     *
     * <p>1 <= nums.length <= 1000 1 <= nums[i] <= 1000
     *
     * @param nums
     * @return
     */
    public int movesToMakeZigzag(int[] nums) {
        int len = nums.length;
        // 偶数索引对应的元素都大于相邻的元素 A[0] > A[1] < A[2] > A[3] < A[4] > ...
        int num = 0;
        for (int i = 1; i < len; i += 2) {
            int minNum = Integer.MAX_VALUE;
            if (i - 1 >= 0) {
                minNum = Math.min(minNum, nums[i - 1]);
            }
            if (i + 1 < len) {
                minNum = Math.min(minNum, nums[i + 1]);
            }
            if (nums[i] >= minNum) {
                num += nums[i] - minNum + 1;
            }
        }
        int min = num;
        // 奇数索引对应的元素都大于相邻的元素 A[0] < A[1] > A[2] < A[3] > A[4] < ...
        num = 0;
        for (int i = 0; i < len; i += 2) {
            int minNum = Integer.MAX_VALUE;
            if (i - 1 >= 0) {
                minNum = Math.min(minNum, nums[i - 1]);
            }
            if (i + 1 < len) {
                minNum = Math.min(minNum, nums[i + 1]);
            }
            if (nums[i] >= minNum) {
                num += nums[i] - minNum + 1;
            }
        }
        min = Math.min(min, num);

        return min;
    }

    /**
     * 5185. 存在连续三个奇数的数组
     *
     * <p>给你一个整数数组 arr，请你判断数组中是否存在连续三个元素都是奇数的情况：如果存在，请返回 true ；否则，返回 false 。
     *
     * <p>示例 1：
     *
     * <p>输入：arr = [2,6,4,1] 输出：false 解释：不存在连续三个元素都是奇数的情况。 示例 2：
     *
     * <p>输入：arr = [1,2,34,3,4,5,7,23,12] 输出：true 解释：存在连续三个元素都是奇数的情况，即 [5,7,23] 。
     *
     * <p>提示：
     *
     * <p>1 <= arr.length <= 1000 1 <= arr[i] <= 1000
     *
     * @param arr
     * @return
     */
    public boolean threeConsecutiveOdds(int[] arr) {
        int len = arr.length;
        if (len < 3) {
            return false;
        }
        int i = 2;
        while (i < len) {
            boolean b1 = (arr[i - 2] & 1) == 1;
            boolean b2 = (arr[i - 1] & 1) == 1;
            boolean b3 = (arr[i] & 1) == 1;
            if (b1 && b2 && b3) {
                return true;
            }
            if (!b3) {
                i += 3;
            } else if (!b2) {
                i += 2;
            } else {
                i += 1;
            }
        }

        return false;
    }

    @Test
    public void minOperations() {
        int n = 6;
        logResult(minOperations(n));
    }

    /**
     * 5488. 使数组中所有元素相等的最小操作数
     *
     * <p>存在一个长度为 n 的数组 arr ，其中 arr[i] = (2 * i) + 1 （ 0 <= i < n ）。
     *
     * <p>一次操作中，你可以选出两个下标，记作 x 和 y （ 0 <= x, y < n ）并使 arr[x] 减去 1 、arr[y] 加上 1 （即 arr[x] -=1 且
     * arr[y] += 1 ）。最终的目标是使数组中的所有元素都 相等 。题目测试用例将会 保证 ：在执行若干步操作后，数组中的所有元素最终可以全部相等。
     *
     * <p>给你一个整数 n，即数组的长度。请你返回使数组 arr 中所有元素相等所需的 最小操作数 。
     *
     * <p>示例 1：
     *
     * <p>输入：n = 3 输出：2 解释：arr = [1, 3, 5] 第一次操作选出 x = 2 和 y = 0，使数组变为 [2, 3, 4] 第二次操作继续选出 x = 2 和 y
     * = 0，数组将会变成 [3, 3, 3] 示例 2：
     *
     * <p>输入：n = 6 输出：9
     *
     * <p>提示：
     *
     * <p>1 <= n <= 10^4
     *
     * @param n
     * @return
     */
    public int minOperations(int n) {
        int helf = n >> 1; /*
        int sum = n * helf;

        int num = helf * helf;


        // 1 3 5 7 9 11
        int result = 0;

        for (int i = 0; i < n >> 1; i++) {
            result += (n - 2 * i - 1);
        }
        */
        return (n - helf) * helf;
    }

    /**
     * 1169. 查询无效交易
     *
     * <p>如果出现下述两种情况，交易 可能无效：
     *
     * <p>交易金额超过 ¥1000 或者，它和另一个城市中同名的另一笔交易相隔不超过 60 分钟（包含 60 分钟整） 每个交易字符串 transactions[i]
     * 由一些用逗号分隔的值组成，这些值分别表示交易的名称，时间（以分钟计），金额以及城市。
     *
     * <p>给你一份交易清单 transactions，返回可能无效的交易列表。你可以按任何顺序返回答案。
     *
     * <p>示例 1：
     *
     * <p>输入：transactions = ["alice,20,800,mtv","alice,50,100,beijing"]
     * 输出：["alice,20,800,mtv","alice,50,100,beijing"] 解释：第一笔交易是无效的，因为第二笔交易和它间隔不超过 60
     * 分钟、名称相同且发生在不同的城市。同样，第二笔交易也是无效的。 示例 2：
     *
     * <p>输入：transactions = ["alice,20,800,mtv","alice,50,1200,mtv"] 输出：["alice,50,1200,mtv"] 示例 3：
     *
     * <p>输入：transactions = ["alice,20,800,mtv","bob,50,1200,mtv"] 输出：["bob,50,1200,mtv"]
     *
     * <p>提示：
     *
     * <p>transactions.length <= 1000 每笔交易 transactions[i] 按 "{name},{time},{amount},{city}" 的格式进行记录
     * 每个交易名称 {name} 和城市 {city} 都由小写英文字母组成，长度在 1 到 10 之间 每个交易时间 {time} 由一些数字组成，表示一个 0 到 1000 之间的整数
     * 每笔交易金额 {amount} 由一些数字组成，表示一个 0 到 2000 之间的整数
     *
     * @param transactions
     * @return
     */
    public List<String> invalidTransactions(String[] transactions) {
        int len = transactions.length;
        List<Transaction> list = new ArrayList<>();
        List<String> result = new ArrayList<>();
        for (String str : transactions) {
            String[] strings = str.split(",");
            list.add(
                    new Transaction(
                            strings[0],
                            Integer.parseInt(strings[1]),
                            Integer.parseInt(strings[2]),
                            strings[3]));
        }
        for (int i = 0; i < len; i++) {
            Transaction tran = list.get(i);
            if (tran.amount > 1000) {
                result.add(transactions[i]);
                continue;
            }
            for (int j = 0; j < len; j++) {
                if (i == j) {
                    continue;
                }
                Transaction t2 = list.get(j);
                if (Objects.equals(tran.name, t2.name)
                        && !Objects.equals(tran.city, t2.city)
                        && Math.abs(tran.time - t2.time) <= 60) {
                    result.add(transactions[i]);
                    break;
                }
            }
        }

        return result;
    }

    static class Transaction {
        String name;
        int time;
        int amount;
        String city;

        public Transaction(String name, int minute, int amount, String city) {
            this.name = name;
            this.time = minute;
            this.amount = amount;
            this.city = city;
        }
    }

    /**
     * 1186. 删除一次得到子数组最大和
     *
     * <p>给你一个整数数组，返回它的某个 非空 子数组（连续元素）在执行一次可选的删除操作后，所能得到的最大元素总和。
     *
     * <p>换句话说，你可以从原数组中选出一个子数组，并可以决定要不要从中删除一个元素（只能删一次哦），（删除后）子数组中至少应当有一个元素，然后该子数组（剩下）的元素总和是所有子数组之中最大的。
     *
     * <p>注意，删除一个元素后，子数组 不能为空。
     *
     * <p>请看示例：
     *
     * <p>示例 1：
     *
     * <p>输入：arr = [1,-2,0,3] 输出：4 解释：我们可以选出 [1, -2, 0, 3]，然后删掉 -2，这样得到 [1, 0, 3]，和最大。 示例 2：
     *
     * <p>输入：arr = [1,-2,-2,3] 输出：3 解释：我们直接选出 [3]，这就是最大和。 示例 3：
     *
     * <p>输入：arr = [-1,-1,-1,-1] 输出：-1 解释：最后得到的子数组不能为空，所以我们不能选择 [-1] 并从中删去 -1 来得到 0。 我们应该直接选择
     * [-1]，或者选择 [-1, -1] 再从中删去一个 -1。
     *
     * <p>提示：
     *
     * <p>1 <= arr.length <= 10^5 -10^4 <= arr[i] <= 10^4
     *
     * @param arr
     * @return
     */
    public int maximumSum(int[] arr) {
        int len = arr.length;
        // 0 表示未删除 1 已删除一个元素
        int[][] dp = new int[len][2];
        int max = arr[0];
        dp[0][0] = arr[0];
        dp[0][1] = Integer.MIN_VALUE >> 1;
        for (int i = 1; i < len; i++) {
            //
            int num = arr[i];
            dp[i][0] = Math.max(dp[i - 1][0] + num, num);
            dp[i][1] = Math.max(dp[i - 1][1] + num, dp[i - 1][0]);
            max = Math.max(max, Math.max(dp[i][0], dp[i][1]));
        }

        return max;
    }

    @Test
    public void smallestStringWithSwaps() {
        String s = "dcab";
        List<List<Integer>> pairs = new ArrayList<>();
        pairs.add(Arrays.asList(0, 3));
        pairs.add(Arrays.asList(1, 2));
        logResult(smallestStringWithSwaps(s, pairs));
    }

    /**
     * 1202. 交换字符串中的元素
     *
     * <p>给你一个字符串 s，以及该字符串中的一些「索引对」数组 pairs，其中 pairs[i] = [a, b] 表示字符串中的两个索引（编号从 0 开始）。
     *
     * <p>你可以 任意多次交换 在 pairs 中任意一对索引处的字符。
     *
     * <p>返回在经过若干次交换后，s 可以变成的按字典序最小的字符串。
     *
     * <p>示例 1:
     *
     * <p>输入：s = "dcab", pairs = [[0,3],[1,2]] 输出："bacd" 解释： 交换 s[0] 和 s[3], s = "bcad" 交换 s[1] 和
     * s[2], s = "bacd" 示例 2：
     *
     * <p>输入：s = "dcab", pairs = [[0,3],[1,2],[0,2]] 输出："abcd" 解释： 交换 s[0] 和 s[3], s = "bcad" 交换
     * s[0] 和 s[2], s = "acbd" 交换 s[1] 和 s[2], s = "abcd" 示例 3：
     *
     * <p>输入：s = "cba", pairs = [[0,1],[1,2]] 输出："abc" 解释： 交换 s[0] 和 s[1], s = "bca" 交换 s[1] 和 s[2],
     * s = "bac" 交换 s[0] 和 s[1], s = "abc"
     *
     * <p>提示：
     *
     * <p>1 <= s.length <= 10^5 0 <= pairs.length <= 10^5 0 <= pairs[i][0], pairs[i][1] < s.length s
     * 中只含有小写英文字母
     *
     * @param s
     * @param pairs
     * @return
     */
    public String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {
        int len = s.length();
        swapParents = new int[len];
        for (int i = 0; i < len; i++) {
            swapParents[i] = i;
        }

        for (List<Integer> pair : pairs) {
            int idx1 = pair.get(0), idx2 = pair.get(1);
            union(idx1, idx2);
        }

        // 每个下标集合有1个leader，用leader作为key(唯一)，下标集合List作为value
        HashMap<Integer, List<Integer>> map = new HashMap<>();
        // 从小到大遍历，使得List<Integer>中的值是有序的(事后不用再排序了)
        for (int i = 0; i < s.length(); ++i) {
            int key = findParent(i);
            map.computeIfAbsent(key, k -> new ArrayList<>()).add(i);
        }

        StringBuilder sb = new StringBuilder(s);
        // 遍历所有每个下标集合，进行字符局部排序
        for (List<Integer> list : map.values()) {
            if (list.size() > 1) {
                sort(sb, list);
            }
        }

        return sb.toString();
    }

    // 根据下标集合进行局部排序
    private void sort(StringBuilder sb, List<Integer> list) {
        int len = list.size();
        char[] array = new char[len];
        // 根据下标集合构建字符集合
        for (int i = 0; i < len; ++i) {
            array[i] = sb.charAt(list.get(i));
        }

        // 将字符集合排序
        Arrays.sort(array);
        // 将字符按序“插入”回StringBuilder
        for (int i = 0; i < len; ++i) {
            sb.setCharAt(list.get(i), array[i]);
        }
    }

    private int[] swapParents;

    private void union(int child, int parent) {
        child = findParent(child);
        parent = findParent(parent);
        swapParents[child] = parent;
    }

    private int findParent(int child) {
        int parent = swapParents[child];
        if (parent != child) {
            return swapParents[child] = findParent(parent);
        }
        return child;
    }

    /**
     * 1222. 可以攻击国王的皇后
     *
     * <p>在一个 8x8 的棋盘上，放置着若干「黑皇后」和一个「白国王」。
     *
     * <p>「黑皇后」在棋盘上的位置分布用整数坐标数组 queens 表示，「白国王」的坐标用数组 king 表示。
     *
     * <p>「黑皇后」的行棋规定是：横、直、斜都可以走，步数不受限制，但是，不能越子行棋。
     *
     * <p>请你返回可以直接攻击到「白国王」的所有「黑皇后」的坐标（任意顺序）。
     *
     * <p>示例 1：
     *
     * <p>输入：queens = [[0,1],[1,0],[4,0],[0,4],[3,3],[2,4]], king = [0,0] 输出：[[0,1],[1,0],[3,3]] 解释：
     * [0,1] 的皇后可以攻击到国王，因为他们在同一行上。 [1,0] 的皇后可以攻击到国王，因为他们在同一列上。 [3,3] 的皇后可以攻击到国王，因为他们在同一条对角线上。 [0,4]
     * 的皇后无法攻击到国王，因为她被位于 [0,1] 的皇后挡住了。 [4,0] 的皇后无法攻击到国王，因为她被位于 [1,0] 的皇后挡住了。 [2,4]
     * 的皇后无法攻击到国王，因为她和国王不在同一行/列/对角线上。 示例 2：
     *
     * <p>输入：queens = [[0,0],[1,1],[2,2],[3,4],[3,5],[4,4],[4,5]], king = [3,3]
     * 输出：[[2,2],[3,4],[4,4]] 示例 3：
     *
     * <p>输入：queens =
     * [[5,6],[7,7],[2,1],[0,7],[1,6],[5,1],[3,7],[0,3],[4,0],[1,2],[6,3],[5,0],[0,4],[2,2],[1,1],[6,4],[5,4],[0,0],[2,6],[4,5],[5,2],[1,4],[7,5],[2,3],[0,5],[4,2],[1,0],[2,7],[0,1],[4,6],[6,1],[0,6],[4,3],[1,7]],
     * king = [3,4] 输出：[[2,3],[1,4],[1,6],[3,7],[4,3],[5,4],[4,5]]
     *
     * <p>提示：
     *
     * <p>1 <= queens.length <= 63 queens[0].length == 2 0 <= queens[i][j] < 8 king.length == 2 0 <=
     * king[0], king[1] < 8 一个棋盘格上最多只能放置一枚棋子。
     *
     * @param queens
     * @param king
     * @return
     */
    public List<List<Integer>> queensAttacktheKing(int[][] queens, int[] king) {
        List<List<Integer>> result = new ArrayList<>();
        boolean[][] board = new boolean[8][8];
        for (int[] queen : queens) {
            board[queen[0]][queen[1]] = true;
        }
        // 8个方向                         右     左    上    下    右上   右下   左上    左下
        int[][] direction =
                new int[][] {{1, 0}, {-1, 0}, {0, 1}, {0, -1}, {1, 1}, {1, -1}, {-1, 1}, {-1, -1}};
        // 从第一个方向开始到第八个方向
        for (int i = 0; i < direction.length; i++) {
            // 起始位置为king的坐标,找到第一个皇后停止这个方向的查找，或者直到出界
            for (int x = king[0], y = king[1];
                    x >= 0 && x < 8 && y >= 0 && y < 8;
                    x += direction[i][0], y += direction[i][1]) {
                if (board[x][y]) {
                    result.add(Arrays.asList(x, y));
                    break;
                }
            }
        }

        return result;
    }

    /**
     * 1219. 黄金矿工
     *
     * <p>你要开发一座金矿，地质勘测学家已经探明了这座金矿中的资源分布，并用大小为 m * n 的网格 grid
     * 进行了标注。每个单元格中的整数就表示这一单元格中的黄金数量；如果该单元格是空的，那么就是 0。
     *
     * <p>为了使收益最大化，矿工需要按以下规则来开采黄金：
     *
     * <p>每当矿工进入一个单元，就会收集该单元格中的所有黄金。 矿工每次可以从当前位置向上下左右四个方向走。 每个单元格只能被开采（进入）一次。 不得开采（进入）黄金数目为 0 的单元格。
     * 矿工可以从网格中 任意一个 有黄金的单元格出发或者是停止。
     *
     * <p>示例 1：
     *
     * <p>输入：grid = [[0,6,0],[5,8,7],[0,9,0]] 输出：24 解释： [[0,6,0], [5,8,7], [0,9,0]] 一种收集最多黄金的路线是：9
     * -> 8 -> 7。 示例 2：
     *
     * <p>输入：grid = [[1,0,7],[2,0,6],[3,4,5],[0,3,0],[9,0,20]] 输出：28 解释： [[1,0,7], [2,0,6], [3,4,5],
     * [0,3,0], [9,0,20]] 一种收集最多黄金的路线是：1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7。
     *
     * <p>提示：
     *
     * <p>1 <= grid.length, grid[i].length <= 15 0 <= grid[i][j] <= 100 最多 25 个单元格中有黄金。
     *
     * @param grid
     * @return
     */
    public int getMaximumGold(int[][] grid) {
        int max = 0;
        int rows = grid.length, cols = grid[0].length;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                max = Math.max(max, dfsMaximumGold(grid, i, j));
            }
        }

        return max;
    }

    private int dfsMaximumGold(int[][] grid, int i, int j) {
        if (!inArea(i, j, grid.length, grid[0].length) || grid[i][j] == 0) {
            return 0;
        }
        int num = grid[i][j], max = 0;
        grid[i][j] = 0;

        for (int k = 0; k < 4; k++) {
            int row = i + DIR_ROW[k], col = j + DIR_COL[k];
            max = Math.max(max, num + dfsMaximumGold(grid, row, col));
        }
        grid[i][j] = num;
        return max;
    }

    @Test
    public void minOperations2() {
        int[] nums = {1000000000};
        logResult(minOperations(nums));
    }

    /**
     * 5481. 得到目标数组的最少函数调用次数
     *
     * <p>给你一个与 nums 大小相同且初始值全为 0 的数组 arr ，请你调用以上函数得到整数数组 nums 。
     *
     * <p>请你返回将 arr 变成 nums 的最少函数调用次数。
     *
     * <p>答案保证在 32 位有符号整数以内。
     *
     * <p>示例 1：
     *
     * <p>输入：nums = [1,5] 输出：5 解释：给第二个数加 1 ：[0, 0] 变成 [0, 1] （1 次操作）。 将所有数字乘以 2 ：[0, 1] -> [0, 2] ->
     * [0, 4] （2 次操作）。 给两个数字都加 1 ：[0, 4] -> [1, 4] -> [1, 5] （2 次操作）。 总操作次数为：1 + 2 + 2 = 5 。 示例 2：
     *
     * <p>输入：nums = [2,2] 输出：3 解释：给两个数字都加 1 ：[0, 0] -> [0, 1] -> [1, 1] （2 次操作）。 将所有数字乘以 2 ： [1, 1]
     * -> [2, 2] （1 次操作）。 总操作次数为： 2 + 1 = 3 。 示例 3：
     *
     * <p>输入：nums = [4,2,5] 输出：6 解释：（初始）[0,0,0] -> [1,0,0] -> [1,0,1] -> [2,0,2] -> [2,1,2] ->
     * [4,2,4] -> [4,2,5] （nums 数组）。 示例 4：
     *
     * <p>输入：nums = [3,2,2,4] 输出：7 示例 5：
     *
     * <p>输入：nums = [2,4,8,16] 输出：8
     *
     * <p>提示：
     *
     * <p>1 <= nums.length <= 10^5 0 <= nums[i] <= 10^9
     *
     * @param nums
     * @return
     */
    public int minOperations(int[] nums) {
        int result = 0;
        int maxOp = 0;
        for (int num : nums) {
            int op = 0;
            while (num > 0) {
                if ((num & 1) == 1) {
                    result++;
                }
                num >>= 1;
                op++;
            }
            maxOp = Math.max(maxOp, op - 1);
        }
        log.debug("maxOp:{}", maxOp);
        log.debug("result:{}", result);
        result += maxOp;

        return result;
    }

    @Test
    public void mostVisited() {
        int n = 4;
        int[] rounds = {1, 3, 1, 2};
        logResult(mostVisited(n, rounds));
    }
    /**
     * 5495. 圆形赛道上经过次数最多的扇区
     *
     * <p>给你一个整数 n 和一个整数数组 rounds 。有一条圆形赛道由 n 个扇区组成，扇区编号从 1 到 n 。现将在这条赛道上举办一场马拉松比赛，该马拉松全程由 m
     * 个阶段组成。其中，第 i 个阶段将会从扇区 rounds[i - 1] 开始，到扇区 rounds[i] 结束。举例来说，第 1 阶段从 rounds[0] 开始，到 rounds[1]
     * 结束。
     *
     * <p>请你以数组形式返回经过次数最多的那几个扇区，按扇区编号 升序 排列。
     *
     * <p>注意，赛道按扇区编号升序逆时针形成一个圆（请参见第一个示例）。
     *
     * <p>示例 1：
     *
     * <p>输入：n = 4, rounds = [1,3,1,2] 输出：[1,2] 解释：本场马拉松比赛从扇区 1 开始。经过各个扇区的次序如下所示： 1 --> 2 --> 3（阶段 1
     * 结束）--> 4 --> 1（阶段 2 结束）--> 2（阶段 3 结束，即本场马拉松结束） 其中，扇区 1 和 2 都经过了两次，它们是经过次数最多的两个扇区。扇区 3 和 4
     * 都只经过了一次。 示例 2：
     *
     * <p>输入：n = 2, rounds = [2,1,2,1,2,1,2,1,2] 输出：[2] 示例 3：
     *
     * <p>输入：n = 7, rounds = [1,3,5,7] 输出：[1,2,3,4,5,6,7]
     *
     * <p>提示：
     *
     * <p>2 <= n <= 100 1 <= m <= 100 rounds.length == m + 1 1 <= rounds[i] <= n rounds[i] !=
     * rounds[i + 1] ，其中 0 <= i < m
     *
     * @param n
     * @param rounds
     * @return
     */
    public List<Integer> mostVisited(int n, int[] rounds) {
        List<Integer> result = new ArrayList<>();
        int[] counts = new int[n + 1];
        int start = rounds[0];
        counts[start]++;
        for (int i = 1; i < rounds.length; i++) {
            int end = rounds[i];
            if (end < start) {
                for (int j = start + 1; j <= n; j++) {
                    counts[j]++;
                }
                for (int j = 1; j <= end; j++) {
                    counts[j]++;
                }
            } else {

                for (int j = start + 1; j <= end; j++) {
                    counts[j]++;
                }
            }
            start = end;
        }
        log.debug("counts:{}", counts);
        int max = 0;
        for (int i = 1; i <= n; i++) {
            if (counts[i] > max) {
                max = counts[i];
                result = new ArrayList<>();
                result.add(i);
            } else if (counts[i] == max) {
                result.add(i);
            }
        }

        return result;
    }

    /**
     * 5496. 你可以获得的最大硬币数目
     *
     * <p>有 3n 堆数目不一的硬币，你和你的朋友们打算按以下方式分硬币：
     *
     * <p>每一轮中，你将会选出 任意 3 堆硬币（不一定连续）。 Alice 将会取走硬币数量最多的那一堆。 你将会取走硬币数量第二多的那一堆。 Bob 将会取走最后一堆。
     * 重复这个过程，直到没有更多硬币。 给你一个整数数组 piles ，其中 piles[i] 是第 i 堆中硬币的数目。
     *
     * <p>返回你可以获得的最大硬币数目。
     *
     * <p>示例 1：
     *
     * <p>输入：piles = [2,4,1,2,7,8] 输出：9 解释：选出 (2, 7, 8) ，Alice 取走 8 枚硬币的那堆，你取走 7 枚硬币的那堆，Bob 取走最后一堆。
     * 选出 (1, 2, 4) , Alice 取走 4 枚硬币的那堆，你取走 2 枚硬币的那堆，Bob 取走最后一堆。 你可以获得的最大硬币数目：7 + 2 = 9.
     * 考虑另外一种情况，如果选出的是 (1, 2, 8) 和 (2, 4, 7) ，你就只能得到 2 + 4 = 6 枚硬币，这不是最优解。 示例 2：
     *
     * <p>输入：piles = [2,4,5] 输出：4 示例 3：
     *
     * <p>输入：piles = [9,8,7,6,5,1,2,3,4] 输出：18
     *
     * <p>提示：
     *
     * <p>3 <= piles.length <= 10^5 piles.length % 3 == 0 1 <= piles[i] <= 10^4
     *
     * @param piles
     * @return
     */
    public int maxCoins(int[] piles) {

        Arrays.sort(piles);
        int len = piles.length / 3;
        int index = piles.length - 2;
        int result = 0;
        for (int i = 0; i < len; i++) {
            result += piles[index];
            index -= 2;
        }
        return result;
    }

    @Test
    public void findLatestStep() {
        int[] arr = {3, 1, 5, 4, 2};
        int m = 2;
        logResult(findLatestStep(arr, m));
    }

    /**
     * 5497. 查找大小为 M
     *
     * <p>给你一个数组 arr ，该数组表示一个从 1 到 n 的数字排列。有一个长度为 n 的二进制字符串，该字符串上的所有位最初都设置为 0 。
     *
     * <p>在从 1 到 n 的每个步骤 i 中（假设二进制字符串和 arr 都是从 1 开始索引的情况下），二进制字符串上位于位置 arr[i] 的位将会设为 1 。
     *
     * <p>给你一个整数 m ，请你找出二进制字符串上存在长度为 m 的一组 1 的最后步骤。一组 1 是一个连续的、由 1 组成的子串，且左右两边不再有可以延伸的 1 。
     *
     * <p>返回存在长度 恰好 为 m 的 一组 1 的最后步骤。如果不存在这样的步骤，请返回 -1 。
     *
     * <p>示例 1：
     *
     * <p>输入：arr = [3,5,1,2,4], m = 1 输出：4 解释： 步骤 1："00100"，由 1 构成的组：["1"] 步骤 2："00101"，由 1
     * 构成的组：["1", "1"] 步骤 3："10101"，由 1 构成的组：["1", "1", "1"] 步骤 4："11101"，由 1 构成的组：["111", "1"] 步骤
     * 5："11111"，由 1 构成的组：["11111"] 存在长度为 1 的一组 1 的最后步骤是步骤 4 。 示例 2：
     *
     * <p>输入：arr = [3,1,5,4,2], m = 2 输出：-1 解释： 步骤 1："00100"，由 1 构成的组：["1"] 步骤 2："10100"，由 1
     * 构成的组：["1", "1"] 步骤 3："10101"，由 1 构成的组：["1", "1", "1"] 步骤 4："10111"，由 1 构成的组：["1", "111"] 步骤
     * 5："11111"，由 1 构成的组：["11111"] 不管是哪一步骤都无法形成长度为 2 的一组 1 。 示例 3：
     *
     * <p>输入：arr = [1], m = 1 输出：1 示例 4：
     *
     * <p>输入：arr = [2,1], m = 2 输出：2
     *
     * <p>提示：
     *
     * <p>n == arr.length 1 <= n <= 10^5 1 <= arr[i] <= n arr 中的所有整数 互不相同 1 <= m <= arr.length
     *
     * @param arr
     * @param m
     * @return
     */
    public int findLatestStep(int[] arr, int m) {
        int n = arr.length;
        // int[] nums = new int[n + 1];
        TreeSet<Integer> treeSet = new TreeSet<>();
        treeSet.add(0);
        treeSet.add(arr.length + 1);
        if (arr.length == m) {
            return arr.length;
        }
        for (int i = n - 1; i >= 0; i--) {
            int idx = arr[i];
            int l = treeSet.lower(idx), h = treeSet.higher(idx);
            if (idx - l - 1 == m || h - idx - 1 == m) {
                return i;
            }
            treeSet.add(idx);
        }

        return -1;
    }

    /**
     * 1552. 两球之间的磁力
     *
     * <p>在代号为 C-137 的地球上，Rick 发现如果他将两个球放在他新发明的篮子里，它们之间会形成特殊形式的磁力。Rick 有 n 个空的篮子，第 i 个篮子的位置在
     * position[i] ，Morty 想把 m 个球放到这些篮子里，使得任意两球间 最小磁力 最大。
     *
     * <p>已知两个球如果分别位于 x 和 y ，那么它们之间的磁力为 |x - y| 。
     *
     * <p>给你一个整数数组 position 和一个整数 m ，请你返回最大化的最小磁力。
     *
     * <p>示例 1：
     *
     * <p>输入：position = [1,2,3,4,7], m = 3 输出：3 解释：将 3 个球分别放入位于 1，4 和 7 的三个篮子，两球间的磁力分别为 [3, 3,
     * 6]。最小磁力为 3 。我们没办法让最小磁力大于 3 。 示例 2：
     *
     * <p>输入：position = [5,4,3,2,1,1000000000], m = 2 输出：999999999 解释：我们使用位于 1 和 1000000000
     * 的篮子时最小磁力最大。
     *
     * <p>提示：
     *
     * <p>n == position.length 2 <= n <= 10^5 1 <= position[i] <= 10^9 所有 position 中的整数 互不相同 。 2 <=
     * m <= position.length
     *
     * @param position
     * @param m
     * @return
     */
    public int maxDistance(int[] position, int m) {
        Arrays.sort(position);
        int high = (position[position.length - 1] - position[0]) / (m - 1);
        int low = 1;
        int result = 1;
        while (low <= high) {
            int mid = (low + high) >> 1;
            if (checkDistance(position, mid, m)) {
                low = mid + 1;
                result = mid;
            } else {
                high = mid - 1;
            }
        }

        return result;
    }

    boolean checkDistance(int[] position, int distance, int m) {
        int count = 1;
        int i = 0;
        for (int j = 1; j < position.length; j++) {
            if (position[j] - position[i] >= distance) {
                i = j;
                count++;
                if (count >= m) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 1233. 删除子文件夹
     *
     * <p>你是一位系统管理员，手里有一份文件夹列表 folder，你的任务是要删除该列表中的所有 子文件夹，并以 任意顺序 返回剩下的文件夹。
     *
     * <p>我们这样定义「子文件夹」：
     *
     * <p>如果文件夹 folder[i] 位于另一个文件夹 folder[j] 下，那么 folder[i] 就是 folder[j] 的子文件夹。
     * 文件夹的「路径」是由一个或多个按以下格式串联形成的字符串：
     *
     * <p>/ 后跟一个或者多个小写英文字母。 例如，/leetcode 和 /leetcode/problems 都是有效的路径，而空字符串和 / 不是。
     *
     * <p>示例 1：
     *
     * <p>输入：folder = ["/a","/a/b","/c/d","/c/d/e","/c/f"] 输出：["/a","/c/d","/c/f"] 解释："/a/b/" 是 "/a"
     * 的子文件夹，而 "/c/d/e" 是 "/c/d" 的子文件夹。 示例 2：
     *
     * <p>输入：folder = ["/a","/a/b/c","/a/b/d"] 输出：["/a"] 解释：文件夹 "/a/b/c" 和 "/a/b/d/" 都会被删除，因为它们都是
     * "/a" 的子文件夹。 示例 3：
     *
     * <p>输入：folder = ["/a/b/c","/a/b/d","/a/b/ca"] 输出：["/a/b/c","/a/b/ca","/a/b/d"]
     *
     * <p>提示：
     *
     * <p>1 <= folder.length <= 4 * 10^4 2 <= folder[i].length <= 100 folder[i] 只包含小写字母和 / folder[i]
     * 总是以字符 / 起始 每个文件夹名都是唯一的
     *
     * @param folder
     * @return
     */
    public List<String> removeSubfolders(String[] folder) {
        Arrays.sort(folder);
        List<String> list = new ArrayList<>();
        list.add(folder[0]);
        for (int i = 1; i < folder.length; i++) {
            String parent = list.get(list.size() - 1);
            String path = folder[i];
            if (!path.startsWith(parent + "/")) {
                list.add(path);
            }
        }
        return list;
    }

    /**
     * 1254. 统计封闭岛屿的数目
     *
     * <p>有一个二维矩阵 grid ，每个位置要么是陆地（记号为 0 ）要么是水域（记号为 1 ）。
     *
     * <p>我们从一块陆地出发，每次可以往上下左右 4 个方向相邻区域走，能走到的所有陆地区域，我们将其称为一座「岛屿」。
     *
     * <p>如果一座岛屿 完全 由水域包围，即陆地边缘上下左右所有相邻区域都是水域，那么我们将其称为 「封闭岛屿」。
     *
     * <p>请返回封闭岛屿的数目。
     *
     * <p>示例 1：
     *
     * <p>输入：grid =
     * [[1,1,1,1,1,1,1,0],[1,0,0,0,0,1,1,0],[1,0,1,0,1,1,1,0],[1,0,0,0,0,1,0,1],[1,1,1,1,1,1,1,0]]
     * 输出：2 解释： 灰色区域的岛屿是封闭岛屿，因为这座岛屿完全被水域包围（即被 1 区域包围）。 示例 2：
     *
     * <p>输入：grid = [[0,0,1,0,0],[0,1,0,1,0],[0,1,1,1,0]] 输出：1 示例 3：
     *
     * <p>输入：grid = [[1,1,1,1,1,1,1], [1,0,0,0,0,0,1], [1,0,1,1,1,0,1], [1,0,1,0,1,0,1],
     * [1,0,1,1,1,0,1], [1,0,0,0,0,0,1], [1,1,1,1,1,1,1]] 输出：2
     *
     * <p>提示：
     *
     * <p>1 <= grid.length, grid[0].length <= 100 0 <= grid[i][j] <=1
     *
     * @param grid
     * @return
     */
    public int closedIsland(int[][] grid) {
        int rows = grid.length, cols = grid[0].length;
        int result = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 0 && closedIslandDfs(grid, i, j)) {
                    result++;
                }
            }
        }
        return result;
    }

    private boolean closedIslandDfs(int[][] grid, int i, int j) {
        if (!inArea(i, j, grid.length, grid[0].length)) {
            return false;
        }
        if (grid[i][j] == 1) {
            return true;
        }
        grid[i][j] = 1;

        boolean up = closedIslandDfs(grid, i - 1, j);
        boolean down = closedIslandDfs(grid, i + 1, j);
        boolean left = closedIslandDfs(grid, i, j - 1);
        boolean right = closedIslandDfs(grid, i, j + 1);

        return up && down && left && right;
    }

    @Test
    public void maxSatisfied() {
        int[] customers = {1, 0, 1, 2, 1, 1, 7, 5}, grumpy = {0, 1, 0, 1, 0, 1, 0, 1};
        int X = 3;
        logResult(maxSatisfied(customers, grumpy, X));
    }
    /**
     * 1052. 爱生气的书店老板
     *
     * <p>今天，书店老板有一家店打算试营业 customers.length 分钟。每分钟都有一些顾客（customers[i]）会进入书店，所有这些顾客都会在那一分钟结束后离开。
     *
     * <p>在某些时候，书店老板会生气。 如果书店老板在第 i 分钟生气，那么 grumpy[i] = 1，否则 grumpy[i] = 0。
     * 当书店老板生气时，那一分钟的顾客就会不满意，不生气则他们是满意的。
     *
     * <p>书店老板知道一个秘密技巧，能抑制自己的情绪，可以让自己连续 X 分钟不生气，但却只能使用一次。
     *
     * <p>请你返回这一天营业下来，最多有多少客户能够感到满意的数量。
     *
     * <p>示例：
     *
     * <p>输入：customers = [1,0,1,2,1,1,7,5], grumpy = [0,1,0,1,0,1,0,1], X = 3 输出：16 解释： 书店老板在最后 3
     * 分钟保持冷静。 感到满意的最大客户数量 = 1 + 1 + 1 + 1 + 7 + 5 = 16.
     *
     * <p>提示：
     *
     * <p>1 <= X <= customers.length == grumpy.length <= 20000 0 <= customers[i] <= 1000 0 <=
     * grumpy[i] <= 1
     *
     * @param customers
     * @param grumpy
     * @param X
     * @return
     */
    public int maxSatisfied(int[] customers, int[] grumpy, int X) {
        int max = 0;
        int len = customers.length, sum = 0;
        for (int i = 0; i < len; i++) {
            if (grumpy[i] == 0) {
                sum += customers[i];
                customers[i] = 0;
            }
        }
        int cost = 0;
        int i = 0;
        while (i < len) {
            cost += customers[i];
            if (i >= X - 1) {
                if (cost > max) {
                    max = cost;
                }
                cost -= customers[i - X + 1]; // 窗口长度为X,窗口在向前滑动一位之前,需要将窗口中的最左边的一位数减掉
            }
            i++;
        }

        return sum + max;
    }

    @Test
    public void rearrangeBarcodes() {
        int[] barcodes = {2, 2, 1, 3};
        int[] result = rearrangeBarcodes(barcodes);
        log.debug("result:{}", result);
    }

    /**
     * 1054. 距离相等的条形码
     *
     * <p>在一个仓库里，有一排条形码，其中第 i 个条形码为 barcodes[i]。
     *
     * <p>请你重新排列这些条形码，使其中两个相邻的条形码 不能 相等。 你可以返回任何满足该要求的答案，此题保证存在答案。
     *
     * <p>示例 1：
     *
     * <p>输入：[1,1,1,2,2,2] 输出：[2,1,2,1,2,1] 示例 2：
     *
     * <p>输入：[1,1,1,1,2,2,3,3] 输出：[1,3,1,3,2,1,2,1]
     *
     * <p>提示：
     *
     * <p>1 <= barcodes.length <= 10000 1 <= barcodes[i] <= 10000
     *
     * @param barcodes
     * @return
     */
    public int[] rearrangeBarcodes(int[] barcodes) {
        Map<Integer, Integer> countMap = new HashMap<>();
        for (int barcode : barcodes) {
            int count = countMap.getOrDefault(barcode, 0);
            countMap.put(barcode, count + 1);
        }
        int len = barcodes.length;
        List<Integer> list = new ArrayList<>(countMap.keySet());
        // 按数量排序
        list.sort((a, b) -> countMap.get(b) - countMap.get(a));

        int index = 0;
        // 间隔排列
        int[] result = new int[len];
        int i = 0;
        while (i < len && index < list.size()) {
            int num = list.get(index);
            result[i] = num;
            int count = countMap.get(num);
            countMap.put(num, count - 1);
            if (count - 1 == 0) {
                index++;
            }
            i += 2;
            if (i >= len) {
                i = 1;
            }
        }

        return result;
    }

    @Test
    public void containsPattern() {
        int[] arr = {4, 1, 1, 4, 5, 3, 4, 3, 5, 3, 1, 1, 4, 4, 4, 4, 3, 1, 2, 1};
        int m = 4, k = 2;
        logResult(containsPattern(arr, m, k));
    }

    /**
     * 5499. 重复至少 K 次且长度为 M 的模式
     *
     * <p>给你一个正整数数组 arr，请你找出一个长度为 m 且在数组中至少重复 k 次的模式。
     *
     * <p>模式 是由一个或多个值组成的子数组（连续的子序列），连续 重复多次但 不重叠 。 模式由其长度和重复次数定义。
     *
     * <p>如果数组中存在一个至少重复 k 次且长度为 m 的模式，则返回 true ，否则返回 false 。
     *
     * <p>示例 1：
     *
     * <p>输入：arr = [1,2,4,4,4,4], m = 1, k = 3 输出：true 解释：模式 (4) 的长度为 1 ，且连续重复 4 次。注意，模式可以重复 k
     * 次或更多次，但不能少于 k 次。 示例 2：
     *
     * <p>输入：arr = [1,2,1,2,1,1,1,3], m = 2, k = 2 输出：true 解释：模式 (1,2) 长度为 2 ，且连续重复 2 次。另一个符合题意的模式是
     * (2,1) ，同样重复 2 次。 示例 3：
     *
     * <p>输入：arr = [1,2,1,2,1,3], m = 2, k = 3 输出：false 解释：模式 (1,2) 长度为 2 ，但是只连续重复 2 次。不存在长度为 2
     * 且至少重复 3 次的模式。 示例 4：
     *
     * <p>输入：arr = [1,2,3,1,2], m = 2, k = 2 输出：false 解释：模式 (1,2) 出现 2 次但并不连续，所以不能算作连续重复 2 次。 示例 5：
     *
     * <p>输入：arr = [2,2,2,2], m = 2, k = 3 输出：false 解释：长度为 2 的模式只有 (2,2) ，但是只连续重复 2
     * 次。注意，不能计算重叠的重复次数。
     *
     * <p>提示：
     *
     * <p>2 <= arr.length <= 100 1 <= arr[i] <= 100 1 <= m <= 100 2 <= k <= 100
     *
     * @param arr
     * @param m
     * @param k
     * @return
     */
    public boolean containsPattern(int[] arr, int m, int k) {
        int len = arr.length;
        for (int i = 0; i < len - m * k + 1; i++) {
            int idx = i, count = k;
            boolean flag = true;
            while (idx < len && count > 0) {
                if (arr[idx] != arr[i]) {
                    flag = false;
                    break;
                }
                idx += m;
                count--;
            }
            if (!flag) {
                continue;
            }
            if (m == 1) {
                return true;
            }

            noPattern:
            for (int j = 1; j < m; j++) {
                idx = i + j;
                count = k;
                while (idx < len && count > 0) {
                    if (arr[idx] != arr[i + j]) {
                        flag = false;
                        break noPattern;
                    }
                    idx += m;
                    count--;
                }
            }
            if (flag) {
                return true;
            }
        }

        return false;
    }

    public static int MOD = 1_000_000_007;

    /**
     * 5501. 使陆地分离的最少天数
     *
     * <p>给你一个由若干 0 和 1 组成的二维网格 grid ，其中 0 表示水，而 1 表示陆地。岛屿由水平方向或竖直方向上相邻的 1 （陆地）连接形成。
     *
     * <p>如果 恰好只有一座岛屿 ，则认为陆地是 连通的 ；否则，陆地就是 分离的 。
     *
     * <p>一天内，可以将任何单个陆地单元（1）更改为水单元（0）。
     *
     * <p>返回使陆地分离的最少天数。
     *
     * <p>示例 1：
     *
     * <p>输入：grid = [[0,1,1,0],[0,1,1,0],[0,0,0,0]] 输出：2 解释：至少需要 2 天才能得到分离的陆地。 将陆地 grid[1][1] 和
     * grid[0][2] 更改为水，得到两个分离的岛屿。 示例 2：
     *
     * <p>输入：grid = [[1,1]] 输出：2 解释：如果网格中都是水，也认为是分离的 ([[1,1]] -> [[0,0]])，0 岛屿。 示例 3：
     *
     * <p>输入：grid = [[1,0,1,0]] 输出：0 示例 4：
     *
     * <p>输入：grid = [[1,1,0,1,1], [1,1,1,1,1], [1,1,0,1,1], [1,1,0,1,1]] 输出：1 示例 5：
     *
     * <p>输入：grid = [[1,1,0,1,1], [1,1,1,1,1], [1,1,0,1,1], [1,1,1,1,1]] 输出：2
     *
     * <p>提示：
     *
     * <p>1 <= grid.length, grid[i].length <= 30 grid[i][j] 为 0 或 1
     *
     * @param grid
     * @return
     */
    public int minDays(int[][] grid) {
        // 计算岛屿数量 ！= 1 返回 0

        int count = 0;

        int m = grid.length;
        int n = grid[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    count++;
                    if (count > 1) {
                        return 0;
                    }
                    updateAround(grid, i, j);
                }
            }
        }
        if (count == 0) {
            return 0;
        }

        // 找交点,有交点，则答案为1 否则答案为2

        return 2;
    }

    /**
     * 1283. 使结果不超过阈值的最小除数
     *
     * <p>给你一个整数数组 nums 和一个正整数 threshold ，你需要选择一个正整数作为除数，然后将数组里每个数都除以它，并对除法结果求和。
     *
     * <p>请你找出能够使上述结果小于等于阈值 threshold 的除数中 最小 的那个。
     *
     * <p>每个数除以除数后都向上取整，比方说 7/3 = 3 ， 10/2 = 5 。
     *
     * <p>题目保证一定有解。
     *
     * <p>示例 1：
     *
     * <p>输入：nums = [1,2,5,9], threshold = 6 输出：5 解释：如果除数为 1 ，我们可以得到和为 17 （1+2+5+9）。 如果除数为 4
     * ，我们可以得到和为 7 (1+1+2+3) 。如果除数为 5 ，和为 5 (1+1+1+2)。 示例 2：
     *
     * <p>输入：nums = [2,3,5,7,11], threshold = 11 输出：3 示例 3：
     *
     * <p>输入：nums = [19], threshold = 5 输出：4
     *
     * <p>提示：
     *
     * <p>1 <= nums.length <= 5 * 10^4 1 <= nums[i] <= 10^6 nums.length <= threshold <= 10^6
     *
     * @param nums
     * @param threshold
     * @return
     */
    public int smallestDivisor(int[] nums, int threshold) {
        int left = 1;
        int right = Arrays.stream(nums).max().orElse(0);
        int result = 0;
        while (left < right) {
            int mid = (left + right) >> 1;
            int total = 0;
            for (int num : nums) {
                total += (num - 1) / mid + 1;
            }
            if (total <= threshold) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return right;
    }

    @Test
    public void maxSideLength() {
        int[][] mat = {{18, 70}, {61, 1}, {25, 85}, {14, 40}, {11, 96}, {97, 96}, {63, 45}};
        int threshold = 40184;
        logResult(maxSideLength(mat, threshold));
    }

    /**
     * 1292. 元素和小于等于阈值的正方形的最大边长
     *
     * <p>给你一个大小为 m x n 的矩阵 mat 和一个整数阈值 threshold。
     *
     * <p>请你返回元素总和小于或等于阈值的正方形区域的最大边长；如果没有这样的正方形区域，则返回 0 。
     *
     * <p>示例 1：
     *
     * <p>输入：mat = [[1,1,3,2,4,3,2],[1,1,3,2,4,3,2],[1,1,3,2,4,3,2]], threshold = 4 输出：2 解释：总和小于 4
     * 的正方形的最大边长为 2，如图所示。 示例 2：
     *
     * <p>输入：mat = [[2,2,2,2,2],[2,2,2,2,2],[2,2,2,2,2],[2,2,2,2,2],[2,2,2,2,2]], threshold = 1 输出：0
     * 示例 3：
     *
     * <p>输入：mat = [[1,1,1,1],[1,0,0,0],[1,0,0,0],[1,0,0,0]], threshold = 6 输出：3 示例 4：
     *
     * <p>输入：mat = [[18,70],[61,1],[25,85],[14,40],[11,96],[97,96],[63,45]], threshold = 40184 输出：2
     *
     * <p>提示：
     *
     * <p>1 <= m, n <= 300 m == mat.length n == mat[i].length 0 <= mat[i][j] <= 10000 0 <= threshold
     * <= 10^5
     *
     * @param mat
     * @param threshold
     * @return
     */
    public int maxSideLength(int[][] mat, int threshold) {

        m = mat.length;
        n = mat[0].length;
        sum = new int[m + 1][n + 1];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                sum[i + 1][j + 1] = mat[i][j] + sum[i + 1][j] + sum[i][j + 1] - sum[i][j];
            }
        }
        int result = 0;
        int low = 0, high = Math.min(m, n);
        if (canMakeSide(high, threshold)) {
            return high;
        }
        while (low < high) {
            int mid = (low + high) >> 1;
            if (canMakeSide(mid, threshold)) {
                result = mid;
                low = mid + 1;
            } else {
                high = mid;
            }
        }

        return result;
    }

    private int m, n;
    private int[][] sum;

    private boolean canMakeSide(int side, int threshold) {

        for (int i = side; i <= m; i++) {
            for (int j = side; j <= n; j++) {
                if (sum[i][j] - sum[i][j - side] - sum[i - side][j] + sum[i - side][j - side]
                        <= threshold) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * 1296. 划分数组为连续数字的集合
     *
     * <p>给你一个整数数组 nums 和一个正整数 k，请你判断是否可以把这个数组划分成一些由 k 个连续数字组成的集合。 如果可以，请返回 True；否则，返回 False。
     *
     * <p>示例 1：
     *
     * <p>输入：nums = [1,2,3,3,4,4,5,6], k = 4 输出：true 解释：数组可以分成 [1,2,3,4] 和 [3,4,5,6]。 示例 2：
     *
     * <p>输入：nums = [3,2,1,2,3,4,3,4,5,9,10,11], k = 3 输出：true 解释：数组可以分成 [1,2,3] , [2,3,4] , [3,4,5]
     * 和 [9,10,11]。 示例 3：
     *
     * <p>输入：nums = [3,3,2,2,1,1], k = 3 输出：true 示例 4：
     *
     * <p>输入：nums = [1,2,3,4], k = 3 输出：false 解释：数组不能分成几个大小为 3 的子数组。
     *
     * <p>提示：
     *
     * <p>1 <= nums.length <= 10^5 1 <= nums[i] <= 10^9 1 <= k <= nums.length
     *
     * @param nums
     * @param k
     * @return
     */
    public boolean isPossibleDivide(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        int len = nums.length;
        if (len % k != 0) {
            return false;
        }
        Arrays.sort(nums);
        for (int num : nums) {
            int count = map.getOrDefault(num, 0);
            map.put(num, count + 1);
        }

        for (int num : nums) {
            int count = map.getOrDefault(num, 0);
            if (count == 0) {
                continue;
            }
            map.put(num, count - 1);
            for (int i = 1; i < k; i++) {
                int next = num + i;
                count = map.getOrDefault(next, 0);
                if (count == 0) {
                    return false;
                }
                map.put(next, count - 1);
            }
        }

        return true;
    }

    @Test
    public void canReach() {
        int[] arr = {4, 2, 3, 0, 3, 1, 2};
        int start = 5;
        logResult(canReach(arr, start));
    }
    /**
     * 1306. 跳跃游戏 III
     *
     * <p>这里有一个非负整数数组 arr，你最开始位于该数组的起始下标 start 处。当你位于下标 i 处时，你可以跳到 i + arr[i] 或者 i - arr[i]。
     *
     * <p>请你判断自己是否能够跳到对应元素值为 0 的 任一 下标处。
     *
     * <p>注意，不管是什么情况下，你都无法跳到数组之外。
     *
     * <p>示例 1：
     *
     * <p>输入：arr = [4,2,3,0,3,1,2], start = 5 输出：true 解释： 到达值为 0 的下标 3 有以下可能方案： 下标 5 -> 下标 4 -> 下标 1
     * -> 下标 3 下标 5 -> 下标 6 -> 下标 4 -> 下标 1 -> 下标 3 示例 2：
     *
     * <p>输入：arr = [4,2,3,0,3,1,2], start = 0 输出：true 解释： 到达值为 0 的下标 3 有以下可能方案： 下标 0 -> 下标 4 -> 下标 1
     * -> 下标 3 示例 3：
     *
     * <p>输入：arr = [3,0,2,1,2], start = 2 输出：false 解释：无法到达值为 0 的下标 1 处。
     *
     * <p>提示：
     *
     * <p>1 <= arr.length <= 5 * 10^4 0 <= arr[i] < arr.length 0 <= start < arr.length
     *
     * @param arr
     * @param start
     * @return
     */
    public boolean canReach(int[] arr, int start) {
        Queue<Integer> queue = new LinkedList<>();
        int len = arr.length;
        boolean[] visited = new boolean[len];

        queue.offer(start);
        if (arr[start] == 0) {
            return true;
        }
        // 广度优先遍历
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int curIndex = queue.poll();
                if (visited[curIndex]) {
                    continue;
                }
                visited[curIndex] = true;
                if (arr[curIndex] == 0) {
                    return true;
                }
                int left = curIndex - arr[curIndex], right = curIndex + arr[curIndex];
                if (left >= 0) {
                    queue.offer(left);
                }
                if (right < len) {
                    queue.offer(right);
                }
            }
        }

        return false;
    }

    @Test
    public void xorQueries() {
        int[] arr = {1, 3, 4, 8};
        int[][] queries = {{0, 1}, {1, 2}, {0, 3}, {3, 3}};
        int[] result = xorQueries(arr, queries);
        log.debug("result:{}", result);
    }

    /**
     * 1310. 子数组异或查询
     *
     * <p>有一个正整数数组 arr，现给你一个对应的查询数组 queries，其中 queries[i] = [Li, Ri]。
     *
     * <p>对于每个查询 i，请你计算从 Li 到 Ri 的 XOR 值（即 arr[Li] xor arr[Li+1] xor ... xor arr[Ri]）作为本次查询的结果。
     *
     * <p>并返回一个包含给定查询 queries 所有结果的数组。
     *
     * <p>示例 1：
     *
     * <p>输入：arr = [1,3,4,8], queries = [[0,1],[1,2],[0,3],[3,3]] 输出：[2,7,14,8] 解释： 数组中元素的二进制表示形式是：
     * 1 = 0001 3 = 0011 4 = 0100 8 = 1000 查询的 XOR 值为： [0,1] = 1 xor 3 = 2 [1,2] = 3 xor 4 = 7 [0,3]
     * = 1 xor 3 xor 4 xor 8 = 14 [3,3] = 8 示例 2：
     *
     * <p>输入：arr = [4,8,2,10], queries = [[2,3],[1,3],[0,0],[0,3]] 输出：[8,0,4,4]
     *
     * <p>提示：
     *
     * <p>1 <= arr.length <= 3 * 10^4 1 <= arr[i] <= 10^9 1 <= queries.length <= 3 * 10^4
     * queries[i].length == 2 0 <= queries[i][0] <= queries[i][1] < arr.length
     *
     * @param arr
     * @param queries
     * @return
     */
    public int[] xorQueries(int[] arr, int[][] queries) {
        int[] result = new int[queries.length];
        int len = arr.length;
        int[] nums = new int[len + 1];
        int num = 0;
        for (int i = 1; i <= len; i++) {
            num ^= arr[i - 1];
            nums[i] ^= num;
        }
        for (int i = 0; i < queries.length; i++) {
            int left = queries[i][0], right = queries[i][1];
            result[i] = nums[left] ^ nums[right + 1];
        }

        return result;
    }

    @Test
    public void watchedVideosByFriends() {
        List<List<String>> watchedVideos = new ArrayList<>();
        /*watchedVideos.add(Arrays.asList("A", "B"));
        watchedVideos.add(Arrays.asList("C"));
        watchedVideos.add(Arrays.asList("B", "C"));
        watchedVideos.add(Arrays.asList("D"));
        int[][] friends = {{1, 2}, {0, 3}, {0, 3}, {1, 2}};
        int id = 0, level = 2;*/

        watchedVideos.add(Arrays.asList("bjwtssmu"));
        watchedVideos.add(Arrays.asList("aygr", "mqls"));
        watchedVideos.add(Arrays.asList("vrtxa", "zxqzeqy", "nbpl", "qnpl"));
        watchedVideos.add(Arrays.asList("r", "otazhu", "rsf"));
        watchedVideos.add(Arrays.asList("bvcca", "ayyihidz", "ljc", "fiq", "viu"));
        int[][] friends = {{3, 2, 1, 4}, {0, 4}, {4, 0}, {0, 4}, {2, 3, 1, 0}};
        int id = 3, level = 1;
        logResult(watchedVideosByFriends(watchedVideos, friends, id, level));
    }

    /**
     * 1311. 获取你好友已观看的视频
     *
     * <p>有 n 个人，每个人都有一个 0 到 n-1 的唯一 id 。
     *
     * <p>给你数组 watchedVideos 和 friends ，其中 watchedVideos[i] 和 friends[i] 分别表示 id = i
     * 的人观看过的视频列表和他的好友列表。
     *
     * <p>Level 1 的视频包含所有你好友观看过的视频，level 2 的视频包含所有你好友的好友观看过的视频，以此类推。一般的，Level 为 k 的视频包含所有从你出发，最短距离为
     * k 的好友观看过的视频。
     *
     * <p>给定你的 id 和一个 level 值，请你找出所有指定 level 的视频，并将它们按观看频率升序返回。如果有频率相同的视频，请将它们按字母顺序从小到大排列。
     *
     * <p>示例 1：
     *
     * <p>输入：watchedVideos = [["A","B"],["C"],["B","C"],["D"]], friends = [[1,2],[0,3],[0,3],[1,2]],
     * id = 0, level = 1 输出：["B","C"] 解释： 你的 id 为 0（绿色），你的朋友包括（黄色）： id 为 1 -> watchedVideos = ["C"]
     * id 为 2 -> watchedVideos = ["B","C"] 你朋友观看过视频的频率为： B -> 1 C -> 2 示例 2：
     *
     * <p>输入：watchedVideos = [["A","B"],["C"],["B","C"],["D"]], friends = [[1,2],[0,3],[0,3],[1,2]],
     * id = 0, level = 2 输出：["D"] 解释： 你的 id 为 0（绿色），你朋友的朋友只有一个人，他的 id 为 3（黄色）。
     *
     * <p>提示：
     *
     * <p>n == watchedVideos.length == friends.length 2 <= n <= 100 1 <= watchedVideos[i].length <=
     * 100 1 <= watchedVideos[i][j].length <= 8 0 <= friends[i].length < n 0 <= friends[i][j] < n 0
     * <= id < n 1 <= level < n 如果 friends[i] 包含 j ，那么 friends[j] 包含 i
     *
     * @param watchedVideos
     * @param friends
     * @param id
     * @param level
     * @return
     */
    public List<String> watchedVideosByFriends(
            List<List<String>> watchedVideos, int[][] friends, int id, int level) {
        int len = friends.length;
        boolean[] visited = new boolean[len];
        Set<String> set = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(id);

        // 并将它们按观看频率升序返回 如果有频率相同的视频，请将它们按字母顺序从小到大排列
        Map<String, Integer> videoMap = new HashMap<>();

        while (!queue.isEmpty()) {
            int size = queue.size();
            if (level < 0) {
                break;
            }
            for (int i = 0; i < size; i++) {
                int curId = queue.poll();
                if (visited[curId]) {
                    continue;
                }
                visited[curId] = true;
                if (level == 0) {
                    List<String> list = watchedVideos.get(curId);
                    set.addAll(list);
                    for (String video : list) {
                        int count = videoMap.getOrDefault(video, 0);
                        videoMap.put(video, count + 1);
                    }
                    continue;
                }
                int[] friendList = friends[curId];
                for (int friend : friendList) {
                    if (visited[friend]) {
                        continue;
                    }
                    queue.offer(friend);
                }
            }
            level--;
        }
        List<String> list =
                new ArrayList<>(set)
                        .stream()
                                .sorted(
                                        (a, b) -> {
                                            int count1 = videoMap.get(a), count2 = videoMap.get(b);
                                            return count1 == count2
                                                    ? a.compareTo(b)
                                                    : count1 - count2;
                                        })
                                .collect(Collectors.toList());

        return list;
    }

    /**
     * 5491. 矩阵对角线元素的和
     *
     * <p>给你一个正方形矩阵 mat，请你返回矩阵对角线元素的和。
     *
     * <p>请你返回在矩阵主对角线上的元素和副对角线上且不在主对角线上元素的和。
     *
     * <p>示例 1：
     *
     * <p>输入：mat = [[1,2,3], [4,5,6], [7,8,9]] 输出：25 解释：对角线的和为：1 + 5 + 9 + 3 + 7 = 25 请注意，元素
     * mat[1][1] = 5 只会被计算一次。 示例 2：
     *
     * <p>输入：mat = [[1,1,1,1], [1,1,1,1], [1,1,1,1], [1,1,1,1]] 输出：8 示例 3：
     *
     * <p>输入：mat = [[5]] 输出：5
     *
     * <p>提示：
     *
     * <p>n == mat.length == mat[i].length 1 <= n <= 100 1 <= mat[i][j] <= 100
     *
     * @param mat
     * @return
     */
    public int diagonalSum(int[][] mat) {
        int len = mat.length;
        int sum = 0;
        for (int i = 0; i < len; i++) {
            sum += mat[i][i];
            if (i != len - 1 - i) {
                sum += mat[i][len - 1 - i];
            }
        }

        return sum;
    }

    @Test
    public void findLengthOfShortestSubarray() {
        int[] arr = {1, 2, 3, 3, 5, 10, 4, 2, 3, 5};
        logResult(findLengthOfShortestSubarray(arr));
    }

    /**
     * 5493. 删除最短的子数组使剩余数组有序
     *
     * <p>给你一个整数数组 arr ，请你删除一个子数组（可以为空），使得 arr 中剩下的元素是 非递减 的。
     *
     * <p>一个子数组指的是原数组中连续的一个子序列。
     *
     * <p>请你返回满足题目要求的最短子数组的长度。
     *
     * <p>示例 1：
     *
     * <p>输入：arr = [1,2,3,10,4,2,3,5] 输出：3 解释：我们需要删除的最短子数组是 [10,4,2] ，长度为 3 。剩余元素形成非递减数组 [1,2,3,3,5]
     * 。 另一个正确的解为删除子数组 [3,10,4] 。 示例 2：
     *
     * <p>输入：arr = [5,4,3,2,1] 输出：4 解释：由于数组是严格递减的，我们只能保留一个元素。所以我们需要删除长度为 4 的子数组，要么删除 [5,4,3,2]，要么删除
     * [4,3,2,1]。 示例 3：
     *
     * <p>输入：arr = [1,2,3] 输出：0 解释：数组已经是非递减的了，我们不需要删除任何元素。 示例 4：
     *
     * <p>输入：arr = [1] 输出：0
     *
     * <p>提示：
     *
     * <p>1 <= arr.length <= 10^5 0 <= arr[i] <= 10^9
     *
     * @param arr
     * @return
     */
    public int findLengthOfShortestSubarray(int[] arr) {
        int result = 0;
        int len = arr.length;
        if (len == 1) {
            return 0;
        }
        // 从前往后找单调递增,从后往前找单调递减
        int left = 0, right = len - 1;
        while (left + 1 < len && arr[left] <= arr[left + 1]) {
            left++;
        }
        while (right > 0 && arr[right - 1] <= arr[right]) {
            right--;
        }
        log.debug("left :{}, right :{}", left, right);
        result = Math.min(len - left - 1, right);
        if (left >= right) {
            return result;
        }

        // 得到两个有序数组 [0, left] [right ,len - 1];

        // 枚举 [0 , left] 的num, 然后使用二分在 [right, len - 1] 找到第一个 >= num 的 idx
        for (int i = 0; i <= left; i++) {
            if (arr[i] > arr[len - 1]) {
                continue;
            }
            int index = getSubarrayIndex(arr, right, len - 1, arr[i]);
            log.debug("i :{} idx:{}", i, index);
            result = Math.min(result, index - i - 1);
        }

        /*int newleft = 0, newright = len - 1;
        while (newleft < right && arr[newleft] <= arr[right]) {
            if (arr[newleft] <= arr[newleft + 1] && arr[newleft] <= arr[right]) {
                newleft++;
            }
        }
        log.debug("left :{}, right :{}", newleft, right);
        result = Math.min(right - newleft, result);
        while (left < newright && arr[left] <= arr[newright]) {
            if (arr[newright - 1] <= arr[newright] && arr[left] <= arr[newright]) {
                newright--;
            }
        }
        log.debug("left :{}, right :{}", left, newright);
        result = Math.min(newright - left, result);*/
        return result;
    }

    private int getSubarrayIndex(int[] arr, int start, int end, int target) {
        int left = start, right = end;
        while (left < right) {
            int mid = (left + right) >> 1;
            if (target <= arr[mid]) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return right;
    }

    @Test
    public void matrixBlockSum() {
        int[][] mat = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        int k = 2;
        int[][] result = matrixBlockSum(mat, k);
        logResult(result);
    }
    /**
     * 1314. 矩阵区域和
     *
     * <p>给你一个 m * n 的矩阵 mat 和一个整数 K ，请你返回一个矩阵 answer ，其中每个 answer[i][j] 是所有满足下述条件的元素 mat[r][c] 的和：
     *
     * <p>i - K <= r <= i + K, j - K <= c <= j + K (r, c) 在矩阵内。
     *
     * <p>示例 1：
     *
     * <p>输入：mat = [[1,2,3],[4,5,6],[7,8,9]], K = 1 输出：[[12,21,16],[27,45,33],[24,39,28]] 示例 2：
     *
     * <p>输入：mat = [[1,2,3],[4,5,6],[7,8,9]], K = 2 输出：[[45,45,45],[45,45,45],[45,45,45]]
     *
     * <p>提示：
     *
     * <p>m == mat.length n == mat[i].length 1 <= m, n, K <= 100 1 <= mat[i][j] <= 100
     *
     * @param mat
     * @param K
     * @return
     */
    public int[][] matrixBlockSum(int[][] mat, int K) {
        int m = mat.length, n = mat[0].length;

        int[][] sum = new int[m + 1][n + 1];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                sum[i + 1][j + 1] = mat[i][j] + sum[i + 1][j] + sum[i][j + 1] - sum[i][j];
            }
        }
        logResult(sum);
        int[][] result = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int x1 = Math.max(0, i - K), x2 = Math.min(m, i + K + 1);
                int y1 = Math.max(0, j - K), y2 = Math.min(n, j + K + 1);
                result[i][j] = sum[x2][y2] - sum[x2][y1] - sum[x1][y2] + sum[x1][y1];
            }
        }
        return result;
    }

    @Test
    public void numTriplets() {
        int[] nums1 = {43024, 99908}, nums2 = {1864};
        logResult(numTriplets(nums1, nums2));
    }

    /**
     * 5508. 数的平方等于两数乘积的方法数
     *
     * <p>给你两个整数数组 nums1 和 nums2 ，请你返回根据以下规则形成的三元组的数目（类型 1 和类型 2 ）：
     *
     * <p>类型 1：三元组 (i, j, k) ，如果 nums1[i]2 == nums2[j] * nums2[k] 其中 0 <= i < nums1.length 且 0 <= j
     * < k < nums2.length 类型 2：三元组 (i, j, k) ，如果 nums2[i]2 == nums1[j] * nums1[k] 其中 0 <= i <
     * nums2.length 且 0 <= j < k < nums1.length
     *
     * <p>示例 1：
     *
     * <p>输入：nums1 = [7,4], nums2 = [5,2,8,9] 输出：1 解释：类型 1：(1,1,2), nums1[1]^2 = nums2[1] * nums2[2]
     * (4^2 = 2 * 8) 示例 2：
     *
     * <p>输入：nums1 = [1,1], nums2 = [1,1,1] 输出：9 解释：所有三元组都符合题目要求，因为 1^2 = 1 * 1 类型 1：(0,0,1),
     * (0,0,2), (0,1,2), (1,0,1), (1,0,2), (1,1,2), nums1[i]^2 = nums2[j] * nums2[k] 类型 2：(0,0,1),
     * (1,0,1), (2,0,1), nums2[i]^2 = nums1[j] * nums1[k] 示例 3：
     *
     * <p>输入：nums1 = [7,7,8,3], nums2 = [1,2,9,7] 输出：2 解释：有两个符合题目要求的三元组 类型 1：(3,0,2), nums1[3]^2 =
     * nums2[0] * nums2[2] 类型 2：(3,0,1), nums2[3]^2 = nums1[0] * nums1[1] 示例 4：
     *
     * <p>输入：nums1 = [4,7,9,11,23], nums2 = [3,5,1024,12,18] 输出：0 解释：不存在符合题目要求的三元组
     *
     * <p>提示：
     *
     * <p>1 <= nums1.length, nums2.length <= 1000 1 <= nums1[i], nums2[i] <= 10^5
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public int numTriplets(int[] nums1, int[] nums2) {
        int result = 0;
        Map<Long, Integer> map1 = new HashMap<>();
        Map<Long, Integer> map2 = new HashMap<>();
        for (int i = 0; i < nums1.length; i++) {
            for (int j = i + 1; j < nums1.length; j++) {
                long num = (long) nums1[i] * (long) nums1[j];
                int count = map1.getOrDefault(num, 0);
                map1.put(num, count + 1);
            }
        }
        for (int i = 0; i < nums2.length; i++) {
            for (int j = i + 1; j < nums2.length; j++) {
                long num = (long) nums2[i] * (long) nums2[j];
                int count = map2.getOrDefault(num, 0);
                map2.put(num, count + 1);
            }
        }
        for (int i = 0; i < nums1.length; i++) {
            long num = (long) nums1[i] * (long) nums1[i];
            int count = map2.getOrDefault(num, 0);
            result += count;
        }
        for (int i = 0; i < nums2.length; i++) {
            long num = (long) nums2[i] * (long) nums2[i];
            int count = map1.getOrDefault(num, 0);
            result += count;
        }

        return result;
    }

    @Test
    public void diagonalSort() {
        int[][] mat = {{3, 3, 1, 1}, {2, 2, 1, 2}, {1, 1, 1, 2}};
        logResult(diagonalSort(mat));
    }

    /**
     * 1329. 将矩阵按对角线排序
     *
     * <p>给你一个 m * n 的整数矩阵 mat ，请你将同一条对角线上的元素（从左上到右下）按升序排序后，返回排好序的矩阵。
     *
     * <p>示例 1：
     *
     * <p>输入：mat = [[3,3,1,1],[2,2,1,2],[1,1,1,2]] 输出：[[1,1,1,1],[1,2,2,2],[1,2,3,3]]
     *
     * <p>提示：
     *
     * <p>m == mat.length n == mat[i].length 1 <= m, n <= 100 1 <= mat[i][j] <= 100
     *
     * @param mat
     * @return
     */
    public int[][] diagonalSort(int[][] mat) {
        int m = mat.length, n = mat[0].length;

        for (int row = 0; row < m - 1; row++) {
            int col = 0, len = Math.min(m - row, n - col);
            // 冒泡排序
            for (int i = 0; i < len; i++) {
                // 从第一位开始遍历，每一位和后面作比较，把最大的数移动到最后
                for (int j = 0; j < len - 1 - i; j++) {

                    if (mat[row + j][col + j] > mat[row + j + 1][col + j + 1]) {
                        int tmp = mat[row + j][col + j];
                        mat[row + j][col + j] = mat[row + j + 1][col + j + 1];
                        mat[row + j + 1][col + j + 1] = tmp;
                    }
                }
            }
        }
        for (int col = 1; col < n - 1; col++) {
            int row = 0, len = Math.min(m - row, n - col);
            // 冒泡排序
            for (int i = 0; i < len; i++) {
                // 从第一位开始遍历，每一位和后面作比较，把最大的数移动到最后
                for (int j = 0; j < len - 1 - i; j++) {

                    if (mat[row + j][col + j] > mat[row + j + 1][col + j + 1]) {
                        int tmp = mat[row + j][col + j];
                        mat[row + j][col + j] = mat[row + j + 1][col + j + 1];
                        mat[row + j + 1][col + j + 1] = tmp;
                    }
                }
            }
        }
        return mat;
    }

    /**
     * 1333. 餐厅过滤器
     *
     * <p>给你一个餐馆信息数组 restaurants，其中 restaurants[i] = [idi, ratingi, veganFriendlyi, pricei,
     * distancei]。你必须使用以下三个过滤器来过滤这些餐馆信息。
     *
     * <p>其中素食者友好过滤器 veganFriendly 的值可以为 true 或者 false，如果为 true 就意味着你应该只包括 veganFriendlyi 为 true
     * 的餐馆，为 false 则意味着可以包括任何餐馆。此外，我们还有最大价格 maxPrice 和最大距离 maxDistance 两个过滤器，它们分别考虑餐厅的价格因素和距离因素的最大值。
     *
     * <p>过滤后返回餐馆的 id，按照 rating 从高到低排序。如果 rating 相同，那么按 id 从高到低排序。简单起见， veganFriendlyi 和
     * veganFriendly 为 true 时取值为 1，为 false 时，取值为 0 。
     *
     * <p>示例 1：
     *
     * <p>输入：restaurants = [[1,4,1,40,10],[2,8,0,50,5],[3,8,1,30,4],[4,10,0,10,3],[5,1,1,15,1]],
     * veganFriendly = 1, maxPrice = 50, maxDistance = 10 输出：[3,1,5] 解释： 这些餐馆为： 餐馆 1 [id=1,
     * rating=4, veganFriendly=1, price=40, distance=10] 餐馆 2 [id=2, rating=8, veganFriendly=0,
     * price=50, distance=5] 餐馆 3 [id=3, rating=8, veganFriendly=1, price=30, distance=4] 餐馆 4
     * [id=4, rating=10, veganFriendly=0, price=10, distance=3] 餐馆 5 [id=5, rating=1,
     * veganFriendly=1, price=15, distance=1] 在按照 veganFriendly = 1, maxPrice = 50 和 maxDistance =
     * 10 进行过滤后，我们得到了餐馆 3, 餐馆 1 和 餐馆 5（按评分从高到低排序）。 示例 2：
     *
     * <p>输入：restaurants = [[1,4,1,40,10],[2,8,0,50,5],[3,8,1,30,4],[4,10,0,10,3],[5,1,1,15,1]],
     * veganFriendly = 0, maxPrice = 50, maxDistance = 10 输出：[4,3,2,1,5] 解释：餐馆与示例 1 相同，但在
     * veganFriendly = 0 的过滤条件下，应该考虑所有餐馆。 示例 3：
     *
     * <p>输入：restaurants = [[1,4,1,40,10],[2,8,0,50,5],[3,8,1,30,4],[4,10,0,10,3],[5,1,1,15,1]],
     * veganFriendly = 0, maxPrice = 30, maxDistance = 3 输出：[4,5]
     *
     * <p>提示：
     *
     * <p>1 <= restaurants.length <= 10^4 restaurants[i].length == 5 1 <= idi, ratingi, pricei,
     * distancei <= 10^5 1 <= maxPrice, maxDistance <= 10^5 veganFriendlyi 和 veganFriendly 的值为 0 或 1
     * 。 所有 idi 各不相同。
     *
     * @param restaurants
     * @param veganFriendly
     * @param maxPrice
     * @param maxDistance
     * @return
     */
    public List<Integer> filterRestaurants(
            int[][] restaurants, int veganFriendly, int maxPrice, int maxDistance) {
        List<Integer> result = new ArrayList<>();
        List<Restaurant> list = new ArrayList<>();
        for (int[] restaurant : restaurants) {
            if (restaurant[3] > maxPrice) {
                continue;
            }
            if (restaurant[4] > maxDistance) {
                continue;
            }
            if (veganFriendly == 1 && restaurant[2] == 0) {
                continue;
            }
            list.add(new Restaurant(restaurant[0], restaurant[1]));
        }
        Collections.sort(list);
        for (Restaurant restaurant : list) {
            result.add(restaurant.id);
        }

        return result;
    }

    class Restaurant implements Comparable<Restaurant> {
        int id;
        int rating;

        Restaurant(int id, int rating) {
            this.id = id;
            this.rating = rating;
        }
        // 按照 rating 从高到低排序。如果 rating 相同，那么按 id 从高到低排序
        @Override
        public int compareTo(Restaurant that) {
            return this.rating == that.rating ? that.id - this.id : that.rating - this.rating;
        }
    }

    /**
     * 1338. 数组大小减半
     *
     * <p>给你一个整数数组 arr。你可以从中选出一个整数集合，并删除这些整数在数组中的每次出现。
     *
     * <p>返回 至少 能删除数组中的一半整数的整数集合的最小大小。
     *
     * <p>示例 1：
     *
     * <p>输入：arr = [3,3,3,3,5,5,5,2,2,7] 输出：2 解释：选择 {3,7} 使得结果数组为 [5,5,5,2,2]、长度为 5（原数组长度的一半）。 大小为 2
     * 的可行集合有 {3,5},{3,2},{5,2}。 选择 {2,7} 是不可行的，它的结果数组为 [3,3,3,3,5,5,5]，新数组长度大于原数组的二分之一。 示例 2：
     *
     * <p>输入：arr = [7,7,7,7,7,7] 输出：1 解释：我们只能选择集合 {7}，结果数组为空。 示例 3：
     *
     * <p>输入：arr = [1,9] 输出：1 示例 4：
     *
     * <p>输入：arr = [1000,1000,3,7] 输出：1 示例 5：
     *
     * <p>输入：arr = [1,2,3,4,5,6,7,8,9,10] 输出：5
     *
     * <p>提示：
     *
     * <p>1 <= arr.length <= 10^5 arr.length 为偶数 1 <= arr[i] <= 10^5
     *
     * @param arr
     * @return
     */
    public int minSetSize(int[] arr) {
        Map<Integer, Integer> countMap = new HashMap<>();
        for (int num : arr) {
            int count = countMap.getOrDefault(num, 0);
            countMap.put(num, count + 1);
        }
        PriorityQueue<Integer> heap = new PriorityQueue<>((a, b) -> b - a);
        for (int count : countMap.values()) {
            heap.offer(count);
        }

        /* List<Integer> list = new ArrayList<>(countMap.values());
        list.sort((a, b) -> b - a);*/
        int helf = arr.length >> 1;
        if ((arr.length & 1) == 1) {
            helf++;
        }
        int result = 0;
        while (!heap.isEmpty()) {
            helf -= heap.poll();
            result++;
            if (helf <= 0) {
                break;
            }
        }
        return result;
    }

    /**
     * 1343. 大小为 K 且平均值大于等于阈值的子数组数目
     *
     * <p>给你一个整数数组 arr 和两个整数 k 和 threshold 。
     *
     * <p>请你返回长度为 k 且平均值大于等于 threshold 的子数组数目。
     *
     * <p>示例 1：
     *
     * <p>输入：arr = [2,2,2,2,5,5,5,8], k = 3, threshold = 4 输出：3 解释：子数组 [2,5,5],[5,5,5] 和 [5,5,8]
     * 的平均值分别为 4，5 和 6 。其他长度为 3 的子数组的平均值都小于 4 （threshold 的值)。 示例 2：
     *
     * <p>输入：arr = [1,1,1,1,1], k = 1, threshold = 0 输出：5 示例 3：
     *
     * <p>输入：arr = [11,13,17,23,29,31,7,5,2,3], k = 3, threshold = 5 输出：6 解释：前 6 个长度为 3 的子数组平均值都大于 5
     * 。注意平均值不是整数。 示例 4：
     *
     * <p>输入：arr = [7,7,7,7,7,7,7], k = 7, threshold = 7 输出：1 示例 5：
     *
     * <p>输入：arr = [4,4,4,4], k = 4, threshold = 1 输出：1
     *
     * <p>提示：
     *
     * <p>1 <= arr.length <= 10^5 1 <= arr[i] <= 10^4 1 <= k <= arr.length 0 <= threshold <= 10^4
     *
     * @param arr
     * @param k
     * @param threshold
     * @return
     */
    public int numOfSubarrays(int[] arr, int k, int threshold) {
        int target = k * threshold;
        int sum = 0;
        for (int i = 0; i < k; i++) {
            sum += arr[i];
        }
        int result = 0;
        if (sum >= target) {
            result++;
        }
        // 滑动窗口
        for (int i = k; i < arr.length; i++) {
            sum += arr[i] - arr[i - k];
            if (sum >= target) {
                result++;
            }
        }
        return result;
    }

    /**
     * 5511. 二进制矩阵中的特殊位置
     *
     * <p>给你一个大小为 rows x cols 的矩阵 mat，其中 mat[i][j] 是 0 或 1，请返回 矩阵 mat 中特殊位置的数目 。
     *
     * <p>特殊位置 定义：如果 mat[i][j] == 1 并且第 i 行和第 j 列中的所有其他元素均为 0（行和列的下标均 从 0 开始 ），则位置 (i, j) 被称为特殊位置。
     *
     * <p>示例 1：
     *
     * <p>输入：mat = [[1,0,0], [0,0,1], [1,0,0]] 输出：1 解释：(1,2) 是一个特殊位置，因为 mat[1][2] == 1
     * 且所处的行和列上所有其他元素都是 0 示例 2：
     *
     * <p>输入：mat = [[1,0,0], [0,1,0], [0,0,1]] 输出：3 解释：(0,0), (1,1) 和 (2,2) 都是特殊位置 示例 3：
     *
     * <p>输入：mat = [[0,0,0,1], [1,0,0,0], [0,1,1,0], [0,0,0,0]] 输出：2 示例 4：
     *
     * <p>输入：mat = [[0,0,0,0,0], [1,0,0,0,0], [0,1,0,0,0], [0,0,1,0,0], [0,0,0,1,1]] 输出：3
     *
     * <p>提示：
     *
     * <p>rows == mat.length cols == mat[i].length 1 <= rows, cols <= 100 mat[i][j] 是 0 或 1
     *
     * @param mat
     * @return
     */
    public int numSpecial(int[][] mat) {
        int rows = mat.length, cols = mat[0].length;
        int result = 0;

        int[] rowNum = new int[rows];
        int[] colNum = new int[cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (mat[i][j] == 1) {
                    rowNum[i]++;
                    colNum[j]++;
                }
            }
        }
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (mat[i][j] == 1 && rowNum[i] == 1 && colNum[j] == 1) {
                    result++;
                }
            }
        }

        return result;
    }

    /**
     * 5512. 统计不开心的朋友
     *
     * <p>给你一份 n 位朋友的亲近程度列表，其中 n 总是 偶数 。
     *
     * <p>对每位朋友 i，preferences[i] 包含一份 按亲近程度从高到低排列 的朋友列表。换句话说，排在列表前面的朋友与 i
     * 的亲近程度比排在列表后面的朋友更高。每个列表中的朋友均以 0 到 n-1 之间的整数表示。
     *
     * <p>所有的朋友被分成几对，配对情况以列表 pairs 给出，其中 pairs[i] = [xi, yi] 表示 xi 与 yi 配对，且 yi 与 xi 配对。
     *
     * <p>但是，这样的配对情况可能会是其中部分朋友感到不开心。在 x 与 y 配对且 u 与 v 配对的情况下，如果同时满足下述两个条件，x 就会不开心：
     *
     * <p>x 与 u 的亲近程度胜过 x 与 y，且 u 与 x 的亲近程度胜过 u 与 v 返回 不开心的朋友的数目 。
     *
     * <p>示例 1：
     *
     * <p>输入：n = 4, preferences = [[1, 2, 3], [3, 2, 0], [3, 1, 0], [1, 2, 0]], pairs = [[0, 1], [2,
     * 3]] 输出：2 解释： 朋友 1 不开心，因为： - 1 与 0 配对，但 1 与 3 的亲近程度比 1 与 0 高，且 - 3 与 1 的亲近程度比 3 与 2 高。 朋友 3
     * 不开心，因为： - 3 与 2 配对，但 3 与 1 的亲近程度比 3 与 2 高，且 - 1 与 3 的亲近程度比 1 与 0 高。 朋友 0 和 2 都是开心的。 示例 2：
     *
     * <p>输入：n = 2, preferences = [[1], [0]], pairs = [[1, 0]] 输出：0 解释：朋友 0 和 1 都开心。 示例 3：
     *
     * <p>输入：n = 4, preferences = [[1, 3, 2], [2, 3, 0], [1, 3, 0], [0, 2, 1]], pairs = [[1, 3], [0,
     * 2]] 输出：4
     *
     * <p>提示：
     *
     * <p>2 <= n <= 500 n 是偶数 preferences.length == n preferences[i].length == n - 1 0 <=
     * preferences[i][j] <= n - 1 preferences[i] 不包含 i preferences[i] 中的所有值都是独一无二的 pairs.length ==
     * n/2 pairs[i].length == 2 xi != yi 0 <= xi, yi <= n - 1 每位朋友都 恰好 被包含在一对中
     *
     * @param n
     * @param preferences
     * @param pairs
     * @return
     */
    public int unhappyFriends(int n, int[][] preferences, int[][] pairs) {

        Map<Integer, Integer> map = new HashMap<>();
        int result = 0;
        for (int[] pair : pairs) {
            map.put(pair[0], pair[1]);
        }

        for (int i = 0; i < n; i++) {
            // i配对的人
            int friend = getFriend(i, map);
            // i配对到的是最好的人 直接快乐
            if (friend == preferences[i][0]) {
                continue;
            }

            // 查找friend在i这里的亲密度排行
            int x = -1;
            for (int j = 1; j < preferences[i].length; j++) {
                if (preferences[i][j] == friend) {
                    x = j;
                    break;
                }
            }

            // 再查找比friend亲密度高
            for (int z = 0; z < x; z++) {
                // i的第一位朋友
                int friend1 = preferences[i][z];
                // 与i的第一位朋友配对的人
                int friend2 = getFriend(friend1, map);

                // 判断 这位朋友与i的亲密度是否 比 这位朋友配对到的人亲密度高
                int a = -1, b = -1;
                for (int i1 = 0; i1 < preferences[friend1].length; i1++) {
                    if (a != -1 && b != -1) {
                        break;
                    }
                    if (preferences[friend1][i1] == i) {
                        a = i1;
                    }
                    if (preferences[friend1][i1] == friend2) {
                        b = i1;
                    }
                }

                // 是的话就不开心，并跳出循环，否则开心
                if (a < b) {
                    result++;
                    break;
                }
            }
        }
        return result;
    }

    public int getFriend(int i, Map<Integer, Integer> map) {
        int friend = -1;
        if (map.containsKey(i)) {
            friend = map.get(i);
        } else {
            for (Integer key : map.keySet()) {
                if (map.get(key) == i) {
                    friend = key;
                }
            }
        }
        return friend;
    }

    /**
     * 5513. 连接所有点的最小费用
     *
     * <p>给你一个points 数组，表示 2D 平面上的一些点，其中 points[i] = [xi, yi] 。
     *
     * <p>连接点 [xi, yi] 和点 [xj, yj] 的费用为它们之间的 曼哈顿距离 ：|xi - xj| + |yi - yj| ，其中 |val| 表示 val 的绝对值。
     *
     * <p>请你返回将所有点连接的最小总费用。只有任意两点之间 有且仅有 一条简单路径时，才认为所有点都已连接。
     *
     * <p>示例 1：
     *
     * <p>输入：points = [[0,0],[2,2],[3,10],[5,2],[7,0]] 输出：20 解释：
     *
     * <p>我们可以按照上图所示连接所有点得到最小总费用，总费用为 20 。 注意到任意两个点之间只有唯一一条路径互相到达。 示例 2：
     *
     * <p>输入：points = [[3,12],[-2,5],[-4,1]] 输出：18 示例 3：
     *
     * <p>输入：points = [[0,0],[1,1],[1,0],[-1,1]] 输出：4 示例 4：
     *
     * <p>输入：points = [[-1000000,-1000000],[1000000,1000000]] 输出：4000000 示例 5：
     *
     * <p>输入：points = [[0,0]] 输出：0
     *
     * <p>提示：
     *
     * <p>1 <= points.length <= 1000 -106 <= xi, yi <= 106 所有点 (xi, yi) 两两不同。
     *
     * @param points
     * @return
     */
    public int minCostConnectPoints(int[][] points) {
        List<List<Integer>> lists = new ArrayList<List<Integer>>(); // 用列表储存所有的边，以及两个点的集合
        for (int i = 0; i < points.length; i++) {
            for (int j = i + 1; j < points.length; j++) {
                List<Integer> list = new ArrayList<Integer>();
                list.add(
                        Math.abs(points[j][0] - points[i][0])
                                + Math.abs(points[j][1] - points[i][1])); // 存放边的长度
                list.add(i); // 存放第一个点
                list.add(j); // 存放第二个点
                lists.add(list);
            }
        }
        // 按照边的大小进行排序
        lists.sort(
                (a, b) -> {
                    if (a.get(0).equals(b.get(0))) {
                        return a.get(1) - b.get(1);
                    }
                    return a.get(0) - b.get(0);
                });
        int res = 0;
        int[] target = new int[points.length + 1]; // 标记每个点的终点
        Arrays.fill(target, -1);
        for (List<Integer> list : lists) {
            int x = list.get(1); // 获取该边的起点(终点)
            int y = list.get(2); // 获取该边的终点
            if (target[x] == -1 && target[y] == -1) { // 直接添加该条边
                res += list.get(0);
                target[x] = y; // 更新终点
            } else {
                if (getEnd(target, x) != getEnd(target, y)) { // 判断是否构成回路
                    target[getEnd(target, x)] = getEnd(target, y);
                    res += list.get(0);
                }
            }
        }

        return res;
    }

    public int getEnd(int[] target, int i) { // 返回该点的终点
        while (target[i] != -1) {
            i = target[i];
        }
        return i;
    }

    /**
     * LCP 18. 早餐组合
     *
     * <p>小扣在秋日市集选择了一家早餐摊位，一维整型数组 staple 中记录了每种主食的价格，一维整型数组 drinks
     * 中记录了每种饮料的价格。小扣的计划选择一份主食和一款饮料，且花费不超过 x 元。请返回小扣共有多少种购买方案。
     *
     * <p>注意：答案需要以 1e9 + 7 (1000000007) 为底取模，如：计算初始结果为：1000000008，请返回 1
     *
     * <p>示例 1：
     *
     * <p>输入：staple = [10,20,5], drinks = [5,5,2], x = 15
     *
     * <p>输出：6
     *
     * <p>解释：小扣有 6 种购买方案，所选主食与所选饮料在数组中对应的下标分别是： 第 1 种方案：staple[0] + drinks[0] = 10 + 5 = 15； 第 2
     * 种方案：staple[0] + drinks[1] = 10 + 5 = 15； 第 3 种方案：staple[0] + drinks[2] = 10 + 2 = 12； 第 4
     * 种方案：staple[2] + drinks[0] = 5 + 5 = 10； 第 5 种方案：staple[2] + drinks[1] = 5 + 5 = 10； 第 6
     * 种方案：staple[2] + drinks[2] = 5 + 2 = 7。
     *
     * <p>示例 2：
     *
     * <p>输入：staple = [2,1,1], drinks = [8,9,5,1], x = 9
     *
     * <p>输出：8
     *
     * <p>解释：小扣有 8 种购买方案，所选主食与所选饮料在数组中对应的下标分别是： 第 1 种方案：staple[0] + drinks[2] = 2 + 5 = 7； 第 2
     * 种方案：staple[0] + drinks[3] = 2 + 1 = 3； 第 3 种方案：staple[1] + drinks[0] = 1 + 8 = 9； 第 4
     * 种方案：staple[1] + drinks[2] = 1 + 5 = 6； 第 5 种方案：staple[1] + drinks[3] = 1 + 1 = 2； 第 6
     * 种方案：staple[2] + drinks[0] = 1 + 8 = 9； 第 7 种方案：staple[2] + drinks[2] = 1 + 5 = 6； 第 8
     * 种方案：staple[2] + drinks[3] = 1 + 1 = 2；
     *
     * <p>提示：
     *
     * <p>1 <= staple.length <= 10^5 1 <= drinks.length <= 10^5 1 <= staple[i],drinks[i] <= 10^5 1
     * <= x <= 2*10^5
     *
     * @param staple
     * @param drinks
     * @param x
     * @return
     */
    public int breakfastNumber(int[] staple, int[] drinks, int x) {
        Arrays.sort(staple);
        Arrays.sort(drinks);
        int result = 0;
        for (int i = 0; i < staple.length; i++) {
            int num = staple[i];
            if (num >= x) {
                break;
            }
            int num2 = binarySearch(drinks, x - num);
            if (num2 == 0) {
                break;
            }
            result += num2;
            result %= MOD;
        }

        return result;
    }

    private int binarySearch(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        if (nums[left] > target) {
            return 0;
        }
        if (nums[right] <= target) {
            return nums.length;
        }
        while (left < right) {
            int mid = (left + right) >> 1;
            if (nums[mid] > target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }

    /**
     * 5503. 所有奇数长度子数组的和
     *
     * <p>给你一个正整数数组 arr ，请你计算所有可能的奇数长度子数组的和。
     *
     * <p>子数组 定义为原数组中的一个连续子序列。
     *
     * <p>请你返回 arr 中 所有奇数长度子数组的和 。
     *
     * <p>示例 1：
     *
     * <p>输入：arr = [1,4,2,5,3] 输出：58 解释：所有奇数长度子数组和它们的和为： [1] = 1 [4] = 4 [2] = 2 [5] = 5 [3] = 3
     * [1,4,2] = 7 [4,2,5] = 11 [2,5,3] = 10 [1,4,2,5,3] = 15 我们将所有值求和得到 1 + 4 + 2 + 5 + 3 + 7 + 11
     * + 10 + 15 = 58 示例 2：
     *
     * <p>输入：arr = [1,2] 输出：3 解释：总共只有 2 个长度为奇数的子数组，[1] 和 [2]。它们的和为 3 。 示例 3：
     *
     * <p>输入：arr = [10,11,12] 输出：66
     *
     * <p>提示：
     *
     * <p>1 <= arr.length <= 100 1 <= arr[i] <= 1000
     *
     * @param arr
     * @return
     */
    public int sumOddLengthSubarrays(int[] arr) {
        int len = arr.length;
        int[] sums = new int[len + 1];
        int num = 0;
        for (int i = 0; i < len; i++) {
            num += arr[i];
            sums[i + 1] = num;
        }
        int result = 0;
        for (int i = 1; i <= len; i++) {
            int index = i - 1;
            while (index >= 0) {
                result += sums[i] - sums[index];
                index -= 2;
            }
        }
        return result;
    }

    @Test
    public void maxSumRangeQuery() {
        int[] nums = {4, 5, 1};
        int[][] requests = {{0, 1}, {0, 2}, {1, 2}};
        logResult(maxSumRangeQuery(nums, requests));
    }

    /**
     * 5505. 所有排列中的最大和
     *
     * <p>有一个整数数组 nums ，和一个查询数组 requests ，其中 requests[i] = [starti, endi] 。第 i 个查询求 nums[starti] +
     * nums[starti + 1] + ... + nums[endi - 1] + nums[endi] 的结果 ，starti 和 endi 数组索引都是 从 0 开始 的。
     *
     * <p>你可以任意排列 nums 中的数字，请你返回所有查询结果之和的最大值。
     *
     * <p>由于答案可能会很大，请你将它对 109 + 7 取余 后返回。
     *
     * <p>示例 1：
     *
     * <p>输入：nums = [1,2,3,4,5], requests = [[1,3],[0,1]] 输出：19 解释：一个可行的 nums 排列为
     * [2,1,3,4,5]，并有如下结果： requests[0] -> nums[1] + nums[2] + nums[3] = 1 + 3 + 4 = 8 requests[1] ->
     * nums[0] + nums[1] = 2 + 1 = 3 总和为：8 + 3 = 11。 一个总和更大的排列为 [3,5,4,2,1]，并有如下结果： requests[0] ->
     * nums[1] + nums[2] + nums[3] = 5 + 4 + 2 = 11 requests[1] -> nums[0] + nums[1] = 3 + 5 = 8
     * 总和为： 11 + 8 = 19，这个方案是所有排列中查询之和最大的结果。 示例 2：
     *
     * <p>输入：nums = [1,2,3,4,5,6], requests = [[0,1]] 输出：11 解释：一个总和最大的排列为 [6,5,4,3,2,1] ，查询和为 [11]。
     * 示例 3：
     *
     * <p>输入：nums = [1,2,3,4,5,10], requests = [[0,2],[1,3],[1,1]] 输出：47 解释：一个和最大的排列为 [4,10,5,3,2,1]
     * ，查询结果分别为 [19,18,10]。
     *
     * <p>提示：
     *
     * <p>n == nums.length 1 <= n <= 105 0 <= nums[i] <= 105 1 <= requests.length <= 105
     * requests[i].length == 2 0 <= starti <= endi < n
     *
     * @param nums
     * @param requests
     * @return
     */
    public int maxSumRangeQuery(int[] nums, int[][] requests) {
        int n = nums.length;
        int[] counts = new int[n + 1];
        for (int[] request : requests) {
            counts[request[0]]++;
            counts[request[1] + 1]--;
        }
        log.debug("counts:{}", counts);
        for (int i = 1; i <= n; i++) {
            counts[i] += counts[i - 1];
        }
        log.debug("counts:{}", counts);
        Arrays.sort(nums);
        Arrays.sort(counts);
        int result = 0;
        for (int i = 0; i < n; i++) {
            result += counts[i + 1] * nums[i];
            result %= MOD;
        }

        return result;
    }

    @Test
    public void minSubarray() {
        int[] nums = {1000000000, 1000000000, 1000000000};
        int p = 3;
        logResult(minSubarray(nums, p));
    }

    /**
     * 5504. 使数组和能被 P 整除
     *
     * <p>给你一个正整数数组 nums，请你移除 最短 子数组（可以为 空），使得剩余元素的 和 能被 p 整除。 不允许 将整个数组都移除。
     *
     * <p>请你返回你需要移除的最短子数组，如果无法满足题目要求，返回 -1 。
     *
     * <p>子数组 定义为原数组中连续的一组元素。
     *
     * <p>示例 1：
     *
     * <p>输入：nums = [3,1,4,2], p = 6 输出：1 解释：nums 中元素和为 10，不能被 p 整除。我们可以移除子数组 [4] ，剩余元素的和为 6 。 示例 2：
     *
     * <p>输入：nums = [6,3,5,2], p = 9 输出：2 解释：我们无法移除任何一个元素使得和被 9 整除，最优方案是移除子数组 [5,2] ，剩余元素为 [6,3]，和为
     * 9 。 示例 3：
     *
     * <p>输入：nums = [1,2,3], p = 3 输出：0 解释：和恰好为 6 ，已经能被 3 整除了。所以我们不需要移除任何元素。 示例 4：
     *
     * <p>输入：nums = [1,2,3], p = 7 输出：-1 解释：没有任何方案使得移除子数组后剩余元素的和被 7 整除。 示例 5：
     *
     * <p>输入：nums = [1000000000,1000000000,1000000000], p = 3 输出：0
     *
     * <p>提示：
     *
     * <p>1 <= nums.length <= 105 1 <= nums[i] <= 109 1 <= p <= 109
     *
     * @param nums
     * @param p
     * @return
     */
    public int minSubarray(int[] nums, int p) {
        int sum = 0, len = nums.length;

        Map<Integer, Integer> indexMap = new HashMap<>();
        int[] rems = new int[len];
        for (int i = 0; i < len; i++) {
            rems[i] = nums[i] % p;
        }
        int min = len;
        for (int i = 1; i < len; i++) {
            rems[i] += rems[i - 1];
            rems[i] %= p;
        }
        int remainder = rems[len - 1] % p;

        if (remainder == 0) {
            return 0;
        }
        indexMap.put(0, -1);
        // 查找余数为 rem 的最小子串

        for (int i = 0; i < len; i++) {
            int currRem = rems[i];
            int tmpRem = (currRem - remainder + p) % p;
            if (tmpRem >= 0 && indexMap.containsKey(tmpRem)) {
                min = Math.min(min, i - indexMap.get(tmpRem));
            }
            indexMap.put(currRem, i);
        }

        /*min = len - leftMap.getOrDefault(0, 0);
        sum = 0;
        for (int i = len - 1; i >= 0; i--) {
            sum += nums[i];
            int right = sum % p;
            int left = (p - right) % p;
            if (right == 0) {
                min = Math.min(i, min);
            }
            int leftIndex = leftMap.getOrDefault(left, -1);
            if (leftIndex != -1 && i > leftIndex) {
                min = Math.min(min, i - leftIndex);
            }
        }*/
        return min == len ? -1 : min;
    }

    /**
     * LCP 23. 魔术排列
     *
     * <p>秋日市集上，魔术师邀请小扣与他互动。魔术师的道具为分别写有数字 1~N 的 N 张卡牌，然后请小扣思考一个 N 张卡牌的排列 target。
     *
     * <p>魔术师的目标是找到一个数字 k（k >= 1），使得初始排列顺序为 1~N 的卡牌经过特殊的洗牌方式最终变成小扣所想的排列 target，特殊的洗牌方式为：
     *
     * <p>第一步，魔术师将当前位于 偶数位置 的卡牌（下标自 1 开始），保持 当前排列顺序 放在位于 奇数位置 的卡牌之前。例如：将当前排列 [1,2,3,4,5] 位于偶数位置的
     * [2,4] 置于奇数位置的 [1,3,5] 前，排列变为 [2,4,1,3,5]； 第二步，若当前卡牌数量小于等于 k，则魔术师按排列顺序取走全部卡牌；若当前卡牌数量大于 k，则取走前
     * k 张卡牌，剩余卡牌继续重复这两个步骤，直至所有卡牌全部被取走； 卡牌按照魔术师取走顺序构成的新排列为「魔术取数排列」，请返回是否存在这个数字 k 使得「魔术取数排列」恰好就是
     * target，从而让小扣感到大吃一惊。
     *
     * <p>示例 1：
     *
     * <p>输入：target = [2,4,3,1,5]
     *
     * <p>输出：true
     *
     * <p>解释：排列 target 长度为 5，初始排列为：1,2,3,4,5。我们选择 k = 2： 第一次：将当前排列 [1,2,3,4,5] 位于偶数位置的 [2,4] 置于奇数位置的
     * [1,3,5] 前，排列变为 [2,4,1,3,5]。取走前 2 张卡牌 2,4，剩余 [1,3,5]； 第二次：将当前排列 [1,3,5] 位于偶数位置的 [3] 置于奇数位置的
     * [1,5] 前，排列变为 [3,1,5]。取走前 2 张 3,1，剩余 [5]； 第三次：当前排列为 [5]，全部取出。 最后，数字按照取出顺序构成的「魔术取数排列」2,4,3,1,5
     * 恰好为 target。
     *
     * <p>示例 2：
     *
     * <p>输入：target = [5,4,3,2,1]
     *
     * <p>输出：false
     *
     * <p>解释：无法找到一个数字 k 可以使「魔术取数排列」恰好为 target。
     *
     * <p>提示：
     *
     * <p>1 <= target.length = N <= 5000 题目保证 target 是 1~N 的一个排列。
     *
     * @param target
     * @return
     */
    public boolean isMagic(int[] target) {
        int n = target.length;
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = i + 1;
        }
        magicSort(nums, 0);
        int maxk = getSameCount(nums, target, 0);
        if (maxk == 0) {
            return false;
        }
        for (int k = maxk; k >= 1; k--) {
            if (checkMagic(nums, target, k)) {
                return true;
            }
        }

        return false;
    }

    /**
     * 检查 k 是否满足条件
     *
     * @param nums
     * @param target
     * @param k
     * @return
     */
    private boolean checkMagic(int[] nums, int[] target, int k) {
        int start = k;
        while (start < nums.length) {
            magicSort(nums, start);
            if (getSameCount(nums, target, start) < k) {
                break;
            }
            start += k;
        }

        return getSameCount(nums, target, 0) == nums.length;
    }

    /**
     * 魔法排序
     *
     * @param nums
     * @param start
     */
    private void magicSort(int[] nums, int start) {
        Queue<Integer> queue = new LinkedList<>();
        for (int i = start + 1; i < nums.length; i += 2) {
            queue.offer(nums[i]);
        }
        for (int i = start; i < nums.length; i += 2) {
            queue.offer(nums[i]);
        }
        for (int i = start; i < nums.length; i++) {
            nums[i] = queue.poll();
        }
    }

    /**
     * 获取相同的前缀长度
     *
     * @param nums
     * @param target
     * @param start
     * @return
     */
    private int getSameCount(int[] nums, int[] target, int start) {
        int count = 0;
        for (int i = start; i < nums.length; i++) {
            if (nums[i] != target[i]) {
                break;
            }
            count++;
        }
        return count;
    }

    /**
     * 1395. 统计作战单位数
     *
     * <p>n 名士兵站成一排。每个士兵都有一个 独一无二 的评分 rating 。
     *
     * <p>每 3 个士兵可以组成一个作战单位，分组规则如下：
     *
     * <p>从队伍中选出下标分别为 i、j、k 的 3 名士兵，他们的评分分别为 rating[i]、rating[j]、rating[k] 作战单位需满足： rating[i] <
     * rating[j] < rating[k] 或者 rating[i] > rating[j] > rating[k] ，其中 0 <= i < j < k < n
     * 请你返回按上述条件可以组建的作战单位数量。每个士兵都可以是多个作战单位的一部分。
     *
     * <p>示例 1：
     *
     * <p>输入：rating = [2,5,3,4,1] 输出：3 解释：我们可以组建三个作战单位 (2,3,4)、(5,4,1)、(5,3,1) 。 示例 2：
     *
     * <p>输入：rating = [2,1,3] 输出：0 解释：根据题目条件，我们无法组建作战单位。 示例 3：
     *
     * <p>输入：rating = [1,2,3,4] 输出：4
     *
     * <p>提示：
     *
     * <p>n == rating.length 1 <= n <= 200 1 <= rating[i] <= 10^5
     *
     * @param rating
     * @return
     */
    public int numTeams(int[] rating) {
        int n = rating.length;
        int result = 0;
        for (int j = 1; j < n - 1; j++) {
            int num = rating[j];
            int l1 = 0, l2 = 0, r1 = 0, r2 = 0;
            for (int i = 0; i < j; i++) {
                if (rating[i] < num) {
                    l1++;
                }
                if (rating[i] > num) {
                    l2++;
                }
            }
            for (int k = j + 1; k < n; k++) {
                if (num < rating[k]) {
                    r1++;
                }
                if (num > rating[k]) {
                    r2++;
                }
            }
            result += l1 * r1 + l2 * r2;
        }

        return result;
    }

    /**
     * 1366. 通过投票对团队排名
     *
     * <p>现在有一个特殊的排名系统，依据参赛团队在投票人心中的次序进行排名，每个投票者都需要按从高到低的顺序对参与排名的所有团队进行排位。
     *
     * <p>排名规则如下：
     *
     * <p>参赛团队的排名次序依照其所获「排位第一」的票的多少决定。如果存在多个团队并列的情况，将继续考虑其「排位第二」的票的数量。以此类推，直到不再存在并列的情况。
     * 如果在考虑完所有投票情况后仍然出现并列现象，则根据团队字母的字母顺序进行排名。 给你一个字符串数组 votes 代表全体投票者给出的排位情况，请你根据上述排名规则对所有参赛团队进行排名。
     *
     * <p>请你返回能表示按排名系统 排序后 的所有团队排名的字符串。
     *
     * <p>示例 1：
     *
     * <p>输入：votes = ["ABC","ACB","ABC","ACB","ACB"] 输出："ACB" 解释：A 队获得五票「排位第一」，没有其他队获得「排位第一」，所以 A
     * 队排名第一。 B 队获得两票「排位第二」，三票「排位第三」。 C 队获得三票「排位第二」，两票「排位第三」。 由于 C 队「排位第二」的票数较多，所以 C 队排第二，B 队排第三。 示例
     * 2：
     *
     * <p>输入：votes = ["WXYZ","XYZW"] 输出："XWYZ" 解释：X 队在并列僵局打破后成为排名第一的团队。X 队和 W 队的「排位第一」票数一样，但是 X
     * 队有一票「排位第二」，而 W 没有获得「排位第二」。 示例 3：
     *
     * <p>输入：votes = ["ZMNAGUEDSJYLBOPHRQICWFXTVK"] 输出："ZMNAGUEDSJYLBOPHRQICWFXTVK"
     * 解释：只有一个投票者，所以排名完全按照他的意愿。 示例 4：
     *
     * <p>输入：votes = ["BCA","CAB","CBA","ABC","ACB","BAC"] 输出："ABC" 解释： A
     * 队获得两票「排位第一」，两票「排位第二」，两票「排位第三」。 B 队获得两票「排位第一」，两票「排位第二」，两票「排位第三」。 C
     * 队获得两票「排位第一」，两票「排位第二」，两票「排位第三」。 完全并列，所以我们需要按照字母升序排名。 示例 5：
     *
     * <p>输入：votes = ["M","M","M","M"] 输出："M" 解释：只有 M 队参赛，所以它排名第一。
     *
     * <p>提示：
     *
     * <p>1 <= votes.length <= 1000 1 <= votes[i].length <= 26 votes[i].length == votes[j].length
     * for 0 <= i, j < votes.length votes[i][j] 是英文 大写 字母 votes[i] 中的所有字母都是唯一的 votes[0] 中出现的所有字母 同样也
     * 出现在 votes[j] 中，其中 1 <= j < votes.length
     *
     * @param votes
     * @return
     */
    public String rankTeams(String[] votes) {
        StringBuilder sb = new StringBuilder();
        int[][] ranks = new int[26][27];
        for (int i = 0; i < 26; i++) {
            ranks[i][26] = i;
        }
        for (String vote : votes) {
            for (int i = 0; i < vote.length(); i++) {
                char c = vote.charAt(i);
                ranks[c - 'A'][i]++;
            }
        }
        Arrays.sort(
                ranks,
                (rank1, rank2) -> {
                    for (int i = 0; i < 26; i++) {
                        if (rank1[i] != rank2[i]) {
                            return rank2[i] - rank1[i];
                        }
                    }
                    return rank1[26] - rank2[26];
                });
        int len = votes[0].length();
        for (int i = 0; i < len; i++) {
            sb.append((char) ('A' + ranks[i][26]));
        }
        /* Map<Character, int[]> rankMap = new HashMap<>();
        for (String vote : votes) {
            for (int i = 0; i < vote.length(); i++) {
                char c = vote.charAt(i);
                int[] rank = rankMap.computeIfAbsent(c, k -> new int[26]);
                rank[i]++;
            }
        }
        List<Map.Entry<Character, int[]>> list = new ArrayList<>(rankMap.entrySet());
        list.sort(
                (entry1, entry2) -> {
                    int[] rank1 = entry1.getValue(), rank2 = entry2.getValue();
                    for (int i = 0; i < 26; i++) {
                        if (rank1[i] != rank2[i]) {
                            return rank2[i] - rank1[i];
                        }
                    }
                    return entry1.getKey() - entry2.getKey();
                });
        list.forEach(entry -> sb.append(entry.getKey()));*/
        return sb.toString();
    }

    /**
     * 1375. 灯泡开关 III
     *
     * <p>房间中有 n 枚灯泡，编号从 1 到 n，自左向右排成一排。最初，所有的灯都是关着的。
     *
     * <p>在 k 时刻（ k 的取值范围是 0 到 n - 1），我们打开 light[k] 这个灯。
     *
     * <p>灯的颜色要想 变成蓝色 就必须同时满足下面两个条件：
     *
     * <p>灯处于打开状态。 排在它之前（左侧）的所有灯也都处于打开状态。 请返回能够让 所有开着的 灯都 变成蓝色 的时刻 数目 。
     *
     * <p>示例 1：
     *
     * <p>输入：light = [2,1,3,5,4] 输出：3 解释：所有开着的灯都变蓝的时刻分别是 1，2 和 4 。 示例 2：
     *
     * <p>输入：light = [3,2,4,1,5] 输出：2 解释：所有开着的灯都变蓝的时刻分别是 3 和 4（index-0）。 示例 3：
     *
     * <p>输入：light = [4,1,2,3] 输出：1 解释：所有开着的灯都变蓝的时刻是 3（index-0）。 第 4 个灯在时刻 3 变蓝。 示例 4：
     *
     * <p>输入：light = [2,1,4,3,6,5] 输出：3 示例 5：
     *
     * <p>输入：light = [1,2,3,4,5,6] 输出：6
     *
     * <p>提示：
     *
     * <p>n == light.length 1 <= n <= 5 * 10^4 light 是 [1, 2, ..., n] 的一个排列。
     *
     * @param light
     * @return
     */
    public int numTimesAllBlue(int[] light) {
        int result = 0;
        int maxIndex = 0;
        // 遍历数组，记录当前最大亮起来的灯，如果最大亮起来的灯等于遍历过的灯的数量 那么说明前面灯都亮了
        for (int i = 0; i < light.length; i++) {
            maxIndex = Math.max(maxIndex, light[i]);
            if (maxIndex == i + 1) {
                result++;
            }
        }

        return result;
    }

    int[] manager;
    int[] informTime;

    @Test
    public void numOfMinutes() {
        int n = 15, headID = 0;
        int[] manager = {-1, 0, 0, 1, 1, 2, 2, 3, 3, 4, 4, 5, 5, 6, 6},
                informTime = {1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0};
        logResult(numOfMinutes(n, headID, manager, informTime));
    }
    /**
     * 1376. 通知所有员工所需的时间
     *
     * <p>公司里有 n 名员工，每个员工的 ID 都是独一无二的，编号从 0 到 n - 1。公司的总负责人通过 headID 进行标识。
     *
     * <p>在 manager 数组中，每个员工都有一个直属负责人，其中 manager[i] 是第 i 名员工的直属负责人。对于总负责人，manager[headID] =
     * -1。题目保证从属关系可以用树结构显示。
     *
     * <p>公司总负责人想要向公司所有员工通告一条紧急消息。他将会首先通知他的直属下属们，然后由这些下属通知他们的下属，直到所有的员工都得知这条紧急消息。
     *
     * <p>第 i 名员工需要 informTime[i] 分钟来通知它的所有直属下属（也就是说在 informTime[i] 分钟后，他的所有直属下属都可以开始传播这一消息）。
     *
     * <p>返回通知所有员工这一紧急消息所需要的 分钟数 。
     *
     * <p>示例 1：
     *
     * <p>输入：n = 1, headID = 0, manager = [-1], informTime = [0] 输出：0 解释：公司总负责人是该公司的唯一一名员工。 示例 2：
     *
     * <p>输入：n = 6, headID = 2, manager = [2,2,-1,2,2,2], informTime = [0,0,1,0,0,0] 输出：1 解释：id = 2
     * 的员工是公司的总负责人，也是其他所有员工的直属负责人，他需要 1 分钟来通知所有员工。 上图显示了公司员工的树结构。 示例 3：
     *
     * <p>输入：n = 7, headID = 6, manager = [1,2,3,4,5,6,-1], informTime = [0,6,5,4,3,2,1] 输出：21
     * 解释：总负责人 id = 6。他将在 1 分钟内通知 id = 5 的员工。 id = 5 的员工将在 2 分钟内通知 id = 4 的员工。 id = 4 的员工将在 3 分钟内通知
     * id = 3 的员工。 id = 3 的员工将在 4 分钟内通知 id = 2 的员工。 id = 2 的员工将在 5 分钟内通知 id = 1 的员工。 id = 1 的员工将在 6
     * 分钟内通知 id = 0 的员工。 所需时间 = 1 + 2 + 3 + 4 + 5 + 6 = 21 。 示例 4：
     *
     * <p>输入：n = 15, headID = 0, manager = [-1,0,0,1,1,2,2,3,3,4,4,5,5,6,6], informTime =
     * [1,1,1,1,1,1,1,0,0,0,0,0,0,0,0] 输出：3 解释：第一分钟总负责人通知员工 1 和 2 。 第二分钟他们将会通知员工 3, 4, 5 和 6 。
     * 第三分钟他们将会通知剩下的员工。 示例 5：
     *
     * <p>输入：n = 4, headID = 2, manager = [3,3,-1,2], informTime = [0,0,162,914] 输出：1076
     *
     * <p>提示：
     *
     * <p>1 <= n <= 10^5 0 <= headID < n manager.length == n 0 <= manager[i] < n manager[headID] ==
     * -1 informTime.length == n 0 <= informTime[i] <= 1000 如果员工 i 没有下属，informTime[i] == 0 。 题目 保证
     * 所有员工都可以收到通知。
     *
     * @param n
     * @param headID
     * @param manager
     * @param informTime
     * @return
     */
    public int numOfMinutes(int n, int headID, int[] manager, int[] informTime) {
        this.manager = manager;
        this.informTime = informTime;
        int result = 0;
        int[] visitTime = new int[n];
        for (int i = 0; i < n; i++) {
            int time = numOfMinutesDfs(i, visitTime);
            result = Math.max(time, result);
        }

        return result;
    }

    private int numOfMinutesDfs(int id, int[] visitTime) {
        int managerId = manager[id];
        if (managerId == -1) {
            return 0;
        }
        if (visitTime[id] > 0) {
            return visitTime[id];
        }
        int result = informTime[managerId] + numOfMinutesDfs(managerId, visitTime);
        visitTime[id] = result;
        return result;
    }

    @Test
    public void maxNumberOfFamilies() {

        int n = 3;
        int[][] reservedSeats = {{1, 2}, {1, 3}, {1, 8}, {2, 6}, {3, 1}, {3, 10}};
        logResult(maxNumberOfFamilies(n, reservedSeats));
    }

    /**
     * 1386. 安排电影院座位
     *
     * <p>如上图所示，电影院的观影厅中有 n 行座位，行编号从 1 到 n ，且每一行内总共有 10 个座位，列编号从 1 到 10 。
     *
     * <p>给你数组 reservedSeats ，包含所有已经被预约了的座位。比如说，researvedSeats[i]=[3,8] ，它表示第 3 行第 8 个座位被预约了。
     *
     * <p>请你返回 最多能安排多少个 4 人家庭 。4 人家庭要占据 同一行内连续 的 4 个座位。隔着过道的座位（比方说 [3,3] 和 [3,4]）不是连续的座位，但是如果你可以将 4
     * 人家庭拆成过道两边各坐 2 人，这样子是允许的。
     *
     * <p>示例 1：
     *
     * <p>输入：n = 3, reservedSeats = [[1,2],[1,3],[1,8],[2,6],[3,1],[3,10]] 输出：4
     * 解释：上图所示是最优的安排方案，总共可以安排 4 个家庭。蓝色的叉表示被预约的座位，橙色的连续座位表示一个 4 人家庭。 示例 2：
     *
     * <p>输入：n = 2, reservedSeats = [[2,1],[1,8],[2,6]] 输出：2 示例 3：
     *
     * <p>输入：n = 4, reservedSeats = [[4,3],[1,4],[4,6],[1,7]] 输出：4
     *
     * <p>提示：
     *
     * <p>1 <= n <= 10^9 1 <= reservedSeats.length <= min(10*n, 10^4) reservedSeats[i].length == 2 1
     * <= reservedSeats[i][0] <= n 1 <= reservedSeats[i][1] <= 10 所有 reservedSeats[i] 都是互不相同的。
     *
     * @param n
     * @param reservedSeats
     * @return
     */
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {
        // 利用位运算
        int left = 0b11110000;
        int middle = 0b11000011;
        int right = 0b00001111;
        // 使用map 存储 , 数组会导致内存溢出
        Map<Integer, Integer> map = new HashMap<>();
        for (int[] seat : reservedSeats) {
            if (seat[1] == 1 || seat[1] == 10) {
                continue;
            }

            int num = map.getOrDefault(seat[0], 0);
            num |= 1 << (seat[1] - 2);
            map.put(seat[0], num);
        }
        // 为 0 的 位置 可以座2家
        int result = (n - map.size()) * 2;
        for (int num : map.values()) {
            if (((num | left) == left) || ((num | middle) == middle) || ((num | right) == right)) {
                result++;
            }
        }

        return result;
    }

    /**
     * 1391. 检查网格中是否存在有效路径
     *
     * <p>给你一个 m x n 的网格 grid。网格里的每个单元都代表一条街道。grid[i][j] 的街道可以是：
     *
     * <p>1 表示连接左单元格和右单元格的街道。 2 表示连接上单元格和下单元格的街道。 3 表示连接左单元格和下单元格的街道。 4 表示连接右单元格和下单元格的街道。 5
     * 表示连接左单元格和上单元格的街道。 6 表示连接右单元格和上单元格的街道。
     *
     * <p>你最开始从左上角的单元格 (0,0) 开始出发，网格中的「有效路径」是指从左上方的单元格 (0,0) 开始、一直到右下方的 (m-1,n-1) 结束的路径。该路径必须只沿着街道走。
     *
     * <p>注意：你 不能 变更街道。
     *
     * <p>如果网格中存在有效的路径，则返回 true，否则返回 false 。
     *
     * <p>示例 1：
     *
     * <p>输入：grid = [[2,4,3],[6,5,2]] 输出：true 解释：如图所示，你可以从 (0, 0) 开始，访问网格中的所有单元格并到达 (m - 1, n - 1) 。
     * 示例 2：
     *
     * <p>输入：grid = [[1,2,1],[1,2,1]] 输出：false 解释：如图所示，单元格 (0, 0) 上的街道没有与任何其他单元格上的街道相连，你只会停在 (0, 0)
     * 处。 示例 3：
     *
     * <p>输入：grid = [[1,1,2]] 输出：false 解释：你会停在 (0, 1)，而且无法到达 (0, 2) 。 示例 4：
     *
     * <p>输入：grid = [[1,1,1,1,1,1,3]] 输出：true 示例 5：
     *
     * <p>输入：grid = [[2],[2],[2],[2],[2],[2],[6]] 输出：true
     *
     * <p>提示：
     *
     * <p>m == grid.length n == grid[i].length 1 <= m, n <= 300 1 <= grid[i][j] <= 6
     *
     * @param grid
     * @return
     */
    public boolean hasValidPath(int[][] grid) {
        M = grid.length;
        N = grid[0].length;
        hasValidPathVisited = new boolean[M][N];
        this.grid = grid;
        int start = grid[0][0];
        for (int i = 0; i < 4; i++) {
            if (pipe[start][i] == -1) {
                continue;
            }
            if (hasValidPathDfs(0, 0, pipe[start][i])) {
                return true;
            }
        }

        return false;
    }

    // 该坐标是否走过
    boolean[][] hasValidPathVisited;
    int[][] pipe =
            new int[][] {
                {-1, -1, -1, -1},
                {-1, 1, -1, 3},
                {0, -1, 2, -1},
                {-1, 0, 3, -1},
                {-1, -1, 1, 0},
                {3, 2, -1, -1},
                {1, -1, -1, 2}
            };
    int[][] grid;

    private boolean hasValidPathDfs(int row, int col, int flag) {

        if (row == M - 1 && col == N - 1) {
            return true;
        }
        hasValidPathVisited[row][col] = true;
        int nextRow = row + DIR_ROW[flag], nextCol = col + DIR_COL[flag];
        if (!inArea(nextRow, nextCol, M, N) || hasValidPathVisited[nextRow][nextCol]) {
            return false;
        }
        int next = grid[nextRow][nextCol];
        if (pipe[next][flag] != -1) {
            return hasValidPathDfs(nextRow, nextCol, pipe[next][flag]);
        }

        return false;
    }

    @Test
    public void validTicTacToe() {
        String[] board = {"OXX", "XOX", "OXO"};
        logResult(validTicTacToe(board));
    }

    /**
     * 794. 有效的井字游戏
     *
     * <p>用字符串数组作为井字游戏的游戏板 board。当且仅当在井字游戏过程中，玩家有可能将字符放置成游戏板所显示的状态时，才返回 true。
     *
     * <p>该游戏板是一个 3 x 3 数组，由字符 " "，"X" 和 "O" 组成。字符 " " 代表一个空位。
     *
     * <p>以下是井字游戏的规则：
     *
     * <p>玩家轮流将字符放入空位（" "）中。 第一个玩家总是放字符 “X”，且第二个玩家总是放字符 “O”。 “X” 和 “O” 只允许放置在空位中，不允许对已放有字符的位置进行填充。
     * 当有 3 个相同（且非空）的字符填充任何行、列或对角线时，游戏结束。 当所有位置非空时，也算为游戏结束。 如果游戏结束，玩家不允许再放置字符。 示例 1: 输入: board = ["O
     * ", " ", " "] 输出: false 解释: 第一个玩家总是放置“X”。
     *
     * <p>示例 2: 输入: board = ["XOX", " X ", " "] 输出: false 解释: 玩家应该是轮流放置的。
     *
     * <p>示例 3: 输入: board = ["XXX", " ", "OOO"] 输出: false
     *
     * <p>示例 4: 输入: board = ["XOX", "O O", "XOX"] 输出: true 说明:
     *
     * <p>游戏板 board 是长度为 3 的字符串数组，其中每个字符串 board[i] 的长度为 3。 board[i][j] 是集合 {" ", "X", "O"} 中的一个字符。
     *
     * @param board
     * @return
     */
    public boolean validTicTacToe(String[] board) {
        int xCount = 0, oCount = 0;

        for (String str : board) {
            for (char c : str.toCharArray()) {
                switch (c) {
                    case 'X':
                        xCount++;
                        break;
                    case 'O':
                        oCount++;
                        break;
                }
            }
        }
        if (xCount != oCount && (xCount - oCount) != 1) {
            return false;
        }
        if (validTicTacToeWin(board, 'X') && (xCount - oCount) != 1) {
            return false;
        }
        if (validTicTacToeWin(board, 'O') && xCount != oCount) {
            return false;
        }
        return true;
    }

    private boolean validTicTacToeWin(String[] board, char c) {
        for (int i = 0; i < 3; i++) {
            if (board[i].charAt(0) == c && board[i].charAt(1) == c && board[i].charAt(2) == c) {
                return true;
            }
            if (board[0].charAt(i) == c && board[1].charAt(i) == c && board[2].charAt(i) == c) {
                return true;
            }
        }
        if (board[0].charAt(0) == c && board[1].charAt(1) == c && board[2].charAt(2) == c) {
            return true;
        }
        if (board[0].charAt(2) == c && board[1].charAt(1) == c && board[2].charAt(0) == c) {
            return true;
        }

        return false;
    }

    /**
     * 795. 区间子数组个数
     *
     * <p>给定一个元素都是正整数的数组A ，正整数 L 以及 R (L <= R)。
     *
     * <p>求连续、非空且其中最大元素满足大于等于L 小于等于R的子数组个数。
     *
     * <p>例如 : 输入: A = [2, 1, 4, 3] L = 2 R = 3 输出: 3 解释: 满足条件的子数组: [2], [2, 1], [3]. 注意:
     *
     * <p>L, R 和 A[i] 都是整数，范围在 [0, 10^9]。 数组 A 的长度范围在[1, 50000]。
     *
     * @param A
     * @param L
     * @param R
     * @return
     */
    public int numSubarrayBoundedMax(int[] A, int L, int R) {
        // L ~ R 的 子数组个数 =  <= R的子数组个 - <=L-1 的子数组个数
        return getSubarrayLessThen(A, R) - getSubarrayLessThen(A, L - 1);
    }

    /**
     * <= target 的子数组个数
     *
     * @param nums
     * @param target
     * @return
     */
    private int getSubarrayLessThen(int[] nums, int target) {
        int result = 0, count = 0;
        for (int num : nums) {
            count = num <= target ? count + 1 : 0;
            result += count;
        }

        return result;
    }

    /**
     * 792. 匹配子序列的单词数
     *
     * <p>给定字符串 S 和单词字典 words, 求 words[i] 中是 S 的子序列的单词个数。
     *
     * <p>示例: 输入: S = "abcde" words = ["a", "bb", "acd", "ace"] 输出: 3 解释: 有三个是 S 的子序列的单词: "a",
     * "acd", "ace"。 注意:
     *
     * <p>所有在words和 S 里的单词都只由小写字母组成。 S 的长度在 [1, 50000]。 words 的长度在 [1, 5000]。 words[i]的长度在[1, 50]。
     *
     * @param S
     * @param words
     * @return
     */
    public int numMatchingSubseq(String S, String[] words) {
        Map<Character, List<WordNode>> letterMap = new HashMap<>();
        for (String word : words) {
            char c = word.charAt(0);
            List<WordNode> list = letterMap.computeIfAbsent(c, k -> new ArrayList<>());
            list.add(new WordNode(word));
        }
        int result = 0;
        for (char c : S.toCharArray()) {
            List<WordNode> list = letterMap.get(c);
            if (Objects.isNull(list)) {
                continue;
            }
            letterMap.remove(c);
            for (WordNode node : list) {
                node.index++;
                if (node.index == node.word.length()) {
                    result++;
                } else {
                    char next = node.word.charAt(node.index);
                    List<WordNode> nextList =
                            letterMap.computeIfAbsent(next, k -> new ArrayList<>());
                    nextList.add(node);
                }
            }
        }
        return result;
    }

    static class WordNode {
        String word;
        int index;

        WordNode(String word) {
            this.word = word;
            index = 0;
        }
    }

    /**
     * 807. 保持城市天际线
     *
     * <p>在二维数组grid中，grid[i][j]代表位于某处的建筑物的高度。 我们被允许增加任何数量（不同建筑物的数量可能不同）的建筑物的高度。 高度 0 也被认为是建筑物。
     *
     * <p>最后，从新数组的所有四个方向（即顶部，底部，左侧和右侧）观看的“天际线”必须与原始数组的天际线相同。 城市的天际线是从远处观看时，由所有建筑物形成的矩形的外部轮廓。
     * 请看下面的例子。
     *
     * <p>建筑物高度可以增加的最大总和是多少？
     *
     * <p>例子： 输入： grid = [[3,0,8,4],[2,4,5,7],[9,2,6,3],[0,3,1,0]] 输出： 35 解释： The grid is: [ [3, 0,
     * 8, 4], [2, 4, 5, 7], [9, 2, 6, 3], [0, 3, 1, 0] ]
     *
     * <p>从数组竖直方向（即顶部，底部）看“天际线”是：[9, 4, 8, 7] 从水平水平方向（即左侧，右侧）看“天际线”是：[8, 7, 9, 3]
     *
     * <p>在不影响天际线的情况下对建筑物进行增高后，新数组如下：
     *
     * <p>gridNew = [ [8, 4, 8, 7], [7, 4, 7, 7], [9, 4, 8, 7], [3, 3, 3, 3] ] 说明:
     *
     * <p>1 < grid.length = grid[0].length <= 50。 grid[i][j] 的高度范围是： [0, 100]。
     * 一座建筑物占据一个grid[i][j]：换言之，它们是 1 x 1 x grid[i][j] 的长方体。
     *
     * @param grid
     * @return
     */
    public int maxIncreaseKeepingSkyline(int[][] grid) {
        int rows = grid.length, cols = grid[0].length;
        int[] rowHeight = new int[rows], colHeight = new int[cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                rowHeight[i] = Math.max(rowHeight[i], grid[i][j]);
                colHeight[j] = Math.max(colHeight[j], grid[i][j]);
            }
        }
        int result = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int height = Math.min(rowHeight[i], colHeight[j]);
                if (height > grid[i][j]) {
                    result += height - grid[i][j];
                }
            }
        }
        return result;
    }

    /**
     * 845. 数组中的最长山脉
     *
     * <p>我们把数组 A 中符合下列属性的任意连续子数组 B 称为 “山脉”：
     *
     * <p>B.length >= 3 存在 0 < i < B.length - 1 使得 B[0] < B[1] < ... B[i-1] < B[i] > B[i+1] > ... >
     * B[B.length - 1] （注意：B 可以是 A 的任意子数组，包括整个数组 A。）
     *
     * <p>给出一个整数数组 A，返回最长 “山脉” 的长度。
     *
     * <p>如果不含有 “山脉” 则返回 0。
     *
     * <p>示例 1：
     *
     * <p>输入：[2,1,4,7,3,2,5] 输出：5 解释：最长的 “山脉” 是 [1,4,7,3,2]，长度为 5。 示例 2：
     *
     * <p>输入：[2,2,2] 输出：0 解释：不含 “山脉”。
     *
     * <p>提示：
     *
     * <p>0 <= A.length <= 10000 0 <= A[i] <= 10000
     *
     * @param A
     * @return
     */
    public int longestMountain(int[] A) {
        int len = A.length;
        int[] left = new int[len], right = new int[len];
        for (int i = 1; i < len; i++) {
            if (A[i - 1] < A[i]) {
                left[i] = left[i - 1] + 1;
            }
        }
        for (int i = len - 2; i >= 0; i--) {
            if (A[i] > A[i + 1]) {
                right[i] = right[i + 1] + 1;
            }
        }
        int result = 0;
        for (int i = 1; i < len - 1; i++) {
            if (left[i] > 0 && right[i] > 0) {
                result = Math.max(result, left[i] + right[i] + 1);
            }
        }

        return result;
    }

    @Test
    public void alertNames() {
        String[] keyName = {"a", "a", "a", "a", "a", "a", "b", "b", "b", "b", "b"},
                keyTime =
                        {
                            "23:27", "03:14", "12:57", "13:35", "13:18", "21:58", "22:39", "10:49",
                            "19:37", "14:14", "10:41"
                        };

        logResult(alertNames(keyName, keyTime));
    }
    /**
     * 5516. 警告一小时内使用相同员工卡大于等于三次的人
     *
     * <p>力扣公司的员工都使用员工卡来开办公室的门。每当一个员工使用一次他的员工卡，安保系统会记录下员工的名字和使用时间。如果一个员工在一小时时间内使用员工卡的次数大于等于三次，这个系统会自动发布一个
     * 警告 。
     *
     * <p>给你字符串数组 keyName 和 keyTime ，期中 [keyName[i], keyTime[i]] 对应一个人的名字和他在 某一天 内使用员工卡的时间。
     *
     * <p>使用时间的格式是 24小时制 ，形如 "HH:MM" ，比方说 "23:51" 和 "09:49" 。
     *
     * <p>请你返回去重后的收到系统警告的员工名字，将它们按 字典序升序 排序后返回。
     *
     * <p>请注意 "10:00" - "11:00" 视为一个小时时间范围内，而 "23:51" - "00:10" 不被视为一小时内，因为系统记录的是某一天内的使用情况。
     *
     * <p>示例 1：
     *
     * <p>输入：keyName = ["daniel","daniel","daniel","luis","luis","luis","luis"], keyTime =
     * ["10:00","10:40","11:00","09:00","11:00","13:00","15:00"] 输出：["daniel"] 解释："daniel" 在一小时内使用了
     * 3 次员工卡（"10:00"，"10:40"，"11:00"）。 示例 2：
     *
     * <p>输入：keyName = ["alice","alice","alice","bob","bob","bob","bob"], keyTime =
     * ["12:01","12:00","18:00","21:00","21:20","21:30","23:00"] 输出：["bob"] 解释："bob" 在一小时内使用了 3
     * 次员工卡（"21:00"，"21:20"，"21:30"）。 示例 3：
     *
     * <p>输入：keyName = ["john","john","john"], keyTime = ["23:58","23:59","00:01"] 输出：[] 示例 4：
     *
     * <p>输入：keyName = ["leslie","leslie","leslie","clare","clare","clare","clare"], keyTime =
     * ["13:00","13:20","14:00","18:00","18:51","19:30","19:49"] 输出：["clare","leslie"]
     *
     * <p>提示：
     *
     * <p>1 <= keyName.length, keyTime.length <= 105 keyName.length == keyTime.length keyTime 格式为
     * "HH:MM" 。 保证 [keyName[i], keyTime[i]] 形成的二元对 互不相同 。 1 <= keyName[i].length <= 10 keyName[i]
     * 只包含小写英文字母。
     *
     * @param keyName
     * @param keyTime
     * @return
     */
    public List<String> alertNames(String[] keyName, String[] keyTime) {

        Set<String> set = new HashSet<>();
        Map<String, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < keyName.length; i++) {
            String name = keyName[i];
            int time = getTime(keyTime[i]);
            List<Integer> list = map.computeIfAbsent(name, k -> new ArrayList<>());
            list.add(time);
        }
        for (Map.Entry<String, List<Integer>> entry : map.entrySet()) {
            String name = entry.getKey();
            List<Integer> list = entry.getValue();
            Collections.sort(list);
            TreeSet<Integer> treeSet = new TreeSet<>();
            out:
            for (int time : list) {
                treeSet.add(time);
                SortedSet<Integer> count = treeSet.subSet(time - 60, time + 1);
                if (count.size() >= 3) {
                    set.add(name);
                    break out;
                }
            }
        }

        List<String> result = new ArrayList<>(set);
        Collections.sort(result);
        return result;
    }

    private int getTime(String s) {
        int hours = Integer.valueOf(s.substring(0, 2));
        int mins = Integer.valueOf(s.substring(3));
        return hours * 60 + mins;
    }

    @Test
    public void test11() {
        String s = "10:30";
        logResult(getTime(s));
    }

    /**
     * 5531. 特殊数组的特征值
     *
     * <p>给你一个非负整数数组 nums 。如果存在一个数 x ，使得 nums 中恰好有 x 个元素 大于或者等于 x ，那么就称 nums 是一个 特殊数组 ，而 x 是该数组的 特征值
     * 。
     *
     * <p>注意： x 不必 是 nums 的中的元素。
     *
     * <p>如果数组 nums 是一个 特殊数组 ，请返回它的特征值 x 。否则，返回 -1 。可以证明的是，如果 nums 是特殊数组，那么其特征值 x 是 唯一的 。
     *
     * <p>示例 1：
     *
     * <p>输入：nums = [3,5] 输出：2 解释：有 2 个元素（3 和 5）大于或等于 2 。 示例 2：
     *
     * <p>输入：nums = [0,0] 输出：-1 解释：没有满足题目要求的特殊数组，故而也不存在特征值 x 。 如果 x = 0，应该有 0 个元素 >= x，但实际有 2 个。 如果
     * x = 1，应该有 1 个元素 >= x，但实际有 0 个。 如果 x = 2，应该有 2 个元素 >= x，但实际有 0 个。 x 不能取更大的值，因为 nums 中只有两个元素。
     * 示例 3：
     *
     * <p>输入：nums = [0,4,3,0,4] 输出：3 解释：有 3 个元素大于或等于 3 。 示例 4：
     *
     * <p>输入：nums = [3,6,7,7,0] 输出：-1
     *
     * @param nums
     * @return
     */
    public int specialArray(int[] nums) {
        int len = nums.length;
        Arrays.sort(nums);
        if (nums[0] >= len) {
            return len;
        }
        for (int i = 1; i < len; i++) {
            int count = len - i;
            if (nums[i] >= count && nums[i - 1] < count) {
                return count;
            }
        }

        return -1;
    }

    /**
     * 1610. 可见点的最大数目
     *
     * <p>给你一个点数组 points 和一个表示角度的整数 angle ，你的位置是 location ，其中 location = [posx, posy] 且 points[i] =
     * [xi, yi] 都表示 X-Y 平面上的整数坐标。
     *
     * <p>最开始，你面向东方进行观测。你 不能 进行移动改变位置，但可以通过 自转 调整观测角度。换句话说，posx 和 posy 不能改变。你的视野范围的角度用 angle 表示，
     * 这决定了你观测任意方向时可以多宽。设 d 为逆时针旋转的度数，那么你的视野就是角度范围 [d - angle/2, d + angle/2] 所指示的那片区域。
     *
     * <p>对于每个点，如果由该点、你的位置以及从你的位置直接向东的方向形成的角度 位于你的视野中 ，那么你就可以看到它。
     *
     * <p>同一个坐标上可以有多个点。你所在的位置也可能存在一些点，但不管你的怎么旋转，总是可以看到这些点。同时，点不会阻碍你看到其他点。
     *
     * <p>返回你能看到的点的最大数目。
     *
     * <p>示例 1：
     *
     * <p>输入：points = [[2,1],[2,2],[3,3]], angle = 90, location = [1,1] 输出：3
     * 解释：阴影区域代表你的视野。在你的视野中，所有的点都清晰可见，尽管 [2,2] 和 [3,3]在同一条直线上，你仍然可以看到 [3,3] 。 示例 2：
     *
     * <p>输入：points = [[2,1],[2,2],[3,4],[1,1]], angle = 90, location = [1,1] 输出：4
     * 解释：在你的视野中，所有的点都清晰可见，包括你所在位置的那个点。 示例 3：
     *
     * <p>输入：points = [[0,1],[2,1]], angle = 13, location = [1,1] 输出：1 解释：如图所示，你只能看到两点之一。
     *
     * <p>提示：
     *
     * <p>1 <= points.length <= 105 points[i].length == 2 location.length == 2 0 <= angle < 360 0 <=
     * posx, posy, xi, yi <= 109
     *
     * @param points
     * @param angle
     * @param location
     * @return
     */
    public int visiblePoints(List<List<Integer>> points, int angle, List<Integer> location) {
        List<Double> angles = new ArrayList<>();
        int same = 0;
        for (List<Integer> point : points) {
            if (point.get(1).equals(location.get(1))) {
                if (point.get(0).equals(location.get(0))) {
                    same++;
                    continue;
                }
                angles.add(point.get(0) > location.get(0) ? 0D : 180D);
            } else if (point.get(0).equals(location.get(0))) {
                angles.add(point.get(1) > location.get(1) ? 90D : 270D);
            } else {
                double tan =
                        (point.get(1) - location.get(1))
                                / (double) (point.get(0) - location.get(0));
                double ang = Math.toDegrees(Math.atan(tan));
                if (point.get(0) > location.get(0) && point.get(1) > location.get(1)) {
                    angles.add(ang);
                } else if (point.get(0) > location.get(0) && point.get(1) < location.get(1)) {
                    angles.add(ang + 360D);
                } else {
                    angles.add(ang + 180D);
                }
            }
        }
        // 排序
        Collections.sort(angles);
        int L = 0, wsize = 0, max = 0, len = angles.size();
        // 窗口大小wsize, 窗口范围 [L, i]
        OUTER:
        for (int i = 0; i < len; i = (i + 1) % len) {
            // 当窗口右边界来到i时，删除左边已经观察不到的点
            while ((angles.get(i) - angles.get(L) + 360D) % 360D > angle) {
                L++;
                if (L == len) break OUTER;
                wsize--;
            }
            wsize++;
            if (wsize >= len) return wsize + same;
            max = Math.max(max, wsize);
        }
        return max + same;
    }

    @Test
    public void numsSameConsecDiff() {
        int n = 3, k = 7;
        int[] result = numsSameConsecDiff(n, k);
        log.debug("result:{}", result);
    }
    /**
     * 967. 连续差相同的数字
     *
     * <p>返回所有长度为 N 且满足其每两个连续位上的数字之间的差的绝对值为 K 的非负整数。
     *
     * <p>请注意，除了数字 0 本身之外，答案中的每个数字都不能有前导零。例如，01 因为有一个前导零，所以是无效的；但 0 是有效的。
     *
     * <p>你可以按任何顺序返回答案。
     *
     * <p>示例 1：
     *
     * <p>输入：N = 3, K = 7 输出：[181,292,707,818,929] 解释：注意，070 不是一个有效的数字，因为它有前导零。 示例 2：
     *
     * <p>输入：N = 2, K = 1 输出：[10,12,21,23,32,34,43,45,54,56,65,67,76,78,87,89,98]
     *
     * <p>提示：
     *
     * <p>1 <= N <= 9 0 <= K <= 9
     *
     * @param n
     * @param k
     * @return
     */
    public int[] numsSameConsecDiff(int n, int k) {
        Set<Integer> set = new HashSet<>();
        for (int i = 1; i <= 9; i++) {
            set.add(i);
        }

        for (int i = 1; i < n; i++) {
            Set<Integer> set2 = new HashSet<>();
            for (int num : set) {
                int d = num % 10;
                if (d - k >= 0) {
                    set2.add(num * 10 + (d - k));
                }
                if (d + k <= 9) {
                    set2.add(num * 10 + (d + k));
                }
            }

            set = set2;
        }

        if (n == 1) {
            set.add(0);
        }

        int[] result = new int[set.size()];

        int index = 0;
        for (int num : set) {
            result[index++] = num;
        }
        return result;
    }

    /**
     * 826. 安排工作以达到最大收益
     *
     * <p>有一些工作：difficulty[i] 表示第 i 个工作的难度，profit[i] 表示第 i 个工作的收益。
     *
     * <p>现在我们有一些工人。worker[i] 是第 i 个工人的能力，即该工人只能完成难度小于等于 worker[i] 的工作。
     *
     * <p>每一个工人都最多只能安排一个工作，但是一个工作可以完成多次。
     *
     * <p>举个例子，如果 3 个工人都尝试完成一份报酬为 1 的同样工作，那么总收益为 $3。如果一个工人不能完成任何工作，他的收益为 $0 。
     *
     * <p>我们能得到的最大收益是多少？
     *
     * <p>示例：
     *
     * <p>输入: difficulty = [2,4,6,8,10], profit = [10,20,30,40,50], worker = [4,5,6,7] 输出: 100 解释:
     * 工人被分配的工作难度是 [4,4,6,6] ，分别获得 [20,20,30,30] 的收益。
     *
     * <p>提示:
     *
     * <p>1 <= difficulty.length = profit.length <= 10000 1 <= worker.length <= 10000 difficulty[i],
     * profit[i], worker[i] 的范围是 [1, 10^5]
     *
     * @param difficulty
     * @param profit
     * @param worker
     * @return
     */
    public int maxProfitAssignment(int[] difficulty, int[] profit, int[] worker) {
        int len = difficulty.length;
        List<int[]> list = new ArrayList<>();
        for (int i = 0; i < len; i++) {
            list.add(new int[] {difficulty[i], profit[i]});
        }
        list.sort((a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);

        Arrays.sort(worker);
        int index = 0, max = 0, result = 0;
        for (int skill : worker) {
            while (index < len && skill >= list.get(index)[0]) {
                max = Math.max(max, list.get(index)[1]);
                index++;
            }
            result += max;
        }
        return result;
    }

    /**
     * 825. 适龄的朋友
     *
     * <p>人们会互相发送好友请求，现在给定一个包含有他们年龄的数组，ages[i] 表示第 i 个人的年龄。
     *
     * <p>当满足以下任一条件时，A 不能给 B（A、B不为同一人）发送好友请求：
     *
     * <p>age[B] <= 0.5 * age[A] + 7 age[B] > age[A] age[B] > 100 && age[A] < 100 否则，A 可以给 B 发送好友请求。
     *
     * <p>注意如果 A 向 B 发出了请求，不等于 B 也一定会向 A 发出请求。而且，人们不会给自己发送好友请求。
     *
     * <p>求总共会发出多少份好友请求?
     *
     * <p>示例 1：
     *
     * <p>输入：[16,16] 输出：2 解释：二人可以互发好友申请。 示例 2：
     *
     * <p>输入：[16,17,18] 输出：2 解释：好友请求可产生于 17 -> 16, 18 -> 17. 示例 3：
     *
     * <p>输入：[20,30,100,110,120] 输出：3 解释：好友请求可产生于 110 -> 100, 120 -> 110, 120 -> 100.
     *
     * <p>提示：
     *
     * <p>1 <= ages.length <= 20000. 1 <= ages[i] <= 120.
     *
     * @param ages
     * @return
     */
    public int numFriendRequests(int[] ages) {
        int[] counts = new int[121];
        for (int age : ages) {
            counts[age]++;
        }
        int result = 0;
        for (int i = 15; i <= 120; i++) {
            if (counts[i] == 0) {
                continue;
            }
            int left = (i >> 1) + 7 + 1;
            int friend = 0;
            for (int j = left; j <= i; j++) {
                if (counts[j] == 0) {
                    continue;
                }
                friend += counts[j];
                if (j == i) {
                    friend--;
                }
            }
            result += friend * counts[i];
        }

        return result;
    }

    /**
     * 835. 图像重叠
     *
     * <p>给出两个图像 A 和 B ，A 和 B 为大小相同的二维正方形矩阵。（并且为二进制矩阵，只包含0和1）。
     *
     * <p>我们转换其中一个图像，向左，右，上，或下滑动任何数量的单位，并把它放在另一个图像的上面。之后，该转换的重叠是指两个图像都具有 1 的位置的数目。
     *
     * <p>（请注意，转换不包括向任何方向旋转。）
     *
     * <p>最大可能的重叠是什么？
     *
     * <p>示例 1:
     *
     * <p>输入：A = [[1,1,0], [0,1,0], [0,1,0]] B = [[0,0,0], [0,1,1], [0,0,1]] 输出：3 解释: 将 A
     * 向右移动一个单位，然后向下移动一个单位。 注意:
     *
     * <p>1 <= A.length = A[0].length = B.length = B[0].length <= 30 0 <= A[i][j], B[i][j] <= 1
     *
     * @param img1
     * @param img2
     * @return
     */
    public int largestOverlap(int[][] img1, int[][] img2) {
        int N = img1.length;
        // 偏移量矩阵
        int[][] count = new int[2 * N + 1][2 * N + 1];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (img1[i][j] == 0) {
                    continue;
                }
                for (int ii = 0; ii < N; ii++) {
                    for (int jj = 0; jj < N; jj++) {
                        if (img2[ii][jj] == 0) {
                            continue;
                        }
                        count[i - ii + N][j - jj + N]++;
                    }
                }
            }
        }
        int result = 0;
        for (int[] row : count) {
            for (int v : row) {
                result = Math.max(v, result);
            }
        }

        return result;
    }

    @Test
    public void pushDominoes() {
        String dominoes = ".L.R...LR..L..";
        logResult(pushDominoes(dominoes));
    }

    /**
     * 838. 推多米诺
     *
     * <p>一行中有 N 张多米诺骨牌，我们将每张多米诺骨牌垂直竖立。
     *
     * <p>在开始时，我们同时把一些多米诺骨牌向左或向右推。
     *
     * <p>每过一秒，倒向左边的多米诺骨牌会推动其左侧相邻的多米诺骨牌。
     *
     * <p>同样地，倒向右边的多米诺骨牌也会推动竖立在其右侧的相邻多米诺骨牌。
     *
     * <p>如果同时有多米诺骨牌落在一张垂直竖立的多米诺骨牌的两边，由于受力平衡， 该骨牌仍然保持不变。
     *
     * <p>就这个问题而言，我们会认为正在下降的多米诺骨牌不会对其它正在下降或已经下降的多米诺骨牌施加额外的力。
     *
     * <p>给定表示初始状态的字符串 "S" 。如果第 i 张多米诺骨牌被推向左边，则 S[i] = 'L'；如果第 i 张多米诺骨牌被推向右边，则 S[i] = 'R'；如果第 i
     * 张多米诺骨牌没有被推动，则 S[i] = '.'。
     *
     * <p>返回表示最终状态的字符串。
     *
     * <p>示例 1：
     *
     * <p>输入：".L.R...LR..L.." 输出："LL.RR.LLRRLL.." 示例 2：
     *
     * <p>输入："RR.L" 输出："RR.L" 说明：第一张多米诺骨牌没有给第二张施加额外的力。 提示：
     *
     * <p>0 <= N <= 10^5 表示多米诺骨牌状态的字符串只含有 'L'，'R'; 以及 '.';
     *
     * @param dominoes
     * @return
     */
    public String pushDominoes(String dominoes) {
        int len = dominoes.length();
        char[] chars = dominoes.toCharArray();
        int[] nums = new int[len];
        for (int i = 0; i < len; i++) {

            if (chars[i] == 'R') {
                int num = 10000;
                nums[i] += num--;
                while (i + 1 < len && chars[i + 1] == '.') {
                    nums[i + 1] += num--;
                    i++;
                }
            }
        }

        for (int i = len - 1; i >= 0; i--) {
            if (chars[i] == 'L') {
                int num = -10000;
                nums[i] += num++;
                while (i - 1 >= 0 && chars[i - 1] == '.') {
                    nums[i - 1] += num++;
                    i--;
                }
            }
        }
        log.debug("nums : {}", nums);
        char[] result = new char[len];
        for (int i = 0; i < len; i++) {
            if (nums[i] < 0) {
                result[i] = 'L';
            } else if (nums[i] > 0) {
                result[i] = 'R';
            } else {
                result[i] = '.';
            }
        }

        return new String(result);
    }

    /**
     * 846. 一手顺子
     *
     * <p>爱丽丝有一手（hand）由整数数组给定的牌。
     *
     * <p>现在她想把牌重新排列成组，使得每个组的大小都是 W，且由 W 张连续的牌组成。
     *
     * <p>如果她可以完成分组就返回 true，否则返回 false。
     *
     * <p>示例 1：
     *
     * <p>输入：hand = [1,2,3,6,2,3,4,7,8], W = 3 输出：true 解释：爱丽丝的手牌可以被重新排列为 [1,2,3]，[2,3,4]，[6,7,8]。 示例
     * 2：
     *
     * <p>输入：hand = [1,2,3,4,5], W = 4 输出：false 解释：爱丽丝的手牌无法被重新排列成几个大小为 4 的组。
     *
     * <p>提示：
     *
     * <p>1 <= hand.length <= 10000 0 <= hand[i] <= 10^9 1 <= W <= hand.length
     *
     * <p>注意：此题目与 1294
     * 重复：https://leetcode-cn.com/problems/divide-array-in-sets-of-k-consecutive-numbers/
     *
     * @param hand
     * @param W
     * @return
     */
    public boolean isNStraightHand(int[] hand, int W) {
        int len = hand.length;
        if (len % W != 0) {
            return false;
        }
        Map<Integer, Integer> map = new HashMap<>();
        Arrays.sort(hand);
        for (int num : hand) {
            int count = map.getOrDefault(num, 0);
            map.put(num, count + 1);
        }
        for (int num : hand) {
            int count = map.getOrDefault(num, 0);
            if (count == 0) {
                continue;
            }
            map.put(num, count - 1);
            for (int i = 1; i < W; i++) {
                count = map.getOrDefault(num + i, 0);
                if (count == 0) {
                    return false;
                }
                map.put(num + i, count - 1);
            }
        }

        return true;
    }

    /**
     * 851. 喧闹和富有
     *
     * <p>在一组 N 个人（编号为 0, 1, 2, ..., N-1）中，每个人都有不同数目的钱，以及不同程度的安静（quietness）。
     *
     * <p>为了方便起见，我们将编号为 x 的人简称为 "person x "。
     *
     * <p>如果能够肯定 person x 比 person y 更有钱的话，我们会说 richer[i] = [x, y] 。注意 richer 可能只是有效观察的一个子集。
     *
     * <p>另外，如果 person x 的安静程度为 q ，我们会说 quiet[x] = q 。
     *
     * <p>现在，返回答案 answer ，其中 answer[x] = y 的前提是，在所有拥有的钱不少于 person x 的人中，person y 是最安静的人（也就是安静值
     * quiet[y] 最小的人）。
     *
     * <p>示例：
     *
     * <p>输入：richer = [[1,0],[2,1],[3,1],[3,7],[4,3],[5,3],[6,3]], quiet = [3,2,5,4,6,1,7,0]
     * 输出：[5,5,2,5,4,5,6,7] 解释： answer[0] = 5， person 5 比 person 3 有更多的钱，person 3 比 person 1
     * 有更多的钱，person 1 比 person 0 有更多的钱。 唯一较为安静（有较低的安静值 quiet[x]）的人是 person 7， 但是目前还不清楚他是否比 person 0
     * 更有钱。
     *
     * <p>answer[7] = 7， 在所有拥有的钱肯定不少于 person 7 的人中(这可能包括 person 3，4，5，6 以及 7)， 最安静(有较低安静值
     * quiet[x])的人是 person 7。
     *
     * <p>其他的答案也可以用类似的推理来解释。 提示：
     *
     * <p>1 <= quiet.length = N <= 500 0 <= quiet[i] < N，所有 quiet[i] 都不相同。 0 <= richer.length <= N *
     * (N-1) / 2 0 <= richer[i][j] < N richer[i][0] != richer[i][1] richer[i] 都是不同的。 对 richer
     * 的观察在逻辑上是一致的。
     *
     * @param richer
     * @param quiet
     * @return
     */
    public int[] loudAndRich(int[][] richer, int[] quiet) {
        int len = quiet.length;
        int[] result = new int[len];
        Arrays.fill(result, -1);
        richList = new ArrayList<>();
        for (int i = 0; i < len; i++) {
            richList.add(new ArrayList<>());
        }
        quietList = quiet;
        for (int[] num : richer) {
            richList.get(num[1]).add(num[0]);
        }
        for (int i = 0; i < len; i++) {
            loudAndRichDfs(i, result);
        }

        return result;
    }

    List<List<Integer>> richList;
    int[] quietList;

    private int loudAndRichDfs(int node, int[] result) {
        if (result[node] == -1) {
            result[node] = node;
            for (int child : richList.get(node)) {
                int minQuiet = loudAndRichDfs(child, result);
                if (quietList[minQuiet] < quietList[result[node]]) {
                    result[node] = minQuiet;
                }
            }
        }

        return result[node];
    }

    /**
     * 853. 车队
     *
     * <p>N 辆车沿着一条车道驶向位于 target 英里之外的共同目的地。
     *
     * <p>每辆车 i 以恒定的速度 speed[i] （英里/小时），从初始位置 position[i] （英里） 沿车道驶向目的地。
     *
     * <p>一辆车永远不会超过前面的另一辆车，但它可以追上去，并与前车以相同的速度紧接着行驶。
     *
     * <p>此时，我们会忽略这两辆车之间的距离，也就是说，它们被假定处于相同的位置。
     *
     * <p>车队 是一些由行驶在相同位置、具有相同速度的车组成的非空集合。注意，一辆车也可以是一个车队。
     *
     * <p>即便一辆车在目的地才赶上了一个车队，它们仍然会被视作是同一个车队。
     *
     * <p>会有多少车队到达目的地?
     *
     * <p>示例：
     *
     * <p>输入：target = 12, position = [10,8,0,5,3], speed = [2,4,1,1,3] 输出：3 解释： 从 10 和 8
     * 开始的车会组成一个车队，它们在 12 处相遇。 从 0 处开始的车无法追上其它车，所以它自己就是一个车队。 从 5 和 3 开始的车会组成一个车队，它们在 6 处相遇。
     * 请注意，在到达目的地之前没有其它车会遇到这些车队，所以答案是 3。
     *
     * <p>提示：
     *
     * <p>0 <= N <= 10 ^ 4 0 < target <= 10 ^ 6 0 < speed[i] <= 10 ^ 6 0 <= position[i] < target
     * 所有车的初始位置各不相同。
     *
     * @param target
     * @param position
     * @param speed
     * @return
     */
    public int carFleet(int target, int[] position, int[] speed) {
        int len = position.length;
        if (len <= 1) {
            return len;
        }
        Car[] cars = new Car[len];
        for (int i = 0; i < len; i++) {
            cars[i] = new Car(position[i], (double) (target - position[i]) / speed[i]);
        }
        Arrays.sort(cars, Comparator.comparingInt(a -> a.position));
        int count = 0;
        for (int i = len - 2; i >= 0; i--) {
            if (cars[i].time <= cars[i + 1].time) {
                cars[i].time = cars[i + 1].time;
            } else {
                count++;
            }
        }
        count++;
        return count;
    }

    class Car {
        int position;
        double time;

        Car(int p, double t) {
            position = p;
            time = t;
        }
    }

    /**
     * 1619. 删除某些元素后的数组均值
     *
     * <p>给你一个整数数组 arr ，请你删除最小 5% 的数字和最大 5% 的数字后，剩余数字的平均值。
     *
     * <p>与 标准答案 误差在 10-5 的结果都被视为正确结果。
     *
     * <p>示例 1：
     *
     * <p>输入：arr = [1,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,3] 输出：2.00000 解释：删除数组中最大和最小的元素后，所有元素都等于
     * 2，所以平均值为 2 。 示例 2：
     *
     * <p>输入：arr = [6,2,7,5,1,2,0,3,10,2,5,0,5,5,0,8,7,6,8,0] 输出：4.00000 示例 3：
     *
     * <p>输入：arr =
     * [6,0,7,0,7,5,7,8,3,4,0,7,8,1,6,8,1,1,2,4,8,1,9,5,4,3,8,5,10,8,6,6,1,0,6,10,8,2,3,4]
     * 输出：4.77778 示例 4：
     *
     * <p>输入：arr =
     * [9,7,8,7,7,8,4,4,6,8,8,7,6,8,8,9,2,6,0,0,1,10,8,6,3,3,5,1,10,9,0,7,10,0,10,4,1,10,6,9,3,6,0,0,2,7,0,6,7,2,9,7,7,3,0,1,6,1,10,3]
     * 输出：5.27778 示例 5：
     *
     * <p>输入：arr =
     * [4,8,4,10,0,7,1,3,7,8,8,3,4,1,6,2,1,1,8,0,9,8,0,3,9,10,3,10,1,10,7,3,2,1,4,9,10,7,6,4,0,8,5,1,2,1,6,2,5,0,7,10,9,10,3,7,10,5,8,5,7,6,7,6,10,9,5,10,5,5,7,2,10,7,7,8,2,0,1,1]
     * 输出：5.29167
     *
     * <p>提示：
     *
     * <p>20 <= arr.length <= 1000 arr.length 是 20 的 倍数 0 <= arr[i] <= 105 通过次数2,136提交次数2,869
     *
     * @param arr
     * @return
     */
    public double trimMean(int[] arr) {
        int len = arr.length, len1 = len / 20;
        Arrays.sort(arr);
        double sum = 0, count = len - 2 * len1;
        for (int i = len1; i < len - len1; i++) {
            sum += arr[i];
        }
        return sum / count;
    }

    @Test
    public void maxSumTwoNoOverlap() {
        int[] A = {2, 1, 5, 6, 0, 9, 5, 0, 3, 8};
        int l = 4, m = 3;
        logResult(maxSumTwoNoOverlap(A, l, m));
    }

    /**
     * 1031. 两个非重叠子数组的最大和
     *
     * <p>给出非负整数数组 A ，返回两个非重叠（连续）子数组中元素的最大和，子数组的长度分别为 L 和 M。（这里需要澄清的是，长为 L 的子数组可以出现在长为 M 的子数组之前或之后。）
     *
     * <p>从形式上看，返回最大的 V，而 V = (A[i] + A[i+1] + ... + A[i+L-1]) + (A[j] + A[j+1] + ... + A[j+M-1])
     * 并满足下列条件之一：
     *
     * <p>0 <= i < i + L - 1 < j < j + M - 1 < A.length, 或 0 <= j < j + M - 1 < i < i + L - 1 <
     * A.length.
     *
     * <p>示例 1：
     *
     * <p>输入：A = [0,6,5,2,2,5,1,9,4], L = 1, M = 2 输出：20 解释：子数组的一种选择中，[9] 长度为 1，[6,5] 长度为 2。 示例 2：
     *
     * <p>输入：A = [3,8,1,3,2,1,8,9,0], L = 3, M = 2 输出：29 解释：子数组的一种选择中，[3,8,1] 长度为 3，[8,9] 长度为 2。 示例
     * 3：
     *
     * <p>输入：A = [2,1,5,6,0,9,5,0,3,8], L = 4, M = 3 输出：31 解释：子数组的一种选择中，[5,6,0,9] 长度为 4，[0,3,8] 长度为
     * 3。
     *
     * <p>提示：
     *
     * <p>L >= 1 M >= 1 L + M <= A.length <= 1000 0 <= A[i] <= 1000
     *
     * @param A
     * @param L
     * @param M
     * @return
     */
    public int maxSumTwoNoOverlap(int[] A, int L, int M) {
        // 前缀和
        int len = A.length;
        for (int i = 1; i < len; i++) {
            A[i] += A[i - 1];
        }
        // 滑动窗口 同时求两个数组的最大值
        int result = 0;
        int lMax = A[L - 1];
        result = Math.max(result, lMax + A[M + L - 1] - A[L - 1]);
        for (int i = L; i < len - M; i++) {
            lMax = Math.max(lMax, A[i] - A[i - L]);
            result = Math.max(result, lMax + A[i + M] - A[i]);
        }

        int mMax = A[M - 1];
        result = Math.max(result, mMax + A[M + L - 1] - A[M - 1]);
        for (int i = M; i < len - L; i++) {
            mMax = Math.max(mMax, A[i] - A[i - M]);
            result = Math.max(result, mMax + A[i + L] - A[i]);
        }

        return result;
    }

    /**
     * 1034. 边框着色
     *
     * <p>给出一个二维整数网格 grid，网格中的每个值表示该位置处的网格块的颜色。
     *
     * <p>只有当两个网格块的颜色相同，而且在四个方向中任意一个方向上相邻时，它们属于同一连通分量。
     *
     * <p>连通分量的边界是指连通分量中的所有与不在分量中的正方形相邻（四个方向上）的所有正方形，或者在网格的边界上（第一行/列或最后一行/列）的所有正方形。
     *
     * <p>给出位于 (r0, c0) 的网格块和颜色 color，使用指定颜色 color 为所给网格块的连通分量的边界进行着色，并返回最终的网格 grid 。
     *
     * <p>示例 1：
     *
     * <p>输入：grid = [[1,1],[1,2]], r0 = 0, c0 = 0, color = 3 输出：[[3, 3], [3, 2]] 示例 2：
     *
     * <p>输入：grid = [[1,2,2],[2,3,2]], r0 = 0, c0 = 1, color = 3 输出：[[1, 3, 3], [2, 3, 3]] 示例 3：
     *
     * <p>输入：grid = [[1,1,1],[1,1,1],[1,1,1]], r0 = 1, c0 = 1, color = 2 输出：[[2, 2, 2], [2, 1, 2],
     * [2, 2, 2]]
     *
     * <p>提示：
     *
     * <p>1 <= grid.length <= 50 1 <= grid[0].length <= 50 1 <= grid[i][j] <= 1000 0 <= r0 <
     * grid.length 0 <= c0 < grid[0].length 1 <= color <= 1000
     *
     * @param grid
     * @param r0
     * @param c0
     * @param color
     * @return
     */
    public int[][] colorBorder(int[][] grid, int r0, int c0, int color) {
        oldColor = grid[r0][c0];
        newColor = color;
        M = grid.length;
        N = grid[0].length;
        boolean[][] visited = new boolean[M][N];
        visited[r0][c0] = true;
        dfsColorBorder(grid, r0, c0, visited);
        return grid;
    }

    private int oldColor, newColor;

    private void dfsColorBorder(int[][] grid, int row, int col, boolean[][] visited) {
        if (!inArea(row, col, M, N)) {
            return;
        }

        for (int i = 0; i < 4; i++) {
            int rowNum = row + DIR_ROW[i];
            int colNum = col + DIR_COL[i];
            if (inArea(rowNum, colNum, M, N)) {
                if (visited[rowNum][colNum]) {
                    continue;
                }
                if (grid[rowNum][colNum] != oldColor) {
                    grid[row][col] = newColor;
                } else {
                    visited[rowNum][colNum] = true;
                    dfsColorBorder(grid, rowNum, colNum, visited);
                }

            } else {
                grid[row][col] = newColor;
            }
        }
    }

    /**
     * 5547. 等差子数组
     *
     * <p>如果一个数列由至少两个元素组成，且每两个连续元素之间的差值都相同，那么这个序列就是 等差数列 。更正式地，数列 s 是等差数列，只需要满足：对于每个有效的 i ， s[i+1] -
     * s[i] == s[1] - s[0] 都成立。
     *
     * <p>例如，下面这些都是 等差数列 ：
     *
     * <p>1, 3, 5, 7, 9 7, 7, 7, 7 3, -1, -5, -9 下面的数列 不是等差数列 ：
     *
     * <p>1, 1, 2, 5, 7 给你一个由 n 个整数组成的数组 nums，和两个由 m 个整数组成的数组 l 和 r，后两个数组表示 m 组范围查询，其中第 i 个查询对应范围
     * [l[i], r[i]] 。所有数组的下标都是 从 0 开始 的。
     *
     * <p>返回 boolean 元素构成的答案列表 answer 。如果子数组 nums[l[i]], nums[l[i]+1], ... , nums[r[i]] 可以 重新排列 形成
     * 等差数列 ，answer[i] 的值就是 true；否则answer[i] 的值就是 false 。
     *
     * <p>示例 1：
     *
     * <p>输入：nums = [4,6,5,9,3,7], l = [0,0,2], r = [2,3,5] 输出：[true,false,true] 解释： 第 0 个查询，对应子数组
     * [4,6,5] 。可以重新排列为等差数列 [6,5,4] 。 第 1 个查询，对应子数组 [4,6,5,9] 。无法重新排列形成等差数列。 第 2 个查询，对应子数组 [5,9,3,7]
     * 。可以重新排列为等差数列 [3,5,7,9] 。 示例 2：
     *
     * <p>输入：nums = [-12,-9,-3,-12,-6,15,20,-25,-20,-15,-10], l = [0,1,6,4,8,7], r = [4,4,9,7,9,10]
     * 输出：[false,true,false,false,true,true]
     *
     * <p>提示：
     *
     * <p>n == nums.length m == l.length m == r.length 2 <= n <= 500 1 <= m <= 500 0 <= l[i] < r[i]
     * < n -105 <= nums[i] <= 105
     *
     * @param nums
     * @param l
     * @param r
     * @return
     */
    public List<Boolean> checkArithmeticSubarrays(int[] nums, int[] l, int[] r) {
        List<Boolean> result = new ArrayList<>();
        int len = l.length;
        for (int i = 0; i < len; i++) {
            int[] arr = Arrays.copyOfRange(nums, l[i], r[i] + 1);
            log.debug("arr:{}", arr);
            Arrays.sort(arr);
            boolean b = canMakeArithmeticProgression(arr);
            result.add(b);
        }

        return result;
    }

    @Test
    public void checkArithmeticSubarrays() {
        int[] nums = {-12, -9, -3, -12, -6, 15, 20, -25, -20, -15, -10},
                l = {0, 1, 6, 4, 8, 7},
                r = {4, 4, 9, 7, 9, 10};
        logResult(checkArithmeticSubarrays(nums, l, r));
    }

    /**
     * 5548. 最小体力消耗路径
     *
     * <p>你准备参加一场远足活动。给你一个二维 rows x columns 的地图 heights ，其中 heights[row][col] 表示格子 (row, col)
     * 的高度。一开始你在最左上角的格子 (0, 0) ，且你希望去最右下角的格子 (rows-1, columns-1) （注意下标从 0 开始编号）。你每次可以往 上，下，左，右
     * 四个方向之一移动，你想要找到耗费 体力 最小的一条路径。
     *
     * <p>一条路径耗费的 体力值 是路径上相邻格子之间 高度差绝对值 的 最大值 决定的。
     *
     * <p>请你返回从左上角走到右下角的最小 体力消耗值 。
     *
     * <p>示例 1：
     *
     * <p>输入：heights = [[1,2,2],[3,8,2],[5,3,5]] 输出：2 解释：路径 [1,3,5,3,5] 连续格子的差值绝对值最大为 2 。 这条路径比路径
     * [1,2,2,2,5] 更优，因为另一条路劲差值最大值为 3 。 示例 2：
     *
     * <p>输入：heights = [[1,2,3],[3,8,4],[5,3,5]] 输出：1 解释：路径 [1,2,3,4,5] 的相邻格子差值绝对值最大为 1 ，比路径
     * [1,3,5,3,5] 更优。 示例 3：
     *
     * <p>输入：heights = [[1,2,1,1,1],[1,2,1,2,1],[1,2,1,2,1],[1,2,1,2,1],[1,1,1,2,1]] 输出：0
     * 解释：上图所示路径不需要消耗任何体力。
     *
     * <p>提示：
     *
     * <p>rows == heights.length columns == heights[i].length 1 <= rows, columns <= 100 1 <=
     * heights[i][j] <= 106
     *
     * @param heights
     * @return
     */
    public int minimumEffortPath(int[][] heights) {
        // 二分
        grid = heights;
        M = heights.length;
        N = heights[0].length;
        int left = 0, right = 0;
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (i == 0 && j == 0) {
                    continue;
                }
                if (i > 0) {
                    right = Math.max(right, Math.abs(heights[i][j] - heights[i - 1][j]));
                }
                if (j > 0) {
                    right = Math.max(right, Math.abs(heights[i][j] - heights[i][j - 1]));
                }
            }
        }

        while (left < right) {
            int mid = (left + right) >> 1;
            if (dfsEffortPath(0, 0, mid, new boolean[M][N])) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }

    /**
     * 能否以 最大max 到达 终点
     *
     * @param row
     * @param col
     * @param max
     * @param visited
     * @return
     */
    private boolean dfsEffortPath(int row, int col, int max, boolean[][] visited) {
        if (row == M - 1 && col == N - 1) {
            return true;
        }
        visited[row][col] = true;
        for (int i = 0; i < 4; i++) {
            int rowNum = row + DIR_ROW[i], colNum = col + DIR_COL[i];
            if (!inArea(rowNum, colNum, M, N)) {
                continue;
            }
            if (visited[rowNum][colNum]) {
                continue;
            }
            if (Math.abs(grid[rowNum][colNum] - grid[row][col]) > max) {
                continue;
            }
            if (dfsEffortPath(rowNum, colNum, max, visited)) {
                return true;
            }
        }

        return false;
    }

    /**
     * 898. 子数组按位或操作
     *
     * <p>我们有一个非负整数数组 A。
     *
     * <p>对于每个（连续的）子数组 B = [A[i], A[i+1], ..., A[j]] （ i <= j），我们对 B 中的每个元素进行按位或操作，获得结果 A[i] |
     * A[i+1] | ... | A[j]。
     *
     * <p>返回可能结果的数量。 （多次出现的结果在最终答案中仅计算一次。）
     *
     * <p>示例 1：
     *
     * <p>输入：[0] 输出：1 解释： 只有一个可能的结果 0 。 示例 2：
     *
     * <p>输入：[1,1,2] 输出：3 解释： 可能的子数组为 [1]，[1]，[2]，[1, 1]，[1, 2]，[1, 1, 2]。 产生的结果为 1，1，2，1，3，3 。
     * 有三个唯一值，所以答案是 3 。 示例 3：
     *
     * <p>输入：[1,2,4] 输出：6 解释： 可能的结果是 1，2，3，4，6，以及 7 。
     *
     * <p>提示：
     *
     * <p>1 <= A.length <= 50000 0 <= A[i] <= 10^9
     *
     * @param A
     * @return
     */
    public int subarrayBitwiseORs(int[] A) {
        Set<Integer> set = new HashSet<>();
        int len = A.length;
        for (int i = 0; i < len; i++) {
            set.add(A[i]);
            for (int j = i - 1; j >= 0; j--) {
                int tmp = A[j] | A[i];
                if (tmp == A[j]) {

                    break;
                }
                A[j] = tmp;
                set.add(A[j]);
            }
        }
        return set.size();
    }

    /**
     * 904. 水果成篮
     *
     * <p>在一排树中，第 i 棵树产生 tree[i] 型的水果。 你可以从你选择的任何树开始，然后重复执行以下步骤：
     *
     * <p>把这棵树上的水果放进你的篮子里。如果你做不到，就停下来。 移动到当前树右侧的下一棵树。如果右边没有树，就停下来。 请注意，在选择一颗树后，你没有任何选择：你必须执行步骤
     * 1，然后执行步骤 2，然后返回步骤 1，然后执行步骤 2，依此类推，直至停止。
     *
     * <p>你有两个篮子，每个篮子可以携带任何数量的水果，但你希望每个篮子只携带一种类型的水果。
     *
     * <p>用这个程序你能收集的水果树的最大总量是多少？
     *
     * <p>示例 1：
     *
     * <p>输入：[1,2,1] 输出：3 解释：我们可以收集 [1,2,1]。 示例 2：
     *
     * <p>输入：[0,1,2,2] 输出：3 解释：我们可以收集 [1,2,2] 如果我们从第一棵树开始，我们将只能收集到 [0, 1]。 示例 3：
     *
     * <p>输入：[1,2,3,2,2] 输出：4 解释：我们可以收集 [2,3,2,2] 如果我们从第一棵树开始，我们将只能收集到 [1, 2]。 示例 4：
     *
     * <p>输入：[3,3,3,1,2,1,1,2,3,3,4] 输出：5 解释：我们可以收集 [1,2,1,1,2] 如果我们从第一棵树或第八棵树开始，我们将只能收集到 4 棵水果树。
     *
     * <p>提示：
     *
     * <p>1 <= tree.length <= 40000 0 <= tree[i] < tree.length
     *
     * @param tree
     * @return
     */
    public int totalFruit(int[] tree) {
        int max = 0;
        int len = tree.length;
        if (len <= 2) {
            return len;
        }
        int first = tree[0];
        int index = 1;
        while (index < len && tree[index] == first) {
            index++;
        }
        if (index == len) {
            return len;
        }
        int second = tree[index++];
        int start = 0;
        for (; index < tree.length; index++) {
            // 遇到了第3种水果
            if (tree[index] != first && tree[index] != second) {
                max = Math.max(max, index - start);
                first = tree[index - 1];
                second = tree[index];
                start = index - 1;
                // 找到 前一种水果的开始位置
                while (tree[start - 1] == first) {
                    start--;
                }
            }
        }

        return Math.max(max, index - start);
    }

    /**
     * 5539. 按照频率将数组升序排序
     *
     * <p>给你一个整数数组 nums ，请你将数组按照每个值的频率 升序 排序。如果有多个值的频率相同，请你按照数值本身将它们 降序 排序。
     *
     * <p>请你返回排序后的数组。
     *
     * <p>示例 1：
     *
     * <p>输入：nums = [1,1,2,2,2,3] 输出：[3,1,1,2,2,2] 解释：'3' 频率为 1，'1' 频率为 2，'2' 频率为 3 。 示例 2：
     *
     * <p>输入：nums = [2,3,1,3,2] 输出：[1,3,3,2,2] 解释：'2' 和 '3' 频率都为 2 ，所以它们之间按照数值本身降序排序。 示例 3：
     *
     * <p>输入：nums = [-1,1,-6,4,5,-6,1,4,1] 输出：[5,-1,4,4,-6,-6,1,1,1]
     *
     * <p>提示：
     *
     * <p>1 <= nums.length <= 100 -100 <= nums[i] <= 100
     *
     * @param nums
     * @return
     */
    public int[] frequencySort(int[] nums) {
        Map<Integer, Integer> countMap = new HashMap<>();
        int len = nums.length;
        for (int num : nums) {
            int count = countMap.getOrDefault(num, 0);
            countMap.put(num, count + 1);
        }
        List<Integer> list =
                countMap.keySet().stream()
                        .sorted(
                                (a, b) -> {
                                    int count1 = countMap.get(a), count2 = countMap.get(b);

                                    return count1 == count2 ? b - a : count1 - count2;
                                })
                        .collect(Collectors.toList());
        int[] result = new int[len];
        int index = 0;
        for (int num : list) {
            int count = countMap.get(num);
            for (int i = 0; i < count; i++) {
                result[index++] = num;
            }
        }

        return result;
    }

    /**
     * 5540. 两点之间不包含任何点的最宽垂直面积
     *
     * <p>给你 n 个二维平面上的点 points ，其中 points[i] = [xi, yi] ，请你返回两点之间内部不包含任何点的 最宽垂直面积 的宽度。
     *
     * <p>垂直面积 的定义是固定宽度，而 y 轴上无限延伸的一块区域（也就是高度为无穷大）。 最宽垂直面积 为宽度最大的一个垂直面积。
     *
     * <p>请注意，垂直区域 边上 的点 不在 区域内。
     *
     * <p>示例 1：
     *
     * <p>输入：points = [[8,7],[9,9],[7,4],[9,7]] 输出：1 解释：红色区域和蓝色区域都是最优区域。 示例 2：
     *
     * <p>输入：points = [[3,1],[9,0],[1,0],[1,4],[5,3],[8,8]] 输出：3
     *
     * <p>提示：
     *
     * <p>n == points.length 2 <= n <= 105 points[i].length == 2 0 <= xi, yi <= 109
     *
     * @param points
     * @return
     */
    public int maxWidthOfVerticalArea(int[][] points) {

        Arrays.sort(points, Comparator.comparingInt(a -> a[0]));
        int max = 0;
        for (int i = 1; i < points.length; i++) {
            int num = points[i][0] - points[i - 1][0];
            max = Math.max(max, num);
        }

        return max;
    }

    /**
     * 5554. 能否连接形成数组
     *
     * <p>给你一个整数数组 arr ，数组中的每个整数 互不相同 。另有一个由整数数组构成的数组 pieces，其中的整数也 互不相同 。请你以 任意顺序 连接 pieces 中的数组以形成
     * arr 。但是，不允许 对每个数组 pieces[i] 中的整数重新排序。
     *
     * <p>如果可以连接 pieces 中的数组形成 arr ，返回 true ；否则，返回 false 。
     *
     * <p>示例 1：
     *
     * <p>输入：arr = [85], pieces = [[85]] 输出：true 示例 2：
     *
     * <p>输入：arr = [15,88], pieces = [[88],[15]] 输出：true 解释：依次连接 [15] 和 [88] 示例 3：
     *
     * <p>输入：arr = [49,18,16], pieces = [[16,18,49]] 输出：false 解释：即便数字相符，也不能重新排列 pieces[0] 示例 4：
     *
     * <p>输入：arr = [91,4,64,78], pieces = [[78],[4,64],[91]] 输出：true 解释：依次连接 [91]、[4,64] 和 [78] 示例
     * 5：
     *
     * <p>输入：arr = [1,3,5,7], pieces = [[2,4,6,8]] 输出：false
     *
     * <p>提示：
     *
     * <p>1 <= pieces.length <= arr.length <= 100 sum(pieces[i].length) == arr.length 1 <=
     * pieces[i].length <= arr.length 1 <= arr[i], pieces[i][j] <= 100 arr 中的整数 互不相同 pieces 中的整数
     * 互不相同（也就是说，如果将 pieces 扁平化成一维数组，数组中的所有整数互不相同）
     *
     * @param arr
     * @param pieces
     * @return
     */
    public boolean canFormArray(int[] arr, int[][] pieces) {
        Map<Integer, Integer> indexMap = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            indexMap.put(arr[i], i);
        }
        int count = 0;
        for (int[] piece : pieces) {
            int index = indexMap.getOrDefault(piece[0], -1);
            if (index == -1) {
                return false;
            }
            for (int i = 1; i < piece.length; i++) {
                int idx = indexMap.getOrDefault(piece[i], -1);
                if (idx != index + i) {
                    return false;
                }
            }
            count += piece.length;
        }

        return count != arr.length;
    }

    /**
     * 5556. 可以到达的最远建筑
     *
     * <p>给你一个整数数组 heights ，表示建筑物的高度。另有一些砖块 bricks 和梯子 ladders 。
     *
     * <p>你从建筑物 0 开始旅程，不断向后面的建筑物移动，期间可能会用到砖块或梯子。
     *
     * <p>当从建筑物 i 移动到建筑物 i+1（下标 从 0 开始 ）时：
     *
     * <p>如果当前建筑物的高度 大于或等于 下一建筑物的高度，则不需要梯子或砖块 如果当前建筑的高度 小于 下一个建筑的高度，您可以使用 一架梯子 或 (h[i+1] - h[i]) 个砖块
     * 如果以最佳方式使用给定的梯子和砖块，返回你可以到达的最远建筑物的下标（下标 从 0 开始 ）。
     *
     * <p>示例 1：
     *
     * <p>输入：heights = [4,2,7,6,9,14,12], bricks = 5, ladders = 1 输出：4 解释：从建筑物 0 出发，你可以按此方案完成旅程： -
     * 不使用砖块或梯子到达建筑物 1 ，因为 4 >= 2 - 使用 5 个砖块到达建筑物 2 。你必须使用砖块或梯子，因为 2 < 7 - 不使用砖块或梯子到达建筑物 3 ，因为 7 >=
     * 6 - 使用唯一的梯子到达建筑物 4 。你必须使用砖块或梯子，因为 6 < 9 无法越过建筑物 4 ，因为没有更多砖块或梯子。 示例 2：
     *
     * <p>输入：heights = [4,12,2,7,3,18,20,3,19], bricks = 10, ladders = 2 输出：7 示例 3：
     *
     * <p>输入：heights = [14,3,19,3], bricks = 17, ladders = 0 输出：3
     *
     * <p>提示：
     *
     * <p>1 <= heights.length <= 105 1 <= heights[i] <= 106 0 <= bricks <= 109 0 <= ladders <=
     * heights.length
     *
     * @param heights
     * @param bricks
     * @param ladders
     * @return
     */
    public int furthestBuilding(int[] heights, int bricks, int ladders) {
        int left = 0, right = heights.length - 1;
        int result = -1;
        while (left < right) {
            int mid = (left + right) >> 1;
            if (canArrive(heights, mid, bricks, ladders)) {
                result = mid;
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return result;
    }

    private boolean canArrive(int[] heights, int end, int bricks, int ladders) {
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i <= end; i++) {
            int height = heights[i] - heights[i - 1];
            if (height > 0) {
                list.add(height);
            }
        }
        Collections.sort(list);
        for (int height : list) {
            if (bricks >= height) {
                bricks -= height;
            } else if (ladders > 0) {
                ladders--;
            } else {
                return false;
            }
        }

        return true;
    }

    /**
     * 923. 三数之和的多种可能
     *
     * <p>给定一个整数数组 A，以及一个整数 target 作为目标值，返回满足 i < j < k 且 A[i] + A[j] + A[k] == target 的元组 i, j, k
     * 的数量。
     *
     * <p>由于结果会非常大，请返回 结果除以 10^9 + 7 的余数。
     *
     * <p>示例 1：
     *
     * <p>输入：A = [1,1,2,2,3,3,4,4,5,5], target = 8 输出：20 解释： 按值枚举（A[i]，A[j]，A[k]）： (1, 2, 5) 出现 8 次；
     * (1, 3, 4) 出现 8 次； (2, 2, 4) 出现 2 次； (2, 3, 3) 出现 2 次。 示例 2：
     *
     * <p>输入：A = [1,1,2,2,2,2], target = 5 输出：12 解释： A[i] = 1，A[j] = A[k] = 2 出现 12 次： 我们从 [1,1]
     * 中选择一个 1，有 2 种情况， 从 [2,2,2,2] 中选出两个 2，有 6 种情况。
     *
     * <p>提示：
     *
     * <p>3 <= A.length <= 3000 0 <= A[i] <= 100 0 <= target <= 300
     *
     * @param A
     * @param target
     * @return
     */
    public int threeSumMulti(int[] A, int target) {
        int len = A.length;
        Arrays.sort(A);
        int result = 0;
        for (int i = 0; i < len - 2; i++) {
            int num = target - A[i];
            if (num <= 0) {
                break;
            }
            int j = i + 1, k = len - 1;
            while (j < k) {
                int sum = A[j] + A[k];
                if (sum > num) {
                    k--;
                } else if (sum < num) {
                    j++;
                } else if (A[j] != A[k]) {
                    int count1 = 1, count2 = 1;
                    while (j + 1 < k && A[j + 1] == A[j]) {
                        j++;
                        count1++;
                    }
                    while (k - 1 > j && A[k - 1] == A[k]) {
                        k--;
                        count2++;
                    }
                    result += count1 * count2;
                    result %= MOD;
                    j++;
                    k--;
                } else {
                    int count = k - j + 1;
                    result += (count * (count - 1)) >> 1;
                    result %= MOD;
                    break;
                }
            }
        }
        return result;
    }

    @Test
    public void spiralMatrixIII() {
        int R = 5, C = 6, r0 = 1, c0 = 4;
        logResult(spiralMatrixIII(R, C, r0, c0));
    }

    /**
     * 885. 螺旋矩阵 III
     *
     * <p>在 R 行 C 列的矩阵上，我们从 (r0, c0) 面朝东面开始
     *
     * <p>这里，网格的西北角位于第一行第一列，网格的东南角位于最后一行最后一列。
     *
     * <p>现在，我们以顺时针按螺旋状行走，访问此网格中的每个位置。
     *
     * <p>每当我们移动到网格的边界之外时，我们会继续在网格之外行走（但稍后可能会返回到网格边界）。
     *
     * <p>最终，我们到过网格的所有 R * C 个空间。
     *
     * <p>按照访问顺序返回表示网格位置的坐标列表。
     *
     * <p>示例 1：
     *
     * <p>输入：R = 1, C = 4, r0 = 0, c0 = 0 输出：[[0,0],[0,1],[0,2],[0,3]]
     *
     * <p>示例 2：
     *
     * <p>输入：R = 5, C = 6, r0 = 1, c0 = 4
     * 输出：[[1,4],[1,5],[2,5],[2,4],[2,3],[1,3],[0,3],[0,4],[0,5],[3,5],[3,4],[3,3],[3,2],[2,2],[1,2],[0,2],[4,5],[4,4],[4,3],[4,2],[4,1],[3,1],[2,1],[1,1],[0,1],[4,0],[3,0],[2,0],[1,0],[0,0]]
     *
     * <p>提示：
     *
     * <p>1 <= R <= 100 1 <= C <= 100 0 <= r0 < R 0 <= c0 < C
     *
     * @param R
     * @param C
     * @param r0
     * @param c0
     * @return
     */
    public int[][] spiralMatrixIII(int R, int C, int r0, int c0) {
        int size = R * C;
        int[][] result = new int[size][2];

        int[] dr = new int[] {0, 1, 0, -1};
        int[] dc = new int[] {1, 0, -1, 0};

        int index = 0;
        result[index][0] = r0;
        result[index][1] = c0;
        index++;
        if (size == 1) {
            return result;
        }
        // 移动方向
        int flag = 0;
        // 行走长度， 1,1,2,2,3,3,4,4
        int len = 1;
        int r = r0, c = c0;
        while (index < size) {
            for (int i = 0; i < len; i++) {
                r += dr[flag];
                c += dc[flag];
                if (inArea(r, c, R, C)) {
                    result[index][0] = r;
                    result[index][1] = c;
                    index++;
                }
            }
            len += flag % 2;
            flag++;
            flag %= 4;
        }
        return result;
    }

    /**
     * 886. 可能的二分法
     *
     * <p>给定一组 N 人（编号为 1, 2, ..., N）， 我们想把每个人分进任意大小的两组。
     *
     * <p>每个人都可能不喜欢其他人，那么他们不应该属于同一组。
     *
     * <p>形式上，如果 dislikes[i] = [a, b]，表示不允许将编号为 a 和 b 的人归入同一组。
     *
     * <p>当可以用这种方法将所有人分进两组时，返回 true；否则返回 false。
     *
     * <p>示例 1：
     *
     * <p>输入：N = 4, dislikes = [[1,2],[1,3],[2,4]] 输出：true 解释：group1 [1,4], group2 [2,3] 示例 2：
     *
     * <p>输入：N = 3, dislikes = [[1,2],[1,3],[2,3]] 输出：false 示例 3：
     *
     * <p>输入：N = 5, dislikes = [[1,2],[2,3],[3,4],[4,5],[1,5]] 输出：false
     *
     * <p>提示：
     *
     * <p>1 <= N <= 2000 0 <= dislikes.length <= 10000 dislikes[i].length == 2 1 <= dislikes[i][j]
     * <= N dislikes[i][0] < dislikes[i][1] 对于 dislikes[i] == dislikes[j] 不存在 i != j
     *
     * @param N
     * @param dislikes
     * @return
     */
    public boolean possibleBipartition(int N, int[][] dislikes) {
        // 深度优先搜索
        relations = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            relations.add(new ArrayList<>());
        }

        for (int[] d : dislikes) {
            relations.get(d[0]).add(d[1]);
            relations.get(d[1]).add(d[0]);
        }
        colors = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            if (!possibleDfs(i)) {
                return false;
            }
        }

        return true;
    }

    int[] colors;

    List<List<Integer>> relations;

    private static final int UNCOLOR = 0;
    private static final int WHITE = 1;
    private static final int BLACK = 2;

    private boolean possibleDfs(int num) {
        if (colors[num] == UNCOLOR) {
            colors[num] = WHITE;
        }
        List<Integer> relation = relations.get(num);
        int dislike = colors[num] == WHITE ? BLACK : WHITE;
        for (int p : relation) {
            if (colors[p] == UNCOLOR) {
                colors[p] = dislike;
                if (!possibleDfs(p)) {
                    return false;
                }
            } else if (colors[p] != dislike) {
                return false;
            }
        }

        return true;
    }

    /**
     * 973. 最接近原点的 K 个点
     *
     * <p>我们有一个由平面上的点组成的列表 points。需要从中找出 K 个距离原点 (0, 0) 最近的点。
     *
     * <p>（这里，平面上两点之间的距离是欧几里德距离。）
     *
     * <p>你可以按任何顺序返回答案。除了点坐标的顺序之外，答案确保是唯一的。
     *
     * <p>示例 1：
     *
     * <p>输入：points = [[1,3],[-2,2]], K = 1 输出：[[-2,2]] 解释： (1, 3) 和原点之间的距离为 sqrt(10)， (-2, 2)
     * 和原点之间的距离为 sqrt(8)， 由于 sqrt(8) < sqrt(10)，(-2, 2) 离原点更近。 我们只需要距离原点最近的 K = 1 个点，所以答案就是
     * [[-2,2]]。 示例 2：
     *
     * <p>输入：points = [[3,3],[5,-1],[-2,4]], K = 2 输出：[[3,3],[-2,4]] （答案 [[-2,4],[3,3]] 也会被接受。）
     *
     * <p>提示：
     *
     * <p>1 <= K <= points.length <= 10000 -10000 < points[i][0] < 10000 -10000 < points[i][1] <
     * 10000
     *
     * @param points
     * @param K
     * @return
     */
    public int[][] kClosest(int[][] points, int K) {

        // 最大堆
        PriorityQueue<int[]> heap =
                new PriorityQueue<>(
                        (a, b) -> b[0] * b[0] + b[1] * b[1] - a[0] * a[0] - a[1] * a[1]);
        for (int i = 0; i < K; i++) {
            heap.offer(points[i]);
        }
        for (int i = K; i < points.length; i++) {
            int[] max = heap.peek();
            int[] point = points[i];
            int maxD = max[0] * max[0] + max[1] * max[1];
            int d = point[0] * point[0] + point[1] * point[1];
            if (maxD > d) {
                heap.poll();
                heap.offer(point);
            }
        }

        int[][] result = new int[K][];
        int index = 0;
        for (int[] point : heap) {
            result[index++] = point;
        }

        return result;
    }

    /**
     * 915. 分割数组
     *
     * <p>给定一个数组 A，将其划分为两个不相交（没有公共元素）的连续子数组 left 和 right， 使得：
     *
     * <p>left 中的每个元素都小于或等于 right 中的每个元素。 left 和 right 都是非空的。 left 要尽可能小。 在完成这样的分组后返回 left
     * 的长度。可以保证存在这样的划分方法。
     *
     * <p>示例 1：
     *
     * <p>输入：[5,0,3,8,6] 输出：3 解释：left = [5,0,3]，right = [8,6] 示例 2：
     *
     * <p>输入：[1,1,1,0,6,12] 输出：4 解释：left = [1,1,1,0]，right = [6,12]
     *
     * <p>提示：
     *
     * <p>2 <= A.length <= 30000 0 <= A[i] <= 10^6 可以保证至少有一种方法能够按题目所描述的那样对 A 进行划分。
     *
     * @param A
     * @return
     */
    public int partitionDisjoint(int[] A) {
        int len = A.length;
        int[] minNum = new int[len];
        int min = A[len - 1];
        for (int i = len - 1; i > 0; i--) {
            //
            min = Math.min(min, A[i]);
            minNum[i] = min;
        }
        int max = A[0];
        for (int i = 1; i < len; i++) {
            if (max <= minNum[i]) {
                return i;
            }
            max = Math.max(max, A[i]);
        }

        return -1;
    }

    /**
     * 930. 和相同的二元子数组
     *
     * <p>在由若干 0 和 1 组成的数组 A 中，有多少个和为 S 的非空子数组。
     *
     * <p>示例：
     *
     * <p>输入：A = [1,0,1,0,1], S = 2 输出：4 解释： 如下面黑体所示，有 4 个满足题目要求的子数组： [1,0,1,0,1] [1,0,1,0,1]
     * [1,0,1,0,1] [1,0,1,0,1]
     *
     * <p>提示：
     *
     * <p>A.length <= 30000 0 <= S <= A.length A[i] 为 0 或 1
     *
     * @param A
     * @param S
     * @return
     */
    public int numSubarraysWithSum(int[] A, int S) {

        // 前缀和
        int sum = 0;
        Map<Integer, Integer> countMap = new HashMap<>();
        countMap.put(0, 1);
        int result = 0;
        for (int num : A) {
            sum += num;
            int count = countMap.getOrDefault(sum - S, 0);
            result += count;
            int oldCount = countMap.getOrDefault(sum, 0);
            countMap.put(sum, oldCount + 1);
        }
        return result;
    }

    @Test
    public void numSubarraysWithSum() {
        int[] A = {1, 0, 1, 0, 1};
        int S = 2;
        logResult(numSubarraysWithSum(A, S));
    }

    @Test
    public void minFlipsMonoIncr() {
        String s = "010110";
        logResult(minFlipsMonoIncr(s));
    }

    /**
     * 926. 将字符串翻转到单调递增
     *
     * <p>如果一个由 '0' 和 '1' 组成的字符串，是以一些 '0'（可能没有 '0'）后面跟着一些 '1'（也可能没有 '1'）的形式组成的，那么该字符串是单调递增的。
     *
     * <p>我们给出一个由字符 '0' 和 '1' 组成的字符串 S，我们可以将任何 '0' 翻转为 '1' 或者将 '1' 翻转为 '0'。
     *
     * <p>返回使 S 单调递增的最小翻转次数。
     *
     * <p>示例 1：
     *
     * <p>输入："00110" 输出：1 解释：我们翻转最后一位得到 00111. 示例 2：
     *
     * <p>输入："010110" 输出：2 解释：我们翻转得到 011111，或者是 000111。 示例 3：
     *
     * <p>输入："00011000" 输出：2 解释：我们翻转得到 00000000。
     *
     * <p>提示：
     *
     * <p>1 <= S.length <= 20000 S 中只包含字符 '0' 和 '1'
     *
     * @param S
     * @return
     */
    public int minFlipsMonoIncr(String S) {
        int len = S.length();
        // 选择 位置i 左边 都变成0, 右边都变成 1
        // 求最小翻转次数
        int[] counts = new int[len + 1];
        int count = 0;
        for (int i = 0; i < len; i++) {
            char c = S.charAt(i);
            if (c == '1') {
                count++;
            }
            counts[i + 1] = count;
        }
        if (count == 0 || count == len) {
            return 0;
        }
        count = 0;
        int min = counts[len];
        for (int i = len - 1; i >= 0; i--) {
            char c = S.charAt(i);
            if (c == '0') {
                count++;
            }
            min = Math.min(min, counts[i] + count);
        }
        return min;
    }

    /**
     * 327. 区间和的个数
     *
     * <p>给定一个整数数组 nums，返回区间和在 [lower, upper] 之间的个数，包含 lower 和 upper。 区间和 S(i, j) 表示在 nums 中，位置从 i 到
     * j 的元素之和，包含 i 和 j (i ≤ j)。
     *
     * <p>说明: 最直观的算法复杂度是 O(n2) ，请在此基础上优化你的算法。
     *
     * <p>示例:
     *
     * <p>输入: nums = [-2,5,-1], lower = -2, upper = 2, 输出: 3 解释: 3个区间分别是: [0,0], [2,2],
     * [0,2]，它们表示的和分别为: -2, -1, 2。
     *
     * @param nums
     * @param lower
     * @param upper
     * @return
     */
    public int countRangeSum(int[] nums, int lower, int upper) {
        // 前缀和
        long sum = 0;
        int len = nums.length;

        int count = 0;
        long[] sums = new long[len + 1];
        for (int i = 0; i < len; i++) {
            sum += nums[i];
            sums[i + 1] = sum;
        }
        for (int i = 1; i <= len; i++) {
            for (int j = 0; j < i; j++) {
                long num = sums[i] - sums[j];
                if (num >= lower && num <= upper) {
                    count++;
                }
            }
        }

        return count;
    }

    /**
     * 5563. 销售价值减少的颜色球
     *
     * <p>你有一些球的库存 inventory ，里面包含着不同颜色的球。一个顾客想要 任意颜色 总数为 orders 的球。
     *
     * <p>这位顾客有一种特殊的方式衡量球的价值：每个球的价值是目前剩下的 同色球 的数目。比方说还剩下 6 个黄球，那么顾客买第一个黄球的时候该黄球的价值为 6 。这笔交易以后，只剩下 5
     * 个黄球了，所以下一个黄球的价值为 5 （也就是球的价值随着顾客购买同色球是递减的）
     *
     * <p>给你整数数组 inventory ，其中 inventory[i] 表示第 i 种颜色球一开始的数目。同时给你整数 orders ，表示顾客总共想买的球数目。你可以按照 任意顺序
     * 卖球。
     *
     * <p>请你返回卖了 orders 个球以后 最大 总价值之和。由于答案可能会很大，请你返回答案对 109 + 7 取余数 的结果。
     *
     * <p>示例 1：
     *
     * <p>输入：inventory = [2,5], orders = 4 输出：14 解释：卖 1 个第一种颜色的球（价值为 2 )，卖 3 个第二种颜色的球（价值为 5 + 4 +
     * 3）。 最大总和为 2 + 5 + 4 + 3 = 14 。 示例 2：
     *
     * <p>输入：inventory = [3,5], orders = 6 输出：19 解释：卖 2 个第一种颜色的球（价值为 3 + 2），卖 4 个第二种颜色的球（价值为 5 + 4 +
     * 3 + 2）。 最大总和为 3 + 2 + 5 + 4 + 3 + 2 = 19 。 示例 3：
     *
     * <p>输入：inventory = [2,8,4,10,6], orders = 20 输出：110 示例 4：
     *
     * <p>输入：inventory = [1000000000], orders = 1000000000 输出：21 解释：卖 1000000000 次第一种颜色的球，总价值为
     * 500000000500000000 。 500000000500000000 对 109 + 7 取余为 21 。
     *
     * <p>提示：
     *
     * <p>1 <= inventory.length <= 105 1 <= inventory[i] <= 109 1 <= orders <=
     * min(sum(inventory[i]), 109)
     *
     * @param inventory
     * @param orders
     * @return
     */
    public int maxProfit(int[] inventory, int orders) {
        long result = 0L;
        int len = inventory.length;
        // 二分发
        // 对于所有数量 > x 的颜色，其肯定会减小到 x，因此用等差数列求和公式求和即可。
        // 如果执行完第 1 步，仍有剩余的 orders，则这些 orders 一定会以价格 x 卖出。
        int left = 1, right = MOD;
        while (left < right) {
            int mid = (left + right) >> 1;
            if (chkInventory(inventory, orders, mid)) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        for (int num : inventory) {
            if (num > left) {
                result += ((long) (num + left + 1) * (long) (num - left)) >> 1;
                result %= MOD;
                orders -= num - left;
            }
        }

        if (orders > 0) {
            result += left * (long) orders;
            result %= MOD;
        }

        return (int) result;
    }

    private boolean chkInventory(int[] inventory, int orders, int x) {
        int sum = 0;
        for (int num : inventory) {
            sum += Math.max(num - x, 0);
            if (sum > orders) {
                return true;
            }
        }
        return false;
    }

    @Test
    public void maxProfit() {

        int[] inventory = {497978859, 167261111, 483575207, 591815159};
        int orders = 836556809;
        // int[] inventory = {1, 2, 20};
        // int orders = 9;
        logResult(maxProfit(inventory, orders));
    }

    /**
     * 909. 蛇梯棋
     *
     * <p>N x N 的棋盘 board 上，按从 1 到 N*N 的数字给方格编号，编号 从左下角开始，每一行交替方向。
     *
     * <p>例如，一块 6 x 6 大小的棋盘，编号如下：
     *
     * <p>r 行 c 列的棋盘，按前述方法编号，棋盘格中可能存在 “蛇” 或 “梯子”；如果 board[r][c] != -1，那个蛇或梯子的目的地将会是 board[r][c]。
     *
     * <p>玩家从棋盘上的方格 1 （总是在最后一行、第一列）开始出发。
     *
     * <p>每一回合，玩家需要从当前方格 x 开始出发，按下述要求前进：
     *
     * <p>选定目标方格：选择从编号 x+1，x+2，x+3，x+4，x+5，或者 x+6 的方格中选出一个目标方格 s ，目标方格的编号 <= N*N。
     * 该选择模拟了掷骰子的情景，无论棋盘大小如何，你的目的地范围也只能处于区间 [x+1, x+6] 之间。 传送玩家：如果目标方格 S
     * 处存在蛇或梯子，那么玩家会传送到蛇或梯子的目的地。否则，玩家传送到目标方格 S。
     * 注意，玩家在每回合的前进过程中最多只能爬过蛇或梯子一次：就算目的地是另一条蛇或梯子的起点，你也不会继续移动。
     *
     * <p>返回达到方格 N*N 所需的最少移动次数，如果不可能，则返回 -1。
     *
     * <p>示例：
     *
     * <p>输入：[ [-1,-1,-1,-1,-1,-1], [-1,-1,-1,-1,-1,-1], [-1,-1,-1,-1,-1,-1], [-1,35,-1,-1,13,-1],
     * [-1,-1,-1,-1,-1,-1], [-1,15,-1,-1,-1,-1]] 输出：4 解释： 首先，从方格 1 [第 5 行，第 0 列] 开始。 你决定移动到方格
     * 2，并必须爬过梯子移动到到方格 15。 然后你决定移动到方格 17 [第 3 行，第 5 列]，必须爬过蛇到方格 13。 然后你决定移动到方格 14，且必须通过梯子移动到方格 35。
     * 然后你决定移动到方格 36, 游戏结束。 可以证明你需要至少 4 次移动才能到达第 N*N 个方格，所以答案是 4。
     *
     * <p>提示：
     *
     * <p>2 <= board.length = board[0].length <= 20 board[i][j] 介于 1 和 N*N 之间或者等于 -1。 编号为 1
     * 的方格上没有蛇或梯子。 编号为 N*N 的方格上没有蛇或梯子。
     *
     * @param board
     * @return
     */
    public int snakesAndLadders(int[][] board) {
        int N = board.length;
        int start = 1, end = N * N;
        int[] nums = new int[end + 1];
        int index = 1;
        while (index <= end) {
            int rowIdx = (index - 1) / N;
            int row = N - 1 - rowIdx;
            int colIdx = (index - 1) % N;
            int col = (rowIdx & 1) == 0 ? colIdx : N - 1 - colIdx;
            nums[index++] = board[row][col];
        }
        boolean[] visited = new boolean[end + 1];

        // 广度游戏遍历
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);
        int step = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int num = queue.poll();
                if (num == end) {
                    return step;
                }
                for (int next = num + 1; next <= Math.min(num + 6, end); next++) {

                    int target = nums[next] == -1 ? next : nums[next];
                    if (!visited[target]) {
                        visited[target] = true;
                        queue.offer(target);
                    }
                }
            }

            step++;
        }

        return -1;
    }

    @Test
    public void snakesAndLadders() {
        int[][] board = {
            {-1, -1, -1, -1, -1, -1},
            {-1, -1, -1, -1, -1, -1},
            {-1, -1, -1, -1, -1, -1},
            {-1, 35, -1, -1, 13, -1},
            {-1, -1, -1, -1, -1, -1},
            {-1, 15, -1, -1, -1, -1}
        };
        logResult(snakesAndLadders(board));
    }

    /**
     * 1004. 最大连续1的个数 III
     *
     * <p>给定一个由若干 0 和 1 组成的数组 A，我们最多可以将 K 个值从 0 变成 1 。
     *
     * <p>返回仅包含 1 的最长（连续）子数组的长度。
     *
     * <p>示例 1：
     *
     * <p>输入：A = [1,1,1,0,0,0,1,1,1,1,0], K = 2 输出：6 解释： [1,1,1,0,0,1,1,1,1,1,1] 粗体数字从 0 翻转到
     * 1，最长的子数组长度为 6。 示例 2：
     *
     * <p>输入：A = [0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1], K = 3 输出：10 解释：
     * [0,0,1,1,1,1,1,1,1,1,1,1,0,0,0,1,1,1,1] 粗体数字从 0 翻转到 1，最长的子数组长度为 10。
     *
     * <p>提示：
     *
     * <p>1 <= A.length <= 20000 0 <= K <= A.length A[i] 为 0 或 1
     *
     * @param A
     * @param K
     * @return
     */
    public int longestOnes(int[] A, int K) {
        int count = 0, max = 0;
        int left = 0, right = 0;
        // 滑动窗口
        for (; right < A.length; right++) {
            if (A[right] == 0) {
                count++;
            }
            while (count > K) {
                if (A[left++] == 0) {
                    count--;
                }
            }
            max = Math.max(max, right + 1 - left);
        }

        return max;
    }

    /**
     * 1011. 在 D 天内送达包裹的能力
     *
     * <p>传送带上的包裹必须在 D 天内从一个港口运送到另一个港口。
     *
     * <p>传送带上的第 i 个包裹的重量为 weights[i]。每一天，我们都会按给出重量的顺序往传送带上装载包裹。我们装载的重量不会超过船的最大运载重量。
     *
     * <p>返回能在 D 天内将传送带上的所有包裹送达的船的最低运载能力。
     *
     * <p>示例 1：
     *
     * <p>输入：weights = [1,2,3,4,5,6,7,8,9,10], D = 5 输出：15 解释： 船舶最低载重 15 就能够在 5 天内送达所有包裹，如下所示： 第 1
     * 天：1, 2, 3, 4, 5 第 2 天：6, 7 第 3 天：8 第 4 天：9 第 5 天：10
     *
     * <p>请注意，货物必须按照给定的顺序装运，因此使用载重能力为 14 的船舶并将包装分成 (2, 3, 4, 5), (1, 6, 7), (8), (9), (10) 是不允许的。 示例
     * 2：
     *
     * <p>输入：weights = [3,2,2,4,1,4], D = 3 输出：6 解释： 船舶最低载重 6 就能够在 3 天内送达所有包裹，如下所示： 第 1 天：3, 2 第 2
     * 天：2, 4 第 3 天：1, 4 示例 3：
     *
     * <p>输入：weights = [1,2,3,1,1], D = 4 输出：3 解释： 第 1 天：1 第 2 天：2 第 3 天：3 第 4 天：1, 1
     *
     * <p>提示：
     *
     * <p>1 <= D <= weights.length <= 50000 1 <= weights[i] <= 500
     *
     * @param weights
     * @param D
     * @return
     */
    public int shipWithinDays(int[] weights, int D) {
        int left = 0, right = 0;
        for (int weight : weights) {
            right += weight;
            left = Math.max(left, weight);
        }
        while (left < right) {
            int mid = (left + right) >> 1;
            if (canShip(weights, D, mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return right;
    }

    private boolean canShip(int[] weights, int days, int weight) {
        int num = 0;
        for (int w : weights) {
            if (num + w > weight) {
                days--;
                if (days <= 0) {
                    return false;
                }
                num = w;
            } else {
                num += w;
            }
        }
        if (num > 0) {
            days--;
        }

        return days >= 0;
    }

    @Test
    public void shipWithinDays() {
        int[] weights = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int D = 5;
        logResult(shipWithinDays(weights, D));
    }

    /**
     * 986. 区间列表的交集
     *
     * <p>给定两个由一些 闭区间 组成的列表，每个区间列表都是成对不相交的，并且已经排序。
     *
     * <p>返回这两个区间列表的交集。
     *
     * <p>（形式上，闭区间 [a, b]（其中 a <= b）表示实数 x 的集合，而 a <= x <= b。两个闭区间的交集是一组实数，要么为空集，要么为闭区间。例如，[1, 3] 和
     * [2, 4] 的交集为 [2, 3]。）
     *
     * <p>示例：
     *
     * <p>输入：A = [[0,2],[5,10],[13,23],[24,25]], B = [[1,5],[8,12],[15,24],[25,26]]
     * 输出：[[1,2],[5,5],[8,10],[15,23],[24,24],[25,25]]
     *
     * <p>提示：
     *
     * <p>0 <= A.length < 1000 0 <= B.length < 1000 0 <= A[i].start, A[i].end, B[i].start, B[i].end
     * < 10^9
     *
     * @param A
     * @param B
     * @return
     */
    public int[][] intervalIntersection(int[][] A, int[][] B) {
        List<int[]> list = new ArrayList<>();
        // 双指针
        int i = 0, j = 0;
        while (i < A.length && j < B.length) {
            int[] a = A[i], b = B[j];

            int left = Math.max(a[0], b[0]), right = Math.min(a[1], b[1]);
            if (left <= right) {
                list.add(new int[] {left, right});
            }
            if (a[1] < b[1]) {
                i++;
            } else {
                j++;
            }
        }

        return list.toArray(new int[list.size()][]);
    }

    @Test
    public void intervalIntersection() {
        int[][] A = {{0, 2}, {5, 10}, {13, 23}, {24, 25}},
                B = {{1, 5}, {8, 12}, {15, 24}, {25, 26}};
        logResult(intervalIntersection(A, B));
    }

    /**
     * 950. 按递增顺序显示卡牌
     *
     * <p>牌组中的每张卡牌都对应有一个唯一的整数。你可以按你想要的顺序对这套卡片进行排序。
     *
     * <p>最初，这些卡牌在牌组里是正面朝下的（即，未显示状态）。
     *
     * <p>现在，重复执行以下步骤，直到显示所有卡牌为止：
     *
     * <p>从牌组顶部抽一张牌，显示它，然后将其从牌组中移出。 如果牌组中仍有牌，则将下一张处于牌组顶部的牌放在牌组的底部。 如果仍有未显示的牌，那么返回步骤 1。否则，停止行动。
     * 返回能以递增顺序显示卡牌的牌组顺序。
     *
     * <p>答案中的第一张牌被认为处于牌堆顶部。
     *
     * <p>示例：
     *
     * <p>输入：[17,13,11,2,3,5,7] 输出：[2,13,3,11,5,17,7] 解释： 我们得到的牌组顺序为
     * [17,13,11,2,3,5,7]（这个顺序不重要），然后将其重新排序。 重新排序后，牌组以 [2,13,3,11,5,17,7] 开始，其中 2 位于牌组的顶部。 我们显示
     * 2，然后将 13 移到底部。牌组现在是 [3,11,5,17,7,13]。 我们显示 3，并将 11 移到底部。牌组现在是 [5,17,7,13,11]。 我们显示 5，然后将 17
     * 移到底部。牌组现在是 [7,13,11,17]。 我们显示 7，并将 13 移到底部。牌组现在是 [11,17,13]。 我们显示 11，然后将 17 移到底部。牌组现在是
     * [13,17]。 我们展示 13，然后将 17 移到底部。牌组现在是 [17]。 我们显示 17。 由于所有卡片都是按递增顺序排列显示的，所以答案是正确的。
     *
     * <p>提示：
     *
     * <p>1 <= A.length <= 1000 1 <= A[i] <= 10^6 对于所有的 i != j，A[i] != A[j]
     *
     * @param deck
     * @return
     */
    public int[] deckRevealedIncreasing(int[] deck) {
        int len = deck.length;
        Arrays.sort(deck);
        // 逆向思维, 利用队列重组
        Queue<Integer> queue = new LinkedList<>();
        for (int i = len - 1; i >= 0; i--) {
            queue.offer(deck[i]);
            if (i == 0) {
                break;
            }
            queue.offer(queue.poll());
        }
        for (int i = len - 1; i >= 0; i--) {
            deck[i] = queue.poll();
        }

        return deck;
    }

    /**
     * 962. 最大宽度坡
     *
     * <p>给定一个整数数组 A，坡是元组 (i, j)，其中 i < j 且 A[i] <= A[j]。这样的坡的宽度为 j - i。
     *
     * <p>找出 A 中的坡的最大宽度，如果不存在，返回 0 。
     *
     * <p>示例 1：
     *
     * <p>输入：[6,0,8,2,1,5] 输出：4 解释： 最大宽度的坡为 (i, j) = (1, 5): A[1] = 0 且 A[5] = 5. 示例 2：
     *
     * <p>输入：[9,8,1,0,1,9,4,0,4,1] 输出：7 解释： 最大宽度的坡为 (i, j) = (2, 9): A[2] = 1 且 A[9] = 1.
     *
     * <p>提示：
     *
     * <p>2 <= A.length <= 50000 0 <= A[i] <= 50000
     *
     * @param A
     * @return
     */
    public int maxWidthRamp(int[] A) {
        int max = 0;
        int len = A.length;
        /*int[][] nums = new int[len][2];
        for (int i = 0; i < len; i++) {
            nums[i][0] = A[i];
            nums[i][1] = i;
        }
        Arrays.sort(nums, (a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);
        int minIndex = nums[0][1];
        for (int i = 1; i < len; i++) {
            if (nums[i][1] > minIndex) {
                max = Math.max(max, nums[i][1] - minIndex);
            } else {
                minIndex = nums[i][1];
            }
        }   */
        Deque<Integer> stack = new LinkedList<>();
        for (int i = 0; i < len; i++) {
            if (stack.isEmpty() || A[stack.peek()] > A[i]) {
                stack.push(i);
            }
        }
        for (int i = len - 1; i >= 1; i--) {
            while (!stack.isEmpty() && A[stack.peek()] <= A[i]) {
                max = Math.max(max, i - stack.pop());
            }
        }
        return max;
    }

    @Test
    public void maxWidthRamp() {
        int[] nums = {9, 8, 1, 0, 1, 9, 4, 0, 4, 1};
        logResult(maxWidthRamp(nums));
    }

    /**
     * 969. 煎饼排序
     *
     * <p>给定数组 A，我们可以对其进行煎饼翻转：我们选择一些正整数 k <= A.length，然后反转 A 的前 k
     * 个元素的顺序。我们要执行零次或多次煎饼翻转（按顺序一次接一次地进行）以完成对数组 A 的排序。
     *
     * <p>返回能使 A 排序的煎饼翻转操作所对应的 k 值序列。任何将数组排序且翻转次数在 10 * A.length 范围内的有效答案都将被判断为正确。
     *
     * <p>示例 1：
     *
     * <p>输入：[3,2,4,1] 输出：[4,2,4,3] 解释： 我们执行 4 次煎饼翻转，k 值分别为 4，2，4，和 3。 初始状态 A = [3, 2, 4, 1] 第一次翻转后
     * (k=4): A = [1, 4, 2, 3] 第二次翻转后 (k=2): A = [4, 1, 2, 3] 第三次翻转后 (k=4): A = [3, 2, 1, 4] 第四次翻转后
     * (k=3): A = [1, 2, 3, 4]，此时已完成排序。 示例 2：
     *
     * <p>输入：[1,2,3] 输出：[] 解释： 输入已经排序，因此不需要翻转任何内容。 请注意，其他可能的答案，如[3，3]，也将被接受。
     *
     * <p>提示：
     *
     * <p>1 <= A.length <= 100 A[i] 是 [1, 2, ..., A.length] 的排列
     *
     * @param arr
     * @return
     */
    public List<Integer> pancakeSort(int[] arr) {
        List<Integer> list = new ArrayList<>();
        int len = arr.length;

        Integer[] indexs = new Integer[len];
        for (int i = 0; i < len; i++) {
            indexs[i] = i;
        }
        Arrays.sort(indexs, (a, b) -> arr[b] - arr[a]);
        for (int index : indexs) {
            for (int num : list) {
                if (index < num) {
                    index = num - 1 - index;
                }
            }
            if (index + 1 == len) {
                len--;
                continue;
            }
            if (index > 0) {
                list.add(index + 1);
            }
            if (len > 1) {
                list.add(len--);
            }
        }

        return list;
    }

    @Test
    public void pancakeSort() {
        int[] arr = {1, 2, 4, 3};
        logResult(pancakeSort(arr));
    }

    /**
     * 5550. 拆炸弹
     *
     * <p>你有一个炸弹需要拆除，时间紧迫！你的情报员会给你一个长度为 n 的 循环 数组 code 以及一个密钥 k 。
     *
     * <p>为了获得正确的密码，你需要替换掉每一个数字。所有数字会 同时 被替换。
     *
     * <p>如果 k > 0 ，将第 i 个数字用 接下来 k 个数字之和替换。 如果 k < 0 ，将第 i 个数字用 之前 k 个数字之和替换。 如果 k == 0 ，将第 i 个数字用
     * 0 替换。 由于 code 是循环的， code[n-1] 下一个元素是 code[0] ，且 code[0] 前一个元素是 code[n-1] 。
     *
     * <p>给你 循环 数组 code 和整数密钥 k ，请你返回解密后的结果来拆除炸弹！
     *
     * <p>示例 1：
     *
     * <p>输入：code = [5,7,1,4], k = 3 输出：[12,10,16,13] 解释：每个数字都被接下来 3 个数字之和替换。解密后的密码为 [7+1+4, 1+4+5,
     * 4+5+7, 5+7+1]。注意到数组是循环连接的。 示例 2：
     *
     * <p>输入：code = [1,2,3,4], k = 0 输出：[0,0,0,0] 解释：当 k 为 0 时，所有数字都被 0 替换。 示例 3：
     *
     * <p>输入：code = [2,4,9,3], k = -2 输出：[12,5,6,13] 解释：解密后的密码为 [3+9, 2+3, 4+2, 9+4] 。注意到数组是循环连接的。如果
     * k 是负数，那么和为 之前 的数字。
     *
     * <p>提示：
     *
     * <p>n == code.length 1 <= n <= 100 1 <= code[i] <= 100 -(n - 1) <= k <= n - 1
     *
     * @param code
     * @param k
     * @return
     */
    public int[] decrypt(int[] code, int k) {
        int len = code.length;
        int[] result = new int[len];
        if (k == 0) {
            return result;
        }
        int num = 0;
        if (k > 0) {
            //
            for (int i = 0; i < k; i++) {
                num += code[i];
            }
            int next = k - 1;
            for (int i = 0; i < len; i++) {
                num -= code[i];
                next++;
                next %= len;
                num += code[next];
                result[i] = num;
            }
        } else {
            for (int i = len + k; i < len; i++) {
                num += code[i];
            }
            int last = len + k;
            for (int i = 0; i < len; i++) {
                result[i] = num;
                num += code[i];
                num -= code[last];
                last++;
                last %= len;
            }
        }

        return result;
    }

    @Test
    public void decrypt() {
        int[] code = {2, 4, 9, 3};
        int k = -2;
        log.debug("nums:{}", decrypt(code, k));
    }

    /**
     * 5552. 到家的最少跳跃次数
     *
     * <p>有一只跳蚤的家在数轴上的位置 x 处。请你帮助它从位置 0 出发，到达它的家。
     *
     * <p>跳蚤跳跃的规则如下：
     *
     * <p>它可以 往前 跳恰好 a 个位置（即往右跳）。 它可以 往后 跳恰好 b 个位置（即往左跳）。 它不能 连续 往后跳 2 次。 它不能跳到任何 forbidden 数组中的位置。
     * 跳蚤可以往前跳 超过 它的家的位置，但是它 不能跳到负整数 的位置。
     *
     * <p>给你一个整数数组 forbidden ，其中 forbidden[i] 是跳蚤不能跳到的位置，同时给你整数 a， b 和 x ，请你返回跳蚤到家的最少跳跃次数。如果没有恰好到达 x
     * 的可行方案，请你返回 -1 。
     *
     * <p>示例 1：
     *
     * <p>输入：forbidden = [14,4,18,1,15], a = 3, b = 15, x = 9 输出：3 解释：往前跳 3 次（0 -> 3 -> 6 ->
     * 9），跳蚤就到家了。 示例 2：
     *
     * <p>输入：forbidden = [8,3,16,6,12,20], a = 15, b = 13, x = 11 输出：-1 示例 3：
     *
     * <p>输入：forbidden = [1,6,2,14,5,17,4], a = 16, b = 9, x = 7 输出：2 解释：往前跳一次（0 -> 16），然后往回跳一次（16
     * -> 7），跳蚤就到家了。
     *
     * <p>提示：
     *
     * <p>1 <= forbidden.length <= 1000 1 <= a, b, forbidden[i] <= 2000 0 <= x <= 2000 forbidden
     * 中所有位置互不相同。 位置 x 不在 forbidden 中。
     *
     * @param forbidden
     * @param a
     * @param b
     * @param x
     * @return
     */
    public int minimumJumps(int[] forbidden, int a, int b, int x) {

        boolean[] visited = new boolean[4004];
        for (int f : forbidden) {
            visited[f] = true;
        }
        int extra = 0;
        if (b > a) {
            if (b % a != 0) {
                extra = a - b % a;
            }
        } else {
            extra = a - b;
        }
        int result = -1;
        if (extra == 0) {
            if (x % a != 0) {
                return -1;
            }

            for (int i = 0; i <= x; i += a) {
                if (visited[i]) {
                    return -1;
                }
                result++;
            }
        } else {

            int step = 0;
            // 广度优先遍历
            Queue<JumpNode> queue = new LinkedList<>();
            queue.offer(new JumpNode(0, true));
            visited[0] = true;
            while (!queue.isEmpty()) {
                int size = queue.size();
                step++;
                for (int i = 0; i < size; i++) {
                    JumpNode node = queue.poll();
                    int rightNext = node.val + a;
                    if (rightNext == x) {
                        return step;
                    } else if (rightNext < 4004 && !visited[rightNext]) {
                        visited[rightNext] = true;
                        queue.offer(new JumpNode(node.val + a, true));
                    }
                    int leftNext = node.val - b;
                    if (leftNext == x) {
                        return step;
                    } else if (leftNext >= 0 && !visited[leftNext] && node.right) {
                        // visited[leftNext] = true;
                        queue.offer(new JumpNode(node.val - b, false));
                    }
                }
            }
        }

        return result;
    }

    static class JumpNode {
        int val;
        boolean right;

        JumpNode(int val, boolean right) {
            this.val = val;
            this.right = right;
        }
    }

    @Test
    public void minimumJumps() {

        int[] forbidden = {
            162, 118, 178, 152, 167, 100, 40, 74, 199, 186, 26, 73, 200, 127, 30, 124, 193, 84, 184,
            36, 103, 149, 153, 9, 54, 154, 133, 95, 45, 198, 79, 157, 64, 122, 59, 71, 48, 177, 82,
            35, 14, 176, 16, 108, 111, 6, 168, 31, 134, 164, 136, 72, 98
        };
        int a = 29, b = 98, x = 80;
        logResult(minimumJumps(forbidden, a, b, x));
    }

    /**
     * 5602. 将 x 减到 0 的最小操作数
     *
     * <p>给你一个整数数组 nums 和一个整数 x 。每一次操作时，你应当移除数组 nums 最左边或最右边的元素，然后从 x 中减去该元素的值。请注意，需要 修改
     * 数组以供接下来的操作使用。
     *
     * <p>如果可以将 x 恰好 减到 0 ，返回 最小操作数 ；否则，返回 -1 。
     *
     * <p>示例 1：
     *
     * <p>输入：nums = [1,1,4,2,3], x = 5 输出：2 解释：最佳解决方案是移除后两个元素，将 x 减到 0 。 示例 2：
     *
     * <p>输入：nums = [5,6,7,8,9], x = 4 输出：-1 示例 3：
     *
     * <p>输入：nums = [3,2,20,1,1,3], x = 10 输出：5 解释：最佳解决方案是移除后三个元素和前两个元素（总共 5 次操作），将 x 减到 0 。
     *
     * <p>提示：
     *
     * <p>1 <= nums.length <= 105 1 <= nums[i] <= 104 1 <= x <= 109
     *
     * @param nums
     * @param x
     * @return
     */
    public int minOperations(int[] nums, int x) {
        int len = nums.length;
        // 换个思路, 求  连续元素 = sum - x 即可
        int sum = Arrays.stream(nums).sum();
        int target = sum - x;
        if (target < 0) {
            return -1;
        }
        if (target == 0) {
            return len;
        }
        Map<Integer, Integer> indexMap = new HashMap<>();
        sum = 0;
        indexMap.put(0, -1);
        int max = 0;
        for (int i = 0; i < len; i++) {
            sum += nums[i];
            if (sum >= target) {
                int last = indexMap.getOrDefault(sum - target, -2);
                if (last != -2) {
                    max = Math.max(max, i - last);
                }
            }
            indexMap.put(sum, i);
        }

        return max == 0 ? -1 : len - max;
    }

    @Test
    public void minOperations3() {
        int[] nums = {3, 2, 20, 1, 1, 3};
        int x = 10;
        logResult(minOperations(nums, x));
    }

    /**
     * 932. 漂亮数组
     *
     * <p>对于某些固定的 N，如果数组 A 是整数 1, 2, ..., N 组成的排列，使得：
     *
     * <p>对于每个 i < j，都不存在 k 满足 i < k < j 使得 A[k] * 2 = A[i] + A[j]。
     *
     * <p>那么数组 A 是漂亮数组。
     *
     * <p>给定 N，返回任意漂亮数组 A（保证存在一个）。
     *
     * <p>示例 1：
     *
     * <p>输入：4 输出：[2,1,4,3] 示例 2：
     *
     * <p>输入：5 输出：[3,1,2,5,4]
     *
     * <p>提示：
     *
     * <p>1 <= N <= 1000
     *
     * @param N
     * @return
     */
    public int[] beautifulArray(int N) {
        // A是一个漂亮数组，如果对A中所有元素添加一个常数，那么Ａ还是一个漂亮数组。
        // A是一个漂亮数组，如果对A中所有元素乘以一个常数，那么A还是一个漂亮数组。
        // A是一个漂亮数组，如果删除一些A中一些元素，那么A还是一个漂亮数组。
        // A是一个奇数构成的漂亮数组，B是一个偶数构成的漂亮数组，那么A+B也是一个漂亮数组
        arrayMap = new HashMap<>();
        arrayMap.put(1, new int[] {1});
        arrayMap.put(2, new int[] {1, 2});
        arrayMap.put(3, new int[] {1, 3, 2});
        arrayMap.put(4, new int[] {1, 3, 2, 4});

        return getBeautifulArray(N);
    }

    private int[] getBeautifulArray(int n) {
        if (arrayMap.containsKey(n)) {
            return arrayMap.get(n);
        }
        int[] nums = new int[n];
        int idx = 0;
        int half = (n + 1) >> 1;
        for (int num : getBeautifulArray(half)) {
            nums[idx++] = 2 * num - 1;
        }
        for (int num : getBeautifulArray(n - half)) {
            nums[idx++] = 2 * num;
        }
        arrayMap.put(n, nums);
        return nums;
    }

    Map<Integer, int[]> arrayMap;

    @Test
    public void beautifulArray() {
        int n = 100;
        int[] nums = beautifulArray(n);
        log.debug("nums:{}", nums);
    }

    /**
     * 947. 移除最多的同行或同列石头
     *
     * <p>我们将石头放置在二维平面中的一些整数坐标点上。每个坐标点上最多只能有一块石头。
     *
     * <p>每次 move 操作都会移除一块所在行或者列上有其他石头存在的石头。
     *
     * <p>请你设计一个算法，计算最多能执行多少次 move 操作？
     *
     * <p>示例 1：
     *
     * <p>输入：stones = [[0,0],[0,1],[1,0],[1,2],[2,1],[2,2]] 输出：5 示例 2：
     *
     * <p>输入：stones = [[0,0],[0,2],[1,1],[2,0],[2,2]] 输出：3 示例 3：
     *
     * <p>输入：stones = [[0,0]] 输出：0
     *
     * <p>提示：
     *
     * <p>1 <= stones.length <= 1000 0 <= stones[i][j] < 10000
     *
     * @param stones
     * @return
     */
    public int removeStones(int[][] stones) {
        // 每次移除一块石头, 所在行或者列上有其他石头存在的石头
        int n = stones.length;
        // 同行或者同列的合并一起，每个组最后只能剩一个。返回（N - group数）即可
        StoneDsu dsu = new StoneDsu(20000);
        for (int[] stone : stones) {
            dsu.union(stone[0], stone[1] + 10000);
        }
        Set<Integer> set = new HashSet<>();
        for (int[] stone : stones) {
            set.add(dsu.findParent(stone[0]));
        }

        return n - set.size();
    }

    static class StoneDsu {

        private int[] parent;

        StoneDsu(int n) {
            this.parent = new int[n];
            for (int i = 0; i < n; ++i) {
                parent[i] = i;
            }
        }

        int findParent(int num) {
            if (num != parent[num]) {
                parent[num] = findParent(parent[num]);
            }

            return parent[num];
        }

        void union(int x, int y) {
            parent[findParent(x)] = findParent(y);
        }
    }

    /**
     * 934. 最短的桥
     *
     * <p>在给定的二维二进制数组 A 中，存在两座岛。（岛是由四面相连的 1 形成的一个最大组。）
     *
     * <p>现在，我们可以将 0 变为 1，以使两座岛连接起来，变成一座岛。
     *
     * <p>返回必须翻转的 0 的最小数目。（可以保证答案至少是 1。）
     *
     * <p>示例 1：
     *
     * <p>输入：[[0,1],[1,0]] 输出：1 示例 2：
     *
     * <p>输入：[[0,1,0],[0,0,0],[0,0,1]] 输出：2 示例 3：
     *
     * <p>输入：[[1,1,1,1,1],[1,0,0,0,1],[1,0,1,0,1],[1,0,0,0,1],[1,1,1,1,1]] 输出：1
     *
     * <p>提示：
     *
     * <p>1 <= A.length = A[0].length <= 100 A[i][j] == 0 或 A[i][j] == 1
     *
     * @param A
     * @return
     */
    public int shortestBridge(int[][] A) {

        int rows = A.length, cols = A[0].length;
        int color = 2;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (A[i][j] == 1) {
                    findIsland(A, i, j, color++);
                }
            }
        }
        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (A[i][j] != 2) {
                    continue;
                }
                for (int k = 0; k < 4; k++) {
                    int rowIdx = i + DIR_ROW[k], colIdx = j + DIR_COL[k];
                    if (inArea(rowIdx, colIdx, rows, cols) && A[rowIdx][colIdx] == 0) {
                        queue.offer(new int[] {rowIdx, colIdx});
                    }
                }
            }
        }
        int len = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            len++;
            for (int i = 0; i < size; i++) {
                int[] p = queue.poll();
                int row = p[0], col = p[1];
                if (A[row][col] != 0) {
                    continue;
                }
                A[row][col] = 2;
                for (int j = 0; j < 4; j++) {
                    int rowIdx = row + DIR_ROW[j], colIdx = col + DIR_COL[j];
                    if (inArea(rowIdx, colIdx, rows, cols)) {
                        if (A[rowIdx][colIdx] == 0) {
                            queue.offer(new int[] {rowIdx, colIdx});
                        } else if (A[rowIdx][colIdx] == 3) {
                            return len;
                        }
                    }
                }
            }
        }

        return -1;
    }

    private void findIsland(int[][] A, int row, int col, int color) {
        if (!inArea(row, col, A.length, A[0].length)) {
            return;
        }
        if (A[row][col] == 0 || A[row][col] == color) {
            return;
        }
        A[row][col] = color;

        for (int i = 0; i < 4; i++) {
            int rowIdx = row + DIR_ROW[i], colIdx = col + DIR_COL[i];
            findIsland(A, rowIdx, colIdx, color);
        }
    }

    @Test
    public void shortestBridge() {
        int[][] A = {{0, 1}, {1, 0}};
        logResult(shortestBridge(A));
    }

    /**
     * 954. 二倍数对数组
     *
     * <p>给定一个长度为偶数的整数数组 A，只有对 A 进行重组后可以满足 “对于每个 0 <= i < len(A) / 2，都有 A[2 * i + 1] = 2 * A[2 * i]”
     * 时，返回 true；否则，返回 false。
     *
     * <p>示例 1：
     *
     * <p>输入：[3,1,3,6] 输出：false 示例 2：
     *
     * <p>输入：[2,1,2,6] 输出：false 示例 3：
     *
     * <p>输入：[4,-2,2,-4] 输出：true 解释：我们可以用 [-2,-4] 和 [2,4] 这两组组成 [-2,-4,2,4] 或是 [2,4,-2,-4] 示例 4：
     *
     * <p>输入：[1,2,4,16,8,4] 输出：false
     *
     * <p>提示：
     *
     * <p>0 <= A.length <= 30000 A.length 为偶数 -100000 <= A[i] <= 100000
     *
     * @param A
     * @return
     */
    public boolean canReorderDoubled(int[] A) {
        Map<Integer, Integer> countMap = new HashMap<>();
        for (int num : A) {
            int count = countMap.getOrDefault(num, 0);
            countMap.put(num, count + 1);
        }
        Integer[] nums = new Integer[A.length];
        for (int i = 0; i < A.length; i++) {
            nums[i] = A[i];
        }
        Arrays.sort(nums, Comparator.comparingInt(Math::abs));
        for (int num : nums) {
            int count = countMap.getOrDefault(num, 0);
            if (count == 0) {
                continue;
            }
            countMap.put(num, count - 1);
            int count2 = countMap.getOrDefault(2 * num, 0);
            if (count2 == 0) {
                return false;
            }
            countMap.put(2 * num, count2 - 1);
        }
        return true;
    }

    /**
     * 1040. 移动石子直到连续 II
     *
     * <p>在一个长度无限的数轴上，第 i 颗石子的位置为 stones[i]。如果一颗石子的位置最小/最大，那么该石子被称作端点石子。
     *
     * <p>每个回合，你可以将一颗端点石子拿起并移动到一个未占用的位置，使得该石子不再是一颗端点石子。
     *
     * <p>值得注意的是，如果石子像 stones = [1,2,5] 这样，你将无法移动位于位置 5 的端点石子，因为无论将它移动到任何位置（例如 0 或 3），该石子都仍然会是端点石子。
     *
     * <p>当你无法进行任何移动时，即，这些石子的位置连续时，游戏结束。
     *
     * <p>要使游戏结束，你可以执行的最小和最大移动次数分别是多少？ 以长度为 2 的数组形式返回答案：answer = [minimum_moves, maximum_moves] 。
     *
     * <p>示例 1：
     *
     * <p>输入：[7,4,9] 输出：[1,2] 解释： 我们可以移动一次，4 -> 8，游戏结束。 或者，我们可以移动两次 9 -> 5，4 -> 6，游戏结束。 示例 2：
     *
     * <p>输入：[6,5,4,3,10] 输出：[2,3] 解释： 我们可以移动 3 -> 8，接着是 10 -> 7，游戏结束。 或者，我们可以移动 3 -> 7, 4 -> 8, 5
     * -> 9，游戏结束。 注意，我们无法进行 10 -> 2 这样的移动来结束游戏，因为这是不合要求的移动。 示例 3：
     *
     * <p>输入：[100,101,104,102,103] 输出：[0,0]
     *
     * <p>提示：
     *
     * <p>3 <= stones.length <= 10^4 1 <= stones[i] <= 10^9 stones[i] 的值各不相同。
     *
     * @param stones
     * @return
     */
    public int[] numMovesStonesII(int[] stones) {
        int n = stones.length;
        Arrays.sort(stones);
        // max ： stones[0] 到 stones[n - 2] 的空置数（stones[n - 2] - stones[0] + 1） - n + 1
        // stones[1] 到 stones[n - 1] 的空置数（stones[n - 1] - stones[1] + 1） - n + 1
        int max = Math.max(stones[n - 1] - stones[1] - n + 2, stones[n - 2] - stones[0] - n + 2);

        int min = n;
        // 双指针
        int i = 0, j = 0;
        for (; i < n; i++) {
            // i 和j 之间可以容纳n个数
            while (j + 1 < n && stones[j + 1] - stones[i] + 1 <= n) {
                j++;
            }
            // 当前窗口中的元素个数
            int count = j - i + 1;
            if (count == n - 1 && stones[j] - stones[i] + 1 == n - 1) {
                min = Math.min(min, 2);
            } else {
                min = Math.min(min, n - count);
            }
        }

        return new int[] {min, max};
    }

    /**
     * 5605. 检查两个字符串数组是否相等
     *
     * <p>word1 和 word2 。如果两个数组表示的字符串相同，返回 true ；否则，返回 false 。
     *
     * <p>数组表示的字符串 是由数组中的所有元素 按顺序 连接形成的字符串。
     *
     * <p>示例 1：
     *
     * <p>输入：word1 = ["ab", "c"], word2 = ["a", "bc"] 输出：true 解释： word1 表示的字符串为 "ab" + "c" -> "abc"
     * word2 表示的字符串为 "a" + "bc" -> "abc" 两个字符串相同，返回 true 示例 2：
     *
     * <p>输入：word1 = ["a", "cb"], word2 = ["ab", "c"] 输出：false 示例 3：
     *
     * <p>输入：word1 = ["abc", "d", "defg"], word2 = ["abcddefg"] 输出：true
     *
     * <p>提示：
     *
     * <p>1 <= word1.length, word2.length <= 103 1 <= word1[i].length, word2[i].length <= 103 1 <=
     * sum(word1[i].length), sum(word2[i].length) <= 103 word1[i] 和 word2[i] 由小写字母组成
     *
     * @param word1
     * @param word2
     * @return
     */
    public boolean arrayStringsAreEqual(String[] word1, String[] word2) {
        StringBuilder sb = new StringBuilder();
        for (String word : word1) {
            sb.append(word);
        }
        StringBuilder sb2 = new StringBuilder();
        for (String word : word2) {
            sb2.append(word);
        }
        return Objects.equals(sb.toString(), sb2.toString());
    }

    /**
     * 5607. 生成平衡数组的方案数
     *
     * <p>给你一个整数数组 nums 。你需要选择 恰好 一个下标（下标从 0 开始）并删除对应的元素。请注意剩下元素的下标可能会因为删除操作而发生改变。
     *
     * <p>比方说，如果 nums = [6,1,7,4,1] ，那么：
     *
     * <p>选择删除下标 1 ，剩下的数组为 nums = [6,7,4,1] 。 选择删除下标 2 ，剩下的数组为 nums = [6,1,4,1] 。 选择删除下标 4 ，剩下的数组为
     * nums = [6,1,7,4] 。 如果一个数组满足奇数下标元素的和与偶数下标元素的和相等，该数组就是一个 平衡数组 。
     *
     * <p>请你返回删除操作后，剩下的数组 nums 是 平衡数组 的 方案数 。
     *
     * <p>示例 1：
     *
     * <p>输入：nums = [2,1,6,4] 输出：1 解释： 删除下标 0 ：[1,6,4] -> 偶数元素下标为：1 + 4 = 5 。奇数元素下标为：6 。不平衡。 删除下标 1
     * ：[2,6,4] -> 偶数元素下标为：2 + 4 = 6 。奇数元素下标为：6 。平衡。 删除下标 2 ：[2,1,4] -> 偶数元素下标为：2 + 4 = 6 。奇数元素下标为：1
     * 。不平衡。 删除下标 3 ：[2,1,6] -> 偶数元素下标为：2 + 6 = 8 。奇数元素下标为：1 。不平衡。 只有一种让剩余数组成为平衡数组的方案。 示例 2：
     *
     * <p>输入：nums = [1,1,1] 输出：3 解释：你可以删除任意元素，剩余数组都是平衡数组。 示例 3：
     *
     * <p>输入：nums = [1,2,3] 输出：0 解释：不管删除哪个元素，剩下数组都不是平衡数组。
     *
     * <p>提示：
     *
     * <p>1 <= nums.length <= 105 1 <= nums[i] <= 104
     *
     * @param nums
     * @return
     */
    public int waysToMakeFair(int[] nums) {
        int count = 0;
        int len = nums.length;
        // 0 奇数 1 偶数
        int[][] sums = new int[len][2];
        int odd = 0, even = nums[0];
        for (int i = 1; i < len; i++) {
            sums[i][0] += odd;
            sums[i][1] += even;
            if ((i & 1) == 1) {
                odd += nums[i];
            } else {
                even += nums[i];
            }
        }
        // 奇偶互换
        if (((len - 1) & 1) == 1) {
            even = nums[len - 1];
            odd = 0;
        } else {
            odd = nums[len - 1];
            even = 0;
        }

        for (int i = len - 2; i >= 0; i--) {
            sums[i][0] += odd;
            sums[i][1] += even;
            if ((i & 1) == 1) {
                even += nums[i];
            } else {
                odd += nums[i];
            }
        }
        logResult(sums);
        for (int i = 0; i < len; i++) {
            if (sums[i][0] == sums[i][1]) {
                count++;
            }
        }

        return count;
    }

    @Test
    public void waysToMakeFair() {
        int[] nums = {2, 1, 6, 4};
        logResult(waysToMakeFair(nums));
    }

    /**
     * 5608. 完成所有任务的最少初始能量
     *
     * <p>给你一个任务数组 tasks ，其中 tasks[i] = [actuali, minimumi] ：
     *
     * <p>actuali 是完成第 i 个任务 需要耗费 的实际能量。 minimumi 是开始第 i 个任务前需要达到的最低能量。 比方说，如果任务为 [10, 12] 且你当前的能量为
     * 11 ，那么你不能开始这个任务。如果你当前的能量为 13 ，你可以完成这个任务，且完成它后剩余能量为 3 。
     *
     * <p>你可以按照 任意顺序 完成任务。
     *
     * <p>请你返回完成所有任务的 最少 初始能量。
     *
     * <p>示例 1：
     *
     * <p>输入：tasks = [[1,2],[2,4],[4,8]] 输出：8 解释： 一开始有 8 能量，我们按照如下顺序完成任务： - 完成第 3 个任务，剩余能量为 8 - 4 =
     * 4 。 - 完成第 2 个任务，剩余能量为 4 - 2 = 2 。 - 完成第 1 个任务，剩余能量为 2 - 1 = 1 。 注意到尽管我们有能量剩余，但是如果一开始只有 7
     * 能量是不能完成所有任务的，因为我们无法开始第 3 个任务。 示例 2：
     *
     * <p>输入：tasks = [[1,3],[2,4],[10,11],[10,12],[8,9]] 输出：32 解释： 一开始有 32 能量，我们按照如下顺序完成任务： - 完成第 1
     * 个任务，剩余能量为 32 - 1 = 31 。 - 完成第 2 个任务，剩余能量为 31 - 2 = 29 。 - 完成第 3 个任务，剩余能量为 29 - 10 = 19 。 -
     * 完成第 4 个任务，剩余能量为 19 - 10 = 9 。 - 完成第 5 个任务，剩余能量为 9 - 8 = 1 。 示例 3：
     *
     * <p>输入：tasks = [[1,7],[2,8],[3,9],[4,10],[5,11],[6,12]] 输出：27 解释： 一开始有 27 能量，我们按照如下顺序完成任务： -
     * 完成第 5 个任务，剩余能量为 27 - 5 = 22 。 - 完成第 2 个任务，剩余能量为 22 - 2 = 20 。 - 完成第 3 个任务，剩余能量为 20 - 3 = 17 。
     * - 完成第 1 个任务，剩余能量为 17 - 1 = 16 。 - 完成第 4 个任务，剩余能量为 16 - 4 = 12 。 - 完成第 6 个任务，剩余能量为 12 - 6 = 6
     * 。
     *
     * <p>提示：
     *
     * <p>1 <= tasks.length <= 105 1 <= actuali <= minimumi <= 104
     *
     * @param tasks
     * @return
     */
    public int minimumEffort(int[][] tasks) {
        /*Arrays.sort(
                tasks,
                (a, b) -> {
                    int dif1 = a[1] - a[0], dif2 = b[1] - b[0];
                    return dif1 == dif2 ? b[0] - a[0] : dif2 - dif1;
                });
        logResult(tasks);

        for (int i = 0; i < len - 1; i++) {
            result += tasks[i][0];
        }
        result += tasks[len - 1][1];*/
        // 找到差值最小和 需求能量最新的 task
        int len = tasks.length;
        int result = 0;
        int[] minTask = tasks[0];
        int minDif = tasks[0][1] - tasks[0][0], minIdx = 0;
        for (int i = 1; i < len; i++) {
            int dif = tasks[i][1] - tasks[i][0];
            if (dif < minDif) {
                minDif = dif;
                minTask = tasks[i];
                minIdx = i;
            } else if (dif == minDif && minTask[1] < tasks[i][1]) {
                minTask = tasks[i];
                minIdx = i;
            }
        }
        List<int[]> taskList = new ArrayList<>();
        int taskNum = 0;
        for (int i = 0; i < len; i++) {
            if (i == minIdx) {
                taskNum += tasks[i][1];
                continue;
            }
            taskList.add(tasks[i]);
            taskNum += tasks[i][0];
        }
        taskList.sort((a, b) -> b[1] - b[0] - a[1] + a[0]);
        for (int[] task : taskList) {
            log.debug("task:{}", task);
        }
        log.debug("taskNum:{}", taskNum);
        result = taskNum;
        for (int[] task : taskList) {
            int dif = task[1] - taskNum;
            if (dif > 0) {
                result += dif;
                taskNum -= task[0];
                taskNum += dif;
                continue;
            }
            taskNum -= task[0];
        }
        if (taskNum < minTask[1]) {
            result += minTask[1] - taskNum;
        }

        return result;
    }

    @Test
    public void minimumEffort() {
        // [[1,2],[1,7],[2,3],[5,9],[2,2]]
        int[][] tasks = {{1, 2}, {18, 23}, {2, 4}};
        logResult(minimumEffort(tasks));
    }

    /**
     * 1020. 飞地的数量
     *
     * <p>给出一个二维数组 A，每个单元格为 0（代表海）或 1（代表陆地）。
     *
     * <p>移动是指在陆地上从一个地方走到另一个地方（朝四个方向之一）或离开网格的边界。
     *
     * <p>返回网格中无法在任意次数的移动中离开网格边界的陆地单元格的数量。
     *
     * <p>示例 1：
     *
     * <p>输入：[[0,0,0,0],[1,0,1,0],[0,1,1,0],[0,0,0,0]] 输出：3 解释： 有三个 1 被 0 包围。一个 1 没有被包围，因为它在边界上。 示例
     * 2：
     *
     * <p>输入：[[0,1,1,0],[0,0,1,0],[0,0,1,0],[0,0,0,0]] 输出：0 解释： 所有 1 都在边界上或可以到达边界。
     *
     * @param A
     * @return
     */
    public int numEnclaves(int[][] A) {
        // 从边界的1出发，把1变成2 , 剩下的1为答案
        int rows = A.length, cols = A[0].length;
        for (int i = 0; i < rows; i++) {
            if (A[i][0] == 1) {
                numEnclavesDfs(A, i, 0);
            }
            if (A[i][cols - 1] == 1) {
                numEnclavesDfs(A, i, cols - 1);
            }
        }
        for (int j = 1; j < cols - 1; j++) {
            if (A[0][j] == 1) {
                numEnclavesDfs(A, 0, j);
            }
            if (A[rows - 1][j] == 1) {
                numEnclavesDfs(A, rows - 1, j);
            }
        }
        int count = 0;
        for (int[] nums : A) {
            for (int num : nums) {
                if (num == 1) {
                    count++;
                }
            }
        }
        return count;
    }

    private void numEnclavesDfs(int[][] A, int row, int col) {
        if (!inArea(row, col, A.length, A[0].length)) {
            return;
        }
        if (A[row][col] != 1) {
            return;
        }
        A[row][col] = 2;
        for (int i = 0; i < 4; i++) {
            int r = row + DIR_ROW[i], c = col + DIR_COL[i];
            numEnclavesDfs(A, r, c);
        }
    }

    /**
     * 164. 最大间距
     *
     * <p>给定一个无序的数组，找出数组在排序之后，相邻元素之间最大的差值。
     *
     * <p>如果数组元素个数小于 2，则返回 0。
     *
     * <p>示例 1:
     *
     * <p>输入: [3,6,9,1] 输出: 3 解释: 排序后的数组是 [1,3,6,9], 其中相邻元素 (3,6) 和 (6,9) 之间都存在最大差值 3。 示例 2:
     *
     * <p>输入: [10] 输出: 0 解释: 数组元素个数小于 2，因此返回 0。 说明:
     *
     * <p>你可以假设数组中所有元素都是非负整数，且数值在 32 位有符号整数范围内。 请尝试在线性时间复杂度和空间复杂度的条件下解决此问题。
     *
     * @param nums
     * @return
     */
    public int maximumGap(int[] nums) {
        Arrays.sort(nums);
        int max = 0;
        for (int i = 1; i < nums.length; i++) {
            max = Math.max(max, nums[i] - nums[i - 1]);
        }
        return max;
    }

    /**
     * 493. 翻转对
     *
     * <p>给定一个数组 nums ，如果 i < j 且 nums[i] > 2*nums[j] 我们就将 (i, j) 称作一个重要翻转对。
     *
     * <p>你需要返回给定数组中的重要翻转对的数量。
     *
     * <p>示例 1:
     *
     * <p>输入: [1,3,2,3,1] 输出: 2 示例 2:
     *
     * <p>输入: [2,4,3,5,1] 输出: 3 注意:
     *
     * <p>给定数组的长度不会超过50000。 输入数组中的所有数字都在32位整数的表示范围内。
     *
     * @param nums
     * @return
     */
    public int reversePairs2(int[] nums) {
        // 归并排序
        return mergeSort(nums, 0, nums.length - 1);
    }

    private int mergeSort(int[] nums, int start, int end) {

        // 当数组规模小于2时，非常容易
        if (end - start == 0) {
            return 0;
        }
        int mid = (start + end) >> 1;
        int count1 = mergeSort(nums, start, mid);
        int count2 = mergeSort(nums, mid + 1, end);
        int result = count1 + count2;
        int i = start, j = mid + 1;
        for (; i <= mid; i++) {
            while (j <= end && (long) nums[i] > ((long) nums[j] << 1)) {
                j++;
            }
            result += j - mid - 1;
        }

        // 合并有序数组
        int[] sortNums = new int[end - start + 1];
        int index = 0;
        i = start;
        j = mid + 1;
        while (index < sortNums.length) {
            if (i > mid) {
                sortNums[index++] = nums[j++];
                continue;
            }
            if (j > end) {
                sortNums[index++] = nums[i++];
                continue;
            }
            if (nums[i] < nums[j]) {
                sortNums[index++] = nums[i++];
            } else {
                sortNums[index++] = nums[j++];
            }
        }
        for (int k = 0; k < sortNums.length; k++) {
            nums[start + k] = sortNums[k];
        }
        return result;
    }

    @Test
    public void reversePairs2() {
        int[] nums = {2, 4, 3, 5, 1};
        logResult(reversePairs2(nums));
    }

    /**
     * 5559. 得到山形数组的最少删除次数
     *
     * <p>我们定义 arr 是 山形数组 当且仅当它满足：
     *
     * <p>arr.length >= 3 存在某个下标 i （从 0 开始） 满足 0 < i < arr.length - 1 且： arr[0] < arr[1] < ... <
     * arr[i - 1] < arr[i] arr[i] > arr[i + 1] > ... > arr[arr.length - 1] 给你整数数组 nums ，请你返回将 nums
     * 变成 山形状数组 的最少 删除次数。
     *
     * <p>示例 1：
     *
     * <p>输入：nums = [1,3,1] 输出：0 解释：数组本身就是山形数组，所以我们不需要删除任何元素。
     *
     * <p>示例 2：
     *
     * <p>输入：nums = [2,1,1,5,6,2,3,1] 输出：3 解释：一种方法是将下标为 0，1 和 5 的元素删除，剩余元素为 [1,5,6,3,1] ，是山形数组。
     *
     * <p>示例 3：
     *
     * <p>输入：nums = [4,3,2,1,1,2,3,1] 输出：4
     *
     * <p>示例4：
     *
     * <p>输入：nums = [1,2,3,4,4,3,2,1] 输出：1
     *
     * <p>提示：
     *
     * <p>3 <= nums.length <= 1000 1 <= nums[i] <= 109 题目保证 nums 删除一些元素后一定能得到山形数组。
     *
     * @param nums
     * @return
     */
    public int minimumMountainRemovals(int[] nums) {
        /**
         * int count = 0; int maxLen = 0; int[] lens = new int[nums.length]; int[] counts = new
         * int[nums.length]; Arrays.fill(counts, 1); // lens[0] = 1; for (int i = 0; i <
         * nums.length; i++) { int num = nums[i]; for (int j = 0; j < i; j++) { if (num <= nums[j])
         * { continue; } // lens[i] = Math.max(lens[j] + 1, lens[i]); if (lens[j] >= lens[i]) {
         * lens[i] = lens[j] + 1; counts[i] = counts[j]; } else if (lens[j] + 1 == lens[i]) {
         * counts[i] += counts[j]; } } maxLen = Math.max(maxLen, lens[i]); }
         */
        int len = nums.length;
        if (len == 3) {
            return 0;
        }
        // 从左向右找最长递增子序列
        int[] leftLen = new int[len], rightLen = new int[len];
        for (int i = 0; i < len; i++) {
            int num = nums[i];
            for (int j = 0; j < i; j++) {
                if (num <= nums[j]) {
                    continue;
                }
                leftLen[i] = Math.max(leftLen[j] + 1, leftLen[i]);
            }
        }
        log.debug("left:{}", leftLen);
        for (int i = len - 1; i >= 0; i--) {
            int num = nums[i];
            for (int j = len - 1; j > i; j--) {
                if (num <= nums[j]) {
                    continue;
                }
                rightLen[i] = Math.max(rightLen[j] + 1, rightLen[i]);
            }
        }
        log.debug("right:{}", rightLen);
        int min = len;
        // 从右向左找最长递增子序列
        for (int i = 1; i < len - 1; i++) {
            if (leftLen[i] == 0 || rightLen[i] == 0) {
                continue;
            }
            min = Math.min(min, len - leftLen[i] - rightLen[i] - 1);
        }

        return min;
    }

    @Test
    public void minimumMountainRemovals() {
        int[] nums = {2, 2, 3, 4, 3, 2, 1};
        logResult(minimumMountainRemovals(nums));
    }

    /**
     * 5613. 最富有客户的资产总量
     *
     * <p>给你一个 m x n 的整数网格 accounts ，其中 accounts[i][j] 是第 i 位客户在第 j 家银行托管的资产数量。返回最富有客户所拥有的 资产总量 。
     *
     * <p>客户的 资产总量 就是他们在各家银行托管的资产数量之和。最富有客户就是 资产总量 最大的客户。
     *
     * <p>示例 1：
     *
     * <p>输入：accounts = [[1,2,3],[3,2,1]] 输出：6 解释： 第 1 位客户的资产总量 = 1 + 2 + 3 = 6 第 2 位客户的资产总量 = 3 + 2
     * + 1 = 6 两位客户都是最富有的，资产总量都是 6 ，所以返回 6 。 示例 2：
     *
     * <p>输入：accounts = [[1,5],[7,3],[3,5]] 输出：10 解释： 第 1 位客户的资产总量 = 6 第 2 位客户的资产总量 = 10 第 3
     * 位客户的资产总量 = 8 第 2 位客户是最富有的，资产总量是 10 示例 3：
     *
     * <p>输入：accounts = [[2,8,7],[7,1,3],[1,9,5]] 输出：17
     *
     * <p>提示：
     *
     * <p>m == accounts.length n == accounts[i].length 1 <= m, n <= 50 1 <= accounts[i][j] <= 100
     *
     * @param accounts
     * @return
     */
    public int maximumWealth(int[][] accounts) {
        int max = 0;
        for (int[] account : accounts) {
            int sum = Arrays.stream(account).sum();
            max = Math.max(max, sum);
        }
        return max;
    }

    /**
     * 5614. 找出最具竞争力的子序列
     *
     * <p>题目难度Medium 给你一个整数数组 nums 和一个正整数 k ，返回长度为 k 且最具 竞争力 的 nums 子序列。
     *
     * <p>数组的子序列是从数组中删除一些元素（可能不删除元素）得到的序列。
     *
     * <p>在子序列 a 和子序列 b 第一个不相同的位置上，如果 a 中的数字小于 b 中对应的数字，那么我们称子序列 a 比子序列 b（相同长度下）更具 竞争力 。 例如，[1,3,4]
     * 比 [1,3,5] 更具竞争力，在第一个不相同的位置，也就是最后一个位置上， 4 小于 5 。
     *
     * <p>示例 1：
     *
     * <p>输入：nums = [3,5,2,6], k = 2 输出：[2,6] 解释：在所有可能的子序列集合 {[3,5], [3,2], [3,6], [5,2], [5,6],
     * [2,6]} 中，[2,6] 最具竞争力。 示例 2：
     *
     * <p>输入：nums = [2,4,3,3,5,4,9,6], k = 4 输出：[2,3,3,4]
     *
     * <p>提示：
     *
     * <p>1 <= nums.length <= 105 0 <= nums[i] <= 109 1 <= k <= nums.length
     *
     * @param nums
     * @param k
     * @return
     */
    public int[] mostCompetitive(int[] nums, int k) {
        int len = nums.length;
        if (k >= len) {
            return nums;
        }
        int[] result = new int[k];
        Deque<Integer> stack = new LinkedList<>();
        int i = 0;
        for (; i < len; i++) {
            int num = nums[i];
            int leftSize = len - i;
            while (!stack.isEmpty() && num < stack.peekLast() && stack.size() > k - leftSize) {
                stack.pollLast();
            }
            stack.offerLast(num);
        }
        logResult(stack);
        for (int j = 0; j < k; j++) {
            result[j] = stack.pollFirst();
        }

        return result;
        /*
        Arrays.fill(result, Integer.MAX_VALUE);
        for (int i = 0; i < len; i++) {
            int num = nums[i];
            int j = Math.max(0, k - len + i);
            for (; j < k; j++) {
                if (num < result[j]) {
                    result[j] = num;
                    break;
                }
            }
            j++;
            for (; j < k; j++) {
                result[j] = Integer.MAX_VALUE;
            }
        }
        return result;*/
    }

    @Test
    public void mostCompetitive() {
        int[] nums = {3, 5, 2, 6};
        int k = 2;
        log.debug("nums:{}", mostCompetitive(nums, k));
    }

    /**
     * 5615. 使数组互补的最少操作次数
     *
     * <p>给你一个长度为 偶数 n 的整数数组 nums 和一个整数 limit 。每一次操作，你可以将 nums 中的任何整数替换为 1 到 limit 之间的另一个整数。
     *
     * <p>如果对于所有下标 i（下标从 0 开始），nums[i] + nums[n - 1 - i] 都等于同一个数，则数组 nums 是 互补的 。例如，数组 [1,2,3,4]
     * 是互补的，因为对于所有下标 i ，nums[i] + nums[n - 1 - i] = 5 。
     *
     * <p>返回使数组 互补 的 最少 操作次数。
     *
     * <p>示例 1：
     *
     * <p>输入：nums = [1,2,4,3], limit = 4 输出：1 解释：经过 1 次操作，你可以将数组 nums 变成 [1,2,2,3]（加粗元素是变更的数字）：
     * nums[0] + nums[3] = 1 + 3 = 4. nums[1] + nums[2] = 2 + 2 = 4. nums[2] + nums[1] = 2 + 2 = 4.
     * nums[3] + nums[0] = 3 + 1 = 4. 对于每个 i ，nums[i] + nums[n-1-i] = 4 ，所以 nums 是互补的。 示例 2：
     *
     * <p>输入：nums = [1,2,2,1], limit = 2 输出：2 解释：经过 2 次操作，你可以将数组 nums 变成 [2,2,2,2] 。你不能将任何数字变更为 3
     * ，因为 3 > limit 。 示例 3：
     *
     * <p>输入：nums = [1,2,1,2], limit = 2 输出：0 解释：nums 已经是互补的。
     *
     * <p>提示：
     *
     * <p>n == nums.length 2 <= n <= 105 1 <= nums[i] <= limit <= 105 n 是偶数。
     *
     * @param nums
     * @param limit
     * @return
     */
    public int minMoves(int[] nums, int limit) {

        // 差分数组  nums[i] <= limit 最大是2 * limit
        int[] diff = new int[(limit << 1) + 2];
        int len = nums.length;
        int left = 0, right = len - 1;
        while (left < right) {

            // [2, 2 * limit] 范围 + 2
            int l = 2, r = 2 * limit;
            diff[l] += 2;
            diff[r + 1] -= 2;

            // [1 + min(x, y), rlimit + max(x, y)] 范围 -1
            int low = Math.min(nums[left], nums[right]) + 1;
            int high = Math.max(nums[left], nums[right]) + limit;
            diff[low]--;
            diff[high + 1]++;

            int sum = nums[left] + nums[right];
            diff[sum]--;
            diff[sum + 1]++;
            left++;
            right--;
        }
        int result = len, count = 0;

        for (int i = 2; i <= limit << 1; i++) {
            count += diff[i];
            result = Math.min(result, count);
        }

        return result;
    }

    /**
     * 480. 滑动窗口中位数
     *
     * <p>中位数是有序序列最中间的那个数。如果序列的大小是偶数，则没有最中间的数；此时中位数是最中间的两个数的平均数。
     *
     * <p>例如：
     *
     * <p>[2,3,4]，中位数是 3 [2,3]，中位数是 (2 + 3) / 2 = 2.5 给你一个数组 nums，有一个大小为 k 的窗口从最左端滑动到最右端。窗口中有 k
     * 个数，每次窗口向右移动 1 位。你的任务是找出每次窗口移动后得到的新窗口中元素的中位数，并输出由它们组成的数组。
     *
     * <p>示例：
     *
     * <p>给出 nums = [1,3,-1,-3,5,3,6,7]，以及 k = 3。
     *
     * <p>窗口位置 中位数 --------------- ----- [1 3 -1] -3 5 3 6 7 1 1 [3 -1 -3] 5 3 6 7 -1 1 3 [-1 -3 5]
     * 3 6 7 -1 1 3 -1 [-3 5 3] 6 7 3 1 3 -1 -3 [5 3 6] 7 5 1 3 -1 -3 5 [3 6 7] 6 因此，返回该滑动窗口的中位数数组
     * [1,-1,-1,3,5,6]。
     *
     * <p>提示：
     *
     * <p>你可以假设 k 始终有效，即：k 始终小于输入的非空数组的元素个数。 与真实值误差在 10 ^ -5 以内的答案将被视作正确答案。
     *
     * @param nums
     * @param k
     * @return
     */
    public double[] medianSlidingWindow(int[] nums, int k) {
        minHeap = new PriorityQueue<>();
        maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        int i = 0;
        int len = nums.length;
        double[] result = new double[len - k + 1];
        for (; i < k - 1; i++) {
            add(nums[i]);
        }

        for (; i < len; i++) {
            add(nums[i]);
            Integer leftMid = maxHeap.peek();
            Integer rightMid = minHeap.peek();
            if (minHeap.size() == maxHeap.size()) {
                result[i - k + 1] = (leftMid.doubleValue() + rightMid.doubleValue()) / 2.0;
            } else {
                result[i - k + 1] = leftMid.doubleValue();
            }
            remove(nums[i - k + 1]);
        }

        return result;
    }

    PriorityQueue<Integer> minHeap;
    PriorityQueue<Integer> maxHeap;

    private void add(int num) {
        maxHeap.offer(num);
        minHeap.offer(maxHeap.poll());
        if (minHeap.size() > maxHeap.size()) {
            maxHeap.offer(minHeap.poll());
        }
    }

    private void remove(int num) {
        if (num <= maxHeap.peek()) {
            maxHeap.remove(num);
        } else {
            minHeap.remove(num);
        }
    }

    /**
     * 363. 矩形区域不超过 K 的最大数值和
     *
     * <p>给定一个非空二维矩阵 matrix 和一个整数 k，找到这个矩阵内部不大于 k 的最大矩形和。
     *
     * <p>示例:
     *
     * <p>输入: matrix = [[1,0,1],[0,-2,3]], k = 2 输出: 2 解释: 矩形区域 [[0, 1], [-2, 3]] 的数值和是 2，且 2 是不超过 k
     * 的最大数字（k = 2）。 说明：
     *
     * <p>矩阵内的矩形区域面积必须大于 0。 如果行数远大于列数，你将如何解答呢？
     *
     * @param matrix
     * @param k
     * @return
     */
    public int maxSumSubmatrix(int[][] matrix, int k) {
        int rows = matrix.length, cols = matrix[0].length;
        int max = Integer.MIN_VALUE;
        // 前缀和
        for (int i = 0; i < rows; i++) {
            for (int j = 1; j < cols; j++) {
                matrix[i][j] += matrix[i][j - 1];
            }
        }
        for (int left = 0; left < cols; left++) {
            for (int right = left; right < cols; right++) {
                // 前缀和
                int sum = 0, curMax = Integer.MIN_VALUE;
                TreeSet<Integer> set = new TreeSet<>();
                set.add(0);
                for (int i = 0; i < rows; i++) {
                    sum += matrix[i][right];
                    if (left > 0) {
                        sum -= matrix[i][left - 1];
                    }
                    Integer another = set.ceiling(sum - k);
                    if (null != another) {
                        curMax = Math.max(curMax, sum - another);
                    }
                    set.add(sum);
                }
                max = Math.max(max, curMax);
            }
        }

        return max;
    }

    /**
     * 407. 接雨水 II
     *
     * <p>给你一个 m x n 的矩阵，其中的值均为非负整数，代表二维高度图每个单元的高度，请计算图中形状最多能接多少体积的雨水。
     *
     * <p>示例：
     *
     * <p>给出如下 3x6 的高度图: [ [1,4,3,1,3,2], [3,2,1,3,2,4], [2,3,3,2,3,1] ]
     *
     * <p>返回 4 。
     *
     * <p>如上图所示，这是下雨前的高度图[[1,4,3,1,3,2],[3,2,1,3,2,4],[2,3,3,2,3,1]] 的状态。
     *
     * <p>下雨后，雨水将会被存储在这些方块中。总的接雨水量是4。
     *
     * <p>提示：
     *
     * <p>1 <= m, n <= 110 0 <= heightMap[i][j] <= 20000
     *
     * @param heightMap
     * @return
     */
    public int trapRainWater(int[][] heightMap) {

        int m = heightMap.length, n = heightMap[0].length;
        if (m < 3 || n < 3) {
            return 0;
        }
        // 优先队列找到四周最小的height
        PriorityQueue<Barrel> heap = new PriorityQueue<>((a, b) -> a.height - b.height);
        boolean[][] visit = new boolean[m][n];
        visit[0][0] = true;
        visit[0][n - 1] = true;
        visit[m - 1][0] = true;
        visit[m - 1][n - 1] = true;
        // 先把最外一圈放进去
        for (int i = 1; i < m - 1; i++) {
            heap.offer(new Barrel(i, 0, heightMap[i][0]));
            heap.offer(new Barrel(i, n - 1, heightMap[i][n - 1]));
            visit[i][0] = true;
            visit[i][n - 1] = true;
        }
        for (int j = 1; j < n - 1; j++) {
            heap.offer(new Barrel(0, j, heightMap[0][j]));
            heap.offer(new Barrel(m - 1, j, heightMap[m - 1][j]));
            visit[0][j] = true;
            visit[m - 1][j] = true;
        }

        int result = 0;
        while (!heap.isEmpty()) {
            Barrel barrel = heap.poll();
            for (int i = 0; i < 4; i++) {
                int row = barrel.row + DIR_ROW[i], col = barrel.col + DIR_COL[i];
                if (!inArea(row, col, m, n) || visit[row][col]) {
                    continue;
                }
                if (barrel.height > heightMap[row][col]) {
                    result += barrel.height - heightMap[row][col];
                }
                heap.offer(new Barrel(row, col, Math.max(barrel.height, heightMap[row][col])));
                visit[row][col] = true;
            }
        }

        return result;
    }

    static class Barrel {
        int row, col, height;

        public Barrel(int row, int col, int height) {
            this.row = row;
            this.col = col;
            this.height = height;
        }
    }

    @Test
    public void trapRainWater() {
        int[][] heightMap = {{5, 5, 5, 1}, {5, 1, 1, 5}, {5, 1, 5, 5}, {5, 2, 5, 8}};
        logResult(trapRainWater(heightMap));
    }
}
