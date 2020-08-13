package binarytree;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 给定一个二叉搜索树，编写一个函数 kthSmallest 来查找其中第 k 个最小的元素。
 *
 * 说明：
 * 你可以假设 k 总是有效的，1 ≤ k ≤ 二叉搜索树元素个数。
 * <pre>
 *  输入: root = [5,3,6,2,4,null,null,1], k = 3
 *        5
 *       / \
 *      3   6
 *     / \
 *    2   4
 *   /
 *  1
 * 输出: 3
 * </pre>
 * @see 二叉树中序遍历
 * @author ：huangxin
 * @modified ：
 * @since ：2020/08/01 14:12
 */
public class 二叉搜索树中第K小的元素 {

    /**
     * 利用二叉搜索树的特性：中序遍历是有序的，因此中序遍历BST，并且使用一个计数器当和k相等的时候返回值
     * @param root
     * @param k
     * @return
     */
    public int kthSmallest(TreeNode root, int k) {
        if(root==null){
            return 0;
        }
        Deque<TreeNode> stack=new ArrayDeque<>();
        int count=0;
        while(!stack.isEmpty()||root!=null){
            while(root!=null){
                stack.push(root);
                root=root.left;
            }
            TreeNode node=stack.pop();
            if(++count==k){
                return node.val;
            }
            root=node.right;
        }
        return 0;
    }

}
