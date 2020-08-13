package bit;

/**
 * 位运算判断奇偶的两种方式
 *
 * @author ：huangxin
 * @modified ：
 * @since ：2020/08/03 10:57
 */
public class OddOrEven {

    /**
     * 基于&运算符判断数字奇偶
     *
     * @param num 数字
     * @return 如果是偶数返回true, 否则返回false
     */
    public static boolean isOdd1(int num) {
        return (num & 1) == 0;
    }

    /**
     * 基于位移运算符判断数字奇偶
     *
     * @param num 数字
     * @return 如果是偶数返回true, 否则返回false
     */
    public static boolean isOdd2(int num) {
        return ((num >> 1) << 1) == num;
    }

    public static void main(String[] args) {
        System.out.println(isOdd1(28));
        System.out.println(isOdd2(26));
        System.out.println(isOdd1(23));
        System.out.println(isOdd2(25));
    }

}
