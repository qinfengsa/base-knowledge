package com.qinfengsa.structure.leetcode;

import static com.qinfengsa.structure.util.LogUtils.logResult;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

/**
 * 字符串 test
 *
 * @author qinfengsa
 * @date 2021/01/24 10:31
 */
@Slf4j
public class String2Test {

    /**
     * 5661. 替换隐藏数字得到的最晚时间
     *
     * <p>给你一个字符串 time ，格式为 hh:mm（小时：分钟），其中某几位数字被隐藏（用 ? 表示）。
     *
     * <p>有效的时间为 00:00 到 23:59 之间的所有时间，包括 00:00 和 23:59 。
     *
     * <p>替换 time 中隐藏的数字，返回你可以得到的最晚有效时间。
     *
     * <p>示例 1：
     *
     * <p>输入：time = "2?:?0" 输出："23:50" 解释：以数字 '2' 开头的最晚一小时是 23 ，以 '0' 结尾的最晚一分钟是 50 。 示例 2：
     *
     * <p>输入：time = "0?:3?" 输出："09:39" 示例 3：
     *
     * <p>输入：time = "1?:22" 输出："19:22"
     *
     * <p>提示：
     *
     * <p>time 的格式为 hh:mm 题目数据保证你可以由输入的字符串生成有效的时间
     *
     * @param time
     * @return
     */
    public String maximumTime(String time) {
        char[] chars = time.toCharArray();
        switch (chars[0]) {
            case '?':
                {
                    if (chars[1] == '?') {
                        chars[0] = '2';
                        chars[1] = '3';
                    } else if (chars[1] > '3') {
                        chars[0] = '1';
                    } else {
                        chars[0] = '2';
                    }
                }
                break;
            case '0':
            case '1':
                {
                    if (chars[1] == '?') {
                        chars[1] = '9';
                    }
                }
                break;
            case '2':
                {
                    if (chars[1] == '?') {
                        chars[1] = '3';
                    }
                }
                break;
        }

        if (chars[3] == '?') {
            chars[3] = '5';
        }
        if (chars[4] == '?') {
            chars[4] = '9';
        }

        return new String(chars);
    }

    /**
     * 5662. 满足三条件之一需改变的最少字符数
     *
     * <p>给你两个字符串 a 和 b ，二者均由小写字母组成。一步操作中，你可以将 a 或 b 中的 任一字符 改变为 任一小写字母 。
     *
     * <p>操作的最终目标是满足下列三个条件 之一 ：
     *
     * <p>a 中的 每个字母 在字母表中 严格小于 b 中的 每个字母 。
     *
     * <p>b 中的 每个字母 在字母表中 严格小于 a 中的 每个字母 。
     *
     * <p>a 和 b 都 由 同一个 字母组成。 返回达成目标所需的 最少 操作数。
     *
     * <p>示例 1：
     *
     * <p>输入：a = "aba", b = "caa" 输出：2 解释：满足每个条件的最佳方案分别是： 1) 将 b 变为 "ccc"，2 次操作，满足 a 中的每个字母都小于 b
     * 中的每个字母； 2) 将 a 变为 "bbb" 并将 b 变为 "aaa"，3 次操作，满足 b 中的每个字母都小于 a 中的每个字母； 3) 将 a 变为 "aaa" 并将 b 变为
     * "aaa"，2 次操作，满足 a 和 b 由同一个字母组成。 最佳的方案只需要 2 次操作（满足条件 1 或者条件 3）。 示例 2：
     *
     * <p>输入：a = "dabadd", b = "cda" 输出：3 解释：满足条件 1 的最佳方案是将 b 变为 "eee" 。
     *
     * <p>提示：
     *
     * <p>1 <= a.length, b.length <= 105 a 和 b 只由小写字母组成
     *
     * @param a
     * @param b
     * @return
     */
    public int minCharacters(String a, String b) {
        int len1 = a.length(), len2 = b.length();
        int[] letters = new int[26], letters1 = new int[26], letters2 = new int[26];
        int max = 0;
        for (char c : a.toCharArray()) {
            letters1[c - 'a']++;
            letters[c - 'a']++;
            max = Math.max(max, letters[c - 'a']);
        }
        for (char c : b.toCharArray()) {
            letters2[c - 'a']++;
            letters[c - 'a']++;
            max = Math.max(max, letters[c - 'a']);
        }
        // 条件3
        int result = len1 + len2 - max;
        int count1 = 0, count2 = 0;
        for (int i = 0; i < 25; i++) {
            count1 += letters1[i];
            count2 += letters2[i];
            // 条件1 a 中的 每个字母 在字母表中 严格小于 b 中的 每个字母
            result = Math.min(result, len1 - count1 + count2);
            // 条件2 b 中的 每个字母 在字母表中 严格小于 a 中的 每个字母
            result = Math.min(result, len2 - count2 + count1);
        }

        return result;
    }

