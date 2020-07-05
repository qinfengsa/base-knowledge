package com.qinfengsa.structure.leetcode;

import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import lombok.extern.slf4j.Slf4j;

/**
 * 实现 Trie (前缀树) 实现一个 Trie (前缀树)，包含 insert, search, 和 startsWith 这三个操作。
 *
 * <p>示例:
 *
 * <p>Trie trie = new Trie();
 *
 * <p>trie.insert("apple"); trie.search("apple"); // 返回 true trie.search("app"); // 返回 false
 * trie.startsWith("app"); // 返回 true trie.insert("app"); trie.search("app"); // 返回 true 说明:
 *
 * <p>你可以假设所有的输入都是由小写字母 a-z 构成的。 保证所有输入均为非空字符串。
 *
 * @author: qinfengsa
 * @date: 2020/2/15 22:48
 */
@Slf4j
public class Trie {
    private static final int SIZE = 26;
    // 根节点
    private TrieNode root;

    /** Initialize your data structure here. */
    public Trie() {
        root = new TrieNode();
    }

    /** 节点 */
    static class TrieNode {
        // 有多少单词通过这个节点,即由根至该节点组成的字符串模式出现的次数
        private int num;
        // 所有的儿子节点
        private TrieNode[] children;
        // 是不是最后一个节点
        private boolean isEnd;
        // 节点的值
        private String val;

        TrieNode() {
            num = 0;
            children = new TrieNode[SIZE];
            isEnd = false;
        }
    }

    /**
     * 逆序插入
     *
     * @param word
     */
    public void reverseInsert(String word) {
        if (word.length() == 0) {
            return;
        }
        TrieNode node = root;
        // 遍历word
        for (int i = word.length() - 1; i >= 0; i--) {
            char c = word.charAt(i);
            // 取子节点
            TrieNode currentNode = node.children[c - 'a'];
            if (currentNode == null) {
                currentNode = new TrieNode();
                node.children[c - 'a'] = currentNode;
            }
            currentNode.num++;
            // 判断是否 end
            if (i == 0) {
                currentNode.isEnd = true;
                currentNode.val = word;
            }
            node = currentNode;
        }
    }

    public int minimumLengthEncoding() {
        // 深度优先遍历
        if (this.root == null) {
            return 0;
        }
        int result = 0;
        Deque<TrieNode> stack = new LinkedList<>();
        stack.addLast(this.root);
        while (!stack.isEmpty()) {
            TrieNode node = stack.removeLast();
            String val = node.val;
            int count = 0;
            for (TrieNode child : node.children) {
                if (child != null) {
                    stack.addLast(child);
                    count++;
                }
            }
            if (count == 0) {
                result += val.length() + 1;
            }
        }

        return result;
    }

