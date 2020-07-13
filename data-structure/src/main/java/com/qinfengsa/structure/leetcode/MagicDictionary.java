package com.qinfengsa.structure.leetcode;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * 676. 实现一个魔法字典
 *
 * <p>实现一个带有buildDict, 以及 search方法的魔法字典。
 *
 * <p>对于buildDict方法，你将被给定一串不重复的单词来构建一个字典。
 *
 * <p>对于search方法，你将被给定一个单词，并且判定能否只将这个单词中一个字母换成另一个字母，使得所形成的新单词存在于你构建的字典中。
 *
 * <p>示例 1:
 *
 * <p>Input: buildDict(["hello", "leetcode"]), Output: Null Input: search("hello"), Output: False
 * Input: search("hhllo"), Output: True Input: search("hell"), Output: False Input:
 * search("leetcoded"), Output: False 注意:
 *
 * <p>你可以假设所有输入都是小写字母 a-z。 为了便于竞赛，测试所用的数据量很小。你可以在竞赛结束后，考虑更高效的算法。
 * 请记住重置MagicDictionary类中声明的类变量，因为静态/类变量会在多个测试用例中保留。 请参阅这里了解更多详情。
 *
 * @author qinfengsa
 * @date 2020/07/13 15:26
 */
public class MagicDictionary {

    private Set<String> dicts;

    /** Initialize your data structure here. */
    public MagicDictionary() {
        dicts = new HashSet<>();
    }

    /** Build a dictionary through a list of words */
    public void buildDict(String[] dict) {
        for (String word : dict) {
            dicts.add(word);
        }
    }

    /**
     * Returns if there is any word in the trie that equals to the given word after modifying
     * exactly one character
     */
    public boolean search(String word) {

        Optional<String> optional =
                dicts.stream()
                        .filter(
                                w -> {
                                    if (w.length() != word.length()) {
                                        return false;
                                    }
                                    int count = 0;
                                    for (int i = 0; i < word.length(); i++) {
                                        if (w.charAt(i) != word.charAt(i)) {
                                            count++;
                                        }
                                        if (count > 1) {
                                            return false;
                                        }
                                    }
                                    return count == 1;
                                })
                        .findAny();
        return optional.isPresent();
    }
}
