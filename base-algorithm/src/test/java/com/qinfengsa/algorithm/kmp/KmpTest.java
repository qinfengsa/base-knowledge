package com.qinfengsa.algorithm.kmp;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * KMP字符串匹配算法
 * @author: qinfengsa
 * @date: 2019/8/10 18:11
 */
@Slf4j
public class KmpTest {


    @Test
    public void  test() {
        String s = "abaabaabbabaaabaabbabaab";
        String target = "abaabbabaab";
        int result = getIndex(s,target);
        log.debug("result:{}",result);
        log.debug("result2:{}",s.indexOf(target));
    }


    /**
     * 求出一个字符数组的next数组
     * @param target 字符数组
     * @return next数组
     */
    private int[] getNextArray(char[] target) {
        int[] next = new int[target.length];
        next[0] = -1;
        // 通过next记录target字符串自身的信息
        // 与自身做匹配，匹配自身重复的部分
        for (int i = 1; i < target.length; i++) {
            int j = next[i - 1]; // 求前面一位匹配
            while (j >= 0 && target[i - 1] != target[j]) {
                j = next[j]; //递推计算
            }
            if (j >= 0 && target[i - 1] == target[j]) {
                next[i] = j + 1;
            } else {
                next[i] = 0;
            }
        }
        return next;
    }


    private int getIndex(String s,String target) {
        char[] sArr = s.toCharArray();
        char[] tArr = target.toCharArray();
        int[] next = getNextArray(tArr);
        int i = 0, j = 0;
        while (i < sArr.length && j < tArr.length){
            if (j == -1 || sArr[i] == tArr[j]){
                i++;
                j++;
            } else {
                j = next[j];
            }
        }
        if(j == tArr.length) {
            return i - j;
        }
        return -1;
    }


}
