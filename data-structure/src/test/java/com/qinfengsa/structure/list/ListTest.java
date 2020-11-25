package com.qinfengsa.structure.list;

import com.qinfengsa.base.Node;
import com.qinfengsa.structure.linearlist.LinearList;
import com.qinfengsa.structure.linearlist.array.ListArray;
import com.qinfengsa.structure.linearlist.linkedlist.LinkedList;
import com.qinfengsa.structure.linearlist.linkedlist.dllist.ListDLinked;
import com.qinfengsa.structure.linearlist.linkedlist.sllist.ListSLinked;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

/**
 * 线性表测试
 *
 * @author: qinfengsa
 * @date: 2019/5/3 16:09
 */
@Slf4j
public class ListTest {

    @Test
    public void testListArray() {
        LinearList listArray = new ListArray();
        log.debug("empty:{}", listArray.isEmpty());

        listArray.insert(0, 2);
        log.debug("empty:{}", listArray.isEmpty());
        listArray.insert(1, 3);
        log.debug("size:{}", listArray.getSize());
        listArray.insert(2, 5);
        listArray.insert(3, 8);
        listArray.insert(4, 13);
        listArray.insert(5, 21);
        listArray.insert(6, 34);
        // listArray.insert(7,55);

        boolean b = listArray.insertBefore(34, 24);
        boolean b1 = listArray.insertAfter(34, 11);
        log.debug("success:{},{}", b, b1);
        log.debug(listArray.toString());
        Integer a = 34;
        listArray.remove(a);
        log.debug(listArray.toString());

        log.debug("tsest:{}", listArray.get(5));
    }

    @Test
    public void testListSLinked() {
        LinearList list = new ListSLinked();
        log.debug("empty:{}", list.isEmpty());

        list.insert(0, "2");
        log.debug("empty:{}", list.isEmpty());
        list.insert(1, "3");
        log.debug("size:{}", list.getSize());
        list.insert(2, "5");
        list.insert(3, "8");
        list.insert(4, "13");
        list.insert(5, "21");
        list.insert(6, "34");

        list.remove(3);
        log.debug(list.toString());
        list.remove("2");
        log.debug(list.toString());

        list.replace(0, "44");
        log.debug(list.toString());
        // listArray.insert(7,55);

        boolean b = list.insertBefore("34", "24");
        boolean b1 = list.insertAfter("34", "11");
        log.debug("success:{},{}", b, b1);
        log.debug(list.toString());

        list.remove("24");
        log.debug(list.toString());

        log.debug("tsest:{}", list.get(5));
    }

    @Test
    public void testListDLinked() {
        LinkedList list = new ListDLinked();
        log.debug("empty:{}", list.isEmpty());

        list.insertFirst("2");
        log.debug("empty:{}", list.isEmpty());

        list.insertLast("3");
        log.debug("size:{}", list.getSize());
        list.insertLast("5");
        list.insertLast("8");
        list.insertLast("13");
        list.insertLast("21");
        list.insertLast("34");
        log.debug(list.toString());

        Node lastNode = list.last();
        // list.remove(lastNode);
        // log.debug(list.toString());
        // list.removeLast();
        // log.debug(list.toString());

        list.replace(lastNode, "44");
        log.debug(list.toString());
        // listArray.insert(7,55);

        Node node1 = list.insertBefore(lastNode, "24");
        Node node2 = list.insertAfter(lastNode, "11");
        log.debug("success:{},{}", node1, node2);
        Node nextNode = list.getNext(node2);
        log.debug("next:{} ", nextNode);

        Node preNode = list.getPre(node1);
        log.debug("pre:{} ", preNode);
        log.debug(list.toString());

        // list.remove("24");
        // log.debug(list.toString());

        // log.debug("tsest:{}",list.get(5));
    }
}
