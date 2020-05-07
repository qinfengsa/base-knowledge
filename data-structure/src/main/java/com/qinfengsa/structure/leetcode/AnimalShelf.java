package com.qinfengsa.structure.leetcode;


import java.util.LinkedList;
import java.util.Queue;

/**
 * 面试题 03.06. 动物收容所
 * 动物收容所。有家动物收容所只收容狗与猫，且严格遵守“先进先出”的原则。在收养该收容所的动物时，收养人只能收养所有动物中“最老”（由其进入收容所的时间长短而定）的动物，或者可以挑选猫或狗（同时必须收养此类动物中“最老”的）。换言之，收养人不能自由挑选想收养的对象。请创建适用于这个系统的数据结构，实现各种操作方法，比如enqueue、dequeueAny、dequeueDog和dequeueCat。允许使用Java内置的LinkedList数据结构。
 *
 * enqueue方法有一个animal参数，animal[0]代表动物编号，animal[1]代表动物种类，其中 0 代表猫，1 代表狗。
 *
 * dequeue*方法返回一个列表[动物编号, 动物种类]，若没有可以收养的动物，则返回[-1,-1]。
 *
 * 示例1:
 *
 *  输入：
 * ["AnimalShelf", "enqueue", "enqueue", "dequeueCat", "dequeueDog", "dequeueAny"]
 * [[], [[0, 0]], [[1, 0]], [], [], []]
 *  输出：
 * [null,null,null,[0,0],[-1,-1],[1,0]]
 * 示例2:
 *
 *  输入：
 * ["AnimalShelf", "enqueue", "enqueue", "enqueue", "dequeueDog", "dequeueCat", "dequeueAny"]
 * [[], [[0, 0]], [[1, 0]], [[2, 1]], [], [], []]
 *  输出：
 * [null,null,null,null,[2,1],[0,0],[1,0]]
 * 说明:
 *
 * 收纳所的最大容量为20000
 * @author: qinfengsa
 * @create: 2020/04/19 08:19
 */
public class AnimalShelf {



    private class AnimalNode {
        int[] animal;
        int index;

        public AnimalNode(int[] animal, int index) {
            this.animal = animal;
            this.index = index;
        }
    }

    private int index = 0;

    private Queue<AnimalNode> catQueue;

    private Queue<AnimalNode> dogQueue;

    public AnimalShelf() {
        catQueue = new LinkedList<>();
        dogQueue = new LinkedList<>();
    }

    /**
     * 收容动物
     * animal[0]代表动物编号，animal[1]代表动物种类，其中 0 代表猫，1 代表狗
     * @param animal
     */
    public void enqueue(int[] animal) {
        AnimalNode node = new AnimalNode(animal,index++);
        int type = animal[1];
        if (type == 0) {
            catQueue.offer(node);
        } else {
            dogQueue.offer(node);
        }
    }

    public int[] dequeueAny() {
        if (dogQueue.isEmpty() && catQueue.isEmpty()) {
            return new int[]{-1,-1};
        }
        AnimalNode dog = dogQueue.peek();
        AnimalNode cat = catQueue.peek();
        if (dogQueue.isEmpty() ) {
            catQueue.poll();
            return cat.animal;
        }
        if (catQueue.isEmpty()  ) {
            dogQueue.poll();
            return dog.animal;
        }

        if ( cat.index < dog.index) {
            catQueue.poll();
            return cat.animal;
        }
        dogQueue.poll();
        return dog.animal;
    }

    public int[] dequeueDog() {

        if (dogQueue.isEmpty()) {
            return new int[]{-1,-1};
        }
        AnimalNode node = dogQueue.poll();
        return node.animal;
    }

    public int[] dequeueCat() {

        if (catQueue.isEmpty()) {
            return new int[]{-1,-1};
        }
        AnimalNode node = catQueue.poll();
        return node.animal;
    }

    public static void main(String[] args) {

    }
}
