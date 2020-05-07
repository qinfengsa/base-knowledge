package com.qinfengsa.structure.linearlist.linkedlist.dllist;

import com.qinfengsa.exception.InvalidNodeException;
import com.qinfengsa.exception.OutOfBoundaryException;
import com.qinfengsa.structure.linearlist.DefaultStrategy;
import com.qinfengsa.structure.linearlist.Strategy;
import com.qinfengsa.structure.linearlist.linkedlist.LinkedList;
import com.qinfengsa.base.Node;

import java.util.Iterator;
import java.util.Objects;

/**
 * @author: qinfengsa
 * @date: 2019/5/3 21:57
 */
public class ListDLinked implements LinkedList {

    /**
     * 线性表大小
     */
    private int size;

    /**
     * 比较策略
     */
    private Strategy strategy;

    /**
     * 头结点
     */
    private DLNode head;

    /**
     * 尾结点
     */
    private DLNode tail;

    /**
     * 构造方法
     */
    public ListDLinked() {
        size = 0;
        strategy = new DefaultStrategy();
        head = new DLNode();
        tail = new DLNode();
        head.setNext(tail);
        tail.setPre(head);
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
     * 返回链表第一个结点
     * @return
     * @throws OutOfBoundaryException
     */
    @Override
    public Node first() throws OutOfBoundaryException {
        if (isEmpty()) {
            throw new OutOfBoundaryException("错误：链表为空");
        }
        return head.getNext();
    }

    /**
     * 返回链表最后的结点
     * @return
     * @throws OutOfBoundaryException
     */
    @Override
    public Node last() throws OutOfBoundaryException {
        if (isEmpty()) {
            throw new OutOfBoundaryException("错误：链表为空");
        }
        return tail.getPre();
    }

    /**
     * 校验node合法
     * @param p
     * @return
     */
    private DLNode checkPosition(Node p) throws InvalidNodeException {
        if (Objects.isNull(p)) {
            throw new InvalidNodeException("错误：p 为空");
        }
        if (p == head) {
            throw new InvalidNodeException("错误：p指向头结点");
        }
        if (p == tail) {
            throw new InvalidNodeException("错误：p指向尾结点");
        }

        DLNode node = this.head;
        while (Objects.nonNull(node)) {
            if (node == p) {
                return node;
            } else {
                node = node.getNext();
            }
        }
        return null;

    }

    /**
     * 返回p结点之后的next结点
     * @param p
     * @return
     * @throws InvalidNodeException
     * @throws OutOfBoundaryException
     */
    @Override
    public Node getNext(Node p) throws InvalidNodeException, OutOfBoundaryException {
        DLNode node = checkPosition(p);
        if (Objects.isNull(node)) {
            throw new InvalidNodeException("错误：结点p在链表中不存在");
        }
        return node.getNext();
    }

    /**
     * 返回 p结点 前置结点
     * @param p
     * @return
     * @throws InvalidNodeException
     * @throws OutOfBoundaryException
     */
    @Override
    public Node getPre(Node p) throws InvalidNodeException, OutOfBoundaryException {
        DLNode node = checkPosition(p);
        if (Objects.isNull(node)) {
            throw new InvalidNodeException("错误：结点p在链表中不存在");
        }
        return node.getPre();
    }

    /**
     * 将 e 作为第一个元素插入链接表,并返回 e 所在结点
     * @param e
     * @return
     */
    @Override
    public Node insertFirst(Object e) {

        //Object element, DLNode pre,
        DLNode next = head.getNext();
        DLNode dlNode = new DLNode(e,head,head.getNext());

        this.head.setNext(dlNode);
        next.setPre(dlNode);
        size++;
        return dlNode;
    }

    /**
     * 将 e 作为后一个元素插入列表,并返回 e 所在结点
     * @param e
     * @return
     */
    @Override
    public Node insertLast(Object e) {
        DLNode pre = tail.getPre();
        DLNode dlNode = new DLNode(e,tail.getPre(),tail);

        this.tail.setPre(dlNode);
        pre.setNext(dlNode);
        size++;
        return dlNode;
    }

    /**
     * 将 e 插入至 p 之后的位置,并返回 e 所在结点
     * @param p
     * @param e
     * @return
     * @throws InvalidNodeException
     */
    @Override
    public Node insertAfter(Node p, Object e) throws InvalidNodeException {
        DLNode node = checkPosition(p);
        if (Objects.isNull(node)) {
            throw new InvalidNodeException("错误：结点p在链表中不存在");
        }
        // 后置结点
        DLNode nextNode = node.getNext();
        // 新建结点
        DLNode dlNode = new DLNode(e,node,node.getNext());
        node.setNext(dlNode);

        // 后置结点
        nextNode.setPre(dlNode);

        size++;
        return dlNode;
    }

    /**
     * 将 e 插入至 p 之前的位置,并返回 e 所在结点
     * @param p
     * @param e
     * @return
     * @throws InvalidNodeException
     */
    @Override
    public Node insertBefore(Node p, Object e) throws InvalidNodeException {
        DLNode node = checkPosition(p);
        if (Objects.isNull(node)) {
            throw new InvalidNodeException("错误：结点p在链表中不存在");
        }
        // 前置结点
        DLNode preNode = node.getPre();
        // 新建结点
        DLNode dlNode = new DLNode(e,node.getPre(),node);
        node.setPre(dlNode);
        //DLNode preNode =

        // 前置结点
        preNode.setNext(dlNode);

        size++;
        return dlNode;
    }
    /**
     * 删除给定位置处的元素，并返回之
     * @param p
     * @return
     * @throws InvalidNodeException
     */
    @Override
    public Object remove(Node p) throws InvalidNodeException {
        DLNode node = checkPosition(p);
        if (Objects.isNull(node)) {
            throw new InvalidNodeException("错误：结点p在链表中不存在");
        }
        DLNode preNode = node.getPre();

        DLNode nextNode = node.getNext();

        preNode.setNext(nextNode);
        nextNode.setPre(preNode);

        Object element = node.getData();
        // 清空结点
        node = null;
        size--;
        return element;
    }

    /**
     * 删除首元素，并返回之
     * @return
     * @throws OutOfBoundaryException
     */
    @Override
    public Object removeFirst() throws OutOfBoundaryException {
        if (isEmpty()) {
            throw new OutOfBoundaryException("错误：链表为空");
        }
        DLNode node = this.head.getNext();
        Object element = node.getData();
        DLNode nextNode = node.getNext();
        this.head.setNext(nextNode);

        nextNode.setPre(this.head);
        node = null;
        size--;
        return element;
    }

    /**
     * 删除末元素，并返回之
     * @return
     * @throws OutOfBoundaryException
     */
    @Override
    public Object removeLast() throws OutOfBoundaryException {
        if (isEmpty()) {
            throw new OutOfBoundaryException("错误：链表为空");
        }
        DLNode node = this.tail.getPre();
        Object element = node.getData();
        DLNode preNode = node.getPre();
        this.tail.setPre(preNode);

        preNode.setNext(this.tail);
        node = null;
        size--;
        return element;
    }

    /**
     * 将处于给定位置的元素替换为新元素，并返回被替换的元素
     * @param p
     * @param e
     * @return
     * @throws InvalidNodeException
     */
    @Override
    public Object replace(Node p, Object e) throws InvalidNodeException {
        DLNode node = checkPosition(p);
        if (Objects.isNull(node)) {
            throw new InvalidNodeException("错误：结点p在链表中不存在");
        }
        Object element = node.getData();
        node.setData(e);
        return element;
    }

    /**
     * 元素迭代器
     * @return
     */
    @Override
    public Iterator elements() {
        return null;
    }

    /**
     * toString重写
     * @return
     */
    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append("ListSLinked{");


        DLNode node = this.head.getNext();
        int index = 0;
        while (Objects.nonNull(node)) {
            if (node == tail) {
                break;
            }
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
