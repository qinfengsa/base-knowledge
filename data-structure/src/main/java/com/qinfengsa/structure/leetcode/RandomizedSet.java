package com.qinfengsa.structure.leetcode;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

/**
 * 常数时间插入、删除和获取随机元素
 * 设计一个支持在平均 时间复杂度 O(1) 下，执行以下操作的数据结构。
 *
 * insert(val)：当元素 val 不存在时，向集合中插入该项。
 * remove(val)：元素 val 存在时，从集合中移除该项。
 * getRandom：随机返回现有集合中的一项。每个元素应该有相同的概率被返回。
 * 示例 :
 *
 * // 初始化一个空的集合。
 * RandomizedSet randomSet = new RandomizedSet();
 *
 * // 向集合中插入 1 。返回 true 表示 1 被成功地插入。
 * randomSet.insert(1);
 *
 * // 返回 false ，表示集合中不存在 2 。
 * randomSet.remove(2);
 *
 * // 向集合中插入 2 。返回 true 。集合现在包含 [1,2] 。
 * randomSet.insert(2);
 *
 * // getRandom 应随机返回 1 或 2 。
 * randomSet.getRandom();
 *
 * // 从集合中移除 1 ，返回 true 。集合现在包含 [2] 。
 * randomSet.remove(1);
 *
 * // 2 已在集合中，所以返回 false 。
 * randomSet.insert(2);
 *
 * // 由于 2 是集合中唯一的数字，getRandom 总是返回 2 。
 * randomSet.getRandom();
 * @author: qinfengsa
 * @date: 2019/5/29 06:20
 */
@Slf4j
public class RandomizedSet {

    /**
     * 定义常量
     */
    private static final Object PRESENT = new Object();

    /**
     *
     */
    private static final int DEFAULT_SIZE = 16;

    /**
     *
     */
    private Integer[] indexNums;

    /**
     * 用HashMap 存放数据
     */
    private HashMap<Integer,Object> hashMap;

    /**
     * 集合大小
     */
    private int size;




    /**
     * 构造方法
     */
    public RandomizedSet() {

        hashMap = new HashMap<>();
        size = 0;
    }

    /**
     * 添加元素
     * @param val
     * @return
     */
    public boolean insert(int val) {

        boolean b = hashMap.containsKey(val);

        if (b) {
            return false;
        }
        hashMap.put(val,PRESENT);
        size++;
        return true;
    }


    /**
     * 移除元素
     * @param val
     * @return
     */
    public boolean remove(int val) {
        boolean b = hashMap.containsKey(val);
        if (b) {
            hashMap.remove(val);
            size--;
            return true;
        }
        return false;

    }

    /**
     * 获取随机元素
     * @return
     */
    public int getRandom() {
        Random random = new Random();

        int randIndex = random.nextInt(size);

        int index = 0;

        for (Integer key :  hashMap.keySet()) {

            if (index == randIndex) {
                return key;
            }
            index++;
        }
        return 0;
    }
}
