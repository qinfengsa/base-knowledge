package com.qinfengsa.structure.leetcode;

import lombok.extern.slf4j.Slf4j;

/**
 * 641. 设计循环双端队列
 *
 * <p>设计实现双端队列。 你的实现需要支持以下操作：
 *
 * <p>MyCircularDeque(k)：构造函数,双端队列的大小为k。 insertFront()：将一个元素添加到双端队列头部。 如果操作成功返回 true。
 * insertLast()：将一个元素添加到双端队列尾部。如果操作成功返回 true。 deleteFront()：从双端队列头部删除一个元素。 如果操作成功返回 true。
 * deleteLast()：从双端队列尾部删除一个元素。如果操作成功返回 true。 getFront()：从双端队列头部获得一个元素。如果双端队列为空，返回 -1。
 * getRear()：获得双端队列的最后一个元素。 如果双端队列为空，返回 -1。 isEmpty()：检查双端队列是否为空。 isFull()：检查双端队列是否满了。 示例：
 *
 * <p>MyCircularDeque circularDeque = new MycircularDeque(3); // 设置容量大小为3
 * circularDeque.insertLast(1); // 返回 true circularDeque.insertLast(2); // 返回 true
 * circularDeque.insertFront(3); // 返回 true circularDeque.insertFront(4); // 已经满了，返回 false
 * circularDeque.getRear(); // 返回 2 circularDeque.isFull(); // 返回 true circularDeque.deleteLast();
 * // 返回 true circularDeque.insertFront(4); // 返回 true circularDeque.getFront(); // 返回 4
 *
 * <p>提示：
 *
 * <p>所有值的范围为 [1, 1000] 操作次数的范围为 [1, 1000] 请不要使用内置的双端队列库。
 *
 * @author qinfengsa
 * @date 2020/07/09 11:13
 */
@Slf4j
public class MyCircularDeque {
    /** 队列元素集合 */
    private Integer[] elements;

    /** 队首指针,指向队首 */
    private int front;

    /** 队尾指针,指向队尾的后一个位置 */
    private int rear;

    /** 数组容量大小 */
    private int capacity;

    /**
     * 构造函数,双端队列的大小为k
     *
     * @param k
     */
    public MyCircularDeque(int k) {
        capacity = k + 1;
        front = rear = 0;
        elements = new Integer[capacity];
    }

    /**
     * 将一个元素添加到双端队列头部。 如果操作成功返回 true。
     *
     * @param value
     * @return
     */
    public boolean insertFront(int value) {
        if (isFull()) {
            return false;
        }
        front = (front - 1 + capacity) % capacity;
        elements[front] = value;
        return true;
    }

    /**
     * 将一个元素添加到双端队列尾部。如果操作成功返回 true。
     *
     * @param value
     * @return
     */
    public boolean insertLast(int value) {
        if (isFull()) {
            return false;
        }
        elements[rear] = value;
        rear = (rear + 1) % capacity;
        return true;
    }

    /**
     * 从双端队列头部删除一个元素。 如果操作成功返回 true。
     *
     * @return
     */
    public boolean deleteFront() {
        if (isEmpty()) {
            return false;
        }
        elements[front] = null;
        front = (front + 1) % capacity;
        if (front == rear) {
            front = 0;
            rear = 0;
        }
        return true;
    }

    /**
     * 从双端队列尾部删除一个元素。如果操作成功返回 true。
     *
     * @return
     */
    public boolean deleteLast() {
        if (isEmpty()) {
            return false;
        }
        rear = (rear - 1 + capacity) % capacity;
        elements[rear] = null;
        if (front == rear) {
            front = 0;
            rear = 0;
        }
        return true;
    }

    /**
     * 从双端队列头部获得一个元素。如果双端队列为空，返回 -1。
     *
     * @return
     */
    public int getFront() {
        return isEmpty() ? -1 : elements[front];
    }

    /**
     * 获得双端队列的最后一个元素。 如果双端队列为空，返回 -1。
     *
     * @return
     */
    public int getRear() {
        if (isEmpty()) {
            return -1;
        }
        int index = (rear - 1 + capacity) % capacity;
        return elements[index];
    }

    /**
     * 检查双端队列是否为空。
     *
     * @return
     */
    public boolean isEmpty() {

        return front == rear;
    }

    /**
     * 检查双端队列是否满了。
     *
     * @return
     */
    public boolean isFull() {
        int a = (rear - front + 1 + capacity) % capacity;
        if (a == 0) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {

        MyCircularDeque circularDeque = new MyCircularDeque(3); // 设置容量大小为3
        boolean b1 = circularDeque.insertLast(1); // 返回 true
        log.debug("b1:{}", b1);
        boolean b2 = circularDeque.insertLast(2); // 返回 true
        log.debug("b2:{}", b2);
        boolean b3 = circularDeque.insertFront(3); // 返回 true
        log.debug("b3:{}", b3);
        boolean b4 = circularDeque.insertFront(4); // 已经满了，返回 false
        log.debug("b4:{}", b4);
        int v1 = circularDeque.getRear(); // 返回 2
        log.debug("v1:{}", v1);
        boolean b5 = circularDeque.isFull(); // 返回 true
        log.debug("b5:{}", b5);
        boolean b6 = circularDeque.deleteLast(); // 返回 true
        log.debug("b6:{}", b6);
        boolean b7 = circularDeque.insertFront(4); // 返回 true
        log.debug("b7:{}", b7);
        int v2 = circularDeque.getFront(); // 返回 4
        log.debug("v2:{}", v2);
        boolean b8 = circularDeque.deleteLast();

        log.debug("b8:{}", b8);
        boolean b9 = circularDeque.insertFront(3); // 返回 true
        log.debug("b9:{}", b9);
    }
}
