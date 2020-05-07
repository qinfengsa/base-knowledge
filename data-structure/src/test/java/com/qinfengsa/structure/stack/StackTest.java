package com.qinfengsa.structure.stack;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Objects;

/**
 * 栈测试
 * @author: qinfengsa
 * @date: 2019/5/6 08:12
 */
@Slf4j
public class StackTest {

    /**
     * 测试括号匹配
     */
    @Test
    public void testBracketMatch() {
        boolean b = bracketMatch("abcdd我的([])");
        log.debug("b:{}",b);
    }


    /**
     * 括号匹配
     * @param str
     * @return
     */
    private boolean bracketMatch(String str) {
        char[] chars = str.toCharArray();
        Stack stack = new StackSLinked();

        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            switch (c) {
                case '{' :
                case '[' :
                case '(' :
                    stack.push(Integer.valueOf(c));break;
                case '}' :
                    if (!stack.isEmpty() && ((Integer)stack.pop()).intValue() == '{') {
                        break;
                    } else {
                        return false;
                    }
                case ']' :
                    if (!stack.isEmpty() && ((Integer)stack.pop()).intValue() == '[') {
                        break;
                    } else {
                        return false;
                    }
                case ')' :
                    if (!stack.isEmpty() && ((Integer)stack.pop()).intValue() == '(') {
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
     * 利用栈实现进制转换
     */
    @Test
    public void testBaseConversion() {

        baseConversion(2007,8);
    }



    /**
     * 10进制转n进制
     * @param num
     */
    private void baseConversion(int num,int n) {
        if (n <= 1) {
            return;
        }

        Stack stack = new StackSLinked();

        while (num >0) {
            int a = num%n;
            num = num /n;
            stack.push(a);
        }
        log.debug(stack.toString());
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        log.debug(sb.toString());




    }


    @Test
    public void test1() {
        Stack stack = new StackArray();
        stack.push("A");
        stack.push("B");
        log.debug(stack.toString());
        stack.push("C");
        stack.push("D");
        stack.push("E");
        stack.push("F");
        log.debug(stack.toString());
        stack.push("G");
        stack.push("H");
        stack.push("K");
        log.debug(stack.toString());
        log.debug("a:{}",stack.peek());
        log.debug("b:{}",stack.pop());
        log.debug(stack.toString());
        stack.pop();
        stack.pop();
        stack.pop();
        log.debug(stack.toString());

    }

    @Test
    public void test2() {
        Stack stack = new StackSLinked();
        stack.push("A");
        stack.push("B");
        log.debug(stack.toString());
        /*stack.push("C");
        stack.push("D");
        stack.push("E");
        stack.push("F");
        log.debug(stack.toString());
        stack.push("G");
        stack.push("H");
        stack.push("K");*/
        log.debug(stack.toString());
        log.debug("a:{}",stack.peek());
        log.debug("b:{}",stack.pop());
        log.debug(stack.toString());
        stack.pop();
        stack.pop();
        stack.pop();
        log.debug(stack.toString());

    }

}
