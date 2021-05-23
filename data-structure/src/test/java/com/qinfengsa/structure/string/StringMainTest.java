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
}
