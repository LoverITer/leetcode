package top.easyblog.array;



import java.util.Arrays;
import java.util.Objects;

/**
 * @author HuangXin
 * @since 2020/2/11 21:06
 * 根据LeetCode算法题总结出来的一个有关数组的工具类
 */
public class ArrayUtils {

    private static ArrayUtils arrayUtils = null;

    private ArrayUtils() {

    }

    public static ArrayUtils getInstance() {
        if (Objects.isNull(arrayUtils)) {
            synchronized (ArrayUtils.class) {
                if (Objects.isNull(arrayUtils)) {
                    arrayUtils = new ArrayUtils();
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
     * @return 没有重复元素的数组
     */
    public int[] removeDuplicates(int[] nums) {
        if (nums.length == 0) return null;
        int i = 0;
        for (int j = 1; j < nums.length; j++) {
            if (nums[j] != nums[i]) {
                i++;
                nums[i] = nums[j];
            }
        }
        return Arrays.copyOf(nums, i + 1);
    }

}
