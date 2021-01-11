package com.qinfengsa.algorithm.greedy;

import static com.qinfengsa.algorithm.util.LogUtils.logResult;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

/**
 * 贪心算法 设计贪心算法的步骤
 *
 * <p>1.将优化问题转换成这样一个问题，即先做出选择，再解决剩下的一个子问题。
 *
 * <p>2.证明原问题总是有一个最优解是贪心选择的得到的，从而说明贪心选择的安全。
 *
 * <p>3.说明在做出贪心选择后，剩下的子问题具有这样一个性质。即如果将子问题的最优解和我们所做的贪心选择联合起来，可以得到一个更加负责的动态规划解。
 *
 * @author: qinfengsa
 * @date: 2019/8/10 23:17
 */
@Slf4j
public class GreedyTest {

    @Test
    public void greedyKnapsack() {
        int max = 150;
        String[] items = {"A", "B", "C", "D", "E", "F", "G"};
        int[] weights = {35, 30, 6, 50, 40, 10, 25};
        int[] costs = {10, 40, 30, 50, 35, 40, 30};
        greedyKnapsack(max, items, weights, costs);
    }

    private void greedyKnapsackStrategy() {}

    /**
     * 0-1背包问题 有一个背包，背包容量是M=150kg。有7个物品，物品不可以分割成任意大小。要求尽可能让装入背包中的物品总价值最大，但不能超过总容量。 物品 A B C D E F G
     * 重量 35kg 30kg 6kg 50kg 40kg 10kg 25kg 价值 10$ 40$ 30$ 50$ 35$ 40$ 30$
     */
    private void greedyKnapsack(int max, String[] items, int[] weights, int[] costs) {

        int size = items.length;
        double[] costPreWeight = new double[size];
        for (int i = 0; i < size; i++) {
            double price = (double) costs[i] / (double) weights[i];
            costPreWeight[i] = price;
        }
    }

    @Test
    public void fullJustify() {
        String[] words = {"What", "must", "be", "acknowledgment", "shall", "be"};
        int maxWidth = 16;
        List<String> result = fullJustify(words, maxWidth);
        for (String word : result) {
            log.debug("result : {}/end", word);
        }
    }

    /**
     * 文本左右对齐 给定一个单词数组和一个长度 maxWidth，重新排版单词，使其成为每行恰好有 maxWidth 个字符，且左右两端对齐的文本。
     *
     * <p>你应该使用“贪心算法”来放置给定的单词；也就是说，尽可能多地往每行中放置单词。必要时可用空格 ' ' 填充，使得每行恰好有 maxWidth 个字符。
     *
     * <p>要求尽可能均匀分配单词间的空格数量。如果某一行单词间的空格不能均匀分配，则左侧放置的空格数要多于右侧的空格数。
     *
     * <p>文本的最后一行应为左对齐，且单词之间不插入额外的空格。
     *
     * <p>说明:
     *
     * <p>单词是指由非空格字符组成的字符序列。 每个单词的长度大于 0，小于等于 maxWidth。 输入单词数组 words 至少包含一个单词。 示例:
     *
     * <p>输入: words = ["This", "is", "an", "example", "of", "text", "justification."] maxWidth = 16
     * 输出: [ "This is an", "example of text", "justification. " ] 示例 2:
     *
     * <p>输入: words = ["What","must","be","acknowledgment","shall","be"] maxWidth = 16 输出: [ "What
     * must be", "acknowledgment ", "shall be " ] 解释: 注意最后一行的格式应为 "shall be " 而不是 "shall be",
     * 因为最后一行应为左对齐，而不是左右两端对齐。 第二行同样为左对齐，这是因为这行只包含一个单词。 示例 3:
     *
     * <p>输入: words = ["Science","is","what","we","understand","well","enough","to","explain",
     * "to","a","computer.","Art","is","everything","else","we","do"] maxWidth = 20 输出: [ "Science
     * is what we", "understand well", "enough to explain to", "a computer. Art is", "everything
     * else we", "do " ]
     *
     * @param words
     * @param maxWidth
     * @return
     */
    public List<String> fullJustify(String[] words, int maxWidth) {
        if (words.length == 0) {
            return new ArrayList<>();
        }
        List<String> result = new ArrayList<>();
        int currentIndex = 0; // 当前游标
        int len = 0; // 当前行的长度
        int qty = 0; // 当前行单词数量
        // 1.当前行需要包含尽可能多的Word，长度不超过maxWidth;
        while (currentIndex < words.length) {

            String word = words[currentIndex];
            if (len + word.length() <= maxWidth - qty) { // 需要加qty个空格
                len += word.length();
                currentIndex++;
                qty++;
            } else {
                // 2.当前行的Word之间的空格尽可能均匀分配，如果不能，左侧放置的空格数要多于右侧的空格数
                int lastIndex = currentIndex - 1;
                // 计算Word之间的空格数
                int totalNum = maxWidth - len;
                String rowWords = getRowWords(lastIndex, totalNum, qty, words);
                result.add(rowWords);
                len = 0; // 当前行的长度重置
                qty = 0; // 当前行的qty重置
            }
        }
        // 处理最后一行
        int index = words.length - 1;
        // 文本的最后一行应为左对齐，且单词之间不插入额外的空格。
        StringBuilder rowWords = new StringBuilder();
        for (int i = index - qty + 1; i <= index; i++) {
            rowWords.append(words[i]);
            if (i != index) {
                rowWords.append(" "); // 最后一行，只插入一个空格
            }
        }
        int num = maxWidth - rowWords.length();
        while (num > 0) {
            rowWords.append(" ");
            num--;
        }
        // String rowWords = getRowWords(index,maxWidth,len,qty,words);
        result.add(rowWords.toString());
        return result;
    }

    private String getRowWords(int index, int totalNum, int qty, String[] words) {
        // 2.当前行的Word之间的空格尽可能均匀分配，如果不能，左侧放置的空格数要多于右侧的空格数

        int avgNum = totalNum;
        int num = 0;
        if (qty > 1) {
            avgNum = totalNum / (qty - 1);
            num = totalNum % (qty - 1);
        }

        StringBuilder blankStr = new StringBuilder();
        while (avgNum > 0) {
            blankStr.append(" ");
            avgNum--;
        }
        // 构造 当前行
        StringBuilder rowWords = new StringBuilder();
        for (int i = index - qty + 1; i <= index; i++) {
            rowWords.append(words[i]);
            if (i != index) {
                rowWords.append(blankStr);
                if (num > 0) {
                    rowWords.append(" ");
                    num--;
                }
            }
        }
        if (qty == 1) {
            rowWords.append(blankStr);
        }
        return rowWords.toString();
    }

    @Test
    public void balancedStringSplit() {
        String s = "RLLLLLRRRLR";
        logResult(balancedStringSplit(s));
    }

    /**
     * 1221. 分割平衡字符串 在一个「平衡字符串」中，'L' 和 'R' 字符的数量是相同的。
     *
     * <p>给出一个平衡字符串 s，请你将它分割成尽可能多的平衡字符串。
     *
     * <p>返回可以通过分割得到的平衡字符串的最大数量。
     *
     * <p>示例 1：
     *
     * <p>输入：s = "RLRRLLRLRL" 输出：4 解释：s 可以分割为 "RL", "RRLL", "RL", "RL", 每个子字符串中都包含相同数量的 'L' 和 'R'。
     * 示例 2：
     *
     * <p>输入：s = "RLLLLRRRLR" 输出：3 解释：s 可以分割为 "RL", "LLLRRR", "LR", 每个子字符串中都包含相同数量的 'L' 和 'R'。 示例 3：
     *
     * <p>输入：s = "LLLLRRRR" 输出：1 解释：s 只能保持原样 "LLLLRRRR".
     *
     * <p>提示：
     *
     * <p>1 <= s.length <= 1000 s[i] = 'L' 或 'R'
     *
     * @param s
     * @return
     */
    public int balancedStringSplit(String s) {
        int result = 0;

        if (s.length() == 0) {
            return 0;
        }
        // 'L' 和 'R' 字符的数量是相同的 所以可以采用下面的写法
        int num = 0;
        for (char c : s.toCharArray()) {
            if (c == 'L') {
                num--;
            } else {
                num++;
            }
            if (num == 0) {
                result++;
            }
        }
        return result;
    }

