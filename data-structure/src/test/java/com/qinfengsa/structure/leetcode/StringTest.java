package com.qinfengsa.structure.leetcode;

import static com.qinfengsa.structure.util.LogUtils.logResult;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import javafx.util.Pair;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

/**
 * 字符串 test
 *
 * @author: qinfengsa
 * @date: 2019/5/9 09:11
 */
@Slf4j
public class StringTest {

    /**
     * 二进制求和 给定两个二进制字符串，返回他们的和（用二进制表示）。
     *
     * <p>输入为非空字符串且只包含数字 1 和 0。
     *
     * <p>示例 1:
     *
     * <p>输入: a = "11", b = "1" 输出: "100" 示例 2:
     *
     * <p>输入: a = "1010", b = "1011" 输出: "10101"
     */
    @Test
    public void testAddBinary() {
        String a = "11";
        String b = "1111";
        String result = addBinary(a, b);
        log.debug("result:{} ", result);
    }

    /**
     * 二进制求和 给定两个二进制字符串，返回他们的和（用二进制表示）。
     *
     * <p>输入为非空字符串且只包含数字 1 和 0。
     *
     * <p>示例 1:
     *
     * <p>输入: a = "11", b = "1" 输出: "100" 示例 2:
     *
     * <p>输入: a = "1010", b = "1011" 输出: "10101"
     *
     * @param a
     * @param b
     * @return
     */
    public String addBinary(String a, String b) {

        int m = a.length() - 1;
        int n = b.length() - 1;
        int size = a.length() > b.length() ? a.length() : b.length();
        Integer[] nums = new Integer[size + 1];
        int carry = 0;
        int index = size;
        while (m >= 0 || n >= 0) {
            int p = m >= 0 ? a.charAt(m--) - '0' : 0;
            int q = n >= 0 ? b.charAt(n--) - '0' : 0;
            int sum = p + q + carry;
            int num = sum % 2;
            nums[index] = num;
            carry = sum / 2;
            index--;
        }

        if (carry > 0) {
            nums[0] = carry;
        }
        StringBuilder sb = new StringBuilder();
        for (Integer num : nums) {
            if (Objects.nonNull(num)) {
                sb.append(num);
            }
        }

        return sb.toString();
    }

    /**
     * 实现 strStr() 函数。
     *
     * <p>给定一个 haystack 字符串和一个 needle 字符串，在 haystack 字符串中找出 needle 字符串出现的第一个位置 (从0开始)。如果不存在，则返回 -1。
     *
     * <p>示例 1:
     *
     * <p>输入: haystack = "hello", needle = "ll" 输出: 2 示例 2:
     *
     * <p>输入: haystack = "aaaaa", needle = "bba" 输出: -1 说明:
     *
     * <p>当 needle 是空字符串时，我们应当返回什么值呢？这是一个在面试中很好的问题。
     *
     * <p>对于本题而言，当 needle 是空字符串时我们应当返回 0 。这与C语言的 strstr() 以及 Java的 indexOf() 定义相符。
     */
    @Test
    public void testStr() {
        String haystack = "hello", needle = "";
        int index = strStr2(haystack, needle);
        log.debug("result:{} ", index);
    }

    public int strStr2(String haystack, String needle) {
        int len = haystack.length();
        int strLen = needle.length();
        if (strLen == 0) {
            return 0;
        }
        for (int i = 0; i < len - strLen + 1; i++) {
            boolean isSame = true;
            for (int j = strLen - 1; j >= 0; j--) {
                if (needle.charAt(j) != haystack.charAt(i + j)) {
                    isSame = false;
                    break;
                }
            }
            if (isSame) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 实现 strStr() 函数。
     *
     * <p>给定一个 haystack 字符串和一个 needle 字符串，在 haystack 字符串中找出 needle 字符串出现的第一个位置 (从0开始)。如果不存在，则返回 -1。
     *
     * <p>示例 1:
     *
     * <p>输入: haystack = "hello", needle = "ll" 输出: 2 示例 2:
     *
     * <p>输入: haystack = "aaaaa", needle = "bba" 输出: -1 说明:
     *
     * <p>当 needle 是空字符串时，我们应当返回什么值呢？这是一个在面试中很好的问题。
     *
     * <p>对于本题而言，当 needle 是空字符串时我们应当返回 0 。这与C语言的 strstr() 以及 Java的 indexOf() 定义相符。
     *
     * @param haystack
     * @param needle
     * @return
     */
    public int strStr(String haystack, String needle) {
        if (haystack.length() < needle.length()) {
            return -1;
        }
        if (Objects.equals(needle, "")) {
            return 0;
        }
        int alen = haystack.length();
        int blen = needle.length();
        int index = -1;
        for (int i = 0; i < alen; i++) {

            int last = blen - 1;
            if (i + last >= alen) {
                index = -1;
                break;
            }
            // 最后一位匹配
            if (haystack.charAt(i + last) == needle.charAt(last)) {
                boolean isIndex = true;
                for (int j = last - 1; j >= 0; j--) {
                    if (haystack.charAt(i + j) != needle.charAt(j)) {
                        isIndex = false;
                        break;
                    }
                }
                if (isIndex) {
                    index = i;
                    return index;
                }
            } else {
                continue;
            }
        }

        return index;
    }

    /**
     * 最长公共前缀 编写一个函数来查找字符串数组中的最长公共前缀。
     *
     * <p>如果不存在公共前缀，返回空字符串 ""。
     *
     * <p>示例 1:
     *
     * <p>输入: ["flower","flow","flight"] 输出: "fl" 示例 2:
     *
     * <p>输入: ["dog","racecar","car"] 输出: "" 解释: 输入不存在公共前缀。 说明:
     *
     * <p>所有输入只包含小写字母 a-z
     */
    @Test
    public void testLongestCommonPrefix() {
        String[] strs = {"aca", "acaa"};
        String result = longestCommonPrefix2(strs);
        log.debug(result);
    }

    public String longestCommonPrefix2(String[] strs) {
        String result = "";
        if (strs.length == 0) {
            return "";
        }
        if (strs.length == 1) {
            return strs[0];
        }
        String str = strs[0];
        int index = -1;
        for (int i = 0; i < str.length(); i++) {
            boolean isIndex = true;
            char num = str.charAt(i);
            for (int j = 1; j < strs.length; j++) {
                String s = strs[j];
                if (s.length() <= i) {
                    isIndex = false;
                    break;
                }
                if (s.charAt(i) != num) {
                    isIndex = false;
                    break;
                }
            }
            if (isIndex) {
                index = i;
            } else {
                break;
            }
        }

        if (index > -1) {
            result = str.substring(0, index + 1);
        }

        return result;
    }

    /**
     * 最长公共前缀 编写一个函数来查找字符串数组中的最长公共前缀。
     *
     * <p>如果不存在公共前缀，返回空字符串 ""。
     *
     * <p>示例 1:
     *
     * <p>输入: ["flower","flow","flight"] 输出: "fl" 示例 2:
     *
     * <p>输入: ["dog","racecar","car"] 输出: "" 解释: 输入不存在公共前缀。 说明:
     *
     * <p>所有输入只包含小写字母 a-z
     *
     * @param strs
     * @return
     */
    public String longestCommonPrefix(String[] strs) {
        if (Objects.isNull(strs) || strs.length == 0) {
            return null;
        }
        if (strs.length == 1) {
            return strs[0];
        }
        List<Character> chars = new ArrayList<>();
        String a = strs[0];
        for (int i = 0; i < a.length(); i++) {
            char chPre = a.charAt(i);
            boolean isPre = true;
            for (int j = 1; j < strs.length; j++) {

                if (strs[j].length() - 1 < i) {
                    isPre = false;
                    break;
                }

                if (chPre != strs[j].charAt(i)) {
                    isPre = false;
                    break;
                }
            }
            if (isPre) {
                chars.add(chPre);
            } else {
                break;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (Character c : chars) {
            sb.append(c);
        }

        return sb.toString();
    }

    /**
     * 反转字符串 编写一个函数，其作用是将输入的字符串反转过来。输入字符串以字符数组 char[] 的形式给出。
     *
     * <p>不要给另外的数组分配额外的空间，你必须原地修改输入数组、使用 O(1) 的额外空间解决这一问题。
     *
     * <p>你可以假设数组中的所有字符都是 ASCII 码表中的可打印字符。 示例 1：
     *
     * <p>输入：["h","e","l","l","o"] 输出：["o","l","l","e","h"] 示例 2：
     *
     * <p>输入：["H","a","n","n","a","h"] 输出：["h","a","n","n","a","H"]
     */
    @Test
    public void testReverseString() {
        // char[] s = {'h','e','l','l','o'};
        char[] s = {'H', 'a', 'n', 'n', 'a', 'h'};
        reverseString(s);
        log.debug("result :{}", s);
    }

    /**
     * 反转字符串 编写一个函数，其作用是将输入的字符串反转过来。输入字符串以字符数组 char[] 的形式给出。
     *
     * <p>不要给另外的数组分配额外的空间，你必须原地修改输入数组、使用 O(1) 的额外空间解决这一问题。
     *
     * <p>你可以假设数组中的所有字符都是 ASCII 码表中的可打印字符。 示例 1：
     *
     * <p>输入：["h","e","l","l","o"] 输出：["o","l","l","e","h"] 示例 2：
     *
     * <p>输入：["H","a","n","n","a","h"] 输出：["h","a","n","n","a","H"]
     *
     * @param s
     */
    public void reverseString(char[] s) {
        if (s.length < 2) {
            return;
        }
        int len = s.length;
        int i = 0, j = len - 1;
        while (i < j) {
            char tmp = s[i];
            s[i] = s[j];
            s[j] = tmp;
            i++;
            j--;
        }
    }

    @Test
    public void testArrayPairSum() {
        int[] a = {1, 4, 3, 2};
        log.debug("result:{}", arrayPairSum(a));
    }

    /**
     * 数组拆分 I 给定长度为 2n 的数组, 你的任务是将这些数分成 n 对, 例如 (a1, b1), (a2, b2), ..., (an, bn) ，使得从1 到 n 的
     * min(ai, bi) 总和最大。
     *
     * <p>示例 1:
     *
     * <p>输入: [1,4,3,2]
     *
     * <p>输出: 4 解释: n 等于 2, 最大总和为 4 = min(1, 2) + min(3, 4). 提示:
     *
     * <p>n 是正整数,范围在 [1, 10000]. 数组中的元素范围在 [-10000, 10000].
     *
     * @param nums
     * @return
     */
    public int arrayPairSum(int[] nums) {
        int sum = 0;
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i += 2) sum += nums[i];
        return sum;
    }

    /**
     * 两数之和 II - 输入有序数组 给定一个已按照升序排列 的有序数组，找到两个数使得它们相加之和等于目标数。
     *
     * <p>函数应该返回这两个下标值 index1 和 index2，其中 index1 必须小于 index2。
     *
     * <p>说明:
     *
     * <p>返回的下标值（index1 和 index2）不是从零开始的。 你可以假设每个输入只对应唯一的答案，而且你不可以重复使用相同的元素。 示例:
     *
     * <p>输入: numbers = [2, 7, 11, 15], target = 9 输出: [1,2] 解释: 2 与 7 之和等于目标数 9 。因此 index1 = 1,
     * index2 = 2 。
     */
    @Test
    public void testTwoSum() {
        int[] numbers = {2, 3, 5, 7, 11, 15};
        int target = 9;
        int[] result = twoSum2(numbers, target);
        log.debug("result:{}", result);
    }

    /**
     * 两数之和 II - 输入有序数组 给定一个已按照升序排列 的有序数组，找到两个数使得它们相加之和等于目标数。
     *
     * <p>函数应该返回这两个下标值 index1 和 index2，其中 index1 必须小于 index2。
     *
     * <p>说明:
     *
     * <p>返回的下标值（index1 和 index2）不是从零开始的。 你可以假设每个输入只对应唯一的答案，而且你不可以重复使用相同的元素。 示例:
     *
     * <p>输入: numbers = [2, 7, 11, 15], target = 9 输出: [1,2] 解释: 2 与 7 之和等于目标数 9 。因此 index1 = 1,
     * index2 = 2 。
     *
     * @param numbers
     * @param target
     * @return
     */
    public int[] twoSum(int[] numbers, int target) {
        int[] result = new int[2];
        int size = numbers.length;
        if (size < 2) {
            return null;
        }
        if (numbers[0] + numbers[1] > target) {
            return null;
        }
        if (numbers[size - 2] + numbers[size - 1] < target) {
            return null;
        }
        int a = 0, b = 0;
        // 遍历 a = numbers[i] 在剩余元素中找到 target - a 的元素
        for (int i = 0; i < numbers.length; i++) {
            a = numbers[i];
            if (a > target) {
                return null;
            }
            b = target - a;
            if (b < a) {
                return null;
            }
            result[0] = i + 1;
            for (int j = i + 1; j < numbers.length; j++) {
                if (numbers[j] == b) {
                    result[1] = j + 1;
                    return result;
                } else if (numbers[j] < b) {
                    continue;
                } else if (numbers[j] > b) {
                    break;
                }
            }
        }

        return null;
    }

    /**
     * 两数之和 II - 输入有序数组 给定一个已按照升序排列 的有序数组，找到两个数使得它们相加之和等于目标数。
     *
     * <p>函数应该返回这两个下标值 index1 和 index2，其中 index1 必须小于 index2。
     *
     * <p>说明:
     *
     * <p>返回的下标值（index1 和 index2）不是从零开始的。 你可以假设每个输入只对应唯一的答案，而且你不可以重复使用相同的元素。 示例:
     *
     * <p>输入: numbers = [2, 7, 11, 15], target = 9 输出: [1,2] 解释: 2 与 7 之和等于目标数 9 。因此 index1 = 1,
     * index2 = 2 。
     *
     * @param numbers
     * @param target
     * @return
     */
    public int[] twoSum2(int[] numbers, int target) {
        int[] result = new int[2];
        int size = numbers.length;
        if (size < 2) {
            return null;
        }
        if (numbers[0] + numbers[1] > target) {
            return null;
        }
        if (numbers[size - 2] + numbers[size - 1] < target) {
            return null;
        }

        int left = 0;
        int right = size - 1;
        //  双指针，
        while (left < right) {
            if (numbers[left] + numbers[right] == target) {
                break;
            }

            if (numbers[left] + numbers[right] > target) {
                right--;
            } else {
                left++;
            }
        }
        result[0] = left + 1;
        result[1] = right + 1;
        return result;
    }

    /**
     * 移除元素 给定一个数组 nums 和一个值 val，你需要原地移除所有数值等于 val 的元素，返回移除后数组的新长度。
     *
     * <p>不要使用额外的数组空间，你必须在原地修改输入数组并在使用 O(1) 额外空间的条件下完成。
     *
     * <p>元素的顺序可以改变。你不需要考虑数组中超出新长度后面的元素。
     *
     * <p>示例 1:
     *
     * <p>给定 nums = [3,2,2,3], val = 3,
     *
     * <p>函数应该返回新的长度 2, 并且 nums 中的前两个元素均为 2。
     *
     * <p>你不需要考虑数组中超出新长度后面的元素。 示例 2:
     *
     * <p>给定 nums = [0,1,2,2,3,0,4,2], val = 2,
     *
     * <p>函数应该返回新的长度 5, 并且 nums 中的前五个元素为 0, 1, 3, 0, 4。
     *
     * <p>注意这五个元素可为任意顺序。
     *
     * <p>你不需要考虑数组中超出新长度后面的元素。
     */
    @Test
    public void testRemoveElement() {
        int[] nums = {3, 2, 2, 3};
        int val = 3;
        int result = removeElement(nums, val);
        log.debug("result:{},nums:{}", result, nums);
    }

    /**
     * 移除元素 给定一个数组 nums 和一个值 val，你需要原地移除所有数值等于 val 的元素，返回移除后数组的新长度。
     *
     * <p>不要使用额外的数组空间，你必须在原地修改输入数组并在使用 O(1) 额外空间的条件下完成。
     *
     * <p>元素的顺序可以改变。你不需要考虑数组中超出新长度后面的元素。
     *
     * <p>示例 1:
     *
     * <p>给定 nums = [3,2,2,3], val = 3,
     *
     * <p>函数应该返回新的长度 2, 并且 nums 中的前两个元素均为 2。
     *
     * <p>你不需要考虑数组中超出新长度后面的元素。 示例 2:
     *
     * <p>给定 nums = [0,1,2,2,3,0,4,2], val = 2,
     *
     * <p>函数应该返回新的长度 5, 并且 nums 中的前五个元素为 0, 1, 3, 0, 4。
     *
     * <p>注意这五个元素可为任意顺序。
     *
     * <p>你不需要考虑数组中超出新长度后面的元素。
     *
     * @param nums
     * @param val
     * @return
     */
    public int removeElement(int[] nums, int val) {

        int k = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val) {
                nums[k] = nums[i];
                k++;
            }
        }

        return k;
    }

    /**
     * 最大连续1的个数 给定一个二进制数组， 计算其中最大连续1的个数。
     *
     * <p>示例 1:
     *
     * <p>输入: [1,1,0,1,1,1] 输出: 3 解释: 开头的两位和最后的三位都是连续1，所以最大连续1的个数是 3. 注意：
     *
     * <p>输入的数组只包含 0 和1。 输入数组的长度是正整数，且不超过 10,000。
     */
    @Test
    public void testFindMaxConsecutiveOnes() {
        int[] nums = {1, 1, 0, 1, 0, 1};
        int result = findMaxConsecutiveOnes(nums);
        log.debug("result:{} ", result);
    }

    /**
     * 最大连续1的个数 给定一个二进制数组， 计算其中最大连续1的个数。
     *
     * <p>示例 1:
     *
     * <p>输入: [1,1,0,1,1,1] 输出: 3 解释: 开头的两位和最后的三位都是连续1，所以最大连续1的个数是 3. 注意：
     *
     * <p>输入的数组只包含 0 和1。 输入数组的长度是正整数，且不超过 10,000。
     *
     * @param nums
     * @return
     */
    public int findMaxConsecutiveOnes(int[] nums) {
        int max = 0;

        int count = 0;
        for (int num : nums) {
            if (num == 1) {
                count++;
                if (count > max) {
                    max = count;
                }
            } else {
                count = 0;
            }
        }

        return max;
    }

    /**
     * 长度最小的子数组 给定一个含有 n 个正整数的数组和一个正整数 s ，找出该数组中满足其和 ≥ s 的长度最小的连续子数组。如果不存在符合条件的连续子数组，返回 0。
     *
     * <p>示例:
     *
     * <p>输入: s = 7, nums = [2,3,1,2,4,3] 输出: 2 解释: 子数组 [4,3] 是该条件下的长度最小的连续子数组。 进阶:
     *
     * <p>如果你已经完成了O(n) 时间复杂度的解法, 请尝试 O(n log n) 时间复杂度的解法。
     */
    @Test
    public void testMinSubArrayLen() {
        int[] nums = {2, 3, 1, 2, 4, 3};
        int s = 7;
        int result = minSubArrayLen(s, nums);
        log.debug("result:{} ", result);
    }

    /**
     * 长度最小的子数组 给定一个含有 n 个正整数的数组和一个正整数 s ，找出该数组中满足其和 ≥ s 的长度最小的连续子数组。如果不存在符合条件的连续子数组，返回 0。
     *
     * <p>示例:
     *
     * <p>输入: s = 7, nums = [2,3,1,2,4,3] 输出: 2 解释: 子数组 [4,3] 是该条件下的长度最小的连续子数组。 进阶:
     *
     * <p>如果你已经完成了O(n) 时间复杂度的解法, 请尝试 O(n log n) 时间复杂度的解法。
     *
     * @param s
     * @param nums
     * @return
     */
    public int minSubArrayLen(int s, int[] nums) {
        // 定义一个滑块，往右滑动；
        if (nums.length == 0) {
            return 0;
        }
        int i = 0;
        int j = -1;
        int total = 0;
        int result = nums.length + 1;

        while (i < nums.length) {
            // total 比s小时，右滑
            if (j + 1 < nums.length && total < s) {
                j++;
                total += nums[j];
            } else {
                // total 比s大时，右滑
                total -= nums[i];
                i++;
            }

            if (total >= s) {
                result = Math.min(result, j - i + 1);
            }
        }
        if (result > nums.length) {
            return 0;
        }

        return result;
        /*if (nums.length == 0) {
            return 0;
        }
        int i = 0;
        int j = -1;
        int total = 0;
        int result = nums.length + 1;
        while(i < nums.length) {
            if (j + 1 < nums.length && total < s)  {
                j++;
                total += nums[j];
            } else {
                total -= nums[i];
                i++;
            }

            if (total >= s) {
                result = Math.min(result, j - i + 1);
            }

        }
        if(result > nums.length ) {
            return 0;
        }

        return result;*/
    }

    /**
     * 翻转字符串里的单词 给定一个字符串，逐个翻转字符串中的每个单词。 示例 1：
     *
     * <p>输入: "the sky is blue" 输出: "blue is sky the" 示例 2：
     *
     * <p>输入: " hello world! " 输出: "world! hello" 解释: 输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。 示例 3：
     *
     * <p>输入: "a good example" 输出: "example good a" 解释: 如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。
     *
     * <p>说明：
     *
     * <p>无空格字符构成一个单词。 输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。 如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。
     */
    @Test
    public void testReverseWords() {
        String s = "a good   example";
        String result = reverseWords(s);
        log.debug("result:{} ", result);
    }

    /**
     * 翻转字符串里的单词 给定一个字符串，逐个翻转字符串中的每个单词。 示例 1：
     *
     * <p>输入: "the sky is blue" 输出: "blue is sky the" 示例 2：
     *
     * <p>输入: " hello world! " 输出: "world! hello" 解释: 输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。 示例 3：
     *
     * <p>输入: "a good example" 输出: "example good a" 解释: 如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。
     *
     * <p>说明：
     *
     * <p>无空格字符构成一个单词。 输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。 如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。
     *
     * @param s
     * @return
     */
    public String reverseWords(String s) {

        String[] strs = s.split(" ");
        StringBuilder sb = new StringBuilder();
        for (int i = strs.length - 1; i >= 0; i--) {
            String s1 = strs[i].trim();
            if (sb.length() > 0 && s1.length() > 0) {
                sb.append(" ");
            }

            sb.append(s1);
        }

        return sb.toString();
    }

    /**
     * 反转字符串中的单词 III 给定一个字符串，你需要反转字符串中每个单词的字符顺序，同时仍保留空格和单词的初始顺序。
     *
     * <p>示例 1:
     *
     * <p>输入: "Let's take LeetCode contest" 输出: "s'teL ekat edoCteeL tsetnoc"
     * 注意：在字符串中，每个单词由单个空格分隔，并且字符串中不会有任何额外的空格。
     */
    @Test
    public void testReverseWords2() {
        String s = "Let's take LeetCode contest";
        String result = reverseWords2(s);
        log.debug("result:{} ", result);
    }

    /**
     * 反转字符串中的单词 III 给定一个字符串，你需要反转字符串中每个单词的字符顺序，同时仍保留空格和单词的初始顺序。
     *
     * <p>示例 1:
     *
     * <p>输入: "Let's take LeetCode contest" 输出: "s'teL ekat edoCteeL tsetnoc"
     * 注意：在字符串中，每个单词由单个空格分隔，并且字符串中不会有任何额外的空格。
     *
     * @param s
     * @return
     */
    public String reverseWords2(String s) {
        String[] strs = s.split(" ");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < strs.length; i++) {
            String s1 = strs[i];
            if (sb.length() > 0) {
                sb.append(" ");
            }

            sb.append(reverseString(s1));
        }

        return sb.toString();
    }

    private String reverseString(String s) {

        if (s.length() < 2) {
            return s;
        }
        char[] chars = s.toCharArray();
        int len = chars.length;
        int i = 0, j = len - 1;
        while (i < j) {
            char tmp = chars[i];
            chars[i] = chars[j];
            chars[j] = tmp;
            i++;
            j--;
        }

        return String.valueOf(chars);
    }

    @Test
    public void countAndSay() {
        int num = 7;
        String result = countAndSay(num);
        log.debug("result:{} ", result);
    }

    /**
     * 报数 报数序列是一个整数序列，按照其中的整数的顺序进行报数，得到下一个数。其前五项如下：
     *
     * <p>1. 1 2. 11 3. 21 4. 1211 5. 111221 6 312211 1 被读作 "one 1" ("一个一") , 即 11。 11 被读作 "two 1s"
     * ("两个一"）, 即 21。 21 被读作 "one 2", "one 1" （"一个二" , "一个一") , 即 1211。
     *
     * <p>给定一个正整数 n（1 ≤ n ≤ 30），输出报数序列的第 n 项。
     *
     * <p>注意：整数顺序将表示为一个字符串。
     *
     * <p>示例 1:
     *
     * <p>输入: 1 输出: "1" 示例 2:
     *
     * <p>输入: 4 输出: "1211"
     *
     * @param n
     * @return
     */
    public String countAndSay(int n) {
        String result = "1";
        while (n > 1) {
            // 循环n次，每次用上次的结点 读数
            result = getSay(result);
            n--;
        }
        return result;
    }

    private String getSay(String s) {

        StringBuilder sb = new StringBuilder();
        char[] chars = s.toCharArray();

        int count = 1;
        Character lastChar = chars[0];
        for (int i = 1; i < chars.length; i++) {
            char c = chars[i];
            // 记录报数的个数，如果和上个字符相等，累加，不等报数 count个lastChar字符
            if (Objects.equals(lastChar, c)) {
                count++;
            } else {
                sb.append(count);
                sb.append(lastChar);
                count = 1;
            }

            lastChar = c;
        }
        sb.append(count);
        sb.append(lastChar);

        return sb.toString();
    }

    @Test
    public void reverse() {
        int num = 120;
        int result = reverse(num);
        log.debug("result:{} ", result);
    }

    /**
     * 整数反转 给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。
     *
     * <p>示例 1:
     *
     * <p>输入: 123 输出: 321 示例 2:
     *
     * <p>输入: -123 输出: -321 示例 3:
     *
     * <p>输入: 120 输出: 21 注意:
     *
     * <p>假设我们的环境只能存储得下 32 位的有符号整数，则其数值范围为 [−2^31, 2^31 − 1]。 请根据这个假设，如果反转后整数溢出那么就返回 0。
     *
     * @param x
     * @return
     */
    public int reverse(int x) {
        int m = 0;
        while (x != 0) {
            int pop = x % 10;
            x /= 10;
            if (m > Integer.MAX_VALUE / 10 || (m == Integer.MAX_VALUE / 10 && pop > 7)) return 0;
            if (m < Integer.MIN_VALUE / 10 || (m == Integer.MIN_VALUE / 10 && pop < -8)) return 0;
            m = m * 10 + pop;
        }
        return m;
        /*String s = String.valueOf(x);
        char[] chars = s.toCharArray();

        int i = 0, j= chars.length -1;
        while (i < j) {
            if (chars[i] == '-') {
                i++;
                continue;
            }
            char tmp = chars[i];
            chars[i] = chars[j];
            chars[j] = tmp;
            i++;
            j--;
        }
        String a = String.valueOf(chars);
        Long num = Long.valueOf(a);
        if (num < Integer.MIN_VALUE || num > Integer.MAX_VALUE) {
            return 0;
        }

        return num.intValue();*/

    }

    @Test
    public void myAtoi() {
        String s = "-91283472332";
        int result = myAtoi(s);
        log.debug("result:{} ", result);
    }

    /**
     * 字符串转换整数 (atoi) 请你来实现一个 atoi 函数，使其能将字符串转换成整数。
     *
     * <p>首先，该函数会根据需要丢弃无用的开头空格字符，直到寻找到第一个非空格的字符为止。
     *
     * <p>当我们寻找到的第一个非空字符为正或者负号时，则将该符号与之后面尽可能多的连续数字组合起来，作为该整数的正负号；假如第一个非空字符是数字，则直接将其与之后连续的数字字符组合起来，形成整数。
     *
     * <p>该字符串除了有效的整数部分之后也可能会存在多余的字符，这些字符可以被忽略，它们对于函数不应该造成影响。
     *
     * <p>注意：假如该字符串中的第一个非空格字符不是一个有效整数字符、字符串为空或字符串仅包含空白字符时，则你的函数不需要进行转换。
     *
     * <p>在任何情况下，若函数不能进行有效的转换时，请返回 0。
     *
     * <p>说明：
     *
     * <p>假设我们的环境只能存储 32 位大小的有符号整数，那么其数值范围为 [−231, 231 − 1]。如果数值超过这个范围，qing返回 INT_MAX (231 − 1) 或
     * INT_MIN (−231) 。
     *
     * <p>示例 1:
     *
     * <p>输入: "42" 输出: 42 示例 2:
     *
     * <p>输入: " -42" 输出: -42 解释: 第一个非空白字符为 '-', 它是一个负号。 我们尽可能将负号与后面所有连续出现的数字组合起来，最后得到 -42 。 示例 3:
     *
     * <p>输入: "4193 with words" 输出: 4193 解释: 转换截止于数字 '3' ，因为它的下一个字符不为数字。 示例 4:
     *
     * <p>输入: "words and 987" 输出: 0 解释: 第一个非空字符是 'w', 但它不是数字或正、负号。 因此无法执行有效的转换。 示例 5:
     *
     * <p>输入: "-91283472332" 输出: -2147483648 解释: 数字 "-91283472332" 超过 32 位有符号整数范围。 因此返回 INT_MIN
     * (−231) 。
     *
     * @param str
     * @return
     */
    public int myAtoi(String str) {
        char[] chars = str.toCharArray();
        int len = chars.length;
        if (len == 0) {
            return 0;
        }
        int start = 0;

        List<Character> list = new ArrayList<>();
        while (start < len && chars[start] == ' ') {
            start++;
        }
        if (start == len) {
            return 0;
        }
        char startChar = chars[start];
        if (!isNumber(startChar) && !isPlusOrMinus(startChar)) {
            return 0;
        }
        int bPlus = 1;
        if (startChar == '+') {
            bPlus = 1;
            start++;
        } else if (startChar == '-') {
            bPlus = -1;
            start++;
        }
        int end = start;
        while (end < len && isNumber(chars[end])) {
            end++;
        }
        int sum = 0;
        for (int i = start; i < end; i++) {
            int m = bPlus * (chars[i] - '0');

            if (sum > Integer.MAX_VALUE / 10 || (sum == Integer.MAX_VALUE / 10 && m > 7)) {
                return Integer.MAX_VALUE;
            }
            if (sum < Integer.MIN_VALUE / 10 || (sum == Integer.MIN_VALUE / 10 && m < -8)) {
                return Integer.MIN_VALUE;
            }
            sum = sum * 10 + m;
        }
        return sum;
    }

    private boolean isPlusOrMinus(char num) {
        switch (num) {
            case '+':
            case '-':
                return true;
            default:
                return false;
        }
    }

    private boolean isNumber(char num) {
        switch (num) {
            case '0':
            case '1':
            case '2':
            case '3':
            case '4':
            case '5':
            case '6':
            case '7':
            case '8':
            case '9':
                return true;
            default:
                return false;
        }
    }

    /**
     * 交错字符串 给定三个字符串 s1, s2, s3, 验证 s3 是否是由 s1 和 s2 交错组成的。
     *
     * <p>示例 1:
     *
     * <p>输入: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac" 输出: true 示例 2:
     *
     * <p>输入: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbbaccc" 输出: false
     *
     * @param s1
     * @param s2
     * @param s3
     * @return
     */
    public boolean isInterleave(String s1, String s2, String s3) {
        // 使用动态规划,构建一个二维数组 row = s1.length + 1, col = s2.length + 1
        int rowLen = s1.length();
        int colLen = s2.length();
        if (s3.length() != rowLen + colLen) {
            return false;
        }

        boolean[][] dp = new boolean[rowLen + 1][colLen + 1];
        dp[0][0] = true;
        for (int i = 0; i <= rowLen; i++) {
            for (int j = 0; j <= colLen; j++) {
                if (i == 0 && j == 0) {

                } else if (i == 0) {
                    dp[i][j] = dp[i][j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1);
                } else if (j == 0) {
                    dp[i][j] = dp[i - 1][j] && s1.charAt(i - 1) == s3.charAt(i + j - 1);
                } else {
                    dp[i][j] =
                            (dp[i][j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1))
                                    || (dp[i - 1][j] && s1.charAt(i - 1) == s3.charAt(i + j - 1));
                }
            }
        }

        return dp[rowLen][colLen];
    }

    @Test
    public void compareVersion() {
        String version1 = "1.0";
        String version2 = "1.0.1";
        int result = compareVersion(version1, version2);
        log.debug("result:{}", result);
    }

    /**
     * 比较版本号 比较两个版本号 version1 和 version2。 如果 version1 > version2 返回 1，如果 version1 < version2 返回 -1，
     * 除此之外返回 0。
     *
     * <p>你可以假设版本字符串非空，并且只包含数字和 . 字符。
     *
     * <p>. 字符不代表小数点，而是用于分隔数字序列。
     *
     * <p>例如，2.5 不是“两个半”，也不是“差一半到三”，而是第二版中的第五个小版本。
     *
     * <p>你可以假设版本号的每一级的默认修订版号为 0。例如，版本号 3.4 的第一级（大版本）和第二级（小版本）修订号分别为 3 和 4。其第三级和第四级修订号均为 0。
     *
     * <p>示例 1:
     *
     * <p>输入: version1 = "0.1", version2 = "1.1" 输出: -1 示例 2:
     *
     * <p>输入: version1 = "1.0.1", version2 = "1" 输出: 1 示例 3:
     *
     * <p>输入: version1 = "7.5.2.4", version2 = "7.5.3" 输出: -1 示例 4：
     *
     * <p>输入：version1 = "1.01", version2 = "1.001" 输出：0 解释：忽略前导零，“01” 和 “001” 表示相同的数字 “1”。 示例 5：
     *
     * <p>输入：version1 = "1.0", version2 = "1.0.0" 输出：0 解释：version1 没有第三级修订号，这意味着它的第三级修订号默认为 “0”。
     *
     * <p>提示：
     *
     * <p>版本字符串由以点 （.） 分隔的数字字符串组成。这个数字字符串可能有前导零。 版本字符串不以点开始或结束，并且其中不会有两个连续的点。
     *
     * @param version1
     * @param version2
     * @return
     */
    public int compareVersion(String version1, String version2) {
        String[] array1 = version1.split("\\.");
        String[] array2 = version2.split("\\.");
        int len1 = array1.length;
        int len2 = array2.length;
        int min = Math.min(len1, len2);
        for (int i = 0; i < min; i++) {

            int v1 = Integer.valueOf(array1[i]);

            int v2 = Integer.valueOf(array2[i]);
            if (v1 > v2) {
                return 1;
            }
            if (v1 < v2) {
                return -1;
            }
        }
        if (array1.length > array2.length) {
            for (int i = min; i < array1.length; i++) {
                int v1 = Integer.valueOf(array1[i]);
                if (v1 != 0) {
                    return 1;
                }
            }
        }
        if (array1.length < array2.length) {
            for (int i = min; i < array2.length; i++) {
                int v2 = Integer.valueOf(array2[i]);
                if (v2 != 0) {
                    return -1;
                }
            }
        }

        return 0;
    }

    @Test
    public void orderlyQueue() {

        String S = "cba";
        int K = 1;
        String result = orderlyQueue(S, K);
        log.debug("result:{}", result);
    }
    /**
     * 有序队列 给出了一个由小写字母组成的字符串 S。然后，我们可以进行任意次数的移动。
     *
     * <p>在每次移动中，我们选择前 K 个字母中的一个（从左侧开始），将其从原位置移除，并放置在字符串的末尾。
     *
     * <p>返回我们在任意次数的移动之后可以拥有的按字典顺序排列的最小字符串。
     *
     * <p>示例 1：
     *
     * <p>输入：S = "cba", K = 1 输出："acb" 解释： 在第一步中，我们将第一个字符（“c”）移动到最后，获得字符串 “bac”。
     * 在第二步中，我们将第一个字符（“b”）移动到最后，获得最终结果 “acb”。 示例 2：
     *
     * <p>输入：S = "baaca", K = 3 输出："aaabc" 解释： 在第一步中，我们将第一个字符（“b”）移动到最后，获得字符串 “aacab”。
     * 在第二步中，我们将第三个字符（“c”）移动到最后，获得最终结果 “aaabc”。
     *
     * <p>提示：
     *
     * <p>1 <= K <= S.length <= 1000 S 只由小写字母组成。
     *
     * @param S
     * @param K
     * @return
     */
    public String orderlyQueue(String S, int K) {

        String result = "";
        int len = S.length();
        if (K == 1) {
            result = S;
            for (int i = 0; i < S.length(); ++i) {
                String str = S.substring(i) + S.substring(0, i);
                if (str.compareTo(result) < 0) {
                    result = str;
                }
            }
        } else {
            char[] chars = S.toCharArray();
            Arrays.sort(chars);
            result = new String(chars);
        }

        return result;
    }

    @Test
    public void multiply() {
        String num1 = "123456789", num2 = "987654321";

        String result = multiply(num1, num2);
        log.debug("result:{}", result);
    }

    /**
     * 字符串相乘 给定两个以字符串形式表示的非负整数 num1 和 num2，返回 num1 和 num2 的乘积，它们的乘积也表示为字符串形式。
     *
     * <p>示例 1:
     *
     * <p>输入: num1 = "2", num2 = "3" 输出: "6" 示例 2:
     *
     * <p>输入: num1 = "123", num2 = "456" 输出: "56088" 说明：
     *
     * <p>num1 和 num2 的长度小于110。 num1 和 num2 只包含数字 0-9。 num1 和 num2 均不以零开头，除非是数字 0 本身。
     * 不能使用任何标准库的大数类型（比如 BigInteger）或直接将输入转换为整数来处理。
     *
     * @param num1
     * @param num2
     * @return
     */
    public String multiply(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0")) {
            return "0";
        }
        // 通过数组存储每一位数字
        int[] res = new int[num1.length() + num2.length()];
        for (int i = num1.length() - 1; i >= 0; i--) {
            int n1 = num1.charAt(i) - '0';
            for (int j = num2.length() - 1; j >= 0; j--) {
                int n2 = num2.charAt(j) - '0';
                int sum = (res[i + j + 1] + n1 * n2);
                res[i + j + 1] = sum % 10;
                res[i + j] += sum / 10;
            }
        }

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < res.length; i++) {
            if (i == 0 && res[i] == 0) continue;
            result.append(res[i]);
        }
        return result.toString();
    }

    @Test
    public void simplifyPath() {
        String path = "/a";
        String result = simplifyPath(path);
        log.debug("result:{}", result);
    }

    /**
     * 简化路径 以 Unix 风格给出一个文件的绝对路径，你需要简化它。或者换句话说，将其转换为规范路径。
     *
     * <p>在 Unix 风格的文件系统中，一个点（.）表示当前目录本身；此外，两个点 （..）
     * 表示将目录切换到上一级（指向父目录）；两者都可以是复杂相对路径的组成部分。更多信息请参阅：Linux / Unix中的绝对路径 vs 相对路径
     *
     * <p>请注意，返回的规范路径必须始终以斜杠 / 开头，并且两个目录名之间必须只有一个斜杠 /。最后一个目录名（如果存在）不能以 / 结尾。此外，规范路径必须是表示绝对路径的最短字符串。
     *
     * <p>示例 1：
     *
     * <p>输入："/home/" 输出："/home" 解释：注意，最后一个目录名后面没有斜杠。 示例 2：
     *
     * <p>输入："/../" 输出："/" 解释：从根目录向上一级是不可行的，因为根是你可以到达的最高级。 示例 3：
     *
     * <p>输入："/home//foo/" 输出："/home/foo" 解释：在规范路径中，多个连续斜杠需要用一个斜杠替换。 示例 4：
     *
     * <p>输入："/a/./b/../../c/" 输出："/c" 示例 5：
     *
     * <p>输入："/a/../../b/../c//.//" 输出："/c" 示例 6：
     *
     * <p>输入："/a//b////c/d//././/.." 输出："/a/b/c"
     *
     * @param path
     * @return
     */
    public String simplifyPath(String path) {
        if (path.length() <= 1) {
            return "/";
        }
        // 思路，通过栈存储路径；
        LinkedList<String> linkedList = new LinkedList<>();

        int index = 0;
        for (int i = 0; i < path.length(); i++) {
            char c = path.charAt(i);
            if (c == '/') {
                if (index < i) {
                    String dir = path.substring(index, i);
                    if (Objects.equals(dir, ".")) {
                        // 什么都不做
                    } else if (Objects.equals(dir, "..")) {
                        // 出栈
                        linkedList.pollLast();
                    } else {
                        // 入栈
                        linkedList.offerLast(dir);
                    }
                }
                index = i + 1;
            }
        }

        if (index < path.length()) {
            String dir = path.substring(index, path.length());
            if (Objects.equals(dir, ".")) {
                // 什么都不做
            } else if (Objects.equals(dir, "..")) {
                // 出栈
                linkedList.pollLast();
            } else {
                // 入栈
                linkedList.offerLast(dir);
            }
        }

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < linkedList.size(); i++) {
            result.append("/");
            result.append(linkedList.get(i));
        }
        if (result.length() == 0) {
            result.append("/");
        }
        return result.toString();
    }

    @Test
    public void restoreIpAddresses() {
        String s = "25525511135";
        List<String> list = restoreIpAddresses(s);
        for (String a : list) {
            log.debug("a:{}", a);
        }
    }

    /**
     * 复原IP地址 给定一个只包含数字的字符串，复原它并返回所有可能的 IP 地址格式。
     *
     * <p>示例:
     *
     * <p>输入: "25525511135" 输出: ["255.255.11.135", "255.255.111.35"]
     *
     * @param s
     * @return
     */
    public List<String> restoreIpAddresses(String s) {
        List<String> list = checkIp(s, 4);

        return list;
    }

    private List<String> checkIp(String s, int count) {
        List<String> list = new ArrayList<>();

        if (count == 1) {
            if (s.charAt(0) == '0' && s.length() > 1) {
                return list;
            }
            if (Integer.parseInt(s) <= 255) {
                list.add(s);
            }
            return list;
        }
        if (s.length() > count * 3) {
            return list;
        }

        for (int i = 1; i <= 3 && i < s.length(); i++) {
            StringBuilder sb = new StringBuilder();

            String segment = s.substring(0, i);
            /*if (num == 0 && i < 3 && i < s.length() - 1 && s.charAt(i+1) == '0') {
                break;
            }*/
            // 剪枝条件：不能以0开头，不能大于255
            if (segment.startsWith("0") && segment.length() > 1
                    || (i == 3 && Integer.parseInt(segment) > 255)) {
                break;
            }

            sb.append(Integer.parseInt(segment));
            sb.append(".");
            List<String> nextIpList = checkIp(s.substring(i, s.length()), count - 1);
            for (String ip : nextIpList) {
                StringBuilder nextIp = new StringBuilder(sb);
                nextIp.append(ip);
                list.add(nextIp.toString());
            }
        }
        return list;
    }

    @Test
    public void partition() {
        String s = "aabb";
        List<List<String>> result = partition(s);
        for (List<String> list : result) {
            log.debug("result:{}", list);
        }
    }

    /**
     * 分割回文串 给定一个字符串 s，将 s 分割成一些子串，使每个子串都是回文串。
     *
     * <p>返回 s 所有可能的分割方案。
     *
     * <p>示例:
     *
     * <p>输入: "aab" 输出: [ ["aa","b"], ["a","a","b"] ]
     *
     * @param s
     * @return
     */
    public List<List<String>> partition(String s) {
        int len = s.length();
        List<List<String>> result = new ArrayList<>();
        if (len == 0) {
            return result;
        }
        Deque<String> stack = new ArrayDeque<>();

        // 状态：dp[i][j] 表示 s.substring(i,j) 是否是回文
        boolean[][] dp = new boolean[len][len];
        for (int i = 0; i < len; i++) {
            dp[i][i] = true;
        }
        backtracking(s, 0, len, dp, stack, result);

        return result;
    }

    private void backtracking(
            String s,
            int start,
            int len,
            boolean[][] dp,
            Deque<String> stack,
            List<List<String>> result) {
        if (start == len) {
            result.add(new ArrayList<>(stack));
        }
        for (int i = start; i < len; i++) {
            if (!dp[start][i] && !checkPalindrome(s, start, i)) {
                continue;
            }
            dp[start][i] = true;
            stack.addLast(s.substring(start, i + 1));
            backtracking(s, i + 1, len, dp, stack, result);
            stack.removeLast();
        }
    }

    /**
     * 判断是否回文字符串
     *
     * @param s
     * @param start
     * @param end
     * @return
     */
    private boolean checkPalindrome(String s, int start, int end) {
        while (start < end) {
            if (s.charAt(start) != s.charAt(end)) {
                return false;
            }
            start++;
            end--;
        }

        return true;
    }

    @Test
    public void distinctSubseqII() {
        String s = "aab";
        int result = distinctSubseqII(s);
        logResult(result);
    }

    /**
     * 不同的子序列 II 给定一个字符串 S，计算 S 的不同非空子序列的个数。
     *
     * <p>因为结果可能很大，所以返回答案模 10^9 + 7.
     *
     * <p>示例 1：
     *
     * <p>输入："abc" 输出：7 解释：7 个不同的子序列分别是 "a", "b", "c", "ab", "ac", "bc", 以及 "abc"。 示例 2：
     *
     * <p>输入："aba" 输出：6 解释：6 个不同的子序列分别是 "a", "b", "ab", "ba", "aa" 以及 "aba"。 示例 3：
     *
     * <p>输入："aaa" 输出：3 解释：3 个不同的子序列分别是 "a", "aa" 以及 "aaa"。
     *
     * <p>提示：
     *
     * <p>S 只包含小写字母。
     *
     * @param S
     * @return
     */
    public int distinctSubseqII(String S) {
        int result = 0;
        int MOD = 1_000_000_007;
        int len = S.length();
        int[] nums = new int[len + 1];
        nums[0] = 1;
        Integer[] letterIndex = new Integer[26];
        // 思路 根据组合数公式 n个不同的数 组合为 2^n （包括 空集合） 中
        // "a" - "" "a" 2
        // "ab" - "" "a" "b"("" + "b") "ab"("a" + "b")  4
        // "abc" - "" "a" "b" "ab" "c"(""+"c") "ac"("a"+"c") "bc"("b"+"c")"abc"("ab"+"c")  8
        // "aba" - "" "a" "b" "ab" (""+"a" 舍弃) "aa"("a"+"a") "ba"("b"+"a")"aba"("ab"+"a")  7
        // "abaa" - "" "a" "b" "ab" "aa" "ba" "aba"
        //  (""+"a" 舍弃)("a"+"a" 舍弃)("b"+"a" 舍弃)("ab"+"a" 舍弃) "aaa"("aa"+"a")
        // "baa"("b"+"aa")"abaa"("ab"a+"a") 10(7*2-4)
        // num[i+1] = num[i] * 2 - 上一个a的前一个字符的个数
        for (int i = 0; i < len; i++) {
            char c = S.charAt(i);
            Integer index = letterIndex[c - 'a'];
            nums[i + 1] = nums[i] * 2 % MOD;
            if (index != null) {
                nums[i + 1] -= nums[index];
            }
            nums[i + 1] %= MOD;
            letterIndex[c - 'a'] = i;
        }
        result = nums[len] - 1;
        if (result < 0) {
            result += MOD;
        }
        return result;
    }

    @Test
    public void findRotateSteps() {
        // String ring =  "abc", key = "aabbcc";
        String ring = "caotmcaataijjxi", key = "oatjiioicitatajtijciocjcaaxaaatmctxamacaamjjx";
        // String ring =  "abcde", key = "ade";
        int result = findRotateSteps(ring, key);
        log.debug("result:{}", result);
    }

    /**
     * 自由之路 视频游戏“辐射4”中，任务“通向自由”要求玩家到达名为“Freedom Trail Ring”的金属表盘，并使用表盘拼写特定关键词才能开门。
     *
     * <p>给定一个字符串 ring，表示刻在外环上的编码；给定另一个字符串 key，表示需要拼写的关键词。您需要算出能够拼写关键词中所有字符的最少步数。
     *
     * <p>最初，ring 的第一个字符与12:00方向对齐。您需要顺时针或逆时针旋转 ring 以使 key 的一个字符在 12:00 方向对齐，然后按下中心按钮， 以此逐个拼写完 key
     * 中的所有字符。
     *
     * <p>旋转 ring 拼出 key 字符 key[i] 的阶段中：
     *
     * <p>您可以将 ring 顺时针或逆时针旋转一个位置，计为1步。旋转的最终目的是将字符串 ring 的一个字符与 12:00 方向对齐， 并且这个字符必须等于字符 key[i] 。
     * 如果字符 key[i] 已经对齐到12:00方向，您需要按下中心按钮进行拼写，这也将算作 1 步。按完之后，您可以开始拼写 key 的下一个字符（下一阶段）, 直至完成所有拼写。 示例：
     *
     * <p>输入: ring = "godding", key = "gd" 输出: 4 解释: 对于 key 的第一个字符 'g'，已经在正确的位置, 我们只需要1步来拼写这个字符。 对于
     * key 的第二个字符 'd'，我们需要逆时针旋转 ring "godding" 2步使它变成 "ddinggo"。 当然, 我们还需要1步进行拼写。 因此最终的输出是 4。 提示：
     *
     * <p>ring 和 key 的字符串长度取值范围均为 1 至 100； 两个字符串中都只有小写字符，并且均可能存在重复字符； 字符串 key 一定可以由字符串 ring 旋转拼出。
     *
     * @param ring
     * @param key
     * @return
     */
    public int findRotateSteps(String ring, String key) {
        /*char[] rings=ring.toCharArray();
        List<Integer>[] rs=new List[26];
        for (int i=0;i<26;i++)rs[i]=new ArrayList<>();
        for (int i=0;i<rings.length;i++)
        {
            rs[rings[i]-'a'].add(i);
        }

        char[] keys=key.toCharArray();
        int[][] dp=new int[ring.length()+1][key.length()+1];
        for (int i=0;i<dp.length;i++) Arrays.fill(dp[i],-1);

        return slove(0,0,keys,rs,dp,ring.length());*/
        int len = ring.length();
        Map<Character, List<Integer>> map = new HashMap<>();

        for (int i = 0; i < len; i++) {
            char c = ring.charAt(i);
            List<Integer> list = map.get(c);
            if (list == null) {
                list = new ArrayList<>();
                map.put(c, list);
            }
            list.add(i);
        }
        int[][] dp = new int[ring.length() + 1][key.length() + 1];
        for (int i = 0; i < dp.length; i++) Arrays.fill(dp[i], -1);
        int result = findRotateSteps(map, dp, len, 0, key, 0);
        return result + key.length();
    }

    private int slove(
            int r_idx, int k_idx, char[] keys, List<Integer>[] rs, int[][] dp, final int rlen) {
        if (k_idx == keys.length) return 0;
        if (dp[r_idx][k_idx] != -1) return dp[r_idx][k_idx];
        int res = Integer.MAX_VALUE;
        for (int i : rs[keys[k_idx] - 'a']) {
            int t =
                    1
                            + Math.min(
                                    Math.abs(r_idx - i),
                                    Math.min(
                                            Math.abs(i + rlen - r_idx),
                                            Math.abs(r_idx + rlen - i)));
            t += slove(i, k_idx + 1, keys, rs, dp, rlen);

            res = Math.min(res, t);
        }
        return dp[r_idx][k_idx] = res;
    }

    private int findRotateSteps(
            Map<Character, List<Integer>> map,
            int[][] dp,
            int len,
            int start,
            String key,
            int keyIndex) {
        if (keyIndex >= key.length()) {
            return 0;
        }
        // 如果 key 中有很多重复元素, 需要记忆, 防止大量重复运算
        if (dp[start][keyIndex] != -1) {
            return dp[start][keyIndex];
        }
        char c = key.charAt(keyIndex);
        List<Integer> list = map.get(c);
        int min = Integer.MAX_VALUE;
        int result = 0;
        int helf = len >> 1;
        for (Integer index : list) {
            // 距离 d 大于一半时 可以反向
            int d = index >= start ? index - start : start - index;
            if (d > helf) {
                d = len - d;
            }

            result = d + findRotateSteps(map, dp, len, index, key, keyIndex + 1);
            if (result < min) {
                min = result;
            }
        }
        return dp[start][keyIndex] = min;
    }

    @Test
    public void wordBreak() {
        // String s = "catsandog";
        String s =
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaab";
        List<String> wordDict = new ArrayList<>();
        /*wordDict.add("cats");
        wordDict.add("dog");
        wordDict.add("and");
        wordDict.add("cat");*/
        wordDict.add("a");
        wordDict.add("aa");
        wordDict.add("aaa");
        wordDict.add("aaaa");
        wordDict.add("aaaaa");
        wordDict.add("aaaaaa");
        wordDict.add("aaaaaaaaaaaaaaaaaaa");
        boolean result = wordBreak(s, wordDict);
        log.debug("result:{}", result);
    }

    @Test
    public void stringTest() {
        String a = "123456";
        log.debug(a.substring(4, a.length() - 1));
    }

    /**
     * 单词拆分 给定一个非空字符串 s 和一个包含非空单词列表的字典 wordDict，判定 s 是否可以被空格拆分为一个或多个在字典中出现的单词。
     *
     * <p>说明：
     *
     * <p>拆分时可以重复使用字典中的单词。 你可以假设字典中没有重复的单词。 示例 1：
     *
     * <p>输入: s = "leetcode", wordDict = ["leet", "code"] 输出: true 解释: 返回 true 因为 "leetcode" 可以被拆分成
     * "leet code"。 示例 2：
     *
     * <p>输入: s = "applepenapple", wordDict = ["apple", "pen"] 输出: true 解释: 返回 true 因为
     * "applepenapple" 可以被拆分成 "apple pen apple"。 注意你可以重复使用字典中的单词。 示例 3：
     *
     * <p>输入: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"] 输出: false
     *
     * @param s
     * @param wordDict
     * @return
     */
    public boolean wordBreak(String s, List<String> wordDict) {
        // dp 表示第 0~i 位的字符串是否可以分割
        /*boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && wordDict.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length()];*/
        Boolean[] array = new Boolean[s.length()];
        return wordBreak(s, 0, wordDict, array);
    }

    private boolean wordBreak(String s, int start, List<String> wordDict, Boolean[] array) {

        if (start == s.length()) {
            return true;
        }
        if (array[start] != null) {
            return array[start];
        }

        for (int i = start + 1; i <= s.length(); i++) {
            if (wordDict.contains(s.substring(start, i)) && wordBreak(s, i, wordDict, array)) {
                return array[start] = true;
            }
        }

        return array[start] = false;
    }

    @Test
    public void wordBreak2() {
        String s = "catsanddog";
        List<String> wordDict = new ArrayList<>();
        wordDict.add("cats");
        wordDict.add("dog");
        wordDict.add("and");
        wordDict.add("sand");
        wordDict.add("cat");
        log.debug("start ");
        List<String> result = wordBreak2(s, wordDict);
        log.debug("result:{}", result);
    }

    /**
     * 单词拆分 II 给定一个非空字符串 s 和一个包含非空单词列表的字典 wordDict，在字符串中增加空格来构建一个句子，使得句子中所有的单词都在词典中。返回所有这些可能的句子。
     *
     * <p>说明：
     *
     * <p>分隔时可以重复使用字典中的单词。 你可以假设字典中没有重复的单词。 示例 1：
     *
     * <p>输入: s = "catsanddog" wordDict = ["cat", "cats", "and", "sand", "dog"] 输出: [ "cats and
     * dog", "cat sand dog" ] 示例 2：
     *
     * <p>输入: s = "pineapplepenapple" wordDict = ["apple", "pen", "applepen", "pine", "pineapple"]
     * 输出: [ "pine apple pen apple", "pineapple pen apple", "pine applepen apple" ] 解释:
     * 注意你可以重复使用字典中的单词。 示例 3：
     *
     * <p>输入: s = "catsandog" wordDict = ["cats", "dog", "sand", "and", "cat"] 输出: []
     *
     * @param s
     * @param wordDict
     * @return
     */
    public List<String> wordBreak2(String s, List<String> wordDict) {
        Set<String> set = new HashSet<>();
        for (String word : wordDict) {
            set.add(word);
        }
        Map<String, List<String>> map = new HashMap<>();

        return wordBreakHelper(s, set, map);
    }

    // 回溯算法
    private List<String> wordBreakHelper(
            String s, Set<String> wordDict, Map<String, List<String>> map) {

        if (map.containsKey(s)) {
            return map.get(s);
        }

        List<String> result = new ArrayList<>();
        for (int i = 1; i <= s.length(); i++) {
            // 判断当前字符串是否存在
            String word = s.substring(0, i);
            if (wordDict.contains(word)) {
                if (i == s.length()) {
                    result.add(word);
                } else {
                    List<String> list = wordBreakHelper(s.substring(i, s.length()), wordDict, map);
                    for (String l : list) {
                        StringBuilder sb = new StringBuilder(word);
                        sb.append(" ");
                        sb.append(l);
                        result.add(sb.toString());
                    }
                }
            }
        }
        map.put(s, result);
        return result;
    }

    public void findWords() {

        char[][] board = {
            {'o', 'a', 'a', 'n'}, {'e', 't', 'a', 'e'}, {'i', 'h', 'k', 'r'}, {'i', 'f', 'l', 'v'}
        };
        String[] words = {"oath", "pea", "eat", "rain"};
        List<String> result = findWords(board, words);
        log.debug("result:{}", result);
    }
    /**
     * 单词搜索 II 给定一个二维网格 board 和一个字典中的单词列表 words，找出所有同时在二维网格和字典中出现的单词。
     *
     * <p>单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母在一个单词中不允许被重复使用。
     *
     * <p>示例:
     *
     * <p>输入: words = ["oath","pea","eat","rain"] and board = [ ['o','a','a','n'],
     * ['e','t','a','e'], ['i','h','k','r'], ['i','f','l','v'] ]
     *
     * <p>输出: ["eat","oath"] 说明: 你可以假设所有输入都由小写字母 a-z 组成。
     *
     * <p>提示:
     *
     * <p>你需要优化回溯算法以通过更大数据量的测试。你能否早点停止回溯？
     * 如果当前单词不存在于所有单词的前缀中，则可以立即停止回溯。什么样的数据结构可以有效地执行这样的操作？散列表是否可行？为什么？
     * 前缀树如何？如果你想学习如何实现一个基本的前缀树，请先查看这个问题： 实现Trie（前缀树）。
     *
     * @param board
     * @param words
     * @return
     */
    public List<String> findWords(char[][] board, String[] words) {
        List<String> result = new ArrayList<>();
        int rowNum = board.length;
        if (rowNum == 0) {
            return result;
        }
        int colNum = board[0].length;
        if (colNum == 0) {
            return result;
        }
        boolean[][] marked = new boolean[rowNum][colNum];
        Trie trie = new Trie();
        for (String word : words) {
            trie.insert(word);
        }
        for (int i = 0; i < rowNum; i++) {
            for (int j = 0; j < colNum; j++) {
                existWord(board, i, j, trie, result);
            }
        }
        return result;
    }

    private void existWord(char[][] board, int row, int col, Trie trie, List<String> result) {}

    private boolean exist(char[][] board, String word) {
        int rowNum = board.length;
        if (rowNum == 0) {
            return false;
        }
        int colNum = board[0].length;
        if (colNum == 0) {
            return false;
        }

        boolean[][] marked = new boolean[rowNum][colNum];

        for (int i = 0; i < rowNum; i++) {

            for (int j = 0; j < colNum; j++) {
                if (existWord(i, j, 0, board, marked, word)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean existWord(
            int row, int col, int index, char[][] board, boolean[][] marked, String word) {
        int rowNum = board.length;
        int colNum = board[0].length;
        if (index == word.length() - 1) {
            return board[row][col] == word.charAt(index);
        }
        if (board[row][col] == word.charAt(index)) {
            marked[row][col] = true;
            if (inArea(row + 1, col, rowNum, colNum)
                    && !marked[row + 1][col]
                    && existWord(row + 1, col, index + 1, board, marked, word)) {
                return true;
            }
            if (inArea(row - 1, col, rowNum, colNum)
                    && !marked[row - 1][col]
                    && existWord(row - 1, col, index + 1, board, marked, word)) {
                return true;
            }
            if (inArea(row, col + 1, rowNum, colNum)
                    && !marked[row][col + 1]
                    && existWord(row, col + 1, index + 1, board, marked, word)) {
                return true;
            }
            if (inArea(row, col - 1, rowNum, colNum)
                    && !marked[row][col - 1]
                    && existWord(row, col - 1, index + 1, board, marked, word)) {
                return true;
            }

            marked[row][col] = false;
        }

        return false;
    }

    private boolean inArea(int row, int col, int rowNum, int colNum) {
        return row >= 0 && row < rowNum && col >= 0 && col < colNum;
    }

    @Test
    public void minWindow() {
        String s = "ADOBECODEBANC";
        String t = "ABC";
        String result = minWindow(s, t);
        log.debug("result:{}", result);
    }

    /**
     * 最小覆盖子串 给你一个字符串 S、一个字符串 T，请在字符串 S 里面找出：包含 T 所有字母的最小子串。
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
        if (s.length() == 0 || t.length() == 0) {
            return "";
        }
        // 思路 ： 滑动窗口
        Map<Character, Integer> dictT = new HashMap<>();

        for (int i = 0; i < t.length(); i++) {
            int count = dictT.getOrDefault(t.charAt(i), 0);
            dictT.put(t.charAt(i), count + 1);
        }

        int required = dictT.size();

        List<Pair<Integer, Character>> filteredS = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (dictT.containsKey(c)) {
                filteredS.add(new Pair<>(i, c));
            }
        }

        int l = 0, r = 0, formed = 0;
        Map<Character, Integer> windowCounts = new HashMap<>();
        int[] ans = {-1, 0, 0};

        while (r < filteredS.size()) {
            char c = filteredS.get(r).getValue();
            int count = windowCounts.getOrDefault(c, 0);
            windowCounts.put(c, count + 1);

            if (dictT.containsKey(c) && windowCounts.get(c).intValue() == dictT.get(c).intValue()) {
                formed++;
            }

            while (l <= r && formed == required) {
                c = filteredS.get(l).getValue();
                int end = filteredS.get(r).getKey();
                int start = filteredS.get(l).getKey();
                if (ans[0] == -1 || end - start + 1 < ans[0]) {
                    ans[0] = end - start + 1;
                    ans[1] = start;
                    ans[2] = end;
                }

                windowCounts.put(c, windowCounts.get(c) - 1);
                if (dictT.containsKey(c)
                        && windowCounts.get(c).intValue() < dictT.get(c).intValue()) {
                    formed--;
                }
                l++;
            }
            r++;
        }
        return ans[0] == -1 ? "" : s.substring(ans[1], ans[2] + 1);
        // 思路 ： 滑动窗口

        /*Map<Character, Integer> dict = new HashMap<>();
        for (char c : t.toCharArray()) {
            Integer count = dict.getOrDefault(c,0);
            dict.put(c,++count);
        }
        // Pair 元素配对
        List<Pair<Integer,Character>> list = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (dict.containsKey(c)) {
                list.add(new Pair<>(i,c));
            }
        }
        int required = dict.size();
        int resultLen = 0,formed = 0;

        int start = 0,end = 0;

        int left = 0,right = 0;
        Map<Character, Integer> windowCounts = new HashMap<Character, Integer>();
        while (right < list.size()) {
            char c = list.get(right).getValue();
            int count = windowCounts.getOrDefault(c, 0);
            windowCounts.put(c, count + 1);

            if (dict.containsKey(c) && windowCounts.get(c).intValue() == dict.get(c).intValue()) {
                formed++;
            }

            // Try and co***act the window till the point where it ceases to be 'desirable'.
            while (left <= right && formed == required) {
                c = list.get(left).getValue();

                // Save the smallest window until now.

                int a = list.get(left).getKey();
                int b = list.get(right).getKey();
                if (resultLen == 0 || b - a + 1 < resultLen) {
                    resultLen = end - start + 1;
                    start = a;
                    end = b;
                }

                windowCounts.put(c, windowCounts.get(c) - 1);
                if (dict.containsKey(c) && windowCounts.get(c).intValue() < dict.get(c).intValue()) {
                    formed--;
                }
                left++;
            }
            right++;
        }
        return resultLen == 0 ?"":s.substring(start,end+1);*/
    }

    @Test
    public void canTransform() {
        String start = "RXXLRXRXL", end = "XRLXXRRLX";
        boolean result = canTransform(start, end);
        log.debug("result:{}", result);
    }
    /**
     * 在LR字符串中交换相邻字符 在一个由 'L' , 'R' 和 'X' 三个字符组成的字符串（例如"RXXLRXRXL"）中进行移动操作。一次移动操作指用一个"LX"替换一个"XL"，
     * 或者用一个"XR"替换一个"RX"。现给定起始字符串start和结束字符串end，请编写代码，当且仅当存在一系列移动操作使得start可以转换成end时， 返回True。
     *
     * <p>示例 :
     *
     * <p>输入: start = "RXXLRXRXL", end = "XRLXXRRLX" 输出: True 解释: 我们可以通过以下几步将start转换成end: RXXLRXRXL
     * -> XRXLRXRXL -> XRLXRXRXL -> XRLXXRRXL -> XRLXXRRLX 注意:
     *
     * <p>1 <= len(start) = len(end) <= 10000。 start和end中的字符串仅限于'L', 'R'和'X'。
     *
     * @param start
     * @param end
     * @return
     */
    public boolean canTransform(String start, String end) {
        // L只能向左移动，R只能向右移动，只有在遇到X的时候可以移动
        // R 不能越过 L
        int len = start.length();
        int i = 0, j = 0;
        while (i < len && j < len) {
            // 当遇到 X 时
            while (i < len && start.charAt(i) == 'X') {
                i++;
            }
            while (j < len && end.charAt(j) == 'X') {
                j++;
            }
            if ((i < len) ^ (j < len)) return false;
            if (i < len && j < len) {
                char a = start.charAt(i);
                char b = end.charAt(j);
                if (a != b || (a == 'L' && i < j) || (a == 'R' && i > j)) {
                    return false;
                }
            }

            i++;
            j++;
        }
        return true;
    }

    @Test
    public void convert() {
        String s = "PAYPALISHIRING";
        int numRows = 3;
        // String s = "A";
        // int numRows = 1;
        logResult(convert(s, numRows));
    }
    /**
     * 6. Z 字形变换 将一个给定字符串根据给定的行数，以从上往下、从左到右进行 Z 字形排列。
     *
     * <p>比如输入字符串为 "LEETCODEISHIRING" 行数为 3 时，排列如下：
     *
     * <p>L C I R E T O E S I I G E D H N 之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："LCIRETOESIIGEDHN"。
     *
     * <p>请你实现这个将字符串进行指定行数变换的函数：
     *
     * <p>string convert(string s, int numRows); 示例 1:
     *
     * <p>输入: s = "LEETCODEISHIRING", numRows = 3 输出: "LCIRETOESIIGEDHN" 示例 2:
     *
     * <p>输入: s = "LEETCODEISHIRING", numRows = 4 输出: "LDREOEIIECIHNTSG" 解释:
     *
     * <p>L D R E O E I I E C I H N T S G
     *
     * @param s
     * @param numRows
     * @return
     */
    public String convert(String s, int numRows) {
        int len = s.length();
        if (numRows < 2) {
            return s;
        }
        char[] strChar = new char[len];
        // 循环 的 字符数
        int num = numRows * 2 - 2;
        int strIndex = 0;
        for (int i = 0; i < numRows; i++) {
            int index = i;
            int sIndex = num - index;
            while (index < len) {
                strChar[strIndex++] = s.charAt(index);
                index += num;
                // 第2行 ~ 第 numRows - 1 行 需要 额外添加
                if (i > 0 && i < numRows - 1 && sIndex < len) {
                    strChar[strIndex++] = s.charAt(sIndex);
                    sIndex += num;
                }
            }
        }

        return new String(strChar);
    }

    @Test
    public void intToRoman() {
        int num = 1994;
        logResult(intToRoman(num));
    }

    /**
     * 12. 整数转罗马数字 罗马数字包含以下七种字符： I， V， X， L，C，D 和 M。
     *
     * <p>字符 数值 I 1 V 5 X 10 L 50 C 100 D 500 M 1000 例如， 罗马数字 2 写做 II ，即为两个并列的 1。12 写做 XII ，即为 X +
     * II 。 27 写做 XXVII, 即为 XX + V + II 。
     *
     * <p>通常情况下，罗马数字中小的数字在大的数字的右边。但也存在特例，例如 4 不写做 IIII，而是 IV。数字 1 在数字 5 的左边，所表示的数等于大数 5 减小数 1 得到的数值
     * 4 。同样地，数字 9 表示为 IX。这个特殊的规则只适用于以下六种情况：
     *
     * <p>I 可以放在 V (5) 和 X (10) 的左边，来表示 4 和 9。 X 可以放在 L (50) 和 C (100) 的左边，来表示 40 和 90。 C 可以放在 D
     * (500) 和 M (1000) 的左边，来表示 400 和 900。 给定一个整数，将其转为罗马数字。输入确保在 1 到 3999 的范围内。
     *
     * <p>示例 1:
     *
     * <p>输入: 3 输出: "III" 示例 2:
     *
     * <p>输入: 4 输出: "IV" 示例 3:
     *
     * <p>输入: 9 输出: "IX" 示例 4:
     *
     * <p>输入: 58 输出: "LVIII" 解释: L = 50, V = 5, III = 3. 示例 5:
     *
     * <p>输入: 1994 输出: "MCMXCIV" 解释: M = 1000, CM = 900, XC = 90, IV = 4.
     *
     * @param num
     * @return
     */
    public String intToRoman(int num) {
        int[] nums = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] romans = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        int index = 0;
        StringBuilder sb = new StringBuilder();
        while (index < 13) {
            while (num >= nums[index]) {
                sb.append(romans[index]);
                num -= nums[index];
            }
            index++;
        }
        return sb.toString();
        /*StringBuilder sb = new StringBuilder();
        char[] romans = {'I','V','X','L','C','D','M'};
        int[] nums = {1,5,10,50,100,500,1000};
        int index = nums.length - 1;
        intToRoman(  num, romans, nums, index, sb);

        return sb.toString();*/
    }

    public void intToRoman(int num, char[] romans, int[] nums, int index, StringBuilder sb) {
        if (index < 0 || num == 0) {
            return;
        }
        char roman = romans[index];
        int n = nums[index];
        while (num >= n) {
            num -= n;
            sb.append(roman);
        }
        if (index > 0) {
            int leftNum;
            char leftRoman;
            // 奇数 5 50 500
            if ((index & 1) == 1) {
                leftNum = nums[index - 1];
                leftRoman = romans[index - 1];
            } else {
                leftNum = nums[index - 2];
                leftRoman = romans[index - 2];
            }
            if (num >= (n - leftNum)) {
                num -= n - leftNum;
                sb.append(leftRoman).append(roman);
            }
        }
        intToRoman(num, romans, nums, index - 1, sb);
    }

    @Test
    public void lengthOfLastWord() {
        String s = "  ";
        logResult(lengthOfLastWord(s));
    }

    /**
     * 58. 最后一个单词的长度 给定一个仅包含大小写字母和空格 ' ' 的字符串 s，返回其最后一个单词的长度。
     *
     * <p>如果字符串从左向右滚动显示，那么最后一个单词就是最后出现的单词。
     *
     * <p>如果不存在最后一个单词，请返回 0 。
     *
     * <p>说明：一个单词是指仅由字母组成、不包含任何空格的 最大子字符串。 示例:
     *
     * <p>输入: "Hello World" 输出: 5
     *
     * @param s
     * @return
     */
    public int lengthOfLastWord(String s) {
        int len = s.length();
        // 去0
        while (len > 0 && s.charAt(len - 1) == ' ') {
            len--;
        }
        for (int i = len - 1; i >= 0; i--) {
            char c = s.charAt(i);
            if (c == ' ') {
                return len - 1 - i;
            }
        }

        return len;
    }

    @Test
    public void canConstruct() {
        String ransomNote = "aa", magazine = "aab";
        logResult(canConstruct(ransomNote, magazine));
    }

    /**
     * 383. 赎金信 给定一个赎金信 (ransom) 字符串和一个杂志(magazine)字符串，判断第一个字符串ransom能不能由第二个字符串magazines里面的字符构成。
     * 如果可以构成，返回 true ；否则返回 false。
     *
     * <p>(题目说明：为了不暴露赎金信字迹，要从杂志上搜索各个需要的字母，组成单词来表达意思。)
     *
     * <p>注意：
     *
     * <p>你可以假设两个字符串均只含有小写字母。
     *
     * <p>canConstruct("a", "b") -> false canConstruct("aa", "ab") -> false canConstruct("aa",
     * "aab") -> true
     *
     * @param ransomNote
     * @param magazine
     * @return
     */
    public boolean canConstruct(String ransomNote, String magazine) {
        int[] letters = new int[26];
        for (char c : magazine.toCharArray()) {
            letters[c - 'a']++;
        }
        for (char c : ransomNote.toCharArray()) {
            letters[c - 'a']--;
        }

        for (int num : letters) {
            if (num < 0) {
                return false;
            }
        }
        return true;
    }

    @Test
    public void findTheDifference() {
        String s = "abcd", t = "abcde";
        logResult(findTheDifference(s, t));
    }
    /**
     * 389. 找不同 给定两个字符串 s 和 t，它们只包含小写字母。
     *
     * <p>字符串 t 由字符串 s 随机重排，然后在随机位置添加一个字母。
     *
     * <p>请找出在 t 中被添加的字母。 示例:
     *
     * <p>输入： s = "abcd" t = "abcde"
     *
     * <p>输出： e
     *
     * <p>解释： 'e' 是那个被添加的字母。
     *
     * @param s
     * @param t
     * @return
     */
    public char findTheDifference(String s, String t) {
        // 只有一个字母不同 可以用异或运算
        int result = 0;
        for (char c : t.toCharArray()) {
            result ^= c;
        }
        for (char c : s.toCharArray()) {
            result ^= c;
        }
        return (char) result;
        /*int[] letters = new int[26];
        for (char c : t.toCharArray()) {
            letters[c - 'a']++;
        }
        for (char c : s.toCharArray()) {
            letters[c - 'a']--;
        }
        char result = ' ';
        for (int i = 0; i < letters.length; i++) {
            if (letters[i] == 1) {
                result = (char)('a' + i) ;
            }
        }
        return result;*/
    }

    @Test
    public void isSubsequence() {
        String s = "abc", t = "abc";
        logResult(isSubsequence(s, t));
    }

    /**
     * 392. 判断子序列 给定字符串 s 和 t ，判断 s 是否为 t 的子序列。
     *
     * <p>你可以认为 s 和 t 中仅包含英文小写字母。字符串 t 可能会很长（长度 ~= 500,000），而 s 是个短字符串（长度 <=100）。
     *
     * <p>字符串的一个子序列是原始字符串删除一些（也可以不删除）字符而不改变剩余字符相对位置形成的新字符串。（例如，"ace"是"abcde"的一个子序列，而"aec"不是）。
     *
     * <p>示例 1: s = "abc", t = "ahbgdc"
     *
     * <p>返回 true.
     *
     * <p>示例 2: s = "axc", t = "ahbgdc"
     *
     * <p>返回 false.
     *
     * <p>后续挑战 :
     *
     * <p>如果有大量输入的 S，称作S1, S2, ... , Sk 其中 k >= 10亿，你需要依次检查它们是否为 T 的子序列。在这种情况下，你会怎样改变代码？
     *
     * <p>致谢:
     *
     * @param s
     * @param t
     * @return
     */
    public boolean isSubsequence(String s, String t) {
        int left1 = 0, left2 = 0;
        int right1 = s.length() - 1, right2 = t.length() - 1;
        int count = 0;
        while (left1 <= right1 && left2 <= right2) {
            if (s.charAt(left1) == t.charAt(left2)) {
                left1++;
                count++;
            }
            left2++;
            if (s.charAt(right1) == t.charAt(right2)) {
                right1--;
                count++;
            }
            right2--;
        }
        return count >= s.length();
    }

    @Test
    public void addStrings() {
        String num1 = "101", num2 = "909";
        logResult(addStrings(num1, num2));
    }

    /**
     * 415. 字符串相加 给定两个字符串形式的非负整数 num1 和num2 ，计算它们的和。
     *
     * <p>注意：
     *
     * <p>num1 和num2 的长度都小于 5100. num1 和num2 都只包含数字 0-9. num1 和num2 都不包含任何前导零。 你不能使用任何內建 BigInteger
     * 库， 也不能直接将输入的字符串转换为整数形式。
     *
     * @param num1
     * @param num2
     * @return
     */
    public String addStrings(String num1, String num2) {
        int len1 = num1.length(), len2 = num2.length();

        int minLen = Math.min(len1, len2);
        StringBuilder sb = new StringBuilder();
        int lastNum = 0;
        for (int i = 0; i < minLen; i++) {
            char c1 = num1.charAt(len1 - 1 - i);
            char c2 = num2.charAt(len2 - 1 - i);
            int num = (c1 - '0') + (c2 - '0') + lastNum;
            if (num >= 10) {
                num -= 10;
                lastNum = 1;
            } else {
                lastNum = 0;
            }
            sb.insert(0, num);
        }
        if (len1 > len2) {
            for (int i = len1 - len2 - 1; i >= 0; i--) {
                char c = num1.charAt(i);
                int num = (c - '0') + lastNum;
                if (num >= 10) {
                    num -= 10;
                    lastNum = 1;
                } else {
                    lastNum = 0;
                }
                sb.insert(0, num);
            }
        }
        if (len2 > len1) {
            for (int i = len2 - len1 - 1; i >= 0; i--) {
                char c = num2.charAt(i);
                int num = (c - '0') + lastNum;
                if (num >= 10) {
                    num -= 10;
                    lastNum = 1;
                } else {
                    lastNum = 0;
                }
                sb.insert(0, num);
            }
        }
        if (lastNum > 0) {
            sb.insert(0, lastNum);
        }
        return sb.toString();
    }

    @Test
    public void findSubstring() {
        // String s = "wordgoodgoodgoodbestword";
        // String[] words = {"word","good","best","good"};
        // String s = "lingmindraboofooowingdingbarrwingmonkeypoundcake";
        // String[] words = {"fooo","barr","wing","ding","wing"};
        // String s = "wordgoodgoodgoodbestword";
        // String[] words = {"word","good","best","word"};
        String s = "aaaaaaaa";
        String[] words = {"aa", "aa", "aa"};
        logResult(findSubstring(s, words));
    }
    /**
     * 30. 串联所有单词的子串 给定一个字符串 s 和一些长度相同的单词 words。找出 s 中恰好可以由 words 中所有单词串联形成的子串的起始位置。
     *
     * <p>注意子串要与 words 中的单词完全匹配，中间不能有其他字符，但不需要考虑 words 中单词串联的顺序。 示例 1：
     *
     * <p>输入： s = "barfoothefoobarman", words = ["foo","bar"] 输出：[0,9] 解释： 从索引 0 和 9 开始的子串分别是
     * "barfoo" 和 "foobar" 。 输出的顺序不重要, [9,0] 也是有效答案。 示例 2：
     *
     * <p>输入： s = "wordgoodgoodgoodbestword", words = ["word","good","best","word"] 输出：[]
     *
     * @param s
     * @param words
     * @return
     */
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> result = new ArrayList<>();
        int len = s.length();
        if (len == 0 || words.length == 0) {
            return result;
        }

        int wordLen = words[0].length();

        int sumLen = words.length * wordLen;
        if (len < sumLen) {
            return result;
        }

        // 将words中的单词及其数量存入hashmap
        HashMap<String, Integer> allWords = new HashMap<>();
        for (String word : words) {
            Integer count = allWords.getOrDefault(word, 0);
            allWords.put(word, count + 1);
        }

        for (int k = 0; k < wordLen; k++) {
            HashMap<String, Integer> hasWords = new HashMap<>();
            for (int i = k; i <= len - wordLen; i += wordLen) {

                String word = s.substring(i, i + wordLen);
                Integer count = allWords.get(word);

                if (count == null) {
                    // 需要重新匹配
                    hasWords.clear();
                    continue;
                } else {
                    int count1 = hasWords.getOrDefault(word, 0);
                    hasWords.put(word, count1 + 1);
                }

                // 开始位置
                int start = i - sumLen + wordLen;
                if (start < 0) {
                    continue;
                }
                // 开始单词
                String startWord = s.substring(start, start + wordLen);
                // 不包含 直接跳过
                if (!allWords.containsKey(startWord)) {
                    continue;
                }
                // 遍历比较allWords和hasWords
                if (compareWord(allWords, hasWords)) {
                    result.add(start);
                }
                Integer count2 = hasWords.getOrDefault(startWord, 0);
                hasWords.put(startWord, count2 - 1);
            }
        }

        return result;
    }

    private boolean compareWord(
            HashMap<String, Integer> allWords, HashMap<String, Integer> hasWords) {
        for (String key : allWords.keySet()) {
            Integer value1 = allWords.get(key);
            Integer value2 = hasWords.get(key);
            if (!Objects.equals(value1, value2)) {
                return false;
            }
        }
        return true;
    }

    @Test
    public void countSegments() {
        String s = "Hello, my name is John";
        logResult(countSegments(s));
    }

    /**
     * 434. 字符串中的单词数 统计字符串中的单词个数，这里的单词指的是连续的不是空格的字符。
     *
     * <p>请注意，你可以假定字符串里不包括任何不可打印的字符。
     *
     * <p>示例:
     *
     * <p>输入: "Hello, my name is John" 输出: 5
     *
     * @param s
     * @return
     */
    public int countSegments(String s) {
        int count = 0;

        for (int i = 0; i < s.length(); i++) {
            if ((i == 0 || s.charAt(i - 1) == ' ') && s.charAt(i) != ' ') {
                count++;
            }
        }

        return count;
    }

    @Test
    public void compress() {
        char[] chars = {
            'a', 'a', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b',
            'b', 'b', 'b', 'c', 'c', 'c'
        };
        logResult(compress(chars));
        logResult(chars);
    }
    /**
     * 443. 压缩字符串 给定一组字符，使用原地算法将其压缩。
     *
     * <p>压缩后的长度必须始终小于或等于原数组长度。
     *
     * <p>数组的每个元素应该是长度为1 的字符（不是 int 整数类型）。
     *
     * <p>在完成原地修改输入数组后，返回数组的新长度。
     *
     * <p>进阶： 你能否仅使用O(1) 空间解决问题？
     *
     * <p>示例 1：
     *
     * <p>输入： ["a","a","b","b","c","c","c"]
     *
     * <p>输出： 返回6，输入数组的前6个字符应该是：["a","2","b","2","c","3"]
     *
     * <p>说明： "aa"被"a2"替代。"bb"被"b2"替代。"ccc"被"c3"替代。 示例 2：
     *
     * <p>输入： ["a"]
     *
     * <p>输出： 返回1，输入数组的前1个字符应该是：["a"]
     *
     * <p>说明： 没有任何字符串被替代。 示例 3：
     *
     * <p>输入： ["a","b","b","b","b","b","b","b","b","b","b","b","b"]
     *
     * <p>输出： 返回4，输入数组的前4个字符应该是：["a","b","1","2"]。
     *
     * <p>说明： 由于字符"a"不重复，所以不会被压缩。"bbbbbbbbbbbb"被“b12”替代。 注意每个数字在数组中都有它自己的位置。 注意：
     *
     * <p>所有字符都有一个ASCII值在[35, 126]区间内。 1 <= len(chars) <= 1000。
     *
     * @param chars
     * @return
     */
    public int compress(char[] chars) {
        int len = chars.length;
        if (len <= 1) {
            return len;
        }
        int count = 1;
        int index = 1;
        for (int i = 1; i <= len; i++) {
            if (i == len || chars[i - 1] != chars[i]) {
                if (count > 1) {
                    /*int num = count/1000;
                    count %= 1000;
                    if (num > 0) {
                        chars[index++] = (char) ('0' + num);
                    }
                    num = count/100;
                    count %= 100;
                    if (num > 0) {
                        chars[index++] = (char) ('0' + num);
                    }
                    num = count/10;
                    count %= 10;
                    if (num > 0) {
                        chars[index++] = (char) ('0' + num);
                    }
                    chars[index++] = (char) ('0' + count);  */
                    for (char c : ("" + count).toCharArray()) {
                        chars[index++] = c;
                    }
                }
                if (i < len) {
                    chars[index++] = chars[i];
                }
                count = 1;
            } else {
                count++;
            }
        }

        return index;
    }

    @Test
    public void gcdOfStrings() {
        String str1 = "ABABABAB", str2 = "ABAB";
        logResult(gcdOfStrings(str1, str2));
    }

    /**
     * 1071. 字符串的最大公因子 对于字符串 S 和 T，只有在 S = T + ... + T（T 与自身连接 1 次或多次）时，我们才认定 “T 能除尽 S”。
     *
     * <p>返回最长字符串 X，要求满足 X 能除尽 str1 且 X 能除尽 str2。
     *
     * <p>示例 1：
     *
     * <p>输入：str1 = "ABCABC", str2 = "ABC" 输出："ABC" 示例 2：
     *
     * <p>输入：str1 = "ABABAB", str2 = "ABAB" 输出："AB" 示例 3：
     *
     * <p>输入：str1 = "LEET", str2 = "CODE" 输出：""
     *
     * <p>提示：
     *
     * <p>1 <= str1.length <= 1000 1 <= str2.length <= 1000 str1[i] 和 str2[i] 为大写英文字母
     *
     * @param str1
     * @param str2
     * @return
     */
    public String gcdOfStrings(String str1, String str2) {
        if (!Objects.equals(str1 + str2, str2 + str1)) {
            return "";
        }
        // str1 和 str2 的长度差一定是结果
        return str1.substring(0, gcd(str1.length(), str2.length()));
    }

    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    @Test
    public void findOcurrences() {
        String text = "alice is a good girl she is a good student", first = "a", second = "good";
        logResult(findOcurrences(text, first, second));
    }
    /**
     * 1078. Bigram 分词 给出第一个词 first 和第二个词 second，考虑在某些文本 text 中可能以 "first second third" 形式出现的情况，其中
     * second 紧随 first 出现，third 紧随 second 出现。
     *
     * <p>对于每种这样的情况，将第三个词 "third" 添加到答案中，并返回答案。
     *
     * <p>示例 1：
     *
     * <p>输入：text = "alice is a good girl she is a good student", first = "a", second = "good"
     * 输出：["girl","student"] 示例 2：
     *
     * <p>输入：text = "we will we will rock you", first = "we", second = "will" 输出：["we","rock"]
     *
     * <p>提示：
     *
     * <p>1 <= text.length <= 1000 text 由一些用空格分隔的单词组成，每个单词都由小写英文字母组成 1 <= first.length,
     * second.length <= 10 first 和 second 由小写英文字母组成
     *
     * @param text
     * @param first
     * @param second
     * @return
     */
    public String[] findOcurrences(String text, String first, String second) {
        String[] words = text.split(" ");
        int len = words.length;
        if (len <= 2) {
            return new String[0];
        }
        List<String> list = new ArrayList<>();
        for (int i = 2; i < len; i++) {
            if (Objects.equals(first, words[i - 2]) && Objects.equals(second, words[i - 1])) {
                list.add(words[i]);
            }
        }
        String[] result = new String[list.size()];
        return list.toArray(result);
    }

    @Test
    public void rotateString() {
        String a = "abcde", b = "abced";
        logResult(rotateString(a, b));
    }

    /**
     * 796. 旋转字符串 给定两个字符串, A 和 B。
     *
     * <p>A 的旋转操作就是将 A 最左边的字符移动到最右边。 例如, 若 A = 'abcde'， 在移动一次之后结果就是'bcdea' 。如果在若干次旋转操作之后，A
     * 能变成B，那么返回True。
     *
     * <p>示例 1: 输入: A = 'abcde', B = 'cdeab' 输出: true
     *
     * <p>示例 2: 输入: A = 'abcde', B = 'abced' 输出: false 注意：
     *
     * <p>A 和 B 长度不超过 100。
     *
     * @param A
     * @param B
     * @return
     */
    public boolean rotateString(String A, String B) {
        if (A.length() != B.length()) {
            return false;
        }
        if (Objects.equals(A, B)) {
            return true;
        }
        int len = A.length();
        // 用一个list记录所有B中所有能和A[0] A[len-1] 匹配的 索引
        char[] aChars = A.toCharArray();
        char[] bChars = B.toCharArray();
        List<Integer> indexList = new ArrayList<>();

        for (int i = 0; i < len; i++) {
            int last = i - 1;
            if (i == 0) {
                last = len - 1;
            }
            if (aChars[0] == bChars[i] && aChars[len - 1] == bChars[last]) {
                indexList.add(i);
            }
        }

        if (indexList.size() == 0) {
            return false;
        }
        for (Integer index : indexList) {
            // 开始匹配
            if (match(aChars, bChars, index)) {
                return true;
            }
        }

        return false;
    }

    private boolean match(char[] aChars, char[] bChars, int index) {
        int i = 0;
        int len = aChars.length;
        while (i < len) {
            if (aChars[i++] != bChars[index++]) {
                return false;
            }
            if (index == len) {
                index = 0;
            }
        }
        return true;
    }

    @Test
    public void sortString() {
        String s = "leetcode";
        logResult(sortString(s));
    }

    /**
     * 1370. 上升下降字符串 给你一个字符串 s ，请你根据下面的算法重新构造字符串：
     *
     * <p>从 s 中选出 最小 的字符，将它 接在 结果字符串的后面。 从 s 剩余字符中选出 最小 的字符，且该字符比上一个添加的字符大，将它 接在 结果字符串后面。 重复步骤 2
     * ，直到你没法从 s 中选择字符。 从 s 中选出 最大 的字符，将它 接在 结果字符串的后面。 从 s 剩余字符中选出 最大 的字符，且该字符比上一个添加的字符小，将它 接在
     * 结果字符串后面。 重复步骤 5 ，直到你没法从 s 中选择字符。 重复步骤 1 到 6 ，直到 s 中所有字符都已经被选过。 在任何一步中，如果最小或者最大字符不止一个
     * ，你可以选择其中任意一个，并将其添加到结果字符串。
     *
     * <p>请你返回将 s 中字符重新排序后的 结果字符串 。
     *
     * <p>示例 1：
     *
     * <p>输入：s = "aaaabbbbcccc" 输出："abccbaabccba" 解释：第一轮的步骤 1，2，3 后，结果字符串为 result = "abc" 第一轮的步骤
     * 4，5，6 后，结果字符串为 result = "abccba" 第一轮结束，现在 s = "aabbcc" ，我们再次回到步骤 1 第二轮的步骤 1，2，3 后，结果字符串为
     * result = "abccbaabc" 第二轮的步骤 4，5，6 后，结果字符串为 result = "abccbaabccba" 示例 2：
     *
     * <p>输入：s = "rat" 输出："art" 解释：单词 "rat" 在上述算法重排序以后变成 "art" 示例 3：
     *
     * <p>输入：s = "leetcode" 输出："cdelotee" 示例 4：
     *
     * <p>输入：s = "ggggggg" 输出："ggggggg" 示例 5：
     *
     * <p>输入：s = "spo" 输出："ops"
     *
     * <p>提示：
     *
     * <p>1 <= s.length <= 500 s 只包含小写英文字母。
     *
     * @param s
     * @return
     */
    public String sortString(String s) {
        char[] chars = new char[s.length()];
        int[] letters = new int[26];
        for (char c : s.toCharArray()) {
            letters[c - 'a']++;
        }
        int index = 0;
        int len = s.length();
        while (index < len) {
            for (int i = 0; i < 26; i++) {
                if (letters[i] > 0) {
                    chars[index++] = (char) ('a' + i);
                    letters[i]--;
                }
            }
            for (int i = 25; i >= 0; i--) {
                if (letters[i] > 0) {
                    chars[index++] = (char) ('a' + i);
                    letters[i]--;
                }
            }
            // chars[i++] = c;
        }

        return String.valueOf(chars);
    }

    @Test
    public void freqAlphabets() {
        String s = "12345678910#11#12#13#14#15#16#17#18#19#20#21#22#23#24#25#26#";
        logResult(freqAlphabets(s));
    }
    /**
     * 1309. 解码字母到整数映射 给你一个字符串 s，它由数字（'0' - '9'）和 '#' 组成。我们希望按下述规则将 s 映射为一些小写英文字符：
     *
     * <p>字符（'a' - 'i'）分别用（'1' - '9'）表示。 字符（'j' - 'z'）分别用（'10#' - '26#'）表示。 返回映射之后形成的新字符串。
     *
     * <p>题目数据保证映射始终唯一。
     *
     * <p>示例 1：
     *
     * <p>输入：s = "10#11#12" 输出："jkab" 解释："j" -> "10#" , "k" -> "11#" , "a" -> "1" , "b" -> "2". 示例
     * 2：
     *
     * <p>输入：s = "1326#" 输出："acz" 示例 3：
     *
     * <p>输入：s = "25#" 输出："y" 示例 4：
     *
     * <p>输入：s = "12345678910#11#12#13#14#15#16#17#18#19#20#21#22#23#24#25#26#"
     * 输出："abcdefghijklmnopqrstuvwxyz"
     *
     * <p>提示：
     *
     * <p>1 <= s.length <= 1000 s[i] 只包含数字（'0'-'9'）和 '#' 字符。 s 是映射始终存在的有效字符串。
     *
     * @param s
     * @return
     */
    public String freqAlphabets(String s) {
        StringBuilder sb = new StringBuilder();
        char[] chars = s.toCharArray();
        int i = chars.length - 1;
        while (i >= 0) {
            int num = 0;
            if (chars[i] == '#') {
                num = (chars[i - 2] - '0') * 10 + (chars[i - 1] - '0');
                i = i - 3;
            } else {
                num = chars[i] - '0';
                i--;
            }
            char c = (char) ('a' + num - 1);
            sb.insert(0, c);
        }

        return sb.toString();
    }

    /**
     * 1332. 删除回文子序列 给你一个字符串 s，它仅由字母 'a' 和 'b' 组成。每一次删除操作都可以从 s 中删除一个回文 子序列。
     *
     * <p>返回删除给定字符串中所有字符（字符串为空）的最小删除次数。
     *
     * <p>「子序列」定义：如果一个字符串可以通过删除原字符串某些字符而不改变原字符顺序得到，那么这个字符串就是原字符串的一个子序列。
     *
     * <p>「回文」定义：如果一个字符串向后和向前读是一致的，那么这个字符串就是一个回文。
     *
     * <p>示例 1：
     *
     * <p>输入：s = "ababa" 输出：1 解释：字符串本身就是回文序列，只需要删除一次。 示例 2：
     *
     * <p>输入：s = "abb" 输出：2 解释："abb" -> "bb" -> "". 先删除回文子序列 "a"，然后再删除 "bb"。 示例 3：
     *
     * <p>输入：s = "baabb" 输出：2 解释："baabb" -> "b" -> "". 先删除回文子序列 "baab"，然后再删除 "b"。 示例 4：
     *
     * <p>输入：s = "" 输出：0
     *
     * <p>提示：
     *
     * <p>0 <= s.length <= 1000 s 仅包含字母 'a' 和 'b'
     *
     * @param s
     * @return
     */
    public int removePalindromeSub(String s) {
        // 题目意思是每次删除一个子序列，这个子序列必须是回文。由于子序列的元素可以不连续，且字符串只有a和b组成，
        // 而 n 个 a 本身是一个回文子序列，所以第一次先删除所有的a，再删除所有的b就行了。
        int result = 0;
        if (s.contains("a")) result++;
        if (s.contains("b")) result++;
        StringBuffer sb = new StringBuffer(s);
        sb.reverse();
        if (s.equals(sb.toString()) && sb.length() > 0) return 1;
        return result;
    }

    @Test
    public void minimumLengthEncoding() {
        String[] words = {"time", "me", "abe", "el", "bell"};
        logResult(minimumLengthEncoding(words));
    }
    /**
     * 820. 单词的压缩编码 给定一个单词列表，我们将这个列表编码成一个索引字符串 S 与一个索引列表 A。
     *
     * <p>例如，如果这个列表是 ["time", "me", "bell"]，我们就可以将其表示为 S = "time#bell#" 和 indexes = [0, 2, 5]。
     *
     * <p>对于每一个索引，我们可以通过从字符串 S 中索引的位置开始读取字符串，直到 "#" 结束，来恢复我们之前的单词列表。
     *
     * <p>那么成功对给定单词列表进行编码的最小字符串长度是多少呢？
     *
     * <p>示例：
     *
     * <p>输入: words = ["time", "me", "bell"] 输出: 10 说明: S = "time#bell#" ， indexes = [0, 2, 5] 。
     *
     * <p>提示：
     *
     * <p>1 <= words.length <= 2000 1 <= words[i].length <= 7 每个单词都是小写字母 。
     *
     * @param words
     * @return
     */
    public int minimumLengthEncoding(String[] words) {
        int result = 0;
        // 思路：只有是后缀是才能合并
        // 将word 反序后插入字典树
        Trie trie = new Trie();
        for (String word : words) {
            trie.reverseInsert(word);
        }

        return trie.minimumLengthEncoding();
    }

    @Test
    public void repeatedSubstringPattern() {
        String s = "abc";
        logResult(repeatedSubstringPattern(s));
    }
    /**
     * 459. 重复的子字符串 给定一个非空的字符串，判断它是否可以由它的一个子串重复多次构成。给定的字符串只含有小写英文字母，并且长度不超过10000。
     *
     * <p>示例 1:
     *
     * <p>输入: "abab"
     *
     * <p>输出: True
     *
     * <p>解释: 可由子字符串 "ab" 重复两次构成。 示例 2:
     *
     * <p>输入: "aba"
     *
     * <p>输出: False 示例 3:
     *
     * <p>输入: "abcabcabcabc"
     *
     * <p>输出: True
     *
     * <p>解释: 可由子字符串 "abc" 重复四次构成。 (或者子字符串 "abcabc" 重复两次构成。)
     *
     * @param s
     * @return
     */
    public boolean repeatedSubstringPattern(String s) {

        char[] chars = s.toCharArray();
        int len = chars.length;
        if (len == 1) {
            return false;
        }
        int[] next = getNextArray(chars);
        log.debug("next:{}", next);
        int k = next[len - 1];
        if (k > 0 && len % (len - k) == 0) {
            return true;
        }
        return false;
    }

    /**
     * 求出一个字符数组的next数组
     *
     * @param target 字符数组
     * @return next数组
     */
    private int[] getNextArray(char[] target) {
        int[] next = new int[target.length];
        next[0] = 0;
        // 通过next记录target字符串自身的信息
        // 与自身做匹配，匹配自身重复的部分
        for (int i = 1; i < target.length; i++) {
            int j = next[i - 1]; // 求前面一位匹配
            while (j >= 0 && target[i - 1] != target[j]) {
                j = next[j]; // 递推计算
            }
            if (j >= 0 && target[i - 1] == target[j]) {
                next[i] = j + 1;
            } else {
                next[i] = -1;
            }
        }
        return next;
    }

    @Test
    public void reverseStr() {
        String s = "abcdefgs";
        int k = 3;
        logResult(reverseStr(s, k));
    }

    /**
     * 541. 反转字符串 II 给定一个字符串和一个整数 k，你需要对从字符串开头算起的每个 2k 个字符的前k个字符进行反转。如果剩余少于 k 个字符，则将剩余的所有全部反转。如果有小于
     * 2k 但大于或等于 k 个字符，则反转前 k 个字符，并将剩余的字符保持原样。
     *
     * <p>示例:
     *
     * <p>输入: s = "abcdefg", k = 2 输出: "bacdfeg" 要求:
     *
     * <p>该字符串只包含小写的英文字母。 给定字符串的长度和 k 在[1, 10000]范围内。
     *
     * @param s
     * @param k
     * @return
     */
    public String reverseStr(String s, int k) {
        char[] chars = s.toCharArray();
        int index = 0;
        while (index + k < chars.length) {

            int left = index;
            int right = index + k - 1;
            while (left < right) {
                char c = chars[left];
                chars[left] = chars[right];
                chars[right] = c;
                left++;
                right--;
            }
            index += 2 * k;
        }
        if (index < chars.length) {
            int left = index;
            int right = chars.length - 1;
            while (left < right) {
                char c = chars[left];
                chars[left] = chars[right];
                chars[right] = c;
                left++;
                right--;
            }
        }
        return new String(chars);
    }

    @Test
    public void licenseKeyFormatting() {
        String s = "--a-a-a-a--";
        int k = 2;
        logResult(licenseKeyFormatting(s, k));
    }

    /**
     * 给定一个密钥字符串S，只包含字母，数字以及 '-'（破折号）。N 个 '-' 将字符串分成了 N+1 组。 给定一个数字 K，重新格式化字符串，除了第一个分组以外，每个分组要包含 K
     * 个字符，第一个分组至少要包含 1 个字符。 两个分组之间用 '-'（破折号）隔开，并且将所有的小写字母转换为大写字母。
     *
     * <p>给定非空字符串 S 和数字 K，按照上面描述的规则进行格式化。
     *
     * <p>示例 1：
     *
     * <p>输入：S = "5F3Z-2e-9-w", K = 4
     *
     * <p>输出："5F3Z-2E9W"
     *
     * <p>解释：字符串 S 被分成了两个部分，每部分 4 个字符； 注意，两个额外的破折号需要删掉。 示例 2：
     *
     * <p>输入：S = "2-5g-3-J", K = 2
     *
     * <p>输出："2-5G-3J"
     *
     * <p>解释：字符串 S 被分成了 3 个部分，按照前面的规则描述，第一部分的字符可以少于给定的数量，其余部分皆为 2 个字符。
     *
     * <p>提示:
     *
     * <p>S 的长度不超过 12,000，K 为正整数 S 只包含字母数字（a-z，A-Z，0-9）以及破折号'-' S 非空
     *
     * @param S
     * @param K
     * @return
     */
    public String licenseKeyFormatting(String S, int K) {
        StringBuilder sb = new StringBuilder();
        char[] chars = S.toCharArray();
        int len = chars.length;
        // 除了第一个分组以外,每个分组要包含 K 个字符,第一个分组至少要包含 1 个字符
        // 思路, 倒序
        int count = 0;
        for (int i = len - 1; i >= 0; i--) {

            char c = chars[i];
            if (c == '-') {
                continue;
            }
            if (count == K) {
                count = 0;
                sb.append('-');
            }
            if (c >= 'a' && c <= 'z') {
                c = (char) (c - 'a' + 'A');
            }
            count++;
            sb.append(c);
        }

        sb.reverse();

        return sb.toString();
    }

    @Test
    public void detectCapitalUse() {
        String word = "flaG";
        logResult(detectCapitalUse(word));
    }
    /**
     * 520. 检测大写字母 给定一个单词，你需要判断单词的大写使用是否正确。
     *
     * <p>我们定义，在以下情况时，单词的大写用法是正确的：
     *
     * <p>全部字母都是大写，比如"USA"。 单词中所有字母都不是大写，比如"leetcode"。 如果单词不只含有一个字母，只有首字母大写， 比如 "Google"。
     * 否则，我们定义这个单词没有正确使用大写字母。
     *
     * <p>示例 1:
     *
     * <p>输入: "USA" 输出: True 示例 2:
     *
     * <p>输入: "FlaG" 输出: False 注意: 输入是由大写和小写拉丁字母组成的非空单词。
     *
     * @param word
     * @return
     */
    public boolean detectCapitalUse(String word) {
        int bigCount = 0;
        boolean first = false;
        int len = word.length();
        for (int i = 0; i < len; i++) {
            char c = word.charAt(i);
            if (c >= 'A' && c <= 'Z') {
                bigCount++;
                if (i == 0) {
                    first = true;
                }
            }
        }
        if (!first && bigCount > 0) {
            return false;
        }
        if (first && bigCount > 1 && bigCount < len) {
            return false;
        }

        return true;
    }

    /**
     * 551. 学生出勤记录 I 给定一个字符串来代表一个学生的出勤记录，这个记录仅包含以下三个字符：
     *
     * <p>'A' : Absent，缺勤 'L' : Late，迟到 'P' : Present，到场
     * 如果一个学生的出勤记录中不超过一个'A'(缺勤)并且不超过两个连续的'L'(迟到),那么这个学生会被奖赏。
     *
     * <p>你需要根据这个学生的出勤记录判断他是否会被奖赏。
     *
     * <p>示例 1:
     *
     * <p>输入: "PPALLP" 输出: True 示例 2:
     *
     * <p>输入: "PPALLL" 输出: False
     *
     * @param s
     * @return
     */
    public boolean checkRecord(String s) {
        char[] chars = s.toCharArray();
        int aCount = 0;
        int lCount = 0;
        // 超过一个'A' 或 连续 2个'L'
        for (char c : chars) {
            if (c == 'A') {
                aCount++;
            }
            if (aCount > 1) {
                return false;
            }
            if (c == 'L') {
                lCount++;
                if (lCount > 2) {
                    return false;
                }
            } else {
                lCount = 0;
            }
        }

        return true;
    }

    /**
     * 1111. 有效括号的嵌套深度 有效括号字符串 定义：对于每个左括号，都能找到与之对应的右括号，反之亦然。详情参见题末「有效括号字符串」部分。
     *
     * <p>嵌套深度 depth 定义：即有效括号字符串嵌套的层数，depth(A) 表示有效括号字符串 A 的嵌套深度。详情参见题末「嵌套深度」部分。
     *
     * <p>给你一个「有效括号字符串」 seq，请你将其分成两个不相交的有效括号字符串，A 和 B，并使这两个字符串的深度最小。
     *
     * <p>不相交：每个 seq[i] 只能分给 A 和 B 二者中的一个，不能既属于 A 也属于 B 。 A 或 B 中的元素在原字符串中可以不连续。 A.length + B.length
     * = seq.length max(depth(A), depth(B)) 的可能取值最小。 划分方案用一个长度为 seq.length 的答案数组 answer 表示，编码规则如下：
     *
     * <p>answer[i] = 0，seq[i] 分给 A 。 answer[i] = 1，seq[i] 分给 B 。 如果存在多个满足要求的答案，只需返回其中任意 一个 即可。
     *
     * <p>示例 1：
     *
     * <p>输入：seq = "(()())" 输出：[0,1,1,1,1,0] 示例 2：
     *
     * <p>输入：seq = "()(())()" 输出：[0,0,0,1,1,0,1,1]
     *
     * <p>提示：
     *
     * <p>1 <= text.size <= 10000
     *
     * <p>有效括号字符串：
     *
     * <p>仅由 "(" 和 ")" 构成的字符串，对于每个左括号，都能找到与之对应的右括号，反之亦然。 下述几种情况同样属于有效括号字符串：
     *
     * <p>1. 空字符串 2. 连接，可以记作 AB（A 与 B 连接），其中 A 和 B 都是有效括号字符串 3. 嵌套，可以记作 (A)，其中 A 是有效括号字符串 嵌套深度：
     *
     * <p>类似地，我们可以定义任意有效括号字符串 s 的 嵌套深度 depth(S)：
     *
     * <p>1. s 为空时，depth("") = 0 2. s 为 A 与 B 连接时，depth(A + B) = max(depth(A), depth(B))，其中 A 和 B
     * 都是有效括号字符串 3. s 为嵌套情况，depth("(" + A + ")") = 1 + depth(A)，其中 A 是有效括号字符串
     *
     * <p>例如：""，"()()"，和 "()(()())" 都是有效括号字符串，嵌套深度分别为 0，1，2，而 ")(" 和 "(()" 都不是有效括号字符串。
     *
     * @param seq
     * @return
     */
    public int[] maxDepthAfterSplit(String seq) {

        int[] result = new int[seq.length()];
        for (int i = 0; i < seq.length(); i++) {
            char c = seq.charAt(i);
            if (c == '(') {
                result[i] = i % 2;
            } else {
                result[i] = 1 - i % 2;
            }
        }
        return result;
    }

    /**
     * 709. 转换成小写字母 实现函数 ToLowerCase()，该函数接收一个字符串参数 str，并将该字符串中的大写字母转换成小写字母，之后返回新的字符串。
     *
     * <p>示例 1：
     *
     * <p>输入: "Hello" 输出: "hello" 示例 2：
     *
     * <p>输入: "here" 输出: "here" 示例 3：
     *
     * <p>输入: "LOVELY" 输出: "lovely"
     *
     * @param str
     * @return
     */
    public String toLowerCase(String str) {
        char[] chars = str.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            if (c >= 'A' && c <= 'Z') {
                chars[i] = (char) (c + 32);
            }
        }
        return new String(chars);
    }

    @Test
    public void test1() {
        int a = 'A';
        int b = 'a';
        log.debug("a:{},b:{} : {}", a, b, 'A' - 'a');
    }

    @Test
    public void validPalindrome() {
        String s = "abcdcbca";
        logResult(validPalindrome(s));
    }
    /**
     * 680. 验证回文字符串 Ⅱ 给定一个非空字符串 s，最多删除一个字符。判断是否能成为回文字符串。
     *
     * <p>示例 1:
     *
     * <p>输入: "aba" 输出: True 示例 2:
     *
     * <p>输入: "abca" 输出: True 解释: 你可以删除c字符。 注意:
     *
     * <p>字符串只包含从 a-z 的小写字母。字符串的最大长度是50000。
     *
     * @param s
     * @return
     */
    public boolean validPalindrome(String s) {
        char[] chars = s.toCharArray();
        int left = 0, right = s.length() - 1;

        int count = 0;
        while (left < right) {
            if (chars[left] != chars[right]) {
                return validPalindrome(chars, left + 1, right)
                        || validPalindrome(chars, left, right - 1);
            }
            left++;
            right--;
        }
        return true;
    }

    private boolean validPalindrome(char[] chars, int left, int right) {
        while (left < right) {
            if (chars[left++] != chars[right--]) {
                return false;
            }
        }
        return true;
    }

    /**
     * 686. 重复叠加字符串匹配 给定两个字符串 A 和 B, 寻找重复叠加字符串A的最小次数，使得字符串B成为叠加后的字符串A的子串，如果不存在则返回 -1。
     *
     * <p>举个例子，A = "abcd"，B = "cdabcdab"。
     *
     * <p>答案为 3， 因为 A 重复叠加三遍后为 “abcdabcdabcd”，此时 B 是其子串；A 重复叠加两遍后为"abcdabcd"，B 并不是其子串。
     *
     * <p>注意:
     *
     * <p>A 与 B 字符串的长度在1和10000区间范围内。
     *
     * @param A
     * @param B
     * @return
     */
    public int repeatedStringMatch(String A, String B) {
        int count = B.length() / A.length();
        if (A.length() * count < B.length()) {
            count++;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < count; i++) {
            sb.append(A);
        }
        if (sb.indexOf(B) >= 0) {
            return count;
        }
        sb.append(A);
        if (sb.indexOf(B) >= 0) {
            return count + 1;
        }
        return -1;
    }

    @Test
    public void countBinarySubstrings() {
        String s = "10101";
        logResult(countBinarySubstrings(s));
    }
    /**
     * 696. 计数二进制子串 给定一个字符串 s，计算具有相同数量0和1的非空(连续)子字符串的数量，并且这些子字符串中的所有0和所有1都是组合在一起的。
     *
     * <p>重复出现的子串要计算它们出现的次数。
     *
     * <p>示例 1 :
     *
     * <p>输入: "00110011" 输出: 6 解释: 有6个子串具有相同数量的连续1和0：“0011”，“01”，“1100”，“10”，“0011” 和 “01”。
     *
     * <p>请注意，一些重复出现的子串要计算它们出现的次数。
     *
     * <p>另外，“00110011”不是有效的子串，因为所有的0（和1）没有组合在一起。 示例 2 :
     *
     * <p>输入: "10101" 输出: 4 解释: 有4个子串：“10”，“01”，“10”，“01”，它们具有相同数量的连续1和0。 注意：
     *
     * <p>s.length 在1到50,000之间。 s 只包含“0”或“1”字符。
     *
     * @param s
     * @return
     */
    public int countBinarySubstrings(String s) {
        int result = 0;

        int len = s.length();
        char[] chars = s.toCharArray();

        int preCount = 0;
        int count = 1;
        for (int i = 1; i < len; i++) {
            char c = chars[i];
            if (chars[i] == chars[i - 1]) {
                count++;
            } else {
                preCount = count;
                count = 1;
            }
            if (count <= preCount) {
                result++;
            }
            /* boolean flag = true;
            for (int j = 0; j < count; j++) {
                int index = j  + (i - 2 * count + 1);
                if (index >= 0 && chars[index] != c) {

                } else {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                result++;
            }*/

        }

        return result;
    }

    /**
     * 804. 唯一摩尔斯密码词 国际摩尔斯密码定义一种标准编码方式，将每个字母对应于一个由一系列点和短线组成的字符串， 比如: "a" 对应 ".-", "b" 对应 "-...", "c"
     * 对应 "-.-.", 等等。
     *
     * <p>为了方便，所有26个英文字母对应摩尔斯密码表如下：
     *
     * <p>[".-","-...","-.-.","-..",".","..-.","--.","....","..",".---","-.-",".-..","--","-.","---",".--.","--.-",".-.","...","-","..-","...-",".--","-..-","-.--","--.."]
     * 给定一个单词列表，每个单词可以写成每个字母对应摩尔斯密码的组合。 例如，"cab" 可以写成 "-.-..--..."，(即 "-.-." + "-..." + ".-"字符串的结合)。
     * 我们将这样一个连接过程称作单词翻译。
     *
     * <p>返回我们可以获得所有词不同单词翻译的数量。
     *
     * <p>例如: 输入: words = ["gin", "zen", "gig", "msg"] 输出: 2 解释: 各单词翻译如下: "gin" -> "--...-." "zen"
     * -> "--...-." "gig" -> "--...--." "msg" -> "--...--."
     *
     * <p>共有 2 种不同翻译, "--...-." 和 "--...--.".
     *
     * <p>注意:
     *
     * <p>单词列表words 的长度不会超过 100。 每个单词 words[i]的长度范围为 [1, 12]。 每个单词 words[i]只包含小写字母。
     *
     * @param words
     * @return
     */
    public int uniqueMorseRepresentations(String[] words) {
        String[] morses = {
            ".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..", ".---", "-.-", ".-..",
            "--", "-.", "---", ".--.", "--.-", ".-.", "...", "-", "..-", "...-", ".--", "-..-",
            "-.--", "--.."
        };
        Set<String> set = new HashSet<>();
        StringBuilder sb = new StringBuilder();
        for (String word : words) {
            sb.setLength(0);
            for (char c : word.toCharArray()) {
                sb.append(morses[c - 'a']);
            }
            set.add(sb.toString());
        }

        return set.size();
    }

    @Test
    public void numberOfLines() {
        int[] widths = {
            4, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10,
            10, 10, 10, 10
        };
        String S = "bbbcccdddaaa";
        int[] result = numberOfLines(widths, S);
        logResult(result);
    }
    /**
     * 806. 写字符串需要的行数 我们要把给定的字符串 S 从左到右写到每一行上，每一行的最大宽度为100个单位，如果我们在写某个字母的时候会使这行超过了100 个单位，
     * 那么我们应该把这个字母写到下一行。我们给定了一个数组 widths ，这个数组 widths[0] 代表 'a' 需要的单位， widths[1] 代表 'b' 需要的单位，...，
     * widths[25] 代表 'z' 需要的单位。
     *
     * <p>现在回答两个问题：至少多少行能放下S，以及最后一行使用的宽度是多少个单位？将你的答案作为长度为2的整数列表返回。
     *
     * <p>示例 1: 输入: widths =
     * [10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10] S =
     * "abcdefghijklmnopqrstuvwxyz" 输出: [3, 60] 解释: 所有的字符拥有相同的占用单位10。所以书写所有的26个字母，
     * 我们需要2个整行和占用60个单位的一行。 示例 2: 输入: widths =
     * [4,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10] S =
     * "bbbcccdddaaa" 输出: [2, 4] 解释: 除去字母'a'所有的字符都是相同的单位10，并且字符串 "bbbcccdddaa" 将会覆盖 9 * 10 + 2 * 4 =
     * 98 个单位. 最后一个字母 'a' 将会被写到第二行，因为第一行只剩下2个单位了。 所以，这个答案是2行，第二行有4个单位宽度。
     *
     * <p>注:
     *
     * <p>字符串 S 的长度在 [1, 1000] 的范围。 S 只包含小写字母。 widths 是长度为 26的数组。 widths[i] 值的范围在 [2, 10]。
     *
     * @param widths
     * @param S
     * @return
     */
    public int[] numberOfLines(int[] widths, String S) {
        int[] result = new int[2];
        int rows = 1;
        int lineCount = 0;
        char[] chars = S.toCharArray();
        for (char c : chars) {
            int width = widths[c - 'a'];
            if (lineCount + width > 100) {
                rows++;
                lineCount = width;
            } else {
                lineCount += width;
            }
        }
        result[0] = rows;
        result[1] = lineCount;

        return result;
    }

    @Test
    public void longestDiverseString() {
        int a = 0, b = 8, c = 11;
        logResult(longestDiverseString(a, b, c));
    }

    /**
     * 5195. 最长快乐字符串 如果字符串中不含有任何 'aaa'，'bbb' 或 'ccc' 这样的字符串作为子串，那么该字符串就是一个「快乐字符串」。
     *
     * <p>给你三个整数 a，b ，c，请你返回 任意一个 满足下列全部条件的字符串 s：
     *
     * <p>s 是一个尽可能长的快乐字符串。 s 中 最多 有a 个字母 'a'、b 个字母 'b'、c 个字母 'c' 。 s 中只含有 'a'、'b' 、'c' 三种字母。
     * 如果不存在这样的字符串 s ，请返回一个空字符串 ""。
     *
     * <p>示例 1：
     *
     * <p>输入：a = 1, b = 1, c = 7 输出："ccaccbcc" 解释："ccbccacc" 也是一种正确答案。 示例 2：
     *
     * <p>输入：a = 2, b = 2, c = 1 输出："aabbc" 示例 3：
     *
     * <p>输入：a = 7, b = 1, c = 0 输出："aabaa" 解释：这是该测试用例的唯一正确答案。
     *
     * <p>提示：
     *
     * <p>0 <= a, b, c <= 100 a + b + c > 0
     *
     * @param a
     * @param b
     * @param c
     * @return
     */
    public String longestDiverseString(int a, int b, int c) {
        StringBuilder sb = new StringBuilder();
        char lastChar = ' ';

        while (a > 0 || b > 0 || c > 0) {

            boolean flag = false;

            if (a >= b && a >= c) {
                if (lastChar != 'a') {
                    a = addChar(sb, 'a', a, true);
                    lastChar = 'a';
                    flag = true;
                } else {
                    if (b > 0 && b >= c) {
                        b = addChar(sb, 'b', b, false);
                        lastChar = 'b';
                        flag = true;
                    } else if (c > 0) {
                        c = addChar(sb, 'c', c, false);
                        lastChar = 'c';
                        flag = true;
                    }
                }

            } else if (b >= a && b >= c) {
                if (lastChar != 'b') {
                    flag = true;
                    b = addChar(sb, 'b', b, true);
                    lastChar = 'b';
                } else {
                    if (a > 0 && a >= c) {
                        a = addChar(sb, 'a', a, false);
                        lastChar = 'a';
                        flag = true;
                    } else if (c > 0) {
                        c = addChar(sb, 'c', c, false);
                        lastChar = 'c';
                        flag = true;
                    }
                }
            } else if (c >= b && c >= a) {
                if (lastChar != 'c') {
                    c = addChar(sb, 'c', c, true);
                    lastChar = 'c';
                    flag = true;
                } else {
                    if (a > 0 && a >= b) {
                        a = addChar(sb, 'a', a, false);
                        lastChar = 'a';
                        flag = true;
                    } else if (b > 0) {
                        b = addChar(sb, 'b', b, false);
                        lastChar = 'b';
                        flag = true;
                    }
                }
            }

            if (!flag) {
                break;
            }
        }

        return sb.toString();
    }

    private int addChar(StringBuilder sb, char num, int count, boolean flag) {

        if (count >= 2 && flag) {
            sb.append(num);
            sb.append(num);
            count -= 2;
        } else {
            sb.append(num);
            count--;
        }
        return count;
    }

    @Test
    public void mostCommonWord() {
        String paragraph = "Bob";
        String[] banned = new String[0];
        logResult(mostCommonWord(paragraph, banned));
    }

    /**
     * 819. 最常见的单词 给定一个段落 (paragraph) 和一个禁用单词列表 (banned)。返回出现次数最多，同时不在禁用列表中的单词。
     * 题目保证至少有一个词不在禁用列表中，而且答案唯一。
     *
     * <p>禁用列表中的单词用小写字母表示，不含标点符号。段落中的单词不区分大小写。答案都是小写字母。
     *
     * <p>示例：
     *
     * <p>输入: paragraph = "Bob hit a ball, the hit BALL flew far after it was hit." banned = ["hit"]
     * 输出: "ball" 解释: "hit" 出现了3次，但它是一个禁用的单词。 "ball" 出现了2次
     * (同时没有其他单词出现2次)，所以它是段落里出现次数最多的，且不在禁用列表中的单词。 注意，所有这些单词在段落里不区分大小写，标点符号需要忽略（即使是紧挨着单词也忽略， 比如
     * "ball,"）， "hit"不是最终的答案，虽然它出现次数更多，但它在禁用单词列表中。
     *
     * <p>说明：
     *
     * <p>1 <= 段落长度 <= 1000. 1 <= 禁用单词个数 <= 100. 1 <= 禁用单词长度 <= 10. 答案是唯一的, 且都是小写字母 (即使在 paragraph
     * 里是大写的，即使是一些特定的名词，答案都是小写的。) paragraph 只包含字母、空格和下列标点符号!?',;. 不存在没有连字符或者带有连字符的单词。
     * 单词里只包含字母，不会出现省略号或者其他标点符号。
     *
     * @param paragraph
     * @param banned
     * @return
     */
    public String mostCommonWord(String paragraph, String[] banned) {
        int maxCount = 0;
        String result = "";
        Set<String> set = new HashSet<>();
        for (String word : banned) {
            set.add(word);
        }
        Map<String, Integer> map = new HashMap<>();
        StringBuilder word = new StringBuilder();
        char[] chars = paragraph.toCharArray();
        int len = chars.length;
        for (int i = 0; i <= len; i++) {
            if (i < len && Character.isLetter(chars[i])) {
                word.append(Character.toLowerCase(chars[i]));
            } else if (word.length() > 0) {
                String key = word.toString();
                word.setLength(0);
                if (set.contains(key)) {
                    continue;
                }
                int count = map.getOrDefault(key, 0);
                map.put(key, ++count);
                if (count > maxCount) {
                    maxCount = count;
                    result = key;
                }
            }
        }

        return result;
    }

    @Test
    public void shortestToChar() {
        String S = "loveleetcode";
        char C = 'e';
        logResult(shortestToChar(S, C));
    }

    /**
     * 821. 字符的最短距离 给定一个字符串 S 和一个字符 C。返回一个代表字符串 S 中每个字符到字符串 S 中的字符 C 的最短距离的数组。
     *
     * <p>示例 1:
     *
     * <p>输入: S = "loveleetcode", C = 'e' 输出: [3, 2, 1, 0, 1, 0, 0, 1, 2, 2, 1, 0] 说明:
     *
     * <p>字符串 S 的长度范围为 [1, 10000]。 C 是一个单字符，且保证是字符串 S 里的字符。 S 和 C 中的所有字母均为小写字母。
     *
     * @param S
     * @param C
     * @return
     */
    public int[] shortestToChar(String S, char C) {
        char[] chars = S.toCharArray();
        int len = S.length();
        int[] result = new int[len];
        // 遍历两遍
        int index = Integer.MAX_VALUE;
        for (int i = 0; i < len; i++) {
            char c = chars[i];
            if (c == C) {
                index = 0;
            }
            if (index != Integer.MAX_VALUE) {
                result[i] = index++;
            } else {
                result[i] = index;
            }
        }
        index = Integer.MAX_VALUE;
        for (int i = len - 1; i >= 0; i--) {
            char c = chars[i];
            if (c == C) {
                index = 0;
            }
            if (index != Integer.MAX_VALUE) {
                result[i] = Math.min(result[i], index++);
            }
        }
        return result;
    }

    /**
     * 824. 山羊拉丁文 给定一个由空格分割单词的句子 S。每个单词只包含大写或小写字母。
     *
     * <p>我们要将句子转换为 “Goat Latin”（一种类似于 猪拉丁文 - Pig Latin 的虚构语言）。
     *
     * <p>山羊拉丁文的规则如下：
     *
     * <p>如果单词以元音开头（a, e, i, o, u），在单词后添加"ma"。 例如，单词"apple"变为"applema"。
     *
     * <p>如果单词以辅音字母开头（即非元音字母），移除第一个字符并将它放到末尾，之后再添加"ma"。 例如，单词"goat"变为"oatgma"。
     *
     * <p>根据单词在句子中的索引，在单词最后添加与索引相同数量的字母'a'，索引从1开始。 例如，在第一个单词后添加"a"，在第二个单词后添加"aa"，以此类推。 返回将 S
     * 转换为山羊拉丁文后的句子。
     *
     * <p>示例 1:
     *
     * <p>输入: "I speak Goat Latin" 输出: "Imaa peaksmaaa oatGmaaaa atinLmaaaaa" 示例 2:
     *
     * <p>输入: "The quick brown fox jumped over the lazy dog" 输出: "heTmaa uickqmaaa rownbmaaaa
     * oxfmaaaaa umpedjmaaaaaa overmaaaaaaa hetmaaaaaaaa azylmaaaaaaaaa ogdmaaaaaaaaaa" 说明:
     *
     * <p>S 中仅包含大小写字母和空格。单词间有且仅有一个空格。 1 <= S.length <= 150。
     *
     * @param S
     * @return
     */
    public String toGoatLatin(String S) {
        StringBuilder result = new StringBuilder();

        String[] words = S.split(" ");
        for (int i = 0; i < words.length; i++) {
            String word = words[i];

            char c = word.charAt(0);
            if (vowel(c)) {
                // 如果单词以元音开头（a, e, i, o, u），在单词后添加"ma"。
                result.append(word);
            } else {
                // 如果单词以辅音字母开头（即非元音字母），移除第一个字符并将它放到末尾，之后再添加"ma"。
                result.append(word.substring(1));
                result.append(c);
            }
            // 根据单词在句子中的索引，在单词最后添加与索引相同数量的字母'a'，索引从1开始。
            // 例如，在第一个单词后添加"a"，在第二个单词后添加"aa"，以此类推。
            result.append("ma");
            for (int j = 0; j < i + 1; j++) {
                result.append("a");
            }
            if (i < words.length - 1) {
                result.append(" ");
            }
        }

        return result.toString();
    }

    private boolean vowel(char c) {
        switch (c) {
            case 'A':
            case 'E':
            case 'I':
            case 'O':
            case 'U':
            case 'a':
            case 'e':
            case 'i':
            case 'o':
            case 'u':
                return true;
        }
        return false;
    }

    /**
     * 844. 比较含退格的字符串 给定 S 和 T 两个字符串，当它们分别被输入到空白的文本编辑器后，判断二者是否相等，并返回结果。 # 代表退格字符。
     *
     * <p>示例 1：
     *
     * <p>输入：S = "ab#c", T = "ad#c" 输出：true 解释：S 和 T 都会变成 “ac”。 示例 2：
     *
     * <p>输入：S = "ab##", T = "c#d#" 输出：true 解释：S 和 T 都会变成 “”。 示例 3：
     *
     * <p>输入：S = "a##c", T = "#a#c" 输出：true 解释：S 和 T 都会变成 “c”。 示例 4：
     *
     * <p>输入：S = "a#c", T = "b" 输出：false 解释：S 会变成 “c”，但 T 仍然是 “b”。
     *
     * <p>提示：
     *
     * <p>1 <= S.length <= 200 1 <= T.length <= 200 S 和 T 只含有小写字母以及字符 '#'。
     *
     * @param S
     * @param T
     * @return
     */
    public boolean backspaceCompare(String S, String T) {
        Deque<Character> stack1 = new LinkedList<>();
        Deque<Character> stack2 = new LinkedList<>();
        for (char c : S.toCharArray()) {
            if (c == '#') {
                stack1.pollLast();
            } else {
                stack1.offerLast(c);
            }
        }
        for (char c : T.toCharArray()) {
            if (c == '#') {
                stack2.pollLast();
            } else {
                stack2.offerLast(c);
            }
        }
        if (stack1.size() != stack2.size()) {
            return false;
        }
        boolean result = true;
        while (!stack1.isEmpty()) {
            if (stack1.pollLast() != stack2.pollLast()) {
                result = false;
                break;
            }
        }
        return result;
    }

    @Test
    public void maxDistToClosest() {
        int[] seats = {0, 0, 0, 0, 0, 1, 0, 0, 0};
        logResult(maxDistToClosest(seats));
    }

    /**
     * 849. 到最近的人的最大距离 在一排座位（ seats）中，1 代表有人坐在座位上，0 代表座位上是空的。
     *
     * <p>至少有一个空座位，且至少有一人坐在座位上。
     *
     * <p>亚历克斯希望坐在一个能够使他与离他最近的人之间的距离达到最大化的座位上。
     *
     * <p>返回他到离他最近的人的最大距离。
     *
     * <p>示例 1：
     *
     * <p>输入：[1,0,0,0,1,0,1] 输出：2 解释： 如果亚历克斯坐在第二个空位（seats[2]）上，他到离他最近的人的距离为 2 。
     * 如果亚历克斯坐在其它任何一个空位上，他到离他最近的人的距离为 1 。 因此，他到离他最近的人的最大距离是 2 。 示例 2：
     *
     * <p>输入：[1,0,0,0] 输出：3 解释： 如果亚历克斯坐在最后一个座位上，他离最近的人有 3 个座位远。 这是可能的最大距离，所以答案是 3 。 提示：
     *
     * <p>1 <= seats.length <= 20000 seats 中只含有 0 和 1，至少有一个 0，且至少有一个 1。
     *
     * @param seats
     * @return
     */
    public int maxDistToClosest(int[] seats) {

        int len = seats.length;
        for (int i = 1; i < len; i++) {

            if (seats[i - 1] > 0 && seats[i] == 0) {
                seats[i] = seats[i - 1] + 1;
            }
        }
        for (int i = len - 2; i >= 0; i--) {
            if (seats[i + 1] > 0) {
                if (seats[i] == 0) {
                    seats[i] = seats[i + 1] + 1;
                } else {
                    seats[i] = Math.min(seats[i], seats[i + 1] + 1);
                }
            }
        }
        log.debug("seats:{}", seats);
        int max = 0;
        for (int seat : seats) {
            if (seat > max) {
                max = seat;
            }
        }
        return max - 1;
    }

    @Test
    public void numDecodings() {
        String s = "12";
        logResult(numDecodings(s));
    }

    /**
     * 91. 解码方法 一条包含字母 A-Z 的消息通过以下方式进行了编码：
     *
     * <p>'A' -> 1 'B' -> 2 ... 'Z' -> 26 给定一个只包含数字的非空字符串，请计算解码方法的总数。
     *
     * <p>示例 1:
     *
     * <p>输入: "12" 输出: 2 解释: 它可以解码为 "AB"（1 2）或者 "L"（12）。 示例 2:
     *
     * <p>输入: "226" 输出: 3 解释: 它可以解码为 "BZ" (2 26), "VF" (22 6), 或者 "BBF" (2 2 6) 。
     *
     * @param s
     * @return
     */
    public int numDecodings(String s) {
        int len = s.length();
        if (len == 0) {
            return len;
        }
        int[] nums = new int[len];
        char[] chars = s.toCharArray();
        if (chars[0] != '0') {
            nums[0] = 1;
        }

        for (int i = 1; i < len; i++) {
            int count = 0;
            if (chars[i - 1] == '1' || (chars[i - 1] == '2' && chars[i] <= '6')) {
                if (i - 2 >= 0) {
                    count = nums[i - 2];
                } else {
                    count = 1;
                }
            }
            if (chars[i] > '0') {
                log.debug("nums;{}", nums);
                nums[i] += nums[i - 1];
            }
            nums[i] += count;
        }
        log.debug("nums;{}", nums);
        return nums[len - 1];
    }

    @Test
    public void removeDuplicates() {
        String str = "abbaca";
        logResult(removeDuplicates(str));
    }

    /**
     * 1047. 删除字符串中的所有相邻重复项 给出由小写字母组成的字符串 S，重复项删除操作会选择两个相邻且相同的字母，并删除它们。
     *
     * <p>在 S 上反复执行重复项删除操作，直到无法继续删除。
     *
     * <p>在完成所有重复项删除操作后返回最终的字符串。答案保证唯一。
     *
     * <p>示例：
     *
     * <p>输入："abbaca" 输出："ca" 解释： 例如，在 "abbaca" 中，我们可以删除 "bb" 由于两字母相邻且相同，
     * 这是此时唯一可以执行删除操作的重复项。之后我们得到字符串 "aaca"，其中又只有 "aa" 可以执行重复项删除操作， 所以最后的字符串为 "ca"。
     *
     * <p>提示：
     *
     * <p>1 <= S.length <= 20000 S 仅由小写英文字母组成。
     *
     * @param S
     * @return
     */
    public String removeDuplicates(String S) {
        Deque<Character> stack = new LinkedList<>();

        for (char c : S.toCharArray()) {
            boolean flag = true;
            while (!stack.isEmpty() && stack.peekLast() == c) {
                stack.pollLast();
                flag = false;
            }
            if (flag) {
                stack.addLast(c);
            }
        }
        StringBuilder sb = new StringBuilder();

        for (char c : stack) {
            sb.append(c);
        }
        return sb.toString();
    }

    @Test
    public void buddyStrings() {
        String s1 = "acccccb", s2 = "bccccca";
        logResult(buddyStrings(s1, s2));
    }

    /**
     * 859. 亲密字符串 给定两个由小写字母构成的字符串 A 和 B ，只要我们可以通过交换 A 中的两个字母得到与 B 相等的结果，就返回 true ；否则返回 false 。
     *
     * <p>示例 1：
     *
     * <p>输入： A = "ab", B = "ba" 输出： true 示例 2：
     *
     * <p>输入： A = "ab", B = "ab" 输出： false 示例 3:
     *
     * <p>输入： A = "aa", B = "aa" 输出： true 示例 4：
     *
     * <p>输入： A = "aaaaaaabc", B = "aaaaaaacb" 输出： true 示例 5：
     *
     * <p>输入： A = "", B = "aa" 输出： false
     *
     * <p>提示：
     *
     * <p>0 <= A.length <= 20000 0 <= B.length <= 20000 A 和 B 仅由小写字母构成。
     *
     * @param A
     * @param B
     * @return
     */
    public boolean buddyStrings(String A, String B) {
        int len1 = A.length(), len2 = B.length();
        if (len1 != len2) {
            return false;
        }
        // 两种情况
        // 两个相同的字符
        // 两个不同的字符交换位置
        int[] letters = new int[26];
        boolean twoChar = false;

        int diffCount = 0;
        int firstIndex = -1, secondIndex = -1;
        for (int i = 0; i < len1; i++) {
            char c1 = A.charAt(i);
            char c2 = B.charAt(i);
            letters[c1 - 'a']++;
            if (letters[c1 - 'a'] >= 2) {
                twoChar = true;
            }
            if (c1 != c2) {
                diffCount++;
                if (diffCount == 1) {
                    firstIndex = i;
                }
                if (diffCount == 2) {
                    secondIndex = i;
                }
            }

            if (diffCount > 2) {
                return false;
            }
        }
        // 两个相同的字符
        if (diffCount == 0 && twoChar) {
            return true;
        }
        log.debug("first:{},second:{}", firstIndex, secondIndex);
        if (diffCount == 2
                && A.charAt(firstIndex) == B.charAt(secondIndex)
                && B.charAt(firstIndex) == A.charAt(secondIndex)) {
            return true;
        }

        return false;
    }

    /**
     * 917. 仅仅反转字母 给定一个字符串 S，返回 “反转后的” 字符串，其中不是字母的字符都保留在原地，而所有字母的位置发生反转。
     *
     * <p>示例 1：
     *
     * <p>输入："ab-cd" 输出："dc-ba" 示例 2：
     *
     * <p>输入："a-bC-dEf-ghIj" 输出："j-Ih-gfE-dCba" 示例 3：
     *
     * <p>输入："Test1ng-Leet=code-Q!" 输出："Qedo1ct-eeLg=ntse-T!"
     *
     * <p>提示：
     *
     * <p>S.length <= 100 33 <= S[i].ASCIIcode <= 122 S 中不包含 \ or "
     *
     * @param S
     * @return
     */
    public String reverseOnlyLetters(String S) {
        char[] chars = S.toCharArray();
        int len = chars.length;
        int left = 0, right = len - 1;
        while (left < right) {
            if (!Character.isLetter(chars[left])) {
                left++;
                continue;
            }
            if (!Character.isLetter(chars[right])) {
                right--;
                continue;
            }
            char tmp = chars[left];
            chars[left++] = chars[right];
            chars[right--] = tmp;
        }

        return new String(chars);
    }

    /**
     * 1408. 数组中的字符串匹配 给你一个字符串数组 words ，数组中的每个字符串都可以看作是一个单词。请你按 任意 顺序返回 words 中是其他单词的子字符串的所有单词。
     *
     * <p>如果你可以删除 words[j] 最左侧和/或最右侧的若干字符得到 word[i] ，那么字符串 words[i] 就是 words[j] 的一个子字符串。
     *
     * <p>示例 1：
     *
     * <p>输入：words = ["mass","as","hero","superhero"] 输出：["as","hero"] 解释："as" 是 "mass" 的子字符串，"hero"
     * 是 "superhero" 的子字符串。 ["hero","as"] 也是有效的答案。 示例 2：
     *
     * <p>输入：words = ["leetcode","et","code"] 输出：["et","code"] 解释："et" 和 "code" 都是 "leetcode" 的子字符串。
     * 示例 3：
     *
     * <p>输入：words = ["blue","green","bu"] 输出：[]
     *
     * <p>提示：
     *
     * <p>1 <= words.length <= 100 1 <= words[i].length <= 30 words[i] 仅包含小写英文字母。 题目数据 保证 每个
     * words[i] 都是独一无二的。
     *
     * @param words
     * @return
     */
    public List<String> stringMatching(String[] words) {
        List<String> result = new ArrayList<>();

        Arrays.sort(words, Comparator.comparingInt(String::length));
        for (int i = 0; i < words.length; i++) {

            for (int j = i + 1; j < words.length; j++) {
                if (words[j].contains(words[i])) {
                    result.add(words[i]);
                    break;
                }
            }
        }

        return result;
    }

    /**
     * 929. 独特的电子邮件地址 每封电子邮件都由一个本地名称和一个域名组成，以 @ 符号分隔。
     *
     * <p>例如，在 alice@leetcode.com中， alice 是本地名称，而 leetcode.com 是域名。
     *
     * <p>除了小写字母，这些电子邮件还可能包含 '.' 或 '+'。
     *
     * <p>如果在电子邮件地址的本地名称部分中的某些字符之间添加句点（'.'），则发往那里的邮件将会转发到本地名称中没有点的同一地址。例如，"alice.z@leetcode.com” 和
     * “alicez@leetcode.com” 会转发到同一电子邮件地址。 （请注意，此规则不适用于域名。）
     *
     * <p>如果在本地名称中添加加号（'+'），则会忽略第一个加号后面的所有内容。这允许过滤某些电子邮件，例如 m.y+name@email.com 将转发到 my@email.com。
     * （同样，此规则不适用于域名。）
     *
     * <p>可以同时使用这两个规则。
     *
     * <p>给定电子邮件列表 emails，我们会向列表中的每个地址发送一封电子邮件。实际收到邮件的不同地址有多少？
     *
     * <p>示例：
     *
     * <p>输入：["test.email+alex@leetcode.com","test.e.mail+bob.cathy@leetcode.com","testemail+david@lee.tcode.com"]
     * 输出：2 解释：实际收到邮件的是 "testemail@leetcode.com" 和 "testemail@lee.tcode.com"。
     *
     * <p>提示：
     *
     * <p>1 <= emails[i].length <= 100 1 <= emails.length <= 100 每封 emails[i] 都包含有且仅有一个 '@' 字符。
     *
     * @param emails
     * @return
     */
    public int numUniqueEmails(String[] emails) {
        Set<String> set = new HashSet<>();
        StringBuilder sb = new StringBuilder();
        for (String email : emails) {
            int index = email.indexOf("@");
            sb.setLength(0);
            String name = email.substring(0, index);
            for (char c : name.toCharArray()) {
                if (c == '.') {
                    continue;
                }
                if (c == '+') {
                    break;
                }
                sb.append(c);
            }
            sb.append(email.substring(index));
            set.add(sb.toString());
        }

        return set.size();
    }

    /**
     * 893. 特殊等价字符串组 你将得到一个字符串数组 A。
     *
     * <p>如果经过任意次数的移动，S == T，那么两个字符串 S 和 T 是特殊等价的。
     *
     * <p>一次移动包括选择两个索引 i 和 j，且 i ％ 2 == j ％ 2，交换 S[j] 和 S [i]。
     *
     * <p>现在规定，A 中的特殊等价字符串组是 A 的非空子集 S，这样不在 S 中的任何字符串与 S 中的任何字符串都不是特殊等价的。
     *
     * <p>返回 A 中特殊等价字符串组的数量。
     *
     * <p>示例 1：
     *
     * <p>输入：["a","b","c","a","c","c"] 输出：3 解释：3 组 ["a","a"]，["b"]，["c","c","c"] 示例 2：
     *
     * <p>输入：["aa","bb","ab","ba"] 输出：4 解释：4 组 ["aa"]，["bb"]，["ab"]，["ba"] 示例 3：
     *
     * <p>输入：["abc","acb","bac","bca","cab","cba"] 输出：3 解释：3 组
     * ["abc","cba"]，["acb","bca"]，["bac","cab"] 示例 4：
     *
     * <p>输入：["abcd","cdab","adcb","cbad"] 输出：1 解释：1 组 ["abcd","cdab","adcb","cbad"]
     *
     * <p>提示：
     *
     * <p>1 <= A.length <= 1000 1 <= A[i].length <= 20 所有 A[i] 都具有相同的长度。 所有 A[i] 都只由小写字母组成。
     *
     * @param A
     * @return
     */
    public int numSpecialEquivGroups(String[] A) {
        // 设计一个函数, 让 两个字符串 S 和 T 经过函数映射成相同的值
        Set<String> set = new HashSet<>();
        for (String str : A) {
            int[] counts = new int[52];
            for (int i = 0; i < str.length(); i++) {
                char c = str.charAt(i);
                // 偶数前26位，奇数后26位
                int index = c - 'a' + (i & 1) * 26;
                counts[index]++;
            }
            set.add(Arrays.toString(counts));
        }

        return set.size();
    }

    /**
     * 937. 重新排列日志文件 你有一个日志数组 logs。每条日志都是以空格分隔的字串。
     *
     * <p>对于每条日志，其第一个字为字母数字标识符。然后，要么：
     *
     * <p>标识符后面的每个字将仅由小写字母组成，或； 标识符后面的每个字将仅由数字组成。 我们将这两种日志分别称为字母日志和数字日志。保证每个日志在其标识符后面至少有一个字。
     *
     * <p>将日志重新排序，使得所有字母日志都排在数字日志之前。字母日志按内容字母顺序排序，忽略标识符；在内容相同时，按标识符排序。数字日志应该按原来的顺序排列。
     *
     * <p>返回日志的最终顺序。
     *
     * <p>示例 ：
     *
     * <p>输入：["a1 9 2 3 1","g1 act car","zo4 4 7","ab1 off key dog","a8 act zoo"] 输出：["g1 act
     * car","a8 act zoo","ab1 off key dog","a1 9 2 3 1","zo4 4 7"]
     *
     * <p>提示：
     *
     * <p>0 <= logs.length <= 100 3 <= logs[i].length <= 100 logs[i] 保证有一个标识符，并且标识符后面有一个字。
     *
     * @param logs
     * @return
     */
    public String[] reorderLogFiles(String[] logs) {
        //
        int len = logs.length;
        String[] result = new String[len];
        List<String> letterList = new ArrayList<>();
        List<String> numberList = new ArrayList<>();
        for (String strLog : logs) {
            int index = strLog.indexOf(" ");
            char c = strLog.charAt(index + 1);
            if (c >= '0' && c <= '9') {
                numberList.add(strLog);
            } else {
                letterList.add(strLog);
            }
        }

        Collections.sort(
                letterList,
                (s1, s2) -> {
                    int index1 = s1.indexOf(" ");
                    int index2 = s2.indexOf(" ");
                    String strA = s1.substring(index1 + 1);
                    String strB = s2.substring(index2 + 1);
                    if (Objects.equals(strA, strB)) {
                        return s1.substring(0, index1).compareTo(s2.substring(0, index2));
                    }
                    return strA.compareTo(strB);
                });

        int index = 0;
        for (String strLog : letterList) {
            result[index++] = strLog;
        }
        for (String strLog : numberList) {
            result[index++] = strLog;
        }
        return result;
    }

    /**
     * 面试题 01.02. 判定是否互为字符重排 给定两个字符串 s1 和 s2，请编写一个程序，确定其中一个字符串的字符重新排列后，能否变成另一个字符串。
     *
     * <p>示例 1：
     *
     * <p>输入: s1 = "abc", s2 = "bca" 输出: true 示例 2：
     *
     * <p>输入: s1 = "abc", s2 = "bad" 输出: false 说明：
     *
     * <p>0 <= len(s1) <= 100 0 <= len(s2) <= 100
     *
     * @param s1
     * @param s2
     * @return
     */
    public boolean CheckPermutation(String s1, String s2) {
        int[] letters = new int[26];
        for (char c : s1.toCharArray()) {
            letters[c - 'a']++;
        }
        for (char c : s2.toCharArray()) {
            letters[c - 'a']--;
        }
        for (int num : letters) {
            if (num != 0) {
                return false;
            }
        }
        return true;
    }

    @Test
    public void replaceSpaces() {
        String S = "ds sdfs afs sdfa dfssf asdf             ";
        int length = 27;
        // String S = "Mr John Smith    ";
        // int length = 13;
        logResult(replaceSpaces(S, length) + "END");
    }
    /**
     * 面试题 01.03. URL化 URL化。编写一种方法，将字符串中的空格全部替换为%20。假定该字符串尾部有足够的空间存放新增字符，
     * 并且知道字符串的“真实”长度。（注：用Java实现的话，请使用字符数组实现，以便直接在数组上操作。）
     *
     * <p>示例1:
     *
     * <p>输入："Mr John Smith ", 13 输出："Mr%20John%20Smith" 示例2:
     *
     * <p>输入：" ", 5 输出："%20%20%20%20%20" 提示：
     *
     * <p>字符串长度在[0, 500000]范围内。
     *
     * @param S
     * @param length
     * @return
     */
    public String replaceSpaces(String S, int length) {

        char[] chars = S.toCharArray();

        /*int len = 0;
        for (int i = 0; i < length; i++) {
            char c = S.charAt(i);
            if (c == ' ') {
                len += 3;
            } else {
                len++;
            }
        }*/
        int index = S.length() - 1;
        for (int i = length - 1; i >= 0; i--) {
            char c = S.charAt(i);
            if (c == ' ') {
                chars[index--] = '0';
                chars[index--] = '2';
                chars[index--] = '%';
            } else {
                chars[index--] = c;
            }
        }

        /*for (int i = 0; i < length; i++) {
            char c = S.charAt(i);
            if (c == ' ') {
                sb.append("%20");
            } else {
                sb.append(c);
            }
        }*/
        log.debug("index:{}", index);
        index++;
        return new String(chars, index, S.length() - index);
    }

    /**
     * 面试题 01.04. 回文排列 给定一个字符串，编写一个函数判定其是否为某个回文串的排列之一。
     *
     * <p>回文串是指正反两个方向都一样的单词或短语。排列是指字母的重新排列。
     *
     * <p>回文串不一定是字典当中的单词。
     *
     * <p>示例1：
     *
     * <p>输入："tactcoa" 输出：true（排列有"tacocat"、"atcocta"，等等）
     *
     * @param s
     * @return
     */
    public boolean canPermutePalindrome(String s) {
        // 假设只有小写
        int[] letters = new int[128];
        // 思路, 最多一个字符是奇数个,其它必须是偶数个
        for (char c : s.toCharArray()) {
            letters[c]++;
        }
        boolean flag = false;
        for (int num : letters) {
            if ((num & 1) == 0) {
                continue;
            }
            if (!flag) {
                flag = true;
            } else {
                return false;
            }
        }
        return true;
    }

    /**
     * 面试题 01.09. 字符串轮转 字符串轮转。给定两个字符串s1和s2，请编写代码检查s2是否为s1旋转而成（比如，waterbottle是erbottlewat旋转后的字符串）。
     *
     * <p>示例1:
     *
     * <p>输入：s1 = "waterbottle", s2 = "erbottlewat" 输出：True 示例2:
     *
     * <p>输入：s1 = "aa", "aba" 输出：False 提示：
     *
     * <p>字符串长度在[0, 100000]范围内。 说明:
     *
     * <p>你能只调用一次检查子串的方法吗？
     *
     * @param s1
     * @param s2
     * @return
     */
    public boolean isFlipedString(String s1, String s2) {
        if (s1.length() != s2.length()) {
            return false;
        }

        return (s1 + s1).contains(s2);
    }

    /**
     * 面试题05. 替换空格 请实现一个函数，把字符串 s 中的每个空格替换成"%20"。
     *
     * <p>示例 1：
     *
     * <p>输入：s = "We are happy." 输出："We%20are%20happy."
     *
     * <p>限制：
     *
     * <p>0 <= s 的长度 <= 10000
     *
     * @param s
     * @return
     */
    public String replaceSpace(String s) {
        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (c == ' ') {
                sb.append("%20");
            } else {
                sb.append(c);
            }
        }

        return sb.toString();
    }

    @Test
    public void reformat() {
        String s = "covid2019";
        logResult(reformat(s));
    }
    /**
     * 1417. 重新格式化字符串 给你一个混合了数字和字母的字符串 s，其中的字母均为小写英文字母。
     *
     * <p>请你将该字符串重新格式化，使得任意两个相邻字符的类型都不同。也就是说，字母后面应该跟着数字，而数字后面应该跟着字母。
     *
     * <p>请你返回 重新格式化后 的字符串；如果无法按要求重新格式化，则返回一个 空字符串 。
     *
     * <p>示例 1：
     *
     * <p>输入：s = "a0b1c2" 输出："0a1b2c" 解释："0a1b2c" 中任意两个相邻字符的类型都不同。 "a0b1c2", "0a1b2c", "0c2a1b"
     * 也是满足题目要求的答案。 示例 2：
     *
     * <p>输入：s = "leetcode" 输出："" 解释："leetcode" 中只有字母，所以无法满足重新格式化的条件。 示例 3：
     *
     * <p>输入：s = "1229857369" 输出："" 解释："1229857369" 中只有数字，所以无法满足重新格式化的条件。 示例 4：
     *
     * <p>输入：s = "covid2019" 输出："c2o0v1i9d" 示例 5：
     *
     * <p>输入：s = "ab123" 输出："1a2b3"
     *
     * <p>提示：
     *
     * <p>1 <= s.length <= 500 s 仅由小写英文字母和/或数字组成。
     *
     * @param s
     * @return
     */
    public String reformat(String s) {

        int len = s.length();
        if (len == 1) {
            return s;
        }
        char[] nums = new char[len];
        char[] letters = new char[len];
        int numLen = 0, letterLen = 0;
        for (char c : s.toCharArray()) {
            if (c >= '0' && c <= '9') {
                nums[numLen++] = c;
            } else {
                letters[letterLen++] = c;
            }
        }

        if (Math.abs(numLen - letterLen) > 1) {
            return "";
        }
        char[] result = new char[len];
        int i = 0, j = 0, index = 0;

        boolean numflag = true;
        if (numLen < letterLen) {
            numflag = false;
        }
        while (i < numLen || j < letterLen) {
            if (numflag) {
                result[index++] = nums[i++];
                numflag = false;
            } else {
                result[index++] = letters[j++];
                numflag = true;
            }
        }

        return new String(result);
    }

    /**
     * 5392. 分割字符串的最大得分 给你一个由若干 0 和 1 组成的字符串 s ，请你计算并返回将该字符串分割成两个 非空 子字符串（即 左 子字符串和 右
     * 子字符串）所能获得的最大得分。
     *
     * <p>「分割字符串的得分」为 左 子字符串中 0 的数量加上 右 子字符串中 1 的数量。
     *
     * <p>示例 1：
     *
     * <p>输入：s = "011101" 输出：5 解释： 将字符串 s 划分为两个非空子字符串的可行方案有： 左子字符串 = "0" 且 右子字符串 = "11101"，得分 = 1 +
     * 4 = 5 左子字符串 = "01" 且 右子字符串 = "1101"，得分 = 1 + 3 = 4 左子字符串 = "011" 且 右子字符串 = "101"，得分 = 1 + 2 =
     * 3 左子字符串 = "0111" 且 右子字符串 = "01"，得分 = 1 + 1 = 2 左子字符串 = "01110" 且 右子字符串 = "1"，得分 = 2 + 1 = 3
     * 示例 2：
     *
     * <p>输入：s = "00111" 输出：5 解释：当 左子字符串 = "00" 且 右子字符串 = "111" 时，我们得到最大得分 = 2 + 3 = 5 示例 3：
     *
     * <p>输入：s = "1111" 输出：3
     *
     * <p>提示：
     *
     * <p>2 <= s.length <= 500 字符串 s 仅由字符 '0' 和 '1' 组成。
     *
     * @param s
     * @return
     */
    public int maxScore(String s) {
        int count1 = 0;
        for (char c : s.toCharArray()) {
            if (c == '1') {
                count1++;
            }
        }
        int max = 0;
        int count0 = 0;
        for (int i = 0; i < s.length() - 1; i++) {
            char c = s.charAt(i);
            if (c == '0') {
                count0++;
            } else {
                count1--;
            }
            int count = count0 + count1;
            if (count > max) {
                max = count;
            }
        }

        return max;
    }

    @Test
    public void lengthOfLongestSubstring() {
        String s = "abcabcbb";
        int result = lengthOfLongestSubstring(s);
        logResult(result);
    }

    /**
     * 3. 无重复字符的最长子串 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
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
        int max = 0;
        if (s.length() == 0) {
            return 0;
        }
        int left = 0;
        int[] indexs = new int[128];
        Arrays.fill(indexs, -1);
        for (int i = 0; i < s.length(); i++) {
            int num = s.charAt(i);
            if (indexs[num] != -1 && indexs[num] >= left) {
                left = indexs[num] + 1;
            }
            indexs[num] = i;
            int len = i - left + 1;
            max = Math.max(max, len);
            log.debug("left:{}", left);
        }

        /*int left = 0 ,right = 1;
        while (right < s.length()) {
             if (s.charAt(left) == s.charAt(right)) {


                 left++;
                 right++;
             }

        } */

        return max;
    }

    /**
     * 面试题45. 把数组排成最小的数 输入一个正整数数组，把数组里所有数字拼接起来排成一个数，打印能拼接出的所有数字中最小的一个。
     *
     * <p>示例 1:
     *
     * <p>输入: [10,2] 输出: "102" 示例 2:
     *
     * <p>输入: [3,30,34,5,9] 输出: "3033459"
     *
     * <p>提示:
     *
     * <p>0 < nums.length <= 100 说明:
     *
     * <p>输出结果可能非常大，所以你需要返回一个字符串而不是整数 拼接起来的数字可能会有前导 0，最后结果不需要去掉前导 0
     *
     * @param nums
     * @return
     */
    public String minNumber(int[] nums) {
        String[] list = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            list[i] = String.valueOf(nums[i]);
        }
        Arrays.sort(list, (x, y) -> (x + y).compareTo(y + x));
        StringBuilder sb = new StringBuilder();
        for (String str : list) {

            sb.append(str);
        }

        return sb.toString();
    }

    /**
     * 5396. 连续字符 给你一个字符串 s ，字符串的「能量」定义为：只包含一种字符的最长非空子字符串的长度。
     *
     * <p>请你返回字符串的能量。
     *
     * <p>示例 1：
     *
     * <p>输入：s = "leetcode" 输出：2 解释：子字符串 "ee" 长度为 2 ，只包含字符 'e' 。 示例 2：
     *
     * <p>输入：s = "abbcccddddeeeeedcba" 输出：5 解释：子字符串 "eeeee" 长度为 5 ，只包含字符 'e' 。 示例 3：
     *
     * <p>输入：s = "triplepillooooow" 输出：5 示例 4：
     *
     * <p>输入：s = "hooraaaaaaaaaaay" 输出：11 示例 5：
     *
     * <p>输入：s = "tourist" 输出：1
     *
     * <p>提示：
     *
     * <p>1 <= s.length <= 500 s 只包含小写英文字母。
     *
     * @param s
     * @return
     */
    public int maxPower(String s) {
        int max = 0;
        char[] chars = s.toCharArray();
        int count = 1;
        for (int i = 1; i <= chars.length; i++) {
            if (i == chars.length || chars[i] != chars[i - 1]) {
                max = Math.max(count, max);
                count = 1;
            } else {
                count++;
            }
        }

        return max;
    }

    @Test
    public void largestNumber() {
        int[] cost = {4, 3, 2, 5, 6, 7, 2, 5, 5};
        int target = 9;
        // [2,4,2,5,3,2,5,5,4]
        // 739
        logResult(largestNumber(cost, target));
    }

    /**
     * 5399. 数位成本和为目标值的最大数字 给你一个整数数组 cost 和一个整数 target 。请你返回满足如下规则可以得到的 最大 整数：
     *
     * <p>给当前结果添加一个数位（i + 1）的成本为 cost[i] （cost 数组下标从 0 开始）。 总成本必须恰好等于 target 。 添加的数位中没有数字 0 。
     * 由于答案可能会很大，请你以字符串形式返回。
     *
     * <p>如果按照上述要求无法得到任何整数，请你返回 "0" 。
     *
     * <p>示例 1：
     *
     * <p>输入：cost = [4,3,2,5,6,7,2,5,5], target = 9 输出："7772" 解释：添加数位 '7' 的成本为 2 ，添加数位 '2' 的成本为 3
     * 。所以 "7772" 的代价为 2*3+ 3*1 = 9 。 "997" 也是满足要求的数字，但 "7772" 是较大的数字。 数字 成本 1 -> 4 2 -> 3 3 -> 2 4
     * -> 5 5 -> 6 6 -> 7 7 -> 2 8 -> 5 9 -> 5 示例 2：
     *
     * <p>输入：cost = [7,6,5,5,5,6,8,7,8], target = 12 输出："85" 解释：添加数位 '8' 的成本是 7 ，添加数位 '5' 的成本是 5
     * 。"85" 的成本为 7 + 5 = 12 。 示例 3：
     *
     * <p>输入：cost = [2,4,6,2,4,6,4,4,4], target = 5 输出："0" 解释：总成本是 target 的条件下，无法生成任何整数。 示例 4：
     *
     * <p>输入：cost = [6,10,15,40,40,40,40,40,40], target = 47 输出："32211"
     *
     * <p>提示：
     *
     * <p>cost.length == 9 1 <= cost[i] <= 5000 1 <= target <= 5000
     *
     * @param cost
     * @param target
     * @return
     */
    public String largestNumber(int[] cost, int target) {
        // 背包问题
        String[] dp = new String[target + 1];
        Comparator<String> comparator =
                Comparator.comparing(String::length).thenComparing(Function.identity());

        for (int i = 0; i <= target; i++) {
            String max = null;
            for (int j = 0; j < cost.length; j++) {
                String temp = null;
                if (i - cost[j] > 0) {
                    String str = dp[i - cost[j]];
                    if (str != null) {
                        temp = String.valueOf(j + 1).concat(str);
                    }
                } else if (i == cost[j]) {
                    temp = String.valueOf(j + 1);
                }
                if (max == null || (temp != null && comparator.compare(max, temp) < 0)) {
                    max = temp;
                }
            }
            dp[i] = max;
        }

        return dp[target] == null ? "0" : dp[target];
    }

    private static String largeNum = "0";

    private static List<String> largeNums = new ArrayList<>();

    private void largestNumber(int[] cost, int end, int target, StringBuilder sb) {
        if (target == 0) {

            if (sb.length() > largeNum.length()
                    || (sb.length() == largeNum.length()
                            && sb.toString().compareTo(largeNum) > 0)) {
                largeNum = sb.toString();
            }
            largeNums.add(sb.toString());
        }

        for (int i = end; i > 0; i--) {
            if (cost[i - 1] > target) {
                continue;
            }
            int count = target / cost[i - 1];

            int num = target - count * cost[i - 1];

            int len = sb.length();
            for (int j = 0; j < count; j++) {
                sb.append(i);
            }

            largestNumber(cost, i - 1, num, sb);
            sb.delete(len, len + count);
        }
    }

    @Test
    public void test44() {
        StringBuilder sb = new StringBuilder("ABC");
        int len = sb.length();
        int count = 9;
        for (int j = 0; j < count; j++) {
            sb.append(1);
        }

        sb.delete(len, len + count);
        logResult(sb.toString());
    }

    /**
     * 5413. 重新排列句子中的单词 「句子」是一个用空格分隔单词的字符串。给你一个满足下述格式的句子 text :
     *
     * <p>句子的首字母大写 text 中的每个单词都用单个空格分隔。 请你重新排列 text 中的单词，使所有单词按其长度的升序排列。如果两个单词的长度相同，则保留其在原句子中的相对顺序。
     *
     * <p>请同样按上述格式返回新的句子。
     *
     * <p>示例 1：
     *
     * <p>输入：text = "Leetcode is cool" 输出："Is cool leetcode" 解释：句子中共有 3 个单词，长度为 8 的 "Leetcode" ，长度为
     * 2 的 "is" 以及长度为 4 的 "cool" 。 输出需要按单词的长度升序排列，新句子中的第一个单词首字母需要大写。 示例 2：
     *
     * <p>输入：text = "Keep calm and code on" 输出："On and keep calm code" 解释：输出的排序情况如下： "On" 2 个字母。
     * "and" 3 个字母。 "keep" 4 个字母，因为存在长度相同的其他单词，所以它们之间需要保留在原句子中的相对顺序。 "calm" 4 个字母。 "code" 4 个字母。 示例
     * 3：
     *
     * <p>输入：text = "To be or not to be" 输出："To be or to be not"
     *
     * <p>提示：
     *
     * <p>text 以大写字母开头，然后包含若干小写字母以及单词间的单个空格。 1 <= text.length <= 10^5
     *
     * @param text
     * @return
     */
    public String arrangeWords(String text) {
        String[] words = text.split(" ");
        words[0] = words[0].toLowerCase();
        Arrays.sort(words, (str1, str2) -> str1.length() - str2.length());
        StringBuilder sb = new StringBuilder();
        for (String word : words) {
            if (sb.length() > 0) {
                sb.append(" ");
            }
            sb.append(word);
        }
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            if (i == 0) {
                word = word.substring(0, 1).toUpperCase() + word.substring(1);
            } else {
                sb.append(" ");
            }
            sb.append(word);
        }
        return sb.toString();
    }

    /**
     * 1371. 每个元音包含偶数次的最长子字符串 给你一个字符串 s ，请你返回满足以下条件的最长子字符串的长度：每个元音字母，即 'a'，'e'，'i'，'o'，'u'
     * ，在子字符串中都恰好出现了偶数次。
     *
     * <p>示例 1：
     *
     * <p>输入：s = "eleetminicoworoep" 输出：13 解释：最长子字符串是 "leetminicowor" ，它包含 e，i，o 各 2 个，以及 0 个 a，u 。
     * 示例 2：
     *
     * <p>输入：s = "leetcodeisgreat" 输出：5 解释：最长子字符串是 "leetc" ，其中包含 2 个 e 。 示例 3：
     *
     * <p>输入：s = "bcbcbc" 输出：6 解释：这个示例中，字符串 "bcbcbc" 本身就是最长的，因为所有的元音 a，e，i，o，u 都出现了 0 次。
     *
     * <p>提示：
     *
     * <p>1 <= s.length <= 5 x 10^5 s 只包含小写英文字母。
     *
     * @param s
     * @return
     */
    public int findTheLongestSubstring(String s) {

        int max = 0;
        int status = 0;
        // 记录 a，e，i，o，u 的起始位置
        int[] indexs = new int[1 << 5];
        Arrays.fill(indexs, -1);
        indexs[0] = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            switch (c) {
                case 'a':
                    status ^= 1;
                    break;
                case 'e':
                    status ^= 2;
                    break;
                case 'i':
                    status ^= 4;
                    break;
                case 'o':
                    status ^= 8;
                    break;
                case 'u':
                    status ^= 16;
                    break;
            }
            if (indexs[status] >= 0) {
                max = Math.max(max, i + 1 - indexs[status]);
            } else {
                // 起始位置, 当前位置的元音个数为奇数个
                indexs[status] = i + 1;
            }
        }
        return max;
    }

    @Test
    public void lengthLongestPath() {
        String input = "dir\n    file.txt";
        logResult(lengthLongestPath(input));
    }

    /**
     * 388. 文件的最长绝对路径 假设我们以下述方式将我们的文件系统抽象成一个字符串:
     *
     * <p>字符串 "dir\n\tsubdir1\n\tsubdir2\n\t\tfile.ext" 表示:
     *
     * <p>dir subdir1 subdir2 file.ext 目录 dir 包含一个空的子目录 subdir1 和一个包含一个文件 file.ext 的子目录 subdir2 。
     *
     * <p>字符串
     * "dir\n\tsubdir1\n\t\tfile1.ext\n\t\tsubsubdir1\n\tsubdir2\n\t\tsubsubdir2\n\t\t\tfile2.ext"
     * 表示:
     *
     * <p>dir subdir1 file1.ext subsubdir1 subdir2 subsubdir2 file2.ext 目录 dir 包含两个子目录 subdir1 和
     * subdir2。 subdir1 包含一个文件 file1.ext 和一个空的二级子目录 subsubdir1。subdir2 包含一个二级子目录 subsubdir2
     * ，其中包含一个文件 file2.ext。
     *
     * <p>我们致力于寻找我们文件系统中文件的最长 (按字符的数量统计) 绝对路径。例如，在上述的第二个例子中， 最长路径为
     * "dir/subdir2/subsubdir2/file2.ext"，其长度为 32 (不包含双引号)。
     *
     * <p>给定一个以上述格式表示文件系统的字符串，返回文件系统中文件的最长绝对路径的长度。 如果系统中没有文件，返回 0。
     *
     * <p>说明:
     *
     * <p>文件名至少存在一个 . 和一个扩展名。 目录或者子目录的名字不能包含 .。 要求时间复杂度为 O(n) ，其中 n 是输入字符串的大小。
     *
     * <p>请注意，如果存在路径 aaaaaaaaaaaaaaaaaaaaa/sth.png 的话，那么 a/aa/aaa/file1.txt 就不是一个最长的路径。
     *
     * @param input
     * @return
     */
    public int lengthLongestPath(String input) {
        int max = 0;

        String[] paths = input.split("\n");
        logResult(paths);

        int[] dirLens = new int[paths.length];
        int dirLen = 0;
        int lastIndex = -1;
        for (int i = 0; i < paths.length; i++) {
            String path = paths[i];
            int level = getDirLevel(lastIndex, path);
            log.debug("level:{}", level);
            if (level <= 0) {
                dirLen = 0;
            } else if (level <= lastIndex) {
                dirLen = dirLens[level - 1];
                log.debug("dirLen1:{}", dirLen);
            }

            log.debug("dirLen1:{}", dirLen);
            log.debug("p:{}", path.length());
            dirLen += path.length() - level + 1;
            log.debug("dirLen2:{}", dirLen);

            if (path.contains(".")) {
                log.debug("path:{} , dirLen :{}", path, dirLen);
                max = Math.max(dirLen, max);
            } else {
                dirLens[level] = dirLen;
            }
            lastIndex = level;
        }
        log.debug("dirLens:{}", dirLens);
        return max == 0 ? 0 : max - 1;
    }

    private int getDirLevel(int last, String path) {
        int level = 0;
        while (path.charAt(level) == '\t') {
            level++;
        }
        return level;
    }

    /**
     * 5416. 检查单词是否为句中其他单词的前缀 给你一个字符串 sentence 作为句子并指定检索词为 searchWord ，其中句子由若干用 单个空格 分隔的单词组成。
     *
     * <p>请你检查检索词 searchWord 是否为句子 sentence 中任意单词的前缀。
     *
     * <p>如果 searchWord 是某一个单词的前缀，则返回句子 sentence 中该单词所对应的下标（下标从 1 开始）。 如果 searchWord
     * 是多个单词的前缀，则返回匹配的第一个单词的下标（最小下标）。 如果 searchWord 不是任何单词的前缀，则返回 -1 。 字符串 S 的 「前缀」是 S 的任何前导连续子字符串。
     *
     * <p>示例 1：
     *
     * <p>输入：sentence = "i love eating burger", searchWord = "burg" 输出：4 解释："burg" 是 "burger" 的前缀，而
     * "burger" 是句子中第 4 个单词。 示例 2：
     *
     * <p>输入：sentence = "this problem is an easy problem", searchWord = "pro" 输出：2 解释："pro" 是
     * "problem" 的前缀，而 "problem" 是句子中第 2 个也是第 6 个单词，但是应该返回最小下标 2 。 示例 3：
     *
     * <p>输入：sentence = "i am tired", searchWord = "you" 输出：-1 解释："you" 不是句子中任何单词的前缀。 示例 4：
     *
     * <p>输入：sentence = "i use triple pillow", searchWord = "pill" 输出：4 示例 5：
     *
     * <p>输入：sentence = "hello from the other side", searchWord = "they" 输出：-1
     *
     * <p>提示：
     *
     * <p>1 <= sentence.length <= 100 1 <= searchWord.length <= 10 sentence 由小写英文字母和空格组成。 searchWord
     * 由小写英文字母组成。 前缀就是紧密附着于词根的语素，中间不能插入其它成分，并且它的位置是固定的——-位于词根之前。（引用自 前缀_百度百科 ）
     *
     * @param sentence
     * @param searchWord
     * @return
     */
    public int isPrefixOfWord(String sentence, String searchWord) {
        String[] words = sentence.split(" ");

        for (int i = 0; i < words.length; i++) {
            if (words[i].startsWith(searchWord)) {
                return i + 1;
            }
        }
        return -1;
    }

    /**
     * 5417. 定长子串中元音的最大数目 题目难度Medium 给你字符串 s 和整数 k 。
     *
     * <p>请返回字符串 s 中长度为 k 的单个子字符串中可能包含的最大元音字母数。
     *
     * <p>英文中的 元音字母 为（a, e, i, o, u）。
     *
     * <p>示例 1：
     *
     * <p>输入：s = "abciiidef", k = 3 输出：3 解释：子字符串 "iii" 包含 3 个元音字母。 示例 2：
     *
     * <p>输入：s = "aeiou", k = 2 输出：2 解释：任意长度为 2 的子字符串都包含 2 个元音字母。 示例 3：
     *
     * <p>输入：s = "leetcode", k = 3 输出：2 解释："lee"、"eet" 和 "ode" 都包含 2 个元音字母。 示例 4：
     *
     * <p>输入：s = "rhythms", k = 4 输出：0 解释：字符串 s 中不含任何元音字母。 示例 5：
     *
     * <p>输入：s = "tryhard", k = 4 输出：1
     *
     * <p>提示：
     *
     * <p>1 <= s.length <= 10^5 s 由小写英文字母组成 1 <= k <= s.length
     *
     * @param s
     * @param k
     * @return
     */
    public int maxVowels(String s, int k) {
        int max = 0;
        for (int i = 0; i < k; i++) {
            char c = s.charAt(i);
            if (vowel(c)) {
                max++;
            }
        }
        int num = max;
        for (int i = k; i < s.length(); i++) {
            if (vowel(s.charAt(i))) {
                num++;
            }
            if (vowel(s.charAt(i - k))) {
                num--;
            }
            max = Math.max(max, num);
        }
        return max;
    }

    @Test
    public void characterReplacement() {
        String s = "AABABBA";
        int k = 1;
        logResult(characterReplacement(s, k));
    }

    /**
     * 424. 替换后的最长重复字符 给你一个仅由大写英文字母组成的字符串，你可以将任意位置上的字符替换成另外的字符， 总共可最多替换 k
     * 次。在执行上述操作后，找到包含重复字母的最长子串的长度。
     *
     * <p>注意: 字符串长度 和 k 不会超过 104。
     *
     * <p>示例 1:
     *
     * <p>输入: s = "ABAB", k = 2
     *
     * <p>输出: 4
     *
     * <p>解释: 用两个'A'替换为两个'B',反之亦然。 示例 2:
     *
     * <p>输入: s = "AABABBA", k = 1
     *
     * <p>输出: 4
     *
     * <p>解释: 将中间的一个'A'替换为'B',字符串变为 "AABBBBA"。 子串 "BBBB" 有最长重复字母, 答案为 4。
     */
    public int characterReplacement(String s, int k) {
        int[] letters = new int[26];
        int max = 0;
        int left = 0;

        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            letters[c - 'A']++;
            max = Math.max(max, letters[c - 'A']);
            if (right - left + 1 > max + k) {
                letters[s.charAt(left) - 'A']--;
                left++;
            }
        }
        return s.length() - left;
    }

    @Test
    public void reverseWords3() {
        String s = "  hello world!  ";
        logResult(reverseWords3(s));
    }

    /**
     * 151. 翻转字符串里的单词 给定一个字符串，逐个翻转字符串中的每个单词。
     *
     * <p>示例 1：
     *
     * <p>输入: "the sky is blue" 输出: "blue is sky the" 示例 2：
     *
     * <p>输入: " hello world! " 输出: "world! hello" 解释: 输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。 示例 3：
     *
     * <p>输入: "a good example" 输出: "example good a" 解释: 如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。
     *
     * <p>说明：
     *
     * <p>无空格字符构成一个单词。 输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。 如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。
     *
     * @param s
     * @return
     */
    public String reverseWords3(String s) {
        String[] words = s.split(" ");
        logResult(words);
        StringBuilder sb = new StringBuilder();
        for (int i = words.length - 1; i >= 0; i--) {
            if (words[i].length() == 0) {
                continue;
            }
            if (sb.length() > 0) {
                sb.append(" ");
            }
            sb.append(words[i]);
        }

        return sb.toString();
    }

    @Test
    public void reverseWords4() {
        String s = "Let's take LeetCode contest";
        logResult(reverseWords4(s));
    }

    /**
     * 557. 反转字符串中的单词 III 给定一个字符串，你需要反转字符串中每个单词的字符顺序，同时仍保留空格和单词的初始顺序 示例 1: 输入: "Let's take LeetCode
     * contest" 输出: "s'teL ekat edoCteeL tsetnoc" 注意：在字符串中，每个单词由单个空格分隔，并且字符串中不会有任何额外的空格。
     *
     * @param s
     * @return
     */
    public String reverseWords4(String s) {
        int left = 0;
        char[] words = s.toCharArray();
        for (int i = 0; i <= words.length; i++) {
            if (i == words.length || words[i] == ' ') {
                reverseWords4(words, left, i - 1);
                left = i + 1;
            }
        }

        return new String(words);
    }

    private void reverseWords4(char[] words, int left, int right) {
        while (left < right) {
            char tmp = words[left];
            words[left] = words[right];
            words[right] = tmp;
            left++;
            right--;
        }
    }

    /**
     * 面试题 05.02. 二进制数转字符串
     *
     * <p>二进制数转字符串。给定一个介于0和1之间的实数（如0.72），类型为double，打印它的二进制表达式。如果该数字不在0和1之间，
     * 或者无法精确地用32位以内的二进制表示，则打印“ERROR”。
     *
     * <p>示例1:
     *
     * <p>输入：0.625 输出："0.101" 示例2:
     *
     * <p>输入：0.1 输出："ERROR" 提示：0.1无法被二进制准确表示 提示：
     *
     * <p>32位包括输出中的"0."这两位。
     *
     * @param num
     * @return
     */
    public String printBin(double num) {
        if (num < 0.0 || num > 1.0) {
            return "ERROR";
        }
        // 乘二取整法
        StringBuilder sb = new StringBuilder("0.");
        int bit = 30;
        while (num > 0.0 && bit > 0) {
            num *= 2;
            if (num >= 1) {
                sb.append(1);
                num -= 1;
            } else {
                sb.append(0);
            }
            bit--;
        }

        return num != 0.0 ? "ERROR" : sb.toString();
    }

    @Test
    public void findClosest() {
        String[] words = {"I", "am", "a", "student", "from", "a", "university", "in", "a", "city"};
        String word1 = "am", word2 = "student";
        logResult(findClosest(words, word1, word2));
    }
    /**
     * 面试题 17.11. 单词距离
     *
     * <p>有个内含单词的超大文本文件，给定任意两个单词，找出在这个文件中这两个单词的最短距离(相隔单词数)。
     *
     * <p>如果寻找过程在这个文件中会重复多次， 而每次寻找的单词不同，你能对此优化吗?
     *
     * <p>示例：
     *
     * <p>输入：words = ["I","am","a","student","from","a","university","in","a","city"], word1 = "a",
     * word2 = "student" 输出：1 提示：
     *
     * <p>words.length <= 100000
     *
     * @param words
     * @param word1
     * @param word2
     * @return
     */
    public int findClosest(String[] words, String word1, String word2) {
        int min = words.length;
        int index1 = -1, index2 = -1;
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            if (Objects.equals(word, word1)) {
                index1 = i;
                if (index2 > -1) {
                    min = Math.min(min, index1 - index2);
                }
            }
            if (Objects.equals(word, word2)) {
                index2 = i;
                if (index1 > -1) {
                    min = Math.min(min, index2 - index1);
                }
            }
            if (min == 1) {
                break;
            }
        }
        return min;
    }

    @Test
    public void countEval() {
        String s = "1^0|0|1";
        int result = 0;
        logResult(countEval(s, result));
    }

    /**
     * 面试题 08.14. 布尔运算
     *
     * <p>给定一个布尔表达式和一个期望的布尔结果 result，布尔表达式由 0 (false)、1 (true)、& (AND)、 | (OR) 和 ^ (XOR)
     * 符号组成。实现一个函数，算出有几种可使该表达式得出 result 值的括号方法。
     *
     * <p>示例 1:
     *
     * <p>输入: s = "1^0|0|1", result = 0
     *
     * <p>输出: 2 解释: 两种可能的括号方法是 1^(0|(0|1)) 1^((0|0)|1) 示例 2:
     *
     * <p>输入: s = "0&0&0&1^1|0", result = 1
     *
     * <p>输出: 10 提示：
     *
     * <p>运算符的数量不超过 19 个
     *
     * @param s
     * @param result
     * @return
     */
    public int countEval(String s, int result) {
        int n = s.length() >> 1;

        char[] opts = new char[n];
        int[] nums = new int[n + 1];

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            int index = i >> 1;
            if ((i & 1) == 0) {
                nums[index] = c - '0';
            } else {
                opts[index] = c;
            }
        }
        log.debug("nums:{}", nums);
        log.debug("opts:{}", opts);
        int[][][] dp = new int[n + 1][n + 1][2];
        for (int i = 0; i <= n; i++) {
            dp[i][i][nums[i] & 1] = 1;
        }
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i + 1; j <= n; j++) {
                // 计算 i ~ j 的结果
                // 操作符
                for (int k = i; k < j; k++) {
                    switch (opts[k]) {
                        case '&':
                            // 两个都为1
                            dp[i][j][1] += dp[i][k][1] * dp[k + 1][j][1];
                            // 只要有1个为0 就是0
                            dp[i][j][0] += dp[i][k][1] * dp[k + 1][j][0];
                            dp[i][j][0] += dp[i][k][0] * dp[k + 1][j][1];
                            dp[i][j][0] += dp[i][k][0] * dp[k + 1][j][0];
                            break;
                        case '|':
                            // 只要有1个为1 就是1
                            dp[i][j][1] += dp[i][k][1] * dp[k + 1][j][1];
                            dp[i][j][1] += dp[i][k][1] * dp[k + 1][j][0];
                            dp[i][j][1] += dp[i][k][0] * dp[k + 1][j][1];
                            // 两个都为0
                            dp[i][j][0] += dp[i][k][0] * dp[k + 1][j][0];
                            break;
                        case '^':
                            // 相同为0
                            dp[i][j][0] += dp[i][k][1] * dp[k + 1][j][1];
                            dp[i][j][0] += dp[i][k][0] * dp[k + 1][j][0];
                            // 不同为1
                            dp[i][j][1] += dp[i][k][1] * dp[k + 1][j][0];
                            dp[i][j][1] += dp[i][k][0] * dp[k + 1][j][1];
                            break;
                    }
                }
            }
        }
        logResult(dp);
        return dp[0][n][result];
    }

    @Test
    public void patternMatching() {
        String pattern = "baabab", value = "eimmieimmieeimmiee";
        logResult(patternMatching(pattern, value));
    }

    /**
     * 面试题 16.18. 模式匹配
     *
     * <p>你有两个字符串，即pattern和value。pattern字符串由字母"a"和"b"组成，用于描述字符串中的模式。
     * 例如，字符串"catcatgocatgo"匹配模式"aabab"（其中"cat"是"a"，"go"是"b"），该字符串也匹配像"a"、"ab"和"b"这样的模式。
     * 但需注意"a"和"b"不能同时表示相同的字符串。编写一个方法判断value字符串是否匹配pattern字符串。
     *
     * <p>示例 1：
     *
     * <p>输入： pattern = "abba", value = "dogcatcatdog" 输出： true 示例 2：
     *
     * <p>输入： pattern = "abba", value = "dogcatcatfish" 输出： false 示例 3：
     *
     * <p>输入： pattern = "aaaa", value = "dogcatcatdog" 输出： false 示例 4：
     *
     * <p>输入： pattern = "abba", value = "dogdogdogdog" 输出： true 解释： "a"="dogdog",b=""，反之也符合规则 提示：
     *
     * <p>0 <= len(pattern) <= 1000 0 <= len(value) <= 1000 你可以假设pattern只包含字母"a"和"b"，value仅包含小写字母。
     *
     * @param pattern
     * @param value
     * @return
     */
    public boolean patternMatching(String pattern, String value) {
        if (Objects.isNull(pattern) || pattern.length() == 0) {
            return value.length() == 0;
        }
        int countA = 0, countB = 0;
        for (char c : pattern.toCharArray()) {
            if (c == 'a') {
                countA++;
            }
            if (c == 'b') {
                countB++;
            }
        }
        if (countA == 0) {
            return singleCount(countB, value);
        }
        if (countB == 0) {
            return singleCount(countA, value);
        }
        // 可以是 a , b 映射为""
        if (singleCount(countA, value)) {
            return true;
        }
        if (singleCount(countB, value)) {
            return true;
        }

        int len = value.length();
        // countA * lenA + countB * lenB = len;

        //  countA,countB都不为空; 枚举a, b匹配的长度，使得countA * lenA + countB * lenB = len
        for (int lenA = 1; lenA * countA <= len - countB; lenA++) {
            if ((len - lenA * countA) % countB != 0) {
                continue;
            }
            int lenB = (len - lenA * countA) / countB;
            if (checkPatternMatching(pattern, value, lenA, lenB)) {
                return true;
            }
        }

        return false;
    }

    private boolean checkPatternMatching(String pattern, String value, int lenA, int lenB) {
        String a = "", b = "";
        for (int i = 0, j = 0; i < pattern.length(); i++) { // i, j指针都是恰当长度的
            if (pattern.charAt(i) == 'a') {
                if (a.length() == 0) {
                    a = value.substring(j, j + lenA);
                } else if (!Objects.equals(a, value.substring(j, j + lenA))) {
                    return false;
                }
                j += lenA;
            } else if (pattern.charAt(i) == 'b') {
                if (b.length() == 0) {
                    b = value.substring(j, j + lenB);
                } else if (!Objects.equals(b, value.substring(j, j + lenB))) {
                    return false;
                }
                j += lenB;
            }
        }
        return true;
    }

    private boolean singleCount(int count, String value) {
        if (value.length() % count != 0) {
            return false;
        }
        int singleLen = value.length() / count;

        for (int i = 0; i < singleLen; i++) {
            char c = value.charAt(i);
            for (int j = 1; j < count; j++) {
                char c1 = value.charAt(j * singleLen + i);
                if (c1 != c) {
                    return false;
                }
            }
        }
        return true;
    }

    @Test
    public void multiSearch() {
        String big = "mississippi";
        String[] smalls = {"is", "ppi", "hi", "sis", "i", "ssippi"};
        logResult(multiSearch(big, smalls));
    }

    /**
     * 面试题 17.17. 多次搜索
     *
     * <p>给定一个较长字符串big和一个包含较短字符串的数组smalls，设计一个方法，根据smalls中的每一个较短字符串，对big进行搜索。输出smalls中的字符串在big里出现的所有位置positions，其中positions[i]为smalls[i]出现的所有位置。
     *
     * <p>示例：
     *
     * <p>输入： big = "mississippi" smalls = ["is","ppi","hi","sis","i","ssippi"] 输出：
     * [[1,4],[8],[],[3],[1,4,7,10],[5]] 提示：
     *
     * <p>0 <= len(big) <= 1000 0 <= len(smalls[i]) <= 1000 smalls的总字符数不会超过 100000。
     * 你可以认为smalls中没有重复字符串。 所有出现的字符均为英文小写字母。
     *
     * @param big
     * @param smalls
     * @return
     */
    public int[][] multiSearch(String big, String[] smalls) {
        Trie2 trie = new Trie2();
        for (int i = 0; i < smalls.length; i++) {
            trie.insert(smalls[i], i);
        }
        List<Integer>[] lists = new List[smalls.length];
        for (int i = 0; i < smalls.length; i++) {
            lists[i] = new ArrayList<>();
        }
        int[][] result = new int[smalls.length][];
        for (int i = 0; i < big.length(); i++) {
            List<Integer> ids = trie.multiSearch(big.substring(i));
            for (int index : ids) {
                lists[index].add(i);
            }
        }
        for (int i = 0; i < smalls.length; i++) {
            List<Integer> list = lists[i];
            int[] arr = new int[list.size()];
            for (int j = 0; j < list.size(); j++) {
                arr[j] = list.get(j);
            }

            result[i] = arr;
        }

        return result;
    }

    @Test
    public void longestWord() {
        String[] words = {"cat", "banana", "dog", "nana", "walk", "walker", "dogwalker"};
        logResult(longestWord(words));
    }

    /**
     * 面试题 17.15. 最长单词
     *
     * <p>给定一组单词words，编写一个程序，找出其中的最长单词，且该单词由这组单词中的其他单词组合而成。若有多个长度相同的结果，返回其中字典序最小的一项，若没有符合要求的单词则返回空字符串。
     *
     * <p>示例：
     *
     * <p>输入： ["cat","banana","dog","nana","walk","walker","dogwalker"] 输出： "dogwalker" 解释：
     * "dogwalker"可由"dog"和"walker"组成。 提示：
     *
     * <p>0 <= len(words) <= 100 1 <= len(words[i]) <= 100
     *
     * @param words
     * @return
     */
    public String longestWord(String[] words) {
        Arrays.sort(
                words,
                (a, b) -> a.length() == b.length() ? a.compareTo(b) : a.length() - b.length());
        HashSet<String> set = new HashSet<>(Arrays.asList(words));
        String result = "";
        for (String word : words) {
            set.remove(word);
            if (isMergeWord(word, set) && word.length() > result.length()) {
                result = word;
            }
            set.add(word);
        }
        return result;
    }

    private boolean isMergeWord(String word, Set<String> set) {
        if (word.length() == 0) {
            return true;
        }
        for (int i = 0; i < word.length(); i++) {
            if (set.contains(word.substring(0, i + 1)) && isMergeWord(word.substring(i + 1), set)) {
                return true;
            }
        }
        return false;
    }

    private boolean isMergeWord(String word, int start, Set<String> set) {
        if (start == word.length()) {
            return true;
        }
        boolean flag = false;
        for (String str : set) {
            if (str.charAt(0) != word.charAt(start)) {
                continue;
            }
            int index = start;
            int i = 0;
            for (; i < str.length() && index < word.length(); i++) {
                if (word.charAt(index) != str.charAt(i)) {
                    break;
                }
                index++;
            }
            if (i != str.length()) {
                continue;
            }
            if (isMergeWord(word, index, set)) {
                flag = true;
                break;
            }
        }
        return flag;
    }

    @Test
    public void respace() {
        String[] dictionary = {"looked", "just", "like", "her", "brother"};
        String sentence = "jesslookedjustliketimherbrother";
        logResult(respace(dictionary, sentence));
    }
    /**
     * 面试题 17.13. 恢复空格
     *
     * <p>哦，不！你不小心把一个长篇文章中的空格、标点都删掉了，并且大写也弄成了小写。像句子"I reset the computer. It still didn’t
     * boot!"已经变成了"iresetthecomputeritstilldidntboot"。在处理标点符号和大小写之前，你得先把它断成词语。当然了，你有一本厚厚的词典dictionary，不过，有些词没在词典里。假设文章用sentence表示，设计一个算法，把文章断开，要求未识别的字符最少，返回未识别的字符数。
     *
     * <p>注意：本题相对原题稍作改动，只需返回未识别的字符数
     *
     * <p>示例：
     *
     * <p>输入： dictionary = ["looked","just","like","her","brother"] sentence =
     * "jesslookedjustliketimherbrother" 输出： 7 解释： 断句后为"jess looked just like tim her
     * brother"，共7个未识别字符。 提示：
     *
     * <p>0 <= len(sentence) <= 1000 dictionary中总字符数不超过 150000。 你可以认为dictionary和sentence中只包含小写字母。
     *
     * @param dictionary
     * @param sentence
     * @return
     */
    public int respace(String[] dictionary, String sentence) {
        int len = sentence.length();

        Map<Character, Set<String>> map = new HashMap<>();
        for (String word : dictionary) {
            Set<String> set =
                    map.computeIfAbsent(word.charAt(word.length() - 1), k -> new HashSet<>());
            set.add(word);
        }
        int[] dp = new int[len + 1];
        for (int i = 0; i < len; i++) {
            dp[i + 1] = dp[i] + 1;
            char c = sentence.charAt(i);
            if (!map.containsKey(c)) {
                continue;
            }
            for (String word : map.get(c)) {
                int index = i + 1 - word.length();
                if (index < 0) {
                    continue;
                }
                if (Objects.equals(word, sentence.substring(index, i + 1))) {
                    dp[i + 1] = Math.min(dp[i + 1], dp[index]);
                }
            }
        }

        return dp[len];
    }

    @Test
    public void minMutation() {
        String start = "AACCGGTT", end = "AAACGGTA";
        String[] bank = {"AACCGGTA", "AACCGCTA", "AAACGGTA"};
        logResult(minMutation(start, end, bank));
    }
    /**
     * 433. 最小基因变化
     *
     * <p>一条基因序列由一个带有8个字符的字符串表示，其中每个字符都属于 "A", "C", "G", "T"中的任意一个。
     *
     * <p>假设我们要调查一个基因序列的变化。一次基因变化意味着这个基因序列中的一个字符发生了变化。
     *
     * <p>例如，基因序列由"AACCGGTT" 变化至 "AACCGGTA" 即发生了一次基因变化。
     *
     * <p>与此同时，每一次基因变化的结果，都需要是一个合法的基因串，即该结果属于一个基因库。
     *
     * <p>现在给定3个参数 — start, end,
     * bank，分别代表起始基因序列，目标基因序列及基因库，请找出能够使起始基因序列变化为目标基因序列所需的最少变化次数。如果无法实现目标变化，请返回 -1。
     *
     * <p>注意:
     *
     * <p>起始基因序列默认是合法的，但是它并不一定会出现在基因库中。 所有的目标基因序列必须是合法的。 假定起始基因序列与目标基因序列是不一样的。 示例 1:
     *
     * <p>start: "AACCGGTT" end: "AACCGGTA" bank: ["AACCGGTA"]
     *
     * <p>返回值: 1 示例 2:
     *
     * <p>start: "AACCGGTT" end: "AAACGGTA" bank: ["AACCGGTA", "AACCGCTA", "AAACGGTA"]
     *
     * <p>返回值: 2 示例 3:
     *
     * <p>start: "AAAAACCC" end: "AACCCCCC" bank: ["AAAACCCC", "AAACCCCC", "AACCCCCC"]
     *
     * <p>返回值: 3
     *
     * @param start
     * @param end
     * @param bank
     * @return
     */
    public int minMutation(String start, String end, String[] bank) {
        Set<String> bankSet = new HashSet<>(Arrays.asList(bank));
        if (!bankSet.contains(end)) {
            return -1;
        }
        Set<String> visited = new HashSet<>();

        return minMutation(start, end, bankSet, visited);
    }

    static char[] factors = {'A', 'C', 'G', 'T'};

    private int minMutation(String start, String end, Set<String> bankSet, Set<String> visited) {

        if (Objects.equals(start, end)) {
            return 0;
        }
        int min = Integer.MAX_VALUE;
        char[] chars = start.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            for (int j = 0; j < 4; j++) {
                if (c == factors[j]) {
                    continue;
                }
                chars[i] = factors[j];
                String str = new String(chars);
                if (!bankSet.contains(str)) {
                    continue;
                }
                if (visited.contains(str)) {
                    continue;
                }
                visited.add(str);
                int count = minMutation(str, end, bankSet, visited);
                if (count != -1) {
                    min = Math.min(count + 1, min);
                }
                visited.remove(str);
            }
            chars[i] = c;
        }

        return min == Integer.MAX_VALUE ? -1 : min;
    }

    @Test
    public void addBinary2() {
        String a = "11";
        String b = "1111";
        String result = addBinary(a, b);
        logResult(result);
    }

    /**
     * 67. 二进制求和
     *
     * <p>给你两个二进制字符串，返回它们的和（用二进制表示）。
     *
     * <p>输入为 非空 字符串且只包含数字 1 和 0。
     *
     * <p>示例 1:
     *
     * <p>输入: a = "11", b = "1" 输出: "100" 示例 2:
     *
     * <p>输入: a = "1010", b = "1011" 输出: "10101"
     *
     * <p>提示：
     *
     * <p>每个字符串仅由字符 '0' 或 '1' 组成。 1 <= a.length, b.length <= 10^4 字符串如果不是 "0" ，就都不含前导零。
     *
     * @param a
     * @param b
     * @return
     */
    public String addBinary2(String a, String b) {

        char[] chars1 = a.toCharArray(), chars2 = b.toCharArray();
        int len = Math.max(chars1.length, chars2.length);
        char[] chars = new char[len];
        int last = 0;
        for (int i = 0; i < len; i++) {
            int num1 = 0, num2 = 0;
            if (i < a.length()) {
                num1 = a.charAt(a.length() - 1 - i) - '0';
            }
            if (i < b.length()) {
                num2 = b.charAt(b.length() - 1 - i) - '0';
            }
            int val = num1 + num2 + last;
            if (val >= 2) {
                val -= 2;
                last = 1;
            } else {
                last = 0;
            }
            if (val == 1) {
                chars[len - 1 - i] = '1';
            } else {
                chars[len - 1 - i] = '0';
            }
        }
        StringBuilder sb = new StringBuilder();
        if (last != 0) {
            sb.append(last);
        }
        sb.append(chars);
        return sb.toString();
    }

    @Test
    public void validIPAddress() {
        String IP = "1e1.4.5.6";
        logResult(validIPAddress(IP));
    }

    /**
     * 468. 验证IP地址 编写一个函数来验证输入的字符串是否是有效的 IPv4 或 IPv6 地址。
     *
     * <p>IPv4 地址由十进制数和点来表示，每个地址包含4个十进制数，其范围为 0 - 255， 用(".")分割。比如，172.16.254.1；
     *
     * <p>同时，IPv4 地址内的数不会以 0 开头。比如，地址 172.16.254.01 是不合法的。
     *
     * <p>IPv6 地址由8组16进制的数字来表示，每组表示 16 比特。这些组数字通过 (":")分割。比如,
     * 2001:0db8:85a3:0000:0000:8a2e:0370:7334 是一个有效的地址。而且，我们可以加入一些以 0 开头的数字，字母可以使用大写，也可以是小写。所以，
     * 2001:db8:85a3:0:0:8A2E:0370:7334 也是一个有效的 IPv6 address地址 (即，忽略 0 开头，忽略大小写)。
     *
     * <p>然而，我们不能因为某个组的值为 0，而使用一个空的组，以至于出现 (::) 的情况。 比如， 2001:0db8:85a3::8A2E:0370:7334 是无效的 IPv6
     * 地址。
     *
     * <p>同时，在 IPv6 地址中，多余的 0 也是不被允许的。比如， 02001:0db8:85a3:0000:0000:8a2e:0370:7334 是无效的。
     *
     * <p>说明: 你可以认为给定的字符串里没有空格或者其他特殊字符。
     *
     * <p>示例 1:
     *
     * <p>输入: "172.16.254.1"
     *
     * <p>输出: "IPv4"
     *
     * <p>解释: 这是一个有效的 IPv4 地址, 所以返回 "IPv4"。 示例 2:
     *
     * <p>输入: "2001:0db8:85a3:0:0:8A2E:0370:7334"
     *
     * <p>输出: "IPv6"
     *
     * <p>解释: 这是一个有效的 IPv6 地址, 所以返回 "IPv6"。 示例 3:
     *
     * <p>输入: "256.256.256.256"
     *
     * <p>输出: "Neither"
     *
     * <p>解释: 这个地址既不是 IPv4 也不是 IPv6 地址。
     *
     * @param IP
     * @return
     */
    public String validIPAddress(String IP) {

        if (IP.contains(".")) {
            return validIPv4(IP);
        } else if (IP.contains(":")) {
            return validIPv6(IP);
        }
        return IP_ADDR_OUT;
    }

    String IP_ADDR_OUT = "Neither";

    private String validIPv4(String IP) {
        String[] ips = IP.split("\\.", -1);
        if (ips.length != 4) {
            return IP_ADDR_OUT;
        }
        for (String ip : ips) {
            if (ip.length() > 3 || ip.length() <= 0) {
                return IP_ADDR_OUT;
            }
            if (ip.length() > 1 && ip.subSequence(0, 1).equals("0")) {
                return IP_ADDR_OUT;
            }
            for (int i = 0; i < ip.length(); i++) {
                if (!Character.isDigit(ip.charAt(i))) {
                    return IP_ADDR_OUT;
                }
            }

            int num = Integer.parseInt(ip);
            if (num != 0 && ip.startsWith("0")) {
                return IP_ADDR_OUT;
            }
            if (num < 0 || num > 255) {
                return IP_ADDR_OUT;
            }
        }

        return "IPv4";
    }

    private String validIPv6(String IP) {
        String[] ips = IP.split(":", -1);
        if (ips.length != 8) {
            return IP_ADDR_OUT;
        }
        for (String ip : ips) {
            if (!checkIPv6(ip)) {
                return IP_ADDR_OUT;
            }
        }
        return "IPv6";
    }

    private boolean checkIPv6(String ip) {
        if (ip.length() > 4 || ip.length() == 0) {
            return false;
        }
        for (char c : ip.toCharArray()) {
            if (c >= '0' && c <= '9' || c >= 'a' && c <= 'f' || c >= 'A' && c <= 'F') {

            } else {
                return false;
            }
        }

        return true;
    }

    @Test
    public void magicalString() {
        int n = 6;
        logResult(magicalString(n));
    }

    /**
     * 481. 神奇字符串
     *
     * <p>神奇的字符串 S 只包含 '1' 和 '2'，并遵守以下规则：
     *
     * <p>字符串 S 是神奇的，因为串联字符 '1' 和 '2' 的连续出现次数会生成字符串 S 本身。
     *
     * <p>字符串 S 的前几个元素如下：S = “1221121221221121122 ......”
     *
     * <p>如果我们将 S 中连续的 1 和 2 进行分组，它将变成：
     *
     * <p>1 22 11 2 1 22 1 22 11 2 11 22 ......
     *
     * <p>并且每个组中 '1' 或 '2' 的出现次数分别是：
     *
     * <p>1 2 2 1 1 2 1 2 2 1 2 2 ......
     *
     * <p>你可以看到上面的出现次数就是 S 本身。
     *
     * <p>给定一个整数 N 作为输入，返回神奇字符串 S 中前 N 个数字中的 '1' 的数目。
     *
     * <p>注意：N 不会超过 100,000。
     *
     * <p>示例：
     *
     * <p>输入：6 输出：3 解释：神奇字符串 S 的前 6 个元素是 “12211”，它包含三个 1，因此返回 3。
     *
     * @param n
     * @return
     */
    public int magicalString(int n) {
        if (n == 0) {
            return 0;
        }
        if (n <= 3) {
            return 1;
        }

        boolean[] chars = new boolean[n + 2];
        chars[0] = false;
        chars[1] = true;
        chars[2] = true;
        int end = 2;
        for (int i = 2; i < n; i++) {
            if (end >= n) {
                break;
            }
            boolean b = chars[i];
            boolean endNum = chars[end];
            if (b) {
                chars[++end] = !endNum;
                chars[++end] = !endNum;
            } else {
                chars[++end] = !endNum;
            }
        }
        int count = 0;
        for (int i = 0; i < n; i++) {
            if (!chars[i]) {
                count++;
            }
        }

        return count;
    }

    @Test
    public void isPathCrossing() {
        String path = "NNSWWEWSSESSWENNW";
        logResult(isPathCrossing(path));
    }

    /**
     * 5448. 判断路径是否相交
     *
     * <p>给你一个字符串 path，其中 path[i] 的值可以是 'N'、'S'、'E' 或者 'W'，分别表示向北、向南、向东、向西移动一个单位。
     *
     * <p>机器人从二维平面上的原点 (0, 0) 处开始出发，按 path 所指示的路径行走。
     *
     * <p>如果路径在任何位置上出现相交的情况，也就是走到之前已经走过的位置，请返回 True ；否则，返回 False 。
     *
     * <p>示例 1：
     *
     * <p>输入：path = "NES" 输出：false 解释：该路径没有在任何位置相交。 示例 2：
     *
     * <p>输入：path = "NESWW" 输出：true 解释：该路径经过原点两次。
     *
     * <p>提示：
     *
     * <p>1 <= path.length <= 10^4 path 仅由 {'N', 'S', 'E', 'W} 中的字符组成
     *
     * @param path
     * @return
     */
    public boolean isPathCrossing(String path) {
        int x = 0, y = 0;
        Set<String> set = new HashSet<>();
        set.add(x + "|" + y);
        for (char c : path.toCharArray()) {
            int[] move = getMove(c);
            x += move[0];
            y += move[1];
            log.debug("x:{} y:{}", x, y);
            if (set.contains(x + "|" + y)) {
                return true;
            }
            set.add(x + "|" + y);
        }

        return false;
    }

    private int[] getMove(char c) {
        switch (c) {
            case 'N':
                return new int[] {0, 1};
            case 'S':
                return new int[] {0, -1};
            case 'E':
                return new int[] {1, 0};
            case 'W':
                return new int[] {-1, 0};
        }
        return null;
    }

    /**
     * 521. 最长特殊序列 I
     *
     * <p>给你两个字符串，请你从这两个字符串中找出最长的特殊序列。
     *
     * <p>「最长特殊序列」定义如下：该序列为某字符串独有的最长子序列（即不能是其他字符串的子序列）。
     *
     * <p>子序列 可以通过删去字符串中的某些字符实现，但不能改变剩余字符的相对顺序。空序列为所有字符串的子序列，任何字符串为其自身的子序列。
     *
     * <p>输入为两个字符串，输出最长特殊序列的长度。如果不存在，则返回 -1。
     *
     * <p>示例 1：
     *
     * <p>输入: "aba", "cdc" 输出: 3 解释: 最长特殊序列可为 "aba" (或 "cdc")，两者均为自身的子序列且不是对方的子序列。 示例 2：
     *
     * <p>输入：a = "aaa", b = "bbb" 输出：3 示例 3：
     *
     * <p>输入：a = "aaa", b = "aaa" 输出：-1
     *
     * <p>提示：
     *
     * <p>两个字符串长度均处于区间 [1 - 100] 。 字符串中的字符仅含有 'a'~'z'
     *
     * @param a
     * @param b
     * @return
     */
    public int findLUSlength(String a, String b) {
        if (a.equals(b)) return -1;
        return Math.max(a.length(), b.length());
    }

    /**
     * 522. 最长特殊序列 II
     *
     * <p>给定字符串列表，你需要从它们中找出最长的特殊序列。最长特殊序列定义如下：该序列为某字符串独有的最长子序列（即不能是其他字符串的子序列）。
     *
     * <p>子序列可以通过删去字符串中的某些字符实现，但不能改变剩余字符的相对顺序。空序列为所有字符串的子序列，任何字符串为其自身的子序列。
     *
     * <p>输入将是一个字符串列表，输出是最长特殊序列的长度。如果最长特殊序列不存在，返回 -1 。
     *
     * <p>示例：
     *
     * <p>输入: "aba", "cdc", "eae" 输出: 3
     *
     * <p>提示：
     *
     * <p>所有给定的字符串长度不会超过 10 。 给定字符串列表的长度将在 [2, 50 ] 之间。
     *
     * @param strs
     * @return
     */
    public int findLUSlength(String[] strs) {
        // 先按长度排序
        Arrays.sort(strs, (a, b) -> b.length() - a.length());

        for (int i = 0; i < strs.length; i++) {
            boolean flag = true;
            // 判断是否是其他字符串的子串
            for (int j = 0; j < strs.length; j++) {
                if (i == j) {
                    continue;
                }
                if (isSubsequence2(strs[i], strs[j])) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                return strs[i].length();
            }
        }

        return -1;
    }

    /**
     * 判断 x 是否为 y 的子序列
     *
     * @param x
     * @param y
     * @return
     */
    public boolean isSubsequence2(String x, String y) {
        if (x.length() > y.length()) {
            return false;
        }
        if (Objects.equals(x, y)) {
            return true;
        }
        int index = 0;
        for (int i = 0; i < y.length() && index < x.length(); i++) {
            if (x.charAt(index) == y.charAt(i)) {
                index++;
            }
        }
        return index == x.length();
    }

    /**
     * 524. 通过删除字母匹配到字典里最长单词
     *
     * <p>给定一个字符串和一个字符串字典，找到字典里面最长的字符串， 该字符串可以通过删除给定字符串的某些字符来得到。
     * 如果答案不止一个，返回长度最长且字典顺序最小的字符串。如果答案不存在，则返回空字符串。
     *
     * <p>示例 1:
     *
     * <p>输入: s = "abpcplea", d = ["ale","apple","monkey","plea"]
     *
     * <p>输出: "apple" 示例 2:
     *
     * <p>输入: s = "abpcplea", d = ["a","b","c"]
     *
     * <p>输出: "a" 说明:
     *
     * <p>所有输入的字符串只包含小写字母。 字典的大小不会超过 1000。 所有输入的字符串长度不会超过 1000。
     *
     * @param s
     * @param d
     * @return
     */
    public String findLongestWord(String s, List<String> d) {

        String result = "";
        for (String str : d) {
            if (result.length() > str.length()
                    || (result.length() == str.length() && result.compareTo(str) < 0)) {
                continue;
            }

            if (isSubsequence2(str, s)) {
                result = str;
            }
        }
        return result;
    }

    @Test
    public void isMatch() {
        String s = "acdcb", p = "a*c?b";
        boolean result = isMatch(s, p);
        logResult(result);
    }

    /**
     * 44. 通配符匹配
     *
     * <p>给定一个字符串 (s) 和一个字符模式 (p) ，实现一个支持 '?' 和 '*' 的通配符匹配。
     *
     * <p>'?' 可以匹配任何单个字符。 '*' 可以匹配任意字符串（包括空字符串）。 两个字符串完全匹配才算匹配成功。
     *
     * <p>说明:
     *
     * <p>s 可能为空，且只包含从 a-z 的小写字母。 p 可能为空，且只包含从 a-z 的小写字母，以及字符 ? 和 *。 示例 1:
     *
     * <p>输入: s = "aa" p = "a" 输出: false 解释: "a" 无法匹配 "aa" 整个字符串。 示例 2:
     *
     * <p>输入: s = "aa" p = "*" 输出: true 解释: '*' 可以匹配任意字符串。 示例 3:
     *
     * <p>输入: s = "cb" p = "?a" 输出: false 解释: '?' 可以匹配 'c', 但第二个 'a' 无法匹配 'b'。 示例 4:
     *
     * <p>输入: s = "adceb" p = "*a*b" 输出: true 解释: 第一个 '*' 可以匹配空字符串, 第二个 '*' 可以匹配字符串 "dce". 示例 5:
     *
     * <p>输入: s = "acdcb" p = "a*c?b" 输出: false
     *
     * @param s
     * @param p
     * @return
     */
    public boolean isMatch(String s, String p) {
        int sLen = s.length(), pLen = p.length();
        boolean[][] dp = new boolean[sLen + 1][pLen + 1];
        dp[0][0] = true;
        for (int j = 0; j < pLen; j++) {
            if (p.charAt(j) == '*') {
                dp[0][j + 1] = dp[0][j];
            }
        }
        for (int i = 0; i < sLen; i++) {
            for (int j = 0; j < pLen; j++) {
                char c1 = s.charAt(i), c2 = p.charAt(j);
                boolean same = c1 == c2 || c2 == '?';
                if (same) {
                    dp[i + 1][j + 1] = dp[i][j];
                    continue;
                }
                if (c2 == '*') {
                    dp[i + 1][j + 1] = dp[i + 1][j] || dp[i][j + 1];
                }
            }
        }

        return dp[sLen][pLen];
    }

    @Test
    public void replaceWords() {
        List<String> dict = Arrays.asList("a", "aa", "aaa", "aaaa");
        String sentence = "a aa a aaaa aaa aaa aaa aaaaaa bbb baba ababa";
        logResult(replaceWords(dict, sentence));
    }
    /**
     * 648. 单词替换
     *
     * <p>在英语中，我们有一个叫做 词根(root)的概念，它可以跟着其他一些词组成另一个较长的单词——我们称这个词为 继承词(successor)。例如，词根an，跟随着单词
     * other(其他)，可以形成新的单词 another(另一个)。
     *
     * <p>现在，给定一个由许多词根组成的词典和一个句子。你需要将句子中的所有继承词用词根替换掉。如果继承词有许多可以形成它的词根，则用最短的词根替换它。
     *
     * <p>你需要输出替换之后的句子。
     *
     * <p>示例：
     *
     * <p>输入：dict(词典) = ["cat", "bat", "rat"] sentence(句子) = "the cattle was rattled by the battery"
     * 输出："the cat was rat by the bat"
     *
     * <p>提示：
     *
     * <p>输入只包含小写字母。 1 <= dict.length <= 1000 1 <= dict[i].length <= 100 1 <= 句中词语数 <= 1000 1 <=
     * 句中词语长度 <= 1000
     *
     * @param dict
     * @param sentence
     * @return
     */
    public String replaceWords(List<String> dict, String sentence) {
        StringBuilder result = new StringBuilder();
        Trie trie = new Trie();

        for (String word : dict) {
            trie.insert(word);
        }
        for (String word : sentence.split(" ")) {
            if (result.length() > 0) {
                result.append(" ");
            }
            result.append(trie.getReplaceWord(word));
        }

        return result.toString();
    }

    /**
     * 5177. 转变日期格式
     *
     * <p>给你一个字符串 date ，它的格式为 Day Month Year ，其中：
     *
     * <p>Day 是集合 {"1st", "2nd", "3rd", "4th", ..., "30th", "31st"} 中的一个元素。 Month 是集合 {"Jan", "Feb",
     * "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"} 中的一个元素。 Year 的范围在
     * ​[1900, 2100] 之间。 请你将字符串转变为 YYYY-MM-DD 的格式，其中：
     *
     * <p>YYYY 表示 4 位的年份。 MM 表示 2 位的月份。 DD 表示 2 位的天数。
     *
     * <p>示例 1：
     *
     * <p>输入：date = "20th Oct 2052" 输出："2052-10-20" 示例 2：
     *
     * <p>输入：date = "6th Jun 1933" 输出："1933-06-06" 示例 3：
     *
     * <p>输入：date = "26th May 1960" 输出："1960-05-26"
     *
     * <p>提示：
     *
     * <p>给定日期保证是合法的，所以不需要处理异常输入。
     *
     * @param date
     * @return
     */
    public String reformatDate(String date) {
        String[] dates = date.split(" ");

        String year = dates[2];
        String day = dates[0].substring(0, dates[0].length() - 2);
        Map<String, String> monthMap = new HashMap<>();
        for (int i = 0; i < months.length; i++) {
            int m = i + 1;
            String month = "" + (m < 10 ? "0" + m : m);
            monthMap.put(months[i], month);
        }
        String result =
                year + "-" + monthMap.get(dates[1]) + "-" + (day.length() == 1 ? "0" + day : day);
        return result;
    }

    static String[] months = {
        "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"
    };

    @Test
    public void numSub() {
        String s = "000";
        logResult(numSub(s));
    }

    /**
     * 5461. 仅含 1 的子串数
     *
     * <p>给你一个二进制字符串 s（仅由 '0' 和 '1' 组成的字符串）。
     *
     * <p>返回所有字符都为 1 的子字符串的数目。
     *
     * <p>由于答案可能很大，请你将它对 10^9 + 7 取模后返回。
     *
     * <p>示例 1：
     *
     * <p>输入：s = "0110111" 输出：9 解释：共有 9 个子字符串仅由 '1' 组成 "1" -> 5 次 "11" -> 3 次 "111" -> 1 次 示例 2：
     *
     * <p>输入：s = "101" 输出：2 解释：子字符串 "1" 在 s 中共出现 2 次 示例 3：
     *
     * <p>输入：s = "111111" 输出：21 解释：每个子字符串都仅由 '1' 组成 示例 4：
     *
     * <p>输入：s = "000" 输出：0
     *
     * <p>提示：
     *
     * <p>s[i] == '0' 或 s[i] == '1' 1 <= s.length <= 10^5
     *
     * @param s
     * @return
     */
    public int numSub(String s) {
        int result = 0;
        // 动态规划
        int[] dp = new int[s.length() + 1];

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '1') {
                dp[i + 1] = (dp[i] + 1) % MOD;
                result += dp[i + 1];
                result %= MOD;
            }
        }

        return result;
    }

    int MOD = 1_000_000_007;
    /**
     * 1433. 检查一个字符串是否可以打破另一个字符串
     *
     * <p>给你两个字符串 s1 和 s2 ，它们长度相等，请你检查是否存在一个 s1 的排列可以打破 s2 的一个排列，或者是否存在一个 s2 的排列可以打破 s1 的一个排列。
     *
     * <p>字符串 x 可以打破字符串 y （两者长度都为 n ）需满足对于所有 i（在 0 到 n - 1 之间）都有 x[i] >= y[i]（字典序意义下的顺序）。
     *
     * <p>示例 1：
     *
     * <p>输入：s1 = "abc", s2 = "xya" 输出：true 解释："ayx" 是 s2="xya" 的一个排列，"abc" 是字符串 s1="abc" 的一个排列，且
     * "ayx" 可以打破 "abc" 。 示例 2：
     *
     * <p>输入：s1 = "abe", s2 = "acd" 输出：false 解释：s1="abe" 的所有排列包括："abe"，"aeb"，"bae"，"bea"，"eab" 和
     * "eba" ，s2="acd" 的所有排列包括："acd"，"adc"，"cad"，"cda"，"dac" 和 "dca"。然而没有任何 s1 的排列可以打破 s2 的排列。也没有 s2
     * 的排列能打破 s1 的排列。 示例 3：
     *
     * <p>输入：s1 = "leetcodee", s2 = "interview" 输出：true
     *
     * <p>提示：
     *
     * <p>s1.length == n s2.length == n 1 <= n <= 10^5 所有字符串都只包含小写英文字母。
     *
     * @param s1
     * @param s2
     * @return
     */
    public boolean checkIfCanBreak(String s1, String s2) {
        int[] letter = new int[26];
        for (char c : s1.toCharArray()) {
            letter[c - 'a']++;
        }
        for (char c : s2.toCharArray()) {
            letter[c - 'a']--;
        }
        int sum = 0;
        // sum > 0 说明存在
        boolean flag1 = true, flag2 = true;
        for (int i = 25; i >= 0; --i) {
            sum += letter[i];
            if (flag1 && sum > 0) flag1 = false;
            if (flag2 && sum < 0) flag2 = false;
            if (!flag1 && !flag2) return false;
        }
        return true;
    }

    /**
     * 1419. 数青蛙
     *
     * <p>给你一个字符串 croakOfFrogs，它表示不同青蛙发出的蛙鸣声（字符串 "croak" ）的组合。由于同一时间可以有多只青蛙呱呱作响，所以 croakOfFrogs
     * 中会混合多个 “croak” 。请你返回模拟字符串中所有蛙鸣所需不同青蛙的最少数目。
     *
     * <p>注意：要想发出蛙鸣 "croak"，青蛙必须 依序 输出 ‘c’, ’r’, ’o’, ’a’, ’k’ 这 5 个字母。如果没有输出全部五个字母，那么它就不会发出声音。
     *
     * <p>如果字符串 croakOfFrogs 不是由若干有效的 "croak" 字符混合而成，请返回 -1 。
     *
     * <p>示例 1：
     *
     * <p>输入：croakOfFrogs = "croakcroak" 输出：1 解释：一只青蛙 “呱呱” 两次 示例 2：
     *
     * <p>输入：croakOfFrogs = "crcoakroak" 输出：2 解释：最少需要两只青蛙，“呱呱” 声用黑体标注 第一只青蛙 "crcoakroak" 第二只青蛙
     * "crcoakroak" 示例 3：
     *
     * <p>输入：croakOfFrogs = "croakcrook" 输出：-1 解释：给出的字符串不是 "croak" 的有效组合。 示例 4：
     *
     * <p>输入：croakOfFrogs = "croakcroa" 输出：-1
     *
     * <p>提示：
     *
     * <p>1 <= croakOfFrogs.length <= 10^5 字符串中的字符只有 'c', 'r', 'o', 'a' 或者 'k'
     *
     * @param croakOfFrogs
     * @return
     */
    public int minNumberOfFrogs(String croakOfFrogs) {
        int c = 0, r = 0, o = 0, a = 0, k = 0;
        int result = 0;
        for (char ch : croakOfFrogs.toCharArray()) {
            switch (ch) {
                case 'c':
                    {
                        if (k > 0) {
                            k--;
                        } else {
                            result++;
                        }
                        c++;
                    }
                    break;
                case 'r':
                    {
                        c--;
                        r++;
                    }
                    break;
                case 'o':
                    {
                        r--;
                        o++;
                    }
                    break;
                case 'a':
                    {
                        o--;
                        a++;
                    }
                    break;
                case 'k':
                    {
                        a--;
                        k++;
                    }
                    break;
            }
            if (c < 0 || r < 0 || o < 0 || a < 0) {
                break;
            }
        }
        // 剩余的不为0
        if (c != 0 || r != 0 || o != 0 || a != 0) {
            return -1;
        }

        return result;
    }

    @Test
    public void entityParser() {
        String text = "&amp; is an HTML entity but &ambassador is not.";
        logResult(entityParser(text));
    }

    /**
     * 1410. HTML 实体解析器
     *
     * <p>「HTML 实体解析器」 是一种特殊的解析器，它将 HTML 代码作为输入，并用字符本身替换掉所有这些特殊的字符实体。
     *
     * <p>HTML 里这些特殊字符和它们对应的字符实体包括：
     *
     * <p>双引号：字符实体为 &quot; ，对应的字符是 " 。 单引号：字符实体为 &apos; ，对应的字符是 ' 。 与符号：字符实体为 &amp; ，对应对的字符是 & 。
     * 大于号：字符实体为 &gt; ，对应的字符是 > 。 小于号：字符实体为 &lt; ，对应的字符是 < 。 斜线号：字符实体为 &frasl; ，对应的字符是 / 。 给你输入字符串
     * text ，请你实现一个 HTML 实体解析器，返回解析器解析后的结果。
     *
     * <p>示例 1：
     *
     * <p>输入：text = "&amp; is an HTML entity but &ambassador; is not." 输出："& is an HTML entity but
     * &ambassador; is not." 解释：解析器把字符实体 &amp; 用 & 替换 示例 2：
     *
     * <p>输入：text = "and I quote: &quot;...&quot;" 输出："and I quote: \"...\"" 示例 3：
     *
     * <p>输入：text = "Stay home! Practice on Leetcode :)" 输出："Stay home! Practice on Leetcode :)" 示例
     * 4：
     *
     * <p>输入：text = "x &gt; y &amp;&amp; x &lt; y is always false" 输出："x > y && x < y is always
     * false" 示例 5：
     *
     * <p>输入：text = "leetcode.com&frasl;problemset&frasl;all" 输出："leetcode.com/problemset/all"
     *
     * <p>提示：
     *
     * <p>1 <= text.length <= 10^5 字符串可能包含 256 个ASCII 字符中的任意字符。
     *
     * @param text
     * @return
     */
    public String entityParser(String text) {
        int len = text.length();
        int index = 0;
        StringBuilder sb = new StringBuilder();
        Map<String, Character> map = new HashMap<>();
        map.put("&quot;", '"');
        map.put("&apos;", '\'');
        map.put("&amp;", '&');
        map.put("&gt;", '>');
        map.put("&lt;", '<');
        map.put("&frasl;", '/');
        // 双引号：字符实体为 &quot; ，对应的字符是 " 。
        // 单引号：字符实体为 &apos; ，对应的字符是 ' 。
        // 与符号：字符实体为 &amp; ，对应对的字符是 & 。
        // 大于号：字符实体为 &gt; ，对应的字符是 > 。
        // 小于号：字符实体为 &lt; ，对应的字符是 < 。
        // 斜线号：字符实体为 &frasl; ，对应的字符是 / 。
        while (index < len) {
            char c = text.charAt(index);
            if (c == '&') {
                int end = index + 1;
                while (end + 1 < len && text.charAt(end) != ';') {
                    end++;
                }
                String str = text.substring(index, end + 1);
                log.debug(str);
                Character ch = map.get(str);
                if (Objects.nonNull(ch)) {
                    sb.append(ch);
                } else {
                    sb.append(str);
                }

                index = end + 1;
            } else {
                sb.append(c);
                index++;
            }
        }
        return sb.toString();
    }

    /**
     * 722. 删除注释
     *
     * <p>给一个 C++ 程序，删除程序中的注释。这个程序source是一个数组，其中source[i]表示第i行源码。 这表示每行源码由\n分隔。
     *
     * <p>在 C++ 中有两种注释风格，行内注释和块注释。
     *
     * <p>字符串// 表示行注释，表示//和其右侧的其余字符应该被忽略。
     *
     * <p>字符串/*\
     * 表示一个块注释，它表示直到*\/的下一个（非重叠）出现的所有字符都应该被忽略。（阅读顺序为从左到右）非重叠是指，字符串/*\/并没有结束块注释，因为注释的结尾与开头相重叠。
     *
     * <p>第一个有效注释优先于其他注释：如果字符串//出现在块注释中会被忽略。 同样，如果字符串/*\出现在行或块注释中也会被忽略。
     *
     * <p>如果一行在删除注释之后变为空字符串，那么不要输出该行。即，答案列表中的每个字符串都是非空的。
     *
     * <p>样例中没有控制字符，单引号或双引号字符。比如，source = "string s = "/*\ Not a comment. *\/";"
     * 不会出现在测试样例里。（此外，没有其他内容（如定义或宏）会干扰注释。）
     *
     * <p>我们保证每一个块注释最终都会被闭合， 所以在行或块注释之外的/*\总是开始新的注释。
     *
     * <p>最后，隐式换行符可以通过块注释删除。 有关详细信息，请参阅下面的示例。
     *
     * <p>从源代码中删除注释后，需要以相同的格式返回源代码。
     *
     * <p>示例 1:
     *
     * <p>输入: source = ["/*\Test program *\/", "int main()", "{ ", " // variable declaration ", "int
     * a, b, c;", "/*\ This is a test", " multiline ", " comment for ", " testing *\/", "a = b +
     * c;", "}"]
     *
     * <p>示例代码可以编排成这样: /*\Test program *\/ int main() { // variable declaration int a, b, c; /*\
     * This is a test multiline comment for testing *\/ a = b + c; }
     *
     * <p>输出: ["int main()","{ "," ","int a, b, c;","a = b + c;","}"]
     *
     * <p>编排后: int main() {
     *
     * <p>int a, b, c; a = b + c; }
     *
     * <p>解释: 第 1 行和第 6-9 行的字符串 /*\ 表示块注释。第 4 行的字符串 // 表示行注释。 示例 2:
     *
     * <p>输入: source = ["a/*\comment", "line", "more_comment*\/b"] 输出: ["ab"] 解释: 原始的 source 字符串是
     * "a/*\comment\nline\nmore_comment*\/b", 其中我们用粗体显示了换行符。删除注释后，隐含的换行符被删除，留下字符串 "ab" 用换行符分隔成数组时就是
     * ["ab"]. 注意:
     *
     * <p>source的长度范围为[1, 100]. source[i]的长度范围为[0, 80]. 每个块注释都会被闭合。 给定的源码中不会有单引号、双引号或其他控制字符。
     *
     * @param source
     * @return
     */
    public List<String> removeComments(String[] source) {
        List<String> result = new ArrayList<>();
        boolean inBlock = false;
        StringBuilder sb = new StringBuilder();

        for (String line : source) {
            if (!inBlock) {
                sb = new StringBuilder();
            }
            int i = 0;
            char[] chars = line.toCharArray();
            while (i < line.length()) {
                if (!inBlock && i + 1 < line.length() && chars[i] == '/' && chars[i + 1] == '*') {
                    inBlock = true;
                    i++;
                } else if (inBlock
                        && i + 1 < line.length()
                        && chars[i] == '*'
                        && chars[i + 1] == '/') {
                    inBlock = false;
                    i++;
                } else if (!inBlock
                        && i + 1 < line.length()
                        && chars[i] == '/'
                        && chars[i + 1] == '/') {
                    break;
                } else if (!inBlock) {
                    sb.append(chars[i]);
                }
                i++;
            }

            if (!inBlock && sb.length() > 0) {
                result.add(sb.toString());
            }
        }

        return result;
    }

    /**
     * 5472. 重新排列字符串
     *
     * <p>给你一个字符串 s 和一个 长度相同 的整数数组 indices 。
     *
     * <p>请你重新排列字符串 s ，其中第 i 个字符需要移动到 indices[i] 指示的位置。
     *
     * <p>返回重新排列后的字符串。
     *
     * <p>示例 1：
     *
     * <p>输入：s = "codeleet", indices = [4,5,6,7,0,2,1,3] 输出："leetcode" 解释：如图所示，"codeleet" 重新排列后变为
     * "leetcode" 。 示例 2：
     *
     * <p>输入：s = "abc", indices = [0,1,2] 输出："abc" 解释：重新排列后，每个字符都还留在原来的位置上。 示例 3：
     *
     * <p>输入：s = "aiohn", indices = [3,1,4,2,0] 输出："nihao" 示例 4：
     *
     * <p>输入：s = "aaiougrt", indices = [4,0,2,6,7,3,1,5] 输出："arigatou" 示例 5：
     *
     * <p>输入：s = "art", indices = [1,0,2] 输出："rat"
     *
     * <p>提示：
     *
     * <p>s.length == indices.length == n 1 <= n <= 100 s 仅包含小写英文字母。 0 <= indices[i] < n indices
     * 的所有的值都是唯一的（也就是说，indices 是整数 0 到 n - 1 形成的一组排列）。
     *
     * @param s
     * @param indices
     * @return
     */
    public String restoreString(String s, int[] indices) {
        char[] chars = new char[s.length()];

        for (int i = 0; i < s.length(); i++) {
            int idx = indices[i];
            chars[idx] = s.charAt(i);
        }
        return new String(chars);
    }

    @Test
    public void numSplits() {
        String s = "acbadbaada";
        logResult(numSplits(s));
    }

    /**
     * 1525. 字符串的好分割数目
     *
     * <p>给你一个字符串 s ，一个分割被称为 「好分割」 当它满足：将 s 分割成 2 个字符串 p 和 q ，它们连接起来等于 s 且 p 和 q 中不同字符的数目相同。
     *
     * <p>请你返回 s 中好分割的数目。
     *
     * <p>示例 1：
     *
     * <p>输入：s = "aacaba" 输出：2 解释：总共有 5 种分割字符串 "aacaba" 的方法，其中 2 种是好分割。 ("a", "acaba")
     * 左边字符串和右边字符串分别包含 1 个和 3 个不同的字符。 ("aa", "caba") 左边字符串和右边字符串分别包含 1 个和 3 个不同的字符。 ("aac", "aba")
     * 左边字符串和右边字符串分别包含 2 个和 2 个不同的字符。这是一个好分割。 ("aaca", "ba") 左边字符串和右边字符串分别包含 2 个和 2 个不同的字符。这是一个好分割。
     * ("aacab", "a") 左边字符串和右边字符串分别包含 3 个和 1 个不同的字符。 示例 2：
     *
     * <p>输入：s = "abcd" 输出：1 解释：好分割为将字符串分割成 ("ab", "cd") 。 示例 3：
     *
     * <p>输入：s = "aaaaa" 输出：4 解释：所有分割都是好分割。 示例 4：
     *
     * <p>输入：s = "acbadbaada" 输出：2
     *
     * <p>提示：
     *
     * <p>s 只包含小写英文字母。 1 <= s.length <= 10^5
     *
     * @param s
     * @return
     */
    public int numSplits(String s) {
        char[] chars = s.toCharArray();
        int len = s.length();
        // 滑动窗口
        int[] leftCount = new int[len];
        int[] rightCount = new int[len];
        Set<Character> set = new HashSet<>();
        for (int i = 0; i < len; i++) {
            set.add(chars[i]);
            leftCount[i] = set.size();
        }
        set.clear();
        for (int i = len - 1; i >= 0; i--) {
            set.add(chars[i]);
            rightCount[i] = set.size();
        }
        int result = 0;
        for (int i = 1; i < len; i++) {
            if (leftCount[i - 1] == rightCount[i]) {
                result++;
            }
        }

        return result;
    }

    @Test
    public void reorganizeString() {
        String s = "aaab";
        logResult(reorganizeString(s));
    }

    /**
     * 767. 重构字符串
     *
     * <p>给定一个字符串S，检查是否能重新排布其中的字母，使得两相邻的字符不同。
     *
     * <p>若可行，输出任意可行的结果。若不可行，返回空字符串。
     *
     * <p>示例 1:
     *
     * <p>输入: S = "aab" 输出: "aba" 示例 2:
     *
     * <p>输入: S = "aaab" 输出: "" 注意:
     *
     * <p>S 只包含小写字母并且长度在[1, 500]区间内。
     *
     * @param S
     * @return
     */
    public String reorganizeString(String S) {
        int[] letters = new int[26];
        for (int i = 0; i < 26; i++) {
            letters[i] = i;
        }
        int len = S.length();
        int helf = (len + 1) >> 1;
        for (char c : S.toCharArray()) {
            letters[c - 'a'] += 100;
        }

        Arrays.sort(letters);
        char[] chars = new char[len];
        int index = 0;
        for (int i = 25; i >= 0; i--) {
            int count = letters[i] / 100;
            if (count == 0) {
                continue;
            }
            if (count > helf) {
                return "";
            }
            char c = (char) ('a' + letters[i] % 100);
            while (count > 0) {
                if (index >= len) {
                    index = 1;
                }
                chars[index] = c;
                count--;
                index += 2;
            }
        }
        return new String(chars);
    }

    /**
     * 756. 金字塔转换矩阵
     *
     * <p>现在，我们用一些方块来堆砌一个金字塔。 每个方块用仅包含一个字母的字符串表示。
     *
     * <p>使用三元组表示金字塔的堆砌规则如下：
     *
     * <p>对于三元组(A, B, C) ，“C”为顶层方块，方块“A”、“B”分别作为方块“C”下一层的的左、右子块。当且仅当(A, B, C)是被允许的三元组，我们才可以将其堆砌上。
     *
     * <p>初始时，给定金字塔的基层 bottom，用一个字符串表示。一个允许的三元组列表 allowed，每个三元组用一个长度为 3 的字符串表示。
     *
     * <p>如果可以由基层一直堆到塔尖就返回 true ，否则返回 false 。
     *
     * <p>示例 1：
     *
     * <p>输入：bottom = "BCD", allowed = ["BCG", "CDE", "GEA", "FFF"] 输出：true 解析： 可以堆砌成这样的金字塔: A / \ G
     * E / \ / \ B C D
     *
     * <p>因为符合('B', 'C', 'G'), ('C', 'D', 'E') 和 ('G', 'E', 'A') 三种规则。 示例 2：
     *
     * <p>输入：bottom = "AABA", allowed = ["AAA", "AAB", "ABA", "ABB", "BAC"] 输出：false 解析： 无法一直堆到塔尖。
     * 注意, 允许存在像 (A, B, C) 和 (A, B, D) 这样的三元组，其中 C != D。
     *
     * <p>提示：
     *
     * <p>bottom 的长度范围在 [2, 8]。 allowed 的长度范围在[0, 200]。 方块的标记字母范围为{'A', 'B', 'C', 'D', 'E', 'F',
     * 'G'}。
     *
     * @param bottom
     * @param allowed
     * @return
     */
    public boolean pyramidTransition(String bottom, List<String> allowed) {
        T = new int[7][7];
        for (String a : allowed) {
            T[a.charAt(0) - 'A'][a.charAt(1) - 'A'] |= 1 << (a.charAt(2) - 'A');
        }
        seen = new HashSet<>();
        int N = bottom.length();
        int[][] A = new int[N][N];
        int t = 0;
        for (char c : bottom.toCharArray()) {
            A[N - 1][t++] = c - 'A';
        }
        return solve(A, 0, N - 1, 0);
    }

    int[][] T;
    Set<Long> seen;

    public boolean solve(int[][] A, long R, int N, int i) {
        if (N == 1 && i == 1) {
            return true;
        } else if (i == N) {
            if (seen.contains(R)) return false;
            seen.add(R);
            return solve(A, 0, N - 1, 0);
        } else {
            int w = T[A[N][i]][A[N][i + 1]];
            for (int b = 0; b < 7; b++) {
                if (((w >> b) & 1) != 0) {
                    A[N - 1][i] = b;
                    if (solve(A, R * 8 + (b + 1), N, i + 1)) {
                        return true;
                    }
                }
            }
            return false;
        }
    }

    @Test
    public void smallestSubsequence() {
        String text = "cdadabcc";
        logResult(smallestSubsequence(text));
    }

    /**
     * 1081. 不同字符的最小子序列
     *
     * <p>返回字符串 text 中按字典序排列最小的子序列，该子序列包含 text 中所有不同字符一次。
     *
     * <p>示例 1：
     *
     * <p>输入："cdadabcc" 输出："adbc" 示例 2：
     *
     * <p>输入："abcd" 输出："abcd" 示例 3：
     *
     * <p>输入："ecbacba" 输出："eacb" 示例 4：
     *
     * <p>输入："leetcode" 输出："letcod"
     *
     * <p>提示：
     *
     * <p>1 <= text.length <= 1000 text 由小写英文字母组成
     *
     * <p>注意：本题目与 316 https://leetcode-cn.com/problems/remove-duplicate-letters/ 相同
     *
     * @param text
     * @return
     */
    public String smallestSubsequence(String text) {
        // 方法一：题目要求最终返回的字符串必须包含所有出现过的字母，同时得让字符串的字典序最小。因此，对于最终返回的字符串，
        //  最左侧的字符是在能保证其他字符至少能出现一次情况下的最小字符。
        //
        // 方法二：在遍历字符串的过程中，如果字符 i 大于字符i+1，在字符 i 不是最后一次出现的情况下，删除字符 i。
        StringBuilder sb = new StringBuilder();
        Deque<Character> stack = new LinkedList<>();
        Map<Character, Integer> lastIndex = new HashMap<>();
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            lastIndex.put(c, i);
        }
        boolean[] visited = new boolean[26];
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (visited[c - 'a']) {
                continue;
            }
            while (!stack.isEmpty()
                    && c < stack.peekLast()
                    && lastIndex.get(stack.peekLast()) > i) {
                visited[stack.removeLast() - 'a'] = false;
            }
            visited[c - 'a'] = true;
            stack.addLast(c);
        }
        for (char c : stack) {
            sb.append(c);
        }
        /*int size = stack.size();
        for (int i = 0; i < size; i++) {
            sb.append(stack.pop());
        }*/
        return sb.toString();
    }

    @Test
    public void palindromePairs() {
        String[] words = {"abcd", "dcba", "lls", "s", "sssll"};
        logResult(palindromePairs(words));
    }

    /**
     * 336. 回文对 给定一组唯一的单词，
     *
     * <p>找出所有不同 的索引对(i, j)，使得列表中的两个单词， words[i] + words[j] ，可拼接成回文串。
     *
     * <p>示例 1:
     *
     * <p>输入: ["abcd","dcba","lls","s","sssll"] 输出: [[0,1],[1,0],[3,2],[2,4]] 解释: 可拼接成的回文串为
     * ["dcbaabcd","abcddcba","slls","llssssll"] 示例 2:
     *
     * <p>输入: ["bat","tab","cat"] 输出: [[0,1],[1,0]] 解释: 可拼接成的回文串为 ["battab","tabbat"]
     *
     * @param words
     * @return
     */
    public List<List<Integer>> palindromePairs(String[] words) {
        List<List<Integer>> result = new ArrayList<>();

        Map<String, Integer> indexMap = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            String word = new StringBuilder(words[i]).reverse().toString();
            indexMap.put(word, i);
        }

        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            int len = word.length();
            if (len == 0) {
                continue;
            }
            for (int j = 0; j <= len; j++) {
                if (checkPalindrome(word, j, len - 1)) {
                    int index = indexMap.getOrDefault(word.substring(0, j), -1);
                    if (index != -1 && index != i) {
                        result.add(Arrays.asList(i, index));
                    }
                }
                if (j != 0 && checkPalindrome(word, 0, j - 1)) {
                    int index = indexMap.getOrDefault(word.substring(j, len), -1);
                    if (index != -1 && index != i) {
                        result.add(Arrays.asList(index, i));
                    }
                }
            }
        }

        /*for (int i = 0; i < words.length; i++) {
            String word = words[i];
            if (word.length() == 0) {
                for (int j = 0; j < words.length; j++) {
                    if (i == j) {
                        continue;
                    }
                    if (checkPalindrome(words[j])) {
                        result.add(Arrays.asList(i, j));
                        result.add(Arrays.asList(j, i));
                    }
                }
                continue;
            }
            char c1 = word.charAt(0), c2 = word.charAt(word.length() - 1);

            String suffix = "-" + c1;
            String prefix = c2 + "-";
            if (map.containsKey(suffix)) {
                List<Integer> list = map.get(suffix);
                for (int j : list) {
                    if (i != j && checkPalindrome(word + words[j])) {
                        result.add(Arrays.asList(i, j));
                    }
                }
            }

            if (map.containsKey(prefix)) {
                List<Integer> list = map.get(prefix);
                for (int j : list) {
                    if (i != j && checkPalindrome(words[j] + word)) {
                        result.add(Arrays.asList(j, i));
                    }
                }
            }

            List<Integer> list1 = map.computeIfAbsent(c1 + "-", k -> new ArrayList<>());
            List<Integer> list2 = map.computeIfAbsent("-" + c2, k -> new ArrayList<>());
            list1.add(i);
            list2.add(i);
        }*/

        return result;
    }

    private boolean checkPalindrome(String s) {
        char[] chars = s.toCharArray();
        int left = 0, right = chars.length - 1;
        while (left < right) {
            if (chars[left] != chars[right]) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    @Test
    public void canConvertString() {
        String s = "aab", t = "bbb";
        int k = 27;
        logResult(canConvertString(s, t, k));
    }

    /**
     * 5469. K 次操作转变字符串
     *
     * <p>给你两个字符串 s 和 t ，你的目标是在 k 次操作以内把字符串 s 转变成 t 。
     *
     * <p>在第 i 次操作时（1 <= i <= k），你可以选择进行如下操作：
     *
     * <p>选择字符串 s 中满足 1 <= j <= s.length 且之前未被选过的任意下标 j （下标从 1 开始），并将此位置的字符切换 i 次。 不进行任何操作。 切换 1
     * 次字符的意思是用字母表中该字母的下一个字母替换它（字母表环状接起来，所以 'z' 切换后会变成 'a'）。
     *
     * <p>请记住任意一个下标 j 最多只能被操作 1 次。
     *
     * <p>如果在不超过 k 次操作内可以把字符串 s 转变成 t ，那么请你返回 true ，否则请你返回 false 。
     *
     * <p>示例 1：
     *
     * <p>输入：s = "input", t = "ouput", k = 9 输出：true 解释：第 6 次操作时，我们将 'i' 切换 6 次得到 'o' 。第 7 次操作时，我们将
     * 'n' 切换 7 次得到 'u' 。 示例 2：
     *
     * <p>输入：s = "abc", t = "bcd", k = 10 输出：false 解释：我们需要将每个字符切换 1 次才能得到 t 。我们可以在第 1 次操作时将 'a' 切换成
     * 'b' ，但另外 2 个字母在剩余操作中无法再转变为 t 中对应字母。 示例 3：
     *
     * <p>输入：s = "aab", t = "bbb", k = 27 输出：true 解释：第 1 次操作时，我们将第一个 'a' 切换 1 次得到 'b' 。在第 27
     * 次操作时，我们将第二个字母 'a' 切换 27 次得到 'b' 。
     *
     * <p>提示：
     *
     * <p>1 <= s.length, t.length <= 10^5 0 <= k <= 10^9 s 和 t 只包含小写英文字母。
     *
     * @param s
     * @param t
     * @param k
     * @return
     */
    public boolean canConvertString(String s, String t, int k) {
        if (s.length() != t.length()) {
            return false;
        }
        int[] counts = new int[26];
        int len = s.length();
        for (int i = 0; i < len; i++) {
            char c1 = s.charAt(i), c2 = t.charAt(i);
            if (c1 == c2) {
                continue;
            }
            int count = c1 < c2 ? c2 - c1 : 26 - c1 + c2;
            if (count > k) {
                return false;
            }
            int idx = count;
            /*while (idx <= k && counts[idx]) {
                idx += 26;
            }
            if (idx > k || counts[idx]) {
                return false;
            }*/
            counts[idx]++;
        }

        for (int i = 1; i < 26; i++) {
            int count = counts[i];
            if (i + 26 * (count - 1) > k) {
                return false;
            }
        }
        return true;
    }

    /**
     * 1541. 平衡括号字符串的最少插入次数
     *
     * <p>给你一个括号字符串 s ，它只包含字符 '(' 和 ')' 。一个括号字符串被称为平衡的当它满足：
     *
     * <p>任何左括号 '(' 必须对应两个连续的右括号 '))' 。 左括号 '(' 必须在对应的连续两个右括号 '))' 之前。
     *
     * <p>比方说 "())"， "())(())))" 和 "(())())))" 都是平衡的， ")()"， "()))" 和 "(()))" 都是不平衡的。
     *
     * <p>你可以在任意位置插入字符 '(' 和 ')' 使字符串平衡。
     *
     * <p>请你返回让 s 平衡的最少插入次数。
     *
     * <p>示例 1：
     *
     * <p>输入：s = "(()))" 输出：1 解释：第二个左括号有与之匹配的两个右括号，但是第一个左括号只有一个右括号。
     *
     * <p>我们需要在字符串结尾额外增加一个 ')' 使字符串变成平衡字符串 "(())))" 。 示例 2：
     *
     * <p>输入：s = "())" 输出：0 解释：字符串已经平衡了。 示例 3：
     *
     * <p>输入：s = "))())(" 输出：3 解释：添加 '(' 去匹配最开头的 '))' ，然后添加 '))' 去匹配最后一个 '(' 。 示例 4：
     *
     * <p>输入：s = "((((((" 输出：12 解释：添加 12 个 ')' 得到平衡字符串。 示例 5：
     *
     * <p>输入：s = ")))))))" 输出：5 解释：在字符串开头添加 4 个 '(' 并在结尾添加 1 个 ')' ，
     *
     * <p>字符串变成平衡字符串 "(((())))))))" 。
     *
     * <p>提示：
     *
     * <p>1 <= s.length <= 10^5 s 只包含 '(' 和 ')' 。
     *
     * @param s
     * @return
     */
    public int minInsertions(String s) {
        int leftCount = 0, len = s.length();
        int result = 0;
        /* for (char c :) {

        }*/
        char[] chars = s.toCharArray();
        for (int i = 0; i < len; i++) {
            if (chars[i] == '(') {
                leftCount++; // 左括号次数
            } else if (chars[i] == ')') {
                if (i + 1 < len && chars[i + 1] == ')') {
                    i++; // 找第二个左括号
                } else {
                    result++; // 缺少第二个右括号就添加一个
                }
                if (leftCount > 0) {
                    leftCount--;
                } // 两个右括号匹配一个左括号
                else {
                    result++; // 缺少左括号就添加一个
                }
            }
        }
        result += leftCount * 2; // 多出的左括号都匹配两个右括号

        return result;
    }

    @Test
    public void makeGood() {
        String s = "leEeetcode";
        logResult(makeGood(s));
    }

    /**
     * 5483. 整理字符串
     *
     * <p>给你一个由大小写英文字母组成的字符串 s 。
     *
     * <p>一个整理好的字符串中，两个相邻字符 s[i] 和 s[i + 1] 不会同时满足下述条件：
     *
     * <p>0 <= i <= s.length - 2 s[i] 是小写字符，但 s[i + 1] 是对应的大写字符；反之亦然 。
     * 请你将字符串整理好，每次你都可以从字符串中选出满足上述条件的 两个相邻 字符并删除，直到字符串整理好为止。
     *
     * <p>请返回整理好的 字符串 。题目保证在给出的约束条件下，测试样例对应的答案是唯一的。
     *
     * <p>注意：空字符串也属于整理好的字符串，尽管其中没有任何字符。
     *
     * <p>示例 1：
     *
     * <p>输入：s = "leEeetcode" 输出："leetcode" 解释：无论你第一次选的是 i = 1 还是 i = 2，都会使 "leEeetcode" 缩减为
     * "leetcode" 。 示例 2：
     *
     * <p>输入：s = "abBAcC" 输出："" 解释：存在多种不同情况，但所有的情况都会导致相同的结果。例如： "abBAcC" --> "aAcC" --> "cC" --> ""
     * "abBAcC" --> "abBA" --> "aA" --> "" 示例 3：
     *
     * <p>输入：s = "s" 输出："s"
     *
     * <p>提示：
     *
     * <p>1 <= s.length <= 100 s 只包含小写和大写英文字母
     *
     * @param s
     * @return
     */
    public String makeGood(String s) {
        StringBuilder sb = new StringBuilder();
        char[] chars = s.toCharArray();
        Deque<Character> stack = new LinkedList<>();
        logResult('A' - 'a');
        for (int i = 0; i < s.length(); i++) {
            char c = chars[i];
            if (stack.isEmpty()) {
                stack.push(c);
            } else {
                int num = c - stack.peek();
                if (num == 32 || num == -32) {
                    stack.pop();
                } else {
                    stack.push(c);
                }
            }
        }
        for (char c : stack) {
            sb.append(c);
        }
        sb.reverse();
        /* char last = chars[0];
        for (int i = 1; i < s.length(); i++) {

            char c = chars[i];
            if ('a' <= last && last <= 'z' && 'A' <= c && c <= 'Z') {
                continue;
            }
            if ('A' <= last && last <= 'Z' && 'a' <= chars[i + 1] && chars[i + 1] <= 'z') {
                continue;
            }
            sb.append(chars[i]);
            if (i == s.length() - 1) {
                sb.append(c);
            }
        }*/
        return sb.toString();
    }

    @Test
    public void findKthBit() {
        int n = 3, k = 5;
        logResult(findKthBit(n, k));
    }
    /**
     * 5484. 找出第 N 个二进制字符串中的第 K 位
     *
     * <p>给你两个正整数 n 和 k，二进制字符串 Sn 的形成规则如下：
     *
     * <p>S1 = "0" 当 i > 1 时，Si = Si-1 + "1" + reverse(invert(Si-1)) 其中 + 表示串联操作，reverse(x) 返回反转 x
     * 后得到的字符串，而 invert(x) 则会翻转 x 中的每一位（0 变为 1，而 1 变为 0）
     *
     * <p>例如，符合上述描述的序列的前 4 个字符串依次是：
     *
     * <p>S1 = "0" S2 = "011" S3 = "0111001" S4 = "011100110110001" 请你返回 Sn 的 第 k 位字符 ，题目数据保证 k 一定在
     * Sn 长度范围以内。
     *
     * <p>示例 1：
     *
     * <p>输入：n = 3, k = 1 输出："0" 解释：S3 为 "0111001"，其第 1 位为 "0" 。 示例 2：
     *
     * <p>输入：n = 4, k = 11 输出："1" 解释：S4 为 "011100110110001"，其第 11 位为 "1" 。 示例 3：
     *
     * <p>输入：n = 1, k = 1 输出："0" 示例 4：
     *
     * <p>输入：n = 2, k = 3 输出："1"
     *
     * <p>提示：
     *
     * <p>1 <= n <= 20 1 <= k <= 2n - 1
     *
     * @param n
     * @param k
     * @return
     */
    public char findKthBit(int n, int k) {
        if (n == 1) {
            return '0';
        }
        // 1 3 7 15
        // len(n) = 2^n - 1 => 1>>n - 1
        int mid = 1 << (n - 1);
        if (k == mid) {
            return '1';
        }
        if (k < mid) {
            return findKthBit(n - 1, k);
        } else {
            char c = findKthBit(n - 1, 2 * mid - k);
            log.debug("n:{} k :{}", n - 1, 2 * mid - k);
            log.debug("c:{}", c);
            return c == '0' ? '1' : '0';
        }
    }

    @Test
    public void alphabetBoardPath() {
        String target = "leet";
        logResult(alphabetBoardPath(target));
    }

    /**
     * 1138. 字母板上的路径
     *
     * <p>我们从一块字母板上的位置 (0, 0) 出发，该坐标对应的字符为 board[0][0]。
     *
     * <p>在本题里，字母板为board = ["abcde", "fghij", "klmno", "pqrst", "uvwxy", "z"]，如下所示。
     *
     * <p>我们可以按下面的指令规则行动：
     *
     * <p>如果方格存在，'U' 意味着将我们的位置上移一行； 如果方格存在，'D' 意味着将我们的位置下移一行； 如果方格存在，'L' 意味着将我们的位置左移一列； 如果方格存在，'R'
     * 意味着将我们的位置右移一列； '!' 会把在我们当前位置 (r, c) 的字符 board[r][c] 添加到答案中。 （注意，字母板上只存在有字母的位置。）
     *
     * <p>返回指令序列，用最小的行动次数让答案和目标 target 相同。你可以返回任何达成目标的路径。
     *
     * <p>示例 1：
     *
     * <p>输入：target = "leet" 输出："DDR!UURRR!!DDD!" 示例 2：
     *
     * <p>输入：target = "code" 输出："RR!DDRR!UUL!R!"
     *
     * <p>提示：
     *
     * <p>1 <= target.length <= 100 target 仅含有小写英文字母。
     *
     * @param target
     * @return
     */
    public String alphabetBoardPath(String target) {
        StringBuilder sb = new StringBuilder();
        Map<Character, int[]> map = new HashMap<>();
        int row = 0, col = 0;
        for (int i = 0; i < 26; i++) {
            char c = (char) ('a' + i);
            map.put(c, new int[] {row, col});
            col++;
            if (col == 5) {
                col = 0;
                row++;
            }
        }
        int[] start = new int[] {0, 0};
        for (int i = 0; i < target.length(); i++) {
            char c = target.charAt(i);
            int[] end = map.get(c);
            sb.append(getPath(start, end));
            start = end;
        }

        return sb.toString();
    }

    private String getPath(int[] start, int[] end) {
        StringBuilder sb = new StringBuilder();
        int y = end[0] - start[0], x = end[1] - start[1];
        char c2 = y > 0 ? 'D' : 'U';
        for (int i = 0; i < Math.abs(y); i++) {
            sb.append(c2);
        }
        char c1 = x > 0 ? 'R' : 'L';
        for (int i = 0; i < Math.abs(x); i++) {
            sb.append(c1);
        }

        sb.append('!');
        return sb.toString();
    }

    /**
     * 1156. 单字符重复子串的最大长度
     *
     * <p>如果字符串中的所有字符都相同，那么这个字符串是单字符重复的字符串。
     *
     * <p>给你一个字符串 text，你只能交换其中两个字符一次或者什么都不做，然后得到一些单字符重复的子串。返回其中最长的子串的长度。
     *
     * <p>示例 1：
     *
     * <p>输入：text = "ababa" 输出：3 示例 2：
     *
     * <p>输入：text = "aaabaaa" 输出：6 示例 3：
     *
     * <p>输入：text = "aaabbaaa" 输出：4 示例 4：
     *
     * <p>输入：text = "aaaaa" 输出：5 示例 5：
     *
     * <p>输入：text = "abcdef" 输出：1
     *
     * <p>提示：
     *
     * <p>1 <= text.length <= 20000 text 仅由小写英文字母组成。
     *
     * @param text
     * @return
     */
    public int maxRepOpt1(String text) {
        int len = text.length();
        int[] letters = new int[26];
        // 统计26个小写字母各自出现的次数
        for (char c : text.toCharArray()) {
            letters[c - 'a']++;
        }
        char last = text.charAt(0);
        int count = 1, result = 1;
        for (int i = 1; i < len; ++i) {
            char c = text.charAt(i);
            if (last != c) {
                int tempIdx = i;
                while (tempIdx + 1 < len && last == text.charAt(tempIdx + 1)) {
                    count++;
                    tempIdx++;
                }
                if (letters[last - 'a'] > count) {
                    count++;
                }
                result = Math.max(result, count);
                count = 1;
                last = text.charAt(i);
            } else {
                count++;
            }
        }
        if (count > 1 && letters[last - 'a'] > count) {
            count++;
        }
        return Math.max(result, count);
    }

    /**
     * 1177. 构建回文串检测
     *
     * <p>给你一个字符串 s，请你对 s 的子串进行检测。
     *
     * <p>每次检测，待检子串都可以表示为 queries[i] = [left, right, k]。我们可以 重新排列 子串 s[left], ..., s[right]，并从中选择 最多
     * k 项替换成任何小写英文字母。
     *
     * <p>如果在上述检测过程中，子串可以变成回文形式的字符串，那么检测结果为 true，否则结果为 false。
     *
     * <p>返回答案数组 answer[]，其中 answer[i] 是第 i 个待检子串 queries[i] 的检测结果。
     *
     * <p>注意：在替换时，子串中的每个字母都必须作为 独立的 项进行计数，也就是说，如果 s[left..right] = "aaa" 且 k =
     * 2，我们只能替换其中的两个字母。（另外，任何检测都不会修改原始字符串 s，可以认为每次检测都是独立的）
     *
     * <p>示例：
     *
     * <p>输入：s = "abcda", queries = [[3,3,0],[1,2,0],[0,3,1],[0,3,2],[0,4,1]]
     * 输出：[true,false,false,true,true] 解释： queries[0] : 子串 = "d"，回文。 queries[1] : 子串 = "bc"，不是回文。
     * queries[2] : 子串 = "abcd"，只替换 1 个字符是变不成回文串的。 queries[3] : 子串 = "abcd"，可以变成回文的 "abba"。 也可以变成
     * "baab"，先重新排序变成 "bacd"，然后把 "cd" 替换为 "ab"。 queries[4] : 子串 = "abcda"，可以变成回文的 "abcba"。
     *
     * <p>提示：
     *
     * <p>1 <= s.length, queries.length <= 10^5 0 <= queries[i][0] <= queries[i][1] < s.length 0 <=
     * queries[i][2] <= s.length s 中只有小写英文字母
     *
     * @param s
     * @param queries
     * @return
     */
    public List<Boolean> canMakePaliQueries(String s, int[][] queries) {
        // 使用 26位的二进制数表示 字符个数
        int len = s.length();
        int[] nums = new int[len];
        int num = 0;
        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            num ^= 1 << (c - 'a');
            nums[i] = num;
        }

        List<Boolean> result = new ArrayList<>();
        for (int[] query : queries) {
            int start = query[0], end = query[1], k = query[2];
            int last = start > 0 ? nums[start - 1] : 0;
            num = last ^ nums[end];

            int count = Integer.bitCount(num);
            result.add((count >> 1) <= k);
        }

        return result;
    }

    /**
     * 1208. 尽可能使字符串相等
     *
     * <p>给你两个长度相同的字符串，s 和 t。
     *
     * <p>将 s 中的第 i 个字符变到 t 中的第 i 个字符需要 |s[i] - t[i]| 的开销（开销可能为 0），也就是两个字符的 ASCII 码值的差的绝对值。
     *
     * <p>用于变更字符串的最大预算是 maxCost。在转化字符串时，总开销应当小于等于该预算，这也意味着字符串的转化可能是不完全的。
     *
     * <p>如果你可以将 s 的子字符串转化为它在 t 中对应的子字符串，则返回可以转化的最大长度。
     *
     * <p>如果 s 中没有子字符串可以转化成 t 中对应的子字符串，则返回 0。
     *
     * <p>示例 1：
     *
     * <p>输入：s = "abcd", t = "bcdf", cost = 3 输出：3 解释：s 中的 "abc" 可以变为 "bcd"。开销为 3，所以最大长度为 3。 示例 2：
     *
     * <p>输入：s = "abcd", t = "cdef", cost = 3 输出：1 解释：s 中的任一字符要想变成 t 中对应的字符，其开销都是 2。因此，最大长度为 1。 示例
     * 3：
     *
     * <p>输入：s = "abcd", t = "acde", cost = 0 输出：1 解释：你无法作出任何改动，所以最大长度为 1。
     *
     * <p>提示：
     *
     * <p>1 <= s.length, t.length <= 10^5 0 <= maxCost <= 10^6 s 和 t 都只含小写英文字母。
     *
     * @param s
     * @param t
     * @param maxCost
     * @return
     */
    public int equalSubstring(String s, String t, int maxCost) {
        int left = 0, right = 0, cost = 0, result = 0;
        int[] value = new int[s.length()];
        for (int i = 0; i < s.length(); i++) {
            value[i] = Math.abs(s.charAt(i) - t.charAt(i));
        }
        while (right < s.length()) {
            cost += Math.abs(value[right]);
            if (cost > maxCost) {
                cost -= Math.abs(value[left]);
                left++;
            }
            result = Math.max(result, right - left + 1);
            right++;
        }

        return result;
    }

    @Test
    public void thousandSeparator() {
        int n = 123456789;
        logResult(thousandSeparator(n));
    }

    /**
     * 5479. 千位分隔数
     *
     * <p>给你一个整数 n，请你每隔三位添加点（即 "." 符号）作为千位分隔符，并将结果以字符串格式返回。
     *
     * <p>示例 1：
     *
     * <p>输入：n = 987 输出："987" 示例 2：
     *
     * <p>输入：n = 1234 输出："1.234" 示例 3：
     *
     * <p>输入：n = 123456789 输出："123.456.789" 示例 4：
     *
     * <p>输入：n = 0 输出："0"
     *
     * <p>提示：
     *
     * <p>0 <= n < 2^31
     *
     * @param n
     * @return
     */
    public String thousandSeparator(int n) {
        if (n == 0) {
            return "0";
        }
        StringBuilder sb = new StringBuilder();
        int idx = 0;
        while (n > 0) {
            sb.append(n % 10);
            n /= 10;
            idx++;
            if (idx % 3 == 0 && n > 0) {
                sb.append(".");
            }
        }
        return sb.reverse().toString();
    }

    /**
     * 1234. 替换子串得到平衡字符串
     *
     * <p>有一个只含有 'Q', 'W', 'E', 'R' 四种字符，且长度为 n 的字符串。
     *
     * <p>假如在该字符串中，这四个字符都恰好出现 n/4 次，那么它就是一个「平衡字符串」。
     *
     * <p>给你一个这样的字符串 s，请通过「替换一个子串」的方式，使原字符串 s 变成一个「平衡字符串」。
     *
     * <p>你可以用和「待替换子串」长度相同的 任何 其他字符串来完成替换。
     *
     * <p>请返回待替换子串的最小可能长度。
     *
     * <p>如果原字符串自身就是一个平衡字符串，则返回 0。
     *
     * <p>示例 1：
     *
     * <p>输入：s = "QWER" 输出：0 解释：s 已经是平衡的了。 示例 2：
     *
     * <p>输入：s = "QQWE" 输出：1 解释：我们需要把一个 'Q' 替换成 'R'，这样得到的 "RQWE" (或 "QRWE") 是平衡的。 示例 3：
     *
     * <p>输入：s = "QQQW" 输出：2 解释：我们可以把前面的 "QQ" 替换成 "ER"。 示例 4：
     *
     * <p>输入：s = "QQQQ" 输出：3 解释：我们可以替换后 3 个 'Q'，使 s = "QWER"。
     *
     * <p>提示：
     *
     * <p>1 <= s.length <= 10^5 s.length 是 4 的倍数 s 中只含有 'Q', 'W', 'E', 'R' 四种字符
     *
     * @param s
     * @return
     */
    public int balancedString(String s) {
        int len = s.length();
        int[] letters = new int[26];
        for (char c : s.toCharArray()) {
            letters[c - 'A']++;
        }
        int left = 0;
        // 只需要考虑大于len/4 的字符
        int quarterLen = len / 4;
        int result = len;
        for (int right = 0; right < len; right++) {
            char c = s.charAt(right);
            letters[c - 'A']--;
            while (left < len
                    && letters['Q' - 'A'] <= quarterLen
                    && letters['W' - 'A'] <= quarterLen
                    && letters['E' - 'A'] <= quarterLen
                    && letters['R' - 'A'] <= quarterLen) {
                result = Math.min(result, right - left + 1);
                letters[s.charAt(left++) - 'A']++;
            }
        }

        return result;
    }

    @Test
    public void shortestPalindrome() {
        String s = "aacecaaa";
        logResult(shortestPalindrome(s));
    }

    /**
     * 214. 最短回文串
     *
     * <p>给定一个字符串 s，你可以通过在字符串前面添加字符将其转换为回文串。找到并返回可以用这种方式转换的最短回文串。
     *
     * <p>示例 1:
     *
     * <p>输入: "aacecaaa" 输出: "aaacecaaa" 示例 2:
     *
     * <p>输入: "abcd" 输出: "dcbabcd"
     *
     * @param s
     * @return
     */
    public String shortestPalindrome(String s) {

        int len = s.length();
        int[] fail = new int[len];
        Arrays.fill(fail, -1);
        for (int i = 1; i < len; ++i) {
            int j = fail[i - 1];
            while (j != -1 && s.charAt(j + 1) != s.charAt(i)) {
                j = fail[j];
            }
            if (s.charAt(j + 1) == s.charAt(i)) {
                fail[i] = j + 1;
            }
        }
        int best = -1;
        for (int i = len - 1; i >= 0; --i) {
            while (best != -1 && s.charAt(best + 1) != s.charAt(i)) {
                best = fail[best];
            }
            if (s.charAt(best + 1) == s.charAt(i)) {
                ++best;
            }
        }
        String add = (best == len - 1 ? "" : s.substring(best + 1));
        StringBuffer ans = new StringBuffer(add).reverse();
        ans.append(s);
        return ans.toString();
        /*StringBuilder sb = new StringBuilder();

        char[] chars = s.toCharArray();

        int len = chars.length;
        if (len <= 1) {
            return s;
        }
        int resIdx = -1;
        for (int i = len - 1; i >= 0; i--) {
            if (chars[i] != chars[0]) {
                continue;
            }
            if (checkPalindrome(s, 0, i)) {
                resIdx = i;
                break;
            }
        }
        if (resIdx == len - 1) {
            return s;
        }
        sb.append(s.substring(resIdx + 1));
        sb.reverse();
        sb.append(s);

        return sb.toString();*/
    }

    @Test
    public void maxFreq() {
        String s = "aababcaab";
        int maxLetters = 2, minSize = 3, maxSize = 4;
        logResult(maxFreq(s, maxLetters, minSize, maxSize));
    }

    /**
     * 1297. 子串的最大出现次数
     *
     * <p>给你一个字符串 s ，请你返回满足以下条件且出现次数最大的 任意 子串的出现次数：
     *
     * <p>子串中不同字母的数目必须小于等于 maxLetters 。 子串的长度必须大于等于 minSize 且小于等于 maxSize 。
     *
     * <p>示例 1：
     *
     * <p>输入：s = "aababcaab", maxLetters = 2, minSize = 3, maxSize = 4 输出：2 解释：子串 "aab" 在原字符串中出现了 2
     * 次。 它满足所有的要求：2 个不同的字母，长度为 3 （在 minSize 和 maxSize 范围内）。 示例 2：
     *
     * <p>输入：s = "aaaa", maxLetters = 1, minSize = 3, maxSize = 3 输出：2 解释：子串 "aaa" 在原字符串中出现了 2
     * 次，且它们有重叠部分。 示例 3：
     *
     * <p>输入：s = "aabcabcab", maxLetters = 2, minSize = 2, maxSize = 3 输出：3 示例 4：
     *
     * <p>输入：s = "abcde", maxLetters = 2, minSize = 3, maxSize = 3 输出：0
     *
     * <p>提示：
     *
     * <p>1 <= s.length <= 10^5 1 <= maxLetters <= 26 1 <= minSize <= maxSize <= min(26, s.length) s
     * 只包含小写英文字母。
     *
     * @param s
     * @param maxLetters
     * @param minSize
     * @param maxSize
     * @return
     */
    public int maxFreq(String s, int maxLetters, int minSize, int maxSize) {
        int len = s.length();
        int[] nums = new int[len];
        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            nums[i] = 1 << (c - 'a');
        }
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i <= len - minSize; i++) {

            String tmp = s.substring(i, i + minSize);
            int count = map.getOrDefault(tmp, 0);
            if (count > 0) {
                map.put(tmp, count + 1);
                continue;
            }
            int num = 0;
            for (int j = i; j < i + minSize; j++) {
                num |= nums[j];
            }
            if (Integer.bitCount(num) <= maxLetters) {
                map.put(tmp, count + 1);
            }
        }
        int result = map.values().stream().max(Integer::compareTo).orElse(0);

        return result;
    }

    @Test
    public void numWays() {
        String s = "111111";
        logResult(numWays(s));
    }

    /**
     * 5492. 分割字符串的方案数
     *
     * <p>给你一个二进制串 s （一个只包含 0 和 1 的字符串），我们可以将 s 分割成 3 个 非空 字符串 s1, s2, s3 （s1 + s2 + s3 = s）。
     *
     * <p>请你返回分割 s 的方案数，满足 s1，s2 和 s3 中字符 '1' 的数目相同。
     *
     * <p>由于答案可能很大，请将它对 10^9 + 7 取余后返回。
     *
     * <p>示例 1：
     *
     * <p>输入：s = "10101" 输出：4 解释：总共有 4 种方法将 s 分割成含有 '1' 数目相同的三个子字符串。 "1|010|1" "1|01|01" "10|10|1"
     * "10|1|01" 示例 2：
     *
     * <p>输入：s = "1001" 输出：0 示例 3：
     *
     * <p>输入：s = "0000" 输出：3 解释：总共有 3 种分割 s 的方法。 "0|0|00" "0|00|0" "00|0|0" 示例 4：
     *
     * <p>输入：s = "100100010100110" 输出：12
     *
     * <p>提示：
     *
     * <p>s[i] == '0' 或者 s[i] == '1' 3 <= s.length <= 10^5
     *
     * @param s
     * @return
     */
    public int numWays(String s) {
        int count = 0;
        for (char c : s.toCharArray()) {
            if (c == '1') {
                count++;
            }
        }
        if (count % 3 != 0) {
            return 0;
        }
        int result = 0;
        int len = s.length();
        long leftCount = 0, rightCount = 0;
        if (count == 0) {
            leftCount = (len - 1) % MOD;
            rightCount = (len - 2) % MOD;

            result = (int) (leftCount * rightCount % MOD) / 2;
            return result % MOD;
        }
        int count3 = count / 3;

        int num = 0;
        for (int i = 0; i < len - 2; i++) {
            char c = s.charAt(i);
            if (c == '1') {
                num++;
            }
            if (num == count3) {
                leftCount++;
                leftCount %= MOD;
            }
            if (num > count3) {
                break;
            }
        }
        num = 0;
        for (int i = len - 1; i > 1; i--) {
            char c = s.charAt(i);
            if (c == '1') {
                num++;
            }
            if (num == count3) {
                rightCount++;
                rightCount %= MOD;
            }
            if (num > count3) {
                break;
            }
        }
        result = (int) (leftCount * rightCount % MOD);
        return result;
    }

    @Test
    public void modifyString() {
        String s = "??yw?ipkj?";
        logResult(modifyString(s));
    }
    /**
     * 5507. 替换所有的问号
     *
     * <p>给你一个仅包含小写英文字母和 '?' 字符的字符串 s<var> </var>，请你将所有的 '?' 转换为若干小写字母，使最终的字符串不包含任何 连续重复 的字符。
     *
     * <p>注意：你 不能 修改非 '?' 字符。
     *
     * <p>题目测试用例保证 除 '?' 字符 之外，不存在连续重复的字符。
     *
     * <p>在完成所有转换（可能无需转换）后返回最终的字符串。如果有多个解决方案，请返回其中任何一个。可以证明，在给定的约束条件下，答案总是存在的。
     *
     * <p>示例 1：
     *
     * <p>输入：s = "?zs" 输出："azs" 解释：该示例共有 25 种解决方案，从 "azs" 到 "yzs" 都是符合题目要求的。只有 "z" 是无效的修改，因为字符串
     * "zzs" 中有连续重复的两个 'z' 。 示例 2：
     *
     * <p>输入：s = "ubv?w" 输出："ubvaw" 解释：该示例共有 24 种解决方案，只有替换成 "v" 和 "w" 不符合题目要求。因为 "ubvvw" 和 "ubvww"
     * 都包含连续重复的字符。 示例 3：
     *
     * <p>输入：s = "j?qg??b" 输出："jaqgacb" 示例 4：
     *
     * <p>输入：s = "??yw?ipkj?" 输出："acywaipkja"
     *
     * <p>提示：
     *
     * <p>1 <= s.length <= 100
     *
     * <p>s 仅包含小写英文字母和 '?' 字符
     *
     * @param s
     * @return
     */
    public String modifyString(String s) {
        char[] chars = s.toCharArray();
        int len = s.length();
        for (int i = 0; i < len; i++) {
            char c = chars[i];
            if (c != '?') {
                continue;
            }
            char c1 = i == 0 ? 'a' : getNext(chars[i - 1]);
            if (i + 1 < len) {
                while (c1 == chars[i + 1]) {
                    c1 = getNext(c1);
                }
            }
            chars[i] = c1;
        }

        return new String(chars);
    }

    private char getNext(char c) {
        if (c == 'z') {
            return 'a';
        }
        return (char) (c + 1);
    }

    @Test
    public void printVertically() {
        String s = "TO BE OR NOT TO BE";
        logResult(printVertically(s));
    }

    /**
     * 1324. 竖直打印单词
     *
     * <p>给你一个字符串 s。请你按照单词在 s 中的出现顺序将它们全部竖直返回。 单词应该以字符串列表的形式返回，必要时用空格补位，但输出尾部的空格需要删除（不允许尾随空格）。
     * 每个单词只能放在一列上，每一列中也只能有一个单词。
     *
     * <p>示例 1：
     *
     * <p>输入：s = "HOW ARE YOU" 输出：["HAY","ORO","WEU"] 解释：每个单词都应该竖直打印。 "HAY" "ORO" "WEU" 示例 2：
     *
     * <p>输入：s = "TO BE OR NOT TO BE" 输出：["TBONTB","OEROOE"," T"] 解释：题目允许使用空格补位，但不允许输出末尾出现空格。
     * "TBONTB" "OEROOE" " T" 示例 3：
     *
     * <p>输入：s = "CONTEST IS COMING" 输出：["CIC","OSO","N M","T I","E N","S G","T"]
     *
     * <p>提示：
     *
     * <p>1 <= s.length <= 200 s 仅含大写英文字母。 题目数据保证两个单词之间只有一个空格。
     *
     * @param s
     * @return
     */
    public List<String> printVertically(String s) {
        String[] strs = s.split(" ");
        StringBuilder sb = new StringBuilder();
        List<String> result = new ArrayList<>();

        for (int i = 0; i < 200; i++) {
            sb.setLength(0);
            int blankLen = 0;
            for (String str : strs) {
                if (i < str.length()) {
                    while (blankLen > 0) {
                        sb.append(' ');
                        blankLen--;
                    }
                    sb.append(str.charAt(i));
                } else {
                    blankLen++;
                }
            }
            if (sb.length() == 0) {
                break;
            }
            result.add(sb.toString());
        }

        return result;
    }

    /**
     * 1328. 破坏回文串
     *
     * <p>给你一个回文字符串 palindrome ，请你将其中 一个 字符用任意小写英文字母替换，使得结果字符串的字典序最小，且 不是 回文串。
     *
     * <p>请你返回结果字符串。如果无法做到，则返回一个空串。
     *
     * <p>示例 1：
     *
     * <p>输入：palindrome = "abccba" 输出："aaccba" 示例 2：
     *
     * <p>输入：palindrome = "a" 输出：""
     *
     * <p>提示：
     *
     * <p>1 <= palindrome.length <= 1000 palindrome 只包含小写英文字母。
     *
     * @param palindrome
     * @return
     */
    public String breakPalindrome(String palindrome) {
        int len = palindrome.length();
        if (len == 1) {
            return "";
        }
        char[] chars = palindrome.toCharArray();

        int idx = 0;
        while (idx < len && chars[idx] == 'a') {
            idx++;
        }
        // idx == len 说明全是 a 把 最后一位改为b
        if (idx == len) {
            chars[len - 1] = 'b';
            return new String(chars);
        }
        // idx 是 奇数个元素中间的元素
        if ((len & 1) == 1 && idx == len >> 1) {
            // 前半部分 和 后半部分为 a 中间为 其他
            chars[len - 1] = 'b';
            return new String(chars);
        }

        //  第一个不为 a 的 元素
        chars[idx] = 'a';
        return new String(chars);
    }

    /**
     * 1347. 制造字母异位词的最小步骤数
     *
     * <p>给你两个长度相等的字符串 s 和 t。每一个步骤中，你可以选择将 t 中的 任一字符 替换为 另一个字符。
     *
     * <p>返回使 t 成为 s 的字母异位词的最小步骤数。
     *
     * <p>字母异位词 指字母相同，但排列不同（也可能相同）的字符串。
     *
     * <p>示例 1：
     *
     * <p>输出：s = "bab", t = "aba" 输出：1 提示：用 'b' 替换 t 中的第一个 'a'，t = "bba" 是 s 的一个字母异位词。 示例 2：
     *
     * <p>输出：s = "leetcode", t = "practice" 输出：5 提示：用合适的字符替换 t 中的 'p', 'r', 'a', 'i' 和 'c'，使 t 变成 s
     * 的字母异位词。 示例 3：
     *
     * <p>输出：s = "anagram", t = "mangaar" 输出：0 提示："anagram" 和 "mangaar" 本身就是一组字母异位词。 示例 4：
     *
     * <p>输出：s = "xxyyzz", t = "xxyyzz" 输出：0 示例 5：
     *
     * <p>输出：s = "friend", t = "family" 输出：4
     *
     * <p>提示：
     *
     * <p>1 <= s.length <= 50000 s.length == t.length s 和 t 只包含小写英文字母
     *
     * @param s
     * @param t
     * @return
     */
    public int minSteps(String s, String t) {
        int[] letters = new int[26];
        int result = 0;
        /*for (char c : s.toCharArray()) {
            letters[c - 'a']++;
        }

        for (char c : t.toCharArray()) {
            letters[c - 'a']--;
            if (letters[c - 'a'] < 0) {
                result++;
            }
        }*/
        for (int i = 0; i < s.length(); i++) {
            char c1 = s.charAt(i), c2 = t.charAt(i);
            letters[c1 - 'a']++;
            letters[c2 - 'a']--;
        }
        for (int i = 0; i < 26; i++) {
            if (letters[i] > 0) {
                result += letters[i];
            }
        }

        return result;
    }

    /**
     * 1358. 包含所有三种字符的子字符串数目
     *
     * <p>给你一个字符串 s ，它只包含三种字符 a, b 和 c 。
     *
     * <p>请你返回 a，b 和 c 都 至少 出现过一次的子字符串数目。
     *
     * <p>示例 1：
     *
     * <p>输入：s = "abcabc" 输出：10 解释：包含 a，b 和 c 各至少一次的子字符串为 "abc", "abca", "abcab", "abcabc", "bca",
     * "bcab", "bcabc", "cab", "cabc" 和 "abc" (相同字符串算多次)。 示例 2：
     *
     * <p>输入：s = "aaacb" 输出：3 解释：包含 a，b 和 c 各至少一次的子字符串为 "aaacb", "aacb" 和 "acb" 。 示例 3：
     *
     * <p>输入：s = "abc" 输出：1
     *
     * <p>提示：
     *
     * <p>3 <= s.length <= 5 x 10^4 s 只包含字符 a，b 和 c 。
     *
     * @param s
     * @return
     */
    public int numberOfSubstrings(String s) {
        int[] letters = new int[3];
        int count = 0;
        int right = -1;
        int len = s.length();
        for (int i = 0; i < s.length(); i++) {
            while (right + 1 < len && (letters[0] == 0 || letters[1] == 0 || letters[2] == 0)) {
                char c = s.charAt(++right);
                letters[c - 'a']++;
            }
            if (letters[0] == 0 || letters[1] == 0 || letters[2] == 0) {
                break;
            }
            count += len - right;
            char c = s.charAt(i);
            letters[c - 'a']--;
        }

        return count;
    }

    @Test
    public void numberOfSubstrings() {
        String s = "abc";
        logResult(numberOfSubstrings(s));
    }

    /**
     * LCP 19. 秋叶收藏集
     *
     * <p>小扣出去秋游，途中收集了一些红叶和黄叶，他利用这些叶子初步整理了一份秋叶收藏集 leaves， 字符串 leaves 仅包含小写字符 r 和 y， 其中字符 r 表示一片红叶，字符
     * y 表示一片黄叶。 出于美观整齐的考虑，小扣想要将收藏集中树叶的排列调整成「红、黄、红」三部分。每部分树叶数量可以不相等，但均需大于等于
     * 1。每次调整操作，小扣可以将一片红叶替换成黄叶或者将一片黄叶替换成红叶。请问小扣最少需要多少次调整操作才能将秋叶收藏集调整完毕。
     *
     * <p>示例 1：
     *
     * <p>输入：leaves = "rrryyyrryyyrr"
     *
     * <p>输出：2
     *
     * <p>解释：调整两次，将中间的两片红叶替换成黄叶，得到 "rrryyyyyyyyrr"
     *
     * <p>示例 2：
     *
     * <p>输入：leaves = "ryr"
     *
     * <p>输出：0
     *
     * <p>解释：已符合要求，不需要额外操作
     *
     * <p>提示：
     *
     * <p>3 <= leaves.length <= 10^5 leaves 中只包含字符 'r' 和字符 'y'
     *
     * @param leaves
     * @return
     */
    public int minimumOperations(String leaves) {
        int len = leaves.length();
        // 最终需要红黄红
        // 维持3种状态，分别为截止目前全部红色，在截止目前全红的基础上变为黄色，以及变为黄色的基础上全部红色
        int[][] dp = new int[len][3];
        char[] chars = leaves.toCharArray();

        dp[0][0] = chars[0] == 'r' ? 0 : 1;
        for (int i = 1; i < len; i++) { // 设置第一个状态的值
            if (chars[i] == 'r') {
                dp[i][0] = dp[i - 1][0];
            } else {
                dp[i][0] = dp[i - 1][0] + 1;
            }
        }
        if (chars[1] == 'y') {
            dp[1][1] = dp[0][0]; // 设置第二个状态初值
        } else {
            dp[1][1] = dp[0][0] + 1;
        }
        for (int i = 2; i < len; i++) { // 设置第二个状态的值
            if (chars[i] == 'r') {
                dp[i][1] = Math.min(dp[i - 1][1], dp[i - 1][0]) + 1;
            } else {
                dp[i][1] = Math.min(dp[i - 1][1], dp[i - 1][0]);
            }
        }
        if (chars[2] == 'r') {
            dp[2][2] = dp[1][1]; // 设置第三个状态的值
        } else {
            dp[2][2] = dp[1][1] + 1;
        }
        for (int i = 3; i < len; i++) { // 设置第三个状态的初值
            if (chars[i] == 'r') {
                dp[i][2] = Math.min(dp[i - 1][1], dp[i - 1][2]);
            } else {
                dp[i][2] = Math.min(dp[i - 1][1], dp[i - 1][2]) + 1;
            }
        }

        return dp[len - 1][2];
    }

    @Test
    public void reorderSpaces() {
        String text = "   a";
        logResult(reorderSpaces(text));
    }

    /**
     * 5519. 重新排列单词间的空格
     *
     * <p>给你一个字符串 text ，该字符串由若干被空格包围的单词组成。每个单词由一个或者多个小写英文字母组成，并且两个单词之间至少存在一个空格。题目测试用例保证 text
     * 至少包含一个单词 。
     *
     * <p>请你重新排列空格，使每对相邻单词之间的空格数目都 相等 ，并尽可能 最大化 该数目。如果不能重新平均分配所有空格，请 将多余的空格放置在字符串末尾 ，这也意味着返回的字符串应当与原
     * text 字符串的长度相等。
     *
     * <p>返回 重新排列空格后的字符串 。
     *
     * <p>示例 1：
     *
     * <p>输入：text = " this is a sentence " 输出："this is a sentence" 解释：总共有 9 个空格和 4 个单词。可以将 9
     * 个空格平均分配到相邻单词之间，相邻单词间空格数为：9 / (4-1) = 3 个。 示例 2：
     *
     * <p>输入：text = " practice makes perfect" 输出："practice makes perfect " 解释：总共有 7 个空格和 3 个单词。7 /
     * (3-1) = 3 个空格加上 1 个多余的空格。多余的空格需要放在字符串的末尾。 示例 3：
     *
     * <p>输入：text = "hello world" 输出："hello world" 示例 4：
     *
     * <p>输入：text = " walks udp package into bar a" 输出："walks udp package into bar a " 示例 5：
     *
     * <p>输入：text = "a" 输出："a"
     *
     * <p>提示：
     *
     * <p>1 <= text.length <= 100 text 由小写英文字母和 ' ' 组成 text 中至少包含一个单词
     *
     * @param text
     * @return
     */
    public String reorderSpaces(String text) {
        int count = 0;
        for (char c : text.toCharArray()) {
            if (c == ' ') {
                count++;
            }
        }
        String[] strings = text.split(" ");
        List<String> list =
                Arrays.stream(strings).filter(s -> s.length() > 0).collect(Collectors.toList());
        logResult(list);
        int len = list.size();
        int num = len == 1 ? 0 : count / (len - 1);
        int left = count - num * (len - 1);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len; i++) {
            sb.append(list.get(i));
            int blankLen = i == len - 1 ? left : num;
            for (int j = 0; j < blankLen; j++) {
                sb.append(' ');
            }
        }
        return sb.toString();
    }

    @Test
    public void isScramble() {
        String s1 = "great", s2 = "eatrg";
        logResult(isScramble(s1, s2));
    }

    /**
     * 87. 扰乱字符串
     *
     * <p>给定一个字符串 s1，我们可以把它递归地分割成两个非空子字符串，从而将其表示为二叉树。
     *
     * <p>下图是字符串 s1 = "great" 的一种可能的表示形式。
     *
     * <p>great / \ gr eat / \ / \ g r e at / \ a t 在扰乱这个字符串的过程中，我们可以挑选任何一个非叶节点，然后交换它的两个子节点。
     *
     * <p>例如，如果我们挑选非叶节点 "gr" ，交换它的两个子节点，将会产生扰乱字符串 "rgeat" 。
     *
     * <p>rgeat / \ rg eat / \ / \ r g e at / \ a t 我们将 "rgeat” 称作 "great" 的一个扰乱字符串。
     *
     * <p>同样地，如果我们继续交换节点 "eat" 和 "at" 的子节点，将会产生另一个新的扰乱字符串 "rgtae" 。
     *
     * <p>rgtae / \ rg tae / \ / \ r g ta e / \ t a 我们将 "rgtae” 称作 "great" 的一个扰乱字符串。
     *
     * <p>给出两个长度相等的字符串 s1 和 s2，判断 s2 是否是 s1 的扰乱字符串。
     *
     * <p>示例 1:
     *
     * <p>输入: s1 = "great", s2 = "rgeat" 输出: true 示例 2:
     *
     * <p>输入: s1 = "abcde", s2 = "caebd" 输出: false
     *
     * @param s1
     * @param s2
     * @return
     */
    public boolean isScramble(String s1, String s2) {
        scrambleChars1 = s1.toCharArray();
        scrambleChars2 = s2.toCharArray();
        return isScramble(0, 0, s1.length());
    }

    char[] scrambleChars1, scrambleChars2;

    private boolean isScramble(int start1, int start2, int len) {
        if (start1 + len > scrambleChars1.length || start2 + len > scrambleChars2.length) {
            return false;
        }
        int sameNum = 0;
        int[] letters = new int[26];
        for (int i = 0; i < len; i++) {
            char c1 = scrambleChars1[start1 + i], c2 = scrambleChars2[start2 + i];
            if (c1 == c2) {
                sameNum++;
            }
            letters[c1 - 'a']++;
            letters[c2 - 'a']--;
        }
        if (sameNum == len) {
            return true;
        }
        for (int i = 0; i < 26; i++) {
            if (letters[i] != 0) {
                return false;
            }
        }

        for (int i = 1; i < len; i++) {
            if (isScramble(start1, start2, i) && isScramble(start1 + i, start2 + i, len - i)) {
                return true;
            }
            if (isScramble(start1, start2 + len - i, i)
                    && isScramble(start1 + i, start2, len - i)) {
                return true;
            }
        }

        return false;
    }

    /**
     * 5523. 文件夹操作日志搜集器
     *
     * <p>每当用户执行变更文件夹操作时，LeetCode 文件系统都会保存一条日志记录。
     *
     * <p>下面给出对变更操作的说明：
     *
     * <p>"../" ：移动到当前文件夹的父文件夹。如果已经在主文件夹下，则 继续停留在当前文件夹 。 "./" ：继续停留在当前文件夹。 "x/" ：移动到名为 x 的子文件夹中。题目数据
     * 保证总是存在文件夹 x 。 给你一个字符串列表 logs ，其中 logs[i] 是用户在 ith 步执行的操作。
     *
     * <p>文件系统启动时位于主文件夹，然后执行 logs 中的操作。
     *
     * <p>执行完所有变更文件夹操作后，请你找出 返回主文件夹所需的最小步数 。
     *
     * <p>示例 1：
     *
     * <p>输入：logs = ["d1/","d2/","../","d21/","./"] 输出：2 解释：执行 "../" 操作变更文件夹 2 次，即可回到主文件夹 示例 2：
     *
     * <p>输入：logs = ["d1/","d2/","./","d3/","../","d31/"] 输出：3 示例 3：
     *
     * <p>输入：logs = ["d1/","../","../","../"] 输出：0
     *
     * <p>提示：
     *
     * <p>1 <= logs.length <= 103 2 <= logs[i].length <= 10 logs[i] 包含小写英文字母，数字，'.' 和 '/' logs[i]
     * 符合语句中描述的格式 文件夹名称由小写英文字母和数字组成
     *
     * @param logs
     * @return
     */
    public int minOperations(String[] logs) {
        int num = 0;
        for (String log : logs) {
            if (Objects.equals(log, "../")) {
                if (num > 0) {
                    num--;
                }
            } else if (!Objects.equals(log, "./")) {
                num++;
            }
        }
        return Math.abs(num);
    }

    /**
     * 791. 自定义字符串排序
     *
     * <p>字符串S和 T 只包含小写字符。在S中，所有字符只会出现一次。
     *
     * <p>S 已经根据某种规则进行了排序。我们要根据S中的字符顺序对T进行排序。更具体地说，如果S中x在y之前出现，那么返回的字符串中x也应出现在y之前。
     *
     * <p>返回任意一种符合条件的字符串T。
     *
     * <p>示例: 输入: S = "cba" T = "abcd" 输出: "cbad" 解释: S中出现了字符 "a", "b", "c", 所以 "a", "b", "c" 的顺序应该是
     * "c", "b", "a". 由于 "d" 没有在S中出现, 它可以放在T的任意位置. "dcba", "cdba", "cbda" 都是合法的输出。 注意:
     *
     * <p>S的最大长度为26，其中没有重复的字符。 T的最大长度为200。 S和T只包含小写字符。
     *
     * @param S
     * @param T
     * @return
     */
    public String customSortString(String S, String T) {
        StringBuilder sb = new StringBuilder();
        // 计数排序
        int[] letters = new int[26];
        for (char c : T.toCharArray()) {
            letters[c - 'a']++;
        }
        for (char c : S.toCharArray()) {
            for (int i = 0; i < letters[c - 'a']; i++) {
                sb.append(c);
            }
            letters[c - 'a'] = 0;
        }
        for (char c = 'a'; c <= 'z'; ++c) {
            for (int i = 0; i < letters[c - 'a']; ++i) {
                sb.append(c);
            }
        }

        return sb.toString();
    }

    @Test
    public void findReplaceString() {
        String S = "abc";
        int[] indexes = {0, 2};
        String[] sources = {"ab", "cd"}, targets = {"eee", "ffff"};
        logResult(findReplaceString(S, indexes, sources, targets));
    }

    /**
     * 833. 字符串中的查找与替换
     *
     * <p>对于某些字符串 S，我们将执行一些替换操作，用新的字母组替换原有的字母组（不一定大小相同）。
     *
     * <p>每个替换操作具有 3 个参数：起始索引 i，源字 x 和目标字 y。规则是如果 x 从原始字符串 S 中的位置 i 开始，那么我们将用 y 替换出现的
     * x。如果没有，我们什么都不做。
     *
     * <p>举个例子，如果我们有 S = “abcd” 并且我们有一些替换操作 i = 2，x = “cd”，y = “ffff”，那么因为 “cd” 从原始字符串 S 中的位置 2
     * 开始，我们将用 “ffff” 替换它。
     *
     * <p>再来看 S = “abcd” 上的另一个例子，如果我们有替换操作 i = 0，x = “ab”，y = “eee”，以及另一个替换操作 i = 2，x = “ec”，y =
     * “ffff”，那么第二个操作将不执行任何操作，因为原始字符串中 S[2] = 'c'，与 x[0] = 'e' 不匹配。
     *
     * <p>所有这些操作同时发生。保证在替换时不会有任何重叠： S = "abc", indexes = [0, 1], sources = ["ab","bc"] 不是有效的测试用例。
     *
     * <p>示例 1：
     *
     * <p>输入：S = "abcd", indexes = [0,2], sources = ["a","cd"], targets = ["eee","ffff"]
     * 输出："eeebffff" 解释： "a" 从 S 中的索引 0 开始，所以它被替换为 "eee"。 "cd" 从 S 中的索引 2 开始，所以它被替换为 "ffff"。 示例 2：
     *
     * <p>输入：S = "abcd", indexes = [0,2], sources = ["ab","ec"], targets = ["eee","ffff"] 输出："eeecd"
     * 解释： "ab" 从 S 中的索引 0 开始，所以它被替换为 "eee"。 "ec" 没有从原始的 S 中的索引 2 开始，所以它没有被替换。
     *
     * <p>提示：
     *
     * <p>0 <= indexes.length = sources.length = targets.length <= 100 0 < indexes[i] < S.length <=
     * 1000 给定输入中的所有字符都是小写字母。
     *
     * @param S
     * @param indexes
     * @param sources
     * @param targets
     * @return
     */
    public String findReplaceString(String S, int[] indexes, String[] sources, String[] targets) {

        StringBuffer sb = new StringBuffer();
        int len = S.length();
        int[] match = new int[len];
        Arrays.fill(match, -1);
        for (int i = 0; i < indexes.length; i++) {
            int idx = indexes[i];
            if (S.substring(idx, idx + sources[i].length()).equals(sources[i])) {
                match[idx] = i;
            }
        }

        int idx = 0;
        while (idx < len) {
            if (match[idx] >= 0) {
                sb.append(targets[match[idx]]);
                idx += sources[match[idx]].length();
            } else {
                sb.append(S.charAt(idx++));
            }
        }
        return sb.toString();
    }

    @Test
    public void expressiveWords() {
        String S = "abcd";
        String[] words = {"abc", "hi", "helo"};
        logResult(expressiveWords(S, words));
    }

    /**
     * 809. 情感丰富的文字
     *
     * <p>有时候人们会用重复写一些字母来表示额外的感受，比如 "hello" -> "heeellooo", "hi" ->
     * "hiii"。我们将相邻字母都相同的一串字符定义为相同字母组，例如："h", "eee", "ll", "ooo"。
     *
     * <p>对于一个给定的字符串 S ，如果另一个单词能够通过将一些字母组扩张从而使其和 S 相同，我们将这个单词定义为可扩张的（stretchy）。扩张操作定义如下：选择一个字母组（包含字母
     * c ），然后往其中添加相同的字母 c 使其长度达到 3 或以上。
     *
     * <p>例如，以 "hello" 为例，我们可以对字母组 "o" 扩张得到 "hellooo"，但是无法以同样的方法得到 "helloo" 因为字母组 "oo" 长度小于
     * 3。此外，我们可以进行另一种扩张 "ll" -> "lllll" 以获得 "helllllooo"。如果 S = "helllllooo"，那么查询词 "hello"
     * 是可扩张的，因为可以对它执行这两种扩张操作使得 query = "hello" -> "hellooo" -> "helllllooo" = S。
     *
     * <p>输入一组查询单词，输出其中可扩张的单词数量。
     *
     * <p>示例：
     *
     * <p>输入： S = "heeellooo" words = ["hello", "hi", "helo"] 输出：1 解释： 我们能通过扩张 "hello" 的 "e" 和 "o"
     * 来得到 "heeellooo"。 我们不能通过扩张 "helo" 来得到 "heeellooo" 因为 "ll" 的长度小于 3 。
     *
     * <p>说明：
     *
     * <p>0 <= len(S) <= 100。 0 <= len(words) <= 100。 0 <= len(words[i]) <= 100。 S 和所有在 words
     * 中的单词都只由小写字母组成。
     *
     * @param S
     * @param words
     * @return
     */
    public int expressiveWords(String S, String[] words) {

        int count = 0;

        for (String word : words) {
            if (canExpressiveWord(S, word)) {
                count++;
            }
        }

        return count;
    }

    private boolean canExpressiveWord(String s, String word) {
        int i = 0, j = 0;
        while (i < s.length() && j < word.length()) {
            char c1 = s.charAt(i), c2 = word.charAt(j);
            if (c1 != c2) {
                return false;
            }
            int start1 = i++;
            int start2 = j++;
            while (i < s.length() && s.charAt(i) == c1) {
                i++;
            }
            while (j < word.length() && word.charAt(j) == c2) {
                j++;
            }
            if (i - start1 == j - start2) {
                continue;
            }
            if (j - start2 > i - start1 || i - start1 < 3) {
                return false;
            }
        }
        return i == s.length() && j == word.length();
    }

    @Test
    public void ambiguousCoordinates() {
        String S = "(123)";
        logResult(ambiguousCoordinates(S));
    }

    /**
     * 816. 模糊坐标
     *
     * <p>我们有一些二维坐标，如 "(1, 3)" 或 "(2, 0.5)"，然后我们移除所有逗号，小数点和空格，得到一个字符串S。返回所有可能的原始字符串到一个列表中。
     *
     * <p>原始的坐标表示法不会存在多余的零，所以不会出现类似于"00", "0.0", "0.00", "1.0", "001",
     * "00.01"或一些其他更小的数来表示坐标。此外，一个小数点前至少存在一个数，所以也不会出现“.1”形式的数字。
     *
     * <p>最后返回的列表可以是任意顺序的。而且注意返回的两个数字中间（逗号之后）都有一个空格。
     *
     * <p>示例 1: 输入: "(123)" 输出: ["(1, 23)", "(12, 3)", "(1.2, 3)", "(1, 2.3)"] 示例 2: 输入: "(00011)"
     * 输出: ["(0.001, 1)", "(0, 0.011)"] 解释: 0.0, 00, 0001 或 00.01 是不被允许的。 示例 3: 输入: "(0123)" 输出:
     * ["(0, 123)", "(0, 12.3)", "(0, 1.23)", "(0.1, 23)", "(0.1, 2.3)", "(0.12, 3)"] 示例 4: 输入:
     * "(100)" 输出: [(10, 0)] 解释: 1.0 是不被允许的。
     *
     * <p>提示:
     *
     * <p>4 <= S.length <= 12. S[0] = "(", S[S.length - 1] = ")", 且字符串 S 中的其他元素都是数字。
     *
     * @param S
     * @return
     */
    public List<String> ambiguousCoordinates(String S) {
        List<String> result = new ArrayList<>();
        int len = S.length();

        for (int i = 2; i < len - 1; i++) {
            for (String left : getCoordinates(S.substring(1, i))) {
                for (String right : getCoordinates(S.substring(i, len - 1))) {
                    result.add("(" + left + ", " + right + ")");
                }
            }
        }

        return result;
    }

    private List<String> getCoordinates(String s) {
        List<String> list = new ArrayList<>();
        if (s.length() == 1) {
            list.add(s);
            return list;
        }
        if (s.startsWith("0")) {
            if (!s.endsWith("0")) {
                list.add("0." + s.substring(1));
            }
            return list;
        }
        if (s.endsWith("0")) {
            list.add(s);
            return list;
        }
        list.add(s);
        for (int i = 1; i < s.length(); i++) {
            list.add(s.substring(0, i) + "." + s.substring(i));
        }

        return list;
    }

    @Test
    public void maskPII() {
        String S = "1(234)567-890";
        logResult(maskPII(S));
    }

    /**
     * 831. 隐藏个人信息
     *
     * <p>给你一条个人信息字符串 S，它可能是一个 邮箱地址 ，也可能是一串 电话号码 。
     *
     * <p>我们将隐藏它的隐私信息，通过如下规则:
     *
     * <p>1. 电子邮箱
     *
     * <p>定义名称 name 是长度大于等于 2 （length ≥ 2），并且只包含小写字母 a-z 和大写字母 A-Z 的字符串。
     *
     * <p>电子邮箱地址由名称 name 开头，紧接着是符号 '@'，后面接着一个名称 name，再接着一个点号 '.'，然后是一个名称 name。
     *
     * <p>电子邮箱地址确定为有效的，并且格式是 "name1@name2.name3"。
     *
     * <p>为了隐藏电子邮箱，所有的名称 name 必须被转换成小写的，并且第一个名称 name 的第一个字母和最后一个字母的中间的所有字母由 5 个 '*' 代替。
     *
     * <p>2. 电话号码
     *
     * <p>电话号码是一串包括数字 0-9，以及 {'+', '-', '(', ')', ' '} 这几个字符的字符串。你可以假设电话号码包含 10 到 13 个数字。
     *
     * <p>电话号码的最后 10 个数字组成本地号码，在这之前的数字组成国际号码。注意，国际号码是可选的。我们只暴露最后 4 个数字并隐藏所有其他数字。
     *
     * <p>本地号码是有格式的，并且如 "***-***-1111" 这样显示，这里的 1 表示暴露的数字。
     *
     * <p>为了隐藏有国际号码的电话号码，像 "+111 111 111 1111"，我们以 "+***-***-***-1111" 的格式来显示。在本地号码前面的 '+' 号和第一个 '-'
     * 号仅当电话号码中包含国际号码时存在。例如，一个 12 位的电话号码应当以 "+**-" 开头进行显示。
     *
     * <p>注意：像 "("，")"，" " 这样的不相干的字符以及不符合上述格式的额外的减号或者加号都应当被删除。
     *
     * <p>最后，将提供的信息正确隐藏后返回。
     *
     * <p>示例 1：
     *
     * <p>输入: "LeetCode@LeetCode.com" 输出: "l*****e@leetcode.com" 解释： 所有的名称转换成小写,
     * 第一个名称的第一个字符和最后一个字符中间由 5 个星号代替。 因此，"leetcode" -> "l*****e"。 示例 2：
     *
     * <p>输入: "AB@qq.com" 输出: "a*****b@qq.com" 解释: 第一个名称"ab"的第一个字符和最后一个字符的中间必须有 5 个星号 因此，"ab" ->
     * "a*****b"。 示例 3：
     *
     * <p>输入: "1(234)567-890" 输出: "***-***-7890" 解释: 10 个数字的电话号码，那意味着所有的数字都是本地号码。 示例 4：
     *
     * <p>输入: "86-(10)12345678" 输出: "+**-***-***-5678" 解释: 12 位数字，2 个数字是国际号码另外 10 个数字是本地号码 。
     *
     * <p>注意:
     *
     * <p>S.length <= 40。 邮箱的长度至少是 8。 电话号码的长度至少是 10。
     *
     * @param S
     * @return
     */
    public String maskPII(String S) {
        // 方法一：模拟
        // 我们首先判断 S 是邮箱还是电话号码。显然，如果 S 中有字符 '@'，那么它是邮箱，否则它是电话号码。
        //
        // 如果 S 是邮箱，我们将 S 的 '@' 之前的部分保留第一个和最后一个字符，中间用 '*****' 代替，并将整个字符串转换为小写。
        //
        // 如果 S 是电话号码，我们只保留 S 中的所有数字。首先将最后 10 位本地号码变成 '***-***-abcd' 的形式，再判断 S
        // 中是否有额外的国际号码。如果有，则将国际号码之前添加 '+' 号并加到本地号码的最前端。
        int idx = S.indexOf("@");
        StringBuilder sb = new StringBuilder();
        if (idx > 0) {
            sb.append(Character.toLowerCase(S.charAt(0)))
                    .append("*****")
                    .append(S.substring(idx - 1).toLowerCase());
        } else {
            String digits = S.replaceAll("\\D+", "");
            log.debug("digits :{}", digits);
            if (digits.length() > 10) {
                sb.append("+");
                for (int i = 0; i < digits.length() - 10; i++) {
                    sb.append("*");
                }
                sb.append("-");
            }
            sb.append("***-***-").append(digits.substring(digits.length() - 4).toLowerCase());
        }
        return sb.toString();
    }

    @Test
    public void checkPalindromeFormation() {
        String a = "ulacfd", b = "jizzlu";
        logResult(checkPalindromeFormation(a, b));
    }

    /**
     * 5537. 分割两个字符串得到回文串
     *
     * <p>给你两个字符串 a 和 b ，它们长度相同。请你选择一个下标，将两个字符串都在 相同的下标 分割开。由 a 可以得到两个字符串： aprefix 和 asuffix ，满足 a =
     * aprefix + asuffix ，同理，由 b 可以得到两个字符串 bprefix 和 bsuffix ，满足 b = bprefix + bsuffix 。请你判断 aprefix
     * + bsuffix 或者 bprefix + asuffix 能否构成回文串。
     *
     * <p>当你将一个字符串 s 分割成 sprefix 和 ssuffix 时， ssuffix 或者 sprefix 可以为空。比方说， s = "abc" 那么 "" + "abc" ，
     * "a" + "bc" ， "ab" + "c" 和 "abc" + "" 都是合法分割。
     *
     * <p>如果 能构成回文字符串 ，那么请返回 true，否则返回 false 。
     *
     * <p>请注意， x + y 表示连接字符串 x 和 y 。
     *
     * <p>示例 1：
     *
     * <p>输入：a = "x", b = "y" 输出：true 解释：如果 a 或者 b 是回文串，那么答案一定为 true ，因为你可以如下分割： aprefix = "",
     * asuffix = "x" bprefix = "", bsuffix = "y" 那么 aprefix + bsuffix = "" + "y" = "y" 是回文串。 示例 2：
     *
     * <p>输入：a = "ulacfd", b = "jizalu" 输出：true 解释：在下标为 3 处分割： aprefix = "ula", asuffix = "cfd"
     * bprefix = "jiz", bsuffix = "alu" 那么 aprefix + bsuffix = "ula" + "alu" = "ulaalu" 是回文串。
     *
     * <p>提示：
     *
     * <p>1 <= a.length, b.length <= 105 a.length == b.length a 和 b 都只包含小写英文字母
     *
     * @param a
     * @param b
     * @return
     */
    public boolean checkPalindromeFormation(String a, String b) {
        if (checkPalindrome(a) || checkPalindrome(b)) {
            return true;
        }
        int len = a.length();
        int left = 0, right = len - 1;
        char[] chars1 = a.toCharArray(), chars2 = b.toCharArray();
        // 判断 aprefix + bsuffix
        while (left < right) {
            if (chars1[left] != chars2[right]) {
                break;
            }
            left++;
            right--;
        }
        // 判断a, b中间是否是回文
        while (left < right) {
            if (chars1[left] != chars1[right] && chars2[left] != chars2[right]) {
                break;
            }
            left++;
            right--;
        }
        if (left >= right) {
            return true;
        }
        left = 0;
        right = len - 1;
        // 判断 bprefix + asuffix
        while (left < right) {
            if (chars2[left] != chars1[right]) {
                break;
            }
            left++;
            right--;
        }
        // 判断a, b中间是否是回文
        while (left < right) {
            if (chars1[left] != chars1[right] && chars2[left] != chars2[right]) {
                break;
            }
            left++;
            right--;
        }
        return left >= right;
    }

    @Test
    public void shiftingLetters() {
        String S = "z";
        int[] shift = {52};
        logResult(shiftingLetters(S, shift));
    }

    /**
     * 848. 字母移位
     *
     * <p>有一个由小写字母组成的字符串 S，和一个整数数组 shifts。
     *
     * <p>我们将字母表中的下一个字母称为原字母的 移位（由于字母表是环绕的， 'z' 将会变成 'a'）。
     *
     * <p>例如·，shift('a') = 'b'， shift('t') = 'u',， 以及 shift('z') = 'a'。
     *
     * <p>对于每个 shifts[i] = x ， 我们会将 S 中的前 i+1 个字母移位 x 次。
     *
     * <p>返回将所有这些移位都应用到 S 后最终得到的字符串。
     *
     * <p>示例：
     *
     * <p>输入：S = "abc", shifts = [3,5,9] 输出："rpl" 解释： 我们以 "abc" 开始。 将 S 中的第 1 个字母移位 3 次后，我们得到 "dbc"。
     * 再将 S 中的前 2 个字母移位 5 次后，我们得到 "igc"。 最后将 S 中的这 3 个字母移位 9 次后，我们得到答案 "rpl"。 提示：
     *
     * <p>1 <= S.length = shifts.length <= 20000 0 <= shifts[i] <= 10 ^ 9
     *
     * @param S
     * @param shifts
     * @return
     */
    public String shiftingLetters(String S, int[] shifts) {
        int len = shifts.length;
        char[] chars = S.toCharArray();
        int sum = 0;
        for (int i = len - 1; i >= 0; i--) {
            sum += shifts[i] % 26;
            shifts[i] = sum % 26;
        }
        for (int i = 0; i < len; i++) {
            int num = chars[i] + shifts[i];
            if (num > 'z') {
                num -= 26;
            }
            chars[i] = (char) num;
        }

        return new String(chars);
    }

    @Test
    public void findLexSmallestString() {
        String s = "192804";
        int a = 8, b = 5;
        logResult(findLexSmallestString(s, a, b));
    }

    /**
     * 1625. 执行操作后字典序最小的字符串
     *
     * <p>给你一个字符串 s 以及两个整数 a 和 b 。其中，字符串 s 的长度为偶数，且仅由数字 0 到 9 组成。
     *
     * <p>你可以在 s 上按任意顺序多次执行下面两个操作之一：
     *
     * <p>累加：将 a 加到 s 中所有下标为奇数的元素上（下标从 0 开始）。数字一旦超过 9 就会变成 0，如此循环往复。例如，s = "3456" 且 a = 5，则执行此操作后 s
     * 变成 "3951"。 轮转：将 s 向右轮转 b 位。例如，s = "3456" 且 b = 1，则执行此操作后 s 变成 "6345"。 请你返回在 s
     * 上执行上述操作任意次后可以得到的 字典序最小 的字符串。
     *
     * <p>如果两个字符串长度相同，那么字符串 a 字典序比字符串 b 小可以这样定义：在 a 和 b 出现不同的第一个位置上，字符串 a 中的字符出现在字母表中的时间早于 b
     * 中的对应字符。例如，"0158” 字典序比 "0190" 小，因为不同的第一个位置是在第三个字符，显然 '5' 出现在 '9' 之前。
     *
     * <p>示例 1：
     *
     * <p>输入：s = "5525", a = 9, b = 2 输出："2050" 解释：执行操作如下： 初态："5525" 轮转："2555" 累加："2454" 累加："2353"
     * 轮转："5323" 累加："5222" 累加："5121" 轮转："2151" 累加："2050" 无法获得字典序小于 "2050" 的字符串。 示例 2：
     *
     * <p>输入：s = "74", a = 5, b = 1 输出："24" 解释：执行操作如下： 初态："74" 轮转："47" 累加："42" 轮转："24"​​​​​​​​​​​​
     * 无法获得字典序小于 "24" 的字符串。 示例 3：
     *
     * <p>输入：s = "0011", a = 4, b = 2 输出："0011" 解释：无法获得字典序小于 "0011" 的字符串。 示例 4：
     *
     * <p>输入：s = "43987654", a = 7, b = 3 输出："00553311"
     *
     * <p>提示：
     *
     * <p>2 <= s.length <= 100 s.length 是偶数 s 仅由数字 0 到 9 组成 1 <= a <= 9 1 <= b <= s.length - 1
     *
     * @param s
     * @param a
     * @param b
     * @return
     */
    public String findLexSmallestString(String s, int a, int b) {
        char[] chars = s.toCharArray();
        int len = chars.length;
        // 获取 len 和 b 的 最大公约数
        int step = getGcd(len, b);

        List<Integer> indexs = new ArrayList<>();
        List<Integer> counts = new ArrayList<>();
        int minStart = 10;
        // 按 step 判断每一位 最小值
        boolean oddNum = (step & 1) == 1;
        for (int i = 0; i < len; i += step) {
            int num = chars[i] - '0';
            int low = num, lowCount = 0;
            if (oddNum) {
                // step 是奇数, 需要考虑 + a 得到的最小值
                for (int j = 1; j < 10; j++) {
                    num += a;
                    num %= 10;
                    if (num < low) {
                        low = num;
                        lowCount = j;
                    }
                }
            }

            if (low < minStart) {
                minStart = low;
                indexs = new ArrayList<>();
                counts = new ArrayList<>();
                indexs.add(i);
                counts.add(lowCount);
            } else if (low == minStart) {
                indexs.add(i);
                counts.add(lowCount);
            }
        }

        return getLexSmallestString(s, a, indexs, counts);
    }

    private String getLexSmallestString(
            String s, int a, List<Integer> indexs, List<Integer> counts) {
        String str = s + s;
        int len = s.length(), size = indexs.size();
        int index = indexs.get(0), count = counts.get(0);
        String result = addLex(str.substring(index, index + len), a, count);

        for (int i = 1; i < size; i++) {
            index = indexs.get(i);
            count = counts.get(i);
            String s1 = addLex(str.substring(index, index + len), a, count);
            if (s1.compareTo(result) < 0) {
                result = s1;
            }
        }

        return result;
    }

    private String addLex(String s, int a, int count) {
        int len = s.length();
        char[] chars = s.toCharArray();
        if (count > 0) {
            // 偶数位加count个 a
            for (int i = 0; i < len; i += 2) {
                int num = (chars[i] - '0' + count * a) % 10;
                chars[i] = (char) ('0' + num);
            }
        }
        // 第二位最小
        int num = chars[1] - '0';
        int low = num, lowCount = 0;
        // step 是奇数, 需要考虑 + a 得到的最小值
        for (int j = 1; j < 10; j++) {
            num += a;
            num %= 10;
            if (num < low) {
                low = num;
                lowCount = j;
            }
        }
        // 奇数位加 lowCount 个 a
        for (int i = 1; i < len; i += 2) {
            num = (chars[i] - '0' + lowCount * a) % 10;
            chars[i] = (char) ('0' + num);
        }
        return new String(chars);
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

    /**
     * 1624. 两个相同字符之间的最长子字符串
     *
     * <p>给你一个字符串 s，请你返回 两个相同字符之间的最长子字符串的长度 ，计算长度时不含这两个字符。如果不存在这样的子字符串，返回 -1 。
     *
     * <p>子字符串 是字符串中的一个连续字符序列。
     *
     * <p>示例 1：
     *
     * <p>输入：s = "aa" 输出：0 解释：最优的子字符串是两个 'a' 之间的空子字符串。 示例 2：
     *
     * <p>输入：s = "abca" 输出：2 解释：最优的子字符串是 "bc" 。 示例 3：
     *
     * <p>输入：s = "cbzxy" 输出：-1 解释：s 中不存在出现出现两次的字符，所以返回 -1 。 示例 4：
     *
     * <p>输入：s = "cabbac" 输出：4 解释：最优的子字符串是 "abba" ，其他的非最优解包括 "bb" 和 "" 。
     *
     * <p>提示：
     *
     * <p>1 <= s.length <= 300 s 只含小写英文字母
     *
     * @param s
     * @return
     */
    public int maxLengthBetweenEqualCharacters(String s) {
        int max = -1;
        int[] indexs = new int[26];
        Arrays.fill(indexs, -1);
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (indexs[c - 'a'] >= 0) {
                max = Math.max(max, i - indexs[c - 'a']);
            }
        }

        return max;
    }

    /**
     * 5546. 按键持续时间最长的键
     *
     * <p>LeetCode 设计了一款新式键盘，正在测试其可用性。测试人员将会点击一系列键（总计 n 个），每次一个。
     *
     * <p>给你一个长度为 n 的字符串 keysPressed ，其中 keysPressed[i] 表示测试序列中第 i 个被按下的键。releaseTimes 是一个升序排列的列表，其中
     * releaseTimes[i] 表示松开第 i 个键的时间。字符串和数组的 下标都从 0 开始 。第 0 个键 keysPressed[0] 在时间为 0 时被按下，接下来每个键都 恰好
     * 在前一个键松开时被按下。
     *
     * <p>测试人员想要找出按键 持续时间最长 的键。第 i 次按键的持续时间为 releaseTimes[i] - releaseTimes[i - 1] ，第 0 次按键的持续时间为
     * releaseTimes[0] 。
     *
     * <p>注意，测试期间，同一个键可以在不同时刻被多次按下，而每次的持续时间都可能不同。
     *
     * <p>请返回按键 持续时间最长 的键，如果有多个这样的键，则返回 按字母顺序排列最大 的那个键。
     *
     * <p>示例 1：
     *
     * <p>输入：releaseTimes = [9,29,49,50], keysPressed = "cbcd" 输出："c" 解释：按键顺序和持续时间如下： 按下 'c' ，持续时间
     * 9（时间 0 按下，时间 9 松开） 按下 'b' ，持续时间 29 - 9 = 20（松开上一个键的时间 9 按下，时间 29 松开） 按下 'c' ，持续时间 49 - 29 =
     * 20（松开上一个键的时间 29 按下，时间 49 松开） 按下 'd' ，持续时间 50 - 49 = 1（松开上一个键的时间 49 按下，时间 50 松开） 按键持续时间最长的键是
     * 'b' 和 'c'（第二次按下时），持续时间都是 20 'c' 按字母顺序排列比 'b' 大，所以答案是 'c' 示例 2：
     *
     * <p>输入：releaseTimes = [12,23,36,46,62], keysPressed = "spuda" 输出："a" 解释：按键顺序和持续时间如下： 按下 's'
     * ，持续时间 12 按下 'p' ，持续时间 23 - 12 = 11 按下 'u' ，持续时间 36 - 23 = 13 按下 'd' ，持续时间 46 - 36 = 10 按下 'a'
     * ，持续时间 62 - 46 = 16 按键持续时间最长的键是 'a' ，持续时间 16
     *
     * <p>提示：
     *
     * <p>releaseTimes.length == n keysPressed.length == n 2 <= n <= 1000 0 <= releaseTimes[i] <=
     * 109 releaseTimes[i] < releaseTimes[i+1] keysPressed 仅由小写英文字母组成
     *
     * @param releaseTimes
     * @param keysPressed
     * @return
     */
    public char slowestKey(int[] releaseTimes, String keysPressed) {
        int max = releaseTimes[0];
        char result = keysPressed.charAt(0);
        for (int i = 1; i < keysPressed.length(); i++) {
            int time = releaseTimes[i] - releaseTimes[i - 1];
            char c = keysPressed.charAt(i);
            if (time > max) {
                max = time;
                result = c;
            } else if (time == max && c > result) {
                result = c;
            }
        }

        return result;
    }

    @Test
    public void decodeAtIndex() {
        // "y959q969u3hb22odq595"
        // 222280369
        String s = "leet2code3";
        int k = 10;
        logResult(decodeAtIndex(s, k));
    }

    /**
     * 880. 索引处的解码字符串
     *
     * <p>给定一个编码字符串 S。请你找出 解码字符串 并将其写入磁带。解码时，从编码字符串中 每次读取一个字符 ，并采取以下步骤：
     *
     * <p>如果所读的字符是字母，则将该字母写在磁带上。 如果所读的字符是数字（例如 d），则整个当前磁带总共会被重复写 d-1 次。 现在，对于给定的编码字符串 S 和索引
     * K，查找并返回解码字符串中的第 K 个字母。
     *
     * <p>示例 1：
     *
     * <p>输入：S = "leet2code3", K = 10 输出："o" 解释： 解码后的字符串为 "leetleetcodeleetleetcodeleetleetcode"。
     * 字符串中的第 10 个字母是 "o"。 示例 2：
     *
     * <p>输入：S = "ha22", K = 5 输出："h" 解释： 解码后的字符串为 "hahahaha"。第 5 个字母是 "h"。 示例 3：
     *
     * <p>输入：S = "a2345678999999999999999", K = 1 输出："a" 解释： 解码后的字符串为 "a" 重复 8301530446056247680 次。第
     * 1 个字母是 "a"。
     *
     * <p>提示：
     *
     * <p>2 <= S.length <= 100 S 只包含小写字母与数字 2 到 9 。 S 以字母开头。 1 <= K <= 10^9 题目保证 K 小于或等于解码字符串的长度。
     * 解码后的字符串保证少于 2^63 个字母。
     *
     * @param S
     * @param K
     * @return
     */
    public String decodeAtIndex(String S, int K) {
        int size = 0;

        for (char c : S.toCharArray()) {

            if (Character.isDigit(c)) {
                int count = c - '0';
                if ((K - 1) / count >= size) {
                    size *= count;
                } else {
                    return decodeAtIndex(S, (K - 1) % size + 1);
                }

            } else {
                size++;
                if (K == size) {
                    return String.valueOf(c);
                }
            }
        }

        return "";
    }

    /**
     * 890. 查找和替换模式
     *
     * <p>你有一个单词列表 words 和一个模式 pattern，你想知道 words 中的哪些单词与模式匹配。
     *
     * <p>如果存在字母的排列 p ，使得将模式中的每个字母 x 替换为 p(x) 之后，我们就得到了所需的单词，那么单词与模式是匹配的。
     *
     * <p>（回想一下，字母的排列是从字母到字母的双射：每个字母映射到另一个字母，没有两个字母映射到同一个字母。）
     *
     * <p>返回 words 中与给定模式匹配的单词列表。
     *
     * <p>你可以按任何顺序返回答案。
     *
     * <p>示例：
     *
     * <p>输入：words = ["abc","deq","mee","aqq","dkd","ccc"], pattern = "abb" 输出：["mee","aqq"] 解释：
     * "mee" 与模式匹配，因为存在排列 {a -> m, b -> e, ...}。 "ccc" 与模式不匹配，因为 {a -> c, b -> c, ...} 不是排列。 因为 a 和
     * b 映射到同一个字母。
     *
     * <p>提示：
     *
     * <p>1 <= words.length <= 50 1 <= pattern.length = words[i].length <= 20
     *
     * @param words
     * @param pattern
     * @return
     */
    public List<String> findAndReplacePattern(String[] words, String pattern) {
        List<String> result = new ArrayList<>();
        for (String word : words) {
            if (match(word, pattern)) {
                result.add(word);
            }
        }
        return result;
    }

    private boolean match(String word, String pattern) {
        if (word.length() != pattern.length()) {
            return false;
        }
        Map<Character, Character> map1 = new HashMap<>(), map2 = new HashMap<>();
        for (int i = 0; i < word.length(); i++) {
            char c1 = word.charAt(i), c2 = pattern.charAt(i);
            if (map1.containsKey(c1) && map1.get(c1) != c2) {
                return false;
            }
            if (map2.containsKey(c2) && map2.get(c2) != c1) {
                return false;
            }
            map1.put(c1, c2);
            map2.put(c2, c1);
        }

        return true;
    }

    /**
     * 921. 使括号有效的最少添加
     *
     * <p>给定一个由 '(' 和 ')' 括号组成的字符串 S，我们需要添加最少的括号（ '(' 或是 ')'，可以在任何位置），以使得到的括号字符串有效。
     *
     * <p>从形式上讲，只有满足下面几点之一，括号字符串才是有效的：
     *
     * <p>它是一个空字符串，或者 它可以被写成 AB （A 与 B 连接）, 其中 A 和 B 都是有效字符串，或者 它可以被写作 (A)，其中 A 是有效字符串。
     * 给定一个括号字符串，返回为使结果字符串有效而必须添加的最少括号数。
     *
     * <p>示例 1：
     *
     * <p>输入："())" 输出：1 示例 2：
     *
     * <p>输入："(((" 输出：3 示例 3：
     *
     * <p>输入："()" 输出：0 示例 4：
     *
     * <p>输入："()))((" 输出：4
     *
     * <p>提示：
     *
     * <p>S.length <= 1000 S 只包含 '(' 和 ')' 字符。
     *
     * @param S
     * @return
     */
    public int minAddToMakeValid(String S) {
        int result = 0, leftCount = 0;
        for (char c : S.toCharArray()) {
            if (c == '(') {
                leftCount++;
            } else if (c == ')') {
                if (leftCount == 0) {
                    result++;
                } else {
                    leftCount--;
                }
            }
        }
        result += leftCount;
        return result;
    }

    /**
     * 5541. 统计只差一个字符的子串数目
     *
     * <p>给你两个字符串 s 和 t ，请你找出 s 中的非空子串的数目，这些子串满足替换 一个不同字符 以后，是 t 串的子串。换言之，请你找到 s 和 t 串中 恰好
     * 只有一个字符不同的子字符串对的数目。
     *
     * <p>比方说， "computer" 和 "computation" 加粗部分只有一个字符不同： 'e'/'a' ，所以这一对子字符串会给答案加 1 。
     *
     * <p>请你返回满足上述条件的不同子字符串对数目。
     *
     * <p>一个 子字符串 是一个字符串中连续的字符。
     *
     * <p>示例 1：
     *
     * <p>输入：s = "aba", t = "baba" 输出：6 解释：以下为只相差 1 个字符的 s 和 t 串的子字符串对： ("aba", "baba") ("aba",
     * "baba") ("aba", "baba") ("aba", "baba") ("aba", "baba") ("aba", "baba") 加粗部分分别表示 s 和 t
     * 串选出来的子字符串。 示例 2： 输入：s = "ab", t = "bb" 输出：3 解释：以下为只相差 1 个字符的 s 和 t 串的子字符串对： ("ab", "bb")
     * ("ab", "bb") ("ab", "bb") 加粗部分分别表示 s 和 t 串选出来的子字符串。 示例 3： 输入：s = "a", t = "a" 输出：0 示例 4：
     *
     * <p>输入：s = "abe", t = "bbc" 输出：10
     *
     * <p>提示：
     *
     * <p>1 <= s.length, t.length <= 100 s 和 t 都只包含小写英文字母。
     *
     * @param s
     * @param t
     * @return
     */
    public int countSubstrings(String s, String t) {
        int count = 0;
        int m = s.length(), n = t.length();
        /*for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int diff = 0;
                for (int k = 0; i + k < m && j + k < n; k++) {
                    diff += s.charAt(i + k) != t.charAt(j + k) ? 1 : 0;
                    if (diff > 1) {
                        break;
                    }
                    if (diff == 1) {
                        count++;
                    }
                }
            }
        }*/
        // 枚举不同的那一个字符的位置，然后向两端扩展
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (s.charAt(i) == t.charAt(j)) {
                    continue;
                }
                int l = 0, r = 0;
                while (i - l - 1 >= 0
                        && j - l - 1 >= 0
                        && s.charAt(i - l - 1) == t.charAt(j - l - 1)) {
                    l++;
                }
                while (i + r + 1 < m
                        && j + r + 1 < n
                        && s.charAt(i + r + 1) == t.charAt(j + r + 1)) {
                    r++;
                }
                count += (l + 1) * (r + 1);
            }
        }
        return count;
    }

    /**
     * 916. 单词子集
     *
     * <p>我们给出两个单词数组 A 和 B。每个单词都是一串小写字母。
     *
     * <p>现在，如果 b 中的每个字母都出现在 a 中，包括重复出现的字母，那么称单词 b 是单词 a 的子集。 例如，“wrr” 是 “warrior” 的子集，但不是 “world”
     * 的子集。
     *
     * <p>如果对 B 中的每一个单词 b，b 都是 a 的子集，那么我们称 A 中的单词 a 是通用的。
     *
     * <p>你可以按任意顺序以列表形式返回 A 中所有的通用单词。
     *
     * <p>示例 1：
     *
     * <p>输入：A = ["amazon","apple","facebook","google","leetcode"], B = ["e","o"]
     * 输出：["facebook","google","leetcode"] 示例 2：
     *
     * <p>输入：A = ["amazon","apple","facebook","google","leetcode"], B = ["l","e"]
     * 输出：["apple","google","leetcode"] 示例 3：
     *
     * <p>输入：A = ["amazon","apple","facebook","google","leetcode"], B = ["e","oo"]
     * 输出：["facebook","google"] 示例 4：
     *
     * <p>输入：A = ["amazon","apple","facebook","google","leetcode"], B = ["lo","eo"]
     * 输出：["google","leetcode"] 示例 5：
     *
     * <p>输入：A = ["amazon","apple","facebook","google","leetcode"], B = ["ec","oc","ceo"]
     * 输出：["facebook","leetcode"]
     *
     * <p>提示：
     *
     * <p>1 <= A.length, B.length <= 10000 1 <= A[i].length, B[i].length <= 10 A[i] 和 B[i] 只由小写字母组成。
     * A[i] 中所有的单词都是独一无二的，也就是说不存在 i != j 使得 A[i] == A[j]。
     *
     * @param A
     * @param B
     * @return
     */
    public List<String> wordSubsets(String[] A, String[] B) {
        int[] maxLetter = new int[26];
        for (String s : B) {
            int[] nums = getLetterCount(s);
            for (int i = 0; i < 26; i++) {
                maxLetter[i] = Math.max(maxLetter[i], nums[i]);
            }
        }

        List<String> result = new ArrayList<>();
        out:
        for (String s : A) {
            int[] nums = getLetterCount(s);
            for (int i = 0; i < 26; i++) {
                if (nums[i] < maxLetter[i]) {
                    continue out;
                }
            }
            result.add(s);
        }

        return result;
    }

    private int[] getLetterCount(String s) {
        int[] nums = new int[26];
        for (char c : s.toCharArray()) {
            nums[c - 'a']++;
        }
        return nums;
    }

    /**
     * 5562. 字符频次唯一的最小删除次数
     *
     * <p>如果字符串 s 中 不存在 两个不同字符 频次 相同的情况，就称 s 是 优质字符串 。
     *
     * <p>给你一个字符串 s，返回使 s 成为 优质字符串 需要删除的 最小 字符数。
     *
     * <p>字符串中字符的 频次 是该字符在字符串中的出现次数。例如，在字符串 "aab" 中，'a' 的频次是 2，而 'b' 的频次是 1 。
     *
     * <p>示例 1：
     *
     * <p>输入：s = "aab" 输出：0 解释：s 已经是优质字符串。 示例 2：
     *
     * <p>输入：s = "aaabbbcc" 输出：2 解释：可以删除两个 'b' , 得到优质字符串 "aaabcc" 。 另一种方式是删除一个 'b' 和一个 'c' ，得到优质字符串
     * "aaabbc" 。 示例 3：
     *
     * <p>输入：s = "ceabaacb" 输出：2 解释：可以删除两个 'c' 得到优质字符串 "eabaab" 。 注意，只需要关注结果字符串中仍然存在的字符。（即，频次为 0
     * 的字符会忽略不计。）
     *
     * <p>提示：
     *
     * <p>1 <= s.length <= 105 s 仅含小写英文字母
     *
     * @param s
     * @return
     */
    public int minDeletions(String s) {
        int[] letters = new int[26];
        for (char c : s.toCharArray()) {
            letters[c - 'a']++;
        }
        Arrays.sort(letters);
        int result = 0;
        Set<Integer> countSet = new HashSet<>();
        for (int i = 0; i < 26; i++) {
            if (letters[i] != 0) {
                countSet.add(letters[i]);
            }
        }

        for (int i = 24; i >= 0; i--) {
            if (letters[i] == 0) {
                break;
            }
            if (letters[i] != letters[i + 1]) {
                countSet.add(letters[i]);
                continue;
            }
            int minCount = letters[i] - 1;
            while (minCount > 0 && countSet.contains(minCount)) {
                minCount--;
            }
            if (minCount == 0) {
                result += letters[i];
            } else {
                result += letters[i] - minCount;
                countSet.add(minCount);
            }
        }

        return result;
    }

    @Test
    public void minDeletions() {
        String s = "abcabc";
        logResult(minDeletions(s));
    }

    /**
     * 5551. 使字符串平衡的最少删除次数
     *
     * <p>给你一个字符串 s ，它仅包含字符 'a' 和 'b' 。
     *
     * <p>你可以删除 s 中任意数目的字符，使得 s 平衡 。我们称 s 平衡的 当不存在下标对 (i,j) 满足 i < j 且 s[i] = 'b' 同时 s[j]= 'a' 。
     *
     * <p>请你返回使 s 平衡 的 最少 删除次数。
     *
     * <p>示例 1：
     *
     * <p>输入：s = "aababbab" 输出：2 解释：你可以选择以下任意一种方案： 下标从 0 开始，删除第 2 和第 6 个字符（"aababbab" -> "aaabbb"），
     * 下标从 0 开始，删除第 3 和第 6 个字符（"aababbab" -> "aabbbb"）。 示例 2：
     *
     * <p>输入：s = "bbaaaaabb" 输出：2 解释：唯一的最优解是删除最前面两个字符。
     *
     * <p>提示：
     *
     * <p>1 <= s.length <= 105 s[i] 要么是 'a' 要么是 'b'​ 。​
     *
     * @param s
     * @return
     */
    public int minimumDeletions(String s) {
        int len = s.length(), min = len;
        int[] aCount = new int[len + 1], bCount = new int[len + 1];
        // 左边 b 的个数 和 右边 a的个数
        int count = 0;
        for (int i = 0; i < len; i++) {
            bCount[i] = count;
            char c = s.charAt(i);
            if (c == 'b') {
                count++;
            }
        }
        bCount[len] = count;
        count = 0;
        for (int i = len - 1; i >= 0; i--) {
            aCount[i + 1] = count;
            char c = s.charAt(i);
            if (c == 'a') {
                count++;
            }
        }
        aCount[0] = count;

        for (int i = 0; i <= len; i++) {
            min = Math.min(min, aCount[i] + bCount[i]);
        }
        return min;
    }

    @Test
    public void minimumDeletions() {
        String s = "bbaaaaabb";
        logResult(minimumDeletions(s));
    }

    /**
     * 5603. 确定两个字符串是否接近
     *
     * <p>如果可以使用以下操作从一个字符串得到另一个字符串，则认为两个字符串 接近 ：
     *
     * <p>操作 1：交换任意两个 现有 字符。 例如，abcde -> aecdb 操作 2：将一个 现有 字符的每次出现转换为另一个 现有 字符，并对另一个字符执行相同的操作。
     * 例如，aacabb -> bbcbaa（所有 a 转化为 b ，而所有的 b 转换为 a ） 你可以根据需要对任意一个字符串多次使用这两种操作。
     *
     * <p>给你两个字符串，word1 和 word2 。如果 word1 和 word2 接近 ，就返回 true ；否则，返回 false 。
     *
     * <p>示例 1：
     *
     * <p>输入：word1 = "abc", word2 = "bca" 输出：true 解释：2 次操作从 word1 获得 word2 。 执行操作 1："abc" -> "acb"
     * 执行操作 1："acb" -> "bca" 示例 2：
     *
     * <p>输入：word1 = "a", word2 = "aa" 输出：false 解释：不管执行多少次操作，都无法从 word1 得到 word2 ，反之亦然。 示例 3：
     *
     * <p>输入：word1 = "cabbba", word2 = "abbccc" 输出：true 解释：3 次操作从 word1 获得 word2 。 执行操作 1："cabbba"
     * -> "caabbb" 执行操作 2："caabbb" -> "baaccc" 执行操作 2："baaccc" -> "abbccc" 示例 4：
     *
     * <p>输入：word1 = "cabbba", word2 = "aabbss" 输出：false 解释：不管执行多少次操作，都无法从 word1 得到 word2 ，反之亦然。
     *
     * <p>提示：
     *
     * <p>1 <= word1.length, word2.length <= 105 word1 和 word2 仅包含小写英文字母
     *
     * @param word1
     * @param word2
     * @return
     */
    public boolean closeStrings(String word1, String word2) {
        if (word1.length() != word2.length()) {
            return false;
        }
        if (Objects.equals(word1, word2)) {
            return true;
        }
        int[] letter1 = new int[26], letter2 = new int[26];
        for (int i = 0; i < word1.length(); i++) {
            char c1 = word1.charAt(i), c2 = word2.charAt(i);
            letter1[c1 - 'a']++;
            letter2[c2 - 'a']++;
        }
        // 判断字母组合是否相等
        for (int i = 0; i < 26; i++) {
            if (letter1[i] == 0 && letter2[i] > 0 || letter1[i] > 0 && letter2[i] == 0) {
                return false;
            }
        }
        Arrays.sort(letter1);
        Arrays.sort(letter2);
        for (int i = 0; i < 26; i++) {
            if (letter1[i] != letter2[i]) {
                return false;
            }
        }
        return true;
    }

    /**
     * 1016. 子串能表示从 1 到 N 数字的二进制串
     *
     * <p>给定一个二进制字符串 S（一个仅由若干 '0' 和 '1' 构成的字符串）和一个正整数 N，如果对于从 1 到 N 的每个整数 X，其二进制表示都是 S 的子串，就返回
     * true，否则返回 false。
     *
     * <p>示例 1：
     *
     * <p>输入：S = "0110", N = 3 输出：true 示例 2：
     *
     * <p>输入：S = "0110", N = 4 输出：false
     *
     * <p>提示：
     *
     * <p>1 <= S.length <= 1000 1 <= N <= 10^9
     *
     * @param S
     * @param N
     * @return
     */
    public boolean queryString(String S, int N) {

        for (int i = 1; i <= N; i++) {
            String num = Integer.toBinaryString(i);
            if (!S.contains(num)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 1023. 驼峰式匹配
     *
     * <p>如果我们可以将小写字母插入模式串 pattern 得到待查询项 query，那么待查询项与给定模式串匹配。（我们可以在任何位置插入每个字符，也可以插入 0 个字符。）
     *
     * <p>给定待查询列表 queries，和模式串 pattern，返回由布尔值组成的答案列表 answer。只有在待查项 queries[i] 与模式串 pattern 匹配时，
     * answer[i] 才为 true，否则为 false。
     *
     * <p>示例 1：
     *
     * <p>输入：queries = ["FooBar","FooBarTest","FootBall","FrameBuffer","ForceFeedBack"], pattern =
     * "FB" 输出：[true,false,true,true,false] 示例： "FooBar" 可以这样生成："F" + "oo" + "B" + "ar"。 "FootBall"
     * 可以这样生成："F" + "oot" + "B" + "all". "FrameBuffer" 可以这样生成："F" + "rame" + "B" + "uffer". 示例 2：
     *
     * <p>输入：queries = ["FooBar","FooBarTest","FootBall","FrameBuffer","ForceFeedBack"], pattern =
     * "FoBa" 输出：[true,false,true,false,false] 解释： "FooBar" 可以这样生成："Fo" + "o" + "Ba" + "r".
     * "FootBall" 可以这样生成："Fo" + "ot" + "Ba" + "ll". 示例 3：
     *
     * <p>输出：queries = ["FooBar","FooBarTest","FootBall","FrameBuffer","ForceFeedBack"], pattern =
     * "FoBaT" 输入：[false,true,false,false,false] 解释： "FooBarTest" 可以这样生成："Fo" + "o" + "Ba" + "r" +
     * "T" + "est".
     *
     * <p>提示：
     *
     * <p>1 <= queries.length <= 100 1 <= queries[i].length <= 100 1 <= pattern.length <= 100
     * 所有字符串都仅由大写和小写英文字母组成。
     *
     * @param queries
     * @param pattern
     * @return
     */
    public List<Boolean> camelMatch(String[] queries, String pattern) {
        List<Boolean> result = new ArrayList<>();
        for (String word : queries) {
            result.add(camelMatch(word, pattern));
        }

        return result;
    }

    /**
     * 模式匹配
     *
     * @param word
     * @param pattern
     * @return
     */
    private boolean camelMatch(String word, String pattern) {
        int idx = 0;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (idx < pattern.length() && c == pattern.charAt(idx)) {
                idx++;
            } else if (c >= 'A' && c <= 'Z') {
                return false;
            }
        }
        return idx == pattern.length();
    }
}
