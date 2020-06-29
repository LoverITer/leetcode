package top.easyblog.binarytree;

/**
 * +---------------------+
 * |                     |
 * |                     |
 * |                     |
 * +---------------------+
 * @author ：huangxin
 * @modified ：
 * @since ：2020/06/21 22:46
 */
public class 二叉树中的最大路径和 {

    private static int val = Integer.MIN_VALUE;

    /**
     * 寻找二叉树中结点和最大的一条路径，这个路径不一定从根节点开始，比如：
     * <pre>
     *    -10
     *    / \
     *   9  20
     *     /  \
     *    15   7
     *
     *  最大的路径和是：15+20+7=42
     * </pre>
     *  解决思路：参考二叉树的后续遍历，从左子树的叶子节点开始，如果一个结点小于0 直接跳过
     * @param root
     * @return
     */
    public static int maxPathSum(TreeNode root) {
        dfs(root);
        return val;
    }

    private static int dfs(TreeNode node) {
        if (node == null) {
            return 0;
        }
        //左分支的最大值
        int leftMax = Math.max(dfs(node.left), 0);
        //右分支的最大值
        int rightMax = Math.max(dfs(node.right), 0);
        //更新全局最大值
        val = Math.max(val, node.val + leftMax + rightMax);
        return node.val + Math.max(leftMax, rightMax);
    }

    public static void main(String[] args) {
        System.out.println(maxPathSum(TreeNode.createBinarySearchTree(new int[]{4, 2, 6, 7, -9, 3})));
    }

}
