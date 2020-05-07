package com.qinfengsa.exception;

/**
 * 参数的结点不合法异常
 * @author: qinfengsa
 * @date: 2019/5/4 11:56
 */
public class InvalidNodeException extends RuntimeException {

    /**
     * 序列id
     */
    private static final long serialVersionUID = 6061623880315575140L;

    /**
     * 构造方法
     * @param message
     */
    public InvalidNodeException(String message) {
        super(message);
    }
}
