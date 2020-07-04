package top.easyblog.binarytree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 104. 二叉树的最大深度
 * 给定一个二叉树，找出其最大深度。
 * <p>
 * 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
 * 示例：
 * <pre>
 *
 * 给定二叉树 [3,9,20,null,null,15,7]，
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回它的最大深度 3 。
 * </pre>
 * @author ：huangxin
 * @modified ：
 * @since ：2020/07/01 11:24
 */
public class 二叉树的最大深度 {

    /**
     * 主题思路：使用二叉树后序遍历分别统计左右子树的高度，然后比较返回值，返回最大的一个即可
     *
     * @param root
     * @return
     */
    public static int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftDepth, rightDepth;
        /*分别计算出左右子树的高度，然后比较得出最大的一侧+1返回即可*/
        leftDepth = maxDepth(root.left);
        rightDepth = maxDepth(root.right);
        return leftDepth > rightDepth ? leftDepth + 1 : rightDepth + 1;
    }

    /**
     * 非递归版 -效率没有递归版的高，采用宽度优先遍历BFS，每进入一层高度high+1,之后返回该值
     *
     * @param root
     * @return
     */
    public static int depth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int high = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            high++;
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.remove();
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
        }
        return high;
    }

    public static void main(String[] args) {
        /*BST:
                       5
                      / \
                     3   6
                    / \   \
                   2   4   9
                  /       / \
                 1       8   10
                              \
                              11
         */
        int[] arr = {5, 3, 2, 1, 6, 9, 4, 8, 10, 11};
        TreeNode root = TreeNode.createBinarySearchTree(arr);
        System.out.println(depth(root));
    }
}
