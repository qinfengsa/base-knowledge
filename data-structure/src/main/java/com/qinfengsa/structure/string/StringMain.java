package com.qinfengsa.structure.string;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;
import lombok.extern.slf4j.Slf4j;

/**
 * 字符串
 *
 * @author qinfengsa
 * @date 2021/05/15 23:38
 */
@Slf4j
public class StringMain {

    /**
     * 5742. 将句子排序
     *
     * <p>一个 句子 指的是一个序列的单词用单个空格连接起来，且开头和结尾没有任何空格。每个单词都只包含小写或大写英文字母。
     *
     * <p>我们可以给一个句子添加 从 1 开始的单词位置索引 ，并且将句子中所有单词 打乱顺序 。
     *
     * <p>比方说，句子 "This is a sentence" 可以被打乱顺序得到 "sentence4 a3 is2 This1" 或者 "is2 sentence4 This1 a3"
     * 。 给你一个 打乱顺序 的句子 s ，它包含的单词不超过 9 个，请你重新构造并得到原本顺序的句子。
     *
     * <p>示例 1：
     *
     * <p>输入：s = "is2 sentence4 This1 a3" 输出："This is a sentence" 解释：将 s 中的单词按照初始位置排序，得到 "This1 is2
     * a3 sentence4" ，然后删除数字。 示例 2：
     *
     * <p>输入：s = "Myself2 Me1 I4 and3" 输出："Me Myself and I" 解释：将 s 中的单词按照初始位置排序，得到 "Me1 Myself2 and3
     * I4" ，然后删除数字。
     *
     * <p>提示：
     *
     * <p>2 <= s.length <= 200 s 只包含小写和大写英文字母、空格以及从 1 到 9 的数字。 s 中单词数目为 1 到 9 个。 s 中的单词由单个空格分隔。 s
     * 不包含任何前导或者后缀空格。
     *
     * @param s
     * @return
     */
    public String sortSentence(String s) {
        String[] strs = new String[9];

        for (String str : s.split(" ")) {
            int len = str.length();
            int index = str.charAt(len - 1) - '1';
            strs[index] = str.substring(0, len - 1);
        }
        StringBuilder sb = new StringBuilder();
        for (String str : strs) {
            if (Objects.nonNull(str)) {
                sb.append(str).append(" ");
            }
        }

        return sb.deleteCharAt(sb.length() - 1).toString();
    }

    /**
     * 5760. 构成交替字符串需要的最小交换次数
     *
     * <p>给你一个二进制字符串 s ，现需要将其转化为一个 交替字符串 。请你计算并返回转化所需的 最小 字符交换次数，如果无法完成转化，返回 -1 。
     *
     * <p>交替字符串 是指：相邻字符之间不存在相等情况的字符串。例如，字符串 "010" 和 "1010" 属于交替字符串，但 "0100" 不是。
     *
     * <p>任意两个字符都可以进行交换，不必相邻 。
     *
     * <p>示例 1：
     *
     * <p>输入：s = "111000" 输出：1 解释：交换位置 1 和 4："111000" -> "101010" ，字符串变为交替字符串。 示例 2：
     *
     * <p>输入：s = "010" 输出：0 解释：字符串已经是交替字符串了，不需要交换。 示例 3：
     *
     * <p>输入：s = "1110" 输出：-1
     *
     * <p>提示：
     *
     * <p>1 <= s.length <= 1000 s[i] 的值为 '0' 或 '1'
     *
     * @param s
     * @return
     */
    public int minSwaps(String s) {
        int count0 = 0, count1 = 0;
        char[] chars = s.toCharArray();
        int result0 = 0, result1 = 0;
        int len = chars.length;
        for (int i = 0; i < len; i++) {
            char c = chars[i];
            if (c == '0') {
                count0++;
                // 0 1 0 1
                if ((i & 1) == 0) {
                    result1++;
                }
                // 1 0 1 0
                else {
                    result0++;
                }
            } else {
                count1++;
                // 0 1 0 1
                if ((i & 1) == 0) {
                    result0++;
                }
                // 1 0 1 0
                else {
                    result1++;
                }
            }
        }

        log.debug("result0 {}  result1 {}", result0, result1);
        if (count0 == count1) {

            return Math.min(result0, result1) >> 1;

        } else if (count0 == count1 + 1) {
            // 0 开头
            return result0 >> 1;
        } else if (count1 == count0 + 1) {
            // 1 开头
            return result1 >> 1;
        }
        return -1;
    }

