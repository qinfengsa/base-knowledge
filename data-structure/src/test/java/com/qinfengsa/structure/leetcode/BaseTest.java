package com.qinfengsa.structure.leetcode;

import java.util.Arrays;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

/**
 * @author: qinfengsa
 * @date: 2019/5/16 15:45
 */
@Slf4j
public class BaseTest {

    @Test
    public void test1() {
        char b = '1';
        int a = (int) b;
        log.debug("result:{}", a);
    }

    @Test
    public void majorityElement() {
        int[] nums = {2, 2, 1, 1, 1, 2, 2};
        int result = majorityElement(nums);
        log.debug("result:{}", result);
    }

    /**
     * 求众数 给定一个大小为 n 的数组，找到其中的众数。众数是指在数组中出现次数大于 ⌊ n/2 ⌋ 的元素。
     *
     * <p>你可以假设数组是非空的，并且给定的数组总是存在众数。
     *
     * <p>示例 1:
     *
     * <p>输入: [3,2,3] 输出: 3 示例 2:
     *
     * <p>输入: [2,2,1,1,1,2,2] 输出: 2
     *
     * @param nums
     * @return
     */
    public int majorityElement(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length / 2];
    }

    @Test
    public void merge() {
        int[] nums1 = {1, 2, 3, 0, 0, 0};
        int m = 3;
        int[] nums2 = {2, 5, 6};
        int n = 3;
        merge(nums1, m, nums2, n);
        log.debug("result:{}", nums1);
    }

    /**
     * 合并两个有序数组 给定两个有序整数数组 nums1 和 nums2，将 nums2 合并到 nums1 中，使得 num1 成为一个有序数组。
     *
     * <p>说明:
     *
     * <p>初始化 nums1 和 nums2 的元素数量分别为 m 和 n。 你可以假设 nums1 有足够的空间（空间大小大于或等于 m + n）来保存 nums2 中的元素。 示例:
     *
     * <p>输入: nums1 = [1,2,3,0,0,0], m = 3 nums2 = [2,5,6], n = 3
     *
     * <p>输出: [1,2,2,3,5,6]
     *
     * @param nums1
     * @param m
     * @param nums2
     * @param n
     */
    public void merge(int[] nums1, int m, int[] nums2, int n) {

        // nums1 有足够的空间，采用尾插法，谁大先插入数组最后

        int index = m + n - 1;
        int i = m - 1, j = n - 1;
        while (i >= 0 || j >= 0) {

            if (i < 0) {
                nums1[index] = nums2[j];
                j--;
                index--;
                continue;
            }
            if (j < 0) {
                nums1[index] = nums1[i];
                i--;
                index--;
                continue;
            }

            if (nums1[i] >= nums2[j]) {
                nums1[index] = nums1[i];
                i--;
                index--;
            } else {
                nums1[index] = nums2[j];
                j--;
                index--;
            }
        }

        /*int index = 0;
        int[] nums0 = new int[m];
        for (int i = 0; i < m; i++) {
            nums0[i] = nums1[i];
        }
        int i = 0, j = 0;
        while ( i < m || j < n) {

            if (i == m) {
                nums1[index] = nums2[j];
                j++;
                index++;
                continue;
            }
            if (j == n) {
                nums1[index] = nums0[i];
                i++;
                index++;
                continue;
            }

            if (nums0[i] <= nums2[j]) {
                nums1[index] = nums0[i];
                i++;
                index++;
            } else {
                nums1[index] = nums2[j];
                j++;
                index++;
            }

        }*/

    }
}
