package sort;

/**
 * @author ：huangxin
 * @modified ：
 * @since ：2020/05/01 23:12
 */
public class QuickSort {

    /**
     * 快速排序JAVA实现
     * 一趟快速排序的过程叫做一次划分，做法是：先找一个数作为基准值（pivot，一般将一个序列的首元素
     * 作为基准），然后j“哨兵”从序列末尾开始，i“哨兵”从序列的首元素开始，j向前遍历当找到一个比基准
     * 值小的数就交换a[i],a[j]，i向后遍历当找到一个比基准值大的数就教换a[i]，a[j]（简而言之就是“边
     * 挖坑，边添坑”）.这个过程直到i=j时结束。
     *
     * @param array
     */
    public void quickSort(int[] array) {
        if (array == null || array.length == 0) {
            return;
        }
        quickSort(array, 0, array.length - 1);
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


