package com.qinfengsa.structure.hash;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 全 O(1) 的数据结构
 * 实现一个数据结构支持以下操作：
 *
 * Inc(key) - 插入一个新的值为 1 的 key。或者使一个存在的 key 增加一，保证 key 不为空字符串。
 * Dec(key) - 如果这个 key 的值是 1，那么把他从数据结构中移除掉。否者使一个存在的 key 值减一。
 *      如果这个 key 不存在，这个函数不做任何事情。key 保证不为空字符串。
 * GetMaxKey() - 返回 key 中值最大的任意一个。如果没有元素存在，返回一个空字符串""。
 * GetMinKey() - 返回 key 中值最小的任意一个。如果没有元素存在，返回一个空字符串""。
 * 挑战：以 O(1) 的时间复杂度实现所有操作。
 * @author: qinfengsa
 * @date: 2019/11/18 09:02
 */
@Slf4j
public class AllOne {

    /**
     * 通过 map 记录 key-value
     * 通过双向链表记录 最大和最小值
     */
    private Map<String,OneNode> map;

    /**
     * 头结点，最小值
     */
    private OneNode head;
    /**
     * 尾结点，最大值
     */
    private OneNode tail;

    /**
     * 双向链表 结点，记录 value 的 大小顺序
     */
    static class OneNode {
        String key;
        Integer value;
        OneNode prev;
        OneNode next;
        OneNode(String key, Integer value, OneNode prev, OneNode next) {
            this.key = key;
            this.value = value;
            this.prev = prev;
            this.next = next;
        }
    }


    /** Initialize your data structure here. */
    public AllOne() {
        map = new HashMap<>();
        head = new OneNode("",0,null,null);
        tail = new OneNode("",Integer.MAX_VALUE,null,null);
        head.next = tail;
        tail.prev = head;
    }

    /**
     * 添加元素，头部 新增元素,由于新增的 value 都是 1，添加到head 的 next 结点
     */
    private OneNode addNode(String key ) {
        final OneNode h = head;
        final OneNode nextNode = head.next;
        Integer value = 1;
        final OneNode newNode = new OneNode(key,value,h, nextNode);
        h.next = newNode;
        nextNode.prev = newNode;
        return newNode;
    }

    /**
     * 移除Node
     * @param node
     * @return
     */
    private void removeNode(OneNode node) {
        final OneNode prevNode = node.prev;
        final OneNode nextNode = node.next;
        prevNode.next = nextNode;
        nextNode.prev = prevNode;
        // GC
        node.key = null;
        node.value = null;
        node.next = null;
        node.prev = null;

    }

    /**
     * value-- ,向前移
     */
    private void moveForward(OneNode node) {
        OneNode prevNode = node.prev;
        OneNode nextNode = node.next;
        if (prevNode.value <= node.value) {
            return;
        }

        while (prevNode.value > node.value) {
            // 向前移动
            prevNode.next = nextNode;
            nextNode.prev = prevNode;

            nextNode = prevNode;
            prevNode = prevNode.prev;
        }
        prevNode.next = node;
        nextNode.prev = node;
        node.prev = prevNode;
        node.next = nextNode;
    }

    /**
     * value++ ,向后移
     */
    private void moveBackward(OneNode node) {
        OneNode prevNode = node.prev;
        OneNode nextNode = node.next;
        if (nextNode.value >= node.value) {
            return;
        }
        while (nextNode.value < node.value) {
            // 向后移动
            prevNode.next = nextNode;
            nextNode.prev = prevNode;


            prevNode = nextNode;
            nextNode = nextNode.next;
        }
        prevNode.next = node;
        nextNode.prev = node;
        node.prev = prevNode;
        node.next = nextNode;
    }

    /**
     * Inc(key) - 插入一个新的值为 1 的 key。或者使一个存在的 key 增加一，保证 key 不为空字符串。
     * @param key
     */
    public void inc(String key) {
        OneNode node = map.get(key);
        if (node == null) {
            final OneNode newNode = addNode(key);
            map.put(key,newNode);
        } else {
            ++node.value;
            // 把node 向后移
            moveBackward(node);
        }
    }

    /**
     * Dec(key) - 如果这个 key 的值是 1，那么把他从数据结构中移除掉。否者使一个存在的 key 值减一。
     *   如果这个 key 不存在，这个函数不做任何事情。key 保证不为空字符串。
     * @param key
     */
    public void dec(String key) {
        OneNode node = map.get(key);
        if (node != null) {
            Integer value = node.value;
            if (Objects.equals(value,1)) {
                map.remove(key);
                removeNode(node);
            } else {
                --node.value;
                // 把node 向前移
                moveForward(node);
            }
        }
    }

    /**
     * GetMaxKey() - 返回 key 中值最大的任意一个。如果没有元素存在，返回一个空字符串""。
     * @return
     */
    public String getMaxKey() {
        if (map.isEmpty()) {
            return "";
        }
        return tail.prev.key;
    }

    /**
     * GetMaxKey() - 返回 key 中值最大的任意一个。如果没有元素存在，返回一个空字符串""。
     * @return
     */
    public String getMinKey() {
        if (map.isEmpty()) {
            return "";
        }
        return head.next.key;
    }


    public static void main(String[] args) {

        AllOne obj = new AllOne();
        obj.inc("a");
        obj.inc("b");
        obj.inc("b");
        obj.inc("c");
        obj.inc("c");
        obj.inc("c");
        obj.dec("b");
        obj.dec("b");
        log.debug("1:{}",obj.getMinKey());
        obj.dec("a");
        log.debug("2:{}",obj.getMaxKey());
        log.debug("3:{}",obj.getMinKey());
        /*obj.inc("1");
        obj.inc("1");
        obj.inc("1");
        obj.inc("2");
        obj.inc("2");
        obj.inc("2");
        obj.inc("2");
        obj.inc("3");
        obj.dec("1");
        String param_3 = obj.getMaxKey();
        log.debug("1:{}",param_3);
        String param_4 = obj.getMinKey();
        log.debug("2:{}",param_4);*/
    }
}
