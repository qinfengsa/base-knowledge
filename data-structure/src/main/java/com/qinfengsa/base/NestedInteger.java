package com.qinfengsa.base;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author qinfengsa
 * @date 2020/05/28 09:19
 */
public class NestedInteger {

    private Integer value;

    private List<NestedInteger> list = new ArrayList<>();

    /**
     * 空 list
     */
    public NestedInteger(){

    }


    /**
     * 单 Integer
     * @param value
     */
    public NestedInteger(int value) {
        this.value = value;
    }

    /**
     * 是否 单Integer
     * @return 如果是单Integer, 返回true
     */
    public boolean isInteger() {

        return Objects.nonNull(value);
    }


    /**
     *
     * @return 返回单Integer
     */
    public Integer getInteger() {
        return this.value;
    }

    public void setInteger(int value) {
        this.value = value;
    }

    public void add(NestedInteger ni) {
        this.list.add(ni);
    }

    public List<NestedInteger> getList() {
        return this.list;
    }

    @Override
    public String toString() {
        if (Objects.nonNull(value)) {
            return value.toString();
        }
        return list.toString();
    }
}
