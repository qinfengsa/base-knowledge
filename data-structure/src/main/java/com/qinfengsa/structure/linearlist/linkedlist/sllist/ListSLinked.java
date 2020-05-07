package com.qinfengsa.structure.linearlist.linkedlist.sllist;

import com.qinfengsa.exception.OutOfBoundaryException;
import com.qinfengsa.structure.linearlist.DefaultStrategy;
import com.qinfengsa.structure.linearlist.LinearList;
import com.qinfengsa.structure.linearlist.Strategy;

import java.util.Objects;

/**
 * 单链表
 * @author: qinfengsa
 * @date: 2019/5/3 21:56
 */
public class ListSLinked implements LinearList {

    /**
     * 头结点
     */
    private SLNode head;

    /**
     * 链表大小
     */
    private int size;

    /**
     * 比较策略
     */
    private Strategy strategy;


    /**
     * 构造方法
     */
    public ListSLinked() {
        this(null,new DefaultStrategy());
    }

    /**
     * 构造方法
     * @param head
     * @param strategy
     */
    public ListSLinked(SLNode head, Strategy strategy) {
        this.head = head;
        this.strategy = strategy;
    }

    /**
     * 返回链表的大小
     * @return
     */
    @Override
    public int getSize() {
        return size;
    }

    /**
     * 判断链表是否为空 如果链表为空返回 true，否则返回 false
     * @return
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }


    /**
     * 判断链表是否包含数据元素 e
     * @param e
     * @return
     */
    @Override
    public boolean contains(Object e) {
        if (Objects.isNull(this.head)) {
            return false;
        }
        SLNode node = this.head;
        while (Objects.nonNull(node)) {
            if (strategy.equal(node.getData(),e)) {
                return true;
            } else {
                node = node.getNext();
            }

        }
        return false;
    }

    /**
     * 返回数据元素 e 在链表中的序号
     * @param e
     * @return
     */
    @Override
    public int indexOf(Object e) {
        if (Objects.isNull(this.head)) {
            return -1;
        }
        SLNode node = this.head;
        int index = 0;
        while (Objects.nonNull(node)) {
            if (strategy.equal(node.getData(),e)) {
                return index;
            } else {
                node = node.getNext();
                index++;
            }

        }
        return -1;
    }

    /**
     * 获取当前结点的前置结点
     * @param e
     * @return
     */
    private SLNode getPreNode(Object e) {
        if (Objects.isNull(this.head)) {
            return null;
        }
        SLNode node = this.head;
        while (Objects.nonNull(node.getNext())) {
            if (strategy.equal(node.getNext().getData(),e)) {
                return node;
            } else {
                node = node.getNext();
            }

        }
        return null;
    }


    /**
     * 返回数据元素 e 在链表中的结点SLNode
     * @param e
     * @return
     */
    private SLNode getCurrNode(Object e) {
        if (Objects.isNull(this.head)) {
            return null;
        }
        SLNode node = this.head;
        while (Objects.nonNull(node)) {
            if (strategy.equal(node.getData(),e)) {
                return node;
            } else {
                node = node.getNext();
            }

        }
        return null;
    }


    /**
     * 获取第i个元素
     * @param i
     * @return
     */
    private SLNode getSLNode(int i) {
        if (Objects.isNull(this.head)) {
            return null;
        }
        SLNode node = this.head;
        int index = 0;
        while (Objects.nonNull(node)) {
            if (index == i) {
                return node;
            } else {
                node = node.getNext();
                index++;
            }

        }
        return null;
    }

    /**
     * 将数据元素 e 插入到链表中 i 号位置。若 i 越界，报错。
     * @param i
     * @param e
     * @throws OutOfBoundaryException
     */
    @Override
    public void insert(int i, Object e) throws OutOfBoundaryException {
        if (i < 0 || i > size ) {
            throw new OutOfBoundaryException("错误:指定的插入序号越界");
        }
        // 如果头结点插入
        if (i == 0) {
            SLNode node = new SLNode(e,this.head);
            this.head = node;
            size++;
            return;
        }
        //获取i-1位置的结点 前置结点
        SLNode preNode = getSLNode(i-1);
        if (Objects.nonNull(preNode)) {
            // 新建结点
            SLNode slNode = new SLNode(e,preNode.getNext());
            preNode.setNext(slNode);
            size++;
            return;
        }




    }

