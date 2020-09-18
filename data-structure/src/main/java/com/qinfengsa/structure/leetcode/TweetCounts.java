package com.qinfengsa.structure.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;
import lombok.extern.slf4j.Slf4j;

/**
 * 1348. 推文计数
 *
 * <p>请你实现一个能够支持以下两种方法的推文计数类 TweetCounts：
 *
 * <p>1. recordTweet(string tweetName, int time)
 *
 * <p>记录推文发布情况：用户 tweetName 在 time（以 秒 为单位）时刻发布了一条推文。 2. getTweetCountsPerFrequency(string freq,
 * string tweetName, int startTime, int endTime)
 *
 * <p>返回从开始时间 startTime（以 秒 为单位）到结束时间 endTime（以 秒 为单位）内，每 分 minute，时 hour 或者 日 day （取决于 freq）内指定用户
 * tweetName 发布的推文总数。 freq 的值始终为 分 minute，时 hour 或者 日 day 之一，表示获取指定用户 tweetName 发布推文次数的时间间隔。
 * 第一个时间间隔始终从 startTime 开始，因此时间间隔为 [startTime, startTime + delta*1>, [startTime + delta*1, startTime
 * + delta*2>, [startTime + delta*2, startTime + delta*3>, ... , [startTime + delta*i, min(startTime
 * + delta*(i+1), endTime + 1)>，其中 i 和 delta（取决于 freq）都是非负整数。
 *
 * <p>示例：
 *
 * <p>输入：
 * ["TweetCounts","recordTweet","recordTweet","recordTweet","getTweetCountsPerFrequency","getTweetCountsPerFrequency","recordTweet","getTweetCountsPerFrequency"]
 * [[],["tweet3",0],["tweet3",60],["tweet3",10],["minute","tweet3",0,59],["minute","tweet3",0,60],["tweet3",120],["hour","tweet3",0,210]]
 *
 * <p>输出： [null,null,null,null,[2],[2,1],null,[4]]
 *
 * <p>解释： TweetCounts tweetCounts = new TweetCounts(); tweetCounts.recordTweet("tweet3", 0);
 * tweetCounts.recordTweet("tweet3", 60); tweetCounts.recordTweet("tweet3", 10); // "tweet3"
 * 发布推文的时间分别是 0, 10 和 60 。 tweetCounts.getTweetCountsPerFrequency("minute", "tweet3", 0, 59); // 返回
 * [2]。统计频率是每分钟（60 秒），因此只有一个有效时间间隔 [0,60> - > 2 条推文。
 * tweetCounts.getTweetCountsPerFrequency("minute", "tweet3", 0, 60); // 返回 [2,1]。统计频率是每分钟（60
 * 秒），因此有两个有效时间间隔 1) [0,60> - > 2 条推文，和 2) [60,61> - > 1 条推文。 tweetCounts.recordTweet("tweet3",
 * 120); // "tweet3" 发布推文的时间分别是 0, 10, 60 和 120 。 tweetCounts.getTweetCountsPerFrequency("hour",
 * "tweet3", 0, 210); // 返回 [4]。统计频率是每小时（3600 秒），因此只有一个有效时间间隔 [0,211> - > 4 条推文。
 *
 * <p>提示：
 *
 * <p>同时考虑 recordTweet 和 getTweetCountsPerFrequency，最多有 10000 次操作。 0 <= time, startTime, endTime <=
 * 10^9 0 <= endTime - startTime <= 10^4
 *
 * @author qinfengsa
 * @date 2020/09/18 00:19
 */
@Slf4j
public class TweetCounts {

    Map<String, TreeSet<Integer>> tweetMap = new HashMap<>();

    static final Map<String, Integer> freqMap = new HashMap<>();

    static {
        // minute，时 hour 或者 日 day
        freqMap.put("minute", 60);
        freqMap.put("hour", 3600);
        freqMap.put("day", 86400);
    }

    public TweetCounts() {}

    public void recordTweet(String tweetName, int time) {
        TreeSet<Integer> treeSet = tweetMap.computeIfAbsent(tweetName, k -> new TreeSet<>());
        treeSet.add(time);
    }

    public List<Integer> getTweetCountsPerFrequency(
            String freq, String tweetName, int startTime, int endTime) {
        List<Integer> list = new ArrayList<>();
        TreeSet<Integer> treeSet = tweetMap.get(tweetName);
        int freqTime = freqMap.get(freq);
        for (int time = startTime; time <= endTime; time += freqTime) {

            int end = Math.min(time + freqTime, endTime + 1);
            SortedSet<Integer> count = treeSet.subSet(time, end);
            list.add(count.size());
        }

        return list;
    }

    public static void main(String[] args) {

        TweetCounts tweetCounts = new TweetCounts();
        tweetCounts.recordTweet("tweet3", 0);
        tweetCounts.recordTweet("tweet3", 60);
        tweetCounts.recordTweet("tweet3", 10);
        // "tweet3"发布推文的时间分别是0,10和60 。
        List<Integer> list1 = tweetCounts.getTweetCountsPerFrequency("minute", "tweet3", 0, 59);
        log.debug("list1:{}", list1);
        // 返回[2]。统计频率是每分钟（60 秒），因此只有一个有效时间间隔 [0,60>->2条推文。
        List<Integer> list2 = tweetCounts.getTweetCountsPerFrequency("minute", "tweet3", 0, 60);
        log.debug("list2:{}", list2);
        // 返回[2,1]。统计频率是每分钟（60 秒），因此有两个有效时间间隔1)[0,60>->2条推文，和2)[60,61>->1条推文。
        tweetCounts.recordTweet("tweet3", 120);
        // "tweet3"发布推文的时间分别是 0, 10, 60 和 120 。
        List<Integer> list4 = tweetCounts.getTweetCountsPerFrequency("hour", "tweet3", 0, 210);
        // 返回[4]。统计频率是每小时（3600 秒），因此只有一个有效时间间隔 [0,211>->4条推文。
        log.debug("list4:{}", list4);
    }
}
