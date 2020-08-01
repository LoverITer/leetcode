package sort.impl;

import sort.IArraySort;

import java.util.Arrays;

/**
 * 冒泡排序Java实现
 * 冒泡排序的思想： 把相邻的两个元素比较，把较大的元素放到右侧，较小的元素放左侧。
 * 每一趟冒泡都会包当前集合中未排序集的最大数字移动到末尾
 *
 * @author ：huangxin
 * @modified ：
 * @since ：2020/04/16 00:07
 */
public class BubbleSort implements IArraySort {


    @Override
    public int[] sort(int[] array) {
        if (array == null || array.length == 0) {
            return array;
        }
        sort1(array);
        //sort2(array)
        return array;
    }

    /**
     * 冒泡排序初始实现
     *
     * @param array
     * @return
     */
    public int[] sort1(int[] array) {
        int len = array.length;
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    //使用异或的性质交换两个数
                    array[j] = array[j] ^ array[j + 1];
                    array[j + 1] = array[j] ^ array[j + 1];
                    array[j] = array[j] ^ array[j + 1];
                }
            }
        }
        return array;
    }


    /**
     * 冒泡排序最优化版本
     *
     * @param array
     */
    public void sort2(int[] array) {
        //记录最后一次交换的位置
        int lastIndex = 0;
        //记录无序序列的边界，以后每次比较到此位置
        int sortBorder = array.length - 1;
        for (int i = 0; i < array.length - 1; i++) {
            //标记在这一趟中是否发生过移动，如果没有发生过移动那就说明已经有序了，就可以提前结束
            boolean moved = false;
            for (int j = 0; j < sortBorder; j++) {
                if (array[j] > array[j + 1]) {
                    int tmp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = tmp;
                    moved = true;
                    lastIndex = j;
                }
            }
            sortBorder = lastIndex;
            if (!moved) {
                break;
            }
        }
    }


    public static void main(String[] args) {
        System.out.println(Arrays.toString(new BubbleSort().sort(new int[]{25, 56, 23, 67, 89, 12, 45, 42, 34})));
    }

}
