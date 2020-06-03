package com.qinfengsa.structure.hash;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Objects;

/**
 * 运用你所掌握的数据结构，设计和实现一个  LRU (最近最少使用) 缓存机制。它应该支持以下操作： 获取数据 get 和 写入数据 put 。
 *
 * 获取数据 get(key) - 如果密钥 (key) 存在于缓存中，则获取密钥的值（总是正数），否则返回 -1。
 * 写入数据 put(key, value) - 如果密钥不存在，则写入其数据值。当缓存容量达到上限时，它应该在写入新数据之前删除最近最少使用的数据值，从而为新的数据值留出空间。
 *
 * 进阶:
 * 你是否可以在 O(1) 时间复杂度内完成这两种操作？
 *
 * 示例:
 * LRUCache cache = new LRUCache( 2   );// 缓存容量
 * cache.put(1,1);
 * cache.put(2,2);
 * cache.get(1);       // 返回  1
 * cache.put(3,3);    // 该操作会使得密钥 2 作废
 * cache.get(2);       // 返回 -1 (未找到)
 * cache.put(4,4);    // 该操作会使得密钥 1 作废
 * cache.get(1);       // 返回 -1 (未找到)
 * cache.get(3);       // 返回  3
 * cache.get(4);       // 返回  4
 * @author: qinfengsa
 * @date: 2019/10/27 12:33
 */
@Slf4j
public class LRUCacheTest {
    // 思路，使用HashMap作为存储,使用双向链表维护 key的顺序,让最近最少使用的key排在 队首，最近使用的排在队尾

    private Map<Integer,Integer> map;

    static class LRULinkedHashMap<K,V> extends LinkedHashMap {

        private Integer capacity;

        LRULinkedHashMap(int initialCapacity ) {
            super(initialCapacity,0.75f,true);
            this.capacity = initialCapacity;
        }
        @Override
        protected boolean removeEldestEntry(Map.Entry eldest) {
            return size() > capacity;
        }

    }


    LRUCacheTest(int capacity) {
        map = new LRULinkedHashMap<>(capacity);
    }

    /**
     * 从Map中获取key,然后把当前key的
     * @param key
     * @return
     */
    int get(int key) {
        return map.getOrDefault(key, -1);
    }

    void put(int key, int value) {
        map.put(key,value);
    }


    /*public static void main(String[] args) {
        LRUCache cache = new LRUCache( 2 *//* 缓存容量 *//* );

        cache.put(1, 1);
        cache.put(2, 2);
        log.debug("1:{}",cache.get(1));       // 返回  1
        cache.put(3, 3);    // 该操作会使得密钥 2 作废
        log.debug("1:{}",cache.get(2));       // 返回 -1 (未找到)
        cache.put(4, 4);    // 该操作会使得密钥 1 作废
        log.debug("1:{}",cache.get(1));       // 返回 -1 (未找到)
        log.debug("1:{}",cache.get(3));       // 返回  3
        log.debug("1:{}",cache.get(4));       // 返回  4

    }
*/

}
