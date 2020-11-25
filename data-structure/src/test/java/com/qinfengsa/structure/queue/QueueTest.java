package com.qinfengsa.structure.queue;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

/**
 * 队列测试
 *
 * @author: qinfengsa
 * @date: 2019/5/6 10:09
 */
@Slf4j
public class QueueTest {

    @Test
    public void test1() {
        Queue queue = new QueueArray();
        queue.enqueue("A");
        queue.enqueue("B");
        log.debug("1:{}", queue.toString());
        queue.enqueue("C");
        queue.enqueue("D");
        queue.enqueue("E");
        queue.enqueue("F");
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        log.debug("2:{}", queue.toString());
        // queue.enqueue("G");
        // queue.enqueue("H");
        // queue.enqueue("K");
        log.debug("3:{}", queue.toString());
        log.debug("a:{}", queue.peek());
        log.debug("b:{}", queue.dequeue());
        log.debug("4:{}", queue.toString());
        queue.dequeue();
        queue.dequeue();
        log.debug("5:{}", queue.toString());
        queue.enqueue("L");
        log.debug("6:{}", queue.toString());

        // queue.dequeue();
        // log.debug("5:{}",queue.toString());

    }

    @Test
    public void test2() {
        Queue queue = new QueueSLinked();
        queue.enqueue("A");
        queue.enqueue("B");
        log.debug("1:{}", queue.toString());
        queue.enqueue("C");
        // queue.enqueue("D");
        // queue.enqueue("E");
        // queue.enqueue("F");
        log.debug("1.5:{}", queue.toString());
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        log.debug("2:{}", queue.toString());
        queue.enqueue("G");
        queue.enqueue("H");
        queue.enqueue("K");
        log.debug("3:{}", queue.toString());
        log.debug("a:{}", queue.peek());
        log.debug("b:{}", queue.dequeue());
        log.debug("4:{}", queue.toString());
        queue.dequeue();
        queue.dequeue();
        log.debug("4.5:{}", queue.toString());
        queue.dequeue();
        log.debug("5:{}", queue.toString());
    }

    @Test
    public void test3() {
        int k = 8;
        int parent = (k - 1) >>> 1;
        log.debug("parent:{}", parent);

        int a = 1 << 30;
        log.debug("a:{}", a);
    }
}
