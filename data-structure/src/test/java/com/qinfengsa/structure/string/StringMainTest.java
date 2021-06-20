package com.qinfengsa.structure.string;

import static com.qinfengsa.structure.util.LogUtils.logResult;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

/**
 * String
 *
 * @author qinfengsa
 * @date 2021/05/16 11:16
 */
@Slf4j
public class StringMainTest {

    static StringMain main = new StringMain();

    @Test
    public void minSwaps() {
        String s = "010";
        logResult(main.minSwaps(s));
    }

    @Test
    public void distinctEchoSubstrings() {
        String text = "aaaaaaaaaa";
        logResult(main.distinctEchoSubstrings(text));
    }

    @Test
    public void canReach() {
        String s = "01101110";
        int minJump = 2, maxJump = 3;
        logResult(main.canReach(s, minJump, maxJump));
    }

    @Test
    public void longestPrefix() {
        String s = "level";
        logResult(main.longestPrefix(s));
    }

    @Test
    public void maxValue() {
        String n = "469975787943862651173569913153377";
        int x = 3;
        logResult(main.maxValue(n, x));
    }

    @Test
    public void minFlips() {
        String s = "11100";
        logResult(main.minFlips(s));
    }

    @Test
    public void maximumRemovals() {
        String s = "abcab", p = "abc";
        int[] removable = {0, 1, 2, 3, 4};
        logResult(main.maximumRemovals(s, p, removable));
    }

    @Test
    public void minInteger() {
        String num = "9438957234785635408";
        int k = 23;
        logResult(main.minInteger(num, k));
    }

    @Test
    public void makeStringSorted() {
        String s =
                "mvbuibhaaeylbwvlntycbfegpwsxkxzqrppthmuibecipmuimzbeolrnbxjwkfeuikyadepanwxigievibdrxittluatoiqzlwsczjgtnxqiu";
        logResult(main.makeStringSorted(s));
    }

    @Test
    public void largestOddNumber() {
        String num = "";
        logResult(main.largestOddNumber(num));
    }

    @Test
    public void numberOfRounds() {
        String startTime = "04:54", finishTime = "18:51";
        logResult(main.numberOfRounds(startTime, finishTime));
    }
}
