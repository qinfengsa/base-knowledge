package com.qinfengsa.structure.leetcode;

import static com.qinfengsa.structure.util.LogUtils.logResult;

import com.qinfengsa.base.NestedInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Stack;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @author: qinfengsa
 * @date: 2019/5/25 07:33
 */
@Slf4j
public class StackTest {

    @Test
    public void dailyTemperatures() {
        int[] nums = {73, 74, 75, 71, 69, 72, 76, 73};
        int[] result = dailyTemperatures(nums);
        log.debug("result:{}", result);
    }

    /**
     * 每日温度 根据每日 气温 列表，请重新生成一个列表，对应位置的输入是你需要再等待多久温度才会升高的天数。如果之后都不会升高，请输入 0 来代替。
     *
     * <p>例如，给定一个列表 temperatures = [73, 74, 75, 71, 69, 72, 76, 73]，你的输出应该是 [1, 1, 4, 2, 1, 1, 0,
     * 0]。
     *
     * <p>提示：气温 列表长度的范围是 [1, 30000]。每个气温的值的都是 [30, 100] 范围内的整数。
     *
     * @param T
     * @return
     */
    public int[] dailyTemperatures(int[] T) {
        int len = T.length;
        int[] result = new int[len];
        Stack<Integer> stack = new Stack<>();

        Stack<Integer> indexStack = new Stack<>();

        for (int i = 0; i < len; i++) {

            // 当前温度大于栈顶温度，出栈，并且计算天数差 （下标之差）
            int num = T[i];
            while (!stack.isEmpty() && stack.peek() < num) {
                stack.pop();
                int index = indexStack.pop();
                result[index] = i - index;
            }
            stack.push(num);
            indexStack.push(i);
        }

        return result;
    }

    @Test
    public void evalRPN() {
        String[] tokens = {"10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"};
        int result = evalRPN(tokens);
        log.debug("result:{}", result);
    }

    /**
     * 逆波兰表达式求值 根据逆波兰表示法，求表达式的值。
     *
     * <p>有效的运算符包括 +, -, *, / 。每个运算对象可以是整数，也可以是另一个逆波兰表达式。
     *
     * <p>说明：
     *
     * <p>整数除法只保留整数部分。 给定逆波兰表达式总是有效的。换句话说，表达式总会得出有效数值且不存在除数为 0 的情况。 示例 1：
     *
     * <p>输入: ["2", "1", "+", "3", "*"] 输出: 9 解释: ((2 + 1) * 3) = 9 示例 2：
     *
     * <p>输入: ["4", "13", "5", "/", "+"] 输出: 6 解释: (4 + (13 / 5)) = 6 示例 3：
     *
     * <p>输入: ["10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"] 输出: 22 解释: ((10
     * * (6 / ((9 + 3) * -11))) + 17) + 5 = ((10 * (6 / (12 * -11))) + 17) + 5 = ((10 * (6 / -132))
     * + 17) + 5 = ((10 * 0) + 17) + 5 = (0 + 17) + 5 = 17 + 5 = 22
     *
     * @param tokens
     * @return
     */
    public int evalRPN(String[] tokens) {
        int result = 0;
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < tokens.length; i++) {
            String s = tokens[i];
            // 如果是计算符号，取栈顶两个元素运算
            if (isCalculate(s)) {
                int b = stack.pop();
                int a = stack.pop();
                result = calculateNum(a, b, s);
                stack.push(result);
            } else {
                stack.push(Integer.valueOf(s));
            }
        }
        if (!stack.isEmpty()) {
            result = stack.pop();
        }

