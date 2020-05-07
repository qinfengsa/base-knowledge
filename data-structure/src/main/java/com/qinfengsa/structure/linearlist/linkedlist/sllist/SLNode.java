package com.qinfengsa.structure.linearlist.linkedlist.sllist;

import com.qinfengsa.base.Node;

/**
 * 单链表元素结点
 * @author: qinfengsa
 * @date: 2019/5/3 21:32
 */
public class SLNode implements Node {

    /**
     * 具体元素对象
     */
    private Object element;

    /**
     * 下一个结点元素
     */
    private SLNode next;

    /**
     * 构造方法
     */
    public SLNode() {
        this(null,null);
    }

    /**
     * 构造方法
     * @param element
     * @param next
     */
    public SLNode(Object element, SLNode next) {
        this.element = element;
        this.next = next;
    }

    /**
     * 获取结点数据元素
     * @return
     */
    @Override
    public Object getData() {
        return this.element;
    }

    /**
     * 设置结点数据元素
     * @param obj
     */
    @Override
    public void setData(Object obj) {
        element = obj;
    }


    public SLNode getNext() {
        return next;
    }

    public void setNext(SLNode next) {
        this.next = next;
    }
}
