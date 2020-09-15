package sort;

/**
 * 寻找一个数组中的第K大的数
 *
 * @author ：huangxin
 * @modified ：
 * @since ：2020/09/06 22:29
 */
public class TopKth {

    public int findKth(int[] a, int n, int K) {
        return find(a, 0, n - 1, K);
    }

    //递归寻找数组中第K大的元素
    private int find(int[] a, int low, int high, int K) {
        int pivot = partition(a, low, high);
        if (pivot + 1 < K) {
            //中轴位置少于K个，在右子数组中继续查找
            return find(a, pivot + 1, high, K);
        } else if (pivot + 1 > K) {
            //中轴位置大于K个，在左子数组中继续查找
            return find(a, low, pivot - 1, K);
        } else {
            //中轴元素正好是第K大的元素
            return a[pivot];
        }
    }

    //将数组划分为两部分，左边较大，右边较小
    private int partition(int[] a, int low, int high) {
        // 将数组首元素作为每一轮比较的基准
        int pivot = a[low];
        while (low < high) {
            // 从右往左扫描，直到遇到比基准元素小的元素
            while (low < high && a[high] <= pivot) {
                --high;
            }
            // 将右子数组中不合格的元素放到左边不合格元素的位置（原元素已经移走）
            a[low] = a[high];

            // 从左往右扫描，直到遇到比基准元素大的元素
            while (low < high && a[low] > pivot) {
                ++low;
            }
            // 将左子数组中不合格的元素放到左边不合格元素的位置（原元素已经移走）
            a[high] = a[low];
        }
        // 将基准元素放到中间位置
        a[low] = pivot;
        // 返回数组的中轴位置
        return low;
    }

    public static void main(String[] args) {
        System.out.println(new TopKth().findKth(new int[]{1332802, 1177178, 1514891, 871248, 753214, 123866, 1615405, 328656, 1540395, 968891, 1884022, 252932, 1034406, 1455178, 821713, 486232, 860175, 1896237, 852300, 566715, 1285209, 1845742, 883142, 259266, 520911, 1844960, 218188, 1528217, 332380, 261485, 1111670, 16920, 1249664, 1199799, 1959818, 1546744, 1904944, 51047, 1176397, 190970, 48715, 349690, 673887, 1648782, 1010556, 1165786, 937247, 986578, 798663}, 49, 24));
    }

}