    /**
     * 455. 分发饼干 假设你是一位很棒的家长，想要给你的孩子们一些小饼干。但是，每个孩子最多只能给一块饼干。对每个孩子 i ，都有一个胃口值 gi
     * ，这是能让孩子们满足胃口的饼干的最小尺寸；并且每块饼干 j ，都有一个尺寸 sj 。如果 sj >= gi ，我们可以将这个饼干 j 分配给孩子 i
     * ，这个孩子会得到满足。你的目标是尽可能满足越多数量的孩子，并输出这个最大数值。
     *
     * <p>注意：
     *
     * <p>你可以假设胃口值为正。 一个小朋友最多只能拥有一块饼干。
     *
     * <p>示例 1:
     *
     * <p>输入: [1,2,3], [1,1]
     *
     * <p>输出: 1
     *
     * <p>解释: 你有三个孩子和两块小饼干，3个孩子的胃口值分别是：1,2,3。 虽然你有两块小饼干，由于他们的尺寸都是1，你只能让胃口值是1的孩子满足。 所以你应该输出1。 示例 2:
     *
     * <p>输入: [1,2], [1,2,3]
     *
     * <p>输出: 2
     *
     * <p>解释: 你有两个孩子和三块小饼干，2个孩子的胃口值分别是1,2。 你拥有的饼干数量和尺寸都足以让所有孩子满足。 所以你应该输出2.
     *
     * @param g
     * @param s
     * @return
     */
    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int i = 0, j = 0;
        while (i < g.length && j < s.length) {
            if (g[i] <= s[j]) {
                i++;
            }
            j++;
        }
        return i;
    }

    /**
     * 860. 柠檬水找零 在柠檬水摊上，每一杯柠檬水的售价为 5 美元。
     *
     * <p>顾客排队购买你的产品，（按账单 bills 支付的顺序）一次购买一杯。
     *
     * <p>每位顾客只买一杯柠檬水，然后向你付 5 美元、10 美元或 20 美元。你必须给每个顾客正确找零，也就是说净交易是每位顾客向你支付 5 美元。
     *
     * <p>注意，一开始你手头没有任何零钱。
     *
     * <p>如果你能给每位顾客正确找零，返回 true ，否则返回 false 。
     *
     * <p>示例 1：
     *
     * <p>输入：[5,5,5,10,20] 输出：true 解释： 前 3 位顾客那里，我们按顺序收取 3 张 5 美元的钞票。 第 4 位顾客那里，我们收取一张 10 美元的钞票，并返还
     * 5 美元。 第 5 位顾客那里，我们找还一张 10 美元的钞票和一张 5 美元的钞票。 由于所有客户都得到了正确的找零，所以我们输出 true。 示例 2：
     *
     * <p>输入：[5,5,10] 输出：true 示例 3：
     *
     * <p>输入：[10,10] 输出：false 示例 4：
     *
     * <p>输入：[5,5,10,10,20] 输出：false 解释： 前 2 位顾客那里，我们按顺序收取 2 张 5 美元的钞票。 对于接下来的 2 位顾客，我们收取一张 10
     * 美元的钞票，然后返还 5 美元。 对于最后一位顾客，我们无法退回 15 美元，因为我们现在只有两张 10 美元的钞票。 由于不是每位顾客都得到了正确的找零，所以答案是 false。
     *
     * <p>提示：
     *
     * <p>0 <= bills.length <= 10000 bills[i] 不是 5 就是 10 或是 20
     *
     * @param bills
     * @return
     */
    public boolean lemonadeChange(int[] bills) {
        int len = bills.length;
        int five = 0, ten = 0;
        for (int bill : bills) {
            if (bill == 5) {
                five++;
            } else if (bill == 10) {
                if (five == 0) {
                    return false;
                }
                ten++;
                five--;
            } else {
                if (five > 0 && ten > 0) {
                    five--;
                    ten--;
                } else if (five >= 3) {
                    five -= 3;
                } else {
                    return false;
                }
            }
        }
        return true;
    }

    @Test
    public void robotSim() {
        // int[] commands = {4,-1,4,-2,4};
        // int[][] obstacles = {{2,4}};
        int[] commands = {-2, -1, -2, 3, 7};
        int[][] obstacles = {
            {1, -3}, {2, -3}, {4, 0}, {-2, 5}, {-5, 2}, {0, 0}, {4, -4}, {-2, -5}, {-1, -2}, {0, 2}
        };
        logResult(robotSim(commands, obstacles));
    }

    /**
     * 874. 模拟行走机器人 机器人在一个无限大小的网格上行走，从点 (0, 0) 处开始出发，面向北方。该机器人可以接收以下三种类型的命令：
     *
     * <p>-2：向左转 90 度 -1：向右转 90 度 1 <= x <= 9：向前移动 x 个单位长度 在网格上有一些格子被视为障碍物。
     *
     * <p>第 i 个障碍物位于网格点 (obstacles[i][0], obstacles[i][1])
     *
     * <p>如果机器人试图走到障碍物上方，那么它将停留在障碍物的前一个网格方块上，但仍然可以继续该路线的其余部分。
     *
     * <p>返回从原点到机器人的最大欧式距离的平方。
     *
     * <p>示例 1：
     *
     * <p>输入: commands = [4,-1,3], obstacles = [] 输出: 25 解释: 机器人将会到达 (3, 4) 示例 2：
     *
     * <p>输入: commands = [4,-1,4,-2,4], obstacles = [[2,4]] 输出: 65 解释: 机器人在左转走到 (1, 8) 之前将被困在 (1, 4)
     * 处
     *
     * <p>提示：
     *
     * <p>0 <= commands.length <= 10000 0 <= obstacles.length <= 10000 -30000 <= obstacle[i][0] <=
     * 30000 -30000 <= obstacle[i][1] <= 30000 答案保证小于 2 ^ 31
     *
     * @param commands
     * @param obstacles
     * @return
     */
    public int robotSim(int[] commands, int[][] obstacles) {
        int x = 0, y = 0;

        Map<String, Integer> obstacleMap = new HashMap<>();
        for (int[] obstacle : obstacles) {
            int obx = obstacle[0], oby = obstacle[1];
            obstacleMap.put("x-" + obx, oby);
            obstacleMap.put("y-" + oby, obx);
        }

        // 上 右 下 左
        int flagIndex = 0;
        int result = 0;

        for (int command : commands) {
            if (command == -1) {
                flagIndex = ++flagIndex % 4;
            } else if (command == -2) {
                flagIndex = (flagIndex + 3) % 4;
            } else {
                // 4个方向
                switch (flagIndex) {
                    case 0:
                        {
                            // 判断上方有没有障碍物
                            Integer oby = obstacleMap.get("x-" + x);
                            if (oby == null || oby < y || oby > y + command) {
                                y += command;
                            } else {
                                y = oby - 1;
                            }
                        }
                        ;
                        break;
                    case 1:
                        {
                            // 判断右方有没有障碍物
                            Integer obx = obstacleMap.get("y-" + y);
                            if (obx == null || obx < x || obx > x + command) {
                                x += command;
                            } else {
                                x = obx - 1;
                            }
                        }
                        ;
                        break;
                    case 2:
                        {
                            // 判断下方有没有障碍物
                            Integer oby = obstacleMap.get("x-" + x);
                            if (oby == null || oby > y || oby < y - command) {
                                y -= command;
                            } else {
                                y = oby + 1;
                            }
                        }
                        ;
                        break;
                    case 3:
                        {
                            // 判断左方有没有障碍物
                            Integer obx = obstacleMap.get("y-" + y);
                            if (obx == null || obx > x || obx < x - command) {
                                x -= command;
                            } else {
                                x = obx + 1;
                            }
                        }
                        ;
                        break;
                }
            }
        }
        log.debug("x:{},y:{}", x, y);
        result = x * x + y * y;
        return result;
    }

    static int[] DIR_ROW = {0, 1, 0, -1};
    static int[] DIR_COL = {1, 0, -1, 0};

    @Test
    public void largestSumAfterKNegations() {
        int[] nums = {-2, 5, 0, 2, -2};
        int k = 3;
        logResult(largestSumAfterKNegations(nums, k));
    }

    /**
     * 1005. K 次取反后最大化的数组和 给定一个整数数组 A，我们只能用以下方法修改该数组：我们选择某个个索引 i 并将 A[i] 替换为 -A[i]，然后总共重复这个过程 K
     * 次。（我们可以多次选择同一个索引 i。）
     *
     * <p>以这种方式修改数组后，返回数组可能的最大和。
     *
     * <p>示例 1：
     *
     * <p>输入：A = [4,2,3], K = 1 输出：5 解释：选择索引 (1,) ，然后 A 变为 [4,-2,3]。 示例 2：
     *
     * <p>输入：A = [3,-1,0,2], K = 3 输出：6 解释：选择索引 (1, 2, 2) ，然后 A 变为 [3,1,0,2]。 示例 3：
     *
     * <p>输入：A = [2,-3,-1,5,-4], K = 2 输出：13 解释：选择索引 (1, 4) ，然后 A 变为 [2,3,-1,5,4]。
     *
     * <p>提示：
     *
     * <p>1 <= A.length <= 10000 1 <= K <= 10000 -100 <= A[i] <= 100
     *
     * @param A
     * @param K
     * @return
     */
    public int largestSumAfterKNegations(int[] A, int K) {
        int len = A.length;
        int result = 0;
        Arrays.sort(A);
        for (int i = 0; i < len; i++) {
            if (K == 0) {
                break;
            }
            if (A[i] >= 0) {
                break;
            }
            A[i] = -A[i];
            K--;
        }
        log.debug("a:{}", A);
        Arrays.sort(A);
        log.debug("a:{}", A);
        int min = A[0];

        for (int i = 1; i < len; i++) {
            result += A[i];
        }

        if ((K & 1) == 0) {
            result += min;
        } else {
            result -= min;
        }

        return result;
    }

    /**
     * 1029. 两地调度 公司计划面试 2N 人。第 i 人飞往 A 市的费用为 costs[i][0]，飞往 B 市的费用为 costs[i][1]。
     *
     * <p>返回将每个人都飞到某座城市的最低费用，要求每个城市都有 N 人抵达。
     *
     * <p>示例：
     *
     * <p>输入：[[10,20},{30,200},{400,50},{30,20]] 输出：110 解释： 第一个人去 A 市，费用为 10。 第二个人去 A 市，费用为 30。
     * 第三个人去 B 市，费用为 50。 第四个人去 B 市，费用为 20。
     *
     * <p>最低总费用为 10 + 30 + 50 + 20 = 110，每个城市都有一半的人在面试。
     *
     * <p>提示：
     *
     * <p>1 <= costs.length <= 100 costs.length 为偶数 1 <= costs[i][0], costs[i][1] <= 1000
     *
     * @param costs
     * @return
     */
    public int twoCitySchedCost(int[][] costs) {
        int result = 0;
        int len = costs.length;
        int n = len >> 1;
        // 选择A - B差距最大的 n个去B, 其余的去A
        Arrays.sort(costs, (a, b) -> (b[0] - b[1]) - (a[0] - a[1]));

        for (int i = 0; i < n; i++) {
            result += costs[i][1];
        }
        for (int i = n; i < len; i++) {
            result += costs[i][0];
        }

        return result;
    }

    @Test
    public void removeKdigits() {
        String num = "10200";
        int k = 1;
        logResult(removeKdigits(num, 3));
    }

    /**
     * 402. 移掉K位数字 给定一个以字符串表示的非负整数 num，移除这个数中的 k 位数字，使得剩下的数字最小。
     *
     * <p>注意:
     *
     * <p>num 的长度小于 10002 且 ≥ k。 num 不会包含任何前导零。 示例 1 :
     *
     * <p>输入: num = "1432219", k = 3 输出: "1219" 解释: 移除掉三个数字 4, 3, 和 2 形成一个新的最小的数字 1219。 示例 2 :
     *
     * <p>输入: num = "10200", k = 1 输出: "200" 解释: 移掉首位的 1 剩下的数字为 200. 注意输出不能有任何前导零。 示例 3 :
     *
     * <p>输入: num = "10", k = 2 输出: "0" 解释: 从原数字移除所有的数字，剩余为空就是0。
     *
     * @param num
     * @param k
     * @return
     */
    public String removeKdigits(String num, int k) {
        if (num.length() <= k) {
            return "0";
        }
        Deque<Integer> stack = new LinkedList<>();
        int index = 0;
        for (char c : num.toCharArray()) {
            int n = c - '0';
            while (!stack.isEmpty() && n < stack.peekLast() && k > 0) {
                stack.removeLast();
                k--;
            }
            stack.addLast(n);
        }
        for (int i = 0; i < k; i++) {
            stack.removeLast();
        }

        StringBuilder sb = new StringBuilder();
        boolean leadingZero = true;
        for (int n : stack) {
            if (leadingZero && n == 0) continue;
            leadingZero = false;
            sb.append(n);
        }
        return sb.length() == 0 ? "0" : sb.toString();
    }

    @Test
    public void findMinArrowShots() {
        int[][] points = {{10, 16}, {2, 8}, {1, 6}, {7, 12}};
        logResult(findMinArrowShots(points));
    }
    /**
     * 452. 用最少数量的箭引爆气球
     *
     * <p>在二维空间中有许多球形的气球。对于每个气球，提供的输入是水平方向上，气球直径的开始和结束坐标。由于它是水平的，所以y坐标并不重要，因此只要知道开始和结束的x坐标就足够了。开始坐标总是小于结束坐标。平面内最多存在104个气球。
     *
     * <p>一支弓箭可以沿着x轴从不同点完全垂直地射出。在坐标x处射出一支箭，若有一个气球的直径的开始和结束坐标为 xstart，xend， 且满足 xstart ≤ x ≤
     * xend，则该气球会被引爆。可以射出的弓箭的数量没有限制。 弓箭一旦被射出之后，可以无限地前进。我们想找到使得所有气球全部被引爆，所需的弓箭的最小数量。
     *
     * <p>Example:
     *
     * <p>输入: [[10,16], [2,8], [1,6], [7,12]]
     *
     * <p>输出: 2
     *
     * <p>解释: 对于该样例，我们可以在x = 6（射爆[2,8},{1,6]两个气球）和 x = 11（射爆另外两个气球）。
     *
     * @param points
     * @return
     */
    public int findMinArrowShots(int[][] points) {
        if (points.length == 0) {
            return 0;
        }
        Arrays.sort(points, (a, b) -> a[1] == b[1] ? a[0] - b[0] : a[1] - b[1]);

        int result = 1;
        int xStart, xEnd, firstEnd = points[0][1];
        for (int[] p : points) {
            xStart = p[0];
            xEnd = p[1];
            if (firstEnd < xStart) {
                result++;
                firstEnd = xEnd;
            }
        }

        return result;
    }

    @Test
    public void eraseOverlapIntervals() {
        int[][] intervals = {{1, 2}, {2, 3}, {3, 4}, {1, 3}};
        logResult(eraseOverlapIntervals(intervals));
    }

    /**
     * 435. 无重叠区间
     *
     * <p>给定一个区间的集合，找到需要移除区间的最小数量，使剩余区间互不重叠。
     *
     * <p>注意:
     *
     * <p>可以认为区间的终点总是大于它的起点。 区间 [1,2] 和 [2,3] 的边界相互“接触”，但没有相互重叠。 示例 1:
     *
     * <p>输入: [ [1,2], [2,3], [3,4], [1,3] ]
     *
     * <p>输出: 1
     *
     * <p>解释: 移除 [1,3] 后，剩下的区间没有重叠。 示例 2:
     *
     * <p>输入: [ [1,2], [1,2], [1,2] ]
     *
     * <p>输出: 2
     *
     * <p>解释: 你需要移除两个 [1,2] 来使剩下的区间没有重叠。 示例 3:
     *
     * <p>输入: [ [1,2], [2,3] ]
     *
     * <p>输出: 0
     *
     * <p>解释: 你不需要移除任何区间，因为它们已经是无重叠的了。
     *
     * @param intervals
     * @return
     */
    public int eraseOverlapIntervals(int[][] intervals) {
        int result = 0;
        // 先按右区间排序
        Arrays.sort(intervals, (a, b) -> a[1] == b[1] ? a[0] - b[0] : a[1] - b[1]);
        logResult(intervals);
        int lastIndex = 0;
        for (int i = 1; i < intervals.length; i++) {
            int[] last = intervals[lastIndex];
            int[] current = intervals[i];
            if (current[0] >= last[1]) {
                lastIndex = i;
                continue;
            }
            result++;
        }

        return result;
    }

    /**
     * 678. 有效的括号字符串
     *
     * <p>给定一个只包含三种字符的字符串：（ ，） 和 *，写一个函数来检验这个字符串是否为有效字符串。有效字符串具有如下规则：
     *
     * <p>任何左括号 ( 必须有相应的右括号 )。 任何右括号 ) 必须有相应的左括号 ( 。 左括号 ( 必须在对应的右括号之前 )。 * 可以被视为单个右括号 ) ，或单个左括号 (
     * ，或一个空字符串。 一个空字符串也被视为有效字符串。 示例 1:
     *
     * <p>输入: "()" 输出: True 示例 2:
     *
     * <p>输入: "(*)" 输出: True 示例 3:
     *
     * <p>输入: "(*))" 输出: True 注意:
     *
     * <p>字符串大小将在 [1，100] 范围内。
     *
     * @param s
     * @return
     */
    public boolean checkValidString(String s) {
        // 左括号最少数量 和 左括号最多数量
        int leftMin = 0, leftMax = 0;
        for (char c : s.toCharArray()) {
            if (c == '(') {
                leftMin++;
                leftMax++;
            } else if (c == ')') {
                if (leftMin > 0) {
                    leftMin--;
                }
                // 左括号已用完
                if (leftMax-- == 0) {
                    return false;
                }

            } else if (c == '*') {
                // 作为右括号
                if (leftMin > 0) {
                    leftMin--;
                }
                // 作为左括号
                leftMax++;
            }
        }

        return leftMin == 0;
    }

    /**
     * 649. Dota2 参议院
     *
     * <p>Dota2 的世界里有两个阵营：Radiant(天辉)和 Dire(夜魇)
     *
     * <p>Dota2 参议院由来自两派的参议员组成。现在参议院希望对一个 Dota2
     * 游戏里的改变作出决定。他们以一个基于轮为过程的投票进行。在每一轮中，每一位参议员都可以行使两项权利中的一项：
     *
     * <p>禁止一名参议员的权利：
     *
     * <p>参议员可以让另一位参议员在这一轮和随后的几轮中丧失所有的权利。
     *
     * <p>宣布胜利：
     *
     * <p>如果参议员发现有权利投票的参议员都是同一个阵营的，他可以宣布胜利并决定在游戏中的有关变化。
     *
     * <p>给定一个字符串代表每个参议员的阵营。字母 “R” 和 “D” 分别代表了 Radiant（天辉）和 Dire（夜魇）。然后，如果有 n 个参议员，给定字符串的大小将是 n。
     *
     * <p>以轮为基础的过程从给定顺序的第一个参议员开始到最后一个参议员结束。这一过程将持续到投票结束。所有失去权利的参议员将在过程中被跳过。
     *
     * <p>假设每一位参议员都足够聪明，会为自己的政党做出最好的策略，你需要预测哪一方最终会宣布胜利并在 Dota2 游戏中决定改变。输出应该是 Radiant 或 Dire。
     *
     * <p>示例 1:
     *
     * <p>输入: "RD" 输出: "Radiant" 解释: 第一个参议员来自 Radiant
     * 阵营并且他可以使用第一项权利让第二个参议员失去权力，因此第二个参议员将被跳过因为他没有任何权利。然后在第二轮的时候，第一个参议员可以宣布胜利，因为他是唯一一个有投票权的人 示例 2:
     *
     * <p>输入: "RDD" 输出: "Dire" 解释: 第一轮中,第一个来自 Radiant 阵营的参议员可以使用第一项权利禁止第二个参议员的权利 第二个来自 Dire
     * 阵营的参议员会被跳过因为他的权利被禁止 第三个来自 Dire 阵营的参议员可以使用他的第一项权利禁止第一个参议员的权利 因此在第二轮只剩下第三个参议员拥有投票的权利,于是他可以宣布胜利
     *
     * <p>注意:
     *
     * <p>给定字符串的长度在 [1, 10,000] 之间.
     *
     * @param senate
     * @return
     */
    public String predictPartyVictory(String senate) {
        // 使用一个整数队列表示所有的参议员：1 代表 'Radiant' 阵营；0 代表 'Dire' 阵营。
        // 遍历队列：对于当前队头的参议员，如果另外一个阵营有禁令，则禁止当前参议员的权利；
        // 如果另外一个阵营没有禁令，则该参议员所在阵营的禁令数量加 1。
        Queue<Integer> queue = new LinkedList<>();
        int[] peoples = new int[2];
        int[] bans = new int[2];

        for (char c : senate.toCharArray()) {
            int x = c == 'R' ? 1 : 0;
            peoples[x]++;
            queue.offer(x);
        }
        while (peoples[0] > 0 && peoples[1] > 0) {
            int x = queue.poll();
            if (bans[x] > 0) {
                bans[x]--;
                peoples[x]--;
                continue;
            }

            // 对对方的禁令+1;
            bans[x ^ 1]++;
            queue.offer(x);
        }

        return peoples[1] > 0 ? "Radiant" : "Dire";
    }

    /**
     * 1414. 和为 K 的最少斐波那契数字数目
     *
     * <p>给你数字 k ，请你返回和为 k 的斐波那契数字的最少数目，其中，每个斐波那契数字都可以被使用多次。
     *
     * <p>斐波那契数字定义为：
     *
     * <p>F1 = 1 F2 = 1 Fn = Fn-1 + Fn-2 ， 其中 n > 2 。 数据保证对于给定的 k ，一定能找到可行解。
     *
     * <p>示例 1：
     *
     * <p>输入：k = 7 输出：2 解释：斐波那契数字为：1，1，2，3，5，8，13，…… 对于 k = 7 ，我们可以得到 2 + 5 = 7 。 示例 2：
     *
     * <p>输入：k = 10 输出：2 解释：对于 k = 10 ，我们可以得到 2 + 8 = 10 。 示例 3：
     *
     * <p>输入：k = 19 输出：3 解释：对于 k = 19 ，我们可以得到 1 + 5 + 13 = 19 。
     *
     * <p>提示：
     *
     * <p>1 <= k <= 10^9
     *
     * @param k
     * @return
     */
    public int findMinFibonacciNumbers(int k) {
        int a = 1, b = 1;
        List<Integer> list = new ArrayList<>();
        list.add(a);
        list.add(b);
        while (a + b <= k) {
            int tmp = a + b;
            a = b;
            b = tmp;
            list.add(b);
        }
        int count = 0;
        for (int i = list.size() - 1; i >= 0; i--) {
            int num = list.get(i);
            if (k >= num) {
                k -= num;
                count++;
            }
        }

        return count;
    }

    @Test
    public void monotoneIncreasingDigits() {
        int num = 101;
        logResult(monotoneIncreasingDigits(num));
    }
    /**
     * 738. 单调递增的数字
     *
     * <p>给定一个非负整数 N，找出小于或等于 N 的最大的整数，同时这个整数需要满足其各个位数上的数字是单调递增。
     *
     * <p>（当且仅当每个相邻位数上的数字 x 和 y 满足 x <= y 时，我们称这个整数是单调递增的。）
     *
     * <p>示例 1:
     *
     * <p>输入: N = 10 输出: 9 示例 2:
     *
     * <p>输入: N = 1234 输出: 1234 示例 3:
     *
     * <p>输入: N = 332 输出: 299 说明: N 是在 [0, 10^9] 范围内的一个整数。
     *
     * @param N
     * @return
     */
    public int monotoneIncreasingDigits(int N) {
        String s = String.valueOf(N);
        char[] chars = s.toCharArray();
        int i = 1;
        while (i < chars.length && chars[i - 1] <= chars[i]) {
            i++;
        }
        while (0 < i && i < chars.length && chars[i - 1] > chars[i]) {
            chars[--i]--;
        }
        for (int j = i + 1; j < chars.length; ++j) {
            chars[j] = '9';
        }
        return Integer.parseInt(new String(chars));
    }

    /**
     * 763. 划分字母区间
     *
     * <p>字符串 S 由小写字母组成。我们要把这个字符串划分为尽可能多的片段，同一个字母只会出现在其中的一个片段。返回一个表示每个字符串片段的长度的列表。
     *
     * <p>示例 1：
     *
     * <p>输入：S = "ababcbacadefegdehijhklij" 输出：[9,7,8] 解释： 划分结果为 "ababcbaca", "defegde", "hijhklij"。
     * 每个字母最多出现在一个片段中。 像 "ababcbacadefegde", "hijhklij" 的划分是错误的，因为划分的片段数较少。
     *
     * <p>提示：
     *
     * <p>S的长度在[1, 500]之间。 S只包含小写字母 'a' 到 'z' 。
     *
     * @param S
     * @return
     */
    public List<Integer> partitionLabels(String S) {
        int[] lastIndex = new int[26];
        for (int i = 0; i < S.length(); i++) {
            char c = S.charAt(i);
            lastIndex[c - 'a'] = i;
        }
        List<Integer> result = new ArrayList<>();
        int last = 0, prev = -1;
        for (int i = 0; i < S.length(); i++) {
            char c = S.charAt(i);
            last = Math.max(lastIndex[c - 'a'], last);
            if (last == i) {
                result.add(i - prev);
                prev = last;
            }
        }

        return result;
    }

    @Test
    public void maxNumOfSubstrings() {
        String s = "adefaddaccc";
        logResult(maxNumOfSubstrings(s));
    }

    /**
     * 1520. 最多的不重叠子字符串
     *
     * <p>给你一个只包含小写字母的字符串 s ，你需要找到 s 中最多数目的非空子字符串，满足如下条件：
     *
     * <p>这些字符串之间互不重叠，也就是说对于任意两个子字符串 s[i..j] 和 s[k..l] ，要么 j < k 要么 i > l 。 如果一个子字符串包含字符 char ，那么 s
     * 中所有 char 字符都应该在这个子字符串中。
     * 请你找到满足上述条件的最多子字符串数目。如果有多个解法有相同的子字符串数目，请返回这些子字符串总长度最小的一个解。可以证明最小总长度解是唯一的。
     *
     * <p>请注意，你可以以 任意 顺序返回最优解的子字符串。
     *
     * <p>示例 1：
     *
     * <p>输入：s = "adefaddaccc" 输出：["e","f","ccc"] 解释：下面为所有满足第二个条件的子字符串： [ "adefaddaccc" "adefadda",
     * "ef", "e", "f", "ccc", ] 如果我们选择第一个字符串，那么我们无法再选择其他任何字符串，所以答案为 1 。如果我们选择 "adefadda"
     * ，剩下子字符串中我们只可以选择 "ccc" ，它是唯一不重叠的子字符串，所以答案为 2 。同时我们可以发现，选择 "ef" 不是最优的，因为它可以被拆分成 2
     * 个子字符串。所以最优解是选择 ["e","f","ccc"] ，答案为 3 。不存在别的相同数目子字符串解。 示例 2：
     *
     * <p>输入：s = "abbaccd" 输出：["d","bb","cc"] 解释：注意到解 ["d","abba","cc"] 答案也为 3 ，但它不是最优解，因为它的总长度更长。
     *
     * <p>提示：
     *
     * <p>1 <= s.length <= 10^5 s 只包含小写英文字母。
     *
     * @param s
     * @return
     */
    public List<String> maxNumOfSubstrings(String s) {
        int[][] letters = new int[26][2];
        for (int i = 0; i < 26; i++) {
            letters[i][0] = -1;
        }
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (letters[c - 'a'][0] == -1) {
                letters[c - 'a'][0] = i;
            }
            letters[c - 'a'][1] = i;
        }
        logResult(letters);
        for (int i = 0; i < 26; i++) {
            if (letters[i][0] == -1) {
                continue;
            }
            for (int j = letters[i][0]; j <= letters[i][1]; ++j) {
                int charIdx = s.charAt(j) - 'a';
                if (letters[i][0] <= letters[charIdx][0] && letters[charIdx][1] <= letters[i][1]) {
                    continue;
                }
                letters[i][0] = Math.min(letters[i][0], letters[charIdx][0]);
                letters[i][1] = Math.max(letters[i][1], letters[charIdx][1]);
                j = letters[i][0];
            }
        }
        logResult(letters);
        Arrays.sort(
                letters,
                (a, b) -> {
                    if (a[1] == b[1]) {
                        return b[0] - a[0];
                    }

                    return a[1] - b[1];
                });
        logResult(letters);
        List<String> result = new ArrayList<>();
        int end = -1;
        for (int[] letter : letters) {
            int left = letter[0], right = letter[1];
            if (left == -1) {
                continue;
            }
            if (end == -1 || left > end) {
                end = right;
                result.add(s.substring(left, right + 1));
            }
        }

        return result;
    }

    /**
     * 1400. 构造 K 个回文字符串
     *
     * <p>给你一个字符串 s 和一个整数 k 。请你用 s 字符串中 所有字符 构造 k 个非空 回文串 。
     *
     * <p>如果你可以用 s 中所有字符构造 k 个回文字符串，那么请你返回 True ，否则返回 False 。
     *
     * <p>示例 1：
     *
     * <p>输入：s = "annabelle", k = 2 输出：true 解释：可以用 s 中所有字符构造 2 个回文字符串。 一些可行的构造方案包括："anna" +
     * "elble"，"anbna" + "elle"，"anellena" + "b" 示例 2：
     *
     * <p>输入：s = "leetcode", k = 3 输出：false 解释：无法用 s 中所有字符构造 3 个回文串。 示例 3：
     *
     * <p>输入：s = "true", k = 4 输出：true 解释：唯一可行的方案是让 s 中每个字符单独构成一个字符串。 示例 4：
     *
     * <p>输入：s = "yzyzyzyzyzyzyzy", k = 2 输出：true 解释：你只需要将所有的 z 放在一个字符串中，所有的 y
     * 放在另一个字符串中。那么两个字符串都是回文串。 示例 5：
     *
     * <p>输入：s = "cr", k = 7 输出：false 解释：我们没有足够的字符去构造 7 个回文串。
     *
     * <p>提示：
     *
     * <p>1 <= s.length <= 10^5 s 中所有字符都是小写英文字母。 1 <= k <= 10^5
     *
     * @param s
     * @param k
     * @return
     */
    public boolean canConstruct(String s, int k) {
        int[] letters = new int[26];
        for (char c : s.toCharArray()) {
            letters[c - 'a']++;
        }
        int num = 0;
        // 奇数的个数
        for (int count : letters) {
            if ((count & 1) == 1) {
                num++;
            }
        }
        if (num > k) {
            return false;
        }
        return k <= s.length();
    }

    @Test
    public void minimumSwap() {
        String s1 = "xxyyxyxyxx", s2 = "xyyxyxxxyx";
        logResult(minimumSwap(s1, s2));
    }
    /**
     * 1247. 交换字符使得字符串相同
     *
     * <p>有两个长度相同的字符串 s1 和 s2，且它们其中 只含有 字符 "x" 和 "y"，你需要通过「交换字符」的方式使这两个字符串相同。
     *
     * <p>每次「交换字符」的时候，你都可以在两个字符串中各选一个字符进行交换。
     *
     * <p>交换只能发生在两个不同的字符串之间，绝对不能发生在同一个字符串内部。也就是说，我们可以交换 s1[i] 和 s2[j]，但不能交换 s1[i] 和 s1[j]。
     *
     * <p>最后，请你返回使 s1 和 s2 相同的最小交换次数，如果没有方法能够使得这两个字符串相同，则返回 -1 。
     *
     * <p>示例 1：
     *
     * <p>输入：s1 = "xx", s2 = "yy" 输出：1 解释： 交换 s1[0] 和 s2[1]，得到 s1 = "yx"，s2 = "yx"。 示例 2：
     *
     * <p>输入：s1 = "xy", s2 = "yx" 输出：2 解释： 交换 s1[0] 和 s2[0]，得到 s1 = "yy"，s2 = "xx" 。 交换 s1[0] 和
     * s2[1]，得到 s1 = "xy"，s2 = "xy" 。 注意，你不能交换 s1[0] 和 s1[1] 使得 s1 变成 "yx"，因为我们只能交换属于两个不同字符串的字符。 示例
     * 3：
     *
     * <p>输入：s1 = "xx", s2 = "xy" 输出：-1 示例 4：
     *
     * <p>输入：s1 = "xxyyxyxyxx", s2 = "xyyxyxxxyx" 输出：4
     *
     * <p>提示：
     *
     * <p>1 <= s1.length, s2.length <= 1000 s1, s2 只包含 'x' 或 'y'。
     *
     * @param s1
     * @param s2
     * @return
     */
    public int minimumSwap(String s1, String s2) {
        int len = s1.length();
        int xx = 0, yy = 0;
        for (int i = 0; i < len; i++) {
            char c1 = s1.charAt(i), c2 = s2.charAt(i);
            if (c1 == c2) {
                continue;
            }
            if (c1 == 'x') {
                xx++;
            } else {
                yy++;
            }
        }
        // 奇数个
        if (((xx + yy) & 1) == 1) {
            return -1;
        }
        int result = (xx + 1) / 2 + (yy + 1) / 2;

        return result;
    }

    @Test
    public void reconstructMatrix() {
        int upper = 9, lower = 2;
        int[] colsum = {0, 1, 2, 0, 0, 0, 0, 0, 2, 1, 2, 1, 2};
        logResult(reconstructMatrix(upper, lower, colsum));
    }

    /**
     * 1253. 重构 2 行二进制矩阵
     *
     * <p>给你一个 2 行 n 列的二进制数组：
     *
     * <p>矩阵是一个二进制矩阵，这意味着矩阵中的每个元素不是 0 就是 1。 第 0 行的元素之和为 upper。 第 1 行的元素之和为 lower。 第 i 列（从 0
     * 开始编号）的元素之和为 colsum[i]，colsum 是一个长度为 n 的整数数组。 你需要利用 upper，lower 和 colsum
     * 来重构这个矩阵，并以二维整数数组的形式返回它。
     *
     * <p>如果有多个不同的答案，那么任意一个都可以通过本题。
     *
     * <p>如果不存在符合要求的答案，就请返回一个空的二维数组。
     *
     * <p>示例 1：
     *
     * <p>输入：upper = 2, lower = 1, colsum = [1,1,1] 输出：[[1,1,0],[0,0,1]] 解释：[[1,0,1],[0,1,0]] 和
     * [[0,1,1],[1,0,0]] 也是正确答案。 示例 2：
     *
     * <p>输入：upper = 2, lower = 3, colsum = [2,2,1,1] 输出：[] 示例 3：
     *
     * <p>输入：upper = 5, lower = 5, colsum = [2,1,2,0,1,0,1,2,0,1]
     * 输出：[[1,1,1,0,1,0,0,1,0,0],[1,0,1,0,0,0,1,1,0,1]]
     *
     * <p>提示：
     *
     * <p>1 <= colsum.length <= 10^5 0 <= upper, lower <= colsum.length 0 <= colsum[i] <= 2
     *
     * @param upper
     * @param lower
     * @param colsum
     * @return
     */
    public List<List<Integer>> reconstructMatrix(int upper, int lower, int[] colsum) {
        List<List<Integer>> result = new ArrayList<>();
        int len = colsum.length;

        int[][] list = new int[2][len];

        // 贪心算法, 先 把 2 分配完, 然后分配 1
        for (int i = 0; i < len; i++) {
            if (colsum[i] == 2) {
                list[0][i] = 1;
                list[1][i] = 1;
                upper--;
                lower--;
            }
        }
        for (int i = 0; i < len; i++) {
            if (colsum[i] != 1) {
                continue;
            }
            if (upper > 0) {
                list[0][i] = 1;
                upper--;
            } else {
                list[1][i] = 1;
                lower--;
            }
        }
        if (upper != 0 || lower != 0) {
            return result;
        }
        /*result.add(Arrays.stream(list[0]).boxed().collect(Collectors.toList()));
        result.add(Arrays.stream(list[1]).boxed().collect(Collectors.toList()));*/
        for (int[] arr : list) {
            List<Integer> nums = new ArrayList<>();
            for (int val : arr) {
                nums.add(val);
            }
            result.add(nums);
        }
        return result;
    }

    /**
     * 1053. 交换一次的先前排列
     *
     * <p>给你一个正整数的数组 A（其中的元素不一定完全不同），请你返回可在 一次交换（交换两数字 A[i] 和 A[j] 的位置）后得到的、按字典序排列小于 A 的最大可能排列。
     *
     * <p>如果无法这么操作，就请返回原数组。
     *
     * <p>示例 1：
     *
     * <p>输入：[3,2,1] 输出：[3,1,2] 解释： 交换 2 和 1
     *
     * <p>示例 2：
     *
     * <p>输入：[1,1,5] 输出：[1,1,5] 解释： 这已经是最小排列
     *
     * <p>示例 3：
     *
     * <p>输入：[1,9,4,6,7] 输出：[1,7,4,6,9] 解释： 交换 9 和 7
     *
     * <p>示例 4：
     *
     * <p>输入：[3,1,1,3] 输出：[1,3,1,3] 解释： 交换 1 和 3
     *
     * <p>提示：
     *
     * <p>1 <= A.length <= 10000 1 <= A[i] <= 10000
     *
     * @param A
     * @return
     */
    public int[] prevPermOpt1(int[] A) {
        int len = A.length;
        int curMax = -1;
        int index = -1;
        // 找到第一个递增序列
        boolean hasResult = false;
        for (int i = len - 2; i >= 0; i--) {
            if (A[i + 1] < A[i]) { // 此处逆序，需要移动A[i]
                for (int j = i + 1; j < len; j++) { // 寻找与 A[i] 交换的位置
                    if (A[i] > A[j]) { // 必须满足 A[i] > A[j]，否则不能满足交换后的字典序小于原始字典序
                        hasResult = true;
                        if (A[j] > curMax) {
                            curMax = A[j];
                            index = j;
                        }
                    }
                }
                if (hasResult) {
                    int tmp = A[i];
                    A[i] = A[index];
                    A[index] = tmp;
                    return A;
                }
            }
        }
        return A;
    }

    @Test
    public void groupThePeople() {
        int[] groupSizes = {2, 1, 3, 3, 3, 2};
        logResult(groupThePeople(groupSizes));
    }

    /**
     * 1282. 用户分组
     *
     * <p>有 n 位用户参加活动，他们的 ID 从 0 到 n - 1，每位用户都 恰好 属于某一用户组。给你一个长度为 n 的数组
     * groupSizes，其中包含每位用户所处的用户组的大小，请你返回用户分组情况（存在的用户组以及每个组中用户的 ID）。
     *
     * <p>你可以任何顺序返回解决方案，ID 的顺序也不受限制。此外，题目给出的数据保证至少存在一种解决方案。
     *
     * <p>示例 1：
     *
     * <p>输入：groupSizes = [3,3,3,3,3,1,3] 输出：[[5],[0,1,2],[3,4,6]] 解释： 其他可能的解决方案有
     * [[2,1,6],[5],[0,4,3]] 和 [[5],[0,6,2],[4,3,1]]。 示例 2：
     *
     * <p>输入：groupSizes = [2,1,3,3,3,2] 输出：[[1],[0,5],[2,3,4]]
     *
     * <p>提示：
     *
     * <p>groupSizes.length == n 1 <= n <= 500 1 <= groupSizes[i] <= n
     *
     * @param groupSizes
     * @return
     */
    public List<List<Integer>> groupThePeople(int[] groupSizes) {
        List<List<Integer>> result = new ArrayList<>();
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < groupSizes.length; i++) {
            int count = groupSizes[i];
            List<Integer> list = map.computeIfAbsent(count, k -> new ArrayList<>());
            list.add(i);
            if (list.size() == count) {
                result.add(list);
                map.remove(count);
            }
        }
        /*for (Integer count : map.keySet()) {
            List<Integer> list = map.get(count);
            int index = 0;
            while (index < list.size()) {
                result.add(list.subList(index, index + count));
                index += count;
            }
        }*/

        return result;
    }

    /**
     * 1288. 删除被覆盖区间
     *
     * <p>给你一个区间列表，请你删除列表中被其他区间所覆盖的区间。
     *
     * <p>只有当 c <= a 且 b <= d 时，我们才认为区间 [a,b) 被区间 [c,d) 覆盖。
     *
     * <p>在完成所有删除操作后，请你返回列表中剩余区间的数目。
     *
     * <p>示例：
     *
     * <p>输入：intervals = [[1,4],[3,6],[2,8]] 输出：2 解释：区间 [3,6] 被区间 [2,8] 覆盖，所以它被删除了。
     *
     * <p>提示：
     *
     * <p>1 <= intervals.length <= 1000 0 <= intervals[i][0] < intervals[i][1] <= 10^5 对于所有的 i !=
     * j：intervals[i] != intervals[j]
     *
     * @param intervals
     * @return
     */
    public int removeCoveredIntervals(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> a[0] == b[0] ? b[1] - a[1] : a[0] - b[0]);
        int count = 0;
        int end = 0, preEnd = 0;
        for (int[] interval : intervals) {
            end = interval[1];
            if (preEnd < end) {
                count++;
                preEnd = end;
            }
        }

        return count;
    }

    @Test
    public void minCost() {
        String s = "aabaa";
        int[] cost = {1, 2, 3, 4, 1};
        logResult(minCost(s, cost));
    }

    /**
     * 5509. 避免重复字母的最小删除成本
     *
     * <p>给你一个字符串 s 和一个整数数组 cost ，其中 cost[i] 是从 s 中删除字符 i 的代价。
     *
     * <p>返回使字符串任意相邻两个字母不相同的最小删除成本。
     *
     * <p>请注意，删除一个字符后，删除其他字符的成本不会改变。
     *
     * <p>示例 1：
     *
     * <p>输入：s = "abaac", cost = [1,2,3,4,5] 输出：3 解释：删除字母 "a" 的成本为 3，然后得到 "abac"（字符串中相邻两个字母不相同）。 示例
     * 2：
     *
     * <p>输入：s = "abc", cost = [1,2,3] 输出：0 解释：无需删除任何字母，因为字符串中不存在相邻两个字母相同的情况。 示例 3：
     *
     * <p>输入：s = "aabaa", cost = [1,2,3,4,1] 输出：2 解释：删除第一个和最后一个字母，得到字符串 ("aba") 。
     *
     * <p>提示：
     *
     * <p>s.length == cost.length 1 <= s.length, cost.length <= 10^5 1 <= cost[i] <= 10^4 s
     * 中只含有小写英文字母
     *
     * @param s
     * @param cost
     * @return
     */
    public int minCost(String s, int[] cost) {
        int result = 0, len = s.length();

        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            int start = i, end = i;
            while (i + 1 < len && c == s.charAt(i + 1)) {
                i++;
                end = i;
            }
            if (start < end) {
                result += getMinCost(cost, start, end);
            }
        }

        return result;
    }

    private int getMinCost(int[] cost, int start, int end) {
        int sum = 0, max = 0;
        for (int i = start; i <= end; i++) {
            sum += cost[i];
            max = Math.max(max, cost[i]);
        }
        return sum - max;
    }

    @Test
    public void maxEvents() {

        int[][] events = {{1, 2}, {1, 2}, {3, 3}, {1, 5}, {1, 5}};
        logResult(maxEvents(events));
    }

    /**
     * 1353. 最多可以参加的会议数目
     *
     * <p>给你一个数组 events，其中 events[i] = [startDayi, endDayi] ，表示会议 i 开始于 startDayi ，结束于 endDayi 。
     *
     * <p>你可以在满足 startDayi <= d <= endDayi 中的任意一天 d 参加会议 i 。注意，一天只能参加一个会议。
     *
     * <p>请你返回你可以参加的 最大 会议数目。
     *
     * <p>示例 1：
     *
     * <p>输入：events = [[1,2],[2,3],[3,4]] 输出：3 解释：你可以参加所有的三个会议。 安排会议的一种方案如上图。 第 1 天参加第一个会议。 第 2
     * 天参加第二个会议。 第 3 天参加第三个会议。 示例 2：
     *
     * <p>输入：events= [[1,2],[2,3],[3,4],[1,2]] 输出：4 示例 3：
     *
     * <p>输入：events = [[1,4],[4,4],[2,2],[3,4],[1,1]] 输出：4 示例 4：
     *
     * <p>输入：events = [[1,100000]] 输出：1 示例 5：
     *
     * <p>输入：events = [[1,1],[1,2],[1,3],[1,4],[1,5],[1,6],[1,7]] 输出：7
     *
     * <p>提示：
     *
     * <p>1 <= events.length <= 10^5 events[i].length == 2 1 <= events[i][0] <= events[i][1] <= 10^5
     *
     * @param events
     * @return
     */
    public int maxEvents(int[][] events) {
        Arrays.sort(events, (a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);

        int result = 0;
        /*for (int[] event : events) {
            for (int i = event[0]; i <= event[1]; i++) {
                if (!visited[i]) {
                    visited[i] = true;
                    result++;
                    break;
                }
            }
        }*/
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        int idx = 0, day = 1;
        while (idx < events.length || !heap.isEmpty()) {
            // 将day天开始能参加的所有会议入堆；
            while (idx < events.length && events[idx][0] == day) {
                heap.offer(events[idx++][1]);
            }
            // 将已经结束的会议删除；（即：没机会参加到的；）；
            while (!heap.isEmpty() && heap.peek() < day) {
                heap.poll();
            }
            // 此时的堆顶元素就是我们今天（即day天）要参加的那一个会议；
            // 一天只能参加一场会议将结束时间最早的安排了;
            if (!heap.isEmpty()) {
                result++;
                heap.poll(); // 决定参加这个会议后，记得弹出pq候选队列；
            }
            day++;
        }

        return result;
    }

    @Test
    public void minOperationsMaxProfit() {
        int[] customers = {10, 10, 6, 4, 7};
        int boardingCost = 3, runningCost = 8;
        logResult(minOperationsMaxProfit(customers, boardingCost, runningCost));
    }

    /**
     * 5524. 经营摩天轮的最大利润
     *
     * <p>你正在经营一座摩天轮，该摩天轮共有 4 个座舱 ，每个座舱 最多可以容纳 4 位游客 。你可以 逆时针 轮转座舱，但每次轮转都需要支付一定的运行成本 runningCost
     * 。摩天轮每次轮转都恰好转动 1 / 4 周。
     *
     * <p>给你一个长度为 n 的数组 customers ， customers[i] 是在第 i 次轮转（下标从 0 开始）之前到达的新游客的数量。这也意味着你必须在新游客到来前轮转 i
     * 次。每位游客在登上离地面最近的座舱前都会支付登舱成本 boardingCost ，一旦该座舱再次抵达地面，他们就会离开座舱结束游玩。
     *
     * <p>你可以随时停下摩天轮，即便是 在服务所有游客之前 。如果你决定停止运营摩天轮，为了保证所有游客安全着陆，将免费进行所有后续轮转 。注意，如果有超过 4 位游客在等摩天轮，那么只有
     * 4 位游客可以登上摩天轮，其余的需要等待 下一次轮转 。
     *
     * <p>返回最大化利润所需执行的 最小轮转次数 。 如果不存在利润为正的方案，则返回 -1 。
     *
     * <p>示例 1：
     *
     * <p>输入：customers = [8,3], boardingCost = 5, runningCost = 6 输出：3 解释：座舱上标注的数字是该座舱的当前游客数。 1. 8
     * 位游客抵达，4 位登舱，4 位等待下一舱，摩天轮轮转。当前利润为 4 * $5 - 1 * $6 = $14 。 2. 3 位游客抵达，4 位在等待的游客登舱，其他 3
     * 位等待，摩天轮轮转。当前利润为 8 * $5 - 2 * $6 = $28 。 3. 最后 3 位游客登舱，摩天轮轮转。当前利润为 11 * $5 - 3 * $6 = $37 。 轮转
     * 3 次得到最大利润，最大利润为 $37 。 示例 2：
     *
     * <p>输入：customers = [10,9,6], boardingCost = 6, runningCost = 4 输出：7 解释： 1. 10 位游客抵达，4 位登舱，6
     * 位等待下一舱，摩天轮轮转。当前利润为 4 * $6 - 1 * $4 = $20 。 2. 9 位游客抵达，4 位登舱，11 位等待（2 位是先前就在等待的，9
     * 位新加入等待的），摩天轮轮转。当前利润为 8 * $6 - 2 * $4 = $40 。 3. 最后 6 位游客抵达，4 位登舱，13 位等待，摩天轮轮转。当前利润为 12 * $6 -
     * 3 * $4 = $60 。 4. 4 位登舱，9 位等待，摩天轮轮转。当前利润为 * $6 - 4 * $4 = $80 。 5. 4 位登舱，5 位等待，摩天轮轮转。当前利润为 20
     * * $6 - 5 * $4 = $100 。 6. 4 位登舱，1 位等待，摩天轮轮转。当前利润为 24 * $6 - 6 * $4 = $120 。 7. 4
     * 位登舱，摩天轮轮转。当前利润为 25 * $6 - 7 * $4 = $122 。 轮转 7 次得到最大利润，最大利润为$122 。 示例 3：
     *
     * <p>输入：customers = [3,4,0,5,1], boardingCost = 1, runningCost = 92 输出：-1 解释： 1. 3 位游客抵达，3
     * 位登舱，0 位等待，摩天轮轮转。当前利润为 3 * $1 - 1 * $92 = -$89 。 2. 4 位游客抵达，4 位登舱，0 位等待，摩天轮轮转。当前利润为 is 7 * $1
     * - 2 * $92 = -$177 。 3. 0 位游客抵达，0 位登舱，0 位等待，摩天轮轮转。当前利润为 7 * $1 - 3 * $92 = -$269 。 4. 5
     * 位游客抵达，4 位登舱，1 位等待，摩天轮轮转。当前利润为 12 * $1 - 4 * $92 = -$356 。 5. 1 位游客抵达，2 位登舱，0 位等待，摩天轮轮转。当前利润为
     * 13 * $1 - 5 * $92 = -$447 。 利润永不为正，所以返回 -1 。 示例 4：
     *
     * <p>输入：customers = [10,10,6,4,7], boardingCost = 3, runningCost = 8 输出：9 解释： 1. 10 位游客抵达，4
     * 位登舱，6 位等待，摩天轮轮转。当前利润为 4 * $3 - 1 * $8 = $4 。 2. 10 位游客抵达，4 位登舱，12 位等待，摩天轮轮转。当前利润为 8 * $3 - 2
     * * $8 = $8 。 3. 6 位游客抵达，4 位登舱，14 位等待，摩天轮轮转。当前利润为 12 * $3 - 3 * $8 = $12 。 4. 4 位游客抵达，4 位登舱，14
     * 位等待，摩天轮轮转。当前利润为 16 * $3 - 4 * $8 = $16 。 5. 7 位游客抵达，4 位登舱，17 位等待，摩天轮轮转。当前利润为 20 * $3 - 5 * $8
     * = $20 。 6. 4 位登舱，13 位等待，摩天轮轮转。当前利润为 24 * $3 - 6 * $8 = $24 。 7. 4 位登舱，9 位等待，摩天轮轮转。当前利润为 28 *
     * $3 - 7 * $8 = $28 。 8. 4 位登舱，5 位等待，摩天轮轮转。当前利润为 32 * $3 - 8 * $8 = $32 。 ​​​​​​​9. 4 位登舱，1
     * 位等待，摩天轮轮转。当前利润为 36 * $3 - 9 * $8 = $36 。 ​​​​​​​10. 1 位登舱，0 位等待，摩天轮轮转。当前利润为 37 * $3 - 10 * $8
     * = $31 。 轮转 9 次得到最大利润，最大利润为 $36 。
     *
     * <p>提示：
     *
     * <p>n == customers.length 1 <= n <= 105 0 <= customers[i] <= 50 1 <= boardingCost, runningCost
     * <= 100
     *
     * @param customers
     * @param boardingCost
     * @param runningCost
     * @return
     */
    public int minOperationsMaxProfit(int[] customers, int boardingCost, int runningCost) {
        int count = 0, max = 0;
        int num = 0;
        int profit = 0, maxProfit = 0;
        for (int customer : customers) {
            num += customer;
            int person = 4;
            if (num >= 4) {
                num -= 4;
            } else {
                person = num;
                num = 0;
            }
            count++;
            profit += boardingCost * person - runningCost;
            if (profit > maxProfit) {
                maxProfit = profit;
                max = count;
            }
        }
        while (num > 0) {
            int person = 4;
            if (num >= 4) {
                num -= 4;
            } else {
                person = num;
                num = 0;
            }
            count++;
            profit += boardingCost * person - runningCost;
            if (profit > maxProfit) {
                maxProfit = profit;
                max = count;
            }
        }
        return profit < 0 ? -1 : max;
    }

    @Test
    public void restoreMatrix() {
        int[] rowSum = {0}, colSum = {0};
        int[][] result = restoreMatrix(rowSum, colSum);
        logResult(result);
    }
    /**
     * 5518. 给定行和列的和求可行矩阵
     *
     * <p>给你两个非负整数数组 rowSum 和 colSum ，其中 rowSum[i] 是二维矩阵中第 i 行元素的和， colSum[j] 是第 j
     * 列元素的和。换言之你不知道矩阵里的每个元素，但是你知道每一行和每一列的和。
     *
     * <p>请找到大小为 rowSum.length x colSum.length 的任意 非负整数 矩阵，且该矩阵满足 rowSum 和 colSum 的要求。
     *
     * <p>请你返回任意一个满足题目要求的二维矩阵，题目保证存在 至少一个 可行矩阵。
     *
     * <p>示例 1：
     *
     * <p>输入：rowSum = [3,8], colSum = [4,7] 输出：[[3,0], [1,7]] 解释： 第 0 行：3 + 0 = 0 == rowSum[0] 第 1
     * 行：1 + 7 = 8 == rowSum[1] 第 0 列：3 + 1 = 4 == colSum[0] 第 1 列：0 + 7 = 7 == colSum[1]
     * 行和列的和都满足题目要求，且所有矩阵元素都是非负的。 另一个可行的矩阵为：[[1,2], [3,5]] 示例 2：
     *
     * <p>输入：rowSum = [5,7,10], colSum = [8,6,8] 输出：[[0,5,0], [6,1,0], [2,0,8]] 示例 3：
     *
     * <p>输入：rowSum = [14,9], colSum = [6,9,8] 输出：[[0,9,5], [6,0,3]] 示例 4：
     *
     * <p>输入：rowSum = [1,0], colSum = [1] 输出：[[1], [0]] 示例 5：
     *
     * <p>输入：rowSum = [0], colSum = [0] 输出：[[0]]
     *
     * <p>提示：
     *
     * <p>1 <= rowSum.length, colSum.length <= 500 0 <= rowSum[i], colSum[i] <= 108 sum(rows) ==
     * sum(columns)
     *
     * @param rowSum
     * @param colSum
     * @return
     */
    public int[][] restoreMatrix(int[] rowSum, int[] colSum) {
        int rows = rowSum.length, cols = colSum.length;
        int[][] result = new int[rows][cols];
        int len = Math.min(rows, cols);
        for (int i = 0; i < len; i++) {
            int num = Math.min(rowSum[i], colSum[i]);
            result[i][i] = num;
            rowSum[i] -= num;
            colSum[i] -= num;
        }
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (rowSum[i] == 0 || colSum[j] == 0) {
                    continue;
                }
                int num = Math.min(rowSum[i], colSum[j]);
                result[i][j] = num;
                rowSum[i] -= num;
                colSum[j] -= num;
            }
        }

        return result;
    }

    /**
     * 984. 不含 AAA 或 BBB 的字符串
     *
     * <p>给定两个整数 A 和 B，返回任意字符串 S，要求满足：
     *
     * <p>S 的长度为 A + B，且正好包含 A 个 'a' 字母与 B 个 'b' 字母； 子串 'aaa' 没有出现在 S 中； 子串 'bbb' 没有出现在 S 中。
     *
     * <p>示例 1：
     *
     * <p>输入：A = 1, B = 2 输出："abb" 解释："abb", "bab" 和 "bba" 都是正确答案。 示例 2：
     *
     * <p>输入：A = 4, B = 1 输出："aabaa"
     *
     * <p>提示：
     *
     * <p>0 <= A <= 100 0 <= B <= 100 对于给定的 A 和 B，保证存在满足要求的 S。
     *
     * @param A
     * @param B
     * @return
     */
    public String strWithout3a3b(int A, int B) {
        StringBuilder sb = new StringBuilder();
        while (A > 0 || B > 0) {
            boolean flag = true;
            int len = sb.length();
            if (len >= 2 && sb.charAt(len - 1) == sb.charAt(len - 2)) {
                if (sb.charAt(len - 1) == 'a') {
                    flag = false;
                }
            } else {
                if (B > A) {
                    flag = false;
                }
            }
            if (flag) {
                sb.append("a");
                A--;
            } else {
                sb.append("b");
                B--;
            }
        }
        return sb.toString();
    }

    /**
     * 861. 翻转矩阵后的得分
     *
     * <p>有一个二维矩阵 A 其中每个元素的值为 0 或 1 。
     *
     * <p>移动是指选择任一行或列，并转换该行或列中的每一个值：将所有 0 都更改为 1，将所有 1 都更改为 0。
     *
     * <p>在做出任意次数的移动后，将该矩阵的每一行都按照二进制数来解释，矩阵的得分就是这些数字的总和。
     *
     * <p>返回尽可能高的分数。
     *
     * <p>示例：
     *
     * <p>输入：[[0,0,1,1],[1,0,1,0],[1,1,0,0]] 输出：39 解释： 转换为 [[1,1,1,1],[1,0,0,1],[1,1,1,1]] 0b1111 +
     * 0b1001 + 0b1111 = 15 + 9 + 15 = 39
     *
     * <p>提示：
     *
     * <p>1 <= A.length <= 20 1 <= A[0].length <= 20 A[i][j] 是 0 或 1
     *
     * @param A
     * @return
     */
    public int matrixScore(int[][] A) {
        int rows = A.length, cols = A[0].length;
        // 行翻转 保证第一位是1即可
        for (int i = 0; i < rows; i++) {
            if (A[i][0] == 1) {
                continue;
            }
            for (int j = 0; j < cols; j++) {
                A[i][j] ^= 1;
            }
        }

        // 列翻转 保证每一列 1 的数量 > 0的数量
        // 只需要统计每一列 1的数量即可
        int num = rows;
        for (int j = 1; j < cols; j++) {
            int count = 0;
            for (int i = 0; i < rows; i++) {
                if (A[i][j] == 1) {
                    count++;
                }
            }
            count = Math.max(count, rows - count);
            num = (num << 1) + count;
        }

        return num;
    }

    /**
     * 870. 优势洗牌
     *
     * <p>给定两个大小相等的数组 A 和 B，A 相对于 B 的优势可以用满足 A[i] > B[i] 的索引 i 的数目来描述。
     *
     * <p>返回 A 的任意排列，使其相对于 B 的优势最大化。
     *
     * <p>示例 1：
     *
     * <p>输入：A = [2,7,11,15], B = [1,10,4,11] 输出：[2,11,7,15] 示例 2：
     *
     * <p>输入：A = [12,24,8,32], B = [13,25,32,11] 输出：[24,32,8,12]
     *
     * <p>提示：
     *
     * <p>1 <= A.length = B.length <= 10000 0 <= A[i] <= 10^9 0 <= B[i] <= 10^9
     *
     * @param A
     * @param B
     * @return
     */
    public int[] advantageCount(int[] A, int[] B) {
        int len = A.length;
        int[] result = new int[len];
        Arrays.sort(A);
        int[][] arrs = new int[len][2];
        for (int i = 0; i < len; i++) {
            arrs[i][0] = B[i];
            arrs[i][1] = i;
        }
        Arrays.sort(arrs, Comparator.comparingInt(a -> a[0]));
        int left = 0, right = len - 1;
        for (int i = 0; i < len; i++) {
            if (A[i] <= arrs[left][0]) {
                result[arrs[right--][1]] = A[i]; // 要放到原数组对应的位置上
            } else {
                result[arrs[left++][1]] = A[i]; // 要放到原数组对应的位置上
            }
        }

        return result;
    }

    /**
     * 1620. 网络信号最好的坐标
     *
     * <p>给你一个数组 towers 和一个整数 radius ，数组中包含一些网络信号塔，其中 towers[i] = [xi, yi, qi] 表示第 i 个网络信号塔的坐标是 (xi,
     * yi) 且信号强度参数为 qi 。所有坐标都是在 X-Y 坐标系内的 整数 坐标。两个坐标之间的距离用 欧几里得距离 计算。
     *
     * <p>整数 radius 表示一个塔 能到达 的 最远距离 。如果一个坐标跟塔的距离在 radius 以内，那么该塔的信号可以到达该坐标。在这个范围以外信号会很微弱，所以 radius
     * 以外的距离该塔是 不能到达的 。
     *
     * <p>如果第 i 个塔能到达 (x, y) ，那么该塔在此处的信号为 ⌊qi / (1 + d)⌋ ，其中 d 是塔跟此坐标的距离。一个坐标的 网络信号 是所有 能到达
     * 该坐标的塔的信号强度之和。
     *
     * <p>请你返回 网络信号 最大的整数坐标点。如果有多个坐标网络信号一样大，请你返回字典序最小的一个坐标。
     *
     * <p>注意：
     *
     * <p>坐标 (x1, y1) 字典序比另一个坐标 (x2, y2) 小：要么 x1 < x2 ，要么 x1 == x2 且 y1 < y2 。 ⌊val⌋ 表示小于等于 val
     * 的最大整数（向下取整函数）。
     *
     * <p>示例 1：
     *
     * <p>输入：towers = [[1,2,5],[2,1,7],[3,1,9]], radius = 2 输出：[2,1] 解释： 坐标 (2, 1) 信号强度之和为 13 - 塔
     * (2, 1) 强度参数为 7 ，在该点强度为 ⌊7 / (1 + sqrt(0)⌋ = ⌊7⌋ = 7 - 塔 (1, 2) 强度参数为 5 ，在该点强度为 ⌊5 / (1 +
     * sqrt(2)⌋ = ⌊2.07⌋ = 2 - 塔 (3, 1) 强度参数为 9 ，在该点强度为 ⌊9 / (1 + sqrt(1)⌋ = ⌊4.5⌋ = 4
     * 没有别的坐标有更大的信号强度。 示例 2：
     *
     * <p>输入：towers = [[23,11,21]], radius = 9 输出：[23,11] 示例 3：
     *
     * <p>输入：towers = [[1,2,13],[2,1,7],[0,1,9]], radius = 2 输出：[1,2] 示例 4：
     *
     * <p>输入：towers = [[2,1,9],[0,1,9]], radius = 2 输出：[0,1] 解释：坐标 (0, 1) 和坐标 (2, 1) 都是强度最大的位置，但是
     * (0, 1) 字典序更小。
     *
     * <p>提示：
     *
     * <p>1 <= towers.length <= 50 towers[i].length == 3 0 <= xi, yi, qi <= 50 1 <= radius <= 50
     *
     * @param towers
     * @param radius
     * @return
     */
    public int[] bestCoordinate(int[][] towers, int radius) {
        int maxX = 0, maxY = 0;
        for (int[] tower : towers) {
            maxX = Math.max(maxX, tower[0]);
            maxY = Math.max(maxY, tower[1]);
        }

        Map<Point, Integer> signalMap = new HashMap<>();
        // 对每个半径内能到的点求signal值并累加到sig数组
        for (int[] tower : towers) {
            for (int x = -radius; x <= radius; x++) {
                for (int y = -radius; y <= radius; y++) {
                    double distance = distance(x, y);
                    if (distance > radius) {
                        continue;
                    }
                    int a = tower[0] + x, b = tower[1] + y;
                    if (a < 0 || b < 0) {
                        continue;
                    }
                    Point point = new Point(a, b);
                    int sig = signalMap.getOrDefault(point, 0);
                    signalMap.put(point, sig + signal(tower[2], distance));
                }
            }
        }
        List<Point> list = new ArrayList<>(signalMap.keySet());
        list.sort(
                (a, b) -> {
                    int val1 = signalMap.get(a), val2 = signalMap.get(b);
                    if (val1 == val2) {
                        return a.x == b.x ? a.y - b.y : a.x - b.x;
                    }
                    return val2 - val1;
                });
        Point first = list.get(0);
        int[] result = new int[2];
        if (signalMap.get(first) == 0) {
            return result;
        }
        result[0] = first.x;
        result[1] = first.y;

        return result;
    }

    private double distance(int x, int y) {
        return Math.sqrt(x * x + y * y);
    }

    private int signal(int q, double distance) {
        return (int) (q / (1 + distance));
    }

    @Test
    public void numRescueBoats() {
        int[] people = {3, 5, 3, 4};
        int limit = 5;
        logResult(numRescueBoats(people, limit));
    }
    /**
     * 881. 救生艇
     *
     * <p>第 i 个人的体重为 people[i]，每艘船可以承载的最大重量为 limit。
     *
     * <p>每艘船最多可同时载两人，但条件是这些人的重量之和最多为 limit。
     *
     * <p>返回载到每一个人所需的最小船数。(保证每个人都能被船载)。
     *
     * <p>示例 1：
     *
     * <p>输入：people = [1,2], limit = 3 输出：1 解释：1 艘船载 (1, 2) 示例 2：
     *
     * <p>输入：people = [3,2,2,1], limit = 3 输出：3 解释：3 艘船分别载 (1, 2), (2) 和 (3) 示例 3：
     *
     * <p>输入：people = [3,5,3,4], limit = 5 输出：4 解释：4 艘船分别载 (3), (3), (4), (5) 提示：
     *
     * <p>1 <= people.length <= 50000 1 <= people[i] <= limit <= 30000
     *
     * @param people
     * @param limit
     * @return
     */
    public int numRescueBoats(int[] people, int limit) {
        Arrays.sort(people);
        int left = 0, right = people.length - 1;
        int result = 0;

        while (left <= right) {
            if (people[right] + people[left] <= limit) {
                result++;
                left++;
                right--;
            } else {
                result++;
                right--;
            }
        }

        return result;
    }

    /**
     * 948. 令牌放置
     *
     * <p>你的初始 能量 为 P，初始 分数 为 0，只有一包令牌 tokens 。其中 tokens[i] 是第 i 个令牌的值（下标从 0 开始）。
     *
     * <p>令牌可能的两种使用方法如下：
     *
     * <p>如果你至少有 token[i] 点 能量 ，可以将令牌 i 置为正面朝上，失去 token[i] 点 能量 ，并得到 1 分 。 如果我们至少有 1 分 ，可以将令牌 i
     * 置为反面朝上，获得 token[i] 点 能量 ，并失去 1 分 。 每个令牌 最多 只能使用一次，使用 顺序不限 ，不需 使用所有令牌。
     *
     * <p>在使用任意数量的令牌后，返回我们可以得到的最大 分数 。
     *
     * <p>示例 1：
     *
     * <p>输入：tokens = [100], P = 50 输出：0 解释：无法使用唯一的令牌，因为能量和分数都太少了。 示例 2：
     *
     * <p>输入：tokens = [100,200], P = 150 输出：1 解释：令牌 0 正面朝上，能量变为 50，分数变为 1 。不必使用令牌 1 ，因为你无法使用它来提高分数。
     * 示例 3：
     *
     * <p>输入：tokens = [100,200,300,400], P = 200 输出：2 解释：按下面顺序使用令牌可以得到 2 分： 1. 令牌 0 正面朝上，能量变为 100
     * ，分数变为 1 2. 令牌 3 正面朝下，能量变为 500 ，分数变为 0 3. 令牌 1 正面朝上，能量变为 300 ，分数变为 1 4. 令牌 2 正面朝上，能量变为 0 ，分数变为
     * 2
     *
     * <p>提示：
     *
     * <p>0 <= tokens.length <= 1000 0 <= tokens[i], P < 104
     *
     * @param tokens
     * @param P
     * @return
     */
    public int bagOfTokensScore(int[] tokens, int P) {
        // 先把小token换成分数, 然后把
        Arrays.sort(tokens);
        int left = 0, right = tokens.length - 1;
        int score = 0, max = 0;
        while (left <= right && (P >= tokens[left] || score > 0)) {
            while (left <= right && P >= tokens[left]) {
                P -= tokens[left++];
                score++;
            }
            max = Math.max(max, score);
            if (left <= right && score > 0) {
                P += tokens[right--];
                score--;
            }
        }

        return max;
    }

    /**
     * 1007. 行相等的最少多米诺旋转
     *
     * <p>在一排多米诺骨牌中，A[i] 和 B[i] 分别代表第 i 个多米诺骨牌的上半部分和下半部分。（一个多米诺是两个从 1 到 6 的数字同列平铺形成的 ——
     * 该平铺的每一半上都有一个数字。）
     *
     * <p>我们可以旋转第 i 张多米诺，使得 A[i] 和 B[i] 的值交换。
     *
     * <p>返回能使 A 中所有值或者 B 中所有值都相同的最小旋转次数。
     *
     * <p>如果无法做到，返回 -1.
     *
     * <p>示例 1：
     *
     * <p>输入：A = [2,1,2,4,2,2], B = [5,2,6,2,3,2] 输出：2 解释： 图一表示：在我们旋转之前， A 和 B 给出的多米诺牌。
     * 如果我们旋转第二个和第四个多米诺骨牌，我们可以使上面一行中的每个值都等于 2，如图二所示。 示例 2：
     *
     * <p>输入：A = [3,5,1,2,3], B = [3,6,3,3,4] 输出：-1 解释： 在这种情况下，不可能旋转多米诺牌使一行的值相等。
     *
     * <p>提示：
     *
     * <p>1 <= A[i], B[i] <= 6 2 <= A.length == B.length <= 20000
     *
     * @param A
     * @param B
     * @return
     */
    public int minDominoRotations(int[] A, int[] B) {
        int len = A.length;
        int[] nums = new int[7];
        for (int i = 0; i < len; i++) {
            nums[A[i]]++;
            nums[B[i]]++;
        }
        int num = -1;
        for (int i = 1; i <= 6; i++) {
            if (nums[i] >= len) {
                num = i;
                break;
            }
        }
        if (num == -1) {
            return -1;
        }
        int a = 0, b = 0;
        for (int i = 0; i < len; i++) {
            if (A[i] != num && B[i] != num) {
                return -1;
            } else if (A[i] != num) {
                a++;
            } else if (B[i] != num) {
                b++;
            }
        }

        return Math.min(a, b);
    }

    /**
     * 5606. 具有给定数值的最小字符串
     *
     * <p>小写字符 的 数值 是它在字母表中的位置（从 1 开始），因此 a 的数值为 1 ，b 的数值为 2 ，c 的数值为 3 ，以此类推。
     *
     * <p>字符串由若干小写字符组成，字符串的数值 为各字符的数值之和。例如，字符串 "abe" 的数值等于 1 + 2 + 5 = 8 。
     *
     * <p>给你两个整数 n 和 k 。返回 长度 等于 n 且 数值 等于 k 的 字典序最小 的字符串。
     *
     * <p>注意，如果字符串 x 在字典排序中位于 y 之前，就认为 x 字典序比 y 小，有以下两种情况：
     *
     * <p>x 是 y 的一个前缀； 如果 i 是 x[i] != y[i] 的第一个位置，且 x[i] 在字母表中的位置比 y[i] 靠前。
     *
     * <p>示例 1：
     *
     * <p>输入：n = 3, k = 27 输出："aay" 解释：字符串的数值为 1 + 1 + 25 = 27，它是数值满足要求且长度等于 3 字典序最小的字符串。 示例 2：
     *
     * <p>输入：n = 5, k = 73 输出："aaszz"
     *
     * <p>提示：
     *
     * <p>1 <= n <= 105 n <= k <= 26 * n
     *
     * @param n
     * @param k
     * @return
     */
    public String getSmallestString(int n, int k) {
        // 长度 等于 n 且 数值 等于 k
        int[] nums = new int[n];
        Arrays.fill(nums, 1);
        nums[n - 1] += k - n;
        for (int i = n - 1; i > 0; i--) {
            if (nums[i] <= 26) {
                break;
            }
            int num = nums[i] - 26;
            nums[i] = 26;
            nums[i - 1] += num;
        }
        char[] chars = new char[n];
        for (int i = 0; i < n; i++) {
            chars[i] = (char) ('a' + nums[i] - 1);
        }

        return new String(chars);
    }

    @Test
    public void getSmallestString() {
        int n = 5, k = 73;
        logResult(getSmallestString(n, k));
    }

    /**
     * 135. 分发糖果
     *
     * <p>老师想给孩子们分发糖果，有 N 个孩子站成了一条直线，老师会根据每个孩子的表现，预先给他们评分。
     *
     * <p>你需要按照以下要求，帮助老师给这些孩子分发糖果：
     *
     * <p>每个孩子至少分配到 1 个糖果。 相邻的孩子中，评分高的孩子必须获得更多的糖果。 那么这样下来，老师至少需要准备多少颗糖果呢？
     *
     * <p>示例 1:
     *
     * <p>输入: [1,0,2] 输出: 5 解释: 你可以分别给这三个孩子分发 2、1、2 颗糖果。 示例 2:
     *
     * <p>输入: [1,2,2] 输出: 4 解释: 你可以分别给这三个孩子分发 1、2、1 颗糖果。 第三个孩子只得到 1 颗糖果，这已满足上述两个条件。
     *
     * @param ratings
     * @return
     */
    public int candy(int[] ratings) {
        // 找谷底
        int len = ratings.length;
        int[] leftNums = new int[len], rightNums = new int[len];
        Arrays.fill(leftNums, 1);
        Arrays.fill(rightNums, 1);
        for (int i = 1; i < len; i++) {
            if (ratings[i] > ratings[i - 1]) {
                leftNums[i] = leftNums[i - 1] + 1;
            }
        }
        for (int i = len - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1]) {
                rightNums[i] = rightNums[i + 1] + 1;
            }
        }
        int sum = 0;
        for (int i = 0; i < len; i++) {
            sum += Math.max(leftNums[i], rightNums[i]);
        }

        return sum;
    }

    @Test
    public void candy() {
        int[] ratings = {0};
        logResult(candy(ratings));
    }

    /**
     * 321. 拼接最大数
     *
     * <p>给定长度分别为 m 和 n 的两个数组，其元素由 0-9 构成，表示两个自然数各位上的数字。现在从这两个数组中选出 k (k <= m + n)
     * 个数字拼接成一个新的数，要求从同一个数组中取出的数字保持其在原数组中的相对顺序。
     *
     * <p>求满足该条件的最大数。结果返回一个表示该最大数的长度为 k 的数组。
     *
     * <p>说明: 请尽可能地优化你算法的时间和空间复杂度。
     *
     * <p>示例 1:
     *
     * <p>输入: nums1 = [3, 4, 6, 5] nums2 = [9, 1, 2, 5, 8, 3] k = 5 输出: [9, 8, 6, 5, 3] 示例 2:
     *
     * <p>输入: nums1 = [6, 7] nums2 = [6, 0, 4] k = 5 输出: [6, 7, 6, 0, 4] 示例 3:
     *
     * <p>输入: nums1 = [3, 9] nums2 = [8, 9] k = 3 输出: [9, 8, 9]
     *
     * @param nums1
     * @param nums2
     * @param k
     * @return
     */
    public int[] maxNumber(int[] nums1, int[] nums2, int k) {
        int[] result = new int[k];
        int len1 = nums1.length, len2 = nums2.length;

        for (int i = Math.max(0, k - len2); i <= Math.min(k, len1); i++) {
            int[] list1 = getMaxSubsequence(nums1, i), list2 = getMaxSubsequence(nums2, k - i);
            int[] nums = mergeMaxNumber(list1, list2);
            if (compare(nums, 0, result, 0)) {
                result = nums;
            }
        }
        return result;
    }

    @Test
    public void maxNumber() {
        int[] nums1 = {2, 5, 6, 4, 4, 0}, nums2 = {7, 3, 8, 0, 6, 5, 7, 6, 2};
        int k = 15;
        log.debug("nums:{}", maxNumber(nums1, nums2, k));
    }

    public boolean compare(int[] nums1, int i, int[] nums2, int j) {
        if (j >= nums2.length) {
            return true;
        }
        if (i >= nums1.length) {
            return false;
        }
        if (nums1[i] > nums2[j]) {
            return true;
        }
        if (nums1[i] < nums2[j]) {
            return false;
        }

        return compare(nums1, i + 1, nums2, j + 1);
    }

    /**
     * 合并两个数组
     *
     * @param nums1
     * @param nums2
     * @return
     */
    private int[] mergeMaxNumber(int[] nums1, int[] nums2) {
        if (nums1.length == 0) {
            return nums2;
        }
        if (nums2.length == 0) {
            return nums1;
        }
        int len1 = nums1.length, len2 = nums2.length;
        int[] result = new int[len1 + len2];
        int index = 0, i = 0, j = 0;
        while (i < len1 || j < len2) {
            if (compare(nums1, i, nums2, j)) {
                result[index++] = nums1[i++];
            } else {
                result[index++] = nums2[j++];
            }
        }
        return result;
    }

    /**
     * 获取 nums 长度为k的最大子序列
     *
     * @param nums
     * @param k
     * @return
     */
    private int[] getMaxSubsequence(int[] nums, int k) {
        if (k == 0) {
            return new int[0];
        }
        int[] result = new int[k];
        int index = 0, rem = nums.length - k;
        for (int num : nums) {
            while (index > 0 && rem > 0 && result[index - 1] < num) {
                index--;
                rem--;
            }
            if (index < k) {
                result[index++] = num;
            } else {
                rem--;
            }
        }

        return result;
    }

    /**
     * 330. 按要求补齐数组
     *
     * <p>给定一个已排序的正整数数组 nums，和一个正整数 n 。从 [1, n] 区间内选取任意个数字补充到 nums 中，使得 [1, n] 区间内的任何数字都可以用 nums
     * 中某几个数字的和来表示。请输出满足上述要求的最少需要补充的数字个数。
     *
     * <p>示例 1:
     *
     * <p>输入: nums = [1,3], n = 6 输出: 1 解释: 根据 nums 里现有的组合 [1], [3], [1,3]，可以得出 1, 3, 4。 现在如果我们将 2
     * 添加到 nums 中， 组合变为: [1], [2], [3], [1,3], [2,3], [1,2,3]。 其和可以表示数字 1, 2, 3, 4, 5, 6，能够覆盖 [1, 6]
     * 区间里所有的数。 所以我们最少需要添加一个数字。 示例 2:
     *
     * <p>输入: nums = [1,5,10], n = 20 输出: 2 解释: 我们需要添加 [2, 4]。 示例 3:
     *
     * <p>输入: nums = [1,2,2], n = 5 输出: 0
     *
     * @param nums
     * @param n
     * @return
     */
    public int minPatches(int[] nums, int n) {
        int count = 0, i = 0;
        long miss = 1;
        while (miss <= n) {
            if (i < nums.length && nums[i] <= miss) {
                miss += nums[i++];
            } else {
                miss += miss;
                count++;
            }
        }

        return count;
    }

    /**
     * 502. IPO
     *
     * <p>假设 力扣（LeetCode）即将开始其 IPO。为了以更高的价格将股票卖给风险投资公司，力扣 希望在 IPO 之前开展一些项目以增加其资本。 由于资源有限，它只能在 IPO
     * 之前完成最多 k 个不同的项目。帮助 力扣 设计完成最多 k 个不同项目后得到最大总资本的方式。
     *
     * <p>给定若干个项目。对于每个项目 i，它都有一个纯利润 Pi，并且需要最小的资本 Ci 来启动相应的项目。最初，你有 W
     * 资本。当你完成一个项目时，你将获得纯利润，且利润将被添加到你的总资本中。
     *
     * <p>总而言之，从给定项目中选择最多 k 个不同项目的列表，以最大化最终资本，并输出最终可获得的最多资本。
     *
     * <p>示例 1:
     *
     * <p>输入: k=2, W=0, Profits=[1,2,3], Capital=[0,1,1].
     *
     * <p>输出: 4
     *
     * <p>解释: 由于你的初始资本为 0，你尽可以从 0 号项目开始。 在完成后，你将获得 1 的利润，你的总资本将变为 1。 此时你可以选择开始 1 号或 2 号项目。
     * 由于你最多可以选择两个项目，所以你需要完成 2 号项目以获得最大的资本。 因此，输出最后最大化的资本，为 0 + 1 + 3 = 4。
     *
     * <p>注意:
     *
     * <p>假设所有输入数字都是非负整数。 表示利润和资本的数组的长度不超过 50000。 答案保证在 32 位有符号整数范围内。
     *
     * @param k
     * @param W
     * @param Profits
     * @param Capital
     * @return
     */
    public int findMaximizedCapital(int k, int W, int[] Profits, int[] Capital) {
        int len = Profits.length;
        List<Ipo> ipoList = new ArrayList<>();
        for (int i = 0; i < len; i++) {
            ipoList.add(new Ipo(Profits[i], Capital[i]));
        }
        // 最大堆, 利润最大
        PriorityQueue<Ipo> heap = new PriorityQueue<>((a, b) -> b.profit - a.profit);

        int idx = 0;
        ipoList.sort(Comparator.comparingInt(a -> a.capital));
        int size = ipoList.size();
        while (idx < size && k > 0) {
            while (idx < size && ipoList.get(idx).capital <= W) {
                heap.add(ipoList.get(idx));
                idx++;
            }
            if (heap.isEmpty()) {
                return W;
            }
            Ipo ipo = heap.poll();
            k--;
            W += ipo.profit;
        }
        while (k > 0 && !heap.isEmpty()) {
            Ipo ipo = heap.poll();
            W += ipo.profit;
            k--;
        }

        return W;
    }

    static class Ipo {
        int profit, capital;

        public Ipo(int profit, int capital) {
            this.profit = profit;
            this.capital = capital;
        }
    }

    @Test
    public void findMaximizedCapital() {
        int k = 1, W = 2;
        int[] Profits = {1, 2, 3}, Capital = {1, 1, 2};
        logResult(findMaximizedCapital(k, W, Profits, Capital));
    }

    /**
     * 517. 超级洗衣机
     *
     * <p>假设有 n 台超级洗衣机放在同一排上。开始的时候，每台洗衣机内可能有一定量的衣服，也可能是空的。
     *
     * <p>在每一步操作中，你可以选择任意 m （1 ≤ m ≤ n） 台洗衣机，与此同时将每台洗衣机的一件衣服送到相邻的一台洗衣机。
     *
     * <p>给定一个非负整数数组代表从左至右每台洗衣机中的衣物数量，请给出能让所有洗衣机中剩下的衣物的数量相等的最少的操作步数。如果不能使每台洗衣机中衣物的数量相等，则返回 -1。
     *
     * <p>示例 1：
     *
     * <p>输入: [1,0,5]
     *
     * <p>输出: 3
     *
     * <p>解释: 第一步: 1 0 <-- 5 => 1 1 4 第二步: 1 <-- 1 <-- 4 => 2 1 3 第三步: 2 1 <-- 3 => 2 2 2 示例 2：
     *
     * <p>输入: [0,3,0]
     *
     * <p>输出: 2
     *
     * <p>解释: 第一步: 0 <-- 3 0 => 1 2 0 第二步: 1 2 --> 0 => 1 1 1 示例 3:
     *
     * <p>输入: [0,2,0]
     *
     * <p>输出: -1
     *
     * <p>解释: 不可能让所有三个洗衣机同时剩下相同数量的衣物。
     *
     * <p>提示：
     *
     * <p>n 的范围是 [1, 10000]。 在每台超级洗衣机中，衣物数量的范围是 [0, 1e5]。
     *
     * @param machines
     * @return
     */
    public int findMinMoves(int[] machines) {
        int n = machines.length;
        int sum = Arrays.stream(machines).sum();
        if (sum % n != 0) {
            return -1;
        }
        int avg = sum / n;
        int result = 0;
        /*int maxFlowCnt = 0;
        for (int count : machines) {
            int num = count - avg;
            maxFlowCnt += num;
            result = Math.max(result, Math.max(Math.abs(maxFlowCnt), num));
        }

        return result;*/

        for (int i = 0; i < n; i++) {
            machines[i] -= avg;
        }
        int currSum = 0, maxSum = 0;
        for (int count : machines) {
            currSum += count;
            maxSum = Math.max(maxSum, Math.abs(currSum));
            result = Math.max(result, Math.max(maxSum, count));
        }
        return result;
    }

    /**
     * 630. 课程表 III
     *
     * <p>这里有 n 门不同的在线课程，他们按从 1 到 n 编号。每一门课程有一定的持续上课时间（课程时间）t 以及关闭时间第 d 天。一门课要持续学习 t 天直到第 d
     * 天时要完成，你将会从第 1 天开始。
     *
     * <p>给出 n 个在线课程用 (t, d) 对表示。你的任务是找出最多可以修几门课。
     *
     * <p>示例：
     *
     * <p>输入: [[100, 200], [200, 1300], [1000, 1250], [2000, 3200]] 输出: 3 解释: 这里一共有 4 门课程, 但是你最多可以修
     * 3 门: 首先, 修第一门课时, 它要耗费 100 天，你会在第 100 天完成, 在第 101 天准备下门课。 第二, 修第三门课时, 它会耗费 1000 天，所以你将在第 1100
     * 天的时候完成它, 以及在第 1101 天开始准备下门课程。 第三, 修第二门课时, 它会耗时 200 天，所以你将会在第 1300 天时完成它。 第四门课现在不能修，因为你将会在第
     * 3300 天完成它，这已经超出了关闭日期。
     *
     * <p>提示:
     *
     * <p>整数 1 <= d, t, n <= 10,000 。 你不能同时修两门课程。
     *
     * @param courses
     * @return
     */
    public int scheduleCourse(int[][] courses) {
        // 排序
        Arrays.sort(courses, Comparator.comparingInt(a -> a[1]));
        int time = 0;
        PriorityQueue<Integer> queue = new PriorityQueue<>((a, b) -> b - a);
        for (int[] course : courses) {
            if (time + course[0] <= course[1]) {
                queue.offer(course[0]);
                time += course[0];
            } else if (!queue.isEmpty() && queue.peek() > course[0]) {
                time += course[0] - queue.poll();
                queue.offer(course[0]);
            }
        }

        return queue.size();
    }

    /**
     * 1686. 石子游戏 VI
     *
     * <p>Alice 和 Bob 轮流玩一个游戏，Alice 先手。
     *
     * <p>一堆石子里总共有 n 个石子，轮到某个玩家时，他可以 移出 一个石子并得到这个石子的价值。Alice 和 Bob 对石子价值有 不一样的的评判标准 。
     *
     * <p>给你两个长度为 n 的整数数组 aliceValues 和 bobValues 。aliceValues[i] 和 bobValues[i] 分别表示 Alice 和 Bob
     * 认为第 i 个石子的价值。
     *
     * <p>所有石子都被取完后，得分较高的人为胜者。如果两个玩家得分相同，那么为平局。两位玩家都会采用 最优策略 进行游戏。
     *
     * <p>请你推断游戏的结果，用如下的方式表示：
     *
     * <p>如果 Alice 赢，返回 1 。 如果 Bob 赢，返回 -1 。 如果游戏平局，返回 0 。
     *
     * <p>示例 1：
     *
     * <p>输入：aliceValues = [1,3], bobValues = [2,1] 输出：1 解释： 如果 Alice 拿石子 1 （下标从 0开始），那么 Alice 可以得到
     * 3 分。 Bob 只能选择石子 0 ，得到 2 分。 Alice 获胜。 示例 2：
     *
     * <p>输入：aliceValues = [1,2], bobValues = [3,1] 输出：0 解释： Alice 拿石子 0 ， Bob 拿石子 1 ，他们得分都为 1 分。
     * 打平。 示例 3：
     *
     * <p>输入：aliceValues = [2,4,3], bobValues = [1,6,7] 输出：-1 解释： 不管 Alice 怎么操作，Bob 都可以得到比 Alice
     * 更高的得分。 比方说，Alice 拿石子 1 ，Bob 拿石子 2 ， Alice 拿石子 0 ，Alice 会得到 6 分而 Bob 得分为 7 分。 Bob 会获胜。
     *
     * <p>提示：
     *
     * <p>n == aliceValues.length == bobValues.length 1 <= n <= 105 1 <= aliceValues[i],
     * bobValues[i] <= 100
     *
     * @param aliceValues
     * @param bobValues
     * @return
     */
    public int stoneGameVI(int[] aliceValues, int[] bobValues) {
        // 假设只有两个石头,对于 a， b 的价值分别是 a1, a2, b1, b2
        //
        // 第一种方案是A取第一个，B取第二个，A与B的价值差是 c1 = a1 - b2
        // 第二种方案是A取第二个，B取第一个，A与B的价值差是 c2 = a2 - b1
        // c1 - c2 = a1 - b2 - a2 + b1 => a1+ b1 - (a2 + b2);
        int len = aliceValues.length;
        // 按 a1+ b1 排序
        int[][] values = new int[len][2];
        for (int i = 0; i < len; i++) {
            values[i][0] = aliceValues[i];
            values[i][1] = bobValues[i];
        }
        Arrays.sort(values, (a, b) -> b[0] + b[1] - a[0] - a[1]);
        int a = 0, b = 0;
        for (int i = 0; i < len; i++) {
            if ((i & 1) == 0) {
                a += values[i][0];
            } else {
                b += values[i][1];
            }
        }
        if (a == b) {
            return 0;
        }

        return a > b ? 1 : -1;
    }

    /**
     * 5631. 跳跃游戏 VI
     *
     * <p>给你一个下标从 0 开始的整数数组 nums 和一个整数 k 。
     *
     * <p>一开始你在下标 0 处。每一步，你最多可以往前跳 k 步，但你不能跳出数组的边界。也就是说，你可以从下标 i 跳到 [i + 1， min(n - 1, i + k)] 包含
     * 两个端点的任意位置。
     *
     * <p>你的目标是到达数组最后一个位置（下标为 n - 1 ），你的 得分 为经过的所有数字之和。
     *
     * <p>请你返回你能得到的 最大得分 。
     *
     * <p>示例 1：
     *
     * <p>输入：nums = [1,-1,-2,4,-7,3], k = 2 输出：7 解释：你可以选择子序列 [1,-1,4,3] （上面加粗的数字），和为 7 。 示例 2：
     *
     * <p>输入：nums = [10,-5,-2,4,0,3], k = 3 输出：17 解释：你可以选择子序列 [10,4,3] （上面加粗数字），和为 17 。 示例 3：
     *
     * <p>输入：nums = [1,-5,-20,4,-1,3,-6,-3], k = 2 输出：0
     *
     * <p>提示：
     *
     * <p>1 <= nums.length, k <= 105 -104 <= nums[i] <= 104
     *
     * @param nums
     * @param k
     * @return
     */
    public int maxResult(int[] nums, int k) {
        int len = nums.length;
        if (len == 1) {
            return nums[0];
        }
        int result = 0;
        // 如果后面k个数字里面碰到第一个大于0的数，就直接跳到那个数
        // 如果里面都是小于0的数，就找这里面最大的一个跳

        for (int i = 0; i < len; ) {
            result += nums[i];
            int end = Math.min(i + k, len - 1);
            int next = i + 1;
            int max = Integer.MIN_VALUE;
            for (int j = i + 1; j <= end; j++) {
                if (j == len - 1) {
                    return result + nums[len - 1];
                }
                if (nums[j] > 0) {
                    next = j;
                    break;
                }
                if (nums[j] > max) {
                    max = nums[j];
                    next = j;
                }
            }

            i = next;
        }

        return result + nums[len - 1];
    }

    @Test
    public void maxResult() {
        int[] nums = {10, -5, -2, 4, 0, 3};
        int k = 3;
        logResult(maxResult(nums, k));
    }

    /**
     * 757. 设置交集大小至少为2
     *
     * <p>一个整数区间 [a, b] ( a < b ) 代表着从 a 到 b 的所有连续整数，包括 a 和 b。
     *
     * <p>给你一组整数区间intervals，请找到一个最小的集合 S，使得 S 里的元素与区间intervals中的每一个整数区间都至少有2个元素相交。
     *
     * <p>输出这个最小集合S的大小。
     *
     * <p>示例 1:
     *
     * <p>输入: intervals = [[1, 3], [1, 4], [2, 5], [3, 5]] 输出: 3 解释: 考虑集合 S = {2, 3, 4}.
     * S与intervals中的四个区间都有至少2个相交的元素。 且这是S最小的情况，故我们输出3。 示例 2:
     *
     * <p>输入: intervals = [[1, 2], [2, 3], [2, 4], [4, 5]] 输出: 5 解释: 最小的集合S = {1, 2, 3, 4, 5}. 注意:
     *
     * <p>intervals 的长度范围为[1, 3000]。 intervals[i] 长度为 2，分别代表左、右边界。 intervals[i][j] 的值是 [0,
     * 10^8]范围内的整数。
     *
     * @param intervals
     * @return
     */
    public int intersectionSizeTwo(int[][] intervals) {
        int result = 0;
        // 起点升序 终点降序
        Arrays.sort(intervals, (a, b) -> a[0] == b[0] ? a[1] - b[1] : b[0] - a[0]);
        logResult(intervals);
        // 假设前一个区间为[a1,b1]，与集合的交集是min1,min2，保证min1 < min2,接下来要处理的区间是[a2,b2]。 有以下几种情况：
        // 1、a2 <= min1 < min2 <= b2,min1、min2都在[a2,b2]内部，不需要更新。
        // 2、a2 <= min1 <= b2 < min2,min1在[a2,b2]内部，更新min2。
        // 3、min1 < a2 <= min2 <= b2,,min2在[a2,b2]内部，更新min1。
        // 4、min1、min2不在[a2,b2]内部，同时更新min1、min2。
        // 关键点：更新的时候需要保证min1 < min2
        int min1 = Integer.MAX_VALUE, min2 = Integer.MAX_VALUE;
        for (int[] interval : intervals) {

            if (interval[0] <= min1
                    && min1 <= interval[1]
                    && interval[0] <= min2
                    && min2 <= interval[1]) {
                continue;
            } else if (interval[0] <= min1 && min1 <= interval[1]) {
                result++;
                if (min1 == interval[0]) {
                    min2 = interval[0] + 1;
                } else {
                    min2 = min1;
                    min1 = interval[0];
                }
            } else if (interval[0] <= min2 && min2 <= interval[1]) {
                result++;
                if (min2 == interval[0]) {
                    min1 = interval[0];
                    min2 = interval[0] + 1;
                } else {
                    min1 = interval[0];
                }
            } else {
                result += 2;
                min1 = interval[0];
                min2 = interval[0] + 1;
            }
            log.debug("min1 {} min2 {}", min1, min2);
        }

        return result;
    }

    @Test
    public void intersectionSizeTwo() {
        int[][] intervals = {{1, 3}, {1, 4}, {2, 5}, {3, 5}};
        logResult(intersectionSizeTwo(intervals));
    }

    /**
     * 5641. 卡车上的最大单元数
     *
     * <p>请你将一些箱子装在 一辆卡车 上。给你一个二维数组 boxTypes ，其中 boxTypes[i] = [numberOfBoxesi,
     * numberOfUnitsPerBoxi] ：
     *
     * <p>numberOfBoxesi 是类型 i 的箱子的数量。 numberOfUnitsPerBoxi 是类型 i 每个箱子可以装载的单元数量。 整数 truckSize
     * 表示卡车上可以装载 箱子 的 最大数量 。只要箱子数量不超过 truckSize ，你就可以选择任意箱子装到卡车上。
     *
     * <p>返回卡车可以装载 单元 的 最大 总数。
     *
     * <p>示例 1：
     *
     * <p>输入：boxTypes = [[1,3],[2,2],[3,1]], truckSize = 4 输出：8 解释：箱子的情况如下： - 1 个第一类的箱子，里面含 3 个单元。 -
     * 2 个第二类的箱子，每个里面含 2 个单元。 - 3 个第三类的箱子，每个里面含 1 个单元。 可以选择第一类和第二类的所有箱子，以及第三类的一个箱子。 单元总数 = (1 * 3) +
     * (2 * 2) + (1 * 1) = 8 示例 2：
     *
     * <p>输入：boxTypes = [[5,10],[2,5],[4,7],[3,9]], truckSize = 10 输出：91
     *
     * <p>提示：
     *
     * <p>1 <= boxTypes.length <= 1000 1 <= numberOfBoxesi, numberOfUnitsPerBoxi <= 1000 1 <=
     * truckSize <= 106
     *
     * @param boxTypes
     * @param truckSize
     * @return
     */
    public int maximumUnits(int[][] boxTypes, int truckSize) {
        int result = 0;
        Arrays.sort(boxTypes, (a, b) -> b[1] - a[1]);
        int idx = 0, len = boxTypes.length;
        logResult(boxTypes);
        while (idx < len && truckSize > 0) {
            int[] box = boxTypes[idx];
            result += Math.min(box[0], truckSize) * box[1];
            truckSize -= box[0];
            idx++;
        }
        return result;
    }

    @Test
    public void maximumUnits() {
        int[][] boxTypes = {{1, 3}, {2, 2}, {3, 1}};
        int truckSize = 4;
        logResult(maximumUnits(boxTypes, truckSize));
    }

    /**
     * 5634. 删除子字符串的最大得分
     *
     * <p>给你一个字符串 s 和两个整数 x 和 y 。你可以执行下面两种操作任意次。
     *
     * <p>删除子字符串 "ab" 并得到 x 分。 比方说，从 "cabxbae" 删除 ab ，得到 "cxbae" 。 删除子字符串"ba" 并得到 y 分。 比方说，从
     * "cabxbae" 删除 ba ，得到 "cabxe" 。 请返回对 s 字符串执行上面操作若干次能得到的最大得分。
     *
     * <p>示例 1：
     *
     * <p>输入：s = "cdbcbbaaabab", x = 4, y = 5 输出：19 解释： - 删除 "cdbcbbaaabab" 中加粗的 "ba" ，得到 s =
     * "cdbcbbaaab" ，加 5 分。 - 删除 "cdbcbbaaab" 中加粗的 "ab" ，得到 s = "cdbcbbaa" ，加 4 分。 - 删除 "cdbcbbaa"
     * 中加粗的 "ba" ，得到 s = "cdbcba" ，加 5 分。 - 删除 "cdbcba" 中加粗的 "ba" ，得到 s = "cdbc" ，加 5 分。 总得分为 5 + 4
     * + 5 + 5 = 19 。 示例 2：
     *
     * <p>输入：s = "aabbaaxybbaabb", x = 5, y = 4 输出：20
     *
     * <p>提示：
     *
     * <p>1 <= s.length <= 105 1 <= x, y <= 104 s 只包含小写英文字母。
     *
     * @param s
     * @param x
     * @param y
     * @return
     */
    public int maximumGain(String s, int x, int y) {
        int result = 0, len = s.length();
        char[] chars = s.toCharArray();
        // 假设 "ab" 的得分总是不低于 "ba" ；否则，我们将字符串中的字符 a  换成 b ，b  换成 a
        if (x < y) {
            int tmp = x;
            x = y;
            y = tmp;
            for (int i = 0; i < len; i++) {
                char c = chars[i];
                if (c == 'a') {
                    chars[i] = 'b';
                } else if (c == 'b') {
                    chars[i] = 'a';
                }
            }
        }
        int i = 0;
        while (i < len) {
            while (i < len && chars[i] != 'a' && chars[i] != 'b') {
                i++;
            }

            int ca = 0, cb = 0;
            while (i < len && (chars[i] == 'a' || chars[i] == 'b')) {
                if (chars[i] == 'a') {
                    ca++;
                } else {
                    if (ca > 0) {
                        ca--;
                        result += x;
                    } else {
                        cb++;
                    }
                }
                i++;
            }

            result += Math.min(ca, cb) * y;
        }

        return result;
    }
}
