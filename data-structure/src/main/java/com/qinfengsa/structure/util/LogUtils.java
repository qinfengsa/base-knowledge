package com.qinfengsa.structure.util;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Array;

/**
 * log
 * @author: qinfengsa
 * @date: 2020/2/19 12:43
 */
@Slf4j
public class LogUtils {


    public static void logResult(Object result) {

        if (logArray(result)) {
            return;
        }
        log.debug("result:{}",result);
    }
    public static boolean logArray(Object result) {
        if (null == result) {
            return false;
        }
        // 反射 获得类型
        boolean b =  result.getClass().isArray();
        if (b) {
            int length = Array.getLength(result);
            for (int i = 0; i < length; i++) {
                log.debug("index:{}:{}",i,Array.get(result, i));
            }
        }
        return b;
    }

    public static boolean isArray(Object obj) {
        if (null == obj) {
            return false;
        }
        // 反射 获得类型
        return obj.getClass().isArray();
    }
}
