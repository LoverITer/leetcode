package sort.impl;

import sort.IArraySort;

import java.util.Arrays;

/**
 * 归并排序是建立在归并操作上的一种有效的排序算法。该算法是采用分治法（Divide and Conquer）
 * 的一个非常典型的应用。将已有序的子序列合并，得到完全有序的序列；即先使每个子序列有序，
 * 再使子序列段间有序。若将两个有序表合并成一个有序表，称为2-路归并。
 * <pre>
 * 1.把长度为n的输入序列分成两个长度为n/2的子序列；
 * 2.对这两个子序列分别采用归并排序；
 * 3.将两个排序好的子序列合并成一个最终的排序序列。
 * </pre>
 *
 * @author ：huangxin
 * @modified ：
 * @since ：2020/08/01 17:42
 */
public class MergeSort implements IArraySort {

    @Override
    public int[] sort(int[] array) {
        int len;
        if (array == null || (len = array.length) == 0) {
            return new int[]{};
        }
        mergeSort(array, 0, len - 1);
        return array;
    }

    /**
     * 递归的分隔数组，并调用方法{@code merge}合并两个有序数组
     *
     * @param array 待排序数组
     * @param low   排序起始位置
     * @param high  排序终止位置
     */
    private void mergeSort(int[] array, int low, int high) {
        if (low < high) {
            int mid = low + ((high - low) >>> 1);
            mergeSort(array, low, mid);
            mergeSort(array, mid + 1, high);
            merge(array, low, mid, high);
        }

    }

    /**
     * 合并两个有序数组
     *
     * @param array 数组
     * @param low   数组1起始地址
     * @param mid   数组1和数组2的分界点
     * @param high  数组2结束地址
     */
    private void merge(int[] array, int low, int mid, int high) {
        //开辟额外的空间
        int[] tmp = new int[high - low + 1];
        int i = 0, p1 = low, p2 = mid + 1; //这里p1,p2分别表示两个数组的起始地址
        while (p1 <= mid && p2 <= high) {
            tmp[i++] = array[p1] < array[p2] ? array[p1++] : array[p2++];
        }

        //退出大循环之后还需要分别判断一下哪个数组还没有遍历完
        while (p1 <= mid) {
            tmp[i++] = array[p1++];
        }
        while (p2 <= high) {
            tmp[i++] = array[p2++];
        }
        //把最终的排序结果复制到原数组
        for (i = 0; i < tmp.length; i++) {
            array[low + i] = tmp[i];
        }
    }




    public static void main(String[] args) {
        int[] array = {1, 2, 3, 1};
        new MergeSort().sort(array);
        System.out.println(array[array.length-1]);
        System.out.println(Arrays.toString(array));
    }

}
