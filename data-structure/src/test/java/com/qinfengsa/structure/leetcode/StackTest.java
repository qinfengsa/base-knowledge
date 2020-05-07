package com.qinfengsa.structure.leetcode;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Stack;

import static com.qinfengsa.structure.util.LogUtils.logResult;

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
        log.debug("result:{}",result);
    }


    /**
     * 每日温度
     * 根据每日 气温 列表，请重新生成一个列表，对应位置的输入是你需要再等待多久温度才会升高的天数。如果之后都不会升高，请输入 0 来代替。
     *
     * 例如，给定一个列表 temperatures = [73, 74, 75, 71, 69, 72, 76, 73]，你的输出应该是 [1, 1, 4, 2, 1, 1, 0, 0]。
     *
     * 提示：气温 列表长度的范围是 [1, 30000]。每个气温的值的都是 [30, 100] 范围内的整数。
     * @param T
     * @return
     */
    public int[] dailyTemperatures(int[] T) {
        int len = T.length;
        int[] result = new int[len];
        Stack<Integer> stack = new Stack<>();

        Stack<Integer> indexStack = new Stack<>();

        for (int i = 0; i < len ; i++) {

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
        log.debug("result:{}",result);
    }

    /**
     *  逆波兰表达式求值
     * 根据逆波兰表示法，求表达式的值。
     *
     * 有效的运算符包括 +, -, *, / 。每个运算对象可以是整数，也可以是另一个逆波兰表达式。
     *
     * 说明：
     *
     * 整数除法只保留整数部分。
     * 给定逆波兰表达式总是有效的。换句话说，表达式总会得出有效数值且不存在除数为 0 的情况。
     * 示例 1：
     *
     * 输入: ["2", "1", "+", "3", "*"]
     * 输出: 9
     * 解释: ((2 + 1) * 3) = 9
     * 示例 2：
     *
     * 输入: ["4", "13", "5", "/", "+"]
     * 输出: 6
     * 解释: (4 + (13 / 5)) = 6
     * 示例 3：
     *
     * 输入: ["10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"]
     * 输出: 22
     * 解释:
     *   ((10 * (6 / ((9 + 3) * -11))) + 17) + 5
     * = ((10 * (6 / (12 * -11))) + 17) + 5
     * = ((10 * (6 / -132)) + 17) + 5
     * = ((10 * 0) + 17) + 5
     * = (0 + 17) + 5
     * = 17 + 5
     * = 22
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
                result = calculateNum(a,b,s);
                stack.push( result);
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
            case '+' :
            case '-' :
            case '*' :
            case '/' : return true;
            default:break;
        }
        return false;
    }


    /**
     * 计算
     * @param a
     * @param b
     * @param s
     * @return
     */
    private int calculateNum(int a, int b ,String s) {
        char c = s.charAt(0);
        int reuslt = 0;
        switch (c) {
            case '+' : reuslt = a + b;break;
            case '-' : reuslt = a - b;break;
            case '*' : reuslt = a * b;break;
            case '/' : reuslt = a / b;break;
            default:break;
        }
        return reuslt;
    }

    @Test
    public void longestValidParentheses( ) {
        String s = "()(())";
        int result = longestValidParentheses(s);
        log.debug("result:{}",result);
    }

    /**
     * 给定一个只包含 '(' 和 ')' 的字符串，找出最长的包含有效括号的子串的长度。
     *
     * 示例 1:
     *
     * 输入: "(()"
     * 输出: 2
     * 解释: 最长有效括号子串为 "()"
     * 示例 2:
     *
     * 输入: ")()())"
     * 输出: 4
     * 解释: 最长有效括号子串为 "()()"
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
                    result = list.isEmpty() ? Math.max(result, i - start + 1) : Math.max(result, i - list.peek());
                }

            }

        }

        return result;
    }


    @Test
    public void decodeString() {
        String s = "2[abc]ef3[cd]";
        String result = decodeString2(s);
        log.debug("result :{}",result);
    }

    /**
     *  字符串解码
     * 给定一个经过编码的字符串，返回它解码后的字符串。
     *
     * 编码规则为: k[encoded_string]，表示其中方括号内部的 encoded_string 正好重复 k 次。注意 k 保证为正整数。
     *
     * 你可以认为输入字符串总是有效的；输入字符串中没有额外的空格，且输入的方括号总是符合格式要求的。
     *
     * 此外，你可以认为原始数据不包含数字，所有的数字只表示重复的次数 k ，例如不会出现像 3a 或 2[4] 的输入。
     *
     * 示例:
     *
     * s = "3[a]2[bc]", 返回 "aaabcbc".
     * s = "3[a2[c]]", 返回 "accaccacc".
     * s = "2[abc]3[cd]ef", 返回 "abcabccdcdcdef".
     * @param s
     * @return
     */
    public String decodeString(String s) {
        StringBuilder result = new StringBuilder();
        // 使用递归算法


        return result.toString();
    }

    public String decodeString2(String s) {

        StringBuilder result = new StringBuilder();
        // 思路 构建两个栈 数量栈 和 字符栈
        Deque<Integer> numStack = new LinkedList<>();
        Deque<String> strStack = new LinkedList<>();


        int num = 0;
        for(Character c : s.toCharArray()) {
            if(c == '[') {
                numStack.addLast(num);
                strStack.addLast(result.toString());
                num = 0;
                result = new StringBuilder();
            }  else if(c == ']') {
                StringBuilder tmp = new StringBuilder();
                int count = numStack.removeLast();
                for(int i = 0; i < count; i++) {
                    tmp.append(result);
                }
                result = new StringBuilder(strStack.removeLast() + tmp);
            } else if(c >= '0' && c <= '9') {
                num = num * 10 + Integer.parseInt(c + "");
            }
            else result.append(c);
        }
        /**for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '[') {
                Integer num = Integer.valueOf(s.substring(numStart,i));
                numStack.push(num);
                numStart = -1;
                strStart = i + 1;
            } else if (c == ']') {
                if (strStart != -1) {
                    String str = s.substring(strStart,i);
                    strStack.push(str);
                    strStart = -1;
                }

                int num = numStack.pop();
                StringBuilder sub = new StringBuilder();
                // 循环num次，
                String top = strStack.pop();
                for (int j = 0; j < num; j++) {
                    sub.append(top);
                }
                // 如果 strStack 为空, 把 sub 加到 result 上
                if (strStack.isEmpty()) {
                    result.append(sub);
                } else {
                    // strStack 不为空
                    String str1 = strStack.pop();
                    sub.insert(0,str1);
                    strStack.push(sub.toString());
                }


            } else if (isNumber(c)) {
                if (numStart == -1) {
                    numStart = i;
                }
                if (strStart != -1) {
                    String str = s.substring(strStart,i);
                    strStack.push(str);
                    strStart = -1;
                }
            } else {
                if (numStack.isEmpty()) {
                    result.append(c);
                } else if (strStack.size() == numStack.size() ) {
                    StringBuilder pop = new StringBuilder(strStack.pop());
                    pop.append(c);
                    strStack.push(pop.toString());
                }
            }
        }*/


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
            case '9': return true;
        }
        return false;
    }

    @Test
    public void calPoints() {
        String[] ops = {"5","-2","4","C","D","9","+","+"};
        logResult(calPoints(ops));
    }

    /**
     * 682. 棒球比赛
     * 你现在是棒球比赛记录员。
     * 给定一个字符串列表，每个字符串可以是以下四种类型之一：
     * 1.整数（一轮的得分）：直接表示您在本轮中获得的积分数。
     * 2. "+"（一轮的得分）：表示本轮获得的得分是前两轮有效 回合得分的总和。
     * 3. "D"（一轮的得分）：表示本轮获得的得分是前一轮有效 回合得分的两倍。
     * 4. "C"（一个操作，这不是一个回合的分数）：表示您获得的最后一个有效 回合的分数是无效的，应该被移除。
     *
     * 每一轮的操作都是永久性的，可能会对前一轮和后一轮产生影响。
     * 你需要返回你在所有回合中得分的总和。
     *
     * 示例 1:
     *
     * 输入: ["5","2","C","D","+"]
     * 输出: 30
     * 解释:
     * 第1轮：你可以得到5分。总和是：5。
     * 第2轮：你可以得到2分。总和是：7。
     * 操作1：第2轮的数据无效。总和是：5。
     * 第3轮：你可以得到10分（第2轮的数据已被删除）。总数是：15。
     * 第4轮：你可以得到5 + 10 = 15分。总数是：30。
     * 示例 2:
     *
     * 输入: ["5","-2","4","C","D","9","+","+"]
     * 输出: 27
     * 解释:
     * 第1轮：你可以得到5分。总和是：5。
     * 第2轮：你可以得到-2分。总数是：3。
     * 第3轮：你可以得到4分。总和是：7。
     * 操作1：第3轮的数据无效。总数是：3。
     * 第4轮：你可以得到-4分（第三轮的数据已被删除）。总和是：-1。
     * 第5轮：你可以得到9分。总数是：8。
     * 第6轮：你可以得到-4 + 9 = 5分。总数是13。
     * 第7轮：你可以得到9 + 5 = 14分。总数是27。
     * 注意：
     *
     * 输入列表的大小将介于1和1000之间。
     * 列表中的每个整数都将介于-30000和30000之间。
     * @param ops
     * @return
     */
    public int calPoints(String[] ops) {
        int result = 0;
        Deque<Integer> stack = new LinkedList<>();

        for (String op : ops) {
            if (Objects.equals(op,"C")) {
                // 移除 前一回合
                if (!stack.isEmpty()) {
                    stack.pop();
                }
            } else if (Objects.equals(op,"D")) {
                // 前一轮有效 回合得分的两倍
                if (!stack.isEmpty()) {
                    Integer num = stack.peek();
                    stack.push(2*num);
                }
            } else if (Objects.equals(op,"+")) {
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
                    stack.push(num1+num2);
                }
            } else {
                stack.push(Integer.valueOf(op) );
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
     * 1021. 删除最外层的括号
     * 有效括号字符串为空 ("")、"(" + A + ")" 或 A + B，
     * 其中 A 和 B 都是有效的括号字符串，+ 代表字符串的连接。例如，""，"()"，"(())()" 和 "(()(()))" 都是有效的括号字符串。
     *
     * 如果有效字符串 S 非空，且不存在将其拆分为 S = A+B 的方法，我们称其为原语（primitive），其中 A 和 B 都是非空有效括号字符串。
     *
     * 给出一个非空有效字符串 S，考虑将其进行原语化分解，使得：S = P_1 + P_2 + ... + P_k，其中 P_i 是有效括号字符串原语。
     *
     * 对 S 进行原语化分解，删除分解中每个原语字符串的最外层括号，返回 S 。
     *
     *
     *
     * 示例 1：
     *
     * 输入："(()())(())"
     * 输出："()()()"
     * 解释：
     * 输入字符串为 "(()())(())"，原语化分解得到 "(()())" + "(())"，
     * 删除每个部分中的最外层括号后得到 "()()" + "()" = "()()()"。
     * 示例 2：
     *
     * 输入："(()())(())(()(()))"
     * 输出："()()()()(())"
     * 解释：
     * 输入字符串为 "(()())(())(()(()))"，原语化分解得到 "(()())" + "(())" + "(()(()))"，
     * 删除每个部分中的最外层括号后得到 "()()" + "()" + "()(())" = "()()()()(())"。
     * 示例 3：
     *
     * 输入："()()"
     * 输出：""
     * 解释：
     * 输入字符串为 "()()"，原语化分解得到 "()" + "()"，
     * 删除每个部分中的最外层括号后得到 "" + "" = ""。
     *
     *
     * 提示：
     *
     * S.length <= 10000
     * S[i] 为 "(" 或 ")"
     * S 是一个有效括号字符串
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
        int count =0;
        for(char c:S.toCharArray()){

            if(c==')') count--;
            if(count>0)sb.append(c);
            if(c=='(') count++;
        }
        return sb.toString();
    }


    @Test
    public void validateStackSequences() {
        int[] pushed = {1,2,3,4,5}, popped = {4,5,3,2,1};

        logResult(validateStackSequences(pushed,popped));
    }

    /**
     * 面试题31. 栈的压入、弹出序列
     * 输入两个整数序列，第一个序列表示栈的压入顺序，请判断第二个序列是否为该栈的弹出顺序。假设压入栈的所有数字均不相等。例如，序列 {1,2,3,4,5} 是某栈的压栈序列，序列 {4,5,3,2,1} 是该压栈序列对应的一个弹出序列，但 {4,3,5,1,2} 就不可能是该压栈序列的弹出序列。
     *
     *
     *
     * 示例 1：
     *
     * 输入：pushed = [1,2,3,4,5], popped = [4,5,3,2,1]
     * 输出：true
     * 解释：我们可以按以下顺序执行：
     * push(1), push(2), push(3), push(4), pop() -> 4,
     * push(5), pop() -> 5, pop() -> 3, pop() -> 2, pop() -> 1
     * 示例 2：
     *
     * 输入：pushed = [1,2,3,4,5], popped = [4,3,5,1,2]
     * 输出：false
     * 解释：1 不能在 2 之前弹出。
     *
     *
     * 提示：
     *
     * 0 <= pushed.length == popped.length <= 1000
     * 0 <= pushed[i], popped[i] < 1000
     * pushed 是 popped 的排列。
     * 注意：本题与主站 946 题相同：https://leetcode-cn.com/problems/validate-stack-sequences/
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
}
