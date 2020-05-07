package com.qinfengsa.algorithm.recursion;

import lombok.extern.slf4j.Slf4j;

/**
 * 求解 n 阶汉诺塔问题。汉诺塔问题是由法国数学家 Edouard Lucas 在 1883 年发 明的。
 * n 阶汉诺塔问题可以描述为：假设有 X、Y、Z 三个塔座，初始时有 n 个大小不一的 盘子按照从小到大的次序放在塔座 X 上。
 * 现在要求将塔座 X 上的所有盘子移动到塔座 Z 上 并保持原来的顺序，
 * 在移动过程中要满足以下要求：在塔座之间一次只能移动一个盘子并且 任何时候大盘子都不能放到小盘子上。
 * 基本项：若只有一个盘子，则不需要使用过渡塔座，直接把它放到目的塔座即可。
 * 递归项：如果多于一个盘子，则需要将塔座 X 上的 1 到 n-1 个盘子使用 Z 作为过渡塔 座放到塔座 Y 上，
 * 然后将第 n 个盘子（后一个盘子）放到塔座 Z，后将塔座 Y 上的 n-1 个盘子使用塔座 X 作为过渡放到塔座 Z。
 * @author: qinfengsa
 * @date: 2019/5/8 08:28
 */
@Slf4j
public class Hanoi {

    public static void main(String[] args) {
        Hanoi a = new Hanoi();
        a.hanio(10,'X','Y','Z');
    }

    /**
     *
     * @param n n 阶汉诺塔
     * @param x
     * @param y
     * @param z
     */
    public void hanio(int n, char x, char y, char z){
        if (n == 1) {
            move(n, x, z);
        } else {
            // X 上的 1 到 n-1 个盘子使用 Z 作为过渡塔 座放到塔座 Y 上
            hanio(n-1,x,z,y);
            move(n,x,z);
            //将塔座 Y 上的 n-1 个盘子使用塔座 X 作为过渡放到塔座 Z。
            hanio(n-1,y,x,z);
        }

    }

    private void move(int n,char x,  char y) {
        log.info("Move " + n + " from " + x + " to " + y);
    }
}
