package top.easyblog.binarytree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * LeetCode 199. 二叉树的右视图<br/>
 * 给定一棵二叉树，想象自己站在它的右侧，按照从顶部到底部的顺序，返回从右侧所能看到的节点值。
 * <pre>
 * 示例:
 *
 * 输入: [1,2,3,null,5,null,4]
 * 输出: [1, 3, 4]
 * 解释:
 *
 *    1            <---
 *  /   \
 * 2     3         <---
 *  \     \
 *   5     4       <---
 * </pre>
 *
 * @author ：huangxin
 * @modified ：
 * @see 二叉树层序遍历
 * @since ：2020/07/04 11:24
 */
public class 二叉树的右视图 {


    /**
     * 总体思路：层序遍历，选择每一层遍历的最后一个结点的值
     */
    public static List<Integer> rightSideView(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            //遍历二叉树的每一层
            while (levelSize > 0) {
                TreeNode node = queue.poll();
                if (levelSize == 1) {
                    ans.add(node.val);
                }
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
                levelSize--;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] arr = {5, 3, 6, 1, 8, 0, 1};
        System.out.println(rightSideView(TreeNode.createBinarySearchTree(arr)));
    }

}
