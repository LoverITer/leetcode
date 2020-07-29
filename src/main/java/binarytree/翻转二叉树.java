package binarytree;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 翻转一颗二叉树，使其与原二叉树镜像对称
 * 示例：
 * <p>
 * 输入：
 * <p>
 * 4
 * /   \
 * 2     7
 * / \   / \
 * 1   3 6   9
 * 输出：
 * <p>
 * 4
 * /   \
 * 7     2
 * / \   / \
 * 9   6 3   1
 * <p>
 * 两个二叉树镜像对称。这道题实际就是对二叉树4中遍历方式的应用，熟悉二叉树的遍历方式的话，这道题不难
 *
 * @author ：huangxin
 * @modified ：
 * @since ：2020/07/06 11:17
 */
public class 翻转二叉树 {

    /**
     * 思路一：利用前序遍历，从根节点开始交换每个结点的左右子结点即可
     *
     * @param root
     * @return
     */
    public TreeNode invertTree(TreeNode root) {
        if (root != null) {
            //自顶向下交换左右结点
            TreeNode t = root.left;
            root.left = root.right;
            root.right = t;
            invertTree(root.left);
            invertTree(root.right);
        }
        return root;
    }

    /**
     * 思路二：和思路一类似，这次借助后续遍历，自底向上交换一个节点的左右子结点
     */
    public TreeNode invertTree2(TreeNode root) {
        if (root != null) {
            invertTree2(root.left);
            invertTree2(root.right);
            //自底向上交换左右结点
            TreeNode t = root.left;
            root.left = root.right;
            root.right = t;
        }
        return root;
    }

    /**
     * 思路三：和思路一类似，这次借助中序遍历
     */
    public TreeNode invertTree3(TreeNode root) {
        if (root != null) {
            invertTree3(root.left);
            //自底向上交换左右结点
            TreeNode t = root.left;
            root.left = root.right;
            root.right = t;
            invertTree3(root.right);
        }
        return root;
    }

    /**
     * 思路四：借助层序序遍历
     */
    public TreeNode invertTree4(TreeNode root) {
        if (root == null) {
            return null;
        }
        //a queue
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            TreeNode t=node.left;
            node.left=node.right;
            node.right=t;
            if (node.left != null) {
                queue.offer(node);
            }
            if (node.right != null) {
                queue.offer(node);
            }
        }
        return root;
    }


}
