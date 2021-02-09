package com.qinfengsa.structure.leetcode;

import static com.qinfengsa.structure.util.LogUtils.logResult;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

/**
 * 计算器
 *
 * @author qinfengsa
 * @date 2021/1/28 20:26
 */
@Slf4j
public class CalculatorTest {

    public boolean isAndOp(char c) {
        return c == '+' || c == '-';
    }

    public boolean isMulOp(char c) {
        return c == '*' || c == '/';
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

    private int calculateNum(int a, int b, char c) {
        int result = 0;
        switch (c) {
            case '+':
                result = a + b;
                break;
            case '-':
                result = a - b;
                break;
            case '*':
                result = a * b;
                break;
            case '/':
                result = a / b;
                break;
        }
        return result;
    }

    @Test
    public void testCalculate() {
        // log.debug("a:{}",3/2);

        String s = "(1-(3-4))";
        int result = calculate(s);
        logResult(result);
    }

    /**
     * 面试题 16.26. 计算器
     *
     * <p>给定一个包含正整数、加(+)、减(-)、乘(*)、除(/)的算数表达式(括号除外)，计算其结果。
     *
     * <p>表达式仅包含非负整数，+， - ，*，/ 四种运算符和空格 。 整数除法仅保留整数部分。
     *
     * <p>示例 1:
     *
     * <p>输入: "3+2*2" 输出: 7 示例 2:
     *
     * <p>输入: " 3/2 " 输出: 1 示例 3:
     *
     * <p>输入: " 3+5 / 2 " 输出: 5 说明：
     *
     * <p>你可以假设所给定的表达式都是有效的。 请不要使用内置的库函数 eval。
     *
     * @param s
     * @return
     */
    public int calculate(String s) {

        int result = 0;
        // 思路： 使用两个栈，分别存放运算的数值 和运算符
        Deque<Integer> numDeque = new LinkedList<>();
        Deque<Character> calculateDeque = new LinkedList<>();

        int num = 0;
        Character lastChar = null;
        // 是否计算
        boolean isCal = false;
        for (char c : s.toCharArray()) {
            if (c == ' ') {
                continue;
            }
            if (isNumber(c)) {
                lastChar = null;
                num = num * 10 + (c - '0');
            } else if (c == '(') {
                calculateDeque.addLast(c);
            } else if (c == ')') {
                Deque<Integer> deque1 = new LinkedList<>();
                Deque<Character> deque2 = new LinkedList<>();

                while (true) {
                    Character cs = calculateDeque.removeLast();
                    if (cs == '(') {
                        break;
                    }
                    deque2.addFirst(cs);
                    deque1.addFirst(numDeque.removeLast());
                }
                if (Objects.equals(lastChar, ')')) {
                    deque1.addFirst(numDeque.removeLast());
                } else {
                    deque1.addLast(num);
                }

                num = deque1.removeFirst();
                while (!deque1.isEmpty()) {
                    num = calculateNum(num, deque1.removeFirst(), deque2.removeFirst());
                }
                numDeque.addLast(num);
                num = 0;
                lastChar = ')';
            } else {

                if (lastChar == null) {
                    if (isCal) {
                        // 先算 乘除
                        num = calculateNum(numDeque.removeLast(), num, calculateDeque.removeLast());
                    }
                    numDeque.addLast(num);
                }
                calculateDeque.addLast(c);
                if (isMulOp(c)) {
                    isCal = true;
                } else if (isAndOp(c)) {
                    isCal = false;
                }
                num = 0;
            }
        }

        if (isCal) {
            // 先算 乘除
            num = calculateNum(numDeque.removeLast(), num, calculateDeque.removeLast());
        }
        if (lastChar == null) {
            numDeque.addLast(num);
        }

        // 算加减法, 使用队列的特性

        result = numDeque.removeFirst();
        while (!numDeque.isEmpty()) {
            result = calculateNum(result, numDeque.removeFirst(), calculateDeque.removeFirst());
        }

        return result;
    }

    @Test
    public void testCalculateII() {
        String s = " 3/2 ";
        int result = calculateII(s);
        log.debug("result:{}", result);
    }

    /**
     * 227. 基本计算器 II
     *
     * <p>实现一个基本的计算器来计算一个简单的字符串表达式的值。
     *
     * <p>字符串表达式仅包含非负整数，+， - ，*，/ 四种运算符和空格 。 整数除法仅保留整数部分。
     *
     * <p>示例 1: 输入: "3+2*2" 输出: 7
     *
     * <p>示例 2: 输入: " 3/2 " 输出: 1
     *
     * <p>示例 3: 输入: " 3+5 / 2 " 输出: 5 说明：
     *
     * <p>你可以假设所给定的表达式都是有效的。 请不要使用内置的库函数 eval。
     *
     * @param s
     * @return
     */
    public int calculateII(String s) {

        // 思路： 使用两个栈，分别存放运算的数值 和运算符
        Deque<Integer> numDeque = new LinkedList<>();
        Deque<Character> calculateDeque = new LinkedList<>();

        int result = 0;
        int num = 0;
        // 是否计算
        boolean isCal = false;
        for (char c : s.toCharArray()) {
            if (c == ' ') {
                continue;
            }
            if (isNumber(c)) {
                num = num * 10 + (c - '0');
            } else {
                if (isCal) {
                    // 先算 乘除
                    num = calculateNum(numDeque.removeLast(), num, calculateDeque.removeLast());
                }
                numDeque.addLast(num);
                calculateDeque.addLast(c);
                if (isMulOp(c)) {
                    isCal = true;
                } else if (isAndOp(c)) {
                    isCal = false;
                }
                num = 0;
            }
        }

        if (isCal) {
            // 先算 乘除
            num = calculateNum(numDeque.removeLast(), num, calculateDeque.removeLast());
        }
        numDeque.addLast(num);
        // 算加减法, 使用队列的特性

        result = numDeque.removeFirst();
        while (!numDeque.isEmpty()) {
            result = calculateNum(result, numDeque.removeFirst(), calculateDeque.removeFirst());
        }

        return result;
    }

    /**
     * 224. 基本计算器
     *
     * <p>实现一个基本的计算器来计算一个简单的字符串表达式 s 的值。
     *
     * <p>示例 1：
     *
     * <p>输入：s = "1 + 1" 输出：2 示例 2：
     *
     * <p>输入：s = " 2-1 + 2 " 输出：3 示例 3：
     *
     * <p>输入：s = "(1+(4+5+2)-3)+(6+8)" 输出：23
     *
     * <p>提示：
     *
     * <p>1 <= s.length <= 3 * 105 s 由数字、'+'、'-'、'('、')'、和 ' ' 组成 s 表示一个有效的表达式
     *
     * @param s
     * @return
     */
    public int calculateI(String s) {

        // 思路： 使用两个栈，分别存放运算的数值 和运算符
        Deque<Integer> numDeque = new LinkedList<>();
        Deque<Character> calculateDeque = new LinkedList<>();

        int num = 0;
        // 是否计算
        boolean isCal = false;
        boolean lastNum = true;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == ' ') {
                continue;
            }

            if (isNumber(c)) {
                num = num * 10 + (c - '0');
                lastNum = true;
            } else if (c == '(') {
                int count = 0, start = i;
                while (i < s.length()) {
                    if (s.charAt(i) == '(') {
                        count++;
                    }
                    if (s.charAt(i) == ')') {
                        count--;
                    }
                    if (count == 0) {
                        break;
                    }
                    i++;
                }
                log.debug("s:{} start:{} i :{}", s, start, i);
                int val = calculateI(s.substring(start + 1, i));
                numDeque.addLast(val);
                num = 0;
                lastNum = false;
            } else {
                if (isCal) {
                    // 先算 乘除
                    num = calculateNum(numDeque.removeLast(), num, calculateDeque.removeLast());
                }
                if (lastNum) {
                    numDeque.addLast(num);
                }
                calculateDeque.addLast(c);
                if (isMulOp(c)) {
                    isCal = true;
                } else if (isAndOp(c)) {
                    isCal = false;
                }
                num = 0;
            }
        }
        if (isCal) {
            // 先算 乘除
            num = calculateNum(numDeque.removeLast(), num, calculateDeque.removeLast());
        }
        if (lastNum) {
            numDeque.addLast(num);
        }
        log.debug("s:{}", s);
        // 双向队列 从左到右计算
        log.debug("numDeque:{}", numDeque);
        log.debug("calculateDeque:{}", calculateDeque);
        int result = numDeque.removeFirst();
        while (!numDeque.isEmpty()) {
            result = calculateNum(result, numDeque.removeFirst(), calculateDeque.removeFirst());
        }

