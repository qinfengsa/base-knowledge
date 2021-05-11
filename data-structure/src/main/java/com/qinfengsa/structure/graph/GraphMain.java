package com.qinfengsa.structure.graph;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import lombok.extern.slf4j.Slf4j;

/**
 * 图
 *
 * @author qinfengsa
 * @date 2021/5/10 13:32
 */
@Slf4j
public class GraphMain {

    /**
     * LCP 35. 电动车游城市
     *
     * <p>小明的电动车电量充满时可行驶距离为 cnt，每行驶 1 单位距离消耗 1 单位电量，且花费 1 单位时间。小明想选择电动车作为代步工具。地图上共有 N 个景点，景点编号为 0 ~
     * N-1。他将地图信息以 [城市 A 编号,城市 B 编号,两城市间距离] 格式整理在在二维数组 paths，表示城市 A、B 间存在双向通路。初始状态，电动车电量为
     * 0。每个城市都设有充电桩，charge[i] 表示第 i 个城市每充 1 单位电量需要花费的单位时间。请返回小明最少需要花费多少单位时间从起点城市 start 抵达终点城市 end。
     *
     * <p>示例 1：
     *
     * <p>输入：paths = [[1,3,3],[3,2,1],[2,1,3],[0,1,4],[3,0,5]], cnt = 6, start = 1, end = 0, charge
     * = [2,10,4,1]
     *
     * <p>输出：43
     *
     * <p>解释：最佳路线为：1->3->0。 在城市 1 仅充 3 单位电至城市 3，然后在城市 3 充 5 单位电，行驶至城市 5。 充电用时共 3*10 + 5*1= 35 行驶用时 3
     * + 5 = 8，此时总用时最短 43。 image.png
     *
     * <p>示例 2：
     *
     * <p>输入：paths = [[0,4,2],[4,3,5],[3,0,5],[0,1,5],[3,2,4],[1,2,8]], cnt = 8, start = 0, end = 2,
     * charge = [4,1,1,3,2]
     *
     * <p>输出：38
     *
     * <p>解释：最佳路线为：0->4->3->2。 城市 0 充电 2 单位，行驶至城市 4 充电 8 单位，行驶至城市 3 充电 1 单位，最终行驶至城市 2。 充电用时
     * 4*2+2*8+3*1 = 27 行驶用时 2+5+4 = 11，总用时最短 38。
     *
     * <p>提示：
     *
     * <p>1 <= paths.length <= 200 paths[i].length == 3 2 <= charge.length == n <= 100 0 <=
     * path[i][0],path[i][1],start,end < n 1 <= cnt <= 100 1 <= path[i][2] <= cnt 1 <= charge[i] <=
     * 100 题目保证所有城市相互可以到达
     *
     * @param paths
     * @param cnt
     * @param start
     * @param end
     * @param charge
     * @return
     */
    public int electricCarPlan(int[][] paths, int cnt, int start, int end, int[] charge) {
        if (start == end) {
            return 0;
        }
        int n = charge.length;
        // 建图
        int[][] graph = new int[n][n];
        for (int[] nums : graph) {
            Arrays.fill(nums, Integer.MAX_VALUE >> 1);
        }
        for (int[] path : paths) {
            int from = path[0], to = path[1];
            int dist = Math.min(path[2], graph[from][to]);
            graph[from][to] = dist;
            graph[to][from] = dist;
        }
        PriorityQueue<CarNode> queue = new PriorityQueue<>(Comparator.comparingInt(a -> a.time));
        queue.offer(new CarNode(start, 0, 0));

        // 记录最小时间
        int[][] dp = new int[n][cnt + 1];
        for (int[] nums : dp) {
            Arrays.fill(nums, Integer.MAX_VALUE >> 1);
        }
        dp[start][0] = 0;
        while (!queue.isEmpty()) {
            CarNode node = queue.poll();
            int city = node.city, elec = node.elec, time = node.time;
            if (city == end) {
                return time;
            }
            if (time > dp[city][elec]) {
                continue;
            }
            // 充电
            int chargeCnt = 0;
            for (int j = elec + 1; j <= cnt; j++) {
                chargeCnt++;
                int nextTime = time + chargeCnt * charge[city];
                if (nextTime < dp[city][j]) {
                    dp[city][j] = nextTime;
                    queue.offer(new CarNode(city, j, nextTime));
                }
            }
            // 下一个
            for (int next = 0; next < n; next++) {
                if (next == city) {
                    continue;
                }
                int dist = graph[city][next];

                // 电量不够 或不存在通路
                if (dist > elec) {
                    continue;
                }
                int nextTime = time + dist, nextElec = elec - dist;
                if (nextTime < dp[next][nextElec]) {
                    dp[next][nextElec] = nextTime;
                    queue.offer(new CarNode(next, nextElec, nextTime));
                }
            }
        }

        return -1;
        /*
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        pq.offer(new int[] {0, start, 0});
        while (!pq.isEmpty()) {
            int[] c = pq.poll();
            int d = c[0];
            // current location
            int loc = c[1];
            if (loc == end) return (d);
            // current charge
            int ch = c[2];
            if (d > dp[loc][ch]) continue;
            if (ch < cnt) {
                int newch = ch + 1;
                int newdist = d + charge[loc];
                if (newdist < dp[loc][newch]) {
                    dp[loc][newch] = newdist;
                    pq.offer(new int[] {newdist, loc, newch});
                }
            }


            for (int next = 0; next < n; next++) {
                if (next == loc) {
                    continue;
                }
                int dln = graph[loc][next];

                // 电量不够 或不存在通路
                if (dln > ch) {
                    continue;
                }
                int newch = ch - dln;
                int newdist = d + dln;
                if (newdist < dp[next][newch]) {
                    dp[next][newch] = newdist;
                    pq.offer(new int[] {newdist, next, newch});
                }
            }
        }
        return -1;*/
    }

    int[][] graph, dp;
    int[] charge;
    int n, cnt;

    class CarNode {
        // 位置 电量 时间
        int city, elec, time;

        public CarNode(int city, int elec, int time) {
            this.city = city;
            this.elec = elec;
            this.time = time;
        }
    }

    public void dfsCarPlan(int idx) {}
}
