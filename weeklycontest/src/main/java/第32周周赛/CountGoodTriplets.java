package 第32周周赛;

/**
 * 给你一个整数数组 arr ，以及 a、b 、c 三个整数。请你统计其中好三元组的数量。
 *
 * 如果三元组 (arr[i], arr[j], arr[k]) 满足下列全部条件，则认为它是一个 好三元组 。
 *<pre>
 * 1.0 <= i < j < k < arr.length
 * 2.|arr[i] - arr[j]| <= a
 * 3.|arr[j] - arr[k]| <= b
 * 4.|arr[i] - arr[k]| <= c
 * 其中 |x| 表示 x 的绝对值。
 *</pre>
 * 返回 好三元组的数量 。
 * @author ：huangxin
 * @modified ：
 * @since ：2020/08/02 21:25
 */
public class CountGoodTriplets {

    //直接暴力破解，遍历每一种可能
    public int countGoodTriplets(int[] arr, int a, int b, int c) {
        int len;
        if (arr == null || (len = arr.length) < 3) {
            return 0;
        }
        int count = 0;
        for (int i = 0; i < len - 2; i++) {
            for (int j = i + 1; j < len - 1; j++) {
                for (int k = j + 1; k < len && Math.abs(arr[i] - arr[j]) <= a; k++) {
                    if (Math.abs(arr[j] - arr[k]) <= b &&
                            Math.abs(arr[i] - arr[k]) <= c) {
                        count++;
                    }
                }
            }

        }
        return count;
    }

    public static void main(String[] args) {

    }
}
