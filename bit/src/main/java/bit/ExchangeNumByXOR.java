package bit;

/**
 * 通过异或交换两个数
 *
 * @author ：huangxin
 * @modified ：
 * @since ：2020/07/31 09:34
 */
public class ExchangeNumByXOR {

    /**
     * 不使用中间变量，使用异或运算交换两个数的值
     *
     * <pre>
     *   输入： a=3(0011) ,b=5(0101)
     * 第一次： a=a^b
     *    0 0 1 1
     *  ^ 0 1 0 1
     *  ------------
     * a= 0 1 1 0
     *
     * 第二次： b=a^b
     *    0 1 1 0
     *  ^ 0 1 0 1
     *  ------------
     * b= 0 0 1 1
     *
     * 第三次：a=a^b
     *    0 1 1 0
     *  ^ 0 0 1 1
     *  ------------
     * a= 0 1 0 1
     *
     *
     * 交换结果： a=0101(5) ,b=0011(3)
     * </pre>
     *
     * @param a
     * @param b
     */
    public static void exchange(int a, int b) {
        a = a ^ b;
        b = a ^ b;
        a = a ^ b;
        System.out.println(a + " " + b);
    }

    public static void main(String[] args) {
        exchange(3, 5);
    }

}
