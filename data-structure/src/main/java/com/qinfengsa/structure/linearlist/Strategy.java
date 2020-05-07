package com.qinfengsa.structure.linearlist;

/**
 * 比较策略接口
 * @author: qinfengsa
 * @date: 2019/5/3 15:04
 */
public interface Strategy {


    /**
     * 判断两个数据元素是否相等
     * @param obj1
     * @param obj2
     * @return
     */
    boolean equal(Object obj1, Object obj2);


    /**
     * 比较两个数据元素的大小
     * 如果 obj1 < obj2 返回-1
     * 如果 obj1 = obj2 返回 0
     * 如果 obj1 > obj2 返回 1
     * @param obj1
     * @param obj2
     * @return
     */
    int compare(Object obj1, Object obj2) throws Exception;
}
