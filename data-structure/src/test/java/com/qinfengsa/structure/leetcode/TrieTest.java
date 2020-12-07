package com.qinfengsa.structure.leetcode;

import static com.qinfengsa.structure.util.LogUtils.logResult;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import org.junit.jupiter.api.Test;

/**
 * 字典树
 *
 * @author qinfengsa
 * @date 2020/08/27 08:37
 */
public class TrieTest {

    private static final int SIZE = 26;

    // 根节点
    private TrieNode root = new TrieNode();

    /** 节点 */
    static class TrieNode {
        // 有多少单词通过这个节点,即由根至该节点组成的字符串模式出现的次数
        private int count;
        // 所有的儿子节点
        private TrieNode[] children;
        // 是不是最后一个节点
        private boolean isEnd;
        // 节点的值
        private List<String> list;

        TrieNode() {
            count = 0;
            children = new TrieNode[SIZE];
            isEnd = false;
            list = new ArrayList<>();
        }
    }

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
            node.list.add(word);
            node.count++;
        }
        node.isEnd = true;
    }

    @Test
    public void suggestedProducts() {
        String[] products = {"havana"};
        String searchWord = "tatiana";
        logResult(suggestedProducts(products, searchWord));
    }

    /**
     * 1268. 搜索推荐系统
     *
     * <p>给你一个产品数组 products 和一个字符串 searchWord ，products 数组中每个产品都是一个字符串。
     *
     * <p>请你设计一个推荐系统，在依次输入单词 searchWord 的每一个字母后，推荐 products 数组中前缀与 searchWord
     * 相同的最多三个产品。如果前缀相同的可推荐产品超过三个，请按字典序返回最小的三个。
     *
     * <p>请你以二维列表的形式，返回在输入 searchWord 每个字母后相应的推荐产品的列表。
     *
     * <p>示例 1：
     *
     * <p>输入：products = ["mobile","mouse","moneypot","monitor","mousepad"], searchWord = "mouse"
     * 输出：[ ["mobile","moneypot","monitor"], ["mobile","moneypot","monitor"], ["mouse","mousepad"],
     * ["mouse","mousepad"], ["mouse","mousepad"] ] 解释：按字典序排序后的产品列表是
     * ["mobile","moneypot","monitor","mouse","mousepad"] 输入 m 和 mo，由于所有产品的前缀都相同，所以系统返回字典序最小的三个产品
     * ["mobile","moneypot","monitor"] 输入 mou， mous 和 mouse 后系统都返回 ["mouse","mousepad"] 示例 2：
     *
     * <p>输入：products = ["havana"], searchWord = "havana"
     * 输出：[["havana"],["havana"],["havana"],["havana"],["havana"],["havana"]] 示例 3：
     *
     * <p>输入：products = ["bags","baggage","banner","box","cloths"], searchWord = "bags"
     * 输出：[["baggage","bags","banner"],["baggage","bags","banner"],["baggage","bags"],["bags"]] 示例
     * 4：
     *
     * <p>输入：products = ["havana"], searchWord = "tatiana" 输出：[[],[],[],[],[],[],[]]
     *
     * <p>提示：
     *
     * <p>1 <= products.length <= 1000 1 <= Σ products[i].length <= 2 * 10^4 products[i]
     * 中所有的字符都是小写英文字母。 1 <= searchWord.length <= 1000 searchWord 中所有字符都是小写英文字母。
     *
     * @param products
     * @param searchWord
     * @return
     */
    public List<List<String>> suggestedProducts(String[] products, String searchWord) {
        List<List<String>> result = new ArrayList<>();
        Arrays.sort(products);
        for (String product : products) {
            insert(product);
        }

        TrieNode node = root;
        boolean flag = false;
        for (int i = 0; i < searchWord.length(); i++) {
            if (flag) {
                result.add(new ArrayList<>());
                continue;
            }
            char c = searchWord.charAt(i);
            // 取子节点
            TrieNode currentNode = node.children[c - 'a'];
            if (Objects.isNull(currentNode)) {
                result.add(new ArrayList<>());
                flag = true;
                continue;
            }
            List<String> list = currentNode.list;
            int size = list.size();
            int endIndex = Math.min(size, 3);
            node = currentNode;
            result.add(list.subList(0, endIndex));
        }

        return result;
    }

    /**
     * 472. 连接词
     *
     * <p>给定一个不含重复单词的列表，编写一个程序，返回给定单词列表中所有的连接词。
     *
     * <p>连接词的定义为：一个字符串完全是由至少两个给定数组中的单词组成的。
     *
     * <p>示例:
     *
     * <p>输入: ["cat","cats","catsdogcats","dog","dogcatsdog","hippopotamuses","rat","ratcatdogcat"]
     *
     * <p>输出: ["catsdogcats","dogcatsdog","ratcatdogcat"]
     *
     * <p>解释: "catsdogcats"由"cats", "dog" 和 "cats"组成; "dogcatsdog"由"dog", "cats"和"dog"组成;
     * "ratcatdogcat"由"rat", "cat", "dog"和"cat"组成。 说明:
     *
     * <p>给定数组的元素总数不超过 10000。 给定数组中元素的长度总和不超过 600000。 所有输入字符串只包含小写字母。 不需要考虑答案输出的顺序。
     *
     * @param words
     * @return
     */
    public List<String> findAllConcatenatedWordsInADict(String[] words) {

        List<String> list = new ArrayList<>();
        for (String word : words) {
            insert(word);
        }
        for (String word : words) {
            if (findAllConcatenatedWordsInADictDfs(word, 0)) {
                list.add(word);
            }
        }

        return list;
    }

    private boolean findAllConcatenatedWordsInADictDfs(String word, int start) {

        int len = word.length();
        TrieNode node = root;
        for (int i = start; i < len; i++) {
            char c = word.charAt(i);
            if (Objects.isNull(node.children[c - 'a'])) {
                return false;
            }
            node = node.children[c - 'a'];
            // 短路运算: 如果找到了一个字符串则尝试从头开始走, 找下一个字符串
            if (node.isEnd && findAllConcatenatedWordsInADictDfs(word, i + 1)) {
                return true;
            }
        }

        return node.isEnd && start != 0;
    }
}
