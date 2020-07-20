package top.easyblog.binarytree;

/**
 * 给定二叉搜索树（BST）的根节点和要插入树中的值，将值插入二叉搜索树。
 * 返回插入后二叉搜索树的根节点。 保证原始二叉搜索树中不存在新值。
 * <p>
 * 注意，可能存在多种有效的插入方式，只要树在插入后仍保持为二叉搜索树即可。
 * 你可以返回任意有效的结果。
 *
 * @author ：huangxin
 * @modified ：
 * @since ：2020/07/12 09:49
 */
public class 二叉搜索树中的插入操作 {

    /**
     * 向二叉树搜索树中插入新值val
     *
     * @param root BST根节点
     * @param val  新值
     * @return
     */
    public static TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null) {
            //BST可能为空，此时直接返回新值为根节点
            return new TreeNode(val);
        }
        //BST不为空
        TreeNode currRoot = root;   //当前根节点
        TreeNode parent = currRoot;   //待插入新值的父节点
        while (currRoot != null) {
            //寻找到待插入新值的父节点
            parent = currRoot;
            if (currRoot.val < val) {
                currRoot = currRoot.right;
            } else if (currRoot.val > val) {
                currRoot = currRoot.left;
            } else {
                throw new RuntimeException("can not insert same value in to a BST:" + val);
            }
        }
        //插入新值到父节点parent下
        if (parent.val < val) {
            parent.right = new TreeNode(val);
        } else if (parent.val > val) {
            parent.left = new TreeNode(val);
        }
        return root;
    }

}
