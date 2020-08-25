package com.qinfengsa.algorithm.greedy;

import static com.qinfengsa.algorithm.util.LogUtils.logResult;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * 贪心算法 设计贪心算法的步骤 　　1.将优化问题转换成这样一个问题，即先做出选择，再解决剩下的一个子问题。 　　2.证明原问题总是有一个最优解是贪心选择的得到的，从而说明贪心选择的安全。
 * 　　3.说明在做出贪心选择后，剩下的子问题具有这样一个性质。即如果将子问题的最优解和我们所做的贪心选择联合起来，可以得到一个更加负责的动态规划解。
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
}
