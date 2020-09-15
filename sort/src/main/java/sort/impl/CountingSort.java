package sort.impl;

import sort.IArraySort;

import java.util.Arrays;

/**
 * 计数排序不是基于比较的排序算法，其核心在于将输入的数据值转化为键存储在额外开辟的数组空间中。
 * 作为一种线性时间复杂度的排序，计数排序要求输入的数据必须是有确定范围的整数。
 * <pre>
 * 1.找出待排序的数组中最大和最小的元素；
 * 2.统计数组中每个值为i的元素出现的次数，存入数组C的第i项；
 * 3.对所有的计数累加（从C中的第一个元素开始，每一项和前一项相加）；
 * 4.反向填充目标数组：将每个元素i放在新数组的第C(i)项，每放一个元素就将C(i)减去1。
 * </pre>
 * @author ：huangxin
 * @modified ：
 * @since ：2020/06/26 00:17
 */
public class CountingSort implements IArraySort {

    /**
     * 计数排序简单实现
     *
     * @param array
     * @return
     */
    @Override
    public int[] sort(int[] array) {
        if (array == null || array.length == 0) {
            return new int[]{};
        }
        //寻找最大值
        int max=Integer.MIN_VALUE,min=Integer.MAX_VALUE;
        for (int num : array) {
            if (max < num) {
                max = num;
            }
        }
        //统计每个数的多少
        int[] countArray = new int[max+1];
        for (int value : array) {
            countArray[value]++;
        }
        int index = 0;
        for (int i = 0; i < countArray.length; i++) {
            /*for (int j = 0; j < countArray[i]; j++) {
                array[index++] = i;
            }*/
            int j=0;
            while(j<countArray[i]){
                array[index++]=i;
                j++;
            }
        }
        return array;
    }


    public static void main(String[] args) {
        int[] array = {45, 23, 12, 56, 78, 46, 78, 90, 235,46,10000000};
        new CountingSort().sort(array);
        System.out.println(Arrays.toString(array));
    }

}
