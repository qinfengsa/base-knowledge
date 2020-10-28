package com.qinfengsa.algorithm.binary;

import static com.qinfengsa.algorithm.util.LogUtils.logResult;

import com.qinfengsa.algorithm.util.LogUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * 二分查找
 *
 * @author: qinfengsa
 * @date: 2019/8/10 10:44
 */
@Slf4j
public class SearchTest {

    public void searchTest1() {}

    /**
     * 二分查找，循环实现
     *
     * @param nums
     * @param target
     * @return
     */
    private int commonBinarySearch(int[] nums, int target) {
        int len = nums.length;
        int low = 0, high = len - 1;
        if (target < nums[low] || target > nums[high]) {
            return -1;
        }
        while (low < high) {
            int mid = low + (high - low) / 2;
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
     *
     * @param nums
     * @param target
     * @param low
     * @param high
     * @return
     */
    private int recursionBinarySearch(int[] nums, int target, int low, int high) {
        if (target < nums[low] || target > nums[high] || low > high) {
            return -1;
        }

        int mid = low + (high - low) / 2;
        if (nums[mid] == target) {
            return mid;
        } else if (nums[mid] > target) {
            return recursionBinarySearch(nums, target, low, mid - 1);
        } else {
            return recursionBinarySearch(nums, target, mid + 1, high);
        }
    }

    @Test
    public void searchMatrix() {
        // int[][] matrix = {{1,   3,  5,  7},{10, 11, 16, 20},{23, 30, 34, 50}};
        // int target = 7;
        int[][] matrix = {{1}};
        int target = 1;
        LogUtils.logResult(searchMatrix(matrix, target));
    }
    /**
     * 74. 搜索二维矩阵 编写一个高效的算法来判断 m x n 矩阵中，是否存在一个目标值。该矩阵具有如下特性：
     *
     * <p>每行中的整数从左到右按升序排列。 每行的第一个整数大于前一行的最后一个整数。 示例 1:
     *
     * <p>输入: matrix = [ [1, 3, 5, 7], [10, 11, 16, 20], [23, 30, 34, 50] ] target = 3 输出: true 示例
     * 2:
     *
     * <p>输入: matrix = [ [1, 3, 5, 7], [10, 11, 16, 20], [23, 30, 34, 50] ] target = 13 输出: false
     *
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
        int high = rows * cols - 1;
        int low = 0;
        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            int col = mid / cols;
            int row = mid % cols;
            if (matrix[col][row] == target) {
                return true;
            }
            if (matrix[col][row] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return false;
    }

    /**
     * 二分查找 给定一个 n 个元素有序的（升序）整型数组 nums 和一个目标值 target ，写一个函数搜索 nums 中的 target，如果目标值存在返回下标，否则返回 -1。
     *
     * <p>示例 1:
     *
     * <p>输入: nums = [-1,0,3,5,9,12], target = 9 输出: 4 解释: 9 出现在 nums 中并且下标为 4 示例 2:
     *
     * <p>输入: nums = [-1,0,3,5,9,12], target = 2 输出: -1 解释: 2 不存在 nums 中因此返回 -1
     *
     * <p>提示：
     *
     * <p>你可以假设 nums 中的所有元素是不重复的。 n 将在 [1, 10000]之间。 nums 的每个元素都将在 [-9999, 9999]之间。
     */
    @Test
    public void testSearch() {
        int[] nums = {-1, 0, 3, 5, 9, 12};
        int target = 2;
        int result = search(nums, target);
        log.debug("result:{}", result);
    }

    /**
     * 二分查找 给定一个 n 个元素有序的（升序）整型数组 nums 和一个目标值 target ，写一个函数搜索 nums 中的 target，如果目标值存在返回下标，否则返回 -1。
     *
     * <p>示例 1:
     *
     * <p>输入: nums = [-1,0,3,5,9,12], target = 9 输出: 4 解释: 9 出现在 nums 中并且下标为 4 示例 2:
     *
     * <p>输入: nums = [-1,0,3,5,9,12], target = 2 输出: -1 解释: 2 不存在 nums 中因此返回 -1
     *
     * <p>提示：
     *
     * <p>你可以假设 nums 中的所有元素是不重复的。 n 将在 [1, 10000]之间。 nums 的每个元素都将在 [-9999, 9999]之间。
     *
     * @param nums
     * @param target
     * @return
     */
    public int search(int[] nums, int target) {
        int low = 0;
        int high = nums.length - 1;

        while (low <= high) {
            int mid = (low + high) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] > target) {
                high = mid - 1;
            } else if (nums[mid] < target) {
                low = mid + 1;
            }
        }

        return -1;
    }

    @Test
    public void mySqrtTest() {

        int target = 9;
        int result = mySqrt(target);
        log.debug("result:{}", result);
    }

    /**
     * x 的平方根 实现 int sqrt(int x) 函数。
     *
     * <p>计算并返回 x 的平方根，其中 x 是非负整数。
     *
     * <p>由于返回类型是整数，结果只保留整数的部分，小数部分将被舍去。
     *
     * <p>示例 1:
     *
     * <p>输入: 4 输出: 2 示例 2:
     *
     * <p>输入: 8 输出: 2 说明: 8 的平方根是 2.82842..., 由于返回类型是整数，小数部分将被舍去。
     *
     * @param x
     * @return
     */
    public int mySqrt(int x) {
        if (x == 0) {
            return 0;
        }
        if (x <= 3) {
            return 1;
        }
        int a = x / 2;
        Long num = Long.valueOf(a);
        Long max = num;
        while (num * num > x || (num + 1) * (num + 1) <= x) {

            if (num * num > x) {
                max = num;
                num = num / 2;

            } else {
                num = (num + max) / 2;
            }
        }
        return num.intValue();
        /*while (true) {

            if (num*num <= x && (num+1)*(num+1) > x) {
                return num;
            }
            if (num*num > x) {
                max = num;
                num = num/2;

            } else {
                num = (num + max)/2;
            }
        }*/

    }

    @Test
    public void testGuess() {

        int result = guessNumber(2126753390);
        log.debug("result:{}", result);
    }

    public int guessNumber(int n) {

        int min = 1;
        int num = n;
        int max = n;
        while (true) {
            int result = guess(num);
            if (result == 0) {
                return num;
            } else if (result == -1) {
                max = num;
                num = min + (num - min) / 2;

            } else {
                min = num;
                num = num + (max - num) / 2;
            }
        }
    }

    private int guess(int num) {

        int target = 1702766719;
        if (num == target) {
            return 0;
        } else if (num > target) {
            return -1;
        } else {
            return 1;
        }
    }

    /**
     * 搜索旋转排序数组 假设按照升序排序的数组在预先未知的某个点上进行了旋转。
     *
     * <p>( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。
     *
     * <p>搜索一个给定的目标值，如果数组中存在这个目标值，则返回它的索引，否则返回 -1 。
     *
     * <p>你可以假设数组中不存在重复的元素。
     *
     * <p>你的算法时间复杂度必须是 O(log n) 级别。
     *
     * <p>示例 1:
     *
     * <p>输入: nums = [4,5,6,7,0,1,2], target = 0 输出: 4 示例 2:
     *
     * <p>输入: nums = [4,5,6,7,0,1,2], target = 3 输出: -1
     */
    @Test
    public void searchTest() {
        int[] nums = {4, 5, 6, 7, 0, 1, 2};
        int target = 3;
        int result = searchA(nums, target);
        log.debug("result:{}", result);
    }

    public int searchA(int[] nums, int target) {

        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            // 如果中间值比右边小,顺序排列的
            if (nums[mid] < nums[right]) {
                if (nums[mid] < target && nums[right] >= target) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            } else {
                if (nums[left] <= target && nums[mid] > target) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
        }
        return -1;
    }

