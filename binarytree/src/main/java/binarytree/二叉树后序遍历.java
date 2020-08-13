package binarytree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**二叉树的后序遍历值的是在比那里一颗二叉树的时候首先访问其左子树，在访问其右子树，最后访问根节点
 * <pre>
 *     1
 *   /  \
 *  6    2
 *     /  \
 *    3    9
 *
 * 输出: [6,3,9,2,1]
 * </pre>
 * @see 二叉树中序遍历
 * @see 二叉树先序遍历
 * @author ：huangxin
 * @modified ：
 * @since ：2020/06/27 20:56
 */
public class 二叉树后序遍历 {

    private static List<Integer> ans = new ArrayList<>();

    /**
     * 二叉树后续遍历——递归版
     *
     * @param root
     * @return
     */
    public static List<Integer> postOrder(TreeNode root) {
        if (root != null) {
            postOrder(root.left);
            postOrder(root.right);
            ans.add(root.val);
        }
        return ans;
    }

    public static List<Integer> postOrder2(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }
        TreeNode lastVisit = null;
        LinkedList<TreeNode> stack = new LinkedList<>();
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            TreeNode node = stack.peek();
            assert node != null;
            if (node.right == null || node.right == lastVisit) {
                stack.pop();
                ans.add(node.val);
                //标记这个结点已经被遍历过了
                lastVisit = node;
            } else {
                root = node.right;
            }
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
         */
        int[] arr = new int[]{4, 3, 7, 1, -1, 2, 9, 12, 5};
        System.out.println(Arrays.toString(postOrder(TreeNode.createBinarySearchTree(arr)).toArray()));
        System.out.println(Arrays.toString(postOrder2(TreeNode.createBinarySearchTree(arr)).toArray()));

    }

}
