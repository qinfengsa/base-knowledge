package com.qinfengsa.algorithm.util;

/**
 * Math 工具
 *
 * @author qinfengsa
 * @date 2021/6/11 9:20
 */
public final class MathUtils {

    /**
     * 最大公约数
     *
     * @param a
     * @param b
     * @return
     */
    public static long getGcd(long a, long b) {
        long max, min;
        max = Math.max(a, b);
        min = Math.min(a, b);
        if (min == 0L) {
            return max;
        }

        if (max % min != 0) {
            return getGcd(min, max % min);
        }
        return min;
    }

    /**
     * 最大公约数
     *
     * @param a
     * @param b
     * @return
     */
    public static int getGcd(int a, int b) {
        int max, min;
        max = Math.max(a, b);
        min = Math.min(a, b);
        if (min == 0L) {
            return max;
        }

        if (max % min != 0) {
            return getGcd(min, max % min);
        }
        return min;
    }
}
