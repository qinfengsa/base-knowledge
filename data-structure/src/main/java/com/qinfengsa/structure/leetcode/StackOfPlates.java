package com.qinfengsa.structure.leetcode;

import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

/**
 * 面试题 03.03. 堆盘子
 *
 * <p>堆盘子。设想有一堆盘子，堆太高可能会倒下来。因此，在现实生活中，盘子堆到一定高度时，我们就会另外堆一堆盘子。请实现数据结构SetOfStacks，
 * 模拟这种行为。SetOfStacks应该由多个栈组成，并且在前一个栈填满时新建一个栈。
 * 此外，SetOfStacks.push()和SetOfStacks.pop()应该与普通栈的操作方法相同（也就是说，pop()返回的值，应该跟只有一个栈时的情况一样）。
 * 进阶：实现一个popAt(int index)方法，根据指定的子栈，执行pop操作。
 *
 * <p>当某个栈为空时，应当删除该栈。当栈中没有元素或不存在该栈时，pop，popAt 应返回 -1.
 *
 * <p>示例1:
 *
 * <p>输入： ["StackOfPlates", "push", "push", "popAt", "pop", "pop"] [[1], [1], [2], [1], [], []] 输出：
 * [null, null, null, 2, 1, -1] 示例2:
 *
 * <p>输入： ["StackOfPlates", "push", "push", "push", "popAt", "popAt", "popAt"] [[2], [1], [2], [3],
 * [0], [0], [0]] 输出： [null, null, null, null, 2, 1, 3]
 *
 * @author qinfengsa
 * @date 2020/06/10 06:37
 */
@Slf4j
public class StackOfPlates {
    private int cap;

    private List<int[]> stackList;

    private List<Integer> topList;

    public StackOfPlates(int cap) {
        this.cap = cap;
        if (cap == 0) {
            return;
        }
        this.stackList = new ArrayList<>();
        this.topList = new ArrayList<>();
        stackList.add(new int[cap]);
        topList.add(-1);
    }

    public void push(int val) {
        if (cap == 0) {
            return;
        }
        int[] stack;
        if (topList.size() == 0 || topList.get(topList.size() - 1) == cap - 1) {
            stack = new int[cap];
            stack[0] = val;
            stackList.add(stack);
            topList.add(0);
        } else {
            int index = topList.size() - 1;
            int top = topList.get(index);
            stack = stackList.get(index);
            stack[++top] = val;
            topList.set(index, top);
        }
    }

    public int pop() {
        if (cap == 0) {
            return -1;
        }
        for (int i = topList.size() - 1; i >= 0; i--) {
            int top = topList.get(i);
            if (top == -1) {
                continue;
            }
            int[] stack = stackList.get(i);
            topList.set(i, top - 1);
            int result = stack[top];
            removeStack();
            return result;
        }

        return -1;
    }

    public int popAt(int index) {
        if (cap == 0) {
            return -1;
        }
        if (index >= topList.size()) {
            return -1;
        }
        int top = topList.get(index);
        if (top == -1) {
            return -1;
        }
        int[] stack = stackList.get(index);
        topList.set(index, top - 1);
        int result = stack[top];
        removeStack();
        return result;
    }

    private void removeStack() {
        for (int i = topList.size() - 1; i >= 0; i--) {
            int top = topList.get(i);
            if (top == -1) {
                topList.remove(i);
                stackList.remove(i);
            }
        }
    }

