package com.qinfengsa.structure.stack;

import com.qinfengsa.exception.StackEmptyException;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

/**
 * 栈的顺序存储，数组实现
 * @author: qinfengsa
 * @date: 2019/5/6 07:59
 */
@Slf4j
public class StackArray implements Stack {


    /**
     * 默认数组大小
     */
    private static final int LEN = 8;



    /**
     * 数组元素数组
     */
    private Object[] elements;

    /**
     * 栈顶,指向栈顶元素的数组下标
     */
    private int top;

    /**
     * 栈的构造方法
     */
    public StackArray( ) {
        this.elements = new Object[LEN];
        this.top = -1;
    }

    /**
     * 返回堆栈的大小
     * @return
     */
    @Override
    public int getSize() {
        return top + 1;
    }

    /**
     * 判断堆栈是否为空
     * @return
     */
    @Override
    public boolean isEmpty() {
        return top < 0;
    }

    /**
     * 数据元素 e 入栈
     * @param e
     */
    @Override
    public void push(Object e) {


        if (getSize() >= elements.length) {
            expendSpace();
        }
        top++;
        elements[top] = e;

    }


    /**
     * 扩展空间
     */
    private void expendSpace() {

        Object[] newElements = new Object[elements.length*2];
        for (int i = 0; i < elements.length; i++) {
            newElements[i] = elements[i];
        }
        elements = newElements;
    }

    /**
     * 栈顶元素出栈
     * @return
     * @throws StackEmptyException
     */
    @Override
    public Object pop() throws StackEmptyException {
        if (top < 0) {
            throw new StackEmptyException("错误：堆栈为空");
        }
        Object result = elements[top];
        //清空栈顶
        elements[top] = null;
        top--;
        return result;
    }

    /**
     * 取栈顶元素
     * @return
     * @throws StackEmptyException
     */
    @Override
    public Object peek() throws StackEmptyException {
        if (top < 0) {
            throw new StackEmptyException("错误：堆栈为空");
        }
        return elements[top];
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("StackArray{");
        for (int i = 0; i <= top; i++) {
            if (i > 0) {
                sb.append(",");
            }
            sb.append(elements[i]);
        }
        sb.append("}");

        log.debug( "StackArray{" +
                "elements=" + Arrays.toString(elements) +
                '}');
        return sb.toString();
    }
}
