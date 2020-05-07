package com.qinfengsa.structure.leetcode;

import com.qinfengsa.structure.util.LogUtils;
import lombok.extern.slf4j.Slf4j;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 面试题59 - II. 队列的最大值
 * 请定义一个队列并实现函数 max_value 得到队列里的最大值，要求函数max_value、push_back 和 pop_front 的时间复杂度都是O(1)。
 *
 * 若队列为空，pop_front 和 max_value 需要返回 -1
 *
 * 示例 1：
 *
 * 输入:
 * ["MaxQueue","push_back","push_back","max_value","pop_front","max_value"]
 * [[],[1],[2],[],[],[]]
 * 输出: [null,null,null,2,1,2]
 * 示例 2：
 *
 * 输入:
 * ["MaxQueue","pop_front","max_value"]
 * [[],[],[]]
 * 输出: [null,-1,-1]
 *
 *
 * 限制：
 *
 * 1 <= push_back,pop_front,max_value的总操作数 <= 10000
 * 1 <= value <= 10^5
 * @author: qinfengsa
 * @date: 2020/3/7 07:21
 */
@Slf4j
public class MaxQueue {




    public static void main(String[] args) {
        MaxQueue queue = new MaxQueue();
        /*queue.push_back(1);
        queue.push_back(2);
        LogUtils.logResult(queue.max_value());
        LogUtils.logResult(queue.pop_front());
        LogUtils.logResult(queue.max_value());*/
        int val0 = queue.max_value();
        log.debug("val0：{} ",val0);
        int val1 = queue.pop_front();
        log.debug("val1：{} ",val1);
        int val2 = queue.max_value();
        log.debug("val2：{} ",val2);
        queue.push_back(46);
        int val4 = queue.max_value();
        log.debug("val4：{} ",val4);
        int val5 = queue.pop_front();
        log.debug("val5：{} ",val5);
        int val6 = queue.max_value();
        log.debug("val6：{} ",val6);
        int val7 = queue.pop_front();
        log.debug("val7：{} ",val7);
        queue.push_back(868);
        int val9 = queue.pop_front();
        log.debug("val9：{} ",val9);
        int val10 = queue.pop_front();
        log.debug("val10：{} ",val10);
        int val11 = queue.pop_front();
        log.debug("val11：{} ",val11);
        queue.push_back(525);
        int val13 = queue.pop_front();
        log.debug("val13：{} ",val13);
        int val14 = queue.max_value();
        log.debug("val14：{} ",val14);
        int val15 = queue.pop_front();
        log.debug("val15：{} ",val15);
        int val16 = queue.max_value();
        log.debug("val16：{} ",val16);
        queue.push_back(123);
        queue.push_back(646);
        int val19 = queue.max_value();
        log.debug("val19：{} ",val19);
        queue.push_back(229);
        int val21 = queue.max_value();
        log.debug("val21：{} ",val21);
        int val22 = queue.max_value();
        log.debug("val22：{} ",val22);
        int val23 = queue.max_value();
        log.debug("val23：{} ",val23);
        queue.push_back(871);
        int val25 = queue.pop_front();
        log.debug("val25：{} ",val25);
        int val26 = queue.max_value();
        log.debug("val26：{} ",val26);
        queue.push_back(285);
        int val28 = queue.max_value();
        log.debug("val28：{} ",val28);
        int val29 = queue.max_value();
        log.debug("val29：{} ",val29);
        int val30 = queue.max_value();
        log.debug("val30：{} ",val30);
        int val31 = queue.pop_front();
        log.debug("val31：{} ",val31);
        queue.push_back(45);
        queue.push_back(140);
        queue.push_back(837);
        queue.push_back(545);
        int val36 = queue.pop_front();
        log.debug("val36：{} ",val36);
        int val37 = queue.pop_front();
        log.debug("val37：{} ",val37);
        int val38 = queue.max_value();
        log.debug("val38：{} ",val38);
        int val39 = queue.pop_front();
        log.debug("val39：{} ",val39);
        int val40 = queue.pop_front();
        log.debug("val40：{} ",val40);
        int val41 = queue.max_value();
        log.debug("val41：{} ",val41);
        queue.push_back(561);
        queue.push_back(237);
        int val44 = queue.pop_front();
        log.debug("val44：{} ",val44);
        queue.push_back(633);
        queue.push_back(98);
        queue.push_back(806);
        queue.push_back(717);
        int val49 = queue.pop_front();
        log.debug("val49：{} ",val49);
        int val50 = queue.max_value();
        log.debug("val50：{} ",val50);
        queue.push_back(186);
        int val52 = queue.max_value();
        log.debug("val52：{} ",val52);
        int val53 = queue.max_value();
        log.debug("val53：{} ",val53);
        int val54 = queue.pop_front();
        log.debug("val54：{} ",val54);
        int val55 = queue.max_value();
        log.debug("val55：{} ",val55);
        int val56 = queue.max_value();
        log.debug("val56：{} ",val56);
        int val57 = queue.max_value();
        log.debug("val57：{} ",val57);
        queue.push_back(268);
        int val59 = queue.pop_front();
        log.debug("val59：{} ",val59);
        queue.push_back(29);
        int val61 = queue.pop_front();
        log.debug("val61：{} ",val61);
        int val62 = queue.max_value();
        log.debug("val62：{} ",val62);
        int val63 = queue.max_value();
        log.debug("val63：{} ",val63);
        int val64 = queue.max_value();
        log.debug("val64：{} ",val64);
        queue.push_back(866);
        int val66 = queue.pop_front();
        log.debug("val66：{} ",val66);
        queue.push_back(239);
        queue.push_back(3);
        queue.push_back(850);
        int val70 = queue.pop_front();
        log.debug("val70：{} ",val70);
        int val71 = queue.max_value();
        log.debug("val71：{} ",val71);
        int val72 = queue.pop_front();
        log.debug("val72：{} ",val72);
        int val73 = queue.max_value();
        log.debug("val73：{} ",val73);
        int val74 = queue.max_value();
        log.debug("val74：{} ",val74);
        int val75 = queue.max_value();
        log.debug("val75：{} ",val75);
        int val76 = queue.pop_front();
        log.debug("val76：{} ",val76);
        queue.push_back(310);
        int val78 = queue.pop_front();
        log.debug("val78：{} ",val78);
        queue.push_back(674);
        queue.push_back(770);
        int val81 = queue.pop_front();
        log.debug("val81：{} ",val81);
        queue.push_back(525);
        int val83 = queue.pop_front();
        log.debug("val83：{} ",val83);
        queue.push_back(425);
        int val85 = queue.pop_front();
        log.debug("val85：{} ",val85);
        int val86 = queue.pop_front();
        log.debug("val86：{} ",val86);
        queue.push_back(720);
        int val88 = queue.pop_front();
        log.debug("val88：{} ",val88);
        int val89 = queue.pop_front();
        log.debug("val89：{} ",val89);
        int val90 = queue.pop_front();
        log.debug("val90：{} ",val90);
        queue.push_back(373);
        queue.push_back(411);
        int val93 = queue.max_value();
        log.debug("val93：{} ",val93);
        queue.push_back(831);
        int val95 = queue.pop_front();
        log.debug("val95：{} ",val95);
        queue.push_back(765);
        queue.push_back(701);
        int val98 = queue.pop_front();
        log.debug("val98：{} ",val98);
    }

