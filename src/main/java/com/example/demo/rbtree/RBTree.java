package com.example.demo.rbtree;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @Author: zhoujing
 * @Date: 2023/6/30 13:11
 * @Description: 红黑树
 */
public class RBTree<T extends Comparable<T>> {
    /**
     * 根节点
     */
    private final RBTreeNode<T> root;
    /**
     * 节点编号
     */
    private AtomicLong size = new AtomicLong(0);
    /**
     * 覆盖模式：所有的节点不能有相同的值
     * 非覆盖模式(不推荐)：节点可以有相同的值
     */
    private volatile boolean overrideMode = true;

    public  RBTree() {
        this.root = new RBTreeNode<>();
    }

    public RBTree(boolean overrideMode) {
        this();
        this.overrideMode = overrideMode;
    }

    public boolean isOverrideMode() {
        return overrideMode;
    }

    public void setOverrideMode(boolean overrideMode) {
        this.overrideMode = overrideMode;
    }

    public long getSize() {
        return size.get();
    }

    /**
     * 获取根节点
     * 所有叶子节点统一指向同一个哨兵节点 根节点的父节点也指向哨兵节点。
     * 根节点的左子节点被用作实际的存储起点，而不是根节点本身。
     * 这样设计的好处是可以简化红黑树的操作，例如在插入和删除节点时可以统一处理哨兵节点。同时，由于根节点被定义为哨兵节点，不存储实际数据，
     * 因此根节点的左子节点可以存储树中的第一个真实节点，使得实际数据可以更方便地在树中操作。
     * @return 根节点
     */
    public RBTreeNode<T> getRoot() {
        return root.getLeft();
    }

    /**
     * 添加元素
     * 如果该值存在于该树中，则向新节点添加值，
     *
     * 如果值存在，则返回存在的值。否则返回null
     *
     * 如果覆盖模式为真、如果值存在于树中，
     *
     * 它将覆盖树中的旧值
     * @param value
     * @return
     */
    public T addNode(T value) {
//        RBTreeNode<T> t = new RBTreeNode<>(value);
//        return addNode(t);
        return value;
    }
}
