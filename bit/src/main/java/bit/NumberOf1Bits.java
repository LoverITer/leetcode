package bit;

/**
 * @author ：huangxin
 * @modified ：
 * @since ：2020/07/31 11:42
 */
public class NumberOf1Bits {

    /**
     * 第一种解法：利用&运算符的特性：n&(n-1) 相当于移除n这个数的二进制的最后一个1，
     * 因此，使用一个计数器{@code count}统计移动的次数就好了
     *
     * @param n 目标数
     * @return
     */
    public int hammingWeight(int n) {
        int count = 0;
        while (n != 0) {
            //统计移除的次数就是1的个数
            count++;
            //每次一次最低位的一个1
            n = n & (n - 1);
        }
        return count;
    }

    public int hammingWeight2(int n) {
        int count = 0;
        for (int i = 0; i < 32; i++) {
            if (((1 << i) & n) == (1 << i)) {
                count++;
            }
        }
        return count;
    }

}
