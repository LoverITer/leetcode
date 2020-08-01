package sort.impl;

import sort.IArraySort;

/**
 * 快速排序的基本思想：通过一趟排序将待排记录分隔成独立的两部分，其中一部分记录的关键字均比
 * 另一部分的关键字小，则可分别对这两部分记录继续进行排序，以达到整个序列有序。
 * 快速排序使用分治法来把一个串（list）分为两个子串（sub-lists）。具体算法描述如下：
 * <pre>
 * 1.从数列中挑出一个元素，称为 “基准”（pivot）,一般以数列的第一个数字为基准；
 * 2.重新排序数列，所有元素比基准值小的摆放在基准前面，所有元素比基准值大的摆在基准的后面（相同的数可以到任一边）。在这个分区退出之后，该基准就处于数列的中间位置。这个称为分区（partition）操作；
 * 3.递归地（recursive）把小于基准值元素的子数列和大于基准值元素的子数列排序。
 * </pre>
 * @author ：huangxin
 * @modified ：
 * @since ：2020/05/01 23:12
 */
public class QuickSort implements IArraySort {

    @Override
    public int[] sort(int[] array) {
        if (array == null || array.length == 0) {
            return new int[]{};
        }
        quickSort(array, 0, array.length - 1);
        return array;
    }


    private void quickSort(int[] array, int low, int high) {
        if (low < high) {
            int mid = partition(array, low, high);
            quickSort(array, low, mid - 1);
            quickSort(array, mid + 1, high);
        }
    }

    /**
     * 快排-双边循环的法
     *
     * @param array
     * @param low
     * @param high
     * @return
     */
    private int partition(int[] array, int low, int high) {
        //将数组的首元素作为基准元素，进行划分数组
        int pivot = array[low];
        while (low < high) {
            while (low < high && array[high] > pivot){
                high--;
            }
            array[low] = array[high];
            while (low < high && array[low] <= pivot){
                low++;
            }
            array[high] = array[low];
        }
        //low==high
        array[low] = pivot;
        //当这一步，数组中的元素应该是以pivot为中心，在pivot之前的数都比他小，在pivot之后的数都比他大
        return low;
    }


    /**
     * 快排——单边循环法
     * 单边循环法的效率比双边循环的效率要高一点，但是没有双边循环法稳定
     *
     * @param array
     * @param low
     * @param high
     * @return
     */
    private int partitionSingle(int[] array, int low, int high) {
        int pivot = array[low];
        int mark = low;

        for (int i = low + 1; i <= high; i++) {
            if (array[i] < pivot) {
                int t = array[++mark];
                array[mark] = array[i];
                array[i] = t;
            }
        }

        array[low] = array[mark];
        array[mark] = pivot;
        return mark;
    }

}


