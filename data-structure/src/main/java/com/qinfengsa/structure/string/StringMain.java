package com.qinfengsa.structure.string;

import java.util.Objects;
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
}
