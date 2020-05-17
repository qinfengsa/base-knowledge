package com.qinfengsa.structure.leetcode;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Queue;
import java.util.Set;

/**
 * 图论算法
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
        int result = ladderLength(beginWord,endWord,wordList);
        log.debug("result:{}",result);
    }
    /**
     * 单词接龙
     * 给定两个单词（beginWord 和 endWord）和一个字典，找到从 beginWord 到 endWord 的最短转换序列的长度。转换需遵循如下规则：
     *
     * 每次转换只能改变一个字母。
     * 转换过程中的中间单词必须是字典中的单词。
     * 说明:
     *
     * 如果不存在这样的转换序列，返回 0。
     * 所有单词具有相同的长度。
     * 所有单词只由小写字母组成。
     * 字典中不存在重复的单词。
     * 你可以假设 beginWord 和 endWord 是非空的，且二者不相同。
     * 示例 1:
     *
     * 输入:
     * beginWord = "hit",
     * endWord = "cog",
     * wordList = ["hot","dot","dog","lot","log","cog"]
     *
     * 输出: 5
     *
     * 解释: 一个最短转换序列是 "hit" -> "hot" -> "dot" -> "dog" -> "cog",
     *      返回它的长度 5。
     * 示例 2:
     *
     * 输入:
     * beginWord = "hit"
     * endWord = "cog"
     * wordList = ["hot","dot","dog","lot","log"]
     *
     * 输出: 0
     *
     * 解释: endWord "cog" 不在字典中，所以无法进行转换。
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
        Map<String,List<String>> allComboDict = new HashMap<>();
        // 用 h*t - hot 分别作k-v
        wordList.forEach(word ->{
            for (int i = 0 ; i < len; i++) {
                String key = word.substring(0,i) + "*" + word.substring(i+1,len);
                List<String> dict = allComboDict.get(key );
                if (dict == null) {
                    dict = new ArrayList<>();
                    allComboDict.put(key,dict);
                }
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
                if (Objects.equals(curWord,endWord)) {
                    return steps;
                }
                for (int j = 0 ; j < len; j++) {
                    String key = curWord.substring(0,j) + "*" + curWord.substring(j+1,len);
                    List<String> dict = allComboDict.get(key );
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
        int[][] prerequisites ={{1,0},{2,0},{3,1},{3,2} };
        //int[][] prerequisites ={{1,0}  };
        boolean result = canFinish(numCourses,prerequisites);
        log.debug("result:{}",result);
    }
    /**
     * 课程表
     * 现在你总共有 n 门课需要选，记为 0 到 n-1。
     *
     * 在选修某些课程之前需要一些先修课程。 例如，想要学习课程 0 ，你需要先完成课程 1 ，我们用一个匹配来表示他们: [0,1]
     *
     * 给定课程总量以及它们的先决条件，判断是否可能完成所有课程的学习？
     *
     * 示例 1:
     *
     * 输入: 2, [[1,0]]
     * 输出: true
     * 解释: 总共有 2 门课程。学习课程 1 之前，你需要完成课程 0。所以这是可能的。
     * 示例 2:
     *
     * 输入: 2, [[1,0],[0,1]]
     * 输出: false
     * 解释: 总共有 2 门课程。学习课程 1 之前，你需要先完成​课程 0；并且学习课程 0 之前，你还应先完成课程 1。这是不可能的。
     * 说明:
     *
     * 输入的先决条件是由边缘列表表示的图形，而不是邻接矩阵。详情请参见图的表示法。
     * 你可以假定输入的先决条件中没有重复的边。
     * 提示:
     *
     * 这个问题相当于查找一个循环是否存在于有向图中。如果存在循环，则不存在拓扑排序，因此不可能选取所有课程进行学习。
     * 通过 DFS 进行拓扑排序 - 一个关于Coursera的精彩视频教程（21分钟），介绍拓扑排序的基本概念。
     * 拓扑排序也可以通过 BFS 完成。
     * @param numCourses
     * @param prerequisites
     * @return
     */
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        int[] indegrees = new int[numCourses];
        for(int[] p : prerequisites) {
            indegrees[p[0]]++;
        }
        LinkedList<Integer> queue = new LinkedList<>();
        for(int i = 0; i < numCourses; i++){
            if(indegrees[i] == 0) {
                queue.addLast(i);
            }
        }
        while(!queue.isEmpty()) {
            Integer pre = queue.removeFirst();
            numCourses--;
            for(int[] req : prerequisites) {
                if(req[1] != pre) continue;
                if(--indegrees[req[0]] == 0) {
                    queue.add(req[0]);
                }
            }
        }
        return numCourses == 0;
    }

    public boolean dfs(int num,List<Integer>[] courses,Set<Integer> canFi,Set<Integer> visited) {
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
                boolean b = dfs(course,courses,canFi,visited);
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
        int[][] prerequisites ={{1,0},{2,0},{3,1},{3,2} };
        //int[][] prerequisites ={{1,0},{0,1}  };
        int[] result = findOrder(numCourses,prerequisites);
        log.debug("result:{}",result);
    }


    /**
     * 课程表 II
     * 现在你总共有 n 门课需要选，记为 0 到 n-1。
     *
     * 在选修某些课程之前需要一些先修课程。 例如，想要学习课程 0 ，你需要先完成课程 1 ，我们用一个匹配来表示他们: [0,1]
     *
     * 给定课程总量以及它们的先决条件，返回你为了学完所有课程所安排的学习顺序。
     *
     * 可能会有多个正确的顺序，你只要返回一种就可以了。如果不可能完成所有课程，返回一个空数组。
     *
     * 示例 1:
     *
     * 输入: 2, [[1,0]]
     * 输出: [0,1]
     * 解释: 总共有 2 门课程。要学习课程 1，你需要先完成课程 0。因此，正确的课程顺序为 [0,1] 。
     * 示例 2:
     *
     * 输入: 4, [[1,0],[2,0],[3,1],[3,2]]
     * 输出: [0,1,2,3] or [0,2,1,3]
     * 解释: 总共有 4 门课程。要学习课程 3，你应该先完成课程 1 和课程 2。并且课程 1 和课程 2 都应该排在课程 0 之后。
     *      因此，一个正确的课程顺序是 [0,1,2,3] 。另一个正确的排序是 [0,2,1,3] 。
     * 说明:
     *
     * 输入的先决条件是由边缘列表表示的图形，而不是邻接矩阵。详情请参见图的表示法。
     * 你可以假定输入的先决条件中没有重复的边。
     * 提示:
     *
     * 这个问题相当于查找一个循环是否存在于有向图中。如果存在循环，则不存在拓扑排序，因此不可能选取所有课程进行学习。
     * 通过 DFS 进行拓扑排序 - 一个关于Coursera的精彩视频教程（21分钟），介绍拓扑排序的基本概念。
     * 拓扑排序也可以通过 BFS 完成。
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
        Map<Integer,List<Integer>> map = new HashMap<>();
        for (int[] p : prerequisites) {
            inDegrees[p[0]]++;
            List<Integer> list = map.computeIfAbsent(p[1],key -> new ArrayList<>());
            list.add(p[0]);
        }
        // 队列 广度优先遍历
        Deque<Integer> queue = new LinkedList<>();
        for(int i = 0; i < numCourses; i++){
            if(inDegrees[i] == 0) {
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
        char[][] board = {{'X','X','X','X'},{'X','O','O','X'},{'X','X','O','X'},{'X','O','X','X'}};
        solve(board);
        for (char[] a: board) {
            log.debug("borad:{}",a);
        }

    }

    /**
     * 被围绕的区域
     * 给定一个二维的矩阵，包含 'X' 和 'O'（字母 O）。
     *
     * 找到所有被 'X' 围绕的区域，并将这些区域里所有的 'O' 用 'X' 填充。
     *
     * 示例:
     *
     * X X X X
     * X O O X
     * X X O X
     * X O X X
     * 运行你的函数后，矩阵变为：
     *
     * X X X X
     * X X X X
     * X X X X
     * X O X X
     * 解释:
     *
     * 被围绕的区间不会存在于边界上，换句话说，任何边界上的 'O' 都不会被填充为 'X'。 任何不在边界上，或不与边界上的 'O' 相连的 'O' 最终都会被填充为 'X'。如果两个元素在水平或垂直方向相邻，则称它们是“相连”的。
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
        for (int i = 0; i < rows;i++) {
            if (board[i][0] == 'O') {
                updateAround(board,i,0);
            }
            if (board[i][cols-1] == 'O') {
                updateAround(board,i,cols-1);
            }
        }
        // 第一行 和 最后一行
        for (int i = 1; i < cols - 1;i++) {
            if (board[0][i] == 'O') {
                updateAround(board,0,i);
            }
            if (board[rows-1][i] == 'O') {
                updateAround(board,rows-1,i);
            }
        }
        for (int i = 0; i < rows;i++) {
            for (int j = 0; j < cols;j++) {
                if (board[i][j] == '*') {
                    board[i][j] = 'O';
                } else if (board[i][j] != 'X')  {
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
            updateAround(board,row - 1,col);
            updateAround(board,row + 1,col);
            updateAround(board,row,col - 1);
            updateAround(board,row ,col + 1);
        }

    }

    /*// 更新相邻坐标
    updateAround(grid,row - 1,col);
    updateAround(grid,row + 1,col);
    updateAround(grid,row,col - 1);
    updateAround(grid,row ,col + 1);*/


    /**
     * 997. 找到小镇的法官
     * 在一个小镇里，按从 1 到 N 标记了 N 个人。传言称，这些人中有一个是小镇上的秘密法官。
     *
     * 如果小镇的法官真的存在，那么：
     *
     * 小镇的法官不相信任何人。
     * 每个人（除了小镇法官外）都信任小镇的法官。
     * 只有一个人同时满足属性 1 和属性 2 。
     * 给定数组 trust，该数组由信任对 trust[i] = [a, b] 组成，表示标记为 a 的人信任标记为 b 的人。
     *
     * 如果小镇存在秘密法官并且可以确定他的身份，请返回该法官的标记。否则，返回 -1。
     *
     *
     *
     * 示例 1：
     *
     * 输入：N = 2, trust = [[1,2]]
     * 输出：2
     * 示例 2：
     *
     * 输入：N = 3, trust = [[1,3],[2,3]]
     * 输出：3
     * 示例 3：
     *
     * 输入：N = 3, trust = [[1,3],[2,3],[3,1]]
     * 输出：-1
     * 示例 4：
     *
     * 输入：N = 3, trust = [[1,2],[2,3]]
     * 输出：-1
     * 示例 5：
     *
     * 输入：N = 4, trust = [[1,3],[1,4],[2,3],[2,4],[4,3]]
     * 输出：3
     *
     *
     * 提示：
     *
     * 1 <= N <= 1000
     * trust.length <= 10000
     * trust[i] 是完全不同的
     * trust[i][0] != trust[i][1]
     * 1 <= trust[i][0], trust[i][1] <= N
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

        for (int i = 1; i <= N ; i++) {
            if (inDegree[i] == N - 1 && outDegree[i] == 0) {
                return i;
            }
        }
        return -1;
    }


    /**
     * 1042. 不邻接植花
     * 有 N 个花园，按从 1 到 N 标记。在每个花园中，你打算种下四种花之一。
     *
     * paths[i] = [x, y] 描述了花园 x 到花园 y 的双向路径。
     *
     * 另外，没有花园有 3 条以上的路径可以进入或者离开。
     *
     * 你需要为每个花园选择一种花，使得通过路径相连的任何两个花园中的花的种类互不相同。
     *
     * 以数组形式返回选择的方案作为答案 answer，其中 answer[i] 为在第 (i+1) 个花园中种植的花的种类。
     * 花的种类用  1, 2, 3, 4 表示。保证存在答案。
     *
     *
     *
     * 示例 1：
     *
     * 输入：N = 3, paths = [[1,2],[2,3],[3,1]]
     * 输出：[1,2,3]
     * 示例 2：
     *
     * 输入：N = 4, paths = [[1,2],[3,4]]
     * 输出：[1,2,1,2]
     * 示例 3：
     *
     * 输入：N = 4, paths = [[1,2],[2,3],[3,4],[4,1],[1,3],[2,4]]
     * 输出：[1,2,3,4]
     *
     *
     * 提示：
     *
     * 1 <= N <= 10000
     * 0 <= paths.size <= 20000
     * 不存在花园有 4 条或者更多路径可以进入或离开。
     * 保证存在答案。
     * @param N
     * @param paths
     * @return
     */
    public int[] gardenNoAdj(int N, int[][] paths) {
        Map<Integer,Set<Integer>> graph = new HashMap<>();

        for (int i = 0; i < N; i++) {
            graph.put(i,new HashSet<>());
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
            for (int adj: set) {
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
     * 5400. 旅行终点站
     * 给你一份旅游线路图，该线路图中的旅行线路用数组 paths 表示，其中 paths[i] = [cityAi, cityBi] 表示该线路将会从 cityAi 直接前往 cityBi 。请你找出这次旅行的终点站，即没有任何可以通往其他城市的线路的城市。
     *
     * 题目数据保证线路图会形成一条不存在循环的线路，因此只会有一个旅行终点站。
     *
     *
     *
     * 示例 1：
     *
     * 输入：paths = [["London","New York"],["New York","Lima"],["Lima","Sao Paulo"]]
     * 输出："Sao Paulo"
     * 解释：从 "London" 出发，最后抵达终点站 "Sao Paulo" 。本次旅行的路线是 "London" -> "New York" -> "Lima" -> "Sao Paulo" 。
     * 示例 2：
     *
     * 输入：paths = [["B","C"],["D","B"],["C","A"]]
     * 输出："A"
     * 解释：所有可能的线路是：
     * "D" -> "B" -> "C" -> "A".
     * "B" -> "C" -> "A".
     * "C" -> "A".
     * "A".
     * 显然，旅行终点站是 "A" 。
     * 示例 3：
     *
     * 输入：paths = [["A","Z"]]
     * 输出："Z"
     *
     *
     * 提示：
     *
     * 1 <= paths.length <= 100
     * paths[i].length == 2
     * 1 <= cityAi.length, cityBi.length <= 10
     * cityAi != cityBi
     * 所有字符串均由大小写英文字母和空格字符组成。
     * @param paths
     * @return
     */
    public String destCity(List<List<String>> paths) {
        // 计算出度为0的城市 入度
        String result = "";
        Map<String,Integer> outMap = new HashMap<>();
        Map<String,Integer> inMap = new HashMap<>();
        paths.forEach(path -> {
            int count1 = outMap.getOrDefault(path.get(0),0);
            outMap.put(path.get(0),count1 + 1);
            int count2 = inMap.getOrDefault(path.get(1),0);
            inMap.put(path.get(1),count2 + 1);
        });
        for (String key : inMap.keySet()) {
            if (!outMap.containsKey(key)) {
                result = key;
            }
        }


        return result;
    }


    /**
     * 332. 重新安排行程
     * 给定一个机票的字符串二维数组 [from, to]，子数组中的两个成员分别表示飞机出发和降落的机场地点，对该行程进行重新规划排序。
     * 所有这些机票都属于一个从JFK（肯尼迪国际机场）出发的先生，所以该行程必须从 JFK 出发。
     *
     * 说明:
     *
     * 如果存在多种有效的行程，你可以按字符自然排序返回最小的行程组合。例如，行程 ["JFK", "LGA"] 与 ["JFK", "LGB"] 相比就更小，排序更靠前
     * 所有的机场都用三个大写字母表示（机场代码）。
     * 假定所有机票至少存在一种合理的行程。
     * 示例 1:
     *
     * 输入: [["MUC", "LHR"], ["JFK", "MUC"], ["SFO", "SJC"], ["LHR", "SFO"]]
     * 输出: ["JFK", "MUC", "LHR", "SFO", "SJC"]
     * 示例 2:
     *
     * 输入: [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
     * 输出: ["JFK","ATL","JFK","SFO","ATL","SFO"]
     * 解释: 另一种有效的行程是 ["JFK","SFO","ATL","JFK","ATL","SFO"]。但是它自然排序更大更靠后。
     * @param tickets
     * @return
     */
    public List<String> findItinerary(List<List<String>> tickets) {
        List<String> result = new ArrayList<>();
        if (tickets.isEmpty()) {
            return result;
        }
        Map<String,List<String>> graph = new HashMap<>();
        for (List<String> ticket : tickets) {
            String start = ticket.get(0);
            String end = ticket.get(1);
            List<String> list = graph.computeIfAbsent(start,key -> new ArrayList<>());
            list.add(end);
        }

        graph.values().forEach(values -> values.sort(String::compareTo));

        // 开始深度优先遍历
        visitItinerary(graph,"JFK",result);

        return result;
    }


    private void visitItinerary(Map<String,List<String>> graph,String start,List<String> result) {
        List<String> list = graph.get(start);
        while (Objects.nonNull(list) && list.size() > 0) {
            visitItinerary(graph,list.remove(0),result);
        }
        result.add(0,start);
    }


}
