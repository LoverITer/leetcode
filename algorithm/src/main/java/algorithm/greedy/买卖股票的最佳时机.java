package algorithm.greedy;

import java.util.concurrent.Executors;

/**
 * @author ：huangxin
 * @modified ：
 * @since ：2020/08/10 21:06
 */
public class 买卖股票的最佳时机 {

    /**
     * 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
     * <p>
     * 设计一个算法来计算你所能获取的最大利润。你可以尽可能地完成更多的交易（多次买卖一支股票）。
     * <p>
     * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）
     * <p>
     * <p>
     * 使用贪心策略：比较前一天的价格比后一天的价格低那就买入，如果前一天的价格比后一天的价格高那就不买入
     *
     * @param prices
     * @return
     */
    public static int maxProfile1(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        int maxProfile = 0;
        for (int i = 0; i < prices.length - 1; i++) {
            if (prices[i] < prices[i + 1]) {
                maxProfile += prices[i + 1] - prices[i];
            }
        }
        return maxProfile;
    }


    public static void main(String[] args) {
        System.out.println(maxProfile1(new int[]{7,1,5,3,6,4}));
        System.out.println(maxProfile1(new int[]{7,1,5,6,6,4}));
        System.out.println(maxProfile1(new int[]{7,8,5,6,6,4}));
    }


}