    @Test
    public void minCharacters() {
        String a = "dabadd", b = "cda";
        logResult(minCharacters(a, b));
    }

    /**
     * 1096. 花括号展开 II
     *
     * <p>如果你熟悉 Shell 编程，那么一定了解过花括号展开，它可以用来生成任意字符串。
     *
     * <p>花括号展开的表达式可以看作一个由 花括号、逗号 和 小写英文字母 组成的字符串，定义下面几条语法规则：
     *
     * <p>如果只给出单一的元素 x，那么表达式表示的字符串就只有 "x"。
     *
     * <p>R(x) = {x}
     *
     * <p>例如，表达式 {"a"} 表示字符串 "a"。
     *
     * <p>而表达式 {"w"} 就表示字符串 "w"。 当两个或多个表达式并列，以逗号分隔时，我们取这些表达式中元素的并集。R({e_1,e_2,...}) = R(e_1) ∪
     * R(e_2) ∪ ...
     *
     * <p>例如，表达式 "{a,b,c}" 表示字符串 "a","b","c"。
     *
     * <p>而表达式 "{{a,b},{b,c}}" 也可以表示字符串 "a","b","c"。
     *
     * <p>要是两个或多个表达式相接，中间没有隔开时，我们从这些表达式中各取一个元素依次连接形成字符串。R(e_1 + e_2) = {a + b for (a, b) in R(e_1) ×
     * R(e_2)}
     *
     * <p>例如，表达式 "{a,b}{c,d}" 表示字符串 "ac","ad","bc","bd"。
     *
     * <p>表达式之间允许嵌套，单一元素与表达式的连接也是允许的。
     *
     * <p>例如，表达式 "a{b,c,d}" 表示字符串 "ab","ac","ad"。
     *
     * <p>例如，表达式 "a{b,c}}{{d,e}f{g,h}" 可以表示字符串 "abdfg", "abdfh", "abefg", "abefh", "acdfg", "acdfh",
     * "acefg", "acefh"。
     *
     * <p>给出表示基于给定语法规则的表达式 expression，返回它所表示的所有字符串组成的有序列表。
     *
     * <p>假如你希望以「集合」的概念了解此题，也可以通过点击 “显示英文描述” 获取详情。
     *
     * <p>示例 1：
     *
     * <p>输入："{a,b}{c,{d,e}}" 输出：["ac","ad","ae","bc","bd","be"]
     *
     * <p>示例 2：
     *
     * <p>输入："{{a,z},a{b,c},{ab,z}}" 输出：["a","ab","ac","z"] 解释：输出中 不应 出现重复的组合结果。
     *
     * <p>提示：
     *
     * <p>1 <= expression.length <= 50 expression[i] 由 '{'，'}'，',' 或小写英文字母组成 给出的表达式 expression
     * 用以表示一组基于题目描述中语法构造的字符串
     *
     * @param expression
     * @return
     */
    public List<String> braceExpansionII(String expression) {

        return getBraceExpansionII(expression).stream().sorted().collect(Collectors.toList());
    }

