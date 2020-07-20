package top.easyblog.math;

/**
 * 要求不使用比较运算符求两个数的最大值或最小值
 * 此题就是个纯数学问题，对于两个数a,b有以下规律：
 * <pre>
 * 最大值：Max(a,b)=(a+b+|a-b|)/2
 * 最小值：Min(a,b)=(a+b-|a-b|)/2
 * </pre>
 * 考虑带a和b非常大的时候会溢出，可以把a,b强制转换为long
 *
 * @author ：huangxin
 * @modified ：
 * @since ：2020/07/06 22:52
 */
public class 不使用比较运算符求最大最小值 {

    /**
     * 求两个数的最大值
     *
     * @param a
     * @param b
     * @return
     */
    public static int max(int a, int b) {
        return (int) (((long) a + (long) b + Math.abs((long) a - (long) b)) / 2);
    }

    /**
     * 求两个数的最小值
     *
     * @param a
     * @param b
     * @return
     */
    public static int min(int a, int b) {
        return (int) (((long) a + (long) b - Math.abs((long) a - (long) b)) / 2);
    }

    public static void main(String[] args) {
        System.out.println("max(5,6)：" + max(5, 6));
        System.out.println("max(-10,10)：" + max(-10, 10));
        System.out.println("max(-0,+0)：" + max(-0, +0));
        System.out.println("min(-10,+10)：" + min(-10, +10));
        System.out.println("min(34,56)：" + min(34, 56));
    }

}
