package com.qinfengsa.structure.leetcode;

import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

/**
 * 1352. 最后 K 个数的乘积
 *
 * <p>请你实现一个「数字乘积类」ProductOfNumbers，要求支持下述两种方法：
 *
 * <p>1. add(int num)
 *
 * <p>将数字 num 添加到当前数字列表的最后面。 2. getProduct(int k)
 *
 * <p>返回当前数字列表中，最后 k 个数字的乘积。 你可以假设当前列表中始终 至少 包含 k 个数字。 题目数据保证：任何时候，任一连续数字序列的乘积都在 32-bit 整数范围内，不会溢出。
 *
 * <p>示例：
 *
 * <p>输入：
 * ["ProductOfNumbers","add","add","add","add","add","getProduct","getProduct","getProduct","add","getProduct"]
 * [[],[3],[0],[2],[5],[4],[2],[3],[4],[8],[2]]
 *
 * <p>输出： [null,null,null,null,null,null,20,40,0,null,32]
 *
 * <p>解释： ProductOfNumbers productOfNumbers = new ProductOfNumbers(); productOfNumbers.add(3); //
 * [3] productOfNumbers.add(0); // [3,0] productOfNumbers.add(2); // [3,0,2]
 * productOfNumbers.add(5); // [3,0,2,5] productOfNumbers.add(4); // [3,0,2,5,4]
 * productOfNumbers.getProduct(2); // 返回 20 。最后 2 个数字的乘积是 5 * 4 = 20 productOfNumbers.getProduct(3);
 * // 返回 40 。最后 3 个数字的乘积是 2 * 5 * 4 = 40 productOfNumbers.getProduct(4); // 返回 0 。最后 4 个数字的乘积是 0 * 2
 * * 5 * 4 = 0 productOfNumbers.add(8); // [3,0,2,5,4,8] productOfNumbers.getProduct(2); // 返回 32
 * 。最后 2 个数字的乘积是 4 * 8 = 32
 *
 * <p>提示：
 *
 * <p>add 和 getProduct 两种操作加起来总共不会超过 40000 次。 0 <= num <= 100 1 <= k <= 40000
 *
 * @author qinfengsa
 * @date 2020/09/12 09:49
 */
@Slf4j
public class ProductOfNumbers {

    private List<Integer> list;

    public ProductOfNumbers() {
        list = new ArrayList<>();
        list.add(1);
    }

    public void add(int num) {
        int index = list.size();

        if (num == 0) {
            list = new ArrayList<>();
            list.add(1);
        } else {
            int product = list.get(index - 1);
            list.add(product * num);
        }
    }

    public int getProduct(int k) {
        if (list.size() <= k) {
            return 0;
        }
        int end = list.size() - 1, start = end - k;

        return list.get(end) / list.get(start);
    }

    public static void main(String[] args) {
        ProductOfNumbers productOfNumbers = new ProductOfNumbers();
        productOfNumbers.add(3); // [3]
        productOfNumbers.add(0); // [3,0]
        productOfNumbers.add(2); // [3,0,2]
        productOfNumbers.add(5); // [3,0,2,5]
        productOfNumbers.add(4); // [3,0,2,5,4]
        int num1 = productOfNumbers.getProduct(2); // 返回 20 。最后 2 个数字的乘积是 5 * 4 = 20
        log.debug("num1 : {}", num1);
        int num2 = productOfNumbers.getProduct(3); // 返回 40 。最后 3 个数字的乘积是 2 * 5 * 4 = 40
        log.debug("num2 : {}", num2);
        int num3 = productOfNumbers.getProduct(4); // 返回  0 。最后 4 个数字的乘积是 0 * 2 * 5 * 4 = 0
        log.debug("num3 : {}", num3);
        productOfNumbers.add(8); // [3,0,2,5,4,8]
        int num4 = productOfNumbers.getProduct(2); // 返回 32 。最后 2 个数字的乘积是 4 * 8 = 32
        log.debug("num4 : {}", num4);

        /* productOfNumbers.add(7);
        int val1 = productOfNumbers.getProduct(1);
        log.debug("val1：{} ", val1);
        int val2 = productOfNumbers.getProduct(1);
        log.debug("val2：{} ", val2);
        productOfNumbers.add(4);
        productOfNumbers.add(5);
        int val5 = productOfNumbers.getProduct(3);
        log.debug("val5：{} ", val5);
        productOfNumbers.add(4);
        int val7 = productOfNumbers.getProduct(4);
        log.debug("val7：{} ", val7);
        productOfNumbers.add(3);
        int val9 = productOfNumbers.getProduct(4);
        log.debug("val9：{} ", val9);
        productOfNumbers.add(8);
        int val11 = productOfNumbers.getProduct(1);
        log.debug("val11：{} ", val11);
        int val12 = productOfNumbers.getProduct(6);
        log.debug("val12：{} ", val12);
        productOfNumbers.add(2);
        int val14 = productOfNumbers.getProduct(3);
        log.debug("val14：{} ", val14);*/
    }
}
