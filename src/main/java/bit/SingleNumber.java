package bit;

import java.util.Arrays;

/**
 * @author ：huangxin
 * @modified ：
 * @since ：2020/07/31 09:56
 */
public class SingleNumber {

    /**
     * 给定一个非空整数数组，除了某个元素只出现一次以外，
     * 其余每个元素均出现两次。找出那个只出现了一次的元素。
     * <pre>
     *  输入: [4,1,2,1,2]
     *  输出: 4
     * </pre>
     * 此题可以使用Set或Map,，但是没有必要，并且题目要求使用线性空间.
     * 可以考虑使用位运算：异或，异或有以下两个结论：
     * <pre>
     *  1、a^0=0^a=a
     *  2、a^a=0
     * </pre>
     *
     * @param nums
     * @return
     */
    public static int singleNumberⅠ(int[] nums) {
        //两个基本理论：a^0=0^a=a  a^a=0
        int res = 0;
        for (int num : nums) {
            res = res ^ num;
        }
        return res;
    }

    /**
     * 给定一个非空整数数组，除了某个元素只出现一次以外，
     * 其余每个元素均出现了三次。找出那个只出现了一次的元素。
     * <pre>
     *  输入: [0,1,0,1,0,1,99]
     *  输出: 99
     * </pre>
     * <p>
     * 如果能设计一个状态转换电路，使得一个数出现3次时能自动抵消为0，最后剩下的就是只出现1次的数。
     * 开始设计：一个二进制位只能表示0或者1。也就是天生可以记录一个数出现了一次还是两次。
     * <pre>
     * x ^ 0 = x;
     * x ^ x = 0;
     * </pre>
     * 要记录出现3次，需要两个二进制位。那么上面单独的x就不行了。我们需要两个变量，每个变量取一位：
     * <pre>
     * ab ^ 00 = ab;
     * ab ^ ab = 00;
     * </pre>
     * 这里，a、b都是32位的变量。我们使用a的第k位与b的第k位组合起来的两位二进制，表示当前位出现了几次。
     * 也就是，一个8位的二进制x就变成了16位来表示。
     * <p>
     * x = x[7] x[6] x[5] x[4] x[3] x[2] x[1] x[0]
     * <p>
     * x = (a[7]b[7]) (a[6]b[6]) ... (a[1]b[1]) (a[0]b[0])
     * <p>
     * 这样以设计就可以实现当一个数出现3次自动抵消为0了
     *
     * @param nums
     * @return
     */
    public static int singleNumberⅡ(int[] nums) {
        // a & ~a = 0
        // a & ~0 = a
        // a ^ a = 0
        // a ^ 0 = a
        int a = 0, b = 0;
        for (int num : nums) {
            a = ~b & (a ^ num);
            b = ~a & (b ^ num);
        }
        return a;
    }

    public static int[] singleNUmberⅢ(int[] nums) {
        int diff = 0;
        for (int num : nums) {
            diff ^= num;
        }
        int[] res = new int[]{diff, diff};
        //获取到最后一个1的位置
        diff = (diff & (diff - 1)) ^ diff;
        for (int num : nums) {
            if ((diff & num) == 0) {
                res[0] ^= num;
            } else {
                res[1] ^= num;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(singleNumberⅠ(new int[]{4, 1, 2, 1, 2}));
        System.out.println(singleNumberⅡ(new int[]{4, 1, 2, 1, 2, 1, 2}));
        System.out.println(Arrays.toString(singleNUmberⅢ(new int[]{1, 2, 1, 3, 2, 5})));

        System.out.println(8&~8);
        System.out.println(54&-54);
    }

}
