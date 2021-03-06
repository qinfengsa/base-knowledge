package com.qinfengsa.structure.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

/**
 * 381. O(1) 时间插入、删除和获取随机元素 - 允许重复
 *
 * <p>设计一个支持在平均 时间复杂度 O(1) 下， 执行以下操作的数据结构。
 *
 * <p>注意: 允许出现重复元素。
 *
 * <p>insert(val)：向集合中插入元素 val。 remove(val)：当 val 存在时，从集合中移除一个 val。
 * getRandom：从现有集合中随机获取一个元素。每个元素被返回的概率应该与其在集合中的数量呈线性相关。 示例:
 *
 * <p>// 初始化一个空的集合。 RandomizedCollection collection = new RandomizedCollection();
 *
 * <p>// 向集合中插入 1 。返回 true 表示集合不包含 1 。 collection.insert(1);
 *
 * <p>// 向集合中插入另一个 1 。返回 false 表示集合包含 1 。集合现在包含 [1,1] 。 collection.insert(1);
 *
 * <p>// 向集合中插入 2 ，返回 true 。集合现在包含 [1,1,2] 。 collection.insert(2);
 *
 * <p>// getRandom 应当有 2/3 的概率返回 1 ，1/3 的概率返回 2 。 collection.getRandom();
 *
 * <p>// 从集合中删除 1 ，返回 true 。集合现在包含 [1,2] 。 collection.remove(1);
 *
 * <p>// getRandom 应有相同概率返回 1 和 2 。 collection.getRandom();
 *
 * @author qinfengsa
 * @date 2020/10/31 13:03
 */
public class RandomizedCollection {

    Map<Integer, Set<Integer>> indexMap;

    List<Integer> nums;

    Random random;

    /** Initialize your data structure here. */
    public RandomizedCollection() {
        indexMap = new HashMap<>();
        nums = new ArrayList<>();
        random = new Random();
    }

    /**
     * 向集合中插入一个值。如果集合尚未包含指定的元素，则返回true
     *
     * @param val
     * @return
     */
    public boolean insert(int val) {
        int index = nums.size();
        Set<Integer> set = indexMap.computeIfAbsent(val, k -> new HashSet<>());
        boolean result = set.isEmpty();
        set.add(index);
        nums.add(val);
        return result;
    }

    /**
     * 从集合中删除一个值。如果集合包含指定的元素，则返回true。
     *
     * @param val
     * @return
     */
    public boolean remove(int val) {
        Set<Integer> set = indexMap.computeIfAbsent(val, k -> new HashSet<>());
        if (set.isEmpty()) {
            return false;
        }
        int size = nums.size();
        int index = set.iterator().next();
        int swapVal = nums.remove(size - 1);
        if (index != size - 1) {
            set.remove(index);
            nums.set(index, swapVal);
            Set<Integer> swapSet = indexMap.get(swapVal);
            swapSet.remove(size - 1);
            swapSet.add(index);
        } else {
            set.remove(index);
        }
        return true;
    }

    /** 从集合中获取随机元素。 */
    public int getRandom() {
        int size = nums.size();
        return nums.get(random.nextInt(size));
    }

    public static void main(String[] args) {
        RandomizedCollection collection = new RandomizedCollection();
        // ["RandomizedCollection",
        // "insert","insert","insert","insert","insert","insert",
        // "remove","remove","remove","remove","remove",
        // "insert","remove","remove","getRandom","getRandom","getRandom","getRandom","getRandom","getRandom","getRandom","getRandom","getRandom","getRandom"]
        // [[],[10],[10],[20],[20],[30],[30],
        // [10],[20],[20],[10],[30],
        // [40],[30],[30],[],[],[],[],[],[],[],[],[],[]]
        collection.insert(10);
        collection.insert(10);
        collection.insert(20);
        collection.insert(20);
        collection.insert(30);
        collection.insert(30);

        collection.remove(10);
        collection.remove(20);
        collection.remove(20);
        collection.remove(10);
        collection.remove(30);

        collection.insert(40);

        collection.remove(30);
        collection.remove(30);
        /*collection.insert(4);
        collection.insert(3);
        collection.insert(4);
        collection.insert(2);
        collection.insert(4);

        collection.remove(4);
        collection.remove(3);
        collection.remove(4);
        collection.remove(4);*/
    }
}
