package com.qinfengsa.structure.leetcode;

import java.util.Objects;

/**
 * @author: qinfengsa
 * @date: 2019/5/6 15:58
 */
public class MyCircularQueue2 {
    /**
     * 数组容量大小
     */
    private int capacity;

    /**
     * 队首指针
     */
    private int front;

    /**
     * 队尾指针
     */
    private int rear;

    /**
     * 队列元素集合
     */
    private Integer[] elements;


    /**
     * 构造器，设置队列长度为 k 。
     * @param k
     */
    public MyCircularQueue2(int k) {
        capacity = k + 1;
        front = 0;
        rear = 0;
        elements = new Integer[capacity];

    }

    /**
     * 向循环队列插入一个元素。如果成功插入则返回真。
     * @param value
     * @return
     */
    public boolean enQueue(int value) {
        if (isFull()) {
            return false;
        }

        elements[rear] = value;
        rear = (rear + 1)%capacity;
        return true;
    }

    /**
     * 从循环队列中删除一个元素。如果成功删除则返回真。
     * @return
     */
    public boolean deQueue() {
        if (isEmpty()) {
            return false;
        }

        elements[front] = null;
        front = (front + 1)%capacity;
        if (front == rear) {
            front = 0;
            rear = 0;
        }
        return true;
    }

    /**
     * 从队首获取元素。如果队列为空，返回 -1 。
     */
    public int Front() {
        if (!isEmpty()) {
            return elements[front];
        }
        return -1;
    }

    /**
     * 获取队尾元素。如果队列为空，返回 -1 。
     * @return
     */
    public int Rear() {
        if (!isEmpty()) {
            int index = (rear-1 + capacity)%capacity;
            return elements[index];
        }
        return -1;
    }

    /**
     * 检查循环队列是否为空。
     * @return
     */
    public boolean isEmpty() {
        return front == rear;
    }

    /**
     * 检查循环队列是否为空。
     * @return
     */
    public boolean isFull() {
        int a = (rear - front + 1 + capacity)%capacity;
        if (a == 0) {
            return true;
        }
        return false;
    }
}
