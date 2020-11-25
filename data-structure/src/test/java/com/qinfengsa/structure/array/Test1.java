package com.qinfengsa.structure.array;

import static com.qinfengsa.structure.util.LogUtils.logResult;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

/**
 * @author: qinfengsa
 * @date: 2019/5/13 13:26
 */
@Slf4j
public class Test1 {

    @Test
    public void test11() {

        TestFather inf = new TestSon();

        Object result = null;
        try {
            result = inf.add(1);
        } catch (Exception e) {
            e.printStackTrace();
        }

        log.debug("result:{}", result);
    }

    @Test
    public void countA() {
        String txt = "AAA bc";
        logResult(countA(txt));
    }

    public int countA(String input) {
        if (input == null || input.length() == 0) {
            return 0;
        }
        return countA(input, 0);
    }

    private int countA(String input, int count) {
        if (input == null || input.length() == 0) {
            return count;
        }
        if (input.charAt(0) == 'A') {
            count++;
        }
        return countA(input.substring(1), count);
    }

    @Test
    public void getTotalCount() {
        int monthCount = 9;
        logResult(getTotalCount(monthCount));
    }

    public int getTotalCount(int monthCount) {
        // 1 个月 2 个月 3 个月
        int a = 1, b = 0, c = 0;
        while (monthCount > 1) { // 每过一个月兔子数变化
            c += b;
            b = a;
            a = c;
            monthCount--;
        }
        return a + b + c;
    }

    @Test
    public void getIndex() {
        int num = 3;
        logResult(getIndex(num));
    }

    public int getIndex(int num) {
        int len = num * 2 - 1;
        int[] nums = new int[len];
        nums[0] = 1;
        for (int i = 1; i < num; i++) {
            int l = 2 * i;
            for (int j = l; j >= 0; j--) {
                int sum = 0;
                for (int k = 0; k < 3; k++) {
                    if (j - k >= 0) {
                        sum += nums[j - k];
                    }
                }
                nums[j] = sum;
            }
        }
        for (int i = 0; i < len; i++) {
            if ((nums[i] & 1) == 0) {
                return i + 1;
            }
        }
        return -1;
    }

    @Test
    public void getParam() {
        String str = "xcopy /s \"C:\\program files\" \"d:\\\"";
        List<String> result = getParam(str);
        System.out.println(result.size());
        for (String s : result) {
            System.out.println(s);
        }
    }

