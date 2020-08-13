package binarytree;

/**
 * LeerCode 108. 将有序数组转换为二叉搜索树<br/>
 * 将一个按照升序排列的有序数组，转换为一棵高度平衡二叉搜索树。
 * <pre>
 * 示例:
 *
 * 给定有序数组: [-10,-3,0,5,9],
 *
 * 一个可能的答案是：[0,-3,9,-10,null,5]，它可以表示下面这个高度平衡二叉搜索树：
 *
 *       0
 *      / \
 *    -3   9
 *    /   /
 *  -10  5
 *  </pre>
 *
 * @author ：huangxin
 * @modified ：
 * @since ：2020/07/04 00:16
 */
public class 将有序数组转换为二叉搜索树 {

    /**
     * 总体思路：
     * 由于给定数组是升序的，要构建的是一颗BST树，那就相当于已知BST树的中序遍历结果恢复这颗BST树。
     * 并且题目还要求树的两边高度差不大于1，因此每次选择升序集合的中间值作为当前根节点即可。
     *
     * @param nums
     * @return
     */
    public static TreeNode sortedArrayToBST(int[] nums) {
        return build(nums, 0, nums.length - 1);
    }

    private static TreeNode build(int[] nums, int low, int high) {
        if (low > high) {
            return null;
        }
        int mid = low + ((high - low) >> 1);
        TreeNode root = new TreeNode(nums[mid]);
        root.left = build(nums, low, mid - 1);
        root.right = build(nums, mid + 1, high);
        return root;
    }


    public static void main(String[] args) {
        int[] arr = {-10, -3, 0, 5, 9};
        System.out.println(sortedArrayToBST(arr));
    }
}
