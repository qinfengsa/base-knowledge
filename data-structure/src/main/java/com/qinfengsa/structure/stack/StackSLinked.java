package com.qinfengsa.structure.stack;

import com.qinfengsa.exception.StackEmptyException;
import com.qinfengsa.structure.linearlist.linkedlist.sllist.SLNode;

import java.util.Objects;

/**
 * 栈的链式存储，链表实现
 * @author: qinfengsa
 * @date: 2019/5/6 09:55
 */
public class StackSLinked implements Stack {

    /**
     * 栈顶元素
     */
    private SLNode top;

    /**
     * 栈大小
     */
    private int size;

    /**
     * 构造方法
     */
    public StackSLinked( ) {
        this.top = null;
        size = 0;
    }

    /**
     * 返回堆栈的大小
     * @return
     */
    @Override
    public int getSize() {
        return size;
    }

    /**
     * 判断堆栈是否为空
     * @return
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 数据元素 e 入栈
     * @param e
     */
    @Override
    public void push(Object e) {
        SLNode node = new SLNode(e,top);
        top = node;
        size++;

    }

    /**
     * 栈顶元素出栈
     * @return
     * @throws StackEmptyException
     */
    @Override
    public Object pop() throws StackEmptyException {
        if (size <= 0) {
            throw new StackEmptyException("错误：堆栈为空");
        }
        SLNode node = top;
        top = top.getNext();
        size--;
        return node.getData();
    }

    /**
     * 取栈顶元素
     * @return
     * @throws StackEmptyException
     */
    @Override
    public Object peek() throws StackEmptyException {
        if (size <= 0) {
            throw new StackEmptyException("错误：堆栈为空");
        }
        return top.getData();
    }

    /**
     * toString重写
     * @return
     */
    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append("StackSLinked{");


        SLNode node = this.top;
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
