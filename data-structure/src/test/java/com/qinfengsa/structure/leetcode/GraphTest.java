package com.qinfengsa.structure.leetcode;

import static com.qinfengsa.structure.util.LogUtils.logResult;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Queue;
import java.util.Set;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

/**
 * 图论算法
 *
 * @author: qinfengsa
 * @date: 2020/2/17 07:18
 */
@Slf4j
public class GraphTest {

    @Test
    public void ladderLength() {
        String beginWord = "hit";
        String endWord = "cog";
        List<String> wordList = new ArrayList<>();
        wordList.add("hot");
        wordList.add("dot");
        wordList.add("dog");
        wordList.add("lot");
        wordList.add("log");
        // wordList.add("cog");
        int result = ladderLength(beginWord, endWord, wordList);
        log.debug("result:{}", result);
    }
    /**
     * 单词接龙 给定两个单词（beginWord 和 endWord）和一个字典，找到从 beginWord 到 endWord 的最短转换序列的长度。转换需遵循如下规则：
     *
     * <p>每次转换只能改变一个字母。 转换过程中的中间单词必须是字典中的单词。 说明:
     *
     * <p>如果不存在这样的转换序列，返回 0。 所有单词具有相同的长度。 所有单词只由小写字母组成。 字典中不存在重复的单词。 你可以假设 beginWord 和 endWord
     * 是非空的，且二者不相同。 示例 1:
     *
     * <p>输入: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log","cog"]
     *
     * <p>输出: 5
     *
     * <p>解释: 一个最短转换序列是 "hit" -> "hot" -> "dot" -> "dog" -> "cog", 返回它的长度 5。 示例 2:
     *
     * <p>输入: beginWord = "hit" endWord = "cog" wordList = ["hot","dot","dog","lot","log"]
     *
     * <p>输出: 0
     *
     * <p>解释: endWord "cog" 不在字典中，所以无法进行转换。
     *
     * @param beginWord
     * @param endWord
     * @param wordList
     * @return
     */
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {

        if (!wordList.contains(endWord)) {
            return 0;
        }
        int len = beginWord.length();
        Map<String, List<String>> allComboDict = new HashMap<>();
        // 用 h*t - hot 分别作k-v
        wordList.forEach(
                word -> {
                    for (int i = 0; i < len; i++) {
                        String key = word.substring(0, i) + "*" + word.substring(i + 1, len);
                        List<String> dict =
                                allComboDict.computeIfAbsent(key, k -> new ArrayList<>());
                        dict.add(word);
                    }
                });
        int steps = 1;
        // 广度优先遍历
        Queue<String> queue1 = new LinkedList<>();
        queue1.offer(beginWord); // beginWord 入队

        // 访问word
        Set<String> visited = new HashSet<>();
        visited.add(beginWord);
        while (!queue1.isEmpty()) {
            int size = queue1.size();
            for (int i = 0; i < size; i++) {
                // 出队
                String curWord = queue1.poll();
                // 如果当前word和 endWord 返回
                if (Objects.equals(curWord, endWord)) {
                    return steps;
                }
                for (int j = 0; j < len; j++) {
                    String key = curWord.substring(0, j) + "*" + curWord.substring(j + 1, len);
                    List<String> dict = allComboDict.get(key);
                    if (dict != null) {
                        for (String s : dict) {
                            if (!visited.contains(s)) {
                                queue1.offer(s);
                                visited.add(s);
                            }
                        }
                    }
                }
            }
            steps++;
        }

        return 0;
    }

