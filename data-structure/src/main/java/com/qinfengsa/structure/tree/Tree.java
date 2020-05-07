package com.qinfengsa.structure.tree;

/**
 * @author: qinfengsa
 * @date: 2019/5/12 19:03
 */
public interface Tree {

    /**
     * 返回树的结点数
     * @return
     */
    int getSzie();

    /**
     * 返回树根结点
     * @return
     */
    Object getRoot(); // getParent(x) 输入参数：结点 x 返回参数：结点 功能：返回结点 x 的父结点。 ⑷ getFirstChild(x) 输入参数：结点 x 返回参数：结点 功能：返回结点 x 的第一个孩子。 ⑸ getNextSibling(x) 输入参数：结点 x 返回参数：结点 功能：返回结点 x 的下一个兄弟结点，如果 x 是后一个孩子， 则返回空。 ⑹ getHeight(x) 输入参数：无 返回参数：整数 功能：返回以 x 为根的树的高度。 ⑺ insertChild(x,child) 输入参数：结点 x、结点 child 返回参数：无 功能：将结点 child 为根的子树插入树中，作为结点 x 的子树。 ⑻ deleteChild(x,i) 输入参数：结点 x、整数 i 返回参数：无 功能：删除结点 x 的第 i 棵子树。 ⑼ preOrder(x) postOrder(x) levelOrder(x) 输入参数：结点 x, 线性表 list 返回参数：迭代器 功能：先序、后序、按层遍历 x 为根的树。
}