    /**
     * 寻找旋转排序数组中的最小值 假设按照升序排序的数组在预先未知的某个点上进行了旋转。
     *
     * <p>( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。
     *
     * <p>请找出其中最小的元素。
     *
     * <p>你可以假设数组中不存在重复元素。
     *
     * <p>示例 1:
     *
     * <p>输入: [3,4,5,1,2] 输出: 1 示例 2:
     *
     * <p>输入: [4,5,6,7,0,1,2] 输出: 0
     */
    @Test
    public void testFindMin() {
        int[] nums = {3, 1, 3, 3};
        int result = findMin(nums);
        log.debug("result:{}", result);
    }

    /**
     * 寻找旋转排序数组中的最小值 假设按照升序排序的数组在预先未知的某个点上进行了旋转。
     *
     * <p>( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。
     *
     * <p>请找出其中最小的元素。
     *
     * <p>你可以假设数组中不存在重复元素。
     *
     * <p>示例 1:
     *
     * <p>输入: [3,4,5,1,2] 输出: 1 示例 2:
     *
     * <p>输入: [4,5,6,7,0,1,2] 输出: 0
     *
     * @param nums
     * @return
     */
    public int findMin(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        if (nums[left] < nums[right]) {
            return nums[left];
        }
        while (left < right) {
            if (nums[left] == nums[right]) {
                left++;
                continue;
            }
            int mid = left + (right - left) / 2;
            // 如果中间值比右边小,顺序排列的
            if (nums[mid] > nums[right]) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return nums[left];
    }

    @Test
    public void firstBadVersionTest() {
        int n = 4;
        int result = firstBadVersion(n);
        log.debug("result:{}", result);
    }

    /**
     * 第一个错误的版本
     * 你是产品经理，目前正在带领一个团队开发新的产品。不幸的是，你的产品的最新版本没有通过质量检测。由于每个版本都是基于之前的版本开发的，所以错误的版本之后的所有版本都是错的。
     *
     * <p>假设你有 n 个版本 [1, 2, ..., n]，你想找出导致之后所有版本出错的第一个错误的版本。
     *
     * <p>你可以通过调用 bool isBadVersion(version) 接口来判断版本号 version
     * 是否在单元测试中出错。实现一个函数来查找第一个错误的版本。你应该尽量减少对调用 API 的次数。
     *
     * <p>示例:
     *
     * <p>给定 n = 5，并且 version = 4 是第一个错误的版本。
     *
     * <p>调用 isBadVersion(3) -> false 调用 isBadVersion(5) -> true 调用 isBadVersion(4) -> true
     *
     * <p>所以，4 是第一个错误的版本。
     *
     * @param n
     * @return
     */
    public int firstBadVersion(int n) {

        int min = 1;
        int max = n;
        while (min < max) {
            int mid = min + (max - min) / 2;
            // mid版本出错 mid版本未出错
            if (isBadVersion(mid) && !isBadVersion(mid - 1)) {
                return mid;
            }
            if (isBadVersion(mid)) {
                max = mid;
            } else {
                min = mid + 1;
            }
        }

        return min;
    }

    private boolean isBadVersion(int n) {
        int target = 4;
        if (n >= target) {
            return true;
        }
        return false;
    }

    @Test
    public void findPeakElement() {
        int[] nums = {1, 2, 1, 3, 5, 3, 4};
        int result = findPeakElement(nums);
        log.debug("result:{}", result);
    }

    /**
     * 寻找峰值 峰值元素是指其值大于左右相邻值的元素。
     *
     * <p>给定一个输入数组 nums，其中 nums[i] ≠ nums[i+1]，找到峰值元素并返回其索引。
     *
     * <p>数组可能包含多个峰值，在这种情况下，返回任何一个峰值所在位置即可。
     *
     * <p>你可以假设 nums[-1] = nums[n] = -∞。
     *
     * <p>示例 1:
     *
     * <p>输入: nums = [1,2,3,1] 输出: 2 解释: 3 是峰值元素，你的函数应该返回其索引 2。 示例 2:
     *
     * <p>输入: nums = [1,2,1,3,5,6,4] 输出: 1 或 5 解释: 你的函数可以返回索引 1，其峰值元素为 2； 或者返回索引 5， 其峰值元素为 6。 说明:
     *
     * <p>你的解法应该是 O(logN) 时间复杂度的。
     *
     * @param nums
     * @return
     */
    public int findPeakElement(int[] nums) {
        if (nums.length == 1) {
            return 0;
        }
        int left = 0;
        int right = nums.length - 1;

        // 规律一：如果nums[i] > nums[i+1]，则在i之前一定存在峰值元素

        // 规律二：如果nums[i] < nums[i+1]，则在i+1之后一定存在峰值元素

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] > nums[mid + 1]) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }

    @Test
    public void searchRangeTest() {

        int[] nums = {5, 7, 7, 8, 8, 8, 10};
        int target = 10;
        int[] result = searchRange(nums, target);
        log.debug("result:{}", result);
    }

    /**
     * 在排序数组中查找元素的第一个和最后一个位置 给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。
     *
     * <p>你的算法时间复杂度必须是 O(log n) 级别。
     *
     * <p>如果数组中不存在目标值，返回 [-1, -1]。
     *
     * <p>示例 1:
     *
     * <p>输入: nums = [5,7,7,8,8,10], target = 8 输出: [3,4] 示例 2:
     *
     * <p>输入: nums = [5,7,7,8,8,10], target = 6 输出: [-1,-1]
     *
     * @param nums
     * @param target
     * @return
     */
    public int[] searchRange(int[] nums, int target) {
        int[] result = {-1, -1};
        if (Objects.isNull(nums) || nums.length == 0) {

            return result;
        }
        int left = 0;
        int right = nums.length - 1;

        int resultMin = -1;
        int resultMax = -1;

        //
        while (left <= right) {
            if (nums[left] == target && nums[right] == target) {
                resultMin = left;
                resultMax = right;
                break;
            }
            int mid = left + (right - left) / 2;

            if (nums[mid] == target) {
                if (nums[left] < target) {
                    left++;
                }
                if (nums[right] > target) {
                    right--;
                }
            } else if (nums[mid] > target) {
                // mid > target
                right = mid - 1;
            } else {
                // mid < target
                left = mid + 1;
            }
        }
        result[0] = resultMin;
        result[1] = resultMax;

        return result;
    }

    @Test
    public void findClosestElementsTest() {
        int[] nums = {1, 2, 3, 4, 5, 7, 10};
        int k = 4;
        int x = 5;
        List<Integer> result = findClosestElements(nums, k, x);
        log.debug("result:{}", result);
    }

    /**
     * 找到 K 个最接近的元素 给定一个排序好的数组，两个整数 k 和 x，从数组中找到最靠近 x（两数之差最小）的 k 个数。 返回的结果必须要是按升序排好的。如果有两个数与 x
     * 的差值一样，优先选择数值较小的那个数。
     *
     * <p>示例 1:
     *
     * <p>输入: [1,2,3,4,5], k=4, x=3 输出: [1,2,3,4]
     *
     * <p>示例 2:
     *
     * <p>输入: [1,2,3,4,5], k=4, x=-1 输出: [1,2,3,4]
     *
     * <p>说明:
     *
     * <p>k 的值为正数，且总是小于给定排序数组的长度。 数组不为空，且长度不超过 10000 数组里的每个元素与 x 的绝对值不超过 10000
     *
     * @param arr
     * @param k
     * @param x
     * @return
     */
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        List<Integer> list = new ArrayList<>();
        if (Objects.isNull(arr) || arr.length == 0) {
            return list;
        }

        int left = 0;
        int right = arr.length - k;

        while (left < right) {
            int mid = left + (right - left) / 2;

            // arr[mid] x arr[mid+k]
            // 要保证 x 在mid 和mid + k 中间 x 距离左侧比距离右侧更近
            if (x - arr[mid] > arr[mid + k] - x) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        for (int i = 0; i < k; i++) {
            list.add(arr[left + i]);
        }

        return list;
    }

    @Test
    public void myPowTest() {
        double x = 2.0;
        int n = 10;
        double result = myPow(x, n);
        log.debug("result:{}", result);
    }

    /**
     * 实现 pow(x, n) ，即计算 x 的 n 次幂函数。
     *
     * <p>示例 1:
     *
     * <p>输入: 2.00000, 10 输出: 1024.00000 示例 2:
     *
     * <p>输入: 2.10000, 3 输出: 9.26100 示例 3:
     *
     * <p>输入: 2.00000, -2 输出: 0.25000 解释: 2-2 = 1/22 = 1/4 = 0.25
     *
     * @param x
     * @param n
     * @return
     */
    public double myPow(double x, int n) {
        if (n == 0 && x != 0) {
            return 1;
        }
        // 判断n 是否为负数
        boolean isPositive = n > 0;
        double result = 1.0;

        if (!isPositive) {
            n = -n;
        }
        for (int i = n; i != 0; i >>= 1) {

            if ((i & 1) == 1) {
                result *= x;
            }
            x *= x;
        }

        return isPositive ? result : 1 / result;
    }

    @Test
    public void isPerfectSquareTest() {
        int num = 10000;
        boolean result = isPerfectSquare(num);
        log.debug("result:{}", result);
    }

    /**
     * 有效的完全平方数 给定一个正整数 num，编写一个函数，如果 num 是一个完全平方数，则返回 True，否则返回 False。
     *
     * <p>说明：不要使用任何内置的库函数，如 sqrt。
     *
     * <p>示例 1：
     *
     * <p>输入：16 输出：True 示例 2：
     *
     * <p>输入：14 输出：False
     *
     * @param num
     * @return
     */
    public boolean isPerfectSquare(int num) {
        if (num <= 1) {
            return true;
        }

        int a = num / 2;
        Long n = Long.valueOf(a);
        Long max = n;
        while (n * n > num || (n + 1) * (n + 1) <= num) {
            if (n * n > num) {
                max = n;
                n = n / 2;

            } else {
                n = (n + max) / 2;
            }
        }
        if (n * n == num) {
            return true;
        }

        return false;
    }

    @Test
    public void nextGreatestLetterTest() {

        char[] letters = {'c', 'f', 'j'};
        char target = 'g';
        // char[] letters = {'e', 'e', 'e','n', 'n', 'n'};
        // char target = 'e';

        char result = nextGreatestLetter(letters, target);
        log.debug("result:{}", result);
    }

    /**
     * 寻找比目标字母大的最小字母 给定一个只包含小写字母的有序数组letters 和一个目标字母 target，寻找有序数组里面比目标字母大的最小字母。
     *
     * <p>数组里字母的顺序是循环的。举个例子，如果目标字母target = 'z' 并且有序数组为 letters = ['a', 'b']，则答案返回 'a'。
     *
     * <p>示例:
     *
     * <p>输入: letters = ["c", "f", "j"] target = "a" 输出: "c"
     *
     * <p>输入: letters = ["c", "f", "j"] target = "c" 输出: "f"
     *
     * <p>输入: letters = ["c", "f", "j"] target = "d" 输出: "f"
     *
     * <p>输入: letters = ["c", "f", "j"] target = "g" 输出: "j"
     *
     * <p>输入: letters = ["c", "f", "j"] target = "j" 输出: "c"
     *
     * <p>输入: letters = ["c", "f", "j"] target = "k" 输出: "c"
     *
     * @param letters
     * @param target
     * @return
     */
    public char nextGreatestLetter(char[] letters, char target) {

        int left = 0;
        int right = letters.length - 1;
        if (target >= letters[left] && target < letters[right]) {

            while (left <= right) {

                if (letters[right] > target && letters[right - 1] <= target) {
                    break;
                }

                int mid = left + (right - left) / 2;
                if (letters[mid] > target) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }

        } else {
            return letters[left];
        }

        return letters[right];
    }

    @Test
    public void intersectionTest() {
        int[] nums1 = {4, 9, 5};
        int[] nums2 = {9, 4, 9, 8, 4};

        int[] result = intersection(nums1, nums2);
        log.debug("result:{}", result);
    }

    /**
     * 两个数组的交集 给定两个数组，编写一个函数来计算它们的交集。
     *
     * <p>示例 1:
     *
     * <p>输入: nums1 = [1,2,2,1], nums2 = [2,2] 输出: [2] 示例 2:
     *
     * <p>输入: nums1 = [4,9,5], nums2 = [9,4,9,8,4] 输出: [9,4] 说明:
     *
     * <p>输出结果中的每个元素一定是唯一的。 我们可以不考虑输出结果的顺序。
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public int[] intersection(int[] nums1, int[] nums2) {
        int size = (nums1.length / 3) * 4 + 1;
        HashMap<Integer, Integer> map = new HashMap<>(size);
        for (int i = 0; i < nums1.length; i++) {
            map.put(nums1[i], 1);
        }
        Set<Integer> list = new HashSet<>();
        for (int num : nums2) {
            if (map.containsKey(num)) {
                list.add(num);
            }
        }
        int len = list.size();
        int[] result = new int[len];
        Iterator<Integer> iterator = list.iterator();
        int index = 0;
        while (iterator.hasNext()) {
            result[index] = iterator.next();
            index++;
        }

        return result;
    }

    @Test
    public void intersectTest() {
        int[] nums1 = {1, 2, 2, 1};
        int[] nums2 = {2, 2};

        int[] result = intersect(nums1, nums2);
        log.debug("result:{}", result);
    }
    /**
     * 两个数组的交集 II 给定两个数组，编写一个函数来计算它们的交集。
     *
     * <p>示例 1:
     *
     * <p>输入: nums1 = [1,2,2,1], nums2 = [2,2] 输出: [2,2] 示例 2:
     *
     * <p>输入: nums1 = [4,9,5], nums2 = [9,4,9,8,4] 输出: [4,9] 说明：
     *
     * <p>输出结果中每个元素出现的次数，应与元素在两个数组中出现的次数一致。 我们可以不考虑输出结果的顺序。 进阶:
     *
     * <p>如果给定的数组已经排好序呢？你将如何优化你的算法？ 如果 nums1 的大小比 nums2 小很多，哪种方法更优？ 如果 nums2
     * 的元素存储在磁盘上，磁盘内存是有限的，并且你不能一次加载所有的元素到内存中，你该怎么办？
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public int[] intersect(int[] nums1, int[] nums2) {

        int size = (nums1.length / 3) * 4 + 1;
        HashMap<Integer, Integer> map = new HashMap<>(size);
        for (int num : nums1) {
            if (map.containsKey(num)) {
                int value = map.get(num) + 1;
                map.put(num, value);
            } else {
                map.put(num, 1);
            }
        }
        List<Integer> list = new ArrayList<>();
        for (int num : nums2) {
            if (map.containsKey(num) && map.get(num) > 0) {
                list.add(num);
                int value = map.get(num) - 1;
                map.put(num, value);
            }
        }
        int len = list.size();
        int[] result = new int[len];
        for (int i = 0; i < len; i++) {
            result[i] = list.get(i);
        }

        return result;
    }

    /** */
    @Test
    public void findDuplicateTest() {
        int[] nums = {1, 3, 2, 4, 2};
        int result = findDuplicate(nums);
        log.debug("result:{}", result);
    }

    /**
     * 寻找重复数 给定一个包含 n + 1 个整数的数组 nums，其数字都在 1 到 n 之间（包括 1 和 n），可知至少存在一个重复的整数。 假设只有一个重复的整数，找出这个重复的数。
     *
     * <p>示例 1:
     *
     * <p>输入: [1,3,4,2,2] 输出: 2 示例 2:
     *
     * <p>输入: [3,1,3,4,2] 输出: 3 说明：
     *
     * <p>不能更改原数组（假设数组是只读的）。 只能使用额外的 O(1) 的空间。 时间复杂度小于 O(n2) 。 数组中只有一个重复的数字，但它可能不止重复出现一次。
     *
     * @param nums
     * @return
     */
    public int findDuplicate(int[] nums) {

        // 【笔记】这道题（据说）花费了计算机科学界的传奇人物Don Knuth 24小时才解出来。并且我只见过一个人（注：Keith Amling）用更短时间解出此题。

        // 快慢指针，一个时间复杂度为O(N)的算法。

        // 其一，对于链表问题，使用快慢指针可以判断是否有环。

        // 其二，本题可以使用数组配合下标，抽象成链表问题。但是难点是要定位环的入口位置。

        // 举个例子：nums = [2,5, 9 ,6,9,3,8, 9 ,7,1]，构造成链表就是：2->[9]->1->5->3->6->8->7->[9]，也就是在[9]处循环。

        // 其三，快慢指针问题，会在环内的[9]->1->5->3->6->8->7->[9]任何一个节点追上，不一定是在[9]处相碰，事实上会在7处碰上。

        // 其四，必须另起一个for循环定位环入口位置[9]。这里需要数学证明。

        int result = -1;

        // 快慢指针思想，把数组看成链表 数组的下标，数组的值代表next的指针
        // 题目中  n + 1 个数 ，数字都在 1 到 n 之间 就是为了这个
        // 举个例子：nums = [2,5, 9 ,6,9,3,8, 9 ,7,1]，构造成链表就是：2->[9]->1->5->3->6->8->7->[9]，
        // 也就是在[9]处循环。
        int fast = 0, slow = 0;

        int size = nums.length;

        while (true) {
            fast = nums[nums[fast]]; // fast -> nums[fast] -> nums[nums[fast]]
            slow = nums[slow]; // slow -> nums[slow]
            if (slow == fast) { // fast到slow 相撞，只能证明有重复

                // 找相遇的位置
                fast = 0;
                while (nums[slow] != nums[fast]) {
                    fast = nums[fast];
                    slow = nums[slow];
                }
                return nums[slow];
            }
        }

        // return result;

        // 笨办法
        /*int result = -1;

        int left = 0;
        int right = nums.length - 1;
        int cursor = left;
        while (left < right) {
            if (nums[left] == nums[right]) {
                result = nums[left];
                break;
            } else {
                left++;
                if (left >= right) {
                    right--;
                    left = cursor;
                }
                if (right <= cursor) {
                    left = ++cursor;
                }
            }

        }

        return result;*/
    }

    /** */
    @Test
    public void findMedianSortedArrays() {
        int[] nums1 = {1};
        int[] nums2 = {3, 4};
        double result = findMedianSortedArrays(nums1, nums2);
        log.debug("result:{}", result);
    }

    /**
     * 寻找两个有序数组的中位数 给定两个大小为 m 和 n 的有序数组 nums1 和 nums2。
     *
     * <p>请你找出这两个有序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。
     *
     * <p>你可以假设 nums1 和 nums2 不会同时为空。
     *
     * <p>示例 1:
     *
     * <p>nums1 = [1, 3] nums2 = [2]
     *
     * <p>则中位数是 2.0 示例 2:
     *
     * <p>nums1 = [1, 2] nums2 = [3, 4]
     *
     * <p>则中位数是 (2 + 3)/2 = 2.5
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {

        int m = nums1.length;
        int n = nums2.length;

        int midIndex = (m + n) >> 1;

        /*if((index&1)==1){
            System.out.println("奇数！");
        } else {
            System.out.println("偶数！");
        }*/

        boolean isEven = true;
        if (((m + n) & 1) == 1) { // 奇数
            isEven = false;
        }

        double midNum = 0.0;

        // 合并有序数组
        int index = 0;
        int i = 0, j = 0;
        while (index <= midIndex) {
            double num = 0.0;

            if (i == m) {
                num = nums2[j];
                j++;
            } else if (j == n) {
                num = nums1[i];
                i++;
            } else if (nums1[i] < nums2[j]) {
                num = nums1[i];
                i++;
            } else {
                num = nums2[j];
                j++;
            }
            if (isEven && index == midIndex - 1) { // 如果是偶数 且index = midIndex - 1
                midNum += num;
            }
            if (index == midIndex) {
                midNum += num;
            }
            index++;
        }
        if (isEven) {
            return midNum / 2.0;
        }

        return midNum;
    }

    @Test
    public void smallestDistancePair() {
        int[] nums = {1, 3, 1, 4};
        int k = 2;
        int result = smallestDistancePair(nums, k);
        log.debug("result:{}", result);
    }

    /**
     * 找出第 k 小的距离对 给定一个整数数组，返回所有数对之间的第 k 个最小距离。一对 (A, B) 的距离被定义为 A 和 B 之间的绝对差值。
     *
     * <p>示例 1:
     *
     * <p>输入： nums = [1,3,1] k = 1 输出：0 解释： 所有数对如下： (1,3) -> 2 (1,1) -> 0 (3,1) -> 2 因此第 1 个最小距离的数对是
     * (1,1)，它们之间的距离为 0。 提示:
     *
     * <p>2 <= len(nums) <= 10000. 0 <= nums[i] < 1000000. 1 <= k <= len(nums) * (len(nums) - 1) /
     * 2.
     *
     * @param nums
     * @param k
     * @return
     */
    public int smallestDistancePair(int[] nums, int k) {
        // 思路分析： 与 LeetCode 乘法表中第k小的数（二分搜索） 非常类似。
        // 最大的距离必定是最大值 - 最小值，而最小的距离可能是0（也可能比0大）。所以我们采用二分法，
        // 初始化left = 0， right = 最大距离。对于mid = (left + right) / 2，我们计算距离小于等于k个距离对的个数shortDisMid。
        //
        // 如果shortDisMid < k， 则第k小的距离对必定不会出现在[left, mid]，所以修改left = mid + 1
        // 否则 right = mid
        int len = nums.length;

        Arrays.sort(nums);
        int maxDistance = nums[len - 1] - nums[0];

        int left = 0, right = maxDistance;
        int mid;
        while (left < right) {
            mid = left + (right - left) / 2;
            // 获取距离大小不超过k的距离对的个数
            int disMid = countDistance(nums, mid);
            // 缩小[left, right]区间
            if (disMid < k) {
                // <= mid的距离对个数小于k，则第k小的距离必定不会出现在[left, mid]
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return left;
    }

    /**
     * 查找数组nums中距离小于distance的对数
     *
     * @param nums
     * @param distance
     * @return
     */
    private int countDistance(int[] nums, int distance) {
        int size = nums.length;
        int result = 0;

        int right = size - 1;
        for (int i = size - 2; i >= 0; i--) {
            while (i < right && nums[right] - nums[i] > distance) {
                right--;
            }

            result += (right - i);
        }

        return result;
    }

    @Test
    public void largestNumber() {
        int[] nums = {3, 30, 34, 5, 9};
        String result = largestNumber(nums);
        log.debug("result:{}", result);
    }
    /**
     * 最大数 给定一组非负整数，重新排列它们的顺序使之组成一个最大的整数。
     *
     * <p>示例 1:
     *
     * <p>输入: [10,2] 输出: 210 示例 2:
     *
     * <p>输入: [3,30,34,5,9] 输出: 9534330 说明: 输出结果可能非常大，所以你需要返回一个字符串而不是整数。
     *
     * @param nums
     * @return
     */
    public String largestNumber(int[] nums) {
        StringBuilder result = new StringBuilder();
        int len = nums.length;
        String[] arrays = new String[len];
        for (int i = 0; i < len; i++) {
            arrays[i] = String.valueOf(nums[i]);
        }
        Arrays.sort(
                arrays,
                (a, b) -> {
                    String c = a + b;
                    String d = b + a;
                    return d.compareTo(c);
                });
        if (arrays[0].equals("0")) {
            return "0";
        }
        for (String s : arrays) {
            result.append(s);
        }

        return result.toString();
    }

    @Test
    public void wiggleSort() {
        int[] nums = {1, 2, 2, 3};
        wiggleSort(nums);
        log.debug("nums:{}", nums);
    }

    /**
     * 摆动排序 II 给定一个无序的数组 nums，将它重新排列成 nums[0] < nums[1] > nums[2] < nums[3]... 的顺序。
     *
     * <p>示例 1:
     *
     * <p>输入: nums = [1, 5, 1, 1, 6, 4] 输出: 一个可能的答案是 [1, 4, 1, 5, 1, 6] 示例 2:
     *
     * <p>输入: nums = [1, 3, 2, 2, 3, 1] 输出: 一个可能的答案是 [2, 3, 1, 3, 1, 2] 说明: 你可以假设所有输入都会得到有效的结果。
     *
     * <p>进阶: 你能用 O(n) 时间复杂度和 / 或原地 O(1) 额外空间来实现吗？
     *
     * @param nums
     */
    public void wiggleSort(int[] nums) {
        if (nums.length < 2) {
            return;
        }
        Arrays.sort(nums);
        int[] ans = nums.clone();
        int i = 0, right = nums.length - 1, left = right / 2;
        nums[i++] = ans[left--];
        while (true) {
            if (i < nums.length) {
                nums[i++] = ans[right--];
            } else break;
            if (i < nums.length) {
                nums[i++] = ans[left--];
            } else break;
        }
    }

    /**
     * 1237. 找出给定方程的正整数解 给出一个函数 f(x, y) 和一个目标结果 z，请你计算方程 f(x,y) == z 所有可能的正整数 数对 x 和 y。
     *
     * <p>给定函数是严格单调的，也就是说：
     *
     * <p>f(x, y) < f(x + 1, y) f(x, y) < f(x, y + 1) 函数接口定义如下：
     *
     * <p>interface CustomFunction { public: // Returns positive integer f(x, y) for any given
     * positive integer x and y. int f(int x, int y); }; 如果你想自定义测试，你可以输入整数 function_id 和一个目标结果 z
     * 作为输入， 其中 function_id 表示一个隐藏函数列表中的一个函数编号，题目只会告诉你列表中的 2 个函数。
     *
     * <p>你可以将满足条件的 结果数对 按任意顺序返回。
     *
     * <p>示例 1：
     *
     * <p>输入：function_id = 1, z = 5 输出：[[1,4],[2,3],[3,2],[4,1]] 解释：function_id = 1 表示 f(x, y) = x +
     * y 示例 2：
     *
     * <p>输入：function_id = 2, z = 5 输出：[[1,5],[5,1]] 解释：function_id = 2 表示 f(x, y) = x * y
     *
     * <p>提示：
     *
     * <p>1 <= function_id <= 9 1 <= z <= 100 题目保证 f(x, y) == z 的解处于 1 <= x, y <= 1000 的范围内。 在 1 <=
     * x, y <= 1000 的前提下，题目保证 f(x, y) 是一个 32 位有符号整数。
     *
     * @param customfunction
     * @param z
     * @return
     */
    public List<List<Integer>> findSolution(CustomFunction customfunction, int z) {
        List<List<Integer>> result = new ArrayList<>();
        if (customfunction.f(z, z) < z || customfunction.f(1, 1) > z) {
            return result;
        }
        int x = 1, y = z;

        while (x <= z && y >= 1) {
            int num = customfunction.f(x, y);
            if (num == z) {
                List<Integer> list = new ArrayList<>();
                list.add(x++);
                list.add(y--);
                result.add(list);
            }
            if (num < z) {
                x++;
            }
            if (num > z) {
                y--;
            }
        }

        return result;
    }

    interface CustomFunction {
        int f(int x, int y);
    }

    /**
     * 面试题04. 二维数组中的查找 在一个 n * m
     * 的二维数组中，每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
     *
     * <p>示例:
     *
     * <p>现有矩阵 matrix 如下：
     *
     * <p>[ [1, 4, 7, 11, 15], [2, 5, 8, 12, 19], [3, 6, 9, 16, 22], [10, 13, 14, 17, 24], [18, 21,
     * 23, 26, 30] ] 给定 target = 5，返回 true。
     *
     * <p>给定 target = 20，返回 false。
     *
     * <p>限制：
     *
     * <p>0 <= n <= 1000
     *
     * <p>0 <= m <= 1000
     *
     * @param matrix
     * @param target
     * @return
     */
    public boolean findNumberIn2DArray(int[][] matrix, int target) {
        int rows = matrix.length;
        if (rows == 0) {
            return false;
        }
        int cols = matrix[0].length;
        if (cols == 0) {
            return false;
        }

        int row = 0, col = cols - 1;

        while (row < rows && col >= 0) {
            int num = matrix[row][col];

            if (num == target) {
                return true;
            }

            if (num > target) {
                col--;
            } else {
                row++;
            }
        }

        return false;
    }

    @Test
    public void minArray() {
        int[] numbers = {2, 2, 2, 0, 1};
        logResult(minArray(numbers));
    }
    /**
     * 面试题11. 旋转数组的最小数字 把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。输入一个递增排序的数组的一个旋转，输出旋转数组的最小元素。例如，数组
     * [3,4,5,1,2] 为 [1,2,3,4,5] 的一个旋转，该数组的最小值为1。
     *
     * <p>示例 1：
     *
     * <p>输入：[3,4,5,1,2] 输出：1 示例 2：
     *
     * <p>输入：[2,2,2,0,1] 输出：0
     *
     * @param numbers
     * @return
     */
    public int minArray(int[] numbers) {
        int len = numbers.length;
        int low = 0, high = len - 1;

        while (low < high) {
            int mid = (low + high) >> 1;
            if (numbers[mid] > numbers[high]) {
                low = mid + 1;
            } else if (numbers[mid] < numbers[high]) {
                high = mid;
            } else {
                high--;
            }
        }
        return numbers[low];
    }

    /**
     * 面试题53 - I. 在排序数组中查找数字 I 统计一个数字在排序数组中出现的次数。
     *
     * <p>示例 1:
     *
     * <p>输入: nums = [5,7,7,8,8,10], target = 8 输出: 2 示例 2:
     *
     * <p>输入: nums = [5,7,7,8,8,10], target = 6 输出: 0
     *
     * <p>限制：
     *
     * <p>0 <= 数组长度 <= 50000
     *
     * <p>注意：本题与主站 34 题相同（仅返回值不同）：
     * https://leetcode-cn.com/problems/find-first-and-last-position-of-element-in-sorted-array/
     *
     * @param nums
     * @param target
     * @return
     */
    public int search2(int[] nums, int target) {
        int low = 0, high = nums.length - 1;
        int result = 0;
        while (low <= high) {
            if (nums[low] == target && nums[high] == target) {
                result = high - low + 1;
                break;
            }
            int mid = (low + high) >> 1;

            if (nums[mid] == target) {
                if (nums[low] < target) {
                    low++;
                }
                if (nums[high] > target) {
                    high--;
                }
            } else if (nums[mid] > target) {
                // mid > target
                high = mid - 1;
            } else {
                // mid < target
                low = mid + 1;
            }
        }
        return result;
    }

    /**
     * 33. 搜索旋转排序数组 假设按照升序排序的数组在预先未知的某个点上进行了旋转。
     *
     * <p>( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。
     *
     * <p>搜索一个给定的目标值，如果数组中存在这个目标值，则返回它的索引，否则返回 -1 。
     *
     * <p>你可以假设数组中不存在重复的元素。
     *
     * <p>你的算法时间复杂度必须是 O(log n) 级别。
     *
     * <p>示例 1:
     *
     * <p>输入: nums = [4,5,6,7,0,1,2], target = 0 输出: 4 示例 2:
     *
     * <p>输入: nums = [4,5,6,7,0,1,2], target = 3 输出: -1
     *
     * @param nums
     * @param target
     * @return
     */
    public int search3(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            // 如果中间值比右边小,顺序排列的
            if (nums[mid] < nums[right]) {
                if (nums[mid] < target && nums[right] >= target) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            } else {
                if (nums[left] <= target && nums[mid] > target) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
        }
        return -1;
    }

    /**
     * 面试题 08.03. 魔术索引 魔术索引。 在数组A[0...n-1]中，有所谓的魔术索引，满足条件A[i] = i。给定一个有序整数数组，
     * 编写一种方法找出魔术索引，若有的话，在数组A中找出一个魔术索引，如果没有，则返回-1。若有多个魔术索引，返回索引值最小的一个。
     *
     * <p>示例1:
     *
     * <p>输入：nums = [0, 2, 3, 4, 5] 输出：0 说明: 0下标的元素为0 示例2:
     *
     * <p>输入：nums = [1, 1, 1] 输出：1 提示:
     *
     * <p>nums长度在[1, 1000000]之间
     *
     * @param nums
     * @return
     */
    public int findMagicIndex(int[] nums) {
        return searchMagicIndex(nums, 0, nums.length - 1);
    }

    public int searchMagicIndex(int[] nums, int left, int right) {
        if (right >= nums.length || left < 0 || left > right) {
            return -1;
        }
        final int middle = (left + right) >> 1;
        final int middleVal = nums[middle];

        int leftSearch = searchMagicIndex(nums, left, middle - 1);
        if (leftSearch >= 0) {
            return leftSearch;
        } else if (middle == middleVal) {
            return middle;
        } else {
            return searchMagicIndex(nums, middle + 1, right);
        }
    }

    @Test
    public void findString() {
        // String[] words = {"at", "", "", "", "ball", "", "", "car", "", "","dad", "", ""};
        // String s = "ball";
        String[] words = {
            "DirNnILhARNS hOYIFB",
            "SM ",
            "YSPBaovrZBS",
            "evMMBOf",
            "mCrS",
            "oRJfjw gwuo",
            "xOpSEXvfI"
        };
        String s = "mCrS";
        logResult(findString(words, s));
    }
    /**
     * 面试题 10.05. 稀疏数组搜索 稀疏数组搜索。有个排好序的字符串数组，其中散布着一些空字符串，编写一种方法，找出给定字符串的位置。
     *
     * <p>示例1:
     *
     * <p>输入: words = ["at", "", "", "", "ball", "", "", "car", "", "","dad", "", ""], s = "ta"
     * 输出：-1 说明: 不存在返回-1。 示例2:
     *
     * <p>输入：words = ["at", "", "", "", "ball", "", "", "car", "", "","dad", "", ""], s = "ball"
     * 输出：4 提示:
     *
     * <p>words的长度在[1, 1000000]之间
     *
     * @param words
     * @param s
     * @return
     */
    public int findString(String[] words, String s) {
        int len = words.length;
        int left = 0, right = len - 1;
        while (left < right) {
            int mid = (left + right) >> 1;

            int tmp = mid;
            while (mid < right && words[mid].length() == 0) {
                mid++;
            }
            if (words[mid].length() == 0) {
                right = tmp - 1;
                continue;
            }

            log.debug("mid:{}", mid);
            if (Objects.equals(words[mid], s)) {
                return mid;
            }
            if (s.compareTo(words[mid]) < 0) {
                right = tmp - 1;
            } else {
                left = mid + 1;
            }
        }
        if (Objects.equals(words[left], s)) {
            return left;
        }
        return -1;
    }

    @Test
    public void findRadius() {
        int[] houses = {1};
        int[] heaters = {1, 2};
        logResult(findRadius(houses, heaters));
    }
    /**
     * 475. 供暖器 冬季已经来临。 你的任务是设计一个有固定加热半径的供暖器向所有房屋供暖。
     *
     * <p>现在，给出位于一条水平线上的房屋和供暖器的位置，找到可以覆盖所有房屋的最小加热半径。
     *
     * <p>所以，你的输入将会是房屋和供暖器的位置。你将输出供暖器的最小加热半径。
     *
     * <p>说明:
     *
     * <p>给出的房屋和供暖器的数目是非负数且不会超过 25000。 给出的房屋和供暖器的位置均是非负数且不会超过10^9。 只要房屋位于供暖器的半径内(包括在边缘上)，它就可以得到供暖。
     * 所有供暖器都遵循你的半径标准，加热的半径也一样。 示例 1:
     *
     * <p>输入: [1,2,3],[2] 输出: 1 解释: 仅在位置2上有一个供暖器。如果我们将加热半径设为1，那么所有房屋就都能得到供暖。 示例 2:
     *
     * <p>输入: [1,2,3,4],[1,4] 输出: 1 解释: 在位置1, 4上有两个供暖器。我们需要将加热半径设为1，这样所有房屋就都能得到供暖。
     *
     * @param houses
     * @param heaters
     * @return
     */
    public int findRadius(int[] houses, int[] heaters) {
        // 先进行升序排列
        Arrays.sort(houses);
        Arrays.sort(heaters);
        int radius = 0;
        int i = 0;
        for (int house : houses) {
            while (i < heaters.length && heaters[i] < house) {
                // 一直找到处于房屋右侧的热水器
                i++;
            }
            if (i == 0) radius = Math.max(radius, heaters[i] - house);
            else if (i == heaters.length)
                // 最后一个热水器
                return Math.max(radius, houses[houses.length - 1] - heaters[heaters.length - 1]);
            else
                // 房屋右侧的热水器和房屋左侧的热水器，取小的那个
                radius = Math.max(radius, Math.min(heaters[i] - house, house - heaters[i - 1]));
        }
        return radius;
        /* int result = 0;
        int len = heaters.length;
        // 找到每个房屋离加热器的最短距离（即找出离房屋最近的加热器），然后在所有距离中选出最大的一个即为结果
        for (int house : houses) {
            int index = getIndex(heaters,house);
            int distance = 0;
            if (heaters[index] == house) {
                distance = 0;
            } else if (heaters[index] > house) {
                distance = heaters[index] - house;
                if (index > 0 && house - heaters[index - 1] < distance) {
                    distance = house - heaters[index - 1];
                }
            } else {
                distance = house - heaters[index];
                if (index < len - 1 && heaters[index + 1] - house < distance) {
                    distance = heaters[index + 1] - house;
                }
            }
            log.debug("index:{} ",index);
            log.debug("house:{},distance:{}",house,distance);
            if (distance > result) {
                result = distance;
            }
        }


        return result;*/
    }

    /**
     * 二分查找，在heaters中寻找与房屋 house 最近的加热器
     *
     * @param heaters
     * @param house
     * @return
     */
    private int getIndex(int[] heaters, int house) {
        int low = 0, hight = heaters.length - 1;
        while (low < hight) {
            int mid = (low + hight) >> 1;
            if (heaters[mid] == house) {
                return mid;
            }
            if (heaters[mid] < house) {
                low = mid + 1;
            } else {
                hight = mid;
            }
        }
        return low;
    }

    /**
     * 852. 山脉数组的峰顶索引 我们把符合下列属性的数组 A 称作山脉：
     *
     * <p>A.length >= 3 存在 0 < i < A.length - 1 使得A[0] < A[1] < ... A[i-1] < A[i] > A[i+1] > ... >
     * A[A.length - 1] 给定一个确定为山脉的数组，返回任何满足 A[0] < A[1] < ... A[i-1] < A[i] > A[i+1] > ... >
     * A[A.length - 1] 的 i 的值。
     *
     * <p>示例 1：
     *
     * <p>输入：[0,1,0] 输出：1 示例 2：
     *
     * <p>输入：[0,2,1,0] 输出：1
     *
     * <p>提示：
     *
     * <p>3 <= A.length <= 10000 0 <= A[i] <= 10^6 A 是如上定义的山脉
     *
     * @param A
     * @return
     */
    public int peakIndexInMountainArray(int[] A) {
        int len = A.length;
        int left = 0, right = len - 1;
        while (left < right) {
            int mid = (left + right) >> 1;

            if (mid > 0 && A[mid] > A[mid - 1] && A[mid] > A[mid + 1]) {
                return mid;
            }
            if (A[mid] < A[mid - 1]) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    @Test
    public void findInMountainArray() {
        int[] array = {1, 2, 3, 4, 5, 3, 1};
        int target = 3;
        MountainArray mountainArray = new MountainArray(array);
        logResult(findInMountainArray(target, mountainArray));
    }

    /**
     * 1095. 山脉数组中查找目标值 （这是一个 交互式问题 ）
     *
     * <p>给你一个 山脉数组 mountainArr，请你返回能够使得 mountainArr.get(index) 等于 target 最小 的下标 index 值。
     *
     * <p>如果不存在这样的下标 index，就请返回 -1。
     *
     * <p>何为山脉数组？如果数组 A 是一个山脉数组的话，那它满足如下条件：
     *
     * <p>首先，A.length >= 3
     *
     * <p>其次，在 0 < i < A.length - 1 条件下，存在 i 使得：
     *
     * <p>A[0] < A[1] < ... A[i-1] < A[i] A[i] > A[i+1] > ... > A[A.length - 1]
     *
     * <p>你将 不能直接访问该山脉数组，必须通过 MountainArray 接口来获取数据：
     *
     * <p>MountainArray.get(k) - 会返回数组中索引为k 的元素（下标从 0 开始） MountainArray.length() - 会返回该数组的长度
     *
     * <p>注意：
     *
     * <p>对 MountainArray.get 发起超过 100 次调用的提交将被视为错误答案。此外，任何试图规避判题系统的解决方案都将会导致比赛资格被取消。
     *
     * <p>为了帮助大家更好地理解交互式问题，我们准备了一个样例 “答案”：https://leetcode-cn.com/playground/RKhe3ave，请注意这 不是一个正确答案。
     *
     * <p>示例 1：
     *
     * <p>输入：array = [1,2,3,4,5,3,1], target = 3 输出：2 解释：3 在数组中出现了两次，下标分别为 2 和 5，我们返回最小的下标 2。 示例 2：
     *
     * <p>输入：array = [0,1,2,4,2,1], target = 3 输出：-1 解释：3 在数组中没有出现，返回 -1。
     *
     * <p>提示：
     *
     * <p>3 <= mountain_arr.length() <= 10000 0 <= target <= 10^9 0 <= mountain_arr.get(index) <=
     * 10^9
     *
     * @param target
     * @param mountainArr
     * @return
     */
    public int findInMountainArray(int target, MountainArray mountainArr) {
        int len = mountainArr.length();

        // 先找到峰顶索引 peakIdx
        int lo = 0, hi = mountainArr.length() - 1;
        while (lo + 1 < hi) {
            int mid = (lo + hi) >> 1;
            int midVal = mountainArr.get(mid);

            if (midVal > mountainArr.get(mid - 1)) {
                lo = mid;
            } else {
                hi = mid;
            }
        }
        int peakIdx = mountainArr.get(lo) > mountainArr.get(hi) ? lo : hi;

        // 根据峰顶将山脉数组分为「升序数组」和「降序数组」两段，分别进行二分查找
        int idx = findInMountainArray(target, mountainArr, 0, peakIdx, true);
        return idx != -1
                ? idx
                : findInMountainArray(
                        target, mountainArr, peakIdx + 1, mountainArr.length() - 1, false);
    }

    private int findInMountainArray(
            int target, MountainArray mountainArr, int left, int right, boolean flag) {
        while (left <= right) {
            int mid = (left + right) >> 1;
            int midVal = mountainArr.get(mid);

            if (midVal == target) {
                return mid;
            }
            if (midVal < target) {
                left = flag ? mid + 1 : left;
                right = flag ? right : mid - 1;
            } else {
                right = flag ? mid - 1 : right;
                left = flag ? left : mid + 1;
            }
        }
        return -1;
    }

    class MountainArray {
        int[] array;

        MountainArray(int[] array) {
            this.array = array;
        }

        int get(int index) {
            return array[index];
        }

        int length() {
            return array.length;
        }
    }

    /**
     * 69. x 的平方根 实现 int sqrt(int x) 函数。
     *
     * <p>计算并返回 x 的平方根，其中 x 是非负整数。
     *
     * <p>由于返回类型是整数，结果只保留整数的部分，小数部分将被舍去。
     *
     * <p>示例 1:
     *
     * <p>输入: 4 输出: 2 示例 2:
     *
     * <p>输入: 8 输出: 2 说明: 8 的平方根是 2.82842..., 由于返回类型是整数，小数部分将被舍去。
     *
     * @param x
     * @return
     */
    public int mySqrt2(int x) {
        if (x <= 3) {
            return 1;
        }
        int result = -1;
        int left = 0, right = x;

        while (left <= right) {
            int mid = (left + right) >> 1;
            if ((long) mid * mid <= x) {
                result = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return result;
    }

    @Test
    public void search4() {

        int[] nums = {3, 1};
        int target = 3;
        logResult(search4(nums, target));
    }

    /**
     * 81. 搜索旋转排序数组 II 假设按照升序排序的数组在预先未知的某个点上进行了旋转。
     *
     * <p>( 例如，数组 [0,0,1,2,2,5,6] 可能变为 [2,5,6,0,0,1,2] )。
     *
     * <p>编写一个函数来判断给定的目标值是否存在于数组中。若存在返回 true，否则返回 false。
     *
     * <p>示例 1:
     *
     * <p>输入: nums = [2,5,6,0,0,1,2], target = 0 输出: true 示例 2:
     *
     * <p>输入: nums = [2,5,6,0,0,1,2], target = 3 输出: false 进阶:
     *
     * <p>这是 搜索旋转排序数组 的延伸题目，本题中的 nums 可能包含重复元素。 这会影响到程序的时间复杂度吗？会有怎样的影响，为什么？
     *
     * @param nums
     * @param target
     * @return
     */
    public boolean search4(int[] nums, int target) {
        if (nums.length == 0) {
            return false;
        }
        if (nums.length == 1) {
            return nums[0] == target;
        }
        int left = 0;
        int right = nums.length - 1;

        // 思路 ： 二分法找到峰值, 分成两个数组 分别二分
        while (left < right) {
            int mid = (left + right) >> 1;

            if (nums[mid] < nums[left]) {
                right = mid - 1;
            } else if (nums[mid] > nums[right]) {
                left = mid + 1;
            } else {
                left++;
            }
        }

        int index = left;
        log.debug("index:{}", index);

        return search4(nums, target, 0, index) || search4(nums, target, index + 1, nums.length - 1);
    }

    private boolean search4(int[] nums, int target, int start, int end) {
        if (start == end) {
            if (nums[start] == target) {
                return true;
            }
            return false;
        }

        while (start < end) {
            int mid = (start + end) >> 1;
            if (nums[mid] == target) {
                return true;
            } else if (nums[mid] < target) {
                start = mid + 1;
            } else {
                end = mid;
            }
        }
        return false;
    }

    @Test
    public void search5() {
        int[] arr = {2, 1, 2, 2, 2};

        int target = 2;
        logResult(search5(arr, target));
    }
    /**
     * 面试题 10.03. 搜索旋转数组
     *
     * <p>搜索旋转数组。给定一个排序后的数组，包含n个整数，但这个数组已被旋转过很多次了，次数不详。请编写代码找出数组中的某个元素，假设数组元素原先是按升序排列的。若有多个相同元素，返回索引值最小的一个。
     *
     * <p>示例1:
     *
     * <p>输入: arr = [15, 16, 19, 20, 25, 1, 3, 4, 5, 7, 10, 14], target = 5 输出: 8（元素5在该数组中的索引） 示例2:
     *
     * <p>输入：arr = [15, 16, 19, 20, 25, 1, 3, 4, 5, 7, 10, 14], target = 11 输出：-1 （没有找到） 提示:
     *
     * <p>arr 长度范围在[1, 1000000]之间
     *
     * @param arr
     * @param target
     * @return
     */
    public int search5(int[] arr, int target) {
        if (arr.length == 0) {
            return -1;
        }
        if (arr.length == 1) {
            return arr[0] == target ? 0 : -1;
        }
        // 二分法
        int left = 0, right = arr.length - 1;
        // 先找到峰值, 然后二分搜索
        while (left < right) {
            if (arr[left] > arr[left + 1]) {
                break;
            }

            int mid = (left + right) >> 1;
            if (mid > 0
                    && mid < arr.length - 1
                    && arr[mid] > arr[mid - 1]
                    && arr[mid] > arr[mid + 1]) {
                left = mid;
                break;
            }
            if (mid < arr.length - 1 && arr[mid] > arr[mid + 1]) {
                left = mid;

                break;
            }

            if (arr[mid] < arr[left]) {
                right = mid - 1;
            } else if (arr[mid] > arr[right]) {
                left = mid + 1;
            } else {
                left++;
            }
        }
        int index = left;
        log.debug("index :{} v :{} ", index, arr[index]);
        if (index > 0) {
            log.debug("left :{}  ", arr[index - 1]);
        }
        if (index < arr.length - 1) {
            log.debug("right :{}  ", arr[index + 1]);
        }

        int result = search5(arr, target, 0, index);
        if (result > -1) {
            return result;
        }
        return search5(arr, target, index + 1, arr.length - 1);
    }

    private int search5(int[] arr, int target, int left, int right) {

        if (left > right) {
            return -1;
        }
        if (target == arr[left]) {
            return left;
        }
        if (target == arr[right]) {
            return right;
        }
        if (target < arr[left] || target > arr[right]) {
            return -1;
        }

        // 二分法
        while (left <= right) {
            int mid = (left + right) >> 1;
            if (arr[mid] == target) {
                while (mid > 0 && arr[mid - 1] == target) {
                    mid--;
                }
                return mid;
            }
            // 如果中间值比右边小,顺序排列的
            if (arr[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return -1;
    }

    @Test
    public void searchMatrix2() {
        int[][] matrix = {{1, 3, 5, 7}, {10, 11, 16, 20}, {23, 30, 34, 50}};
        int target = 12;
        // int[][] matrix = {{1}};
        // int target = 1;
        logResult(searchMatrix(matrix, target));
    }

    /**
     * 面试题 10.09. 排序矩阵查找
     *
     * <p>给定M×N矩阵，每一行、每一列都按升序排列，请编写代码找出某元素。
     *
     * <p>示例:
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
    public boolean searchMatrix2(int[][] matrix, int target) {
        int rows = matrix.length;
        if (rows == 0) {
            return false;
        }
        int cols = matrix[0].length;
        if (cols == 0) {
            return false;
        }
        int row = 0, col = cols - 1;
        while (row < rows && col >= 0) {
            if (matrix[row][col] == target) {
                return true;
            }
            if (matrix[row][col] > target) {
                col--;
            } else {
                row++;
            }
        }

        return false;
    }

    @Test
    public void findRightInterval() {

        int[][] intervals = {{1, 4}, {2, 3}, {3, 4}};
        int[] result = findRightInterval(intervals);
        log.debug("result:{}", result);
    }

    /**
     * 436. 寻找右区间
     *
     * <p>给定一组区间，对于每一个区间 i，检查是否存在一个区间 j，它的起始点大于或等于区间 i 的终点，这可以称为 j 在 i 的“右侧”。
     *
     * <p>对于任何区间，你需要存储的满足条件的区间 j 的最小索引，这意味着区间 j 有最小的起始点可以使其成为“右侧”区间。如果区间 j 不存在，则将区间 i 存储为
     * -1。最后，你需要输出一个值为存储的区间值的数组。
     *
     * <p>注意:
     *
     * <p>你可以假设区间的终点总是大于它的起始点。 你可以假定这些区间都不具有相同的起始点。 示例 1:
     *
     * <p>输入: [ [1,2] ] 输出: [-1]
     *
     * <p>解释:集合中只有一个区间，所以输出-1。 示例 2:
     *
     * <p>输入: [ [3,4], [2,3], [1,2] ] 输出: [-1, 0, 1]
     *
     * <p>解释:对于[3,4]，没有满足条件的“右侧”区间。 对于[2,3]，区间[3,4]具有最小的“右”起点; 对于[1,2]，区间[2,3]具有最小的“右”起点。 示例 3:
     *
     * <p>输入: [ [1,4], [2,3], [3,4] ] 输出: [-1, 2, -1]
     *
     * <p>解释:对于区间[1,4]和[3,4]，没有满足条件的“右侧”区间。 对于[2,3]，区间[3,4]有最小的“右”起点。
     *
     * @param intervals
     * @return
     */
    public int[] findRightInterval(int[][] intervals) {
        int len = intervals.length;
        int[] result = new int[len];
        Map<Integer, Integer> indexMap = new HashMap<>();
        int[] left = new int[len];
        for (int i = 0; i < len; i++) {
            indexMap.put(intervals[i][0], i);
            left[i] = intervals[i][0];
        }
        Arrays.sort(left);
        for (int i = 0; i < len; i++) {
            int right = intervals[i][1];
            int index = getLeft(left, right);
            logResult(index);
            if (index < 0) {
                result[i] = -1;
            } else {
                result[i] = indexMap.get(left[index]);
            }
        }

        return result;
    }

    /**
     * 获取左区间值
     *
     * @param leftNum
     * @param target
     * @return
     */
    private int getLeft(int[] leftNum, int target) {
        int low = 0, high = leftNum.length - 1;
        if (target > leftNum[high]) {
            return -1;
        }
        if (target <= leftNum[low]) {
            return 0;
        }
        while (low < high) {
            int mid = (low + high) >> 1;
            if (leftNum[mid] == target) {
                return mid;
            }
            if (leftNum[mid] > target) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }

    /**
     * 875. 爱吃香蕉的珂珂
     *
     * <p>珂珂喜欢吃香蕉。这里有 N 堆香蕉，第 i 堆中有 piles[i] 根香蕉。警卫已经离开了，将在 H 小时后回来。
     *
     * <p>珂珂可以决定她吃香蕉的速度 K （单位：根/小时）。每个小时，她将会选择一堆香蕉，从中吃掉 K 根。如果这堆香蕉少于 K
     * 根，她将吃掉这堆的所有香蕉，然后这一小时内不会再吃更多的香蕉。
     *
     * <p>珂珂喜欢慢慢吃，但仍然想在警卫回来前吃掉所有的香蕉。
     *
     * <p>返回她可以在 H 小时内吃掉所有香蕉的最小速度 K（K 为整数）。
     *
     * <p>示例 1：
     *
     * <p>输入: piles = [3,6,7,11], H = 8 输出: 4 示例 2：
     *
     * <p>输入: piles = [30,11,23,4,20], H = 5 输出: 30 示例 3：
     *
     * <p>输入: piles = [30,11,23,4,20], H = 6 输出: 23
     *
     * <p>提示：
     *
     * <p>1 <= piles.length <= 10^4 piles.length <= H <= 10^9 1 <= piles[i] <= 10^9
     *
     * @param piles
     * @param H
     * @return
     */
    public int minEatingSpeed(int[] piles, int H) {
        int left = 1, right = 0;
        for (int pile : piles) {
            right = Math.max(right, pile);
        }
        if (H == piles.length) {
            return right;
        }

        while (left < right) {
            int mid = (left + right) >> 1;
            if (getFinishTime(piles, mid) <= H) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return right;
    }

    private int getFinishTime(int[] piles, int speed) {
        int time = 0;

        for (int pile : piles) {
            time += (pile - 1) / speed + 1;
        }
        return time;
    }
}
