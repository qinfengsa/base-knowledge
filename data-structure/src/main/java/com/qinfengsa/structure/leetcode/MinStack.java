package com.qinfengsa.structure.leetcode;

import java.util.Objects;

/**
 * 最小栈
 * 设计一个支持 push，pop，top 操作，并能在常数时间内检索到最小元素的栈。
 * @author: qinfengsa
 * @date: 2019/5/6 17:15
 */
public class MinStack {


    /**
     * 栈大小
     */
    private int size;

    /**
     * 栈顶位置
     */
    private MyNode top;



    public MinStack( ) {
        size = 0;
        top = null;
    }

    /**
     * 将元素 x 推入栈中。
     * @param x
     */
    public void push(int x) {
        Integer minNum;
        if (Objects.isNull(top)) {
            minNum = x;
        } else {
            minNum = top.min;
        }
        if (x < minNum) {
            minNum = x;
        }
        MyNode node = new MyNode(x);
        node.next = top;
        node.min = minNum;
        top = node;
        size++;
    }



    /**
     * 删除栈顶的元素。
     */
    public void pop() {
        if (size <= 0) {
            return;
        }
        top = top.next;
        size--;

    }

    /**
     * 获取栈顶元素。
     * @return
     */
    public int top() {
        if (size <= 0) {
            return -1;
        }
        return top.val;
    }


    /**
     * 检索栈中的最小元素。
     * @return
     */
    public int getMin() {
        if (size <= 0) {
            return -1;
        }
        return top.min;
    }


    class MyNode {
        /**
         * 当前节点的值
         */
        private int val;

        private Integer min;



        /**
         * 是指向下一个节点的指针/引用
         */
        private MyNode next;

        public MyNode(int val) {
            this.val = val;
            this.next = null;
        }


    }
}


