package top.easyblog.sort;

import top.easyblog.array.ArrayUtils;

import java.util.Arrays;

/**
 * 直接插入排序Java实现
 * 直接插入排序将一个序列分成已排序序列（有序序列）和未排序序列（无序序列）,
 * 每次从无序序列中取一个元素并和有序序列中的元素逐个比较，并插入到合适的位置
 *
 * @author ：huangxin
 * @modified ：
 * @since ：2020/04/21 23:30
 */
public class DirectInsertSort implements Sort {

    @Override
    public void sort(int[] array) {
        //外层遍历无序序列
        for (int i = 1; i < array.length; i++) {
            //内层逐个和有序序列比较，这里是可以优化的地方
            for (int j = i - 1; (j >= 0) && (array[j] > array[j + 1]); j--) {
                ArrayUtils.swap(array, j, j + 1);
            }
        }

    }


    /**
     * 直接插入排序优化——使用二分查找提高查找插入的位置效率，这种方式又称为折半插入排序
     * 时间复杂度O(n*logn)
     *
     * @param array
     */
    public void binarySort(int[] array) {
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

    private int findInsertIndex(int[] array, int left, int right, int value) {
        while (left <= right) {
            int mid = (left + right) >>> 1;
            if (value > array[mid]) {
                left = mid + 1;
            } else if (value < array[mid]) {
                right = mid - 1;
            }
        }
        return left;
    }
}