    /** Inserts a word into the trie. */
    public void insert(String word) {
        if (word.length() == 0) {
            return;
        }
        TrieNode node = root;
        // 遍历word
        for (char c : word.toCharArray()) {
            if (Objects.isNull(node.children[c - 'a'])) {
                node.children[c - 'a'] = new TrieNode();
            }
            node = node.children[c - 'a'];
            node.num++;
        }
        node.isEnd = true;
        node.val = word;
    }

    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        if (word.length() == 0) {
            return false;
        }
        TrieNode node = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            // 取子节点
            TrieNode currentNode = node.children[c - 'a'];
            if (currentNode == null) {
                return false;
            }
            if (i == word.length() - 1 && currentNode.isEnd) {
                return true;
            }
            node = currentNode;
        }
        return false;
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        if (prefix.length() == 0) {
            return false;
        }
        TrieNode node = root;
        for (int i = 0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);
            // 取子节点
            TrieNode currentNode = node.children[c - 'a'];
            if (currentNode == null) {
                return false;
            }
            node = currentNode;
        }
        return true;
    }

    public String longestWord() {
        // 深度优先遍历
        if (this.root == null) {
            return "";
        }
        String result = "";
        Deque<TrieNode> stack = new LinkedList<>();
        stack.addLast(this.root);
        while (!stack.isEmpty()) {
            TrieNode node = stack.removeLast();
            String val = node.val;
            if (node.isEnd) {
                if (val.length() > result.length()) {
                    result = val;
                } else if (val.length() == result.length() && val.compareTo(result) < 0) {
                    result = val;
                }
            }
            if (node == root || node.isEnd) {
                for (TrieNode child : node.children) {
                    if (child != null) {
                        stack.addLast(child);
                    }
                }
            }
        }
        return result;
    }

    public String getReplaceWord(String word) {
        TrieNode node = root;
        for (char c : word.toCharArray()) {
            if (Objects.isNull(node.children[c - 'a'])) {
                break;
            }
            node = node.children[c - 'a'];
            if (node.isEnd) {
                break;
            }
        }
        return node.isEnd ? node.val : word;
    }

    public static void main(String[] args) {
        /*Trie trie = new Trie();
        trie.insert("app");
        trie.insert("apple");
        trie.insert("beer");
        trie.insert("add");
        trie.insert("jam");
        trie.insert("rental");
        boolean b6 = trie.search("apps");
        log.debug("b6：{} ",b6);
        boolean b7 = trie.search("app");
        log.debug("b7：{} ",b7);
        boolean b8 = trie.search("ad");
        log.debug("b8：{} ",b8);
        boolean b9 = trie.search("applepie");
        log.debug("b9：{} ",b9);
        boolean b10 = trie.search("rest");
        log.debug("b10：{} ",b10);
        boolean b11 = trie.search("jan");
        log.debug("b11：{} ",b11);
        boolean b12 = trie.search("rent");
        log.debug("b12：{} ",b12);
        boolean b13 = trie.search("beer");
        log.debug("b13：{} ",b13);
        boolean b14 = trie.search("jam");
        log.debug("b14：{} ",b14);
        boolean b15 = trie.startsWith("apps");
        log.debug("b15：{} ",b15);
        boolean b16 = trie.startsWith("app");
        log.debug("b16：{} ",b16);
        boolean b17 = trie.startsWith("ad");
        log.debug("b17：{} ",b17);
        boolean b18 = trie.startsWith("applepie");
        log.debug("b18：{} ",b18);
        boolean b19 = trie.startsWith("rest");
        log.debug("b19：{} ",b19);
        boolean b20 = trie.startsWith("jan");
        log.debug("b20：{} ",b20);
        boolean b21 = trie.startsWith("rent");
        log.debug("b21：{} ",b21);
        boolean b22 = trie.startsWith("beer");
        log.debug("b22：{} ",b22);
        boolean b23 = trie.startsWith("jam");
        log.debug("b23：{} ",b23);*/

        /* trie.insert("apple");
        boolean a = trie.search("apple");
        log.debug("a：{} // 返回 true",a);
        boolean b = trie.search("app");
        log.debug("b：{} // 返回 false",b);
        boolean c = trie.startsWith("app");
        log.debug("c：{} // 返回 true",c);
        trie.insert("app");
        boolean d = trie.search("app");
        log.debug("d：{} // 返回 true",d);*/

    }

    static class Solution {

        public static void main(String[] args) {
            Solution solution = new Solution();
            char[][] board = {
                {'o', 'a', 'a', 'n'},
                {'e', 't', 'a', 'e'},
                {'i', 'h', 'k', 'r'},
                {'i', 'f', 'l', 'v'}
            };
            String[] words = {"oath", "pea", "eat", "rain"};
            List<String> result = solution.findWords(board, words);
            log.debug("result:{}", result);
        }

        public List<String> findWords(char[][] board, String[] words) {
            Set<String> set = new HashSet<>();
            int rowNum = board.length;
            if (rowNum == 0) {
                return new ArrayList<>();
            }
            int colNum = board[0].length;
            if (colNum == 0) {
                return new ArrayList<>();
            }
            boolean[][] marked = new boolean[rowNum][colNum];
            Trie trie = new Trie();
            for (String word : words) {
                trie.insert(word);
            }
            for (int i = 0; i < rowNum; i++) {
                for (int j = 0; j < colNum; j++) {
                    findWord(board, marked, i, j, trie.root, set);
                }
            }
            List<String> result = new ArrayList<>(set);
            return result;
        }

        private void findWord(
                char[][] board,
                boolean[][] marked,
                int row,
                int col,
                TrieNode node,
                Set<String> set) {
            if (!inArea(row, col, board.length, board[0].length) || marked[row][col]) {
                return;
            }
            char c = board[row][col];
            node = node.children[c - 'a'];
            if (node == null) {
                return;
            }
            marked[row][col] = true;
            if (node.isEnd) {
                set.add(node.val);
            }
            findWord(board, marked, row - 1, col, node, set);
            findWord(board, marked, row + 1, col, node, set);
            findWord(board, marked, row, col - 1, node, set);
            findWord(board, marked, row, col + 1, node, set);
            marked[row][col] = false;
        }

        private boolean inArea(int row, int col, int rowNum, int colNum) {
            return row >= 0 && row < rowNum && col >= 0 && col < colNum;
        }
    }
}
