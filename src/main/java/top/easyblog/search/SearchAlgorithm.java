package top.easyblog.search;

/**
 * 查找算法
 *
 * @author ：huangxin
 * @modified ：
 * @since ：2020/02/22 01:07
 */
public class SearchAlgorithm {

    /**
     * 二分查找递归版本
     *
     * @param nums   目标数组
     * @param head   查找的起始位置
     * @param tail   查找的末尾
     * @param target 关键字
     * @return 关键字在数组中的索引，如果没找到返回-1
     */
    public int binarySearch(int[] nums, int head, int tail, int target) {
        checkNotNull(nums);
        int mid = (head + tail) >>> 1;
        if (nums[mid] == target) {
            return mid;
        } else if (nums[mid] > target) {
            return binarySearch(nums, head, mid + 1, target);
        } else {
            return binarySearch(nums, mid - 1, tail, target);
        }
    }

    /**
     * 二分查找——非递归实现
     *
     * @param nums   目标数组
     * @param target 关键字
     * @return
     */
    public int binarySearch(int[] nums, int target) {
        checkNotNull(nums);
        int left = 0, right = nums.length;
        int mid = -1;
        while (left <= right) {
            mid = (left + right) >>> 1;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }


    private void checkNotNull(int[] nums) {
        if (nums == null || nums.length <= 0) {
            throw new NullPointerException("array can`t be null!");
        }
    }

}
