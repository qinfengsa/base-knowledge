package com.qinfengsa.structure.leetcode;

import static com.qinfengsa.structure.util.LogUtils.logResult;

import java.util.HashMap;
import java.util.Map;
import javafx.util.Pair;
/**
 * 1396. 设计地铁系统
 *
 * <p>请你实现一个类 UndergroundSystem ，它支持以下 3 种方法：
 *
 * <p>1. checkIn(int id, string stationName, int t)
 *
 * <p>编号为 id 的乘客在 t 时刻进入地铁站 stationName 。 一个乘客在同一时间只能在一个地铁站进入或者离开。 2. checkOut(int id, string
 * stationName, int t)
 *
 * <p>编号为 id 的乘客在 t 时刻离开地铁站 stationName 。 3. getAverageTime(string startStation, string endStation)
 *
 * <p>返回从地铁站 startStation 到地铁站 endStation 的平均花费时间。 平均时间计算的行程包括当前为止所有从 startStation 直接到达 endStation
 * 的行程。 调用 getAverageTime 时，询问的路线至少包含一趟行程。 你可以假设所有对 checkIn 和 checkOut 的调用都是符合逻辑的。也就是说，如果一个顾客在 t1
 * 时刻到达某个地铁站，那么他离开的时间 t2 一定满足 t2 > t1 。所有的事件都按时间顺序给出。
 *
 * <p>示例：
 *
 * <p>输入：
 * ["UndergroundSystem","checkIn","checkIn","checkIn","checkOut","checkOut","checkOut","getAverageTime","getAverageTime","checkIn","getAverageTime","checkOut","getAverageTime"]
 * [[],[45,"Leyton",3],[32,"Paradise",8],[27,"Leyton",10],[45,"Waterloo",15],[27,"Waterloo",20],[32,"Cambridge",22],["Paradise","Cambridge"],["Leyton","Waterloo"],[10,"Leyton",24],["Leyton","Waterloo"],[10,"Waterloo",38],["Leyton","Waterloo"]]
 *
 * <p>输出： [null,null,null,null,null,null,null,14.0,11.0,null,11.0,null,12.0]
 *
 * <p>解释： UndergroundSystem undergroundSystem = new UndergroundSystem();
 * undergroundSystem.checkIn(45, "Leyton", 3); undergroundSystem.checkIn(32, "Paradise", 8);
 * undergroundSystem.checkIn(27, "Leyton", 10); undergroundSystem.checkOut(45, "Waterloo", 15);
 * undergroundSystem.checkOut(27, "Waterloo", 20); undergroundSystem.checkOut(32, "Cambridge", 22);
 * undergroundSystem.getAverageTime("Paradise", "Cambridge"); // 返回 14.0。从 "Paradise"（时刻 8）到
 * "Cambridge"(时刻 22)的行程只有一趟 undergroundSystem.getAverageTime("Leyton", "Waterloo"); // 返回 11.0。总共有
 * 2 躺从 "Leyton" 到 "Waterloo" 的行程，编号为 id=45 的乘客出发于 time=3 到达于 time=15，编号为 id=27 的乘客于 time=10 出发于
 * time=20 到达。所以平均时间为 ( (15-3) + (20-10) ) / 2 = 11.0 undergroundSystem.checkIn(10, "Leyton", 24);
 * undergroundSystem.getAverageTime("Leyton", "Waterloo"); // 返回 11.0 undergroundSystem.checkOut(10,
 * "Waterloo", 38); undergroundSystem.getAverageTime("Leyton", "Waterloo"); // 返回 12.0
 *
 * <p>提示：
 *
 * <p>总共最多有 20000 次操作。 1 <= id, t <= 10^6 所有的字符串包含大写字母，小写字母和数字。 1 <= stationName.length <= 10
 * 与标准答案误差在 10^-5 以内的结果都视为正确结果。
 *
 * @author qinfengsa
 * @date 2020/07/28 10:54
 */
public class UndergroundSystem {

    private Map<Integer, Pair<String, Integer>> startMap = new HashMap<>();

    // start -> end 总时间
    private Map<String, Map<String, Integer>> times = new HashMap<>();

    // start -> end 总次数
    private Map<String, Map<String, Integer>> counts = new HashMap<>();

    public UndergroundSystem() {}

    public void checkIn(int id, String stationName, int t) {
        startMap.put(id, new Pair<>(stationName, t));
    }

    public void checkOut(int id, String stationName, int t) {
        Pair<String, Integer> startInfo = startMap.get(id);
        String startStation = startInfo.getKey();
        Integer startTime = startInfo.getValue();
        Map<String, Integer> timeMap = times.computeIfAbsent(startStation, k -> new HashMap<>());
        int sumTime = timeMap.getOrDefault(stationName, 0);
        timeMap.put(stationName, sumTime + t - startTime);

        Map<String, Integer> countMap = counts.computeIfAbsent(startStation, k -> new HashMap<>());
        int count = countMap.getOrDefault(stationName, 0);
        countMap.put(stationName, count + 1);
    }

    public double getAverageTime(String startStation, String endStation) {
        if (!times.containsKey(startStation)) {
            return 0.0;
        }
        Map<String, Integer> timeMap = times.get(startStation);
        if (!timeMap.containsKey(endStation)) {
            return 0.0;
        }
        Map<String, Integer> countMap = counts.get(startStation);
        int sumTime = timeMap.get(endStation);
        int count = countMap.get(endStation);
        return (double) sumTime / (double) count;
    }

    public static void main(String[] args) {
        UndergroundSystem undergroundSystem = new UndergroundSystem();
        undergroundSystem.checkIn(45, "Leyton", 3);
        undergroundSystem.checkIn(32, "Paradise", 8);
        undergroundSystem.checkIn(27, "Leyton", 10);
        undergroundSystem.checkOut(45, "Waterloo", 15);
        undergroundSystem.checkOut(27, "Waterloo", 20);
        undergroundSystem.checkOut(32, "Cambridge", 22);
        double time1 = undergroundSystem.getAverageTime("Paradise", "Cambridge");
        // 返回 14.0。从 "Paradise"（时刻 8）到 "Cambridge"(时刻 22)的行程只有一趟
        logResult(time1);

        double time2 = undergroundSystem.getAverageTime("Leyton", "Waterloo");
        logResult(time2);
        // 返回 11.0。总共有 2 躺从 "Leyton" 到 "Waterloo" 的行程，编号为 id=45 的乘客出发于 time=3 到达于 time=15，编号为 id=27
        // 的乘客于 time=10 出发于 time=20 到达。所以平均时间为 ( (15-3) + (20-10) ) / 2 = 11.0
        undergroundSystem.checkIn(10, "Leyton", 24);
        double time3 = undergroundSystem.getAverageTime("Leyton", "Waterloo");
        logResult(time3);
        // 返回 11.0
        undergroundSystem.checkOut(10, "Waterloo", 38);
        double time4 = undergroundSystem.getAverageTime("Leyton", "Waterloo");
        logResult(time4);
        // 返回 12.0

    }
}
