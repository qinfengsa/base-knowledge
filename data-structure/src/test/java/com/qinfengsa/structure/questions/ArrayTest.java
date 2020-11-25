package com.qinfengsa.structure.questions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

/**
 * 数组问题
 *
 * @author: qinfengsa
 * @date: 2019/5/26 07:59
 */
@Slf4j
public class ArrayTest {

    /**
     * 1、在一个给定的从1到100的整型数组中，如何快速找到缺失的数字？
     *
     * <p>2、如何找到一个给定的整型数组中的重复数字？
     *
     * <p>3、在一个未排序的整型数组中，如何找到最大和最小的数字？
     *
     * <p>4、在一个整型数组中，如何找到一个所有成对的数字，满足它们的和等于一个给定的数字？
     *
     * <p>5、如果一个数组包含多个重复元素，如何找到这些重复的数字？
     *
     * <p>6、用 Java 实现从一个给定数组中删除重复元素？
     *
     * <p>7、如何利用快速排序对一个整型数组进行排序？
     *
     * <p>8、如何从一个数组中删除重复元素？
     *
     * <p>9、用 Java 实现数组反转？
     *
     * <p>10、如何不借助库实现从数组中删除重复元素？
     */

    /** 1、在一个给定的从1到100的整型数组中，如何快速找到缺失的数字？ 构建一个长度为100的桶 把数组中的元素放入桶中，然后找到哪些桶是空的 */
    @Test
    public void test1() {
        int[] test = new int[100];
        Random random = new Random(1);
        for (int i = 0; i < 100; i++) {
            int num = random.nextInt(100);
            while (num <= 0) {
                num = random.nextInt(100);
            }
            test[i] = num;
        }
        log.debug("array:{}", test);
        boolean[] nums = new boolean[100];

        for (int index : test) {
            nums[index - 1] = true;
        }
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            if (!nums[i]) {
                list.add(i + 1);
            }
        }
        log.debug("result:{}", list);
    }

    /** 2.如何找到一个给定的整型数组中的重复数字？ HashMap,HashSet 或者 排序 */
    @Test
    public void test2() {
        int[] array = {1, 3, 5, 7, 8, 3, 7};

        Set<Integer> set = new HashSet<>();

        Set<Integer> result = new HashSet<>();

        for (int num : array) {
            if (set.contains(num)) {
                result.add(num);
            } else {
                set.add(num);
            }
        }
        log.debug("result:{}", Arrays.toString(result.toArray()));
    }

    /** 3、在一个未排序的整型数组中，如何找到最大和最小的数字？ */
    @Test
    public void test3() {
        int[] array = {3, 1, 5, 7, 8, 3, 7};
        int max = array[0];
        int min = array[0];
        for (int num : array) {
            if (num > max) {
                max = num;
            }
            if (num < min) {
                min = num;
            }
        }
        log.debug("min:{},max:{}", min, max);
    }

    /** 4、在一个整型数组中，如何找到一个所有成对的数字，满足它们的和等于一个给定的数字？ */
    @Test
    public void test4() {
        int[] array = {3, 1, 5, 7, 8, 6, 4};
        int target = 8;
        List<List<Integer>> result = getNum(array, target);
        log.debug("result:{}", result);
    }

    private List<List<Integer>> getNum(int[] array, int target) {
        if (array.length < 2) {
            return null;
        }
        List<List<Integer>> result = new ArrayList<>();
        Set<Integer> set = new HashSet<>();
        for (int num : array) {
            int t = target - num;
            if (set.contains(t)) {
                List<Integer> list = new ArrayList<>();
                list.add(t);
                list.add(num);
                result.add(list);
            }
            set.add(num);
        }
        return result;
    }

    /** 5、如果一个数组包含多个重复元素，如何找到这些重复的数字？ */
    @Test
    public void test5() {}

    /** 6、用 Java 实现从一个给定数组中删除重复元素？ */
    @Test
    public void test6() {}

    /** 7、如何利用快速排序对一个整型数组进行排序？ */
    @Test
    public void test7() {
        int[] sortArray = {82, 26, 79, 64, 37, 72, 39, 93, 25, 38};
        quickSort(sortArray);
        log.debug("result:{}", sortArray);
    }

    private int[] quickSort(int[] array) {

        quickSort(array, 0, array.length - 1);
        return array;
    }

    private void quickSort(int[] array, int start, int end) {
        if (end - start <= 1) {
            if (end - start == 1) {
                if (array[start] > array[end]) {
                    int tmp = array[start];
                    array[start] = array[end];
                    array[end] = tmp;
                }
            }
            return;
        }
        // 找一个元素，使得元素左边 都小于此元素，右边都大于此元素
        int key = array[start];
        int i = start, j = end;
        while (i < j) {
            // 从后向前找到 第一个小于key的数
            while (i < j && array[j] >= key) {
                j--;
            }
            array[i] = array[j];
            // 从前向后找到 第一个大于key的数
            while (i < j && array[i] <= key) {
                i++;
            }
            array[j] = array[i];
        }
        // i 和j 相遇
        array[i] = key;
        quickSort(array, start, i - 1);
        quickSort(array, i + 1, end);
    }

    /** 8、如何从一个数组中删除重复元素？ */
    @Test
    public void test8() {}

    /** 9、用 Java 实现数组反转？ */
    @Test
    public void test9() {
        int[] sortArray = {82, 26, 79, 64, 37, 72, 39, 93, 25, 38};
        reverse(sortArray);
        log.debug("result:{}", sortArray);
    }

    private void reverse(int[] array) {
        if (array.length <= 1) {
            return;
        }
        int left = 0, right = array.length - 1;
        while (left < right) {
            int tmp = array[left];
            array[left] = array[right];
            array[right] = tmp;
            left++;
            right--;
        }
    }

    /** 10、如何不借助库实现从数组中删除重复元素？ */
    @Test
    public void test10() {}
}
