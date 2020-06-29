package top.easyblog.binarytree;

/**
 * 二叉树有4种遍历方式：先序（前序）遍历、中序遍历、后序遍历以及层序遍历
 * 结论1：已知前序遍历和中序遍历可以唯一的确定一颗二叉树。
 * 结论2：已知后序遍历和中序遍历可以唯一的确定一颗二叉树。
 * 结论3：已知前序遍历和后序遍历无法唯一的确定一颗二叉树
 *
 * @author ：huangxin
 * @modified ：
 * @since ：2020/06/28 20:52
 */
public class 还原二叉树 {

    /**
     * 根据前序遍历+中序遍历恢复一颗二叉树
     *
     * @param preOrder 前序遍历
     * @param inOrder  中序遍历
     * @return 还原的二叉树
     */
    public static TreeNode reConstructBinTree(String[] preOrder, String[] inOrder) {
        return reConstructBinTree(preOrder, true, inOrder);
    }

    /**
     * 根据前序遍历+中序遍历或者后序+中序遍历恢复一颗二叉树
     *
     * @param order      前序遍历或后序遍历
     * @param isPreOrder order是否是前序遍历
     * @param inOrder    中序遍历
     * @return 还原的二叉树
     */
    public static TreeNode reConstructBinTree(String[] order, boolean isPreOrder, String[] inOrder) {
        if (order == null || inOrder == null || inOrder.length == 0 || order.length != inOrder.length) {
            return null;
        }
        int index = -1;
        String rootCharacter;
        if (isPreOrder) {
            //前序遍历,order第一个元素就是根节点
            rootCharacter = order[0];
        } else {
            //后序遍历，order最后一个元素是根节点
            rootCharacter = order[order.length - 1];
        }

        for (int i = 0; i < inOrder.length; i++) {
            if (inOrder[i].equals(rootCharacter)) {
                index = i;
                break;
            }
        }
        if (index == -1) {
            return null;
        }
        //根节点
        TreeNode root = new TreeNode(Integer.parseInt(rootCharacter));
        //处理左子树
        String[] lChildOrder = new String[index];
        if (isPreOrder) {
            //order第一个元素是根节点，已经处理过了，这里需要跳过，从order index =1开始往后数index个元素就是左子树的元素
            System.arraycopy(order, 1, lChildOrder, 0, index);
        } else {
            //此时order第一个元素往后数index个元素就是左子树的元素
            System.arraycopy(order, 0, lChildOrder, 0, index);
        }
        String[] lChildInOrder = new String[index];
        System.arraycopy(inOrder, 0, lChildInOrder, 0, index);
        if (isPreOrder) {
            root.left = reConstructBinTree(lChildOrder, lChildInOrder);
        } else {
            root.left = reConstructBinTree(lChildOrder, false, lChildOrder);
        }
        //处理右子树
        String[] rChildOrder = new String[inOrder.length - 1 - index];
        if (isPreOrder) {
            System.arraycopy(order, index + 1, rChildOrder, 0, inOrder.length - 1 - index);
        } else {
            System.arraycopy(order, index, rChildOrder, 0, inOrder.length - 1 - index);
        }
        String[] rChildInOrder = new String[inOrder.length - 1 - index];
        System.arraycopy(inOrder, index + 1, rChildInOrder, 0, inOrder.length - 1 - index);
        if (isPreOrder) {
            root.right = reConstructBinTree(rChildOrder, rChildInOrder);
        } else {
            root.right = reConstructBinTree(rChildOrder, false, rChildInOrder);
        }
        return root;
    }

    public static void main(String[] args) {

        /**
         *             4
         *            / \
         *           3   7
         *          /   / \
         *         1   5   9
         *        / \       \
         *      -1  2       12
         */
        int[] arr = new int[]{4, 3, 7, 1, -1, 2, 9, 12, 5};
        TreeNode root = TreeNode.createBinarySearchTree(arr);

        String[] preOrder = {"4", "3", "1", "-1", "2", "7", "5", "9", "12"}; //先序
        String[] inOrder = {"-1", "1", "2", "3", "4", "5", "7", "9", "12"};   //中序
        String[] postOrder = {"-1", "2", "1", "3", "5", "12", "9", "7", "4"};  //后序

        TreeNode root1 = reConstructBinTree(preOrder, inOrder);
        TreeNode root2 = reConstructBinTree(postOrder, false, inOrder);
        System.out.println("root1后序遍历：" + 二叉树后序遍历.postOrder2(root1));
        System.out.println("root2后序遍历：" + 二叉树后序遍历.postOrder2(root2));
    }

}
