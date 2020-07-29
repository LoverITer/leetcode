package binarytree;

/**
 * 给定一个二叉树，检查它是否是镜像对称的。
 *
 *
 * <pre>
 * 例如，二叉树 [1,2,2,3,4,4,3] 是对称的。
 *
 *     1
 *    / \
 *   2   2
 *  / \ / \
 * 3  4 4  3
 *
 *
 * 但是下面这个 [1,2,2,null,3,null,3] 则不是镜像对称的:
 *
 *     1
 *    / \
 *   2   2
 *    \   \
 *    3    3
 * </pre>
 * @author ：huangxin
 * @modified ：
 * @since ：2020/07/01 01:11
 */
public class 镜像二叉树 {


    /**
     * 设置两个指针{@code left}和{@code  right},从根节点开始,left和right同步移动，
     * 但是left和right的移动方向相反。即left右移，right左移；left左移，right右移。
     * 在移动的过程中检查left，right，如果其中一个为{@code null}了，就直接返回{@code false}，
     * 否则检查left和right的值是否相等,相等继续向下移动，不相等直接返回{@code false}。
     * <pre>
     *  left->   1  <-right
     *          / \
     *         2   2
     *        / \ / \
     *       3  4 4  3
     * --------------------------------------------------------
     * 递归调用isMirror(left.left, right.right) ：
     *           1
     *          / \
     * left->  2   2 <-right   left.val==right.val  继续向下
     *        / \ / \
     *       3  4 4  3
     * --------------------------------------------------------
     *             1
     *            / \
     *           2   2
     *          / \ / \
     *left->   3  4 4  3   <-right   left.val==right.val  继续向下
     * ---------------------------------------------------------
     *             1
     *            / \
     *           2   2
     *          / \ / \
     *         3  4 4  3
     *  left->           <-right   left==null && right==null  返回true ，这一次递归调用结束
     * </pre>
     *
     * @param root
     * @return
     */
    public static boolean isSymmetric(TreeNode root) {
        return isMirror(root, root);
    }

    private static boolean isMirror(TreeNode left, TreeNode right) {
        //空树或者left right同时遍历到空
        if (left == null && right == null) {
            return true;
        }
        //瘸腿树
        if (left == null || right == null) {
            return false;
        }
        //一个往左一个往有，相互交叉
        return left.val == right.val && isMirror(left.left, right.right) && isMirror(left.right, right.left);
    }


    public static void main(String[] args) {
        int[] arr = {4, 3, 6, 1, 7, 9,};
        System.out.println(isSymmetric(TreeNode.createBinarySearchTree(arr)));
    }

}
