package array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？
 * 请你找出所有满足条件且不重复的三元组。
 *
 * 示例：
 *
 * 给定数组 nums = [-1, 0, 1, 2, -1, -4]，
 *
 * 满足要求的三元组集合为：
 * [
 *   [-1, 0, 1],
 *   [-1, -1, 2]
 * ]
 * @author ：huangxin
 * @modified ：
 * @since ：2020/09/13 15:56
 */
public class 三数之和 {

    public static void main(String[] agrs) {
        Scanner sc = new Scanner(System.in);
        String line = sc.nextLine();
        String[] strs = line.split(" ");
        int[] nums = new int[strs.length];
        for (int i = 0; i < strs.length; i++) {
            nums[i] = Integer.parseInt(strs[i]);
        }
        threeSumList(nums).forEach(list -> {
            list.forEach(num -> System.out.print(num + " "));
            System.out.println();
        });
    }


    /**
     * 先将问题简化为两数之和，然后在使用二分查找寻找target=0-nums[i]的两个数
     * @param nums
     * @return
     */
    private static List<List<Integer>> threeSumList(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            //若 nums[i]>0nums[i]>0：因为已经排序好，所以后面不可能有三个数加和等于 0，直接返回结果。
            if (nums[i] > 0) {
                break;
            }
            //这里将问题简化为在数组中寻找两个数的和为0-nums[i]
            int target = 0 - nums[i];
            int left = i + 1, right = nums.length - 1;
            //对于重复元素：跳过，避免出现重复解
            if (i == 0 || nums[i] != nums[i - 1]) {
                while (left < right) {
                    if (nums[left] + nums[right] == target) {
                        ans.add(Arrays.asList(nums[i], nums[left], nums[right]));
                        //去重操作
                        while (left < right && nums[left] == nums[left + 1]) {
                            left++;
                        }
                        while (left < right && nums[right] == nums[right - 1]) {
                            right--;
                        }
                        left++;
                        right--;
                    } else if (nums[left] + nums[right] < target) {
                        //和<0，说明nums[left]太小，将left向右移动
                        left++;
                    } else {
                        //和>0，说明nums[right]太大，将right向左移动
                        right--;
                    }
                }
            }
        }
        return ans;
    }

}
