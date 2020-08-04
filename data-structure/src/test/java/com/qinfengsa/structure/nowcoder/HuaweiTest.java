package com.qinfengsa.structure.nowcoder;

import static com.qinfengsa.structure.util.LogUtils.logResult;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * 牛客网 华为笔试面试
 *
 * @author qinfengsa
 * @date 2020/08/02 08:31
 */
@Slf4j
public class HuaweiTest {

    @Test
    public void calculate() {
        String expression = "400 + (-1 - 7) * 2 ";
        logResult(calculate(expression));
    }

    /**
     * 题目描述 给定一个字符串描述的算术表达式，计算出结果值。
     *
     * <p>输入字符串长度不超过100，合法的字符包括”+, -, *, /, (, )”，”0-9”，字符串内容的合法性及表达式语法的合法性由做题者检查。本题目只涉及整型计算。
     *
     * <p>输入描述: 输入算术表达式
     *
     * <p>输出描述: 计算出结果值
     *
     * <p>示例1 输入 400+5 输出 405
     *
     * @param expression
     * @return
     */
    public int calculate(String expression) {
        // 思路：
        // 1、去掉字符串中的空格，将字符串按照运算符和数字保存到vector<string>中；
        // 2、判断字符串是否符合四则运算的表达式；
        // 3、栈实现：数字栈、运算符栈，从左到右扫描中缀表达式；
        // 数字：直接入栈；运算符：
        // （1）”（“：直接入栈；
        // （2）”）“：弹出（）之间的运算符，进行运算；
        // （3）”+-*/“：如果当前运算符优先级<栈顶运算符优先级，则数字栈弹出两个数和栈顶运算符进行运算，并将结果存入数字运算符，再将当前运算符入栈；否则直接入栈。
        // 扫描结束后，如果运算符栈还有元素，则出栈运算。
        // 负数：支持5+-3这样的写法，扫描到”-“，判断”-“是减号还是负号；
        // 　　　”-“前面一个字符是数字或者”）“，则认为是减号；否则是负号；
        // 　　　如果当前字符是负号，将标志isNeg=true，则下一个数字存入 中时，加上负号。

        Deque<Character> optStack = new LinkedList<>();
        Deque<Integer> numStack = new LinkedList<>();
        int len = expression.length();
        char[] exp = expression.toCharArray();
        boolean isNeg = false;
        for (int i = 0; i < len; i++) {
            if (exp[i] == ' ') {
                continue;
            }
            if (exp[i] == '(') {
                optStack.push(exp[i]);
            } else if (exp[i] == ')') {
                while (!optStack.isEmpty() && optStack.peek() != '(') {
                    dealStack(optStack, numStack);
                }
                optStack.pop();
            } else if (exp[i] >= '0' && exp[i] <= '9') {
                int num = exp[i] - '0';
                while (i < len - 1 && exp[i + 1] >= '0' && exp[i + 1] <= '9') {
                    i++;
                    num = num * 10 + exp[i] - '0';
                }
                if (isNeg) {
                    num = -num;
                    isNeg = false;
                }
                numStack.push(num);
            } else {
                if (exp[i] == '-') {
                    // ”-“前面一个字符是数字或者”）“，则认为是减号；否则是负号；
                    int index = i - 1;
                    while (index >= 0 && exp[index] == ' ') {
                        index--;
                    }
                    if (index >= 0
                            && (exp[index] >= '0' && exp[index] <= '9' || exp[index] == ')')) {

                    } else {
                        isNeg = true;
                        continue;
                    }
                }

                while (!optStack.isEmpty() && getLevel(exp[i]) <= getLevel(optStack.peek())) {
                    dealStack(optStack, numStack);
                }
                optStack.push(exp[i]);
            }
        }
        while (!optStack.isEmpty()) {
            dealStack(optStack, numStack);
        }

        return numStack.pop();
    }

    /**
     * 对栈进行运算
     *
     * @param optStack
     * @param numStack
     */
    private void dealStack(Deque<Character> optStack, Deque<Integer> numStack) {
        char c = optStack.pop();
        int num1 = numStack.pop();
        int num2 = numStack.pop();
        switch (c) {
            case '+':
                numStack.push(num1 + num2);
                break;
            case '-':
                numStack.push(num2 - num1);
                break;
            case '*':
                numStack.push(num1 * num2);
                break;
            case '/':
                numStack.push(num2 / num1);
                break;
        }
    }

    /**
     * 优先集
     *
     * @param c
     * @return
     */
    static int getLevel(char c) {
        switch (c) {
            case '(':
                return 0;
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
        }
        return 0;
    }