    @Test
    public void canFinish() {
        int numCourses = 4;
        int[][] prerequisites = {{1, 0}, {2, 0}, {3, 1}, {3, 2}};
        // int[][] prerequisites ={{1,0}  };
        boolean result = canFinish(numCourses, prerequisites);
        log.debug("result:{}", result);
    }
    /**
     * 课程表 现在你总共有 n 门课需要选，记为 0 到 n-1。
     *
     * <p>在选修某些课程之前需要一些先修课程。 例如，想要学习课程 0 ，你需要先完成课程 1 ，我们用一个匹配来表示他们: [0,1]
     *
     * <p>给定课程总量以及它们的先决条件，判断是否可能完成所有课程的学习？
     *
     * <p>示例 1:
     *
     * <p>输入: 2, [[1,0]] 输出: true 解释: 总共有 2 门课程。学习课程 1 之前，你需要完成课程 0。所以这是可能的。 示例 2:
     *
     * <p>输入: 2, [[1,0],[0,1]] 输出: false 解释: 总共有 2 门课程。学习课程 1 之前，你需要先完成​课程 0；并且学习课程 0 之前，你还应先完成课程
     * 1。这是不可能的。 说明:
     *
     * <p>输入的先决条件是由边缘列表表示的图形，而不是邻接矩阵。详情请参见图的表示法。 你可以假定输入的先决条件中没有重复的边。 提示:
     *
     * <p>这个问题相当于查找一个循环是否存在于有向图中。如果存在循环，则不存在拓扑排序，因此不可能选取所有课程进行学习。 通过 DFS 进行拓扑排序 -
     * 一个关于Coursera的精彩视频教程（21分钟），介绍拓扑排序的基本概念。 拓扑排序也可以通过 BFS 完成。
     *
     * @param numCourses
     * @param prerequisites
     * @return
     */
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        int[] indegrees = new int[numCourses];
        for (int[] p : prerequisites) {
            indegrees[p[0]]++;
        }
        LinkedList<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (indegrees[i] == 0) {
                queue.addLast(i);
            }
        }
        while (!queue.isEmpty()) {
            Integer pre = queue.removeFirst();
            numCourses--;
            for (int[] req : prerequisites) {
                if (req[1] != pre) continue;
                if (--indegrees[req[0]] == 0) {
                    queue.add(req[0]);
                }
            }
        }
        return numCourses == 0;
    }

    public boolean dfs(int num, List<Integer>[] courses, Set<Integer> canFi, Set<Integer> visited) {
        if (canFi.contains(num)) {
            return true;
        }
        if (visited.contains(num)) {
            return false;
        }
        List<Integer> courseList = courses[num];
        visited.add(num);
        if (courseList != null) {
            for (Integer course : courseList) {
                boolean b = dfs(course, courses, canFi, visited);
                if (!b) {
                    visited.remove(num);
                    return false;
                }
            }
        }
        canFi.add(num);
        return true;
    }

    @Test
    public void findOrder() {
        int numCourses = 4;
        int[][] prerequisites = {{1, 0}, {2, 0}, {3, 1}, {3, 2}};
        // int[][] prerequisites ={{1,0},{0,1}  };
        int[] result = findOrder(numCourses, prerequisites);
        log.debug("result:{}", result);
    }

    /**
     * 课程表 II 现在你总共有 n 门课需要选，记为 0 到 n-1。
     *
     * <p>在选修某些课程之前需要一些先修课程。 例如，想要学习课程 0 ，你需要先完成课程 1 ，我们用一个匹配来表示他们: [0,1]
     *
     * <p>给定课程总量以及它们的先决条件，返回你为了学完所有课程所安排的学习顺序。
     *
     * <p>可能会有多个正确的顺序，你只要返回一种就可以了。如果不可能完成所有课程，返回一个空数组。
     *
     * <p>示例 1:
     *
     * <p>输入: 2, [[1,0]] 输出: [0,1] 解释: 总共有 2 门课程。要学习课程 1，你需要先完成课程 0。因此，正确的课程顺序为 [0,1] 。 示例 2:
     *
     * <p>输入: 4, [[1,0],[2,0],[3,1],[3,2]] 输出: [0,1,2,3] or [0,2,1,3] 解释: 总共有 4 门课程。要学习课程 3，你应该先完成课程
     * 1 和课程 2。并且课程 1 和课程 2 都应该排在课程 0 之后。 因此，一个正确的课程顺序是 [0,1,2,3] 。另一个正确的排序是 [0,2,1,3] 。 说明:
     *
     * <p>输入的先决条件是由边缘列表表示的图形，而不是邻接矩阵。详情请参见图的表示法。 你可以假定输入的先决条件中没有重复的边。 提示:
     *
     * <p>这个问题相当于查找一个循环是否存在于有向图中。如果存在循环，则不存在拓扑排序，因此不可能选取所有课程进行学习。 通过 DFS 进行拓扑排序 -
     * 一个关于Coursera的精彩视频教程（21分钟），介绍拓扑排序的基本概念。 拓扑排序也可以通过 BFS 完成。
     *
     * @param numCourses
     * @param prerequisites
     * @return
     */
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        /*int[] indegrees = new int[numCourses];
        for(int[] p : prerequisites) {
            indegrees[p[0]]++;
        }
        int[] result = new int[numCourses];

        int index = 0;
        Deque<Integer> queue = new LinkedList<>();
        for(int i = 0; i < numCourses; i++){
            if(indegrees[i] == 0) {
                queue.addLast(i);
            }
        }

        // 广度优先遍历
        while(!queue.isEmpty()) {
            Integer pre = queue.removeFirst();
            result[index++] = pre;
            for(int[] req : prerequisites) {
                if(req[1] != pre) continue;
                if(--indegrees[req[0]] == 0) {
                    queue.add(req[0]);
                }
            }
        }
        if (index < numCourses) {
            return new int[0];
        }

        return result;*/

        int[] inDegrees = new int[numCourses];
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int[] p : prerequisites) {
            inDegrees[p[0]]++;
            List<Integer> list = map.computeIfAbsent(p[1], key -> new ArrayList<>());
            list.add(p[0]);
        }
        // 队列 广度优先遍历
        Deque<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (inDegrees[i] == 0) {
                queue.addLast(i);
            }
        }
        int[] result = new int[numCourses];
        boolean[] visit = new boolean[numCourses];
        int index = 0;
        while (!queue.isEmpty()) {
            Integer pre = queue.poll();
            result[index++] = pre;
            if (map.containsKey(pre)) {
                List<Integer> list = map.get(pre);
                for (int i = 0; i < list.size(); i++) {

                    if (!visit[list.get(i)]) {
                        queue.offer(list.get(i));
                    }
                    visit[list.get(i)] = true;
                }
            }
        }
        if (index < numCourses) {
            return new int[0];
        }

        return result;
    }

    @Test
    public void solve() {
        char[][] board = {
            {'X', 'X', 'X', 'X'}, {'X', 'O', 'O', 'X'}, {'X', 'X', 'O', 'X'}, {'X', 'O', 'X', 'X'}
        };
        solve(board);
        for (char[] a : board) {
            log.debug("borad:{}", a);
        }
    }

    /**
     * 被围绕的区域 给定一个二维的矩阵，包含 'X' 和 'O'（字母 O）。
     *
     * <p>找到所有被 'X' 围绕的区域，并将这些区域里所有的 'O' 用 'X' 填充。
     *
     * <p>示例:
     *
     * <p>X X X X X O O X X X O X X O X X 运行你的函数后，矩阵变为：
     *
     * <p>X X X X X X X X X X X X X O X X 解释:
     *
     * <p>被围绕的区间不会存在于边界上，换句话说，任何边界上的 'O' 都不会被填充为 'X'。 任何不在边界上，或不与边界上的 'O' 相连的 'O' 最终都会被填充为
     * 'X'。如果两个元素在水平或垂直方向相邻，则称它们是“相连”的。
     *
     * @param board
     */
    public void solve(char[][] board) {
        int rows = board.length;
        if (rows == 0) {
            return;
        }
        int cols = board[0].length;
        // 思路, 算边界的 O
        // 第一列 和 最后一列
        for (int i = 0; i < rows; i++) {
            if (board[i][0] == 'O') {
                updateAround(board, i, 0);
            }
            if (board[i][cols - 1] == 'O') {
                updateAround(board, i, cols - 1);
            }
        }
        // 第一行 和 最后一行
        for (int i = 1; i < cols - 1; i++) {
            if (board[0][i] == 'O') {
                updateAround(board, 0, i);
            }
            if (board[rows - 1][i] == 'O') {
                updateAround(board, rows - 1, i);
            }
        }
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (board[i][j] == '*') {
                    board[i][j] = 'O';
                } else if (board[i][j] != 'X') {
                    board[i][j] = 'X';
                }
            }
        }
    }

    private void updateAround(char[][] board, int row, int col) {
        int rows = board.length;
        int cols = board[0].length;
        if (row < 0 || row >= rows) {
            return;
        }
        if (col < 0 || col >= cols) {
            return;
        }

        if (board[row][col] == 'O') {
            // 更新坐标
            board[row][col] = '*';
            // 更新相邻坐标
            updateAround(board, row - 1, col);
            updateAround(board, row + 1, col);
            updateAround(board, row, col - 1);
            updateAround(board, row, col + 1);
        }
    }

    /*// 更新相邻坐标
    updateAround(grid,row - 1,col);
    updateAround(grid,row + 1,col);
    updateAround(grid,row,col - 1);
    updateAround(grid,row ,col + 1);*/

    /**
     * 997. 找到小镇的法官 在一个小镇里，按从 1 到 N 标记了 N 个人。传言称，这些人中有一个是小镇上的秘密法官。
     *
     * <p>如果小镇的法官真的存在，那么：
     *
     * <p>小镇的法官不相信任何人。 每个人（除了小镇法官外）都信任小镇的法官。 只有一个人同时满足属性 1 和属性 2 。 给定数组 trust，该数组由信任对 trust[i] = [a,
     * b] 组成，表示标记为 a 的人信任标记为 b 的人。
     *
     * <p>如果小镇存在秘密法官并且可以确定他的身份，请返回该法官的标记。否则，返回 -1。
     *
     * <p>示例 1：
     *
     * <p>输入：N = 2, trust = [[1,2]] 输出：2 示例 2：
     *
     * <p>输入：N = 3, trust = [[1,3],[2,3]] 输出：3 示例 3：
     *
     * <p>输入：N = 3, trust = [[1,3],[2,3],[3,1]] 输出：-1 示例 4：
     *
     * <p>输入：N = 3, trust = [[1,2],[2,3]] 输出：-1 示例 5：
     *
     * <p>输入：N = 4, trust = [[1,3],[1,4],[2,3],[2,4],[4,3]] 输出：3
     *
     * <p>提示：
     *
     * <p>1 <= N <= 1000 trust.length <= 10000 trust[i] 是完全不同的 trust[i][0] != trust[i][1] 1 <=
     * trust[i][0], trust[i][1] <= N
     *
     * @param N
     * @param trust
     * @return
     */
    public int findJudge(int N, int[][] trust) {
        // 法官是这样一个点：出度为0，并且入度为N-1
        int[] inDegree = new int[N + 1];
        int[] outDegree = new int[N + 1];
        for (int[] trustPerson : trust) {
            outDegree[trustPerson[0]]++;
            inDegree[trustPerson[1]]++;
        }

        for (int i = 1; i <= N; i++) {
            if (inDegree[i] == N - 1 && outDegree[i] == 0) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 1042. 不邻接植花 有 N 个花园，按从 1 到 N 标记。在每个花园中，你打算种下四种花之一。
     *
     * <p>paths[i] = [x, y] 描述了花园 x 到花园 y 的双向路径。
     *
     * <p>另外，没有花园有 3 条以上的路径可以进入或者离开。
     *
     * <p>你需要为每个花园选择一种花，使得通过路径相连的任何两个花园中的花的种类互不相同。
     *
     * <p>以数组形式返回选择的方案作为答案 answer，其中 answer[i] 为在第 (i+1) 个花园中种植的花的种类。 花的种类用 1, 2, 3, 4 表示。保证存在答案。
     *
     * <p>示例 1：
     *
     * <p>输入：N = 3, paths = [[1,2],[2,3],[3,1]] 输出：[1,2,3] 示例 2：
     *
     * <p>输入：N = 4, paths = [[1,2],[3,4]] 输出：[1,2,1,2] 示例 3：
     *
     * <p>输入：N = 4, paths = [[1,2],[2,3],[3,4],[4,1],[1,3],[2,4]] 输出：[1,2,3,4]
     *
     * <p>提示：
     *
     * <p>1 <= N <= 10000 0 <= paths.size <= 20000 不存在花园有 4 条或者更多路径可以进入或离开。 保证存在答案。
     *
     * @param N
     * @param paths
     * @return
     */
    public int[] gardenNoAdj(int N, int[][] paths) {
        Map<Integer, Set<Integer>> graph = new HashMap<>();

        for (int i = 0; i < N; i++) {
            graph.put(i, new HashSet<>());
        }

        for (int[] path : paths) {
            int a = path[0] - 1, b = path[1] - 1;
            graph.get(a).add(b);
            graph.get(b).add(a);
        }
        int[] result = new int[N];

        for (int i = 0; i < N; i++) {
            boolean[] used = new boolean[5];
            Set<Integer> set = graph.get(i);
            for (int adj : set) {
                used[result[adj]] = true;
            }
            /* 为当前节点染色 */
            for (int j = 1; j <= 4; j++) {
                if (!used[j]) {
                    result[i] = j;
                }
            }
        }

        return result;
    }

    /**
     * 5400. 旅行终点站 给你一份旅游线路图，该线路图中的旅行线路用数组 paths 表示，其中 paths[i] = [cityAi, cityBi] 表示该线路将会从 cityAi
     * 直接前往 cityBi 。请你找出这次旅行的终点站，即没有任何可以通往其他城市的线路的城市。
     *
     * <p>题目数据保证线路图会形成一条不存在循环的线路，因此只会有一个旅行终点站。
     *
     * <p>示例 1：
     *
     * <p>输入：paths = [["London","New York"],["New York","Lima"],["Lima","Sao Paulo"]] 输出："Sao Paulo"
     * 解释：从 "London" 出发，最后抵达终点站 "Sao Paulo" 。本次旅行的路线是 "London" -> "New York" -> "Lima" -> "Sao
     * Paulo" 。 示例 2：
     *
     * <p>输入：paths = [["B","C"],["D","B"],["C","A"]] 输出："A" 解释：所有可能的线路是： "D" -> "B" -> "C" -> "A".
     * "B" -> "C" -> "A". "C" -> "A". "A". 显然，旅行终点站是 "A" 。 示例 3：
     *
     * <p>输入：paths = [["A","Z"]] 输出："Z"
     *
     * <p>提示：
     *
     * <p>1 <= paths.length <= 100 paths[i].length == 2 1 <= cityAi.length, cityBi.length <= 10
     * cityAi != cityBi 所有字符串均由大小写英文字母和空格字符组成。
     *
     * @param paths
     * @return
     */
    public String destCity(List<List<String>> paths) {
        // 计算出度为0的城市 入度
        String result = "";
        Map<String, Integer> outMap = new HashMap<>();
        Map<String, Integer> inMap = new HashMap<>();
        paths.forEach(
                path -> {
                    int count1 = outMap.getOrDefault(path.get(0), 0);
                    outMap.put(path.get(0), count1 + 1);
                    int count2 = inMap.getOrDefault(path.get(1), 0);
                    inMap.put(path.get(1), count2 + 1);
                });
        for (String key : inMap.keySet()) {
            if (!outMap.containsKey(key)) {
                result = key;
            }
        }

        return result;
    }

    /**
     * 332. 重新安排行程 给定一个机票的字符串二维数组 [from, to]，子数组中的两个成员分别表示飞机出发和降落的机场地点，对该行程进行重新规划排序。
     * 所有这些机票都属于一个从JFK（肯尼迪国际机场）出发的先生，所以该行程必须从 JFK 出发。
     *
     * <p>说明:
     *
     * <p>如果存在多种有效的行程，你可以按字符自然排序返回最小的行程组合。例如，行程 ["JFK", "LGA"] 与 ["JFK", "LGB"] 相比就更小，排序更靠前
     * 所有的机场都用三个大写字母表示（机场代码）。 假定所有机票至少存在一种合理的行程。 示例 1:
     *
     * <p>输入: [["MUC", "LHR"], ["JFK", "MUC"], ["SFO", "SJC"], ["LHR", "SFO"]] 输出: ["JFK", "MUC",
     * "LHR", "SFO", "SJC"] 示例 2:
     *
     * <p>输入: [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]] 输出:
     * ["JFK","ATL","JFK","SFO","ATL","SFO"] 解释: 另一种有效的行程是
     * ["JFK","SFO","ATL","JFK","ATL","SFO"]。但是它自然排序更大更靠后。
     *
     * @param tickets
     * @return
     */
    public List<String> findItinerary(List<List<String>> tickets) {
        List<String> result = new ArrayList<>();
        if (tickets.isEmpty()) {
            return result;
        }
        Map<String, List<String>> graph = new HashMap<>();
        for (List<String> ticket : tickets) {
            String start = ticket.get(0);
            String end = ticket.get(1);
            List<String> list = graph.computeIfAbsent(start, key -> new ArrayList<>());
            list.add(end);
        }

        graph.values().forEach(values -> values.sort(String::compareTo));

        // 开始深度优先遍历
        visitItinerary(graph, "JFK", result);

        return result;
    }

    private void visitItinerary(
            Map<String, List<String>> graph, String start, List<String> result) {
        List<String> list = graph.get(start);
        while (Objects.nonNull(list) && list.size() > 0) {
            visitItinerary(graph, list.remove(0), result);
        }
        result.add(0, start);
    }

    /**
     * 310. 最小高度树
     * 对于一个具有树特征的无向图，我们可选择任何一个节点作为根。图因此可以成为树，在所有可能的树中，具有最小高度的树被称为最小高度树。给出这样的一个图，写出一个函数找到所有的最小高度树并返回他们的根节点。
     *
     * <p>格式
     *
     * <p>该图包含 n 个节点，标记为 0 到 n - 1。给定数字 n 和一个无向边 edges 列表（每一个边都是一对标签）。
     *
     * <p>你可以假设没有重复的边会出现在 edges 中。由于所有的边都是无向边， [0, 1]和 [1, 0] 是相同的，因此不会同时出现在 edges 里。
     *
     * <p>示例 1:
     *
     * <p>输入: n = 4, edges = [[1, 0], [1, 2], [1, 3]]
     *
     * <p>0 | 1 / \ 2 3
     *
     * <p>输出: [1] 示例 2:
     *
     * <p>输入: n = 6, edges = [[0, 3], [1, 3], [2, 3], [4, 3], [5, 4]]
     *
     * <p>0 1 2 \ | / 3 | 4 | 5
     *
     * <p>输出: [3, 4] 说明:
     *
     * <p>根据树的定义，树是一个无向图，其中任何两个顶点只通过一条路径连接。 换句话说，一个任何没有简单环路的连通图都是一棵树。 树的高度是指根节点和叶子节点之间最长向下路径上边的数量。
     *
     * @param n
     * @param edges
     * @return
     */
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        List<Integer> result = new ArrayList<>();
        if (n == 1) {
            result.add(0);
            return result;
        }
        int[] degree = new int[n];
        Map<Integer, List<Integer>> graph = new HashMap<>();

        for (int[] edge : edges) {
            degree[edge[0]]++;
            degree[edge[1]]++;
            List<Integer> list1 = graph.computeIfAbsent(edge[0], k -> new ArrayList<>());
            list1.add(edge[1]);
            List<Integer> list2 = graph.computeIfAbsent(edge[1], k -> new ArrayList<>());
            list2.add(edge[0]);
        }
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (degree[i] == 1) {
                queue.offer(i);
            }
        }
        while (!queue.isEmpty()) {
            // int edge = queue.poll();
            result = new ArrayList<>();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int curV = queue.poll();
                result.add(curV);
                for (int tmp : graph.get(curV)) {
                    degree[tmp] -= 1;
                    if (degree[tmp] == 1) {
                        queue.offer(tmp);
                    }
                }
            }
        }

        return result;
    }

    @Test
    public void checkIfPrerequisite() {
        int n = 5;
        int[][] prerequisites = {
            {4, 3}, {4, 1}, {4, 0}, {3, 2}, {3, 1}, {3, 0}, {2, 1}, {2, 0}, {1, 0}
        };
        ;
        int[][] queries = {{1, 4}, {4, 2}, {0, 1}, {4, 0}, {0, 2}, {1, 3}, {0, 1}};

        logResult(checkIfPrerequisite(n, prerequisites, queries));
    }

    /**
     * 5410. 课程安排 IV 你总共需要上 n 门课，课程编号依次为 0 到 n-1 。
     *
     * <p>有的课会有直接的先修课程，比如如果想上课程 0 ，你必须先上课程 1 ，那么会以 [1,0] 数对的形式给出先修课程数对。
     *
     * <p>给你课程总数 n 和一个直接先修课程数对列表 prerequisite 和一个查询对列表 queries 。
     *
     * <p>对于每个查询对 queries[i] ，请判断 queries[i][0] 是否是 queries[i][1] 的先修课程。
     *
     * <p>请返回一个布尔值列表，列表中每个元素依次分别对应 queries 每个查询对的判断结果。
     *
     * <p>注意：如果课程 a 是课程 b 的先修课程且课程 b 是课程 c 的先修课程，那么课程 a 也是课程 c 的先修课程。
     *
     * <p>示例 1：
     *
     * <p>输入：n = 2, prerequisites = [[1,0]], queries = [[0,1],[1,0]] 输出：[false,true] 解释：课程 0 不是课程 1
     * 的先修课程，但课程 1 是课程 0 的先修课程。 示例 2：
     *
     * <p>输入：n = 2, prerequisites = [], queries = [[1,0],[0,1]] 输出：[false,false]
     * 解释：没有先修课程对，所以每门课程之间是独立的。 示例 3：
     *
     * <p>输入：n = 3, prerequisites = [[1,2],[1,0],[2,0]], queries = [[1,0],[1,2]] 输出：[true,true] 示例
     * 4：
     *
     * <p>输入：n = 3, prerequisites = [[1,0],[2,0]], queries = [[0,1],[2,0]] 输出：[false,true] 示例 5：
     *
     * <p>输入：n = 5, prerequisites = [[0,1],[1,2],[2,3],[3,4]], queries = [[0,4],[4,0],[1,3],[3,0]]
     * 输出：[true,false,true,false]
     *
     * <p>提示：
     *
     * <p>2 <= n <= 100 0 <= prerequisite.length <= (n * (n - 1) / 2) 0 <= prerequisite[i][0],
     * prerequisite[i][1] < n prerequisite[i][0] != prerequisite[i][1] 先修课程图中没有环。 先修课程图中没有重复的边。 1 <=
     * queries.length <= 10^4 queries[i][0] != queries[i][1]
     *
     * @param n
     * @param prerequisites
     * @param queries
     * @return
     */
    public List<Boolean> checkIfPrerequisite(int n, int[][] prerequisites, int[][] queries) {
        boolean[][] graph = new boolean[n][n];

        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int[] prerequisite : prerequisites) {
            int row = prerequisite[0], col = prerequisite[1];

            List<Integer> list = map.computeIfAbsent(row, k -> new ArrayList<>());

            list.add(col);
            graph[row][col] = true;
        }
        // 需要加记忆
        boolean[][] visited = new boolean[n][n];
        for (int[] prerequisite : prerequisites) {
            int row = prerequisite[0], col = prerequisite[1];
            graph[row][col] = true;

            updateMap(map, row, col, graph, visited);
        }
        logResult(graph);
        List<Boolean> result = new ArrayList<>();
        for (int[] query : queries) {
            int row = query[0], col = query[1];
            result.add(graph[row][col]);
        }
        return result;
    }

    private void updateMap(
            Map<Integer, List<Integer>> map,
            int row,
            int col,
            boolean[][] graph,
            boolean[][] visited) {
        if (visited[row][col]) {
            return;
        }
        visited[row][col] = true;

        if (!map.containsKey(col)) {
            return;
        }
        for (Integer col1 : map.get(col)) {
            graph[row][col1] = true;
            updateMap(map, row, col1, graph, visited);
        }
    }

    @Test
    public void minReorder() {
        int n = 6;
        int[][] connections = {{1, 0}, {1, 2}, {3, 2}, {3, 4}};
        logResult(minReorder(n, connections));
    }

    /**
     * 5426. 重新规划路线 n 座城市，从 0 到 n-1 编号，其间共有 n-1 条路线。因此，要想在两座不同城市之间旅行只有唯一一条路线可供选择（路线网形成一颗树）。
     * 去年，交通运输部决定重新规划路线，以改变交通拥堵的状况。
     *
     * <p>路线用 connections 表示，其中 connections[i] = [a, b] 表示从城市 a 到 b 的一条有向路线。
     *
     * <p>今年，城市 0 将会举办一场大型比赛，很多游客都想前往城市 0 。
     *
     * <p>请你帮助重新规划路线方向，使每个城市都可以访问城市 0 。返回需要变更方向的最小路线数。
     *
     * <p>题目数据 保证 每个城市在重新规划路线方向后都能到达城市 0 。
     *
     * <p>示例 1：
     *
     * <p>输入：n = 6, connections = [[0,1],[1,3],[2,3],[4,0],[4,5]] 输出：3 解释：更改以红色显示的路线的方向，使每个城市都可以到达城市
     * 0 。 示例 2：
     *
     * <p>输入：n = 5, connections = [[1,0],[1,2],[3,2],[3,4]] 输出：2 解释：更改以红色显示的路线的方向，使每个城市都可以到达城市 0 。
     * 示例 3：
     *
     * <p>输入：n = 3, connections = [[1,0],[2,0]] 输出：0
     *
     * <p>提示：
     *
     * <p>2 <= n <= 5 * 10^4 connections.length == n-1 connections[i].length == 2 0 <=
     * connections[i][0], connections[i][1] <= n-1 connections[i][0] != connections[i][1]
     *
     * @param n
     * @param connections
     * @return
     */
    public int minReorder(int n, int[][] connections) {
        boolean[] visited = new boolean[n + 1];
        visited[0] = true;
        int min = 0;
        Map<Integer, Set<Integer>> setMap = new HashMap<>();
        for (int[] connection : connections) {
            int from = connection[0], to = connection[1];
            if (from == 0) {
                min++;
                from = to;
                to = 0;
            }
            if (to == 0) {
                visited[from] = true;
                continue;
            }
            Set<Integer> set = setMap.computeIfAbsent(from, k -> new HashSet<>());
            set.add(to);
        }
        log.debug("visited :{}", visited);
        log.debug("setMap :{}", setMap);
        //
        while (!setMap.isEmpty()) {
            Set<Integer> fromSet = new HashSet<>(setMap.keySet());
            for (int from : fromSet) {
                if (visited[from]) {
                    Set<Integer> tos = setMap.get(from);
                    min += tos.size();
                    for (int to : setMap.get(from)) {
                        visited[to] = true;
                    }
                    setMap.remove(from);
                } else {
                    Set<Integer> tos = setMap.get(from);
                    for (Iterator<Integer> iterator = tos.iterator(); iterator.hasNext(); ) {
                        if (visited[iterator.next()]) {
                            visited[from] = true;
                            iterator.remove();
                        }
                    }
                    if (tos.isEmpty()) {
                        setMap.remove(from);
                    }
                }
            }
        }

        return min;
    }

    @Test
    public void calcEquation() {
        List<List<String>> equations = new ArrayList<>();
        equations.add(Arrays.asList("a", "b"));
        equations.add(Arrays.asList("b", "c"));
        double[] values = {2.0, 3.0};
        List<List<String>> queries = new ArrayList<>();
        queries.add(Arrays.asList("a", "c"));
        queries.add(Arrays.asList("b", "a"));
        queries.add(Arrays.asList("a", "e"));
        queries.add(Arrays.asList("a", "a"));
        queries.add(Arrays.asList("x", "x"));
        log.debug("result :{} ", calcEquation(equations, values, queries));
    }

    /**
     * 399. 除法求值 给出方程式 A / B = k, 其中 A 和 B 均为用字符串表示的变量， k 是一个浮点型数字。根据已知方程式求解问题，并返回计算结果。如果结果不存在，则返回
     * -1.0。
     *
     * <p>示例 : 给定 a / b = 2.0, b / c = 3.0 问题: a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ?
     * 返回 [6.0, 0.5, -1.0, 1.0, -1.0 ]
     *
     * <p>输入为: vector<pair<string, string>> equations, vector<double>& values, vector<pair<string,
     * string>> queries(方程式， 方程式结果，问题方程式)， 其中 equations.size() ==
     * values.size()，即方程式的长度与方程式结果长度相等（程式与结果一一对应），并且结果值均为正数。 以上为方程式的描述。 返回vector<double>类型。
     *
     * <p>基于上述例子，输入如下：
     *
     * <p>equations(方程式) = [ ["a", "b"], ["b", "c"] ], values(方程式结果) = [2.0, 3.0], queries(问题方程式) =
     * [ ["a", "c"], ["b", "a"], ["a", "e"], ["a", "a"], ["x", "x"] ].
     * 输入总是有效的。你可以假设除法运算中不会出现除数为0的情况，且不存在任何矛盾的结果。
     *
     * @param equations
     * @param values
     * @param queries
     * @return
     */
    public double[] calcEquation(
            List<List<String>> equations, double[] values, List<List<String>> queries) {
        double[] result = new double[queries.size()];
        // 创建 c -> b -> a -> a 的 单向路径, 记录 路径的value, 通过路径求对应的结果
        for (int i = 0; i < equations.size(); i++) {
            String dividend = equations.get(i).get(0);
            String divisor = equations.get(i).get(1);
            union(dividend, divisor, values[i]);
        }
        for (int i = 0; i < queries.size(); i++) {
            String dividend = queries.get(i).get(0);
            String divisor = queries.get(i).get(1);
            if (!childToParents.containsKey(dividend) || !childToParents.containsKey(divisor)) {
                result[i] = -1.0;
                continue;
            }
            if (Objects.equals(dividend, divisor)) {
                result[i] = 1.0;
                continue;
            }

            String p = getRoot(dividend);
            String c = getRoot(divisor);
            if (!Objects.equals(p, c)) {
                result[i] = -1.0;
                continue;
            }
            result[i] = pm(divisor) / pm(dividend);
        }

        return result;
    }

    private double pm(String x) {
        return childToParents.get(x).equals(x) ? 1.0 : values.get(x) * pm(childToParents.get(x));
    }

    private void union(String parent, String child, double value) {
        add(parent);
        add(child);

        String root1 = getRoot(parent);
        String root2 = getRoot(child);

        if (!Objects.equals(root1, root2)) {
            childToParents.put(root2, root1);
            values.put(root2, value * (pm(parent) / pm(child)));
        }
    }

    private void add(String x) {
        if (!childToParents.containsKey(x)) {
            childToParents.put(x, x);
            values.put(x, 1.0);
        }
    }

    private String getRoot(String child) {
        while (!child.equals(childToParents.get(child))) {
            child = childToParents.get(child);
        }
        return child;
    }

    /** key : 当前节点 value : 其父节点 除了root -> root 节点,其它节点都是 单向连通 */
    private Map<String, String> childToParents = new HashMap<>();
    /** key : 当前节点 value : 父节点/当前节点 */
    private Map<String, Double> values = new HashMap<>();

    @Test
    public void findWhetherExistsPath() {
        int n = 5;
        int[][] graph = {{0, 1}, {0, 2}, {0, 1}, {1, 3}, {1, 4}, {1, 3}, {2, 3}, {3, 4}};
        int start = 0, target = 4;
        logResult(findWhetherExistsPath(n, graph, start, target));
    }

    /**
     * 面试题 04.01. 节点间通路
     *
     * <p>节点间通路。给定有向图，设计一个算法，找出两个节点之间是否存在一条路径。
     *
     * <p>示例1:
     *
     * <p>输入：n = 3, graph = [[0, 1], [0, 2], [1, 2], [1, 2]], start = 0, target = 2 输出：true 示例2:
     *
     * <p>输入：n = 5, graph = [[0, 1], [0, 2], [0, 4], [0, 4], [0, 1], [1, 3], [1, 4], [1, 3], [2, 3],
     * [3, 4]], start = 0, target = 4 输出 true 提示：
     *
     * <p>节点数量n在[0, 1e5]范围内。 节点编号大于等于 0 小于 n。 图中可能存在自环和平行边。
     *
     * @param n
     * @param graph
     * @param start
     * @param target
     * @return
     */
    public boolean findWhetherExistsPath(int n, int[][] graph, int start, int target) {
        // boolean[][] direct = new boolean[n][n];
        // Map<Integer, Set<Integer>> map = new HashMap<>();

        Set<Integer>[] direct = new Set[n];
        for (int[] path : graph) {
            int from = path[0], to = path[1];
            if (Objects.isNull(direct[from])) {
                direct[from] = new HashSet<>();
            }
            direct[from].add(to);
        }
        Set<Integer> tos = direct[start];
        if (Objects.nonNull(tos) && tos.contains(target)) {
            return true;
        }
        boolean[] visited = new boolean[n];
        visited[start] = true;
        return findPath(start, target, direct, visited);
    }

    private boolean findPath(int start, int target, Set<Integer>[] direct, boolean[] visited) {
        if (start == target) {
            return true;
        }

        Set<Integer> tos = direct[start];
        if (Objects.isNull(tos) || tos.isEmpty()) {
            return false;
        }
        if (tos.contains(target)) {
            return true;
        }
        for (int to : tos) {
            if (visited[to]) {
                continue;
            }
            visited[to] = true;
            if (findPath(to, target, direct, visited)) {
                return true;
            }
        }
        return false;
    }

    @Test
    public void equationsPossible() {
        String[] equations = {"c==c", "b==d", "x!=z"};

        logResult(equationsPossible(equations));
    }

    /**
     * 990. 等式方程的可满足性
     *
     * <p>给定一个由表示变量之间关系的字符串方程组成的数组，每个字符串方程 equations[i] 的长度为 4，
     *
     * <p>并采用两种不同的形式之一："a==b" 或 "a!=b"。在这里，a 和 b 是小写字母（不一定不同），表示单字母变量名。
     *
     * <p>只有当可以将整数分配给变量名，以便满足所有给定的方程时才返回 true，否则返回 false。
     *
     * <p>示例 1：
     *
     * <p>输入：["a==b","b!=a"] 输出：false 解释：如果我们指定，a = 1 且 b =
     * 1，那么可以满足第一个方程，但无法满足第二个方程。没有办法分配变量同时满足这两个方程。 示例 2：
     *
     * <p>输出：["b==a","a==b"] 输入：true 解释：我们可以指定 a = 1 且 b = 1 以满足满足这两个方程。 示例 3：
     *
     * <p>输入：["a==b","b==c","a==c"] 输出：true 示例 4：
     *
     * <p>输入：["a==b","b!=c","c==a"] 输出：false 示例 5：
     *
     * <p>输入：["c==c","b==d","x!=z"] 输出：true
     *
     * <p>提示：
     *
     * <p>1 <= equations.length <= 500 equations[i].length == 4 equations[i][0] 和 equations[i][3]
     * 是小写字母 equations[i][1] 要么是 '='，要么是 '!' equations[i][2] 是 '='
     *
     * @param equations
     * @return
     */
    public boolean equationsPossible(String[] equations) {
        // 用来存储 和 当前字母等价的
        int[] letters = new int[26];
        Arrays.fill(letters, -1);
        for (String equation : equations) {
            if (equation.charAt(1) == '=') {
                union(letters, equation.charAt(0), equation.charAt(3));
            }
        }
        for (String equation : equations) {
            if (equation.charAt(1) == '=') {
                continue;
            }
            int aIndex = findLeader(letters, equation.charAt(0) - 'a');
            int bIndex = findLeader(letters, equation.charAt(3) - 'a');
            if (aIndex == bIndex) {
                return false;
            }
        }

        return true;
    }

    private void union(int[] letters, char a, char b) {
        int aIdx = findLeader(letters, a - 'a');
        int bIdx = findLeader(letters, b - 'a');
        if (aIdx != bIdx) {
            letters[bIdx] = aIdx;
        }
    }

    private int findLeader(int[] letters, int index) {
        if (letters[index] == -1) {
            letters[index] = index;
            return index;
        }
        if (letters[index] == index) {
            return index;
        }
        return findLeader(letters, letters[index]);
    }

    @Test
    public void minNumberOfSemesters() {
        int n = 8, k = 3; // 3
        int[][] dependencies = {{1, 6}, {2, 7}, {8, 7}, {2, 5}, {3, 4}};
        logResult(minNumberOfSemesters(n, dependencies, k));
    }

    /**
     * 5435. 并行课程 II
     *
     * <p>给你一个整数 n 表示某所大学里课程的数目，编号为 1 到 n ，数组 dependencies 中， dependencies[i] = [xi, yi]
     * 表示一个先修课的关系，也就是课程 xi 必须在课程 yi 之前上。同时你还有一个整数 k 。
     *
     * <p>在一个学期中，你 最多 可以同时上 k 门课，前提是这些课的先修课在之前的学期里已经上过了。
     *
     * <p>请你返回上完所有课最少需要多少个学期。题目保证一定存在一种上完所有课的方式。
     *
     * <p>示例 1：
     *
     * <p>输入：n = 4, dependencies = [[2,1],[3,1],[1,4]], k = 2 输出：3 解释：上图展示了题目输入的图。在第一个学期中，我们可以上课程 2
     * 和课程 3 。然后第二个学期上课程 1 ，第三个学期上课程 4 。 示例 2：
     *
     * <p>输入：n = 5, dependencies = [[2,1],[3,1],[4,1],[1,5]], k = 2 输出：4
     * 解释：上图展示了题目输入的图。一个最优方案是：第一学期上课程 2 和 3，第二学期上课程 4 ，第三学期上课程 1 ，第四学期上课程 5 。 示例 3：
     *
     * <p>输入：n = 11, dependencies = [], k = 2 输出：6
     *
     * <p>提示：
     *
     * <p>1 <= n <= 15 1 <= k <= n 0 <= dependencies.length <= n * (n-1) / 2 dependencies[i].length
     * == 2 1 <= xi, yi <= n xi != yi 所有先修关系都是不同的，也就是说 dependencies[i] != dependencies[j] 。
     * 题目输入的图是个有向无环图。
     *
     * @param n
     * @param dependencies
     * @param k
     * @return
     */
    public int minNumberOfSemesters(int n, int[][] dependencies, int k) {
        if (dependencies.length == 0) {
            return (n - 1) / k + 1;
        }
        // 入度
        int[] inDegree = new int[n + 1];
        // 出度
        int[] outDegree = new int[n + 1];
        boolean[][] graph = new boolean[n + 1][n + 1];
        for (int[] dependency : dependencies) {
            outDegree[dependency[0]]++;
            inDegree[dependency[1]]++;
            graph[dependency[0]][dependency[1]] = true;
        }
        // 入度为0的点开始遍历
        minNumberOfSemesters(n, k, inDegree, graph);
        return minNumberOfSemestersResult;
    }

    private int minNumberOfSemestersResult = 0;

    private void minNumberOfSemesters(int n, int k, int[] inDegree, boolean[][] graph) {
        int count = 0;
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            if (inDegree[i] != 0) {
                continue;
            }
            list.add(i);
            inDegree[i] = -1;
            count++;
        }
        if (count == 0) {
            return;
        }
        for (int i : list) {
            for (int j = 1; j <= n; j++) {
                if (graph[i][j]) {
                    inDegree[j]--;
                }
            }
        }
        minNumberOfSemestersResult += (count - 1) / k + 1;
        minNumberOfSemesters(n, k, inDegree, graph);
    }

    @Test
    public void maxProbability() {
        int[][] edges = {{0, 1}, {1, 2}, {0, 2}};
        double[] succProb = {0.5, 0.5, 0.3};
        int n = 3, start = 0, end = 2;
        logResult(maxProbability(n, edges, succProb, start, end));
    }

    /**
     * 5211. 概率最大的路径
     *
     * <p>给你一个由 n 个节点（下标从 0 开始）组成的无向加权图，该图由一个描述边的列表组成，其中 edges[i] = [a, b] 表示连接节点 a 和 b
     * 的一条无向边，且该边遍历成功的概率为 succProb[i] 。
     *
     * <p>指定两个节点分别作为起点 start 和终点 end ，请你找出从起点到终点成功概率最大的路径，并返回其成功概率。
     *
     * <p>如果不存在从 start 到 end 的路径，请 返回 0 。只要答案与标准答案的误差不超过 1e-5 ，就会被视作正确答案。
     *
     * <p>示例 1：
     *
     * <p>输入：n = 3, edges = [[0,1],[1,2],[0,2]], succProb = [0.5,0.5,0.2], start = 0, end = 2
     * 输出：0.25000 解释：从起点到终点有两条路径，其中一条的成功概率为 0.2 ，而另一条为 0.5 * 0.5 = 0.25 示例 2：
     *
     * <p>输入：n = 3, edges = [[0,1],[1,2],[0,2]], succProb = [0.5,0.5,0.3], start = 0, end = 2
     * 输出：0.30000 示例 3：
     *
     * <p>输入：n = 3, edges = [[0,1]], succProb = [0.5], start = 0, end = 2 输出：0.00000 解释：节点 0 和 节点 2
     * 之间不存在路径
     *
     * <p>提示：
     *
     * <p>2 <= n <= 10^4 0 <= start, end < n start != end 0 <= a, b < n a != b 0 <= succProb.length
     * == edges.length <= 2*10^4 0 <= succProb[i] <= 1 每两个节点之间最多有一条边
     *
     * @param n
     * @param edges
     * @param succProb
     * @param start
     * @param end
     * @return
     */
    public double maxProbability(int n, int[][] edges, double[] succProb, int start, int end) {
        Map<Integer, Map<Integer, Double>> map = new HashMap<>();
        for (int i = 0; i < edges.length; i++) {
            int[] edge = edges[i];
            double succ = succProb[i];
            // 无向图
            Map<Integer, Double> endMap = map.computeIfAbsent(edge[0], k -> new HashMap<>());
            endMap.put(edge[1], succ);
            Map<Integer, Double> endMap2 = map.computeIfAbsent(edge[1], k -> new HashMap<>());
            endMap2.put(edge[0], succ);
        }
        // 深度优先遍历
        // 使用队列广度优先遍历

        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);
        double[] probs = new double[n];
        boolean[] visited = new boolean[n];
        probs[start] = 1.0;
        visited[start] = true;
        while (!queue.isEmpty()) {
            int tmp = queue.poll();
            visited[tmp] = false;
            Map<Integer, Double> fromStart = map.get(tmp);
            if (Objects.isNull(fromStart)) {
                continue;
            }
            for (Integer key : fromStart.keySet()) {
                double value = fromStart.get(key);
                if (probs[key] < probs[tmp] * value) {
                    probs[key] = probs[tmp] * value;
                    if (!visited[key]) {
                        queue.offer(key);
                        visited[key] = true;
                    }
                }
            }
        }

        return probs[end];
    }

    @Test
    public void findRedundantConnection() {
        int[][] edges = {{1, 2}, {2, 3}, {3, 4}, {1, 4}, {1, 5}};
        logResult(findRedundantConnection(edges));
    }

    /**
     * 684. 冗余连接
     *
     * <p>在本问题中, 树指的是一个连通且无环的无向图。
     *
     * <p>输入一个图，该图由一个有着N个节点 (节点值不重复1, 2, ..., N) 的树及一条附加的边构成。附加的边的两个顶点包含在1到N中间，这条附加的边不属于树中已存在的边。
     *
     * <p>结果图是一个以边组成的二维数组。每一个边的元素是一对[u, v] ，满足 u < v，表示连接顶点u 和v的无向图的边。
     *
     * <p>返回一条可以删去的边，使得结果图是一个有着N个节点的树。如果有多个答案，则返回二维数组中最后出现的边。答案边 [u, v] 应满足相同的格式 u < v。
     *
     * <p>示例 1：
     *
     * <p>输入: [[1,2], [1,3], [2,3]] 输出: [2,3] 解释: 给定的无向图为: 1 / \ 2 - 3 示例 2：
     *
     * <p>输入: [[1,2], [2,3], [3,4], [1,4], [1,5]] 输出: [1,4] 解释: 给定的无向图为: 5 - 1 - 2 | | 4 - 3 注意:
     *
     * <p>输入的二维数组大小在 3 到 1000。 二维数组中的整数在1到N之间，其中N是输入数组的大小。 更新(2017-09-26): 我们已经重新检查了问题描述及测试用例，明确图是无向
     * 图。对于有向图详见冗余连接II。对于造成任何不便，我们深感歉意。
     *
     * @param edges
     * @return
     */
    public int[] findRedundantConnection(int[][] edges) {
        // 使用并查集
        /*Map<Integer, Integer> rootMap = new HashMap<>();
        for (int[] edge : edges) {
            int num1 = getRoot(rootMap, edge[0]);
            int num2 = getRoot(rootMap, edge[1]);
            if (num1 == num2) {
                return edge;
            }
            rootMap.put(num2, num1);
        }*/
        // 数组并查集
        int[] nums = new int[edges.length + 1];
        for (int[] edge : edges) {
            int num1 = findRoot(nums, edge[0]);
            int num2 = findRoot(nums, edge[1]);
            if (num1 == num2) {
                return edge;
            } else {
                unionRedundant(nums, num1, num2);
            }
        }

        return null;
    }

    private void unionRedundant(int[] nums, int start, int end) {
        nums[end] = start;
    }

    private int findRoot(int[] nums, int num) {
        if (nums[num] == 0) {
            nums[num] = num;
            return num;
        }
        if (nums[num] == num) {
            return num;
        }
        return findRoot(nums, nums[num]);
    }

    private int getRoot(Map<Integer, Integer> rootMap, int num) {

        if (!rootMap.containsKey(num)) {
            rootMap.put(num, num);
            return num;
        }
        int result = rootMap.get(num);
        if (result == num) {
            return result;
        }
        return getRoot(rootMap, result);
    }

    /**
     * 785. 判断二分图
     *
     * <p>给定一个无向图graph，当这个图为二分图时返回true。
     *
     * <p>如果我们能将一个图的节点集合分割成两个独立的子集A和B，并使图中的每一条边的两个节点一个来自A集合，一个来自B集合，我们就将这个图称为二分图。
     *
     * <p>graph将会以邻接表方式给出，graph[i]表示图中与节点i相连的所有节点。每个节点都是一个在0到graph.length-1之间的整数。这图中没有自环和平行边：
     * graph[i] 中不存在i，并且graph[i]中没有重复的值。
     *
     * <p>示例 1: 输入: [[1,3], [0,2], [1,3], [0,2]] 输出: true 解释: 无向图如下: 0----1 | | | | 3----2
     * 我们可以将节点分成两组: {0, 2} 和 {1, 3}。
     *
     * <p>示例 2: 输入: [[1,2,3], [0,2], [0,1,3], [0,2]] 输出: false 解释: 无向图如下: 0----1 | \ | | \ | 3----2
     * 我们不能将节点分割成两个独立的子集。 注意:
     *
     * <p>graph 的长度范围为 [1, 100]。 graph[i] 中的元素的范围为 [0, graph.length - 1]。 graph[i] 不会包含 i 或者有重复的值。
     * 图是无向的: 如果j 在 graph[i]里边, 那么 i 也会在 graph[j]里边。
     *
     * @param graph
     * @return
     */
    public boolean isBipartite(int[][] graph) {
        // 广度优先遍历, 染色
        int[] colors = new int[101];
        // 广度优先遍历
        for (int i = 0; i < graph.length; i++) {
            int[] points = graph[i];
            if (colors[i] == 0) {
                Queue<Integer> queue = new LinkedList<>();
                queue.offer(i);
                colors[i] = 1;
                while (!queue.isEmpty()) {
                    int node = queue.poll();
                    int nextColor = colors[node] == 1 ? 2 : 1;
                    for (int next : graph[node]) {
                        if (colors[next] == 0) {
                            colors[next] = nextColor;
                            queue.offer(next);
                        } else if (colors[next] != nextColor) {
                            return false;
                        }
                    }
                }
            }
        }

        return true;
    }

    @Test
    public void networkDelayTime() {
        int[][] times = {{2, 1, 1}, {2, 3, 1}, {3, 4, 1}};
        int N = 4, K = 2;
        /*int[][] times = {{1, 2, 1}, {2, 1, 3}};
        int N = 2, K = 2;*/
        logResult(networkDelayTime(times, N, K));
    }
    /**
     * 743. 网络延迟时间
     *
     * <p>有 N 个网络节点，标记为 1 到 N。
     *
     * <p>给定一个列表 times，表示信号经过有向边的传递时间。 times[i] = (u, v, w)，其中 u 是源节点，v 是目标节点， w
     * 是一个信号从源节点传递到目标节点的时间。
     *
     * <p>现在，我们从某个节点 K 发出一个信号。需要多久才能使所有节点都收到信号？如果不能使所有节点收到信号，返回 -1。
     *
     * <p>示例：
     *
     * <p>输入：times = [[2,1,1],[2,3,1],[3,4,1]], N = 4, K = 2 输出：2
     *
     * <p>注意:
     *
     * <p>N 的范围在 [1, 100] 之间。 K 的范围在 [1, N] 之间。 times 的长度在 [1, 6000] 之间。 所有的边 times[i] = (u, v, w)
     * 都有 1 <= u, v <= N 且 0 <= w <= 100。
     *
     * @param times
     * @param N
     * @param K
     * @return
     */
    public int networkDelayTime(int[][] times, int N, int K) {
        int maxTime = 0;

        int[][] graph = new int[N + 1][N + 1];
        for (int i = 1; i < N + 1; i++) {
            for (int j = 1; j < N + 1; j++) {
                graph[i][j] = -1;
            }
        }
        for (int[] time : times) {
            graph[time[0]][time[1]] = time[2];
        }
        // 从 K 开始遍历
        int[] dist = new int[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[0] = 0;
        dist[K] = 0;
        // 广度优先遍历
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(K);
        while (!queue.isEmpty()) {
            int start = queue.poll();
            for (int i = 1; i <= N; i++) {
                if (graph[start][i] == -1) {
                    continue;
                }
                if (dist[i] > dist[start] + graph[start][i]) {
                    dist[i] = dist[start] + graph[start][i];
                    if (!queue.contains(i)) {
                        queue.offer(i);
                    }
                }
            }
        }
        log.debug("dist:{}", dist);
        for (int t : dist) {
            if (t == Integer.MAX_VALUE) {
                return -1;
            }
            maxTime = Math.max(t, maxTime);
        }

        return maxTime;
    }

    int networkDelayTimeResult = 0;

    private void dfsNetworkDelayTime(
            Map<Integer, Map<Integer, Integer>> map, int start, int time, Set<Integer> visited) {
        if (visited.contains(start)) {
            return;
        }
        visited.add(start);
        networkDelayTimeResult = Math.max(networkDelayTimeResult, time);
        if (!map.containsKey(start)) {
            return;
        }

        Map<Integer, Integer> timeMap = map.get(start);
        timeMap.forEach(
                (k, v) -> {
                    dfsNetworkDelayTime(map, k, time + v, visited);
                });
    }

    @Test
    public void findCheapestPrice() {
        int n = 3;
        int[][] edges = {{0, 1, 100}, {1, 2, 100}, {0, 2, 500}};
        int src = 0, dst = 2, k = 1;
        logResult(findCheapestPrice(n, edges, src, dst, k));
    }

    /**
     * 787. K 站中转内最便宜的航班
     *
     * <p>有 n 个城市通过 m 个航班连接。每个航班都从城市 u 开始，以价格 w 抵达 v。
     *
     * <p>现在给定所有的城市和航班，以及出发城市 src 和目的地 dst，你的任务是找到从 src 到 dst 最多经过 k 站中转的最便宜的价格。 如果没有这样的路线，则输出 -1。
     *
     * <p>示例 1：
     *
     * <p>输入: n = 3, edges = [[0,1,100],[1,2,100],[0,2,500]] src = 0, dst = 2, k = 1 输出: 200 解释:
     * 城市航班图如下
     *
     * <p>从城市 0 到城市 2 在 1 站中转以内的最便宜价格是 200，如图中红色所示。 示例 2：
     *
     * <p>输入: n = 3, edges = [[0,1,100],[1,2,100],[0,2,500]] src = 0, dst = 2, k = 0 输出: 500 解释:
     * 城市航班图如下
     *
     * <p>从城市 0 到城市 2 在 0 站中转以内的最便宜价格是 500，如图中蓝色所示。
     *
     * <p>提示：
     *
     * <p>n 范围是 [1, 100]，城市标签从 0 到 n - 1. 航班数量范围是 [0, n * (n - 1) / 2]. 每个航班的格式 (src, dst, price).
     * 每个航班的价格范围是 [1, 10000]. k 范围是 [0, n - 1]. 航班没有重复，且不存在环路
     *
     * @param n
     * @param flights
     * @param src
     * @param dst
     * @param K
     * @return
     */
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        int result = Integer.MAX_VALUE;
        int[][] graph = new int[n][n];
        for (int[] flight : flights) {
            graph[flight[0]][flight[1]] = flight[2];
        }
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[] {src, 0});
        while (!queue.isEmpty()) {
            if (K < 0) {
                break;
            }
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] cur = queue.poll();
                int start = cur[0], price = cur[1];
                for (int j = 0; j < n; j++) {
                    if (graph[start][j] == 0) {
                        continue;
                    }
                    int curPrice = price + graph[start][j];
                    if (j == dst) {
                        result = Math.min(result, curPrice);
                    }
                    queue.offer(new int[] {j, curPrice});
                }
            }
            K--;
        }

        return result == Integer.MAX_VALUE ? -1 : result;
    }

    @Test
    public void findSmallestSetOfVertices() {
        int n = 5;
        List<List<Integer>> edges = new ArrayList<>();
        edges.add(Arrays.asList(0, 1));
        edges.add(Arrays.asList(2, 1));
        edges.add(Arrays.asList(3, 1));
        edges.add(Arrays.asList(1, 4));
        edges.add(Arrays.asList(2, 4));

        logResult(findSmallestSetOfVertices(n, edges));
    }

    /**
     * 5480. 可以到达所有点的最少点数目
     *
     * <p>给你一个 有向无环图 ， n 个节点编号为 0 到 n-1 ，以及一个边数组 edges ，其中 edges[i] = [fromi, toi] 表示一条从点 fromi 到点
     * toi 的有向边。
     *
     * <p>找到最小的点集使得从这些点出发能到达图中所有点。题目保证解存在且唯一。
     *
     * <p>你可以以任意顺序返回这些节点编号。
     *
     * <p>示例 1：
     *
     * <p>输入：n = 6, edges = [[0,1],[0,2],[2,5],[3,4],[4,2]] 输出：[0,3] 解释：从单个节点出发无法到达所有节点。从 0 出发我们可以到达
     * [0,1,2,5] 。从 3 出发我们可以到达 [3,4,2,5] 。所以我们输出 [0,3] 。 示例 2：
     *
     * <p>输入：n = 5, edges = [[0,1],[2,1],[3,1],[1,4],[2,4]] 输出：[0,2,3] 解释：注意到节点 0，3 和 2
     * 无法从其他节点到达，所以我们必须将它们包含在结果点集中，这些点都能到达节点 1 和 4 。
     *
     * <p>提示：
     *
     * <p>2 <= n <= 10^5 1 <= edges.length <= min(10^5, n * (n - 1) / 2) edges[i].length == 2 0 <=
     * fromi, toi < n 所有点对 (fromi, toi) 互不相同。
     *
     * @param n
     * @param edges
     * @return
     */
    public List<Integer> findSmallestSetOfVertices(int n, List<List<Integer>> edges) {
        List<Integer> list = new ArrayList<>();
        // 入度为0
        int[] inDegree = new int[n];
        for (List<Integer> edge : edges) {
            int from = edge.get(0), to = edge.get(1);
            inDegree[to]++;
        }
        for (int i = 0; i < n; i++) {
            if (inDegree[i] == 0) {
                list.add(i);
            }
        }

        return list;
    }

    /**
     * 1267. 统计参与通信的服务器
     *
     * <p>这里有一幅服务器分布图，服务器的位置标识在 m * n 的整数矩阵网格 grid 中，1 表示单元格上有服务器，0 表示没有。
     *
     * <p>如果两台服务器位于同一行或者同一列，我们就认为它们之间可以进行通信。
     *
     * <p>请你统计并返回能够与至少一台其他服务器进行通信的服务器的数量。
     *
     * <p>示例 1：
     *
     * <p>输入：grid = [[1,0],[0,1]] 输出：0 解释：没有一台服务器能与其他服务器进行通信。 示例 2：
     *
     * <p>输入：grid = [[1,0],[1,1]] 输出：3 解释：所有这些服务器都至少可以与一台别的服务器进行通信。 示例 3：
     *
     * <p>输入：grid = [[1,1,0,0],[0,0,1,0],[0,0,1,0],[0,0,0,1]] 输出：4
     * 解释：第一行的两台服务器互相通信，第三列的两台服务器互相通信，但右下角的服务器无法与其他服务器通信。
     *
     * <p>提示：
     *
     * <p>m == grid.length n == grid[i].length 1 <= m <= 250 1 <= n <= 250 grid[i][j] == 0 or 1
     *
     * @param grid
     * @return
     */
    public int countServers(int[][] grid) {
        int result = 0;
        int rows = grid.length, cols = grid[0].length;
        int[] countRows = new int[rows], countCols = new int[cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 0) {
                    continue;
                }
                countRows[i]++;
                countCols[j]++;
            }
        }
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 0) {
                    continue;
                }
                if (countRows[i] > 1 || countCols[j] > 1) {
                    result++;
                }
            }
        }

        return result;
    }

    /**
     * 1579. 保证图可完全遍历
     *
     * <p>Alice 和 Bob 共有一个无向图，其中包含 n 个节点和 3 种类型的边：
     *
     * <p>类型 1：只能由 Alice 遍历。 类型 2：只能由 Bob 遍历。 类型 3：Alice 和 Bob 都可以遍历。 给你一个数组 edges ，其中 edges[i] =
     * [typei, ui, vi] 表示节点 ui 和 vi 之间存在类型为 typei 的双向边。请你在保证图仍能够被 Alice和 Bob
     * 完全遍历的前提下，找出可以删除的最大边数。如果从任何节点开始，Alice 和 Bob 都可以到达所有其他节点，则认为图是可以完全遍历的。
     *
     * <p>返回可以删除的最大边数，如果 Alice 和 Bob 无法完全遍历图，则返回 -1 。
     *
     * <p>示例 1：
     *
     * <p>输入：n = 4, edges = [[3,1,2],[3,2,3],[1,1,3],[1,2,4],[1,1,2],[2,3,4]] 输出：2 解释：如果删除 [1,1,2] 和
     * [1,1,3] 这两条边，Alice 和 Bob 仍然可以完全遍历这个图。再删除任何其他的边都无法保证图可以完全遍历。所以可以删除的最大边数是 2 。 示例 2：
     *
     * <p>输入：n = 4, edges = [[3,1,2],[3,2,3],[1,1,4],[2,1,4]] 输出：0 解释：注意，删除任何一条边都会使 Alice 和 Bob
     * 无法完全遍历这个图。 示例 3：
     *
     * <p>输入：n = 4, edges = [[3,2,3],[1,1,2],[2,3,4]] 输出：-1 解释：在当前图中，Alice 无法从其他节点到达节点 4 。类似地，Bob
     * 也不能达到节点 1 。因此，图无法完全遍历。
     *
     * <p>提示：
     *
     * <p>1 <= n <= 10^5 1 <= edges.length <= min(10^5, 3 * n * (n-1) / 2) edges[i].length == 3 1 <=
     * edges[i][0] <= 3 1 <= edges[i][1] < edges[i][2] <= n 所有元组 (typei, ui, vi) 互不相同
     *
     * @param n
     * @param edges
     * @return
     */
    public int maxNumEdgesToRemove(int n, int[][] edges) {
        visited = new boolean[edges.length];
        Arrays.sort(edges, (a, b) -> b[0] - a[0]);
        if (unionFind(n, edges, 1) != 1) {
            return -1;
        }
        if (unionFind(n, edges, 2) != 1) {
            return -1;
        }
        int result = 0;
        for (boolean v : visited) {
            result += v ? 0 : 1;
        }
        return result;
    }

    private boolean[] visited;

    private int unionFind(int n, int[][] edges, int excludedType) {
        int[] union = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            union[i] = i;
        }
        for (int i = 0; i < edges.length; i++) {
            int[] edge = edges[i];
            if (edge[0] == excludedType) {
                continue;
            }
            int root1 = findRoot2(union, edge[1]);
            int root2 = findRoot2(union, edge[2]);
            if (root1 != root2) {
                union[root1] = root2;
                visited[i] = true;
            }
        }
        int result = 0;
        for (int i = 1; i <= n; i++) {
            if (union[i] == i) {
                result++;
            }
        }
        return result;
    }

    private int findRoot2(int[] union, int target) {

        if (union[target] != target) {
            int root = findRoot2(union, union[target]);
            // 压缩路径
            union[target] = root;
            return root;
        }

        return target;
    }

    @Test
    public void maxNumEdgesToRemove() {
        int n = 4;
        int[][] edges = {{3, 1, 2}, {3, 2, 3}, {1, 1, 3}, {1, 2, 4}, {1, 1, 2}, {2, 3, 4}};
        logResult(maxNumEdgesToRemove(n, edges));
    }

    @Test
    public void makeConnected() {
        int n = 4;
        int[][] connections = {{0, 1}, {0, 2}, {1, 2}};
        logResult(makeConnected(n, connections));
    }

    /**
     * 1319. 连通网络的操作次数
     *
     * <p>用以太网线缆将 n 台计算机连接成一个网络，计算机的编号从 0 到 n-1。线缆用 connections 表示，其中 connections[i] = [a, b] 连接了计算机
     * a 和 b。
     *
     * <p>网络中的任何一台计算机都可以通过网络直接或者间接访问同一个网络中其他任意一台计算机。
     *
     * <p>给你这个计算机网络的初始布线
     * connections，你可以拔开任意两台直连计算机之间的线缆，并用它连接一对未直连的计算机。请你计算并返回使所有计算机都连通所需的最少操作次数。如果不可能，则返回 -1 。
     *
     * <p>示例 1：
     *
     * <p>输入：n = 4, connections = [[0,1],[0,2],[1,2]] 输出：1 解释：拔下计算机 1 和 2 之间的线缆，并将它插到计算机 1 和 3 上。 示例
     * 2：
     *
     * <p>输入：n = 6, connections = [[0,1],[0,2],[0,3],[1,2],[1,3]] 输出：2 示例 3：
     *
     * <p>输入：n = 6, connections = [[0,1],[0,2],[0,3],[1,2]] 输出：-1 解释：线缆数量不足。 示例 4：
     *
     * <p>输入：n = 5, connections = [[0,1],[0,2],[3,4],[2,3]] 输出：0
     *
     * <p>提示：
     *
     * <p>1 <= n <= 10^5 1 <= connections.length <= min(n*(n-1)/2, 10^5) connections[i].length == 2
     * 0 <= connections[i][0], connections[i][1] < n connections[i][0] != connections[i][1] 没有重复的连接。
     * 两台计算机不会通过多条线缆连接。
     *
     * @param n
     * @param connections
     * @return
     */
    public int makeConnected(int n, int[][] connections) {
        if (connections.length < n - 1) {
            return -1;
        }
        // 使用并查集
        int[] union = new int[n];
        for (int i = 0; i < n; i++) {
            union[i] = i;
        }
        for (int[] connect : connections) {
            int root1 = findRoot3(union, connect[0]);
            int root2 = findRoot3(union, connect[1]);
            if (root1 != root2) {
                union[root1] = root2;
            }
        }
        int count = 0;
        for (int i = 0; i < n; i++) {
            count += union[i] == i ? 1 : 0;
        }

        return count - 1;
    }

    private int findRoot3(int[] union, int target) {

        if (union[target] != target) {
            int root = findRoot3(union, union[target]);
            // 压缩路径
            union[target] = root;
            return root;
        }

        return target;
    }

    /**
     * 1334. 阈值距离内邻居最少的城市
     *
     * <p>有 n 个城市，按从 0 到 n-1 编号。给你一个边数组 edges，其中 edges[i] = [fromi, toi, weighti] 代表 fromi 和 toi
     * 两个城市之间的双向加权边，距离阈值是一个整数 distanceThreshold。
     *
     * <p>返回能通过某些路径到达其他城市数目最少、且路径距离 最大 为 distanceThreshold 的城市。如果有多个这样的城市，则返回编号最大的城市。
     *
     * <p>注意，连接城市 i 和 j 的路径的距离等于沿该路径的所有边的权重之和。
     *
     * <p>示例 1：
     *
     * <p>输入：n = 4, edges = [[0,1,3],[1,2,1],[1,3,4],[2,3,1]], distanceThreshold = 4 输出：3
     * 解释：城市分布图如上。 每个城市阈值距离 distanceThreshold = 4 内的邻居城市分别是： 城市 0 -> [城市 1, 城市 2] 城市 1 -> [城市 0, 城市
     * 2, 城市 3] 城市 2 -> [城市 0, 城市 1, 城市 3] 城市 3 -> [城市 1, 城市 2] 城市 0 和 3 在阈值距离 4 以内都有 2
     * 个邻居城市，但是我们必须返回城市 3，因为它的编号最大。 示例 2：
     *
     * <p>输入：n = 5, edges = [[0,1,2],[0,4,8],[1,2,3],[1,4,2],[2,3,1],[3,4,1]], distanceThreshold = 2
     * 输出：0 解释：城市分布图如上。 每个城市阈值距离 distanceThreshold = 2 内的邻居城市分别是： 城市 0 -> [城市 1] 城市 1 -> [城市 0, 城市
     * 4] 城市 2 -> [城市 3, 城市 4] 城市 3 -> [城市 2, 城市 4] 城市 4 -> [城市 1, 城市 2, 城市 3] 城市 0 在阈值距离 4 以内只有 1
     * 个邻居城市。
     *
     * <p>提示：
     *
     * <p>2 <= n <= 100 1 <= edges.length <= n * (n - 1) / 2 edges[i].length == 3 0 <= fromi < toi <
     * n 1 <= weighti, distanceThreshold <= 10^4 所有 (fromi, toi) 都是不同的。
     *
     * @param n
     * @param edges
     * @param distanceThreshold
     * @return
     */
    public int findTheCity(int n, int[][] edges, int distanceThreshold) {
        int[][] graph = new int[n][n];

        for (int i = 0; i < n; i++) {
            Arrays.fill(graph[i], Integer.MAX_VALUE);
        }

        for (int[] edge : edges) {
            graph[edge[0]][edge[1]] = edge[2];
            graph[edge[1]][edge[0]] = edge[2];
        }

        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (i == j) {
                        continue;
                    }
                    if (graph[i][k] == Integer.MAX_VALUE || graph[k][j] == Integer.MAX_VALUE) {
                        continue;
                    }
                    graph[i][j] = Math.min(graph[i][j], graph[i][k] + graph[k][j]);
                }
            }
        }
        int minCount = n;
        int result = -1;
        for (int i = 0; i < n; i++) {
            int count = 0;
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    continue;
                }
                if (graph[i][j] <= distanceThreshold) {
                    count++;
                }
            }
            if (count < minCount) {
                minCount = count;
                result = i;
            } else if (count == minCount) {
                result = i;
            }
        }

        return result;
    }

    @Test
    public void findRedundantDirectedConnection() {
        int[][] edges = {{1, 2}, {2, 3}, {3, 1}};
        logResult(findRedundantDirectedConnection(edges));
    }

    /**
     * 685. 冗余连接 II
     *
     * <p>在本问题中，有根树指满足以下条件的有向图。该树只有一个根节点，所有其他节点都是该根节点的后继。每一个节点只有一个父节点，除了根节点没有父节点。
     *
     * <p>输入一个有向图，该图由一个有着N个节点 (节点值不重复1, 2, ..., N) 的树及一条附加的边构成。附加的边的两个顶点包含在1到N中间，这条附加的边不属于树中已存在的边。
     *
     * <p>结果图是一个以边组成的二维数组。 每一个边 的元素是一对 [u, v]，用以表示有向图中连接顶点 u 和顶点 v 的边，其中 u 是 v 的一个父节点。
     *
     * <p>返回一条能删除的边，使得剩下的图是有N个节点的有根树。若有多个答案，返回最后出现在给定二维数组的答案。
     *
     * <p>示例 1:
     *
     * <p>输入: [[1,2], [1,3], [2,3]] 输出: [2,3] 解释: 给定的有向图如下: 1 / \ v v 2-->3 示例 2:
     *
     * <p>输入: [[1,2], [2,3], [3,4], [4,1], [1,5]] 输出: [4,1] 解释: 给定的有向图如下: 5 <- 1 -> 2 ^ | | v 4 <- 3
     * 注意:
     *
     * <p>二维数组大小的在3到1000范围内。 二维数组中的每个整数在1到N之间，其中 N 是二维数组的大小。
     *
     * @param edges
     * @return
     */
    public int[] findRedundantDirectedConnection(int[][] edges) {

        // 使用并查集

        /*
        for (int[] edge : edges) {
            int root1 = findRoot3(union, edge[0]);
            int root2 = findRoot3(union, edge[1]);
            if (root1 == root2) {
                result = edge;
            } else {

            }
        }*/
        int n = edges.length;
        int[] result = null;
        int[] union = new int[n + 1];
        for (int i = 1; i < n; i++) {
            union[i] = i;
        }
        // 1.都是度为1， 则找出构成环的最后一条边
        // 2.有度为2的两条边(A->B, C->B)，则删除的边一定是在其中
        // 先不将C->B加入并查集中，若不能构成环，则C->B是需要删除的点边，反之，则A->B是删除的边(去掉C->B还能构成环，则C->B一定不是要删除的边)

        int repeatRoot = -1;
        int[] inDegree = new int[n + 1];
        for (int[] edge : edges) {
            inDegree[edge[1]]++;
            if (inDegree[edge[1]] == 2) {
                repeatRoot = edge[1];
            }
        }
        if (repeatRoot == -1) {
            for (int[] edge : edges) {

                int root1 = findRoot3(union, edge[0]);
                int root2 = findRoot3(union, edge[1]);
                if (root1 != root2) {
                    union[root1] = root2;
                } else {
                    result = edge;
                }
            }
        } else {
            List<int[]> list = new ArrayList<>();
            for (int[] edge : edges) {
                if (edge[1] == repeatRoot) {
                    list.add(edge);
                    continue;
                }
                int root1 = findRoot3(union, edge[0]);
                int root2 = findRoot3(union, edge[1]);
                union[root1] = root2;
            }
            for (int[] edge : list) {
                int root1 = findRoot3(union, edge[0]);
                int root2 = findRoot3(union, edge[1]);
                if (root1 == root2) {
                    result = edge;
                    break;
                } else {
                    union[root1] = root2;
                }
            }
        }

        return result;
    }

    /**
     * 1361. 验证二叉树
     *
     * <p>二叉树上有 n 个节点，按从 0 到 n - 1 编号，其中节点 i 的两个子节点分别是 leftChild[i] 和 rightChild[i]。
     *
     * <p>只有 所有 节点能够形成且 只 形成 一颗 有效的二叉树时，返回 true；否则返回 false。
     *
     * <p>如果节点 i 没有左子节点，那么 leftChild[i] 就等于 -1。右子节点也符合该规则。
     *
     * <p>注意：节点没有值，本问题中仅仅使用节点编号。
     *
     * <p>示例 1：
     *
     * <p>输入：n = 4, leftChild = [1,-1,3,-1], rightChild = [2,-1,-1,-1] 输出：true 示例 2：
     *
     * <p>输入：n = 4, leftChild = [1,-1,3,-1], rightChild = [2,3,-1,-1] 输出：false 示例 3：
     *
     * <p>输入：n = 2, leftChild = [1,0], rightChild = [-1,-1] 输出：false 示例 4：
     *
     * <p>输入：n = 6, leftChild = [1,-1,-1,4,-1,-1], rightChild = [2,-1,-1,5,-1,-1] 输出：false
     *
     * <p>提示：
     *
     * <p>1 <= n <= 10^4 leftChild.length == rightChild.length == n -1 <= leftChild[i],
     * rightChild[i] <= n - 1
     *
     * @param n
     * @param leftChild
     * @param rightChild
     * @return
     */
    public boolean validateBinaryTreeNodes(int n, int[] leftChild, int[] rightChild) {
        int[] inDegree = new int[n];
        for (int i = 0; i < leftChild.length; i++) {
            int leftNum = leftChild[i], rightNum = rightChild[i];
            if (leftNum != -1) {
                inDegree[leftNum]++;
                if (inDegree[leftNum] > 1) {
                    return false;
                }
            }
            if (rightNum != -1) {
                inDegree[rightNum]++;
                if (inDegree[rightNum] > 1) {
                    return false;
                }
            }
        }
        for (int num : leftChild) {
            if (num == -1) {
                continue;
            }
            inDegree[num]++;
            if (inDegree[num] > 1) {
                return false;
            }
        }
        for (int num : rightChild) {
            if (num == -1) {
                continue;
            }
            inDegree[num]++;
            if (inDegree[num] > 1) {
                return false;
            }
        }
        int zeroCount = 0;
        int root = -1;
        for (int num : inDegree) {
            if (num == 0) {
                zeroCount++;
                root = num;
            }
        }
        if (zeroCount != 1) {
            return false;
        }
        Queue<Integer> queue = new LinkedList<>();
        // 广度优先遍历 然后 查看重复
        queue.offer(root);
        boolean[] visited = new boolean[n];
        while (!queue.isEmpty()) {
            Integer node = queue.poll();
            if (visited[node]) {
                return false;
            }
            visited[node] = true;
            if (leftChild[node] != -1) {
                queue.offer(leftChild[node]);
            }
            if (rightChild[node] != -1) {
                queue.offer(rightChild[node]);
            }
        }
        return true;
    }

    /**
     * 802. 找到最终的安全状态
     *
     * <p>在有向图中, 我们从某个节点和每个转向处开始, 沿着图的有向边走。 如果我们到达的节点是终点 (即它没有连出的有向边), 我们停止。
     *
     * <p>现在, 如果我们最后能走到终点，那么我们的起始节点是最终安全的。 更具体地说, 存在一个自然数 K, 无论选择从哪里开始行走, 我们走了不到 K 步后必能停止在一个终点。
     *
     * <p>哪些节点最终是安全的？ 结果返回一个有序的数组。
     *
     * <p>该有向图有 N 个节点，标签为 0, 1, ..., N-1, 其中 N 是 graph 的节点数. 图以以下的形式给出: graph[i] 是节点 j 的一个列表，满足 (i,
     * j) 是图的一条有向边。
     *
     * <p>示例： 输入：graph = [[1,2],[2,3],[5],[0],[5],[],[]] 输出：[2,4,5,6] 这里是上图的示意图。
     *
     * <p>Illustration of graph
     *
     * <p>提示：
     *
     * <p>graph 节点数不超过 10000. 图的边数不会超过 32000. 每个 graph[i] 被排序为不同的整数列表， 在区间 [0, graph.length - 1]
     * 中选取。
     *
     * @param graph
     * @return
     */
    public List<Integer> eventualSafeNodes(int[][] graph) {
        // 深度优先搜索
        // 我们同样可以使用深度优先搜索的方法判断图中的每个节点是否能走到环中。对于每个节点，我们有三种染色的方法：
        // 白色表示该节点还没有被访问过；灰色表示该节点在栈中（这一轮搜索中被访问过）或者在环中；黑色表示该节点的所有相连的节点都被访问过，且该节点不在环中。
        //
        // 当我们第一次访问一个节点时，我们把它从白色变成灰色，并继续搜索与它相连的节点。
        // 如果在搜索过程中我们遇到一个灰色的节点，那么说明找到了一个环，此时退出搜索，所有的灰色节点保持不变（即从任意一个灰色节点开始，都能走到环中），
        // 如果搜索过程中，我们没有遇到灰色的节点，那么在回溯到当前节点时，我们把它从灰色变成黑色，即表示它是一个安全的节点。
        int N = graph.length;
        int[] colors = new int[N];
        List<Integer> result = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            if (eventualSafeNodesDfs(i, colors, graph)) {
                result.add(i);
            }
        }

        return result;
    }

    /**
     * 深度优先遍历
     *
     * @param node
     * @param colors WHITE 0, GRAY 1, BLACK 2;
     * @param graph
     * @return
     */
    private boolean eventualSafeNodesDfs(int node, int[] colors, int[][] graph) {
        if (colors[node] != 0) {
            return colors[node] == 2;
        }
        colors[node] = 1;

        for (int next : graph[node]) {
            if (colors[next] == 2) {
                continue;
            }
            if (colors[next] == 1 || !eventualSafeNodesDfs(next, colors, graph)) {
                return false;
            }
        }

        colors[node] = 2;

        return true;
    }

    @Test
    public void maximalNetworkRank() {
        int n = 8;
        int[][] roads = {{4, 6}, {5, 2}, {3, 5}, {7, 5}, {7, 6}};
        logResult(maximalNetworkRank(n, roads));
    }

    /**
     * 5536. 最大网络秩
     *
     * <p>n 座城市和一些连接这些城市的道路 roads 共同组成一个基础设施网络。每个 roads[i] = [ai, bi] 都表示在城市 ai 和 bi 之间有一条双向道路。
     *
     * <p>两座不同城市构成的 城市对 的 网络秩 定义为：与这两座城市 直接 相连的道路总数。如果存在一条道路直接连接这两座城市，则这条道路只计算 一次 。
     *
     * <p>整个基础设施网络的 最大网络秩 是所有不同城市对中的 最大网络秩 。
     *
     * <p>给你整数 n 和数组 roads，返回整个基础设施网络的 最大网络秩 。
     *
     * <p>示例 1：
     *
     * <p>输入：n = 4, roads = [[0,1],[0,3],[1,2],[1,3]] 输出：4 解释：城市 0 和 1 的网络秩是 4，因为共有 4 条道路与城市 0 或 1
     * 相连。位于 0 和 1 之间的道路只计算一次。 示例 2：
     *
     * <p>输入：n = 5, roads = [[0,1],[0,3],[1,2],[1,3],[2,3],[2,4]] 输出：5 解释：共有 5 条道路与城市 1 或 2 相连。 示例
     * 3：
     *
     * <p>输入：n = 8, roads = [[0,1],[1,2],[2,3],[2,4],[5,6],[5,7]] 输出：5 解释：2 和 5 的网络秩为
     * 5，注意并非所有的城市都需要连接起来。
     *
     * <p>提示：
     *
     * <p>2 <= n <= 100 0 <= roads.length <= n * (n - 1) / 2 roads[i].length == 2 0 <= ai, bi <= n-1
     * ai != bi 每对城市之间 最多只有一条 道路相连
     *
     * @param n
     * @param roads
     * @return
     */
    public int maximalNetworkRank(int n, int[][] roads) {
        if (n == 2) {
            return roads.length;
        }
        // 每个点的度
        int[] degree = new int[n];
        boolean[][] graph = new boolean[n][n];
        for (int[] road : roads) {
            degree[road[0]]++;
            degree[road[1]]++;
            graph[road[0]][road[1]] = true;
            graph[road[1]][road[0]] = true;
        }
        int[][] nums = new int[n][2];
        for (int i = 0; i < n; i++) {
            nums[i][0] = i;
            nums[i][1] = degree[i];
        }
        Arrays.sort(nums, Comparator.comparingInt(a -> a[1]));
        int result = nums[n - 1][1] + nums[n - 2][1];
        if (!graph[nums[n - 1][0]][nums[n - 2][0]]) {
            return result;
        }
        int idx = n - 3;
        while (idx >= 0 && nums[idx][1] == nums[n - 2][1]) {
            if (!graph[nums[n - 1][0]][nums[idx][0]]) {
                return result;
            }
            idx--;
        }

        return result - 1;
    }

    /**
     * 959. 由斜杠划分区域
     *
     * <p>在由 1 x 1 方格组成的 N x N 网格 grid 中，每个 1 x 1 方块由 /、\ 或空格构成。这些字符会将方块划分为一些共边的区域。
     *
     * <p>（请注意，反斜杠字符是转义的，因此 \ 用 "\\" 表示。）。
     *
     * <p>返回区域的数目。
     *
     * <p>示例 1：
     *
     * <p>输入： [ " /", "/ " ] 输出：2 解释：2x2 网格如下：
     *
     * <p>示例 2：
     *
     * <p>输入： [ " /", " " ] 输出：1 解释：2x2 网格如下：
     *
     * <p>示例 3：
     *
     * <p>输入： [ "\\/", "/\\" ] 输出：4 解释：（回想一下，因为 \ 字符是转义的，所以 "\\/" 表示 \/，而 "/\\" 表示 /\。） 2x2 网格如下：
     *
     * <p>示例 4：
     *
     * <p>输入： [ "/\\", "\\/" ] 输出：5 解释：（回想一下，因为 \ 字符是转义的，所以 "/\\" 表示 /\，而 "\\/" 表示 \/。） 2x2 网格如下：
     *
     * <p>示例 5：
     *
     * <p>输入： [ "//", "/ " ] 输出：3 解释：2x2 网格如下：
     *
     * <p>提示：
     *
     * <p>1 <= grid.length == grid[0].length <= 30 grid[i][j] 是 '/'、'\'、或 ' '。
     *
     * @param grid
     * @return
     */
    public int regionsBySlashes(String[] grid) {
        // 每个区域分为4个方向 => 上右下左(北东南西)  0 1 2 3
        int N = grid.length;

        int len = N * N << 2;
        RegionDsu dsu = new RegionDsu(len);
        int rows = N << 2;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                char c = grid[i].charAt(j);
                // 位置
                int idx = (i * N + j) << 2; // => *4
                if (c == ' ') {
                    dsu.union(idx, idx + 1);
                    dsu.union(idx + 1, idx + 2);
                    dsu.union(idx + 2, idx + 3);
                } else if (c == '/') {
                    // / 右 下
                    dsu.union(idx + 1, idx + 2);
                    // / 上 左
                    dsu.union(idx, idx + 3);

                } else if (c == '\\') {
                    // \ 上 右
                    dsu.union(idx, idx + 1);
                    // \  下 左
                    dsu.union(idx + 2, idx + 3);
                }
                // 上
                if (i > 0) {
                    int up = (idx - rows) + 2;
                    dsu.union(idx, up);
                }
                // 左
                if (j > 0) {
                    int left = (idx - 4) + 1;
                    dsu.union(idx + 3, left);
                }
            }
        }
        int result = 0;
        for (int i = 0; i < len; i++) {
            if (dsu.findParent(i) == i) {
                result++;
            }
        }

        return result;
    }

    /** 并查集 */
    static class RegionDsu {
        private int[] parent;

        RegionDsu(int len) {

            this.parent = new int[len];
            for (int i = 0; i < len; i++) {
                parent[i] = i;
            }
        }

        int findParent(int num) {
            if (num != parent[num]) {
                parent[num] = findParent(parent[num]);
            }

            return parent[num];
        }

        void union(int x, int y) {
            parent[findParent(x)] = findParent(y);
        }
    }

    /**
     * 1192. 查找集群内的「关键连接」
     *
     * <p>力扣数据中心有 n 台服务器，分别按从 0 到 n-1 的方式进行了编号。
     *
     * <p>它们之间以「服务器到服务器」点对点的形式相互连接组成了一个内部集群，其中连接 connections 是无向的。
     *
     * <p>从形式上讲，connections[i] = [a, b] 表示服务器 a 和 b 之间形成连接。任何服务器都可以直接或者间接地通过网络到达任何其他服务器。
     *
     * <p>「关键连接」是在该集群中的重要连接，也就是说，假如我们将它移除，便会导致某些服务器无法访问其他服务器。
     *
     * <p>请你以任意顺序返回该集群内的所有 「关键连接」。
     *
     * <p>示例 1：
     *
     * <p>输入：n = 4, connections = [[0,1],[1,2],[2,0],[1,3]] 输出：[[1,3]] 解释：[[3,1]] 也是正确的。
     *
     * <p>提示：
     *
     * <p>1 <= n <= 10^5 n-1 <= connections.length <= 10^5 connections[i][0] != connections[i][1]
     * 不存在重复的连接
     *
     * @param n
     * @param connections
     * @return
     */
    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        graphMap = new HashMap<>();
        criticalConnectionsResult = new ArrayList<>();
        // 深度
        for (List<Integer> conn : connections) {
            graphMap.computeIfAbsent(conn.get(0), k -> new ArrayList<>()).add(conn.get(1));
            graphMap.computeIfAbsent(conn.get(1), k -> new ArrayList<>()).add(conn.get(0));
        }
        // 记录每个节点的深度
        depthNums = new int[n];
        dfsCriticalConnections(0, -1, 1);
        return criticalConnectionsResult;
    }

    int[] depthNums;

    Map<Integer, List<Integer>> graphMap;

    List<List<Integer>> criticalConnectionsResult;
    /**
     * 深度优先遍历
     *
     * @param num 当前节点
     * @param parent 父节点
     * @param depth 深度
     */
    private int dfsCriticalConnections(int num, int parent, int depth) {
        depthNums[num] = depth;
        List<Integer> nextList = graphMap.get(num);
        if (Objects.nonNull(nextList)) {
            for (int next : nextList) {
                // 两个节点相互连接
                if (next == parent) {
                    continue;
                }
                if (depthNums[next] != 0) {
                    depthNums[num] = Math.min(depthNums[num], depthNums[next]);
                } else {
                    depthNums[num] =
                            Math.min(depthNums[num], dfsCriticalConnections(next, num, depth + 1));
                }
                // 关键边
                if (depth < depthNums[next]) {
                    criticalConnectionsResult.add(Arrays.asList(num, next));
                }
            }
        }
        return depthNums[num];
    }

    /**
     * LCP 16. 游乐园的游览计划
     *
     * <p>又到了一年一度的春游时间，小吴计划去游乐场游玩 1 天，游乐场总共有 N 个游乐项目，编号从 0 到 N-1。小吴给每个游乐项目定义了一个非负整数值 value[i]
     * 表示自己的喜爱值。两个游乐项目之间会有双向路径相连，整个游乐场总共有 M 条双向路径，保存在二维数组 edges中。 小吴计划选择一个游乐项目 A
     * 作为这一天游玩的重点项目。上午小吴准备游玩重点项目 A 以及与项目 A 相邻的两个项目 B、C （项目A、B与C要求是不同的项目，且项目B与项目C要求相邻），并返回 A ，即存在一条
     * A-B-C-A 的路径。 下午，小吴决定再游玩重点项目 A以及与A相邻的两个项目 B'、C'，（项目A、B'与C'要求是不同的项目，且项目B'与项目C'要求相邻），并返回 A
     * ，即存在一条 A-B'-C'-A 的路径。下午游玩项目 B'、C' 可与上午游玩项目B、C存在重复项目。
     * 小吴希望提前安排好游玩路径，使得喜爱值之和最大。请你返回满足游玩路径选取条件的最大喜爱值之和，如果没有这样的路径，返回 0。
     * 注意：一天中重复游玩同一个项目并不能重复增加喜爱值了。例如：上下午游玩路径分别是 A-B-C-A与A-C-D-A 那么只能获得 value[A] + value[B] +
     * value[C] + value[D] 的总和。
     *
     * <p>示例 1：
     *
     * <p>输入：edges = [[0,1],[1,2],[0,2]], value = [1,2,3]
     *
     * <p>输出：6
     *
     * <p>解释：喜爱值之和最高的方案之一是 0->1->2->0 与 0->2->1->0 。重复游玩同一点不重复计入喜爱值，返回1+2+3=6
     *
     * <p>示例 2：
     *
     * <p>输入：edges = [[0,2],[2,1]], value = [1,2,5]
     *
     * <p>输出：0
     *
     * <p>解释：无满足要求的游玩路径，返回 0
     *
     * <p>示例 3：
     *
     * <p>输入：edges = [[0,1],[0,2],[0,3],[0,4],[0,5],[1,3],[2,4],[2,5],[3,4],[3,5],[4,5]], value =
     * [7,8,6,8,9,7]
     *
     * <p>输出：39
     *
     * <p>解释：喜爱值之和最高的方案之一是 3->0->1->3 与 3->4->5->3 。喜爱值最高为 7+8+8+9+7=39
     *
     * <p>限制：
     *
     * <p>3 <= value.length <= 10000 1 <= edges.length <= 10000 0 <= edges[i][0],edges[i][1] <
     * value.length 0 <= value[i] <= 10000 edges中没有重复的边 edges[i][0] != edges[i][1]
     *
     * @param edges
     * @param value
     * @return
     */
    public int maxWeight(int[][] edges, int[] value) {
        if (edges.length < 3 || value.length < 3) {
            return 0;
        }
        int len = value.length;
        this.valueNums = value;
        Map<Integer, Set<Integer>> graph = new HashMap<>();
        int[] degree = new int[len];
        List<int[]> edgeList = new ArrayList<>();

        // 构建图
        for (int[] edge : edges) {
            graph.computeIfAbsent(edge[0], k -> new HashSet<>()).add(edge[1]);
            graph.computeIfAbsent(edge[1], k -> new HashSet<>()).add(edge[0]);
            degree[edge[0]]++;
            degree[edge[1]]++;
            edgeList.add(edge);
        }
        edgeList.sort((a, b) -> (value[b[0]] + value[b[1]]) - (value[a[0]] + value[a[1]]));
        int maxD = (int) Math.sqrt(edges.length);

        int result = 0;
        // 遍历 value
        for (int i = 0; i < len; i++) {
            Set<Integer> set = graph.get(i);
            if (Objects.isNull(set)) {
                continue;
            }
            // 以点i 作为 重点项目 A
            if (degree[i] >= maxD) {
                // 枚举权重最大的前3条边
                int b = -1, b1 = -1, b2 = -1, c = -1, c1 = -1, c2 = -1;

                int maxEdgeValue = -1;
                // 直接遍历所有的边
                for (int[] edge : edgeList) {
                    if (!set.contains(edge[0]) || !set.contains(edge[1])) {
                        continue;
                    }
                    if (b == -1) {
                        b = edge[0];
                        c = edge[1];
                        maxEdgeValue = Math.max(maxEdgeValue, calcValue(b, c, b, c));
                    } else if (b1 == -1) {
                        b1 = edge[0];
                        c1 = edge[1];
                        maxEdgeValue = Math.max(maxEdgeValue, calcValue(b1, c1, b, c));
                    } else if (b2 == -1) {
                        b2 = edge[0];
                        c2 = edge[1];
                        maxEdgeValue = Math.max(maxEdgeValue, calcValue(b, c, b2, c2));
                        maxEdgeValue = Math.max(maxEdgeValue, calcValue(b1, c1, b2, c2));
                    } else {
                        maxEdgeValue = Math.max(maxEdgeValue, calcValue(b, c, edge[0], edge[1]));
                        maxEdgeValue = Math.max(maxEdgeValue, calcValue(b1, c1, edge[0], edge[1]));
                        maxEdgeValue = Math.max(maxEdgeValue, calcValue(b2, c2, edge[0], edge[1]));
                    }
                }
                if (maxEdgeValue != -1) {
                    result = Math.max(result, value[i] + maxEdgeValue);
                }
            } else {
                // 遍历所有的点 计算
                // 当前点的所有边
                List<int[]> pointEdgeList = new ArrayList<>();
                for (int b : set) {
                    for (int c : set) {
                        if (b >= c) {
                            continue;
                        }
                        Set<Integer> bSet = graph.get(b);
                        if (Objects.isNull(bSet) || !bSet.contains(c)) {
                            continue;
                        }
                        pointEdgeList.add(new int[] {b, c});
                    }
                }
                int maxEdgeValue = -1;
                // 排序
                pointEdgeList.sort(
                        (a, b) -> (value[b[0]] + value[b[1]]) - (value[a[0]] + value[a[1]]));
                // 枚举权重最大的前3条边
                int b = -1, b1 = -1, b2 = -1, c = -1, c1 = -1, c2 = -1;
                // 直接遍历所有的边
                for (int[] edge : pointEdgeList) {
                    if (b == -1) {
                        b = edge[0];
                        c = edge[1];
                        maxEdgeValue = Math.max(maxEdgeValue, calcValue(b, c, b, c));
                    } else if (b1 == -1) {
                        b1 = edge[0];
                        c1 = edge[1];
                        maxEdgeValue = Math.max(maxEdgeValue, calcValue(b1, c1, b, c));
                    } else if (b2 == -1) {
                        b2 = edge[0];
                        c2 = edge[1];
                        maxEdgeValue = Math.max(maxEdgeValue, calcValue(b, c, b2, c2));
                        maxEdgeValue = Math.max(maxEdgeValue, calcValue(b1, c1, b2, c2));
                    } else {
                        maxEdgeValue = Math.max(maxEdgeValue, calcValue(b, c, edge[0], edge[1]));
                        maxEdgeValue = Math.max(maxEdgeValue, calcValue(b1, c1, edge[0], edge[1]));
                        maxEdgeValue = Math.max(maxEdgeValue, calcValue(b2, c2, edge[0], edge[1]));
                    }
                }
                if (maxEdgeValue != -1) {
                    result = Math.max(result, value[i] + maxEdgeValue);
                }
            }
        }

        return result;
    }

    private int[] valueNums;

    /**
     * 获取两个三角形的 最大value
     *
     * @param b
     * @param c
     * @param b1
     * @param c1
     * @return
     */
    private int calcValue(int b, int c, int b1, int c1) {
        int result = valueNums[b] + valueNums[c];
        if (b1 != b && b1 != c) {
            result += valueNums[b1];
        }
        if (c1 != b && c1 != c) {
            result += valueNums[c1];
        }
        return result;
    }

    @Test
    public void maxWeight() {
        int[][] edges = {{0, 1}, {1, 2}, {0, 2}};
        int[] value = {1, 2, 3};
        logResult(maxWeight(edges, value));
    }

    /**
     * LCP 21. 追逐游戏
     *
     * <p>秋游中的小力和小扣设计了一个追逐游戏。他们选了秋日市集景区中的 N 个景点，景点编号为 1~N。此外，他们还选择了 N
     * 条小路，满足任意两个景点之间都可以通过小路互相到达，且不存在两条连接景点相同的小路。整个游戏场景可视作一个无向连通图，记作二维数组 edges，数组中以 [a,b] 形式表示景点 a
     * 与景点 b 之间有一条小路连通。
     *
     * <p>小力和小扣只能沿景点间的小路移动。小力的目标是在最快时间内追到小扣，小扣的目标是尽可能延后被小力追到的时间。游戏开始前，两人分别站在两个不同的景点 startA 和
     * startB。每一回合，小力先行动，小扣观察到小力的行动后再行动。小力和小扣在每回合可选择以下行动之一：
     *
     * <p>移动至相邻景点 留在原地 如果小力追到小扣（即两人于某一时刻出现在同一位置），则游戏结束。若小力可以追到小扣，请返回最少需要多少回合；若小力无法追到小扣，请返回 -1。
     *
     * <p>注意：小力和小扣一定会采取最优移动策略。
     *
     * <p>示例 1：
     *
     * <p>输入：edges = [[1,2],[2,3],[3,4],[4,1],[2,5],[5,6]], startA = 3, startB = 5
     *
     * <p>输出：3
     *
     * <p>解释： image.png
     *
     * <p>第一回合，小力移动至 2 号点，小扣观察到小力的行动后移动至 6 号点； 第二回合，小力移动至 5 号点，小扣无法移动，留在原地； 第三回合，小力移动至 6
     * 号点，小力追到小扣。返回 3。
     *
     * <p>示例 2：
     *
     * <p>输入：edges = [[1,2],[2,3],[3,4],[4,1]], startA = 1, startB = 3
     *
     * <p>输出：-1
     *
     * <p>解释： image.png
     *
     * <p>小力如果不动，则小扣也不动；否则小扣移动到小力的对角线位置。这样小力无法追到小扣。
     *
     * <p>提示：
     *
     * <p>edges 的长度等于图中节点个数 3 <= edges.length <= 10^5 1 <= edges[i][0], edges[i][1] <= edges.length
     * 且 edges[i][0] != edges[i][1] 1 <= startA, startB <= edges.length 且 startA != startB
     *
     * @param edges
     * @param startA
     * @param startB
     * @return
     */
    public int chaseGame(int[][] edges, int startA, int startB) {
        int n = edges.length;
        Map<Integer, List<Integer>> graph = new HashMap<>();
        int[] degree = new int[n + 1];

        int ringHead = -1;
        // 构建图
        for (int[] edge : edges) {
            graph.computeIfAbsent(edge[0], k -> new ArrayList<>()).add(edge[1]);
            graph.computeIfAbsent(edge[1], k -> new ArrayList<>()).add(edge[0]);
            // startA 和 startB 相邻
            if ((edge[0] == startA && edge[1] == startB)
                    || (edge[1] == startA && edge[0] == startB)) {
                return 1;
            }
            degree[edge[0]]++;
            degree[edge[1]]++;
            if (degree[edge[0]] == 3) {
                ringHead = edge[0];
            }
            if (degree[edge[1]] == 3) {
                ringHead = edge[1];
            }
        }

        // n 个点 n条边 一定有一个环 通过 degree 找到 环 degree == 3 的是换的焦点,
        // 不存在度数为3的点 说明所有的点都在 环上
        if (ringHead == -1) {
            // 永远追不上
            return -1;
        }
        // 记录 所有点 到 startA 和 startB 的距离
        int[] distA = new int[n + 1], distB = new int[n + 1];
        chaseGameBfs(graph, distA, startA);
        chaseGameBfs(graph, distB, startB);
        // 找到 degree 为 1 的点, 作为起点, ringHead 作为终点, 这些点在环外, 其他点在环内
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            if (degree[i] == 1) {
                queue.offer(i);
            }
        }
        int ringCount = n;
        while (!queue.isEmpty()) {
            Integer p = queue.poll();
            if (Objects.isNull(p)) {
                break;
            }
            ringCount--;
            for (int next : graph.get(p)) {
                degree[next]--;
                if (degree[next] == 1) {
                    queue.offer(next);
                }
            }
        }
        int result = -1;
        for (int i = 1; i <= n; i++) {
            if (distA[i] > distB[i] + 1) {
                // 表示这是环上的点，B可以先一步到达
                if (degree[i] > 1 && ringCount > 3) {
                    result = -1;
                    break;
                } else {
                    result = Math.max(result, distA[i]);
                }
            }
        }

        return result;
    }

    private void chaseGameBfs(Map<Integer, List<Integer>> graph, int[] dist, int start) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);
        dist[start] = 0;
        while (!queue.isEmpty()) {
            Integer p = queue.poll();
            if (Objects.isNull(p)) {
                break;
            }
            for (int next : graph.get(p)) {
                if (dist[next] == 0 && next != start) {
                    dist[next] = dist[p] + 1;
                    queue.offer(next);
                }
            }
        }
    }
}
