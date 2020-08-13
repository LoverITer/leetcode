package search.binarysearch;

import sort.impl.QuickSort;

import java.util.Random;

/**
 * 二分查找的两种实现方式
 *
 * @author ：huangxin
 * @modified ：
 * @since ：2020/06/09 10:26
 */
public class BinarySearch {

    /**
     * 二分查找递归版本
     *
     * @param array  数组，要求必须是有序的
     * @param low
     * @param high
     * @param target 查找的关键字
     * @return
     */
    public int binarySearch(int[] array, int low, int high, int target) {
        if (array == null || array.length == 0) {
            return -1;
        }
        int mid = low + ((high-low)>>>1);
        if (array[mid] == target) {
            return mid;
        } else if (array[mid] > target) {
            return binarySearch(array, low, mid - 1, target);
        } else {
            return binarySearch(array, mid + 1, high, target);
        }
    }


    /**
     * 二分查找分迭代版本
     *
     * @param array
     * @param target
     * @return
     */
    public int binarySearch(int[] array, int target) {
        if (array == null || array.length == 0) {
            return -1;
        }
        int low = 0, high = array.length - 1;
        while (low <= target) {
            int mid = low + ((high - low) >>>1);
            if (array[mid] == target) {
                return mid;
            } else if (array[mid] > target) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return -1;
    }


    public static void main(String[] args) {
        Random random=new Random();
        int[] array=new int[100000];
        for(int i=0;i<10_0000;i++){
            array[i]=new Random().nextInt(10_000);
        }
        new QuickSort().sort(array);
        long start=System.nanoTime();
        int index = new BinarySearch().binarySearch(array,5000);
        long end=System.nanoTime();
        System.out.println("查询完毕，结果："+index+",用时："+(end-start)+"ns");
    }

}
