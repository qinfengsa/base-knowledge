package com.qinfengsa.structure.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;

/**
 * 981. 基于时间的键值存储
 *
 * <p>创建一个基于时间的键值存储类 TimeMap，它支持下面两个操作：
 *
 * <p>1. set(string key, string value, int timestamp)
 *
 * <p>存储键 key、值 value，以及给定的时间戳 timestamp。 2. get(string key, int timestamp)
 *
 * <p>返回先前调用 set(key, value, timestamp_prev) 所存储的值，其中 timestamp_prev <= timestamp。
 * 如果有多个这样的值，则返回对应最大的 timestamp_prev 的那个值。 如果没有值，则返回空字符串（""）。
 *
 * <p>示例 1：
 *
 * <p>输入：inputs = ["TimeMap","set","get","get","set","get","get"], inputs =
 * [[],["foo","bar",1],["foo",1],["foo",3],["foo","bar2",4],["foo",4],["foo",5]]
 * 输出：[null,null,"bar","bar",null,"bar2","bar2"] 解释： TimeMap kv; kv.set("foo", "bar", 1); // 存储键
 * "foo" 和值 "bar" 以及时间戳 timestamp = 1 kv.get("foo", 1); // 输出 "bar" kv.get("foo", 3); // 输出 "bar"
 * 因为在时间戳 3 和时间戳 2 处没有对应 "foo" 的值，所以唯一的值位于时间戳 1 处（即 "bar"） kv.set("foo", "bar2", 4); kv.get("foo",
 * 4); // 输出 "bar2" kv.get("foo", 5); // 输出 "bar2"
 *
 * <p>示例 2：
 *
 * <p>输入：inputs = ["TimeMap","set","set","get","get","get","get","get"], inputs =
 * [[],["love","high",10],["love","low",20],["love",5],["love",10],["love",15],["love",20],["love",25]]
 * 输出：[null,null,null,"","high","high","low","low"]
 *
 * <p>提示：
 *
 * <p>所有的键/值字符串都是小写的。 所有的键/值字符串长度都在 [1, 100] 范围内。 所有 TimeMap.set 操作中的时间戳 timestamps 都是严格递增的。 1 <=
 * timestamp <= 10^7 TimeMap.set 和 TimeMap.get 函数在每个测试用例中将（组合）调用总计 120000 次。
 *
 * @author qinfengsa
 * @date 2020/11/12 16:42
 */
@Slf4j
public class TimeMap {
    Map<String, List<TimeNode>> valueMap;

    /** Initialize your data structure here. */
    public TimeMap() {
        valueMap = new HashMap<>();
    }

    public void set(String key, String value, int timestamp) {
        TimeNode node = new TimeNode(value, timestamp);
        valueMap.computeIfAbsent(key, k -> new ArrayList<>()).add(node);
    }

    public String get(String key, int timestamp) {
        if (!valueMap.containsKey(key)) {
            return "";
        }
        List<TimeNode> list = valueMap.get(key);
        // 二分法
        int left = 0, right = list.size() - 1;
        if (timestamp < list.get(left).timestamp) {
            return "";
        }
        if (timestamp >= list.get(right).timestamp) {
            return list.get(right).value;
        }

        int index = left;
        while (left < right) {
            int mid = (left + right) >> 1;
            if (list.get(mid).timestamp <= timestamp) {
                left = mid + 1;
                index = mid;
            } else {
                right = mid;
            }
        }

        return list.get(index).value;
    }

    static class TimeNode {
        String value;
        int timestamp;

        public TimeNode(String value, int timestamp) {
            this.value = value;
            this.timestamp = timestamp;
        }
    }

    public static void main(String[] args) {
        /*TimeMap timeMap = new TimeMap();
        timeMap.set("foo", "bar", 1);
        String val1 = timeMap.get("foo", 1);
        log.debug("val1:{}", val1);
        String val2 = timeMap.get("foo", 3);
        log.debug("val2:{}", val2);
        timeMap.set("foo", "bar2", 4);
        String val3 = timeMap.get("foo", 4);
        log.debug("val3:{}", val3);
        String val4 = timeMap.get("foo", 5);
        log.debug("val4:{}", val4);*/
        TimeMap timeMap = new TimeMap();
        timeMap.set("love", "high", 10);
        timeMap.set("love", "low", 20);
        String val1 = timeMap.get("love", 5);
        log.debug("val1:{}", val1);
        String val2 = timeMap.get("love", 10);
        log.debug("val2:{}", val2);
        String val3 = timeMap.get("love", 15);
        log.debug("val3:{}", val3);
        String val4 = timeMap.get("love", 20);
        log.debug("val4:{}", val4);
        String val5 = timeMap.get("love", 25);
        log.debug("val5:{}", val5);
    }
}
