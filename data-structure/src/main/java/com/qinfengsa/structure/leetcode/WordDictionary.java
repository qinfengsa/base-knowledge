package com.qinfengsa.structure.leetcode;

import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

/**
 * 211. 添加与搜索单词 - 数据结构设计
 * 设计一个支持以下两种操作的数据结构：
 *
 * void addWord(word)
 * bool search(word)
 * search(word) 可以搜索文字或正则表达式字符串，字符串只包含字母 . 或 a-z 。 . 可以表示任何一个字母。
 *
 * 示例:
 *
 * addWord("bad")
 * addWord("dad")
 * addWord("mad")
 * search("pad") -> false
 * search("bad") -> true
 * search(".ad") -> true
 * search("b..") -> true
 * 说明:
 *
 * 你可以假设所有单词都是由小写字母 a-z 组成的。
 * @author qinfengsa
 * @date 2020/05/15 00:23
 */
@Slf4j
public class WordDictionary {
    private static final int SIZE = 26;
    // 根节点
    private WordNode root;

    /** Initialize your data structure here. */
    public WordDictionary() {
        root = new WordNode();
    }

    /** Adds a word into the data structure. */
    public void addWord(String word) {
        if (word.length() == 0) {
            return;
        }
        WordNode node = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);

            WordNode child = node.children[c - 'a'];
            if (Objects.isNull(child)) {
                child = new WordNode();
                node.children[c - 'a'] = child;
            }
            child.num++;
            if (i == word.length() - 1) {
                child.isEnd = true;
            }
            node = child;
        }


    }

    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
    public boolean search(String word) {
        if (word.length() == 0) {
            return false;
        }
        WordNode node = root;
        /*for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);

            if (c == '*') {

            }
            WordNode child = node.children[c - 'a'];
            if (Objects.isNull(child)) {
                child = new WordNode();
                child.val = word;
            } else {
                child.num++;
            }
            if (i == word.length() - 1) {
                child.isEnd = true;
            }
            node = child;
        }*/
        return search(word,0,node);
    }


    private boolean search(String word,int start,WordNode node) {
        if (start == word.length()) {
            return node.isEnd;
        }
        char c = word.charAt(start);
        if (c == '.') {
            for (int i = 0; i < SIZE; i++) {
                if (Objects.isNull(node.children[i])) {
                    continue;
                }
                if (search(word,start + 1,node.children[i])) {
                    return true;
                }
            }
        } else if (Objects.nonNull(node.children[c - 'a']) ) {
            return search(word,start + 1,node.children[c - 'a']);
        }
        return false;
    }

    /**
     * 节点
     */
    private class WordNode {
        // 有多少单词通过这个节点,即由根至该节点组成的字符串模式出现的次数
        private int num;
        // 所有的儿子节点
        private WordNode[] children;
        // 是不是最后一个节点
        private boolean isEnd;

        WordNode() {
            num = 0;
            children = new WordNode[SIZE];
            isEnd = false;
        }
    }

    public static void main(String[] args) {
        WordDictionary wordDictionary = new WordDictionary();
        wordDictionary.addWord("at");
        wordDictionary.addWord("and");
        wordDictionary.addWord("an");
        wordDictionary.addWord("add");
        boolean val4 = wordDictionary.search("a");
        log.debug("val4：{} ",val4);
        boolean val5 = wordDictionary.search(".at");
        log.debug("val5：{} ",val5);
        wordDictionary.addWord("bat");
        boolean val7 = wordDictionary.search(".at");
        log.debug("val7：{} ",val7);
        boolean val8 = wordDictionary.search("an.");
        log.debug("val8：{} ",val8);
        boolean val9 = wordDictionary.search("a.d.");
        log.debug("val9：{} ",val9);
        boolean val10 = wordDictionary.search("b.");
        log.debug("val10：{} ",val10);
        boolean val11 = wordDictionary.search("a.d");
        log.debug("val11：{} ",val11);
        boolean val12 = wordDictionary.search(".");
        log.debug("val12：{} ",val12);

    }
}
