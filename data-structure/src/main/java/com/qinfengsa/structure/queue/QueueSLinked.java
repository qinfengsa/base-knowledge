package com.qinfengsa.structure.queue;

import com.qinfengsa.exception.QueueEmptyException;
import com.qinfengsa.structure.linearlist.linkedlist.sllist.SLNode;

import java.util.Objects;

/**
 * @author: qinfengsa
 * @date: 2019/5/6 11:26
 */
public class QueueSLinked implements Queue {

    /**
     * 队列队首结点
     */
    private SLNode front;

    /**
     * 队列队尾结点
     */
    private SLNode rear;

    /**
     * 队列大小
     */
    private int size;

    /**
     * 构造方法
     */
    public QueueSLinked() {
        front = new SLNode();
        rear = front;
        size = 0;
    }

    /**
     * 返回队列的大小
     * @return
     */
    @Override
    public int getSize() {
        return size;
    }

    /**
     * 判断队列是否为空
     * @return
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 数据元素 e 入队
     * @param e
     */
    @Override
    public void enqueue(Object e) {
        // 新建结点放入队尾
        SLNode node = new SLNode(e,null);
        rear.setNext(node);
        rear = node;
        size++;

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
        SLNode node = front.getNext();
        Object result = node.getData();

        front.setNext(node.getNext());
        size--;
        node = null;
        if (size < 1) {
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
        SLNode node = front.getNext();
        return node.getData();
    }

    /**
     * toString重写
     * @return
     */
    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append("QueueSLinked{");


        SLNode node = this.front.getNext();
        int index = 0;
        while (Objects.nonNull(node)) {
            if (index > 0) {
                sb.append(",");
            }
            sb.append(node.getData());
            node = node.getNext();
            index++;

        }
        sb.append("}");
        sb.append("size=");
        sb.append(size);
        return sb.toString();
    }
}
