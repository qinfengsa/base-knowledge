package com.qinfengsa.structure.nowcoder;

import static com.qinfengsa.structure.util.LogUtils.logResult;

import java.util.Deque;
import java.util.LinkedList;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * 牛客网 华为笔试面试
 *
 * @author qinfengsa
 * @date 2020/08/02 08:31
 */
@Slf4j
public class HuaweiTest {

    @Test
    public void calculate() {
        String expression = "400 + (-1 - 7) * 2 ";
        logResult(calculate(expression));
    }

    /**
     * 题目描述 给定一个字符串描述的算术表达式，计算出结果值。
     *
     * <p>输入字符串长度不超过100，合法的字符包括”+, -, *, /, (, )”，”0-9”，字符串内容的合法性及表达式语法的合法性由做题者检查。本题目只涉及整型计算。
     *
     * <p>输入描述: 输入算术表达式
     *
     * <p>输出描述: 计算出结果值
     *
     * <p>示例1 输入 400+5 输出 405
     *
     * @param expression
     * @return
     */
    public int calculate(String expression) {
        // 思路：
        // 1、去掉字符串中的空格，将字符串按照运算符和数字保存到vector<string>中；
        // 2、判断字符串是否符合四则运算的表达式；
        // 3、栈实现：数字栈、运算符栈，从左到右扫描中缀表达式；
        // 数字：直接入栈；运算符：
        // （1）”（“：直接入栈；
        // （2）”）“：弹出（）之间的运算符，进行运算；
        // （3）”+-*/“：如果当前运算符优先级<栈顶运算符优先级，则数字栈弹出两个数和栈顶运算符进行运算，并将结果存入数字运算符，再将当前运算符入栈；否则直接入栈。
        // 扫描结束后，如果运算符栈还有元素，则出栈运算。
        // 负数：支持5+-3这样的写法，扫描到”-“，判断”-“是减号还是负号；
        // 　　　”-“前面一个字符是数字或者”）“，则认为是减号；否则是负号；
        // 　　　如果当前字符是负号，将标志isNeg=true，则下一个数字存入 中时，加上负号。

        Deque<Character> optStack = new LinkedList<>();
        Deque<Integer> numStack = new LinkedList<>();
        int len = expression.length();
        char[] exp = expression.toCharArray();
        boolean isNeg = false;
        for (int i = 0; i < len; i++) {
            if (exp[i] == ' ') {
                continue;
            }
            if (exp[i] == '(') {
                optStack.push(exp[i]);
            } else if (exp[i] == ')') {
                while (!optStack.isEmpty() && optStack.peek() != '(') {
                    dealStack(optStack, numStack);
                }
                optStack.pop();
            } else if (exp[i] >= '0' && exp[i] <= '9') {
                int num = exp[i] - '0';
                while (i < len - 1 && exp[i + 1] >= '0' && exp[i + 1] <= '9') {
                    i++;
                    num = num * 10 + exp[i] - '0';
                }
                if (isNeg) {
                    num = -num;
                    isNeg = false;
                }
                numStack.push(num);
            } else {
                if (exp[i] == '-') {
                    // ”-“前面一个字符是数字或者”）“，则认为是减号；否则是负号；
                    int index = i - 1;
                    while (index >= 0 && exp[index] == ' ') {
                        index--;
                    }
                    if (index >= 0
                            && (exp[index] >= '0' && exp[index] <= '9' || exp[index] == ')')) {

                    } else {
                        isNeg = true;
                        continue;
                    }
                }

                while (!optStack.isEmpty() && getLevel(exp[i]) <= getLevel(optStack.peek())) {
                    dealStack(optStack, numStack);
                }
                optStack.push(exp[i]);
            }
        }
        while (!optStack.isEmpty()) {
            dealStack(optStack, numStack);
        }

        return numStack.pop();
    }

    /**
     * 对栈进行运算
     *
     * @param optStack
     * @param numStack
     */
    private void dealStack(Deque<Character> optStack, Deque<Integer> numStack) {
        char c = optStack.pop();
        int num1 = numStack.pop();
        int num2 = numStack.pop();
        switch (c) {
            case '+':
                numStack.push(num1 + num2);
                break;
            case '-':
                numStack.push(num2 - num1);
                break;
            case '*':
                numStack.push(num1 * num2);
                break;
            case '/':
                numStack.push(num2 / num1);
                break;
        }
    }

    /**
     * 优先集
     *
     * @param c
     * @return
     */
    static int getLevel(char c) {
        switch (c) {
            case '(':
                return 0;
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
        }
        return 0;
    }

    public int opNum(int a, int b, char c) {
        switch (c) {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
            case '/':
                if (b != 0) return a / b;
        }
        return -1;
    }

    @Test
    public void calculate2() {
        String expression = "3+2*{1+2*[-4/(8-6)+7]}";
        logResult(calculate2(expression));
    }

    /**
     * 四则运算
     *
     * <p>输入：strExpression：字符串格式的算术表达式，如: "3+2*{1+2*[-4/(8-6)+7]}" 返回：算术表达式的计算结果
     *
     * <p>pucExpression字符串中的有效字符包括[‘0’-‘9’],‘+’,‘-’, ‘*’,‘/’ ,‘(’， ‘)’,‘[’, ‘]’,‘{’ ,‘}’。
     *
     * <p>pucExpression算术表达式的有效性由调用者保证;
     *
     * @param expression
     * @return
     */
    public int calculate2(String expression) {
        expression =
                expression.replace('{', '(').replace('[', '(').replace('}', ')').replace(']', ')');
        log.debug("exp:{}", expression);
        Deque<Character> optStack = new LinkedList<>();
        Deque<Integer> numStack = new LinkedList<>();
        int len = expression.length();
        char[] exp = expression.toCharArray();
        boolean isNeg = false;
        for (int i = 0; i < len; i++) {
            if (exp[i] == ' ') {
                continue;
            }
            if (exp[i] == '(') {
                optStack.push(exp[i]);
            } else if (exp[i] == ')') {
                while (!optStack.isEmpty() && optStack.peek() != '(') {
                    dealStack(optStack, numStack);
                }
                optStack.pop();
            } else if (exp[i] >= '0' && exp[i] <= '9') {
                int num = exp[i] - '0';
                while (i < len - 1 && exp[i + 1] >= '0' && exp[i + 1] <= '9') {
                    i++;
                    num = num * 10 + exp[i] - '0';
                }
                if (isNeg) {
                    num = -num;
                    isNeg = false;
                }
                numStack.push(num);
            } else {
                if (exp[i] == '-') {
                    // ”-“前面一个字符是数字或者”）“，则认为是减号；否则是负号；
                    int index = i - 1;
                    while (index >= 0 && exp[index] == ' ') {
                        index--;
                    }
                    if (index >= 0
                            && (exp[index] >= '0' && exp[index] <= '9' || exp[index] == ')')) {

                    } else {
                        isNeg = true;
                        continue;
                    }
                }

                while (!optStack.isEmpty() && getLevel(exp[i]) <= getLevel(optStack.peek())) {
                    dealStack(optStack, numStack);
                }
                optStack.push(exp[i]);
            }
        }
        while (!optStack.isEmpty()) {
            dealStack(optStack, numStack);
        }

        return numStack.pop();
    }
}
