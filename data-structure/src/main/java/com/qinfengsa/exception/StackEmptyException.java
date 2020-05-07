package com.qinfengsa.exception;

/**
 * 堆栈为空时出栈或取栈顶元素抛出此异常
 * @author: qinfengsa
 * @date: 2019/5/5 20:08
 */
public class StackEmptyException extends RuntimeException {

    /**
     * 序列id
     */
    private static final long serialVersionUID = -989672631162733841L;

    /**
     * 构造方法
     * @param message
     */
    public StackEmptyException(String message) {
        super(message);
    }
}
