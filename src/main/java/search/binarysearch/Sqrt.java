package search.binarysearch;

/**
 * <h4>LeetCode 69. x 的平方根</h4>
 * 实现 int sqrt(int x) 函数。
 * <p>
 * 计算并返回 x 的平方根，其中 x 是非负整数。
 * <p>
 * 由于返回类型是整数，结果只保留整数的部分，小数部分将被舍去。
 * 示例：
 * <pre>
 *    输入: 8
 *    输出: 2
 *    说明: 8 的平方根是 2.82842...,
 *         由于返回类型是整数，小数部分将被舍去。
 * </pre>
 * @author ：huangxin
 * @modified ：
 * @since ：2020/06/08 23:22
 */
public class Sqrt {

    /**
     * 这是一道典型的二分算法应用的题目，使用二分算法在1~x内搜索并配合一定的条件即可轻松解决这道题目
     * @param x
     * @return
     */
    public int mySqrt(int x) {
        if (x == 0 || x == 1) {
            return x;
        }

        int low = 1, high = x;
        int ans = -1;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if ((long) mid * mid == x || (Math.pow(mid + 1, 2) > x && Math.pow(mid, 2) < x)) {
                ans = mid;
                break;
            } else if ((long) mid * mid > x) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new Sqrt().mySqrt(8));
    }
}
