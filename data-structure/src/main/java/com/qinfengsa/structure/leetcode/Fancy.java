package com.qinfengsa.structure.leetcode;

import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

/**
 * 1622. 奇妙序列
 *
 * <p>请你实现三个 API append，addAll 和 multAll 来实现奇妙序列。
 *
 * <p>请实现 Fancy 类 ：
 *
 * <p>Fancy() 初始化一个空序列对象。 void append(val) 将整数 val 添加在序列末尾。 void addAll(inc) 将所有序列中的现有数值都增加 inc 。
 * void multAll(m) 将序列中的所有现有数值都乘以整数 m 。 int getIndex(idx) 得到下标为 idx 处的数值（下标从 0 开始），并将结果对 109 + 7
 * 取余。如果下标大于等于序列的长度，请返回 -1 。
 *
 * <p>示例：
 *
 * <p>输入： ["Fancy", "append", "addAll", "append", "multAll", "getIndex", "addAll", "append",
 * "multAll", "getIndex", "getIndex", "getIndex"] [[], [2], [3], [7], [2], [0], [3], [10], [2], [0],
 * [1], [2]] 输出： [null, null, null, null, null, 10, null, null, null, 26, 34, 20]
 *
 * <p>解释： Fancy fancy = new Fancy(); fancy.append(2); // 奇妙序列：[2] fancy.addAll(3); // 奇妙序列：[2+3] ->
 * [5] fancy.append(7); // 奇妙序列：[5, 7] fancy.multAll(2); // 奇妙序列：[5*2, 7*2] -> [10, 14]
 * fancy.getIndex(0); // 返回 10 fancy.addAll(3); // 奇妙序列：[10+3, 14+3] -> [13, 17] fancy.append(10);
 * // 奇妙序列：[13, 17, 10] fancy.multAll(2); // 奇妙序列：[13*2, 17*2, 10*2] -> [26, 34, 20]
 * fancy.getIndex(0); // 返回 26 fancy.getIndex(1); // 返回 34 fancy.getIndex(2); // 返回 20
 *
 * <p>提示：
 *
 * <p>1 <= val, inc, m <= 100 0 <= idx <= 105 总共最多会有 105 次对 append，addAll，multAll 和 getIndex 的调用。
 *
 * @author qinfengsa
 * @date 2021/06/05 16:35
 */
@Slf4j
public class Fancy {

    static int MOD = 1_000_000_007;

    private final List<Integer> list;

    private long globalInc = 0L, globalMult = 1L;

    /** 初始化一个空序列对象 */
    public Fancy() {
        list = new ArrayList<>();
    }

    /**
     * 快速幂
     *
     * @param x
     * @param n
     * @return
     */
    public static long myPow(long x, int n) {
        if (n == 0 && x != 0) {
            return 1;
        }
        // 判断n 是否为负数
        long result = 1L;

        for (int i = n; i != 0; i >>= 1) {

            if ((i & 1) == 1) {
                result *= x;
                result %= MOD;
            }
            x *= x;
            x %= MOD;
        }

        return result;
    }

    /**
     * 乘法逆元
     *
     * @return
     */
    private long getInv(long num) {
        return myPow(num, MOD - 2);
    }

    /**
     * 将整数 val 添加在序列末尾
     *
     * @param val
     */
    public void append(int val) {
        long num = (val - globalInc + MOD) % MOD;
        long inv = getInv(globalMult) % MOD;
        list.add((int) (num * inv % MOD));
    }

    /**
     * 将所有序列中的现有数值都增加 inc
     *
     * @param inc
     */
    public void addAll(int inc) {
        globalInc += inc;
        globalInc %= MOD;
    }

    /**
     * 将序列中的所有现有数值都乘以整数 m
     *
     * @param m
     */
    public void multAll(int m) {
        globalMult *= m;
        globalInc *= m;
        globalMult %= MOD;
        globalInc %= MOD;
    }

    /**
     * 得到下标为 idx 处的数值（下标从 0 开始），并将结果对 109 + 7 取余。如果下标大于等于序列的长度，请返回 -1 。
     *
     * @param idx
     * @return
     */
    public int getIndex(int idx) {
        if (idx >= list.size()) {
            return -1;
        }
        int num = list.get(idx);
        long result = ((long) num * globalMult % MOD + globalInc) % MOD;
        return (int) result;
    }

    public static void main(String[] args) {
        Fancy fancy = new Fancy();
        fancy.append(2); // 奇妙序列：[2]
        fancy.addAll(3); // 奇妙序列：[2+3] -> [5]
        fancy.append(7); // 奇妙序列：[5, 7]
        fancy.multAll(2); // 奇妙序列：[5*2, 7*2] -> [10, 14]
        int num1 = fancy.getIndex(0); // 返回 10
        log.debug("num1:{}", num1);
        fancy.addAll(3); // 奇妙序列：[10+3, 14+3] -> [13, 17]
        fancy.append(10); // 奇妙序列：[13, 17, 10]
        fancy.multAll(2); // 奇妙序列：[13*2, 17*2, 10*2] -> [26, 34, 20]
        int num2 = fancy.getIndex(0); // 返回 26
        log.debug("num2:{}", num2);
        int num3 = fancy.getIndex(1); // 返回 34
        log.debug("num3:{}", num3);
        int num4 = fancy.getIndex(2); // 返回 20
        log.debug("num4:{}", num4);
    }
}
