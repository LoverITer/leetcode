package top.easyblog.sort;

/**
 * 冒泡排序Java实现
 * 冒泡排序的思想： 把相邻的两个元素比较，把较大的元素放到右侧，较小的元素放左侧。
 * 每一趟冒泡都会包当前集合中未排序集的最大数字移动到末尾
 *
 * @author ：huangxin
 * @modified ：
 * @since ：2020/04/16 00:07
 */
public class BubbleSort {


    /**
     * 冒泡排序最优化版本
     *
     * @param array
     */
    public void sort(int[] array) {
        if (array == null || array.length == 0) {
            return;
        }
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


}
