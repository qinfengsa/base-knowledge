package com.qinfengsa.structure.leetcode;

import java.util.Stack;

/**
 * 用栈实现队列
 * 使用栈实现队列的下列操作：
 *
 * push(x) -- 将一个元素放入队列的尾部。
 * pop() -- 从队列首部移除元素。
 * peek() -- 返回队列首部的元素。
 * empty() -- 返回队列是否为空。
 * 示例:
 *
 * MyQueue queue = new MyQueue();
 *
 * queue.push(1);
 * queue.push(2);
 * queue.peek();  // 返回 1
 * queue.pop();   // 返回 1
 * queue.empty(); // 返回 false
 * 说明:
 *
 * 你只能使用标准的栈操作 -- 也就是只有 push to top, peek/pop from top, size, 和 is empty 操作是合法的。
 * 你所使用的语言也许不支持栈。你可以使用 list 或者 deque（双端队列）来模拟一个栈，只要是标准的栈操作即可。
 * 假设所有操作都是有效的 （例如，一个空的队列不会调用 pop 或者 peek 操作）。
 * @author: qinfengsa
 * @date: 2019/5/27 16:52
 */
public class MyQueue {

    /**
     * 入队栈
     */
    private Stack<Integer> pushStack;

    /**
     * 出队栈
     */
    private Stack<Integer> popStack;

    /**
     * 入队栈栈底元素记录，peek 不出队，防止无用操作
     */
    private Integer peekElement ;

    /** Initialize your data structure here. */
    public MyQueue() {
        pushStack = new Stack();
        popStack = new Stack();
    }

    /**
     * 入队
     * @param x
     */
    public void push(int x) {
        // 当入队栈为空时，记录栈底元素
        if (pushStack.isEmpty()) {
            peekElement = x;
        }

        // 入队栈 入栈
        pushStack.push(x);
    }


    /**
     * 把popStack 元素移入popStack
     */
    private void setPop() {

        while (!pushStack.isEmpty()) {
            popStack.push(pushStack.pop());
        }

    }


    /**
     * 出队，队首元素出队
     * @return
     */
    public int pop() {
        // 1 popStack 为空，出栈，并把元素入栈 popStack
        if (popStack.isEmpty()) {
            setPop();
        }
        // 2 popStack 出栈，就是 队首元素

        return popStack.pop();
    }

    /**
     * 获取队首元素
     * @return
     */
    public int peek() {

        if (popStack.isEmpty()) {
            return peekElement;
        }
        return popStack.peek();

    }

    /**
     * 返回队列是否为空
     * @return
     */
    public boolean empty() {
        boolean pushEmpty = pushStack.isEmpty();
        boolean popEmpty = popStack.isEmpty();
        return pushEmpty && popEmpty;
    }
}