    private Queue<Integer> valQueue;

    private Deque<Integer> maxQueue;

    public MaxQueue() {
        valQueue = new LinkedList<>();
        maxQueue = new LinkedList<>();
        maxQueue.addLast(Integer.MIN_VALUE);
    }
    /**
     * 获取队列的最大值
     * @return
     */
    public int max_value() {
        if (valQueue.isEmpty()) {
            return -1;
        }
        return maxQueue.peekFirst();
    }

    /**
     * 入队
     * @param value
     */
    public void push_back(int value) {
        valQueue.offer(value);
        while(!maxQueue.isEmpty() && maxQueue.peekLast() < value){
            maxQueue.removeLast();
        }
        maxQueue.addLast(value);
        /*QueueNode node = new QueueNode(value);
        Integer max = tail.max;
        if (max == null) {
            node.max = value;
        } else {
            node.max = Math.max(max,value);
        }
        tail.next = node;
        tail = node;
        size++;*/
    }

    /**
     * 出队
     * @return
     */
    public int pop_front() {
        if (valQueue.isEmpty()) {
            return -1;
        }
        int front = valQueue.poll();
        if(maxQueue.peekFirst() == front){
            maxQueue.removeFirst();
        }
        return front;

    }
   /* int size;

    QueueNode head;

    QueueNode tail;

    public MaxQueue() {
        size = 0;
        tail = head = new QueueNode(-1);
    }

    public int max_value() {
        if (size <= 0) {
            return -1;
        }
        return tail.max;
    }

    public void push_back(int value) {
        QueueNode node = new QueueNode(value);
        Integer max = tail.max;
        if (max == null) {
            node.max = value;
        } else {
            node.max = Math.max(max,value);
        }
        tail.next = node;
        tail = node;
        size++;
    }

    public int pop_front() {
        if (size <= 0) {
            return -1;
        }
        QueueNode node = head.next ;
        head.next = node.next;
        size--;
        if (size == 0) {
            tail = head;
        }
        return node.val;
    }


    class QueueNode {
        private int val;

        private Integer max;


        private QueueNode next;

        QueueNode(int val) {
            this.val = val;
            this.next = null;
        }
    }*/
}
