package binarytree;

/**
 * 输入一棵二叉搜索树，将该二叉搜索树转换成一个排序的循环双向链表。
 * 要求不能创建任何新的节点，只能调整树中节点指针的指向。
 * <p>
 * 为了让您更好地理解问题，以下面的二叉搜索树为例：
 * <pre>
 *          4
 *        /  \
 *       2    5
 *      / \
 *     1   3
 * </pre>
 * 转换之后的双链表为：
 * <img src="https://assets.leetcode.com/uploads/2018/10/12/bstdllreturndll.png">
 *
 * @author ：huangxin
 * @modified ：
 * @since ：2020/07/06 15:41
 */
public class 将二叉搜索树转换为双链表 {

    private TreeNode head = null;
    private TreeNode tail = null;

    /**
     * 基本思路是利用中序遍历的有序性在遍历二叉树为时候将节点的right结点指向下一个结点，
     *
     * @param root BST根节点
     * @return
     */
    public TreeNode treeToDoublyList(TreeNode root) {
        if (root == null) {
            return null;
        }
        treeToDoublyList(root.left);
        if (head == null) {
            //头结点
            head = root;
            tail = head;
        } else {
            tail.right = root;
            root.left = tail;
            tail = root;
        }
        treeToDoublyList(root.right);
        head.left = tail;
        tail.right = head;
        return head;
    }

}
