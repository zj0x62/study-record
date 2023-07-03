package com.example.demo.rbtree;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @Author: zhoujing
 * @Date: 2023/6/30 13:11
 * @Description: 红黑树
 *
 * 红黑树是一种自平衡的二叉搜索树，它有以下性质：
 * 1.节点是红色或黑色。
 * 2.根节点是黑色。
 * 3.所有叶子节点（叶子节点指的是空节点）都是黑色。
 * 4.如果一个节点是红色的，则它的两个子节点都是黑色的。
 * 5.对于每个节点，从该节点到其所有后代叶子节点的简单路径上，均包含相同数量的黑色节点。
 */
public class RBTree<T extends Comparable<T>> {
    /**
     * 根节点
     */
    private final RBTreeNode<T> root;
    /**
     * 节点编号
     */
    private final AtomicLong size = new AtomicLong(0);
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
     * 如果值存在，则返回存在的值。否则返回null
     * 如果覆盖模式为真、如果值存在于树中，
     * 它将覆盖树中的旧值
     * @param value
     * @return
     */
    public T addNode(T value) {
        RBTreeNode<T> t = new RBTreeNode<>(value);
        return addNode(t);
    }

    private T addNode(RBTreeNode<T> node) {
        node.setLeft(null);
        node.setRight(null);
        /*
          当向红黑树中插入节点时，首先将新节点插入为一个红色节点。接下来，需要进行调整操作，以满足红黑树的性质。调整操作的目标是通过变色和旋转操作来保持红黑树的平衡。
          具体的调整操作包括以下情况（以下以父节点为当前节点进行讨论）：
          当父节点是黑色，不需要进行调整操作。
          当父节点是红色且叔父节点也是红色，需要进行颜色调整。
          当父节点是红色且叔父节点是黑色或缺失，需要进行旋转和颜色调整操作。
         */
        node.setRed(true);
        setParent(node, null);
        if (root.getLeft() == null) {
            root.setLeft(node);
            node.setRed(false);
            size.incrementAndGet();
        } else {
            RBTreeNode<T> x = findParentNode(node);
            int cmp = x.getValue().compareTo(node.getValue());
            if (this.overrideMode && cmp == 0) {
                T v = x.getValue();
                x.setValue(node.getValue());
                return v;
            } else if (cmp == 0) {
                return x.getValue();
            }
            setParent(node, x);
            if (cmp > 0) {
                x.setLeft(node);
            } else {
                x.setRight(node);
            }
            fixInsert(node);
            size.incrementAndGet();
        }
        return null;
    }

    private void setParent(RBTreeNode<T> node, RBTreeNode<T> parent) {
        if (node != null) {
            node.setParent(parent);
            if (parent == null) {
                node.setParent(null);
            }
        }
    }

    private RBTreeNode<T> findParentNode(RBTreeNode<T> x) {
        RBTreeNode<T> dataRoot = getRoot();
        RBTreeNode<T> child = dataRoot;
        while (child != null) {
            int cmp = child.getValue().compareTo(x.getValue());
            if (cmp == 0) {
                return child;
            }
            if (cmp > 0) {
                dataRoot = child;
                child = child.getLeft();
            } else if (cmp < 0) {
                dataRoot = child;
                child = child.getRight();
            }
        }
        return dataRoot;
    }

    private void fixInsert(RBTreeNode<T> x) {
        RBTreeNode<T> parent = x.getParent();
        while (parent != null && parent.isRed()) {
            RBTreeNode<T> uncle = getUncle(x);
            if (uncle == null) {
                RBTreeNode<T> ancestor = parent.getParent();
                if (parent == ancestor.getLeft()) {
                    boolean isRight = x == parent.getRight();
                    if (isRight) {
                        rotateLeft(parent);
                    }
                    rotateRight(ancestor);

                    if (isRight) {
                        x.setRed(false);
                        parent = null;
                    } else {
                        parent.setRed(false);
                    }
                    ancestor.setRed(true);
                } else {
                    boolean isLeft = x == parent.getLeft();
                    if (isLeft) {
                        rotateRight(parent);
                    }
                    rotateLeft(ancestor);

                    if (isLeft) {
                        x.setRed(false);
                        parent = null;
                    } else {
                        parent.setRed(false);
                    }
                    ancestor.setRed(true);
                }
            } else {
                parent.setRed(false);
                uncle.setRed(false);
                parent.getParent().setRed(true);
                x = parent.getParent();
                parent = x.getParent();
            }
        }
        getRoot().makeBlack();
        getRoot().setParent(null);
    }

