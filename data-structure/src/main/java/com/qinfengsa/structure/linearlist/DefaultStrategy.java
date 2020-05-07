package com.qinfengsa.structure.linearlist;

import java.util.Objects;

/**
 * 默认比较策略
 * @author: qinfengsa
 * @date: 2019/5/3 15:06
 */
public class DefaultStrategy implements Strategy {


    /**
     * 判断两个数据元素是否相等
     * @param obj1
     * @param obj2
     * @return
     */
    @Override
    public boolean equal(Object obj1, Object obj2) {
        if (Objects.equals(obj1,obj2)) {
            return true;
        }
        return false;
    }

    /**
     * 比较两个数据元素的大小
     * 如果 obj1 < obj2 返回-1
     * 如果 obj1 = obj2 返回 0
     * 如果 obj1 > obj2 返回 1
     * @param obj1
     * @param obj2
     * @return
     */
    @Override
    public int compare(Object obj1, Object obj2) throws Exception {
        /*Class<?> clazz1 = obj1.getClass();
        Class<?> clazz2 = obj2.getClass();
        if (clazz1 != clazz2) {
            throw new Exception("Objects ");
        }*/
        if (obj1 instanceof Comparable && obj2 instanceof Comparable) {

            Comparable a = (Comparable) obj1;
            Comparable b = (Comparable) obj2;
            return a.compareTo(b);

        } else {
            throw new Exception("Please implement Comparable ");
        }

    }
}