        return result;
    }

    private boolean isCalculate(String s) {
        if (s.length() != 1) {
            return false;
        }
        char c = s.charAt(0);
        switch (c) {
            case '+':
            case '-':
            case '*':
            case '/':
                return true;
            default:
                break;
        }
        return false;
    }

    /**
     * 计算
     *
     * @param a
     * @param b
     * @param s
     * @return
     */
    private int calculateNum(int a, int b, String s) {
        char c = s.charAt(0);
        int reuslt = 0;
        switch (c) {
            case '+':
                reuslt = a + b;
                break;
            case '-':
                reuslt = a - b;
                break;
            case '*':
                reuslt = a * b;
                break;
            case '/':
                reuslt = a / b;
                break;
            default:
                break;
        }
        return reuslt;
    }

    @Test
    public void longestValidParentheses() {
        String s = "()(())";
        int result = longestValidParentheses(s);
        log.debug("result:{}", result);
    }

    /**
     * 给定一个只包含 '(' 和 ')' 的字符串，找出最长的包含有效括号的子串的长度。
     *
     * <p>示例 1:
     *
     * <p>输入: "(()" 输出: 2 解释: 最长有效括号子串为 "()" 示例 2:
     *
     * <p>输入: ")()())" 输出: 4 解释: 最长有效括号子串为 "()()"
     *
     * @param s
     * @return
     */
    public int longestValidParentheses(String s) {

        Deque<Integer> list = new LinkedList<>();

        int start = 0;
        int result = 0;

        for (int i = 0; i < s.length(); ++i) {
            char c = s.charAt(i);
            if (c == '(') {
                list.push(i);
            } else if (c == ')') {
                if (list.isEmpty()) {
                    start = i + 1;
                } else {
                    // 不为空，栈中的元素肯定是'('
                    list.pop();
                    result =
                            list.isEmpty()
                                    ? Math.max(result, i - start + 1)
                                    : Math.max(result, i - list.peek());
                }
            }
        }

        return result;
    }

    @Test
    public void decodeString() {
        String s = "3[a2[c]]";
        String result = decodeString(s);
        log.debug("result :{}", result);
    }

    /**
     * 394 字符串解码 给定一个经过编码的字符串，返回它解码后的字符串。
     *
     * <p>编码规则为: k[encoded_string]，表示其中方括号内部的 encoded_string 正好重复 k 次。注意 k 保证为正整数。
     *
     * <p>你可以认为输入字符串总是有效的；输入字符串中没有额外的空格，且输入的方括号总是符合格式要求的。
     *
     * <p>此外，你可以认为原始数据不包含数字，所有的数字只表示重复的次数 k ，例如不会出现像 3a 或 2[4] 的输入。
     *
     * <p>示例:
     *
     * <p>s = "3[a]2[bc]", 返回 "aaabcbc". s = "3[a2[c]]", 返回 "accaccacc". s = "2[abc]3[cd]ef", 返回
     * "abcabccdcdcdef".
     *
     * @param s
     * @return
     */
    public String decodeString(String s) {
        StringBuilder result = new StringBuilder();
        Deque<Integer> numStack = new LinkedList<>();
        Deque<String> strStack = new LinkedList<>();

        int num = 0;
        for (char c : s.toCharArray()) {
            if (c == '[') {
                numStack.addLast(num);
                strStack.addLast(result.toString());
                num = 0;
                result.setLength(0);
            } else if (c == ']') {
                int count = numStack.removeLast();
                StringBuilder tmp = new StringBuilder();
                // 出栈
                for (int i = 0; i < count; i++) {
                    tmp.append(result);
                }

                result = new StringBuilder(strStack.removeLast()).append(tmp);
            } else if (isNumber(c)) {
                num = num * 10 + (c - '0');
            } else {
                result.append(c);
            }
        }
        return result.toString();
    }

    public String decodeString2(String s) {

        StringBuilder result = new StringBuilder();
        // 思路 构建两个栈 数量栈 和 字符栈
        Deque<Integer> numStack = new LinkedList<>();
        Deque<String> strStack = new LinkedList<>();

        int num = 0;
        for (Character c : s.toCharArray()) {
            if (c == '[') {
                numStack.addLast(num);
                strStack.addLast(result.toString());
                num = 0;
                result = new StringBuilder();
            } else if (c == ']') {
                StringBuilder tmp = new StringBuilder();
                int count = numStack.removeLast();
                for (int i = 0; i < count; i++) {
                    tmp.append(result);
                }
                result = new StringBuilder(strStack.removeLast() + tmp);
            } else if (c >= '0' && c <= '9') {
                num = num * 10 + Integer.parseInt(c + "");
            } else result.append(c);
        }
        /**
         * for (int i = 0; i < s.length(); i++) { char c = s.charAt(i); if (c == '[') { Integer num
         * = Integer.valueOf(s.substring(numStart,i)); numStack.push(num); numStart = -1; strStart =
         * i + 1; } else if (c == ']') { if (strStart != -1) { String str = s.substring(strStart,i);
         * strStack.push(str); strStart = -1; }
         *
         * <p>int num = numStack.pop(); StringBuilder sub = new StringBuilder(); // 循环num次， String
         * top = strStack.pop(); for (int j = 0; j < num; j++) { sub.append(top); } // 如果 strStack
         * 为空, 把 sub 加到 result 上 if (strStack.isEmpty()) { result.append(sub); } else { // strStack
         * 不为空 String str1 = strStack.pop(); sub.insert(0,str1); strStack.push(sub.toString()); }
         *
         * <p>} else if (isNumber(c)) { if (numStart == -1) { numStart = i; } if (strStart != -1) {
         * String str = s.substring(strStart,i); strStack.push(str); strStart = -1; } } else { if
         * (numStack.isEmpty()) { result.append(c); } else if (strStack.size() == numStack.size() )
         * { StringBuilder pop = new StringBuilder(strStack.pop()); pop.append(c);
         * strStack.push(pop.toString()); } } }
         */
        return result.toString();
    }

    private boolean isNumber(char c) {
        switch (c) {
            case '0':
            case '1':
            case '2':
            case '3':
            case '4':
            case '5':
            case '6':
            case '7':
            case '8':
            case '9':
                return true;
        }
        return false;
    }

    @Test
    public void calPoints() {
        String[] ops = {"5", "-2", "4", "C", "D", "9", "+", "+"};
        logResult(calPoints(ops));
    }

    /**
     * 682. 棒球比赛 你现在是棒球比赛记录员。 给定一个字符串列表，每个字符串可以是以下四种类型之一： 1.整数（一轮的得分）：直接表示您在本轮中获得的积分数。 2.
     * "+"（一轮的得分）：表示本轮获得的得分是前两轮有效 回合得分的总和。 3. "D"（一轮的得分）：表示本轮获得的得分是前一轮有效 回合得分的两倍。 4.
     * "C"（一个操作，这不是一个回合的分数）：表示您获得的最后一个有效 回合的分数是无效的，应该被移除。
     *
     * <p>每一轮的操作都是永久性的，可能会对前一轮和后一轮产生影响。 你需要返回你在所有回合中得分的总和。
     *
     * <p>示例 1:
     *
     * <p>输入: ["5","2","C","D","+"] 输出: 30 解释: 第1轮：你可以得到5分。总和是：5。 第2轮：你可以得到2分。总和是：7。
     * 操作1：第2轮的数据无效。总和是：5。 第3轮：你可以得到10分（第2轮的数据已被删除）。总数是：15。 第4轮：你可以得到5 + 10 = 15分。总数是：30。 示例 2:
     *
     * <p>输入: ["5","-2","4","C","D","9","+","+"] 输出: 27 解释: 第1轮：你可以得到5分。总和是：5。 第2轮：你可以得到-2分。总数是：3。
     * 第3轮：你可以得到4分。总和是：7。 操作1：第3轮的数据无效。总数是：3。 第4轮：你可以得到-4分（第三轮的数据已被删除）。总和是：-1。 第5轮：你可以得到9分。总数是：8。
     * 第6轮：你可以得到-4 + 9 = 5分。总数是13。 第7轮：你可以得到9 + 5 = 14分。总数是27。 注意：
     *
     * <p>输入列表的大小将介于1和1000之间。 列表中的每个整数都将介于-30000和30000之间。
     *
     * @param ops
     * @return
     */
    public int calPoints(String[] ops) {
        int result = 0;
        Deque<Integer> stack = new LinkedList<>();

        for (String op : ops) {
            if (Objects.equals(op, "C")) {
                // 移除 前一回合
                if (!stack.isEmpty()) {
                    stack.pop();
                }
            } else if (Objects.equals(op, "D")) {
                // 前一轮有效 回合得分的两倍
                if (!stack.isEmpty()) {
                    Integer num = stack.peek();
                    stack.push(2 * num);
                }
            } else if (Objects.equals(op, "+")) {
                // 前两轮有效 回合得分的总和
                if (!stack.isEmpty()) {
                    Integer num1 = stack.pop();
                    if (stack.isEmpty()) {
                        stack.push(num1);
                        stack.push(num1);
                        continue;
                    }
                    Integer num2 = stack.peek();
                    stack.push(num1);
                    stack.push(num1 + num2);
                }
            } else {
                stack.push(Integer.valueOf(op));
            }
        }

        while (!stack.isEmpty()) {
            result += stack.pop();
        }
        return result;
    }

    @Test
    public void removeOuterParentheses() {
        String str = "()()";
        logResult(removeOuterParentheses(str));
    }

    /**
     * 1021. 删除最外层的括号 有效括号字符串为空 ("")、"(" + A + ")" 或 A + B， 其中 A 和 B 都是有效的括号字符串，+
     * 代表字符串的连接。例如，""，"()"，"(())()" 和 "(()(()))" 都是有效的括号字符串。
     *
     * <p>如果有效字符串 S 非空，且不存在将其拆分为 S = A+B 的方法，我们称其为原语（primitive），其中 A 和 B 都是非空有效括号字符串。
     *
     * <p>给出一个非空有效字符串 S，考虑将其进行原语化分解，使得：S = P_1 + P_2 + ... + P_k，其中 P_i 是有效括号字符串原语。
     *
     * <p>对 S 进行原语化分解，删除分解中每个原语字符串的最外层括号，返回 S 。
     *
     * <p>示例 1：
     *
     * <p>输入："(()())(())" 输出："()()()" 解释： 输入字符串为 "(()())(())"，原语化分解得到 "(()())" + "(())"，
     * 删除每个部分中的最外层括号后得到 "()()" + "()" = "()()()"。 示例 2：
     *
     * <p>输入："(()())(())(()(()))" 输出："()()()()(())" 解释： 输入字符串为 "(()())(())(()(()))"，原语化分解得到 "(()())"
     * + "(())" + "(()(()))"， 删除每个部分中的最外层括号后得到 "()()" + "()" + "()(())" = "()()()()(())"。 示例 3：
     *
     * <p>输入："()()" 输出："" 解释： 输入字符串为 "()()"，原语化分解得到 "()" + "()"， 删除每个部分中的最外层括号后得到 "" + "" = ""。
     *
     * <p>提示：
     *
     * <p>S.length <= 10000 S[i] 为 "(" 或 ")" S 是一个有效括号字符串
     *
     * @param S
     * @return
     */
    public String removeOuterParentheses(String S) {
        /*StringBuilder sb = new StringBuilder();
        Deque<Integer> stack = new LinkedList<>();

        char[] chars = S.toCharArray();

        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            if (c == '(') {
                stack.offerLast(i);
            } else {
                int index = stack.pollLast();
                if (stack.size() == 0 && i - index > 1) {
                    sb.append(S.substring(index + 1,i));
                }
            }
        }
        return sb.toString();*/
        StringBuilder sb = new StringBuilder();
        int count = 0;
        for (char c : S.toCharArray()) {

            if (c == ')') count--;
            if (count > 0) sb.append(c);
            if (c == '(') count++;
        }
        return sb.toString();
    }

    @Test
    public void validateStackSequences() {
        int[] pushed = {1, 2, 3, 4, 5}, popped = {4, 5, 3, 2, 1};

        logResult(validateStackSequences(pushed, popped));
    }

    /**
     * 面试题31. 栈的压入、弹出序列 输入两个整数序列，第一个序列表示栈的压入顺序，请判断第二个序列是否为该栈的弹出顺序。假设压入栈的所有数字均不相等。例如，序列 {1,2,3,4,5}
     * 是某栈的压栈序列，序列 {4,5,3,2,1} 是该压栈序列对应的一个弹出序列，但 {4,3,5,1,2} 就不可能是该压栈序列的弹出序列。
     *
     * <p>示例 1：
     *
     * <p>输入：pushed = [1,2,3,4,5], popped = [4,5,3,2,1] 输出：true 解释：我们可以按以下顺序执行： push(1), push(2),
     * push(3), push(4), pop() -> 4, push(5), pop() -> 5, pop() -> 3, pop() -> 2, pop() -> 1 示例 2：
     *
     * <p>输入：pushed = [1,2,3,4,5], popped = [4,3,5,1,2] 输出：false 解释：1 不能在 2 之前弹出。
     *
     * <p>提示：
     *
     * <p>0 <= pushed.length == popped.length <= 1000 0 <= pushed[i], popped[i] < 1000 pushed 是
     * popped 的排列。 注意：本题与主站 946 题相同：https://leetcode-cn.com/problems/validate-stack-sequences/
     *
     * @param pushed
     * @param popped
     * @return
     */
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        Deque<Integer> stack = new LinkedList<>();
        int index = 0;
        for (int i = 0; i < pushed.length; i++) {
            stack.push(pushed[i]);
            while (!stack.isEmpty() && stack.peek() == popped[index]) {
                stack.pop();
                index++;
            }
        }
        return stack.isEmpty();
    }

    /**
     * 5404. 用栈操作构建数组 给你一个目标数组 target 和一个整数 n。每次迭代，需要从 list = {1,2,3..., n} 中依序读取一个数字。
     *
     * <p>请使用下述操作来构建目标数组 target ：
     *
     * <p>Push：从 list 中读取一个新元素， 并将其推入数组中。 Pop：删除数组中的最后一个元素。 如果目标数组构建完成，就停止读取更多元素。
     * 题目数据保证目标数组严格递增，并且只包含 1 到 n 之间的数字。
     *
     * <p>请返回构建目标数组所用的操作序列。
     *
     * <p>题目数据保证答案是唯一的。
     *
     * <p>示例 1：
     *
     * <p>输入：target = [1,3], n = 3 输出：["Push","Push","Pop","Push"] 解释： 读取 1 并自动推入数组 -> [1] 读取 2
     * 并自动推入数组，然后删除它 -> [1] 读取 3 并自动推入数组 -> [1,3] 示例 2：
     *
     * <p>输入：target = [1,2,3], n = 3 输出：["Push","Push","Push"] 示例 3：
     *
     * <p>输入：target = [1,2], n = 4 输出：["Push","Push"] 解释：只需要读取前 2 个数字就可以停止。 示例 4：
     *
     * <p>输入：target = [2,3,4], n = 4 输出：["Push","Pop","Push","Push","Push"]
     *
     * <p>提示：
     *
     * <p>1 <= target.length <= 100 1 <= target[i] <= 100 1 <= n <= 100 target 是严格递增的
     *
     * @param target
     * @param n
     * @return
     */
    public List<String> buildArray(int[] target, int n) {
        List<String> result = new ArrayList<>();
        int index = 0;
        int len = target.length;
        for (int i = 1; i <= n; i++) {
            if (index == len) {
                break;
            }
            if (target[index] == i) {
                result.add("Push");
                index++;
            } else {
                result.add("Push");
                result.add("Pop");
            }
        }
        return result;
    }

    @Test
    public void deserialize() {
        String s = "[123,456,[788,799,833],[[]],10,[]]";
        logResult(deserialize(s));
    }

    /**
     * 385. 迷你语法分析器 给定一个用字符串表示的整数的嵌套列表，实现一个解析它的语法分析器。
     *
     * <p>列表中的每个元素只可能是整数或整数嵌套列表
     *
     * <p>提示：你可以假定这些字符串都是格式良好的：
     *
     * <p>字符串非空 字符串不包含空格 字符串只包含数字0-9, [, - ,, ]
     *
     * <p>示例 1：
     *
     * <p>给定 s = "324",
     *
     * <p>你应该返回一个 NestedInteger 对象，其中只包含整数值 324。
     *
     * <p>示例 2：
     *
     * <p>给定 s = "[123,[456,[789]]]",
     *
     * <p>返回一个 NestedInteger 对象包含一个有两个元素的嵌套列表：
     *
     * <p>1. 一个 integer 包含值 123 2. 一个包含两个元素的嵌套列表： i. 一个 integer 包含值 456 ii. 一个包含一个元素的嵌套列表 a. 一个
     * integer 包含值 789
     *
     * @param s
     */
    public NestedInteger deserialize(String s) {

        Deque<NestedInteger> stack = new LinkedList<>();

        boolean positive = true;

        NestedInteger result = new NestedInteger();
        int num = 0;
        // 思路,使用栈
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '[') {
                result = new NestedInteger();
                stack.offerLast(result);
            } else if (c == ']' || c == ',') {
                if (isNumber(s.charAt(i - 1))) {
                    NestedInteger ni = new NestedInteger(positive ? num : -num);
                    result = stack.peekLast();
                    result.add(ni);
                }

                if (c == ']' && stack.size() > 1) {
                    NestedInteger ni = stack.pollLast();
                    result = stack.peekLast();
                    result.add(ni);
                }

                num = 0;
                positive = true;
            } else if (isNumber(c)) {
                num = num * 10 + (c - '0');
            } else if (c == '-') {
                positive = false;
            }
        }
        if (num > 0) {
            result = new NestedInteger(positive ? num : -num);
        }
        return result;
        /*while (index < s.length()) {
            char c = s.charAt(index);

            if (isNumber(c)) {
                num = num * 10 + c - '0';
            } else {
                num = 0;
            }
            if (c == '[') {
                stack.addFirst();
            }


            index++;
        }*/
    }

    /*private boolean isNumber(char c) {
        return c >= '0' && c <= '9';
    }*/

    @Test
    public void nextGreaterElements() {
        int[] nums = {1, 2, 1, 4, 5, 1};

        int[] result = nextGreaterElements(nums);
        log.debug("result:{}", result);
    }

    /**
     * 503. 下一个更大元素 II 给定一个循环数组（最后一个元素的下一个元素是数组的第一个元素），输出每个元素的下一个更大元素。 数字 x
     * 的下一个更大的元素是按数组遍历顺序，这个数字之后的第一个比它更大的数，这意味着你应该循环地搜索它的下一个更大的数。如果不存在，则输出 -1。
     *
     * <p>示例 1:
     *
     * <p>输入: [1,2,1] 输出: [2,-1,2] 解释: 第一个 1 的下一个更大的数是 2； 数字 2 找不到下一个更大的数； 第二个 1 的下一个最大的数需要循环搜索，结果也是
     * 2。 注意: 输入数组的长度不会超过 10000。
     *
     * @param nums
     * @return
     */
    public int[] nextGreaterElements(int[] nums) {
        int[] result = new int[nums.length];
        Deque<Integer> stack = new LinkedList<>();
        Arrays.fill(result, -1);
        for (int i = 0; i < 2 * nums.length; i++) {
            int num = nums[i % nums.length];
            log.debug("num:{}", num);
            while (!stack.isEmpty() && nums[stack.peekLast()] < num) {
                int index = stack.pollLast();
                result[index] = num;
            }
            stack.offerLast(i % nums.length);
        }

        /**
         * for (int i = 2 * nums.length - 1; i >= 0; i--) { int index = i % nums.length; int num =
         * nums[index]; log.debug("num:{}",num); while (!stack.isEmpty() && nums[stack.peekLast()]
         * <= num) { stack.pollLast(); } result[index] = stack.isEmpty() ? - 1 :
         * nums[stack.peekLast()]; stack.offerLast(index); }
         */
        return result;
    }

    @Test
    public void largestRectangleArea() {
        int[] heights = {2, 1, 5, 6, 2, 3};
        logResult(largestRectangleArea(heights));
    }
    /**
     * 84. 柱状图中最大的矩形
     *
     * <p>给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
     *
     * <p>求在该柱状图中，能够勾勒出来的矩形的最大面积。
     *
     * <p>以上是柱状图的示例，其中每个柱子的宽度为 1，给定的高度为 [2,1,5,6,2,3]。
     *
     * <p>图中阴影部分为所能勾勒出的最大矩形面积，其面积为 10 个单位。
     *
     * <p>示例:
     *
     * <p>输入: [2,1,5,6,2,3] 输出: 10
     *
     * @param heights
     * @return
     */
    public int largestRectangleArea(int[] heights) {
        int len = heights.length;
        if (len == 0) {
            return 0;
        }
        if (len == 1) {
            return heights[0];
        }
        Deque<Integer> stack = new LinkedList<>();

        // 如果当前高度比栈顶的高度小, 出栈, 把 大的移走

        // 2,1,5,6,2,3

        stack.push(-1);
        int max = 0;
        for (int i = 0; i < heights.length; i++) {
            int height = heights[i];
            while (stack.peek() != -1 && heights[stack.peek()] >= height) {
                int index = stack.pop();
                int area = (i - stack.peek() - 1) * heights[index];
                log.debug("area:{} ,i :{}, index :{}", area, i, index);

                max = Math.max(area, max);
            }
            stack.push(i);
        }
        logResult(stack);
        while (!stack.isEmpty() && stack.peek() != -1) {
            int index = stack.pop();
            int area = (len - stack.peek() - 1) * heights[index];
            log.debug("area:{}", area);
            max = Math.max(area, max);
        }

        log.debug("max:{}", max);

        return max;
    }

    @Test
    public void dailyTemperatures2() {
        int[] nums = {75, 73, 74, 75, 71, 69, 72, 76, 73};
        int[] result = dailyTemperatures2(nums);
        log.debug("result:{}", result);
    }
    /**
     * 739. 每日温度
     *
     * <p>根据每日 气温 列表，请重新生成一个列表，对应位置的输出是需要再等待多久温度才会升高超过该日的天数。如果之后都不会升高, 请在该位置用 0 来代替。
     *
     * <p>例如，给定一个列表 temperatures = [75,73, 74, 75, 71, 69, 72, 76, 73]，你的输出应该是 [1, 1, 4, 2, 1, 1, 0,
     * 0]。
     *
     * <p>提示：气温 列表长度的范围是 [1, 30000]。每个气温的值的均为华氏度，都是在 [30, 100] 范围内的整数。
     *
     * @param T
     * @return
     */
    public int[] dailyTemperatures2(int[] T) {
        int len = T.length;
        int[] result = new int[len];
        Deque<Integer> stack = new LinkedList<>();
        for (int i = 0; i < len; i++) {
            if (stack.isEmpty() || T[stack.peek()] >= T[i]) {
                stack.push(i);
                continue;
            }
            while (!stack.isEmpty() && T[stack.peek()] < T[i]) {
                int index = stack.pop();
                result[index] = i - index;
            }
            stack.push(i);
        }
        log.debug("result:{}", result);

        return result;
    }

    @Test
    public void find132pattern() {
        int[] nums = {-1, 3, 2, 0};
        logResult(find132pattern(nums));
    }

    /**
     * 456. 132模式
     *
     * <p>给定一个整数序列：a1, a2, ..., an，一个132模式的子序列 ai, aj, ak 被定义为：当 i < j < k 时，ai < ak <
     * aj。设计一个算法，当给定有 n 个数字的序列时，验证这个序列中是否含有132模式的子序列。
     *
     * <p>注意：n 的值小于15000。
     *
     * <p>示例1:
     *
     * <p>输入: [1, 2, 3, 4]
     *
     * <p>输出: False
     *
     * <p>解释: 序列中不存在132模式的子序列。 示例 2:
     *
     * <p>输入: [3, 1, 4, 2]
     *
     * <p>输出: True
     *
     * <p>解释: 序列中有 1 个132模式的子序列： [1, 4, 2]. 示例 3:
     *
     * <p>输入: [-1, 3, 2, 0]
     *
     * <p>输出: True
     *
     * <p>解释: 序列中有 3 个132模式的的子序列: [-1, 3, 2], [-1, 3, 0] 和 [-1, 2, 0].
     *
     * @param nums
     * @return
     */
    public boolean find132pattern(int[] nums) {
        // 最小值列表
        int[] minNums = new int[nums.length];
        minNums[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            minNums[i] = Math.min(minNums[i - 1], nums[i]);
        }

        Deque<Integer> stack = new LinkedList<>();
        stack.push(nums[nums.length - 1]);
        for (int i = nums.length - 2; i > 0; i--) {
            if (nums[i] > minNums[i]) {
                while (!stack.isEmpty() && stack.peek() <= minNums[i]) {
                    stack.pop();
                }
                if (!stack.isEmpty() && stack.peek() < nums[i]) {
                    return true;
                }

                stack.push(nums[i]);
            }
        }
        return false;
    }

    /**
     * 636. 函数的独占时间
     *
     * <p>给出一个非抢占单线程CPU的 n 个函数运行日志，找到函数的独占时间。
     *
     * <p>每个函数都有一个唯一的 Id，从 0 到 n-1，函数可能会递归调用或者被其他函数调用。
     *
     * <p>日志是具有以下格式的字符串：function_id：start_or_end：timestamp。例如："0:start:0" 表示函数 0 从 0
     * 时刻开始运行。"0:end:0" 表示函数 0 在 0 时刻结束。
     *
     * <p>函数的独占时间定义是在该方法中花费的时间，调用其他函数花费的时间不算该函数的独占时间。你需要根据函数的 Id 有序地返回每个函数的独占时间。
     *
     * <p>示例 1:
     *
     * <p>输入: n = 2 logs = ["0:start:0", "1:start:2", "1:end:5", "0:end:6"] 输出:[3, 4] 说明： 函数 0 在时刻 0
     * 开始，在执行了 2个时间单位结束于时刻 1。 现在函数 0 调用函数 1，函数 1 在时刻 2 开始，执行 4 个时间单位后结束于时刻 5。 函数 0 再次在时刻 6 开始执行，并在时刻
     * 6 结束运行，从而执行了 1 个时间单位。 所以函数 0 总共的执行了 2 +1 =3 个时间单位，函数 1 总共执行了 4 个时间单位。 说明：
     *
     * <p>输入的日志会根据时间戳排序，而不是根据日志Id排序。 你的输出会根据函数Id排序，也就意味着你的输出数组中序号为 0 的元素相当于函数 0 的执行时间。
     * 两个函数不会在同时开始或结束。 函数允许被递归调用，直到运行结束。 1 <= n <= 100
     *
     * @param n
     * @param logs
     * @return
     */
    public int[] exclusiveTime(int n, List<String> logs) {
        int[] result = new int[n];
        Deque<Integer> indexStack = new LinkedList<>();

        String[] firstFunc = logs.get(0).split(":");
        int lastTime = Integer.parseInt(firstFunc[2]);
        indexStack.push(Integer.parseInt(firstFunc[0]));
        for (int i = 1; i < logs.size(); i++) {
            String[] func = logs.get(i).split(":");
            int index = Integer.parseInt(func[0]);
            int time = Integer.parseInt(func[2]);
            String op = func[1];
            if (Objects.equals(op, "start")) {
                if (!indexStack.isEmpty()) {
                    result[indexStack.peek()] += time - lastTime;
                }

                indexStack.push(index);
                lastTime = time;
            } else {
                result[indexStack.peek()] += time - lastTime + 1;
                indexStack.pop();
                lastTime = time + 1;
            }
        }

        return result;
    }

    @Test
    public void asteroidCollision() {
        int[] asteroids = {-2, -1, 1, 2};
        int[] result = asteroidCollision(asteroids);

        log.debug("result:{}", result);
    }

    /**
     * 735. 行星碰撞
     *
     * <p>给定一个整数数组 asteroids，表示在同一行的行星。
     *
     * <p>对于数组中的每一个元素，其绝对值表示行星的大小，正负表示行星的移动方向（正表示向右移动，负表示向左移动）。每一颗行星以相同的速度移动。
     *
     * <p>找出碰撞后剩下的所有行星。碰撞规则：两个行星相互碰撞，较小的行星会爆炸。如果两颗行星大小相同，则两颗行星都会爆炸。两颗移动方向相同的行星，永远不会发生碰撞。
     *
     * <p>示例 1:
     *
     * <p>输入: asteroids = [5, 10, -5] 输出: [5, 10] 解释: 10 和 -5 碰撞后只剩下 10。 5 和 10 永远不会发生碰撞。 示例 2:
     *
     * <p>输入: asteroids = [8, -8] 输出: [] 解释: 8 和 -8 碰撞后，两者都发生爆炸。 示例 3:
     *
     * <p>输入: asteroids = [10, 2, -5] 输出: [10] 解释: 2 和 -5 发生碰撞后剩下 -5。10 和 -5 发生碰撞后剩下 10。 示例 4:
     *
     * <p>输入: asteroids = [-2, -1, 1, 2] 输出: [-2, -1, 1, 2] 解释: -2 和 -1 向左移动，而 1 和 2 向右移动。
     * 由于移动方向相同的行星不会发生碰撞，所以最终没有行星发生碰撞。 说明:
     *
     * <p>数组 asteroids 的长度不超过 10000。 每一颗行星的大小都是非零整数，范围是 [-1000, 1000] 。
     *
     * @param asteroids
     * @return
     */
    public int[] asteroidCollision(int[] asteroids) {
        Deque<Integer> stack = new LinkedList<>();

        for (int asteroid : asteroids) {

            collision:
            {
                while (!stack.isEmpty() && asteroid < 0 && 0 < stack.peek()) {
                    if (stack.peek() < -asteroid) {
                        stack.pop();
                        continue;
                    } else if (stack.peek() == -asteroid) {
                        stack.pop();
                    }
                    break collision;
                }
                stack.push(asteroid);
            }
        }
        logResult(stack);

        int[] result = new int[stack.size()];
        int i = stack.size() - 1;
        for (int num : stack) {
            result[i--] = num;
        }
        return result;
    }

    @Test
    public void reverseParentheses() {
        String s = "(u(love)i)";
        logResult(reverseParentheses(s));
    }

    /**
     * 1190. 反转每对括号间的子串
     *
     * <p>给出一个字符串 s（仅含有小写英文字母和括号）。
     *
     * <p>请你按照从括号内到外的顺序，逐层反转每对匹配括号中的字符串，并返回最终的结果。
     *
     * <p>注意，您的结果中 不应 包含任何括号。
     *
     * <p>示例 1：
     *
     * <p>输入：s = "(abcd)" 输出："dcba" 示例 2：
     *
     * <p>输入：s = "(u(love)i)" 输出："iloveu" 示例 3：
     *
     * <p>输入：s = "(ed(et(oc))el)" 输出："leetcode" 示例 4：
     *
     * <p>输入：s = "a(bcdefghijkl(mno)p)q" 输出："apmnolkjihgfedcbq"
     *
     * <p>提示：
     *
     * <p>0 <= s.length <= 2000 s 中只有小写英文字母和括号 我们确保所有括号都是成对出现的
     *
     * @param s
     * @return
     */
    public String reverseParentheses(String s) {
        StringBuilder sb = new StringBuilder();
        Deque<Integer> stack = new LinkedList<>();

        char[] chs = s.toCharArray();

        for (int i = 0; i < chs.length; i++) {
            if (chs[i] == '(') {
                stack.push(i);
            } else if (chs[i] == ')') {
                reverse(chs, stack.pop() + 1, i - 1);
            }
        }
        for (char ch : chs) {
            if (ch != '(' && ch != ')') {
                sb.append(ch);
            }
        }

        return sb.toString();
    }

    private void reverse(char[] chs, int start, int end) {
        while (start < end) {
            char temp = chs[end];
            chs[end] = chs[start];
            chs[start] = temp;
            start++;
            end--;
        }
    }

    /**
     * 1209. 删除字符串中的所有相邻重复项 II
     *
     * <p>给你一个字符串 s，「k 倍重复项删除操作」将会从 s 中选择 k 个相邻且相等的字母，并删除它们，使被删去的字符串的左侧和右侧连在一起。
     *
     * <p>你需要对 s 重复进行无限次这样的删除操作，直到无法继续为止。
     *
     * <p>在执行完所有删除操作后，返回最终得到的字符串。
     *
     * <p>本题答案保证唯一。
     *
     * <p>示例 1：
     *
     * <p>输入：s = "abcd", k = 2 输出："abcd" 解释：没有要删除的内容。 示例 2：
     *
     * <p>输入：s = "deeedbbcccbdaa", k = 3 输出："aa" 解释： 先删除 "eee" 和 "ccc"，得到 "ddbbbdaa" 再删除 "bbb"，得到
     * "dddaa" 最后删除 "ddd"，得到 "aa" 示例 3：
     *
     * <p>输入：s = "pbbcggttciiippooaais", k = 2 输出："ps"
     *
     * <p>提示：
     *
     * <p>1 <= s.length <= 10^5 2 <= k <= 10^4 s 中只含有小写英文字母。
     *
     * @param s
     * @param k
     * @return
     */
    public String removeDuplicates(String s, int k) {

        StringBuilder sb = new StringBuilder(s);
        int[] count = new int[sb.length()];
        for (int i = 0; i < sb.length(); i++) {
            if (i == 0 || sb.charAt(i) != sb.charAt(i - 1)) {
                count[i] = 1;
            } else {
                count[i] = count[i - 1] + 1;
                if (count[i] == k) {
                    sb.delete(i - k + 1, i + 1);
                    i = i - k;
                }
            }
        }

        return sb.toString();
    }
}
