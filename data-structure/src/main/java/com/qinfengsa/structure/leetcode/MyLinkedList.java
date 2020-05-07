package com.qinfengsa.structure.leetcode;


import java.util.Objects;

/**
 * @author: qinfengsa
 * @date: 2019/5/4 18:04
 */
public class MyLinkedList {

    /**
     * 头结点
     */
    private MyNode head;

    /**
     * 链表大小
     */
    private int size;

    public int getSize() {
        return size;
    }

    /**
     * 构造方法
     */
    public MyLinkedList() {
        this.head = null;
        this.size = 0;
    }

    /**
     * 获取链表中第 index 个节点的值。如果索引无效，则返回-1。
     * @param index
     * @return
     */
    public int get(int index) throws Exception {
        if (index < 0 || index >= size ) {
            throw new Exception("错误:指定的序号越界");
        }
        MyNode node = this.head;
        int i = 0;
        while (Objects.nonNull(node)) {
            if (index == i) {
                return node.getVal();
            } else {
                node = node.getNext();
                i++;
            }

        }
        return -1;
    }

    /**
     * 在链表的第一个元素之前添加一个值为 val 的节点。插入后，新节点将成为链表的第一个节点。
     * @param val
     */
    public void addAtHead(int val) {
        if (Objects.isNull(this.head)) {
            // 新建Node
            MyNode newNode = new MyNode(val);
            this.head = newNode;
            size++;
            return;
        }
        // 新建Node
        MyNode newNode = new MyNode(val);
        // newNode 尾结点指向head
        newNode.setNext(head);

        // head 指向 newNode
        this.head = newNode;
        size++;
    }

    /**
     * 将值为 val 的节点追加到链表的最后一个元素
     * @param val
     */
    public void addAtTail(int val) {
        if (Objects.isNull(this.head)) {
            this.head = new MyNode(val);
            size++;
            return;
        }
        MyNode node = this.head;
        while (Objects.nonNull(node.getNext())) {
            // 获取到尾结点
            node = node.getNext();

        }

        // 新建结点，尾结点的next指向newNode
        MyNode newNode = new MyNode(val);
        node.setNext(newNode);
        size++;
    }


    /**
     * 在链表中的第 index 个节点之前添加值为 val  的节点。
     * 如果 index 等于链表的长度，则该节点将附加到链表的末尾。如果 index 大于链表长度，则不会插入节点。
     * @param index
     * @param val
     */
    public void addAtIndex(int index, int val) throws Exception {
        if (index < 0 || index > size ) {
            throw new Exception("错误:指定的序号越界");
        }

        // 如果头结点插入
        if (index == 0) {
            addAtHead(val);
            return;
        }
        // 获取i-1位置的结点 前置结点
        MyNode preNode = getNode(index-1);
        if (Objects.nonNull(preNode)) {
            // 新建结点
            MyNode newNode = new MyNode(val);
            // 把 newNode 的next 指向 index结点
            newNode.setNext(preNode.getNext());
            // 把 preNode 的next 指向 newNode结点
            preNode.setNext(newNode);
            size++;
            return;
        }

    }

    /**
     * 获取第i个结点
     * @param i
     * @return
     */
    private MyNode getNode(int i) {
        if (Objects.isNull(this.head)) {
            return null;
        }
        MyNode node = this.head;
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
     * 如果索引 index 有效，则删除链表中的第 index 个节点。
     * @param index
     */
    public void deleteAtIndex(int index) throws Exception {
        if (index < 0 || index >= size ) {
            throw new Exception("错误:指定的序号越界");
        }

        Object removeElement = null;

        // 如果头结点移除
        if (index == 0) {
            MyNode node = this.head.getNext();
            this.head = node;
            size--;
            return;
        }
        // 获取index-1位置的结点 前置结点
        MyNode preNode = getNode(index-1);
        if (Objects.nonNull(preNode)) {
            // 当前结点e
            MyNode currNode = preNode.getNext();
            // 把前置结点的next 指向 当前结点的next
            preNode.setNext(currNode.getNext());
            // 清空当前结点
            currNode = null;
            size--;
        }
    }


    /**
     * toString重写
     * @return
     */
    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append("ListSLinked{");


        MyNode node = this.head;
        int index = 0;
        while (Objects.nonNull(node)) {
            if (index > 0) {
                sb.append(",");
            }
            sb.append(node.getVal());
            node = node.getNext();
            index++;

        }
        sb.append("}");
        sb.append("size=");
        sb.append(size);
        return sb.toString();
    }
}

class MyNode {



    /**
     * 当前节点的值
     */
    private int val;

    /**
     * 是指向下一个节点的指针/引用
     */
    private MyNode next;

    public MyNode(int val) {
        this.val = val;
        this.next = null;
    }

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }

    public MyNode getNext() {
        return next;
    }

    public void setNext(MyNode next) {
        this.next = next;
    }

}