    /**
     * 1316. 不同的循环子字符串
     *
     * <p>给你一个字符串 text ，请你返回满足下述条件的 不同 非空子字符串的数目：
     *
     * <p>可以写成某个字符串与其自身相连接的形式（即，可以写为 a + a，其中 a 是某个字符串）。 例如，abcabc 就是 abc 和它自身连接形成的。
     *
     * <p>示例 1：
     *
     * <p>输入：text = "abcabcabc" 输出：3 解释：3 个子字符串分别为 "abcabc"，"bcabca" 和 "cabcab" 。 示例 2：
     *
     * <p>输入：text = "leetcodeleetcode" 输出：2 解释：2 个子字符串为 "ee" 和 "leetcodeleetcode" 。
     *
     * <p>提示：
     *
     * <p>1 <= text.length <= 2000 text 只包含小写英文字母。
     *
     * @param text
     * @return
     */
    public int distinctEchoSubstrings(String text) {
        // 字符串hash
        int len = text.length();
        hashs = new long[len + 1];
        hashLens = new long[len + 1];
        hashLens[0] = 1L;
        for (int i = 0; i < len; i++) {
            int num = text.charAt(i) - 'a';
            hashs[i + 1] = (hashs[i] * LETTER_SIZE + num) % MOD;
            hashLens[i + 1] = (hashLens[i] * LETTER_SIZE) % MOD;
        }
        log.debug("hash:{}", hashs);
        Set<Long> strHashSet = new HashSet<>();
        Set<String> strSet = new HashSet<>();
        int result = 0;
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                // 长度
                int l = j - i;
                // 后面的字符串 短于前面的字符串
                if (j + l > len) {
                    continue;
                }
                long leftHash = getHash(i, j);
                String leftStr = text.substring(i, j);
                if (strHashSet.contains(leftHash) && strSet.contains(leftStr)) {
                    continue;
                }
                long rightHash = getHash(j, j + l);
                if (leftHash != rightHash) {
                    continue;
                }
                log.debug("i:{} j:{} j+l:{}", i, j, j + l);

                String rightStr = text.substring(j, j + l);
                log.debug("left:{} right:{}", leftStr, rightStr);
                if (!Objects.equals(leftStr, rightStr)) {
                    continue;
                }

                result++;
                strHashSet.add(leftHash);
                strSet.add(leftStr);
            }
        }

        return result;
    }

    private long[] hashs, hashLens;

    static final int MOD = 1000000007;

    static final int LETTER_SIZE = 26;

    private long getHash(int left, int right) {
        return ((hashs[right] - hashs[left] * hashLens[right - left]) % MOD + MOD) % MOD;
    }

    /**
     * 5763. 哪种连续子字符串更长
     *
     * <p>给你一个二进制字符串 s 。如果字符串中由 1 组成的 最长 连续子字符串 严格长于 由 0 组成的 最长 连续子字符串，返回 true ；否则，返回 false 。
     *
     * <p>例如，s = "110100010" 中，由 1 组成的最长连续子字符串的长度是 2 ，由 0 组成的最长连续子字符串的长度是 3 。 注意，如果字符串中不存在 0 ，此时认为由
     * 0 组成的最长连续子字符串的长度是 0 。字符串中不存在 1 的情况也适用此规则。
     *
     * <p>示例 1：
     *
     * <p>输入：s = "1101" 输出：true 解释： 由 1 组成的最长连续子字符串的长度是 2："1101" 由 0 组成的最长连续子字符串的长度是 1："1101" 由 1
     * 组成的子字符串更长，故返回 true 。 示例 2：
     *
     * <p>输入：s = "111000" 输出：false 解释： 由 1 组成的最长连续子字符串的长度是 3："111000" 由 0 组成的最长连续子字符串的长度是 3："111000"
     * 由 1 组成的子字符串不比由 0 组成的子字符串长，故返回 false 。 示例 3：
     *
     * <p>输入：s = "110100010" 输出：false 解释： 由 1 组成的最长连续子字符串的长度是 2："110100010" 由 0 组成的最长连续子字符串的长度是
     * 3："110100010" 由 1 组成的子字符串不比由 0 组成的子字符串长，故返回 false 。
     *
     * <p>提示：
     *
     * <p>1 <= s.length <= 100 s[i] 不是 '0' 就是 '1'
     *
     * @param s
     * @return
     */
    public boolean checkZeroOnes(String s) {
        int oneLen = 0, zeroLen = 0;
        int maxOneLen = 0, maxZeroLen = 0;

        for (char c : s.toCharArray()) {
            if (c == '0') {
                oneLen = 0;
                zeroLen++;
            } else {
                zeroLen = 0;
                oneLen++;
            }
            maxOneLen = Math.max(maxOneLen, oneLen);
            maxZeroLen = Math.max(maxZeroLen, zeroLen);
        }

        return maxOneLen > maxZeroLen;
    }

    /**
     * 5765. 跳跃游戏 VII
     *
     * <p>给你一个下标从 0 开始的二进制字符串 s 和两个整数 minJump 和 maxJump 。一开始，你在下标 0 处，且该位置的值一定为 '0'
     * 。当同时满足如下条件时，你可以从下标 i 移动到下标 j 处：
     *
     * <p>i + minJump <= j <= min(i + maxJump, s.length - 1) 且 s[j] == '0'. 如果你可以到达 s 的下标 s.length -
     * 1 处，请你返回 true ，否则返回 false 。
     *
     * <p>示例 1：
     *
     * <p>输入：s = "011010", minJump = 2, maxJump = 3 输出：true 解释： 第一步，从下标 0 移动到下标 3 。 第二步，从下标 3 移动到下标
     * 5 。 示例 2：
     *
     * <p>输入：s = "01101110", minJump = 2, maxJump = 3 输出：false
     *
     * <p>提示：
     *
     * <p>2 <= s.length <= 105 s[i] 要么是 '0' ，要么是 '1' s[0] == '0' 1 <= minJump <= maxJump < s.length
     *
     * @param s
     * @param minJump
     * @param maxJump
     * @return
     */
    public boolean canReach(String s, int minJump, int maxJump) {
        int len = s.length();
        if (s.charAt(len - 1) == '1') {
            return false;
        }
        TreeSet<Integer> zeroSet = new TreeSet<>();
        int last = 0;
        zeroSet.add(0);
        for (int i = 1; i < len; i++) {
            if (s.charAt(i) == '1') {
                continue;
            }
            // 超出 最大跳跃距离
            if (i - last > maxJump) {
                return false;
            }

            int left = i - maxJump, right = i - minJump;

            Set<Integer> set = zeroSet.subSet(left, right + 1);
            if (set.isEmpty()) {
                continue;
            }
            zeroSet.add(i);
            last = i;
        }
        return last == len - 1;
    }

    /**
     * 1392. 最长快乐前缀
     *
     * <p>「快乐前缀」是在原字符串中既是 非空 前缀也是后缀（不包括原字符串自身）的字符串。
     *
     * <p>给你一个字符串 s，请你返回它的 最长快乐前缀。
     *
     * <p>如果不存在满足题意的前缀，则返回一个空字符串。
     *
     * <p>示例 1：
     *
     * <p>输入：s = "level" 输出："l" 解释：不包括 s 自己，一共有 4 个前缀（"l", "le", "lev", "leve"）和 4 个后缀（"l", "el",
     * "vel", "evel"）。最长的既是前缀也是后缀的字符串是 "l" 。 示例 2：
     *
     * <p>输入：s = "ababab" 输出："abab" 解释："abab" 是最长的既是前缀也是后缀的字符串。题目允许前后缀在原字符串中重叠。 示例 3：
     *
     * <p>输入：s = "leetcodeleet" 输出："leet" 示例 4：
     *
     * <p>输入：s = "a" 输出：""
     *
     * <p>提示：
     *
     * <p>1 <= s.length <= 10^5 s 只含有小写英文字母
     *
     * @param s
     * @return
     */
    public String longestPrefix(String s) {
        int len = s.length();
        int[] next = new int[len];
        Arrays.fill(next, -1);
        // 通过next记录 字符串自身的信息
        // 与自身做匹配，匹配自身重复的部分
        for (int i = 1; i < len; i++) {
            int j = next[i - 1]; // 求前面一位匹配到的位置
            while (j >= 0 && s.charAt(i) != s.charAt(j + 1)) {
                j = next[j]; // 递推计算
            }
            if (s.charAt(i) == s.charAt(j + 1)) {
                next[i] = j + 1;
            }
        }
        log.debug("next:{}", next);

        return s.substring(0, next[len - 1] + 1);
    }
}
