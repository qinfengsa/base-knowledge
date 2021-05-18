package com.qinfengsa.algorithm.back;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;

/**
 * 回溯算法
 *
 * @author qinfengsa
 * @date 2021/05/18 21:45
 */
@Slf4j
public class BackTrackingMain {

    /**
     * 1307. 口算难题
     *
     * <p>给你一个方程，左边用 words 表示，右边用 result 表示。
     *
     * <p>你需要根据以下规则检查方程是否可解：
     *
     * <p>每个字符都会被解码成一位数字（0 - 9）。 每对不同的字符必须映射到不同的数字。 每个 words[i] 和 result 都会被解码成一个没有前导零的数字。
     * 左侧数字之和（words）等于右侧数字（result）。 如果方程可解，返回 True，否则返回 False。
     *
     * <p>示例 1：
     *
     * <p>输入：words = ["SEND","MORE"], result = "MONEY" 输出：true 解释：映射 'S'-> 9, 'E'->5, 'N'->6,
     * 'D'->7, 'M'->1, 'O'->0, 'R'->8, 'Y'->'2' 所以 "SEND" + "MORE" = "MONEY" , 9567 + 1085 = 10652
     * 示例 2：
     *
     * <p>输入：words = ["SIX","SEVEN","SEVEN"], result = "TWENTY" 输出：true 解释：映射 'S'-> 6, 'I'->5,
     * 'X'->0, 'E'->8, 'V'->7, 'N'->2, 'T'->1, 'W'->'3', 'Y'->4 所以 "SIX" + "SEVEN" + "SEVEN" =
     * "TWENTY" , 650 + 68782 + 68782 = 138214 示例 3：
     *
     * <p>输入：words = ["THIS","IS","TOO"], result = "FUNNY" 输出：true 示例 4：
     *
     * <p>输入：words = ["LEET","CODE"], result = "POINT" 输出：false
     *
     * <p>提示：
     *
     * <p>2 <= words.length <= 5 1 <= words[i].length, results.length <= 7 words[i], result
     * 只含有大写英文字母 表达式中使用的不同字符数最大为 10
     *
     * @param words
     * @param result
     * @return
     */
    public boolean isSolvable(String[] words, String result) {
        notZero = new boolean[26];
        // 字符权重
        charWeightMap = new HashMap<>();
        for (String word : words) {
            handleWord(word, true);
        }
        handleWord(result, false);
        charList = new ArrayList<>(charWeightMap.keySet());
        // 高权重 往前
        charList.sort((a, b) -> Math.abs(charWeightMap.get(b)) - Math.abs(charWeightMap.get(a)));
        N = charList.size();

        visited = new boolean[10];
        // 回溯
        return backSolvable(0, 0);
    }

    private boolean backSolvable(int i, int sum) {
        if (i == N) {
            return sum == 0;
        }
        char c = charList.get(i);
        int j = notZero[c - 'A'] ? 1 : 0;
        for (; j <= 9; j++) {

            if (visited[j]) {
                continue;
            }
            visited[j] = true;

            if (backSolvable(i + 1, sum + j * charWeightMap.get(c))) {
                return true;
            }

            visited[j] = false;
        }

        return false;
    }

    private void handleWord(String word, boolean flag) {
        if (word.length() > 1) {
            notZero[word.charAt(0) - 'A'] = true;
        }

        int num = flag ? 1 : -1;
        for (int i = word.length() - 1; i >= 0; i--) {
            char c = word.charAt(i);
            int weight = charWeightMap.getOrDefault(c, 0);
            weight += num;
            charWeightMap.put(c, weight);
            num *= 10;
        }
    }

    Map<Character, Integer> charWeightMap;

    boolean[] notZero;

    boolean[] visited;

    int N;

    List<Character> charList;
}
