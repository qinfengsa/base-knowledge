package com.qinfengsa.structure.leetcode;

import com.qinfengsa.structure.tree.Tree;
import com.qinfengsa.structure.util.LogUtils;
import javafx.util.Pair;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.lang.reflect.Array;
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
import java.util.Stack;
import java.util.TreeMap;

import static com.qinfengsa.structure.util.LogUtils.logResult;

/**
 * @author qinfengsa
 * @date 2019/5/8 13:38
 */
@Slf4j
public class ArrayTest {

    /**
     * 给定一个由整数组成的非空数组所表示的非负整数，在该数的基础上加一。
     *
     * 最高位数字存放在数组的首位， 数组中每个元素只存储一个数字。
     *
     * 你可以假设除了整数 0 之外，这个整数不会以零开头。
     *
     * 示例 1:
     *
     * 输入: [1,2,3]
     * 输出: [1,2,4]
     * 解释: 输入数组表示数字 123。
     * 示例 2:
     *
     * 输入: [4,3,2,1]
     * 输出: [4,3,2,2]
     * 解释: 输入数组表示数字 4321。
     */
    @Test
    public void testAdd() {
        int[] digits = {9,9,9};
        int[] result = plusOne( digits);
        log.info("result:{}",result);
    }

    /**
     * 给定一个由整数组成的非空数组所表示的非负整数，在该数的基础上加一。
     *
     * 最高位数字存放在数组的首位， 数组中每个元素只存储一个数字。
     *
     * 你可以假设除了整数 0 之外，这个整数不会以零开头。
     *
     * 示例 1:
     *
     * 输入: [1,2,3]
     * 输出: [1,2,4]
     * 解释: 输入数组表示数字 123。
     * 示例 2:
     *
     * 输入: [4,3,2,1]
     * 输出: [4,3,2,2]
     * 解释: 输入数组表示数字 4321。
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
        for (int i = digits.length -1; i >= 0; i--) {
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
            for (int i = 0 ; i < digits.length ; i++) {
                nums[i+1] = digits[i];
            }
            return nums;

        }

        return digits;
    }

    /**
     * 在一个给定的数组nums中，总是存在一个最大元素 。
     *
     * 查找数组中的最大元素是否至少是数组中每个其他数字的两倍。
     *
     * 如果是，则返回最大元素的索引，否则返回-1。
     *
     * 示例 1:
     *
     * 输入: nums = [3, 6, 1, 0]
     * 输出: 1
     * 解释: 6是最大的整数, 对于数组中的其他整数,
     * 6大于数组中其他元素的两倍。6的索引是1, 所以我们返回1.
     *
     *
     * 示例 2:
     *
     * 输入: nums = [1, 2, 3, 4]
     * 输出: -1
     * 解释: 4没有超过3的两倍大, 所以我们返回 -1.
     */
    @Test
    public void testGetMax() {
        int[] nums = {1, 0};

        int result = dominantIndex(nums);
        log.info("result:{}",result);
    }

    /**
     * 在一个给定的数组nums中，总是存在一个最大元素 。
     *
     * 查找数组中的最大元素是否至少是数组中每个其他数字的两倍。
     *
     * 如果是，则返回最大元素的索引，否则返回-1。
     *
     * 示例 1:
     *
     * 输入: nums = [3, 6, 1, 0]
     * 输出: 1
     * 解释: 6是最大的整数, 对于数组中的其他整数,
     * 6大于数组中其他元素的两倍。6的索引是1, 所以我们返回1.
     *
     *
     * 示例 2:
     *
     * 输入: nums = [1, 2, 3, 4]
     * 输出: -1
     * 解释: 4没有超过3的两倍大, 所以我们返回 -1.
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
                max =  nums[i];
                maxIndex = i;
            } else if (nums[i] > max) {

                secondMax = max;
                max = nums[i];
                maxIndex = i;
            } else if (nums[i] > secondMax) {
                secondMax = nums[i];
            }
        }
        log.info("1:{},2:{}",max,secondMax);
        if (max >= (2*secondMax)) {
            return maxIndex;
        }

        return -1;
    }

    /**
     * 对角线遍历
     * 给定一个含有 M x N 个元素的矩阵（M 行，N 列），请以对角线遍历的顺序返回这个矩阵中的所有元素，对角线遍历如下图所示。
     * 示例:
     * 输入:
     * [
     *  [ 1, 2, 3 ],
     *  [ 4, 5, 6 ],
     *  [ 7, 8, 9 ]
     * ]
     *
     * 输出:  [1,2,4,7,5,3,6,8,9]
     */
    @Test
    public void  testDiagonalOrder() {
        //int[][] matrix = {{1, 2, 3},{4, 5, 6},{7, 8, 9},{10,11,12},{13,14,15}};

        //int[][] matrix = {{1, 2, 3,4},{ 5, 6,7, 8},{  9,10,11,12} };
        int[][] matrix = {};
        int[] result = findDiagonalOrder(matrix);
        log.info("result:{}",result);
    }


