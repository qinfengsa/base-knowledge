package com.qinfengsa.algorithm.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

/**
 * 843. 猜猜这个单词
 *
 * @author qinfengsa
 * @date 2021/05/22 07:14
 */
public class FindSecretWord {
    static final ThreadLocalRandom RANDOM = ThreadLocalRandom.current();
    /**
     * 843. 猜猜这个单词
     *
     * <p>这个问题是 LeetCode 平台新增的交互式问题 。
     *
     * <p>我们给出了一个由一些独特的单词组成的单词列表，每个单词都是 6 个字母长，并且这个列表中的一个单词将被选作秘密。
     *
     * <p>你可以调用 master.guess(word) 来猜单词。你所猜的单词应当是存在于原列表并且由 6 个小写字母组成的类型字符串。
     *
     * <p>此函数将会返回一个整型数字，表示你的猜测与秘密单词的准确匹配（值和位置同时匹配）的数目。此外，如果你的猜测不在给定的单词列表中，它将返回 -1。
     *
     * <p>对于每个测试用例，你有 10 次机会来猜出这个单词。当所有调用都结束时，如果您对 master.guess 的调用不超过 10 次，并且至少有一次猜到秘密，那么您将通过该测试用例。
     *
     * <p>除了下面示例给出的测试用例外，还会有 5 个额外的测试用例，每个单词列表中将会有 100 个单词。这些测试用例中的每个单词的字母都是从 'a' 到 'z'
     * 中随机选取的，并且保证给定单词列表中的每个单词都是唯一的。
     *
     * <p>示例 1: 输入: secret = "acckzz", wordlist = ["acckzz","ccbazz","eiowzz","abcczz"]
     *
     * <p>解释:
     *
     * <p>master.guess("aaaaaa") 返回 -1, 因为 "aaaaaa" 不在 wordlist 中. master.guess("acckzz") 返回 6, 因为
     * "acckzz" 就是秘密，6个字母完全匹配。 master.guess("ccbazz") 返回 3, 因为 "ccbazz" 有 3 个匹配项。
     * master.guess("eiowzz") 返回 2, 因为 "eiowzz" 有 2 个匹配项。 master.guess("abcczz") 返回 4, 因为 "abcczz" 有
     * 4 个匹配项。
     *
     * <p>我们调用了 5 次master.guess，其中一次猜到了秘密，所以我们通过了这个测试用例。 提示：任何试图绕过评判的解决方案都将导致比赛资格被取消。
     *
     * @param wordlist
     * @param master
     */
    public void findSecretWord(String[] wordlist, Master master) {
        if (Objects.isNull(wordlist) || wordlist.length == 0) {
            return;
        }
        List<String> list = new ArrayList<>(Arrays.asList(wordlist));
        // 猜10次
        for (int i = 0; i < 10; i++) {
            int size = list.size();
            int idx = RANDOM.nextInt(size);
            String word = list.remove(idx);
            int match = master.guess(word);
            if (match == 6) {
                return;
            }
            list = getNextWordList(list, word, match);
        }
    }

    /**
     * 获取下一轮 需要 匹配的 单词列表
     *
     * @param list
     * @return
     */
    private List<String> getNextWordList(List<String> list, final String word, final int match) {

        return list.stream()
                .filter(
                        str -> {
                            int num = 0;
                            for (int i = 0; i < 6; i++) {
                                if (str.charAt(i) == word.charAt(i)) {
                                    num++;
                                }
                            }
                            return num == match;
                        })
                .collect(Collectors.toList());
    }
}

interface Master {
    int guess(String word);
}
