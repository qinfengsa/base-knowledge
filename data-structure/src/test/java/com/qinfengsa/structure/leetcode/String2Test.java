package com.qinfengsa.structure.leetcode;

import static com.qinfengsa.structure.util.LogUtils.logResult;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

/**
 * 字符串 test
 *
 * @author qinfengsa
 * @date 2021/01/24 10:31
 */
@Slf4j
public class String2Test {

    /**
     * 5661. 替换隐藏数字得到的最晚时间
     *
     * <p>给你一个字符串 time ，格式为 hh:mm（小时：分钟），其中某几位数字被隐藏（用 ? 表示）。
     *
     * <p>有效的时间为 00:00 到 23:59 之间的所有时间，包括 00:00 和 23:59 。
     *
     * <p>替换 time 中隐藏的数字，返回你可以得到的最晚有效时间。
     *
     * <p>示例 1：
     *
     * <p>输入：time = "2?:?0" 输出："23:50" 解释：以数字 '2' 开头的最晚一小时是 23 ，以 '0' 结尾的最晚一分钟是 50 。 示例 2：
     *
     * <p>输入：time = "0?:3?" 输出："09:39" 示例 3：
     *
     * <p>输入：time = "1?:22" 输出："19:22"
     *
     * <p>提示：
     *
     * <p>time 的格式为 hh:mm 题目数据保证你可以由输入的字符串生成有效的时间
     *
     * @param time
     * @return
     */
    public String maximumTime(String time) {
        char[] chars = time.toCharArray();
        switch (chars[0]) {
            case '?':
                {
                    if (chars[1] == '?') {
                        chars[0] = '2';
                        chars[1] = '3';
                    } else if (chars[1] > '3') {
                        chars[0] = '1';
                    } else {
                        chars[0] = '2';
                    }
                }
                break;
            case '0':
            case '1':
                {
                    if (chars[1] == '?') {
                        chars[1] = '9';
                    }
                }
                break;
            case '2':
                {
                    if (chars[1] == '?') {
                        chars[1] = '3';
                    }
                }
                break;
        }

        if (chars[3] == '?') {
            chars[3] = '5';
        }
        if (chars[4] == '?') {
            chars[4] = '9';
        }

        return new String(chars);
    }

    /**
     * 5662. 满足三条件之一需改变的最少字符数
     *
     * <p>给你两个字符串 a 和 b ，二者均由小写字母组成。一步操作中，你可以将 a 或 b 中的 任一字符 改变为 任一小写字母 。
     *
     * <p>操作的最终目标是满足下列三个条件 之一 ：
     *
     * <p>a 中的 每个字母 在字母表中 严格小于 b 中的 每个字母 。
     *
     * <p>b 中的 每个字母 在字母表中 严格小于 a 中的 每个字母 。
     *
     * <p>a 和 b 都 由 同一个 字母组成。 返回达成目标所需的 最少 操作数。
     *
     * <p>示例 1：
     *
     * <p>输入：a = "aba", b = "caa" 输出：2 解释：满足每个条件的最佳方案分别是： 1) 将 b 变为 "ccc"，2 次操作，满足 a 中的每个字母都小于 b
     * 中的每个字母； 2) 将 a 变为 "bbb" 并将 b 变为 "aaa"，3 次操作，满足 b 中的每个字母都小于 a 中的每个字母； 3) 将 a 变为 "aaa" 并将 b 变为
     * "aaa"，2 次操作，满足 a 和 b 由同一个字母组成。 最佳的方案只需要 2 次操作（满足条件 1 或者条件 3）。 示例 2：
     *
     * <p>输入：a = "dabadd", b = "cda" 输出：3 解释：满足条件 1 的最佳方案是将 b 变为 "eee" 。
     *
     * <p>提示：
     *
     * <p>1 <= a.length, b.length <= 105 a 和 b 只由小写字母组成
     *
     * @param a
     * @param b
     * @return
     */
    public int minCharacters(String a, String b) {
        int len1 = a.length(), len2 = b.length();
        int[] letters = new int[26], letters1 = new int[26], letters2 = new int[26];
        int max = 0;
        for (char c : a.toCharArray()) {
            letters1[c - 'a']++;
            letters[c - 'a']++;
            max = Math.max(max, letters[c - 'a']);
        }
        for (char c : b.toCharArray()) {
            letters2[c - 'a']++;
            letters[c - 'a']++;
            max = Math.max(max, letters[c - 'a']);
        }
        // 条件3
        int result = len1 + len2 - max;
        int count1 = 0, count2 = 0;
        for (int i = 0; i < 25; i++) {
            count1 += letters1[i];
            count2 += letters2[i];
            // 条件1 a 中的 每个字母 在字母表中 严格小于 b 中的 每个字母
            result = Math.min(result, len1 - count1 + count2);
            // 条件2 b 中的 每个字母 在字母表中 严格小于 a 中的 每个字母
            result = Math.min(result, len2 - count2 + count1);
        }

        return result;
    }

    @Test
    public void minCharacters() {
        String a = "dabadd", b = "cda";
        logResult(minCharacters(a, b));
    }
}
