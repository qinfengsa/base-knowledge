package com.qinfengsa.structure.linearlist.array;

import com.qinfengsa.exception.OutOfBoundaryException;
import com.qinfengsa.structure.linearlist.DefaultStrategy;
import com.qinfengsa.structure.linearlist.LinearList;
import com.qinfengsa.structure.linearlist.Strategy;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.Objects;

/**
 * 线性表顺序结构，数组实现
 * @author: qinfengsa
 * @date: 2019/5/3 14:49
 */
@Slf4j
public class ListArray implements LinearList {


    /**
     * 默认数组大小
     */
    private static final int LEN = 8;

    /**
     * 线性表大小
     */
    private int size;

    /**
     * 比较策略
     */
    private Strategy strategy;

    /**
     * 数组元素数组
     */
    private Object[] elements;


    /**
     * 构造方法
     */
    public ListArray() {
        this(LEN,new DefaultStrategy());
    }

    /**
     * 构造方法
     * @param len
     */
    public ListArray(int len,Strategy strategy) {
        size = 0;
        this.strategy = strategy;
        this.elements = new Object[len];
    }




    /**
     * 返回线性表的大小，即数组元素的个数
     * @return
     */
    @Override
    public int getSize() {
        return size;
    }

    /**
     * 判断线性表是否为空 如果线性表为空返回 true，否则返回 false
     * @return
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 判断线性表是否包含数据元素 e
     * @param e
     * @return
     */
    @Override
    public boolean contains(Object e)  {

        for (int i = 0; i < size; i++) {
            if (strategy.equal(elements[i],e)) {
                return true;
            }

        }

        return false;
    }

    /**
     * 返回数据元素 e 在线性表中的序号
     * @param e
     * @return
     */
    @Override
    public int indexOf(Object e) {
        for (int i = 0; i < size; i++) {
            if (strategy.equal(elements[i],e)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 将数据元素 e 插入到线性表中 i 号位置。若 i 越界，报错。
     * @param i
     * @param e
     * @throws OutOfBoundaryException
     */
    @Override
    public void insert(int i, Object e) throws OutOfBoundaryException {
        if (i < 0 || i > size ) {
            throw new OutOfBoundaryException("错误:指定的插入序号越界");
        }
        if (size == elements.length) {
            expendSpace();
        }

        for (int j = size; j > i; j--) {
            elements[j] = elements[j-1];
        }
        elements[i] = e;

        size++;


    }

    /**
     * 扩展空间
     */
    private void expendSpace() {

        Object[] newElements = new Object[elements.length*2];
        for (int i = 0; i < elements.length; i++) {
            newElements[i] = elements[i];
        }
        elements = newElements;
    }
    /**
     * 将数据元素 e 插入到元素 obj 之前
     * @param obj
     * @param e
     * @return 成功返回 true，否则 返回 false
     */
    @Override
    public boolean insertBefore(Object obj, Object e) {
        int i = indexOf(obj);
        if (i == -1) {
            return false;
        } else {
            insert(i,e);
            return true;
        }
    }

    /**
     * 将数据元素 e 插入到元素 obj 之后
     * @param obj
     * @param e
     * @return 成功返回 true，否则 返回 false
     */
    @Override
    public boolean insertAfter(Object obj, Object e) {
        int i = indexOf(obj);
        if (i == -1) {
            return false;
        } else {
            insert(i+1,e);
            return true;
        }
    }


    /**
     * 删除线性表中序号为 i 的元素,并返回 删除的元素。若 i 越界，报错。
     * @param i
     * @return
     * @throws OutOfBoundaryException
     */
    @Override
    public Object remove(int i) throws OutOfBoundaryException {

        if (i < 0 || i >= size ) {
            throw new OutOfBoundaryException("错误:指定的序号越界");
        }
        Object removeElement = elements[i];

        for (int j = i; j < size - 1; j++) {
            elements[j] = elements[j+1];
        }
        elements[size-1] = null;
        size--;

        return removeElement;
    }

    /**
     * 删除线性表中第一个与 e 相同的元素
     * @param e
     * @return 成功返回 true，否则 返回 false
     */
    @Override
    public boolean remove(Object e) {
        int i = indexOf(e);
        if (i == -1) {
            return false;
        } else {
            remove(i);
            return true;
        }

    }

    /**
     * 替换线性表中序号为 i 的数据元素为 e，返回原数据元素。若 i 越界，报错。
     * @param i
     * @param e
     * @return
     * @throws OutOfBoundaryException
     */
    @Override
    public Object replace(int i, Object e) throws OutOfBoundaryException {
        if (i < 0 || i >= size ) {
            throw new OutOfBoundaryException("错误:指定的序号越界");
        }
        Object replaceElement = elements[i];
        elements[i] = e;
        return replaceElement;
    }

    /**
     * 返回线性表中序号为 i 的数据元素。若 i 越界，报错。
     * @param i
     * @return
     * @throws OutOfBoundaryException
     */
    @Override
    public Object get(int i) throws OutOfBoundaryException {

        if (i < 0 || i >= size ) {
            throw new OutOfBoundaryException("错误:指定的序号越界");
        }
        return elements[i];
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ListArray{");
        for (int i = 0; i < size; i++) {
            if (i > 0) {
                sb.append(",");
            }
            sb.append(elements[i]);
        }
        sb.append("}");

        log.debug( "ListArray{" +
                "elements=" + Arrays.toString(elements) +
                '}');
        return sb.toString();
    }
}
