package _58同城;

import binarytree.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * @author ：huangxin
 * @modified ：
 * @since ：2020/09/14 22:22
 */
public class 二叉树的层序遍历 {

     public static List<List<Integer>> levelOrder(TreeNode root){
         List<List<Integer>> ans=new ArrayList<>();
         if(root==null){
             return ans;
         }
         Queue<TreeNode> queue=new ArrayDeque<>();
         queue.offer(root);
         while(!queue.isEmpty()){
             int size=queue.size();
             List<Integer> list = new ArrayList<>();
             //分层
             for(int i=0;i<size;i++){
                 TreeNode node = queue.poll();
                 list.add(node.val);
                 if(node.left!=null){
                     queue.offer(node.left);
                 }
                 if(node.right!=null){
                     queue.offer(node.right);
                 }
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
