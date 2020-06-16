package com.qinfengsa.structure.hash;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;

/**
 * 面试题 16.25. LRU缓存
 *
 * <p>设计和构建一个“最近最少使用”缓存，该缓存会删除最近最少使用的项目。缓存应该从键映射到值(允许你插入和检索特定键对应的值)，并在初始化时指定最大容量。当缓存被填满时，它应该删除最近最少使用的项目。
 *
 * <p>它应该支持以下操作： 获取数据 get 和 写入数据 put 。
 *
 * <p>获取数据 get(key) - 如果密钥 (key) 存在于缓存中，则获取密钥的值（总是正数），否则返回 -1。 写入数据 put(key, value) -
 * 如果密钥不存在，则写入其数据值。当缓存容量达到上限时，它应该在写入新数据之前删除最近最少使用的数据值，从而为新的数据值留出空间。
 *
 * <p>LRUCache cache = new LRUCache( 2 ); // 缓存容量
 *
 * <p>cache.put(1, 1);
 *
 * <p>cache.put(2, 2);
 *
 * <p>cache.get(1); // 返回 1 cache.put(3, 3); // 该操作会使得密钥 2 作废
 *
 * <p>cache.get(2); // 返回 -1 (未找到)
 *
 * <p>cache.put(4, 4); // 该操作会使得密钥 1 作废
 *
 * <p>cache.get(1); // 返回 -1 (未找到)
 *
 * <p>cache.get(3); // 返回 3 cache.get(4); // 返回 4
 *
 * @author qinfengsa
 * @date 2020/06/16 07:35
 */
@Slf4j
public class LRUCache2 {

    class LRUCacheNode {
        Integer key;
        Integer value;
        LRUCacheNode prev;
        LRUCacheNode next;

        public LRUCacheNode(Integer key, Integer value) {
            this.key = key;
            this.value = value;
        }
    }

    private Map<Integer, LRUCacheNode> cacheMap;

    private int capacity;

    private int size;

    private LRUCacheNode head;

    private LRUCacheNode tail;

    public LRUCache2(int capacity) {
        this.capacity = capacity;
        this.cacheMap = new HashMap<>();
        this.head = new LRUCacheNode(-1, -1);
        this.tail = new LRUCacheNode(-1, -1);
        this.head.next = this.tail;
        this.tail.prev = this.head;
    }

    public int get(int key) {
        LRUCacheNode node = getNode(key);
        return Objects.isNull(node) ? -1 : node.value;
    }

    private LRUCacheNode getNode(int key) {
        LRUCacheNode node = cacheMap.get(key);
        if (Objects.isNull(node)) {
            return node;
        }
        // 把 node 移动到最后
        removeNode(node);
        addTail(node);
        return node;
    }

    private void removeNode(LRUCacheNode node) {
        LRUCacheNode prev = node.prev;
        LRUCacheNode next = node.next;
        prev.next = next;
        next.prev = prev;
    }

    private void addTail(LRUCacheNode node) {
        LRUCacheNode prev = this.tail.prev;
        prev.next = node;
        node.prev = prev;
        node.next = this.tail;
        this.tail.prev = node;
    }

    public void put(int key, int value) {
        LRUCacheNode node = getNode(key);
        if (Objects.nonNull(node)) {
            node.value = value;
            return;
        }
        if (size == capacity) {
            LRUCacheNode first = this.head.next;
            removeNode(first);
            cacheMap.remove(first.key);
        } else {
            size++;
        }
        node = new LRUCacheNode(key, value);
        cacheMap.put(key, node);
        addTail(node);
    }

    public static void main(String[] args) {
        LRUCache2 cache = new LRUCache2(2);

        cache.put(1, 1);
        cache.put(2, 2);
        log.debug("1:{}", cache.get(1)); // 返回  1
        cache.put(3, 3); // 该操作会使得密钥 2 作废
        log.debug("1:{}", cache.get(2)); // 返回 -1 (未找到)
        cache.put(4, 4); // 该操作会使得密钥 1 作废
        log.debug("2:{}", cache.get(1)); // 返回 -1 (未找到)
        log.debug("3:{}", cache.get(3)); // 返回  3
        log.debug("4:{}", cache.get(4)); // 返回  4

        /*LRUCache cache = new LRUCache( 1  );

                cache.put(2, 1);
                log.debug("1:{}",cache.get(2));       // 返回  1
                cache.put(3, 2);
                log.debug("2:{}",cache.get(2));       // 返回  1
                log.debug("3:{}",cache.get(3));       // 返回  3

        */

    }
}
