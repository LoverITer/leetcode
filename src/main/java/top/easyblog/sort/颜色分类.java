package top.easyblog.sort;

/**
 * @author ：huangxin
 * @modified ：
 * @since ：2020/06/25 23:31
 */
public class 颜色分类 {

    /**
     * 计数排序的思想，先统计出来各个数的多少，然后重写原数组
     *
     * @param nums
     */
    public static void sortColors(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }
        int[] counter = new int[]{0, 0, 0};
        for (int num : nums) {
            counter[num]++;
        }
        int count = 0;
        for (int i = 0; i < counter.length; i++) {
            for (int j = 0; j < counter[i]; j++) {
                nums[count++] = i;
            }
        }
    }

    public static void main(String[] args) {
        sortColors(new int[]{2, 0, 2, 1, 1, 0});
    }
}
