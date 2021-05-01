package com.qinfengsa.structure.leetcode;

import java.util.TreeSet;

/**
 * 5731. 座位预约管理系统
 *
 * <p>请你设计一个管理 n 个座位预约的系统，座位编号从 1 到 n 。
 *
 * <p>请你实现 SeatManager 类：
 *
 * <p>SeatManager(int n) 初始化一个 SeatManager 对象，它管理从 1 到 n 编号的 n 个座位。所有座位初始都是可预约的。 int reserve()
 * 返回可以预约座位的 最小编号 ，此座位变为不可预约。 void unreserve(int seatNumber) 将给定编号 seatNumber 对应的座位变成可以预约。
 *
 * <p>示例 1：
 *
 * <p>输入： ["SeatManager", "reserve", "reserve", "unreserve", "reserve", "reserve", "reserve",
 * "reserve", "unreserve"] [[5], [], [], [2], [], [], [], [], [5]] 输出： [null, 1, 2, null, 2, 3, 4,
 * 5, null]
 *
 * <p>解释： SeatManager seatManager = new SeatManager(5); // 初始化 SeatManager ，有 5 个座位。
 * seatManager.reserve(); // 所有座位都可以预约，所以返回最小编号的座位，也就是 1 。 seatManager.reserve(); // 可以预约的座位为
 * [2,3,4,5] ，返回最小编号的座位，也就是 2 。 seatManager.unreserve(2); // 将座位 2 变为可以预约，现在可预约的座位为 [2,3,4,5] 。
 * seatManager.reserve(); // 可以预约的座位为 [2,3,4,5] ，返回最小编号的座位，也就是 2 。 seatManager.reserve(); //
 * 可以预约的座位为 [3,4,5] ，返回最小编号的座位，也就是 3 。 seatManager.reserve(); // 可以预约的座位为 [4,5] ，返回最小编号的座位，也就是 4 。
 * seatManager.reserve(); // 唯一可以预约的是座位 5 ，所以返回 5 。 seatManager.unreserve(5); // 将座位 5
 * 变为可以预约，现在可预约的座位为 [5] 。
 *
 * <p>提示：
 *
 * <p>1 <= n <= 105 1 <= seatNumber <= n 每一次对 reserve 的调用，题目保证至少存在一个可以预约的座位。 每一次对 unreserve 的调用，题目保证
 * seatNumber 在调用函数前都是被预约状态。 对 reserve 和 unreserve 的调用 总共 不超过 105 次。
 *
 * @author qinfengsa
 * @date 2021/05/01 22:38
 */
public class SeatManager {

    private final int n;
    // 最小
    private int midIdx;

    TreeSet<Integer> set;

    /**
     * 初始化一个 SeatManager 对象，它管理从 1 到 n 编号的 n 个座位。所有座位初始都是可预约的
     *
     * @param n
     */
    public SeatManager(int n) {
        this.n = n;
        this.midIdx = 1;
        this.set = new TreeSet<>();
    }

    /**
     * 返回可以预约座位的 最小编号 ，此座位变为不可预约
     *
     * @return
     */
    public int reserve() {
        if (!set.isEmpty()) {
            return set.pollFirst();
        }
        return midIdx++;
    }

    /**
     * 将给定编号 seatNumber 对应的座位变成可以预约。
     *
     * @param seatNumber
     */
    public void unreserve(int seatNumber) {
        set.add(seatNumber);
    }
}
