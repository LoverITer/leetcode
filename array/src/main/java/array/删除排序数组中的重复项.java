package array;

/**
 * @author ：huangxin
 * @modified ：
 * @since ：2020/07/31 20:23
 */
public class 删除排序数组中的重复项 {

    /**
     * 使用两个指针left、right
     * @param nums
     * @return
     */
    public int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int left = 0;
        for (int right = 1; right < nums.length; right++) {
            if (nums[left] != nums[right]) {
                nums[++left] = nums[right];
            }
        }
        return left + 1;
    }

}
