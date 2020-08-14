package com.qinfengsa.structure.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/**
 * 1146. 快照数组
 *
 * <p>实现支持下列接口的「快照数组」- SnapshotArray：
 *
 * <p>SnapshotArray(int length) - 初始化一个与指定长度相等的 类数组 的数据结构。初始时，每个元素都等于 0。 void set(index, val) -
 * 会将指定索引 index 处的元素设置为 val。 int snap() - 获取该数组的快照，并返回快照的编号 snap_id（快照号是调用 snap() 的总次数减去 1）。 int
 * get(index, snap_id) - 根据指定的 snap_id 选择快照，并返回该快照指定索引 index 的值。
 *
 * <p>示例：
 *
 * <p>输入：["SnapshotArray","set","snap","set","get"] [[3],[0,5],[],[0,6],[0,0]]
 * 输出：[null,null,0,null,5] 解释： SnapshotArray snapshotArr = new SnapshotArray(3); // 初始化一个长度为 3 的快照数组
 * snapshotArr.set(0,5); // 令 array[0] = 5 snapshotArr.snap(); // 获取快照，返回 snap_id = 0
 * snapshotArr.set(0,6); snapshotArr.get(0,0); // 获取 snap_id = 0 的快照中 array[0] 的值，返回 5
 *
 * <p>提示：
 *
 * <p>1 <= length <= 50000 题目最多进行50000 次set，snap，和 get的调用 。 0 <= index < length 0 <= snap_id < 我们调用
 * snap() 的总次数 0 <= val <= 10^9
 *
 * @author qinfengsa
 * @date 2020/08/14 11:34
 */
public class SnapshotArray {

    private int version = 0;

    private List<TreeMap<Integer, Integer>> snapshot;

    public SnapshotArray(int length) {
        snapshot = new ArrayList<>(length);
        for (int i = 0; i < length; i++) {
            snapshot.add(new TreeMap<>());
        }
    }

    public void set(int index, int val) {
        TreeMap<Integer, Integer> treeMap = snapshot.get(index);
        treeMap.put(version, val);
    }

    public int snap() {
        return version++;
    }

    public int get(int index, int snap_id) {
        TreeMap<Integer, Integer> tm = snapshot.get(index);
        Integer key = tm.floorKey(snap_id);
        return key == null ? 0 : tm.get(key);
    }
}
