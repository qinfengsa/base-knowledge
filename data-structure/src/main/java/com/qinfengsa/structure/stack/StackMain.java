package com.qinfengsa.structure.stack;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 栈
 *
 * @author qinfengsa
 * @date 2021/6/22 8:47
 */
public class StackMain {

    /**
     * 1896. 反转表达式值的最少操作次数
     *
     * <p>给你一个 有效的 布尔表达式，用字符串 expression 表示。这个字符串包含字符 '1'，'0'，'&'（按位 与 运算），'|'（按位 或 运算），'(' 和 ')' 。
     *
     * <p>比方说，"()1|1" 和 "(1)&()" 不是有效 布尔表达式。而 "1"， "(((1))|(0))" 和 "1|(0&(1))" 是 有效 布尔表达式。
     * 你的目标是将布尔表达式的 值 反转 （也就是将 0 变为 1 ，或者将 1 变为 0），请你返回达成目标需要的 最少操作 次数。
     *
     * <p>比方说，如果表达式 expression = "1|1|(0&0)&1" ，它的 值 为 1|1|(0&0)&1 = 1|1|0&1 = 1|0&1 = 1&1 = 1
     * 。我们想要执行操作将 新的 表达式的值变成 0 。 可执行的 操作 如下：
     *
     * <p>将一个 '1' 变成一个 '0' 。 将一个 '0' 变成一个 '1' 。 将一个 '&' 变成一个 '|' 。 将一个 '|' 变成一个 '&' 。 注意：'&' 的 运算优先级
     * 与 '|' 相同 。计算表达式时，括号优先级 最高 ，然后按照 从左到右 的顺序运算。
     *
     * <p>示例 1：
     *
     * <p>输入：expression = "1&(0|1)" 输出：1 解释：我们可以将 "1&(0|1)" 变成 "1&(0&1)" ，执行的操作为将一个 '|' 变成一个 '&'
     * ，执行了 1 次操作。 新表达式的值为 0 。 示例 2：
     *
     * <p>输入：expression = "(0&0)&(0&0&0)" 输出：3 解释：我们可以将 "(0&0)&(0&0&0)" 变成 "(0|1)|(0&0&0)" ，执行了 3
     * 次操作。 新表达式的值为 1 。 示例 3：
     *
     * <p>输入：expression = "(0|(1|0&1))" 输出：1 解释：我们可以将 "(0|(1|0&1))" 变成 "(0|(0|0&1))" ，执行了 1 次操作。
     * 新表达式的值为 0 。
     *
     * <p>提示：
     *
     * <p>1 <= expression.length <= 105 expression 只包含 '1'，'0'，'&'，'|'，'(' 和 ')' 所有括号都有与之匹配的对应括号。
     * 不会有空的括号（也就是说 "()" 不是 expression 的子字符串）。
     *
     * @param expression
     * @return
     */
    public int minOperationsToFlip(String expression) {
        // num[0] 表示将整个表达式的值变为 0 最少需要的操作次数
        // num[1] 表示将整个表达式的值变为 1 最少需要的操作次数
        Deque<int[]> numStack = new LinkedList<>();
        Deque<Character> opStack = new LinkedList<>();

        for (char c : expression.toCharArray()) {
            if (c == '&' || c == '|' || c == '(') {
                opStack.push(c);
                continue;
            } else if (c == '0') {
                numStack.push(new int[] {0, 1});
            } else if (c == '1') {
                numStack.push(new int[] {1, 0});
            } else if (c == ')') {
                // 弹出
                opStack.pop();
            }
            // 计算
            calc(numStack, opStack);
        }
        int[] result = numStack.pop();
        return Math.max(result[0], result[1]);
    }

    private void calc(Deque<int[]> numStack, Deque<Character> opStack) {
        if (numStack.size() >= 2 && !opStack.isEmpty() && opStack.peek() != '(') {
            char op = opStack.pop();
            int[] num1 = numStack.pop(), num2 = numStack.pop();
            // and 运算 结果0和1   or运算 结果0和1
            int and0, and1, or0, or1;

            and0 = Math.min(num1[0] + num2[0], Math.min(num1[0] + num2[1], num1[1] + num2[0]));
            and1 = num1[1] + num2[1];

            or0 = num1[0] + num2[0];
            or1 = Math.min(num1[1] + num2[1], Math.min(num1[0] + num2[1], num1[1] + num2[0]));
            int[] resultNum = new int[2];
            if (op == '&') {
                // 结果为0   or0 + 1 表示修改 操作符 & -> |
                resultNum[0] = Math.min(and0, or0 + 1);
                // 结果为1   or1 + 1 表示修改 操作符 & -> |
                resultNum[1] = Math.min(and1, or1 + 1);
            } else {
                // 结果为0  and0 + 1 表示修改 操作符 | -> &
                resultNum[0] = Math.min(or0, and0 + 1);
                // 结果为1  and1 + 1 表示修改 操作符 | -> &
                resultNum[1] = Math.min(or1, and1 + 1);
            }
            numStack.push(resultNum);
        }
    }
}
