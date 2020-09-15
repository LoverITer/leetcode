package array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * 给定一个包含 n 个整数的数组 nums 和一个目标值 target，判断 nums 中是否存在四个元素 a，b，c 和 d ，
 * 使得 a + b + c + d 的值与 target 相等？找出所有满足条件且不重复的四元组。
 *
 *
 * 示例：
 * 给定数组 nums = [1, 0, -1, 0, -2, 2]，和 target = 0。
 *
 * 满足要求的四元组集合为：
 * [
 *   [-1,  0, 0, 1],
 *   [-2, -1, 1, 2],
 *   [-2,  0, 0, 2]
 * ]
 *
 * @author ：huangxin
 * @modified ：
 * @see 三数之和
 * @since ：2020/09/13 23:36
 */
public class 四数之和 {


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String line = sc.nextLine();
        String[] strs = line.split(" ");
        int[] nums = new int[strs.length];
        for (int i = 0; i < strs.length; i++) {
            nums[i] = Integer.parseInt(strs[i]);
        }
        int target = sc.nextInt();
        fourSum(nums, target).forEach(list -> {
            list.forEach(num -> System.out.print(num + " "));
            System.out.println();
        });
    }

    public static List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        if (nums == null || nums.length < 4) {
            return ans;
        }
        Arrays.sort(nums);
        int length = nums.length;
        for (int k = 0; k < nums.length - 3; k++) {
            /*当k的值与前面的值相等时忽略*/
            if (k > 0 && nums[k] == nums[k - 1]) {
                continue;
            }
            /*当前数组k之后的四个相加之和，如果比目标值大，说明后面越来越大的值根本没戏*/
            if (nums[k] + nums[k + 1] + nums[k + 2] + nums[k + 3] > target) {
                break;
            }
            /*当前数组k和倒数3个数之和，如果比目标值小，说明后面越来越小的值根本没戏，忽略本次循环，尝试增大k*/
            if (nums[k] + nums[length - 1] + nums[length - 2] + nums[length - 3] < target) {
                continue;
            }
            for (int i = k + 1; i < nums.length - 2; i++) {
                /*定义指针j指向i+1*/
                int left = i + 1;
                /*定义指针h指向数组末尾*/
                int right = length - 1;
                /*获取当前最小值，如果最小值比目标值大，说明后面越来越大的值根本没戏*/
                if (nums[k] + nums[i] + nums[left] + nums[left + 1] > target) {
                    break;
                }
                /*获取当前最大值，如果最大值比目标值小，说明后面越来越小的值根本没戏，忽略*/
                if (nums[k] + nums[i] + nums[right] + nums[right - 1] < target) {
                    continue;
                }
                if (i == k + 1 || nums[k] != nums[i - 1]) {
                    /*开始j指针和h指针的表演，计算当前和，如果等于目标值，left++并去重，right--并去重，当当前和大于目标值时h--，当当前和小于目标值时j++*/
                    while (left < right) {
                        int sum = nums[k] + nums[i] + nums[left] + nums[right];
                        if (sum == target) {
                            ans.add(Arrays.asList(nums[k], nums[i], nums[left], nums[right]));
                            while (left < right && nums[left] == nums[left + 1]) {
                                left++;
                            }
                            while (left < right && i < right && nums[right] == nums[right - 1]) {
                                right--;
                            }
                            left++;
                            right--;
                        } else if (sum > target) {
                            right--;
                        } else {
                            left++;
                        }
                    }
                }
            }
        }

        return ans;
    }

}
