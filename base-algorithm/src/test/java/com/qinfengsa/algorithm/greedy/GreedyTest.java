package com.qinfengsa.algorithm.greedy;


import jdk.internal.org.objectweb.asm.Handle;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.qinfengsa.algorithm.util.LogUtils.logResult;

/**
 * 贪心算法
 * 设计贪心算法的步骤
 * 　　1.将优化问题转换成这样一个问题，即先做出选择，再解决剩下的一个子问题。
 * 　　2.证明原问题总是有一个最优解是贪心选择的得到的，从而说明贪心选择的安全。
 * 　　3.说明在做出贪心选择后，剩下的子问题具有这样一个性质。即如果将子问题的最优解和我们所做的贪心选择联合起来，可以得到一个更加负责的动态规划解。
 * @author: qinfengsa
 * @date: 2019/8/10 23:17
 */
@Slf4j
public class GreedyTest {


    @Test
    public void greedyKnapsack() {
        int max = 150;
        String[] items = {"A","B","C","D","E","F","G"};
        int[] weights = {35,30, 6,50,40,10,25};
        int[] costs = {10,40,30,50,35,40,30};
        greedyKnapsack(max,items,weights,costs);
    }



    private void greedyKnapsackStrategy() {

    }

    /**
     * 0-1背包问题
     * 有一个背包，背包容量是M=150kg。有7个物品，物品不可以分割成任意大小。要求尽可能让装入背包中的物品总价值最大，但不能超过总容量。
     * 物品 A B C D E F G
     * 重量 35kg 30kg 6kg 50kg 40kg 10kg 25kg
     * 价值 10$ 40$ 30$ 50$ 35$ 40$ 30$
     */
    private void greedyKnapsack(int max,String[] items,int[] weights,int[] costs) {

        int size = items.length;
        double[] costPreWeight = new double[size];
        for (int i = 0; i < size; i++) {
            double price = (double) costs[i]/(double) weights[i];
            costPreWeight[i] = price;
        }

    }


    @Test
    public void fullJustify() {
        String[] words = {"What","must","be","acknowledgment","shall","be"};
        int maxWidth = 16;
        List<String> result = fullJustify(words, maxWidth);
        for (String word : result) {
            log.debug("result : {}/end",word);
        }

    }


