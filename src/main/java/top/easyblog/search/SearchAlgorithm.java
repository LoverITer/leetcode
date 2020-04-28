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

    /**
     * <h5>二分法变种(具体的应用)
     * <h5/>
     * <p>
     * 假设按照升序排序的数组在预先未知的某个点上进行了旋转。
     * ( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。
     *
     * 搜索一个给定的目标值，如果数组中存在这个目标值，则返回它的索引，否则返回 -1 。
     * 示例：
     * </p>
     * <pre>
     * 输入: nums = [4,5,6,7,0,1,2], target = 0
     * 输出: 4
     * <pre/>
     * @param nums
     * @param target
     * @return
     */
    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        if (nums.length == 1) {
            return nums[0] == target ? 0 : -1;
        }
        int left = 0, right = nums.length - 1, mid = -1;
        while (left <= right) {
            mid = (left + right) >>> 1;
            if (nums[mid] == target) {
                return mid;
            }
            //对于数组中有过多重复数字的情况直接遍历就好了，因为这时二分的复杂度也是O(n)
            if (nums[left] == nums[mid]) {
                left++;
                continue;
            }
            //前半区间有序
            if (nums[left] < nums[mid]) {
                if (nums[left] <= target && target < nums[mid]) {
                    //target在前半区间
                    right = mid - 1;
                } else {
                    //target在后半区间
                    left = mid + 1;
                }
            } else {  //后半区间有序
                if (target <= nums[right] && target > nums[mid]) {
                    //target在前半区间
                    left = mid + 1;
                } else {
                    //target在后半区间
                    right = mid - 1;
                }
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
