package top.easyblog.search.二分算法;


import java.util.HashMap;
import java.util.Map;

/**
 * <h4>LeetCode 33. 搜索旋转排序数组</h4>
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
 * @author ：huangxin
 * @modified ：
 * @since ：2020/06/09 10:22
 */
public class SearchRotationSortArray {

    /**
     * 解法一：使用Map存储数组的元素之后利用hash的特性实现快速查找有没有并返回指定的值
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
        Map<Integer,Integer> numMap=new HashMap<>();
        for(int i=0;i<nums.length;i++){
            numMap.put(nums[i],i);
        }
        Integer ans=-1;
        return (ans=numMap.get(target))==null?-1:ans;
    }


    public int binarySearch(int[] nums,int target){
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

}
