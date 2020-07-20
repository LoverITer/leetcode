package top.easyblog.binarytree;

/**
 * LeetCode 114. 二叉树展开为链表
 * 给定一个二叉树，原地将它展开为一个单链表。
 * <pre>
 * 例如，给定二叉树
 *
 *     1
 *    / \
 *   2   5
 *  / \   \
 * 3   4   6
 * 将其展开为：
 *
 * 1
 *  \
 *   2
 *    \
 *     3
 *      \
 *       4
 *        \
 *         5
 *          \
 *           6
 * </pre>
 *
 * @author ：huangxin
 * @modified ：
 * @since ：2020/07/06 15:20
 */
public class 二叉树展开为单链表 {

    /**
     * <pre>
     *     1
     *    / \
     *   2   5
     *  / \   \
     * 3   4   6
     * 程序运行流程图：
     * 1.1 将1的右子树接到左子树右叶子结点上
     *     1
     *    /
     *   2
     *  / \
     * 3   4
     *      \
     *       5
     *        \
     *         6
     * 1.2 将1的左子树接到1的右子树上
     *
     *     1
     *      \
     *       2
     *      / \
     *     3   4
     *          \
     *           5
     *            \
     *             6
     *  2.1 将2的右子树接到左子树的右叶子结点
     *      1
     *       \
     *        2
     *       /
     *      3
     *       \
     *        4
     *         \
     *          5
     *           \
     *            6
     *  2.2 将2的左子树接到器右子树
     *      1
     *       \
     *        2
     *        \
     *         3
     *          \
     *           4
     *            \
     *             5
     *              \
     *               6
     *  </pre>
     *
     * @param root
     */
    public static void flatten(TreeNode root) {
        while (root != null) {
            if (root.left != null) {
                TreeNode t = root.left;
                //找到左子树的右叶子节点
                while (t.right != null) {
                    t = t.right;
                }
                //把原来的右子树接到左子树下
                t.right = root.right;
                root.right = root.left;
                //原来的左子树一定要赋空
                root.left = null;
                root = root.right;
            } else {
                root = root.right;
            }
        }
    }

}
