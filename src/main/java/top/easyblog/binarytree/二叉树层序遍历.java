package top.easyblog.binarytree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 二叉树层序遍历 ：BFS ，Queue
 *
 * @author ：huangxin
 * @modified ：
 * @since ：2020/06/18 08:49
 */
public class 二叉树层序遍历 {

    /**
     * <h4>二叉树的层序遍历</h4>
     * 由于其层级的关系，很明显要用到队列来辅助实现，主要是从左向右，自上而下，
     * 因此依次将二叉树的各节点入队，这样便可以保证输出的顺序是层序排列的。
     * <pre>
     *                       5
     *                     /   \
     *                    3     7
     *                  /  \   /  \
     *                 8    1 12   9
     *  </pre>
     * 层序遍历结果：[[5],[3,7],[8,1,12,9]]
     *
     * @param root 二叉树根节点
     * @return java.util.List 层序遍历结果
     */
    public static List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null) {
            return null;
        }
        ArrayList<List<Integer>> ans = new ArrayList<>();
        //使用队列将每一个结点入队，保证顺序
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.offer(root);   //首先将根节点入队
        while (!queue.isEmpty()) {
            int count = queue.size();
            ArrayList<Integer> list = new ArrayList<>();
            while (count > 0) {
                //队首元素出队
                TreeNode node = queue.poll();
                //下面这两个判断不可以交换位置
                //左子树不空，入队
                if (node.left != null) {
                    queue.offer(node.left);
                }
                //右子树不空，入队
                if (node.right != null) {
                    queue.offer(node.right);
                }
                list.add(node.val);
                count--;
            }
            ans.add(list);
        }
        return ans;
    }

    public static void main(String[] args) {
        /**
         *             4
         *            / \
         *           3   7
         *          /   / \
         *         1   5   9
         *        / \       \
         *       -1  2       12
         */
        int[] arr=new int[]{4,3,7,1,-1,2,9,12,5};
        TreeNode tree = TreeNode.createBinarySearchTree(arr);

        System.out.println(levelOrder(tree));


    }

}
