package com.qinfengsa.structure.leetcode;

import static com.qinfengsa.structure.util.LogUtils.logResult;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;

/**
 * 677. 键值映射
 *
 * <p>实现一个 MapSum 类里的两个方法，insert 和 sum。
 *
 * <p>对于方法 insert，你将得到一对（字符串，整数）的键值对。字符串表示键，整数表示值。如果键已经存在，那么原来的键值对将被替代成新的键值对。
 *
 * <p>对于方法 sum，你将得到一个表示前缀的字符串，你需要返回所有以该前缀开头的键的值的总和。
 *
 * <p>示例 1:
 *
 * <p>输入: insert("apple", 3), 输出: Null 输入: sum("ap"), 输出: 3 输入: insert("app", 2), 输出: Null 输入:
 * sum("ap"), 输出: 5
 *
 * @author qinfengsa
 * @date 2020/07/05 08:53
 */
@Slf4j
public class MapSum {
    private static final int SIZE = 26;

    private Map<String, Integer> map;

    // 根节点
    private MapSumNode root;

    /** Initialize your data structure here. */
    public MapSum() {
        root = new MapSumNode();
        map = new HashMap<>();
    }

    /**
     * 如果key相同,需要覆盖
     *
     * @param key
     * @param val
     */
    public void insert(String key, int val) {
        MapSumNode node = root;
        boolean flag = map.containsKey(key);

        for (char c : key.toCharArray()) {
            if (Objects.isNull(node.children[c - 'a'])) {
                node.children[c - 'a'] = new MapSumNode();
            }
            node = node.children[c - 'a'];
            node.key = key;
            if (flag) {
                node.val = val;
            } else {
                node.val += val;
            }
        }
        map.put(key, val);
        node.isEnd = true;
    }

    public int sum(String prefix) {
        MapSumNode node = root;
        for (char c : prefix.toCharArray()) {
            if (Objects.isNull(node.children[c - 'a'])) {
                return 0;
            }
            node = node.children[c - 'a'];
        }
        return node.val;
    }

    /** 节点 */
    static class MapSumNode {
        // 所有的儿子节点
        private MapSumNode[] children;
        // 是不是最后一个节点
        private boolean isEnd;
        // 节点的值
        private String key;

        private int val;

        MapSumNode() {
            val = 0;
            children = new MapSumNode[SIZE];
            isEnd = false;
        }
    }

    public static void main(String[] args) {
        MapSum mapSum = new MapSum();
        mapSum.insert("ap", 3);
        logResult(mapSum.sum("ap"));
        mapSum.insert("ap", 2);
        logResult(mapSum.sum("ap"));
    }
}
