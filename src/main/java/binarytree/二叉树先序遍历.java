package binarytree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/** 二叉树的先序遍历指的是这种遍历方式：对一颗二叉树遍历时先访问根节点，再访问左结点，最后访问右节点
 * <pre>
 *     1
 *   /  \
 *  6    2
 *     /  \
 *    3    9
 *
 * 输出: [1,6,2,3,9]
 * </pre>
 * @see 二叉树中序遍历
 * @see 二叉树后序遍历
 * @author ：huangxin
 * @modified ：
 * @since ：2020/06/21 23:12
 */
public class 二叉树先序遍历 {

    private static List<Integer> ans = new ArrayList<>();

    /**
     * 二叉树先序遍历递归版
     *
     * @param root
     * @return
     */
    public static List<Integer> preOrder(TreeNode root) {
        if (root != null) {
            ans.add(root.val);
            preOrder(root.left);
            preOrder(root.right);
        }
        return ans;
    }

    /**
     * 非递归前序遍历二叉树
     *
     * @param root
     * @return
     */
    public static List<Integer> preOrder2(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }
        LinkedList<TreeNode> stack = new LinkedList<>();
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                //前序遍历，这里直接打印结点即可
                ans.add(root.val);
                stack.push(root);
                root = root.left;
            }
            //叶子节点出栈
            TreeNode node = stack.pop();
            //访问叶子节点的右子女
            root = node.right;
        }
        return ans;
    }

    public static void main(String[] args) {
        /*
         *             4
         *            / \
         *           3   7
         *          /   / \
         *         1   5   9
         *        / \       \
         *      -1  2       12
         */
        int[] arr = new int[]{4, 3, 7, 1, -1, 2, 9, 12, 5};
        System.out.println("递归遍历结果：" + Arrays.toString(preOrder(TreeNode.createBinarySearchTree(arr)).toArray()));
        System.out.println("非递归遍历结果：" + Arrays.toString(preOrder2(TreeNode.createBinarySearchTree(arr)).toArray()));
    }

}