    /**
     * 对角线遍历
     * 给定一个含有 M x N 个元素的矩阵（M 行，N 列），请以对角线遍历的顺序返回这个矩阵中的所有元素，对角线遍历如下图所示。
     * 示例:
     *
     * 输入:
     * [
     *  [ 1, 2, 3 ],
     *  [ 4, 5, 6 ],
     *  [ 7, 8, 9 ]
     * ]
     *
     * 输出:  [1,2,4,7,5,3,6,8,9]
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
            if (x == len - 1 && y == width-1) {
                break;
            }
            // 如果x+y是偶数 右上移动
            if ((x+y)%2 == 0) {
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
                if (y == width-1) {
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
    public void  findDiagonalOrder2() {
        int[][] matrix = {{1, 2, 3},{4, 5, 6},{7, 8, 9},{10,11,12},{13,14,15}};
        List<List<Integer>> nums = new ArrayList<>();
        List<Integer> list1 = new ArrayList<>( );
        list1.add(1);
        list1.add(2);
        list1.add(3);
        List<Integer> list2 = new ArrayList<>( );
        list2.add(4);
        list2.add(5);
        list2.add(6);
        List<Integer> list3 = new ArrayList<>( );
        list3.add(7);
        list3.add(8);
        list3.add(9);
        nums.add(list1);
        nums.add(list2);
        nums.add(list3);
        //int[][] matrix = {{1, 2, 3,4},{ 5, 6,7, 8},{  9,10,11,12} };
        //int[][] matrix = {};
        int[] result = findDiagonalOrder(nums);
        log.info("result:{}",result);
    }

    /**
     * 1424. 对角线遍历 II
     * 给你一个列表 nums ，里面每一个元素都是一个整数列表。请你依照下面各图的规则，按顺序返回 nums 中对角线上的整数。
     *
     *
     *
     * 示例 1：
     *
     *
     *
     * 输入：nums = [[1,2,3],[4,5,6],[7,8,9]]
     * 输出：[1,4,2,7,5,3,8,6,9]
     * 示例 2：
     *
     *
     *
     * 输入：nums = [[1,2,3,4,5],[6,7],[8],[9,10,11],[12,13,14,15,16]]
     * 输出：[1,6,2,8,7,3,9,4,12,10,5,13,11,14,15,16]
     * 示例 3：
     *
     * 输入：nums = [[1,2,3],[4],[5,6,7],[8],[9,10,11]]
     * 输出：[1,4,2,5,3,8,6,9,7,10,11]
     * 示例 4：
     *
     * 输入：nums = [[1,2,3,4,5,6]]
     * 输出：[1,2,3,4,5,6]
     *
     *
     * 提示：
     *
     * 1 <= nums.length <= 10^5
     * 1 <= nums[i].length <= 10^5
     * 1 <= nums[i][j] <= 10^9
     * nums 中最多有 10^5 个数字。
     * @param nums
     * @return
     */
    public int[] findDiagonalOrder(List<List<Integer>> nums) {
        int len = 0;
        int size = nums.size();
        Map<Integer,List<Integer>> map = new TreeMap<>();
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
     * 螺旋矩阵
     * 给定一个包含 m x n 个元素的矩阵（m 行, n 列），请按照顺时针螺旋顺序，返回矩阵中的所有元素。
     *
     * 示例 1:
     *
     * 输入:
     * [
     *  [ 1, 2, 3 ],
     *  [ 4, 5, 6 ],
     *  [ 7, 8, 9 ]
     * ]
     * 输出: [1,2,3,6,9,8,7,4,5]
     * 示例 2:
     *
     * 输入:
     * [
     *   [1, 2, 3, 4],
     *   [5, 6, 7, 8],
     *   [9,10,11,12]
     * ]
     * 输出: [1,2,3,4,8,12,11,10,9,5,6,7]
     */
    @Test
    public void  testSpiralOrder() {
        int[][] matrix = {{1, 2, 3},{4, 5, 6},{7, 8, 9} ,{  10,11,12}};

        //int[][] matrix = {{1, 2, 3,4},{ 5, 6,7, 8},{  9,10,11,12},{  13,14,15,16} };
        //int[][] matrix = {};
        List<Integer> result = spiralOrder(matrix);
        log.info("result:{}", result);
    }




    /**
     * 螺旋矩阵
     * 给定一个包含 m x n 个元素的矩阵（m 行, n 列），请按照顺时针螺旋顺序，返回矩阵中的所有元素。
     *
     * 示例 1:
     *
     * 输入:
     * [
     *  [ 1, 2, 3 ],
     *  [ 4, 5, 6 ],
     *  [ 7, 8, 9 ]
     * ]
     * 输出: [1,2,3,6,9,8,7,4,5]
     * 示例 2:
     * 输入:
     * [
     *   [1, 2, 3, 4],
     *   [5, 6, 7, 8],
     *   [9,10,11,12]
     * ]
     * 输出: [1,2,3,4,8,12,11,10,9,5,6,7]
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
                    y = y+1;
                    yMin = y ;

                    moveType = 2;

                }
            } else if (moveType == 2) {
                y++;
                // 从上至下 移动到最下边时 xMax = x 方向改变
                if (y > yMax) {
                    y = yMax;
                    x = x - 1;
                    xMax = x  ;
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
                    x = x+1;
                    xMin = x ;
                    moveType = 1;

                }
            }


        }

        return list;
    }

    /**
     *  杨辉三角
     * 给定一个非负整数 numRows，生成杨辉三角的前 numRows 行。
     * 在杨辉三角中，每个数是它左上方和右上方的数的和。
     *
     * 示例:
     *
     * 输入: 5
     * 输出:
     * [
     *      [1],
     *     [1,1],
     *    [1,2,1],
     *   [1,3,3,1],
     *  [1,4,6,4,1]
     * ]
     */
    @Test
    public void testYangHui() {
        int numRows = 7;
        List<List<Integer>> result = generate(numRows);
        int index = 0;
        for (List<Integer> list:result) {
            index++;
            log.info("{}:{}",index,list);
        }
    }


    /**
     *  杨辉三角
     * 给定一个非负整数 numRows，生成杨辉三角的前 numRows 行。
     * 在杨辉三角中，每个数是它左上方和右上方的数的和。
     *
     * 示例:
     *
     * 输入: 5
     * 输出:
     * [
     *      [1],
     *     [1,1],
     *    [1,2,1],
     *   [1,3,3,1],
     *  [1,4,6,4,1]
     * ]
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
                if(j == 0 || i == j ) {
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
                list.add(array[i][j] );
            }

            result.add(list);
        }

        return result;
    }


    /**
     * 旋转数组
     * 给定一个数组，将数组中的元素向右移动 k 个位置，其中 k 是非负数。
     *
     * 示例 1:
     *
     * 输入: [1,2,3,4,5,6,7] 和 k = 3
     * 输出: [5,6,7,1,2,3,4]
     * 解释:
     * 向右旋转 1 步: [7,1,2,3,4,5,6]
     * 向右旋转 2 步: [6,7,1,2,3,4,5]
     * 向右旋转 3 步: [5,6,7,1,2,3,4]
     * 示例 2:
     *
     * 输入: [-1,-100,3,99] 和 k = 2
     * 输出: [3,99,-1,-100]
     * 解释:
     * 向右旋转 1 步: [99,-1,-100,3]
     * 向右旋转 2 步: [3,99,-1,-100]
     * 说明:
     *
     * 尽可能想出更多的解决方案，至少有三种不同的方法可以解决这个问题。
     * 要求使用空间复杂度为 O(1) 的原地算法。
     */
    @Test
    public void testRotate() {
        int[]  nums = {1,2,3,4,5,6,7};
        rotate(nums,7);
        log.debug("result:{} ",nums );
    }

    /**
     * 旋转数组
     * 给定一个数组，将数组中的元素向右移动 k 个位置，其中 k 是非负数。
     *
     * 示例 1:
     *
     * 输入: [1,2,3,4,5,6,7] 和 k = 3
     * 输出: [5,6,7,1,2,3,4]
     * 解释:
     * 向右旋转 1 步: [7,1,2,3,4,5,6]
     * 向右旋转 2 步: [6,7,1,2,3,4,5]
     * 向右旋转 3 步: [5,6,7,1,2,3,4]
     * 示例 2:
     *
     * 输入: [-1,-100,3,99] 和 k = 2
     * 输出: [3,99,-1,-100]
     * 解释:
     * 向右旋转 1 步: [99,-1,-100,3]
     * 向右旋转 2 步: [3,99,-1,-100]
     * 说明:
     *
     * 尽可能想出更多的解决方案，至少有三种不同的方法可以解决这个问题。
     * 要求使用空间复杂度为 O(1) 的原地算法。
     * @param nums
     * @param k
     */
    public void rotate(int[] nums, int k) {
        if (Objects.isNull(nums)) {
            return;
        }
        int size = nums.length;
        k = k%size;
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
     * 杨辉三角 II
     * 给定一个非负索引 k，其中 k ≤ 33，返回杨辉三角的第 k 行。
     * 在杨辉三角中，每个数是它左上方和右上方的数的和。
     *
     * 示例:
     *
     * 输入: 3
     * 输出: [1,3,3,1]
     * 进阶：
     *
     * 你可以优化你的算法到 O(k) 空间复杂度吗？
     */
    @Test
    public void testYangHui2() {
        int rowIndex = 5;
        List<Integer> result = getRow(rowIndex);

        log.info("result:{}", result);
    }


    /**
     *  杨辉三角
     * 给定一个非负整数 numRows，生成杨辉三角的前 numRows 行。
     * 在杨辉三角中，每个数是它左上方和右上方的数的和。
     *
     * 示例:
     *
     * 输入: 5
     * 输出:
     * [
     *      [1],
     *     [1,1],
     *    [1,2,1],
     *   [1,3,3,1],
     *  [1,4,6,4,1]
     * ]
     * 杨辉三角 II
     * 给定一个非负索引 k，其中 k ≤ 33，返回杨辉三角的第 k 行。
     * 在杨辉三角中，每个数是它左上方和右上方的数的和。
     *
     * 示例:
     *
     * 输入: 3
     * 输出: [1,3,3,1]
     * 进阶：
     *
     * 你可以优化你的算法到 O(k) 空间复杂度吗？
     * @param rowIndex
     * @return
     */
    public List<Integer> getRow(int rowIndex) {
        rowIndex++;
        List<Integer> result = new ArrayList<>(rowIndex);
        int[]  array = new int[rowIndex] ;
        // 循环打印杨辉三角,numRows 行
        for (int i = 0; i < rowIndex; i++) {
            // 注意:j<=i, 因为第1行有1列，第2行有2列，第3行有3列。。。
            int lastNum = 0;
            for (int j = 0; j <= i; j++) {
                // 第一列和最后一列
                int tmpNum = 0;
                if (j+1<=i) {
                    // 中间列的值 = 上一行和它所在列-1的值 + 上一行和它所在列的值
                    tmpNum = array[j] + array[j+1];
                }
                if(j == 0 || i == j ) {
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
     *  删除排序数组中的重复项
     * 给定一个排序数组，你需要在原地删除重复出现的元素，使得每个元素只出现一次，返回移除后数组的新长度。
     *
     * 不要使用额外的数组空间，你必须在原地修改输入数组并在使用 O(1) 额外空间的条件下完成。
     *
     * 示例 1:
     *
     * 给定数组 nums = [1,1,2],
     *
     * 函数应该返回新的长度 2, 并且原数组 nums 的前两个元素被修改为 1, 2。
     *
     * 你不需要考虑数组中超出新长度后面的元素。
     * 示例 2:
     *
     * 给定 nums = [0,0,1,1,1,2,2,3,3,4],
     *
     * 函数应该返回新的长度 5, 并且原数组 nums 的前五个元素被修改为 0, 1, 2, 3, 4。
     *
     * 你不需要考虑数组中超出新长度后面的元素。
     */
    @Test
    public void testRemoveDuplicates() {
        int[] nums = {1,1,2};
        int result = removeDuplicates(nums);

        log.info("result:{},nums:{}", result,nums);
    }

    /**
     * 删除排序数组中的重复项
     * 给定一个排序数组，你需要在原地删除重复出现的元素，使得每个元素只出现一次，返回移除后数组的新长度。
     *
     * 不要使用额外的数组空间，你必须在原地修改输入数组并在使用 O(1) 额外空间的条件下完成。
     *
     * 示例 1:
     *
     * 给定数组 nums = [1,1,2],
     *
     * 函数应该返回新的长度 2, 并且原数组 nums 的前两个元素被修改为 1, 2。
     *
     * 你不需要考虑数组中超出新长度后面的元素。
     * 示例 2:
     *
     * 给定 nums = [0,0,1,1,1,2,2,3,3,4],
     *
     * 函数应该返回新的长度 5, 并且原数组 nums 的前五个元素被修改为 0, 1, 2, 3, 4。
     *
     * 你不需要考虑数组中超出新长度后面的元素。
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
     *  移动零
     * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
     *
     * 示例:
     *
     * 输入: [0,1,0,3,12]
     * 输出: [1,3,12,0,0]
     * 说明:
     *
     * 必须在原数组上操作，不能拷贝额外的数组。
     * 尽量减少操作次数。
     */
    @Test
    public void testMoveZeroes() {
        int[] nums = {0,1,0,3,12};
        moveZeroes(nums);

        log.info("nums:{}",nums);
    }

    /**
     *  移动零
     * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
     *
     * 示例:
     *
     * 输入: [0,1,0,3,12]
     * 输出: [1,3,12,0,0]
     * 说明:
     *
     * 必须在原数组上操作，不能拷贝额外的数组。
     * 尽量减少操作次数。
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
     * 岛屿的个数
     * 给定一个由 '1'（陆地）和 '0'（水）组成的的二维网格，计算岛屿的数量。一个岛被水包围，并且它是通过水平方向或垂直方向上相邻的陆地连接而成的。
     * 你可以假设网格的四个边均被水包围。
     *
     * 示例 1:
     *
     * 输入:
     * 11110
     * 11010
     * 11000
     * 00000
     *
     * 输出: 1
     * 示例 2:
     *
     * 输入:
     * 11000
     * 11000
     * 00100
     * 00011
     *
     * 输出: 3
     */
    @Test
    public void testNumIslands() {
        //char[][] grid = {{'1','1','1','1','0'},{'1','1','0','1','0'},{'1','1','0','0','0'},{'0','0','0','0','0'}};
        char[][] grid = {{'1','1','0','0','0'},{'1','1','0','0','0'},{'0','0','1','0','0'},{'0','0','0','1','1'}};
        int result = numIslands(grid);
        log.info("result:{}",result);
    }


    /**
     * 岛屿的个数
     * 给定一个由 '1'（陆地）和 '0'（水）组成的的二维网格，计算岛屿的数量。一个岛被水包围，并且它是通过水平方向或垂直方向上相邻的陆地连接而成的。
     * 你可以假设网格的四个边均被水包围。
     *
     * 示例 1:
     *
     * 输入:
     * 11110
     * 11010
     * 11000
     * 00000
     *
     * 输出: 1
     * 示例 2:
     *
     * 输入:
     * 11000
     * 11000
     * 00100
     * 00011
     *
     * 输出: 3
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
                    updateAround(grid,i,j);
                }
            }
        }
        return result;
    }


    /**
     * 更新坐标row,col周围区域
     * @param grid
     * @param row
     * @param col
     */
    private void updateAround(char[][] grid,int row,int col) {

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
        updateAround(grid,row - 1,col);
        updateAround(grid,row + 1,col);
        updateAround(grid,row,col - 1);
        updateAround(grid,row ,col + 1);

    }

    @Test
    public void sortColors() {
        int[] nums = {2,0,2,1,1,0};
        sortColors(nums);
        log.debug("result:{}",nums);

    }

    /**
     * 颜色分类
     * 给定一个包含红色、白色和蓝色，一共 n 个元素的数组，原地对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。
     *
     * 此题中，我们使用整数 0、 1 和 2 分别表示红色、白色和蓝色。
     *
     * 注意:
     * 不能使用代码库中的排序函数来解决这道题。
     *
     * 示例:
     *
     * 输入: [2,0,2,1,1,0]
     * 输出: [0,0,1,1,2,2]
     * 进阶：
     *
     * 一个直观的解决方案是使用计数排序的两趟扫描算法。
     * 首先，迭代计算出0、1 和 2 元素的个数，然后按照0、1、2的排序，重写当前数组。
     * 你能想出一个仅使用常数空间的一趟扫描算法吗？
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
        int[] nums = {3,2,3,1,2,4,5,5,6};
        int k = 4;
        int result = findKthLargest(nums,k);
        log.debug("nums:{}",nums);
        log.debug("result:{}",result);
    }

    /**
     * 数组中的第K个最大元素
     * 在未排序的数组中找到第 k 个最大的元素。请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
     *
     * 示例 1:
     *
     * 输入: [3,2,1,5,6,4] 和 k = 2
     * 输出: 5
     * 示例 2:
     *
     * 输入: [3,2,3,1,2,4,5,5,6] 和 k = 4
     * 输出: 4
     * 说明:
     *
     * 你可以假设 k 总是有效的，且 1 ≤ k ≤ 数组的长度。
     * @param nums
     * @param k
     * @return
     */
    public int findKthLargest(int[] nums, int k) {

        //利用快速排序的思想，从数组 S 中随机找出一个元素 X，把数组分为两部分 Sa 和 Sb。Sa 中的元素大于等于 X，
        // Sb 中元素小于 X。这时有两种情况：

        //Sa 中元素的个数小于 k，则 Sb 中的第 k-|Sa| 个元素即为第k大数；
        //Sa 中元素的个数大于等于 k，则返回 Sa 中的第 k 大数。时间复杂度近似为 O(n)
        int len = nums.length;

        //Arrays.sort(nums);
        quickSort(nums,len - k ,0,len - 1);

        return nums[len - k];
    }

    /**
     * 快速排序
     * @param nums
     * @param k
     */
    private void quickSort(int[] nums, int k,int start,int end) {
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
        int i = start,j = end;
        while (i < j) {
            // j 递减，找到第一个 < tmp 的元素
            while (i < j && nums[j] >= tmp ) {
                j--;
            }
            nums[i] = nums[j];
            // i 递增，找到第一个 > tmp 的元素
            while (i < j && nums[i] <= tmp ) {
                i++;
            }
            nums[j] = nums[i];

        }
        // i 和 j 相遇，tmp 的位置 就是i ，左边小于 tmp 右边大于tmp
        nums[i] = tmp;

        index = i ;

        if (k < index) {
            //  k < index 搜索左数组
            quickSort(nums,k,start, index -1 );
        } else if (k > index) {
            //  k > index 搜索右数组
            quickSort(nums,k ,index + 1, end);
        }
        // k == index 结束


    }

    @Test
    public void isPalindrome() {
        String s = "race a car";
        boolean result = isPalindrome(s);
        log.debug("result:{}",result);
    }
    /**
     * 验证回文串
     * 给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。
     *
     * 说明：本题中，我们将空字符串定义为有效的回文串。
     *
     * 示例 1:
     *
     * 输入: "A man, a plan, a canal: Panama"
     * 输出: true
     * 示例 2:
     *
     * 输入: "race a car"
     * 输出: false
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
            if (Objects.isNull(start) || Objects.isNull(end) ) {
                if (Objects.isNull(start)) {
                    i++;
                }
                if (Objects.isNull(end) ) {
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
        log.debug("result:{}",result);
    }


    /**
     * 反转字符串中的元音字母
     * 编写一个函数，以字符串作为输入，反转该字符串中的元音字母。
     *
     * 示例 1:
     *
     * 输入: "hello"
     * 输出: "holle"
     * 示例 2:
     *
     * 输入: "leetcode"
     * 输出: "leotcede"
     * 说明:
     * 元音字母不包含字母"y"。
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
            case 'A' :
            case 'a' :
            case 'E' :
            case 'e' :
            case 'I' :
            case 'i' :
            case 'O' :
            case 'o' :
            case 'U' :
            case 'u' :
                return true;
            default:
                return false;
        }

    }


    @Test
    public void maxArea() {
        int[] height = {1,8,6,2,5,4,8,3,7};
        int result = maxArea(height);
        log.debug("result:{}",result);
    }


    /**
     * 盛最多水的容器
     * 给定 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0)。找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
     *
     * 说明：你不能倾斜容器，且 n 的值至少为 2。
     *
     *
     *
     * 图中垂直线代表输入数组 [1,8,6,2,5,4,8,3,7]。在此情况下，容器能够容纳水（表示为蓝色部分）的最大值为 49。
     *
     *
     *
     * 示例:
     *
     * 输入: [1,8,6,2,5,4,8,3,7]
     * 输出: 49
     * @param height
     * @return
     */
    public int maxArea(int[] height) {

        int size = height.length;

        int i = 0,j = size - 1;

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
        int[][] matrix = {{5, 1, 9,11},{2, 4, 8,10},{13, 3, 6, 7},{15,14,12,16}};
        log.debug("a:{}",matrix[0][2]);
        rotate(matrix);
        for (int[] a :matrix) {
            log.debug("result:{}",a);
        }

    }


    /**
     * 旋转图像
     * 给定一个 n × n 的二维矩阵表示一个图像。
     *
     * 将图像顺时针旋转 90 度。
     *
     * 说明：
     *
     * 你必须在原地旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要使用另一个矩阵来旋转图像。
     *
     * 示例 1:
     *
     * 给定 matrix =
     * [
     *   [1,2,3],
     *   [4,5,6],
     *   [7,8,9]
     * ],
     *
     * 原地旋转输入矩阵，使其变为:
     * [
     *   [7,4,1],
     *   [8,5,2],
     *   [9,6,3]
     * ]
     * 示例 2:
     *
     * 给定 matrix =
     * [
     *   [ 5, 1, 9,11],
     *   [ 2, 4, 8,10],
     *   [13, 3, 6, 7],
     *   [15,14,12,16]
     * ],
     *
     * 原地旋转输入矩阵，使其变为:
     * [
     *   [15,13, 2, 5],
     *   [14, 3, 4, 1],
     *   [12, 6, 8, 9],
     *   [16, 7,10,11]
     * ]
     * @param matrix
     */
    public void rotate(int[][] matrix) {

        int len = matrix.length;
        int rowNum = len/2;

        for (int i = 0; i <= rowNum; i++) {

            for (int j = i; j < len - i -1; j++) {
                // 旋转4次
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[len -j -1][i];
                matrix[len -j -1][i] = matrix[len - i -1][len -j -1];
                matrix[len - i -1][len -j -1] = matrix[j][len - i -1];
                matrix[j][len - i -1] = tmp;
            }

        }

    }

    @Test
    public void setZeroes() {
        int[][] matrix = {{1,0} };
        setZeroes(matrix);

        for (int[] a : matrix) {
            log.debug("result:{}",a);
        }
    }

    /**
     * 73. 矩阵置零
     * 给定一个 m x n 的矩阵，如果一个元素为 0，则将其所在行和列的所有元素都设为 0。请使用原地算法。
     *
     * 示例 1:
     *
     * 输入:
     * [
     *   [1,1,1],
     *   [1,0,1],
     *   [1,1,1]
     * ]
     * 输出:
     * [
     *   [1,0,1],
     *   [0,0,0],
     *   [1,0,1]
     * ]
     * 示例 2:
     *
     * 输入:
     * [
     *   [0,1,2,0],
     *   [3,4,5,2],
     *   [1,3,1,5]
     * ]
     * 输出:
     * [
     *   [0,0,0,0],
     *   [0,4,5,0],
     *   [0,3,1,0]
     * ]
     * 进阶:
     *
     * 一个直接的解决方案是使用  O(mn) 的额外空间，但这并不是一个好的解决方案。
     * 一个简单的改进方案是使用 O(m + n) 的额外空间，但这仍然不是最好的解决方案。
     * 你能想出一个常数空间的解决方案吗？
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
        int[] nums = {9,6,4,2,3,5,7,0,1};
        int result = missingNumber(nums);
        log.debug("result:{}",result);
    }

    /**
     * 缺失数字
     * 给定一个包含 0, 1, 2, ..., n 中 n 个数的序列，找出 0 .. n 中没有出现在序列中的那个数。
     *
     * 示例 1:
     *
     * 输入: [3,0,1]
     * 输出: 2
     * 示例 2:
     *
     * 输入: [9,6,4,2,3,5,7,0,1]
     * 输出: 8
     * 说明:
     * 你的算法应具有线性时间复杂度。你能否仅使用额外常数空间来实现?
     * @param nums
     * @return
     */
    public int missingNumber(int[] nums) {
        int len = nums.length;
        // 由于数组中只缺少了一个数字, 从0开始
        boolean[] arrNums = new boolean[len+1];

        for (int num:nums) {
            arrNums[num] = true;
        }

        for (int i = 0; i < arrNums.length; i++) {

            if (! arrNums[i]) {
                return i;
            }
        }

        return -1;
    }

    @Test
    public void searchMatrix () {
        int[][] nums = {{1,4,7,11,15},{2,5,8,12,19},{3,6,9,16,22},
            {10,13,14,17,24},{18,21,23,26,30}};
        int target = 18;
        boolean result = searchMatrix(nums,target);
        log.debug("result:{}",result);
    }

    /**
     * 搜索二维矩阵 II
     * 编写一个高效的算法来搜索 m x n 矩阵 matrix 中的一个目标值 target。该矩阵具有以下特性：
     *
     * 每行的元素从左到右升序排列。
     * 每列的元素从上到下升序排列。
     * 示例:
     *
     * 现有矩阵 matrix 如下：
     *
     * [
     *   [1,   4,  7, 11, 15],
     *   [2,   5,  8, 12, 19],
     *   [3,   6,  9, 16, 22],
     *   [10, 13, 14, 17, 24],
     *   [18, 21, 23, 26, 30]
     * ]
     * 给定 target = 5，返回 true。
     *
     * 给定 target = 20，返回 false。
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

        while (i < rows && i >= 0 && j < cols && j >= 0 ) {

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
    public void longestPalindrome( ) {

        String s = "aa";
        String result = longestPalindrome(s);
        log.debug("result:{}",result);


    }

    /**
     * 最长回文子串
     * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
     *
     * 示例 1：
     *
     * 输入: "babad"
     * 输出: "bab"
     * 注意: "aba" 也是一个有效答案。
     * 示例 2：
     *
     * 输入: "cbbd"
     * 输出: "bb"
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
        for (int i = 0; i < len ; i++) {
            // 选取left 和 right
            int left = i , right = i ;

            // 当右边元素 == 当前元素 right++
            while (right < len - 1 && chars[i] == chars[right + 1] ) {
                right++;
            }
            i = right;
            // 当右边元素 == 左边元素 left--; right++
            while (left > 0 && right < len - 1 && chars[left - 1] == chars[right + 1] )  {
                left--;
                right++;
            }
            int charLen = right - left + 1;
            if (charLen > result.length()) {
                result = s.substring(left,right + 1);
            }

        }


        return result;
    }


    @Test
    public void increasingTriplet( ) {

        int[] nums = {7,2,3,4,5};

        boolean result = increasingTriplet(nums);
        log.debug("result :{}",result);

    }


    /**
     * 递增的三元子序列
     * 给定一个未排序的数组，判断这个数组中是否存在长度为 3 的递增子序列。
     *
     * 数学表达式如下:
     *
     * 如果存在这样的 i, j, k,  且满足 0 ≤ i < j < k ≤ n-1，
     * 使得 arr[i] < arr[j] < arr[k] ，返回 true ; 否则返回 false 。
     * 说明: 要求算法的时间复杂度为 O(n)，空间复杂度为 O(1) 。
     *
     * 示例 1:
     *
     * 输入: [1,2,3,4,5]
     * 输出: true
     * 示例 2:
     *
     * 输入: [5,4,3,2,1]
     * 输出: false
     * @param nums
     * @return
     */
    public boolean increasingTriplet(int[] nums) {

        int len = nums.length;

        if (len < 3) {
            return false;
        }

        // 记录两个值 num1 和 num2 作为有序子序列的前两个数

        int num1 = nums[0],num2 = Integer.MAX_VALUE;
        int i = 1;
        while (i < len) {
            if (nums[i] < num1){
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
    public void numSubmatrixSumTarget( ) {

    }

    /**
     * 给出矩阵 matrix 和目标值 target，返回元素总和等于目标值的非空子矩阵的数量。
     *
     * 子矩阵 x1, y1, x2, y2 是满足 x1 <= x <= x2 且 y1 <= y <= y2 的所有单元 matrix[x][y] 的集合。
     *
     * 如果 (x1, y1, x2, y2) 和 (x1', y1', x2', y2') 两个子矩阵中部分坐标不同（如：x1 != x1'），那么这两个子矩阵也不同。
     *
     * 示例 1：
     *
     * 输入：matrix = [[0,1,0],[1,1,1],[0,1,0]], target = 0
     * 输出：4
     * 解释：四个只含 0 的 1x1 子矩阵。
     * 示例 2：
     *
     * 输入：matrix = [[1,-1],[-1,1]], target = 0
     * 输出：5
     * 解释：两个 1x2 子矩阵，加上两个 2x1 子矩阵，再加上一个 2x2 子矩阵。
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
                matrix[i][j] += matrix[i][j-1];
            }
        }
        // 这样一行中j，k两个之间的区间和可以表示为sum[i][j]-sum[i][k-1]，很快能求出。
        int result = 0;


        return result;
    }


    @Test
    public void merge( ) {
        int[][] intervals = new int[][]{{1,3},{2,6},{8,10},{15,18}};

        int[][] result = merge(intervals);
        log.debug("result:{}", result );
    }

    /**
     * 合并区间
     * 给出一个区间的集合，请合并所有重叠的区间。
     *
     * 示例 1:
     *
     * 输入: [[1,3],[2,6],[8,10],[15,18]]
     * 输出: [[1,6],[8,10],[15,18]]
     * 解释: 区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
     * 示例 2:
     *
     * 输入: [[1,4],[4,5]]
     * 输出: [[1,5]]
     * 解释: 区间 [1,4] 和 [4,5] 可被视为重叠区间。
     * @param intervals
     * @return
     */
    public int[][] merge(int[][] intervals) {


        Arrays.sort(intervals,new Comparator<int[]>(){
            @Override
            public int compare(int[] a,int[] b){
                return a[0]-b[0];
            }
        });
        int i = 0;
        int len = intervals.length;
        List<int[]> list = new ArrayList<>();
        while (i < len){
            int left = intervals[i][0];
            int right = intervals[i][1];
            while(i < len-1 && right >= intervals[i+1][0]){
                right = Math.max(right,intervals[i+1][1]);
                i++;
            }
            list.add(new int[] {left,right});
            i++;
        }
        return list.toArray(new int[list.size()][2]);
    }


    /**
     * Product of Array Except Self
     * 给定长度为 n 的整数数组 nums，其中 n > 1，返回输出数组 output ，其中 output[i] 等于 nums 中除 nums[i] 之外其余各元素的乘积。
     *
     * 示例:
     *
     * 输入: [1,2,3,4]
     * 输出: [24,12,8,6]
     * 说明: 请不要使用除法，且在 O(n) 时间复杂度内完成此题。
     *
     * 进阶：
     * 你可以在常数空间复杂度内完成这个题目吗？（ 出于对空间复杂度分析的目的，输出数组不被视为额外空间。）
     * @param nums
     * @return
     */

    public int[] productExceptSelf(int[] nums) {
        int len = nums.length;
        int[] output = new int[len];
        // 左边元素的乘积
        int k = 1;
        for(int i = 0; i < len; i++){
            output[i] = k;
            k = k * nums[i]; // 此时数组存储的是除去当前元素左边的元素乘积
        }

        // 右边元素的乘积
        k = 1;
        for(int i = len - 1; i >= 0; i--){
            output[i] = k;
            k = k * nums[i]; // 此时数组存储的是除去当前元素左边的元素乘积
        }

        return output;
    }


    @Test
    public void gameOfLife() {
        int[][] board = {{0,1,0},{0,0,1},{1,1,1},{0,0,0}};
        gameOfLife(board);
        for (int[] a : board) {
            log.debug("result:{}",a);
        }
    }

    /**
     * 生命游戏
     * 根据百度百科，生命游戏，简称为生命，是英国数学家约翰·何顿·康威在1970年发明的细胞自动机。
     *
     * 给定一个包含 m × n 个格子的面板，每一个格子都可以看成是一个细胞。每个细胞具有一个初始状态 live（1）即为活细胞，
     * 或 dead（0）即为死细胞。每个细胞与其八个相邻位置（水平，垂直，对角线）的细胞都遵循以下四条生存定律：
     *
     * 如果活细胞周围八个位置的活细胞数少于两个，则该位置活细胞死亡；
     * 如果活细胞周围八个位置有两个或三个活细胞，则该位置活细胞仍然存活；
     * 如果活细胞周围八个位置有超过三个活细胞，则该位置活细胞死亡；
     * 如果死细胞周围正好有三个活细胞，则该位置死细胞复活；
     * 根据当前状态，写一个函数来计算面板上细胞的下一个（一次更新后的）状态。下一个状态是通过将上述规则同时应用于当前状态下的每个细胞所形成的，
     * 其中细胞的出生和死亡是同时发生的。
     *
     * 示例:
     *
     * 输入:
     * [
     *   [0,1,0],
     *   [0,0,1],
     *   [1,1,1],
     *   [0,0,0]
     * ]
     * 输出:
     * [
     *   [0,0,0],
     *   [1,0,1],
     *   [0,1,1],
     *   [0,1,0]
     * ]
     * 进阶:
     *
     * 你可以使用原地算法解决本题吗？请注意，面板上所有格子需要同时被更新：你不能先更新某些格子，然后使用它们的更新后的值再更新其他格子。
     * 本题中，我们使用二维数组来表示面板。原则上，面板是无限的，但当活细胞侵占了面板边界时会造成问题。你将如何解决这些问题？
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

                updateCell(board,i,j);
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

    private void updateCell(int[][] board,int row,int col) {
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
        int[] nums = {3,4,-1,1};
        int result = firstMissingPositive(nums);
        log.debug("result:{}",result);
    }

    /**
     * 第一个缺失的正数
     * 给定一个未排序的整数数组，找出其中没有出现的最小的正整数。
     *
     * 示例 1:
     *
     * 输入: [1,2,0]
     * 输出: 3
     * 示例 2:
     *
     * 输入: [3,4,-1,1]
     * 输出: 2
     * 示例 3:
     *
     * 输入: [7,8,9,11,12]
     * 输出: 1
     * 说明:
     *
     * 你的算法的时间复杂度应为O(n)，并且只能使用常数级别的空间
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
        int len = n-k+1;
        int[] missNums = new int[len];
        for (int i = 0; i < n; i++) {
            int index = nums[i];
            if (index > 0 && index <= len) {
                missNums[index - 1] = index;
            }
        }
        for (int i = 0; i < missNums.length; i++) {
            if (missNums[i] != (i+1)) {
                return i+1;
            }
        }

        return len;
    }

    @Test
    public void maxSlidingWindow() {
        // [7,2,4]
        //2
        //int[] nums = {1,3,-1,-3,5,3,6,7};
        //int k = 3;
        int[] nums = {-6,-10,-7,-1,-9,9,-8,-4,10,-5,2,9,0,-7,7,4,-2,-10,8,7};
        int k = 7;
        int[] result = maxSlidingDynamic(nums,k);
        log.debug("result:{}",result);
    }

    /**
     * 滑动窗口最大值
     * 给定一个数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。
     *
     * 返回滑动窗口中的最大值。
     *
     *
     *
     * 示例:
     *
     * 输入: nums = [1,3,-1,-3,5,3,6,7], 和 k = 3
     * 输出: [3,3,5,5,6,7]
     * 解释:
     *
     *   滑动窗口的位置                最大值
     * ---------------               -----
     * [1  3  -1] -3  5  3  6  7       3
     *  1 [3  -1  -3] 5  3  6  7       3
     *  1  3 [-1  -3  5] 3  6  7       5
     *  1  3  -1 [-3  5  3] 6  7       5
     *  1  3  -1  -3 [5  3  6] 7       6
     *  1  3  -1  -3  5 [3  6  7]      7
     *
     *
     * 提示：
     *
     * 你可以假设 k 总是有效的，在输入数组不为空的情况下，1 ≤ k ≤ 输入数组的大小。
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
            if (i % k == 0) left[i] = nums[i];  // block_start
            else left[i] = Math.max(left[i - 1], nums[i]);

            // from right to left
            int j = len - i - 1;
            if ((j + 1) % k == 0) right[j] = nums[j];  // block_end
            else right[j] = Math.max(right[j + 1], nums[j]);
        }

        for (int i = 0; i < maxLen; i++) {
            result[i] = Math.max(left[i + k - 1],right[i]);
        }
        return result;
    }

    @Test
    public void testCalculate() {
        // log.debug("a:{}",3/2);

        String s = " 3/2 ";
        int result = calculate(s);
        log.debug("result:{}",result);
    }


    /**
     * 基本计算器 II
     * 实现一个基本的计算器来计算一个简单的字符串表达式的值。
     *
     * 字符串表达式仅包含非负整数，+， - ，*，/ 四种运算符和空格  。 整数除法仅保留整数部分。
     *
     * 示例 1:
     *
     * 输入: "3+2*2"
     * 输出: 7
     * 示例 2:
     *
     * 输入: " 3/2 "
     * 输出: 1
     * 示例 3:
     *
     * 输入: " 3+5 / 2 "
     * 输出: 5
     * 说明：
     *
     * 你可以假设所给定的表达式都是有效的。
     * 请不要使用内置的库函数 eval。
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
                    num = calculateNum(numDeque.removeLast(),num,calculateDeque.removeLast());
                }
                numDeque.addLast(num);
                calculateDeque.addLast(c);
                if (isMulOp(c)) {
                    isCal = true;
                } else if (isAndOp(c))  {
                    isCal = false;
                }
                num = 0;
            }
        }

        if (isCal) {
            // 先算 乘除
            num = calculateNum(numDeque.removeLast(),num,calculateDeque.removeLast());
        }
        numDeque.addLast(num);
        // 算加减法, 使用队列的特性

        result = numDeque.removeFirst();
        while (!numDeque.isEmpty()){
            result = calculateNum(result,numDeque.removeFirst(),calculateDeque.removeFirst());
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
            case '9': return true;
        }
        return false;
    }

    private int calculateNum(int a, int b , char c) {
        int result = 0;
        switch (c) {
            case '+': result = a + b;break;
            case '-': result = a - b;break;
            case '*': result = a * b;break;
            case '/': result = a / b;break;
        }
        return result;
    }

    @Test
    public void testCalculate2() {
        // log.debug("a:{}",3/2);

        String s = "(1-(3-4))";
        int result = calculate2(s);
        log.debug("result:{}",result);
    }


    /**
     * 实现一个基本的计算器来计算一个简单的字符串表达式的值。
     *
     * 字符串表达式可以包含左括号 ( ，右括号 )，加号 + ，减号 -，非负整数和空格  。
     *
     * 示例 1:
     *
     * 输入: "1 + 1"
     * 输出: 2
     * 示例 2:
     *
     * 输入: " 2-1 + 2 "
     * 输出: 3
     * 示例 3:
     *
     * 输入: "(1+(4+5+2)-3)+(6+8)"
     * 输出: 23
     * 说明：
     *
     * 你可以假设所给定的表达式都是有效的。
     * 请不要使用内置的库函数 eval。
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
                    if (cs == '('){
                        break;
                    }
                    deque2.addFirst(cs);
                    deque1.addFirst(numDeque.removeLast());
                }
                if (Objects.equals(lastChar,')')) {
                    deque1.addFirst(numDeque.removeLast());
                } else {
                    deque1.addLast(num);
                }

                num = deque1.removeFirst();
                while (!deque1.isEmpty()){
                    num = calculateNum(num,deque1.removeFirst(),deque2.removeFirst());
                }
                numDeque.addLast(num);
                num = 0;
                lastChar = ')';
            } else {

                if (lastChar == null) {
                    if (isCal) {
                        // 先算 乘除
                        num = calculateNum(numDeque.removeLast(),num,calculateDeque.removeLast());
                    }
                    numDeque.addLast(num);
                }
                calculateDeque.addLast(c);
                if (isMulOp(c)) {
                    isCal = true;
                } else if (isAndOp(c))  {
                    isCal = false;
                }
                num = 0;
            }

        }

        if (isCal) {
            // 先算 乘除
            num = calculateNum(numDeque.removeLast(),num,calculateDeque.removeLast());
        }
        if (lastChar == null) {
            numDeque.addLast(num);
        }

        // 算加减法, 使用队列的特性

        result = numDeque.removeFirst();
        while (!numDeque.isEmpty()){
            result = calculateNum(result,numDeque.removeFirst(),calculateDeque.removeFirst());
        }

        return result;
    }


    @Test
    public void floodFill() {
        int[][] image = {{1,1,1},{1,1,0},{1,0,1}};
        int sr = 1,   sc = 1;
        int newColor = 2;

        int[][] result =  floodFill(image,sr,sc,newColor);
        for (int[] a : result) {
            log.debug("result:{}",a);
        }
    }


    /**
     * 图像渲染
     * 有一幅以二维整数数组表示的图画，每一个整数表示该图画的像素值大小，数值在 0 到 65535 之间。
     *
     * 给你一个坐标 (sr, sc) 表示图像渲染开始的像素值（行 ，列）和一个新的颜色值 newColor，让你重新上色这幅图像。
     *
     * 为了完成上色工作，从初始坐标开始，记录初始坐标的上下左右四个方向上像素值与初始坐标相同的相连像素点，接着再记录这四个方向上符合条件的像素点与他们对应四个方向上像素值与初始坐标相同的相连像素点，……，重复该过程。将所有有记录的像素点的颜色值改为新的颜色值。
     *
     * 最后返回经过上色渲染后的图像。
     *
     * 示例 1:
     *
     * 输入:
     * image = [[1,1,1],[1,1,0],[1,0,1]]
     * sr = 1, sc = 1, newColor = 2
     * 输出: [[2,2,2],[2,2,0],[2,0,1]]
     * 解析:
     * 在图像的正中间，(坐标(sr,sc)=(1,1)),
     * 在路径上所有符合条件的像素点的颜色都被更改成2。
     * 注意，右下角的像素没有更改为2，
     * 因为它不是在上下左右四个方向上与初始点相连的像素点。
     * 注意:
     *
     * image 和 image[0] 的长度在范围 [1, 50] 内。
     * 给出的初始点将满足 0 <= sr < image.length 和 0 <= sc < image[0].length。
     * image[i][j] 和 newColor 表示的颜色值在范围 [0, 65535]内。
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
            floodFill(image,sr,sc,oldColor,newColor);
        }

        return image;
    }

    public void floodFill(int[][] image, int i, int j,int oldColor, int newColor) {
        if ( i < 0 || i >= image.length) {
            return;
        }
        if ( j < 0 || j >= image[0].length) {
            return;
        }
        if (image[i][j] == oldColor) {
            image[i][j] = newColor;
            floodFill(image,i - 1,j,oldColor,newColor);
            floodFill(image,i + 1,j,oldColor,newColor);
            floodFill(image,i,j - 1,oldColor,newColor);
            floodFill(image,i,j + 1,oldColor,newColor);
        }


    }


    @Test
    public void updateMatrix() {
        int[][] matrix = {{1,0,1,1,0,0,1,0,0,1}, {0,1,1,0,1,0,1,0,1,1}, {0,0,1,0,1,0,0,1,0,0}, {1,0,1,0,1,1,1,1,1,1},
                {0,1,0,1,1,0,0,0,0,1}, {0,0,1,0,1,1,1,0,1,0}, {0,1,0,1,0,1,0,0,1,1}, {1,0,0,0,1,1,1,1,0,1},
                {1,1,1,1,1,1,1,0,1,0}, {1,1,1,1,0,1,0,0,1,1}};
                //{{0,0,0},{0,1,0},{1,1,1}};

        int[][] result =  updateMatrix(matrix);
        for (int[] a : result) {
            log.debug("result:{}",a);
        }
    }
    /**
     * 01 矩阵
     * 给定一个由 0 和 1 组成的矩阵，找出每个元素到最近的 0 的距离。
     *
     * 两个相邻元素间的距离为 1 。
     *
     * 示例 1:
     * 输入:
     *
     * 0 0 0
     * 0 1 0
     * 0 0 0
     * 输出:
     *
     * 0 0 0
     * 0 1 0
     * 0 0 0
     * 示例 2:
     * 输入:
     *
     * 0 0 0
     * 0 1 0
     * 1 1 1
     * 输出:
     *
     * 0 0 0
     * 0 1 0
     * 1 2 1
     * 注意:
     *
     * 给定矩阵的元素个数不超过 10000。
     * 给定矩阵中至少有一个元素是 0。
     * 矩阵中的元素只在四个方向上相邻: 上、下、左、右。
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
                    result[i][j] = Math.min(result[i][j],result[i - 1][j] + 1);
                }
                if (j > 0) {
                    // 与 左边元素 比较
                    result[i][j] = Math.min(result[i][j],result[i][j - 1] + 1);
                }
            }
        }
        // 从右下向左上 遍历 每次与 下边元素 和 右边元素 比较
        for (int i = row - 1; i >= 0; i--) {
            for (int j = col - 1; j >= 0; j--) {
                if (i < row - 1) {
                    // 与 下边元素 比较
                    result[i][j] = Math.min(result[i][j],result[i + 1][j] + 1);
                }
                if (j < col - 1) {
                    // 与 右边元素 比较
                    result[i][j] = Math.min(result[i][j],result[i][j + 1] + 1);
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

    private void updateMatrix(int[][] result, int i,int j,int value) {
        if ( i < 0 || i >= result.length) {
            return;
        }
        if ( j < 0 || j >= result[0].length) {
            return;
        }
        if (value < result[i][j]) {
            result[i][j] = value;
            updateMatrix(result,i - 1,j,value + 1);
            updateMatrix(result,i + 1,j,value + 1);
            updateMatrix(result,i,j - 1,value + 1);
            updateMatrix(result,i,j + 1,value + 1);
        }
    }

    @Test
    public void canVisitAllRooms() {
        List<List<Integer>> rooms = new ArrayList<>();
        rooms.add(Arrays.asList(6,7,8));
        rooms.add(Arrays.asList(5,4,9));
        rooms.add(new ArrayList<>());
        rooms.add(Arrays.asList(8));
        rooms.add(Arrays.asList(4));
        rooms.add(new ArrayList<>());
        rooms.add(Arrays.asList(1,9,2,3));
        rooms.add(Arrays.asList(7));
        rooms.add(Arrays.asList(6,5));
        rooms.add(Arrays.asList(2,3,1));
        /*rooms.add(Arrays.asList(1,3));
        rooms.add(Arrays.asList(3,0,1));
        rooms.add(Arrays.asList(2));
        rooms.add(Arrays.asList(0));*/
        //rooms.add(new ArrayList<>());
        boolean result = canVisitAllRooms(rooms);
        log.debug("result:{}",result);
    }

    /**
     * 钥匙和房间
     * 有 N 个房间，开始时你位于 0 号房间。每个房间有不同的号码：0，1，2，...，N-1，并且房间里可能有一些钥匙能使你进入下一个房间。
     *
     * 在形式上，对于每个房间 i 都有一个钥匙列表 rooms[i]，每个钥匙 rooms[i][j] 由 [0,1，...，N-1] 中的一个整数表示，其中 N = rooms.length。 钥匙 rooms[i][j] = v 可以打开编号为 v 的房间。
     *
     * 最初，除 0 号房间外的其余所有房间都被锁住。
     *
     * 你可以自由地在房间之间来回走动。
     *
     * 如果能进入每个房间返回 true，否则返回 false。
     *
     * 示例 1：
     *
     * 输入: [[1],[2],[3],[]]
     * 输出: true
     * 解释:
     * 我们从 0 号房间开始，拿到钥匙 1。
     * 之后我们去 1 号房间，拿到钥匙 2。
     * 然后我们去 2 号房间，拿到钥匙 3。
     * 最后我们去了 3 号房间。
     * 由于我们能够进入每个房间，我们返回 true。
     * 示例 2：
     *
     * 输入：[[1,3],[3,0,1],[2],[0]]
     * 输出：false
     * 解释：我们不能进入 2 号房间。
     * 提示：
     *
     * 1 <= rooms.length <= 1000
     * 0 <= rooms[i].length <= 1000
     * 所有房间中的钥匙数量总计不超过 3000。
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
        int[] nums = {-2,0,-1};
        int result = maxProduct(nums);
        log.debug("result:{}",result);

    }


    /**
     * 乘积最大子序列
     * 给定一个整数数组 nums ，找出一个序列中乘积最大的连续子序列（该序列至少包含一个数）。
     *
     * 示例 1:
     *
     * 输入: [2,3,-2,4]
     * 输出: 6
     * 解释: 子数组 [2,3] 有最大乘积 6。
     * 示例 2:
     *
     * 输入: [-2,0,-1]
     * 输出: 0
     * 解释: 结果不能为 2, 因为 [-2,-1] 不是子数组。
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
            maxNums[i] = Math.max(maxNums[i-1]*num,num);
            minNums[i] = Math.min(minNums[i-1]*num,num);
            if (num < 0) {
                // 如果最小值是个负数
                if (minNums[i-1] < 0) {
                    maxNums[i] = Math.max(minNums[i-1]*num,maxNums[i]);
                }
                // 如果最大值是个整数
                if (maxNums[i-1] > 0) {
                    minNums[i] = Math.min(maxNums[i-1]*num,minNums[i]);
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
            log.debug("result:{}",a);
        }
    }

    /**
     * 螺旋矩阵 II
     * 给定一个正整数 n，生成一个包含 1 到 n2 所有元素，且元素按顺时针顺序螺旋排列的正方形矩阵。
     *
     * 示例:
     *
     * 输入: 3
     * 输出:
     * [
     *  [ 1, 2, 3 ],
     *  [ 8, 9, 4 ],
     *  [ 7, 6, 5 ]
     * ]
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
                    yMin = ++y ;

                    moveType = 2;

                }
            } else if (moveType == 2) {
                y++;
                // 从上至下 移动到最下边时 xMax = x 方向改变
                if (y > yMax) {
                    y = yMax;
                    xMax = --x  ;
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
            } else  {
                y--;
                // 从下至上 移动到最上边时 xMin = x+1  方向改变
                if (y < yMin) {
                    y = yMin;
                    xMin = ++x ;
                    moveType = 1;

                }
            }


        }
        return matrix;
    }

    @Test
    public void threeSumClosest() {
        int[] nums = {-1,1,1,-4};
        int target = 1;
        int result = threeSumClosest(nums,target);
        log.debug("result : {}",result);
    }


    /**
     *  最接近的三数之和
     * 给定一个包括 n 个整数的数组 nums 和 一个目标值 target。找出 nums 中的三个整数，使得它们的和与 target 最接近。返回这三个数的和。假定每组输入只存在唯一答案。
     *
     * 例如，给定数组 nums = [-1，2，1，-4], 和 target = 1.
     *
     * 与 target 最接近的三个数的和为 2. (-1 + 2 + 1 = 2).
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
            result = Integer.MAX_VALUE ;
        } else {
            result = Integer.MAX_VALUE + target;
        }
        // 思路：先排序，然后选择一个主元，然后使用双指针遍历剩下的元素
        Arrays.sort(nums);

        for (int i = 0 ; i < nums.length - 2; i++) {
            // 选择 i 位置 做主元
            int num = nums[i];
            int tar = target - num;
            int left = i + 1 , right = nums.length - 1;

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
        int[][] grid = {{0,0,1,0,0,0,0,1,0,0,0,0,0},
                {0,0,0,0,0,0,0,1,1,1,0,0,0},
                {0,1,1,0,1,0,0,0,0,0,0,0,0},
                {0,1,0,0,1,1,0,0,1,0,1,0,0},
                {0,1,0,0,1,1,0,0,1,1,1,0,0},
                {0,0,0,0,0,0,0,0,0,0,1,0,0},
                {0,0,0,0,0,0,0,1,1,1,0,0,0},
                {0,0,0,0,0,0,0,1,1,0,0,0,0}};
        int result = maxAreaOfIsland(grid);
        log.debug("result:{}",result);
    }

    /**
     * 岛屿的最大面积
     * 给定一个包含了一些 0 和 1的非空二维数组 grid , 一个 岛屿 是由四个方向 (水平或垂直) 的 1 (代表土地) 构成的组合。你可以假设二维矩阵的四个边缘都被水包围着。
     *
     * 找到给定的二维数组中最大的岛屿面积。(如果没有岛屿，则返回面积为0。)
     *
     * 示例 1:
     *
     * [[0,0,1,0,0,0,0,1,0,0,0,0,0],
     *  [0,0,0,0,0,0,0,1,1,1,0,0,0],
     *  [0,1,1,0,1,0,0,0,0,0,0,0,0],
     *  [0,1,0,0,1,1,0,0,1,0,1,0,0],
     *  [0,1,0,0,1,1,0,0,1,1,1,0,0],
     *  [0,0,0,0,0,0,0,0,0,0,1,0,0],
     *  [0,0,0,0,0,0,0,1,1,1,0,0,0],
     *  [0,0,0,0,0,0,0,1,1,0,0,0,0]]
     * 对于上面这个给定矩阵应返回 6。注意答案不应该是11，因为岛屿只能包含水平或垂直的四个方向的‘1’。
     *
     * 示例 2:
     *
     * [[0,0,0,0,0,0,0,0]]
     * 对于上面这个给定的矩阵, 返回 0。
     *
     * 注意: 给定的矩阵grid 的长度和宽度都不超过 50。
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

                    int area = updateAround(grid,i,j);
                    if (area > maxArea) {
                        maxArea = area;
                    }
                }
            }
        }
        return maxArea;
    }

    private int updateAround(int[][] grid,int row,int col) {

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
        area += updateAround(grid,row - 1,col);
        area += updateAround(grid,row + 1,col);
        area += updateAround(grid,row,col - 1);
        area += updateAround(grid,row ,col + 1);

        return area;
    }


    @Test
    public void findLengthOfLCIS() {
        int[] nums = {2,2,2,2,2};
        int result = findLengthOfLCIS(nums);
        log.debug("result:{}",result);
    }

    /**
     * 最长连续递增序列
     * 给定一个未经排序的整数数组，找到最长且连续的的递增序列。
     *
     * 示例 1:
     *
     * 输入: [1,3,5,4,7]
     * 输出: 3
     * 解释: 最长连续递增序列是 [1,3,5], 长度为3。
     * 尽管 [1,3,5,7] 也是升序的子序列, 但它不是连续的，因为5和7在原数组里被4隔开。
     * 示例 2:
     *
     * 输入: [2,2,2,2,2]
     * 输出: 1
     * 解释: 最长连续递增序列是 [2], 长度为1。
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

            if (nums[i] > nums[i-1]) {
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
        log.debug("result:{}",result);
    }

    /**
     * 最长连续序列
     * 给定一个未排序的整数数组，找出最长连续序列的长度。
     *
     * 要求算法的时间复杂度为 O(n)。
     *
     * 示例:
     *
     * 输入: [100, 4, 200, 1, 3, 2]
     * 输出: 4
     * 解释: 最长连续序列是 [1, 2, 3, 4]。它的长度为 4。
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
            if (!set.contains(num-1)) {
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
        int n = 2,k = 2;
        String result = getPermutation(n,k);
        log.debug("result:{}",result);
    }
    /**
     * 第k个排列
     * 给出集合 [1,2,3,…,n]，其所有元素共有 n! 种排列。
     *
     * 按大小顺序列出所有排列情况，并一一标记，当 n = 3 时, 所有排列如下：
     *
     * "123"
     * "132"
     * "213"
     * "231"
     * "312"
     * "321"
     * 给定 n 和 k，返回第 k 个排列。
     *
     * 说明：
     *
     * 给定 n 的范围是 [1, 9]。
     * 给定 k 的范围是[1,  n!]。
     * 示例 1:
     *
     * 输入: n = 3, k = 3
     * 输出: "213"
     * 示例 2:
     *
     * 输入: n = 4, k = 9
     * 输出: "2314"
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
                index = (k - 1)/len;
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
        log.debug("result:{}",result);
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
        int[] height = {5,2,1,2,1,5};
        int result = trap(height);
        log.debug("result:{}",result);
    }


    /**
     * 接雨水
     * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
     * 上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。 感谢 Marcos 贡献此图。
     *
     * 示例:
     *
     * 输入: [0,1,0,2,1,0,1,3,2,1,2,1]
     * 输出: 6
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
        for (int i = 1; i < len;i++) {
            leftHeight[i] = Math.max(height[i],leftHeight[i-1]);
        }
        int[] rightHeight = new int[len];
        rightHeight[len - 1] = height[len - 1];
        for (int i = len - 2; i > 0 ;i--) {
            rightHeight[i] = Math.max(height[i],rightHeight[i+1]);
        }



        int result = 0;
        for (int i = 1; i < len - 1 ;i++) {
            result += Math.min(leftHeight[i],rightHeight[i]) - height[i];
        }

        return result;
    }

    @Test
    public void findCircleNum() {
        int[][] M = {{1,1,0},{1,1,0},{0,0,1}};
        int result = findCircleNum(M);
        log.debug("result:{}",result);
    }
    /**
     * 朋友圈
     * 班上有 N 名学生。其中有些人是朋友，有些则不是。他们的友谊具有是传递性。如果已知 A 是 B 的朋友，B 是 C 的朋友，那么我们可以认为 A 也是 C 的朋友。所谓的朋友圈，是指所有朋友的集合。
     *
     * 给定一个 N * N 的矩阵 M，表示班级中学生之间的朋友关系。如果M[i][j] = 1，表示已知第 i 个和 j 个学生互为朋友关系，否则为不知道。你必须输出所有学生中的已知的朋友圈总数。
     *
     * 示例 1:
     *
     * 输入:
     * [[1,1,0],
     *  [1,1,0],
     *  [0,0,1]]
     * 输出: 2
     * 说明：已知学生0和学生1互为朋友，他们在一个朋友圈。
     * 第2个学生自己在一个朋友圈。所以返回2。
     * 示例 2:
     *
     * 输入:
     * [[1,1,0],
     *  [1,1,1],
     *  [0,1,1]]
     * 输出: 1
     * 说明：已知学生0和学生1互为朋友，学生1和学生2互为朋友，所以学生0和学生2也是朋友，所以他们三个在一个朋友圈，返回1。
     * 注意：
     *
     * N 在[1,200]的范围内。
     * 对于所有学生，有M[i][i] = 1。
     * 如果有M[i][j] = 1，则有M[j][i] = 1。
     * @param M
     * @return
     */
    public int findCircleNum(int[][] M) {
        int result = 0;
        int len = M.length;
        boolean[] visited = new boolean[len];
        // 深度优先遍历
        for (int i = 0; i < len ; i++) {
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
        int[] data = {115,100,102,231,154,132,13,10};
        boolean result = validUtf8(data);
        log.debug("result:{}",result);
    }


    /**
     * UTF-8 编码验证
     * UTF-8 中的一个字符可能的长度为 1 到 4 字节，遵循以下的规则：
     *
     * 对于 1 字节的字符，字节的第一位设为0，后面7位为这个符号的unicode码。
     * 对于 n 字节的字符 (n > 1)，第一个字节的前 n 位都设为1，第 n+1 位设为0，
     * 后面字节的前两位一律设为10。剩下的没有提及的二进制位，全部为这个符号的unicode码。
     * 这是 UTF-8 编码的工作方式：
     *
     *    Char. number range  |        UTF-8 octet sequence
     *       (hexadecimal)    |              (binary)
     *    --------------------+---------------------------------------------
     *    0000 0000-0000 007F | 0xxxxxxx
     *    0000 0080-0000 07FF | 110xxxxx 10xxxxxx
     *    0000 0800-0000 FFFF | 1110xxxx 10xxxxxx 10xxxxxx
     *    0001 0000-0010 FFFF | 11110xxx 10xxxxxx 10xxxxxx 10xxxxxx
     * 给定一个表示数据的整数数组，返回它是否为有效的 utf-8 编码。
     *
     * 注意:
     * 输入是整数数组。只有每个整数的最低 8 个有效位用来存储数据。这意味着每个整数只表示 1 字节的数据。
     *
     * 示例 1:
     *
     * data = [197, 130, 1], 表示 8 位的序列: 11000101 10000010 00000001.
     *
     * 返回 true 。
     * 这是有效的 utf-8 编码，为一个2字节字符，跟着一个1字节字符。
     * 示例 2:
     *
     * data = [235, 140, 4], 表示 8 位的序列: 11101011 10001100 00000100.
     *
     * 返回 false 。
     * 前 3 位都是 1 ，第 4 位为 0 表示它是一个3字节字符。
     * 下一个字节是开头为 10 的延续字节，这是正确的。
     * 但第二个延续字节不以 10 开头，所以是不符合规则的。
     * @param data
     * @return
     */
    public boolean validUtf8(int[] data) {
        int len = data.length;
        if (len == 0) {
            return false;
        }
        for (int i = 0; i < len; i++) {
            if (data[i] >= (1<<8) || data[i] < 0 ) {
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
            if (start + byteCount > len ) {
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
                    log.debug("start:{}",start);
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
        log.debug("a:{}",getByteCount(115));
    }

    private int getByteCount(int data) {

        int mask = 1 << 7;
        int count = 0;
        // 判断是否 2 ~ 4 个字节
        //log.debug("a:{},b:{}",mask,(mask & data) );
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
        //log.debug("count:{}",count);
        // 判断是否 1 个字节
        return count == 0 ? 1 : count;
    }

    @Test
    public void sumSubarrayMins() {
        int[] A = {3,1,2,4};
        int result = sumSubarrayMins(A);
        log.debug("result:{}",result);
    }

    /**
     * 子数组的最小值之和
     * 给定一个整数数组 A，找到 min(B) 的总和，其中 B 的范围为 A 的每个（连续）子数组。
     *
     * 由于答案可能很大，因此返回答案模 10^9 + 7。
     *
     *
     *
     * 示例：
     *
     * 输入：[3,1,2,4]
     * 输出：17
     * 解释：
     * 子数组为 [3]，[1]，[2]，[4]，[3,1]，[1,2]，[2,4]，[3,1,2]，[1,2,4]，[3,1,2,4]。
     * 最小值为 3，1，2，4，1，1，2，1，1，1，和为 17。
     *
     *
     * 提示：
     *
     * 1 <= A <= 30000
     * 1 <= A[i] <= 30000
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
        int[][] buildings = {{0,2,3},{2,5,4}};//{ {2,9,10}, {3,7,15}, {5,12,12}, {15,20,10}, {19,24,8} };
        List<List<Integer>> result = getSkyline(buildings);
        for (List<Integer> list : result) {
            log.debug("result:{}",list);
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
     * 天际线问题
     * 城市的天际线是从远处观看该城市中所有建筑物形成的轮廓的外部轮廓。现在，假设您获得了城市风光照片（图A）上显示的所有建筑物的位置和高度，
     * 请编写一个程序以输出由这些建筑物形成的天际线（图B）。
     *
     * Buildings  Skyline Contour
     *
     * 每个建筑物的几何信息用三元组 [Li，Ri，Hi] 表示，其中 Li 和 Ri 分别是第 i 座建筑物左右边缘的 x 坐标，Hi 是其高度。
     * 可以保证 0 ≤ Li, Ri ≤ INT_MAX, 0 < Hi ≤ INT_MAX 和 Ri - Li > 0。您可以假设所有建筑物都是在绝对平坦且高度为 0 的表面上的完美矩形。
     *
     * 例如，图A中所有建筑物的尺寸记录为：[ [2 9 10], [3 7 15], [5 12 12], [15 20 10], [19 24 8] ] 。
     *
     * 输出是以 [ [x1,y1], [x2, y2], [x3, y3], ... ] 格式的“关键点”（图B中的红点）的列表，它们唯一地定义了天际线。
     * 关键点是水平线段的左端点。请注意，最右侧建筑物的最后一个关键点仅用于标记天际线的终点，并始终为零高度。
     * 此外，任何两个相邻建筑物之间的地面都应被视为天际线轮廓的一部分。
     *
     * 例如，图B中的天际线应该表示为：[ [2 10], [3 15], [7 12], [12 0], [15 10], [20 8], [24, 0] ]。
     *
     * 说明:
     *
     * 任何输入列表中的建筑物数量保证在 [0, 10000] 范围内。
     * 输入列表已经按左 x 坐标 Li  进行升序排列。
     * 输出列表必须按 x 位排序。
     * 输出天际线中不得有连续的相同高度的水平线。例如 [...[2 3], [4 5], [7 5], [11 5], [12 7]...] 是不正确的答案；
     * 三条高度为 5 的线应该在最终输出中合并为一个：[...[2 3], [4 5], [12 7], ...]
     * @param buildings
     * @return
     */
    public List<List<Integer>> getSkyline(int[][] buildings) {

        /**
         *  vector<pair<int,int>> h;
         *         multiset<int> m;
         *         vector<vector<int>> res;
         *
         *         //1、将每一个建筑分成“两个部分”，例如:[2,9,10]可以转换成[2，-10][9,10]，我们用负值来表示左边界
         *         for(const auto& b:buildings)
         *         {
         *             h.push_back({b[0], -b[2]});
         *             h.push_back({b[1], b[2]});
         *         }
         *
         *         //2、根据x值对分段进行排序
         *         sort(h.begin(),h.end());
         *         int prev = 0, cur = 0;
         *         m.insert(0);
         *
         *         3、遍历
         *         for (auto i:h)
         *         {
         *             if (i.second < 0) m.insert(-i.second);  //左端点，高度入堆
         *             else m.erase(m.find(i.second));         //右端点，高度出堆
         *             cur = *m.rbegin();                      //当前最大高度高度
         *             if (cur != prev) {                      //当前最大高度不等于最大高度perv表示这是一个转折点
         *                 res.push_back({i.first, cur});      //添加坐标
         *                 prev = cur;                         //更新最大高度
         *             }
         *         }
         *         return res;
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
        Map<Integer,List<Integer>> map = new TreeMap<>();
        for (int[] building : buildings) {
            // 用 负数表示左节点
            if (!map.containsKey(building[0])) {
                map.put(building[0],new ArrayList<>());
            }
            map.get(building[0]).add(-building[2]);
            // 右边节点
            if (!map.containsKey(building[1])) {
                map.put(building[1],new ArrayList<>());
            }
            map.get(building[1]).add(building[2]);
        }
        int maxHeight = 0,lastHeight = 0;
        // 优先队列，构造最大堆
        Queue<Integer> heightQueue = new PriorityQueue<Integer>((Integer a,Integer b) ->  b - a);
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
        int[] nums = {7,2,5,10,8} ;
        int m = 2;
        int result = splitArray(nums,m);
        log.debug("result:{}",result);
    }

    /**
     * 分割数组的最大值
     * 给定一个非负整数数组和一个整数 m，你需要将这个数组分成 m 个非空的连续子数组。设计一个算法使得这 m 个子数组各自和的最大值最小。
     *
     * 注意:
     * 数组长度 n 满足以下条件:
     *
     * 1 ≤ n ≤ 1000
     * 1 ≤ m ≤ min(50, n)
     * 示例:
     *
     * 输入:
     * nums = [7,2,5,10,8]
     * m = 2
     *
     * 输出:
     * 18
     *
     * 解释:
     * 一共有四种方法将nums分割为2个子数组。
     * 其中最好的方式是将其分为[7,2,5] 和 [10,8]，
     * 因为此时这两个子数组各自的和的最大值为18，在所有情况中最小。
     * @param nums
     * @param m
     * @return
     */
    public int splitArray(int[] nums, int m) {

        int max = 0,sum = 0;
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
        int l = max,  h = sum;


        while (l < h) {
            int mid = l + (h - l)/2;
            int tmp = 0;
            int count = 1;
            // 假设 当前数组 的 子数组的最大值 是 mid
            // 遍历 nums 数组，计算超过mid的子数组的个数，个数 < m 说明 mid偏大，
            // 当数组之和 超过 mid count++
            for(int num:nums) {
                tmp += num;
                if(tmp > mid) {
                    tmp = num;
                    count++;
                }
            }
            if(count > m) {
                l = mid + 1;
            } else {
                h = mid;
            }
        }

        return l;
    }


    @Test
    public void kthSmallest() {
        int[][] matrix ={{1,  5,  9},{10, 11, 13},{12, 13, 15}};
        int k = 8;
        int result = kthSmallest2(matrix,k);
        log.debug("result:{}",result);
    }
    /**
     * 有序矩阵中第K小的元素
     * 给定一个 n x n 矩阵，其中每行和每列元素均按升序排序，找到矩阵中第k小的元素。
     * 请注意，它是排序后的第k小元素，而不是第k个元素。
     *
     * 示例:
     *
     * matrix = [
     *    [ 1,  5,  9],
     *    [10, 11, 13],
     *    [12, 13, 15]
     * ],
     * k = 8,
     *
     * 返回 13。
     * 说明:
     * 你可以假设 k 的值永远是有效的, 1 ≤ k ≤ n2 。
     * @param matrix
     * @param k
     * @return
     */
    public int kthSmallest(int[][] matrix, int k) {

        int result = 0;
        // 最小值low在左上角,最大值high在右下角，那么要找的元素区间在[low,high]。
        // mid=(low+high)/2,如果小于等于mid的数量小于k，则可以pass掉小于等于mid的值，即要找的元素一定大于mid，则low=mid+1。
        // 这样每次折半，时间复杂度是log(high-low)，由于32位的int值，其最大log为32(因为除以2相当于右移1位)
        //矩阵里查找小于某个值的数量，时间复杂度最小可以是O(N+N), 即O(N)
        //所以总体时间复杂度是log(high-low)*O(N)，32算常数，即O(N)。
        int rows = matrix.length;
        if (rows == 0) {
            return 0;
        }
        int cols = matrix[0].length;
        int low = matrix[0][0], high = matrix[rows-1][cols-1];
        /*while (low < high) {
            int mid = low + (high-low) >> 1;
            int index =
        }*/

        //return result;
        PriorityQueue<Integer> queue = new PriorityQueue<>((a,b)-> b - a);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {

                queue.offer(matrix[i][j]);
                if (queue.size() > k)  {
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

        //return result;
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
        int low = matrix[0][0], high = matrix[rows-1][cols-1];
        while (low < high) {
            int mid = low + (high-low) / 2;
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
        int[] heights = {2,1,2,1,5,1};
        int result = largestRectangleArea(heights);
        log.debug("result:{}",result);

    }


    /**
     * 柱状图中最大的矩形
     * 给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
     *
     * 求在该柱状图中，能够勾勒出来的矩形的最大面积。
     *
     * 以上是柱状图的示例，其中每个柱子的宽度为 1，给定的高度为 [2,1,5,6,2,3]。
     *
     * 图中阴影部分为所能勾勒出的最大矩形面积，其面积为 10 个单位。
     *
     * 示例:
     *
     * 输入: [2,1,5,6,2,3]
     * 输出: 10
     * @param heights
     * @return
     */
    public int largestRectangleArea(int[] heights) {
        Stack < Integer > stack = new Stack < > ();
        stack.push(-1);
        int maxarea = 0;
        for (int i = 0; i < heights.length; ++i) {
            while (stack.peek() != -1 && heights[stack.peek()] >= heights[i])
                maxarea = Math.max(maxarea, heights[stack.pop()] * (i - stack.peek() - 1));
            stack.push(i);
        }
        log.debug("stack:{}",stack);
        log.debug("maxarea:{}",maxarea);
        while (stack.peek() != -1)
            maxarea = Math.max(maxarea, heights[stack.pop()] * (heights.length - stack.peek() -1));
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
                *//*minHeight = heights[indexDeque.peekLast()];
                minIndex = indexDeque.pollLast();
                int area = minHeight * (i - minIndex);
                if (area > maxArea) {
                    maxArea = area;
                }*//*
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
        int[][] people = {{7,0}, {4,4}, {7,1}, {5,0}, {6,1}, {5,2}};
        int[][] result = reconstructQueue(people);
        for (int[] a : result) {
            log.debug("a:{}",a);
        }
    }
    /**
     * Queue Reconstruction by Height
     * 假设有打乱顺序的一群人站成一个队列。 每个人由一个整数对(h, k)表示，其中h是这个人的身高，k是排在这个人前面且身高大于或等于h的人数。
     * 编写一个算法来重建这个队列。
     *
     * 注意：
     * 总人数少于1100人。
     *
     * 示例
     *
     * 输入:
     * [[7,0], [4,4], [7,1], [5,0], [6,1], [5,2]]
     *
     * 输出:
     * [[5,0], [7,0], [5,2], [6,1], [4,4], [7,1]]
     * @param people
     * @return
     */
    public int[][] reconstructQueue(int[][] people) {
        int len = people.length;
        Arrays.sort(people, (a,b) -> a[0] == b[0] ? a[1] - b[1] : b[0] - a[0] );
        // 按 身高 高 -> 低 排序 k 表示
        List<int []> list = new LinkedList<>();
        for (int[] person : people) {

            list.add(person[1],person);
        }
        int[][] result = new int[len][2];
        list.toArray(result);

        return result;
    }

    @Test
    public void minDeletionSize() {
        String[] a = {"cba", "daf", "ghi"};
        logResult( minDeletionSize(a));
    }

    /**
     * 删列造序
     * 给定由 N 个小写字母字符串组成的数组 A，其中每个字符串长度相等。
     *
     * 删除 操作的定义是：选出一组要删掉的列，删去 A 中对应列中的所有字符，形式上，第 n 列为 [A[0][n], A[1][n], ..., A[A.length-1][n]]）。
     *
     * 比如，有 A = ["abcdef", "uvwxyz"]，
     *
     *
     *
     * 要删掉的列为 {0, 2, 3}，删除后 A 为["bef", "vyz"]， A 的列分别为["b","v"], ["e","y"], ["f","z"]。
     *
     *
     *
     * 你需要选出一组要删掉的列 D，对 A 执行删除操作，使 A 中剩余的每一列都是 非降序 排列的，然后请你返回 D.length 的最小可能值。
     *
     *
     *
     * 示例 1：
     *
     * 输入：["cba", "daf", "ghi"]
     * 输出：1
     * 解释：
     * 当选择 D = {1}，删除后 A 的列为：["c","d","g"] 和 ["a","f","i"]，均为非降序排列。
     * 若选择 D = {}，那么 A 的列 ["b","a","h"] 就不是非降序排列了。
     * 示例 2：
     *
     * 输入：["a", "b"]
     * 输出：0
     * 解释：D = {}
     * 示例 3：
     *
     * 输入：["zyx", "wvu", "tsr"]
     * 输出：3
     * 解释：D = {0, 1, 2}
     *
     *
     * 提示：
     *
     * 1 <= A.length <= 100
     * 1 <= A[i].length <= 1000
     * @param A
     * @return
     */
    public int minDeletionSize(String[] A) {
        int rows = A.length;

        int result = 0;
        int len = A[0].length();
        for (int i = 0; i < len; i++) {
             for (int j = 1; j < rows; j++) {
                 if (A[j-1].charAt(i) > A[j].charAt(i)) {
                     result++;
                     break;
                 }
             }

        }
        return result;
    }

    @Test
    public void minDeletionSize2() {
        String[] a = { "xga","xfb","yfa" };
        logResult( minDeletionSize2(a));
    }

    /**
     * 给定由 N 个小写字母字符串组成的数组 A，其中每个字符串长度相等。
     *
     * 选取一个删除索引序列，对于 A 中的每个字符串，删除对应每个索引处的字符。
     *
     * 比如，有 A = ["abcdef", "uvwxyz"]，删除索引序列 {0, 2, 3}，删除后 A 为["bef", "vyz"]。
     *
     * 假设，我们选择了一组删除索引 D，那么在执行删除操作之后，最终得到的数组的元素是按 字典序（A[0] <= A[1] <= A[2] ... <= A[A.length - 1]）排列的，然后请你返回 D.length 的最小可能值。
     *
     *  
     *
     * 示例 1：
     *
     * 输入：["ca","bb","ac"]
     * 输出：1
     * 解释：
     * 删除第一列后，A = ["a", "b", "c"]。
     * 现在 A 中元素是按字典排列的 (即，A[0] <= A[1] <= A[2])。
     * 我们至少需要进行 1 次删除，因为最初 A 不是按字典序排列的，所以答案是 1。
     *
     * 示例 2：
     *
     * 输入：["xc","yb","za"]
     * 输出：0
     * 解释：
     * A 的列已经是按字典序排列了，所以我们不需要删除任何东西。
     * 注意 A 的行不需要按字典序排列。
     * 也就是说，A[0][0] <= A[0][1] <= ... 不一定成立。
     * 示例 3：
     *
     * 输入：["zyx","wvu","tsr"]
     * 输出：3
     * 解释：
     * 我们必须删掉每一列。
     *  
     *
     * 提示：
     *
     * 1 <= A.length <= 100
     * 1 <= A[i].length <= 100
     * @param A
     * @return
     */
    public int minDeletionSize2(String[] A) {
        int rows = A.length;

        int result = 0;
        int len = A[0].length();
        boolean[] lastCol = new boolean[rows-1];
        search:for (int i = 0; i < len; i++) {
            for (int j = 0; j < rows - 1; j++) {
                if (!lastCol[j] && A[j].charAt(i) > A[j+1].charAt(i)) {
                    result++;
                    continue search;
                }
            }
            for (int j = 0; j < rows - 1; j++) {
                if (A[j].charAt(i) < A[j+1].charAt(i)) {
                    lastCol[j] = true;
                }
            }

        }
        return result;
    }

    @Test
    public void minDeletionSize3() {
        String[] a = { "ghi","def","abc" };
        logResult( minDeletionSize3(a));
    }

    /**
     * 删列造序 III
     * 给定由 N 个小写字母字符串组成的数组 A，其中每个字符串长度相等。
     *
     * 选取一个删除索引序列，对于 A 中的每个字符串，删除对应每个索引处的字符。
     *
     * 比如，有 A = ["babca","bbazb"]，删除索引序列 {0, 1, 4}，删除后 A 为["bc","az"]。
     *
     * 假设，我们选择了一组删除索引 D，那么在执行删除操作之后，最终得到的数组的行中的每个元素都是按字典序排列的。
     *
     * 清楚起见，A[0] 是按字典序排列的（即，A[0][0] <= A[0][1] <= ... <= A[0][A[0].length - 1]），A[1] 是按字典序排列的（即，A[1][0] <= A[1][1] <= ... <= A[1][A[1].length - 1]），依此类推。
     *
     * 请你返回 D.length 的最小可能值。
     *
     *
     *
     * 示例 1：
     *
     * 输入：["babca","bbazb"]
     * 输出：3
     * 解释：
     * 删除 0、1 和 4 这三列后，最终得到的数组是 A = ["bc", "az"]。
     * 这两行是分别按字典序排列的（即，A[0][0] <= A[0][1] 且 A[1][0] <= A[1][1]）。
     * 注意，A[0] > A[1] —— 数组 A 不一定是按字典序排列的。
     * 示例 2：
     *
     * 输入：["edcba"]
     * 输出：4
     * 解释：如果删除的列少于 4 列，则剩下的行都不会按字典序排列。
     * 示例 3：
     *
     * 输入：["ghi","def","abc"]
     * 输出：0
     * 解释：所有行都已按字典序排列。
     *
     *
     * 提示：
     *
     * 1 <= A.length <= 100
     * 1 <= A[i].length <= 100
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
        for (int i = 1; i < len; i++ ) {
            int maxval = 0;
            for (int j = 0; j < i; j++) {

                boolean flag = true;
                for (int k = 0; k < rows; k++)  {
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
        log.debug("lenArray:{}",lenArray);
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
        for (int i = 1; i < len; i++ ) {
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
        int[] nums = {1,3,5,6};
        int target = 6;
        logResult(searchInsert(nums,target));
    }

    /**
     * 35. 搜索插入位置
     * 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
     *
     * 你可以假设数组中无重复元素。
     *
     * 示例 1:
     *
     * 输入: [1,3,5,6], 5
     * 输出: 2
     * 示例 2:
     *
     * 输入: [1,3,5,6], 2
     * 输出: 1
     * 示例 3:
     *
     * 输入: [1,3,5,6], 7
     * 输出: 4
     * 示例 4:
     *
     * 输入: [1,3,5,6], 0
     * 输出: 0
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
            int mid = (left + right)>>1;
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
        int[] nums = {5,1,1};
        nextPermutation(nums);
        log.debug("nums:{}",nums);
    }
    /**
     * 31. 下一个排列
     * 实现获取下一个排列的函数，算法需要将给定数字序列重新排列成字典序中下一个更大的排列。
     *
     * 如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。
     *
     * 必须原地修改，只允许使用额外常数空间。
     *
     * 以下是一些例子，输入位于左侧列，其相应输出位于右侧列。
     * 1,2,3 → 1,3,2
     * 3,2,1 → 1,2,3
     * 1,1,5 → 1,5,1
     * @param nums
     */
    public void nextPermutation(int[] nums) {
        int len = nums.length;
        if (len <= 1) {
            return;
        }
        if (len == 2) {
            swap(nums,0,1);
            return;
        }
        // 如果最后两位是升序, 直接交换
        if (nums[len - 2] < nums[len - 1] ) {
            swap(nums,len - 2,len - 1);
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
        int left = index,right = len - 1;
        while (left < right) {
            swap(nums,left++,right--);
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
        swap(nums,index - 1,firstIndex);


    }

    private void swap(int[] nums,int i,int j) {
        int val = nums[i];
        nums[i] = nums[j];
        nums[j] = val;
    }


    @Test
    public void reconstructMatrix() {
        int upper = 2, lower = 3;
        int[] colsum = {1,1,1};
        logResult(reconstructMatrix(upper,lower,colsum));
    }
    /**
     * 给你一个 2 行 n 列的二进制数组：
     *
     * 矩阵是一个二进制矩阵，这意味着矩阵中的每个元素不是 0 就是 1。
     * 第 0 行的元素之和为 upper。
     * 第 1 行的元素之和为 lower。
     * 第 i 列（从 0 开始编号）的元素之和为 colsum[i]，colsum 是一个长度为 n 的整数数组。
     * 你需要利用 upper，lower 和 colsum 来重构这个矩阵，并以二维整数数组的形式返回它。
     *
     * 如果有多个不同的答案，那么任意一个都可以通过本题。
     *
     * 如果不存在符合要求的答案，就请返回一个空的二维数组。
     *
     *
     *
     * 示例 1：
     *
     * 输入：upper = 2, lower = 1, colsum = [1,1,1]
     * 输出：[[1,1,0],[0,0,1]]
     * 解释：[[1,0,1],[0,1,0]] 和 [[0,1,1],[1,0,0]] 也是正确答案。
     * 示例 2：
     *
     * 输入：upper = 2, lower = 3, colsum = [2,2,1,1]
     * 输出：[]
     * 示例 3：
     * 9
     * 2
     * [0,1,2,0,0,0,0,0,2,1,2,1,2]
     * 输入：upper = 5, lower = 5, colsum = [2,1,2,0,1,0,1,2,0,1]
     * 输出：[[1,1,1,0,1,0,0,1,0,0],[1,0,1,0,0,0,1,1,0,1]]
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
        int num1 = 0,num2 = 0;
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
        int[][] intervals = {{1,2},{3,5},{6,7},{8,10},{12,16}};
        int[] newInterval = {16,18};
        int[][] result = insert(intervals,newInterval);
        for (int[] a: result) {
            log.debug("a:{}",a);
        }
    }

    /**
     * 57. 插入区间
     * 给出一个无重叠的 ，按照区间起始端点排序的区间列表。
     *
     * 在列表中插入一个新的区间，你需要确保列表中的区间仍然有序且不重叠（如果有必要的话，可以合并区间）。
     *
     * 示例 1:
     *
     * 输入: intervals = [[1,3],[6,9]], newInterval = [2,5]
     * 输出: [[1,5],[6,9]]
     * 示例 2:
     *
     * 输入: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
     * 输出: [[1,2],[3,10],[12,16]]
     * 解释: 这是因为新的区间 [4,8] 与 [3,5],[6,7],[8,10] 重叠。
     * @param intervals
     * @param newInterval
     * @return
     */
    public int[][] insert(int[][] intervals, int[] newInterval) {
        int len = intervals.length;
        int low = newInterval[0],high = newInterval[1];
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
        int[] A = {1,2,3,0,0,0};
        int[] B = {2,5,6};
        int m = 3, n = 3;
        merge(A,m,B,n);
        logResult(A);
    }
    /**
     * 面试题 10.01. 合并排序的数组
     * 给定两个排序后的数组 A 和 B，其中 A 的末端有足够的缓冲空间容纳 B。 编写一个方法，将 B 合并入 A 并排序。
     *
     * 初始化 A 和 B 的元素数量分别为 m 和 n。
     *
     * 示例:
     *
     * 输入:
     * A = [1,2,3,0,0,0], m = 3
     * B = [2,5,6],       n = 3
     *
     * 输出: [1,2,2,3,5,6]
     * @param A
     * @param m
     * @param B
     * @param n
     */
    public void merge(int[] A, int m, int[] B, int n) {
        // 归并排序 从后往前
        int i = m - 1,j = n - 1;
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
        int[][] grid = {{2,1,1},{0,1,1},{1,0,1}};
        logResult(orangesRotting(grid));
    }
    /**
     * 994. 腐烂的橘子
     * 在给定的网格中，每个单元格可以有以下三个值之一：
     *
     * 值 0 代表空单元格；
     * 值 1 代表新鲜橘子；
     * 值 2 代表腐烂的橘子。
     * 每分钟，任何与腐烂的橘子（在 4 个正方向上）相邻的新鲜橘子都会腐烂。
     *
     * 返回直到单元格中没有新鲜橘子为止所必须经过的最小分钟数。如果不可能，返回 -1。
     *
     *
     *
     * 示例 1：
     *
     *
     *
     * 输入：[[2,1,1],[1,1,0],[0,1,1]]
     * 输出：4
     * 示例 2：
     *
     * 输入：[[2,1,1],[0,1,1],[1,0,1]]
     * 输出：-1
     * 解释：左下角的橘子（第 2 行， 第 0 列）永远不会腐烂，因为腐烂只会发生在 4 个正向上。
     * 示例 3：
     *
     * 输入：[[0,2]]
     * 输出：0
     * 解释：因为 0 分钟时已经没有新鲜橘子了，所以答案就是 0 。
     *
     *
     * 提示：
     *
     * 1 <= grid.length <= 10
     * 1 <= grid[0].length <= 10
     * grid[i][j] 仅为 0、1 或 2
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
        int badCount = 0,goodCount = 0;
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
                int row = position[0],col = position[1];
                visited[row][col] = true;
                // 上下左右四个方向
                for (int k = 0; k < 4; k++) {
                    int kRow = row + dirRow[k];
                    int kCol = col + dirCol[k];
                    if (inArea(kRow,kCol,rows,cols)
                        && grid[kRow][kCol] == 1 && !visited[kRow][kCol]) {
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

    private boolean inArea(int row, int col,int rowNum,int colNum) {
        return row >= 0 && row < rowNum && col >= 0 && col < colNum;
    }

    @Test
    public void thirdMax() {
        int[] nums = {2, 2,3, Integer.MIN_VALUE};
        logResult(thirdMax(nums));
    }


    /**
     * 414. 第三大的数
     * 给定一个非空数组，返回此数组中第三大的数。如果不存在，则返回数组中最大的数。要求算法时间复杂度必须是O(n)。
     *
     * 示例 1:
     *
     * 输入: [3, 2, 1]
     *
     * 输出: 1
     *
     * 解释: 第三大的数是 1.
     * 示例 2:
     *
     * 输入: [1, 2]
     *
     * 输出: 2
     *
     * 解释: 第三大的数不存在, 所以返回最大的数 2 .
     * 示例 3:
     *
     * 输入: [2, 2, 3, 1]
     *
     * 输出: 1
     *
     * 解释: 注意，要求返回第三大的数，是指第三大且唯一出现的数。
     * 存在两个值为2的数，它们都排第二。
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
            } else if ((mid == null || num > mid) && !Objects.equals(num,big)) {
                small = mid;
                mid = num;
            }  else if ((small == null || num > small) && !Objects.equals(num,big) && !Objects.equals(num,mid)) {
                small = num;
            }
        }

        return small == null ? big : small;

    }

    @Test
    public void findDisappearedNumbers() {
        int[] nums = {4,3,2,7,8,2,3,1};
        logResult(findDisappearedNumbers(nums));
    }

    /**
     * 448. 找到所有数组中消失的数字
     * 给定一个范围在  1 ≤ a[i] ≤ n ( n = 数组大小 ) 的 整型数组，数组中的元素一些出现了两次，另一些只出现一次。
     *
     * 找到所有在 [1, n] 范围之间没有出现在数组中的数字。
     *
     * 您能在不使用额外空间且时间复杂度为O(n)的情况下完成这个任务吗? 你可以假定返回的数组不算在额外空间内。
     *
     * 示例:
     *
     * 输入:
     * [4,3,2,7,8,2,3,1]
     *
     * 输出:
     * [5,6]
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
                result.add(i+1);
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
     * 面试题57 - II. 和为s的连续正数序列
     * 输入一个正整数 target ，输出所有和为 target 的连续正整数序列（至少含有两个数）。
     *
     * 序列内的数字由小到大排列，不同序列按照首个数字从小到大排列。
     *
     *
     *
     * 示例 1：
     *
     * 输入：target = 9
     * 输出：[[2,3,4],[4,5]]
     * 示例 2：
     *
     * 输入：target = 15
     * 输出：[[1,2,3,4,5],[4,5,6],[7,8]]
     *
     *
     * 限制：
     *
     * 1 <= target <= 10^5
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
        int[][] grid = {{0,1,0,0},{1,1,1,0},{0,1,0,0},{1,1,0,0}};
        logResult(islandPerimeter(grid));
    }
    /**
     * 463. 岛屿的周长
     * 给定一个包含 0 和 1 的二维网格地图，其中 1 表示陆地 0 表示水域。
     *
     * 网格中的格子水平和垂直方向相连（对角线方向不相连）。整个网格被水完全包围，但其中恰好有一个岛屿（或者说，一个或多个表示陆地的格子相连组成的岛屿）。
     *
     * 岛屿中没有“湖”（“湖” 指水域在岛屿内部且不和岛屿周围的水相连）。格子是边长为 1 的正方形。网格为长方形，且宽度和高度均不超过 100 。计算这个岛屿的周长。
     *
     *
     *
     * 示例 :
     *
     * 输入:
     * [[0,1,0,0],
     *  [1,1,1,0],
     *  [0,1,0,0],
     *  [1,1,0,0]]
     *
     * 输出: 16
     *
     * 解释: 它的周长是下面图片中的 16 个黄色的边：
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
                    result += getSideLength(i,j,grid);
                }

            }
        }

        return result;
    }


    private int getSideLength(int row, int col,int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        int[] dirRow = {-1, 1, 0, 0};
        int[] dirCol = {0, 0, -1, 1};
        int result = 4;
        for (int k = 0; k < 4; k++) {
            int kRow = row + dirRow[k];
            int kCol = col + dirCol[k];
            if (inArea(kRow,kCol,rows,cols)
                    && grid[kRow][kCol] == 1) {
                result--;
            }
        }
        return result;
    }


    @Test
    public void canPlaceFlowers() {
        int[] flowerbed = {1,0,0,0,0,1};
        int n = 2;
        logResult(canPlaceFlowers(flowerbed,n));
    }

    /**
     * 605. 种花问题
     * 假设你有一个很长的花坛，一部分地块种植了花，另一部分却没有。可是，花卉不能种植在相邻的地块上，它们会争夺水源，两者都会死去。
     *
     * 给定一个花坛（表示为一个数组包含0和1，其中0表示没种植花，1表示种植了花），和一个数 n 。能否在不打破种植规则的情况下种入 n 朵花？能则返回True，不能则返回False。
     *
     * 示例 1:
     *
     * 输入: flowerbed = [1,0,0,0,1], n = 1
     * 输出: True
     * 示例 2:
     *
     * 输入: flowerbed = [1,0,0,0,1], n = 2
     * 输出: False
     * 注意:
     *
     * 数组内已种好的花不会违反种植规则。
     * 输入的数组长度范围为 [1, 20000]。
     * n 是非负整数，且不会超过输入数组的大小。
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
            if ((i == 0|| flowerbed[i - 1] == 0 )
                    && (i == len - 1|| flowerbed[i + 1] == 0 )) {
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
        int[] bits = {1, 1,1,   0};
        logResult(isOneBitCharacter(bits));
    }
    /**
     * 717. 1比特与2比特字符
     * 有两种特殊字符。第一种字符可以用一比特0来表示。第二种字符可以用两比特(10 或 11)来表示。
     *
     * 现给一个由若干比特组成的字符串。问最后一个字符是否必定为一个一比特字符。给定的字符串总是由0结束。
     *
     * 示例 1:
     *
     * 输入:
     * bits = [1, 0, 0]
     * 输出: True
     * 解释:
     * 唯一的编码方式是一个两比特字符和一个一比特字符。所以最后一个字符是一比特字符。
     * 示例 2:
     *
     * 输入:
     * bits = [1, 1, 1, 0]
     * 输出: False
     * 解释:
     * 唯一的编码方式是两比特字符和两比特字符。所以最后一个字符不是一比特字符。
     * 注意:
     *
     * 1 <= len(bits) <= 1000.
     * bits[i] 总是0 或 1.
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


        return isOneBitCharacter(bits,0);
    }

    private boolean isOneBitCharacter(int[] bits,int start) {
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
            result = isOneBitCharacter(bits,start + 1);
        }
        if (result) {
            return true;
        }
        if (num == 1) {
            result = isOneBitCharacter(bits,start + 2);
        }

        return result;
    }

    @Test
    public void duplicateZeros() {
        int[] arr = {8,4,5,0,0,0,0,7};
        duplicateZeros(arr);
        log.debug("arr:{}",arr);
    }
    /**
     * 1089. 复写零
     * 给你一个长度固定的整数数组 arr，请你将该数组中出现的每个零都复写一遍，并将其余的元素向右平移。
     *
     * 注意：请不要在超过该数组长度的位置写入元素。
     *
     * 要求：请对输入的数组 就地 进行上述修改，不要从函数返回任何东西。
     *
     *
     *
     * 示例 1：
     *
     * 输入：[1,0,2,3,0,4,5,0]
     * 输出：null
     * 解释：调用函数后，输入的数组将被修改为：[1,0,0,2,3,0,0,4]
     * 示例 2：
     *
     * 输入：[1,2,3]
     * 输出：null
     * 解释：调用函数后，输入的数组将被修改为：[1,2,3]
     *
     *
     * 提示：
     *
     * 1 <= arr.length <= 10000
     * 0 <= arr[i] <= 9
     *
     * @param arr
     */
    public void duplicateZeros(int[] arr) {
        int len = arr.length;
        int count = 0;
        int i = 0;
        while(i + count < len) {
            if (i + 1 + count >= len) {
                break;
            }
            if(arr[i++] == 0) {
                // 计数 0 的个数, 需要移动多少位
                count++;
            }
        }
        logResult(count);
        int j = len - 1;

        while (count > 0 && j > 0 ) {

            arr[j] = arr[j - count];
            if (arr[j] == 0) {
                arr[--j] = 0;
                count--;
            }
            j--;
            log.debug("arr:{}",arr);

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
    public void minTimeToVisitAllPoints()  {
        int[][] points = {{1,1},{3,4},{-1,0}};
        logResult(minTimeToVisitAllPoints(points));
    }

    /**
     * 1266. 访问所有点的最小时间
     * 平面上有 n 个点，点的位置用整数坐标表示 points[i] = [xi, yi]。请你计算访问所有这些点需要的最小时间（以秒为单位）。
     *
     * 你可以按照下面的规则在平面上移动：
     *
     * 每一秒沿水平或者竖直方向移动一个单位长度，或者跨过对角线（可以看作在一秒内向水平和竖直方向各移动一个单位长度）。
     * 必须按照数组中出现的顺序来访问这些点。
     *
     *
     * 示例 1：
     *
     *
     *
     * 输入：points = [[1,1],[3,4],[-1,0]]
     * 输出：7
     * 解释：一条最佳的访问路径是： [1,1] -> [2,2] -> [3,3] -> [3,4] -> [2,3] -> [1,2] -> [0,1] -> [-1,0]
     * 从 [1,1] 到 [3,4] 需要 3 秒
     * 从 [3,4] 到 [-1,0] 需要 4 秒
     * 一共需要 7 秒
     * 示例 2：
     *
     * 输入：points = [[3,2],[-2,2]]
     * 输出：5
     *
     *
     * 提示：
     *
     * points.length == n
     * 1 <= n <= 100
     * points[i].length == 2
     * -1000 <= points[i][0], points[i][1] <= 1000
     * @param points
     * @return
     */
    public int minTimeToVisitAllPoints(int[][] points) {
        int result = 0;
        int len = points.length;

        for (int i = 1; i < len; i++) {
            int[] point1 = points[i - 1];
            int[] point2 = points[i];

            result += getTime(point1,point2);


        }


        return result;
    }

    private int getTime(int[] point1,int[] point2) {
        int x1 = point1[0],y1 = point1[1];
        int x2 = point2[0],y2 = point2[1];

        int x = Math.abs(x1 - x2);

        int y = Math.abs(y1 - y2);

        return Math.max(x,y);
    }

    @Test
    public void shiftGrid() {
        int[][] grid= {{1,2,3},{4,5,6},{7,8,9}};
        int k = 9;
        logResult(shiftGrid(grid,k));

    }
    /**
     * 1260. 二维网格迁移
     * 给你一个 m 行 n 列的二维网格 grid 和一个整数 k。你需要将 grid 迁移 k 次。
     *
     * 每次「迁移」操作将会引发下述活动：
     *
     * 位于 grid[i][j] 的元素将会移动到 grid[i][j + 1]。
     * 位于 grid[i][n - 1] 的元素将会移动到 grid[i + 1][0]。
     * 位于 grid[m - 1][n - 1] 的元素将会移动到 grid[0][0]。
     * 请你返回 k 次迁移操作后最终得到的 二维网格。
     *
     *
     *
     * 示例 1：
     *
     *
     *
     * 输入：grid = [[1,2,3],[4,5,6],[7,8,9]], k = 1
     * 输出：[[9,1,2],[3,4,5],[6,7,8]]
     * 示例 2：
     *
     *
     *
     * 输入：grid = [[3,8,1,9],[19,7,2,5],[4,6,11,10],[12,0,21,13]], k = 4
     * 输出：[[12,0,21,13],[3,8,1,9],[19,7,2,5],[4,6,11,10]]
     * 示例 3：
     *
     * 输入：grid = [[1,2,3],[4,5,6],[7,8,9]], k = 9
     * 输出：[[1,2,3],[4,5,6],[7,8,9]]
     *
     *
     * 提示：
     *
     * 1 <= grid.length <= 50
     * 1 <= grid[i].length <= 50
     * -1000 <= grid[i][j] <= 1000
     * 0 <= k <= 100
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
        k = k%len;
        if (k > 0) {
            k = len - k;
        }

        int row = k/cols, col = k%cols;

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
        int[][] indices = {{0,1},{1,1}};
        logResult(oddCells(n,m,indices));
    }

    /**
     * 1252. 奇数值单元格的数目
     * 给你一个 n 行 m 列的矩阵，最开始的时候，每个单元格中的值都是 0。
     *
     * 另有一个索引数组 indices，indices[i] = [ri, ci] 中的 ri 和 ci 分别表示指定的行和列（从 0 开始编号）。
     *
     * 你需要将每对 [ri, ci] 指定的行和列上的所有单元格的值加 1。
     *
     * 请你在执行完所有 indices 指定的增量操作后，返回矩阵中 「奇数值单元格」 的数目。
     *
     *
     *
     * 示例 1：
     *
     *
     *
     * 输入：n = 2, m = 3, indices = [[0,1],[1,1]]
     * 输出：6
     * 解释：最开始的矩阵是 [[0,0,0],[0,0,0]]。
     * 第一次增量操作后得到 [[1,2,1],[0,1,0]]。
     * 最后的矩阵是 [[1,3,1],[1,3,1]]，里面有 6 个奇数。
     * 示例 2：
     *
     *
     *
     * 输入：n = 2, m = 2, indices = [[1,1],[0,0]]
     * 输出：0
     * 解释：最后的矩阵是 [[2,2],[2,2]]，里面没有奇数。
     *
     *
     * 提示：
     *
     * 1 <= n <= 50
     * 1 <= m <= 50
     * 1 <= indices.length <= 100
     * 0 <= indices[i][0] < n
     * 0 <= indices[i][1] < m
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
        int[] A ={1,-1,1,-1};
        logResult(canThreePartsEqualSum(A));
    }
    /**
     * 1013. 将数组分成和相等的三个部分
     * 给你一个整数数组 A，只有可以将其划分为三个和相等的非空部分时才返回 true，否则返回 false。
     *
     * 形式上，如果可以找出索引 i+1 < j 且满足 (A[0] + A[1] + ... + A[i] == A[i+1] + A[i+2] + ... + A[j-1] ==
     * A[j] + A[j-1] + ... + A[A.length - 1]) 就可以将数组三等分。
     *
     *
     *
     * 示例 1：
     *
     * 输出：[0,2,1,-6,6,-7,9,1,2,0,1]
     * 输出：true
     * 解释：0 + 2 + 1 = -6 + 6 - 7 + 9 + 1 = 2 + 0 + 1
     * 示例 2：
     *
     * 输入：[0,2,1,-6,6,7,9,-1,2,0,1]
     * 输出：false
     * 示例 3：
     *
     * 输入：[3,3,6,5,-2,2,5,1,-9,4]
     * 输出：true
     * 解释：3 + 3 = 6 = 5 - 2 + 2 + 5 + 1 - 9 + 4
     * @param A
     * @return
     */
    public boolean canThreePartsEqualSum(int[] A) {
        int sum = 0;
        for (int a : A) {
            sum += a;
        }
        log.debug("sum:{}",sum);
        if (sum % 3 != 0) {
            return false;
        }
        int threePart = sum/3;
        int part = 0;
        int flag = 0;
        for (int a : A) {
            part += a;
            if (part == threePart) {
                flag++;
                part = 0;
            }
        }
        log.debug("part:{}",part);
        if (part != 0) {
            return false;
        }


        return flag >= 3;
    }



    @Test
    public void relativeSortArray() {
        int[] arr1 = {2,3,1,3,2,4,6,7,9,2,19};
        int[] arr2 = {2,1,4,3,9,6};
        int[] result = relativeSortArray(arr1,arr2);
        log.debug("result:{}",result);
    }

    /**
     * 1122. 数组的相对排序
     * 给你两个数组，arr1 和 arr2，
     *
     * arr2 中的元素各不相同
     * arr2 中的每个元素都出现在 arr1 中
     * 对 arr1 中的元素进行排序，使 arr1 中项的相对顺序和 arr2 中的相对顺序相同。未在 arr2 中出现过的元素需要按照升序放在 arr1 的末尾。
     *
     *
     *
     * 示例：
     *
     * 输入：arr1 = [2,3,1,3,2,4,6,7,9,2,19], arr2 = [2,1,4,3,9,6]
     * 输出：[2,2,2,1,4,3,3,9,6,7,19]
     *
     *
     * 提示：
     *
     * arr1.length, arr2.length <= 1000
     * 0 <= arr1[i], arr2[i] <= 1000
     * arr2 中的元素 arr2[i] 各不相同
     * arr2 中的每个元素 arr2[i] 都出现在 arr1 中
     * @param arr1
     * @param arr2
     * @return
     */
    public int[] relativeSortArray(int[] arr1, int[] arr2) {
        int len1 = arr1.length,len2 = arr2.length;
        if (len1 == 0) {
            return arr1;
        }
        if (len2 == 0) {
            Arrays.sort(arr1);
            return arr1;
        }
        int[] result = new int[len1];
        // 采用计数排序,但是会浪费一点空间
        int min = arr1[0],max = arr1[0];
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
        for (int i = 0 ; i < len; i++) {
            for (int j = 0; j < counts[i]; j++) {
                result[index++] = i + min;
            }
        }
        return result;
    }


    @Test
    public void numEquivDominoPairs() {
        int[][] dominoes = {{1,2},{2,1},{3,4},{5,6},{1,2}};
        logResult(numEquivDominoPairs(dominoes));
    }

    /**
     * 1128. 等价多米诺骨牌对的数量
     * 给你一个由一些多米诺骨牌组成的列表 dominoes。
     *
     * 如果其中某一张多米诺骨牌可以通过旋转 0 度或 180 度得到另一张多米诺骨牌，我们就认为这两张牌是等价的。
     *
     * 形式上，dominoes[i] = [a, b] 和 dominoes[j] = [c, d] 等价的前提是 a==c 且 b==d，或是 a==d 且 b==c。
     *
     * 在 0 <= i < j < dominoes.length 的前提下，找出满足 dominoes[i] 和 dominoes[j] 等价的骨牌对 (i, j) 的数量。
     *
     *
     *
     * 示例：
     *
     * 输入：dominoes = [[1,2],[2,1],[3,4],[5,6]]
     * 输出：1
     *
     *
     * 提示：
     *
     * 1 <= dominoes.length <= 40000
     * 1 <= dominoes[i][j] <= 9
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
            int index = domin[0]*10+domin[1];
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
        String[] queries = {"bbb","cc"};
        String[] words = {"a","aa","aaa","aaaa"};
        int[] result = numSmallerByFrequency(queries,words);
        log.debug("result:{}",result);
    }

    /**
     * 1170. 比较字符串最小字母出现频次
     * 我们来定义一个函数 f(s)，其中传入参数 s 是一个非空字符串；该函数的功能是统计 s  中（按字典序比较）最小字母的出现频次。
     *
     * 例如，若 s = "dcce"，那么 f(s) = 2，因为最小的字母是 "c"，它出现了 2 次。
     *
     * 现在，给你两个字符串数组待查表 queries 和词汇表 words，请你返回一个整数数组 answer 作为答案，
     * 其中每个 answer[i] 是满足 f(queries[i]) < f(W) 的词的数目，W 是词汇表 words 中的词。
     *
     *
     *
     * 示例 1：
     *
     * 输入：queries = ["cbd"], words = ["zaaaz"]
     * 输出：[1]
     * 解释：查询 f("cbd") = 1，而 f("zaaaz") = 3 所以 f("cbd") < f("zaaaz")。
     * 示例 2：
     *
     * 输入：queries = ["bbb","cc"], words = ["a","aa","aaa","aaaa"]
     * 输出：[1,2]
     * 解释：第一个查询 f("bbb") < f("aaaa")，第二个查询 f("aaa") 和 f("aaaa") 都 > f("cc")。
     *
     *
     * 提示：
     *
     * 1 <= queries.length <= 2000
     * 1 <= words.length <= 2000
     * 1 <= queries[i].length, words[i].length <= 10
     * queries[i][j], words[i][j] 都是小写英文字母
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
            for (int num :letters) {
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
            for (int num :letters) {
                if (num > 0) {
                    wordsNum[i] = num;
                    break;
                }
            }
        }
        log.debug("queriesNum:{}",queriesNum);
        log.debug("wordsNum:{}",wordsNum);
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
        int[][] grid = {{0,0,1,0,0,0,0,1,0,0,0,0,0},
                {0,0,0,0,0,0,0,1,1,1,0,0,0},
                {0,1,1,0,1,0,0,0,0,0,0,0,0},
                {0,1,0,0,1,1,0,0,1,0,1,0,0},
                {0,1,0,0,1,1,0,0,1,1,1,0,0},
                {0,0,0,0,0,0,0,0,0,0,1,0,0},
                {0,0,0,0,0,0,0,1,1,1,0,0,0},
                {0,0,0,0,0,0,0,1,1,0,0,0,0}};

        logResult(maxAreaOfIsland2(grid));
    }

    /**
     * 695. 岛屿的最大面积
     * 给定一个包含了一些 0 和 1的非空二维数组 grid , 一个 岛屿 是由四个方向 (水平或垂直) 的 1 (代表土地) 构成的组合。
     * 你可以假设二维矩阵的四个边缘都被水包围着。
     *
     * 找到给定的二维数组中最大的岛屿面积。(如果没有岛屿，则返回面积为0。)
     *
     * 示例 1:
     *
     * [[0,0,1,0,0,0,0,1,0,0,0,0,0],
     *  [0,0,0,0,0,0,0,1,1,1,0,0,0],
     *  [0,1,1,0,1,0,0,0,0,0,0,0,0],
     *  [0,1,0,0,1,1,0,0,1,0,1,0,0],
     *  [0,1,0,0,1,1,0,0,1,1,1,0,0],
     *  [0,0,0,0,0,0,0,0,0,0,1,0,0],
     *  [0,0,0,0,0,0,0,1,1,1,0,0,0],
     *  [0,0,0,0,0,0,0,1,1,0,0,0,0]]
     * 对于上面这个给定矩阵应返回 6。注意答案不应该是11，因为岛屿只能包含水平或垂直的四个方向的‘1’。
     *
     * 示例 2:
     *
     * [[0,0,0,0,0,0,0,0]]
     * 对于上面这个给定的矩阵, 返回 0。
     *
     * 注意: 给定的矩阵grid 的长度和宽度都不超过 50。
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
                    int area = getArea(i,j,grid);
                    if (area > result) {
                        result = area;
                    }
                }
            }
        }

        return result;
    }

    public int getArea(int row,int col,int[][] grid) {
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
            if (inArea(kRow,kCol,grid.length,grid[0].length)) {
                area += getArea(kRow,kCol,grid);
            }
        }

        return area;
    }

    @Test
    public void distanceBetweenBusStops() {
        int[] distance = {1,2,3,4};
        int start = 0,   destination = 3;
        logResult(distanceBetweenBusStops(distance,start,destination));
    }

    /**
     * 1184. 公交站间的距离
     * 环形公交路线上有 n 个站，按次序从 0 到 n - 1 进行编号。我们已知每一对相邻公交站之间的距离，distance[i] 表示编号为 i 的车站和编号为 (i + 1) % n 的车站之间的距离。
     *
     * 环线上的公交车都可以按顺时针和逆时针的方向行驶。
     *
     * 返回乘客从出发点 start 到目的地 destination 之间的最短距离。
     *
     *
     *
     * 示例 1：
     *
     *
     *
     * 输入：distance = [1,2,3,4], start = 0, destination = 1
     * 输出：1
     * 解释：公交站 0 和 1 之间的距离是 1 或 9，最小值是 1。
     *
     *
     * 示例 2：
     *
     *
     *
     * 输入：distance = [1,2,3,4], start = 0, destination = 2
     * 输出：3
     * 解释：公交站 0 和 2 之间的距离是 3 或 7，最小值是 3。
     *
     *
     * 示例 3：
     *
     *
     *
     * 输入：distance = [1,2,3,4], start = 0, destination = 3
     * 输出：4
     * 解释：公交站 0 和 3 之间的距离是 6 或 4，最小值是 4。
     *
     *
     * 提示：
     *
     * 1 <= n <= 10^4
     * distance.length == n
     * 0 <= start, destination < n
     * 0 <= distance[i] <= 10^4
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
        return Math.min(result,sum - result);
    }


    @Test
    public void maxNumberOfBalloons() {
        String s = "nlaebolko";
        logResult(maxNumberOfBalloons(s));
    }

    /**
     * 1189. “气球” 的最大数量
     * 给你一个字符串 text，你需要使用 text 中的字母来拼凑尽可能多的单词 "balloon"（气球）。
     *
     * 字符串 text 中的每个字母最多只能被使用一次。请你返回最多可以拼凑出多少个单词 "balloon"。
     *
     *
     *
     * 示例 1：
     *
     *
     *
     * 输入：text = "nlaebolko"
     * 输出：1
     * 示例 2：
     *
     *
     *
     * 输入：text = "loonbalxballpoon"
     * 输出：2
     * 示例 3：
     *
     * 输入：text = "leetcode"
     * 输出：0
     *
     *
     * 提示：
     *
     * 1 <= text.length <= 10^4
     * text 全部由小写英文字母组成
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
     * 1200. 最小绝对差
     * 给你个整数数组 arr，其中每个元素都 不相同。
     *
     * 请你找到所有具有最小绝对差的元素对，并且按升序的顺序返回。
     *
     *
     *
     * 示例 1：
     *
     * 输入：arr = [4,2,1,3]
     * 输出：[[1,2],[2,3],[3,4]]
     * 示例 2：
     *
     * 输入：arr = [1,3,6,10,15]
     * 输出：[[1,3]]
     * 示例 3：
     *
     * 输入：arr = [3,8,-10,23,19,-4,-14,27]
     * 输出：[[-14,-10],[19,23],[23,27]]
     *
     *
     * 提示：
     *
     * 2 <= arr.length <= 10^5
     * -10^6 <= arr[i] <= 10^6
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
     * 1207. 独一无二的出现次数
     * 给你一个整数数组 arr，请你帮忙统计数组中每个数的出现次数。
     *
     * 如果每个数的出现次数都是独一无二的，就返回 true；否则返回 false。
     *
     *
     *
     * 示例 1：
     *
     * 输入：arr = [1,2,2,1,1,3]
     * 输出：true
     * 解释：在该数组中，1 出现了 3 次，2 出现了 2 次，3 只出现了 1 次。没有两个数的出现次数相同。
     * 示例 2：
     *
     * 输入：arr = [1,2]
     * 输出：false
     * 示例 3：
     *
     * 输入：arr = [-3,0,1,-3,1,1,1,-3,10,0]
     * 输出：true
     * @param arr
     * @return
     */
    public boolean uniqueOccurrences(int[] arr) {
        if (arr.length <= 1) {
            return true;
        }
        // 计数排序
        int min = arr[0],max = arr[0];
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
        int[] arr = {0,0,0,2,0,5};
        int k = 0;
        int[] result = getLeastNumbers(arr,k);
        log.debug("result:{}",result);
    }

    /**
     * 面试题40. 最小的k个数
     * 输入整数数组 arr ，找出其中最小的 k 个数。例如，输入4、5、1、6、2、7、3、8这8个数字，则最小的4个数字是1、2、3、4。
     *
     *
     *
     * 示例 1：
     *
     * 输入：arr = [3,2,1], k = 2
     * 输出：[1,2] 或者 [2,1]
     * 示例 2：
     *
     * 输入：arr = [0,1,2,1], k = 1
     * 输出：[0]
     *
     *
     * 限制：
     *
     * 0 <= k <= arr.length <= 10000
     * 0 <= arr[i] <= 10000
     * @param arr
     * @param k
     * @return
     */
    public int[] getLeastNumbers(int[] arr, int k) {
        if (k == 0) {
            return new int[0];
        }

        // 构建一个最大堆
        Queue<Integer> queue = new PriorityQueue<>(k,(a,b)-> b-a);

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
        int[] nums = {3,2,1,2,1,7};
        logResult(minIncrementForUnique(nums));
    }

    /**
     * 945. 使数组唯一的最小增量
     * 给定整数数组 A，每次 move 操作将会选择任意 A[i]，并将其递增 1。
     *
     * 返回使 A 中的每个值都是唯一的最少操作次数。
     *
     * 示例 1:
     *
     * 输入：[1,2,2]
     * 输出：1
     * 解释：经过一次 move 操作，数组将变为 [1, 2, 3]。
     * 示例 2:
     *
     * 输入：[3,2,1,2,1,7]
     * 输出：6
     * 解释：经过 6 次 move 操作，数组将变为 [3, 4, 1, 2, 5, 7]。
     * 可以看出 5 次或 5 次以下的 move 操作是不能让数组的每个值唯一的。
     * 提示：
     *
     * 0 <= A.length <= 40000
     * 0 <= A[i] < 40000
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
     * 5348. 两个数组间的距离值
     * 给你两个整数数组 arr1 ， arr2 和一个整数 d ，请你返回两个数组之间的 距离值 。
     *
     * 「距离值」 定义为符合此描述的元素数目：对于元素 arr1[i] ，不存在任何元素 arr2[j] 满足 |arr1[i]-arr2[j]| <= d 。
     *
     *
     *
     * 示例 1：
     *
     * 输入：arr1 = [4,5,8], arr2 = [10,9,1,8], d = 2
     * 输出：2
     * 解释：
     * 对于 arr1[0]=4 我们有：
     * |4-10|=6 > d=2
     * |4-9|=5 > d=2
     * |4-1|=3 > d=2
     * |4-8|=4 > d=2
     * 对于 arr1[1]=5 我们有：
     * |5-10|=5 > d=2
     * |5-9|=4 > d=2
     * |5-1|=4 > d=2
     * |5-8|=3 > d=2
     * 对于 arr1[2]=8 我们有：
     * |8-10|=2 <= d=2
     * |8-9|=1 <= d=2
     * |8-1|=7 > d=2
     * |8-8|=0 <= d=2
     * 示例 2：
     *
     * 输入：arr1 = [1,4,2,3], arr2 = [-4,-3,6,10,20,30], d = 3
     * 输出：2
     * 示例 3：
     *
     * 输入：arr1 = [2,1,100,3], arr2 = [-5,-2,10,-3,7], d = 6
     * 输出：1
     *
     *
     * 提示：
     *
     * 1 <= arr1.length, arr2.length <= 500
     * -10^3 <= arr1[i], arr2[j] <= 10^3
     * 0 <= d <= 100
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
     * 5364. 按既定顺序创建目标数组
     * 给你两个整数数组 nums 和 index。你需要按照以下规则创建目标数组：
     *
     * 目标数组 target 最初为空。
     * 按从左到右的顺序依次读取 nums[i] 和 index[i]，在 target 数组中的下标 index[i] 处插入值 nums[i] 。
     * 重复上一步，直到在 nums 和 index 中都没有要读取的元素。
     * 请你返回目标数组。
     *
     * 题目保证数字插入位置总是存在。
     *
     *
     *
     * 示例 1：
     *
     * 输入：nums = [0,1,2,3,4], index = [0,1,2,2,1]
     * 输出：[0,4,1,3,2]
     * 解释：
     * nums       index     target
     * 0            0        [0]
     * 1            1        [0,1]
     * 2            2        [0,1,2]
     * 3            2        [0,1,3,2]
     * 4            1        [0,4,1,3,2]
     * 示例 2：
     *
     * 输入：nums = [1,2,3,4,0], index = [0,1,2,3,0]
     * 输出：[0,1,2,3,4]
     * 解释：
     * nums       index     target
     * 1            0        [1]
     * 2            1        [1,2]
     * 3            2        [1,2,3]
     * 4            3        [1,2,3,4]
     * 0            0        [0,1,2,3,4]
     * 示例 3：
     *
     * 输入：nums = [1], index = [0]
     * 输出：[1]
     *
     *
     * 提示：
     *
     * 1 <= nums.length, index.length <= 100
     * nums.length == index.length
     * 0 <= nums[i] <= 100
     * 0 <= index[i] <= i
     * @param nums
     * @param index
     * @return
     */
    public int[] createTargetArray(int[] nums, int[] index) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            list.add(index[i],nums[i]);
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
     * 幸运数是指矩阵中满足同时下列两个条件的元素：
     *
     * 在同一行的所有元素中最小
     * 在同一列的所有元素中最大
     *  
     *
     * 示例 1：
     *
     * 输入：matrix = [[3,7,8],[9,11,13],[15,16,17]]
     * 输出：[15]
     * 解释：15 是唯一的幸运数，因为它是其所在行中的最小值，也是所在列中的最大值。
     * 示例 2：
     *
     * 输入：matrix = [[1,10,4,2],[9,3,8,7],[15,16,17,12]]
     * 输出：[12]
     * 解释：12 是唯一的幸运数，因为它是其所在行中的最小值，也是所在列中的最大值。
     * 示例 3：
     *
     * 输入：matrix = [[7,8],[1,2]]
     * 输出：[7]
     *  
     *
     * 提示：
     *
     * m == mat.length
     * n == mat[i].length
     * 1 <= n, m <= 50
     * 1 <= matrix[i][j] <= 10^5
     * 矩阵中的所有元素都是不同的
     * @param matrix
     * @return
     */
    public List<Integer> luckyNumbers (int[][] matrix) {
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
            for (int j = 1; j < cols;j++) {
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
        int[][] grid = {{5,1,0},{-5,-5,-5} };
        logResult(countNegatives(grid));
    }
    /**
     * 1351. 统计有序矩阵中的负数
     * 给你一个 m * n 的矩阵 grid，矩阵中的元素无论是按行还是按列，都以非递增顺序排列。
     *
     * 请你统计并返回 grid 中 负数 的数目。
     *
     *
     *
     * 示例 1：
     *
     * 输入：grid = [[4,3,2,-1],[3,2,1,-1],[1,1,-1,-2],[-1,-1,-2,-3]]
     * 输出：8
     * 解释：矩阵中共有 8 个负数。
     * 示例 2：
     *
     * 输入：grid = [[3,2],[1,0]]
     * 输出：0
     * 示例 3：
     *
     * 输入：grid = [[1,-1],[-1,-1]]
     * 输出：3
     * 示例 4：
     *
     * 输入：grid = [[-1]]
     * 输出：1
     *
     *
     * 提示：
     *
     * m == grid.length
     * n == grid[i].length
     * 1 <= m, n <= 100
     * -100 <= grid[i][j] <= 100
     * @param grid
     * @return
     */
    public int countNegatives(int[][] grid) {
        int count = 0;
        int m = grid.length;
        int n = grid[0].length;
        if (grid[0][0] < 0) {
            return m*n;
        }
        if (grid[m-1][n-1] >= 0) {
            return 0;
        }

        for (int i = 0; i < m; i++) {
            // 二分 查第一个

            if (grid[i][0] < 0) {
                count += n;
                continue;
            }
            if (grid[i][n-1] >= 0) {
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
            log.debug("index:{}",high);
            if (high > 0) {
                count += n - high;
            }



        }


        return count;
    }




    @Test
    public void arrayRankTransform() {
        int[] arr = {37,12,28,9,100,56,80,5,12};
        int[] result = arrayRankTransform(arr) ;
        log.debug("result :{}",result);
    }

    /**
     * 1331. 数组序号转换
     * 给你一个整数数组 arr ，请你将数组中的每个元素替换为它们排序后的序号。
     *
     * 序号代表了一个元素有多大。序号编号的规则如下：
     *
     * 序号从 1 开始编号。
     * 一个元素越大，那么序号越大。如果两个元素相等，那么它们的序号相同。
     * 每个数字的序号都应该尽可能地小。
     *
     *
     * 示例 1：
     *
     * 输入：arr = [40,10,20,30]
     * 输出：[4,1,2,3]
     * 解释：40 是最大的元素。 10 是最小的元素。 20 是第二小的数字。 30 是第三小的数字。
     * 示例 2：
     *
     * 输入：arr = [100,100,100]
     * 输出：[1,1,1]
     * 解释：所有元素有相同的序号。
     * 示例 3：
     *
     * 输入：arr = [37,12,28,9,100,56,80,5,12]
     * 输出：[5,3,4,2,8,6,7,1,3]
     *
     *
     * 提示：
     *
     * 0 <= arr.length <= 105
     * -109 <= arr[i] <= 109
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
        int max = arr[0],min = arr[0];
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
        for (int i = 0;i < bucket.length; i++){
            if(bucket[i] != 0){
                bucket[i] = index++;
            }
        }
        for (int i = 0; i < result.length; i++) {
            result[i] = bucket[arr[i]- min];
        }
        return result;
    }

    @Test
    public void smallerNumbersThanCurrent() {
        int[] nums = {8,1,2,2,3};
        int[] result = smallerNumbersThanCurrent(nums);
        log.debug("result:{}",result);
    }
    /**
     * 1365. 有多少小于当前数字的数字
     * 给你一个数组 nums，对于其中每个元素 nums[i]，请你统计数组中比它小的所有数字的数目。
     *
     * 换而言之，对于每个 nums[i] 你必须计算出有效的 j 的数量，其中 j 满足 j != i 且 nums[j] < nums[i] 。
     *
     * 以数组形式返回答案。
     *
     *
     *
     * 示例 1：
     *
     * 输入：nums = [8,1,2,2,3]
     * 输出：[4,0,1,1,3]
     * 解释：
     * 对于 nums[0]=8 存在四个比它小的数字：（1，2，2 和 3）。
     * 对于 nums[1]=1 不存在比它小的数字。
     * 对于 nums[2]=2 存在一个比它小的数字：（1）。
     * 对于 nums[3]=2 存在一个比它小的数字：（1）。
     * 对于 nums[4]=3 存在三个比它小的数字：（1，2 和 2）。
     * 示例 2：
     *
     * 输入：nums = [6,5,4,8]
     * 输出：[2,1,0,3]
     * 示例 3：
     *
     * 输入：nums = [7,7,7,7]
     * 输出：[0,0,0,0]
     *
     *
     * 提示：
     *
     * 2 <= nums.length <= 500
     * 0 <= nums[i] <= 100
     * @param nums
     * @return
     */
    public int[] smallerNumbersThanCurrent(int[] nums) {
        int len = nums.length;
        int[] result = new int[len];

        int min = nums[0],max = nums[0];
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
        for (int i = 0;i < bucket.length; i++){
            if(bucket[i] != 0){
                int tmp = bucket[i];
                bucket[i] = count;
                count += tmp;
            }
        }
        for (int i = 0; i < result.length; i++) {
            result[i] = bucket[nums[i]- min];
        }

        return result;
    }


    /**
     * 1346. 检查整数及其两倍数是否存在
     * 给你一个整数数组 arr，请你检查是否存在两个整数 N 和 M，满足 N 是 M 的两倍（即，N = 2 * M）。
     *
     * 更正式地，检查是否存在两个下标 i 和 j 满足：
     *
     * i != j
     * 0 <= i, j < arr.length
     * arr[i] == 2 * arr[j]
     *
     *
     * 示例 1：
     *
     * 输入：arr = [10,2,5,3]
     * 输出：true
     * 解释：N = 10 是 M = 5 的两倍，即 10 = 2 * 5 。
     * 示例 2：
     *
     * 输入：arr = [7,1,14,11]
     * 输出：true
     * 解释：N = 14 是 M = 7 的两倍，即 14 = 2 * 7 。
     * 示例 3：
     *
     * 输入：arr = [3,1,7,11]
     * 输出：false
     * 解释：在该情况下不存在 N 和 M 满足 N = 2 * M 。
     *
     *
     * 提示：
     *
     * 2 <= arr.length <= 500
     * -10^3 <= arr[i] <= 10^3
     * @param arr
     * @return
     */
    public boolean checkIfExist(int[] arr) {
        Set<Integer> set = new HashSet<>();
        for (int num : arr) {
            // 偶数,存在其一半
            if ((num&1) == 0 && (set.contains(num >> 1))
                    || set.contains(num << 1)) {
                return true;
            }
            set.add(num);
        }
        return false;
    }


    @Test
    public void replaceElements() {
        int[] arr = {17,18,5,4,6,1};
        int[] result = replaceElements(arr);
        log.debug("result:{}",result);
    }
    /**
     * 1299. 将每个元素替换为右侧最大元素
     * 给你一个数组 arr ，请你将每个元素用它右边最大的元素替换，如果是最后一个元素，用 -1 替换。
     *
     * 完成所有替换操作后，请你返回这个数组。
     *
     *
     *
     * 示例：
     *
     * 输入：arr = [17,18,5,4,6,1]
     * 输出：[18,6,6,6,1,-1]
     *
     *
     * 提示：
     *
     * 1 <= arr.length <= 10^4
     * 1 <= arr[i] <= 10^5
     * @param arr
     * @return
     */
    public int[] replaceElements(int[] arr) {
        int max = -1;
        for (int i = arr.length - 1; i >= 0 ; i--) {
            int tmp = arr[i];
            arr[i] = max;
            if (tmp > max) {
                max = tmp;
            }
        }
        return arr;
    }


    /**
     * 999. 车的可用捕获量
     * 在一个 8 x 8 的棋盘上，有一个白色车（rook）。也可能有空方块，白色的象（bishop）和黑色的卒（pawn）。它们分别以字符 “R”，“.”，“B” 和 “p” 给出。大写字符表示白棋，小写字符表示黑棋。
     *
     * 车按国际象棋中的规则移动：它选择四个基本方向中的一个（北，东，西和南），然后朝那个方向移动，直到它选择停止、到达棋盘的边缘或移动到同一方格来捕获该方格上颜色相反的卒。另外，车不能与其他友方（白色）象进入同一个方格。
     *
     * 返回车能够在一次移动中捕获到的卒的数量。
     *
     *
     * 示例 1：
     *
     *
     *
     * 输入：[[".",".",".",".",".",".",".","."],[".",".",".","p",".",".",".","."],[".",".",".","R",".",".",".","p"],[".",".",".",".",".",".",".","."],[".",".",".",".",".",".",".","."],[".",".",".","p",".",".",".","."],[".",".",".",".",".",".",".","."],[".",".",".",".",".",".",".","."]]
     * 输出：3
     * 解释：
     * 在本例中，车能够捕获所有的卒。
     * 示例 2：
     *
     *
     *
     * 输入：[[".",".",".",".",".",".",".","."],[".","p","p","p","p","p",".","."],[".","p","p","B","p","p",".","."],[".","p","B","R","B","p",".","."],[".","p","p","B","p","p",".","."],[".","p","p","p","p","p",".","."],[".",".",".",".",".",".",".","."],[".",".",".",".",".",".",".","."]]
     * 输出：0
     * 解释：
     * 象阻止了车捕获任何卒。
     * 示例 3：
     *
     *
     *
     * 输入：[[".",".",".",".",".",".",".","."],[".",".",".","p",".",".",".","."],[".",".",".","p",".",".",".","."],["p","p",".","R",".","p","B","."],[".",".",".",".",".",".",".","."],[".",".",".","B",".",".",".","."],[".",".",".","p",".",".",".","."],[".",".",".",".",".",".",".","."]]
     * 输出：3
     * 解释：
     * 车可以捕获位置 b5，d6 和 f5 的卒。
     *
     *
     * 提示：
     *
     * board.length == board[i].length == 8
     * board[i][j] 可以是 'R'，'.'，'B' 或 'p'
     * 只有一个格子上存在 board[i][j] == 'R'
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
        int row = 0,col = 0;
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
            while (inArea(cRow,cCol,rows,cols)) {
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
     * 1356. 根据数字二进制下 1 的数目排序
     * 给你一个整数数组 arr 。请你将数组中的元素按照其二进制表示中数字 1 的数目升序排序。
     *
     * 如果存在多个数字二进制中 1 的数目相同，则必须将它们按照数值大小升序排列。
     *
     * 请你返回排序后的数组。
     *
     *
     *
     * 示例 1：
     *
     * 输入：arr = [0,1,2,3,4,5,6,7,8]
     * 输出：[0,1,2,4,8,3,5,6,7]
     * 解释：[0] 是唯一一个有 0 个 1 的数。
     * [1,2,4,8] 都有 1 个 1 。
     * [3,5,6] 有 2 个 1 。
     * [7] 有 3 个 1 。
     * 按照 1 的个数排序得到的结果数组为 [0,1,2,4,8,3,5,6,7]
     * 示例 2：
     *
     * 输入：arr = [1024,512,256,128,64,32,16,8,4,2,1]
     * 输出：[1,2,4,8,16,32,64,128,256,512,1024]
     * 解释：数组中所有整数二进制下都只有 1 个 1 ，所以你需要按照数值大小将它们排序。
     * 示例 3：
     *
     * 输入：arr = [10000,10000]
     * 输出：[10000,10000]
     * 示例 4：
     *
     * 输入：arr = [2,3,5,7,11,13,17,19]
     * 输出：[2,3,5,17,7,11,13,19]
     * 示例 5：
     *
     * 输入：arr = [10,100,1000,10000]
     * 输出：[10,100,10000,1000]
     *
     *
     * 提示：
     *
     * 1 <= arr.length <= 500
     * 0 <= arr[i] <= 10^4
     * @param arr
     * @return
     */
    public int[] sortByBits(int[] arr) {
        Integer[] sortArr = new Integer[arr.length];
        for(int i = 0; i < arr.length; i++) {
            sortArr[i] = arr[i];
        }

        Arrays.sort(sortArr,(a,b)-> Integer.bitCount(a) != Integer.bitCount(b) ? Integer.bitCount(a) - Integer.bitCount(b): a-b);
        for(int i = 0; i < arr.length; i++) {
           arr[i] =  sortArr[i];
        }
        return arr;
    }


    /**
     * 1337. 方阵中战斗力最弱的 K 行
     * 给你一个大小为 m * n 的方阵 mat，方阵由若干军人和平民组成，分别用 0 和 1 表示。
     *
     * 请你返回方阵中战斗力最弱的 k 行的索引，按从最弱到最强排序。
     *
     * 如果第 i 行的军人数量少于第 j 行，或者两行军人数量相同但 i 小于 j，那么我们认为第 i 行的战斗力比第 j 行弱。
     *
     * 军人 总是 排在一行中的靠前位置，也就是说 1 总是出现在 0 之前。
     *
     *
     *
     * 示例 1：
     *
     * 输入：mat =
     * [[1,1,0,0,0],
     *  [1,1,1,1,0],
     *  [1,0,0,0,0],
     *  [1,1,0,0,0],
     *  [1,1,1,1,1]],
     * k = 3
     * 输出：[2,0,3]
     * 解释：
     * 每行中的军人数目：
     * 行 0 -> 2
     * 行 1 -> 4
     * 行 2 -> 1
     * 行 3 -> 2
     * 行 4 -> 5
     * 从最弱到最强对这些行排序后得到 [2,0,3,1,4]
     * 示例 2：
     *
     * 输入：mat =
     * [[1,0,0,0],
     *  [1,1,1,1],
     *  [1,0,0,0],
     *  [1,0,0,0]],
     * k = 2
     * 输出：[0,2]
     * 解释：
     * 每行中的军人数目：
     * 行 0 -> 1
     * 行 1 -> 4
     * 行 2 -> 1
     * 行 3 -> 1
     * 从最弱到最强对这些行排序后得到 [0,2,3,1]
     *
     *
     * 提示：
     *
     * m == mat.length
     * n == mat[i].length
     * 2 <= n, m <= 100
     * 1 <= k <= m
     * matrix[i][j] 不是 0 就是 1
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
        Arrays.sort(mat,(a,b) -> a[0]- b[0]);
        for (int i = 0; i < k; i++) {
            result[i] = mat[i][1];
        }

        return result;
    }


    /**
     * 1313. 解压缩编码列表
     * 给你一个以行程长度编码压缩的整数列表 nums 。
     *
     * 考虑每对相邻的两个元素 freq, val] = [nums[2*i], nums[2*i+1]] （其中 i >= 0 ），每一对都表示解压后子列表中有 freq 个值为 val 的元素，你需要从左到右连接所有子列表以生成解压后的列表。
     *
     * 请你返回解压后的列表。
     *
     *
     *
     * 示例：
     *
     * 输入：nums = [1,2,3,4]
     * 输出：[2,4,4,4]
     * 解释：第一对 [1,2] 代表着 2 的出现频次为 1，所以生成数组 [2]。
     * 第二对 [3,4] 代表着 4 的出现频次为 3，所以生成数组 [4,4,4]。
     * 最后将它们串联到一起 [2] + [4,4,4] = [2,4,4,4]。
     * 示例 2：
     *
     * 输入：nums = [1,1,2,3]
     * 输出：[1,3,3]
     *
     *
     * 提示：
     *
     * 2 <= nums.length <= 100
     * nums.length % 2 == 0
     * 1 <= nums[i] <= 100
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
        for (int i = 0; i < nums.length; i+=2) {
            int count = nums[i];
            int num = nums[i+1];
            while (count-- > 0) {
                result[index++] = num;
            }
        }


        return result;
    }

    @Test
    public void tictactoe() {
        int[][] moves = {{1,2},{2,1},{1,0},{0,0},{0,1},{2,0},{1,1}};
        logResult(tictactoe(moves));
    }

    /**
     *
     A 和 B 在一个 3 x 3 的网格上玩井字棋。

     井字棋游戏的规则如下：

     玩家轮流将棋子放在空方格 (" ") 上。
     第一个玩家 A 总是用 "X" 作为棋子，而第二个玩家 B 总是用 "O" 作为棋子。
     "X" 和 "O" 只能放在空方格中，而不能放在已经被占用的方格上。
     只要有 3 个相同的（非空）棋子排成一条直线（行、列、对角线）时，游戏结束。
     如果所有方块都放满棋子（不为空），游戏也会结束。
     游戏结束后，棋子无法再进行任何移动。
     给你一个数组 moves，其中每个元素是大小为 2 的另一个数组（元素分别对应网格的行和列），它按照 A 和 B 的行动顺序（先 A 后 B）记录了两人各自的棋子位置。

     如果游戏存在获胜者（A 或 B），就返回该游戏的获胜者；如果游戏以平局结束，则返回 "Draw"；如果仍会有行动（游戏未结束），则返回 "Pending"。

     你可以假设 moves 都 有效（遵循井字棋规则），网格最初是空的，A 将先行动。



     示例 1：

     输入：moves = [[0,0],[2,0],[1,1],[2,1],[2,2]]
     输出："A"
     解释："A" 获胜，他总是先走。
     "X  "    "X  "    "X  "    "X  "    "X  "
     "   " -> "   " -> " X " -> " X " -> " X "
     "   "    "O  "    "O  "    "OO "    "OOX"
     示例 2：

     输入：moves = [[0,0],[1,1],[0,1],[0,2],[1,0],[2,0]]
     输出："B"
     解释："B" 获胜。
     "X  "    "X  "    "XX "    "XXO"    "XXO"    "XXO"
     "   " -> " O " -> " O " -> " O " -> "XO " -> "XO "
     "   "    "   "    "   "    "   "    "   "    "O  "
     示例 3：

     输入：moves = [[0,0],[1,1],[2,0],[1,0],[1,2],[2,1],[0,1],[0,2],[2,2]]
     输出："Draw"
     输出：由于没有办法再行动，游戏以平局结束。
     "XXO"
     "OOX"
     "XOX"
     示例 4：

     输入：moves = [[0,0],[1,1]]
     输出："Pending"
     解释：游戏还没有结束。
     "X  "
     " O "
     "   "


     提示：

     1 <= moves.length <= 9
     moves[i].length == 2
     0 <= moves[i][j] <= 2
     moves 里没有重复的元素。
     moves 遵循井字棋的规则。
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
        int[] winList = {7,56,448,73,146,292,273,84};
        int aNum = 0,bNum = 0;
        String result = "";
        for (int i = 0; i < len; i++) {
            int[] move = moves[i];
            int x = move[0],y = move[1];
            int num = x * 3 + y;
            if ((i&1) == 0) {
                // A
                aNum += 1 << num;
            } else {
                // B
                bNum += 1 << num;
            }
        }

        for(int win : winList){
            // if the moving result contains the winning case in record, then win
            if((aNum & win) == win){
                return "A";
            }
            if((bNum & win) == win){
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
        int[][] grid = {{1,0,0,0},{0,0,0,0},{0,0,0,0},{0,0,0,1}};
        logResult(maxDistance(grid));
    }
    /**
     * 1162. 地图分析
     * 你现在手里有一份大小为 N x N 的『地图』（网格） grid，上面的每个『区域』（单元格）都用 0 和 1 标记好了。
     * 其中 0 代表海洋，1 代表陆地，你知道距离陆地区域最远的海洋区域是是哪一个吗？请返回该海洋区域到离它最近的陆地区域的距离。
     *
     * 我们这里说的距离是『曼哈顿距离』（ Manhattan Distance）：(x0, y0) 和 (x1, y1) 这两个区域之间的距离是 |x0 - x1| + |y0 - y1| 。
     *
     * 如果我们的地图上只有陆地或者海洋，请返回 -1。
     *
     *
     *
     * 示例 1：
     *
     *
     *
     * 输入：[[1,0,1],[0,0,0],[1,0,1]]
     * 输出：2
     * 解释：
     * 海洋区域 (1, 1) 和所有陆地区域之间的距离都达到最大，最大距离为 2。
     * 示例 2：
     *
     *
     *
     * 输入：[[1,0,0],[0,0,0],[0,0,0]]
     * 输出：4
     * 解释：
     * 海洋区域 (2, 2) 和所有陆地区域之间的距离都达到最大，最大距离为 4。
     *
     *
     * 提示：
     *
     * 1 <= grid.length == grid[0].length <= 100
     * grid[i][j] 不是 0 就是 1
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
                if(grid[i][j] == 0){
                    grid[i][j] = rows + cols;
                }
                if (i > 0){
                    grid[i][j] = Math.min(grid[i][j],grid[i - 1][j] +1);
                }
                if (j > 0) {
                    grid[i][j] = Math.min(grid[i][j],grid[i][j - 1] + 1);
                }
            }
        }
        // 从右下开始
        for (int i = rows - 1; i >= 0; i--) {
            for (int j = cols - 1; j >= 0; j--) {
                if (grid[i][j] == 1) {
                    continue;
                }
                if (i < rows - 1){
                    grid[i][j] = Math.min(grid[i][j],grid[i + 1][j] + 1);
                }
                if (j < cols - 1) {
                    grid[i][j] = Math.min(grid[i][j],grid[i][j + 1] + 1);
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


    private void maxDistance1(int[][] grid, int row,int col,int last,boolean flag) {
        int rows = grid.length;
        int cols = grid[0].length;
        if (!inArea(row,col,rows,cols)) {
            return;
        }
        int num = grid[row][col];
        if (num != 1 && flag) {
            grid[row][col] = last + 1 ;
            num = last + 1;
        }
        if (num == 1) {
            flag = true;
        }

        maxDistance1(grid,row+1,col,num,flag);
        maxDistance1(grid,row,col+1,num,flag);
    }

    private void maxDistance2(int[][] grid, int row,int col,int last,boolean flag) {
        int rows = grid.length;
        int cols = grid[0].length;
        if (!inArea(row,col,rows,cols)) {
            return;
        }
        int num = grid[row][col];
        if (num != 1 && flag) {
            grid[row][col] = last + 1 ;
            num = last + 1;
        }
        if (num == 1) {
            flag = true;
        }

        maxDistance2(grid,row-1,col,num,false);
        maxDistance2(grid,row,col-1,num,false);
    }

    @Test
    public void findLucky() {
        int[] arr = {1,2,2,3,3,3};
        logResult(findLucky(arr));
    }

    /**
     * 5368. 找出数组中的幸运数
     * 在整数数组中，如果一个整数的出现频次和它的数值大小相等，我们就称这个整数为「幸运数」。
     *
     * 给你一个整数数组 arr，请你从中找出并返回一个幸运数。
     *
     * 如果数组中存在多个幸运数，只需返回 最大 的那个。
     * 如果数组中不含幸运数，则返回 -1 。
     *
     *
     * 示例 1：
     *
     * 输入：arr = [2,2,3,4]
     * 输出：2
     * 解释：数组中唯一的幸运数是 2 ，因为数值 2 的出现频次也是 2 。
     * 示例 2：
     *
     * 输入：arr = [1,2,2,3,3,3]
     * 输出：3
     * 解释：1、2 以及 3 都是幸运数，只需要返回其中最大的 3 。
     * 示例 3：
     *
     * 输入：arr = [2,2,2,3,3]
     * 输出：-1
     * 解释：数组中不存在幸运数。
     * 示例 4：
     *
     * 输入：arr = [5]
     * 输出：-1
     * 示例 5：
     *
     * 输入：arr = [7,7,7,7,7,7,7]
     * 输出：7
     *
     *
     * 提示：
     *
     * 1 <= arr.length <= 500
     * 1 <= arr[i] <= 500
     * @param arr
     * @return
     */
    public int findLucky(int[] arr) {
        Map<Integer,Integer> map = new HashMap<>();
        for (int num : arr) {
            int count = map.getOrDefault(num,0);
            map.put(num,count+1);
        }
        final int[] result = {-1};
        map.forEach((k,v)->{
            if (Objects.equals(k,v) && k > result[0]) {
                result[0] = k;
            }
        });
        return result[0];
    }


    /**
     * 492. 构造矩形
     * 作为一位web开发者， 懂得怎样去规划一个页面的尺寸是很重要的。
     * 现给定一个具体的矩形页面面积，你的任务是设计一个长度为 L 和宽度为 W 且满足以下要求的矩形的页面。要求：
     *
     * 1. 你设计的矩形页面必须等于给定的目标面积。
     *
     * 2. 宽度 W 不应大于长度 L，换言之，要求 L >= W 。
     *
     * 3. 长度 L 和宽度 W 之间的差距应当尽可能小。
     * 你需要按顺序输出你设计的页面的长度 L 和宽度 W。
     *
     * 示例：
     *
     * 输入: 4
     * 输出: [2, 2]
     * 解释: 目标面积是 4， 所有可能的构造方案有 [1,4], [2,2], [4,1]。
     * 但是根据要求2，[1,4] 不符合要求; 根据要求3，[2,2] 比 [4,1] 更能符合要求. 所以输出长度 L 为 2， 宽度 W 为 2。
     * 说明:
     *
     * 给定的面积不大于 10,000,000 且为正整数。
     * 你设计的页面的长度和宽度必须都是正整数。
     * @param area
     * @return
     */
    public int[] constructRectangle(int area) {
        int num = (int) Math.sqrt(area);
        while (num >= 1) {
            if (area%num == 0) {
                break;
            }
            num--;
        }
        int w = num;
        int l = area/w;
        int[] result = new int[2];
        result[0] = l;
        result[1] = w;
        return result;
    }


    @Test
    public void nextGreaterElement() {
        int[] nums1 = {2,4};
        int[] nums2 = {1,2,3,4};
        int[] result = nextGreaterElement(nums1,nums2);
        log.debug("result:{}",result);
    }

    /**
     * 496. 下一个更大元素 I
     * 给定两个没有重复元素的数组 nums1 和 nums2 ，其中nums1 是 nums2 的子集。找到 nums1 中每个元素在 nums2 中的下一个比其大的值。
     *
     * nums1 中数字 x 的下一个更大元素是指 x 在 nums2 中对应位置的右边的第一个比 x 大的元素。如果不存在，对应位置输出-1。
     *
     * 示例 1:
     *
     * 输入: nums1 = [4,1,2], nums2 = [1,3,4,2].
     * 输出: [-1,3,-1]
     * 解释:
     *     对于num1中的数字4，你无法在第二个数组中找到下一个更大的数字，因此输出 -1。
     *     对于num1中的数字1，第二个数组中数字1右边的下一个较大数字是 3。
     *     对于num1中的数字2，第二个数组中没有下一个更大的数字，因此输出 -1。
     * 示例 2:
     *
     * 输入: nums1 = [2,4], nums2 = [1,2,3,4].
     * 输出: [3,-1]
     * 解释:
     *     对于num1中的数字2，第二个数组中的下一个较大数字是3。
     *     对于num1中的数字4，第二个数组中没有下一个更大的数字，因此输出 -1。
     * 注意:
     *
     * nums1和nums2中所有元素是唯一的。
     * nums1和nums2 的数组大小都不超过1000。
     * @param nums1
     * @param nums2
     * @return
     */
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int len = nums1.length;
        int[] result = new int[len];
        Map<Integer,Integer> map = new HashMap<>();
        Deque<Integer> stack = new LinkedList<>();
        for (int num : nums2) {
            while (!stack.isEmpty() && num > stack.peekLast()) {
                map.put(stack.pollLast(),num);
            }
            stack.addLast(num);
        }
        while (!stack.isEmpty() ) {
            map.put(stack.pollLast(),-1);
        }
        for (int i = 0; i < len; i++) {
            result[i] = map.get(nums1[i]);
        }
        return result;
    }


    /**
     * 506. 相对名次
     * 给出 N 名运动员的成绩，找出他们的相对名次并授予前三名对应的奖牌。
     * 前三名运动员将会被分别授予 “金牌”，“银牌” 和“ 铜牌”（"Gold Medal", "Silver Medal", "Bronze Medal"）。
     *
     * (注：分数越高的选手，排名越靠前。)
     *
     * 示例 1:
     *
     * 输入: [5, 4, 3, 2, 1]
     * 输出: ["Gold Medal", "Silver Medal", "Bronze Medal", "4", "5"]
     * 解释: 前三名运动员的成绩为前三高的，因此将会分别被授予 “金牌”，“银牌”和“铜牌” ("Gold Medal", "Silver Medal" and "Bronze Medal").
     * 余下的两名运动员，我们只需要通过他们的成绩计算将其相对名次即可。
     * 提示:
     *
     * N 是一个正整数并且不会超过 10000。
     * 所有运动员的成绩都不相同。
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
        System.arraycopy(nums,0,newNums,0,len);

        Arrays.sort(newNums);
        Map<Integer,String> map = new HashMap<>();
        int index = 0;
        for (int i = len - 1; i >= 0; i--) {
            index++;
            String str ;
            switch (index) {
                case 1: str = "Gold Medal";break;
                case 2: str = "Silver Medal";break;
                case 3: str = "Bronze Medal";break;
                default: str = String.valueOf(index);
            }
            map.put(newNums[i],str);
        }

        for (int i = 0; i < len; i++) {
            int num = nums[i];
            result[i] = map.get(num);
        }
        return result;
    }


    @Test
    public void findPairs() {
        int[] nums = {1,3,1,5,4};
        int k = 0;
        logResult(findPairs(nums,k));
    }

    /**
     * 532. 数组中的K-diff数对
     * 给定一个整数数组和一个整数 k, 你需要在数组里找到不同的 k-diff 数对。这里将 k-diff 数对定义为一个整数对 (i, j), 其中 i 和 j 都是数组中的数字，且两数之差的绝对值是 k.
     *
     * 示例 1:
     *
     * 输入: [3, 1, 4, 1, 5], k = 2
     * 输出: 2
     * 解释: 数组中有两个 2-diff 数对, (1, 3) 和 (3, 5)。
     * 尽管数组中有两个1，但我们只应返回不同的数对的数量。
     * 示例 2:
     *
     * 输入:[1, 2, 3, 4, 5], k = 1
     * 输出: 4
     * 解释: 数组中有四个 1-diff 数对, (1, 2), (2, 3), (3, 4) 和 (4, 5)。
     * 示例 3:
     *
     * 输入: [1, 3, 1, 5, 4], k = 0
     * 输出: 1
     * 解释: 数组中只有一个 0-diff 数对，(1, 1)。
     * 注意:
     *
     * 数对 (i, j) 和数对 (j, i) 被算作同一数对。
     * 数组的长度不超过10,000。
     * 所有输入的整数的范围在 [-1e7, 1e7]。
     * @param nums
     * @param k
     * @return
     */
    public int findPairs(int[] nums, int k) {
        if (k < 0) {
            return 0;
        }
        Map<Integer,Integer> map = new HashMap<>();
        for (int num : nums) {
            int count = map.getOrDefault(num,0);
            map.put(num,count + 1);
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
                int count2 = map.getOrDefault(key + k ,0);
                if (count2 > 0) {
                    result += 1;
                }
            }
        }
        return result;
    }


    @Test
    public void sortArray() {
        int[] nums = {-4,0,7,4,9,-5,-1,0,-7,-1};
        sortArray(nums);
        log.debug("nums:{}",nums);
        Arrays.sort(nums);
        log.debug("nums:{}",nums);
    }

    /**
     * 912. 排序数组
     * 给定一个整数数组 nums，将该数组升序排列。
     *
     *
     *
     * 示例 1：
     *
     * 输入：[5,2,3,1]
     * 输出：[1,2,3,5]
     * 示例 2：
     *
     * 输入：[5,1,1,2,0,0]
     * 输出：[0,0,1,1,2,5]
     *
     *
     * 提示：
     *
     * 1 <= A.length <= 10000
     * -50000 <= A[i] <= 50000
     * @param nums
     * @return
     */
    public int[] sortArray(int[] nums) {
        shellSort(nums);
        return nums;
    }

    /**
     * 希尔排序
     * @param nums
     */
    private void shellSort(int[] nums) {
        int len = nums.length;
        // 希尔排序，通过优化插入排序，让相距较远的元素优先做比较
        int shellNum = len/2;
        while (shellNum >= 1) {
            for (int i = shellNum; i < len; i++) {
                int tmp = nums[i];
                int index = i;
                while (index >= shellNum && nums[index - shellNum] > tmp ) {
                    nums[index] = nums[index - shellNum];
                    index -= shellNum;
                }
                nums[index] = tmp;
            }
            shellNum = shellNum/2;
        }
    }

    /**
     * 堆排序
     * @param nums
     */
    private void heapSort(int[] nums) {
        int len = nums.length;
        if (len == 1) {
            return;
        }
        for (int i = len; i > 1; i-- ) {
            if (i == 2) {
                if (nums[0] > nums[1]) {
                    swap(nums,0,1);
                }
                break;
            } else {
                createMaxHeap(nums,i);
                swap(nums,0,i - 1);
            }

        }
    }

    /**
     * 构建最大堆
     * @param nums
     * @param len
     */
    private void createMaxHeap(int[] nums,int len) {
        int heapHeight = (int) Math.floor(Math.log(len)/Math.log(2));
        // 0 -> 0
        // 1 -> 1, 2 2 -
        // 2 的heapHeight次幂 - 1
        int heapLen = (1<<heapHeight) - 1;
        // 构建最大堆
        for (int i = heapLen; i >= 0; i--) {
            int left = 2 * i + 1;
            int right = 2 * i + 2;
            if (left < len && nums[left] > nums[i]) {
                swap(nums,i,left);
            }
            if (right < len && nums[right] > nums[i]) {
                swap(nums,i,right);
            }
        }
    }


    /**
     * 566. 重塑矩阵
     * 在MATLAB中，有一个非常有用的函数 reshape，它可以将一个矩阵重塑为另一个大小不同的新矩阵，但保留其原始数据。
     *
     * 给出一个由二维数组表示的矩阵，以及两个正整数r和c，分别表示想要的重构的矩阵的行数和列数。
     *
     * 重构后的矩阵需要将原始矩阵的所有元素以相同的行遍历顺序填充。
     *
     * 如果具有给定参数的reshape操作是可行且合理的，则输出新的重塑矩阵；否则，输出原始矩阵。
     *
     * 示例 1:
     *
     * 输入:
     * nums =
     * [[1,2],
     *  [3,4]]
     * r = 1, c = 4
     * 输出:
     * [[1,2,3,4]]
     * 解释:
     * 行遍历nums的结果是 [1,2,3,4]。新的矩阵是 1 * 4 矩阵, 用之前的元素值一行一行填充新矩阵。
     * 示例 2:
     *
     * 输入:
     * nums =
     * [[1,2],
     *  [3,4]]
     * r = 2, c = 4
     * 输出:
     * [[1,2],
     *  [3,4]]
     * 解释:
     * 没有办法将 2 * 2 矩阵转化为 2 * 4 矩阵。 所以输出原矩阵。
     * 注意：
     *
     * 给定矩阵的宽和高范围在 [1, 100]。
     * 给定的 r 和 c 都是正数。
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
        int row =  0, col = 0;

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
     * 581. 最短无序连续子数组
     * 给定一个整数数组，你需要寻找一个连续的子数组，如果对这个子数组进行升序排序，那么整个数组都会变为升序排序。
     *
     * 你找到的子数组应是最短的，请输出它的长度。
     *
     * 示例 1:
     *
     * 输入: [2, 6, 4, 8, 10, 9, 15]
     * 输出: 5
     * 解释: 你只需要对 [6, 4, 8, 10, 9] 进行升序排序，那么整个表都会变为升序排序。
     * 说明 :
     *
     * 输入的数组长度范围在 [1, 10,000]。
     * 输入的数组可能包含重复元素 ，所以升序的意思是<=。
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
                min = Math.min(min,stack.removeLast());
            }
            stack.addLast(i);
        }
        stack.clear();
        for (int i = len - 1; i >= 0; i--) {
            int num = nums[i];
            while (!stack.isEmpty() && num > nums[stack.peekLast()]) {
                max = Math.max(max,stack.removeLast());
            }
            stack.addLast(i);
        }

        return max > min ? max - min + 1: 0;

    }


    @Test
    public void findMaxAverage() {
        int[] nums ={1,12,-5,-6,50,3};
        int k = 4;
        logResult(findMaxAverage(nums,k));
    }

    /**
     * 643. 子数组最大平均数 I
     * 给定 n 个整数，找出平均数最大且长度为 k 的连续子数组，并输出该最大平均数。
     *
     * 示例 1:
     *
     * 输入: [1,12,-5,-6,50,3], k = 4
     * 输出: 12.75
     * 解释: 最大平均数 (12-5-6+50)/4 = 51/4 = 12.75
     *
     *
     * 注意:
     *
     * 1 <= k <= n <= 30,000。
     * 所给数据范围 [-10,000，10,000]。
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
            sum += nums[i] - nums[i-k];
            if (sum > max) {
                max = sum;
            }
        }

        return max/(double) k;
    }


    @Test
    public void  findErrorNums() {
        int[] nums = {1,2,2,4};
        int[] result = findErrorNums(nums);
        log.debug("result:{}",result);
    }

    /**
     * 645. 错误的集合
     * 集合 S 包含从1到 n 的整数。不幸的是，因为数据错误，导致集合里面某一个元素复制了成了集合里面的另外一个元素的值，
     * 导致集合丢失了一个整数并且有一个元素重复。
     *
     * 给定一个数组 nums 代表了集合 S 发生错误后的结果。你的任务是首先寻找到重复出现的整数，再找到丢失的整数，将它们以数组的形式返回。
     *
     * 示例 1:
     *
     * 输入: nums = [1,2,2,4]
     * 输出: [2,3]
     * 注意:
     *
     * 给定数组的长度范围是 [2, 10000]。
     * 给定的数组是无序的。
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
        int[][] nums = {{2,3,4},{5,6,7},{8,9,10},{11,12,13},{14,15,16}};
        logResult(imageSmoother(nums));
    }

    /**
     * 661. 图片平滑器
     * 包含整数的二维矩阵 M 表示一个图片的灰度。你需要设计一个平滑器来让每一个单元的灰度成为平均灰度 (向下舍入) ，
     * 平均灰度的计算是周围的8个单元和它本身的值求平均，如果周围的单元格不足八个，则尽可能多的利用它们。
     *
     * 示例 1:
     *
     * 输入:
     * [[1,1,1],
     *  [1,0,1],
     *  [1,1,1]]
     * 输出:
     * [[0, 0, 0],
     *  [0, 0, 0],
     *  [0, 0, 0]]
     * 解释:
     * 对于点 (0,0), (0,2), (2,0), (2,2): 平均(3/4) = 平均(0.75) = 0
     * 对于点 (0,1), (1,0), (1,2), (2,1): 平均(5/6) = 平均(0.83333333) = 0
     * 对于点 (1,1): 平均(8/9) = 平均(0.88888889) = 0
     * 注意:
     *
     * 给定矩阵中的整数范围为 [0, 255]。
     * 矩阵的长和宽的范围均为 [1, 150]。
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
                boolean left = false,right = false,up = false,down = false;
                if (i - 1 >= 0 ) {
                    count++;
                    up = true;
                    sum += M[i - 1][j];
                }
                if (j - 1 >= 0 ) {
                    count++;
                    left = true;
                    sum += M[i][j - 1];
                }
                if (i + 1 < rows ) {
                    count++;
                    down = true;
                    sum += M[i + 1][j];
                }
                if (j + 1 < cols ) {
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
                log.debug("i:{},j:{},sum:{},count:{}",i,j,sum,count);

                result[i][j] = sum / count;
            }
        }


        return result;
    }


    @Test
    public void checkPossibility() {
        int[] nums = {1,2,6,7,3,5};
        logResult(checkPossibility(nums));
    }

    /**
     * 665. 非递减数列
     * 给你一个长度为 n 的整数数组，请你判断在 最多 改变 1 个元素的情况下，该数组能否变成一个非递减数列。
     *
     * 我们是这样定义一个非递减数列的： 对于数组中所有的 i (1 <= i < n)，总满足 array[i] <= array[i + 1]。
     *
     *
     *
     * 示例 1:
     *
     * 输入: nums = [4,2,3]
     * 输出: true
     * 解释: 你可以通过把第一个4变成1来使得它成为一个非递减数列。
     * 示例 2:
     *
     * 输入: nums = [4,2,1]
     * 输出: false
     * 解释: 你不能在只改变一个元素的情况下将其变为非递减数列。
     *
     *
     * 说明：
     *
     * 1 <= n <= 10 ^ 4
     * - 10 ^ 5 <= nums[i] <= 10 ^ 5
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
            } else if (nums[i] >= nums[index - 1]){
                index = i;
            } else {
                count++;
            }
        }
        log.debug("count:{} ",count);

        return count <= 1;
    }


    @Test
    public void findShortestSubArray() {
        int[] nums = {1,2,2,3,1,4,2};
        logResult(findShortestSubArray(nums));
    }

    /**
     * 697. 数组的度
     * 给定一个非空且只包含非负数的整数数组 nums, 数组的度的定义是指数组里任一元素出现频数的最大值。
     *
     * 你的任务是找到与 nums 拥有相同大小的度的最短连续子数组，返回其长度。
     *
     * 示例 1:
     *
     * 输入: [1, 2, 2, 3, 1]
     * 输出: 2
     * 解释:
     * 输入数组的度是2，因为元素1和2的出现频数最大，均为2.
     * 连续子数组里面拥有相同度的有如下所示:
     * [1, 2, 2, 3, 1], [1, 2, 2, 3], [2, 2, 3, 1], [1, 2, 2], [2, 2, 3], [2, 2]
     * 最短连续子数组[2, 2]的长度为2，所以返回2.
     * 示例 2:
     *
     * 输入: [1,2,2,3,1,4,2]
     * 输出: 6
     * 注意:
     *
     * nums.length 在1到50,000区间范围内。
     * nums[i] 是一个在0到49,999范围内的整数。
     * @param nums
     * @return
     */
    public int findShortestSubArray(int[] nums) {
        int result = 0;
        if (nums.length == 1) {
            return 1;
        }
        Map<Integer,List<Integer>> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            List<Integer> list = map.getOrDefault(num,new ArrayList<>());
            list.add(i);
            map.put(num,list);
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
        int[] nums = {4,4,7,6,7};
        logResult(minSubsequence(nums));
    }
    /**
     * 5376. 非递增顺序的最小子序列
     * 给你一个数组 nums，请你从中抽取一个子序列，满足该子序列的元素之和 严格 大于未包含在该子序列中的各元素之和。
     *
     * 如果存在多个解决方案，只需返回 长度最小 的子序列。如果仍然有多个解决方案，则返回 元素之和最大 的子序列。
     *
     * 与子数组不同的地方在于，「数组的子序列」不强调元素在原数组中的连续性，也就是说，它可以通过从数组中分离一些（也可能不分离）元素得到。
     *
     * 注意，题目数据保证满足所有约束条件的解决方案是 唯一 的。同时，返回的答案应当按 非递增顺序 排列。
     *
     *
     *
     * 示例 1：
     *
     * 输入：nums = [4,3,10,9,8]
     * 输出：[10,9]
     * 解释：子序列 [10,9] 和 [10,8] 是最小的、满足元素之和大于其他各元素之和的子序列。但是 [10,9] 的元素之和最大。
     * 示例 2：
     *
     * 输入：nums = [4,4,7,6,7]
     * 输出：[7,7,6]
     * 解释：子序列 [7,7] 的和为 14 ，不严格大于剩下的其他元素之和（14 = 4 + 4 + 6）。因此，[7,6,7] 是满足题意的最小子序列。
     * 注意，元素按非递增顺序返回。
     * 示例 3：
     *
     * 输入：nums = [6]
     * 输出：[6]
     *
     *
     * 提示：
     *
     * 1 <= nums.length <= 500
     * 1 <= nums[i] <= 100
     * @param nums
     * @return
     */
    public List<Integer> minSubsequence(int[] nums) {
        List<Integer> list = new ArrayList<>();
        Arrays.sort(nums);
        int len = nums.length;
        int sum = 0;
        for (int num: nums) {
            sum += num;
        }
        int minSum = 0;
        for (int i = len - 1; i >= 0 ; i--) {
            int num = nums[i];
            minSum += num;
            list.add(num);
            if (minSum > sum - minSum ) {
                break;
            }
        }


        return list;
    }


    /**
     * 1051. 高度检查器
     * 学校在拍年度纪念照时，一般要求学生按照 非递减 的高度顺序排列。
     *
     * 请你返回能让所有学生以 非递减 高度排列的最小必要移动人数。
     *
     * 注意，当一组学生被选中时，他们之间可以以任何可能的方式重新排序，而未被选中的学生应该保持不动。
     *
     *
     *
     * 示例：
     *
     * 输入：heights = [1,1,4,2,1,3]
     * 输出：3
     * 解释：
     * 当前数组：[1,1,4,2,1,3]
     * 目标数组：[1,1,1,2,3,4]
     * 在下标 2 处（从 0 开始计数）出现 4 vs 1 ，所以我们必须移动这名学生。
     * 在下标 4 处（从 0 开始计数）出现 1 vs 3 ，所以我们必须移动这名学生。
     * 在下标 5 处（从 0 开始计数）出现 3 vs 4 ，所以我们必须移动这名学生。
     * 示例 2：
     *
     * 输入：heights = [5,1,2,3,4]
     * 输出：5
     * 示例 3：
     *
     * 输入：heights = [1,2,3,4,5]
     * 输出：0
     *
     *
     * 提示：
     *
     * 1 <= heights.length <= 100
     * 1 <= heights[i] <= 100
     * @param heights
     * @return
     */
    public int heightChecker(int[] heights) {
        int[] bucket = new int[101];

        for (int height: heights) {
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
        int[][] matrix = {{5, 1, 9,11},{2, 4, 8,10},{13, 3, 6, 7},{15,14,12,16}};
        rotate2(matrix);
        logResult(matrix);
    }

    /**
     * 面试题 01.07. 旋转矩阵
     * 给你一幅由 N × N 矩阵表示的图像，其中每个像素的大小为 4 字节。请你设计一种算法，将图像旋转 90 度。
     *
     * 不占用额外内存空间能否做到？
     *
     *
     *
     * 示例 1:
     *
     * 给定 matrix =
     * [
     *   [1,2,3],
     *   [4,5,6],
     *   [7,8,9]
     * ],
     *
     * 原地旋转输入矩阵，使其变为:
     * [
     *   [7,4,1],
     *   [8,5,2],
     *   [9,6,3]
     * ]
     * 示例 2:
     *
     * 给定 matrix =
     * [
     *   [ 5, 1, 9,11],
     *   [ 2, 4, 8,10],
     *   [13, 3, 6, 7],
     *   [15,14,12,16]
     * ],
     *
     * 原地旋转输入矩阵，使其变为:
     * [
     *   [15,13, 2, 5],
     *   [14, 3, 4, 1],
     *   [12, 6, 8, 9],
     *   [16, 7,10,11]
     * ]
     * @param matrix
     */
    public void rotate2(int[][] matrix) {
        int len = matrix.length;

        for (int i = 0; i < (len>>1); i++) {
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
     * 830. 较大分组的位置
     * 在一个由小写字母构成的字符串 S 中，包含由一些连续的相同字符所构成的分组。
     *
     * 例如，在字符串 S = "abbxxxxzyy" 中，就含有 "a", "bb", "xxxx", "z" 和 "yy" 这样的一些分组。
     *
     * 我们称所有包含大于或等于三个连续字符的分组为较大分组。找到每一个较大分组的起始和终止位置。
     *
     * 最终结果按照字典顺序输出。
     *
     * 示例 1:
     *
     * 输入: "abbxxxxzzy"
     * 输出: [[3,6]]
     * 解释: "xxxx" 是一个起始于 3 且终止于 6 的较大分组。
     * 示例 2:
     *
     * 输入: "abc"
     * 输出: []
     * 解释: "a","b" 和 "c" 均不是符合要求的较大分组。
     * 示例 3:
     *
     * 输入: "abcdddeeeeaabbbcd"
     * 输出: [[3,5],[6,9],[12,14]]
     * 说明:  1 <= S.length <= 1000
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
        int[][] A = {{1,1,0,0},{1,0,0,1},{0,1,1,1},{1,0,1,0}};
        logResult(flipAndInvertImage(A));
    }
    /**
     * 832. 翻转图像
     * 给定一个二进制矩阵 A，我们想先水平翻转图像，然后反转图像并返回结果。
     *
     * 水平翻转图片就是将图片的每一行都进行翻转，即逆序。例如，水平翻转 [1, 1, 0] 的结果是 [0, 1, 1]。
     *
     * 反转图片的意思是图片中的 0 全部被 1 替换， 1 全部被 0 替换。例如，反转 [0, 1, 1] 的结果是 [1, 0, 0]。
     *
     * 示例 1:
     *
     * 输入: [[1,1,0],[1,0,1],[0,0,0]]
     * 输出: [[1,0,0],[0,1,0],[1,1,1]]
     * 解释: 首先翻转每一行: [[0,1,1],[1,0,1],[0,0,0]]；
     *      然后反转图片: [[1,0,0],[0,1,0],[1,1,1]]
     * 示例 2:
     *
     * 输入: [[1,1,0,0],[1,0,0,1],[0,1,1,1],[1,0,1,0]]
     * 输出: [[1,1,0,0],[0,1,1,0],[0,0,0,1],[1,0,1,0]]
     * 解释: 首先翻转每一行: [[0,0,1,1],[1,0,0,1],[1,1,1,0],[0,1,0,1]]；
     *      然后反转图片: [[1,1,0,0],[0,1,1,0],[0,0,0,1],[1,0,1,0]]
     * 说明:
     *
     * 1 <= A.length = A[0].length <= 20
     * 0 <= A[i][j] <= 1
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
        return  result;
    }


    @Test
    public void numMagicSquaresInside() {
        //int[][] grid = {{4,3,8,4},{9,5,1,9},{2,7,6,2}};
        // int[][] grid = {{2,7,6},{1,5,9},{4,3,8}};
        int[][] grid = {{3,2,9,2,7},{6,1,8,4,2},{7,5,3,2,7},{2,9,4,9,6},{4,3,8,2,5}};
        logResult(numMagicSquaresInside(grid));
    }

    /**
     * 840. 矩阵中的幻方
     * 3 x 3 的幻方是一个填充有从 1 到 9 的不同数字的 3 x 3 矩阵，其中每行，每列以及两条对角线上的各数之和都相等。
     *
     * 给定一个由整数组成的 grid，其中有多少个 3 × 3 的 “幻方” 子矩阵？（每个子矩阵都是连续的）。
     *
     *
     *
     * 示例：
     *
     * 输入: [[4,3,8,4],
     *       [9,5,1,9],
     *       [2,7,6,2]]
     * 输出: 1
     * 解释:
     * 下面的子矩阵是一个 3 x 3 的幻方：
     * 438
     * 951
     * 276
     *
     * 而这一个不是：
     * 384
     * 519
     * 762
     *
     * 总的来说，在本示例所给定的矩阵中只有一个 3 x 3 的幻方子矩阵。
     * 提示:
     *
     * 1 <= grid.length <= 10
     * 1 <= grid[0].length <= 10
     * 0 <= grid[i][j] <= 15
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

                Arrays.fill(magicNums,1);

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
                        if (--magicNums[num - 1] < 0 ) {
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
        int[] stones = {1,2,3,4,5,6,7};
        logResult(lastStoneWeight(stones));

    }

    /**
     * 1046. 最后一块石头的重量
     * 有一堆石头，每块石头的重量都是正整数。
     *
     * 每一回合，从中选出两块最重的石头，然后将它们一起粉碎。假设石头的重量分别为 x 和 y，且 x <= y。那么粉碎的可能结果如下：
     *
     * 如果 x == y，那么两块石头都会被完全粉碎；
     * 如果 x != y，那么重量为 x 的石头将会完全粉碎，而重量为 y 的石头新重量为 y-x。
     * 最后，最多只会剩下一块石头。返回此石头的重量。如果没有石头剩下，就返回 0。
     *
     *
     *
     * 提示：
     *
     * 1 <= stones.length <= 30
     * 1 <= stones[i] <= 1000
     * 通过次数12,420提交次数20,870
     * @param stones
     * @return
     */
    public int lastStoneWeight(int[] stones) {
        int len = stones.length;
        // 构建最大堆
        PriorityQueue<Integer> heap = new PriorityQueue<>(len,(a,b) -> b - a);
        for (int weight : stones) {
            heap.add(weight);
        }
        while (heap.size() > 1) {
            log.debug("a:{}",heap);
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
    public void movingCount( ) {
        int m = 2, n = 3, k = 1;
        logResult(movingCount(m,n,k));
    }

    /**
     * 面试题13. 机器人的运动范围
     * 地上有一个m行n列的方格，从坐标 [0,0] 到坐标 [m-1,n-1] 。一个机器人从坐标 [0, 0] 的格子开始移动，
     * 它每次可以向左、右、上、下移动一格（不能移动到方格外），也不能进入行坐标和列坐标的数位之和大于k的格子。例如，当k为18时，
     * 机器人能够进入方格 [35, 37] ，因为3+5+3+7=18。但它不能进入方格 [35, 38]，因为3+5+3+8=19。请问该机器人能够到达多少个格子？
     *
     *
     *
     * 示例 1：
     *
     * 输入：m = 2, n = 3, k = 1
     * 输出：3
     * 示例 1：
     *
     * 输入：m = 3, n = 1, k = 0
     * 输出：1
     * 提示：
     *
     * 1 <= n,m <= 100
     * 0 <= k <= 20
     * @param m
     * @param n
     * @param k
     * @return
     */
    public int movingCount(int m, int n, int k) {
        M = m;
        N = n;
        Set<String> set = new HashSet<>();

        movingCount(0,0,k,set);


        return set.size();
    }

    static int M,N;

    static int[] DIR_ROW = {-1, 1, 0, 0};
    static int[] DIR_COL = {0, 0, -1, 1};


    private void movingCount(int rowIndex,int colIndex,int k,Set<String> set) {
        if (getNum(rowIndex) + getNum(colIndex) <= k) {
            set.add(rowIndex + "," + colIndex);
        } else {
            return;
        }
        for (int i = 0; i < 4; i++) {
            int row = rowIndex + DIR_ROW[i];
            int col = colIndex + DIR_COL[i];
            if (!inArea(row,col,M,N)) {
                continue;
            }
            String key = row + "," + col;
            if (set.contains(key)) {
                continue;
            }
            movingCount(row,col,k,set);
        }

    }


    private int getNum(int num) {
        int result = 0;
        while (num > 0) {
            result += num%10;
            num /= 10;
        }
        return result;
    }



    @Test
    public void  prefixesDivBy5() {
        // int[] nums = { 1,0,1,1,1,1,0,0,0,0,1,0,0,0,0,0,1,0,0,1,1,1,1,1,0,0,0,0,1,1,
        // 1,0,0,0,0,0,1,0,0,0,1,0,0,1,1,1,1,1,1,0,1,1,0,1,0,0,0,0,0,0,1,0,1,1,1,0,0,1,0};
        int[] nums = {0,1,1,1,1,1};
        logResult(prefixesDivBy5(nums));

    }

    /**
     * 1018. 可被 5 整除的二进制前缀
     * 给定由若干 0 和 1 组成的数组 A。我们定义 N_i：从 A[0] 到 A[i] 的第 i 个子数组被解释为一个二进制数（从最高有效位到最低有效位）。
     *
     * 返回布尔值列表 answer，只有当 N_i 可以被 5 整除时，答案 answer[i] 为 true，否则为 false。
     *
     *
     *
     * 示例 1：
     *
     * 输入：[0,1,1]
     * 输出：[true,false,false]
     * 解释：
     * 输入数字为 0, 01, 011；也就是十进制中的 0, 1, 3 。只有第一个数可以被 5 整除，因此 answer[0] 为真。
     * 示例 2：
     *
     * 输入：[1,1,1]
     * 输出：[false,false,false]
     * 示例 3：
     *
     * 输入：[0,1,1,1,1,1]
     * 输出：[true,false,false,false,true,false]
     * 示例 4：
     *
     * 输入：[1,1,1,0,1]
     * 输出：[false,false,false,false,false]
     *
     *
     * 提示：
     *
     * 1 <= A.length <= 30000
     * A[i] 为 0 或 1
     * @param A
     * @return
     */
    public List<Boolean> prefixesDivBy5(int[] A) {
        List<Boolean> list = new ArrayList<>();
        int num = 0;
        for (int a : A) {
            num += 2 * num + a;
            if (num >= 10) {
                num = num%10;
            }
            if (num  == 0 || num == 5) {
                list.add(true);
                continue;
            }
            list.add(false);

        }


        return list;
    }


    /**
     * 985. 查询后的偶数和
     * 给出一个整数数组 A 和一个查询数组 queries。
     *
     * 对于第 i 次查询，有 val = queries[i][0], index = queries[i][1]，
     * 我们会把 val 加到 A[index] 上。然后，第 i 次查询的答案是 A 中偶数值的和。
     *
     * （此处给定的 index = queries[i][1] 是从 0 开始的索引，每次查询都会永久修改数组 A。）
     *
     * 返回所有查询的答案。你的答案应当以数组 answer 给出，answer[i] 为第 i 次查询的答案。
     *
     *
     *
     * 示例：
     *
     * 输入：A = [1,2,3,4], queries = [[1,0],[-3,1],[-4,0],[2,3]]
     * 输出：[8,6,2,4]
     * 解释：
     * 开始时，数组为 [1,2,3,4]。
     * 将 1 加到 A[0] 上之后，数组为 [2,2,3,4]，偶数值之和为 2 + 2 + 4 = 8。
     * 将 -3 加到 A[1] 上之后，数组为 [2,-1,3,4]，偶数值之和为 2 + 4 = 6。
     * 将 -4 加到 A[0] 上之后，数组为 [-2,-1,3,4]，偶数值之和为 -2 + 4 = 2。
     * 将 2 加到 A[3] 上之后，数组为 [-2,-1,3,6]，偶数值之和为 -2 + 6 = 4。
     *
     *
     * 提示：
     *
     * 1 <= A.length <= 10000
     * -10000 <= A[i] <= 10000
     * 1 <= queries.length <= 10000
     * -10000 <= queries[i][0] <= 10000
     * 0 <= queries[i][1] < A.length
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
        logResult(addToArrayForm(A,K));
    }
    /**
     * 989. 数组形式的整数加法
     * 对于非负整数 X 而言，X 的数组形式是每位数字按从左到右的顺序形成的数组。例如，如果 X = 1231，那么其数组形式为 [1,2,3,1]。
     *
     * 给定非负整数 X 的数组形式 A，返回整数 X+K 的数组形式。
     *
     *
     *
     * 示例 1：
     *
     * 输入：A = [1,2,0,0], K = 34
     * 输出：[1,2,3,4]
     * 解释：1200 + 34 = 1234
     * 示例 2：
     *
     * 输入：A = [2,7,4], K = 181
     * 输出：[4,5,5]
     * 解释：274 + 181 = 455
     * 示例 3：
     *
     * 输入：A = [2,1,5], K = 806
     * 输出：[1,0,2,1]
     * 解释：215 + 806 = 1021
     * 示例 4：
     *
     * 输入：A = [9,9,9,9,9,9,9,9,9,9], K = 1
     * 输出：[1,0,0,0,0,0,0,0,0,0,0]
     * 解释：9999999999 + 1 = 10000000000
     *
     *
     * 提示：
     *
     * 1 <= A.length <= 10000
     * 0 <= A[i] <= 9
     * 0 <= K <= 10000
     * 如果 A.length > 1，那么 A[0] != 0
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
     * 1010. 总持续时间可被 60 整除的歌曲
     * 在歌曲列表中，第 i 首歌曲的持续时间为 time[i] 秒。
     *
     * 返回其总持续时间（以秒为单位）可被 60 整除的歌曲对的数量。形式上，我们希望索引的数字  i < j 且有 (time[i] + time[j]) % 60 == 0。
     *
     *
     *
     * 示例 1：
     *
     * 输入：[30,20,150,100,40]
     * 输出：3
     * 解释：这三对的总持续时间可被 60 整数：
     * (time[0] = 30, time[2] = 150): 总持续时间 180
     * (time[1] = 20, time[3] = 100): 总持续时间 120
     * (time[1] = 20, time[4] = 40): 总持续时间 60
     * 示例 2：
     *
     * 输入：[60,60,60]
     * 输出：3
     * 解释：所有三对的总持续时间都是 120，可以被 60 整数。
     *
     *
     * 提示：
     *
     * 1 <= time.length <= 60000
     * 1 <= time[i] <= 5001010. 总持续时间可被 60 整除的歌曲
     * 在歌曲列表中，第 i 首歌曲的持续时间为 time[i] 秒。
     *
     * 返回其总持续时间（以秒为单位）可被 60 整除的歌曲对的数量。形式上，我们希望索引的数字  i < j 且有 (time[i] + time[j]) % 60 == 0。
     *
     *
     *
     * 示例 1：
     *
     * 输入：[30,20,150,100,40]
     * 输出：3
     * 解释：这三对的总持续时间可被 60 整数：
     * (time[0] = 30, time[2] = 150): 总持续时间 180
     * (time[1] = 20, time[3] = 100): 总持续时间 120
     * (time[1] = 20, time[4] = 40): 总持续时间 60
     * 示例 2：
     *
     * 输入：[60,60,60]
     * 输出：3
     * 解释：所有三对的总持续时间都是 120，可以被 60 整数。
     *
     *
     * 提示：
     *
     * 1 <= time.length <= 60000
     * 1 <= time[i] <= 500
     * @param time
     * @return
     */
    public int numPairsDivisibleBy60(int[] time) {
        int len = time.length;
        int result = 0;
        // 使用余数
        int[] nums = new int[60];
        for (int i = 0; i < len; i++) {
            int index = time[i]%60;
            nums[index]++;
        }

        int left = 1,right = 59;
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
     * 867. 转置矩阵
     * 给定一个矩阵 A， 返回 A 的转置矩阵。
     *
     * 矩阵的转置是指将矩阵的主对角线翻转，交换矩阵的行索引与列索引。
     *
     *
     *
     * 示例 1：
     *
     * 输入：[[1,2,3],[4,5,6],[7,8,9]]
     * 输出：[[1,4,7],[2,5,8],[3,6,9]]
     * 示例 2：
     *
     * 输入：[[1,2,3],[4,5,6]]
     * 输出：[[1,4],[2,5],[3,6]]
     *
     *
     * 提示：
     *
     * 1 <= A.length <= 1000
     * 1 <= A[0].length <= 1000
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
     * 977. 有序数组的平方
     * 给定一个按非递减顺序排序的整数数组 A，返回每个数字的平方组成的新数组，要求也按非递减顺序排序。
     *
     *
     *
     * 示例 1：
     *
     * 输入：[-4,-1,0,3,10]
     * 输出：[0,1,9,16,100]
     * 示例 2：
     *
     * 输入：[-7,-3,2,3,11]
     * 输出：[4,9,9,49,121]
     *
     *
     * 提示：
     *
     * 1 <= A.length <= 10000
     * -10000 <= A[i] <= 10000
     * A 已按非递减顺序排序。
     * @param A
     * @return
     */
    public int[] sortedSquares(int[] A) {
        int len = A.length;
        int left = 0,right = len - 1;
        int index = len - 1;
        int[] result = new int[len];
        while (left < right) {
            int leftNum = A[left]*A[left];
            int rightNum = A[right]*A[right];
            if (leftNum <= rightNum) {
                result[index--] = rightNum;
                right--;

            } else {
                result[index--] = leftNum;
                left++;
            }
        }
        result[index] = A[left]*A[left];
        return result;
    }


    /**
     * 905. 按奇偶排序数组
     * 给定一个非负整数数组 A，返回一个数组，在该数组中， A 的所有偶数元素之后跟着所有奇数元素。
     *
     * 你可以返回满足此条件的任何数组作为答案。
     *
     *
     *
     * 示例：
     *
     * 输入：[3,1,2,4]
     * 输出：[2,4,3,1]
     * 输出 [4,2,3,1]，[2,4,1,3] 和 [4,2,1,3] 也会被接受。
     *
     *
     * 提示：
     *
     * 1 <= A.length <= 5000
     * 0 <= A[i] <= 5000
     * @param A
     * @return
     */
    public int[] sortArrayByParity(int[] A) {
        int len = A.length;
        int left = 0,right = len - 1;
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
     * 922. 按奇偶排序数组 II
     * 给定一个非负整数数组 A， A 中一半整数是奇数，一半整数是偶数。
     *
     * 对数组进行排序，以便当 A[i] 为奇数时，i 也是奇数；当 A[i] 为偶数时， i 也是偶数。
     *
     * 你可以返回任何满足上述条件的数组作为答案。
     *
     *
     *
     * 示例：
     *
     * 输入：[4,2,5,7]
     * 输出：[4,5,2,7]
     * 解释：[4,7,2,5]，[2,5,4,7]，[2,7,4,5] 也会被接受。
     *
     *
     * 提示：
     *
     * 2 <= A.length <= 20000
     * A.length % 2 == 0
     * 0 <= A[i] <= 1000
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


    /***
     * 896. 单调数列
     * 如果数组是单调递增或单调递减的，那么它是单调的。
     *
     * 如果对于所有 i <= j，A[i] <= A[j]，那么数组 A 是单调递增的。 如果对于所有 i <= j，A[i]> = A[j]，那么数组 A 是单调递减的。
     *
     * 当给定的数组 A 是单调数组时返回 true，否则返回 false。
     *
     *
     *
     * 示例 1：
     *
     * 输入：[1,2,2,3]
     * 输出：true
     * 示例 2：
     *
     * 输入：[6,5,4,4]
     * 输出：true
     * 示例 3：
     *
     * 输入：[1,3,2]
     * 输出：false
     * 示例 4：
     *
     * 输入：[1,2,4,5]
     * 输出：true
     * 示例 5：
     *
     * 输入：[1,1,1]
     * 输出：true
     *
     *
     * 提示：
     *
     * 1 <= A.length <= 50000
     * -100000 <= A[i] <= 100000
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
    public void isLongPressedName( ) {
        String name = "pyplrz", typed = "ppyypllr";
        logResult(isLongPressedName(name,typed));
    }

    /**
     * 925. 长按键入
     * 你的朋友正在使用键盘输入他的名字 name。偶尔，在键入字符 c 时，按键可能会被长按，而字符可能被输入 1 次或多次。
     *
     * 你将会检查键盘输入的字符 typed。如果它对应的可能是你的朋友的名字（其中一些字符可能被长按），那么就返回 True。
     *
     *
     *
     * 示例 1：
     *
     * 输入：name = "alex", typed = "aaleex"
     * 输出：true
     * 解释：'alex' 中的 'a' 和 'e' 被长按。
     * 示例 2：
     *
     * 输入：name = "saeed", typed = "ssaaedd"
     * 输出：false
     * 解释：'e' 一定需要被键入两次，但在 typed 的输出中不是这样。
     * 示例 3：
     *
     * 输入：name = "leelee", typed = "lleeelee"
     * 输出：true
     * 示例 4：
     *
     * 输入：name = "laiden", typed = "laiden"
     * 输出：true
     * 解释：长按名字中的字符并不是必要的。
     *
     *
     * 提示：
     *
     * name.length <= 1000
     * typed.length <= 1000
     * name 和 typed 的字符都是小写字母。
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
            } else if (index - 1 >= 0  && c == name.charAt(index - 1)) {
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
     * 941. 有效的山脉数组
     * 给定一个整数数组 A，如果它是有效的山脉数组就返回 true，否则返回 false。
     *
     * 让我们回顾一下，如果 A 满足下述条件，那么它是一个山脉数组：
     *
     * A.length >= 3
     * 在 0 < i < A.length - 1 条件下，存在 i 使得：
     * A[0] < A[1] < ... A[i-1] < A[i]
     * A[i] > A[i+1] > ... > A[A.length - 1]
     *
     *
     *
     *
     *
     *
     * 示例 1：
     *
     * 输入：[2,1]
     * 输出：false
     * 示例 2：
     *
     * 输入：[3,5,5]
     * 输出：false
     * 示例 3：
     *
     * 输入：[0,3,2,1]
     * 输出：true
     *
     *
     * 提示：
     *
     * 0 <= A.length <= 10000
     * 0 <= A[i] <= 10000
     * @param A
     * @return
     */
    public boolean validMountainArray(int[] A) {
        int len = A.length;
        int left = 0,right = len - 1;
        if (len <  3) {
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
     * 888. 公平的糖果交换
     * 爱丽丝和鲍勃有不同大小的糖果棒：A[i] 是爱丽丝拥有的第 i 块糖的大小，B[j] 是鲍勃拥有的第 j 块糖的大小。
     *
     * 因为他们是朋友，所以他们想交换一个糖果棒，这样交换后，他们都有相同的糖果总量。（一个人拥有的糖果总量是他们拥有的糖果棒大小的总和。）
     *
     * 返回一个整数数组 ans，其中 ans[0] 是爱丽丝必须交换的糖果棒的大小，ans[1] 是 Bob 必须交换的糖果棒的大小。
     *
     * 如果有多个答案，你可以返回其中任何一个。保证答案存在。
     *
     *
     *
     * 示例 1：
     *
     * 输入：A = [1,1], B = [2,2]
     * 输出：[1,2]
     * 示例 2：
     *
     * 输入：A = [1,2], B = [2,3]
     * 输出：[1,2]
     * 示例 3：
     *
     * 输入：A = [2], B = [1,3]
     * 输出：[2,3]
     * 示例 4：
     *
     * 输入：A = [1,2,5], B = [2,4]
     * 输出：[5,4]
     *
     *
     * 提示：
     *
     * 1 <= A.length <= 10000
     * 1 <= B.length <= 10000
     * 1 <= A[i] <= 100000
     * 1 <= B[i] <= 100000
     * 保证爱丽丝与鲍勃的糖果总量不同。
     * 答案肯定存在。
     * @param A
     * @param B
     * @return
     */
    public int[] fairCandySwap(int[] A, int[] B) {
        int[] result = new int[2];
        int sumA = 0,sumB = 0;
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
        logResult(allCellsDistOrder(R,C,r0,c0));
    }

    /**
     * 1030. 距离顺序排列矩阵单元格
     * 给出 R 行 C 列的矩阵，其中的单元格的整数坐标为 (r, c)，满足 0 <= r < R 且 0 <= c < C。
     *
     * 另外，我们在该矩阵中给出了一个坐标为 (r0, c0) 的单元格。
     *
     * 返回矩阵中的所有单元格的坐标，并按到 (r0, c0) 的距离从最小到最大的顺序排，其中，两单元格(r1, c1) 和 (r2, c2) 之间的距离是曼哈顿距离，|r1 - r2| + |c1 - c2|。（你可以按任何满足此条件的顺序返回答案。）
     *
     *
     *
     * 示例 1：
     *
     * 输入：R = 1, C = 2, r0 = 0, c0 = 0
     * 输出：[[0,0],[0,1]]
     * 解释：从 (r0, c0) 到其他单元格的距离为：[0,1]
     * 示例 2：
     *
     * 输入：R = 2, C = 2, r0 = 0, c0 = 1
     * 输出：[[0,1],[0,0],[1,1],[1,0]]
     * 解释：从 (r0, c0) 到其他单元格的距离为：[0,1,1,2]
     * [[0,1],[1,1],[0,0],[1,0]] 也会被视作正确答案。
     * 示例 3：
     *
     * 输入：R = 2, C = 3, r0 = 1, c0 = 2
     * 输出：[[1,2],[0,2],[1,1],[0,1],[1,0],[0,0]]
     * 解释：从 (r0, c0) 到其他单元格的距离为：[0,1,1,2,2,3]
     * 其他满足题目要求的答案也会被视为正确，例如 [[1,2],[1,1],[0,2],[1,0],[0,1],[0,0]]。
     *
     *
     * 提示：
     *
     * 1 <= R <= 100
     * 1 <= C <= 100
     * 0 <= r0 < R
     * 0 <= c0 < C
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
        queue.offer(new int[]{r0, c0});
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] cell = queue.poll();
                int row = cell[0],col = cell[1];
                if (visited.contains(row + "-" + col)) {
                    continue;
                }
                visited.add(row + "-" + col);
                list.add(cell);
                for (int j = 0; j < 4; j++) {
                    int nextRow = row + DIR_ROW[j];
                    int nextCol = col + DIR_COL[j];

                    if (inArea(nextRow,nextCol,R,C)) {
                        queue.offer(new int[]{nextRow,nextCol});
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

        Arrays.sort(intervals,(a,b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);

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
     * 面试题 01.01. 判定字符是否唯一
     * 实现一个算法，确定一个字符串 s 的所有字符是否全都不同。
     *
     * 示例 1：
     *
     * 输入: s = "leetcode"
     * 输出: false
     * 示例 2：
     *
     * 输入: s = "abc"
     * 输出: true
     * 限制：
     *
     * 0 <= len(s) <= 100
     * 如果你不使用额外的数据结构，会很加分。
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
            int n = 1 << (c- 'a');
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
        int[][] relation = {{0,2},{2,1},{3,4},{2,3},{1,4},{2,0},{0,4}};
        logResult(numWays(n,relation,k));
    }
    /**
     * LCP 07. 传递信息
     * 小朋友 A 在和 ta 的小伙伴们玩传信息游戏，游戏规则如下：
     *
     * 有 n 名玩家，所有玩家编号分别为 0 ～ n-1，其中小朋友 A 的编号为 0
     * 每个玩家都有固定的若干个可传信息的其他玩家（也可能没有）。传信息的关系是单向的（比如 A 可以向 B 传信息，但 B 不能向 A 传信息）。
     * 每轮信息必须需要传递给另一个人，且信息可重复经过同一个人
     * 给定总玩家数 n，以及按 [玩家编号,对应可传递玩家编号] 关系组成的二维数组 relation。
     * 返回信息从小 A (编号 0 ) 经过 k 轮传递到编号为 n-1 的小伙伴处的方案数；若不能到达，返回 0。
     *
     * 示例 1：
     *
     * 输入：n = 5, relation = [[0,2],[2,1],[3,4],[2,3],[1,4],[2,0],[0,4]], k = 3
     *
     * 输出：3
     *
     * 解释：信息从小 A 编号 0 处开始，经 3 轮传递，到达编号 4。共有 3 种方案，分别是 0->2->0->4， 0->2->1->4， 0->2->3->4。
     *
     * 示例 2：
     *
     * 输入：n = 3, relation = [[0,2],[2,1]], k = 2
     *
     * 输出：0
     *
     * 解释：信息不能从小 A 处经过 2 轮传递到编号 2
     *
     * 限制：
     *
     * 2 <= n <= 10
     * 1 <= k <= 5
     * 1 <= relation.length <= 90, 且 relation[i].length == 2
     * 0 <= relation[i][0],relation[i][1] < n 且 relation[i][0] != relation[i][1]
     * @param n
     * @param relation
     * @param k
     * @return
     */
    public int numWays(int n, int[][] relation, int k) {
        // 深度优先遍历
        Map<Integer,List<Integer>> map = new HashMap<>();
        for (int[] r : relation) {
            List<Integer> list = map.getOrDefault(r[0],new ArrayList<>());
            list.add(r[1]);
            map.put(r[0],list);
        }
        numWays(map,0,n - 1,k);
        return numWay;
    }

    static int numWay = 0;

    private void numWays(Map<Integer,List<Integer>> map,int start,int end,int count) {
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
            numWays(map,num,end, count-1);
        }
    }


    /**
     * 面试题 17.10. 主要元素
     * 如果数组中多一半的数都是同一个，则称之为主要元素。给定一个整数数组，找到它的主要元素。若没有，返回-1。
     *
     * 示例 1：
     *
     * 输入：[1,2,5,9,5,9,5,5,5]
     * 输出：5
     *
     *
     * 示例 2：
     *
     * 输入：[3,2]
     * 输出：-1
     *
     *
     * 示例 3：
     *
     * 输入：[2,2,1,1,1,2,2]
     * 输出：2
     *
     *
     * 说明：
     * 你有办法在时间复杂度为 O(N)，空间复杂度为 O(1) 内完成吗？
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
        int[] nums = {1,-2,-3};
        logResult(minStartValue(nums));
    }


    /**
     * 5372. 逐步求和得到正数的最小值
     * 给你一个整数数组 nums 。你可以选定任意的 正数 startValue 作为初始值。
     *
     * 你需要从左到右遍历 nums 数组，并将 startValue 依次累加上 nums 数组中的值。
     *
     * 请你在确保累加和始终大于等于 1 的前提下，选出一个最小的 正数 作为 startValue 。
     *
     *
     *
     * 示例 1：
     *
     * 输入：nums = [-3,2,-3,4,2]
     * 输出：5
     * 解释：如果你选择 startValue = 4，在第三次累加时，和小于 1 。
     *                 累加求和
     *                 startValue = 4 | startValue = 5 | nums
     *                   (4 -3 ) = 1  | (5 -3 ) = 2    |  -3
     *                   (1 +2 ) = 3  | (2 +2 ) = 4    |   2
     *                   (3 -3 ) = 0  | (4 -3 ) = 1    |  -3
     *                   (0 +4 ) = 4  | (1 +4 ) = 5    |   4
     *                   (4 +2 ) = 6  | (5 +2 ) = 7    |   2
     * 示例 2：
     *
     * 输入：nums = [1,2]
     * 输出：1
     * 解释：最小的 startValue 需要是正数。
     * 示例 3：
     *
     * 输入：nums = [1,-2,-3]
     * 输出：5
     *
     *
     * 提示：
     *
     * 1 <= nums.length <= 100
     * -100 <= nums[i] <= 100
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
                log.debug("start:{}",startValue);
            }

        }

        return startValue;
    }


    @Test
    public void  testSpiralOrder2() {
        int[][] matrix = {{1 },{4 },{7 } };

        //int[][] matrix = {{1, 2, 3,4},{ 5, 6,7, 8},{  9,10,11,12},{  13,14,15,16} };
        //int[][] matrix = {};
        int[] result = spiralOrder2(matrix);
        log.info("result:{}", result);
    }



    /**
     * 面试题29. 顺时针打印矩阵
     * 输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字。
     *
     *
     *
     * 示例 1：
     *
     * 输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
     * 输出：[1,2,3,6,9,8,7,4,5]
     * 示例 2：
     *
     * 输入：matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
     * 输出：[1,2,3,4,8,12,11,10,9,5,6,7]
     *
     *
     * 限制：
     *
     * 0 <= matrix.length <= 100
     * 0 <= matrix[i].length <= 100
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
                case 0 : {
                    j++;
                    // 从左到右
                    if (j == endCol) {
                        flagIndex = 1;
                        endCol--;
                    }
                };break;
                case 1 : {
                    // 从上到下
                    i++;
                    if (i == endRow) {
                        flagIndex = 2;
                        endRow--;
                    }
                };break;
                case 2 : {
                    // 从右到左
                    j--;
                    if (j == startCol) {
                        flagIndex = 3;
                        startCol++;
                    }


                };break;
                case 3 : {
                    // 从下到上
                    i--;
                    if (i == startRow) {
                        flagIndex = 0;
                        startRow++;
                    }


                };break;
            }

        }



        return result;
    }


    @Test
    public void numberOfSubarrays() {
        int[] nums = {1,1,2,1,1};
        int k = 3;
        logResult(numberOfSubarrays(nums,k));
    }


    /**
     * 1248. 统计「优美子数组」
     * 给你一个整数数组 nums 和一个整数 k。
     *
     * 如果某个 连续 子数组中恰好有 k 个奇数数字，我们就认为这个子数组是「优美子数组」。
     *
     * 请返回这个数组中「优美子数组」的数目。
     *
     *
     *
     * 示例 1：
     *
     * 输入：nums = [1,1,2,1,1], k = 3
     * 输出：2
     * 解释：包含 3 个奇数的子数组是 [1,1,2,1] 和 [1,2,1,1] 。
     * 示例 2：
     *
     * 输入：nums = [2,4,6], k = 1
     * 输出：0
     * 解释：数列中不包含任何奇数，所以不存在优美子数组。
     * 示例 3：
     *
     * 输入：nums = [2,2,2,1,2,2,1,2,2,2], k = 2
     * 输出：16
     *
     *
     * 提示：
     *
     * 1 <= nums.length <= 50000
     * 1 <= nums[i] <= 10^5
     * 1 <= k <= nums.length
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
        log.debug("list:{}",list);
        for (int i = 1; i + k < list.size(); i++) {
            int num1 = list.get(i) - list.get(i - 1) ;
            int num2 = list.get(i + k) - list.get(i + k - 1);
            log.debug("num1:{},num2:{}",num1,num2);
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
     * 面试题51. 数组中的逆序对
     * 在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。输入一个数组，求出这个数组中的逆序对的总数。
     * 示例 1:
     *
     * 输入: [7,5,6,4]
     * 输出: 5
     *
     *
     * 限制：
     *
     * 0 <= 数组长度 <= 50000
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
        int[] a = {1,2,3,4,5};
        int[] result = constructArr(a);
        log.debug("result:{}",result);
    }
    /**
     * 面试题66. 构建乘积数组
     * 给定一个数组 A[0,1,…,n-1]，请构建一个数组 B[0,1,…,n-1]，其中 B 中的元素 B[i]=A[0]×A[1]×…×A[i-1]×A[i+1]×…×A[n-1]。不能使用除法。
     *
     *
     *
     * 示例:
     *
     * 输入: [1,2,3,4,5]
     * 输出: [120,60,40,30,24]
     *
     *
     * 提示：
     *
     * 所有元素乘积之和不会溢出 32 位整数
     * a.length <= 100000
     * @param a
     * @return
     */
    public int[] constructArr(int[] a) {
        int len = a.length;
        int[] result = new int[a.length];
        Arrays.fill(result,1);

        int sum = 1;
        for (int i = 1; i < len; i++) {
            sum *= a[i - 1];
            result[i] *= sum;
        }
        log.debug("result:{}",result);
        sum = 1;
        for (int i = len - 2; i >= 0; i--) {
            sum *= a[i + 1];
            result[i] *= sum;
        }
        log.debug("result:{}",result);

        return result;
    }


    @Test
    public void divingBoard() {
        int shorter = 1,longer = 2,k = 3;
        int[] result = divingBoard(shorter,longer,k);
        log.debug("result:{}",result);
    }
    /**
     * 面试题 16.11. 跳水板
     * 你正在使用一堆木板建造跳水板。有两种类型的木板，其中长度较短的木板长度为shorter，长度较长的木板长度为longer。
     * 你必须正好使用k块木板。编写一个方法，生成跳水板所有可能的长度。
     *
     * 返回的长度需要从小到大排列。
     *
     * 示例：
     *
     * 输入：
     * shorter = 1
     * longer = 2
     * k = 3
     * 输出： {3,4,5,6}
     * 提示：
     *
     * 0 < shorter <= longer
     * 0 <= k <= 100000
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
        if(k == 0) return new int[0];
        if(shorter == longer) {
            return new int[]{k * shorter};
        }


        int[] result = new int[k+1];
        for(int i = 0; i < k + 1; i ++) {
            result[i] = (k - i) * shorter + i * longer;
        }
        return result;
    }


    @Test
    public void masterMind() {
        String solution="RGBY",guess="GGRR";
        int[] result = masterMind(solution,guess);
        log.debug("result:{}",result);
    }

    /**
     * 面试题 16.15. 珠玑妙算
     * 珠玑妙算游戏（the game of master mind）的玩法如下。
     *
     * 计算机有4个槽，每个槽放一个球，颜色可能是红色（R）、黄色（Y）、绿色（G）或蓝色（B）。
     * 例如，计算机可能有RGGB 4种（槽1为红色，槽2、3为绿色，槽4为蓝色）。作为用户，你试图猜出颜色组合。打个比方，你可能会猜YRGB。
     * 要是猜对某个槽的颜色，则算一次“猜中”；要是只猜对颜色但槽位猜错了，则算一次“伪猜中”。注意，“猜中”不能算入“伪猜中”。
     *
     * 给定一种颜色组合solution和一个猜测guess，编写一个方法，返回猜中和伪猜中的次数answer，其中answer[0]为猜中的次数，answer[1]为伪猜中的次数。
     *
     * 示例：
     *
     * 输入： solution="RGBY",guess="GGRR"
     * 输出： [1,1]
     * 解释： 猜中1次，伪猜中1次。
     * 提示：
     *
     * len(solution) = len(guess) = 4
     * solution和guess仅包含"R","G","B","Y"这4种字符
     * @param solution
     * @param guess
     * @return
     */
    public int[] masterMind(String solution, String guess) {
        int[] result = new int[2];
        int same = 0, sameColor = 0;
        int[] minds = new int[4];
        if (Objects.equals(solution,guess)) {
            return new int[]{solution.length(),0};
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
            case 'R' : index = 0;break;
            case 'G' : index = 1;break;
            case 'B' : index = 2;break;
            case 'Y' : index = 3;break;
        }
        return index;
    }


    @Test
    public void floodFill2() {
        int[][] image = {{1,1,1},{1,1,0},{1,0,1}};
        int sr = 1,   sc = 1;
        int newColor = 2;

        int[][] result =  floodFill2(image,sr,sc,newColor);
        for (int[] a : result) {
            log.debug("result:{}",a);
        }
    }

    /**
     * 面试题 08.10. 颜色填充
     * 颜色填充。编写函数，实现许多图片编辑软件都支持的“颜色填充”功能。给定一个屏幕（以二维数组表示，元素为颜色值）、一个点和一个新的颜色值，将新颜色值填入这个点的周围区域，直到原来的颜色值全都改变。
     *
     * 示例1:
     *
     *  输入：
     * image = [[1,1,1],[1,1,0],[1,0,1]]
     * sr = 1, sc = 1, newColor = 2
     *  输出：[[2,2,2],[2,2,0],[2,0,1]]
     *  解释:
     * 在图像的正中间，(坐标(sr,sc)=(1,1)),
     * 在路径上所有符合条件的像素点的颜色都被更改成2。
     * 注意，右下角的像素没有更改为2，
     * 因为它不是在上下左右四个方向上与初始点相连的像素点。
     * 说明：
     *
     * image 和 image[0] 的长度在范围 [1, 50] 内。
     * 给出的初始点将满足 0 <= sr < image.length 和 0 <= sc < image[0].length。
     * image[i][j] 和 newColor 表示的颜色值在范围 [0, 65535]内。
     * @param image
     * @param sr
     * @param sc
     * @param newColor
     * @return
     */
    public int[][] floodFill2(int[][] image, int sr, int sc, int newColor) {
        floodFill2(image,sr,sc,image[sr][sc],newColor);
        return image;
    }

    private void floodFill2(int[][] image, int sr, int sc,int oldColor,int newColor) {
        if (oldColor == newColor) {
            return;
        }
        int rows = image.length;
        int cols = image[0].length;
        if (!inArea(sr,sc,rows,cols)) {
            return;
        }
        if (image[sr][sc] != oldColor) {
            return;
        }
        image[sr][sc] = newColor;
        for (int i = 0; i < 4; i++) {
            int row = sr + DIR_ROW[i];
            int col = sc + DIR_COL[i];
            floodFill2(image,row,col,oldColor,newColor);
        }
    }


    @Test
    public void existTest() {
        char[][] board = {{'A','B','C','E'},{'S','F','C','S'},{'A','D','E','E'}};
        //char[][] board = {{'A','A'} };
        String word = "ABCCED";
        logResult(exist(board,word));
    }
    /**
     * 面试题12. 矩阵中的路径
     * 请设计一个函数，用来判断在一个矩阵中是否存在一条包含某字符串所有字符的路径。路径可以从矩阵中的任意一格开始，
     * 每一步可以在矩阵中向左、右、上、下移动一格。如果一条路径经过了矩阵的某一格，那么该路径不能再次进入该格子。
     * 例如，在下面的3×4的矩阵中包含一条字符串“bfce”的路径（路径中的字母用加粗标出）。
     *
     * [["a","b","c","e"],
     * ["s","f","c","s"],
     * ["a","d","e","e"]]
     *
     * 但矩阵中不包含字符串“abfb”的路径，因为字符串的第一个字符b占据了矩阵中的第一行第二个格子之后，路径不能再次进入这个格子。
     *
     *
     *
     * 示例 1：
     *
     * 输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCCED"
     * 输出：true
     * 示例 2：
     *
     * 输入：board = [["a","b"],["c","d"]], word = "abcd"
     * 输出：false
     * 提示：
     *
     * 1 <= board.length <= 200
     * 1 <= board[i].length <= 200
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
                if (existWord(board,i,j,word,0)) {
                    return true;
                }

            }
        }
        return false;
    }


    private boolean existWord(char[][] board, int row,int col, String word,int index) {
        int rows = board.length;
        int cols = board[0].length;
        if (board[row][col] != word.charAt(index)) {
            log.debug("false1:{},{}",row,col);
            return false;
        }
        if (index == word.length() - 1 &&  board[row][col] == word.charAt(index)) {
            return true;
        }
        char tmp = board[row][col];
        board[row][col] = '/';
        for (int i = 0; i < 4; i++) {
            int rowIndex = row + DIR_ROW[i];
            int colIndex = col + DIR_COL[i];
            if (inArea(rowIndex,colIndex,rows,cols) && existWord(board, rowIndex,colIndex,word,index + 1)) {
                return true;
            }
        }
        board[row][col] = tmp;
        return false;
    }


    @Test
    public void countTriplets() {
        int[] arr = {1,1,1,1,1};

        logResult(countTriplets(arr));
    }

    /**
     * 5405. 形成两个异或相等数组的三元组数目
     * 给你一个整数数组 arr 。
     *
     * 现需要从数组中取三个下标 i、j 和 k ，其中 (0 <= i < j <= k < arr.length) 。
     *
     * a 和 b 定义如下：
     *
     * a = arr[i] ^ arr[i + 1] ^ ... ^ arr[j - 1]
     * b = arr[j] ^ arr[j + 1] ^ ... ^ arr[k]
     * 注意：^ 表示 按位异或 操作。
     *
     * 请返回能够令 a == b 成立的三元组 (i, j , k) 的数目。
     *
     *
     *
     * 示例 1：
     *
     * 输入：arr = [2,3,1,6,7]
     * 输出：4
     * 解释：满足题意的三元组分别是 (0,1,2), (0,2,2), (2,3,4) 以及 (2,4,4)
     * 示例 2：
     *
     * 输入：arr = [1,1,1,1,1]
     * 输出：10
     * 示例 3：
     *
     * 输入：arr = [2,3]
     * 输出：0
     * 示例 4：
     *
     * 输入：arr = [1,3,5,7,9]
     * 输出：3
     * 示例 5：
     *
     * 输入：arr = [7,11,12,9,5,2,7,17,22]
     * 输出：8
     *
     *
     * 提示：
     *
     * 1 <= arr.length <= 300
     * 1 <= arr[i] <= 10^8
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

                //log.debug("start {},end {}, sum ,{}  a:{}",i,j,sum,a);
                count += countTriplets(arr,i,j,sum);
            }
        }
        return count;
    }


    private int countTriplets(int[] arr,int start,int end,int sum) {
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
    public void minTime( ) {
        int n = 7;
        int[][] edges = {{0,1},{0,2},{1,4},{1,5},{2,3},{2,6}};
        List<Boolean> hasApple = Arrays.asList(false,false,false,false,false,false,false);
        logResult(minTime(n,edges,hasApple));
    }

    /**
     * 5406. 收集树上所有苹果的最少时间
     * 给你一棵有 n 个节点的无向树，节点编号为 0 到 n-1 ，它们中有一些节点有苹果。通过树上的一条边，需要花费 1 秒钟。你从 节点 0 出发，
     * 请你返回最少需要多少秒，可以收集到所有苹果，并回到节点 0 。
     *
     * 无向树的边由 edges 给出，其中 edges[i] = [fromi, toi] ，表示有一条边连接 from 和 toi 。除此以外，还有一个布尔数组 hasApple ，
     * 其中 hasApple[i] = true 代表节点 i 有一个苹果，否则，节点 i 没有苹果。
     *
     *
     *
     * 示例 1：
     *
     *
     *
     * 输入：n = 7, edges = [[0,1],[0,2],[1,4],[1,5],[2,3],[2,6]], hasApple = [false,false,true,false,true,true,false]
     * 输出：8
     * 解释：上图展示了给定的树，其中红色节点表示有苹果。一个能收集到所有苹果的最优方案由绿色箭头表示。
     * 示例 2：
     *
     *
     *
     * 输入：n = 7, edges = [[0,1],[0,2],[1,4],[1,5],[2,3],[2,6]], hasApple = [false,false,true,false,false,true,false]
     * 输出：6
     * 解释：上图展示了给定的树，其中红色节点表示有苹果。一个能收集到所有苹果的最优方案由绿色箭头表示。
     * 示例 3：
     *
     * 输入：n = 7, edges = [[0,1],[0,2],[1,4],[1,5],[2,3],[2,6]], hasApple = [false,false,false,false,false,false,false]
     * 输出：0
     *
     *
     * 提示：
     *
     * 1 <= n <= 10^5
     * edges.length == n-1
     * edges[i].length == 2
     * 0 <= fromi, toi <= n-1
     * fromi < toi
     * hasApple.length == n
     * @param n
     * @param edges
     * @param hasApple
     * @return
     */
    public int minTime(int n, int[][] edges, List<Boolean> hasApple) {
        int result = 0;

        Map<Integer,List<Integer>> map = new HashMap<>();

        for (int[] edge : edges) {
            List<Integer> list = map.getOrDefault(edge[0],new ArrayList<>());
            list.add(edge[1]);
            map.putIfAbsent(edge[0],list);
        }

        minTime(0,0,map,hasApple);
        log.debug("a :{}",appleTime);
        return appleTime;
    }

    static int appleTime = 0;

    private boolean minTime(int root,int time,Map<Integer,List<Integer>> map,List<Boolean> hasApple) {

        boolean flag = false;
        if (hasApple.get(root)) {
            flag = true;
            log.debug("root :{},time :{}",root,time);
        }
        List<Integer> list = map.get(root);
        if (Objects.isNull(list)) {
            if (flag) {
                appleTime += time;
            }
            return flag;
        }
        for (int child : list) {
            flag |= minTime(child,1,map,hasApple);
        }
        if (flag) {
            appleTime += time;
        }
        return flag;
    }


    /**
     * 228. 汇总区间
     * 给定一个无重复元素的有序整数数组，返回数组区间范围的汇总。
     *
     * 示例 1:
     *
     * 输入: [0,1,2,4,5,7]
     * 输出: ["0->2","4->5","7"]
     * 解释: 0,1,2 可组成一个连续的区间; 4,5 可组成一个连续的区间。
     * 示例 2:
     *
     * 输入: [0,2,3,4,6,8,9]
     * 输出: ["0","2->4","6","8->9"]
     * 解释: 2,3,4 可组成一个连续的区间; 8,9 可组成一个连续的区间。
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
     * 229. 求众数 II
     * 给定一个大小为 n 的数组，找出其中所有出现超过 ⌊ n/3 ⌋ 次的元素。
     *
     * 说明: 要求算法的时间复杂度为 O(n)，空间复杂度为 O(1)。
     *
     * 示例 1:
     *
     * 输入: [3,2,3]
     * 输出: [3]
     * 示例 2:
     *
     * 输入: [1,1,1,3,3,2,2,2]
     * 输出: [1,2]
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
        int num1 = nums[0],count1 = 1;
        int num2 = nums[0],count2 = 1;
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
     * 442. 数组中重复的数据
     * 给定一个整数数组 a，其中1 ≤ a[i] ≤ n （n为数组长度）, 其中有些元素出现两次而其他元素出现一次。
     *
     * 找到所有出现两次的元素。
     *
     * 你可以不用到任何额外空间并在O(n)时间复杂度内解决这个问题吗？
     *
     * 示例：
     *
     * 输入:
     * [4,3,2,7,8,2,3,1]
     *
     * 输出:
     * [2,3]
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
     * 373. 查找和最小的K对数字
     * 给定两个以升序排列的整形数组 nums1 和 nums2, 以及一个整数 k。
     *
     * 定义一对值 (u,v)，其中第一个元素来自 nums1，第二个元素来自 nums2。
     *
     * 找到和最小的 k 对数字 (u1,v1), (u2,v2) ... (uk,vk)。
     *
     * 示例 1:
     *
     * 输入: nums1 = [1,7,11], nums2 = [2,4,6], k = 3
     * 输出: [1,2],[1,4],[1,6]
     * 解释: 返回序列中的前 3 对数：
     *      [1,2],[1,4],[1,6],[7,2],[7,4],[11,2],[7,6],[11,4],[11,6]
     * 示例 2:
     *
     * 输入: nums1 = [1,1,2], nums2 = [1,2,3], k = 2
     * 输出: [1,1],[1,1]
     * 解释: 返回序列中的前 2 对数：
     *      [1,1],[1,1],[1,2],[2,1],[1,2],[2,2],[1,3],[1,3],[2,3]
     * 示例 3:
     *
     * 输入: nums1 = [1,2], nums2 = [3], k = 3
     * 输出: [1,3],[2,3]
     * 解释: 也可能序列中所有的数对都被返回:[1,3],[2,3]
     * @param nums1
     * @param nums2
     * @param k
     * @return
     */
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        // 构建最大堆
        PriorityQueue<int[]> heap = new PriorityQueue<>((a,b) ->
                b[0] + b[1] - a[0] - a[1]);

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
        for (Iterator<int[]> iterator = heap.iterator(); iterator.hasNext();) {
            int[] nums = iterator.next();
            List<Integer> list = new ArrayList<>();
            list.add(nums[0]);
            list.add(nums[1]);
            result.add(list);
        }
        return result;
    }


    /**
     * 5412. 在既定时间做作业的学生人数
     * 给你两个整数数组 startTime（开始时间）和 endTime（结束时间），并指定一个整数 queryTime 作为查询时间。
     *
     * 已知，第 i 名学生在 startTime[i] 时开始写作业并于 endTime[i] 时完成作业。
     *
     * 请返回在查询时间 queryTime 时正在做作业的学生人数。形式上，返回能够使 queryTime 处于区间 [startTime[i], endTime[i]]（含）的学生人数。
     *
     *
     *
     * 示例 1：
     *
     * 输入：startTime = [1,2,3], endTime = [3,2,7], queryTime = 4
     * 输出：1
     * 解释：一共有 3 名学生。
     * 第一名学生在时间 1 开始写作业，并于时间 3 完成作业，在时间 4 没有处于做作业的状态。
     * 第二名学生在时间 2 开始写作业，并于时间 2 完成作业，在时间 4 没有处于做作业的状态。
     * 第二名学生在时间 3 开始写作业，预计于时间 7 完成作业，这是是唯一一名在时间 4 时正在做作业的学生。
     * 示例 2：
     *
     * 输入：startTime = [4], endTime = [4], queryTime = 4
     * 输出：1
     * 解释：在查询时间只有一名学生在做作业。
     * 示例 3：
     *
     * 输入：startTime = [4], endTime = [4], queryTime = 5
     * 输出：0
     * 示例 4：
     *
     * 输入：startTime = [1,1,1,1], endTime = [1,3,2,4], queryTime = 7
     * 输出：0
     * 示例 5：
     *
     * 输入：startTime = [9,8,7,6,5,4,3,2,1], endTime = [10,10,10,10,10,10,10,10,10], queryTime = 5
     * 输出：5
     *
     *
     * 提示：
     *
     * startTime.length == endTime.length
     * 1 <= startTime.length <= 100
     * 1 <= startTime[i] <= endTime[i] <= 1000
     * 1 <= queryTime <= 1000
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
        List<String> list1 = new ArrayList<>( );
        list1.add("leetcode");
        list1.add("google");
        list1.add("facebook");
        List<String> list2 = new ArrayList<>( );
        list2.add("google");
        list2.add("facebook");
        List<String> list3 = new ArrayList<>( );
        list3.add("google");
        list3.add("facebook");
        List<String> list4 = new ArrayList<>( );
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
     * 5414. 收藏清单
     * 给你一个数组 favoriteCompanies ，其中 favoriteCompanies[i] 是第 i 名用户收藏的公司清单（下标从 0 开始）。
     *
     * 请找出不是其他任何人收藏的公司清单的子集的收藏清单，并返回该清单下标。下标需要按升序排列。
     *
     *
     *
     * 示例 1：
     *
     * 输入：favoriteCompanies = [["leetcode","google","facebook"],["google","microsoft"],["google","facebook"],["google"],["amazon"]]
     * 输出：[0,1,4]
     * 解释：
     * favoriteCompanies[2]=["google","facebook"] 是 favoriteCompanies[0]=["leetcode","google","facebook"] 的子集。
     * favoriteCompanies[3]=["google"] 是 favoriteCompanies[0]=["leetcode","google","facebook"] 和 favoriteCompanies[1]=["google","microsoft"] 的子集。
     * 其余的收藏清单均不是其他任何人收藏的公司清单的子集，因此，答案为 [0,1,4] 。
     * 示例 2：
     *
     * 输入：favoriteCompanies = [["leetcode","google","facebook"],["leetcode","amazon"],["facebook","google"]]
     * 输出：[0,1]
     * 解释：favoriteCompanies[2]=["facebook","google"] 是 favoriteCompanies[0]=["leetcode","google","facebook"] 的子集，因此，答案为 [0,1] 。
     * 示例 3：
     *
     * 输入：favoriteCompanies = [["leetcode"],["google"],["facebook"],["amazon"]]
     * 输出：[0,1,2,3]
     *
     *
     * 提示：
     *
     * 1 <= favoriteCompanies.length <= 100
     * 1 <= favoriteCompanies[i].length <= 500
     * 1 <= favoriteCompanies[i][j].length <= 20
     * favoriteCompanies[i] 中的所有字符串 各不相同 。
     * 用户收藏的公司清单也 各不相同 ，也就是说，即便我们按字母顺序排序每个清单， favoriteCompanies[i] != favoriteCompanies[j] 仍然成立。
     * 所有字符串仅包含小写英文字母。
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
     * @param index1
     * @param index2
     */
    private List<Integer> calIndex(List<Integer> index1,List<Integer> index2) {
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < index1.size(); i++) {
            for (int j = 0; j < index2.size(); j++) {
                if (Objects.equals(index1.get(i),index2.get(j))) {
                    result.add(index1.get(i));
                }
            }
        }
        return result;
    }

    @Test
    public void pacificAtlantic() {
        int[][] matrix = {{1,2,2,3,5},{3,2,3,4,4},{2,3,4,3,1},{6,7,1,4,5},{5,1,1,2,4}};
        List<List<Integer>> result = pacificAtlantic(matrix);
        logResult(result);
    }


    /**
     * 417. 太平洋大西洋水流问题
     * 给定一个 m x n 的非负整数矩阵来表示一片大陆上各个单元格的高度。“太平洋”处于大陆的左边界和上边界，而“大西洋”处于大陆的右边界和下边界。
     *
     * 规定水流只能按照上、下、左、右四个方向流动，且只能从高到低或者在同等高度上流动。
     *
     * 请找出那些水流既可以流动到“太平洋”，又能流动到“大西洋”的陆地单元的坐标。
     *
     *
     *
     * 提示：
     *
     * 输出坐标的顺序不重要
     * m 和 n 都小于150
     *
     *
     * 示例：
     *
     *
     *
     * 给定下面的 5x5 矩阵:
     *
     *   太平洋 ~   ~   ~   ~   ~
     *        ~  1   2   2   3  (5) *
     *        ~  3   2   3  (4) (4) *
     *        ~  2   4  (5)  3   1  *
     *        ~ (6) (7)  1   4   5  *
     *        ~ (5)  1   1   2   4  *
     *           *   *   *   *   * 大西洋
     *
     * 返回:
     *
     * [[0, 4], [1, 3], [1, 4], [2, 2], [3, 0], [3, 1], [4, 0]] (上图中带括号的单元).
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
            checkNums(matrix,canReach1,0,i);
        }
        //  左方 第1列
        for (int i = 0; i < rows; i++) {
            // 查找比num小的点
            checkNums(matrix,canReach1,i,0);
        }
        boolean[][] canReach2 = new boolean[rows][cols];
        //  下方 最后1行
        for (int i = 0; i < cols - 1; i++) {
            // 查找比num小的点
            checkNums(matrix,canReach2,rows - 1,i );
        }
        //  右方 最后1列
        for (int i = 0; i < rows; i++) {
            // 查找比num小的点
            checkNums(matrix,canReach2,i,cols - 1 );
        }

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (canReach1[i][j] && canReach2[i][j] ) {
                    result.add(Arrays.asList(i,j));
                }
            }
        }
        return result;
    }

    private void checkNums(int[][] matrix,boolean[][] canReach,int row,int col) {
        int num = matrix[row][col];
        if (canReach[row][col]) {
            return;
        }
        canReach[row][col] = true;
        for (int i = 0; i < 4; i++) {
            int r = row + DIR_ROW[i];
            int c = col + DIR_COL[i];
            if (inArea(r,c,matrix.length,matrix[0].length) && matrix[r][c] >= num) {
                checkNums(matrix,canReach,r,c );
            }
        }
    }


    /**
     * 5408. 通过翻转子数组使两个数组相等
     * 给你两个长度相同的整数数组 target 和 arr 。
     *
     * 每一步中，你可以选择 arr 的任意 非空子数组 并将它翻转。你可以执行此过程任意次。
     *
     * 如果你能让 arr 变得与 target 相同，返回 True；否则，返回 False 。
     *
     *
     *
     * 示例 1：
     *
     * 输入：target = [1,2,3,4], arr = [2,4,1,3]
     * 输出：true
     * 解释：你可以按照如下步骤使 arr 变成 target：
     * 1- 翻转子数组 [2,4,1] ，arr 变成 [1,4,2,3]
     * 2- 翻转子数组 [4,2] ，arr 变成 [1,2,4,3]
     * 3- 翻转子数组 [4,3] ，arr 变成 [1,2,3,4]
     * 上述方法并不是唯一的，还存在多种将 arr 变成 target 的方法。
     * 示例 2：
     *
     * 输入：target = [7], arr = [7]
     * 输出：true
     * 解释：arr 不需要做任何翻转已经与 target 相等。
     * 示例 3：
     *
     * 输入：target = [1,12], arr = [12,1]
     * 输出：true
     * 示例 4：
     *
     * 输入：target = [3,7,9], arr = [3,7,11]
     * 输出：false
     * 解释：arr 没有数字 9 ，所以无论如何也无法变成 target 。
     * 示例 5：
     *
     * 输入：target = [1,1,1,1,1], arr = [1,1,1,1,1]
     * 输出：true
     *
     *
     * 提示：
     *
     * target.length == arr.length
     * 1 <= target.length <= 1000
     * 1 <= target[i] <= 1000
     * 1 <= arr[i] <= 1000
     * @param target
     * @param arr
     * @return
     */
    public boolean canBeEqual(int[] target, int[] arr) {
        // 判断两个集合元素是否相同即可
        int[] nums = new int[1001];
        for (int num :target) {
            nums[num]++;
        }
        for (int num :arr) {
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
     * 5424. 数组中两元素的最大乘积
     * 给你一个整数数组 nums，请你选择数组的两个不同下标 i 和 j，使 (nums[i]-1)*(nums[j]-1) 取得最大值。
     *
     * 请你计算并返回该式的最大值。
     *
     *
     *
     * 示例 1：
     *
     * 输入：nums = [3,4,5,2]
     * 输出：12
     * 解释：如果选择下标 i=1 和 j=2（下标从 0 开始），则可以获得最大值，(nums[1]-1)*(nums[2]-1) = (4-1)*(5-1) = 3*4 = 12 。
     * 示例 2：
     *
     * 输入：nums = [1,5,4,5]
     * 输出：16
     * 解释：选择下标 i=1 和 j=3（下标从 0 开始），则可以获得最大值 (5-1)*(5-1) = 16 。
     * 示例 3：
     *
     * 输入：nums = [3,7]
     * 输出：12
     *
     *
     * 提示：
     *
     * 2 <= nums.length <= 500
     * 1 <= nums[i] <= 10^3
     * @param nums
     * @return
     */
    public int maxProduct2(int[] nums) {
        if (nums.length == 2) {
            return  (nums[0] - 1) * (nums[1] - 1);
        }
        Arrays.sort(nums);
        return  (nums[nums.length - 1] - 1) * (nums[nums.length - 2] - 1);
    }


    public void maxArea2() {
        int h = 1000000000,w = 1000000000;

        //logResult(maxArea(h,w,horizontalCuts,verticalCuts));
    }

    /**
     * 5425. 切割后面积最大的蛋糕
     * 矩形蛋糕的高度为 h 且宽度为 w，给你两个整数数组 horizontalCuts 和 verticalCuts，其中 horizontalCuts[i] 是从矩形蛋糕顶部到第
     * i 个水平切口的距离，类似地， verticalCuts[j] 是从矩形蛋糕的左侧到第 j 个竖直切口的距离。
     *
     * 请你按数组 horizontalCuts 和 verticalCuts 中提供的水平和竖直位置切割后，请你找出 面积最大 的那份蛋糕，并返回其 面积 。
     * 由于答案可能是一个很大的数字，因此需要将结果对 10^9 + 7 取余后返回。
     *
     *
     *
     * 示例 1：
     *
     *
     *
     * 输入：h = 5, w = 4, horizontalCuts = [1,2,4], verticalCuts = [1,3]
     * 输出：4
     * 解释：上图所示的矩阵蛋糕中，红色线表示水平和竖直方向上的切口。切割蛋糕后，绿色的那份蛋糕面积最大。
     * 示例 2：
     *
     *
     *
     * 输入：h = 5, w = 4, horizontalCuts = [3,1], verticalCuts = [1]
     * 输出：6
     * 解释：上图所示的矩阵蛋糕中，红色线表示水平和竖直方向上的切口。切割蛋糕后，绿色和黄色的两份蛋糕面积最大。
     * 示例 3：
     *
     * 输入：h = 5, w = 4, horizontalCuts = [3], verticalCuts = [3]
     * 输出：9
     *
     *
     * 提示：
     *
     * 2 <= h, w <= 10^9
     * 1 <= horizontalCuts.length < min(h, 10^5)
     * 1 <= verticalCuts.length < min(w, 10^5)
     * 1 <= horizontalCuts[i] < h
     * 1 <= verticalCuts[i] < w
     * 题目数据保证 horizontalCuts 中的所有元素各不相同
     * 题目数据保证 verticalCuts 中的所有元素各不相同
     * @param h
     * @param w
     * @param horizontalCuts
     * @param verticalCuts
     * @return
     */
    public int maxArea(int h, int w, int[] horizontalCuts, int[] verticalCuts) {
        int mod = 1000000007;
        long maxHeight = 0,maxWidth = 0;
        Arrays.sort(horizontalCuts);
        Arrays.sort(verticalCuts);
        for (int i = 0; i < horizontalCuts.length; i++) {
            long height ;
            if (i == 0) {
                height = horizontalCuts[i];
            } else  {
                height = horizontalCuts[i] - horizontalCuts[i - 1];
            }
            maxHeight = Math.max(maxHeight,height);
            if (i == horizontalCuts.length - 1) {
                height = h - horizontalCuts[i];
                maxHeight = Math.max(maxHeight,height);
            }
        }
        for (int i = 0; i < verticalCuts.length; i++) {
            long width  ;
            if (i == 0) {
                width = verticalCuts[i];
            } else  {
                width = verticalCuts[i] - verticalCuts[i - 1];
            }
            maxWidth = Math.max(maxWidth,width);
            if (i == verticalCuts.length - 1) {
                width = w - verticalCuts[i];
                maxWidth = Math.max(maxWidth,width);
            }
        }



        return (int)((maxHeight % mod) * (maxWidth % mod)) % mod;
    }


    /**
     * 面试题 01.08. 零矩阵
     * 编写一种算法，若M × N矩阵中某个元素为0，则将其所在的行与列清零。
     *
     *
     *
     * 示例 1：
     *
     * 输入：
     * [
     *   [1,1,1],
     *   [1,0,1],
     *   [1,1,1]
     * ]
     * 输出：
     * [
     *   [1,0,1],
     *   [0,0,0],
     *   [1,0,1]
     * ]
     * 示例 2：
     *
     * 输入：
     * [
     *   [0,1,2,0],
     *   [3,4,5,2],
     *   [1,3,1,5]
     * ]
     * 输出：
     * [
     *   [0,0,0,0],
     *   [0,4,5,0],
     *   [0,3,1,0]
     * ]
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
}

