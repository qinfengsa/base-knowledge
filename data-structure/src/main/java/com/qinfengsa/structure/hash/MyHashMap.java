package com.qinfengsa.structure.hash;

import java.util.Objects;

/**
 * 设计哈希映射
 * 不使用任何内建的哈希表库设计一个哈希映射
 *
 * 具体地说，你的设计应该包含以下的功能
 *
 * put(key, value)：向哈希映射中插入(键,值)的数值对。如果键对应的值已经存在，更新这个值。
 * get(key)：返回给定的键所对应的值，如果映射中不包含这个键，返回-1。
 * remove(key)：如果映射中存在这个键，删除这个数值对。
 *
 * 示例：
 *
 * MyHashMap hashMap = new MyHashMap();
 * hashMap.put(1, 1);
 * hashMap.put(2, 2);
 * hashMap.get(1);            // 返回 1
 * hashMap.get(3);            // 返回 -1 (未找到)
 * hashMap.put(2, 1);         // 更新已有的值
 * hashMap.get(2);            // 返回 1
 * hashMap.remove(2);         // 删除键为2的数据
 * hashMap.get(2);            // 返回 -1 (未找到)
 *
 * 注意：
 *
 * 所有的值都在 [1, 1000000]的范围内。
 * 操作的总数目在[1, 10000]范围内。
 * 不要使用内建的哈希库。
 * @author: qinfengsa
 * @date: 2019/5/16 12:12
 */
public class MyHashMap {

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
    public MyHashMap() {
        element = new HashNode[DEFAULT_SIZE];
    }


    /** value will always be non-negative. */
    public void put(int key, int value) {
        int hash = hash(key);
        HashNode myNode = element[hash];
        if (Objects.isNull(myNode)) {
            HashNode node = new HashNode(key,value);
            element[hash] = node;
        } else {
            HashNode lastNode = null;
            while (Objects.nonNull(myNode)) {
                if (myNode.key == key) {
                    break;
                }
                lastNode = myNode;
                myNode = myNode.next;
            }
            if (Objects.nonNull(myNode)) {
                myNode.value = value;
            } else {
                HashNode node = new HashNode(key,value);
                lastNode.next = node;
            }
        }
    }

    /** Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key */
    public int get(int key) {
        int hash = hash(key);
        HashNode myNode = element[hash];
        while (Objects.nonNull(myNode)) {
            if (myNode.key == key) {
                return myNode.value;
            }
            myNode = myNode.next;
        }
        return -1;
    }

    /** Removes the mapping of the specified value key if this map contains a mapping for the key */
    public void remove(int key) {
        int hash = hash(key);
        HashNode head = element[hash];
        HashNode node = head;
        HashNode preNode = null;
        if (Objects.nonNull(node)) {
            if (node.key == key) {
                if (Objects.isNull(preNode)) {
                    element[hash] = node.next;
                } else {
                    preNode.next = node.next;
                }


                return;
            }
            preNode = node;
            node = node.next;
        }
    }


    class HashNode {
        int key;

        int value;

        HashNode next;

        HashNode(int key,int value) {
            this.key = key;
            this.value = value;
        }


    }
}
