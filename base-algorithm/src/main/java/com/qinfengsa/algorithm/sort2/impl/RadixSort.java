package com.qinfengsa.algorithm.sort2.impl;

import com.qinfengsa.algorithm.sort2.ISort;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * 计数排序
 * @author: qinfengsa
 * @date: 2019/8/9 06:51
 */
public class RadixSort implements ISort {
    @Override
    public int[] sort(int[] sortArray) {

        int len = sortArray.length;
        // 先从个位数排序，然后十位，百位
        int max = sortArray[0];
        for (int num : sortArray) {
            if (num > max) {
                max = num;
            }
        }
        // 求数组的最高位数
        int numLen = 0;
        while (max > 0 ) {
            max /= 10;
            numLen++;
        }

        List<Integer>[] bitList = new List[10];
        for (int i = 0; i < 10 ; i++) {
            bitList[i] = new LinkedList<>();
        }

        for (int i = 0; i < numLen; i++) {

            for (int num : sortArray) {
                // 计算 num 中 第 i+1 位 的数字
                int index = num % (int)Math.pow(10.0,i+1) / (int)Math.pow(10.0,i);
                bitList[index].add(num);
            }
            int arrayIndex = 0;
            for (int j = 0; j < 10; j++) {
                List<Integer> list = bitList[j];
                if (Objects.nonNull(list) && ! list.isEmpty()) {
                    for (Iterator<Integer> it = list.iterator(); it.hasNext(); ) {
                        sortArray[arrayIndex++] = it.next();
                        it.remove();
                    }
                }
            }
        }

        return sortArray;
    }

}