    public List<String> getParam(String str) {
        List<String> result = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        boolean flag = false;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c == ' ') {
                if (!flag) {
                    result.add(sb.toString());
                    sb = new StringBuilder();
                } else {
                    sb.append(c);
                }
            } else if (c == '"') {
                flag = !flag;
            } else {
                sb.append(c);
            }
        }
        if (sb.length() > 0) {
            result.add(sb.toString());
        }

        return result;
    }

    @Test
    public void calculate() {
        String expression = "400 + ( 1 - 7) * 2 ";
        logResult(calculate(expression));
    }

    public int calculate(String expression) {
        Deque<Character> optStack = new LinkedList<>();
        int len = expression.length();
        char[] exp = expression.toCharArray();

        ArrayList<String> list = new ArrayList<>();
        StringBuilder numStr = new StringBuilder();
        for (int i = 0; i < exp.length; i++) {
            if (exp[i] == ' ') {
                continue;
            }
            if (exp[i] == '(') {
                optStack.push(exp[i]);
            } else if (exp[i] == ')') {
                while (!optStack.isEmpty() && optStack.peek() != '(') {
                    list.add(optStack.pop() + "");
                }
                optStack.pop();
            } else if (exp[i] >= '0' && exp[i] <= '9') {
                numStr.append(exp[i]);
                while (i < len - 1 && exp[i + 1] >= '0' && exp[i + 1] <= '9') {
                    i++;
                    numStr.append(exp[i]);
                }
                list.add(numStr.toString());
                numStr.setLength(0);
            } else {
                while (!optStack.isEmpty() && cLevel(exp[i]) <= cLevel(optStack.peek())) {
                    list.add(optStack.pop() + "");
                }
                optStack.push(exp[i]);
            }
        }
        while (!optStack.isEmpty()) {
            list.add(optStack.pop() + "");
        }
        return calculateA(list);

        /*for (int i = 0; i < exp.length; i++) {
        if (exp[i] == ' ') {
            continue;
        }
        if (exp[i] >= '0' && exp[i] <= '9') {
            int num = exp[i] - '0';
            while (i < len - 1 && exp[i + 1] >= '0' && exp[i + 1] <= '9') {
                i++;
                num = num * 10 + exp[i] - '0';
            }
            numStack.push(num);
        } else if (exp[i] == '(') {
            optStack.push(exp[i]);
        } else if (exp[i] == '+' || exp[i] == '-' || exp[i] == '*' || exp[i] == '/') {
            if (exp[i] == '-') {
                int index = i - 1;
                while (index >= 0 && exp[index] == ' ') {
                    index--;
                }
                logResult(index);
                if (index <= 0
                        || exp[index] == '('
                        || exp[index] == '+'
                        || exp[index] == '-'
                        || exp[index] == '*'
                        || exp[index] == '/') {
                    numStack.push(0);
                }
            }
            while (!optStack.isEmpty() && cLevel(exp[i]) < cLevel(optStack.peek())) {
                dealStack(optStack, numStack);
            }

            optStack.push(exp[i]);
        } else if (exp[i] == ')') {
            while (!optStack.isEmpty() && optStack.peek() != '(') {
                dealStack(optStack, numStack);
            }
            // 去掉 '('
            optStack.pop();
        }
        */
        /*else {
            if (optStack.isEmpty()) {
                // 运算符栈栈顶为空则直接入栈
                optStack.push(c);
            } else {
                if (c == '(') {
                    // 当前运算符为左括号，直接入运算符栈
                    optStack.push(c);
                } else if (c == ')') {
                    // 当前运算符为右括号，触发括号内的字表达式进行计算
                    directCalc(optStack, numStack, true);
                } else {
                    // 当前运算符为加减乘除之一，要与栈顶运算符比较，判断是否要进行一次二元计算
                    compareAndCalc(optStack, numStack, curOpt);
                }
            }
        }*/
        /*
        }
        while (!optStack.isEmpty()) {
            dealStack(optStack, numStack);
        }
        return numStack.pop();*/
    }

    public int calculateA(List<String> list) {
        Deque<Integer> stack = new LinkedList<>();
        for (int i = 0; i < list.size(); i++) {
            String s = list.get(i);
            if (s.matches("\\d+")) {
                stack.push(Integer.parseInt(s));
            } else {
                int num1 = stack.pop();
                int num2 = stack.pop();
                int res = getResult(num1, num2, s);
                stack.push(res);
            }
        }
        return stack.pop();
    }

    // 符号等级
    static int cLevel(char c) {
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

    public static int getResult(int num1, int num2, String oper) {
        int res = 0;
        switch (oper) {
            case "+":
                res = num2 + num1;
                break;
            case "-":
                res = num2 - num1;
                break;
            case "*":
                res = num2 * num1;
                break;
            case "/":
                res = num2 / num1;
                break;
            default:
                break;
        }
        return res;
    }

    // 对栈进行运算
    private void dealStack(Deque<Character> optStack, Deque<Integer> numStack) {
        char c = optStack.pop();
        int num1 = numStack.pop();
        int num2 = numStack.pop();
        switch (c) {
            case '+':
                numStack.push(num1 + num2);
                break;
            case '-':
                numStack.push(num1 - num2);
                break;
            case '*':
                numStack.push(num1 * num2);
                break;
            case '/':
                numStack.push(num1 / num2);
                break;
        }
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
}