    public int opNum(int a, int b, char c) {
        switch (c) {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
            case '/':
                if (b != 0) return a / b;
        }
        return -1;
    }

    @Test
    public void calculate2() {
        String expression = "3+2*{1+2*[-4/(8-6)+7]}";
        logResult(calculate2(expression));
    }

    /**
     * 四则运算
     *
     * <p>输入：strExpression：字符串格式的算术表达式，如: "3+2*{1+2*[-4/(8-6)+7]}" 返回：算术表达式的计算结果
     *
     * <p>pucExpression字符串中的有效字符包括[‘0’-‘9’],‘+’,‘-’, ‘*’,‘/’ ,‘(’， ‘)’,‘[’, ‘]’,‘{’ ,‘}’。
     *
     * <p>pucExpression算术表达式的有效性由调用者保证;
     *
     * @param expression
     * @return
     */
    public int calculate2(String expression) {
        expression =
                expression.replace('{', '(').replace('[', '(').replace('}', ')').replace(']', ')');
        log.debug("exp:{}", expression);
        Deque<Character> optStack = new LinkedList<>();
        Deque<Integer> numStack = new LinkedList<>();
        int len = expression.length();
        char[] exp = expression.toCharArray();
        boolean isNeg = false;
        for (int i = 0; i < len; i++) {
            if (exp[i] == ' ') {
                continue;
            }
            if (exp[i] == '(') {
                optStack.push(exp[i]);
            } else if (exp[i] == ')') {
                while (!optStack.isEmpty() && optStack.peek() != '(') {
                    dealStack(optStack, numStack);
                }
                optStack.pop();
            } else if (exp[i] >= '0' && exp[i] <= '9') {
                int num = exp[i] - '0';
                while (i < len - 1 && exp[i + 1] >= '0' && exp[i + 1] <= '9') {
                    i++;
                    num = num * 10 + exp[i] - '0';
                }
                if (isNeg) {
                    num = -num;
                    isNeg = false;
                }
                numStack.push(num);
            } else {
                if (exp[i] == '-') {
                    // ”-“前面一个字符是数字或者”）“，则认为是减号；否则是负号；
                    int index = i - 1;
                    while (index >= 0 && exp[index] == ' ') {
                        index--;
                    }
                    if (index >= 0
                            && (exp[index] >= '0' && exp[index] <= '9' || exp[index] == ')')) {

                    } else {
                        isNeg = true;
                        continue;
                    }
                }

                while (!optStack.isEmpty() && getLevel(exp[i]) <= getLevel(optStack.peek())) {
                    dealStack(optStack, numStack);
                }
                optStack.push(exp[i]);
            }
        }
        while (!optStack.isEmpty()) {
            dealStack(optStack, numStack);
        }

        return numStack.pop();
    }

    @Test
    public void getPassWord() {
        String password = "YUANzhi1987";
        logResult(getPassWord(password));
    }

    /**
     * HJ21 简单密码破解
     *
     * <p>密码是我们生活中非常重要的东东，我们的那么一点不能说的秘密就全靠它了。哇哈哈. 接下来渊子要在密码之上再加一套密码，虽然简单但也安全。
     *
     * <p>假设渊子原来一个BBS上的密码为zvbo9441987,为了方便记忆，他通过一种算法把这个密码变换成YUANzhi1987，这个密码是他的名字和出生年份，怎么忘都忘不了，而且可以明目张胆地放在显眼的地方而不被别人知道真正的密码。
     *
     * <p>他是这么变换的，大家都知道手机上的字母： 1--1， abc--2, def--3, ghi--4, jkl--5, mno--6, pqrs--7, tuv--8
     * wxyz--9, 0--0,就这么简单，渊子把密码中出现的小写字母都变成对应的数字，数字和其他的符号都不做变换，
     *
     * <p>声明：密码中没有空格，而密码中出现的大写字母则变成小写之后往后移一位，如：X，先变成小写，再往后移一位，不就是y了嘛，简单吧。记住，z往后移是a哦。
     *
     * <p>输入描述: 输入包括多个测试数据。输入是一个明文，密码长度不超过100个字符，输入直到文件结尾
     *
     * <p>输出描述: 输出渊子真正的密文
     *
     * <p>示例1 输入 YUANzhi1987 输出 zvbo9441987
     *
     * @param password
     * @return
     */
    public String getPassWord(String password) {
        StringBuilder sb = new StringBuilder();
        for (char c : password.toCharArray()) {
            if (phoneMap.containsKey(c)) {
                sb.append(phoneMap.get(c));
            } else if (c >= 'A' && c <= 'Z') {
                char s;
                if (c == 'Z') {
                    s = 'a';
                } else {
                    s = (char) ('a' + c - 'A' + 1);
                }
                sb.append(s);
            } else {
                sb.append(c);
            }
        }

        return sb.toString();
    }