    private Set<String> getBraceExpansionII(String expression) {
        int len = expression.length();
        HashSet<String> result = new HashSet<>();
        if (len == 0) {
            return result;
        }
        if (!expression.contains("{")) {
            return Arrays.stream(expression.split(",")).collect(Collectors.toSet());
        }

        // pairStart, pairEnd分另表示第一个完整的{}的位置
        int count = 0, start = -1, end = -1;
        for (int i = 0; i < len; i++) {
            if (expression.charAt(i) == '{') {
                if (start == -1) {
                    start = i;
                }
                count++;
            } else if (expression.charAt(i) == '}') {
                count--;
            }
            if (count == 0) {
                if (start != -1 && end == -1) {
                    end = i;
                }
                // pair==0，即不在某个{}中间，那么可以将表达式分成两段
                if (expression.charAt(i) == ',') {
                    result.addAll(getBraceExpansionII(expression.substring(0, i)));
                    result.addAll(getBraceExpansionII(expression.substring(i + 1)));
                    return result;
                }
            }
        }
        // 现在剩下的只会是{a{b}{c}} a{b}c {}{}这种形式的了
        String prefix = "";
        // 括号起点不为0，说明是a{b}c这种形式
        if (start != 0) {
            prefix = expression.substring(0, start);
        }
        // 剥掉第一个的最外层括号
        Set<String> left = getBraceExpansionII(expression.substring(start + 1, end));
        // 求出第一个完整{}后部分
        Set<String> right = getBraceExpansionII(expression.substring(end + 1));
        // 为了方便计算加入空串
        if (left.isEmpty()) {
            left.add("");
        }
        if (right.isEmpty()) {
            right.add("");
        }
        // 拼接
        for (String l : left) {
            for (String r : right) {
                result.add(prefix + l + r);
            }
        }

        return result;
    }

    /**
     * 1106. 解析布尔表达式
     *
     * <p>给你一个以字符串形式表述的 布尔表达式（boolean） expression，返回该式的运算结果。
     *
     * <p>有效的表达式需遵循以下约定：
     *
     * <p>"t"，运算结果为 True
     *
     * <p>"f"，运算结果为 False
     *
     * <p>"!(expr)"，运算过程为对内部表达式 expr 进行逻辑 非的运算（NOT）
     *
     * <p>"&(expr1,expr2,...)"，运算过程为对 2 个或以上内部表达式 expr1, expr2, ... 进行逻辑 与的运算（AND）
     *
     * <p>"|(expr1,expr2,...)"，运算过程为对 2 个或以上内部表达式 expr1, expr2, ... 进行逻辑 或的运算（OR）
     *
     * <p>示例 1：
     *
     * <p>输入：expression = "!(f)" 输出：true 示例 2：
     *
     * <p>输入：expression = "|(f,t)" 输出：true 示例 3：
     *
     * <p>输入：expression = "&(t,f)" 输出：false 示例 4：
     *
     * <p>输入：expression = "|(&(t,f,t),!(t))" 输出：false
     *
     * <p>提示：
     *
     * <p>1 <= expression.length <= 20000 expression[i] 由 {'(', ')', '&', '|', '!', 't', 'f', ','}
     * 中的字符组成。 expression 是以上述形式给出的有效表达式，表示一个布尔值。
     *
     * @param expression
     * @return
     */
    public boolean parseBoolExpr(String expression) {
        // 用栈 操作符栈和 变量栈
        Deque<Character> opDeque = new LinkedList<>();
        Deque<Character> boolDeque = new LinkedList<>();

        Character lastChar = null;
        for (char c : expression.toCharArray()) {
            if (c == ',') {
                continue;
            }
            switch (c) {
                case 'f':
                case 't':
                case '(':
                    boolDeque.push(c);
                    break;
                case '!':
                case '&':
                case '|':
                    opDeque.push(c);
                    break;
                case ')':
                    {
                        char op = opDeque.pop();
                        List<Character> list = new ArrayList<>();
                        while (boolDeque.peek() != '(') {
                            list.add(boolDeque.pop());
                        }
                        boolDeque.pop();
                        boolean result = calBoolResult(op, list);
                        boolDeque.push(result ? 't' : 'f');
                    }
            }
        }

        return boolDeque.pop() == 't';
    }

    private boolean calBoolResult(char op, List<Character> list) {
        switch (op) {
            case '&':
                return list.stream().allMatch(b -> b == 't');
            case '|':
                return list.stream().anyMatch(b -> b == 't');
            case '!':
                return list.get(0) == 'f';
        }
        return false;
    }

    @Test
    public void parseBoolExpr() {
        String expression = "|(&(t,f,t),!(t))";
        logResult(parseBoolExpr(expression));
    }

