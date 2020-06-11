package com.qinfengsa.structure.leetcode;

import lombok.extern.slf4j.Slf4j;

/**
 * 面试题 16.09. 运算
 *
 * <p>请实现整数数字的乘法、减法和除法运算，运算结果均为整数数字，程序中只允许使用加法运算符和逻辑运算符，允许程序中出现正负常数，不允许使用位运算。
 *
 * <p>你的实现应该支持如下操作：
 *
 * <p>Operations() 构造函数 minus(a, b) 减法，返回a - b multiply(a, b) 乘法，返回a * b divide(a, b) 除法，返回a / b 示例：
 *
 * <p>Operations operations = new Operations(); operations.minus(1, 2); //返回-1
 * operations.multiply(3, 4); //返回12 operations.divide(5, -2); //返回-2 提示：
 *
 * <p>你可以假设函数输入一定是有效的，例如不会出现除法分母为0的情况 单个用例的函数调用次数不会超过1000次
 *
 * @author qinfengsa
 * @date 2020/06/11 13:11
 */
@Slf4j
public class Operations {

    public Operations() {}

    private boolean shouldSwap(int a, int b) {
        return Math.abs(a) < Math.abs(b);
    }

    public int minus(int a, int b) {
        if (b == 0) {
            return a;
        }

        // 取b的反码
        return a + ~b + 1;
    }

    public int multiply(int a, int b) {
        if (a == 0 || b == 0) {
            return 0;
        }
        if (shouldSwap(a, b)) {
            int tmp = a;
            a = b;
            b = tmp;
        }
        if (b == 1) {
            return a;
        }
        if (b == -1) {
            return -a;
        }

        int sum = 0;
        boolean flag = b > 0;
        if (!flag) {
            b = -b;
        }
        // 每次翻倍 bit 是 1 > 2 > 4 > 8
        // 相当于 b = n0 * 1 + n1 * 2 + n2 * 4 + n3 * 8
        int bit = 1;
        while (b >= bit && bit > 0) {
            // 判断当前位是否需要加
            if ((b & bit) > 0) {
                sum += a;
            }
            a += a;
            bit += bit;
        }
        return flag ? sum : -sum;
    }

    public int divide(int a, int b) {
        if (a == 0 || b == 0) {
            return 0;
        }

        if (b == 1) {
            return a;
        }
        if (b == -1) {
            return -a;
        }
        //
        boolean flag = (a > 0 && b > 0) || (a < 0 && b < 0);
        a = Math.abs(a);
        b = Math.abs(b);
        // 利用二进制除法, 计算出每一位的除数
        int[] nums = new int[32];
        int[] bits = new int[32];
        nums[0] = b;
        bits[0] = 1;
        int idx = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i - 1] + nums[i - 1] > a || nums[i - 1] + nums[i - 1] < 0) {
                break;
            }
            nums[i] = nums[i - 1] + nums[i - 1];
            bits[i] = bits[i - 1] + bits[i - 1];
            idx++;
        }
        int div = 0;
        for (int i = idx; i >= 0; i--) {
            if (a >= nums[i]) {
                a = minus(a, nums[i]);
                div += bits[i];
            }
        }

        return flag ? div : -div;
    }

    public static void main(String[] args) {
        int num1 = -10, num2 = -7;
        Operations operations = new Operations();
        log.debug("a - b = {}", operations.minus(num1, num2));
        log.debug("a * b = {}", operations.multiply(num1, num2));
        log.debug("a / b = {}", operations.divide(num1, num2));
    }
}
