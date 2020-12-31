package com.qinfengsa.structure.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import lombok.extern.slf4j.Slf4j;

/**
 * 745. 前缀和后缀搜索
 *
 * <p>给定多个 words，words[i] 的权重为 i 。
 *
 * <p>设计一个类 WordFilter 实现函数WordFilter.f(String prefix, String suffix)。这个函数将返回具有前缀 prefix 和后缀suffix
 * 的词的最大权重。如果没有这样的词，返回 -1。
 *
 * <p>例子:
 *
 * <p>输入: WordFilter(["apple"]) WordFilter.f("a", "e") // 返回 0 WordFilter.f("b", "") // 返回 -1 注意:
 *
 * <p>words的长度在[1, 15000]之间。 对于每个测试用例，最多会有words.length次对WordFilter.f的调用。 words[i]的长度在[1, 10]之间。
 * prefix, suffix的长度在[0, 10]之前。 words[i]和prefix, suffix只包含小写字母。
 *
 * @author qinfengsa
 * @date 2020/12/31 20:10
 */
@Slf4j
public class WordFilter {

    TrieNode prefixTrie, suffixTrie;
    Map<String, List<Integer>> prefixMap, suffixMap;

    public WordFilter(String[] words) {
        prefixTrie = new TrieNode();
        suffixTrie = new TrieNode();
        prefixMap = new HashMap<>();
        suffixMap = new HashMap<>();
        Set<String> wordSet = new HashSet<>();
        for (int i = words.length - 1; i >= 0; i--) {
            String word = words[i];
            if (wordSet.contains(word)) {
                continue;
            }
            wordSet.add(word);
            TrieNode node = prefixTrie;
            for (char c : word.toCharArray()) {
                if (Objects.isNull(node.children[c - 'a'])) {
                    node.children[c - 'a'] = new TrieNode();
                }
                node = node.children[c - 'a'];
                node.weight.add(i);
            }
            node = suffixTrie;
            for (int j = word.length() - 1; j >= 0; j--) {
                char c = word.charAt(j);
                if (Objects.isNull(node.children[c - 'a'])) {
                    node.children[c - 'a'] = new TrieNode();
                }
                node = node.children[c - 'a'];
                node.weight.add(i);
            }
        }
    }

    public int f(String prefix, String suffix) {
        TrieNode node1 = prefixTrie, node2 = suffixTrie;
        List<Integer> prefixList, suffixList;
        if (prefixMap.containsKey(prefix)) {
            prefixList = prefixMap.get(prefix);
        } else {
            for (char c : prefix.toCharArray()) {
                if (Objects.isNull(node1.children[c - 'a'])) {
                    return -1;
                }
                node1 = node1.children[c - 'a'];
            }
            prefixList = node1.weight;
            prefixMap.put(prefix, prefixList);
        }

        if (suffixMap.containsKey(prefix)) {
            suffixList = suffixMap.get(prefix);
        } else {
            for (int j = suffix.length() - 1; j >= 0; j--) {
                char c = suffix.charAt(j);
                if (Objects.isNull(node2.children[c - 'a'])) {
                    return -1;
                }
                node2 = node2.children[c - 'a'];
            }
            suffixList = node2.weight;
            suffixMap.put(suffix, suffixList);
        }
        int len1 = prefixList.size(), len2 = suffixList.size();
        int i = 0, j = 0;
        while (i < len1 && j < len2) {
            if (prefixList.get(i).equals(suffixList.get(j))) {
                return prefixList.get(i);
            } else if (prefixList.get(i) > suffixList.get(j)) {
                i++;
            } else {
                j++;
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        WordFilter filter =
                new WordFilter(
                        new String[] {
                            "cabaabaaaa",
                            "ccbcababac",
                            "bacaabccba",
                            "bcbbcbacaa",
                            "abcaccbcaa",
                            "accabaccaa",
                            "cabcbbbcca",
                            "ababccabcb",
                            "caccbbcbab",
                            "bccbacbcba"
                        });
        // ["bccbacbcba","a"],   9
        int val1 = filter.f("bccbacbcba", "a");
        log.debug("val1:{}", val1);
        // ["ab","abcaccbcaa"],4
        int val2 = filter.f("ab", "abcaccbcaa");
        log.debug("val2:{}", val2);
        // ["a","aa"],5
        int val3 = filter.f("a", "aa");
        log.debug("val3:{}", val3);
        // ["cabaaba","abaaaa"],0
        int val4 = filter.f("cabaaba", "abaaaa");
        log.debug("val4:{}", val4);
        // ["cacc","accbbcbab"],8
        int val5 = filter.f("cacc", "accbbcbab");
        log.debug("val5:{}", val5);
        // ["ccbcab","bac"],1
        int val6 = filter.f("ccbcab", "bac");
        log.debug("val6:{}", val6);
        // ["bac","cba"],2
        int val7 = filter.f("bac", "cba");
        log.debug("val7:{}", val7);
        // ["ac","accabaccaa"],5
        int val8 = filter.f("ac", "accabaccaa");
        log.debug("val8:{}", val8);
        // ["bcbb","aa"],3
        int val9 = filter.f("bcbb", "aa");
        log.debug("val9:{}", val9);
        // ["ccbca","cbcababac"]]  1
        int val10 = filter.f("ccbca", "cbcababac");
        log.debug("val10:{}", val10);
    }

    static class TrieNode {
        TrieNode[] children;
        List<Integer> weight;

        public TrieNode() {
            children = new TrieNode[26];
            weight = new ArrayList<>();
        }
    }
}
