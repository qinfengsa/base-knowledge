package com.qinfengsa.algorithm.math;

import lombok.extern.slf4j.Slf4j;

/**
 * 数学
 *
 * @author qinfengsa
 * @date 2021/05/15 23:39
 */
@Slf4j
public class MathMain {

    /**
     * 5743. 增长的内存泄露
     *
     * <p>给你两个整数 memory1 和 memory2 分别表示两个内存条剩余可用内存的位数。现在有一个程序每秒递增的速度消耗着内存。
     *
     * <p>在第 i 秒（秒数从 1 开始），有 i 位内存被分配到 剩余内存较多 的内存条（如果两者一样多，则分配到第一个内存条）。如果两者剩余内存都不足 i 位，那么程序将 意外退出 。
     *
     * <p>请你返回一个数组，包含 [crashTime, memory1crash, memory2crash] ，其中 crashTime是程序意外退出的时间（单位为秒），
     * memory1crash 和 memory2crash 分别是两个内存条最后剩余内存的位数。
     *
     * <p>示例 1：
     *
     * <p>输入：memory1 = 2, memory2 = 2 输出：[3,1,0] 解释：内存分配如下： - 第 1 秒，内存条 1 被占用 1 位内存。内存条 1 现在有 1
     * 位剩余可用内存。 - 第 2 秒，内存条 2 被占用 2 位内存。内存条 2 现在有 0 位剩余可用内存。 - 第 3 秒，程序意外退出，两个内存条分别有 1 位和 0 位剩余可用内存。
     * 示例 2：
     *
     * <p>输入：memory1 = 8, memory2 = 11 输出：[6,0,4] 解释：内存分配如下： - 第 1 秒，内存条 2 被占用 1 位内存，内存条 2 现在有 10
     * 位剩余可用内存。 - 第 2 秒，内存条 2 被占用 2 位内存，内存条 2 现在有 8 位剩余可用内存。 - 第 3 秒，内存条 1 被占用 3 位内存，内存条 1 现在有 5
     * 位剩余可用内存。 - 第 4 秒，内存条 2 被占用 4 位内存，内存条 2 现在有 4 位剩余可用内存。 - 第 5 秒，内存条 1 被占用 5 位内存，内存条 1 现在有 0
     * 位剩余可用内存。 - 第 6 秒，程序意外退出，两个内存条分别有 0 位和 4 位剩余可用内存。
     *
     * <p>提示：
     *
     * <p>0 <= memory1, memory2 <= 231 - 1
     *
     * @param memory1
     * @param memory2
     * @return
     */
    public int[] memLeak(int memory1, int memory2) {
        int[] result = new int[3];
        int i = 1;
        while (memory1 >= i || memory2 >= i) {
            if (memory1 >= i && memory2 >= i) {
                if (memory1 >= memory2) {
                    memory1 -= i;
                } else {
                    memory2 -= i;
                }

            } else if (memory1 >= i) {
                memory1 -= i;
            } else {
                memory2 -= i;
            }
            i++;
        }
        result[0] = i;
        result[1] = memory1;
        result[2] = memory2;

        return result;
    }
}
