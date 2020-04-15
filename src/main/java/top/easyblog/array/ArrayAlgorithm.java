package top.easyblog.array;


import java.util.Arrays;
import java.util.Objects;

/**
 * 根据LeetCode算法题总结出来的一个有关数组的工具类
 *
 * @author HuangXin
 * @since 2020/2/11 21:06
 */
public class ArrayAlgorithm {

    /**
     * 移除排序数组中的重复数据（元素）<br/>
     * 思路：定义快慢指针i和j,当nums[i]==nums[j]时,我们就增加 j 以跳过重复项。
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
        int i = 0;
        for (int j = 1; j < nums.length; j++) {
            if (nums[j] != nums[i]) {
                i++;
                nums[i] = nums[j];
            }
        }
        return Arrays.copyOf(nums, i + 1);
    }


    /**
     * 移除一个数组中的所有值为val的数<br/>
     * 复杂度： 时间复杂度O(n)   空间复杂度O(1)
     *
     * @param nums 目标数组
     * @param val  数组中要移除的数
     * @return int[]
     */
    public int[] removeElement(int[] nums, int val) {
        if (nums == null || nums.length == 0) {
            return null;
        }
        int len = nums.length;
        int i = 0;
        //遍历数组
        while (i < len) {
            if (nums[i] == val) {
                //把要移除的数字和数组末尾的数字交换，并且把数组长度减1表示移除次数
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
     *
     * @param nums 目标数组
     */
    public void nextPermutation(int[] nums) {
        int index = nums.length - 2;
        //从后往前找到，找到逆序递增的边界，就是要交换的数字
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
        //将遍历过的逆序区域转换为顺序,i一定是一次交换中较小的数，j一定是一次交换中较大的数，因此直接交换他们即可
        for (int i = index + 1, j = nums.length - 1; i < j; i++, j--) {
            swap(nums, i, j);
        }
    }


    /**
     * 交换数组中的两个数
     *
     * @param nums 目标数组
     * @param i    待交换数i
     * @param j    待交换数j
     */
    private void swap(int[] nums, int i, int j) {
        nums[i] = nums[i] + nums[j];
        nums[j] = nums[i] - nums[j];
        nums[i] = nums[i] - nums[j];
    }




}
