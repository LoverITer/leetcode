package top.easyblog.sort;

/**
 * @author ：huangxin
 * @modified ：
 * @since ：2020/06/26 00:17
 */
public class CountSort {

    /**
     * 计数排序简单实现
     *
     * @param array
     * @return
     */
    public static int[] countSort(int[] array) {
        if (array == null || array.length == 0) {
            return new int[]{};
        }
        int max = array[0];
        for (int num : array) {
            if (max < num) {
                max = num;
            }
        }
        //寻找到最大的值max，创建max+1大小的数组
        int[] countArray = new int[max + 1];
        for (int value : array) {
            countArray[value]++;
        }
        int count = 0;
        for (int i = 0; i < countArray.length; i++) {
            for (int j = 0; j < countArray[i]; j++) {
                array[count++] = i;
            }
        }
        return array;
    }

}
