package com.qinfengsa.structure.leetcode;

import static com.qinfengsa.structure.util.LogUtils.logResult;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

/**
 * 字典树
 *
 * @author qinfengsa
 * @date 2020/08/27 08:37
 */
@Slf4j
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

    /**
     * 1707. 与数组中元素的最大异或值
     *
     * <p>给你一个由非负整数组成的数组 nums 。另有一个查询数组 queries ，其中 queries[i] = [xi, mi] 。
     *
     * <p>第 i 个查询的答案是 xi 和任何 nums 数组中不超过 mi 的元素按位异或（XOR）得到的最大值。换句话说，答案是 max(nums[j] XOR xi) ，其中所有 j
     * 均满足 nums[j] <= mi 。如果 nums 中的所有元素都大于 mi，最终答案就是 -1 。
     *
     * <p>返回一个整数数组 answer 作为查询的答案，其中 answer.length == queries.length 且 answer[i] 是第 i 个查询的答案。
     *
     * <p>示例 1：
     *
     * <p>输入：nums = [0,1,2,3,4], queries = [[3,1],[1,3],[5,6]] 输出：[3,3,7] 解释： 1) 0 和 1 是仅有的两个不超过 1
     * 的整数。0 XOR 3 = 3 而 1 XOR 3 = 2 。二者中的更大值是 3 。 2) 1 XOR 2 = 3. 3) 5 XOR 2 = 7. 示例 2：
     *
     * <p>输入：nums = [5,2,4,6,6,3], queries = [[12,4],[8,1],[6,3]] 输出：[15,-1,5]
     *
     * <p>提示：
     *
     * <p>1 <= nums.length, queries.length <= 105 queries[i].length == 2 0 <= nums[j], xi, mi <= 109
     *
     * @param nums
     * @param queries
     * @return
     */
    public int[] maximizeXor(int[] nums, int[][] queries) {
        binaryRoot = new BinaryTrieNode(-1);
        int len = queries.length;
        int[] result = new int[len];
        List<XorNode> xorNodeList = new ArrayList<>();
        for (int i = 0; i < len; i++) {
            xorNodeList.add(new XorNode(i, queries[i][0], queries[i][1]));
        }
        xorNodeList.sort(Comparator.comparingInt(node -> node.max));
        Arrays.sort(nums);
        // 二进制
        int i = 0;
        for (XorNode node : xorNodeList) {
            for (; i < nums.length; i++) {
                if (nums[i] <= node.max) {
                    insert(nums[i]);
                } else {
                    break;
                }
            }
            result[node.index] = getNum(node.num);
        }

        return result;
    }

    BinaryTrieNode binaryRoot;

    static class BinaryTrieNode {
        int val;
        // 0 和 1
        BinaryTrieNode[] children = new BinaryTrieNode[2];

        BinaryTrieNode() {}

        BinaryTrieNode(int val) {
            this.val = val;
        }
    }

    private int[] binary2decimal(int decNum) {
        int[] nums = new int[31];
        int idx = 30;
        while (decNum > 0) {

            nums[idx--] = decNum & 1;
            decNum >>= 1;
        }
        return nums;
    }

    void insert(int num) {
        BinaryTrieNode node = binaryRoot;
        int[] binaryNum = binary2decimal(num);
        for (int idx : binaryNum) {
            if (Objects.isNull(node.children[idx])) {
                node.children[idx] = new BinaryTrieNode();
            }
            node = node.children[idx];
        }
        node.val = num;
    }

    int getNum(int num) {
        BinaryTrieNode node = binaryRoot;
        int[] binaryNum = binary2decimal(num);
        if (Objects.isNull(node.children[0]) && Objects.isNull(node.children[1])) {
            return -1;
        }

        for (int idx : binaryNum) {
            if (Objects.nonNull(node.children[1 - idx])) {
                node = node.children[1 - idx];
            } else {
                node = node.children[idx];
            }
        }
        return node.val ^ num;
    }

    static class XorNode {
        int index, num, max;

        public XorNode(int index, int num, int max) {
            this.index = index;
            this.num = num;
            this.max = max;
        }
    }

    @Test
    public void maximizeXor() {
        int[] nums = {5, 2, 4, 6, 6, 3};
        int[][] queries = {{12, 4}, {8, 1}, {6, 3}};
        int[] result = maximizeXor(nums, queries);
        log.debug("result:{}", result);
    }

    /**
     * 1803. 统计异或值在范围内的数对有多少
     *
     * <p>给你一个整数数组 nums （下标 从 0 开始 计数）以及两个整数：low 和 high ，请返回 漂亮数对 的数目。
     *
     * <p>漂亮数对 是一个形如 (i, j) 的数对，其中 0 <= i < j < nums.length 且 low <= (nums[i] XOR nums[j]) <= high 。
     *
     * <p>示例 1：
     *
     * <p>输入：nums = [1,4,2,7], low = 2, high = 6 输出：6 解释：所有漂亮数对 (i, j) 列出如下： - (0, 1): nums[0] XOR
     * nums[1] = 5 - (0, 2): nums[0] XOR nums[2] = 3 - (0, 3): nums[0] XOR nums[3] = 6 - (1, 2):
     * nums[1] XOR nums[2] = 6 - (1, 3): nums[1] XOR nums[3] = 3 - (2, 3): nums[2] XOR nums[3] = 5
     * 示例 2：
     *
     * <p>输入：nums = [9,8,4,2,1], low = 5, high = 14 输出：8 解释：所有漂亮数对 (i, j) 列出如下： - (0, 2): nums[0]
     * XOR nums[2] = 13 - (0, 3): nums[0] XOR nums[3] = 11 - (0, 4): nums[0] XOR nums[4] = 8 - (1,
     * 2): nums[1] XOR nums[2] = 12 - (1, 3): nums[1] XOR nums[3] = 10 - (1, 4): nums[1] XOR nums[4]
     * = 9 - (2, 3): nums[2] XOR nums[3] = 6 - (2, 4): nums[2] XOR nums[4] = 5
     *
     * <p>提示：
     *
     * <p>1 <= nums.length <= 2 * 104 1 <= nums[i] <= 2 * 104 1 <= low <= high <= 2 * 104
     *
     * @param nums
     * @param low
     * @param high
     * @return
     */
    public int countPairs(int[] nums, int low, int high) {
        binaryRoot = new BinaryTrieNode(-1);
        for (int num : nums) {
            insert(num);
        }
        return 0;
    }
}
