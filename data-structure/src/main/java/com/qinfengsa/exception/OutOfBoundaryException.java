package com.qinfengsa.exception;

/**
 * 线性表中出现序号越界时抛出该异常
 * @author: qinfengsa
 * @date: 2019/5/3 11:25
 */
public class OutOfBoundaryException extends RuntimeException {

    /**
     * 序列化id
     */
    private static final long serialVersionUID = -6092279180954688959L;

    /**
     * 构造方法
     */
    public OutOfBoundaryException() {
        super();
    }

    /**
     * 构造方法
     * @param message
     */
    public OutOfBoundaryException(String message) {
        super(message);
    }
}