    public static void main(String[] args) {
        StackOfPlates stackOfPlates = new StackOfPlates(6);
        int val0 = stackOfPlates.pop();
        log.debug("val0：{} ", val0);
        int val1 = stackOfPlates.pop();
        log.debug("val1：{} ", val1);
        int val2 = stackOfPlates.popAt(1);
        log.debug("val2：{} ", val2);
        int val3 = stackOfPlates.popAt(3);
        log.debug("val3：{} ", val3);
        int val4 = stackOfPlates.pop();
        log.debug("val4：{} ", val4);
        stackOfPlates.push(40);
        stackOfPlates.push(10);
        stackOfPlates.push(44);
        stackOfPlates.push(44);
        int val9 = stackOfPlates.pop();
        log.debug("val9：{} ", val9);
        stackOfPlates.push(24);
        stackOfPlates.push(42);
        int val12 = stackOfPlates.popAt(4);
        log.debug("val12：{} ", val12);
        int val13 = stackOfPlates.pop();
        log.debug("val13：{} ", val13);
        int val14 = stackOfPlates.popAt(0);
        log.debug("val14：{} ", val14);
        stackOfPlates.push(42);
        int val16 = stackOfPlates.popAt(5);
        log.debug("val16：{} ", val16);
        int val17 = stackOfPlates.pop();
        log.debug("val17：{} ", val17);
        stackOfPlates.push(29);
        int val19 = stackOfPlates.pop();
        log.debug("val19：{} ", val19);
        int val20 = stackOfPlates.pop();
        log.debug("val20：{} ", val20);
        int val21 = stackOfPlates.pop();
        log.debug("val21：{} ", val21);
        int val22 = stackOfPlates.popAt(0);
        log.debug("val22：{} ", val22);
        stackOfPlates.push(56);
        int val24 = stackOfPlates.pop();
        log.debug("val24：{} ", val24);
        int val25 = stackOfPlates.popAt(4);
        log.debug("val25：{} ", val25);
        int val26 = stackOfPlates.pop();
        log.debug("val26：{} ", val26);
        stackOfPlates.push(34);
        int val28 = stackOfPlates.popAt(1);
        log.debug("val28：{} ", val28);
        int val29 = stackOfPlates.popAt(4);
        log.debug("val29：{} ", val29);
        stackOfPlates.push(52);
        int val31 = stackOfPlates.popAt(4);
        log.debug("val31：{} ", val31);
        int val32 = stackOfPlates.popAt(6);
        log.debug("val32：{} ", val32);
        stackOfPlates.push(63);
        int val34 = stackOfPlates.pop();
        log.debug("val34：{} ", val34);
        int val35 = stackOfPlates.popAt(6);
        log.debug("val35：{} ", val35);
        int val36 = stackOfPlates.popAt(6);
        log.debug("val36：{} ", val36);
        int val37 = stackOfPlates.popAt(1);
        log.debug("val37：{} ", val37);
        int val38 = stackOfPlates.pop();
        log.debug("val38：{} ", val38);
        int val39 = stackOfPlates.popAt(6);
        log.debug("val39：{} ", val39);
        int val40 = stackOfPlates.popAt(2);
        log.debug("val40：{} ", val40);
        stackOfPlates.push(47);
        int val42 = stackOfPlates.popAt(1);
        log.debug("val42：{} ", val42);
        stackOfPlates.push(45);
        stackOfPlates.push(52);
        int val45 = stackOfPlates.pop();
        log.debug("val45：{} ", val45);
        int val46 = stackOfPlates.popAt(6);
        log.debug("val46：{} ", val46);
        int val47 = stackOfPlates.popAt(6);
        log.debug("val47：{} ", val47);
        stackOfPlates.push(20);
        int val49 = stackOfPlates.popAt(4);
        log.debug("val49：{} ", val49);
        stackOfPlates.push(17);
        int val51 = stackOfPlates.pop();
        log.debug("val51：{} ", val51);
        int val52 = stackOfPlates.pop();
        log.debug("val52：{} ", val52);
        stackOfPlates.push(43);
        stackOfPlates.push(6);
        stackOfPlates.push(30);
        int val56 = stackOfPlates.popAt(2);
        log.debug("val56：{} ", val56);
        int val57 = stackOfPlates.popAt(3);
        log.debug("val57：{} ", val57);
        int val58 = stackOfPlates.pop();
        log.debug("val58：{} ", val58);
        int val59 = stackOfPlates.popAt(3);
        log.debug("val59：{} ", val59);
        int val60 = stackOfPlates.pop();
        log.debug("val60：{} ", val60);
        int val61 = stackOfPlates.pop();
        log.debug("val61：{} ", val61);
        stackOfPlates.push(51);
        stackOfPlates.push(46);
    }
}
