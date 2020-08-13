package binarytree;

import java.util.*;

/**
 * LeetCode 103. 二叉树的锯齿形层次遍历
 * 给定一个二叉树，返回其节点值的锯齿形层次遍历。（即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。
 * <pre>
 * 例如：
 * 给定二叉树 [3,9,20,null,null,15,7],
 * <pre>
 * 3
 * / \
 * 9  20
 * /  \
 * 15   7
 * 返回锯齿形层次遍历如下：
 * <pre>
 * [
 * [3],
 * [20,9],
 * [15,7]
 * ]
 *
 * @see 二叉树层序遍历
 * @author ：huangxin
 * @modified ：
 * @since ：2020/07/06 12:32
 */
public class 二叉树的锯齿形层次遍历 {

    /**
     * 依据二叉树层序遍历的思路，使用一个控制变量{@code needReverse}来判断当前层的遍历结果是否需要
     * 翻转，如果需要则使用{@code Collections.reverse(List<>)}来翻转；
     *
     * @param root
     * @return
     */
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        //表示是否需要翻转一层遍历结果
        boolean needReverse = false;
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            List<Integer> list = new ArrayList<>();
            while (levelSize-- > 0) {
                TreeNode node = queue.poll();
                list.add(node.val);
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            if (needReverse) {
                Collections.reverse(list);
                //下一次无需翻转
                needReverse = false;
            } else {
                //下一次需要翻转
                needReverse = true;
            }
            ans.add(list);
        }
        return ans;
    }

}
