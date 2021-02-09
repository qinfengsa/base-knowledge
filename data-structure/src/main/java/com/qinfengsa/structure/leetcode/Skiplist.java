package com.qinfengsa.structure.leetcode;

import java.util.Objects;
import java.util.Random;
import lombok.extern.slf4j.Slf4j;

/**
 * 1206. 设计跳表
 *
 * <p>不使用任何库函数，设计一个跳表。
 *
 * <p>跳表是在 O(log(n)) 时间内完成增加、删除、搜索操作的数据结构。跳表相比于树堆与红黑树，其功能与性能相当，并且跳表的代码长度相较下更短，其设计思想与链表相似。
 *
 * <p>例如，一个跳表包含 [30, 40, 50, 60, 70, 90]，然后增加 80、45 到跳表中，以下图的方式操作：
 *
 * <p>Artyom Kalinin [CC BY-SA 3.0], via Wikimedia Commons
 *
 * <p>跳表中有很多层，每一层是一个短的链表。在第一层的作用下，增加、删除和搜索操作的时间复杂度不超过 O(n)。跳表的每一个操作的平均时间复杂度是 O(log(n))，空间复杂度是 O(n)。
 *
 * <p>在本题中，你的设计应该要包含这些函数：
 *
 * <p>bool search(int target) : 返回target是否存在于跳表中。 void add(int num): 插入一个元素到跳表。 bool erase(int num):
 * 在跳表中删除一个值，如果 num 不存在，直接返回false. 如果存在多个 num ，删除其中任意一个即可。 了解更多 :
 * https://en.wikipedia.org/wiki/Skip_list
 *
 * <p>注意，跳表中可能存在多个相同的值，你的代码需要处理这种情况。
 *
 * <p>样例:
 *
 * <p>Skiplist skiplist = new Skiplist();
 *
 * <p>skiplist.add(1); skiplist.add(2); skiplist.add(3); skiplist.search(0); // 返回 false
 * skiplist.add(4); skiplist.search(1); // 返回 true skiplist.erase(0); // 返回 false，0 不在跳表中
 * skiplist.erase(1); // 返回 true skiplist.search(1); // 返回 false，1 已被擦除 约束条件:
 *
 * <p>0 <= num, target <= 20000 最多调用 50000 次 search, add, 以及 erase操作。
 *
 * @author qinfengsa
 * @date 2021/02/09 09:25
 */
@Slf4j
public class Skiplist {

    private final SkipNode head;

    static final int MAX_LEVEL = 16;

    private int level;

    private final Random random;

    static class SkipNode {

        int val;

        // 可以重复, 记录次数
        int count;

        SkipNode[] levels;

        public SkipNode(int val) {
            this.val = val;
            this.count = 1;
            this.levels = new SkipNode[MAX_LEVEL];
        }
    }

    /** 先 创建链表 */
    public Skiplist() {
        head = new SkipNode(-1);
        level = 0;
        random = new Random();
    }

    /**
     * 返回target是否存在于跳表中
     *
     * @param target
     * @return
     */
    public boolean search(int target) {
        SkipNode node = head;
        for (int i = level - 1; i >= 0; i--) {
            while (Objects.nonNull(node.levels[i]) && node.levels[i].val < target) {
                node = node.levels[i];
            }
        }
        node = node.levels[0];
        return Objects.nonNull(node) && node.val == target;
    }

    /**
     * 返回target是否存在于跳表中
     *
     * @param num
     */
    public void add(int num) {
        SkipNode node = head;
        SkipNode[] tailLevel = new SkipNode[MAX_LEVEL];
        for (int i = level - 1; i >= 0; i--) {
            while (Objects.nonNull(node.levels[i]) && node.levels[i].val < num) {
                node = node.levels[i];
            }
            tailLevel[i] = node;
        }
        node = node.levels[0];
        if (Objects.nonNull(node) && node.val == num) {
            node.count++;
        } else {
            // 插入
            int newLevel = randomLevel();
            if (newLevel > level) {
                for (int i = level; i < newLevel; i++) {
                    tailLevel[i] = head;
                }
                level = newLevel;
            }
            SkipNode newNode = new SkipNode(num);
            for (int i = 0; i < level; i++) {
                newNode.levels[i] = tailLevel[i].levels[i];
                tailLevel[i].levels[i] = newNode;
            }
        }
    }

    private int randomLevel() {
        int level = 1;
        while (random.nextDouble() < 0.5 && level < MAX_LEVEL) {
            level++;
        }
        return Math.min(level, MAX_LEVEL);
    }

    /**
     * 在跳表中删除一个值，如果 num 不存在，直接返回false. 如果存在多个 num ，删除其中任意一个即可
     *
     * @param num
     * @return
     */
    public boolean erase(int num) {
        SkipNode node = head;
        SkipNode[] tailLevel = new SkipNode[MAX_LEVEL];
        for (int i = level - 1; i >= 0; i--) {
            while (Objects.nonNull(node.levels[i]) && node.levels[i].val < num) {
                node = node.levels[i];
            }
            tailLevel[i] = node;
        }
        node = node.levels[0];
        if (Objects.nonNull(node) && node.val == num) {
            if (node.count > 1) {
                node.count--;
                return true;
            }
            // 删除节点
            for (int i = 0; i < level; i++) {
                if (tailLevel[i].levels[i] != node) {
                    break;
                }
                tailLevel[i].levels[i] = node.levels[i];
            }

            while (level > 0 && Objects.isNull(head.levels[level - 1])) {
                level--;
            }

            return true;
        }
        return false;
    }
}
