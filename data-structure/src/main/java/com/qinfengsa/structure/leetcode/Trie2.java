package com.qinfengsa.structure.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 前缀数
 *
 * <p>面试题 17.17. 多次搜索
 *
 * @author qinfengsa
 * @date 2020/06/15 14:05
 */
public class Trie2 {
    private static final int SIZE = 26;

    class TrieNode {
        int id;
        TrieNode[] children = new TrieNode[SIZE];
        boolean isEnd;
    }

    // 根节点
    private TrieNode root;

    /** Initialize your data structure here. */
    public Trie2() {
        root = new TrieNode();
    }

    public void insert(String word, int id) {
        if (word.length() == 0) {
            return;
        }
        TrieNode node = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            TrieNode currentNode = node.children[c - 'a'];
            if (Objects.isNull(currentNode)) {
                currentNode = new TrieNode();
                node.children[c - 'a'] = currentNode;
            }

            if (i == word.length() - 1) {
                currentNode.isEnd = true;
                currentNode.id = id;
            }

            node = currentNode;
        }
    }

    /**
     * 面试题 17.17. 多次搜索
     *
     * @param word
     * @return
     */
    public List<Integer> multiSearch(String word) {
        List<Integer> list = new ArrayList<>();
        TrieNode node = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            TrieNode currentNode = node.children[c - 'a'];
            if (Objects.isNull(currentNode)) {
                break;
            }
            if (currentNode.isEnd) {
                list.add(currentNode.id);
            }
            node = currentNode;
        }

        return list;
    }
}
