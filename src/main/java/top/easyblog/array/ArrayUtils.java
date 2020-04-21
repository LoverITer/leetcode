package top.easyblog.array;

import java.util.Arrays;

/**
 * @author ：huangxin
 * @modified ：
 * @since ：2020/04/21 22:48
 */
public class ArrayUtils {

    /**
     * 移除排序数组中的重复数据（元素）<br/>
     * 思路：使用快慢指针i和j,当nums[i]==nums[j]时,我们就增加 j 以跳过重复项。
     * 当nums[i]!=nums[j]时跳过重复项的运行已经结束,因此我们必须把此时nums[j]的值
     * 复制到 nums[i + 1]。然后递增 i,接着我们将再次重复相同的过程,直到 j 到达数组的末尾为止。
     *
     * @param nums 目标数组
     * @return int[]
     */
    public int[] removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }
        int fast = 0;
        for (int slow = 1; slow < nums.length; slow++) {
            if (nums[slow] != nums[fast]) {
                fast++;
                nums[fast] = nums[slow];
            }
        }
        return Arrays.copyOf(nums, fast + 1);
    }


    /**
     * 移除一个数组中的所有值为target的数<br/>
     *
     * @param nums   目标数组
     * @param target 数组中要移除的数
     * @return int[]
     */
    public int[] removeAllTargetElement(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return null;
        }
        int len = nums.length;
        int i = 0;
        //遍历数组
        while (i < len) {
            if (nums[i] == target) {
                //把要移除的数字和数组末尾的数字交换，并且把数组长度减1
                nums[i] = nums[len - 1];
                len--;
            } else {
                i++;
            }
        }
        return Arrays.copyOf(nums, len);
    }

    /**
     * 将给定数字序列重新排列成字典序中下一个更大的排列。
     * 如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。
     * 比如： 传入{1,5,6,3,7} 应该返回 {1,5,6,7,3}
     * @param nums 目标数组
     */
    public int[] nextBiggerPermutationNum(int[] nums) {
        int index = nums.length - 2;
        //顺序就是：1,2,3,4,5......
        //逆序就是：9,8,7,6,5......
        //从后往前，找到逆序递增的位置，就是要交换的数字
        while (index >= 0 && nums[index + 1] <= nums[index]) {
            index--;
        }
        if (index >= 0) {
            int j = nums.length - 1;
            //找到已近遍历过的逆序区域中仅仅比nums[index]大的数
            while (j >= 0 && nums[j] <= nums[index]) {
                j--;
            }
            //交换nums[index]和仅仅比nums[index]大的数
            swap(nums, index, j);
        }
        //将遍历过的逆序区域转换为顺序的,i一定是一次交换中较小的数，j一定是一次交换中较大的数，
        // 因此直接交换他们即可
        for (int i = index + 1, j = nums.length - 1; i < j; i++, j--) {
            swap(nums, i, j);
        }
        return nums;
    }


    /**
     * 交换数组中索引下标为i,j的两个数
     *
     * @param nums 目标数组
     * @param i    待交换数i
     * @param j    待交换数j
     */
    public static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }


    /**
     * 给定一个整数类型的数组 nums，请编写一个能够返回数组“中心索引”的方法。
     * 数组中心索引的左侧所有元素相加的和等于右侧所有元素相加的和。
     * 如果数组不存在中心索引，那么返回 -1.
     * 如果数组有多个中心索引，那么我们应该返回最靠近左边的那一个。
     *
     * @param nums
     * @return
     */
    public static int pivotIndex(int[] nums) {
        if (nums == null || nums.length < 3) {
            return -1;
        }
        int sum = 0, leftSum = 0, rightSum = 0;
        for (int num : nums) {
            sum += num;
        }

        for (int i = 0; i < nums.length; i++) {
            if (i != 0) {
                leftSum += nums[i - 1];
            }
            rightSum = sum - leftSum - nums[i];
            if (leftSum == rightSum) {
                return i;
            }
        }
        return -1;
    }



}
