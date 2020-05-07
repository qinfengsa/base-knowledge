package com.qinfengsa.algorithm.binary;

import com.qinfengsa.algorithm.util.LogUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Arrays;

/**
 * 二分查找
 * @author: qinfengsa
 * @date: 2019/8/10 10:44
 */
@Slf4j
public class SearchTest {

    public void searchTest1() {

    }

    /**
     * 二分查找，循环实现
     * @param nums
     * @param target
     * @return
     */
    private int commonBinarySearch(int[] nums, int target) {
        int len = nums.length;
        int low = 0,high = len - 1;
        if (target < nums[low] || target > nums[high]) {
            return -1;
        }
        while (low < high) {
            int mid = low + (high-low)/2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] > target) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return -1;
    }


    /**
     * 二分查找，递归实现
     * @param nums
     * @param target
     * @param low
     * @param high
     * @return
     */
    private int recursionBinarySearch(int[] nums, int target,int low, int high) {
        if (target < nums[low] || target > nums[high] || low > high) {
            return -1;
        }

        int mid = low + (high-low)/2;
        if (nums[mid] == target) {
            return mid;
        } else if (nums[mid] > target) {
            return recursionBinarySearch(nums,target,low,mid -1);
        } else {
            return recursionBinarySearch(nums,target,mid + 1,high);
        }
    }

    @Test
    public void searchMatrix() {
        //int[][] matrix = {{1,   3,  5,  7},{10, 11, 16, 20},{23, 30, 34, 50}};
        //int target = 7;
        int[][] matrix = {{1}};
        int target = 1;
        LogUtils.logResult(searchMatrix(matrix,target));
    }
    /**
     * 74. 搜索二维矩阵
     * 编写一个高效的算法来判断 m x n 矩阵中，是否存在一个目标值。该矩阵具有如下特性：
     *
     * 每行中的整数从左到右按升序排列。
     * 每行的第一个整数大于前一行的最后一个整数。
     * 示例 1:
     *
     * 输入:
     * matrix = [
     *   [1,   3,  5,  7],
     *   [10, 11, 16, 20],
     *   [23, 30, 34, 50]
     * ]
     * target = 3
     * 输出: true
     * 示例 2:
     *
     * 输入:
     * matrix = [
     *   [1,   3,  5,  7],
     *   [10, 11, 16, 20],
     *   [23, 30, 34, 50]
     * ]
     * target = 13
     * 输出: false
     * @param matrix
     * @param target
     * @return
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        // 二分法, 先判断在那一行, 然后判断 列
        /*int rows = matrix.length;
        if (rows == 0) {
            return false;
        }

        int cols = matrix[0].length;
        if (cols == 0) {
            return false;
        }

        int low = 0, high = rows - 1;
        if (target < matrix[low][0] || target > matrix[high][cols - 1]) {
            return false;
        }
        while (low <= high) {
            int mid = low + ((high-low)>> 1);
            if (matrix[mid][0] == target) {
                return true;
            }
            if (matrix[mid][0] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        // 锁定行
        int row = low - 1;
        log.debug("row:{}",row);
        low = 0;
        high = cols - 1;
        if (target < matrix[row][low] || target > matrix[row][high]) {
            return false;
        }
        while (low <= high) {
            int mid = low + ((high-low)>> 1);
            if (matrix[row][mid] == target) {
                return true;
            }
            if (matrix[row][mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return false;*/


        int rows = matrix.length;
        if (rows == 0) {
            return false;
        }

        int cols = matrix[0].length;
        if (cols == 0) {
            return false;
        }
        int high = rows*cols - 1;
        int low = 0;
        while(low <= high){
            int mid = low + ((high-low)>> 1);
            int col = mid/cols;
            int row = mid%cols;
            if (matrix[col][row] == target){
                return true;
            }
            if (matrix[col][row] < target){
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return false;
    }


}
