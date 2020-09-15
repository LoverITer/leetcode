package _58同城;

/**
 * @author ：huangxin
 * @modified ：
 * @since ：2020/09/14 21:47
 */
public class 缺失的第一个正数 {


    //原地哈希：遍历数组，对于nums[i]，如果它是正数并且它的大小小于等于数组长度
    //那么就将它移动到合适的位置（index=nums[i]-1，比如,nums[i]=3，那么在数组中已改把它移动到下标为2的位置
    // 如此循环，之后再次遍历原数组，如果发现某个位置nums[i]!=i+1,那么就将i+1返回）
    public static int firstMissingPositive(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 1;
        }
        for (int i = 0; i < nums.length; i++) {
            //整数&&数的大小小于数组长度
            // nums[nums[i] - 1] != nums[i]   防止 nums[i]==nums[nums[i]-1]的情况出现导致死循环
            while (nums[i] >= 1 && nums[i] <= nums.length && nums[nums[i] - 1] != nums[i]) {
                int t = nums[i];
                nums[i] = nums[t - 1];
                nums[t - 1] = t;
            }
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i + 1) {
                return i + 1;
            }
        }
        return nums.length + 1;
    }

    public static void main(String[] args) {
        System.out.println(firstMissingPositive(new int[]{-1,2,4,1,6,8}));
    }

}
