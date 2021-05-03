package com.qinfengsa.structure.leetcode;

import java.util.Objects;

/**
 * 1032. 字符流
 *
 * <p>按下述要求实现 StreamChecker 类：
 *
 * <p>StreamChecker(words)：构造函数，用给定的字词初始化数据结构。 query(letter)：如果存在某些 k >= 1，可以用查询的最后
 * k个字符（按从旧到新顺序，包括刚刚查询的字母）拼写出给定字词表中的某一字词时，返回 true。否则，返回 false。
 *
 * <p>示例：
 *
 * <p>StreamChecker streamChecker = new StreamChecker(["cd","f","kl"]); // 初始化字典
 * streamChecker.query('a'); // 返回 false streamChecker.query('b'); // 返回 false
 * streamChecker.query('c'); // 返回 false streamChecker.query('d'); // 返回 true，因为 'cd' 在字词表中
 * streamChecker.query('e'); // 返回 false streamChecker.query('f'); // 返回 true，因为 'f' 在字词表中
 * streamChecker.query('g'); // 返回 false streamChecker.query('h'); // 返回 false
 * streamChecker.query('i'); // 返回 false streamChecker.query('j'); // 返回 false
 * streamChecker.query('k'); // 返回 false streamChecker.query('l'); // 返回 true，因为 'kl' 在字词表中。
 *
 * <p>提示：
 *
 * <p>1 <= words.length <= 2000 1 <= words[i].length <= 2000 字词只包含小写英文字母。 待查项只包含小写英文字母。 待查项最多 40000
 * 个。
 *
 * @author qinfengsa
 * @date 2021/05/03 10:23
 */
public class StreamChecker {

    private static final int SIZE = 26;
    // 根节点
    private final TrieNode root;

    private final StringBuilder queryStr;

    int maxLength = 0;

    /** 节点 */
    static class TrieNode {
        // 所有的儿子节点
        private TrieNode[] children;
        // 是不是最后一个节点
        private boolean isEnd;

        TrieNode() {
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
            if (Objects.isNull(currentNode)) {
                currentNode = new TrieNode();
                node.children[c - 'a'] = currentNode;
            }
            // 判断是否 end
            if (i == 0) {
                currentNode.isEnd = true;
            }
            node = currentNode;
        }
    }

    /**
     * 构造函数，用给定的字词初始化数据结构。
     *
     * @param words
     */
    public StreamChecker(String[] words) {
        root = new TrieNode();
        queryStr = new StringBuilder();
        for (String word : words) {
            reverseInsert(word);
        }
    }

    /**
     * 如果存在某些 k >= 1，可以用查询的最后 k个字符（按从旧到新顺序，包括刚刚查询的字母）拼写出给定字词表中的某一字词时，返回 true。否则，返回 false
     *
     * <p>来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/stream-of-characters
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param letter
     * @return
     */
    public boolean query(char letter) {
        queryStr.append(letter);

        TrieNode node = root;

        for (int i = queryStr.length() - 1; i >= 0; i--) {
            char c = queryStr.charAt(i);
            TrieNode currentNode = node.children[c - 'a'];
            if (Objects.isNull(currentNode)) {
                return false;
            }
            if (currentNode.isEnd) {
                return true;
            }
            node = currentNode;
        }

        return false;
    }
}
