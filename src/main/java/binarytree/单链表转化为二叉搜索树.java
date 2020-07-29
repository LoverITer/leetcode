package binarytree;

import binarytree.TreeNode;
import linklist.ListNode;

/**
 * 给定一个单链表，其中的元素按升序排序，将其转换为高度平衡的二叉搜索树。
 * <p>
 * 本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1。
 * <pre>
 * 示例:
 *
 * 给定的有序链表： [-10, -3, 0, 5, 9],
 *
 * 一个可能的答案是：[0, -3, 9, -10, null, 5], 它可以表示下面这个高度平衡二叉搜索树：
 *
 *       0
 *      / \
 *    -3   9
 *    /   /
 *  -10  5
 * </pre>
 *
 * @author ：huangxin
 * @modified ：
 * @since ：2020/07/15 17:51
 */
public class 单链表转化为二叉搜索树 {
    /**
     * 用两个指针，一快一慢，快的每次走两步，慢的每次走一步，这样当快指针遍历结束时，慢指针指向的也就是链表的中间位置。
     * 这时候把中间位置的结点的值作为二叉搜索树当前结点的值
     *
     * @param head 链表头结点
     * @return
     */
    public TreeNode sortedListToBST(ListNode head) {
        if (head == null) {
            return null;
        }
        if (head.next == null) {
            return new TreeNode(head.val);
        }
        ListNode prev = null, slower = head, faster = head;
        //寻找当前链表的中间节点
        while (faster != null && faster.next != null) {
            prev = slower;
            slower = prev.next;
            faster = faster.next.next;
        }
        //断开链表
        prev.next=null;
        TreeNode root=new TreeNode(slower.val);
        root.left=sortedListToBST(head);
        root.right=sortedListToBST(slower.next);
        return root;
    }

    public static void main(String[] args) {

    }

}
