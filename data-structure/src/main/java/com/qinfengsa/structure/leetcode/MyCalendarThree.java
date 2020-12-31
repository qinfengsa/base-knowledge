package com.qinfengsa.structure.leetcode;

import java.util.Map;
import java.util.TreeMap;
import lombok.extern.slf4j.Slf4j;

/**
 * 732. 我的日程安排表 III
 *
 * <p>实现一个 MyCalendar 类来存放你的日程安排，你可以一直添加新的日程安排。
 *
 * <p>MyCalendar 有一个 book(int start, int end)方法。它意味着在start到end时间内增加一个日程安排，注意，这里的时间是半开区间，即 [start,
 * end), 实数 x 的范围为， start <= x < end。
 *
 * <p>当 K 个日程安排有一些时间上的交叉时（例如K个日程安排都在同一时间内），就会产生 K 次预订。
 *
 * <p>每次调用 MyCalendar.book方法时，返回一个整数 K ，表示最大的 K 次预订。
 *
 * <p>请按照以下步骤调用MyCalendar 类: MyCalendar cal = new MyCalendar(); MyCalendar.book(start, end)
 *
 * <p>示例 1:
 *
 * <p>MyCalendarThree();
 *
 * <p>MyCalendarThree.book(10, 20); // returns 1
 *
 * <p>MyCalendarThree.book(50, 60); // returns 1
 *
 * <p>MyCalendarThree.book(10, 40); // returns 2
 *
 * <p>MyCalendarThree.book(5, 15); // returns 3
 *
 * <p>MyCalendarThree.book(5, 10); // returns 3
 *
 * <p>MyCalendarThree.book(25, 55); // returns 3
 *
 * <p>解释: 前两个日程安排可以预订并且不相交，所以最大的K次预订是1。 第三个日程安排[10,40]与第一个日程安排相交，最高的K次预订为2。 其余的日程安排的最高K次预订仅为3。
 * 请注意，最后一次日程安排可能会导致局部最高K次预订为2，但答案仍然是3，原因是从开始到最后，时间[10,20]，[10,40]和[5,15]仍然会导致3次预订。 说明:
 *
 * <p>每个测试用例，调用 MyCalendar.book 函数最多不超过 400次。 调用函数 MyCalendar.book(start, end)时， start 和 end 的取值范围为
 * [0, 10^9]。
 *
 * @author qinfengsa
 * @date 2020/12/30 20:45
 */
@Slf4j
public class MyCalendarThree {
    // map.floorEntry() : 包含且向下取值
    // map.lowerEntry() : 不包含向下取值
    // map.ceilingEntry() : 包含且向上取值
    // map.higherEntry() ： 不包含向上取值
    // map.subMap(left, right) : 左右默认包含 区间取值
    // map.subMap(left, false, right, false) : 左右可选，区间取值
    TreeMap<Integer, Integer> calendar;

    int max = 0;

    public MyCalendarThree() {
        calendar = new TreeMap<>();
        calendar.put(0, 0);
    }

    public int book(int start, int end) {
        Map.Entry<Integer, Integer> leftEntry = calendar.floorEntry(start);
        int count = leftEntry.getValue() + 1;
        calendar.put(start, leftEntry.getValue() + 1);
        max = Math.max(max, count);
        // 遍历并更新(start,end)范围内的key
        Map.Entry<Integer, Integer> ce = calendar.ceilingEntry(start + 1);
        while (ce != null && ce.getKey() < end) {
            count = ce.getValue() + 1;
            calendar.put(ce.getKey(), count);
            max = Math.max(max, count);
            ce = calendar.ceilingEntry(ce.getKey() + 1);
        }
        // 如果end处无entry，插入end对应的value为k-1，即上一步遍历到的最后一个key对应的value-1
        if (ce == null || ce.getKey() != end) {
            calendar.put(end, count - 1);
        }

        return max;
    }

    public static void main(String[] args) {
        MyCalendarThree three = new MyCalendarThree();
        int val1 = three.book(10, 20); // returns 1
        log.debug("val1:{}", val1);
        int val2 = three.book(50, 60); // returns 1
        log.debug("val2:{}", val2);
        int val3 = three.book(10, 40); // returns 2
        log.debug("val3:{}", val3);
        int val4 = three.book(5, 15); // returns 3
        log.debug("val4:{}", val4);
        int val5 = three.book(5, 10); // returns 3
        log.debug("val5:{}", val5);
        int val6 = three.book(25, 55); // returns 3
        log.debug("val6:{}", val6);
    }
}
