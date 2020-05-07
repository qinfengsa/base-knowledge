package com.qinfengsa.structure.hash;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * 460. LFU缓存
 * 设计并实现最不经常使用（LFU）缓存的数据结构。它应该支持以下操作：get 和 put。
 *
 * get(key) - 如果键存在于缓存中，则获取键的值（总是正数），否则返回 -1。
 * put(key, value) - 如果键不存在，请设置或插入值。当缓存达到其容量时，它应该在插入新项目之前，使最不经常使用的项目无效。
 * 在此问题中，当存在平局（即两个或更多个键具有相同使用频率）时，最近最少使用的键将被去除。
 *
 * 进阶：
 * 你是否可以在 O(1) 时间复杂度内执行两项操作？
 *
 * 示例：
 *
 * LFUCache cache = new LFUCache( 2  capacity (缓存容量)  )
 * cache.put(1,1);
        **cache.put(2,2);
        **cache.get(1);       // 返回 1
        **cache.put(3,3);    // 去除 key 2
        **cache.get(2);       // 返回 -1 (未找到key 2)
        **cache.get(3);       // 返回 3
        **cache.put(4,4);    // 去除 key 1
        **cache.get(1);       // 返回 -1 (未找到 key 1)
        **cache.get(3);       // 返回 3
        **cache.get(4);       // 返回 4
 * @author: qinfengsa
 * @date: 2020/4/5 07:06
 */
@Slf4j
public class LFUCache {
    private int capacity;

    private int size;

    private PriorityQueue<CacheNode> minHeap;


    private Map<Integer,CacheNode> cache = new HashMap<>();

    private class CacheNode implements Comparable<CacheNode>{
        Integer key;
        Integer value;
        int rate = 0;
        long time;
        public CacheNode(Integer key, Integer value ) {
            this.key = key;
            this.value = value;
            this.time = System.nanoTime();
        }

        void addRate() {
            rate++;
        }
        public int compareTo(CacheNode node) {
            int diff = rate - node.rate;
            if (diff != 0) {
                return diff;
            }
            if (time > node.time) {
                return 1;
            }
            return -1;
        }

    }

    public LFUCache(int capacity) {
        this.capacity = capacity;
        if (capacity > 0) {
            minHeap = new PriorityQueue<>(capacity);
        }

    }

    public int get(int key) {
        CacheNode node = cache.get(key);
        if (node == null) {
            return -1;
        }
        node.addRate();
        node.time = System.nanoTime();
        minHeap.remove(node);
        minHeap.offer(node);
        return node.value;
    }

    public void put(int key, int value) {
        if (capacity == 0) {
            return;
        }
        CacheNode node = cache.get(key);
        if (node != null) {
            node.addRate();
            node.value = value;
            node.addRate();
            node.time = System.nanoTime();
            minHeap.remove(node);
            minHeap.offer(node);
        } else {
            if (size == capacity) {
                cache.remove(minHeap.peek().key);
                minHeap.poll();
                size--;
            }
            CacheNode newNode = new CacheNode(key, value );
            cache.put(key, newNode);
            minHeap.offer(newNode);
            size++;
        }

    }

    void remove() {

    }

    public static void main(String[] args) {
        LFUCache cache = new LFUCache( 2  );

        cache.put(1, 1);
        cache.put(2, 2);
        log.debug("1:{}",cache.get(1));        // 返回 1
        cache.put(3, 3);    // 去除 key 2
        log.debug("1:{}",cache.get(2));       // 返回 -1 (未找到key 2)
        log.debug("1:{}",cache.get(3));       // 返回 3
        cache.put(4, 4);    // 去除 key 1
        log.debug("1:{}",cache.get(1));       // 返回 -1 (未找到 key 1)
        log.debug("1:{}",cache.get(3));       // 返回 3
        log.debug("1:{}",cache.get(4));       // 返回 4
    }
}
