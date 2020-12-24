package com.qinfengsa.structure.leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.Set;

/**
 * 710. 黑名单中的随机数
 *
 * <p>给定一个包含 [0，n ) 中独特的整数的黑名单 B，写一个函数从 [ 0，n ) 中返回一个不在 B 中的随机整数。
 *
 * <p>对它进行优化使其尽量少调用系统方法 Math.random() 。
 *
 * <p>提示:
 *
 * <p>1 <= N <= 1000000000 0 <= B.length < min(100000, N) [0, N) 不包含 N，详细参见 interval notation 。 示例
 * 1:
 *
 * <p>输入: ["Solution","pick","pick","pick"] [[1,[]],[],[],[]] 输出: [null,0,0,0] 示例 2:
 *
 * <p>输入: ["Solution","pick","pick","pick"] [[2,[]],[],[],[]] 输出: [null,1,1,1] 示例 3:
 *
 * <p>输入: ["Solution","pick","pick","pick"] [[3,[1]],[],[],[]] Output: [null,0,0,2] 示例 4:
 *
 * <p>输入: ["Solution","pick","pick","pick"] [[4,[2]],[],[],[]] 输出: [null,1,3,1] 输入语法说明：
 *
 * <p>输入是两个列表：调用成员函数名和调用的参数。Solution的构造函数有两个参数，N 和黑名单 B。pick 没有参数，输入参数是一个列表，即使参数为空，也会输入一个 [] 空列表。
 *
 * @author qinfengsa
 * @date 2020/12/22 13:19
 */
public class BlackRandom {

    int N;

    int bound;

    int[] blacklist;

    Map<Integer, Integer> blackMap;

    Random random;

    // 分析
    //
    // 白名单中数的个数为 N - len(B)，那么可以直接在 [0, N - len(B)) 中随机生成整数。我们把所有小于 N - len(B)
    // 且在黑名单中数一一映射到大于等于 N -len(B) 且出现在白名单中的数
    // 。这样一来，如果随机生成的整数出现在黑名单中，我们就返回它唯一对应的那个出现在白名单中的数即可。
    //
    // 例如当 N = 6，B = [0, 2, 3] 时，我们在 [0, 3) 中随机生成整数，并将 2 映射到 4，3 映射到 5，这样随机生成的整数就是 [0, 1, 4, 5]
    // 中的一个。
    //
    // 算法
    //
    // 我们将黑名单分成两部分，第一部分 X 的数都小于 N - len(B)，需要进行映射；
    //
    // 第二部分 Y 的数都大于等于 N - len(B)，这些数不需要进行映射，因为并不会随机到它们。
    //
    // 我们先用 Y 构造出 W，表示大于等于 N - len(B) 且在白名单中的数，X 和 W 的长度一定是相等的。随后遍历 X 和 W，构造一个映射表（HashMap）M，将 X 和 W
    // 中的数构造一一映射。
    //
    // 在 [0, N - len(B)) 中随机生成整数 a 时，如果 a 出现在 M 中，则将它的映射返回，否则直接返回
    //
    public BlackRandom(int N, int[] blacklist) {
        this.N = N;
        this.blacklist = blacklist;
        this.bound = N - blacklist.length;
        random = new Random();
        blackMap = new HashMap<>();
        Set<Integer> set = new HashSet<>();
        for (int i = bound; i < N; i++) {
            set.add(i);
        }
        for (int black : blacklist) {
            set.remove(black);
        }
        Iterator<Integer> iterator = set.iterator();
        for (int black : blacklist) {
            if (black < bound) {
                blackMap.put(black, iterator.next());
            }
        }
    }

    public int pick() {
        int index = random.nextInt(bound);

        return blackMap.getOrDefault(index, index);
    }
}
