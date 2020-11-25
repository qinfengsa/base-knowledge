package com.qinfengsa.algorithm.sort2;

import com.qinfengsa.algorithm.enumtype.SortTypeEnum;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

/**
 * @author: qinfengsa
 * @date: 2019/8/7 19:14
 */
@Slf4j
public class JavaSortTest {
    @Test
    public void sortTest() {

        // int[] sortArray = {422, 235, 338, 555, 137, 777, 358, 490, 742, 356, 840, 162, 902, 326,
        // 224, 581, 459, 703, 406, 548, 442, 784, 150, 2, 801, 568, 75, 118, 87, 827, 18, 750, 600,
        // 336, 879, 287, 537, 482, 219, 723, 203, 386, 483, 242, 56, 804, 88, 987, 709, 723, 677,
        // 872, 392, 201, 948, 305, 338, 744, 720, 220, 7, 743, 301, 997, 761, 31, 621, 27, 577,
        // 252, 988, 239, 996, 208, 673, 149, 46, 648, 491, 539, 710, 27, 586, 696, 531, 143, 22,
        // 423, 152, 493, 957, 358, 369, 81, 573, 692, 8, 1, 308, 328};
        int[] sortArray = {82, 26, 79, 64, 37, 72, 39, 93, 25, 38};
        ISort javaSort = JavaSortFactory.getSortMethod(SortTypeEnum.HEAP_SORT);
        Long startTime = System.currentTimeMillis();
        sortArray = javaSort.sort(sortArray);
        Long endTime = System.currentTimeMillis();
        Long time = endTime - startTime;
        log.debug("排序时间：{}", time);
        log.debug("sortArray:{}", sortArray);
    }

    private int minRunLength(int n) {

        int r = 0; // 只要不是 2的幂就会置 1
        while (n >= 32) {
            r |= (n & 1);
            n >>= 1;
        }
        return n + r;
    }

    @Test
    public void test1() {
        int n = 65;
        int result = minRunLength(n);
        log.debug("result:{}", result);
    }
}
