package top.easyblog.binarytree;

import java.util.HashMap;

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


    private static HashMap<Object, Integer> inOrderMap;

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
        String rootCharacter;
        if (isPreOrder) {
            //前序遍历,order第一个元素就是根节点
            rootCharacter = order[0];
        } else {
            //后序遍历，order最后一个元素是根节点
            rootCharacter = order[order.length - 1];
        }

        int index = -1;
        for (int i = 0; i < inOrder.length; i++) {
            if (rootCharacter.equals(inOrder[i])) {
                index = i;
                break;
            }
        }
        //非法数据
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
            root.left = reConstructBinTree(lChildOrder, true, lChildInOrder);
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
            root.right = reConstructBinTree(rChildOrder, true, rChildInOrder);
        } else {
            root.right = reConstructBinTree(rChildOrder, false, rChildInOrder);
        }
        return root;
    }

    /**
     * 对上面的代码进行空间复杂度优化：实际上并不需要真正的切分原数组，只需要限定数组的范围就好了
     *
     * @param order
     * @param inOrder
     * @param isPreOrder 是否是通过前序遍历还原
     * @return
     */
    public static TreeNode reConstructBinTree(String[] order, String[] inOrder, boolean isPreOrder) {
        if (order == null || inOrder == null || inOrder.length == 0 || order.length != inOrder.length) {
            return null;
        }
        inOrderMap = new HashMap<>();
        for (int i = 0; i < inOrder.length; i++) {
            inOrderMap.put(inOrder[i], i);
        }
        return reBuild(order, 0, order.length - 1, inOrder, 0, order.length - 1, isPreOrder);
    }


    private static TreeNode reBuild(String[] order, int orderLeft, int orderRight, String[] inOrder, int inOrderLeft, int inOrderRight, boolean isPreOrder) {
        if (orderLeft > orderRight) {
            return null;
        }
        String rootCharacter;
        if (isPreOrder) {
            //前序遍历,order第一个元素就是根节点
            rootCharacter = order[orderLeft];
        } else {
            //后序遍历，order最后一个元素是根节点
            rootCharacter = order[order.length - 1];
        }
        //在中序遍历中寻找根节点的索引下标
        int index = inOrderMap.get(rootCharacter);
        //左子树结点的个数
        int leftSubTreeSize = index - inOrderLeft;
        //根节点
        TreeNode root = new TreeNode(Integer.parseInt(rootCharacter));
        /* 本算法的核心就是下面这幅图，也就是在已知的前序和中序中寻找到分别寻找到左子树个右子树的范围即可
         * ┌--------------------------------------------------------------------------------┐
         * |    orderLeft           orderRight                                              |
         * |      ||                 ||                                                     |
         * |      \/                 \/                                                     |
         * |先序： A [B C D] [E F G H K]                                                     |
         * |      /\   /\           /\                                                      |
         * |      ||   ||           ||                                                      |
         * |     root "left Child"  "right Child"                                           |
         * |            || Range          ||Range                                           |
         * |            ||                \/                                                 |
         * |            \/            [orderLeft+leftChild.length()+1,orderChild]           |
         * |         [orderLeft+1,orderLeft + leftChild.length()]                           |
         * └--------------------------------------------------------------------------------┘
         * ┌--------------------------------------------------------------------------------┐
         * |                     [index+1,orderRight]                                       |
         * |                      /\                                                        |
         * |                      || Range                                                  |
         * |    orderLeft        orderRight                                                 |
         * |      ||              ||                                                        |
         * |      \/              \/                                                        |
         * |中序：[B D C] A E H G K F                                                        |
         * |       /\    /\                                                                 |
         * |       ||    ||                                                                 |
         * |"left Child" root,index=4                                                       |
         * |       || Range                                                                 |
         * |       \/                                                                       |
         * |   [orderLeft,index-1]                                                          |
         * └--------------------------------------------------------------------------------┘
         */
        //递归构造左子树
        root.left = reBuild(order, orderLeft + 1, orderLeft + leftSubTreeSize, inOrder, inOrderLeft, index - 1, isPreOrder);
        //递归构造右子树
        root.right = reBuild(order, orderLeft + leftSubTreeSize + 1, orderRight, inOrder, index + 1, inOrderRight, isPreOrder);
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
        TreeNode root3 = reConstructBinTree(preOrder,inOrder,true);
        System.out.println("root1后序遍历：" + 二叉树后序遍历.postOrder2(root1));
        System.out.println("root2后序遍历：" + 二叉树后序遍历.postOrder2(root2));
        System.out.println("root4后序遍历：" + 二叉树后序遍历.postOrder2(root3));
    }

}
