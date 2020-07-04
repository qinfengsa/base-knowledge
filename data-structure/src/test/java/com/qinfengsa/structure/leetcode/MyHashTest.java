package com.qinfengsa.structure.leetcode;

import static com.qinfengsa.structure.util.LogUtils.logResult;

import com.qinfengsa.base.Employee;
import com.qinfengsa.structure.hash.MyHashMap;
import com.qinfengsa.structure.hash.MyHashSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @author: qinfengsa
 * @date: 2019/5/16 11:55
 */
@Slf4j
public class MyHashTest {

    @Test
    public void hashSetTest() {
        MyHashSet hashSet = new MyHashSet();
        hashSet.add(1);
        hashSet.add(2);
        int index = 1;
        log.debug("index:{},{}", index++, hashSet.contains(1)); // 返回 true
        log.debug("index:{},{}", index++, hashSet.contains(3)); // 返回 false (未找到)
        hashSet.add(2);
        log.debug("index:{},{}", index++, hashSet.contains(2)); // 返回 true
        hashSet.remove(2);
        log.debug("index:{},{}", index++, hashSet.contains(2)); // 返回  false (已经被删除)
    }

    @Test
    public void hashMapTest() {

        MyHashMap hashMap = new MyHashMap();
        hashMap.put(1, 1);
        hashMap.put(2, 2);
        int index = 1;
        log.debug("index:{},{}", index++, hashMap.get(1)); // 返回 1
        log.debug("index:{},{}", index++, hashMap.get(3)); // 返回 -1 (未找到)
        hashMap.put(2, 1); // 更新已有的值
        log.debug("index:{},{}", index++, hashMap.get(2)); // 返回 1
        hashMap.remove(2); // 删除键为2的数据
        log.debug("index:{},{}", index++, hashMap.get(2)); // 返回 -1 (未找到)
    }

    @Test
    public void twoSumTest() {
        int[] nums = {2, 7, 11, 15};
        int target = 9;
        int[] result = twoSum(nums, target);
        log.debug("result:{}", result);
    }

