package com.qinfengsa.structure.hash;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Objects;

/**
 * @author: qinfengsa
 * @date: 2019/10/27 16:32
 */
@Slf4j
public class LruCacheMap {

    // 思路，使用HashMap作为存储,使用双向链表队列维护 key的顺序,让最近最少使用的key排在 队首，最近使用的排在队尾
    private Map<String,CacheNode> map;

    private int capacity;

    private CacheNode head;
    private CacheNode tail;


    /**
     * 队列，记录key和value,队列维护 key的顺序
     */
    static class CacheNode {
        String key;
        Object value;
        CacheNode prev;
        CacheNode next;
        CacheNode(String key, Object value, CacheNode prev, CacheNode next) {
            this.key = key;
            this.value = value;
            this.prev = prev;
            this.next = next;
        }
    }


    /**
     * 入队，队尾新增元素
     */
    private CacheNode addNode(String key,Object value) {
        final CacheNode l = tail;
        final CacheNode newNode = new CacheNode(key,value,l, null);
        tail = newNode;
        if (l == null) {
            head = newNode;
        } else {
            l.next = newNode;
        }
        return newNode;
    }

    /**
     * 出队，队首元素移除
     */
    private CacheNode removeNode() {
        CacheNode node = this.head;
        final CacheNode next = node.next;
        node.key = null;
        node.value = null;
        node.next = null;
        head = next;
        if (next == null) {
            tail = null;
        } else {
            next.prev = null;
        }
        return node;
    }

    /**
     * 把 node 移动到队尾
     * @param node
     */
    private void moveToTail(CacheNode node) {
        final CacheNode prev = node.prev;
        final CacheNode next = node.next;
        if (prev == null) {
            this.head = next;
        } else {
            prev.next = next;
        }
        if (next == null) {
            this.tail = prev;
        } else {
            next.prev = prev;
            node.next = null;
        }
        // 把node 添加到队尾
        if (tail != null) {
            tail.next = node;
            node.prev = tail;
            node.next = null;
        }
        // 队尾指针指向 node
        tail = node;
        if (head == null) {
            head = tail;
        }

    }

    public LruCacheMap(int capacity) {
        this.map = new HashMap<>(Math.max((int) (capacity/.75f) + 1, 16));
        this.capacity = capacity;
    }

    /**
     * get 操作，把 当前key移动到队尾
     * @param key
     * @return
     */
    Object get(String key) {
        CacheNode node = map.get(key);
        if (node == null) {
            return null;
        }
        // 把元素移动到队尾
        moveToTail(node);
        return node.value;
    }

    /**
     * put 操作, 如果元素已满, 把队列的头结点移除
     * @param key
     * @return
     */
    void put(String key, Object value) {
        CacheNode node = map.get(key);
        if (node == null) {
            final CacheNode newNode = addNode(key,value);
            map.put(key,newNode);
            if (map.size() > capacity) {
                map.remove(this.head.key);
                // 移除队首元素
                removeNode();
            }
        } else {
            node.value = value;
            // 把node 重新移动到队尾
            moveToTail(node);
        }
    }

    public static void main(String[] args) {
        LruCacheMap cache = new LruCacheMap( 2 );

        cache.put("1", 1);
        cache.put("2", 2);
        log.debug("1:{}",cache.get("1"));       // 返回  1
        cache.put("3", 3);    // 该操作会使得密钥 2 作废
        log.debug("1:{}",cache.get("2"));       // 返回 -1 (未找到)
        cache.put("4", 4);    // 该操作会使得密钥 1 作废
        log.debug("2:{}",cache.get("1"));       // 返回 -1 (未找到)
        log.debug("3:{}",cache.get("3"));       // 返回  3
        log.debug("4:{}",cache.get("4"));       // 返回  4

    }

}
