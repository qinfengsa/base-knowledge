package com.qinfengsa.algorithm.back;

import static com.qinfengsa.algorithm.util.LogUtils.logResult;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Queue;
import java.util.Set;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

/**
 * 回溯算法
 *
 * @author: qinfengsa
 * @date: 2019/8/10 23:19
 */
@Slf4j
public class BackTest {

    // 回溯算法实际上一个类似枚举的搜索尝试过程，主要是在搜索尝试过程中寻找问题的解，当发现已不满足求解条件时，就“回溯”返回，尝试别的路径
    // 用回溯算法解决问题的一般步骤：
    // 1、 针对所给问题，定义问题的解空间，它至少包含问题的一个（最优）解。
    // 2 、确定易于搜索的解空间结构,使得能用回溯法方便地搜索整个解空间 。
    // 3 、以深度优先的方式搜索解空间，并且在搜索过程中用剪枝函数避免无效搜索。

    /** 八皇后问题：在8×8格的国际象棋上摆放八个皇后，使其不能互相攻击， 即任意两个皇后都不能处于同一行、同一列或同一斜线上，问有多少种摆法。 */
    public void getQueen(int n) {

        // 转化成深度优先遍历
        int i = 0;
    }

    @Test
    public void letterCombinations() {
        String digits = "23";
        List<String> result = letterCombinations(digits);
        log.debug("result:{}", result);
    }

    Map<String, String> phone =
            new HashMap<String, String>() {
                {
                    put("2", "abc");
                    put("3", "def");
                    put("4", "ghi");
                    put("5", "jkl");
                    put("6", "mno");
                    put("7", "pqrs");
                    put("8", "tuv");
                    put("9", "wxyz");
                }
            };