    /**
     * 将数据元素 e 插入到元素 obj 之前
     * @param obj
     * @param e
     * @return 成功返回 true，否则 返回 false
     */
    @Override
    public boolean insertBefore(Object obj, Object e) {
        if (Objects.isNull(this.head)) {
            return false;
        }

        // 当前结点e
        SLNode currNode = getCurrNode(e)  ;
        if (currNode == this.head) {
            SLNode node = new SLNode(e,this.head);
            this.head = node;
            size++;
            return true;
        }

        // 获取obj前置结点
        SLNode preNode = getPreNode(obj);

        if (Objects.nonNull(preNode)) {
            // 新建结点
            SLNode slNode = new SLNode(e,preNode.getNext());
            preNode.setNext(slNode);
            size++;
            return true;
        }
        return false;
    }

    /**
     * 将数据元素 e 插入到元素 obj 之后
     * @param obj
     * @param e
     * @return 成功返回 true，否则 返回 false
     */
    @Override
    public boolean insertAfter(Object obj, Object e) {
        if (Objects.isNull(this.head)) {
            return false;
        }
        // 获取obj所在结点
        SLNode currNode = getCurrNode(obj);
        if (Objects.nonNull(currNode)) {
            // 新建结点
            SLNode slNode = new SLNode(e,currNode.getNext());
            currNode.setNext(slNode);
            size++;
            return true;
        }
        return false;
    }

    /**
     * 删除链表中序号为 i 的元素,并返回 删除的元素。若 i 越界，报错。
     * @param i
     * @return
     * @throws OutOfBoundaryException
     */
    @Override
    public Object remove(int i) throws OutOfBoundaryException {
        if (i < 0 || i >= size ) {
            throw new OutOfBoundaryException("错误:指定的序号越界");
        }
        Object removeElement = null;

        // 如果头结点移除
        if (i == 0) {
            removeElement = this.head.getData();
            SLNode node = this.head.getNext();
            this.head = node;
            size--;
            return removeElement;
        }
        //获取i-1位置的结点 前置结点
        SLNode preNode = getSLNode(i-1);
        if (Objects.nonNull(preNode)) {
            // 当前结点e
            SLNode currNode = preNode.getNext();
            // 把前置结点的next 指向 当前结点的next
            preNode.setNext(currNode.getNext());
            // 清空当前结点
            removeElement = currNode.getData();
            currNode = null;
            size--;
        }


        return removeElement;
    }

    /**
     * 删除链表中第一个与 e 相同的元素
     * @param e
     * @return 成功返回 true，否则 返回 false
     */
    @Override
    public boolean remove(Object e) {

        if (Objects.isNull(this.head)) {
            return false;
        }
        // 当前结点e
        SLNode currNode = getCurrNode(e)  ;
        if (currNode == this.head) {
            SLNode node = this.head.getNext();
            this.head = node;
            size--;
            return true;
        }

        // 获取e前置结点
        SLNode preNode = getPreNode(e);
        if (Objects.nonNull(preNode)) {


            // 把前置结点的next 指向 当前结点的next
            preNode.setNext(currNode.getNext());
            // 清空当前结点
            currNode = null;

            size--;
            return true;
        }
        return false;
    }

    /**
     * 替换链表中序号为 i 的数据元素为 e，返回原数据元素。若 i 越界，报错。
     * @param i
     * @param e
     * @return
     * @throws OutOfBoundaryException
     */
    @Override
    public Object replace(int i, Object e) throws OutOfBoundaryException {
        if (i < 0 || i >= size ) {
            throw new OutOfBoundaryException("错误:指定的序号越界");
        }

        SLNode currNode = getSLNode(i) ;
        Object replaceElement = currNode.getData();
        currNode.setData(e);

        return replaceElement;
    }

    /**
     * 返回链表中序号为 i 的数据元素。若 i 越界，报错。
     * @param i
     * @return
     * @throws OutOfBoundaryException
     */
    @Override
    public Object get(int i) throws OutOfBoundaryException {
        if (i < 0 || i >= size ) {
            throw new OutOfBoundaryException("错误:指定的序号越界");
        }
        SLNode currNode = getSLNode(i) ;
        Object element = currNode.getData();
        return element;
    }


    /**
     * toString重写
     * @return
     */
    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append("ListSLinked{");


        SLNode node = this.head;
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