        return result;
    }

    @Test
    public void testCalculateI() {
        String s = "-2+ 1";
        int result = calculateI(s);
        logResult(result);
    }

    /**
     * 770. 基本计算器 IV
     *
     * <p>给定一个表达式 expression 如 expression = "e + 8 - a + 5" 和一个求值映射，如 {"e": 1}（给定的形式为 evalvars =
     * ["e"] 和 evalints = [1]），返回表示简化表达式的标记列表，例如 ["-1*a","14"]
     *
     * <p>表达式交替使用块和符号，每个块和符号之间有一个空格。 块要么是括号中的表达式，要么是变量，要么是非负整数。 块是括号中的表达式，变量或非负整数。
     * 变量是一个由小写字母组成的字符串（不包括数字）。请注意，变量可以是多个字母，并注意变量从不具有像 "2x" 或 "-x" 这样的前导系数或一元运算符 。
     * 表达式按通常顺序进行求值：先是括号，然后求乘法，再计算加法和减法。例如，expression = "1 + 2 * 3" 的答案是 ["7"]。
     *
     * <p>输出格式如下：
     *
     * <p>对于系数非零的每个自变量项，我们按字典排序的顺序将自变量写在一个项中。例如，我们永远不会写像 “b*a*c” 这样的项，只写 “a*b*c”。
     * 项的次数等于被乘的自变量的数目，并计算重复项。(例如，"a*a*b*c" 的次数为 4。)。我们先写出答案的最大次数项，用字典顺序打破关系，此时忽略词的前导系数。
     * 项的前导系数直接放在左边，用星号将它与变量分隔开(如果存在的话)。前导系数 1 仍然要打印出来。 格式良好的一个示例答案是 ["-2*a*a*a", "3*a*a*b",
     * "3*b*b", "4*a", "5*c", "-6"] 。 系数为 0 的项（包括常数项）不包括在内。例如，“0” 的表达式输出为 []。
     *
     * <p>示例：
     *
     * <p>输入：expression = "e + 8 - a + 5", evalvars = ["e"], evalints = [1] 输出：["-1*a","14"]
     *
     * <p>输入：expression = "e - 8 + temperature - pressure", evalvars = ["e", "temperature"],
     * evalints = [1, 12] 输出：["-1*pressure","5"]
     *
     * <p>输入：expression = "(e + 8) * (e - 8)", evalvars = [], evalints = [] 输出：["1*e*e","-64"]
     *
     * <p>输入：expression = "7 - 7", evalvars = [], evalints = [] 输出：[]
     *
     * <p>输入：expression = "a * b * c + b * a * c * 4", evalvars = [], evalints = [] 输出：["5*a*b*c"]
     *
     * <p>输入：expression = "((a - b) * (b - c) + (c - a)) * ((a - b) + (b - c) * (c - a))", evalvars
     * = [], evalints = []
     * 输出：["-1*a*a*b*b","2*a*a*b*c","-1*a*a*c*c","1*a*b*b*b","-1*a*b*b*c","-1*a*b*c*c","1*a*c*c*c","-1*b*b*b*c","2*b*b*c*c","-1*b*c*c*c","2*a*a*b","-2*a*a*c","-2*a*b*b","2*a*c*c","1*b*b*b","-1*b*b*c","1*b*c*c","-1*c*c*c","-1*a*a","1*a*b","1*a*c","-1*b*c"]
     *
     * <p>提示：
     *
     * <p>expression 的长度在 [1, 250] 范围内。 evalvars, evalints 在范围 [0, 100] 内，且长度相同。
     *
     * @param expression
     * @param evalvars
     * @param evalints
     * @return
     */
    public List<String> basicCalculatorIV(String expression, String[] evalvars, int[] evalints) {

        Map<String, Integer> evalMap = new HashMap<>();
        for (int i = 0; i < evalvars.length; i++) {
            evalMap.put(evalvars[i], evalints[i]);
        }

        Deque<Expression> expStack = new LinkedList<>();
        Deque<String> opStack = new LinkedList<>();

        int i = 0, len = expression.length();
        while (i < len) {
            if (expression.charAt(i) == ' ') {
                i++;
                continue;
            }
            char c = expression.charAt(i);
            if (isNumber(c)) {
                int num = 0;
                while (i < len && isNumber(expression.charAt(i))) {
                    num = num * 10 + (expression.charAt(i) - '0');
                    i++;
                }
                expStack.push(new Expression(new Polynomial(num)));
            } else if (c >= 'a' && c <= 'z') {
                int start = i;
                while (i < len && expression.charAt(i) >= 'a' && expression.charAt(i) <= 'z') {
                    i++;
                }
                String factor = expression.substring(start, i);
                if (evalMap.containsKey(factor)) {
                    expStack.push(new Expression(new Polynomial(evalMap.get(factor))));
                } else {
                    expStack.push(new Expression(new Polynomial(factor, 1)));
                }

            } else if (c == '(') {
                opStack.push("(");
                i++;
            } else if (c == ')') {
                while (!opStack.isEmpty() && !opStack.peek().equals("(")) {
                    Expression exp2 = expStack.pop();
                    Expression exp1 = expStack.pop();
                    expStack.push(exp1.operate(exp2, opStack.pop()));
                }
                opStack.pop();
                i++;
            } else if (c == '*') {
                while (!opStack.isEmpty() && opStack.peek().equals("*")) {
                    Expression exp2 = expStack.pop();
                    Expression exp1 = expStack.pop();
                    expStack.push(exp1.operate(exp2, opStack.pop()));
                }
                opStack.push("*");
                i++;
            } else {
                while (!opStack.isEmpty()
                        && (opStack.peek().equals("+")
                                || opStack.peek().equals("-")
                                || opStack.peek().equals("*"))) {
                    Expression exp2 = expStack.pop();
                    Expression exp1 = expStack.pop();
                    expStack.push(exp1.operate(exp2, opStack.pop()));
                }
                opStack.push(c == '+' ? "+" : "-");

                i++;
            }
        }
        while (!opStack.isEmpty()) {
            Expression exp2 = expStack.pop();
            Expression exp1 = expStack.pop();
            expStack.push(exp1.operate(exp2, opStack.pop()));
        }
        List<String> result = new ArrayList<>();
        Expression exp = expStack.pop();
        exp.clean();
        Collections.sort(exp.items);
        for (Polynomial item : exp.items) {
            result.add(item.toString());
        }

        return result;
    }

    // 多项式
    static class Polynomial implements Comparable<Polynomial> {
        // 系数
        int coeff;

        List<String> factors;

        Polynomial(String factor, int coeff) {
            this.factors = new ArrayList<>();
            this.factors.add(factor);
            this.coeff = coeff;
        }

        Polynomial(int coeff) {
            this.factors = new ArrayList<>();
            this.coeff = coeff;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder().append(coeff);

            factors.forEach(factor -> sb.append("*").append(factor));

            return sb.toString();
        }

        @Override
        public int compareTo(Polynomial o) {
            if (this.factors.size() == o.factors.size()) {
                for (int i = 0; i < factors.size(); i++) {
                    int num = factors.get(i).compareTo(o.factors.get(i));
                    if (num != 0) {
                        return num;
                    }
                }
                return 0;
            }
            return o.factors.size() - this.factors.size();
        }

        public Polynomial mult(Polynomial item) {
            Polynomial result = new Polynomial(this.coeff * item.coeff);
            result.factors.addAll(this.factors);
            result.factors.addAll(item.factors);
            Collections.sort(result.factors);
            return result;
        }
    }

    // 表达式
    static class Expression {

        private List<Polynomial> items;

        Expression(Polynomial item) {
            items = new ArrayList<>();
            items.add(item);
        }

        void add(Expression expression) {
            this.items.addAll(expression.items);
            Collections.sort(this.items);
            clean();
        }

        void mult(Expression expression) {
            List<Polynomial> tmpItems = items;
            items = new ArrayList<>();
            for (Polynomial item1 : tmpItems) {
                for (Polynomial item2 : expression.items) {
                    items.add(item1.mult(item2));
                }
            }
            Collections.sort(this.items);
            clean();
        }

        void clean() {
            List<Polynomial> tmpItems = items;
            items = new ArrayList<>();

            for (Polynomial item : tmpItems) {
                if (items.isEmpty()) {
                    if (item.coeff != 0) {
                        items.add(item);
                    }
                    continue;
                }
                Polynomial last = items.get(items.size() - 1);
                if (last.compareTo(item) == 0) {
                    last.coeff += item.coeff;
                    if (last.coeff == 0) {
                        items.remove(items.size() - 1);
                    }
                } else {
                    items.add(item);
                }
            }
        }

        Expression operate(Expression expression, String op) {
            switch (op) {
                case "+":
                    add(expression);
                    break;
                case "-":
                    {
                        for (Polynomial item : expression.items) {
                            item.coeff *= -1;
                        }
                        add(expression);
                    }
                    break;
                case "*":
                    mult(expression);
                    break;
            }
            return this;
        }
    }

    @Test
    public void basicCalculatorIV() {
        String expression = "0";
        String[] evalvars = {};
        int[] evalints = {};
        logResult(basicCalculatorIV(expression, evalvars, evalints));
    }
}
