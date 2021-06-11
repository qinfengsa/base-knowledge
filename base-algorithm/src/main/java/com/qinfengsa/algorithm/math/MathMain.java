package com.qinfengsa.algorithm.math;

import com.qinfengsa.algorithm.util.CompUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;
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

    /**
     * 1330. 翻转子数组得到最大的数组值
     *
     * <p>给你一个整数数组 nums 。「数组值」定义为所有满足 0 <= i < nums.length-1 的 |nums[i]-nums[i+1]| 的和。
     *
     * <p>你可以选择给定数组的任意子数组，并将该子数组翻转。但你只能执行这个操作 一次 。
     *
     * <p>请你找到可行的最大 数组值 。
     *
     * <p>示例 1：
     *
     * <p>输入：nums = [2,3,1,5,4] 输出：10 解释：通过翻转子数组 [3,1,5] ，数组变成 [2,5,1,3,4] ，数组值为 10 。 示例 2：
     *
     * <p>输入：nums = [2,4,9,24,2,1,10] 输出：68
     *
     * <p>提示：
     *
     * <p>1 <= nums.length <= 3*10^4 -10^5 <= nums[i] <= 10^5
     *
     * @param nums
     * @return
     */
    public int maxValueAfterReverse(int[] nums) {
        // 思路 子数组[i ~ j] 翻转 本身的数组值是不变的, 变动的是 i-1, i和 j, j + 1
        // 计算 变化前后的差值, 求出最大的变化值
        int sum = 0, len = nums.length;
        for (int i = 1; i < len; i++) {
            sum += Math.abs(nums[i] - nums[i - 1]);
        }
        int max = 0;
        // 计算 i == 0 或者 j == len -1  交换边界 的
        for (int i = 0; i < len; i++) {
            // 交换 0 ~ i;
            if (i < len - 1) {
                max =
                        Math.max(
                                max,
                                Math.abs(nums[i + 1] - nums[0]) - Math.abs(nums[i + 1] - nums[i]));
            }

            // 交换 i ~ len - 1;
            if (i > 0) {
                max =
                        Math.max(
                                max,
                                Math.abs(nums[len - 1] - nums[i - 1])
                                        - Math.abs(nums[i] - nums[i - 1]));
            }
        }
        int[] dirX = {1, 1, -1, -1}, dirY = {1, -1, 1, -1};
        // 原本的 val = Math.abs(nums[i] - nums[i - 1]) + Math.abs(nums[j + 1] - nums[j]);
        // 新的 val = Math.abs(nums[j] - nums[i - 1]) + Math.abs(nums[j + 1] - nums[i]);

        //  Math.abs(nums[i] - nums[i - 1]) 和  Math.abs(nums[j + 1] - nums[j]) 是 固定的 可以 在 O(n)
        // 的时间复杂度求出

        // Math.abs(nums[j] - nums[i - 1]) + Math.abs(nums[j + 1] - nums[i])
        // = Math.max (nums[j] - nums[i - 1] + nums[j + 1] - nums[i],
        // - nums[j] + nums[i - 1] + nums[j + 1] - nums[i],
        // nums[j] - nums[i - 1] - nums[j + 1] + nums[i],
        // - nums[j] + nums[i - 1] - nums[j + 1] + nums[i])
        //
        // =>Math.max ( (- nums[i] - nums[i - 1]) - (- nums[j + 1] - nums[j] ) ,
        // (- nums[i] + nums[i - 1]) - (- nums[j + 1] + nums[j]),
        // (nums[i] - nums[i - 1]) - (nums[j + 1] - nums[j]) ,
        // (nums[i] + nums[i - 1]) - (nums[j + 1] + nums[j]))
        //
        // 规律 设 sum[i] = - nums[i] - nums[i - 1]  sum[j + 1] = - nums[j + 1] - nums[j]
        // 差值 = sum[i] - sum[j + 1] - (Math.abs(nums[i] - nums[i - 1]) + Math.abs(nums[j + 1] -
        // nums[j]))
        // =>  (sum[i] - Math.abs(nums[i] - nums[i - 1])) - (sum[j + 1] + Math.abs(nums[j + 1] -
        // nums[j]))

        // 求 (sum[i] - Math.abs(nums[i] - nums[i - 1])) 的 最大值
        // 和 (sum[j + 1] + Math.abs(nums[j + 1] - nums[j]))最小值 相减
        for (int k = 0; k < 4; k++) {
            TreeSet<Integer> treeSet1 = new TreeSet<>(), treeSet2 = new TreeSet<>();
            for (int i = 1; i < len; i++) {
                int num = dirX[k] * nums[i] + dirY[k] * nums[i - 1];
                int curAbs = Math.abs(nums[i] - nums[i - 1]);
                treeSet1.add(num - curAbs);
                treeSet2.add(num + curAbs);
            }
            int num1 = treeSet1.last(), num2 = treeSet2.first();
            max = Math.max(max, num1 - num2);
        }

        return sum + max;
    }

    /**
     * 810. 黑板异或游戏
     *
     * <p>黑板上写着一个非负整数数组 nums[i] 。Alice 和 Bob 轮流从黑板上擦掉一个数字，Alice 先手。如果擦除一个数字后，剩余的所有数字按位异或运算得出的结果等于 0
     * 的话，当前玩家游戏失败。 (另外，如果只剩一个数字，按位异或运算得到它本身；如果无数字剩余，按位异或运算结果为 0。）
     *
     * <p>换种说法就是，轮到某个玩家时，如果当前黑板上所有数字按位异或运算结果等于 0，这个玩家获胜。
     *
     * <p>假设两个玩家每步都使用最优解，当且仅当 Alice 获胜时返回 true。
     *
     * <p>示例：
     *
     * <p>输入: nums = [1, 1, 2] 输出: false 解释: Alice 有两个选择: 擦掉数字 1 或 2。 如果擦掉 1, 数组变成 [1, 2]。剩余数字按位异或得到
     * 1 XOR 2 = 3。那么 Bob 可以擦掉任意数字，因为 Alice 会成为擦掉最后一个数字的人，她总是会输。 如果 Alice 擦掉 2，那么数组变成[1,
     * 1]。剩余数字按位异或得到 1 XOR 1 = 0。Alice 仍然会输掉游戏。
     *
     * <p>提示：
     *
     * <p>1 <= N <= 1000 0 <= nums[i] <= 2^16
     *
     * @param nums
     * @return
     */
    public boolean xorGame(int[] nums) {
        int len = nums.length;
        // 偶数个 Alice 绝对赢
        if ((len & 1) == 0) {
            return true;
        }
        // 奇数个 需要看 总的 xor
        int xor = 0;
        for (int num : nums) {
            xor ^= num;
        }
        return xor == 0;
    }

    /**
     * 1359. 有效的快递序列数目
     *
     * <p>给你 n 笔订单，每笔订单都需要快递服务。
     *
     * <p>请你统计所有有效的 收件/配送 序列的数目，确保第 i 个物品的配送服务 delivery(i) 总是在其收件服务 pickup(i) 之后。
     *
     * <p>由于答案可能很大，请返回答案对 10^9 + 7 取余的结果。
     *
     * <p>示例 1：
     *
     * <p>输入：n = 1 输出：1 解释：只有一种序列 (P1, D1)，物品 1 的配送服务（D1）在物品 1 的收件服务（P1）后。 示例 2：
     *
     * <p>输入：n = 2 输出：6 解释：所有可能的序列包括：
     * (P1,P2,D1,D2)，(P1,P2,D2,D1)，(P1,D1,P2,D2)，(P2,P1,D1,D2)，(P2,P1,D2,D1) 和 (P2,D2,P1,D1)。
     * (P1,D2,P2,D1) 是一个无效的序列，因为物品 2 的收件服务（P2）不应在物品 2 的配送服务（D2）之后。 示例 3：
     *
     * <p>输入：n = 3 输出：90
     *
     * <p>提示：
     *
     * <p>1 <= n <= 500
     *
     * @param n
     * @return
     */
    public int countOrders(int n) {
        // 排列组合问题
        // f(2) n = 2 4个位置中随机选择2个位置 剩下的放置 P1, D1
        // f(3) n = 3 6个位置中随机选择2个位置 剩下的放置 P1, D1,P2, D2   C(2n, 2) * f(2)
        // f(n) = C(2n, 2) * f(n - 1) = 2n * (2n - 1) / 2 *f(n - 1) = n *(2 *n - 1) * f(n - 1)
        if (n == 1) {
            return 1;
        }
        long result = 1;

        for (int i = 2; i <= n; i++) {
            result = result * (i * 2L - 1) * i % MOD;
        }

        return (int) result;
    }

    static final int MOD = 1000000007;

    /**
     * 1453. 圆形靶内的最大飞镖数量
     *
     * <p>墙壁上挂着一个圆形的飞镖靶。现在请你蒙着眼睛向靶上投掷飞镖。
     *
     * <p>投掷到墙上的飞镖用二维平面上的点坐标数组表示。飞镖靶的半径为 r 。
     *
     * <p>请返回能够落在 任意 半径为 r 的圆形靶内或靶上的最大飞镖数。
     *
     * <p>示例 1：
     *
     * <p>输入：points = [[-2,0],[2,0],[0,2],[0,-2]], r = 2 输出：4 解释：如果圆形的飞镖靶的圆心为 (0,0) ，半径为 2
     * ，所有的飞镖都落在靶上，此时落在靶上的飞镖数最大，值为 4 。 示例 2：
     *
     * <p>输入：points = [[-3,0],[3,0],[2,6],[5,4],[0,9],[7,8]], r = 5 输出：5 解释：如果圆形的飞镖靶的圆心为 (0,4) ，半径为
     * 5 ，则除了 (7,8) 之外的飞镖都落在靶上，此时落在靶上的飞镖数最大，值为 5 。 示例 3：
     *
     * <p>输入：points = [[-2,0],[2,0],[0,2],[0,-2]], r = 1 输出：1 示例 4：
     *
     * <p>输入：points = [[1,2],[3,5],[1,-1],[2,3],[4,1],[1,3]], r = 2 输出：4
     *
     * <p>提示：
     *
     * <p>1 <= points.length <= 100 points[i].length == 2 -10^4 <= points[i][0], points[i][1] <=
     * 10^4 1 <= r <= 5000
     *
     * @param points
     * @param r
     * @return
     */
    public int numPoints(int[][] points, int r) {
        int len = points.length;
        if (len == 1) {
            return 1;
        }
        int result = 1;

        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                double x1 = points[i][0], y1 = points[i][1], x2 = points[j][0], y2 = points[j][1];
                double d = Math.sqrt(getDistance(x1, x2, y1, y2));
                if (d > 2 * r) {
                    continue;
                }
                // 中点
                double midX = (x1 + x2) / 2.0, midY = (y1 + y2) / 2.0;
                // 计算 圆心
                List<double[]> centerList = new ArrayList<>();
                double x, y;
                if (d == 2 * r) { // 圆心在中点
                    centerList.add(new double[] {midX, midY});
                } else {
                    // 上下两个圆心
                    double h = Math.sqrt(r * r - (d / 2.0) * (d / 2.0));
                    if (x1 == x2) {
                        // 竖线
                        centerList.add(new double[] {midX + h, midY});
                        centerList.add(new double[] {midX - h, midY});
                    } else if (y1 == y2) {
                        // 横线
                        centerList.add(new double[] {midX, midY + h});
                        centerList.add(new double[] {midX, midY - h});
                    } else {

                        // i j 连线斜率   (y2 - y1) / (x2 - x1)
                        // 中线斜率a 垂直 于 连线斜率b  a*b = -1  (y - midY) / (x - midX)

                        double cx1 = midX + (y1 - y2) / d * h, cx2 = midX + (y2 - y1) / d * h;
                        double cy1 = midY + (x2 - x1) / d * h, cy2 = midY + (x1 - x2) / d * h;
                        centerList.add(new double[] {cx1, cy1});
                        centerList.add(new double[] {cx2, cy2});
                    }
                }
                for (double[] p : centerList) {
                    int count = 0;
                    for (int[] point : points) {
                        if (getDistance(p[0], point[0], p[1], point[1]) - r * r <= 0.00001) {
                            count++;
                        }
                    }
                    result = Math.max(result, count);
                }
            }
        }

        return result;
    }

    private double getDistance(double x1, double x2, double y1, double y2) {
        return (x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2);
    }

    /**
     * 1611. 使整数变为 0 的最少操作次数
     *
     * <p>给你一个整数 n，你需要重复执行多次下述操作将其转换为 0 ：
     *
     * <p>翻转 n 的二进制表示中最右侧位（第 0 位）。 如果第 (i-1) 位为 1 且从第 (i-2) 位到第 0 位都为 0，则翻转 n 的二进制表示中的第 i 位。 返回将 n
     * 转换为 0 的最小操作次数。
     *
     * <p>示例 1：
     *
     * <p>输入：n = 0 输出：0 示例 2：
     *
     * <p>输入：n = 3 输出：2 解释：3 的二进制表示为 "11" "11" -> "01" ，执行的是第 2 种操作，因为第 0 位为 1 。 "01" -> "00" ，执行的是第
     * 1 种操作。 示例 3：
     *
     * <p>输入：n = 6 输出：4 解释：6 的二进制表示为 "110". "110" -> "010" ，执行的是第 2 种操作，因为第 1 位为 1 ，第 0 到 0 位为 0 。
     * "010" -> "011" ，执行的是第 1 种操作。 "011" -> "001" ，执行的是第 2 种操作，因为第 0 位为 1 。 "001" -> "000" ，执行的是第 1
     * 种操作。 示例 4：
     *
     * <p>输入：n = 9 输出：14 示例 5：
     *
     * <p>输入：n = 333 输出：393
     *
     * <p>提示：
     *
     * <p>0 <= n <= 109
     *
     * @param n
     * @return
     */
    public int minimumOneBitOperations(int n) {
        // 格雷码
        int result = 0;

        while (n > 0) {
            result ^= n;
            n >>= 1;
        }

        return result;
    }

    /**
     * 1643. 第 K 条最小指令
     *
     * <p>Bob 站在单元格 (0, 0) ，想要前往目的地 destination ：(row, column) 。他只能向 右 或向 下 走。你可以为 Bob 提供导航 指令
     * 来帮助他到达目的地 destination 。
     *
     * <p>指令 用字符串表示，其中每个字符：
     *
     * <p>'H' ，意味着水平向右移动 'V' ，意味着竖直向下移动 能够为 Bob 导航到目的地 destination 的指令可以有多种，例如，如果目的地 destination 是
     * (2, 3)，"HHHVV" 和 "HVHVH" 都是有效 指令 。
     *
     * <p>然而，Bob 很挑剔。因为他的幸运数字是 k，他想要遵循 按字典序排列后的第 k 条最小指令 的导航前往目的地 destination 。k 的编号 从 1 开始 。
     *
     * <p>给你一个整数数组 destination 和一个整数 k ，请你返回可以为 Bob 提供前往目的地 destination 导航的 按字典序排列后的第 k 条最小指令 。
     *
     * <p>示例 1：
     *
     * <p>输入：destination = [2,3], k = 1 输出："HHHVV" 解释：能前往 (2, 3) 的所有导航指令 按字典序排列后 如下所示： ["HHHVV",
     * "HHVHV", "HHVVH", "HVHHV", "HVHVH", "HVVHH", "VHHHV", "VHHVH", "VHVHH", "VVHHH"]. 示例 2：
     *
     * <p>输入：destination = [2,3], k = 2 输出："HHVHV" 示例 3：
     *
     * <p>输入：destination = [2,3], k = 3 输出："HHVVH"
     *
     * <p>提示：
     *
     * <p>destination.length == 2 1 <= row, column <= 15 1 <= k <= nCr(row + column, row)，其中 nCr(a,
     * b) 表示组合数，即从 a 个物品中选 b 个物品的不同方案数。
     *
     * @param destination
     * @param k
     * @return
     */
    public String kthSmallestPath(int[] destination, int k) {
        // 排列组合问题 m个 v n 个 h
        int v = destination[0], h = destination[1];
        long[][] compNum = CompUtils.initComp(v + h);
        StringBuilder result = new StringBuilder();
        while (v > 0 || h > 0) {
            if (h > 0) {
                // 剩余的排列数
                long count = compNum[v + h - 1][Math.min(v, h - 1)];
                if (count >= k) {
                    result.append('H');
                    h--;
                } else {
                    result.append('V');
                    v--;
                    k -= count;
                }

            } else {
                result.append('V');
                v--;
            }
        }

        return result.toString();
    }

    /**
     * 1808. 好因子的最大数目
     *
     * <p>给你一个正整数 primeFactors 。你需要构造一个正整数 n ，它满足以下条件：
     *
     * <p>n 质因数（质因数需要考虑重复的情况）的数目 不超过 primeFactors 个。 n 好因子的数目最大化。如果 n 的一个因子可以被 n 的每一个质因数整除，我们称这个因子是
     * 好因子 。比方说，如果 n = 12 ，那么它的质因数为 [2,2,3] ，那么 6 和 12 是好因子，但 3 和 4 不是。 请你返回 n
     * 的好因子的数目。由于答案可能会很大，请返回答案对 109 + 7 取余 的结果。
     *
     * <p>请注意，一个质数的定义是大于 1 ，且不能被分解为两个小于该数的自然数相乘。一个数 n 的质因子是将 n 分解为若干个质因子，且它们的乘积为 n 。
     *
     * <p>示例 1：
     *
     * <p>输入：primeFactors = 5 输出：6 解释：200 是一个可行的 n 。 它有 5 个质因子：[2,2,2,5,5] ，且有 6
     * 个好因子：[10,20,40,50,100,200] 。 不存在别的 n 有至多 5 个质因子，且同时有更多的好因子。 示例 2：
     *
     * <p>输入：primeFactors = 8 输出：18
     *
     * <p>提示：
     *
     * <p>1 <= primeFactors <= 109
     *
     * @param primeFactors
     * @return
     */
    public int maxNiceDivisors(int primeFactors) {
        if (primeFactors <= 3) {
            return primeFactors;
        }
        int a = primeFactors / 3, b = primeFactors % 3;
        if (b == 1) {
            return (int) (myPow(3, a - 1) * 4 % MOD);
        } else if (b == 2) {
            return (int) (myPow(3, a) * 2 % MOD);
        } else {
            return (int) myPow(3, a);
        }
    }

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
     * 1835. 所有数对按位与结果的异或和
     *
     * <p>列表的 异或和（XOR sum）指对所有元素进行按位 XOR 运算的结果。如果列表中仅有一个元素，那么其 异或和 就等于该元素。
     *
     * <p>例如，[1,2,3,4] 的 异或和 等于 1 XOR 2 XOR 3 XOR 4 = 4 ，而 [3] 的 异或和 等于 3 。 给你两个下标 从 0 开始 计数的数组 arr1
     * 和 arr2 ，两数组均由非负整数组成。
     *
     * <p>根据每个 (i, j) 数对，构造一个由 arr1[i] AND arr2[j]（按位 AND 运算）结果组成的列表。其中 0 <= i < arr1.length 且 0 <=
     * j < arr2.length 。
     *
     * <p>返回上述列表的 异或和 。
     *
     * <p>示例 1：
     *
     * <p>输入：arr1 = [1,2,3], arr2 = [6,5] 输出：0 解释：列表 = [1 AND 6, 1 AND 5, 2 AND 6, 2 AND 5, 3 AND 6,
     * 3 AND 5] = [0,1,2,0,2,1] ， 异或和 = 0 XOR 1 XOR 2 XOR 0 XOR 2 XOR 1 = 0 。 示例 2：
     *
     * <p>输入：arr1 = [12], arr2 = [4] 输出：4 解释：列表 = [12 AND 4] = [4] ，异或和 = 4 。
     *
     * <p>提示：
     *
     * <p>1 <= arr1.length, arr2.length <= 105 0 <= arr1[i], arr2[j] <= 109
     *
     * @param arr1
     * @param arr2
     * @return
     */
    public int getXORSum(int[] arr1, int[] arr2) {
        // (a1 & b1) ^ (a1 & b2) ^ (a1 & b3) = a1 & (b1 ^ b2 ^ b3)

        int sum1 = 0, sum2 = 0;
        for (int num : arr1) {
            sum1 ^= num;
        }
        for (int num : arr2) {
            sum2 ^= num;
        }
        return sum1 & sum2;
    }
}
