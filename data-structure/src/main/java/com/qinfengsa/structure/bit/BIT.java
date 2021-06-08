package com.qinfengsa.structure.bit;

/**
 * 树状数组
 *
 * @author qinfengsa
 * @date 2021/6/7 13:32
 */
public class BIT {

    private final int n;

    private final int[] nums;

    public BIT(int n) {
        this.n = n;
        this.nums = new int[n + 1];
    }

    private int lowBit(int x) {
        return x & -x;
    }

    /**
     * 获取 1 ~ x 的 和
     *
     * @param x
     * @return
     */
    public int getSum(int x) {
        int result = 0;
        while (x > 0) {
            result += nums[x];
            x -= lowBit(x);
        }

        return result;
    }

    /**
     * 获取 区间 x ~ y 的和
     *
     * @param x
     * @param y
     * @return
     */
    public int getSum(int x, int y) {
        return getSum(y) - getSum(x - 1);
    }

    /**
     * 更新 x 点的值
     *
     * @param x
     * @param val
     */
    public void update(int x, int val) {
        while (x <= n) {
            nums[x] += val;
            x += lowBit(x);
        }
    }
}
