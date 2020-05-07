package com.qinfengsa.structure.leetcode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 用队列实现栈
 * 使用队列实现栈的下列操作：
 *
 * push(x) -- 元素 x 入栈
 * pop() -- 移除栈顶元素
 * top() -- 获取栈顶元素
 * empty() -- 返回栈是否为空
 * 注意:
 *
 * 你只能使用队列的基本操作-- 也就是 push to back, peek/pop from front, size, 和 is empty 这些操作是合法的。
 * 你所使用的语言也许不支持队列。 你可以使用 list 或者 deque（双端队列）来模拟一个队列 , 只要是标准的队列操作即可。
 * 你可以假设所有操作都是有效的（例如, 对一个空的栈不会调用 pop 或者 top 操作）。
 * @author: qinfengsa
 * @date: 2019/5/27 17:14
 */
public class MyStack {

    /**
     *   队列
     */
    private Queue<Integer> myQueue;


    /** Initialize your data structure here. */
    public MyStack() {

        myQueue = new LinkedList<>();
    }

    /**
     * 入栈 入栈队列入队，队列为空记录topElement
     * @param x
     */
    public void push(int x) {

        myQueue.offer(x);

        // 将前面的size-1个元素放到后面去
        int size = myQueue.size();
        while (size > 1) {
            int num = myQueue.poll();
            myQueue.offer(num);
            size--;
        }
    }


    /**
     * 出栈
     * @return
     */
    public int pop() {

        return myQueue.poll();
    }

    /**
     * 获得栈顶元素
     * @return
     */
    public int top() {
        return myQueue.peek();
    }

    /** Returns whether the stack is empty. */
    public boolean empty() {

        return myQueue.isEmpty();
    }
}
