package com.qinfengsa.algorithm.sort2.impl;

import com.qinfengsa.algorithm.sort2.ISort;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * 桶排序
 * @author: qinfengsa
 * @date: 2019/8/9 06:51
 */
public class BucketSort implements ISort {
    @Override
    public int[] sort(int[] sortArray) {

        // 核心思想，把数组sortArray 划分为n个大小相同子区间（桶），每个子区间各自排序，最后合并

        int max = sortArray[0], min = sortArray[0];
        for (int num : sortArray) {
            if (num > max) {
                max = num;
            }
            if (num < min) {
                min = num;
            }
        }
        // 确定桶的数量为 数组的长度
        int bucketNum = sortArray.length ;
        // 构建 bucketNum 个桶
        List<Integer>[] bucketList = new List[bucketNum];
        for (int i = 0; i < bucketNum ; i++) {
            bucketList[i] = new LinkedList<>();
        }
        // 区间大小, +1 防止 max 最大值所在桶 等于bucketNum导致数组越界
        double area = max - min + 1;

        // 遍历数组，然后把数组中的元素放入桶中
        for (int num : sortArray) {
            double a = num - min;
            int index = (int) (a/area*bucketNum);
            bucketList[index].add(num);
        }
        int arrayIndex = 0;
        for (List<Integer> list: bucketList) {
            if (Objects.nonNull(list) && ! list.isEmpty()) {
                // 桶内排序
                java.util.Collections.sort(list);
                for (Iterator<Integer> it = list.iterator(); it.hasNext(); ) {
                    sortArray[arrayIndex++] = it.next();
                }
            }
        }


        return sortArray;
    }
}
