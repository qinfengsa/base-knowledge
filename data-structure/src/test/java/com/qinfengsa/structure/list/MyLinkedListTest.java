package com.qinfengsa.structure.list;

import com.qinfengsa.structure.leetcode.MyLinkedList;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

/**
 * @author: qinfengsa
 * @date: 2019/5/4 18:37
 */
@Slf4j
public class MyLinkedListTest {

    @Test
    public void test1() throws Exception {
        MyLinkedList linkedList = new MyLinkedList();
        linkedList.addAtHead(1);
        log.debug(linkedList.toString());
        linkedList.addAtTail(3);
        log.debug(linkedList.toString());
        linkedList.addAtIndex(1, 2); // 链表变为1-> 2-> 3
        log.debug(linkedList.toString());
        int a = linkedList.get(1); // 返回2
        log.debug("a:{}", a);
        linkedList.deleteAtIndex(1); // 现在链表是1-> 3
        log.debug(linkedList.toString());
        int b = linkedList.get(1); // 返回3
        log.debug("b:{}", b);
    }

    @Test
    public void test2() throws Exception {
        MyLinkedList linkedList = new MyLinkedList();
        linkedList.addAtHead(38);
        linkedList.addAtHead(45);
        log.debug("size:{}", linkedList.getSize());
        linkedList.deleteAtIndex(2);
        linkedList.addAtIndex(1, 24);
        linkedList.addAtTail(36);
        linkedList.addAtIndex(3, 72);
        linkedList.addAtTail(76);
        linkedList.addAtHead(7);
        linkedList.addAtHead(36);
        linkedList.addAtHead(34);
        linkedList.addAtTail(91);
        linkedList.addAtTail(69);
        linkedList.addAtHead(37);
        linkedList.addAtTail(38);
        linkedList.addAtTail(4);
        linkedList.addAtHead(66);
        linkedList.addAtTail(38);
        linkedList.deleteAtIndex(14);
        linkedList.addAtTail(12);
        linkedList.addAtTail(32);
        linkedList.get(5);
        linkedList.addAtIndex(7, 5);
        linkedList.addAtHead(74);
        linkedList.get(7);
        linkedList.get(13);
        linkedList.addAtHead(11);
        linkedList.get(8);
        linkedList.addAtIndex(10, 9);
        linkedList.addAtTail(19);
        linkedList.addAtIndex(3, 76);
        linkedList.addAtHead(7);
        linkedList.addAtHead(37);
        linkedList.addAtHead(99);
        linkedList.get(10);
        linkedList.addAtHead(12);
        linkedList.addAtIndex(1, 20);
        linkedList.addAtTail(29);
        linkedList.addAtHead(42);
        linkedList.addAtIndex(21, 52);
        linkedList.get(11);
        linkedList.addAtTail(44);
        linkedList.addAtTail(47);
        linkedList.addAtIndex(6, 27);
        linkedList.addAtIndex(31, 85);
        linkedList.addAtHead(59);
        linkedList.addAtHead(57);
        linkedList.get(4);
        linkedList.addAtTail(99);
        linkedList.addAtIndex(13, 83);
        linkedList.addAtIndex(10, 34);
        linkedList.addAtHead(48);
        linkedList.deleteAtIndex(9);
        linkedList.addAtIndex(22, 64);
        linkedList.addAtHead(69);
        linkedList.deleteAtIndex(33);
        linkedList.addAtTail(5);
        linkedList.deleteAtIndex(18);
        linkedList.addAtTail(87);
        linkedList.addAtHead(42);
        linkedList.addAtTail(1);
        linkedList.addAtTail(35);
        linkedList.addAtHead(31);
        linkedList.addAtTail(67);
        linkedList.addAtIndex(36, 46);
        linkedList.deleteAtIndex(23);
        linkedList.addAtHead(64);
        linkedList.addAtHead(81);
        linkedList.addAtHead(29);
        linkedList.addAtTail(50);
        linkedList.get(23);
        linkedList.addAtIndex(36, 63);
        linkedList.addAtTail(8);
        linkedList.addAtTail(19);
        linkedList.addAtTail(98);
        linkedList.deleteAtIndex(22);
        linkedList.get(28);
        linkedList.addAtHead(42);
        linkedList.get(24);
        linkedList.get(34);
        linkedList.addAtTail(32);
        linkedList.deleteAtIndex(25);
        linkedList.addAtTail(53);
        linkedList.addAtIndex(55, 76);
        linkedList.addAtHead(38);
        linkedList.addAtIndex(23, 98);
        linkedList.addAtTail(27);
        linkedList.get(18);
        linkedList.addAtIndex(44, 27);
        linkedList.addAtIndex(16, 8);
        linkedList.addAtHead(70);
        linkedList.addAtHead(15);
        linkedList.get(9);
        linkedList.get(27);
        linkedList.get(59);
        linkedList.addAtIndex(40, 50);
        linkedList.addAtHead(15);
        linkedList.addAtIndex(11, 57);
        linkedList.addAtHead(80);
        linkedList.addAtTail(50);
        linkedList.addAtIndex(23, 37);
        linkedList.get(12);
    }
}
