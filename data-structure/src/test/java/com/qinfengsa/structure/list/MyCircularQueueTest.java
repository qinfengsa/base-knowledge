package com.qinfengsa.structure.list;

import com.qinfengsa.structure.leetcode.MyCircularQueue;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

/**
 * @author: qinfengsa
 * @date: 2019/5/6 16:20
 */
@Slf4j
public class MyCircularQueueTest {

    @Test
    public void test1() {
        MyCircularQueue circularQueue = new MyCircularQueue(3); // 设置长度为 3

        log.debug("1:{}", circularQueue.enQueue(1)); // 返回 true

        log.debug("2:{}", circularQueue.enQueue(2)); // 返回 true

        log.debug("3:{}", circularQueue.enQueue(3)); // 返回 true

        log.debug("4:{}", circularQueue.enQueue(4)); // 返回 false，队列已满

        log.debug("5:{}", circularQueue.Rear()); // 返回 3

        log.debug("6:{}", circularQueue.isFull()); // 返回 true

        log.debug("7:{}", circularQueue.deQueue()); // 返回 true

        log.debug("10:{}", circularQueue.deQueue());
        log.debug("11:{}", circularQueue.deQueue());
        log.debug("12:{}", circularQueue.deQueue());

        log.debug("8:{}", circularQueue.enQueue(4)); // 返回 true

        log.debug("9:{}", circularQueue.Rear()); // 返回 4
        log.debug("10:{}", circularQueue.deQueue());
        log.debug("11:{}", circularQueue.deQueue());
        log.debug("12:{}", circularQueue.deQueue());
    }
}
