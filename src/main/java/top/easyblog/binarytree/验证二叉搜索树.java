package top.easyblog.binarytree;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * 给定一个二叉树，判断其是否是一个有效的二叉搜索树。
 * <p>
 * 假设一个二叉搜索树具有如下特征：
 * <p>
 * 节点的左子树只包含小于当前节点的数。
 * 节点的右子树只包含大于当前节点的数。
 * 所有左子树和右子树自身必须也是二叉搜索树。
 * 示例 2:
 * <pre>
 *
 * 输入:
 *     5
 *    / \
 *   1   4
 *      / \
 *     3   6
 * 输出: false
 * 解释: 输入为: [5,1,4,null,null,3,6]。
 *      根节点的值为 5 ，但是其右子节点值为 4 。
 * </pre>
 *
 * @author ：huangxin
 * @modified ：
 * @since ：2020/07/01 15:31
 */
public class 验证二叉搜索树 {

    /**
     * 利用BST中序遍历是严格升序的特征：
     * 中序遍历一棵树，如果发现后一个数小于等于前一个数的时候直接返回false
     *
     * @param root
     * @return
     */
    public static boolean isValidBST(TreeNode root) {
        if (root == null) {
            //BST的定义：空树是合法的BST
            return true;
        }
        LinkedList<TreeNode> stack = new LinkedList<>();
        long preVal = Long.MIN_VALUE;
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            TreeNode node = stack.pop();
            //如果后一个数小于或等于前一个数这里直接返回false
            if (node.val <= preVal) {
                return false;
            }
            preVal = node.val;
            root = node.right;
        }
        return true;
    }

    /**
     * 分治法验证是否是二叉搜索树
     *
     * @param root
     * @return
     */
    public static boolean isValidBST2(TreeNode root) {
        return isValid(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private static boolean isValid(TreeNode root, long min, long max) {
        if (root == null) {
            return true;
        }
        int val = root.val;
        if (min >= val) return false;
        if (max <= val) return false;
        if (!isValid(root.right, val, max)) return false;
        if (!isValid(root.left, min, val)) return false;
        return true;
    }

    public static void main(String[] args) {
        //[5,1,4,null,null,3,6]
        String[] arr = {"10", "5", "15", "#", "#", "6", "2"};
        TreeNode root = TreeNode.createTree(Arrays.asList(arr));
        System.out.println(isValidBST2(root));
    }

}
