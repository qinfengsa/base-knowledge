package com.qinfengsa.structure.leetcode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 面试题 03.05. 栈排序 栈排序。
 *
 * <p>编写程序，对栈进行排序使最小元素位于栈顶。最多只能使用一个其他的临时栈存放数据，但不得将元素复制到别的数据结构（如数组）中。 该栈支持如下操作：push、pop、peek 和
 * isEmpty。当栈为空时，peek 返回 -1。
 *
 * <p>示例1:
 *
 * <p>输入： ["SortedStack", "push", "push", "peek", "pop", "peek"] [[], [1], [2], [], [], []] 输出：
 * [null,null,null,1,null,2] 示例2:
 *
 * <p>输入： ["SortedStack", "pop", "pop", "push", "pop", "isEmpty"] [[], [], [], [1], [], []] 输出：
 * [null,null,null,null,null,true] 说明:
 *
 * <p>栈中的元素数目在[0, 5000]范围内。
 *
 * @author qinfengsa
 * @date 2020/06/10 07:18
 */
public class SortedStack {

    private Deque<Integer> stack;

    public SortedStack() {
        stack = new LinkedList<>();
    }

    public void push(int val) {
        // stack 始终是有序的, 使用临时栈把 val 插入到对应的位置
        if (stack.isEmpty() || val <= stack.peek()) {
            stack.push(val);
            return;
        }

        // 建一个临时栈,把 小于val的元素移动到临时栈
        Deque<Integer> tmp = new LinkedList<>();
        while (!stack.isEmpty() && val > stack.peek()) {
            tmp.push(stack.pop());
        }
        stack.push(val);
        while (!tmp.isEmpty()) {
            stack.push(tmp.pop());
        }
    }

    public void pop() {
        if (stack.isEmpty()) {
            return;
        }
        stack.pop();
    }

    public int peek() {
        if (stack.isEmpty()) {
            return -1;
        }
        return stack.peek();
    }

    public boolean isEmpty() {
        return stack.isEmpty();
    }
}
