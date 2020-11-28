package com.qinfengsa.structure.leetcode;

import lombok.extern.slf4j.Slf4j;

/**
 * 5560. 设计前中后队列
 *
 * <p>请你设计一个队列，支持在前，中，后三个位置的 push 和 pop 操作。
 *
 * <p>请你完成 FrontMiddleBack 类：
 *
 * <p>FrontMiddleBack() 初始化队列。
 *
 * <p>void pushFront(int val) 将 val 添加到队列的 最前面 。
 *
 * <p>void pushMiddle(int val) 将 val 添加到队列的 正中间 。
 *
 * <p>void pushBack(int val) 将 val 添加到队里的 最后面 。
 *
 * <p>int popFront() 将 最前面 的元素从队列中删除并返回值，如果删除之前队列为空，那么返回 -1 。
 *
 * <p>int popMiddle() 将 正中间 的元素从队列中删除并返回值，如果删除之前队列为空，那么返回 -1 。
 *
 * <p>int popBack() 将 最后面 的元素从队列中删除并返回值，如果删除之前队列为空，那么返回 -1 。 请注意当有 两个 中间位置的时候，选择靠前面的位置进行操作。比方说：
 *
 * <p>将 6 添加到 [1, 2, 3, 4, 5] 的中间位置，结果数组为 [1, 2, 6, 3, 4, 5] 。 从 [1, 2, 3, 4, 5, 6] 的中间位置弹出元素，返回 3
 * ，数组变为 [1, 2, 4, 5, 6] 。
 *
 * <p>示例 1：
 *
 * <p>输入： ["FrontMiddleBackQueue", "pushFront", "pushBack", "pushMiddle", "pushMiddle", "popFront",
 * "popMiddle", "popMiddle", "popBack", "popFront"] [[], [1], [2], [3], [4], [], [], [], [], []] 输出：
 * [null, null, null, null, null, 1, 3, 4, 2, -1]
 *
 * <p>解释： FrontMiddleBackQueue q = new FrontMiddleBackQueue(); q.pushFront(1); // [1] q.pushBack(2);
 * // [1, 2] q.pushMiddle(3); // [1, 3, 2] q.pushMiddle(4); // [1, 4, 3, 2] q.popFront(); // 返回 1 ->
 * [4, 3, 2] q.popMiddle(); // 返回 3 -> [4, 2] q.popMiddle(); // 返回 4 -> [2] q.popBack(); // 返回 2 ->
 * [] q.popFront(); // 返回 -1 -> [] （队列为空）
 *
 * <p>提示：
 *
 * <p>1 <= val <= 109 最多调用 1000 次 pushFront， pushMiddle， pushBack， popFront， popMiddle 和 popBack 。
 *
 * @author qinfengsa
 * @date 2020/11/28 22:55
 */
@Slf4j
public class FrontMiddleBackQueue {

    int[] nums;

    int size = 0;

    /** 初始化队列 */
    public FrontMiddleBackQueue() {
        nums = new int[2000];
    }

    /**
     * 将 val 添加到队列的 最前面
     *
     * @param val
     */
    public void pushFront(int val) {
        if (pushEmpty(val)) {
            return;
        }
        System.arraycopy(nums, 0, nums, 1, size);
        nums[0] = val;
        size++;
    }

    private boolean pushEmpty(int val) {
        if (size == 0) {
            nums[0] = val;
            size++;
            return true;
        }
        return false;
    }

    /**
     * 将 val 添加到队列的 正中间
     *
     * @param val
     */
    public void pushMiddle(int val) {
        if (pushEmpty(val)) {
            return;
        }
        int mid = size >> 1;
        System.arraycopy(nums, mid, nums, mid + 1, size - mid);
        nums[mid] = val;
        size++;
    }

    /**
     * 将 val 添加到队里的 最后面
     *
     * @param val
     */
    public void pushBack(int val) {
        if (pushEmpty(val)) {
            return;
        }
        nums[size++] = val;
    }

    /**
     * 将 最前面 的元素从队列中删除并返回值，如果删除之前队列为空，那么返回 -1
     *
     * @return
     */
    public int popFront() {
        if (size == 0) {
            return -1;
        }
        int val = nums[0];
        size--;
        System.arraycopy(nums, 1, nums, 0, size);
        nums[size] = 0;
        return val;
    }

    /**
     * 将 正中间 的元素从队列中删除并返回值，如果删除之前队列为空，那么返回 -1
     *
     * @return
     */
    public int popMiddle() {
        if (size == 0) {
            return -1;
        }
        int mid = (size - 1) >> 1;
        int val = nums[mid];
        size--;
        System.arraycopy(nums, mid + 1, nums, mid, size - mid);
        nums[size] = 0;
        return val;
    }

    /**
     * 将 最后面 的元素从队列中删除并返回值，如果删除之前队列为空，那么返回 -1
     *
     * @return
     */
    public int popBack() {
        if (size == 0) {
            return -1;
        }
        int val = nums[size - 1];
        nums[size - 1] = 0;
        size--;
        return val;
    }

    public static void main(String[] args) {

        FrontMiddleBackQueue q = new FrontMiddleBackQueue();
        q.pushFront(1); // [1]
        q.pushBack(2); // [1, 2]
        q.pushMiddle(3); // [1, 3, 2]
        q.pushMiddle(4); // [1, 4, 3, 2]
        int val1 = q.popFront(); // 返回 1 -> [4, 3, 2]
        log.debug("val1:{}", val1);
        int val2 = q.popMiddle(); // 返回 3 -> [4, 2]
        log.debug("val2:{}", val2);
        int val3 = q.popMiddle(); // 返回 4 -> [2]
        log.debug("val3:{}", val3);
        int val4 = q.popBack(); // 返回 2 -> []
        log.debug("val4:{}", val4);
        int val5 = q.popFront(); // 返回 -1 -> [] （队列为空）
        log.debug("val5:{}", val5);
    }
}
