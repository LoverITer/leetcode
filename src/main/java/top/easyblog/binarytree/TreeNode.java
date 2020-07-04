package top.easyblog.binarytree;

import java.util.ArrayList;
import java.util.List;

/**
 * 二叉树结点类型
 *
 * @author ：huangxin
 * @modified ：
 * @since ：2020/05/14 11:49
 */
public class TreeNode {

    int val;
    TreeNode left;
    TreeNode right;

    public TreeNode(int val) {
        this.val = val;
        this.left = null;
        this.right = null;
    }


    /**
     * <h5>创建二叉查找树</h5>
     * 根据参数传入的数组构建一颗二叉查找树,理论上一颗二叉查找树具有以下特性
     * <ul>
     *     <li>任意节点的左子树如果不为空，则左子树小于父节点的值</li>
     *     <li>任意节点的有子树如果不为空，则右子树大于父节点的值</li>
     *     <li>任意节点的左子树和右子树都是二叉查找树</li>
     *     <li>二叉查找树中没有两个节点的值相等</li>
     * </ul>
     *
     * @param array
     * @return
     */
    public static TreeNode createBinarySearchTree(int[] array) {
        if (array == null) {
            return null;
        }
        TreeNode root = null;
        for (int t : array) {
            //新节点
            TreeNode node = new TreeNode(t);
            if (root == null) {
                //根节点为空
                root = node;
            } else {
                //根节点不为空
                TreeNode curr = root;
                TreeNode parent = curr;
                while (curr != null) {
                    //寻找插入点的过程，parent就是待插入点的父节点
                    parent = curr;
                    if (node.val < curr.val) {
                        curr = curr.left;
                    } else if (node.val > curr.val) {
                        curr = curr.right;
                    } else {
                        //有相等的元素，直接抛异常
                        throw new RuntimeException("can not insert same value into tree: " + node.val);
                    }
                }
                if (node.val < parent.val) {
                    //新节点的值比父节点小
                    parent.left = node;
                } else if (node.val > parent.val) {
                    //新节点的值比父节点大
                    parent.right = node;
                } else {
                    throw new RuntimeException("can not insert same value into tree: " + node.val);
                }
            }
        }

        return root;
    }

    /**
     * 创建普通的二叉树，创建规则是按照:
     *     i
     *    / \
     *  2i  2i+1
     * @param arr
     * @return
     */
    public static TreeNode createTree(List<String> arr) {
        List<TreeNode> nodeList = new ArrayList<>();
        if (arr == null) {
            return null;
        } else {
            for (int i = 0; i < arr.size(); i++) {
                if (!arr.get(i).equals("#")) {
                    nodeList.add(new TreeNode(Integer.parseInt(arr.get(i))));
                } else {
                    nodeList.add(null);
                }
            }
            int i = 0;
            while (i <= arr.size() / 2 - 1) {
                int l = 2 * i + 1;
                int r = 2 * i + 2;
                while (arr.get(i).equals("#")) {
                    i++;
                }
                if (l < arr.size() && arr.get(l) != null && !arr.get(i).equals("#")) {
                    nodeList.get(i).left = nodeList.get(l);
                } else {
                    nodeList.get(i).left = null;
                }
                if (r < arr.size() && arr.get(r) != null && !arr.get(i).equals("#")) {
                    nodeList.get(i).right = nodeList.get(r);
                } else {
                    nodeList.get(i).right = null;
                }

                i++;
            }
            return nodeList.get(0);
        }
    }

    @Override
    public String toString(){
       return  二叉树先序遍历.preOrder2(this).toString();
    }


}
