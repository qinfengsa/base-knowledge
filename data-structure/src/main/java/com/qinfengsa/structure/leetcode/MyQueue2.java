package com.qinfengsa.structure.leetcode;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 面试题 03.04. 化栈为队
 * 实现一个MyQueue类，该类用两个栈来实现一个队列。
 *
 *
 * 示例：
 *
 * MyQueue queue = new MyQueue();
 *
 * queue.push(1);
 * queue.push(2);
 * queue.peek();  // 返回 1
 * queue.pop();   // 返回 1
 * queue.empty(); // 返回 false
 *
 * 说明：
 *
 * 你只能使用标准的栈操作 -- 也就是只有 push to top, peek/pop from top, size 和 is empty 操作是合法的。
 * 你所使用的语言也许不支持栈。你可以使用 list 或者 deque（双端队列）来模拟一个栈，只要是标准的栈操作即可。
 * 假设所有操作都是有效的 （例如，一个空的队列不会调用 pop 或者 peek 操作）。
 * @author: qinfengsa
 * @create: 2020/04/19 08:04
 */
@Slf4j
public class MyQueue2 {

    // 入队栈
    private Deque<Integer> inDeque;

    // 出队栈
    private Deque<Integer> outDeque;

    private int size;

    /** Initialize your data structure here. */
    public MyQueue2() {
        this.inDeque = new LinkedList<>();
        this.outDeque = new LinkedList<>();
        this.size = 0;
    }

    /**
     * 入队
     * @param x
     */
    public void push(int x) {
        inDeque.push(x);
        size++;
    }


    /**
     * 出队
     * @return
     */
    public int pop() {
        if (size == 0) {
            return -1;
        }
        if (outDeque.isEmpty()) {
            while (!inDeque.isEmpty()) {
                outDeque.push(inDeque.pop());
            }

        }
        size--;
        return outDeque.pop();
    }

    /**
     * 队首元素
     * @return
     */
    public int peek() {
        if (size == 0) {
            return -1;
        }
        if (outDeque.isEmpty()) {
            while (!inDeque.isEmpty()) {
                outDeque.push(inDeque.pop());
            }
        }
        return outDeque.peek();
    }

    /** Returns whether the queue is empty. */
    public boolean empty() {
        return this.size == 0;
    }


    public static void main(String[] args) {
        test1();
    }


    public static void test1() {
        MyQueue2 queue = new MyQueue2();

        queue.push(1);
        queue.push(2);
        int index = 0;
        log.debug("{},{}",index++,queue.peek());  // 返回 1
        log.debug("{},{}",index++,queue.pop());   // 返回 1
        log.debug("{},{}",index++,queue.empty()); // 返回 false
    }

}
