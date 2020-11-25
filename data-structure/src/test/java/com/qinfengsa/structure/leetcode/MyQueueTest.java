package com.qinfengsa.structure.leetcode;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

/**
 * @author: qinfengsa
 * @date: 2019/5/27 17:11
 */
@Slf4j
public class MyQueueTest {

    @Test
    public void test1() {
        MyQueue queue = new MyQueue();

        queue.push(1);
        queue.push(2);
        int index = 0;
        log.debug("{},{}", index++, queue.peek()); // 返回 1
        log.debug("{},{}", index++, queue.pop()); // 返回 1
        log.debug("{},{}", index++, queue.empty()); // 返回 false
    }

    @Test
    public void test2() {
        MyStack stack = new MyStack();

        stack.push(1);
        stack.push(2);
        int index = 0;
        log.debug("{},{}", index++, stack.top()); // 返回 2
        log.debug("{},{}", index++, stack.pop()); // 返回 2
        log.debug("{},{}", index++, stack.pop()); // 返回 1
        log.debug("{},{}", index++, stack.empty()); // 返回 false
    }
}
