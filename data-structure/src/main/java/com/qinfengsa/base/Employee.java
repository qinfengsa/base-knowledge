package com.qinfengsa.base;

import java.util.List;

/**
 * @author: qinfengsa
 * @date: 2020/4/2 19:39
 */
public class Employee {
    // It's the unique id of each node;
    // unique id of this employee
    public int id;
    // the importance value of this employee
    public int importance;
    // the id of direct subordinates
    public List<Integer> subordinates;
}
