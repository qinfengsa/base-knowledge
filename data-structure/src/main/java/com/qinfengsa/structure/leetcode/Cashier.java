package com.qinfengsa.structure.leetcode;

import lombok.extern.slf4j.Slf4j;

/**
 * 1357. 每隔 n 个顾客打折
 *
 * <p>超市里正在举行打折活动，每隔 n 个顾客会得到 discount 的折扣。
 *
 * <p>超市里有一些商品，第 i 种商品为 products[i] 且每件单品的价格为 prices[i] 。
 *
 * <p>结账系统会统计顾客的数目，每隔 n 个顾客结账时，该顾客的账单都会打折，折扣为 discount （也就是如果原本账单为 x ，那么实际金额会变成 x - (discount * x) /
 * 100 ），然后系统会重新开始计数。
 *
 * <p>顾客会购买一些商品， product[i] 是顾客购买的第 i 种商品， amount[i] 是对应的购买该种商品的数目。
 *
 * <p>请你实现 Cashier 类：
 *
 * <p>Cashier(int n, int discount, int[] products, int[] prices) 初始化实例对象，参数分别为打折频率 n ，折扣大小 discount
 * ，超市里的商品列表 products 和它们的价格 prices 。 double getBill(int[] product, int[] amount)
 * 返回账单的实际金额（如果有打折，请返回打折后的结果）。返回结果与标准答案误差在 10^-5 以内都视为正确结果。
 *
 * <p>示例 1：
 *
 * <p>输入 ["Cashier","getBill","getBill","getBill","getBill","getBill","getBill","getBill"]
 * [[3,50,[1,2,3,4,5,6,7],[100,200,300,400,300,200,100]],[[1,2],[1,2]],[[3,7],[10,10]],[[1,2,3,4,5,6,7],[1,1,1,1,1,1,1]],[[4],[10]],[[7,3],[10,10]],[[7,5,3,1,6,4,2],[10,10,10,9,9,9,7]],[[2,3,5],[5,3,2]]]
 * 输出 [null,500.0,4000.0,800.0,4000.0,4000.0,7350.0,2500.0] 解释 Cashier cashier = new
 * Cashier(3,50,[1,2,3,4,5,6,7],[100,200,300,400,300,200,100]); cashier.getBill([1,2],[1,2]); // 返回
 * 500.0, 账单金额为 = 1 * 100 + 2 * 200 = 500. cashier.getBill([3,7],[10,10]); // 返回 4000.0
 * cashier.getBill([1,2,3,4,5,6,7],[1,1,1,1,1,1,1]); // 返回 800.0 ，账单原本为 1600.0 ，但由于该顾客是第三位顾客，他将得到
 * 50% 的折扣，所以实际金额为 1600 - 1600 * (50 / 100) = 800 。 cashier.getBill([4],[10]); // 返回 4000.0
 * cashier.getBill([7,3],[10,10]); // 返回 4000.0 cashier.getBill([7,5,3,1,6,4,2],[10,10,10,9,9,9,7]);
 * // 返回 7350.0 ，账单原本为 14700.0 ，但由于系统计数再次达到三，该顾客将得到 50% 的折扣，实际金额为 7350.0 。
 * cashier.getBill([2,3,5],[5,3,2]); // 返回 2500.0
 *
 * <p>提示：
 *
 * <p>1 <= n <= 10^4 0 <= discount <= 100 1 <= products.length <= 200 1 <= products[i] <= 200
 * products 列表中 不会 有重复的元素。 prices.length == products.length 1 <= prices[i] <= 1000 1 <=
 * product.length <= products.length product[i] 在 products 出现过。 amount.length == product.length 1 <=
 * amount[i] <= 1000 最多有 1000 次对 getBill 函数的调用。 返回结果与标准答案误差在 10^-5 以内都视为正确结果。
 *
 * @author qinfengsa
 * @date 2020/09/17 09:53
 */
@Slf4j
public class Cashier {

    private int n;

    private int discount;

    private int idx = 0;

    private int[] productPrices = new int[201];

    public Cashier(int n, int discount, int[] products, int[] prices) {
        this.n = n;
        this.discount = 100 - discount;
        for (int i = 0; i < products.length; i++) {
            productPrices[products[i]] = prices[i];
        }
    }

    public double getBill(int[] product, int[] amount) {
        idx++;
        int sum = 0;
        for (int i = 0; i < product.length; i++) {
            sum += productPrices[product[i]] * amount[i];
        }
        double result = sum;
        if (idx % n == 0) {
            result = result * (double) discount / 100.0;
        }
        return result;
    }

    public static void main(String[] args) {
        Cashier cashier =
                new Cashier(
                        3,
                        50,
                        new int[] {1, 2, 3, 4, 5, 6, 7},
                        new int[] {100, 200, 300, 400, 300, 200, 100});
        double d1 = cashier.getBill(new int[] {1, 2}, new int[] {1, 2});
        // 返回 500.0, 账单金额为 = 1 * 100 + 2 * 200 = 500.
        log.debug("d1:{}", d1);
        double d2 = cashier.getBill(new int[] {3, 7}, new int[] {10, 10});
        // 返回 4000.0
        log.debug("d2:{}", d2);
        double d3 =
                cashier.getBill(new int[] {1, 2, 3, 4, 5, 6, 7}, new int[] {1, 1, 1, 1, 1, 1, 1});
        // 返回 800.0 ，账单原本为 1600.0 ，但由于该顾客是第三位顾客，他将得到 50% 的折扣，所以实际金额为 1600 - 1600 * (50 /
        // 100) = 800 。
        log.debug("d3:{}", d3);
        double d4 = cashier.getBill(new int[] {4}, new int[] {10});
        // 返回 4000.0
        log.debug("d4:{}", d4);
        double d5 = cashier.getBill(new int[] {7, 3}, new int[] {10, 10});
        // 返回 4000.0
        log.debug("d5:{}", d5);
        double d6 =
                cashier.getBill(
                        new int[] {7, 5, 3, 1, 6, 4, 2}, new int[] {10, 10, 10, 9, 9, 9, 7});
        // 返回 7350.0 ，账单原本为 14700.0 ，但由于系统计数再次达到三，该顾客将得到 50% 的折扣，实际金额为 7350.0 。
        log.debug("d6:{}", d6);
        double d7 = cashier.getBill(new int[] {2, 3, 5}, new int[] {5, 3, 2});
        // 返回 2500.0
        log.debug("d7:{}", d7);
    }
}
