package com.qinfengsa.structure.stack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 克隆图
 * @author: qinfengsa
 * @date: 2019/10/31 18:05
 */
public class CloneGraph {


    public static void main(String[] args) {

    }


    /**
     * 克隆图
     * 给定无向连通图中一个节点的引用，返回该图的深拷贝（克隆）。图中的每个节点都包含它的值 val（Int） 和其邻居的列表（list[Node]）。
     *
     * 示例：
     *
     *
     *
     * 输入：
     * {"$id":"1","neighbors":[{"$id":"2","neighbors":[{"$ref":"1"},{"$id":"3","neighbors":
     * [{"$ref":"2"},{"$id":"4","neighbors":[{"$ref":"3"},{"$ref":"1"}],"val":4}],"val":3}],"val":2},{"$ref":"4"}],"val":1}
     *
     * 解释：
     * 节点 1 的值是 1，它有两个邻居：节点 2 和 4 。
     * 节点 2 的值是 2，它有两个邻居：节点 1 和 3 。
     * 节点 3 的值是 3，它有两个邻居：节点 2 和 4 。
     * 节点 4 的值是 4，它有两个邻居：节点 1 和 3 。
     *
     *
     * 提示：
     *
     * 节点数介于 1 到 100 之间。
     * 无向图是一个简单图，这意味着图中没有重复的边，也没有自环。
     * 由于图是无向的，如果节点 p 是节点 q 的邻居，那么节点 q 也必须是节点 p 的邻居。
     * 必须将给定节点的拷贝作为对克隆图的引用返回。
     * @param node
     * @return
     */
    public Node cloneGraph(Node node) {

        if (node == null) {
            return null;
        }
        Map<Integer,Node> map = new HashMap<>();
        return cloneGraph( node, map);
    }

    private Node cloneGraph(Node node,Map<Integer,Node> map) {
        // 思路：通过深度优先遍历把 Graph 遍历一遍，然后 全部复制

        Node newNode ;
        if (node == null) {
            return null;
        }
        if (map.containsKey(node.val)) {
            newNode = map.get(node.val);
        } else {
            newNode = new Node(node.val,new ArrayList<>());
            map.put(node.val,newNode);
            // 通过递归实现
            for (Node child : node.neighbors) {
                newNode.neighbors.add(cloneGraph(child,map));
            }
        }




        return newNode;
    }



    public Node cloneGraph2(Node node,Node newNode) {

        return null;
    }





    static class Node {
        public int val;
        public List<Node> neighbors;

        public Node() {}

        public Node(int _val, List<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }
}
