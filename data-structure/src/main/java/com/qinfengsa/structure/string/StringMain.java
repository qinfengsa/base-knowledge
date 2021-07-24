package com.qinfengsa.structure.string;

import static com.qinfengsa.structure.util.LogUtils.logResult;

import com.qinfengsa.structure.bit.BIT;
import com.qinfengsa.structure.util.CompUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Queue;
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

    /**
     * 5754. 长度为三且各字符不同的子字符串
     *
     * <p>如果一个字符串不含有任何重复字符，我们称这个字符串为 好 字符串。
     *
     * <p>给你一个字符串 s ，请你返回 s 中长度为 3 的 好子字符串 的数量。
     *
     * <p>注意，如果相同的好子字符串出现多次，每一次都应该被记入答案之中。
     *
     * <p>子字符串 是一个字符串中连续的字符序列。
     *
     * <p>示例 1：
     *
     * <p>输入：s = "xyzzaz" 输出：1 解释：总共有 4 个长度为 3 的子字符串："xyz"，"yzz"，"zza" 和 "zaz" 。 唯一的长度为 3 的好子字符串是
     * "xyz" 。 示例 2：
     *
     * <p>输入：s = "aababcabc" 输出：4 解释：总共有 7 个长度为 3 的子字符串："aab"，"aba"，"bab"，"abc"，"bca"，"cab" 和 "abc"
     * 。 好子字符串包括 "abc"，"bca"，"cab" 和 "abc" 。
     *
     * <p>提示：
     *
     * <p>1 <= s.length <= 100 只包含小写英文字母。
     *
     * @param s
     * @return
     */
    public int countGoodSubstrings(String s) {
        int len = s.length();
        int result = 0;
        Set<String> set = new HashSet<>();

        for (int i = 0; i < len - 2; i++) {
            char c0 = s.charAt(i), c1 = s.charAt(i + 1), c2 = s.charAt(i + 2);
            if (c0 != c1 && c0 != c2 && c1 != c2) {
                result++;
            }
        }

        return result;
    }

    /**
     * 5772. 检查某单词是否等于两单词之和
     *
     * <p>字母的 字母值 取决于字母在字母表中的位置，从 0 开始 计数。即，'a' -> 0、'b' -> 1、'c' -> 2，以此类推。
     *
     * <p>对某个由小写字母组成的字符串 s 而言，其 数值 就等于将 s 中每个字母的 字母值 按顺序 连接 并 转换 成对应整数。
     *
     * <p>例如，s = "acb" ，依次连接每个字母的字母值可以得到 "021" ，转换为整数得到 21 。 给你三个字符串 firstWord、secondWord 和
     * targetWord ，每个字符串都由从 'a' 到 'j' （含 'a' 和 'j' ）的小写英文字母组成。
     *
     * <p>如果 firstWord 和 secondWord 的 数值之和 等于 targetWord 的数值，返回 true ；否则，返回 false 。
     *
     * <p>示例 1：
     *
     * <p>输入：firstWord = "acb", secondWord = "cba", targetWord = "cdb" 输出：true 解释： firstWord 的数值为
     * "acb" -> "021" -> 21 secondWord 的数值为 "cba" -> "210" -> 210 targetWord 的数值为 "cdb" -> "231" ->
     * 231 由于 21 + 210 == 231 ，返回 true 示例 2：
     *
     * <p>输入：firstWord = "aaa", secondWord = "a", targetWord = "aab" 输出：false 解释： firstWord 的数值为
     * "aaa" -> "000" -> 0 secondWord 的数值为 "a" -> "0" -> 0 targetWord 的数值为 "aab" -> "001" -> 1 由于 0
     * + 0 != 1 ，返回 false 示例 3：
     *
     * <p>输入：firstWord = "aaa", secondWord = "a", targetWord = "aaaa" 输出：true 解释： firstWord 的数值为
     * "aaa" -> "000" -> 0 secondWord 的数值为 "a" -> "0" -> 0 targetWord 的数值为 "aaaa" -> "0000" -> 0 由于
     * 0 + 0 == 0 ，返回 true
     *
     * <p>提示：
     *
     * <p>1 <= firstWord.length, secondWord.length, targetWord.length <= 8 firstWord、secondWord 和
     * targetWord 仅由从 'a' 到 'j' （含 'a' 和 'j' ）的小写英文字母组成。
     *
     * @param firstWord
     * @param secondWord
     * @param targetWord
     * @return
     */
    public boolean isSumEqual(String firstWord, String secondWord, String targetWord) {
        int num1 = 0, num2 = 0, num0 = 0;
        for (char c : firstWord.toCharArray()) {
            num1 = num1 * 10 + (c - 'a');
        }
        for (char c : secondWord.toCharArray()) {
            num2 = num2 * 10 + (c - 'a');
        }

        for (char c : targetWord.toCharArray()) {
            num0 = num0 * 10 + (c - 'a');
        }

        return num1 + num2 == num0;
    }

    /**
     * 5773. 插入后的最大值
     *
     * <p>给你一个非常大的整数 n 和一个整数数字 x ，大整数 n 用一个字符串表示。n 中每一位数字和数字 x 都处于闭区间 [1, 9] 中，且 n 可能表示一个 负数 。
     *
     * <p>你打算通过在 n 的十进制表示的任意位置插入 x 来 最大化 n 的 数值 。但 不能 在负号的左边插入 x 。
     *
     * <p>例如，如果 n = 73 且 x = 6 ，那么最佳方案是将 6 插入 7 和 3 之间，使 n = 763 。 如果 n = -55 且 x = 2 ，那么最佳方案是将 2
     * 插在第一个 5 之前，使 n = -255 。 返回插入操作后，用字符串表示的 n 的最大值。
     *
     * <p>示例 1：
     *
     * <p>输入：n = "99", x = 9 输出："999" 解释：不管在哪里插入 9 ，结果都是相同的。 示例 2：
     *
     * <p>输入：n = "-13", x = 2 输出："-123" 解释：向 n 中插入 x 可以得到 -213、-123 或者 -132 ，三者中最大的是 -123 。
     *
     * <p>提示：
     *
     * <p>1 <= n.length <= 105 1 <= x <= 9 n 中每一位的数字都在闭区间 [1, 9] 中。 n 代表一个有效的整数。 当 n 表示负数时，将会以字符 '-'
     * 开始。
     *
     * @param n
     * @param x
     * @return
     */
    public String maxValue(String n, int x) {
        StringBuilder sb = new StringBuilder();
        int len = n.length();
        char c = n.charAt(0);
        // 负数 从后往前找第一个比x大的字符

        if (c == '-') {
            /*int idx = len - 1;
            // 负数 从后往前找第一个比x大的字符
            // 负数 从后往前找第一个比x大的字符
            while (idx > 0 && (n.charAt(idx) - '0') < x) {
                idx--;
            }
            sb.append(n.substring(0, idx)).append(x).append(n.substring(idx));*/
            int idx = 1;
            while (idx < len && (n.charAt(idx) - '0') <= x) {
                idx++;
            }
            sb.append(n.substring(0, idx)).append(x).append(n.substring(idx));

        } else {
            int idx = 0;
            // 正数 找第一个比x小的字符
            while (idx < len && (n.charAt(idx) - '0') >= x) {
                idx++;
            }
            sb.append(n.substring(0, idx)).append(x).append(n.substring(idx));
        }

        return sb.toString();
    }

    /**
     * 5778. 使二进制字符串字符交替的最少反转次数
     *
     * <p>给你一个二进制字符串 s 。你可以按任意顺序执行以下两种操作任意次：
     *
     * <p>类型 1 ：删除 字符串 s 的第一个字符并将它 添加 到字符串结尾。 类型 2 ：选择 字符串 s 中任意一个字符并将该字符 反转 ，也就是如果值为 '0' ，则反转得到 '1'
     * ，反之亦然。 请你返回使 s 变成 交替 字符串的前提下， 类型 2 的 最少 操作次数 。
     *
     * <p>我们称一个字符串是 交替 的，需要满足任意相邻字符都不同。
     *
     * <p>比方说，字符串 "010" 和 "1010" 都是交替的，但是字符串 "0100" 不是。
     *
     * <p>示例 1：
     *
     * <p>输入：s = "111000" 输出：2 解释：执行第一种操作两次，得到 s = "100011" 。 然后对第三个和第六个字符执行第二种操作，得到 s = "101010" 。
     * 示例 2：
     *
     * <p>输入：s = "010" 输出：0 解释：字符串已经是交替的。 示例 3：
     *
     * <p>输入：s = "1110" 输出：1 解释：对第二个字符执行第二种操作，得到 s = "1010" 。
     *
     * <p>提示：
     *
     * <p>1 <= s.length <= 105 s[i] 要么是 '0' ，要么是 '1' 。
     *
     * @param s
     * @return
     */
    public int minFlips(String s) {
        int len = s.length();
        int count = 0;

        char[] target = new char[] {'0', '1'};
        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            count += c == target[i % 2] ? 0 : 1;
        }
        String s1 = s + s;
        char[] chars = s1.toCharArray();
        int result = Math.min(count, len - count);
        for (int i = 0; i < len; i++) {
            count -= chars[i] == target[i % 2] ? 0 : 1;
            count += chars[i + len] == target[(i + len) % 2] ? 0 : 1;
            result = Math.min(result, Math.min(count, len - count));
        }
        return result;
    }

    /**
     * 5784. 重新分配字符使所有字符串都相等
     *
     * <p>给你一个字符串数组 words（下标 从 0 开始 计数）。
     *
     * <p>在一步操作中，需先选出两个 不同 下标 i 和 j，其中 words[i] 是一个非空字符串，接着将 words[i] 中的 任一 字符移动到 words[j] 中的 任一
     * 位置上。
     *
     * <p>如果执行任意步操作可以使 words 中的每个字符串都相等，返回 true ；否则，返回 false 。
     *
     * <p>示例 1：
     *
     * <p>输入：words = ["abc","aabc","bc"] 输出：true 解释：将 words[1] 中的第一个 'a' 移动到 words[2] 的最前面。 使
     * words[1] = "abc" 且 words[2] = "abc" 。 所有字符串都等于 "abc" ，所以返回 true 。 示例 2：
     *
     * <p>输入：words = ["ab","a"] 输出：false 解释：执行操作无法使所有字符串都相等。
     *
     * <p>提示：
     *
     * <p>1 <= words.length <= 100 1 <= words[i].length <= 100 words[i] 由小写英文字母组成
     *
     * @param words
     * @return
     */
    public boolean makeEqual(String[] words) {
        int len = words.length;
        int[] letters = new int[26];
        for (String word : words) {
            for (char c : word.toCharArray()) {
                letters[c - 'a']++;
            }
        }
        for (int i = 0; i < 26; i++) {
            if (letters[i] % len != 0) {
                return false;
            }
        }

        return true;
    }

    /**
     * 5786. 可移除字符的最大数目
     *
     * <p>给你两个字符串 s 和 p ，其中 p 是 s 的一个 子序列 。同时，给你一个元素 互不相同 且下标 从 0 开始 计数的整数数组 removable ，该数组是 s
     * 中下标的一个子集（s 的下标也 从 0 开始 计数）。
     *
     * <p>请你找出一个整数 k（0 <= k <= removable.length），选出 removable 中的 前 k 个下标，然后从 s 中移除这些下标对应的 k 个字符。整数 k
     * 需满足：在执行完上述步骤后， p 仍然是 s 的一个 子序列 。更正式的解释是，对于每个 0 <= i < k ，先标记出位于 s[removable[i]]
     * 的字符，接着移除所有标记过的字符，然后检查 p 是否仍然是 s 的一个子序列。
     *
     * <p>返回你可以找出的 最大 k ，满足在移除字符后 p 仍然是 s 的一个子序列。
     *
     * <p>字符串的一个 子序列 是一个由原字符串生成的新字符串，生成过程中可能会移除原字符串中的一些字符（也可能不移除）但不改变剩余字符之间的相对顺序。
     *
     * <p>示例 1：
     *
     * <p>输入：s = "abcacb", p = "ab", removable = [3,1,0] 输出：2 解释：在移除下标 3 和 1 对应的字符后，"abcacb" 变成
     * "accb" 。 "ab" 是 "accb" 的一个子序列。 如果移除下标 3、1 和 0 对应的字符后，"abcacb" 变成 "ccb" ，那么 "ab" 就不再是 s
     * 的一个子序列。 因此，最大的 k 是 2 。 示例 2：
     *
     * <p>输入：s = "abcbddddd", p = "abcd", removable = [3,2,1,4,5,6] 输出：1 解释：在移除下标 3
     * 对应的字符后，"abcbddddd" 变成 "abcddddd" 。 "abcd" 是 "abcddddd" 的一个子序列。 示例 3：
     *
     * <p>输入：s = "abcab", p = "abc", removable = [0,1,2,3,4] 输出：0 解释：如果移除数组 removable 的第一个下标，"abc"
     * 就不再是 s 的一个子序列。
     *
     * <p>提示：
     *
     * <p>1 <= p.length <= s.length <= 105 0 <= removable.length < s.length 0 <= removable[i] <
     * s.length p 是 s 的一个 子字符串 s 和 p 都由小写英文字母组成 removable 中的元素 互不相同
     *
     * @param s
     * @param p
     * @param removable
     * @return
     */
    public int maximumRemovals(String s, String p, int[] removable) {
        // 二分
        int len = removable.length;
        if (len == 0) {
            return 0;
        }
        this.s = s;
        this.p = p;
        this.removable = removable;
        if (canRemove(len)) {
            return len;
        }
        int left = 0, right = len;
        int result = 0;

        while (left < right) {
            int mid = (left + right) >> 1;
            if (canRemove(mid)) {
                result = mid;
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return result;
    }

    private String s, p;

    private int[] removable;

    private boolean canRemove(int k) {
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < k; i++) {
            set.add(removable[i]);
        }
        int idx = 0;
        for (int i = 0; i < s.length(); i++) {
            if (idx == p.length()) {
                break;
            }
            if (set.contains(i)) {
                continue;
            }
            if (s.charAt(i) == p.charAt(idx)) {
                idx++;
            }
        }

        return idx == p.length();
    }

    /**
     * 1505. 最多 K 次交换相邻数位后得到的最小整数
     *
     * <p>给你一个字符串 num 和一个整数 k 。其中，num 表示一个很大的整数，字符串中的每个字符依次对应整数上的各个 数位 。
     *
     * <p>你可以交换这个整数相邻数位的数字 最多 k 次。
     *
     * <p>请你返回你能得到的最小整数，并以字符串形式返回。
     *
     * <p>示例 1：
     *
     * <p>输入：num = "4321", k = 4 输出："1342" 解释：4321 通过 4 次交换相邻数位得到最小整数的步骤如上图所示。 示例 2：
     *
     * <p>输入：num = "100", k = 1 输出："010" 解释：输出可以包含前导 0 ，但输入保证不会有前导 0 。 示例 3：
     *
     * <p>输入：num = "36789", k = 1000 输出："36789" 解释：不需要做任何交换。 示例 4：
     *
     * <p>输入：num = "22", k = 22 输出："22" 示例 5：
     *
     * <p>输入：num = "9438957234785635408", k = 23 输出："0345989723478563548"
     *
     * <p>提示：
     *
     * <p>1 <= num.length <= 30000 num 只包含 数字 且不含有 前导 0 。 1 <= k <= 10^9
     *
     * @param num
     * @param k
     * @return
     */
    public String minInteger(String num, int k) {

        List<Queue<Integer>> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(new LinkedList<>());
        }
        int len = num.length();
        for (int i = 0; i < len; i++) {
            char c = num.charAt(i);
            list.get(c - '0').offer(i + 1);
        }
        StringBuilder sb = new StringBuilder();
        BIT bit = new BIT(len);
        for (int i = 1; i <= len; i++) {
            // 遍历0 ~ 9
            for (int j = 0; j < 10; j++) {
                Queue<Integer> queue = list.get(j);
                if (queue.isEmpty()) {
                    continue;
                }
                // 寻找 数字的 下一个索引 并计算需要交换的距离
                int idx = queue.peek();
                // idx 后面 有多少个元素 (从前面移动到后面的)
                int behindCount = bit.getSum(idx + 1, len);
                // 距离
                int dist = behindCount + idx - i;
                if (dist <= k) {
                    bit.update(idx, 1);
                    queue.poll();
                    sb.append(j);
                    k -= dist;
                    break;
                }
            }
        }

        return sb.toString();
    }

    /**
     * 1830. 使字符串有序的最少操作次数
     *
     * <p>给你一个字符串 s （下标从 0 开始）。你需要对 s 执行以下操作直到它变为一个有序字符串：
     *
     * <p>找到 最大下标 i ，使得 1 <= i < s.length 且 s[i] < s[i - 1] 。 找到 最大下标 j ，使得 i <= j < s.length
     * 且对于所有在闭区间 [i, j] 之间的 k 都有 s[k] < s[i - 1] 。 交换下标为 i - 1 和 j 处的两个字符。 将下标 i 开始的字符串后缀反转。
     * 请你返回将字符串变成有序的最少操作次数。由于答案可能会很大，请返回它对 109 + 7 取余 的结果。
     *
     * <p>示例 1：
     *
     * <p>输入：s = "cba" 输出：5 解释：模拟过程如下所示： 操作 1：i=2，j=2。交换 s[1] 和 s[2] 得到 s="cab" ，然后反转下标从 2
     * 开始的后缀字符串，得到 s="cab" 。 操作 2：i=1，j=2。交换 s[0] 和 s[2] 得到 s="bac" ，然后反转下标从 1 开始的后缀字符串，得到 s="bca" 。
     * 操作 3：i=2，j=2。交换 s[1] 和 s[2] 得到 s="bac" ，然后反转下标从 2 开始的后缀字符串，得到 s="bac" 。 操作 4：i=1，j=1。交换 s[0]
     * 和 s[1] 得到 s="abc" ，然后反转下标从 1 开始的后缀字符串，得到 s="acb" 。 操作 5：i=2，j=2。交换 s[1] 和 s[2] 得到 s="abc"
     * ，然后反转下标从 2 开始的后缀字符串，得到 s="abc" 。 示例 2：
     *
     * <p>输入：s = "aabaa" 输出：2 解释：模拟过程如下所示： 操作 1：i=3，j=4。交换 s[2] 和 s[4] 得到 s="aaaab" ，然后反转下标从 3
     * 开始的后缀字符串，得到 s="aaaba" 。 操作 2：i=4，j=4。交换 s[3] 和 s[4] 得到 s="aaaab" ，然后反转下标从 4 开始的后缀字符串，得到
     * s="aaaab" 。 示例 3：
     *
     * <p>输入：s = "cdbea" 输出：63 示例 4：
     *
     * <p>输入：s = "leetcodeleetcodeleetcode" 输出：982157772
     *
     * <p>提示：
     *
     * <p>1 <= s.length <= 3000 s 只包含小写英文字母。
     *
     * @param s
     * @return
     */
    public int makeStringSorted(String s) {
        // 等价于求比s小的全排列有多少个
        int len = s.length();
        long[][] compNums = CompUtils.initComp(len, MOD);
        int[] letters = new int[26];
        long result = 0L;
        for (int i = len - 1; i >= 0; i--) {
            int idx = s.charAt(i) - 'a';
            letters[idx]++;
            // 比当前小
            for (int j = idx - 1; j >= 0; j--) {
                if (letters[j] == 0) {
                    continue;
                }
                // 后面的长度
                int rightLen = len - i - 1;
                letters[j]--;
                long num = 1;

                // 全排列
                for (int k = 0; k < 26; k++) {
                    if (letters[k] == 0) {
                        continue;
                    }
                    num = num * compNums[rightLen][letters[k]] % MOD;
                    rightLen -= letters[k];
                }
                letters[j]++;
                result += num;
                result %= MOD;
            }
        }

        return (int) result;
    }

    /**
     * 5788. 字符串中的最大奇数
     *
     * <p>给你一个字符串 num ，表示一个大整数。请你在字符串 num 的所有 非空子字符串 中找出 值最大的奇数 ，并以字符串形式返回。如果不存在奇数，则返回一个空字符串 "" 。
     *
     * <p>子字符串 是字符串中的一个连续的字符序列。
     *
     * <p>示例 1：
     *
     * <p>输入：num = "52" 输出："5" 解释：非空子字符串仅有 "5"、"2" 和 "52" 。"5" 是其中唯一的奇数。 示例 2：
     *
     * <p>输入：num = "4206" 输出："" 解释：在 "4206" 中不存在奇数。 示例 3：
     *
     * <p>输入：num = "35427" 输出："35427" 解释："35427" 本身就是一个奇数。
     *
     * <p>提示：
     *
     * <p>1 <= num.length <= 105 num 仅由数字组成且不含前导零
     *
     * @param num
     * @return
     */
    public String largestOddNumber(String num) {
        int len = num.length();
        int idx = len - 1;
        while (idx >= 0) {
            int a = num.charAt(idx) - '0';
            if ((a & 1) == 1) {
                break;
            }
            idx--;
        }
        return num.substring(0, idx + 1);
    }

    /**
     * 5789. 你完成的完整对局数
     *
     * <p>一款新的在线电子游戏在近期发布，在该电子游戏中，以 刻钟 为周期规划若干时长为 15 分钟 的游戏对局。这意味着，在 HH:00、HH:15、HH:30 和 HH:45
     * ，将会开始一个新的对局，其中 HH 用一个从 00 到 23 的整数表示。游戏中使用 24 小时制的时钟 ，所以一天中最早的时间是 00:00 ，最晚的时间是 23:59 。
     *
     * <p>给你两个字符串 startTime 和 finishTime ，均符合 "HH:MM" 格式，分别表示你 进入 和 退出 游戏的确切时间，请计算在整个游戏会话期间，你完成的
     * 完整对局的对局数 。
     *
     * <p>例如，如果 startTime = "05:20" 且 finishTime = "05:59" ，这意味着你仅仅完成从 05:30 到 05:45 这一个完整对局。而你没有完成从
     * 05:15 到 05:30 的完整对局，因为你是在对局开始后进入的游戏；同时，你也没有完成从 05:45 到 06:00 的完整对局，因为你是在对局结束前退出的游戏。 如果
     * finishTime 早于 startTime ，这表示你玩了个通宵（也就是从 startTime 到午夜，再从午夜到 finishTime）。
     *
     * <p>假设你是从 startTime 进入游戏，并在 finishTime 退出游戏，请计算并返回你完成的 完整对局的对局数 。
     *
     * <p>示例 1：
     *
     * <p>输入：startTime = "12:01", finishTime = "12:44" 输出：1 解释：你完成了从 12:15 到 12:30 的一个完整对局。 你没有完成从
     * 12:00 到 12:15 的完整对局，因为你是在对局开始后的 12:01 进入的游戏。 你没有完成从 12:30 到 12:45 的完整对局，因为你是在对局结束前的 12:44
     * 退出的游戏。 示例 2：
     *
     * <p>输入：startTime = "20:00", finishTime = "06:00" 输出：40 解释：你完成了从 20:00 到 00:00 的 16 个完整的对局，以及从
     * 00:00 到 06:00 的 24 个完整的对局。 16 + 24 = 40 示例 3：
     *
     * <p>输入：startTime = "00:00", finishTime = "23:59" 输出：95 解释：除最后一个小时你只完成了 3 个完整对局外，其余每个小时均完成了 4
     * 场完整对局。
     *
     * <p>提示：
     *
     * <p>startTime 和 finishTime 的格式为 HH:MM 00 <= HH <= 23 00 <= MM <= 59 startTime 和 finishTime 不相等
     *
     * @param startTime
     * @param finishTime
     * @return
     */
    public int numberOfRounds(String startTime, String finishTime) {
        int startHour = Integer.parseInt(startTime.substring(0, 2)),
                startMinute = Integer.parseInt(startTime.substring(3, 5));

        int endHour = Integer.parseInt(finishTime.substring(0, 2)),
                endMinute = Integer.parseInt(finishTime.substring(3, 5));

        log.debug("start: {}:{}  end {}:{}", startHour, startMinute, endHour, endMinute);
        int newStartHour = startHour,
                newStartMin = startMinute,
                newEndHour = endHour,
                newEndMin = endMinute;
        if (newStartMin == 0) {
        } else if (newStartMin <= 15) {
            newStartMin = 15;
        } else if (newStartMin <= 30) {
            newStartMin = 30;
        } else if (newStartMin <= 45) {
            newStartMin = 45;
        } else if (newStartMin < 60) {
            newStartMin = 0;
            newStartHour++;
        }

        if (newEndMin < 15) {
            newEndMin = 0;
        } else if (newEndMin < 30) {
            newEndMin = 15;
        } else if (newEndMin < 45) {
            newEndMin = 30;
        } else if (newEndMin < 60) {
            newEndMin = 45;
        }
        log.debug("start: {}:{}  end {}:{}", newStartHour, newStartMin, newEndHour, newEndMin);
        int result = 0;
        if (startHour > endHour || (startHour == endHour && startMinute >= endMinute)) {

            result += (24 - newStartHour) * 4 - newStartMin / 15;

            result += newEndHour * 4 + newEndMin / 15;
        } else {
            result = (newEndHour - newStartHour) * 4 + (newEndMin - newStartMin) / 15;
        }

        return Math.max(result, 0);
    }

    /**
     * 5781. 删除一个字符串中所有出现的给定子字符串
     *
     * <p>给你两个字符串 s 和 part ，请你对 s 反复执行以下操作直到 所有 子字符串 part 都被删除：
     *
     * <p>找到 s 中 最左边 的子字符串 part ，并将它从 s 中删除。 请你返回从 s 中删除所有 part 子字符串以后得到的剩余字符串。
     *
     * <p>一个 子字符串 是一个字符串中连续的字符序列。
     *
     * <p>示例 1：
     *
     * <p>输入：s = "daabcbaabcbc", part = "abc" 输出："dab" 解释：以下操作按顺序执行： - s = "daabcbaabcbc" ，删除下标从 2
     * 开始的 "abc" ，得到 s = "dabaabcbc" 。 - s = "dabaabcbc" ，删除下标从 4 开始的 "abc" ，得到 s = "dababc" 。 - s =
     * "dababc" ，删除下标从 3 开始的 "abc" ，得到 s = "dab" 。 此时 s 中不再含有子字符串 "abc" 。 示例 2：
     *
     * <p>输入：s = "axxxxyyyyb", part = "xy" 输出："ab" 解释：以下操作按顺序执行： - s = "axxxxyyyyb" ，删除下标从 4 开始的
     * "xy" ，得到 s = "axxxyyyb" 。 - s = "axxxyyyb" ，删除下标从 3 开始的 "xy" ，得到 s = "axxyyb" 。 - s =
     * "axxyyb" ，删除下标从 2 开始的 "xy" ，得到 s = "axyb" 。 - s = "axyb" ，删除下标从 1 开始的 "xy" ，得到 s = "ab" 。 此时
     * s 中不再含有子字符串 "xy" 。
     *
     * <p>提示：
     *
     * <p>1 <= s.length <= 1000 1 <= part.length <= 1000 s 和 part 只包小写英文字母。
     *
     * @param s
     * @param part
     * @return
     */
    public String removeOccurrences(String s, String part) {

        while (s.contains(part)) {
            s = s.replaceAll(part, "");
        }

        return s;
    }

    /**
     * 5799. 最美子字符串的数目
     *
     * <p>如果某个字符串中 最多一个 字母出现 奇数 次，则称其为 最美 字符串。
     *
     * <p>例如，"ccjjc" 和 "abab" 都是最美字符串，但 "ab" 不是。 给你一个字符串 word ，该字符串由前十个小写英文字母组成（'a' 到 'j'）。请你返回 word
     * 中 最美非空子字符串 的数目。如果同样的子字符串在 word 中出现多次，那么应当对 每次出现 分别计数。
     *
     * <p>子字符串 是字符串中的一个连续字符序列。
     *
     * <p>示例 1：
     *
     * <p>输入：word = "aba" 输出：4 解释：4 个最美子字符串如下所示： - "aba" -> "a" - "aba" -> "b" - "aba" -> "a" -
     * "aba" -> "aba" 示例 2：
     *
     * <p>输入：word = "aabb" 输出：9 解释：9 个最美子字符串如下所示： - "aabb" -> "a" - "aabb" -> "aa" - "aabb" -> "aab"
     * - "aabb" -> "aabb" - "aabb" -> "a" - "aabb" -> "abb" - "aabb" -> "b" - "aabb" -> "bb" -
     * "aabb" -> "b" 示例 3：
     *
     * <p>输入：word = "he" 输出：2 解释：2 个最美子字符串如下所示： - "he" -> "h" - "he" -> "e"
     *
     * <p>提示：
     *
     * <p>1 <= word.length <= 105 word 由从 'a' 到 'j' 的小写英文字母组成
     *
     * @param word
     * @return
     */
    public long wonderfulSubstrings(String word) {

        // 0 表示偶数 1 表示奇数
        int maxState = 1 << 10;
        long result = 0L;
        // state 前缀和
        long[] stateSums = new long[maxState];
        stateSums[0] = 1;
        int state = 0;
        for (char c : word.toCharArray()) {
            int num = c - 'a';
            state ^= 1 << num;
            result += stateSums[state];
            for (int i = 1; i < maxState; i <<= 1) {
                result += stateSums[state ^ i];
            }
            stateSums[state]++;
        }

        return result;
    }

    /**
     * 5809. 长度为 3 的不同回文子序列
     *
     * <p>给你一个字符串 s ，返回 s 中 长度为 3 的不同回文子序列 的个数。
     *
     * <p>即便存在多种方法来构建相同的子序列，但相同的子序列只计数一次。
     *
     * <p>回文 是正着读和反着读一样的字符串。
     *
     * <p>子序列 是由原字符串删除其中部分字符（也可以不删除）且不改变剩余字符之间相对顺序形成的一个新字符串。
     *
     * <p>例如，"ace" 是 "abcde" 的一个子序列。
     *
     * <p>示例 1：
     *
     * <p>输入：s = "aabca" 输出：3 解释：长度为 3 的 3 个回文子序列分别是： - "aba" ("aabca" 的子序列) - "aaa" ("aabca" 的子序列)
     * - "aca" ("aabca" 的子序列) 示例 2：
     *
     * <p>输入：s = "adc" 输出：0 解释："adc" 不存在长度为 3 的回文子序列。 示例 3：
     *
     * <p>输入：s = "bbcbaba" 输出：4 解释：长度为 3 的 4 个回文子序列分别是： - "bbb" ("bbcbaba" 的子序列) - "bcb" ("bbcbaba"
     * 的子序列) - "bab" ("bbcbaba" 的子序列) - "aba" ("bbcbaba" 的子序列)
     *
     * <p>提示：
     *
     * <p>3 <= s.length <= 105 s 仅由小写英文字母组成
     *
     * @param s
     * @return
     */
    public int countPalindromicSubsequence(String s) {
        int result = 0;
        int n = s.length();
        // 前缀和
        int[][] counts = new int[n][26];
        int[] startIdx = new int[26], endIdx = new int[26];
        Arrays.fill(startIdx, -1);
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            int letIdx = c - 'a';
            if (startIdx[letIdx] == -1) {
                startIdx[letIdx] = i;
            }
            endIdx[letIdx] = i;
            if (i == 0) {
                counts[i][letIdx]++;
            } else {
                System.arraycopy(counts[i - 1], 0, counts[i], 0, 26);
                counts[i][letIdx]++;
            }
        }
        logResult(counts);
        for (int i = 0; i < 26; i++) {
            // 没有 字母 i
            if (startIdx[i] == -1) {
                continue;
            }
            // 只有一个
            if (startIdx[i] == endIdx[i]) {
                continue;
            }
            // 计算中间的元素
            int start = startIdx[i], end = endIdx[i];

            for (int j = 0; j < 26; j++) {
                int cnt = counts[end - 1][j] - counts[start][j];
                if (cnt > 0) {
                    result++;
                }
            }
        }

        return result;
    }

    /**
     * 5817. 判断字符串是否可分解为值均等的子串
     *
     * <p>一个字符串的所有字符都是一样的，被称作等值字符串。
     *
     * <p>举例，"1111" 和 "33" 就是等值字符串。 相比之下，"123"就不是等值字符串。
     * 规则：给出一个数字字符串s，将字符串分解成一些等值字符串，如果有且仅有一个等值子字符串长度为2，其他的等值子字符串的长度都是3.
     *
     * <p>如果能够按照上面的规则分解字符串s，就返回真，否则返回假。
     *
     * <p>子串就是原字符串中连续的字符序列。
     *
     * <p>示例 1：
     *
     * <p>输入: s = "000111000" 输出: false 解释: s只能被分解长度为3的等值子字符串。 示例 2：
     *
     * <p>输入: s = "00011111222" 输出: true 解释: s 能被分解为 ["000","111","11","222"]. 示例 3：
     *
     * <p>输入: s = "01110002223300" 输出: false 解释: 一个不能被分解的原因是在开头有一个0.
     *
     * <p>提示:
     *
     * <p>1 <= s.length <= 1000 s 仅包含数字。
     *
     * @param s
     * @return
     */
    public boolean isDecomposable(String s) {
        int count = 1, count2 = 0;

        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == s.charAt(i - 1)) {
                count++;
                if (count == 3) {
                    count = 0;
                }
            } else {
                if (count == 1) {
                    return false;
                }
                if (count == 2) {
                    count2++;
                }
                count = 1;
            }
        }
        if (count == 1) {
            return false;
        }
        if (count == 2) {
            count2++;
        }

        return count2 == 1;
    }

    /**
     * 5161. 可以输入的最大单词数
     *
     * <p>键盘出现了一些故障，有些字母键无法正常工作。而键盘上所有其他键都能够正常工作。
     *
     * <p>给你一个由若干单词组成的字符串 text ，单词间由单个空格组成（不含前导和尾随空格）；另有一个字符串 brokenLetters
     * ，由所有已损坏的不同字母键组成，返回你可以使用此键盘完全输入的 text 中单词的数目。
     *
     * <p>示例 1：
     *
     * <p>输入：text = "hello world", brokenLetters = "ad" 输出：1 解释：无法输入 "world" ，因为字母键 'd' 已损坏。 示例 2：
     *
     * <p>输入：text = "leet code", brokenLetters = "lt" 输出：1 解释：无法输入 "leet" ，因为字母键 'l' 和 't' 已损坏。 示例
     * 3：
     *
     * <p>输入：text = "leet code", brokenLetters = "e" 输出：0 解释：无法输入任何单词，因为字母键 'e' 已损坏。
     *
     * <p>提示：
     *
     * <p>1 <= text.length <= 104 0 <= brokenLetters.length <= 26 text 由若干用单个空格分隔的单词组成，且不含任何前导和尾随空格
     * 每个单词仅由小写英文字母组成 brokenLetters 由 互不相同 的小写英文字母组成
     *
     * @param text
     * @param brokenLetters
     * @return
     */
    public int canBeTypedWords(String text, String brokenLetters) {
        int count = 0;
        Set<Character> set = new HashSet<>();
        for (char c : brokenLetters.toCharArray()) {
            set.add(c);
        }
        for (String word : text.split(" ")) {
            if (word.length() == 0) {
                continue;
            }
            boolean flag = true;

            for (char c : word.toCharArray()) {
                if (set.contains(c)) {
                    flag = false;
                    break;
                }
            }

            if (flag) {
                count++;
            }
        }

        return count;
    }

    /**
     * 5804. 检查是否所有字符出现次数相同
     *
     * <p>给你一个字符串 s ，如果 s 是一个 好 字符串，请你返回 true ，否则请返回 false 。
     *
     * <p>如果 s 中出现过的 所有 字符的出现次数 相同 ，那么我们称字符串 s 是 好 字符串。
     *
     * <p>示例 1：
     *
     * <p>输入：s = "abacbc" 输出：true 解释：s 中出现过的字符为 'a'，'b' 和 'c' 。s 中所有字符均出现 2 次。 示例 2：
     *
     * <p>输入：s = "aaabb" 输出：false 解释：s 中出现过的字符为 'a' 和 'b' 。 'a' 出现了 3 次，'b' 出现了 2 次，两者出现次数不同。
     *
     * <p>提示：
     *
     * <p>1 <= s.length <= 1000 s 只包含小写英文字母。
     *
     * @param s
     * @return
     */
    public boolean areOccurrencesEqual(String s) {

        int[] counts = new int[26];
        for (char c : s.toCharArray()) {
            counts[c - 'a']++;
        }
        int num = 0;

        for (int i = 0; i < 26; i++) {
            if (counts[i] == 0) {
                continue;
            }
            if (num == 0) {
                num = counts[i];
            }
            if (num != counts[i]) {
                return false;
            }
        }

        return true;
    }
}
