package com.qinfengsa.algorithm.leetcode;

import static com.qinfengsa.algorithm.util.LogUtils.logResult;

import com.qinfengsa.algorithm.util.LogUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.Stack;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

/**
 * @author: qinfengsa
 * @date: 2019/5/22 11:40
 */
@Slf4j
public class MathTest {

    @Test
    public void fizzBuzz() {
        int n = 15;
        List<String> list = fizzBuzz(n);
        log.debug("result:{}", list);
    }

    /**
     * Fizz Buzz 写一个程序，输出从 1 到 n 数字的字符串表示。
     *
     * <p>1. 如果 n 是3的倍数，输出“Fizz”；
     *
     * <p>2. 如果 n 是5的倍数，输出“Buzz”；
     *
     * <p>3.如果 n 同时是3和5的倍数，输出 “FizzBuzz”。
     *
     * <p>示例：
     *
     * <p>n = 15,
     *
     * <p>返回: [ "1", "2", "Fizz", "4", "Buzz", "Fizz", "7", "8", "Fizz", "Buzz", "11", "Fizz", "13",
     * "14", "FizzBuzz" ]
     *
     * @param n
     * @return
     */
    public List<String> fizzBuzz(int n) {

        List<String> list = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            sb.setLength(0);

            if (i % 3 == 0) {
                sb.append("Fizz");
            }
            if (i % 5 == 0) {
                sb.append("Buzz");
            }

            if (sb.length() == 0) {
                sb.append(i);
            }
            list.add(sb.toString());
        }
        return list;
    }

    @Test
    public void countPrimes() {
        int n = 20;
        int result = countPrimes(n);
        log.debug("result:{}", result);
    }

    @Test
    public void test() {
        int n = 12;
        int result = n & 1;
        log.debug("result:{}", result);
    }

    /**
     * 计数质数 统计所有小于非负整数 n 的质数的数量。
     *
     * <p>示例:
     *
     * <p>输入: 10 输出: 4 解释: 小于 10 的质数一共有 4 个, 它们是 2, 3, 5, 7 。
     *
     * @param n
     * @return
     */
    public int countPrimes(int n) {
        // 解法3：厄拉多塞筛法
        // 西元前250年，希腊数学家厄拉多塞(Eeatosthese)想到了一个非常美妙的质数筛法，减少了逐一检查每个数的的步骤，
        // 可以比较简单的从一大堆数字之中，筛选出质数来，这方法被称作厄拉多塞筛法(Sieve of Eeatosthese)。
        //
        // 具体操作：先将 2~n 的各个数放入表中，然后在2的上面画一个圆圈，然后划去2的其他倍数；第一个既未画圈又没有被划去的数是3，
        // 将它画圈，再划去3的其他倍数；现在既未画圈又没有被划去的第一个数 是5，将它画圈，并划去5的其他倍数……依次类推，一直到所有小于或等于
        // n 的各数都画了圈或划去为止。这时，表中画了圈的以及未划去的那些数正好就是小于 n 的素数。

        // 申请一个足够空间的数组，进行标记
        boolean[] nums = new boolean[n];
        for (int i = 2; i < nums.length; i++) { // 首先标记全部标记为true
            nums[i] = true;
        }

        for (int i = 2; i * i <= n; i++) {
            // 如果i位置是质数，
            if (nums[i]) {
                int k = 2;
                while (k * i < n) {
                    nums[k * i] = false;
                    k++;
                }
            }
        }

        int count = 0;

        for (boolean bool : nums) {
            count += bool ? 1 : 0;
        }
        return count;
    }

    public int countPrimesOld(int n) {

        int count = 0;
        int num = 1;
        while (num < n) {
            if (isPrime(num)) {
                count++;
            }

            num++;
        }

        return count;
    }

    private boolean isPrime(int num) {
        if (num == 1) {
            return false;
        }
        if (num == 2) {
            return true;
        }
        // 除了2偶数都是false
        if ((num & 1) == 0) {
            return false;
        }
        for (int i = 3; i * i <= num; i++) {

            if (num % i == 0) {
                return false;
            }
        }

        return true;
    }

    @Test
    public void isPowerOfThree() {
        int n = 45;
        boolean result = isPowerOfThree(n);
        log.debug("result:{}", result);
    }

    /**
     * 3的幂 给定一个整数，写一个函数来判断它是否是 3 的幂次方。
     *
     * <p>示例 1:
     *
     * <p>输入: 27 输出: true 示例 2:
     *
     * <p>输入: 0 输出: false 示例 3:
     *
     * <p>输入: 9 输出: true 示例 4:
     *
     * <p>输入: 45 输出: false 进阶： 你能不使用循环或者递归来完成本题吗？
     *
     * @param n
     * @return
     */
    public boolean isPowerOfThree(int n) {

        while (n >= 3) {
            if (n % 3 != 0) {
                return false;
            }
            n = n / 3;
        }
        if (n == 1) {
            return true;
        }
        return false;
    }

    @Test
    public void romanToInt() {
        String s = "MCMXCIV";
        int result = romanToInt(s);
        log.debug("result:{}", result);
    }

    /**
     * 罗马数字转整数 罗马数字包含以下七种字符: I， V， X， L，C，D 和 M。
     *
     * <p>字符 数值 I 1 V 5 X 10 L 50 C 100 D 500 M 1000 例如， 罗马数字 2 写做 II ，即为两个并列的 1。12 写做 XII ，即为 X +
     * II 。 27 写做 XXVII, 即为 XX + V + II 。
     *
     * <p>通常情况下，罗马数字中小的数字在大的数字的右边。但也存在特例，例如 4 不写做 IIII，而是 IV。数字 1 在数字 5 的左边，所表示的数等于大数 5 减小数 1 得到的数值
     * 4 。同样地，数字 9 表示为 IX。这个特殊的规则只适用于以下六种情况：
     *
     * <p>I 可以放在 V (5) 和 X (10) 的左边，来表示 4 和 9。 X 可以放在 L (50) 和 C (100) 的左边，来表示 40 和 90。 C 可以放在 D
     * (500) 和 M (1000) 的左边，来表示 400 和 900。 给定一个罗马数字，将其转换成整数。输入确保在 1 到 3999 的范围内。
     *
     * <p>示例 1:
     *
     * <p>输入: "III" 输出: 3 示例 2:
     *
     * <p>输入: "IV" 输出: 4 示例 3:
     *
     * <p>输入: "IX" 输出: 9 示例 4:
     *
     * <p>输入: "LVIII" 输出: 58 解释: L = 50, V= 5, III = 3. 示例 5:
     *
     * <p>输入: "MCMXCIV" 输出: 1994 解释: M = 1000, CM = 900, XC = 90, IV = 4.
     *
     * @param s
     * @return
     */
    public int romanToInt(String s) {

        int result = 0;
        char[] chars = s.toCharArray();
        int len = chars.length;
        for (int i = 0; i < len; i++) {
            char c = chars[i];
            switch (c) {
                case 'I':
                    // I 可以放在 V (5) 和 X (10) 的左边，来表示 4 和 9。
                    if (i + 1 < len && (chars[i + 1] == 'V' || chars[i + 1] == 'X')) {
                        result -= 1;
                    } else {
                        result += 1;
                    }
                    break;
                case 'V':
                    result += 5;
                    break;
                case 'X':
                    // X 可以放在 L (50) 和 C (100) 的左边，来表示 40 和 90。
                    if (i + 1 < len && (chars[i + 1] == 'L' || chars[i + 1] == 'C')) {
                        result -= 10;
                    } else {
                        result += 10;
                    }
                    break;
                case 'L':
                    result += 50;
                    break;
                case 'C':
                    // C 可以放在 D (500) 和 M (1000) 的左边，来表示 400 和 900。
                    if (i + 1 < len && (chars[i + 1] == 'D' || chars[i + 1] == 'M')) {
                        result -= 100;
                    } else {
                        result += 100;
                    }
                    break;
                case 'D':
                    result += 500;
                    break;
                case 'M':
                    result += 1000;
                    break;
                default:
                    break;
            }
        }

        return result;
    }

    @Test
    public void trailingZeroes() {
        int n = 125;
        int result = trailingZeroes(n);
        log.debug("result:{}", result);
    }

    /**
     * 阶乘后的零 给定一个整数 n，返回 n! 结果尾数中零的数量。
     *
     * <p>示例 1:
     *
     * <p>输入: 3 输出: 0 解释: 3! = 6, 尾数中没有零。 示例 2:
     *
     * <p>输入: 5 输出: 1 解释: 5! = 120, 尾数中有 1 个零. 说明: 你算法的时间复杂度应为 O(log n) 。
     *
     * @param n
     * @return
     */
    public int trailingZeroes(int n) {

        if (n < 5) {
            return 0;
        }

        // 0的个数和5,10 有关
        int a = n / 5;
        int result = a;
        while (a > 0) {
            a = a / 5;
            result += a;
        }

        return result;
    }

    @Test
    public void titleToNumber() {
        String s = "BB";

        int result = titleToNumber(s);
        log.debug("result:{}", result);
    }

    /**
     * Excel表列序号 给定一个Excel表格中的列名称，返回其相应的列序号。
     *
     * <p>例如，
     *
     * <p>A -> 1 B -> 2 C -> 3 ... Z -> 26 AA -> 27 AB -> 28 ... 示例 1:
     *
     * <p>输入: "A" 输出: 1 示例 2:
     *
     * <p>输入: "AB" 输出: 28 示例 3:
     *
     * <p>输入: "ZY" 输出: 701
     *
     * @param s
     * @return
     */
    public int titleToNumber(String s) {
        char[] chars = s.toCharArray();

        int result = 0;

        int lastNum = 0;
        for (char c : chars) {
            result = lastNum * 26 + (c - 'A') + 1;
            lastNum = result;
        }

        return result;
    }

    @Test
    public void convertToTitle() {

        int n = 701;
        logResult(convertToTitle(n));
    }

    /**
     * 168. Excel表列名称 给定一个正整数，返回它在 Excel 表中相对应的列名称。
     *
     * <p>例如，
     *
     * <p>1 -> A 2 -> B 3 -> C ... 26 -> Z 27 -> AA 28 -> AB ... 示例 1:
     *
     * <p>输入: 1 输出: "A" 示例 2:
     *
     * <p>输入: 28 输出: "AB" 示例 3:
     *
     * <p>输入: 701 输出: "ZY"
     *
     * @param n
     * @return
     */
    public String convertToTitle(int n) {
        StringBuilder sb = new StringBuilder();

        while (n > 0) {
            int num = n % 26;
            if (num == 0) {
                num = 26;
                n--;
            }
            char c = (char) ('A' + num - 1);
            sb.insert(0, c);
            n /= 26;
        }

        return sb.toString();
    }

    @Test
    public void test1() {
        char c = 'a';
        int num = 1;
        char b = (char) (c + num);
        logResult(b);
    }

    @Test
    public void divide() {
        int dividend = 7, divisor = -3;

        int result = divide(dividend, divisor);
        log.debug("result:{}", result);
    }

    /**
     * 两数相除 给定两个整数，被除数 dividend 和除数 divisor。将两数相除，要求不使用乘法、除法和 mod 运算符。
     *
     * <p>返回被除数 dividend 除以除数 divisor 得到的商。
     *
     * <p>示例 1:
     *
     * <p>输入: dividend = 10, divisor = 3 输出: 3 示例 2:
     *
     * <p>输入: dividend = 7, divisor = -3 输出: -2 说明:
     *
     * <p>被除数和除数均为 32 位有符号整数。 除数不为 0。 假设我们的环境只能存储 32 位有符号整数，其数值范围是 [−2^31, 2^31 −
     * 1]。本题中，如果除法结果溢出，则返回 231 − 1。
     *
     * @param dividend
     * @param divisor
     * @return
     */
    public int divide(int dividend, int divisor) {
        if (divisor == 1) {
            return dividend;
        }

        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        } else if (divisor == -1) {
            return -dividend;
        }
        Long lDividend = (long) dividend;
        Long lDivisor = (long) divisor;

        boolean flag = true; // true 表示正数，false表示负数；
        // 把 负数转为正数
        if (lDividend < 0) {
            lDividend = -lDividend;
            flag = false;
        }
        if (divisor < 0) {
            lDivisor = -lDivisor;
            flag = !flag;
        }
        int result = 0;
        int count = 0;
        // 把除数不断左移，直到 2*除数 > 被除数
        while (lDividend - lDivisor >= lDivisor) {
            count++;
            lDivisor <<= 1;
        }

        while (count >= 0) {
            if (lDivisor <= lDividend) {
                result += (1 << count);
                lDividend -= lDivisor;
            }
            count--;
            lDivisor >>= 1; // 除数右移
        }

        return flag ? result : -result;
    }

    @Test
    public void fractionToDecimal() {
        int numerator = 1;
        int denominator = 14;
        String result = fractionToDecimal(numerator, denominator);
        log.debug("result:{}", result);
    }

    /**
     * 分数到小数 给定两个整数，分别表示分数的分子 numerator 和分母 denominator，以字符串形式返回小数。
     *
     * <p>如果小数部分为循环小数，则将循环的部分括在括号内。
     *
     * <p>示例 1:
     *
     * <p>输入: numerator = 1, denominator = 2 输出: "0.5" 示例 2:
     *
     * <p>输入: numerator = 2, denominator = 1 输出: "2" 示例 3:
     *
     * <p>输入: numerator = 2, denominator = 3 输出: "0.(6)"
     *
     * @param numerator
     * @param denominator
     * @return
     */
    public String fractionToDecimal(int numerator, int denominator) {

        if (numerator == 0) {
            return "0";
        }
        StringBuilder fraction = new StringBuilder();
        // 判断结果是否为 负数
        if (numerator < 0 ^ denominator < 0) {
            fraction.append("-");
        }
        // 转成 Long
        Long dividend = Math.abs(Long.valueOf(numerator));
        Long divisor = Math.abs(Long.valueOf(denominator));
        fraction.append(String.valueOf(dividend / divisor));
        long remainder = dividend % divisor;
        if (remainder == 0) {
            return fraction.toString();
        }
        fraction.append(".");
        Map<Long, Integer> map = new HashMap<>();
        while (remainder != 0) {
            if (map.containsKey(remainder)) {
                fraction.insert(map.get(remainder), "(");
                fraction.append(")");
                break;
            }
            map.put(remainder, fraction.length());
            remainder *= 10;
            fraction.append(String.valueOf(remainder / divisor));
            remainder %= divisor;
        }
        return fraction.toString();
    }

    @Test
    public void isPalindrome() {
        int x = -121;
        boolean result = isPalindrome(x);
        log.debug("result:{}", result);
    }

    /**
     * 回文数 判断一个整数是否是回文数。回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。
     *
     * <p>示例 1:
     *
     * <p>输入: 121 输出: true 示例 2:
     *
     * <p>输入: -121 输出: false 解释: 从左向右读, 为 -121 。 从右向左读, 为 121- 。因此它不是一个回文数。 示例 3:
     *
     * <p>输入: 10 输出: false 解释: 从右向左读, 为 01 。因此它不是一个回文数。 进阶:
     *
     * <p>你能不将整数转为字符串来解决这个问题吗？
     *
     * @param x
     * @return
     */
    public boolean isPalindrome(int x) {
        // 负数 肯定不是回文数
        if (x < 0) {
            return false;
        }
        // 小于10 一定是回文数
        if (x < 10) {
            return true;
        }
        // 末位是0 一旦不是回文
        if (x % 10 == 0) {
            return false;
        }
        // 不将整数转为字符串
        List<Integer> list = new ArrayList<>();
        while (x > 0) {
            list.add(x % 10);
            x = x / 10;
        }
        log.debug("list:{}", list);
        int i = 0, j = list.size() - 1;
        while (i < j) {
            if (!Objects.equals(list.get(i), list.get(j))) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }

    @Test
    public void isPowerOfTwo() {
        int n = 255;
        boolean result = isPowerOfTwo(n);
        log.debug("result:{}", result);
    }

    /**
     * 2的幂 给定一个整数，编写一个函数来判断它是否是 2 的幂次方。
     *
     * <p>示例 1:
     *
     * <p>输入: 1 输出: true 解释: 2^0 = 1 示例 2:
     *
     * <p>输入: 16 输出: true 解释: 2^4 = 16 示例 3:
     *
     * <p>输入: 218 输出: false
     *
     * @param n
     * @return
     */
    public boolean isPowerOfTwo(int n) {
        // 负数肯定不是 2 的幂次方
        if (n <= 0) {
            return false;
        }
        // 通过位运算, 2 的幂次方 转为二进制 只有一个 1
        // 2^n - 1 和 2^n 刚好互补 011 100
        return (n & (n - 1)) == 0;
        /*int count = 0;
        int i = 0;
        while (i < 31) {
            if ((n & 1) == 1) {
                count++;
            }
            if (count > 1) {
                return false;
            }
            n = n >> 1;
            i++;
        }
        return count == 1;*/
    }

    @Test
    public void canWinNim() {
        int n = 1348820612;
        boolean result = canWinNim(n);
        log.debug("result:{}", result);
    }

    /**
     * Nim 游戏 你和你的朋友，两个人一起玩 Nim 游戏：桌子上有一堆石头，每次你们轮流拿掉 1 - 3 块石头。 拿掉最后一块石头的人就是获胜者。你作为先手。
     *
     * <p>你们是聪明人，每一步都是最优解。 编写一个函数，来判断你是否可以在给定石头数量的情况下赢得游戏。
     *
     * <p>示例:
     *
     * <p>输入: 4 输出: false 解释: 如果堆中有 4 块石头，那么你永远不会赢得比赛； 因为无论你拿走 1 块、2 块 还是 3 块石头，最后一块石头总是会被你的朋友拿走。
     *
     * @param n
     * @return
     */
    public boolean canWinNim(int n) {

        return (n % 4 != 0);
    }

    private boolean canWinNim(int n, Boolean[] winArray) {
        Boolean bWin = winArray[n];
        if (bWin != null) {
            return bWin;
        }
        // 判断对手能不能赢
        boolean a = canWinNim(n - 3, winArray);
        if (!a) { // 对手 输了, 说明我们能赢
            winArray[n] = true;
            return true;
        }
        boolean b = canWinNim(n - 2, winArray);
        if (!b) { // 对手 输了, 说明我们能赢
            winArray[n] = true;
            return true;
        }
        boolean c = canWinNim(n - 1, winArray);
        if (!c) { // 对手 输了, 说明我们能赢
            winArray[n] = true;
            return true;
        }
        winArray[n] = false;
        return false;
    }

    @Test
    public void superEggDrop() {
        int K = 2;
        int N = 7;
        int result = superEggDrop(K, N);
        log.debug("result:{}", result);
    }

    /**
     * 鸡蛋掉落 你将获得 K 个鸡蛋，并可以使用一栋从 1 到 N 共有 N 层楼的建筑。
     *
     * <p>每个蛋的功能都是一样的，如果一个蛋碎了，你就不能再把它掉下去。
     *
     * <p>你知道存在楼层 F ，满足 0 <= F <= N 任何从高于 F 的楼层落下的鸡蛋都会碎，从 F 楼层或比它低的楼层落下的鸡蛋都不会破。
     *
     * <p>每次移动，你可以取一个鸡蛋（如果你有完整的鸡蛋）并把它从任一楼层 X 扔下（满足 1 <= X <= N）。
     *
     * <p>你的目标是确切地知道 F 的值是多少。
     *
     * <p>无论 F 的初始值如何，你确定 F 的值的最小移动次数是多少？
     *
     * <p>示例 1：
     *
     * <p>输入：K = 1, N = 2 输出：2 解释： 鸡蛋从 1 楼掉落。如果它碎了，我们肯定知道 F = 0 。 否则，鸡蛋从 2 楼掉落。如果它碎了，我们肯定知道 F = 1 。
     * 如果它没碎，那么我们肯定知道 F = 2 。 因此，在最坏的情况下我们需要移动 2 次以确定 F 是多少。 示例 2：
     *
     * <p>输入：K = 2, N = 6 输出：3 示例 3：
     *
     * <p>输入：K = 3, N = 14 输出：4
     *
     * <p>提示：
     *
     * <p>1 <= K <= 100 1 <= N <= 10000
     *
     * @param K
     * @param N
     * @return
     */
    public int superEggDrop(int K, int N) {
        /*if (K == 1) {
            return N;
        }

        return engDrop(K,1 ,N) ;
        int lo = 1, hi = N;
        // 答案在 1 ~ N 之间, 用二分法 找到最小的答案
        while (lo < hi) {
            int mi = (lo + hi) / 2;
            if (f(mi, K, N) < N)
                lo = mi + 1;
            else
                hi = mi;
        }

        return lo;*/
        // 不能简单的使用二分, 因为鸡蛋会碎, 需要根据鸡蛋的个数分

        int[] dp = new int[N + 1];
        for (int i = 0; i <= N; ++i) dp[i] = i;

        for (int k = 2; k <= K; ++k) {
            // Now, we will develop dp2[i] = dp(k, i)
            int[] dp2 = new int[N + 1];
            int x = 1;
            for (int n = 1; n <= N; ++n) {
                // Let's find dp2[n] = dp(k, n)
                // Increase our optimal x while we can make our answer better.
                // Notice max(dp[x-1], dp2[n-x]) > max(dp[x], dp2[n-x-1])
                // is simply max(T1(x-1), T2(x-1)) > max(T1(x), T2(x)).
                while (x < n && Math.max(dp[x - 1], dp2[n - x]) > Math.max(dp[x], dp2[n - x - 1]))
                    x++;

                // The final answer happens at this x.
                dp2[n] = 1 + Math.max(dp[x - 1], dp2[n - x]);
            }

            dp = dp2;
        }

        return dp[N];
        /*if (K == 1) {
            return N;
        }
        if (N == 1) {
            return 1;
        }
        int[] dp = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            dp[i] = i;
        }
        // f (K,N)  = Max{f(K,x - 1),f(K - 1,N -x )} + 1 | x= 1~ N
        for (int k = 1; k <= K ; k++) {
            int[] dp2 = new int[N+1];
            int x = 1;
            for (int n = 1; n <= N; n++) {
                while (x < n && Math.max(dp[x-1], dp2[n-x]) > Math.max(dp[x], dp2[n-x-1]))
                    x++;
                // x-1 是上一层
                dp2[n] = 1 + Math.max(dp[x-1], dp2[n-x]);
            }
            dp = dp2;
        }

        return  dp[N];*/
    }

    /*public int f(int x, int K, int N) {
        int ans = 0, r = 1;
        for (int i = 1; i <= K; ++i) {
            r *= x - i + 1;
            r /= i;
            ans += r;
            if (ans >= N) break;
        }
        return ans;
    }*/

    @Test
    public void judgePoint24() {
        int[] nums = {1, 2, 1, 2};
        boolean result = judgePoint24(nums);
        logResult(result);
    }
    /**
     * 24 点游戏 你有 4 张写有 1 到 9 数字的牌。你需要判断是否能通过 *，/，+，-，(，) 的运算得到 24。
     *
     * <p>示例 1:
     *
     * <p>输入: [4, 1, 8, 7] 输出: True 解释: (8-4) * (7-1) = 24 示例 2:
     *
     * <p>输入: [1, 2, 1, 2] 输出: False 注意:
     *
     * <p>除法运算符 / 表示实数除法，而不是整数除法。例如 4 / (1 - 2/3) = 12 。 每个运算符对两个数进行运算。特别是我们不能用 - 作为一元运算符。例如，[1, 1,
     * 1, 1] 作为输入时，表达式 -1 - 1 - 1 - 1 是不允许的。 你不能将数字连接在一起。例如，输入为 [1, 2, 1, 2] 时，不能写成 12 + 12 。
     *
     * @param nums
     * @return
     */
    public boolean judgePoint24(int[] nums) {
        List<Double> list = new ArrayList<>();
        for (int num : nums) {
            list.add((double) num);
        }

        return calculate(list);
    }

    private boolean calculate(List<Double> list) {
        if (list.isEmpty()) {
            return false;
        }
        if (list.size() == 1) {
            return isAnswer(list.get(0));
        }
        int len = list.size();
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                // 把剩下的  数字 加入 nums2
                List<Double> nums2 = new ArrayList<Double>();
                for (int k = 0; k < len; k++) {
                    if (k != i && k != j) {
                        nums2.add(list.get(k));
                    }
                }
                // + - * /
                for (int m = 0; m < 6; m++) {
                    switch (m) {
                        case 0:
                            nums2.add(list.get(i) + list.get(j));
                            break;
                        case 1:
                            nums2.add(list.get(i) * list.get(j));
                            break;
                            // 减法 i 和 j 需要互换位置
                        case 2:
                            nums2.add(list.get(i) - list.get(j));
                            break;
                        case 3:
                            nums2.add(list.get(j) - list.get(i));
                            break;
                            // 除法 i 和 j 需要互换位置
                        case 4:
                            {
                                if (list.get(j) == 0) {
                                    continue;
                                }
                                nums2.add(list.get(i) / list.get(j));
                            }
                            break;
                        case 5:
                            {
                                if (list.get(i) == 0) {
                                    continue;
                                }
                                nums2.add(list.get(j) / list.get(i));
                            }
                            break;
                    }
                    if (calculate(nums2)) {
                        return true;
                    }
                    nums2.remove(nums2.size() - 1);
                }
            }
        }

        return false;
    }

    private boolean isAnswer(Double num) {
        return Math.abs(num - 24.0) < 1e-6;
    }

    @Test
    public void kInversePairs() {
        int n = 1000, k = 1000;
        logResult(kInversePairs(n, k));
    }
    /**
     * K个逆序对数组 给出两个整数 n 和 k，找出所有包含从 1 到 n 的数字，且恰好拥有 k 个逆序对的不同的数组的个数。
     *
     * <p>逆序对的定义如下：对于数组的第i个和第 j个元素，如果满i < j且 a[i] > a[j]，则其为一个逆序对；否则不是。
     *
     * <p>由于答案可能很大，只需要返回 答案 mod 109 + 7 的值。
     *
     * <p>示例 1:
     *
     * <p>输入: n = 3, k = 0 输出: 1 解释: 只有数组 [1,2,3] 包含了从1到3的整数并且正好拥有 0 个逆序对。 示例 2:
     *
     * <p>输入: n = 3, k = 1 输出: 2 解释: 数组 [1,3,2] 和 [2,1,3] 都有 1 个逆序对。 说明:
     *
     * <p>n 的范围是 [1, 1000] 并且 k 的范围是 [0, 1000]。
     *
     * @param n
     * @param k
     * @return
     */
    public int kInversePairs(int n, int k) {
        // 我们用 f(i, j) 表示数字 [1 .. i] 的排列中恰好包含 j 个逆序对的个数。
        // f(i, j) = f(i - 1, j) + f(i - 1, j - 1) + ... + f(i - 1, j - i + 1)
        // f(i, j-1) = f(i - 1, j - 1) + f(i - 1, j - 2) + ... + f(i - 1, j - i)

        // 由上面2个公式 左右相减
        // ==> f(i, j) - f(i, j - 1) = f(i - 1, j) - f(i - 1, j - i)
        // ==> f(i, j) = f(i, j - 1) + f(i - 1, j) - f(i - 1, j - i)

        // 边界条件为:
        // f(i, j0) = 0 if j0 < 0
        // f(0, 0) = 1
        int[][] dp = new int[n + 1][k + 1];
        int M = 1000000007;

        int result = 0;
        // n 个数 取两个数的组合 最多有 n * (n - 1) / 2 个对, n个元素最多只有n * (n - 1) / 2个逆序对
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= k && j <= i * (i - 1) / 2; j++) {
                if (j == 0) {
                    dp[i][j] = 1;
                } else {
                    // dp[i][j] = dp[i][j - 1] + dp[i - 1][j] - dp[i - 1][j - i];
                    int val = (dp[i - 1][j] + M - ((j - i) >= 0 ? dp[i - 1][j - i] : 0)) % M;
                    dp[i][j] = (dp[i][j - 1] + val) % M;
                }
            }
        }

        return dp[n][k];
        /*int[][] dp = new int[n + 1][k + 1];
        int M = 1000000007;
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= k && j <= i * (i - 1) / 2; j++) {
                if (i == 1 && j == 0) {
                    dp[i][j] = 1;
                    break;
                } else if (j == 0)
                    dp[i][j] = 1;
                else {
                    int val = (dp[i - 1][j] + M - ((j - i) >= 0 ? dp[i - 1][j - i] : 0)) % M;
                    dp[i][j] = (dp[i][j - 1] + val) % M;
                }
            }
        }
        return dp[n][k]; */
    }

    public void removeBoxes() {
        int[] boxes = {1, 3, 2, 2, 2, 3, 4, 3, 1};
        logResult(removeBoxes(boxes));
    }

    /**
     * 546. 移除盒子
     *
     * <p>给出一些不同颜色的盒子，盒子的颜色由数字表示，即不同的数字表示不同的颜色。 你将经过若干轮操作去去掉盒子，直到所有的盒子都去掉为止。每一轮你可以移除具有相同颜色的连续 k
     * 个盒子（k >= 1），这样一轮之后你将得到 k*k 个积分。 当你将所有盒子都去掉之后，求你能获得的最大积分和。
     *
     * <p>示例 1： 输入:
     *
     * <p>[1, 3, 2, 2, 2, 3, 4, 3, 1] 输出:
     *
     * <p>23 解释:
     *
     * <p>[1, 3, 2, 2, 2, 3, 4, 3, 1] ----> [1, 3, 3, 4, 3, 1] (3*3=9 分) ----> [1, 3, 3, 3, 1]
     * (1*1=1 分) ----> [1, 1] (3*3=9 分) ----> [] (2*2=4 分)
     *
     * <p>提示：盒子的总数 n 不会超过 100。
     *
     * @param boxes
     * @return
     */
    public int removeBoxes(int[] boxes) {
        int max = 0;
        int len = boxes.length;
        // dp[i][j][k]代表的是区间段[i, j]（在i前有k个元素与boxes[i]相同的数字）消除可得的最大值
        int[][][] dp = new int[100][100][100];

        for (int i = 0; i < len; i++) {}

        return max;
    }

    @Test
    public void addDigits() {
        int num = 38;
        logResult(addDigits(num));
    }
    /**
     * 258. 各位相加 给定一个非负整数 num，反复将各个位上的数字相加，直到结果为一位数。
     *
     * <p>示例:
     *
     * <p>输入: 38 输出: 2 解释: 各位相加的过程为：3 + 8 = 11, 1 + 1 = 2。 由于 2 是一位数，所以返回 2。 进阶: 你可以不使用循环或者递归，且在
     * O(1) 时间复杂度内解决这个问题吗？
     *
     * @param num
     * @return
     */
    public int addDigits(int num) {

        while (num >= 10) {
            num = getSum(num);
        }
        return num;
    }

    private int addDigits2(int num) {
        // 找到规律，多次各位相加的结果相当于原数num对9取模，其中考虑到“0”和“9的倍数”这两种特殊情况即可。
        int result = num % 9;
        if (result == 0 && num != 0) {
            result = 9;
        }
        return result;
    }

    private int getSum(int num) {
        int result = 0;
        while (num > 0) {
            result += num % 10;
            num /= 10;
        }
        return result;
    }

    @Test
    public void isUgly() {
        int num = 14;
        logResult(isUgly(num));
    }

    /**
     * 263. 丑数 编写一个程序判断给定的数是否为丑数。
     *
     * <p>丑数就是只包含质因数 2, 3, 5 的正整数。
     *
     * <p>示例 1:
     *
     * <p>输入: 6 输出: true 解释: 6 = 2 × 3 示例 2:
     *
     * <p>输入: 8 输出: true 解释: 8 = 2 × 2 × 2 示例 3:
     *
     * <p>输入: 14 输出: false 解释: 14 不是丑数，因为它包含了另外一个质因数 7。 说明：
     *
     * <p>1 是丑数。 输入不会超过 32 位有符号整数的范围: [−231, 231 − 1]。
     *
     * @param num
     * @return
     */
    public boolean isUgly(int num) {
        if (num == 1) {
            return true;
        }
        if (num == 0) {
            return false;
        }
        if (num == 2 || num == 3 || num == 5) {
            return true;
        }
        // 2
        if ((num & 1) == 0 && isUgly(num >> 1)) {
            return true;
        }
        // 3
        if (num % 3 == 0 && isUgly(num / 3)) {
            return true;
        }
        // 5
        if (num % 5 == 0 && isUgly(num / 5)) {
            return true;
        }

        return false;
    }

    @Test
    public void isPowerOfFour() {
        int num = 16;
        logResult(isPowerOfFour(num));
    }
    /**
     * 342. 4的幂 给定一个整数 (32 位有符号整数)，请编写一个函数来判断它是否是 4 的幂次方。
     *
     * <p>示例 1:
     *
     * <p>输入: 16 输出: true 示例 2:
     *
     * <p>输入: 5 输出: false 进阶： 你能不使用循环或者递归来完成本题吗？
     *
     * <p>通过次数18,992提交次数39,665
     *
     * @param num
     * @return
     */
    public boolean isPowerOfFour(int num) {
        while (num >= 4) {
            if (num % 4 != 0) {
                return false;
            }
            num /= 4;
        }
        if (num == 1) {
            return true;
        }
        return false;
    }

    @Test
    public void toHex() {
        int num = 265;
        logResult(toHex(num));
    }

    /**
     * 数字转换为十六进制数 给定一个整数，编写一个算法将这个数转换为十六进制数。对于负整数，我们通常使用 补码运算 方法。
     *
     * <p>注意:
     *
     * <p>十六进制中所有字母(a-f)都必须是小写。
     * 十六进制字符串中不能包含多余的前导零。如果要转化的数为0，那么以单个字符'0'来表示；对于其他情况，十六进制字符串中的第一个字符将不会是0字符。 给定的数确保在32位有符号整数范围内。
     * 不能使用任何由库提供的将数字直接转换或格式化为十六进制的方法。 示例 1：
     *
     * <p>输入: 26
     *
     * <p>输出: "1a" 示例 2：
     *
     * <p>输入: -1
     *
     * <p>输出: "ffffffff"
     *
     * @param num
     * @return
     */
    public String toHex(int num) {
        char[] hexArr = {
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'
        };
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            sb.insert(0, hexArr[num & 15]);
            num = num >>> 4;
        }
        int start = sb.length() - 1;
        for (int i = 0; i < sb.length(); i++) {
            if (sb.charAt(i) != '0') {
                start = i;
                break;
            }
        }
        return sb.substring(start);
        /*if (num == 0) {
            return "0";
        }
        StringBuilder sb = new StringBuilder();
        char[] chars = {'a','b','c','d','e','f'};

        // 思路： 利用位运算 求 num 的 二进制  然后二进制转16进制（4位1组）
        StringBuilder sb2 = new StringBuilder();
        for (int i = 0; i < 32; i++) {
            // 最后1位 0 还是 1
            sb2.insert(0,num&1);
            // 右移一位
            num = num>>1;
        }
        // 二进制转16进制
        for (int i = 0; i < 8 ; i++) {
            // 计算 4 位的 二进制数
            int start = 4 * i;
            int n = 0;
            for (int j = 0; j < 4; j++) {
                n += (sb2.charAt(start+j) - '0')<<(3 - j);
            }
            if (n > 9) {
                sb.append(chars[n - 10]);
            } else if (n > 0 || sb.length() > 0) {
                sb.append(n);
            }
        }

        // 如果是负数, 需要算补码
        */
        /*if () {

        } */
        /*

        return sb.toString();*/
    }

    @Test
    public void arrangeCoins() {
        int n = 1804289383;
        logResult(arrangeCoins(n));
    }
    /**
     * 441. 排列硬币 你总共有 n 枚硬币，你需要将它们摆成一个阶梯形状，第 k 行就必须正好有 k 枚硬币。
     *
     * <p>给定一个数字 n，找出可形成完整阶梯行的总行数。
     *
     * <p>n 是一个非负整数，并且在32位有符号整型的范围内。
     *
     * <p>示例 1:
     *
     * <p>n = 5
     *
     * <p>硬币可排列成以下几行: ¤ ¤ ¤ ¤ ¤
     *
     * <p>因为第三行不完整，所以返回2. 示例 2:
     *
     * <p>n = 8
     *
     * <p>硬币可排列成以下几行: ¤ ¤ ¤ ¤ ¤ ¤ ¤ ¤
     *
     * <p>因为第四行不完整，所以返回3.
     *
     * @param n
     * @return
     */
    public int arrangeCoins(int n) {
        int result = 0;

        // f(1) = 1; f(2) = 3; f(3) = 6 ... f(k) = (k+1)*k/2
        double d = Math.sqrt((double) 2 * n);

        long k = (int) Math.floor(d);
        log.debug("k:{}", k);

        long sum = k * (k + 1) / 2;
        while (sum > n) {
            k--;
            sum = k * (k + 1) / 2;
        }

        return (int) k;
    }

    @Test
    public void distributeCandies() {
        int candies = 7, num_people = 3;
        logResult(distributeCandies(candies, num_people));
    }
    /**
     * 1103. 分糖果 II 排排坐，分糖果。
     *
     * <p>我们买了一些糖果 candies，打算把它们分给排好队的 n = num_people 个小朋友。
     *
     * <p>给第一个小朋友 1 颗糖果，第二个小朋友 2 颗，依此类推，直到给最后一个小朋友 n 颗糖果。
     *
     * <p>然后，我们再回到队伍的起点，给第一个小朋友 n + 1 颗糖果，第二个小朋友 n + 2 颗，依此类推，直到给最后一个小朋友 2 * n 颗糖果。
     *
     * <p>重复上述过程（每次都比上一次多给出一颗糖果，当到达队伍终点后再次从队伍起点开始），直到我们分完所有的糖果。注意，就算我们手中的剩下糖果数不够（不比前一次发出的糖果多），这些糖果也会全部发给当前的小朋友。
     *
     * <p>返回一个长度为 num_people、元素之和为 candies 的数组，以表示糖果的最终分发情况（即 ans[i] 表示第 i 个小朋友分到的糖果数）。
     *
     * <p>示例 1：
     *
     * <p>输入：candies = 7, num_people = 4 输出：[1,2,3,1] 解释： 第一次，ans[0] += 1，数组变为 [1,0,0,0]。 第二次，ans[1]
     * += 2，数组变为 [1,2,0,0]。 第三次，ans[2] += 3，数组变为 [1,2,3,0]。 第四次，ans[3] += 1（因为此时只剩下 1 颗糖果），最终数组变为
     * [1,2,3,1]。 示例 2：
     *
     * <p>输入：candies = 10, num_people = 3 输出：[5,2,3] 解释： 第一次，ans[0] += 1，数组变为 [1,0,0]。 第二次，ans[1] +=
     * 2，数组变为 [1,2,0]。 第三次，ans[2] += 3，数组变为 [1,2,3]。 第四次，ans[0] += 4，最终数组变为 [5,2,3]。
     *
     * @param candies
     * @param num_people
     * @return
     */
    public int[] distributeCandies(int candies, int num_people) {
        int[] nums = new int[num_people];

        int index = 0;
        int num = 1;
        while (candies > 0) {
            if (index == num_people) {
                index = 0;
            }
            if (num <= candies) {
                nums[index] += num;
                candies -= num;
            } else {
                nums[index] += candies;
                candies = 0;
            }
            num++;
            index++;
        }

        return nums;
    }

    @Test
    public void selfDividingNumbers() {
        int left = 1, right = 22;
        LogUtils.logResult(selfDividingNumbers(left, right));
    }
    /**
     * 728. 自除数 自除数 是指可以被它包含的每一位数除尽的数。
     *
     * <p>例如，128 是一个自除数，因为 128 % 1 == 0，128 % 2 == 0，128 % 8 == 0。
     *
     * <p>还有，自除数不允许包含 0 。
     *
     * <p>给定上边界和下边界数字，输出一个列表，列表的元素是边界（含边界）内所有的自除数。
     *
     * <p>示例 1：
     *
     * <p>输入： 上边界left = 1, 下边界right = 22 输出： [1, 2, 3, 4, 5, 6, 7, 8, 9, 11, 12, 15, 22] 注意：
     *
     * <p>每个输入参数的边界满足 1 <= left <= right <= 10000。
     *
     * @param left
     * @param right
     * @return
     */
    public List<Integer> selfDividingNumbers(int left, int right) {
        List<Integer> list = new ArrayList<>();
        for (int i = left; i <= right; i++) {
            if (dividingNumber(i)) {
                list.add(i);
            }
        }

        return list;
    }

    private boolean dividingNumber(int num) {
        int original = num;
        int divideNum = 0;
        while (num != 0) {
            if ((divideNum = num % 10) == 0 || original % divideNum != 0) {
                return false;
            }
            num = num / 10;
        }
        return true;
    }

    @Test
    public void dayOfYear() {
        String date = "2016-11-19";
        logResult(dayOfYear(date));
    }

    @Test
    public void testYear() {
        int[] year = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

        for (int i = 1; i < year.length; i++) {
            year[i] += year[i - 1];
        }
        log.debug("year:{}", year);
    }

    /**
     * 1154. 一年中的第几天 给你一个按 YYYY-MM-DD 格式表示日期的字符串 date，请你计算并返回该日期是当年的第几天。
     *
     * <p>通常情况下，我们认为 1 月 1 日是每年的第 1 天，1 月 2 日是每年的第 2 天，依此类推。每个月的天数与现行公元纪年法（格里高利历）一致。
     *
     * <p>示例 1：
     *
     * <p>输入：date = "2019-01-09" 输出：9 示例 2：
     *
     * <p>输入：date = "2019-02-10" 输出：41 示例 3：
     *
     * <p>输入：date = "2003-03-01" 输出：60 示例 4：
     *
     * <p>输入：date = "2004-03-01" 输出：61
     *
     * <p>提示：
     *
     * <p>date.length == 10 date[4] == date[7] == '-'，其他的 date[i] 都是数字。 date 表示的范围从 1900 年 1 月 1 日至
     * 2019 年 12 月 31 日。
     *
     * @param date
     * @return
     */
    public int dayOfYear(String date) {
        int year = Integer.valueOf(date.substring(0, 4));
        int mouth = Integer.valueOf(date.substring(5, 7));
        int day = Integer.valueOf(date.substring(8, 10));
        int[] days = {0, 31, 59, 90, 120, 151, 181, 212, 243, 273, 304, 334};
        int result = 0;
        result += days[mouth - 1];
        result += day;
        if (isLeapYear(year) && mouth > 2) {
            result += 1;
        }
        return result;
    }

    private boolean isLeapYear(int year) {
        boolean b1 = year % 100 != 0 && year % 4 == 0;
        boolean b2 = year % 400 == 0;
        return b1 | b2;
    }

    @Test
    public void dayOfTheWeek() {
        int day = 29, month = 2, year = 2016;
        logResult(dayOfTheWeek(day, month, year));
    }
    /**
     * 1185. 一周中的第几天 给你一个日期，请你设计一个算法来判断它是对应一周中的哪一天。
     *
     * <p>输入为三个整数：day、month 和 year，分别表示日、月、年。
     *
     * <p>您返回的结果必须是这几个值中的一个 {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday",
     * "Saturday"}。
     *
     * <p>示例 1：
     *
     * <p>输入：day = 31, month = 8, year = 2019 输出："Saturday" 示例 2：
     *
     * <p>输入：day = 18, month = 7, year = 1999 输出："Sunday" 示例 3：
     *
     * <p>输入：day = 15, month = 8, year = 1993 输出："Sunday"
     *
     * @param day
     * @param month
     * @param year
     * @return
     */
    public String dayOfTheWeek(int day, int month, int year) {
        // w = (d + 1+ 2*m+3*(m+1)/5+y+y/4-y/100+y/400)%7;
        if (month == 1 || month == 2) {
            month += 12;
            year--;
        }
        int w =
                (day
                                + 1
                                + 2 * month
                                + 3 * (month + 1) / 5
                                + year
                                + year / 4
                                - year / 100
                                + year / 400)
                        % 7;
        logResult(w);
        String[] weeks = {
            "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"
        };
        return weeks[w];
    }

    @Test
    public void numPrimeArrangements() {
        int n = 100;
        logResult(numPrimeArrangements(n));
    }

    /**
     * 1175. 质数排列 请你帮忙给从 1 到 n 的数设计排列方案，使得所有的「质数」都应该被放在「质数索引」（索引从 1 开始）上；你需要返回可能的方案总数。
     *
     * <p>让我们一起来回顾一下「质数」：质数一定是大于 1 的，并且不能用两个小于它的正整数的乘积来表示。
     *
     * <p>由于答案可能会很大，所以请你返回答案 模 mod 10^9 + 7 之后的结果即可。
     *
     * <p>示例 1：
     *
     * <p>输入：n = 5 输出：12 解释：举个例子，[1,2,5,4,3] 是一个有效的排列，但 [5,2,3,4,1] 不是，因为在第二种情况里质数 5 被错误地放在索引为 1
     * 的位置上。 示例 2：
     *
     * <p>输入：n = 100 输出：682289015
     *
     * <p>提示：
     *
     * <p>1 <= n <= 100
     *
     * @param n
     * @return
     */
    public int numPrimeArrangements(int n) {
        /*if (n < 3)
            return 1;

        int count = 0;
        boolean[] nums = new boolean[n + 1];
        for (int i = 2; i * i < nums.length; i++) {
            if (!nums[i]) {
                for (int j = i * i; j < nums.length; j += i) {
                    if (nums[j])
                        continue;
                    nums[j] = true;
                    count++;
                }
            }
        }
        // 非质数的数量(加1：元素1不包含在内)
        count++;

        // 8以内的质数个数多于非质数
        if (n < 8)
            count = n - count;

        // 结果
        long res = 1;
        for (int i = 2; i <= count; i++) {
            res = (res * i) % 1000000007;
            // 这里判断会减少一轮遍历
            if (i == n - count) {
                res = (res * res) % 1000000007;
            }
        }
        return (int) res;  */
        if (n < 3) return 1;
        int count = 0;
        boolean[] nums = new boolean[n + 1];
        for (int i = 2; i * i < nums.length; i++) {
            if (!nums[i]) {
                for (int j = i * i; j < nums.length; j += i) {
                    if (nums[j]) continue;
                    nums[j] = true;
                    count++;
                }
            }
        }
        // 非质数的数量(加1：元素1不包含在内)
        count++;
        int MOD = 1000000007;

        long num = 1;
        for (int i = 1; i <= count; i++) {
            num = (num * i) % MOD;
        }
        for (int i = 1; i <= n - count; i++) {
            num = (num * i) % MOD;
        }
        return (int) num;
    }

    private long getFactorial(int n) {
        int MOD = 1000000007;
        long num = 1;
        for (int i = 2; i <= n; i++) {
            num = (num % MOD) * (i % MOD);
            log.debug("{}:{}", i, num);
        }
        return num;
    }

    @Test
    public void checkStraightLine() {
        int[][] coordinates = {{1, 2}, {2, 3}, {3, 4}, {4, 5}, {5, 6}, {6, 7}};
        logResult(checkStraightLine(coordinates));
    }

    /**
     * 1232. 缀点成线 在一个 XY 坐标系中有一些点，我们用数组 coordinates 来分别记录它们的坐标，其中 coordinates[i] = [x, y] 表示横坐标为
     * x、纵坐标为 y 的点。
     *
     * <p>请你来判断，这些点是否在该坐标系中属于同一条直线上，是则返回 true，否则请返回 false。
     *
     * <p>示例 1：
     *
     * <p>输入：coordinates = [[1,2],[2,3],[3,4],[4,5],[5,6],[6,7]] 输出：true 示例 2：
     *
     * <p>输入：coordinates = [[1,1],[2,2],[3,4],[4,5],[5,6],[7,7]] 输出：false
     *
     * <p>提示：
     *
     * <p>2 <= coordinates.length <= 1000 coordinates[i].length == 2 -10^4 <= coordinates[i][0],
     * coordinates[i][1] <= 10^4 coordinates 中不含重复的点 通过次数5,119提交次数10,136
     *
     * @param coordinates
     * @return
     */
    public boolean checkStraightLine(int[][] coordinates) {
        int len = coordinates.length;
        if (len <= 2) {
            return true;
        }
        int[] point1 = coordinates[0];
        int[] point2 = coordinates[1];

        for (int i = 2; i < len; i++) {

            if (!inLine(point1, point2, coordinates[i])) {
                return false;
            }
        }

        return true;
    }

    /**
     * 判断三点是否共线
     *
     * @return
     */
    private boolean inLine(int[] point1, int[] point2, int[] point3) {
        int x1 = point1[0], y1 = point1[1];
        int x2 = point2[0], y2 = point2[1];
        int x3 = point3[0], y3 = point3[1];
        // y2 - y1 / x2 - x1 = y3 - y1 / x3 - x1 说明三点共线
        // ==> (y2 - y1) *(x3 - x1) = (y3 - y1) *(x2 - x1)

        if ((y2 - y1) * (x3 - x1) == (y3 - y1) * (x2 - x1)) {
            return true;
        }

        return false;
    }

    @Test
    public void isRectangleOverlap() {
        int[] rec1 = {0, 0, 1, 1};
        int[] rec2 = {1, 0, 2, 1};
        logResult(isRectangleOverlap(rec1, rec2));
    }

    /**
     * 836. 矩形重叠 矩形以列表 [x1, y1, x2, y2] 的形式表示，其中 (x1, y1) 为左下角的坐标，(x2, y2) 是右上角的坐标。
     *
     * <p>如果相交的面积为正，则称两矩形重叠。需要明确的是，只在角或边接触的两个矩形不构成重叠。
     *
     * <p>给出两个矩形，判断它们是否重叠并返回结果。
     *
     * <p>示例 1：
     *
     * <p>输入：rec1 = [0,0,2,2], rec2 = [1,1,3,3] 输出：true 示例 2：
     *
     * <p>输入：rec1 = [0,0,1,1], rec2 = [1,0,2,1] 输出：false 说明：
     *
     * <p>两个矩形 rec1 和 rec2 都以含有四个整数的列表的形式给出。 矩形中的所有坐标都处于 -10^9 和 10^9 之间。
     *
     * @param rec1
     * @param rec2
     * @return
     */
    public boolean isRectangleOverlap(int[] rec1, int[] rec2) {
        // 只要rec1有一个点在rec2内部即可
        int x1 = rec1[0], y1 = rec1[1], x2 = rec1[2], y2 = rec1[3];
        if (inRectangle(rec2, x1, y1)) {
            return true;
        }
        if (inRectangle(rec2, x1, y2)) {
            return true;
        }
        if (inRectangle(rec2, x2, y1)) {
            return true;
        }
        if (inRectangle(rec2, x2, y2)) {
            return true;
        }

        return false;
    }

    private boolean inRectangle(int[] rec, int x, int y) {
        boolean b1 = false;
        boolean b2 = false;
        if (rec[0] < x && x < rec[2]) {
            b1 = true;
        }
        if (rec[1] < y && y < rec[3]) {
            b2 = true;
        }
        return b1 & b2;
    }

    @Test
    public void countPrimeSetBits() {
        int L = 10, R = 15;
        logResult(countPrimeSetBits(L, R));
    }

    /**
     * 762. 二进制表示中质数个计算置位 给定两个整数 L 和 R ，找到闭区间 [L, R] 范围内，计算置位位数为质数的整数个数。
     *
     * <p>（注意，计算置位代表二进制表示中1的个数。例如 21 的二进制表示 10101 有 3 个计算置位。还有，1 不是质数。）
     *
     * <p>示例 1:
     *
     * <p>输入: L = 6, R = 10 输出: 4 解释: 6 -> 110 (2 个计算置位，2 是质数) 7 -> 111 (3 个计算置位，3 是质数) 9 -> 1001 (2
     * 个计算置位，2 是质数) 10-> 1010 (2 个计算置位，2 是质数) 示例 2:
     *
     * <p>输入: L = 10, R = 15 输出: 5 解释: 10 -> 1010 (2 个计算置位, 2 是质数) 11 -> 1011 (3 个计算置位, 3 是质数) 12 ->
     * 1100 (2 个计算置位, 2 是质数) 13 -> 1101 (3 个计算置位, 3 是质数) 14 -> 1110 (3 个计算置位, 3 是质数) 15 -> 1111 (4
     * 个计算置位, 4 不是质数) 注意:
     *
     * <p>L, R 是 L <= R 且在 [1, 10^6] 中的整数。 R - L 的最大值为 10000。
     *
     * @param L
     * @param R
     * @return
     */
    public int countPrimeSetBits(int L, int R) {
        int result = 0;
        int[] primes = {0, 0, 1, 1, 0, 1, 0, 1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 1, 0, 1, 0};
        for (int num = L; num <= R; num++) {
            int bit = Integer.bitCount(num);
            result += primes[bit];
        }
        return result;
    }

    @Test
    public void rotatedDigits() {
        int num = 165;
        logResult(rotatedDigits(num));
    }
    /**
     * 788. 旋转数字 我们称一个数 X 为好数, 如果它的每位数字逐个地被旋转 180 度后，我们仍可以得到一个有效的，且和 X 不同的数。要求每位数字都要被旋转。
     *
     * <p>如果一个数的每位数字被旋转以后仍然还是一个数字， 则这个数是有效的。0, 1, 和 8 被旋转后仍然是它们自己；2 和 5 可以互相旋转成对方； 6 和 9
     * 同理，除了这些以外其他的数字旋转以后都不再是有效的数字。
     *
     * <p>现在我们有一个正整数 N, 计算从 1 到 N 中有多少个数 X 是好数？
     *
     * <p>示例: 输入: 10 输出: 4 解释: 在[1, 10]中有四个好数： 2, 5, 6, 9。 注意 1 和 10 不是好数, 因为他们在旋转之后不变。 注意:
     *
     * <p>N 的取值范围是 [1, 10000]。
     *
     * @param N
     * @return
     */
    public int rotatedDigits(int N) {
        int result = 0;

        // 1 ~ 9 四个好数 2, 5, 6, 9
        // 10 ~ 19 四个好数 12, 15, 16, 19
        // 20 ~ 29 7个好数 22, 25, 26, 29   20, 21,28
        // 30 ~ 39 0
        // 40 ~ 49 0
        // 50 ~ 59 7
        // ..
        // 100 ~ 109 4
        /* int num = 1;
        // 从万位到个位
        // 万位
        int a = num / 1000;
        while (num <= N) {


        }*/
        // 0,1,8的个数
        /*int[] goodCounts1 = {1,2,2,2,2,2,2,2,3,3};
        // 2, 5, 6, 9的个数
        int[] goodCounts2 = {0,0,1,1,1,2,3,3,3,4};
        // 动态规划
        int[] nums1 = new int[5];
        int[] nums2 = new int[5];
        int index = 0;
        int result1 = 0;
        while (N > 0) {
            int num = N % 10;
            // 0,1,8的个数
            int count1 = goodCounts1[num];
            // 存在一个 2, 5, 6, 9的个数
            int count2 = goodCounts2[num];

            if (index > 0) {
                // 2, 5, 6, 9
                if (num == 2 || num ==  5 || num == 6 || num ==  9) {
                    count2--;
                    result += goodCounts1[num - 1] + goodCounts2[num - 1];
                } else if (num == 0 || num ==  1 || num == 8 ) {
                    count1--;
                    result += goodCounts2[num - 1];
                    result1 += goodCounts1[num - 1];
                }
                int a = 1;
                int b = 1;
                for (int i = 1; i < index;i++) {
                    a *= 7;
                    b *= 3;
                }

                result += count1 * 4 * a;
                result += count2 * 7 * a;

                result1 += count1 * 3 * b;

                nums1[index] = result1;
                nums2[index] = result;
            } else {
                nums1[index] = count1;
                nums2[index] = count2;
            }

            N /= 10;
            index++;
        }*/

        int count = 0;
        int[] dp = new int[N + 1];
        // 9以后的每个数n可以拆成a = n % 10（最后一位）和a = n / 10（前面r - 1位）
        // 若a和b中均不含有3、4、7且至少有一个含有2、5、6、9，那么n就是好数
        // dp数组存储3种值，0：不包含3、4、7的坏数，1：含有3、4、7的坏数，2：好数
        // 通过dp数组可以知晓a和b是否含有3、4、7或2、5、6、9，直接判断出n是否是好数

        for (int i = 1; i < 10; i++) {
            if (i > N) {
                break;
            }
            if (i == 3 || i == 4 || i == 7) {
                dp[i] = 1;
            } else if (i == 2 || i == 5 || i == 6 || i == 9) {
                dp[i] = 2;
                count++;
            }
        }

        for (int i = 10; i <= N; i++) {
            // 如果 个位i % 10 是 3、4、7 当前数字为坏数
            // 如果 前面的数字 i / 10 为坏数  当前数字为坏数
            if (dp[i % 10] == 1 || dp[i / 10] == 1) {
                dp[i] = 1;
            } else if (dp[i % 10] == 2 || dp[i / 10] == 2) {
                dp[i] = 2;
                count++;
            }
        }
        return count;

        // return result;
    }

    @Test
    public void canMeasureWater() {
        int x = 2, y = 6, z = 5;
        logResult(canMeasureWater(x, y, z));
    }

    /**
     * 365. 水壶问题 有两个容量分别为 x升 和 y升 的水壶以及无限多的水。请判断能否通过使用这两个水壶，从而可以得到恰好 z升 的水？
     *
     * <p>如果可以，最后请用以上水壶中的一或两个来盛放取得的 z升 水。
     *
     * <p>你允许：
     *
     * <p>装满任意一个水壶 清空任意一个水壶 从一个水壶向另外一个水壶倒水，直到装满或者倒空 示例 1: (From the famous "Die Hard" example)
     *
     * <p>输入: x = 3, y = 5, z = 4 输出: True 示例 2:
     *
     * <p>输入: x = 2, y = 6, z = 5 输出: False
     *
     * @param x
     * @param y
     * @param z
     * @return
     */
    public boolean canMeasureWater(int x, int y, int z) {
        // 贝祖定理告诉 ax+by=z 有解当且仅当 z 是 x, y 的最大公约数的倍数。
        // 因此我们只需要找到 x, y 的最大公约数并判断 z 是否是它的倍数即可。
        if (x + y < z) return false;
        if (x == 0 || y == 0) return z == 0 || x + y == z;
        return z % getGcd(x, y) == 0;
    }

    // 最大公约数
    public static int getGcd(int a, int b) {
        int max, min;
        max = Math.max(a, b);
        min = Math.min(a, b);

        if (max % min != 0) {
            return getGcd(min, max % min);
        }
        return min;
    }

    @Test
    public void fraction() {
        int[] cont = {0, 0, 3};
        logResult(fraction(cont));
    }

    /**
     * LCP 2. 分式化简 有一个同学在学习分式。他需要将一个连分数化成最简分数，你能帮助他吗？
     *
     * <p>连分数是形如上图的分式。在本题中，所有系数都是大于等于0的整数。
     *
     * <p>输入的cont代表连分数的系数（cont[0]代表上图的a0，以此类推）。返回一个长度为2的数组[n, m]，使得连分数的值等于n / m，且n, m最大公约数为1。
     *
     * <p>示例 1：
     *
     * <p>输入：cont = [3, 2, 0, 2] 输出：[13, 4] 解释：原连分数等价于3 + (1 / (2 + (1 / (0 + 1 / 2))))。注意[26, 8],
     * [-13, -4]都不是正确答案。 示例 2：
     *
     * <p>输入：cont = [0, 0, 3] 输出：[3, 1] 解释：如果答案是整数，令分母为1即可。 限制：
     *
     * <p>cont[i] >= 0 1 <= cont的长度 <= 10 cont最后一个元素不等于0 答案的n, m的取值都能被32位int整型存下（即不超过2 ^ 31 - 1）。
     *
     * @param cont
     * @return
     */
    public int[] fraction(int[] cont) {
        int[] result = new int[2];
        int len = cont.length;
        // a 分子  b 分母
        int a = 1, b = cont[len - 1];

        for (int i = len - 2; i >= 0; i--) {
            // 分子 = 前一个的分母
            int f1 = b;
            // 分母 = 前一个的分母* cont[i] + 分子
            int f2 = cont[i] * b + a;
            // 重新赋值
            a = f1;
            b = f2;
        }
        // 最后一次计算是反的
        result[0] = b;
        result[1] = a;

        return result;
    }

    @Test
    public void maximum69Number() {
        int num = 699;
        logResult(maximum69Number(num));
    }
    /**
     * 1323. 6 和 9 组成的最大数字 给你一个仅由数字 6 和 9 组成的正整数 num。
     *
     * <p>你最多只能翻转一位数字，将 6 变成 9，或者把 9 变成 6 。
     *
     * <p>请返回你可以得到的最大数字。
     *
     * <p>示例 1：
     *
     * <p>输入：num = 9669 输出：9969 解释： 改变第一位数字可以得到 6669 。 改变第二位数字可以得到 9969 。 改变第三位数字可以得到 9699 。
     * 改变第四位数字可以得到 9666 。 其中最大的数字是 9969 。 示例 2：
     *
     * <p>输入：num = 9996 输出：9999 解释：将最后一位从 6 变到 9，其结果 9999 是最大的数。 示例 3：
     *
     * <p>输入：num = 9999 输出：9999 解释：无需改变就已经是最大的数字了。
     *
     * <p>提示：
     *
     * <p>1 <= num <= 10^4 num 每一位上的数字都是 6 或者 9 。
     *
     * @param num
     * @return
     */
    public int maximum69Number(int num) {
        int reuslt = 0;
        int k = 1000;
        while (k > 0) {
            int a = num / k;
            boolean flag = false;
            if (a == 6) {
                a = 9;
                flag = true;
            }
            reuslt += a * k;
            num %= k;
            k /= 10;
            if (flag) {
                reuslt += num;
                break;
            }
        }

        return reuslt;
    }

    @Test
    public void subtractProductAndSum() {
        int n = 4421;
        logResult(subtractProductAndSum(n));
    }

    /**
     * 1281. 整数的各位积和之差 给你一个整数 n，请你帮忙计算并返回该整数「各位数字之积」与「各位数字之和」的差。
     *
     * <p>示例 1：
     *
     * <p>输入：n = 234 输出：15 解释： 各位数之积 = 2 * 3 * 4 = 24 各位数之和 = 2 + 3 + 4 = 9 结果 = 24 - 9 = 15 示例 2：
     *
     * <p>输入：n = 4421 输出：21 解释： 各位数之积 = 4 * 4 * 2 * 1 = 32 各位数之和 = 4 + 4 + 2 + 1 = 11 结果 = 32 - 11 =
     * 21
     *
     * <p>提示：
     *
     * <p>1 <= n <= 10^5
     *
     * @param n
     * @return
     */
    public int subtractProductAndSum(int n) {
        int sum = 0, mult = 1;
        while (n > 0) {
            int num = n % 10;
            sum += num;
            mult *= num;
            n /= 10;
        }

        return mult - sum;
    }

    @Test
    public void findNumbers() {
        int[] nums = {555, 901, 482, 9999};
        logResult(findNumbers(nums));
    }

    /**
     * 1295. 统计位数为偶数的数字 给你一个整数数组 nums，请你返回其中位数为 偶数 的数字的个数。
     *
     * <p>示例 1：
     *
     * <p>输入：nums = [12,345,2,6,7896] 输出：2 解释： 12 是 2 位数字（位数为偶数） 345 是 3 位数字（位数为奇数） 2 是 1 位数字（位数为奇数）
     * 6 是 1 位数字 位数为奇数） 7896 是 4 位数字（位数为偶数） 因此只有 12 和 7896 是位数为偶数的数字 示例 2：
     *
     * <p>输入：nums = [555,901,482,1771] 输出：1 解释： 只有 1771 是位数为偶数的数字。
     *
     * <p>提示：
     *
     * <p>1 <= nums.length <= 500 1 <= nums[i] <= 10^5
     *
     * @param nums
     * @return
     */
    public int findNumbers(int[] nums) {
        int result = 0;
        for (int num : nums) {
            int a = (int) Math.log10(num) + 1;
            if ((a & 1) == 0) {
                result++;
            }
        }
        return result;
    }

    /**
     * 1317. 将整数转换为两个无零整数的和 「无零整数」是十进制表示中 不含任何 0 的正整数。
     *
     * <p>给你一个整数 n，请你返回一个 由两个整数组成的列表 [A, B]，满足：
     *
     * <p>A 和 B 都是无零整数 A + B = n 题目数据保证至少有一个有效的解决方案。
     *
     * <p>如果存在多个有效解决方案，你可以返回其中任意一个。
     *
     * <p>示例 1：
     *
     * <p>输入：n = 2 输出：[1,1] 解释：A = 1, B = 1. A + B = n 并且 A 和 B 的十进制表示形式都不包含任何 0 。 示例 2：
     *
     * <p>输入：n = 11 输出：[2,9] 示例 3：
     *
     * <p>输入：n = 10000 输出：[1,9999] 示例 4：
     *
     * <p>输入：n = 69 输出：[1,68] 示例 5：
     *
     * <p>输入：n = 1010 输出：[11,999]
     *
     * <p>提示：
     *
     * <p>2 <= n <= 10^4
     *
     * @param n
     * @return
     */
    public int[] getNoZeroIntegers(int n) {
        int[] result = new int[2];
        result[0] = 1;
        result[1] = n - 1;
        if (n >= 1000) {
            while (result[0] % 10 == 0
                    || result[0] % 100 <= 10
                    || result[0] % 1000 <= 100
                    || result[1] % 10 == 0
                    || result[1] % 100 <= 10
                    || result[1] % 1000 <= 100) {
                result[0]++;
                result[1]--;
            }
        } else if (n >= 100) {
            while (result[0] % 10 == 0
                    || result[0] % 100 <= 10
                    || result[1] % 10 == 0
                    || result[1] % 100 <= 10) {
                result[0]++;
                result[1]--;
            }
        } else if (n >= 10) {
            while (result[0] % 10 == 0 || result[1] % 10 == 0) {
                result[0]++;
                result[1]--;
            }
        }

        return result;
    }

    @Test
    public void surfaceArea() {
        int[][] grid = {{1, 2}, {3, 4}};
        logResult(surfaceArea(grid));
    }

    /**
     * 题目描述 评论 (151) 题解(98) 提交记录 892. 三维形体的表面积 在 N * N 的网格上，我们放置一些 1 * 1 * 1 的立方体。
     *
     * <p>每个值 v = grid[i][j] 表示 v 个正方体叠放在对应单元格 (i, j) 上。
     *
     * <p>请你返回最终形体的表面积。
     *
     * <p>示例 1：
     *
     * <p>输入：[[2]] 输出：10 示例 2：
     *
     * <p>输入：[[1,2],[3,4]] 输出：34 示例 3：
     *
     * <p>输入：[[1,0],[0,2]] 输出：16 示例 4：
     *
     * <p>输入：[[1,1,1],[1,0,1],[1,1,1]] 输出：32 示例 5：
     *
     * <p>输入：[[2,2,2],[2,1,2],[2,2,2]] 输出：46
     *
     * <p>提示：
     *
     * <p>1 <= N <= 50 0 <= grid[i][j] <= 50
     *
     * @param grid
     * @return
     */
    public int surfaceArea(int[][] grid) {
        int rows = grid.length;
        if (rows == 0) {
            return 0;
        }
        int cols = grid[0].length;
        if (cols == 0) {
            return 0;
        }
        int result = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int num = grid[i][j];
                if (num > 0) {
                    result += 2 + 4 * num;
                }
                // 减掉重合的部分
                if (i > 0) {
                    int leftNum = grid[i - 1][j];
                    result -= 2 * Math.min(leftNum, num);
                }
                if (j > 0) {
                    int upNum = grid[i][j - 1];
                    result -= 2 * Math.min(upNum, num);
                }
            }
        }
        return result;
    }

    @Test
    public void numberOfSteps() {
        int num = 123;
        logResult(numberOfSteps(num));
    }

    /**
     * 1342. 将数字变成 0 的操作次数 给你一个非负整数 num ，请你返回将它变成 0 所需要的步数。 如果当前数字是偶数，你需要把它除以 2 ；否则，减去 1 。
     *
     * <p>示例 1：
     *
     * <p>输入：num = 14 输出：6 解释： 步骤 1) 14 是偶数，除以 2 得到 7 。 步骤 2） 7 是奇数，减 1 得到 6 。 步骤 3） 6 是偶数，除以 2 得到 3
     * 。 步骤 4） 3 是奇数，减 1 得到 2 。 步骤 5） 2 是偶数，除以 2 得到 1 。 步骤 6） 1 是奇数，减 1 得到 0 。 示例 2：
     *
     * <p>输入：num = 8 输出：4 解释： 步骤 1） 8 是偶数，除以 2 得到 4 。 步骤 2） 4 是偶数，除以 2 得到 2 。 步骤 3） 2 是偶数，除以 2 得到 1
     * 。 步骤 4） 1 是奇数，减 1 得到 0 。 示例 3：
     *
     * <p>输入：num = 123 输出：12
     *
     * <p>提示：
     *
     * <p>0 <= num <= 10^6
     *
     * @param num
     * @return
     */
    public int numberOfSteps(int num) {
        int count = 0;

        while (num > 0) {
            if ((num & 1) == 1) {
                num--;
            } else {
                num >>= 1;
            }
            count++;
        }

        return count;
    }

    @Test
    public void hasGroupsSizeX() {
        int[] deck = {1};
        logResult(hasGroupsSizeX(deck));
    }
    /**
     * 914. 卡牌分组 给定一副牌，每张牌上都写着一个整数。
     *
     * <p>此时，你需要选定一个数字 X，使我们可以将整副牌按下述规则分成 1 组或更多组：
     *
     * <p>每组都有 X 张牌。 组内所有的牌上都写着相同的整数。 仅当你可选的 X >= 2 时返回 true。
     *
     * <p>示例 1：
     *
     * <p>输入：[1,2,3,4,4,3,2,1] 输出：true 解释：可行的分组是 [1,1]，[2,2]，[3,3]，[4,4] 示例 2：
     *
     * <p>输入：[1,1,1,2,2,2,3,3] 输出：false 解释：没有满足要求的分组。 示例 3：
     *
     * <p>输入：[1] 输出：false 解释：没有满足要求的分组。 示例 4：
     *
     * <p>输入：[1,1] 输出：true 解释：可行的分组是 [1,1] 示例 5：
     *
     * <p>输入：[1,1,2,2,2,2] 输出：true 解释：可行的分组是 [1,1]，[2,2]，[2,2]
     *
     * <p>提示：
     *
     * <p>1 <= deck.length <= 10000 0 <= deck[i] < 10000
     *
     * @param deck
     * @return
     */
    public boolean hasGroupsSizeX(int[] deck) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : deck) {
            int count = map.getOrDefault(num, 0);
            map.put(num, ++count);
        }
        int gcd = 0;
        for (int count : map.values()) {
            if (count < 2) {
                return false;
            }
            if (gcd == 0) {
                gcd = count;
                continue;
            }
            gcd = getGcd(gcd, count);
        }

        return gcd >= 2;
    }

    /**
     * 1360. 日期之间隔几天 请你编写一个程序来计算两个日期之间隔了多少天。
     *
     * <p>日期以字符串形式给出，格式为 YYYY-MM-DD，如示例所示。
     *
     * <p>示例 1：
     *
     * <p>输入：date1 = "2019-06-29", date2 = "2019-06-30" 输出：1 示例 2：
     *
     * <p>输入：date1 = "2020-01-15", date2 = "2019-12-31" 输出：15
     *
     * <p>提示：
     *
     * <p>给定的日期是 1971 年到 2100 年之间的有效日期。
     *
     * @param date1
     * @param date2
     * @return
     */
    public int daysBetweenDates(String date1, String date2) {
        int result = dateToDays(date1) - dateToDays(date2);
        return Math.abs(result);
    }

    private int dateToDays(String date) {
        int year = Integer.valueOf(date.substring(0, 4));
        int month = Integer.valueOf(date.substring(5, 7));
        int day = Integer.valueOf(date.substring(8, 10));
        int[] months = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        int result = day - 1;
        while (month != 0) {
            --month;
            result += months[month];
            if (month == 2 && isLeapYear(year)) result += 1;
        }
        result += 365 * (year - 1971);
        result += (year - 1) / 4 - 1971 / 4;
        result -= (year - 1) / 100 - 1971 / 100;
        result += (year - 1) / 400 - 1971 / 400;
        return result;
    }

    @Test
    public void minMoves() {
        int[] nums = {1, 1, 2147483647};
        logResult(minMoves(nums));
    }

    /**
     * 453. 最小移动次数使数组元素相等 给定一个长度为 n 的非空整数数组，找到让数组所有元素相等的最小移动次数。每次移动可以使 n - 1 个元素增加 1。
     *
     * <p>示例:
     *
     * <p>输入: [1,2,3]
     *
     * <p>输出: 3
     *
     * <p>解释: 只需要3次移动（注意每次移动会增加两个元素的值）：
     *
     * <p>[1,2,3] => [2,3,3] => [3,4,3] => [4,4,4]
     *
     * @param nums
     * @return
     */
    public int minMoves(int[] nums) {
        int len = nums.length;
        int min = nums[0];
        for (int i = 1; i < len; i++) {
            if (nums[i] < min) {
                min = nums[i];
            }
        }
        int result = 0;
        for (int num : nums) {
            result += num - min;
        }
        return result;
    }

    // 最小公倍数
    public static int getLcm(int n1, int n2) {
        return n1 * n2 / getGcd(n1, n2);
    }

    public static long getLcm(long n1, long n2) {
        return n1 * n2 / getGcd(n1, n2);
    }

    // 最大公约数
    public static long getGcd(long a, long b) {
        long max, min;
        max = Math.max(a, b);
        min = Math.min(a, b);

        if (max % min != 0) {
            return getGcd(min, max % min);
        }
        return min;
    }

    @Test
    public void convertToBase7() {
        int num = -10;
        logResult(convertToBase7(num));
    }
    /**
     * 504. 七进制数 给定一个整数，将其转化为7进制，并以字符串形式输出。
     *
     * <p>示例 1:
     *
     * <p>输入: 100 输出: "202" 示例 2:
     *
     * <p>输入: -7 输出: "-10" 注意: 输入范围是 [-1e7, 1e7] 。
     *
     * @param num
     * @return
     */
    public String convertToBase7(int num) {
        StringBuilder sb = new StringBuilder();
        if (num == 0) {
            return "0";
        }
        boolean flag = false;
        if (num < 0) {
            flag = true;
            num = -num;
        }
        while (num > 0) {
            sb.insert(0, num % 7);
            num /= 7;
        }
        if (flag) {
            sb.insert(0, '-');
        }

        return sb.toString();
    }

    @Test
    public void checkPerfectNumber() {
        int num = 28;
        logResult(checkPerfectNumber(num));
    }
    /**
     * 507. 完美数 对于一个 正整数，如果它和除了它自身以外的所有正因子之和相等，我们称它为“完美数”。
     *
     * <p>给定一个 整数 n， 如果他是完美数，返回 True，否则返回 False
     *
     * <p>示例：
     *
     * <p>输入: 28 输出: True 解释: 28 = 1 + 2 + 4 + 7 + 14
     *
     * <p>提示：
     *
     * <p>输入的数字 n 不会超过 100,000,000. (1e8)
     *
     * @param num
     * @return
     */
    public boolean checkPerfectNumber(int num) {
        int sum = 1;

        if ((num & 1) == 0) {
            sum += 2;
            sum += num >> 1;
        }

        for (int i = 3; i * i <= num; i++) {
            if (num % i == 0) {
                sum += i;
                if (i * i != num) {
                    sum += num / i;
                }
            }
            if (sum > num) {
                return false;
            }
        }
        log.debug("sum:{}", sum);

        return sum == num;
    }

    @Test
    public void findComplement() {
        int num = 8;
        logResult(findComplement(num));
    }

    /**
     * 476. 数字的补数 给定一个正整数，输出它的补数。补数是对该数的二进制表示取反。
     *
     * <p>示例 1:
     *
     * <p>输入: 5 输出: 2 解释: 5 的二进制表示为 101（没有前导零位），其补数为 010。所以你需要输出 2 。 示例 2:
     *
     * <p>输入: 1 输出: 0 解释: 1 的二进制表示为 1（没有前导零位），其补数为 0。所以你需要输出 0 。
     *
     * @param num
     * @return
     */
    public int findComplement(int num) {
        // 思路, 先求num最近的2的次幂
        int n = num;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        log.debug("n:{}", n);
        return n - num;
    }

    /**
     * 面试题62. 圆圈中最后剩下的数字 0,1,,n-1这n个数字排成一个圆圈，从数字0开始，每次从这个圆圈里删除第m个数字。求出这个圆圈里剩下的最后一个数字。
     *
     * <p>例如，0、1、2、3、4这5个数字组成一个圆圈，从数字0开始每次删除第3个数字，则删除的前4个数字依次是2、0、4、1，因此最后剩下的数字是3。
     *
     * <p>示例 1：
     *
     * <p>输入: n = 5, m = 3 输出: 3 示例 2：
     *
     * <p>输入: n = 10, m = 17 输出: 2
     *
     * <p>限制：
     *
     * <p>1 <= n <= 10^5 1 <= m <= 10^6
     *
     * @param n
     * @param m
     * @return
     */
    public int lastRemaining(int n, int m) {
        int ans = 0;
        // 最后一轮剩下2个人，所以从2开始反推
        for (int i = 2; i <= n; i++) {
            ans = (ans + m) % i;
        }
        return ans;
    }

    /**
     * 598. 范围求和 II 给定一个初始元素全部为 0，大小为 m*n 的矩阵 M 以及在 M 上的一系列更新操作。
     *
     * <p>操作用二维数组表示，其中的每个操作用一个含有两个正整数 a 和 b 的数组表示，含义是将所有符合 0 <= i < a 以及 0 <= j < b 的元素 M[i][j]
     * 的值都增加 1。
     *
     * <p>在执行给定的一系列操作后，你需要返回矩阵中含有最大整数的元素个数。
     *
     * <p>示例 1:
     *
     * <p>输入: m = 3, n = 3 operations = [[2,2],[3,3]] 输出: 4 解释: 初始状态, M = [[0, 0, 0], [0, 0, 0], [0,
     * 0, 0]]
     *
     * <p>执行完操作 [2,2] 后, M = [[1, 1, 0], [1, 1, 0], [0, 0, 0]]
     *
     * <p>执行完操作 [3,3] 后, M = [[2, 2, 1], [2, 2, 1], [1, 1, 1]]
     *
     * <p>M 中最大的整数是 2, 而且 M 中有4个值为2的元素。因此返回 4。 注意:
     *
     * <p>m 和 n 的范围是 [1,40000]。 a 的范围是 [1,m]，b 的范围是 [1,n]。 操作数目不超过 10000。
     *
     * @param m
     * @param n
     * @param ops
     * @return
     */
    public int maxCount(int m, int n, int[][] ops) {
        int minRows = m, minCols = n;
        for (int[] op : ops) {
            int rows = op[0];
            int cols = op[1];
            if (rows < minRows) {
                minRows = rows;
            }
            if (cols < minCols) {
                minCols = cols;
            }
        }
        return minRows * minCols;
    }

    @Test
    public void judgeSquareSum() {
        int c = 17;
        logResult(judgeSquareSum(c));
    }

    /**
     * 633. 平方数之和 给定一个非负整数 c ，你要判断是否存在两个整数 a 和 b，使得 a2 + b2 = c。
     *
     * <p>示例1:
     *
     * <p>输入: 5 输出: True 解释: 1 * 1 + 2 * 2 = 5
     *
     * <p>示例2:
     *
     * <p>输入: 3 输出: False
     *
     * @param c
     * @return
     */
    public boolean judgeSquareSum(int c) {

        // 费马平方和定理告诉我们：
        //
        // 一个非负整数 cc 能够表示为两个整数的平方和，当且仅当 cc 的所有形如 4k+34k+3 的质因子的幂次均为偶数。
        //
        // 证明方法可以见 这里。
        //
        // 因此我们对 c 进行质因数分解，再判断形如 4k+3 的质因子的幂次是否均为偶数即可。
        if (c < 3) {
            return true;
        }
        for (int i = 1; i * i <= c; i++) {
            int num = c - i * i;
            double b = Math.sqrt(num);
            if (b == (int) b) return true;
        }

        return false;
    }

    @Test
    public void myAtoi() {
        String str = "+";
        logResult(myAtoi(str));
    }
    /**
     * 8. 字符串转换整数 (atoi) 请你来实现一个 atoi 函数，使其能将字符串转换成整数。
     *
     * <p>首先，该函数会根据需要丢弃无用的开头空格字符，直到寻找到第一个非空格的字符为止。接下来的转化规则如下：
     *
     * <p>如果第一个非空字符为正或者负号时，则将该符号与之后面尽可能多的连续数字字符组合起来，形成一个有符号整数。
     * 假如第一个非空字符是数字，则直接将其与之后连续的数字字符组合起来，形成一个整数。 该字符串在有效的整数部分之后也可能会存在多余的字符，那么这些字符可以被忽略，它们对函数不应该造成影响。
     * 注意：假如该字符串中的第一个非空格字符不是一个有效整数字符、字符串为空或字符串仅包含空白字符时，则你的函数不需要进行转换，即无法进行有效转换。
     *
     * <p>在任何情况下，若函数不能进行有效的转换时，请返回 0 。
     *
     * <p>提示：
     *
     * <p>本题中的空白字符只包括空格字符 ' ' 。 假设我们的环境只能存储 32 位大小的有符号整数，那么其数值范围为 [−231, 231 − 1]。如果数值超过这个范围，请返回
     * INT_MAX (231 − 1) 或 INT_MIN (−231) 。
     *
     * <p>示例 1:
     *
     * <p>输入: "42" 输出: 42 示例 2:
     *
     * <p>输入: " -42" 输出: -42 解释: 第一个非空白字符为 '-', 它是一个负号。 我们尽可能将负号与后面所有连续出现的数字组合起来，最后得到 -42 。 示例 3:
     *
     * <p>输入: "4193 with words" 输出: 4193 解释: 转换截止于数字 '3' ，因为它的下一个字符不为数字。 示例 4:
     *
     * <p>输入: "words and 987" 输出: 0 解释: 第一个非空字符是 'w', 但它不是数字或正、负号。 因此无法执行有效的转换。 示例 5:
     *
     * <p>输入: "-91283472332" 输出: -2147483648 解释: 数字 "-91283472332" 超过 32 位有符号整数范围。 因此返回 INT_MIN
     * (−231) 。
     *
     * @param str
     * @return
     */
    public int myAtoi(String str) {
        int result = 0;
        boolean minus = false;
        int len = str.length();
        if (len == 0) {
            return 0;
        }

        char[] chars = str.toCharArray();
        int start = 0;
        while (start < len && chars[start] == ' ') {
            start++;
        }
        if (start == len) {
            return 0;
        }
        if (chars[start] == '+') {
            start++;
        } else if (chars[start] == '-') {
            minus = true;
            start++;
        }

        if (start == len || chars[start] < '0' || chars[start] > '9') {
            return 0;
        }
        int minValue = Integer.MIN_VALUE / 10;
        int maxValue = Integer.MAX_VALUE / 10;

        for (int i = start; i < len; i++) {
            char c = chars[i];
            if (c < '0' || c > '9') {
                break;
            }
            int num = c - '0';
            if (minus) {
                num = -num;
            }
            if (result > maxValue || (result == maxValue && num >= 7)) {
                return Integer.MAX_VALUE;
            }
            if (result < minValue || (result == minValue && num <= -8)) {
                return Integer.MIN_VALUE;
            }
            result = result * 10 + num;
        }
        return result;
    }

    /**
     * 693. 交替位二进制数 给定一个正整数，检查他是否为交替位二进制数：换句话说，就是他的二进制数相邻的两个位数永不相等。
     *
     * <p>示例 1:
     *
     * <p>输入: 5 输出: True 解释: 5的二进制数是: 101 示例 2:
     *
     * <p>输入: 7 输出: False 解释: 7的二进制数是: 111 示例 3:
     *
     * <p>输入: 11 输出: False 解释: 11的二进制数是: 1011 示例 4:
     *
     * <p>输入: 10 输出: True 解释: 10的二进制数是: 1010
     *
     * @param n
     * @return
     */
    public boolean hasAlternatingBits(int n) {
        int a = n ^ (n >> 1);
        return (a & (a + 1)) == 0;
        /*if (n < 2) {
            return false;
        }
        int last = -1;
        while (n != 0) {
            int tmp = n & 1;
            if (last != - 1 && tmp == last) {
                return false;
            }
            last = tmp;
            n = n >> 1;
        }


        return false;*/
    }

    @Test
    public void countLargestGroup() {
        int n = 24;
        logResult(countLargestGroup(n));
    }

    /**
     * 5360. 统计最大组的数目 给你一个整数 n 。请你先求出从 1 到 n 的每个整数 10 进制表示下的数位和（每一位上的数字相加），然后把数位和相等的数字放到同一个组中。
     *
     * <p>请你统计每个组中的数字数目，并返回数字数目并列最多的组有多少个。
     *
     * <p>示例 1：
     *
     * <p>输入：n = 13 输出：4 解释：总共有 9 个组，将 1 到 13 按数位求和后这些组分别是：
     * [1,10]，[2,11]，[3,12]，[4,13]，[5]，[6]，[7]，[8]，[9]。总共有 4 个组拥有的数字并列最多。 示例 2：
     *
     * <p>输入：n = 2 输出：2 解释：总共有 2 个大小为 1 的组 [1]，[2]。 示例 3：
     *
     * <p>输入：n = 15 输出：6 示例 4：
     *
     * <p>输入：n = 24 输出：5
     *
     * <p>提示：
     *
     * <p>1 <= n <= 10^4
     *
     * @param n
     * @return
     */
    public int countLargestGroup(int n) {
        if (n < 10) {
            return n;
        }
        // 最大值为10000 数位求和最大 9999 => 36;
        int[] nums = new int[36];

        for (int i = 1; i <= n; i++) {
            int tmp = i;
            int sum = 0;
            while (tmp != 0) {
                sum += tmp % 10;
                tmp /= 10;
            }

            nums[sum - 1]++;
        }
        int count = 1;
        int max = 0;
        for (int num : nums) {
            if (num == 0) {
                continue;
            }
            if (num > max) {
                max = num;
                count = 1;
            } else if (num == max) {
                count++;
            }
        }

        return count;
    }

    @Test
    public void numSteps() {
        String s = "10";
        logResult(numSteps(s));
    }

    /**
     * 5377. 将二进制表示减到 1 的步骤数 给你一个以二进制形式表示的数字 s 。请你返回按下述规则将其减少到 1 所需要的步骤数：
     *
     * <p>如果当前数字为偶数，则将其除以 2 。
     *
     * <p>如果当前数字为奇数，则将其加上 1 。
     *
     * <p>题目保证你总是可以按上述规则将测试用例变为 1 。
     *
     * <p>示例 1：
     *
     * <p>输入：s = "1101" 输出：6 解释："1101" 表示十进制数 13 。 Step 1) 13 是奇数，加 1 得到 14 Step 2) 14 是偶数，除 2 得到 7
     * Step 3) 7 是奇数，加 1 得到 8 Step 4) 8 是偶数，除 2 得到 4 Step 5) 4 是偶数，除 2 得到 2 Step 6) 2 是偶数，除 2 得到 1
     * 示例 2：
     *
     * <p>输入：s = "10" 输出：1 解释："10" 表示十进制数 2 。 Step 1) 2 是偶数，除 2 得到 1 示例 3：
     *
     * <p>输入：s = "1" 输出：0
     *
     * <p>提示：
     *
     * <p>1 <= s.length <= 500 s 由字符 '0' 或 '1' 组成。 s[0] == '1'
     *
     * @param s
     * @return
     */
    public int numSteps(String s) {
        char[] chars = s.toCharArray();
        int len = chars.length;
        int count = 0;
        int index = len - 1;

        while (index > 0) {
            char c = chars[index];
            switch (c) {
                case '1':
                    {
                        int lastIndex = index;
                        while (lastIndex >= 0 && chars[lastIndex] == '1') {
                            chars[lastIndex--] = '0';
                        }
                        if (lastIndex >= 0) {
                            chars[lastIndex] = '1';
                        }
                        count++;
                        break;
                    }
                case '0':
                    index--;
                    count++;
                    break;
            }
        }
        if (chars[0] == '0') {
            count++;
        }

        return count;

        /*long num = 0;

        for (char c : s.toCharArray()) {
            num = num * 2 + (c - '0');
        }
        int count = 0;
        while (num != 1) {

            if ((num&1) == 1) { // 计数
                num++; // 数字为奇数，则将其加上 1
            } else {
                num >>= 1; // 数字为偶数，则将其除以 2
            }

            count++;
        }

        return count;*/
    }

    @Test
    public void stoneGameIII() {
        int[] values = {1, 2, 3, 4, 5, 6, 7};
        logResult(stoneGameIII(values));
    }
    /**
     * 5379. 石子游戏 III Alice 和 Bob 用几堆石子在做游戏。几堆石子排成一行，每堆石子都对应一个得分，由数组 stoneValue 给出。
     *
     * <p>Alice 和 Bob 轮流取石子，Alice 总是先开始。在每个玩家的回合中，该玩家可以拿走剩下石子中的的前 1、2 或 3 堆石子 。 比赛一直持续到所有石头都被拿走。
     *
     * <p>每个玩家的最终得分为他所拿到的每堆石子的对应得分之和。每个玩家的初始分数都是 0 。比赛的目标是决出最高分， 得分最高的选手将会赢得比赛，比赛也可能会出现平局。
     *
     * <p>假设 Alice 和 Bob 都采取 最优策略 。 如果 Alice 赢了就返回 "Alice" ，Bob 赢了就返回 "Bob"，平局（分数相同）返回 "Tie" 。
     *
     * <p>示例 1：
     *
     * <p>输入：values = [1,2,3,7] 输出："Bob" 解释：Alice 总是会输，她的最佳选择是拿走前三堆，得分变成 6 。但是 Bob 的得分为 7，Bob 获胜。 示例
     * 2：
     *
     * <p>输入：values = [1,2,3,-9] 输出："Alice" 解释：Alice 要想获胜就必须在第一个回合拿走前三堆石子，给 Bob 留下负分。 如果 Alice
     * 只拿走第一堆，那么她的得分为 1，接下来 Bob 拿走第二、三堆，得分为 5 。之后 Alice 只能拿到分数 -9 的石子堆，输掉比赛。 如果 Alice 拿走前两堆，那么她的得分为
     * 3，接下来 Bob 拿走第三堆，得分为 3 。之后 Alice 只能拿到分数 -9 的石子堆，同样会输掉比赛。 注意，他们都应该采取 最优策略 ，所以在这里 Alice
     * 将选择能够使她获胜的方案。 示例 3：
     *
     * <p>输入：values = [1,2,3,6] 输出："Tie" 解释：Alice 无法赢得比赛。如果她决定选择前三堆，她可以以平局结束比赛，否则她就会输。 示例 4：
     *
     * <p>输入：values = [1,2,3,-1,-2,-3,7] 输出："Alice" 示例 5：
     *
     * <p>输入：values = [-1,-2,-3] 输出："Tie"
     *
     * <p>提示：
     *
     * <p>1 <= values.length <= 50000 -1000 <= values[i] <= 1000
     *
     * @param stoneValue
     * @return
     */
    public String stoneGameIII(int[] stoneValue) {
        String result = "Tie";
        int len = stoneValue.length;
        int[] dp = new int[len + 1];
        dp[0] = stoneValue[0];
        // 我们用一个数组 dp 来表示“在只剩下第 i 堆到最后一堆石子时，当前玩家最多能拿多少分”。
        // 假如算出了这个 dp 数组，那么最终答案就是判断 dp[0] 和（分数总和-dp[0]）之间的大小关系即可

        // 其实这是 Alice 一个人的精分游戏，你老想着有两个人在博弈就会很难下手。
        //
        // 一开始，Alice 面临取1、2、3 堆的抉择，Alice 此刻总共能拿多少分，
        // 取决于取完 1 or 2 or 3 堆之后面对剩下的这堆石子精分出来的 Bob能拿多少分，因为剩下的分就是 Alice 的。
        //
        // 也就是 dp[i]=Max(sum-dp[i+1],sum-dp[i+2],sum-dp[i+3])
        // dp[i]=Max(sum−dp[i+1],sum−dp[i+2],sum−dp[i+3]) ，
        // dp[i]为石碓 i……n 得最高分数，sum 为 i……n 的石碓分数之和。
        int sum = 0;
        // 每一步的最优解 = i + step 中的 最劣解
        dp[len - 1] = stoneValue[len - 1];
        for (int i = len - 1; i >= 0; i--) {
            int max = Integer.MIN_VALUE;
            sum += stoneValue[i];
            // stoneValue[i] + sum{i+1 ~ len} - dp[i + 1];
            // stoneValue[i] + stoneValue[i + 1]  +  sum{i+2 ~ len} - dp[i + 2];
            // stoneValue[i] + stoneValue[i + 1] + stoneValue[i + 2] + sum{i+3 ~ len} - dp[i + 3];
            for (int j = 0; j < 3 && i + j < len; j++) {
                int num = sum - dp[i + j + 1];
                if (num > max) {
                    max = num;
                }
            }

            dp[i] = max;
        }
        log.debug("dp:{}", dp);

        int bob = sum - dp[0];
        if (dp[0] > bob) {
            result = "Alice";
        } else if (dp[0] < bob) {
            result = "Bob";
        }

        return result;
    }

    private int canWinStoneGame(int[] stoneValue, int[] winArray) {
        int len = winArray.length;
        int bWin = winArray[len - 1];
        if (bWin != 0) {
            return bWin;
        }

        return 0;
        /*// 判断对手能不能赢
        boolean a = canWinNim(n - 3,winArray);
        if (!a) { // 对手 输了, 说明我们能赢
            winArray[n] = true;
            return true;
        }
        boolean b = canWinNim(n - 2,winArray);
        if (!b) { // 对手 输了, 说明我们能赢
            winArray[n] = true;
            return true;
        }
        boolean c = canWinNim(n - 1,winArray);
        if (!c) { // 对手 输了, 说明我们能赢
            winArray[n] = true;
            return true;
        }
        winArray[n] = false;
        return false;*/
    }

    /**
     * 1009. 十进制整数的反码 每个非负整数 N 都有其二进制表示。例如， 5 可以被表示为二进制 "101"，11 可以用二进制 "1011" 表示，依此类推。注意，除 N = 0
     * 外，任何二进制表示中都不含前导零。
     *
     * <p>二进制的反码表示是将每个 1 改为 0 且每个 0 变为 1。例如，二进制数 "101" 的二进制反码为 "010"。
     *
     * <p>给你一个十进制数 N，请你返回其二进制表示的反码所对应的十进制整数。
     *
     * <p>示例 1：
     *
     * <p>输入：5 输出：2 解释：5 的二进制表示为 "101"，其二进制反码为 "010"，也就是十进制中的 2 。 示例 2：
     *
     * <p>输入：7 输出：0 解释：7 的二进制表示为 "111"，其二进制反码为 "000"，也就是十进制中的 0 。 示例 3：
     *
     * <p>输入：10 输出：5 解释：10 的二进制表示为 "1010"，其二进制反码为 "0101"，也就是十进制中的 5 。
     *
     * <p>提示：
     *
     * <p>0 <= N < 10^9 本题与 476：https://leetcode-cn.com/problems/number-complement/ 相同
     *
     * @param N
     * @return
     */
    public int bitwiseComplement(int N) {
        int n = N;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return n - N;
    }

    @Test
    public void happyNum() {
        int num = 10;
        logResult(happyNum(num));
    }

    private boolean happyNum(int num) {
        // set 记录  防止死循环
        Set<Integer> set = new HashSet<>();
        return happyNum(num, set);
    }

    private boolean happyNum(int num, Set<Integer> set) {
        if (num == 1) {
            return true;
        }
        if (set.contains(num)) {
            return false;
        }
        set.add(num);
        // 求各位的平方和
        int sum = 0;
        while (num > 0) {
            int a = num % 10;
            sum += a * a;
            num /= 10;
        }
        // 递归
        return happyNum(sum, set);
    }

    @Test
    public void binaryGap() {
        int num = 8;
        logResult(binaryGap(num));
    }

    /**
     * 868. 二进制间距 给定一个正整数 N，找到并返回 N 的二进制表示中两个连续的 1 之间的最长距离。
     *
     * <p>如果没有两个连续的 1，返回 0 。
     *
     * <p>示例 1：
     *
     * <p>输入：22 输出：2 解释： 22 的二进制是 0b10110 。 在 22 的二进制表示中，有三个 1，组成两对连续的 1 。 第一对连续的 1 中，两个 1 之间的距离为 2
     * 。 第二对连续的 1 中，两个 1 之间的距离为 1 。 答案取两个距离之中最大的，也就是 2 。 示例 2：
     *
     * <p>输入：5 输出：2 解释： 5 的二进制是 0b101 。 示例 3：
     *
     * <p>输入：6 输出：1 解释： 6 的二进制是 0b110 。 示例 4：
     *
     * <p>输入：8 输出：0 解释： 8 的二进制是 0b1000 。 在 8 的二进制表示中没有连续的 1，所以返回 0 。
     *
     * <p>提示：
     *
     * <p>1 <= N <= 10^9
     *
     * @param N
     * @return
     */
    public int binaryGap(int N) {

        // 把最后的0移除
        while ((N & 1) == 0) {
            N >>= 1;
        }
        int max = 0;
        int count = 0;
        while (N > 0) {
            if ((N & 1) == 1) {
                if (count > max) {
                    max = count;
                }
                count = 0;
            }
            count++;
            N >>= 1;
        }
        return max;
    }

    /**
     * 908. 最小差值 I 给你一个整数数组 A，对于每个整数 A[i]，我们可以选择处于区间 [-K, K] 中的任意数 x ，将 x 与 A[i] 相加，结果存入 A[i] 。
     *
     * <p>在此过程之后，我们得到一些数组 B。
     *
     * <p>返回 B 的最大值和 B 的最小值之间可能存在的最小差值。
     *
     * <p>示例 1：
     *
     * <p>输入：A = [1], K = 0 输出：0 解释：B = [1] 示例 2：
     *
     * <p>输入：A = [0,10], K = 2 输出：6 解释：B = [2,8] 示例 3：
     *
     * <p>输入：A = [1,3,6], K = 3 输出：0 解释：B = [3,3,3] 或 B = [4,4,4]
     *
     * <p>提示：
     *
     * <p>1 <= A.length <= 10000 0 <= A[i] <= 10000 0 <= K <= 10000
     *
     * @param A
     * @param K
     * @return
     */
    public int smallestRangeI(int[] A, int K) {
        int len = A.length;
        if (len <= 1) {
            return 0;
        }
        int min = A[0], max = A[0];
        for (int i = 1; i < len; i++) {
            if (A[i] > max) {
                max = A[i];
            }
            if (A[i] < min) {
                min = A[i];
            }
        }
        if (max - min <= 2 * K) {
            return 0;
        }

        return max - min - 2 * K;
    }

    /**
     * 883. 三维形体投影面积 在 N * N 的网格中，我们放置了一些与 x，y，z 三轴对齐的 1 * 1 * 1 立方体。
     *
     * <p>每个值 v = grid[i][j] 表示 v 个正方体叠放在单元格 (i, j) 上。
     *
     * <p>现在，我们查看这些立方体在 xy、yz 和 zx 平面上的投影。
     *
     * <p>投影就像影子，将三维形体映射到一个二维平面上。
     *
     * <p>在这里，从顶部、前面和侧面看立方体时，我们会看到“影子”。
     *
     * <p>返回所有三个投影的总面积。
     *
     * <p>示例 1：
     *
     * <p>输入：[[2]] 输出：5 示例 2：
     *
     * <p>输入：[[1,2],[3,4]] 输出：17 解释： 这里有该形体在三个轴对齐平面上的三个投影(“阴影部分”)。
     *
     * <p>示例 3：
     *
     * <p>输入：[[1,0],[0,2]] 输出：8 示例 4：
     *
     * <p>输入：[[1,1,1],[1,0,1],[1,1,1]] 输出：14 示例 5：
     *
     * <p>输入：[[2,2,2],[2,1,2],[2,2,2]] 输出：21
     *
     * <p>提示：
     *
     * <p>1 <= grid.length = grid[0].length <= 50 0 <= grid[i][j] <= 50
     *
     * @param grid
     * @return
     */
    public int projectionArea(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        int result = 0;
        // 寻找每一行的最大值
        for (int i = 0; i < rows; i++) {
            int max = 0;
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] > 0) {
                    // xy水平面积
                    result++;
                }
                if (grid[i][j] > max) {
                    max = grid[i][j];
                }
            }
            // yz 投影面积
            result += max;
        }
        // 寻找每一列的最大值
        for (int i = 0; i < cols; i++) {
            int max = 0;
            for (int j = 0; j < rows; j++) {
                if (grid[j][i] > max) {
                    max = grid[j][i];
                }
            }
            // xz 投影面积
            result += max;
        }

        return result;
    }

    @Test
    public void largestTimeFromDigits() {
        int[] nums = {1, 2, 3, 4};
        logResult(largestTimeFromDigits(nums));
    }

    /**
     * 949. 给定数字能组成的最大时间 给定一个由 4 位数字组成的数组，返回可以设置的符合 24 小时制的最大时间。
     *
     * <p>最小的 24 小时制时间是 00:00，而最大的是 23:59。从 00:00 （午夜）开始算起，过得越久，时间越大。
     *
     * <p>以长度为 5 的字符串返回答案。如果不能确定有效时间，则返回空字符串。
     *
     * <p>示例 1：
     *
     * <p>输入：[1,2,3,4] 输出："23:41" 示例 2：
     *
     * <p>输入：[5,5,5,5] 输出：""
     *
     * @param A
     * @return
     */
    public String largestTimeFromDigits(int[] A) {

        int max = -1;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (i == j) {
                    continue;
                }
                for (int k = 0; k < 4; k++) {
                    if (k == i || k == j) {
                        continue;
                    }
                    int l = 6 - i - j - k;
                    int hours = A[i] * 10 + A[j];
                    int minutes = A[k] * 10 + A[l];

                    if (hours < 24 && minutes < 60) {

                        int time = hours * 60 + minutes;
                        if (time > max) {
                            max = time;
                        }
                    }
                }
            }
        }

        return max == -1 ? "" : String.format("%02d:%02d", max / 60, max % 60);
    }

    /**
     * 970. 强整数 给定两个正整数 x 和 y，如果某一整数等于 x^i + y^j，其中整数 i >= 0 且 j >= 0，那么我们认为该整数是一个强整数。
     *
     * <p>返回值小于或等于 bound 的所有强整数组成的列表。
     *
     * <p>你可以按任何顺序返回答案。在你的回答中，每个值最多出现一次。
     *
     * <p>示例 1：
     *
     * <p>输入：x = 2, y = 3, bound = 10 输出：[2,3,4,5,7,9,10] 解释： 2 = 2^0 + 3^0 3 = 2^1 + 3^0 4 = 2^0 +
     * 3^1 5 = 2^1 + 3^1 7 = 2^2 + 3^1 9 = 2^3 + 3^0 10 = 2^0 + 3^2 示例 2：
     *
     * <p>输入：x = 3, y = 5, bound = 15 输出：[2,4,6,8,10,14]
     *
     * <p>提示：
     *
     * <p>1 <= x <= 100 1 <= y <= 100 0 <= bound <= 10^6
     *
     * @param x
     * @param y
     * @param bound
     * @return
     */
    public List<Integer> powerfulIntegers(int x, int y, int bound) {
        // List<Integer> list = new ArrayList<>();
        int iMax = x == 1 ? 0 : (int) (Math.log(bound - 1) / Math.log(x));
        int jMax = y == 1 ? 0 : (int) (Math.log(bound - 1) / Math.log(y));
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < iMax && Math.pow(x, i) < bound; i++) {
            int xNum = (int) Math.pow(x, i);
            for (int j = 0; j < jMax && Math.pow(y, j) <= bound - xNum; j++) {
                int num = (int) Math.pow(x, i) + (int) Math.pow(y, j);
                set.add(num);
            }
        }
        return new ArrayList<>(set);
    }

    /**
     * 1037. 有效的回旋镖 回旋镖定义为一组三个点，这些点各不相同且不在一条直线上。
     *
     * <p>给出平面上三个点组成的列表，判断这些点是否可以构成回旋镖。
     *
     * <p>示例 1：
     *
     * <p>输入：[[1,1],[2,3],[3,2]] 输出：true 示例 2：
     *
     * <p>输入：[[1,1],[2,2],[3,3]] 输出：false
     *
     * <p>提示：
     *
     * <p>points.length == 3 points[i].length == 2 0 <= points[i][j] <= 100
     *
     * @param points
     * @return
     */
    public boolean isBoomerang(int[][] points) {
        // 判断三点是否共线
        int[] point1 = points[0];
        int[] point2 = points[1];
        int[] point3 = points[2];
        if (inLine(point1, point2, point3)) {
            return false;
        }
        return true;
    }

    @Test
    public void largestTriangleArea() {
        int[][] points = {{8, 3}, {5, 6}, {3, 5}};

        logResult(largestTriangleArea(points));
    }

    /**
     * 812. 最大三角形面积 给定包含多个点的集合，从其中取三个点组成三角形，返回能组成的最大三角形的面积。
     *
     * <p>示例: 输入: points = [[0,0],[0,1],[1,0],[0,2],[2,0]] 输出: 2 解释: 这五个点如下图所示。组成的橙色三角形是最大的，面积为2。
     *
     * <p>注意:
     *
     * <p>3 <= points.length <= 50. 不存在重复的点。 -50 <= points[i][j] <= 50. 结果误差值在 10^-6 以内都认为是正确答案。
     *
     * @param points
     * @return
     */
    public double largestTriangleArea(int[][] points) {
        int len = points.length;

        double max = 0;
        for (int i = 0; i < len - 2; i++) {
            for (int j = i + 1; j < len - 1; j++) {
                for (int k = j + 1; k < len; k++) {
                    int[] point1 = points[i];
                    int[] point2 = points[j];
                    int[] point3 = points[k];
                    double area = getArea(point1, point2, point3);
                    log.debug("area:{}", area);
                    if (area > max) {
                        max = area;
                    }
                }
            }
        }
        return max;
    }

    private double getArea(int[] point1, int[] point2, int[] point3) {
        int x1 = point1[0], y1 = point1[1];
        int x2 = point2[0], y2 = point2[1];
        int x3 = point3[0], y3 = point3[1];
        double result = 0.5 * (x1 * y3 + x2 * y1 + x3 * y2 - x1 * y2 - x2 * y3 - x3 * y1);
        // sum = 1/2 (x1*y3 + x2*y1 + x3*y2 - x1*y2 - x2*y3 - x3*y1)

        if (result < 0) {
            result = -result;
        }
        return result;
    }

    @Test
    public void largestPerimeter() {
        int[] A = {3, 9, 2, 5, 2, 19};
        logResult(largestPerimeter(A));
    }

    /**
     * 976. 三角形的最大周长 给定由一些正数（代表长度）组成的数组 A，返回由其中三个长度组成的、面积不为零的三角形的最大周长。
     *
     * <p>如果不能形成任何面积不为零的三角形，返回 0。
     *
     * <p>示例 1：
     *
     * <p>输入：[2,1,2] 输出：5 示例 2：
     *
     * <p>输入：[1,2,1] 输出：0 示例 3：
     *
     * <p>输入：[3,2,3,4] 输出：10 示例 4：
     *
     * <p>输入：[3,6,2,3] 输出：8
     *
     * <p>提示：
     *
     * <p>3 <= A.length <= 10000 1 <= A[i] <= 10^6
     *
     * @param A
     * @return
     */
    public int largestPerimeter(int[] A) {

        Arrays.sort(A);
        int max = 0;
        for (int i = A.length - 1; i >= 2; i--) {
            int a = A[i], b = A[i - 1], c = A[i - 2];
            if (b + c > a) {
                max = a + b + c;
                break;
            }
        }
        return max;
    }

    /**
     * 942. 增减字符串匹配 给定只含 "I"（增大）或 "D"（减小）的字符串 S ，令 N = S.length。
     *
     * <p>返回 [0, 1, ..., N] 的任意排列 A 使得对于所有 i = 0, ..., N-1，都有：
     *
     * <p>如果 S[i] == "I"，那么 A[i] < A[i+1] 如果 S[i] == "D"，那么 A[i] > A[i+1]
     *
     * <p>示例 1：
     *
     * <p>输出："IDID" 输出：[0,4,1,3,2] 示例 2：
     *
     * <p>输出："III" 输出：[0,1,2,3] 示例 3：
     *
     * <p>输出："DDI" 输出：[3,2,0,1]
     *
     * <p>提示：
     *
     * <p>1 <= S.length <= 10000 S 只包含字符 "I" 或 "D"。
     *
     * @param S
     * @return
     */
    public int[] diStringMatch(String S) {
        int n = S.length();
        int low = 0, high = n;
        int[] result = new int[n + 1];

        for (int i = 0; i < n; i++) {
            char c = S.charAt(i);
            // 取最大值
            if (c == 'D') {
                result[i] = high--;
            }
            // 取最小值
            if (c == 'I') {
                result[i] = low++;
            }
        }
        result[n] = low;

        return result;
    }

    /**
     * 1033. 移动石子直到连续 三枚石子放置在数轴上，位置分别为 a，b，c。
     *
     * <p>每一回合，我们假设这三枚石子当前分别位于位置 x, y, z 且 x < y < z。 从位置 x 或者是位置 z 拿起一枚石子，并将该石子移动到某一整数位置 k 处，其中 x <
     * k < z 且 k != y。
     *
     * <p>当你无法进行任何移动时，即，这些石子的位置连续时，游戏结束。
     *
     * <p>要使游戏结束，你可以执行的最小和最大移动次数分别是多少？ 以长度为 2 的数组形式返回答案：answer = [minimum_moves, maximum_moves]
     *
     * <p>示例 1：
     *
     * <p>输入：a = 1, b = 2, c = 5 输出：[1, 2] 解释：将石子从 5 移动到 4 再移动到 3，或者我们可以直接将石子移动到 3。 示例 2：
     *
     * <p>输入：a = 4, b = 3, c = 2 输出：[0, 0] 解释：我们无法进行任何移动。
     *
     * <p>提示：
     *
     * <p>1 <= a <= 100 1 <= b <= 100 1 <= c <= 100 a != b, b != c, c != a
     *
     * @param a
     * @param b
     * @param c
     * @return
     */
    public int[] numMovesStones(int a, int b, int c) {
        int[] nums = {a, b, c};
        Arrays.sort(nums);
        a = nums[0];
        b = nums[1];
        c = nums[2];
        int len1 = b - a, len2 = c - b;
        int min = 0;
        int max = 0;
        int[] result = new int[2];
        if (len1 == 1 && len2 == 1) {
            return result;
        } else if (len1 == 1 || len2 == 1) {
            min = 1;
            max = Math.max(len1, len2) - 1;
        } else {
            min = Math.min(len1, len2) <= 2 ? 1 : 2;
            max = len1 + len2 - 2;
        }
        result[0] = min;
        result[1] = max;
        return result;
    }

    /**
     * 面试题 16.05. 阶乘尾数 设计一个算法，算出 n 阶乘有多少个尾随零。
     *
     * <p>示例 1:
     *
     * <p>输入: 3 输出: 0 解释: 3! = 6, 尾数中没有零。 示例 2:
     *
     * <p>输入: 5 输出: 1 解释: 5! = 120, 尾数中有 1 个零. 说明: 你算法的时间复杂度应为 O(log n) 。
     *
     * @param n
     * @return
     */
    public int trailingZeroes2(int n) {

        int count = 0;
        for (int i = 5; i <= n; i += 5) {
            count++;
        }
        return count;
    }

    /**
     * 面试题 17.01. 不用加号的加法 设计一个函数把两个数字相加。不得使用 + 或者其他算术运算符。
     *
     * <p>示例:
     *
     * <p>输入: a = 1, b = 1 输出: 2
     *
     * <p>提示：
     *
     * <p>a, b 均可能是负数或 0 结果不会溢出 32 位整数
     *
     * @param a
     * @param b
     * @return
     */
    public int add(int a, int b) {
        // 10101  10001
        // 相同位是1 都需要进位  使用 & 运算
        // 其它位保存不变,使用 ^ 运算(相同位都是0 无影响)

        while (b != 0) {
            int sum = (a ^ b);
            int carry = (a & b) << 1;
            a = sum;
            b = carry;
        }

        return a;
    }

    /**
     * 面试题 16.07. 最大数值 编写一个方法，找出两个数字a和b中最大的那一个。不得使用if-else或其他比较运算符。
     *
     * <p>示例：
     *
     * <p>输入： a = 1, b = 2 输出： 2
     *
     * @param a
     * @param b
     * @return
     */
    public int maximum(int a, int b) {
        // 正数最高为0，负数最高为1
        int i = (int) (((long) a - (long) b) >>> 63);

        return (i ^ 1) * a + i * b;
    }

    @Test
    public void hanota() {
        List<Integer> A = new ArrayList<>(), B = new ArrayList<>(), C = new ArrayList<>();
        A.add(4);
        A.add(3);
        A.add(2);
        A.add(1);
        hanota(A, B, C);
        logResult(A);
        logResult(B);
        logResult(C);
    }
    /**
     * 面试题 08.06. 汉诺塔问题 在经典汉诺塔问题中，有 3 根柱子及 N 个不同大小的穿孔圆盘，盘子可以滑入任意一根柱子。
     * 一开始，所有盘子自上而下按升序依次套在第一根柱子上(即每一个盘子只能放在更大的盘子上面)。移动圆盘时受到以下限制: (1) 每次只能移动一个盘子; (2)
     * 盘子只能从柱子顶端滑出移到下一根柱子; (3) 盘子只能叠在比它大的盘子上。
     *
     * <p>请编写程序，用栈将所有盘子从第一根柱子移到最后一根柱子。
     *
     * <p>你需要原地修改栈。
     *
     * <p>示例1:
     *
     * <p>输入：A = [2, 1, 0], B = [], C = [] 输出：C = [2, 1, 0] 示例2:
     *
     * <p>输入：A = [1, 0], B = [], C = [] 输出：C = [1, 0] 提示:
     *
     * <p>A中盘子的数目不大于14个。
     *
     * @param A
     * @param B
     * @param C
     */
    public void hanota(List<Integer> A, List<Integer> B, List<Integer> C) {
        int size = A.size();
        // A (n - 1) 移到 B
        hanota(A, B, C, size);
    }

    public void hanota(List<Integer> A, List<Integer> B, List<Integer> C, int len) {
        if (len == 1) {
            C.add(A.remove(A.size() - 1));
            return;
        }
        // A (n - 1) 移到 B
        hanota(A, C, B, len - 1);
        C.add(A.remove(A.size() - 1));
        // B (n - 1) 移动到 C
        hanota(B, A, C, len - 1);
    }

    /**
     * 面试题 17.04. 消失的数字 数组nums包含从0到n的所有整数，但其中缺了一个。请编写代码找出那个缺失的整数。你有办法在O(n)时间内完成吗？
     *
     * <p>注意：本题相对书上原题稍作改动
     *
     * <p>示例 1：
     *
     * <p>输入：[3,0,1] 输出：2
     *
     * <p>示例 2：
     *
     * <p>输入：[9,6,4,2,3,5,7,0,1] 输出：8
     *
     * @param nums
     * @return
     */
    public int missingNumber(int[] nums) {
        int n = nums.length;
        int sum = n;
        for (int i = 0; i < n; i++) {
            sum ^= i;
            sum ^= nums[i];
        }
        return sum;
    }

    /**
     * 面试题60. n个骰子的点数 把n个骰子扔在地上，所有骰子朝上一面的点数之和为s。输入n，打印出s的所有可能的值出现的概率。
     *
     * <p>你需要用一个浮点数数组返回答案，其中第 i 个元素代表这 n 个骰子所能掷出的点数集合中第 i 小的那个的概率。
     *
     * <p>示例 1:
     *
     * <p>输入: 1 输出: [0.16667,0.16667,0.16667,0.16667,0.16667,0.16667] 示例 2:
     *
     * <p>输入: 2 输出:
     * [0.02778,0.05556,0.08333,0.11111,0.13889,0.16667,0.13889,0.11111,0.08333,0.05556,0.02778]
     *
     * <p>限制：
     *
     * <p>1 <= n <= 11
     *
     * @param n
     * @return
     */
    public double[] twoSum(int n) {
        int[][] dp = new int[n + 1][6 * n + 1];
        // 边界条件
        for (int s = 1; s <= 6; s++) dp[1][s] = 1;
        for (int i = 2; i <= n; i++) {
            for (int s = i; s <= 6 * i; s++) {
                // 求dp[i][s]
                for (int d = 1; d <= 6; d++) {
                    if (s - d < i - 1) break; // 为0了
                    dp[i][s] += dp[i - 1][s - d];
                }
            }
        }
        double total = Math.pow((double) 6, (double) n);
        double[] ans = new double[5 * n + 1];
        for (int i = n; i <= 6 * n; i++) {
            ans[i - n] = ((double) dp[n][i]) / total;
        }
        return ans;
    }

    @Test
    public void convertInteger() {
        int A = 29, B = 15;
        logResult(convertInteger(A, B));
    }

    /**
     * 面试题 05.06. 整数转换 整数转换。编写一个函数，确定需要改变几个位才能将整数A转成整数B。
     *
     * <p>示例1:
     *
     * <p>输入：A = 29 （或者0b11101）, B = 15（或者0b01111） 输出：2 示例2:
     *
     * <p>输入：A = 1，B = 2 输出：2 提示:
     *
     * <p>A，B范围在[-2147483648, 2147483647]之间
     *
     * @param A
     * @param B
     * @return
     */
    public int convertInteger(int A, int B) {
        int num = A ^ B;
        return Integer.bitCount(num);
    }

    /**
     * 面试题 05.01. 插入 插入。给定两个32位的整数N与M，以及表示比特位置的i与j。编写一种方法，
     * 将M插入N，使得M从N的第j位开始，到第i位结束。假定从j位到i位足以容纳M，也即若M = 10 011，那么j和i之间至少可容纳5个位。例如，不可能出现j = 3和i =
     * 2的情况，因为第3位和第2位之间放不下M。
     *
     * <p>示例1:
     *
     * <p>输入：N = 10000000000, M = 10011, i = 2, j = 6 输出：N = 10001001100 示例2:
     *
     * <p>输入： N = 0, M = 11111, i = 0, j = 4 输出：N = 11111
     *
     * @param N
     * @param M
     * @param i
     * @param j
     * @return
     */
    public int insertBits(int N, int M, int i, int j) {

        int mask = (1 << j - i + 1) - 1 << i;
        return (N & ~mask) | M << i;
        /*
        int a = 0;
        for (int k = i; k <= j; k++) {
            a = (a << 1) + 1;
        }

        int m = M & a;
        m <<= i;
        return N + m;*/
    }

    @Test
    public void exchangeBits() {
        int num = 3;
        logResult(exchangeBits(num));
    }

    /**
     * 面试题 05.07. 配对交换 配对交换。编写程序，交换某个整数的奇数位和偶数位，尽量使用较少的指令（也就是说，位0与位1交换，位2与位3交换，以此类推）。
     *
     * <p>示例1:
     *
     * <p>输入：num = 2（或者0b10） 输出 1 (或者 0b01) 示例2:
     *
     * <p>输入：num = 3 输出：3 提示:
     *
     * <p>num的范围在[0, 2^30 - 1]之间，不会发生整数溢出。
     *
     * @param num
     * @return
     */
    public int exchangeBits(int num) {
        int result = 0;
        int bit = 0;
        while (num > 0) {
            int a = num & 1;
            num >>= 1;
            int b = num & 1;
            num >>= 1;

            int c = (a << 1) + b;
            log.debug("a:{},b:{},c:{}", a, b, c);
            result += (c << bit);
            bit += 2;
        }
        return result;
    }

    /**
     * 面试题 05.03. 翻转数位 给定一个32位整数 num，你可以将一个数位从0变为1。请编写一个程序，找出你能够获得的最长的一串1的长度。
     *
     * <p>示例 1：
     *
     * <p>输入: num = 1775(110111011112) 输出: 8 示例 2：
     *
     * <p>输入: num = 7(01112) 输出: 4
     *
     * @param num
     * @return
     */
    public int reverseBits(int num) {
        /*int max = 0;
        int count = 0;
        int countPre = 0;

        while (num > 0) {
            int a = num & 1;

            if (a == 1) {
                count++;
            } else  if ( (count + countPre + 1) > max ) {
                max = count + countPre + 1;
                countPre = count;
                count = 0;

            }


            num >>= 1;
        }
        if ( (count + countPre + 1) > max ) {
            max = count + countPre + 1;
        }
        return max;*/
        int count = 1;
        int pos = -1;
        int max = 0;
        for (int i = 0; i < 33; i++) {
            if ((num & 1) == 1) {
                count++;
            } else {
                max = Math.max(max, count);
                count = i - pos;
                pos = i;
            }
            num >>>= 1;
        }

        return max;
    }

    /**
     * 面试题56 - I. 数组中数字出现的次数 一个整型数组 nums
     * 里除两个数字之外，其他数字都出现了两次。请写程序找出这两个只出现一次的数字。要求时间复杂度是O(n)，空间复杂度是O(1)。
     *
     * <p>示例 1：
     *
     * <p>输入：nums = [4,1,4,6] 输出：[1,6] 或 [6,1] 示例 2：
     *
     * <p>输入：nums = [1,2,10,4,1,4,3,3] 输出：[2,10] 或 [10,2]
     *
     * <p>限制：
     *
     * <p>2 <= nums <= 10000
     *
     * @param nums
     * @return
     */
    public int[] singleNumbers(int[] nums) {
        int[] result = new int[2];
        int num = 0;
        int ret = 0;
        for (int n : nums) ret ^= n;
        int div = 1;
        // div 是两个数字的差
        while ((div & ret) == 0) div <<= 1;
        int a = 0, b = 0;
        for (int n : nums)
            if ((div & n) == 0) a ^= n;
            else b ^= n;
        result[0] = a;
        result[1] = b;
        return result;
    }

    /**
     * 面试题56 - II. 数组中数字出现的次数 II 在一个数组 nums 中除一个数字只出现一次之外，其他数字都出现了三次。请找出那个只出现一次的数字。
     *
     * <p>示例 1：
     *
     * <p>输入：nums = [3,4,3,3] 输出：4 示例 2：
     *
     * <p>输入：nums = [9,1,7,9,7,9,7] 输出：1
     *
     * <p>限制：
     *
     * <p>1 <= nums.length <= 10000 1 <= nums[i] < 2^31
     *
     * @param nums
     * @return
     */
    public int singleNumber(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            int count = map.getOrDefault(num, 0);
            map.put(num, count + 1);
        }

        for (Integer key : map.keySet()) {
            if (map.get(key) == 1) {
                return key;
            }
        }
        return -1;
    }

    @Test
    public void countDigitOne() {
        int n = 13;
        logResult(countDigitOne(n));
    }
    /**
     * 面试题43. 1～n整数中1出现的次数 输入一个整数 n ，求1～n这n个整数的十进制表示中1出现的次数。
     *
     * <p>例如，输入12，1～12这些整数中包含1 的数字有1、10、11和12，1一共出现了5次。
     *
     * <p>示例 1：
     *
     * <p>输入：n = 12 输出：5 示例 2：
     *
     * <p>输入：n = 13 输出：6
     *
     * <p>限制：
     *
     * <p>1 <= n < 2^31 注意：本题与主站 233 题相同：https://leetcode-cn.com/problems/number-of-digit-one/
     *
     * @param n
     * @return
     */
    public int countDigitOne(int n) {
        int count = 0;

        /*for (int i = 1; i <= n; i++) {
            int num = i;
            while (num > 0) {
                if (num%10 == 1) {
                    count++;
                }
                num /= 10;
            }
        }*/

        for (long i = 1; i <= n; i *= 10) {}

        return count;
    }

    @Test
    public void nthUglyNumber() {
        int n = 10;
        logResult(nthUglyNumber(n));
    }

    /**
     * 面试题49. 丑数 我们把只包含因子 2、3 和 5 的数称作丑数（Ugly Number）。求按从小到大的顺序的第 n 个丑数。
     *
     * <p>示例:
     *
     * <p>输入: n = 10 输出: 12 解释: 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 是前 10 个丑数。 说明:
     *
     * <p>1 是丑数。 n 不超过1690。 注意：本题与主站 264 题相同：https://leetcode-cn.com/problems/ugly-number-ii/
     *
     * @param n
     * @return
     */
    public int nthUglyNumber(int n) {
        int[] nums = new int[n];
        if (n <= 5) {
            return n;
        }
        nums[0] = 1;
        int index = 1;
        int a = 0, b = 0, c = 0;
        while (index < n) {
            nums[index] = Math.min(Math.min(nums[a] * 2, nums[b] * 3), nums[c] * 5);
            if (nums[index] == nums[a] * 2) {
                a++;
            }
            if (nums[index] == nums[b] * 3) {
                b++;
            }
            if (nums[index] == nums[c] * 5) {
                c++;
            }
            index++;
        }
        /*int indexs[] = new int[3];
        int uglys[] = new int[]{2,3,5};

        while (index < n) {
            int minIndex = getMinIndex(nums,indexs,uglys);
            int num = nums[indexs[minIndex]++] * uglys[minIndex];
            if (num <= nums[index - 1]) {
                continue;
            }
            nums[index++] = num;
        }*/
        log.debug("nums:{}", nums);
        return nums[n - 1];
    }

    /**
     * 313. 超级丑数 编写一段程序来查找第 n 个超级丑数。
     *
     * <p>超级丑数是指其所有质因数都是长度为 k 的质数列表 primes 中的正整数。
     *
     * <p>示例:
     *
     * <p>输入: n = 12, primes = [2,7,13,19] 输出: 32 解释: 给定长度为 4 的质数列表 primes = [2,7,13,19]，前 12
     * 个超级丑数序列为：[1,2,4,7,8,13,14,16,19,26,28,32] 。 说明:
     *
     * <p>1 是任何给定 primes 的超级丑数。 给定 primes 中的数字以升序排列。 0 < k ≤ 100, 0 < n ≤ 106, 0 < primes[i] < 1000
     * 。 第 n 个超级丑数确保在 32 位有符整数范围内。
     *
     * @param n
     * @param primes
     * @return
     */
    public int nthSuperUglyNumber(int n, int[] primes) {
        int[] nums = new int[n];
        nums[0] = 1;
        int index = 1;
        int indexs[] = new int[primes.length];
        while (index < n) {
            int minIndex = getMinIndex(nums, indexs, primes);
            int num = nums[indexs[minIndex]++] * primes[minIndex];
            if (num <= nums[index - 1]) {
                continue;
            }
            nums[index++] = num;
        }

        return nums[n - 1];
    }

    private int getMinIndex(int[] nums, int[] indexs, int[] uglys) {
        int min = Integer.MAX_VALUE;
        int minIndex = -1;

        for (int i = 0; i < indexs.length; i++) {
            int index = indexs[i];
            int num = nums[index] * uglys[i];
            if (num < min) {
                min = num;
                minIndex = i;
            }
        }
        return minIndex;
    }

    @Test
    public void findNthDigit() {
        int n = 20;
        logResult(findNthDigit(n));
    }

    /**
     * 面试题44. 数字序列中某一位的数字
     * 数字以0123456789101112131415…的格式序列化到一个字符序列中。在这个序列中，第5位（从下标0开始计数）是5，第13位是1，第19位是4，等等。
     *
     * <p>请写一个函数，求任意第n位对应的数字。
     *
     * <p>示例 1：
     *
     * <p>输入：n = 3 输出：3 示例 2：
     *
     * <p>输入：n = 11 输出：0
     *
     * <p>限制：
     *
     * <p>0 <= n < 2^31 注意：本题与主站 400 题相同：https://leetcode-cn.com/problems/nth-digit/
     *
     * <p>通过次数5,401提交次数14,497
     *
     * @param n
     * @return
     */
    public int findNthDigit(int n) {
        int result = -1;
        if (n < 10) {
            return n;
        }
        int count = 1;
        int tmp = 1;
        while (n - 9 * tmp * count > 0) {
            n -= 9 * tmp * count;
            tmp *= 10;
            count++;
        }
        log.debug("n：{}, count:{}", n, count);
        // 在 count 位数中的第n位  2位数的第一位 1 第2位 0
        int num = (n - 1) % count;
        int k = (n - 1) / count;
        for (int i = 0; i < count - num - 1; i++) {
            k = k / 10;
        }
        result = k % 10;
        if (num == 0) {
            result++;
        }

        // count 标识 n 处于的区域是 几位数
        return result;

        /*int digit = 1;
        long start = 1;
        long count = 9;
        while (n > count) { // 1.
            n -= count;
            digit += 1;
            start *= 10;
            count = digit * start * 9;
        }
        long num = start + (n - 1) / digit; // 2.
        return Long.toString(num).charAt((n - 1) % digit) - '0'; // 3. */
    }

    /**
     * 137. 只出现一次的数字 II 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现了三次。找出那个只出现了一次的元素。
     *
     * <p>说明：
     *
     * <p>你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？
     *
     * <p>示例 1:
     *
     * <p>输入: [2,2,3,2] 输出: 3 示例 2:
     *
     * <p>输入: [0,1,0,1,0,1,99] 输出: 99
     *
     * @param nums
     * @return
     */
    public int singleNumber2(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            int count = map.getOrDefault(num, 0);
            map.put(num, count + 1);
        }

        for (Integer key : map.keySet()) {
            if (map.get(key) == 1) {
                return key;
            }
        }
        return -1;
    }

    /**
     * 223. 矩形面积 在二维平面上计算出两个由直线构成的矩形重叠后形成的总面积。
     *
     * <p>每个矩形由其左下顶点和右上顶点坐标表示，如图所示。
     *
     * <p>Rectangle Area
     *
     * <p>示例:
     *
     * <p>输入: -3, 0, 3, 4, 0, -1, 9, 2 输出: 45 说明: 假设矩形面积不会超出 int 的范围。
     *
     * @param A
     * @param B
     * @param C
     * @param D
     * @param E
     * @param F
     * @param G
     * @param H
     * @return
     */
    public int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {

        int x1 = Math.max(E, A);
        int y1 = Math.max(B, F);

        int x2 = Math.min(C, G);
        int y2 = Math.min(D, H);
        int area1 = (C - A) * (D - B);
        int area2 = (G - E) * (H - F);
        int area3 = 0;
        if (E >= C || G <= A) {
        } else if (F >= D || H <= B) {
        } else {
            area3 = (y2 - y1) * (x2 - x1);
        }
        return area1 + area2 - area3;
    }

    /**
     * 201. 数字范围按位与 给定范围 [m, n]，其中 0 <= m <= n <= 2147483647，返回此范围内所有数字的按位与（包含 m, n 两端点）。
     *
     * <p>示例 1:
     *
     * <p>输入: [5,7] 输出: 4 示例 2:
     *
     * <p>输入: [0,1] 输出: 0
     *
     * @param m
     * @param n
     * @return
     */
    public int rangeBitwiseAnd(int m, int n) {
        int count = 0;

        // n > m 表示最低位经过&运算一定是0（只有有两个连续的数 最低位一定是0和1）
        while (n > m && m > 0) {
            count++;
            n >>= 1;
            m >>= 1;
        }
        return m << count;
    }

    /**
     * 338. 比特位计数 给定一个非负整数 num。对于 0 ≤ i ≤ num 范围中的每个数字 i ，计算其二进制数中的 1 的数目并将它们作为数组返回。
     *
     * <p>示例 1:
     *
     * <p>输入: 2 输出: [0,1,1] 示例 2:
     *
     * <p>输入: 5 输出: [0,1,1,2,1,2] 进阶:
     *
     * <p>给出时间复杂度为O(n*sizeof(integer))的解答非常容易。但你可以在线性时间O(n)内用一趟扫描做到吗？ 要求算法的空间复杂度为O(n)。
     * 你能进一步完善解法吗？要求在C++或任何其他语言中不使用任何内置函数（如 C++ 中的 __builtin_popcount）来执行此操作。
     *
     * @param num
     * @return
     */
    public int[] countBits(int num) {
        int[] result = new int[num + 1];

        for (int i = 1; i <= num; i++) {
            result[i] = result[i >> 1] + (i & 1);
        }

        return result;
    }

    /**
     * 343. 整数拆分 给定一个正整数 n，将其拆分为至少两个正整数的和，并使这些整数的乘积最大化。 返回你可以获得的最大乘积。
     *
     * <p>示例 1:
     *
     * <p>输入: 2 输出: 1 解释: 2 = 1 + 1, 1 × 1 = 1。 示例 2:
     *
     * <p>输入: 10 输出: 36 解释: 10 = 3 + 3 + 4, 3 × 3 × 4 = 36。 说明: 你可以假设 n 不小于 2 且不大于 58。
     *
     * @param n
     * @return
     */
    public int integerBreak(int n) {
        if (n <= 3) {
            return n - 1;
        }
        if (n == 4) {
            return 4;
        }
        int result = 1;
        while (n > 3) {
            if (n == 4) {
                break;
            }
            n -= 3;
            result *= 3;
        }
        if (n > 0) {
            result *= n;
        }
        return result;
    }

    @Test
    public void findNthDigit2() {
        int n = 1000000000;
        logResult(findNthDigit2(n));
    }

    /**
     * 400. 第N个数字 在无限的整数序列 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ...中找到第 n 个数字。
     *
     * <p>注意: n 是正数且在32位整数范围内 ( n < 231)。
     *
     * <p>示例 1:
     *
     * <p>输入: 3
     *
     * <p>输出: 3 示例 2:
     *
     * <p>输入: 11
     *
     * <p>输出: 0
     *
     * <p>说明: 第11个数字在序列 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ... 里是0，它是10的一部分。
     *
     * @param n
     * @return
     */
    public int findNthDigit2(int n) {
        int digit = 1;
        long start = 1;
        long count = 9;
        while (n > count) { // 1.
            n -= count;
            digit += 1;
            start *= 10;
            count = digit * start * 9;
        }
        long num = start + (n - 1) / digit; // 2.
        return Long.toString(num).charAt((n - 1) % digit) - '0'; // 3.
    }

    /**
     * 397. 整数替换 给定一个正整数 n，你可以做如下操作：
     *
     * <p>1. 如果 n 是偶数，则用 n / 2替换 n。 2. 如果 n 是奇数，则可以用 n + 1或n - 1替换 n。 n 变为 1 所需的最小替换次数是多少？
     *
     * <p>示例 1:
     *
     * <p>输入: 8
     *
     * <p>输出: 3
     *
     * <p>解释: 8 -> 4 -> 2 -> 1 示例 2:
     *
     * <p>输入: 7
     *
     * <p>输出: 4
     *
     * <p>解释: 7 -> 8 -> 4 -> 2 -> 1 或 7 -> 6 -> 3 -> 2 -> 1
     *
     * @param n
     * @return
     */
    public int integerReplacement(int n) {
        if (n == 1) return 0;
        if (n == Integer.MAX_VALUE) return 32;
        if ((n & 1) == 0) {
            return 1 + integerReplacement(n >> 1);
        } else {
            return 1 + Math.min(integerReplacement(n + 1), integerReplacement(n - 1));
        }
    }

    @Test
    public void simplifiedFractions() {
        int n = 4;
        List<String> result = simplifiedFractions(n);
        logResult(result);
    }
    /**
     * 5397. 最简分数 显示英文描述 给你一个整数 n ，请你返回所有 0 到 1 之间（不包括 0 和 1）满足分母小于等于 n 的 最简 分数 。分数可以以 任意 顺序返回。
     *
     * <p>示例 1：
     *
     * <p>输入：n = 2 输出：["1/2"] 解释："1/2" 是唯一一个分母小于等于 2 的最简分数。 示例 2：
     *
     * <p>输入：n = 3 输出：["1/2","1/3","2/3"] 示例 3：
     *
     * <p>输入：n = 4 输出：["1/2","1/3","1/4","2/3","3/4"] 解释："2/4" 不是最简分数，因为它可以化简为 "1/2" 。 示例 4：
     *
     * <p>输入：n = 1 输出：[]
     *
     * @param n
     * @return
     */
    public List<String> simplifiedFractions(int n) {
        List<String> result = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < n; i++) {
            for (int j = i + 1; j <= n; j++) {
                if (getGcd(i, j) != 1) {
                    continue;
                }

                sb.setLength(0);
                sb.append(i).append('/').append(j);
                result.add(sb.toString());
            }
        }

        return result;
    }

    /**
     * 241. 为运算表达式设计优先级 给定一个含有数字和运算符的字符串，为表达式添加括号，改变其运算优先级以求出不同的结果。 你需要给出所有可能的组合的结果。有效的运算符号包含 +, -
     * 以及 * 。
     *
     * <p>示例 1:
     *
     * <p>输入: "2-1-1" 输出: [0, 2] 解释: ((2-1)-1) = 0 (2-(1-1)) = 2 示例 2:
     *
     * <p>输入: "2*3-4*5" 输出: [-34, -14, -10, -10, 10] 解释: (2*(3-(4*5))) = -34 ((2*3)-(4*5)) = -14
     * ((2*(3-4))*5) = -10 (2*((3-4)*5)) = -10 (((2*3)-4)*5) = 10
     *
     * @param input
     * @return
     */
    public List<Integer> diffWaysToCompute(String input) {

        if (input.length() == 0) {
            return Collections.emptyList();
        }
        List<Integer> result = new ArrayList<>();
        int index = 0, num = 0;
        while (index < input.length() && !isOperation(input.charAt(index))) {
            num = num * 10 + (input.charAt(index) - '0');
            index++;
        }
        if (index == input.length()) {
            result.add(num);
            return result;
        }
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (!isOperation(c)) {
                continue;
            }
            List<Integer> list1 = diffWaysToCompute(input.substring(0, i));
            List<Integer> list2 = diffWaysToCompute(input.substring(i + 1));

            for (int j = 0; j < list1.size(); j++) {
                for (int k = 0; k < list2.size(); k++) {
                    result.add(calculate(list1.get(j), c, list2.get(k)));
                }
            }
        }

        return result;
    }

    private boolean isOperation(char c) {
        return c == '+' || c == '-' || c == '*';
    }

    private int calculate(int num1, char c, int num2) {
        switch (c) {
            case '+':
                return num1 + num2;
            case '-':
                return num1 - num2;
            case '*':
                return num1 * num2;
        }
        return -1;
    }

    /**
     * 318. 最大单词长度乘积 给定一个字符串数组 words，找到 length(word[i]) * length(word[j]) 的最大值，
     * 并且这两个单词不含有公共字母。你可以认为每个单词只包含小写字母。如果不存在这样的两个单词，返回 0。
     *
     * <p>示例 1:
     *
     * <p>输入: ["abcw","baz","foo","bar","xtfn","abcdef"] 输出: 16 解释: 这两个单词为 "abcw", "xtfn"。 示例 2:
     *
     * <p>输入: ["a","ab","abc","d","cd","bcd","abcd"] 输出: 4 解释: 这两个单词为 "ab", "cd"。 示例 3:
     *
     * <p>输入: ["a","aa","aaa","aaaa"] 输出: 0 解释: 不存在这样的两个单词。
     *
     * @param words
     * @return
     */
    public int maxProduct(String[] words) {
        int[] bits = new int[words.length];
        for (int i = 0; i < words.length; i++) {
            for (char c : words[i].toCharArray()) {
                bits[i] |= 1 << (c - 'a');
            }
        }
        int max = 0;
        for (int i = 0; i < words.length - 1; i++) {
            for (int j = i + 1; j < words.length; j++) {
                if ((bits[i] & bits[j]) == 0) {
                    max = Math.max(max, words[i].length() * words[j].length());
                }
            }
        }
        return max;
    }

    /**
     * 319. 灯泡开关 初始时有 n 个灯泡关闭。 第 1 轮，你打开所有的灯泡。 第 2 轮，每两个灯泡你关闭一次。 第 3 轮，每三个灯泡切换一次开关（如果关闭则开启，如果开启则关闭）。
     * 第 i 轮，每 i 个灯泡切换一次开关。 对于第 n 轮，你只切换最后一个灯泡的开关。 找出 n 轮后有多少个亮着的灯泡。
     *
     * <p>示例:
     *
     * <p>输入: 3 输出: 1 解释: 初始时, 灯泡状态 [关闭, 关闭, 关闭]. 第一轮后, 灯泡状态 [开启, 开启, 开启]. 第二轮后, 灯泡状态 [开启, 关闭, 开启].
     * 第三轮后, 灯泡状态 [开启, 关闭, 关闭].
     *
     * <p>你应该返回 1，因为只有一个灯泡还亮着。
     *
     * @param n
     * @return
     */
    public int bulbSwitch(int n) {
        // 除了完全平方数，因数都是成对出现的，这意味着实际起到翻转作用(0->1)的，只有 完全平方数而已。
        return (int) Math.sqrt(n);
    }

    @Test
    public void superPow() {
        int a = Integer.MAX_VALUE;
        int[] b = {2, 0, 0};
        logResult(superPow(a, b));
    }

    /**
     * 372. 超级次方 你的任务是计算 ab 对 1337 取模，a 是一个正整数，b 是一个非常大的正整数且会以数组形式给出。
     *
     * <p>示例 1:
     *
     * <p>输入: a = 2, b = [3] 输出: 8 示例 2:
     *
     * <p>输入: a = 2, b = [1,0] 输出: 1024
     *
     * @param a
     * @param b
     * @return
     */
    public int superPow(int a, int[] b) {
        a %= 1337;
        int result = mypow(a, b[0]);
        for (int i = 1; i < b.length; i++) {

            result = mypow(result, 10) * mypow(a, b[i]) % 1337;
        }

        return result;
    }

    private int mypow(int x, int n) {
        if (n == 0) {
            return 1;
        }
        int result = 1;
        for (int i = n; i != 0; i >>= 1) {
            if ((i & 1) == 1) {
                result *= x;
                result %= 1337;
            }
            x *= x;
            x %= 1337;
        }
        return result;
    }

    @Test
    public void maxRotateFunction() {
        int[] a = {4, 3, 2, 6};
        logResult(maxRotateFunction(a));
    }

    /**
     * 396. 旋转函数 给定一个长度为 n 的整数数组 A 。
     *
     * <p>假设 Bk 是数组 A 顺时针旋转 k 个位置后的数组，我们定义 A 的“旋转函数” F 为：
     *
     * <p>F(k) = 0 * Bk[0] + 1 * Bk[1] + ... + (n-1) * Bk[n-1]。
     *
     * <p>计算F(0), F(1), ..., F(n-1)中的最大值。
     *
     * <p>注意: 可以认为 n 的值小于 105。
     *
     * <p>示例:
     *
     * <p>A = [4, 3, 2, 6]
     *
     * <p>F(0) = (0 * 4) + (1 * 3) + (2 * 2) + (3 * 6) = 0 + 3 + 4 + 18 = 25 F(1) = (0 * 6) + (1 *
     * 4) + (2 * 3) + (3 * 2) = 0 + 4 + 6 + 6 = 16 F(2) = (0 * 2) + (1 * 6) + (2 * 4) + (3 * 3) = 0
     * + 6 + 8 + 9 = 23 F(3) = (0 * 3) + (1 * 2) + (2 * 6) + (3 * 4) = 0 + 2 + 12 + 12 = 26
     *
     * <p>所以 F(0), F(1), F(2), F(3) 中的最大值是 F(3) = 26 。
     *
     * @param A
     * @return
     */
    public int maxRotateFunction(int[] A) {
        int len = A.length;
        int sum = 0;
        int num = 0;
        for (int i = 0; i < len; i++) {
            num += i * A[i];
            sum += A[i];
        }

        int max = num;
        for (int i = len - 1; i > 0; i--) {
            num = num + sum - len * A[i];
            max = Math.max(max, num);
        }
        return max;
    }

    @Test
    public void lexicalOrder() {
        int n = 13;
        logResult(lexicalOrder(n));
    }

    /**
     * 386. 字典序排数 给定一个整数 n, 返回从 1 到 n 的字典顺序。
     *
     * <p>例如，
     *
     * <p>给定 n =1 3，返回 [1,10,11,12,13,2,3,4,5,6,7,8,9] 。
     *
     * <p>请尽可能的优化算法的时间复杂度和空间复杂度。 输入的数据 n 小于等于 5,000,000。
     *
     * @param n
     * @return
     */
    public List<Integer> lexicalOrder(int n) {
        List<Integer> result = new ArrayList<>(n);

        lexicalOrder(result, 0, n);
        return result;
    }

    private void lexicalOrder(List<Integer> list, int num, int n) {
        for (int i = 0; i <= 9; i++) {
            int a = num * 10 + i;
            if (a <= 0 || a > n) {
                continue;
            }
            list.add(a);
            lexicalOrder(list, a, n);
        }
    }

    /**
     * 390. 消除游戏 给定一个从1 到 n 排序的整数列表。 首先，从左到右，从第一个数字开始，每隔一个数字进行删除，直到列表的末尾。
     * 第二步，在剩下的数字中，从右到左，从倒数第一个数字开始，每隔一个数字进行删除，直到列表开头。 我们不断重复这两步，从左到右和从右到左交替进行，直到只剩下一个数字。 返回长度为 n
     * 的列表中，最后剩下的数字。
     *
     * <p>示例：
     *
     * <p>输入: n = 9, 1 2 3 4 5 6 7 8 9 2 4 6 8 2 6 6
     *
     * <p>输出: 6
     *
     * @param n
     * @return
     */
    public int lastRemaining(int n) {
        if (n == 1) {
            return 1;
        }
        int helf = n >> 1;
        return 2 * (helf + 1 - lastRemaining(helf));
    }

    /**
     * 421. 数组中两个数的最大异或值 给定一个非空数组，数组中元素为 a0, a1, a2, … , an-1，其中 0 ≤ ai < 231 。
     *
     * <p>找到 ai 和aj 最大的异或 (XOR) 运算结果，其中0 ≤ i, j < n 。
     *
     * <p>你能在O(n)的时间解决这个问题吗？
     *
     * <p>示例:
     *
     * <p>输入: [3, 10, 5, 25, 2, 8]
     *
     * <p>输出: 28
     *
     * <p>解释: 最大的结果是 5 ^ 25 = 28.
     *
     * @param nums
     * @return
     */
    public int findMaximumXOR(int[] nums) {
        // 1 暴力法
        int max = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                max = Math.max(max, nums[i] ^ nums[j]);
            }
        }
        return max;
    }

    /**
     * 423. 从英文中重建数字 给定一个非空字符串，其中包含字母顺序打乱的英文单词表示的数字0-9。按升序输出原始的数字。
     *
     * <p>注意:
     *
     * <p>输入只包含小写英文字母。 输入保证合法并可以转换为原始的数字，这意味着像 "abc" 或 "zerone" 的输入是不允许的。 输入字符串的长度小于 50,000。 示例 1:
     *
     * <p>输入: "owoztneoer"
     *
     * <p>输出: "012" (zeroonetwo) 示例 2:
     *
     * <p>输入: "fviefuro"
     *
     * <p>输出: "45" (fourfive)
     *
     * @param s
     * @return
     */
    public String originalDigits(String s) {

        StringBuilder sb = new StringBuilder();
        // 建立字符 到 数字的映射
        // z => 0 zero
        // w => 2 two
        // u => 4 four
        // x => 6 six
        // g => 8 eight
        // h => 3 - 8  three eight
        // f => 5 - 4  five four
        // s => 7 - 6  seven six
        // i => 9 - 5 - 6 - 8  nine five six eight
        // n => 1 - 7 - 2 * 9  one seven nine
        int[] letters = new int[26];
        for (char c : s.toCharArray()) {
            letters[c - 'a']++;
        }

        int[] counts = new int[10];
        counts[0] = letters['z' - 'a'];
        counts[2] = letters['w' - 'a'];
        counts[4] = letters['u' - 'a'];
        counts[6] = letters['x' - 'a'];
        counts[8] = letters['g' - 'a'];
        counts[3] = letters['h' - 'a'] - counts[8];
        counts[5] = letters['f' - 'a'] - counts[4];
        counts[7] = letters['s' - 'a'] - counts[6];
        counts[9] = letters['i' - 'a'] - counts[5] - counts[6] - counts[8];
        counts[1] = letters['n' - 'a'] - counts[7] - 2 * counts[9];

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < counts[i]; j++) {
                sb.append(i);
            }
        }

        return sb.toString();
    }

    @Test
    public void multiply() {
        int A = 7, B = 4;
        logResult(multiply(A, B));
    }

    /**
     * 面试题 08.05. 递归乘法
     *
     * <p>递归乘法。 写一个递归函数，不使用 * 运算符， 实现两个正整数的相乘。可以使用加号、减号、位移，但要吝啬一些。
     *
     * <p>示例1:
     *
     * <p>输入：A = 1, B = 10 输出：10 示例2:
     *
     * <p>输入：A = 3, B = 4 输出：12 提示:
     *
     * <p>保证乘法范围不会溢出 通过次数4,169提交次数6,212
     *
     * @param A
     * @param B
     * @return
     */
    public int multiply(int A, int B) {
        int max = Math.max(A, B);
        int min = Math.min(A, B);

        return multiply2(max, min);
    }

    private int multiply2(int max, int min) {
        if (min == 1) {
            return max;
        }
        int num = multiply2(max, min >> 1);
        int result = num << 1;
        if ((min & 1) == 1) {
            result += max;
        }

        return result;
    }

    /**
     * 面试题 05.04. 下一个数
     *
     * <p>下一个数。给定一个正整数，找出与其二进制表达式中1的个数相同且大小最接近的那两个数（一个略大，一个略小）。
     *
     * <p>示例1:
     *
     * <p>输入：num = 2（或者0b10） 输出：[4, 1] 或者（[0b100, 0b1]） 示例2:
     *
     * <p>输入：num = 1 输出：[2, -1] 提示:
     *
     * <p>num的范围在[1, 2147483647]之间； 如果找不到前一个或者后一个满足条件的正数，那么输出 -1。
     *
     * @param num
     * @return
     */
    public int[] findClosedNumbers(int num) {
        // 略大 从后往前, 找到 第一个0 和 1 ，互换位置
        // 略小 从前往后, 找到 第一个1 和 0
        // 比 num 大的数：从右往左，找到第一个 01 位置，然后把 01 转为 10，右侧剩下的 1 移到右侧的低位，右侧剩下的位清0。
        // 比 num 小的数：从右往左，找到第一个 10 位置，然后把 10 转为 01，右侧剩下的 1 移到右侧的高位，右侧剩下的位置0。
        int little = num - 1;
        int bigger = num + 1;
        int binNums = Integer.bitCount(num);
        while (Integer.bitCount(little) != binNums && little > 0) {
            little--;
        }
        while (Integer.bitCount(bigger) != binNums && bigger > 0) {
            bigger++;
        }
        return new int[] {bigger > 0 ? bigger : -1, little > 0 ? little : -1};
    }

    /**
     * 面试题 16.13. 平分正方形
     *
     * <p>给定两个正方形及一个二维平面。请找出将这两个正方形分割成两半的一条直线。假设正方形顶边和底边与 x 轴平行。
     *
     * <p>每个正方形的数据square包含3个数值，正方形的左下顶点坐标[X,Y] =
     * [square[0],square[1]]，以及正方形的边长square[2]。所求直线穿过两个正方形会形成4个交点，请返回4个交点形成线段的两端点坐标（两个端点即为4个交点中距离最远的2个点，这2个点所连成的线段一定会穿过另外2个交点）。2个端点坐标[X1,Y1]和[X2,Y2]的返回格式为{X1,Y1,X2,Y2}，要求若X1
     * != X2，需保证X1 < X2，否则需保证Y1 <= Y2。
     *
     * <p>若同时有多条直线满足要求，则选择斜率最大的一条计算并返回（与Y轴平行的直线视为斜率无穷大）。
     *
     * <p>示例：
     *
     * <p>输入： square1 = {-1, -1, 2} square2 = {0, -1, 2} 输出： {-1,0,2,0} 解释： 直线 y = 0
     * 能将两个正方形同时分为等面积的两部分，返回的两线段端点为[-1,0]和[2,0] 提示：
     *
     * <p>square.length == 3 square[2] > 0
     *
     * @param square1
     * @param square2
     * @return
     */
    public double[] cutSquares(int[] square1, int[] square2) {
        double[] result = new double[4];
        // 两个正方形的中心,连一条线 , 如果两个正方形中心一个点,返回y轴平行的线
        double x1 = (double) square1[0] + (double) square1[2] / 2;
        double y1 = (double) square1[1] + (double) square1[2] / 2;

        double x2 = (double) square2[0] + (double) square2[2] / 2;
        double y2 = (double) square2[1] + (double) square2[2] / 2;
        // 中心相同 或者 x1 == x2 ,返回y轴平行的线
        if (x1 == x2) {
            result[0] = x1;
            result[1] = Math.min(square1[1], square2[1]);
            result[2] = x1;
            result[3] = Math.max(square1[1] + square1[2], square2[1] + square2[2]);
            return result;
        }
        // y1 == y2 ,返回x轴平行的线
        if (y1 == y2) {
            result[0] = Math.min(square1[0], square2[0]);
            result[1] = y1;
            result[2] = Math.max(square1[0] + square1[2], square2[0] + square2[2]);
            result[3] = y1;
            return result;
        }
        double k = (y2 - y1) / (x2 - x1);

        // 判断斜率
        if (Math.abs(k) < 1.0) {
            // 小于1 与 y轴平行的边相交
            double leftX = Math.min(square1[0], square2[0]);
            double leftY = k * (leftX - x1) + y1;
            double rightX = Math.max(square1[0] + square1[2], square2[0] + square2[2]);
            double rightY = k * (rightX - x1) + y1;
            return new double[] {leftX, leftY, rightX, rightY};
        } else {
            // 大于1 与 x轴平行的边相交
            double bottonY = Math.min(square1[1], square2[1]);
            double topY = Math.max(square1[1] + square1[2], square2[1] + square2[2]);
            double bottonX = (bottonY - y1) / k + x1;
            double topX = (topY - y1) / k + x1;
            return bottonX < topX
                    ? new double[] {bottonX, bottonY, topX, topY}
                    : new double[] {topX, topY, bottonX, bottonY};
        }
    }

    /**
     * 面试题 16.08. 整数的英语表示 给定一个整数，打印该整数的英文描述。
     *
     * <p>示例 1:
     *
     * <p>输入: 123 输出: "One Hundred Twenty Three" 示例 2:
     *
     * <p>输入: 12345 输出: "Twelve Thousand Three Hundred Forty Five" 示例 3:
     *
     * <p>输入: 1234567 输出: "One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven" 示例
     * 4:
     *
     * <p>输入: 1234567891 输出: "One Billion Two Hundred Thirty Four Million Five Hundred Sixty Seven
     * Thousand Eight Hundred Ninety One"
     *
     * @param num
     * @return
     */
    public String numberToWords(int num) {
        if (num == 0) {
            return "Zero";
        }
        List<String> list = new ArrayList<>();

        numberToWords(num, list);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).length() == 0) {
                continue;
            }
            if (sb.length() > 0) {
                sb.append(" ");
            }
            sb.append(list.get(i));
        }
        log.debug("len:{}", sb.length());
        return sb.toString();
    }

    @Test
    public void testNumberToWords() {
        int num = 20;
        logResult(numberToWords(num));
    }

    private void numberToWords(int num, List<String> list) {
        int[] factors = {1000000000, 1000000, 1000};
        for (int i = 0; i < 3; i++) {
            if (num >= factors[i]) {
                int bigNum = num / factors[i];
                num = num % factors[i];

                numberToWords(bigNum, list);
                list.add(GENS[i]);
            }
        }
        if (num >= 100) {
            int hundredNum = num / 100;
            num = num % 100;
            numberToWords(hundredNum, list);
            list.add("Hundred");
        }
        if (num >= 20) {
            int tenIndex = num / 10;
            num = num % 10;
            list.add(NUM_0_90[tenIndex]);
        }
        list.add(NUM_0_19[num]);
    }

    static String[] NUM_0_19 = {
        "",
        "One",
        "Two",
        "Three",
        "Four",
        "Five",
        "Six",
        "Seven",
        "Eight",
        "Nine",
        "Ten",
        "Eleven",
        "Twelve",
        "Thirteen",
        "Fourteen",
        "Fifteen",
        "Sixteen",
        "Seventeen",
        "Eighteen",
        "Nineteen"
    };
    static String[] NUM_0_90 = {
        "", "", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"
    };
    static String[] GENS = {"Billion", "Million", "Thousand", ""};

    @Test
    public void missingTwo() {
        int[] nums = {1};
        logResult(missingTwo(nums));
    }
    /**
     * 面试题 17.19. 消失的两个数字
     *
     * <p>给定一个数组，包含从 1 到 N 所有的整数，但其中缺了两个数字。你能在 O(N) 时间内只用 O(1) 的空间找到它们吗？
     *
     * <p>以任意顺序返回这两个数字均可。
     *
     * <p>示例 1:
     *
     * <p>输入: [1] 输出: [2,3] 示例 2:
     *
     * <p>输入: [2,3] 输出: [1,4] 提示：
     *
     * <p>nums.length <= 30000
     *
     * @param nums
     * @return
     */
    public int[] missingTwo(int[] nums) {
        // 求和
        int n = nums.length + 2;
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        int twoSum = (n * (n + 1) >> 1) - sum;
        logResult(twoSum);
        int limit = twoSum >> 1;
        sum = 0;
        for (int num : nums) {
            if (num <= limit) {
                sum += num;
            }
        }
        int small = (limit * (limit + 1) >> 1) - sum;
        return new int[] {small, twoSum - small};
    }

    @Test
    public void xorOperation() {
        int n = 1, start = 7;

        logResult(xorOperation(n, start));
    }

    /**
     * 5440. 数组异或操作
     *
     * <p>给你两个整数，n 和 start 。
     *
     * <p>数组 nums 定义为：nums[i] = start + 2*i（下标从 0 开始）且 n == nums.length 。
     *
     * <p>请返回 nums 中所有元素按位异或（XOR）后得到的结果。
     *
     * <p>示例 1：
     *
     * <p>输入：n = 5, start = 0 输出：8 解释：数组 nums 为 [0, 2, 4, 6, 8]，其中 (0 ^ 2 ^ 4 ^ 6 ^ 8) = 8 。 "^"
     * 为按位异或 XOR 运算符。 示例 2：
     *
     * <p>输入：n = 4, start = 3 输出：8 解释：数组 nums 为 [3, 5, 7, 9]，其中 (3 ^ 5 ^ 7 ^ 9) = 8. 示例 3：
     *
     * <p>输入：n = 1, start = 7 输出：7 示例 4：
     *
     * <p>输入：n = 10, start = 5 输出：2
     *
     * <p>提示：
     *
     * <p>1 <= n <= 1000 0 <= start <= 1000 n == nums.length
     *
     * @param n
     * @param start
     * @return
     */
    public int xorOperation(int n, int start) {

        int num = start;
        for (int i = 1; i < n; i++) {
            num ^= (start + 2 * i);
        }
        return num;
    }

    @Test
    public void minMoves2() {
        int[] nums = {1, 2, 3, 4, 8};
        logResult(minMoves2(nums));
    }

    /**
     * 462. 最少移动次数使数组元素相等 II
     *
     * <p>给定一个非空整数数组，找到使所有数组元素相等所需的最小移动数，其中每次移动可将选定的一个元素加1或减1。 您可以假设数组的长度最多为10000。
     *
     * <p>例如:
     *
     * <p>输入: [1,2,3]
     *
     * <p>输出: 2
     *
     * <p>说明： 只有两个动作是必要的（记得每一步仅可使其中一个元素加1或减1）：
     *
     * <p>[1,2,3] => [2,2,3] => [2,2,2]
     *
     * @param nums
     * @return
     */
    public int minMoves2(int[] nums) {
        int move = 0;
        Arrays.sort(nums);
        int left = 0, right = nums.length - 1;
        while (left < right) {
            move += (nums[right] - nums[left]);
            left++;
            right--;
        }
        return move;
    }

    /**
     * 477. 汉明距离总和
     *
     * <p>两个整数的 汉明距离 指的是这两个数字的二进制数对应位不同的数量。
     *
     * <p>计算一个数组中，任意两个数之间汉明距离的总和。
     *
     * <p>示例:
     *
     * <p>输入: 4, 14, 2
     *
     * <p>输出: 6
     *
     * <p>解释: 在二进制表示中，4表示为0100，14表示为1110，2表示为0010。（这样表示是为了体现后四位之间关系） 所以答案为： HammingDistance(4, 14) +
     * HammingDistance(4, 2) + HammingDistance(14, 2) = 2 + 2 + 2 = 6. 注意:
     *
     * <p>数组中元素的范围为从 0到 10^9。 数组的长度不超过 10^4。
     *
     * @param nums
     * @return
     */
    public int totalHammingDistance(int[] nums) {
        // 计算每一位中 1 的 个数
        int[] counts = new int[32];

        for (int num : nums) {
            int i = 0;
            while (num > 0) {
                counts[i] += (num & 1);
                num >>= 1;
                i++;
            }
        }
        int result = 0;
        int n = nums.length;
        //
        for (int count : counts) {
            // count 个 1 和 n - count 个 0
            result += count * (n - count);
        }

        return result;
    }

    @Test
    public void kthFactor() {
        int n = 1000, k = 3;
        logResult(kthFactor(n, k));
    }

    /**
     * 5433. n 的第 k 个因子
     *
     * <p>给你两个正整数 n 和 k 。
     *
     * <p>如果正整数 i 满足 n % i == 0 ，那么我们就说正整数 i 是整数 n 的因子。
     *
     * <p>考虑整数 n 的所有因子，将它们 升序排列 。请你返回第 k 个因子。如果 n 的因子数少于 k ，请你返回 -1 。
     *
     * <p>示例 1：
     *
     * <p>输入：n = 12, k = 3 输出：3 解释：因子列表包括 [1, 2, 3, 4, 6, 12]，第 3 个因子是 3 。 示例 2：
     *
     * <p>输入：n = 7, k = 2 输出：7 解释：因子列表包括 [1, 7] ，第 2 个因子是 7 。 示例 3：
     *
     * <p>输入：n = 4, k = 4 输出：-1 解释：因子列表包括 [1, 2, 4] ，只有 3 个因子，所以我们应该返回 -1 。 示例 4：
     *
     * <p>输入：n = 1, k = 1 输出：1 解释：因子列表包括 [1] ，第 1 个因子为 1 。 示例 5：
     *
     * <p>输入：n = 1000, k = 3 输出：4 解释：因子列表包括 [1, 2, 4, 5, 8, 10, 20, 25, 40, 50, 100, 125, 200, 250,
     * 500, 1000] 。
     *
     * <p>提示：
     *
     * <p>1 <= k <= n <= 1000
     *
     * @param n
     * @param k
     * @return
     */
    public int kthFactor(int n, int k) {
        int idx = 0;

        for (int i = 1; i <= n; i++) {
            if (n % i == 0) {
                idx++;
                if (k == idx) {
                    return i;
                }
            }
        }

        return -1;
    }

    /**
     * 537. 复数乘法
     *
     * <p>给定两个表示复数的字符串。
     *
     * <p>返回表示它们乘积的字符串。注意，根据定义 i2 = -1 。
     *
     * <p>示例 1:
     *
     * <p>输入: "1+1i", "1+1i" 输出: "0+2i" 解释: (1 + i) * (1 + i) = 1 + i2 + 2 * i = 2i ，你需要将它转换为 0+2i
     * 的形式。 示例 2:
     *
     * <p>输入: "1+-1i", "1+-1i" 输出: "0+-2i" 解释: (1 - i) * (1 - i) = 1 + i2 - 2 * i = -2i ，你需要将它转换为
     * 0+-2i 的形式。 注意:
     *
     * <p>输入字符串不包含额外的空格。 输入字符串将以 a+bi 的形式给出，其中整数 a 和 b 的范围均在 [-100, 100] 之间。输出也应当符合这种形式。
     *
     * @param a
     * @param b
     * @return
     */
    public String complexNumberMultiply(String a, String b) {
        String[] num1 = a.split("[+i]");
        String[] num2 = b.split("[+i]");
        StringBuilder sb = new StringBuilder();
        int num1Real = Integer.parseInt(num1[0]);
        int num1Imag = Integer.parseInt(num1[1]);
        int num2Real = Integer.parseInt(num2[0]);
        int num2Imag = Integer.parseInt(num2[1]);

        sb.append(num1Real * num2Real - num1Imag * num2Imag)
                .append("+")
                .append(num1Real * num2Imag + num1Imag * num2Real)
                .append("i");
        return sb.toString();
    }

    /**
     * 553. 最优除法
     *
     * <p>给定一组正整数，相邻的整数之间将会进行浮点除法操作。例如， [2,3,4] -> 2 / 3 / 4 。
     *
     * <p>但是，你可以在任意位置添加任意数目的括号，来改变算数的优先级。你需要找出怎么添加括号，才能得到最大的结果，并且返回相应的字符串格式的表达式。你的表达式不应该含有冗余的括号。
     *
     * <p>示例：
     *
     * <p>输入: [1000,100,10,2] 输出: "1000/(100/10/2)" 解释: 1000/(100/10/2) = 1000/((100/10)/2) = 200
     * 但是，以下加粗的括号 "1000/((100/10)/2)" 是冗余的， 因为他们并不影响操作的优先级，所以你需要返回 "1000/(100/10/2)"。
     *
     * <p>其他用例: 1000/(100/10)/2 = 50 1000/(100/(10/2)) = 50 1000/100/10/2 = 0.5 1000/100/(10/2) = 2
     * 说明:
     *
     * <p>输入数组的长度在 [1, 10] 之间。 数组中每个元素的大小都在 [2, 1000] 之间。 每个测试用例只有一个最优除法解。
     *
     * @param nums
     * @return
     */
    public String optimalDivision(int[] nums) {
        // 最大被除数
        int[] maxDividend = new int[nums.length - 1];
        // 最小除数
        int[] minDivisor = new int[nums.length - 1];
        if (nums.length == 1) {
            return String.valueOf(nums[0]);
        }
        if (nums.length == 2) {

            return nums[0] + "/" + nums[1];
        }
        StringBuilder sb = new StringBuilder();
        sb.append(nums[0]).append("/(").append(nums[1]);
        for (int i = 2; i < nums.length; i++) {
            sb.append("/").append(nums[i]);
        }
        sb.append(")");
        return sb.toString();
    }

    @Test
    public void nextGreaterElement() {
        int n = 101;
        logResult(nextGreaterElement(n));
    }

    /**
     * 556. 下一个更大元素 III
     *
     * <p>给定一个32位正整数 n，你需要找到最小的32位整数，其与 n 中存在的位数完全相同，并且其值大于n。如果不存在这样的32位整数，则返回-1。
     *
     * <p>示例 1:
     *
     * <p>输入: 12 输出: 21 示例 2:
     *
     * <p>输入: 21 输出: -1
     *
     * @param n
     * @return
     */
    public int nextGreaterElement(int n) {
        int[] nums = new int[32];
        Arrays.fill(nums, -1);
        int index = nums.length - 1;
        while (n > 0) {
            nums[index--] = n % 10;
            n /= 10;
        }

        // 1 从后往前,找到第一个递增（从后往前递增,从前往后递减）
        // 2 然后 获取前一位数字a, 如果前一位是0或不存在,返回-1
        // 3 在 后面的数字中找到比a大的最小值, 放到a的位置
        // 4 对后面的数字排序,(逆序即可)
        index = -1;
        for (int i = nums.length - 2; i >= 0; i--) {
            if (nums[i] != -1 && nums[i] < nums[i + 1]) {
                index = i;
                break;
            }
        }
        if (index == -1) {
            return -1;
        }

        int left = index + 1, right = nums.length - 1;
        while (left < right) {
            swap(nums, left++, right--);
        }
        for (int i = index + 1; i < nums.length; i++) {
            if (nums[i] > nums[index]) {
                swap(nums, index, i);
                break;
            }
        }
        int max = Integer.MAX_VALUE / 10;
        int num = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == -1) {
                continue;
            }
            if (num > max || (num == max && nums[i] > 7)) {
                return -1;
            }
            num = num * 10 + nums[i];
        }

        return num;
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    @Test
    public void fractionAddition() {
        String expression = "5/3+1/3";
        logResult(fractionAddition(expression));
    }

    /**
     * 592. 分数加减运算
     *
     * <p>给定一个表示分数加减运算表达式的字符串，你需要返回一个字符串形式的计算结果。 这个结果应该是不可约分的分数，即最简分数。 如果最终结果是一个整数，例如
     * 2，你需要将它转换成分数形式，其分母为 1。所以在上述例子中, 2 应该被转换为 2/1。
     *
     * <p>示例 1:
     *
     * <p>输入:"-1/2+1/2" 输出: "0/1" 示例 2:
     *
     * <p>输入:"-1/2+1/2+1/3" 输出: "1/3" 示例 3:
     *
     * <p>输入:"1/3-1/2" 输出: "-1/6" 示例 4:
     *
     * <p>输入:"5/3+1/3" 输出: "2/1" 说明:
     *
     * <p>输入和输出字符串只包含 '0' 到 '9' 的数字，以及 '/', '+' 和 '-'。 输入和输出分数格式均为 ±分子/分母。如果输入的第一个分数或者输出的分数是正数，则 '+'
     * 会被省略掉。 输入只包含合法的最简分数，每个分数的分子与分母的范围是 [1,10]。 如果分母是1，意味着这个分数实际上是一个整数。 输入的分数个数范围是 [1,10]。
     * 最终结果的分子与分母保证是 32 位整数范围内的有效整数。
     *
     * @param expression
     * @return
     */
    public String fractionAddition(String expression) {
        /*FractionNode last = null;
        FractionNode node = null;
        int numerator = 0;
        int denominator = 0;*/
        List<Boolean> addFlags = new ArrayList<>();
        for (int i = 1; i < expression.length(); i++) {
            char c = expression.charAt(i);
            switch (c) {
                case '+':
                    addFlags.add(true);
                    break;
                case '-':
                    addFlags.add(false);
                    break;
            }
        }
        List<FractionNode> list = new ArrayList<>();
        for (String num : expression.split("[+-]")) {
            log.debug("num:{}", num);
            if (num.length() > 0) {
                String[] fracts = num.split("/");
                list.add(
                        new FractionNode(Integer.parseInt(fracts[0]), Integer.parseInt(fracts[1])));
            }
        }
        FractionNode node = list.get(0);
        if (expression.charAt(0) == '-') {
            node.numerator *= -1;
        }
        for (int i = 0; i < addFlags.size(); i++) {
            boolean flag = addFlags.get(i);
            FractionNode currentNode = list.get(i + 1);
            if (!flag) {
                currentNode.numerator *= -1;
            }
            node = addFraction(node, currentNode);
        }
        StringBuilder sb = new StringBuilder();
        if (node.numerator == 0) {
            return "0/1";
        }

        int gcd = getGcd(Math.abs(node.numerator), node.denominator);

        sb.append(node.numerator / gcd).append("/").append(node.denominator / gcd);
        return sb.toString();
    }

    class FractionNode {

        // 分子
        private int numerator;
        // 分母
        private int denominator;

        FractionNode(int numerator, int denominator) {
            this.numerator = numerator;
            this.denominator = denominator;
        }
    }

    private FractionNode addFraction(FractionNode node1, FractionNode node2) {
        if (Objects.isNull(node1)) {
            return node2;
        }
        int gcd = getGcd(node1.denominator, node2.denominator);

        int denominator = node1.denominator * node2.denominator / gcd;

        int numerator =
                (node1.numerator * node2.denominator + node2.numerator * node1.denominator) / gcd;

        return new FractionNode(numerator, denominator);
    }

    @Test
    public void maximumSwap() {
        int num = 98368;
        logResult(maximumSwap(num));
    }

    /**
     * 670. 最大交换
     *
     * <p>给定一个非负整数，你至多可以交换一次数字中的任意两位。返回你能得到的最大值。
     *
     * <p>示例 1 :
     *
     * <p>输入: 2736 输出: 7236 解释: 交换数字2和数字7。 示例 2 :
     *
     * <p>输入: 9973 输出: 9973 解释: 不需要交换。 注意:
     *
     * <p>给定数字的范围是 [0, 108]
     *
     * @param num
     * @return
     */
    public int maximumSwap(int num) {
        int[] nums = new int[9];
        int len = nums.length;
        int index = len - 1;
        while (num > 0) {
            nums[index--] = num % 10;
            num /= 10;
        }
        // 从后往前记录最多值
        int maxIndex = len - 1;
        int[] maxIndexs = new int[len];
        for (int i = len - 1; i > index; i--) {
            if (nums[i] > nums[maxIndex]) {
                maxIndex = i;
            }
            maxIndexs[i] = maxIndex;
        }
        log.debug("maxIndexs:{}", maxIndexs);
        for (int i = index + 1; i < len; i++) {
            if (nums[maxIndexs[i]] != nums[i]) {
                swap(nums, i, maxIndexs[i]);
                break;
            }
        }
        log.debug("nums:{}", nums);
        num = 0;
        for (int i = index + 1; i < len; i++) {
            num = num * 10 + nums[i];
        }

        return num;
    }

    @Test
    public void solveEquation() {
        String equation = "-x=-2";
        logResult(solveEquation(equation));
    }
    /**
     * 640. 求解方程
     *
     * <p>求解一个给定的方程，将x以字符串"x=#value"的形式返回。该方程仅包含'+'，' - '操作，变量 x 和其对应系数。
     *
     * <p>如果方程没有解，请返回“No solution”。
     *
     * <p>如果方程有无限解，则返回“Infinite solutions”。
     *
     * <p>如果方程中只有一个解，要保证返回值 x 是一个整数。
     *
     * <p>示例 1：
     *
     * <p>输入: "x+5-3+x=6+x-2" 输出: "x=2" 示例 2:
     *
     * <p>输入: "x=x" 输出: "Infinite solutions" 示例 3:
     *
     * <p>输入: "2x=x" 输出: "x=0" 示例 4:
     *
     * <p>输入: "2x+3x-6x=x+2" 输出: "x=-1" 示例 5:
     *
     * <p>输入: "x=x+2" 输出: "No solution"
     *
     * @param equation
     * @return
     */
    public String solveEquation(String equation) {
        String[] arr = equation.split("=");
        Equation left = new Equation(arr[0]);
        Equation right = new Equation(arr[1]);

        int xNum = left.xNum - right.xNum;
        int value = right.value - left.value;
        if (xNum == 0) {
            return value == 0 ? "Infinite solutions" : "No solution";
        }
        return "x" + "=" + value / xNum;
    }

    class Equation {
        int xNum;
        int value;

        Equation(String expression) {
            List<Boolean> addFlags = new ArrayList<>();
            addFlags.add(!expression.startsWith("-"));
            for (int i = 1; i < expression.length(); i++) {
                char c = expression.charAt(i);
                switch (c) {
                    case '+':
                        addFlags.add(true);
                        break;
                    case '-':
                        addFlags.add(false);
                        break;
                }
            }
            xNum = 0;
            value = 0;
            List<String> numList = new ArrayList<>();
            for (String num : expression.split("[+-]")) {
                if (num.length() > 0) {
                    numList.add(num);
                }
            }
            for (int i = 0; i < numList.size(); i++) {
                boolean flag = addFlags.get(i);
                String num = numList.get(i);
                if (num.endsWith("x")) {
                    int val = 1;
                    if (num.length() > 1) {
                        val = Integer.parseInt(num.substring(0, num.length() - 1));
                    }
                    xNum += flag ? val : -val;
                } else {
                    int val = Integer.parseInt(num);
                    value += flag ? val : -val;
                }
            }
        }
    }

    @Test
    public void maxDiff() {
        int num = 111;
        logResult(maxDiff(num));
    }

    /**
     * 1432. 改变一个整数能得到的最大差值
     *
     * <p>给你一个整数 num 。你可以对它进行如下步骤恰好 两次 ：
     *
     * <p>选择一个数字 x (0 <= x <= 9). 选择另一个数字 y (0 <= y <= 9) 。数字 y 可以等于 x 。 将 num 中所有出现 x 的数位都用 y 替换。
     * 得到的新的整数 不能 有前导 0 ，得到的新整数也 不能 是 0 。 令两次对 num 的操作得到的结果分别为 a 和 b 。
     *
     * <p>请你返回 a 和 b 的 最大差值 。
     *
     * <p>示例 1：
     *
     * <p>输入：num = 555 输出：888 解释：第一次选择 x = 5 且 y = 9 ，并把得到的新数字保存在 a 中。 第二次选择 x = 5 且 y = 1
     * ，并把得到的新数字保存在 b 中。 现在，我们有 a = 999 和 b = 111 ，最大差值为 888 示例 2：
     *
     * <p>输入：num = 9 输出：8 解释：第一次选择 x = 9 且 y = 9 ，并把得到的新数字保存在 a 中。 第二次选择 x = 9 且 y = 1 ，并把得到的新数字保存在
     * b 中。 现在，我们有 a = 9 和 b = 1 ，最大差值为 8 示例 3：
     *
     * <p>输入：num = 123456 输出：820000 示例 4：
     *
     * <p>输入：num = 10000 输出：80000 示例 5：
     *
     * <p>输入：num = 9288 输出：8700
     *
     * <p>提示：
     *
     * <p>1 <= num <= 10^8
     *
     * @param num
     * @return
     */
    public int maxDiff(int num) {
        char[] chars = String.valueOf(num).toCharArray();
        char[] chars2 = chars.clone();
        int len = chars.length;
        // 找到第一个不是9的数字 变成9
        int index = 0;
        while (index < len && chars[index] == '9') {
            index++;
        }
        if (index < len) {
            char c = chars[index];
            for (int i = index; i < len; i++) {
                if (chars[i] == c) {
                    chars[i] = '9';
                }
            }
        }
        int max = Integer.parseInt(new String(chars));
        log.debug("index:{}", index);
        log.debug("chars:{}", chars);
        log.debug("max:{}", max);
        // 第一个数字不为1 修改为1
        if (chars2[0] != '1') {
            char c = chars2[0];

            for (int i = 0; i < len; i++) {
                if (chars2[i] == c) {
                    chars2[i] = '1';
                }
            }
        }

        // 第一个数字为1 找到第一个不是0 或 1的数字 变成0
        else {
            index = 1;
            while (index < len && (chars2[index] == '0' || chars2[index] == '1')) {
                index++;
            }
            if (index < len) {
                char c = chars2[index];
                for (int i = index; i < len; i++) {
                    if (chars2[i] == c) {
                        chars2[i] = '0';
                    }
                }
            }
        }
        int min = Integer.parseInt(new String(chars2));
        log.debug("min:{}", min);
        return max - min;
    }

    @Test
    public void numWaterBottles() {
        int numBottles = 25, numExchange = 4;
        logResult(numWaterBottles(numBottles, numExchange));
    }

    /**
     * 5464. 换酒问题
     *
     * <p>小区便利店正在促销，用 numExchange 个空酒瓶可以兑换一瓶新酒。你购入了 numBottles 瓶酒。
     *
     * <p>如果喝掉了酒瓶中的酒，那么酒瓶就会变成空的。
     *
     * <p>请你计算 最多 能喝到多少瓶酒。
     *
     * <p>示例 1：
     *
     * <p>输入：numBottles = 9, numExchange = 3 输出：13 解释：你可以用 3 个空酒瓶兑换 1 瓶酒。 所以最多能喝到 9 + 3 + 1 = 13 瓶酒。
     * 示例 2：
     *
     * <p>输入：numBottles = 15, numExchange = 4 输出：19 解释：你可以用 4 个空酒瓶兑换 1 瓶酒。 所以最多能喝到 15 + 3 + 1 = 19
     * 瓶酒。 示例 3：
     *
     * <p>输入：numBottles = 5, numExchange = 5 输出：6 示例 4：
     *
     * <p>输入：numBottles = 2, numExchange = 3 输出：2
     *
     * <p>提示：
     *
     * <p>1 <= numBottles <= 100 2 <= numExchange <= 100
     *
     * @param numBottles
     * @param numExchange
     * @return
     */
    public int numWaterBottles(int numBottles, int numExchange) {
        int count = numBottles;
        // 空瓶子
        int emptyNum = 0;

        while (numBottles + emptyNum >= numExchange) {
            emptyNum += numBottles;
            numBottles = emptyNum / numExchange;
            count += numBottles;
            emptyNum -= numExchange * numBottles;
        }
        return count;
    }

    /**
     * LCP 03. 机器人大冒险
     *
     * <p>力扣团队买了一个可编程机器人，机器人初始位置在原点(0, 0)。小伙伴事先给机器人输入一串指令command，机器人就会无限循环这条指令的步骤进行移动。指令有两种：
     *
     * <p>U: 向y轴正方向移动一格 R: 向x轴正方向移动一格。 不幸的是，在 xy 平面上还有一些障碍物，他们的坐标用obstacles表示。机器人一旦碰到障碍物就会被损毁。
     *
     * <p>给定终点坐标(x, y)，返回机器人能否完好地到达终点。如果能，返回true；否则返回false。
     *
     * <p>示例 1：
     *
     * <p>输入：command = "URR", obstacles = [], x = 3, y = 2 输出：true 解释：U(0, 1) -> R(1, 1) -> R(2, 1)
     * -> U(2, 2) -> R(3, 2)。 示例 2：
     *
     * <p>输入：command = "URR", obstacles = [[2, 2]], x = 3, y = 2 输出：false 解释：机器人在到达终点前会碰到(2, 2)的障碍物。
     * 示例 3：
     *
     * <p>输入：command = "URR", obstacles = [[4, 2]], x = 3, y = 2 输出：true 解释：到达终点后，再碰到障碍物也不影响返回结果。
     *
     * <p>限制：
     *
     * <p>2 <= command的长度 <= 1000 command由U，R构成，且至少有一个U，至少有一个R 0 <= x <= 1e9, 0 <= y <= 1e9 0 <=
     * obstacles的长度 <= 1000 obstacles[i]不为原点或者终点
     *
     * @param command
     * @param obstacles
     * @param x
     * @param y
     * @return
     */
    public boolean robot(String command, int[][] obstacles, int x, int y) {
        int xNum = 0, yNum = 0;
        Set<Long> set = new HashSet<>();
        // 使用 x * 2^30 + y 存储坐标
        set.add(((long) xNum << 30) | yNum);

        for (char c : command.toCharArray()) {
            if (c == 'U') {
                yNum++;
            } else {
                xNum++;
            }
            set.add(((long) xNum << 30) | yNum);
        }
        // 判断需要循环多少次 到达最近的点
        int cir = Math.min(x / xNum, y / yNum);

        // 如果不包含 经过 cir 次循环 到达 x,y 的点  , 返回false
        if (!set.contains(((long) (x - cir * xNum) << 30) | (y - cir * yNum))) {
            return false;
        }
        // 判断障碍物
        for (int[] obstacle : obstacles) {
            int x1 = obstacle[0];
            int y1 = obstacle[1];
            if (x1 > x || y1 > y) {
                continue;
            }
            // 判断需要循环多少次 到达最近的点
            cir = Math.min(x1 / xNum, y1 / yNum);
            // 如果包含 经过 cir 次循环 到达 x1,y1 的点(障碍物)  , 返回false
            if (set.contains(((long) (x1 - cir * xNum) << 30) | (y1 - cir * yNum))) {
                return false;
            }
        }

        return true;
    }

    @Test
    public void reachNumber() {
        int target = 18;
        logResult(reachNumber(target));
    }

    /**
     * 754. 到达终点数字
     *
     * <p>在一根无限长的数轴上，你站在0的位置。终点在target的位置。
     *
     * <p>每次你可以选择向左或向右移动。第 n 次移动（从 1 开始），可以走 n 步。
     *
     * <p>返回到达终点需要的最小移动次数。
     *
     * <p>示例 1:
     *
     * <p>输入: target = 3 输出: 2 解释: 第一次移动，从 0 到 1 。 第二次移动，从 1 到 3 。 示例 2:
     *
     * <p>输入: target = 2 输出: 3 解释: 第一次移动，从 0 到 1 。 第二次移动，从 1 到 -1 。 第三次移动，从 -1 到 2 。 注意:
     *
     * <p>target是在[-10^9, 10^9]范围中的非零整数。
     *
     * @param target
     * @return
     */
    public int reachNumber(int target) {
        target = Math.abs(target);
        int k = 0;
        while (target > 0) {
            target -= ++k;
        }
        // 让  1 ~ k 中的 部分元素变成负的
        // data 是 余数, 余数是偶数 可以 通过 把（data/2）变成负的即可
        // 余数是奇数
        return (target & 1) == 0 ? k : k + 1 + k % 2;
    }

    @Test
    public void numRabbits() {
        int[] answers = {10, 10, 10};
        logResult(numRabbits(answers));
    }

    /**
     * 781. 森林中的兔子
     *
     * <p>森林中，每个兔子都有颜色。其中一些兔子（可能是全部）告诉你还有多少其他的兔子和自己有相同的颜色。我们将这些回答放在 answers 数组里。
     *
     * <p>返回森林中兔子的最少数量。
     *
     * <p>示例: 输入: answers = [1, 1, 2] 输出: 5 解释: 两只回答了 "1" 的兔子可能有相同的颜色，设为红色。 之后回答了 "2"
     * 的兔子不会是红色，否则他们的回答会相互矛盾。 设回答了 "2" 的兔子为蓝色。 此外，森林中还应有另外 2 只蓝色兔子的回答没有包含在数组中。 因此森林中兔子的最少数量是 5: 3
     * 只回答的和 2 只没有回答的。
     *
     * <p>输入: answers = [10, 10, 10] 输出: 11
     *
     * <p>输入: answers = [] 输出: 0 说明:
     *
     * <p>answers 的长度最大为1000。 answers[i] 是在 [0, 999] 范围内的整数。
     *
     * @param answers
     * @return
     */
    public int numRabbits(int[] answers) {
        int[] count = new int[1000];
        for (int x : answers) {
            count[x]++;
        }

        int result = count[0];
        for (int i = 1; i < 1000; i++) {
            if (count[i] == 0) {
                continue;
            }
            int div = count[i] / (i + 1);
            int rem = count[i] % (i + 1);

            result += div * (i + 1);
            if (rem > 0) {
                result += i + 1;
            }
        }

        return result;
    }

    @Test
    public void minFlips() {
        String target = "11000";
        logResult(minFlips(target));
    }

    /**
     * 5473. 灯泡开关 IV
     *
     * <p>房间中有 n 个灯泡，编号从 0 到 n-1 ，自左向右排成一行。最开始的时候，所有的灯泡都是 关 着的。
     *
     * <p>请你设法使得灯泡的开关状态和 target 描述的状态一致，其中 target[i] 等于 1 第 i 个灯泡是开着的，等于 0 意味着第 i 个灯是关着的。
     *
     * <p>有一个开关可以用于翻转灯泡的状态，翻转操作定义如下：
     *
     * <p>选择当前配置下的任意一个灯泡（下标为 i ） 翻转下标从 i 到 n-1 的每个灯泡 翻转时，如果灯泡的状态为 0 就变为 1，为 1 就变为 0 。
     *
     * <p>返回达成 target 描述的状态所需的 最少 翻转次数。
     *
     * <p>示例 1：
     *
     * <p>输入：target = "10111" 输出：3 解释：初始配置 "00000". 从第 3 个灯泡（下标为 2）开始翻转 "00000" -> "00111" 从第 1
     * 个灯泡（下标为 0）开始翻转 "00111" -> "11000" 从第 2 个灯泡（下标为 1）开始翻转 "11000" -> "10111" 至少需要翻转 3 次才能达成
     * target 描述的状态 示例 2：
     *
     * <p>输入：target = "101" 输出：3 解释："000" -> "111" -> "100" -> "101". 示例 3：
     *
     * <p>输入：target = "00000" 输出：0 示例 4：
     *
     * <p>输入：target = "001011101" 输出：5
     *
     * <p>提示：
     *
     * <p>1 <= target.length <= 10^5 target[i] == '0' 或者 target[i] == '1'
     *
     * @param target
     * @return
     */
    public int minFlips(String target) {
        char[] chars = target.toCharArray();
        int index = 0;
        while (index < chars.length && chars[index] == '0') {
            index++;
        }
        if (index == chars.length) {
            return 0;
        }
        int count = 1;
        char last = chars[index];
        // 连续的0有几个
        for (int i = index + 1; i < chars.length; i++) {
            while (i < chars.length && chars[i] == last) {
                i++;
            }
            if (i < chars.length) {
                count++;
                last = chars[i];
            }
        }

        return count;
    }

    @Test
    public void numOfSubarrays() {
        int[] arr = {1, 2, 3, 4, 5, 6, 7};
        logResult(numOfSubarrays(arr));
    }

    /**
     * 1523. 在区间范围内统计奇数数目
     *
     * <p>给你两个非负整数 low 和 high 。请你返回 low 和 high 之间（包括二者）奇数的数目。
     *
     * <p>示例 1：
     *
     * <p>输入：low = 3, high = 7 输出：3 解释：3 到 7 之间奇数数字为 [3,5,7] 。 示例 2：
     *
     * <p>输入：low = 8, high = 10 输出：1 解释：8 到 10 之间奇数数字为 [9] 。
     *
     * <p>提示：
     *
     * <p>0 <= low <= high <= 10^9
     *
     * @param low
     * @param high
     * @return
     */
    public int countOdds(int low, int high) {
        int count = 0;
        int len = high - low + 1;
        if ((len & 1) == 0) {
            count = len >> 1;
        } else {

            count = len >> 1;
            if ((low & 1) == 1) {
                count++;
            }
        }
        return count;
    }

    /**
     * 1524. 和为奇数的子数组数目
     *
     * <p>给你一个整数数组 arr 。请你返回和为 奇数 的子数组数目。
     *
     * <p>由于答案可能会很大，请你将结果对 10^9 + 7 取余后返回。
     *
     * <p>示例 1：
     *
     * <p>输入：arr = [1,3,5] 输出：4 解释：所有的子数组为 [[1],[1,3],[1,3,5],[3],[3,5],[5]] 。 所有子数组的和为
     * [1,4,9,3,8,5]. 奇数和包括 [1,9,3,5] ，所以答案为 4 。 示例 2 ：
     *
     * <p>输入：arr = [2,4,6] 输出：0 解释：所有子数组为 [[2],[2,4],[2,4,6],[4],[4,6],[6]] 。 所有子数组和为
     * [2,6,12,4,10,6] 。 所有子数组和都是偶数，所以答案为 0 。 示例 3：
     *
     * <p>输入：arr = [1,2,3,4,5,6,7] 输出：16 示例 4：
     *
     * <p>输入：arr = [100,100,99,99] 输出：4 示例 5：
     *
     * <p>输入：arr = [7] 输出：1
     *
     * <p>提示：
     *
     * <p>1 <= arr.length <= 10^5 1 <= arr[i] <= 100
     *
     * @param arr
     * @return
     */
    public int numOfSubarrays(int[] arr) {
        int MOD = 1000000007;
        // 0 偶数个数 1 奇数个数
        int[] cnts = new int[2];
        int cur = 0;
        int result = 0;
        cnts[0] = 1;
        for (int num : arr) {
            // 查看当前的前缀和 是奇数或偶数
            cur = (cur + num) & 1;
            // 结果加上前缀和相反的个数
            result += cnts[1 - cur];
            // 前缀和为cur的个数++
            cnts[cur]++;
            result %= MOD;
        }
        return result;
    }

    /**
     * 1401. 圆和矩形是否有重叠
     *
     * <p>给你一个以 (radius, x_center, y_center) 表示的圆和一个与坐标轴平行的矩形 (x1, y1, x2, y2)，其中 (x1, y1)
     * 是矩形左下角的坐标，(x2, y2) 是右上角的坐标。
     *
     * <p>如果圆和矩形有重叠的部分，请你返回 True ，否则返回 False 。
     *
     * <p>换句话说，请你检测是否 存在 点 (xi, yi) ，它既在圆上也在矩形上（两者都包括点落在边界上的情况）。
     *
     * <p>示例 1：
     *
     * <p>输入：radius = 1, x_center = 0, y_center = 0, x1 = 1, y1 = -1, x2 = 3, y2 = 1 输出：true
     * 解释：圆和矩形有公共点 (1,0) 示例 2：
     *
     * <p>输入：radius = 1, x_center = 0, y_center = 0, x1 = -1, y1 = 0, x2 = 0, y2 = 1 输出：true 示例 3：
     *
     * <p>输入：radius = 1, x_center = 1, y_center = 1, x1 = -3, y1 = -3, x2 = 3, y2 = 3 输出：true 示例 4：
     *
     * <p>输入：radius = 1, x_center = 1, y_center = 1, x1 = 1, y1 = -3, x2 = 2, y2 = -1 输出：false
     *
     * <p>提示：
     *
     * <p>1 <= radius <= 2000 -10^4 <= x_center, y_center, x1, y1, x2, y2 <= 10^4 x1 < x2 y1 < y2
     *
     * @param radius
     * @param x_center
     * @param y_center
     * @param x1
     * @param y1
     * @param x2
     * @param y2
     * @return
     */
    public boolean checkOverlap(
            int radius, int x_center, int y_center, int x1, int y1, int x2, int y2) {
        // 分为 3 种情况
        // 1 圆心在矩形内,肯定相交
        // 2 圆心在矩形的上下左右, 半径  >= 圆心到最近的边的距离 -> 相交
        // 3 圆心在矩形的四个角, 半径 >= 圆心到最近的角的距离 -> 相交

        if (x_center < x1) {
            if (y_center > y2) {
                // 左上
                return radius * radius
                        >= (x1 - x_center) * (x1 - x_center) + (y_center - y2) * (y_center - y2);
            } else if (y_center < y1) {
                // 左下
                return radius * radius
                        >= (x1 - x_center) * (x1 - x_center) + (y1 - y_center) * (y1 - y_center);
            } else {
                // 左
                return radius >= x1 - x_center;
            }
        } else if (x_center > x2) {
            if (y_center > y2) {
                // 右上
                return radius * radius
                        >= (x_center - x2) * (x_center - x2) + (y_center - y2) * (y_center - y2);
            } else if (y_center < y1) {
                // 右下
                return radius * radius
                        >= (x_center - x2) * (x_center - x2) + (y1 - y_center) * (y1 - y_center);
            } else {
                // 右
                return radius >= x_center - x2;
            }
        } else {
            // 上
            if (y_center > y2) {
                return radius >= y_center - y2;
            } else if (y_center < y1) {
                // 下
                return radius >= y1 - y_center;
            } else {
                // 在矩形内
                return true;
            }
        }
    }

    @Test
    public void nthUglyNumber2() {
        int n = 1000000000, a = 2, b = 217983653, c = 336916467;
        logResult(nthUglyNumber(n, a, b, c));
    }
    /**
     * 1201. 丑数 III
     *
     * <p>请你帮忙设计一个程序，用来找出第 n 个丑数。
     *
     * <p>丑数是可以被 a 或 b 或 c 整除的 正整数。
     *
     * <p>示例 1：
     *
     * <p>输入：n = 3, a = 2, b = 3, c = 5 输出：4 解释：丑数序列为 2, 3, 4, 5, 6, 8, 9, 10... 其中第 3 个是 4。 示例 2：
     *
     * <p>输入：n = 4, a = 2, b = 3, c = 4 输出：6 解释：丑数序列为 2, 3, 4, 6, 8, 9, 10, 12... 其中第 4 个是 6。 示例 3：
     *
     * <p>输入：n = 5, a = 2, b = 11, c = 13 输出：10 解释：丑数序列为 2, 4, 6, 8, 10, 11, 12, 13... 其中第 5 个是 10。
     * 示例 4：
     *
     * <p>输入：n = 1000000000, a = 2, b = 217983653, c = 336916467 输出：1999999984
     *
     * <p>提示：
     *
     * <p>1 <= n, a, b, c <= 10^9 1 <= a * b * c <= 10^18 本题结果在 [1, 2 * 10^9] 的范围内
     *
     * @param n
     * @param a
     * @param b
     * @param c
     * @return
     */
    public int nthUglyNumber(int n, int a, int b, int c) {
        int min = Math.min(a, Math.min(b, c));
        int right = min * n;
        int left = min;

        long ab = getLcm(a, (long) b);
        long ac = getLcm(a, (long) c);
        long bc = getLcm(b, (long) c);
        long abc = getLcm(ab, c);
        while (left < right) {
            int mid = left + ((right - left) >> 1);
            long idx1 = mid / a;
            long idx2 = mid / b;
            long idx3 = mid / c;
            long idx12 = mid / ab;
            long idx13 = mid / ac;
            long idx23 = mid / bc;
            long idx123 = mid / abc;
            long num = idx1 + idx2 + idx3 - idx12 - idx13 - idx23 + idx123;
            if (num == n) {
                if (mid % a == 0 || mid % b == 0 || mid % c == 0) {
                    return mid;
                } else {
                    return mid - Math.min(Math.min(mid % a, mid % b), mid % c);
                }
            } else if (num > n) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }

    /**
     * 1276. 不浪费原料的汉堡制作方案
     *
     * <p>圣诞活动预热开始啦，汉堡店推出了全新的汉堡套餐。为了避免浪费原料，请你帮他们制定合适的制作计划。
     *
     * <p>给你两个整数 tomatoSlices 和 cheeseSlices，分别表示番茄片和奶酪片的数目。不同汉堡的原料搭配如下：
     *
     * <p>巨无霸汉堡：4 片番茄和 1 片奶酪 小皇堡：2 片番茄和 1 片奶酪 请你以 [total_jumbo,
     * total_small]（[巨无霸汉堡总数，小皇堡总数]）的格式返回恰当的制作方案，使得剩下的番茄片 tomatoSlices 和奶酪片 cheeseSlices 的数量都是 0。
     *
     * <p>如果无法使剩下的番茄片 tomatoSlices 和奶酪片 cheeseSlices 的数量为 0，就请返回 []。
     *
     * <p>示例 1：
     *
     * <p>输入：tomatoSlices = 16, cheeseSlices = 7 输出：[1,6] 解释：制作 1 个巨无霸汉堡和 6 个小皇堡需要 4*1 + 2*6 = 16
     * 片番茄和 1 + 6 = 7 片奶酪。不会剩下原料。 示例 2：
     *
     * <p>输入：tomatoSlices = 17, cheeseSlices = 4 输出：[] 解释：只制作小皇堡和巨无霸汉堡无法用光全部原料。 示例 3：
     *
     * <p>输入：tomatoSlices = 4, cheeseSlices = 17 输出：[] 解释：制作 1 个巨无霸汉堡会剩下 16 片奶酪，制作 2 个小皇堡会剩下 15 片奶酪。
     * 示例 4：
     *
     * <p>输入：tomatoSlices = 0, cheeseSlices = 0 输出：[0,0] 示例 5：
     *
     * <p>输入：tomatoSlices = 2, cheeseSlices = 1 输出：[0,1]
     *
     * <p>提示：
     *
     * <p>0 <= tomatoSlices <= 10^7 0 <= cheeseSlices <= 10^7
     *
     * @param tomatoSlices
     * @param cheeseSlices
     * @return
     */
    public List<Integer> numOfBurgers(int tomatoSlices, int cheeseSlices) {
        List<Integer> result = new ArrayList<>();
        // 2 x +    y = tomatoSlices / 2
        // x + y = cheeseSlices
        if ((tomatoSlices & 1) == 1) {
            return result;
        }
        int x = (tomatoSlices >> 1) - cheeseSlices;

        int y = cheeseSlices - x;
        if (x < 0 || y < 0) {
            return result;
        }
        result.add(x);
        result.add(y);
        return result;
    }

    /**
     * 1318. 或运算的最小翻转次数
     *
     * <p>给你三个正整数 a、b 和 c。
     *
     * <p>你可以对 a 和 b 的二进制表示进行位翻转操作，返回能够使按位或运算 a OR b == c 成立的最小翻转次数。
     *
     * <p>「位翻转操作」是指将一个数的二进制表示任何单个位上的 1 变成 0 或者 0 变成 1 。
     *
     * <p>示例 1：
     *
     * <p>输入：a = 2, b = 6, c = 5 输出：3 解释：翻转后 a = 1 , b = 4 , c = 5 使得 a OR b == c 示例 2：
     *
     * <p>输入：a = 4, b = 2, c = 7 输出：1 示例 3：
     *
     * <p>输入：a = 1, b = 2, c = 3 输出：0
     *
     * <p>提示：
     *
     * <p>1 <= a <= 10^9 1 <= b <= 10^9 1 <= c <= 10^9
     *
     * @param a
     * @param b
     * @param c
     * @return
     */
    public int minFlips(int a, int b, int c) {
        int count = 0;

        while (a > 0 || b > 0 || c > 0) {
            if ((c & 1) == 1) {
                // 两个 0 修改其中一个1
                if ((a & 1) == 0 && (b & 1) == 0) {
                    count++;
                }
            } else {
                // 当前位0, a和b都需要改为0
                if ((a & 1) == 1) {
                    count++;
                }
                if ((b & 1) == 1) {
                    count++;
                }
            }

            a >>= 1;
            b >>= 1;
            c >>= 1;
        }
        return count;
    }

    /**
     * 1344. 时钟指针的夹角
     *
     * <p>给你两个数 hour 和 minutes 。请你返回在时钟上，由给定时间的时针和分针组成的较小角的角度（60 单位制）。
     *
     * <p>示例 1：
     *
     * <p>输入：hour = 12, minutes = 30 输出：165 示例 2：
     *
     * <p>输入：hour = 3, minutes = 30 输出；75 示例 3：
     *
     * <p>输入：hour = 3, minutes = 15 输出：7.5 示例 4：
     *
     * <p>输入：hour = 4, minutes = 50 输出：155 示例 5：
     *
     * <p>输入：hour = 12, minutes = 0 输出：0
     *
     * <p>提示：
     *
     * <p>1 <= hour <= 12 0 <= minutes <= 59 与标准答案误差在 10^-5 以内的结果都被视为正确结果。
     *
     * @param hour
     * @param minutes
     * @return
     */
    public double angleClock(int hour, int minutes) {
        double result = 0.0;

        double hourAngle = 0.0;
        if (hour == 12) {
            hour = 0;
        }
        // 时针角度
        hourAngle += hour * 30.0;
        // 分针角度
        double minuteAngle = minutes * 6;

        // 时针角度 的额外角度
        hourAngle += (double) minutes / 2.0;
        if (minuteAngle > hourAngle) {
            result = minuteAngle - hourAngle;
        } else {
            result = hourAngle - minuteAngle;
        }
        if (result > 180.0) {
            result = 360.0 - result;
        }
        return result;
    }

    /**
     * LCP 17. 速算机器人
     *
     * <p>小扣在秋日市集发现了一款速算机器人。店家对机器人说出两个数字（记作 x 和 y），请小扣说出计算指令：
     *
     * <p>"A" 运算：使 x = 2 * x + y； "B" 运算：使 y = 2 * y + x。 在本次游戏中，店家说出的数字为 x = 1 和 y =
     * 0，小扣说出的计算指令记作仅由大写字母 A、B 组成的字符串 s，字符串中字符的顺序表示计算顺序，请返回最终 x 与 y 的和为多少。
     *
     * <p>示例 1：
     *
     * <p>输入：s = "AB"
     *
     * <p>输出：4
     *
     * <p>解释： 经过一次 A 运算后，x = 2, y = 0。 再经过一次 B 运算，x = 2, y = 2。 最终 x 与 y 之和为 4。
     *
     * <p>提示：
     *
     * <p>0 <= s.length <= 10 s 由 'A' 和 'B' 组成
     *
     * @param s
     * @return
     */
    public int calculate(String s) {
        int x = 1, y = 0;
        /*for (char c : s.toCharArray()) {
            if (c == 'A') {
                // x + y = 2x + 2y;
                x = 2 * x + y;
            } else {
                y = 2 * y + x;
            }
        }*/
        return (int) Math.pow(2.0, s.length());
    }

    /**
     * 1362. 最接近的因数
     *
     * <p>给你一个整数 num，请你找出同时满足下面全部要求的两个整数：
     *
     * <p>两数乘积等于 num + 1 或 num + 2 以绝对差进行度量，两数大小最接近 你可以按任意顺序返回这两个整数。
     *
     * <p>示例 1：
     *
     * <p>输入：num = 8 输出：[3,3] 解释：对于 num + 1 = 9，最接近的两个因数是 3 & 3；对于 num + 2 = 10, 最接近的两个因数是 2 &
     * 5，因此返回 3 & 3 。 示例 2：
     *
     * <p>输入：num = 123 输出：[5,25] 示例 3：
     *
     * <p>输入：num = 999 输出：[40,25]
     *
     * <p>提示：
     *
     * <p>1 <= num <= 10^9
     *
     * @param num
     * @return
     */
    public int[] closestDivisors(int num) {

        int[] result1 = getMinDivisors(num + 1);
        int min1 = result1[1] - result1[0];
        int[] result2 = getMinDivisors(num + 2);
        int min2 = result2[1] - result2[0];

        return min1 < min2 ? result1 : result2;
    }

    private int[] getMinDivisors(int num) {
        int a = (int) Math.sqrt(num);
        while (num % a != 0) {
            a--;
        }
        return new int[] {a, num / a};
    }

    /**
     * LCP 22. 黑白方格画
     *
     * <p>小扣注意到秋日市集上有一个创作黑白方格画的摊位。摊主给每个顾客提供一个固定在墙上的白色画板，画板不能转动。画板上有 n * n
     * 的网格。绘画规则为，小扣可以选择任意多行以及任意多列的格子涂成黑色，所选行数、列数均可为 0。
     *
     * <p>小扣希望最终的成品上需要有 k 个黑色格子，请返回小扣共有多少种涂色方案。
     *
     * <p>注意：两个方案中任意一个相同位置的格子颜色不同，就视为不同的方案。
     *
     * <p>示例 1：
     *
     * <p>输入：n = 2, k = 2
     *
     * <p>输出：4
     *
     * <p>解释：一共有四种不同的方案： 第一种方案：涂第一列； 第二种方案：涂第二列； 第三种方案：涂第一行； 第四种方案：涂第二行。
     *
     * <p>示例 2：
     *
     * <p>输入：n = 2, k = 1
     *
     * <p>输出：0
     *
     * <p>解释：不可行，因为第一次涂色至少会涂两个黑格。
     *
     * <p>示例 3：
     *
     * <p>输入：n = 2, k = 4
     *
     * <p>输出：1
     *
     * <p>解释：共有 2*2=4 个格子，仅有一种涂色方案。
     *
     * <p>限制：
     *
     * <p>1 <= n <= 6 0 <= k <= n * n
     *
     * @param n
     * @param k
     * @return
     */
    public int paintingPlan(int n, int k) {
        // 先确定行数和列数
        int result = 0;
        if (n * n == k) {
            return 1;
        }
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= n; j++) {
                if (i * n + j * n - i * j == k) {
                    result += getCombinatorialNum(n, i) * getCombinatorialNum(n, j);
                }
            }
        }

        return result;
    }

    private Map<Integer, Integer> factorialMap = new HashMap<>();

    /**
     * 计算阶乘
     *
     * @param num
     * @return
     */
    private int getFactorialNum(int num) {
        if (factorialMap.containsKey(num)) {
            return factorialMap.get(num);
        }
        if (num == 1) {
            factorialMap.put(num, 1);
            return 1;
        }
        return factorialMap.putIfAbsent(num, num * getFactorialNum(num - 1));
    }

    /**
     * 计算组合数
     *
     * @param m
     * @param n
     * @return
     */
    private int getCombinatorialNum(int m, int n) {
        if (n > m - n) {
            n = m - n;
        }
        int temp = 1;
        for (int i = m; i > n; i--) {
            temp = temp * i;
        }
        for (int j = m - n; j > 0; j--) {
            temp = temp / j;
        }
        return temp;
    }

    private int getCombinatorialNum2(int m, int n) {
        if (n > m - n) {
            n = m - n;
        }
        double s1 = 0.0;
        double s2 = 0.0;
        for (int j = m - n + 1; j <= m; j++) {
            s1 += Math.log(j);
        }
        for (int j = 1; j <= n; j++) {
            s2 += Math.log(j);
        }
        return (int) Math.exp(s1 - s2);
    }

    /**
     * 1390. 四因数
     *
     * <p>给你一个整数数组 nums，请你返回该数组中恰有四个因数的这些整数的各因数之和。
     *
     * <p>如果数组中不存在满足题意的整数，则返回 0 。
     *
     * <p>示例：
     *
     * <p>输入：nums = [21,4,7] 输出：32 解释： 21 有 4 个因数：1, 3, 7, 21 4 有 3 个因数：1, 2, 4 7 有 2 个因数：1, 7 答案仅为
     * 21 的所有因数的和。
     *
     * <p>提示：
     *
     * <p>1 <= nums.length <= 10^4 1 <= nums[i] <= 10^5
     *
     * @param nums
     * @return
     */
    public int sumFourDivisors(int[] nums) {
        int result = 0;
        for (int num : nums) {

            result += getFourDivisorsSum(num);
        }

        return result;
    }

    private int getFourDivisorsSum(int num) {
        if (num == 1) {
            return 1;
        }
        Set<Integer> set = new HashSet<>();
        for (int i = 1; i * i <= num; i++) {
            if (num % i != 0) {
                continue;
            }
            int t = num / i;
            set.add(i);
            if (i != t) {
                set.add(t);
            }
            if (set.size() > 4) {
                return 0;
            }
        }
        if (set.size() != 4) {
            return 0;
        }
        int result = 0;
        for (int n : set) {
            result += n;
        }
        return result;
    }

    /**
     * 1387. 将整数按权重排序
     *
     * <p>我们将整数 x 的 权重 定义为按照下述规则将 x 变成 1 所需要的步数：
     *
     * <p>如果 x 是偶数，那么 x = x / 2 如果 x 是奇数，那么 x = 3 * x + 1 比方说，x=3 的权重为 7 。因为 3 需要 7 步变成 1 （3 --> 10
     * --> 5 --> 16 --> 8 --> 4 --> 2 --> 1）。
     *
     * <p>给你三个整数 lo， hi 和 k 。你的任务是将区间 [lo, hi] 之间的整数按照它们的权重 升序排序 ，如果大于等于 2 个整数有 相同 的权重，那么按照数字自身的数值
     * 升序排序 。
     *
     * <p>请你返回区间 [lo, hi] 之间的整数按权重排序后的第 k 个数。
     *
     * <p>注意，题目保证对于任意整数 x （lo <= x <= hi） ，它变成 1 所需要的步数是一个 32 位有符号整数。
     *
     * <p>示例 1：
     *
     * <p>输入：lo = 12, hi = 15, k = 2 输出：13 解释：12 的权重为 9（12 --> 6 --> 3 --> 10 --> 5 --> 16 --> 8 -->
     * 4 --> 2 --> 1） 13 的权重为 9 14 的权重为 17 15 的权重为 17 区间内的数按权重排序以后的结果为 [12,13,14,15] 。对于 k = 2
     * ，答案是第二个整数也就是 13 。 注意，12 和 13 有相同的权重，所以我们按照它们本身升序排序。14 和 15 同理。 示例 2：
     *
     * <p>输入：lo = 1, hi = 1, k = 1 输出：1 示例 3：
     *
     * <p>输入：lo = 7, hi = 11, k = 4 输出：7 解释：区间内整数 [7, 8, 9, 10, 11] 对应的权重为 [16, 3, 19, 6, 14] 。
     * 按权重排序后得到的结果为 [8, 10, 11, 7, 9] 。 排序后数组中第 4 个数字为 7 。 示例 4：
     *
     * <p>输入：lo = 10, hi = 20, k = 5 输出：13 示例 5：
     *
     * <p>输入：lo = 1, hi = 1000, k = 777 输出：570
     *
     * <p>提示：
     *
     * <p>1 <= lo <= hi <= 1000 1 <= k <= hi - lo + 1
     *
     * @param lo
     * @param hi
     * @param k
     * @return
     */
    public int getKth(int lo, int hi, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(1, 0);
        List<Integer> list = new ArrayList<>();
        for (int i = lo; i <= hi; i++) {
            list.add(i);
            map.put(i, getWeight(i, map));
        }
        list.sort(
                (a, b) -> {
                    int weight1 = map.get(a), weight2 = map.get(b);

                    return weight1 == weight2 ? a - b : weight1 - weight2;
                });
        return list.get(k - 1);
    }

    private int getWeight(int num, Map<Integer, Integer> map) {
        if (map.containsKey(num)) {
            return map.get(num);
        }
        int weight;
        if ((num & 1) == 1) {
            weight = getWeight(3 * num + 1, map);
        } else {
            weight = getWeight(num >> 1, map);
        }
        map.put(num, weight + 1);
        return weight + 1;
    }

    /**
     * 789. 逃脱阻碍者
     *
     * <p>你在进行一个简化版的吃豆人游戏。你从 (0, 0) 点开始出发，你的目的地是 (target[0], target[1]) 。地图上有一些阻碍者，第 i 个阻碍者从
     * (ghosts[i][0], ghosts[i][1]) 出发。
     *
     * <p>每一回合，你和阻碍者们*可以*同时向东，西，南，北四个方向移动，每次可以移动到距离原位置1个单位的新位置。
     *
     * <p>如果你可以在任何阻碍者抓住你之前到达目的地（阻碍者可以采取任意行动方式），则被视为逃脱成功。如果你和阻碍者同时到达了一个位置（包括目的地）都不算是逃脱成功。
     *
     * <p>当且仅当你有可能成功逃脱时，输出 True。
     *
     * <p>示例 1: 输入： ghosts = [[1, 0], [0, 3]] target = [0, 1] 输出：true 解释： 你可以直接一步到达目的地(0,1)，在(1,
     * 0)或者(0, 3)位置的阻碍者都不可能抓住你。 示例 2: 输入： ghosts = [[1, 0]] target = [2, 0] 输出：false 解释： 你需要走到位于(2,
     * 0)的目的地，但是在(1, 0)的阻碍者位于你和目的地之间。 示例 3: 输入： ghosts = [[2, 0]] target = [1, 0] 输出：false 解释：
     * 阻碍者可以和你同时达到目的地。 说明：
     *
     * <p>所有的点的坐标值的绝对值 <= 10000。 阻碍者的数量不会超过 100。
     *
     * @param ghosts
     * @param target
     * @return
     */
    public boolean escapeGhosts(int[][] ghosts, int[] target) {
        // 看主人和阻碍者谁先到达目的地即可，阻碍者可以守株待兔

        int distance = getDistance(new int[] {0, 0}, target);

        for (int[] ghost : ghosts) {
            if (getDistance(ghost, target) <= distance) {
                return false;
            }
        }
        return true;
    }

    private int getDistance(int[] start, int[] target) {
        // 曼哈顿距离 dist(A, B) = abs(A.x - B.x) + abs(A.y - B.y)
        return Math.abs(start[0] - target[0]) + Math.abs(start[1] - target[1]);
    }

    @Test
    public void smallestRepunitDivByK() {
        int k = 5;
        logResult(smallestRepunitDivByK(k));
    }

    /**
     * 1015. 可被 K 整除的最小整数
     *
     * <p>给定正整数 K，你需要找出可以被 K 整除的、仅包含数字 1 的最小正整数 N。
     *
     * <p>返回 N 的长度。如果不存在这样的 N，就返回 -1。
     *
     * <p>示例 1：
     *
     * <p>输入：1 输出：1 解释：最小的答案是 N = 1，其长度为 1。 示例 2：
     *
     * <p>输入：2 输出：-1 解释：不存在可被 2 整除的正整数 N 。 示例 3：
     *
     * <p>输入：3 输出：3 解释：最小的答案是 N = 111，其长度为 3。
     *
     * <p>提示：
     *
     * <p>1 <= K <= 10^5
     *
     * @param K
     * @return
     */
    public int smallestRepunitDivByK(int K) {
        if ((K & 1) == 0) {
            return -1;
        }
        if (K % 5 == 0) {
            return -1;
        }
        int count = 1, num = 1;

        while (num % K != 0) {
            num %= K;
            num = num * 10 + 1;
            count++;
        }
        return count;
    }

    /**
     * 858. 镜面反射
     *
     * <p>有一个特殊的正方形房间，每面墙上都有一面镜子。除西南角以外，每个角落都放有一个接受器，编号为 0， 1，以及 2。
     *
     * <p>正方形房间的墙壁长度为 p，一束激光从西南角射出，首先会与东墙相遇，入射点到接收器 0 的距离为 q 。
     *
     * <p>返回光线最先遇到的接收器的编号（保证光线最终会遇到一个接收器）。
     *
     * <p>示例：
     *
     * <p>输入： p = 2, q = 1 输出： 2 解释： 这条光线在第一次被反射回左边的墙时就遇到了接收器 2 。
     *
     * <p>提示：
     *
     * <p>1 <= p <= 1000 0 <= q <= p
     *
     * @param p
     * @param q
     * @return
     */
    public int mirrorReflection(int p, int q) {
        int g = getGcd(p, q);
        p /= g;
        p %= 2;
        q /= g;
        q %= 2;

        if (p == 1 && q == 1) return 1;
        return p == 1 ? 0 : 2;
    }

    /**
     * 869. 重新排序得到 2 的幂
     *
     * <p>给定正整数 N ，我们按任何顺序（包括原始顺序）将数字重新排序，注意其前导数字不能为零。
     *
     * <p>如果我们可以通过上述方式得到 2 的幂，返回 true；否则，返回 false。
     *
     * <p>示例 1：
     *
     * <p>输入：1 输出：true 示例 2：
     *
     * <p>输入：10 输出：false 示例 3：
     *
     * <p>输入：16 输出：true 示例 4：
     *
     * <p>输入：24 输出：false 示例 5：
     *
     * <p>输入：46 输出：true
     *
     * <p>提示：
     *
     * <p>1 <= N <= 10^9
     *
     * @param N
     * @return
     */
    public boolean reorderedPowerOf2(int N) {
        if (isPowerOfTwo(N)) {
            return true;
        }
        String[][] nums = {
            {"1", "2", "4", "8"},
            {"16", "32", "64"},
            {"128", "256", "512"},
            {"1024", "2048", "4096", "8192"},
            {"16384", "32768", "65536"},
            {"131072", "262144", "524288"},
            {"1048576", "2097152", "4194304", "8388608"},
            {"16777216", "33554432", "67108864"},
            {"134217728", "268435456", "536870912"}
        };
        String num = String.valueOf(N);
        String[] list = nums[num.length() - 1];
        for (String str : list) {
            if (compareNum(str, num)) {
                return true;
            }
        }

        return false;
    }

    private boolean compareNum(String num1, String num2) {
        int[] nums = new int[10];
        for (int i = 0; i < num1.length(); i++) {
            char c1 = num1.charAt(i), c2 = num2.charAt(i);
            nums[c1 - '0']++;
            nums[c2 - '0']--;
        }

        for (int i = 0; i < 10; i++) {
            if (nums[i] != 0) {
                return false;
            }
        }

        return true;
    }

    /**
     * 866. 回文素数
     *
     * <p>求出大于或等于 N 的最小回文素数。
     *
     * <p>回顾一下，如果一个数大于 1，且其因数只有 1 和它自身，那么这个数是素数。
     *
     * <p>例如，2，3，5，7，11 以及 13 是素数。
     *
     * <p>回顾一下，如果一个数从左往右读与从右往左读是一样的，那么这个数是回文数。
     *
     * <p>例如，12321 是回文数。
     *
     * <p>示例 1：
     *
     * <p>输入：6 输出：7 示例 2：
     *
     * <p>输入：8 输出：11 示例 3：
     *
     * <p>输入：13 输出：101
     *
     * <p>提示：
     *
     * <p>1 <= N <= 10^8 答案肯定存在，且小于 2 * 10^8。
     *
     * @param N
     * @return
     */
    public int primePalindrome(int N) {
        while (true) {
            if (N == reverse(N) && isPrime(N)) {
                return N;
            }
            N++;
            if (10_000_000 < N && N < 100_000_000) {
                N = 100_000_000;
            }
        }
    }

    private int reverse(int num) {
        int result = 0;
        while (num > 0) {
            result = result * 10 + num % 10;
            num /= 10;
        }

        return result;
    }

    /**
     * 1041. 困于环中的机器人
     *
     * <p>在无限的平面上，机器人最初位于 (0, 0) 处，面朝北方。机器人可以接受下列三条指令之一：
     *
     * <p>"G"：直走 1 个单位 "L"：左转 90 度 "R"：右转 90 度 机器人按顺序执行指令 instructions，并一直重复它们。
     *
     * <p>只有在平面中存在环使得机器人永远无法离开时，返回 true。否则，返回 false。
     *
     * <p>示例 1：
     *
     * <p>输入："GGLLGG" 输出：true 解释： 机器人从 (0,0) 移动到 (0,2)，转 180 度，然后回到 (0,0)。 重复这些指令，机器人将保持在以原点为中心，2
     * 为半径的环中进行移动。 示例 2：
     *
     * <p>输入："GG" 输出：false 解释： 机器人无限向北移动。 示例 3：
     *
     * <p>输入："GL" 输出：true 解释： 机器人按 (0, 0) -> (0, 1) -> (-1, 1) -> (-1, 0) -> (0, 0) -> ... 进行移动。
     *
     * <p>提示：
     *
     * <p>1 <= instructions.length <= 100 instructions[i] 在 {'G', 'L', 'R'} 中
     *
     * @param instructions
     * @return
     */
    public boolean isRobotBounded(String instructions) {
        int x = 0, y = 0;
        // 0 上 1 左 2 下 3 右
        int flag = 0;
        for (char c : instructions.toCharArray()) {
            switch (c) {
                case 'L':
                    {
                        flag++;
                        flag %= 4;
                    }
                    ;
                    break;
                case 'R':
                    {
                        flag += 3;
                        flag %= 4;
                    }
                    ;
                    break;
                case 'G':
                    {
                        switch (flag) {
                            case 0:
                                y++;
                                break;
                            case 1:
                                x--;
                                break;
                            case 2:
                                y--;
                                break;
                            case 3:
                                x++;
                                break;
                        }
                    }
                    ;
                    break;
            }
        }

        if (x == 0 && y == 0) {
            return true;
        }
        return flag != 0;
    }

    @Test
    public void smallestRangeII() {
        int[] A = {1, 3, 6};
        int K = 3;
        logResult(smallestRangeII(A, K));
    }

    /**
     * 910. 最小差值 II
     *
     * <p>给定一个整数数组 A，对于每个整数 A[i]，我们可以选择 x = -K 或是 x = K，并将 x 加到 A[i] 中。
     *
     * <p>在此过程之后，我们得到一些数组 B。
     *
     * <p>返回 B 的最大值和 B 的最小值之间可能存在的最小差值。
     *
     * <p>示例 1：
     *
     * <p>输入：A = [1], K = 0 输出：0 解释：B = [1] 示例 2：
     *
     * <p>输入：A = [0,10], K = 2 输出：6 解释：B = [2,8] 示例 3：
     *
     * <p>输入：A = [1,3,6], K = 3 输出：3 解释：B = [4,6,3]
     *
     * <p>提示：
     *
     * <p>1 <= A.length <= 10000 0 <= A[i] <= 10000 0 <= K <= 10000
     *
     * @param A
     * @param K
     * @return
     */
    public int smallestRangeII(int[] A, int K) {
        int len = A.length;
        if (len == 1) {
            return 0;
        }
        Arrays.sort(A);
        if (K == 0) {
            return A[len - 1] - A[0];
        }
        int result = A[len - 1] - A[0];
        // 排序后的数组中，一定有一个位置 i，i 本身及左测全部加 K,i 右侧全部减 K。
        // 即A[0]..A[i]全部加上K，A[i+1]..A[n-1]全部减去 K，
        // 此时整个数组的最大值是 A[n-1]-K 或 A[i]+K，最小值是 A[0]+K 或 A[i+1]-K
        for (int i = 0; i < len - 1; i++) {
            int min = Math.min(A[0] + K, A[i + 1] - K);
            int max = Math.max(A[len - 1] - K, A[i] + K);
            result = Math.min(result, max - min);
        }

        return result;
    }

    /**
     * 5561. 获取生成数组中的最大值
     *
     * <p>给你一个整数 n 。按下述规则生成一个长度为 n + 1 的数组 nums ：
     *
     * <p>nums[0] = 0 nums[1] = 1 当 2 <= 2 * i <= n 时，nums[2 * i] = nums[i] 当 2 <= 2 * i + 1 <= n
     * 时，nums[2 * i + 1] = nums[i] + nums[i + 1] 返回生成数组 nums 中的 最大 值。
     *
     * <p>示例 1：
     *
     * <p>输入：n = 7 输出：3 解释：根据规则： nums[0] = 0 nums[1] = 1 nums[(1 * 2) = 2] = nums[1] = 1 nums[(1 *
     * 2) + 1 = 3] = nums[1] + nums[2] = 1 + 1 = 2 nums[(2 * 2) = 4] = nums[2] = 1 nums[(2 * 2) + 1
     * = 5] = nums[2] + nums[3] = 1 + 2 = 3 nums[(3 * 2) = 6] = nums[3] = 2 nums[(3 * 2) + 1 = 7] =
     * nums[3] + nums[4] = 2 + 1 = 3 因此，nums = [0,1,1,2,1,3,2,3]，最大值 3 示例 2：
     *
     * <p>输入：n = 2 输出：1 解释：根据规则，nums[0]、nums[1] 和 nums[2] 之中的最大值是 1 示例 3：
     *
     * <p>输入：n = 3 输出：2 解释：根据规则，nums[0]、nums[1]、nums[2] 和 nums[3] 之中的最大值是 2
     *
     * <p>提示：
     *
     * <p>0 <= n <= 100
     *
     * @param n
     * @return
     */
    public int getMaximumGenerated(int n) {
        int max = 0;
        int[] nums = new int[n + 1];
        if (n > 0) {
            nums[1] = 1;
            max = 1;
        }
        for (int i = 2; i <= n; i++) {
            int idx = i >> 1;
            if ((i & 1) == 1) {
                nums[i] = nums[idx] + nums[idx + 1];
            } else {
                nums[i] = nums[idx];
            }
            max = Math.max(max, nums[i]);
        }

        return max;
    }

    @Test
    public void getMaximumGenerated() {
        int n = 4;
        logResult(getMaximumGenerated(n));
    }

    /**
     * 991. 坏了的计算器
     *
     * <p>在显示着数字的坏计算器上，我们可以执行以下两种操作：
     *
     * <p>双倍（Double）：将显示屏上的数字乘 2； 递减（Decrement）：将显示屏上的数字减 1 。 最初，计算器显示数字 X。
     *
     * <p>返回显示数字 Y 所需的最小操作数。
     *
     * <p>示例 1：
     *
     * <p>输入：X = 2, Y = 3 输出：2 解释：先进行双倍运算，然后再进行递减运算 {2 -> 4 -> 3}. 示例 2：
     *
     * <p>输入：X = 5, Y = 8 输出：2 解释：先递减，再双倍 {5 -> 4 -> 8}. 示例 3：
     *
     * <p>输入：X = 3, Y = 10 输出：3 解释：先双倍，然后递减，再双倍 {3 -> 6 -> 5 -> 10}. 示例 4：
     *
     * <p>输入：X = 1024, Y = 1 输出：1023 解释：执行递减运算 1023 次
     *
     * <p>提示：
     *
     * <p>1 <= X <= 10^9 1 <= Y <= 10^9
     *
     * @param X
     * @param Y
     * @return
     */
    public int brokenCalc(int X, int Y) {
        if (X >= Y) {
            return X - Y;
        }
        // 逆向思维 Y -> X ; Y 是 奇数 + 1 Y 是偶数 / 2
        int count = 0;
        while (Y > X) {
            if ((Y & 1) == 1) {
                Y++;
            } else {
                Y >>= 1;
            }
            count++;
        }

        return count + X - Y;
    }

    /**
     * 1006. 笨阶乘
     *
     * <p>通常，正整数 n 的阶乘是所有小于或等于 n 的正整数的乘积。例如，factorial(10) = 10 * 9 * 8 * 7 * 6 * 5 * 4 * 3 * 2 * 1。
     *
     * <p>相反，我们设计了一个笨阶乘 clumsy：在整数的递减序列中，我们以一个固定顺序的操作符序列来依次替换原有的乘法操作符：乘法(*)，除法(/)，加法(+)和减法(-)。
     *
     * <p>例如，clumsy(10) = 10 * 9 / 8 + 7 - 6 * 5 / 4 + 3 - 2 *
     * 1。然而，这些运算仍然使用通常的算术运算顺序：我们在任何加、减步骤之前执行所有的乘法和除法步骤，并且按从左到右处理乘法和除法步骤。
     *
     * <p>另外，我们使用的除法是地板除法（floor division），所以 10 * 9 / 8 等于 11。这保证结果是一个整数。
     *
     * <p>实现上面定义的笨函数：给定一个整数 N，它返回 N 的笨阶乘。
     *
     * <p>示例 1：
     *
     * <p>输入：4 输出：7 解释：7 = 4 * 3 / 2 + 1 示例 2：
     *
     * <p>输入：10 输出：12 解释：12 = 10 * 9 / 8 + 7 - 6 * 5 / 4 + 3 - 2 * 1
     *
     * <p>提示：
     *
     * <p>1 <= N <= 10000 -2^31 <= answer <= 2^31 - 1 （答案保证符合 32 位整数。）
     *
     * @param N
     * @return
     */
    public int clumsy(int N) {
        int result = 0;
        for (int i = N - 3; i > 0; i -= 4) {
            result += i;
        }
        for (int i = N; i > 0; i -= 4) {
            int num = i;
            if (i - 1 > 0) {
                num *= i - 1;
            }
            if (i - 2 > 0) {
                num /= i - 2;
            }
            if (i == N) {
                result += num;
            } else {
                result -= num;
            }
        }

        return result;
    }

    /**
     * 1017. 负二进制转换
     *
     * <p>给出数字 N，返回由若干 "0" 和 "1"组成的字符串，该字符串为 N 的负二进制（base -2）表示。
     *
     * <p>除非字符串就是 "0"，否则返回的字符串中不能含有前导零。
     *
     * <p>示例 1：
     *
     * <p>输入：2 输出："110" 解释：(-2) ^ 2 + (-2) ^ 1 = 2 示例 2：
     *
     * <p>输入：3 输出："111" 解释：(-2) ^ 2 + (-2) ^ 1 + (-2) ^ 0 = 3 示例 3：
     *
     * <p>输入：4 输出："100" 解释：(-2) ^ 2 = 4
     *
     * <p>提示：
     *
     * <p>0 <= N <= 10^9
     *
     * @param N
     * @return
     */
    public String baseNeg2(int N) {
        if (N == 0) {
            return "0";
        }
        StringBuilder sb = new StringBuilder();
        while (N != 0) {
            int mod = N % -2;
            sb.append(mod == -1 ? 1 : mod);
            N /= -2;
            if (mod == -1) {
                // 前进一位
                N++;
            }
        }

        return sb.reverse().toString();
    }

    /**
     * 273. 整数转换英文表示
     *
     * <p>将非负整数 num 转换为其对应的英文表示。
     *
     * <p>示例 1：
     *
     * <p>输入：num = 123 输出："One Hundred Twenty Three" 示例 2：
     *
     * <p>输入：num = 12345 输出："Twelve Thousand Three Hundred Forty Five" 示例 3：
     *
     * <p>输入：num = 1234567 输出："One Million Two Hundred Thirty Four Thousand Five Hundred Sixty
     * Seven" 示例 4：
     *
     * <p>输入：num = 1234567891 输出："One Billion Two Hundred Thirty Four Million Five Hundred Sixty
     * Seven Thousand Eight Hundred Ninety One"
     *
     * <p>提示：
     *
     * <p>0 <= num <= 231 - 1
     *
     * @param num
     * @return
     */
    public String numberToWords2(int num) {
        if (num == 0) {
            return "Zero";
        }
        List<String> list = new ArrayList<>();
        numberToWordList(num, list);
        return list.stream().filter(word -> word.length() > 0).collect(Collectors.joining(" "));
    }

    @Test
    public void numberToWords() {
        int num = 20;
        logResult(numberToWords2(num));
    }

    private void numberToWordList(int num, List<String> list) {
        int[] factors = {1000000000, 1000000, 1000};
        for (int i = 0; i < 3; i++) {
            if (num >= factors[i]) {
                // 前缀
                int prefixNum = num / factors[i];
                num %= factors[i];
                numberToWordList(prefixNum, list);
                list.add(GENS[i]);
            }
        }
        if (num >= 100) {
            int hundredNum = num / 100;
            num %= 100;
            numberToWordList(hundredNum, list);
            list.add("Hundred");
        }
        if (num >= 20) {
            int tenIndex = num / 10;
            num %= 10;
            list.add(NUM_0_90[tenIndex]);
        }
        list.add(NUM_0_19[num]);
    }

    static int[] DIR_X = {0, -1, 0, 1};
    static int[] DIR_Y = {1, 0, -1, 0};

    /**
     * 335. 路径交叉
     *
     * <p>给定一个含有 n 个正数的数组 x。从点 (0,0) 开始，先向北移动 x[0] 米，然后向西移动 x[1] 米，向南移动 x[2] 米，向东移动 x[3]
     * 米，持续移动。也就是说，每次移动后你的方位会发生逆时针变化。
     *
     * <p>编写一个 O(1) 空间复杂度的一趟扫描算法，判断你所经过的路径是否相交。
     *
     * <p>示例 1:
     *
     * <p>┌───┐ │ │ └───┼──> │
     *
     * <p>输入: [2,1,1,2] 输出: true 示例 2:
     *
     * <p>┌──────┐ │ │ │ │ └────────────>
     *
     * <p>输入: [1,2,3,4] 输出: false 示例 3:
     *
     * <p>┌───┐ │ │ └───┼>
     *
     * <p>输入: [1,1,1,1] 输出: true
     *
     * @param x
     * @return
     */
    public boolean isSelfCrossing(int[] x) {
        int len = x.length;
        if (len < 4) {
            return false;
        }
        // 交叉只有三种可能：
        //
        // 第一种 条件为 i>=3 && x[i] >= x[i-2] && x[i-1] <= x[i-3]
        //
        // 第二种 条件为 i> 3 && x[i-1] == x[i-3] && x[i-4] + x[i] >= x[i-2]

        // 第三种 条件为 i> 4 && x[i-3]-x[i-5] <= x[i-1] <= x[i-3] && x[i-2]-x[i-4] <= x[i] <= x[i-2] &&
        // x[i-2] > x[i-4]
        for (int i = 3; i < len; i++) {
            if (x[i] >= x[i - 2] && x[i - 1] <= x[i - 3]) {
                return true;
            }
            if (i > 3 && x[i - 1] == x[i - 3] && x[i - 4] + x[i] >= x[i - 2]) {
                return true;
            }
            if (i > 4
                    && x[i - 3] - x[i - 5] <= x[i - 1]
                    && x[i - 1] <= x[i - 3]
                    && x[i - 2] - x[i - 4] <= x[i]
                    && x[i] <= x[i - 2]
                    && x[i - 2] > x[i - 4]) {
                return true;
            }
        }

        return false;
    }

    @Test
    public void isSelfCrossing() {
        int[] nums = {1, 1, 2, 1, 1};
        logResult(isSelfCrossing(nums));
    }

    /**
     * 440. 字典序的第K小数字
     *
     * <p>给定整数 n 和 k，找到 1 到 n 中字典序第 k 小的数字。
     *
     * <p>注意：1 ≤ k ≤ n ≤ 109。
     *
     * <p>示例 :
     *
     * <p>输入: n: 13 k: 2
     *
     * <p>输出: 10
     *
     * <p>解释: 字典序的排列是 [1, 10, 11, 12, 13, 2, 3, 4, 5, 6, 7, 8, 9]，所以第二小的数字是 10。
     *
     * @param n
     * @param k
     * @return
     */
    public int findKthNumber(int n, int k) {
        int num = 1;
        k--; // 扣除掉第一个0节点
        while (k > 0) {
            int count = getCount(n, num, num + 1);
            if (count <= k) { // 第k个数不在以cur为根节点的树上
                num += 1; // cur在字典序数组中从左往右移动
                k -= count;
            } else { // 在子树中
                num *= 10; // cur在字典序数组中从上往下移动
                k -= 1; // 刨除根节点
            }
        }
        return num;
    }

    public int getCount(int n, long first, long last) {
        int count = 0;
        while (first <= n) {
            count += Math.min(n + 1, last) - first; // 比如n是195的情况195到100有96个数
            first *= 10;
            last *= 10;
        }
        return count;
    }

    /**
     * 5620. 连接连续二进制数字
     *
     * <p>给你一个整数 n ，请你将 1 到 n 的二进制表示连接起来，并返回连接结果对应的 十进制 数字对 109 + 7 取余的结果。
     *
     * <p>示例 1：
     *
     * <p>输入：n = 1 输出：1 解释：二进制的 "1" 对应着十进制的 1 。 示例 2：
     *
     * <p>输入：n = 3 输出：27 解释：二进制下，1，2 和 3 分别对应 "1" ，"10" 和 "11" 。 将它们依次连接，我们得到 "11011" ，对应着十进制的 27 。
     * 示例 3：
     *
     * <p>输入：n = 12 输出：505379714 解释：连接结果为 "1101110010111011110001001101010111100" 。 对应的十进制数字为
     * 118505380540 。 对 109 + 7 取余后，结果为 505379714 。
     *
     * <p>提示：
     *
     * <p>1 <= n <= 105
     *
     * @param n
     * @return
     */
    public int concatenatedBinary(int n) {
        long result = 0;
        int num = 1, bit = 0;
        for (int i = 1; i <= n; i++) {
            if (i >= num) {
                num <<= 1;
                bit++;
            }
            result <<= bit;
            result %= MOD;
            result += i % MOD;
            result %= MOD;
        }

        return (int) result;
    }

    static final int MOD = 1_000_000_007;

    @Test
    public void concatenatedBinary() {
        int n = 12;
        logResult(concatenatedBinary(n));
    }

    /**
     * 458. 可怜的小猪
     *
     * <p>有 buckets 桶液体，其中 正好
     * 有一桶含有毒药，其余装的都是水。它们从外观看起来都一样。为了弄清楚哪只水桶含有毒药，你可以喂一些猪喝，通过观察猪是否会死进行判断。不幸的是，你只有 minutesToTest
     * 分钟时间来确定哪桶液体是有毒的。
     *
     * <p>喂猪的规则如下：
     *
     * <p>选择若干活猪进行喂养 可以允许小猪同时饮用任意数量的桶中的水，并且该过程不需要时间。 小猪喝完水后，必须有 minutesToDie
     * 分钟的冷却时间。在这段时间里，你只能观察，而不允许继续喂猪。 过了 minutesToDie 分钟后，所有喝到毒药的猪都会死去，其他所有猪都会活下来。 重复这一过程，直到时间用完。
     * 给你桶的数目 buckets ，minutesToDie 和 minutesToTest ，返回在规定时间内判断哪个桶有毒所需的 最小 猪数。
     *
     * <p>示例 1：
     *
     * <p>输入：buckets = 1000, minutesToDie = 15, minutesToTest = 60 输出：5 示例 2：
     *
     * <p>输入：buckets = 4, minutesToDie = 15, minutesToTest = 15 输出：2 示例 3：
     *
     * <p>输入：buckets = 4, minutesToDie = 15, minutesToTest = 30 输出：2
     *
     * <p>提示：
     *
     * <p>1 <= buckets <= 1000 1 <= minutesToDie <= minutesToTest <= 100
     *
     * @param buckets
     * @param minutesToDie
     * @param minutesToTest
     * @return
     */
    public int poorPigs(int buckets, int minutesToDie, int minutesToTest) {
        int states = minutesToTest / minutesToDie + 1;
        return (int) Math.ceil(Math.log(buckets) / Math.log(states));
    }

    /**
     * 479. 最大回文数乘积
     *
     * <p>你需要找到由两个 n 位数的乘积组成的最大回文数。
     *
     * <p>由于结果会很大，你只需返回最大回文数 mod 1337得到的结果。
     *
     * <p>示例:
     *
     * <p>输入: 2
     *
     * <p>输出: 987
     *
     * <p>解释: 99 x 91 = 9009, 9009 % 1337 = 987
     *
     * <p>说明:
     *
     * <p>n 的取值范围为 [1,8]。
     *
     * @param n
     * @return
     */
    public int largestPalindrome(int n) {
        if (n == 1) {
            return 9;
        }
        int max = 1;
        for (int i = 0; i < n; i++) {
            max *= 10;
        }
        int mod = 1337;
        max--;
        // 而相乘可以构成9的尾数只有3(33),7(77),9(9*1)
        for (int i = max - 1; i > max / 10; i--) {
            // 构造回文数 9889 后缀
            long prev = i, sufNum = i;
            while (sufNum > 0) {
                prev = prev * 10 + sufNum % 10;
                sufNum /= 10;
            }
            long num = max;
            while (num > 0 && num * num >= prev) {
                if (prev % num == 0) {
                    return (int) (prev % 1337);
                } else if (num % 10 == 9) {
                    num -= 2;
                } else {
                    num -= 4;
                }
            }
        }
        return -1;
    }

    @Test
    public void largestPalindrome() {
        int n = 5;
        logResult(largestPalindrome(n));
    }

    /**
     * 483. 最小好进制
     *
     * <p>对于给定的整数 n, 如果n的k（k>=2）进制数的所有数位全为1，则称 k（k>=2）是 n 的一个好进制。
     *
     * <p>以字符串的形式给出 n, 以字符串的形式返回 n 的最小好进制。
     *
     * <p>示例 1：
     *
     * <p>输入："13" 输出："3" 解释：13 的 3 进制是 111。 示例 2：
     *
     * <p>输入："4681" 输出："8" 解释：4681 的 8 进制是 11111。 示例 3：
     *
     * <p>输入："1000000000000000000" 输出："999999999999999999" 解释：1000000000000000000 的
     * 999999999999999999 进制是 11。
     *
     * <p>提示：
     *
     * <p>n的取值范围是 [3, 10^18]。 输入总是有效且没有前导 0。
     *
     * @param n
     * @return
     */
    public String smallestGoodBase(String n) {
        long num = Long.valueOf(n);
        for (int m = 59; m > 1; m--) {
            long k = (long) Math.pow(num, 1.0 / m);
            // 不存在1进制，如果k<=1，直接下一次
            if (k <= 1) {
                continue;
            }
            long s = 0L;
            for (int i = 0; i <= m; i++) {
                s = s * k + 1;
            }
            if (s == num) {
                return String.valueOf(k);
            }
        }
        return String.valueOf(num - 1);
    }

    /**
     * 5625. 比赛中的配对次数
     *
     * <p>给你一个整数 n ，表示比赛中的队伍数。比赛遵循一种独特的赛制：
     *
     * <p>如果当前队伍数是 偶数 ，那么每支队伍都会与另一支队伍配对。总共进行 n / 2 场比赛，且产生 n / 2 支队伍进入下一轮。 如果当前队伍数为 奇数
     * ，那么将会随机轮空并晋级一支队伍，其余的队伍配对。总共进行 (n - 1) / 2 场比赛，且产生 (n - 1) / 2 + 1 支队伍进入下一轮。
     * 返回在比赛中进行的配对次数，直到决出获胜队伍为止。
     *
     * <p>示例 1：
     *
     * <p>输入：n = 7 输出：6 解释：比赛详情： - 第 1 轮：队伍数 = 7 ，配对次数 = 3 ，4 支队伍晋级。 - 第 2 轮：队伍数 = 4 ，配对次数 = 2 ，2
     * 支队伍晋级。 - 第 3 轮：队伍数 = 2 ，配对次数 = 1 ，决出 1 支获胜队伍。 总配对次数 = 3 + 2 + 1 = 6 示例 2：
     *
     * <p>输入：n = 14 输出：13 解释：比赛详情： - 第 1 轮：队伍数 = 14 ，配对次数 = 7 ，7 支队伍晋级。 - 第 2 轮：队伍数 = 7 ，配对次数 = 3 ，4
     * 支队伍晋级。 - 第 3 轮：队伍数 = 4 ，配对次数 = 2 ，2 支队伍晋级。 - 第 4 轮：队伍数 = 2 ，配对次数 = 1 ，决出 1 支获胜队伍。 总配对次数 = 7 +
     * 3 + 2 + 1 = 13
     *
     * <p>提示：
     *
     * <p>1 <= n <= 200
     *
     * @param n
     * @return
     */
    public int numberOfMatches(int n) {
        if (n == 1) {
            return 0;
        }
        int count = n >> 1;
        matchResult += count;
        if ((n & 1) == 1) {
            count++;
        }
        numberOfMatches(count);

        return matchResult;
    }

    int matchResult = 0;

    /**
     * 5626. 十-二进制数的最少数目
     *
     * <p>如果一个十进制数字不含任何前导零，且每一位上的数字不是 0 就是 1 ，那么该数字就是一个 十-二进制数 。例如，101 和 1100 都是 十-二进制数，而 112 和 3001
     * 不是。
     *
     * <p>给你一个表示十进制整数的字符串 n ，返回和为 n 的 十-二进制数 的最少数目。
     *
     * <p>示例 1：
     *
     * <p>输入：n = "32" 输出：3 解释：10 + 11 + 11 = 32 示例 2：
     *
     * <p>输入：n = "82734" 输出：8 示例 3：
     *
     * <p>输入：n = "27346209830709182346" 输出：9
     *
     * <p>提示：
     *
     * <p>1 <= n.length <= 105 n 仅由数字组成 n 不含任何前导零并总是表示正整数
     *
     * @param n
     * @return
     */
    public int minPartitions(String n) {
        char[] chars = n.toCharArray();
        int len = n.length();
        int max = 0;
        for (int i = 0; i < len; i++) {
            int num = chars[i] - '0';
            max = Math.max(num, max);
        }

        return max;
    }

    /**
     * 587. 安装栅栏
     *
     * <p>在一个二维的花园中，有一些用 (x, y)
     * 坐标表示的树。由于安装费用十分昂贵，你的任务是先用最短的绳子围起所有的树。只有当所有的树都被绳子包围时，花园才能围好栅栏。你需要找到正好位于栅栏边界上的树的坐标。
     *
     * <p>示例 1:
     *
     * <p>输入: [[1,1],[2,2],[2,0],[2,4],[3,3],[4,2]] 输出: [[1,1],[2,0],[4,2],[3,3],[2,4]] 解释:
     *
     * <p>示例 2:
     *
     * <p>输入: [[1,2],[2,2],[4,2]] 输出: [[1,2],[2,2],[4,2]] 解释:
     *
     * <p>即使树都在一条直线上，你也需要先用绳子包围它们。
     *
     * <p>注意:
     *
     * <p>所有的树应当被围在一起。你不能剪断绳子来包围树或者把树分成一组以上。 输入的整数在 0 到 100 之间。 花园至少有一棵树。 所有树的坐标都是不同的。
     * 输入的点没有顺序。输出顺序也没有要求。
     *
     * @param points
     * @return
     */
    public int[][] outerTrees(int[][] points) {
        int len = points.length;
        if (len <= 3) {
            return points;
        }
        // 找到最左端的点
        final int[] left = getLeft(points);
        Arrays.sort(
                points,
                (p, q) -> {
                    int diff = orientation(left, p, q) - orientation(left, q, p);
                    if (diff == 0) {
                        return distance(left, p) - distance(left, q);
                    }
                    return diff > 0 ? 1 : -1;
                });
        // 最后一条边(连接到起点)需要交换顺序, 否则会缺少点
        int idx = points.length - 1;
        while (idx > 0 && orientation(left, points[points.length - 1], points[idx - 1]) == 0) {
            idx--;
        }
        for (int l = idx, r = points.length - 1; l < r; l++, r--) {
            int[] tmp = points[l];
            points[l] = points[r];
            points[r] = tmp;
        }

        Deque<int[]> stack = new LinkedList<>();

        for (int[] point : points) {
            if (stack.size() < 2) {
                stack.push(point);
                continue;
            }
            int[] top = stack.pop();
            while (orientation(stack.peek(), top, point) > 0) {
                top = stack.pop();
            }

            stack.push(top);
            stack.push(point);
        }
        int[][] result = new int[stack.size()][];
        for (int i = stack.size() - 1; i >= 0; i--) {
            result[i] = stack.pop();
        }
        return result;
    }

    public int[][] outerTrees2(int[][] points) {
        int len = points.length;
        if (len <= 3) {
            return points;
        }
        // 找到最左端的点
        Arrays.sort(points, (p, q) -> p[0] == q[0] ? p[1] - q[1] : p[0] - q[0]);
        logResult(points);
        Stack<int[]> stack = new Stack<>();
        // 从左到右
        for (int[] point : points) {

            while (stack.size() >= 2
                    && orientation(stack.get(stack.size() - 2), stack.get(stack.size() - 1), point)
                            > 0) {
                stack.pop();
            }
            stack.push(point);
        }
        stack.pop();
        for (int i = points.length - 1; i >= 0; i--) {
            while (stack.size() >= 2
                    && orientation(
                                    stack.get(stack.size() - 2),
                                    stack.get(stack.size() - 1),
                                    points[i])
                            > 0) {
                stack.pop();
            }

            stack.push(points[i]);
        }

        // 从右到左
        int[][] result = new int[stack.size()][];
        for (int i = stack.size() - 1; i >= 0; i--) {
            result[i] = stack.pop();
        }
        return result;
    }

    private int[] getLeft(int[][] points) {
        int[] left = points[0];
        for (int i = 1; i < points.length; i++) {
            if (points[i][0] < left[0]) {
                left = points[i];
            } else if (points[i][0] == left[0] && points[i][1] == left[1]) {
                left = points[i];
            }
        }
        return left;
    }

    private int orientation(int[] left, int[] p, int[] q) {
        return (p[1] - left[1]) * (q[0] - left[0]) - (p[0] - left[0]) * (q[1] - left[1]);
    }

    private int distance(int[] p, int[] q) {
        return (p[0] - q[0]) * (p[0] - q[0]) + (p[1] - q[1]) * (p[1] - q[1]);
    }

    @Test
    public void outerTrees() {
        int[][] points = {
            {3, 0}, {4, 0}, {5, 0}, {6, 1}, {7, 2}, {7, 3}, {7, 4}, {6, 5}, {5, 5}, {4, 5}, {3, 5},
            {2, 5}, {1, 4}, {1, 3}, {1, 2}, {2, 1}, {4, 2}, {0, 3}
        };
        logResult(outerTrees(points));
    }

    /**
     * 5633. 计算力扣银行的钱 Hercy
     *
     * <p>想要为购买第一辆车存钱。他 每天 都往力扣银行里存钱。
     *
     * <p>最开始，他在周一的时候存入 1 块钱。从周二到周日，他每天都比前一天多存入 1 块钱。在接下来每一个周一，他都会比 前一个周一 多存入 1 块钱。
     *
     * <p>给你 n ，请你返回在第 n 天结束的时候他在力扣银行总共存了多少块钱。
     *
     * <p>示例 1：
     *
     * <p>输入：n = 4 输出：10 解释：第 4 天后，总额为 1 + 2 + 3 + 4 = 10 。 示例 2：
     *
     * <p>输入：n = 10 输出：37 解释：第 10 天后，总额为 (1 + 2 + 3 + 4 + 5 + 6 + 7) + (2 + 3 + 4) = 37
     * 。注意到第二个星期一，Hercy 存入 2 块钱。 示例 3：
     *
     * <p>输入：n = 20 输出：96 解释：第 20 天后，总额为 (1 + 2 + 3 + 4 + 5 + 6 + 7) + (2 + 3 + 4 + 5 + 6 + 7 + 8) +
     * (3 + 4 + 5 + 6 + 7 + 8) = 96 。
     *
     * <p>提示：
     *
     * <p>1 <= n <= 1000
     *
     * @param n
     * @return
     */
    public int totalMoney(int n) {
        int count = n / 7;
        int result = 0;
        int num = n % 7;
        result += 28 * count;
        result += ((count - 1) * count / 2) * 7;

        if (num > 0) {
            result += (1 + num) * num / 2;
            result += num * count;
        }

        return result;
    }

    @Test
    public void totalMoney() {
        int n = 20;
        logResult(totalMoney(n));
    }

    public int minimumBoxes(int n) {
        int num = n * 6;

        int last = (int) Math.pow(num, 1.0 / 3);
        log.debug("last:{}", last);
        int total = last * (last + 1) * (last + 2) / 6;
        log.debug("total:{}", total);
        int result = last * (last + 1) / 2 + (total - n);

        return result;
    }

    /**
     * 5654. 盒子中小球的最大数量
     *
     * <p>你在一家生产小球的玩具厂工作，有 n 个小球，编号从 lowLimit 开始，到 highLimit 结束（包括 lowLimit 和 highLimit ，即 n ==
     * highLimit - lowLimit + 1）。另有无限数量的盒子，编号从 1 到 infinity 。
     *
     * <p>你的工作是将每个小球放入盒子中，其中盒子的编号应当等于小球编号上每位数字的和。例如，编号 321 的小球应当放入编号 3 + 2 + 1 = 6 的盒子，而编号 10
     * 的小球应当放入编号 1 + 0 = 1 的盒子。
     *
     * <p>给你两个整数 lowLimit 和 highLimit ，返回放有最多小球的盒子中的小球数量。如果有多个盒子都满足放有最多小球，只需返回其中任一盒子的小球数量。
     *
     * <p>示例 1：
     *
     * <p>输入：lowLimit = 1, highLimit = 10 输出：2 解释： 盒子编号：1 2 3 4 5 6 7 8 9 10 11 ... 小球数量：2 1 1 1 1 1
     * 1 1 1 0 0 ... 编号 1 的盒子放有最多小球，小球数量为 2 。 示例 2：
     *
     * <p>输入：lowLimit = 5, highLimit = 15 输出：2 解释： 盒子编号：1 2 3 4 5 6 7 8 9 10 11 ... 小球数量：1 1 1 1 2 2
     * 1 1 1 0 0 ... 编号 5 和 6 的盒子放有最多小球，每个盒子中的小球数量都是 2 。 示例 3：
     *
     * <p>输入：lowLimit = 19, highLimit = 28 输出：2 解释： 盒子编号：1 2 3 4 5 6 7 8 9 10 11 12 ... 小球数量：0 1 1 1
     * 1 1 1 1 1 2 0 0 ... 编号 10 的盒子放有最多小球，小球数量为 2 。
     *
     * @param lowLimit
     * @param highLimit
     * @return
     */
    public int countBalls(int lowLimit, int highLimit) {
        // 暴力
        int max = 0;
        Map<Integer, Integer> countMap = new HashMap<>();
        // 动态规划
        for (int i = lowLimit; i <= highLimit; i++) {
            int num = getNums(i);
            int count = countMap.getOrDefault(num, 0) + 1;
            max = Math.max(max, count);
            countMap.put(num, count);
        }

        return max;
    }

    private int getNums(int num) {
        int result = 0;
        while (num > 0) {
            result += num % 10;
            num /= 10;
        }
        return result;
    }

    /**
     * 5667. 你能在你最喜欢的那天吃到你最喜欢的糖果吗？
     *
     * <p>给你一个下标从 0 开始的正整数数组 candiesCount ，其中 candiesCount[i] 表示你拥有的第 i 类糖果的数目。同时给你一个二维数组 queries
     * ，其中 queries[i] = [favoriteTypei, favoriteDayi, dailyCapi] 。
     *
     * <p>你按照如下规则进行一场游戏：
     *
     * <p>你从第 0 天开始吃糖果。 你在吃完 所有 第 i - 1 类糖果之前，不能 吃任何一颗第 i 类糖果。 在吃完所有糖果之前，你必须每天 至少 吃 一颗 糖果。
     * 请你构建一个布尔型数组 answer ，满足 answer.length == queries.length 。answer[i] 为 true 的条件是：在每天吃 不超过
     * dailyCapi 颗糖果的前提下，你可以在第 favoriteDayi 天吃到第 favoriteTypei 类糖果；否则 answer[i] 为 false 。注意，只要满足上面 3
     * 条规则中的第二条规则，你就可以在同一天吃不同类型的糖果。
     *
     * <p>请你返回得到的数组 answer 。
     *
     * <p>示例 1：
     *
     * <p>输入：candiesCount = [7,4,5,3,8], queries = [[0,2,2],[4,2,4],[2,13,1000000000]]
     * 输出：[true,false,true]
     *
     * <p>提示： 1- 在第 0 天吃 2 颗糖果(类型 0），第 1 天吃 2 颗糖果（类型 0），第 2 天你可以吃到类型 0 的糖果。 2- 每天你最多吃 4 颗糖果。即使第 0 天吃
     * 4 颗糖果（类型 0），第 1 天吃 4 颗糖果（类型 0 和类型 1），你也没办法在第 2 天吃到类型 4 的糖果。换言之，你没法在每天吃 4 颗糖果的限制下在第 2 天吃到第 4
     * 类糖果。 3- 如果你每天吃 1 颗糖果，你可以在第 13 天吃到类型 2 的糖果。 示例 2：
     *
     * <p>输入：candiesCount = [5,2,6,4,1], queries = [[3,1,2],[4,10,3],[3,10,100],[4,100,30],[1,3,1]]
     * 输出：[false,true,true,false,false]
     *
     * <p>提示：
     *
     * <p>1 <= candiesCount.length <= 105 1 <= candiesCount[i] <= 105 1 <= queries.length <= 105
     * queries[i].length == 3 0 <= favoriteTypei < candiesCount.length 0 <= favoriteDayi <= 109 1 <=
     * dailyCapi <= 109
     *
     * @param candiesCount
     * @param queries
     * @return
     */
    public boolean[] canEat(int[] candiesCount, int[][] queries) {
        int len = queries.length;
        boolean[] result = new boolean[len];
        long[] candySum = new long[candiesCount.length + 1];
        int sum = 0;
        for (int i = 0; i < candiesCount.length; i++) {
            sum += candiesCount[i];
            candySum[i + 1] = sum;
        }

        for (int i = 0; i < len; i++) {
            result[i] = canEat(candySum, queries[i]);
        }
        return result;
    }

    /**
     * 吃到你最喜欢的糖果
     *
     * @param candySum
     * @param query
     * @return
     */
    private boolean canEat(long[] candySum, int[] query) {
        // 前缀和
        // favoriteTypei, favoriteDayi, dailyCapi
        int type = query[0];
        long day = query[1], cap = query[2];
        // 提前吃完
        if (day + 1 > candySum[type + 1]) {
            return false;
        }
        // 不够吃
        return cap * (day + 1) > candySum[type];
    }

    @Test
    public void canEat() {
        int[] candiesCount = {5, 2, 6, 4, 1};
        int[][] queries = {{3, 1, 2}, {4, 10, 3}, {3, 10, 100}, {4, 100, 30}, {1, 3, 1}};
        boolean[] result = canEat(candiesCount, queries);
        log.debug("result :{}", result);
    }

    /**
     * 1250. 检查「好数组」
     *
     * <p>给你一个正整数数组 nums，你需要从中任选一些子集，然后将子集中每一个数乘以一个 任意整数，并求出他们的和。
     *
     * <p>假如该和结果为 1，那么原数组就是一个「好数组」，则返回 True；否则请返回 False。
     *
     * <p>示例 1：
     *
     * <p>输入：nums = [12,5,7,23] 输出：true 解释：挑选数字 5 和 7。 5*3 + 7*(-2) = 1 示例 2：
     *
     * <p>输入：nums = [29,6,10] 输出：true 解释：挑选数字 29, 6 和 10。 29*1 + 6*(-3) + 10*(-1) = 1 示例 3：
     *
     * <p>输入：nums = [3,6] 输出：false
     *
     * <p>提示：
     *
     * <p>1 <= nums.length <= 10^5 1 <= nums[i] <= 10^9
     *
     * @param nums
     * @return
     */
    public boolean isGoodArray(int[] nums) {
        // 求最大公约数 , 最大公约数是1 为 true
        int num = nums[0];

        for (int i = 1; i < nums.length; i++) {
            num = getGcd(num, nums[i]);
        }
        return num == 1;
    }
}
