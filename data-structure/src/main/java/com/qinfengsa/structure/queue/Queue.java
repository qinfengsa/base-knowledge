package com.qinfengsa.structure.queue;

import com.qinfengsa.exception.QueueEmptyException;

/**
 * 队列接口
 * @author: qinfengsa
 * @date: 2019/5/6 10:12
 */
public interface Queue {


    /**
     * 返回队列的大小
     * @return
     */
    int getSize();

    /**
     * 判断队列是否为空
     * @return
     */
    boolean isEmpty();

    /**
     * 数据元素 e 入队
     * @param e
     */
    void enqueue(Object e);

    /**
     * 队首元素出队
     * @return
     * @throws QueueEmptyException
     */
    Object dequeue() throws QueueEmptyException;

    /**
     * 取队首元素
     * @return
     * @throws QueueEmptyException
     */
    Object peek() throws QueueEmptyException;
}
