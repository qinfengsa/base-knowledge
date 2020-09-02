package com.qinfengsa.structure.leetcode;

import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

/**
 * 1286. 字母组合迭代器
 *
 * <p>请你设计一个迭代器类，包括以下内容：
 *
 * <p>一个构造函数，输入参数包括：一个 有序且字符唯一 的字符串 characters（该字符串只包含小写英文字母）和一个数字 combinationLength 。 函数 next() ，按
 * 字典序 返回长度为 combinationLength 的下一个字母组合。 函数 hasNext() ，只有存在长度为 combinationLength 的下一个字母组合时，才返回
 * True；否则，返回 False。
 *
 * <p>示例：
 *
 * <p>CombinationIterator iterator = new CombinationIterator("abc", 2); // 创建迭代器 iterator
 *
 * <p>iterator.next(); // 返回 "ab" iterator.hasNext(); // 返回 true iterator.next(); // 返回 "ac"
 * iterator.hasNext(); // 返回 true iterator.next(); // 返回 "bc" iterator.hasNext(); // 返回 false
 *
 * <p>提示：
 *
 * <p>1 <= combinationLength <= characters.length <= 15 每组测试数据最多包含 10^4 次函数调用。 题目保证每次调用函数 next
 * 时都存在下一个字母组合。
 *
 * @author qinfengsa
 * @date 2020/09/02 14:01
 */
@Slf4j
public class CombinationIterator {

    private int index;

    private int combinationLength;

    private List<String> list = new ArrayList<>();

    public CombinationIterator(String characters, int combinationLength) {
        char[] chars = characters.toCharArray();
        this.combinationLength = combinationLength;
        index = 0;
        back(chars, new StringBuilder(), 0);
        log.debug("list:{}", list);
    }

    private void back(char[] chars, StringBuilder sb, int start) {
        if (sb.length() == combinationLength) {
            list.add(sb.toString());
            return;
        }
        for (int i = start; i < chars.length; i++) {
            sb.append(chars[i]);
            back(chars, sb, i + 1);
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    // 回溯算法，使用StringBuilder记录结果，递归完成后从后往前删除
    private void dfs(int start, StringBuilder sb, String s) {
        if (sb.length() == combinationLength) {
            list.add(sb.toString());
            return;
        }
        for (int i = start; i < s.length(); i++) {
            dfs(i + 1, sb.append(s.charAt(i)), s);
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    public String next() {

        return list.get(index++);
    }

    public boolean hasNext() {
        return index < list.size();
    }

    public static void main(String[] args) {
        CombinationIterator iterator = new CombinationIterator("abc", 2); // 创建迭代器 iterator

        log.debug("1:{}", iterator.next()); // 返回 "ab"
        log.debug("2:{}", iterator.hasNext()); // 返回 true
        log.debug("3:{}", iterator.next()); // 返回 "ac"
        log.debug("4:{}", iterator.hasNext()); // 返回 true
        log.debug("5:{}", iterator.next()); // 返回 "bc"
        log.debug("6:{}", iterator.hasNext()); // 返回 false
    }
}
