package com.qinfengsa.structure.hash;

import java.util.Objects;

/**
 * 设计哈希集合
 * 不使用任何内建的哈希表库设计一个哈希集合
 *
 * 具体地说，你的设计应该包含以下的功能
 *
 * add(value)：向哈希集合中插入一个值。
 * contains(value) ：返回哈希集合中是否存在这个值。
 * remove(value)：将给定值从哈希集合中删除。如果哈希集合中没有这个值，什么也不做。
 *
 * 示例:
 *
 * MyHashSet hashSet = new MyHashSet();
 * hashSet.add(1);
 * hashSet.add(2);
 * hashSet.contains(1);    // 返回 true
 * hashSet.contains(3);    // 返回 false (未找到)
 * hashSet.add(2);
 * hashSet.contains(2);    // 返回 true
 * hashSet.remove(2);
 * hashSet.contains(2);    // 返回  false (已经被删除)
 *
 * 注意：
 *
 * 所有的值都在 [1, 1000000]的范围内。
 * 操作的总数目在[1, 10000]范围内。
 * 不要使用内建的哈希集合库。
 * @author: qinfengsa
 * @date: 2019/5/16 11:11
 */
public class MyHashSet {

    /**
     * 默认
     */
    private static final int DEFAULT_SIZE = 100003;

    /**
     * 元素集合
     */
    private HashNode[] element;


    static final int hash(int key) {
        return key % DEFAULT_SIZE;
    }


    /** Initialize your data structure here. */
    public MyHashSet() {
        element = new HashNode[DEFAULT_SIZE];
    }

    public void add(int key) {
        int hash = hash(key);
        HashNode myNode = element[hash];
        if (Objects.isNull(myNode)) {
            HashNode node = new HashNode(key);
            element[hash] = node;
        } else if (key != myNode.val) {
            while (Objects.nonNull(myNode.next)) {
                myNode = myNode.next;
            }
            HashNode node = new HashNode(key);
            myNode.next = node;
        }


    }

    public void remove(int key) {
        int hash = hash(key);
        HashNode head = element[hash];
        HashNode node = head;
        HashNode preNode = null;
        if (Objects.nonNull(node)) {
            if (node.val == key) {
                if (Objects.isNull(preNode)) {
                    element[hash] = node.next;
                } else {
                    preNode.next = node.next;
                }


                return;
            }
            node = node.next;
        }
    }

    /** Returns true if this set contains the specified element */
    public boolean contains(int key) {
        int hash = hash(key);
        HashNode myNode = element[hash];
        while (Objects.nonNull(myNode)) {
            if (myNode.val == key) {
                return true;
            }
            myNode = myNode.next;
        }
        return false;
    }


    static class HashNode {
        int val;

        HashNode next;

        HashNode(int val) {
            this.val = val;
        }


    }
}