    static Map<Character, Integer> phoneMap = new HashMap<>();

    static {
        phoneMap.put('a', 2);
        phoneMap.put('b', 2);
        phoneMap.put('c', 2);
        phoneMap.put('d', 3);
        phoneMap.put('e', 3);
        phoneMap.put('f', 3);
        phoneMap.put('g', 4);
        phoneMap.put('h', 4);
        phoneMap.put('i', 4);
        // jkl--5, mno--6, pqrs--7, tuv--8
        phoneMap.put('j', 5);
        phoneMap.put('k', 5);
        phoneMap.put('l', 5);
        // mno--6
        phoneMap.put('m', 6);
        phoneMap.put('n', 6);
        phoneMap.put('o', 6);
        // pqrs--7, tuv--8
        phoneMap.put('p', 7);
        phoneMap.put('q', 7);
        phoneMap.put('r', 7);
        phoneMap.put('s', 7);

        phoneMap.put('t', 8);
        phoneMap.put('u', 8);
        phoneMap.put('v', 8);
        // wxyz--9
        phoneMap.put('w', 9);
        phoneMap.put('x', 9);
        phoneMap.put('y', 9);
        phoneMap.put('z', 9);
    }

    @Test
    public void sortString() {
        String str = "A Famous Saying: Much Ado About Nothing (2012/8).";
        logResult(sortString(str));
    }

    /**
     * HJ26 字符串排序
     *
     * <p>编写一个程序，将输入字符串中的字符按如下规则排序。
     *
     * <p>规则 1 ：英文字母从 A 到 Z 排列，不区分大小写。
     *
     * <p>如，输入： Type 输出： epTy
     *
     * <p>规则 2 ：同一个英文字母的大小写同时存在时，按照输入顺序排列。
     *
     * <p>如，输入： BabA 输出： aABb
     *
     * <p>规则 3 ：非英文字母的其它字符保持原来的位置。
     *
     * <p>如，输入： By?e 输出： Be?y
     *
     * <p>注意有多组测试数据，即输入有多行，每一行单独处理（换行符隔开的表示不同行）
     *
     * <p>输入描述: 输入字符串 输出描述: 输出字符串 示例1 输入 复制 A Famous Saying: Much Ado About Nothing (2012/8). 输出 复制
     * A aaAAbc dFgghh: iimM nNn oooos Sttuuuy (2012/8).
     *
     * @param str
     * @return
     */
    public String sortString(String str) {
        char[] chars = str.toCharArray();
        List<Character> list = new ArrayList<>();
        for (char c : chars) {
            if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')) {
                list.add(c);
            }
        }
        Collections.sort(list, (a, b) -> Character.toLowerCase(a) - Character.toLowerCase(b));

