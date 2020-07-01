package top.easyblog.binarytree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author ：huangxin
 * @modified ：
 * @since ：2020/06/27 20:50
 */
public class 二叉树中序遍历 {

    private static List<Integer> ans = new ArrayList<>();

    /**
     * 二叉树中序遍历——递归版
     *
     * @param root
     * @return
     */
    public static List<Integer> inOrder(TreeNode root) {
        if (root == null) {
            return ans;
        }
        inOrder(root.left);
        ans.add(root.val);
        inOrder(root.right);
        return ans;
    }

    /**
     * 二叉树中序遍历——非递归版
     *
     * @param root
     * @return
     */
    public static List<Integer> inOrder2(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }
        LinkedList<TreeNode> stack = new LinkedList<>();
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                //"一头扎下去"
                stack.push(root);
                root = root.left;
            }
            //左--->根--->右
            TreeNode node = stack.pop();
            ans.add(node.val);
            root = node.right;
        }
        return ans;
    }


    public static void main(String[] args) {
        /**
         *             4
         *            / \
         *           3   7
         *          /   / \
         *         1   5   9
         *        / \       \
         *      -1  2       12
         *  温馨提示：二叉排序树的中序遍历结果是一个有序的集合
         */
        int[] arr = new int[]{4, 3, 7, 1, -1, 2, 9, 12, 5};
        System.out.println(Arrays.toString(inOrder(TreeNode.createBinarySearchTree(arr)).toArray()));
        System.out.println(Arrays.toString(inOrder2(TreeNode.createBinarySearchTree(arr)).toArray()));
    }

}
