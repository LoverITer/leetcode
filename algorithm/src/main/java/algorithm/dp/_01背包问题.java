package algorithm.dp;

import java.util.Scanner;

/**
 * 0-1背包问题
 * 有N个物品，第i个物品的价值为vi,重量为wi，其中vi和wi均不为负数，
 * 背包的容量为W.现在要求用这些物品组合出一种不超过背包容量并且物品的价值最大的组合方式
 * <p>
 * 注意：这个题目中的物品不可以分割，要么装进包里，要么不装，不能说切成两块装⼀半。这就是 0-1背包这
 * 个名词的来历。
 *
 * @author ：huangxin
 * @modified ：
 * @since ：2020/08/13 08:17
 */
public class _01背包问题 {

    /**
     * <pre>
     * DP解题三部曲：
     * 1.明确问题的状态和选择
     *   1.1 状态 最终结果的值受【背包容量】和【物品重量】的限制，这两个参数一遍结果就会改变
     *   1.2 选择  一个物品装入背包或者不装入背包两种状态
     *
     * 2.明确dp数组的含义
     * 首先看看刚才找到的「状态」，有两个，也就是说我们需要一个二维dp数组，一维表示可选择的物品，一维表示背包的容量。
     * 因此dp[i][w]的含义就是：对于前i个物品，当背包容量为w的时候，最大可以装下的物品的价值是dp[i][w]
     *
     * 根据这个定义那么我们的答案就是dp[N][W],并且base case :dp[0][w]=0（没有物品可选）,dp[i][0]=0（背包没有容量）
     *
     * 3. 思考状态转移方程
     *  3.1 如果没有把第i个物品放入背包，那么dp[i][w]=dp[i-1][w],没装入新物品和肯定不加
     *  3.2 如果把第i个物品放入背包，那么dp[i][w]=dp[i-1][w-wights[i-1]]+value[i-1]
     * </pre>
     *
     * @param N      物品数量
     * @param W      背包载重
     * @param wights wight[i]表示第i个物品的重量
     * @param values values[i]表示第i个物品的价值
     * @return
     */
    public static int helper(int N, int W, int[] wights, int[] values) {
        int[][] dp = new int[N + 1][W + 1];
        for (int i = 1; i <= N; i++) {
            for (int w = 1; w <= W; w++) {
                //w-wights[i]判断当前背包能不能容下当前物品
                //当前物品的重量大于背包的容量，当然选择不装入书包了
                //当物品的重量小于背包的容量，选择装入背包，这时一般需要择优
                dp[i][w] = (w - wights[i-1] < 0) ? (dp[i - 1][w]) :
                        (Math.max(dp[i - 1][w], dp[i - 1][w - wights[i - 1]] + values[i - 1]));
            }
        }

        /*for (int i=0;i<dp.length;i++)
           System.out.println(Arrays.toString(dp[i]));*/
        return dp[N][W];
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();  //物品数量
        int w = sc.nextInt();   //背包重量
        int[] wight = new int[n];  //wight[i]表示第i个物品的重量
        int[] values = new int[n];  //values[i]表示第i个物品的价值
        for (int i = 0; i < wight.length; i++) {
            wight[i] = sc.nextInt();
        }
        for (int i = 0; i < values.length; i++) {
            values[i] = sc.nextInt();
        }
        System.out.println(helper(n, w, wight, values));
    }

}
