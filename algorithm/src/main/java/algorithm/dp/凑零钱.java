package algorithm.dp;

import java.util.Scanner;

/**
 * @author ：huangxin
 * @modified ：
 * @since ：2020/08/12 17:05
 */
public class 凑零钱 {


    /**
     * 给你 k 种⾯值的硬币，⾯值分别为 c1, c2 ... ck ，每种硬
     * 币的数量⽆限，再给⼀个总⾦额 amount ，问你最少需要⼏枚硬币凑出这个
     * ⾦额，如果不可能凑出，算法返回 -1
     * 注意：硬币数量数量无限决定这题无法使用贪心算法
     * <p>
     * <p>
     * <p>
     * 暴力动态规划，会重复计算子问题的答案，可以使用一个备忘录数组来优化
     *
     * @param coins  可选硬币⾯值
     * @param amount ⽬标⾦额
     * @return
     */
    private static int helper(int[] coins, int amount) {
        //base case
        if (amount == 0) {
            return 0;
        }
        if (amount < 0) {
            return -1;
        }
        //求最小值，初始化为正无穷
        int min = Integer.MAX_VALUE;
        for (int coin : coins) {
            //求解子问题
            int subMin = helper(coins, amount - coin);
            //子问题无解
            if (subMin == -1) continue;
            min = Math.min(min, subMin + 1);
        }
        return min;
    }

    public static int helper2(int[] coins, int amount) {
        if (amount < 1) return 0;
        return helper2(coins, amount, new int[amount + 1]);
    }

    /**
     * 这是一种自顶向下的做法，逐级分级子问题，并使用dp数组保存子问题的解，提高算法的效率
     *
     * @param coins  硬币面额
     * @param amount 目标面值
     * @param dp     备忘录数组，dp[i]的含义是当amount=i的时候的解
     * @return
     */
    private static int helper2(int[] coins, int amount, int[] dp) {
        //base case
        //正常结束
        if (amount == 0) return 0;
        //路径不通
        if (amount <= 0) return -1;
        //之前已经计算过结果了，这里直接返回结果
        if (dp[amount - 1] != 0) return dp[amount - 1];
        //最少需要的硬币数
        int min = Integer.MAX_VALUE;
        for (int coin : coins) {
            int subMin = helper2(coins, amount - coin, dp);
            if (subMin == -1) continue;
            min = Math.min(min, subMin + 1);
        }
        dp[amount - 1] = min == Integer.MAX_VALUE ? -1 : min;
        return dp[amount - 1];
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int k = sc.nextInt();
        int[] coins = new int[k];
        for (int i = 0; i < k; i++) {
            coins[i] = sc.nextInt();
        }
        int amount = sc.nextInt();

        System.out.println(helper(coins, amount));
        System.out.println(helper2(coins, amount));
    }


}
