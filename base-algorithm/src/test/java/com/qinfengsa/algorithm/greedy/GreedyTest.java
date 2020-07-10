package com.qinfengsa.algorithm.greedy;

import static com.qinfengsa.algorithm.util.LogUtils.logResult;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
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
        int[][] intervals = {
            {-100, -87},
            {-99, -44},
            {-98, -19},
            {-97, -33},
            {-96, -60},
            {-95, -17},
            {-94, -44},
            {-93, -9},
            {-92, -63},
            {-91, -76},
            {-90, -44},
            {-89, -18},
            {-88, 10},
            {-87, -39},
            {-86, 7},
            {-85, -76},
            {-84, -51},
            {-83, -48},
            {-82, -36},
            {-81, -63},
            {-80, -71},
            {-79, -4},
            {-78, -63},
            {-77, -14},
            {-76, -10},
            {-75, -36},
            {-74, 31},
            {-73, 11},
            {-72, -50},
            {-71, -30},
            {-70, 33},
            {-69, -37},
            {-68, -50},
            {-67, 6},
            {-66, -50},
            {-65, -26},
            {-64, 21},
            {-63, -8},
            {-62, 23},
            {-61, -34},
            {-60, 13},
            {-59, 19},
            {-58, 41},
            {-57, -15},
            {-56, 35},
            {-55, -4},
            {-54, -20},
            {-53, 44},
            {-52, 48},
            {-51, 12},
            {-50, -43},
            {-49, 10},
            {-48, -34},
            {-47, 3},
            {-46, 28},
            {-45, 51},
            {-44, -14},
            {-43, 59},
            {-42, -6},
            {-41, -32},
            {-40, -12},
            {-39, 33},
            {-38, 17},
            {-37, -7},
            {-36, -29},
            {-35, 24},
            {-34, 49},
            {-33, -19},
            {-32, 2},
            {-31, 8},
            {-30, 74},
            {-29, 58},
            {-28, 13},
            {-27, -8},
            {-26, 45},
            {-25, -5},
            {-24, 45},
            {-23, 19},
            {-22, 9},
            {-21, 54},
            {-20, 1},
            {-19, 81},
            {-18, 17},
            {-17, -10},
            {-16, 7},
            {-15, 86},
            {-14, -3},
            {-13, -3},
            {-12, 45},
            {-11, 93},
            {-10, 84},
            {-9, 20},
            {-8, 3},
            {-7, 81},
            {-6, 52},
            {-5, 67},
            {-4, 18},
            {-3, 40},
            {-2, 42},
            {-1, 49},
            {0, 7},
            {1, 104},
            {2, 79},
            {3, 37},
            {4, 47},
            {5, 69},
            {6, 89},
            {7, 110},
            {8, 108},
            {9, 19},
            {10, 25},
            {11, 48},
            {12, 63},
            {13, 94},
            {14, 55},
            {15, 119},
            {16, 64},
            {17, 122},
            {18, 92},
            {19, 37},
            {20, 86},
            {21, 84},
            {22, 122},
            {23, 37},
            {24, 125},
            {25, 99},
            {26, 45},
            {27, 63},
            {28, 40},
            {29, 97},
            {30, 78},
            {31, 102},
            {32, 120},
            {33, 91},
            {34, 107},
            {35, 62},
            {36, 137},
            {37, 55},
            {38, 115},
            {39, 46},
            {40, 136},
            {41, 78},
            {42, 86},
            {43, 106},
            {44, 66},
            {45, 141},
            {46, 92},
            {47, 132},
            {48, 89},
            {49, 61},
            {50, 128},
            {51, 155},
            {52, 153},
            {53, 78},
            {54, 114},
            {55, 84},
            {56, 151},
            {57, 123},
            {58, 69},
            {59, 91},
            {60, 89},
            {61, 73},
            {62, 81},
            {63, 139},
            {64, 108},
            {65, 165},
            {66, 92},
            {67, 117},
            {68, 140},
            {69, 109},
            {70, 102},
            {71, 171},
            {72, 141},
            {73, 117},
            {74, 124},
            {75, 171},
            {76, 132},
            {77, 142},
            {78, 107},
            {79, 132},
            {80, 171},
            {81, 104},
            {82, 160},
            {83, 128},
            {84, 137},
            {85, 176},
            {86, 188},
            {87, 178},
            {88, 117},
            {89, 115},
            {90, 140},
            {91, 165},
            {92, 133},
            {93, 114},
            {94, 125},
            {95, 135},
            {96, 144},
            {97, 114},
            {98, 183},
            {99, 157}
        };
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
}
