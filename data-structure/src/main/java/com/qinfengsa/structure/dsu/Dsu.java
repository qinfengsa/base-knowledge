package com.qinfengsa.structure.dsu;

/**
 * 并查集
 *
 * @author qinfengsa
 * @date 2021/6/4 10:20
 */
public class Dsu {

    private final int[] parent;

    public Dsu(int n) {
        this.parent = new int[n];
        for (int i = 0; i < n; ++i) {
            parent[i] = i;
        }
    }

    public int findParent(int num) {
        if (num != parent[num]) {
            parent[num] = findParent(parent[num]);
        }
        return parent[num];
    }

    public void union(int x, int y) {
        parent[findParent(x)] = findParent(y);
    }

    public boolean isConnected(int x, int y) {
        return findParent(x) == findParent(y);
    }
}