    /**
     * 电话号码的字母组合 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。
     *
     * <p>给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。 示例:
     *
     * <p>输入："23" 输出：["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"]. 说明:
     * 尽管上面的答案是按字典序排列的，但是你可以任意选择答案输出的顺序。
     *
     * @param digits
     * @return
     */
    public List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();
        if (digits.length() > 0) {
            setLetter("", digits, result);
        }
        return result;
    }

    private void setLetter(String str, String nextDigits, List<String> combinationList) {
        if (nextDigits.length() == 0) {
            combinationList.add(str);
        } else {
            String digit = nextDigits.substring(0, 1);
            String letters = phone.get(digit);
            for (int i = 0; i < letters.length(); i++) {
                String letter = letters.substring(i, i + 1);
                setLetter(str + letter, nextDigits.substring(1), combinationList);
            }
        }
    }

    @Test
    public void test1() {
        char c = 'a' + 1;
        log.debug("c:{}", c);
    }

    @Test
    public void generateParenthesis() {
        int n = 4;
        List<String> result = generateParenthesis(n);
        log.debug("result:{}", result);
    }
    /**
     * 生成括号 给出 n 代表生成括号的对数，请你写出一个函数，使其能够生成所有可能的并且有效的括号组合。
     *
     * <p>例如，给出 n = 3，生成结果为：
     *
     * <p>[ "((()))", "(()())", "(())()", "()(())", "()()()" ]
     *
     * @param n
     * @return
     */
    private List<String> generateParenthesis(int n) {
        // 思路 记录左括号的数量，为0是添加 （, n = 0 时只能添加 ）,
        List<String> result = new ArrayList<>();
        if (n > 0) {
            generateParenthesis("", 0, n, result);
        }

        return result;
    }

    private void generateParenthesis(String str, int leftNum, int n, List<String> combinationList) {
        // leftNum 和 n 均为0 表示 生成括号 结束
        if (leftNum == 0 && n == 0) {
            combinationList.add(str);
        } else {
            if (leftNum == 0) { // 左括号的数量 为 0 添加 (
                generateParenthesis(str + "(", leftNum + 1, n - 1, combinationList);
            } else if (n == 0) { // n 为 0 添加 )
                generateParenthesis(str + ")", leftNum - 1, n, combinationList);
            } else {
                generateParenthesis(str + "(", leftNum + 1, n - 1, combinationList);
                generateParenthesis(str + ")", leftNum - 1, n, combinationList);
            }
        }
    }

    @Test
    public void permute() {
        int[] nums = {1, 2, 3, 4};
        List<List<Integer>> result = permute(nums);
        log.debug("result:{}", result);
    }

    /**
     * 全排列 给定一个没有重复数字的序列，返回其所有可能的全排列。
     *
     * <p>示例:
     *
     * <p>输入: [1,2,3] 输出: [ [1,2,3], [1,3,2], [2,1,3], [2,3,1], [3,1,2], [3,2,1] ]
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        List<Integer> numList = new ArrayList<>();
        for (int num : nums) {
            numList.add(num);
        }

        permute(new LinkedList<>(), numList, result);

        return result;
    }

    /**
     * 排列
     *
     * @param deque 已排列的数字
     * @param numList 未排列的数字
     * @param combinationList
     */
    private void permute(
            Deque<Integer> deque, List<Integer> numList, List<List<Integer>> combinationList) {
        int len = numList.size();
        // 当 numList 中 没有元素,遍历结束,开始添加
        if (len == 0) {
            combinationList.add(new ArrayList<>(deque));
            return;
        }
        for (int i = 0; i < len; i++) {

            /*List<Integer> bList = new ArrayList<>(numList);
            int num = bList.remove(i);
            List<Integer> aList = new ArrayList<>(list);
            aList.add(num);
            permute(aList,bList,combinationList);*/
            int num = numList.remove(i);
            deque.addLast(num);
            permute(deque, numList, combinationList);
            numList.add(i, num);
            deque.removeLast();
        }
    }

    @Test
    public void permuteUnique() {
        int[] nums = {1, 1, 3, 4};
        List<List<Integer>> result = permuteUnique(nums);
        logResult(result);
    }

    /**
     * 47. 全排列 II 给定一个可包含重复数字的序列，返回所有不重复的全排列。
     *
     * <p>示例:
     *
     * <p>输入: [1,1,2] 输出: [ [1,1,2], [1,2,1], [2,1,1] ]
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        List<Integer> numList = new ArrayList<>();
        for (int num : nums) {
            numList.add(num);
        }

        permuteUnique(new LinkedList<>(), numList, result);

        return result;
    }

    /**
     * 排列
     *
     * @param deque 已排列的数字
     * @param numList 未排列的数字
     * @param combinationList
     */
    private void permuteUnique(
            Deque<Integer> deque, List<Integer> numList, List<List<Integer>> combinationList) {
        int len = numList.size();
        // 当 numList 中 没有元素,遍历结束,开始添加
        if (len == 0) {
            combinationList.add(new ArrayList<>(deque));
            return;
        }
        for (int i = 0; i < len; i++) {
            // 如果 当前 num 和 前一个相等, 跳过
            if (i > 0 && Objects.equals(numList.get(i - 1), numList.get(i))) {
                continue;
            }
            int num = numList.remove(i);
            deque.addLast(num);
            permuteUnique(deque, numList, combinationList);
            numList.add(i, num);
            deque.removeLast();
        }
    }

    @Test
    public void subsets() {
        int[] nums = {1, 2, 3, 4};
        List<List<Integer>> result = subsets(nums);
        log.debug("result:{}", result);
    }
    /**
     * 子集 给定一组不含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
     *
     * <p>说明：解集不能包含重复的子集。
     *
     * <p>示例:
     *
     * <p>输入: nums = [1,2,3] 输出: [ [3], [1], [2], [1,2,3], [1,3], [2,3], [1,2], [] ]
     *
     * @param nums
     * @return
     */
    private List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        result.add(new ArrayList<>());
        List<Integer> numList = new ArrayList<>();
        for (int num : nums) {
            numList.add(num);
        }
        // 思路 ： 第1个元素  + 剩下元素的组合
        subsets(new ArrayList<>(), numList, result);

        return result;
    }

    private void subsets(
            List<Integer> list, List<Integer> numList, List<List<Integer>> combinationList) {

        if (list.size() > 0) {
            combinationList.add(list);
        }

        for (Iterator<Integer> iterator = numList.iterator(); iterator.hasNext(); ) {
            int num = iterator.next();
            iterator.remove();
            List<Integer> aList = new ArrayList<>(list);
            aList.add(num);
            List<Integer> bList = new ArrayList<>(numList);
            subsets(aList, bList, combinationList);
        }
    }

    @Test
    public void existWord() {
        char[][] board = {{'A', 'B', 'C', 'E'}, {'S', 'F', 'C', 'S'}, {'A', 'D', 'E', 'E'}};
        String word = "ABCED";
        boolean result = exist(board, word);
        log.debug("result:{}", result);
    }

    /**
     * 单词搜索 给定一个二维网格和一个单词，找出该单词是否存在于网格中。
     *
     * <p>单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。
     *
     * <p>示例:
     *
     * <p>board = [ ['A','B','C','E'], ['S','F','C','S'], ['A','D','E','E'] ]
     *
     * <p>给定 word = "ABCCED", 返回 true. 给定 word = "SEE", 返回 true. 给定 word = "ABCB", 返回 false.
     *
     * @param board
     * @param word
     * @return
     */
    private boolean exist(char[][] board, String word) {
        int rowNum = board.length;
        if (rowNum == 0) {
            return false;
        }
        int colNum = board[0].length;
        if (colNum == 0) {
            return false;
        }

        boolean[][] marked = new boolean[rowNum][colNum];

        for (int i = 0; i < rowNum; i++) {

            for (int j = 0; j < colNum; j++) {
                if (existWord(i, j, 0, board, marked, word)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean existWord(
            int row, int col, int index, char[][] board, boolean[][] marked, String word) {
        int rowNum = board.length;
        int colNum = board[0].length;
        if (index == word.length() - 1) {
            return board[row][col] == word.charAt(index);
        }
        if (board[row][col] == word.charAt(index)) {
            marked[row][col] = true;
            if (inArea(row + 1, col, rowNum, colNum)
                    && !marked[row + 1][col]
                    && existWord(row + 1, col, index + 1, board, marked, word)) {
                return true;
            }
            if (inArea(row - 1, col, rowNum, colNum)
                    && !marked[row - 1][col]
                    && existWord(row - 1, col, index + 1, board, marked, word)) {
                return true;
            }
            if (inArea(row, col + 1, rowNum, colNum)
                    && !marked[row][col + 1]
                    && existWord(row, col + 1, index + 1, board, marked, word)) {
                return true;
            }
            if (inArea(row, col - 1, rowNum, colNum)
                    && !marked[row][col - 1]
                    && existWord(row, col - 1, index + 1, board, marked, word)) {
                return true;
            }

            marked[row][col] = false;
        }

        return false;
    }

    private boolean inArea(int row, int col, int rowNum, int colNum) {
        return row >= 0 && row < rowNum && col >= 0 && col < colNum;
    }

    @Test
    public void grayCode() {
        int n = 2;

        List<Integer> result = grayCode(n);
        log.debug("result:{}", result);
    }
    /**
     * 格雷编码 格雷编码是一个二进制数字系统，在该系统中，两个连续的数值仅有一个位数的差异。
     *
     * <p>给定一个代表编码总位数的非负整数 n，打印其格雷编码序列。格雷编码序列必须以 0 开头。
     *
     * <p>示例 1:
     *
     * <p>输入: 2 输出: [0,1,3,2] 解释: 00 - 0 01 - 1 11 - 3 10 - 2
     *
     * <p>对于给定的 n，其格雷编码序列并不唯一。 例如，[0,2,3,1] 也是一个有效的格雷编码序列。
     *
     * <p>00 - 0 10 - 2 11 - 3 01 - 1 示例 2:
     *
     * <p>输入: 0 输出: [0] 解释: 我们定义格雷编码序列必须以 0 开头。 给定编码总位数为 n 的格雷编码序列，其长度为 2^n。当 n = 0 时，长度为 2^0 = 1。
     * 因此，当 n = 0 时，其格雷编码序列为 [0]。
     *
     * @param n
     * @return
     */
    public List<Integer> grayCode(int n) {
        List<Integer> result = new ArrayList<>();
        result.add(0);
        if (n == 0) {
            return result;
        }
        int head = 1;
        // 全排列
        for (int i = 0; i < n; i++) {
            for (int j = result.size() - 1; j >= 0; j--) {
                result.add(head + result.get(j));
            }
            head <<= 1;
        }

        return result;
    }

    @Test
    public void removeInvalidParentheses() {
        String s = "(a)())()";

        List<String> result = removeInvalidParentheses(s);
        log.debug("result:{}", result);
    }

    /**
     * Remove Invalid Parentheses 删除最小数量的无效括号，使得输入的字符串有效，返回所有可能的结果。
     *
     * <p>说明: 输入可能包含了除 ( 和 ) 以外的字符。
     *
     * <p>示例 1:
     *
     * <p>输入: "()())()" 输出: ["()()()", "(())()"] 示例 2:
     *
     * <p>输入: "(a)())()" 输出: ["(a)()()", "(a())()"] 示例 3:
     *
     * <p>输入: ")(" 输出: [""]
     *
     * @param s
     * @return
     */
    public List<String> removeInvalidParentheses(String s) {
        Set<String> set = new HashSet<>();
        int leftInvalidCount = 0, rightInvalidCount = 0;
        // 计算 无效的括号个数
        for (char c : s.toCharArray()) {
            if (c == '(') {
                leftInvalidCount++;
            }
            if (c == ')') {
                if (leftInvalidCount > 0) {
                    leftInvalidCount--;
                } else {
                    rightInvalidCount++;
                }
            }
        }
        removeInvalidParentheses(new StringBuilder(s), 0, leftInvalidCount, rightInvalidCount, set);
        List<String> result = new ArrayList<>(set);
        return result;
    }

    private void removeInvalidParentheses(
            StringBuilder sb, int index, int leftCount, int rightCount, Set<String> set) {
        if (leftCount == 0 && rightCount == 0) {
            if (checkParentheses(sb)) {
                set.add(sb.toString());
            }
            return;
        }
        int len = sb.length();
        for (int i = index; i < len; i++) {
            char c = sb.charAt(i);
            if (leftCount > 0 && c == '(') {
                StringBuilder a = new StringBuilder();
                a.append(sb.substring(0, i));
                a.append(sb.substring(i + 1, len));
                removeInvalidParentheses(a, i, leftCount - 1, rightCount, set);
            }
            if (rightCount > 0 && c == ')') {
                StringBuilder a = new StringBuilder();
                a.append(sb.substring(0, i));
                a.append(sb.substring(i + 1, len));
                removeInvalidParentheses(a, i, leftCount, rightCount - 1, set);
            }
        }
    }

    private boolean checkParentheses(StringBuilder sb) {
        int leftCount = 0;
        for (int i = 0; i < sb.length(); i++) {
            char c = sb.charAt(i);
            if (c == '(') {
                leftCount++;
            }
            if (c == ')') {
                if (leftCount > 0) {
                    leftCount--;
                } else {
                    return false;
                }
            }
        }
        return leftCount == 0;
    }

    private void addSet(
            String s,
            StringBuilder sb,
            int index,
            int leftCount,
            int rightCount,
            int leftInvalidCount,
            int rightInvalidCount,
            Set<String> set) {

        if (index == s.length()) {
            if (leftInvalidCount == 0 && rightInvalidCount == 0) {
                set.add(sb.toString());
            }
            return;
        }
        char c = s.charAt(index);
        if (c == '(' && leftInvalidCount > 0) {
            addSet(
                    s,
                    sb,
                    index + 1,
                    leftCount,
                    rightCount,
                    ++leftInvalidCount,
                    rightInvalidCount,
                    set);
        } else if (c == ')' && rightInvalidCount > 0) {
            addSet(
                    s,
                    sb,
                    index + 1,
                    leftCount,
                    rightCount,
                    leftInvalidCount,
                    --rightInvalidCount,
                    set);
            if (rightCount == leftCount) {
                return;
            }
        }
        int len = sb.length();
        sb.append(c);
        if (c == '(') {
            leftCount++;
        } else if (c == ')') {
            rightCount++;
        }
        addSet(s, sb, index + 1, leftCount, rightCount, leftInvalidCount, rightInvalidCount, set);
        // 删除最后一位
        sb.deleteCharAt(len);
    }

    @Test
    public void isMatch() {
        String s = "adceb", p = "*a*c?b";
        boolean result = isMatch(s, p);
        log.debug("result:{}", result);
    }

    /**
     * 通配符匹配 给定一个字符串 (s) 和一个字符模式 (p) ，实现一个支持 '?' 和 '*' 的通配符匹配。
     *
     * <p>'?' 可以匹配任何单个字符。 '*' 可以匹配任意字符串（包括空字符串）。 两个字符串完全匹配才算匹配成功。
     *
     * <p>说明:
     *
     * <p>s 可能为空，且只包含从 a-z 的小写字母。 p 可能为空，且只包含从 a-z 的小写字母，以及字符 ? 和 *。 示例 1:
     *
     * <p>输入: s = "aa" p = "a" 输出: false 解释: "a" 无法匹配 "aa" 整个字符串。 示例 2:
     *
     * <p>输入: s = "aa" p = "*" 输出: true 解释: '*' 可以匹配任意字符串。 示例 3:
     *
     * <p>输入: s = "cb" p = "?a" 输出: false 解释: '?' 可以匹配 'c', 但第二个 'a' 无法匹配 'b'。 示例 4:
     *
     * <p>输入: s = "adceb" p = "*a*b" 输出: true 解释: 第一个 '*' 可以匹配空字符串, 第二个 '*' 可以匹配字符串 "dce". 示例 5:
     *
     * <p>输入: s = "acdcb" p = "a*c?b" 输入: false
     *
     * @param s
     * @param p
     * @return
     */
    public boolean isMatch(String s, String p) {
        int m = s.length(), n = p.length();

        boolean[][] match = new boolean[m + 1][n + 1];
        match[0][0] = true;
        for (int j = 1; j <= n; j++) {
            match[0][j] = p.charAt(j - 1) == '*' && match[0][j - 1];
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                char c1 = s.charAt(i - 1), c2 = p.charAt(j - 1);
                // 同位置的字符相同或者有？号, 查看前面是否匹配
                if (c1 == c2 || c2 == '?') {
                    // 上面一位是否匹配
                    match[i][j] = match[i - 1][j - 1];
                }
                // * 可以不占位置
                // match[i-1][j] s 的上一位是否匹配
                // match[i][j-1] p 的上一位是否匹配
                if (c2 == '*') {
                    match[i][j] = match[i - 1][j] || match[i][j - 1];
                }
            }
        }
        return match[m][n];
    }

    @Test
    public void isMatch2() {
        String s = "aab", p = "c*a*b";
        boolean result = isMatch2(s, p);
        log.debug("result:{}", result);
    }

    /**
     * 正则表达式匹配 给你一个字符串 s 和一个字符规律 p，请你来实现一个支持 '.' 和 '*' 的正则表达式匹配。
     *
     * <p>'.' 匹配任意单个字符 '*' 匹配零个或多个前面的那一个元素 所谓匹配，是要涵盖 整个 字符串 s的，而不是部分字符串。
     *
     * <p>说明:
     *
     * <p>s 可能为空，且只包含从 a-z 的小写字母。 p 可能为空，且只包含从 a-z 的小写字母，以及字符 . 和 *。 示例 1:
     *
     * <p>输入: s = "aa" p = "a" 输出: false 解释: "a" 无法匹配 "aa" 整个字符串。 示例 2:
     *
     * <p>输入: s = "aa" p = "a*" 输出: true 解释: 因为 '*' 代表可以匹配零个或多个前面的那一个元素, 在这里前面的元素就是 'a'。因此，字符串 "aa"
     * 可被视为 'a' 重复了一次。 示例 3:
     *
     * <p>输入: s = "ab" p = ".*" 输出: true 解释: ".*" 表示可匹配零个或多个（'*'）任意字符（'.'）。 示例 4:
     *
     * <p>输入: s = "aab" p = "c*a*b" 输出: true 解释: 因为 '*' 表示零个或多个，这里 'c' 为 0 个, 'a' 被重复一次。因此可以匹配字符串
     * "aab"。 示例 5:
     *
     * <p>输入: s = "mississippi" p = "mis*is*p*." 输出: false
     *
     * @param s
     * @param p
     * @return
     */
    public boolean isMatch2(String s, String p) {
        int m = s.length(), n = p.length();

        boolean[][] match = new boolean[m + 1][n + 1];
        match[m][n] = true;
        for (int j = n - 2; j >= 0; j--) {
            match[m][j] = p.charAt(j + 1) == '*' && match[m][j + 2];
        }
        // 由于存在*从前往后遍历
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                char c1 = s.charAt(i), c2 = p.charAt(j);
                // 同位置的字符相同或者有.号, 查看后面一位是否匹配
                boolean charMatch = c1 == c2 || c2 == '.';
                // 当前位置后面是 *
                if (j < n - 1 && p.charAt(j + 1) == '*') {
                    match[i][j] = match[i][j + 2] || charMatch && match[i + 1][j];
                } else {
                    match[i][j] = charMatch && match[i + 1][j + 1];
                }
            }
        }
        return match[0][0];
    }

    @Test
    public void readBinaryWatch() {
        int num = 2;
        logResult(readBinaryWatch(num));
    }

    /**
     * 401. 二进制手表 二进制手表顶部有 4 个 LED 代表小时（0-11），底部的 6 个 LED 代表分钟（0-59）。
     *
     * <p>每个 LED 代表一个 0 或 1，最低位在右侧。
     *
     * <p>例如，上面的二进制手表读取 “3:25”。
     *
     * <p>给定一个非负整数 n 代表当前 LED 亮着的数量，返回所有可能的时间。
     *
     * <p>案例:
     *
     * <p>输入: n = 1 返回: ["1:00", "2:00", "4:00", "8:00", "0:01", "0:02", "0:04", "0:08", "0:16",
     * "0:32"]
     *
     * <p>注意事项:
     *
     * <p>输出的顺序没有要求。 小时不会以零开头，比如 “01:00” 是不允许的，应为 “1:00”。 分钟必须由两位数组成，可能会以零开头，比如 “10:2” 是无效的，应为
     * “10:02”。
     *
     * @param num
     * @return
     */
    public List<String> readBinaryWatch(int num) {
        List<String> result = new ArrayList<>();
        // 8 4 2 1 32 16 8 4 2 1
        boolean[] leds = new boolean[10];

        readBinaryWatch(num, 0, leds, result);
        return result;
    }

    private void readBinaryWatch(int num, int start, boolean[] leds, List<String> result) {
        if (num == 0) {
            int[] times = getTimes(leds);
            if (times != null) {
                StringBuilder sb = new StringBuilder();
                sb.append(times[0]);
                sb.append(':');
                if (times[1] < 10) {
                    sb.append('0');
                }
                sb.append(times[1]);
                result.add(sb.toString());
            }
            return;
        }
        for (int i = start; i < 10; i++) {
            if (leds[i]) {
                continue;
            }
            leds[i] = true;
            readBinaryWatch(num - 1, i + 1, leds, result);
            leds[i] = false;
        }
    }

    private int[] getTimes(boolean[] leds) {
        int hours = 0;

        int minutes = 0;
        // 8 4 2 1
        for (int i = 0; i < 4; i++) {
            if (leds[i]) {
                int num = 1 << (3 - i);
                hours += num;
            }
        }
        if (hours >= 12) {
            return null;
        }

        // 32 16 8 4 2 1
        for (int i = 4; i < 10; i++) {
            if (leds[i]) {
                int num = 1 << (9 - i);
                minutes += num;
            }
        }
        if (minutes > 59) {
            return null;
        }

        int[] times = new int[2];

        times[0] = hours;
        times[1] = minutes;
        return times;
    }

    @Test
    public void solveSudoku() {
        char[][] board = {
            {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
            {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
            {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
            {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
            {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
            {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
            {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
            {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
            {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
        };
        solveSudoku2(board);
        logResult(board);
    }
    /**
     * 37. 解数独 编写一个程序，通过已填充的空格来解决数独问题。
     *
     * <p>一个数独的解法需遵循如下规则：
     *
     * <p>数字 1-9 在每一行只能出现一次。 数字 1-9 在每一列只能出现一次。 数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。 空白格用 '.' 表示。
     *
     * <p>一个数独。 答案被标成红色。
     *
     * <p>Note:
     *
     * <p>给定的数独序列只包含数字 1-9 和字符 '.' 。 你可以假设给定的数独只有唯一解。 给定数独永远是 9x9 形式的。
     *
     * @param board
     */
    public void solveSudoku(char[][] board) {

        Map<String, Boolean> map = new HashMap<>();
        // i 行 j 列
        for (int i = 0; i < 9; i++) {

            for (int j = 0; j < 9; j++) {

                char c = board[i][j];
                if (c == '.') {
                    continue;
                }
                // j  列
                String colKey = "col" + c + j;
                map.put(colKey, true);
                // i 行
                String rowKey = "row" + c + i;
                map.put(rowKey, true);
                // 3x3 宫格
                String sudoKey = getSudoKey(c, i, j);
                map.put(sudoKey, true);
            }
        }

        solveSudoku(board, map, 0, 0);
    }

    public void solveSudoku2(char[][] board) {

        boolean[][] rowFlag = new boolean[9][9];
        boolean[][] colFlag = new boolean[9][9];
        boolean[][] boxFlag = new boolean[9][9];

        // i 行 j 列
        for (int i = 0; i < 9; i++) {

            for (int j = 0; j < 9; j++) {

                char c = board[i][j];
                if (c == '.') {
                    continue;
                }
                int numIndex = c - '1';
                // 行
                rowFlag[i][numIndex] = true;
                // 列
                colFlag[j][numIndex] = true;
                // 3x3 宫格
                boxFlag[(i / 3) * 3 + j / 3][numIndex] = true;
            }
        }
        boolean a = solveSudoku(board, rowFlag, colFlag, boxFlag, 0, 0);
    }

    public void solveSudoku3(char[][] board) {
        boolean[][] rowFlag = new boolean[9][9];
        boolean[][] colFlag = new boolean[9][9];
        boolean[][] boxFlag = new boolean[9][9];

        int fillCount = 9 * 9;
        for (int i = 0; i < 9; ++i) {
            for (int j = 0; j < 9; ++j) {
                if (board[i][j] == '.') {
                    --fillCount;
                    continue;
                }
                rowFlag[i][board[i][j] - '1'] = true;
                colFlag[j][board[i][j] - '1'] = true;
                boxFlag[(i / 3) * 3 + j / 3][board[i][j] - '1'] = true;
            }
        }
        solveSudokuSub(board, fillCount, rowFlag, colFlag, boxFlag);
    }

    public boolean solveSudokuSub(
            char[][] board,
            int fillcount,
            boolean[][] rowFlag,
            boolean[][] colFlag,
            boolean[][] boxFlag) {
        if (fillcount >= 81) return true;
        int i = 0;
        int j = 0;
        for (; i < 9; ++i) {
            for (j = 0; j < 9; ++j) {
                if (board[i][j] == '.') break;
            }
            if (j < 9) break;
        }
        for (int k = 0; k < 9; ++k) {
            if (!rowFlag[i][k] && !colFlag[j][k] && !boxFlag[(i / 3) * 3 + j / 3][k]) {
                board[i][j] = (char) ((int) '1' + k);
                ++fillcount;
                rowFlag[i][k] = true;
                colFlag[j][k] = true;
                boxFlag[(i / 3) * 3 + j / 3][k] = true;
                if (solveSudokuSub(board, fillcount, rowFlag, colFlag, boxFlag)) return true;
                board[i][j] = '.';
                --fillcount;
                rowFlag[i][k] = false;
                colFlag[j][k] = false;
                boxFlag[(i / 3) * 3 + j / 3][k] = false;
            }
        }
        return false;
    }

    private boolean solveSudoku(
            char[][] board,
            boolean[][] rowFlag,
            boolean[][] colFlag,
            boolean[][] boxFlag,
            int row,
            int col) {
        // 边界校验, 如果已经填充完成, 返回true, 表示一切结束
        if (col == 9) {
            // 下一行
            col = 0;
            row++;
            if (row == 9) {
                return true;
            }
        }
        if (board[row][col] == '.') {
            for (char num = '1'; num <= '9'; num++) {
                int boxIndex = (row / 3) * 3 + col / 3;
                int numIndex = num - '1';
                // 当前 num 在 当前行,当前列, 当前 3x3 宫格 存在 continue
                if (rowFlag[row][numIndex]
                        || colFlag[col][numIndex]
                        || boxFlag[boxIndex][numIndex]) {
                    continue;
                }
                // 行
                rowFlag[row][numIndex] = true;
                // 列
                colFlag[col][numIndex] = true;
                // 3x3 宫格
                boxFlag[boxIndex][numIndex] = true;
                board[row][col] = num;
                if (solveSudoku(board, rowFlag, colFlag, boxFlag, row, col + 1)) {
                    return true;
                }
                board[row][col] = '.';
                // 行
                rowFlag[row][numIndex] = false;
                // 列
                colFlag[col][numIndex] = false;
                // 3x3 宫格
                boxFlag[boxIndex][numIndex] = false;
            }
        } else {
            return solveSudoku(board, rowFlag, colFlag, boxFlag, row, col + 1);
        }
        return false;
    }

    private boolean solveSudoku(char[][] board, Map<String, Boolean> map, int row, int col) {
        // 边界校验, 如果已经填充完成, 返回true, 表示一切结束
        if (col == 9) {
            // 下一行
            col = 0;
            row++;
            if (row == 9) {
                return true;
            }
        }
        boolean result = false;
        if (board[row][col] == '.') {
            for (char num = '1'; num <= '9'; num++) {
                // col 列
                String colKey = "col" + num + col;
                // row 行
                String rowKey = "row" + num + row;
                // 3x3 宫格
                String sudoKey = getSudoKey(num, row, col);
                // 当前 num 在 当前行,当前列, 当前 3x3 宫格 存在 continue
                if (map.containsKey(colKey)
                        || map.containsKey(rowKey)
                        || map.containsKey(sudoKey)) {
                    continue;
                }
                map.put(colKey, true);
                map.put(rowKey, true);
                map.put(sudoKey, true);
                board[row][col] = num;
                if (solveSudoku(board, map, row, col + 1)) {
                    return true;
                }
                board[row][col] = '.';
                map.remove(colKey);
                map.remove(rowKey);
                map.remove(sudoKey);
            }
        } else {
            return solveSudoku(board, map, row, col + 1);
        }

        /*// j  列
        String colKey = "col" + c + j;
        map.put(colKey,true);
        // i 行
        String rowKey = "row" + c + i;
        map.put(rowKey,true);
        // 3x3 宫格
        String sudoKey = getSudoKey(c,i,j);
        map.put(sudoKey,true);*/
        return false;
    }

    private String getSudoKey(char c, int i, int j) {

        int a = i / 3;
        int b = j / 3;

        return "sudo" + c + a + b;
    }

    @Test
    public void combinationSum() {
        int[] candidates = {2, 3, 6, 7};
        int target = 7;
        logResult(combinationSum(candidates, target));
    }

    /**
     * 39. 组合总和 给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
     *
     * <p>candidates 中的数字可以无限制重复被选取。
     *
     * <p>说明：
     *
     * <p>所有数字（包括 target）都是正整数。 解集不能包含重复的组合。 示例 1:
     *
     * <p>输入: candidates = [2,3,6,7], target = 7, 所求解集为: [ [7], [2,2,3] ] 示例 2:
     *
     * <p>输入: candidates = [2,3,5], target = 8, 所求解集为: [ [2,2,2,2], [2,3,3], [3,5] ]
     *
     * @param candidates
     * @param target
     * @return
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();

        Arrays.sort(candidates);
        Deque<Integer> deque = new LinkedList<>();
        combinationSum(0, candidates, target, deque, result);

        return result;
    }

    private void combinationSum(
            int start,
            int[] candidates,
            int target,
            Deque<Integer> deque,
            List<List<Integer>> result) {
        if (target == 0) {
            result.add(new ArrayList<>(deque));
            return;
        }
        int len = candidates.length;
        for (int i = start; i < len; i++) {
            int num = candidates[i];
            if (num <= target) {
                deque.addLast(num);
                combinationSum(i, candidates, target - num, deque, result);
                deque.removeLast();
            } else {
                break;
            }
        }
    }

    @Test
    public void combinationSum2() {
        int[] candidates = {2, 5, 2, 1, 2};
        int target = 5;
        logResult(combinationSum2(candidates, target));
    }

    /**
     * 40. 组合总和 II 给定一个数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
     *
     * <p>candidates 中的每个数字在每个组合中只能使用一次。
     *
     * <p>说明：
     *
     * <p>所有数字（包括目标数）都是正整数。 解集不能包含重复的组合。 示例 1:
     *
     * <p>输入: candidates = [10,1,2,7,6,1,5], target = 8, 所求解集为: [ [1, 7], [1, 2, 5], [2, 6], [1, 1,
     * 6] ] 示例 2:
     *
     * <p>输入: candidates = [2,5,2,1,2], target = 5, 所求解集为: [ [1,2,2], [5] ]
     */
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();

        Arrays.sort(candidates);
        Deque<Integer> deque = new LinkedList<>();
        combinationSum2(0, candidates, target, deque, result);

        return result;
    }

    private void combinationSum2(
            int start,
            int[] candidates,
            int target,
            Deque<Integer> deque,
            List<List<Integer>> result) {
        if (target == 0) {
            result.add(new ArrayList<>(deque));
            return;
        }
        int len = candidates.length;
        for (int i = start; i < len; i++) {
            int num = candidates[i];
            if (num <= target) {
                if (i > start && candidates[i - 1] == num) {
                    continue;
                }
                deque.addLast(num);
                combinationSum2(i + 1, candidates, target - num, deque, result);
                deque.removeLast();
            } else {
                break;
            }
        }
    }

    @Test
    public void solveNQueens() {
        int n = 4;
        List<List<String>> result = solveNQueens(n);
        for (List<String> a : result) {
            logResult(a);
        }
    }

    /**
     * 51. N皇后 n 皇后问题研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
     *
     * <p>上图为 8 皇后问题的一种解法。
     *
     * <p>给定一个整数 n，返回所有不同的 n 皇后问题的解决方案。
     *
     * <p>每一种解法包含一个明确的 n 皇后问题的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。
     *
     * <p>示例:
     *
     * <p>输入: 4 输出: [ [".Q..", // 解法 1 "...Q", "Q...", "..Q."],
     *
     * <p>["..Q.", // 解法 2 "Q...", "...Q", ".Q.."] ] 解释: 4 皇后问题存在两个不同的解法。
     *
     * @param n
     * @return
     */
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> result = new ArrayList<>();
        // 行
        // boolean[] rowFlag = new boolean[n];
        // 列
        boolean[] colFlag = new boolean[n];
        // 正斜向 反斜向
        boolean[] flag1 = new boolean[2 * n - 1];
        boolean[] flag2 = new boolean[2 * n - 1];

        int[] indexs = new int[n];
        backQueens(n, 0, indexs, result, colFlag, flag1, flag2);
        // log.debug("index:{}",indexs);
        return result;
    }

    private void backQueens(
            int n,
            int row,
            int[] indexs,
            List<List<String>> result,
            boolean[] colFlag,
            boolean[] flag1,
            boolean[] flag2) {
        if (row == n) {
            // log.debug("index:{}",indexs);
            addSolve(indexs, result);
            return;
        }
        // 每一列的位置
        for (int i = 0; i < n; i++) {
            // 判断是否在攻击范围
            if (colFlag[i] || flag1[i + row] || flag2[row + n - 1 - i]) {
                continue;
            }
            colFlag[i] = true;
            flag1[i + row] = true;
            flag2[row + n - 1 - i] = true;
            indexs[row] = i;

            backQueens(n, row + 1, indexs, result, colFlag, flag1, flag2);

            indexs[row] = 0;
            colFlag[i] = false;
            flag1[i + row] = false;
            flag2[row + n - 1 - i] = false;
        }
    }

    private void addSolve(int[] indexs, List<List<String>> result) {
        List<String> list = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for (int num : indexs) {
            sb.setLength(0);
            for (int i = 0; i < indexs.length; i++) {
                if (i == num) {
                    sb.append('Q');
                } else {
                    sb.append('.');
                }
            }
            list.add(sb.toString());
        }
        result.add(list);
    }

    @Test
    public void totalNQueens() {
        int n = 4;
        logResult(totalNQueens(n));
    }

    private static int num = 0;
    /**
     * 52. N皇后 II n 皇后问题研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
     *
     * <p>上图为 8 皇后问题的一种解法。
     *
     * <p>给定一个整数 n，返回 n 皇后不同的解决方案的数量。
     *
     * <p>示例:
     *
     * <p>输入: 4 输出: 2 解释: 4 皇后问题存在如下两个不同的解法。 [ [".Q..", // 解法 1 "...Q", "Q...", "..Q."],
     *
     * <p>["..Q.", // 解法 2 "Q...", "...Q", ".Q.."] ]
     *
     * @param n
     * @return
     */
    public int totalNQueens(int n) {
        // 行
        // boolean[] rowFlag = new boolean[n];
        // 列
        boolean[] colFlag = new boolean[n];
        // 正斜向 反斜向
        boolean[] flag1 = new boolean[2 * n - 1];
        boolean[] flag2 = new boolean[2 * n - 1];

        int[] indexs = new int[n];
        backTotalQueens(n, 0, colFlag, flag1, flag2);
        return num;
    }

    private void backTotalQueens(
            int n, int row, boolean[] colFlag, boolean[] flag1, boolean[] flag2) {
        if (row == n) {
            num++;
            return;
        }
        // 每一列的位置
        for (int i = 0; i < n; i++) {
            // 判断是否在攻击范围
            if (colFlag[i] || flag1[i + row] || flag2[row + n - 1 - i]) {
                continue;
            }
            colFlag[i] = true;
            flag1[i + row] = true;
            flag2[row + n - 1 - i] = true;
            backTotalQueens(n, row + 1, colFlag, flag1, flag2);
            colFlag[i] = false;
            flag1[i + row] = false;
            flag2[row + n - 1 - i] = false;
        }
    }

    @Test
    public void letterCasePermutation() {
        String s = "3z4";
        logResult(letterCasePermutation(s));
    }
    /**
     * 784. 字母大小写全排列 给定一个字符串S，通过将字符串S中的每个字母转变大小写，我们可以获得一个新的字符串。返回所有可能得到的字符串集合。
     *
     * <p>示例: 输入: S = "a1b2" 输出: ["a1b2", "a1B2", "A1b2", "A1B2"]
     *
     * <p>输入: S = "3z4" 输出: ["3z4", "3Z4"]
     *
     * <p>输入: S = "12345" 输出: ["12345"] 注意：
     *
     * <p>S 的长度不超过12。 S 仅由数字和字母组成。
     *
     * @param S
     * @return
     */
    public List<String> letterCasePermutation(String S) {
        List<String> result = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        letterCasePermutation(S, 0, sb, result);
        return result;
    }

    private void letterCasePermutation(
            String str, int index, StringBuilder sb, List<String> result) {
        if (index == str.length()) {
            result.add(sb.toString());
            return;
        }
        char c = str.charAt(index);
        int len = sb.length();
        sb.append(c);
        letterCasePermutation(str, index + 1, sb, result);
        sb.deleteCharAt(len);
        Character ch = null;
        if (c >= 'a' && c <= 'z') {
            ch = (char) ('A' + (c - 'a'));
        } else if (c >= 'A' && c <= 'Z') {
            ch = (char) ('a' + (c - 'A'));
        }
        if (ch != null) {
            sb.append(ch);
            letterCasePermutation(str, index + 1, sb, result);
            sb.deleteCharAt(len);
        }
    }

    @Test
    public void generateParenthesis2() {
        int n = 4;
        logResult(generateParenthesis2(n));
    }

    public List<String> generateParenthesis2(int n) {
        List<String> list = new ArrayList<>();
        if (n > 0) {
            generateParenthesis2(0, n, list, new StringBuilder());
        }

        return list;
    }

    public void generateParenthesis2(int leftNum, int n, List<String> list, StringBuilder sb) {

        if (leftNum == 0 && n == 0) {
            list.add(sb.toString());
            return;
        }
        int len = sb.length();
        if (leftNum == 0) { // 左括号的数量 为 0 添加 (
            generateParenthesis2(leftNum + 1, n - 1, list, sb.append("("));
            sb.deleteCharAt(len);
        } else if (n == 0) { // n 为 0 添加 )
            generateParenthesis2(leftNum - 1, n, list, sb.append(")"));
            sb.deleteCharAt(len);
        } else {
            generateParenthesis2(leftNum + 1, n - 1, list, sb.append("("));
            sb.deleteCharAt(len);
            generateParenthesis2(leftNum - 1, n, list, sb.append(")"));
            sb.deleteCharAt(len);
        }
    }

    @Test
    public void permutation() {
        String s = "abc";
        String[] result = permutation(s);
        log.debug("result:{}", result.length);
    }

    /**
     * 面试题38. 字符串的排列 输入一个字符串，打印出该字符串中字符的所有排列。
     *
     * <p>你可以以任意顺序返回这个字符串数组，但里面不能有重复元素。
     *
     * <p>示例:
     *
     * <p>输入：s = "abc" 输出：["abc","acb","bac","bca","cab","cba"]
     *
     * @param s
     * @return
     */
    public String[] permutation(String s) {
        Set<String> list = new HashSet<>();

        List<Character> charList = new ArrayList<>(s.length());
        for (char c : s.toCharArray()) {
            charList.add(c);
        }
        StringBuilder sb = new StringBuilder();

        permutation(sb, charList, list);

        String[] reuslt = new String[list.size()];
        return list.toArray(reuslt);
    }

    public void permutation(StringBuilder sb, List<Character> charList, Set<String> list) {
        int size = charList.size();
        if (size == 0) {
            list.add(sb.toString());
            return;
        }

        for (int i = 0; i < size; i++) {
            char c = charList.remove(i);
            int len = sb.length();
            sb.append(c);
            permutation(sb, charList, list);
            sb.deleteCharAt(len);
            charList.add(i, c);
        }
    }

    @Test
    public void translateNum() {
        int num = 12258;
        logResult(translateNum(num));
    }

    /**
     * 面试题46. 把数字翻译成字符串 给定一个数字，我们按照如下规则把它翻译为字符串：0 翻译成 “a” ，1 翻译成 “b”，……，11 翻译成 “l”，……，25 翻译成
     * “z”。一个数字可能有多个翻译。请编程实现一个函数，用来计算一个数字有多少种不同的翻译方法。
     *
     * <p>示例 1:
     *
     * <p>输入: 12258 输出: 5 解释: 12258有5种不同的翻译，分别是"bccfi", "bwfi", "bczi", "mcfi"和"mzi"
     *
     * <p>提示：
     *
     * <p>0 <= num < 231
     *
     * @return
     */
    public int translateNum(int num) {

        int result = 0;
        if (num < 10) {
            return 1;
        }
        result += translateNum(num / 10);

        int a = num % 100;
        if (a >= 10 && a <= 25) {
            result += translateNum(num / 100);
        }
        return result;
    }

    @Test
    public void combine() {
        int n = 5, k = 3;
        logResult(combine(n, k));
    }

    /**
     * 77. 组合 给定两个整数 n 和 k，返回 1 ... n 中所有可能的 k 个数的组合。
     *
     * <p>示例:
     *
     * <p>输入: n = 4, k = 2 输出: [ [2,4], [3,4], [2,3], [1,2], [1,3], [1,4], ]
     *
     * @param n
     * @param k
     * @return
     */
    public List<List<Integer>> combine(int n, int k) {

        List<List<Integer>> result = new ArrayList<>();

        combine(1, n, k, new LinkedList<>(), result);

        return result;
    }

    public void combine(int start, int end, int k, List<Integer> list, List<List<Integer>> result) {

        if (k == 0) {
            result.add(new ArrayList<>(list));
            return;
        }
        int size = list.size();
        for (int i = start; i <= end; i++) {
            list.add(i);
            combine(i + 1, end, k - 1, list, result);
            list.remove(size);
        }
    }

    /**
     * 216. 组合总和 III 找出所有相加之和为 n 的 k 个数的组合。组合中只允许含有 1 - 9 的正整数，并且每种组合中不存在重复的数字。
     *
     * <p>说明：
     *
     * <p>所有数字都是正整数。 解集不能包含重复的组合。 示例 1:
     *
     * <p>输入: k = 3, n = 7 输出: [[1,2,4]] 示例 2:
     *
     * <p>输入: k = 3, n = 9 输出: [[1,2,6], [1,3,5], [2,3,4]]
     *
     * @param k
     * @param n
     * @return
     */
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> result = new ArrayList<>();

        combinationSum3(k, n, 1, new ArrayList<>(), result);
        return result;
    }

    private void combinationSum3(
            int count, int sum, int start, List<Integer> list, List<List<Integer>> result) {
        if (count == 0 && sum == 0) {
            result.add(new ArrayList<>(list));
            return;
        }
        if (count <= 0) {
            return;
        }
        if (sum <= 0) {
            return;
        }

        for (int i = start; i <= 9; i++) {
            list.add(i);
            combinationSum3(count - 1, sum - i, i + 1, list, result);
            list.remove(list.size() - 1);
        }
    }

    @Test
    public void combinationSum4() {
        int[] nums = {1, 2, 3};
        int target = 35;
        logResult(combinationSum4(nums, target));
    }

    /**
     * 377. 组合总和 Ⅳ 给定一个由正整数组成且不存在重复数字的数组，找出和为给定目标正整数的组合的个数。
     *
     * <p>示例:
     *
     * <p>nums = [1, 2, 3] target = 4
     *
     * <p>所有可能的组合为： (1, 1, 1, 1) (1, 1, 2) (1, 2, 1) (1, 3) (2, 1, 1) (2, 2) (3, 1)
     *
     * <p>请注意，顺序不同的序列被视作不同的组合。
     *
     * <p>因此输出为 7。 进阶： 如果给定的数组中含有负数会怎么样？ 问题会产生什么变化？ 我们需要在题目中添加什么限制来允许负数的出现？
     *
     * @param nums
     * @param target
     * @return
     */
    public int combinationSum4(int[] nums, int target) {

        // 背包问题 零钱问题
        int[] dp = new int[target + 1];
        for (int num : nums) {
            if (num <= target) {
                dp[num] = 1;
            }
        }

        for (int i = 1; i <= target; i++) {
            for (int num : nums) {
                if (i - num >= 0) {
                    dp[i] += dp[i - num];
                }
            }
        }
        log.debug("dp :{}", dp);

        return dp[target];
    }

    @Test
    public void subsetsWithDup() {
        int[] nums = {1, 2, 2};
        List<List<Integer>> result = subsetsWithDup(nums);
        log.debug("result:{}", result);
    }

    /**
     * 90. 子集 II 给定一个可能包含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
     *
     * <p>说明：解集不能包含重复的子集。
     *
     * <p>示例:
     *
     * <p>输入: [1,2,2] 输出: [ [2], [1], [1,2,2], [2,2], [1,2], [] ]
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);

        subsetsWithDup(new ArrayList<>(), result, nums, 0);
        return result;
    }

    private void subsetsWithDup(
            List<Integer> list, List<List<Integer>> result, int[] nums, int start) {

        result.add(new ArrayList<>(list));
        int size = list.size();
        for (int i = start; i < nums.length; i++) {
            if (i > start && nums[i] == nums[i - 1]) {
                continue;
            }
            list.add(nums[i]);

            subsetsWithDup(list, result, nums, i + 1);
            list.remove(size);
        }
    }

    @Test
    public void isAdditiveNumber() {
        String num = "101";
        logResult(isAdditiveNumber(num));
    }

    /**
     * 306. 累加数 累加数是一个字符串，组成它的数字可以形成累加序列。
     *
     * <p>一个有效的累加序列必须至少包含 3 个数。除了最开始的两个数以外，字符串中的其他数都等于它之前两个数相加的和。
     *
     * <p>给定一个只包含数字 '0'-'9' 的字符串，编写一个算法来判断给定输入是否是累加数。
     *
     * <p>说明: 累加序列里的数不会以 0 开头，所以不会出现 1, 2, 03 或者 1, 02, 3 的情况。
     *
     * <p>示例 1:
     *
     * <p>输入: "112358" 输出: true 解释: 累加序列为: 1, 1, 2, 3, 5, 8 。1 + 1 = 2, 1 + 2 = 3, 2 + 3 = 5, 3 + 5
     * = 8 示例 2:
     *
     * <p>输入: "199100199" 输出: true 解释: 累加序列为: 1, 99, 100, 199。1 + 99 = 100, 99 + 100 = 199 进阶:
     * 你如何处理一个溢出的过大的整数输入?
     *
     * @param num
     * @return
     */
    public boolean isAdditiveNumber(String num) {
        if (num.length() < 3) return false;

        for (int i = 1; i <= num.length() >> 1; i++) {
            for (int j = i + 1; j <= num.length() - 1; j++) {
                if (isAdditiveNumber(num, 0, i, j)) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * @param num 字符串
     * @param a 第1个数的开始索引
     * @param b 第2个数的开始索引
     * @param c 第3个数的开始索引
     * @return
     */
    private boolean isAdditiveNumber(String num, int a, int b, int c) {
        int len = Math.max(b - a, c - b);
        if ((b - a > 1 && num.charAt(a) == '0') || (c - b > 1 && num.charAt(b) == '0')) {
            return false;
        }

        long num1 = Long.parseLong(num.substring(a, b));
        long num2 = Long.parseLong(num.substring(b, c));

        if (c + len > num.length()) {
            return false;
        }
        long num3 = Long.parseLong(num.substring(c, c + len));
        if (num3 > 0 && num.charAt(c) == '0') {
            return false;
        }
        if (num1 + num2 == num3) {
            if (c + len == num.length()) {
                return true;
            }
            return isAdditiveNumber(num, b, c, c + len);
        }
        if (c + len + 1 > num.length()) {
            return false;
        }
        long num4 = Long.parseLong(num.substring(c, c + len + 1));
        if (num4 > 0 && num.charAt(c) == '0') {
            return false;
        }
        if (num1 + num2 == num4) {
            if (c + len + 1 == num.length()) {
                return true;
            }
            return isAdditiveNumber(num, b, c, c + len + 1);
        }

        return false;
    }

    @Test
    public void findLadders() {
        String beginWord = "hot", endWord = "dog";
        List<String> wordList = Arrays.asList("hot", "dog");

        logResult(findLadders(beginWord, endWord, wordList));
    }

    /**
     * 126. 单词接龙 II
     *
     * <p>给定两个单词（beginWord 和 endWord）和一个字典 wordList，找出所有从 beginWord 到 endWord 的最短转换序列。转换需遵循如下规则：
     *
     * <p>每次转换只能改变一个字母。 转换过程中的中间单词必须是字典中的单词。 说明:
     *
     * <p>如果不存在这样的转换序列，返回一个空列表。 所有单词具有相同的长度。 所有单词只由小写字母组成。 字典中不存在重复的单词。
     *
     * <p>你可以假设 beginWord 和 endWord 是非空的，且二者不相同。 示例 1:
     *
     * <p>输入: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log","cog"]
     *
     * <p>输出: [ ["hit","hot","dot","dog","cog"], ["hit","hot","lot","log","cog"] ] 示例 2:
     *
     * <p>输入: beginWord = "hit" endWord = "cog" wordList = ["hot","dot","dog","lot","log"]
     *
     * <p>输出: []
     *
     * <p>解释: endWord "cog" 不在字典中，所以不存在符合要求的转换序列。
     *
     * @param beginWord
     * @param endWord
     * @param wordList
     * @return
     */
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> result = new ArrayList<>();
        if (!wordList.contains(endWord)) {
            return result;
        }
        Map<String, List<String>> allComboDict = new HashMap<>();
        wordList.forEach(
                word -> {
                    char[] chars = word.toCharArray();
                    for (int i = 0; i < chars.length; i++) {
                        char c = chars[i];
                        chars[i] = '*';
                        List<String> list =
                                allComboDict.computeIfAbsent(
                                        new String(chars), k -> new ArrayList<>());
                        list.add(word);
                        chars[i] = c;
                    }
                });

        Queue<List<String>> listQueue = new LinkedList<>();
        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);

        listQueue.offer(new ArrayList<>(Arrays.asList(beginWord)));
        while (!queue.isEmpty()) {
            int size = queue.size();
            boolean flag = false;
            for (int i = 0; i < size; i++) {
                String word = queue.poll();
                List<String> list = listQueue.poll();
                if (Objects.equals(word, endWord)) {
                    result.add(new ArrayList<>(list));
                    flag = true;
                }
                if (flag) {
                    continue;
                }
                char[] chars = word.toCharArray();
                for (int j = 0; j < chars.length; j++) {
                    char c = chars[j];
                    chars[j] = '*';
                    String key = new String(chars);
                    if (allComboDict.containsKey(key)) {
                        for (String value : allComboDict.get(key)) {
                            if (list.contains(value)) {
                                continue;
                            }
                            // findLadders(word, endWord, allComboDict, list);
                            list.add(value);
                            queue.offer(value);
                            listQueue.offer(new ArrayList<>(list));
                            list.remove(list.size() - 1);
                        }
                    }

                    chars[j] = c;
                }
            }
            if (flag) {

                break;
            }
        }
        return result;
        /*findLadders(beginWord, endWord, allComboDict, new ArrayList<>());
        return findLaddersResult;*/
    }

    private List<List<String>> findLaddersResult = new ArrayList<>();

    private int findLadderMin = Integer.MAX_VALUE;

    /**
     * 超时
     *
     * @param currWord
     * @param endWord
     * @param allComboDict
     * @param list
     */
    private void findLadders(
            String currWord,
            String endWord,
            Map<String, List<String>> allComboDict,
            List<String> list) {
        if (list.size() >= findLadderMin) {
            return;
        }
        list.add(currWord);
        if (Objects.equals(currWord, endWord)) {
            if (list.size() < findLadderMin) {
                findLaddersResult = new ArrayList<>();
                findLaddersResult.add(new ArrayList<>(list));
                findLadderMin = list.size();
            } else if (list.size() == findLadderMin) {
                findLaddersResult.add(new ArrayList<>(list));
            }
            list.remove(list.size() - 1);
            return;
        }

        char[] chars = currWord.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            chars[i] = '*';
            String key = new String(chars);
            if (allComboDict.containsKey(key)) {
                for (String word : allComboDict.get(key)) {
                    if (list.contains(word)) {
                        continue;
                    }
                    findLadders(word, endWord, allComboDict, list);
                }
            }

            chars[i] = c;
        }

        list.remove(list.size() - 1);
    }

    @Test
    public void permutation2() {
        String s = "qwe";
        String[] result = permutation2(s);
        logResult(result);
    }

    /**
     * 面试题 08.07. 无重复字符串的排列组合
     *
     * <p>无重复字符串的排列组合。编写一种方法，计算某字符串的所有排列组合，字符串每个字符均不相同。
     *
     * <p>示例1:
     *
     * <p>输入：S = "qwe" 输出：["qwe", "qew", "wqe", "weq", "ewq", "eqw"] 示例2:
     *
     * <p>输入：S = "ab" 输出：["ab", "ba"] 提示:
     *
     * <p>字符都是英文字母。 字符串长度在[1, 9]之间。
     *
     * @param S
     * @return
     */
    public String[] permutation2(String S) {
        List<String> list = new ArrayList<>();
        char[] chars = S.toCharArray();
        Arrays.sort(chars);
        boolean[] visited = new boolean[chars.length];
        getPermutation(new StringBuilder(), chars, visited, list);
        return list.toArray(new String[0]);
    }

    private void getPermutation(
            StringBuilder sb, char[] chars, boolean[] visited, List<String> list) {
        if (sb.length() == chars.length) {
            log.debug("sb:{}", sb.toString());
            list.add(sb.toString());
            return;
        }
        for (int i = 0; i < chars.length; i++) {

            char c = chars[i];
            if (visited[i]) {
                continue;
            }
            visited[i] = true;
            int len = sb.length();
            sb.append(c);
            getPermutation(sb, chars, visited, list);
            sb.setLength(len);
            visited[i] = false;
        }
    }

    @Test
    public void permutation3() {
        String s = "qqe";
        String[] result = permutation3(s);
        logResult(result);
    }

    /**
     * 面试题 08.08. 有重复字符串的排列组合
     *
     * <p>有重复字符串的排列组合。编写一种方法，计算某字符串的所有排列组合。
     *
     * <p>示例1:
     *
     * <p>输入：S = "qqe" 输出：["eqq","qeq","qqe"] 示例2:
     *
     * <p>输入：S = "ab" 输出：["ab", "ba"] 提示:
     *
     * <p>字符都是英文字母。 字符串长度在[1, 9]之间。
     *
     * @param S
     * @return
     */
    public String[] permutation3(String S) {
        List<String> list = new ArrayList<>();
        char[] chars = S.toCharArray();
        Arrays.sort(chars);
        boolean[] visited = new boolean[chars.length];
        getPermutation3(new StringBuilder(), chars, visited, list);
        return list.toArray(new String[0]);
    }

    private void getPermutation3(
            StringBuilder sb, char[] chars, boolean[] visited, List<String> list) {
        if (sb.length() == chars.length) {
            log.debug("sb:{}", sb.toString());
            list.add(sb.toString());
            return;
        }
        for (int i = 0; i < chars.length; i++) {

            char c = chars[i];
            if (i > 0 && chars[i] == chars[i - 1] && !visited[i - 1]) {
                continue;
            }
            if (visited[i]) {
                continue;
            }
            visited[i] = true;
            int len = sb.length();
            sb.append(c);
            getPermutation3(sb, chars, visited, list);
            sb.setLength(len);
            visited[i] = false;
        }
    }

    @Test
    public void subsets2() {
        int[] nums = {1, 2, 3, 4};
        List<List<Integer>> result = subsets2(nums);
        log.debug("result:{}", result);
    }

    /**
     * 面试题 08.04. 幂集
     *
     * <p>幂集。编写一种方法，返回某集合的所有子集。集合中不包含重复的元素。
     *
     * <p>说明：解集不能包含重复的子集。
     *
     * <p>示例:
     *
     * <p>输入： nums = [1,2,3] 输出： [ [3], [1], [2], [1,2,3], [1,3], [2,3], [1,2], [] ]
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> subsets2(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();

        subset2(nums, new ArrayList<>(), result, 0);
        return result;
    }

    private void subset2(int[] nums, List<Integer> list, List<List<Integer>> result, int start) {
        result.add(new ArrayList<>(list));
        // int size = numList.size();
        for (int i = start; i < nums.length; i++) {
            int num = nums[i];
            //  添加当前元素
            list.add(num);
            subset2(nums, list, result, i + 1);
            // 回溯
            list.remove(list.size() - 1);
        }
    }

    @Test
    public void generateParenthesis3() {
        int n = 4;
        List<String> result = generateParenthesis3(n);
        logResult(result);
    }

    /**
     * 面试题 08.09. 括号
     *
     * <p>括号。设计一种算法，打印n对括号的所有合法的（例如，开闭一一对应）组合。
     *
     * <p>说明：解集不能包含重复的子集。
     *
     * <p>例如，给出 n = 3，生成结果为：
     *
     * <p>[ "((()))", "(()())", "(())()", "()(())", "()()()" ]
     *
     * @param n
     * @return
     */
    public List<String> generateParenthesis3(int n) {
        List<String> result = new ArrayList<>();
        generateParenthesis3(0, 0, n, new StringBuilder(), result);
        return result;
    }

    private void generateParenthesis3(
            int leftCount, int rightCount, int n, StringBuilder sb, List<String> result) {
        if (rightCount == n) {
            result.add(sb.toString());
            return;
        }
        int len = sb.length();
        if (leftCount == n) {
            // 加右括号
            sb.append(")");
            generateParenthesis3(leftCount, rightCount + 1, n, sb, result);
            sb.setLength(len);
        } else if (leftCount == rightCount) {
            // 左括号
            sb.append("(");
            generateParenthesis3(leftCount + 1, rightCount, n, sb, result);
            sb.setLength(len);
        } else {

            // 左括号
            sb.append("(");
            generateParenthesis3(leftCount + 1, rightCount, n, sb, result);
            sb.setLength(len);
            // 加右括号
            sb.append(")");
            generateParenthesis3(leftCount, rightCount + 1, n, sb, result);
            sb.setLength(len);
        }
    }

    @Test
    public void solveNQueens2() {
        int n = 4;
        List<List<String>> result = solveNQueens2(n);
        for (List<String> a : result) {
            logResult(a);
        }
    }

    /**
     * 面试题 08.12. 八皇后
     *
     * <p>设计一种算法，打印 N 皇后在 N × N 棋盘上的各种摆法，其中每个皇后都不同行、不同列，也不在对角线上。这里的“对角线”指的是所有的对角线，不只是平分整个棋盘的那两条对角线。
     *
     * <p>注意：本题相对原题做了扩展
     *
     * <p>示例:
     *
     * <p>输入：4 输出：[[".Q..","...Q","Q...","..Q."],["..Q.","Q...","...Q",".Q.."]] 解释: 4
     * 皇后问题存在如下两个不同的解法。 [ [".Q..", // 解法 1 "...Q", "Q...", "..Q."],
     *
     * <p>["..Q.", // 解法 2 "Q...", "...Q", ".Q.."] ]
     *
     * @param n
     * @return
     */
    public List<List<String>> solveNQueens2(int n) {
        backQueensResult = new ArrayList<>();
        // 回溯算法
        boolean[][] queens = new boolean[n][n];

        boolean[] colFlag = new boolean[n];
        // 正斜向 反斜向
        boolean[] flag1 = new boolean[2 * n - 1];
        boolean[] flag2 = new boolean[2 * n - 1];
        int[] indexs = new int[n];
        Arrays.fill(indexs, -1);
        backQueenChars = new char[n];
        Arrays.fill(backQueenChars, '.');
        backQueens2(n, 0, indexs, colFlag, flag1, flag2);

        /*char[] chars = new char[n];
        Arrays.fill(chars,'.');
        for (boolean[] queen : queens) {
            for (int i = 0; i < n; i++) {
                if (queen[i] == true) {
                    chars[i] = 'Q';
                    result
                    chars[i] = '.';
                }
            }
        }*/

        return backQueensResult;
    }

    List<List<String>> backQueensResult;

    char[] backQueenChars;

    /**
     * 回溯
     *
     * @param n 总行数
     * @param row 当前行
     * @param indexs
     * @param colFlag
     * @param flag1
     * @param flag2
     */
    private void backQueens2(
            int n, int row, int[] indexs, boolean[] colFlag, boolean[] flag1, boolean[] flag2) {
        if (row == n) {
            List<String> list = new ArrayList<>();
            for (int index : indexs) {
                backQueenChars[index] = 'Q';
                list.add(new String(backQueenChars));
                backQueenChars[index] = '.';
            }
            backQueensResult.add(list);
            return;
        }

        for (int col = 0; col < n; col++) {
            if (colFlag[col]) {
                continue;
            }
            if (flag1[row + col]) {
                continue;
            }
            if (flag2[n - 1 - row + col]) {
                continue;
            }
            colFlag[col] = true;
            flag1[row + col] = true;
            flag2[n - 1 - row + col] = true;
            indexs[row] = col;
            backQueens2(n, row + 1, indexs, colFlag, flag1, flag2);
            indexs[row] = -1;
            colFlag[col] = false;
            flag1[row + col] = false;
            flag2[n - 1 - row + col] = false;
        }
    }

    @Test
    public void findSubsequences() {
        int[] nums = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 1, 1, 1, 1, 1};
        logResult(findSubsequences(nums));
    }

    /**
     * 491. 递增子序列
     *
     * <p>给定一个整型数组, 你的任务是找到所有该数组的递增子序列，递增子序列的长度至少是2。
     *
     * <p>示例:
     *
     * <p>输入: [4, 6, 7, 7] 输出: [[4, 6], [4, 7], [4, 6, 7], [4, 6, 7, 7], [6, 7], [6, 7, 7], [7,7],
     * [4,7,7]] 说明:
     *
     * <p>给定数组的长度不会超过15。 数组中的整数范围是 [-100,100]。 给定数组中可能包含重复数字，相等的数字应该被视为递增的一种情况。
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> findSubsequences(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums.length < 2) {
            return result;
        }
        findSubsequencesBack(0, nums, new ArrayList<>(), result);
        return result;
    }

    private void findSubsequencesBack(
            int index, int[] nums, List<Integer> list, List<List<Integer>> result) {
        if (index >= nums.length) {
            if (list.size() >= 2) {
                log.debug("list:{}", list);
                result.add(new ArrayList<>(list));
            }
            return;
        }
        int size = list.size();
        // 把第 index 个元素加进 list 中
        if (list.isEmpty() || list.get(size - 1) <= nums[index]) {
            list.add(nums[index]);
            findSubsequencesBack(index + 1, nums, list, result);
            list.remove(size);
        }
        if (index > 0 && !list.isEmpty() && nums[index] == list.get(size - 1)) {
            return;
        }
        // 不把第 index 个元素加进 list 中
        findSubsequencesBack(index + 1, nums, list, result);
    }

    /**
     * 526. 优美的排列
     *
     * <p>假设有从 1 到 N 的 N 个整数，如果从这 N 个数字中成功构造出一个数组，使得数组的第 i 位 (1 <= i <= N)
     * 满足如下两个条件中的一个，我们就称这个数组为一个优美的排列。条件：
     *
     * <p>第 i 位的数字能被 i 整除 i 能被第 i 位上的数字整除 现在给定一个整数 N，请问可以构造多少个优美的排列？
     *
     * <p>示例1:
     *
     * <p>输入: 2 输出: 2 解释:
     *
     * <p>第 1 个优美的排列是 [1, 2]: 第 1 个位置（i=1）上的数字是1，1能被 i（i=1）整除 第 2 个位置（i=2）上的数字是2，2能被 i（i=2）整除
     *
     * <p>第 2 个优美的排列是 [2, 1]: 第 1 个位置（i=1）上的数字是2，2能被 i（i=1）整除 第 2 个位置（i=2）上的数字是1，i（i=2）能被 1 整除 说明:
     *
     * <p>N 是一个正整数，并且不会超过15。
     *
     * @param N
     * @return
     */
    public int countArrangement(int N) {
        countArrangementResult = 0;
        boolean[] visited = new boolean[N + 1];
        countArrangement(visited, N, 1);
        return countArrangementResult;
    }

    private int countArrangementResult;

    private void countArrangement(boolean[] visited, int N, int index) {
        if (index > N) {
            countArrangementResult++;
        }

        for (int i = 1; i <= N; i++) {
            if (visited[i]) {
                continue;
            }
            if (index % i == 0 || i % index == 0) {
                visited[i] = true;
                countArrangement(visited, N, index + 1);
                visited[i] = false;
            }
        }
    }

    /**
     * 638. 大礼包
     *
     * <p>在LeetCode商店中， 有许多在售的物品。
     *
     * <p>然而，也有一些大礼包，每个大礼包以优惠的价格捆绑销售一组物品。
     *
     * <p>现给定每个物品的价格，每个大礼包包含物品的清单，以及待购物品清单。请输出确切完成待购清单的最低花费。
     *
     * <p>每个大礼包的由一个数组中的一组数据描述，最后一个数字代表大礼包的价格，其他数字分别表示内含的其他种类物品的数量。
     *
     * <p>任意大礼包可无限次购买。
     *
     * <p>示例 1:
     *
     * <p>输入: [2,5], [[3,0,5],[1,2,10]], [3,2] 输出: 14 解释: 有A和B两种物品，价格分别为¥2和¥5。
     * 大礼包1，你可以以¥5的价格购买3A和0B。 大礼包2， 你可以以¥10的价格购买1A和2B。 你需要购买3个A和2个B，
     * 所以你付了¥10购买了1A和2B（大礼包2），以及¥4购买2A。 示例 2:
     *
     * <p>输入: [2,3,4], [[1,1,0,4],[2,2,1,9]], [1,2,1] 输出: 11 解释: A，B，C的价格分别为¥2，¥3，¥4.
     * 你可以用¥4购买1A和1B，也可以用¥9购买2A，2B和1C。 你需要买1A，2B和1C，所以你付了¥4买了1A和1B（大礼包1），以及¥3购买1B， ¥4购买1C。
     * 你不可以购买超出待购清单的物品，尽管购买大礼包2更加便宜。 说明:
     *
     * <p>最多6种物品， 100种大礼包。 每种物品，你最多只需要购买6个。 你不可以购买超出待购清单的物品，即使更便宜。
     *
     * @param price
     * @param special
     * @param needs
     * @return
     */
    public int shoppingOffers(
            List<Integer> price, List<List<Integer>> special, List<Integer> needs) {
        minShoppingOffersResult = Integer.MAX_VALUE;
        shoppingOffersSum = 0;
        backShopping(0, price, special, needs);
        return minShoppingOffersResult;
    }

    private int minShoppingOffersResult;

    private int shoppingOffersSum;

    private void backShopping(
            int start, List<Integer> price, List<List<Integer>> special, List<Integer> needs) {
        if (!validNeeds(needs)) {
            return;
        }

        int tempSum = shoppingOffersSum;
        for (int i = 0; i < needs.size(); ++i) {
            shoppingOffersSum += needs.get(i) * price.get(i);
        }
        minShoppingOffersResult = Math.min(minShoppingOffersResult, shoppingOffersSum);
        shoppingOffersSum = tempSum;
        for (int i = start; i < special.size(); ++i) {
            List<Integer> curSpecial = special.get(i);
            int specialNum = calSpecialNum(curSpecial, needs);
            if (specialNum != 0) {
                for (int j = 1; j <= specialNum; ++j) {
                    List<Integer> tempNeeds = new ArrayList<>(needs);
                    for (int k = 0; k < needs.size(); ++k)
                        needs.set(k, needs.get(k) - curSpecial.get(k) * j);

                    shoppingOffersSum += curSpecial.get(needs.size()) * j;
                    backShopping(i + 1, price, special, needs);
                    needs = tempNeeds;
                    shoppingOffersSum = tempSum;
                }
            }
        }
    }

    private int calSpecialNum(List<Integer> item, List<Integer> needs) {
        int max = Integer.MAX_VALUE;
        for (int i = 0; i < needs.size(); ++i) {
            int curNeed = needs.get(i), curItem = item.get(i);
            if (curNeed < curItem) return 0;

            if (curItem != 0) max = Math.min(curNeed / curItem, max);
        }
        return max;
    }

    private boolean needsZero(List<Integer> needs) {
        for (int need : needs) {
            if (need != 0) {
                return false;
            }
        }
        return true;
    }

    private boolean validNeeds(List<Integer> needs) {
        for (int need : needs) {
            if (need < 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * 698. 划分为k个相等的子集
     *
     * <p>给定一个整数数组 nums 和一个正整数 k，找出是否有可能把这个数组分成 k 个非空子集，其总和都相等。
     *
     * <p>示例 1：
     *
     * <p>输入： nums = [4, 3, 2, 3, 5, 2, 1], k = 4 输出： True 说明： 有可能将其分成 4
     * 个子集（5），（1,4），（2,3），（2,3）等于总和。
     *
     * <p>提示：
     *
     * <p>1 <= k <= len(nums) <= 16 0 < nums[i] < 10000
     *
     * @param nums
     * @param k
     * @return
     */
    public boolean canPartitionKSubsets(int[] nums, int k) {
        if (k == 0) {
            return false;
        }
        int sum = 0, max = 0;
        for (int num : nums) {
            sum += num;
            max = Math.max(max, num);
        }
        if (sum % k != 0) {
            return false;
        }
        int target = sum / k;
        if (max > target) {
            return false;
        }
        return backPartition(nums, k, target, 0, 0, new boolean[nums.length]);
    }

    private boolean backPartition(
            int[] nums, int k, int target, int cur, int start, boolean[] visited) {
        if (k == 0) {
            return true;
        }
        if (cur == target) {
            // 构建下一个集合
            return backPartition(nums, k - 1, target, 0, 0, visited);
        }
        for (int i = start; i < nums.length; i++) {
            if (visited[i]) {
                continue;
            }
            if (cur + nums[i] > target) {
                continue;
            }
            visited[i] = true;
            if (backPartition(nums, k, target, cur + nums[i], i + 1, visited)) {
                return true;
            }
            visited[i] = false;
        }

        return false;
    }

    /**
     * 1415. 长度为 n 的开心字符串中字典序第 k 小的字符串
     *
     * <p>一个 「开心字符串」定义为：
     *
     * <p>仅包含小写字母 ['a', 'b', 'c']. 对所有在 1 到 s.length - 1 之间的 i ，满足 s[i] != s[i + 1] （字符串的下标从 1 开始）。
     * 比方说，字符串 "abc"，"ac"，"b" 和 "abcbabcbcb" 都是开心字符串，但是 "aa"，"baa" 和 "ababbc" 都不是开心字符串。
     *
     * <p>给你两个整数 n 和 k ，你需要将长度为 n 的所有开心字符串按字典序排序。
     *
     * <p>请你返回排序后的第 k 个开心字符串，如果长度为 n 的开心字符串少于 k 个，那么请你返回 空字符串 。
     *
     * <p>示例 1：
     *
     * <p>输入：n = 1, k = 3 输出："c" 解释：列表 ["a", "b", "c"] 包含了所有长度为 1 的开心字符串。按照字典序排序后第三个字符串为 "c" 。 示例 2：
     *
     * <p>输入：n = 1, k = 4 输出："" 解释：长度为 1 的开心字符串只有 3 个。 示例 3：
     *
     * <p>输入：n = 3, k = 9 输出："cab" 解释：长度为 3 的开心字符串总共有 12 个 ["aba", "abc", "aca", "acb", "bab",
     * "bac", "bca", "bcb", "cab", "cac", "cba", "cbc"] 。第 9 个字符串为 "cab" 示例 4：
     *
     * <p>输入：n = 2, k = 7 输出："" 示例 5：
     *
     * <p>输入：n = 10, k = 100 输出："abacbabacb"
     *
     * <p>提示：
     *
     * <p>1 <= n <= 10 1 <= k <= 100
     *
     * @param n
     * @param k
     * @return
     */
    public String getHappyString(int n, int k) {
        // 全排列问题
        if (total(n) < k) return "";
        char[] result = new char[n];
        int idx = 0;
        while (idx < n) {
            char pre = idx == 0 ? '0' : result[idx - 1];
            result[idx] = pre == 'a' ? 'b' : 'a';
            int len = 1 << (n - idx - 1);
            while (k > len) {
                result[idx] = (char) (result[idx] + 1);
                if (result[idx] == pre) {
                    result[idx] = (char) (result[idx] + 1);
                }
                k -= len;
            }
            ++idx;
        }
        return new String(result);
    }

    int total(int n) {
        return 3 * (1 << (n - 1));
    }

    /**
     * 1079. 活字印刷
     *
     * <p>你有一套活字字模 tiles，其中每个字模上都刻有一个字母 tiles[i]。返回你可以印出的非空字母序列的数目。
     *
     * <p>注意：本题中，每个活字字模只能使用一次。
     *
     * <p>示例 1：
     *
     * <p>输入："AAB" 输出：8 解释：可能的序列为 "A", "B", "AA", "AB", "BA", "AAB", "ABA", "BAA"。 示例 2：
     *
     * <p>输入："AAABBC" 输出：188
     *
     * <p>提示：
     *
     * <p>1 <= tiles.length <= 7 tiles 由大写英文字母组成
     *
     * @param tiles
     * @return
     */
    public int numTilePossibilities(String tiles) {
        if (tiles.length() == 0) {
            return 0;
        }
        char[] tilesChar = tiles.toCharArray();
        Arrays.sort(tilesChar);
        numTilePossibilitiesBack(tilesChar, 0, new boolean[tiles.length()]);
        return numTileResult;
    }

    private int numTileResult;

    private void numTilePossibilitiesBack(char[] tilesChar, int idx, boolean[] visited) {
        if (idx == tilesChar.length) {
            return;
        }
        for (int i = 0; i < tilesChar.length; i++) {
            if (visited[i]) {
                continue;
            }
            if (i > 0 && tilesChar[i] == tilesChar[i - 1] && !visited[i - 1]) {
                continue;
            }
            numTileResult++;
            visited[i] = true;
            numTilePossibilitiesBack(tilesChar, idx + 1, visited);
            visited[i] = false;
        }
    }

    @Test
    public void maxLength() {
        List<String> arr = Arrays.asList("un", "iq", "ue");
        logResult(maxLength(arr));
    }

    int maxLengthResult = 0;

    /**
     * 1239. 串联字符串的最大长度
     *
     * <p>给定一个字符串数组 arr，字符串 s 是将 arr 某一子序列字符串连接所得的字符串，如果 s 中的每一个字符都只出现过一次，那么它就是一个可行解。
     *
     * <p>请返回所有可行解 s 中最长长度。
     *
     * <p>示例 1：
     *
     * <p>输入：arr = ["un","iq","ue"] 输出：4 解释：所有可能的串联组合是 "","un","iq","ue","uniq" 和 "ique"，最大长度为 4。 示例
     * 2：
     *
     * <p>输入：arr = ["cha","r","act","ers"] 输出：6 解释：可能的解答有 "chaers" 和 "acters"。 示例 3：
     *
     * <p>输入：arr = ["abcdefghijklmnopqrstuvwxyz"] 输出：26
     *
     * <p>提示：
     *
     * <p>1 <= arr.length <= 16 1 <= arr[i].length <= 26 arr[i] 中只含有小写英文字母
     *
     * @param arr
     * @return
     */
    public int maxLength(List<String> arr) {

        List<Integer> list = new ArrayList<>();
        for (String str : arr) {
            int num = 0;
            for (char c : str.toCharArray()) {
                int n = 1 << (c - 'a');
                if ((n & num) > 0) {
                    num = 0;
                    break;
                }
                num |= n;
            }
            if (num > 0) {
                list.add(num);
            }
        }

        maxLength(list, 0, 0);
        return maxLengthResult;
    }

    private void maxLength(List<Integer> list, int num, int index) {
        if (index == list.size()) {
            return;
        }
        int cur = list.get(index);
        int newNum = num ^ cur;
        if (newNum != (num + cur)) {
            maxLength(list, num, index + 1);
            return;
        }
        maxLengthResult = Math.max(maxLengthResult, Integer.bitCount(newNum));
        // 选择index
        maxLength(list, newNum, index + 1);
        // 不选择index
        maxLength(list, num, index + 1);
    }

    @Test
    public void circularPermutation() {
        int n = 3, start = 2;
        logResult(circularPermutation(n, start));
    }

    /**
     * 1238. 循环码排列
     *
     * <p>给你两个整数 n 和 start。你的任务是返回任意 (0,1,2,,...,2^n-1) 的排列 p，并且满足：
     *
     * <p>p[0] = start p[i] 和 p[i+1] 的二进制表示形式只有一位不同 p[0] 和 p[2^n -1] 的二进制表示形式也只有一位不同
     *
     * <p>示例 1：
     *
     * <p>输入：n = 2, start = 3 输出：[3,2,0,1] 解释：这个排列的二进制表示是 (11,10,00,01) 所有的相邻元素都有一位是不同的，另一个有效的排列是
     * [3,1,0,2] 示例 2：
     *
     * <p>输出：n = 3, start = 2 输出：[2,6,7,5,4,0,1,3] 解释：这个排列的二进制表示是 (010,110,111,101,100,000,001,011)
     *
     * <p>提示：
     *
     * <p>1 <= n <= 16 0 <= start < 2^n
     *
     * @param n
     * @param start
     * @return
     */
    public List<Integer> circularPermutation(int n, int start) {
        // 先生成格雷码, 然后选择数组

        int startIndex = 0;
        List<Integer> list = new ArrayList<>();
        list.add(0);
        int head = 1;
        // 全排列
        for (int i = 0; i < n; i++) {
            for (int j = list.size() - 1; j >= 0; j--) {
                int num = head | list.get(j);
                if (num == start) {
                    startIndex = list.size();
                }
                list.add(num);
            }
            head <<= 1;
        }

        List<Integer> result = new ArrayList<>();
        for (int i = startIndex; i < list.size(); i++) {
            result.add(list.get(i));
        }
        for (int i = 0; i < startIndex; i++) {
            result.add(list.get(i));
        }

        return result;
    }

    /**
     * 1291. 顺次数
     *
     * <p>我们定义「顺次数」为：每一位上的数字都比前一位上的数字大 1 的整数。
     *
     * <p>请你返回由 [low, high] 范围内所有顺次数组成的 有序 列表（从小到大排序）。
     *
     * <p>示例 1：
     *
     * <p>输出：low = 100, high = 300 输出：[123,234] 示例 2：
     *
     * <p>输出：low = 1000, high = 13000 输出：[1234,2345,3456,4567,5678,6789,12345]
     *
     * <p>提示：
     *
     * <p>10 <= low <= high <= 10^9
     *
     * @param low
     * @param high
     * @return
     */
    public List<Integer> sequentialDigits(int low, int high) {
        /*int[] nums = {
            12, 23, 34, 45, 56, 67, 78, 89, 123, 234, 345, 456, 567, 678, 789, 1234, 2345, 3456,
            4567, 5678, 6789, 12345, 23456, 34567, 45678, 56789, 123456, 234567, 345678, 456789,
            1234567, 2345678, 3456789, 12345678, 23456789, 123456789
        };
        int start = 0, end = nums.length - 1;
        List<Integer> result = new ArrayList<>();
        while (start < nums.length && nums[start] < low) {
            start++;
        }

        while (end >= 0 && nums[end] > high) {
            end--;
        }
        for (int i = start; i <= end; i++) {
            result.add(nums[i]);
        }*/
        List<Integer> result = new ArrayList<>();

        for (int i = 1; i < 9; i++) {
            int num = i;
            for (int j = i + 1; j <= 9; j++) {
                num = num * 10 + j;
                if (num >= low && num <= high) {
                    result.add(num);
                }
            }
        }
        Collections.sort(result);
        return result;
        /*while(a < t.size() && t[a] < low)
            a++;
        while(b >=0 && t[b] > high)
            b--;
        if(a <= b)
            return vector<int>(t.begin() + a, t.begin() + b + 1);
        else
        return vector<int>();*/
        /*List<Integer> result = new ArrayList<>();

        return result;*/
    }

    int maxUniqueSplitResult = 0;

    @Test
    public void maxUniqueSplit() {
        String s = "aa";
        logResult(maxUniqueSplit(s));
    }

    /**
     * 5520. 拆分字符串使唯一子字符串的数目最大
     *
     * <p>给你一个字符串 s ，请你拆分该字符串，并返回拆分后唯一子字符串的最大数目。
     *
     * <p>字符串 s 拆分后可以得到若干 非空子字符串 ，这些子字符串连接后应当能够还原为原字符串。但是拆分出来的每个子字符串都必须是 唯一的 。
     *
     * <p>注意：子字符串 是字符串中的一个连续字符序列。
     *
     * <p>示例 1：
     *
     * <p>输入：s = "ababccc" 输出：5 解释：一种最大拆分方法为 ['a', 'b', 'ab', 'c', 'cc'] 。像 ['a', 'b', 'a', 'b',
     * 'c', 'cc'] 这样拆分不满足题目要求，因为其中的 'a' 和 'b' 都出现了不止一次。 示例 2：
     *
     * <p>输入：s = "aba" 输出：2 解释：一种最大拆分方法为 ['a', 'ba'] 。 示例 3：
     *
     * <p>输入：s = "aa" 输出：1 解释：无法进一步拆分字符串。
     *
     * <p>提示：
     *
     * <p>1 <= s.length <= 16
     *
     * <p>s 仅包含小写英文字母
     *
     * @param s
     * @return
     */
    public int maxUniqueSplit(String s) {
        backUniqueSplit(s, 0, new HashSet<>());
        return maxUniqueSplitResult;
    }

    private void backUniqueSplit(String s, int start, Set<String> set) {
        // 最大子数组数量
        if (set.size() + s.length() - start <= maxUniqueSplitResult) {
            return;
        }

        if (start == s.length()) {
            maxUniqueSplitResult = Math.max(maxUniqueSplitResult, set.size());
            return;
        }
        for (int i = start; i < s.length(); i++) {
            String str = s.substring(start, i + 1);
            if (set.contains(str)) {
                continue;
            }
            set.add(str);
            backUniqueSplit(s, i + 1, set);
            set.remove(str);
        }
    }

    @Test
    public void allPathsSourceTargetTest() {
        int[][] graph = {{1, 2}, {3}, {3}, {}};
        logResult(allPathsSourceTarget(graph));
    }

    List<List<Integer>> allPathsSourceTargetResult;
    /**
     * 797. 所有可能的路径
     *
     * <p>给一个有 n 个结点的有向无环图，找到所有从 0 到 n-1 的路径并输出（不要求按顺序）
     *
     * <p>二维数组的第 i 个数组中的单元都表示有向图中 i 号结点所能到达的下一些结点（译者注：有向图是有方向的，即规定了a→b你就不能从b→a）空就是没有下一个结点了。
     *
     * <p>示例: 输入: [[1,2], [3], [3], []] 输出: [[0,1,3],[0,2,3]] 解释: 图是这样的: 0--->1 | | v v 2--->3
     * 这有两条路: 0 -> 1 -> 3 和 0 -> 2 -> 3. 提示:
     *
     * <p>结点的数量会在范围 [2, 15] 内。 你可以把路径以任意顺序输出，但在路径内的结点的顺序必须保证。
     *
     * @param graph
     * @return
     */
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        allPathsSourceTargetResult = new ArrayList<>();
        int n = graph.length;
        boolean[] visited = new boolean[n];
        visited[0] = true;
        List<Integer> list = new ArrayList<>();
        list.add(0);
        allPathsSourceTarget(0, n - 1, list, visited, graph);
        return allPathsSourceTargetResult;
    }

    private void allPathsSourceTarget(
            int start, int end, List<Integer> list, boolean[] visited, int[][] graph) {

        if (start == end) {
            allPathsSourceTargetResult.add(new ArrayList<>(list));
            return;
        }

        int[] nextList = graph[start];
        if (nextList.length == 0) {
            return;
        }
        int size = list.size();
        for (int next : nextList) {
            if (visited[next]) {
                continue;
            }
            visited[next] = true;
            list.add(next);
            allPathsSourceTarget(next, end, list, visited, graph);
            visited[next] = false;
            list.remove(size);
        }
    }

    @Test
    public void splitIntoFibonacci() {
        String s = "2820022842865610841740282445647388119521934031292";
        logResult(splitIntoFibonacci(s));
    }

    /**
     * 842. 将数组拆分成斐波那契序列
     *
     * <p>给定一个数字字符串 S，比如 S = "123456579"，我们可以将它分成斐波那契式的序列 [123, 456, 579]。
     *
     * <p>形式上，斐波那契式序列是一个非负整数列表 F，且满足：
     *
     * <p>0 <= F[i] <= 2^31 - 1，（也就是说，每个整数都符合 32 位有符号整数类型）； F.length >= 3； 对于所有的0 <= i < F.length -
     * 2，都有 F[i] + F[i+1] = F[i+2] 成立。 另外，请注意，将字符串拆分成小块时，每个块的数字一定不要以零开头，除非这个块是数字 0 本身。
     *
     * <p>返回从 S 拆分出来的任意一组斐波那契式的序列块，如果不能拆分则返回 []。
     *
     * <p>示例 1：
     *
     * <p>输入："123456579" 输出：[123,456,579] 示例 2：
     *
     * <p>输入: "11235813" 输出: [1,1,2,3,5,8,13] 示例 3：
     *
     * <p>输入: "112358130" 输出: [] 解释: 这项任务无法完成。 示例 4：
     *
     * <p>输入："0123" 输出：[] 解释：每个块的数字不能以零开头，因此 "01"，"2"，"3" 不是有效答案。 示例 5：
     *
     * <p>输入: "1101111" 输出: [110, 1, 111] 解释: 输出 [11,0,11,11] 也同样被接受。
     *
     * <p>提示：
     *
     * <p>1 <= S.length <= 200 字符串 S 中只含有数字。
     *
     * @param S
     * @return
     */
    public List<Integer> splitIntoFibonacci(String S) {
        List<Integer> result = new ArrayList<>();
        int len = S.length();
        if (len < 3) {
            return result;
        }

        for (int i = 1; i <= Math.min(len >> 1, 10); i++) {
            if (i > 1 && S.charAt(0) == '0') {
                break;
            }
            long num1 = Long.parseLong(S.substring(0, i));
            if (num1 > Integer.MAX_VALUE) {
                break;
            }

            for (int j = i + 1; j <= len - 1; j++) {
                if (S.charAt(i) == '0' && j - i > 1) {
                    break;
                }
                long num2 = Long.parseLong(S.substring(i, j));
                if (num2 > Integer.MAX_VALUE) {
                    break;
                }
                result = new ArrayList<>();
                int a = Math.toIntExact(num1), b = Math.toIntExact(num2);
                result.add(a);
                result.add(b);
                if (splitIntoFibonacci(S.substring(j), a, b, result)) {
                    return result;
                }
            }
        }

        return new ArrayList<>();
    }

    /**
     * @param num 字符串
     * @param num1 第1个数
     * @param num2 第2个数
     * @param list
     * @return
     */
    private boolean splitIntoFibonacci(String num, int num1, int num2, List<Integer> list) {
        if (num.length() == 0) {
            return true;
        }
        int sum = num1 + num2;
        if (sum < 0) {
            return false;
        }
        String next = String.valueOf(sum);
        if (!num.startsWith(next)) {
            return false;
        }
        list.add(sum);
        return splitIntoFibonacci(num.substring(next.length()), num2, sum, list);
    }

    /**
     * 282. 给表达式添加运算符
     *
     * <p>给定一个仅包含数字 0-9 的字符串和一个目标值，在数字之间添加二元运算符（不是一元）+、- 或 * ，返回所有能够得到目标值的表达式。
     *
     * <p>示例 1:
     *
     * <p>输入: num = "123", target = 6 输出: ["1+2+3", "1*2*3"] 示例 2:
     *
     * <p>输入: num = "232", target = 8 输出: ["2*3+2", "2+3*2"] 示例 3:
     *
     * <p>输入: num = "105", target = 5 输出: ["1*0+5","10-5"] 示例 4:
     *
     * <p>输入: num = "00", target = 0 输出: ["0+0", "0-0", "0*0"] 示例 5:
     *
     * <p>输入: num = "3456237490", target = 9191 输出: []
     *
     * @param num
     * @param target
     * @return
     */
    public List<String> addOperators(String num, int target) {
        operatorResult = new ArrayList<>();
        this.operatorNum = num;
        this.target = target;
        backOperators(0, 0, 0, new StringBuilder());
        return operatorResult;
    }

    private String operatorNum;

    private int target;

    private List<String> operatorResult;

    private void backOperators(int start, long num, long lastNum, StringBuilder sb) {
        if (start == operatorNum.length()) {
            if (num == target) {
                operatorResult.add(sb.toString());
            }
            return;
        }
        for (int i = start; i < operatorNum.length(); i++) {
            char c = operatorNum.charAt(i);
            if (i > start && operatorNum.charAt(start) == '0') {
                break;
            }
            long cur = Long.parseLong(operatorNum.substring(start, i + 1));
            int len = sb.length();
            if (start == 0) {
                backOperators(i + 1, cur, cur, sb.append(cur));
                sb.setLength(len);
            } else {
                // +
                backOperators(i + 1, num + cur, cur, sb.append("+").append(cur));
                sb.setLength(len);
                // -
                backOperators(i + 1, num - cur, -cur, sb.append("-").append(cur));
                sb.setLength(len);
                // *
                backOperators(
                        i + 1,
                        num - lastNum + lastNum * cur,
                        lastNum * cur,
                        sb.append("*").append(cur));
                sb.setLength(len);
            }
        }
    }
}
