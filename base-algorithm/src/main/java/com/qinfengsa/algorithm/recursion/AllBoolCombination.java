package com.qinfengsa.algorithm.recursion;

import lombok.extern.slf4j.Slf4j;

/**
 * 编写一个算法输出 n 个布尔量的所有可能组合。
 * @author: qinfengsa
 * @date: 2019/5/8 11:27
 */
@Slf4j
public class AllBoolCombination {

    public static void main(String[] args) {

        int size = 2;
        int a[] = new int[size];
        AllBoolCombination acb = new AllBoolCombination();
        acb.coding(a,size-1);
    }

    public void coding(int[] b, int n) {

        if (n == 0) {
            b[n] = 0;logCode(b);
            b[n] = 1;logCode(b);
        } else {
            b[n] = 0;coding(b,n-1);
            b[n] = 1;coding(b,n-1);
        }

    }

    private void logCode(int[] b) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < b.length; i++) {
            sb.append(b[i]);
        }
        log.info(sb.toString());
    }
}
