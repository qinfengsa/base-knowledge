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
import javafx.util.Pair;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

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
}
