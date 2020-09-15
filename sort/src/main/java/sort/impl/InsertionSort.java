package sort.impl;

import sort.IArraySort;

import java.util.Arrays;

/**
 * 插入排序（Insertion-Sort）的算法描述是一种简单直观的排序算法。它的工作原理是通过构建有序序列，
 * 对于未排序数据，在已排序序列中从后向前扫描，找到相应位置并插入。
 * 一般来说，插入排序都采用in-place在数组上实现。具体算法描述如下：
 * <pre>
 * 1.从第一个元素开始，该元素可以认为已经被排序；
 * 2.取出下一个元素，在已经排序的元素序列中从后向前扫描；
 * 3.如果该元素（已排序）大于新元素，将该元素移到下一位置；
 * 4.重复步骤3，直到找到已排序的元素小于或者等于新元素的位置；
 * 5.将新元素插入到该位置后；
 * 6.重复步骤2~5。
 * </pre>
 * @author ：huangxin
 * @modified ：
 * @since ：2020/04/21 23:30
 */
public class InsertionSort  implements IArraySort {

    @Override
    public int[] sort(int[] array) {
        //外层遍历无序序列
        for (int i = 1; i < array.length; i++) {
            //内层逐个和有序序列比较，这里是可以优化的地方
            for (int j = i - 1; (j >= 0) && (array[j] > array[j + 1]); j--) {
                int t=array[j];
                array[j]=array[j+1];
                array[j+1]=t;
            }
        }
        return array;
    }


    /**
     * 直接插入排序优化——使用二分查找提高查找插入的位置效率，这种方式又称为折半插入排序
     * 时间复杂度O(n*logn)
     *
     * @param array
     */
    public static void binarySort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            int tmp=array[i];
            //在有序序列中定位array[i]应该插入到的位置
            int insertIndex = findInsertIndex(array, 0, i - 1, tmp);
            //移动数组元素，给待插入的元素腾位置
            for (int j = i; j > insertIndex; j--) {
                array[j] = array[j - 1];
            }
            //把tmp值放入合适的位置
            array[insertIndex] = tmp;
        }
    }

    private static int findInsertIndex(int[] array, int left, int right, int value) {
        while (left <= right) {
            int mid = (left + right) >>> 1;
            if (value > array[mid]) {
                left = mid + 1;
            } else if (value < array[mid]) {
                right = mid - 1;
            }else {
                return mid;
            }
        }
        return left;
    }

    public static void main(String[] args) {
        int[] array = {25, 34, 67, 12, 78, 45, 24, 67};
        binarySort(array);
        System.out.println(Arrays.toString(array));
    }
}
