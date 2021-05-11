package com.qinfengsa.structure.array;

import static com.qinfengsa.structure.util.LogUtils.logResult;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

/**
 * 数组
 *
 * @author qinfengsa
 * @date 2021/5/10 12:55
 */
@Slf4j
public class ArrayMainTest {

    static ArrayMain main = new ArrayMain();

    @Test
    public void processTasks() {
        int[][] tasks = {{1, 3, 2}, {2, 5, 3}, {5, 6, 2}};
        logResult(main.processTasks(tasks));
    }
}
