package com.qinfengsa.algorithm.leetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author: qinfengsa
 * @date: 2019/3/30 00:29
 */
public class LeetCode {


    public int canCompleteCircuit(int[] gas, int[] cost) {

        /*for (int i = 0; i < gas.length; i++) {
            int gasNum = 0;
        }*/
        int startIndex = 0;
        int curIndex = 0;
        int gasNum = 0;
        while (startIndex < gas.length) {
            boolean isEnd = false;
            for (int i = 0; i < gas.length; i++) {
                curIndex = i + startIndex;
                if (curIndex >= gas.length) {
                    curIndex = curIndex - gas.length;
                }
                gasNum += gas[curIndex];
                if (gasNum >= cost[curIndex]) {
                    gasNum = gasNum - cost[curIndex];
                } else {
                    gasNum = 0;
                    startIndex++;
                    break;
                }

                if (gasNum >= 0 && i == gas.length - 1) {

                    isEnd = true;
                }
            }
            if (isEnd) {
                break;
            }
            if (startIndex == gas.length) {
                startIndex = -1;
                break;
            }
        }

        return startIndex;

    }


    public List<String> fizzBuzz(int n) {

        List<String> list = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            StringBuilder sb = new StringBuilder();
            if (i % 3 == 0) {
                sb.append("Fizz") ;
            }
            if (i % 5 == 0) {
                sb.append("Buzz");
            }
            if (sb.length() == 0) {
                sb.append(i);
            }
            list.add(sb.toString());
        }

        return list;
    }


    public int getSum(int a, int b) {

        System.out.println(a + ":" + b);

        if((a&b) == 0) {
            return a|b;
        }

        return getSum(a^b,(a&b)<<1);
    }

    public boolean isHappy(int n) {

        Set<Integer> happyNumSet = new HashSet<>();
        while(n > 0) {
            if (n == 1) {
                return true;
            }
            if (happyNumSet.contains(n)) {
                return false;
            } else {
                happyNumSet.add(n);
            }
            n = getHappyNum(n);


        }


        return false;

    }

    private int getHappyNum(int num) {
        int happyNum = 0;
        while (num > 0) {
            int a = num%10;
            num = num/10;

            happyNum += a*a;


        }
        return happyNum;

    }
}
