package com.qinfengsa.structure.linearlist.linkedlist;

import com.qinfengsa.base.Node;
import com.qinfengsa.exception.InvalidNodeException;
import com.qinfengsa.exception.OutOfBoundaryException;

import java.util.Iterator;

/**
 * 链表接口
 * @author: qinfengsa
 * @date: 2019/5/4 11:53
 */
public interface LinkedList {


    /**
     * 查询链接表当前大小
     * @return
     */
    int getSize();

    /**
     * 判断链表是否为空
     * @return
     */
    boolean isEmpty();

    /**
     * 返回链表第一个结点
     * @return
     * @throws OutOfBoundaryException
     */
    Node first() throws OutOfBoundaryException;

    /**
     * 返回链表最后的结点
     * @return
     * @throws OutOfBoundaryException
     */
    Node last() throws OutOfBoundaryException;


    /**
     * 返回p结点之后的next结点
     * @param p
     * @return
     * @throws InvalidNodeException
     * @throws OutOfBoundaryException
     */
    Node getNext(Node p) throws InvalidNodeException, OutOfBoundaryException;


    /**
     * 返回 p结点 前置结点
     * @param p
     * @return
     * @throws InvalidNodeException
     * @throws OutOfBoundaryException
     */
    Node getPre(Node p) throws InvalidNodeException, OutOfBoundaryException;


    /**
     * 将 e 作为第一个元素插入链接表,并返回 e 所在结点
     * @param e
     * @return
     */
    Node insertFirst(Object e);

    /**
     * 将 e 作为后一个元素插入列表,并返回 e 所在结点
     * @param e
     * @return
     */
    Node insertLast(Object e);

    /**
     * 将 e 插入至 p 之后的位置,并返回 e 所在结点
     * @param p
     * @param e
     * @return
     * @throws InvalidNodeException
     */
    Node insertAfter(Node p, Object e) throws InvalidNodeException;

    /**
     * 将 e 插入至 p 之前的位置,并返回 e 所在结点
     * @param p
     * @param e
     * @return
     * @throws InvalidNodeException
     */
    Node insertBefore(Node p, Object e) throws InvalidNodeException;


    /**
     * 删除给定位置处的元素，并返回之
     * @param p
     * @return
     * @throws InvalidNodeException
     */
    Object remove(Node p) throws InvalidNodeException;

    /**
     * 删除首元素，并返回之
     * @return
     * @throws OutOfBoundaryException
     */
    Object removeFirst() throws OutOfBoundaryException;

    /**
     * 删除末元素，并返回之
     * @return
     * @throws OutOfBoundaryException
     */
    Object removeLast() throws OutOfBoundaryException;

    /**
     * 将处于给定位置的元素替换为新元素，并返回被替换的元素
     * @param p
     * @param e
     * @return
     * @throws InvalidNodeException
     */
    Object replace(Node p, Object e) throws InvalidNodeException;


    /**
     * 元素迭代器
     * @return
     */
    Iterator elements();
}
