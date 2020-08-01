package binarytree;

/**
 * LeetCode 543. 二叉树的直径
 * 给定一棵二叉树，你需要计算它的直径长度。
 * 一棵二叉树的直径长度是任意两个结点路径长度中的最大值。这条路径可能穿过也可能不穿过根结点。
 * <pre>
 * 示例 :
 * 给定二叉树
 *
 *           1
 *          / \
 *         2   3
 *        / \
 *       4   5
 * 返回 3, 它的长度是路径 [4,2,1,3] 或者 [5,2,1,3]。注意：两结点之间的路径长度是以它们之间边的数目表示。
 * </pre>
 * @author ：huangxin
 * @modified ：
 * @since ：2020/07/04 11:58
 */
public class 二叉树的直径 {

    private int max=0;

    /**
     *
     * @param root
     * @return
     */
    public int diameterOfBinaryTree(TreeNode root) {
        if(root==null){
            return 0;
        }
        dfs(root);
        return max;
    }

    private int dfs(TreeNode root){
        if(root.left==null && root.right==null){
            return 0;
        }
        //分别求出左右子树的最大路径值
        int leftSize=root.left==null?0:dfs(root.left)+1;
        int rightSize=root.right==null?0:dfs(root.right)+1;
        max=Math.max(max,leftSize+rightSize);  //左右子树路径最大值相加一定就是全局最大值
        return Math.max(leftSize,rightSize);   //左子树或右子树最大值
    }

}