    /**
     * 5659. 删除字符串两端相同字符后的最短长度
     *
     * <p>给你一个只包含字符 'a'，'b' 和 'c' 的字符串 s ，你可以执行下面这个操作（5 个步骤）任意次：
     *
     * <p>选择字符串 s 一个 非空 的前缀，这个前缀的所有字符都相同。 选择字符串 s 一个 非空 的后缀，这个后缀的所有字符都相同。 前缀和后缀在字符串中任意位置都不能有交集。
     * 前缀和后缀包含的所有字符都要相同。 同时删除前缀和后缀。 请你返回对字符串 s 执行上面操作任意次以后（可能 0 次），能得到的 最短长度 。
     *
     * <p>示例 1：
     *
     * <p>输入：s = "ca" 输出：2 解释：你没法删除任何一个字符，所以字符串长度仍然保持不变。 示例 2：
     *
     * <p>输入：s = "cabaabac" 输出：0 解释：最优操作序列为： - 选择前缀 "c" 和后缀 "c" 并删除它们，得到 s = "abaaba" 。 - 选择前缀 "a"
     * 和后缀 "a" 并删除它们，得到 s = "baab" 。 - 选择前缀 "b" 和后缀 "b" 并删除它们，得到 s = "aa" 。 - 选择前缀 "a" 和后缀 "a"
     * 并删除它们，得到 s = "" 。 示例 3：
     *
     * <p>输入：s = "aabccabba" 输出：3 解释：最优操作序列为： - 选择前缀 "aa" 和后缀 "a" 并删除它们，得到 s = "bccabb" 。 - 选择前缀 "b"
     * 和后缀 "bb" 并删除它们，得到 s = "cca" 。
     *
     * <p>提示：
     *
     * <p>1 <= s.length <= 105 s 只包含字符 'a'，'b' 和 'c' 。
     *
     * @param s
     * @return
     */
    public int minimumLength(String s) {
        int len = s.length();
        int left = 0, right = len - 1;
        char[] chars = s.toCharArray();
        while (left < right) {
            if (chars[left] != chars[right]) {
                break;
            }
            char c = chars[left];
            while (left < right && chars[left] == c) {
                left++;
            }
            if (left == right) {
                return 0;
            }
            while (left < right && chars[right] == c) {
                right--;
            }
        }
        return right - left + 1;
    }

    @Test
    public void minimumLength() {
        String s = "ca";

        logResult(minimumLength(s));
    }

