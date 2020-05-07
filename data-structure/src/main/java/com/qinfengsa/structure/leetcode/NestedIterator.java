package com.qinfengsa.structure.leetcode;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 给你一个嵌套的整型列表。请你设计一个迭代器，使其能够遍历这个整型列表中的所有整数。
 *
 * 列表中的每一项或者为一个整数，或者是另一个列表。
 *
 *
 *
 * 示例 1:
 *
 * 输入: [[1,1],2,[1,1]]
 * 输出: [1,1,2,1,1]
 * 解释: 通过重复调用 next 直到 hasNext 返回 false，next 返回的元素的顺序应该是: [1,1,2,1,1]。
 * 示例 2:
 *
 * 输入: [1,[4,[6]]]
 * 输出: [1,4,6]
 * 解释: 通过重复调用 next 直到 hasNext 返回 false，next 返回的元素的顺序应该是: [1,4,6]。
 * @author: qinfengsa
 * @date: 2020/2/16 17:42
 */
@Slf4j
public class NestedIterator implements Iterator<Integer> {

    public static void main(String[] args) {
        List<NestedInteger> nestedList = new ArrayList<>();
        nestedList.add(new NestedIntegerImpl(1));
        List<NestedInteger> list2 = new ArrayList<>();
        nestedList.add(new NestedIntegerImpl(list2));
        list2.add(new NestedIntegerImpl(4));
        List<NestedInteger> list3 = new ArrayList<>();
        list2.add(new NestedIntegerImpl(list3));
        list3.add(new NestedIntegerImpl(6));

        NestedIterator iterator = new NestedIterator(nestedList);

        while (iterator.hasNext()) {
            log.debug("n:{}",iterator.next());
        }

    }

    Iterator<Integer> iterator ;
    List<Integer> list;


    public NestedIterator(List<NestedInteger> nestedList) {
        this.list = new ArrayList<>();
        getIterator(nestedList);
        iterator = list.iterator();
    }

    private void getIterator(List<NestedInteger> nestedList) {
        for (NestedInteger nest: nestedList) {
            if (nest.isInteger()) {
                list.add(nest.getInteger());
            } else {
                getIterator(nest.getList());
            }
        }
    }

    @Override
    public Integer next() {
        return iterator.next();
    }

    @Override
    public boolean hasNext() {
        return iterator.hasNext();
    }

    interface NestedInteger {

        // @return true if this NestedInteger holds a single integer, rather than a nested list.
        boolean isInteger();
        // @return the single integer that this NestedInteger holds, if it holds a single integer
        // Return null if this NestedInteger holds a nested list
        Integer getInteger();

        // @return the nested list that this NestedInteger holds, if it holds a nested list
        // Return null if this NestedInteger holds a single integer
        List<NestedInteger> getList();
    }

    static class  NestedIntegerImpl implements NestedInteger {

        private boolean number;

        private int val;

        private List<NestedInteger> list;

        NestedIntegerImpl(int val) {
            number = true;
            this.val = val;
        }
        NestedIntegerImpl(List<NestedInteger> list) {
            number = false;
            this.list = list;
        }

        @Override
        public boolean isInteger() {
            return number;
        }
        @Override
        public Integer getInteger() {
            return number ? val : null;
        }

        @Override
        public List<NestedInteger> getList() {
            return number ? null : list;

        }
    }
}
