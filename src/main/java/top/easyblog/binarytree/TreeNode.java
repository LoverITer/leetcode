package top.easyblog.binarytree;

/**
 * 二叉树结点类型
 *
 * @author ：huangxin
 * @modified ：
 * @since ：2020/05/14 11:49
 */
public class TreeNode<T extends Comparable<T>> {

    T val;
    TreeNode<T> left;
    TreeNode<T> right;

    public TreeNode(T val) {
        this.val = val;
        this.left = null;
        this.right = null;
    }


    /**
     * <h4>创建二叉查找树</h4>
     * 根据参数传入的数组构建一颗二叉查找树,理论上一颗二叉查找树具有以下特性
     * <ul>
     *     <li>任意节点的左子树如果不为空，则左子树小于父节点的值</li>
     *     <li>任意节点的有子树如果不为空，则右子树大于父节点的值</li>
     *     <li>任意节点的左子树和右子树都是二叉查找树</li>
     *     <li>二叉查找树中没有两个节点的值相等</li>
     * </ul>
     *
     * @param array
     * @return
     */
    public static <T extends Comparable<T>> TreeNode<T> createBinSearchTree(T[] array) {
        if (array == null) {
            return null;
        }
        TreeNode<T> root = null;
        for (T t : array) {
            //新节点
            TreeNode<T> node = new TreeNode<>(t);
            if (root == null) {
                //根节点为空
                root = node;
            } else {
                //根节点不为空
                TreeNode<T> curr = root;
                TreeNode<T> parent = curr;
                while (curr != null) {
                    //寻找插入点的过程，parent就是待插入点的父节点
                    parent = curr;
                    if (node.compareTo(curr) < 0) {
                        curr = curr.left;
                    }else if (node.compareTo(curr) > 0) {
                        curr = curr.right;
                    }
                }
                if (node.compareTo(parent) < 0) {
                    //新节点的值比父节点小
                    parent.left = node;
                } else if (node.compareTo(parent) > 0) {
                    //新节点的值比父节点大
                    parent.right = node;
                } else {
                    throw new RuntimeException("can not insert same value into tree: " + node.val);
                }
            }
        }

        return root;
    }


    /**
     * 实现compareTo方法，将此对象与指定对象进行比较。 返回一个
     * 负整数，零或正整数，因为此对象小于，等于或大于指定的对象。
     *
     * @param o
     * @return
     */
    public int compareTo(TreeNode<T> o) {

        T value1 = this.val;
        if (value1 instanceof Short) {
            Short value2 = (Short) o.val;
            return ((Short) value1).compareTo(value2);
        } else if (value1 instanceof Byte) {
            Byte value2 = (Byte) o.val;
            return ((Byte) value1).compareTo(value2);
        } else if (value1 instanceof Integer) {
            Integer value2 = (Integer) o.val;
            return ((Integer) value1).compareTo(value2);
        } else if (value1 instanceof Character) {
            Character value2 = (Character) o.val;
            return ((Character) value1).compareTo(value2);
        } else if (value1 instanceof Long) {
            Long value2 = (Long) o.val;
            return ((Long) value1).compareTo(value2);
        } else if (value1 instanceof Float) {
            Float value2 = (Float) o.val;
            return ((Float) value1).compareTo(value2);
        } else if (value1 instanceof Double) {
            Double value2 = (Double) o.val;
            return ((Double) value1).compareTo(value2);
        }
        return 0;
    }
}
