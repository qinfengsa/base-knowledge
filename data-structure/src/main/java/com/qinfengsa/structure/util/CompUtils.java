package com.qinfengsa.structure.util;

/**
 * @author qinfengsa
 * @date 2021/06/05 16:17
 */
public class CompUtils {

    /**
     * 初始化组合数
     *
     * @param n
     */
    public static long[][] initComp(int n) {
        long[][] compNum = new long[n + 1][n + 1];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= i; j++) {
                if (j == 0) {
                    compNum[i][j] = 1;
                } else {
                    compNum[i][j] = compNum[i - 1][j] + compNum[i - 1][j - 1];
                }
            }
        }
        return compNum;
    }

    /**
     * 初始化组合数
     *
     * @param n
     */
    public static long[][] initComp(int n, int mod) {
        long[][] compNum = new long[n + 1][n + 1];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= i; j++) {
                if (j == 0) {
                    compNum[i][j] = 1;
                } else {
                    compNum[i][j] = (compNum[i - 1][j] + compNum[i - 1][j - 1]) % mod;
                }
            }
        }
        return compNum;
    }
}
