package com.qinfengsa.structure.leetcode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Queue;
import java.util.Set;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

/**
 * 队列test
 *
 * @author: qinfengsa
 * @date: 2019/7/10 16:04
 */
@Slf4j
public class QueueTest {

    @Test
    public void openLock() {
        String[] deadends = {"8888"};
        String target = "0009";
        int result = openLock(deadends, target);
        log.debug("result:{}", result);
    }

    /**
     * 打开转盘锁 你有一个带有四个圆形拨轮的转盘锁。每个拨轮都有10个数字： '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' 。
     * 每个拨轮可以自由旋转：例如把 '9' 变为 '0'，'0' 变为 '9' 。每次旋转都只能旋转一个拨轮的一位数字。
     *
     * <p>锁的初始数字为 '0000' ，一个代表四个拨轮的数字的字符串。
     *
     * <p>列表 deadends 包含了一组死亡数字，一旦拨轮的数字和列表里的任何一个元素相同，这个锁将会被永久锁定，无法再被旋转。
     *
     * <p>字符串 target 代表可以解锁的数字，你需要给出最小的旋转次数，如果无论如何不能解锁，返回 -1。 示例 1:
     *
     * <p>输入：deadends = ["0201","0101","0102","1212","2002"], target = "0202" 输出：6 解释： 可能的移动序列为
     * "0000" -> "1000" -> "1100" -> "1200" -> "1201" -> "1202" -> "0202"。 注意 "0000" -> "0001" ->
     * "0002" -> "0102" -> "0202" 这样的序列是不能解锁的， 因为当拨动到 "0102" 时这个锁就会被锁定。 示例 2:
     *
     * <p>输入: deadends = ["8888"], target = "0009" 输出：1 解释： 把最后一位反向旋转一次即可 "0000" -> "0009"。 示例 3:
     *
     * <p>输入: deadends = ["8887","8889","8878","8898","8788","8988","7888","9888"], target = "8888"
     * 输出：-1 解释： 无法旋转到目标数字且不被锁定。 示例 4:
     *
     * <p>输入: deadends = ["0000"], target = "8888" 输出：-1
     *
     * @param deadends
     * @param target
     * @return
     */
    public int openLock(String[] deadends, String target) {
        // 死亡数字
        Set<String> dead = new HashSet<>(Arrays.asList(deadends));
        // 访问数字
        Set<String> visited = new HashSet<>();
        // 起始数字
        String init = "0000";
        if (dead.contains(init) || dead.contains(target)) {
            return -1;
        }
        int steps = 0;
        // 广度优先遍历 4位数字一共有8个子节点
        Queue<String> queue1 = new LinkedList<>();
        queue1.offer(init); // 起始数字入队
        visited.add(init);
        while (!queue1.isEmpty()) {
            int size = queue1.size();
            for (int i = 0; i < size; i++) {
                // 出队
                String curNum = queue1.poll();
                // 当前数字在死亡数字中，下一次循环
                if (dead.contains(curNum)) {
                    continue;
                }
                // 如果当前数字和target 返回
                if (Objects.equals(curNum, target)) {
                    return steps;
                }
                // 获取当前数字的子节点
                List<String> nexts = getNexts(curNum);
                // 遍历子节点
                for (String child : nexts) {
                    if (!visited.contains(child)) {
                        queue1.offer(child);
                        visited.add(child);
                    }
                }
            }
            steps++;
        }

        return -1;
    }
    /*public int openLock(String[] deadends, String target) {
        Set<String> dead = new HashSet<>(Arrays.asList(deadends));
        Set<String> visited = new HashSet<>();
        String init = "0000";
        if (dead.contains(init) || dead.contains(target)) {
            return -1;
        }

        Queue<String> queue1 = new LinkedList<>();
        Queue<String> queue2 = new LinkedList<>();
        int steps = 0;
        queue1.offer(init);
        while (!queue1.isEmpty()) {
            String cur = queue1.poll();
            if (cur.equals(target)) {
                return steps;
            }

            List<String> nexts = getNexts(cur);
            for (String next : nexts) {
                if (!dead.contains(next) && !visited.contains(next)) {
                    visited.add(next);
                    queue2.offer(next);
                }
            }

            if (queue1.isEmpty()) {
                steps++;
                queue1 = queue2;
                queue2 = new LinkedList<>();
            }
        }

        return -1;
    }*/

    private List<String> getNexts(String cur) {
        List<String> nexts = new LinkedList<>();
        for (int i = 0; i < cur.length(); ++i) {
            char ch = cur.charAt(i);

            char newCh = ch == '0' ? '9' : (char) (ch - 1);
            StringBuilder builder = new StringBuilder(cur);
            builder.setCharAt(i, newCh);
            nexts.add(builder.toString());

            newCh = ch == '9' ? '0' : (char) (ch + 1);
            builder = new StringBuilder(cur);
            builder.setCharAt(i, newCh);
            nexts.add(builder.toString());
        }

        return nexts;
    }

    @Test
    public void numSquares() {
        int n = 4;
        int result = numSquares(n);
        log.debug("result :{}", result);
    }

    /**
     * 完全平方数 给定正整数 n，找到若干个完全平方数（比如 1, 4, 9, 16, ...）使得它们的和等于 n。你需要让组成和的完全平方数的个数最少。
     *
     * <p>示例 1:
     *
     * <p>输入: n = 12 输出: 3 解释: 12 = 4 + 4 + 4. 示例 2:
     *
     * <p>输入: n = 13 输出: 2 解释: 13 = 4 + 9.
     *
     * @param n
     * @return
     */
    public int numSquares(int n) {
        // 动态规划 构造一个长度为 n 的 数组，每个位置表示 当前下标i的最少的完全平方数个数
        // 动态转移方程为：dp[i] = MIN(dp[i], dp[i - j * j] + 1)，i表示当前数字，j*j表示平方数
        int[] dp = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            dp[i] = i;
            for (int j = 1; i - j * j >= 0; j++) {
                dp[i] = Math.min(dp[i], dp[i - j * j] + 1); // 动态转移方程
            }
        }

        return dp[n];
        /*int minDepth = Integer.MAX_VALUE;

        int depth = 0;

        // 广度优先遍历
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(n);

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int num = queue.poll();
            }


        }*/

    }

    private int getNexts(int num) {

        double result = Math.sqrt(num);

        int a = (int) Math.floor(result);

        return a;
    }
}
