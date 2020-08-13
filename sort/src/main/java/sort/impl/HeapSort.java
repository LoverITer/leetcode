package sort.impl;

import sort.IArraySort;

import java.util.Arrays;

/**
 * @author ：huangxin
 * @modified ：
 * @since ：2020/08/01 23:43
 */
public class HeapSort implements IArraySort {

    @Override
    public int[] sort(int[] array) {
        int len;
        if (array == null || (len = array.length) == 0) {
            return new int[]{};
        }
        //1.构建大顶堆
        for (int i = len / 2 - 1; i >= 0; i--) {
            //从最后一个非叶节点开始向前遍历，调整节点性质，使之成为大顶堆
            heapify(array, i, len);
        }

        for (int i = len - 1; i > 0; i--) {
            //2.交换堆顶和末尾结点(请忽略交换的方式，反正就是交换array[0]和array[i])
            array[0] = array[0] ^ array[i];
            array[i] = array[0] ^ array[i];
            array[0] = array[0] ^ array[i];
            //堆的长度减1，表示数组末尾的数字已经排序到位了
            len--;
            //3.交换过后重新调整为新的大顶堆
            heapify(array, 0, len);
        }
        return array;
    }

    /**
     * 将普通的数组堆化，构建成一个大顶堆
     *
     * @param array 待堆化数组
     * @param i     堆化开始位置
     * @param len   堆化长度
     */
    private void heapify(int[] array, int i, int len) {
        //当前父节点
        int root = array[i];
        for (int k = 2 * i + 1; k < len; k = k * 2 + 1) { //从i结点的左子结点开始，也就是2i+1处开始
            if (k + 1 < len && array[k] < array[k + 1]) {
                //如果左子结点小于右子结点，k指向右子结点
                k++;
            }
            if (array[k] > root) {
                //如果子节点大于父节点，将子节点值赋给父节点（不用进行交换）
                array[i] = array[k];
                i = k;
            } else {
                break;
            }
        }
        //将temp值放到最终的位置
        array[i] = root;
    }

    public static void main(String[] args) {
        int[] ints = {45, 23, 87, 89, 12, 67, -1, 34};
        new HeapSort().sort(ints);
        System.out.println(Arrays.toString(ints));
    }
}
