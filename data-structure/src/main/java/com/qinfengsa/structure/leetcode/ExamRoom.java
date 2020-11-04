package com.qinfengsa.structure.leetcode;

import java.util.Objects;
import java.util.TreeSet;

/**
 * 855. 考场就座
 *
 * <p>在考场里，一排有 N 个座位，分别编号为 0, 1, 2, ..., N-1 。
 *
 * <p>当学生进入考场后，他必须坐在能够使他与离他最近的人之间的距离达到最大化的座位上。如果有多个这样的座位，他会坐在编号最小的座位上。(另外，如果考场里没有人，那么学生就坐在 0 号座位上。)
 *
 * <p>返回 ExamRoom(int N) 类，它有两个公开的函数：其中，函数 ExamRoom.seat() 会返回一个 int （整型数据），代表学生坐的位置；函数
 * ExamRoom.leave(int p) 代表坐在座位 p 上的学生现在离开了考场。每次调用 ExamRoom.leave(p) 时都保证有学生坐在座位 p 上。
 *
 * <p>示例：
 *
 * <p>输入：["ExamRoom","seat","seat","seat","seat","leave","seat"], [[10],[],[],[],[],[4],[]]
 * 输出：[null,0,9,4,2,null,5] 解释： ExamRoom(10) -> null seat() -> 0，没有人在考场里，那么学生坐在 0 号座位上。 seat() ->
 * 9，学生最后坐在 9 号座位上。 seat() -> 4，学生最后坐在 4 号座位上。 seat() -> 2，学生最后坐在 2 号座位上。 leave(4) -> null seat() ->
 * 5，学生最后坐在 5 号座位上。
 *
 * <p>提示：
 *
 * <p>1 <= N <= 10^9 在所有的测试样例中 ExamRoom.seat() 和 ExamRoom.leave() 最多被调用 10^4 次。 保证在调用
 * ExamRoom.leave(p) 时有学生正坐在座位 p 上。
 *
 * @author qinfengsa
 * @date 2020/11/3 19:26
 */
public class ExamRoom {

    int N;

    TreeSet<Integer> seats;

    public ExamRoom(int N) {
        seats = new TreeSet<>();
        this.N = N;
    }

    /**
     * 返回一个 int （整型数据），代表学生坐的位置
     *
     * @return
     */
    public int seat() {
        int seat = 0;
        if (seats.size() > 0) {
            // 距离
            int maxDist = seats.first();
            Integer prev = null;
            for (int s : seats) {
                if (Objects.nonNull(prev)) {
                    // 距离
                    int d = (s - prev) >> 1;
                    if (d > maxDist) {
                        maxDist = d;
                        seat = prev + d;
                    }
                }
                prev = s;
            }
            int d = this.N - 1 - prev;
            if (d > maxDist) {
                seat = N - 1;
            }
        }

        seats.add(seat);
        return seat;
    }

    /**
     * 在座位 p 上的学生现在离开了考场
     *
     * @param p
     */
    public void leave(int p) {
        seats.remove(p);
    }
}
