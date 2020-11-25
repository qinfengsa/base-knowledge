package com.qinfengsa.structure.leetcode;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

/**
 * @author: qinfengsa
 * @date: 2019/5/15 23:18
 */
@Slf4j
public class KthLargestTest {

    @Test
    public void KthLargest() {
        // int k = 3;
        // int[] arr = {4,5,8,2};
        int k = 3;
        int[] arr = {5, -1};
        KthLargest kthLargest = new KthLargest(k, arr);
        int index = 1;
        log.debug("index:{},{}", index++, kthLargest.add(2)); // returns 4
        log.debug("index:{},{}", index++, kthLargest.add(1)); // returns 5
        log.debug("index:{},{}", index++, kthLargest.add(-1)); // returns 5
        log.debug("index:{},{}", index++, kthLargest.add(3)); // returns 8
        log.debug("index:{},{}", index++, kthLargest.add(4)); // returns 8

        // [null,-1,1,1,2,3]

    }
}
