package com.qinfengsa.structure.trie;

import static com.qinfengsa.structure.util.LogUtils.logResult;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

/**
 * 字典树
 *
 * @author qinfengsa
 * @date 2021/6/9 11:20
 */
@Slf4j
public class TrieMainTest {

    static final TrieMain main = new TrieMain();

    @Test
    public void countPairs() {
        int[] nums = {9, 8, 4, 2, 1};
        int low = 5, high = 14;
        logResult(main.countPairs(nums, low, high));
    }
}
