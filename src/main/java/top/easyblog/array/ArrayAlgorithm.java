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

    private static ArrayAlgorithm arrayUtils = null;

    private ArrayAlgorithm() {

    }

    public static ArrayAlgorithm getInstance() {
        if (Objects.isNull(arrayUtils)) {
            synchronized (ArrayAlgorithm.class) {
                if (Objects.isNull(arrayUtils)) {
                    arrayUtils = new ArrayAlgorithm();
                }
            }
        }
        return arrayUtils;
    }

    /**
     * 移除排序数组的重复数据（元素）<br/>
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
     * 移除一个数组中的位val的数<br/>
     * 思路：遍历数组，当nums[i]==val时，把nums[i]和数组末尾的值交换一下，并释放最后一个数（实际上是然数组的长度-1）
     * 注意：但是有可能我们最后一个数也是需要删除的。对于这个问题无序担心，因为每次交换会对nums[i]进行两次检查
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
        while (i < len) {
            if (nums[i] == val) {
                nums[i] = nums[len - 1];
                len--;
            } else {
                i++;
            }
        }
        return Arrays.copyOf(nums, len);
    }

}
