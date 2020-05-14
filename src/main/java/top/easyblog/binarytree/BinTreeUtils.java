package top.easyblog.binarytree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author HuangXin
 * @since 2020/2/11 21:07
 */
public class BinTreeUtils {



    /**
     * <h4>二叉树的层序遍历</h4>
     * 由于其层级的关系，很明显要用到队列来辅助实现，主要是从左向右，自上而下，
     * 因此依次将二叉树的各节点入队，这样便可以保证输出的顺序是层序排列的。
     *
     * @param root 二叉树根节点
     * @return java.util.List
     */
    public List<List<Integer>> levelOrder(TreeNode<Integer> root) {
        if (root == null) {
            return null;
        }
        ArrayList<List<Integer>> list = new ArrayList<>();
        //使用队列将每一个结点入队，保证顺序
        LinkedList<TreeNode<Integer>> queue = new LinkedList<>();
        queue.offer(root);   //首先将根节点入队
        while (!queue.isEmpty()) {
            int count = queue.size();
            ArrayList<Integer> inner = new ArrayList<>();
            while (count > 0) {
                TreeNode<Integer> node = queue.poll();
                System.out.print(node.val + " ");
                inner.add(node.val);
                //下面这两个判断不可以交换位置
                //左子树不空，入队
                if (node.left != null) {
                    queue.offer(node.left);
                }
                //右子树不空，入队
                if (node.right != null) {
                    queue.offer(node.right);
                }
                count--;
            }
            list.add(inner);
        }
        return list;
    }


}
