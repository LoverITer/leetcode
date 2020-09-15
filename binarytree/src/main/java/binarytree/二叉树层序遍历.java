package binarytree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * <h5>二叉树的层序遍历</h5>
 * 二叉树层序遍历就是按层来比那里一颗二叉树，例如：
 * <pre>
 *                       5
 *                     /   \
 *                    3     7
 *                  /  \   /  \
 *                 8    1 12   9
 *  层序遍历结果：[[5],[3,7],[8,1,12,9]]
 *  </pre>
 *
 * <p>由于其层级的关系，我们可以使用队列{@link java.util.Queue}来辅助实现，每次遍历到一个结点
 * 除了访问一下这个结点的值以外，还需要判断一下这个节点的左右结点如果不为{@code null}就将其加入到队列中</p>
 *
 * @author ：huangxin
 * @modified ：
 * @since ：2020/06/18 08:49
 */
public class 二叉树层序遍历 {

    /**
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

        List<List<Integer>> lists = levelOrder(tree);
        System.out.println(lists);


    }

}
