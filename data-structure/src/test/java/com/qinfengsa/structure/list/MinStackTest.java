package com.qinfengsa.structure.list;

import com.qinfengsa.structure.leetcode.MinStack;
import java.util.Objects;
import java.util.Stack;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

/**
 * @author: qinfengsa
 * @date: 2019/5/6 17:34
 */
@Slf4j
public class MinStackTest {

    @Test
    public void test1() {
        MinStack minStack = new MinStack();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        log.debug("1:{}", minStack.getMin()); // --> 返回 -3.
        minStack.pop();
        log.debug("2:{}", minStack.top()); // --> 返回 0.
        log.debug("3:{}", minStack.getMin()); // --> 返回 -2.
    }

    /** 测试括号匹配 */
    @Test
    public void testBracketMatch() {
        boolean b = isValid("");
        log.debug("b:{}", b);
    }

    /**
     * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。 有效字符串需满足： 左括号必须用相同类型的右括号闭合。 左括号必须以正确的顺序闭合。
     * 注意空字符串可被认为是有效字符串。
     *
     * @param s
     * @return
     */
    public boolean isValid(String s) {

        Stack<Character> stack = new Stack<Character>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            switch (c) {
                case '(':
                case '{':
                case '[':
                    stack.push(c);
                    break;
                case ')':
                    if (!stack.isEmpty() && Objects.equals(stack.peek(), '(')) {
                        stack.pop();
                        break;
                    } else {
                        return false;
                    }
                case '}':
                    if (!stack.isEmpty() && Objects.equals(stack.peek(), '{')) {
                        stack.pop();
                        break;
                    } else {
                        return false;
                    }
                case ']':
                    if (!stack.isEmpty() && Objects.equals(stack.peek(), '[')) {
                        stack.pop();
                        break;
                    } else {
                        return false;
                    }
            }
        }
        if (stack.isEmpty()) {
            return true;
        }

        return false;
    }

    /**
     * 根据每日 气温 列表，请重新生成一个列表，对应位置的输入是你需要再等待多久温度才会升高的天数。如果之后都不会升高，请输入 0 来代替。
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

        int[] days = new int[T.length];

        return null;
    }
}