    /**
     * 5676. 生成交替二进制字符串的最少操作数
     *
     * <p>给你一个仅由字符 '0' 和 '1' 组成的字符串 s 。一步操作中，你可以将任一 '0' 变成 '1' ，或者将 '1' 变成 '0' 。
     *
     * <p>交替字符串 定义为：如果字符串中不存在相邻两个字符相等的情况，那么该字符串就是交替字符串。例如，字符串 "010" 是交替字符串，而字符串 "0100" 不是。
     *
     * <p>返回使 s 变成 交替字符串 所需的 最少 操作数。
     *
     * <p>示例 1：
     *
     * <p>输入：s = "0100" 输出：1 解释：如果将最后一个字符变为 '1' ，s 就变成 "0101" ，即符合交替字符串定义。 示例 2：
     *
     * <p>输入：s = "10" 输出：0 解释：s 已经是交替字符串。 示例 3：
     *
     * <p>输入：s = "1111" 输出：2 解释：需要 2 步操作得到 "0101" 或 "1010" 。
     *
     * <p>提示：
     *
     * <p>1 <= s.length <= 104 s[i] 是 '0' 或 '1'
     *
     * @param s
     * @return
     */
    public int minOperations(String s) {
        // 0101
        int min1 = 0, min2 = 0;
        int len = s.length();
        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            // 0101
            if ((i & 1) == 0) { // 偶数位
                if (c == '1') {
                    min1++;
                } else {
                    min2++;
                }
            } else {
                if (c == '0') {
                    min1++;
                } else {
                    min2++;
                }
            }
            // 1010
        }
        return Math.min(min1, min2);
    }

    /**
     * 5677. 统计同构子字符串的数目
     *
     * <p>给你一个字符串 s ，返回 s 中 同构子字符串 的数目。由于答案可能很大，只需返回对 109 + 7 取余 后的结果。
     *
     * <p>同构字符串 的定义为：如果一个字符串中的所有字符都相同，那么该字符串就是同构字符串。
     *
     * <p>子字符串 是字符串中的一个连续字符序列。
     *
     * <p>示例 1：
     *
     * <p>输入：s = "abbcccaa" 输出：13 解释：同构子字符串如下所列： "a" 出现 3 次。 "aa" 出现 1 次。 "b" 出现 2 次。 "bb" 出现 1 次。
     * "c" 出现 3 次。 "cc" 出现 2 次。 "ccc" 出现 1 次。 3 + 1 + 2 + 1 + 3 + 2 + 1 = 13 示例 2：
     *
     * <p>输入：s = "xy" 输出：2 解释：同构子字符串是 "x" 和 "y" 。 示例 3：
     *
     * <p>输入：s = "zzzzz" 输出：15
     *
     * <p>提示：
     *
     * <p>1 <= s.length <= 105 s 由小写字符串组成
     *
     * @param s
     * @return
     */
    public int countHomogenous(String s) {
        char[] chars = s.toCharArray();

        int count = 1;
        // 求连续字符个数
        int result = 0;
        for (int i = 1; i < chars.length; i++) {
            if (chars[i] == chars[i - 1]) {
                count++;
            } else {
                long num = getCountHomogenous(count);
                result += num % MOD;
                count = 1;
            }
        }

        long num = getCountHomogenous(count);
        result += num % MOD;

        return result;
    }

    static final int MOD = 1000000007;

    private long getCountHomogenous(int count) {
        return ((long) (1 + count) * count) >> 1;
    }

    /**
     * 1745. 回文串分割 IV
     *
     * <p>给你一个字符串 s ，如果可以将它分割成三个 非空 回文子字符串，那么返回 true ，否则返回 false 。
     *
     * <p>当一个字符串正着读和反着读是一模一样的，就称其为 回文字符串 。
     *
     * <p>示例 1：
     *
     * <p>输入：s = "abcbdd" 输出：true 解释："abcbdd" = "a" + "bcb" + "dd"，三个子字符串都是回文的。 示例 2：
     *
     * <p>输入：s = "bcbddxy" 输出：false 解释：s 没办法被分割成 3 个回文子字符串。
     *
     * <p>提示：
     *
     * <p>3 <= s.length <= 2000 s 只包含小写英文字母。
     *
     * @param s
     * @return
     */
    public boolean checkPartitioning(String s) {
        int len = s.length();
        char[] chars = s.toCharArray();
        boolean[][] palindrome = new boolean[len][len];
        for (int i = len - 1; i >= 0; i--) {

            for (int j = i; j < len; j++) {
                if (i == j) {
                    palindrome[i][j] = true;
                } else if (i + 1 == j) {
                    palindrome[i][j] = chars[i] == chars[j];
                } else {
                    palindrome[i][j] = chars[i] == chars[j] && palindrome[i + 1][j - 1];
                }
            }
        }
        // 枚举分割点
        for (int i = 0; i < len - 2; i++) {
            if (!palindrome[0][i]) {
                continue;
            }
            for (int j = i + 1; j < len - 1; j++) {
                if (palindrome[i + 1][j] && palindrome[j + 1][len - 1]) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * 5685. 交替合并字符串
     *
     * <p>给你两个字符串 word1 和 word2 。请你从 word1 开始，通过交替添加字母来合并字符串。如果一个字符串比另一个字符串长，就将多出来的字母追加到合并后字符串的末尾。
     *
     * <p>返回 合并后的字符串 。
     *
     * <p>示例 1：
     *
     * <p>输入：word1 = "abc", word2 = "pqr" 输出："apbqcr" 解释：字符串合并情况如下所示： word1： a b c word2： p q r 合并后：
     * a p b q c r 示例 2：
     *
     * <p>输入：word1 = "ab", word2 = "pqrs" 输出："apbqrs" 解释：注意，word2 比 word1 长，"rs" 需要追加到合并后字符串的末尾。
     * word1： a b word2： p q r s 合并后： a p b q r s 示例 3：
     *
     * <p>输入：word1 = "abcd", word2 = "pq" 输出："apbqcd" 解释：注意，word1 比 word2 长，"cd" 需要追加到合并后字符串的末尾。
     * word1： a b c d word2： p q 合并后： a p b q c d
     *
     * <p>提示：
     *
     * <p>1 <= word1.length, word2.length <= 100 word1 和 word2 由小写英文字母组成
     *
     * @param word1
     * @param word2
     * @return
     */
    public String mergeAlternately(String word1, String word2) {

        StringBuilder sb = new StringBuilder();
        int len1 = word1.length(), len2 = word2.length();
        int minLen = Math.min(len1, len2);
        for (int i = 0; i < minLen; i++) {
            sb.append(word1.charAt(i));
            sb.append(word2.charAt(i));
        }

        if (len1 > minLen) {
            sb.append(word1.substring(minLen));
        }
        if (len2 > minLen) {
            sb.append(word2.substring(minLen));
        }

        return sb.toString();
    }

    /**
     * 5668. 最长的美好子字符串
     *
     * <p>当一个字符串 s 包含的每一种字母的大写和小写形式 同时 出现在 s 中，就称这个字符串 s 是 美好 字符串。比方说，"abABB" 是美好字符串，因为 'A' 和 'a'
     * 同时出现了，且 'B' 和 'b' 也同时出现了。然而，"abA" 不是美好字符串因为 'b' 出现了，而 'B' 没有出现。
     *
     * <p>给你一个字符串 s ，请你返回 s 最长的 美好子字符串 。如果有多个答案，请你返回 最早 出现的一个。如果不存在美好子字符串，请你返回一个空字符串。
     *
     * <p>示例 1：
     *
     * <p>输入：s = "YazaAay" 输出："aAa" 解释："aAa" 是一个美好字符串，因为这个子串中仅含一种字母，其小写形式 'a' 和大写形式 'A' 也同时出现了。
     * "aAa" 是最长的美好子字符串。 示例 2：
     *
     * <p>输入：s = "Bb" 输出："Bb" 解释："Bb" 是美好字符串，因为 'B' 和 'b' 都出现了。整个字符串也是原字符串的子字符串。 示例 3：
     *
     * <p>输入：s = "c" 输出："" 解释：没有美好子字符串。 示例 4：
     *
     * <p>输入：s = "dDzeE" 输出："dD" 解释："dD" 和 "eE" 都是最长美好子字符串。 由于有多个美好子字符串，返回 "dD" ，因为它出现得最早。
     *
     * <p>提示：
     *
     * <p>1 <= s.length <= 100 s 只包含大写和小写英文字母。
     *
     * @param s
     * @return
     */
    public String longestNiceSubstring(String s) {
        int len = s.length();
        if (len <= 1) {
            return "";
        }
        String result = "";
        char[] chars = s.toCharArray();
        for (int i = 0; i < len; i++) {
            int a = 0, b = 0;
            for (int j = i; j < len; j++) {
                char c = chars[j];
                if (c >= 'a' && c <= 'z') {
                    a |= 1 << (c - 'a');
                } else {
                    b |= 1 << (c - 'A');
                }
                if (a == b && j - i + 1 > result.length()) {
                    result = s.substring(i, j + 1);
                }
            }
        }

        return result;
    }

    @Test
    public void longestNiceSubstring() {
        String s = "dDzeE";
        logResult(longestNiceSubstring(s));
    }

    /**
     * 1542. 找出最长的超赞子字符串
     *
     * <p>给你一个字符串 s 。请返回 s 中最长的 超赞子字符串 的长度。
     *
     * <p>「超赞子字符串」需满足满足下述两个条件：
     *
     * <p>该字符串是 s 的一个非空子字符串 进行任意次数的字符交换后，该字符串可以变成一个回文字符串
     *
     * <p>示例 1：
     *
     * <p>输入：s = "3242415" 输出：5 解释："24241" 是最长的超赞子字符串，交换其中的字符后，可以得到回文 "24142" 示例 2：
     *
     * <p>输入：s = "12345678" 输出：1 示例 3：
     *
     * <p>输入：s = "213123" 输出：6 解释："213123" 是最长的超赞子字符串，交换其中的字符后，可以得到回文 "231132" 示例 4：
     *
     * <p>输入：s = "00" 输出：2
     *
     * <p>提示：
     *
     * <p>1 <= s.length <= 10^5 s 仅由数字组成
     *
     * @param s
     * @return
     */
    public int longestAwesome(String s) {
        char[] chars = s.toCharArray();
        int result = 1;
        Map<Integer, Integer> prefixMap = new HashMap<>();
        int num = 0;
        prefixMap.put(0, -1);
        for (int i = 0; i < s.length(); i++) {
            char c = chars[i];
            num ^= 1 << (c - '0');
            // 完全由两两字符组成的回文字符串
            if (prefixMap.containsKey(num)) {
                result = Math.max(result, i - prefixMap.get(num));
            } else {
                prefixMap.put(num, i);
            }
            // 存在单个字符的回文字符串
            for (int j = 0; j < 10; j++) {
                int singNum = num ^ (1 << j);
                if (prefixMap.containsKey(singNum)) {
                    result = Math.max(result, i - prefixMap.get(singNum));
                }
            }
        }

        return result;
    }

    /**
     * 1585. 检查字符串是否可以通过排序子字符串得到另一个字符串
     *
     * <p>给你两个字符串 s 和 t ，请你通过若干次以下操作将字符串 s 转化成字符串 t ：
     *
     * <p>选择 s 中一个 非空 子字符串并将它包含的字符就地 升序 排序。 比方说，对下划线所示的子字符串进行操作可以由 "14234" 得到 "12344" 。
     *
     * <p>如果可以将字符串 s 变成 t ，返回 true 。否则，返回 false 。
     *
     * <p>一个 子字符串 定义为一个字符串中连续的若干字符。
     *
     * <p>示例 1：
     *
     * <p>输入：s = "84532", t = "34852" 输出：true 解释：你可以按以下操作将 s 转变为 t ： "84532" （从下标 2 到下标 3）-> "84352"
     * "84352" （从下标 0 到下标 2） -> "34852" 示例 2：
     *
     * <p>输入：s = "34521", t = "23415" 输出：true 解释：你可以按以下操作将 s 转变为 t ： "34521" -> "23451" "23451" ->
     * "23415" 示例 3：
     *
     * <p>输入：s = "12345", t = "12435" 输出：false 示例 4：
     *
     * <p>输入：s = "1", t = "2" 输出：false
     *
     * <p>提示：
     *
     * <p>s.length == t.length 1 <= s.length <= 105 s 和 t 都只包含数字字符，即 '0' 到 '9' 。
     *
     * @param s
     * @param t
     * @return
     */
    public boolean isTransformable(String s, String t) {
        int len = s.length();
        if (s.length() != t.length()) {
            return false;
        }
        List<Queue<Integer>> queueList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            queueList.add(new LinkedList<>());
        }
        for (int i = 0; i < len; i++) {
            int idx = s.charAt(i) - '0';
            queueList.get(idx).offer(i);
        }
        for (int i = 0; i < len; i++) {
            int idx = t.charAt(i) - '0';
            Queue<Integer> queue = queueList.get(idx);
            if (queue.isEmpty()) {
                return false;
            }
            for (int j = 0; j < idx; j++) {
                Queue<Integer> smallQueue = queueList.get(j);
                if (smallQueue.isEmpty()) {
                    continue;
                }
                // 当前位置还有更小的没有排序
                if (smallQueue.peek() < queue.peek()) {
                    return false;
                }
            }

            queue.poll();
        }

        return true;
    }

    /**
     * 5689. 统计匹配检索规则的物品数量
     *
     * <p>给你一个数组 items ，其中 items[i] = [typei, colori, namei] ，描述第 i 件物品的类型、颜色以及名称。
     *
     * <p>另给你一条由两个字符串 ruleKey 和 ruleValue 表示的检索规则。
     *
     * <p>如果第 i 件物品能满足下述条件之一，则认为该物品与给定的检索规则 匹配 ：
     *
     * <p>ruleKey == "type" 且 ruleValue == typei 。 ruleKey == "color" 且 ruleValue == colori 。
     * ruleKey == "name" 且 ruleValue == namei 。 统计并返回 匹配检索规则的物品数量 。
     *
     * <p>示例 1：
     *
     * <p>输入：items =
     * [["phone","blue","pixel"],["computer","silver","lenovo"],["phone","gold","iphone"]], ruleKey
     * = "color", ruleValue = "silver" 输出：1 解释：只有一件物品匹配检索规则，这件物品是 ["computer","silver","lenovo"] 。
     * 示例 2：
     *
     * <p>输入：items =
     * [["phone","blue","pixel"],["computer","silver","phone"],["phone","gold","iphone"]], ruleKey =
     * "type", ruleValue = "phone" 输出：2 解释：只有两件物品匹配检索规则，这两件物品分别是 ["phone","blue","pixel"] 和
     * ["phone","gold","iphone"] 。注意，["computer","silver","phone"] 未匹配检索规则。
     *
     * <p>提示：
     *
     * <p>1 <= items.length <= 104 1 <= typei.length, colori.length, namei.length, ruleValue.length
     * <= 10 ruleKey 等于 "type"、"color" 或 "name" 所有字符串仅由小写字母组成
     *
     * @param items
     * @param ruleKey
     * @param ruleValue
     * @return
     */
    public int countMatches(List<List<String>> items, String ruleKey, String ruleValue) {

        int count = 0;

        int index = getRuleIndex(ruleKey);
        for (List<String> item : items) {

            if (item.get(index).equals(ruleValue)) {
                count++;
            }
        }
        return count;
    }

    private int getRuleIndex(String ruleKey) {
        switch (ruleKey) {
            case "type":
                return 0;
            case "color":
                return 1;
            case "name":
                return 2;
        }
        return 0;
    }

    /**
     * 828. 统计子串中的唯一字符
     *
     * <p>我们定义了一个函数 countUniqueChars(s) 来统计字符串 s 中的唯一字符，并返回唯一字符的个数。
     *
     * <p>例如：s = "LEETCODE" ，则其中 "L", "T","C","O","D" 都是唯一字符，因为它们只出现一次，所以 countUniqueChars(s) = 5 。
     *
     * <p>本题将会给你一个字符串 s ，我们需要返回 countUniqueChars(t) 的总和，其中 t 是 s
     * 的子字符串。注意，某些子字符串可能是重复的，但你统计时也必须算上这些重复的子字符串（也就是说，你必须统计 s 的所有子字符串中的唯一字符）。
     *
     * <p>由于答案可能非常大，请将结果 mod 10 ^ 9 + 7 后再返回。
     *
     * <p>示例 1：
     *
     * <p>输入: s = "ABC" 输出: 10 解释: 所有可能的子串为："A","B","C","AB","BC" 和 "ABC"。 其中，每一个子串都由独特字符构成。
     * 所以其长度总和为：1 + 1 + 1 + 2 + 2 + 3 = 10 示例 2：
     *
     * <p>输入: s = "ABA" 输出: 8 解释: 除了 countUniqueChars("ABA") = 1 之外，其余与示例 1 相同。 示例 3：
     *
     * <p>输入：s = "LEETCODE" 输出：92
     *
     * <p>提示：
     *
     * <p>0 <= s.length <= 10^4 s 只包含大写英文字符
     *
     * @param s
     * @return
     */
    public int uniqueLetterString(String s) {

        int[] letters = new int[26];

        // "LEETCODE"  len=8
        // 对于字符'L'，在区间[0,7]只出现一次，为答案贡献8(在该区间中,'L'可以存在于8个子串中)
        // 对于字符'E'，在区间[0,1]只出现一次，为答案贡献2
        // 对于字符'E'，在区间[2,6]只出现一次，为答案贡献5
        // 对于字符'T'，在区间[0,7]只出现一次，为答案贡献20 左边 4 右边5
        // 对于字符'C'，在区间[0,7]只出现一次，为答案贡献20 左边 5 右边4
        // 对于字符'O'，在区间[0,7]只出现一次，为答案贡献18
        // 对于字符'D'，在区间[0,7]只出现一次，为答案贡献14
        // 对于字符'E'，在区间[3,7]只出现一次，为答案贡献5
        // ans=8+2+5+20+20+18+14+5=92
        // 以每个字符为中心，向两边扩展到不重复为止
        int result = 0;
        char[] chars = s.toCharArray();
        int n = chars.length;
        for (int i = 0; i < n; i++) {
            int left = i - 1, right = i + 1;
            char c = chars[i];
            while (left >= 0 && chars[left] != c) {
                left--;
            }
            while (right < n && chars[right] != c) {
                right++;
            }
            result += (i - left) * (right - i) % MOD;
            result %= MOD;
        }

        return result;
    }
}
