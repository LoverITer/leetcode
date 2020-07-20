package top.easyblog.binarytree;

/**
 * 给定二叉搜索树（BST）的根节点和一个值。 你需要在BST中找到节点值等于给定值的节点。
 * 返回以该节点为根的子树。 如果节点不存在，则返回 NULL。
 *
 * @author ：huangxin
 * @modified ：
 * @since ：2020/07/12 09:43
 */
public class 二叉搜索树中的搜索 {

    /**
     * 在BST中查找关键字val,如果找到返回结点引用，没找到返回null
     *
     * @param root 根节点
     * @param val  关键字key
     * @return
     */
    public TreeNode searchBST(TreeNode root, int val) {
        if (root == null) {
            return null;
        }
        if (root.val == val) {
            //找到了，返回结点引用
            return root;
        } else if (root.val < val) {
            //当前结点比关键字的值小，就向右搜索
            return searchBST(root.right, val);
        } else {
            //当前结点比关键字的值大，就向左搜索
            return searchBST(root.left, val);
        }
    }

}
