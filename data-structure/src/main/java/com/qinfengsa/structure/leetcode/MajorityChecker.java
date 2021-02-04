package com.qinfengsa.structure.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;

/**
 * 1157. 子数组中占绝大多数的元素
 *
 * <p>实现一个 MajorityChecker 的类，它应该具有下述几个 API：
 *
 * <p>MajorityChecker(int[] arr) 会用给定的数组 arr 来构造一个 MajorityChecker 的实例。
 *
 * <p>int query(int left, int right, int threshold) 有这么几个参数：
 *
 * <p>0 <= left <= right < arr.length 表示数组 arr 的子数组的长度。
 *
 * <p>2 * threshold > right - left + 1，也就是说阈值 threshold 始终比子序列长度的一半还要大。
 *
 * <p>每次查询 query(...) 会返回在 arr[left], arr[left+1], ..., arr[right] 中至少出现阈值次数 threshold
 * 的元素，如果不存在这样的元素，就返回 -1。
 *
 * <p>示例：
 *
 * <p>MajorityChecker majorityChecker = new MajorityChecker([1,1,2,2,1,1]);
 *
 * <p>majorityChecker.query(0,5,4); // 返回 1
 *
 * <p>majorityChecker.query(0,3,3); // 返回 -1
 *
 * <p>majorityChecker.query(2,3,2); // 返回 2
 *
 * <p>提示：
 *
 * <p>1 <= arr.length <= 20000 1 <= arr[i] <= 20000
 *
 * <p>对于每次查询，0 <= left <= right < len(arr) 对于每次查询，2 * threshold > right - left + 1
 *
 * <p>查询次数最多为 10000
 *
 * @author qinfengsa
 * @date 2021/2/2 13:45
 */
@Slf4j
public class MajorityChecker {

    int[] nums;

    // 每个元素值 的 index列表
    Map<Integer, List<Integer>> numListMap = new HashMap<>();

    List<Integer> numList;

    int[] numCountArr;

    public MajorityChecker(int[] arr) {
        nums = arr;
        for (int i = 0; i < arr.length; i++) {
            List<Integer> list = numListMap.computeIfAbsent(arr[i], k -> new ArrayList<>());
            list.add(i);
        }
        // 存储所有超过25的 num , 低于25（right - left <= 50）的直接暴力解决
        numList =
                numListMap.keySet().stream()
                        .filter(num -> numListMap.get(num).size() > 25)
                        .sorted(Comparator.comparingInt(a -> numListMap.get(a).size()))
                        .collect(Collectors.toList());
        numCountArr = new int[numList.size()];
        for (int i = 0; i < numList.size(); i++) {
            int num = numList.get(i);
            numCountArr[i] = numListMap.get(num).size();
        }
    }

    public int query(int left, int right, int threshold) {

        if (right - left <= 50) {
            // 暴力
            Map<Integer, Integer> countMap = new HashMap<>();
            for (int i = left; i <= right; i++) {
                int num = nums[i];
                int count = countMap.getOrDefault(num, 0) + 1;
                if (count >= threshold) {
                    return num;
                }
                countMap.put(num, count);
            }
            return -1;
        }
        // 查找所有 大于 等于 threshold 的 num
        int pos = Arrays.binarySearch(numCountArr, threshold - 1);
        if (pos < 0) {
            pos = -1 * pos - 1;
        }
        for (int i = pos; i < numList.size(); i++) {
            int num = numList.get(i);
            List<Integer> indexList = numListMap.get(num);
            int start = Collections.binarySearch(indexList, left);
            if (start < 0) {
                start = -1 * start - 1;
            }

            int end = Collections.binarySearch(indexList, right);
            if (end < 0) {
                end = -1 * end - 2;
            }

            if (end - start + 1 >= threshold) {
                return num;
            }
        }

        return -1;
    }
}
