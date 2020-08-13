package algorithm.dp;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author ：huangxin
 * @modified ：
 * @since ：2020/08/12 19:53
 */
public class 最长递增子序列 {

    /**
     * 动态规划：
     * 1、定义dp数组的含义：dp[i]表示长度为i的nums[i]数组的最长子序列
     * 2、dp[n]的值应该是nums[0~n-1]大于nums[n]的最大子序列长度+1
     * @param nums
     * @return
     */
    private static int helper(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int[] dp = new int[nums.length];
        //base case  默认自己就是一个子序列
        Arrays.fill(dp,1);
        for (int i = 0; i < nums.length; i++) {
            //计算当长度为j的时候最长的子序列长度
            for (int j = 0; j < i; j++) {
                //寻找前面那些比num[i]小的子序列，然后计算将nums[i]拼接后的长度
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }

        //寻找最大值返回
        int max = 0;
        for (int num : dp) {
            max = Math.max(max, num);
        }
        return max;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < nums.length; i++) {
            nums[i] = sc.nextInt();
        }
        System.out.println(helper(nums));
    }

}
