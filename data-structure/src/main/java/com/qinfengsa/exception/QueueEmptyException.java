package com.qinfengsa.exception;

/**
 * 队列为空时出队或取队首元素抛出此异常
 * @author: qinfengsa
 * @date: 2019/5/6 10:15
 */
public class QueueEmptyException  extends RuntimeException {

    /**
     * 序列id
     */
    private static final long serialVersionUID = 1640485122712135209L;

    /**
     * 构造方法
     * @param message
     */
    public QueueEmptyException(String message) {
        super(message);
    }
}
