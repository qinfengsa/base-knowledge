package com.qinfengsa.structure.linearlist.linkedlist.dllist;

import com.qinfengsa.base.Node;

/**
 * @author: qinfengsa
 * @date: 2019/5/3 21:41
 */
public class DLNode implements Node {


    /**
     * 具体元素对象
     */
    private Object element;

    /**
     * 上一个结点元素
     */
    private DLNode pre;

    /**
     * 下一个结点元素
     */
    private DLNode next;

    /**
     * 构造方法
     */
    public DLNode() {
        this(null,null,null);
    }

    /**
     * 构造方法
     * @param element
     * @param pre
     * @param next
     */
    public DLNode(Object element, DLNode pre, DLNode next) {
        this.element = element;
        this.pre = pre;
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

    public DLNode getPre() {
        return pre;
    }

    public void setPre(DLNode pre) {
        this.pre = pre;
    }

    public DLNode getNext() {
        return next;
    }

    public void setNext(DLNode next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "DLNode{" +
                "element=" + element +
                '}';
    }
}
