package com.qinfengsa.structure.leetcode;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * 895. 最大频率栈
 *
 * <p>实现 FreqStack，模拟类似栈的数据结构的操作的一个类。
 *
 * <p>FreqStack 有两个函数：
 *
 * <p>push(int x)，将整数 x 推入栈中。 pop()，它移除并返回栈中出现最频繁的元素。 如果最频繁的元素不只一个，则移除并返回最接近栈顶的元素。
 *
 * <p>示例：
 *
 * <p>输入： ["FreqStack","push","push","push","push","push","push","pop","pop","pop","pop"],
 * [[],[5],[7],[5],[7],[4],[5],[],[],[],[]] 输出：[null,null,null,null,null,null,null,5,7,5,4] 解释： 执行六次
 * .push 操作后，栈自底向上为 [5,7,5,7,4,5]。然后：
 *
 * <p>pop() -> 返回 5，因为 5 是出现频率最高的。 栈变成 [5,7,5,7,4]。
 *
 * <p>pop() -> 返回 7，因为 5 和 7 都是频率最高的，但 7 最接近栈顶。 栈变成 [5,7,5,4]。
 *
 * <p>pop() -> 返回 5 。 栈变成 [5,7,4]。
 *
 * <p>pop() -> 返回 4 。 栈变成 [5,7]。
 *
 * <p>提示：
 *
 * <p>对 FreqStack.push(int x) 的调用中 0 <= x <= 10^9。 如果栈的元素数目为零，则保证不会调用 FreqStack.pop()。 单个测试样例中，对
 * FreqStack.push 的总调用次数不会超过 10000。 单个测试样例中，对 FreqStack.pop 的总调用次数不会超过 10000。 所有测试样例中，对
 * FreqStack.push 和 FreqStack.pop 的总调用次数不会超过 150000。
 *
 * @author qinfengsa
 * @date 2021/03/13 09:01
 */
public class FreqStack {

    // 频率
    private final Map<Integer, Integer> freqMap;

    // 频率下所有的元素
    private final Map<Integer, Deque<Integer>> freq2Stack;

    // 最大频率
    private int mapFreq = 0;

    public FreqStack() {
        freqMap = new HashMap<>();
        freq2Stack = new HashMap<>();
    }

    public void push(int val) {
        // 当前 val 的频率
        int curFreq = freqMap.getOrDefault(val, 0) + 1;
        freqMap.put(val, curFreq);
        if (curFreq > mapFreq) {
            mapFreq = curFreq;
        }

        Deque<Integer> stack = freq2Stack.computeIfAbsent(curFreq, k -> new LinkedList<>());
        stack.push(val);
    }

    public int pop() {
        Deque<Integer> stack = freq2Stack.get(mapFreq);
        int val = stack.pop();
        freqMap.put(val, freqMap.get(val) - 1);
        if (stack.isEmpty()) {
            mapFreq--;
        }

        return val;
    }
}
