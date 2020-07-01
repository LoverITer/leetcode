package top.easyblog.binarytree;

/**
 * @author ：huangxin
 * @modified ：
 * @since ：2020/07/01 01:11
 */
public class 镜像二叉树 {


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
     *
     * @param root
     * @return
     */
    public boolean isSymmetric(TreeNode root) {
        return isMirror(root, root);
    }

    private boolean isMirror(TreeNode left, TreeNode right) {
        //空树
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


}
