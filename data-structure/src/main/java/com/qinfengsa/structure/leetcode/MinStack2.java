package com.qinfengsa.structure.leetcode;

import lombok.extern.slf4j.Slf4j;

/**
 * 面试题30. 包含min函数的栈
 * 定义栈的数据结构，请在该类型中实现一个能够得到栈的最小元素的 min 函数在该栈中，调用 min、push 及 pop 的时间复杂度都是 O(1)。
 *
 *
 *
 * 示例:
 *
 * MinStack minStack = new MinStack();
 * minStack.push(-2);
 * minStack.push(0);
 * minStack.push(-3);
 * minStack.min();   --> 返回 -3.
 * minStack.pop();
 * minStack.top();      --> 返回 0.
 * minStack.min();   --> 返回 -2.
 *
 *
 * 提示：
 *
 * 各函数的调用总次数不超过 20000 次
 * @author: qinfengsa
 * @create: 2020/04/25 21:44
 */
@Slf4j
public class MinStack2 {


    StackNode top;

    int size;


    public MinStack2() {
        top = new StackNode(0);
        size = 0;
    }

    public void push(int x) {

        size++;
        StackNode node = new StackNode(x);
        if (size == 1) {
            node.min = x;
        } else {
            node.min = Math.min(top.min,x);
        }
        node.next = top;
        this.top = node;
    }

    public void pop() {
        if (size == 0) {
            return;
        }
        size--;
        StackNode next = top.next;
        top.next = null;

        top = next;
    }

    public int top() {
        if (size == 0) {
            return -1;
        }
        return top.val;
    }

    public int min() {
        if (size == 0) {
            return -1;
        }
        return top.min;
    }

    static class StackNode {
        int val;
        int min;
        StackNode next;

        StackNode(int val) {
            this.val = val;
        }
    }

    public static void main(String[] args) {
        MinStack2 stack2 = new MinStack2();
        stack2.push(-1);
        log.debug("top:{}",stack2.top());
        log.debug("min:{}",stack2.min());
    }
}
