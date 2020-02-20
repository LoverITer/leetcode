package top.easyblog.math;

/**
 * @author ：huangxin
 * @modified ：
 * @since ：2020/02/21 00:11
 */
public class MathematicalAlgorithm {


    /**
     * 给定两个整数，被除数 dividend 和除数 divisor。将两数相除，要求不使用乘法、除法和 mod 运算符。
     * 返回被除数 dividend 除以除数 divisor 得到的商。
     *
     * @param dividend 被除数
     * @param divisor 除数
     * @return   商
     */
    public int divideWithOutOperator(int dividend, int divisor) {
        if (dividend == 0) {
            return 0;
        }
        if (divisor == 1) {
            return dividend;
        }
        if (divisor == -1) {
            if (dividend > Integer.MIN_VALUE) {
                return -dividend;
            } else {
                return Integer.MAX_VALUE;
            }
        }
        long a = dividend;
        long b = divisor;
        int sign = 1;
        boolean isOpposite = (a > 0 && b < 0) || (a < 0 && b > 0);
        if (isOpposite) {
            sign = -1;
        }
        long res = div(Math.abs(a), Math.abs(b));
        if (sign > 0) {
            return res > Integer.MAX_VALUE ? Integer.MAX_VALUE : (int) res;
        }
        return (int) -res;

    }

    private int div(long dividend, long divisor) {
        if (divisor > dividend) {
            return 0;
        }
        //count保存被除数被整数的次数
        long count = 1;
        long temp = divisor;
        while ((temp + temp) < dividend) {
            temp += temp;
            count += count;
        }
        return (int) count + div(dividend - temp, divisor);
    }


    public static void main(String[] args) {
        //System.out.println(new MathematicalAlgorithm().divideWithOutOperator(10, 3));
    }

}
