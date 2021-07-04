package com.qinfengsa.structure.stack;

import static com.qinfengsa.structure.util.LogUtils.logResult;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

/**
 * 栈
 *
 * @author qinfengsa
 * @date 2021/6/23 9:28
 */
@Slf4j
public class StackMainTest {

    static final StackMain main = new StackMain();

    @Test
    public void minOperationsToFlip() {
        String expression = "(0&0)&(0&0&0)";
        logResult(main.minOperationsToFlip(expression));
    }
}
