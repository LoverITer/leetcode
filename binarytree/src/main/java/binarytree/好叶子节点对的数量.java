package binarytree;

/**
 * 给你二叉树的根节点 root 和一个整数 distance 。
 * <p>
 * 如果二叉树中两个 叶 节点之间的 最短路径长度 小于或者等于 distance ，那它们就可以构成一组 好叶子节点对 。
 * <p>
 * 返回树中 好叶子节点对的数量 。
 *
 * @author ：huangxin
 * @modified ：
 * @since ：2020/07/31 00:37
 */
public class 好叶子节点对的数量 {

    private static int ans = 0;

    public static int countPairs(TreeNode root, int distance) {
        dfs(root, distance);
        return ans;
    }

    private static int[] dfs(TreeNode root, int distance) {
        if (root == null) {
            return new int[distance + 1];
        }
        int[] count = new int[distance + 1];
        if (root.left == null && root.right == null) {
            count[0] = 1;
            return count;
        }
        int[] leftCount = dfs(root.left, distance);
        int[] rightCount = dfs(root.right, distance);
        //计算组合数
        for (int i = 0; i <= distance; i++) {
            for (int j = 0; i + j + 2 <= distance; j++) {
                ans += leftCount[i] * rightCount[j];
            }
        }
        //向上层返回,距离+1
        for (int i = 1; i <= distance; i++) {
            count[i] = leftCount[i - 1] + rightCount[i - 1];
        }
        return count;
    }

    public static void main(String[] args) {

    }

}
