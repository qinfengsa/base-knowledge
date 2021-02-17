package com.qinfengsa.structure.leetcode;

import static com.qinfengsa.structure.util.LogUtils.logResult;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
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
}
