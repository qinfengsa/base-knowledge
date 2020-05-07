package com.qinfengsa.structure.queue;

import com.qinfengsa.exception.QueueEmptyException;
import com.qinfengsa.exception.StackEmptyException;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

/**
 * @author: qinfengsa
 * @date: 2019/5/6 10:44
 */
@Slf4j
public class QueueArray implements Queue {

    /**
     * 队列默认大小
     */
    private static final int CAP = 7;

    /**
     * 数据元素数组
     */
    private Object[] elements;

    /**
     * 数组的大小 队列容量
     */
    private int capacity;

    /**
     * 队首指针,指向队首
     */
    private int front;

    /**
     * 队尾指针,指向队尾的后一个位置
     */
    private int rear;

    /**
     * 构造方法
     */
    public QueueArray() {
        this(CAP);
    }

    /**
     * 构造方法
     * @param cap
     */
    public QueueArray(int cap){
        capacity = cap + 1;
        elements = new Object[capacity];
        front = rear = 0;
    }

    /**
     * 返回队列的大小
     * @return
     */
    @Override
    public int getSize() {
        // 队尾-队首+总容量 ps:（有可能队首>队尾）
        return (rear - front + capacity)%capacity;
    }

    /**
     * 判断队列是否为空
     * @return
     */
    @Override
    public boolean isEmpty() {
        // 队尾和队首相遇，说明队列为空
        return rear == front;
    }

    /**
     * 数据元素 e 入队
     * @param e
     */
    @Override
    public void enqueue(Object e) {

        int size = getSize();
        if (size >= capacity-1) {
            expendSpace();
        }
        elements[rear] = e;
        rear = (rear+1)%capacity;

    }

    /**
     * 队首元素出队
     * @return
     * @throws QueueEmptyException
     */
    @Override
    public Object dequeue() throws QueueEmptyException {
        if (isEmpty()) {
            throw new QueueEmptyException("错误：队列为空");
        }
        Object result = elements[front];

        elements[front] = null;

        front = (front+1)%capacity;
        if (front == rear) {
            front = 0;
            rear = front;
        }

        return result;
    }

    /**
     * 取队首元素
     * @return
     * @throws QueueEmptyException
     */
    @Override
    public Object peek() throws QueueEmptyException {
        if (isEmpty()) {
            throw new QueueEmptyException("错误：队列为空");
        }
        return elements[front];
    }

    /**
     * 扩展空间
     */
    private void expendSpace() {

        Object[] newElements = new Object[elements.length*2];
        int i = front; int j = 0;
        while (i!=rear){
            //将从 front 开始到 rear 前一个存储单元的元素复制到新数组
            newElements[j++] = elements[i];
            i = (i+1)%capacity;
        }
        elements = newElements;
        capacity = elements.length;
        // 设置新的队首、队尾指针
        front = 0;
        rear = j;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("QueueArray{");
        int size = getSize();
        int index = front;
        for (int i = 0; i < size; i++) {
            if (i > 0) {
                sb.append(",");
            }
            if (index >= elements.length) {
                index = 0;
            }
            sb.append(elements[index]);
            index++;
        }
        sb.append("}");

        log.debug( "QueueArray{ elements={},size={}}" , Arrays.toString(elements), getSize());
        return sb.toString();
    }
}