    /**
     * 两数之和 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
     *
     * <p>你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素。
     *
     * <p>示例:
     *
     * <p>给定 nums = [2, 7, 11, 15], target = 9
     *
     * <p>因为 nums[0] + nums[1] = 2 + 7 = 9 所以返回 [0, 1]
     *
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum(int[] nums, int target) {
        int[] result = new int[2];
        int size = (nums.length / 3) * 4 + 1;

        // 用Map存之前的所有元素，建立 num[i] -> i 的映射
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int a = nums[i];
            // 判断是否存在 b
            int b = target - a;
            Integer index = map.get(b);
            if (Objects.nonNull(index)) {
                result[0] = index;
                result[1] = i;
                break;
            }

            map.put(a, i);
        }

        return result;
    }

    @Test
    public void containsDuplicate() {
        int[] nums = {1, 2, 3, 1};
        boolean result = containsDuplicate(nums);
        log.debug("result:{}", result);
    }

    /**
     * 存在重复元素 给定一个整数数组，判断是否存在重复元素。
     *
     * <p>如果任何值在数组中出现至少两次，函数返回 true。如果数组中每个元素都不相同，则返回 false。
     *
     * <p>示例 1:
     *
     * <p>输入: [1,2,3,1] 输出: true 示例 2:
     *
     * <p>输入: [1,2,3,4] 输出: false 示例 3:
     *
     * <p>输入: [1,1,1,3,3,4,3,2,4,2] 输出: true
     *
     * @param nums
     * @return
     */
    public boolean containsDuplicate(int[] nums) {
        int size = (nums.length / 3) * 4 + 1;
        Map<Integer, Integer> map = new HashMap<>(size);
        // 通过map 判断重复

        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            if (Objects.nonNull(map.get(num))) {
                return true;
            }

            map.put(num, i);
        }
        return false;
    }

    @Test
    public void singleNumber() {
        int[] nums = {4, 1, 2, 1, 2};
        int result = singleNumber(nums);
        log.debug("result:{}", result);
    }

    /**
     * 只出现一次的数字 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
     *
     * <p>说明：
     *
     * <p>你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？
     *
     * <p>示例 1:
     *
     * <p>输入: [2,2,1] 输出: 1 示例 2:
     *
     * <p>输入: [4,1,2,1,2] 输出: 4
     *
     * @param nums
     * @return
     */
    public int singleNumber(int[] nums) {
        // 由于 0^a = a，a^a = 0 而数组中除了一个数字是只出现一次的，其他数字均出现两次，
        // 则可以采用此思路来解答这个问题。
        int result = 0;
        for (int num : nums) {
            result = result ^ num;
        }

        return result;
    }

    @Test
    public void isHappy() {
        int num = 2;
        boolean result = isHappy(num);
        log.debug("result:{}", result);
    }

    /**
     * 快乐数 编写一个算法来判断一个数是不是“快乐数”。
     *
     * <p>一个“快乐数”定义为：对于一个正整数，每一次将该数替换为它每个位置上的数字的平方和， 然后重复这个过程直到这个数变为 1，也可能是无限循环但始终变不到 1。如果可以变为
     * 1，那么这个数就是快乐数。
     *
     * <p>示例:
     *
     * <p>输入: 19 输出: true 解释: 1^2 + 9^2 = 82 8^2 + 2^2 = 68 6^2 + 8^2 = 100 1^2 + 0^2 + 0^2 = 1
     *
     * @param n
     * @return
     */
    public boolean isHappy(int n) {
        if (n == 0) {
            return false;
        }
        // 用map家用重复
        Map<Integer, Integer> map = new HashMap<>();

        while (n != 1) {
            if (map.containsKey(n)) {
                return false;
            }
            map.put(n, n);
            n = getHappyNum(n);
        }

        return true;
    }

    private int getHappyNum(int num) {

        int sum = 0;
        while (num >= 10) {
            // 求余
            int a = num % 10;
            sum += a * a;
            // 正常
            num = num / 10;
        }
        sum += num * num;
        return sum;
    }

    @Test
    public void isIsomorphic() {
        String s = "ab";
        String t = "aa";
        boolean result = isIsomorphic(s, t);
        log.debug("result:{}", result);
    }

    /**
     * 同构字符串 给定两个字符串 s 和 t，判断它们是否是同构的。
     *
     * <p>如果 s 中的字符可以被替换得到 t ，那么这两个字符串是同构的。
     *
     * <p>所有出现的字符都必须用另一个字符替换，同时保留字符的顺序。两个字符不能映射到同一个字符上，但字符可以映射自己本身。
     *
     * <p>示例 1:
     *
     * <p>输入: s = "egg", t = "add" 输出: true 示例 2:
     *
     * <p>输入: s = "foo", t = "bar" 输出: false 示例 3:
     *
     * <p>输入: s = "paper", t = "title" 输出: true 说明: 你可以假设 s 和 t 具有相同的长度。
     *
     * @param s
     * @param t
     * @return
     */
    public boolean isIsomorphic(String s, String t) {
        int len = s.length();
        int size = (len / 3) * 4 + 1;
        Map<Character, Character> map = new HashMap<>(size);
        Map<Character, Character> map2 = new HashMap<>(size);

        for (int i = 0; i < len; i++) {
            char key = s.charAt(i);
            Character a = map.get(key);
            char value = t.charAt(i);
            if (Objects.nonNull(a) && !Objects.equals(a, value)) {
                return false;
            }
            Character b = map2.get(value);
            if (Objects.nonNull(b) && !Objects.equals(b, key)) {
                return false;
            }

            map.put(key, value);
            map2.put(value, key);
        }
        return true;
    }

    @Test
    public void findRestaurant() {
        String[] list1 = {"Shogun", "KFC", "Tapioca Express", "Burger King"};
        String[] list2 = {"KFC", "Shogun", "Burger King"};
        String[] result = findRestaurant(list1, list2);
        log.debug("result:{}", Arrays.toString(result));
    }

    /**
     * 两个列表的最小索引总和 假设Andy和Doris想在晚餐时选择一家餐厅，并且他们都有一个表示最喜爱餐厅的列表，每个餐厅的名字用字符串表示。
     *
     * <p>你需要帮助他们用最少的索引和找出他们共同喜爱的餐厅。 如果答案不止一个，则输出所有答案并且不考虑顺序。 你可以假设总是存在一个答案。
     *
     * <p>示例 1:
     *
     * <p>输入: ["Shogun", "Tapioca Express", "Burger King", "KFC"] ["Piatti", "The Grill at Torrey
     * Pines", "Hungry Hunter Steakhouse", "Shogun"] 输出: ["Shogun"] 解释: 他们唯一共同喜爱的餐厅是“Shogun”。 示例 2:
     *
     * <p>输入: ["Shogun", "Tapioca Express", "Burger King", "KFC"] ["KFC", "Shogun", "Burger King"]
     * 输出: ["Shogun"] 解释: 他们共同喜爱且具有最小索引和的餐厅是“Shogun”，它有最小的索引和1(0+1)。 提示:
     *
     * <p>两个列表的长度范围都在 [1, 1000]内。 两个列表中的字符串的长度将在[1，30]的范围内。 下标从0开始，到列表的长度减1。 两个列表都没有重复的元素。
     *
     * @param list1
     * @param list2
     * @return
     */
    public String[] findRestaurant(String[] list1, String[] list2) {
        /*Map<String,Integer> map = new HashMap<>();
        int len1 = list1.length;
        int len2 = list2.length;
        int i = 0,j =0;
        while (i < len1 && j < len2) {
            if (i <= j) {
                String s = list1[i];
                Integer num = map.get(s);
                if (Objects.nonNull(num)) {
                    map.put(s,num + i);
                } else {
                    map.put(s,i);
                }
                i++;
            } else {
                String s = list2[j];
                Integer num = map.get(s);
                if (Objects.nonNull(num)) {
                    map.put(s,num + j);
                } else {
                    map.put(s,i);
                }
                j++;
            }
        }*/
        Map<String, Integer> map = new HashMap<>();

        List<String> list = new ArrayList<>();

        // 保证索引总和最小
        for (int i = 0; i < list1.length; i++) {

            map.put(list1[i], i);
        }
        int minIndex = -1;
        for (int i = 0; i < list2.length; i++) {
            String s = list2[i];
            Integer num = map.get(s);
            if (Objects.nonNull(num)) {
                map.put(s, num + i);
                // 记录最小索引
                if (minIndex == -1 || num + i < minIndex) {
                    minIndex = num + i;
                }
                list.add(s);

            } else {
                map.put(s, 1);
            }
        }
        List<String> resultList = new ArrayList<>();
        for (String s : list) {

            if (map.get(s) == minIndex) {
                resultList.add(s);
            }
        }

        String[] result = new String[resultList.size()];

        for (int i = 0; i < resultList.size(); i++) {
            result[i] = resultList.get(i);
        }

        return result;
    }

    @Test
    public void firstUniqChar() {
        String s = "loveleetcode";
        int result = firstUniqChar(s);
        log.debug("result:{}", result);
    }

    /**
     * 字符串中的第一个唯一字符 给定一个字符串，找到它的第一个不重复的字符，并返回它的索引。如果不存在，则返回 -1。
     *
     * <p>案例:
     *
     * <p>s = "leetcode" 返回 0.
     *
     * <p>s = "loveleetcode", 返回 2.
     *
     * <p>注意事项：您可以假定该字符串只包含小写字母。
     *
     * @param s
     * @return
     */
    public int firstUniqChar(String s) {
        Map<Character, Integer> map = new LinkedHashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char key = s.charAt(i);
            if (map.containsKey(key)) {
                map.put(key, -1);
            } else {
                map.put(key, i);
            }
        }
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            if (entry.getValue() != -1) {
                return entry.getValue();
            }
        }

        return -1;
    }

    @Test
    public void containsNearbyDuplicate() {
        int[] nums = {1, 2, 3, 1, 2, 3};
        int k = 2;
        boolean result = containsNearbyDuplicate(nums, k);
        log.debug("result:{}", result);
    }

    /**
     * 存在重复元素 II 给定一个整数数组和一个整数 k，判断数组中是否存在两个不同的索引 i 和 j，使得 nums [i] = nums [j]，并且 i 和 j 的差的绝对值最大为 k。
     *
     * <p>示例 1:
     *
     * <p>输入: nums = [1,2,3,1], k = 3 输出: true 示例 2:
     *
     * <p>输入: nums = [1,0,1,1], k = 1 输出: true 示例 3:
     *
     * <p>输入: nums = [1,2,3,1,2,3], k = 2 输出: false
     *
     * @param nums
     * @param k
     * @return
     */
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        int size = nums.length;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < size; i++) {
            Integer j = map.get(nums[i]);
            if (j != null && i - j <= k) {
                return true;
            }
            map.put(nums[i], i);

            /*for (int j = 1; j <= k; j++) {
                if (i + j >= size) {
                    break;
                }
                if (nums[i] == nums[i+j]) {
                    return true;
                }

            }*/
        }

        return false;
    }

    @Test
    public void groupAnagrams() {
        String[] strs = {"eat", "tea", "tan", "ate", "nat", "bat"};
        List<List<String>> result = groupAnagrams(strs);
        for (List<String> s : result) {
            log.debug("result:{}", s.toString());
        }
    }

    /**
     * 字母异位词分组
     *
     * <p>给定一个字符串数组，将字母异位词组合在一起。字母异位词指字母相同，但排列不同的字符串。
     *
     * <p>示例:
     *
     * <p>输入: ["eat", "tea", "tan", "ate", "nat", "bat"], 输出: [ ["ate","eat","tea"], ["nat","tan"],
     * ["bat"] ] 说明：
     *
     * <p>所有输入均为小写字母。 不考虑答案输出的顺序。
     *
     * @param strs
     * @return
     */
    public List<List<String>> groupAnagrams(String[] strs) {

        List<List<String>> result = new ArrayList<>();

        Map<String, List<String>> map = new HashMap<>();

        for (String str : strs) {
            char[] charKey = str.toCharArray();
            Arrays.sort(charKey);
            String key = String.valueOf(charKey);
            List<String> list = map.get(key);
            if (Objects.isNull(list) || list.isEmpty()) {
                list = new ArrayList<>();
                map.put(key, list);
            }
            list.add(str);
        }
        for (Map.Entry entry : map.entrySet()) {
            result.add(map.get(entry.getKey()));
        }

        return result;
    }

    @Test
    public void isValidSudoku() {

        char[][] board = {
            {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
            {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
            {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
            {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
            {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
            {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
            {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
            {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
            {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
        };

        /*char[][] board = {{'8','3','.','.','7','.','.','.','.'},
        {'6','.','.','1','9','5','.','.','.'},
        {'.','9','8','.','.','.','.','6','.'},
        {'8','.','.','.','6','.','.','.','3'},
        {'4','.','.','8','.','3','.','.','1'},
        {'7','.','.','.','2','.','.','.','6'},
        {'.','6','.','.','.','.','2','8','.'},
        {'.','.','.','4','1','9','.','.','5'},
        {'.','.','.','.','8','.','.','7','9'}};*/
        boolean result = isValidSudoku(board);

        log.debug("result:{}", result);
    }

    /**
     * 判断一个 9x9 的数独是否有效。只需要根据以下规则，验证已经填入的数字是否有效即可。
     *
     * <p>数字 1-9 在每一行只能出现一次。 数字 1-9 在每一列只能出现一次。 数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。
     *
     * <p>上图是一个部分填充的有效的数独。
     *
     * <p>数独部分空格内已填入了数字，空白格用 '.' 表示。
     *
     * <p>示例 1:
     *
     * <p>输入: [ ["5","3",".",".","7",".",".",".","."], ["6",".",".","1","9","5",".",".","."],
     * [".","9","8",".",".",".",".","6","."], ["8",".",".",".","6",".",".",".","3"],
     * ["4",".",".","8",".","3",".",".","1"], ["7",".",".",".","2",".",".",".","6"],
     * [".","6",".",".",".",".","2","8","."], [".",".",".","4","1","9",".",".","5"],
     * [".",".",".",".","8",".",".","7","9"] ] 输出: true 示例 2:
     *
     * <p>输入: [ ["8","3",".",".","7",".",".",".","."], ["6",".",".","1","9","5",".",".","."],
     * [".","9","8",".",".",".",".","6","."], ["8",".",".",".","6",".",".",".","3"],
     * ["4",".",".","8",".","3",".",".","1"], ["7",".",".",".","2",".",".",".","6"],
     * [".","6",".",".",".",".","2","8","."], [".",".",".","4","1","9",".",".","5"],
     * [".",".",".",".","8",".",".","7","9"] ] 输出: false 解释: 除了第一行的第一个数字从 5 改为 8 以外，空格内其他数字均与 示例1
     * 相同。 但由于位于左上角的 3x3 宫内有两个 8 存在, 因此这个数独是无效的。 说明:
     *
     * <p>一个有效的数独（部分已被填充）不一定是可解的。 只需要根据以上规则，验证已经填入的数字是否有效即可。 给定数独序列只包含数字 1-9 和字符 '.' 。 给定数独永远是 9x9
     * 形式的。
     *
     * @param board
     * @return
     */
    public boolean isValidSudoku(char[][] board) {

        Map<String, Boolean> map = new HashMap<>();
        for (int i = 0; i < 9; i++) {

            for (int j = 0; j < 9; j++) {

                char c = board[i][j];
                if (c == '.') {
                    continue;
                }
                String colKey = "col" + c + j;
                if (map.containsKey(colKey)) {
                    return false;
                }
                map.put(colKey, true);

                String rowKey = "row" + c + i;
                if (map.containsKey(rowKey)) {
                    return false;
                }
                map.put(rowKey, true);

                String sudoKey = getSudoKey(c, i, j);
                if (map.containsKey(sudoKey)) {
                    return false;
                }
                map.put(sudoKey, true);
            }
        }

        return true;
    }

    private String getSudoKey(char c, int i, int j) {

        int a = i / 3;
        int b = j / 3;

        return "sudo" + c + a + b;
    }

    @Test
    public void numJewelsInStones() {
        String J = "aA";
        String S = "aAAbbbb";

        int result = numJewelsInStones(J, S);
        log.debug("result:{}", result);
    }

    /**
     * 宝石与石头 给定字符串J 代表石头中宝石的类型，和字符串 S代表你拥有的石头。 S 中每个字符代表了一种你拥有的石头的类型，你想知道你拥有的石头中有多少是宝石。
     *
     * <p>J 中的字母不重复，J 和 S中的所有字符都是字母。字母区分大小写，因此"a"和"A"是不同类型的石头。
     *
     * <p>示例 1:
     *
     * <p>输入: J = "aA", S = "aAAbbbb" 输出: 3 示例 2:
     *
     * <p>输入: J = "z", S = "ZZ" 输出: 0 注意:
     *
     * <p>S 和 J 最多含有50个字母。 J 中的字符不重复。
     *
     * @param J
     * @param S
     * @return
     */
    public int numJewelsInStones(String J, String S) {
        int size = (J.length() / 3) * 4 + 1;
        Map<Character, Boolean> map = new HashMap<>();
        for (int i = 0; i < J.length(); i++) {
            map.put(J.charAt(i), true);
        }
        int result = 0;

        for (int i = 0; i < S.length(); i++) {
            char key = S.charAt(i);
            if (map.containsKey(key)) {
                result++;
            }
        }

        return result;
    }

    @Test
    public void lengthOfLongestSubstring() {
        String s = "pwwkew";
        int result = lengthOfLongestSubstring(s);
        log.debug("result:{}", result);
    }

    /**
     * 无重复字符的最长子串 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
     *
     * <p>示例 1:
     *
     * <p>输入: "abcabcbb" 输出: 3 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。 示例 2:
     *
     * <p>输入: "bbbbb" 输出: 1 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。 示例 3:
     *
     * <p>输入: "pwwkew" 输出: 3 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。 请注意，你的答案必须是 子串 的长度，"pwke"
     * 是一个子序列，不是子串。
     *
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {

        Map<Character, Integer> map = new HashMap<>();

        int maxLen = 0;
        int len = 0;
        for (int i = 0; i < s.length(); i++) {
            char key = s.charAt(i);

            if (map.containsKey(key)) {
                len = map.size();
                if (len > maxLen) {
                    maxLen = len;
                }
                i = map.get(key);
                map = new HashMap<>();
                continue;
            }
            map.put(key, i);
        }

        len = map.size();
        if (len > maxLen) {
            maxLen = len;
        }

        return maxLen;
    }

    @Test
    public void findDuplicateSubtrees() {
        TreeNode root = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(2);
        TreeNode node6 = new TreeNode(4);
        TreeNode node7 = new TreeNode(4);
        TreeNode node8 = new TreeNode(2);
        TreeNode node9 = new TreeNode(1);
        root.left = node2;
        root.right = node3;
        node2.left = node4;

        node3.left = node5;
        node3.right = node6;

        node5.left = node7;
        // node4.right = node8;

        /// node6.right = node9;
        List<TreeNode> result = findDuplicateSubtrees(root);

        log.debug("result:{}", result);
    }

    /**
     * 寻找重复的子树 给定一棵二叉树，返回所有重复的子树。对于同一类的重复子树，你只需要返回其中任意一棵的根结点即可。
     *
     * <p>两棵树重复是指它们具有相同的结构以及相同的结点值。
     *
     * <p>示例 1：
     *
     * <p>1 / \ 2 3 / / \ 4 2 4 / 4 下面是两个重复的子树：
     *
     * <p>2 / 4
     *
     * @param root
     * @return
     */
    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {

        List<TreeNode> result = new ArrayList<>();
        // 用树的路径做Key
        Map<String, Integer> map = new HashMap<>();

        preOrder(root, result, map);

        return result;
    }

    /**
     * 前序遍历
     *
     * @param root
     * @param list
     * @param map
     */
    private String preOrder(TreeNode root, List<TreeNode> list, Map<String, Integer> map) {
        if (Objects.isNull(root)) {
            return "";
        }

        String route =
                root.val
                        + ","
                        + preOrder(root.left, list, map)
                        + ","
                        + preOrder(root.right, list, map);
        int val = root.val;
        Integer num = map.get(route);
        if (Objects.nonNull(num)) {
            if (num == 1) {
                list.add(root);
            }

            map.put(route, num + 1);
        } else {
            map.put(route, 1);
        }
        return route;
    }

    @Test
    public void isAnagram() {
        String s = "anagram", t = "nagaram";
        boolean result = isAnagram(s, t);
        log.debug("result:{}", result);
    }

    /**
     * 有效的字母异位词 给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的一个字母异位词。
     *
     * <p>示例 1:
     *
     * <p>输入: s = "anagram", t = "nagaram" 输出: true 示例 2:
     *
     * <p>输入: s = "rat", t = "car" 输出: false 说明: 你可以假设字符串只包含小写字母。
     *
     * <p>进阶: 如果输入字符串包含 unicode 字符怎么办？你能否调整你的解法来应对这种情况？
     *
     * @param s
     * @param t
     * @return
     */
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        // 解法2: 用大小为26位的数组大小存放字符串s中字母出现的次数，比较s ，t中字母出现的次数。
        int[] array = new int[26];
        int size = s.length();
        for (int i = 0; i < size; i++) {
            char char1 = s.charAt(i);
            char char2 = t.charAt(i);
            // s的个数++
            array[char1 - 'a']++;
            // t的个数--
            array[char2 - 'a']--;
        }

        for (int num : array) {
            if (num != 0) {
                return false;
            }
        }
        return true;

        /*int size = s.length()/3*4 +1;

        char[] char1 = s.toCharArray();
        Map<Character,Integer> map = new HashMap<>(size);
        for (char c : char1) {
            Integer count = map.get(c);
            if (Objects.isNull(count)) {
                map.put(c,1);
            } else {
                map.put(c,count+1);
            }
        }
        char[] char2 = t.toCharArray();
        for (char c : char2) {
            Integer count = map.get(c);
            if (Objects.isNull(count)) {
                return false;
            } else if (count == 1){
                map.remove(c);
            } else {
                map.put(c,count-1);
            }
        }

        if (map.isEmpty()) {
            return true;
        }


        return false;*/

    }

    @Test
    public void frequencySort() {
        String s = "Aabb";

        String result = frequencySort(s);
        log.debug("result:{}", result);
    }

    /**
     * 根据字符出现频率排序 给定一个字符串，请将字符串里的字符按照出现的频率降序排列。
     *
     * <p>示例 1:
     *
     * <p>输入: "tree"
     *
     * <p>输出: "eert"
     *
     * <p>解释: 'e'出现两次，'r'和't'都只出现一次。 因此'e'必须出现在'r'和't'之前。此外，"eetr"也是一个有效的答案。 示例 2:
     *
     * <p>输入: "cccaaa"
     *
     * <p>输出: "cccaaa"
     *
     * <p>解释: 'c'和'a'都出现三次。此外，"aaaccc"也是有效的答案。 注意"cacaca"是不正确的，因为相同的字母必须放在一起。 示例 3:
     *
     * <p>输入: "Aabb"
     *
     * <p>输出: "bbAa"
     *
     * <p>解释: 此外，"bbaA"也是一个有效的答案，但"Aabb"是不正确的。 注意'A'和'a'被认为是两种不同的字符。
     *
     * @param s
     * @return
     */
    public String frequencySort(String s) {
        char[] chars = s.toCharArray();
        Map<Character, Integer> map = new HashMap<>();
        for (char c : chars) {
            if (map.containsKey(c)) {
                map.put(c, map.get(c) + 1);
            } else {
                map.put(c, 1);
            }
        }

        Character[] keys = new Character[map.size()];
        int index = -1;
        // 遍历顺便排序
        for (Character c : map.keySet()) {
            index++;
            int num = map.get(c);
            if (index == 0) {
                keys[index] = c;
            } else {
                // 向后遍历，找到合适的位置，后面的元素后移一位
                int currIndex = index;
                for (int i = 0; i < index; i++) {
                    int count = map.get(keys[i]);
                    if (num > count) {
                        currIndex = i;
                        break;
                    }
                }

                for (int i = index; i > currIndex; i--) {
                    keys[i] = keys[i - 1];
                }
                keys[currIndex] = c;
            }
        }
        StringBuilder sb = new StringBuilder();
        for (char key : keys) {
            int count = map.get(key);
            int num = 0;
            while (num < count) {
                sb.append(key);
                num++;
            }
        }

        return sb.toString();
    }

    @Test
    public void threeSum() {
        int[] nums = {0, 0, 0, 0};
        List<List<Integer>> result = threeSum(nums);
        for (List<Integer> list : result) {
            log.debug("result:{}", list);
        }
    }

    /**
     * 三数之和 给定一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？找出所有满足条件且不重复的三元组。
     *
     * <p>注意：答案中不可以包含重复的三元组。
     *
     * <p>例如, 给定数组 nums = [-1, 0, 1, 2, -1, -4]，
     *
     * <p>满足要求的三元组集合为： [ [-1, 0, 1], [-1, -1, 2] ]
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        // 先将数组 nums 排序
        Arrays.sort(nums);

        // 选取主元 作为中间元素，然后 左右两边遍历
        /*for (int i = 1; i < nums.length - 1; i++) {
            int num = nums[i];
            int left = 0,right = nums.length - 1;
            while (left < i && right > i) {
                int sum = nums[left] + nums[right] + num;
                if (sum == 0) {
                    List<Integer> list = new ArrayList<>();
                    list.add(nums[left]);
                    list.add(num);
                    list.add(nums[right]);
                    result.add(list);

                }
                // 当= 0时 让left 前进 如果left 和后一位相等，跳过，不能重复
                if (sum <= 0 && left < i) {
                    while (left < i && nums[left] == nums[++left] ) {

                    }
                } else if (sum > 0 && right > i)  {
                    while (right > i && nums[right] == nums[--right] ) {

                    }
                } else {
                    break;
                }

            }
        }*/

        // 优化
        // 选取左边的元素主元  ，然后 前后两边遍历
        for (int i = 0; i < nums.length - 2; i++) {
            int num = nums[i];
            // 最小的元素 > 0 sum肯定大于0
            if (num > 0) {
                break;
            }
            // 如果 num 和前一位相等，跳过
            if (i > 0 && num == nums[i - 1]) {
                continue;
            }
            int left = i + 1, right = nums.length - 1;
            while (left < right) {
                // 最大元素  < 0 sum肯定小于0
                if (nums[right] < 0) {
                    break;
                }
                int sum = nums[left] + nums[right] + num;
                if (sum == 0) {
                    List<Integer> list = new ArrayList<>();
                    list.add(nums[left]);
                    list.add(num);
                    list.add(nums[right]);
                    result.add(list);
                }
                // 当= 0时 让left 前进 如果left 和后一位相等，跳过，不能重复
                if (sum <= 0) {
                    while (left < right && nums[left] == nums[++left]) {}

                } else {
                    while (left < right && nums[right] == nums[--right]) {}
                }
            }
        }

        return result;
    }

    @Test
    public void fourSum() {
        int[] nums = {-1, 0, -5, -2, -2, -4, 0, 1, -2};
        int target = -9;
        List<List<Integer>> result = fourSum(nums, target);
        for (List<Integer> list : result) {
            log.debug("result:{}", list);
        }
    }

    /**
     * 四数之和 给定一个包含 n 个整数的数组 nums 和一个目标值 target，判断 nums 中是否存在四个元素 a，b，c 和 d ，使得 a + b + c + d 的值与
     * target 相等？找出所有满足条件且不重复的四元组。
     *
     * <p>注意：
     *
     * <p>答案中不可以包含重复的四元组。
     *
     * <p>示例：
     *
     * <p>给定数组 nums = [1, 0, -1, 0, -2, 2]，和 target = 0。
     *
     * <p>满足要求的四元组集合为： [ [-1, 0, 0, 1], [-2, -1, 1, 2], [-2, 0, 0, 2] ]
     *
     * @param nums
     * @param target
     * @return
     */
    public List<List<Integer>> fourSum(int[] nums, int target) {

        List<List<Integer>> result = new ArrayList<>();
        // 先将数组 nums 排序
        Arrays.sort(nums);

        int n = nums.length;
        // 使用双循环固定两个数，用双指针找另外两个数，通过比较与target 的大小，移动指针。

        for (int i = 0; i < n - 3; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            if (nums[i] + nums[i + 1] + nums[i + 2] + nums[i + 3] > target) break;
            if (nums[i] + nums[n - 1] + nums[n - 2] + nums[n - 3] < target) continue;
            for (int j = i + 1; j < n - 2; j++) {
                if (j - i > 1 && nums[j] == nums[j - 1]) continue;
                if (nums[i] + nums[j] + nums[j + 1] + nums[j + 2] > target) break;
                if (nums[i] + nums[j] + nums[n - 1] + nums[n - 2] < target) continue;
                int num = nums[i] + nums[j];

                int left = j + 1, right = n - 1;
                while (left < right) {
                    int sum = nums[left] + nums[right] + num;
                    if (sum == target) {
                        List<Integer> list = new ArrayList<>();
                        list.add(nums[i]);
                        list.add(nums[j]);
                        list.add(nums[left]);
                        list.add(nums[right]);
                        result.add(list);
                        // left 和left+1 相等
                        while (left < right && nums[left] == nums[left + 1]) left++;
                        // right和right-1 相等
                        while (left < right && nums[right] == nums[right - 1]) right--;
                        left++;
                        right--;

                    } else if (sum < target) {
                        left++;
                    } else {
                        right--;
                    }
                }
            }
        }

        return result;
    }

    @Test
    public void fourSumCount() {
        int[] A = {-1, -1};
        int[] B = {-1, 1};
        int[] C = {-1, 1};
        int[] D = {1, -1};
        int result = fourSumCount(A, B, C, D);
        log.debug("result:{}", result);
    }

    /**
     * 四数相加 II 给定四个包含整数的数组列表 A , B , C , D ,计算有多少个元组 (i, j, k, l) ，使得 A[i] + B[j] + C[k] + D[l] = 0。
     *
     * <p>为了使问题简单化，所有的 A, B, C, D 具有相同的长度 N，且 0 ≤ N ≤ 500 。所有整数的范围在 -228 到 228 - 1 之间，最终结果不会超过 231 -
     * 1 。
     *
     * <p>例如:
     *
     * <p>输入: A = [ 1, 2] B = [-2,-1] C = [-1, 2] D = [ 0, 2]
     *
     * <p>输出: 2
     *
     * <p>解释: 两个元组如下: 1. (0, 0, 0, 1) -> A[0] + B[0] + C[0] + D[1] = 1 + (-2) + (-1) + 2 = 0 2. (1,
     * 1, 0, 0) -> A[1] + B[1] + C[0] + D[0] = 2 + (-1) + (-1) + 0 = 0
     *
     * @param A
     * @param B
     * @param C
     * @param D
     * @return
     */
    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        int result = 0;
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < B.length; j++) {
                int num = A[i] + B[j];
                if (map.containsKey(num)) {
                    map.put(num, map.get(num) + 1);
                } else {
                    map.put(num, 1);
                }
            }
        }

        for (int i = 0; i < C.length; i++) {
            for (int j = 0; j < D.length; j++) {
                int num = 0 - C[i] - D[j];
                if (map.containsKey(num)) {
                    result += map.get(num);
                }
            }
        }

        return result;
    }

    @Test
    public void numberOfBoomerangs() {
        int[][] points = {{0, 0}, {1, 0}, {2, 0}};
        int result = numberOfBoomerangs(points);
        log.debug("result:{}", result);
    }

    /**
     * 回旋镖的数量 给定平面上 n 对不同的点，“回旋镖” 是由点表示的元组 (i, j, k) ，其中 i 和 j 之间的距离和 i 和 k 之间的距离相等（需要考虑元组的顺序）。
     *
     * <p>找到所有回旋镖的数量。你可以假设 n 最大为 500，所有点的坐标在闭区间 [-10000, 10000] 中。
     *
     * <p>示例:
     *
     * <p>输入: [[0,0],[1,0],[2,0]]
     *
     * <p>输出: 2
     *
     * <p>解释: 两个回旋镖为 [[1,0],[0,0],[2,0]] 和 [[1,0],[2,0],[0,0]]
     *
     * @param points
     * @return
     */
    public int numberOfBoomerangs(int[][] points) {
        int result = 0;

        for (int i = 0; i < points.length; i++) {
            Map<Integer, Integer> map = new HashMap<>();
            for (int j = 0; j < points.length; j++) {
                if (i != j) {
                    int distance = getDistanceTwoPoints(points[i], points[j]);
                    if (map.containsKey(distance)) {
                        // 存在num个点距离为distance，然后考虑顺序 个数为2*num
                        int num = map.get(distance);
                        map.put(distance, num + 1);
                        result += 2 * num;
                    } else {
                        map.put(distance, 1);
                    }
                }
            }
        }

        return result;
    }

    /**
     * 获取两点的距离
     *
     * @param point1
     * @param point2
     */
    private int getDistanceTwoPoints(int[] point1, int[] point2) {
        int x1 = point1[0], x2 = point2[0];
        int x = x1 > x2 ? x1 - x2 : x2 - x1;

        int y1 = point1[1], y2 = point2[1];
        int y = y1 > y2 ? y1 - y2 : y2 - y1;

        return x * x + y * y;
    }

    @Test
    public void maxPoints() {
        // int[][] points   = {{1,1},{3,2},{5,3},{4,1},{2,3},{1,4}};
        int[][] points = {
            {0, 9},
            {138, 429},
            {115, 359},
            {115, 359},
            {-30, -102},
            {230, 709},
            {-150, -686},
            {-135, -613},
            {-60, -248},
            {-161, -481},
            {207, 639},
            {23, 79},
            {-230, -691},
            {-115, -341},
            {92, 289},
            {60, 336},
            {-105, -467},
            {135, 701},
            {-90, -394},
            {-184, -551},
            {150, 774}
        };
        int result = maxPoints(points);
        log.debug("result:{}", result);
    }

    /**
     * 直线上最多的点数 给定一个二维平面，平面上有 n 个点，求最多有多少个点在同一条直线上。
     *
     * <p>示例 1:
     *
     * <p>输入: [[1,1],[2,2],[3,3]] 输出: 3 解释: ^ | | o | o | o +-------------> 0 1 2 3 4 示例 2:
     *
     * <p>输入: [[1,1],[3,2],[5,3],[4,1],[2,3],[1,4]] 输出: 4 解释: ^ | | o | o o | o | o o
     * +-------------------> 0 1 2 3 4 5 6
     *
     * @param points
     * @return
     */
    public int maxPoints(int[][] points) {

        if (points.length <= 2) {
            return points.length;
        }

        int max = 0;
        for (int i = 0; i < points.length; i++) {
            int same = 1;
            for (int j = i + 1; j < points.length; j++) {

                int count = 0;
                if (points[i][0] == points[j][0] && points[i][1] == points[j][1]) {
                    // i、j 是重复点s
                    same++;
                } else {
                    count++;
                    for (int k = j + 1; k < points.length; k++) {
                        if (isLineThreePoints(points[i], points[j], points[k])) {
                            count++;
                        }
                    }
                }
                // 最大值比较
                max = Math.max(max, count + same);
            }
        }

        return max;
    }

    /**
     * 判断3点是否直线
     *
     * @param point1
     * @param point2
     * @return
     */
    private boolean isLineThreePoints(int[] point1, int[] point2, int[] point3) {
        long x1 = point1[0], x2 = point2[0], x3 = point3[0];

        long y1 = point1[1], y2 = point2[1], y3 = point3[1];

        //  x2-x1/y2-y1 = x3-x1/y3-y1 -> (x2-x1)(y3-y1) == (y2-y1)(x3-x1)

        if ((x2 - x1) * (y3 - y1) == (y2 - y1) * (x3 - x1)) {
            return true;
        }
        return false;
    }

    @Test
    public void topKFrequent() {
        int[] nums = {1, 1, 1, 2, 2, 3};
        int k = 2;
        List<Integer> result = topKFrequent(nums, k);
        log.debug("result:{}", result);
    }

    /**
     * 前K个高频元素 给定一个非空的整数数组，返回其中出现频率前 k 高的元素。
     *
     * <p>示例 1:
     *
     * <p>输入: nums = [1,1,1,2,2,3], k = 2 输出: [1,2] 示例 2:
     *
     * <p>输入: nums = [1], k = 1 输出: [1] 说明：
     *
     * <p>你可以假设给定的 k 总是合理的，且 1 ≤ k ≤ 数组中不相同的元素的个数。 你的算法的时间复杂度必须优于 O(n log n) , n 是数组的大小。
     *
     * @param nums
     * @param k
     * @return
     */
    public List<Integer> topKFrequent(int[] nums, int k) {
        Integer[] topK = new Integer[k];

        Map<Integer, Integer> map = new HashMap<>(16);

        for (int num : nums) {
            if (map.containsKey(num)) {
                map.put(num, map.get(num) + 1);
            } else {
                map.put(num, 1);
            }
        }
        int index = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            Integer key = entry.getKey();
            if (topK[0] == null) {
                topK[0] = key;
                continue;
            }
            int count = map.get(key);
            index = 0;
            // 排序
            while (index < k && Objects.nonNull(topK[index])) {
                if (count > map.get(topK[index])) {
                    break;
                }

                index++;
            }
            if (index == k) {
                continue;
            }
            Integer tmp = topK[index];
            for (int i = index; i < k - 1; i++) {
                if (Objects.isNull(tmp)) {
                    break;
                }
                Integer a = topK[i + 1];
                topK[i + 1] = tmp;
                tmp = a;
            }
            topK[index] = key;
        }
        List<Integer> result = new ArrayList<>();
        for (int top : topK) {
            result.add(top);
        }

        return result;
    }

    @Test
    public void randomHashTest() {

        // 初始化一个空的集合。
        RandomizedSet randomSet = new RandomizedSet();
        int index = 0;
        // 向集合中插入 1 。返回 true 表示 1 被成功地插入。
        log.debug("{}:{}", index++, randomSet.insert(1));

        // 返回 false ，表示集合中不存在 2 。
        log.debug("{}:{}", index++, randomSet.remove(2));

        // 向集合中插入 2 。返回 true 。集合现在包含 [1,2] 。
        log.debug("{}:{}", index++, randomSet.insert(2));

        // getRandom 应随机返回 1 或 2 。
        log.debug("{}:{}", index++, randomSet.getRandom());

        // 从集合中移除 1 ，返回 true 。集合现在包含 [2] 。
        log.debug("{}:{}", index++, randomSet.remove(1));

        // 2 已在集合中，所以返回 false 。
        log.debug("{}:{}", index++, randomSet.insert(2));

        // 由于 2 是集合中唯一的数字，getRandom 总是返回 2 。
        log.debug("{}:{}", index++, randomSet.getRandom());
    }

    @Test
    public void wordPattern() {
        String pattern = "abba", str = "dog cat cat dog";

        logResult(wordPattern(pattern, str));
    }

    /**
     * 给定一种规律 pattern 和一个字符串 str ，判断 str 是否遵循相同的规律。
     *
     * <p>这里的 遵循 指完全匹配，例如， pattern 里的每个字母和字符串 str 中的每个非空单词之间存在着双向连接的对应规律。
     *
     * <p>示例1:
     *
     * <p>输入: pattern = "abba", str = "dog cat cat dog" 输出: true 示例 2:
     *
     * <p>输入:pattern = "abba", str = "dog cat cat fish" 输出: false 示例 3:
     *
     * <p>输入: pattern = "aaaa", str = "dog cat cat dog" 输出: false 示例 4:
     *
     * <p>输入: pattern = "abba", str = "dog dog dog dog" 输出: false 说明:
     * 你可以假设 pattern 只包含小写字母， str 包含了由单个空格分隔的小写字母。
     *
     * @param pattern
     * @param str
     * @return
     */
    public boolean wordPattern(String pattern, String str) {
        int len = pattern.length();
        String[] strs = str.split(" ");
        if (len != strs.length) {
            return false;
        }
        Map<Character, String> charMap = new HashMap<>();
        Map<String, Character> strMap = new HashMap<>();
        for (int i = 0; i < len; i++) {
            char c = pattern.charAt(i);
            String s = strs[i];
            String s2 = charMap.get(c);
            if (s2 != null && !Objects.equals(s, s2)) {
                return false;
            }
            charMap.put(c, s);
            Character c2 = strMap.get(s);
            if (c2 != null && !Objects.equals(c, c2)) {
                return false;
            }
            strMap.put(s, c);
        }
        return true;
    }

    @Test
    public void getHint() {
        String secret = "1123", guess = "0111";
        logResult(getHint(secret, guess));
    }

    /**
     * 你正在和你的朋友玩 猜数字（Bulls and Cows）游戏：你写下一个数字让你的朋友猜。每次他猜测后，你给他一个提示，告诉他有多少位数字和确切位置都猜对了（称为“Bulls”,
     * 公牛），有多少位数字猜对了但是位置不对（称为“Cows”, 奶牛）。你的朋友将会根据提示继续猜，直到猜出秘密数字。
     *
     * <p>请写出一个根据秘密数字和朋友的猜测数返回提示的函数，用 A 表示公牛，用 B 表示奶牛。
     *
     * <p>请注意秘密数字和朋友的猜测数都可能含有重复数字。
     *
     * <p>示例 1:
     *
     * <p>输入: secret = "1807", guess = "7810"
     *
     * <p>输出: "1A3B"
     *
     * <p>解释: 1 公牛和 3 奶牛。公牛是 8，奶牛是 0, 1 和 7。 示例 2:
     *
     * <p>输入: secret = "1123", guess = "0111"
     *
     * <p>输出: "1A1B"
     *
     * <p>解释: 朋友猜测数中的第一个 1 是公牛，第二个或第三个 1 可被视为奶牛。 说明: 你可以假设秘密数字和朋友的猜测数都只包含数字，并且它们的长度永远相等。
     *
     * @param secret
     * @param guess
     * @return
     */
    public String getHint(String secret, String guess) {
        StringBuilder sb = new StringBuilder();
        int[] bucket = new int[10];
        int count1 = 0, count2 = 0;
        for (char c : secret.toCharArray()) {
            bucket[c - '0']++;
        }
        int len = secret.length();
        for (int i = 0; i < len; i++) {
            char c = guess.charAt(i);
            if (secret.charAt(i) == c) {
                count1++;
            }
            if (bucket[c - '0']-- > 0) {
                count2++;
            }
        }

        sb.append(count1).append('A').append(count2 - count1).append('B');
        return sb.toString();
    }

    public String getHintHash(String secret, String guess) {
        StringBuilder sb = new StringBuilder();

        Map<Character, Integer> map = new HashMap<>();
        for (char c : secret.toCharArray()) {
            int count = map.getOrDefault(c, 0);
            map.put(c, count + 1);
        }
        int len = secret.length();
        int count1 = 0, count2 = 0;
        for (int i = 0; i < len; i++) {
            char c = guess.charAt(i);
            if (secret.charAt(i) == c) {
                count1++;
            }
            Integer count = map.get(c);
            if (count != null && count > 0) {
                map.put(c, count - 1);
                count2++;
            }
        }

        sb.append(count1).append('A').append(count2 - count1).append('B');
        return sb.toString();
    }

    @Test
    public void longestPalindrome() {
        String s = "zeusnilemacaronimaisanitratetartinasiaminoracamelinsuez";
        logResult(longestPalindrome(s));
    }

    /**
     * 409. 最长回文串 给定一个包含大写字母和小写字母的字符串，找到通过这些字母构造成的最长的回文串。
     *
     * <p>在构造过程中，请注意区分大小写。比如 "Aa" 不能当做一个回文字符串。
     *
     * <p>注意: 假设字符串的长度不会超过 1010。
     *
     * <p>示例 1:
     *
     * <p>输入: "abccccdd"
     *
     * <p>输出: 7
     *
     * <p>解释: 我们可以构造的最长的回文串是"dccaccd", 它的长度是 7。
     *
     * @param s
     * @return
     */
    public int longestPalindrome(String s) {
        int len = 0;

        // Map<Character,Integer> map = new HashMap<>();
        int[] counts = new int[52];
        // 偶数个的字符全取,
        // 奇数个的字符 全部 - 1  最后 + 1
        for (char c : s.toCharArray()) {
            /*int count = map.getOrDefault(c,0);
            map.put(c,++count);*/
            if (c >= 'a') {
                counts[c - 'a' + 26]++;
            } else {
                counts[c - 'A']++;
            }
        }

        // 是否有奇数
        boolean flag = false;

        for (int num : counts) {
            // 奇数
            if ((num & 1) == 1) {
                flag = true;
                len += (num - 1);
            } else {
                len += num;
            }
        }
        if (flag) {
            len++;
        }
        return len;
    }

    @Test
    public void longestWord() {
        String[] words = {"a", "banana", "app", "appl", "ap", "apply", "apple"};
        logResult(longestWord(words));
    }

    /**
     * 720. 词典中最长的单词
     * 给出一个字符串数组words组成的一本英语词典。从中找出最长的一个单词，该单词是由words词典中其他单词逐步添加一个字母组成。若其中有多个可行的答案，则返回答案中字典序最小的单词。
     *
     * <p>若无答案，则返回空字符串。
     *
     * <p>示例 1:
     *
     * <p>输入: words = ["w","wo","wor","worl", "world"] 输出: "world" 解释: 单词"world"可由"w", "wo", "wor",
     * 和 "worl"添加一个字母组成。 示例 2:
     *
     * <p>输入: words = ["a", "banana", "app", "appl", "ap", "apply", "apple"] 输出: "apple" 解释:
     * "apply"和"apple"都能由词典中的单词组成。但是"apple"得字典序小于"apply"。 注意:
     *
     * <p>所有输入的字符串都只包含小写字母。 words数组长度范围为[1,1000]。 words[i]的长度范围为[1,30]。
     *
     * @param words
     * @return
     */
    public String longestWord(String[] words) {
        // 前缀树
        Trie trie = new Trie();
        for (String word : words) {
            trie.insert(word);
        }

        return trie.longestWord();
    }

    @Test
    public void shortestCompletingWord() {
        String licensePlate = "1s3 456";
        String[] words = {"looks", "pest", "stew", "show"};
        logResult(shortestCompletingWord(licensePlate, words));
    }
    /**
     * 748. 最短完整词 如果单词列表（words）中的一个单词包含牌照（licensePlate）中所有的字母，那么我们称之为完整词。在所有完整词中，最短的单词我们称之为最短完整词。
     *
     * <p>单词在匹配牌照中的字母时不区分大小写，比如牌照中的 "P" 依然可以匹配单词中的 "p" 字母。
     *
     * <p>我们保证一定存在一个最短完整词。当有多个单词都符合最短完整词的匹配条件时取单词列表中最靠前的一个。
     *
     * <p>牌照中可能包含多个相同的字符，比如说：对于牌照 "PP"，单词 "pair" 无法匹配，但是 "supper" 可以匹配。
     *
     * <p>示例 1：
     *
     * <p>输入：licensePlate = "1s3 PSt", words = ["step", "steps", "stripe", "stepple"] 输出："steps"
     * 说明：最短完整词应该包括 "s"、"p"、"s" 以及 "t"。对于 "step" 它只包含一个 "s" 所以它不符合条件。同时在匹配过程中我们忽略牌照中的大小写。
     *
     * <p>示例 2：
     *
     * <p>输入：licensePlate = "1s3 456", words = ["looks", "pest", "stew", "show"] 输出："pest" 说明：存在 3
     * 个包含字母 "s" 且有着最短长度的完整词，但我们返回最先出现的完整词。
     *
     * <p>注意:
     *
     * <p>牌照（licensePlate）的长度在区域[1, 7]中。 牌照（licensePlate）将会包含数字、空格、或者字母（大写和小写）。 单词列表（words）长度在区间
     * [10, 1000] 中。 每一个单词 words[i] 都是小写，并且长度在区间 [1, 15] 中。
     *
     * @param licensePlate
     * @param words
     * @return
     */
    public String shortestCompletingWord(String licensePlate, String[] words) {
        int[] letters = new int[26];
        for (char c : licensePlate.toCharArray()) {
            if (c >= 'a' && c <= 'z') {
                letters[c - 'a']++;
            }
            if (c >= 'A' && c <= 'Z') {
                letters[c - 'A']++;
            }
        }

        String result = "";

        for (String word : words) {

            if (compareWord(letters, word)
                    && (result.length() == 0 || word.length() < result.length())) {
                result = word;
            }
        }
        return result;
    }

    private boolean compareWord(int[] letters, String word) {
        int[] wordLetters = new int[26];
        for (char c : word.toCharArray()) {
            wordLetters[c - 'a']++;
        }
        for (int i = 0; i < letters.length; i++) {
            if (letters[i] > wordLetters[i]) {
                return false;
            }
        }
        return true;
    }

    /**
     * 500. 键盘行 给定一个单词列表，只返回可以使用在键盘同一行的字母打印出来的单词。键盘如下图所示。
     *
     * <p>American keyboard
     *
     * <p>示例：
     *
     * <p>输入: ["Hello", "Alaska", "Dad", "Peace"] 输出: ["Alaska", "Dad"]
     *
     * <p>注意：
     *
     * <p>你可以重复使用键盘上同一字符。 你可以假设输入的字符串将只包含字母。
     *
     * @param words
     * @return
     */
    public String[] findWords(String[] words) {
        char[] key1 = {'q', 'w', 'e', 'r', 't', 'y', 'u', 'i', 'o', 'p'};
        char[] key2 = {'a', 's', 'd', 'f', 'g', 'h', 'j', 'k', 'l'};
        char[] key3 = {'z', 'x', 'c', 'v', 'b', 'n', 'm'};
        Map<Character, Integer> indexMap = new HashMap<>();
        for (char c : key1) {
            indexMap.put(c, 1);
            char bigC = (char) (c - 'a' + 'A');
            indexMap.put(bigC, 1);
        }
        for (char c : key2) {
            indexMap.put(c, 2);
            char bigC = (char) (c - 'a' + 'A');
            indexMap.put(bigC, 2);
        }
        for (char c : key3) {
            indexMap.put(c, 3);
            char bigC = (char) (c - 'a' + 'A');
            indexMap.put(bigC, 3);
        }

        List<String> list = new ArrayList<>();

        for (String word : words) {
            int index = indexMap.get(word.charAt(0));
            boolean flag = true;
            for (int i = 1; i < word.length(); i++) {
                char c = word.charAt(i);
                if (index != indexMap.get(c)) {
                    flag = false;
                    break;
                }
            }

            if (flag) {
                list.add(word);
            }
        }

        String[] result = new String[list.size()];
        list.toArray(result);
        return result;
    }

    /**
     * 575. 分糖果 给定一个偶数长度的数组，其中不同的数字代表着不同种类的糖果，每一个数字代表一个糖果。你需要把这些糖果平均分给一个弟弟和一个妹妹。返回妹妹可以获得的最大糖果的种类数。
     *
     * <p>示例 1:
     *
     * <p>输入: candies = [1,1,2,2,3,3] 输出: 3 解析: 一共有三种种类的糖果，每一种都有两个。
     * 最优分配方案：妹妹获得[1,2,3],弟弟也获得[1,2,3]。这样使妹妹获得糖果的种类数最多。 示例 2 :
     *
     * <p>输入: candies = [1,1,2,3] 输出: 2 解析:
     * 妹妹获得糖果[2,3],弟弟获得糖果[1,1]，妹妹有两种不同的糖果，弟弟只有一种。这样使得妹妹可以获得的糖果种类数最多。 注意:
     *
     * <p>数组的长度为[2, 10,000]，并且确定为偶数。 数组中数字的大小在范围[-100,000, 100,000]内。
     *
     * @param candies
     * @return
     */
    public int distributeCandies(int[] candies) {
        int len = candies.length;

        int num = len >> 1;
        Set<Integer> set = new HashSet<>();
        for (int candy : candies) {
            set.add(candy);
        }
        return Math.min(num, set.size());
    }

    /**
     * 594. 最长和谐子序列 和谐数组是指一个数组里元素的最大值和最小值之间的差别正好是1。
     *
     * <p>现在，给定一个整数数组，你需要在所有可能的子序列中找到最长的和谐子序列的长度。
     *
     * <p>示例 1:
     *
     * <p>输入: [1,3,2,2,5,2,3,7] 输出: 5 原因: 最长的和谐数组是：[3,2,2,2,3]. 说明: 输入的数组长度最大不超过20,000.
     *
     * @param nums
     * @return
     */
    public int findLHS(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            int count = map.getOrDefault(num, 0);
            map.put(num, count + 1);
        }
        int result = 0;
        for (Integer key : map.keySet()) {
            if (map.containsKey(key + 1)) {
                int count = map.get(key) + map.get(key + 1);
                if (count > result) {
                    result = count;
                }
            }
        }
        return result;
    }

    /**
     * 690. 员工的重要性 给定一个保存员工信息的数据结构，它包含了员工唯一的id，重要度 和 直系下属的id。
     *
     * <p>比如，员工1是员工2的领导，员工2是员工3的领导。他们相应的重要度为15, 10, 5。那么员工1的数据结构是[1, 15, [2]]，员工2的数据结构是[2, 10,
     * [3]]，员工3的数据结构是[3, 5, []]。注意虽然员工3也是员工1的一个下属，但是由于并不是直系下属，因此没有体现在员工1的数据结构中。
     *
     * <p>现在输入一个公司的所有员工信息，以及单个员工id，返回这个员工和他所有下属的重要度之和。
     *
     * <p>示例 1:
     *
     * <p>输入: [[1, 5, [2, 3]], [2, 3, []], [3, 3, []]], 1 输出: 11 解释:
     * 员工1自身的重要度是5，他有两个直系下属2和3，而且2和3的重要度均为3。因此员工1的总重要度是 5 + 3 + 3 = 11。 注意:
     *
     * <p>一个员工最多有一个直系领导，但是可以有多个直系下属 员工数量不超过2000。
     *
     * @param employees
     * @param id
     * @return
     */
    public int getImportance(List<Employee> employees, int id) {
        Map<Integer, Employee> map = new HashMap<>();
        for (Employee employee : employees) {
            map.put(employee.id, employee);
        }

        return getImportance(map, id);
    }

    private int getImportance(Map<Integer, Employee> map, int id) {
        Employee leader = map.get(id);
        if (leader == null) {
            return 0;
        }
        int result = leader.importance;
        List<Integer> followers = leader.subordinates;
        for (Integer followerId : followers) {
            result += getImportance(map, followerId);
        }
        return result;
    }

    @Test
    public void subdomainVisits() {
        String[] cpdomains = {
            "900 google.mail.com", "50 yahoo.com", "1 intel.mail.com", "5 wiki.org"
        };
        logResult(subdomainVisits(cpdomains));
    }

    /**
     * 811. 子域名访问计数 一个网站域名，如"discuss.leetcode.com"，包含了多个子域名。作为顶级域名，常用的有"com"，
     * 下一级则有"leetcode.com"，最低的一级为"discuss.leetcode.com"。当我们访问域名"discuss.leetcode.com"时，
     * 也同时访问了其父域名"leetcode.com"以及顶级域名 "com"。
     *
     * <p>给定一个带访问次数和域名的组合，要求分别计算每个域名被访问的次数。其格式为访问次数+空格+地址， 例如："9001 discuss.leetcode.com"。
     *
     * <p>接下来会给出一组访问次数和域名组合的列表cpdomains 。要求解析出所有域名的访问次数，输出格式和输入格式相同，不限定先后顺序。
     *
     * <p>示例 1: 输入: ["9001 discuss.leetcode.com"] 输出: ["9001 discuss.leetcode.com", "9001
     * leetcode.com", "9001 com"] 说明:
     * 例子中仅包含一个网站域名："discuss.leetcode.com"。按照前文假设，子域名"leetcode.com"和"com"都会被访问，所以它们都被访问了9001次。 示例 2
     * 输入: ["900 google.mail.com", "50 yahoo.com", "1 intel.mail.com", "5 wiki.org"] 输出: ["901
     * mail.com","50 yahoo.com","900 google.mail.com","5 wiki.org","5 org","1 intel.mail.com","951
     * com"] 说明: 按照假设，会访问"google.mail.com" 900次，"yahoo.com" 50次，"intel.mail.com" 1次，"wiki.org" 5次。
     * 而对于父域名，会访问"mail.com" 900+1 = 901次，"com" 900 + 50 + 1 = 951次，和 "org" 5 次。 注意事项：
     *
     * <p>cpdomains 的长度小于 100。 每个域名的长度小于100。 每个域名地址包含一个或两个"."符号。 输入中任意一个域名的访问次数都小于10000。
     *
     * @param cpdomains
     * @return
     */
    public List<String> subdomainVisits(String[] cpdomains) {
        List<String> result = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>();

        for (String cpdomain : cpdomains) {
            /*String[] a = cpdomain.split(" ");
            int count = Integer.valueOf(a[0]);
            String addr = a[1];
            int addrCount = map.getOrDefault(addr,0);
            map.put(addr,addrCount + count);
            // addr.split(".");
            // 每个域名地址包含一个或两个"."符号。
            int firstIndex = addr.indexOf(".");
            String key1 = addr.substring(firstIndex + 1);
            int count1 = map.getOrDefault(key1 ,0);

            map.put(key1,count1 + count);

            int secondIndex = addr.indexOf(".",firstIndex + 1);
            if (secondIndex != -1) {
                String key2 = addr.substring(secondIndex + 1);
                int count2 = map.getOrDefault(key2 ,0);
                map.put(key2,count2 + count);
            }*/
            int index = cpdomain.indexOf(' ');
            int count = Integer.valueOf(cpdomain.substring(0, index));

            while (index != -1) {
                String key = cpdomain.substring(index + 1);
                int value = map.getOrDefault(key, 0);
                map.put(key, count + value);
                // 每个域名地址包含一个或两个"."符号。
                index = cpdomain.indexOf('.', index + 1);
            }
        }
        map.forEach(
                (k, v) -> {
                    StringBuilder sb = new StringBuilder();
                    sb.append(v).append(" ").append(k);
                    result.add(sb.toString());
                });
        return result;
    }

    @Test
    public void commonChars() {
        String[] A = {"cool", "lock", "cook"};
        logResult(commonChars(A));
    }

    /**
     * 1002. 查找常用字符 给定仅有小写字母组成的字符串数组 A，返回列表中的每个字符串中都显示的全部字符（包括重复字符）组成的列表。 例如，如果一个字符在每个字符串中出现 3 次，但不是
     * 4 次，则需要在最终答案中包含该字符 3 次。
     *
     * <p>你可以按任意顺序返回答案。
     *
     * <p>示例 1：
     *
     * <p>输入：["bella","label","roller"] 输出：["e","l","l"] 示例 2：
     *
     * <p>输入：["cool","lock","cook"] 输出：["c","o"]
     *
     * <p>提示：
     *
     * <p>1 <= A.length <= 100 1 <= A[i].length <= 100 A[i][j] 是小写字母
     *
     * @param A
     * @return
     */
    public List<String> commonChars(String[] A) {
        List<String> list = new ArrayList<>();
        int len = A.length;
        if (len == 1) {
            for (char c : A[0].toCharArray()) {
                list.add(String.valueOf(c));
            }
            return list;
        }

        int[] letter1 = new int[26];
        String s = A[0];
        for (char c : s.toCharArray()) {
            letter1[c - 'a']++;
        }

        int[] letter2 = new int[26];

        for (int i = 1; i < len; i++) {
            String str = A[i];
            for (char c : str.toCharArray()) {
                letter2[c - 'a']++;
            }
            for (int j = 0; j < 26; j++) {
                letter1[j] = Math.min(letter1[j], letter2[j]);
                letter2[j] = 0;
            }
        }
        for (int i = 0; i < 26; i++) {
            while (letter1[i]-- > 0) {
                list.add(String.valueOf((char) ('a' + i)));
            }
        }

        return list;
    }

    /**
     * 961. 重复 N 次的元素 在大小为 2N 的数组 A 中有 N+1 个不同的元素，其中有一个元素重复了 N 次。
     *
     * <p>返回重复了 N 次的那个元素。
     *
     * <p>示例 1：
     *
     * <p>输入：[1,2,3,3] 输出：3 示例 2：
     *
     * <p>输入：[2,1,2,5,3,2] 输出：2 示例 3：
     *
     * <p>输入：[5,1,5,2,5,3,5,4] 输出：5
     *
     * <p>提示：
     *
     * <p>4 <= A.length <= 10000 0 <= A[i] < 10000 A.length 为偶数 通过次数20,393提交次数30,822
     *
     * @param A
     * @return
     */
    public int repeatedNTimes(int[] A) {
        int[] nums = new int[10001];
        int result = 0;
        for (int a : A) {
            nums[a]++;
            if (nums[a] > 1) {
                result = a;
                break;
            }
        }
        return result;
    }

    /**
     * 953. 验证外星语词典 某种外星语也使用英文小写字母，但可能顺序 order 不同。字母表的顺序（order）是一些小写字母的排列。
     *
     * <p>给定一组用外星语书写的单词 words，以及其字母表的顺序 order，只有当给定的单词在这种外星语中按字典序排列时，返回 true；否则，返回 false。
     *
     * <p>示例 1：
     *
     * <p>输入：words = ["hello","leetcode"], order = "hlabcdefgijkmnopqrstuvwxyz" 输出：true
     * 解释：在该语言的字母表中，'h' 位于 'l' 之前，所以单词序列是按字典序排列的。 示例 2：
     *
     * <p>输入：words = ["word","world","row"], order = "worldabcefghijkmnpqstuvxyz" 输出：false
     * 解释：在该语言的字母表中，'d' 位于 'l' 之后，那么 words[0] > words[1]，因此单词序列不是按字典序排列的。 示例 3：
     *
     * <p>输入：words = ["apple","app"], order = "abcdefghijklmnopqrstuvwxyz" 输出：false 解释：当前三个字符 "app"
     * 匹配时，第二个字符串相对短一些，然后根据词典编纂规则 "apple" > "app"，因为 'l' > '∅'，其中 '∅' 是空白字符，定义为比任何其他字符都小（更多信息）。
     *
     * <p>提示：
     *
     * <p>1 <= words.length <= 100 1 <= words[i].length <= 20 order.length == 26 在 words[i] 和 order
     * 中的所有字符都是英文小写字母。
     *
     * @param words
     * @param order
     * @return
     */
    public boolean isAlienSorted(String[] words, String order) {
        /*Map<Character,Integer> map = new HashMap<>(order.length());
        for (int i = 0; i < order.length(); i++) {
            map.put(order.charAt(i),i);
        }*/
        int[] letters = new int[26];
        // 使用数组作为哈希
        for (int i = 0; i < order.length(); i++) {
            char c = order.charAt(i);
            letters[c - 'a'] = i;
        }
        int len = words.length;

        for (int i = 1; i < len; i++) {
            if (compareAlienWord(words[i - 1], words[i], letters) == 1) {
                return false;
            }
        }
        return true;
    }

    private int compareAlienWord(String word1, String word2, int[] letters) {
        int index = 0;
        while (index < word1.length() && index < word2.length()) {
            char c1 = word1.charAt(index);
            char c2 = word2.charAt(index);
            int num1 = letters[c1 - 'a'];
            int num2 = letters[c2 - 'a'];
            if (num1 < num2) {
                return -1;
            }
            if (num1 > num2) {
                return 1;
            }
            index++;
        }
        if (word1.length() < word2.length()) {
            return -1;
        }
        if (word1.length() > word2.length()) {
            return 1;
        }
        return 0;
    }

    /**
     * 884. 两句话中的不常见单词 给定两个句子 A 和 B 。 （句子是一串由空格分隔的单词。每个单词仅由小写字母组成。）
     *
     * <p>如果一个单词在其中一个句子中只出现一次，在另一个句子中却没有出现，那么这个单词就是不常见的。
     *
     * <p>返回所有不常用单词的列表。
     *
     * <p>您可以按任何顺序返回列表。
     *
     * <p>示例 1：
     *
     * <p>输入：A = "this apple is sweet", B = "this apple is sour" 输出：["sweet","sour"] 示例 2：
     *
     * <p>输入：A = "apple apple", B = "banana" 输出：["banana"]
     *
     * <p>提示：
     *
     * <p>0 <= A.length <= 200 0 <= B.length <= 200 A 和 B 都只包含空格和小写字母。
     *
     * @param A
     * @param B
     * @return
     */
    public String[] uncommonFromSentences(String A, String B) {
        Map<String, Integer> map = new HashMap<>();
        String[] aList = A.split(" ");

        String[] bList = B.split(" ");
        for (String a : aList) {
            int count = map.getOrDefault(a, 0);
            map.put(a, count + 1);
        }
        for (String b : bList) {
            int count = map.getOrDefault(b, 0);
            map.put(b, count + 1);
        }
        List<String> list = new ArrayList<>();

        map.forEach(
                (k, v) -> {
                    if (v == 1) {
                        list.add(k);
                    }
                });
        String[] result = new String[list.size()];
        list.toArray(result);
        return result;
    }

    /**
     * 面试题50. 第一个只出现一次的字符 在字符串 s 中找出第一个只出现一次的字符。如果没有，返回一个单空格。
     *
     * <p>示例:
     *
     * <p>s = "abaccdeff" 返回 "b"
     *
     * <p>s = "" 返回 " "
     *
     * <p>限制：
     *
     * <p>0 <= s 的长度 <= 50000
     *
     * @param s
     * @return
     */
    public char firstUniqChar2(String s) {
        char result = ' ';
        Map<Character, Integer> map = new LinkedHashMap<>();
        for (char c : s.toCharArray()) {
            int count = map.getOrDefault(c, 0);
            map.put(c, count + 1);
        }

        for (Character c : map.keySet()) {
            if (map.get(c) == 1) {
                return c;
            }
        }
        return result;
    }

    /**
     * 面试题48. 最长不含重复字符的子字符串 请从字符串中找出一个最长的不包含重复字符的子字符串，计算该最长子字符串的长度。
     *
     * <p>示例 1:
     *
     * <p>输入: "abcabcbb" 输出: 3 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。 示例 2:
     *
     * <p>输入: "bbbbb" 输出: 1 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。 示例 3:
     *
     * <p>输入: "pwwkew" 输出: 3 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。 请注意，你的答案必须是 子串 的长度，"pwke"
     * 是一个子序列，不是子串。
     *
     * <p>提示：
     *
     * <p>s.length <= 40000 注意：本题与主站 3
     * 题相同：https://leetcode-cn.com/problems/longest-substring-without-repeating-characters/
     *
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring2(String s) {
        int max = 0;

        int left = 0;
        int[] indexs = new int[128];
        Arrays.fill(indexs, -1);

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (indexs[c] != -1 && left <= indexs[c]) {
                left = indexs[c] + 1;
            }
            indexs[c] = i;
            max = Math.max(max, i - left + 1);
        }

        return max;
    }

    /**
     * 274. H 指数 给定一位研究者论文被引用次数的数组（被引用次数是非负整数）。编写一个方法，计算出研究者的 h 指数。
     *
     * <p>h 指数的定义：h 代表“高引用次数”（high citations），一名科研人员的 h 指数是指他（她）的 （N 篇论文中） 至多有 h 篇论文分别被引用了至少 h
     * 次。（其余的 N - h 篇论文每篇被引用次数 不超过 h 次。）
     *
     * <p>例如：某人的 h 指数是 20，这表示他已发表的论文中，每篇被引用了至少 20 次的论文总共有 20 篇。
     *
     * <p>示例：
     *
     * <p>输入：citations = [3,0,6,1,5] 输出：3 解释：给定数组表示研究者总共有 5 篇论文，每篇论文相应的被引用了 3, 0, 6, 1, 5 次。 由于研究者有
     * 3 篇论文每篇 至少 被引用了 3 次，其余两篇论文每篇被引用 不多于 3 次，所以她的 h 指数是 3。
     *
     * <p>提示：如果 h 有多种可能的值，h 指数是其中最大的那个。
     *
     * @param citations
     * @return
     */
    public int hIndex(int[] citations) {
        int len = citations.length;
        if (len == 0) {
            return 0;
        }
        Arrays.sort(citations);

        int result = 0;
        // 二分法
        int low = 0, high = len - 1;
        while (low < high) {
            int mid = (low + high) >> 1;
            if (citations[mid] >= len - mid) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        if (low < len && citations[low] == 0) {
            return citations[low];
        }
        return len - low;
    }

    /**
     * 187. 重复的DNA序列 所有 DNA 都由一系列缩写为 A，C，G 和 T 的核苷酸组成，例如：“ACGAATTCCG”。在研究 DNA 时，识别 DNA
     * 中的重复序列有时会对研究非常有帮助。
     *
     * <p>编写一个函数来查找 DNA 分子中所有出现超过一次的 10 个字母长的序列（子串）。
     *
     * <p>示例：
     *
     * <p>输入：s = "AAAAACCCCCAAAAACCCCCCAAAAAGGG 输出：["AAAAACCCCC", "CCCCCAAAAA"]
     *
     * @param s
     * @return
     */
    public List<String> findRepeatedDnaSequences(String s) {
        Set<String> result = new HashSet<>();
        Set<String> set = new HashSet<>();
        for (int i = 0; i < s.length() - 10; i++) {
            String str = s.substring(i, i + 10);
            if (set.contains(str)) {
                result.add(str);
            }
            set.add(str);
        }

        return new ArrayList<>(result);
    }

    /**
     * 560. 和为K的子数组 给定一个整数数组和一个整数 k，你需要找到该数组中和为 k 的连续的子数组的个数。
     *
     * <p>示例 1 :
     *
     * <p>输入:nums = [1,1,1], k = 2 输出: 2 , [1,1] 与 [1,1] 为两种不同的情况。 说明 :
     *
     * <p>数组的长度为 [1, 20,000]。 数组中元素的范围是 [-1000, 1000] ，且整数 k 的范围是 [-1e7, 1e7]。
     *
     * @param nums
     * @param k
     * @return
     */
    public int subarraySum(int[] nums, int k) {
        int count = 0, sum = 0;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (map.containsKey(sum - k)) {
                count += map.get(sum - k);
            }
            int n = map.getOrDefault(sum, 0);
            map.put(sum, n + 1);
        }

        return count;
        /*// 滑动窗口
        int left = 0,right = 0;
        int sum = 0;
        while (sum < k && right < nums.length) {
            sum += nums[right++];
        }
        if (sum == k) {
            count++;
        }
        for (int i = right; i < nums.length; i++) {
            sum +=
        }*/

    }

    /**
     * 76. 最小覆盖子串 给你一个字符串 S、一个字符串 T，请在字符串 S 里面找出：包含 T 所有字符的最小子串。
     *
     * <p>示例：
     *
     * <p>输入: S = "ADOBECODEBANC", T = "ABC" 输出: "BANC" 说明：
     *
     * <p>如果 S 中不存这样的子串，则返回空字符串 ""。 如果 S 中存在这样的子串，我们保证它是唯一的答案。
     *
     * @param s
     * @param t
     * @return
     */
    public String minWindow(String s, String t) {
        int[] dict = new int[26];

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
        }

        return "";
    }

    @Test
    public void subarraysDivByK() {
        int[] A = {5, 0, -2, -3, 4, 1};
        int K = 5;
        logResult(subarraysDivByK(A, K));
    }

    /**
     * 974. 和可被 K 整除的子数组 给定一个整数数组 A，返回其中元素之和可被 K 整除的（连续、非空）子数组的数目。
     *
     * <p>示例：
     *
     * <p>输入：A = [4,5,0,-2,-3,1], K = 5 输出：7 解释： 有 7 个子数组满足其元素之和可被 K = 5 整除： [4, 5, 0, -2, -3, 1],
     * [5], [5, 0], [5, 0, -2, -3], [0], [0, -2, -3], [-2, -3]
     *
     * <p>提示：
     *
     * <p>1 <= A.length <= 30000 -10000 <= A[i] <= 10000 2 <= K <= 10000
     *
     * @param A
     * @param K
     * @return
     */
    public int subarraysDivByK(int[] A, int K) {

        int sum = 0;
        int[] nums = new int[K];
        nums[0] = 1;
        int result = 0;
        for (int num : A) {
            sum += num;
            int key = (sum % K + K) % K;
            result += nums[key]++;
        }

        return result;
    }

    @Test
    public void hasAllCodes() {
        String s = "00110110";
        int k = 2;
        logResult(hasAllCodes(s, k));
    }

    /**
     * 5409. 检查一个字符串是否包含所有长度为 K 的二进制子串 给你一个二进制字符串 s 和一个整数 k 。
     *
     * <p>如果所有长度为 k 的二进制字符串都是 s 的子串，请返回 True ，否则请返回 False 。
     *
     * <p>示例 1：
     *
     * <p>输入：s = "00110110", k = 2 输出：true 解释：长度为 2 的二进制串包括 "00"，"01"，"10" 和 "11"。它们分别是 s 中下标为
     * 0，1，3，2 开始的长度为 2 的子串。 示例 2：
     *
     * <p>输入：s = "00110", k = 2 输出：true 示例 3：
     *
     * <p>输入：s = "0110", k = 1 输出：true 解释：长度为 1 的二进制串包括 "0" 和 "1"，显然它们都是 s 的子串。 示例 4：
     *
     * <p>输入：s = "0110", k = 2 输出：false 解释：长度为 2 的二进制串 "00" 没有出现在 s 中。 示例 5：
     *
     * <p>输入：s = "0000000001011100", k = 4 输出：false
     *
     * <p>提示：
     *
     * <p>1 <= s.length <= 5 * 10^5 s 中只含 0 和 1 。 1 <= k <= 20
     *
     * @param s
     * @param k
     * @return
     */
    public boolean hasAllCodes(String s, int k) {
        int num = 0;
        for (int i = 0; i < k && i < s.length(); i++) {
            num = (num << 1) + (s.charAt(i) - '0');
        }
        Set<Integer> set = new HashSet<>();
        set.add(num);
        for (int i = k; i < s.length(); i++) {
            int c = s.charAt(i - k) - '0';
            log.debug("k :{} num0:{}", c, c << (k - 1));
            num -= c << (k - 1);
            num = (num << 1) + (s.charAt(i) - '0');
            set.add(num);
            // log.debug("num2:{}",num);
        }
        log.debug("set:{}", set);
        return set.size() == (1 << k);
    }

    @Test
    public void getValidT9Words() {
        String num = "8733";
        String[] words = {"tree", "used"};
        logResult(getValidT9Words(num, words));
    }

    /**
     * 面试题 16.20. T9键盘
     *
     * <p>在老式手机上，用户通过数字键盘输入，手机将提供与这些数字相匹配的单词列表。每个数字映射到0至4个字母。给定一个数字序列，实现一个算法来返回匹配单词的列表。你会得到一张含有有效单词的列表。映射如下图所示：
     *
     * <p>示例 1:
     *
     * <p>输入: num = "8733", words = ["tree", "used"] 输出: ["tree", "used"] 示例 2:
     *
     * <p>输入: num = "2", words = ["a", "b", "c", "d"] 输出: ["a", "b", "c"] 提示：
     *
     * <p>num.length <= 1000 words.length <= 500 words[i].length == num.length num中不会出现 0, 1 这两个数字
     *
     * @param num
     * @param words
     * @return
     */
    public List<String> getValidT9Words(String num, String[] words) {
        /*Map<Character, Integer> map = new HashMap<>();
        map.put('!', 1);
        map.put('@', 1);
        map.put('#', 1);
        char c = 'a';
        for (int i = 2; i <= 9; i++) {
            int k = 3;
            if (i == 7 || i == 9) {
                k = 4;
            }
            for (int j = 0; j < k; j++) {
                map.put(c++, i);
            }
        }
        logResult(map);*/
        char[] map = {
            '2', '2', '2', '3', '3', '3', '4', '4', '4', '5', '5', '5', '6', '6', '6', '7', '7',
            '7', '7', '8', '8', '8', '9', '9', '9', '9'
        };

        List<String> result = new ArrayList<>();
        for (String word : words) {
            if (word.length() != num.length()) {
                continue;
            }

            boolean flag = true;
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                if (num.charAt(i) != map[c - 'a']) {
                    flag = false;
                    break;
                }
            }

            if (flag) {
                result.add(word);
            }
        }

        return result;
    }

    @Test
    public void maxAliveYear() {
        int[] birth = {1900, 1901, 1950}, death = {1948, 1951, 2000};
        logResult(maxAliveYear(birth, death));
    }
    /**
     * 面试题 16.10. 生存人数
     *
     * <p>给定N个人的出生年份和死亡年份，第i个人的出生年份为birth[i]，死亡年份为death[i]，实现一个方法以计算生存人数最多的年份。
     *
     * <p>你可以假设所有人都出生于1900年至2000年（含1900和2000）之间。如果一个人在某一年的任意时期都处于生存状态，那么他们应该被纳入那一年的统计中。例如，生于1908年、死于1909年的人应当被列入1908年和1909年的计数。
     *
     * <p>如果有多个年份生存人数相同且均为最大值，输出其中最小的年份。
     *
     * <p>示例：
     *
     * <p>输入： birth = {1900, 1901, 1950} death = {1948, 1951, 2000} 输出： 1901 提示：
     *
     * <p>0 < birth.length == death.length <= 10000 birth[i] <= death[i]
     *
     * @param birth
     * @param death
     * @return
     */
    public int maxAliveYear(int[] birth, int[] death) {
        /* int[] alives = new int[101];

        for (int i = 0; i < birth.length; i++) {
            for (int j = birth[i]; j <= death[i]; j++) {
                alives[j - 1900]++;
            }
        }
        int result = 0, max = 0;
        for (int i = 0; i < 101; i++) {
            if (alives[i] > max) {
                result = i;
                max = alives[i];
            }
        }
        return 1900 + result;*/
        // 出生, 人数 + 1；
        int[] alives = new int[102];
        for (int b : birth) {
            alives[b - 1900]++;
        }
        // 死亡的下一年, 人数 - 1；
        for (int d : death) {
            alives[d - 1900 + 1]--;
        }

        int result = 0, max = 0;
        for (int i = 1; i < 102; i++) {
            alives[i] += alives[i - 1];
            if (alives[i] > max) {
                result = i;
                max = alives[i];
            }
        }
        return 1900 + result;
    }

    /**
     * 面试题 10.02. 变位词组
     *
     * <p>编写一种方法，对字符串数组进行排序，将所有变位词组合在一起。变位词是指字母相同，但排列不同的字符串。
     *
     * <p>注意：本题相对原题稍作修改
     *
     * <p>示例:
     *
     * <p>输入: ["eat", "tea", "tan", "ate", "nat", "bat"], 输出: [ ["ate","eat","tea"], ["nat","tan"],
     * ["bat"] ] 说明：
     *
     * <p>所有输入均为小写字母。 不考虑答案输出的顺序。
     *
     * @param strs
     * @return
     */
    public List<List<String>> groupAnagrams2(String[] strs) {
        // 利用HashMap 分组
        // 问题,找到合适的hash算法区分不同的单词
        HashMap<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            String key = getAnagramsKey(str);
            List<String> list = map.computeIfAbsent(key, k -> new ArrayList<>());
            list.add(str);
        }
        return new ArrayList<>(map.values());
    }

    private String getAnagramsKey(String str) {
        int[] letters = new int[26];
        for (char c : str.toCharArray()) {
            letters[c - 'a']++;
        }
        StringBuilder sb = new StringBuilder();

        for (int num : letters) {
            sb.append(num).append('.');
        }
        return sb.toString();
    }

    @Test
    public void bestLine() {
        int[][] points = {{0, 0}, {1, 1}, {1, 0}, {2, 0}};
        int[] point = bestLine(points);
        log.debug("point:{}", point);
    }
    /**
     * 面试题 16.14. 最佳直线
     *
     * <p>给定一个二维平面及平面上的 N 个点列表Points，其中第i个点的坐标为Points[i]=[Xi,Yi]。请找出一条直线，其通过的点的数目最多。
     *
     * <p>设穿过最多点的直线所穿过的全部点编号从小到大排序的列表为S，你仅需返回[S[0],S[1]]作为答案，若有多条直线穿过了相同数量的点，则选择S[0]值较小的直线返回，
     * S[0]相同则选择S[1]值较小的直线返回。
     *
     * <p>示例：
     *
     * <p>输入： [[0,0],[1,1],[1,0],[2,0]] 输出： [0,2] 解释： 所求直线穿过的3个点的编号为[0,2,3] 提示：
     *
     * <p>2 <= len(Points) <= 300 len(Points[i]) = 2
     *
     * @param points
     * @return
     */
    public int[] bestLine(int[][] points) {

        // Arrays.sort(points, (a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < points.length - 1; i++) {

            Map<Double, List<Integer>> map = new HashMap<>();
            for (int j = i + 1; j < points.length; j++) {
                // 使用 斜率作为key
                int dx = points[j][0] - points[i][0], dy = points[j][1] - points[i][1];

                double key = compute(dx, dy);

                int finalI = i;
                List<Integer> list =
                        map.computeIfAbsent(
                                key,
                                k -> {
                                    List<Integer> a = new ArrayList<>();
                                    a.add(finalI);
                                    return a;
                                });
                list.add(j);
                if (list.size() > result.size()) {
                    result = list;
                }
            }
        }

        return new int[] {result.get(0), result.get(1)};
    }

    private double compute(int diffx, int diffy) {
        if (diffx == 0) {
            return Integer.MIN_VALUE;
        } else if (diffy == 0) {
            return 0.0;
        } else {
            return (double) diffy / (double) diffx;
        }
    }

    // 最大公约数
    public static int getGcd(int a, int b) {
        int max, min;
        max = Math.max(a, b);
        min = Math.min(a, b);

        if (max % min != 0) {
            return getGcd(min, max % min);
        }
        return min;
    }

    @Test
    public void testGcg() {
        int a = -10, b = 15;
        logResult(getGcd(a, b));
    }

    @Test
    public void maxRectangle() {
        String[] words = {"this", "real", "hard", "trh", "hea", "iar", "sld"};
        logResult(maxRectangle(words));
    }

    /**
     * 面试题 17.25. 单词矩阵
     *
     * <p>给定一份单词的清单，设计一个算法，创建由字母组成的面积最大的矩形，其中每一行组成一个单词(自左向右)，每一列也组成一个单词(自上而下)。不要求这些单词在清单里连续出现，但要求所有行等长，所有列等高。
     *
     * <p>如果有多个面积最大的矩形，输出任意一个均可。一个单词可以重复使用。
     *
     * <p>示例 1:
     *
     * <p>输入: ["this", "real", "hard", "trh", "hea", "iar", "sld"] 输出: [ "this", "real", "hard" ] 示例
     * 2:
     *
     * <p>输入: ["aa"] 输出: ["aa","aa"] 说明：
     *
     * <p>words.length <= 1000 words[i].length <= 100 数据保证单词足够随机
     *
     * @param words
     * @return
     */
    public String[] maxRectangle(String[] words) {
        trieNode = new Trie2.TrieNode();
        lenMap = new HashMap<>();
        maxRectangleResult = new ArrayList<>();
        maxArea = 0;
        maxLength = 0;
        for (String word : words) {
            Set<String> set = lenMap.computeIfAbsent(word.length(), k -> new HashSet<>());
            set.add(word);
            maxLength = Math.max(word.length(), maxLength);

            Trie2.TrieNode node = trieNode;
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                Trie2.TrieNode currentNode = node.children[c - 'a'];
                if (Objects.isNull(currentNode)) {
                    currentNode = new Trie2.TrieNode();
                    node.children[c - 'a'] = currentNode;
                }
                node = currentNode;
            }
            node.isEnd = true;
        }

        List<String> path = new ArrayList<>();
        for (int key : lenMap.keySet()) {
            path.clear();
            // 回溯需要的参数是：相同长度单词的集合，存放路径的列表，当前单词的长度
            maxRectangleDfs(lenMap.get(key), path, key);
        }

        String[] result = new String[maxRectangleResult.size()];
        return maxRectangleResult.toArray(result);
    }

    private void maxRectangleDfs(Set<String> strings, List<String> path, int wordLen) {
        if (wordLen * maxLength <= maxArea) {
            return;
        }
        // 超过了最长的单词
        if (path.size() > maxLength) {
            return;
        }
        for (String str : strings) {
            path.add(str);
            boolean[] result = isValidRectangle(path);
            if (result[0]) {
                int area = path.size() * path.get(0).length();
                if (result[1] && (area > maxArea)) { // 每列都是单词的矩阵
                    maxArea = area;
                    // ans = path;   一定注意这里不能直接把path引用交给答案
                    maxRectangleResult = new ArrayList<>(path);
                }
                // 回溯
                maxRectangleDfs(strings, path, wordLen);
            }

            path.remove(path.size() - 1);
        }
    }

    /**
     * @param input
     * @return
     */
    private boolean[] isValidRectangle(List<String> input) {
        boolean allLeaf = true;
        int m = input.size();
        int n = input.get(0).length();
        for (int j = 0; j < n; j++) {
            // 按列来看单词是否在字典树
            Trie2.TrieNode node = trieNode;
            for (int i = 0; i < m; i++) {
                int c = input.get(i).charAt(j) - 'a';
                if (node.children[c] == null) {
                    return new boolean[] {false, false};
                }
                node = node.children[c];
            }
            if (!node.isEnd) {
                allLeaf = false;
            }
        }
        return new boolean[] {true, allLeaf};
    }

    Trie2.TrieNode trieNode;
    // 把清单根据单词长度集合起来
    Map<Integer, Set<String>> lenMap;
    int maxArea;
    int maxLength;
    List<String> maxRectangleResult;

    @Test
    public void computeSimilarities() {
        int[][] docs = {{14, 15, 100, 9, 3}, {32, 1, 9, 3, 5}, {15, 29, 2, 6, 8, 7}, {7, 10}};
        logResult(computeSimilarities(docs));
    }
    /**
     * 面试题 17.26. 稀疏相似度
     *
     * <p>两个(具有不同单词的)文档的交集(intersection)中元素的个数除以并集(union)中元素的个数，就是这两个文档的相似度。例如，{1, 5, 3} 和 {1, 7, 2,
     * 3} 的相似度是 0.4，其中，交集的元素有 2 个，并集的元素有 5 个。给定一系列的长篇文档，每个文档元素各不相同，并与一个 ID 相关联。它们的相似度非常“稀疏”，也就是说任选 2
     * 个文档，相似度都很接近 0。请设计一个算法返回每对文档的 ID 及其相似度。只需输出相似度大于 0 的组合。请忽略空文档。为简单起见，可以假定每个文档由一个含有不同整数的数组表示。
     *
     * <p>输入为一个二维数组 docs，docs[i] 表示 id 为 i 的文档。返回一个数组，其中每个元素是一个字符串，代表每对相似度大于 0 的文档，其格式为 {id1},{id2}:
     * {similarity}，其中 id1 为两个文档中较小的 id，similarity 为相似度，精确到小数点后 4 位。以任意顺序返回数组均可。
     *
     * <p>示例:
     *
     * <p>输入: [ [14, 15, 100, 9, 3], [32, 1, 9, 3, 5], [15, 29, 2, 6, 8, 7], [7, 10] ] 输出: [ "0,1:
     * 0.2500", "0,2: 0.1000", "2,3: 0.1429" ] 提示：
     *
     * <p>docs.length <= 500 docs[i].length <= 500 相似度大于 0 的文档对数不会超过 1000
     *
     * @param docs
     * @return
     */
    public List<String> computeSimilarities(int[][] docs) {
        List<String> result = new ArrayList<>();
        int len = docs.length;
        // 记录每个元素 出现的下标集合
        // map的key就是遍历到的数字，value是一个由数组index组成的list
        Map<Integer, List<Integer>> dict = new HashMap<>();
        // 用一个help矩阵来存储数组与数组之间有多少交集
        int[][] help = new int[docs.length][docs.length];
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < docs[i].length; j++) {
                int key = docs[i][j];
                List<Integer> indexList = dict.computeIfAbsent(key, k -> new ArrayList<>());
                // 当前 i位置与其他位置存在交集, 记录交集元素个数
                for (int index : indexList) {
                    help[i][index]++;
                }
                indexList.add(i);
            }
            for (int j = 0; j < len; j++) {
                int count = help[i][j];
                if (count > 0) {
                    result.add(
                            String.format(
                                    "%d,%d: %.4f",
                                    j,
                                    i,
                                    (double) count
                                            / (double) (docs[i].length + docs[j].length - count)));
                }
            }
        }

        return result;
    }

    @Test
    public void findAnagrams() {
        String s = "abab", p = "ab";
        logResult(findAnagrams(s, p));
    }

    /**
     * 438. 找到字符串中所有字母异位词
     *
     * <p>给定一个字符串 s 和一个非空字符串 p，找到 s 中所有是 p 的字母异位词的子串，返回这些子串的起始索引。
     *
     * <p>字符串只包含小写英文字母，并且字符串 s 和 p 的长度都不超过 20100。
     *
     * <p>说明：
     *
     * <p>字母异位词指字母相同，但排列不同的字符串。 不考虑答案输出的顺序。 示例 1:
     *
     * <p>输入: s: "cbaebabacd" p: "abc"
     *
     * <p>输出: [0, 6]
     *
     * <p>解释: 起始索引等于 0 的子串是 "cba", 它是 "abc" 的字母异位词。 起始索引等于 6 的子串是 "bac", 它是 "abc" 的字母异位词。 示例 2:
     *
     * <p>输入: s: "abab" p: "ab"
     *
     * <p>输出: [0, 1, 2]
     *
     * <p>解释: 起始索引等于 0 的子串是 "ab", 它是 "ab" 的字母异位词。 起始索引等于 1 的子串是 "ba", 它是 "ab" 的字母异位词。 起始索引等于 2 的子串是
     * "ab", 它是 "ab" 的字母异位词。
     *
     * @param s
     * @param p
     * @return
     */
    public List<Integer> findAnagrams(String s, String p) {
        int[] letters = new int[26];
        int pLen = p.length();
        for (char c : p.toCharArray()) {
            letters[c - 'a']++;
        }
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            letters[c - 'a']--;
            if (i >= pLen) {
                letters[s.charAt(i - pLen) - 'a']++;
            }
            if (i >= pLen - 1 && checkLetter(letters)) {
                result.add(i - pLen + 1);
            }
        }

        return result;
    }

    private boolean checkLetter(int[] letters) {
        for (int count : letters) {
            if (count != 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * 554. 砖墙
     *
     * <p>你的面前有一堵矩形的、由多行砖块组成的砖墙。 这些砖块高度相同但是宽度不同。你现在要画一条自顶向下的、穿过最少砖块的垂线。
     *
     * <p>砖墙由行的列表表示。 每一行都是一个代表从左至右每块砖的宽度的整数列表。
     *
     * <p>如果你画的线只是从砖块的边缘经过，就不算穿过这块砖。你需要找出怎样画才能使这条线穿过的砖块数量最少，并且返回穿过的砖块数量。
     *
     * <p>你不能沿着墙的两个垂直边缘之一画线，这样显然是没有穿过一块砖的。
     *
     * <p>示例：
     *
     * <p>输入: [[1,2,2,1], [3,1,2], [1,3,2], [2,4], [3,1,2], [1,3,1,1]]
     *
     * <p>输出: 2
     *
     * <p>解释:
     *
     * <p>提示：
     *
     * <p>每一行砖块的宽度之和应该相等，并且不能超过 INT_MAX。 每一行砖块的数量在 [1,10,000] 范围内， 墙的高度在 [1,10,000] 范围内， 总的砖块数量不超过
     * 20,000。
     *
     * @param wall
     * @return
     */
    public int leastBricks(List<List<Integer>> wall) {
        Map<Integer, Integer> map = new HashMap<>();
        for (List<Integer> list : wall) {
            int end = 0;
            for (int i = 0; i < list.size() - 1; i++) {
                end += list.get(i);
                int count = map.getOrDefault(end, 0);
                map.put(end, count + 1);
            }
        }
        int max = 0;
        for (int value : map.values()) {
            max = Math.max(max, value);
        }
        return wall.size() - max;
    }

    @Test
    public void findDuplicate() {
        String[] paths = {
            "root/a 1.txt(abcd) 2.txt(efgh)",
            "root/c 3.txt(abcd)",
            "root/c/d 4.txt(efgh)",
            "root 4.txt(efgh)"
        };
        logResult(findDuplicate(paths));
    }

    /**
     * 609. 在系统中查找重复文件
     *
     * <p>给定一个目录信息列表，包括目录路径，以及该目录中的所有包含内容的文件，您需要找到文件系统中的所有重复文件组的路径。一组重复的文件至少包括二个具有完全相同内容的文件。
     *
     * <p>输入列表中的单个目录信息字符串的格式如下：
     *
     * <p>"root/d1/d2/.../dm f1.txt(f1_content) f2.txt(f2_content) ... fn.txt(fn_content)"
     *
     * <p>这意味着有 n 个文件（f1.txt, f2.txt ... fn.txt 的内容分别是 f1_content, f2_content ... fn_content）在目录
     * root/d1/d2/.../dm 下。注意：n>=1 且 m>=0。如果 m=0，则表示该目录是根目录。
     *
     * <p>该输出是重复文件路径组的列表。对于每个组，它包含具有相同内容的文件的所有文件路径。文件路径是具有下列格式的字符串：
     *
     * <p>"directory_path/file_name.txt"
     *
     * <p>示例 1：
     *
     * <p>输入： ["root/a 1.txt(abcd) 2.txt(efgh)", "root/c 3.txt(abcd)", "root/c/d 4.txt(efgh)", "root
     * 4.txt(efgh)"] 输出：
     * [["root/a/2.txt","root/c/d/4.txt","root/4.txt"],["root/a/1.txt","root/c/3.txt"]]
     *
     * <p>注：
     *
     * <p>最终输出不需要顺序。 您可以假设目录名、文件名和文件内容只有字母和数字，并且文件内容的长度在 [1，50] 的范围内。 给定的文件数量在 [1，20000] 个范围内。
     * 您可以假设在同一目录中没有任何文件或目录共享相同的名称。 您可以假设每个给定的目录信息代表一个唯一的目录。目录路径和文件信息用一个空格分隔。
     *
     * <p>超越竞赛的后续行动：
     *
     * <p>假设您有一个真正的文件系统，您将如何搜索文件？广度搜索还是宽度搜索？ 如果文件内容非常大（GB级别），您将如何修改您的解决方案？ 如果每次只能读取 1 kb
     * 的文件，您将如何修改解决方案？ 修改后的解决方案的时间复杂度是多少？其中最耗时的部分和消耗内存的部分是什么？如何优化？ 如何确保您发现的重复文件不是误报？
     *
     * @param paths
     * @return
     */
    public List<List<String>> findDuplicate(String[] paths) {
        Map<String, List<String>> map = new HashMap<>();

        for (String path : paths) {
            String[] dirs = path.split(" ");
            String root = dirs[0];
            for (int i = 1; i < dirs.length; i++) {
                String[] files = dirs[i].split("[()]");
                logResult(files);
                List<String> list = map.computeIfAbsent(files[1], k -> new ArrayList<>());
                list.add(root + "/" + files[0]);
            }
        }
        /*List<List<String>> result = new ArrayList<>();
        for (List<String> list : map.values()) {
            if (list.size() > 1) {
                result.add(list);
            }
        }*/

        return map.values().stream().filter(list -> list.size() > 1).collect(Collectors.toList());
    }
}