    private RBTreeNode<T> getUncle(RBTreeNode<T> node) {
        RBTreeNode<T> parent = node.getParent();
        RBTreeNode<T> ancestor = parent.getParent();
        if (ancestor == null) {
            return null;
        }
        if (parent == ancestor.getLeft()) {
            return ancestor.getRight();
        } else {
            return ancestor.getLeft();
        }
    }

    private void rotateLeft(RBTreeNode<T> node) {
        RBTreeNode<T> right = node.getRight();
        if (right == null) {
            throw new IllegalStateException("right node is null");
        }
        RBTreeNode<T> parent = node.getParent();
        node.setRight(right.getLeft());
        setParent(right.getLeft(), node);

        right.setLeft(node);
        setParent(node, right);

        if (parent == null) {
            root.setLeft(right);
            setParent(right, null);
        } else {
            if (parent.getLeft() == node) {
                parent.setLeft(right);
            } else {
                parent.setRight(right);
            }
            setParent(right, parent);
        }
    }

    private void rotateRight(RBTreeNode<T> node) {
        RBTreeNode<T> left = node.getLeft();
        if (left == null) {
            throw new IllegalStateException("left node is  null");
        }
        RBTreeNode<T> parent = node.getParent();
        node.setLeft(left.getRight());
        setParent(left.getRight(), node);

        left.setRight(node);
        setParent(node, left);

        if (parent == null) {
            root.setLeft(left);
            setParent(left, null);
        } else {
            if (parent.getLeft() == node) {
                parent.setLeft(left);
            } else {
                parent.setRight(left);
            }
            setParent(left, parent);
        }
    }

    public void printTree(RBTreeNode<T> root){
        java.util.LinkedList<RBTreeNode<T>> queue =new java.util.LinkedList<RBTreeNode<T>>();
        java.util.LinkedList<RBTreeNode<T>> queue2 =new java.util.LinkedList<RBTreeNode<T>>();
        if(root==null){
            return ;
        }
        queue.add(root);
        boolean firstQueue = true;

        while(!queue.isEmpty() || !queue2.isEmpty()){
            java.util.LinkedList<RBTreeNode<T>> q = firstQueue ? queue : queue2;
            RBTreeNode<T> n = q.poll();

            if(n!=null){
                String pos = n.getParent()==null ? "" : ( n == n.getParent().getLeft()
                        ? " LE" : " RI");
                String pstr = n.getParent()==null ? "" : n.getParent().toString();
                String cstr = n.isRed()?"R":"B";
                cstr = n.getParent()==null ? cstr : cstr+" ";
                System.out.print(n+"("+(cstr)+pstr+(pos)+")"+"\t");
                if(n.getLeft()!=null){
                    (firstQueue ? queue2 : queue).add(n.getLeft());
                }
                if(n.getRight()!=null){
                    (firstQueue ? queue2 : queue).add(n.getRight());
                }
            }else{
                System.out.println();
                firstQueue = !firstQueue;
            }
        }
    }

    public static void main(String[] args) {
        RBTree<String> bst = new RBTree<String>();
        bst.addNode("d");
        bst.addNode("d");
        bst.addNode("c");
        bst.addNode("c");
        bst.addNode("b");
        bst.addNode("f");

        bst.addNode("a");
        bst.addNode("e");

        bst.addNode("g");
        bst.addNode("h");


//        bst.remove("c");

        bst.printTree(bst.getRoot());
    }
}
