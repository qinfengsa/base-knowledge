package com.qinfengsa.structure.leetcode;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

/**
 * 面试题 03.01. 三合一
 * 三合一。描述如何只用一个数组来实现三个栈。
 *
 * 你应该实现push(stackNum, value)、pop(stackNum)、isEmpty(stackNum)、peek(stackNum)方法。stackNum表示栈下标，value表示压入的值。
 *
 * 构造函数会传入一个stackSize参数，代表每个栈的大小。
 *
 * 示例1:
 *
 *  输入：
 * ["TripleInOne", "push", "push", "pop", "pop", "pop", "isEmpty"]
 * [[1], [0, 1], [0, 2], [0], [0], [0], [0]]
 *  输出：
 * [null, null, null, 1, -1, -1, true]
 * 说明：当栈为空时`pop, peek`返回-1，当栈满时`push`不压入元素。
 * 示例2:
 *
 *  输入：
 * ["TripleInOne", "push", "push", "push", "pop", "pop", "pop", "peek"]
 * [[2], [0, 1], [0, 2], [0, 3], [0], [0], [0], [0]]
 *  输出：
 * [null, null, null, null, 2, 1, -1, -1]
 * @author: qinfengsa
 * @create: 2020/04/18 07:11
 */
@Slf4j
public class TripleInOne {

    // 大小
    int stackSize;

    // 栈存储
    int[] tripleStack;

    // 栈顶
    int[] tops;


    public TripleInOne(int stackSize) {
        this.stackSize = stackSize;
        this.tripleStack = new int[3 * stackSize];
        this.tops = new int[3];
        for (int i = 0; i < 3; i++) {
            tops[i] = i * stackSize - 1;
        }
    }

    /**
     * 入栈
     * 当栈满时`push`不压入元素。
     * @param stackNum
     * @param value
     */
    public void push(int stackNum, int value) {
        int top = tops[stackNum];
        int start = stackNum * stackSize;
        int end = (stackNum + 1) * stackSize - 1;
        if (top == end) {
            return;
        }
        tops[stackNum] = ++top;
        tripleStack[top] = value;
    }

    public int pop(int stackNum) {
        int start = stackNum * stackSize;
        int end = (stackNum + 1) * stackSize - 1;
        int top = tops[stackNum];
        if (top == start - 1) {
            return -1;
        }
        int value = tripleStack[top];
        tops[stackNum]--;
        return value;
    }

    public int peek(int stackNum) {
        int start = stackNum * stackSize;
        int top = tops[stackNum];
        if (top == start - 1) {
            return -1;
        }
        return tripleStack[top];
    }

    public boolean isEmpty(int stackNum) {
        int start = stackNum * stackSize;
        int top = tops[stackNum];
        return top == start - 1;
    }

    public static void main(String[] args) {
        TripleInOne stack = new TripleInOne(2);
        stack.push(0,1);
        stack.push(0,2);
        stack.push(0,3);
        log.debug("1;{}",stack.pop(0));
        log.debug("2;{}",stack.pop(0));
        log.debug("3;{}",stack.pop(0));
        log.debug("4;{}",stack.peek(0));
    }
}