    /**
     * 文本左右对齐
     * 给定一个单词数组和一个长度 maxWidth，重新排版单词，使其成为每行恰好有 maxWidth 个字符，且左右两端对齐的文本。
     *
     * 你应该使用“贪心算法”来放置给定的单词；也就是说，尽可能多地往每行中放置单词。必要时可用空格 ' ' 填充，使得每行恰好有 maxWidth 个字符。
     *
     * 要求尽可能均匀分配单词间的空格数量。如果某一行单词间的空格不能均匀分配，则左侧放置的空格数要多于右侧的空格数。
     *
     * 文本的最后一行应为左对齐，且单词之间不插入额外的空格。
     *
     * 说明:
     *
     * 单词是指由非空格字符组成的字符序列。
     * 每个单词的长度大于 0，小于等于 maxWidth。
     * 输入单词数组 words 至少包含一个单词。
     * 示例:
     *
     * 输入:
     * words = ["This", "is", "an", "example", "of", "text", "justification."]
     * maxWidth = 16
     * 输出:
     * [
     *    "This    is    an",
     *    "example  of text",
     *    "justification.  "
     * ]
     * 示例 2:
     *
     * 输入:
     * words = ["What","must","be","acknowledgment","shall","be"]
     * maxWidth = 16
     * 输出:
     * [
     *   "What   must   be",
     *   "acknowledgment  ",
     *   "shall be        "
     * ]
     * 解释: 注意最后一行的格式应为 "shall be    " 而不是 "shall     be",
     *      因为最后一行应为左对齐，而不是左右两端对齐。
     *      第二行同样为左对齐，这是因为这行只包含一个单词。
     * 示例 3:
     *
     * 输入:
     * words = ["Science","is","what","we","understand","well","enough","to","explain",
     *          "to","a","computer.","Art","is","everything","else","we","do"]
     * maxWidth = 20
     * 输出:
     * [
     *   "Science  is  what we",
     *   "understand      well",
     *   "enough to explain to",
     *   "a  computer.  Art is",
     *   "everything  else  we",
     *   "do                  "
     * ]
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
                String rowWords = getRowWords(lastIndex,totalNum,qty,words);
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
            if (i != index ) {
                rowWords.append(" ");  // 最后一行，只插入一个空格
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

    private String getRowWords(int index,int totalNum,int qty,String[] words) {
        // 2.当前行的Word之间的空格尽可能均匀分配，如果不能，左侧放置的空格数要多于右侧的空格数

        int avgNum = totalNum;
        int num = 0;
        if (qty > 1) {
            avgNum = totalNum / (qty-1);
            num = totalNum % (qty-1);
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
            if (i != index ) {
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
     * 1221. 分割平衡字符串
     * 在一个「平衡字符串」中，'L' 和 'R' 字符的数量是相同的。
     *
     * 给出一个平衡字符串 s，请你将它分割成尽可能多的平衡字符串。
     *
     * 返回可以通过分割得到的平衡字符串的最大数量。
     *
     *
     *
     * 示例 1：
     *
     * 输入：s = "RLRRLLRLRL"
     * 输出：4
     * 解释：s 可以分割为 "RL", "RRLL", "RL", "RL", 每个子字符串中都包含相同数量的 'L' 和 'R'。
     * 示例 2：
     *
     * 输入：s = "RLLLLRRRLR"
     * 输出：3
     * 解释：s 可以分割为 "RL", "LLLRRR", "LR", 每个子字符串中都包含相同数量的 'L' 和 'R'。
     * 示例 3：
     *
     * 输入：s = "LLLLRRRR"
     * 输出：1
     * 解释：s 只能保持原样 "LLLLRRRR".
     *
     *
     * 提示：
     *
     * 1 <= s.length <= 1000
     * s[i] = 'L' 或 'R'
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
     * 455. 分发饼干
     * 假设你是一位很棒的家长，想要给你的孩子们一些小饼干。但是，每个孩子最多只能给一块饼干。对每个孩子 i ，都有一个胃口值 gi ，这是能让孩子们满足胃口的饼干的最小尺寸；并且每块饼干 j ，都有一个尺寸 sj 。如果 sj >= gi ，我们可以将这个饼干 j 分配给孩子 i ，这个孩子会得到满足。你的目标是尽可能满足越多数量的孩子，并输出这个最大数值。
     *
     * 注意：
     *
     * 你可以假设胃口值为正。
     * 一个小朋友最多只能拥有一块饼干。
     *
     * 示例 1:
     *
     * 输入: [1,2,3], [1,1]
     *
     * 输出: 1
     *
     * 解释:
     * 你有三个孩子和两块小饼干，3个孩子的胃口值分别是：1,2,3。
     * 虽然你有两块小饼干，由于他们的尺寸都是1，你只能让胃口值是1的孩子满足。
     * 所以你应该输出1。
     * 示例 2:
     *
     * 输入: [1,2], [1,2,3]
     *
     * 输出: 2
     *
     * 解释:
     * 你有两个孩子和三块小饼干，2个孩子的胃口值分别是1,2。
     * 你拥有的饼干数量和尺寸都足以让所有孩子满足。
     * 所以你应该输出2.
     * @param g
     * @param s
     * @return
     */
    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int i = 0, j = 0;
        while (i < g.length && j < s.length){
            if (g[i] <= s[j]) {
                i++;
            }
            j++;
        }
        return i;
    }


    /**
     * 860. 柠檬水找零
     * 在柠檬水摊上，每一杯柠檬水的售价为 5 美元。
     *
     * 顾客排队购买你的产品，（按账单 bills 支付的顺序）一次购买一杯。
     *
     * 每位顾客只买一杯柠檬水，然后向你付 5 美元、10 美元或 20 美元。你必须给每个顾客正确找零，也就是说净交易是每位顾客向你支付 5 美元。
     *
     * 注意，一开始你手头没有任何零钱。
     *
     * 如果你能给每位顾客正确找零，返回 true ，否则返回 false 。
     *
     * 示例 1：
     *
     * 输入：[5,5,5,10,20]
     * 输出：true
     * 解释：
     * 前 3 位顾客那里，我们按顺序收取 3 张 5 美元的钞票。
     * 第 4 位顾客那里，我们收取一张 10 美元的钞票，并返还 5 美元。
     * 第 5 位顾客那里，我们找还一张 10 美元的钞票和一张 5 美元的钞票。
     * 由于所有客户都得到了正确的找零，所以我们输出 true。
     * 示例 2：
     *
     * 输入：[5,5,10]
     * 输出：true
     * 示例 3：
     *
     * 输入：[10,10]
     * 输出：false
     * 示例 4：
     *
     * 输入：[5,5,10,10,20]
     * 输出：false
     * 解释：
     * 前 2 位顾客那里，我们按顺序收取 2 张 5 美元的钞票。
     * 对于接下来的 2 位顾客，我们收取一张 10 美元的钞票，然后返还 5 美元。
     * 对于最后一位顾客，我们无法退回 15 美元，因为我们现在只有两张 10 美元的钞票。
     * 由于不是每位顾客都得到了正确的找零，所以答案是 false。
     *
     *
     * 提示：
     *
     * 0 <= bills.length <= 10000
     * bills[i] 不是 5 就是 10 或是 20
     * @param bills
     * @return
     */
    public boolean lemonadeChange(int[] bills) {
        int len = bills.length;
        int five = 0,ten = 0;
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
        //int[] commands = {4,-1,4,-2,4};
        //int[][] obstacles = {{2,4}};
        int[] commands = {-2,-1,-2,3,7};
        int[][] obstacles = {{1,-3},{2,-3},{4,0},{-2,5},{-5,2},{0,0},{4,-4},{-2,-5},{-1,-2},{0,2}};
        logResult(robotSim(commands,obstacles));
    }

    /**
     * 874. 模拟行走机器人
     * 机器人在一个无限大小的网格上行走，从点 (0, 0) 处开始出发，面向北方。该机器人可以接收以下三种类型的命令：
     *
     * -2：向左转 90 度
     * -1：向右转 90 度
     * 1 <= x <= 9：向前移动 x 个单位长度
     * 在网格上有一些格子被视为障碍物。
     *
     * 第 i 个障碍物位于网格点  (obstacles[i][0], obstacles[i][1])
     *
     * 如果机器人试图走到障碍物上方，那么它将停留在障碍物的前一个网格方块上，但仍然可以继续该路线的其余部分。
     *
     * 返回从原点到机器人的最大欧式距离的平方。
     *
     *
     *
     * 示例 1：
     *
     * 输入: commands = [4,-1,3], obstacles = []
     * 输出: 25
     * 解释: 机器人将会到达 (3, 4)
     * 示例 2：
     *
     * 输入: commands = [4,-1,4,-2,4], obstacles = [[2,4]]
     * 输出: 65
     * 解释: 机器人在左转走到 (1, 8) 之前将被困在 (1, 4) 处
     *
     *
     * 提示：
     *
     * 0 <= commands.length <= 10000
     * 0 <= obstacles.length <= 10000
     * -30000 <= obstacle[i][0] <= 30000
     * -30000 <= obstacle[i][1] <= 30000
     * 答案保证小于 2 ^ 31
     * @param commands
     * @param obstacles
     * @return
     */
    public int robotSim(int[] commands, int[][] obstacles) {
        int x = 0,y = 0;

        Map<String,Integer> obstacleMap = new HashMap<>();
        for (int[] obstacle : obstacles) {
            int obx = obstacle[0],oby = obstacle[1];
            obstacleMap.put("x-" + obx, oby);
            obstacleMap.put("y-" + oby, obx);
        }


        // 上 右 下 左
        int flagIndex = 0;
        int result = 0;

        for (int command : commands) {
            if (command == -1) {
                flagIndex = ++flagIndex%4;
            } else if (command == - 2) {
                flagIndex = (flagIndex + 3)%4;
            } else {
                // 4个方向
                switch (flagIndex) {
                    case 0 : {
                        // 判断上方有没有障碍物
                        Integer oby = obstacleMap.get("x-" + x);
                        if (oby == null || oby < y || oby > y + command) {
                            y += command;
                        } else {
                            y = oby - 1;
                        }
                    };break;
                    case 1 : {
                        // 判断右方有没有障碍物
                        Integer obx = obstacleMap.get("y-" + y);
                        if (obx == null || obx < x || obx > x + command) {
                            x += command;
                        } else {
                            x = obx - 1;
                        }
                    };break;
                    case 2 : {
                        // 判断下方有没有障碍物
                        Integer oby = obstacleMap.get("x-" + x);
                        if (oby == null || oby > y || oby < y - command) {
                            y -= command;
                        } else {
                            y = oby + 1;
                        }
                    };break;
                    case 3 : {
                        // 判断左方有没有障碍物
                        Integer obx = obstacleMap.get("y-" + y);
                        if (obx == null || obx > x || obx < x - command) {
                            x -= command;
                        } else {
                            x = obx + 1;
                        }
                    };break;

                }
            }

        }
        log.debug("x:{},y:{}",x,y);
        result = x * x + y * y;
        return result;
    }

    static int[] DIR_ROW = {0, 1, 0, -1};
    static int[] DIR_COL = {1, 0, -1, 0};


    @Test
    public void largestSumAfterKNegations() {
        int[] nums = {-2,5,0,2,-2};
        int k = 3;
        logResult(largestSumAfterKNegations(nums,k));
    }

    /**
     * 1005. K 次取反后最大化的数组和
     * 给定一个整数数组 A，我们只能用以下方法修改该数组：我们选择某个个索引 i 并将 A[i] 替换为 -A[i]，然后总共重复这个过程 K 次。（我们可以多次选择同一个索引 i。）
     *
     * 以这种方式修改数组后，返回数组可能的最大和。
     *
     *
     *
     * 示例 1：
     *
     * 输入：A = [4,2,3], K = 1
     * 输出：5
     * 解释：选择索引 (1,) ，然后 A 变为 [4,-2,3]。
     * 示例 2：
     *
     * 输入：A = [3,-1,0,2], K = 3
     * 输出：6
     * 解释：选择索引 (1, 2, 2) ，然后 A 变为 [3,1,0,2]。
     * 示例 3：
     *
     * 输入：A = [2,-3,-1,5,-4], K = 2
     * 输出：13
     * 解释：选择索引 (1, 4) ，然后 A 变为 [2,3,-1,5,4]。
     *
     *
     * 提示：
     *
     * 1 <= A.length <= 10000
     * 1 <= K <= 10000
     * -100 <= A[i] <= 100
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
            A[i] = - A[i];
            K--;
        }
        log.debug("a:{}",A);
        Arrays.sort(A);
        log.debug("a:{}",A);
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
     * 1029. 两地调度
     * 公司计划面试 2N 人。第 i 人飞往 A 市的费用为 costs[i][0]，飞往 B 市的费用为 costs[i][1]。
     *
     * 返回将每个人都飞到某座城市的最低费用，要求每个城市都有 N 人抵达。
     *
     *
     *
     * 示例：
     *
     * 输入：[[10,20],[30,200],[400,50],[30,20]]
     * 输出：110
     * 解释：
     * 第一个人去 A 市，费用为 10。
     * 第二个人去 A 市，费用为 30。
     * 第三个人去 B 市，费用为 50。
     * 第四个人去 B 市，费用为 20。
     *
     * 最低总费用为 10 + 30 + 50 + 20 = 110，每个城市都有一半的人在面试。
     *
     *
     * 提示：
     *
     * 1 <= costs.length <= 100
     * costs.length 为偶数
     * 1 <= costs[i][0], costs[i][1] <= 1000
     * @param costs
     * @return
     */
    public int twoCitySchedCost(int[][] costs) {
        int result = 0;
        int len = costs.length;
        int n = len >> 1;
        // 选择A - B差距最大的 n个去B, 其余的去A
        Arrays.sort(costs,(a,b) -> (b[0] - b[1]) - (a[0] - a[1]));

        for (int i = 0; i < n; i++) {
            result += costs[i][1];
        }
        for (int i = n; i < len; i++) {
            result += costs[i][0];
        }






        return result;
    }
}
