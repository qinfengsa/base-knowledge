package com.qinfengsa.algorithm.search;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * 搜索 test
 * @author: qinfengsa
 * @date: 2019/11/13 08:46
 */
@Slf4j
public class SearchTest {


    @Test
    public void countSmaller() {
        int[] nums = {5,2,6,1};
        List<Integer> result = countSmaller(nums);
        log.debug("result :{}",result);
    }


    /**
     * 计算右侧小于当前元素的个数
     * 给定一个整数数组 nums，按要求返回一个新数组 counts。数组 counts 有该性质： counts[i] 的值是  nums[i] 右侧小于 nums[i] 的元素的数量。
     *
     * 示例:
     *
     * 输入: [5,2,6,1]
     * 输出: [2,1,1,0]
     * 解释:
     * 5 的右侧有 2 个更小的元素 (2 和 1).
     * 2 的右侧仅有 1 个更小的元素 (1).
     * 6 的右侧有 1 个更小的元素 (1).
     * 1 的右侧有 0 个更小的元素.
     * @param nums
     * @return
     */
    public List<Integer> countSmaller(int[] nums) {
        // 暴力破解 ，时间复杂度O（N^2） 舍弃
        /*List<Integer> result = new ArrayList<>();
        int len = nums.length;
        if (len == 1) {
            result.add(0);
            return result;
        }
        int[] counts = new int[len];
        for (int i = 0; i < len - 1; i++) {
            for (int j = i + 1; j < len; j++ ) {
                if (nums[i] > nums[j]) {
                    counts[i]++;
                }
            }
        }
        for (int count: counts) {
           result.add(count);
        }
        return result;*/

        // 从后往前进行 排序(二分插入)
        LinkedList<Integer> result = new LinkedList<>();
        int len = nums.length;
        if (len == 0) {
            return result;
        }
        if (len == 1) {
            result.add(0);
            return result;
        }
        int[] newNums = new int[len];

        newNums[0] = nums[len-1];
        result.add(0);
        for (int i = len - 2 ; i >= 0; i--) {
            // 已排序的元素个数
            int lastIndex = len - 1 - i - 1 ;
            int index = lastIndex + 1;
            int num = nums[i];
            log.debug("i:{} ; lastIndex:{}",i,lastIndex);
            if (num <= newNums[0]) { // 位于 最小位置
                // 所有元素后移
                System.arraycopy(newNums, 0, newNums, 1, lastIndex +1);
                newNums[0] = num;
                result.addFirst(0);
                log.debug("newNums1:{}",newNums);
                continue;

            } else if (num > newNums[lastIndex]) {// 位于 最大位置
                newNums[index] = num;
                result.addFirst(index);
                log.debug("newNums2:{}",newNums);
                continue;
            } else if (lastIndex <= 1) {
                for (int j = 0;j <= lastIndex; j++) {
                    if (num <= newNums[j]) {
                        index = j;
                        break;
                    }
                }
            } else {
                // 二分法插入
                int left = 0,right = lastIndex;
                int mid;
                while (left < right) {
                    mid = left + (right-left) / 2;
                    // 缩小[left, right]区间
                    if (num > newNums[mid]) {
                        left = mid + 1;
                    } else {
                        right = mid;
                    }
                }
                index = left;

            }
            //newNums[index]
            // index 后的元素后移
            System.arraycopy(newNums, index, newNums, index + 1, lastIndex - index + 1);
            newNums[index] = num;
            result.addFirst(index );
            log.debug("newNums3:{}",newNums);
        }


        return result;
    }


    @Test
    public void copyTest() {

        int[] nums = new int[10];
        nums[0] = 1;
        nums[1] = 2;
        nums[2] = 3;
        System.arraycopy(nums, 0, nums, 1, 3);
        log.debug("nums:{}",nums);
    }
}
