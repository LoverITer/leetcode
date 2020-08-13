package sort.impl;

import sort.IArraySort;

import java.util.Arrays;

/**
 * 1959年Shell发明，第一个突破O(n2)的排序算法，是简单插入排序的改进版。
 * 它与插入排序的不同之处在于，它会优先比较距离较远的元素。希尔排序又叫缩小增量排序。
 * 先将整个待排序的记录序列分割成为若干子序列分别进行直接插入排序，具体算法描述：
 * <pre>
 * 1.选择一个增量序列t1，t2，…，tk，其中ti>tj，tk=1；
 * 2.按增量序列个数k，对序列进行k 趟排序；
 * 3.每趟排序，根据对应的增量ti，将待排序列分割成若干长度为m 的子序列，分别对各子表进行直接插入排序。
 * 仅增量因子为1 时，整个序列作为一个表来处理，表长度即为整个序列的长度。
 * </pre>
 *
 * @author ：huangxin
 * @modified ：
 * @see InsertionSort
 * @since ：2020/08/01 16:54
 */
public class ShellSort implements IArraySort {


    /**
     * @param array
     * @return
     */
    @Override
    public int[] sort(int[] array) {
        int len;
        if (array == null || (len = array.length) == 0) {
            return new int[]{};
        }
        int gpa = 1;
        //寻找最大的合适的间距
        while (gpa < len) {
            gpa = gpa * 3 + 1;
        }
        while (gpa > 0) {
            //内部就是简单插入排序
            for (int i = gpa; i < len; i++) {
                for (int j = i - gpa; (j >= 0) && (array[j] > array[j + gpa]); j -= gpa) {
                    array[j] = array[j] ^ array[j + gpa];
                    array[j + gpa] = array[j] ^ array[j + gpa];
                    array[j] = array[j] ^ array[j + gpa];
                }
            }
            //计算剩下的增量
            gpa = (int) Math.floor(gpa / 3);
        }
        return array;
    }

    public void main(String[] args) {
        int[] array = {25, 34, 67, 12, 78, 45, 24, 67, -2, -5};
        sort(array);
        System.out.println(Arrays.toString(array));
    }

}