        int index = 0;
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')) {
                chars[i] = list.get(index);
                index++;
            }
        }

        return new String(chars);
    }

    @Test
    public void sortImage() {
        String img = "Ihave1nose2hands10fingers";
        logResult(sortImage(img));
    }

    /**
     * HJ34 图片整理
     *
     * <p>Lily上课时使用字母数字图片教小朋友们学习英语单词，每次都需要把这些图片按照大小（ASCII码值从小到大）排列收好。请大家给Lily帮忙，通过C语言解决。
     *
     * <p>输入描述: Lily使用的图片包括"A"到"Z"、"a"到"z"、"0"到"9"。输入字母或数字个数不超过1024。
     *
     * <p>输出描述: Lily的所有图片按照从小到大的顺序输出
     *
     * <p>示例1 输入 复制 Ihave1nose2hands10fingers 输出 复制 0112Iaadeeefghhinnnorsssv
     *
     * @return
     */
    public String sortImage(String img) {
        StringBuilder sb = new StringBuilder();
        int[] chars = new int[128];
        for (char c : img.toCharArray()) {
            chars[c]++;
        }

        for (int i = 0; i < 128; i++) {
            int count = chars[i];
            if (count == 0) {
                continue;
            }
            char c = (char) i;
            for (int j = 0; j < count; j++) {
                sb.append(c);
            }
        }

        return sb.toString();
    }

    @Test
    public void getResult() {
        int n = 5;
        int[][] result = getResult(n);
        logResult(result);
        for (int i = 0; i < n; i++) {
            int[] nums = result[i];
        }
    }

    /**
     * HJ35 蛇形矩阵
     *
     * <p>题目说明
     *
     * <p>蛇形矩阵是由1开始的自然数依次排列成的一个矩阵上三角形。
     *
     * <p>样例输入
     *
     * <p>5
     *
     * <p>样例输出
     *
     * <p>1 3 6 10 15
     *
     * <p>2 5 9 14
     *
     * <p>4 8 13
     *
     * <p>7 12
     *
     * <p>11
     *
     * <p>接口说明
     *
     * <p>原型
     *
     * <p>void GetResult(int Num, char * pResult);
     *
     * <p>输入参数：
     *
     * <p>int Num：输入的正整数N
     *
     * <p>输出参数：
     *
     * <p>int * pResult：指向存放蛇形矩阵的字符串指针
     *
     * <p>指针指向的内存区域保证有效
     *
     * <p>返回值：
     *
     * <p>void
     *
     * <p>输入描述: 输入正整数N（N不大于100）
     *
     * <p>输出描述: 输出一个N行的蛇形矩阵。
     *
     * <p>示例1 输入 复制 4 输出 复制 1 3 6 10 2 5 9 4 8 7
     *
     * @param n
     * @return
     */
    public int[][] getResult(int n) {
        int[][] result = new int[n][];
        for (int i = 0; i < n; i++) {
            result[i] = new int[n - i];
        }
        int num = 1;
        for (int i = 1; i <= n; i++) {

            int row = i - 1, col = 0;
            for (int j = 0; j < i; j++) {
                result[row][col] = num++;
                row--;
                col++;
            }
        }

        return result;
    }

    @Test
    public void encrypt() {
        String key = "TRAILBLAZERS", data = "Attack AT DAWN";
        logResult(encrypt(key, data));
    }

    /**
     * HJ36 字符串加密
     *
     * <p>有一种技巧可以对数据进行加密，它使用一个单词作为它的密匙。下面是它的工作原理：首先，选择一个单词作为密匙，如TRAILBLAZERS。如果单词中包含有重复的字母，只保留第1个，其余几个丢弃。现在，修改过的那个单词属于字母表的下面，如下所示：
     *
     * <p>A B C D E F G H I J K L M N O P Q R S T U V W X Y Z
     *
     * <p>T R A I L B Z E S C D F G H J K M N O P Q U V W X Y
     *
     * <p>上面其他用字母表中剩余的字母填充完整。在对信息进行加密时，信息中的每个字母被固定于顶上那行，并用下面那行的对应字母一一取代原文的字母(字母字符的大小写状态应该保留)。因此，使用这个密匙，Attack
     * AT DAWN(黎明时攻击)就会被加密为Tpptad TP ITVH。
     *
     * <p>请实现下述接口，通过指定的密匙和明文得到密文。
     *
     * <p>详细描述：
     *
     * <p>接口说明
     *
     * <p>原型：
     *
     * <p>voidencrypt(char * key,char * data,char * encrypt);
     *
     * <p>输入参数：
     *
     * <p>char * key：密匙
     *
     * <p>char * data：明文
     *
     * <p>输出参数：
     *
     * <p>char * encrypt：密文
     *
     * <p>返回值：
     *
     * <p>void
     *
     * <p>输入描述: 先输入key和要加密的字符串
     *
     * <p>输出描述: 返回加密后的字符串
     *
     * <p>示例1 输入 复制 nihao ni 输出 复制 le
     *
     * @param key
     * @param data
     * @return
     */
    public String encrypt(String key, String data) {
        StringBuilder result = new StringBuilder();
        Set<Character> set = new HashSet<>();
        int index = 0;
        char[] chars = new char[26];
        for (char c : key.toCharArray()) {
            c = Character.toLowerCase(c);
            if (set.contains(c)) {
                continue;
            }
            chars[index++] = c;
            set.add(c);
        }
        for (int i = 0; i < 26; i++) {
            char c = (char) ('a' + i);
            if (set.contains(c)) {
                continue;
            }
            chars[index++] = c;
            set.add(c);
        }
        log.debug("char:{}", chars);
        char[] d = data.toCharArray();
        for (int i = 0; i < d.length; i++) {
            char c = d[i];
            if (c >= 'a' && c <= 'z') {
                int idx = c - 'a';
                d[i] = chars[idx];
            } else if (c >= 'A' && c <= 'Z') {
                int idx = c - 'A';
                d[i] = Character.toUpperCase(chars[idx]);
            }
        }

        return new String(d);
    }

    @Test
    public void getHeight() {
        int n = 1;
        getHeight(n);
    }

    public void getHeight(int n) {
        double h = (double) n;
        double total = 0.0;
        total += h;
        for (int i = 1; i < 5; i++) {
            total += h;
            h = h / 2.0;
        }
        log.debug("total :{}", total);
        log.debug("h :{}", h / 2.0);
    }

    @Test
    public void getButy() {
        String name = "lisi";
        logResult(getButy(name));
    }

    public int getButy(String name) {
        int[] letters = new int[26];
        for (char c : name.toCharArray()) {
            if (c >= 'a' && c <= 'z') {
                letters[c - 'a']++;
            } else if (c >= 'A' && c <= 'Z') {
                letters[c - 'A']++;
            }
        }
        Arrays.sort(letters);
        int n = 26;
        int idx = 25;
        int result = 0;
        while (idx >= 0 && letters[idx] != 0) {
            result += n * letters[idx];
            idx--;
            n--;
        }
        return result;
    }

    /**
     * HJ52 计算字符串的距离
     *
     * <p>Levenshtein
     * 距离，又称编辑距离，指的是两个字符串之间，由一个转换成另一个所需的最少编辑操作次数。许可的编辑操作包括将一个字符替换成另一个字符，插入一个字符，删除一个字符。编辑距离的算法是首先由俄国科学家Levenshtein提出的，故又叫Levenshtein
     * Distance。
     *
     * <p>Ex：
     *
     * <p>字符串A:abcdefg
     *
     * <p>字符串B: abcdef
     *
     * <p>通过增加或是删掉字符”g”的方式达到目的。这两种方案都需要一次操作。把这个操作所需要的次数定义为两个字符串的距离。
     *
     * <p>要求：
     *
     * <p>给定任意两个字符串，写出一个算法计算它们的编辑距离。
     *
     * @param word1
     * @param word2
     * @return
     */
    public int calStringDistance(String word1, String word2) {
        if (Objects.equals(word1, word2)) {
            return 0;
        }

        int len1 = word1.length(), len2 = word2.length();

        if (len1 * len2 == 0) {
            return len1 + len2;
        }
        char[] chars1 = word1.toCharArray();
        char[] chars2 = word2.toCharArray();
        int[][] dp = new int[len1 + 1][len2 + 1];
        for (int i = 0; i < len1 + 1; i++) {
            dp[i][0] = i;
        }
        for (int j = 0; j < len2 + 1; j++) {
            dp[0][j] = j;
        }
        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                // 删除当前字符
                int left = dp[i - 1][j] + 1;
                // 增加一个字符
                int up = dp[i][j - 1] + 1;

                int min = Math.min(left, up);
                int len = dp[i - 1][j - 1];
                if (chars1[i - 1] != chars2[j - 1]) {
                    len += 1;
                }
                min = Math.min(min, len);
                dp[i][j] = min;
            }
        }

        return dp[len1][len2];
    }

    @Test
    public void getLong() {
        String ip = "10.0.3.193";
        logResult(getLong(ip));
    }

    public long getLong(String ip) {
        String[] ips = ip.split("\\.");
        int len = ips.length;
        long num = 0L;
        if (len != 4) {
            return num;
        }
        for (int i = 0; i < 4; i++) {
            num = num * 256L + Long.parseLong(ips[i]);
        }

        return num;
    }

    public boolean check(int[] nums) {
        int sum1 = 0, sum2 = 0, other = 0;
        List<Integer> list = new ArrayList<>();
        for (int num : nums) {
            if (num % 5 == 0) {
                sum1 += num;
            } else if (num % 3 == 0) {
                sum2 += num;
            } else {
                other += num;
                list.add(num);
            }
        }
        if (list.size() == 0) {
            return sum1 == sum2;
        }
        // sum1 + x = sum2 + y; x + y = other
        // x - y = sum2 - sum1  2 x = sum2 - sum1 + other  y =
        int s = sum2 - sum1 + other;
        if ((s & 1) == 1) {
            return false;
        }

        int x = s >> 1;
        int y = other - x;
        // 动态规划
        int size = list.size();

        return f(0, size, list, 0, x);
    }

    public static boolean f(int i, int n, List<Integer> list, int result, int sum) {
        if (i == n) {
            return Math.abs(result) == sum; // 绝对值相等就可以
        } else {
            return f(i + 1, n, list, result + list.get(i), sum)
                    || f(i + 1, n, list, result - list.get(i), sum);
        }
    }
}
