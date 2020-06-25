package com.qinfengsa.structure.leetcode;

/**
 * 478. 在圆内随机生成点
 *
 * <p>给定圆的半径和圆心的 x、y 坐标，写一个在圆中产生均匀随机点的函数 randPoint 。
 *
 * <p>说明:
 *
 * <p>输入值和输出值都将是浮点数。 圆的半径和圆心的 x、y 坐标将作为参数传递给类的构造函数。 圆周上的点也认为是在圆中。 randPoint
 * 返回一个包含随机点的x坐标和y坐标的大小为2的数组。 示例 1：
 *
 * <p>输入: ["Solution","randPoint","randPoint","randPoint"] [[1,0,0],[],[],[]] 输出:
 * [null,[-0.72939,-0.65505],[-0.78502,-0.28626],[-0.83119,-0.19803]] 示例 2：
 *
 * <p>输入: ["Solution","randPoint","randPoint","randPoint"] [[10,5,-7.5],[],[],[]] 输出:
 * [null,[11.52438,-8.33273],[2.46992,-16.21705],[11.13430,-12.42337]] 输入语法说明：
 *
 * <p>输入是两个列表：调用成员函数名和调用的参数。Solution 的构造函数有三个参数，圆的半径、圆心的 x 坐标、圆心的 y 坐标。randPoint
 * 没有参数。输入参数是一个列表，即使参数为空，也会输入一个 [] 空列表。
 *
 * @author qinfengsa
 * @date 2020/06/24 19:10
 */
public class Circular {
    private double radius;
    private double x_center;
    private double y_center;

    public Circular(double radius, double x_center, double y_center) {
        this.radius = radius;
        this.x_center = x_center;
        this.y_center = y_center;
    }

    public double[] randPoint() {
        // 随机半径, 半径越长,概率越大
        double randRadius = radius * Math.sqrt(Math.random());
        // 随机角度
        double theta = Math.random() * 2 * Math.PI;

        double x = x_center + Math.cos(theta) * randRadius;
        double y = y_center + Math.sin(theta) * randRadius;
        return new double[] {x, y};
    }
}
