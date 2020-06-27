package com.qinfengsa.structure.leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * 519. 随机翻转矩阵
 *
 * <p>题中给出一个 n_rows 行 n_cols 列的二维矩阵，且所有值被初始化为 0。要求编写一个 flip 函数，均匀随机的将矩阵中的 0 变为 1，并返回该值的位置下标
 * [row_id,col_id]；同样编写一个 reset 函数，将所有的值都重新置为 0。尽量最少调用随机函数 Math.random()，并且优化时间和空间复杂度。
 *
 * <p>注意:
 *
 * <p>1 <= n_rows, n_cols <= 10000 0 <= row.id < n_rows 并且 0 <= col.id < n_cols 当矩阵中没有值为 0 时，不可以调用
 * flip 函数 调用 flip 和 reset 函数的次数加起来不会超过 1000 次 示例 1：
 *
 * <p>输入: ["Solution","flip","flip","flip","flip"] [[2,3],[],[],[],[]] 输出:
 * [null,[0,1],[1,2],[1,0],[1,1]] 示例 2：
 *
 * <p>输入: ["Solution","flip","flip","reset","flip"] [[1,2],[],[],[],[]] 输出:
 * [null,[0,0],[0,1],null,[0,0]] 输入语法解释：
 *
 * <p>输入包含两个列表：被调用的子程序和他们的参数。Solution 的构造函数有两个参数，分别为 n_rows 和 n_cols。flip 和 reset
 * 没有参数，参数总会以列表形式给出，哪怕该列表为空
 *
 * @author qinfengsa
 * @date 2020/06/27 10:44
 */
public class RandomizedMatrix {

    private Map<Integer, Integer> map;

    private int rows;

    private int cols;

    private int len;

    Random rand = new Random();

    public RandomizedMatrix(int n_rows, int n_cols) {
        this.rows = n_rows;
        this.cols = n_cols;
        this.len = rows * cols;
        map = new HashMap<>(len);
    }

    public int[] flip() {
        if (len < 0) {
            return null;
        }
        int index = rand.nextInt(len--);
        int x = map.getOrDefault(index, index);
        // 原下标与尾部下标交换
        map.put(index, map.getOrDefault(len, len));
        return new int[] {x / cols, x % cols};
    }

    public void reset() {
        map.clear();
        this.len = rows * cols;
    }
}
